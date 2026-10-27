package p8;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: p8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2138m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC2136k f20986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f20987b;
    public final AbstractC2139n c;
    public final C2137l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f20988e;

    public C2138m(AbstractC2136k abstractC2136k, Object obj, AbstractC2139n abstractC2139n, C2137l c2137l, Class cls) {
        if (abstractC2136k == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
        if (c2137l.f20984m == EnumC2124L.f20952q && abstractC2139n == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        }
        this.f20986a = abstractC2136k;
        this.f20987b = obj;
        this.c = abstractC2139n;
        this.d = c2137l;
        if (!InterfaceC2140o.class.isAssignableFrom(cls)) {
            this.f20988e = null;
            return;
        }
        try {
            this.f20988e = cls.getMethod("valueOf", Integer.TYPE);
        } catch (NoSuchMethodException e10) {
            String name = cls.getName();
            StringBuilder sb = new StringBuilder(name.length() + 52);
            sb.append("Generated message class \"");
            sb.append(name);
            sb.append("\" missing method \"valueOf\".");
            throw new RuntimeException(sb.toString(), e10);
        }
    }

    public final Object a(Object obj) {
        if (this.d.f20984m.l != EnumC2125M.f20962t) {
            return obj;
        }
        try {
            return this.f20988e.invoke(null, (Integer) obj);
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

    public final Object b(Object obj) {
        return this.d.f20984m.l == EnumC2125M.f20962t ? Integer.valueOf(((InterfaceC2140o) obj).a()) : obj;
    }
}
