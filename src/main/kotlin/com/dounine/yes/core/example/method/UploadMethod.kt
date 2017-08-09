package com.dounine.yes.core.example.method

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.request.RequestMethod

class UploadMethod(example: Example) : EntityEnclosingMethod(example,RequestMethod.POST) {


    private var fileDatas:ArrayList<FileData> = ArrayList()

    fun addFileData(vararg fileDatas: FileData): UploadMethod{
        this.fileDatas.addAll(fileDatas)
        return this
    }

    fun getFileDatas():List<FileData>{
        return fileDatas
    }

}