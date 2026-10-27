package R1;

import androidx.datastore.preferences.protobuf.AbstractC0955s;
import androidx.datastore.preferences.protobuf.AbstractC0957u;
import androidx.datastore.preferences.protobuf.C0944g;
import androidx.datastore.preferences.protobuf.C0956t;
import androidx.datastore.preferences.protobuf.T;
import androidx.datastore.preferences.protobuf.X;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AbstractC0957u {
    public static final int BOOLEAN_FIELD_NUMBER = 1;
    public static final int BYTES_FIELD_NUMBER = 8;
    private static final h DEFAULT_INSTANCE;
    public static final int DOUBLE_FIELD_NUMBER = 7;
    public static final int FLOAT_FIELD_NUMBER = 2;
    public static final int INTEGER_FIELD_NUMBER = 3;
    public static final int LONG_FIELD_NUMBER = 4;
    private static volatile T PARSER = null;
    public static final int STRING_FIELD_NUMBER = 5;
    public static final int STRING_SET_FIELD_NUMBER = 6;
    private int bitField0_;
    private int valueCase_ = 0;
    private Object value_;

    static {
        h hVar = new h();
        DEFAULT_INSTANCE = hVar;
        AbstractC0957u.l(h.class, hVar);
    }

    public static g F() {
        return (g) ((AbstractC0955s) DEFAULT_INSTANCE.e(5));
    }

    public static void n(h hVar, long j3) {
        hVar.valueCase_ = 4;
        hVar.value_ = Long.valueOf(j3);
    }

    public static void o(h hVar, String str) {
        hVar.getClass();
        str.getClass();
        hVar.valueCase_ = 5;
        hVar.value_ = str;
    }

    public static void p(h hVar, f fVar) {
        hVar.getClass();
        hVar.value_ = fVar;
        hVar.valueCase_ = 6;
    }

    public static void q(h hVar, double d) {
        hVar.valueCase_ = 7;
        hVar.value_ = Double.valueOf(d);
    }

    public static void r(h hVar, C0944g c0944g) {
        hVar.getClass();
        hVar.valueCase_ = 8;
        hVar.value_ = c0944g;
    }

    public static void s(h hVar, boolean z6) {
        hVar.valueCase_ = 1;
        hVar.value_ = Boolean.valueOf(z6);
    }

    public static void t(h hVar, float f10) {
        hVar.valueCase_ = 2;
        hVar.value_ = Float.valueOf(f10);
    }

    public static void u(h hVar, int i7) {
        hVar.valueCase_ = 3;
        hVar.value_ = Integer.valueOf(i7);
    }

    public static h x() {
        return DEFAULT_INSTANCE;
    }

    public final int A() {
        if (this.valueCase_ == 3) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    public final long B() {
        if (this.valueCase_ == 4) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    public final String C() {
        return this.valueCase_ == 5 ? (String) this.value_ : "";
    }

    public final f D() {
        return this.valueCase_ == 6 ? (f) this.value_ : f.o();
    }

    public final int E() {
        switch (this.valueCase_) {
            case 0:
                return 9;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            default:
                return 0;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0957u
    public final Object e(int i7) {
        switch (AbstractC2641j.c(i7)) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new X(DEFAULT_INSTANCE, "\u0001\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001်\u0000\u0002ဴ\u0000\u0003့\u0000\u0004ဵ\u0000\u0005ျ\u0000\u0006ြ\u0000\u0007ဳ\u0000\bွ\u0000", new Object[]{"value_", "valueCase_", "bitField0_", f.class});
            case 3:
                return new h();
            case 4:
                return new g(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                T c0956t = PARSER;
                if (c0956t == null) {
                    synchronized (h.class) {
                        try {
                            c0956t = PARSER;
                            if (c0956t == null) {
                                c0956t = new C0956t();
                                PARSER = c0956t;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return c0956t;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final boolean v() {
        if (this.valueCase_ == 1) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public final C0944g w() {
        return this.valueCase_ == 8 ? (C0944g) this.value_ : C0944g.f11844n;
    }

    public final double y() {
        if (this.valueCase_ == 7) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    public final float z() {
        if (this.valueCase_ == 2) {
            return ((Float) this.value_).floatValue();
        }
        return 0.0f;
    }
}
