package com.amazon.mp3.library.provider.source.nowplaying;

import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public interface PlayQueueHelper {
    String getCatalogPlaylistId(Uri uri);

    boolean isUnlimitedCollection(Uri uri);

    Cursor queryNowPlayingTracks(String[] strArr, String str);
}
