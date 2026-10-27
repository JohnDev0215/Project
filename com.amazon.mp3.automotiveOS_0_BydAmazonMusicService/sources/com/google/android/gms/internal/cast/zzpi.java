package com.google.android.gms.internal.cast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzpi {
    static final zzpi zza = new zzpi(true);
    private static volatile boolean zzb = false;
    private static volatile zzpi zzc;
    private final Map zzd;

    zzpi() {
        this.zzd = new HashMap();
    }

    public static zzpi zza() {
        zzpi zzpiVar = zzc;
        if (zzpiVar == null) {
            synchronized (zzpi.class) {
                zzpiVar = zzc;
                if (zzpiVar == null) {
                    zzpiVar = zza;
                    zzc = zzpiVar;
                }
            }
        }
        return zzpiVar;
    }

    zzpi(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
