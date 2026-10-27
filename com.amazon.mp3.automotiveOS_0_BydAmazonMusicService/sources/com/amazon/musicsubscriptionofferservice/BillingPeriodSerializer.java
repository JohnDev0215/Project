package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class BillingPeriodSerializer extends JsonSerializer<BillingPeriod> {
    public static final BillingPeriodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BillingPeriodSerializer billingPeriodSerializer = new BillingPeriodSerializer();
        INSTANCE = billingPeriodSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.BillingPeriodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BillingPeriod.class, billingPeriodSerializer);
    }

    private BillingPeriodSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BillingPeriod billingPeriod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (billingPeriod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(billingPeriod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BillingPeriod billingPeriod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("basePrice");
        MonetaryAmountSerializer monetaryAmountSerializer = MonetaryAmountSerializer.INSTANCE;
        monetaryAmountSerializer.serialize(billingPeriod.getBasePrice(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("duration");
        PeriodSerializer.INSTANCE.serialize(billingPeriod.getDuration(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("price");
        monetaryAmountSerializer.serialize(billingPeriod.getPrice(), jsonGenerator, serializerProvider);
    }
}
