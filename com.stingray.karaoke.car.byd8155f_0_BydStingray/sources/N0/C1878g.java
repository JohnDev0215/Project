package n0;

import android.graphics.Paint;
import android.graphics.Shader;

/* JADX INFO: renamed from: n0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1878g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f19432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19433b = 3;
    public Shader c;
    public C1883l d;

    public C1878g(Paint paint) {
        this.f19432a = paint;
    }

    public final int a() {
        Paint.Cap strokeCap = this.f19432a.getStrokeCap();
        int i7 = strokeCap == null ? -1 : AbstractC1879h.f19434a[strokeCap.ordinal()];
        if (i7 == 1) {
            return 0;
        }
        if (i7 != 2) {
            return i7 != 3 ? 0 : 2;
        }
        return 1;
    }

    public final int b() {
        Paint.Join strokeJoin = this.f19432a.getStrokeJoin();
        int i7 = strokeJoin == null ? -1 : AbstractC1879h.f19435b[strokeJoin.ordinal()];
        if (i7 == 1) {
            return 0;
        }
        if (i7 != 2) {
            return i7 != 3 ? 0 : 1;
        }
        return 2;
    }

    public final void c(float f10) {
        this.f19432a.setAlpha((int) Math.rint(f10 * 255.0f));
    }

    public final void d(int i7) {
        if (AbstractC1861K.p(this.f19433b, i7)) {
            return;
        }
        this.f19433b = i7;
        C1871V.f19424a.a(this.f19432a, i7);
    }

    public final void e(long j3) {
        this.f19432a.setColor(AbstractC1861K.E(j3));
    }

    public final void f(C1883l c1883l) {
        this.d = c1883l;
        this.f19432a.setColorFilter(c1883l != null ? c1883l.f19439a : null);
    }

    public final void g(int i7) {
        this.f19432a.setFilterBitmap(!AbstractC1861K.r(i7, 0));
    }

    public final void h(Shader shader) {
        this.c = shader;
        this.f19432a.setShader(shader);
    }

    public final void i(int i7) {
        this.f19432a.setStrokeCap(AbstractC1861K.s(i7, 2) ? Paint.Cap.SQUARE : AbstractC1861K.s(i7, 1) ? Paint.Cap.ROUND : AbstractC1861K.s(i7, 0) ? Paint.Cap.BUTT : Paint.Cap.BUTT);
    }

    public final void j(int i7) {
        this.f19432a.setStrokeJoin(AbstractC1861K.t(i7, 0) ? Paint.Join.MITER : AbstractC1861K.t(i7, 2) ? Paint.Join.BEVEL : AbstractC1861K.t(i7, 1) ? Paint.Join.ROUND : Paint.Join.MITER);
    }

    public final void k(float f10) {
        this.f19432a.setStrokeWidth(f10);
    }

    public final void l(int i7) {
        this.f19432a.setStyle(i7 == 1 ? Paint.Style.STROKE : Paint.Style.FILL);
    }
}
