package com.amazon.avod.content.urlvending;

import com.google.common.collect.ImmutableMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum CuepointManifestFormat {
    VMAP_VAST,
    SMARTXML;

    private static final ImmutableMap<String, CuepointManifestFormat> MANIFEST_FORMAT;

    static {
        CuepointManifestFormat cuepointManifestFormat = VMAP_VAST;
        CuepointManifestFormat cuepointManifestFormat2 = SMARTXML;
        MANIFEST_FORMAT = ImmutableMap.builder().put(cuepointManifestFormat.name(), cuepointManifestFormat).put(cuepointManifestFormat2.name(), cuepointManifestFormat2).build();
    }

    @Nullable
    public static CuepointManifestFormat lookup(String str) {
        if (str != null) {
            ImmutableMap<String, CuepointManifestFormat> immutableMap = MANIFEST_FORMAT;
            if (immutableMap.containsKey(str)) {
                return immutableMap.get(str);
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name();
    }
}
