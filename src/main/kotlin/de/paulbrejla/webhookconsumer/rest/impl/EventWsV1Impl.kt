package de.paulbrejla.webhookconsumer.rest.impl

import de.paulbrejla.webhookconsumer.application.api.EventService
import de.paulbrejla.webhookconsumer.rest.api.EventWsV1
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.ws.rs.PathParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response
import javax.ws.rs.sse.Sse
import javax.ws.rs.sse.SseEventSink

@Component("eventWsV1")
class EventWsV1Impl(val eventService: EventService) : EventWsV1 {
    var logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun receivePayload(
        body: Any,
        @PathParam(value = "listenerId") listenerId: String,
        @Context context: HttpHeaders
    ): Response {
        logger.info(body.toString())
        eventService.sendEvent(listenerId, body, context.requestHeaders)

        return Response.accepted().build()
    }

    override fun registerForEvents(eventSink: SseEventSink?, sse: Sse?, listenerId: String) {
        eventService.addEventSink(eventSink!!, listenerId)
    }
}