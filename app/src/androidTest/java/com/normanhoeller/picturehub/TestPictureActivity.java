package com.normanhoeller.picturehub;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.normanhoeller.picturehub.api.ShutterStockService;
import com.normanhoeller.picturehub.dagger.ApplicationComponent;
import com.normanhoeller.picturehub.ui.PictureFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by norman on 01/09/15.
 */
@RunWith(AndroidJUnit4.class)
public class TestPictureActivity {

    private int expectedItems = 1;
    private PictureFragment fragment;
    @Inject
    ShutterStockService shutterStockService;

    @Component(modules = MockApplicationModule.class)
    @Singleton
    public interface TestComponent extends ApplicationComponent {
        void inject(TestPictureActivity testPictureActivity);
    }

    @Rule
    public ActivityTestRule<PictureActivity> activityRule = new ActivityTestRule<>(
            PictureActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False so we can customize the intent per test method


    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        PictureHubApplication app
                = (PictureHubApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerTestPictureActivity_TestComponent.builder().mockApplicationModule(new MockApplicationModule()).build();
        app.setComponent(component);
        component.inject(this);

        Intent launchIntent = new Intent();
        launchIntent.putExtra(PictureActivity.SEARCH_QUERY, "bikes");
        activityRule.launchActivity(launchIntent);
        PictureActivity activity = activityRule.getActivity();
        fragment = (PictureFragment) activity.getSupportFragmentManager().findFragmentById(android.R.id.content);
    }

    @Test
    public void fragmentNotNull() {
        assertNotNull(shutterStockService);
        assertNotNull(fragment);
    }

    @Test
    public void itemCount(){
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.rv_item_grid);
        int itemCount = recyclerView.getAdapter().getItemCount();
        assertEquals(expectedItems, itemCount);
    }

}
