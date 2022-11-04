package de.paulbrejla.webhookconsumer.rest.impl

import de.paulbrejla.webhookconsumer.application.api.EventService
import de.paulbrejla.webhookconsumer.rest.api.EventWsV1
import org.glassfish.jersey.inject.hk2.RequestContext
import org.json.JSONObject
import org.springframework.stereotype.Component
import javax.ws.rs.PathParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import javax.ws.rs.sse.Sse
import javax.ws.rs.sse.SseEventSink

@Component("eventWsV1")
class EventWsV1Impl(val eventService: EventService) : EventWsV1 {
    override fun receivePayload(
        body: JSONObject,
        @PathParam(value = "listenerId") listenerId: String,
        @Context context: HttpHeaders
    ): Response {

        eventService.sendEvent(listenerId, body, context.requestHeaders)
        return Response.accepted().build()
    }

    override fun registerForEvents(eventSink: SseEventSink?, sse: Sse?, listenerId: String) {
        eventService.addEventSink(eventSink!!, listenerId)
    }
}