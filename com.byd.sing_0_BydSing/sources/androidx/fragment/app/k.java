package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import c.g.k.q;
import c.g.k.t;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1570a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final m f1571b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final m f1572c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1573b;

        a(ArrayList arrayList) {
            this.f1573b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            k.A(this.f1573b, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1574b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ m f1575c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f1576d;
        final /* synthetic */ Fragment e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ ArrayList h;
        final /* synthetic */ Object i;

        b(Object obj, m mVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1574b = obj;
            this.f1575c = mVar;
            this.f1576d = view;
            this.e = fragment;
            this.f = arrayList;
            this.g = arrayList2;
            this.h = arrayList3;
            this.i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1574b;
            if (obj != null) {
                this.f1575c.p(obj, this.f1576d);
                this.g.addAll(k.k(this.f1575c, this.f1574b, this.e, this.f, this.f1576d));
            }
            if (this.h != null) {
                if (this.i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f1576d);
                    this.f1575c.q(this.i, this.h, arrayList);
                }
                this.h.clear();
                this.h.add(this.f1576d);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1577b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1578c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1579d;
        final /* synthetic */ c.d.a e;
        final /* synthetic */ View f;
        final /* synthetic */ m g;
        final /* synthetic */ Rect h;

        c(Fragment fragment, Fragment fragment2, boolean z, c.d.a aVar, View view, m mVar, Rect rect) {
            this.f1577b = fragment;
            this.f1578c = fragment2;
            this.f1579d = z;
            this.e = aVar;
            this.f = view;
            this.g = mVar;
            this.h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            k.f(this.f1577b, this.f1578c, this.f1579d, this.e, false);
            View view = this.f;
            if (view != null) {
                this.g.k(view, this.h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ m f1580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.d.a f1581c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Object f1582d;
        final /* synthetic */ e e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ View g;
        final /* synthetic */ Fragment h;
        final /* synthetic */ Fragment i;
        final /* synthetic */ boolean j;
        final /* synthetic */ ArrayList k;
        final /* synthetic */ Object l;
        final /* synthetic */ Rect m;

        d(m mVar, c.d.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1580b = mVar;
            this.f1581c = aVar;
            this.f1582d = obj;
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
            c.d.a<String, View> aVarH = k.h(this.f1580b, this.f1581c, this.f1582d, this.e);
            if (aVarH != null) {
                this.f.addAll(aVarH.values());
                this.f.add(this.g);
            }
            k.f(this.h, this.i, this.j, aVarH, false);
            Object obj = this.f1582d;
            if (obj != null) {
                this.f1580b.z(obj, this.k, this.f);
                View viewS = k.s(aVarH, this.e, this.l, this.j);
                if (viewS != null) {
                    this.f1580b.k(viewS, this.m);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f1583a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1584b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f1585c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1586d;
        public boolean e;
        public androidx.fragment.app.a f;

        e() {
        }
    }

    static {
        f1571b = Build.VERSION.SDK_INT >= 21 ? new l() : null;
        f1572c = w();
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
            View view = new View(hVar.u.h());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                c.d.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    o(hVar, iKeyAt, eVar, view, aVarD);
                } else {
                    n(hVar, iKeyAt, eVar, view, aVarD);
                }
            }
        }
    }

    private static void a(ArrayList<View> arrayList, c.d.a<String, View> aVar, Collection<String> collection) {
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
    private static void b(androidx.fragment.app.a r16, androidx.fragment.app.j.a r17, android.util.SparseArray<androidx.fragment.app.k.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.k.b(androidx.fragment.app.a, androidx.fragment.app.j$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<e> sparseArray, boolean z) {
        int size = aVar.f1562a.size();
        for (int i = 0; i < size; i++) {
            b(aVar, aVar.f1562a.get(i), sparseArray, false, z);
        }
    }

    private static c.d.a<String, String> d(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        c.d.a<String, String> aVar = new c.d.a<>();
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
            for (int size = aVar.f1562a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f1562a.get(size), sparseArray, true, z);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z, c.d.a<String, View> aVar, boolean z2) {
        if ((z ? fragment2.r() : fragment.r()) != null) {
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

    private static boolean g(m mVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!mVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    static c.d.a<String, View> h(m mVar, c.d.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.j jVarR;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f1583a;
        View viewK = fragment.K();
        if (aVar.isEmpty() || obj == null || viewK == null) {
            aVar.clear();
            return null;
        }
        c.d.a<String, View> aVar2 = new c.d.a<>();
        mVar.j(aVar2, viewK);
        androidx.fragment.app.a aVar3 = eVar.f1585c;
        if (eVar.f1584b) {
            jVarR = fragment.t();
            arrayList = aVar3.o;
        } else {
            jVarR = fragment.r();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.o(arrayList);
            aVar2.o(aVar.values());
        }
        if (jVarR != null) {
            throw null;
        }
        x(aVar, aVar2);
        return aVar2;
    }

    private static c.d.a<String, View> i(m mVar, c.d.a<String, String> aVar, Object obj, e eVar) {
        androidx.core.app.j jVarT;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = eVar.f1586d;
        c.d.a<String, View> aVar2 = new c.d.a<>();
        mVar.j(aVar2, fragment.a1());
        androidx.fragment.app.a aVar3 = eVar.f;
        if (eVar.e) {
            jVarT = fragment.r();
            arrayList = aVar3.p;
        } else {
            jVarT = fragment.t();
            arrayList = aVar3.o;
        }
        aVar2.o(arrayList);
        if (jVarT != null) {
            throw null;
        }
        aVar.o(aVar2.keySet());
        return aVar2;
    }

    private static m j(Fragment fragment, Fragment fragment2) {
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
        m mVar = f1571b;
        if (mVar != null && g(mVar, arrayList)) {
            return mVar;
        }
        m mVar2 = f1572c;
        if (mVar2 != null && g(mVar2, arrayList)) {
            return mVar2;
        }
        if (mVar == null && mVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(m mVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewK = fragment.K();
        if (viewK != null) {
            mVar.f(arrayList2, viewK);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        mVar.b(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(m mVar, ViewGroup viewGroup, View view, c.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        c.d.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f1583a;
        Fragment fragment2 = eVar.f1586d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1584b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(mVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        c.d.a<String, View> aVarI = i(mVar, aVar2, objT, eVar);
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
            mVar.y(obj3, view, arrayList);
            z(mVar, obj3, obj2, aVarI, eVar.e, eVar.f);
            if (obj != null) {
                mVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        q.a(viewGroup, new d(mVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(m mVar, ViewGroup viewGroup, View view, c.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f1583a;
        Fragment fragment2 = eVar.f1586d;
        if (fragment != null) {
            fragment.a1().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1584b;
        Object objT = aVar.isEmpty() ? null : t(mVar, fragment, fragment2, z);
        c.d.a<String, View> aVarI = i(mVar, aVar, objT, eVar);
        c.d.a<String, View> aVarH = h(mVar, aVar, objT, eVar);
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
            mVar.y(obj3, view, arrayList);
            z(mVar, obj3, obj2, aVarI, eVar.e, eVar.f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, eVar, obj, z);
            if (viewS != null) {
                mVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        q.a(viewGroup, new c(fragment, fragment2, z, aVarH, view2, mVar, rect));
        return obj3;
    }

    private static void n(h hVar, int i, e eVar, View view, c.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        m mVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.v.f() ? (ViewGroup) hVar.v.e(i) : null;
        if (viewGroup == null || (mVarJ = j((fragment2 = eVar.f1586d), (fragment = eVar.f1583a))) == null) {
            return;
        }
        boolean z = eVar.f1584b;
        boolean z2 = eVar.e;
        Object objQ = q(mVarJ, fragment, z);
        Object objR = r(mVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(mVarJ, viewGroup, view, aVar, eVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(mVarJ, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListK == null || arrayListK.isEmpty()) ? null : obj;
        mVarJ.a(objQ, view);
        Object objU = u(mVarJ, objQ, obj2, objL, fragment, eVar.f1584b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            mVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(mVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            mVarJ.w(viewGroup, arrayList2, aVar);
            mVarJ.c(viewGroup, objU);
            mVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(h hVar, int i, e eVar, View view, c.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        m mVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.v.f() ? (ViewGroup) hVar.v.e(i) : null;
        if (viewGroup == null || (mVarJ = j((fragment2 = eVar.f1586d), (fragment = eVar.f1583a))) == null) {
            return;
        }
        boolean z = eVar.f1584b;
        boolean z2 = eVar.e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(mVarJ, fragment, z);
        Object objR = r(mVarJ, fragment2, z2);
        Object objM = m(mVarJ, viewGroup, view, aVar, eVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(mVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(mVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(mVarJ, objQ, obj, objM, fragment, z);
        if (objU != null) {
            v(mVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = mVarJ.o(arrayList);
            mVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            mVarJ.c(viewGroup, objU);
            mVarJ.x(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            mVarJ.z(objM, arrayList2, arrayList);
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

    private static Object q(m mVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return mVar.g(z ? fragment.B() : fragment.q());
    }

    private static Object r(m mVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return mVar.g(z ? fragment.E() : fragment.s());
    }

    static View s(c.d.a<String, View> aVar, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar2 = eVar.f1585c;
        if (obj == null || aVar == null || (arrayList = aVar2.o) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z ? aVar2.o.get(0) : aVar2.p.get(0));
    }

    private static Object t(m mVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return mVar.A(mVar.g(z ? fragment2.G() : fragment.F()));
    }

    private static Object u(m mVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.k() : fragment.j() ? mVar.n(obj2, obj, obj3) : mVar.m(obj2, obj, obj3);
    }

    private static void v(m mVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.m && fragment.A && fragment.P) {
            fragment.g1(true);
            mVar.r(obj, fragment.K(), arrayList);
            q.a(fragment.H, new a(arrayList));
        }
    }

    private static m w() {
        try {
            return (m) Class.forName("c.n.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void x(c.d.a<String, String> aVar, c.d.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.m(size))) {
                aVar.k(size);
            }
        }
    }

    private static void y(m mVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        q.a(viewGroup, new b(obj, mVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static void z(m mVar, Object obj, Object obj2, c.d.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        ArrayList<String> arrayList = aVar2.o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z ? aVar2.p.get(0) : aVar2.o.get(0));
        mVar.v(obj, view);
        if (obj2 != null) {
            mVar.v(obj2, view);
        }
    }
}
