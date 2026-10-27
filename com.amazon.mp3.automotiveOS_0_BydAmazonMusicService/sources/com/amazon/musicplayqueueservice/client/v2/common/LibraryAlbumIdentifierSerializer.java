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
public class LibraryAlbumIdentifierSerializer extends JsonSerializer<LibraryAlbumIdentifier> {
    public static final LibraryAlbumIdentifierSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LibraryAlbumIdentifierSerializer libraryAlbumIdentifierSerializer = new LibraryAlbumIdentifierSerializer();
        INSTANCE = libraryAlbumIdentifierSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.common.LibraryAlbumIdentifierSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LibraryAlbumIdentifier.class, libraryAlbumIdentifierSerializer);
    }

    private LibraryAlbumIdentifierSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LibraryAlbumIdentifier libraryAlbumIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (libraryAlbumIdentifier == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(libraryAlbumIdentifier, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LibraryAlbumIdentifier libraryAlbumIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("artistName");
        SimpleSerializers.serializeString(libraryAlbumIdentifier.getArtistName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("albumName");
        SimpleSerializers.serializeString(libraryAlbumIdentifier.getAlbumName(), jsonGenerator, serializerProvider);
    }
}
