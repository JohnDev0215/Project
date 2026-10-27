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
public class ListDevicesByCustomerIdRequestSerializer extends JsonSerializer<ListDevicesByCustomerIdRequest> {
    public static final ListDevicesByCustomerIdRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ListDevicesByCustomerIdRequestSerializer listDevicesByCustomerIdRequestSerializer = new ListDevicesByCustomerIdRequestSerializer();
        INSTANCE = listDevicesByCustomerIdRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.ListDevicesByCustomerIdRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ListDevicesByCustomerIdRequest.class, listDevicesByCustomerIdRequestSerializer);
    }

    private ListDevicesByCustomerIdRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (listDevicesByCustomerIdRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(listDevicesByCustomerIdRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(listDevicesByCustomerIdRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(listDevicesByCustomerIdRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(listDevicesByCustomerIdRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
