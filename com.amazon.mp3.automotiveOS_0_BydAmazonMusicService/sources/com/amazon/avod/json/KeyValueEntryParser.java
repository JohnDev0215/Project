package com.amazon.avod.json;

import com.amazon.avod.util.DLog;
import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class KeyValueEntryParser implements JacksonJsonStreamParser<ImmutableMap<String, String>> {
    private final EntryParser mEntryParser = new EntryParser();

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public ImmutableMap<String, String> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        while (true) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    if ("values".equals(currentName)) {
                        parseMapValues(jsonParser, builder);
                    } else {
                        jsonParser.skipChildren();
                    }
                }
            } else {
                return builder.build();
            }
        }
    }

    private void parseMapValues(@Nonnull JsonParser jsonParser, @Nonnull ImmutableMap.Builder<String, String> builder) throws JsonContractException, IOException {
        JsonValidator.checkEqual(JsonToken.START_ARRAY, jsonParser.getCurrentToken(), jsonParser);
        while (JsonValidator.isInsideArray(jsonParser.nextToken())) {
            try {
                builder.put(this.mEntryParser.parse(jsonParser));
            } catch (JsonContractException e) {
                DLog.exceptionf(e, "Encountered JsonContractException parsing list entry for additionalConfig; will drop from the list", new Object[0]);
            }
        }
    }

    private static class EntryParser implements JacksonJsonParser<Map.Entry<String, String>> {
        private EntryParser() {
        }

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Map.Entry<String, String> parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            return new AbstractMap.SimpleEntry(jsonNode.get("key").asText(), jsonNode.get("value").asText());
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Map.Entry<String, String> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            String text = null;
            String text2 = null;
            while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    String strIntern = jsonParser.getCurrentName().intern();
                    if ("key".equals(strIntern)) {
                        jsonParser.nextToken();
                        text = jsonParser.getText();
                    } else if ("value".equals(strIntern)) {
                        jsonParser.nextToken();
                        text2 = jsonParser.getText();
                    } else {
                        JsonParsingUtils.skipIfNestedObject(jsonTokenNextToken, jsonParser);
                    }
                }
                jsonTokenNextToken = jsonParser.nextToken();
            }
            JsonParsingUtils.checkNotNull(text, this, "key");
            JsonParsingUtils.checkNotNull(text2, this, text);
            return new AbstractMap.SimpleEntry(text, text2);
        }
    }
}
