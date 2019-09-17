package com.hyunyong.test.lotto.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.hyunyong.test.lotto.data.Repository;
import com.hyunyong.test.lotto.data.Webservice;
import com.hyunyong.test.lotto.db.AppDatabase;
import com.hyunyong.test.lotto.db.LottoDao;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class})
abstract class AppModule {

    @Binds
    abstract Context provideContext(Application application);

    @Singleton
    @Provides
    static SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    static Repository provideRepository(Webservice webservice, LottoDao lottoDao) {
        return new Repository(webservice, lottoDao);
    }

    @Singleton
    @Provides
    static AppDatabase provideAppDatabase(Application application) {
        return Room.inMemoryDatabaseBuilder(application, AppDatabase.class).allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    static LottoDao provideLottoDao(AppDatabase appDatabase) {
        return appDatabase.lottoDao();
    }
}
