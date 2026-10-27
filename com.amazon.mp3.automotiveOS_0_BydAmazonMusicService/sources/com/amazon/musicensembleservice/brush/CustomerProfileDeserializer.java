package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicprofileservice.ProfileInfoDeserializer;
import com.amazon.musicrelationshipservice.model.EdgeDeserializer;
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
public class CustomerProfileDeserializer extends JsonDeserializer<CustomerProfile> {
    public static final CustomerProfileDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CustomerProfileDeserializer customerProfileDeserializer = new CustomerProfileDeserializer();
        INSTANCE = customerProfileDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.CustomerProfileDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CustomerProfile.class, customerProfileDeserializer);
    }

    private CustomerProfileDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CustomerProfile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#CustomerProfile".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CustomerProfile deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CustomerProfile customerProfile = new CustomerProfile();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("blockRef".equals(currentName)) {
                customerProfile.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isNewFollower".equals(currentName)) {
                customerProfile.setIsNewFollower(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                customerProfile.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("hasFollowersNotification".equals(currentName)) {
                customerProfile.setHasFollowersNotification(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("profileDetails".equals(currentName)) {
                customerProfile.setProfileDetails(ProfileInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("relationship".equals(currentName)) {
                customerProfile.setRelationship(EdgeDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("customerProfileStats".equals(currentName)) {
                customerProfile.setCustomerProfileStats(CustomerProfileStatsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("widgets".equals(currentName)) {
                customerProfile.setWidgets(CustomerProfileWidgetListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return customerProfile;
    }
}
