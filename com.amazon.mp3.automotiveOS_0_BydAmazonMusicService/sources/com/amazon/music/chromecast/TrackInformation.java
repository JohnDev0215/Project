package com.amazon.music.chromecast;

/* JADX INFO: loaded from: classes4.dex */
public final class TrackInformation {
    private final String mAlbumArtUrl;
    private final String mAlbumName;
    private final String mArtistName;
    private final long mDuration;
    private final StreamType mStreamType;
    private final String mStreamUrl;
    private final String mTitle;

    public TrackInformation(String str, String str2, String str3, long j, String str4, String str5, StreamType streamType) {
        this.mTitle = str;
        this.mArtistName = str2;
        this.mAlbumName = str3;
        this.mDuration = j;
        this.mStreamUrl = str4;
        this.mAlbumArtUrl = str5;
        this.mStreamType = streamType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getArtistName() {
        return this.mArtistName;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public String getStreamUrl() {
        return this.mStreamUrl;
    }

    public String getAlbumArtUrl() {
        return this.mAlbumArtUrl;
    }

    public StreamType getStreamType() {
        return this.mStreamType;
    }
}
