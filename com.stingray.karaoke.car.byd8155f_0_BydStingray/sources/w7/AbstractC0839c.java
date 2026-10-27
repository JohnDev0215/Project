package W7;

import H7.InterfaceC0318c;
import K7.InterfaceC0381e;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import n7.AbstractC1996k;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import z7.InterfaceC2772a;
import z7.InterfaceC2773b;
import z7.InterfaceC2774c;
import z7.InterfaceC2775d;
import z7.InterfaceC2776e;
import z7.InterfaceC2777f;
import z7.InterfaceC2778g;
import z7.InterfaceC2779h;
import z7.InterfaceC2780i;
import z7.InterfaceC2781j;
import z7.InterfaceC2782k;
import z7.InterfaceC2783l;
import z7.InterfaceC2784m;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;
import z7.InterfaceC2787p;
import z7.InterfaceC2788q;
import z7.InterfaceC2789r;
import z7.InterfaceC2790s;
import z7.InterfaceC2791t;
import z7.InterfaceC2792u;

/* JADX INFO: renamed from: W7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0839c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f9878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f9879b;
    public static final Map c;
    public static final Map d;

    static {
        int i7 = 0;
        Class cls = Boolean.TYPE;
        A7.B b10 = A7.A.f443a;
        List<InterfaceC0318c> listD = AbstractC1999n.D(b10.b(cls), b10.b(Byte.TYPE), b10.b(Character.TYPE), b10.b(Double.TYPE), b10.b(Float.TYPE), b10.b(Integer.TYPE), b10.b(Long.TYPE), b10.b(Short.TYPE));
        f9878a = listD;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listD, 10));
        for (InterfaceC0318c interfaceC0318c : listD) {
            arrayList.add(new m7.i(n7.z.j(interfaceC0318c), n7.z.k(interfaceC0318c)));
        }
        f9879b = n7.z.x(arrayList);
        List<InterfaceC0318c> list = f9878a;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(list, 10));
        for (InterfaceC0318c interfaceC0318c2 : list) {
            arrayList2.add(new m7.i(n7.z.k(interfaceC0318c2), n7.z.j(interfaceC0318c2)));
        }
        c = n7.z.x(arrayList2);
        List listD2 = AbstractC1999n.D(InterfaceC2772a.class, InterfaceC2782k.class, InterfaceC2785n.class, InterfaceC2786o.class, InterfaceC2787p.class, InterfaceC2788q.class, InterfaceC2789r.class, InterfaceC2790s.class, InterfaceC2791t.class, InterfaceC2792u.class, InterfaceC2773b.class, InterfaceC2774c.class, InterfaceC0381e.class, InterfaceC2775d.class, InterfaceC2776e.class, InterfaceC2777f.class, InterfaceC2778g.class, InterfaceC2779h.class, InterfaceC2780i.class, InterfaceC2781j.class, InterfaceC2783l.class, InterfaceC2784m.class, InterfaceC0381e.class);
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(listD2, 10));
        for (Object obj : listD2) {
            int i10 = i7 + 1;
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            arrayList3.add(new m7.i((Class) obj, Integer.valueOf(i7)));
            i7 = i10;
        }
        d = n7.z.x(arrayList3);
    }

    public static final C2057b a(Class cls) {
        C2057b c2057bA;
        A7.m.f(cls, "<this>");
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("Can't compute ClassId for primitive type: " + cls);
        }
        if (cls.isArray()) {
            throw new IllegalArgumentException("Can't compute ClassId for array type: " + cls);
        }
        if (cls.getEnclosingMethod() == null && cls.getEnclosingConstructor() == null && cls.getSimpleName().length() != 0) {
            Class<?> declaringClass = cls.getDeclaringClass();
            return (declaringClass == null || (c2057bA = a(declaringClass)) == null) ? C2057b.j(new C2058c(cls.getName())) : c2057bA.d(C2061f.e(cls.getSimpleName()));
        }
        C2058c c2058c = new C2058c(cls.getName());
        return new C2057b(c2058c.e(), C2058c.j(c2058c.f()), true);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final String b(Class cls) {
        A7.m.f(cls, "<this>");
        if (!cls.isPrimitive()) {
            if (cls.isArray()) {
                return Q8.o.w0(cls.getName(), '.', '/');
            }
            return "L" + Q8.o.w0(cls.getName(), '.', '/') + ';';
        }
        String name = cls.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    return "D";
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    return "I";
                }
                break;
            case 3039496:
                if (name.equals("byte")) {
                    return "B";
                }
                break;
            case 3052374:
                if (name.equals("char")) {
                    return "C";
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    return "J";
                }
                break;
            case 3625364:
                if (name.equals("void")) {
                    return "V";
                }
                break;
            case 64711720:
                if (name.equals("boolean")) {
                    return "Z";
                }
                break;
            case 97526364:
                if (name.equals("float")) {
                    return "F";
                }
                break;
            case 109413500:
                if (name.equals("short")) {
                    return "S";
                }
                break;
        }
        throw new IllegalArgumentException("Unsupported primitive type: " + cls);
    }

    public static final List c(Type type) {
        A7.m.f(type, "<this>");
        if (!(type instanceof ParameterizedType)) {
            return n7.u.l;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getOwnerType() != null) {
            return P8.m.i0(new P8.h(P8.m.d0(type, C0838b.f9874n), C0838b.o, P8.q.f5483t));
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        A7.m.e(actualTypeArguments, "actualTypeArguments");
        return AbstractC1996k.b0(actualTypeArguments);
    }

    public static final ClassLoader d(Class cls) {
        A7.m.f(cls, "<this>");
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        A7.m.e(systemClassLoader, "getSystemClassLoader()");
        return systemClassLoader;
    }
}
