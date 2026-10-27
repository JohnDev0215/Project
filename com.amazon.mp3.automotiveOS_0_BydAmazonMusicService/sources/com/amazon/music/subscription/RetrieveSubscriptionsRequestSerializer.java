package com.amazon.music.subscription;

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

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveSubscriptionsRequestSerializer extends JsonSerializer<RetrieveSubscriptionsRequest> {
    public static final RetrieveSubscriptionsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveSubscriptionsRequestSerializer retrieveSubscriptionsRequestSerializer = new RetrieveSubscriptionsRequestSerializer();
        INSTANCE = retrieveSubscriptionsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.RetrieveSubscriptionsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveSubscriptionsRequest.class, retrieveSubscriptionsRequestSerializer);
    }

    private RetrieveSubscriptionsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveSubscriptionsRequest retrieveSubscriptionsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveSubscriptionsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveSubscriptionsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveSubscriptionsRequest retrieveSubscriptionsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(retrieveSubscriptionsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(retrieveSubscriptionsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(retrieveSubscriptionsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("activeOnly");
        SimpleSerializers.serializeBoolean(retrieveSubscriptionsRequest.isActiveOnly(), jsonGenerator, serializerProvider);
    }
}
