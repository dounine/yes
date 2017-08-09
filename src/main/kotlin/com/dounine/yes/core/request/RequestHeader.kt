package com.dounine.yes.core.request

class RequestHeader {

    private lateinit var name:String
    private var type:DataType = DataType.String
    private var des:String = "无"
    private var value:Any = ""
    private var require:Boolean = false
    private var constraint:String = "无"

    fun name(name:String):RequestHeader{
        this.name = name
        return this
    }

    fun type(type:DataType):RequestHeader{
        this.type = type
        return this
    }

    fun des(des:String):RequestHeader{
        this.des = des
        return this
    }

    fun value(value:Any):RequestHeader{
        this.value = value
        return this
    }

    fun require():RequestHeader{
        this.require = true
        return this
    }

    fun constraint(con:String):RequestHeader{
        this.constraint = con
        return this
    }

    fun getName():String = this.name
    fun getDes():String = this.des
    fun getType():DataType = this.type
    fun getRequire():Boolean = this.require
    fun getConstraint():String = this.constraint
    fun getValue():Any = this.value
}