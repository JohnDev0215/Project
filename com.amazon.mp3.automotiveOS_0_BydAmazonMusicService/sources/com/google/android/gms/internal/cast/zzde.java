package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.view.Choreographer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@TargetApi(16)
final class zzde extends zzdf {
    private final Choreographer zza = Choreographer.getInstance();

    @Override // com.google.android.gms.internal.cast.zzdf
    public final void zza(zzdc zzdcVar) {
        this.zza.postFrameCallback(zzdcVar.zzb());
    }
}
