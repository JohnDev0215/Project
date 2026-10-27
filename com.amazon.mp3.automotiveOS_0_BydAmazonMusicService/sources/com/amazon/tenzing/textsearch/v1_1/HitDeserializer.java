package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.platform.model.DocumentDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class HitDeserializer extends JsonDeserializer<Hit> {
    public static final HitDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HitDeserializer hitDeserializer = new HitDeserializer();
        INSTANCE = hitDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.HitDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Hit.class, hitDeserializer);
    }

    private HitDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Hit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.tenzing.textsearch.v1_1#Hit".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Hit deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Hit hit = new Hit();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("highlights".equals(currentName)) {
                hit.setHighlights(HighlightsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                hit.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (SearchIntents.EXTRA_QUERY.equals(currentName)) {
                hit.setQuery(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("document".equals(currentName)) {
                hit.setDocument(DocumentDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("score".equals(currentName)) {
                hit.setScore(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("explanation".equals(currentName)) {
                hit.setExplanation(ExplanationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return hit;
    }
}
