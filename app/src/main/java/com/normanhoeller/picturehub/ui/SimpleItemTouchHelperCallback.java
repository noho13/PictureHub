package com.normanhoeller.picturehub.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import static android.support.v7.widget.helper.ItemTouchHelper.*;

/**
 * Created by norman on 03/11/15.
 */
public class SimpleItemTouchHelperCallback  extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter itemTouchHelperAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.itemTouchHelperAdapter = itemTouchHelperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ACTION_STATE_IDLE, RIGHT) | makeFlag(ACTION_STATE_SWIPE, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
