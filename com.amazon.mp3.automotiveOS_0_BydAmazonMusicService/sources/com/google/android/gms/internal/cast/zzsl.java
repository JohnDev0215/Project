package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzsl extends zzsm {
    zzsl(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final void zzc(Object obj, long j, boolean z) {
        if (zzsn.zzb) {
            zzsn.zzD(obj, j, z ? (byte) 1 : (byte) 0);
        } else {
            zzsn.zzE(obj, j, z ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final void zzd(Object obj, long j, byte b) {
        if (zzsn.zzb) {
            zzsn.zzD(obj, j, b);
        } else {
            zzsn.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.cast.zzsm
    public final boolean zzg(Object obj, long j) {
        return zzsn.zzb ? zzsn.zzt(obj, j) : zzsn.zzu(obj, j);
    }
}
