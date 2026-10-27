package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ShrinkTransition.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bm2 extends Visibility {
    public static ObjectAnimator b(View view, float f, float f2) {
        Path path = new Path();
        path.moveTo(f, f);
        path.lineTo(f2, f2);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "ScaleX", "ScaleY", path);
        if (f2 == 0.0f) {
            objectAnimatorOfFloat.addListener(new am2(view));
        }
        return objectAnimatorOfFloat;
    }

    @Override // android.transition.Visibility
    public final Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(view, view.getScaleX(), 1.0f);
    }

    @Override // android.transition.Visibility
    public final Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(view, view.getScaleX(), 0.0f);
    }
}
