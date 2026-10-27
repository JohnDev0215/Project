package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryAlbumDeserializer extends JsonDeserializer<LibraryAlbum> {
    public static final LibraryAlbumDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LibraryAlbumDeserializer libraryAlbumDeserializer = new LibraryAlbumDeserializer();
        INSTANCE = libraryAlbumDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.LibraryAlbumDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LibraryAlbum.class, libraryAlbumDeserializer);
    }

    private LibraryAlbumDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LibraryAlbum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.music.platform.model#LibraryAlbum".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LibraryAlbum deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LibraryAlbum libraryAlbum = new LibraryAlbum();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("artistAsin".equals(currentName)) {
                libraryAlbum.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                libraryAlbum.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                libraryAlbum.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contributors".equals(currentName)) {
                libraryAlbum.setContributors(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                libraryAlbum.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                libraryAlbum.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                libraryAlbum.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                libraryAlbum.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("objectId".equals(currentName)) {
                libraryAlbum.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                libraryAlbum.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("releaseDate".equals(currentName)) {
                libraryAlbum.setReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                libraryAlbum.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                libraryAlbum.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                libraryAlbum.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                libraryAlbum.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                libraryAlbum.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                libraryAlbum.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                libraryAlbum.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                libraryAlbum.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                libraryAlbum.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                libraryAlbum.setRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return libraryAlbum;
    }
}
