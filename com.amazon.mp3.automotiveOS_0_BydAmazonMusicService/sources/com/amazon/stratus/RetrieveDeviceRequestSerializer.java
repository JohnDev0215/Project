package com.amazon.stratus;

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
public class RetrieveDeviceRequestSerializer extends JsonSerializer<RetrieveDeviceRequest> {
    public static final RetrieveDeviceRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveDeviceRequestSerializer retrieveDeviceRequestSerializer = new RetrieveDeviceRequestSerializer();
        INSTANCE = retrieveDeviceRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrieveDeviceRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveDeviceRequest.class, retrieveDeviceRequestSerializer);
    }

    private RetrieveDeviceRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveDeviceRequest retrieveDeviceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveDeviceRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveDeviceRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveDeviceRequest retrieveDeviceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(retrieveDeviceRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetDeviceType");
        SimpleSerializers.serializeString(retrieveDeviceRequest.getTargetDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetDeviceId");
        SimpleSerializers.serializeString(retrieveDeviceRequest.getTargetDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(retrieveDeviceRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(retrieveDeviceRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
