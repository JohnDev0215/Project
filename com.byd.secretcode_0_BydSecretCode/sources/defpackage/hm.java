package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PendingPost.java */
/* JADX INFO: loaded from: classes.dex */
public final class hm {
    public static final List<hm> a = new ArrayList();
    public Object b;
    public om c;
    public hm d;

    public hm(Object obj, om omVar) {
        this.b = obj;
        this.c = omVar;
    }

    public static hm a(om omVar, Object obj) {
        List<hm> list = a;
        synchronized (list) {
            int size = list.size();
            if (size <= 0) {
                return new hm(obj, omVar);
            }
            hm hmVarRemove = list.remove(size - 1);
            hmVarRemove.b = obj;
            hmVarRemove.c = omVar;
            hmVarRemove.d = null;
            return hmVarRemove;
        }
    }

    public static void b(hm hmVar) {
        hmVar.b = null;
        hmVar.c = null;
        hmVar.d = null;
        List<hm> list = a;
        synchronized (list) {
            if (list.size() < 10000) {
                list.add(hmVar);
            }
        }
    }
}
