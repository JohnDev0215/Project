package com.byd.systemsettings.screensaver.callback;

import android.media.MediaMetadata;
import android.media.session.PlaybackState;

/* JADX INFO: loaded from: classes2.dex */
public interface IMusicStateCallback {
    void onMusicDataChanged(MediaMetadata mediaMetadata);

    void onPlaybackStateChanged(PlaybackState playbackState);
}
