package com.dounine.yes.core.example.method

import java.io.File

class FileData {
    private var name:String
    private var file:File? = null
    private var disabled:Boolean
    private var url:String = ""

    constructor(name:String,file:File,disabled:Boolean = false){
        this.disabled =disabled
        this.name = name
        this.file =file
    }

    constructor(name:String,url:String,disabled:Boolean = false){
        this.disabled =disabled
        this.name = name
        this.url = url
    }

    fun getName():String{
        return this.name
    }

    fun getFile():File?{
        return this.file
    }

    fun getUrl():String{
        return this.url
    }

    fun getDisabled():Boolean{
        return disabled
    }
}