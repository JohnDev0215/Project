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
public class BuyabilitySerializer extends JsonSerializer<Buyability> {
    public static final BuyabilitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BuyabilitySerializer buyabilitySerializer = new BuyabilitySerializer();
        INSTANCE = buyabilitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.BuyabilitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Buyability.class, buyabilitySerializer);
    }

    private BuyabilitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Buyability buyability, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (buyability == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(buyability, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Buyability buyability, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("buyable");
        SimpleSerializers.serializeBoolean(buyability.isBuyable(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("reasons");
        BuyabilityReasonsSerializer.INSTANCE.serialize(buyability.getReasons(), jsonGenerator, serializerProvider);
    }
}
