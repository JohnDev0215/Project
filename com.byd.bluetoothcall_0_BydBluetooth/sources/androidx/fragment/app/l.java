package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import b.g.k.q;
import b.g.k.t;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1423a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final n f1424b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final n f1425c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1426b;

        a(ArrayList arrayList) {
            this.f1426b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.A(this.f1426b, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1427b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ n f1428c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f1429d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Fragment f1430e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ ArrayList h;
        final /* synthetic */ Object i;

        b(Object obj, n nVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1427b = obj;
            this.f1428c = nVar;
            this.f1429d = view;
            this.f1430e = fragment;
            this.f = arrayList;
            this.g = arrayList2;
            this.h = arrayList3;
            this.i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1427b;
            if (obj != null) {
                this.f1428c.p(obj, this.f1429d);
                this.g.addAll(l.k(this.f1428c, this.f1427b, this.f1430e, this.f, this.f1429d));
            }
            if (this.h != null) {
                if (this.i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f1429d);
                    this.f1428c.q(this.i, this.h, arrayList);
                }
                this.h.clear();
                this.h.add(this.f1429d);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1431b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1432c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1433d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b.d.a f1434e;
        final /* synthetic */ View f;
        final /* synthetic */ n g;
        final /* synthetic */ Rect h;

        c(Fragment fragment, Fragment fragment2, boolean z, b.d.a aVar, View view, n nVar, Rect rect) {
            this.f1431b = fragment;
            this.f1432c = fragment2;
            this.f1433d = z;
            this.f1434e = aVar;
            this.f = view;
            this.g = nVar;
            this.h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.f(this.f1431b, this.f1432c, this.f1433d, this.f1434e, false);
            View view = this.f;
            if (view != null) {
                this.g.k(view, this.h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n f1435b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.d.a f1436c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Object f1437d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ e f1438e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ View g;
        final /* synthetic */ Fragment h;
        final /* synthetic */ Fragment i;
        final /* synthetic */ boolean j;
        final /* synthetic */ ArrayList k;
        final /* synthetic */ Object l;
        final /* synthetic */ Rect m;

        d(n nVar, b.d.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1435b = nVar;
            this.f1436c = aVar;
            this.f1437d = obj;
            this.f1438e = eVar;
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
            b.d.a<String, View> aVarH = l.h(this.f1435b, this.f1436c, this.f1437d, this.f1438e);
            if (aVarH != null) {
                this.f.addAll(aVarH.values());
                this.f.add(this.g);
            }
            l.f(this.h, this.i, this.j, aVarH, false);
            Object obj = this.f1437d;
            if (obj != null) {
                this.f1435b.z(obj, this.k, this.f);
                View viewS = l.s(aVarH, this.f1438e, this.l, this.j);
                if (viewS != null) {
                    this.f1435b.k(viewS, this.m);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f1439a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1440b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f1441c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1442d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1443e;
        public androidx.fragment.app.a f;

        e() {
        }
    }

    static {
        f1424b = Build.VERSION.SDK_INT >= 21 ? new m() : null;
        f1425c = w();
    }

    static void A(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    static void B(h hVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (hVar.t < 1) {
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
            View view = new View(hVar.u.e());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                b.d.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    o(hVar, iKeyAt, eVar, view, aVarD);
                } else {
                    n(hVar, iKeyAt, eVar, view, aVarD);
                }
            }
        }
    }

    private static void a(ArrayList<View> arrayList, b.d.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewM = aVar.m(size);
            if (collection.contains(t.H(viewM))) {
                arrayList.add(viewM);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void b(androidx.fragment.app.a r16, androidx.fragment.app.k.a r17, android.util.SparseArray<androidx.fragment.app.l.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.l.b(androidx.fragment.app.a, androidx.fragment.app.k$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<e> sparseArray, boolean z) {
        int size = aVar.f1413a.size();
        for (int i = 0; i < size; i++) {
            b(aVar, aVar.f1413a.get(i), sparseArray, false, z);
        }
    }

    private static b.d.a<String, String> d(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        b.d.a<String, String> aVar = new b.d.a<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i4);
            if (aVar2.u(i)) {
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
        if (aVar.s.v.c()) {
            for (int size = aVar.f1413a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f1413a.get(size), sparseArray, true, z);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z, b.d.a<String, View> aVar, boolean z2) {
        if ((z ? fragment2.getEnterTransitionCallback() : fragment.getEnterTransitionCallback()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(aVar.i(i));
                arrayList.add(aVar.m(i));
            }
            if (!z2) {
                throw null;
            }
            throw null;
        }
    }

    private static boolean g(n nVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!nVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    static b.d.a<String, View> h(n nVar, b.d.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.j enterTransitionCallback;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f1439a;
        View view = fragment.getView();
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        b.d.a<String, View> aVar2 = new b.d.a<>();
        nVar.j(aVar2, view);
        androidx.fragment.app.a aVar3 = eVar.f1441c;
        if (eVar.f1440b) {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.o;
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.o(arrayList);
            aVar2.o(aVar.values());
        }
        if (enterTransitionCallback != null) {
            throw null;
        }
        x(aVar, aVar2);
        return aVar2;
    }

    private static b.d.a<String, View> i(n nVar, b.d.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.j exitTransitionCallback;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = eVar.f1442d;
        b.d.a<String, View> aVar2 = new b.d.a<>();
        nVar.j(aVar2, fragment.requireView());
        androidx.fragment.app.a aVar3 = eVar.f;
        if (eVar.f1443e) {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.p;
        } else {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.o;
        }
        aVar2.o(arrayList);
        if (exitTransitionCallback != null) {
            throw null;
        }
        aVar.o(aVar2.keySet());
        return aVar2;
    }

    private static n j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        n nVar = f1424b;
        if (nVar != null && g(nVar, arrayList)) {
            return nVar;
        }
        n nVar2 = f1425c;
        if (nVar2 != null && g(nVar2, arrayList)) {
            return nVar2;
        }
        if (nVar == null && nVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(n nVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            nVar.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        nVar.b(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(n nVar, ViewGroup viewGroup, View view, b.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        b.d.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f1439a;
        Fragment fragment2 = eVar.f1442d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1440b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(nVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        b.d.a<String, View> aVarI = i(nVar, aVar2, objT, eVar);
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
            nVar.y(obj3, view, arrayList);
            z(nVar, obj3, obj2, aVarI, eVar.f1443e, eVar.f);
            if (obj != null) {
                nVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        q.a(viewGroup, new d(nVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(n nVar, ViewGroup viewGroup, View view, b.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f1439a;
        Fragment fragment2 = eVar.f1442d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1440b;
        Object objT = aVar.isEmpty() ? null : t(nVar, fragment, fragment2, z);
        b.d.a<String, View> aVarI = i(nVar, aVar, objT, eVar);
        b.d.a<String, View> aVarH = h(nVar, aVar, objT, eVar);
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
            nVar.y(obj3, view, arrayList);
            z(nVar, obj3, obj2, aVarI, eVar.f1443e, eVar.f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, eVar, obj, z);
            if (viewS != null) {
                nVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        q.a(viewGroup, new c(fragment, fragment2, z, aVarH, view2, nVar, rect));
        return obj3;
    }

    private static void n(h hVar, int i, e eVar, View view, b.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        n nVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.v.c() ? (ViewGroup) hVar.v.b(i) : null;
        if (viewGroup == null || (nVarJ = j((fragment2 = eVar.f1442d), (fragment = eVar.f1439a))) == null) {
            return;
        }
        boolean z = eVar.f1440b;
        boolean z2 = eVar.f1443e;
        Object objQ = q(nVarJ, fragment, z);
        Object objR = r(nVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(nVarJ, viewGroup, view, aVar, eVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(nVarJ, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListK == null || arrayListK.isEmpty()) ? null : obj;
        nVarJ.a(objQ, view);
        Object objU = u(nVarJ, objQ, obj2, objL, fragment, eVar.f1440b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            nVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(nVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            nVarJ.w(viewGroup, arrayList2, aVar);
            nVarJ.c(viewGroup, objU);
            nVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(h hVar, int i, e eVar, View view, b.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        n nVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.v.c() ? (ViewGroup) hVar.v.b(i) : null;
        if (viewGroup == null || (nVarJ = j((fragment2 = eVar.f1442d), (fragment = eVar.f1439a))) == null) {
            return;
        }
        boolean z = eVar.f1440b;
        boolean z2 = eVar.f1443e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(nVarJ, fragment, z);
        Object objR = r(nVarJ, fragment2, z2);
        Object objM = m(nVarJ, viewGroup, view, aVar, eVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(nVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(nVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(nVarJ, objQ, obj, objM, fragment, z);
        if (objU != null) {
            v(nVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = nVarJ.o(arrayList);
            nVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            nVarJ.c(viewGroup, objU);
            nVarJ.x(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            nVarJ.z(objM, arrayList2, arrayList);
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

    private static Object q(n nVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return nVar.g(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    private static Object r(n nVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return nVar.g(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    static View s(b.d.a<String, View> aVar, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar2 = eVar.f1441c;
        if (obj == null || aVar == null || (arrayList = aVar2.o) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z ? aVar2.o.get(0) : aVar2.p.get(0));
    }

    private static Object t(n nVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return nVar.A(nVar.g(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    private static Object u(n nVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? nVar.n(obj2, obj, obj3) : nVar.m(obj2, obj, obj3);
    }

    private static void v(n nVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            nVar.r(obj, fragment.getView(), arrayList);
            q.a(fragment.mContainer, new a(arrayList));
        }
    }

    private static n w() {
        try {
            return (n) Class.forName("b.m.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void x(b.d.a<String, String> aVar, b.d.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.m(size))) {
                aVar.k(size);
            }
        }
    }

    private static void y(n nVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        q.a(viewGroup, new b(obj, nVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static void z(n nVar, Object obj, Object obj2, b.d.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        ArrayList<String> arrayList = aVar2.o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z ? aVar2.p.get(0) : aVar2.o.get(0));
        nVar.v(obj, view);
        if (obj2 != null) {
            nVar.v(obj2, view);
        }
    }
}
