package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class FatalPlaybackErrorEvent extends PlaybackErrorEvent {
    private final boolean mIsPreCache;

    public FatalPlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException, @Nullable String str) {
        this(timeSpan, mediaException, null, str);
    }

    public FatalPlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException, String str, @Nullable String str2) {
        this(timeSpan, mediaException, str, 0, str2);
    }

    public FatalPlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException, String str, int i, @Nullable String str2, boolean z) {
        super(timeSpan, mediaException, str, i, str2);
        this.mIsPreCache = z;
    }

    public FatalPlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException, String str, int i, @Nullable String str2) {
        super(timeSpan, mediaException, str, i, str2);
        this.mIsPreCache = false;
    }

    @Override // com.amazon.avod.playback.event.playback.PlaybackErrorEvent
    public boolean isRetriable() {
        return this.mIsPreCache;
    }
}
