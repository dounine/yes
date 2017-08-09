package com.dounine.yes.core.doc

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.request.RequestData
import com.dounine.yes.core.request.RequestHeader
import com.dounine.yes.core.response.ResponseData

class RPDoc {

    private val yesDoc: YesDoc
    private var requestDatas: ArrayList<RequestData> = ArrayList()
    private var requestHeaders: ArrayList<RequestHeader> = ArrayList()
    private var responseDatas: ArrayList<ResponseData> = ArrayList()
    private var examples:ArrayList<Example> = ArrayList()
    private lateinit var name:String
    private var des:String = ""

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
//        sendRequest()
        return this
    }

    fun printDoc():RPDoc{
        var masterDoc:MasterDoc = MasterDoc(
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

    private fun sendRequest(){
        var url:String = yesDoc.getPrefixUrl()+yesDoc.getUrl()
        for(example in examples){
            example.method().execute(url)
        }
    }

    fun postMan(): RPDoc {

        return this
    }

    fun name(name:String,des:String = ""):RPDoc{
        this.name = name
        this.des = des
        return this
    }

    fun getName():String = this.name
    fun getDes():String = this.des

}