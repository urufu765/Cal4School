package com.nuwulf.cal4school

import android.provider.BaseColumns

object DBContract{
    class PeriodEntry: BaseColumns{
        companion object {
            val TABLE_NAME = "periods"
            val COLUMN_PERIOD_ID = "_id"
            val COLUMN_MON = "Monday"
            val COLUMN_TUE = "Tuesday"
            val COLUMN_WED = "Wednesday"
            val COLUMN_THU = "Thursday"
            val COLUMN_FRI = "Friday"
        }
    }
    class EventEntry: BaseColumns{
        companion object {
            val TABLE_NAME = "events"
            val COLUMN_ID = "_id"
            val COLUMN_STATUS = "status"
            val COLUMN_PRIORITY = "priority"
            val COLUMN_NAME = "name"
            val COLUMN_DUE_DATE = "duedate"
            val COLUMN_DUE_TIME = "duetime"
            val COLUMN_REMINDER = "reminder"
            val COLUMN_NOTES = "note"
        }
    }
}