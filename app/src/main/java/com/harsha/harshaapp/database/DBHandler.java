package com.harsha.harshaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.harsha.harshaapp.bean.BaselineInfo;
import com.harsha.harshaapp.bean.Block;
import com.harsha.harshaapp.bean.District;
import com.harsha.harshaapp.bean.MemberInfo;
import com.harsha.harshaapp.bean.Occupation;
import com.harsha.harshaapp.bean.Religion;
import com.harsha.harshaapp.bean.SocialCategory;
import com.harsha.harshaapp.bean.State;
import com.harsha.harshaapp.bean.User;
import com.harsha.harshaapp.bean.Village;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static final String DISTRICT_CODE = "districtId";
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

    // Attributes of PROJECT Table
    public static final String PROJECT_ID = "projectId";
    public static final String PROJECT_NAME = "projectName";

    // Attributes of BASELINE_MEMBER Table
    public static final String BASELINE_MEMBER_ID = "baselineMemberId";

    // Attributes of IMPACT_INFORMATION Table
    public static final String IMPACT_ID = "impactId";
    public static final String LAND = "land";
    public static final String PLANT_NUMBER = "plantNo";
    public static final String PLANTING_YEAR = "plantingYear";
    public static final String HERR = "herr";
    public static final String IMPACT_INCOME = "memberIncome";

    //Attributes of SOCIAL_CATEGORY
    public static final String SOCIAL_CATEGORY_ID = "socialCategoryId";
    public static final String SOCIAL_CATEGORY_CODE = "socialCategoryCode";
    public static final String SOCIAL_CATEGORY_NAME = "socialCategoryName";

    //Attributes of RELIGION
    public static final String RELIGION_ID = "religionId";
    public static final String RELIGION_CODE = "religionCode";
    public static final String RELIGION_NAME = "religionName";

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
            STATE_CODE + "TEXT NOT NULL, " +
            STATE_NAME + " TEXT NOT NULL " +
            ");";

    // Create Table for DISTRICT
    public static final String CREATE_DISTRICT = "CREATE TABLE " + TABLE_DISTRICT + " (" +
            DISTRICT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DISTRICT_CODE + "TEXT NOT NULL, " +
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
            CONTACT_NO + " INTEGER NOT NULL, " +
            FAMILY_MEMBER_NUMBER + " INTEGER NOT NULL, " +
            INCOME + " INTEGER NOT NULL " +
            ");";

    // Create Table for MEMBER
    public static final String CREATE_MEMBER = "CREATE TABLE " + TABLE_MEMBER + " (" +
            MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UNIQUE_ID + " INTEGER NOT NULL, " +
            MEMBER_NAME + " TEXT NOT NULL, " +
            DOB + " TEXT NOT NULL, " +
            GENDER + " TEXT NOT NULL, " +
            QUOTA + " TEXT NOT NULL, " +
            AADHAAR_CARD_ID + " INTEGER NOT NULL, " +
            VOTER_ID + " INTEGER NOT NULL, " +
            FAMILY_HEAD + " TEXT NOT NULL, " +
            PERSONAL_SALARY + " INTEGER NOT NULL " +
            ");";

    // Create Table for PROJECT
    public static final String CREATE_PROJECT = "CREATE TABLE " + TABLE_PROJECT + " (" +
            PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROJECT_NAME + " TEXT NOT NULL " +
            ");";

    // Create Table for BASELINE_MEMBER
    public static final String CREATE_BASELINE_MEMBER = "CREATE TABLE " + TABLE_BASELINE_MEMBER + " (" +
            BASELINE_MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            ");";

    // Create Table for IMPACT_INFORMATION
    public static final String CREATE_IMPACT_INNFORMATION = "CREATE TABLE " + TABLE_IMPACT_INFORMATION + " (" +
            IMPACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            LAND + " TEXT NOT NULL, " +
            PLANT_NUMBER + " INTEGER NOT NULL, " +
            PLANTING_YEAR + " INTEGER NOT NULL, " +
            HERR + " INTEGER NOT NULL, " +
            IMPACT_INCOME + " INTEGER NOT NULL " +
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

    // create table for RELIGION
    public static final String CREATE_RELIGION = "CREATE TABLE " + RELIGION + " (" +
            RELIGION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RELIGION_CODE + " TEXT NOT NULL, " +
            RELIGION_NAME + " TEXT NOT NULL " +
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

        db.execSQL(CREATE_IMPACT_INNFORMATION);
        Log.d(TAG, "IMPACT_INNFORMATION TABLE CREATED");

        db.execSQL(CREATE_OCCUPATION);
        Log.d(TAG, "OCCUPATION TABLE CREATED");

        db.execSQL(CREATE_SOCIAL_CATEGORY);
        Log.d(TAG, "SOCIAL_CATEGORY TABLE CREATED");

        db.execSQL(CREATE_RELIGION);
        Log.d(TAG, "RELIGION TABLE CREATED");
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
        db.execSQL("UPDATE"+ TABLE_USER + "SET " + USER_PASSWORD + "=" + user.getPassword() + " WHERE "+ USER_ID+"="+user.getUserId()+ ";");
        Log.d(TAG, "Row Updated in TABLE_USER");

    }

    //INSERT ROW INTO TABLE_BASELINE_INFORMATION
    public void insertBaselineInformation(BaselineInfo baseInfo){

        ContentValues values = new ContentValues();

        String currentDate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());

        values.put(BASELINE_ID, baseInfo.getBaselineId());
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
        String currentDate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());

        db.execSQL("UPDATE"+ TABLE_BASELINE_INFORMATION + "SET" + STATE_ID + "=\"" + baseinfo.getStateId() + "\"" +
                DISTRICT_ID + "=\"" + baseinfo.getDistrictId() + "\" " +DISTRICT_ID + "=\"" + baseinfo.getDistrictId() +"\" " +
                ""+BLOCK_ID + "=\"" + baseinfo.getBlockId()+ "\""+VILLAGE_ID + "=\"" + baseinfo.getVillageId()+ "\"" +
                ""+USER_ID + "=\"" + baseinfo.getSurveyUserId() +"\""+SOCIAL_CATEGORY_ID + "=\"" + baseinfo.getSocialCategoryId() +"\"" +
                ""+RELIGION_ID + "=\"" + baseinfo.getReligionId() +""+OCCUPATION_ID + "=\"" + baseinfo.getOccupationId() +"" +
                ""+OCCUPATION_ID + "=\"" + baseinfo.getOccupationId() +""+CONTACT_NO + "=\"" + baseinfo.getContactNo() +"" +
                ""+FAMILY_MEMBER_NUMBER + "=\"" + baseinfo.getFamilyMemberNumber()+""+INCOME + "=\"" + baseinfo.getIncome()+";");
        Log.d(TAG, "Row Deleted in TABLE_USER");

    }

    //INSERT ROW INTO TABLE_MEMBER
    public void insertMemberInformation(MemberInfo memberInfo){

        ContentValues values = new ContentValues();

        values.put(MEMBER_ID, memberInfo.getMemberId());
        values.put(UNIQUE_ID, memberInfo.getUniqueId());
        values.put(MEMBER_NAME, memberInfo.getMemberName());
        values.put(DOB, memberInfo.getDob());
        values.put(GENDER, memberInfo.getGender());
        values.put(QUOTA, memberInfo.getQuota());
        values.put(AADHAAR_CARD_ID, memberInfo.getAadhaarCardId());
        values.put(VOTER_ID, memberInfo.getVoterId());
        values.put(FAMILY_HEAD, memberInfo.getFamilyHead());
        values.put(PERSONAL_SALARY, memberInfo.getPersonalSalary());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_MEMBER, null, values);
        Log.d(TAG, "Row Inserted in TABLE_MEMBER");

        db.close();
    }

    //UPDATE Row from TABLE_MEMBER
    public void updateMemberInformation(MemberInfo memberInfo){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE"+ TABLE_MEMBER + "SET" + UNIQUE_ID + "=\"" + memberInfo.getUniqueId() + "\"" +
                MEMBER_NAME + "=\"" + memberInfo.getMemberName() + "\" " +DOB + "=\"" + memberInfo.getDob() +"\" " +
                ""+GENDER + "=\"" + memberInfo.getGender()+ "\""+QUOTA + "=\"" + memberInfo.getQuota()+ "\"" +
                ""+AADHAAR_CARD_ID + "=\"" + memberInfo.getAadhaarCardId() +"\""+VOTER_ID + "=\"" + memberInfo.getVoterId() +"\"" +
                ""+FAMILY_HEAD + "=\"" + memberInfo.getFamilyHead() +""+PERSONAL_SALARY + "=\"" + memberInfo.getPersonalSalary() +";");
        Log.d(TAG, "Row Deleted in TABLE_USER");

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

    //insert into BLOCK Table
    public void insertBLOCK(Block block){

        ContentValues values = new ContentValues();

        values.put(BLOCK_ID, block.getBlockId());
        values.put(BLOCK_CODE, block.getBlockCode());
        values.put(BLOCK_NAME, block.getBlockName());
        values.put(DISTRICT_ID, block.getDistrictId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DISTRICT, null, values);
        Log.d(TAG, "Row Inserted in TABLE_BLOCK");
        db.close();
    }

    //delete from BLOCK Table
    public void deleteBlock(Block block){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BLOCK + ";");
        Log.d(TAG, "Row Deleted in TABLE_BLOCK");
    }

    //insert into VILLAGE Table
    public void insertVillage(Village village){

        ContentValues values = new ContentValues();

        values.put(DISTRICT_ID, village.getVillageId());
        values.put(DISTRICT_CODE, village.getVillageCode());
        values.put(DISTRICT_NAME, village.getVillageName());
        values.put(BLOCK_ID, village.getBlockId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DISTRICT, null, values);
        Log.d(TAG, "Row Inserted in TABLE_DISTRICT");
        db.close();
    }

    //delete from VILLAGE Table
    public void deleteVillage(Village village){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VILLAGE + ";");
        Log.d(TAG, "Row Deleted in TABLE_VILLAGE");
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

    //delete from RELIGION Table
    public void deleteReligionInformation(Religion religion){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + RELIGION + ";");
        Log.d(TAG, "Row Deleted in RELIGION");

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
}
