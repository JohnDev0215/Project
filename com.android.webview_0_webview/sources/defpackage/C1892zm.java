package defpackage;

import sun.misc.Unsafe;

/* JADX INFO: renamed from: zm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1892zm extends AbstractC0048Cm {
    public C1892zm(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // defpackage.AbstractC0048Cm
    public boolean c(Object obj, long j) {
        return AbstractC0066Dm.j ? AbstractC0066Dm.h(obj, j) != 0 : AbstractC0066Dm.i(obj, j) != 0;
    }

    @Override // defpackage.AbstractC0048Cm
    public byte d(Object obj, long j) {
        return AbstractC0066Dm.j ? AbstractC0066Dm.h(obj, j) : AbstractC0066Dm.i(obj, j);
    }

    @Override // defpackage.AbstractC0048Cm
    public double e(Object obj, long j) {
        return Double.longBitsToDouble(h(obj, j));
    }

    @Override // defpackage.AbstractC0048Cm
    public float f(Object obj, long j) {
        return Float.intBitsToFloat(g(obj, j));
    }

    @Override // defpackage.AbstractC0048Cm
    public void k(Object obj, long j, boolean z) {
        if (AbstractC0066Dm.j) {
            AbstractC0066Dm.r(obj, j, z ? (byte) 1 : (byte) 0);
        } else {
            AbstractC0066Dm.s(obj, j, z ? (byte) 1 : (byte) 0);
        }
    }

    @Override // defpackage.AbstractC0048Cm
    public void l(Object obj, long j, byte b) {
        if (AbstractC0066Dm.j) {
            AbstractC0066Dm.r(obj, j, b);
        } else {
            AbstractC0066Dm.s(obj, j, b);
        }
    }

    @Override // defpackage.AbstractC0048Cm
    public void m(Object obj, long j, double d) {
        p(obj, j, Double.doubleToLongBits(d));
    }

    @Override // defpackage.AbstractC0048Cm
    public void n(Object obj, long j, float f) {
        o(obj, j, Float.floatToIntBits(f));
    }
}
