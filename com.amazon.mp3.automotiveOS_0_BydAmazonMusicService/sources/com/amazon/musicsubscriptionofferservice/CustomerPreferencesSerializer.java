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
public class CustomerPreferencesSerializer extends JsonSerializer<CustomerPreferences> {
    public static final CustomerPreferencesSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CustomerPreferencesSerializer customerPreferencesSerializer = new CustomerPreferencesSerializer();
        INSTANCE = customerPreferencesSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.CustomerPreferencesSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CustomerPreferences.class, customerPreferencesSerializer);
    }

    private CustomerPreferencesSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CustomerPreferences customerPreferences, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (customerPreferences == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(customerPreferences, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CustomerPreferences customerPreferences, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("cop");
        SimpleSerializers.serializeString(customerPreferences.getCop(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lop");
        SimpleSerializers.serializeString(customerPreferences.getLop(), jsonGenerator, serializerProvider);
    }
}
