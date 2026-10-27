package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzro {
    private static final Class zza;
    private static final zzsd zzb;
    private static final zzsd zzc;
    private static final zzsd zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzZ(false);
        zzc = zzZ(true);
        zzd = new zzsf();
    }

    public static zzsd zzA() {
        return zzc;
    }

    public static zzsd zzB() {
        return zzd;
    }

    static void zzC(zzpj zzpjVar, Object obj, Object obj2) {
        zzpjVar.zza(obj2);
        throw null;
    }

    static void zzD(zzsd zzsdVar, Object obj, Object obj2) {
        zzsdVar.zzf(obj, zzsdVar.zzd(zzsdVar.zzc(obj), zzsdVar.zzc(obj2)));
    }

    public static void zzE(Class cls) {
        Class cls2;
        if (!zzpt.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzF(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static void zzG(zzqw zzqwVar, Object obj, Object obj2, long j) {
        zzqv zzqvVarZza = (zzqv) zzsn.zzf(obj, j);
        zzqv zzqvVar = (zzqv) zzsn.zzf(obj2, j);
        if (!zzqvVar.isEmpty()) {
            if (!zzqvVarZza.zzd()) {
                zzqvVarZza = zzqvVarZza.zza();
            }
            zzqvVarZza.zzc(zzqvVar);
        }
        zzsn.zzs(obj, j, zzqvVarZza);
    }

    public static void zzH(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzc(i, list, z);
    }

    public static void zzI(int i, List list, zzph zzphVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zze(i, list);
    }

    public static void zzJ(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzg(i, list, z);
    }

    public static void zzK(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzi(i, list, z);
    }

    public static void zzL(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzk(i, list, z);
    }

    public static void zzM(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzm(i, list, z);
    }

    public static void zzN(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzo(i, list, z);
    }

    public static void zzO(int i, List list, zzph zzphVar, zzrm zzrmVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzphVar.zzp(i, list.get(i2), zzrmVar);
        }
    }

    public static void zzP(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzr(i, list, z);
    }

    public static void zzQ(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzt(i, list, z);
    }

    public static void zzR(int i, List list, zzph zzphVar, zzrm zzrmVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzphVar.zzu(i, list.get(i2), zzrmVar);
        }
    }

    public static void zzS(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzw(i, list, z);
    }

    public static void zzT(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzy(i, list, z);
    }

    public static void zzU(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzA(i, list, z);
    }

    public static void zzV(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzC(i, list, z);
    }

    public static void zzW(int i, List list, zzph zzphVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzE(i, list);
    }

    public static void zzX(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzG(i, list, z);
    }

    public static void zzY(int i, List list, zzph zzphVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzphVar.zzI(i, list, z);
    }

    private static zzsd zzZ(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzsd) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzpg.zzA(i << 3) + 1);
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = size * zzpg.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzz += zzpg.zzt((zzoy) list.get(i2));
        }
        return iZzz;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzpg.zzz(i));
    }

    static int zze(List list) {
        int iZzv;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzpu) {
            zzpu zzpuVar = (zzpu) list;
            iZzv = 0;
            while (i < size) {
                iZzv += zzpg.zzv(zzpuVar.zzd(i));
                i++;
            }
        } else {
            iZzv = 0;
            while (i < size) {
                iZzv += zzpg.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzv;
    }

    static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzpg.zzA(i << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzpg.zzA(i << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i, List list, zzrm zzrmVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzu = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzu += zzpg.zzu(i, (zzrb) list.get(i2), zzrmVar);
        }
        return iZzu;
    }

    static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzpg.zzz(i));
    }

    static int zzl(List list) {
        int iZzv;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzpu) {
            zzpu zzpuVar = (zzpu) list;
            iZzv = 0;
            while (i < size) {
                iZzv += zzpg.zzv(zzpuVar.zzd(i));
                i++;
            }
        } else {
            iZzv = 0;
            while (i < size) {
                iZzv += zzpg.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzv;
    }

    static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzpg.zzz(i));
    }

    static int zzn(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzqq) {
            zzqq zzqqVar = (zzqq) list;
            iZzB = 0;
            while (i < size) {
                iZzB += zzpg.zzB(zzqqVar.zzd(i));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                iZzB += zzpg.zzB(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzB;
    }

    static int zzo(int i, Object obj, zzrm zzrmVar) {
        if (!(obj instanceof zzqh)) {
            return zzpg.zzA(i << 3) + zzpg.zzx((zzrb) obj, zzrmVar);
        }
        int iZzA = zzpg.zzA(i << 3);
        int iZza = ((zzqh) obj).zza();
        return iZzA + zzpg.zzA(iZza) + iZza;
    }

    static int zzp(int i, List list, zzrm zzrmVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = zzpg.zzz(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            iZzz += obj instanceof zzqh ? zzpg.zzw((zzqh) obj) : zzpg.zzx((zzrb) obj, zzrmVar);
        }
        return iZzz;
    }

    static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzpg.zzz(i));
    }

    static int zzr(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzpu) {
            zzpu zzpuVar = (zzpu) list;
            iZzA = 0;
            while (i < size) {
                int iZzd = zzpuVar.zzd(i);
                iZzA += zzpg.zzA((iZzd >> 31) ^ (iZzd + iZzd));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzA += zzpg.zzA((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzA;
    }

    static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzpg.zzz(i));
    }

    static int zzt(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzqq) {
            zzqq zzqqVar = (zzqq) list;
            iZzB = 0;
            while (i < size) {
                long jZzd = zzqqVar.zzd(i);
                iZzB += zzpg.zzB((jZzd >> 63) ^ (jZzd + jZzd));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzB += zzpg.zzB((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzB;
    }

    static int zzu(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzz = zzpg.zzz(i) * size;
        if (list instanceof zzqj) {
            zzqj zzqjVar = (zzqj) list;
            while (i2 < size) {
                Object objZze = zzqjVar.zze(i2);
                iZzz += objZze instanceof zzoy ? zzpg.zzt((zzoy) objZze) : zzpg.zzy((String) objZze);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                iZzz += obj instanceof zzoy ? zzpg.zzt((zzoy) obj) : zzpg.zzy((String) obj);
                i2++;
            }
        }
        return iZzz;
    }

    static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzpg.zzz(i));
    }

    static int zzw(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzpu) {
            zzpu zzpuVar = (zzpu) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzpg.zzA(zzpuVar.zzd(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzpg.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzA;
    }

    static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzpg.zzz(i));
    }

    static int zzy(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzqq) {
            zzqq zzqqVar = (zzqq) list;
            iZzB = 0;
            while (i < size) {
                iZzB += zzpg.zzB(zzqqVar.zzd(i));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                iZzB += zzpg.zzB(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzB;
    }

    public static zzsd zzz() {
        return zzb;
    }
}
