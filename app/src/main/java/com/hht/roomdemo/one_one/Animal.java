package com.hht.roomdemo.one_one;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * ForeignKey.CASCADE 删除
 * entity 主表
 * parentColumns 主表id
 * childColumns  外键id
 */
@Entity(foreignKeys = @ForeignKey(entity = Owner.class,parentColumns = "user_id"
,childColumns = "dog_owner_id",onDelete = ForeignKey.CASCADE))
public class Animal {
    @PrimaryKey
    private  int anim_id;
    @ColumnInfo(name = "anim_name")
    private String name;
    private int dog_owner_id;

    public void setDog_owner_id(int dog_owner_id) {
        this.dog_owner_id = dog_owner_id;
    }

    public int getDog_owner_id() {
        return dog_owner_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnim_id(int anim_id) {
        this.anim_id = anim_id;
    }

    public String getName() {
        return name;
    }

    public int getAnim_id() {
        return anim_id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "anim_id=" + anim_id +
                ", name='" + name + '\'' +
                '}';
    }
}
