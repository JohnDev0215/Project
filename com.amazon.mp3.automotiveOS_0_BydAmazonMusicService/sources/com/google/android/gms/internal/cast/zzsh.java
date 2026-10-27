package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzsh implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzsi zzb;

    zzsh(zzsi zzsiVar) {
        this.zzb = zzsiVar;
        this.zza = zzsiVar.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
