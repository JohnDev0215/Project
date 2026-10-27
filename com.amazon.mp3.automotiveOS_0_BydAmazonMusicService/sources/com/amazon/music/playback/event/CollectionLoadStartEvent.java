package com.amazon.music.playback.event;

import android.net.Uri;

/* JADX INFO: loaded from: classes4.dex */
public final class CollectionLoadStartEvent {
    public final Uri collectionUri;
    public final long startTimeMS;

    public CollectionLoadStartEvent(long j, Uri uri) {
        this.startTimeMS = j;
        this.collectionUri = uri;
    }
}
