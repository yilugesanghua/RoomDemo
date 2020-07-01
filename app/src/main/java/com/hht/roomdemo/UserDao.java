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

/**
 *
 * https://blog.csdn.net/Ricardo6/article/details/88385613
 * INSERT，UPDATE，DELETE操作可以返回Completable，Single和Maybe三种对象，
 * 而QEURY操作，如果是一次性查询，可以返回Single，Maybe，
 * 如需要可观测对象，可以返回Observable和Flowable对象，
 * 但是注意，不能返回Completable对象。
 *
 *
 * 先来简单介绍一下这几个属于RxJava的对象的特点。
 *
 * Completable：只有onComplete和onError方法，即是只有“完成”和“错误”两种状态，不会返回具体的结果。
 * Single：其回调为onSuccess和onError，查询成功会在onSuccess中返回结果，需要注意的是，如果未查询到结果，即查询结果为空，会直接走onError回调，抛出EmptyResultSetException异常。
 * Maybe：其回调为onSuccess，onError，onComplete，查询成功，如果有数据，会先回调onSuccess再回调onComplete，如果没有数据，则会直接回调onComplete。
 * Flowable/Observable：这俩相信不用多介绍了，这是返回一个可观察的对象，每当查询语句查询的部分有变化时，都会回调它的onNext方法，直到Rx流断开。
 */
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

