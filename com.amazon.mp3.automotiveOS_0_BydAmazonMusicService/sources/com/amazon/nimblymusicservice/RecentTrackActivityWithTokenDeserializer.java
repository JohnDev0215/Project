package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class RecentTrackActivityWithTokenDeserializer extends JsonDeserializer<RecentTrackActivityWithToken> {
    public static final RecentTrackActivityWithTokenDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecentTrackActivityWithTokenDeserializer recentTrackActivityWithTokenDeserializer = new RecentTrackActivityWithTokenDeserializer();
        INSTANCE = recentTrackActivityWithTokenDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.RecentTrackActivityWithTokenDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecentTrackActivityWithToken.class, recentTrackActivityWithTokenDeserializer);
    }

    private RecentTrackActivityWithTokenDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecentTrackActivityWithToken deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.nimblymusicservice#RecentTrackActivityWithToken".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecentTrackActivityWithToken deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecentTrackActivityWithToken recentTrackActivityWithToken = new RecentTrackActivityWithToken();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                recentTrackActivityWithToken.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("recentTrackList".equals(currentName)) {
                recentTrackActivityWithToken.setRecentTrackList(RecentTrackActivityListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("nextToken".equals(currentName)) {
                recentTrackActivityWithToken.setNextToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recentTrackActivityWithToken;
    }
}
