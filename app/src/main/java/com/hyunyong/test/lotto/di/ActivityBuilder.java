package com.hyunyong.test.lotto.di;

import com.hyunyong.test.lotto.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
