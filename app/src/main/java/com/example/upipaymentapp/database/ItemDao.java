package com.example.upipaymentapp.database;

import com.example.upipaymentapp.model.UPIUsersModel;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ItemDao {

    @Insert
    void insertItem(UPIUsersModel upiUsersModel);

    /*@Query("UPDATE UPIUserItem SET UserName=:upiUsersModel, WHERE Id =:id")
    int updateItem(UPIUsersModel upiUsersModel, int id);*/

    @Update
    void updateItem(UPIUsersModel upiUsersModel);

    @Query("DELETE FROM UPIUserItem WHERE Id =:userID")
    int deleteItem(int userID);

    @Query("SELECT * FROM UPIUserItem")
    List<UPIUsersModel> getAllItem();

}
