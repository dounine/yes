package com.dounine.yes

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class YesApplication

fun main(args: Array<String>) {
    SpringApplication.run(YesApplication::class.java, *args)
}
