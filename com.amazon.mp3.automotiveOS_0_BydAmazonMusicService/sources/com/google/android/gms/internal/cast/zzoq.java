package com.google.android.gms.internal.cast;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzoq implements Comparator {
    zzoq() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzoy zzoyVar = (zzoy) obj;
        zzoy zzoyVar2 = (zzoy) obj2;
        zzop zzopVar = new zzop(zzoyVar);
        zzop zzopVar2 = new zzop(zzoyVar2);
        while (zzopVar.hasNext() && zzopVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzopVar.zza() & 255).compareTo(Integer.valueOf(zzopVar2.zza() & 255));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzoyVar.zzd()).compareTo(Integer.valueOf(zzoyVar2.zzd()));
    }
}
