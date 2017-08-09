package com.dounine.yes.core.postman

import com.dounine.yes.core.doc.RPDoc
import com.dounine.yes.core.doc.UrlUtils
import com.dounine.yes.core.doc.YesDoc
import com.dounine.yes.core.example.method.*
import com.dounine.yes.core.postman.example.ExampleData
import com.dounine.yes.core.postman.example.PreviewLanguage
import com.dounine.yes.core.postman.example.Response
import com.dounine.yes.core.request.*
import com.dounine.yes.core.response.ResponseData
import org.apache.http.HttpResponse
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.protocol.HttpContext
import java.util.*
import kotlin.collections.ArrayList

object ResponseUtils {

    fun getResponse(rpDoc: RPDoc, exampleDatas: List<ExampleData>): ArrayList<Response> {
        var yesDoc: YesDoc = rpDoc.getYesDoc()
        var responses: ArrayList<Response> = ArrayList()
        var urlPath: String = yesDoc.getPrefixUrl() + yesDoc.getUrl()

        for (exampleData in exampleDatas) {
            var request: Request = Request()
            var segmentList: List<Segment> = handlerSegment(exampleData.example.method().getSegments(), yesDoc.getSegments())
            var parameterList: List<Parameter> = handlerParameter(exampleData.example.method().getParameters(), yesDoc.getParameters())
            request.setUrl(UrlUtils.getPostManUrl(urlPath, segmentList, parameterList, true))
            request.setMethod(yesDoc.getMethod())

            request.setHeader(HeaderUtils.getHeader(handlerHeader(yesDoc, rpDoc, exampleData)))
            request.setDescription(null)
            request.setBody(handlerBody(exampleData, yesDoc.getMethod(), rpDoc.getResponseDatas()))

            responses.add(Response(
                    name = exampleData.example.name(),
                    originalRequest = request,
                    body = exampleData.expect.getBody(),
                    postmanPreviewLanguage = exampleData.expect.getContentType(),
                    code = exampleData.expect.getCode(),
                    status = CodeUtils.getStatus(exampleData.expect.getCode()),
                    header = getResponseHeaders(exampleData.expect.getResponse()),
                    cookie = getResponseCookies(exampleData.expect.getHttpContext())
            ))
        }


        return responses
    }

    private fun getResponseHeaders(response:HttpResponse):ArrayList<Header>{
        var hs:ArrayList<Header> = ArrayList()
        var headers:Array<org.apache.http.Header> =  response.allHeaders
        for(header in headers){
            hs.add(Header(
                    key = header.name,
                    value = header.value
            ))
        }
        return hs
    }

    private fun getResponseCookies(httpContext: HttpClientContext):ArrayList<Cookie>{
        var hs:ArrayList<Cookie> = ArrayList()
        var cookies: MutableList<org.apache.http.cookie.Cookie>? =  httpContext.cookieStore.cookies
        cookies?.let {
            for(cookie in cookies){
                var expiryDate:String = ""
                cookie.expiryDate?.let {
                    expiryDate = cookie.expiryDate.toString()
                }
                hs.add(Cookie(
                        name = cookie.name,
                        value = cookie.value,
                        path = cookie.path,
                        domain = cookie.domain,
                        expires = expiryDate,
                        secure = cookie.isSecure
                ))
            }
        }
        return hs
    }

    private fun handlerBody(exampleData: ExampleData, method: RequestMethod, responseDatas: List<ResponseData>): Body? {
        var body: Body? = null
        var convertMethod2: EntityEnclosingMethod? = null
        var convertMethod3: UploadMethod? = null

        if (exampleData.example.method() is EntityEnclosingMethod) {
            convertMethod2 = exampleData.example.method() as EntityEnclosingMethod
        } else if (exampleData.example.method() is UploadMethod) {
            convertMethod3 = exampleData.example.method() as UploadMethod
        }
        var list: ArrayList<ResponseData> = ArrayList()
        if (convertMethod2 != null) {
            for (data in convertMethod2.getDatas()) {
                var op: Optional<ResponseData> = responseDatas.stream().filter({ r -> r.getName().equals(data.name) }).findAny()
                if (op.isPresent) {
                    list.add(ResponseData().name(data.name).value(data.value).des(op.get().getDes()).type(op.get().getType()).require(data.disabled))
                }
            }
            body = BodyUtils.getBody(method, list)
        }
        if (null != convertMethod2 && convertMethod3 != null) {
            for (data in convertMethod3.getFileDatas()) {
                var op: Optional<ResponseData> = responseDatas.stream().filter({ r -> r.getName().equals(data.getName()) }).findAny()
                if (op.isPresent) {
                    list.add(ResponseData().name(data.getName()).value(data.getUrl()).des(op.get().getDes()).type(DataType.File).require(data.getDisabled()))
                }
            }
            body = BodyUtils.getBody(method, list)
        } else {
            if(null!=body){
                return body
            }
            body = BodyUtils.getBody(method, responseDatas)
        }

        return body
    }

    private fun handlerParameter(methodParameters: List<ExParameter>, docsParameters: List<Parameter>): List<Parameter> {
        var valueParameters: ArrayList<Parameter> = ArrayList()
        for (parameter in methodParameters) {
            var des: String = ""
            var op: Optional<Parameter> = docsParameters.stream().filter({ d -> d.getName().equals(parameter.name) }).findFirst()
            if (op.isPresent) {
                des = op.get().getDes()
            }
            valueParameters.add(Parameter().name(parameter.name).des(des).value(parameter.value))
        }

        return valueParameters
    }

    private fun handlerSegment(methodSegments: List<ExSegment>, docsSegments: List<Segment>): List<Segment> {
        var valueSegments: ArrayList<Segment> = ArrayList()
        for (segment in methodSegments) {
            var des: String = ""
            var op: Optional<Segment> = docsSegments.stream().filter({ d -> d.getName().equals(segment.name) }).findFirst()
            if (op.isPresent) {
                des = op.get().getDes()
            }
            valueSegments.add(Segment().name(segment.name).des(des).value(segment.value))
        }

        return valueSegments
    }

    private fun handlerHeader(yesDoc: YesDoc, rpDoc: RPDoc, exampleData: ExampleData): List<RequestHeader> {
        var baseMethod: BaseMethod = exampleData.example.method()
        var headerList: ArrayList<RequestHeader> = ArrayList()
        for (bm in baseMethod.getHeaders()) {
            var op: Optional<RequestHeader> = rpDoc.getRequestHeaders().stream().filter({ q -> q.getName().equals(bm.name) }).findAny()
            if (op.isPresent) {
                headerList.add(RequestHeader().name(bm.name).des(op.get().getDes()).value(bm.value))
            }
        }
        return headerList
    }
}