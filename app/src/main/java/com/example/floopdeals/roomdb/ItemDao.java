package com.example.floopdeals.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.floopdeals.model.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    List<Item> getAllItems();

    @Insert
    void insertItem(Item item);

    @Delete
    void deleteItem(Item item);
}
