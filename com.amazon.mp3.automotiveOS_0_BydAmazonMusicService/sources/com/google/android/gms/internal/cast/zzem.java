package com.google.android.gms.internal.cast;

import java.util.AbstractMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzem extends zzee {
    final /* synthetic */ zzen zza;

    zzem(zzen zzenVar) {
        this.zza = zzenVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzdr.zza(i, this.zza.zzc, "index");
        zzen zzenVar = this.zza;
        int i2 = i + i;
        Object obj = zzenVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzenVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    public final boolean zzf() {
        return true;
    }
}
