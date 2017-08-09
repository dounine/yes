package com.dounine.yes.core.postman

import java.util.*

class Info {
    private lateinit var name: String
    private var postmanId: String = UUID.randomUUID().toString()
    private var description: String = ""
    private var schema: String = "https://schema.getpostman.com/json/collection/v2.0.0/collection.json"


    fun getName(): String = this.name

    fun setName(name: String) {
        this.name = name
    }

    fun get__postman_id(): String = this.postmanId

    fun getDescription(): String = this.description

    fun setDescription(des: String) {
        this.description = des
    }

    fun getSchema(): String = this.schema
}