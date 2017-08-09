package com.dounine.yes.core.postman

class ItemGroup {
    private lateinit var name:String
    private var description:String = ""
    private var item:List<Item> = ArrayList()

     fun getName(): String = this.name

    fun setName(name:String){
        this.name = name
    }

     fun getDescription(): String = this.description

    fun setDescription(description:String){
        this.description = description
    }

     fun getItem(): List<Item> = this.item

    fun  setItem(item:List<Item>){
        this.item = item
    }
}