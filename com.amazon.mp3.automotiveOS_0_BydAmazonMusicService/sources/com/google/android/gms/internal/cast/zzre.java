package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzre<T> implements zzrm<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzsn.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzrb zze;
    private final boolean zzf;
    private final boolean zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzqp zzk;
    private final zzsd zzl;
    private final zzpj zzm;
    private final zzrg zzn;
    private final zzqw zzo;

    private zzre(int[] iArr, Object[] objArr, int i, int i2, zzrb zzrbVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzrg zzrgVar, zzqp zzqpVar, zzsd zzsdVar, zzpj zzpjVar, zzqw zzqwVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzg = z;
        boolean z3 = false;
        if (zzpjVar != null && zzpjVar.zzc(zzrbVar)) {
            z3 = true;
        }
        this.zzf = z3;
        this.zzh = iArr2;
        this.zzi = i3;
        this.zzj = i4;
        this.zzn = zzrgVar;
        this.zzk = zzqpVar;
        this.zzl = zzsdVar;
        this.zzm = zzpjVar;
        this.zze = zzrbVar;
        this.zzo = zzqwVar;
    }

    private final boolean zzA(Object obj, int i) {
        int iZzo = zzo(i);
        long j = iZzo & 1048575;
        if (j != 1048575) {
            return (zzsn.zzc(obj, j) & (1 << (iZzo >>> 20))) != 0;
        }
        int iZzq = zzq(i);
        long j2 = iZzq & 1048575;
        switch (zzp(iZzq)) {
            case 0:
                return Double.doubleToRawLongBits(zzsn.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzsn.zzb(obj, j2)) != 0;
            case 2:
                return zzsn.zzd(obj, j2) != 0;
            case 3:
                return zzsn.zzd(obj, j2) != 0;
            case 4:
                return zzsn.zzc(obj, j2) != 0;
            case 5:
                return zzsn.zzd(obj, j2) != 0;
            case 6:
                return zzsn.zzc(obj, j2) != 0;
            case 7:
                return zzsn.zzw(obj, j2);
            case 8:
                Object objZzf = zzsn.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzoy) {
                    return !zzoy.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzsn.zzf(obj, j2) != null;
            case 10:
                return !zzoy.zzb.equals(zzsn.zzf(obj, j2));
            case 11:
                return zzsn.zzc(obj, j2) != 0;
            case 12:
                return zzsn.zzc(obj, j2) != 0;
            case 13:
                return zzsn.zzc(obj, j2) != 0;
            case 14:
                return zzsn.zzd(obj, j2) != 0;
            case 15:
                return zzsn.zzc(obj, j2) != 0;
            case 16:
                return zzsn.zzd(obj, j2) != 0;
            case 17:
                return zzsn.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzB(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzA(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzC(Object obj, int i, zzrm zzrmVar) {
        return zzrmVar.zzf(zzsn.zzf(obj, i & 1048575));
    }

    private final boolean zzD(Object obj, int i, int i2) {
        return zzsn.zzc(obj, (long) (zzo(i2) & 1048575)) == i;
    }

    private static boolean zzE(Object obj, long j) {
        return ((Boolean) zzsn.zzf(obj, j)).booleanValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void zzF(Object obj, zzph zzphVar) throws IOException {
        int i;
        boolean z;
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int iZzq = zzq(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int iZzp = zzp(iZzq);
            if (iZzp <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            long j = iZzq & i2;
            switch (iZzp) {
                case 0:
                    if ((i5 & i) != 0) {
                        zzphVar.zzf(i6, zzsn.zza(obj, j));
                    }
                    break;
                case 1:
                    if ((i5 & i) != 0) {
                        zzphVar.zzn(i6, zzsn.zzb(obj, j));
                    }
                    break;
                case 2:
                    if ((i5 & i) != 0) {
                        zzphVar.zzs(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 3:
                    if ((i5 & i) != 0) {
                        zzphVar.zzH(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 4:
                    if ((i5 & i) != 0) {
                        zzphVar.zzq(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 5:
                    if ((i5 & i) != 0) {
                        zzphVar.zzl(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 6:
                    if ((i5 & i) != 0) {
                        zzphVar.zzj(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 7:
                    if ((i5 & i) != 0) {
                        zzphVar.zzb(i6, zzsn.zzw(obj, j));
                    }
                    break;
                case 8:
                    if ((i5 & i) != 0) {
                        zzH(i6, unsafe.getObject(obj, j), zzphVar);
                    }
                    break;
                case 9:
                    if ((i5 & i) != 0) {
                        zzphVar.zzu(i6, unsafe.getObject(obj, j), zzs(i4));
                    }
                    break;
                case 10:
                    if ((i5 & i) != 0) {
                        zzphVar.zzd(i6, (zzoy) unsafe.getObject(obj, j));
                    }
                    break;
                case 11:
                    if ((i5 & i) != 0) {
                        zzphVar.zzF(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 12:
                    if ((i5 & i) != 0) {
                        zzphVar.zzh(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 13:
                    if ((i5 & i) != 0) {
                        zzphVar.zzv(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 14:
                    if ((i5 & i) != 0) {
                        zzphVar.zzx(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 15:
                    if ((i5 & i) != 0) {
                        zzphVar.zzz(i6, unsafe.getInt(obj, j));
                    }
                    break;
                case 16:
                    if ((i5 & i) != 0) {
                        zzphVar.zzB(i6, unsafe.getLong(obj, j));
                    }
                    break;
                case 17:
                    if ((i5 & i) != 0) {
                        zzphVar.zzp(i6, unsafe.getObject(obj, j), zzs(i4));
                    }
                    break;
                case 18:
                    zzro.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 19:
                    zzro.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 20:
                    zzro.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 21:
                    zzro.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 22:
                    zzro.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 23:
                    zzro.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 24:
                    zzro.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 25:
                    zzro.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 26:
                    zzro.zzW(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar);
                    break;
                case 27:
                    zzro.zzR(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, zzs(i4));
                    break;
                case 28:
                    zzro.zzI(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar);
                    break;
                case 29:
                    z = false;
                    zzro.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 30:
                    z = false;
                    zzro.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 31:
                    z = false;
                    zzro.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 32:
                    z = false;
                    zzro.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 33:
                    z = false;
                    zzro.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 34:
                    z = false;
                    zzro.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, false);
                    break;
                case 35:
                    zzro.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 36:
                    zzro.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 37:
                    zzro.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 38:
                    zzro.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 39:
                    zzro.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 40:
                    zzro.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 41:
                    zzro.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 42:
                    zzro.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 43:
                    zzro.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 44:
                    zzro.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 45:
                    zzro.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 46:
                    zzro.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 47:
                    zzro.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 48:
                    zzro.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, true);
                    break;
                case 49:
                    zzro.zzO(this.zzc[i4], (List) unsafe.getObject(obj, j), zzphVar, zzs(i4));
                    break;
                case 50:
                    zzG(zzphVar, i6, unsafe.getObject(obj, j), i4);
                    break;
                case 51:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzf(i6, zzj(obj, j));
                    }
                    break;
                case 52:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzn(i6, zzk(obj, j));
                    }
                    break;
                case 53:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzs(i6, zzr(obj, j));
                    }
                    break;
                case 54:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzH(i6, zzr(obj, j));
                    }
                    break;
                case 55:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzq(i6, zzn(obj, j));
                    }
                    break;
                case 56:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzl(i6, zzr(obj, j));
                    }
                    break;
                case 57:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzj(i6, zzn(obj, j));
                    }
                    break;
                case 58:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzb(i6, zzE(obj, j));
                    }
                    break;
                case 59:
                    if (zzD(obj, i6, i4)) {
                        zzH(i6, unsafe.getObject(obj, j), zzphVar);
                    }
                    break;
                case 60:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzu(i6, unsafe.getObject(obj, j), zzs(i4));
                    }
                    break;
                case 61:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzd(i6, (zzoy) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzF(i6, zzn(obj, j));
                    }
                    break;
                case 63:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzh(i6, zzn(obj, j));
                    }
                    break;
                case 64:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzv(i6, zzn(obj, j));
                    }
                    break;
                case 65:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzx(i6, zzr(obj, j));
                    }
                    break;
                case 66:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzz(i6, zzn(obj, j));
                    }
                    break;
                case 67:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzB(i6, zzr(obj, j));
                    }
                    break;
                case 68:
                    if (zzD(obj, i6, i4)) {
                        zzphVar.zzp(i6, unsafe.getObject(obj, j), zzs(i4));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzsd zzsdVar = this.zzl;
        zzsdVar.zzg(zzsdVar.zzc(obj), zzphVar);
    }

    private final void zzG(zzph zzphVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private static final void zzH(int i, Object obj, zzph zzphVar) throws IOException {
        if (obj instanceof String) {
            zzphVar.zzD(i, (String) obj);
        } else {
            zzphVar.zzd(i, (zzoy) obj);
        }
    }

    static zzre zzg(Class cls, zzqy zzqyVar, zzrg zzrgVar, zzqp zzqpVar, zzsd zzsdVar, zzpj zzpjVar, zzqw zzqwVar) {
        if (zzqyVar instanceof zzrl) {
            return zzh((zzrl) zzqyVar, zzrgVar, zzqpVar, zzsdVar, zzpjVar, zzqwVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0379  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.gms.internal.cast.zzre zzh(com.google.android.gms.internal.cast.zzrl r34, com.google.android.gms.internal.cast.zzrg r35, com.google.android.gms.internal.cast.zzqp r36, com.google.android.gms.internal.cast.zzsd r37, com.google.android.gms.internal.cast.zzpj r38, com.google.android.gms.internal.cast.zzqw r39) {
        /*
            Method dump skipped, instruction units count: 1016
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzre.zzh(com.google.android.gms.internal.cast.zzrl, com.google.android.gms.internal.cast.zzrg, com.google.android.gms.internal.cast.zzqp, com.google.android.gms.internal.cast.zzsd, com.google.android.gms.internal.cast.zzpj, com.google.android.gms.internal.cast.zzqw):com.google.android.gms.internal.cast.zzre");
    }

    private static double zzj(Object obj, long j) {
        return ((Double) zzsn.zzf(obj, j)).doubleValue();
    }

    private static float zzk(Object obj, long j) {
        return ((Float) zzsn.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzl(Object obj) {
        int i;
        int iZzA;
        int iZzA2;
        int iZzA3;
        int iZzB;
        int iZzA4;
        int iZzv;
        int iZzA5;
        int iZzA6;
        int iZzd;
        int iZzA7;
        int i2;
        int iZzu;
        boolean z;
        int iZzd2;
        int iZzi;
        int iZzz;
        int iZzA8;
        int iZzA9;
        int iZzA10;
        int iZzA11;
        int iZzA12;
        int iZzB2;
        int iZzA13;
        int iZzd3;
        int iZzA14;
        int i3;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int iZzA15 = 0;
        int i7 = 0;
        while (i6 < this.zzc.length) {
            int iZzq = zzq(i6);
            int[] iArr = this.zzc;
            int i8 = iArr[i6];
            int iZzp = zzp(iZzq);
            if (iZzp <= 17) {
                int i9 = iArr[i6 + 2];
                int i10 = i9 & i4;
                i = 1 << (i9 >>> 20);
                if (i10 != i5) {
                    i7 = unsafe.getInt(obj, i10);
                    i5 = i10;
                }
            } else {
                i = 0;
            }
            long j = iZzq & i4;
            switch (iZzp) {
                case 0:
                    if ((i7 & i) != 0) {
                        iZzA = zzpg.zzA(i8 << 3);
                        iZzA5 = iZzA + 8;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 1:
                    if ((i7 & i) != 0) {
                        iZzA2 = zzpg.zzA(i8 << 3);
                        iZzA5 = iZzA2 + 4;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 2:
                    if ((i7 & i) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        iZzA3 = zzpg.zzA(i8 << 3);
                        iZzB = zzpg.zzB(j2);
                        iZzA15 += iZzA3 + iZzB;
                    }
                    break;
                case 3:
                    if ((i7 & i) != 0) {
                        long j3 = unsafe.getLong(obj, j);
                        iZzA3 = zzpg.zzA(i8 << 3);
                        iZzB = zzpg.zzB(j3);
                        iZzA15 += iZzA3 + iZzB;
                    }
                    break;
                case 4:
                    if ((i7 & i) != 0) {
                        int i11 = unsafe.getInt(obj, j);
                        iZzA4 = zzpg.zzA(i8 << 3);
                        iZzv = zzpg.zzv(i11);
                        i2 = iZzA4 + iZzv;
                        iZzA15 += i2;
                    }
                    break;
                case 5:
                    if ((i7 & i) != 0) {
                        iZzA = zzpg.zzA(i8 << 3);
                        iZzA5 = iZzA + 8;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 6:
                    if ((i7 & i) != 0) {
                        iZzA2 = zzpg.zzA(i8 << 3);
                        iZzA5 = iZzA2 + 4;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 7:
                    if ((i7 & i) != 0) {
                        iZzA5 = zzpg.zzA(i8 << 3) + 1;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 8:
                    if ((i7 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (!(object instanceof zzoy)) {
                            iZzA4 = zzpg.zzA(i8 << 3);
                            iZzv = zzpg.zzy((String) object);
                            i2 = iZzA4 + iZzv;
                            iZzA15 += i2;
                        } else {
                            iZzA6 = zzpg.zzA(i8 << 3);
                            iZzd = ((zzoy) object).zzd();
                            iZzA7 = zzpg.zzA(iZzd);
                            i2 = iZzA6 + iZzA7 + iZzd;
                            iZzA15 += i2;
                        }
                    }
                    break;
                case 9:
                    if ((i7 & i) != 0) {
                        iZzA5 = zzro.zzo(i8, unsafe.getObject(obj, j), zzs(i6));
                        iZzA15 += iZzA5;
                    }
                    break;
                case 10:
                    if ((i7 & i) != 0) {
                        zzoy zzoyVar = (zzoy) unsafe.getObject(obj, j);
                        iZzA6 = zzpg.zzA(i8 << 3);
                        iZzd = zzoyVar.zzd();
                        iZzA7 = zzpg.zzA(iZzd);
                        i2 = iZzA6 + iZzA7 + iZzd;
                        iZzA15 += i2;
                    }
                    break;
                case 11:
                    if ((i7 & i) != 0) {
                        int i12 = unsafe.getInt(obj, j);
                        iZzA4 = zzpg.zzA(i8 << 3);
                        iZzv = zzpg.zzA(i12);
                        i2 = iZzA4 + iZzv;
                        iZzA15 += i2;
                    }
                    break;
                case 12:
                    if ((i7 & i) != 0) {
                        int i13 = unsafe.getInt(obj, j);
                        iZzA4 = zzpg.zzA(i8 << 3);
                        iZzv = zzpg.zzv(i13);
                        i2 = iZzA4 + iZzv;
                        iZzA15 += i2;
                    }
                    break;
                case 13:
                    if ((i7 & i) != 0) {
                        iZzA2 = zzpg.zzA(i8 << 3);
                        iZzA5 = iZzA2 + 4;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 14:
                    if ((i7 & i) != 0) {
                        iZzA = zzpg.zzA(i8 << 3);
                        iZzA5 = iZzA + 8;
                        iZzA15 += iZzA5;
                    }
                    break;
                case 15:
                    if ((i7 & i) != 0) {
                        int i14 = unsafe.getInt(obj, j);
                        iZzA4 = zzpg.zzA(i8 << 3);
                        iZzv = zzpg.zzA((i14 >> 31) ^ (i14 + i14));
                        i2 = iZzA4 + iZzv;
                        iZzA15 += i2;
                    }
                    break;
                case 16:
                    if ((i & i7) != 0) {
                        long j4 = unsafe.getLong(obj, j);
                        iZzA15 += zzpg.zzA(i8 << 3) + zzpg.zzB((j4 >> 63) ^ (j4 + j4));
                    }
                    break;
                case 17:
                    if ((i7 & i) != 0) {
                        iZzA5 = zzpg.zzu(i8, (zzrb) unsafe.getObject(obj, j), zzs(i6));
                        iZzA15 += iZzA5;
                    }
                    break;
                case 18:
                    iZzA5 = zzro.zzh(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 19:
                    iZzA5 = zzro.zzf(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 20:
                    iZzA5 = zzro.zzm(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 21:
                    iZzA5 = zzro.zzx(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 22:
                    iZzA5 = zzro.zzk(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 23:
                    iZzA5 = zzro.zzh(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 24:
                    iZzA5 = zzro.zzf(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 25:
                    iZzA5 = zzro.zza(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzA5;
                    break;
                case 26:
                    iZzu = zzro.zzu(i8, (List) unsafe.getObject(obj, j));
                    iZzA15 += iZzu;
                    break;
                case 27:
                    iZzu = zzro.zzp(i8, (List) unsafe.getObject(obj, j), zzs(i6));
                    iZzA15 += iZzu;
                    break;
                case 28:
                    iZzu = zzro.zzc(i8, (List) unsafe.getObject(obj, j));
                    iZzA15 += iZzu;
                    break;
                case 29:
                    iZzu = zzro.zzv(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzu;
                    break;
                case 30:
                    z = false;
                    iZzd2 = zzro.zzd(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzd2;
                    break;
                case 31:
                    z = false;
                    iZzd2 = zzro.zzf(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzd2;
                    break;
                case 32:
                    z = false;
                    iZzd2 = zzro.zzh(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzd2;
                    break;
                case 33:
                    z = false;
                    iZzd2 = zzro.zzq(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzd2;
                    break;
                case 34:
                    z = false;
                    iZzd2 = zzro.zzs(i8, (List) unsafe.getObject(obj, j), false);
                    iZzA15 += iZzd2;
                    break;
                case 35:
                    iZzi = zzro.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 36:
                    iZzi = zzro.zzg((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 37:
                    iZzi = zzro.zzn((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 38:
                    iZzi = zzro.zzy((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 39:
                    iZzi = zzro.zzl((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 40:
                    iZzi = zzro.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 41:
                    iZzi = zzro.zzg((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 42:
                    iZzi = zzro.zzb((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 43:
                    iZzi = zzro.zzw((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 44:
                    iZzi = zzro.zze((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 45:
                    iZzi = zzro.zzg((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 46:
                    iZzi = zzro.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 47:
                    iZzi = zzro.zzr((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 48:
                    iZzi = zzro.zzt((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzz = zzpg.zzz(i8);
                        iZzA8 = zzpg.zzA(iZzi);
                        iZzA9 = iZzz + iZzA8;
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 49:
                    iZzu = zzro.zzj(i8, (List) unsafe.getObject(obj, j), zzs(i6));
                    iZzA15 += iZzu;
                    break;
                case 50:
                    zzqw.zza(i8, unsafe.getObject(obj, j), zzt(i6));
                    break;
                case 51:
                    if (zzD(obj, i8, i6)) {
                        iZzA10 = zzpg.zzA(i8 << 3);
                        iZzu = iZzA10 + 8;
                        iZzA15 += iZzu;
                    }
                    break;
                case 52:
                    if (zzD(obj, i8, i6)) {
                        iZzA11 = zzpg.zzA(i8 << 3);
                        iZzu = iZzA11 + 4;
                        iZzA15 += iZzu;
                    }
                    break;
                case 53:
                    if (zzD(obj, i8, i6)) {
                        long jZzr = zzr(obj, j);
                        iZzA12 = zzpg.zzA(i8 << 3);
                        iZzB2 = zzpg.zzB(jZzr);
                        iZzA15 += iZzA12 + iZzB2;
                    }
                    break;
                case 54:
                    if (zzD(obj, i8, i6)) {
                        long jZzr2 = zzr(obj, j);
                        iZzA12 = zzpg.zzA(i8 << 3);
                        iZzB2 = zzpg.zzB(jZzr2);
                        iZzA15 += iZzA12 + iZzB2;
                    }
                    break;
                case 55:
                    if (zzD(obj, i8, i6)) {
                        int iZzn = zzn(obj, j);
                        iZzA9 = zzpg.zzA(i8 << 3);
                        iZzi = zzpg.zzv(iZzn);
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 56:
                    if (zzD(obj, i8, i6)) {
                        iZzA10 = zzpg.zzA(i8 << 3);
                        iZzu = iZzA10 + 8;
                        iZzA15 += iZzu;
                    }
                    break;
                case 57:
                    if (zzD(obj, i8, i6)) {
                        iZzA11 = zzpg.zzA(i8 << 3);
                        iZzu = iZzA11 + 4;
                        iZzA15 += iZzu;
                    }
                    break;
                case 58:
                    if (zzD(obj, i8, i6)) {
                        iZzu = zzpg.zzA(i8 << 3) + 1;
                        iZzA15 += iZzu;
                    }
                    break;
                case 59:
                    if (zzD(obj, i8, i6)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzoy) {
                            iZzA13 = zzpg.zzA(i8 << 3);
                            iZzd3 = ((zzoy) object2).zzd();
                            iZzA14 = zzpg.zzA(iZzd3);
                            i3 = iZzA13 + iZzA14 + iZzd3;
                            iZzA15 += i3;
                        } else {
                            iZzA9 = zzpg.zzA(i8 << 3);
                            iZzi = zzpg.zzy((String) object2);
                            i3 = iZzA9 + iZzi;
                            iZzA15 += i3;
                        }
                    }
                    break;
                case 60:
                    if (zzD(obj, i8, i6)) {
                        iZzu = zzro.zzo(i8, unsafe.getObject(obj, j), zzs(i6));
                        iZzA15 += iZzu;
                    }
                    break;
                case 61:
                    if (zzD(obj, i8, i6)) {
                        zzoy zzoyVar2 = (zzoy) unsafe.getObject(obj, j);
                        iZzA13 = zzpg.zzA(i8 << 3);
                        iZzd3 = zzoyVar2.zzd();
                        iZzA14 = zzpg.zzA(iZzd3);
                        i3 = iZzA13 + iZzA14 + iZzd3;
                        iZzA15 += i3;
                    }
                    break;
                case 62:
                    if (zzD(obj, i8, i6)) {
                        int iZzn2 = zzn(obj, j);
                        iZzA9 = zzpg.zzA(i8 << 3);
                        iZzi = zzpg.zzA(iZzn2);
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 63:
                    if (zzD(obj, i8, i6)) {
                        int iZzn3 = zzn(obj, j);
                        iZzA9 = zzpg.zzA(i8 << 3);
                        iZzi = zzpg.zzv(iZzn3);
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 64:
                    if (zzD(obj, i8, i6)) {
                        iZzA11 = zzpg.zzA(i8 << 3);
                        iZzu = iZzA11 + 4;
                        iZzA15 += iZzu;
                    }
                    break;
                case 65:
                    if (zzD(obj, i8, i6)) {
                        iZzA10 = zzpg.zzA(i8 << 3);
                        iZzu = iZzA10 + 8;
                        iZzA15 += iZzu;
                    }
                    break;
                case 66:
                    if (zzD(obj, i8, i6)) {
                        int iZzn4 = zzn(obj, j);
                        iZzA9 = zzpg.zzA(i8 << 3);
                        iZzi = zzpg.zzA((iZzn4 >> 31) ^ (iZzn4 + iZzn4));
                        i3 = iZzA9 + iZzi;
                        iZzA15 += i3;
                    }
                    break;
                case 67:
                    if (zzD(obj, i8, i6)) {
                        long jZzr3 = zzr(obj, j);
                        iZzA15 += zzpg.zzA(i8 << 3) + zzpg.zzB((jZzr3 >> 63) ^ (jZzr3 + jZzr3));
                    }
                    break;
                case 68:
                    if (zzD(obj, i8, i6)) {
                        iZzu = zzpg.zzu(i8, (zzrb) unsafe.getObject(obj, j), zzs(i6));
                        iZzA15 += iZzu;
                    }
                    break;
            }
            i6 += 3;
            i4 = 1048575;
        }
        zzsd zzsdVar = this.zzl;
        int iZza = iZzA15 + zzsdVar.zza(zzsdVar.zzc(obj));
        if (!this.zzf) {
            return iZza;
        }
        this.zzm.zza(obj);
        throw null;
    }

    private final int zzm(Object obj) {
        int iZzA;
        int iZzA2;
        int iZzA3;
        int iZzB;
        int iZzA4;
        int iZzv;
        int iZzA5;
        int iZzA6;
        int iZzd;
        int iZzA7;
        int iZzo;
        int iZzz;
        int iZzA8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int iZzq = zzq(i3);
            int iZzp = zzp(iZzq);
            int i4 = this.zzc[i3];
            long j = iZzq & 1048575;
            if (iZzp >= zzpo.zzJ.zza() && iZzp <= zzpo.zzW.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (iZzp) {
                case 0:
                    if (zzA(obj, i3)) {
                        iZzA = zzpg.zzA(i4 << 3);
                        iZzo = iZzA + 8;
                        i2 += iZzo;
                    }
                    break;
                case 1:
                    if (zzA(obj, i3)) {
                        iZzA2 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA2 + 4;
                        i2 += iZzo;
                    }
                    break;
                case 2:
                    if (zzA(obj, i3)) {
                        long jZzd = zzsn.zzd(obj, j);
                        iZzA3 = zzpg.zzA(i4 << 3);
                        iZzB = zzpg.zzB(jZzd);
                        i2 += iZzA3 + iZzB;
                    }
                    break;
                case 3:
                    if (zzA(obj, i3)) {
                        long jZzd2 = zzsn.zzd(obj, j);
                        iZzA3 = zzpg.zzA(i4 << 3);
                        iZzB = zzpg.zzB(jZzd2);
                        i2 += iZzA3 + iZzB;
                    }
                    break;
                case 4:
                    if (zzA(obj, i3)) {
                        int iZzc = zzsn.zzc(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzv(iZzc);
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 5:
                    if (zzA(obj, i3)) {
                        iZzA = zzpg.zzA(i4 << 3);
                        iZzo = iZzA + 8;
                        i2 += iZzo;
                    }
                    break;
                case 6:
                    if (zzA(obj, i3)) {
                        iZzA2 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA2 + 4;
                        i2 += iZzo;
                    }
                    break;
                case 7:
                    if (zzA(obj, i3)) {
                        iZzA5 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA5 + 1;
                        i2 += iZzo;
                    }
                    break;
                case 8:
                    if (zzA(obj, i3)) {
                        Object objZzf = zzsn.zzf(obj, j);
                        if (objZzf instanceof zzoy) {
                            iZzA6 = zzpg.zzA(i4 << 3);
                            iZzd = ((zzoy) objZzf).zzd();
                            iZzA7 = zzpg.zzA(iZzd);
                            i = iZzA6 + iZzA7 + iZzd;
                            i2 += i;
                        } else {
                            iZzA4 = zzpg.zzA(i4 << 3);
                            iZzv = zzpg.zzy((String) objZzf);
                            i = iZzA4 + iZzv;
                            i2 += i;
                        }
                    }
                    break;
                case 9:
                    if (zzA(obj, i3)) {
                        iZzo = zzro.zzo(i4, zzsn.zzf(obj, j), zzs(i3));
                        i2 += iZzo;
                    }
                    break;
                case 10:
                    if (zzA(obj, i3)) {
                        zzoy zzoyVar = (zzoy) zzsn.zzf(obj, j);
                        iZzA6 = zzpg.zzA(i4 << 3);
                        iZzd = zzoyVar.zzd();
                        iZzA7 = zzpg.zzA(iZzd);
                        i = iZzA6 + iZzA7 + iZzd;
                        i2 += i;
                    }
                    break;
                case 11:
                    if (zzA(obj, i3)) {
                        int iZzc2 = zzsn.zzc(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzA(iZzc2);
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 12:
                    if (zzA(obj, i3)) {
                        int iZzc3 = zzsn.zzc(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzv(iZzc3);
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 13:
                    if (zzA(obj, i3)) {
                        iZzA2 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA2 + 4;
                        i2 += iZzo;
                    }
                    break;
                case 14:
                    if (zzA(obj, i3)) {
                        iZzA = zzpg.zzA(i4 << 3);
                        iZzo = iZzA + 8;
                        i2 += iZzo;
                    }
                    break;
                case 15:
                    if (zzA(obj, i3)) {
                        int iZzc4 = zzsn.zzc(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzA((iZzc4 >> 31) ^ (iZzc4 + iZzc4));
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 16:
                    if (zzA(obj, i3)) {
                        long jZzd3 = zzsn.zzd(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzB((jZzd3 >> 63) ^ (jZzd3 + jZzd3));
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 17:
                    if (zzA(obj, i3)) {
                        iZzo = zzpg.zzu(i4, (zzrb) zzsn.zzf(obj, j), zzs(i3));
                        i2 += iZzo;
                    }
                    break;
                case 18:
                    iZzo = zzro.zzh(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 19:
                    iZzo = zzro.zzf(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 20:
                    iZzo = zzro.zzm(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 21:
                    iZzo = zzro.zzx(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 22:
                    iZzo = zzro.zzk(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 23:
                    iZzo = zzro.zzh(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 24:
                    iZzo = zzro.zzf(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 25:
                    iZzo = zzro.zza(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 26:
                    iZzo = zzro.zzu(i4, (List) zzsn.zzf(obj, j));
                    i2 += iZzo;
                    break;
                case 27:
                    iZzo = zzro.zzp(i4, (List) zzsn.zzf(obj, j), zzs(i3));
                    i2 += iZzo;
                    break;
                case 28:
                    iZzo = zzro.zzc(i4, (List) zzsn.zzf(obj, j));
                    i2 += iZzo;
                    break;
                case 29:
                    iZzo = zzro.zzv(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 30:
                    iZzo = zzro.zzd(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 31:
                    iZzo = zzro.zzf(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 32:
                    iZzo = zzro.zzh(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 33:
                    iZzo = zzro.zzq(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 34:
                    iZzo = zzro.zzs(i4, (List) zzsn.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 35:
                    iZzv = zzro.zzi((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 36:
                    iZzv = zzro.zzg((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 37:
                    iZzv = zzro.zzn((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 38:
                    iZzv = zzro.zzy((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 39:
                    iZzv = zzro.zzl((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 40:
                    iZzv = zzro.zzi((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 41:
                    iZzv = zzro.zzg((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 42:
                    iZzv = zzro.zzb((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 43:
                    iZzv = zzro.zzw((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 44:
                    iZzv = zzro.zze((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 45:
                    iZzv = zzro.zzg((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 46:
                    iZzv = zzro.zzi((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 47:
                    iZzv = zzro.zzr((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 48:
                    iZzv = zzro.zzt((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzz = zzpg.zzz(i4);
                        iZzA8 = zzpg.zzA(iZzv);
                        iZzA4 = iZzz + iZzA8;
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 49:
                    iZzo = zzro.zzj(i4, (List) zzsn.zzf(obj, j), zzs(i3));
                    i2 += iZzo;
                    break;
                case 50:
                    zzqw.zza(i4, zzsn.zzf(obj, j), zzt(i3));
                    break;
                case 51:
                    if (zzD(obj, i4, i3)) {
                        iZzA = zzpg.zzA(i4 << 3);
                        iZzo = iZzA + 8;
                        i2 += iZzo;
                    }
                    break;
                case 52:
                    if (zzD(obj, i4, i3)) {
                        iZzA2 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA2 + 4;
                        i2 += iZzo;
                    }
                    break;
                case 53:
                    if (zzD(obj, i4, i3)) {
                        long jZzr = zzr(obj, j);
                        iZzA3 = zzpg.zzA(i4 << 3);
                        iZzB = zzpg.zzB(jZzr);
                        i2 += iZzA3 + iZzB;
                    }
                    break;
                case 54:
                    if (zzD(obj, i4, i3)) {
                        long jZzr2 = zzr(obj, j);
                        iZzA3 = zzpg.zzA(i4 << 3);
                        iZzB = zzpg.zzB(jZzr2);
                        i2 += iZzA3 + iZzB;
                    }
                    break;
                case 55:
                    if (zzD(obj, i4, i3)) {
                        int iZzn = zzn(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzv(iZzn);
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 56:
                    if (zzD(obj, i4, i3)) {
                        iZzA = zzpg.zzA(i4 << 3);
                        iZzo = iZzA + 8;
                        i2 += iZzo;
                    }
                    break;
                case 57:
                    if (zzD(obj, i4, i3)) {
                        iZzA2 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA2 + 4;
                        i2 += iZzo;
                    }
                    break;
                case 58:
                    if (zzD(obj, i4, i3)) {
                        iZzA5 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA5 + 1;
                        i2 += iZzo;
                    }
                    break;
                case 59:
                    if (zzD(obj, i4, i3)) {
                        Object objZzf2 = zzsn.zzf(obj, j);
                        if (objZzf2 instanceof zzoy) {
                            iZzA6 = zzpg.zzA(i4 << 3);
                            iZzd = ((zzoy) objZzf2).zzd();
                            iZzA7 = zzpg.zzA(iZzd);
                            i = iZzA6 + iZzA7 + iZzd;
                            i2 += i;
                        } else {
                            iZzA4 = zzpg.zzA(i4 << 3);
                            iZzv = zzpg.zzy((String) objZzf2);
                            i = iZzA4 + iZzv;
                            i2 += i;
                        }
                    }
                    break;
                case 60:
                    if (zzD(obj, i4, i3)) {
                        iZzo = zzro.zzo(i4, zzsn.zzf(obj, j), zzs(i3));
                        i2 += iZzo;
                    }
                    break;
                case 61:
                    if (zzD(obj, i4, i3)) {
                        zzoy zzoyVar2 = (zzoy) zzsn.zzf(obj, j);
                        iZzA6 = zzpg.zzA(i4 << 3);
                        iZzd = zzoyVar2.zzd();
                        iZzA7 = zzpg.zzA(iZzd);
                        i = iZzA6 + iZzA7 + iZzd;
                        i2 += i;
                    }
                    break;
                case 62:
                    if (zzD(obj, i4, i3)) {
                        int iZzn2 = zzn(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzA(iZzn2);
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 63:
                    if (zzD(obj, i4, i3)) {
                        int iZzn3 = zzn(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzv(iZzn3);
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 64:
                    if (zzD(obj, i4, i3)) {
                        iZzA2 = zzpg.zzA(i4 << 3);
                        iZzo = iZzA2 + 4;
                        i2 += iZzo;
                    }
                    break;
                case 65:
                    if (zzD(obj, i4, i3)) {
                        iZzA = zzpg.zzA(i4 << 3);
                        iZzo = iZzA + 8;
                        i2 += iZzo;
                    }
                    break;
                case 66:
                    if (zzD(obj, i4, i3)) {
                        int iZzn4 = zzn(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzA((iZzn4 >> 31) ^ (iZzn4 + iZzn4));
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 67:
                    if (zzD(obj, i4, i3)) {
                        long jZzr3 = zzr(obj, j);
                        iZzA4 = zzpg.zzA(i4 << 3);
                        iZzv = zzpg.zzB((jZzr3 >> 63) ^ (jZzr3 + jZzr3));
                        i = iZzA4 + iZzv;
                        i2 += i;
                    }
                    break;
                case 68:
                    if (zzD(obj, i4, i3)) {
                        iZzo = zzpg.zzu(i4, (zzrb) zzsn.zzf(obj, j), zzs(i3));
                        i2 += iZzo;
                    }
                    break;
            }
        }
        zzsd zzsdVar = this.zzl;
        return i2 + zzsdVar.zza(zzsdVar.zzc(obj));
    }

    private static int zzn(Object obj, long j) {
        return ((Integer) zzsn.zzf(obj, j)).intValue();
    }

    private final int zzo(int i) {
        return this.zzc[i + 2];
    }

    private static int zzp(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzq(int i) {
        return this.zzc[i + 1];
    }

    private static long zzr(Object obj, long j) {
        return ((Long) zzsn.zzf(obj, j)).longValue();
    }

    private final zzrm zzs(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzrm zzrmVar = (zzrm) this.zzd[i3];
        if (zzrmVar != null) {
            return zzrmVar;
        }
        zzrm zzrmVarZzb = zzrj.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzrmVarZzb;
        return zzrmVarZzb;
    }

    private final Object zzt(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzu(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(string).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(string);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzv(Object obj, Object obj2, int i) {
        long jZzq = zzq(i) & 1048575;
        if (zzA(obj2, i)) {
            Object objZzf = zzsn.zzf(obj, jZzq);
            Object objZzf2 = zzsn.zzf(obj2, jZzq);
            if (objZzf != null && objZzf2 != null) {
                zzsn.zzs(obj, jZzq, zzqc.zzg(objZzf, objZzf2));
                zzx(obj, i);
            } else if (objZzf2 != null) {
                zzsn.zzs(obj, jZzq, objZzf2);
                zzx(obj, i);
            }
        }
    }

    private final void zzw(Object obj, Object obj2, int i) {
        int iZzq = zzq(i);
        int i2 = this.zzc[i];
        long j = iZzq & 1048575;
        if (zzD(obj2, i2, i)) {
            Object objZzf = zzD(obj, i2, i) ? zzsn.zzf(obj, j) : null;
            Object objZzf2 = zzsn.zzf(obj2, j);
            if (objZzf != null && objZzf2 != null) {
                zzsn.zzs(obj, j, zzqc.zzg(objZzf, objZzf2));
                zzy(obj, i2, i);
            } else if (objZzf2 != null) {
                zzsn.zzs(obj, j, objZzf2);
                zzy(obj, i2, i);
            }
        }
    }

    private final void zzx(Object obj, int i) {
        int iZzo = zzo(i);
        long j = 1048575 & iZzo;
        if (j == 1048575) {
            return;
        }
        zzsn.zzq(obj, j, (1 << (iZzo >>> 20)) | zzsn.zzc(obj, j));
    }

    private final void zzy(Object obj, int i, int i2) {
        zzsn.zzq(obj, zzo(i2) & 1048575, i);
    }

    private final boolean zzz(Object obj, Object obj2, int i) {
        return zzA(obj, i) == zzA(obj2, i);
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final int zza(Object obj) {
        return this.zzg ? zzm(obj) : zzl(obj);
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final int zzb(Object obj) {
        int i;
        int iZzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzq = zzq(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzq;
            int iHashCode = 37;
            switch (zzp(iZzq)) {
                case 0:
                    i = i2 * 53;
                    iZzc = zzqc.zzc(Double.doubleToLongBits(zzsn.zza(obj, j)));
                    i2 = i + iZzc;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzc = Float.floatToIntBits(zzsn.zzb(obj, j));
                    i2 = i + iZzc;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzc = zzqc.zzc(zzsn.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzc = zzqc.zzc(zzsn.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzc = zzsn.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzc = zzqc.zzc(zzsn.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzc = zzsn.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzc = zzqc.zza(zzsn.zzw(obj, j));
                    i2 = i + iZzc;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzc = ((String) zzsn.zzf(obj, j)).hashCode();
                    i2 = i + iZzc;
                    break;
                case 9:
                    Object objZzf = zzsn.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzc = zzsn.zzf(obj, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzc = zzsn.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzc = zzsn.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzc = zzsn.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzc = zzqc.zzc(zzsn.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzc = zzsn.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzc = zzqc.zzc(zzsn.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 17:
                    Object objZzf2 = zzsn.zzf(obj, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iZzc = zzsn.zzf(obj, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzc = zzsn.zzf(obj, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 51:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zzc(Double.doubleToLongBits(zzj(obj, j)));
                        i2 = i + iZzc;
                    }
                    break;
                case 52:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = Float.floatToIntBits(zzk(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 53:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zzc(zzr(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 54:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zzc(zzr(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 55:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzn(obj, j);
                        i2 = i + iZzc;
                    }
                    break;
                case 56:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zzc(zzr(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 57:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzn(obj, j);
                        i2 = i + iZzc;
                    }
                    break;
                case 58:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zza(zzE(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 59:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = ((String) zzsn.zzf(obj, j)).hashCode();
                        i2 = i + iZzc;
                    }
                    break;
                case 60:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzsn.zzf(obj, j).hashCode();
                        i2 = i + iZzc;
                    }
                    break;
                case 61:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzsn.zzf(obj, j).hashCode();
                        i2 = i + iZzc;
                    }
                    break;
                case 62:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzn(obj, j);
                        i2 = i + iZzc;
                    }
                    break;
                case 63:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzn(obj, j);
                        i2 = i + iZzc;
                    }
                    break;
                case 64:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzn(obj, j);
                        i2 = i + iZzc;
                    }
                    break;
                case 65:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zzc(zzr(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 66:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzn(obj, j);
                        i2 = i + iZzc;
                    }
                    break;
                case 67:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzqc.zzc(zzr(obj, j));
                        i2 = i + iZzc;
                    }
                    break;
                case 68:
                    if (zzD(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzsn.zzf(obj, j).hashCode();
                        i2 = i + iZzc;
                    }
                    break;
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzl.zzc(obj).hashCode();
        if (!this.zzf) {
            return iHashCode2;
        }
        this.zzm.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final void zzc(Object obj) {
        int i;
        int i2 = this.zzi;
        while (true) {
            i = this.zzj;
            if (i2 >= i) {
                break;
            }
            long jZzq = zzq(this.zzh[i2]) & 1048575;
            Object objZzf = zzsn.zzf(obj, jZzq);
            if (objZzf != null) {
                ((zzqv) objZzf).zzb();
                zzsn.zzs(obj, jZzq, objZzf);
            }
            i2++;
        }
        int length = this.zzh.length;
        while (i < length) {
            this.zzk.zza(obj, this.zzh[i]);
            i++;
        }
        this.zzl.zze(obj);
        if (this.zzf) {
            this.zzm.zzb(obj);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final boolean zze(Object obj, Object obj2) {
        boolean zZzF;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzq = zzq(i);
            long j = iZzq & 1048575;
            switch (zzp(iZzq)) {
                case 0:
                    if (!zzz(obj, obj2, i) || Double.doubleToLongBits(zzsn.zza(obj, j)) != Double.doubleToLongBits(zzsn.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzz(obj, obj2, i) || Float.floatToIntBits(zzsn.zzb(obj, j)) != Float.floatToIntBits(zzsn.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzz(obj, obj2, i) || zzsn.zzd(obj, j) != zzsn.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzz(obj, obj2, i) || zzsn.zzd(obj, j) != zzsn.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzz(obj, obj2, i) || zzsn.zzc(obj, j) != zzsn.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzz(obj, obj2, i) || zzsn.zzd(obj, j) != zzsn.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzz(obj, obj2, i) || zzsn.zzc(obj, j) != zzsn.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzz(obj, obj2, i) || zzsn.zzw(obj, j) != zzsn.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzz(obj, obj2, i) || !zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzz(obj, obj2, i) || !zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzz(obj, obj2, i) || !zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzz(obj, obj2, i) || zzsn.zzc(obj, j) != zzsn.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzz(obj, obj2, i) || zzsn.zzc(obj, j) != zzsn.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzz(obj, obj2, i) || zzsn.zzc(obj, j) != zzsn.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzz(obj, obj2, i) || zzsn.zzd(obj, j) != zzsn.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzz(obj, obj2, i) || zzsn.zzc(obj, j) != zzsn.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzz(obj, obj2, i) || zzsn.zzd(obj, j) != zzsn.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzz(obj, obj2, i) || !zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzF = zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j));
                    break;
                case 50:
                    zZzF = zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzo = zzo(i) & 1048575;
                    if (zzsn.zzc(obj, jZzo) != zzsn.zzc(obj2, jZzo) || !zzro.zzF(zzsn.zzf(obj, j), zzsn.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    break;
            }
            if (!zZzF) {
                return false;
            }
        }
        if (!this.zzl.zzc(obj).equals(this.zzl.zzc(obj2))) {
            return false;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzm.zza(obj);
        this.zzm.zza(obj2);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009e  */
    @Override // com.google.android.gms.internal.cast.zzrm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzf(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzre.zzf(java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final void zzi(Object obj, zzph zzphVar) throws IOException {
        if (!this.zzg) {
            zzF(obj, zzphVar);
            return;
        }
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzq = zzq(i);
            int i2 = this.zzc[i];
            switch (zzp(iZzq)) {
                case 0:
                    if (zzA(obj, i)) {
                        zzphVar.zzf(i2, zzsn.zza(obj, iZzq & 1048575));
                    }
                    break;
                case 1:
                    if (zzA(obj, i)) {
                        zzphVar.zzn(i2, zzsn.zzb(obj, iZzq & 1048575));
                    }
                    break;
                case 2:
                    if (zzA(obj, i)) {
                        zzphVar.zzs(i2, zzsn.zzd(obj, iZzq & 1048575));
                    }
                    break;
                case 3:
                    if (zzA(obj, i)) {
                        zzphVar.zzH(i2, zzsn.zzd(obj, iZzq & 1048575));
                    }
                    break;
                case 4:
                    if (zzA(obj, i)) {
                        zzphVar.zzq(i2, zzsn.zzc(obj, iZzq & 1048575));
                    }
                    break;
                case 5:
                    if (zzA(obj, i)) {
                        zzphVar.zzl(i2, zzsn.zzd(obj, iZzq & 1048575));
                    }
                    break;
                case 6:
                    if (zzA(obj, i)) {
                        zzphVar.zzj(i2, zzsn.zzc(obj, iZzq & 1048575));
                    }
                    break;
                case 7:
                    if (zzA(obj, i)) {
                        zzphVar.zzb(i2, zzsn.zzw(obj, iZzq & 1048575));
                    }
                    break;
                case 8:
                    if (zzA(obj, i)) {
                        zzH(i2, zzsn.zzf(obj, iZzq & 1048575), zzphVar);
                    }
                    break;
                case 9:
                    if (zzA(obj, i)) {
                        zzphVar.zzu(i2, zzsn.zzf(obj, iZzq & 1048575), zzs(i));
                    }
                    break;
                case 10:
                    if (zzA(obj, i)) {
                        zzphVar.zzd(i2, (zzoy) zzsn.zzf(obj, iZzq & 1048575));
                    }
                    break;
                case 11:
                    if (zzA(obj, i)) {
                        zzphVar.zzF(i2, zzsn.zzc(obj, iZzq & 1048575));
                    }
                    break;
                case 12:
                    if (zzA(obj, i)) {
                        zzphVar.zzh(i2, zzsn.zzc(obj, iZzq & 1048575));
                    }
                    break;
                case 13:
                    if (zzA(obj, i)) {
                        zzphVar.zzv(i2, zzsn.zzc(obj, iZzq & 1048575));
                    }
                    break;
                case 14:
                    if (zzA(obj, i)) {
                        zzphVar.zzx(i2, zzsn.zzd(obj, iZzq & 1048575));
                    }
                    break;
                case 15:
                    if (zzA(obj, i)) {
                        zzphVar.zzz(i2, zzsn.zzc(obj, iZzq & 1048575));
                    }
                    break;
                case 16:
                    if (zzA(obj, i)) {
                        zzphVar.zzB(i2, zzsn.zzd(obj, iZzq & 1048575));
                    }
                    break;
                case 17:
                    if (zzA(obj, i)) {
                        zzphVar.zzp(i2, zzsn.zzf(obj, iZzq & 1048575), zzs(i));
                    }
                    break;
                case 18:
                    zzro.zzJ(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 19:
                    zzro.zzN(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 20:
                    zzro.zzQ(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 21:
                    zzro.zzY(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 22:
                    zzro.zzP(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 23:
                    zzro.zzM(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 24:
                    zzro.zzL(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 25:
                    zzro.zzH(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 26:
                    zzro.zzW(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar);
                    break;
                case 27:
                    zzro.zzR(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, zzs(i));
                    break;
                case 28:
                    zzro.zzI(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar);
                    break;
                case 29:
                    zzro.zzX(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 30:
                    zzro.zzK(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 31:
                    zzro.zzS(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 32:
                    zzro.zzT(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 33:
                    zzro.zzU(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 34:
                    zzro.zzV(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, false);
                    break;
                case 35:
                    zzro.zzJ(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 36:
                    zzro.zzN(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 37:
                    zzro.zzQ(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 38:
                    zzro.zzY(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 39:
                    zzro.zzP(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 40:
                    zzro.zzM(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 41:
                    zzro.zzL(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 42:
                    zzro.zzH(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 43:
                    zzro.zzX(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 44:
                    zzro.zzK(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 45:
                    zzro.zzS(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 46:
                    zzro.zzT(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 47:
                    zzro.zzU(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 48:
                    zzro.zzV(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, true);
                    break;
                case 49:
                    zzro.zzO(i2, (List) zzsn.zzf(obj, iZzq & 1048575), zzphVar, zzs(i));
                    break;
                case 50:
                    zzG(zzphVar, i2, zzsn.zzf(obj, iZzq & 1048575), i);
                    break;
                case 51:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzf(i2, zzj(obj, iZzq & 1048575));
                    }
                    break;
                case 52:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzn(i2, zzk(obj, iZzq & 1048575));
                    }
                    break;
                case 53:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzs(i2, zzr(obj, iZzq & 1048575));
                    }
                    break;
                case 54:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzH(i2, zzr(obj, iZzq & 1048575));
                    }
                    break;
                case 55:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzq(i2, zzn(obj, iZzq & 1048575));
                    }
                    break;
                case 56:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzl(i2, zzr(obj, iZzq & 1048575));
                    }
                    break;
                case 57:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzj(i2, zzn(obj, iZzq & 1048575));
                    }
                    break;
                case 58:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzb(i2, zzE(obj, iZzq & 1048575));
                    }
                    break;
                case 59:
                    if (zzD(obj, i2, i)) {
                        zzH(i2, zzsn.zzf(obj, iZzq & 1048575), zzphVar);
                    }
                    break;
                case 60:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzu(i2, zzsn.zzf(obj, iZzq & 1048575), zzs(i));
                    }
                    break;
                case 61:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzd(i2, (zzoy) zzsn.zzf(obj, iZzq & 1048575));
                    }
                    break;
                case 62:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzF(i2, zzn(obj, iZzq & 1048575));
                    }
                    break;
                case 63:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzh(i2, zzn(obj, iZzq & 1048575));
                    }
                    break;
                case 64:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzv(i2, zzn(obj, iZzq & 1048575));
                    }
                    break;
                case 65:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzx(i2, zzr(obj, iZzq & 1048575));
                    }
                    break;
                case 66:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzz(i2, zzn(obj, iZzq & 1048575));
                    }
                    break;
                case 67:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzB(i2, zzr(obj, iZzq & 1048575));
                    }
                    break;
                case 68:
                    if (zzD(obj, i2, i)) {
                        zzphVar.zzp(i2, zzsn.zzf(obj, iZzq & 1048575), zzs(i));
                    }
                    break;
            }
        }
        zzsd zzsdVar = this.zzl;
        zzsdVar.zzg(zzsdVar.zzc(obj), zzphVar);
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final void zzd(Object obj, Object obj2) {
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzq = zzq(i);
            long j = 1048575 & iZzq;
            int i2 = this.zzc[i];
            switch (zzp(iZzq)) {
                case 0:
                    if (zzA(obj2, i)) {
                        zzsn.zzo(obj, j, zzsn.zza(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 1:
                    if (zzA(obj2, i)) {
                        zzsn.zzp(obj, j, zzsn.zzb(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 2:
                    if (zzA(obj2, i)) {
                        zzsn.zzr(obj, j, zzsn.zzd(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 3:
                    if (zzA(obj2, i)) {
                        zzsn.zzr(obj, j, zzsn.zzd(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 4:
                    if (zzA(obj2, i)) {
                        zzsn.zzq(obj, j, zzsn.zzc(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 5:
                    if (zzA(obj2, i)) {
                        zzsn.zzr(obj, j, zzsn.zzd(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 6:
                    if (zzA(obj2, i)) {
                        zzsn.zzq(obj, j, zzsn.zzc(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 7:
                    if (zzA(obj2, i)) {
                        zzsn.zzm(obj, j, zzsn.zzw(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 8:
                    if (zzA(obj2, i)) {
                        zzsn.zzs(obj, j, zzsn.zzf(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 9:
                    zzv(obj, obj2, i);
                    break;
                case 10:
                    if (zzA(obj2, i)) {
                        zzsn.zzs(obj, j, zzsn.zzf(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 11:
                    if (zzA(obj2, i)) {
                        zzsn.zzq(obj, j, zzsn.zzc(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 12:
                    if (zzA(obj2, i)) {
                        zzsn.zzq(obj, j, zzsn.zzc(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 13:
                    if (zzA(obj2, i)) {
                        zzsn.zzq(obj, j, zzsn.zzc(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 14:
                    if (zzA(obj2, i)) {
                        zzsn.zzr(obj, j, zzsn.zzd(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 15:
                    if (zzA(obj2, i)) {
                        zzsn.zzq(obj, j, zzsn.zzc(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 16:
                    if (zzA(obj2, i)) {
                        zzsn.zzr(obj, j, zzsn.zzd(obj2, j));
                        zzx(obj, i);
                    }
                    break;
                case 17:
                    zzv(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzk.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzro.zzG(this.zzo, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzD(obj2, i2, i)) {
                        zzsn.zzs(obj, j, zzsn.zzf(obj2, j));
                        zzy(obj, i2, i);
                    }
                    break;
                case 60:
                    zzw(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzD(obj2, i2, i)) {
                        zzsn.zzs(obj, j, zzsn.zzf(obj2, j));
                        zzy(obj, i2, i);
                    }
                    break;
                case 68:
                    zzw(obj, obj2, i);
                    break;
            }
        }
        zzro.zzD(this.zzl, obj, obj2);
        if (this.zzf) {
            zzro.zzC(this.zzm, obj, obj2);
        }
    }
}
