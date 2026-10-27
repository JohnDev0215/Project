package com.google.android.gms.internal.cast;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
class zzdy extends zzdz {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    zzdy(int i) {
    }

    private final void zzc(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i) {
            this.zza = Arrays.copyOf(objArr, zzdz.zzb(length, i));
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
    }

    public final zzdy zza(Object obj) {
        obj.getClass();
        zzc(this.zzb + 1);
        Object[] objArr = this.zza;
        int i = this.zzb;
        this.zzb = i + 1;
        objArr[i] = obj;
        return this;
    }
}
