package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f717a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final u f718b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final u f719c;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ g f720c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ androidx.core.os.b e;

        a(g gVar, Fragment fragment, androidx.core.os.b bVar) {
            this.f720c = gVar;
            this.d = fragment;
            this.e = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f720c.a(this.d, this.e);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f721c;

        b(ArrayList arrayList) {
            this.f721c = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.B(this.f721c, 4);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ g f722c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ androidx.core.os.b e;

        c(g gVar, Fragment fragment, androidx.core.os.b bVar) {
            this.f722c = gVar;
            this.d = fragment;
            this.e = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f722c.a(this.d, this.e);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f723c;
        final /* synthetic */ u d;
        final /* synthetic */ View e;
        final /* synthetic */ Fragment f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ ArrayList h;
        final /* synthetic */ ArrayList i;
        final /* synthetic */ Object j;

        d(Object obj, u uVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f723c = obj;
            this.d = uVar;
            this.e = view;
            this.f = fragment;
            this.g = arrayList;
            this.h = arrayList2;
            this.i = arrayList3;
            this.j = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f723c;
            if (obj != null) {
                this.d.p(obj, this.e);
                this.h.addAll(s.k(this.d, this.f723c, this.f, this.g, this.e));
            }
            if (this.i != null) {
                if (this.j != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.e);
                    this.d.q(this.j, this.i, arrayList);
                }
                this.i.clear();
                this.i.add(this.e);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f724c;
        final /* synthetic */ Fragment d;
        final /* synthetic */ boolean e;
        final /* synthetic */ b.a.a f;
        final /* synthetic */ View g;
        final /* synthetic */ u h;
        final /* synthetic */ Rect i;

        e(Fragment fragment, Fragment fragment2, boolean z, b.a.a aVar, View view, u uVar, Rect rect) {
            this.f724c = fragment;
            this.d = fragment2;
            this.e = z;
            this.f = aVar;
            this.g = view;
            this.h = uVar;
            this.i = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.f(this.f724c, this.d, this.e, this.f, false);
            View view = this.g;
            if (view != null) {
                this.h.k(view, this.i);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ u f725c;
        final /* synthetic */ b.a.a d;
        final /* synthetic */ Object e;
        final /* synthetic */ h f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ View h;
        final /* synthetic */ Fragment i;
        final /* synthetic */ Fragment j;
        final /* synthetic */ boolean k;
        final /* synthetic */ ArrayList l;
        final /* synthetic */ Object m;
        final /* synthetic */ Rect n;

        f(u uVar, b.a.a aVar, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f725c = uVar;
            this.d = aVar;
            this.e = obj;
            this.f = hVar;
            this.g = arrayList;
            this.h = view;
            this.i = fragment;
            this.j = fragment2;
            this.k = z;
            this.l = arrayList2;
            this.m = obj2;
            this.n = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.a.a<String, View> aVarH = s.h(this.f725c, this.d, this.e, this.f);
            if (aVarH != null) {
                this.g.addAll(aVarH.values());
                this.g.add(this.h);
            }
            s.f(this.i, this.j, this.k, aVarH, false);
            Object obj = this.e;
            if (obj != null) {
                this.f725c.A(obj, this.l, this.g);
                View viewT = s.t(aVarH, this.f, this.m, this.k);
                if (viewT != null) {
                    this.f725c.k(viewT, this.n);
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface g {
        void a(@NonNull Fragment fragment, @NonNull androidx.core.os.b bVar);

        void b(@NonNull Fragment fragment, @NonNull androidx.core.os.b bVar);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f726a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f727b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f728c;
        public Fragment d;
        public boolean e;
        public androidx.fragment.app.a f;

        h() {
        }
    }

    static {
        f718b = Build.VERSION.SDK_INT >= 21 ? new t() : null;
        f719c = x();
    }

    private static void A(u uVar, Object obj, Object obj2, b.a.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        ArrayList<String> arrayList = aVar2.m;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z ? aVar2.n.get(0) : aVar2.m.get(0));
        uVar.v(obj, view);
        if (obj2 != null) {
            uVar.v(obj2, view);
        }
    }

    static void B(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    static void C(@NonNull Context context, @NonNull androidx.fragment.app.e eVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z, g gVar) {
        ViewGroup viewGroup;
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
            View view = new View(context);
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                b.a.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i, i2);
                h hVar = (h) sparseArray.valueAt(i4);
                if (eVar.i() && (viewGroup = (ViewGroup) eVar.g(iKeyAt)) != null) {
                    if (z) {
                        o(viewGroup, hVar, view, aVarD, gVar);
                    } else {
                        n(viewGroup, hVar, view, aVarD, gVar);
                    }
                }
            }
        }
    }

    private static void a(ArrayList<View> arrayList, b.a.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewL = aVar.l(size);
            if (collection.contains(ViewCompat.v(viewL))) {
                arrayList.add(viewL);
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

    public static void c(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        int size = aVar.f711a.size();
        for (int i = 0; i < size; i++) {
            b(aVar, aVar.f711a.get(i), sparseArray, false, z);
        }
    }

    private static b.a.a<String, String> d(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        b.a.a<String, String> aVar = new b.a.a<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i4);
            if (aVar2.u(i)) {
                boolean zBooleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = aVar2.m;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.m;
                        arrayList4 = aVar2.n;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.m;
                        arrayList3 = aVar2.n;
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

    public static void e(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        if (aVar.q.n0().i()) {
            for (int size = aVar.f711a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f711a.get(size), sparseArray, true, z);
            }
        }
    }

    static void f(Fragment fragment, Fragment fragment2, boolean z, b.a.a<String, View> aVar, boolean z2) {
        androidx.core.app.g gVarT = z ? fragment2.t() : fragment.t();
        if (gVarT != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(aVar.i(i));
                arrayList.add(aVar.l(i));
            }
            if (z2) {
                gVarT.c(arrayList2, arrayList, null);
            } else {
                gVarT.b(arrayList2, arrayList, null);
            }
        }
    }

    private static boolean g(u uVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!uVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    static b.a.a<String, View> h(u uVar, b.a.a<String, String> aVar, Object obj, h hVar) {
        androidx.core.app.g gVarT;
        ArrayList<String> arrayList;
        String strQ;
        Fragment fragment = hVar.f726a;
        View viewK = fragment.K();
        if (aVar.isEmpty() || obj == null || viewK == null) {
            aVar.clear();
            return null;
        }
        b.a.a<String, View> aVar2 = new b.a.a<>();
        uVar.j(aVar2, viewK);
        androidx.fragment.app.a aVar3 = hVar.f728c;
        if (hVar.f727b) {
            gVarT = fragment.v();
            arrayList = aVar3.m;
        } else {
            gVarT = fragment.t();
            arrayList = aVar3.n;
        }
        if (arrayList != null) {
            aVar2.p(arrayList);
            aVar2.p(aVar.values());
        }
        if (gVarT != null) {
            gVarT.a(arrayList, aVar2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view = aVar2.get(str);
                if (view == null) {
                    String strQ2 = q(aVar, str);
                    if (strQ2 != null) {
                        aVar.remove(strQ2);
                    }
                } else if (!str.equals(ViewCompat.v(view)) && (strQ = q(aVar, str)) != null) {
                    aVar.put(strQ, ViewCompat.v(view));
                }
            }
        } else {
            y(aVar, aVar2);
        }
        return aVar2;
    }

    private static b.a.a<String, View> i(u uVar, b.a.a<String, String> aVar, Object obj, h hVar) {
        androidx.core.app.g gVarV;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = hVar.d;
        b.a.a<String, View> aVar2 = new b.a.a<>();
        uVar.j(aVar2, fragment.e1());
        androidx.fragment.app.a aVar3 = hVar.f;
        if (hVar.e) {
            gVarV = fragment.t();
            arrayList = aVar3.n;
        } else {
            gVarV = fragment.v();
            arrayList = aVar3.m;
        }
        if (arrayList != null) {
            aVar2.p(arrayList);
        }
        if (gVarV != null) {
            gVarV.a(arrayList, aVar2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view = aVar2.get(str);
                if (view == null) {
                    aVar.remove(str);
                } else if (!str.equals(ViewCompat.v(view))) {
                    aVar.put(ViewCompat.v(view), aVar.remove(str));
                }
            }
        } else {
            aVar.p(aVar2.keySet());
        }
        return aVar2;
    }

    private static u j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object objU = fragment.u();
            if (objU != null) {
                arrayList.add(objU);
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
            Object objS = fragment2.s();
            if (objS != null) {
                arrayList.add(objS);
            }
            Object objC = fragment2.C();
            if (objC != null) {
                arrayList.add(objC);
            }
            Object objF = fragment2.F();
            if (objF != null) {
                arrayList.add(objF);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        u uVar = f718b;
        if (uVar != null && g(uVar, arrayList)) {
            return f718b;
        }
        u uVar2 = f719c;
        if (uVar2 != null && g(uVar2, arrayList)) {
            return f719c;
        }
        if (f718b == null && f719c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(u uVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View viewK = fragment.K();
        if (viewK != null) {
            uVar.f(arrayList2, viewK);
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

    private static Object l(u uVar, ViewGroup viewGroup, View view, b.a.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objU;
        b.a.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.f726a;
        Fragment fragment2 = hVar.d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f727b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objU = null;
        } else {
            objU = u(uVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        b.a.a<String, View> aVarI = i(uVar, aVar2, objU, hVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarI.values());
            obj3 = objU;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, aVarI, true);
        if (obj3 != null) {
            rect = new Rect();
            uVar.z(obj3, view, arrayList);
            A(uVar, obj3, obj2, aVarI, hVar.e, hVar.f);
            if (obj != null) {
                uVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        androidx.core.view.q.a(viewGroup, new f(uVar, aVar, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(u uVar, ViewGroup viewGroup, View view, b.a.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.f726a;
        Fragment fragment2 = hVar.d;
        if (fragment != null) {
            fragment.e1().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f727b;
        Object objU = aVar.isEmpty() ? null : u(uVar, fragment, fragment2, z);
        b.a.a<String, View> aVarI = i(uVar, aVar, objU, hVar);
        b.a.a<String, View> aVarH = h(uVar, aVar, objU, hVar);
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
            obj3 = objU;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, aVarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            uVar.z(obj3, view, arrayList);
            A(uVar, obj3, obj2, aVarI, hVar.e, hVar.f);
            Rect rect2 = new Rect();
            View viewT = t(aVarH, hVar, obj, z);
            if (viewT != null) {
                uVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewT;
        } else {
            view2 = null;
            rect = null;
        }
        androidx.core.view.q.a(viewGroup, new e(fragment, fragment2, z, aVarH, view2, uVar, rect));
        return obj3;
    }

    private static void n(@NonNull ViewGroup viewGroup, h hVar, View view, b.a.a<String, String> aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.f726a;
        Fragment fragment2 = hVar.d;
        u uVarJ = j(fragment2, fragment);
        if (uVarJ == null) {
            return;
        }
        boolean z = hVar.f727b;
        boolean z2 = hVar.e;
        Object objR = r(uVarJ, fragment, z);
        Object objS = s(uVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(uVarJ, viewGroup, view, aVar, hVar, arrayList, arrayList2, objR, objS);
        if (objR == null && objL == null) {
            obj = objS;
            if (obj == null) {
                return;
            }
        } else {
            obj = objS;
        }
        ArrayList<View> arrayListK = k(uVarJ, obj, fragment2, arrayList, view);
        if (arrayListK == null || arrayListK.isEmpty()) {
            obj = null;
        }
        Object obj2 = obj;
        uVarJ.a(objR, view);
        Object objV = v(uVarJ, objR, obj2, objL, fragment, hVar.f727b);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList.size() > 0)) {
            androidx.core.os.b bVar = new androidx.core.os.b();
            gVar.b(fragment2, bVar);
            uVarJ.w(fragment2, objV, bVar, new c(gVar, fragment2, bVar));
        }
        if (objV != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            uVarJ.t(objV, objR, arrayList3, obj2, arrayListK, objL, arrayList2);
            z(uVarJ, viewGroup, fragment, view, arrayList2, objR, arrayList3, obj2, arrayListK);
            uVarJ.x(viewGroup, arrayList2, aVar);
            uVarJ.c(viewGroup, objV);
            uVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    private static void o(@NonNull ViewGroup viewGroup, h hVar, View view, b.a.a<String, String> aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.f726a;
        Fragment fragment2 = hVar.d;
        u uVarJ = j(fragment2, fragment);
        if (uVarJ == null) {
            return;
        }
        boolean z = hVar.f727b;
        boolean z2 = hVar.e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objR = r(uVarJ, fragment, z);
        Object objS = s(uVarJ, fragment2, z2);
        Object objM = m(uVarJ, viewGroup, view, aVar, hVar, arrayList2, arrayList, objR, objS);
        if (objR == null && objM == null) {
            obj = objS;
            if (obj == null) {
                return;
            }
        } else {
            obj = objS;
        }
        ArrayList<View> arrayListK = k(uVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(uVarJ, objR, fragment, arrayList, view);
        B(arrayListK2, 4);
        Object objV = v(uVarJ, objR, obj, objM, fragment, z);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList2.size() > 0)) {
            androidx.core.os.b bVar = new androidx.core.os.b();
            gVar.b(fragment2, bVar);
            uVarJ.w(fragment2, objV, bVar, new a(gVar, fragment2, bVar));
        }
        if (objV != null) {
            w(uVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = uVarJ.o(arrayList);
            uVarJ.t(objV, objR, arrayListK2, obj, arrayListK, objM, arrayList);
            uVarJ.c(viewGroup, objV);
            uVarJ.y(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            B(arrayListK2, 0);
            uVarJ.A(objM, arrayList2, arrayList);
        }
    }

    private static h p(h hVar, SparseArray<h> sparseArray, int i) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i, hVar2);
        return hVar2;
    }

    private static String q(b.a.a<String, String> aVar, String str) {
        int size = aVar.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(aVar.l(i))) {
                return aVar.i(i);
            }
        }
        return null;
    }

    private static Object r(u uVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return uVar.g(z ? fragment.C() : fragment.s());
    }

    private static Object s(u uVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return uVar.g(z ? fragment.E() : fragment.u());
    }

    static View t(b.a.a<String, View> aVar, h hVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar2 = hVar.f728c;
        if (obj == null || aVar == null || (arrayList = aVar2.m) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z ? aVar2.m.get(0) : aVar2.n.get(0));
    }

    private static Object u(u uVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return uVar.B(uVar.g(z ? fragment2.G() : fragment.F()));
    }

    private static Object v(u uVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.m() : fragment.l() ? uVar.n(obj2, obj, obj3) : uVar.m(obj2, obj, obj3);
    }

    private static void w(u uVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.n && fragment.B && fragment.O) {
            fragment.l1(true);
            uVar.r(obj, fragment.K(), arrayList);
            androidx.core.view.q.a(fragment.I, new b(arrayList));
        }
    }

    private static u x() {
        try {
            return (u) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static void y(@NonNull b.a.a<String, String> aVar, @NonNull b.a.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.l(size))) {
                aVar.j(size);
            }
        }
    }

    private static void z(u uVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        androidx.core.view.q.a(viewGroup, new d(obj, uVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }
}
