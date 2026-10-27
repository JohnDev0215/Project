package S3;

import T.C0735d;
import T.C0738e0;
import T.D;
import T.Q0;
import T.S;
import x3.AbstractC2673a;
import y.n0;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Q0 {
    public final C0738e0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0738e0 f7903m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f7904n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C0738e0 f7905p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0738e0 f7906q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0738e0 f7907r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final D f7908s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0738e0 f7909t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f7910u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C0738e0 f7911v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0738e0 f7912w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final D f7913x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final n0 f7914y;

    public h() {
        Boolean bool = Boolean.FALSE;
        S s6 = S.f8224q;
        this.l = C0735d.O(bool, s6);
        this.f7903m = C0735d.O(1, s6);
        this.f7904n = C0735d.O(1, s6);
        this.o = C0735d.O(bool, s6);
        this.f7905p = C0735d.O(null, s6);
        this.f7906q = C0735d.O(Float.valueOf(1.0f), s6);
        this.f7907r = C0735d.O(bool, s6);
        this.f7908s = C0735d.G(new f(this, 1));
        this.f7909t = C0735d.O(null, s6);
        Float fValueOf = Float.valueOf(0.0f);
        this.f7910u = C0735d.O(fValueOf, s6);
        this.f7911v = C0735d.O(fValueOf, s6);
        this.f7912w = C0735d.O(Long.MIN_VALUE, s6);
        this.f7913x = C0735d.G(new f(this, 0));
        C0735d.G(new f(this, 2));
        this.f7914y = new n0();
    }

    public static final boolean b(h hVar, int i7, long j3) {
        O3.a aVar = (O3.a) hVar.f7909t.getValue();
        if (aVar == null) {
            return true;
        }
        C0738e0 c0738e0 = hVar.f7912w;
        long jLongValue = ((Number) c0738e0.getValue()).longValue() == Long.MIN_VALUE ? 0L : j3 - ((Number) c0738e0.getValue()).longValue();
        c0738e0.setValue(Long.valueOf(j3));
        C0738e0 c0738e02 = hVar.f7905p;
        if (c0738e02.getValue() != null) {
            throw new ClassCastException();
        }
        if (c0738e02.getValue() != null) {
            throw new ClassCastException();
        }
        float fB = (jLongValue / ((long) 1000000)) / aVar.b();
        D d = hVar.f7908s;
        float fFloatValue = ((Number) d.getValue()).floatValue() * fB;
        float fFloatValue2 = ((Number) d.getValue()).floatValue();
        C0738e0 c0738e03 = hVar.f7910u;
        float fFloatValue3 = fFloatValue2 < 0.0f ? 0.0f - (((Number) c0738e03.getValue()).floatValue() + fFloatValue) : (((Number) c0738e03.getValue()).floatValue() + fFloatValue) - 1.0f;
        if (fFloatValue3 < 0.0f) {
            hVar.h(AbstractC2673a.u(((Number) c0738e03.getValue()).floatValue(), 0.0f, 1.0f) + fFloatValue);
            return true;
        }
        int i10 = (int) (fFloatValue3 / 1.0f);
        int i11 = i10 + 1;
        if (hVar.e() + i11 > i7) {
            hVar.h(hVar.d());
            hVar.g(i7);
            return false;
        }
        hVar.g(hVar.e() + i11);
        float f10 = fFloatValue3 - (i10 * 1.0f);
        hVar.h(((Number) d.getValue()).floatValue() < 0.0f ? 1.0f - f10 : 0.0f + f10);
        return true;
    }

    public static final void c(h hVar, boolean z6) {
        hVar.l.setValue(Boolean.valueOf(z6));
    }

    public final float d() {
        return ((Number) this.f7913x.getValue()).floatValue();
    }

    public final int e() {
        return ((Number) this.f7903m.getValue()).intValue();
    }

    public final void g(int i7) {
        this.f7903m.setValue(Integer.valueOf(i7));
    }

    @Override // T.Q0
    public final Object getValue() {
        return Float.valueOf(((Number) this.f7911v.getValue()).floatValue());
    }

    public final void h(float f10) {
        O3.a aVar;
        this.f7910u.setValue(Float.valueOf(f10));
        if (((Boolean) this.f7907r.getValue()).booleanValue() && (aVar = (O3.a) this.f7909t.getValue()) != null) {
            f10 -= f10 % (1 / aVar.f5079n);
        }
        this.f7911v.setValue(Float.valueOf(f10));
    }
}
