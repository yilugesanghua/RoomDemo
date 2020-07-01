package com.hht.roomdemo.one_more;

import com.hht.roomdemo.one_one.Animal;
import com.hht.roomdemo.one_one.Owner;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class OneToMore {
    @Embedded
    private Owner owner;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "dog_owner_id"
    )
    private List<Animal> animalList;

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
}
