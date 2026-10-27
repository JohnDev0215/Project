package com.amazon.avod.config.internal;

import com.amazon.avod.json.KeyValueEntryParser;
import com.amazon.avod.util.json.JacksonJsonStreamParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonValidator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceConfigParser implements JacksonJsonStreamParser<ImmutableMap<String, String>> {
    private static final ImmutableMap<String, String> EMPTY_MAP = ImmutableMap.of();
    private final KeyValueEntryParser mMapParser = new KeyValueEntryParser();

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public ImmutableMap<String, String> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
        JsonToken jsonTokenNextToken = jsonParser.nextToken();
        ImmutableMap<String, String> immutableMap = null;
        while (JsonValidator.isInsideObject(jsonTokenNextToken)) {
            if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if ("additionalConfig".equals(currentName)) {
                    immutableMap = this.mMapParser.parse(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
            jsonTokenNextToken = jsonParser.nextToken();
        }
        return (ImmutableMap) MoreObjects.firstNonNull(immutableMap, EMPTY_MAP);
    }
}
