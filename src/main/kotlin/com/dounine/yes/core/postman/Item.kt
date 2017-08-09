package com.dounine.yes.core.postman

import com.dounine.yes.core.postman.example.Response

class Item {
    private lateinit var name:String
    private lateinit var request:Request
    private var response:ArrayList<Response> = ArrayList()

     fun getName(): String = this.name

    fun setName(name:String){
        this.name = name
    }

     fun getRequest(): Request = this.request

    fun setRequest(request:Request){
        this.request = request
    }

    fun addResponse(response:Response){
        this.response.add(response)
    }

     fun getResponse(): List<Response> = this.response

    fun setResponse(response: ArrayList<Response>){
        this.response =response
    }
}