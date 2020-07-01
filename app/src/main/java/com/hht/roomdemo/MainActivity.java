package com.hht.roomdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hht.roomdemo.one_more.OneToMore;
import com.hht.roomdemo.one_one.Animal;
import com.hht.roomdemo.one_one.Owner;
import com.hht.roomdemo.one_one.OwnerAndAnim;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();
        user.setId(1);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setUid(user.getId() + 1);
        Gift gift = new Gift();
        gift.setgId(1);
        gift.setName("flower");
        user.setGift(gift);
        AppDataBase.getDatabase().userdao().insert(user);
        AppDataBase.getDatabase().userdao().getAll().subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("getAll==", "onSubscribe: ");
            }

            @Override
            public void onNext(List<User> users) {
                Log.e("getAll==", "onNext: " + users.size());
                for (int i = 0; i < users.size(); i++) {
                    Log.e("User: ", users.get(i).getUid() + "");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("getAll==", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("getAll==", "onComplete");
            }
        });
    }

    public void insert(View view) {


        User user = AppDataBase.getDatabase().userdao().findByUid(2);
        Log.e("insert: -->", user.getGift().toString());
//        user.setUid(3);
//        AppDataBase.getDatabase().userdao().update(user);
        //one to one
//        oneToOne();
        oneToMore();
    }


    private void oneToMore() {
        Owner owner = new Owner();
        owner.setUser_id(2);
        owner.setName("Jellal2");
        Animal animal = new Animal();
        animal.setAnim_id(100 * 2);
        animal.setDog_owner_id(2);
        animal.setName("more1");
        Animal animal2 = new Animal();
        animal2.setAnim_id(200 * 2);
        animal2.setDog_owner_id(2);
        animal2.setName("more2");


        Owner owner2 = new Owner();
        owner2.setUser_id(3);
        owner2.setName("Jellal3");
        Animal animal3 = new Animal();
        animal3.setAnim_id(4);
        animal3.setDog_owner_id(3);
        animal3.setName("more3");
        AppDataBase.getDatabase().ownerDao().insertOwner(owner);
        AppDataBase.getDatabase().ownerDao().insertOwner(owner2);
        AppDataBase.getDatabase().animDao().insertAnim(animal);
        AppDataBase.getDatabase().animDao().insertAnim(animal2);
        AppDataBase.getDatabase().animDao().insertAnim(animal3);
        List<OneToMore> ownerAndAnims = AppDataBase.getDatabase().ownerDao().getOneToMore();
        Log.e("TAG", ownerAndAnims.size() + "");
        for (int i = 0; i < ownerAndAnims.size(); i++) {
            Log.e("one To more: ", ownerAndAnims.get(i).toString());
        }
        AppDataBase.getDatabase().ownerDao().deleteOneToMore(owner);
        List<OneToMore> ownerAndAnims2 = AppDataBase.getDatabase().ownerDao().getOneToMore();
        if (ownerAndAnims2 != null) {
            Log.e("TAG", ownerAndAnims2.size() + "");
            for (int i = 0; i < ownerAndAnims2.size(); i++) {
                Log.e("TAG", ownerAndAnims2.get(i).toString());
            }
        } else {
            Log.e("TAG", "null null ");
        }
    }

    private void oneToOne() {
        AppDataBase.getDatabase().userdao().delete(user);
        Owner owner = new Owner();
        owner.setUser_id(1);
        owner.setName("Jellal");
        Animal animal = new Animal();
        animal.setAnim_id(100);
        animal.setDog_owner_id(1);
        animal.setName("dog1");

        Owner owner2 = new Owner();
        owner2.setUser_id(2);
        owner2.setName("Jella2");
        Animal animal2 = new Animal();
        animal2.setAnim_id(200);
        animal2.setDog_owner_id(2);
        animal2.setName("dog2");
        AppDataBase.getDatabase().ownerDao().insertOwner(owner);
        AppDataBase.getDatabase().ownerDao().insertOwner(owner2);
        AppDataBase.getDatabase().animDao().insertAnim(animal);
        AppDataBase.getDatabase().animDao().insertAnim(animal2);
        AppDataBase.getDatabase().ownerDao().deleteOwner(owner);

    }
}
