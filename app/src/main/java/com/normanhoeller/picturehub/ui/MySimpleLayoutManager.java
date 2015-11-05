package com.normanhoeller.picturehub.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by norman on 03/11/15.
 */
public class MySimpleLayoutManager extends RecyclerView.LayoutManager {

    private static final int SCROLL_DISTANCE = 80; // dp
    private static final String TAG = "MySimpleLayoutManager";
    private final int mScrollDistance;
    private int mFirstPosition;
    private boolean isEmptyLayout;

    public MySimpleLayoutManager(Context c) {
        final DisplayMetrics dm = c.getResources().getDisplayMetrics();
        mScrollDistance = (int) (SCROLL_DISTANCE * dm.density + 0.5f);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, "onLayoutChildren() - " + state.getItemCount());
//        final int parentBottom = getHeight() - getPaddingBottom();
        final View oldTopView = getChildCount() > 0 ? getChildAt(0) : null;
        int oldTop = getPaddingTop();
        if (oldTopView != null) {
            oldTop = oldTopView.getTop();
        }
        Log.d(TAG, "childCount before detachAnd...: " + getChildCount());
        if (getChildCount() == 0) {
            isEmptyLayout = true;
        } else {
            isEmptyLayout = false;
        }
        detachAndScrapAttachedViews(recycler);
        Log.d(TAG, "childCount after detachAnd...: " + getChildCount());

        int top = oldTop;
        int bottom;
        int left = getPaddingLeft();
        int right;

//        int count = state.getItemCount();
//        for (int i = 0; mFirstPosition + i < count && top < parentBottom; i++) {
        if (isEmptyLayout) {
            for (int i = 0; mFirstPosition + i < 4; i++) {
                View v = recycler.getViewForPosition(mFirstPosition + i);
                addView(v, i);
                measureChildWithMargins(v, 0, 0);
                bottom = top + getDecoratedMeasuredHeight(v);
                right = left + getDecoratedMeasuredWidth(v);
                layoutDecorated(v, left, top, right, bottom);
                top = top + 80;
                left = left + 20;
            }
        } else {
            for (int i = 0; mFirstPosition + i < 4; i++) {
                View v;
                if (i == 0) {
                    v = recycler.getViewForPosition(3);
                } else {
                    v = recycler.getViewForPosition(i - 1);
                }
                addView(v, i);
                measureChildWithMargins(v, 0, 0);
                bottom = top + getDecoratedMeasuredHeight(v);
                right = left + getDecoratedMeasuredWidth(v);
                layoutDecorated(v, left, top, right, bottom);
                top = top + 80;
                left = left + 20;
            }
        }
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler,
                                  RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }

        int scrolled = 0;
        final int left = getPaddingLeft();
        final int right = getWidth() - getPaddingRight();
        if (dy < 0) {
            while (scrolled > dy) {
                final View topView = getChildAt(0);
                final int hangingTop = Math.max(-getDecoratedTop(topView), 0);
                final int scrollBy = Math.min(scrolled - dy, hangingTop);
                scrolled -= scrollBy;
                offsetChildrenVertical(scrollBy);
                if (mFirstPosition > 0 && scrolled > dy) {
                    mFirstPosition--;
                    View v = recycler.getViewForPosition(mFirstPosition);
                    addView(v, 0);
                    measureChildWithMargins(v, 0, 0);
                    final int bottom = getDecoratedTop(topView);
                    final int top = bottom - getDecoratedMeasuredHeight(v);
                    layoutDecorated(v, left, top, right, bottom);
                } else {
                    break;
                }
            }
        } else if (dy > 0) {
            final int parentHeight = getHeight();
            while (scrolled < dy) {
                final View bottomView = getChildAt(getChildCount() - 1);
                final int hangingBottom =
                        Math.max(getDecoratedBottom(bottomView) - parentHeight, 0);
                final int scrollBy = -Math.min(dy - scrolled, hangingBottom);
                scrolled -= scrollBy;
                offsetChildrenVertical(scrollBy);
                if (scrolled < dy && state.getItemCount() > mFirstPosition + getChildCount()) {
                    View v = recycler.getViewForPosition(mFirstPosition + getChildCount());
                    final int top = getDecoratedBottom(getChildAt(getChildCount() - 1));
                    addView(v);
                    measureChildWithMargins(v, 0, 0);
                    final int bottom = top + getDecoratedMeasuredHeight(v);
                    layoutDecorated(v, left, top, right, bottom);
                } else {
                    break;
                }
            }
        }
        recycleViewsOutOfBounds(recycler);
        return scrolled;
    }

    @Override
    public View onFocusSearchFailed(View focused, int direction,
                                    RecyclerView.Recycler recycler, RecyclerView.State state) {
        final int oldCount = getChildCount();

        if (oldCount == 0) {
            return null;
        }

        final int left = getPaddingLeft();
        final int right = getWidth() - getPaddingRight();

        View toFocus = null;
        int newViewsHeight = 0;
        if (direction == View.FOCUS_UP || direction == View.FOCUS_BACKWARD) {
            while (mFirstPosition > 0 && newViewsHeight < mScrollDistance) {
                mFirstPosition--;
                View v = recycler.getViewForPosition(mFirstPosition);
                final int bottom = getDecoratedTop(getChildAt(0));
                addView(v, 0);
                measureChildWithMargins(v, 0, 0);
                final int top = bottom - getDecoratedMeasuredHeight(v);
                layoutDecorated(v, left, top, right, bottom);
                if (v.isFocusable()) {
                    toFocus = v;
                    break;
                }
            }
        }
        if (direction == View.FOCUS_DOWN || direction == View.FOCUS_FORWARD) {
            while (mFirstPosition + getChildCount() < state.getItemCount() &&
                    newViewsHeight < mScrollDistance) {
                View v = recycler.getViewForPosition(mFirstPosition + getChildCount());
                final int top = getDecoratedBottom(getChildAt(getChildCount() - 1));
                addView(v);
                measureChildWithMargins(v, 0, 0);
                final int bottom = top + getDecoratedMeasuredHeight(v);
                layoutDecorated(v, left, top, right, bottom);
                if (v.isFocusable()) {
                    toFocus = v;
                    break;
                }
            }
        }

        return toFocus;
    }

    public void recycleViewsOutOfBounds(RecyclerView.Recycler recycler) {
        final int childCount = getChildCount();
        final int parentWidth = getWidth();
        final int parentHeight = getHeight();
        boolean foundFirst = false;
        int first = 0;
        int last = 0;
        for (int i = 0; i < childCount; i++) {
            final View v = getChildAt(i);
            if (v.hasFocus() || (getDecoratedRight(v) >= 0 &&
                    getDecoratedLeft(v) <= parentWidth &&
                    getDecoratedBottom(v) >= 0 &&
                    getDecoratedTop(v) <= parentHeight)) {
                if (!foundFirst) {
                    first = i;
                    foundFirst = true;
                }
                last = i;
            }
        }
        for (int i = childCount - 1; i > last; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
        for (int i = first - 1; i >= 0; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
        if (getChildCount() == 0) {
            mFirstPosition = 0;
        } else {
            mFirstPosition += first;
        }
    }
}

