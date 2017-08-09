package com.dounine.yes.core.request

class RequestData {

    private lateinit var name:String
    private var type:DataType = DataType.String
    private var des:String = "无"
    private var require:Boolean = false
    private var constraint:String = "无"

    fun name(name:String):RequestData{
        this.name = name
        return this
    }

    fun type(type:DataType):RequestData{
        this.type = type
        return this
    }

    fun des(des:String):RequestData{
        this.des = des
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

    fun getName():String = this.name
    fun getDes():String = this.des
    fun getType():DataType = this.type
    fun getRequire():Boolean = this.require
    fun getConstraint():String = this.constraint
}