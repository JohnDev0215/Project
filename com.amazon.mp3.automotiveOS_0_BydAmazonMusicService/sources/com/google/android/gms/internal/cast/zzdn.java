package com.google.android.gms.internal.cast;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzdn implements Serializable {
    zzdn() {
    }

    public static zzdn zzb(@CheckForNull Object obj) {
        return obj == null ? zzdj.zza : new zzds(obj);
    }

    @CheckForNull
    public abstract Object zza();
}
