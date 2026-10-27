package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class PriceWithCurrencySerializer extends JsonSerializer<PriceWithCurrency> {
    public static final PriceWithCurrencySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PriceWithCurrencySerializer priceWithCurrencySerializer = new PriceWithCurrencySerializer();
        INSTANCE = priceWithCurrencySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.PriceWithCurrencySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PriceWithCurrency.class, priceWithCurrencySerializer);
    }

    private PriceWithCurrencySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PriceWithCurrency priceWithCurrency, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (priceWithCurrency == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(priceWithCurrency, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PriceWithCurrency priceWithCurrency, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("amount");
        SimpleSerializers.serializeBigDecimal(priceWithCurrency.getAmount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("currencyCode");
        SimpleSerializers.serializeString(priceWithCurrency.getCurrencyCode(), jsonGenerator, serializerProvider);
    }
}
