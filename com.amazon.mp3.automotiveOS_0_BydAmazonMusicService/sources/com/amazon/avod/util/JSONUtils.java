package com.amazon.avod.util;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class JSONUtils {
    public static String getMapAsJsonString(Map<String, ?> map) {
        if (map != null) {
            return new JSONObject((Map<?, ?>) map).toString();
        }
        return null;
    }

    @Nonnull
    public static ImmutableMultimap<String, String> truncateMultimap(@Nullable Multimap<String, String> multimap, int i, int i2) {
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        if (multimap != null) {
            for (Map.Entry<String, String> entry : multimap.entries()) {
                builder.put(truncateString(entry.getKey(), i), truncateString(entry.getValue(), i2));
            }
        }
        return builder.build();
    }

    @Nullable
    public static String truncateString(@Nullable String str, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("maxLength");
        }
        if (str == null) {
            return null;
        }
        return str.substring(0, Math.min(i, str.length()));
    }

    @Nonnull
    public static <K, V> ImmutableMultimap<K, V> mapListToMultimap(@Nullable Map<K, List<V>> map) {
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        if (map != null) {
            for (Map.Entry<K, List<V>> entry : map.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    builder.putAll((Object) entry.getKey(), (Iterable) entry.getValue());
                }
            }
        }
        return builder.build();
    }
}
