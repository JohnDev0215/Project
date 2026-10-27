package com.amazon.music.playback.event;

import android.net.Uri;

/* JADX INFO: loaded from: classes4.dex */
public final class CollectionLoadEndEvent {
    public final Uri collectionUri;
    public final long endTimeMS;

    public CollectionLoadEndEvent(long j, Uri uri) {
        this.endTimeMS = j;
        this.collectionUri = uri;
    }
}
