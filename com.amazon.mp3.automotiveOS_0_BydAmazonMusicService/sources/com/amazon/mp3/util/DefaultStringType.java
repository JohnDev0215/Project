package com.amazon.mp3.util;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public enum DefaultStringType {
    ALBUM(R.string.dmusic_cirrus_unknown_album, R.array.dmusic_cirrus_unknown_album_list, "Unknown_Album"),
    ARTIST(R.string.dmusic_cirrus_unknown_artist, R.array.dmusic_cirrus_unknown_artist_list, "<unknown>"),
    GENRE(R.string.dmusic_cirrus_unknown_genre, R.array.dmusic_cirrus_unknown_genre_list, "");

    private final int mCirrusStringArrayId;
    private final int mCirrusStringId;
    private HashSet<String> mPlatformDefaults;

    DefaultStringType(int cirrusStringId, int cirrusStringArrayId, String... platformStrings) {
        HashSet<String> hashSet = new HashSet<>();
        this.mPlatformDefaults = hashSet;
        this.mCirrusStringId = cirrusStringId;
        this.mCirrusStringArrayId = cirrusStringArrayId;
        Collections.addAll(hashSet, platformStrings);
        Collections.addAll(this.mPlatformDefaults, getCirrusDefaults());
    }

    public String getDefault() {
        return AmazonApplication.getContext().getString(this.mCirrusStringId);
    }

    private String[] getCirrusDefaults() {
        return AmazonApplication.getContext().getResources().getStringArray(this.mCirrusStringArrayId);
    }

    public boolean isPlatformOrCirrusDefault(String string) {
        if (string == null) {
            return true;
        }
        return this.mPlatformDefaults.contains(string) || this.mPlatformDefaults.contains(string.replace('_', ' '));
    }
}
