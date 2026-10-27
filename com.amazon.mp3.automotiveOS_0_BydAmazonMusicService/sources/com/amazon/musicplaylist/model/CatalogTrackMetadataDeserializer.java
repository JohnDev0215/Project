package com.amazon.musicplaylist.model;

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

/* JADX INFO: loaded from: classes5.dex */
public class CatalogTrackMetadataDeserializer extends JsonDeserializer<CatalogTrackMetadata> {
    public static final CatalogTrackMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogTrackMetadataDeserializer catalogTrackMetadataDeserializer = new CatalogTrackMetadataDeserializer();
        INSTANCE = catalogTrackMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.CatalogTrackMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogTrackMetadata.class, catalogTrackMetadataDeserializer);
    }

    private CatalogTrackMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogTrackMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#CatalogTrackMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogTrackMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogTrackMetadata catalogTrackMetadata = new CatalogTrackMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("freeOnDemandPlayable".equals(currentName)) {
                catalogTrackMetadata.setFreeOnDemandPlayable(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                catalogTrackMetadata.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArt".equals(currentName)) {
                catalogTrackMetadata.setAlbumArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("prime".equals(currentName)) {
                catalogTrackMetadata.setPrime(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("musicSubscription".equals(currentName)) {
                catalogTrackMetadata.setMusicSubscription(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                catalogTrackMetadata.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("objectId".equals(currentName)) {
                catalogTrackMetadata.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                catalogTrackMetadata.setArtists(ArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                catalogTrackMetadata.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogTrackMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                catalogTrackMetadata.setOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogTrackMetadata.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumDiscNum".equals(currentName)) {
                catalogTrackMetadata.setAlbumDiscNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("albumTrackNum".equals(currentName)) {
                catalogTrackMetadata.setAlbumTrackNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                catalogTrackMetadata.setDurationSeconds(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("internalTags".equals(currentName)) {
                catalogTrackMetadata.setInternalTags(InternalTagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumArtists".equals(currentName)) {
                catalogTrackMetadata.setAlbumArtists(ArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                catalogTrackMetadata.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("free".equals(currentName)) {
                catalogTrackMetadata.setFree(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("trackNum".equals(currentName)) {
                catalogTrackMetadata.setTrackNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("fileSizeKilobytes".equals(currentName)) {
                catalogTrackMetadata.setFileSizeKilobytes(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogTrackMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("purchased".equals(currentName)) {
                catalogTrackMetadata.setPurchased(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("newSinceLastMajorVersion".equals(currentName)) {
                catalogTrackMetadata.setNewSinceLastMajorVersion(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogTrackMetadata;
    }
}
