package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzdf {
    private static final ThreadLocal zza = new zzcz();

    public static zzdf zzb() {
        return (zzdf) zza.get();
    }

    public abstract void zza(zzdc zzdcVar);
}
