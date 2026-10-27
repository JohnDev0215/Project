package com.android.car.audio.hal;

import android.os.IHwBinder;

/* JADX INFO: renamed from: com.android.car.audio.hal.-$$Lambda$AudioControlWrapperV1$GmHsHZmywBeSZ4Vpqn_BffHdl2k, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$AudioControlWrapperV1$GmHsHZmywBeSZ4Vpqn_BffHdl2k implements IHwBinder.DeathRecipient {
    public final /* synthetic */ AudioControlWrapperV1 f$0;

    public /* synthetic */ $$Lambda$AudioControlWrapperV1$GmHsHZmywBeSZ4Vpqn_BffHdl2k(AudioControlWrapperV1 audioControlWrapperV1) {
        this.f$0 = audioControlWrapperV1;
    }

    public final void serviceDied(long j) {
        this.f$0.serviceDied(j);
    }
}
