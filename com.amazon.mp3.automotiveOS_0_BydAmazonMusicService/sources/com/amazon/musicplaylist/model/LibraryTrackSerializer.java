package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class LibraryTrackSerializer extends JsonSerializer<LibraryTrack> {
    public static final LibraryTrackSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LibraryTrackSerializer libraryTrackSerializer = new LibraryTrackSerializer();
        INSTANCE = libraryTrackSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.LibraryTrackSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LibraryTrack.class, libraryTrackSerializer);
    }

    private LibraryTrackSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LibraryTrack libraryTrack, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (libraryTrack == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(libraryTrack, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LibraryTrack libraryTrack, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("objectId");
        SimpleSerializers.serializeString(libraryTrack.getObjectId(), jsonGenerator, serializerProvider);
    }
}
