package com.dounine.yes.core.postman

class Body {

    private lateinit var mode:BodyMode
    private var formdata:List<FormData> = ArrayList()

    fun setMode(mode:BodyMode){
        this.mode = mode
    }

     fun getMode(): BodyMode = this.mode

    fun setFormdata(formdata:List<FormData>){
        this.formdata = formdata
    }

     fun getFormdata(): List<FormData> = this.formdata
}