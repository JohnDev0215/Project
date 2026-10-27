package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
abstract class zzso {
    zzso() {
    }

    abstract int zza(int i, byte[] bArr, int i2, int i3);

    final boolean zzb(byte[] bArr, int i, int i2) {
        return zza(0, bArr, 0, i2) == 0;
    }
}
