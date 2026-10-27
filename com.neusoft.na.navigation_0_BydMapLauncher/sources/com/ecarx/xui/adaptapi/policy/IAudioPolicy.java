package com.ecarx.xui.adaptapi.policy;

/* JADX INFO: loaded from: classes2.dex */
public interface IAudioPolicy {
    int getAudioStreamTypeForFunction(String str);

    int getMicState();

    void micFocusGot();

    boolean registerMicStateChange(IMicStateCallback iMicStateCallback);

    boolean releaseMic(IMicResourceCallback iMicResourceCallback);

    boolean requestMic(int i, IMicResourceCallback iMicResourceCallback);

    boolean unregisterMicStateChange(IMicStateCallback iMicStateCallback);
}
