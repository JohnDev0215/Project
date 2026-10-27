package com.amazon.avod.media.contentcache;

import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.TimeSpan;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface CacheRecord extends Downloadable {

    public enum RecordState {
        NOT_STARTED,
        IN_PROGRESS,
        FULLY_CACHED
    }

    boolean beginsAtTimestamp(TimeSpan timeSpan);

    RecordState getCacheState();

    @Nullable
    PlaybackEnvelope getPlaybackEnvelope();

    int getPriority();
}
