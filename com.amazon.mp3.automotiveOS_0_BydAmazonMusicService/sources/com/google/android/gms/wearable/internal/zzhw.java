package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.CapabilityApi;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzhw implements ListenerHolder.Notifier<CapabilityApi.CapabilityListener> {
    final /* synthetic */ zzag zza;

    zzhw(zzag zzagVar) {
        this.zza = zzagVar;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(CapabilityApi.CapabilityListener capabilityListener) {
        capabilityListener.onCapabilityChanged(this.zza);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
