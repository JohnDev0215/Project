package com.amazon.music.metrics.mts.android;

import com.amazon.cirrus.libraryservice.v3.ReportClientActionsRequestDeserializer;
import com.amazon.cirrus.libraryservice.v3.ReportClientActionsRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponseSerializer;
import com.amazon.cirrus.libraryservice.v3.ReportEventRequestDeserializer;
import com.amazon.cirrus.libraryservice.v3.ReportEventRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.ReportEventResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.ReportEventResponseSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        ReportClientActionsRequestSerializer.register(objectMapper);
        ReportClientActionsRequestDeserializer.register(objectMapper);
        ReportClientActionsResponseSerializer.register(objectMapper);
        ReportClientActionsResponseDeserializer.register(objectMapper);
        ReportEventRequestSerializer.register(objectMapper);
        ReportEventRequestDeserializer.register(objectMapper);
        ReportEventResponseSerializer.register(objectMapper);
        ReportEventResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
