package K7;

import A7.InterfaceC0057e;
import W7.AbstractC0839c;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class E implements InterfaceC0057e {
    public static final Q8.g l = new Q8.g("<v#(\\d+)>");

    public static Method o(Class cls, String str, Class[] clsArr, Class cls2, boolean z6) {
        Class clsJ0;
        Method methodO;
        if (z6) {
            clsArr[0] = cls;
        }
        Method methodR = r(cls, str, clsArr, cls2);
        if (methodR != null) {
            return methodR;
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null && (methodO = o(superclass, str, clsArr, cls2, z6)) != null) {
            return methodO;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        A7.m.e(interfaces, "interfaces");
        for (Class<?> cls3 : interfaces) {
            A7.m.e(cls3, "superInterface");
            Method methodO2 = o(cls3, str, clsArr, cls2, z6);
            if (methodO2 != null) {
                return methodO2;
            }
            if (z6 && (clsJ0 = l9.d.j0(AbstractC0839c.d(cls3), cls3.getName().concat("$DefaultImpls"))) != null) {
                clsArr[0] = cls3;
                Method methodR2 = r(clsJ0, str, clsArr, cls2);
                if (methodR2 != null) {
                    return methodR2;
                }
            }
        }
        return null;
    }

    public static Constructor q(Class cls, ArrayList arrayList) {
        try {
            Class[] clsArr = (Class[]) arrayList.toArray(new Class[0]);
            return cls.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method r(Class cls, String str, Class[] clsArr, Class cls2) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            if (A7.m.a(declaredMethod.getReturnType(), cls2)) {
                return declaredMethod;
            }
            Method[] declaredMethods = cls.getDeclaredMethods();
            A7.m.e(declaredMethods, "declaredMethods");
            for (Method method : declaredMethods) {
                if (A7.m.a(method.getName(), str) && A7.m.a(method.getReturnType(), cls2) && Arrays.equals(method.getParameterTypes(), clsArr)) {
                    return method;
                }
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final void f(ArrayList arrayList, String str, boolean z6) {
        ArrayList arrayListN = n(str);
        arrayList.addAll(arrayListN);
        int size = (arrayListN.size() + 31) / 32;
        for (int i7 = 0; i7 < size; i7++) {
            Class cls = Integer.TYPE;
            A7.m.e(cls, "TYPE");
            arrayList.add(cls);
        }
        if (!z6) {
            arrayList.add(Object.class);
        } else {
            arrayList.remove(DefaultConstructorMarker.class);
            arrayList.add(DefaultConstructorMarker.class);
        }
    }

    public final Method g(String str, String str2) throws ClassNotFoundException {
        Method methodO;
        A7.m.f(str, "name");
        A7.m.f(str2, "desc");
        if (str.equals("<init>")) {
            return null;
        }
        Class[] clsArr = (Class[]) n(str2).toArray(new Class[0]);
        Class clsP = p(str2, Q8.h.J0(str2, ')', 0, false, 6) + 1, str2.length());
        Method methodO2 = o(l(), str, clsArr, clsP, false);
        if (methodO2 != null) {
            return methodO2;
        }
        if (!l().isInterface() || (methodO = o(Object.class, str, clsArr, clsP, false)) == null) {
            return null;
        }
        return methodO;
    }

    public abstract Collection h();

    public abstract Collection i(C2061f c2061f);

    public abstract Q7.L j(int i7);

    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List k(y8.n r8, int r9) {
        /*
            r7 = this;
            java.lang.String r0 = "scope"
            A7.m.f(r8, r0)
            java.lang.String r0 = "belonginess"
            A7.k.p(r9, r0)
            K7.D r0 = new K7.D
            r0.<init>(r7)
            r7 = 0
            r1 = 3
            java.util.Collection r8 = h6.f.i(r8, r7, r1)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r8 = r8.iterator()
        L20:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L60
            java.lang.Object r2 = r8.next()
            Q7.j r2 = (Q7.InterfaceC0657j) r2
            boolean r3 = r2 instanceof Q7.InterfaceC0650c
            if (r3 == 0) goto L59
            r3 = r2
            Q7.c r3 = (Q7.InterfaceC0650c) r3
            Q7.n r4 = r3.e()
            Q7.n r5 = Q7.AbstractC0662o.f7194h
            boolean r4 = A7.m.a(r4, r5)
            if (r4 != 0) goto L59
            int r3 = r3.N()
            r4 = 2
            r5 = 0
            r6 = 1
            if (r3 == r4) goto L4a
            r3 = r6
            goto L4b
        L4a:
            r3 = r5
        L4b:
            if (r9 != r6) goto L4e
            r5 = r6
        L4e:
            if (r3 != r5) goto L59
            m7.q r3 = m7.q.f19267a
            java.lang.Object r2 = r2.S(r0, r3)
            K7.r r2 = (K7.r) r2
            goto L5a
        L59:
            r2 = r7
        L5a:
            if (r2 == 0) goto L20
            r1.add(r2)
            goto L20
        L60:
            java.util.List r7 = n7.AbstractC1998m.z0(r1)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: K7.E.k(y8.n, int):java.util.List");
    }

    public Class l() {
        Class clsD = d();
        List list = AbstractC0839c.f9878a;
        A7.m.f(clsD, "<this>");
        Class cls = (Class) AbstractC0839c.c.get(clsD);
        return cls == null ? d() : cls;
    }

    public abstract Collection m(C2061f c2061f);

    public final ArrayList n(String str) {
        int iJ0;
        ArrayList arrayList = new ArrayList();
        int i7 = 1;
        while (str.charAt(i7) != ')') {
            int i10 = i7;
            while (str.charAt(i10) == '[') {
                i10++;
            }
            char cCharAt = str.charAt(i10);
            if (Q8.h.D0("VZCBSIFJD", cCharAt)) {
                iJ0 = i10 + 1;
            } else {
                if (cCharAt != 'L') {
                    throw new r0("Unknown type prefix in the method signature: ".concat(str));
                }
                iJ0 = Q8.h.J0(str, ';', i7, false, 4) + 1;
            }
            arrayList.add(p(str, i7, iJ0));
            i7 = iJ0;
        }
        return arrayList;
    }

    public final Class p(String str, int i7, int i10) throws ClassNotFoundException {
        char cCharAt = str.charAt(i7);
        if (cCharAt == 'L') {
            ClassLoader classLoaderD = AbstractC0839c.d(d());
            String strSubstring = str.substring(i7 + 1, i10 - 1);
            A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            Class<?> clsLoadClass = classLoaderD.loadClass(Q8.o.w0(strSubstring, '/', '.'));
            A7.m.e(clsLoadClass, "jClass.safeClassLoader.l…d - 1).replace('/', '.'))");
            return clsLoadClass;
        }
        if (cCharAt == '[') {
            Class clsP = p(str, i7 + 1, i10);
            C2058c c2058c = A0.f3718a;
            A7.m.f(clsP, "<this>");
            return Array.newInstance((Class<?>) clsP, 0).getClass();
        }
        if (cCharAt == 'V') {
            Class cls = Void.TYPE;
            A7.m.e(cls, "TYPE");
            return cls;
        }
        if (cCharAt == 'Z') {
            return Boolean.TYPE;
        }
        if (cCharAt == 'C') {
            return Character.TYPE;
        }
        if (cCharAt == 'B') {
            return Byte.TYPE;
        }
        if (cCharAt == 'S') {
            return Short.TYPE;
        }
        if (cCharAt == 'I') {
            return Integer.TYPE;
        }
        if (cCharAt == 'F') {
            return Float.TYPE;
        }
        if (cCharAt == 'J') {
            return Long.TYPE;
        }
        if (cCharAt == 'D') {
            return Double.TYPE;
        }
        throw new r0("Unknown type prefix in the method signature: ".concat(str));
    }
}
