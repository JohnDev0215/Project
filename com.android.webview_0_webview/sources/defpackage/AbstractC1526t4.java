package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: renamed from: t4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1526t4 {
    public static final int[] a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    public static final C4 b = new C1801y4();
    public static final C4 c;

    static {
        C4 c4;
        try {
            c4 = (C4) Class.forName("O6").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            c4 = null;
        }
        c = c4;
    }

    public static void a(ArrayList arrayList, C0971j1 c0971j1, Collection collection) {
        for (int i = c0971j1.u - 1; i >= 0; i--) {
            View view = (View) c0971j1.j(i);
            if (collection.contains(AbstractC1251o3.p(view))) {
                arrayList.add(view);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(defpackage.C3 r11, defpackage.B3 r12, android.util.SparseArray r13, boolean r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1526t4.b(C3, B3, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(K3 k3, K3 k32, boolean z, C0971j1 c0971j1, boolean z2) {
        if (z) {
            k32.k();
        } else {
            k3.k();
        }
    }

    public static boolean d(C4 c4, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!c4.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static C0971j1 e(C4 c4, C0971j1 c0971j1, Object obj, C1471s4 c1471s4) {
        ArrayList arrayList;
        K3 k3 = c1471s4.a;
        View view = k3.Z;
        if (c0971j1.isEmpty() || obj == null || view == null) {
            c0971j1.clear();
            return null;
        }
        C0971j1 c0971j12 = new C0971j1();
        c4.i(c0971j12, view);
        C3 c3 = c1471s4.c;
        if (c1471s4.b) {
            arrayList = c3.q;
        } else {
            k3.k();
            arrayList = c3.r;
        }
        if (arrayList != null) {
            AbstractC1578u1.k(c0971j12, arrayList);
            AbstractC1578u1.k(c0971j12, c0971j1.values());
        }
        int i = c0971j1.u;
        while (true) {
            i--;
            if (i < 0) {
                return c0971j12;
            }
            if (!c0971j12.containsKey((String) c0971j1.j(i))) {
                c0971j1.i(i);
            }
        }
    }

    public static C0971j1 f(C4 c4, C0971j1 c0971j1, Object obj, C1471s4 c1471s4) {
        ArrayList arrayList;
        if (c0971j1.isEmpty() || obj == null) {
            c0971j1.clear();
            return null;
        }
        K3 k3 = c1471s4.d;
        C0971j1 c0971j12 = new C0971j1();
        c4.i(c0971j12, k3.Z);
        C3 c3 = c1471s4.f;
        if (c1471s4.e) {
            k3.k();
            arrayList = c3.r;
        } else {
            arrayList = c3.q;
        }
        AbstractC1578u1.k(c0971j12, arrayList);
        AbstractC1578u1.k(c0971j1, c0971j12.keySet());
        return c0971j12;
    }

    public static C4 g(K3 k3, K3 k32) {
        C4 c4 = b;
        ArrayList arrayList = new ArrayList();
        if (k3 != null) {
            k3.l();
            Object objR = k3.r();
            if (objR != null) {
                arrayList.add(objR);
            }
            Object objT = k3.t();
            if (objT != null) {
                arrayList.add(objT);
            }
        }
        if (k32 != null) {
            k32.j();
            Object objQ = k32.q();
            if (objQ != null) {
                arrayList.add(objQ);
            }
            k32.s();
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (d(c4, arrayList)) {
            return c4;
        }
        C4 c42 = c;
        if (c42 == null || !d(c42, arrayList)) {
            throw new IllegalArgumentException("Invalid Transition types");
        }
        return c;
    }

    public static ArrayList h(C4 c4, Object obj, K3 k3, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        View view2 = k3.Z;
        if (view2 != null) {
            c4.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        c4.b(obj, arrayList2);
        return arrayList2;
    }

    public static Object i(C4 c4, K3 k3, boolean z) {
        Object objQ = null;
        if (k3 == null) {
            return null;
        }
        if (z) {
            objQ = k3.q();
        } else {
            k3.j();
        }
        return c4.g(objQ);
    }

    public static Object j(C4 c4, K3 k3, boolean z) {
        Object objR = null;
        if (k3 == null) {
            return null;
        }
        if (z) {
            objR = k3.r();
        } else {
            k3.l();
        }
        return c4.g(objR);
    }

    public static View k(C0971j1 c0971j1, C1471s4 c1471s4, Object obj, boolean z) {
        ArrayList arrayList;
        C3 c3 = c1471s4.c;
        if (obj == null || c0971j1 == null || (arrayList = c3.q) == null || arrayList.isEmpty()) {
            return null;
        }
        return (View) c0971j1.get(z ? (String) c3.q.get(0) : (String) c3.r.get(0));
    }

    public static Object l(C4 c4, K3 k3, K3 k32, boolean z) {
        Object objT;
        if (z) {
            objT = k32.t();
        } else {
            k3.s();
            objT = null;
        }
        return c4.u(c4.g(objT));
    }

    public static void m(C4 c4, Object obj, Object obj2, C0971j1 c0971j1, boolean z, C3 c3) {
        ArrayList arrayList = c3.q;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = (View) c0971j1.get(z ? (String) c3.r.get(0) : (String) c3.q.get(0));
        c4.r(obj, view);
        if (obj2 != null) {
            c4.r(obj2, view);
        }
    }

    public static void n(ArrayList arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0435 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0214  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void o(defpackage.LayoutInflaterFactory2C0974j4 r38, java.util.ArrayList r39, java.util.ArrayList r40, int r41, int r42, boolean r43) {
        /*
            Method dump skipped, instruction units count: 1098
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1526t4.o(j4, java.util.ArrayList, java.util.ArrayList, int, int, boolean):void");
    }
}
