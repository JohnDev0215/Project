package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackStopEvent extends PlaybackEvent {
    private final String mDrmAssetId;
    private final String mDrmScheme;
    private final String mRendererScheme;
    private final String mStreamMetaData;
    private final String mTitleId;

    public PlaybackStopEvent(@Nonnull TimeSpan timeSpan, @Nonnull String str, @Nonnull String str2) {
        this(timeSpan, str, str2, null, null, null);
    }

    public PlaybackStopEvent(TimeSpan timeSpan, @Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        super(timeSpan);
        this.mRendererScheme = (String) Preconditions.checkNotNull(str, "rendererScheme");
        this.mDrmScheme = (String) Preconditions.checkNotNull(str2, "drmScheme");
        this.mDrmAssetId = str3;
        this.mTitleId = str4;
        this.mStreamMetaData = str5;
    }

    @Nonnull
    public String getRendererScheme() {
        return this.mRendererScheme;
    }

    @Nonnull
    public String getDrmScheme() {
        return this.mDrmScheme;
    }

    @Nullable
    public String getStreamMetaData() {
        return this.mStreamMetaData;
    }
}
