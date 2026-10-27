package com.ts.androidauto.fsdk.interfaces.audio;

import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes2.dex */
public interface IAudioControllerListener {
    void audioFocusChangedTo(ResourceConst.AudioId audioId, ResourceConst.AudioId audioId2, ResourceConst.AudioFocusNotify audioFocusNotify, boolean z);

    void audioSourceStateChanged(ResourceConst.AudioId audioId, int i);

    void entertainmentMuteStateChangedTo(int i);

    void micFocusChangedTo(ResourceConst.MicChangeEvent micChangeEvent);

    default void setNativeTtsState(boolean state) {
    }
}
