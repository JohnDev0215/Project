package com.amazon.avod.util.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class JsonValidator {
    public static void checkEqual(@Nonnull JsonToken jsonToken, @Nullable JsonToken jsonToken2, @Nonnull JsonParser jsonParser) throws JsonParseException {
        Preconditions.checkNotNull(jsonToken, "expected");
        Preconditions.checkNotNull(jsonParser, "parser");
        if (jsonToken != jsonToken2) {
            throw new JsonParseException(String.format("JsonTokens do not match: expected=%s, actual=%s", jsonToken, jsonToken2), jsonParser.getCurrentLocation());
        }
    }

    public static boolean isInsideObject(@Nullable JsonToken jsonToken) {
        return (jsonToken == null || jsonToken == JsonToken.END_OBJECT) ? false : true;
    }

    public static boolean isInsideArray(@Nullable JsonToken jsonToken) {
        return (jsonToken == null || jsonToken == JsonToken.END_ARRAY) ? false : true;
    }
}
