package com.hht.roomdemo;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Table_User")//表名字
public class User {

    @PrimaryKey(autoGenerate = true) //设置自增长，默认是false,必须要设置主键，不然会报错
    private int id;
    private String userName;
    //字段映射具体的数据表字段名
    @ColumnInfo(name = "uid")
    private int uid;

    private String firstName;

    private String lastName;
    @Embedded
    private Gift gift;

    public User() {
    }

    @Ignore
    public User(int uid, String userName) {
        this.uid = uid;
        this.userName = userName;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

