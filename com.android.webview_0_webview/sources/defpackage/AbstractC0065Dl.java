package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: Dl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0065Dl extends AbstractC0315Rk {
    public static Map defaultInstanceMap = new ConcurrentHashMap();
    public C1562tm unknownFields = C1562tm.f;
    public int memoizedSerializedSize = -1;

    public static AbstractC0065Dl d(Class cls) {
        AbstractC0065Dl abstractC0065DlE = (AbstractC0065Dl) defaultInstanceMap.get(cls);
        if (abstractC0065DlE == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                abstractC0065DlE = (AbstractC0065Dl) defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (abstractC0065DlE == null) {
            abstractC0065DlE = ((AbstractC0065Dl) AbstractC0066Dm.a(cls)).e();
            if (abstractC0065DlE == null) {
                throw new IllegalStateException();
            }
            defaultInstanceMap.put(cls, abstractC0065DlE);
        }
        return abstractC0065DlE;
    }

    public static Object g(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static final boolean i(AbstractC0065Dl abstractC0065Dl, boolean z) {
        byte bByteValue = ((Byte) abstractC0065Dl.c(EnumC0047Cl.GET_MEMOIZED_IS_INITIALIZED, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zA = C1011jm.c.b(abstractC0065Dl).a(abstractC0065Dl);
        if (z) {
            abstractC0065Dl.c(EnumC0047Cl.SET_MEMOIZED_IS_INITIALIZED, zA ? abstractC0065Dl : null, null);
        }
        return zA;
    }

    public abstract Object c(EnumC0047Cl enumC0047Cl, Object obj, Object obj2);

    public final AbstractC0065Dl e() {
        return (AbstractC0065Dl) c(EnumC0047Cl.GET_DEFAULT_INSTANCE, null, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (e().getClass().isInstance(obj)) {
            return C1011jm.c.b(this).d(this, (AbstractC0065Dl) obj);
        }
        return false;
    }

    public int f() {
        if (this.memoizedSerializedSize == -1) {
            this.memoizedSerializedSize = C1011jm.c.b(this).e(this);
        }
        return this.memoizedSerializedSize;
    }

    public final boolean h() {
        return i(this, true);
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int i2 = C1011jm.c.b(this).i(this);
        this.memoizedHashCode = i2;
        return i2;
    }

    public void j(AbstractC1342pl abstractC1342pl) {
        InterfaceC1178mm interfaceC1178mmB = C1011jm.c.b(this);
        C1397ql c1397ql = abstractC1342pl.a;
        if (c1397ql == null) {
            c1397ql = new C1397ql(abstractC1342pl);
        }
        interfaceC1178mmB.g(this, c1397ql);
    }

    public String toString() {
        String string = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(string);
        AbstractC0565bm.c(this, sb, 0);
        return sb.toString();
    }
}
