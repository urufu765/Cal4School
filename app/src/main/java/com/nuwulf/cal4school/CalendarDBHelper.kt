package com.nuwulf.cal4school

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

class CalendarDBHelper(context: Context): SQLiteOpenHelper(context, "calendarThings.db", null, 1){
    private val L = Logger.getLogger(PROTODontTouch::class.java.name)
    var myDataBase:SQLiteDatabase? = null
    val myContext = context

    override fun onCreate(db: SQLiteDatabase?) {
        /*val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MON + " TEXT," +
                    COLUMN_TUE + " TEXT," +
                    COLUMN_WED + " TEXT," +
                    COLUMN_THU + " TEXT," +
                    COLUMN_FRI + " TEXT)"
        db?.execSQL(SQL_CREATE_ENTRIES)*/
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    /**
     * either gets the existing DB or copies the DB from assets
     * @throws IOException
     */
    @Throws(IOException::class) fun importDB(){
        val dbExist:Boolean = checkDataBase()
        if(!dbExist){
            this.writableDatabase
            try{
                val myPath = myContext.getDatabasePath("$DATABASE_NAME.db")
                copyDataBase(myPath)
            }catch(e:IOException){
                throw Error("Error copying database")
            }
        }
    }

    /**
     * Checks if database exists
     *
     * @return boolean
     */
    fun checkDataBase():Boolean{
        var checkDB:SQLiteDatabase? = null
        try{
            val myPath = myContext.getDatabasePath("$DATABASE_NAME.db")
            checkDB = SQLiteDatabase.openDatabase(myPath.path, null, SQLiteDatabase.OPEN_READONLY)
            // error says that database cannot be found.
        } catch (e: SQLiteException){
            L.warning("checkdatabase caught")
        }
        return checkDB != null
    }

    /**
     * An inline function of a while function in JAVA
     */
    private inline fun <T> `while`(nextValue: () -> T, condition: (T) -> Boolean, body: (T) -> Unit) {
        var value = nextValue()
        while (condition(value)) {
            body(value)
            value = nextValue()
        }
    }

    /**
     * Opens the database
     *
     * @throws RuntimeException
     * @return database opened from assets
     */
    fun openDataBase(): SQLiteDatabase{
        val myPath = myContext.getDatabasePath("$DATABASE_NAME.db")
        if(!myPath.exists()){
            try{
                val checkDB = myContext.openOrCreateDatabase("$DATABASE_NAME.db", Context.MODE_PRIVATE, null)
                checkDB.close()
                copyDataBase(myPath)
            }catch(e:IOException){
                throw RuntimeException("Error opening db")
            }
        }
        return SQLiteDatabase.openDatabase(myPath.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    /**
     * Copies the database for local use
     *
     * @throws IOException
     */
    @Throws(IOException::class) private fun copyDataBase(myPath: File){
        //val myInput:InputStream = myContext.assets.open(DATABASE_NAME)
        //val outFileName = DB_PATH + DATABASE_NAME
        //val myOutput: OutputStream = FileOutputStream(outFileName)
        val myOutput = FileOutputStream(myPath)
        val buffer = ByteArray(1024)
        val myInput = myContext.assets.open("$DATABASE_NAME.db")
        `while`({myInput.read(buffer)}, {it > 0}){length->
            myOutput.write(buffer, 0, length)
        }
        myOutput.flush()
        myOutput.close()
        myInput.close()
    }

    /**
     * reads the row in the local database
     *
     * @param num  period number
     * @return row
     */
    fun readBlock(num: String): CalModel?{
        val db = readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_PERIOD_ID = $num", null)
        } catch (e: SQLiteException){
            L.warning("CalendarDBHelper: readBlock> ERROR CAUGHT")
        }
        if(cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val results = CalModel(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PERIOD_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MON)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TUE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_WED)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_THU)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FRI))
                )
                cursor.moveToNext()
                return results
            }
        }
        cursor.close()
        return null
    }
    /*
    "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PERIOD_ID + "='" + periodNo + "'"*/

    /*This method requires the first calModel class that does not have a body.*/
    /*fun readBlock(_id:Int):ArrayList<CalModel>{
        L.warning("CalendarDBHelper: readBlock started")
        val periods = ArrayList<CalModel>()
        val db = readableDatabase
        L.warning("CalendarDBHelper: db set")
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery("SELECT  * FROM $TABLE_NAME WHERE $COLUMN_PERIOD_ID = $_id", null)
            L.warning("CalendarDBHelper: tried")
        } catch(e: SQLiteException){
            L.warning("CalendarDBHelper: caught")
            cursor?.close()
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        } catch(e: CursorIndexOutOfBoundsException){
            L.warning("out of range caught")
            cursor?.close()
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var Monday: String
        var Tuesday: String
        var Wednesday: String
        var Thursday: String
        var Friday: String
        if(cursor!!.moveToFirst()){
            while(cursor.isAfterLast == false){
                Monday = cursor.getString(cursor.getColumnIndex(COLUMN_MON))
                Tuesday = cursor.getString(cursor.getColumnIndex(COLUMN_TUE))
                Wednesday = cursor.getString(cursor.getColumnIndex(COLUMN_WED))
                Thursday = cursor.getString(cursor.getColumnIndex(COLUMN_THU))
                Friday = cursor.getString(cursor.getColumnIndex(COLUMN_FRI))
                L.warning("CalendarDBHelper:")
                L.warning(Monday+Tuesday+Wednesday+Thursday+Friday)
                periods.add(CalModel.constructor(_id, Monday, Tuesday, Wednesday, Thursday, Friday))
                cursor.moveToNext()
            }
        }
        L.warning(periods.toString())
        cursor.close()
        return periods
    }*/

    /**
     * Edits the designated block in the local database
     *
     * @param num  Row "period"
     * @param variable  new value
     * @param varcol  Column "day"
     * @return true if successful
     */
    fun editBlock(num:String, variable:String, varcol:String):Boolean{
        val db = this.writableDatabase
        val selection = "$COLUMN_PERIOD_ID LIKE ?"
        val selectionArgs = arrayOf(num)
        val newvariable = variable
        val vartype = varcol
        var values = ContentValues()
        //concept, so only monday is coded.
        when (vartype) {
            "mon" -> values = ContentValues().apply {
                put(COLUMN_MON, newvariable)
            }
            "tue" -> values = ContentValues().apply {
                put(COLUMN_TUE, newvariable)
            }
            "wed" -> values = ContentValues().apply {
                put(COLUMN_WED, newvariable)
            }
            "thu" -> values = ContentValues().apply {
                put(COLUMN_THU, newvariable)
            }
            "fri" -> values = ContentValues().apply {
                put(COLUMN_FRI, newvariable)
            }
        }
        db.update(
            TABLE_NAME,
            values,
            selection,
            selectionArgs)
        return true
    }

    /**
     * It closes stuff. What did you expect?
     */
    override fun close(){
        myDataBase?.close()
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "calendarThings"

        /*(private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MON + " TEXT," +
                    COLUMN_TUE + " TEXT," +
                    COLUMN_WED + " TEXT," +
                    COLUMN_THU + " TEXT," +
                    COLUMN_FRI + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.PeriodEntry.TABLE_NAME*/
    }
}