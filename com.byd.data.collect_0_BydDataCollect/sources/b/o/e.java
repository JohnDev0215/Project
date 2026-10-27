package b.o;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import b.g.g.a;
import b.o.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class e extends androidx.fragment.app.r {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class a extends m.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f2837a;

        a(Rect rect) {
            this.f2837a = rect;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class b implements m.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f2839a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f2840b;

        b(View view, ArrayList arrayList) {
            this.f2839a = view;
            this.f2840b = arrayList;
        }

        @Override // b.o.m.f
        public void a(m mVar) {
        }

        @Override // b.o.m.f
        public void b(m mVar) {
        }

        @Override // b.o.m.f
        public void c(m mVar) {
            mVar.T(this);
            mVar.b(this);
        }

        @Override // b.o.m.f
        public void d(m mVar) {
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            mVar.T(this);
            this.f2839a.setVisibility(8);
            int size = this.f2840b.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((View) this.f2840b.get(i2)).setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class c extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f2842a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f2843b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f2844c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f2845d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object f2846e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f2847f;

        c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f2842a = obj;
            this.f2843b = arrayList;
            this.f2844c = obj2;
            this.f2845d = arrayList2;
            this.f2846e = obj3;
            this.f2847f = arrayList3;
        }

        @Override // b.o.n, b.o.m.f
        public void c(m mVar) {
            Object obj = this.f2842a;
            if (obj != null) {
                e.this.q(obj, this.f2843b, null);
            }
            Object obj2 = this.f2844c;
            if (obj2 != null) {
                e.this.q(obj2, this.f2845d, null);
            }
            Object obj3 = this.f2846e;
            if (obj3 != null) {
                e.this.q(obj3, this.f2847f, null);
            }
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            mVar.T(this);
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class d implements a.InterfaceC0039a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ m f2849a;

        d(m mVar) {
            this.f2849a = mVar;
        }

        @Override // b.g.g.a.InterfaceC0039a
        public void a() {
            this.f2849a.cancel();
        }
    }

    /* JADX INFO: renamed from: b.o.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class C0056e implements m.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f2851a;

        C0056e(Runnable runnable) {
            this.f2851a = runnable;
        }

        @Override // b.o.m.f
        public void a(m mVar) {
        }

        @Override // b.o.m.f
        public void b(m mVar) {
        }

        @Override // b.o.m.f
        public void c(m mVar) {
        }

        @Override // b.o.m.f
        public void d(m mVar) {
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            this.f2851a.run();
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    class f extends m.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f2853a;

        f(Rect rect) {
            this.f2853a = rect;
        }
    }

    private static boolean C(m mVar) {
        return (androidx.fragment.app.r.l(mVar.D()) && androidx.fragment.app.r.l(mVar.E()) && androidx.fragment.app.r.l(mVar.F())) ? false : true;
    }

    @Override // androidx.fragment.app.r
    public void A(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        q qVar = (q) obj;
        if (qVar != null) {
            qVar.G().clear();
            qVar.G().addAll(arrayList2);
            q(qVar, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.r
    public Object B(Object obj) {
        if (obj == null) {
            return null;
        }
        q qVar = new q();
        qVar.i0((m) obj);
        return qVar;
    }

    @Override // androidx.fragment.app.r
    public void a(Object obj, View view) {
        if (obj != null) {
            ((m) obj).c(view);
        }
    }

    @Override // androidx.fragment.app.r
    public void b(Object obj, ArrayList<View> arrayList) {
        m mVar = (m) obj;
        if (mVar == null) {
            return;
        }
        int i2 = 0;
        if (mVar instanceof q) {
            q qVar = (q) mVar;
            int iL0 = qVar.l0();
            while (i2 < iL0) {
                b(qVar.k0(i2), arrayList);
                i2++;
            }
            return;
        }
        if (C(mVar) || !androidx.fragment.app.r.l(mVar.G())) {
            return;
        }
        int size = arrayList.size();
        while (i2 < size) {
            mVar.c(arrayList.get(i2));
            i2++;
        }
    }

    @Override // androidx.fragment.app.r
    public void c(ViewGroup viewGroup, Object obj) {
        o.a(viewGroup, (m) obj);
    }

    @Override // androidx.fragment.app.r
    public boolean e(Object obj) {
        return obj instanceof m;
    }

    @Override // androidx.fragment.app.r
    public Object g(Object obj) {
        if (obj != null) {
            return ((m) obj).clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.r
    public Object m(Object obj, Object obj2, Object obj3) {
        m mVarQ0 = (m) obj;
        m mVar = (m) obj2;
        m mVar2 = (m) obj3;
        if (mVarQ0 != null && mVar != null) {
            mVarQ0 = new q().i0(mVarQ0).i0(mVar).q0(1);
        } else if (mVarQ0 == null) {
            mVarQ0 = mVar != null ? mVar : null;
        }
        if (mVar2 == null) {
            return mVarQ0;
        }
        q qVar = new q();
        if (mVarQ0 != null) {
            qVar.i0(mVarQ0);
        }
        qVar.i0(mVar2);
        return qVar;
    }

    @Override // androidx.fragment.app.r
    public Object n(Object obj, Object obj2, Object obj3) {
        q qVar = new q();
        if (obj != null) {
            qVar.i0((m) obj);
        }
        if (obj2 != null) {
            qVar.i0((m) obj2);
        }
        if (obj3 != null) {
            qVar.i0((m) obj3);
        }
        return qVar;
    }

    @Override // androidx.fragment.app.r
    public void p(Object obj, View view) {
        if (obj != null) {
            ((m) obj).U(view);
        }
    }

    @Override // androidx.fragment.app.r
    public void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        m mVar = (m) obj;
        int i2 = 0;
        if (mVar instanceof q) {
            q qVar = (q) mVar;
            int iL0 = qVar.l0();
            while (i2 < iL0) {
                q(qVar.k0(i2), arrayList, arrayList2);
                i2++;
            }
            return;
        }
        if (C(mVar)) {
            return;
        }
        List<View> listG = mVar.G();
        if (listG.size() == arrayList.size() && listG.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i2 < size) {
                mVar.c(arrayList2.get(i2));
                i2++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                mVar.U(arrayList.get(size2));
            }
        }
    }

    @Override // androidx.fragment.app.r
    public void r(Object obj, View view, ArrayList<View> arrayList) {
        ((m) obj).b(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.r
    public void t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((m) obj).b(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.r
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((m) obj).Z(new f(rect));
        }
    }

    @Override // androidx.fragment.app.r
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((m) obj).Z(new a(rect));
        }
    }

    @Override // androidx.fragment.app.r
    public void w(Fragment fragment, Object obj, b.g.g.a aVar, Runnable runnable) {
        m mVar = (m) obj;
        aVar.c(new d(mVar));
        mVar.b(new C0056e(runnable));
    }

    @Override // androidx.fragment.app.r
    public void z(Object obj, View view, ArrayList<View> arrayList) {
        q qVar = (q) obj;
        List<View> listG = qVar.G();
        listG.clear();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            androidx.fragment.app.r.d(listG, arrayList.get(i2));
        }
        listG.add(view);
        arrayList.add(view);
        b(qVar, arrayList);
    }
}
