package com.amazon.music.search.suggestion.instantsearch;

import com.amazon.tenzing.textsearch.v1_1.InstantSearchRequestSerializer;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        InstantSearchRequestSerializer.register(objectMapper);
        InstantSearchResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
