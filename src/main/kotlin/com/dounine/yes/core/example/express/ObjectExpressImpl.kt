package com.dounine.yes.core.example.express

import com.alibaba.fastjson.JSONObject
import com.dounine.yes.core.exception.YesDocException
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ObjectExpressImpl : BaseExpress {

    companion object {
        private val STRING_SINGLE_PATTERN: List<Pattern> = Arrays.asList(Pattern.compile("^[a-zA-Z0-9\$_]+$"),
                Pattern.compile("^[a-zA-Z0-9\$_]+[{]length[}]$"))
        private var NUMBER_PATTERN:Pattern = Pattern.compile("(?=\\{\\d+\\})\\d+")
    }

    protected var name: String = ""
    protected var queryLength:Boolean = false



    override fun name(): String {
        return name
    }

    override fun matcher(str: String): Boolean {
        if(STRING_SINGLE_PATTERN.stream().anyMatch({s -> s.matcher(str).find()})){
            if(str.indexOf("{")!=-1){
                name = str.substring(0, str.indexOf("{"))
                if(str.endsWith("{length}")){
                    this.queryLength = true
                }
            }else{
                name = str
            }
            return true
        }
        return false
    }

    override fun expressStr(responseStr: String, parentJsonFields: StringBuilder): String {
        if ("" == responseStr) {
            throw YesDocException("JsonField [ ${parentJsonFields.toString()}.${name} ]键不存在")
        }
        var jo: JSONObject = JSONObject.parseObject(responseStr)
        if (!jo.containsKey(name)) {
            throw YesDocException("JsonField [ ${parentJsonFields.toString()} ]键不存在")
        }
        var exprStr: String = jo.getString(name)
        if (parentJsonFields.length > 0) {
            parentJsonFields.append(".")
        }
        parentJsonFields.append(name)
        if(queryLength){
            return exprStr.length.toString()
        }
        return exprStr
    }

}