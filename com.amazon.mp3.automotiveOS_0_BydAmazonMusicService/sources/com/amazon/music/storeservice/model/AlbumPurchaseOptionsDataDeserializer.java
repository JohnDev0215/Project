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
public class AlbumPurchaseOptionsDataDeserializer extends JsonDeserializer<AlbumPurchaseOptionsData> {
    public static final AlbumPurchaseOptionsDataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AlbumPurchaseOptionsDataDeserializer albumPurchaseOptionsDataDeserializer = new AlbumPurchaseOptionsDataDeserializer();
        INSTANCE = albumPurchaseOptionsDataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.AlbumPurchaseOptionsDataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AlbumPurchaseOptionsData.class, albumPurchaseOptionsDataDeserializer);
    }

    private AlbumPurchaseOptionsDataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AlbumPurchaseOptionsData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#AlbumPurchaseOptionsData".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AlbumPurchaseOptionsData deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AlbumPurchaseOptionsData albumPurchaseOptionsData = new AlbumPurchaseOptionsData();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("displayStringBinding".equals(currentName)) {
                albumPurchaseOptionsData.setDisplayStringBinding(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayPrice".equals(currentName)) {
                albumPurchaseOptionsData.setDisplayPrice(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("digitalAsin".equals(currentName)) {
                albumPurchaseOptionsData.setDigitalAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currency".equals(currentName)) {
                albumPurchaseOptionsData.setCurrency(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayAlbumOnlyButton".equals(currentName)) {
                albumPurchaseOptionsData.setDisplayAlbumOnlyButton(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isAlreadyPurchased".equals(currentName)) {
                albumPurchaseOptionsData.setIsAlreadyPurchased(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("displayBuyButton".equals(currentName)) {
                albumPurchaseOptionsData.setDisplayBuyButton(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                albumPurchaseOptionsData.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayPreOrderButton".equals(currentName)) {
                albumPurchaseOptionsData.setDisplayPreOrderButton(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                albumPurchaseOptionsData.setPrice(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return albumPurchaseOptionsData;
    }
}
