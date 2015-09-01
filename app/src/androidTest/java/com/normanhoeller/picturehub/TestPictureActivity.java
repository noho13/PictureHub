package com.normanhoeller.picturehub;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.normanhoeller.picturehub.api.RestClient;
import com.normanhoeller.picturehub.dagger.ApplicationComponent;
import com.normanhoeller.picturehub.model.SearchResult;
import com.normanhoeller.picturehub.ui.PictureFragment;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by norman on 01/09/15.
 */
@RunWith(AndroidJUnit4.class)
public class TestPictureActivity {

    @Inject
    RestClient restClient;

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
    }

    @Test
    public void testTest() {
        assertNotNull(restClient);
        SearchResult searchResult = new SearchResult();
        searchResult.setPage(2);
        Mockito.when(restClient.getService().getSearchResult(Mockito.anyString())).thenReturn(Observable.just(searchResult));

        Intent launchIntent = new Intent();
        launchIntent.putExtra(PictureActivity.SEARCH_QUERY, "bikes");
        activityRule.launchActivity(launchIntent);

        PictureActivity activity = activityRule.getActivity();
        PictureFragment fragment = (PictureFragment) activity.getSupportFragmentManager().findFragmentById(android.R.id.content);

        assertNotNull(fragment);
    }

}
