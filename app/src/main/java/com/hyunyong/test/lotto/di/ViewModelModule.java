package com.hyunyong.test.lotto.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hyunyong.test.lotto.viewmodel.DeepLinkViewModel;
import com.hyunyong.test.lotto.viewmodel.FrequencyViewModel;
import com.hyunyong.test.lotto.viewmodel.LookUpViewModel;
import com.hyunyong.test.lotto.viewmodel.LottoViewModel;
import com.hyunyong.test.lotto.viewmodel.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LottoViewModel.class)
    abstract ViewModel bindLottoViewModel(LottoViewModel lottoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LookUpViewModel.class)
    abstract ViewModel bindLookUpViewModel(LookUpViewModel lookUpViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DeepLinkViewModel.class)
    abstract ViewModel bindDeepLinkViewModel(DeepLinkViewModel deepLinkViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FrequencyViewModel.class)
    abstract ViewModel bindFrequencyViewModel(FrequencyViewModel frequencyViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);
}
