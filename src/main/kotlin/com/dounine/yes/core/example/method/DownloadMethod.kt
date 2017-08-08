package com.dounine.yes.core.example.method

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.request.RequestMethod

class DownloadMethod(example: Example) : EntityEnclosingMethod(example,RequestMethod.GET) {

    fun downloadPath(path: String): DownloadMethod{

        return this
    }

}