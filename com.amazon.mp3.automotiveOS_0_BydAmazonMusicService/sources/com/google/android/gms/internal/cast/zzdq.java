package com.google.android.gms.internal.cast;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdq {
    private static final Logger zza = Logger.getLogger(zzdq.class.getName());
    private static final zzdp zzb = new zzdp(null);

    private zzdq() {
    }

    @CheckForNull
    static String zza(@CheckForNull String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return str;
    }
}
