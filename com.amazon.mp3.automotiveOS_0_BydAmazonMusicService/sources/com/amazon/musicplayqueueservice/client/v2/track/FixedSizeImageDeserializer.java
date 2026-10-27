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
public class FixedSizeImageDeserializer extends JsonDeserializer<FixedSizeImage> {
    public static final FixedSizeImageDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FixedSizeImageDeserializer fixedSizeImageDeserializer = new FixedSizeImageDeserializer();
        INSTANCE = fixedSizeImageDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.FixedSizeImageDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FixedSizeImage.class, fixedSizeImageDeserializer);
    }

    private FixedSizeImageDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FixedSizeImage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#FixedSizeImage".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FixedSizeImage deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FixedSizeImage fixedSizeImage = new FixedSizeImage();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("fileFormat".equals(currentName)) {
                fixedSizeImage.setFileFormat(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("heightInPixels".equals(currentName)) {
                fixedSizeImage.setHeightInPixels(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else if ("clickUrl".equals(currentName)) {
                fixedSizeImage.setClickUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("widthInPixels".equals(currentName)) {
                fixedSizeImage.setWidthInPixels(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else if ("imageUrl".equals(currentName)) {
                fixedSizeImage.setImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return fixedSizeImage;
    }
}
