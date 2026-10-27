package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzz extends AnimatorListenerAdapter {
    final /* synthetic */ zzaa zza;

    zzz(zzaa zzaaVar) {
        this.zza = zzaaVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        zzad.zza(this.zza.zza);
    }
}
