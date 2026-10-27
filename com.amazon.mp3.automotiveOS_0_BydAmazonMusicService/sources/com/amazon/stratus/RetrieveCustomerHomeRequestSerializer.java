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
public class RetrieveCustomerHomeRequestSerializer extends JsonSerializer<RetrieveCustomerHomeRequest> {
    public static final RetrieveCustomerHomeRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveCustomerHomeRequestSerializer retrieveCustomerHomeRequestSerializer = new RetrieveCustomerHomeRequestSerializer();
        INSTANCE = retrieveCustomerHomeRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrieveCustomerHomeRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveCustomerHomeRequest.class, retrieveCustomerHomeRequestSerializer);
    }

    private RetrieveCustomerHomeRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveCustomerHomeRequest retrieveCustomerHomeRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveCustomerHomeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveCustomerHomeRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveCustomerHomeRequest retrieveCustomerHomeRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(retrieveCustomerHomeRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(retrieveCustomerHomeRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(retrieveCustomerHomeRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(retrieveCustomerHomeRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(retrieveCustomerHomeRequest.getDeviceType(), jsonGenerator, serializerProvider);
    }
}
