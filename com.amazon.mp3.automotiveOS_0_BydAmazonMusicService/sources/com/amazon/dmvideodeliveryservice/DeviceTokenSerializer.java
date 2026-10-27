package com.amazon.dmvideodeliveryservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceTokenSerializer extends JsonSerializer<DeviceToken> {
    public static final DeviceTokenSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeviceTokenSerializer deviceTokenSerializer = new DeviceTokenSerializer();
        INSTANCE = deviceTokenSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.dmvideodeliveryservice.DeviceTokenSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DeviceToken.class, deviceTokenSerializer);
    }

    private DeviceTokenSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DeviceToken deviceToken, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (deviceToken == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(deviceToken, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DeviceToken deviceToken, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(deviceToken.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deviceTypeId");
        SimpleSerializers.serializeString(deviceToken.getDeviceTypeId(), jsonGenerator, serializerProvider);
    }
}
