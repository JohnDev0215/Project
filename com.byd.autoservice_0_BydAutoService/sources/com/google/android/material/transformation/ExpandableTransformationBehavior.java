package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AnimatorSet f2596b;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ExpandableTransformationBehavior.this.f2596b = null;
        }
    }

    public ExpandableTransformationBehavior() {
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior
    public boolean H(View view, View view2, boolean z3, boolean z4) {
        AnimatorSet animatorSet = this.f2596b;
        boolean z5 = animatorSet != null;
        if (z5) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSetJ = J(view, view2, z3, z5);
        this.f2596b = animatorSetJ;
        animatorSetJ.addListener(new a());
        this.f2596b.start();
        if (!z4) {
            this.f2596b.end();
        }
        return true;
    }

    public abstract AnimatorSet J(View view, View view2, boolean z3, boolean z4);

    public ExpandableTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
