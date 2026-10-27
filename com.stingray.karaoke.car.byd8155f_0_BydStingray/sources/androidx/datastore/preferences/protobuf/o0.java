package androidx.datastore.preferences.protobuf;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends q0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f11874b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o0(Unsafe unsafe, int i7) {
        super(unsafe);
        this.f11874b = i7;
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final boolean c(long j3, Object obj) {
        switch (this.f11874b) {
            case 0:
                if (r0.f11887g) {
                    if (r0.h(j3, obj) == 0) {
                    }
                } else if (r0.i(j3, obj) == 0) {
                }
                break;
            default:
                if (r0.f11887g) {
                    if (r0.h(j3, obj) == 0) {
                    }
                } else if (r0.i(j3, obj) == 0) {
                }
                break;
        }
        return false;
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final byte d(long j3, Object obj) {
        switch (this.f11874b) {
            case 0:
                if (!r0.f11887g) {
                }
                break;
            default:
                if (!r0.f11887g) {
                }
                break;
        }
        return r0.i(j3, obj);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final double e(long j3, Object obj) {
        switch (this.f11874b) {
        }
        return Double.longBitsToDouble(h(j3, obj));
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final float f(long j3, Object obj) {
        switch (this.f11874b) {
        }
        return Float.intBitsToFloat(g(j3, obj));
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void k(Object obj, long j3, boolean z6) {
        switch (this.f11874b) {
            case 0:
                if (!r0.f11887g) {
                    r0.p(obj, j3, z6 ? (byte) 1 : (byte) 0);
                } else {
                    r0.o(obj, j3, z6 ? (byte) 1 : (byte) 0);
                }
                break;
            default:
                if (!r0.f11887g) {
                    r0.p(obj, j3, z6 ? (byte) 1 : (byte) 0);
                } else {
                    r0.o(obj, j3, z6 ? (byte) 1 : (byte) 0);
                }
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void l(Object obj, long j3, byte b10) {
        switch (this.f11874b) {
            case 0:
                if (!r0.f11887g) {
                    r0.p(obj, j3, b10);
                } else {
                    r0.o(obj, j3, b10);
                }
                break;
            default:
                if (!r0.f11887g) {
                    r0.p(obj, j3, b10);
                } else {
                    r0.o(obj, j3, b10);
                }
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void m(Object obj, long j3, double d) {
        switch (this.f11874b) {
            case 0:
                p(obj, j3, Double.doubleToLongBits(d));
                break;
            default:
                p(obj, j3, Double.doubleToLongBits(d));
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void n(Object obj, long j3, float f10) {
        switch (this.f11874b) {
            case 0:
                o(Float.floatToIntBits(f10), j3, obj);
                break;
            default:
                o(Float.floatToIntBits(f10), j3, obj);
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final boolean s() {
        switch (this.f11874b) {
        }
        return false;
    }
}
