package com.amazon.music.subscription;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class AdditionalPlanPriceInfoSerializer extends JsonSerializer<AdditionalPlanPriceInfo> {
    public static final AdditionalPlanPriceInfoSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AdditionalPlanPriceInfoSerializer additionalPlanPriceInfoSerializer = new AdditionalPlanPriceInfoSerializer();
        INSTANCE = additionalPlanPriceInfoSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.AdditionalPlanPriceInfoSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AdditionalPlanPriceInfo.class, additionalPlanPriceInfoSerializer);
    }

    private AdditionalPlanPriceInfoSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AdditionalPlanPriceInfo additionalPlanPriceInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (additionalPlanPriceInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(additionalPlanPriceInfo, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AdditionalPlanPriceInfo additionalPlanPriceInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("dueNowPlanPriceTotal");
        PriceWithCurrencySerializer priceWithCurrencySerializer = PriceWithCurrencySerializer.INSTANCE;
        priceWithCurrencySerializer.serialize(additionalPlanPriceInfo.getDueNowPlanPriceTotal(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pendingPlanPriceTotal");
        priceWithCurrencySerializer.serialize(additionalPlanPriceInfo.getPendingPlanPriceTotal(), jsonGenerator, serializerProvider);
    }
}
