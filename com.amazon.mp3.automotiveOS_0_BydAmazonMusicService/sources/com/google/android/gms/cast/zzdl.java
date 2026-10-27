package com.google.android.gms.cast;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdl implements com.google.android.gms.cast.internal.zzar {
    final /* synthetic */ zzdn zza;

    zzdl(zzdn zzdnVar) {
        this.zza = zzdnVar;
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zza(long j, int i, @Nullable Object obj) {
        if (true != (obj instanceof com.google.android.gms.cast.internal.zzao)) {
            obj = null;
        }
        try {
            this.zza.setResult(new zzdo(new Status(i), obj != null ? ((com.google.android.gms.cast.internal.zzao) obj).zza : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zzb(long j) {
        try {
            zzdn zzdnVar = this.zza;
            zzdnVar.setResult(new zzdm(zzdnVar, new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }
}
