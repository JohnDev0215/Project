package com.amazon.avod.playback;

import com.amazon.avod.media.VideoResolution;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackContentEventListener {
    void onContentDownloaded();

    void onContentMetadataAvailable(VideoResolution[] videoResolutionArr, double d);
}
