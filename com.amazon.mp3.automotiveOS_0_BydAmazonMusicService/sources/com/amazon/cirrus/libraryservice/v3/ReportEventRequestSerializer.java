package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ReportEventRequestSerializer extends JsonSerializer<ReportEventRequest> {
    public static final ReportEventRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReportEventRequestSerializer reportEventRequestSerializer = new ReportEventRequestSerializer();
        INSTANCE = reportEventRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.ReportEventRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ReportEventRequest.class, reportEventRequestSerializer);
    }

    private ReportEventRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ReportEventRequest reportEventRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (reportEventRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(reportEventRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ReportEventRequest reportEventRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(reportEventRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("eventDescription");
        SimpleSerializers.serializeString(reportEventRequest.getEventDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("clientVersion");
        SimpleSerializers.serializeString(reportEventRequest.getClientVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(reportEventRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("eventName");
        SimpleSerializers.serializeString(reportEventRequest.getEventName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(reportEventRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("eventCount");
        SimpleSerializers.serializeInteger(reportEventRequest.getEventCount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("eventSeverity");
        SimpleSerializers.serializeString(reportEventRequest.getEventSeverity(), jsonGenerator, serializerProvider);
    }
}
