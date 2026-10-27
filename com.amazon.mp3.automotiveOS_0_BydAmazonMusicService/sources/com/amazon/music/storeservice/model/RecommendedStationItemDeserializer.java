package com.amazon.music.storeservice.model;

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

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedStationItemDeserializer extends JsonDeserializer<RecommendedStationItem> {
    public static final RecommendedStationItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecommendedStationItemDeserializer recommendedStationItemDeserializer = new RecommendedStationItemDeserializer();
        INSTANCE = recommendedStationItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.RecommendedStationItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecommendedStationItem.class, recommendedStationItemDeserializer);
    }

    private RecommendedStationItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecommendedStationItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#RecommendedStationItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecommendedStationItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecommendedStationItem recommendedStationItem = new RecommendedStationItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isFree".equals(currentName)) {
                recommendedStationItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("stationTitle".equals(currentName)) {
                recommendedStationItem.setStationTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("stationImageUrl".equals(currentName)) {
                recommendedStationItem.setStationImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recommendedStationItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("stationKey".equals(currentName)) {
                recommendedStationItem.setStationKey(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subscriptionTierToDetails".equals(currentName)) {
                recommendedStationItem.setSubscriptionTierToDetails(StationSubscriptionTierDetailsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recommendedStationItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recommendedStationItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("justification".equals(currentName)) {
                recommendedStationItem.setJustification(JustificationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("seed".equals(currentName)) {
                recommendedStationItem.setSeed(SeedDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recommendedStationItem;
    }
}
