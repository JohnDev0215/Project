package com.amazon.stratus;

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
public class RetrievePrivacyCompliancePreferencesResponseDeserializer extends JsonDeserializer<RetrievePrivacyCompliancePreferencesResponse> {
    public static final RetrievePrivacyCompliancePreferencesResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrievePrivacyCompliancePreferencesResponseDeserializer retrievePrivacyCompliancePreferencesResponseDeserializer = new RetrievePrivacyCompliancePreferencesResponseDeserializer();
        INSTANCE = retrievePrivacyCompliancePreferencesResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RetrievePrivacyCompliancePreferencesResponse.class, retrievePrivacyCompliancePreferencesResponseDeserializer);
    }

    private RetrievePrivacyCompliancePreferencesResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RetrievePrivacyCompliancePreferencesResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#RetrievePrivacyCompliancePreferencesResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RetrievePrivacyCompliancePreferencesResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RetrievePrivacyCompliancePreferencesResponse retrievePrivacyCompliancePreferencesResponse = new RetrievePrivacyCompliancePreferencesResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("preferencesByNamespace".equals(currentName)) {
                retrievePrivacyCompliancePreferencesResponse.setPreferencesByNamespace(PreferencesByNamespaceMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return retrievePrivacyCompliancePreferencesResponse;
    }
}
