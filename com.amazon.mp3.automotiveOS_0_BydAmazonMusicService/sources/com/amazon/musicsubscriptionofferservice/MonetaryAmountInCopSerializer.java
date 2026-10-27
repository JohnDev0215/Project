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
public class MonetaryAmountInCopSerializer extends JsonSerializer<MonetaryAmountInCop> {
    public static final MonetaryAmountInCopSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MonetaryAmountInCopSerializer monetaryAmountInCopSerializer = new MonetaryAmountInCopSerializer();
        INSTANCE = monetaryAmountInCopSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.MonetaryAmountInCopSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MonetaryAmountInCop.class, monetaryAmountInCopSerializer);
    }

    private MonetaryAmountInCopSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MonetaryAmountInCop monetaryAmountInCop, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (monetaryAmountInCop == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(monetaryAmountInCop, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MonetaryAmountInCop monetaryAmountInCop, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("value");
        SimpleSerializers.serializeBigDecimal(monetaryAmountInCop.getValue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("currencyCode");
        SimpleSerializers.serializeString(monetaryAmountInCop.getCurrencyCode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("formattedAmount");
        SimpleSerializers.serializeString(monetaryAmountInCop.getFormattedAmount(), jsonGenerator, serializerProvider);
    }
}
