package b.l.d;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f2859a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final s f2860b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s f2861c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ g f2862b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f2863c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.h.i.a f2864d;

        public a(g gVar, Fragment fragment, b.h.i.a aVar) {
            this.f2862b = gVar;
            this.f2863c = fragment;
            this.f2864d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2862b.a(this.f2863c, this.f2864d);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2865b;

        public b(ArrayList arrayList) {
            this.f2865b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            q.A(this.f2865b, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ g f2866b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f2867c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.h.i.a f2868d;

        public c(g gVar, Fragment fragment, b.h.i.a aVar) {
            this.f2866b = gVar;
            this.f2867c = fragment;
            this.f2868d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2866b.a(this.f2867c, this.f2868d);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f2869b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ s f2870c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f2871d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Fragment f2872e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2873f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2874g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2875h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Object f2876i;

        public d(Object obj, s sVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f2869b = obj;
            this.f2870c = sVar;
            this.f2871d = view;
            this.f2872e = fragment;
            this.f2873f = arrayList;
            this.f2874g = arrayList2;
            this.f2875h = arrayList3;
            this.f2876i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f2869b;
            if (obj != null) {
                this.f2870c.p(obj, this.f2871d);
                this.f2874g.addAll(q.k(this.f2870c, this.f2869b, this.f2872e, this.f2873f, this.f2871d));
            }
            if (this.f2875h != null) {
                if (this.f2876i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f2871d);
                    this.f2870c.q(this.f2876i, this.f2875h, arrayList);
                }
                this.f2875h.clear();
                this.f2875h.add(this.f2871d);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public class e implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f2877b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f2878c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f2879d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ b.e.a f2880e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f2881f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ s f2882g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Rect f2883h;

        public e(Fragment fragment, Fragment fragment2, boolean z, b.e.a aVar, View view, s sVar, Rect rect) {
            this.f2877b = fragment;
            this.f2878c = fragment2;
            this.f2879d = z;
            this.f2880e = aVar;
            this.f2881f = view;
            this.f2882g = sVar;
            this.f2883h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            q.f(this.f2877b, this.f2878c, this.f2879d, this.f2880e, false);
            View view = this.f2881f;
            if (view != null) {
                this.f2882g.k(view, this.f2883h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ s f2884b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.e.a f2885c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f2886d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ h f2887e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2888f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ View f2889g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Fragment f2890h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Fragment f2891i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ ArrayList k;
        public final /* synthetic */ Object l;
        public final /* synthetic */ Rect m;

        public f(s sVar, b.e.a aVar, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f2884b = sVar;
            this.f2885c = aVar;
            this.f2886d = obj;
            this.f2887e = hVar;
            this.f2888f = arrayList;
            this.f2889g = view;
            this.f2890h = fragment;
            this.f2891i = fragment2;
            this.j = z;
            this.k = arrayList2;
            this.l = obj2;
            this.m = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.e.a<String, View> aVarH = q.h(this.f2884b, this.f2885c, this.f2886d, this.f2887e);
            if (aVarH != null) {
                this.f2888f.addAll(aVarH.values());
                this.f2888f.add(this.f2889g);
            }
            q.f(this.f2890h, this.f2891i, this.j, aVarH, false);
            Object obj = this.f2886d;
            if (obj != null) {
                this.f2884b.A(obj, this.k, this.f2888f);
                View viewS = q.s(aVarH, this.f2887e, this.l, this.j);
                if (viewS != null) {
                    this.f2884b.k(viewS, this.m);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public interface g {
        void a(Fragment fragment, b.h.i.a aVar);

        void b(Fragment fragment, b.h.i.a aVar);
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f2892a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2893b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.l.d.a f2894c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f2895d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2896e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.l.d.a f2897f;
    }

    static {
        f2860b = Build.VERSION.SDK_INT >= 21 ? new r() : null;
        f2861c = w();
    }

    public static void A(ArrayList<View> arrayList, int i2) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i2);
        }
    }

    public static void B(Context context, b.l.d.d dVar, ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, boolean z, g gVar) {
        ViewGroup viewGroup;
        SparseArray sparseArray = new SparseArray();
        for (int i4 = i2; i4 < i3; i4++) {
            b.l.d.a aVar = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size = sparseArray.size();
            for (int i5 = 0; i5 < size; i5++) {
                int iKeyAt = sparseArray.keyAt(i5);
                b.e.a<String, String> aVarD = d(iKeyAt, arrayList, arrayList2, i2, i3);
                h hVar = (h) sparseArray.valueAt(i5);
                if (dVar.d() && (viewGroup = (ViewGroup) dVar.c(iKeyAt)) != null) {
                    if (z) {
                        o(viewGroup, hVar, view, aVarD, gVar);
                    } else {
                        n(viewGroup, hVar, view, aVarD, gVar);
                    }
                }
            }
        }
    }

    public static void a(ArrayList<View> arrayList, b.e.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewM = aVar.m(size);
            if (collection.contains(b.h.m.t.H(viewM))) {
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
    public static void b(b.l.d.a r8, b.l.d.p.a r9, android.util.SparseArray<b.l.d.q.h> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.l.d.q.b(b.l.d.a, b.l.d.p$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(b.l.d.a aVar, SparseArray<h> sparseArray, boolean z) {
        int size = aVar.f2844c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b(aVar, aVar.f2844c.get(i2), sparseArray, false, z);
        }
    }

    public static b.e.a<String, String> d(int i2, ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        b.e.a<String, String> aVar = new b.e.a<>();
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            b.l.d.a aVar2 = arrayList.get(i5);
            if (aVar2.C(i2)) {
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

    public static void e(b.l.d.a aVar, SparseArray<h> sparseArray, boolean z) {
        if (aVar.t.p0().d()) {
            for (int size = aVar.f2844c.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f2844c.get(size), sparseArray, true, z);
            }
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z, b.e.a<String, View> aVar, boolean z2) {
        if ((z ? fragment2.getEnterTransitionCallback() : fragment.getEnterTransitionCallback()) != null) {
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

    public static boolean g(s sVar, List<Object> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!sVar.e(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public static b.e.a<String, View> h(s sVar, b.e.a<String, String> aVar, Object obj, h hVar) {
        b.h.d.k enterTransitionCallback;
        ArrayList<String> arrayList;
        Fragment fragment = hVar.f2892a;
        View view = fragment.getView();
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        b.e.a<String, View> aVar2 = new b.e.a<>();
        sVar.j(aVar2, view);
        b.l.d.a aVar3 = hVar.f2894c;
        if (hVar.f2893b) {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.p;
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.q;
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

    public static b.e.a<String, View> i(s sVar, b.e.a<String, String> aVar, Object obj, h hVar) {
        b.h.d.k exitTransitionCallback;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        Fragment fragment = hVar.f2895d;
        b.e.a<String, View> aVar2 = new b.e.a<>();
        sVar.j(aVar2, fragment.requireView());
        b.l.d.a aVar3 = hVar.f2897f;
        if (hVar.f2896e) {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = aVar3.q;
        } else {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = aVar3.p;
        }
        if (arrayList != null) {
            aVar2.o(arrayList);
        }
        if (exitTransitionCallback != null) {
            throw null;
        }
        aVar.o(aVar2.keySet());
        return aVar2;
    }

    public static s j(Fragment fragment, Fragment fragment2) {
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
        s sVar = f2860b;
        if (sVar != null && g(sVar, arrayList)) {
            return sVar;
        }
        s sVar2 = f2861c;
        if (sVar2 != null && g(sVar2, arrayList)) {
            return sVar2;
        }
        if (sVar == null && sVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList<View> k(s sVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            sVar.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        sVar.b(obj, arrayList2);
        return arrayList2;
    }

    public static Object l(s sVar, ViewGroup viewGroup, View view, b.e.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        b.e.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.f2892a;
        Fragment fragment2 = hVar.f2895d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f2893b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            objT = null;
        } else {
            objT = t(sVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        b.e.a<String, View> aVarI = i(sVar, aVar2, objT, hVar);
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
            sVar.z(obj3, view, arrayList);
            z(sVar, obj3, obj2, aVarI, hVar.f2896e, hVar.f2897f);
            if (obj != null) {
                sVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        b.h.m.q.a(viewGroup, new f(sVar, aVar, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(s sVar, ViewGroup viewGroup, View view, b.e.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.f2892a;
        Fragment fragment2 = hVar.f2895d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f2893b;
        Object objT = aVar.isEmpty() ? null : t(sVar, fragment, fragment2, z);
        b.e.a<String, View> aVarI = i(sVar, aVar, objT, hVar);
        b.e.a<String, View> aVarH = h(sVar, aVar, objT, hVar);
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
            sVar.z(obj3, view, arrayList);
            z(sVar, obj3, obj2, aVarI, hVar.f2896e, hVar.f2897f);
            Rect rect2 = new Rect();
            View viewS = s(aVarH, hVar, obj, z);
            if (viewS != null) {
                sVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        b.h.m.q.a(viewGroup, new e(fragment, fragment2, z, aVarH, view2, sVar, rect));
        return obj3;
    }

    public static void n(ViewGroup viewGroup, h hVar, View view, b.e.a<String, String> aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.f2892a;
        Fragment fragment2 = hVar.f2895d;
        s sVarJ = j(fragment2, fragment);
        if (sVarJ == null) {
            return;
        }
        boolean z = hVar.f2893b;
        boolean z2 = hVar.f2896e;
        Object objQ = q(sVarJ, fragment, z);
        Object objR = r(sVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(sVarJ, viewGroup, view, aVar, hVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(sVarJ, obj, fragment2, arrayList, view);
        if (arrayListK == null || arrayListK.isEmpty()) {
            obj = null;
        }
        Object obj2 = obj;
        sVarJ.a(objQ, view);
        Object objU = u(sVarJ, objQ, obj2, objL, fragment, hVar.f2893b);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList.size() > 0)) {
            b.h.i.a aVar2 = new b.h.i.a();
            gVar.b(fragment2, aVar2);
            sVarJ.w(fragment2, objU, aVar2, new c(gVar, fragment2, aVar2));
        }
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            sVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(sVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            sVarJ.x(viewGroup, arrayList2, aVar);
            sVarJ.c(viewGroup, objU);
            sVarJ.s(viewGroup, arrayList2, aVar);
        }
    }

    public static void o(ViewGroup viewGroup, h hVar, View view, b.e.a<String, String> aVar, g gVar) {
        Object obj;
        Fragment fragment = hVar.f2892a;
        Fragment fragment2 = hVar.f2895d;
        s sVarJ = j(fragment2, fragment);
        if (sVarJ == null) {
            return;
        }
        boolean z = hVar.f2893b;
        boolean z2 = hVar.f2896e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(sVarJ, fragment, z);
        Object objR = r(sVarJ, fragment2, z2);
        Object objM = m(sVarJ, viewGroup, view, aVar, hVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(sVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(sVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(sVarJ, objQ, obj, objM, fragment, z);
        if (fragment2 != null && arrayListK != null && (arrayListK.size() > 0 || arrayList2.size() > 0)) {
            b.h.i.a aVar2 = new b.h.i.a();
            gVar.b(fragment2, aVar2);
            sVarJ.w(fragment2, objU, aVar2, new a(gVar, fragment2, aVar2));
        }
        if (objU != null) {
            v(sVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = sVarJ.o(arrayList);
            sVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            sVarJ.c(viewGroup, objU);
            sVarJ.y(viewGroup, arrayList2, arrayList, arrayListO, aVar);
            A(arrayListK2, 0);
            sVarJ.A(objM, arrayList2, arrayList);
        }
    }

    public static h p(h hVar, SparseArray<h> sparseArray, int i2) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i2, hVar2);
        return hVar2;
    }

    public static Object q(s sVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return sVar.g(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    public static Object r(s sVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return sVar.g(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    public static View s(b.e.a<String, View> aVar, h hVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        b.l.d.a aVar2 = hVar.f2894c;
        if (obj == null || aVar == null || (arrayList = aVar2.p) == null || arrayList.isEmpty()) {
            return null;
        }
        return aVar.get(z ? aVar2.p.get(0) : aVar2.q.get(0));
    }

    public static Object t(s sVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return sVar.B(sVar.g(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    public static Object u(s sVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? sVar.n(obj2, obj, obj3) : sVar.m(obj2, obj, obj3);
    }

    public static void v(s sVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            sVar.r(obj, fragment.getView(), arrayList);
            b.h.m.q.a(fragment.mContainer, new b(arrayList));
        }
    }

    public static s w() {
        try {
            return (s) Class.forName("b.x.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void x(b.e.a<String, String> aVar, b.e.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.m(size))) {
                aVar.k(size);
            }
        }
    }

    public static void y(s sVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        b.h.m.q.a(viewGroup, new d(obj, sVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static void z(s sVar, Object obj, Object obj2, b.e.a<String, View> aVar, boolean z, b.l.d.a aVar2) {
        ArrayList<String> arrayList = aVar2.p;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = aVar.get(z ? aVar2.q.get(0) : aVar2.p.get(0));
        sVar.v(obj, view);
        if (obj2 != null) {
            sVar.v(obj2, view);
        }
    }
}
