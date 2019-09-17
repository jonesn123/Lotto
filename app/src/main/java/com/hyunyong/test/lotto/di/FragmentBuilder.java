package com.hyunyong.test.lotto.di;

import com.hyunyong.test.lotto.view.DeepLinkFragment;
import com.hyunyong.test.lotto.view.FrequencyFragment;
import com.hyunyong.test.lotto.view.LookupLottoFragment;
import com.hyunyong.test.lotto.view.LottoFragment;
import com.hyunyong.test.lotto.view.SplashFragment;
import com.hyunyong.test.lotto.view.WelcomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector
    abstract WelcomeFragment contributeWelcomeFragment();

    @ContributesAndroidInjector
    abstract LottoFragment contributeLottoFragment();

    @ContributesAndroidInjector
    abstract LookupLottoFragment contributeLookupLottoFragment();

    @ContributesAndroidInjector
    abstract FrequencyFragment contributeFrequencyFragment();

    @ContributesAndroidInjector
    abstract DeepLinkFragment contributeDeepLinkFragment();
}
