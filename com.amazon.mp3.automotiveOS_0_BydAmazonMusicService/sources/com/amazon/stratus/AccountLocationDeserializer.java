package com.amazon.stratus;

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

/* JADX INFO: loaded from: classes5.dex */
public class AccountLocationDeserializer extends JsonDeserializer<AccountLocation> {
    public static final AccountLocationDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AccountLocationDeserializer accountLocationDeserializer = new AccountLocationDeserializer();
        INSTANCE = accountLocationDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.AccountLocationDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AccountLocation.class, accountLocationDeserializer);
    }

    private AccountLocationDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AccountLocation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#AccountLocation".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AccountLocation deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AccountLocation accountLocation = new AccountLocation();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("migrationCandidate".equals(currentName)) {
                accountLocation.setMigrationCandidate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currentCountryOfResidence".equals(currentName)) {
                accountLocation.setCurrentCountryOfResidence(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currentMarketplaceId".equals(currentName)) {
                accountLocation.setCurrentMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("targetMarketplaceId".equals(currentName)) {
                accountLocation.setTargetMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("targetCountryOfResidence".equals(currentName)) {
                accountLocation.setTargetCountryOfResidence(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return accountLocation;
    }
}
