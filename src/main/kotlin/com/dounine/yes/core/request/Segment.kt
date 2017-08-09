package com.dounine.yes.core.request

class Segment {

    private lateinit var name:String
    private var type:DataType = DataType.String
    private var des:String = ""
    private var value:Any = ""
    private var constraint:String = ""

    fun name(name:String):Segment{
        this.name = name
        return this
    }

    fun type(type:DataType):Segment{
        this.type = type
        return this
    }

    fun des(des:String):Segment{
        this.des = des
        return this
    }

    fun value(value:Any):Segment{
        this.value =value
        return this
    }

    fun constraint(con:String):Segment{
        this.constraint = con
        return this
    }

    fun getName():String = this.name
    fun getDes():String = this.des
    fun getValue():Any = this.value

}


