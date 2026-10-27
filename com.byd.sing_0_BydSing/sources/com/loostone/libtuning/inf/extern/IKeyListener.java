package com.loostone.libtuning.inf.extern;

import kotlin.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public interface IKeyListener {

    @f
    public static final class DefaultImpls {
        public static void onPairStateChanged(IKeyListener iKeyListener, int i, int i2) {
            h.d(iKeyListener, "this");
        }
    }

    void onPairStateChanged(int i, int i2);

    void onPower(int i, boolean z);

    void onTVPhoneSwitch(int i, boolean z);

    void onVoice(int i, boolean z);

    void onVolumeDown(int i, boolean z);

    void onVolumeUp(int i, boolean z);
}
