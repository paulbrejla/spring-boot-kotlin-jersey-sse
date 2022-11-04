package de.paulbrejla.webhookconsumer.application.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import de.paulbrejla.webhookconsumer.application.api.EventService
import de.paulbrejla.webhookconsumer.rest.EventPayloadDto
import org.glassfish.jersey.media.sse.OutboundEvent
import org.json.JSONObject
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import java.net.http.HttpHeaders
import java.util.concurrent.ConcurrentHashMap
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.sse.SseEventSink

@Component("eventService")
class EventServiceImpl : EventService {
    private val eventSinks: ConcurrentHashMap<String, SseEventSink> = ConcurrentHashMap()
    var ow: ObjectWriter = ObjectMapper().writer()

    override fun addEventSink(eventSink: SseEventSink, listenerId: String) {
        eventSinks[listenerId] = eventSink
    }

    override fun sendEvent(listenerId: String, payload: Any, headers: MultivaluedMap<String, String>) {
        val eventBuilder = OutboundEvent.Builder()
        eventBuilder.name("event_$listenerId")
        eventBuilder.data(
            String::class.java,
            ow
                .withDefaultPrettyPrinter()
                .writeValueAsString(
                    EventPayloadDto(payload = payload, headers = headers)
                )
        )
        val event = eventBuilder.build()
        eventSinks[listenerId]!!.send(event)
    }
}