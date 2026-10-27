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
public class AttemptAccountCreationRequestSerializer extends JsonSerializer<AttemptAccountCreationRequest> {
    public static final AttemptAccountCreationRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AttemptAccountCreationRequestSerializer attemptAccountCreationRequestSerializer = new AttemptAccountCreationRequestSerializer();
        INSTANCE = attemptAccountCreationRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.AttemptAccountCreationRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AttemptAccountCreationRequest.class, attemptAccountCreationRequestSerializer);
    }

    private AttemptAccountCreationRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AttemptAccountCreationRequest attemptAccountCreationRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (attemptAccountCreationRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(attemptAccountCreationRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AttemptAccountCreationRequest attemptAccountCreationRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(attemptAccountCreationRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(attemptAccountCreationRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(attemptAccountCreationRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(attemptAccountCreationRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(attemptAccountCreationRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("acceptTermsOfUse");
        SimpleSerializers.serializeBoolean(attemptAccountCreationRequest.isAcceptTermsOfUse(), jsonGenerator, serializerProvider);
    }
}
