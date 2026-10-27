package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistHintDeserializer extends JsonDeserializer<PlaylistHint> {
    public static final PlaylistHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaylistHintDeserializer playlistHintDeserializer = new PlaylistHintDeserializer();
        INSTANCE = playlistHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.PlaylistHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaylistHint.class, playlistHintDeserializer);
    }

    private PlaylistHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaylistHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#PlaylistHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaylistHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlaylistHint playlistHint = new PlaylistHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isSonicRush".equals(currentName)) {
                playlistHint.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("contentTypes".equals(currentName)) {
                playlistHint.setContentTypes(ContentTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                playlistHint.setLanguagesOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                playlistHint.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                playlistHint.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("timeStamp".equals(currentName)) {
                playlistHint.setTimeStamp(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("isRecommended".equals(currentName)) {
                playlistHint.setIsRecommended(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                playlistHint.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                playlistHint.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                playlistHint.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                playlistHint.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                playlistHint.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                playlistHint.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                playlistHint.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("recentlyPlayedEntityType".equals(currentName)) {
                playlistHint.setRecentlyPlayedEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playlistHint;
    }
}
