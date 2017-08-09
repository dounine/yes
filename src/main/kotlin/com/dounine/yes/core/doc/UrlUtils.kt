package com.dounine.yes.core.doc

import com.dounine.yes.core.exception.YesDocException
import com.dounine.yes.core.postman.Query
import com.dounine.yes.core.postman.Url
import com.dounine.yes.core.postman.UrlEncodedUtils
import com.dounine.yes.core.request.Parameter
import com.dounine.yes.core.request.Segment
import org.apache.commons.lang3.StringUtils
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

object UrlUtils {

    val VARIABLE_PATTERN: Pattern = Pattern.compile("[{][a-zA-Z0-9_$]+[}]")

    fun getPostManUrl(url: String, segments: List<Segment>, parameters: List<Parameter>,replaceDesForValue:Boolean = false): Any {
        var parametersNV: ArrayList<NameValuePair> = ArrayList()
        for (parameter in parameters) {
            parametersNV.add(BasicNameValuePair(parameter.getName(), parameter.getDes()))
        }
        var url: URL = URL(url + UrlEncodedUtils.format(parametersNV))
        if (parameters.size > 0) {
            var querys: ArrayList<Query> = ArrayList(parameters.size)
            for (p in parameters) {
                var hasP: Optional<Parameter> = parameters.stream().filter({ s -> s.getName().equals(p.getName()) }).findFirst()
                if (hasP.isPresent) {
                    querys.add(Query(key = p.getName(), value = hasP.get().getValue().toString(), description = hasP.get().getDes(), disabled = null))
                } else {
                    querys.add(Query(key = p.getName(), value = "", description = "", disabled = null))
                }
            }
            var tmpPath:String = UrlUtils.handleUrl(url.toString(), segments);
            if(replaceDesForValue){
                for(p in parameters){
                    tmpPath = tmpPath.replace("{"+p.getDes()+"}",p.getValue().toString())
                }
            }
            var tmpUrl: URL = URL(tmpPath)
            var pmUrl: Url = Url(raw = tmpUrl.toString(),
                    host = Arrays.asList(tmpUrl.host),
                    port = tmpUrl.port,
                    protocol = tmpUrl.protocol,
                    path = tmpUrl.path.split("/"),
                    query = querys
            )
            return pmUrl
        } else {
            return UrlUtils.handleUrl(url.toString(), segments)
        }
    }

    fun handleUrl(url: String, segments: List<Segment>): String {
        var tmpUrl: String = url
        var matcher: Matcher = VARIABLE_PATTERN.matcher(tmpUrl)
        var variables: ArrayList<String> = ArrayList()
        var matchCount: Int = 0
        while (matcher.find()) {
            variables.add(matcher.group())
        }
        if (segments.size != variables.size) {
            throw YesDocException("segment 数量${segments.size}不匹配variable${variables.size}")
        }

        for (variable in variables) {
            var op: Optional<Segment> = segments.stream().filter({ s -> s.getName().equals(StringUtils.substring(variable, 1, -1)) }).findAny()
            if (op.isPresent) {
                matchCount++
                var desOrValue:String = ""
                if(""!=op.get().getValue()){
                    desOrValue = op.get().getValue().toString()
                }else if ("" != op.get().getDes()) {
                    desOrValue = "{" + op.get().getDes() + "}"
                }
                tmpUrl = tmpUrl.replace(variable, desOrValue)
            }
        }
        if (matchCount != segments.size) {
            throw YesDocException("segment 数量${segments.size}不匹配variable${variables.size}")
        }
        return tmpUrl
    }

}