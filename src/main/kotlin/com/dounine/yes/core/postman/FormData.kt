package com.dounine.yes.core.postman

class FormData{


    private lateinit var key:String
    private lateinit var value:String
    private var src:String? = null
    private lateinit var type:FormDataType
    private lateinit var description:String
    private var disabled:Boolean? = null

    constructor(key:String,value:String,src:String?,type:FormDataType,description:String,disabled:Boolean?){
        this.key = key
        this.value = value
        this.src = src
        this.type = type
        this.description = description
        this.disabled = disabled
    }


     fun getKey(): String = this.key

     fun getValue(): String = this.value

    /**
     * 在FormData=file情况下使用
     */
     fun getSrc(): String? = this.src

     fun getType(): FormDataType = this.type

     fun getDescription(): String = this.description

     fun getDisabled(): Boolean? = this.disabled
}