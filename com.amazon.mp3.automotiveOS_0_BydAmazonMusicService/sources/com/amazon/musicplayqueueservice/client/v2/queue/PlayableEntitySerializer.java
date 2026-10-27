package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifierSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PlayableEntitySerializer extends JsonSerializer<PlayableEntity> {
    public static final PlayableEntitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlayableEntitySerializer playableEntitySerializer = new PlayableEntitySerializer();
        INSTANCE = playableEntitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.queue.PlayableEntitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlayableEntity.class, playableEntitySerializer);
    }

    private PlayableEntitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlayableEntity playableEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playableEntity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playableEntity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlayableEntity playableEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("metricsContextIdentifier");
        SimpleSerializers.serializeString(playableEntity.getMetricsContextIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        AbstractPlayableEntityIdentifierSerializer.INSTANCE.serialize(playableEntity.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("shuffledIndex");
        SimpleSerializers.serializeInteger(playableEntity.getShuffledIndex(), jsonGenerator, serializerProvider);
    }
}
