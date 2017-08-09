package com.dounine.yes.core.postman

class Query {
    private var key:String
    private var value:String = ""
    private val equals:Boolean = true
    private var description:String = ""
    private var disabled:Boolean? = null

    constructor(key:String,value:String,description:String,disabled:Boolean?){
        this.key = key
        this.value =value
        this.description =description
        this.disabled = disabled
    }


     fun getKey(): String {
        return this.key
    }

     fun getValue(): String {
        return this.value
    }

     fun getEquals(): Boolean {
        return this.equals
    }

     fun getDescription(): String {
        return this.description
    }

     fun getDisabled(): Boolean? {
        return this.disabled
    }
}