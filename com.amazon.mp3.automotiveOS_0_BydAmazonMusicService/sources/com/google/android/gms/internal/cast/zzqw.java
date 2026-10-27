package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzqw {
    zzqw() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzqv zzqvVar = (zzqv) obj;
        if (zzqvVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzqvVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }
}
