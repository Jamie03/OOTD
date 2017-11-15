package com.example.pat4008.ootd;

/**
 * Created by Pat4008 on 14/11/2017.
 */

public class Tags {

    public static final String TABLE_NAME = "tags";
    public static final String COLUMN_TAGSID = "_tagid";   //all primary key has _
    public static final String COLUMN_CLOTHESID = "clothesid";
    public static final String COLUMN_TAG = "tag";

    private long tagid;
    private long clothesid;
    private String tag;

    public Tags(long clothesid, String tag) {
        this.clothesid = clothesid;
        this.tag = tag;
    }

    public long getTagid() {
        return tagid;
    }

    public void setTagid(long tagid) {
        this.tagid = tagid;
    }

    public long getClothesid() {
        return clothesid;
    }

    public void setClothesid(long clothesid) {
        this.clothesid = clothesid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
