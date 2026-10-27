package com.amazon.music.freetier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
public final class Mappers {
    private static final ObjectMapper objectMapper;

    static {
        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper = objectMapper2;
        objectMapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper mapper() {
        return objectMapper;
    }
}
