package K7;

import Q7.InterfaceC0650c;
import W7.AbstractC0839c;
import java.lang.reflect.Method;
import n7.AbstractC1996k;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class v0 {
    public static final C8.d l = new C8.d(4);

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0105  */
    /* JADX WARN: Type inference failed for: r1v34, types: [java.lang.Object, m7.f] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final L7.e f(K7.g0 r6, boolean r7) throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K7.v0.f(K7.g0, boolean):L7.e");
    }

    public static final String h(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        A7.m.e(parameterTypes, "parameterTypes");
        sb.append(AbstractC1996k.V(parameterTypes, "(", ")", C0377b.f3773y));
        Class<?> returnType = method.getReturnType();
        A7.m.e(returnType, "returnType");
        sb.append(AbstractC0839c.b(returnType));
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final L7.t j(K7.g0 r4, boolean r5, java.lang.reflect.Field r6) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K7.v0.j(K7.g0, boolean, java.lang.reflect.Field):L7.t");
    }

    public static final boolean l(g0 g0Var) {
        return !F8.Y.e(g0Var.t().n().b());
    }

    public static final Object m(g0 g0Var) {
        A7.m.f(g0Var, "<this>");
        m0 m0VarT = g0Var.t();
        return w0.c.G(m0VarT.f3814p, m0VarT.n());
    }

    public static t0 n(InterfaceC0650c interfaceC0650c, InterfaceC2772a interfaceC2772a) {
        if (interfaceC2772a != null) {
            return new t0(interfaceC0650c, interfaceC2772a);
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "kotlin/reflect/jvm/internal/ReflectProperties", "lazySoft"));
    }

    public abstract String i();
}
