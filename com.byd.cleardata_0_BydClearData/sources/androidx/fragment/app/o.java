package androidx.fragment.app;

import a.g.k.t;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1339a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final q f1340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final q f1341c;

    static class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1342b;

        a(ArrayList arrayList) {
            this.f1342b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            o.A(this.f1342b, 4);
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ q f1344c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f1345d;
        final /* synthetic */ Fragment e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ ArrayList h;
        final /* synthetic */ Object i;

        b(Object obj, q qVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1343b = obj;
            this.f1344c = qVar;
            this.f1345d = view;
            this.e = fragment;
            this.f = arrayList;
            this.g = arrayList2;
            this.h = arrayList3;
            this.i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1343b;
            if (obj != null) {
                this.f1344c.p(obj, this.f1345d);
                this.g.addAll(o.k(this.f1344c, this.f1343b, this.e, this.f, this.f1345d));
            }
            if (this.h != null) {
                if (this.i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f1345d);
                    this.f1344c.q(this.i, this.h, arrayList);
                }
                this.h.clear();
                this.h.add(this.f1345d);
            }
        }
    }

    static class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1346b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1347c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1348d;
        final /* synthetic */ a.d.a e;
        final /* synthetic */ View f;
        final /* synthetic */ q g;
        final /* synthetic */ Rect h;

        c(Fragment fragment, Fragment fragment2, boolean z, a.d.a aVar, View view, q qVar, Rect rect) {
            this.f1346b = fragment;
            this.f1347c = fragment2;
            this.f1348d = z;
            this.e = aVar;
            this.f = view;
            this.g = qVar;
            this.h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            o.f(this.f1346b, this.f1347c, this.f1348d, this.e, false);
            View view = this.f;
            if (view != null) {
                this.g.k(view, this.h);
            }
        }
    }

    static class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ q f1349b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ a.d.a f1350c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Object f1351d;
        final /* synthetic */ e e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ View g;
        final /* synthetic */ Fragment h;
        final /* synthetic */ Fragment i;
        final /* synthetic */ boolean j;
        final /* synthetic */ ArrayList k;
        final /* synthetic */ Object l;
        final /* synthetic */ Rect m;

        d(q qVar, a.d.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1349b = qVar;
            this.f1350c = aVar;
            this.f1351d = obj;
            this.e = eVar;
            this.f = arrayList;
            this.g = view;
            this.h = fragment;
            this.i = fragment2;
            this.j = z;
            this.k = arrayList2;
            this.l = obj2;
            this.m = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.d.a<String, View> aVarH = o.h(this.f1349b, this.f1350c, this.f1351d, this.e);
            if (aVarH != null) {
                this.f.addAll(aVarH.values());
                this.f.add(this.g);
            }
            o.f(this.h, this.i, this.j, aVarH, false);
            Object obj = this.f1351d;
            if (obj != null) {
                this.f1349b.z(obj, this.k, this.f);
                View viewS = o.s(aVarH, this.e, this.l, this.j);
                if (viewS != null) {
                    this.f1349b.k(viewS, this.m);
                }
            }
        }
    }

    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f1352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1353b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f1354c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1355d;
        public boolean e;
        public androidx.fragment.app.a f;

        e() {
        }
    }

    static {
        f1340b = Build.VERSION.SDK_INT >= 21 ? new p() : null;
        f1341c = w();
    }

    static void A(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    static void B(j jVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (jVar.t < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            androidx.fragment.app.a aVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(jVar.u.h());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                a.d.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    o(jVar, iKeyAt, eVar, view, aVarD);
                } else {
                    n(jVar, iKeyAt, eVar, view, aVarD);
                }
            }
        }
    }

    private static void a(ArrayList<View> arrayList, a.d.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewM = aVar.m(size);
            if (collection.contains(t.F(viewM))) {
                arrayList.add(viewM);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void b(androidx.fragment.app.a r16, androidx.fragment.app.n.a r17, android.util.SparseArray<androidx.fragment.app.o.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.o.b(androidx.fragment.app.a, androidx.fragment.app.n$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<e> sparseArray, boolean z) {
        int size = aVar.f1331a.size();
        for (int i = 0; i < size; i++) {
            b(aVar, aVar.f1331a.get(i), sparseArray, false, z);
        }
    }

    private static a.d.a<String, String> d(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        a.d.a<String, String> aVar = new a.d.a<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i4);
            if (aVar2.r(i)) {
                boolean zBooleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = aVar2.o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.o;
                        arrayList4 = aVar2.p;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.o;
                        arrayList3 = aVar2.p;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String strRemove = aVar.remove(str2);
                        if (strRemove != null) {
                            aVar.put(str, strRemove);
                        } else {
                            aVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return aVar;
    }

    public static void e(androidx.fragment.app.a aVar, SparseArray<e> sparseArray, boolean z) {
        if (aVar.s.v.f()) {
            for (int size = aVar.f1331a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f1331a.get(size), sparseArray, true, z);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z, a.d.a<String, View> aVar, boolean z2) {
        if ((z ? fragment2.A() : fragment.A()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(aVar.i(i));
                arrayList.add(aVar.m(i));
            }
            throw null;
        }
    }

    private static boolean g(q qVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!qVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    static a.d.a<String, View> h(q qVar, a.d.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.f fVarA;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f1352a;
        View viewU = fragment.U();
        if (aVar.isEmpty() || obj == null || viewU == null) {
            aVar.clear();
            return null;
        }
        a.d.a<String, View> aVar2 = new a.d.a<>();
        qVar.j(aVar2, viewU);
        androidx.fragment.app.a aVar3 = eVar.f1354c;
        if (eVar.f1353b) {
            fVarA = fragment.C();
            arrayList = aVar3.o;
        } else {
            fVarA = fragment.A();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.o(arrayList);
            aVar2.o(aVar.values());
        }
        if (fVarA != null) {
            throw null;
        }
        x(aVar, aVar2);
        return aVar2;
    }

    private static a.d.a<String, View> i(q qVar, a.d.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.f fVarC;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = eVar.f1355d;
        a.d.a<String, View> aVar2 = new a.d.a<>();
        qVar.j(aVar2, fragment.l1());
        androidx.fragment.app.a aVar3 = eVar.f;
        if (eVar.e) {
            fVarC = fragment.A();
            arrayList = aVar3.p;
        } else {
            fVarC = fragment.C();
            arrayList = aVar3.o;
        }
        aVar2.o(arrayList);
        if (fVarC != null) {
            throw null;
        }
        aVar.o(aVar2.keySet());
        return aVar2;
    }

    private static q j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objB = fragment.B();
            if (objB != null) {
                arrayList.add(objB);
            }
            Object objN = fragment.N();
            if (objN != null) {
                arrayList.add(objN);
            }
            Object objP = fragment.P();
            if (objP != null) {
                arrayList.add(objP);
            }
        }
        if (fragment2 != null) {
            Object objZ = fragment2.z();
            if (objZ != null) {
                arrayList.add(objZ);
            }
            Object objK = fragment2.K();
            if (objK != null) {
                arrayList.add(objK);
            }
            Object objO = fragment2.O();
            if (objO != null) {
                arrayList.add(objO);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        q qVar = f1340b;
        if (qVar != null && g(qVar, arrayList)) {
            return qVar;
        }
        q qVar2 = f1341c;
        if (qVar2 != null && g(qVar2, arrayList)) {
            return qVar2;
        }
        if (qVar == null && qVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(q qVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewU = fragment.U();
        if (viewU != null) {
            qVar.f(arrayList2, viewU);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        qVar.b(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(q qVar, ViewGroup viewGroup, View view, a.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        a.d.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f1352a;
        Fragment fragment2 = eVar.f1355d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1353b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(qVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        a.d.a<String, View> aVarI = i(qVar, aVar2, objT, eVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarI.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, aVarI, true);
        if (obj3 != null) {
            rect = new Rect();
            qVar.y(obj3, view, arrayList);
            z(qVar, obj3, obj2, aVarI, eVar.e, eVar.f);
            if (obj != null) {
                qVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        a.g.k.q.a(viewGroup, new d(qVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(q qVar, ViewGroup viewGroup, View view, a.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f1352a;
        Fragment fragment2 = eVar.f1355d;
        if (fragment != null) {
            fragment.l1().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1353b;
        Object objT = aVar.isEmpty() ? null : t(qVar, fragment, fragment2, z);
        a.d.a<String, View> aVarI = i(qVar, aVar, objT, eVar);
        a.d.a<String, View> aVarH = h(qVar, aVar, objT, eVar);
        if (aVar.isEmpty()) {
            if (aVarI != null) {
                aVarI.clear();
            }
            if (aVarH != null) {
                aVarH.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, aVarI, aVar.keySet());
            a(arrayList2, aVarH, aVar.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, aVarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            qVar.y(obj3, view, arrayList);
            z(qVar, obj3, obj2, aVarI, eVar.e, eVar.f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, eVar, obj, z);
            if (viewS != null) {
                qVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        a.g.k.q.a(viewGroup, new c(fragment, fragment2, z, aVarH, view2, qVar, rect));
        return obj3;
    }

    private static void n(j jVar, int i, e eVar, View view, a.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        q qVarJ;
        Object obj;
        ViewGroup viewGroup = jVar.v.f() ? (ViewGroup) jVar.v.e(i) : null;
        if (viewGroup == null || (qVarJ = j((fragment2 = eVar.f1355d), (fragment = eVar.f1352a))) == null) {
            return;
        }
        boolean z = eVar.f1353b;
        boolean z2 = eVar.e;
        Object objQ = q(qVarJ, fragment, z);
        Object objR = r(qVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(qVarJ, viewGroup, view, aVar, eVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(qVarJ, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListK == null || arrayListK.isEmpty()) ? null : obj;
        qVarJ.a(objQ, view);
        Object objU = u(qVarJ, objQ, obj2, objL, fragment, eVar.f1353b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            qVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(qVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            qVarJ.w(viewGroup, arrayList2, aVar);
            qVarJ.c(viewGroup, objU);
            qVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(j jVar, int i, e eVar, View view, a.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        q qVarJ;
        Object obj;
        ViewGroup viewGroup = jVar.v.f() ? (ViewGroup) jVar.v.e(i) : null;
        if (viewGroup == null || (qVarJ = j((fragment2 = eVar.f1355d), (fragment = eVar.f1352a))) == null) {
            return;
        }
        boolean z = eVar.f1353b;
        boolean z2 = eVar.e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(qVarJ, fragment, z);
        Object objR = r(qVarJ, fragment2, z2);
        Object objM = m(qVarJ, viewGroup, view, aVar, eVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(qVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(qVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(qVarJ, objQ, obj, objM, fragment, z);
        if (objU != null) {
            v(qVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = qVarJ.o(arrayList);
            qVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            qVarJ.c(viewGroup, objU);
            qVarJ.x(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            qVarJ.z(objM, arrayList2, arrayList);
        }
    }

    private static e p(e eVar, SparseArray<e> sparseArray, int i) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i, eVar2);
        return eVar2;
    }

    private static Object q(q qVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return qVar.g(z ? fragment.K() : fragment.z());
    }

    private static Object r(q qVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return qVar.g(z ? fragment.N() : fragment.B());
    }

    static View s(a.d.a<String, View> aVar, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar2 = eVar.f1354c;
        if (obj == null || aVar == null || (arrayList = aVar2.o) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get((z ? aVar2.o : aVar2.p).get(0));
    }

    private static Object t(q qVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return qVar.A(qVar.g(z ? fragment2.P() : fragment.O()));
    }

    private static Object u(q qVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.t() : fragment.q() ? qVar.n(obj2, obj, obj3) : qVar.m(obj2, obj, obj3);
    }

    private static void v(q qVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.m && fragment.A && fragment.P) {
            fragment.r1(true);
            qVar.r(obj, fragment.U(), arrayList);
            a.g.k.q.a(fragment.H, new a(arrayList));
        }
    }

    private static q w() {
        try {
            return (q) Class.forName("a.n.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void x(a.d.a<String, String> aVar, a.d.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.m(size))) {
                aVar.k(size);
            }
        }
    }

    private static void y(q qVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        a.g.k.q.a(viewGroup, new b(obj, qVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static void z(q qVar, Object obj, Object obj2, a.d.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        ArrayList<String> arrayList = aVar2.o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get((z ? aVar2.p : aVar2.o).get(0));
        qVar.v(obj, view);
        if (obj2 != null) {
            qVar.v(obj2, view);
        }
    }
}
