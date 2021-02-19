package com.nuwulf.cal4school

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class EventDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    @Throws(SQLiteConstraintException :: class)
    fun insertEvent(events: eveModel): Boolean{
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.EventEntry.COLUMN_ID, events.num)
        values.put(DBContract.EventEntry.COLUMN_STATUS, events.status)
        values.put(DBContract.EventEntry.COLUMN_PRIORITY, events.priority)
        values.put(DBContract.EventEntry.COLUMN_NAME, events.name)
        values.put(DBContract.EventEntry.COLUMN_DUE_DATE, events.duedate)
        values.put(DBContract.EventEntry.COLUMN_DUE_TIME, events.duetime)
        values.put(DBContract.EventEntry.COLUMN_REMINDER, events.reminder)
        values.put(DBContract.EventEntry.COLUMN_NOTES, events.note)

        val newRowId = db.insert(DBContract.EventEntry.TABLE_NAME, null, values)
        return true
    }
    @Throws(SQLiteConstraintException :: class)
    fun deleteEvent(num:String):Boolean{
        val db = writableDatabase
        val selection = DBContract.EventEntry.COLUMN_ID + " LIKE ?"
        val selectionArgs = arrayOf(num)
        db.delete(DBContract.EventEntry.TABLE_NAME, selection, selectionArgs)
        return true
    }
    fun readEvent(num:String):ArrayList<eveModel>{
        val events = ArrayList<eveModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery("select * from " + DBContract.EventEntry.TABLE_NAME + " WHERE " + DBContract.EventEntry.COLUMN_ID + "='" + num + "'", null)
        } catch(e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var status: String
        var priority: String
        var name: String
        var duedate: String
        var duetime: String
        var reminder: String
        var notes: String
        if(cursor!!.moveToFirst()){
            while(!cursor.isAfterLast){
                status = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_STATUS))
                priority = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_PRIORITY))
                name = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_NAME))
                duedate = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_DUE_DATE))
                duetime = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_DUE_TIME))
                reminder = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_REMINDER))
                notes = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_NOTES))
                events.add(eveModel(num, status, priority, name, duedate, duetime, reminder, notes))
                cursor.moveToNext()
            }
        }
        return events
    }
    fun readAllEvent():ArrayList<eveModel>{
        val events = ArrayList<eveModel>()
        val db = writableDatabase
        var cursor : Cursor? = null
        try{
            cursor = db.rawQuery("select * from " + DBContract.EventEntry.TABLE_NAME, null)
        } catch (e:SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var num: String
        var status: String
        var priority: String
        var name: String
        var duedate: String
        var duetime: String
        var reminder: String
        var notes: String
        if(cursor!!.moveToFirst()){
            while(!cursor.isAfterLast){
                num = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_ID))
                status = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_STATUS))
                priority = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_PRIORITY))
                name = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_NAME))
                duedate = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_DUE_DATE))
                duetime = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_DUE_TIME))
                reminder = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_REMINDER))
                notes = cursor.getString(cursor.getColumnIndex(DBContract.EventEntry.COLUMN_NOTES))

                events.add(eveModel(num, status, priority, name, duedate, duetime, reminder, notes))
                cursor.moveToNext()
            }
        }
        return events
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = ""

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.EventEntry.TABLE_NAME + " (" +
                        DBContract.EventEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                        DBContract.EventEntry.COLUMN_STATUS + " TEXT," +
                        DBContract.EventEntry.COLUMN_PRIORITY + " TEXT," +
                        DBContract.EventEntry.COLUMN_NAME + " TEXT," +
                        DBContract.EventEntry.COLUMN_DUE_DATE + " TEXT," +
                        DBContract.EventEntry.COLUMN_DUE_TIME + " TEXT," +
                        DBContract.EventEntry.COLUMN_REMINDER + " TEXT," +
                        DBContract.EventEntry.COLUMN_NOTES + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.EventEntry.TABLE_NAME
    }
}