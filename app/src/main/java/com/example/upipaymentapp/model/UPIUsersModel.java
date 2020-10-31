package com.example.upipaymentapp.model;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UPIUserItem")
public class UPIUsersModel implements Serializable {

    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "UserName")
    public String userName;

    @ColumnInfo(name = "UPI Id")
    public String UserUPIID;

    @ColumnInfo(name = "UserImage")
    public String userImage;

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

    public String getUserUPIID() {
        return UserUPIID;
    }

    public void setUserUPIID(String userUPIID) {
        UserUPIID = userUPIID;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
