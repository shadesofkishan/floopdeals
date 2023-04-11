package com.example.floopdeals.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.floopdeals.model.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
