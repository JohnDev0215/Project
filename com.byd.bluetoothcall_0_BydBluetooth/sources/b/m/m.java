package b.m;

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
public abstract class m implements Cloneable {

    /* JADX INFO: renamed from: b */
    private static final int[] f2497b = {2, 1, 3, 4};

    /* JADX INFO: renamed from: c */
    private static final g f2498c = new a();

    /* JADX INFO: renamed from: d */
    private static ThreadLocal<b.d.a<Animator, d>> f2499d = new ThreadLocal<>();
    p H;
    private e I;
    private b.d.a<String, String> J;
    private ArrayList<s> x;
    private ArrayList<s> y;

    /* JADX INFO: renamed from: e */
    private String f2500e = getClass().getName();
    private long f = -1;
    long g = -1;
    private TimeInterpolator h = null;
    ArrayList<Integer> i = new ArrayList<>();
    ArrayList<View> j = new ArrayList<>();
    private ArrayList<String> k = null;
    private ArrayList<Class<?>> l = null;
    private ArrayList<Integer> m = null;
    private ArrayList<View> n = null;
    private ArrayList<Class<?>> o = null;
    private ArrayList<String> p = null;
    private ArrayList<Integer> q = null;
    private ArrayList<View> r = null;
    private ArrayList<Class<?>> s = null;
    private t t = new t();
    private t u = new t();
    q v = null;
    private int[] w = f2497b;
    private ViewGroup z = null;
    boolean A = false;
    ArrayList<Animator> B = new ArrayList<>();
    private int C = 0;
    private boolean D = false;
    private boolean E = false;
    private ArrayList<f> F = null;
    private ArrayList<Animator> G = new ArrayList<>();
    private g K = f2498c;

    /* JADX INFO: compiled from: Transition.java */
    static class a extends g {
        a() {
        }

        @Override // b.m.g
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ b.d.a f2501a;

