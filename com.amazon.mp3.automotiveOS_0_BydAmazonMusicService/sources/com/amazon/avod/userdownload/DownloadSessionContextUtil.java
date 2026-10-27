package com.amazon.avod.userdownload;

import com.amazon.avod.json.JacksonCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadSessionContextUtil {
    @Nonnull
    public static Map<String, String> JSONToMap(@Nonnull String str) {
        try {
            return (Map) JacksonCache.OBJECT_MAPPER.readValue((String) Preconditions.checkNotNull(str, "json"), new TypeReference<HashMap<String, String>>() { // from class: com.amazon.avod.userdownload.DownloadSessionContextUtil.1
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("json", e);
        }
    }

    @Nonnull
    public static String mapToJSON(@Nonnull Map<String, String> map) {
        try {
            Preconditions.checkNotNull(map, "sessionContext");
            return JacksonCache.OBJECT_MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("sessionContext", e);
        }
    }
}
