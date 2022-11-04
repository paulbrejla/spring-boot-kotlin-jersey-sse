package de.paulbrejla.webhookconsumer.rest

import org.springframework.util.MultiValueMap
import javax.ws.rs.core.MultivaluedMap

data class EventPayloadDto(val payload: Any, val headers: MultivaluedMap<String, String>)