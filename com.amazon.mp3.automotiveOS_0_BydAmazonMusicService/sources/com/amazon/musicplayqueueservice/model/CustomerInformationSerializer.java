package com.amazon.musicplayqueueservice.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerInformationSerializer extends JsonSerializer<CustomerInformation> {
    public static final CustomerInformationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CustomerInformationSerializer customerInformationSerializer = new CustomerInformationSerializer();
        INSTANCE = customerInformationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.model.CustomerInformationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CustomerInformation.class, customerInformationSerializer);
    }

    private CustomerInformationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CustomerInformation customerInformation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (customerInformation == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(customerInformation, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CustomerInformation customerInformation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(customerInformation.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(customerInformation.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(customerInformation.getCustomerId(), jsonGenerator, serializerProvider);
    }
}
