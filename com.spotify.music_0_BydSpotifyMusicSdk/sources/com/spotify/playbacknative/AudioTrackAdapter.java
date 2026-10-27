package com.spotify.playbacknative;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public interface AudioTrackAdapter {

    public interface OnRoutingChangedListener {
        void onRoutingChanged(AudioDeviceInfoFacade audioDeviceInfoFacade);
    }

    void addOnRoutingChangedListener(OnRoutingChangedListener onRoutingChangedListener, Handler handler);

    AudioDeviceInfoFacade getRoutedDevice();

    void removeOnRoutingChangedListener(OnRoutingChangedListener onRoutingChangedListener);
}
