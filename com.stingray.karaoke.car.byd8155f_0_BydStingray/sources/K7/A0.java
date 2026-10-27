package K7;

import H7.InterfaceC0317b;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0671y;
import T.C0746i0;
import W7.AbstractC0839c;
import W7.C0840d;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import h8.AbstractC1387d;
import j8.C1579y;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import l8.AbstractC1783a;
import l8.C1790h;
import l8.InterfaceC1788f;
import n7.AbstractC1996k;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import p8.AbstractC2136k;
import v8.AbstractC2564d;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class A0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2058c f3718a = new C2058c("kotlin.jvm.JvmStatic");

    public static final r a(InterfaceC0317b interfaceC0317b) {
        r rVar = interfaceC0317b instanceof r ? (r) interfaceC0317b : null;
        if (rVar != null) {
            return rVar;
        }
        G gB = b(interfaceC0317b);
        return gB != null ? gB : c(interfaceC0317b);
    }

    public static final G b(Object obj) {
        InterfaceC0317b interfaceC0317bF;
        G g7 = obj instanceof G ? (G) obj : null;
        if (g7 != null) {
            return g7;
        }
        A7.i iVar = obj instanceof A7.i ? (A7.i) obj : null;
        if (iVar != null) {
            interfaceC0317bF = iVar.l;
            if (interfaceC0317bF == null) {
                interfaceC0317bF = iVar.f();
                iVar.l = interfaceC0317bF;
            }
        } else {
            interfaceC0317bF = null;
        }
        if (interfaceC0317bF instanceof G) {
            return (G) interfaceC0317bF;
        }
        return null;
    }

    public static final m0 c(Object obj) {
        m0 m0Var = obj instanceof m0 ? (m0) obj : null;
        if (m0Var != null) {
            return m0Var;
        }
        A7.u uVar = obj instanceof A7.u ? (A7.u) obj : null;
        InterfaceC0317b interfaceC0317bJ = uVar != null ? uVar.j() : null;
        if (interfaceC0317bJ instanceof m0) {
            return (m0) interfaceC0317bJ;
        }
        return null;
    }

    public static final ArrayList d(R7.a aVar) throws IllegalAccessException, InvocationTargetException {
        List listJ;
        A7.m.f(aVar, "<this>");
        R7.h hVarK = aVar.k();
        ArrayList<Annotation> arrayList = new ArrayList();
        Iterator it = hVarK.iterator();
        while (true) {
            Annotation annotationI = null;
            if (!it.hasNext()) {
                break;
            }
            R7.b bVar = (R7.b) it.next();
            Q7.N nG = bVar.g();
            if (nG instanceof V7.a) {
                annotationI = ((V7.a) nG).l;
            } else if (nG instanceof V7.g) {
                W7.r rVar = ((V7.g) nG).l;
                C0840d c0840d = rVar instanceof C0840d ? (C0840d) rVar : null;
                if (c0840d != null) {
                    annotationI = c0840d.f9880a;
                }
            } else {
                annotationI = i(bVar);
            }
            if (annotationI != null) {
                arrayList.add(annotationI);
            }
        }
        if (arrayList.isEmpty()) {
            return arrayList;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            if (n7.z.i(n7.z.g((Annotation) it2.next())).getSimpleName().equals("Container")) {
                ArrayList arrayList2 = new ArrayList();
                for (Annotation annotation : arrayList) {
                    Class clsI = n7.z.i(n7.z.g(annotation));
                    if (!clsI.getSimpleName().equals("Container") || clsI.getAnnotation(A7.C.class) == null) {
                        listJ = AbstractC1387d.j(annotation);
                    } else {
                        Object objInvoke = clsI.getDeclaredMethod("value", null).invoke(annotation, null);
                        A7.m.d(objInvoke, "null cannot be cast to non-null type kotlin.Array<out kotlin.Annotation>");
                        listJ = AbstractC1996k.x((Annotation[]) objInvoke);
                    }
                    AbstractC2004s.L(arrayList2, listJ);
                }
                return arrayList2;
            }
        }
        return arrayList;
    }

    public static final Object e(Type type) {
        if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            return null;
        }
        if (type.equals(Boolean.TYPE)) {
            return Boolean.FALSE;
        }
        if (type.equals(Character.TYPE)) {
            return (char) 0;
        }
        if (type.equals(Byte.TYPE)) {
            return (byte) 0;
        }
        if (type.equals(Short.TYPE)) {
            return (short) 0;
        }
        if (type.equals(Integer.TYPE)) {
            return 0;
        }
        if (type.equals(Float.TYPE)) {
            return Float.valueOf(0.0f);
        }
        if (type.equals(Long.TYPE)) {
            return 0L;
        }
        if (type.equals(Double.TYPE)) {
            return Double.valueOf(0.0d);
        }
        if (type.equals(Void.TYPE)) {
            throw new IllegalStateException("Parameter with void type is illegal");
        }
        throw new UnsupportedOperationException("Unknown primitive: " + type);
    }

    public static final InterfaceC0649b f(Class cls, AbstractC2136k abstractC2136k, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, AbstractC1783a abstractC1783a, InterfaceC2785n interfaceC2785n) {
        List list;
        A7.m.f(cls, "moduleAnchor");
        A7.m.f(abstractC2136k, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        A7.m.f(abstractC1783a, "metadataVersion");
        V7.f fVarA = s0.a(cls);
        if (abstractC2136k instanceof C1579y) {
            list = ((C1579y) abstractC2136k).f17494t;
        } else {
            if (!(abstractC2136k instanceof j8.G)) {
                throw new IllegalStateException(("Unsupported message: " + abstractC2136k).toString());
            }
            list = ((j8.G) abstractC2136k).f17123t;
        }
        List list2 = list;
        B8.i iVar = fVarA.f9552a;
        InterfaceC0671y interfaceC0671y = iVar.f763b;
        C1790h c1790h = C1790h.f18862a;
        A7.m.e(list2, "typeParameters");
        return (InterfaceC0649b) interfaceC2785n.invoke(new B8.s(new B8.k(iVar, interfaceC1788f, interfaceC0671y, c0746i0, c1790h, abstractC1783a, null, null, list2)), abstractC2136k);
    }

    public static final T7.u g(InterfaceC0650c interfaceC0650c) {
        A7.m.f(interfaceC0650c, "<this>");
        if (interfaceC0650c.B() == null) {
            return null;
        }
        InterfaceC0657j interfaceC0657jO = interfaceC0650c.o();
        A7.m.d(interfaceC0657jO, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        return ((InterfaceC0652e) interfaceC0657jO).J0();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class h(ClassLoader classLoader, C2057b c2057b, int i7) {
        String str = P7.d.f5417a;
        C2060e c2060eI = c2057b.b().i();
        A7.m.e(c2060eI, "kotlinClassId.asSingleFqName().toUnsafe()");
        C2057b c2057bF = P7.d.f(c2060eI);
        if (c2057bF != null) {
            c2057b = c2057bF;
        }
        String strB = c2057b.g().b();
        String strB2 = c2057b.h().b();
        if (strB.equals("kotlin")) {
            switch (strB2.hashCode()) {
                case -901856463:
                    if (strB2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (strB2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (strB2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (strB2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (strB2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (strB2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (strB2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (strB2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (strB2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (i7 > 0) {
            for (int i10 = 0; i10 < i7; i10++) {
                sb.append("[");
            }
            sb.append("L");
        }
        if (strB.length() > 0) {
            sb.append(strB.concat("."));
        }
        sb.append(Q8.o.w0(strB2, '.', '$'));
        if (i7 > 0) {
            sb.append(";");
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return l9.d.j0(classLoader, string);
    }

    public static final Annotation i(R7.b bVar) {
        InterfaceC0652e interfaceC0652eD = AbstractC2564d.d(bVar);
        Class clsJ = interfaceC0652eD != null ? j(interfaceC0652eD) : null;
        if (clsJ == null) {
            clsJ = null;
        }
        if (clsJ == null) {
            return null;
        }
        Set<Map.Entry> setEntrySet = bVar.c().entrySet();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : setEntrySet) {
            C2061f c2061f = (C2061f) entry.getKey();
            t8.g gVar = (t8.g) entry.getValue();
            ClassLoader classLoader = clsJ.getClassLoader();
            A7.m.e(classLoader, "annotationClass.classLoader");
            Object objK = k(gVar, classLoader);
            m7.i iVar = objK != null ? new m7.i(c2061f.b(), objK) : null;
            if (iVar != null) {
                arrayList.add(iVar);
            }
        }
        Map mapX = n7.z.x(arrayList);
        Set setKeySet = mapX.keySet();
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(setKeySet, 10));
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList2.add(clsJ.getDeclaredMethod((String) it.next(), null));
        }
        return (Annotation) io.sentry.config.a.T(clsJ, mapX, arrayList2);
    }

    public static final Class j(InterfaceC0652e interfaceC0652e) {
        A7.m.f(interfaceC0652e, "<this>");
        Q7.N nG = interfaceC0652e.g();
        A7.m.e(nG, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (nG instanceof h8.o) {
            return ((h8.o) nG).l.f9548a;
        }
        if (nG instanceof V7.g) {
            W7.r rVar = ((V7.g) nG).l;
            A7.m.d(rVar, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            return ((W7.n) rVar).f9891a;
        }
        C2057b c2057bF = AbstractC2564d.f(interfaceC0652e);
        if (c2057bF == null) {
            return null;
        }
        return h(AbstractC0839c.d(interfaceC0652e.getClass()), c2057bF, 0);
    }

    /* JADX WARN: Incorrect type for immutable var: ssa=boolean[], code=short[], for r8v16, types: [boolean[]] */
    /* JADX WARN: Incorrect type for immutable var: ssa=byte[], code=short[], for r8v18, types: [byte[]] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v54, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r8v14, types: [java.lang.Class[]] */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r8v20, types: [int[]] */
    /* JADX WARN: Type inference failed for: r8v21, types: [float[]] */
    /* JADX WARN: Type inference failed for: r8v22, types: [long[]] */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24, types: [double[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object k(t8.g r7, java.lang.ClassLoader r8) {
        /*
            Method dump skipped, instruction units count: 694
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K7.A0.k(t8.g, java.lang.ClassLoader):java.lang.Object");
    }
}
