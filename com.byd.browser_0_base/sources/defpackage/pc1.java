package defpackage;

import android.view.animation.Animation;
import defpackage.qc1;

/* JADX INFO: compiled from: MaterialProgressDrawable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pc1 implements Animation.AnimationListener {
    public final /* synthetic */ qc1.b a;
    public final /* synthetic */ qc1 b;

    public pc1(qc1 qc1Var, qc1.b bVar) {
        this.b = qc1Var;
        this.a = bVar;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
        qc1.b bVar = this.a;
        bVar.l = bVar.e;
        bVar.m = bVar.f;
        bVar.n = bVar.g;
        bVar.b((bVar.k + 1) % bVar.j.length);
        bVar.e = bVar.f;
        bVar.a();
        qc1 qc1Var = this.b;
        if (!qc1Var.n) {
            qc1Var.k = (qc1Var.k + 1.0f) % 5.0f;
            return;
        }
        qc1Var.n = false;
        animation.setDuration(1332L);
        if (bVar.o) {
            bVar.o = false;
            bVar.a();
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        this.b.k = 0.0f;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
    }
}
