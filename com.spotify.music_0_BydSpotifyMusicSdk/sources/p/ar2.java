package p;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class ar2 extends cr2 {
    public final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ar2(Unsafe unsafe, int i) {
        super(unsafe);
        this.b = i;
    }

    @Override // p.cr2
    public final void c(long j, byte[] bArr, long j2) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // p.cr2
    public final boolean d(long j, Object obj) {
        switch (this.b) {
            case 0:
                if (dr2.h) {
                    if (dr2.h(j, obj) == 0) {
                    }
                } else if (dr2.i(j, obj) == 0) {
                }
                break;
            default:
                if (dr2.h) {
                    if (dr2.h(j, obj) == 0) {
                    }
                } else if (dr2.i(j, obj) == 0) {
                }
                break;
        }
        return false;
    }

    @Override // p.cr2
    public final byte e(long j) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // p.cr2
    public final byte f(long j, Object obj) {
        switch (this.b) {
            case 0:
                if (!dr2.h) {
                }
                break;
            default:
                if (!dr2.h) {
                }
                break;
        }
        return dr2.i(j, obj);
    }

    @Override // p.cr2
    public final double g(long j, Object obj) {
        switch (this.b) {
        }
        return Double.longBitsToDouble(j(j, obj));
    }

    @Override // p.cr2
    public final float h(long j, Object obj) {
        switch (this.b) {
        }
        return Float.intBitsToFloat(i(j, obj));
    }

    @Override // p.cr2
    public final void m(Object obj, long j, boolean z) {
        switch (this.b) {
            case 0:
                if (!dr2.h) {
                    dr2.q(obj, j, z ? (byte) 1 : (byte) 0);
                } else {
                    dr2.p(obj, j, z ? (byte) 1 : (byte) 0);
                }
                break;
            default:
                if (!dr2.h) {
                    dr2.q(obj, j, z ? (byte) 1 : (byte) 0);
                } else {
                    dr2.p(obj, j, z ? (byte) 1 : (byte) 0);
                }
                break;
        }
    }

    @Override // p.cr2
    public final void n(Object obj, long j, byte b) {
        switch (this.b) {
            case 0:
                if (!dr2.h) {
                    dr2.q(obj, j, b);
                } else {
                    dr2.p(obj, j, b);
                }
                break;
            default:
                if (!dr2.h) {
                    dr2.q(obj, j, b);
                } else {
                    dr2.p(obj, j, b);
                }
                break;
        }
    }

    @Override // p.cr2
    public final void o(Object obj, long j, double d) {
        switch (this.b) {
            case 0:
                r(obj, j, Double.doubleToLongBits(d));
                break;
            default:
                r(obj, j, Double.doubleToLongBits(d));
                break;
        }
    }

    @Override // p.cr2
    public final void p(Object obj, long j, float f) {
        switch (this.b) {
            case 0:
                q(Float.floatToIntBits(f), j, obj);
                break;
            default:
                q(Float.floatToIntBits(f), j, obj);
                break;
        }
    }

    @Override // p.cr2
    public final boolean u() {
        return false;
    }
}
