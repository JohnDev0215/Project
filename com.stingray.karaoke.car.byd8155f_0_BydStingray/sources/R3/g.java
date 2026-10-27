package R3;

import N0.q;
import a4.C0920a;
import android.graphics.Color;
import android.graphics.Matrix;

/* JADX INFO: loaded from: classes.dex */
public final class g implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X3.b f7394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X3.b f7395b;
    public final f c;
    public final f d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f7396e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f7397f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f7398g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Matrix f7399h;

    public g(X3.b bVar, X3.b bVar2, q qVar) {
        this.f7395b = bVar;
        this.f7394a = bVar2;
        e eVarT0 = ((V3.a) qVar.f4399m).t0();
        this.c = (f) eVarT0;
        eVarT0.a(this);
        bVar2.d(eVarT0);
        f fVarT0 = ((V3.b) qVar.f4400n).t0();
        this.d = fVarT0;
        fVarT0.a(this);
        bVar2.d(fVarT0);
        f fVarT02 = ((V3.b) qVar.o).t0();
        this.f7396e = fVarT02;
        fVarT02.a(this);
        bVar2.d(fVarT02);
        f fVarT03 = ((V3.b) qVar.f4401p).t0();
        this.f7397f = fVarT03;
        fVarT03.a(this);
        bVar2.d(fVarT03);
        f fVarT04 = ((V3.b) qVar.l).t0();
        this.f7398g = fVarT04;
        fVarT04.a(this);
        bVar2.d(fVarT04);
    }

    public final C0920a a(Matrix matrix, int i7) {
        float fI = this.f7396e.i() * 0.017453292f;
        float fFloatValue = ((Float) this.f7397f.d()).floatValue();
        double d = fI;
        float fSin = ((float) Math.sin(d)) * fFloatValue;
        float fCos = ((float) Math.cos(d + 3.141592653589793d)) * fFloatValue;
        float fFloatValue2 = ((Float) this.f7398g.d()).floatValue();
        int iIntValue = ((Integer) this.c.d()).intValue();
        int iArgb = Color.argb(Math.round((((Float) this.d.d()).floatValue() * i7) / 255.0f), Color.red(iIntValue), Color.green(iIntValue), Color.blue(iIntValue));
        C0920a c0920a = new C0920a();
        c0920a.f11090a = fFloatValue2 * 0.33f;
        c0920a.f11091b = fSin;
        c0920a.c = fCos;
        c0920a.d = iArgb;
        c0920a.f11092e = null;
        c0920a.c(matrix);
        if (this.f7399h == null) {
            this.f7399h = new Matrix();
        }
        this.f7394a.f10015w.d().invert(this.f7399h);
        c0920a.c(this.f7399h);
        return c0920a;
    }

    @Override // R3.a
    public final void b() {
        this.f7395b.b();
    }
}
