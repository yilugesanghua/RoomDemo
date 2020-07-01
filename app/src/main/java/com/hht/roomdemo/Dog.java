package com.hht.roomdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Dog {
    @PrimaryKey
    private int dId;
    @ColumnInfo(name = "d_name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getName() {
        return name;
    }

    public int getdId() {
        return dId;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dId=" + dId +
                ", name='" + name + '\'' +
                '}';
    }
}
