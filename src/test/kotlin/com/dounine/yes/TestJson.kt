package com.dounine.yes

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream

class TestJson {

    @Test
    fun testFastJsonField(){
        var jsonStr:String = "{\"name\":\"lake\",\"headers\":[{\"name\":\"header1\"},{\"name\":\"header2\"}],\"obj\":{\"name\":\"objName\"}}"
        var jb:JSONObject = JSON.parseObject(jsonStr)
        println((jb.get("obj") as JSONObject).get("name"))
        println((jb.get("headers") as JSONArray).get(0))
    }

    @Test
    fun testYaml(){
        var yaml:Yaml = Yaml()
        var oos: Iterable<Any> = yaml.loadAll(FileInputStream("/home/lake/github/fastdoc/src/test/kotlin/com/dounine/fastdoc/subsectionPath.yml"))
        for(o in oos){
            println(o)
        }
    }
}