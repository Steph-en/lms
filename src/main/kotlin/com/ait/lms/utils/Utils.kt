package com.ait.lms.utils

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import kotlin.random.Random


@Component
class Utils{
    private val logger = LoggerFactory.getLogger(Utils::class.java)

    companion object {
        const val STRING_LENGTH: Int = 10
    }

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun generateRandom(): String {
        return (1..STRING_LENGTH)
                .map { Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
    }

    fun getCurrentTime(): String? {
        return Timestamp.valueOf(LocalDateTime.now()).toString()
    }
}