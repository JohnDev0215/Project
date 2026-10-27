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
public class OfferStatusQuerySerializer extends JsonSerializer<OfferStatusQuery> {
    public static final OfferStatusQuerySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        OfferStatusQuerySerializer offerStatusQuerySerializer = new OfferStatusQuerySerializer();
        INSTANCE = offerStatusQuerySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.OfferStatusQuerySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(OfferStatusQuery.class, offerStatusQuerySerializer);
    }

    private OfferStatusQuerySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull OfferStatusQuery offerStatusQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (offerStatusQuery == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(offerStatusQuery, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(OfferStatusQuery offerStatusQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("offerStatus");
        SimpleSerializers.serializeString(offerStatusQuery.getOfferStatus(), jsonGenerator, serializerProvider);
    }
}
