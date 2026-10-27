package m1;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v.a<String, Method> f6340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v.a<String, Method> f6341b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v.a<String, Class> f6342c;

    public a(v.a<String, Method> aVar, v.a<String, Method> aVar2, v.a<String, Class> aVar3) {
        this.f6340a = aVar;
        this.f6341b = aVar2;
        this.f6342c = aVar3;
    }

    public abstract void A(byte[] bArr);

    public void B(byte[] bArr, int i8) {
        w(i8);
        A(bArr);
    }

    public abstract void C(CharSequence charSequence);

    public void D(CharSequence charSequence, int i8) {
        w(i8);
        C(charSequence);
    }

    public abstract void E(int i8);

    public void F(int i8, int i9) {
        w(i9);
        E(i8);
    }

    public abstract void G(Parcelable parcelable);

    public void H(Parcelable parcelable, int i8) {
        w(i8);
        G(parcelable);
    }

    public abstract void I(String str);

    public void J(String str, int i8) {
        w(i8);
        I(str);
    }

    public <T extends c> void K(T t7, a aVar) {
        try {
            e(t7.getClass()).invoke(null, t7, aVar);
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e8);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e9);
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e10);
        } catch (InvocationTargetException e11) {
            if (!(e11.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e11);
            }
            throw ((RuntimeException) e11.getCause());
        }
    }

    public void L(c cVar) {
        if (cVar == null) {
            I(null);
            return;
        }
        N(cVar);
        a aVarB = b();
        K(cVar, aVarB);
        aVarB.a();
    }

    public void M(c cVar, int i8) {
        w(i8);
        L(cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void N(c cVar) {
        try {
            I(c(cVar.getClass()).getName());
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException(cVar.getClass().getSimpleName() + " does not have a Parcelizer", e8);
        }
    }

    public abstract void a();

    public abstract a b();

    public final Class c(Class<? extends c> cls) throws ClassNotFoundException {
        Class cls2 = this.f6342c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f6342c.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method d(String str) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.f6340a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        this.f6340a.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method e(Class cls) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.f6341b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class clsC = c(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsC.getDeclaredMethod("write", cls, a.class);
        this.f6341b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public boolean f() {
        return false;
    }

    public abstract boolean g();

    public boolean h(boolean z7, int i8) {
        return !m(i8) ? z7 : g();
    }

    public abstract byte[] i();

    public byte[] j(byte[] bArr, int i8) {
        return !m(i8) ? bArr : i();
    }

    public abstract CharSequence k();

    public CharSequence l(CharSequence charSequence, int i8) {
        return !m(i8) ? charSequence : k();
    }

    public abstract boolean m(int i8);

    public <T extends c> T n(String str, a aVar) {
        try {
            return (T) d(str).invoke(null, aVar);
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e8);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e9);
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e10);
        } catch (InvocationTargetException e11) {
            if (e11.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e11.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e11);
        }
    }

    public abstract int o();

    public int p(int i8, int i9) {
        return !m(i9) ? i8 : o();
    }

    public abstract <T extends Parcelable> T q();

    public <T extends Parcelable> T r(T t7, int i8) {
        return !m(i8) ? t7 : (T) q();
    }

    public abstract String s();

    public String t(String str, int i8) {
        return !m(i8) ? str : s();
    }

    public <T extends c> T u() {
        String strS = s();
        if (strS == null) {
            return null;
        }
        return (T) n(strS, b());
    }

    public <T extends c> T v(T t7, int i8) {
        return !m(i8) ? t7 : (T) u();
    }

    public abstract void w(int i8);

    public void x(boolean z7, boolean z8) {
    }

    public abstract void y(boolean z7);

    public void z(boolean z7, int i8) {
        w(i8);
        y(z7);
    }
}
