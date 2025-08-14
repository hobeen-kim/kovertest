package com.hobeen.kovertest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KovertestApplication

fun main(args: Array<String>) {
    runApplication<KovertestApplication>(*args)
}
