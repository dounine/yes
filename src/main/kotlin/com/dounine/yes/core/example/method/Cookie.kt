package com.dounine.yes.core.example.method

class Cookie(val name:String,
             val value:Any,
             val domain:String = "localhost",
             val path:String = "/",
             val expires:String = "",
             val http:Boolean = true,
             val secure:Boolean = false) {
}