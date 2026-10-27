package defpackage;

import sun.misc.Unsafe;

/* JADX INFO: renamed from: Bm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0030Bm extends AbstractC0048Cm {
    public C0030Bm(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // defpackage.AbstractC0048Cm
    public boolean c(Object obj, long j) {
        return this.a.getBoolean(obj, j);
    }

    @Override // defpackage.AbstractC0048Cm
    public byte d(Object obj, long j) {
        return this.a.getByte(obj, j);
    }

    @Override // defpackage.AbstractC0048Cm
    public double e(Object obj, long j) {
        return this.a.getDouble(obj, j);
    }

    @Override // defpackage.AbstractC0048Cm
    public float f(Object obj, long j) {
        return this.a.getFloat(obj, j);
    }

    @Override // defpackage.AbstractC0048Cm
    public void k(Object obj, long j, boolean z) {
        this.a.putBoolean(obj, j, z);
    }

    @Override // defpackage.AbstractC0048Cm
    public void l(Object obj, long j, byte b) {
        this.a.putByte(obj, j, b);
    }

    @Override // defpackage.AbstractC0048Cm
    public void m(Object obj, long j, double d) {
        this.a.putDouble(obj, j, d);
    }

    @Override // defpackage.AbstractC0048Cm
    public void n(Object obj, long j, float f) {
        this.a.putFloat(obj, j, f);
    }
}
