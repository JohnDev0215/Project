package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
class zzov extends zzou {
    protected final byte[] zza;

    zzov(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzoy) || zzd() != ((zzoy) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzov)) {
            return obj.equals(this);
        }
        zzov zzovVar = (zzov) obj;
        int iZzk = zzk();
        int iZzk2 = zzovVar.zzk();
        if (iZzk != 0 && iZzk2 != 0 && iZzk != iZzk2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzovVar.zzd()) {
            int iZzd2 = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(iZzd);
            sb.append(iZzd2);
            throw new IllegalArgumentException(sb.toString());
        }
        if (iZzd > zzovVar.zzd()) {
            int iZzd3 = zzovVar.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(iZzd);
            sb2.append(", ");
            sb2.append(iZzd3);
            throw new IllegalArgumentException(sb2.toString());
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzovVar.zza;
        zzovVar.zzc();
        int i = 0;
        int i2 = 0;
        while (i < iZzd) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    protected final int zze(int i, int i2, int i3) {
        return zzqc.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    public final zzoy zzf(int i, int i2) {
        zzoy.zzj(0, i2, zzd());
        return i2 == 0 ? zzoy.zzb : new zzos(this.zza, 0, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    final void zzh(zzoo zzooVar) throws IOException {
        ((zzpd) zzooVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.cast.zzoy
    public final boolean zzi() {
        return zzsr.zze(this.zza, 0, zzd());
    }
}
