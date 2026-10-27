package com.google.android.gms.cast.framework.media;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbi implements com.google.android.gms.cast.internal.zzar {
    final /* synthetic */ zzbk zza;

    zzbi(zzbk zzbkVar) {
        this.zza = zzbkVar;
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zza(long j, int i, @Nullable Object obj) {
        if (true != (obj instanceof com.google.android.gms.cast.internal.zzao)) {
            obj = null;
        }
        try {
            this.zza.setResult(new zzbl(new Status(i), obj != null ? ((com.google.android.gms.cast.internal.zzao) obj).zza : null, obj != null ? ((com.google.android.gms.cast.internal.zzao) obj).zzb : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestCompleted", e);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zzb(long j) {
        try {
            zzbk zzbkVar = this.zza;
            zzbkVar.setResult(new zzbj(zzbkVar, new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestReplaced", e);
        }
    }
}
