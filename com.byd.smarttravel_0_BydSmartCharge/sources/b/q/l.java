package b.q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Transition.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1489b = {2, 1, 3, 4};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f1490c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ThreadLocal<b.e.a<Animator, d>> f1491d = new ThreadLocal<>();
    public o H;
    public e I;
    public b.e.a<String, String> J;
    public ArrayList<r> x;
    public ArrayList<r> y;
    public String e = getClass().getName();
    public long f = -1;
    public long g = -1;
    public TimeInterpolator h = null;
    public ArrayList<Integer> i = new ArrayList<>();
    public ArrayList<View> j = new ArrayList<>();
    public ArrayList<String> k = null;
    public ArrayList<Class<?>> l = null;
    public ArrayList<Integer> m = null;
    public ArrayList<View> n = null;
    public ArrayList<Class<?>> o = null;
    public ArrayList<String> p = null;
    public ArrayList<Integer> q = null;
    public ArrayList<View> r = null;
    public ArrayList<Class<?>> s = null;
    public s t = new s();
    public s u = new s();
    public p v = null;
    public int[] w = f1489b;
    public ViewGroup z = null;
    public boolean A = false;
    public ArrayList<Animator> B = new ArrayList<>();
    public int C = 0;
    public boolean D = false;
    public boolean E = false;
    public ArrayList<f> F = null;
    public ArrayList<Animator> G = new ArrayList<>();
    public g K = f1490c;

    /* JADX INFO: compiled from: Transition.java */
    public static class a extends g {
        @Override // b.q.g
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.e.a f1492a;

        public b(b.e.a aVar) {
            this.f1492a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1492a.remove(animator);
            l.this.B.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            l.this.B.add(animator);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            l.this.p();
            animator.removeListener(this);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f1495a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1496b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public r f1497c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public l0 f1498d;
        public l e;

        public d(View view, String str, l lVar, l0 l0Var, r rVar) {
            this.f1495a = view;
            this.f1496b = str;
            this.f1497c = rVar;
            this.f1498d = l0Var;
            this.e = lVar;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public static abstract class e {
    }

    /* JADX INFO: compiled from: Transition.java */
    public interface f {
        void a(l lVar);

        void b(l lVar);

        void c(l lVar);

        void d(l lVar);

        void e(l lVar);
    }

    public static boolean H(r rVar, r rVar2, String str) {
        Object obj = rVar.f1512a.get(str);
        Object obj2 = rVar2.f1512a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public static void d(s sVar, View view, r rVar) {
        sVar.f1515a.put(view, rVar);
        int id = view.getId();
        if (id >= 0) {
            if (sVar.f1516b.indexOfKey(id) >= 0) {
                sVar.f1516b.put(id, null);
            } else {
                sVar.f1516b.put(id, view);
            }
        }
        String strH = b.g.j.t.H(view);
        if (strH != null) {
            if (sVar.f1518d.containsKey(strH)) {
                sVar.f1518d.put(strH, null);
            } else {
                sVar.f1518d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (sVar.f1517c.g(itemIdAtPosition) < 0) {
                    b.g.j.t.s0(view, true);
                    sVar.f1517c.i(itemIdAtPosition, view);
                    return;
                }
                View viewE = sVar.f1517c.e(itemIdAtPosition);
                if (viewE != null) {
                    b.g.j.t.s0(viewE, false);
                    sVar.f1517c.i(itemIdAtPosition, null);
                }
            }
        }
    }

    public static b.e.a<Animator, d> x() {
        b.e.a<Animator, d> aVar = f1491d.get();
        if (aVar != null) {
            return aVar;
        }
        b.e.a<Animator, d> aVar2 = new b.e.a<>();
        f1491d.set(aVar2);
        return aVar2;
    }

    public List<String> A() {
        return this.k;
    }

    public List<Class<?>> B() {
        return this.l;
    }

    public List<View> C() {
        return this.j;
    }

    public String[] D() {
        return null;
    }

    public r E(View view, boolean z) {
        p pVar = this.v;
        if (pVar != null) {
            return pVar.E(view, z);
        }
        return (z ? this.t : this.u).f1515a.get(view);
    }

    public boolean F(r rVar, r rVar2) {
        if (rVar == null || rVar2 == null) {
            return false;
        }
        String[] strArrD = D();
        if (strArrD == null) {
            Iterator<String> it = rVar.f1512a.keySet().iterator();
            while (it.hasNext()) {
                if (H(rVar, rVar2, it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrD) {
            if (!H(rVar, rVar2, str)) {
            }
        }
        return false;
        return true;
    }

    public boolean G(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.m;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.n;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.o;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i = 0; i < size; i++) {
                if (this.o.get(i).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.p != null && b.g.j.t.H(view) != null && this.p.contains(b.g.j.t.H(view))) {
            return false;
        }
        if ((this.i.size() == 0 && this.j.size() == 0 && (((arrayList = this.l) == null || arrayList.isEmpty()) && ((arrayList2 = this.k) == null || arrayList2.isEmpty()))) || this.i.contains(Integer.valueOf(id)) || this.j.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.k;
        if (arrayList6 != null && arrayList6.contains(b.g.j.t.H(view))) {
            return true;
        }
        if (this.l != null) {
            for (int i2 = 0; i2 < this.l.size(); i2++) {
                if (this.l.get(i2).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void I(b.e.a<View, r> aVar, b.e.a<View, r> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View viewValueAt = sparseArray.valueAt(i);
            if (viewValueAt != null && G(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i))) != null && G(view)) {
                r rVar = aVar.get(viewValueAt);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.x.add(rVar);
                    this.y.add(rVar2);
                    aVar.remove(viewValueAt);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void J(b.e.a<View, r> aVar, b.e.a<View, r> aVar2) {
        r rVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewI = aVar.i(size);
            if (viewI != null && G(viewI) && (rVarRemove = aVar2.remove(viewI)) != null && G(rVarRemove.f1513b)) {
                this.x.add(aVar.k(size));
                this.y.add(rVarRemove);
            }
        }
    }

    public final void K(b.e.a<View, r> aVar, b.e.a<View, r> aVar2, b.e.d<View> dVar, b.e.d<View> dVar2) {
        View viewE;
        int iL = dVar.l();
        for (int i = 0; i < iL; i++) {
            View viewM = dVar.m(i);
            if (viewM != null && G(viewM) && (viewE = dVar2.e(dVar.h(i))) != null && G(viewE)) {
                r rVar = aVar.get(viewM);
                r rVar2 = aVar2.get(viewE);
                if (rVar != null && rVar2 != null) {
                    this.x.add(rVar);
                    this.y.add(rVar2);
                    aVar.remove(viewM);
                    aVar2.remove(viewE);
                }
            }
        }
    }

    public final void L(b.e.a<View, r> aVar, b.e.a<View, r> aVar2, b.e.a<String, View> aVar3, b.e.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i = 0; i < size; i++) {
            View viewM = aVar3.m(i);
            if (viewM != null && G(viewM) && (view = aVar4.get(aVar3.i(i))) != null && G(view)) {
                r rVar = aVar.get(viewM);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.x.add(rVar);
                    this.y.add(rVar2);
                    aVar.remove(viewM);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void M(s sVar, s sVar2) {
        b.e.a<View, r> aVar = new b.e.a<>(sVar.f1515a);
        b.e.a<View, r> aVar2 = new b.e.a<>(sVar2.f1515a);
        int i = 0;
        while (true) {
            int[] iArr = this.w;
            if (i >= iArr.length) {
                c(aVar, aVar2);
                return;
            }
            int i2 = iArr[i];
            if (i2 == 1) {
                J(aVar, aVar2);
            } else if (i2 == 2) {
                L(aVar, aVar2, sVar.f1518d, sVar2.f1518d);
            } else if (i2 == 3) {
                I(aVar, aVar2, sVar.f1516b, sVar2.f1516b);
            } else if (i2 == 4) {
                K(aVar, aVar2, sVar.f1517c, sVar2.f1517c);
            }
            i++;
        }
    }

    public void N(View view) {
        if (this.E) {
            return;
        }
        b.e.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        l0 l0VarD = b0.d(view);
        for (int i = size - 1; i >= 0; i--) {
            d dVarM = aVarX.m(i);
            if (dVarM.f1495a != null && l0VarD.equals(dVarM.f1498d)) {
                b.q.a.b(aVarX.i(i));
            }
        }
        ArrayList<f> arrayList = this.F;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.F.clone();
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((f) arrayList2.get(i2)).a(this);
            }
        }
        this.D = true;
    }

    public void O(ViewGroup viewGroup) {
        d dVar;
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        M(this.t, this.u);
        b.e.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        l0 l0VarD = b0.d(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animatorI = aVarX.i(i);
            if (animatorI != null && (dVar = aVarX.get(animatorI)) != null && dVar.f1495a != null && l0VarD.equals(dVar.f1498d)) {
                r rVar = dVar.f1497c;
                View view = dVar.f1495a;
                r rVarE = E(view, true);
                r rVarT = t(view, true);
                if (rVarE == null && rVarT == null) {
                    rVarT = this.u.f1515a.get(view);
                }
                if (!(rVarE == null && rVarT == null) && dVar.e.F(rVar, rVarT)) {
                    if (animatorI.isRunning() || animatorI.isStarted()) {
                        animatorI.cancel();
                    } else {
                        aVarX.remove(animatorI);
                    }
                }
            }
        }
        o(viewGroup, this.t, this.u, this.x, this.y);
        T();
    }

    public l P(f fVar) {
        ArrayList<f> arrayList = this.F;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(fVar);
        if (this.F.size() == 0) {
            this.F = null;
        }
        return this;
    }

    public l Q(View view) {
        this.j.remove(view);
        return this;
    }

    public void R(View view) {
        if (this.D) {
            if (!this.E) {
                b.e.a<Animator, d> aVarX = x();
                int size = aVarX.size();
                l0 l0VarD = b0.d(view);
                for (int i = size - 1; i >= 0; i--) {
                    d dVarM = aVarX.m(i);
                    if (dVarM.f1495a != null && l0VarD.equals(dVarM.f1498d)) {
                        b.q.a.c(aVarX.i(i));
                    }
                }
                ArrayList<f> arrayList = this.F;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.F.clone();
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((f) arrayList2.get(i2)).b(this);
                    }
                }
            }
            this.D = false;
        }
    }

    public final void S(Animator animator, b.e.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            e(animator);
        }
    }

    public void T() {
        a0();
        b.e.a<Animator, d> aVarX = x();
        for (Animator animator : this.G) {
            if (aVarX.containsKey(animator)) {
                a0();
                S(animator, aVarX);
            }
        }
        this.G.clear();
        p();
    }

    public l U(long j) {
        this.g = j;
        return this;
    }

    public void V(e eVar) {
        this.I = eVar;
    }

    public l W(TimeInterpolator timeInterpolator) {
        this.h = timeInterpolator;
        return this;
    }

    public void X(g gVar) {
        if (gVar == null) {
            this.K = f1490c;
        } else {
            this.K = gVar;
        }
    }

    public void Y(o oVar) {
    }

    public l Z(long j) {
        this.f = j;
        return this;
    }

    public l a(f fVar) {
        if (this.F == null) {
            this.F = new ArrayList<>();
        }
        this.F.add(fVar);
        return this;
    }

    public void a0() {
        if (this.C == 0) {
            ArrayList<f> arrayList = this.F;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.F.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((f) arrayList2.get(i)).c(this);
                }
            }
            this.E = false;
        }
        this.C++;
    }

    public l b(View view) {
        this.j.add(view);
        return this;
    }

    public String b0(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.g != -1) {
            str2 = str2 + "dur(" + this.g + ") ";
        }
        if (this.f != -1) {
            str2 = str2 + "dly(" + this.f + ") ";
        }
        if (this.h != null) {
            str2 = str2 + "interp(" + this.h + ") ";
        }
        if (this.i.size() <= 0 && this.j.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.i.size() > 0) {
            for (int i = 0; i < this.i.size(); i++) {
                if (i > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.i.get(i);
            }
        }
        if (this.j.size() > 0) {
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.j.get(i2);
            }
        }
        return str3 + ")";
    }

    public final void c(b.e.a<View, r> aVar, b.e.a<View, r> aVar2) {
        for (int i = 0; i < aVar.size(); i++) {
            r rVarM = aVar.m(i);
            if (G(rVarM.f1513b)) {
                this.x.add(rVarM);
                this.y.add(null);
            }
        }
        for (int i2 = 0; i2 < aVar2.size(); i2++) {
            r rVarM2 = aVar2.m(i2);
            if (G(rVarM2.f1513b)) {
                this.y.add(rVarM2);
                this.x.add(null);
            }
        }
    }

    public void e(Animator animator) {
        if (animator == null) {
            p();
            return;
        }
        if (q() >= 0) {
            animator.setDuration(q());
        }
        if (y() >= 0) {
            animator.setStartDelay(y() + animator.getStartDelay());
        }
        if (s() != null) {
            animator.setInterpolator(s());
        }
        animator.addListener(new c());
        animator.start();
    }

    public void f() {
        for (int size = this.B.size() - 1; size >= 0; size--) {
            this.B.get(size).cancel();
        }
        ArrayList<f> arrayList = this.F;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.F.clone();
        int size2 = arrayList2.size();
        for (int i = 0; i < size2; i++) {
            ((f) arrayList2.get(i)).d(this);
        }
    }

    public abstract void g(r rVar);

    public final void h(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.m;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.n;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.o;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i = 0; i < size; i++) {
                        if (this.o.get(i).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    r rVar = new r(view);
                    if (z) {
                        j(rVar);
                    } else {
                        g(rVar);
                    }
                    rVar.f1514c.add(this);
                    i(rVar);
                    if (z) {
                        d(this.t, view, rVar);
                    } else {
                        d(this.u, view, rVar);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.q;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.r;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.s;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    if (this.s.get(i2).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                                h(viewGroup.getChildAt(i3), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public void i(r rVar) {
        if (this.H != null && !rVar.f1512a.isEmpty()) {
            throw null;
        }
    }

    public abstract void j(r rVar);

    public void k(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        b.e.a<String, String> aVar;
        l(z);
        if ((this.i.size() > 0 || this.j.size() > 0) && (((arrayList = this.k) == null || arrayList.isEmpty()) && ((arrayList2 = this.l) == null || arrayList2.isEmpty()))) {
            for (int i = 0; i < this.i.size(); i++) {
                View viewFindViewById = viewGroup.findViewById(this.i.get(i).intValue());
                if (viewFindViewById != null) {
                    r rVar = new r(viewFindViewById);
                    if (z) {
                        j(rVar);
                    } else {
                        g(rVar);
                    }
                    rVar.f1514c.add(this);
                    i(rVar);
                    if (z) {
                        d(this.t, viewFindViewById, rVar);
                    } else {
                        d(this.u, viewFindViewById, rVar);
                    }
                }
            }
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                View view = this.j.get(i2);
                r rVar2 = new r(view);
                if (z) {
                    j(rVar2);
                } else {
                    g(rVar2);
                }
                rVar2.f1514c.add(this);
                i(rVar2);
                if (z) {
                    d(this.t, view, rVar2);
                } else {
                    d(this.u, view, rVar2);
                }
            }
        } else {
            h(viewGroup, z);
        }
        if (z || (aVar = this.J) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList3.add(this.t.f1518d.remove(this.J.i(i3)));
        }
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) arrayList3.get(i4);
            if (view2 != null) {
                this.t.f1518d.put(this.J.m(i4), view2);
            }
        }
    }

    public void l(boolean z) {
        if (z) {
            this.t.f1515a.clear();
            this.t.f1516b.clear();
            this.t.f1517c.b();
        } else {
            this.u.f1515a.clear();
            this.u.f1516b.clear();
            this.u.f1517c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public l clone() {
        try {
            l lVar = (l) super.clone();
            lVar.G = new ArrayList<>();
            lVar.t = new s();
            lVar.u = new s();
            lVar.x = null;
            lVar.y = null;
            return lVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void o(android.view.ViewGroup r20, b.q.s r21, b.q.s r22, java.util.ArrayList<b.q.r> r23, java.util.ArrayList<b.q.r> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.q.l.o(android.view.ViewGroup, b.q.s, b.q.s, java.util.ArrayList, java.util.ArrayList):void");
    }

    public void p() {
        int i = this.C - 1;
        this.C = i;
        if (i == 0) {
            ArrayList<f> arrayList = this.F;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.F.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((f) arrayList2.get(i2)).e(this);
                }
            }
            for (int i3 = 0; i3 < this.t.f1517c.l(); i3++) {
                View viewM = this.t.f1517c.m(i3);
                if (viewM != null) {
                    b.g.j.t.s0(viewM, false);
                }
            }
            for (int i4 = 0; i4 < this.u.f1517c.l(); i4++) {
                View viewM2 = this.u.f1517c.m(i4);
                if (viewM2 != null) {
                    b.g.j.t.s0(viewM2, false);
                }
            }
            this.E = true;
        }
    }

    public long q() {
        return this.g;
    }

    public e r() {
        return this.I;
    }

    public TimeInterpolator s() {
        return this.h;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r7 = r6.y;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r7 = r6.x;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
    
        return r7.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b.q.r t(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            b.q.p r0 = r6.v
            if (r0 == 0) goto L9
            b.q.r r7 = r0.t(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<b.q.r> r0 = r6.x
            goto L10
        Le:
            java.util.ArrayList<b.q.r> r0 = r6.y
        L10:
            r1 = 0
            if (r0 != 0) goto L14
            return r1
        L14:
            int r2 = r0.size()
            r3 = -1
            r4 = 0
        L1a:
            if (r4 >= r2) goto L2e
            java.lang.Object r5 = r0.get(r4)
            b.q.r r5 = (b.q.r) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f1513b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<b.q.r> r7 = r6.y
            goto L37
        L35:
            java.util.ArrayList<b.q.r> r7 = r6.x
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            b.q.r r1 = (b.q.r) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.q.l.t(android.view.View, boolean):b.q.r");
    }

    public String toString() {
        return b0("");
    }

    public String u() {
        return this.e;
    }

    public g v() {
        return this.K;
    }

    public o w() {
        return this.H;
    }

    public long y() {
        return this.f;
    }

    public List<Integer> z() {
        return this.i;
    }
}
