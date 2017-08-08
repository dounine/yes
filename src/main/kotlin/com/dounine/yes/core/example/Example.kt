package com.dounine.yes.core.example

import com.dounine.yes.core.example.method.BaseMethod
import org.apache.http.HttpResponse

interface Example {

    fun name(): String

    fun method(): BaseMethod

    fun expect(response: HttpResponse): Expect

}