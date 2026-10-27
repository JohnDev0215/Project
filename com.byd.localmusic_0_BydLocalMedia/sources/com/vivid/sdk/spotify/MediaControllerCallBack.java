package com.vivid.sdk.spotify;

import android.os.Bundle;

/* JADX INFO: loaded from: classes5.dex */
public interface MediaControllerCallBack {
    public static final String KEY_IS_EPISODE_COLLECTED = "KEY_IS_EPISODE_COLLECTED";
    public static final String KEY_IS_TRACK_COLLECTED = "KEY_IS_TRACK_COLLECTED";
    public static final String KEY_PLAY_STATE = "KEY_PLAY_STATE";

    void onMetadataChanged(Metadata metadata);

    void onPlaybackStateChanged(Bundle bundle);

    void onPositionChanged(long j);
}
