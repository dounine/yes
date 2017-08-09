package com.dounine.yes.core.postman.example

import com.dounine.yes.core.example.method.Cookie
import com.dounine.yes.core.postman.Header
import com.dounine.yes.core.postman.Request
import java.util.*
import kotlin.collections.ArrayList

class Response {

    private val id:String = UUID.randomUUID().toString()
    private val name:String
    private lateinit var originalRequest:Request
    private val status:String
    private val code:Int
    private val postmanPreviewLanguage:PreviewLanguage
    private val postmanPreviewType:String
    private var header:ArrayList<Header> = ArrayList()
    private var cookie:ArrayList<Cookie> = ArrayList()
    private val responseTime:Int
    private val body:String

    constructor(name:String,
                originalRequest:Request,
                status:String = CodeStatus.C200.value,
                code:Int = 200,
                postmanPreviewLanguage:PreviewLanguage = PreviewLanguage.auto,
                postmanPreviewType:String = "parsed",
                header:ArrayList<Header> = ArrayList(),
                cookie:ArrayList<Cookie> = ArrayList(),
                responseTime:Int = 0,
                body:String = ""
                ){
        this.name = name
        this.originalRequest =originalRequest
        this.status =status
        this.code = code
        this.postmanPreviewLanguage = postmanPreviewLanguage
        this.postmanPreviewType = postmanPreviewType
        this.header = header
        this.cookie = cookie
        this.responseTime = responseTime
        this.body = body
    }


    fun getId():String = this.id
    fun getName():String = this.name
    fun getOriginalRequest():Request = this.originalRequest
    fun getStatus():String = this.status
    fun getCode():Int = this.code
    fun get__postman_previewlanguage():PreviewLanguage = this.postmanPreviewLanguage
    fun get__postman_previewtype():String = this.postmanPreviewType
    fun getHeader():ArrayList<Header> = this.header
    fun getCookie():ArrayList<Cookie> = this.cookie
    fun getResponseTime():Int = this.responseTime
    fun getBody():String = this.body

}
