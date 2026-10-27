package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzec extends zzdv {
    private final zzee zza;

    zzec(zzee zzeeVar, int i) {
        super(zzeeVar.size(), i);
        this.zza = zzeeVar;
    }

    @Override // com.google.android.gms.internal.cast.zzdv
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
