package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicensembleservice.StationSubscriptionTierDetailsDeserializer;
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
public class StationHintDeserializer extends JsonDeserializer<StationHint> {
    public static final StationHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StationHintDeserializer stationHintDeserializer = new StationHintDeserializer();
        INSTANCE = stationHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.StationHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(StationHint.class, stationHintDeserializer);
    }

    private StationHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public StationHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#StationHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public StationHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        StationHint stationHint = new StationHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("timeStamp".equals(currentName)) {
                stationHint.setTimeStamp(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                stationHint.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("subscriptionTierToDetails".equals(currentName)) {
                stationHint.setSubscriptionTierToDetails(StationSubscriptionTierDetailsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                stationHint.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isRecommended".equals(currentName)) {
                stationHint.setIsRecommended(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("recentlyPlayedEntityType".equals(currentName)) {
                stationHint.setRecentlyPlayedEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                stationHint.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("stationKey".equals(currentName)) {
                stationHint.setStationKey(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("stationImageUrl".equals(currentName)) {
                stationHint.setStationImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                stationHint.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                stationHint.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("seedId".equals(currentName)) {
                stationHint.setSeedId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return stationHint;
    }
}
