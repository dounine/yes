package com.dounine.yes.core.example

import com.dounine.yes.core.example.method.BaseMethod
import org.apache.http.HttpResponse
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.protocol.HttpContext

interface Example {

    fun name(): String

    fun method(): BaseMethod

    fun expect(response: HttpResponse,httpContext: HttpClientContext): Expect

}