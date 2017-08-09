package com.dounine.yes.core.doc

import com.dounine.yes.core.exception.YesDocException
import com.dounine.yes.core.request.*
import com.dounine.yes.core.response.ResponseData
import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class MasterDoc {
    private val name: String
    private val des: String
    private val url: String
    private val method: RequestMethod
    private val segments: List<Segment>
    private val parameters: List<Parameter>
    private val requestHeaders: List<RequestHeader>
    private val requestDatas: List<RequestData>
    private val responseDatas: List<ResponseData>

    constructor(
            url: String,
            method: RequestMethod,
            name: String,
            des: String = "",
            segments: List<Segment> = ArrayList(),
            parameters: List<Parameter> = ArrayList(),
            requestHeaders: List<RequestHeader> = ArrayList(),
            requestDatas: List<RequestData> = ArrayList(),
            responseDatas: List<ResponseData> = ArrayList()
    ) {
        this.url = url
        this.method = method
        this.name = name
        this.des = des
        this.parameters = parameters
        this.segments = segments
        this.requestHeaders = requestHeaders
        this.requestDatas = requestDatas
        this.responseDatas = responseDatas
    }

    fun print() {
        println(this.name)
        println(this.des)
        println("Request Url")
        println(this.method.name+" "+handleUrl())
        println()
        println("Request Parameters")
        handlerParameter()
        println()
        println("Request Headers")
        handlerHeader()
        println()
        println("Request Datas")
        handlerRequestData()
        println()
        println("Response Datas")
        handlerResponseData()
    }

    fun handlerHeader() {
        println("name---------type---------require---------con---------des")
        for (header in requestHeaders) {
            println("${header.getName()}---------${header.getType()}---------${header.getRequire()}---------${header.getConstraint()}---------${header.getDes()}")
        }
    }

    fun handlerParameter() {
        println("name---------type---------require---------con---------des")
        for (parameter in parameters) {
            println("${parameter.getName()}---------${parameter.getType()}---------${parameter.getRequire()}---------${parameter.getConstraint()}---------${parameter.getDes()}")
        }
    }

    fun handlerRequestData() {
        println("name---------type---------require---------con---------des")
        for (requestData in requestDatas) {
            println("${requestData.getName()}---------${requestData.getType()}---------${requestData.getRequire()}---------${requestData.getConstraint()}---------${requestData.getDes()}")
        }
    }

    fun handlerResponseData() {
        println("name---------type---------require---------con---------des")
        for (responseData in responseDatas) {
            println("${responseData.getName()}---------${responseData.getType()}---------${responseData.getRequire()}---------${responseData.getConstraint()}---------${responseData.getDes()}")
        }
    }

    fun handleUrl(): String {
        return UrlUtils.handleUrl(this.url,this.segments)
    }
}