package de.paulbrejla.webhookconsumer.application.config

import de.paulbrejla.webhookconsumer.rest.api.EventWsV1
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration
import javax.ws.rs.ApplicationPath

@Configuration
@ApplicationPath("/app")
class JerseyConfig : ResourceConfig(EventWsV1::class.java) {
    init {
        registerEndpoints()
    }

    private fun registerEndpoints() {
        register(EventWsV1::class)

    }

}