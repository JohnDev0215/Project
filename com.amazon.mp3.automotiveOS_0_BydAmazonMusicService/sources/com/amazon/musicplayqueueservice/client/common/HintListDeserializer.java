package com.amazon.musicplayqueueservice.client.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class HintListDeserializer extends JsonDeserializer<List<Hint>> {
    public static final JsonDeserializer<List<Hint>> INSTANCE = new HintListDeserializer();

    private HintListDeserializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public List<Hint> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_ARRAY) {
            throw new JsonParseException(jsonParser, "Expected start of array, got " + currentToken, jsonParser.getTokenLocation());
        }
        ArrayList arrayList = new ArrayList();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            if (jsonParser.isClosed()) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            arrayList.add(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
        }
        return arrayList;
    }
}
