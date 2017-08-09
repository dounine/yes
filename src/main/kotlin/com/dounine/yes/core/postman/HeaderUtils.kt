package com.dounine.yes.core.postman

import com.dounine.yes.core.request.RequestHeader

object HeaderUtils {
    fun getHeader(headers:List<RequestHeader>):List<Header>{
        var pmHeaders:ArrayList<Header> = ArrayList(headers.size)
        for(header in headers){
            pmHeaders.add(Header(key = header.getName(),
                    value = header.getValue(),
                    description = header.getDes()))
        }
        return pmHeaders
    }
}