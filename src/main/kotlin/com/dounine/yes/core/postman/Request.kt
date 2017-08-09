package com.dounine.yes.core.postman

import com.dounine.yes.core.request.RequestMethod

class Request {

    private lateinit var url: Any
    private var method: RequestMethod = RequestMethod.GET
    private var header: List<Header> = ArrayList()
    private var body: Body? = null
    private var description: String? = ""

    fun getUrl(): Any = this.url

    fun setUrl(url: Any) {
        this.url = url
    }

    fun getMethod(): RequestMethod = this.method

    fun setMethod(method: RequestMethod) {
        this.method = method
    }

    fun getHeader(): List<Header> = this.header

    fun setHeader(header: List<Header>) {
        this.header = header
    }

    fun getBody(): Body? = this.body

    fun setBody(body: Body?) {
        this.body = body
    }

    fun getDescription(): String? = this.description

    fun setDescription(des: String?) {
        this.description = des
    }
}