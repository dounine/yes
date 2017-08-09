package com.dounine.yes.core.example

import com.dounine.yes.core.example.expect.BodyCallback
import com.dounine.yes.core.example.expect.HeaderCallback
import com.dounine.yes.core.example.expect.LengthCallback
import com.dounine.yes.core.example.expect.StatusCallback
import com.dounine.yes.core.example.express.BaseExpressEx
import com.dounine.yes.core.exception.YesDocException
import com.dounine.yes.core.postman.example.PreviewLanguage
import org.apache.http.HttpResponse
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.protocol.HttpContext
import org.apache.http.util.EntityUtils
import java.io.InputStream

class Expect {

    private val body: String
    private val length: Long
    private val status: Int
    private val contentType: PreviewLanguage
    private val response: HttpResponse
    private val httpContext:HttpClientContext
    private val input: InputStream?

    constructor(response: HttpResponse,httpContext:HttpClientContext) {
        this.response = response
        this.httpContext = httpContext
        this.body = EntityUtils.toString(response.entity)
        this.length = response.entity.contentLength
        this.status = response.statusLine.statusCode
        var ct:String = response.entity.contentType.value
        if(ct.contains("application/json")){
            contentType = PreviewLanguage.json
        }else if(ct.contains("text/html")){
            contentType = PreviewLanguage.text
        }else if(ct.contains("xml")){
            contentType = PreviewLanguage.xml
        }else if(ct.contains("html")){
            contentType = PreviewLanguage.html
        }else{
            contentType = PreviewLanguage.auto
        }
        this.input = null
    }

    fun status(callback: StatusCallback):Expect{

        return this
    }

    fun length(callback: LengthCallback):Expect{
        return this
    }

    fun body(callback: BodyCallback):Expect{
        var exprStr:String = BaseExpressEx(callback.jsonExpress()).expressStr(this.body)
        if (!callback.result().toString().equals(exprStr)) {
            throw YesDocException("期望为${callback.result()},实际为${exprStr}")
        }
        return this
    }

    fun getBody():String{
        return this.body
    }

    fun getContentType():PreviewLanguage{
        return this.contentType
    }

    fun getCode():Int{
        return this.status
    }

    fun getResponse():HttpResponse{
        return this.response
    }

    fun getHttpContext():HttpClientContext{
        return this.httpContext
    }

    fun headers(callback: HeaderCallback):Expect{
        return this
    }

}