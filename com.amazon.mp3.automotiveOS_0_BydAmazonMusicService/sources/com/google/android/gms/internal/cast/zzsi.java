package com.google.android.gms.internal.cast;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzsi extends AbstractList implements RandomAccess, zzqj {
    private final zzqj zza;

    public zzsi(zzqj zzqjVar) {
        this.zza = zzqjVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzqi) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzsh(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzsg(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.cast.zzqj
    public final zzqj zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.cast.zzqj
    public final Object zze(int i) {
        return this.zza.zze(i);
    }

    @Override // com.google.android.gms.internal.cast.zzqj
    public final List zzh() {
        return this.zza.zzh();
    }
}
