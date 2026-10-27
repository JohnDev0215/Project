package com.amazon.avod.settings;

import com.amazon.avod.media.MediaQuality;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadQuality {
    BEST(0, Optional.of(MediaQuality.HIGH)),
    BETTER(1, Optional.of(MediaQuality.MEDIUM)),
    GOOD(2, Optional.of(MediaQuality.LOW)),
    DATA_SAVER(4, Optional.of(MediaQuality.LOWEST)),
    ALWAYS_ASK(3, Optional.absent());

    private final Optional<MediaQuality> mMediaQualityEquivalent;
    private final int mValue;

    DownloadQuality(int i, Optional optional) {
        this.mValue = i;
        this.mMediaQualityEquivalent = optional;
    }

    public int getValue() {
        return this.mValue;
    }

    public Optional<MediaQuality> getMediaQualityEquivalent() {
        return this.mMediaQualityEquivalent;
    }

    @Nullable
    public static DownloadQuality fromMediaQuality(@Nonnull MediaQuality mediaQuality) {
        Preconditions.checkNotNull(mediaQuality, "mediaQuality");
        for (DownloadQuality downloadQuality : values()) {
            if (mediaQuality.equals(downloadQuality.getMediaQualityEquivalent().orNull())) {
                return downloadQuality;
            }
        }
        return null;
    }

    @Nonnull
    public static DownloadQuality fromInt(int i) {
        for (DownloadQuality downloadQuality : values()) {
            if (downloadQuality.getValue() == i) {
                return downloadQuality;
            }
        }
        return ALWAYS_ASK;
    }
}
