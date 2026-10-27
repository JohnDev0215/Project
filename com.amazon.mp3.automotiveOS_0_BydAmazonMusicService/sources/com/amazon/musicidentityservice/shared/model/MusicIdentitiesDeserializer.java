package com.amazon.musicidentityservice.shared.model;

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

/* JADX INFO: loaded from: classes3.dex */
public class MusicIdentitiesDeserializer extends JsonDeserializer<MusicIdentities> {
    public static final MusicIdentitiesDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicIdentitiesDeserializer musicIdentitiesDeserializer = new MusicIdentitiesDeserializer();
        INSTANCE = musicIdentitiesDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicidentityservice.shared.model.MusicIdentitiesDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MusicIdentities.class, musicIdentitiesDeserializer);
    }

    private MusicIdentitiesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MusicIdentities deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicidentityservice.shared.model#MusicIdentities".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MusicIdentities deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MusicIdentities musicIdentities = new MusicIdentities();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("musicConsumerCids".equals(currentName)) {
                musicIdentities.setMusicConsumerCids(MusicConsumerCidMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("libraryOwnerCids".equals(currentName)) {
                musicIdentities.setLibraryOwnerCids(LibraryOwnerCidListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("musicAccountCid".equals(currentName)) {
                musicIdentities.setMusicAccountCid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subscriptionOwnerCid".equals(currentName)) {
                musicIdentities.setSubscriptionOwnerCid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("recentsCids".equals(currentName)) {
                musicIdentities.setRecentsCids(RecentCidListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return musicIdentities;
    }
}
