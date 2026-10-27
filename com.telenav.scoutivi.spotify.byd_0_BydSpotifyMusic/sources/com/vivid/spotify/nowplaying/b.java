package com.vivid.spotify.nowplaying;

import android.support.v4.media.MediaMetadataCompat;
import i3.t;

/* JADX INFO: compiled from: MetadataWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends t {
    public static String l(MediaMetadataCompat mediaMetadataCompat) {
        return mediaMetadataCompat == null ? "" : mediaMetadataCompat.h().getString("com.spotify.music.extra.CONTEXT_URI", "");
    }
}
