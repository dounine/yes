package com.dounine.yes.core.response

import com.dounine.yes.core.request.DataType
import com.dounine.yes.core.request.Parameter

class ResponseData {

    private lateinit var name:String
    private var type: DataType = DataType.String
    private var des:String = ""
    private var require:Boolean = false
    private var constraint:String = ""

    fun name(name:String): ResponseData {
        return this
    }

    fun type(type: DataType): ResponseData {
        return this
    }

    fun des(des:String): ResponseData {
        return this
    }

    fun require(): ResponseData {
        this.require = true
        return this
    }

    fun constraint(con:String): ResponseData {
        this.constraint = con
        return this
    }

}