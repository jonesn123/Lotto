package com.hyunyong.test.lotto.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.hyunyong.test.lotto.data.Lotto;

@Database(entities = {Lotto.class}, version = 1)
@TypeConverters({ConverterDoubleToString.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract LottoDao lottoDao();
}
