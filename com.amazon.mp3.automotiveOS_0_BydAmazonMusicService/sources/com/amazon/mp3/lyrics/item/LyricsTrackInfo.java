package com.amazon.mp3.lyrics.item;

import com.amazon.mp3.AmazonApplication;

/* JADX INFO: loaded from: classes3.dex */
public abstract class LyricsTrackInfo {
    public abstract String getAsin();

    public abstract String getMarketplaceId();

    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LyricsTrackInfo)) {
            return false;
        }
        LyricsTrackInfo lyricsTrackInfo = (LyricsTrackInfo) obj;
        if (getAsin().equals(lyricsTrackInfo.getAsin())) {
            return !AmazonApplication.getCapabilities().isMarketIdCheckedForLyricsSupport() || getMarketplaceId().equals(lyricsTrackInfo.getMarketplaceId());
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = getAsin().hashCode();
        return AmazonApplication.getCapabilities().isMarketIdCheckedForLyricsSupport() ? (iHashCode * 31) + getMarketplaceId().hashCode() : iHashCode;
    }
}
