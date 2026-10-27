package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class UpsellRequestSerializer extends JsonSerializer<UpsellRequest> {
    public static final UpsellRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UpsellRequestSerializer upsellRequestSerializer = new UpsellRequestSerializer();
        INSTANCE = upsellRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.UpsellRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UpsellRequest.class, upsellRequestSerializer);
    }

    private UpsellRequestSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UpsellRequest upsellRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (upsellRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(upsellRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UpsellRequest upsellRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowUpsellForCatalogContent");
        SimpleSerializers.serializeBoolean(upsellRequest.isAllowUpsellForCatalogContent(), jsonGenerator, serializerProvider);
    }
}
