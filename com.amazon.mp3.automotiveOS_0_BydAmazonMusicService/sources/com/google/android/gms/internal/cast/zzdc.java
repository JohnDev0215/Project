package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.view.Choreographer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzdc {
    private Runnable zza;
    private Choreographer.FrameCallback zzb;

    public abstract void zza(long j);

    @TargetApi(16)
    final Choreographer.FrameCallback zzb() {
        if (this.zzb == null) {
            this.zzb = new Choreographer.FrameCallback() { // from class: com.google.android.gms.internal.cast.zzda
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j) {
                    this.zza.zza(j);
                }
            };
        }
        return this.zzb;
    }

    final Runnable zzc() {
        if (this.zza == null) {
            this.zza = new Runnable() { // from class: com.google.android.gms.internal.cast.zzdb
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zza(System.nanoTime());
                }
            };
        }
        return this.zza;
    }
}
