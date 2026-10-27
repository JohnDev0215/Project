package com.amazon.avod.content;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadStoreType {
    FRAGMENTED("Fragment"),
    SINGLE_FILE("Single");

    private final String mShortName;

    DownloadStoreType(@Nonnull String str) {
        this.mShortName = (String) Preconditions.checkNotNull(str, "shortName");
    }

    public String getShortName() {
        return this.mShortName;
    }

    @Nonnull
    public static DownloadStoreType fromString(@Nullable String str) {
        if (str == null) {
            return FRAGMENTED;
        }
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return FRAGMENTED;
        }
    }
}
