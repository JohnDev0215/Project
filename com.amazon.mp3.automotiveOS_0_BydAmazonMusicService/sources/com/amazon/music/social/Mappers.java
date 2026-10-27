package com.amazon.music.social;

import com.amazon.musicrelationshipservice.model.EdgesRequestSerializer;
import com.amazon.musicrelationshipservice.model.EdgesResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        EdgesRequestSerializer.register(objectMapper);
        EdgesResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
