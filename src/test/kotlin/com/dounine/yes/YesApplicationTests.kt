package com.dounine.yes

import com.dounine.yes.core.doc.YesDoc
import com.dounine.yes.core.example.Example
import com.dounine.yes.core.example.Expect
import com.dounine.yes.core.example.expect.BodyCallback
import com.dounine.yes.core.example.method.*
import com.dounine.yes.core.request.*
import com.dounine.yes.core.response.ResponseData
import org.apache.http.HttpResponse
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.protocol.HttpContext
import org.junit.Before
import org.junit.Test

class YesApplicationTests {

    var yesDoc: YesDoc = YesDoc()

    @Before
    fun init() {
        yesDoc.setAppName("用户模块测试")
        yesDoc.setGroupName("用户模块测试")
        yesDoc.init(prefixPath = "http://localhost:8080")
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun testGetMethod() {
        yesDoc
                .request(RequestMethod.PUT, url = "/result/put/{username}")
                .segment(Segment().name("username").des("用户名").type(DataType.String))
                .parameter(Parameter().name("age").des("年龄").type(DataType.Number))
                .rpDoc()
                .config("list","我是list描述")
                .requestHeader(RequestHeader().name("token").des("令牌").require())
                .requestData(RequestData().name("age").des("多大了").constraint("10岁以上"))
                .requestData(RequestData().name("address").des("地扯").type(DataType.String))
                .responseData(ResponseData().name("uuid").des("编号").type(DataType.String))
                .responseData(ResponseData().name("address").des("地扯").type(DataType.String))
                .example(object : Example {

                    override fun name(): String = "这是list功能"

                    override fun method(): BaseMethod = PutMethod(this)
                            .addData(Data("address","广东"))
                            .addHeader(Header("token","445646-4546-146456"))
                            .addParameter(ExParameter("age", 18))
                            .addSegment(ExSegment("username", "lake"))

                    override fun expect(response: HttpResponse,httpContext: HttpClientContext): Expect {
                        var expect: Expect = Expect(response,httpContext)
                        expect.body(object : BodyCallback {
                            override fun result(): Any = 10
                            override fun jsonExpress(): String = "data.cu.ages[0]"
                        })
                        return expect
                    }
                })
                .done()
//                .printDoc()
                .postMan()


    }

}
