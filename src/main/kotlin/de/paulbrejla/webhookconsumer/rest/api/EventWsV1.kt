package de.paulbrejla.webhookconsumer.rest.api

import org.glassfish.jersey.inject.hk2.RequestContext
import org.json.JSONObject
import org.springframework.web.bind.annotation.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.sse.Sse
import javax.ws.rs.sse.SseEventSink

@Path("/api/v1/")
@Produces(MediaType.APPLICATION_JSON)
interface EventWsV1 {

    @POST
    @Path("/messages/{listenerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun receivePayload(body: Any, @PathParam(value = "listenerId") listenerId: String, @Context context: HttpHeaders): Response

    /**
     * Register an event listener.
     */
    @Path("/listeners/{listenerId}")
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun registerForEvents(
        @Context eventSink: SseEventSink?, @Context sse: Sse?, @PathParam(value = "listenerId") listenerId: String
    )
}