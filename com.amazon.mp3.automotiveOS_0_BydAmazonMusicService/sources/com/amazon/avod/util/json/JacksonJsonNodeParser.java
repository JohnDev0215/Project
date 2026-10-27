package com.amazon.avod.util.json;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface JacksonJsonNodeParser<T> {
    @Nonnull
    T parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException;
}
