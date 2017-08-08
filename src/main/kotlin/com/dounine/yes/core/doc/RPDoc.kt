package com.dounine.yes.core.doc

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.request.RequestData
import com.dounine.yes.core.response.ResponseData

class RPDoc {

    private val yesDoc: YesDoc
    private var requestDatas: ArrayList<RequestData> = ArrayList()
    private var responseDatas: ArrayList<ResponseData> = ArrayList()
    private var examples:ArrayList<Example> = ArrayList()

    constructor(yesDoc: YesDoc) {
        this.yesDoc = yesDoc
    }


    fun requestData(data: RequestData): RPDoc {
        this.requestDatas.add(data)
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

    private fun sendRequest(){
        var url:String = yesDoc.getPrefixUrl()+yesDoc.getUrl()
        for(example in examples){
            example.method().execute(url)
        }
    }

    fun postMan(): RPDoc {

        return this
    }

}