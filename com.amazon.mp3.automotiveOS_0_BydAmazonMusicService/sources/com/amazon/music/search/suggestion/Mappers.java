package com.amazon.music.search.suggestion;

import com.amazon.tenzing.textsearch.v1_1.SuggestRequestSerializer;
import com.amazon.tenzing.textsearch.v1_1.SuggestResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        SuggestRequestSerializer.register(objectMapper);
        SuggestResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
