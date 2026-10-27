package v0;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import o0.q;
import o0.t;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f8448a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m f8449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f8450c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f8451d;

        public a(ArrayList arrayList) {
            this.f8451d = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            k.A(this.f8451d, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f8452d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ m f8453e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f8454f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ Fragment f8455g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ ArrayList f8456h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ ArrayList f8457i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ ArrayList f8458j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ Object f8459k;

        public b(Object obj, m mVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f8452d = obj;
            this.f8453e = mVar;
            this.f8454f = view;
            this.f8455g = fragment;
            this.f8456h = arrayList;
            this.f8457i = arrayList2;
            this.f8458j = arrayList3;
            this.f8459k = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f8452d;
            if (obj != null) {
                this.f8453e.p(obj, this.f8454f);
                this.f8457i.addAll(k.k(this.f8453e, this.f8452d, this.f8455g, this.f8456h, this.f8454f));
            }
            if (this.f8458j != null) {
                if (this.f8459k != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f8454f);
                    this.f8453e.q(this.f8459k, this.f8458j, arrayList);
                }
                this.f8458j.clear();
                this.f8458j.add(this.f8454f);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Fragment f8460d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Fragment f8461e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ boolean f8462f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ v.a f8463g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ View f8464h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ m f8465i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ Rect f8466j;

        public c(Fragment fragment, Fragment fragment2, boolean z7, v.a aVar, View view, m mVar, Rect rect) {
            this.f8460d = fragment;
            this.f8461e = fragment2;
            this.f8462f = z7;
            this.f8463g = aVar;
            this.f8464h = view;
            this.f8465i = mVar;
            this.f8466j = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            k.f(this.f8460d, this.f8461e, this.f8462f, this.f8463g, false);
            View view = this.f8464h;
            if (view != null) {
                this.f8465i.k(view, this.f8466j);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class d implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ m f8467d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ v.a f8468e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Object f8469f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ e f8470g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ ArrayList f8471h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ View f8472i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ Fragment f8473j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ Fragment f8474k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ boolean f8475l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ ArrayList f8476m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final /* synthetic */ Object f8477n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final /* synthetic */ Rect f8478o;

        public d(m mVar, v.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z7, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f8467d = mVar;
            this.f8468e = aVar;
            this.f8469f = obj;
            this.f8470g = eVar;
            this.f8471h = arrayList;
            this.f8472i = view;
            this.f8473j = fragment;
            this.f8474k = fragment2;
            this.f8475l = z7;
            this.f8476m = arrayList2;
            this.f8477n = obj2;
            this.f8478o = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            v.a<String, View> aVarH = k.h(this.f8467d, this.f8468e, this.f8469f, this.f8470g);
            if (aVarH != null) {
                this.f8471h.addAll(aVarH.values());
                this.f8471h.add(this.f8472i);
            }
            k.f(this.f8473j, this.f8474k, this.f8475l, aVarH, false);
            Object obj = this.f8469f;
            if (obj != null) {
                this.f8467d.z(obj, this.f8476m, this.f8471h);
                View viewS = k.s(aVarH, this.f8470g, this.f8477n, this.f8475l);
                if (viewS != null) {
                    this.f8467d.k(viewS, this.f8478o);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f8479a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f8480b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public v0.a f8481c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f8482d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8483e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public v0.a f8484f;
    }

    static {
        f8449b = Build.VERSION.SDK_INT >= 21 ? new l() : null;
        f8450c = w();
    }

    public static void A(ArrayList<View> arrayList, int i8) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i8);
        }
    }

    public static void B(h hVar, ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2, int i8, int i9, boolean z7) {
        if (hVar.f8382v < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i10 = i8; i10 < i9; i10++) {
            v0.a aVar = arrayList.get(i10);
            if (arrayList2.get(i10).booleanValue()) {
                e(aVar, sparseArray, z7);
            } else {
                c(aVar, sparseArray, z7);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(hVar.f8383w.e());
            int size = sparseArray.size();
            for (int i11 = 0; i11 < size; i11++) {
                int iKeyAt = sparseArray.keyAt(i11);
                v.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i8, i9);
                e eVar = (e) sparseArray.valueAt(i11);
                if (z7) {
                    o(hVar, iKeyAt, eVar, view, aVarD);
                } else {
                    n(hVar, iKeyAt, eVar, view, aVarD);
                }
            }
        }
    }

    public static void a(ArrayList<View> arrayList, v.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewM = aVar.m(size);
            if (collection.contains(t.I(viewM))) {
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
    public static void b(v0.a r16, v0.j.a r17, android.util.SparseArray<v0.k.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: v0.k.b(v0.a, v0.j$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(v0.a aVar, SparseArray<e> sparseArray, boolean z7) {
        int size = aVar.f8422a.size();
        for (int i8 = 0; i8 < size; i8++) {
            b(aVar, aVar.f8422a.get(i8), sparseArray, false, z7);
        }
    }

    public static v.a<String, String> d(int i8, ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2, int i9, int i10) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        v.a<String, String> aVar = new v.a<>();
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            v0.a aVar2 = arrayList.get(i11);
            if (aVar2.v(i8)) {
                boolean zBooleanValue = arrayList2.get(i11).booleanValue();
                ArrayList<String> arrayList5 = aVar2.f8436o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.f8436o;
                        arrayList4 = aVar2.f8437p;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.f8436o;
                        arrayList3 = aVar2.f8437p;
                        arrayList4 = arrayList6;
                    }
                    for (int i12 = 0; i12 < size; i12++) {
                        String str = arrayList4.get(i12);
                        String str2 = arrayList3.get(i12);
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

    public static void e(v0.a aVar, SparseArray<e> sparseArray, boolean z7) {
        if (aVar.f8354s.f8384x.c()) {
            for (int size = aVar.f8422a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f8422a.get(size), sparseArray, true, z7);
            }
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z7, v.a<String, View> aVar, boolean z8) {
        if ((z7 ? fragment2.getEnterTransitionCallback() : fragment.getEnterTransitionCallback()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i8 = 0; i8 < size; i8++) {
                arrayList2.add(aVar.i(i8));
                arrayList.add(aVar.m(i8));
            }
            if (!z8) {
                throw null;
            }
            throw null;
        }
    }

    public static boolean g(m mVar, List<Object> list) {
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (!mVar.e(list.get(i8))) {
                return false;
            }
        }
        return true;
    }

    public static v.a<String, View> h(m mVar, v.a<String, String> aVar, Object obj, e eVar) {
        d0.i enterTransitionCallback;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f8479a;
        View view = fragment.getView();
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        v.a<String, View> aVar2 = new v.a<>();
        mVar.j(aVar2, view);
        v0.a aVar3 = eVar.f8481c;
        if (eVar.f8480b) {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.f8436o;
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.f8437p;
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

    public static v.a<String, View> i(m mVar, v.a<String, String> aVar, Object obj, e eVar) {
        d0.i exitTransitionCallback;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = eVar.f8482d;
        v.a<String, View> aVar2 = new v.a<>();
        mVar.j(aVar2, fragment.requireView());
        v0.a aVar3 = eVar.f8484f;
        if (eVar.f8483e) {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.f8437p;
        } else {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.f8436o;
        }
        aVar2.o(arrayList);
        if (exitTransitionCallback != null) {
            throw null;
        }
        aVar.o(aVar2.keySet());
        return aVar2;
    }

    public static m j(Fragment fragment, Fragment fragment2) {
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
        m mVar = f8449b;
        if (mVar != null && g(mVar, arrayList)) {
            return mVar;
        }
        m mVar2 = f8450c;
        if (mVar2 != null && g(mVar2, arrayList)) {
            return mVar2;
        }
        if (mVar == null && mVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList<View> k(m mVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            mVar.f(arrayList2, view2);
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

    public static Object l(m mVar, ViewGroup viewGroup, View view, v.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        v.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f8479a;
        Fragment fragment2 = eVar.f8482d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z7 = eVar.f8480b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(mVar, fragment, fragment2, z7);
            aVar2 = aVar;
        }
        v.a<String, View> aVarI = i(mVar, aVar2, objT, eVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarI.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z7, aVarI, true);
        if (obj3 != null) {
            rect = new Rect();
            mVar.y(obj3, view, arrayList);
            z(mVar, obj3, obj2, aVarI, eVar.f8483e, eVar.f8484f);
            if (obj != null) {
                mVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        q.a(viewGroup, new d(mVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z7, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(m mVar, ViewGroup viewGroup, View view, v.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f8479a;
        Fragment fragment2 = eVar.f8482d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z7 = eVar.f8480b;
        Object objT = aVar.isEmpty() ? null : t(mVar, fragment, fragment2, z7);
        v.a<String, View> aVarI = i(mVar, aVar, objT, eVar);
        v.a<String, View> aVarH = h(mVar, aVar, objT, eVar);
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
        f(fragment, fragment2, z7, aVarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            mVar.y(obj3, view, arrayList);
            z(mVar, obj3, obj2, aVarI, eVar.f8483e, eVar.f8484f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, eVar, obj, z7);
            if (viewS != null) {
                mVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        q.a(viewGroup, new c(fragment, fragment2, z7, aVarH, view2, mVar, rect));
        return obj3;
    }

    public static void n(h hVar, int i8, e eVar, View view, v.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        m mVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.f8384x.c() ? (ViewGroup) hVar.f8384x.b(i8) : null;
        if (viewGroup == null || (mVarJ = j((fragment2 = eVar.f8482d), (fragment = eVar.f8479a))) == null) {
            return;
        }
        boolean z7 = eVar.f8480b;
        boolean z8 = eVar.f8483e;
        Object objQ = q(mVarJ, fragment, z7);
        Object objR = r(mVarJ, fragment2, z8);
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
        Object objU = u(mVarJ, objQ, obj2, objL, fragment, eVar.f8480b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            mVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(mVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            mVarJ.w(viewGroup, arrayList2, aVar);
            mVarJ.c(viewGroup, objU);
            mVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    public static void o(h hVar, int i8, e eVar, View view, v.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        m mVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.f8384x.c() ? (ViewGroup) hVar.f8384x.b(i8) : null;
        if (viewGroup == null || (mVarJ = j((fragment2 = eVar.f8482d), (fragment = eVar.f8479a))) == null) {
            return;
        }
        boolean z7 = eVar.f8480b;
        boolean z8 = eVar.f8483e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(mVarJ, fragment, z7);
        Object objR = r(mVarJ, fragment2, z8);
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
        Object objU = u(mVarJ, objQ, obj, objM, fragment, z7);
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

    public static e p(e eVar, SparseArray<e> sparseArray, int i8) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i8, eVar2);
        return eVar2;
    }

    public static Object q(m mVar, Fragment fragment, boolean z7) {
        if (fragment == null) {
            return null;
        }
        return mVar.g(z7 ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    public static Object r(m mVar, Fragment fragment, boolean z7) {
        if (fragment == null) {
            return null;
        }
        return mVar.g(z7 ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    public static View s(v.a<String, View> aVar, e eVar, Object obj, boolean z7) {
        ArrayList<String> arrayList;
        v0.a aVar2 = eVar.f8481c;
        if (obj == null || aVar == null || (arrayList = aVar2.f8436o) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z7 ? aVar2.f8436o.get(0) : aVar2.f8437p.get(0));
    }

    public static Object t(m mVar, Fragment fragment, Fragment fragment2, boolean z7) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return mVar.A(mVar.g(z7 ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    public static Object u(m mVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z7) {
        return (obj == null || obj2 == null || fragment == null) ? true : z7 ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? mVar.n(obj2, obj, obj3) : mVar.m(obj2, obj, obj3);
    }

    public static void v(m mVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            mVar.r(obj, fragment.getView(), arrayList);
            q.a(fragment.mContainer, new a(arrayList));
        }
    }

    public static m w() {
        try {
            return (m) Class.forName("k1.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void x(v.a<String, String> aVar, v.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.m(size))) {
                aVar.k(size);
            }
        }
    }

    public static void y(m mVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        q.a(viewGroup, new b(obj, mVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static void z(m mVar, Object obj, Object obj2, v.a<String, View> aVar, boolean z7, v0.a aVar2) {
        ArrayList<String> arrayList = aVar2.f8436o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z7 ? aVar2.f8437p.get(0) : aVar2.f8436o.get(0));
        mVar.v(obj, view);
        if (obj2 != null) {
            mVar.v(obj2, view);
        }
    }
}
