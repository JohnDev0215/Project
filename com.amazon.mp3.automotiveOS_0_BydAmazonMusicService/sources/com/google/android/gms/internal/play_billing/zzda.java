package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzda {
    zzda() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzcz zzczVar = (zzcz) obj;
        if (zzczVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzczVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzcz zzczVarZzb = (zzcz) obj;
        zzcz zzczVar = (zzcz) obj2;
        if (!zzczVar.isEmpty()) {
            if (!zzczVarZzb.zze()) {
                zzczVarZzb = zzczVarZzb.zzb();
            }
            zzczVarZzb.zzd(zzczVar);
        }
        return zzczVarZzb;
    }
}
