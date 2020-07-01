package com.hht.roomdemo.one_one;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;

@Dao
public abstract class AnimalDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertAnim(Animal animal);
}
