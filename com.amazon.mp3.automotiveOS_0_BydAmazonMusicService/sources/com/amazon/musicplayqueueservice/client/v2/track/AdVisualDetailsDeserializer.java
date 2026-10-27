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
public class AdVisualDetailsDeserializer extends JsonDeserializer<AdVisualDetails> {
    public static final AdVisualDetailsDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AdVisualDetailsDeserializer adVisualDetailsDeserializer = new AdVisualDetailsDeserializer();
        INSTANCE = adVisualDetailsDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.AdVisualDetailsDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AdVisualDetails.class, adVisualDetailsDeserializer);
    }

    private AdVisualDetailsDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AdVisualDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#AdVisualDetails".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AdVisualDetails deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AdVisualDetails adVisualDetails = new AdVisualDetails();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("adChoiceURL".equals(currentName)) {
                adVisualDetails.setAdChoiceURL(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clickThroughURL".equals(currentName)) {
                adVisualDetails.setClickThroughURL(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageUrl".equals(currentName)) {
                adVisualDetails.setImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("fileFormat".equals(currentName)) {
                adVisualDetails.setFileFormat(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("widthInPixels".equals(currentName)) {
                adVisualDetails.setWidthInPixels(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else if ("heightInPixels".equals(currentName)) {
                adVisualDetails.setHeightInPixels(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return adVisualDetails;
    }
}
