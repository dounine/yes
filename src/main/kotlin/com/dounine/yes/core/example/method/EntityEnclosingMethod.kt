package com.dounine.yes.core.example.method

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.example.Expect
import com.dounine.yes.core.example.client.ExampleClient
import com.dounine.yes.core.request.RequestMethod
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

open class EntityEnclosingMethod(example: Example,method:RequestMethod) : BaseMethod(example,method) {

    protected var data:List<Data> = ArrayList()

    fun addData(vararg datas: Data):EntityEnclosingMethod {
        if(datas.size>0){
            var ds:ArrayList<Data> = ArrayList(data)
            for(d in datas){
                ds.add(d)
            }
            data = ds
        }
        return this
    }

    override fun execute(uri: String): Expect {
        var httpContext: HttpClientContext = HttpClientContext.create()

        var urlPath = uri
        var url: String = exampleClient.fillSegments(segments, urlPath)
        url = exampleClient.fillParameters(parameters, url)

        var httpRequest: HttpEntityEnclosingRequestBase
        if(this.method.equals(RequestMethod.POST)){
            httpRequest = HttpPost(url)
        }else if(this.method.equals(RequestMethod.PUT)){
            httpRequest = HttpPut(url)
        }else{
            httpRequest = HttpPatch(url)
        }
        if (data.size > 0) {
            val params: ArrayList<BasicNameValuePair> = ArrayList(data.size)
            for (pd in data) {
                params.add(BasicNameValuePair(pd.name, pd.value.toString()))
            }
            httpRequest.entity = UrlEncodedFormEntity(params)
        }

        exampleClient.fillHeader(headers,httpRequest)
        exampleClient.fillCookie(cookies,httpContext)

        var response: HttpResponse = ExampleClient.HTTP_CLIENT.execute(httpRequest, httpContext)

        return example.expect(response)

    }

}