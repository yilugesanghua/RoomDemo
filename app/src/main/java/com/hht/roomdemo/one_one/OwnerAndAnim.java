package com.hht.roomdemo.one_one;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

/**
 *
 */
public class OwnerAndAnim {
    @Embedded
    private Owner owner;
    @Relation(parentColumn = "user_id",entityColumn = "dog_owner_id")
    private Animal animal;

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "OwnerAndAnim{" +
                "owner=" + owner +
                ", animal=" + animal +
                '}';
    }
}
