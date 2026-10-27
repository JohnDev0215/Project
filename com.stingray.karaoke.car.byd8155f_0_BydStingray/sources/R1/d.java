package R1;

import E5.z;
import androidx.datastore.preferences.protobuf.AbstractC0955s;
import androidx.datastore.preferences.protobuf.AbstractC0957u;
import androidx.datastore.preferences.protobuf.C0946i;
import androidx.datastore.preferences.protobuf.C0950m;
import androidx.datastore.preferences.protobuf.C0956t;
import androidx.datastore.preferences.protobuf.C0961y;
import androidx.datastore.preferences.protobuf.J;
import androidx.datastore.preferences.protobuf.T;
import androidx.datastore.preferences.protobuf.V;
import androidx.datastore.preferences.protobuf.X;
import androidx.datastore.preferences.protobuf.Y;
import androidx.datastore.preferences.protobuf.h0;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractC0957u {
    private static final d DEFAULT_INSTANCE;
    private static volatile T PARSER = null;
    public static final int PREFERENCES_FIELD_NUMBER = 1;
    private J preferences_ = J.f11797m;

    static {
        d dVar = new d();
        DEFAULT_INSTANCE = dVar;
        AbstractC0957u.l(d.class, dVar);
    }

    public static J n(d dVar) {
        J j3 = dVar.preferences_;
        if (!j3.l) {
            dVar.preferences_ = j3.c();
        }
        return dVar.preferences_;
    }

    public static b p() {
        return (b) ((AbstractC0955s) DEFAULT_INSTANCE.e(5));
    }

    public static d q(InputStream inputStream) {
        d dVar = DEFAULT_INSTANCE;
        C0946i c0946i = new C0946i(inputStream);
        C0950m c0950mA = C0950m.a();
        AbstractC0957u abstractC0957uK = dVar.k();
        try {
            V v10 = V.c;
            v10.getClass();
            Y yA = v10.a(abstractC0957uK.getClass());
            z zVar = c0946i.f11863b;
            if (zVar == null) {
                zVar = new z(c0946i);
            }
            yA.i(abstractC0957uK, zVar, c0950mA);
            yA.d(abstractC0957uK);
            if (AbstractC0957u.h(abstractC0957uK, true)) {
                return (d) abstractC0957uK;
            }
            throw new C0961y(new h0().getMessage());
        } catch (h0 e10) {
            throw new C0961y(e10.getMessage());
        } catch (C0961y e11) {
            if (e11.l) {
                throw new C0961y(e11.getMessage(), e11);
            }
            throw e11;
        } catch (IOException e12) {
            if (e12.getCause() instanceof C0961y) {
                throw ((C0961y) e12.getCause());
            }
            throw new C0961y(e12.getMessage(), e12);
        } catch (RuntimeException e13) {
            if (e13.getCause() instanceof C0961y) {
                throw ((C0961y) e13.getCause());
            }
            throw e13;
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
                return new X(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"preferences_", c.f7354a});
            case 3:
                return new d();
            case 4:
                return new b(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                T c0956t = PARSER;
                if (c0956t == null) {
                    synchronized (d.class) {
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

    public final Map o() {
        return Collections.unmodifiableMap(this.preferences_);
    }
}
