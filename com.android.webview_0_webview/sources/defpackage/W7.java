package defpackage;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class W7 {
    public static Class c(Class cls) {
        return Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
    }

    public abstract void a();

    public abstract W7 b();

    public abstract boolean d(int i);

    public int e(int i, int i2) {
        return !d(i2) ? i : ((X7) this).b.readInt();
    }

    public Parcelable f(Parcelable parcelable, int i) {
        return !d(i) ? parcelable : ((X7) this).b.readParcelable(X7.class.getClassLoader());
    }

    public Y7 g() {
        String string = ((X7) this).b.readString();
        if (string == null) {
            return null;
        }
        try {
            return (Y7) Class.forName(string, true, W7.class.getClassLoader()).getDeclaredMethod("read", W7.class).invoke(null, b());
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

    public abstract void h(int i);

    public void i(int i, int i2) {
        h(i2);
        ((X7) this).b.writeInt(i);
    }

    public void j(Parcelable parcelable, int i) {
        h(i);
        ((X7) this).b.writeParcelable(parcelable, 0);
    }

    public void k(Y7 y7) {
        if (y7 == null) {
            ((X7) this).b.writeString(null);
            return;
        }
        try {
            ((X7) this).b.writeString(c(y7.getClass()).getName());
            W7 w7B = b();
            try {
                c(y7.getClass()).getDeclaredMethod("write", y7.getClass(), W7.class).invoke(null, y7, w7B);
                w7B.a();
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
            throw new RuntimeException(y7.getClass().getSimpleName() + " does not have a Parcelizer", e5);
        }
    }
}
