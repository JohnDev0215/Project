package a8;

import R7.m;
import R7.n;
import W7.s;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import n7.w;
import n7.z;
import o8.C2057b;
import o8.C2061f;
import t8.C2449b;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f11208a = z.s(new m7.i("PACKAGE", EnumSet.noneOf(n.class)), new m7.i("TYPE", EnumSet.of(n.CLASS, n.FILE)), new m7.i("ANNOTATION_TYPE", EnumSet.of(n.ANNOTATION_CLASS)), new m7.i("TYPE_PARAMETER", EnumSet.of(n.TYPE_PARAMETER)), new m7.i("FIELD", EnumSet.of(n.FIELD)), new m7.i("LOCAL_VARIABLE", EnumSet.of(n.LOCAL_VARIABLE)), new m7.i("PARAMETER", EnumSet.of(n.VALUE_PARAMETER)), new m7.i("CONSTRUCTOR", EnumSet.of(n.CONSTRUCTOR)), new m7.i("METHOD", EnumSet.of(n.FUNCTION, n.PROPERTY_GETTER, n.PROPERTY_SETTER)), new m7.i("TYPE_USE", EnumSet.of(n.TYPE)));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f11209b = z.s(new m7.i("RUNTIME", m.l), new m7.i("CLASS", m.f7541m), new m7.i("SOURCE", m.f7542n));

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.Map] */
    public static C2449b a(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof s) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Iterable iterable = (EnumSet) f11208a.get(C2061f.e(((s) it.next()).f9896b.name()).b());
            if (iterable == null) {
                iterable = w.l;
            }
            AbstractC2004s.L(arrayList2, iterable);
        }
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new t8.i(C2057b.j(N7.n.f4862u), C2061f.e(((n) it2.next()).name())));
        }
        return new C2449b(arrayList3, d.f11207m);
    }
}
