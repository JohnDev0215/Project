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
public class AuthorizeDeviceRequestSerializer extends JsonSerializer<AuthorizeDeviceRequest> {
    public static final AuthorizeDeviceRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AuthorizeDeviceRequestSerializer authorizeDeviceRequestSerializer = new AuthorizeDeviceRequestSerializer();
        INSTANCE = authorizeDeviceRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.AuthorizeDeviceRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AuthorizeDeviceRequest.class, authorizeDeviceRequestSerializer);
    }

    private AuthorizeDeviceRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AuthorizeDeviceRequest authorizeDeviceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (authorizeDeviceRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(authorizeDeviceRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AuthorizeDeviceRequest authorizeDeviceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("deviceName");
        SimpleSerializers.serializeString(authorizeDeviceRequest.getDeviceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("capabilitiesToGrant");
        DeviceCapabilitiesSerializer.INSTANCE.serialize(authorizeDeviceRequest.getCapabilitiesToGrant(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(authorizeDeviceRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetDeviceType");
        SimpleSerializers.serializeString(authorizeDeviceRequest.getTargetDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetDeviceId");
        SimpleSerializers.serializeString(authorizeDeviceRequest.getTargetDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(authorizeDeviceRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(authorizeDeviceRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
