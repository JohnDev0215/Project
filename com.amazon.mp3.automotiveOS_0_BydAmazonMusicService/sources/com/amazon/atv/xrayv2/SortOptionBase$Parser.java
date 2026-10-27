package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class SortOptionBase$Parser extends PolymorphicJsonParser<Object> {
    public SortOptionBase$Parser(@Nonnull ObjectMapper objectMapper) {
        super(objectMapper, ParserConstants.SORTOPTIONBASE_TYPE_PARSER_MAP_FUNCTION);
    }
}
