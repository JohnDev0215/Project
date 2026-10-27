package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class ys2 {
    public final nb a;
    public final nb b;
    public final nb c;

    public ys2(nb nbVar, nb nbVar2, nb nbVar3) {
        this.a = nbVar;
        this.b = nbVar2;
        this.c = nbVar3;
    }

    public abstract zs2 a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        nb nbVar = this.c;
        Class cls2 = (Class) nbVar.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        nbVar.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        nb nbVar = this.a;
        Method method = (Method) nbVar.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, ys2.class.getClassLoader()).getDeclaredMethod("read", ys2.class);
        nbVar.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        nb nbVar = this.b;
        Method method = (Method) nbVar.get(name);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, ys2.class);
        nbVar.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i);

    public final int f(int i, int i2) {
        return !e(i2) ? i : ((zs2) this).e.readInt();
    }

    public final Parcelable g(Parcelable parcelable, int i) {
        if (!e(i)) {
            return parcelable;
        }
        return ((zs2) this).e.readParcelable(zs2.class.getClassLoader());
    }

    public final at2 h() {
        String string = ((zs2) this).e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (at2) c(string).invoke(null, a());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    public abstract void i(int i);

    public final void j(int i, int i2) {
        i(i2);
        ((zs2) this).e.writeInt(i);
    }

    public final void k(Parcelable parcelable, int i) {
        i(i);
        ((zs2) this).e.writeParcelable(parcelable, 0);
    }

    public final void l(at2 at2Var) {
        if (at2Var == null) {
            ((zs2) this).e.writeString(null);
            return;
        }
        try {
            ((zs2) this).e.writeString(b(at2Var.getClass()).getName());
            zs2 zs2VarA = a();
            try {
                d(at2Var.getClass()).invoke(null, at2Var, zs2VarA);
                int i = zs2VarA.i;
                if (i >= 0) {
                    int i2 = zs2VarA.d.get(i);
                    Parcel parcel = zs2VarA.e;
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i2);
                    parcel.writeInt(iDataPosition - i2);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
            } catch (InvocationTargetException e4) {
                if (!(e4.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
                }
                throw ((RuntimeException) e4.getCause());
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(at2Var.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
