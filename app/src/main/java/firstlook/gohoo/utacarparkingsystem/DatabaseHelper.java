package firstlook.gohoo.utacarparkingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DBName = "SE";
    private Parking_Users parkingUsers;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, 1);
        parkingUsers = new Parking_Users();
        Log.e("TEXT", "-----------------Came to Constructor-----------------");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("TEXT", "-----------------Came to create Database-----------------");
        db.execSQL(parkingUsers.getCreateTableQuery());

        Log.e("TEXT", "-----------------Database Created Successfully-----------------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Parking_Users.TABLE_NAME);
        onCreate(db);

    }

    public long insertParkingUser(String fName, String lName, String name, String userpassword, String userrole, String email, String usercity,
                                  String useraddress, String phone, String userstate, String zip, String vehicleNumber, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("TEXT", "-----------------Database Created Successfully-----------------");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Parking_Users.COLUMN_FIRSTNAME, fName);
        contentValues.put(Parking_Users.COLUMN_LASTNAME, lName);
        contentValues.put(Parking_Users.COLUMN_USERNAME, name);
        contentValues.put(Parking_Users.COLUMN_PASSWORD, userpassword);
        contentValues.put(Parking_Users.COLUMN_ROLE, userrole);
        contentValues.put(Parking_Users.COLUMN_EMAIL, email);
        contentValues.put(Parking_Users.COLUMN_CITY, usercity);
        contentValues.put(Parking_Users.COLUMN_STREETADDRESS, useraddress);
        contentValues.put(Parking_Users.COLUMN_PHONE, phone);
        contentValues.put(Parking_Users.COLUMN_STATE, userstate);
        contentValues.put(Parking_Users.COLUMN_ZIPCODE, zip);
        contentValues.put(Parking_Users.COLUMN_VEHICLENUM, vehicleNumber);
        contentValues.put(Parking_Users.COLUMN_PARKINGTYPE, type);

        long id = db.insert(Parking_Users.TABLE_NAME, null, contentValues);
        Log.e("TEXT", "newEntry" + id);

        db.close();

        return id;
    }


    //check username exists
    public boolean checkUsername(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Parking_Users.TABLE_NAME where userName=?", new String[]{userName});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //check username and password for parking user
    public boolean checkUsernamePasswordParkinUser(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Parking_Users where userName=? and  password=? and role='User'", new String[]{userName, password});
        Log.e("TEXT", "-----------------parking user fetched-----------------");
        if (cursor.getCount() > 0) return true;
        else return false;
    }

//parking manager
    public boolean checkUsernamePasswordPakingManager(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Parking_Users where userName=? and  password=? and role='Manager'", new String[]{userName, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
//admin
    public boolean checkUsernamePasswordAdmin(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Parking_Users where userName=? and  password=? and role='Admin'", new String[]{userName, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
}