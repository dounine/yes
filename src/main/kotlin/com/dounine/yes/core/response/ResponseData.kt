package com.dounine.yes.core.response

import com.dounine.yes.core.request.DataType
import com.dounine.yes.core.request.Parameter

class ResponseData {

    private lateinit var name:String
    private var type: DataType = DataType.String
    private var des:String = ""
    private var value:Any = ""
    private var require:Boolean = false
    private var constraint:String = ""

    fun name(name:String): ResponseData {
        this.name = name
        return this
    }

    fun type(type: DataType): ResponseData {
        this.type = type
        return this
    }

    fun des(des:String): ResponseData {
        this.des = des
        return this
    }

    fun value(value:Any): ResponseData {
        this.value =value
        return this
    }

    fun require(require:Boolean = true): ResponseData {
        this.require = require
        return this
    }

    fun constraint(con:String): ResponseData {
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