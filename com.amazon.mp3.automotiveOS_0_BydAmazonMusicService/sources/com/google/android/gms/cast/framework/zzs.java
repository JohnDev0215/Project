package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzs extends zzae {
    private final CastStateListener zza;

    public zzs(CastStateListener castStateListener) {
        this.zza = castStateListener;
    }

    @Override // com.google.android.gms.cast.framework.zzaf
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzaf
    public final void zzc(int i) {
        this.zza.onCastStateChanged(i);
    }
}
