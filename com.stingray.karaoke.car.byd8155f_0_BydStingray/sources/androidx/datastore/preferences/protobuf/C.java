package androidx.datastore.preferences.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class C extends E {
    public static final Class c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public static List d(int i7, long j3, Object obj) {
        List list;
        List list2 = (List) r0.c.i(j3, obj);
        if (list2.isEmpty()) {
            List a10 = list2 instanceof B ? new A(i7) : new ArrayList(i7);
            r0.s(j3, obj, a10);
            return a10;
        }
        if (c.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + i7);
            arrayList.addAll(list2);
            r0.s(j3, obj, arrayList);
            list = arrayList;
        } else {
            if (!(list2 instanceof m0)) {
                return list2;
            }
            A a11 = new A(list2.size() + i7);
            a11.addAll((m0) list2);
            r0.s(j3, obj, a11);
            list = a11;
        }
        return list;
    }

    @Override // androidx.datastore.preferences.protobuf.E
    public final void a(long j3, Object obj) {
        Object objUnmodifiableList;
        List list = (List) r0.c.i(j3, obj);
        if (list instanceof B) {
            objUnmodifiableList = ((B) list).b();
        } else {
            if (c.isAssignableFrom(list.getClass())) {
                return;
            } else {
                objUnmodifiableList = Collections.unmodifiableList(list);
            }
        }
        r0.s(j3, obj, objUnmodifiableList);
    }

    @Override // androidx.datastore.preferences.protobuf.E
    public final void b(long j3, Object obj, Object obj2) {
        List list = (List) r0.c.i(j3, obj2);
        List listD = d(list.size(), j3, obj);
        int size = listD.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            listD.addAll(list);
        }
        if (size > 0) {
            list = listD;
        }
        r0.s(j3, obj, list);
    }

    @Override // androidx.datastore.preferences.protobuf.E
    public final List c(long j3, Object obj) {
        return d(10, j3, obj);
    }
}
