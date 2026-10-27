package com.google.android.gms.internal.cast;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzer extends zzei {
    static final zzer zza;
    private static final Object[] zzd;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] objArr = new Object[0];
        zzd = objArr;
        zza = new zzer(objArr, 0, objArr, 0, 0);
    }

    zzer(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zzb = objArr;
        this.zze = i;
        this.zzc = objArr2;
        this.zzf = i2;
        this.zzg = i3;
    }

    @Override // com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        Object[] objArr = this.zzc;
        if (obj == null || objArr.length == 0) {
            return false;
        }
        int iZza = zzdx.zza(obj.hashCode());
        while (true) {
            int i = iZza & this.zzf;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iZza = i + 1;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzei, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzg);
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zzb() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzei, com.google.android.gms.internal.cast.zzea
    /* JADX INFO: renamed from: zze */
    public final zzeu iterator() {
        return zzd().listIterator(0);
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final Object[] zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzei
    final zzee zzi() {
        return zzee.zzk(this.zzb, this.zzg);
    }

    @Override // com.google.android.gms.internal.cast.zzei
    final boolean zzl() {
        return true;
    }
}
