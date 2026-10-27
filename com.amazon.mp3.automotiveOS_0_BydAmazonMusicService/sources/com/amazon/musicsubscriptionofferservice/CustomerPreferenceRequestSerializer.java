package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerPreferenceRequestSerializer extends JsonSerializer<CustomerPreferenceRequest> {
    public static final CustomerPreferenceRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CustomerPreferenceRequestSerializer customerPreferenceRequestSerializer = new CustomerPreferenceRequestSerializer();
        INSTANCE = customerPreferenceRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.CustomerPreferenceRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CustomerPreferenceRequest.class, customerPreferenceRequestSerializer);
    }

    private CustomerPreferenceRequestSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CustomerPreferenceRequest customerPreferenceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (customerPreferenceRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(customerPreferenceRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CustomerPreferenceRequest customerPreferenceRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("requireCop");
        SimpleSerializers.serializeBoolean(customerPreferenceRequest.isRequireCop(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerPreferences");
        CustomerPreferencesSerializer.INSTANCE.serialize(customerPreferenceRequest.getCustomerPreferences(), jsonGenerator, serializerProvider);
    }
}
