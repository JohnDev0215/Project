package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentEncodingsDeserializer;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumHintDeserializer extends JsonDeserializer<AlbumHint> {
    public static final AlbumHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AlbumHintDeserializer albumHintDeserializer = new AlbumHintDeserializer();
        INSTANCE = albumHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.AlbumHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AlbumHint.class, albumHintDeserializer);
    }

    private AlbumHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AlbumHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#AlbumHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AlbumHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AlbumHint albumHint = new AlbumHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isMusicSubscription".equals(currentName)) {
                albumHint.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                albumHint.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("timeStamp".equals(currentName)) {
                albumHint.setTimeStamp(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                albumHint.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                albumHint.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                albumHint.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                albumHint.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                albumHint.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("recentlyPlayedEntityType".equals(currentName)) {
                albumHint.setRecentlyPlayedEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                albumHint.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                albumHint.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                albumHint.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                albumHint.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRecommended".equals(currentName)) {
                albumHint.setIsRecommended(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                albumHint.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                albumHint.setLanguagesOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTypes".equals(currentName)) {
                albumHint.setContentTypes(ContentTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return albumHint;
    }
}
