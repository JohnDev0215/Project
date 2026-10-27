package p;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hx2 extends l13 {
    public final wx2 f;
    public final List g;
    public final ArrayList h;
    public final ArrayList i = new ArrayList();
    public boolean j;

    static {
        vc1.d("WorkContinuationImpl");
    }

    public hx2(wx2 wx2Var, List list) {
        this.f = wx2Var;
        this.g = list;
        this.h = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            String string = ((gy2) list.get(i)).a.toString();
            v03.i(string, "id.toString()");
            this.h.add(string);
            this.i.add(string);
        }
    }

    public static boolean w(hx2 hx2Var, HashSet hashSet) {
        hashSet.addAll(hx2Var.h);
        HashSet hashSetX = x(hx2Var);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (hashSetX.contains((String) it.next())) {
                return true;
            }
        }
        hashSet.removeAll(hx2Var.h);
        return false;
    }

    public static HashSet x(hx2 hx2Var) {
        HashSet hashSet = new HashSet();
        hx2Var.getClass();
        return hashSet;
    }
}
