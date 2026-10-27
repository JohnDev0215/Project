package com.amazon.musicplaylist.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class TrackIdSerializer extends JsonSerializer<TrackId> {
    public static final TrackIdSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackIdSerializer trackIdSerializer = new TrackIdSerializer();
        INSTANCE = trackIdSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.TrackIdSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TrackId.class, trackIdSerializer);
    }

    private TrackIdSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TrackId trackId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (trackId == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (trackId instanceof LibraryTrack) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicplaylist.model#LibraryTrack");
            LibraryTrackSerializer.INSTANCE.serializeFields((LibraryTrack) trackId, jsonGenerator, serializerProvider);
        } else {
            boolean z = trackId instanceof CatalogTrack;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(trackId, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("com.amazon.musicplaylist.model#CatalogTrack");
                CatalogTrackSerializer.INSTANCE.serializeFields((CatalogTrack) trackId, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TrackId trackId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
