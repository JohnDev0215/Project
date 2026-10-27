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
public class TrackSerializer extends JsonSerializer<Track> {
    public static final TrackSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackSerializer trackSerializer = new TrackSerializer();
        INSTANCE = trackSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.casting.TrackSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Track.class, trackSerializer);
    }

    private TrackSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Track track, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (track == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(track, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Track track, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("shuffledIndex");
        SimpleSerializers.serializeInteger(track.getShuffledIndex(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsContextIdentifier");
        SimpleSerializers.serializeString(track.getMetricsContextIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identifier");
        SimpleSerializers.serializeString(track.getIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identifierType");
        SimpleSerializers.serializeString(track.getIdentifierType(), jsonGenerator, serializerProvider);
    }
}
