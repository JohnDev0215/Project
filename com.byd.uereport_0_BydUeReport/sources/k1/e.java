package k1;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import k1.l;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class e extends v0.m {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class a extends l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f5731a;

        public a(Rect rect) {
            this.f5731a = rect;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class b implements l.f {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f5733d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5734e;

        public b(View view, ArrayList arrayList) {
            this.f5733d = view;
            this.f5734e = arrayList;
        }

        @Override // k1.l.f
        public void a(l lVar) {
        }

        @Override // k1.l.f
        public void b(l lVar) {
        }

        @Override // k1.l.f
        public void c(l lVar) {
        }

        @Override // k1.l.f
        public void d(l lVar) {
        }

        @Override // k1.l.f
        public void e(l lVar) {
            lVar.P(this);
            this.f5733d.setVisibility(8);
            int size = this.f5734e.size();
            for (int i8 = 0; i8 < size; i8++) {
                ((View) this.f5734e.get(i8)).setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class c extends m {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f5736d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5737e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Object f5738f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5739g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Object f5740h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5741i;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f5736d = obj;
            this.f5737e = arrayList;
            this.f5738f = obj2;
            this.f5739g = arrayList2;
            this.f5740h = obj3;
            this.f5741i = arrayList3;
        }

        @Override // k1.m, k1.l.f
        public void a(l lVar) {
            Object obj = this.f5736d;
            if (obj != null) {
                e.this.q(obj, this.f5737e, null);
            }
            Object obj2 = this.f5738f;
            if (obj2 != null) {
                e.this.q(obj2, this.f5739g, null);
            }
            Object obj3 = this.f5740h;
            if (obj3 != null) {
                e.this.q(obj3, this.f5741i, null);
            }
        }

        @Override // k1.l.f
        public void e(l lVar) {
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class d extends l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f5743a;

        public d(Rect rect) {
            this.f5743a = rect;
        }
    }

    public static boolean B(l lVar) {
        return (v0.m.l(lVar.z()) && v0.m.l(lVar.A()) && v0.m.l(lVar.B())) ? false : true;
    }

    @Override // v0.m
    public Object A(Object obj) {
        if (obj == null) {
            return null;
        }
        p pVar = new p();
        pVar.e0((l) obj);
        return pVar;
    }

    @Override // v0.m
    public void a(Object obj, View view) {
        if (obj != null) {
            ((l) obj).b(view);
        }
    }

    @Override // v0.m
    public void b(Object obj, ArrayList<View> arrayList) {
        l lVar = (l) obj;
        if (lVar == null) {
            return;
        }
        int i8 = 0;
        if (lVar instanceof p) {
            p pVar = (p) lVar;
            int iH0 = pVar.h0();
            while (i8 < iH0) {
                b(pVar.g0(i8), arrayList);
                i8++;
            }
            return;
        }
        if (B(lVar) || !v0.m.l(lVar.C())) {
            return;
        }
        int size = arrayList.size();
        while (i8 < size) {
            lVar.b(arrayList.get(i8));
            i8++;
        }
    }

    @Override // v0.m
    public void c(ViewGroup viewGroup, Object obj) {
        n.a(viewGroup, (l) obj);
    }

    @Override // v0.m
    public boolean e(Object obj) {
        return obj instanceof l;
    }

    @Override // v0.m
    public Object g(Object obj) {
        if (obj != null) {
            return ((l) obj).clone();
        }
        return null;
    }

    @Override // v0.m
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

    @Override // v0.m
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

    @Override // v0.m
    public void p(Object obj, View view) {
        if (obj != null) {
            ((l) obj).Q(view);
        }
    }

    @Override // v0.m
    public void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        l lVar = (l) obj;
        int i8 = 0;
        if (lVar instanceof p) {
            p pVar = (p) lVar;
            int iH0 = pVar.h0();
            while (i8 < iH0) {
                q(pVar.g0(i8), arrayList, arrayList2);
                i8++;
            }
            return;
        }
        if (B(lVar)) {
            return;
        }
        List<View> listC = lVar.C();
        if (listC.size() == arrayList.size() && listC.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i8 < size) {
                lVar.b(arrayList2.get(i8));
                i8++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                lVar.Q(arrayList.get(size2));
            }
        }
    }

    @Override // v0.m
    public void r(Object obj, View view, ArrayList<View> arrayList) {
        ((l) obj).a(new b(view, arrayList));
    }

    @Override // v0.m
    public void t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((l) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // v0.m
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((l) obj).V(new d(rect));
        }
    }

    @Override // v0.m
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((l) obj).V(new a(rect));
        }
    }

    @Override // v0.m
    public void y(Object obj, View view, ArrayList<View> arrayList) {
        p pVar = (p) obj;
        List<View> listC = pVar.C();
        listC.clear();
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            v0.m.d(listC, arrayList.get(i8));
        }
        listC.add(view);
        arrayList.add(view);
        b(pVar, arrayList);
    }

    @Override // v0.m
    public void z(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        p pVar = (p) obj;
        if (pVar != null) {
            pVar.C().clear();
            pVar.C().addAll(arrayList2);
            q(pVar, arrayList, arrayList2);
        }
    }
}
