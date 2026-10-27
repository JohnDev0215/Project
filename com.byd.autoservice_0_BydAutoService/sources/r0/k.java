package r0;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import l0.q;
import l0.t;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4770a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m f4771b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f4772c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4773b;

        public a(ArrayList arrayList) {
            this.f4773b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            k.A(this.f4773b, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f4774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ m f4775c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f4776d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Fragment f4777e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4778f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4779g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4780h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Object f4781i;

        public b(Object obj, m mVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f4774b = obj;
            this.f4775c = mVar;
            this.f4776d = view;
            this.f4777e = fragment;
            this.f4778f = arrayList;
            this.f4779g = arrayList2;
            this.f4780h = arrayList3;
            this.f4781i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f4774b;
            if (obj != null) {
                this.f4775c.p(obj, this.f4776d);
                this.f4779g.addAll(k.k(this.f4775c, this.f4774b, this.f4777e, this.f4778f, this.f4776d));
            }
            if (this.f4780h != null) {
                if (this.f4781i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f4776d);
                    this.f4775c.q(this.f4781i, this.f4780h, arrayList);
                }
                this.f4780h.clear();
                this.f4780h.add(this.f4776d);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f4782b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f4783c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f4784d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ s.a f4785e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f4786f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ m f4787g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Rect f4788h;

        public c(Fragment fragment, Fragment fragment2, boolean z3, s.a aVar, View view, m mVar, Rect rect) {
            this.f4782b = fragment;
            this.f4783c = fragment2;
            this.f4784d = z3;
            this.f4785e = aVar;
            this.f4786f = view;
            this.f4787g = mVar;
            this.f4788h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            k.f(this.f4782b, this.f4783c, this.f4784d, this.f4785e, false);
            View view = this.f4786f;
            if (view != null) {
                this.f4787g.k(view, this.f4788h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ m f4789b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ s.a f4790c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f4791d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ e f4792e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4793f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ View f4794g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Fragment f4795h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Fragment f4796i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ boolean f4797j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ ArrayList f4798k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ Object f4799l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ Rect f4800m;

        public d(m mVar, s.a aVar, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z3, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f4789b = mVar;
            this.f4790c = aVar;
            this.f4791d = obj;
            this.f4792e = eVar;
            this.f4793f = arrayList;
            this.f4794g = view;
            this.f4795h = fragment;
            this.f4796i = fragment2;
            this.f4797j = z3;
            this.f4798k = arrayList2;
            this.f4799l = obj2;
            this.f4800m = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.a<String, View> aVarH = k.h(this.f4789b, this.f4790c, this.f4791d, this.f4792e);
            if (aVarH != null) {
                this.f4793f.addAll(aVarH.values());
                this.f4793f.add(this.f4794g);
            }
            k.f(this.f4795h, this.f4796i, this.f4797j, aVarH, false);
            Object obj = this.f4791d;
            if (obj != null) {
                this.f4789b.z(obj, this.f4798k, this.f4793f);
                View viewS = k.s(aVarH, this.f4792e, this.f4799l, this.f4797j);
                if (viewS != null) {
                    this.f4789b.k(viewS, this.f4800m);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f4801a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f4802b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public r0.a f4803c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f4804d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4805e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public r0.a f4806f;
    }

    static {
        f4771b = Build.VERSION.SDK_INT >= 21 ? new l() : null;
        f4772c = w();
    }

    public static void A(ArrayList<View> arrayList, int i3) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i3);
        }
    }

    public static void B(h hVar, ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4, boolean z3) {
        if (hVar.f4702t < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i5 = i3; i5 < i4; i5++) {
            r0.a aVar = arrayList.get(i5);
            if (arrayList2.get(i5).booleanValue()) {
                e(aVar, sparseArray, z3);
            } else {
                c(aVar, sparseArray, z3);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(hVar.f4703u.e());
            int size = sparseArray.size();
            for (int i6 = 0; i6 < size; i6++) {
                int iKeyAt = sparseArray.keyAt(i6);
                s.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i3, i4);
                e eVar = (e) sparseArray.valueAt(i6);
                if (z3) {
                    o(hVar, iKeyAt, eVar, view, aVarD);
                } else {
                    n(hVar, iKeyAt, eVar, view, aVarD);
                }
            }
        }
    }

    public static void a(ArrayList<View> arrayList, s.a<String, View> aVar, Collection<String> collection) {
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
    public static void b(r0.a r16, r0.j.a r17, android.util.SparseArray<r0.k.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.k.b(r0.a, r0.j$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(r0.a aVar, SparseArray<e> sparseArray, boolean z3) {
        int size = aVar.f4744a.size();
        for (int i3 = 0; i3 < size; i3++) {
            b(aVar, aVar.f4744a.get(i3), sparseArray, false, z3);
        }
    }

    public static s.a<String, String> d(int i3, ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2, int i4, int i5) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        s.a<String, String> aVar = new s.a<>();
        for (int i6 = i5 - 1; i6 >= i4; i6--) {
            r0.a aVar2 = arrayList.get(i6);
            if (aVar2.p(i3)) {
                boolean zBooleanValue = arrayList2.get(i6).booleanValue();
                ArrayList<String> arrayList5 = aVar2.f4758o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = aVar2.f4758o;
                        arrayList4 = aVar2.f4759p;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.f4758o;
                        arrayList3 = aVar2.f4759p;
                        arrayList4 = arrayList6;
                    }
                    for (int i7 = 0; i7 < size; i7++) {
                        String str = arrayList4.get(i7);
                        String str2 = arrayList3.get(i7);
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

    public static void e(r0.a aVar, SparseArray<e> sparseArray, boolean z3) {
        if (aVar.f4674s.f4704v.c()) {
            for (int size = aVar.f4744a.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f4744a.get(size), sparseArray, true, z3);
            }
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z3, s.a<String, View> aVar, boolean z4) {
        if ((z3 ? fragment2.getEnterTransitionCallback() : fragment.getEnterTransitionCallback()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i3 = 0; i3 < size; i3++) {
                arrayList2.add(aVar.i(i3));
                arrayList.add(aVar.m(i3));
            }
            if (!z4) {
                throw null;
            }
            throw null;
        }
    }

    public static boolean g(m mVar, List<Object> list) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (!mVar.e(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public static s.a<String, View> h(m mVar, s.a<String, String> aVar, Object obj, e eVar) {
        a0.h enterTransitionCallback;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f4801a;
        View view = fragment.getView();
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        s.a<String, View> aVar2 = new s.a<>();
        mVar.j(aVar2, view);
        r0.a aVar3 = eVar.f4803c;
        if (eVar.f4802b) {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.f4758o;
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.f4759p;
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

    public static s.a<String, View> i(m mVar, s.a<String, String> aVar, Object obj, e eVar) {
        a0.h exitTransitionCallback;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = eVar.f4804d;
        s.a<String, View> aVar2 = new s.a<>();
        mVar.j(aVar2, fragment.requireView());
        r0.a aVar3 = eVar.f4806f;
        if (eVar.f4805e) {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.f4759p;
        } else {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.f4758o;
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
        m mVar = f4771b;
        if (mVar != null && g(mVar, arrayList)) {
            return mVar;
        }
        m mVar2 = f4772c;
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

    public static Object l(m mVar, ViewGroup viewGroup, View view, s.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        s.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f4801a;
        Fragment fragment2 = eVar.f4804d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z3 = eVar.f4802b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(mVar, fragment, fragment2, z3);
            aVar2 = aVar;
        }
        s.a<String, View> aVarI = i(mVar, aVar2, objT, eVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(aVarI.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z3, aVarI, true);
        if (obj3 != null) {
            rect = new Rect();
            mVar.y(obj3, view, arrayList);
            z(mVar, obj3, obj2, aVarI, eVar.f4805e, eVar.f4806f);
            if (obj != null) {
                mVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        q.a(viewGroup, new d(mVar, aVar, obj3, eVar, arrayList2, view, fragment, fragment2, z3, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(m mVar, ViewGroup viewGroup, View view, s.a<String, String> aVar, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f4801a;
        Fragment fragment2 = eVar.f4804d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z3 = eVar.f4802b;
        Object objT = aVar.isEmpty() ? null : t(mVar, fragment, fragment2, z3);
        s.a<String, View> aVarI = i(mVar, aVar, objT, eVar);
        s.a<String, View> aVarH = h(mVar, aVar, objT, eVar);
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
        f(fragment, fragment2, z3, aVarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            mVar.y(obj3, view, arrayList);
            z(mVar, obj3, obj2, aVarI, eVar.f4805e, eVar.f4806f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, eVar, obj, z3);
            if (viewS != null) {
                mVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        q.a(viewGroup, new c(fragment, fragment2, z3, aVarH, view2, mVar, rect));
        return obj3;
    }

    public static void n(h hVar, int i3, e eVar, View view, s.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        m mVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.f4704v.c() ? (ViewGroup) hVar.f4704v.b(i3) : null;
        if (viewGroup == null || (mVarJ = j((fragment2 = eVar.f4804d), (fragment = eVar.f4801a))) == null) {
            return;
        }
        boolean z3 = eVar.f4802b;
        boolean z4 = eVar.f4805e;
        Object objQ = q(mVarJ, fragment, z3);
        Object objR = r(mVarJ, fragment2, z4);
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
        Object objU = u(mVarJ, objQ, obj2, objL, fragment, eVar.f4802b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            mVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(mVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            mVarJ.w(viewGroup, arrayList2, aVar);
            mVarJ.c(viewGroup, objU);
            mVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    public static void o(h hVar, int i3, e eVar, View view, s.a<String, String> aVar) {
        Fragment fragment;
        Fragment fragment2;
        m mVarJ;
        Object obj;
        ViewGroup viewGroup = hVar.f4704v.c() ? (ViewGroup) hVar.f4704v.b(i3) : null;
        if (viewGroup == null || (mVarJ = j((fragment2 = eVar.f4804d), (fragment = eVar.f4801a))) == null) {
            return;
        }
        boolean z3 = eVar.f4802b;
        boolean z4 = eVar.f4805e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(mVarJ, fragment, z3);
        Object objR = r(mVarJ, fragment2, z4);
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
        Object objU = u(mVarJ, objQ, obj, objM, fragment, z3);
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

    public static e p(e eVar, SparseArray<e> sparseArray, int i3) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i3, eVar2);
        return eVar2;
    }

    public static Object q(m mVar, Fragment fragment, boolean z3) {
        if (fragment == null) {
            return null;
        }
        return mVar.g(z3 ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    public static Object r(m mVar, Fragment fragment, boolean z3) {
        if (fragment == null) {
            return null;
        }
        return mVar.g(z3 ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    public static View s(s.a<String, View> aVar, e eVar, Object obj, boolean z3) {
        ArrayList<String> arrayList;
        r0.a aVar2 = eVar.f4803c;
        if (obj == null || aVar == null || (arrayList = aVar2.f4758o) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z3 ? aVar2.f4758o.get(0) : aVar2.f4759p.get(0));
    }

    public static Object t(m mVar, Fragment fragment, Fragment fragment2, boolean z3) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return mVar.A(mVar.g(z3 ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    public static Object u(m mVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z3) {
        return (obj == null || obj2 == null || fragment == null) ? true : z3 ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? mVar.n(obj2, obj, obj3) : mVar.m(obj2, obj, obj3);
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
            return (m) Class.forName("y0.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void x(s.a<String, String> aVar, s.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.m(size))) {
                aVar.k(size);
            }
        }
    }

    public static void y(m mVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        q.a(viewGroup, new b(obj, mVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static void z(m mVar, Object obj, Object obj2, s.a<String, View> aVar, boolean z3, r0.a aVar2) {
        ArrayList<String> arrayList = aVar2.f4758o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z3 ? aVar2.f4759p.get(0) : aVar2.f4758o.get(0));
        mVar.v(obj, view);
        if (obj2 != null) {
            mVar.v(obj2, view);
        }
    }
}
