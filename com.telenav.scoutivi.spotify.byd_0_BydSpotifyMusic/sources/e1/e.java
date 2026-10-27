package e1;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import d0.b;
import e1.l;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class e extends androidx.fragment.app.u {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class a extends l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f6078a;

        a(Rect rect) {
            this.f6078a = rect;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class b implements l.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f6080a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f6081b;

        b(View view, ArrayList arrayList) {
            this.f6080a = view;
            this.f6081b = arrayList;
        }

        @Override // e1.l.f
        public void a(l lVar) {
            lVar.P(this);
            lVar.a(this);
        }

        @Override // e1.l.f
        public void b(l lVar) {
        }

        @Override // e1.l.f
        public void c(l lVar) {
        }

        @Override // e1.l.f
        public void d(l lVar) {
        }

        @Override // e1.l.f
        public void e(l lVar) {
            lVar.P(this);
            this.f6080a.setVisibility(8);
            int size = this.f6081b.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((View) this.f6081b.get(i7)).setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class c extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f6083a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f6084b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f6085c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f6086d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object f6087e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f6088f;

        c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f6083a = obj;
            this.f6084b = arrayList;
            this.f6085c = obj2;
            this.f6086d = arrayList2;
            this.f6087e = obj3;
            this.f6088f = arrayList3;
        }

        @Override // e1.m, e1.l.f
        public void a(l lVar) {
            Object obj = this.f6083a;
            if (obj != null) {
                e.this.q(obj, this.f6084b, null);
            }
            Object obj2 = this.f6085c;
            if (obj2 != null) {
                e.this.q(obj2, this.f6086d, null);
            }
            Object obj3 = this.f6087e;
            if (obj3 != null) {
                e.this.q(obj3, this.f6088f, null);
            }
        }

        @Override // e1.l.f
        public void e(l lVar) {
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class d implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ l f6090a;

        d(l lVar) {
            this.f6090a = lVar;
        }

        @Override // d0.b.a
        public void a() {
            this.f6090a.cancel();
        }
    }

    /* JADX INFO: renamed from: e1.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class C0069e implements l.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f6092a;

        C0069e(Runnable runnable) {
            this.f6092a = runnable;
        }

        @Override // e1.l.f
        public void a(l lVar) {
        }

        @Override // e1.l.f
        public void b(l lVar) {
        }

        @Override // e1.l.f
        public void c(l lVar) {
        }

        @Override // e1.l.f
        public void d(l lVar) {
        }

        @Override // e1.l.f
        public void e(l lVar) {
            this.f6092a.run();
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class f extends l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f6094a;

        f(Rect rect) {
            this.f6094a = rect;
        }
    }

    private static boolean C(l lVar) {
        return (androidx.fragment.app.u.l(lVar.z()) && androidx.fragment.app.u.l(lVar.A()) && androidx.fragment.app.u.l(lVar.B())) ? false : true;
    }

    @Override // androidx.fragment.app.u
    public void A(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        p pVar = (p) obj;
        if (pVar != null) {
            pVar.C().clear();
            pVar.C().addAll(arrayList2);
            q(pVar, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.u
    public Object B(Object obj) {
        if (obj == null) {
            return null;
        }
        p pVar = new p();
        pVar.e0((l) obj);
        return pVar;
    }

    @Override // androidx.fragment.app.u
    public void a(Object obj, View view) {
        if (obj != null) {
            ((l) obj).b(view);
        }
    }

    @Override // androidx.fragment.app.u
    public void b(Object obj, ArrayList<View> arrayList) {
        l lVar = (l) obj;
        if (lVar == null) {
            return;
        }
        int i7 = 0;
        if (lVar instanceof p) {
            p pVar = (p) lVar;
            int iH0 = pVar.h0();
            while (i7 < iH0) {
                b(pVar.g0(i7), arrayList);
                i7++;
            }
            return;
        }
        if (C(lVar) || !androidx.fragment.app.u.l(lVar.C())) {
            return;
        }
        int size = arrayList.size();
        while (i7 < size) {
            lVar.b(arrayList.get(i7));
            i7++;
        }
    }

    @Override // androidx.fragment.app.u
    public void c(ViewGroup viewGroup, Object obj) {
        n.a(viewGroup, (l) obj);
    }

    @Override // androidx.fragment.app.u
    public boolean e(Object obj) {
        return obj instanceof l;
    }

    @Override // androidx.fragment.app.u
    public Object g(Object obj) {
        if (obj != null) {
            return ((l) obj).clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.u
    public Object m(Object obj, Object obj2, Object obj3) {
        l lVarM0 = (l) obj;
        l lVar = (l) obj2;
        l lVar2 = (l) obj3;
        if (lVarM0 != null && lVar != null) {
            lVarM0 = new p().e0(lVarM0).e0(lVar).m0(1);
        } else if (lVarM0 == null) {
            lVarM0 = lVar != null ? lVar : null;
        }
        if (lVar2 == null) {
            return lVarM0;
        }
        p pVar = new p();
        if (lVarM0 != null) {
            pVar.e0(lVarM0);
        }
        pVar.e0(lVar2);
        return pVar;
    }

    @Override // androidx.fragment.app.u
    public Object n(Object obj, Object obj2, Object obj3) {
        p pVar = new p();
        if (obj != null) {
            pVar.e0((l) obj);
        }
        if (obj2 != null) {
            pVar.e0((l) obj2);
        }
        if (obj3 != null) {
            pVar.e0((l) obj3);
        }
        return pVar;
    }

    @Override // androidx.fragment.app.u
    public void p(Object obj, View view) {
        if (obj != null) {
            ((l) obj).Q(view);
        }
    }

    @Override // androidx.fragment.app.u
    public void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        l lVar = (l) obj;
        int i7 = 0;
        if (lVar instanceof p) {
            p pVar = (p) lVar;
            int iH0 = pVar.h0();
            while (i7 < iH0) {
                q(pVar.g0(i7), arrayList, arrayList2);
                i7++;
            }
            return;
        }
        if (C(lVar)) {
            return;
        }
        List<View> listC = lVar.C();
        if (listC.size() == arrayList.size() && listC.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i7 < size) {
                lVar.b(arrayList2.get(i7));
                i7++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                lVar.Q(arrayList.get(size2));
            }
        }
    }

    @Override // androidx.fragment.app.u
    public void r(Object obj, View view, ArrayList<View> arrayList) {
        ((l) obj).a(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.u
    public void t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((l) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.u
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((l) obj).V(new f(rect));
        }
    }

    @Override // androidx.fragment.app.u
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((l) obj).V(new a(rect));
        }
    }

    @Override // androidx.fragment.app.u
    public void w(Fragment fragment, Object obj, d0.b bVar, Runnable runnable) {
        l lVar = (l) obj;
        bVar.c(new d(lVar));
        lVar.a(new C0069e(runnable));
    }

    @Override // androidx.fragment.app.u
    public void z(Object obj, View view, ArrayList<View> arrayList) {
        p pVar = (p) obj;
        List<View> listC = pVar.C();
        listC.clear();
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            androidx.fragment.app.u.d(listC, arrayList.get(i7));
        }
        listC.add(view);
        arrayList.add(view);
        b(pVar, arrayList);
    }
}
