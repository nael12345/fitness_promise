package com.example.user.myapplication;

/**
 * Created by user on 21/02/2018.
 */

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FP_DB extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FP_DB";

    public FP_DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create user table
        String CREATE_USER_TABLE = "CREATE TABLE user ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "username TEXT, "+
                "password TEXT )";

        /*
        String CREATE_CAR_TABLE = "CREATE TABLE car ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "company TEXT, "+
                "model TEXT, "+
                "year TEXT, "+
                "engine REAL, "+
                "horse_power REAL, "+
                "condition TEXT, "+
                "phone TEXT )"; */



        // create userss table
        db.execSQL(CREATE_USER_TABLE);
        //db.execSQL(CREATE_CAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older users table if existed
        db.execSQL("DROP TABLE IF EXISTS user");
        //db.execSQL("DROP TABLE IF EXISTS car");

        // create fresh users table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) user + get all users + delete all users
     */

    // users table name
    private static final String TABLE_USER = "user";

    // users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String[] USER_COLUMNS = {KEY_ID,KEY_NAME,KEY_USERNAME,KEY_PASSWORD};

    public void addUser(User user){
        Log.d("addUser", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // get title
        values.put(KEY_USERNAME, user.getUsername()); // get title
        values.put(KEY_PASSWORD, user.getPassword()); // get title

        // 3. insert
        db.insert(TABLE_USER, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public User getUser(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_USER, // a. table
                        USER_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build user object
        User user = new User();
        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setName(cursor.getString(1));
        user.setUsername(cursor.getString(2));
        user.setPassword(cursor.getString(3));

        Log.d("getUser(" + id + ")", user.toString());

        // 5. return user
        return user;
    }

    public User getUser(String username){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_USER, // a. table
                        USER_COLUMNS, // b. column names
                        " username = ?", // c. selections
                        new String[] { String.valueOf(username) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build user object

        if (cursor.getCount() > 0) {
            User user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            Log.d("getUser(" + username + ")", user.toString());
            return user;
        }
        else {
            Log.d("getUser(" + username + ")", "null");
            return null;
        }
        // 5. return user
    }

    // Get All users
    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_USER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build user and add it to list
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                // Add user to users
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());

        // return users
        return users;
    }

    // Updating single user
    public int updateUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", user.getName()); // get title
        values.put("username", user.getUsername()); // get author
        values.put("password", user.getPassword());

        // 3. updating row
        int i = db.update(TABLE_USER, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(user.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single user
    public void deleteUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_USER,
                KEY_ID+" = ?",
                new String[] { String.valueOf(user.getId()) });

        // 3. close
        db.close();

        Log.d("deleteUser", user.toString());
    }

    // TODO: ...

    private static final String TABLE_EXERCISE = "exercise";

    // users Table Columns names
    private static final String KEY_ID_EXERCISE = "id";
    private static final String KEY_NAME_EXERCISE = "name";
    private static final String KEY_EXERCISE_INSTRUCTIONS = "instruction";

    private static final String[] EXERCISE_COLUMNS = {KEY_ID_EXERCISE, KEY_NAME_EXERCISE,KEY_EXERCISE_INSTRUCTIONS };

    public void addExdercise(Exercise exercise){
        Log.d("addExercise", exercise.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_NAME_EXERCISE, exercise.getName()); // get title
        //values.put(KEY_EXERCISE_INSTRUCTIONS, exercise.getExerciseInstruction()); // get title


        // 3. insert
        db.insert(TABLE_EXERCISE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }
/*
    // users table name
    private static final String TABLE_CAR = "car";

    // users Table Columns names
    private static final String KEY_COMPANY = "company";
    private static final String KEY_MODEL = "model";
    private static final String KEY_YEAR = "year";
    private static final String KEY_ENGINE = "engine";
    private static final String KEY_HORSE_POWER = "horse_power";
    private static final String KEY_CONDITION = "condition";
    private static final String KEY_PHONE = "phone";

    private static final String[] CAR_COLUMNS = {KEY_ID,KEY_COMPANY,KEY_MODEL,KEY_YEAR, KEY_ENGINE, KEY_HORSE_POWER, KEY_CONDITION, KEY_PHONE};

    public void addCar(Car car){
        Log.d("addCar", car.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY, car.getCompany()); // get title
        values.put(KEY_MODEL, car.getModel()); // get title
        values.put(KEY_YEAR, car.getYear());
        values.put(KEY_ENGINE,car.getEngine());
        values.put(KEY_HORSE_POWER,car.getHorsePower());
        values.put(KEY_CONDITION,car.getCondition());
        values.put(KEY_PHONE,car.getPhone());

        // get title

        // 3. insert
        db.insert(TABLE_CAR, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    // Get All cars
    public List<Car> getAllCars() {
        List<Car> cars = new LinkedList<Car>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CAR;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build user and add it to list
        Car car = null;
        if (cursor.moveToFirst()) {
            do {
                car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setCompany(cursor.getString(1));
                car.setModel(cursor.getString(2));
                car.setYear(cursor.getString(3));
                car.setEngine(cursor.getDouble(4));
                car.setHorsePower(cursor.getInt(5));
                car.setCondition(cursor.getString(6));
                car.setPhone(cursor.getString(7));

                // Add user to users
                cars.add(car);
            } while (cursor.moveToNext());
        }

        Log.d("getAllCars()", cars.toString());

        // return users
        return cars;
    }*/
}