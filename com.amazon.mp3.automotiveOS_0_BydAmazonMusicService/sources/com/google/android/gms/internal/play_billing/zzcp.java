package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcp extends zzct {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzcp() {
        super(null);
    }

    /* synthetic */ zzcp(zzco zzcoVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzeq.zzf(obj, j);
        if (list instanceof zzcn) {
            objUnmodifiableList = ((zzcn) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzdm) && (list instanceof zzcf)) {
                zzcf zzcfVar = (zzcf) list;
                if (zzcfVar.zzc()) {
                    zzcfVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzeq.zzs(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    final void zzb(Object obj, Object obj2, long j) {
        List list;
        List list2;
        List list3 = (List) zzeq.zzf(obj2, j);
        int size = list3.size();
        List list4 = (List) zzeq.zzf(obj, j);
        if (list4.isEmpty()) {
            List zzcmVar = list4 instanceof zzcn ? new zzcm(size) : ((list4 instanceof zzdm) && (list4 instanceof zzcf)) ? ((zzcf) list4).zzd(size) : new ArrayList(size);
            zzeq.zzs(obj, j, zzcmVar);
            list2 = zzcmVar;
        } else {
            if (zza.isAssignableFrom(list4.getClass())) {
                ArrayList arrayList = new ArrayList(list4.size() + size);
                arrayList.addAll(list4);
                zzeq.zzs(obj, j, arrayList);
                list = arrayList;
            } else if (list4 instanceof zzel) {
                zzcm zzcmVar2 = new zzcm(list4.size() + size);
                zzcmVar2.addAll(zzcmVar2.size(), (zzel) list4);
                zzeq.zzs(obj, j, zzcmVar2);
                list = zzcmVar2;
            } else {
                boolean z = list4 instanceof zzdm;
                list2 = list4;
                if (z) {
                    boolean z2 = list4 instanceof zzcf;
                    list2 = list4;
                    if (z2) {
                        zzcf zzcfVar = (zzcf) list4;
                        list2 = list4;
                        if (!zzcfVar.zzc()) {
                            zzcf zzcfVarZzd = zzcfVar.zzd(list4.size() + size);
                            zzeq.zzs(obj, j, zzcfVarZzd);
                            list2 = zzcfVarZzd;
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
        zzeq.zzs(obj, j, list3);
    }
}
