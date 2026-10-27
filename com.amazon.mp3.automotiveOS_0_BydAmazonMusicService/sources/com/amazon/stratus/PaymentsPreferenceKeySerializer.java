package com.amazon.stratus;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PaymentsPreferenceKeySerializer extends JsonSerializer<PaymentsPreferenceKey> {
    public static final PaymentsPreferenceKeySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PaymentsPreferenceKeySerializer paymentsPreferenceKeySerializer = new PaymentsPreferenceKeySerializer();
        INSTANCE = paymentsPreferenceKeySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.PaymentsPreferenceKeySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PaymentsPreferenceKey.class, paymentsPreferenceKeySerializer);
    }

    private PaymentsPreferenceKeySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PaymentsPreferenceKey paymentsPreferenceKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (paymentsPreferenceKey == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(paymentsPreferenceKey, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PaymentsPreferenceKey paymentsPreferenceKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(paymentsPreferenceKey.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("externalReferenceId");
        SimpleSerializers.serializeString(paymentsPreferenceKey.getExternalReferenceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentMethodId");
        SimpleSerializers.serializeString(paymentsPreferenceKey.getPaymentMethodId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(paymentsPreferenceKey.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentPreferenceId");
        SimpleSerializers.serializeString(paymentsPreferenceKey.getPaymentPreferenceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preferenceType");
        SimpleSerializers.serializeString(paymentsPreferenceKey.getPreferenceType(), jsonGenerator, serializerProvider);
    }
}
