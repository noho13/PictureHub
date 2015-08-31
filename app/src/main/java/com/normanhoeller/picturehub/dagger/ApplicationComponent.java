package com.normanhoeller.picturehub.dagger;

import com.normanhoeller.picturehub.ui.PictureFragment;

/**
 * Created by norman on 31/08/15.
 */
public interface ApplicationComponent {
    void inject(PictureFragment fragment);
}
