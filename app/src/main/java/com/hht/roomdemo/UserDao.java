package com.hht.roomdemo;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    //简单sql语句，查询user表所有的column
    @Query("SELECT * FROM table_user")
    Observable<List<User>> getAll();

    //根据条件查询，方法参数和注解的sql语句参数一一对应
    @Query("SELECT * FROM table_user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    //同上
    @Query("SELECT * FROM table_user WHERE firstName LIKE :first AND "
            + "lastName LIKE :last LIMIT 1")
    User findByName(String first, String last);

    //同上
    @Query("SELECT * FROM table_user WHERE uid = :uid")
    User findByUid(int uid);

//-----------------------insert----------------------

    // OnConflictStrategy.REPLACE表示如果已经有数据，那么就覆盖掉
//数据的判断通过主键进行匹配，也就是uid，非整个user对象
//返回Long数据表示，插入条目的主键值（uid）
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(User user);

    //返回List<Long>数据表示被插入数据的主键uid列表
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(User... users);

    //返回List<Long>数据表示被插入数据的主键uid列表
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<User> users);

    //---------------------update------------------------
//更新已有数据，根据主键（uid）匹配，而非整个user对象
//返回类型int代表更新的条目数目，而非主键uid的值。
//表示更新了多少条目
    @Update()
    int update(User user);

    //同上
    @Update()
    int updateAll(User... user);

    //同上
    @Update()
    int updateAll(List<User> user);

    //-------------------delete-------------------
//删除user数据，数据的匹配通过主键uid实现。
//返回int数据表示删除了多少条。非主键uid值。
    @Delete
    int delete(User user);

    //同上
    @Delete
    int deleteAll(List<User> users);

    //同上
    @Delete
    int deleteAll(User... users);

}

