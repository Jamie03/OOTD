package com.example.pat4008.ootd;

import android.graphics.Bitmap;

/**
 * Created by Pat4008 on 14/11/2017.
 */

public class Clothes {

    public static final String TABLE_NAME = "clothes";
    public static final String COLUMN_CLOTHESID = "_clothesid";   //all primary key has _
    //public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_SHADE = "shade";

    private long clothesid;
   // private Bitmap image;
    private String type;
    private String color;
    private String shade;

    public Clothes(String type, String color, String shade) {
        this.type = type;
        this.color = color;
        this.shade = shade;
    }

//    public Clothes(Bitmap image, String type, String color, String shade) {
//        this.image = image;
//        this.type = type;
//        this.color = color;
//        this.shade = shade;
//    }

    public long getId() {
        return clothesid;
    }

    public void setId(long id) {
        this.clothesid = clothesid;
    }

//    public Bitmap getImage() {
//        return image;
//    }
//
//    public void setImage(Bitmap image) {
//        this.image = image;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShade() {
        return shade;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }
}
