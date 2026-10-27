package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f2393a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final u f2394b = new t();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final u f2395c = w();

    /* JADX INFO: compiled from: FragmentTransition.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ g f2396e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Fragment f2397g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ d0.b f2398h;

        a(g gVar, Fragment fragment, d0.b bVar) {
            this.f2396e = gVar;
            this.f2397g = fragment;
            this.f2398h = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2396e.a(this.f2397g, this.f2398h);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f2399e;

        b(ArrayList arrayList) {
            this.f2399e = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.A(this.f2399e, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ g f2400e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Fragment f2401g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ d0.b f2402h;

        c(g gVar, Fragment fragment, d0.b bVar) {
            this.f2400e = gVar;
            this.f2401g = fragment;
            this.f2402h = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2400e.a(this.f2401g, this.f2402h);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    class d implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object f2403e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ u f2404g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ View f2405h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ Fragment f2406i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final /* synthetic */ ArrayList f2407j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final /* synthetic */ ArrayList f2408k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ ArrayList f2409l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        final /* synthetic */ Object f2410m;

        d(Object obj, u uVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f2403e = obj;
            this.f2404g = uVar;
            this.f2405h = view;
            this.f2406i = fragment;
            this.f2407j = arrayList;
            this.f2408k = arrayList2;
            this.f2409l = arrayList3;
            this.f2410m = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f2403e;
            if (obj != null) {
                this.f2404g.p(obj, this.f2405h);
                this.f2408k.addAll(s.k(this.f2404g, this.f2403e, this.f2406i, this.f2407j, this.f2405h));
            }
            if (this.f2409l != null) {
                if (this.f2410m != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f2405h);
                    this.f2404g.q(this.f2410m, this.f2409l, arrayList);
                }
                this.f2409l.clear();
                this.f2409l.add(this.f2405h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    class e implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Fragment f2411e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Fragment f2412g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ boolean f2413h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ n.a f2414i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final /* synthetic */ View f2415j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final /* synthetic */ u f2416k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ Rect f2417l;

        e(Fragment fragment, Fragment fragment2, boolean z6, n.a aVar, View view, u uVar, Rect rect) {
            this.f2411e = fragment;
            this.f2412g = fragment2;
            this.f2413h = z6;
            this.f2414i = aVar;
            this.f2415j = view;
            this.f2416k = uVar;
            this.f2417l = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.f(this.f2411e, this.f2412g, this.f2413h, this.f2414i, false);
            View view = this.f2415j;
            if (view != null) {
                this.f2416k.k(view, this.f2417l);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    class f implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ u f2418e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ n.a f2419g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ Object f2420h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ h f2421i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final /* synthetic */ ArrayList f2422j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final /* synthetic */ View f2423k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ Fragment f2424l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        final /* synthetic */ Fragment f2425m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        final /* synthetic */ boolean f2426n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        final /* synthetic */ ArrayList f2427o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        final /* synthetic */ Object f2428p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        final /* synthetic */ Rect f2429q;

        f(u uVar, n.a aVar, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z6, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f2418e = uVar;
            this.f2419g = aVar;
            this.f2420h = obj;
            this.f2421i = hVar;
            this.f2422j = arrayList;
            this.f2423k = view;
            this.f2424l = fragment;
            this.f2425m = fragment2;
            this.f2426n = z6;
            this.f2427o = arrayList2;
            this.f2428p = obj2;
            this.f2429q = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.a<String, View> aVarH = s.h(this.f2418e, this.f2419g, this.f2420h, this.f2421i);
            if (aVarH != null) {
                this.f2422j.addAll(aVarH.values());
                this.f2422j.add(this.f2423k);
            }
            s.f(this.f2424l, this.f2425m, this.f2426n, aVarH, false);
            Object obj = this.f2420h;
            if (obj != null) {
                this.f2418e.A(obj, this.f2427o, this.f2422j);
                View viewS = s.s(aVarH, this.f2421i, this.f2428p, this.f2426n);
                if (viewS != null) {
                    this.f2418e.k(viewS, this.f2429q);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    interface g {
        void a(Fragment fragment, d0.b bVar);

        void b(Fragment fragment, d0.b bVar);
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f2430a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2431b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f2432c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f2433d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2434e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public androidx.fragment.app.a f2435f;

        h() {
        }
    }

    static void A(ArrayList<View> arrayList, int i7) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i7);
        }
    }

    static void B(Context context, androidx.fragment.app.f fVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8, boolean z6, g gVar) {
        ViewGroup viewGroup;
        SparseArray sparseArray = new SparseArray();
        for (int i9 = i7; i9 < i8; i9++) {
            androidx.fragment.app.a aVar = arrayList.get(i9);
            if (arrayList2.get(i9).booleanValue()) {
                e(aVar, sparseArray, z6);
            } else {
                c(aVar, sparseArray, z6);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size = sparseArray.size();
            for (int i10 = 0; i10 < size; i10++) {
                int iKeyAt = sparseArray.keyAt(i10);
                n.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i7, i8);
                h hVar = (h) sparseArray.valueAt(i10);
                if (fVar.h() && (viewGroup = (ViewGroup) fVar.f(iKeyAt)) != null) {
                    if (z6) {
                        o(viewGroup, hVar, view, aVarD, gVar);
                    } else {
                        n(viewGroup, hVar, view, aVarD, gVar);
                    }
                }
            }
        }
    }

    static boolean C() {
        return (f2394b == null && f2395c == null) ? false : true;
    }

    private static void a(ArrayList<View> arrayList, n.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewO = aVar.o(size);
            if (collection.contains(i0.u.H(viewO))) {
                arrayList.add(viewO);
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
    private static void b(androidx.fragment.app.a r8, androidx.fragment.app.r.a r9, android.util.SparseArray<androidx.fragment.app.s.h> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.s.b(androidx.fragment.app.a, androidx.fragment.app.r$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z6) {
        int size = aVar.f2368c.size();
        for (int i7 = 0; i7 < size; i7++) {
            b(aVar, aVar.f2368c.get(i7), sparseArray, false, z6);
        }
    }

    private static n.a<String, String> d(int i7, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i8, int i9) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        n.a<String, String> aVar = new n.a<>();
        for (int i10 = i9 - 1; i10 >= i8; i10--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i10);
            if (aVar2.F(i7)) {
                boolean zBooleanValue = arrayList2.get(i10).booleanValue();
                ArrayList<String> arrayList5 = aVar2.f2381p;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.f2381p;
                        arrayList4 = aVar2.f2382q;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.f2381p;
                        arrayList3 = aVar2.f2382q;
                        arrayList4 = arrayList6;
                    }
                    for (int i11 = 0; i11 < size; i11++) {
                        String str = arrayList4.get(i11);
                        String str2 = arrayList3.get(i11);
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

    public static void e(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z6) {
        if (aVar.f2240t.n0().h()) {
            for (int size = aVar.f2368c.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f2368c.get(size), sparseArray, true, z6);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z6, n.a<String, View> aVar, boolean z7) {
        if (z6) {
            fragment2.v();
        } else {
            fragment.v();
        }
    }

    private static boolean g(u uVar, List<Object> list) {
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (!uVar.e(list.get(i7))) {
                return false;
            }
        }
        return true;
    }

    static n.a<String, View> h(u uVar, n.a<String, String> aVar, Object obj, h hVar) {
        ArrayList<String> arrayList;
        Fragment fragment = hVar.f2430a;
        View viewW = fragment.W();
        if (aVar.isEmpty() || obj == null || viewW == null) {
            aVar.clear();
            return null;
        }
        n.a<String, View> aVar2 = new n.a<>();
        uVar.j(aVar2, viewW);
        androidx.fragment.app.a aVar3 = hVar.f2432c;
        if (hVar.f2431b) {
            fragment.y();
            arrayList = aVar3.f2381p;
        } else {
            fragment.v();
            arrayList = aVar3.f2382q;
        }
        if (arrayList != null) {
            aVar2.q(arrayList);
            aVar2.q(aVar.values());
        }
        x(aVar, aVar2);
        return aVar2;
    }

    private static n.a<String, View> i(u uVar, n.a<String, String> aVar, Object obj, h hVar) {
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = hVar.f2433d;
        n.a<String, View> aVar2 = new n.a<>();
        uVar.j(aVar2, fragment.v1());
        androidx.fragment.app.a aVar3 = hVar.f2435f;
        if (hVar.f2434e) {
            fragment.v();
            arrayList = aVar3.f2382q;
        } else {
            fragment.y();
            arrayList = aVar3.f2381p;
        }
        if (arrayList != null) {
            aVar2.q(arrayList);
        }
        aVar.q(aVar2.keySet());
        return aVar2;
    }

    private static u j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objX = fragment.x();
            if (objX != null) {
                arrayList.add(objX);
            }
            Object objO = fragment.O();
            if (objO != null) {
                arrayList.add(objO);
            }
            Object objQ = fragment.Q();
            if (objQ != null) {
                arrayList.add(objQ);
            }
        }
        if (fragment2 != null) {
            Object objU = fragment2.u();
            if (objU != null) {
                arrayList.add(objU);
            }
            Object objM = fragment2.M();
            if (objM != null) {
                arrayList.add(objM);
            }
            Object objP = fragment2.P();
            if (objP != null) {
                arrayList.add(objP);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        u uVar = f2394b;
        if (uVar != null && g(uVar, arrayList)) {
            return uVar;
        }
        u uVar2 = f2395c;
        if (uVar2 != null && g(uVar2, arrayList)) {
            return uVar2;
        }
        if (uVar == null && uVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(u uVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewW = fragment.W();
        if (viewW != null) {
            uVar.f(arrayList2, viewW);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        uVar.b(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(u uVar, ViewGroup viewGroup, View view, n.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        n.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.f2430a;
        Fragment fragment2 = hVar.f2433d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z6 = hVar.f2431b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(uVar, fragment, fragment2, z6);
            aVar2 = aVar;
        }
        n.a<String, View> aVarI = i(uVar, aVar2, objT, hVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarI.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z6, aVarI, true);
        if (obj3 != null) {
            rect = new Rect();
            uVar.z(obj3, view, arrayList);
            z(uVar, obj3, obj2, aVarI, hVar.f2434e, hVar.f2435f);
            if (obj != null) {
                uVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        i0.s.a(viewGroup, new f(uVar, aVar, obj3, hVar, arrayList2, view, fragment, fragment2, z6, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(u uVar, ViewGroup viewGroup, View view, n.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.f2430a;
        Fragment fragment2 = hVar.f2433d;
        if (fragment != null) {
            fragment.v1().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z6 = hVar.f2431b;
        Object objT = aVar.isEmpty() ? null : t(uVar, fragment, fragment2, z6);
        n.a<String, View> aVarI = i(uVar, aVar, objT, hVar);
        n.a<String, View> aVarH = h(uVar, aVar, objT, hVar);
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
        f(fragment, fragment2, z6, aVarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            uVar.z(obj3, view, arrayList);
            z(uVar, obj3, obj2, aVarI, hVar.f2434e, hVar.f2435f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, hVar, obj, z6);
            if (viewS != null) {
                uVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        i0.s.a(viewGroup, new e(fragment, fragment2, z6, aVarH, view2, uVar, rect));
        return obj3;
    }

    private static void n(ViewGroup viewGroup, h hVar, View view, n.a<String, String> aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.f2430a;
        Fragment fragment2 = hVar.f2433d;
        u uVarJ = j(fragment2, fragment);
        if (uVarJ == null) {
            return;
        }
        boolean z6 = hVar.f2431b;
        boolean z7 = hVar.f2434e;
        Object objQ = q(uVarJ, fragment, z6);
        Object objR = r(uVarJ, fragment2, z7);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(uVarJ, viewGroup, view, aVar, hVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(uVarJ, obj, fragment2, arrayList, view);
        if (arrayListK == null || arrayListK.isEmpty()) {
            obj = null;
        }
        Object obj2 = obj;
        uVarJ.a(objQ, view);
        Object objU = u(uVarJ, objQ, obj2, objL, fragment, hVar.f2431b);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList.size() > 0)) {
            d0.b bVar = new d0.b();
            gVar.b(fragment2, bVar);
            uVarJ.w(fragment2, objU, bVar, new c(gVar, fragment2, bVar));
        }
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            uVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(uVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            uVarJ.x(viewGroup, arrayList2, aVar);
            uVarJ.c(viewGroup, objU);
            uVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(ViewGroup viewGroup, h hVar, View view, n.a<String, String> aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.f2430a;
        Fragment fragment2 = hVar.f2433d;
        u uVarJ = j(fragment2, fragment);
        if (uVarJ == null) {
            return;
        }
        boolean z6 = hVar.f2431b;
        boolean z7 = hVar.f2434e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(uVarJ, fragment, z6);
        Object objR = r(uVarJ, fragment2, z7);
        Object objM = m(uVarJ, viewGroup, view, aVar, hVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(uVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(uVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(uVarJ, objQ, obj, objM, fragment, z6);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList2.size() > 0)) {
            d0.b bVar = new d0.b();
            gVar.b(fragment2, bVar);
            uVarJ.w(fragment2, objU, bVar, new a(gVar, fragment2, bVar));
        }
        if (objU != null) {
            v(uVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = uVarJ.o(arrayList);
            uVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            uVarJ.c(viewGroup, objU);
            uVarJ.y(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            uVarJ.A(objM, arrayList2, arrayList);
        }
    }

    private static h p(h hVar, SparseArray<h> sparseArray, int i7) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i7, hVar2);
        return hVar2;
    }

    private static Object q(u uVar, Fragment fragment, boolean z6) {
        if (fragment == null) {
            return null;
        }
        return uVar.g(z6 ? fragment.M() : fragment.u());
    }

    private static Object r(u uVar, Fragment fragment, boolean z6) {
        if (fragment == null) {
            return null;
        }
        return uVar.g(z6 ? fragment.O() : fragment.x());
    }

    static View s(n.a<String, View> aVar, h hVar, Object obj, boolean z6) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar2 = hVar.f2432c;
        if (obj == null || aVar == null || (arrayList = aVar2.f2381p) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z6 ? aVar2.f2381p.get(0) : aVar2.f2382q.get(0));
    }

    private static Object t(u uVar, Fragment fragment, Fragment fragment2, boolean z6) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return uVar.B(uVar.g(z6 ? fragment2.Q() : fragment.P()));
    }

    private static Object u(u uVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z6) {
        return (obj == null || obj2 == null || fragment == null) ? true : z6 ? fragment.n() : fragment.l() ? uVar.n(obj2, obj, obj3) : uVar.m(obj2, obj, obj3);
    }

    private static void v(u uVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.f2121q && fragment.E && fragment.S) {
            fragment.E1(true);
            uVar.r(obj, fragment.W(), arrayList);
            i0.s.a(fragment.L, new b(arrayList));
        }
    }

    private static u w() {
        try {
            return (u) e1.e.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static void x(n.a<String, String> aVar, n.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.o(size))) {
                aVar.m(size);
            }
        }
    }

    private static void y(u uVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        i0.s.a(viewGroup, new d(obj, uVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static void z(u uVar, Object obj, Object obj2, n.a<String, View> aVar, boolean z6, androidx.fragment.app.a aVar2) {
        ArrayList<String> arrayList = aVar2.f2381p;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z6 ? aVar2.f2382q.get(0) : aVar2.f2381p.get(0));
        uVar.v(obj, view);
        if (obj2 != null) {
            uVar.v(obj2, view);
        }
    }
}
