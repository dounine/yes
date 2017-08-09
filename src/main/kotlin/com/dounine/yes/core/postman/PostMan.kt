package com.dounine.yes.core.postman

class PostMan {

    private var variables:List<String> = ArrayList()
    private lateinit var info:Info
    private var item:List<Any> = ArrayList()

     fun getVariables(): List<String> = this.variables

    fun setInfo(info:Info){
        this.info = info
    }

     fun getInfo(): Info = this.info

     fun getItem(): List<Any> = this.item

    fun setItem(itemGroup:List<ItemGroup>){
        this.item = itemGroup
    }
}