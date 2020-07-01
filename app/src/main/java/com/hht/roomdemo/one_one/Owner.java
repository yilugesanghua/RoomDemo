package com.hht.roomdemo.one_one;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity()
public class Owner {
    @PrimaryKey
    private  int user_id;
    @ColumnInfo(name = "user_name")
    private String name;
//    @Ignore
//    private List<Animal>animals;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

//    public void setAnimals(List<Animal> animals) {
//        this.animals = animals;
//    }
//
//    public List<Animal> getAnimals() {
//        return animals;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
