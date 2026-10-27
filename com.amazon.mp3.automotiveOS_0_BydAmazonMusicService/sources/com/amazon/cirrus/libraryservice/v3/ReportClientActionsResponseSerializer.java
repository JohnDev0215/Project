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
public class ReportClientActionsResponseSerializer extends JsonSerializer<ReportClientActionsResponse> {
    public static final ReportClientActionsResponseSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReportClientActionsResponseSerializer reportClientActionsResponseSerializer = new ReportClientActionsResponseSerializer();
        INSTANCE = reportClientActionsResponseSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponseSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ReportClientActionsResponse.class, reportClientActionsResponseSerializer);
    }

    private ReportClientActionsResponseSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ReportClientActionsResponse reportClientActionsResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (reportClientActionsResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(reportClientActionsResponse, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ReportClientActionsResponse reportClientActionsResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    }
}
