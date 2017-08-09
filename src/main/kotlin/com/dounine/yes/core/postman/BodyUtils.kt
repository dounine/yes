package com.dounine.yes.core.postman

import com.dounine.yes.core.request.DataType
import com.dounine.yes.core.request.RequestMethod
import com.dounine.yes.core.response.ResponseData
import java.util.ArrayList

object BodyUtils {
    fun getBody(method:RequestMethod,responseDatas:List<ResponseData>):Body?{
        if (method.equals(RequestMethod.POST) ||
                method.equals(RequestMethod.PUT) ||
                method.equals(RequestMethod.PATCH)) {
            var pmBody: Body = Body()
            pmBody.setMode(BodyMode.formdata)
            var formdatas: ArrayList<FormData> = pmBody.getFormdata() as ArrayList<FormData>
            for (pd in responseDatas) {
                var fdt:FormDataType = FormDataType.text
                var src:String? = null
                if(pd.getType().equals(DataType.File)){
                    fdt = FormDataType.file
                }
                formdatas.add(FormData(key = pd.getName(),
                        value = pd.getValue().toString(),
                        src = src,
                        type = fdt,
                        description = pd.getDes(),
                        disabled = pd.getRequire()
                ))
            }
            return pmBody
        }
        return null
    }
}