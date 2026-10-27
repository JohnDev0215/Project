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
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDeviceRequestSerializer extends JsonSerializer<UpdateDeviceRequest> {
    public static final UpdateDeviceRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UpdateDeviceRequestSerializer updateDeviceRequestSerializer = new UpdateDeviceRequestSerializer();
        INSTANCE = updateDeviceRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.UpdateDeviceRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UpdateDeviceRequest.class, updateDeviceRequestSerializer);
    }

    private UpdateDeviceRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UpdateDeviceRequest updateDeviceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (updateDeviceRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(updateDeviceRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UpdateDeviceRequest updateDeviceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("capabilitiesToGrant");
        JsonSerializer<List<String>> jsonSerializer = DeviceCapabilitiesSerializer.INSTANCE;
        jsonSerializer.serialize(updateDeviceRequest.getCapabilitiesToGrant(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(updateDeviceRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deviceName");
        SimpleSerializers.serializeString(updateDeviceRequest.getDeviceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetDeviceType");
        SimpleSerializers.serializeString(updateDeviceRequest.getTargetDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetDeviceId");
        SimpleSerializers.serializeString(updateDeviceRequest.getTargetDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(updateDeviceRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(updateDeviceRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("capabilitiesToRevoke");
        jsonSerializer.serialize(updateDeviceRequest.getCapabilitiesToRevoke(), jsonGenerator, serializerProvider);
    }
}
