package com.amazon.music.media.playback.config;

import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public interface OnMediaBrowseHandler {
    void onMediaBrowse(Uri uri, Bundle bundle, boolean z);

    void onMediaBrowse(String str, Bundle bundle, boolean z);
}
