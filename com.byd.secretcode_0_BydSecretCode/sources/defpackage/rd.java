package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import defpackage.yd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class rd extends tb {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class a extends yd.e {
        public final /* synthetic */ Rect a;

        public a(Rect rect) {
            this.a = rect;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class b implements yd.f {
        public final /* synthetic */ View a;
        public final /* synthetic */ ArrayList b;

        public b(View view, ArrayList arrayList) {
            this.a = view;
            this.b = arrayList;
        }

        @Override // yd.f
        public void a(yd ydVar) {
        }

        @Override // yd.f
        public void b(yd ydVar) {
        }

        @Override // yd.f
        public void c(yd ydVar) {
        }

        @Override // yd.f
        public void d(yd ydVar) {
        }

        @Override // yd.f
        public void e(yd ydVar) {
            ydVar.P(this);
            this.a.setVisibility(8);
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.b.get(i)).setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class c extends zd {
        public final /* synthetic */ Object a;
        public final /* synthetic */ ArrayList b;
        public final /* synthetic */ Object c;
        public final /* synthetic */ ArrayList d;
        public final /* synthetic */ Object e;
        public final /* synthetic */ ArrayList f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.a = obj;
            this.b = arrayList;
            this.c = obj2;
            this.d = arrayList2;
            this.e = obj3;
            this.f = arrayList3;
        }

        @Override // defpackage.zd, yd.f
        public void c(yd ydVar) {
            Object obj = this.a;
            if (obj != null) {
                rd.this.q(obj, this.b, null);
            }
            Object obj2 = this.c;
            if (obj2 != null) {
                rd.this.q(obj2, this.d, null);
            }
            Object obj3 = this.e;
            if (obj3 != null) {
                rd.this.q(obj3, this.f, null);
            }
        }

        @Override // yd.f
        public void e(yd ydVar) {
            ydVar.P(this);
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class d extends yd.e {
        public final /* synthetic */ Rect a;

        public d(Rect rect) {
            this.a = rect;
        }
    }

    public static boolean B(yd ydVar) {
        return (tb.l(ydVar.z()) && tb.l(ydVar.A()) && tb.l(ydVar.B())) ? false : true;
    }

    @Override // defpackage.tb
    public Object A(Object obj) {
        if (obj == null) {
            return null;
        }
        ce ceVar = new ce();
        ceVar.e0((yd) obj);
        return ceVar;
    }

    @Override // defpackage.tb
    public void a(Object obj, View view) {
        if (obj != null) {
            ((yd) obj).b(view);
        }
    }

    @Override // defpackage.tb
    public void b(Object obj, ArrayList<View> arrayList) {
        yd ydVar = (yd) obj;
        if (ydVar == null) {
            return;
        }
        int i = 0;
        if (ydVar instanceof ce) {
            ce ceVar = (ce) ydVar;
            int iH0 = ceVar.h0();
            while (i < iH0) {
                b(ceVar.g0(i), arrayList);
                i++;
            }
            return;
        }
        if (B(ydVar) || !tb.l(ydVar.C())) {
            return;
        }
        int size = arrayList.size();
        while (i < size) {
            ydVar.b(arrayList.get(i));
            i++;
        }
    }

    @Override // defpackage.tb
    public void c(ViewGroup viewGroup, Object obj) {
        ae.a(viewGroup, (yd) obj);
    }

    @Override // defpackage.tb
    public boolean e(Object obj) {
        return obj instanceof yd;
    }

    @Override // defpackage.tb
    public Object g(Object obj) {
        if (obj != null) {
            return ((yd) obj).clone();
        }
        return null;
    }

    @Override // defpackage.tb
    public Object m(Object obj, Object obj2, Object obj3) {
        yd ydVarM0 = (yd) obj;
        yd ydVar = (yd) obj2;
        yd ydVar2 = (yd) obj3;
        if (ydVarM0 != null && ydVar != null) {
            ydVarM0 = new ce().e0(ydVarM0).e0(ydVar).m0(1);
        } else if (ydVarM0 == null) {
            ydVarM0 = ydVar != null ? ydVar : null;
        }
        if (ydVar2 == null) {
            return ydVarM0;
        }
        ce ceVar = new ce();
        if (ydVarM0 != null) {
            ceVar.e0(ydVarM0);
        }
        ceVar.e0(ydVar2);
        return ceVar;
    }

    @Override // defpackage.tb
    public Object n(Object obj, Object obj2, Object obj3) {
        ce ceVar = new ce();
        if (obj != null) {
            ceVar.e0((yd) obj);
        }
        if (obj2 != null) {
            ceVar.e0((yd) obj2);
        }
        if (obj3 != null) {
            ceVar.e0((yd) obj3);
        }
        return ceVar;
    }

    @Override // defpackage.tb
    public void p(Object obj, View view) {
        if (obj != null) {
            ((yd) obj).Q(view);
        }
    }

    @Override // defpackage.tb
    public void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        yd ydVar = (yd) obj;
        int i = 0;
        if (ydVar instanceof ce) {
            ce ceVar = (ce) ydVar;
            int iH0 = ceVar.h0();
            while (i < iH0) {
                q(ceVar.g0(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (B(ydVar)) {
            return;
        }
        List<View> listC = ydVar.C();
        if (listC.size() == arrayList.size() && listC.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i < size) {
                ydVar.b(arrayList2.get(i));
                i++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                ydVar.Q(arrayList.get(size2));
            }
        }
    }

    @Override // defpackage.tb
    public void r(Object obj, View view, ArrayList<View> arrayList) {
        ((yd) obj).a(new b(view, arrayList));
    }

    @Override // defpackage.tb
    public void t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((yd) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // defpackage.tb
    public void u(Object obj, Rect rect) {
        if (obj != null) {
            ((yd) obj).V(new d(rect));
        }
    }

    @Override // defpackage.tb
    public void v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            k(view, rect);
            ((yd) obj).V(new a(rect));
        }
    }

    @Override // defpackage.tb
    public void y(Object obj, View view, ArrayList<View> arrayList) {
        ce ceVar = (ce) obj;
        List<View> listC = ceVar.C();
        listC.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            tb.d(listC, arrayList.get(i));
        }
        listC.add(view);
        arrayList.add(view);
        b(ceVar, arrayList);
    }

    @Override // defpackage.tb
    public void z(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        ce ceVar = (ce) obj;
        if (ceVar != null) {
            ceVar.C().clear();
            ceVar.C().addAll(arrayList2);
            q(ceVar, arrayList, arrayList2);
        }
    }
}
