package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.S0;
import defpackage.e1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class a {
    final S0 a;
    final S0 b;
    final S0 c;

    a(S0 s0, S0 s02, S0 s03) {
        this.a = s0;
        this.b = s02;
        this.c = s03;
    }

    private Class c(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        S0 s0 = this.c;
        Class cls2 = (Class) s0.a(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        s0.e(cls.getName(), cls3);
        return cls3;
    }

    private Method d(String str) throws NoSuchMethodException {
        S0 s0 = this.a;
        Method method = (Method) s0.a(str);
        if (method != null) {
            return method;
        }
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        s0.e(str, declaredMethod);
        return declaredMethod;
    }

    private Method e(Class cls) throws NoSuchMethodException {
        String name = cls.getName();
        S0 s0 = this.b;
        Method method = (Method) s0.a(name);
        if (method != null) {
            return method;
        }
        Method declaredMethod = c(cls).getDeclaredMethod("write", cls, a.class);
        s0.e(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public final void A(CharSequence charSequence, int i) {
        u(i);
        z(charSequence);
    }

    protected abstract void B(int i);

    public final void C(int i, int i2) {
        u(i2);
        B(i);
    }

    protected abstract void D(Parcelable parcelable);

    public final void E(Parcelable parcelable, int i) {
        u(i);
        D(parcelable);
    }

    protected abstract void F(String str);

    public final void G(String str, int i) {
        u(i);
        F(str);
    }

    protected final void H(e1 e1Var) {
        if (e1Var == null) {
            F(null);
            return;
        }
        try {
            F(c(e1Var.getClass()).getName());
            a aVarB = b();
            try {
                e(e1Var.getClass()).invoke(null, e1Var, aVarB);
                aVarB.a();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                Throwable cause = e4.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                if (!(cause instanceof Error)) {
                    throw new RuntimeException(e4);
                }
                throw ((Error) cause);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(e1Var.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void I(IconCompat iconCompat) {
        u(1);
        H(iconCompat);
    }

    protected abstract void a();

    protected abstract a b();

    protected abstract boolean f();

    public final boolean g(int i, boolean z) {
        return !l(i) ? z : f();
    }

    protected abstract byte[] h();

    public final byte[] i(byte[] bArr) {
        return !l(2) ? bArr : h();
    }

    protected abstract CharSequence j();

    public final CharSequence k(CharSequence charSequence, int i) {
        return !l(i) ? charSequence : j();
    }

    protected abstract boolean l(int i);

    protected abstract int m();

    public final int n(int i, int i2) {
        return !l(i2) ? i : m();
    }

    protected abstract Parcelable o();

    public final Parcelable p(Parcelable parcelable, int i) {
        return !l(i) ? parcelable : o();
    }

    protected abstract String q();

    public final String r(String str, int i) {
        return !l(i) ? str : q();
    }

    protected final e1 s() {
        String strQ = q();
        if (strQ == null) {
            return null;
        }
        try {
            return (e1) d(strQ).invoke(null, b());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException(e4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final e1 t(IconCompat iconCompat) {
        return !l(1) ? iconCompat : s();
    }

    protected abstract void u(int i);

    public final void v(int i, boolean z) {
        u(i);
        w(z);
    }

    protected abstract void w(boolean z);

    protected abstract void x(byte[] bArr);

    public final void y(byte[] bArr) {
        u(2);
        x(bArr);
    }

    protected abstract void z(CharSequence charSequence);
}
