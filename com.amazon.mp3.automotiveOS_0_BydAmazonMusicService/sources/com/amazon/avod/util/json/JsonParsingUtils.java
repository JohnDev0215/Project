package com.amazon.avod.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class JsonParsingUtils {
    public static <T> T checkNotNull(@Nullable T t, @Nonnull Object obj, @Nonnull String str) throws JsonContractException {
        Preconditions.checkNotNull(obj, "parser");
        Preconditions.checkNotNull(str, "expectedJsonFieldName");
        if (t != null) {
            return t;
        }
        throw new JsonContractException(obj.getClass().getCanonicalName() + " expected " + str + " and it wasn't found (or failed to parse) in the json");
    }

    public static void throwIfNotObject(@Nonnull JsonNode jsonNode, @Nonnull String str) throws JsonContractException {
        Preconditions.checkNotNull(jsonNode, "node");
        Preconditions.checkNotNull(str, "fieldName");
        if (jsonNode.isObject()) {
            return;
        }
        throw new JsonContractException("Expected json object for parsing object of type " + str);
    }

    public static void skipIfNestedObject(@Nullable JsonToken jsonToken, @Nonnull JsonParser jsonParser) throws IOException {
        if (JsonToken.START_ARRAY == jsonToken || JsonToken.START_OBJECT == jsonToken) {
            jsonParser.skipChildren();
        }
    }
}
