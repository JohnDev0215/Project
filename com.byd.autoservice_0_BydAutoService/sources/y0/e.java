package y0;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import y0.l;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class e extends r0.m {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class a extends l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f5887a;

        public a(Rect rect) {
            this.f5887a = rect;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class b implements l.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f5889a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5890b;

        public b(View view, ArrayList arrayList) {
            this.f5889a = view;
            this.f5890b = arrayList;
        }

        @Override // y0.l.f
        public void a(l lVar) {
        }

        @Override // y0.l.f
        public void b(l lVar) {
        }

        @Override // y0.l.f
        public void c(l lVar) {
        }

        @Override // y0.l.f
        public void d(l lVar) {
        }

        @Override // y0.l.f
        public void e(l lVar) {
            lVar.P(this);
            this.f5889a.setVisibility(8);
            int size = this.f5890b.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((View) this.f5890b.get(i3)).setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class c extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f5892a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5893b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f5894c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5895d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f5896e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f5897f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f5892a = obj;
            this.f5893b = arrayList;
            this.f5894c = obj2;
            this.f5895d = arrayList2;
            this.f5896e = obj3;
            this.f5897f = arrayList3;
        }

        @Override // y0.m, y0.l.f
        public void c(l lVar) {
            Object obj = this.f5892a;
            if (obj != null) {
                e.this.q(obj, this.f5893b, null);
            }
            Object obj2 = this.f5894c;
            if (obj2 != null) {
                e.this.q(obj2, this.f5895d, null);
            }
            Object obj3 = this.f5896e;
            if (obj3 != null) {
                e.this.q(obj3, this.f5897f, null);
            }
        }

        @Override // y0.l.f
        public void e(l lVar) {
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class d extends l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f5899a;

        public d(Rect rect) {
            this.f5899a = rect;
        }
    }

    public static boolean B(l lVar) {
        return (r0.m.l(lVar.z()) && r0.m.l(lVar.A()) && r0.m.l(lVar.B())) ? false : true;
    }

    @Override // r0.m
    public Object A(Object obj) {
        if (obj == null) {
            return null;
        }
        p pVar = new p();
        pVar.e0((l) obj);
        return pVar;
    }

    @Override // r0.m
    public void a(Object obj, View view) {
        if (obj != null) {
            ((l) obj).b(view);
        }
    }

    @Override // r0.m
    public void b(Object obj, ArrayList<View> arrayList) {
        l lVar = (l) obj;
        if (lVar == null) {
            return;
        }
        int i3 = 0;
        if (lVar instanceof p) {
            p pVar = (p) lVar;
            int iH0 = pVar.h0();
            while (i3 < iH0) {
                b(pVar.g0(i3), arrayList);
                i3++;
            }
            return;
        }
        if (B(lVar) || !r0.m.l(lVar.C())) {
            return;
        }
        int size = arrayList.size();
        while (i3 < size) {
            lVar.b(arrayList.get(i3));
            i3++;
        }
    }

    @Override // r0.m
    public void c(ViewGroup viewGroup, Object obj) {
        n.a(viewGroup, (l) obj);
    }

    @Override // r0.m
    public boolean e(Object obj) {
        return obj instanceof l;
    }

    @Override // r0.m
    public Object g(Object obj) {
        if (obj != null) {
            return ((l) obj).clone();
        }
        return null;
    }

    @Override // r0.m
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

    @Override // r0.m
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

    @Override // r0.m
    public void p(Object obj, View view) {
        if (obj != null) {
            ((l) obj).Q(view);
        }
    }

    @Override // r0.m
    public void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        l lVar = (l) obj;
        int i3 = 0;
        if (lVar instanceof p) {
            p pVar = (p) lVar;
            int iH0 = pVar.h0();
            while (i3 < iH0) {
                q(pVar.g0(i3), arrayList, arrayList2);
                i3++;
            }
            return;
        }
        if (B(lVar)) {
            return;
        }
        List<View> listC = lVar.C();
        if (listC.size() == arrayList.size() && listC.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i3 < size) {
                lVar.b(arrayList2.get(i3));
                i3++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                lVar.Q(arrayList.get(size2));
            }
        }
    }

    @Override // r0.m
    public void r(Object obj, View view, ArrayList<View> arrayList) {
        ((l) obj).a(new b(view, arrayList));
    }

    @Override // r0.m
    public void t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((l) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // r0.m
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((l) obj).V(new d(rect));
        }
    }

    @Override // r0.m
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((l) obj).V(new a(rect));
        }
    }

    @Override // r0.m
    public void y(Object obj, View view, ArrayList<View> arrayList) {
        p pVar = (p) obj;
        List<View> listC = pVar.C();
        listC.clear();
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            r0.m.d(listC, arrayList.get(i3));
        }
        listC.add(view);
        arrayList.add(view);
        b(pVar, arrayList);
    }

    @Override // r0.m
    public void z(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        p pVar = (p) obj;
        if (pVar != null) {
            pVar.C().clear();
            pVar.C().addAll(arrayList2);
            q(pVar, arrayList, arrayList2);
        }
    }
}
