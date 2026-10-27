package b.x;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import b.x.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class e extends b.l.d.s {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class a extends m.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f3237a;

        public a(Rect rect) {
            this.f3237a = rect;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class b implements m.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f3239a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3240b;

        public b(View view, ArrayList arrayList) {
            this.f3239a = view;
            this.f3240b = arrayList;
        }

        @Override // b.x.m.f
        public void a(m mVar) {
        }

        @Override // b.x.m.f
        public void b(m mVar) {
        }

        @Override // b.x.m.f
        public void c(m mVar) {
        }

        @Override // b.x.m.f
        public void d(m mVar) {
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            mVar.Q(this);
            this.f3239a.setVisibility(8);
            int size = this.f3240b.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((View) this.f3240b.get(i2)).setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class c extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f3242a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3243b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f3244c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3245d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f3246e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f3247f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f3242a = obj;
            this.f3243b = arrayList;
            this.f3244c = obj2;
            this.f3245d = arrayList2;
            this.f3246e = obj3;
            this.f3247f = arrayList3;
        }

        @Override // b.x.n, b.x.m.f
        public void c(m mVar) {
            Object obj = this.f3242a;
            if (obj != null) {
                e.this.q(obj, this.f3243b, null);
            }
            Object obj2 = this.f3244c;
            if (obj2 != null) {
                e.this.q(obj2, this.f3245d, null);
            }
            Object obj3 = this.f3246e;
            if (obj3 != null) {
                e.this.q(obj3, this.f3247f, null);
            }
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            mVar.Q(this);
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class d extends m.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f3249a;

        public d(Rect rect) {
            this.f3249a = rect;
        }
    }

    public static boolean C(m mVar) {
        return (b.l.d.s.l(mVar.A()) && b.l.d.s.l(mVar.B()) && b.l.d.s.l(mVar.C())) ? false : true;
    }

    @Override // b.l.d.s
    public void A(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        q qVar = (q) obj;
        if (qVar != null) {
            qVar.D().clear();
            qVar.D().addAll(arrayList2);
            q(qVar, arrayList, arrayList2);
        }
    }

    @Override // b.l.d.s
    public Object B(Object obj) {
        if (obj == null) {
            return null;
        }
        q qVar = new q();
        qVar.f0((m) obj);
        return qVar;
    }

    @Override // b.l.d.s
    public void a(Object obj, View view) {
        if (obj != null) {
            ((m) obj).b(view);
        }
    }

    @Override // b.l.d.s
    public void b(Object obj, ArrayList<View> arrayList) {
        m mVar = (m) obj;
        if (mVar == null) {
            return;
        }
        int i2 = 0;
        if (mVar instanceof q) {
            q qVar = (q) mVar;
            int iI0 = qVar.i0();
            while (i2 < iI0) {
                b(qVar.h0(i2), arrayList);
                i2++;
            }
            return;
        }
        if (C(mVar) || !b.l.d.s.l(mVar.D())) {
            return;
        }
        int size = arrayList.size();
        while (i2 < size) {
            mVar.b(arrayList.get(i2));
            i2++;
        }
    }

    @Override // b.l.d.s
    public void c(ViewGroup viewGroup, Object obj) {
        o.a(viewGroup, (m) obj);
    }

    @Override // b.l.d.s
    public boolean e(Object obj) {
        return obj instanceof m;
    }

    @Override // b.l.d.s
    public Object g(Object obj) {
        if (obj != null) {
            return ((m) obj).clone();
        }
        return null;
    }

    @Override // b.l.d.s
    public Object m(Object obj, Object obj2, Object obj3) {
        m mVarN0 = (m) obj;
        m mVar = (m) obj2;
        m mVar2 = (m) obj3;
        if (mVarN0 != null && mVar != null) {
            mVarN0 = new q().f0(mVarN0).f0(mVar).n0(1);
        } else if (mVarN0 == null) {
            mVarN0 = mVar != null ? mVar : null;
        }
        if (mVar2 == null) {
            return mVarN0;
        }
        q qVar = new q();
        if (mVarN0 != null) {
            qVar.f0(mVarN0);
        }
        qVar.f0(mVar2);
        return qVar;
    }

    @Override // b.l.d.s
    public Object n(Object obj, Object obj2, Object obj3) {
        q qVar = new q();
        if (obj != null) {
            qVar.f0((m) obj);
        }
        if (obj2 != null) {
            qVar.f0((m) obj2);
        }
        if (obj3 != null) {
            qVar.f0((m) obj3);
        }
        return qVar;
    }

    @Override // b.l.d.s
    public void p(Object obj, View view) {
        if (obj != null) {
            ((m) obj).R(view);
        }
    }

    @Override // b.l.d.s
    public void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        m mVar = (m) obj;
        int i2 = 0;
        if (mVar instanceof q) {
            q qVar = (q) mVar;
            int iI0 = qVar.i0();
            while (i2 < iI0) {
                q(qVar.h0(i2), arrayList, arrayList2);
                i2++;
            }
            return;
        }
        if (C(mVar)) {
            return;
        }
        List<View> listD = mVar.D();
        if (listD.size() == arrayList.size() && listD.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i2 < size) {
                mVar.b(arrayList2.get(i2));
                i2++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                mVar.R(arrayList.get(size2));
            }
        }
    }

    @Override // b.l.d.s
    public void r(Object obj, View view, ArrayList<View> arrayList) {
        ((m) obj).a(new b(view, arrayList));
    }

    @Override // b.l.d.s
    public void t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((m) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // b.l.d.s
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((m) obj).W(new d(rect));
        }
    }

    @Override // b.l.d.s
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((m) obj).W(new a(rect));
        }
    }

    @Override // b.l.d.s
    public void z(Object obj, View view, ArrayList<View> arrayList) {
        q qVar = (q) obj;
        List<View> listD = qVar.D();
        listD.clear();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            b.l.d.s.d(listD, arrayList.get(i2));
        }
        listD.add(view);
        arrayList.add(view);
        b(qVar, arrayList);
    }
}
