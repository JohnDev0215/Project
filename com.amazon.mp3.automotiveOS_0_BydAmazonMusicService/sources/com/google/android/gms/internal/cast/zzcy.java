package com.google.android.gms.internal.cast;

import android.animation.Animator;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzcy extends zzcw {
    protected final Animator zza;
    private int zzc;
    private final zzdc zzd = new zzcx(this);
    private final int zzb = -1;

    private zzcy(Animator animator, int i, @Nullable Runnable runnable) {
        this.zza = animator;
    }

    public static void zzd(Animator animator, int i, @Nullable Runnable runnable) {
        animator.addListener(new zzcy(animator, -1, null));
    }

    static /* bridge */ /* synthetic */ boolean zze(zzcy zzcyVar) {
        return zzcyVar.zzb != -1 && zzcyVar.zzc >= 0;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (zza(animator)) {
            return;
        }
        zzdf.zzb().zza(this.zzd);
    }
}
