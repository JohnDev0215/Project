package com.amazon.music.media.playback;

import androidx.annotation.WorkerThread;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.config.BitRateSelection;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface MediaLinkProvider {
    @WorkerThread
    MediaLinks getMediaLinks(MediaItem mediaItem, BitRateSelection bitRateSelection) throws PlaybackException;

    @WorkerThread
    MediaLinks getMediaLinks(MediaItem mediaItem, BitRateSelection bitRateSelection, boolean z, List<MediaLink.Type> list) throws PlaybackException;
}
