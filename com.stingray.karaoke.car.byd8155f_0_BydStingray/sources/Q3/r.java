package Q3;

import a4.C0920a;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class r extends b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f6216p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final R3.f f6217q;

    /* JADX WARN: Illegal instructions before constructor call */
    public r(O3.j jVar, X3.b bVar, W3.o oVar) {
        int iC = AbstractC2641j.c(oVar.f9804f);
        Paint.Cap cap = iC != 0 ? iC != 1 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        int iC2 = AbstractC2641j.c(oVar.f9805g);
        super(jVar, bVar, cap, iC2 != 0 ? iC2 != 1 ? iC2 != 2 ? null : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER, oVar.f9806h, oVar.d, oVar.f9803e, oVar.f9802b, oVar.f9801a);
        this.f6216p = oVar.f9807i;
        R3.e eVarT0 = oVar.c.t0();
        this.f6217q = (R3.f) eVarT0;
        eVarT0.a(this);
        bVar.d(eVarT0);
    }

    @Override // Q3.b, Q3.e
    public final void f(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        if (this.f6216p) {
            return;
        }
        R3.f fVar = this.f6217q;
        this.f6131i.setColor(fVar.k(fVar.c.t(), fVar.b()));
        super.f(canvas, matrix, i7, c0920a);
    }
}
