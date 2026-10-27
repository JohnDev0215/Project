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
public class IsAccountValidRequestSerializer extends JsonSerializer<IsAccountValidRequest> {
    public static final IsAccountValidRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        IsAccountValidRequestSerializer isAccountValidRequestSerializer = new IsAccountValidRequestSerializer();
        INSTANCE = isAccountValidRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.IsAccountValidRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(IsAccountValidRequest.class, isAccountValidRequestSerializer);
    }

    private IsAccountValidRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull IsAccountValidRequest isAccountValidRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (isAccountValidRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(isAccountValidRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(IsAccountValidRequest isAccountValidRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(isAccountValidRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(isAccountValidRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(isAccountValidRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("verbose");
        SimpleSerializers.serializeBoolean(isAccountValidRequest.isVerbose(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(isAccountValidRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
