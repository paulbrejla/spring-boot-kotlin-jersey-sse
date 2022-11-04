package de.paulbrejla.webhookconsumer.application.api

import javax.ws.rs.sse.SseEventSink

interface EventService {
    fun addEventSink(eventSink: SseEventSink, listenerId: String)
    fun sendEvent(listenerId: String, payload: String)
}