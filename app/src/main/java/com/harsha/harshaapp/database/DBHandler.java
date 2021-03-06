package com.harsha.harshaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.harsha.harshaapp.bean.Asset;
import com.harsha.harshaapp.bean.BaselineHeadInfo;
import com.harsha.harshaapp.bean.BaselineHeadInfo2;
import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.Block;
import com.harsha.harshaapp.bean.Disabilities;
import com.harsha.harshaapp.bean.District;
import com.harsha.harshaapp.bean.Education;
import com.harsha.harshaapp.bean.EducationStatus;
import com.harsha.harshaapp.bean.ImpactInfo;
import com.harsha.harshaapp.bean.MaritalStatus;
import com.harsha.harshaapp.bean.MemberInfo;
import com.harsha.harshaapp.bean.MigrationReason;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Project;
import com.harsha.harshaapp.bean.Relationship;
import com.harsha.harshaapp.bean.Religion;
import com.harsha.harshaapp.bean.Scheme;
import com.harsha.harshaapp.bean.SocialCategory;
import com.harsha.harshaapp.bean.State;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.bean.Village;

import java.util.ArrayList;

/**
 * Created by Jeevani on 12/8/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    // LOG Tag
    private static final String TAG = "DBHandler";

    // Database Version. This Should be increment after every changes in database.
    private static final int DATABASE_VERSION = 1;

    // Database name. This should end with .db extension
    private static final String DATABASE_NAME = "harsha.db";

    // Table Names
    public static final String TABLE_USER = "user";
    public static final String TABLE_STATE = "state";
    public static final String TABLE_DISTRICT = "district";
    public static final String TABLE_BLOCK = "block";
    public static final String TABLE_VILLAGE = "village";
    public static final String TABLE_BASELINE_INFORMATION = "baselineInformation";
    public static final String TABLE_MEMBER = "member";
    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_BASELINE_MEMBER = "baselineMember";
    public static final String TABLE_IMPACT_INFORMATION = "impactInformation";
    public static final String OCCUPATION = "occupation";
    public static final String SOCIAL_CATEGORY = "socialCategory";
    public static final String RELIGION = "religion";
    public static final String EDUCATION = "education";
    public static final String EDUCATION_STATUS = "educationStatus";
    public static final String SCHEME = "scheme";
    public static final String RELATIONSHIP = "relationship";
    public static final String DISABILITIES = "disabilities";
    public static final String MARITAL_STATUS = "maritalStatus";
    public static final String ASSET = "asset";
    public static final String MIGRATION_REASON = "migrationReason";

    // Attributes of USER Table
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String USER_PASSWORD = "password";
    public static final String USER_LAST_ACTIVITY = "lastActivity";
    public static final String USER_PROFILE_ID = "profileId";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_ADDRESS = "address";
    public static final String USER_PHOTO = "photo";
    public static final String USER_ROLE_ID = "roleId";
    public static final String USER_ROLE_NAME = "roleName";
    public static final String USER_STATUS_CODE = "statusCode";

    //Attributes of DISABILITIES
    public static final String DISABILTIES_ID = "disabilitiesId";
    public static final String DISABILTIES_CODE = "disabilitiesCode";
    public static final String DISABILTIES_NAME = "disabilitiesName";

    //Attributes of RELATIONSHIP
    public static final String RELATIONSHIP_ID = "relationshipId";
    public static final String RELATIONSHIP_Code = "relationshipCode";
    public static final String RELATIONSHIP_NAME = "relationshipName";



    //Attributes of OCCUPATION
    public static final String OCCUPATION_ID = "occupationId";
    public static final String OCCUPATION_CODE = "occupationCode";
    public static final String OCCUPATION_NAME = "occupationName";

    // Attributes of STATE Table
    public static final String STATE_ID = "stateId";
    public static final String STATE_CODE = "stateCode";
    public static final String STATE_NAME = "stateName";

    // Attributes of DISTRICT Table
    public static final String DISTRICT_ID = "districtId";
    public static final String DISTRICT_CODE = "districtCode";
    public static final String DISTRICT_NAME = "districtName";

    // Attributes of BLOCK Table
    public static final String BLOCK_ID = "blockId";
    public static final String BLOCK_CODE = "blockCode";
    public static final String BLOCK_NAME = "blockName";

    // Attributes of VILLAGE Table
    public static final String VILLAGE_ID = "villageId";
    public static final String VILLAGE_CODE = "villageCode";
    public static final String VILLAGE_NAME = "villageName";

    // Attributes of BASELINE INFORMATION Table
    public static final String BASELINE_ID = "baselineId";
    public static final String FAMILY_HEAD_ID = "familyHeadId";
    public static final String CONTACT_NO = "contactNo";
    public static final String FAMILY_MEMBER_NUMBER = "familyMemberNo";
    public static final String INCOME = "income";

    // Attributes of MEMBER Table
    public static final String MEMBER_ID = "memberId";
    public static final String UNIQUE_ID = "uniqueId";
    public static final String MEMBER_NAME = "name";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String QUOTA = "quota";
    public static final String AADHAAR_CARD_ID = "aadhaarCardId";
    public static final String VOTER_ID = "voterId";
    public static final String FAMILY_HEAD = "familyHead";
    public static final String PERSONAL_SALARY = "personalSalary";

    //Attributes of MARITAL_STATUS
    public static final String MARITAL_STATUS_ID = "martialStatusId";
    public static final String MARITAL_STATUS_CODE = "martialStatusCode";
    public static final String MARTIAL_STATUS_NAME = "martialStatusName";

    //Attributes of ASSET
    public static final String ASSET_ID = "assetId";
    public static final String ASSET_CODE = "assetCode";
    public static final String ASSET_NAME = "assetName";

    // Attributes of PROJECT Table
    public static final String PROJECT_ID = "projectId";
    public static final String PROJECT_NAME = "projectName";
    public static final String DONOR_NAME = "donorName";

    // Attributes of BASELINE_MEMBER Table
    public static final String BASELINE_MEMBER_ID = "baselineMemberId";

    // Attributes of IMPACT_INFORMATION Table
    public static final String IMPACT_ID = "impactId";
    public static final String LAND = "land";
    public static final String PLANT_NUMBER = "plantNo";
    public static final String PLANTING_YEAR = "plantingYear";
    public static final String HERR = "herr";
    public static final String IMPACT_INCOME = "memberIncome";
    public static final String BENEFICIARY_NAME = "beneficiaryName";
    public static final String HUSBAND_WIFE_NAME = "husbandWifeName";

    //Attributes of SOCIAL_CATEGORY
    public static final String SOCIAL_CATEGORY_ID = "socialCategoryId";
    public static final String SOCIAL_CATEGORY_CODE = "socialCategoryCode";
    public static final String SOCIAL_CATEGORY_NAME = "socialCategoryName";

    //Attributes of RELIGION
    public static final String RELIGION_ID = "religionId";
    public static final String RELIGION_CODE = "religionCode";
    public static final String RELIGION_NAME = "religionName";

    //Attributes of EDUCATION
    public static final String EDUCATION_ID = "educationId";
    public static final String EDUCATION_CODE = "educationCode";
    public static final String EDUCATION_NAME = "educationName";

    //Attributes of EDUCATION_STATUS
    public static final String EDUCATION_STATUS_ID = "educationStatusId";
    public static final String EDUCATION_STATUS_CODE = "educationStatusCode";
    public static final String EDUCATION_STATUS_NAME = "educationStatusName";

    //Attributes of SCHEME
    public static final String SCHEME_ID = "schemeId";
    public static final String SCHEME_NAME = "schemeName";

    //Attributes of MIGRATION_REASON
    public static final String MIGRATION_REASON_ID = "migrationReasonId";
    public static final String MIGRATION_REASON_CODE = "migrationReasonCode";
    public static final String MIGRATION_REASON_NAME = "migrationReasonName";

    // create table for ASSET
    public static final String CREATE_ASSET = "CREATE TABLE " + ASSET + " (" +
            ASSET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ASSET_CODE + " TEXT NOT NULL, " +
            ASSET_NAME + " TEXT NOT NULL " +
            ");";

    // create table for DIABILITIES
    public static final String CREATE_DISABILITIES = "CREATE TABLE " + DISABILITIES + " (" +
            DISABILTIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DISABILTIES_CODE + " TEXT NOT NULL, " +
            DISABILTIES_NAME + " TEXT NOT NULL " +
            ");";

    // create table for RELATIONSHIP
    public static final String CREATE_RELATIONSHIP = "CREATE TABLE " + RELATIONSHIP + " (" +
            RELATIONSHIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RELATIONSHIP_Code + " TEXT NOT NULL, " +
            RELATIONSHIP_NAME + " TEXT NOT NULL" +
            ");";

    // Create Table for USER
    public static final String CREATE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_NAME + " TEXT NOT NULL, " +
            USER_PASSWORD + " TEXT NOT NULL, " +
            USER_LAST_ACTIVITY + " TEXT NOT NULL, " +
            USER_PROFILE_ID + " INTEGER NOT NULL, " +
            USER_FIRST_NAME + " TEXT NOT NULL, " +
            USER_LAST_NAME + " TEXT NOT NULL, " +
            USER_EMAIL + " TEXT NOT NULL, " +
            USER_PHONE + " INTEGER NOT NULL, " +
            USER_ADDRESS + " TEXT NOT NULL, " +
            USER_PHOTO + " TEXT NOT NULL, " +
            USER_ROLE_ID + " INTEGER NOT NULL, " +
            USER_ROLE_NAME + " TEXT NOT NULL, " +
            USER_STATUS_CODE + " TEXT NOT NULL " +
            ");";

    // Create Table for STATE
    public static final String CREATE_STATE = "CREATE TABLE " + TABLE_STATE + " (" +
            STATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STATE_CODE + " TEXT NOT NULL, " +
            STATE_NAME + " TEXT NOT NULL " +
            ");";

    // Create Table for DISTRICT
    public static final String CREATE_DISTRICT = "CREATE TABLE " + TABLE_DISTRICT + " (" +
            DISTRICT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DISTRICT_CODE + " TEXT NOT NULL, " +
            DISTRICT_NAME + " TEXT NOT NULL, " +
            STATE_ID + " INTEGER NOT NULL " +
            ");";

    // Create Table for BLOCK
    public static final String CREATE_BLOCK = "CREATE TABLE " + TABLE_BLOCK + " (" +
            BLOCK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BLOCK_CODE + " TEXT NOT NULL, " +
            BLOCK_NAME + " TEXT NOT NULL, " +
            DISTRICT_ID + " INTEGER NOT NULL " +
            ");";

    // Create Table for VILLAGE
    public static final String CREATE_VILLAGE = "CREATE TABLE " + TABLE_VILLAGE + " (" +
            VILLAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            VILLAGE_CODE + " TEXT NOT NULL, " +
            VILLAGE_NAME + " TEXT NOT NULL, " +
            BLOCK_ID + " INTEGER NOT NULL " +
            ");";


    // Create Table for BASELINE_INFORMATION
    public static final String CREATE_BASELINE_INFORMATION = "CREATE TABLE " + TABLE_BASELINE_INFORMATION + " (" +
            BASELINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FAMILY_HEAD_ID + " INTEGER NOT NULL, " +
            STATE_ID + " INTEGER NOT NULL, " +
            DISTRICT_ID + " INTEGER NOT NULL, " +
            BLOCK_ID + " INTEGER NOT NULL, " +
            VILLAGE_ID + " INTEGER NOT NULL, " +
            SOCIAL_CATEGORY_ID + " INTEGER NOT NULL, " +
            RELIGION_ID + " INTEGER NOT NULL, " +
            OCCUPATION_ID + " INTEGER NOT NULL, " +
            USER_ID + " INTEGER NOT NULL, " +
            CONTACT_NO + " TEXT NOT NULL, " +
            FAMILY_MEMBER_NUMBER + " INTEGER NOT NULL, " +
            INCOME + " TEXT NOT NULL " +
            ");";

    // Create Table for MEMBER
    public static final String CREATE_MEMBER = "CREATE TABLE " + TABLE_MEMBER + " (" +
            MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UNIQUE_ID + " INTEGER, " +
            MEMBER_NAME + " TEXT NOT NULL, " +
            DOB + " TEXT NOT NULL, " +
            GENDER + " TEXT NOT NULL, " +
            QUOTA + " TEXT NOT NULL, " +
            AADHAAR_CARD_ID + " INTEGER NOT NULL, " +
            VOTER_ID + " TEXT NOT NULL, " +
            FAMILY_HEAD + " TEXT, " +
            PERSONAL_SALARY + " INTEGER NOT NULL, " +
            OCCUPATION_ID + " INTEGER NOT NULL, " +
            DISABILTIES_ID + " INTEGER NOT NULL, " +
            RELATIONSHIP_ID + " INTEGER NOT NULL, " +
            EDUCATION_ID + " INTEGER NOT NULL, " +
            EDUCATION_STATUS_ID + " INTEGER NOT NULL, " +
            MARITAL_STATUS_ID + " INTEGER NOT NULL, " +
            RELIGION_ID + " INTEGER NOT NULL, " +
            SCHEME_ID + " INTEGER NOT NULL " +
            ");";

    // Create Table for PROJECT
    public static final String CREATE_PROJECT = "CREATE TABLE " + TABLE_PROJECT + " (" +
            PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROJECT_NAME + " TEXT NOT NULL," +
            DONOR_NAME + " TEXT NOT NULL " +
            ");";

    // Create Table for BASELINE_MEMBER
    public static final String CREATE_BASELINE_MEMBER = "CREATE TABLE " + TABLE_BASELINE_MEMBER + " (" +
            BASELINE_MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            ");";

    // Create Table for IMPACT_INFORMATION
    public static final String CREATE_IMPACT_INFORMATION = "CREATE TABLE " + TABLE_IMPACT_INFORMATION + " (" +
            IMPACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FAMILY_HEAD_ID + " INTEGER NOT NULL, " +
            LAND + " TEXT NOT NULL, " +
            BENEFICIARY_NAME + " TEXT NOT NULL, " +
            HUSBAND_WIFE_NAME + " TEXT NOT NULL, " +
            PLANT_NUMBER + " INTEGER NOT NULL, " +
            PLANTING_YEAR + " INTEGER NOT NULL, " +
            HERR + " INTEGER NOT NULL, " +
            IMPACT_INCOME + " INTEGER NOT NULL, " +
            STATE_ID + " INTEGER NOT NULL, " +
            DISTRICT_ID + " INTEGER NOT NULL, " +
            VILLAGE_ID + " INTEGER NOT NULL, " +
            PROJECT_ID + " INTEGER NOT NULL, " +
            BASELINE_ID + " INTEGER NOT NULL " +
            ");";

    // create table for OCCUPATION
    public static final String CREATE_OCCUPATION = "CREATE TABLE " + OCCUPATION + " (" +
            OCCUPATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OCCUPATION_CODE + " TEXT NOT NULL, " +
            OCCUPATION_NAME + " TEXT NOT NULL " +
            ");";

    // create table for SOCIAL_CATEGORY
    public static final String CREATE_SOCIAL_CATEGORY = "CREATE TABLE " + SOCIAL_CATEGORY + " (" +
            SOCIAL_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SOCIAL_CATEGORY_CODE + " TEXT NOT NULL, " +
            SOCIAL_CATEGORY_NAME + " TEXT NOT NULL " +
            ");";

    // create table for EDUCATION
    public static final String CREATE_EDUCATION = "CREATE TABLE " + EDUCATION + " (" +
            EDUCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EDUCATION_CODE + " TEXT NOT NULL, " +
            EDUCATION_NAME + " TEXT NOT NULL " +
            ");";

    // create table for EDUCATION_STATUS
    public static final String CREATE_EDUCATION_STATUS = "CREATE TABLE " + EDUCATION_STATUS + " (" +
            EDUCATION_STATUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EDUCATION_STATUS_CODE + " TEXT NOT NULL, " +
            EDUCATION_STATUS_NAME + " TEXT NOT NULL " +
            ");";

    // create table for RELIGION
    public static final String CREATE_RELIGION = "CREATE TABLE " + RELIGION + " (" +
            RELIGION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RELIGION_CODE + " TEXT NOT NULL, " +
            RELIGION_NAME + " TEXT NOT NULL " +
            ");";

    // create table for SCHEME
    public static final String CREATE_SCHEME = "CREATE TABLE " + SCHEME+ " (" +
            SCHEME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SCHEME_NAME + " TEXT NOT NULL " +
            ");";

    // create table for MARITAL_STATUS
    public static final String CREATE_MARITAL_STATUS = "CREATE TABLE " + MARITAL_STATUS + " (" +
            MARITAL_STATUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MARITAL_STATUS_CODE + " TEXT NOT NULL, " +
            MARTIAL_STATUS_NAME + " TEXT NOT NULL " +
            ");";

    // create table for MIGRATION_REASON
    public static final String CREATE_MIGRATION_REASON = "CREATE TABLE " + MIGRATION_REASON + " (" +
            MIGRATION_REASON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MIGRATION_REASON_CODE + " TEXT NOT NULL, " +
            MIGRATION_REASON_NAME + " TEXT NOT NULL " +
            ");";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "Creating Table......");

        db.execSQL(CREATE_USER);
        Log.d(TAG, "USER TABLE CREATED");

        db.execSQL(CREATE_STATE);
        Log.d(TAG, "STATE TABLE CREATED");

        db.execSQL(CREATE_DISTRICT);
        Log.d(TAG, "DISTRICT TABLE CREATED");

        db.execSQL(CREATE_BLOCK);
        Log.d(TAG, "BLOCK TABLE CREATED");

        db.execSQL(CREATE_VILLAGE);
        Log.d(TAG, "VILLAGE TABLE CREATED");

        db.execSQL(CREATE_BASELINE_INFORMATION);
        Log.d(TAG, "BASELINE_INFORMATION TABLE CREATED");

        db.execSQL(CREATE_MEMBER);
        Log.d(TAG, "MEMBER TABLE CREATED");

        db.execSQL(CREATE_PROJECT);
        Log.d(TAG, "PROJECT TABLE CREATED");

        db.execSQL(CREATE_BASELINE_MEMBER);
        Log.d(TAG, "BASELINE_MEMBER TABLE CREATED");

        db.execSQL(CREATE_IMPACT_INFORMATION);
        Log.d(TAG, "IMPACT_INNFORMATION TABLE CREATED");

        db.execSQL(CREATE_OCCUPATION);
        Log.d(TAG, "OCCUPATION TABLE CREATED");

        db.execSQL(CREATE_SOCIAL_CATEGORY);
        Log.d(TAG, "SOCIAL_CATEGORY TABLE CREATED");

        db.execSQL(CREATE_RELIGION);
        Log.d(TAG, "RELIGION TABLE CREATED");

        db.execSQL(CREATE_EDUCATION);
        Log.d(TAG, "EDUCATION TABLE CREATED");

        db.execSQL(CREATE_EDUCATION_STATUS);
        Log.d(TAG, "EDUCATION_STATUS TABLE CREATED");

        db.execSQL(CREATE_RELATIONSHIP);
        Log.d(TAG, "RELATIONSHIP TABLE CREATED");

        db.execSQL(CREATE_MARITAL_STATUS);
        Log.d(TAG, "MARITAL_STATUS TABLE CREATED");

        db.execSQL(CREATE_SCHEME);
        Log.d(TAG, "SCHEME TABLE CREATED");

        db.execSQL(CREATE_DISABILITIES);
        Log.d(TAG, "DISABILITIES TABLE CREATED");

        db.execSQL(CREATE_ASSET);
        Log.d(TAG, "ASSET TABLE CREATED");

        db.execSQL(CREATE_MIGRATION_REASON);
        Log.d(TAG, "MIGRATION_REASON TABLE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_USER);
        Log.d(TAG, "TABLE_USER TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_STATE);
        Log.d(TAG, "TABLE_STATE TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_DISTRICT);
        Log.d(TAG, "TABLE_DISTRICT TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_BLOCK);
        Log.d(TAG, "TABLE_USER TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_VILLAGE);
        Log.d(TAG, "TABLE_VILLAGE TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_BASELINE_INFORMATION);
        Log.d(TAG, "TABLE_BASELINE_INFORMATION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_MEMBER);
        Log.d(TAG, "TABLE_MEMBER TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_PROJECT);
        Log.d(TAG, "TABLE_PROJECT TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_BASELINE_MEMBER);
        Log.d(TAG, "TABLE_BASELINE_MEMBER TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + TABLE_IMPACT_INFORMATION);
        Log.d(TAG, "TABLE_IMPACT_INFORMATION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + OCCUPATION);
        Log.d(TAG, "OCCUPATION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + SOCIAL_CATEGORY);
        Log.d(TAG, "SOCIAL_CATEGORY TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + RELIGION);
        Log.d(TAG, "RELIGION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + MARITAL_STATUS);
        Log.d(TAG, "MARITAL_STATUS TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + EDUCATION);
        Log.d(TAG, "EDUCATION TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + EDUCATION_STATUS);
        Log.d(TAG, "EDUCATION_STATUS TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + RELATIONSHIP);
        Log.d(TAG, "RELATIONSHIP TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + MARITAL_STATUS);
        Log.d(TAG, "RELATIONSHIP TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + SCHEME);
        Log.d(TAG, "SCHEME TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + ASSET);
        Log.d(TAG, "ASSET TABLE DELETED");

        db.execSQL("DROP TABLE IF EXIST " + MIGRATION_REASON);
        Log.d(TAG, "MIGRATION_REASON TABLE DELETED");

        onCreate(db);

    }

     /*CRUD OPERATIONS*/

    /*Login*/
    public boolean isUserLoggedIn() {

        SQLiteDatabase db = getWritableDatabase();

        boolean check = false;
        String query = "SELECT * FROM " + TABLE_USER + " ORDER BY " + USER_ID + " DESC LIMIT 1";

        //Cursor points to the results
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {

            check = true;

            c.moveToNext();

        }

        db.close();

        Log.d(TAG, "Row Fetched from TABLE_USER");

        return check;

    }

    public User getUserDetail() {

        SQLiteDatabase db = getWritableDatabase();

        User user = new User();

        String query = "SELECT * FROM " + TABLE_USER + " ORDER BY " + USER_ID + " DESC LIMIT 1";

        //Cursor points to the results
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {

            user.setUserId(c.getInt(c.getColumnIndex(USER_ID)));
            user.setUserName(c.getString(c.getColumnIndex(USER_NAME)));
            user.setFirstName(c.getString(c.getColumnIndex(USER_FIRST_NAME)));
            user.setLastName(c.getString(c.getColumnIndex(USER_LAST_NAME)));
            user.setEmail(c.getString(c.getColumnIndex(USER_EMAIL)));
            user.setPassword(c.getString(c.getColumnIndex(USER_PASSWORD)));
            user.setRoleId(c.getInt(c.getColumnIndex(USER_ROLE_ID)));
            user.setProfileId(c.getInt(c.getColumnIndex(USER_PROFILE_ID)));
            user.setRoleName(c.getString(c.getColumnIndex(USER_ROLE_NAME)));
            user.setLastActivity(c.getString(c.getColumnIndex(USER_LAST_ACTIVITY)));
            user.setAddress(c.getString(c.getColumnIndex(USER_ADDRESS)));
            user.setPhone(c.getString(c.getColumnIndex(USER_PHONE)));
            user.setPhoto(c.getString(c.getColumnIndex(USER_PHOTO)));
            user.setStatusCode((c.getString(c.getColumnIndex(USER_STATUS_CODE))));

            c.moveToNext();

        }

        db.close();

        Log.d(TAG, "Row Fetched from TABLE_USER");

        return user;

    }


    //insert user details
    public void insertUser(User user) {

        ContentValues values = new ContentValues();

        values.put(USER_ID, user.getUserId());
        values.put(USER_NAME, user.getUserName());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_LAST_ACTIVITY, user.getLastActivity());
        values.put(USER_PROFILE_ID,user.getProfileId());
        values.put(USER_FIRST_NAME,user.getFirstName());
        values.put(USER_LAST_NAME,user.getLastName());
        values.put(USER_EMAIL,user.getEmail());
        values.put(USER_PHONE,user.getPhone());
        values.put(USER_ADDRESS,user.getAddress());
        values.put(USER_PHOTO,user.getPhoto());
        values.put(USER_ROLE_ID,user.getRoleId());
        values.put(USER_ROLE_NAME,user.getRoleName());
        values.put(USER_STATUS_CODE,user.getStatusCode());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_USER, null, values);
        Log.d(TAG, "Row Inserted in TABLE_USER");

        db.close();

    }

    // DELETE Row from TABLE_USER
    public void deleteUser(User user) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + ";");
        Log.d(TAG, "Row Deleted in TABLE_USER");

    }

    //UPDATE Row from TABLE_USER
    public void updateUser(User user){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE "+ TABLE_USER + " SET " + USER_PASSWORD + "='" + user.getPassword() + "' WHERE "+ USER_ID+"="+user.getUserId()+ ";");
        Log.d(TAG, "Row Updated in TABLE_USER");

    }

    //INSERT ROW INTO TABLE_BASELINE_INFORMATION
    public void insertBaselineInformation(BaselineInfo baseInfo){

        ContentValues values = new ContentValues();

        //values.put(BASELINE_ID, baseInfo.getBaselineId());
        values.put(FAMILY_HEAD_ID, baseInfo.getFamilyHeadId());
        values.put(STATE_ID, baseInfo.getStateId());
        values.put(DISTRICT_ID, baseInfo.getDistrictId());
        values.put(BLOCK_ID, baseInfo.getBlockId());
        values.put(VILLAGE_ID, baseInfo.getVillageId());
        values.put(USER_ID, baseInfo.getSurveyUserId());
        values.put(SOCIAL_CATEGORY_ID, baseInfo.getSocialCategoryId());
        values.put(RELIGION_ID, baseInfo.getReligionId());
        values.put(OCCUPATION_ID, baseInfo.getOccupationId());
        values.put(CONTACT_NO, baseInfo.getContactNo());
        values.put(FAMILY_MEMBER_NUMBER, baseInfo.getFamilyMemberNumber());
        values.put(INCOME, baseInfo.getIncome());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_BASELINE_INFORMATION, null, values);
        Log.d(TAG, "Row Inserted in TABLE_BASELINE_INFORMATION");

        db.close();
    }

    //UPDATE Row from TABLE_BASELINE_INFORMATION
    public void updateBaselineInformation(BaselineInfo baseinfo){

        SQLiteDatabase db = getWritableDatabase();
        //String currentDate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());

        db.execSQL("UPDATE"+ TABLE_BASELINE_INFORMATION + "SET" + STATE_ID + "=\"" + baseinfo.getStateId() + "\"" +
                ""+DISTRICT_ID + "=\"" + baseinfo.getDistrictId() +"\" " + ""+BLOCK_ID + "=\"" + baseinfo.getBlockId()+ "\""+
                ""+VILLAGE_ID + "=\"" + baseinfo.getVillageId() +"\""+SOCIAL_CATEGORY_ID + "=\"" + baseinfo.getSocialCategoryId() +"\"" +
                ""+RELIGION_ID + "=\"" + baseinfo.getReligionId() +""+OCCUPATION_ID + "=\"" + baseinfo.getOccupationId() +"" +
                ""+CONTACT_NO + "=\"" + baseinfo.getContactNo() +"" + ""+FAMILY_MEMBER_NUMBER + "=\"" + baseinfo.getFamilyMemberNumber()+
                ""+INCOME + "=\"" + baseinfo.getIncome()+" WHERE "+USER_ID+ "=\"" + baseinfo.getSurveyUserId()+" ;");
        Log.d(TAG, "Row Deleted in TABLE_Baseline");

    }

    public int getRelationShipIdByName(String name) {

        String query = "SELECT * FROM " + RELATIONSHIP + " WHERE " + RELATIONSHIP_NAME + "='" + name +"' LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        int id = 0;
        //Cursor points to the results
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {

            id = c.getInt(c.getColumnIndex(RELATIONSHIP_NAME));

            c.moveToNext();

        }

        Log.d(TAG, "Row Fetched from Member table");

        return id;

    }

    public ArrayList<BaselineHeadInfo2> getAllBaselineHeadInformation2() {

        String query = "SELECT " +
                TABLE_BASELINE_INFORMATION + "." + BASELINE_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + FAMILY_HEAD_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + STATE_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + DISTRICT_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + BLOCK_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + VILLAGE_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + USER_ID + ", " +
                TABLE_BASELINE_INFORMATION + "." + CONTACT_NO + ", " +
                TABLE_BASELINE_INFORMATION + "." + FAMILY_MEMBER_NUMBER + ", " +
                TABLE_BASELINE_INFORMATION + "." + INCOME + ", " +
                TABLE_MEMBER + "." + MEMBER_ID + ", " +
                TABLE_MEMBER + "." + UNIQUE_ID + ", " +
                TABLE_MEMBER + "." + MEMBER_NAME + ", " +
                TABLE_MEMBER + "." + DOB + ", " +
                TABLE_MEMBER + "." + GENDER + ", " +
                TABLE_MEMBER + "." + QUOTA + ", " +
                TABLE_MEMBER + "." + AADHAAR_CARD_ID + ", " +
                TABLE_MEMBER + "." + VOTER_ID + ", " +
                TABLE_MEMBER + "." + FAMILY_HEAD + ", " +
                TABLE_MEMBER + "." + PERSONAL_SALARY + ", " +
                TABLE_MEMBER + "." + OCCUPATION_ID + ", " +
                TABLE_MEMBER + "." + DISABILTIES_ID + ", " +
                TABLE_MEMBER + "." + RELATIONSHIP_ID + ", " +
                TABLE_MEMBER + "." + EDUCATION_ID + ", " +
                TABLE_MEMBER + "." + EDUCATION_STATUS_ID + ", " +
                TABLE_MEMBER + "." + MARITAL_STATUS_ID + ", " +
                TABLE_MEMBER + "." + RELIGION_ID + ", " +
                TABLE_MEMBER + "." + SCHEME_ID + ", " +
                TABLE_STATE + "." + STATE_NAME + ", " +
                TABLE_STATE + "." + STATE_CODE + ", " +
                TABLE_DISTRICT + "." + DISTRICT_CODE + ", " +
                TABLE_DISTRICT + "." + DISTRICT_NAME + ", " +
                TABLE_BLOCK + "." + BLOCK_CODE + ", " +
                TABLE_BLOCK + "." + BLOCK_NAME + ", " +
                TABLE_VILLAGE + "." + VILLAGE_CODE + ", " +
                TABLE_VILLAGE + "." + VILLAGE_NAME + ", " +
                TABLE_USER + "." + USER_NAME + ", " +
                SOCIAL_CATEGORY + "." + SOCIAL_CATEGORY_NAME + ", " +
                OCCUPATION + "." + OCCUPATION_NAME + ", " +
                DISABILITIES + "." + DISABILTIES_NAME + ", " +
                RELATIONSHIP + "." + RELATIONSHIP_NAME + ", " +
                EDUCATION + "." + EDUCATION_NAME + ", " +
                EDUCATION_STATUS + "." + EDUCATION_STATUS_NAME + ", " +
                MARITAL_STATUS + "." + MARTIAL_STATUS_NAME + ", " +
                RELIGION + "." + RELIGION_NAME + ", " +
                SCHEME + "." + SCHEME_NAME + " " +
                " FROM " +
                TABLE_BASELINE_INFORMATION +
                " LEFT JOIN " + TABLE_MEMBER + " ON " + TABLE_BASELINE_INFORMATION + "." + FAMILY_HEAD_ID + "=" + TABLE_MEMBER + "." + MEMBER_ID +
                " LEFT JOIN " + TABLE_STATE + " ON " + TABLE_BASELINE_INFORMATION + "." + STATE_ID + "=" + TABLE_STATE + "." + STATE_ID +
                " LEFT JOIN " + TABLE_DISTRICT + " ON " + TABLE_BASELINE_INFORMATION + "." + DISTRICT_ID + "=" + TABLE_DISTRICT + "." + DISTRICT_ID +
                " LEFT JOIN " + TABLE_BLOCK + " ON " + TABLE_BASELINE_INFORMATION + "." + BLOCK_ID + "=" + TABLE_BLOCK + "." + BLOCK_ID +
                " LEFT JOIN " + TABLE_VILLAGE + " ON " + TABLE_BASELINE_INFORMATION + "." + VILLAGE_ID + "=" + TABLE_VILLAGE + "." + VILLAGE_ID +
                " LEFT JOIN " + TABLE_USER + " ON " + TABLE_BASELINE_INFORMATION + "." + USER_ID + "=" + TABLE_USER + "." + USER_ID +
                " LEFT JOIN " + SOCIAL_CATEGORY + " ON " + TABLE_MEMBER + "." + QUOTA + "=" + SOCIAL_CATEGORY + "." + SOCIAL_CATEGORY_ID +
                " LEFT JOIN " + OCCUPATION + " ON " + TABLE_MEMBER + "." + OCCUPATION_ID + "=" + OCCUPATION + "." + OCCUPATION_ID +
                " LEFT JOIN " + DISABILITIES + " ON " + TABLE_MEMBER + "." + DISABILTIES_ID + "=" + DISABILITIES + "." + DISABILTIES_ID +
                " LEFT JOIN " + RELATIONSHIP + " ON " + TABLE_MEMBER + "." + RELATIONSHIP_ID + "=" + RELATIONSHIP + "." + RELATIONSHIP_ID +
                " LEFT JOIN " + EDUCATION + " ON " + TABLE_MEMBER + "." + EDUCATION_ID + "=" + EDUCATION + "." + EDUCATION_ID +
                " LEFT JOIN " + EDUCATION_STATUS + " ON " + TABLE_MEMBER + "." + EDUCATION_STATUS_ID + "=" + EDUCATION_STATUS + "." + EDUCATION_STATUS_ID +
                " LEFT JOIN " + MARITAL_STATUS + " ON " + TABLE_MEMBER + "." + MARITAL_STATUS_ID + "=" + MARITAL_STATUS + "." + MARITAL_STATUS_ID +
                " LEFT JOIN " + RELIGION + " ON " + TABLE_MEMBER + "." + RELIGION_ID + "=" + RELIGION + "." + RELIGION_ID +
                " LEFT JOIN " + SCHEME + " ON " + TABLE_MEMBER + "." + SCHEME_ID + "=" + SCHEME + "." + SCHEME_ID +
                "";

        Log.d("QUERY:", "\n" +
                "\n" + query+"\n\n");

        ArrayList<BaselineHeadInfo2> baselineHeadInfo2 = new ArrayList<BaselineHeadInfo2>();

        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        /*
        String query = "";
        String qry = "SELECT * FROM "+TABLE_BASELINE_INFORMATION+ " ORDER BY "+ BASELINE_ID + " ASC" ;*/
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                BaselineHeadInfo2 baseHead = new BaselineHeadInfo2();

                baseHead.baselineId = cursor.getInt(cursor.getColumnIndex(BASELINE_ID));
                baseHead.familyHeadId = cursor.getInt(cursor.getColumnIndex(FAMILY_HEAD_ID));
                baseHead.stateId = cursor.getInt(cursor.getColumnIndex(STATE_ID));
                baseHead.districtId = cursor.getInt(cursor.getColumnIndex(DISTRICT_ID));
                baseHead.blockId = cursor.getInt(cursor.getColumnIndex(BLOCK_ID));
                baseHead.villageId = cursor.getInt(cursor.getColumnIndex(VILLAGE_ID));
                baseHead.surveyUserId = cursor.getInt(cursor.getColumnIndex(USER_ID));
                baseHead.contactNo = cursor.getString(cursor.getColumnIndex(CONTACT_NO));
                baseHead.familyMemberNumber = cursor.getInt(cursor.getColumnIndex(FAMILY_MEMBER_NUMBER));
                baseHead.income = cursor.getString(cursor.getColumnIndex(INCOME));

                baseHead.memberId = cursor.getInt(cursor.getColumnIndex(MEMBER_ID));
                baseHead.uniqueId = cursor.getInt(cursor.getColumnIndex(UNIQUE_ID));
                baseHead.memberName = cursor.getString(cursor.getColumnIndex(MEMBER_NAME));
                baseHead.dob = cursor.getString(cursor.getColumnIndex(DOB));
                baseHead.gender = cursor.getString(cursor.getColumnIndex(GENDER));
                baseHead.socialCategoryId = cursor.getInt(cursor.getColumnIndex(QUOTA));
                baseHead.aadhaarCardId = cursor.getString(cursor.getColumnIndex(AADHAAR_CARD_ID));
                baseHead.voterId = cursor.getString(cursor.getColumnIndex(VOTER_ID));
                baseHead.familyHead = cursor.getString(cursor.getColumnIndex(FAMILY_HEAD));
                baseHead.personalSalary = cursor.getString(cursor.getColumnIndex(PERSONAL_SALARY));
                baseHead.occupationId = cursor.getInt(cursor.getColumnIndex(OCCUPATION_ID));
                baseHead.disabilitiesId = cursor.getInt(cursor.getColumnIndex(DISABILTIES_ID));
                baseHead.relationshipId = cursor.getInt(cursor.getColumnIndex(RELATIONSHIP_ID));
                baseHead.educationId = cursor.getInt(cursor.getColumnIndex(EDUCATION_ID));
                baseHead.educationStatusId = cursor.getInt(cursor.getColumnIndex(EDUCATION_STATUS_ID));
                baseHead.maritalStatusId =  cursor.getInt(cursor.getColumnIndex(MARITAL_STATUS_ID));
                baseHead.religionId = cursor.getInt(cursor.getColumnIndex(RELIGION_ID));
                baseHead.schemeId = cursor.getInt(cursor.getColumnIndex(SCHEME_ID));

                baseHead.stateCode = cursor.getString(cursor.getColumnIndex(STATE_CODE));
                baseHead.stateName = cursor.getString(cursor.getColumnIndex(STATE_NAME));
                baseHead.districtCode = cursor.getString(cursor.getColumnIndex(DISTRICT_CODE));
                baseHead.districtName = cursor.getString(cursor.getColumnIndex(DISTRICT_NAME));
                baseHead.blockCode = cursor.getString(cursor.getColumnIndex(BLOCK_CODE));
                baseHead.blockName = cursor.getString(cursor.getColumnIndex(BLOCK_NAME));
                baseHead.villageCode = cursor.getString(cursor.getColumnIndex(VILLAGE_CODE));
                baseHead.villageName = cursor.getString(cursor.getColumnIndex(VILLAGE_NAME));

                baseHead.userName = cursor.getString(cursor.getColumnIndex(USER_NAME));

                baseHead.socialCategoryName = cursor.getString(cursor.getColumnIndex(SOCIAL_CATEGORY_NAME));
                baseHead.occupationName = cursor.getString(cursor.getColumnIndex(OCCUPATION_NAME));
                baseHead.disabilitiesName = cursor.getString(cursor.getColumnIndex(DISABILTIES_NAME));
                baseHead.relationshipName = cursor.getString(cursor.getColumnIndex(RELATIONSHIP_NAME));
                baseHead.educationName = cursor.getString(cursor.getColumnIndex(EDUCATION_NAME));
                baseHead.educationStatusName = cursor.getString(cursor.getColumnIndex(EDUCATION_STATUS_NAME));
                baseHead.maritalStatusName = cursor.getString(cursor.getColumnIndex(MARTIAL_STATUS_NAME));
                baseHead.religionName = cursor.getString(cursor.getColumnIndex(RELIGION_NAME));
                baseHead.schemeName = cursor.getString(cursor.getColumnIndex(SCHEME_NAME));

                Log.d("Fetch Member Data:","\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\nDONE\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n");

                Log.d("baselineId:", "" + baseHead.baselineId);

                baselineHeadInfo2.add(baseHead);

            }
        }

        return baselineHeadInfo2;

    }

    // fetch form basline information table and member information table
    public ArrayList<BaselineHeadInfo> getAllBaselineHeadInformation() {

        ArrayList<BaselineHeadInfo> baselineHeadInfo = new ArrayList<BaselineHeadInfo>();

        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String query = "";
        String qry = "SELECT * FROM "+TABLE_BASELINE_INFORMATION+ " ORDER BY "+ BASELINE_ID + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                BaselineHeadInfo baseHead = new BaselineHeadInfo();
                BaselineInfo baselineInfo = new BaselineInfo();
                baselineInfo.setBaselineId(cursor.getInt(cursor.getColumnIndex(BASELINE_ID)));
                baselineInfo.setFamilyHeadId(cursor.getInt(cursor.getColumnIndex(FAMILY_HEAD_ID)));
                baselineInfo.setStateId(cursor.getInt(cursor.getColumnIndex(STATE_ID)));
                baselineInfo.setDistrictId(cursor.getInt(cursor.getColumnIndex(DISTRICT_ID)));
                baselineInfo.setBlockId(cursor.getInt(cursor.getColumnIndex(BLOCK_ID)));
                baselineInfo.setVillageId(cursor.getInt(cursor.getColumnIndex(VILLAGE_ID)));
                baselineInfo.setSurveyUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
                baselineInfo.setSocialCategoryId(cursor.getInt(cursor.getColumnIndex(SOCIAL_CATEGORY_ID)));
                baselineInfo.setReligionId(cursor.getInt(cursor.getColumnIndex(RELIGION_ID)));
                baselineInfo.setOccupationId(cursor.getInt(cursor.getColumnIndex(OCCUPATION_ID)));
                baselineInfo.setContactNo(cursor.getString(cursor.getColumnIndex(CONTACT_NO)));
                baselineInfo.setFamilyMemberNumber(cursor.getInt(cursor.getColumnIndex(FAMILY_MEMBER_NUMBER)));
                baselineInfo.setIncome(cursor.getString(cursor.getColumnIndex(INCOME)));

                baseHead.setBaselineInfo(baselineInfo);

                query = "SELECT * FROM " + TABLE_MEMBER + " WHERE "
                        + RELATIONSHIP_ID + "=" + getRelationShipIdByName("Self") + " AND "
                        + MEMBER_ID + "=" + baselineInfo.getFamilyHeadId() + " LIMIT 1";
                //SQLiteDatabase db1 = this.getReadableDatabase();
                //Cursor points to the results
                Log.d("Query:","\n\nQRY="+qry+"\n\nQUERY="+query);
                Cursor c = db.rawQuery(query, null);

                c.moveToFirst();

                MemberInfo memberInfo = new MemberInfo();

                while (!c.isAfterLast()) {

                    memberInfo.setMemberId(c.getInt(c.getColumnIndex(MEMBER_ID)));
                    memberInfo.setUniqueId(c.getInt(c.getColumnIndex(UNIQUE_ID)));
                    memberInfo.setMemberName(c.getString(c.getColumnIndex(MEMBER_NAME)));
                    memberInfo.setDob(c.getString(c.getColumnIndex(DOB)));
                    memberInfo.setGender(c.getString(c.getColumnIndex(GENDER)));
                    memberInfo.setSocialCategoryId(c.getInt(c.getColumnIndex(SOCIAL_CATEGORY_ID)));
                    memberInfo.setAadhaarCardId(c.getString(c.getColumnIndex(AADHAAR_CARD_ID)));
                    memberInfo.setVoterId(c.getString(c.getColumnIndex(VOTER_ID)));
                    memberInfo.setFamilyHead(c.getString(c.getColumnIndex(FAMILY_HEAD)));
                    memberInfo.setPersonalSalary(c.getString(c.getColumnIndex(PERSONAL_SALARY)));
                    memberInfo.setOccupationId(c.getInt(c.getColumnIndex(OCCUPATION_ID)));
                    memberInfo.setDisabilityId(c.getInt(c.getColumnIndex(DISABILTIES_ID)));
                    memberInfo.setRelationshipId(c.getInt(c.getColumnIndex(RELATIONSHIP_ID)));
                    memberInfo.setEducationId(c.getInt(c.getColumnIndex(EDUCATION_ID)));
                    memberInfo.setEducationStatusId(c.getInt(c.getColumnIndex(EDUCATION_STATUS_ID)));
                    memberInfo.setMaritalStatusId(c.getInt(c.getColumnIndex(MARITAL_STATUS_ID)));
                    memberInfo.setRelationshipId(c.getInt(c.getColumnIndex(RELIGION_ID)));
                    memberInfo.setSchemeId(c.getInt(c.getColumnIndex(SCHEME_ID)));

                    c.moveToNext();

                }

                Log.d(TAG, "Row Fetched from Member table");

                baseHead.setMemberInfo(memberInfo);
                Log.d("Fetch Baseline Data:", "stateId=" + baselineInfo.getStateId()
                        + "\ndistrictId=" + baselineInfo.getDistrictId()
                        + "\nblockId=" + baselineInfo.getBlockId()
                        + "\nvillageId=" + baselineInfo.getVillageId()
                        + "\nbaselineId=" +  baselineInfo.getBaselineId()
                        + "\nmemberId=" + baselineInfo.getFamilyHeadId()
                        + "\nsurveyUserId=" + baselineInfo.getSurveyUserId()
                        + "\nsocialCategoryId=" + baselineInfo.getSocialCategoryId()
                        + "\nreligionId=" + baselineInfo.getReligionId()
                        + "\noccupationId=" + baselineInfo.getOccupationId()
                        + "\ncontactNo=" + baselineInfo.getContactNo()
                        + "\nfamilyMemberNumber=" + baselineInfo.getFamilyMemberNumber()
                        + "\nincome=" + baselineInfo.getIncome());
                Log.d("Fetch Member Data:","");

                baselineHeadInfo.add(baseHead);
            }
        }

        return baselineHeadInfo;

    }

    //fetch from baseline informationtable
    public ArrayList<BaselineInfo> getAllBaselineInformation(){

        ArrayList<BaselineInfo> baselineInformationList = new ArrayList<BaselineInfo>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_BASELINE_INFORMATION+ " ORDER BY "+ BASELINE_ID + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                BaselineInfo baselineInfo = new BaselineInfo();
                baselineInfo.setBaselineId(cursor.getInt(cursor.getColumnIndex(BASELINE_ID)));
                baselineInfo.setFamilyHeadId(cursor.getInt(cursor.getColumnIndex(FAMILY_HEAD_ID)));
                baselineInfo.setStateId(cursor.getInt(cursor.getColumnIndex(STATE_ID)));
                baselineInfo.setDistrictId(cursor.getInt(cursor.getColumnIndex(DISTRICT_ID)));
                baselineInfo.setBlockId(cursor.getInt(cursor.getColumnIndex(BLOCK_ID)));
                baselineInfo.setVillageId(cursor.getInt(cursor.getColumnIndex(VILLAGE_ID)));
                baselineInfo.setSurveyUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
                baselineInfo.setSocialCategoryId(cursor.getInt(cursor.getColumnIndex(SOCIAL_CATEGORY_ID)));
                baselineInfo.setReligionId(cursor.getInt(cursor.getColumnIndex(RELIGION_ID)));
                baselineInfo.setOccupationId(cursor.getInt(cursor.getColumnIndex(OCCUPATION_ID)));
                baselineInfo.setContactNo(cursor.getString(cursor.getColumnIndex(CONTACT_NO)));
                baselineInfo.setFamilyMemberNumber(cursor.getInt(cursor.getColumnIndex(FAMILY_MEMBER_NUMBER)));
                baselineInfo.setIncome(cursor.getString(cursor.getColumnIndex(INCOME)));

                baselineInformationList.add(baselineInfo);
                Log.d("Fetch Data:", "stateId=" + baselineInfo.getStateId()
                        + "\ndistrictId=" + baselineInfo.getDistrictId()
                        + "\nblockId=" + baselineInfo.getBlockId()
                        + "\nvillageId=" + baselineInfo.getVillageId()
                        + "\nbaselineId=" +  baselineInfo.getBaselineId()
                        + "\nmemberId=" + baselineInfo.getFamilyHeadId()
                        + "\nsurveyUserId=" + baselineInfo.getSurveyUserId()
                        + "\nsocialCategoryId=" + baselineInfo.getSocialCategoryId()
                        + "\nreligionId=" + baselineInfo.getReligionId()
                        + "\noccupationId=" + baselineInfo.getOccupationId()
                        + "\ncontactNo=" + baselineInfo.getContactNo()
                        + "\nfamilyMemberNumber=" + baselineInfo.getFamilyMemberNumber()
                        + "\nincome=" + baselineInfo.getIncome());

            }
        }
        return baselineInformationList;
    }

    public int getLastBaselineId() {
        int baselineId = 0;

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_BASELINE_INFORMATION + " ORDER BY " + BASELINE_ID + " DESC LIMIT 1";

        //Cursor points to the results
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {

            baselineId = c.getInt(c.getColumnIndex(BASELINE_ID));

            c.moveToNext();

        }

        db.close();

        Log.d(TAG, "Row Fetched from Baseline table");

        return baselineId;
    }


    //INSERT ROW INTO TABLE_IMPACT_AREA
    public void insertImpactData(ImpactInfo impactInfo){

        ContentValues values = new ContentValues();

        values.put(IMPACT_ID, impactInfo.getImpactId());
        values.put(BASELINE_ID, impactInfo.getBaselineId());
        values.put(FAMILY_HEAD_ID, impactInfo.getFamilyHeadId());
        values.put(LAND, impactInfo.getLand());
        values.put(PLANT_NUMBER, impactInfo.getNoOfPlants());
        values.put(PLANTING_YEAR, impactInfo.getYearOfPlanting());
        values.put(HERR, impactInfo.getHerr());
        values.put(PROJECT_ID, impactInfo.getProjectId());
        values.put(BENEFICIARY_NAME, impactInfo.getBeneficiaryName());
        values.put(HUSBAND_WIFE_NAME, impactInfo.getHusbandWifeName());
        values.put(STATE_ID, impactInfo.getStateId());
        values.put(DISTRICT_ID, impactInfo.getDistrictId());
        values.put(VILLAGE_ID, impactInfo.getVillageId());
        values.put(IMPACT_INCOME, impactInfo.getImpactIncome());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_IMPACT_INFORMATION, null, values);
        Log.d(TAG, "Row Inserted in TABLE_IMPACT_INFORMATION");

        db.close();
    }

    //UPDATE Row from TABLE_IMPACT_AREA
    public void updateImpactData(ImpactInfo impactInfo){

        SQLiteDatabase db = getWritableDatabase();
        //String currentDate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());

        db.execSQL("UPDATE"+ TABLE_IMPACT_INFORMATION + "SET" + STATE_ID + "=\"" + impactInfo.getStateId() + "\"" +
                DISTRICT_ID + "=\"" + impactInfo.getDistrictId() +"\" " +VILLAGE_ID + "=\"" + impactInfo.getVillageId()+ "\"" +
                ""+PROJECT_ID + "=\"" + impactInfo.getProjectId() +"\""+FAMILY_HEAD_ID + "=\"" + impactInfo.getFamilyHeadId() +"\"" +
                ""+BENEFICIARY_NAME + "=\"" + impactInfo.getBeneficiaryName() +""+HUSBAND_WIFE_NAME + "=\"" + impactInfo.getHusbandWifeName() +"" +
                ""+LAND + "=\"" + impactInfo.getLand() +""+PLANTING_YEAR + "=\"" + impactInfo.getYearOfPlanting() +"" +PLANT_NUMBER + "=\"" + impactInfo.getNoOfPlants() +
                ""+HERR + "=\"" + impactInfo.getHerr()+""+IMPACT_INCOME + "=\"" + impactInfo.getImpactIncome()+" WHERE "+BASELINE_ID+ "=\"" + impactInfo.getBaselineId()+" ;");
        Log.d(TAG, "Row Deleted in TABLE_Impact_Information");

    }

