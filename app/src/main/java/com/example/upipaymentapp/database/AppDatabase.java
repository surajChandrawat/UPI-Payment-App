package com.example.upipaymentapp.database;

import com.example.upipaymentapp.model.UPIUsersModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = UPIUsersModel.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
