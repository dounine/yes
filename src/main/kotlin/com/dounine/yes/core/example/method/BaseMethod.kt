package com.dounine.yes.core.example.method

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.example.Expect
import com.dounine.yes.core.example.client.ExampleClient
import com.dounine.yes.core.request.RequestMethod
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpOptions
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.util.EntityUtils
import java.util.*

open class BaseMethod {

    protected val method: RequestMethod
    protected val example: Example
    protected var parameters: ArrayList<ExParameter> = ArrayList()
    protected var segments: ArrayList<ExSegment> = ArrayList()
    protected var headers: ArrayList<Header> = ArrayList()
    protected var cookies: ArrayList<Cookie> = ArrayList()
    protected var exampleClient: ExampleClient = ExampleClient()

    constructor(example: Example,method: RequestMethod) {
        this.example = example
        this.method = method
    }

    fun addParameter(vararg parameters: ExParameter): BaseMethod {
        this.parameters.addAll(parameters)
        return this
    }

    fun addSegment(vararg segments: ExSegment): BaseMethod {
        this.segments.addAll(segments)
        return this
    }

    fun addHeader(vararg headers: Header): BaseMethod {
        this.headers.addAll(headers)
        return this
    }

    fun addCookie(vararg cookies: Cookie): BaseMethod {
        this.cookies.addAll(cookies)
        return this
    }


    open fun execute(uri: String): Expect {
        var httpContext: HttpClientContext = HttpClientContext.create()

        var httpRequest: HttpRequestBase
        var urlPath = uri
        var url: String = exampleClient.fillSegments(segments, urlPath)
        url = exampleClient.fillParameters(parameters, url)

        if (this.method.equals(RequestMethod.GET)) {
            httpRequest = HttpGet(url)
        } else if (this.method.equals(RequestMethod.DELETE)) {
            httpRequest = HttpDelete(url)
        } else {
            httpRequest = HttpOptions(url)
        }

        exampleClient.fillHeader(headers, httpRequest)
        exampleClient.fillCookie(cookies, httpContext)

        var response: HttpResponse = ExampleClient.HTTP_CLIENT.execute(httpRequest, httpContext)

        return example.expect(response)
    }

}