package y0;

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
    public static final int[] f5934b = {2, 1, 3, 4};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f5935c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ThreadLocal<s.a<Animator, d>> f5936d = new ThreadLocal<>();
    public o H;
    public e I;
    public s.a<String, String> J;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ArrayList<r> f5956x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ArrayList<r> f5957y;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5937e = getClass().getName();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5938f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f5939g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TimeInterpolator f5940h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<Integer> f5941i = new ArrayList<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList<View> f5942j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<String> f5943k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<Class<?>> f5944l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList<Integer> f5945m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList<View> f5946n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<Class<?>> f5947o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<String> f5948p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList<Integer> f5949q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<View> f5950r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<Class<?>> f5951s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public s f5952t = new s();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public s f5953u = new s();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public p f5954v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int[] f5955w = f5934b;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ViewGroup f5958z = null;
    public boolean A = false;
    public ArrayList<Animator> B = new ArrayList<>();
    public int C = 0;
    public boolean D = false;
    public boolean E = false;
    public ArrayList<f> F = null;
    public ArrayList<Animator> G = new ArrayList<>();
    public g K = f5935c;

    /* JADX INFO: compiled from: Transition.java */
    public static class a extends g {
        @Override // y0.g
        public Path a(float f3, float f4, float f5, float f6) {
            Path path = new Path();
            path.moveTo(f3, f4);
            path.lineTo(f5, f6);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s.a f5959a;

        public b(s.a aVar) {
            this.f5959a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5959a.remove(animator);
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
        public View f5962a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5963b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public r f5964c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public l0 f5965d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public l f5966e;

        public d(View view, String str, l lVar, l0 l0Var, r rVar) {
            this.f5962a = view;
            this.f5963b = str;
            this.f5964c = rVar;
            this.f5965d = l0Var;
            this.f5966e = lVar;
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
        Object obj = rVar.f5980a.get(str);
        Object obj2 = rVar2.f5980a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public static void d(s sVar, View view, r rVar) {
        sVar.f5983a.put(view, rVar);
        int id = view.getId();
        if (id >= 0) {
            if (sVar.f5984b.indexOfKey(id) >= 0) {
                sVar.f5984b.put(id, null);
            } else {
                sVar.f5984b.put(id, view);
            }
        }
        String strH = l0.t.H(view);
        if (strH != null) {
            if (sVar.f5986d.containsKey(strH)) {
                sVar.f5986d.put(strH, null);
            } else {
                sVar.f5986d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (sVar.f5985c.g(itemIdAtPosition) < 0) {
                    l0.t.s0(view, true);
                    sVar.f5985c.i(itemIdAtPosition, view);
                    return;
                }
                View viewE = sVar.f5985c.e(itemIdAtPosition);
                if (viewE != null) {
                    l0.t.s0(viewE, false);
                    sVar.f5985c.i(itemIdAtPosition, null);
                }
            }
        }
    }

    public static s.a<Animator, d> x() {
        s.a<Animator, d> aVar = f5936d.get();
        if (aVar != null) {
            return aVar;
        }
        s.a<Animator, d> aVar2 = new s.a<>();
        f5936d.set(aVar2);
        return aVar2;
    }

    public List<String> A() {
        return this.f5943k;
    }

    public List<Class<?>> B() {
        return this.f5944l;
    }

    public List<View> C() {
        return this.f5942j;
    }

    public String[] D() {
        return null;
    }

    public r E(View view, boolean z3) {
        p pVar = this.f5954v;
        if (pVar != null) {
            return pVar.E(view, z3);
        }
        return (z3 ? this.f5952t : this.f5953u).f5983a.get(view);
    }

    public boolean F(r rVar, r rVar2) {
        if (rVar == null || rVar2 == null) {
            return false;
        }
        String[] strArrD = D();
        if (strArrD == null) {
            Iterator<String> it = rVar.f5980a.keySet().iterator();
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
        ArrayList<Integer> arrayList3 = this.f5945m;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.f5946n;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.f5947o;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (this.f5947o.get(i3).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.f5948p != null && l0.t.H(view) != null && this.f5948p.contains(l0.t.H(view))) {
            return false;
        }
        if ((this.f5941i.size() == 0 && this.f5942j.size() == 0 && (((arrayList = this.f5944l) == null || arrayList.isEmpty()) && ((arrayList2 = this.f5943k) == null || arrayList2.isEmpty()))) || this.f5941i.contains(Integer.valueOf(id)) || this.f5942j.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.f5943k;
        if (arrayList6 != null && arrayList6.contains(l0.t.H(view))) {
            return true;
        }
        if (this.f5944l != null) {
            for (int i4 = 0; i4 < this.f5944l.size(); i4++) {
                if (this.f5944l.get(i4).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void I(s.a<View, r> aVar, s.a<View, r> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i3 = 0; i3 < size; i3++) {
            View viewValueAt = sparseArray.valueAt(i3);
            if (viewValueAt != null && G(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i3))) != null && G(view)) {
                r rVar = aVar.get(viewValueAt);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.f5956x.add(rVar);
                    this.f5957y.add(rVar2);
                    aVar.remove(viewValueAt);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void J(s.a<View, r> aVar, s.a<View, r> aVar2) {
        r rVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewI = aVar.i(size);
            if (viewI != null && G(viewI) && (rVarRemove = aVar2.remove(viewI)) != null && G(rVarRemove.f5981b)) {
                this.f5956x.add(aVar.k(size));
                this.f5957y.add(rVarRemove);
            }
        }
    }

    public final void K(s.a<View, r> aVar, s.a<View, r> aVar2, s.d<View> dVar, s.d<View> dVar2) {
        View viewE;
        int iL = dVar.l();
        for (int i3 = 0; i3 < iL; i3++) {
            View viewM = dVar.m(i3);
            if (viewM != null && G(viewM) && (viewE = dVar2.e(dVar.h(i3))) != null && G(viewE)) {
                r rVar = aVar.get(viewM);
                r rVar2 = aVar2.get(viewE);
                if (rVar != null && rVar2 != null) {
                    this.f5956x.add(rVar);
                    this.f5957y.add(rVar2);
                    aVar.remove(viewM);
                    aVar2.remove(viewE);
                }
            }
        }
    }

    public final void L(s.a<View, r> aVar, s.a<View, r> aVar2, s.a<String, View> aVar3, s.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i3 = 0; i3 < size; i3++) {
            View viewM = aVar3.m(i3);
            if (viewM != null && G(viewM) && (view = aVar4.get(aVar3.i(i3))) != null && G(view)) {
                r rVar = aVar.get(viewM);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.f5956x.add(rVar);
                    this.f5957y.add(rVar2);
                    aVar.remove(viewM);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void M(s sVar, s sVar2) {
        s.a<View, r> aVar = new s.a<>(sVar.f5983a);
        s.a<View, r> aVar2 = new s.a<>(sVar2.f5983a);
        int i3 = 0;
        while (true) {
            int[] iArr = this.f5955w;
            if (i3 >= iArr.length) {
                c(aVar, aVar2);
                return;
            }
            int i4 = iArr[i3];
            if (i4 == 1) {
                J(aVar, aVar2);
            } else if (i4 == 2) {
                L(aVar, aVar2, sVar.f5986d, sVar2.f5986d);
            } else if (i4 == 3) {
                I(aVar, aVar2, sVar.f5984b, sVar2.f5984b);
            } else if (i4 == 4) {
                K(aVar, aVar2, sVar.f5985c, sVar2.f5985c);
            }
            i3++;
        }
    }

    public void N(View view) {
        if (this.E) {
            return;
        }
        s.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        l0 l0VarD = b0.d(view);
        for (int i3 = size - 1; i3 >= 0; i3--) {
            d dVarM = aVarX.m(i3);
            if (dVarM.f5962a != null && l0VarD.equals(dVarM.f5965d)) {
                y0.a.b(aVarX.i(i3));
            }
        }
        ArrayList<f> arrayList = this.F;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.F.clone();
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                ((f) arrayList2.get(i4)).a(this);
            }
        }
        this.D = true;
    }

    public void O(ViewGroup viewGroup) {
        d dVar;
        this.f5956x = new ArrayList<>();
        this.f5957y = new ArrayList<>();
        M(this.f5952t, this.f5953u);
        s.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        l0 l0VarD = b0.d(viewGroup);
        for (int i3 = size - 1; i3 >= 0; i3--) {
            Animator animatorI = aVarX.i(i3);
            if (animatorI != null && (dVar = aVarX.get(animatorI)) != null && dVar.f5962a != null && l0VarD.equals(dVar.f5965d)) {
                r rVar = dVar.f5964c;
                View view = dVar.f5962a;
                r rVarE = E(view, true);
                r rVarT = t(view, true);
                if (rVarE == null && rVarT == null) {
                    rVarT = this.f5953u.f5983a.get(view);
                }
                if (!(rVarE == null && rVarT == null) && dVar.f5966e.F(rVar, rVarT)) {
                    if (animatorI.isRunning() || animatorI.isStarted()) {
                        animatorI.cancel();
                    } else {
                        aVarX.remove(animatorI);
                    }
                }
            }
        }
        o(viewGroup, this.f5952t, this.f5953u, this.f5956x, this.f5957y);
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
        this.f5942j.remove(view);
        return this;
    }

    public void R(View view) {
        if (this.D) {
            if (!this.E) {
                s.a<Animator, d> aVarX = x();
                int size = aVarX.size();
                l0 l0VarD = b0.d(view);
                for (int i3 = size - 1; i3 >= 0; i3--) {
                    d dVarM = aVarX.m(i3);
                    if (dVarM.f5962a != null && l0VarD.equals(dVarM.f5965d)) {
                        y0.a.c(aVarX.i(i3));
                    }
                }
                ArrayList<f> arrayList = this.F;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.F.clone();
                    int size2 = arrayList2.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        ((f) arrayList2.get(i4)).b(this);
                    }
                }
            }
            this.D = false;
        }
    }

    public final void S(Animator animator, s.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            e(animator);
        }
    }

    public void T() {
        a0();
        s.a<Animator, d> aVarX = x();
        for (Animator animator : this.G) {
            if (aVarX.containsKey(animator)) {
                a0();
                S(animator, aVarX);
            }
        }
        this.G.clear();
        p();
    }

    public l U(long j3) {
        this.f5939g = j3;
        return this;
    }

    public void V(e eVar) {
        this.I = eVar;
    }

    public l W(TimeInterpolator timeInterpolator) {
        this.f5940h = timeInterpolator;
        return this;
    }

    public void X(g gVar) {
        if (gVar == null) {
            this.K = f5935c;
        } else {
            this.K = gVar;
        }
    }

    public void Y(o oVar) {
    }

    public l Z(long j3) {
        this.f5938f = j3;
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
                for (int i3 = 0; i3 < size; i3++) {
                    ((f) arrayList2.get(i3)).c(this);
                }
            }
            this.E = false;
        }
        this.C++;
    }

    public l b(View view) {
        this.f5942j.add(view);
        return this;
    }

    public String b0(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f5939g != -1) {
            str2 = str2 + "dur(" + this.f5939g + ") ";
        }
        if (this.f5938f != -1) {
            str2 = str2 + "dly(" + this.f5938f + ") ";
        }
        if (this.f5940h != null) {
            str2 = str2 + "interp(" + this.f5940h + ") ";
        }
        if (this.f5941i.size() <= 0 && this.f5942j.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f5941i.size() > 0) {
            for (int i3 = 0; i3 < this.f5941i.size(); i3++) {
                if (i3 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f5941i.get(i3);
            }
        }
        if (this.f5942j.size() > 0) {
            for (int i4 = 0; i4 < this.f5942j.size(); i4++) {
                if (i4 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f5942j.get(i4);
            }
        }
        return str3 + ")";
    }

    public final void c(s.a<View, r> aVar, s.a<View, r> aVar2) {
        for (int i3 = 0; i3 < aVar.size(); i3++) {
            r rVarM = aVar.m(i3);
            if (G(rVarM.f5981b)) {
                this.f5956x.add(rVarM);
                this.f5957y.add(null);
            }
        }
        for (int i4 = 0; i4 < aVar2.size(); i4++) {
            r rVarM2 = aVar2.m(i4);
            if (G(rVarM2.f5981b)) {
                this.f5957y.add(rVarM2);
                this.f5956x.add(null);
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
        for (int i3 = 0; i3 < size2; i3++) {
            ((f) arrayList2.get(i3)).d(this);
        }
    }

    public abstract void g(r rVar);

    public final void h(View view, boolean z3) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.f5945m;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.f5946n;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.f5947o;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        if (this.f5947o.get(i3).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    r rVar = new r(view);
                    if (z3) {
                        j(rVar);
                    } else {
                        g(rVar);
                    }
                    rVar.f5982c.add(this);
                    i(rVar);
                    if (z3) {
                        d(this.f5952t, view, rVar);
                    } else {
                        d(this.f5953u, view, rVar);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.f5949q;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.f5950r;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.f5951s;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i4 = 0; i4 < size2; i4++) {
                                    if (this.f5951s.get(i4).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                                h(viewGroup.getChildAt(i5), z3);
                            }
                        }
                    }
                }
            }
        }
    }

    public void i(r rVar) {
        if (this.H != null && !rVar.f5980a.isEmpty()) {
            throw null;
        }
    }

    public abstract void j(r rVar);

    public void k(ViewGroup viewGroup, boolean z3) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        s.a<String, String> aVar;
        l(z3);
        if ((this.f5941i.size() > 0 || this.f5942j.size() > 0) && (((arrayList = this.f5943k) == null || arrayList.isEmpty()) && ((arrayList2 = this.f5944l) == null || arrayList2.isEmpty()))) {
            for (int i3 = 0; i3 < this.f5941i.size(); i3++) {
                View viewFindViewById = viewGroup.findViewById(this.f5941i.get(i3).intValue());
                if (viewFindViewById != null) {
                    r rVar = new r(viewFindViewById);
                    if (z3) {
                        j(rVar);
                    } else {
                        g(rVar);
                    }
                    rVar.f5982c.add(this);
                    i(rVar);
                    if (z3) {
                        d(this.f5952t, viewFindViewById, rVar);
                    } else {
                        d(this.f5953u, viewFindViewById, rVar);
                    }
                }
            }
            for (int i4 = 0; i4 < this.f5942j.size(); i4++) {
                View view = this.f5942j.get(i4);
                r rVar2 = new r(view);
                if (z3) {
                    j(rVar2);
                } else {
                    g(rVar2);
                }
                rVar2.f5982c.add(this);
                i(rVar2);
                if (z3) {
                    d(this.f5952t, view, rVar2);
                } else {
                    d(this.f5953u, view, rVar2);
                }
            }
        } else {
            h(viewGroup, z3);
        }
        if (z3 || (aVar = this.J) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i5 = 0; i5 < size; i5++) {
            arrayList3.add(this.f5952t.f5986d.remove(this.J.i(i5)));
        }
        for (int i6 = 0; i6 < size; i6++) {
            View view2 = (View) arrayList3.get(i6);
            if (view2 != null) {
                this.f5952t.f5986d.put(this.J.m(i6), view2);
            }
        }
    }

    public void l(boolean z3) {
        if (z3) {
            this.f5952t.f5983a.clear();
            this.f5952t.f5984b.clear();
            this.f5952t.f5985c.b();
        } else {
            this.f5953u.f5983a.clear();
            this.f5953u.f5984b.clear();
            this.f5953u.f5985c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public l clone() {
        try {
            l lVar = (l) super.clone();
            lVar.G = new ArrayList<>();
            lVar.f5952t = new s();
            lVar.f5953u = new s();
            lVar.f5956x = null;
            lVar.f5957y = null;
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
    public void o(android.view.ViewGroup r20, y0.s r21, y0.s r22, java.util.ArrayList<y0.r> r23, java.util.ArrayList<y0.r> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.l.o(android.view.ViewGroup, y0.s, y0.s, java.util.ArrayList, java.util.ArrayList):void");
    }

    public void p() {
        int i3 = this.C - 1;
        this.C = i3;
        if (i3 == 0) {
            ArrayList<f> arrayList = this.F;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.F.clone();
                int size = arrayList2.size();
                for (int i4 = 0; i4 < size; i4++) {
                    ((f) arrayList2.get(i4)).e(this);
                }
            }
            for (int i5 = 0; i5 < this.f5952t.f5985c.l(); i5++) {
                View viewM = this.f5952t.f5985c.m(i5);
                if (viewM != null) {
                    l0.t.s0(viewM, false);
                }
            }
            for (int i6 = 0; i6 < this.f5953u.f5985c.l(); i6++) {
                View viewM2 = this.f5953u.f5985c.m(i6);
                if (viewM2 != null) {
                    l0.t.s0(viewM2, false);
                }
            }
            this.E = true;
        }
    }

    public long q() {
        return this.f5939g;
    }

    public e r() {
        return this.I;
    }

    public TimeInterpolator s() {
        return this.f5940h;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r7 = r6.f5957y;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r7 = r6.f5956x;
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
    public y0.r t(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            y0.p r0 = r6.f5954v
            if (r0 == 0) goto L9
            y0.r r7 = r0.t(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<y0.r> r0 = r6.f5956x
            goto L10
        Le:
            java.util.ArrayList<y0.r> r0 = r6.f5957y
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
            y0.r r5 = (y0.r) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f5981b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<y0.r> r7 = r6.f5957y
            goto L37
        L35:
            java.util.ArrayList<y0.r> r7 = r6.f5956x
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            y0.r r1 = (y0.r) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.l.t(android.view.View, boolean):y0.r");
    }

    public String toString() {
        return b0("");
    }

    public String u() {
        return this.f5937e;
    }

    public g v() {
        return this.K;
    }

    public o w() {
        return this.H;
    }

    public long y() {
        return this.f5938f;
    }

    public List<Integer> z() {
        return this.f5941i;
    }
}
