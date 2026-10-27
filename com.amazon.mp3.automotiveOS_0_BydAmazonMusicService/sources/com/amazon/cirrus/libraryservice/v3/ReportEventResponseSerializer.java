package com.amazon.cirrus.libraryservice.v3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ReportEventResponseSerializer extends JsonSerializer<ReportEventResponse> {
    public static final ReportEventResponseSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReportEventResponseSerializer reportEventResponseSerializer = new ReportEventResponseSerializer();
        INSTANCE = reportEventResponseSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.ReportEventResponseSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ReportEventResponse.class, reportEventResponseSerializer);
    }

    private ReportEventResponseSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ReportEventResponse reportEventResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (reportEventResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(reportEventResponse, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ReportEventResponse reportEventResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    }
}
