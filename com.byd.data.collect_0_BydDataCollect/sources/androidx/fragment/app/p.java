package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1499a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final r f1500b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final r f1501c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ g f1502b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1503c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.g.g.a f1504d;

        a(g gVar, Fragment fragment, b.g.g.a aVar) {
            this.f1502b = gVar;
            this.f1503c = fragment;
            this.f1504d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1502b.a(this.f1503c, this.f1504d);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1505b;

        b(ArrayList arrayList) {
            this.f1505b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            p.A(this.f1505b, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ g f1506b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1507c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.g.g.a f1508d;

        c(g gVar, Fragment fragment, b.g.g.a aVar) {
            this.f1506b = gVar;
            this.f1507c = fragment;
            this.f1508d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1506b.a(this.f1507c, this.f1508d);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1509b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ r f1510c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f1511d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Fragment f1512e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f1513f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ ArrayList f1514g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ ArrayList f1515h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ Object f1516i;

        d(Object obj, r rVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1509b = obj;
            this.f1510c = rVar;
            this.f1511d = view;
            this.f1512e = fragment;
            this.f1513f = arrayList;
            this.f1514g = arrayList2;
            this.f1515h = arrayList3;
            this.f1516i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1509b;
            if (obj != null) {
                this.f1510c.p(obj, this.f1511d);
                this.f1514g.addAll(p.k(this.f1510c, this.f1509b, this.f1512e, this.f1513f, this.f1511d));
            }
            if (this.f1515h != null) {
                if (this.f1516i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f1511d);
                    this.f1510c.q(this.f1516i, this.f1515h, arrayList);
                }
                this.f1515h.clear();
                this.f1515h.add(this.f1511d);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class e implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1517b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1518c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1519d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b.d.a f1520e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ View f1521f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ r f1522g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ Rect f1523h;

        e(Fragment fragment, Fragment fragment2, boolean z, b.d.a aVar, View view, r rVar, Rect rect) {
            this.f1517b = fragment;
            this.f1518c = fragment2;
            this.f1519d = z;
            this.f1520e = aVar;
            this.f1521f = view;
            this.f1522g = rVar;
            this.f1523h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            p.f(this.f1517b, this.f1518c, this.f1519d, this.f1520e, false);
            View view = this.f1521f;
            if (view != null) {
                this.f1522g.k(view, this.f1523h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ r f1524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.d.a f1525c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Object f1526d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ h f1527e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f1528f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ View f1529g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ Fragment f1530h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ Fragment f1531i;
        final /* synthetic */ boolean j;
        final /* synthetic */ ArrayList k;
        final /* synthetic */ Object l;
        final /* synthetic */ Rect m;

        f(r rVar, b.d.a aVar, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1524b = rVar;
            this.f1525c = aVar;
            this.f1526d = obj;
            this.f1527e = hVar;
            this.f1528f = arrayList;
            this.f1529g = view;
            this.f1530h = fragment;
            this.f1531i = fragment2;
            this.j = z;
            this.k = arrayList2;
            this.l = obj2;
            this.m = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.d.a<String, View> aVarH = p.h(this.f1524b, this.f1525c, this.f1526d, this.f1527e);
            if (aVarH != null) {
                this.f1528f.addAll(aVarH.values());
                this.f1528f.add(this.f1529g);
            }
            p.f(this.f1530h, this.f1531i, this.j, aVarH, false);
            Object obj = this.f1526d;
            if (obj != null) {
                this.f1524b.A(obj, this.k, this.f1528f);
                View viewS = p.s(aVarH, this.f1527e, this.l, this.j);
                if (viewS != null) {
                    this.f1524b.k(viewS, this.m);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    interface g {
        void a(Fragment fragment, b.g.g.a aVar);

        void b(Fragment fragment, b.g.g.a aVar);
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f1532a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1533b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f1534c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1535d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1536e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public androidx.fragment.app.a f1537f;

        h() {
        }
    }

    static {
        f1500b = Build.VERSION.SDK_INT >= 21 ? new q() : null;
        f1501c = w();
    }

    static void A(ArrayList<View> arrayList, int i2) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i2);
        }
    }

    static void B(j jVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, boolean z, g gVar) {
        if (jVar.o < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i4 = i2; i4 < i3; i4++) {
            androidx.fragment.app.a aVar = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(jVar.p.h());
            int size = sparseArray.size();
            for (int i5 = 0; i5 < size; i5++) {
                int iKeyAt = sparseArray.keyAt(i5);
                b.d.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i2, i3);
                h hVar = (h) sparseArray.valueAt(i5);
                if (z) {
                    o(jVar, iKeyAt, hVar, view, aVarD, gVar);
                } else {
                    n(jVar, iKeyAt, hVar, view, aVarD, gVar);
                }
            }
        }
    }

    static boolean C() {
        return (f1500b == null && f1501c == null) ? false : true;
    }

    private static void a(ArrayList<View> arrayList, b.d.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewM = aVar.m(size);
            if (collection.contains(b.g.k.t.H(viewM))) {
                arrayList.add(viewM);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void b(androidx.fragment.app.a r8, androidx.fragment.app.o.a r9, android.util.SparseArray<androidx.fragment.app.p.h> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.p.b(androidx.fragment.app.a, androidx.fragment.app.o$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        int size = aVar.f1484c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b(aVar, aVar.f1484c.get(i2), sparseArray, false, z);
        }
    }

    private static b.d.a<String, String> d(int i2, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        b.d.a<String, String> aVar = new b.d.a<>();
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i5);
            if (aVar2.D(i2)) {
                boolean zBooleanValue = arrayList2.get(i5).booleanValue();
                ArrayList<String> arrayList5 = aVar2.p;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.p;
                        arrayList4 = aVar2.q;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.p;
                        arrayList3 = aVar2.q;
                        arrayList4 = arrayList6;
                    }
                    for (int i6 = 0; i6 < size; i6++) {
                        String str = arrayList4.get(i6);
                        String str2 = arrayList3.get(i6);
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

    public static void e(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        if (aVar.t.q.f()) {
            for (int size = aVar.f1484c.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f1484c.get(size), sparseArray, true, z);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z, b.d.a<String, View> aVar, boolean z2) {
        if ((z ? fragment2.r() : fragment.r()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList2.add(aVar.i(i2));
                arrayList.add(aVar.m(i2));
            }
            if (!z2) {
                throw null;
            }
            throw null;
        }
    }

    private static boolean g(r rVar, List<Object> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!rVar.e(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    static b.d.a<String, View> h(r rVar, b.d.a<String, String> aVar, Object obj, h hVar) {
        androidx.core.app.f fVarR;
        ArrayList<String> arrayList;
        Fragment fragment = hVar.f1532a;
        View viewL = fragment.L();
        if (aVar.isEmpty() || obj == null || viewL == null) {
            aVar.clear();
            return null;
        }
        b.d.a<String, View> aVar2 = new b.d.a<>();
        rVar.j(aVar2, viewL);
        androidx.fragment.app.a aVar3 = hVar.f1534c;
        if (hVar.f1533b) {
            fVarR = fragment.t();
            arrayList = aVar3.p;
        } else {
            fVarR = fragment.r();
            arrayList = aVar3.q;
        }
        if (arrayList != null) {
            aVar2.o(arrayList);
            aVar2.o(aVar.values());
        }
        if (fVarR != null) {
            throw null;
        }
        x(aVar, aVar2);
        return aVar2;
    }

    private static b.d.a<String, View> i(r rVar, b.d.a<String, String> aVar, Object obj, h hVar) {
        androidx.core.app.f fVarT;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = hVar.f1535d;
        b.d.a<String, View> aVar2 = new b.d.a<>();
        rVar.j(aVar2, fragment.d1());
        androidx.fragment.app.a aVar3 = hVar.f1537f;
        if (hVar.f1536e) {
            fVarT = fragment.r();
            arrayList = aVar3.q;
        } else {
            fVarT = fragment.t();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.o(arrayList);
        }
        if (fVarT != null) {
            throw null;
        }
        aVar.o(aVar2.keySet());
        return aVar2;
    }

    private static r j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objS = fragment.s();
            if (objS != null) {
                arrayList.add(objS);
            }
            Object objE = fragment.E();
            if (objE != null) {
                arrayList.add(objE);
            }
            Object objG = fragment.G();
            if (objG != null) {
                arrayList.add(objG);
            }
        }
        if (fragment2 != null) {
            Object objQ = fragment2.q();
            if (objQ != null) {
                arrayList.add(objQ);
            }
            Object objB = fragment2.B();
            if (objB != null) {
                arrayList.add(objB);
            }
            Object objF = fragment2.F();
            if (objF != null) {
                arrayList.add(objF);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        r rVar = f1500b;
        if (rVar != null && g(rVar, arrayList)) {
            return rVar;
        }
        r rVar2 = f1501c;
        if (rVar2 != null && g(rVar2, arrayList)) {
            return rVar2;
        }
        if (rVar == null && rVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(r rVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewL = fragment.L();
        if (viewL != null) {
            rVar.f(arrayList2, viewL);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        rVar.b(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(r rVar, ViewGroup viewGroup, View view, b.d.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        b.d.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.f1532a;
        Fragment fragment2 = hVar.f1535d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f1533b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(rVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        b.d.a<String, View> aVarI = i(rVar, aVar2, objT, hVar);
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
            rVar.z(obj3, view, arrayList);
            z(rVar, obj3, obj2, aVarI, hVar.f1536e, hVar.f1537f);
            if (obj != null) {
                rVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        b.g.k.q.a(viewGroup, new f(rVar, aVar, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(r rVar, ViewGroup viewGroup, View view, b.d.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.f1532a;
        Fragment fragment2 = hVar.f1535d;
        if (fragment != null) {
            fragment.d1().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f1533b;
        Object objT = aVar.isEmpty() ? null : t(rVar, fragment, fragment2, z);
        b.d.a<String, View> aVarI = i(rVar, aVar, objT, hVar);
        b.d.a<String, View> aVarH = h(rVar, aVar, objT, hVar);
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
            rVar.z(obj3, view, arrayList);
            z(rVar, obj3, obj2, aVarI, hVar.f1536e, hVar.f1537f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, hVar, obj, z);
            if (viewS != null) {
                rVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        b.g.k.q.a(viewGroup, new e(fragment, fragment2, z, aVarH, view2, rVar, rect));
        return obj3;
    }

    private static void n(j jVar, int i2, h hVar, View view, b.d.a<String, String> aVar, g gVar) {
        Fragment fragment;
        Fragment fragment2;
        r rVarJ;
        Object obj;
        ViewGroup viewGroup = jVar.q.f() ? (ViewGroup) jVar.q.c(i2) : null;
        if (viewGroup == null || (rVarJ = j((fragment2 = hVar.f1535d), (fragment = hVar.f1532a))) == null) {
            return;
        }
        boolean z = hVar.f1533b;
        boolean z2 = hVar.f1536e;
        Object objQ = q(rVarJ, fragment, z);
        Object objR = r(rVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(rVarJ, viewGroup, view, aVar, hVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(rVarJ, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListK == null || arrayListK.isEmpty()) ? null : obj;
        rVarJ.a(objQ, view);
        Object objU = u(rVarJ, objQ, obj2, objL, fragment, hVar.f1533b);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList.size() > 0)) {
            b.g.g.a aVar2 = new b.g.g.a();
            gVar.b(fragment2, aVar2);
            rVarJ.w(fragment2, objU, aVar2, new c(gVar, fragment2, aVar2));
        }
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            rVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(rVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            rVarJ.x(viewGroup, arrayList2, aVar);
            rVarJ.c(viewGroup, objU);
            rVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(j jVar, int i2, h hVar, View view, b.d.a<String, String> aVar, g gVar) {
        Fragment fragment;
        Fragment fragment2;
        r rVarJ;
        Object obj;
        ViewGroup viewGroup = jVar.q.f() ? (ViewGroup) jVar.q.c(i2) : null;
        if (viewGroup == null || (rVarJ = j((fragment2 = hVar.f1535d), (fragment = hVar.f1532a))) == null) {
            return;
        }
        boolean z = hVar.f1533b;
        boolean z2 = hVar.f1536e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(rVarJ, fragment, z);
        Object objR = r(rVarJ, fragment2, z2);
        Object objM = m(rVarJ, viewGroup, view, aVar, hVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(rVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(rVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(rVarJ, objQ, obj, objM, fragment, z);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList2.size() > 0)) {
            b.g.g.a aVar2 = new b.g.g.a();
            gVar.b(fragment2, aVar2);
            rVarJ.w(fragment2, objU, aVar2, new a(gVar, fragment2, aVar2));
        }
        if (objU != null) {
            v(rVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = rVarJ.o(arrayList);
            rVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            rVarJ.c(viewGroup, objU);
            rVarJ.y(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            rVarJ.A(objM, arrayList2, arrayList);
        }
    }

    private static h p(h hVar, SparseArray<h> sparseArray, int i2) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i2, hVar2);
        return hVar2;
    }

    private static Object q(r rVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return rVar.g(z ? fragment.B() : fragment.q());
    }

    private static Object r(r rVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return rVar.g(z ? fragment.E() : fragment.s());
    }

    static View s(b.d.a<String, View> aVar, h hVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar2 = hVar.f1534c;
        if (obj == null || aVar == null || (arrayList = aVar2.p) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z ? aVar2.p.get(0) : aVar2.q.get(0));
    }

    private static Object t(r rVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return rVar.B(rVar.g(z ? fragment2.G() : fragment.F()));
    }

    private static Object u(r rVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.k() : fragment.i() ? rVar.n(obj2, obj, obj3) : rVar.m(obj2, obj, obj3);
    }

    private static void v(r rVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.m && fragment.A && fragment.O) {
            fragment.j1(true);
            rVar.r(obj, fragment.L(), arrayList);
            b.g.k.q.a(fragment.H, new b(arrayList));
        }
    }

    private static r w() {
        try {
            return (r) Class.forName("b.o.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
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

    private static void y(r rVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        b.g.k.q.a(viewGroup, new d(obj, rVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static void z(r rVar, Object obj, Object obj2, b.d.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        ArrayList<String> arrayList = aVar2.p;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z ? aVar2.q.get(0) : aVar2.p.get(0));
        rVar.v(obj, view);
        if (obj2 != null) {
            rVar.v(obj2, view);
        }
    }
}
