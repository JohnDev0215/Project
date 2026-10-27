package e1;

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
    private static final int[] L = {2, 1, 3, 4};
    private static final g M = new a();
    private static ThreadLocal<n.a<Animator, d>> N = new ThreadLocal<>();
    private e I;
    private n.a<String, String> J;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ArrayList<r> f6137y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private ArrayList<r> f6138z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f6118e = getClass().getName();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f6119g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    long f6120h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private TimeInterpolator f6121i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    ArrayList<Integer> f6122j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    ArrayList<View> f6123k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ArrayList<String> f6124l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ArrayList<Class<?>> f6125m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private ArrayList<Integer> f6126n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private ArrayList<View> f6127o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ArrayList<Class<?>> f6128p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private ArrayList<String> f6129q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private ArrayList<Integer> f6130r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private ArrayList<View> f6131s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private ArrayList<Class<?>> f6132t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private s f6133u = new s();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private s f6134v = new s();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    p f6135w = null;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int[] f6136x = L;
    private ViewGroup A = null;
    boolean B = false;
    ArrayList<Animator> C = new ArrayList<>();
    private int D = 0;
    private boolean E = false;
    private boolean F = false;
    private ArrayList<f> G = null;
    private ArrayList<Animator> H = new ArrayList<>();
    private g K = M;

    /* JADX INFO: compiled from: Transition.java */
    static class a extends g {
        a() {
        }

        @Override // e1.g
        public Path a(float f7, float f8, float f9, float f10) {
            Path path = new Path();
            path.moveTo(f7, f8);
            path.lineTo(f9, f10);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ n.a f6139a;

        b(n.a aVar) {
            this.f6139a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f6139a.remove(animator);
            l.this.C.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            l.this.C.add(animator);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            l.this.p();
            animator.removeListener(this);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f6142a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f6143b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        r f6144c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        h0 f6145d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        l f6146e;

        d(View view, String str, l lVar, h0 h0Var, r rVar) {
            this.f6142a = view;
            this.f6143b = str;
            this.f6144c = rVar;
            this.f6145d = h0Var;
            this.f6146e = lVar;
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

    private static boolean H(r rVar, r rVar2, String str) {
        Object obj = rVar.f6160a.get(str);
        Object obj2 = rVar2.f6160a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    private void I(n.a<View, r> aVar, n.a<View, r> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i7 = 0; i7 < size; i7++) {
            View viewValueAt = sparseArray.valueAt(i7);
            if (viewValueAt != null && G(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i7))) != null && G(view)) {
                r rVar = aVar.get(viewValueAt);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.f6137y.add(rVar);
                    this.f6138z.add(rVar2);
                    aVar.remove(viewValueAt);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void J(n.a<View, r> aVar, n.a<View, r> aVar2) {
        r rVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewK = aVar.k(size);
            if (viewK != null && G(viewK) && (rVarRemove = aVar2.remove(viewK)) != null && G(rVarRemove.f6161b)) {
                this.f6137y.add(aVar.m(size));
                this.f6138z.add(rVarRemove);
            }
        }
    }

    private void K(n.a<View, r> aVar, n.a<View, r> aVar2, n.d<View> dVar, n.d<View> dVar2) {
        View viewE;
        int iN = dVar.n();
        for (int i7 = 0; i7 < iN; i7++) {
            View viewO = dVar.o(i7);
            if (viewO != null && G(viewO) && (viewE = dVar2.e(dVar.i(i7))) != null && G(viewE)) {
                r rVar = aVar.get(viewO);
                r rVar2 = aVar2.get(viewE);
                if (rVar != null && rVar2 != null) {
                    this.f6137y.add(rVar);
                    this.f6138z.add(rVar2);
                    aVar.remove(viewO);
                    aVar2.remove(viewE);
                }
            }
        }
    }

    private void L(n.a<View, r> aVar, n.a<View, r> aVar2, n.a<String, View> aVar3, n.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i7 = 0; i7 < size; i7++) {
            View viewO = aVar3.o(i7);
            if (viewO != null && G(viewO) && (view = aVar4.get(aVar3.k(i7))) != null && G(view)) {
                r rVar = aVar.get(viewO);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.f6137y.add(rVar);
                    this.f6138z.add(rVar2);
                    aVar.remove(viewO);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void M(s sVar, s sVar2) {
        n.a<View, r> aVar = new n.a<>(sVar.f6163a);
        n.a<View, r> aVar2 = new n.a<>(sVar2.f6163a);
        int i7 = 0;
        while (true) {
            int[] iArr = this.f6136x;
            if (i7 >= iArr.length) {
                c(aVar, aVar2);
                return;
            }
            int i8 = iArr[i7];
            if (i8 == 1) {
                J(aVar, aVar2);
            } else if (i8 == 2) {
                L(aVar, aVar2, sVar.f6166d, sVar2.f6166d);
            } else if (i8 == 3) {
                I(aVar, aVar2, sVar.f6164b, sVar2.f6164b);
            } else if (i8 == 4) {
                K(aVar, aVar2, sVar.f6165c, sVar2.f6165c);
            }
            i7++;
        }
    }

    private void S(Animator animator, n.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            e(animator);
        }
    }

    private void c(n.a<View, r> aVar, n.a<View, r> aVar2) {
        for (int i7 = 0; i7 < aVar.size(); i7++) {
            r rVarO = aVar.o(i7);
            if (G(rVarO.f6161b)) {
                this.f6137y.add(rVarO);
                this.f6138z.add(null);
            }
        }
        for (int i8 = 0; i8 < aVar2.size(); i8++) {
            r rVarO2 = aVar2.o(i8);
            if (G(rVarO2.f6161b)) {
                this.f6138z.add(rVarO2);
                this.f6137y.add(null);
            }
        }
    }

    private static void d(s sVar, View view, r rVar) {
        sVar.f6163a.put(view, rVar);
        int id = view.getId();
        if (id >= 0) {
            if (sVar.f6164b.indexOfKey(id) >= 0) {
                sVar.f6164b.put(id, null);
            } else {
                sVar.f6164b.put(id, view);
            }
        }
        String strH = i0.u.H(view);
        if (strH != null) {
            if (sVar.f6166d.containsKey(strH)) {
                sVar.f6166d.put(strH, null);
            } else {
                sVar.f6166d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (sVar.f6165c.g(itemIdAtPosition) < 0) {
                    i0.u.s0(view, true);
                    sVar.f6165c.j(itemIdAtPosition, view);
                    return;
                }
                View viewE = sVar.f6165c.e(itemIdAtPosition);
                if (viewE != null) {
                    i0.u.s0(viewE, false);
                    sVar.f6165c.j(itemIdAtPosition, null);
                }
            }
        }
    }

    private void g(View view, boolean z6) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.f6126n;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.f6127o;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.f6128p;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        if (this.f6128p.get(i7).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    r rVar = new r(view);
                    if (z6) {
                        i(rVar);
                    } else {
                        f(rVar);
                    }
                    rVar.f6162c.add(this);
                    h(rVar);
                    if (z6) {
                        d(this.f6133u, view, rVar);
                    } else {
                        d(this.f6134v, view, rVar);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.f6130r;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.f6131s;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.f6132t;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i8 = 0; i8 < size2; i8++) {
                                    if (this.f6132t.get(i8).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
                                g(viewGroup.getChildAt(i9), z6);
                            }
                        }
                    }
                }
            }
        }
    }

    private static n.a<Animator, d> x() {
        n.a<Animator, d> aVar = N.get();
        if (aVar != null) {
            return aVar;
        }
        n.a<Animator, d> aVar2 = new n.a<>();
        N.set(aVar2);
        return aVar2;
    }

    public List<String> A() {
        return this.f6124l;
    }

    public List<Class<?>> B() {
        return this.f6125m;
    }

    public List<View> C() {
        return this.f6123k;
    }

    public String[] D() {
        return null;
    }

    public r E(View view, boolean z6) {
        p pVar = this.f6135w;
        if (pVar != null) {
            return pVar.E(view, z6);
        }
        return (z6 ? this.f6133u : this.f6134v).f6163a.get(view);
    }

    public boolean F(r rVar, r rVar2) {
        if (rVar == null || rVar2 == null) {
            return false;
        }
        String[] strArrD = D();
        if (strArrD == null) {
            Iterator<String> it = rVar.f6160a.keySet().iterator();
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

    boolean G(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.f6126n;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.f6127o;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.f6128p;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (this.f6128p.get(i7).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.f6129q != null && i0.u.H(view) != null && this.f6129q.contains(i0.u.H(view))) {
            return false;
        }
        if ((this.f6122j.size() == 0 && this.f6123k.size() == 0 && (((arrayList = this.f6125m) == null || arrayList.isEmpty()) && ((arrayList2 = this.f6124l) == null || arrayList2.isEmpty()))) || this.f6122j.contains(Integer.valueOf(id)) || this.f6123k.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.f6124l;
        if (arrayList6 != null && arrayList6.contains(i0.u.H(view))) {
            return true;
        }
        if (this.f6125m != null) {
            for (int i8 = 0; i8 < this.f6125m.size(); i8++) {
                if (this.f6125m.get(i8).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void N(View view) {
        if (this.F) {
            return;
        }
        n.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        h0 h0VarD = y.d(view);
        for (int i7 = size - 1; i7 >= 0; i7--) {
            d dVarO = aVarX.o(i7);
            if (dVarO.f6142a != null && h0VarD.equals(dVarO.f6145d)) {
                e1.a.b(aVarX.k(i7));
            }
        }
        ArrayList<f> arrayList = this.G;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.G.clone();
            int size2 = arrayList2.size();
            for (int i8 = 0; i8 < size2; i8++) {
                ((f) arrayList2.get(i8)).b(this);
            }
        }
        this.E = true;
    }

    void O(ViewGroup viewGroup) {
        d dVar;
        this.f6137y = new ArrayList<>();
        this.f6138z = new ArrayList<>();
        M(this.f6133u, this.f6134v);
        n.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        h0 h0VarD = y.d(viewGroup);
        for (int i7 = size - 1; i7 >= 0; i7--) {
            Animator animatorK = aVarX.k(i7);
            if (animatorK != null && (dVar = aVarX.get(animatorK)) != null && dVar.f6142a != null && h0VarD.equals(dVar.f6145d)) {
                r rVar = dVar.f6144c;
                View view = dVar.f6142a;
                r rVarE = E(view, true);
                r rVarT = t(view, true);
                if (rVarE == null && rVarT == null) {
                    rVarT = this.f6134v.f6163a.get(view);
                }
                if (!(rVarE == null && rVarT == null) && dVar.f6146e.F(rVar, rVarT)) {
                    if (animatorK.isRunning() || animatorK.isStarted()) {
                        animatorK.cancel();
                    } else {
                        aVarX.remove(animatorK);
                    }
                }
            }
        }
        o(viewGroup, this.f6133u, this.f6134v, this.f6137y, this.f6138z);
        T();
    }

    public l P(f fVar) {
        ArrayList<f> arrayList = this.G;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(fVar);
        if (this.G.size() == 0) {
            this.G = null;
        }
        return this;
    }

    public l Q(View view) {
        this.f6123k.remove(view);
        return this;
    }

    public void R(View view) {
        if (this.E) {
            if (!this.F) {
                n.a<Animator, d> aVarX = x();
                int size = aVarX.size();
                h0 h0VarD = y.d(view);
                for (int i7 = size - 1; i7 >= 0; i7--) {
                    d dVarO = aVarX.o(i7);
                    if (dVarO.f6142a != null && h0VarD.equals(dVarO.f6145d)) {
                        e1.a.c(aVarX.k(i7));
                    }
                }
                ArrayList<f> arrayList = this.G;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.G.clone();
                    int size2 = arrayList2.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        ((f) arrayList2.get(i8)).c(this);
                    }
                }
            }
            this.E = false;
        }
    }

    protected void T() {
        a0();
        n.a<Animator, d> aVarX = x();
        for (Animator animator : this.H) {
            if (aVarX.containsKey(animator)) {
                a0();
                S(animator, aVarX);
            }
        }
        this.H.clear();
        p();
    }

    public l U(long j7) {
        this.f6120h = j7;
        return this;
    }

    public void V(e eVar) {
        this.I = eVar;
    }

    public l W(TimeInterpolator timeInterpolator) {
        this.f6121i = timeInterpolator;
        return this;
    }

    public void X(g gVar) {
        if (gVar == null) {
            this.K = M;
        } else {
            this.K = gVar;
        }
    }

    public void Y(o oVar) {
    }

    public l Z(long j7) {
        this.f6119g = j7;
        return this;
    }

    public l a(f fVar) {
        if (this.G == null) {
            this.G = new ArrayList<>();
        }
        this.G.add(fVar);
        return this;
    }

    protected void a0() {
        if (this.D == 0) {
            ArrayList<f> arrayList = this.G;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.G.clone();
                int size = arrayList2.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ((f) arrayList2.get(i7)).a(this);
                }
            }
            this.F = false;
        }
        this.D++;
    }

    public l b(View view) {
        this.f6123k.add(view);
        return this;
    }

    String b0(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f6120h != -1) {
            str2 = str2 + "dur(" + this.f6120h + ") ";
        }
        if (this.f6119g != -1) {
            str2 = str2 + "dly(" + this.f6119g + ") ";
        }
        if (this.f6121i != null) {
            str2 = str2 + "interp(" + this.f6121i + ") ";
        }
        if (this.f6122j.size() <= 0 && this.f6123k.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f6122j.size() > 0) {
            for (int i7 = 0; i7 < this.f6122j.size(); i7++) {
                if (i7 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f6122j.get(i7);
            }
        }
        if (this.f6123k.size() > 0) {
            for (int i8 = 0; i8 < this.f6123k.size(); i8++) {
                if (i8 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f6123k.get(i8);
            }
        }
        return str3 + ")";
    }

    protected void cancel() {
        for (int size = this.C.size() - 1; size >= 0; size--) {
            this.C.get(size).cancel();
        }
        ArrayList<f> arrayList = this.G;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.G.clone();
        int size2 = arrayList2.size();
        for (int i7 = 0; i7 < size2; i7++) {
            ((f) arrayList2.get(i7)).d(this);
        }
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

    public abstract void f(r rVar);

    void h(r rVar) {
    }

    public abstract void i(r rVar);

    void j(ViewGroup viewGroup, boolean z6) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        n.a<String, String> aVar;
        k(z6);
        if ((this.f6122j.size() > 0 || this.f6123k.size() > 0) && (((arrayList = this.f6124l) == null || arrayList.isEmpty()) && ((arrayList2 = this.f6125m) == null || arrayList2.isEmpty()))) {
            for (int i7 = 0; i7 < this.f6122j.size(); i7++) {
                View viewFindViewById = viewGroup.findViewById(this.f6122j.get(i7).intValue());
                if (viewFindViewById != null) {
                    r rVar = new r(viewFindViewById);
                    if (z6) {
                        i(rVar);
                    } else {
                        f(rVar);
                    }
                    rVar.f6162c.add(this);
                    h(rVar);
                    if (z6) {
                        d(this.f6133u, viewFindViewById, rVar);
                    } else {
                        d(this.f6134v, viewFindViewById, rVar);
                    }
                }
            }
            for (int i8 = 0; i8 < this.f6123k.size(); i8++) {
                View view = this.f6123k.get(i8);
                r rVar2 = new r(view);
                if (z6) {
                    i(rVar2);
                } else {
                    f(rVar2);
                }
                rVar2.f6162c.add(this);
                h(rVar2);
                if (z6) {
                    d(this.f6133u, view, rVar2);
                } else {
                    d(this.f6134v, view, rVar2);
                }
            }
        } else {
            g(viewGroup, z6);
        }
        if (z6 || (aVar = this.J) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i9 = 0; i9 < size; i9++) {
            arrayList3.add(this.f6133u.f6166d.remove(this.J.k(i9)));
        }
        for (int i10 = 0; i10 < size; i10++) {
            View view2 = (View) arrayList3.get(i10);
            if (view2 != null) {
                this.f6133u.f6166d.put(this.J.o(i10), view2);
            }
        }
    }

    void k(boolean z6) {
        if (z6) {
            this.f6133u.f6163a.clear();
            this.f6133u.f6164b.clear();
            this.f6133u.f6165c.a();
        } else {
            this.f6134v.f6163a.clear();
            this.f6134v.f6164b.clear();
            this.f6134v.f6165c.a();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public l clone() {
        try {
            l lVar = (l) super.clone();
            lVar.H = new ArrayList<>();
            lVar.f6133u = new s();
            lVar.f6134v = new s();
            lVar.f6137y = null;
            lVar.f6138z = null;
            return lVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void o(android.view.ViewGroup r20, e1.s r21, e1.s r22, java.util.ArrayList<e1.r> r23, java.util.ArrayList<e1.r> r24) {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e1.l.o(android.view.ViewGroup, e1.s, e1.s, java.util.ArrayList, java.util.ArrayList):void");
    }

    protected void p() {
        int i7 = this.D - 1;
        this.D = i7;
        if (i7 == 0) {
            ArrayList<f> arrayList = this.G;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.G.clone();
                int size = arrayList2.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((f) arrayList2.get(i8)).e(this);
                }
            }
            for (int i9 = 0; i9 < this.f6133u.f6165c.n(); i9++) {
                View viewO = this.f6133u.f6165c.o(i9);
                if (viewO != null) {
                    i0.u.s0(viewO, false);
                }
            }
            for (int i10 = 0; i10 < this.f6134v.f6165c.n(); i10++) {
                View viewO2 = this.f6134v.f6165c.o(i10);
                if (viewO2 != null) {
                    i0.u.s0(viewO2, false);
                }
            }
            this.F = true;
        }
    }

    public long q() {
        return this.f6120h;
    }

    public e r() {
        return this.I;
    }

    public TimeInterpolator s() {
        return this.f6121i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r7 = r6.f6138z;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r7 = r6.f6137y;
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
    e1.r t(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            e1.p r0 = r6.f6135w
            if (r0 == 0) goto L9
            e1.r r7 = r0.t(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<e1.r> r0 = r6.f6137y
            goto L10
        Le:
            java.util.ArrayList<e1.r> r0 = r6.f6138z
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
            e1.r r5 = (e1.r) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f6161b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<e1.r> r7 = r6.f6138z
            goto L37
        L35:
            java.util.ArrayList<e1.r> r7 = r6.f6137y
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            e1.r r1 = (e1.r) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: e1.l.t(android.view.View, boolean):e1.r");
    }

    public String toString() {
        return b0("");
    }

    public String u() {
        return this.f6118e;
    }

    public g v() {
        return this.K;
    }

    public o w() {
        return null;
    }

    public long y() {
        return this.f6119g;
    }

    public List<Integer> z() {
        return this.f6122j;
    }
}
