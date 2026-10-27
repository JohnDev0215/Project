package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import defpackage.ef2;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ScrimMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class if2 implements ef2.b {
    public final Runnable a;
    public final ef2.a b;
    public ValueAnimator c;
    public ValueAnimator d;
    public Animator e;
    public PropertyModel f;
    public boolean g;
    public boolean h;
    public boolean i;

    public if2(ve3 ve3Var, ef2.a aVar) {
        this.a = ve3Var;
        this.b = aVar;
    }

    public final void a(int i, boolean z) {
        if (this.i) {
            Animator animator = this.e;
            if (animator == null || z) {
                return;
            }
            animator.end();
            return;
        }
        if (this.d == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            this.d = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(i);
            this.d.setInterpolator(pc.d);
            this.d.addListener(new gf2(this));
            this.d.addUpdateListener(new jw1(3, this));
            this.d.addListener(new hf2(this));
        }
        this.i = true;
        this.d.setFloatValues(this.f.g(jf2.g), 0.0f);
        ValueAnimator valueAnimator = this.d;
        Animator animator2 = this.e;
        if (animator2 != valueAnimator || !animator2.isRunning()) {
            Animator animator3 = this.e;
            if (animator3 != null) {
                animator3.cancel();
            }
            this.e = valueAnimator;
            valueAnimator.start();
        }
        if (z) {
            return;
        }
        this.d.end();
    }

    public final void b(float f) {
        PropertyModel propertyModel = this.f;
        if (propertyModel == null) {
            return;
        }
        PropertyModel.o oVar = jf2.g;
        if (a8.h(f, propertyModel.g(oVar))) {
            return;
        }
        this.f.m(oVar, f);
        boolean zK = this.f.k(jf2.b);
        ef2.a aVar = this.b;
        if (zK && aVar != null) {
            aVar.a(f);
        }
        ArrayList arrayListB = this.f.b();
        PropertyModel.n nVar = jf2.k;
        if (arrayListB.contains(nVar) && this.f.k(nVar) && aVar != null) {
            aVar.b(f);
        }
        boolean z = f > 0.0f;
        PropertyModel propertyModel2 = this.f;
        PropertyModel.l<Callback<Boolean>> lVar = jf2.e;
        if (propertyModel2.j(lVar) != null && this.g != z) {
            ((Callback) this.f.j(lVar)).onResult(Boolean.valueOf(z));
        }
        this.g = z;
        if (!this.i || z || this.f == null) {
            return;
        }
        this.f = null;
        this.a.run();
    }
}
