package com.dounine.yes.core.postman

import com.dounine.yes.core.exception.YesDocException
import java.util.*
import kotlin.collections.ArrayList

object CodeUtils {
    val codes: MutableList<ArrayList<Any>>? = Arrays.asList(
            arrayListOf(100,"Continue"),
            arrayListOf(101,"Switching Protocols"),
            arrayListOf(102,"Processing (WebDAV) (RFC 2518)"),
            arrayListOf(103,"Checkpoint"),
            arrayListOf(122,"Request-URI too long"),
            arrayListOf(200,"OK"),
            arrayListOf(201,"Created"),
            arrayListOf(202,"Accepted"),
            arrayListOf(203,"Non-Authoritative Information (since HTTP/1.1)"),
            arrayListOf(204,"No Content"),
            arrayListOf(205,"Reset Content"),
            arrayListOf(302,"Found"),
            arrayListOf(304,"Not Modified"),
            arrayListOf(400,"Bad Request"),
            arrayListOf(401,"Unauthorized"),
            arrayListOf(402,"Payment Required"),
            arrayListOf(403,"Forbidden"),
            arrayListOf(404,"Not Found"),
            arrayListOf(405,"Method Not Allowed"),
            arrayListOf(406,"Not Acceptable"),
            arrayListOf(500,"Internal Server Error"),
            arrayListOf(502,"Bad Gateway"),
            arrayListOf(504,"Gateway Timeout")
    )

    fun getStatus(code:Int):String{
        if (codes != null) {
            for(a in codes){
                if(a.get(0).equals(code)){
                    return a.get(1).toString()
                }
            }
        }
        throw YesDocException("没有找到对应的http编码")
    }
}