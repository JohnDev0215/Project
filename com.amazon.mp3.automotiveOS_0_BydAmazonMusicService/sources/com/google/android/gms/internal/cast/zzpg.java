package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzpg extends zzoo {
    private static final Logger zzb = Logger.getLogger(zzpg.class.getName());
    private static final boolean zzc = zzsn.zzx();
    zzph zza;

    private zzpg() {
    }

    /* synthetic */ zzpg(zzpf zzpfVar) {
    }

    public static int zzA(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzB(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzpg zzC(byte[] bArr) {
        return new zzpd(bArr, 0, bArr.length);
    }

    public static int zzt(zzoy zzoyVar) {
        int iZzd = zzoyVar.zzd();
        return zzA(iZzd) + iZzd;
    }

    @Deprecated
    static int zzu(int i, zzrb zzrbVar, zzrm zzrmVar) {
        int iZzA = zzA(i << 3);
        int i2 = iZzA + iZzA;
        zzol zzolVar = (zzol) zzrbVar;
        int iZzn = zzolVar.zzn();
        if (iZzn == -1) {
            iZzn = zzrmVar.zza(zzolVar);
            zzolVar.zzp(iZzn);
        }
        return i2 + iZzn;
    }

    public static int zzv(int i) {
        if (i >= 0) {
            return zzA(i);
        }
        return 10;
    }

    public static int zzw(zzqh zzqhVar) {
        int iZza = zzqhVar.zza();
        return zzA(iZza) + iZza;
    }

    static int zzx(zzrb zzrbVar, zzrm zzrmVar) {
        zzol zzolVar = (zzol) zzrbVar;
        int iZzn = zzolVar.zzn();
        if (iZzn == -1) {
            iZzn = zzrmVar.zza(zzolVar);
            zzolVar.zzp(iZzn);
        }
        return zzA(iZzn) + iZzn;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzsr.zzc(str);
        } catch (zzsq unused) {
            length = str.getBytes(zzqc.zzb).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int i) {
        return zzA(i << 3);
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzE(String str, zzsq zzsqVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzsqVar);
        byte[] bytes = str.getBytes(zzqc.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzpe(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzoy zzoyVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
