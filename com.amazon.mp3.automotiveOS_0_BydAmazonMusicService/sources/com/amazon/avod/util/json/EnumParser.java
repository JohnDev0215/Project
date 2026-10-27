package com.amazon.avod.util.json;

import CoreInterface.v1_0.TemplateLaunchMode;
import com.amazon.avod.util.json.NamedEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.lang.Enum;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class EnumParser<E extends Enum<E> & NamedEnum> implements JacksonJsonParser<E> {
    private final Class<E> mEnumType;

    public static <T extends Enum<T> & NamedEnum> EnumParser<T> newParser(Class<T> cls) {
        return new EnumParser<>(cls);
    }

    private EnumParser(@Nonnull Class<E> cls) {
        this.mEnumType = (Class) Preconditions.checkNotNull(cls, "enumType");
    }

    /* JADX WARN: Incorrect return type in method signature: (Lcom/fasterxml/jackson/databind/JsonNode;)TE; */
    @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
    public Enum parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
        Preconditions.checkNotNull(jsonNode, "jsonNode");
        if (!jsonNode.isTextual()) {
            throw new JsonContractException("expected string(enum)");
        }
        return findValue(jsonNode.textValue());
    }

    /* JADX WARN: Incorrect return type in method signature: (Lcom/fasterxml/jackson/core/JsonParser;)TE; */
    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    public Enum parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        Preconditions.checkNotNull(jsonParser, "jsonParser");
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_STRING && currentToken != JsonToken.FIELD_NAME) {
            throw new JsonContractException("expected string(enum)");
        }
        return findValue(jsonParser.getText());
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/String;)TE; */
    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    private Enum findValue(@Nonnull String str) throws JsonContractException {
        Preconditions.checkNotNull(str);
        for (TemplateLaunchMode templateLaunchMode : (Enum[]) this.mEnumType.getEnumConstants()) {
            if (str.equals(((NamedEnum) templateLaunchMode).getValue())) {
                return templateLaunchMode;
            }
        }
        throw new JsonContractException(String.format("value %s didn't resolve to %s enum", str, this.mEnumType.getSimpleName()));
    }
}
