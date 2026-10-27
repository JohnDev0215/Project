package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.storeservice.model.AlbumListDeserializer;
import com.amazon.music.storeservice.model.GeneralMetadataDeserializer;
import com.amazon.music.storeservice.model.SimilarArtistListDeserializer;
import com.amazon.music.storeservice.model.TrackListDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataResponseDeserializer extends JsonDeserializer<ArtistDetailsMetadataResponse> {
    public static final ArtistDetailsMetadataResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistDetailsMetadataResponseDeserializer artistDetailsMetadataResponseDeserializer = new ArtistDetailsMetadataResponseDeserializer();
        INSTANCE = artistDetailsMetadataResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.ArtistDetailsMetadataResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ArtistDetailsMetadataResponse.class, artistDetailsMetadataResponseDeserializer);
    }

    private ArtistDetailsMetadataResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ArtistDetailsMetadataResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice#ArtistDetailsMetadataResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ArtistDetailsMetadataResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ArtistDetailsMetadataResponse artistDetailsMetadataResponse = new ArtistDetailsMetadataResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("metadata".equals(currentName)) {
                artistDetailsMetadataResponse.setMetadata(GeneralMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                artistDetailsMetadataResponse.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("requestedArtistAsin".equals(currentName)) {
                artistDetailsMetadataResponse.setRequestedArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumList".equals(currentName)) {
                artistDetailsMetadataResponse.setAlbumList(AlbumListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("similarArtistList".equals(currentName)) {
                artistDetailsMetadataResponse.setSimilarArtistList(SimilarArtistListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                artistDetailsMetadataResponse.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("globalArtistAsin".equals(currentName)) {
                artistDetailsMetadataResponse.setGlobalArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("nextTokenMap".equals(currentName)) {
                artistDetailsMetadataResponse.setNextTokenMap(NextTokenMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackList".equals(currentName)) {
                artistDetailsMetadataResponse.setTrackList(TrackListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return artistDetailsMetadataResponse;
    }
}
