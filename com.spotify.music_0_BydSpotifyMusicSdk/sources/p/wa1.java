package p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class wa1 extends ya1 {
    public static final Class c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public static List d(int i, long j, Object obj) {
        List list;
        List list2 = (List) dr2.c.k(j, obj);
        if (list2.isEmpty()) {
            List a91Var = list2 instanceof b91 ? new a91(i) : ((list2 instanceof dx1) && (list2 instanceof v51)) ? ((v51) list2).f(i) : new ArrayList(i);
            dr2.v(j, obj, a91Var);
            return a91Var;
        }
        if (c.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + i);
            arrayList.addAll(list2);
            dr2.v(j, obj, arrayList);
            list = arrayList;
        } else {
            if (!(list2 instanceof rq2)) {
                if (!(list2 instanceof dx1) || !(list2 instanceof v51)) {
                    return list2;
                }
                v51 v51Var = (v51) list2;
                if (((a2) v51Var).c) {
                    return list2;
                }
                v51 v51VarF = v51Var.f(list2.size() + i);
                dr2.v(j, obj, v51VarF);
                return v51VarF;
            }
            a91 a91Var2 = new a91(list2.size() + i);
            a91Var2.addAll((rq2) list2);
            dr2.v(j, obj, a91Var2);
            list = a91Var2;
        }
        return list;
    }

    @Override // p.ya1
    public final void a(long j, Object obj) {
        Object objUnmodifiableList;
        List list = (List) dr2.c.k(j, obj);
        if (list instanceof b91) {
            objUnmodifiableList = ((b91) list).j();
        } else {
            if (c.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof dx1) && (list instanceof v51)) {
                a2 a2Var = (a2) ((v51) list);
                if (a2Var.c) {
                    a2Var.c = false;
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        dr2.v(j, obj, objUnmodifiableList);
    }

    @Override // p.ya1
    public final void b(long j, Object obj, Object obj2) {
        List list = (List) dr2.c.k(j, obj2);
        List listD = d(list.size(), j, obj);
        int size = listD.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            listD.addAll(list);
        }
        if (size > 0) {
            list = listD;
        }
        dr2.v(j, obj, list);
    }

    @Override // p.ya1
    public final List c(long j, Object obj) {
        return d(10, j, obj);
    }
}
