package com.amazon.musicplayqueueservice.client.common.casting;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class StartAtSerializer extends JsonSerializer<StartAt> {
    public static final StartAtSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StartAtSerializer startAtSerializer = new StartAtSerializer();
        INSTANCE = startAtSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.casting.StartAtSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(StartAt.class, startAtSerializer);
    }

    private StartAtSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull StartAt startAt, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (startAt == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(startAt, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(StartAt startAt, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("positionInMs");
        SimpleSerializers.serializePrimitiveInt(startAt.getPositionInMs(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackIdentifier");
        SimpleSerializers.serializeString(startAt.getTrackIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackIdentifierType");
        SimpleSerializers.serializeString(startAt.getTrackIdentifierType(), jsonGenerator, serializerProvider);
    }
}
