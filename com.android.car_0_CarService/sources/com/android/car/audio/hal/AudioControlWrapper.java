package com.android.car.audio.hal;

import android.hardware.automotive.audiocontrol.V2_0.IFocusListener;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioControlWrapper {

    public interface AudioControlDeathRecipient {
        void serviceDied();
    }

    void dump(String str, PrintWriter printWriter);

    void linkToDeath(AudioControlDeathRecipient audioControlDeathRecipient);

    void onAudioFocusChange(int i, int i2, int i3);

    void registerFocusListener(IFocusListener iFocusListener);

    void setBalanceTowardRight(float f);

    void setFadeTowardFront(float f);

    boolean supportsHalAudioFocus();

    void unlinkToDeath();

    void unregisterFocusListener();
}
