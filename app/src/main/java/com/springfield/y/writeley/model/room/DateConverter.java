package com.springfield.y.writeley.model.room;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

/**
 * converts dates from Date to Long and back
 */
public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
