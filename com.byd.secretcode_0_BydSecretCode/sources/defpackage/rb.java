package defpackage;

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
public class rb {
    public static final int[] a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    public static final tb b;
    public static final tb c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class a implements Runnable {
        public final /* synthetic */ ArrayList b;

        public a(ArrayList arrayList) {
            this.b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            rb.A(this.b, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class b implements Runnable {
        public final /* synthetic */ Object b;
        public final /* synthetic */ tb c;
        public final /* synthetic */ View d;
        public final /* synthetic */ Fragment e;
        public final /* synthetic */ ArrayList f;
        public final /* synthetic */ ArrayList g;
        public final /* synthetic */ ArrayList h;
        public final /* synthetic */ Object i;

        public b(Object obj, tb tbVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.b = obj;
            this.c = tbVar;
            this.d = view;
            this.e = fragment;
            this.f = arrayList;
            this.g = arrayList2;
            this.h = arrayList3;
            this.i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.b;
            if (obj != null) {
                this.c.p(obj, this.d);
                this.g.addAll(rb.k(this.c, this.b, this.e, this.f, this.d));
            }
            if (this.h != null) {
                if (this.i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.d);
                    this.c.q(this.i, this.h, arrayList);
                }
                this.h.clear();
                this.h.add(this.d);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class c implements Runnable {
        public final /* synthetic */ Fragment b;
        public final /* synthetic */ Fragment c;
        public final /* synthetic */ boolean d;
        public final /* synthetic */ a4 e;
        public final /* synthetic */ View f;
        public final /* synthetic */ tb g;
        public final /* synthetic */ Rect h;

        public c(Fragment fragment, Fragment fragment2, boolean z, a4 a4Var, View view, tb tbVar, Rect rect) {
            this.b = fragment;
            this.c = fragment2;
            this.d = z;
            this.e = a4Var;
            this.f = view;
            this.g = tbVar;
            this.h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            rb.f(this.b, this.c, this.d, this.e, false);
            View view = this.f;
            if (view != null) {
                this.g.k(view, this.h);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class d implements Runnable {
        public final /* synthetic */ tb b;
        public final /* synthetic */ a4 c;
        public final /* synthetic */ Object d;
        public final /* synthetic */ e e;
        public final /* synthetic */ ArrayList f;
        public final /* synthetic */ View g;
        public final /* synthetic */ Fragment h;
        public final /* synthetic */ Fragment i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ ArrayList k;
        public final /* synthetic */ Object l;
        public final /* synthetic */ Rect m;

        public d(tb tbVar, a4 a4Var, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.b = tbVar;
            this.c = a4Var;
            this.d = obj;
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
            a4<String, View> a4VarH = rb.h(this.b, this.c, this.d, this.e);
            if (a4VarH != null) {
                this.f.addAll(a4VarH.values());
                this.f.add(this.g);
            }
            rb.f(this.h, this.i, this.j, a4VarH, false);
            Object obj = this.d;
            if (obj != null) {
                this.b.z(obj, this.k, this.f);
                View viewS = rb.s(a4VarH, this.e, this.l, this.j);
                if (viewS != null) {
                    this.b.k(viewS, this.m);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class e {
        public Fragment a;
        public boolean b;
        public hb c;
        public Fragment d;
        public boolean e;
        public hb f;
    }

    static {
        b = Build.VERSION.SDK_INT >= 21 ? new sb() : null;
        c = w();
    }

    public static void A(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    public static void B(ob obVar, ArrayList<hb> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (obVar.t < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            hb hbVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                e(hbVar, sparseArray, z);
            } else {
                c(hbVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(obVar.u.e());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                a4<String, String> a4VarD = d(iKeyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    o(obVar, iKeyAt, eVar, view, a4VarD);
                } else {
                    n(obVar, iKeyAt, eVar, view, a4VarD);
                }
            }
        }
    }

    public static void a(ArrayList<View> arrayList, a4<String, View> a4Var, Collection<String> collection) {
        for (int size = a4Var.size() - 1; size >= 0; size--) {
            View viewM = a4Var.m(size);
            if (collection.contains(t9.H(viewM))) {
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
    public static void b(defpackage.hb r16, qb.a r17, android.util.SparseArray<rb.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rb.b(hb, qb$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(hb hbVar, SparseArray<e> sparseArray, boolean z) {
        int size = hbVar.a.size();
        for (int i = 0; i < size; i++) {
            b(hbVar, hbVar.a.get(i), sparseArray, false, z);
        }
    }

    public static a4<String, String> d(int i, ArrayList<hb> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        a4<String, String> a4Var = new a4<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            hb hbVar = arrayList.get(i4);
            if (hbVar.p(i)) {
                boolean zBooleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = hbVar.o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = hbVar.o;
                        arrayList4 = hbVar.p;
                    } else {
                        ArrayList<String> arrayList6 = hbVar.o;
                        arrayList3 = hbVar.p;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String strRemove = a4Var.remove(str2);
                        if (strRemove != null) {
                            a4Var.put(str, strRemove);
                        } else {
                            a4Var.put(str, str2);
                        }
                    }
                }
            }
        }
        return a4Var;
    }

    public static void e(hb hbVar, SparseArray<e> sparseArray, boolean z) {
        if (hbVar.s.v.c()) {
            for (int size = hbVar.a.size() - 1; size >= 0; size--) {
                b(hbVar, hbVar.a.get(size), sparseArray, true, z);
            }
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z, a4<String, View> a4Var, boolean z2) {
        if ((z ? fragment2.getEnterTransitionCallback() : fragment.getEnterTransitionCallback()) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = a4Var == null ? 0 : a4Var.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(a4Var.i(i));
                arrayList.add(a4Var.m(i));
            }
            if (!z2) {
                throw null;
            }
            throw null;
        }
    }

    public static boolean g(tb tbVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!tbVar.e(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static a4<String, View> h(tb tbVar, a4<String, String> a4Var, Object obj, e eVar) {
        c7 enterTransitionCallback;
        ArrayList<String> arrayList;
        Fragment fragment = eVar.a;
        View view = fragment.getView();
        if (a4Var.isEmpty() || obj == null || view == null) {
            a4Var.clear();
            return null;
        }
        a4<String, View> a4Var2 = new a4<>();
        tbVar.j(a4Var2, view);
        hb hbVar = eVar.c;
        if (eVar.b) {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = hbVar.o;
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = hbVar.p;
        }
        if (arrayList != null) {
            a4Var2.o(arrayList);
            a4Var2.o(a4Var.values());
        }
        if (enterTransitionCallback != null) {
            throw null;
        }
        x(a4Var, a4Var2);
        return a4Var2;
    }

    public static a4<String, View> i(tb tbVar, a4<String, String> a4Var, Object obj, e eVar) {
        c7 exitTransitionCallback;
        ArrayList<String> arrayList;
        if (a4Var.isEmpty() || obj == null) {
            a4Var.clear();
            return null;
        }
        Fragment fragment = eVar.d;
        a4<String, View> a4Var2 = new a4<>();
        tbVar.j(a4Var2, fragment.requireView());
        hb hbVar = eVar.f;
        if (eVar.e) {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = hbVar.p;
        } else {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = hbVar.o;
        }
        a4Var2.o(arrayList);
        if (exitTransitionCallback != null) {
            throw null;
        }
        a4Var.o(a4Var2.keySet());
        return a4Var2;
    }

    public static tb j(Fragment fragment, Fragment fragment2) {
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
        tb tbVar = b;
        if (tbVar != null && g(tbVar, arrayList)) {
            return tbVar;
        }
        tb tbVar2 = c;
        if (tbVar2 != null && g(tbVar2, arrayList)) {
            return tbVar2;
        }
        if (tbVar == null && tbVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList<View> k(tb tbVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            tbVar.f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        tbVar.b(obj, arrayList2);
        return arrayList2;
    }

    public static Object l(tb tbVar, ViewGroup viewGroup, View view, a4<String, String> a4Var, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objT;
        a4<String, String> a4Var2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.a;
        Fragment fragment2 = eVar.d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.b;
        if (a4Var.isEmpty()) {
            a4Var2 = a4Var;
            objT = null;
        } else {
            objT = t(tbVar, fragment, fragment2, z);
            a4Var2 = a4Var;
        }
        a4<String, View> a4VarI = i(tbVar, a4Var2, objT, eVar);
        if (a4Var.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(a4VarI.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, a4VarI, true);
        if (obj3 != null) {
            rect = new Rect();
            tbVar.y(obj3, view, arrayList);
            z(tbVar, obj3, obj2, a4VarI, eVar.e, eVar.f);
            if (obj != null) {
                tbVar.u(obj, rect);
            }
        } else {
            rect = null;
        }
        q9.a(viewGroup, new d(tbVar, a4Var, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(tb tbVar, ViewGroup viewGroup, View view, a4<String, String> a4Var, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.a;
        Fragment fragment2 = eVar.d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.b;
        Object objT = a4Var.isEmpty() ? null : t(tbVar, fragment, fragment2, z);
        a4<String, View> a4VarI = i(tbVar, a4Var, objT, eVar);
        a4<String, View> a4VarH = h(tbVar, a4Var, objT, eVar);
        if (a4Var.isEmpty()) {
            if (a4VarI != null) {
                a4VarI.clear();
            }
            if (a4VarH != null) {
                a4VarH.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, a4VarI, a4Var.keySet());
            a(arrayList2, a4VarH, a4Var.values());
            obj3 = objT;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, a4VarI, true);
        if (obj3 != null) {
            arrayList2.add(view);
            tbVar.y(obj3, view, arrayList);
            z(tbVar, obj3, obj2, a4VarI, eVar.e, eVar.f);
            Rect rect2 = new Rect();
            View viewS = s(a4VarH, eVar, obj, z);
            if (viewS != null) {
                tbVar.u(obj, rect2);
            }
            rect = rect2;
            view2 = viewS;
        } else {
            view2 = null;
            rect = null;
        }
        q9.a(viewGroup, new c(fragment, fragment2, z, a4VarH, view2, tbVar, rect));
        return obj3;
    }

    public static void n(ob obVar, int i, e eVar, View view, a4<String, String> a4Var) {
        Fragment fragment;
        Fragment fragment2;
        tb tbVarJ;
        Object obj;
        ViewGroup viewGroup = obVar.v.c() ? (ViewGroup) obVar.v.b(i) : null;
        if (viewGroup == null || (tbVarJ = j((fragment2 = eVar.d), (fragment = eVar.a))) == null) {
            return;
        }
        boolean z = eVar.b;
        boolean z2 = eVar.e;
        Object objQ = q(tbVarJ, fragment, z);
        Object objR = r(tbVarJ, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objL = l(tbVarJ, viewGroup, view, a4Var, eVar, arrayList, arrayList2, objQ, objR);
        if (objQ == null && objL == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(tbVarJ, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListK == null || arrayListK.isEmpty()) ? null : obj;
        tbVarJ.a(objQ, view);
        Object objU = u(tbVarJ, objQ, obj2, objL, fragment, eVar.b);
        if (objU != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            tbVarJ.t(objU, objQ, arrayList3, obj2, arrayListK, objL, arrayList2);
            y(tbVarJ, viewGroup, fragment, view, arrayList2, objQ, arrayList3, obj2, arrayListK);
            tbVarJ.w(viewGroup, arrayList2, a4Var);
            tbVarJ.c(viewGroup, objU);
            tbVarJ.s(viewGroup, arrayList2, a4Var);
        }
    }

    public static void o(ob obVar, int i, e eVar, View view, a4<String, String> a4Var) {
        Fragment fragment;
        Fragment fragment2;
        tb tbVarJ;
        Object obj;
        ViewGroup viewGroup = obVar.v.c() ? (ViewGroup) obVar.v.b(i) : null;
        if (viewGroup == null || (tbVarJ = j((fragment2 = eVar.d), (fragment = eVar.a))) == null) {
            return;
        }
        boolean z = eVar.b;
        boolean z2 = eVar.e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objQ = q(tbVarJ, fragment, z);
        Object objR = r(tbVarJ, fragment2, z2);
        Object objM = m(tbVarJ, viewGroup, view, a4Var, eVar, arrayList2, arrayList, objQ, objR);
        if (objQ == null && objM == null) {
            obj = objR;
            if (obj == null) {
                return;
            }
        } else {
            obj = objR;
        }
        ArrayList<View> arrayListK = k(tbVarJ, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListK2 = k(tbVarJ, objQ, fragment, arrayList, view);
        A(arrayListK2, 4);
        Object objU = u(tbVarJ, objQ, obj, objM, fragment, z);
        if (objU != null) {
            v(tbVarJ, obj, fragment2, arrayListK);
            ArrayList<String> arrayListO = tbVarJ.o(arrayList);
            tbVarJ.t(objU, objQ, arrayListK2, obj, arrayListK, objM, arrayList);
            tbVarJ.c(viewGroup, objU);
            tbVarJ.x(viewGroup, arrayList2, arrayList, arrayListO, a4Var);
            A(arrayListK2, 0);
            tbVarJ.z(objM, arrayList2, arrayList);
        }
    }

    public static e p(e eVar, SparseArray<e> sparseArray, int i) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i, eVar2);
        return eVar2;
    }

    public static Object q(tb tbVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return tbVar.g(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    public static Object r(tb tbVar, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return tbVar.g(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    public static View s(a4<String, View> a4Var, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        hb hbVar = eVar.c;
        if (obj == null || a4Var == null || (arrayList = hbVar.o) == null || arrayList.isEmpty()) {
            return null;
        }
        return a4Var.get(z ? hbVar.o.get(0) : hbVar.p.get(0));
    }

    public static Object t(tb tbVar, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return tbVar.A(tbVar.g(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    public static Object u(tb tbVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? tbVar.n(obj2, obj, obj3) : tbVar.m(obj2, obj, obj3);
    }

    public static void v(tb tbVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            tbVar.r(obj, fragment.getView(), arrayList);
            q9.a(fragment.mContainer, new a(arrayList));
        }
    }

    public static tb w() {
        try {
            return (tb) Class.forName("rd").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void x(a4<String, String> a4Var, a4<String, View> a4Var2) {
        for (int size = a4Var.size() - 1; size >= 0; size--) {
            if (!a4Var2.containsKey(a4Var.m(size))) {
                a4Var.k(size);
            }
        }
    }

    public static void y(tb tbVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        q9.a(viewGroup, new b(obj, tbVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static void z(tb tbVar, Object obj, Object obj2, a4<String, View> a4Var, boolean z, hb hbVar) {
        ArrayList<String> arrayList = hbVar.o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = a4Var.get(z ? hbVar.p.get(0) : hbVar.o.get(0));
        tbVar.v(obj, view);
        if (obj2 != null) {
            tbVar.v(obj2, view);
        }
    }
}
