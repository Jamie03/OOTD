package com.example.pat4008.ootd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Pat4008 on 07/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "ootd";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }


    //never DB close on this one
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String clothes = "CREATE TABLE " + Clothes.TABLE_NAME + " ("
                + Clothes.COLUMN_CLOTHESID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                //+ Clothes.COLUMN_IMAGE + " BLOB,"
                + Clothes.COLUMN_TYPE + " TEXT,"
                + Clothes.COLUMN_COLOR + " TEXT"
                + Clothes.COLUMN_SHADE + " TEXT"
                + ");";

        String tags = "CREATE TABLE " + Tags.TABLE_NAME + " ("
                + Tags.COLUMN_TAGSID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Tags.COLUMN_CLOTHESID + " INTEGER,"
                + Tags.COLUMN_TAG + " TEXT"
                + ");";

        String tagnames = "CREATE TABLE " + Tagnames.TABLE_NAME + " ("
                + Tagnames.COLUMN_TAGNAME + " TEXT PRIMARY KEY"
                + ");";

        sqLiteDatabase.execSQL(clothes);
        sqLiteDatabase.execSQL(tags);
        sqLiteDatabase.execSQL(tagnames);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String clothes = "DROP TABLE IF EXISTS " + Clothes.TABLE_NAME + ";";
        String tags = "DROP TABLE IF EXISTS " + Clothes.TABLE_NAME + ";";
        String tagnames = "DROP TABLE IF EXISTS " + Clothes.TABLE_NAME + ";";

        sqLiteDatabase.execSQL(clothes);
        sqLiteDatabase.execSQL(tags);
        sqLiteDatabase.execSQL(tagnames);

        onCreate(sqLiteDatabase);
    }


    //<------------------------------ Adding Clothes ----------------------------------->

    public long addClothes(Clothes clothes, String alltags){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

//        byte[] image = getBitmapAsByteArray(clothes.getImage());
//
//        cv.put(Clothes.COLUMN_IMAGE, image);
        cv.put(Clothes.COLUMN_TYPE, clothes.getType());
        cv.put(Clothes.COLUMN_COLOR, clothes.getColor());
        cv.put(Clothes.COLUMN_SHADE, clothes.getShade());

        long id = db.insert(Clothes.TABLE_NAME, null, cv);

        addTags(alltags, id);
        db.close();
        return id;
    }

//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }


    //<------------------------------ Adding/Removing Tags ----------------------------------->

    public void addTags(String alltags, long id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv= new ContentValues();

        //String employee = "Smith,Katie,3014,,8.25,6.5,,,10.75,8.5";
        String delims = "[#]";
        String[] tags = alltags.split(delims);

        for(int i=0; i<tags.length; i++) {
            cv.put(Tags.COLUMN_CLOTHESID, id);
            //cv.put(Tags.COLUMN_TAG, tags[i].getTag());
            cv.put(Tags.COLUMN_TAG, tags[i]);
            db.insert(Tags.TABLE_NAME, null, cv);
        }
        db.close();
    }

    public void addTagname(Tagnames tagnames){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Tagnames.COLUMN_TAGNAME, tagnames.getTagname());

        long id = db.insert(Tagnames.TABLE_NAME, null, cv);

        db.close();

    }

    public boolean deleteTagname(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Tagnames.TABLE_NAME, Tagnames.COLUMN_TAGNAMESID + "=?", new String[] {id+""} );
        db.close();

        return rowsAffected > 0;
    }

    public long getLatestId(){
        long id=-1;

        return id;
    }








    //<-----------------------------VIEW DB inside for Debug ------------------>
    public ArrayList<Cursor> getData(String Query){
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);
        try{
            String maxQuery = Query ;
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            Cursor2.addRow(new Object[] { "Success" });
            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0,c);
                c.moveToFirst();
                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
    //<-----------------------------VIEW DB inside for Debug ------------------>

}
