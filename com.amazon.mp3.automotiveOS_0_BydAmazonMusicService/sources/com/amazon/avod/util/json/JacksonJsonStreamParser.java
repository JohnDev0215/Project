package com.amazon.avod.util.json;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface JacksonJsonStreamParser<T> {
    @Nonnull
    T parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException;
}
