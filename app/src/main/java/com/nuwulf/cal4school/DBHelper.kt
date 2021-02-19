package com.nuwulf.cal4school


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.COLUMN_FRI
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.COLUMN_MON
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.COLUMN_PERIOD_ID
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.COLUMN_THU
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.COLUMN_TUE
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.COLUMN_WED
import com.nuwulf.cal4school.DBContract.PeriodEntry.Companion.TABLE_NAME
import java.io.*
import java.sql.SQLException
import java.util.logging.Logger


class DBHelper(context: Context) : SQLiteOpenHelper(context,"calendarThings.db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {}

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    val mContext = context

    companion object {

        //general
        val dbVersion = 1
        val dbName = "calendarThings.db"

    }

    fun open(): SQLiteDatabase {
        val dbFile = mContext.getDatabasePath("$dbName")
        if (!dbFile.exists()){
            try {
                val checkDB = mContext.openOrCreateDatabase("$dbName", Context.MODE_PRIVATE,null)
                checkDB.close()
                copyDatabase(dbFile)
            }catch (e:IOException){
                throw RuntimeException("Error opening db")
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun checkDB():Boolean{
        var db:SQLiteDatabase? = null
        try {
            val dbFile = mContext.getDatabasePath("$dbName")
            db = SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READONLY)
        }catch (e:SQLException){}
        return db != null
    }

    @Throws(IOException::class)
    private fun copyDB(dbFile: File){

        val mo = FileOutputStream(dbFile)
        val bfr = ByteArray(1024)
        val mi = mContext.assets.open("$dbName")
        while (mi.read(bfr) > 0) {
            mo.write(bfr, 0, mi.read(bfr))
        }
        mi.close()
        mo.flush()
        mo.close()
    }

    @Throws(IOException::class)
    fun importDB() {
        val ex = checkDB()
        if (!ex) {
            this.readableDatabase
            try {
                val dbFile = mContext.getDatabasePath("$dbName.db")
                copyDB(dbFile)
            } catch (e: IOException) { }
        }
    }

    var database:SQLiteDatabase? = null

    private fun copyDatabase(dbFile: File) {
        val iss = mContext.assets.open("$dbName.db")
        val os = FileOutputStream(dbFile)

        val buffer = ByteArray(1024)
        while (iss.read(buffer) > 0) {
            os.write(buffer)
        }
        os.flush()
        os.close()
        iss.close()
    }

    override fun close() {
        database?.close()
    }


    /*This method requires the second calModel class that has a body*/
    /*fun readBlock(num: Int): CalModel? {
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $COLUMN_PERIOD_ID = ?"
        db.rawQuery(selectQuery, arrayOf(num.toString())).use { // .use requires API 16
            if (it.moveToFirst()) {
                return CalModel(
                    it.getInt(it.getColumnIndex(COLUMN_PERIOD_ID)),
                    it.getString(it.getColumnIndex(COLUMN_MON)),
                    it.getString(it.getColumnIndex(COLUMN_TUE)),
                    it.getString(it.getColumnIndex(COLUMN_WED)),
                    it.getString(it.getColumnIndex(COLUMN_THU)),
                    it.getString(it.getColumnIndex(COLUMN_FRI)))
            }
        }
        return null
    }*/
}