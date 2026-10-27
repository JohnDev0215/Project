package com.google.android.gms.internal.cast;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzop extends zzor {
    final /* synthetic */ zzoy zza;
    private int zzb = 0;
    private final int zzc;

    zzop(zzoy zzoyVar) {
        this.zza = zzoyVar;
        this.zzc = zzoyVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzot
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
