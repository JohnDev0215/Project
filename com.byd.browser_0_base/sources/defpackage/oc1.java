package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import defpackage.qc1;

/* JADX INFO: compiled from: MaterialProgressDrawable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class oc1 extends Animation {
    public final /* synthetic */ qc1.b e;
    public final /* synthetic */ qc1 f;

    public oc1(qc1 qc1Var, qc1.b bVar) {
        this.f = qc1Var;
        this.e = bVar;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        qc1 qc1Var = this.f;
        boolean z = qc1Var.n;
        qc1.b bVar = this.e;
        if (z) {
            qc1.c(f, bVar);
            float fFloor = (float) (Math.floor(bVar.n / 0.8f) + 1.0d);
            float radians = (float) Math.toRadians(((double) bVar.h) / (bVar.r * 6.283185307179586d));
            float f2 = bVar.l;
            bVar.e = (((bVar.m - radians) - f2) * f) + f2;
            bVar.a();
            bVar.f = bVar.m;
            bVar.a();
            float f3 = bVar.n;
            bVar.g = C0173a.f(fFloor, f3, f, f3);
            bVar.a();
            return;
        }
        float radians2 = (float) Math.toRadians(((double) bVar.h) / (bVar.r * 6.283185307179586d));
        float f4 = bVar.m;
        float f5 = bVar.l;
        float f6 = bVar.n;
        qc1.c(f, bVar);
        if (f <= 0.5f) {
            bVar.e = (qc1.p.getInterpolation(f / 0.5f) * (0.8f - radians2)) + f5;
            bVar.a();
        }
        if (f > 0.5f) {
            bVar.f = (qc1.p.getInterpolation((f - 0.5f) / 0.5f) * (0.8f - radians2)) + f4;
            bVar.a();
        }
        bVar.g = (0.25f * f) + f6;
        bVar.a();
        qc1Var.g = ((qc1Var.k / 5.0f) * 1080.0f) + (f * 216.0f);
        qc1Var.invalidateSelf();
    }
}
