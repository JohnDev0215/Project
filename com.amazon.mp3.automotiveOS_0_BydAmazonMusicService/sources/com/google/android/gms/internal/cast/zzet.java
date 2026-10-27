package com.google.android.gms.internal.cast;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzet extends zzei {
    final transient Object zza;

    zzet(Object obj) {
        obj.getClass();
        this.zza = obj;
    }

    @Override // com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.equals(obj);
    }

    @Override // com.google.android.gms.internal.cast.zzei, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzej(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String string = this.zza.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 2);
        sb.append('[');
        sb.append(string);
        sb.append(']');
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zza(Object[] objArr, int i) {
        objArr[0] = this.zza;
        return 1;
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea
    public final zzee zzd() {
        return zzee.zzm(this.zza);
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea
    /* JADX INFO: renamed from: zze */
    public final zzeu iterator() {
        return new zzej(this.zza);
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final boolean zzf() {
        throw null;
    }
}
