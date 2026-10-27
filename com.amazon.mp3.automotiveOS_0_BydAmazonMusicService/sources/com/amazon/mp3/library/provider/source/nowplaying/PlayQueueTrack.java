package com.amazon.mp3.library.provider.source.nowplaying;

/* JADX INFO: loaded from: classes3.dex */
public class PlayQueueTrack {
    private final String mAsin;
    private final String mCatalogPlaylistId;
    private final String mLuid;
    private final String mShuffle;
    private final String mUdo;
    private final String mUri;

    public PlayQueueTrack(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mUri = str;
        this.mUdo = str2;
        this.mShuffle = str3;
        this.mLuid = str4;
        this.mAsin = str5;
        this.mCatalogPlaylistId = str6;
    }

    public String getUdo() {
        return this.mUdo;
    }

    public String getAsin() {
        return this.mAsin;
    }
}
