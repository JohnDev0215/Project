package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzql extends zzqp {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzql() {
        super(null);
    }

    /* synthetic */ zzql(zzqk zzqkVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.cast.zzqp
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzsn.zzf(obj, j);
        if (list instanceof zzqj) {
            objUnmodifiableList = ((zzqj) list).zzd();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzri) && (list instanceof zzqb)) {
                zzqb zzqbVar = (zzqb) list;
                if (zzqbVar.zzc()) {
                    zzqbVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzsn.zzs(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.gms.internal.cast.zzqp
    final void zzb(Object obj, Object obj2, long j) {
        List list;
        List list2;
        List list3 = (List) zzsn.zzf(obj2, j);
        int size = list3.size();
        List list4 = (List) zzsn.zzf(obj, j);
        if (list4.isEmpty()) {
            List zzqiVar = list4 instanceof zzqj ? new zzqi(size) : ((list4 instanceof zzri) && (list4 instanceof zzqb)) ? ((zzqb) list4).zzg(size) : new ArrayList(size);
            zzsn.zzs(obj, j, zzqiVar);
            list2 = zzqiVar;
        } else {
            if (zza.isAssignableFrom(list4.getClass())) {
                ArrayList arrayList = new ArrayList(list4.size() + size);
                arrayList.addAll(list4);
                zzsn.zzs(obj, j, arrayList);
                list = arrayList;
            } else if (list4 instanceof zzsi) {
                zzqi zzqiVar2 = new zzqi(list4.size() + size);
                zzqiVar2.addAll(zzqiVar2.size(), (zzsi) list4);
                zzsn.zzs(obj, j, zzqiVar2);
                list = zzqiVar2;
            } else {
                boolean z = list4 instanceof zzri;
                list2 = list4;
                if (z) {
                    boolean z2 = list4 instanceof zzqb;
                    list2 = list4;
                    if (z2) {
                        zzqb zzqbVar = (zzqb) list4;
                        list2 = list4;
                        if (!zzqbVar.zzc()) {
                            zzqb zzqbVarZzg = zzqbVar.zzg(list4.size() + size);
                            zzsn.zzs(obj, j, zzqbVarZzg);
                            list2 = zzqbVarZzg;
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
        zzsn.zzs(obj, j, list3);
    }
}
