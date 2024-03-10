package com.project.beautyshop.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.beautyshop.model.Person;
import com.project.beautyshop.model.Product;

@Database( version = 1 , exportSchema = false , entities = {Product.class, Person.class})
public abstract class DatabaseShop extends RoomDatabase {
    private static DatabaseShop databaseShop;
    public static DatabaseShop getAppDataBase(Context context){
        if(databaseShop == null){
            databaseShop = Room.databaseBuilder( context .getApplicationContext(),DatabaseShop.class,"db_beauty" )
                    .allowMainThreadQueries()
                    .build();
        }

        return databaseShop;
    }

    public abstract ProductDao productDao();
    public abstract PersonDao personDao();

}