package com.example.assignment3.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment3.model.User.User;
import com.example.assignment3.model.User.UserDOA;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {User.class}, version = 4, exportSchema = false)
public abstract class database extends RoomDatabase {
    private static database INSTANCE;
    public abstract UserDOA userDAO();

    // this initializes the databse and inserts the defaault values into the database
    public static database getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), database.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            if(isUserEmpty(INSTANCE)){
                populateDatabase(INSTANCE);
            }

        }
        return INSTANCE;
    }
    //this checks if the table is empty
    private static boolean isUserEmpty(database INSTANCE){
        if(INSTANCE.userDAO().getAll().size()==0){
            return true;
        }else{
            return false;
        }
    }
    //this destroys the database
    public static void destroyInstance() {

        INSTANCE = null;
    }
    //this populates the databse
    private static void populateDatabase(final database db){
        for(User item : getUserData()){
            addUser(item,db);
        }
    }

    //this gets the data in the database
    private static List<User> getUserData(){
        List<User> UserItems= new ArrayList<User>();

        User item1 = new User();
        item1.setMarker("UserSession");
        item1.setLat(44.6488);
        item1.setLon(-63.5752);
        item1.setIsTrackerOn(false);
        UserItems.add(item1);


        return UserItems;
    }

    //this adds fields to the database
    private static void addUser(User input,final database db){
        db.userDAO().insertAll(input);
    }

}
