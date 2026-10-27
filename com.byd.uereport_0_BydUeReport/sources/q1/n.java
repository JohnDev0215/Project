package q1;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: PolystarContent.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements m, a.b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7322b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o1.g f7323c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PolystarShape.Type f7324d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f7325e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r1.a<?, Float> f7326f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r1.a<?, PointF> f7327g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final r1.a<?, Float> f7328h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r1.a<?, Float> f7329i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final r1.a<?, Float> f7330j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final r1.a<?, Float> f7331k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r1.a<?, Float> f7332l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f7334n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f7321a = new Path();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f7333m = new b();

    /* JADX INFO: compiled from: PolystarContent.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7335a;

        static {
            int[] iArr = new int[PolystarShape.Type.values().length];
            f7335a = iArr;
            try {
                iArr[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7335a[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public n(o1.g gVar, w1.a aVar, PolystarShape polystarShape) {
        this.f7323c = gVar;
        this.f7322b = polystarShape.d();
        PolystarShape.Type typeJ = polystarShape.j();
        this.f7324d = typeJ;
        this.f7325e = polystarShape.k();
        r1.a<Float, Float> aVarA = polystarShape.g().a();
        this.f7326f = aVarA;
        r1.a<PointF, PointF> aVarA2 = polystarShape.h().a();
        this.f7327g = aVarA2;
        r1.a<Float, Float> aVarA3 = polystarShape.i().a();
        this.f7328h = aVarA3;
        r1.a<Float, Float> aVarA4 = polystarShape.e().a();
        this.f7330j = aVarA4;
        r1.a<Float, Float> aVarA5 = polystarShape.f().a();
        this.f7332l = aVarA5;
        PolystarShape.Type type = PolystarShape.Type.STAR;
        if (typeJ == type) {
            this.f7329i = polystarShape.b().a();
            this.f7331k = polystarShape.c().a();
        } else {
            this.f7329i = null;
            this.f7331k = null;
        }
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVar.k(aVarA3);
        aVar.k(aVarA4);
        aVar.k(aVarA5);
        if (typeJ == type) {
            aVar.k(this.f7329i);
            aVar.k(this.f7331k);
        }
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
        aVarA4.a(this);
        aVarA5.a(this);
        if (typeJ == type) {
            this.f7329i.a(this);
            this.f7331k.a(this);
        }
    }

    @Override // q1.m
    public Path a() {
        if (this.f7334n) {
            return this.f7321a;
        }
        this.f7321a.reset();
        if (this.f7325e) {
            this.f7334n = true;
            return this.f7321a;
        }
        int i8 = a.f7335a[this.f7324d.ordinal()];
        if (i8 == 1) {
            j();
        } else if (i8 == 2) {
            e();
        }
        this.f7321a.close();
        this.f7333m.b(this.f7321a);
        this.f7334n = true;
        return this.f7321a;
    }

    @Override // r1.a.b
    public void c() {
        k();
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        for (int i8 = 0; i8 < list.size(); i8++) {
            c cVar = list.get(i8);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.f7333m.a(sVar);
                    sVar.e(this);
                }
            }
        }
    }

    public final void e() {
        double d8;
        double d9;
        double d10;
        int i8;
        int iFloor = (int) Math.floor(this.f7326f.h().floatValue());
        double radians = Math.toRadians((this.f7328h == null ? 0.0d : r2.h().floatValue()) - 90.0d);
        double d11 = iFloor;
        float fFloatValue = this.f7332l.h().floatValue() / 100.0f;
        float fFloatValue2 = this.f7330j.h().floatValue();
        double d12 = fFloatValue2;
        float fCos = (float) (Math.cos(radians) * d12);
        float fSin = (float) (Math.sin(radians) * d12);
        this.f7321a.moveTo(fCos, fSin);
        double d13 = (float) (6.283185307179586d / d11);
        double d14 = radians + d13;
        double dCeil = Math.ceil(d11);
        int i9 = 0;
        while (i9 < dCeil) {
            float fCos2 = (float) (Math.cos(d14) * d12);
            double d15 = dCeil;
            float fSin2 = (float) (d12 * Math.sin(d14));
            if (fFloatValue != 0.0f) {
                d9 = d12;
                i8 = i9;
                d8 = d14;
                double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                float fCos3 = (float) Math.cos(dAtan2);
                float fSin3 = (float) Math.sin(dAtan2);
                d10 = d13;
                double dAtan22 = (float) (Math.atan2(fSin2, fCos2) - 1.5707963267948966d);
                float f8 = fFloatValue2 * fFloatValue * 0.25f;
                this.f7321a.cubicTo(fCos - (fCos3 * f8), fSin - (fSin3 * f8), fCos2 + (((float) Math.cos(dAtan22)) * f8), fSin2 + (f8 * ((float) Math.sin(dAtan22))), fCos2, fSin2);
            } else {
                d8 = d14;
                d9 = d12;
                d10 = d13;
                i8 = i9;
                this.f7321a.lineTo(fCos2, fSin2);
            }
            d14 = d8 + d10;
            i9 = i8 + 1;
            fSin = fSin2;
            fCos = fCos2;
            dCeil = d15;
            d12 = d9;
            d13 = d10;
        }
        PointF pointFH = this.f7327g.h();
        this.f7321a.offset(pointFH.x, pointFH.y);
        this.f7321a.close();
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.c
    public String h() {
        return this.f7322b;
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        r1.a<?, Float> aVar;
        r1.a<?, Float> aVar2;
        if (t7 == o1.l.f6846s) {
            this.f7326f.m(cVar);
            return;
        }
        if (t7 == o1.l.f6847t) {
            this.f7328h.m(cVar);
            return;
        }
        if (t7 == o1.l.f6837j) {
            this.f7327g.m(cVar);
            return;
        }
        if (t7 == o1.l.f6848u && (aVar2 = this.f7329i) != null) {
            aVar2.m(cVar);
            return;
        }
        if (t7 == o1.l.f6849v) {
            this.f7330j.m(cVar);
            return;
        }
        if (t7 == o1.l.f6850w && (aVar = this.f7331k) != null) {
            aVar.m(cVar);
        } else if (t7 == o1.l.f6851x) {
            this.f7332l.m(cVar);
        }
    }

    public final void j() {
        double d8;
        int i8;
        double d9;
        float fCos;
        float fSin;
        float f8;
        float f9;
        float f10;
        float f11;
        double d10;
        float f12;
        float f13;
        float f14;
        float fFloatValue = this.f7326f.h().floatValue();
        double radians = Math.toRadians((this.f7328h == null ? 0.0d : r2.h().floatValue()) - 90.0d);
        double d11 = fFloatValue;
        float f15 = (float) (6.283185307179586d / d11);
        float f16 = f15 / 2.0f;
        float f17 = fFloatValue - ((int) fFloatValue);
        int i9 = (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1));
        if (i9 != 0) {
            radians += (double) ((1.0f - f17) * f16);
        }
        float fFloatValue2 = this.f7330j.h().floatValue();
        float fFloatValue3 = this.f7329i.h().floatValue();
        r1.a<?, Float> aVar = this.f7331k;
        float fFloatValue4 = aVar != null ? aVar.h().floatValue() / 100.0f : 0.0f;
        r1.a<?, Float> aVar2 = this.f7332l;
        float fFloatValue5 = aVar2 != null ? aVar2.h().floatValue() / 100.0f : 0.0f;
        if (i9 != 0) {
            f8 = ((fFloatValue2 - fFloatValue3) * f17) + fFloatValue3;
            i8 = i9;
            double d12 = f8;
            d8 = d11;
            fCos = (float) (d12 * Math.cos(radians));
            fSin = (float) (d12 * Math.sin(radians));
            this.f7321a.moveTo(fCos, fSin);
            d9 = radians + ((double) ((f15 * f17) / 2.0f));
        } else {
            d8 = d11;
            i8 = i9;
            double d13 = fFloatValue2;
            float fCos2 = (float) (Math.cos(radians) * d13);
            float fSin2 = (float) (d13 * Math.sin(radians));
            this.f7321a.moveTo(fCos2, fSin2);
            d9 = radians + ((double) f16);
            fCos = fCos2;
            fSin = fSin2;
            f8 = 0.0f;
        }
        double dCeil = Math.ceil(d8) * 2.0d;
        int i10 = 0;
        boolean z7 = false;
        while (true) {
            double d14 = i10;
            if (d14 >= dCeil) {
                PointF pointFH = this.f7327g.h();
                this.f7321a.offset(pointFH.x, pointFH.y);
                this.f7321a.close();
                return;
            }
            float f18 = z7 ? fFloatValue2 : fFloatValue3;
            if (f8 == 0.0f || d14 != dCeil - 2.0d) {
                f9 = f15;
                f10 = f16;
            } else {
                f9 = f15;
                f10 = (f15 * f17) / 2.0f;
            }
            if (f8 == 0.0f || d14 != dCeil - 1.0d) {
                f11 = f16;
                d10 = d14;
                f12 = f18;
            } else {
                f11 = f16;
                d10 = d14;
                f12 = f8;
            }
            double d15 = f12;
            double d16 = dCeil;
            float fCos3 = (float) (d15 * Math.cos(d9));
            float fSin3 = (float) (d15 * Math.sin(d9));
            if (fFloatValue4 == 0.0f && fFloatValue5 == 0.0f) {
                this.f7321a.lineTo(fCos3, fSin3);
                f13 = fFloatValue4;
                f14 = f8;
            } else {
                f13 = fFloatValue4;
                f14 = f8;
                double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                float fCos4 = (float) Math.cos(dAtan2);
                float fSin4 = (float) Math.sin(dAtan2);
                double dAtan22 = (float) (Math.atan2(fSin3, fCos3) - 1.5707963267948966d);
                float fCos5 = (float) Math.cos(dAtan22);
                float fSin5 = (float) Math.sin(dAtan22);
                float f19 = z7 ? f13 : fFloatValue5;
                float f20 = z7 ? fFloatValue5 : f13;
                float f21 = (z7 ? fFloatValue3 : fFloatValue2) * f19 * 0.47829f;
                float f22 = fCos4 * f21;
                float f23 = f21 * fSin4;
                float f24 = (z7 ? fFloatValue2 : fFloatValue3) * f20 * 0.47829f;
                float f25 = fCos5 * f24;
                float f26 = f24 * fSin5;
                if (i8 != 0) {
                    if (i10 == 0) {
                        f22 *= f17;
                        f23 *= f17;
                    } else if (d10 == d16 - 1.0d) {
                        f25 *= f17;
                        f26 *= f17;
                    }
                }
                this.f7321a.cubicTo(fCos - f22, fSin - f23, fCos3 + f25, fSin3 + f26, fCos3, fSin3);
            }
            d9 += (double) f10;
            z7 = !z7;
            i10++;
            fCos = fCos3;
            fSin = fSin3;
            fFloatValue4 = f13;
            f8 = f14;
            f16 = f11;
            f15 = f9;
            dCeil = d16;
        }
    }

    public final void k() {
        this.f7334n = false;
        this.f7323c.invalidateSelf();
    }
}
