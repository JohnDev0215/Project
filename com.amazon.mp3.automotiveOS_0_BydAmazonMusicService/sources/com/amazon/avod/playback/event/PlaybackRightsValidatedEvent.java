package com.amazon.avod.playback.event;

import com.amazon.avod.media.TimeSpan;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackRightsValidatedEvent extends PlaybackEvent {
    private final String mPlaybackAuthorityId;
    private final String mSessionToken;

    public PlaybackRightsValidatedEvent(@Nonnull String str, @Nullable String str2) {
        super(TimeSpan.ZERO);
        this.mPlaybackAuthorityId = str;
        this.mSessionToken = str2;
    }

    @Nonnull
    public String getPlaybackAuthorityId() {
        return this.mPlaybackAuthorityId;
    }

    @Nullable
    public String getSessionToken() {
        return this.mSessionToken;
    }
}
