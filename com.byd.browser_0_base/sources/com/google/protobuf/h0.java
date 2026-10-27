package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.dj3;
import defpackage.l42;
import defpackage.q61;
import defpackage.yi3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ListFieldSchema.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h0 {
    public static final a a = new a();
    public static final b b = new b();

    /* JADX INFO: compiled from: ListFieldSchema.java */
    public static final class a extends h0 {
        public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

        public static List d(long j, int i, Object obj) {
            List list;
            List list2 = (List) dj3.o(j, obj);
            if (list2.isEmpty()) {
                List g0Var = list2 instanceof q61 ? new g0(i) : ((list2 instanceof l42) && (list2 instanceof c0.e)) ? ((c0.e) list2).h(i) : new ArrayList(i);
                dj3.z(j, obj, g0Var);
                return g0Var;
            }
            if (c.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + i);
                arrayList.addAll(list2);
                dj3.z(j, obj, arrayList);
                list = arrayList;
            } else {
                if (!(list2 instanceof yi3)) {
                    if (!(list2 instanceof l42) || !(list2 instanceof c0.e)) {
                        return list2;
                    }
                    c0.e eVar = (c0.e) list2;
                    if (eVar.o()) {
                        return list2;
                    }
                    c0.e eVarH = eVar.h(list2.size() + i);
                    dj3.z(j, obj, eVarH);
                    return eVarH;
                }
                g0 g0Var2 = new g0(list2.size() + i);
                g0Var2.addAll((yi3) list2);
                dj3.z(j, obj, g0Var2);
                list = g0Var2;
            }
            return list;
        }

        @Override // com.google.protobuf.h0
        public final void a(long j, Object obj) {
            Object objUnmodifiableList;
            List list = (List) dj3.o(j, obj);
            if (list instanceof q61) {
                objUnmodifiableList = ((q61) list).m();
            } else {
                if (c.isAssignableFrom(list.getClass())) {
                    return;
                }
                if ((list instanceof l42) && (list instanceof c0.e)) {
                    c0.e eVar = (c0.e) list;
                    if (eVar.o()) {
                        eVar.e();
                        return;
                    }
                    return;
                }
                objUnmodifiableList = Collections.unmodifiableList(list);
            }
            dj3.z(j, obj, objUnmodifiableList);
        }

        @Override // com.google.protobuf.h0
        public final void b(long j, Object obj, Object obj2) {
            List list = (List) dj3.o(j, obj2);
            List listD = d(j, list.size(), obj);
            int size = listD.size();
            int size2 = list.size();
            if (size > 0 && size2 > 0) {
                listD.addAll(list);
            }
            if (size > 0) {
                list = listD;
            }
            dj3.z(j, obj, list);
        }

        @Override // com.google.protobuf.h0
        public final List c(long j, Object obj) {
            return d(j, 10, obj);
        }
    }

    /* JADX INFO: compiled from: ListFieldSchema.java */
    public static final class b extends h0 {
        @Override // com.google.protobuf.h0
        public final void a(long j, Object obj) {
            ((c0.e) dj3.o(j, obj)).e();
        }

        @Override // com.google.protobuf.h0
        public final void b(long j, Object obj, Object obj2) {
            c0.e eVarH = (c0.e) dj3.o(j, obj);
            c0.e eVar = (c0.e) dj3.o(j, obj2);
            int size = eVarH.size();
            int size2 = eVar.size();
            if (size > 0 && size2 > 0) {
                if (!eVarH.o()) {
                    eVarH = eVarH.h(size2 + size);
                }
                eVarH.addAll(eVar);
            }
            if (size > 0) {
                eVar = eVarH;
            }
            dj3.z(j, obj, eVar);
        }

        @Override // com.google.protobuf.h0
        public final List c(long j, Object obj) {
            c0.e eVar = (c0.e) dj3.o(j, obj);
            if (eVar.o()) {
                return eVar;
            }
            int size = eVar.size();
            c0.e eVarH = eVar.h(size == 0 ? 10 : size * 2);
            dj3.z(j, obj, eVarH);
            return eVarH;
        }
    }

    public abstract void a(long j, Object obj);

    public abstract void b(long j, Object obj, Object obj2);

    public abstract List c(long j, Object obj);
}
