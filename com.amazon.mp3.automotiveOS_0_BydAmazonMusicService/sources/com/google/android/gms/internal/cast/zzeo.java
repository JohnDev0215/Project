package com.google.android.gms.internal.cast;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzeo extends zzei {
    private final transient zzeh zza;
    private final transient zzee zzb;

    zzeo(zzeh zzehVar, zzee zzeeVar) {
        this.zza = zzehVar;
        this.zzb = zzeeVar;
    }

    @Override // com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea
    public final zzee zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea
    /* JADX INFO: renamed from: zze */
    public final zzeu iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final boolean zzf() {
        throw null;
    }
}