        b(b.d.a aVar) {
            this.f2501a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2501a.remove(animator);
            m.this.B.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            m.this.B.add(animator);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            m.this.p();
            animator.removeListener(this);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    private static class d {

        /* JADX INFO: renamed from: a */
        View f2504a;

        /* JADX INFO: renamed from: b */
        String f2505b;

        /* JADX INFO: renamed from: c */
        s f2506c;

        /* JADX INFO: renamed from: d */
        m0 f2507d;

        /* JADX INFO: renamed from: e */
        m f2508e;

        d(View view, String str, m mVar, m0 m0Var, s sVar) {
            this.f2504a = view;
            this.f2505b = str;
            this.f2506c = sVar;
            this.f2507d = m0Var;
            this.f2508e = mVar;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public static abstract class e {
    }

    /* JADX INFO: compiled from: Transition.java */
    public interface f {
        void a(m mVar);

        void b(m mVar);

        void c(m mVar);

        void d(m mVar);

        void e(m mVar);
    }

    private static boolean H(s sVar, s sVar2, String str) {
        Object obj = sVar.f2522a.get(str);
        Object obj2 = sVar2.f2522a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    private void I(b.d.a<View, s> aVar, b.d.a<View, s> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View viewValueAt = sparseArray.valueAt(i);
            if (viewValueAt != null && G(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i))) != null && G(view)) {
                s sVar = aVar.get(viewValueAt);
                s sVar2 = aVar2.get(view);
                if (sVar != null && sVar2 != null) {
                    this.x.add(sVar);
                    this.y.add(sVar2);
                    aVar.remove(viewValueAt);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void J(b.d.a<View, s> aVar, b.d.a<View, s> aVar2) {
        s sVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewI = aVar.i(size);
            if (viewI != null && G(viewI) && (sVarRemove = aVar2.remove(viewI)) != null && G(sVarRemove.f2523b)) {
                this.x.add(aVar.k(size));
                this.y.add(sVarRemove);
            }
        }
    }

    private void K(b.d.a<View, s> aVar, b.d.a<View, s> aVar2, b.d.d<View> dVar, b.d.d<View> dVar2) {
        View viewF;
        int iM = dVar.m();
        for (int i = 0; i < iM; i++) {
            View viewN = dVar.n(i);
            if (viewN != null && G(viewN) && (viewF = dVar2.f(dVar.i(i))) != null && G(viewF)) {
                s sVar = aVar.get(viewN);
                s sVar2 = aVar2.get(viewF);
                if (sVar != null && sVar2 != null) {
                    this.x.add(sVar);
                    this.y.add(sVar2);
                    aVar.remove(viewN);
                    aVar2.remove(viewF);
                }
            }
        }
    }

    private void L(b.d.a<View, s> aVar, b.d.a<View, s> aVar2, b.d.a<String, View> aVar3, b.d.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i = 0; i < size; i++) {
            View viewM = aVar3.m(i);
            if (viewM != null && G(viewM) && (view = aVar4.get(aVar3.i(i))) != null && G(view)) {
                s sVar = aVar.get(viewM);
                s sVar2 = aVar2.get(view);
                if (sVar != null && sVar2 != null) {
                    this.x.add(sVar);
                    this.y.add(sVar2);
                    aVar.remove(viewM);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void M(t tVar, t tVar2) {
        b.d.a<View, s> aVar = new b.d.a<>(tVar.f2525a);
        b.d.a<View, s> aVar2 = new b.d.a<>(tVar2.f2525a);
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
                L(aVar, aVar2, tVar.f2528d, tVar2.f2528d);
            } else if (i2 == 3) {
                I(aVar, aVar2, tVar.f2526b, tVar2.f2526b);
            } else if (i2 == 4) {
                K(aVar, aVar2, tVar.f2527c, tVar2.f2527c);
            }
            i++;
        }
    }

    private void S(Animator animator, b.d.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            e(animator);
        }
    }

    private void c(b.d.a<View, s> aVar, b.d.a<View, s> aVar2) {
        for (int i = 0; i < aVar.size(); i++) {
            s sVarM = aVar.m(i);
            if (G(sVarM.f2523b)) {
                this.x.add(sVarM);
                this.y.add(null);
            }
        }
        for (int i2 = 0; i2 < aVar2.size(); i2++) {
            s sVarM2 = aVar2.m(i2);
            if (G(sVarM2.f2523b)) {
                this.y.add(sVarM2);
                this.x.add(null);
            }
        }
    }

    private static void d(t tVar, View view, s sVar) {
        tVar.f2525a.put(view, sVar);
        int id = view.getId();
        if (id >= 0) {
            if (tVar.f2526b.indexOfKey(id) >= 0) {
                tVar.f2526b.put(id, null);
            } else {
                tVar.f2526b.put(id, view);
            }
        }
        String strH = b.g.k.t.H(view);
        if (strH != null) {
            if (tVar.f2528d.containsKey(strH)) {
                tVar.f2528d.put(strH, null);
            } else {
                tVar.f2528d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (tVar.f2527c.h(itemIdAtPosition) < 0) {
                    b.g.k.t.t0(view, true);
                    tVar.f2527c.j(itemIdAtPosition, view);
                    return;
                }
                View viewF = tVar.f2527c.f(itemIdAtPosition);
                if (viewF != null) {
                    b.g.k.t.t0(viewF, false);
                    tVar.f2527c.j(itemIdAtPosition, null);
                }
            }
        }
    }

    private void h(View view, boolean z) {
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
                    s sVar = new s(view);
                    if (z) {
                        j(sVar);
                    } else {
                        g(sVar);
                    }
                    sVar.f2524c.add(this);
                    i(sVar);
                    if (z) {
                        d(this.t, view, sVar);
                    } else {
                        d(this.u, view, sVar);
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

    private static b.d.a<Animator, d> x() {
        b.d.a<Animator, d> aVar = f2499d.get();
        if (aVar != null) {
            return aVar;
        }
        b.d.a<Animator, d> aVar2 = new b.d.a<>();
        f2499d.set(aVar2);
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

    public s E(View view, boolean z) {
        q qVar = this.v;
        if (qVar != null) {
            return qVar.E(view, z);
        }
        return (z ? this.t : this.u).f2525a.get(view);
    }

    public boolean F(s sVar, s sVar2) {
        if (sVar == null || sVar2 == null) {
            return false;
        }
        String[] strArrD = D();
        if (strArrD == null) {
            Iterator<String> it = sVar.f2522a.keySet().iterator();
            while (it.hasNext()) {
                if (H(sVar, sVar2, it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrD) {
            if (!H(sVar, sVar2, str)) {
            }
        }
        return false;
        return true;
    }

    boolean G(View view) {
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
        if (this.p != null && b.g.k.t.H(view) != null && this.p.contains(b.g.k.t.H(view))) {
            return false;
        }
        if ((this.i.size() == 0 && this.j.size() == 0 && (((arrayList = this.l) == null || arrayList.isEmpty()) && ((arrayList2 = this.k) == null || arrayList2.isEmpty()))) || this.i.contains(Integer.valueOf(id)) || this.j.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.k;
        if (arrayList6 != null && arrayList6.contains(b.g.k.t.H(view))) {
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

    public void N(View view) {
        if (this.E) {
            return;
        }
        b.d.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        m0 m0VarD = c0.d(view);
        for (int i = size - 1; i >= 0; i--) {
            d dVarM = aVarX.m(i);
            if (dVarM.f2504a != null && m0VarD.equals(dVarM.f2507d)) {
                b.m.a.b(aVarX.i(i));
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

    void O(ViewGroup viewGroup) {
        d dVar;
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        M(this.t, this.u);
        b.d.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        m0 m0VarD = c0.d(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animatorI = aVarX.i(i);
            if (animatorI != null && (dVar = aVarX.get(animatorI)) != null && dVar.f2504a != null && m0VarD.equals(dVar.f2507d)) {
                s sVar = dVar.f2506c;
                View view = dVar.f2504a;
                s sVarE = E(view, true);
                s sVarT = t(view, true);
                if (sVarE == null && sVarT == null) {
                    sVarT = this.u.f2525a.get(view);
                }
                if (!(sVarE == null && sVarT == null) && dVar.f2508e.F(sVar, sVarT)) {
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

    public m P(f fVar) {
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

    public m Q(View view) {
        this.j.remove(view);
        return this;
    }

    public void R(View view) {
        if (this.D) {
            if (!this.E) {
                b.d.a<Animator, d> aVarX = x();
                int size = aVarX.size();
                m0 m0VarD = c0.d(view);
                for (int i = size - 1; i >= 0; i--) {
                    d dVarM = aVarX.m(i);
                    if (dVarM.f2504a != null && m0VarD.equals(dVarM.f2507d)) {
                        b.m.a.c(aVarX.i(i));
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

    protected void T() {
        a0();
        b.d.a<Animator, d> aVarX = x();
        for (Animator animator : this.G) {
            if (aVarX.containsKey(animator)) {
                a0();
                S(animator, aVarX);
            }
        }
        this.G.clear();
        p();
    }

    public m U(long j) {
        this.g = j;
        return this;
    }

    public void V(e eVar) {
        this.I = eVar;
    }

    public m W(TimeInterpolator timeInterpolator) {
        this.h = timeInterpolator;
        return this;
    }

    public void X(g gVar) {
        if (gVar == null) {
            this.K = f2498c;
        } else {
            this.K = gVar;
        }
    }

    public void Y(p pVar) {
    }

    public m Z(long j) {
        this.f = j;
        return this;
    }

    public m a(f fVar) {
        if (this.F == null) {
            this.F = new ArrayList<>();
        }
        this.F.add(fVar);
        return this;
    }

    protected void a0() {
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

    public m b(View view) {
        this.j.add(view);
        return this;
    }

    String b0(String str) {
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

    protected void e(Animator animator) {
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

    protected void f() {
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

    public abstract void g(s sVar);

    void i(s sVar) {
        if (this.H != null && !sVar.f2522a.isEmpty()) {
            throw null;
        }
    }

    public abstract void j(s sVar);

    void k(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        b.d.a<String, String> aVar;
        l(z);
        if ((this.i.size() > 0 || this.j.size() > 0) && (((arrayList = this.k) == null || arrayList.isEmpty()) && ((arrayList2 = this.l) == null || arrayList2.isEmpty()))) {
            for (int i = 0; i < this.i.size(); i++) {
                View viewFindViewById = viewGroup.findViewById(this.i.get(i).intValue());
                if (viewFindViewById != null) {
                    s sVar = new s(viewFindViewById);
                    if (z) {
                        j(sVar);
                    } else {
                        g(sVar);
                    }
                    sVar.f2524c.add(this);
                    i(sVar);
                    if (z) {
                        d(this.t, viewFindViewById, sVar);
                    } else {
                        d(this.u, viewFindViewById, sVar);
                    }
                }
            }
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                View view = this.j.get(i2);
                s sVar2 = new s(view);
                if (z) {
                    j(sVar2);
                } else {
                    g(sVar2);
                }
                sVar2.f2524c.add(this);
                i(sVar2);
                if (z) {
                    d(this.t, view, sVar2);
                } else {
                    d(this.u, view, sVar2);
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
            arrayList3.add(this.t.f2528d.remove(this.J.i(i3)));
        }
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) arrayList3.get(i4);
            if (view2 != null) {
                this.t.f2528d.put(this.J.m(i4), view2);
            }
        }
    }

    void l(boolean z) {
        if (z) {
            this.t.f2525a.clear();
            this.t.f2526b.clear();
            this.t.f2527c.b();
        } else {
            this.u.f2525a.clear();
            this.u.f2526b.clear();
            this.u.f2527c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: m */
    public m clone() {
        try {
            m mVar = (m) super.clone();
            mVar.G = new ArrayList<>();
            mVar.t = new t();
            mVar.u = new t();
            mVar.x = null;
            mVar.y = null;
            return mVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator n(ViewGroup viewGroup, s sVar, s sVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:97:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void o(android.view.ViewGroup r20, b.m.t r21, b.m.t r22, java.util.ArrayList<b.m.s> r23, java.util.ArrayList<b.m.s> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.m.m.o(android.view.ViewGroup, b.m.t, b.m.t, java.util.ArrayList, java.util.ArrayList):void");
    }

    protected void p() {
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
            for (int i3 = 0; i3 < this.t.f2527c.m(); i3++) {
                View viewN = this.t.f2527c.n(i3);
                if (viewN != null) {
                    b.g.k.t.t0(viewN, false);
                }
            }
            for (int i4 = 0; i4 < this.u.f2527c.m(); i4++) {
                View viewN2 = this.u.f2527c.n(i4);
                if (viewN2 != null) {
                    b.g.k.t.t0(viewN2, false);
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

    /* JADX WARN: Code restructure failed: missing block: B:52:0x002e, code lost:
    
        if (r3 < 0) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0030, code lost:
    
        if (r8 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0032, code lost:
    
        r7 = r6.y;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0035, code lost:
    
        r7 = r6.x;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x003e, code lost:
    
        return r7.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    b.m.s t(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            b.m.q r0 = r6.v
            if (r0 == 0) goto L9
            b.m.s r7 = r0.t(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<b.m.s> r0 = r6.x
            goto L10
        Le:
            java.util.ArrayList<b.m.s> r0 = r6.y
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
            b.m.s r5 = (b.m.s) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f2523b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<b.m.s> r7 = r6.y
            goto L37
        L35:
            java.util.ArrayList<b.m.s> r7 = r6.x
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            b.m.s r1 = (b.m.s) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.m.m.t(android.view.View, boolean):b.m.s");
    }

    public String toString() {
        return b0("");
    }

    public String u() {
        return this.f2500e;
    }

    public g v() {
        return this.K;
    }

    public p w() {
        return this.H;
    }

    public long y() {
        return this.f;
    }

    public List<Integer> z() {
        return this.i;
    }
}
