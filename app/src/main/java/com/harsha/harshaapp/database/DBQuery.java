package com.harsha.harshaapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jeevani on 12/14/2016.
 */
public class DBQuery extends SQLiteOpenHelper {

    // LOG Tag
    private static final String TAG = "DBHandler";

    // Database Version. This Should be increment after every changes in database.
    private static final int DATABASE_VERSION = 1;

    // Database name. This should end with .db extension
    private static final String DATABASE_NAME = "harsha.db";

    // Table Names
    public static final String OCCUPATION = "occupation";
    public static final String SOCIAL_CATEGORY = "socialCategory";
    public static final String RELATIONSHIP = "relationship";
    public static final String DISABILITIES = "disabilities";
    public static final String EDUCATION = "education";
    public static final String EDUCATION_STATUS = "educationStatus";
    public static final String RELIGION = "religion";
    public static final String SCHEME = "scheme";
    public static final String MARITAL_STATUS = "maritalStatus";
    public static final String ASSET = "asset";

    //Attributes of OCCUPATION
    public static final String OCCUPATION_ID = "occupationId";
    public static final String OCCUPATION_CODE = "occupationCode";
    public static final String OCCUPATION_NAME = "occupationName";

    //Attributes of SOCIAL_CATEGORY
    public static final String SOCIAL_CATEGORY_ID = "socialCategoryId";
    public static final String SOCIAL_CATEGORY_CODE = "socialCategoryCode";
    public static final String SOCIAL_CATEGORY_NAME = "socialCategoryName";

    //Attributes of RELATIONSHIP
    public static final String RELATIONSHIP_ID = "relationshipId";
    public static final String RELATIONSHIP_Code = "relationshipCode";
    public static final String RELATIONSHIP_NAME = "relationshipName";

    //Attributes of DISABILITIES
    public static final String DISABILTIES_ID = "disabilitiesId";
    public static final String DISABILTIES_CODE = "disabilitiesCode";
    public static final String DISABILTIES_NAME = "disabilitiesName";

    //Attributes of EDUCATION
    public static final String EDUCATION_ID = "educationId";
    public static final String EDUCATION_CODE = "educationCode";
    public static final String EDUCATION_NAME = "educationName";

    //Attributes of EDUCATION_STATUS
    public static final String EDUCATION_STATUS_ID = "educationStatusId";
    public static final String EDUCATION_STATUS_CODE = "educationStatusCode";
    public static final String EDUCATION_STATUS_NAME = "educationStatusName";

    //Attributes of RELIGION
    public static final String RELIGION_ID = "religionId";
    public static final String RELIGION_CODE = "religionCode";
    public static final String RELIGION_NAME = "religionName";

    //Attributes of SCHEME
    public static final String SCHEME_ID = "schemeId";
    public static final String SCHEME_NAME = "schemeName";

    //Attributes of MARITAL_STATUS
    public static final String MARITAL_STATUS_ID = "martialStatusId";
    public static final String MARITAL_STATUS_CODE = "martialStatusCode";
    public static final String MARTIAL_STATUS_NAME = "martialStatusName";

    //Attributes of ASSET
    public static final String ASSET_ID = "assetId";
    public static final String ASSET_CODE = "assetCode";
    public static final String ASSET_NAME = "assetName";

