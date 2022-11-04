package de.paulbrejla.webhookconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebhookConsumerApplication

fun main(args: Array<String>) {
    runApplication<WebhookConsumerApplication>(*args)
}
