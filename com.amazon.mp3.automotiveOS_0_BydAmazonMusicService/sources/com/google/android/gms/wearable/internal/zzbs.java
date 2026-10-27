package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbs extends zzem {
    private final Object zza = new Object();

    @GuardedBy("lock")
    @Nullable
    private zzbt zzb;

    @GuardedBy("lock")
    @Nullable
    private zzaw zzc;

    public final void zzb(zzbt zzbtVar) {
        zzaw zzawVar;
        synchronized (this.zza) {
            this.zzb = (zzbt) Preconditions.checkNotNull(zzbtVar);
            zzawVar = this.zzc;
        }
        if (zzawVar != null) {
            zzbtVar.zza(zzawVar);
        }
    }

    @Override // com.google.android.gms.wearable.internal.zzen
    public final void zzc(int i, int i2) {
        zzbt zzbtVar;
        zzaw zzawVar;
        synchronized (this.zza) {
            zzbtVar = this.zzb;
            zzawVar = new zzaw(i, i2);
            this.zzc = zzawVar;
        }
        if (zzbtVar != null) {
            zzbtVar.zza(zzawVar);
        }
    }
}
