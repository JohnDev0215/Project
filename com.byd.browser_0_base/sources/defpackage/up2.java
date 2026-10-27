package defpackage;

import defpackage.wp2;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: compiled from: SnackbarCollection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class up2 {
    public final LinkedList a = new LinkedList();
    public final LinkedList b = new LinkedList();

    public static boolean c(LinkedList linkedList, wp2.b bVar) {
        boolean z = false;
        if (bVar == null) {
            return false;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            tp2 tp2Var = (tp2) it.next();
            if (tp2Var.a == bVar) {
                it.remove();
                bVar.d(tp2Var.e);
                z = true;
            }
        }
        return z;
    }

    public static boolean d(LinkedList linkedList, wp2.b bVar, Object obj) {
        if (bVar == null) {
            return false;
        }
        Iterator it = linkedList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            tp2 tp2Var = (tp2) it.next();
            if (tp2Var.a == bVar) {
                Object obj2 = tp2Var.e;
                if ((obj2 == null && obj == null) ? true : (obj2 == null || obj == null) ? false : obj2.equals(obj)) {
                    it.remove();
                    bVar.d(tp2Var.e);
                    z = true;
                }
            }
        }
        return z;
    }

    public final tp2 a() {
        tp2 tp2Var = (tp2) this.a.peekFirst();
        return tp2Var == null ? (tp2) this.b.peekFirst() : tp2Var;
    }

    public final tp2 b(boolean z) {
        wp2.b bVar;
        tp2 tp2Var = (tp2) this.a.pollFirst();
        if (tp2Var == null) {
            tp2Var = (tp2) this.b.pollFirst();
        }
        if (tp2Var != null && (bVar = tp2Var.a) != null) {
            if (z) {
                bVar.j(tp2Var.e);
            } else {
                bVar.d(tp2Var.e);
            }
        }
        return tp2Var;
    }
}
