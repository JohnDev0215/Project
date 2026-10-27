package y8;

import h2.AbstractC1347d;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final l c = new l();
    public static final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f24189e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f24190f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f24191g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f24192h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f24193i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int f24194j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int f24195k;
    public static final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f24196m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f24197n;
    public static final f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final f f24198p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final f f24199q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final ArrayList f24200r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final ArrayList f24201s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f24202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f24203b;

    static {
        e eVar;
        int i7 = d;
        int i10 = i7 << 1;
        f24189e = i7;
        int i11 = i7 << 2;
        f24190f = i10;
        int i12 = i7 << 3;
        f24191g = i11;
        int i13 = i7 << 4;
        f24192h = i12;
        int i14 = i7 << 5;
        f24193i = i13;
        f24194j = i14;
        d = i7 << 7;
        int i15 = (i7 << 6) - 1;
        f24195k = i15;
        int i16 = i7 | i10 | i11;
        l = i16;
        f24196m = new f(i15);
        f24197n = new f(i13 | i14);
        new f(i7);
        new f(i10);
        new f(i11);
        o = new f(i16);
        new f(i12);
        f24198p = new f(i13);
        f24199q = new f(i14);
        new f(i10 | i13 | i14);
        Field[] fields = f.class.getFields();
        A7.m.e(fields, "T::class.java.fields");
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            e eVar2 = null;
            if (!it.hasNext()) {
                break;
            }
            Field field2 = (Field) it.next();
            Object obj = field2.get(null);
            f fVar = obj instanceof f ? (f) obj : null;
            if (fVar != null) {
                String name = field2.getName();
                A7.m.e(name, "field.name");
                eVar2 = new e(fVar.f24203b, name);
            }
            if (eVar2 != null) {
                arrayList2.add(eVar2);
            }
        }
        f24200r = arrayList2;
        Field[] fields2 = f.class.getFields();
        A7.m.e(fields2, "T::class.java.fields");
        ArrayList arrayList3 = new ArrayList();
        for (Field field3 : fields2) {
            if (Modifier.isStatic(field3.getModifiers())) {
                arrayList3.add(field3);
            }
        }
        ArrayList<Field> arrayList4 = new ArrayList();
        for (Object obj2 : arrayList3) {
            if (A7.m.a(((Field) obj2).getType(), Integer.TYPE)) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Field field4 : arrayList4) {
            Object obj3 = field4.get(null);
            A7.m.d(obj3, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue = ((Integer) obj3).intValue();
            if (iIntValue == ((-iIntValue) & iIntValue)) {
                String name2 = field4.getName();
                A7.m.e(name2, "field.name");
                eVar = new e(iIntValue, name2);
            } else {
                eVar = null;
            }
            if (eVar != null) {
                arrayList5.add(eVar);
            }
        }
        f24201s = arrayList5;
    }

    public f(int i7, List list) {
        A7.m.f(list, "excludes");
        this.f24202a = list;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            i7 &= ~((d) it.next()).a();
        }
        this.f24203b = i7;
    }

    public final boolean a(int i7) {
        return (this.f24203b & i7) != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!f.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        A7.m.d(obj, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter");
        f fVar = (f) obj;
        return A7.m.a(this.f24202a, fVar.f24202a) && this.f24203b == fVar.f24203b;
    }

    public final int hashCode() {
        return (this.f24202a.hashCode() * 31) + this.f24203b;
    }

    public final String toString() {
        Object next;
        Iterator it = f24200r.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((e) next).f24187a == this.f24203b) {
                break;
            }
        }
        e eVar = (e) next;
        String strF0 = eVar != null ? eVar.f24188b : null;
        if (strF0 == null) {
            ArrayList<e> arrayList = f24201s;
            ArrayList arrayList2 = new ArrayList();
            for (e eVar2 : arrayList) {
                String str = a(eVar2.f24187a) ? eVar2.f24188b : null;
                if (str != null) {
                    arrayList2.add(str);
                }
            }
            strF0 = AbstractC1998m.f0(arrayList2, " | ", null, null, null, 62);
        }
        StringBuilder sbJ = AbstractC1347d.j("DescriptorKindFilter(", strF0, ", ");
        sbJ.append(this.f24202a);
        sbJ.append(')');
        return sbJ.toString();
    }

    public /* synthetic */ f(int i7) {
        this(i7, u.l);
    }
}
