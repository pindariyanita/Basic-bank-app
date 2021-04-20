package com.example.basicbankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_info";
    private String TABLE_NAME1 = "transfers_table";

    public Database(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY ,NAME TEXT,PHONE_NO VARCHAR, EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR,BALANCE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TRANSACTION_ID VARCHAR, DATE VARCHAR,SENDER_ID VARCHAR,RECEIVER_ID VARCHAR,AMOUNT VARCHAR,STATUS TEXT)");

        db.execSQL("insert into user_info values(1, 'Neeta', 'neeta@gmail.com','9876543210', 'XXXXXX0010', 'ABCD1234','23456.00')");
        db.execSQL("insert into user_info values(2, 'Nidhi', 'nidhi@gmail.com', '1234567890','XXXXXX0011', 'PQRS1234','52245.45')");
        db.execSQL("insert into user_info values(3, 'Rutu', 'rutu@gmail.com', '7410852963','XXXXXX0012', 'ABCD1234','98745.66')");
        db.execSQL("insert into user_info values(4, 'Mahek','mahek@gmail.com', '9638527410','XXXXXX0013', 'XYZW1234','40022.77')");
        db.execSQL("insert into user_info values(5, 'Shreya', 'shreya@gmail.com', '7894561230','XXXXXX0014', 'ABCD1234','92133.88')");
        db.execSQL("insert into user_info values(6, 'Rutvi', 'rutvi@gmail.com', '3216549870','XXXXXX0015', 'LMNO1234','66666.99')");
        db.execSQL("insert into user_info values(7, 'Jinkal', 'jinkal@gmail.com', '7539514682','XXXXXX0016', 'ABCD1234','77777.33')");
        db.execSQL("insert into user_info values(8, 'Meera', 'meera@gmail.com','1230456789', 'XXXXXX0017', 'EFGH1234','12345.22')");
        db.execSQL("insert into user_info values(9, 'Janki', 'janki@gmail.com', '9876543210','XXXXXX0018', 'ABCD1234','50055.11')");
        db.execSQL("insert into user_info values(10, 'Priya', 'priya@gmail.com', '7854269098','XXXXXX0019', 'KTVP1234','90005.12')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_info", null);
        return cursor;
    }

    public Cursor readparticulardata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_info where id = " +id, null);
        return cursor;
    }

    public Cursor readselectuserdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_info except select * from user_info where id = " +id, null);
        return cursor;
    }

    public void updateAmount(String id, Double amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_info set balance = " + amount + " where id = " +id);
    }

    public Cursor readtransferdata(String transactionId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table where TRANSACTION_ID = " + transactionId, null);
        return cursor;
    }


    public Cursor readperticulartransferdata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table where SENDER_ID = " + id +" OR RECEIVER_ID = "+ id +" ORDER BY id DESC ", null);
        return cursor;
    }


    public boolean insertTransferData(String Transaction_id, String date, String SenderID, String ReceiverID, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TRANSACTION_ID", Transaction_id);
        contentValues.put("DATE", date);
        contentValues.put("SENDER_ID", SenderID);
        contentValues.put("RECEIVER_ID", ReceiverID);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1,null,  contentValues );
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
