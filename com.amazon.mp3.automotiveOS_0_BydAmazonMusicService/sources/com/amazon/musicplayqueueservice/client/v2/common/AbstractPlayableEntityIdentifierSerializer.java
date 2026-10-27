package com.amazon.musicplayqueueservice.client.v2.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AbstractPlayableEntityIdentifierSerializer extends JsonSerializer<AbstractPlayableEntityIdentifier> {
    public static final AbstractPlayableEntityIdentifierSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AbstractPlayableEntityIdentifierSerializer abstractPlayableEntityIdentifierSerializer = new AbstractPlayableEntityIdentifierSerializer();
        INSTANCE = abstractPlayableEntityIdentifierSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifierSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AbstractPlayableEntityIdentifier.class, abstractPlayableEntityIdentifierSerializer);
    }

    private AbstractPlayableEntityIdentifierSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AbstractPlayableEntityIdentifier abstractPlayableEntityIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (abstractPlayableEntityIdentifier == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (abstractPlayableEntityIdentifier instanceof SimplePlayableEntityIdentifier) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicplayqueueservice.client.v2.common#SimplePlayableEntityIdentifier");
            SimplePlayableEntityIdentifierSerializer.INSTANCE.serializeFields((SimplePlayableEntityIdentifier) abstractPlayableEntityIdentifier, jsonGenerator, serializerProvider);
        } else {
            boolean z = abstractPlayableEntityIdentifier instanceof LibraryAlbumIdentifier;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(abstractPlayableEntityIdentifier, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("com.amazon.musicplayqueueservice.client.v2.common#LibraryAlbumIdentifier");
                LibraryAlbumIdentifierSerializer.INSTANCE.serializeFields((LibraryAlbumIdentifier) abstractPlayableEntityIdentifier, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AbstractPlayableEntityIdentifier abstractPlayableEntityIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
