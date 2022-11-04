package de.paulbrejla.webhookconsumer.application.api

import org.json.JSONObject
import org.springframework.util.MultiValueMap
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.sse.SseEventSink

interface EventService {
    fun addEventSink(eventSink: SseEventSink, listenerId: String)
    fun sendEvent(listenerId: String, payload: JSONObject, headers: MultivaluedMap<String, String>)
}