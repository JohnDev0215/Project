package com.google.android.gms.internal.cast;

import android.os.Bundle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzn {
    public static Map zza(Bundle bundle, String str) {
        Map map = (Map) bundle.getSerializable(str);
        if (map == null) {
            return zzeh.zzd();
        }
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                map2.put((Integer) entry.getKey(), (Integer) entry.getValue());
            }
        }
        return Collections.unmodifiableMap(map2);
    }
}
