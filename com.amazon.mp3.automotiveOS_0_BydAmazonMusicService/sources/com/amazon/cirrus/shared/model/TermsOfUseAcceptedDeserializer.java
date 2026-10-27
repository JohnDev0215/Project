package com.amazon.cirrus.shared.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class TermsOfUseAcceptedDeserializer extends JsonDeserializer<TermsOfUseAccepted> {
    public static final TermsOfUseAcceptedDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TermsOfUseAcceptedDeserializer termsOfUseAcceptedDeserializer = new TermsOfUseAcceptedDeserializer();
        INSTANCE = termsOfUseAcceptedDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.shared.model.TermsOfUseAcceptedDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TermsOfUseAccepted.class, termsOfUseAcceptedDeserializer);
    }

    private TermsOfUseAcceptedDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TermsOfUseAccepted deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.cirrus.shared.model#TermsOfUseAccepted".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TermsOfUseAccepted deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TermsOfUseAccepted termsOfUseAccepted = new TermsOfUseAccepted();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("acceptedDate".equals(currentName)) {
                termsOfUseAccepted.setAcceptedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                termsOfUseAccepted.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                termsOfUseAccepted.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                termsOfUseAccepted.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                termsOfUseAccepted.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return termsOfUseAccepted;
    }
}
