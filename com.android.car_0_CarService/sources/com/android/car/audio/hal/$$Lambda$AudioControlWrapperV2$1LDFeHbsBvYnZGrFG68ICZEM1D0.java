package com.android.car.audio.hal;

import android.os.IHwBinder;

/* JADX INFO: renamed from: com.android.car.audio.hal.-$$Lambda$AudioControlWrapperV2$1LDFeHbsBvYnZGrFG68ICZEM1D0, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$AudioControlWrapperV2$1LDFeHbsBvYnZGrFG68ICZEM1D0 implements IHwBinder.DeathRecipient {
    public final /* synthetic */ AudioControlWrapperV2 f$0;

    public /* synthetic */ $$Lambda$AudioControlWrapperV2$1LDFeHbsBvYnZGrFG68ICZEM1D0(AudioControlWrapperV2 audioControlWrapperV2) {
        this.f$0 = audioControlWrapperV2;
    }

    public final void serviceDied(long j) {
        this.f$0.serviceDied(j);
    }
}
