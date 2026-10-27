package com.google.android.gms.internal.cast;

import android.view.ViewGroup;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzw implements Runnable {
    final /* synthetic */ zzx zza;

    zzw(zzx zzxVar) {
        this.zza = zzxVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzy zzyVar = this.zza.zza;
        if (zzyVar.zzg) {
            ((ViewGroup) zzyVar.zzb.getWindow().getDecorView()).removeView(this.zza.zza);
            zzy zzyVar2 = this.zza.zza;
            if (zzyVar2.zzc != null) {
                zzyVar2.zzc.onOverlayDismissed();
            }
            this.zza.zza.zzg();
        }
    }
}
