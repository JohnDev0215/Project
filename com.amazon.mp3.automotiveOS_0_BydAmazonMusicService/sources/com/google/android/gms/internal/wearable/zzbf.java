package com.google.android.gms.internal.wearable;

import androidx.core.internal.view.SupportMenu;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbf {
    private final Object zza;
    private final int zzb;

    zzbf(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbf)) {
            return false;
        }
        zzbf zzbfVar = (zzbf) obj;
        return this.zza == zzbfVar.zza && this.zzb == zzbfVar.zzb;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * SupportMenu.USER_MASK) + this.zzb;
    }
}
