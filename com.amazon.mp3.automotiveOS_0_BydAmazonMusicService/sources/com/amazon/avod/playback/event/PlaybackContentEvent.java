package com.amazon.avod.playback.event;

import com.amazon.avod.media.playback.VideoPlayer;
import com.google.common.base.MoreObjects;
import dagger.internal.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackContentEvent {

    @Nonnull
    private final String mContentId;

    @Nullable
    private final String mContentType;

    @Nonnegative
    private final long mPlayhead;

    public PlaybackContentEvent(@Nullable String str, @Nonnull VideoPlayer videoPlayer, @Nullable String str2) {
        this.mContentId = str == null ? "Content ID not found" : str;
        this.mPlayhead = ((VideoPlayer) Preconditions.checkNotNull(videoPlayer, "Videoplayer")).getCurrentPosition();
        this.mContentType = str2;
    }

    @Nonnull
    public String getContentId() {
        return this.mContentId;
    }

    @Nonnegative
    public long getPlayhead() {
        return this.mPlayhead;
    }

    @Nullable
    public String getContentType() {
        return this.mContentType;
    }

    @Nonnull
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("contentId", this.mContentId).add("playhead", this.mPlayhead).add("contentType", this.mContentType).toString();
    }
}
