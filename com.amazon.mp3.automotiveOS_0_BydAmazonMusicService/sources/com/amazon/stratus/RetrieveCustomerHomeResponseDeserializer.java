package com.amazon.stratus;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCustomerHomeResponseDeserializer extends JsonDeserializer<RetrieveCustomerHomeResponse> {
    public static final RetrieveCustomerHomeResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveCustomerHomeResponseDeserializer retrieveCustomerHomeResponseDeserializer = new RetrieveCustomerHomeResponseDeserializer();
        INSTANCE = retrieveCustomerHomeResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrieveCustomerHomeResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RetrieveCustomerHomeResponse.class, retrieveCustomerHomeResponseDeserializer);
    }

    private RetrieveCustomerHomeResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RetrieveCustomerHomeResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#RetrieveCustomerHomeResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RetrieveCustomerHomeResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RetrieveCustomerHomeResponse retrieveCustomerHomeResponse = new RetrieveCustomerHomeResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("accountExists".equals(currentName)) {
                retrieveCustomerHomeResponse.setAccountExists(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                retrieveCustomerHomeResponse.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("customerId".equals(currentName)) {
                retrieveCustomerHomeResponse.setCustomerId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("accountStatus".equals(currentName)) {
                retrieveCustomerHomeResponse.setAccountStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("storageRealm".equals(currentName)) {
                retrieveCustomerHomeResponse.setStorageRealm(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                retrieveCustomerHomeResponse.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return retrieveCustomerHomeResponse;
    }
}
