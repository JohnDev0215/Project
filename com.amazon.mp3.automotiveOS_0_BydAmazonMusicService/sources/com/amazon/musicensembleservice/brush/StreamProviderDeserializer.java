package com.amazon.musicensembleservice.brush;

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

/* JADX INFO: loaded from: classes2.dex */
public class StreamProviderDeserializer extends JsonDeserializer<StreamProvider> {
    public static final StreamProviderDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StreamProviderDeserializer streamProviderDeserializer = new StreamProviderDeserializer();
        INSTANCE = streamProviderDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.StreamProviderDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(StreamProvider.class, streamProviderDeserializer);
    }

    private StreamProviderDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public StreamProvider deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#StreamProvider".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public StreamProvider deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        StreamProvider streamProvider = new StreamProvider();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("wordmarkImages".equals(currentName)) {
                streamProvider.setWordmarkImages(ImagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryTitle".equals(currentName)) {
                streamProvider.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                streamProvider.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                streamProvider.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                streamProvider.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                streamProvider.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                streamProvider.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return streamProvider;
    }
}
