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
public class MonetaryAmountSerializer extends JsonSerializer<MonetaryAmount> {
    public static final MonetaryAmountSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MonetaryAmountSerializer monetaryAmountSerializer = new MonetaryAmountSerializer();
        INSTANCE = monetaryAmountSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.MonetaryAmountSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MonetaryAmount.class, monetaryAmountSerializer);
    }

    private MonetaryAmountSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MonetaryAmount monetaryAmount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (monetaryAmount == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(monetaryAmount, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MonetaryAmount monetaryAmount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("copPrice");
        MonetaryAmountInCopSerializer.INSTANCE.serialize(monetaryAmount.getCopPrice(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("value");
        SimpleSerializers.serializeBigDecimal(monetaryAmount.getValue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("currencyCode");
        SimpleSerializers.serializeString(monetaryAmount.getCurrencyCode(), jsonGenerator, serializerProvider);
    }
}
