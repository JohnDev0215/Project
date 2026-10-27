package com.amazon.avod.prs;

import com.amazon.avod.json.JacksonCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceParamsUtils {
    @Nonnull
    public static String formatParamsToJsonString(@Nonnull ResourceParams resourceParams) throws JsonProcessingException {
        Preconditions.checkNotNull(resourceParams, "params");
        return JacksonCache.OBJECT_MAPPER.writeValueAsString(resourceParams);
    }
}
