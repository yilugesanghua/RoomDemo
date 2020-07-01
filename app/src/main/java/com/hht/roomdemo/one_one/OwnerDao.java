package com.hht.roomdemo.one_one;

import com.hht.roomdemo.one_more.OneToMore;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public abstract class OwnerDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertOwner(Owner owner);
    //1 - 1
    @Transaction
    @Query("SELECT * FROM Owner")
    public abstract List<OwnerAndAnim> getDogsAndOwners();
    //1 - more
    @Transaction
    @Query("SELECT * FROM Owner")
    public abstract List<OneToMore>  getOneToMore();

    @Delete
    public abstract void deleteOneToMore(Owner owner);
    @Delete
    public abstract void deleteOwner(Owner owner);
}
