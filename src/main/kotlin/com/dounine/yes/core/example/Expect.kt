package com.dounine.yes.core.example

import com.dounine.yes.core.example.expect.BodyCallback
import com.dounine.yes.core.example.expect.HeaderCallback
import com.dounine.yes.core.example.expect.LengthCallback
import com.dounine.yes.core.example.expect.StatusCallback
import com.dounine.yes.core.example.express.BaseExpressEx
import com.dounine.yes.core.exception.YesDocException
import org.apache.http.HttpResponse
import org.apache.http.util.EntityUtils
import java.io.InputStream

class Expect {

    private val body: String
    private val length: Long
    private val status: Int
    private val response: HttpResponse
    private val input: InputStream?

    constructor(response: HttpResponse) {
        this.response = response
        this.body = EntityUtils.toString(response.entity)
        this.length = response.entity.contentLength
        this.status = response.statusLine.statusCode
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

    fun headers(callback: HeaderCallback):Expect{
        return this
    }

}