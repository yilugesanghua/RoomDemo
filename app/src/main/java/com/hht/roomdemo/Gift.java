package com.hht.roomdemo;

public class Gift {
    public int gId;
    public String name;

    public void setgId(int gId) {
        this.gId = gId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getgId() {
        return gId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "gId=" + gId +
                ", name='" + name + '\'' +
                '}';
    }
}
