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
public class RetrievePreferencesRequestSerializer extends JsonSerializer<RetrievePreferencesRequest> {
    public static final RetrievePreferencesRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrievePreferencesRequestSerializer retrievePreferencesRequestSerializer = new RetrievePreferencesRequestSerializer();
        INSTANCE = retrievePreferencesRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrievePreferencesRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrievePreferencesRequest.class, retrievePreferencesRequestSerializer);
    }

    private RetrievePreferencesRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrievePreferencesRequest retrievePreferencesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrievePreferencesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrievePreferencesRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrievePreferencesRequest retrievePreferencesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(retrievePreferencesRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(retrievePreferencesRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("namespace");
        SimpleSerializers.serializeString(retrievePreferencesRequest.getNamespace(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("keys");
        PreferenceKeyListSerializer.INSTANCE.serialize(retrievePreferencesRequest.getKeys(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(retrievePreferencesRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
