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
public class UpdatePreferencesRequestSerializer extends JsonSerializer<UpdatePreferencesRequest> {
    public static final UpdatePreferencesRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UpdatePreferencesRequestSerializer updatePreferencesRequestSerializer = new UpdatePreferencesRequestSerializer();
        INSTANCE = updatePreferencesRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.UpdatePreferencesRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UpdatePreferencesRequest.class, updatePreferencesRequestSerializer);
    }

    private UpdatePreferencesRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UpdatePreferencesRequest updatePreferencesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (updatePreferencesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(updatePreferencesRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UpdatePreferencesRequest updatePreferencesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(updatePreferencesRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(updatePreferencesRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(updatePreferencesRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preferences");
        PreferenceMapSerializer.INSTANCE.serialize(updatePreferencesRequest.getPreferences(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("namespace");
        SimpleSerializers.serializeString(updatePreferencesRequest.getNamespace(), jsonGenerator, serializerProvider);
    }
}