//Update
    //INSERT ROW INTO TABLE_MEMBER
    public void insertMemberInformation(MemberInfo memberInfo){

        ContentValues values = new ContentValues();

        values.put(MEMBER_NAME, memberInfo.getMemberName());
        values.put(DOB, memberInfo.getDob());
        values.put(GENDER, memberInfo.getGender());
        values.put(QUOTA, memberInfo.getSocialCategoryId());
        values.put(AADHAAR_CARD_ID, memberInfo.getAadhaarCardId());
        values.put(VOTER_ID, memberInfo.getVoterId());
        values.put(PERSONAL_SALARY, memberInfo.getPersonalSalary());
        values.put(OCCUPATION_ID, memberInfo.getOccupationId());
        values.put(DISABILTIES_ID, memberInfo.getDisabilityId());
        values.put(RELATIONSHIP_ID, memberInfo.getRelationshipId());
        values.put(EDUCATION_ID, memberInfo.getEducationId());
        values.put(EDUCATION_STATUS_ID, memberInfo.getEducationStatusId());
        values.put(MARITAL_STATUS_ID, memberInfo.getMaritalStatusId());
        values.put(RELIGION_ID, memberInfo.getReligionId());
        values.put(SCHEME_ID, memberInfo.getSchemeId());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_MEMBER, null, values);
        Log.d(TAG, "Row Inserted in TABLE_MEMBER");

        db.close();
    }

    public int getLastMemberId() {
        int memberId = 0;

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MEMBER + " ORDER BY " + MEMBER_ID + " DESC LIMIT 1";

        //Cursor points to the results
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {

            memberId = c.getInt(c.getColumnIndex(MEMBER_ID));

            c.moveToNext();

        }

        Log.d(TAG, "Row Fetched from Member table");

        return memberId;
    }

    public ArrayList<MemberInfo> getAllMemberDetail(int uniqueId) {

        ArrayList<MemberInfo> allMember = new ArrayList<MemberInfo>();

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MEMBER + " WHERE " + UNIQUE_ID + "=" + uniqueId;

        //Cursor points to the results
        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0) {

            c.moveToFirst();

            while (!c.isAfterLast()) {

                MemberInfo memberInfo = new MemberInfo();

                memberInfo.setMemberId(c.getInt(c.getColumnIndex(MEMBER_ID)));
                memberInfo.setUniqueId(c.getInt(c.getColumnIndex(UNIQUE_ID)));
                memberInfo.setMemberName(c.getString(c.getColumnIndex(MEMBER_NAME)));
                memberInfo.setDob(c.getString(c.getColumnIndex(DOB)));
                memberInfo.setGender(c.getString(c.getColumnIndex(GENDER)));
                memberInfo.setSocialCategoryId(c.getInt(c.getColumnIndex(QUOTA)));
                memberInfo.setAadhaarCardId(c.getString(c.getColumnIndex(AADHAAR_CARD_ID)));
                memberInfo.setVoterId(c.getString(c.getColumnIndex(VOTER_ID)));
                memberInfo.setFamilyHead(c.getString(c.getColumnIndex(FAMILY_HEAD)));
                memberInfo.setPersonalSalary(c.getString(c.getColumnIndex(PERSONAL_SALARY)));
                memberInfo.setOccupationId(c.getInt(c.getColumnIndex(OCCUPATION_ID)));
                memberInfo.setDisabilityId(c.getInt(c.getColumnIndex(DISABILTIES_ID)));
                memberInfo.setRelationshipId(c.getInt(c.getColumnIndex(RELATIONSHIP_ID)));
                memberInfo.setEducationId(c.getInt(c.getColumnIndex(EDUCATION_ID)));
                memberInfo.setEducationStatusId(c.getInt(c.getColumnIndex(EDUCATION_STATUS_ID)));
                memberInfo.setMaritalStatusId(c.getInt(c.getColumnIndex(MARITAL_STATUS_ID)));
                memberInfo.setRelationshipId(c.getInt(c.getColumnIndex(RELIGION_ID)));
                memberInfo.setSchemeId(c.getInt(c.getColumnIndex(SCHEME_ID)));

                Log.d("DBHandler-GetAllMember", "Data:\n" +
                        "\nmemberId=" + memberInfo.getMemberId() + "" +
                        "\nuniqueId=" + memberInfo.getUniqueId() + "" +
                        "\nmemberName=" + memberInfo.getMemberName() + "" +
                        "\nDOB=" + memberInfo.getDob() + "" +
                        "\ngender=" + memberInfo.getGender() + "" +
                        "\nsocialCategoryId=" + memberInfo.getSocialCategoryId() + "" +
                        "\naadhar=" + memberInfo.getAadhaarCardId() + "" +
                        "\nvoterId=" + memberInfo.getVoterId() + "" +
                        "\nfamilyHead=" + memberInfo.getFamilyHead() + "" +
                        "\npersonalSalary=" + memberInfo.getPersonalSalary() + "" +
                        "\noccupationId=" + memberInfo.getOccupationId() + "" +
                        "\ndisabilitiesId=" + memberInfo.getDisabilityId() + "" +
                        "\nrelationshipId=" + memberInfo.getRelationshipId() + "" +
                        "\neducationId=" + memberInfo.getEducationId() + "" +
                        "\neducationStatusId=" + memberInfo.getEducationStatusId() + " " +
                        "\nmaritalStatusId=" + memberInfo.getMaritalStatusId() + " " +
                        "\nreligionId=" + memberInfo.getReligionId() + " " +
                        "\nschemeId=" + memberInfo.getSchemeId());

                allMember.add(memberInfo);

                c.moveToNext();

            }
        }

        Log.d(TAG, "Row Fetched from Member table");

        return allMember;
    }

    //UPDATE Row from TABLE_MEMBER
    public void updateMemberUniqueId(int memberId, int baselineId){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE "+ TABLE_MEMBER + " SET " + UNIQUE_ID + "="+ baselineId +" WHERE "+ MEMBER_ID +"="+ memberId+";");
        Log.d(TAG, "Row Updated in updateMemberUniqueId");
        getAllMemberDetail(baselineId);

    }


    //UPDATE Row from TABLE_MEMBER
    public void updateMemberInformation(MemberInfo memberInfo){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE "+ TABLE_MEMBER + "SET " + UNIQUE_ID + "=\"" + memberInfo.getUniqueId() + "\"" +
                MEMBER_NAME + "=\"" + memberInfo.getMemberName() + "\" " +DOB + "=\"" + memberInfo.getDob() +"\" " +
                ""+GENDER + "=\"" + memberInfo.getGender()+ "\""+QUOTA + "=\"" + memberInfo.getSocialCategoryId()+ "\"" +
                ""+AADHAAR_CARD_ID + "=\"" + memberInfo.getAadhaarCardId() +"\""+VOTER_ID + "=\"" + memberInfo.getVoterId() +"\"" +
                ""+FAMILY_HEAD + "=\"" + memberInfo.getFamilyHead() +""+PERSONAL_SALARY + "=\"" + memberInfo.getPersonalSalary() +"\""+
                ""+OCCUPATION_ID + "=\"" + memberInfo.getOccupationId() +"\""+DISABILTIES_ID + "=\"" + memberInfo.getDisabilityId() +"\""+
                ""+RELATIONSHIP_ID + "=\"" + memberInfo.getRelationshipId() +"\""+EDUCATION_ID + "=\"" + memberInfo.getEducationId()+"\""+
                ""+EDUCATION_STATUS_ID + "=\"" + memberInfo.getEducationStatusId() +"\""+MARITAL_STATUS_ID + "=\"" + memberInfo.getMaritalStatusId() +"\"" +
                ""+RELIGION_ID + "=\"" + memberInfo.getReligionId() +"\""+SCHEME_ID + "=\"" + memberInfo.getSchemeId() +"\"" +";");
        Log.d(TAG, "Row Deleted in TABLE_Member_Information");

    }

    //insert into STATE Table
    public void insertState(State state){

        ContentValues values = new ContentValues();

        values.put(STATE_ID, state.getStateId());
        values.put(STATE_CODE, state.getStateCode());
        values.put(STATE_NAME, state.getStateName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STATE, null, values);
        Log.d(TAG, "Row Inserted in TABLE_STATE");
        db.close();
    }

    //delete from STATE Table
    public void deleteState(State state){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STATE + ";");
        Log.d(TAG, "Row Deleted in TABLE_STATE");
    }

    //fetch data from state
    public State getLastState(){

        State state = new State();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_STATE+ " ORDER BY " + STATE_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                state.setStateId(cursor.getInt(cursor.getColumnIndex(STATE_ID)));
                state.setStateName(cursor.getString(cursor.getColumnIndex(STATE_NAME)));
                state.setStateCode(cursor.getString(cursor.getColumnIndex(STATE_CODE)));

            }
        }
        return state;
    }


    //insert into DISTRICT Table
    public void insertDistrict(District district){

        ContentValues values = new ContentValues();

        values.put(DISTRICT_ID, district.getDistrictId());
        values.put(DISTRICT_CODE, district.getDistrictCode());
        values.put(DISTRICT_NAME, district.getDistrictName());
        values.put(STATE_ID, district.getStateId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DISTRICT, null, values);
        Log.d(TAG, "Row Inserted in TABLE_DISTRICT");
        db.close();
    }

    //delete from DISTRICT Table
    public void deleteDistrict(District district){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DISTRICT + ";");
        Log.d(TAG, "Row Deleted in TABLE_DISTRICT");
    }

    //fetch data from district
    public District getLastDistrict(){

        District district = new District();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_DISTRICT+ " ORDER BY " + DISTRICT_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                district.setDistrictId(cursor.getInt(cursor.getColumnIndex(DISTRICT_ID)));
                district.setDistrictName(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME)));
                district.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_CODE)));

            }
        }
        return district;
    }

    //insert into BLOCK Table
    public void insertBLOCK(Block block){

        ContentValues values = new ContentValues();

        values.put(BLOCK_ID, block.getBlockId());
        values.put(BLOCK_CODE, block.getBlockCode());
        values.put(BLOCK_NAME, block.getBlockName());
        values.put(DISTRICT_ID, block.getDistrictId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BLOCK, null, values);
        Log.d(TAG, "Row Inserted in TABLE_BLOCK");
        db.close();
    }

    //delete from BLOCK Table
    public void deleteBlock(Block block){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BLOCK + ";");
        Log.d(TAG, "Row Deleted in TABLE_BLOCK");
    }

    //fetch data from block
    public Block getLastBlock(){

        Block block = new Block();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_BLOCK + " ORDER BY " + BLOCK_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                block.setBlockId(cursor.getInt(cursor.getColumnIndex(BLOCK_ID)));
                block.setBlockName(cursor.getString(cursor.getColumnIndex(BLOCK_NAME)));
                block.setBlockCode(cursor.getString(cursor.getColumnIndex(BLOCK_CODE)));

            }
        }
        return block;
    }


    //insert into VILLAGE Table
    public void insertVillage(Village village){

        ContentValues values = new ContentValues();

        values.put(VILLAGE_ID, village.getVillageId());
        values.put(VILLAGE_CODE, village.getVillageCode());
        values.put(VILLAGE_NAME, village.getVillageName());
        values.put(BLOCK_ID, village.getBlockId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_VILLAGE, null, values);
        Log.d(TAG, "Row Inserted in TABLE_DISTRICT");
        db.close();
    }

    //fetch data from village
    public Village getLastVillage(){

        Village village = new Village();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_VILLAGE+ " ORDER BY " + VILLAGE_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                village.setVillageId(cursor.getInt(cursor.getColumnIndex(VILLAGE_ID)));
                village.setVillageName(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME)));
                village.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_CODE)));

            }
        }
        return village;
    }

    //inser Into Asset
    public void insertAsset(Asset asset){

        ContentValues values = new ContentValues();

        values.put(ASSET_ID, asset.getAssetId());
        values.put(ASSET_CODE, asset.getAssetCode());
        values.put(ASSET_NAME, asset.getAssetName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(ASSET, null, values);
        Log.d(TAG, "Row Inserted in TABLE_ASSET");
        db.close();
    }

    //inser Into Disability
    public void insertDisabilities(Disabilities disability){

        ContentValues values = new ContentValues();

        values.put(DISABILTIES_ID, disability.getDisabilitiesId());
        values.put(DISABILTIES_CODE, disability.getDisbilitiesCode());
        values.put(DISABILTIES_NAME, disability.getDisabilitiesName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(DISABILITIES, null, values);
        Log.d(TAG, "Row Inserted in TABLE_DISABILITIES");
        db.close();
    }

    //fetch from disability table
    public ArrayList<Disabilities> getAllDisability(){

        ArrayList<Disabilities> disabilityList = new ArrayList<Disabilities>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+DISABILITIES+ " ORDER BY "+ DISABILTIES_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                Disabilities disabilities = new Disabilities();
                disabilities.setDisabilitiesId(cursor.getInt(cursor.getColumnIndex(DISABILTIES_ID)));
                disabilities.setDisabilitiesCode(cursor.getString(cursor.getColumnIndex(DISABILTIES_CODE)));
                disabilities.setDisabilitiesName(cursor.getString(cursor.getColumnIndex(DISABILTIES_NAME)));
                disabilityList.add(disabilities);

            }
        }
        return disabilityList;
    }

    //insert Into Education
    public void insertEducation(Education education){

        ContentValues values = new ContentValues();

        values.put(EDUCATION_ID, education.getEducationId());
        values.put(EDUCATION_CODE, education.getEducationCode());
        values.put(EDUCATION_NAME, education.getEducationName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(EDUCATION, null, values);
        Log.d(TAG, "Row Inserted in TABLE_EDUCATION");
        db.close();
    }

    //fetch from education table
    public ArrayList<Education> getAllEducation(){

        ArrayList<Education> educationList = new ArrayList<Education>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+EDUCATION+ " ORDER BY "+ EDUCATION_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                Education education = new Education();
                education.setEducationId(cursor.getInt(cursor.getColumnIndex(EDUCATION_ID)));
                education.setEducationCode(cursor.getString(cursor.getColumnIndex(EDUCATION_CODE)));
                education.setEducationName(cursor.getString(cursor.getColumnIndex(EDUCATION_NAME)));
                educationList.add(education);

            }
        }
        return educationList;
    }

    //insert Into Project
    public void insertProject(Project project){

        ContentValues values = new ContentValues();

        values.put(PROJECT_ID, project.getProjectId());
        values.put(PROJECT_NAME, project.getProjectName());
        values.put(DONOR_NAME, project.getDonorName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PROJECT, null, values);
        Log.d(TAG, "Row Inserted in TABLE_PROJECT");
        db.close();
    }

    //fetch from project table
    public ArrayList<Project> getAllProject(){

        ArrayList<Project> projectList = new ArrayList<Project>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_PROJECT+ " ORDER BY "+ PROJECT_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                Project project = new Project();
                project.setProjectId(cursor.getInt(cursor.getColumnIndex(PROJECT_ID)));
                project.setProjectName(cursor.getString(cursor.getColumnIndex(PROJECT_NAME)));
                project.setDonorName(cursor.getString(cursor.getColumnIndex(DONOR_NAME)));
                projectList.add(project);

            }
        }
        return projectList;
    }


    //inser Into EducationStatus
    public void insertEducationStatus(EducationStatus educationstatus){

        ContentValues values = new ContentValues();

        values.put(EDUCATION_STATUS_ID, educationstatus.getEducationStatusId());
        values.put(EDUCATION_STATUS_CODE, educationstatus.getEducationStatusCode());
        values.put(EDUCATION_STATUS_NAME, educationstatus.getEducationStatusName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(EDUCATION_STATUS, null, values);
        Log.d(TAG, "Row Inserted in TABLE_EDUCATION_STATUS");
        db.close();
    }

    //fetch from education status table
    public ArrayList<EducationStatus> getAllEducationStatus(){

        ArrayList<EducationStatus> educationStatusList = new ArrayList<EducationStatus>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+EDUCATION_STATUS+ " ORDER BY "+ EDUCATION_STATUS_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                EducationStatus educationStatus = new EducationStatus();
                educationStatus.setEducationStatusId(cursor.getInt(cursor.getColumnIndex(EDUCATION_STATUS_ID)));
                educationStatus.setEducationStatusCode(cursor.getString(cursor.getColumnIndex(EDUCATION_STATUS_CODE)));
                educationStatus.setEducationStatusName(cursor.getString(cursor.getColumnIndex(EDUCATION_STATUS_NAME)));
                educationStatusList.add(educationStatus);

            }
        }
        return educationStatusList;
    }

    //inser Into MaritalStatus
    public void insertMaritalStatus(MaritalStatus maritalStatus){

        ContentValues values = new ContentValues();

        values.put(MARITAL_STATUS_ID, maritalStatus.getMaritalStatusId());
        values.put(MARITAL_STATUS_CODE, maritalStatus.getMaritalStatusCode());
        values.put(MARTIAL_STATUS_NAME, maritalStatus.getMaritalStatusName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(MARITAL_STATUS, null, values);
        Log.d(TAG, "Row Inserted in MARITAL_STATUS");
        db.close();
    }

    //fetch from marital status table
    public ArrayList<MaritalStatus> getAllMaritalStatus(){

        ArrayList<MaritalStatus> maritalStatusList = new ArrayList<MaritalStatus>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+MARITAL_STATUS+ " ORDER BY "+ MARTIAL_STATUS_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                MaritalStatus maritalStatus = new MaritalStatus();
                maritalStatus.setMaritalStatusId(cursor.getInt(cursor.getColumnIndex(MARITAL_STATUS_ID)));
                maritalStatus.setMaritalStatusCode(cursor.getString(cursor.getColumnIndex(MARITAL_STATUS_CODE)));
                maritalStatus.setMaritalStatusName(cursor.getString(cursor.getColumnIndex(MARTIAL_STATUS_NAME)));
                maritalStatusList.add(maritalStatus);

            }
        }
        return maritalStatusList;
    }

    //inser Into relationship
    public void insertRelationship(Relationship relationship){

        ContentValues values = new ContentValues();

        values.put(RELATIONSHIP_ID, relationship.getRelationshipId());
        values.put(RELATIONSHIP_Code, relationship.getRelationshipCode());
        values.put(RELATIONSHIP_NAME, relationship.getRelationshipName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(RELATIONSHIP, null, values);
        Log.d(TAG, "Row Inserted in RELATIONSHIP");
        db.close();
    }

    //fetch from relationship table
    public ArrayList<Relationship> getAllRelationship(){

        ArrayList<Relationship> relationshipList = new ArrayList<Relationship>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+RELATIONSHIP+ " ORDER BY "+ RELATIONSHIP_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                Relationship relationship = new Relationship();
                relationship.setRelationshipId(cursor.getInt(cursor.getColumnIndex(RELATIONSHIP_ID)));
                relationship.setRelationshipCode(cursor.getString(cursor.getColumnIndex(RELATIONSHIP_Code)));
                relationship.setRelationshipName(cursor.getString(cursor.getColumnIndex(RELATIONSHIP_NAME)));
                relationshipList.add(relationship);

            }
        }
        return relationshipList;
    }

    //insert Into MigrationReason
    public void insertMigrationReason(MigrationReason migrationReason){

        ContentValues values = new ContentValues();

        values.put(MIGRATION_REASON_ID, migrationReason.getMigrationReasonId());
        values.put(MIGRATION_REASON_CODE, migrationReason.getMigrationReasonCode());
        values.put(MIGRATION_REASON_NAME, migrationReason.getMigrationReasonName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(MIGRATION_REASON, null, values);
        Log.d(TAG, "Row Inserted in TABLE_MIGRATION_REASON");
        db.close();
    }

    //fetch from migration reason table
    public ArrayList<MigrationReason> getAllMigrationReason(){

        ArrayList<MigrationReason> migrationReasonList = new ArrayList<MigrationReason>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+MIGRATION_REASON+ " ORDER BY "+ MIGRATION_REASON_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                MigrationReason migrationReason = new MigrationReason();
                migrationReason.setMigrationReasonId(cursor.getInt(cursor.getColumnIndex(MIGRATION_REASON_ID)));
                migrationReason.setMigrationReasonCode(cursor.getString(cursor.getColumnIndex(MIGRATION_REASON_CODE)));
                migrationReason.setMigrationReasonName(cursor.getString(cursor.getColumnIndex(MIGRATION_REASON_NAME)));
                migrationReasonList.add(migrationReason);

            }
        }
        return migrationReasonList;
    }


    //delete from VILLAGE Table
    public void deleteVillage(Village village){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VILLAGE + ";");
        Log.d(TAG, "Row Deleted in TABLE_VILLAGE");
    }

    //fetch data from village
    public ArrayList<Village> getAllVillage(){

        ArrayList<Village> villageList = new ArrayList<Village>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+TABLE_VILLAGE + " ORDER BY "+ VILLAGE_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Village village = new Village();
                village.setVillageId(cursor.getInt(cursor.getColumnIndex(VILLAGE_ID)));
                village.setVillageName(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME)));
                village.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_CODE)));
                villageList.add(village);

            }
        }
        return villageList;
    }

    //insert into RELIGION Table
    public void insertReligionInformation(Religion religion){

        ContentValues values = new ContentValues();

        values.put(RELIGION_ID, religion.getReligionId());
        values.put(RELIGION_CODE, religion.getReligionCode());
        values.put(RELIGION_NAME, religion.getReligionName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(RELIGION, null, values);
        Log.d(TAG, "Row Inserted in RELIGION");
        db.close();
    }

    //insert into scheme Table
    public void insertScheme(Scheme scheme){

        ContentValues values = new ContentValues();

        values.put(SCHEME_ID, scheme.getSchemeId());
        values.put(SCHEME_NAME, scheme.getSchemeName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(SCHEME, null, values);
        Log.d(TAG, "Row Inserted in SCHEME");
        db.close();
    }

    //fetch from scheme table
    public ArrayList<Scheme> getAllScheme(){

        ArrayList<Scheme> schemeList = new ArrayList<Scheme>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+SCHEME+ " ORDER BY "+ SCHEME_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                Scheme scheme = new Scheme();
                scheme.setSchemeId(cursor.getInt(cursor.getColumnIndex(SCHEME_ID)));
                scheme.setSchemeName(cursor.getString(cursor.getColumnIndex(SCHEME_NAME)));
                schemeList.add(scheme);

            }
        }
        return schemeList;
    }


    //delete from RELIGION Table
    public void deleteReligionInformation(Religion religion){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + RELIGION + ";");
        Log.d(TAG, "Row Deleted in RELIGION");

    }

    //fetch from religion table
    public ArrayList<Religion> getAllReligion(){

        ArrayList<Religion> religionList = new ArrayList<Religion>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+RELIGION+ " ORDER BY "+ RELIGION_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                /*String religionName = cursor.getString(cursor.getColumnIndex("religionName"));
                String religionCode = cursor.getString(cursor.getColumnIndex("religionCode"));
                religionList.add(religionCode);
                religionList.add(religionName);*/

                Religion religion = new Religion();
                religion.setReligionId(cursor.getInt(cursor.getColumnIndex(RELIGION_ID)));
                religion.setReligionCode(cursor.getString(cursor.getColumnIndex(RELIGION_CODE)));
                religion.setReligionName(cursor.getString(cursor.getColumnIndex(RELIGION_NAME)));
                religionList.add(religion);

            }
        }
        return religionList;
    }

    //insert into SocialCategory Table
    public void insertSocialCategory(SocialCategory socialCategory){

        ContentValues values = new ContentValues();

        values.put(SOCIAL_CATEGORY_ID, socialCategory.getSocialCategoryId());
        values.put(SOCIAL_CATEGORY_CODE, socialCategory.getSocialCategoryCode());
        values.put(SOCIAL_CATEGORY_NAME, socialCategory.getSocialCategoryName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(SOCIAL_CATEGORY, null, values);
        Log.d(TAG, "Row Inserted in SOCIAL_CATEGORY");
        db.close();
    }

    //delete from SOCIAL_CATEGORY Table
    public void deleteSocialCategory(SocialCategory socialCategory){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + SOCIAL_CATEGORY + ";");
        Log.d(TAG, "Row Deleted in SOCIAL_CATEGORY");
    }

    //fetch from social category table
    public ArrayList<SocialCategory> getAllSocialCategory(){

        ArrayList<SocialCategory> socialCategoryList = new ArrayList<SocialCategory>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+SOCIAL_CATEGORY+ " ORDER BY "+ SOCIAL_CATEGORY_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                SocialCategory socialCategory = new SocialCategory();
                socialCategory.setSocialCategoryId(cursor.getInt(cursor.getColumnIndex(SOCIAL_CATEGORY_ID)));
                socialCategory.setSocialCategoryName(cursor.getString(cursor.getColumnIndex(SOCIAL_CATEGORY_NAME)));
                socialCategory.setSocialCategoryCode(cursor.getString(cursor.getColumnIndex(SOCIAL_CATEGORY_CODE)));
                socialCategoryList.add(socialCategory);

            }
        }
        return socialCategoryList;
    }

    //insert into Occupation Table
    public void insertOccupation(Occupation occupation){

        ContentValues values = new ContentValues();

        values.put(OCCUPATION_ID, occupation.getOccupationId());
        values.put(OCCUPATION_CODE, occupation.getOccupationCode());
        values.put(OCCUPATION_NAME, occupation.getOccupationName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(OCCUPATION, null, values);
        Log.d(TAG, "Row Inserted in OCCUPATION");
        db.close();
    }

    //delete from Occupation Table
    public void deleteOccupation(Occupation occupation){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + OCCUPATION + ";");
        Log.d(TAG, "Row Deleted in OCCUPATION");
    }

    //fetch from occupation table
    public ArrayList<Occupation> getAllOccupation(){

        ArrayList<Occupation> occupationList = new ArrayList<Occupation>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String qry = "SELECT * FROM "+OCCUPATION+ " ORDER BY "+ OCCUPATION_NAME + " ASC" ;
        Cursor cursor = db.rawQuery(qry,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Occupation occupation = new Occupation();
                occupation.setOccupationId(cursor.getInt(cursor.getColumnIndex(OCCUPATION_ID)));
                occupation.setOccupationName(cursor.getString(cursor.getColumnIndex(OCCUPATION_NAME)));
                occupation.setOccupationCode(cursor.getString(cursor.getColumnIndex(OCCUPATION_CODE)));
                occupationList.add(occupation);

            }
        }
        return occupationList;
    }
}
