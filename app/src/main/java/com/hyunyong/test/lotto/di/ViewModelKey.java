package com.hyunyong.test.lotto.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Retention;

import dagger.MapKey;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@MapKey
public @interface ViewModelKey{
    Class<? extends ViewModel> value();
}
