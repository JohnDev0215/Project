package com.google.android.gms.internal.cast;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzai extends com.google.android.gms.cast.framework.zzu {
    final /* synthetic */ zzak zza;

    zzai(zzak zzakVar) {
        this.zza = zzakVar;
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this);
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zzc() {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zzd() {
        this.zza.zzb();
    }
}
