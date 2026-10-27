package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
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
public class TrackPurchaseOptionsDataDeserializer extends JsonDeserializer<TrackPurchaseOptionsData> {
    public static final TrackPurchaseOptionsDataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackPurchaseOptionsDataDeserializer trackPurchaseOptionsDataDeserializer = new TrackPurchaseOptionsDataDeserializer();
        INSTANCE = trackPurchaseOptionsDataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.TrackPurchaseOptionsDataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TrackPurchaseOptionsData.class, trackPurchaseOptionsDataDeserializer);
    }

    private TrackPurchaseOptionsDataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TrackPurchaseOptionsData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#TrackPurchaseOptionsData".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TrackPurchaseOptionsData deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TrackPurchaseOptionsData trackPurchaseOptionsData = new TrackPurchaseOptionsData();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("displayAlbumOnlyButton".equals(currentName)) {
                trackPurchaseOptionsData.setDisplayAlbumOnlyButton(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isAlreadyPurchased".equals(currentName)) {
                trackPurchaseOptionsData.setIsAlreadyPurchased(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("displayBuyButton".equals(currentName)) {
                trackPurchaseOptionsData.setDisplayBuyButton(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("currency".equals(currentName)) {
                trackPurchaseOptionsData.setCurrency(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayStringBinding".equals(currentName)) {
                trackPurchaseOptionsData.setDisplayStringBinding(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayPrice".equals(currentName)) {
                trackPurchaseOptionsData.setDisplayPrice(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                trackPurchaseOptionsData.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                trackPurchaseOptionsData.setPrice(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return trackPurchaseOptionsData;
    }
}
