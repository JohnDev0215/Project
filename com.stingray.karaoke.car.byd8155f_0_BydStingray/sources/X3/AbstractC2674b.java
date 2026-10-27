package x3;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import u.C2465e;

/* JADX INFO: renamed from: x3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2674b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2465e f23770a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2465e f23771b;
    public final C2465e c;

    public AbstractC2674b(C2465e c2465e, C2465e c2465e2, C2465e c2465e3) {
        this.f23770a = c2465e;
        this.f23771b = c2465e2;
        this.c = c2465e3;
    }

    public abstract C2675c a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        C2465e c2465e = this.c;
        Class cls2 = (Class) c2465e.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        c2465e.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        C2465e c2465e = this.f23770a;
        Method method = (Method) c2465e.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, AbstractC2674b.class.getClassLoader()).getDeclaredMethod("read", AbstractC2674b.class);
        c2465e.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        C2465e c2465e = this.f23771b;
        Method method = (Method) c2465e.get(name);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, AbstractC2674b.class);
        c2465e.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i7);

    public final int f(int i7, int i10) {
        return !e(i10) ? i7 : ((C2675c) this).f23772e.readInt();
    }

    public final Parcelable g(Parcelable parcelable, int i7) {
        if (!e(i7)) {
            return parcelable;
        }
        return ((C2675c) this).f23772e.readParcelable(C2675c.class.getClassLoader());
    }

    public final InterfaceC2676d h() {
        String string = ((C2675c) this).f23772e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (InterfaceC2676d) c(string).invoke(null, a());
        } catch (ClassNotFoundException e10) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e10);
        } catch (IllegalAccessException e11) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e11);
        } catch (NoSuchMethodException e12) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e12);
        } catch (InvocationTargetException e13) {
            if (e13.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e13.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e13);
        }
    }

    public abstract void i(int i7);

    public final void j(int i7, int i10) {
        i(i10);
        ((C2675c) this).f23772e.writeInt(i7);
    }

    public final void k(Parcelable parcelable, int i7) {
        i(i7);
        ((C2675c) this).f23772e.writeParcelable(parcelable, 0);
    }

    public final void l(InterfaceC2676d interfaceC2676d) {
        if (interfaceC2676d == null) {
            ((C2675c) this).f23772e.writeString(null);
            return;
        }
        try {
            ((C2675c) this).f23772e.writeString(b(interfaceC2676d.getClass()).getName());
            C2675c c2675cA = a();
            try {
                d(interfaceC2676d.getClass()).invoke(null, interfaceC2676d, c2675cA);
                int i7 = c2675cA.f23776i;
                if (i7 >= 0) {
                    int i10 = c2675cA.d.get(i7);
                    Parcel parcel = c2675cA.f23772e;
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i10);
                    parcel.writeInt(iDataPosition - i10);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e10) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e10);
            } catch (IllegalAccessException e11) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e11);
            } catch (NoSuchMethodException e12) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e12);
            } catch (InvocationTargetException e13) {
                if (!(e13.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e13);
                }
                throw ((RuntimeException) e13.getCause());
            }
        } catch (ClassNotFoundException e14) {
            throw new RuntimeException(interfaceC2676d.getClass().getSimpleName().concat(" does not have a Parcelizer"), e14);
        }
    }
}
