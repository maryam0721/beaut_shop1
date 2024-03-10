package com.project.beautyshop.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.beautyshop.model.Person;

import java.util.List;
@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addPerson(Person model);

    @Delete
    int deletePerson(Person model);

    @Update
    int updatePerson(Person model);

    @Query( "Select * From Person" )
    List<Person> getAllPerson();

    @Query( "Delete From Person" )
    void deleteAllPerson();
    @Query("Select * From Person Where id=:id")
    Person searchByIdPerson(int id);

    @Query( "Select * From Person Where name Like '%' || :keyBoard || '%' " )
    List<Person> searchByNamePerson(String keyBoard);

    @Query("Select * From Person Where family=:family")
    List<Person> searchByFamilyPerson(int family);

}
