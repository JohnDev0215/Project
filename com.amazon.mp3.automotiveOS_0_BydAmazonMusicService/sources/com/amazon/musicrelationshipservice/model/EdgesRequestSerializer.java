package com.amazon.musicrelationshipservice.model;

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

/* JADX INFO: loaded from: classes5.dex */
public class EdgesRequestSerializer extends JsonSerializer<EdgesRequest> {
    public static final EdgesRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EdgesRequestSerializer edgesRequestSerializer = new EdgesRequestSerializer();
        INSTANCE = edgesRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicrelationshipservice.model.EdgesRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EdgesRequest.class, edgesRequestSerializer);
    }

    private EdgesRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EdgesRequest edgesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (edgesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(edgesRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EdgesRequest edgesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(edgesRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(edgesRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(edgesRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("edges");
        EdgeListSerializer.INSTANCE.serialize(edgesRequest.getEdges(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("childRequest");
        SimpleSerializers.serializePrimitiveBoolean(edgesRequest.isChildRequest(), jsonGenerator, serializerProvider);
    }
}
