package com.nuwulf.cal4school

/*Use this one for readBlock that outputs using ArrayList<calModel>*/
//class calModel(_id: Int, Monday: String, Tuesday: String, Wednesday: String, Thursday: String, Friday: String)

/*Use this one for readBlock that outputs using calModel?*/
class CalModel{
    private var id: Int = 0
    private var Monday: String
    private var Tuesday: String
    private var Wednesday: String
    private var Thursday: String
    private var Friday: String

    constructor(
        id: Int,
        Monday: String,
        Tuesday: String,
        Wednesday: String,
        Thursday: String,
        Friday: String
    ) {
        this.id = id
        this.Monday = Monday
        this.Tuesday = Tuesday
        this.Wednesday = Wednesday
        this.Thursday = Thursday
        this.Friday = Friday
    }
    fun getVal(num:Int): String{
        if(num == 0){
            return id.toString()
        } else if(num == 1){
            return Monday
        }else if(num == 2){
            return Tuesday
        }else if(num == 3){
            return Wednesday
        }else if(num == 4){
            return Thursday
        }else if(num == 5){
            return Friday
        }
        return "Null"
    }


    /*constructor(
        id: Int,
        Monday: String?,
        Tuesday: String?,
        Wednesday: String?,
        Thursday: String?,
        Friday: String?
    ) {
        this.id = id
        this.Monday = Monday
        this.Tuesday = Tuesday
        this.Wednesday = Wednesday
        this.Thursday = Thursday
        this.Friday = Friday
    }*/


    override fun toString(): String {
        return "CalModel(_id=$id, Monday=$Monday, Tuesday=$Tuesday, Wednesday=$Wednesday, Thursday=$Thursday, Friday=$Friday)"
    }
    /*fun CalModel(
        _id: Int,
        Monday: String,
        Tuesday: String,
        Wednesday: String,
        Thursday: String,
        Friday: String){
        this._id = _id
        this.Monday = Monday
        this.Tuesday = Tuesday
        this.Wednesday = Wednesday
        this.Thursday = Thursday
        this.Friday = Friday

    }*/
}