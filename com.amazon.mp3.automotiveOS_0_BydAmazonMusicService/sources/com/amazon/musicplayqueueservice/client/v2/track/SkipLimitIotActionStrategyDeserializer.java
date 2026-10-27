package com.amazon.musicplayqueueservice.client.v2.track;

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
public class SkipLimitIotActionStrategyDeserializer extends JsonDeserializer<SkipLimitIotActionStrategy> {
    public static final SkipLimitIotActionStrategyDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SkipLimitIotActionStrategyDeserializer skipLimitIotActionStrategyDeserializer = new SkipLimitIotActionStrategyDeserializer();
        INSTANCE = skipLimitIotActionStrategyDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.SkipLimitIotActionStrategyDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SkipLimitIotActionStrategy.class, skipLimitIotActionStrategyDeserializer);
    }

    private SkipLimitIotActionStrategyDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SkipLimitIotActionStrategy deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#SkipLimitIotActionStrategy".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SkipLimitIotActionStrategy deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SkipLimitIotActionStrategy skipLimitIotActionStrategy = new SkipLimitIotActionStrategy();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("skipsRemainingActionStrategy".equals(currentName)) {
                skipLimitIotActionStrategy.setSkipsRemainingActionStrategy(AbstractActionStrategyDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("thingShadowName".equals(currentName)) {
                skipLimitIotActionStrategy.setThingShadowName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("noSkipsRemainingActionStrategy".equals(currentName)) {
                skipLimitIotActionStrategy.setNoSkipsRemainingActionStrategy(AbstractActionStrategyDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return skipLimitIotActionStrategy;
    }
}
