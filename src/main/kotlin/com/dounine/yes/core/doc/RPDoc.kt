package com.dounine.yes.core.doc

import com.alibaba.fastjson.JSON
import com.dounine.yes.core.example.Example
import com.dounine.yes.core.example.Expect
import com.dounine.yes.core.postman.*
import com.dounine.yes.core.postman.example.ExampleData
import com.dounine.yes.core.request.RequestData
import com.dounine.yes.core.request.RequestHeader
import com.dounine.yes.core.response.ResponseData
import java.util.*
import kotlin.collections.ArrayList

class RPDoc {

    private val yesDoc: YesDoc
    private var requestDatas: ArrayList<RequestData> = ArrayList()
    private var requestHeaders: ArrayList<RequestHeader> = ArrayList()
    private var responseDatas: ArrayList<ResponseData> = ArrayList()
    private var examples: ArrayList<Example> = ArrayList()
    private var exampleDatas:ArrayList<ExampleData> = ArrayList()
    private lateinit var name: String
    private var des: String = ""

    constructor(yesDoc: YesDoc) {
        this.yesDoc = yesDoc
    }


    fun requestData(data: RequestData): RPDoc {
        this.requestDatas.add(data)
        return this
    }

    fun requestHeader(header: RequestHeader): RPDoc {
        this.requestHeaders.add(header)
        return this
    }

    fun responseData(data: ResponseData): RPDoc {
        this.responseDatas.add(data)
        return this
    }

    fun example(example: Example): RPDoc {
        this.examples.add(example)
        return this
    }

    fun done(): RPDoc {
        sendRequest()
        return this
    }

    fun printDoc(): RPDoc {
        var masterDoc: MasterDoc = MasterDoc(
                url = yesDoc.getUrl(),
                method = yesDoc.getMethod(),
                name = this.name,
                des = this.des,
                parameters = this.yesDoc.getParameters(),
                segments = this.yesDoc.getSegments(),
                requestHeaders = this.requestHeaders,
                requestDatas = this.requestDatas,
                responseDatas = this.responseDatas
        )
        masterDoc.print()

        return this
    }

    private fun sendRequest() {
        var url: String = yesDoc.getPrefixUrl() + yesDoc.getUrl()

        for (example in examples) {
            var expect:Expect = example.method().execute(url)
            exampleDatas.add(ExampleData(example,expect))
        }
    }

    fun postMan(): RPDoc {
        var urlPath: String = yesDoc.getPrefixUrl() + yesDoc.getUrl()
        var postMan: PostMan = PostMan()
        var info: Info = Info()
        info.setName(yesDoc.getAppName())
        postMan.setInfo(info)
        var item: ItemGroup = ItemGroup()
        item.setName(yesDoc.getGroupName())

        var listItem: Item = Item()
        listItem.setName(name)

        var pmRequest: Request = Request()
        pmRequest.setUrl(UrlUtils.getPostManUrl(urlPath,yesDoc.getSegments(),yesDoc.getParameters()))
        pmRequest.setDescription(des)
        pmRequest.setMethod(yesDoc.getMethod())

        pmRequest.setHeader(HeaderUtils.getHeader(this.requestHeaders))
        pmRequest.setBody(BodyUtils.getBody(yesDoc.getMethod(),this.responseDatas))

        listItem.setRequest(pmRequest)
        listItem.setResponse(ResponseUtils.getResponse(this,exampleDatas))

        item.setItem(Arrays.asList(listItem))

        postMan.setItem(Arrays.asList(item))

        println(JSON.toJSONString(postMan, true))

        return this
    }

    fun config(name: String, des: String = ""): RPDoc {
        this.name = name
        this.des = des
        return this
    }

    fun getName(): String = this.name
    fun getDes(): String = this.des
    fun getYesDoc():YesDoc  = this.yesDoc
    fun getRequestDatas():List<RequestData> = this.requestDatas
    fun getResponseDatas():List<ResponseData> = this.responseDatas
    fun getRequestHeaders():List<RequestHeader> = this.requestHeaders

}