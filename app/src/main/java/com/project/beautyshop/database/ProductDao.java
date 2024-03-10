package com.project.beautyshop.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.beautyshop.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Product model);

    @Delete
    int delete(Product model);

    @Update
    int update(Product model);

    @Query( "Select * From Product" )
    List<Product> getAll();

    @Query( "Delete From Product" )
    void deleteAll();

    @Query("Select * From Product Where id=:id")
    Product searchById(int id);

    @Query( "Select * From Product Where name Like '%' || :keyBoard || '%' " )
    List<Product> searchByName(String keyBoard);

    @Query("Select * From Product Where title=:inputTitle")
    List<Product> searchByTitle(int inputTitle);

    @Query("Select * From Product Where mainImage=:mainImage")
    List<Product> searchByMainImage(int mainImage);

    @Query("Select * From Product Where rawPrice!=:off")
    List<Product> searchByOff(int off);

    @Query("Select * From Product Where quantity!=:select")
    List<Product> searchByQuantity(int select);

    @Query("Select sum(quantity) from Product")
    int getSumQuantity();
}
