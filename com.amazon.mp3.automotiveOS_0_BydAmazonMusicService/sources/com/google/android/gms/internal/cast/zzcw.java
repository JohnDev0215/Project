package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.collection.SimpleArrayMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class zzcw extends AnimatorListenerAdapter {
    private final SimpleArrayMap zza = new SimpleArrayMap();

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.zza.put(animator, Boolean.TRUE);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.zza.put(animator, Boolean.FALSE);
    }

    protected final boolean zza(Animator animator) {
        return this.zza.containsKey(animator) && ((Boolean) this.zza.get(animator)).booleanValue();
    }
}
