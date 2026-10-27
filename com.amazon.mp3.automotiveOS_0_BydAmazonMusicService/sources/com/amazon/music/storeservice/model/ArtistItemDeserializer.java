package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class ArtistItemDeserializer extends JsonDeserializer<ArtistItem> {
    public static final ArtistItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistItemDeserializer artistItemDeserializer = new ArtistItemDeserializer();
        INSTANCE = artistItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.ArtistItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ArtistItem.class, artistItemDeserializer);
    }

    private ArtistItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ArtistItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#ArtistItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ArtistItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ArtistItem artistItem = new ArtistItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("globalAsin".equals(currentName)) {
                artistItem.setGlobalAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                artistItem.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                artistItem.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contributorAsin".equals(currentName)) {
                artistItem.setContributorAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("dmid".equals(currentName)) {
                artistItem.setDmid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("itemIndex".equals(currentName)) {
                artistItem.setItemIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                artistItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                artistItem.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageFull".equals(currentName)) {
                artistItem.setImageFull(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return artistItem;
    }
}
