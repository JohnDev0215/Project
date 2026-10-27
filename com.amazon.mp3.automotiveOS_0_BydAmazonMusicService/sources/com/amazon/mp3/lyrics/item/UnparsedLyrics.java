package com.amazon.mp3.lyrics.item;

/* JADX INFO: loaded from: classes3.dex */
public class UnparsedLyrics {
    private String mRawLyrics;
    private LyricsStatus mStatus;

    public UnparsedLyrics(LyricsStatus status, String rawLyrics) {
        this.mStatus = status;
        this.mRawLyrics = rawLyrics;
    }

    public LyricsStatus getStatus() {
        return this.mStatus;
    }

    public String getRawLyrics() {
        return this.mRawLyrics;
    }
}
