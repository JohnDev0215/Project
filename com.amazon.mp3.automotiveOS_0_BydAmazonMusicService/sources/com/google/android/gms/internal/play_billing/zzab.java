package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzab extends zzu {
    final /* synthetic */ zzac zza;

    zzab(zzac zzacVar) {
        this.zza = zzacVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzm.zza(i, this.zza.zzc, "index");
        zzac zzacVar = this.zza;
        int i2 = i + i;
        Object obj = zzacVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzacVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final boolean zzf() {
        return true;
    }
}
