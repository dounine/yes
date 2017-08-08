package com.dounine.yes.action

import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.OutputStream
import java.net.URLEncoder
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
class TestAct {

    @GetMapping("hello")
    fun hello(): String {
        return "world"
    }

    @GetMapping("hello/{username}")
    fun hello(@PathVariable username: String): String {
        return username
    }

    @GetMapping("result/{username}")
    fun username(@PathVariable username: String): Result {
        return ActResult("你vb")
    }

    @PostMapping("result/post/{username}")
    fun usernamePost(@PathVariable username: String,role:String,age:Int): Result {
        return ActResult(role+age)
    }

    @GetMapping("result/get/cookie")
    fun usernamePost(@CookieValue name:String): Result {

        return ActResult(name)
    }

    @GetMapping("result/get/header")
    fun usernameHeader(@RequestHeader name:String): Result {

        return ActResult(name)
    }

    @PostMapping("result/file")
    fun usernamePost(username:String,@RequestParam("files") multiFile:Array<MultipartFile>): Result {
        if (!multiFile.isEmpty()) {
            for(mf in multiFile){
                FileUtils.copyInputStreamToFile(mf.inputStream,File("/home/lake/github/fastdoc/build/"+mf.originalFilename))
            }
        }
        return ActResult(username)
    }

    @GetMapping("result/build.gradle")
    fun usernamePost(response:HttpServletResponse) {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("build.gradle","UTF-8"));
        var fis:FileInputStream = FileInputStream(File("/home/lake/github/fastdoc/build/build.gradle"))
        IOUtils.copy(fis,response.outputStream)
    }

    @PutMapping("result/put/{username}")
    fun usernamePut(@PathVariable username: String): Result {
        return ActResult(User("lake",23,Arrays.asList(Cuser("广东",Arrays.asList(10,22))),Cuser("bb",Arrays.asList(10,22))))
    }

    @PatchMapping("result/patch/{username}")
    fun usernamePatch(@PathVariable username: String,role:String,age:Int): Result {
        return ActResult(User("lake",23, Arrays.asList(Cuser("广东",Arrays.asList(10,22))),Cuser("bb",Arrays.asList(10,22))))
    }

    @DeleteMapping("result/delete/{username}")
    fun usernameDelete(@PathVariable username: String): Result {
        return ActResult("success")
    }

    @RequestMapping(name = "result/trace/{username}",method = arrayOf(RequestMethod.TRACE))
    fun usernameTrace(@PathVariable username: String): Result {
        return ActResult("success")
    }

    @RequestMapping(name = "result/head/{username}",method = arrayOf(RequestMethod.HEAD))
    fun usernameHead(@PathVariable username: String): Result {
        return ActResult("success")
    }

    @RequestMapping(value = "result/options/{username}",method = arrayOf(RequestMethod.OPTIONS))
    fun usernameOption(@PathVariable username: String): Result {
        return ActResult("success")
    }

}