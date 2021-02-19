package com.nuwulf.cal4school

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Selection.setSelection
import android.view.Gravity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_prototype.*
import java.io.IOException
import java.sql.SQLException
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var calendarDBHelper : CalendarDBHelper

    //period arrays(when adding new stuff, make sure to add stuff to strings.xml as well)
    /*
    private var socialScience = arrayOf("Psychology", "Sociology", "Business Management", "Economics", "Non-contact")
    private var science = arrayOf("Physics", "Biology", "Non-contact")
    private var english = arrayOf("English", "Non-contact")
    private var mathematics = arrayOf("Mathematics", "Math Studies", "Non-contact")
    private var altScience = arrayOf("Music", "Computer Science", "Art", "Chemistry", "Biology", "Physics", "Non-contact")
    private var altLan = arrayOf("Spanish", "Off-site", "Non-contact")
    private var physicalEducation = arrayOf("Physical Education", "Physical Endurance", "Public Execution")
    private var TOK = arrayOf("Theory Of Knowledge", "Theory of Knowledge", "Theory Of knowledge", "Theory of knowledge", "theory Of Knowledge", "theory of Knowledge", "theory Of knowledge", "theory of knowledge")
    */
    private val Log = Logger.getLogger(MainActivity::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // calendarDBHelper = CalendarDBHelper(this)
        calendarDBHelper = importDatabase(context = this)

        //buttons
        val eventsbtn = findViewById<Button>(R.id.eventsbtn)
        val protobtn = findViewById<Button>(R.id.protobtn)
        val editbtn = findViewById<Button>(R.id.editbtn)
        protobtn.isClickable = true
        protobtn.visibility = View.VISIBLE
        Log.warning("buttons assigned")

        completeSpinners()

        //Button to go to Prototype activity
        /*
        TODO: hide prototype button at beta release or replace with secret game button
         */
        protobtn.setOnClickListener{
            Log.warning("button function initialized")
            val toProto =  Intent(this, PROTODontTouch::class.java)
            Log.warning("button intented")
            startActivity(toProto)
            Log.warning("Done.")
        }

        //Button to go to events activity
        /*
        TODO: program in events
         */
        eventsbtn.setOnClickListener{
            Log.warning("button function initialized")
            Toast.makeText(this, "This function is not available in the pre-alpha release", Toast.LENGTH_SHORT).show()
            //val totheevent =  Intent(this, EventActivity::class.java)
            //Log.warning("button intented")
            //startActivity(totheevent)
            Log.warning("Done.")
        }

        //Button to go to the edit function
        /*
        TODO: Find out what this button was supposed to do
         */
        editbtn.setOnClickListener{
            Log.warning("button function initialized")
            Toast.makeText(this, "This function is not available in the pre-alpha release", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent?.getItemAtPosition(position).toString()=="Non-contact") {
            when(parent?.id) {
                R.id.monSocialScience -> calendarDBHelper.editBlock(
                    "1",
                    parent.getItemAtPosition(position).toString(),
                    "mon"
                )
                R.id.monMathematics -> calendarDBHelper.editBlock(
                    "3",
                    parent.getItemAtPosition(position).toString(),
                    "mon"
                )
                R.id.monTOK -> calendarDBHelper.editBlock(
                    "4",
                    parent.getItemAtPosition(position).toString(),
                    "mon"
                )
                R.id.monScience -> calendarDBHelper.editBlock(
                    "5",
                    parent.getItemAtPosition(position).toString(),
                    "mon"
                )
                R.id.monAltScience -> calendarDBHelper.editBlock(
                    "7",
                    parent.getItemAtPosition(position).toString(),
                    "mon"
                )

                R.id.tueEnglish -> calendarDBHelper.editBlock(
                    "1",
                    parent.getItemAtPosition(position).toString(),
                    "tue"
                )
                R.id.tueAltLan -> calendarDBHelper.editBlock(
                    "3",
                    parent.getItemAtPosition(position).toString(),
                    "tue"
                )
                R.id.tueScience -> calendarDBHelper.editBlock(
                    "5",
                    parent.getItemAtPosition(position).toString(),
                    "tue"
                )
                R.id.tueMathematics -> calendarDBHelper.editBlock(
                    "6",
                    parent.getItemAtPosition(position).toString(),
                    "tue"
                )
                R.id.tueSocialScience -> calendarDBHelper.editBlock(
                    "7",
                    parent.getItemAtPosition(position).toString(),
                    "tue"
                )

                R.id.wedPhysicalEducation -> calendarDBHelper.editBlock(
                    "1",
                    parent.getItemAtPosition(position).toString(),
                    "wed"
                )
                R.id.wedAltScience -> calendarDBHelper.editBlock(
                    "2",
                    parent.getItemAtPosition(position).toString(),
                    "wed"
                )
                R.id.wedAltLan -> calendarDBHelper.editBlock(
                    "4",
                    parent.getItemAtPosition(position).toString(),
                    "wed"
                )
                R.id.wedEnglish -> calendarDBHelper.editBlock(
                    "5",
                    parent.getItemAtPosition(position).toString(),
                    "wed"
                )
                R.id.wedMathematics -> calendarDBHelper.editBlock(
                    "6",
                    parent.getItemAtPosition(position).toString(),
                    "wed"
                )

                R.id.thuSocialScience -> calendarDBHelper.editBlock(
                    "1",
                    parent.getItemAtPosition(position).toString(),
                    "thu"
                )
                R.id.thuEnglish -> calendarDBHelper.editBlock(
                    "2",
                    parent.getItemAtPosition(position).toString(),
                    "thu"
                )
                R.id.thuScience -> calendarDBHelper.editBlock(
                    "3",
                    parent.getItemAtPosition(position).toString(),
                    "thu"
                )
                R.id.thuAltScience -> calendarDBHelper.editBlock(
                    "4",
                    parent.getItemAtPosition(position).toString(),
                    "thu"
                )
                R.id.thuAltLan -> calendarDBHelper.editBlock(
                    "5",
                    parent.getItemAtPosition(position).toString(),
                    "thu"
                )
                R.id.thuTOK -> calendarDBHelper.editBlock(
                    "6",
                    parent.getItemAtPosition(position).toString(),
                    "thu"
                )

                R.id.friAltScience -> calendarDBHelper.editBlock(
                    "1",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
                R.id.friSocialScience -> calendarDBHelper.editBlock(
                    "2",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
                R.id.friScience -> calendarDBHelper.editBlock(
                    "3",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
                R.id.friAltLan -> calendarDBHelper.editBlock(
                    "4",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
                R.id.friMathematics -> calendarDBHelper.editBlock(
                    "5",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
                R.id.friEnglish -> calendarDBHelper.editBlock(
                    "6",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
                R.id.friPhysicalEducation -> calendarDBHelper.editBlock(
                    "7",
                    parent.getItemAtPosition(position).toString(),
                    "fri"
                )
            }
        }
        else {
            val theOne: Int = when (parent?.id) {
                R.id.monSocialScience -> 1
                R.id.monMathematics -> 2
                R.id.monTOK -> 7
                R.id.monScience -> 3
                R.id.monAltScience -> 4

                R.id.tueEnglish -> 5
                R.id.tueAltLan -> 6
                R.id.tueScience -> 3
                R.id.tueMathematics -> 2
                R.id.tueSocialScience -> 1

                R.id.wedPhysicalEducation -> 8
                R.id.wedAltScience -> 4
                R.id.wedAltLan -> 6
                R.id.wedEnglish -> 5
                R.id.wedMathematics -> 2

                R.id.thuSocialScience -> 1
                R.id.thuEnglish -> 5
                R.id.thuScience -> 3
                R.id.thuAltScience -> 4
                R.id.thuAltLan -> 6
                R.id.thuTOK -> 7

                R.id.friAltScience -> 4
                R.id.friSocialScience -> 1
                R.id.friScience -> 3
                R.id.friAltLan -> 6
                R.id.friMathematics -> 2
                R.id.friEnglish -> 5
                R.id.friPhysicalEducation -> 8

                else -> 0
            }
            val theOnly: MutableList<MutableList<String>> = ArrayList()
            when (theOne) {
                0 -> Log.warning("No Adapter Found!")
                1 -> {/*social science*/
                    theOnly.add(mutableListOf("1", "mon"))
                    monSocialScience.setSelection(position, false)
                    theOnly.add(mutableListOf("7", "tue"))
                    tueSocialScience.setSelection(position, false)
                    theOnly.add(mutableListOf("1", "thu"))
                    thuSocialScience.setSelection(position, false)
                    theOnly.add(mutableListOf("2", "fri"))
                    friSocialScience.setSelection(position, false)
                }
                2 -> {/*mathematics*/
                    theOnly.add(mutableListOf("3", "mon"))
                    monMathematics.setSelection(position, false)
                    theOnly.add(mutableListOf("6", "tue"))
                    tueMathematics.setSelection(position, false)
                    theOnly.add(mutableListOf("6", "wed"))
                    wedMathematics.setSelection(position, false)
                    theOnly.add(mutableListOf("5", "fri"))
                    friMathematics.setSelection(position, false)
                }
                3 -> {/*science*/
                    theOnly.add(mutableListOf("5", "mon"))
                    monScience.setSelection(position, false)
                    theOnly.add(mutableListOf("5", "tue"))
                    tueScience.setSelection(position, false)
                    theOnly.add(mutableListOf("3", "thu"))
                    thuScience.setSelection(position, false)
                    theOnly.add(mutableListOf("3", "fri"))
                    friScience.setSelection(position, false)
                }
                4 -> {/*alternative science*/
                    theOnly.add(mutableListOf("7", "mon"))
                    monAltScience.setSelection(position, false)
                    theOnly.add(mutableListOf("2", "wed"))
                    wedAltScience.setSelection(position, false)
                    theOnly.add(mutableListOf("4", "thu"))
                    thuAltScience.setSelection(position, false)
                    theOnly.add(mutableListOf("1", "fri"))
                    friAltScience.setSelection(position, false)
                }
                5 -> {/*english*/
                    theOnly.add(mutableListOf("1", "tue"))
                    tueEnglish.setSelection(position, false)
                    theOnly.add(mutableListOf("5", "wed"))
                    wedEnglish.setSelection(position, false)
                    theOnly.add(mutableListOf("2", "thu"))
                    thuEnglish.setSelection(position, false)
                    theOnly.add(mutableListOf("6", "fri"))
                    friEnglish.setSelection(position, false)
                }
                6 -> {/*alt language*/
                    theOnly.add(mutableListOf("3", "tue"))
                    tueAltLan.setSelection(position, false)
                    theOnly.add(mutableListOf("4", "wed"))
                    wedAltLan.setSelection(position, false)
                    theOnly.add(mutableListOf("5", "thu"))
                    thuAltLan.setSelection(position, false)
                    theOnly.add(mutableListOf("4", "fri"))
                    friAltLan.setSelection(position, false)
                }
                7 -> {/*TOK*/
                    theOnly.add(mutableListOf("4", "mon"))
                    monTOK.setSelection(position, false)
                    theOnly.add(mutableListOf("6", "thu"))
                    thuTOK.setSelection(position, false)
                }
                8 -> {/*physical education*/
                    theOnly.add(mutableListOf("1", "wed"))
                    wedPhysicalEducation.setSelection(position, false)
                    theOnly.add(mutableListOf("7", "fri"))
                    friPhysicalEducation.setSelection(position, false)
                }
            }
            for (x in theOnly) {
                calendarDBHelper.editBlock(
                    x[0],
                    parent?.getItemAtPosition(position).toString(),
                    x[1]
                )
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.warning("Nothing selected")
    }

    /**
     * Makes spinners to use
     */
    private fun completeSpinners(){
        val socialScience = ArrayAdapter.createFromResource(
            this,
            R.array.setSocialScience,
            android.R.layout.simple_spinner_item
        ); socialScience.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val mathematics = ArrayAdapter.createFromResource(
            this,
            R.array.setMathematics,
            android.R.layout.simple_spinner_item
        ); mathematics.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val tOK = ArrayAdapter.createFromResource(
            this,
            R.array.setTOK,
            android.R.layout.simple_spinner_item
        ); tOK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val science = ArrayAdapter.createFromResource(
            this,
            R.array.setScience,
            android.R.layout.simple_spinner_item
        ); science.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val altScience = ArrayAdapter.createFromResource(
            this,
            R.array.setAltScience,
            android.R.layout.simple_spinner_item
        ); altScience.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val english = ArrayAdapter.createFromResource(
            this,
            R.array.setEnglish,
            android.R.layout.simple_spinner_item
        ); english.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val altLan = ArrayAdapter.createFromResource(
            this,
            R.array.setAltLan,
            android.R.layout.simple_spinner_item
        ); altLan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val physicalEducation = ArrayAdapter.createFromResource(
            this,
            R.array.setPhysicalEducation,
            android.R.layout.simple_spinner_item
        ); physicalEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Log.warning("adapters assigned")

        spinnerMaker(monSocialScience, socialScience, "1", 1)
        spinnerMaker(monMathematics, mathematics,"3", 1)
        spinnerMaker(monTOK,tOK,"4", 1)
        spinnerMaker(monScience,science,"5", 1)
        spinnerMaker(monAltScience,altScience, "7", 1)
        Log.warning("monday spinners assigned")

        spinnerMaker(tueEnglish,english,"1",2)
        spinnerMaker(tueAltLan,altLan,"3",2)
        spinnerMaker(tueScience,science,"5", 2)
        spinnerMaker(tueMathematics,mathematics,"6",2)
        spinnerMaker(tueSocialScience,socialScience,"7",2)
        Log.warning("tuesday spinners assigned")

        spinnerMaker(wedPhysicalEducation,physicalEducation,"1",3)
        spinnerMaker(wedAltScience,altScience,"2", 3)
        spinnerMaker(wedAltLan,altLan,"4", 3)
        spinnerMaker(wedEnglish,english,"5", 3)
        spinnerMaker(wedMathematics,mathematics,"6", 3)
        Log.warning("wednesday spinners assigned")

        spinnerMaker(thuSocialScience,socialScience,"1",  4)
        spinnerMaker(thuEnglish,english,"2", 4)
        spinnerMaker(thuScience,science, "3", 4)
        spinnerMaker(thuAltScience,altScience,"4", 4)
        spinnerMaker(thuAltLan,altLan, "5", 4)
        spinnerMaker(thuTOK,tOK, "6", 4)
        Log.warning("thursday spinners assigned")

        spinnerMaker(friAltScience,altScience, "1", 5)
        spinnerMaker(friSocialScience,socialScience, "2",5)
        spinnerMaker(friScience,science,"3", 5)
        spinnerMaker(friAltLan,altLan, "4", 5)
        spinnerMaker(friMathematics,mathematics, "5",5)
        spinnerMaker(friEnglish,english,"6", 5)
        spinnerMaker(friPhysicalEducation,physicalEducation,"7", 5)
        Log.warning("friday spinners assigned")
    }

    private fun spinnerMaker(spinner: Spinner, adapter:ArrayAdapter<CharSequence>, period:String, day:Int){
        spinner.adapter = adapter
        with(spinner){
            var pos = 0
            try{
                pos = adapter.getPosition(calendarDBHelper.readBlock(period)?.getVal(day))
                Log.warning("┌MainActivity.kt>>>spinnerMaker-->getPosition()\n└->pos = $pos\n")
            } catch(e:Exception){
                Log.warning("┌MainActivity.kt>>>spinnerMaker-->getPosition()\n└->Unable to get position\n")
                throw e
            }
            setSelection(pos)
            onItemSelectedListener = this@MainActivity
            gravity = Gravity.CENTER
        }

    }


    /**
     * Uses the method editBlock in calendarDBHelper to replace the value in the database
     *
     * @param period  String number indicating the row
     * @param newval  String text value to replace that place
     * @param day  String day that indicates the column
     */
    /*
    private fun getAndSet(period:String, newval:String, day:String){
        calendarDBHelper.editBlock(period, newval, day)
    }
    */

    /**
     * Imports and opens database using the methods in CalendarDBHelper
     *
     * @return Database
     */
    private fun importDatabase(context: Context):CalendarDBHelper{
        val myDbHelper = CalendarDBHelper(context)
        try{
            myDbHelper.importDB()
        } catch (ioe: IOException){
            Log.warning("┌MainActivity.kt>>>importDatabase-->importDB()\n└->Unable to create database\n")
            throw ioe
        }
        try{
            myDbHelper.openDataBase()
        } catch(sqle: SQLException){
            Log.warning("┌MainActivity.kt>>>importDatabase-->openDataBase()\n└->Unable to open database\n")
            throw sqle
        }
        return myDbHelper
    }

}
