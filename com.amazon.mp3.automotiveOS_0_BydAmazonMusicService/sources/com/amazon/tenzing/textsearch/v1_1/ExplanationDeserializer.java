package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ExplanationDeserializer extends JsonDeserializer<Explanation> {
    public static final ExplanationDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ExplanationDeserializer explanationDeserializer = new ExplanationDeserializer();
        INSTANCE = explanationDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.ExplanationDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Explanation.class, explanationDeserializer);
    }

    private ExplanationDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Explanation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.tenzing.textsearch.v1_1#Explanation".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Explanation deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Explanation explanation = new Explanation();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("indexId".equals(currentName)) {
                explanation.setIndexId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("detail".equals(currentName)) {
                explanation.setDetail(ExplanationsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("match".equals(currentName)) {
                explanation.setMatch(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("value".equals(currentName)) {
                explanation.setValue(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                explanation.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clusterId".equals(currentName)) {
                explanation.setClusterId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (SearchIntents.EXTRA_QUERY.equals(currentName)) {
                explanation.setQuery(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return explanation;
    }
}
