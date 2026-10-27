package defpackage;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class kl3 {
    public final wm2<String, Method> a;
    public final wm2<String, Method> b;
    public final wm2<String, Class<?>> c;

    public kl3(wm2<String, Method> wm2Var, wm2<String, Method> wm2Var2, wm2<String, Class<?>> wm2Var3) {
        this.a = wm2Var;
        this.b = wm2Var2;
        this.c = wm2Var3;
    }

    public abstract ll3 a();

    public final Class<?> b(Class<?> cls) throws ClassNotFoundException {
        String name = cls.getName();
        wm2<String, Class<?>> wm2Var = this.c;
        Class<?> cls2 = wm2Var.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        wm2Var.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        wm2<String, Method> wm2Var = this.a;
        Method method = wm2Var.get(str);
        if (method != null) {
            return method;
        }
        Method declaredMethod = Class.forName(str, true, kl3.class.getClassLoader()).getDeclaredMethod("read", kl3.class);
        wm2Var.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method d(Class<?> cls) throws NoSuchMethodException {
        String name = cls.getName();
        wm2<String, Method> wm2Var = this.b;
        Method method = wm2Var.get(name);
        if (method != null) {
            return method;
        }
        Method declaredMethod = b(cls).getDeclaredMethod("write", cls, kl3.class);
        wm2Var.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e();

    public abstract byte[] f();

    public abstract CharSequence g();

    public abstract boolean h(int i);

    public abstract int i();

    public final int j(int i, int i2) {
        return !h(i2) ? i : i();
    }

    public abstract <T extends Parcelable> T k();

    public final <T extends Parcelable> T l(T t, int i) {
        return !h(i) ? t : (T) k();
    }

    public abstract String m();

    public final <T extends ml3> T n() {
        String strM = m();
        if (strM == null) {
            return null;
        }
        try {
            return (T) c(strM).invoke(null, a());
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

    public abstract void o(int i);

    public abstract void p(boolean z);

    public abstract void q(byte[] bArr);

    public abstract void r(CharSequence charSequence);

    public abstract void s(int i);

    public final void t(int i, int i2) {
        o(i2);
        s(i);
    }

    public abstract void u(Parcelable parcelable);

    public abstract void v(String str);

    public final void w(ml3 ml3Var) {
        if (ml3Var == null) {
            v(null);
            return;
        }
        try {
            v(b(ml3Var.getClass()).getName());
            ll3 ll3VarA = a();
            try {
                d(ml3Var.getClass()).invoke(null, ml3Var, ll3VarA);
                ll3VarA.x();
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
            throw new RuntimeException(ml3Var.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
