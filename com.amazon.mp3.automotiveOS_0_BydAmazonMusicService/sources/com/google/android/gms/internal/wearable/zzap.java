package com.google.android.gms.internal.wearable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzap extends zzas {
    private final int zzc;

    zzap(byte[] bArr, int i, int i2) {
        super(bArr);
        zzau.zzq(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.wearable.zzas, com.google.android.gms.internal.wearable.zzau
    final byte zzb(int i) {
        return ((zzas) this).zza[i];
    }

    @Override // com.google.android.gms.internal.wearable.zzas, com.google.android.gms.internal.wearable.zzau
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.wearable.zzas
    protected final int zzd() {
        return 0;
    }

    @Override // com.google.android.gms.internal.wearable.zzas, com.google.android.gms.internal.wearable.zzau
    protected final void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(((zzas) this).zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.wearable.zzas, com.google.android.gms.internal.wearable.zzau
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return ((zzas) this).zza[i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(i2);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }
}
