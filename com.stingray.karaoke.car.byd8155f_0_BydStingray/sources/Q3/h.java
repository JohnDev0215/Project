package Q3;

import a4.AbstractC0925f;
import a4.C0920a;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import java.util.ArrayList;
import java.util.List;
import u.C2472l;

/* JADX INFO: loaded from: classes.dex */
public final class h implements e, R3.a, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f6158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2472l f6159b = new C2472l((Object) null);
    public final C2472l c = new C2472l((Object) null);
    public final Path d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final P3.a f6160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f6161f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f6162g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f6163h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final R3.h f6164i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final R3.f f6165j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final R3.h f6166k;
    public final R3.h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final O3.j f6167m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f6168n;
    public final R3.f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f6169p;

    public h(O3.j jVar, O3.a aVar, X3.b bVar, W3.d dVar) {
        Path path = new Path();
        this.d = path;
        this.f6160e = new P3.a(1, 0);
        this.f6161f = new RectF();
        this.f6162g = new ArrayList();
        this.f6169p = 0.0f;
        dVar.getClass();
        this.f6158a = dVar.f9766g;
        this.f6167m = jVar;
        this.f6163h = dVar.f9762a;
        path.setFillType(dVar.f9763b);
        this.f6168n = (int) (aVar.b() / 32.0f);
        R3.e eVarT0 = dVar.c.t0();
        this.f6164i = (R3.h) eVarT0;
        eVarT0.a(this);
        bVar.d(eVarT0);
        R3.e eVarT02 = dVar.d.t0();
        this.f6165j = (R3.f) eVarT02;
        eVarT02.a(this);
        bVar.d(eVarT02);
        R3.e eVarT03 = dVar.f9764e.t0();
        this.f6166k = (R3.h) eVarT03;
        eVarT03.a(this);
        bVar.d(eVarT03);
        R3.e eVarT04 = dVar.f9765f.t0();
        this.l = (R3.h) eVarT04;
        eVarT04.a(this);
        bVar.d(eVarT04);
        if (bVar.i() != null) {
            R3.f fVarT0 = ((V3.b) bVar.i().l).t0();
            this.o = fVarT0;
            fVarT0.a(this);
            bVar.d(fVarT0);
        }
    }

    @Override // Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        Path path = this.d;
        path.reset();
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f6162g;
            if (i7 >= arrayList.size()) {
                path.computeBounds(rectF, false);
                rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
                return;
            } else {
                path.addPath(((l) arrayList.get(i7)).e(), matrix);
                i7++;
            }
        }
    }

    @Override // R3.a
    public final void b() {
        this.f6167m.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        for (int i7 = 0; i7 < list2.size(); i7++) {
            c cVar = (c) list2.get(i7);
            if (cVar instanceof l) {
                this.f6162g.add((l) cVar);
            }
        }
    }

    public final int d() {
        float f10 = this.f6166k.d;
        float f11 = this.f6168n;
        int iRound = Math.round(f10 * f11);
        int iRound2 = Math.round(this.l.d * f11);
        int iRound3 = Math.round(this.f6164i.d * f11);
        int i7 = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i7 = i7 * 31 * iRound2;
        }
        return iRound3 != 0 ? i7 * 31 * iRound3 : i7;
    }

    @Override // Q3.e
    public final void f(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        Path path;
        Shader shader;
        if (this.f6158a) {
            return;
        }
        Path path2 = this.d;
        path2.reset();
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f6162g;
            if (i10 >= arrayList.size()) {
                break;
            }
            path2.addPath(((l) arrayList.get(i10)).e(), matrix);
            i10++;
        }
        path2.computeBounds(this.f6161f, false);
        int i11 = this.f6163h;
        R3.h hVar = this.f6164i;
        R3.h hVar2 = this.l;
        R3.h hVar3 = this.f6166k;
        if (i11 == 1) {
            long jD = d();
            C2472l c2472l = this.f6159b;
            shader = (LinearGradient) c2472l.b(jD);
            if (shader == null) {
                PointF pointF = (PointF) hVar3.d();
                PointF pointF2 = (PointF) hVar2.d();
                W3.c cVar = (W3.c) hVar.d();
                LinearGradient linearGradient = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, cVar.f9761b, cVar.f9760a, Shader.TileMode.CLAMP);
                c2472l.e(jD, linearGradient);
                shader = linearGradient;
            }
            path = path2;
        } else {
            long jD2 = d();
            C2472l c2472l2 = this.c;
            RadialGradient radialGradient = (RadialGradient) c2472l2.b(jD2);
            if (radialGradient != null) {
                path = path2;
                shader = radialGradient;
            } else {
                PointF pointF3 = (PointF) hVar3.d();
                PointF pointF4 = (PointF) hVar2.d();
                W3.c cVar2 = (W3.c) hVar.d();
                int[] iArr = cVar2.f9761b;
                float f10 = pointF3.x;
                float f11 = pointF3.y;
                path = path2;
                float fHypot = (float) Math.hypot(pointF4.x - f10, pointF4.y - f11);
                if (fHypot <= 0.0f) {
                    fHypot = 0.001f;
                }
                RadialGradient radialGradient2 = new RadialGradient(f10, f11, fHypot, iArr, cVar2.f9760a, Shader.TileMode.CLAMP);
                c2472l2.e(jD2, radialGradient2);
                shader = radialGradient2;
            }
        }
        shader.setLocalMatrix(matrix);
        P3.a aVar = this.f6160e;
        aVar.setShader(shader);
        R3.f fVar = this.o;
        if (fVar != null) {
            float fFloatValue = ((Float) fVar.d()).floatValue();
            if (fFloatValue == 0.0f) {
                aVar.setMaskFilter(null);
            } else if (fFloatValue != this.f6169p) {
                aVar.setMaskFilter(new BlurMaskFilter(fFloatValue, BlurMaskFilter.Blur.NORMAL));
            }
            this.f6169p = fFloatValue;
        }
        float fIntValue = ((Integer) this.f6165j.d()).intValue() / 100.0f;
        aVar.setAlpha(AbstractC0925f.c((int) (i7 * fIntValue)));
        if (c0920a != null) {
            c0920a.a((int) (fIntValue * 255.0f), aVar);
        }
        canvas.drawPath(path, aVar);
    }
}
