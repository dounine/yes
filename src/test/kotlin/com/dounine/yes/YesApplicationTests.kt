package com.dounine.yes

import com.dounine.yes.core.doc.YesDoc
import com.dounine.yes.core.example.Example
import com.dounine.yes.core.example.Expect
import com.dounine.yes.core.example.expect.BodyCallback
import com.dounine.yes.core.example.method.*
import com.dounine.yes.core.request.*
import com.dounine.yes.core.response.ResponseData
import org.apache.http.HttpResponse
import org.junit.Before
import org.junit.Test

class YesApplicationTests {

    var yesDoc: YesDoc = YesDoc()

    @Before
    fun init() {
        yesDoc.init(prefixPath = "http://localhost:8080")
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun testGetMethod() {
        yesDoc
                .request(RequestMethod.PUT, url = "/result/put/{username}")
                .segment(Segment().name("username").des("描述").type(DataType.String))
                .parameter(Parameter().name("age").des("年龄").type(DataType.Number))
                .rpDoc()
                .requestData(RequestData().name("age").des("多大了").constraint("10岁以上"))
                .requestData(RequestData().name("address").des("多大了").type(DataType.String).des("地扯"))
                .responseData(ResponseData().name("uuid").des("编号").type(DataType.String).des("地扯"))
                .responseData(ResponseData().name("address").des("多大了").type(DataType.String).des("地扯"))
                .example(object : Example {

                    override fun name(): String = "这是list功能"

                    override fun method(): BaseMethod = PutMethod(this)
                            .addParameter(ExParameter("age", 18))
                            .addSegment(ExSegment("username", "lake"))

                    override fun expect(response: HttpResponse): Expect {
                        var expect: Expect = Expect(response)
                        expect.body(object : BodyCallback {
                            override fun result(): Any = 10
                            override fun jsonExpress(): String = "data.cu.ages[0]"
                        })
                        return expect
                    }
                })
                .done()
                .postMan()


    }

}
