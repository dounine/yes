package com.dounine.yes.core.example.method

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.request.RequestMethod

class UploadMethod(example: Example) : EntityEnclosingMethod(example,RequestMethod.POST) {

    fun addFileData(vararg fileDatas: FileData): UploadMethod{

        return this
    }

}