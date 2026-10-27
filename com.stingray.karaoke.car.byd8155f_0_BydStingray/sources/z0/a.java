package Z0;

import A7.D;
import A7.m;
import G7.c;
import G7.d;
import Q8.o;
import T.C0761q;
import h6.n;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import n7.AbstractC2000o;
import n7.z;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [int] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    public static Method a(Method[] methodArr, String str, Class... clsArr) throws NoSuchMethodException {
        Method method;
        int length = methodArr.length;
        boolean z6 = false;
        int i7 = 0;
        loop0: while (true) {
            if (i7 >= length) {
                method = null;
                break;
            }
            method = methodArr[i7];
            if (str.equals(method.getName()) || o.z0(method.getName(), str.concat("-"), z6)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] clsArr2 = (Class[]) Arrays.copyOf(clsArr, clsArr.length);
                if (parameterTypes.length == clsArr2.length) {
                    ArrayList arrayList = new ArrayList(parameterTypes.length);
                    int length2 = parameterTypes.length;
                    boolean z10 = z6;
                    ?? r12 = z10;
                    for (?? r11 = z10; r11 < length2; r11++) {
                        Class<?> cls = parameterTypes[r11];
                        int i10 = r12 + 1;
                        Class<?> cls2 = clsArr2[r12];
                        arrayList.add(Boolean.valueOf(z.l(cls).equals(z.l(cls2)) || cls.isAssignableFrom(cls2)));
                        r12 = i10;
                    }
                    if (!arrayList.isEmpty()) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (!((Boolean) it.next()).booleanValue()) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                continue;
            }
            i7++;
            z6 = false;
        }
        if (method != null) {
            return method;
        }
        throw new NoSuchMethodException(str.concat(" not found"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Method b(Class cls, String str, Object... objArr) {
        GenericDeclaration genericDeclaration;
        ArrayList arrayList = new ArrayList();
        int length = objArr.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                break;
            }
            Object obj = objArr[i7];
            genericDeclaration = obj != null ? obj.getClass() : null;
            if (genericDeclaration != null) {
                arrayList.add(genericDeclaration);
            }
            i7++;
        }
        Class[] clsArr = (Class[]) arrayList.toArray(new Class[0]);
        try {
            try {
                int length2 = clsArr.length;
                int iCeil = length2 == 0 ? 1 : (int) Math.ceil(((double) length2) / 10.0d);
                Class cls2 = Integer.TYPE;
                d dVarB0 = AbstractC2673a.b0(0, iCeil);
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(dVarB0, 10));
                c it = dVarB0.iterator();
                while (it.f2715n) {
                    it.b();
                    arrayList2.add(cls2);
                }
                Class[] clsArr2 = (Class[]) arrayList2.toArray(new Class[0]);
                Method[] declaredMethods = cls.getDeclaredMethods();
                D d = new D(3);
                ArrayList arrayList3 = d.l;
                d.j(clsArr);
                d.f(C0761q.class);
                d.j(clsArr2);
                return a(declaredMethods, str, (Class[]) arrayList3.toArray(new Class[arrayList3.size()]));
            } catch (ReflectiveOperationException unused) {
                for (Method method : cls.getDeclaredMethods()) {
                    if (!m.a(method.getName(), str)) {
                        if (!o.z0(method.getName(), str + '-', false)) {
                        }
                    }
                    genericDeclaration = method;
                    break;
                }
                return genericDeclaration;
            }
        } catch (ReflectiveOperationException unused2) {
            return genericDeclaration;
        }
    }

    public static void c(String str, String str2, C0761q c0761q, Object... objArr) {
        try {
            Class<?> cls = Class.forName(str);
            Method methodB = b(cls, str2, Arrays.copyOf(objArr, objArr.length));
            if (methodB != null) {
                methodB.setAccessible(true);
                if (Modifier.isStatic(methodB.getModifiers())) {
                    d(methodB, null, c0761q, Arrays.copyOf(objArr, objArr.length));
                    return;
                } else {
                    d(methodB, cls.getConstructor(null).newInstance(null), c0761q, Arrays.copyOf(objArr, objArr.length));
                    return;
                }
            }
            throw new NoSuchMethodException("Composable " + str + '.' + str2 + " not found");
        } catch (Exception e10) {
            n.u("PreviewLogger", "Failed to invoke Composable Method '" + str + '.' + str2 + '\'', null);
            throw e10;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(java.lang.reflect.Method r10, java.lang.Object r11, T.C0761q r12, java.lang.Object... r13) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z0.a.d(java.lang.reflect.Method, java.lang.Object, T.q, java.lang.Object[]):void");
    }
}
