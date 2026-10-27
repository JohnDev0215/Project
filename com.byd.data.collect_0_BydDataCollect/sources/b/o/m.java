package b.o;

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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f2890b = {2, 1, 3, 4};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final g f2891c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ThreadLocal<b.d.a<Animator, d>> f2892d = new ThreadLocal<>();
    p H;
    private e I;
    private b.d.a<String, String> J;
    private ArrayList<s> x;
    private ArrayList<s> y;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2893e = getClass().getName();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f2894f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    long f2895g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TimeInterpolator f2896h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ArrayList<Integer> f2897i = new ArrayList<>();
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
    private int[] w = f2890b;
    private ViewGroup z = null;
    boolean A = false;
    ArrayList<Animator> B = new ArrayList<>();
    private int C = 0;
    private boolean D = false;
    private boolean E = false;
    private ArrayList<f> F = null;
    private ArrayList<Animator> G = new ArrayList<>();
    private g K = f2891c;

    /* JADX INFO: compiled from: Transition.java */
    static class a extends g {
        a() {
        }

        @Override // b.o.g
        public Path a(float f2, float f3, float f4, float f5) {
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f4, f5);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.d.a f2898a;

        b(b.d.a aVar) {
            this.f2898a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2898a.remove(animator);
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
            m.this.r();
            animator.removeListener(this);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f2901a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2902b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        s f2903c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        m0 f2904d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        m f2905e;

        d(View view, String str, m mVar, m0 m0Var, s sVar) {
            this.f2901a = view;
            this.f2902b = str;
            this.f2903c = sVar;
            this.f2904d = m0Var;
            this.f2905e = mVar;
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

    private static b.d.a<Animator, d> A() {
        b.d.a<Animator, d> aVar = f2892d.get();
        if (aVar != null) {
            return aVar;
        }
        b.d.a<Animator, d> aVar2 = new b.d.a<>();
        f2892d.set(aVar2);
        return aVar2;
    }

    private static boolean L(s sVar, s sVar2, String str) {
        Object obj = sVar.f2919a.get(str);
        Object obj2 = sVar2.f2919a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    private void M(b.d.a<View, s> aVar, b.d.a<View, s> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View viewValueAt = sparseArray.valueAt(i2);
            if (viewValueAt != null && K(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i2))) != null && K(view)) {
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

    private void N(b.d.a<View, s> aVar, b.d.a<View, s> aVar2) {
        s sVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewI = aVar.i(size);
            if (viewI != null && K(viewI) && (sVarRemove = aVar2.remove(viewI)) != null && K(sVarRemove.f2920b)) {
                this.x.add(aVar.k(size));
                this.y.add(sVarRemove);
            }
        }
    }

    private void O(b.d.a<View, s> aVar, b.d.a<View, s> aVar2, b.d.d<View> dVar, b.d.d<View> dVar2) {
        View viewG;
        int iO = dVar.o();
        for (int i2 = 0; i2 < iO; i2++) {
            View viewP = dVar.p(i2);
            if (viewP != null && K(viewP) && (viewG = dVar2.g(dVar.k(i2))) != null && K(viewG)) {
                s sVar = aVar.get(viewP);
                s sVar2 = aVar2.get(viewG);
                if (sVar != null && sVar2 != null) {
                    this.x.add(sVar);
                    this.y.add(sVar2);
                    aVar.remove(viewP);
                    aVar2.remove(viewG);
                }
            }
        }
    }

    private void P(b.d.a<View, s> aVar, b.d.a<View, s> aVar2, b.d.a<String, View> aVar3, b.d.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View viewM = aVar3.m(i2);
            if (viewM != null && K(viewM) && (view = aVar4.get(aVar3.i(i2))) != null && K(view)) {
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

    private void Q(t tVar, t tVar2) {
        b.d.a<View, s> aVar = new b.d.a<>(tVar.f2922a);
        b.d.a<View, s> aVar2 = new b.d.a<>(tVar2.f2922a);
        int i2 = 0;
        while (true) {
            int[] iArr = this.w;
            if (i2 >= iArr.length) {
                e(aVar, aVar2);
                return;
            }
            int i3 = iArr[i2];
            if (i3 == 1) {
                N(aVar, aVar2);
            } else if (i3 == 2) {
                P(aVar, aVar2, tVar.f2925d, tVar2.f2925d);
            } else if (i3 == 3) {
                M(aVar, aVar2, tVar.f2923b, tVar2.f2923b);
            } else if (i3 == 4) {
                O(aVar, aVar2, tVar.f2924c, tVar2.f2924c);
            }
            i2++;
        }
    }

    private void W(Animator animator, b.d.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            g(animator);
        }
    }

    private void e(b.d.a<View, s> aVar, b.d.a<View, s> aVar2) {
        for (int i2 = 0; i2 < aVar.size(); i2++) {
            s sVarM = aVar.m(i2);
            if (K(sVarM.f2920b)) {
                this.x.add(sVarM);
                this.y.add(null);
            }
        }
        for (int i3 = 0; i3 < aVar2.size(); i3++) {
            s sVarM2 = aVar2.m(i3);
            if (K(sVarM2.f2920b)) {
                this.y.add(sVarM2);
                this.x.add(null);
            }
        }
    }

    private static void f(t tVar, View view, s sVar) {
        tVar.f2922a.put(view, sVar);
        int id = view.getId();
        if (id >= 0) {
            if (tVar.f2923b.indexOfKey(id) >= 0) {
                tVar.f2923b.put(id, null);
            } else {
                tVar.f2923b.put(id, view);
            }
        }
        String strH = b.g.k.t.H(view);
        if (strH != null) {
            if (tVar.f2925d.containsKey(strH)) {
                tVar.f2925d.put(strH, null);
            } else {
                tVar.f2925d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (tVar.f2924c.i(itemIdAtPosition) < 0) {
                    b.g.k.t.s0(view, true);
                    tVar.f2924c.l(itemIdAtPosition, view);
                    return;
                }
                View viewG = tVar.f2924c.g(itemIdAtPosition);
                if (viewG != null) {
                    b.g.k.t.s0(viewG, false);
                    tVar.f2924c.l(itemIdAtPosition, null);
                }
            }
        }
    }

    private void i(View view, boolean z) {
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
                    for (int i2 = 0; i2 < size; i2++) {
                        if (this.o.get(i2).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    s sVar = new s(view);
                    if (z) {
                        l(sVar);
                    } else {
                        h(sVar);
                    }
                    sVar.f2921c.add(this);
                    k(sVar);
                    if (z) {
                        f(this.t, view, sVar);
                    } else {
                        f(this.u, view, sVar);
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
                                for (int i3 = 0; i3 < size2; i3++) {
                                    if (this.s.get(i3).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                                i(viewGroup.getChildAt(i4), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public long B() {
        return this.f2894f;
    }

    public List<Integer> D() {
        return this.f2897i;
    }

    public List<String> E() {
        return this.k;
    }

    public List<Class<?>> F() {
        return this.l;
    }

    public List<View> G() {
        return this.j;
    }

    public String[] H() {
        return null;
    }

    public s I(View view, boolean z) {
        q qVar = this.v;
        if (qVar != null) {
            return qVar.I(view, z);
        }
        return (z ? this.t : this.u).f2922a.get(view);
    }

    public boolean J(s sVar, s sVar2) {
        if (sVar == null || sVar2 == null) {
            return false;
        }
        String[] strArrH = H();
        if (strArrH == null) {
            Iterator<String> it = sVar.f2919a.keySet().iterator();
            while (it.hasNext()) {
                if (L(sVar, sVar2, it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrH) {
            if (!L(sVar, sVar2, str)) {
            }
        }
        return false;
        return true;
    }

    boolean K(View view) {
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
            for (int i2 = 0; i2 < size; i2++) {
                if (this.o.get(i2).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.p != null && b.g.k.t.H(view) != null && this.p.contains(b.g.k.t.H(view))) {
            return false;
        }
        if ((this.f2897i.size() == 0 && this.j.size() == 0 && (((arrayList = this.l) == null || arrayList.isEmpty()) && ((arrayList2 = this.k) == null || arrayList2.isEmpty()))) || this.f2897i.contains(Integer.valueOf(id)) || this.j.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.k;
        if (arrayList6 != null && arrayList6.contains(b.g.k.t.H(view))) {
            return true;
        }
        if (this.l != null) {
            for (int i3 = 0; i3 < this.l.size(); i3++) {
                if (this.l.get(i3).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void R(View view) {
        if (this.E) {
            return;
        }
        b.d.a<Animator, d> aVarA = A();
        int size = aVarA.size();
        m0 m0VarD = c0.d(view);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            d dVarM = aVarA.m(i2);
            if (dVarM.f2901a != null && m0VarD.equals(dVarM.f2904d)) {
                b.o.a.b(aVarA.i(i2));
            }
        }
        ArrayList<f> arrayList = this.F;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.F.clone();
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((f) arrayList2.get(i3)).a(this);
            }
        }
        this.D = true;
    }

    void S(ViewGroup viewGroup) {
        d dVar;
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        Q(this.t, this.u);
        b.d.a<Animator, d> aVarA = A();
        int size = aVarA.size();
        m0 m0VarD = c0.d(viewGroup);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animatorI = aVarA.i(i2);
            if (animatorI != null && (dVar = aVarA.get(animatorI)) != null && dVar.f2901a != null && m0VarD.equals(dVar.f2904d)) {
                s sVar = dVar.f2903c;
                View view = dVar.f2901a;
                s sVarI = I(view, true);
                s sVarV = v(view, true);
                if (sVarI == null && sVarV == null) {
                    sVarV = this.u.f2922a.get(view);
                }
                if (!(sVarI == null && sVarV == null) && dVar.f2905e.J(sVar, sVarV)) {
                    if (animatorI.isRunning() || animatorI.isStarted()) {
                        animatorI.cancel();
                    } else {
                        aVarA.remove(animatorI);
                    }
                }
            }
        }
        q(viewGroup, this.t, this.u, this.x, this.y);
        X();
    }

    public m T(f fVar) {
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

    public m U(View view) {
        this.j.remove(view);
        return this;
    }

    public void V(View view) {
        if (this.D) {
            if (!this.E) {
                b.d.a<Animator, d> aVarA = A();
                int size = aVarA.size();
                m0 m0VarD = c0.d(view);
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    d dVarM = aVarA.m(i2);
                    if (dVarM.f2901a != null && m0VarD.equals(dVarM.f2904d)) {
                        b.o.a.c(aVarA.i(i2));
                    }
                }
                ArrayList<f> arrayList = this.F;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.F.clone();
                    int size2 = arrayList2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((f) arrayList2.get(i3)).b(this);
                    }
                }
            }
            this.D = false;
        }
    }

    protected void X() {
        e0();
        b.d.a<Animator, d> aVarA = A();
        for (Animator animator : this.G) {
            if (aVarA.containsKey(animator)) {
                e0();
                W(animator, aVarA);
            }
        }
        this.G.clear();
        r();
    }

    public m Y(long j) {
        this.f2895g = j;
        return this;
    }

    public void Z(e eVar) {
        this.I = eVar;
    }

    public m a0(TimeInterpolator timeInterpolator) {
        this.f2896h = timeInterpolator;
        return this;
    }

    public m b(f fVar) {
        if (this.F == null) {
            this.F = new ArrayList<>();
        }
        this.F.add(fVar);
        return this;
    }

    public void b0(g gVar) {
        if (gVar == null) {
            this.K = f2891c;
        } else {
            this.K = gVar;
        }
    }

    public m c(View view) {
        this.j.add(view);
        return this;
    }

    public void c0(p pVar) {
    }

    protected void cancel() {
        for (int size = this.B.size() - 1; size >= 0; size--) {
            this.B.get(size).cancel();
        }
        ArrayList<f> arrayList = this.F;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.F.clone();
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((f) arrayList2.get(i2)).d(this);
        }
    }

    public m d0(long j) {
        this.f2894f = j;
        return this;
    }

    protected void e0() {
        if (this.C == 0) {
            ArrayList<f> arrayList = this.F;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.F.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((f) arrayList2.get(i2)).c(this);
                }
            }
            this.E = false;
        }
        this.C++;
    }

    String f0(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f2895g != -1) {
            str2 = str2 + "dur(" + this.f2895g + ") ";
        }
        if (this.f2894f != -1) {
            str2 = str2 + "dly(" + this.f2894f + ") ";
        }
        if (this.f2896h != null) {
            str2 = str2 + "interp(" + this.f2896h + ") ";
        }
        if (this.f2897i.size() <= 0 && this.j.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f2897i.size() > 0) {
            for (int i2 = 0; i2 < this.f2897i.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f2897i.get(i2);
            }
        }
        if (this.j.size() > 0) {
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                if (i3 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.j.get(i3);
            }
        }
        return str3 + ")";
    }

    protected void g(Animator animator) {
        if (animator == null) {
            r();
            return;
        }
        if (s() >= 0) {
            animator.setDuration(s());
        }
        if (B() >= 0) {
            animator.setStartDelay(B() + animator.getStartDelay());
        }
        if (u() != null) {
            animator.setInterpolator(u());
        }
        animator.addListener(new c());
        animator.start();
    }

    public abstract void h(s sVar);

    void k(s sVar) {
        if (this.H != null && !sVar.f2919a.isEmpty()) {
            throw null;
        }
    }

    public abstract void l(s sVar);

    void m(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        b.d.a<String, String> aVar;
        n(z);
        if ((this.f2897i.size() > 0 || this.j.size() > 0) && (((arrayList = this.k) == null || arrayList.isEmpty()) && ((arrayList2 = this.l) == null || arrayList2.isEmpty()))) {
            for (int i2 = 0; i2 < this.f2897i.size(); i2++) {
                View viewFindViewById = viewGroup.findViewById(this.f2897i.get(i2).intValue());
                if (viewFindViewById != null) {
                    s sVar = new s(viewFindViewById);
                    if (z) {
                        l(sVar);
                    } else {
                        h(sVar);
                    }
                    sVar.f2921c.add(this);
                    k(sVar);
                    if (z) {
                        f(this.t, viewFindViewById, sVar);
                    } else {
                        f(this.u, viewFindViewById, sVar);
                    }
                }
            }
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                View view = this.j.get(i3);
                s sVar2 = new s(view);
                if (z) {
                    l(sVar2);
                } else {
                    h(sVar2);
                }
                sVar2.f2921c.add(this);
                k(sVar2);
                if (z) {
                    f(this.t, view, sVar2);
                } else {
                    f(this.u, view, sVar2);
                }
            }
        } else {
            i(viewGroup, z);
        }
        if (z || (aVar = this.J) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i4 = 0; i4 < size; i4++) {
            arrayList3.add(this.t.f2925d.remove(this.J.i(i4)));
        }
        for (int i5 = 0; i5 < size; i5++) {
            View view2 = (View) arrayList3.get(i5);
            if (view2 != null) {
                this.t.f2925d.put(this.J.m(i5), view2);
            }
        }
    }

    void n(boolean z) {
        if (z) {
            this.t.f2922a.clear();
            this.t.f2923b.clear();
            this.t.f2924c.c();
        } else {
            this.u.f2922a.clear();
            this.u.f2923b.clear();
            this.u.f2924c.c();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
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

    public Animator p(ViewGroup viewGroup, s sVar, s sVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void q(android.view.ViewGroup r20, b.o.t r21, b.o.t r22, java.util.ArrayList<b.o.s> r23, java.util.ArrayList<b.o.s> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.o.m.q(android.view.ViewGroup, b.o.t, b.o.t, java.util.ArrayList, java.util.ArrayList):void");
    }

    protected void r() {
        int i2 = this.C - 1;
        this.C = i2;
        if (i2 == 0) {
            ArrayList<f> arrayList = this.F;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.F.clone();
                int size = arrayList2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    ((f) arrayList2.get(i3)).e(this);
                }
            }
            for (int i4 = 0; i4 < this.t.f2924c.o(); i4++) {
                View viewP = this.t.f2924c.p(i4);
                if (viewP != null) {
                    b.g.k.t.s0(viewP, false);
                }
            }
            for (int i5 = 0; i5 < this.u.f2924c.o(); i5++) {
                View viewP2 = this.u.f2924c.p(i5);
                if (viewP2 != null) {
                    b.g.k.t.s0(viewP2, false);
                }
            }
            this.E = true;
        }
    }

    public long s() {
        return this.f2895g;
    }

    public e t() {
        return this.I;
    }

    public String toString() {
        return f0("");
    }

    public TimeInterpolator u() {
        return this.f2896h;
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
    b.o.s v(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            b.o.q r0 = r6.v
            if (r0 == 0) goto L9
            b.o.s r7 = r0.v(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<b.o.s> r0 = r6.x
            goto L10
        Le:
            java.util.ArrayList<b.o.s> r0 = r6.y
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
            b.o.s r5 = (b.o.s) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f2920b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<b.o.s> r7 = r6.y
            goto L37
        L35:
            java.util.ArrayList<b.o.s> r7 = r6.x
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            b.o.s r1 = (b.o.s) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.o.m.v(android.view.View, boolean):b.o.s");
    }

    public String w() {
        return this.f2893e;
    }

    public g x() {
        return this.K;
    }

    public p z() {
        return this.H;
    }
}
