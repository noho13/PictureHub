package com.normanhoeller.picturehub;

import android.app.Application;

import com.normanhoeller.picturehub.dagger.ApplicationComponent;
import com.normanhoeller.picturehub.dagger.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by norman on 31/08/15.
 */
public class PictureHubApplication extends Application {

    private ApplicationComponent component = null;

    @Singleton
    @Component(modules = ApplicationModule.class)
    public interface Production extends ApplicationComponent {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (component == null) {
           component = DaggerPictureHubApplication_Production.builder().applicationModule(new ApplicationModule()).build();
        }
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public void setComponent(ApplicationComponent component) {
        this.component = component;
    }
}
