package R3;

import A7.D;
import android.graphics.Matrix;
import android.graphics.PointF;
import b4.C0996b;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f7415a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f7416b;
    public final Matrix c;
    public final Matrix d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f7417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f7418f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f7419g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h f7420h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f7421i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final f f7422j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f f7423k;
    public final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f7424m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f f7425n;
    public final boolean o;

    public n(V3.d dVar) {
        D d = dVar.f9453a;
        this.f7418f = (i) (d == null ? null : d.t0());
        V3.e eVar = dVar.f9454b;
        this.f7419g = eVar == null ? null : eVar.t0();
        V3.a aVar = dVar.c;
        this.f7420h = (h) (aVar == null ? null : aVar.t0());
        V3.b bVar = dVar.d;
        this.f7421i = bVar == null ? null : bVar.t0();
        V3.b bVar2 = dVar.f9456f;
        f fVarT0 = bVar2 == null ? null : bVar2.t0();
        this.f7423k = fVarT0;
        this.o = dVar.f9460j;
        if (fVarT0 != null) {
            this.f7416b = new Matrix();
            this.c = new Matrix();
            this.d = new Matrix();
            this.f7417e = new float[9];
        } else {
            this.f7416b = null;
            this.c = null;
            this.d = null;
            this.f7417e = null;
        }
        V3.b bVar3 = dVar.f9457g;
        this.l = bVar3 == null ? null : bVar3.t0();
        V3.a aVar2 = dVar.f9455e;
        if (aVar2 != null) {
            this.f7422j = (f) aVar2.t0();
        }
        V3.b bVar4 = dVar.f9458h;
        if (bVar4 != null) {
            this.f7424m = bVar4.t0();
        } else {
            this.f7424m = null;
        }
        V3.b bVar5 = dVar.f9459i;
        if (bVar5 != null) {
            this.f7425n = bVar5.t0();
        } else {
            this.f7425n = null;
        }
    }

    public final void a(X3.b bVar) {
        bVar.d(this.f7422j);
        bVar.d(this.f7424m);
        bVar.d(this.f7425n);
        bVar.d(this.f7418f);
        bVar.d(this.f7419g);
        bVar.d(this.f7420h);
        bVar.d(this.f7421i);
        bVar.d(this.f7423k);
        bVar.d(this.l);
    }

    public final void b(a aVar) {
        f fVar = this.f7422j;
        if (fVar != null) {
            fVar.a(aVar);
        }
        f fVar2 = this.f7424m;
        if (fVar2 != null) {
            fVar2.a(aVar);
        }
        f fVar3 = this.f7425n;
        if (fVar3 != null) {
            fVar3.a(aVar);
        }
        i iVar = this.f7418f;
        if (iVar != null) {
            iVar.a(aVar);
        }
        e eVar = this.f7419g;
        if (eVar != null) {
            eVar.a(aVar);
        }
        h hVar = this.f7420h;
        if (hVar != null) {
            hVar.a(aVar);
        }
        f fVar4 = this.f7421i;
        if (fVar4 != null) {
            fVar4.a(aVar);
        }
        f fVar5 = this.f7423k;
        if (fVar5 != null) {
            fVar5.a(aVar);
        }
        f fVar6 = this.l;
        if (fVar6 != null) {
            fVar6.a(aVar);
        }
    }

    public final void c() {
        for (int i7 = 0; i7 < 9; i7++) {
            this.f7417e[i7] = 0.0f;
        }
    }

    public final Matrix d() {
        PointF pointF;
        C0996b c0996b;
        PointF pointF2;
        Matrix matrix = this.f7415a;
        matrix.reset();
        e eVar = this.f7419g;
        if (eVar != null && (pointF2 = (PointF) eVar.d()) != null) {
            float f10 = pointF2.x;
            if (f10 != 0.0f || pointF2.y != 0.0f) {
                matrix.preTranslate(f10, pointF2.y);
            }
        }
        if (!this.o) {
            f fVar = this.f7421i;
            if (fVar != null) {
                float fI = fVar.i();
                if (fI != 0.0f) {
                    matrix.preRotate(fI);
                }
            }
        } else if (eVar != null) {
            float f11 = eVar.d;
            PointF pointF3 = (PointF) eVar.d();
            float f12 = pointF3.x;
            float f13 = pointF3.y;
            eVar.g(1.0E-4f + f11);
            PointF pointF4 = (PointF) eVar.d();
            eVar.g(f11);
            matrix.preRotate((float) Math.toDegrees(Math.atan2(pointF4.y - f13, pointF4.x - f12)));
        }
        if (this.f7423k != null) {
            f fVar2 = this.l;
            float fCos = fVar2 == null ? 0.0f : (float) Math.cos(Math.toRadians((-fVar2.i()) + 90.0f));
            float fSin = fVar2 == null ? 1.0f : (float) Math.sin(Math.toRadians((-fVar2.i()) + 90.0f));
            float fTan = (float) Math.tan(Math.toRadians(r1.i()));
            c();
            float[] fArr = this.f7417e;
            fArr[0] = fCos;
            fArr[1] = fSin;
            float f14 = -fSin;
            fArr[3] = f14;
            fArr[4] = fCos;
            fArr[8] = 1.0f;
            Matrix matrix2 = this.f7416b;
            matrix2.setValues(fArr);
            c();
            fArr[0] = 1.0f;
            fArr[3] = fTan;
            fArr[4] = 1.0f;
            fArr[8] = 1.0f;
            Matrix matrix3 = this.c;
            matrix3.setValues(fArr);
            c();
            fArr[0] = fCos;
            fArr[1] = f14;
            fArr[3] = fSin;
            fArr[4] = fCos;
            fArr[8] = 1.0f;
            Matrix matrix4 = this.d;
            matrix4.setValues(fArr);
            matrix3.preConcat(matrix2);
            matrix4.preConcat(matrix3);
            matrix.preConcat(matrix4);
        }
        h hVar = this.f7420h;
        if (hVar != null && (c0996b = (C0996b) hVar.d()) != null) {
            float f15 = c0996b.f12223a;
            if (f15 != 1.0f || c0996b.f12224b != 1.0f) {
                matrix.preScale(f15, c0996b.f12224b);
            }
        }
        i iVar = this.f7418f;
        if (iVar != null && (pointF = (PointF) iVar.d()) != null) {
            float f16 = pointF.x;
            if (f16 != 0.0f || pointF.y != 0.0f) {
                matrix.preTranslate(-f16, -pointF.y);
            }
        }
        return matrix;
    }

    public final Matrix e(float f10) {
        e eVar = this.f7419g;
        PointF pointF = eVar == null ? null : (PointF) eVar.d();
        h hVar = this.f7420h;
        C0996b c0996b = hVar == null ? null : (C0996b) hVar.d();
        Matrix matrix = this.f7415a;
        matrix.reset();
        if (pointF != null) {
            matrix.preTranslate(pointF.x * f10, pointF.y * f10);
        }
        if (c0996b != null) {
            double d = f10;
            matrix.preScale((float) Math.pow(c0996b.f12223a, d), (float) Math.pow(c0996b.f12224b, d));
        }
        f fVar = this.f7421i;
        if (fVar != null) {
            float fFloatValue = ((Float) fVar.d()).floatValue();
            i iVar = this.f7418f;
            PointF pointF2 = iVar != null ? (PointF) iVar.d() : null;
            matrix.preRotate(fFloatValue * f10, pointF2 == null ? 0.0f : pointF2.x, pointF2 != null ? pointF2.y : 0.0f);
        }
        return matrix;
    }
}
