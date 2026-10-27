package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.transformation.ExpandableTransformationBehavior;

/* JADX INFO: compiled from: ExpandableTransformationBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class li0 extends AnimatorListenerAdapter {
    public final /* synthetic */ ExpandableTransformationBehavior e;

    public li0(ExpandableTransformationBehavior expandableTransformationBehavior) {
        this.e = expandableTransformationBehavior;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.b = null;
    }
}
