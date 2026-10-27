package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzd extends AnimatorListenerAdapter {
    final /* synthetic */ zzh zza;

    zzd(zzh zzhVar) {
        this.zza = zzhVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        zzh zzhVar = this.zza;
        zzhVar.zzg = zzh.zzb(zzhVar);
        this.zza.zzg.start();
    }
}
