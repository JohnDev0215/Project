package com.amazon.layout.music.model;

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
public class ProgramHintDeserializer extends JsonDeserializer<ProgramHint> {
    public static final ProgramHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ProgramHintDeserializer programHintDeserializer = new ProgramHintDeserializer();
        INSTANCE = programHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.ProgramHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ProgramHint.class, programHintDeserializer);
    }

    private ProgramHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ProgramHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#ProgramHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ProgramHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ProgramHint programHint = new ProgramHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isBundesligaFree".equals(currentName)) {
                programHint.setIsBundesligaFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isBundesligaPrime".equals(currentName)) {
                programHint.setIsBundesligaPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("programType".equals(currentName)) {
                programHint.setProgramType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("streamStatus".equals(currentName)) {
                programHint.setStreamStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isBundesligaUnlimited".equals(currentName)) {
                programHint.setIsBundesligaUnlimited(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("programId".equals(currentName)) {
                programHint.setProgramId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("streamType".equals(currentName)) {
                programHint.setStreamType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return programHint;
    }
}
