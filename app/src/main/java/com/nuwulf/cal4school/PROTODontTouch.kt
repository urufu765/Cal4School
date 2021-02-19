package com.nuwulf.cal4school

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_prototype.*
import java.io.IOException
import java.sql.SQLException
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class PROTODontTouch : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var calendarDBHelper : CalendarDBHelper
    private val L = Logger.getLogger(PROTODontTouch::class.java.name)
    var periodArray = arrayOf("English", "Spanish", "Math", "Physics", "Chemistry", "Computer science", "Psychology", "PE")
    val NEW_SPINNER_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prototype)
        // calendarDBHelper = importDatabase(this)
        calendarDBHelper = importDatabase(context = this)

        L.warning("imported")
        L.warning(calendarDBHelper.readBlock("2")?.getVal(0).toString())
        // L.warning(calendarDBHelper.readBlock(2).toString())
        /*The above function returns an error(index out of bounds) and crashes app when using first calModel class
        * and returns "null" when using the second class. What should be returning is "Empty" for every column except the first one, which is the number.*/

        // textview test
        val textView = findViewById<TextView>(R.id.textView2)
        var dataBlock = calendarDBHelper.readBlock("2") //row 2
        //textView.setText(arraystuff.get(0).toString()).toString()
        /*Use this convertcalModel function only when using the second calModel class in CalModel.kt
        * on other usages replace "arraystuff" with "convertcalModel(arraystuff)"*/
        textView.setText(dataBlock?.getVal(1).toString()) //monday

        // spinner test
        L.warning("spinner")
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.setperiods, android.R.layout.simple_spinner_item)
        L.warning("set spinner")
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        L.warning("spinner applied")
        L.warning("Datablock: $dataBlock")
        spinnerMaker(spinner, adapter, 2, 1)
        /*
        spinner.adapter = adapter
        with(spinner){
            var pos:Int=0
            try{
                pos = periodArray.indexOf(dataBlock?.getVal(1))
                L.warning("Position1 $pos")
            } catch(e: Exception){
                L.warning("Spinner method> exception caught")
            }
            L.warning("Position2 $pos")
            setSelection(pos) //setSelection does nothing.
            onItemSelectedListener = this@PROTODontTouch
            prompt = "Select Period"
            gravity = Gravity.CENTER
        }
        */
        L.warning(adapter.toString())
        dataBlock = calendarDBHelper.readBlock("2")
        L.warning("spinner.adapter done")
    }
    private fun spinnerMaker(spinner: Spinner, adapter:ArrayAdapter<CharSequence>, period:Int, day:Int){
        spinner.adapter = adapter
        with(spinner){
            var pos = 0
            try{
                pos = adapter.getPosition(calendarDBHelper.readBlock("$period")?.getVal(day))
                L.warning("Position1 $pos")
            } catch(e:Exception){
                throw e
            }
            setSelection(pos)
            onItemSelectedListener = this@PROTODontTouch
            gravity = Gravity.CENTER
        }

    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
        val textView = findViewById<TextView>(R.id.textView2)
        when(view?.id){
            1 -> L.warning("Spinner position:${position} and period: ${periodArray[position]}")
            else -> {
                L.warning("Spinner position:${position} and period: ${periodArray[position]}")
            }
        }
        getSet("2", parent?.getItemAtPosition(position).toString(), "mon")
        L.warning("Stuff:"+parent?.getItemAtPosition(position).toString())
        textView.setText(calendarDBHelper.readBlock("2")?.getVal(1))
    }
    override fun onNothingSelected(parent: AdapterView<*>?){
        L.warning("Nothing selected")
    }

    private fun getSet(num:String, newval:String, day:String){
        L.warning("getSet initialized")
        L.warning(calendarDBHelper.readBlock(num)?.getVal(1))
        calendarDBHelper.editBlock(num, newval, day)
        L.warning(calendarDBHelper.readBlock(num)?.getVal(1))
        L.warning("edited")
    }

    /*THis method only used when the second class is used in calModel and needs to be converted to ArrayList<calModel?>*/
    /*private fun convertcalModel(calendario: Array<CalModel>?): ArrayList<CalModel>?{
        var arrayListCalendario:ArrayList<CalModel>? = arrayListOf()
        if (calendario != null) {
            for (item in calendario) {
                arrayListCalendario?.add(item)
            }
        }
        return arrayListCalendario
    }*/

    private fun importDatabase(context: Context):CalendarDBHelper{
        val myDbHelper = CalendarDBHelper(context)
        try{
            myDbHelper.importDB() // either get the existing DB or copying the DB from assets
        } catch (ioe:IOException){
            L.warning("importDatabase: Unable to create database")
            throw Error("Unable to create database")
        }
        try{
            myDbHelper.openDataBase()
        } catch(sqle: SQLException){
            throw sqle
        }
        return myDbHelper
    }

}