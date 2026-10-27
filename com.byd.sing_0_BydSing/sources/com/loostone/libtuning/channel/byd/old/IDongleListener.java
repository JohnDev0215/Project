package com.loostone.libtuning.channel.byd.old;

/* JADX INFO: loaded from: classes.dex */
public interface IDongleListener {
    void attach();

    void detach();

    default void onFactoryMicPowerOff() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("IDongleListener", "onFactoryMicPowerOff");
    }

    default void onInteractEffect(String str) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("IDongleListener", "onInteractEffect, effect = " + str);
    }

    default void onMicConnectionChanged(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("IDongleListener", "onMicConnectionChanged, connected = " + z);
    }

    void onMicPowerChanged(int i, boolean z);

    default void onMicPowerOnInit() {
    }

    void onOutsideDongleRejected();

    default void onPairResultChanged(int i, int i2, int i3) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("IDongleListener", "onPairResultChanged{micNum=" + i + ",pairMethod=" + i2 + ",pairResult=1}");
    }

    void onPairStateChanged(int i, int i2);

    default void onPremiumSpeakerState(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("IDongleListener", "onPremiumSpeakerState, isConnected = " + z);
    }
}
