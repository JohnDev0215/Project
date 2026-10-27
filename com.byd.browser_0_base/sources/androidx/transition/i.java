package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.View;
import android.view.animation.BaseInterpolator;
import androidx.transition.Transition;
import defpackage.df3;

/* JADX INFO: compiled from: TranslationAnimationCreator.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static ObjectAnimator a(View view, df3 df3Var, int i, int i2, float f, float f2, float f3, float f4, BaseInterpolator baseInterpolator, Transition transition) {
        float f5;
        float f6;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (((int[]) df3Var.b.getTag(R$id.transition_position)) != null) {
            f5 = (r4[0] - i) + translationX;
            f6 = (r4[1] - i2) + translationY;
        } else {
            f5 = f;
            f6 = f2;
        }
        int iRound = Math.round(f5 - translationX) + i;
        int iRound2 = Math.round(f6 - translationY) + i2;
        view.setTranslationX(f5);
        view.setTranslationY(f6);
        if (f5 == f3 && f6 == f4) {
            return null;
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f5, f3), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f6, f4));
        a aVar = new a(view, df3Var.b, iRound, iRound2, translationX, translationY);
        transition.b(aVar);
        objectAnimatorOfPropertyValuesHolder.addListener(aVar);
        objectAnimatorOfPropertyValuesHolder.addPauseListener(aVar);
        objectAnimatorOfPropertyValuesHolder.setInterpolator(baseInterpolator);
        return objectAnimatorOfPropertyValuesHolder;
    }

    /* JADX INFO: compiled from: TranslationAnimationCreator.java */
    public static class a extends AnimatorListenerAdapter implements Transition.d {
        public final View e;
        public final View f;
        public final int g;
        public final int h;
        public int[] i;
        public float j;
        public float k;
        public final float l;
        public final float m;

        public a(View view, View view2, int i, int i2, float f, float f2) {
            this.f = view;
            this.e = view2;
            this.g = i - Math.round(view.getTranslationX());
            this.h = i2 - Math.round(view.getTranslationY());
            this.l = f;
            this.m = f2;
            int i3 = R$id.transition_position;
            int[] iArr = (int[]) view2.getTag(i3);
            this.i = iArr;
            if (iArr != null) {
                view2.setTag(i3, null);
            }
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            View view = this.f;
            view.setTranslationX(this.l);
            view.setTranslationY(this.m);
            transition.C(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            if (this.i == null) {
                this.i = new int[2];
            }
            int[] iArr = this.i;
            float f = this.g;
            View view = this.f;
            iArr[0] = Math.round(view.getTranslationX() + f);
            this.i[1] = Math.round(view.getTranslationY() + this.h);
            this.e.setTag(R$id.transition_position, this.i);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public final void onAnimationPause(Animator animator) {
            View view = this.f;
            this.j = view.getTranslationX();
            this.k = view.getTranslationY();
            view.setTranslationX(this.l);
            view.setTranslationY(this.m);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public final void onAnimationResume(Animator animator) {
            float f = this.j;
            View view = this.f;
            view.setTranslationX(f);
            view.setTranslationY(this.k);
        }

        @Override // androidx.transition.Transition.d
        public final void a() {
        }

        @Override // androidx.transition.Transition.d
        public final void c() {
        }

        @Override // androidx.transition.Transition.d
        public final void e() {
        }

        @Override // androidx.transition.Transition.d
        public final void b(Transition transition) {
        }
    }
}
