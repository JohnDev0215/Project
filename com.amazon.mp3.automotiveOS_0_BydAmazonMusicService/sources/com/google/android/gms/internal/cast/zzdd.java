package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdd extends zzdf {
    private final Handler zza;

    public zzdd(Looper looper) {
        this.zza = new Handler(looper);
    }

    @Override // com.google.android.gms.internal.cast.zzdf
    public final void zza(zzdc zzdcVar) {
        this.zza.postDelayed(zzdcVar.zzc(), 0L);
    }
}
