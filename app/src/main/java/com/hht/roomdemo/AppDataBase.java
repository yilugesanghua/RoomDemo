package com.hht.roomdemo;


import com.hht.roomdemo.one_one.Animal;
import com.hht.roomdemo.one_one.AnimalDao;
import com.hht.roomdemo.one_one.Owner;
import com.hht.roomdemo.one_one.OwnerDao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Animal.class, Owner.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    //单例
    public static AppDataBase getDatabase() {
        return Holder.instance;
    }

    abstract UserDao userdao();

    abstract AnimalDao animDao();
    abstract OwnerDao ownerDao();
//    abstract OwnerAndAnimDao ownerAndAnimDao();

    private static class Holder {
        private static final AppDataBase instance = Room.databaseBuilder(App.getInstance(), AppDataBase.class, "data")
                .allowMainThreadQueries()   //设置允许在主线程进行数据库操作，默认不允许，建议都设置为默认
                // .fallbackToDestructiveMigration()  //设置数据库升级的时候清除之前的所有数据
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                    }
                }).addMigrations(new Migration(1,2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("CREATE TABLE IF NOT EXISTS `owner` (`user_id` INTEGER PRIMARY KEY   NOT NULL, `user_name` TEXT)");
                        database.execSQL("CREATE TABLE IF NOT EXISTS `animal` (`anim_id` INTEGER PRIMARY KEY   NOT NULL, `anim_name` TEXT, `dog_owner_id` INTEGER )");

                    }
                })
                .build();
    }
}

