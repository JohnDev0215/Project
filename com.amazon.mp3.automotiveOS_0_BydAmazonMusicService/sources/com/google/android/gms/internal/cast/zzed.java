package com.google.android.gms.internal.cast;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzed extends zzee {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzee zzc;

    zzed(zzee zzeeVar, int i, int i2) {
        this.zzc = zzeeVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzdr.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzee, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    @CheckForNull
    final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.cast.zzee
    /* JADX INFO: renamed from: zzh */
    public final zzee subList(int i, int i2) {
        zzdr.zzd(i, i2, this.zzb);
        zzee zzeeVar = this.zzc;
        int i3 = this.zza;
        return zzeeVar.subList(i + i3, i2 + i3);
    }
}
