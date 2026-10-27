package com.google.android.gms.internal.wearable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcj extends zzcl {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzcj() {
        super(null);
    }

    /* synthetic */ zzcj(zzci zzciVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.wearable.zzcl
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzeg.zzn(obj, j);
        if (list instanceof zzch) {
            objUnmodifiableList = ((zzch) list).zzi();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzde) && (list instanceof zzbz)) {
                zzbz zzbzVar = (zzbz) list;
                if (zzbzVar.zza()) {
                    zzbzVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzeg.zzo(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.gms.internal.wearable.zzcl
    final <E> void zzb(Object obj, Object obj2, long j) {
        List list;
        List list2;
        List list3 = (List) zzeg.zzn(obj2, j);
        int size = list3.size();
        List list4 = (List) zzeg.zzn(obj, j);
        if (list4.isEmpty()) {
            List zzcgVar = list4 instanceof zzch ? new zzcg(size) : ((list4 instanceof zzde) && (list4 instanceof zzbz)) ? ((zzbz) list4).zze(size) : new ArrayList(size);
            zzeg.zzo(obj, j, zzcgVar);
            list2 = zzcgVar;
        } else {
            if (zza.isAssignableFrom(list4.getClass())) {
                ArrayList arrayList = new ArrayList(list4.size() + size);
                arrayList.addAll(list4);
                zzeg.zzo(obj, j, arrayList);
                list = arrayList;
            } else if (list4 instanceof zzeb) {
                zzcg zzcgVar2 = new zzcg(list4.size() + size);
                zzcgVar2.addAll(zzcgVar2.size(), (zzeb) list4);
                zzeg.zzo(obj, j, zzcgVar2);
                list = zzcgVar2;
            } else {
                boolean z = list4 instanceof zzde;
                list2 = list4;
                if (z) {
                    boolean z2 = list4 instanceof zzbz;
                    list2 = list4;
                    if (z2) {
                        zzbz zzbzVar = (zzbz) list4;
                        list2 = list4;
                        if (!zzbzVar.zza()) {
                            zzbz<E> zzbzVarZze = zzbzVar.zze(list4.size() + size);
                            zzeg.zzo(obj, j, zzbzVarZze);
                            list2 = zzbzVarZze;
                        }
                    }
                }
            }
            list2 = list;
        }
        int size2 = list2.size();
        int size3 = list3.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list3);
        }
        if (size2 > 0) {
            list3 = list2;
        }
        zzeg.zzo(obj, j, list3);
    }
}
