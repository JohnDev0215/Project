package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final n.a<String, Method> f3204a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final n.a<String, Method> f3205b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final n.a<String, Class> f3206c;

    public a(n.a<String, Method> aVar, n.a<String, Method> aVar2, n.a<String, Class> aVar3) {
        this.f3204a = aVar;
        this.f3205b = aVar2;
        this.f3206c = aVar3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void N(g1.b bVar) {
        try {
            I(c(bVar.getClass()).getName());
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException(bVar.getClass().getSimpleName() + " does not have a Parcelizer", e7);
        }
    }

    private Class c(Class<? extends g1.b> cls) throws ClassNotFoundException {
        Class cls2 = this.f3206c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f3206c.put(cls.getName(), cls3);
        return cls3;
    }

    private Method d(String str) throws NoSuchMethodException {
        Method method = this.f3204a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        this.f3204a.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Method e(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        Method method = this.f3205b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class clsC = c(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsC.getDeclaredMethod("write", cls, a.class);
        this.f3205b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    protected abstract void A(byte[] bArr);

    public void B(byte[] bArr, int i7) {
        w(i7);
        A(bArr);
    }

    protected abstract void C(CharSequence charSequence);

    public void D(CharSequence charSequence, int i7) {
        w(i7);
        C(charSequence);
    }

    protected abstract void E(int i7);

    public void F(int i7, int i8) {
        w(i8);
        E(i7);
    }

    protected abstract void G(Parcelable parcelable);

    public void H(Parcelable parcelable, int i7) {
        w(i7);
        G(parcelable);
    }

    protected abstract void I(String str);

    public void J(String str, int i7) {
        w(i7);
        I(str);
    }

    protected <T extends g1.b> void K(T t6, a aVar) {
        try {
            e(t6.getClass()).invoke(null, t6, aVar);
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e7);
        } catch (IllegalAccessException e8) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e8);
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e9);
        } catch (InvocationTargetException e10) {
            if (!(e10.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e10);
            }
            throw ((RuntimeException) e10.getCause());
        }
    }

    protected void L(g1.b bVar) {
        if (bVar == null) {
            I(null);
            return;
        }
        N(bVar);
        a aVarB = b();
        K(bVar, aVarB);
        aVarB.a();
    }

    public void M(g1.b bVar, int i7) {
        w(i7);
        L(bVar);
    }

    protected abstract void a();

    protected abstract a b();

    public boolean f() {
        return false;
    }

    protected abstract boolean g();

    public boolean h(boolean z6, int i7) {
        return !m(i7) ? z6 : g();
    }

    protected abstract byte[] i();

    public byte[] j(byte[] bArr, int i7) {
        return !m(i7) ? bArr : i();
    }

    protected abstract CharSequence k();

    public CharSequence l(CharSequence charSequence, int i7) {
        return !m(i7) ? charSequence : k();
    }

    protected abstract boolean m(int i7);

    protected <T extends g1.b> T n(String str, a aVar) {
        try {
            return (T) d(str).invoke(null, aVar);
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e7);
        } catch (IllegalAccessException e8) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e8);
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e9);
        } catch (InvocationTargetException e10) {
            if (e10.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e10.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e10);
        }
    }

    protected abstract int o();

    public int p(int i7, int i8) {
        return !m(i8) ? i7 : o();
    }

    protected abstract <T extends Parcelable> T q();

    public <T extends Parcelable> T r(T t6, int i7) {
        return !m(i7) ? t6 : (T) q();
    }

    protected abstract String s();

    public String t(String str, int i7) {
        return !m(i7) ? str : s();
    }

    protected <T extends g1.b> T u() {
        String strS = s();
        if (strS == null) {
            return null;
        }
        return (T) n(strS, b());
    }

    public <T extends g1.b> T v(T t6, int i7) {
        return !m(i7) ? t6 : (T) u();
    }

    protected abstract void w(int i7);

    public void x(boolean z6, boolean z7) {
    }

    protected abstract void y(boolean z6);

    public void z(boolean z6, int i7) {
        w(i7);
        y(z6);
    }
}
