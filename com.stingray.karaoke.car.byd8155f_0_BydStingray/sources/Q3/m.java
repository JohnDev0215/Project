package Q3;

import A7.D;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.List;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class m implements l, R3.a, c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final O3.j f6184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f6186g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f6187h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final R3.f f6188i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final R3.e f6189j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final R3.f f6190k;
    public final R3.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final R3.f f6191m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final R3.f f6192n;
    public final R3.f o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6194q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6182a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f6183b = new Path();
    public final PathMeasure c = new PathMeasure();
    public final float[] d = new float[2];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final D f6193p = new D(1, false);

    public m(O3.j jVar, X3.b bVar, W3.h hVar) {
        this.f6184e = jVar;
        hVar.getClass();
        int i7 = hVar.f9780a;
        this.f6185f = i7;
        this.f6186g = hVar.f9786i;
        this.f6187h = hVar.f9787j;
        R3.f fVarT0 = hVar.f9781b.t0();
        this.f6188i = fVarT0;
        R3.e eVarT0 = hVar.c.t0();
        this.f6189j = eVarT0;
        R3.f fVarT02 = hVar.d.t0();
        this.f6190k = fVarT02;
        R3.f fVarT03 = hVar.f9783f.t0();
        this.f6191m = fVarT03;
        R3.f fVarT04 = hVar.f9785h.t0();
        this.o = fVarT04;
        if (i7 == 1) {
            this.l = hVar.f9782e.t0();
            this.f6192n = hVar.f9784g.t0();
        } else {
            this.l = null;
            this.f6192n = null;
        }
        bVar.d(fVarT0);
        bVar.d(eVarT0);
        bVar.d(fVarT02);
        bVar.d(fVarT03);
        bVar.d(fVarT04);
        if (i7 == 1) {
            bVar.d(this.l);
            bVar.d(this.f6192n);
        }
        fVarT0.a(this);
        eVarT0.a(this);
        fVarT02.a(this);
        fVarT03.a(this);
        fVarT04.a(this);
        if (i7 == 1) {
            this.l.a(this);
            this.f6192n.a(this);
        }
    }

    @Override // R3.a
    public final void b() {
        this.f6194q = false;
        this.f6184e.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i7 >= arrayList.size()) {
                return;
            }
            c cVar = (c) arrayList.get(i7);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.c == 1) {
                    this.f6193p.l.add(sVar);
                    sVar.d(this);
                }
            }
            i7++;
        }
    }

    @Override // Q3.l
    public final Path e() {
        double d;
        float fCos;
        float fSin;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        int i7;
        double d10;
        float f20;
        boolean z6 = this.f6194q;
        Path path = this.f6182a;
        if (z6) {
            return path;
        }
        path.reset();
        if (this.f6186g) {
            this.f6194q = true;
            return path;
        }
        int iC = AbstractC2641j.c(this.f6185f);
        R3.e eVar = this.f6189j;
        R3.f fVar = this.f6191m;
        R3.f fVar2 = this.o;
        R3.f fVar3 = this.f6190k;
        R3.f fVar4 = this.f6188i;
        if (iC == 0) {
            float fFloatValue = ((Float) fVar4.d()).floatValue();
            double radians = Math.toRadians((fVar3 != null ? ((Float) fVar3.d()).floatValue() : 0.0d) - 90.0d);
            double d11 = fFloatValue;
            float f21 = (float) (6.283185307179586d / d11);
            if (this.f6187h) {
                f21 *= -1.0f;
            }
            float f22 = f21;
            float f23 = 2.0f;
            float f24 = f22 / 2.0f;
            float f25 = fFloatValue - ((int) fFloatValue);
            if (f25 != 0.0f) {
                radians += (double) ((1.0f - f25) * f24);
            }
            float fFloatValue2 = ((Float) fVar.d()).floatValue();
            float fFloatValue3 = ((Float) this.l.d()).floatValue();
            R3.f fVar5 = this.f6192n;
            float fFloatValue4 = fVar5 != null ? ((Float) fVar5.d()).floatValue() / 100.0f : 0.0f;
            float fFloatValue5 = fVar2 != null ? ((Float) fVar2.d()).floatValue() / 100.0f : 0.0f;
            if (f25 != 0.0f) {
                float fA = AbstractC1347d.a(fFloatValue2, fFloatValue3, f25, fFloatValue3);
                double d12 = fA;
                f10 = fA;
                fCos = (float) (Math.cos(radians) * d12);
                fSin = (float) (Math.sin(radians) * d12);
                path.moveTo(fCos, fSin);
                d = radians + ((double) ((f22 * f25) / 2.0f));
            } else {
                double d13 = fFloatValue2;
                float fCos2 = (float) (Math.cos(radians) * d13);
                float fSin2 = (float) (d13 * Math.sin(radians));
                path.moveTo(fCos2, fSin2);
                d = radians + ((double) f24);
                fCos = fCos2;
                fSin = fSin2;
                f10 = 0.0f;
            }
            double dCeil = Math.ceil(d11);
            double d14 = 2.0d;
            double d15 = dCeil * 2.0d;
            double d16 = d;
            int i10 = 0;
            boolean z10 = false;
            while (true) {
                double d17 = i10;
                if (d17 >= d15) {
                    break;
                }
                float f26 = z10 ? fFloatValue2 : fFloatValue3;
                float f27 = (f10 == 0.0f || d17 != d15 - d14) ? f24 : (f22 * f25) / f23;
                if (f10 == 0.0f || d17 != d15 - 1.0d) {
                    f11 = fFloatValue3;
                    f12 = fFloatValue2;
                } else {
                    f11 = fFloatValue3;
                    f12 = fFloatValue2;
                    f26 = f10;
                }
                double d18 = f26;
                float f28 = f12;
                float f29 = f22;
                float fCos3 = (float) (Math.cos(d16) * d18);
                float fSin3 = (float) (d18 * Math.sin(d16));
                if (fFloatValue4 == 0.0f && fFloatValue5 == 0.0f) {
                    path.lineTo(fCos3, fSin3);
                    f13 = f29;
                    f14 = fSin3;
                    f15 = f11;
                    f16 = f28;
                    f17 = f24;
                    f19 = f27;
                    f18 = f25;
                } else {
                    f13 = f29;
                    float f30 = f27;
                    double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                    float fCos4 = (float) Math.cos(dAtan2);
                    float fSin4 = (float) Math.sin(dAtan2);
                    float f31 = f24;
                    f14 = fSin3;
                    double dAtan22 = (float) (Math.atan2(fSin3, fCos3) - 1.5707963267948966d);
                    float fCos5 = (float) Math.cos(dAtan22);
                    float fSin5 = (float) Math.sin(dAtan22);
                    float f32 = z10 ? fFloatValue4 : fFloatValue5;
                    float f33 = z10 ? fFloatValue5 : fFloatValue4;
                    float f34 = (z10 ? f11 : f28) * f32 * 0.47829f;
                    float f35 = fCos4 * f34;
                    float f36 = f34 * fSin4;
                    float f37 = (z10 ? f28 : f11) * f33 * 0.47829f;
                    float f38 = fCos5 * f37;
                    float f39 = f37 * fSin5;
                    if (f25 == 0.0f) {
                        f15 = f11;
                        f16 = f28;
                        f17 = f31;
                        f18 = f25;
                        path.cubicTo(fCos - f35, fSin - f36, fCos3 + f38, f14 + f39, fCos3, f14);
                        f19 = f30;
                    } else if (i10 == 0) {
                        f35 *= f25;
                        f36 *= f25;
                        f15 = f11;
                        f16 = f28;
                        f17 = f31;
                        f18 = f25;
                        path.cubicTo(fCos - f35, fSin - f36, fCos3 + f38, f14 + f39, fCos3, f14);
                        f19 = f30;
                    } else {
                        if (d17 == d15 - 1.0d) {
                            f38 *= f25;
                            f39 *= f25;
                        }
                        f15 = f11;
                        f16 = f28;
                        f17 = f31;
                        f18 = f25;
                        path.cubicTo(fCos - f35, fSin - f36, fCos3 + f38, f14 + f39, fCos3, f14);
                        f19 = f30;
                    }
                }
                d16 += (double) f19;
                z10 = !z10;
                i10++;
                fFloatValue3 = f15;
                fFloatValue2 = f16;
                f25 = f18;
                fCos = fCos3;
                f24 = f17;
                f22 = f13;
                fSin = f14;
                d14 = 2.0d;
                f23 = 2.0f;
            }
            PointF pointF = (PointF) eVar.d();
            path.offset(pointF.x, pointF.y);
            path.close();
        } else if (iC == 1) {
            int iFloor = (int) Math.floor(((Float) fVar4.d()).floatValue());
            double radians2 = Math.toRadians((fVar3 != null ? ((Float) fVar3.d()).floatValue() : 0.0d) - 90.0d);
            double d19 = iFloor;
            float fFloatValue6 = ((Float) fVar2.d()).floatValue() / 100.0f;
            float fFloatValue7 = ((Float) fVar.d()).floatValue();
            double d20 = fFloatValue7;
            float fCos6 = (float) (Math.cos(radians2) * d20);
            float fSin6 = (float) (Math.sin(radians2) * d20);
            path.moveTo(fCos6, fSin6);
            double d21 = (float) (6.283185307179586d / d19);
            double dCeil2 = Math.ceil(d19);
            double d22 = radians2 + d21;
            int i11 = 0;
            while (true) {
                double d23 = i11;
                if (d23 >= dCeil2) {
                    break;
                }
                float fCos7 = (float) (Math.cos(d22) * d20);
                double d24 = d21;
                float fSin7 = (float) (Math.sin(d22) * d20);
                if (fFloatValue6 != 0.0f) {
                    d10 = d20;
                    double dAtan23 = (float) (Math.atan2(fSin6, fCos6) - 1.5707963267948966d);
                    float fCos8 = (float) Math.cos(dAtan23);
                    float fSin8 = (float) Math.sin(dAtan23);
                    i7 = i11;
                    float f40 = fCos6;
                    double dAtan24 = (float) (Math.atan2(fSin7, fCos7) - 1.5707963267948966d);
                    float f41 = fFloatValue7 * fFloatValue6 * 0.25f;
                    float f42 = fCos8 * f41;
                    float f43 = fSin8 * f41;
                    float fCos9 = ((float) Math.cos(dAtan24)) * f41;
                    float fSin9 = f41 * ((float) Math.sin(dAtan24));
                    if (d23 == dCeil2 - 1.0d) {
                        Path path2 = this.f6183b;
                        path2.reset();
                        path2.moveTo(f40, fSin6);
                        float f44 = f40 - f42;
                        float f45 = fSin6 - f43;
                        float f46 = fCos9 + fCos7;
                        float f47 = fSin7 + fSin9;
                        path2.cubicTo(f44, f45, f46, f47, fCos7, fSin7);
                        PathMeasure pathMeasure = this.c;
                        pathMeasure.setPath(path2, false);
                        float length = pathMeasure.getLength() * 0.9999f;
                        f20 = fFloatValue7;
                        float[] fArr = this.d;
                        pathMeasure.getPosTan(length, fArr, null);
                        path.cubicTo(f44, f45, f46, f47, fArr[0], fArr[1]);
                    } else {
                        f20 = fFloatValue7;
                        path.cubicTo(f40 - f42, fSin6 - f43, fCos7 + fCos9, fSin7 + fSin9, fCos7, fSin7);
                    }
                } else {
                    i7 = i11;
                    d10 = d20;
                    f20 = fFloatValue7;
                    if (d23 == dCeil2 - 1.0d) {
                        i11 = i7 + 1;
                        fCos6 = fCos7;
                        fSin6 = fSin7;
                        fFloatValue7 = f20;
                        d20 = d10;
                        d21 = d24;
                    } else {
                        path.lineTo(fCos7, fSin7);
                    }
                }
                d22 += d24;
                i11 = i7 + 1;
                fCos6 = fCos7;
                fSin6 = fSin7;
                fFloatValue7 = f20;
                d20 = d10;
                d21 = d24;
            }
            PointF pointF2 = (PointF) eVar.d();
            path.offset(pointF2.x, pointF2.y);
            path.close();
        }
        path.close();
        this.f6193p.l(path);
        this.f6194q = true;
        return path;
    }
}
