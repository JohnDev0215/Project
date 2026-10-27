package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class SharedPlaylistHintDeserializer extends JsonDeserializer<SharedPlaylistHint> {
    public static final SharedPlaylistHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SharedPlaylistHintDeserializer sharedPlaylistHintDeserializer = new SharedPlaylistHintDeserializer();
        INSTANCE = sharedPlaylistHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.SharedPlaylistHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SharedPlaylistHint.class, sharedPlaylistHintDeserializer);
    }

    private SharedPlaylistHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SharedPlaylistHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#SharedPlaylistHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SharedPlaylistHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SharedPlaylistHint sharedPlaylistHint = new SharedPlaylistHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("objectId".equals(currentName)) {
                sharedPlaylistHint.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playlistImageUrl".equals(currentName)) {
                sharedPlaylistHint.setPlaylistImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("timeStamp".equals(currentName)) {
                sharedPlaylistHint.setTimeStamp(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                sharedPlaylistHint.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                sharedPlaylistHint.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("recentlyPlayedEntityType".equals(currentName)) {
                sharedPlaylistHint.setRecentlyPlayedEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isCustomerFollowing".equals(currentName)) {
                sharedPlaylistHint.setIsCustomerFollowing(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("shareBlurb".equals(currentName)) {
                sharedPlaylistHint.setShareBlurb(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                sharedPlaylistHint.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                sharedPlaylistHint.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                sharedPlaylistHint.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return sharedPlaylistHint;
    }
}
