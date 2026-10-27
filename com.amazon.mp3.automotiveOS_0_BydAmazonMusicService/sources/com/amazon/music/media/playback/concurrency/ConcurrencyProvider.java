package com.amazon.music.media.playback.concurrency;

import com.amazon.music.media.playback.MediaItem;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface ConcurrencyProvider {
    boolean shouldUpdateInBackground();

    List<ConcurrentDeviceInfo> updatePlayStatus(int i, MediaItem mediaItem, long j, String str) throws IOException;
}
