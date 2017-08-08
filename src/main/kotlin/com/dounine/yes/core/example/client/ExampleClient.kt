package com.dounine.yes.core.example.client

import com.dounine.yes.core.example.method.*
import org.apache.http.client.HttpClient
import org.apache.http.client.config.CookieSpecs
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.cookie.BasicClientCookie2
import org.apache.http.message.BasicHeader
import java.net.URI
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ExampleClient {
    companion object {
        val COOKIE_REQUEST_CONFIG = RequestConfig.custom()
                .setSocketTimeout(3 * 1000)
                .setConnectTimeout(3 * 1000)
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .build()
        val HTTP_CLIENT: HttpClient = HttpClients.custom()
                .setDefaultRequestConfig(COOKIE_REQUEST_CONFIG)
                .build()
        val VAL_PATTERN: Pattern = Pattern.compile("[{][a-zA-Z0-9_$]+[}]")
    }

    fun fillSegments(segments: ArrayList<ExSegment>,url:String):String{
        if(segments.size>0){
            var match: Matcher = VAL_PATTERN.matcher(url)
            var listStr = ArrayList<String>()

            while (match.find()) {
                listStr.add(match.group())
            }

            var argsSize: Int = segments.size
            var listSize: Int = listStr.size
            var replaceUrl: String = url

            if (argsSize > 0) {
                if (listSize != argsSize) {
                    throw RuntimeException("参数列表不匹配,${listSize}预参,${argsSize}实参")
                }
                var matchCount: Int = 0
                for (i in 0..listSize - 1) {
                    var ov: Optional<ExSegment> = segments.stream().filter({ a -> "{${a.name}}".equals(listStr.get(i)) }).findFirst()
                    if (ov.isPresent) {
                        replaceUrl = replaceUrl.replace(listStr.get(i), ov.get().value.toString())
                        matchCount++
                    }
                }
                if (listSize != matchCount) {
                    throw RuntimeException("参数列表不匹配,${listSize}预参,${matchCount}匹配实参")
                }
            }
            return replaceUrl
        }
        return url
    }

    fun fillParameters(parameters: ArrayList<ExParameter>,url:String):String{
        if(parameters.size>0){
            var uri: URI = URI(url)
            var sb:StringBuilder = StringBuilder(uri.toString())
            var _ps:List<ExParameter> = ArrayList(parameters)
            if(null==uri.query){
                sb.append("?${_ps.get(0).name}=${_ps.get(0).value}")
                _ps = _ps.subList(1,_ps.size)
            }
            for(p in _ps){
                sb.append("&${p.name}=${p.value}")
            }
            return sb.toString()
        }
        return url
    }

    fun fillHeader(headers: ArrayList<Header>,httpRequest: HttpRequestBase){
        if(headers.size>0){
            for(header in headers){
                httpRequest.setHeader(BasicHeader(header.name,header.value.toString()))
            }
        }
    }

    fun fillCookie(cookies: ArrayList<Cookie>, httpContext: HttpClientContext){
        if(cookies.size>0){
            var nameCookie: BasicCookieStore
            if(null!=httpContext.cookieStore){
                nameCookie = httpContext.cookieStore as BasicCookieStore
            }else{
                nameCookie = BasicCookieStore()
            }
            for(d in cookies){
                var bcc: BasicClientCookie2 = BasicClientCookie2(d.name,d.value.toString())
                bcc.domain = d.domain
                nameCookie.addCookie(bcc)
            }
            if(null==httpContext.cookieStore){
                httpContext.cookieStore = nameCookie
            }
        }
    }
}