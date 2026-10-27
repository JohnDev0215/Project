package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: ScrimCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ef2 {
    public final df2 a;
    public final if2 b;
    public kf2 c;
    public j d;

    /* JADX INFO: compiled from: ScrimCoordinator.java */
    public interface b {
    }

    public ef2(Context context, a aVar, ViewGroup viewGroup, int i) {
        this.b = new if2(new ve3(12, this), aVar);
        this.a = new df2(this, context, viewGroup, i);
    }

    public final void a(PropertyModel propertyModel) {
        if2 if2Var = this.b;
        if (if2Var.f != null) {
            if2Var.a(300, false);
        }
        j jVar = this.d;
        if (jVar != null) {
            jVar.c();
        }
        kf2 kf2Var = (kf2) this.a.get();
        this.c = kf2Var;
        this.d = j.b(propertyModel, kf2Var, new cg(28));
        if2Var.f = propertyModel;
        if2Var.i = false;
        a aVar = if2Var.b;
        if (aVar != null) {
            ArrayList arrayListB = propertyModel.b();
            PropertyModel.p pVar = jf2.h;
            if (arrayListB.contains(pVar)) {
                aVar.c(propertyModel.h(pVar));
            }
        }
        if2Var.b(0.0f);
        if (if2Var.c == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            if2Var.c = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(300);
            if2Var.c.setInterpolator(pc.e);
            if2Var.c.addListener(new ff2(if2Var));
            if2Var.c.addUpdateListener(new le0(3, if2Var));
        }
        ArrayList arrayListB2 = propertyModel.b();
        PropertyModel.r<GestureDetector> rVar = jf2.j;
        if (arrayListB2.contains(rVar)) {
            if2Var.h = propertyModel.j(rVar) != null;
        }
        if2Var.c.setFloatValues(if2Var.f.g(jf2.g), 1.0f);
        ValueAnimator valueAnimator = if2Var.c;
        Animator animator = if2Var.e;
        if (animator == valueAnimator && animator.isRunning()) {
            return;
        }
        Animator animator2 = if2Var.e;
        if (animator2 != null) {
            animator2.cancel();
        }
        if2Var.e = valueAnimator;
        valueAnimator.start();
    }

    /* JADX INFO: compiled from: ScrimCoordinator.java */
    public interface a {
        void a(float f);

        void b(float f);

        default void c(int i) {
        }
    }
}
