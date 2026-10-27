package androidx.datastore.preferences.protobuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0957u extends AbstractC0937a {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Object, AbstractC0957u> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize;
    protected i0 unknownFields;

    public AbstractC0957u() {
        this.memoizedHashCode = 0;
        this.memoizedSerializedSize = -1;
        this.unknownFields = i0.f11858f;
    }

    public static AbstractC0957u f(Class cls) {
        AbstractC0957u abstractC0957u = defaultInstanceMap.get(cls);
        if (abstractC0957u == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                abstractC0957u = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e10) {
                throw new IllegalStateException("Class initialization cannot fail.", e10);
            }
        }
        if (abstractC0957u == null) {
            abstractC0957u = (AbstractC0957u) ((AbstractC0957u) r0.b(cls)).e(6);
            if (abstractC0957u == null) {
                throw new IllegalStateException();
            }
            defaultInstanceMap.put(cls, abstractC0957u);
        }
        return abstractC0957u;
    }

    public static Object g(Method method, AbstractC0937a abstractC0937a, Object... objArr) {
        try {
            return method.invoke(abstractC0937a, objArr);
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e10);
        } catch (InvocationTargetException e11) {
            Throwable cause = e11.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static final boolean h(AbstractC0957u abstractC0957u, boolean z6) {
        byte bByteValue = ((Byte) abstractC0957u.e(1)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        V v10 = V.c;
        v10.getClass();
        boolean zF = v10.a(abstractC0957u.getClass()).f(abstractC0957u);
        if (z6) {
            abstractC0957u.e(2);
        }
        return zF;
    }

    public static void l(Class cls, AbstractC0957u abstractC0957u) {
        abstractC0957u.j();
        defaultInstanceMap.put(cls, abstractC0957u);
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0937a
    public final int a(Y y10) {
        int iG;
        int iG2;
        if (i()) {
            if (y10 == null) {
                V v10 = V.c;
                v10.getClass();
                iG2 = v10.a(getClass()).g(this);
            } else {
                iG2 = y10.g(this);
            }
            if (iG2 >= 0) {
                return iG2;
            }
            throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.e(iG2, "serialized size must be non-negative, was "));
        }
        int i7 = this.memoizedSerializedSize;
        if ((i7 & Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return i7 & Integer.MAX_VALUE;
        }
        if (y10 == null) {
            V v11 = V.c;
            v11.getClass();
            iG = v11.a(getClass()).g(this);
        } else {
            iG = y10.g(this);
        }
        m(iG);
        return iG;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0937a
    public final void b(C0948k c0948k) {
        V v10 = V.c;
        v10.getClass();
        Y yA = v10.a(getClass());
        G g7 = c0948k.c;
        if (g7 == null) {
            g7 = new G(c0948k);
        }
        yA.b(this, g7);
    }

    public final void c() {
        this.memoizedHashCode = 0;
    }

    public final void d() {
        m(Integer.MAX_VALUE);
    }

    public abstract Object e(int i7);

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        V v10 = V.c;
        v10.getClass();
        return v10.a(getClass()).e(this, (AbstractC0957u) obj);
    }

    public final int hashCode() {
        if (i()) {
            V v10 = V.c;
            v10.getClass();
            return v10.a(getClass()).c(this);
        }
        if (this.memoizedHashCode == 0) {
            V v11 = V.c;
            v11.getClass();
            this.memoizedHashCode = v11.a(getClass()).c(this);
        }
        return this.memoizedHashCode;
    }

    public final boolean i() {
        return (this.memoizedSerializedSize & Integer.MIN_VALUE) != 0;
    }

    public final void j() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    public final AbstractC0957u k() {
        return (AbstractC0957u) e(4);
    }

    public final void m(int i7) {
        if (i7 < 0) {
            throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "serialized size must be non-negative, was "));
        }
        this.memoizedSerializedSize = (i7 & Integer.MAX_VALUE) | (this.memoizedSerializedSize & Integer.MIN_VALUE);
    }

    public final String toString() {
        String string = super.toString();
        char[] cArr = N.f11800a;
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(string);
        N.c(this, sb, 0);
        return sb.toString();
    }
}
