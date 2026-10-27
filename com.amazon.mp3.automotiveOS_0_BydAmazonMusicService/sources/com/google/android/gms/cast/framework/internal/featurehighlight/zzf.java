package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzf extends AnimatorListenerAdapter {
    final /* synthetic */ Runnable zza;
    final /* synthetic */ zzh zzb;

    zzf(zzh zzhVar, Runnable runnable) {
        this.zzb = zzhVar;
        this.zza = runnable;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.zzb.setVisibility(8);
        this.zzb.zzg = null;
        this.zza.run();
    }
}
