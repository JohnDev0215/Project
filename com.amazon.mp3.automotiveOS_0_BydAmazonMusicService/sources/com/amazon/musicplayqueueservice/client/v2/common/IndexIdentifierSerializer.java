package com.amazon.musicplayqueueservice.client.v2.common;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class IndexIdentifierSerializer extends JsonSerializer<IndexIdentifier> {
    public static final IndexIdentifierSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        IndexIdentifierSerializer indexIdentifierSerializer = new IndexIdentifierSerializer();
        INSTANCE = indexIdentifierSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.common.IndexIdentifierSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(IndexIdentifier.class, indexIdentifierSerializer);
    }

    private IndexIdentifierSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull IndexIdentifier indexIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (indexIdentifier == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(indexIdentifier, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(IndexIdentifier indexIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("index");
        SimpleSerializers.serializePrimitiveInt(indexIdentifier.getIndex(), jsonGenerator, serializerProvider);
    }
}
