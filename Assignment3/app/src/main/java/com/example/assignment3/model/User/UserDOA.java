package com.example.assignment3.model.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDOA {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id IN (:ids)")
    List<User> loadAllByIds(int[] ids);

    @Query("SELECT * FROM User WHERE marker LIKE :marker")
    User findByItem(String marker);

    @Insert
    void insertAll(User... users);

    @Query("UPDATE User set lat = :latGeo where marker = :marker")
    int setLat(String marker, double latGeo);

    @Query("UPDATE User set lon = :longGeo where marker = :marker")
    int setLon(String marker,double longGeo);

    @Query("UPDATE User set isTrackerOn = :TrackerStatus where marker = :marker")
    int setTracker(String marker,boolean TrackerStatus);

    @Delete
    void delete(User user);
}
