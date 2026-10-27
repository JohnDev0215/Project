package com.amazon.avod.content.urlvending;

import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadType {
    MANIFEST("manifest"),
    FRAGMENT("fragment"),
    ANY("any");

    private final String mDownload;

    DownloadType(@Nonnull String str) {
        this.mDownload = (String) Preconditions.checkNotNull(str, "download");
    }

    @JsonCreator
    @Nonnull
    public static DownloadType forValue(@Nullable String str) {
        if (str == null) {
            return ANY;
        }
        String strTrim = str.trim();
        DownloadType downloadType = MANIFEST;
        if (strTrim.equalsIgnoreCase(downloadType.toString())) {
            return downloadType;
        }
        DownloadType downloadType2 = FRAGMENT;
        if (strTrim.equalsIgnoreCase(downloadType2.toString())) {
            return downloadType2;
        }
        DownloadType downloadType3 = ANY;
        if (strTrim.equalsIgnoreCase(downloadType3.toString())) {
            return downloadType3;
        }
        DLog.warnf("No matching download type found for: %s, returning default!", str);
        return downloadType3;
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return this.mDownload;
    }
}
