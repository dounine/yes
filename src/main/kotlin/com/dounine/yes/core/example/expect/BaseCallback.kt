package com.dounine.yes.core.example.expect

interface BaseCallback {

    fun condition():Condition {
        return Condition.EQ
    }

    fun jsonExpress():String {
        return ""
    }

}