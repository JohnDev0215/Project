package com.byd.music.fragment.bluetooth;

import android.media.MediaMetadata;
import android.media.session.PlaybackState;

/* JADX INFO: loaded from: classes5.dex */
public interface IBluetoothListener {
    void onConnected();

    void onDisConnected();

    void onMetadataChanged(MediaMetadata mediaMetadata);

    void onNotluetoothA2dp();

    void onPlaybackStateChanged(PlaybackState playbackState);
}
