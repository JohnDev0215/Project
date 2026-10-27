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
public class SimplePlayableEntityIdentifierSerializer extends JsonSerializer<SimplePlayableEntityIdentifier> {
    public static final SimplePlayableEntityIdentifierSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SimplePlayableEntityIdentifierSerializer simplePlayableEntityIdentifierSerializer = new SimplePlayableEntityIdentifierSerializer();
        INSTANCE = simplePlayableEntityIdentifierSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.common.SimplePlayableEntityIdentifierSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SimplePlayableEntityIdentifier.class, simplePlayableEntityIdentifierSerializer);
    }

    private SimplePlayableEntityIdentifierSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SimplePlayableEntityIdentifier simplePlayableEntityIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (simplePlayableEntityIdentifier == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(simplePlayableEntityIdentifier, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SimplePlayableEntityIdentifier simplePlayableEntityIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("identifier");
        SimpleSerializers.serializeString(simplePlayableEntityIdentifier.getIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identifierType");
        SimpleSerializers.serializeString(simplePlayableEntityIdentifier.getIdentifierType(), jsonGenerator, serializerProvider);
    }
}
