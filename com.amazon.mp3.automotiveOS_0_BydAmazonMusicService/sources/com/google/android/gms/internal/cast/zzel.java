package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzel extends zzee {
    static final zzee zza = new zzel(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzel(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzdr.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzee, com.google.android.gms.internal.cast.zzea
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final Object[] zzg() {
        return this.zzb;
    }
}
