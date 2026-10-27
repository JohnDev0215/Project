package a.l.d;

import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f970a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l0 f971b = new h0();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l0 f972c;

    public interface a {
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f973a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f974b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.l.d.a f975c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f976d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f977e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public a.l.d.a f978f;
    }

    static {
        l0 l0Var;
        try {
            l0Var = (l0) Class.forName("a.r.d").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            l0Var = null;
        }
        f972c = l0Var;
    }

    public static void a(ArrayList<View> arrayList, a.e.a<String, View> aVar, Collection<String> collection) {
        for (int i = aVar.f490c - 1; i >= 0; i--) {
            View viewK = aVar.k(i);
            if (collection.contains(a.h.k.q.v(viewK))) {
                arrayList.add(viewK);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(a.l.d.a r8, a.l.d.z.a r9, android.util.SparseArray<a.l.d.g0.b> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.l.d.g0.b(a.l.d.a, a.l.d.z$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(Fragment fragment, Fragment fragment2, boolean z, a.e.a<String, View> aVar, boolean z2) {
        if (z) {
            Fragment.b bVar = fragment2.I;
        } else {
            Fragment.b bVar2 = fragment.I;
        }
    }

    public static boolean d(l0 l0Var, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!l0Var.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static a.e.a<String, View> e(l0 l0Var, a.e.a<String, String> aVar, Object obj, b bVar) {
        View view = bVar.f973a.F;
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        a.e.a<String, View> aVar2 = new a.e.a<>();
        l0Var.i(aVar2, view);
        a.l.d.a aVar3 = bVar.f975c;
        ArrayList<String> arrayList = bVar.f974b ? aVar3.m : aVar3.n;
        if (arrayList != null) {
            a.e.g.k(aVar2, arrayList);
            a.e.g.k(aVar2, aVar.values());
        }
        int i = aVar.f490c;
        while (true) {
            i--;
            if (i < 0) {
                return aVar2;
            }
            if (!aVar2.containsKey(aVar.k(i))) {
                aVar.i(i);
            }
        }
    }

    public static a.e.a<String, View> f(l0 l0Var, a.e.a<String, String> aVar, Object obj, b bVar) {
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = bVar.f976d;
        a.e.a<String, View> aVar2 = new a.e.a<>();
        l0Var.i(aVar2, fragment.G());
        a.l.d.a aVar3 = bVar.f978f;
        ArrayList<String> arrayList = bVar.f977e ? aVar3.n : aVar3.m;
        if (arrayList != null) {
            a.e.g.k(aVar2, arrayList);
        }
        a.e.g.k(aVar, aVar2.keySet());
        return aVar2;
    }

    public static l0 g(Fragment fragment, Fragment fragment2) {
        Object objN;
        Object objM;
        Object objR;
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objN2 = fragment.n();
            if (objN2 != null) {
                arrayList.add(objN2);
            }
            Fragment.b bVar = fragment.I;
            if (bVar == null) {
                objM = null;
            } else {
                objM = bVar.g;
                if (objM == Fragment.U) {
                    objM = fragment.m();
                }
            }
            if (objM != null) {
                arrayList.add(objM);
            }
            Fragment.b bVar2 = fragment.I;
            if (bVar2 == null) {
                objR = null;
            } else {
                objR = bVar2.k;
                if (objR == Fragment.U) {
                    objR = fragment.r();
                }
            }
            if (objR != null) {
                arrayList.add(objR);
            }
        }
        if (fragment2 != null) {
            Object objM2 = fragment2.m();
            if (objM2 != null) {
                arrayList.add(objM2);
            }
            Fragment.b bVar3 = fragment2.I;
            if (bVar3 == null) {
                objN = null;
            } else {
                objN = bVar3.i;
                if (objN == Fragment.U) {
                    objN = fragment2.n();
                }
            }
            if (objN != null) {
                arrayList.add(objN);
            }
            Object objR2 = fragment2.r();
            if (objR2 != null) {
                arrayList.add(objR2);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (d(f971b, arrayList)) {
            return f971b;
        }
        l0 l0Var = f972c;
        if (l0Var == null || !d(l0Var, arrayList)) {
            throw new IllegalArgumentException("Invalid Transition types");
        }
        return f972c;
    }

    public static ArrayList<View> h(l0 l0Var, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.F;
        if (view2 != null) {
            l0Var.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        l0Var.b(obj, arrayList2);
        return arrayList2;
    }

    public static Object i(l0 l0Var, Fragment fragment, boolean z) {
        Object objM = null;
        if (fragment == null) {
            return null;
        }
        if (z) {
            Fragment.b bVar = fragment.I;
            if (bVar != null && (objM = bVar.i) == Fragment.U) {
                objM = fragment.n();
            }
        } else {
            objM = fragment.m();
        }
        return l0Var.g(objM);
    }

    public static Object j(l0 l0Var, Fragment fragment, boolean z) {
        Object objN = null;
        if (fragment == null) {
            return null;
        }
        if (z) {
            Fragment.b bVar = fragment.I;
            if (bVar != null && (objN = bVar.g) == Fragment.U) {
                objN = fragment.m();
            }
        } else {
            objN = fragment.n();
        }
        return l0Var.g(objN);
    }

    public static View k(a.e.a<String, View> aVar, b bVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        a.l.d.a aVar2 = bVar.f975c;
        if (obj == null || aVar == null || (arrayList = aVar2.m) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get((z ? aVar2.m : aVar2.n).get(0));
    }

    public static Object l(l0 l0Var, Fragment fragment, Fragment fragment2, boolean z) {
        Object objR;
        if (z) {
            Fragment.b bVar = fragment2.I;
            if (bVar == null) {
                objR = null;
            } else {
                objR = bVar.k;
                if (objR == Fragment.U) {
                    objR = fragment2.r();
                }
            }
        } else {
            objR = fragment.r();
        }
        return l0Var.w(l0Var.g(objR));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object m(a.l.d.l0 r0, java.lang.Object r1, java.lang.Object r2, java.lang.Object r3, androidx.fragment.app.Fragment r4, boolean r5) {
        /*
            if (r1 == 0) goto L24
            if (r2 == 0) goto L24
            if (r4 == 0) goto L24
            if (r5 == 0) goto L16
            androidx.fragment.app.Fragment$b r4 = r4.I
            if (r4 == 0) goto L24
            java.lang.Boolean r4 = r4.l
            if (r4 != 0) goto L11
            goto L24
        L11:
            boolean r4 = r4.booleanValue()
            goto L25
        L16:
            androidx.fragment.app.Fragment$b r4 = r4.I
            if (r4 == 0) goto L24
            java.lang.Boolean r4 = r4.m
            if (r4 != 0) goto L1f
            goto L24
        L1f:
            boolean r4 = r4.booleanValue()
            goto L25
        L24:
            r4 = 1
        L25:
            if (r4 == 0) goto L2c
            java.lang.Object r0 = r0.m(r2, r1, r3)
            goto L30
        L2c:
            java.lang.Object r0 = r0.l(r2, r1, r3)
        L30:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.l.d.g0.m(a.l.d.l0, java.lang.Object, java.lang.Object, java.lang.Object, androidx.fragment.app.Fragment, boolean):java.lang.Object");
    }

    public static void n(l0 l0Var, Object obj, Object obj2, a.e.a<String, View> aVar, boolean z, a.l.d.a aVar2) {
        ArrayList<String> arrayList = aVar2.m;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get((z ? aVar2.n : aVar2.m).get(0));
        l0Var.s(obj, view);
        if (obj2 != null) {
            l0Var.s(obj2, view);
        }
    }

    public static void o(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0492 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void p(a.l.d.r r39, java.util.ArrayList<a.l.d.a> r40, java.util.ArrayList<java.lang.Boolean> r41, int r42, int r43, boolean r44, a.l.d.g0.a r45) {
        /*
            Method dump skipped, instruction units count: 1191
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.l.d.g0.p(a.l.d.r, java.util.ArrayList, java.util.ArrayList, int, int, boolean, a.l.d.g0$a):void");
    }
}
