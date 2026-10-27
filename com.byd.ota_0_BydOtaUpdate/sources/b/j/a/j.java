package b.j.a;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import b.f.m.q;
import b.f.m.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1921a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l f1922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f1923c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1924c;

        public a(ArrayList arrayList) {
            this.f1924c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.A(this.f1924c, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class b implements Runnable {
        public final /* synthetic */ ArrayList E3;
        public final /* synthetic */ ArrayList F3;
        public final /* synthetic */ Object G3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f1925c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ l f1926d;
        public final /* synthetic */ View s;
        public final /* synthetic */ Fragment x;
        public final /* synthetic */ ArrayList y;

        public b(Object obj, l lVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1925c = obj;
            this.f1926d = lVar;
            this.s = view;
            this.x = fragment;
            this.y = arrayList;
            this.E3 = arrayList2;
            this.F3 = arrayList3;
            this.G3 = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1925c;
            if (obj != null) {
                this.f1926d.p(obj, this.s);
                this.E3.addAll(j.k(this.f1926d, this.f1925c, this.x, this.y, this.s));
            }
            if (this.F3 != null) {
                if (this.G3 != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.s);
                    this.f1926d.q(this.G3, this.F3, arrayList);
                }
                this.F3.clear();
                this.F3.add(this.s);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class c implements Runnable {
        public final /* synthetic */ l E3;
        public final /* synthetic */ Rect F3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f1927c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Fragment f1928d;
        public final /* synthetic */ boolean s;
        public final /* synthetic */ b.d.a x;
        public final /* synthetic */ View y;

        public c(Fragment fragment, Fragment fragment2, boolean z, b.d.a aVar, View view, l lVar, Rect rect) {
            this.f1927c = fragment;
            this.f1928d = fragment2;
            this.s = z;
            this.x = aVar;
            this.y = view;
            this.E3 = lVar;
            this.F3 = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.f(this.f1927c, this.f1928d, this.s, this.x, false);
            View view = this.y;
            if (view != null) {
                this.E3.k(view, this.F3);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class d implements Runnable {
        public final /* synthetic */ View E3;
        public final /* synthetic */ Fragment F3;
        public final /* synthetic */ Fragment G3;
        public final /* synthetic */ boolean H3;
        public final /* synthetic */ ArrayList I3;
        public final /* synthetic */ Object J3;
        public final /* synthetic */ Rect K3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ l f1929c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.d.a f1930d;
        public final /* synthetic */ Object s;
        public final /* synthetic */ e x;
        public final /* synthetic */ ArrayList y;

        public d(l lVar, b.d.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1929c = lVar;
            this.f1930d = aVar;
            this.s = obj;
            this.x = eVar;
            this.y = arrayList;
            this.E3 = view;
            this.F3 = fragment;
            this.G3 = fragment2;
            this.H3 = z;
            this.I3 = arrayList2;
            this.J3 = obj2;
            this.K3 = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.d.a<String, View> aVarH = j.h(this.f1929c, this.f1930d, this.s, this.x);
            if (aVarH != null) {
                this.y.addAll(aVarH.values());
                this.y.add(this.E3);
            }
            j.f(this.F3, this.G3, this.H3, aVarH, false);
            Object obj = this.s;
            if (obj != null) {
                this.f1929c.z(obj, this.I3, this.y);
                View viewS = j.s(aVarH, this.x, this.J3, this.H3);
                if (viewS != null) {
                    this.f1929c.k(viewS, this.K3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f1931a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1932b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.j.a.a f1933c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1934d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1935e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.j.a.a f1936f;
    }

    static {
        f1922b = Build.VERSION.SDK_INT >= 21 ? new k() : null;
        f1923c = w();
    }

    public static void A(ArrayList<View> arrayList, int i2) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i2);
        }
    }

    public static void B(g gVar, ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, boolean z) {
        if (gVar.R3 < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i4 = i2; i4 < i3; i4++) {
            b.j.a.a aVar = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(gVar.S3.i());
            int size = sparseArray.size();
            for (int i5 = 0; i5 < size; i5++) {
                int iKeyAt = sparseArray.keyAt(i5);
                b.d.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i2, i3);
                e eVar = (e) sparseArray.valueAt(i5);
                if (z) {
                    o(gVar, iKeyAt, eVar, view, aVarD);
                } else {
                    n(gVar, iKeyAt, eVar, view, aVarD);
                }
            }
        }
    }

    public static void a(ArrayList<View> arrayList, b.d.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewL = aVar.l(size);
            if (collection.contains(s.A(viewL))) {
                arrayList.add(viewL);
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
    public static void b(b.j.a.a r16, b.j.a.i.a r17, android.util.SparseArray<b.j.a.j.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.j.a.j.b(b.j.a.a, b.j.a.i$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(b.j.a.a aVar, SparseArray<e> sparseArray, boolean z) {
        int size = aVar.f1904a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b(aVar, aVar.f1904a.get(i2), sparseArray, false, z);
        }
    }

    public static b.d.a<String, String> d(int i2, ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        b.d.a<String, String> aVar = new b.d.a<>();
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            b.j.a.a aVar2 = arrayList.get(i5);
            if (aVar2.p(i2)) {
                boolean zBooleanValue = arrayList2.get(i5).booleanValue();
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

    public static void e(b.j.a.a aVar, SparseArray<e> sparseArray, boolean z) {
        if (aVar.s.T3.f()) {
            for (int size = aVar.f1904a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f1904a.get(size), sparseArray, true, z);
            }
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z, b.d.a<String, View> aVar, boolean z2) {
        if ((z ? fragment2.q() : fragment.q()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList2.add(aVar.i(i2));
                arrayList.add(aVar.l(i2));
            }
            if (!z2) {
                throw null;
            }
            throw null;
        }
    }

    public static boolean g(l lVar, List<Object> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!lVar.e(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public static b.d.a<String, View> h(l lVar, b.d.a<String, String> aVar, Object obj, e eVar) {
        b.f.e.i iVarQ;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f1931a;
        View viewI = fragment.I();
        if (aVar.isEmpty() || obj == null || viewI == null) {
            aVar.clear();
            return null;
        }
        b.d.a<String, View> aVar2 = new b.d.a<>();
        lVar.j(aVar2, viewI);
        b.j.a.a aVar3 = eVar.f1933c;
        if (eVar.f1932b) {
            iVarQ = fragment.s();
            arrayList = aVar3.o;
        } else {
            iVarQ = fragment.q();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.n(arrayList);
            aVar2.n(aVar.values());
        }
        if (iVarQ != null) {
            throw null;
        }
        x(aVar, aVar2);
        return aVar2;
    }

    public static b.d.a<String, View> i(l lVar, b.d.a<String, String> aVar, Object obj, e eVar) {
        b.f.e.i iVarS;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = eVar.f1934d;
        b.d.a<String, View> aVar2 = new b.d.a<>();
        lVar.j(aVar2, fragment.Y0());
        b.j.a.a aVar3 = eVar.f1936f;
        if (eVar.f1935e) {
            iVarS = fragment.q();
            arrayList = aVar3.p;
        } else {
            iVarS = fragment.s();
            arrayList = aVar3.o;
        }
        aVar2.n(arrayList);
        if (iVarS != null) {
            throw null;
        }
        aVar.n(aVar2.keySet());
        return aVar2;
    }

    public static l j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objR = fragment.r();
            if (objR != null) {
                arrayList.add(objR);
            }
            Object objD = fragment.D();
            if (objD != null) {
                arrayList.add(objD);
            }
            Object objF = fragment.F();
            if (objF != null) {
                arrayList.add(objF);
            }
        }
        if (fragment2 != null) {
            Object objP = fragment2.p();
            if (objP != null) {
                arrayList.add(objP);
            }
            Object objA = fragment2.A();
            if (objA != null) {
                arrayList.add(objA);
            }
            Object objE = fragment2.E();
            if (objE != null) {
                arrayList.add(objE);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        l lVar = f1922b;
        if (lVar != null && g(lVar, arrayList)) {
            return lVar;
        }
        l lVar2 = f1923c;
        if (lVar2 != null && g(lVar2, arrayList)) {
            return lVar2;
        }
        if (lVar == null && lVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList<View> k(l lVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewI = fragment.I();
        if (viewI != null) {
            lVar.f(arrayList2, viewI);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        lVar.b(obj, arrayList2);
        return arrayList2;
    }

    public static Object l(l lVar, ViewGroup viewGroup, View view, b.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        b.d.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f1931a;
        Fragment fragment2 = eVar.f1934d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1932b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(lVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        b.d.a<String, View> aVarI = i(lVar, aVar2, objT, eVar);
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
            lVar.y(obj3, view, arrayList);
            z(lVar, obj3, obj2, aVarI, eVar.f1935e, eVar.f1936f);
            if (obj != null) {
                lVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        q.a(viewGroup, new d(lVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(l lVar, ViewGroup viewGroup, View view, b.d.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f1931a;
        Fragment fragment2 = eVar.f1934d;
        if (fragment != null) {
            fragment.Y0().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.f1932b;
        Object objT = aVar.isEmpty() ? null : t(lVar, fragment, fragment2, z);
        b.d.a<String, View> aVarI = i(lVar, aVar, objT, eVar);
        b.d.a<String, View> aVarH = h(lVar, aVar, objT, eVar);
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
            lVar.y(obj3, view, arrayList);
            z(lVar, obj3, obj2, aVarI, eVar.f1935e, eVar.f1936f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, eVar, obj, z);
            if (viewS != null) {
                lVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        q.a(viewGroup, new c(fragment, fragment2, z, aVarH, view2, lVar, rect));
        return obj3;
    }

    public static void n(g gVar, int i2, e eVar, View view, b.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        l lVarJ;
        Object obj;
        ViewGroup viewGroup = gVar.T3.f() ? (ViewGroup) gVar.T3.e(i2) : null;
        if (viewGroup == null || (lVarJ = j((fragment2 = eVar.f1934d), (fragment = eVar.f1931a))) == null) {
            return;
        }
        boolean z = eVar.f1932b;
        boolean z2 = eVar.f1935e;
        Object objQ = q(lVarJ, fragment, z);
        Object objR = r(lVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(lVarJ, viewGroup, view, aVar, eVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(lVarJ, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListK == null || arrayListK.isEmpty()) ? null : obj;
        lVarJ.a(objQ, view);
        Object objU = u(lVarJ, objQ, obj2, objL, fragment, eVar.f1932b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            lVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(lVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            lVarJ.w(viewGroup, arrayList2, aVar);
            lVarJ.c(viewGroup, objU);
            lVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    public static void o(g gVar, int i2, e eVar, View view, b.d.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        l lVarJ;
        Object obj;
        ViewGroup viewGroup = gVar.T3.f() ? (ViewGroup) gVar.T3.e(i2) : null;
        if (viewGroup == null || (lVarJ = j((fragment2 = eVar.f1934d), (fragment = eVar.f1931a))) == null) {
            return;
        }
        boolean z = eVar.f1932b;
        boolean z2 = eVar.f1935e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(lVarJ, fragment, z);
        Object objR = r(lVarJ, fragment2, z2);
        Object objM = m(lVarJ, viewGroup, view, aVar, eVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(lVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(lVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(lVarJ, objQ, obj, objM, fragment, z);
        if (objU != null) {
            v(lVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = lVarJ.o(arrayList);
            lVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            lVarJ.c(viewGroup, objU);
            lVarJ.x(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            lVarJ.z(objM, arrayList2, arrayList);
        }
    }

    public static e p(e eVar, SparseArray<e> sparseArray, int i2) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i2, eVar2);
        return eVar2;
    }

    public static Object q(l lVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return lVar.g(z ? fragment.A() : fragment.p());
    }

    public static Object r(l lVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return lVar.g(z ? fragment.D() : fragment.r());
    }

    public static View s(b.d.a<String, View> aVar, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        b.j.a.a aVar2 = eVar.f1933c;
        if (obj == null || aVar == null || (arrayList = aVar2.o) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z ? aVar2.o.get(0) : aVar2.p.get(0));
    }

    public static Object t(l lVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return lVar.A(lVar.g(z ? fragment2.F() : fragment.E()));
    }

    public static Object u(l lVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.k() : fragment.j() ? lVar.n(obj2, obj, obj3) : lVar.m(obj2, obj, obj3);
    }

    public static void v(l lVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.K3 && fragment.Y3 && fragment.n4) {
            fragment.e1(true);
            lVar.r(obj, fragment.I(), arrayList);
            q.a(fragment.f4, new a(arrayList));
        }
    }

    public static l w() {
        try {
            return (l) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void x(b.d.a<String, String> aVar, b.d.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.l(size))) {
                aVar.j(size);
            }
        }
    }

    public static void y(l lVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        q.a(viewGroup, new b(obj, lVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static void z(l lVar, Object obj, Object obj2, b.d.a<String, View> aVar, boolean z, b.j.a.a aVar2) {
        ArrayList<String> arrayList = aVar2.o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z ? aVar2.p.get(0) : aVar2.o.get(0));
        lVar.v(obj, view);
        if (obj2 != null) {
            lVar.v(obj2, view);
        }
    }
}
