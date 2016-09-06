package com.normanhoeller.picturehub;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RelativeLayout rootView = (RelativeLayout) findViewById(R.id.root_view);

        final EditText query = (EditText) findViewById(R.id.et_query);
        final FloatingActionButton goSearch = (FloatingActionButton) findViewById(R.id.btn_go);

        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryText = query.getText().toString();
                if (!TextUtils.isEmpty(queryText)) {
                    startSearchActivity(queryText);
                }
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(rootView, new Fade());
                toggleViews(query, goSearch);
            }
        });
    }

    private void toggleViews(View... views) {
        for (View v : views) {
            boolean isVisible = v.getVisibility() ==  View.VISIBLE;
            v.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }

    private void startSearchActivity(@NonNull String searchQuery) {
        Intent startActivity = new Intent(this, PictureActivity.class);
        startActivity.putExtra(PictureActivity.SEARCH_QUERY, searchQuery);
        startActivity(startActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
