package com.vivid.spotify.queue;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import i3.v;

/* JADX INFO: compiled from: SpotifyQueueItemWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends v {
    public static double h(MediaSessionCompat.QueueItem queueItem) {
        Bundle bundleC = queueItem.h().c();
        if (bundleC == null) {
            return -1.0d;
        }
        return bundleC.getDouble("androidx.media.MediaItem.Extras.COMPLETION_PERCENTAGE", -1.0d);
    }

    public static boolean i(MediaSessionCompat.QueueItem queueItem) {
        Bundle bundleC = queueItem.h().c();
        return bundleC != null && bundleC.getLong("com.spotify.music.extra.IS_19_PLUS", 0L) == 1;
    }
}
