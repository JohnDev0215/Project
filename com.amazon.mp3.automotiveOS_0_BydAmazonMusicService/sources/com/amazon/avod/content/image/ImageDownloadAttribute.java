package com.amazon.avod.content.image;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ImageDownloadAttribute {
    private final DownloadDirection mDirection;
    private final int mDownloadGranularitySeconds;
    private volatile int mGranularityFactor = 1;

    public ImageDownloadAttribute(@Nonnull DownloadDirection downloadDirection, int i) {
        this.mDirection = (DownloadDirection) Preconditions.checkNotNull(downloadDirection, "downloadDirection");
        this.mDownloadGranularitySeconds = i;
    }

    public void doubleGranularityFactor() {
        this.mGranularityFactor *= 2;
    }

    @Nonnull
    public DownloadDirection getDirection() {
        return this.mDirection;
    }

    public int getDownloadGranularitySeconds() {
        return this.mDownloadGranularitySeconds;
    }

    public int getDownloadGranularityInThumbnails(int i) {
        return (this.mDownloadGranularitySeconds / i) * this.mGranularityFactor;
    }

    @Nonnull
    public String toString() {
        return String.format(Locale.ENGLISH, "%s:%s*%s", this.mDirection, Integer.valueOf(this.mDownloadGranularitySeconds), Integer.valueOf(this.mGranularityFactor));
    }
}
