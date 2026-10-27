package com.spotify.automotive.playback;

import com.spotify.music.R;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public enum ErrorState {
    PLAYBACK_PERMANENT_ERROR(1, 0, R.string.error_general),
    PLAYBACK_TEMPORARY_ERROR(2, 0, R.string.error_playback_temporary),
    NETWORK_ERROR(3, 0, R.string.error_network),
    VOICE_FAILURE(100, 0, R.string.error_voice),
    PREMIUM_IS_REQUIRED(101, 4, R.string.error_voice_premium);

    private final int mError;
    private final int mPlaybackStateError;
    private final int mPlaybackStateErrorMessageId;

    ErrorState(int i, int i2, int i3) {
        this.mError = i;
        this.mPlaybackStateError = i2;
        this.mPlaybackStateErrorMessageId = i3;
    }

    public final int a() {
        return this.mError;
    }

    public final int b() {
        return this.mPlaybackStateError;
    }

    public final int c() {
        return this.mPlaybackStateErrorMessageId;
    }

    @Override // java.lang.Enum
    public final String toString() {
        StringBuilder sb = new StringBuilder("ErrorState{mError=");
        sb.append(this.mError);
        sb.append(", mPlaybackStateError=");
        sb.append(this.mPlaybackStateError);
        sb.append(", isTemporary()=");
        return u4.n(sb, this == VOICE_FAILURE || this == PREMIUM_IS_REQUIRED || this == PLAYBACK_TEMPORARY_ERROR || this == NETWORK_ERROR, '}');
    }
}
