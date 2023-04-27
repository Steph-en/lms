package com.ait.lms

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.client.RestTemplate
import java.util.concurrent.Executor


@SpringBootApplication
@EnableAsync
class LMSServiceApplication

@Value("\${core.pool.size}") val corePoolSize:Int? = null
@Value("\${max.pool.size}") val maxPoolSize:Int? = null

fun main(args: Array<String>) {
    runApplication<LMSServiceApplication>(*args)
}

@Bean
fun taskExecutor(): Executor {
    val executor = ThreadPoolTaskExecutor()
    executor.corePoolSize = corePoolSize!!
    executor.maxPoolSize = maxPoolSize!!
    executor.setQueueCapacity(500)
    executor.setThreadNamePrefix("as-executor")
    executor.initialize()
    return executor
}

@Bean
fun getRestTemplate(): RestTemplate {
    return RestTemplate()
}

@Bean
fun restTemplate(): RestTemplate {
    val restTemplate = RestTemplate()
    val requestFactory = HttpComponentsClientHttpRequestFactory()
    requestFactory.setConnectTimeout(360000)
    requestFactory.setReadTimeout(360000)
    restTemplate.requestFactory = requestFactory
    return restTemplate
}