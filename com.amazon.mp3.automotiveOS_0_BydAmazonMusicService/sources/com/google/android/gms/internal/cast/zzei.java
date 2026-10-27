package com.google.android.gms.internal.cast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzei extends zzea implements Set {

    @CheckForNull
    private transient zzee zza;

    zzei() {
    }

    static int zzh(int i) {
        int iMax = Math.max(i, 2);
        if (iMax >= 751619276) {
            if (iMax < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1);
        do {
            iHighestOneBit += iHighestOneBit;
        } while (((double) iHighestOneBit) * 0.7d < iMax);
        return iHighestOneBit;
    }

    public static zzei zzj(Collection collection) {
        Object[] array = collection.toArray();
        return zzm(array.length, array);
    }

    public static zzei zzk() {
        return zzer.zza;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzei) && zzl() && ((zzei) obj).zzl() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzes.zza(this);
    }

    @Override // com.google.android.gms.internal.cast.zzea
    public zzee zzd() {
        zzee zzeeVar = this.zza;
        if (zzeeVar != null) {
            return zzeeVar;
        }
        zzee zzeeVarZzi = zzi();
        this.zza = zzeeVarZzi;
        return zzeeVarZzi;
    }

    @Override // com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: zze */
    public abstract zzeu iterator();

    zzee zzi() {
        return zzee.zzj(toArray());
    }

    boolean zzl() {
        return false;
    }

    private static zzei zzm(int i, Object... objArr) {
        if (i == 0) {
            return zzer.zza;
        }
        if (i == 1) {
            Object obj = objArr[0];
            obj.getClass();
            return new zzet(obj);
        }
        int iZzh = zzh(i);
        Object[] objArr2 = new Object[iZzh];
        int i2 = iZzh - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj2 = objArr[i5];
            zzek.zza(obj2, i5);
            int iHashCode = obj2.hashCode();
            int iZza = zzdx.zza(iHashCode);
            while (true) {
                int i6 = iZza & i2;
                Object obj3 = objArr2[i6];
                if (obj3 == null) {
                    objArr[i4] = obj2;
                    objArr2[i6] = obj2;
                    i3 += iHashCode;
                    i4++;
                    break;
                }
                if (obj3.equals(obj2)) {
                    break;
                }
                iZza++;
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            Object obj4 = objArr[0];
            obj4.getClass();
            return new zzet(obj4);
        }
        if (zzh(i4) < iZzh / 2) {
            return zzm(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new zzer(objArr, i3, objArr2, i2, i4);
    }
}
