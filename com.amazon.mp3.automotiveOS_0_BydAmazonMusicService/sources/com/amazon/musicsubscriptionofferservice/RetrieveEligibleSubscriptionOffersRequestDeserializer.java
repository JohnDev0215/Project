package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContextDeserializer;
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
public class RetrieveEligibleSubscriptionOffersRequestDeserializer extends JsonDeserializer<RetrieveEligibleSubscriptionOffersRequest> {
    public static final RetrieveEligibleSubscriptionOffersRequestDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveEligibleSubscriptionOffersRequestDeserializer retrieveEligibleSubscriptionOffersRequestDeserializer = new RetrieveEligibleSubscriptionOffersRequestDeserializer();
        INSTANCE = retrieveEligibleSubscriptionOffersRequestDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersRequestDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RetrieveEligibleSubscriptionOffersRequest.class, retrieveEligibleSubscriptionOffersRequestDeserializer);
    }

    private RetrieveEligibleSubscriptionOffersRequestDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RetrieveEligibleSubscriptionOffersRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicsubscriptionofferservice#RetrieveEligibleSubscriptionOffersRequest".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RetrieveEligibleSubscriptionOffersRequest deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest = new RetrieveEligibleSubscriptionOffersRequest();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("filterQuery".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setFilterQuery(QueryDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("ipAddress".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setIpAddress(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("customerId".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setCustomerId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_ID.equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setDeviceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clientId".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setClientId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("customerPreferenceRequest".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setCustomerPreferenceRequest(CustomerPreferenceRequestDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_TYPE.equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setDeviceType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("musicRequestIdentityContext".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setMusicRequestIdentityContext(MusicRequestIdentityContextDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("requestConfig".equals(currentName)) {
                retrieveEligibleSubscriptionOffersRequest.setRequestConfig(RequestConfigDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return retrieveEligibleSubscriptionOffersRequest;
    }
}
