package com.amazon.tenzing.textsearch.v1_1;

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
public class CustomerIdentitySerializer extends JsonSerializer<CustomerIdentity> {
    public static final CustomerIdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CustomerIdentitySerializer customerIdentitySerializer = new CustomerIdentitySerializer();
        INSTANCE = customerIdentitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.CustomerIdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CustomerIdentity.class, customerIdentitySerializer);
    }

    private CustomerIdentitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CustomerIdentity customerIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (customerIdentity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(customerIdentity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CustomerIdentity customerIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(customerIdentity.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(customerIdentity.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(customerIdentity.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(customerIdentity.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(customerIdentity.getDeviceType(), jsonGenerator, serializerProvider);
    }
}
