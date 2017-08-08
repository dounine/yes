package com.dounine.yes.core.doc

import com.dounine.yes.core.request.Parameter
import com.dounine.yes.core.request.RequestMethod
import com.dounine.yes.core.request.Segment


class YesDoc {
    private lateinit var method:RequestMethod
    private lateinit var url:String
    private var segments:ArrayList<Segment> = ArrayList()
    private var parameters:ArrayList<Parameter> = ArrayList()
    private var prefixPath:String = ""

    fun init(prefixPath:String = ""){
        this.prefixPath = prefixPath
    }

    fun request(method: RequestMethod,prefixPath:String = "", url:String):YesDoc{
        this.method = method
        this.url = url
        return this
    }

    fun segment(segment: Segment): YesDoc {
        this.segments.add(segment)
        return this
    }

    fun parameter(parameter: Parameter):YesDoc{
        this.parameters.add(parameter)
        return this
    }

    fun rpDoc():RPDoc{
        return RPDoc(this)
    }

    fun getMethod():RequestMethod = this.method
    fun getUrl():String = this.url
    fun getPrefixUrl():String = this.prefixPath
    fun getParameter():List<Parameter> = this.parameters
    fun getSegments():List<Segment> = this.segments
}