    // create table for OCCUPATION
    public static final String CREATE_OCCUPATION = "CREATE TABLE " + OCCUPATION + " (" +
            OCCUPATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OCCUPATION_CODE + " TEXT NOT NULL, " +
            OCCUPATION_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for SOCIAL_CATEGORY
    public static final String CREATE_SOCIAL_CATEGORY = "CREATE TABLE " + SOCIAL_CATEGORY + " (" +
            SOCIAL_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SOCIAL_CATEGORY_CODE + " TEXT NOT NULL, " +
            SOCIAL_CATEGORY_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for RELATIONSHIP
    public static final String CREATE_RELATIONSHIP = "CREATE TABLE " + RELATIONSHIP + " (" +
            RELATIONSHIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RELATIONSHIP_Code + " TEXT NOT NULL, " +
            RELATIONSHIP_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for DIABILITIES
    public static final String CREATE_DISABILITIES = "CREATE TABLE " + DISABILITIES + " (" +
            DISABILTIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DISABILTIES_CODE + " TEXT NOT NULL, " +
            DISABILTIES_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for EDUCATION
    public static final String CREATE_EDUCATION = "CREATE TABLE " + EDUCATION + " (" +
            EDUCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EDUCATION_CODE + " TEXT NOT NULL, " +
            EDUCATION_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for EDUCATION_STATUS
    public static final String CREATE_EDUCATION_STATUS = "CREATE TABLE " + EDUCATION_STATUS + " (" +
            EDUCATION_STATUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EDUCATION_STATUS_CODE + " TEXT NOT NULL, " +
            EDUCATION_STATUS_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for RELIGION
    public static final String CREATE_RELIGION = "CREATE TABLE " + RELIGION + " (" +
            RELIGION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RELIGION_CODE + " TEXT NOT NULL, " +
            RELIGION_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for SCHEME
    public static final String CREATE_SCHEME = "CREATE TABLE " + SCHEME+ " (" +
            SCHEME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SCHEME_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for MARITAL_STATUS
    public static final String CREATE_MARITAL_STATUS = "CREATE TABLE " + MARITAL_STATUS + " (" +
            MARITAL_STATUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MARITAL_STATUS_CODE + " TEXT NOT NULL, " +
            MARTIAL_STATUS_NAME + " TEXT NOT NULL, " +
            ");";

    // create table for ASSET
    public static final String CREATE_ASSET = "CREATE TABLE " + ASSET + " (" +
            ASSET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ASSET_CODE + " TEXT NOT NULL, " +
            ASSET_NAME + " TEXT NOT NULL, " +
            ");";


    public DBQuery(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating Table......");

        db.execSQL(CREATE_OCCUPATION);
        Log.d(TAG, "OCCUPATION TABLE CREATED");

        db.execSQL(CREATE_SOCIAL_CATEGORY);
        Log.d(TAG, "SOCIAL_CATEGORY TABLE CREATED");

        db.execSQL(CREATE_RELATIONSHIP);
        Log.d(TAG, "RELATIONSHIP TABLE CREATED");

        db.execSQL(CREATE_DISABILITIES);
        Log.d(TAG, "DISABILITIES TABLE CREATED");

        db.execSQL(CREATE_EDUCATION);
        Log.d(TAG, "EDUCATION TABLE CREATED");

        db.execSQL(CREATE_EDUCATION_STATUS);
        Log.d(TAG, "EDUCATION_STATUS TABLE CREATED");

        db.execSQL(CREATE_RELIGION);
        Log.d(TAG, "RELIGION TABLE CREATED");

        db.execSQL(CREATE_SCHEME);
        Log.d(TAG, "SCHEME TABLE CREATED");

        db.execSQL(CREATE_MARITAL_STATUS);
        Log.d(TAG, "MARITAL_STATAUS TABLE CREATED");

        db.execSQL(CREATE_ASSET);
        Log.d(TAG, "ASSET TABLE CREATED");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + OCCUPATION);
        Log.d(TAG, "OCCUPATION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + SOCIAL_CATEGORY);
        Log.d(TAG, "SOCIAL_CATEGORY TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + RELATIONSHIP);
        Log.d(TAG, "RELATIONSHIP TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + DISABILITIES);
        Log.d(TAG, "DISABILITIES TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + EDUCATION);
        Log.d(TAG, "EDUCATION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + EDUCATION_STATUS);
        Log.d(TAG, "EDUCATION_STATUS TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + RELIGION);
        Log.d(TAG, "RELIGION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + SCHEME);
        Log.d(TAG, "SCHEME TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + MARITAL_STATUS);
        Log.d(TAG, "MARITAL_STATUS TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + ASSET);
        Log.d(TAG, "ASSET TABLE DELETED");

    }
}
