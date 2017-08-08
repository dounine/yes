package com.dounine.yes.core.request

class Segment {

    private lateinit var name:String
    private var type:DataType = DataType.String
    private var des:String = ""
    private var constraint:String = ""

    fun name(name:String):Segment{
        return this
    }

    fun type(type:DataType):Segment{
        return this
    }

    fun des(des:String):Segment{
        return this
    }

    fun constraint(con:String):Segment{
        this.constraint = con
        return this
    }

}


