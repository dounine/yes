package com.dounine.yes.action

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface Result {

    fun getCode():Int

    fun getData():Any

    fun getMsg():String

}