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
public class PaymentInfoSerializer extends JsonSerializer<PaymentInfo> {
    public static final PaymentInfoSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PaymentInfoSerializer paymentInfoSerializer = new PaymentInfoSerializer();
        INSTANCE = paymentInfoSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.PaymentInfoSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PaymentInfo.class, paymentInfoSerializer);
    }

    private PaymentInfoSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PaymentInfo paymentInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (paymentInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(paymentInfo, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PaymentInfo paymentInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("paymentPortalClientId");
        SimpleSerializers.serializeString(paymentInfo.getPaymentPortalClientId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentsPreferenceKey");
        PaymentsPreferenceKeySerializer.INSTANCE.serialize(paymentInfo.getPaymentsPreferenceKey(), jsonGenerator, serializerProvider);
    }
}
