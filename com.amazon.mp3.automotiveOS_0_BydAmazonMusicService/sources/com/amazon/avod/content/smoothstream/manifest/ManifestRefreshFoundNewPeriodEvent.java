package com.amazon.avod.content.smoothstream.manifest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ManifestRefreshFoundNewPeriodEvent {

    @Nonnull
    private final String mId;

    @Nonnull
    private final String mLanguage;

    @Nonnull
    private final StreamType mStreamType;

    public ManifestRefreshFoundNewPeriodEvent(@Nullable String str, @Nullable StreamType streamType, @Nullable String str2) {
        this.mId = str == null ? "" : str;
        this.mStreamType = streamType == null ? StreamType.UNSUPPORTED : streamType;
        this.mLanguage = str2 == null ? "" : str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ManifestRefreshFoundNewPeriodEvent.class != obj.getClass()) {
            return false;
        }
        ManifestRefreshFoundNewPeriodEvent manifestRefreshFoundNewPeriodEvent = (ManifestRefreshFoundNewPeriodEvent) obj;
        return this.mId.compareTo(manifestRefreshFoundNewPeriodEvent.mId) == 0 && this.mStreamType.compareTo(manifestRefreshFoundNewPeriodEvent.mStreamType) == 0 && this.mLanguage.compareTo(manifestRefreshFoundNewPeriodEvent.mLanguage) == 0;
    }

    public int hashCode() {
        return (this.mId.hashCode() ^ this.mStreamType.hashCode()) ^ this.mLanguage.hashCode();
    }
}
