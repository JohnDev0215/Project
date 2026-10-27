package Q3;

import a4.AbstractC0925f;
import a4.C0920a;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class o implements e, l, j, R3.a, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f6203a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f6204b = new Path();
    public final O3.j c;
    public final X3.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f6205e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final R3.f f6206f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final R3.f f6207g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final R3.n f6208h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public d f6209i;

    public o(O3.j jVar, X3.b bVar, W3.i iVar) {
        this.c = jVar;
        this.d = bVar;
        iVar.getClass();
        this.f6205e = iVar.c;
        R3.f fVarT0 = iVar.f9789b.t0();
        this.f6206f = fVarT0;
        bVar.d(fVarT0);
        fVarT0.a(this);
        R3.f fVarT02 = ((V3.b) iVar.d).t0();
        this.f6207g = fVarT02;
        bVar.d(fVarT02);
        fVarT02.a(this);
        V3.d dVar = (V3.d) iVar.f9790e;
        dVar.getClass();
        R3.n nVar = new R3.n(dVar);
        this.f6208h = nVar;
        nVar.a(bVar);
        nVar.b(this);
    }

    @Override // Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        this.f6209i.a(rectF, matrix, z6);
    }

    @Override // R3.a
    public final void b() {
        this.c.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        this.f6209i.c(list, list2);
    }

    @Override // Q3.j
    public final void d(ListIterator listIterator) {
        if (this.f6209i != null) {
            return;
        }
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        ArrayList arrayList = new ArrayList();
        while (listIterator.hasPrevious()) {
            arrayList.add((c) listIterator.previous());
            listIterator.remove();
        }
        Collections.reverse(arrayList);
        this.f6209i = new d(this.c, this.d, this.f6205e, arrayList, null);
    }

    @Override // Q3.l
    public final Path e() {
        Path pathE = this.f6209i.e();
        Path path = this.f6204b;
        path.reset();
        float fFloatValue = ((Float) this.f6206f.d()).floatValue();
        float fFloatValue2 = ((Float) this.f6207g.d()).floatValue();
        for (int i7 = ((int) fFloatValue) - 1; i7 >= 0; i7--) {
            Matrix matrix = this.f6203a;
            matrix.set(this.f6208h.e(i7 + fFloatValue2));
            path.addPath(pathE, matrix);
        }
        return path;
    }

    @Override // Q3.e
    public final void f(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        float fFloatValue = ((Float) this.f6206f.d()).floatValue();
        float fFloatValue2 = ((Float) this.f6207g.d()).floatValue();
        R3.n nVar = this.f6208h;
        float fFloatValue3 = ((Float) nVar.f7424m.d()).floatValue() / 100.0f;
        float fFloatValue4 = ((Float) nVar.f7425n.d()).floatValue() / 100.0f;
        for (int i10 = ((int) fFloatValue) - 1; i10 >= 0; i10--) {
            Matrix matrix2 = this.f6203a;
            matrix2.set(matrix);
            float f10 = i10;
            matrix2.preConcat(nVar.e(f10 + fFloatValue2));
            this.f6209i.f(canvas, matrix2, (int) (AbstractC0925f.e(fFloatValue3, fFloatValue4, f10 / fFloatValue) * i7), c0920a);
        }
    }
}
