package com.amazon.dmvideodeliveryservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class CustomerInfoSerializer extends JsonSerializer<CustomerInfo> {
    public static final CustomerInfoSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CustomerInfoSerializer customerInfoSerializer = new CustomerInfoSerializer();
        INSTANCE = customerInfoSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.dmvideodeliveryservice.CustomerInfoSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CustomerInfo.class, customerInfoSerializer);
    }

    private CustomerInfoSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CustomerInfo customerInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (customerInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(customerInfo, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CustomerInfo customerInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("territoryId");
        SimpleSerializers.serializeString(customerInfo.getTerritoryId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(customerInfo.getMarketplaceId(), jsonGenerator, serializerProvider);
    }
}
