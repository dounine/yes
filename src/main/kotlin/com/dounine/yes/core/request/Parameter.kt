package com.dounine.yes.core.request


class Parameter {

    private lateinit var name:String
    private var type:DataType = DataType.String
    private var des:String = ""
    private var require:Boolean = false
    private var constraint:String = ""

    fun name(name:String):Parameter{
        return this
    }

    fun type(type:DataType):Parameter{
        return this
    }

    fun des(des:String):Parameter{
        return this
    }

    fun require():Parameter{
        this.require = true
        return this
    }

    fun constraint(con:String):Parameter{
        this.constraint = con
        return this
    }

}