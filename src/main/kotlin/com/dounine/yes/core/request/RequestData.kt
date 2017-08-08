package com.dounine.yes.core.request

class RequestData {

    private lateinit var name:String
    private var type:DataType = DataType.String
    private var des:String = ""
    private var require:Boolean = false
    private var constraint:String = ""

    fun name(name:String):RequestData{
        return this
    }

    fun type(type:DataType):RequestData{
        return this
    }

    fun des(des:String):RequestData{
        return this
    }

    fun require():RequestData{
        this.require = true
        return this
    }

    fun constraint(con:String):RequestData{
        this.constraint = con
        return this
    }
}