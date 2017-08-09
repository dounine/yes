package com.dounine.yes.core.postman

import org.apache.http.NameValuePair


object UrlEncodedUtils {

    fun format(values:ArrayList<NameValuePair>):String{
        var sb:StringBuffer = StringBuffer()
        if(values.size>0){
            sb = StringBuffer("?")
        }
        for(nvp in values){
            if(sb.length>1){
                sb.append("&")
            }
            sb.append(nvp.name+"={"+nvp.value+"}")
        }
        return sb.toString()
    }
}