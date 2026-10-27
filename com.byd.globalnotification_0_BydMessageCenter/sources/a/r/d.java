package a.r;

import a.l.d.l0;
import a.r.i;
import a.r.m;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class d extends l0 {

    public class a extends i.c {
        public a(d dVar, Rect rect) {
        }
    }

    public class b implements i.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1317a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1318b;

        public b(d dVar, View view, ArrayList arrayList) {
            this.f1317a = view;
            this.f1318b = arrayList;
        }

        @Override // a.r.i.d
        public void a(i iVar) {
        }

        @Override // a.r.i.d
        public void b(i iVar) {
        }

        @Override // a.r.i.d
        public void c(i iVar) {
            iVar.w(this);
            this.f1317a.setVisibility(8);
            int size = this.f1318b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.f1318b.get(i)).setVisibility(0);
            }
        }

        @Override // a.r.i.d
        public void d(i iVar) {
        }

        @Override // a.r.i.d
        public void e(i iVar) {
        }
    }

    public class c extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f1319a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1320b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f1321c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1322d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f1323e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1324f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f1319a = obj;
            this.f1320b = arrayList;
            this.f1321c = obj2;
            this.f1322d = arrayList2;
            this.f1323e = obj3;
            this.f1324f = arrayList3;
        }

        @Override // a.r.l, a.r.i.d
        public void a(i iVar) {
            Object obj = this.f1319a;
            if (obj != null) {
                d.this.o(obj, this.f1320b, null);
            }
            Object obj2 = this.f1321c;
            if (obj2 != null) {
                d.this.o(obj2, this.f1322d, null);
            }
            Object obj3 = this.f1323e;
            if (obj3 != null) {
                d.this.o(obj3, this.f1324f, null);
            }
        }

        @Override // a.r.i.d
        public void c(i iVar) {
            iVar.w(this);
        }
    }

    /* JADX INFO: renamed from: a.r.d$d, reason: collision with other inner class name */
    public class C0028d extends i.c {
        public C0028d(d dVar, Rect rect) {
        }
    }

    public static boolean x(i iVar) {
        return (l0.k(iVar.f1329e) && l0.k(iVar.g) && l0.k(iVar.h)) ? false : true;
    }

    @Override // a.l.d.l0
    public void a(Object obj, View view) {
        if (obj != null) {
            ((i) obj).b(view);
        }
    }

    @Override // a.l.d.l0
    public void b(Object obj, ArrayList<View> arrayList) {
        i iVar = (i) obj;
        if (iVar == null) {
            return;
        }
        int i = 0;
        if (iVar instanceof o) {
            o oVar = (o) iVar;
            int size = oVar.I.size();
            while (i < size) {
                b(oVar.J(i), arrayList);
                i++;
            }
            return;
        }
        if (x(iVar) || !l0.k(iVar.f1330f)) {
            return;
        }
        int size2 = arrayList.size();
        while (i < size2) {
            iVar.b(arrayList.get(i));
            i++;
        }
    }

    @Override // a.l.d.l0
    public void c(ViewGroup viewGroup, Object obj) {
        i iVar = (i) obj;
        if (m.f1341c.contains(viewGroup) || !a.h.k.q.C(viewGroup)) {
            return;
        }
        m.f1341c.add(viewGroup);
        i iVarClone = iVar.clone();
        ArrayList<i> orDefault = m.a().getOrDefault(viewGroup, null);
        if (orDefault != null && orDefault.size() > 0) {
            Iterator<i> it = orDefault.iterator();
            while (it.hasNext()) {
                it.next().v(viewGroup);
            }
        }
        if (iVarClone != null) {
            iVarClone.i(viewGroup, true);
        }
        if (((h) viewGroup.getTag(f.transition_current_scene)) != null) {
            throw null;
        }
        viewGroup.setTag(f.transition_current_scene, null);
        if (iVarClone != null) {
            m.a aVar = new m.a(iVarClone, viewGroup);
            viewGroup.addOnAttachStateChangeListener(aVar);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
        }
    }

    @Override // a.l.d.l0
    public boolean e(Object obj) {
        return obj instanceof i;
    }

    @Override // a.l.d.l0
    public Object g(Object obj) {
        if (obj != null) {
            return ((i) obj).clone();
        }
        return null;
    }

    @Override // a.l.d.l0
    public Object l(Object obj, Object obj2, Object obj3) {
        i iVar = (i) obj;
        i iVar2 = (i) obj2;
        i iVar3 = (i) obj3;
        if (iVar != null && iVar2 != null) {
            o oVar = new o();
            oVar.I(iVar);
            oVar.I(iVar2);
            oVar.K(1);
            iVar = oVar;
        } else if (iVar == null) {
            iVar = iVar2 != null ? iVar2 : null;
        }
        if (iVar3 == null) {
            return iVar;
        }
        o oVar2 = new o();
        if (iVar != null) {
            oVar2.I(iVar);
        }
        oVar2.I(iVar3);
        return oVar2;
    }

    @Override // a.l.d.l0
    public Object m(Object obj, Object obj2, Object obj3) {
        o oVar = new o();
        if (obj != null) {
            oVar.I((i) obj);
        }
        if (obj2 != null) {
            oVar.I((i) obj2);
        }
        if (obj3 != null) {
            oVar.I((i) obj3);
        }
        return oVar;
    }

    @Override // a.l.d.l0
    public void n(Object obj, View view) {
        if (obj != null) {
            ((i) obj).x(view);
        }
    }

    @Override // a.l.d.l0
    public void o(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        i iVar = (i) obj;
        int i = 0;
        if (iVar instanceof o) {
            o oVar = (o) iVar;
            int size = oVar.I.size();
            while (i < size) {
                o(oVar.J(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (x(iVar)) {
            return;
        }
        ArrayList<View> arrayList3 = iVar.f1330f;
        if (arrayList3.size() != arrayList.size() || !arrayList3.containsAll(arrayList)) {
            return;
        }
        int size2 = arrayList2 == null ? 0 : arrayList2.size();
        while (i < size2) {
            iVar.b(arrayList2.get(i));
            i++;
        }
        int size3 = arrayList.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                return;
            } else {
                iVar.x(arrayList.get(size3));
            }
        }
    }

    @Override // a.l.d.l0
    public void p(Object obj, View view, ArrayList<View> arrayList) {
        ((i) obj).a(new b(this, view, arrayList));
    }

    @Override // a.l.d.l0
    public void q(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((i) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // a.l.d.l0
    public void r(Object obj, Rect rect) {
        if (obj != null) {
            ((i) obj).B(new C0028d(this, rect));
        }
    }

    @Override // a.l.d.l0
    public void s(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            j(view, rect);
            ((i) obj).B(new a(this, rect));
        }
    }

    @Override // a.l.d.l0
    public void u(Object obj, View view, ArrayList<View> arrayList) {
        o oVar = (o) obj;
        ArrayList<View> arrayList2 = oVar.f1330f;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            l0.d(arrayList2, arrayList.get(i));
        }
        arrayList2.add(view);
        arrayList.add(view);
        b(oVar, arrayList);
    }

    @Override // a.l.d.l0
    public void v(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        o oVar = (o) obj;
        if (oVar != null) {
            oVar.f1330f.clear();
            oVar.f1330f.addAll(arrayList2);
            o(oVar, arrayList, arrayList2);
        }
    }

    @Override // a.l.d.l0
    public Object w(Object obj) {
        if (obj == null) {
            return null;
        }
        o oVar = new o();
        oVar.I((i) obj);
        return oVar;
    }
}
