package k1;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f5778d = {2, 1, 3, 4};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g f5779e = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static ThreadLocal<v.a<Animator, d>> f5780f = new ThreadLocal<>();
    public ArrayList<r> A;
    public o J;
    public e K;
    public v.a<String, String> L;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ArrayList<r> f5800z;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5781g = getClass().getName();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f5782h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f5783i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public TimeInterpolator f5784j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<Integer> f5785k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<View> f5786l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList<String> f5787m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList<Class<?>> f5788n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<Integer> f5789o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<View> f5790p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList<Class<?>> f5791q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<String> f5792r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<Integer> f5793s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ArrayList<View> f5794t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList<Class<?>> f5795u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public s f5796v = new s();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public s f5797w = new s();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public p f5798x = null;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int[] f5799y = f5778d;
    public ViewGroup B = null;
    public boolean C = false;
    public ArrayList<Animator> D = new ArrayList<>();
    public int E = 0;
    public boolean F = false;
    public boolean G = false;
    public ArrayList<f> H = null;
    public ArrayList<Animator> I = new ArrayList<>();
    public g M = f5779e;

    /* JADX INFO: compiled from: Transition.java */
    public static class a extends g {
        @Override // k1.g
        public Path a(float f8, float f9, float f10, float f11) {
            Path path = new Path();
            path.moveTo(f8, f9);
            path.lineTo(f10, f11);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ v.a f5801d;

        public b(v.a aVar) {
            this.f5801d = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5801d.remove(animator);
            l.this.D.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            l.this.D.add(animator);
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
        public View f5804a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5805b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public r f5806c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public l0 f5807d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public l f5808e;

        public d(View view, String str, l lVar, l0 l0Var, r rVar) {
            this.f5804a = view;
            this.f5805b = str;
            this.f5806c = rVar;
            this.f5807d = l0Var;
            this.f5808e = lVar;
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
        Object obj = rVar.f5822a.get(str);
        Object obj2 = rVar2.f5822a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public static void d(s sVar, View view, r rVar) {
        sVar.f5825a.put(view, rVar);
        int id = view.getId();
        if (id >= 0) {
            if (sVar.f5826b.indexOfKey(id) >= 0) {
                sVar.f5826b.put(id, null);
            } else {
                sVar.f5826b.put(id, view);
            }
        }
        String strI = o0.t.I(view);
        if (strI != null) {
            if (sVar.f5828d.containsKey(strI)) {
                sVar.f5828d.put(strI, null);
            } else {
                sVar.f5828d.put(strI, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (sVar.f5827c.h(itemIdAtPosition) < 0) {
                    o0.t.u0(view, true);
                    sVar.f5827c.k(itemIdAtPosition, view);
                    return;
                }
                View viewF = sVar.f5827c.f(itemIdAtPosition);
                if (viewF != null) {
                    o0.t.u0(viewF, false);
                    sVar.f5827c.k(itemIdAtPosition, null);
                }
            }
        }
    }

    public static v.a<Animator, d> x() {
        v.a<Animator, d> aVar = f5780f.get();
        if (aVar != null) {
            return aVar;
        }
        v.a<Animator, d> aVar2 = new v.a<>();
        f5780f.set(aVar2);
        return aVar2;
    }

    public List<String> A() {
        return this.f5787m;
    }

    public List<Class<?>> B() {
        return this.f5788n;
    }

    public List<View> C() {
        return this.f5786l;
    }

    public String[] D() {
        return null;
    }

    public r E(View view, boolean z7) {
        p pVar = this.f5798x;
        if (pVar != null) {
            return pVar.E(view, z7);
        }
        return (z7 ? this.f5796v : this.f5797w).f5825a.get(view);
    }

    public boolean F(r rVar, r rVar2) {
        if (rVar == null || rVar2 == null) {
            return false;
        }
        String[] strArrD = D();
        if (strArrD == null) {
            Iterator<String> it = rVar.f5822a.keySet().iterator();
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
        ArrayList<Integer> arrayList3 = this.f5789o;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.f5790p;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.f5791q;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i8 = 0; i8 < size; i8++) {
                if (this.f5791q.get(i8).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.f5792r != null && o0.t.I(view) != null && this.f5792r.contains(o0.t.I(view))) {
            return false;
        }
        if ((this.f5785k.size() == 0 && this.f5786l.size() == 0 && (((arrayList = this.f5788n) == null || arrayList.isEmpty()) && ((arrayList2 = this.f5787m) == null || arrayList2.isEmpty()))) || this.f5785k.contains(Integer.valueOf(id)) || this.f5786l.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.f5787m;
        if (arrayList6 != null && arrayList6.contains(o0.t.I(view))) {
            return true;
        }
        if (this.f5788n != null) {
            for (int i9 = 0; i9 < this.f5788n.size(); i9++) {
                if (this.f5788n.get(i9).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void I(v.a<View, r> aVar, v.a<View, r> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i8 = 0; i8 < size; i8++) {
            View viewValueAt = sparseArray.valueAt(i8);
            if (viewValueAt != null && G(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i8))) != null && G(view)) {
                r rVar = aVar.get(viewValueAt);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.f5800z.add(rVar);
                    this.A.add(rVar2);
                    aVar.remove(viewValueAt);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void J(v.a<View, r> aVar, v.a<View, r> aVar2) {
        r rVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewI = aVar.i(size);
            if (viewI != null && G(viewI) && (rVarRemove = aVar2.remove(viewI)) != null && G(rVarRemove.f5823b)) {
                this.f5800z.add(aVar.k(size));
                this.A.add(rVarRemove);
            }
        }
    }

    public final void K(v.a<View, r> aVar, v.a<View, r> aVar2, v.d<View> dVar, v.d<View> dVar2) {
        View viewF;
        int iN = dVar.n();
        for (int i8 = 0; i8 < iN; i8++) {
            View viewO = dVar.o(i8);
            if (viewO != null && G(viewO) && (viewF = dVar2.f(dVar.j(i8))) != null && G(viewF)) {
                r rVar = aVar.get(viewO);
                r rVar2 = aVar2.get(viewF);
                if (rVar != null && rVar2 != null) {
                    this.f5800z.add(rVar);
                    this.A.add(rVar2);
                    aVar.remove(viewO);
                    aVar2.remove(viewF);
                }
            }
        }
    }

    public final void L(v.a<View, r> aVar, v.a<View, r> aVar2, v.a<String, View> aVar3, v.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i8 = 0; i8 < size; i8++) {
            View viewM = aVar3.m(i8);
            if (viewM != null && G(viewM) && (view = aVar4.get(aVar3.i(i8))) != null && G(view)) {
                r rVar = aVar.get(viewM);
                r rVar2 = aVar2.get(view);
                if (rVar != null && rVar2 != null) {
                    this.f5800z.add(rVar);
                    this.A.add(rVar2);
                    aVar.remove(viewM);
                    aVar2.remove(view);
                }
            }
        }
    }

    public final void M(s sVar, s sVar2) {
        v.a<View, r> aVar = new v.a<>(sVar.f5825a);
        v.a<View, r> aVar2 = new v.a<>(sVar2.f5825a);
        int i8 = 0;
        while (true) {
            int[] iArr = this.f5799y;
            if (i8 >= iArr.length) {
                c(aVar, aVar2);
                return;
            }
            int i9 = iArr[i8];
            if (i9 == 1) {
                J(aVar, aVar2);
            } else if (i9 == 2) {
                L(aVar, aVar2, sVar.f5828d, sVar2.f5828d);
            } else if (i9 == 3) {
                I(aVar, aVar2, sVar.f5826b, sVar2.f5826b);
            } else if (i9 == 4) {
                K(aVar, aVar2, sVar.f5827c, sVar2.f5827c);
            }
            i8++;
        }
    }

    public void N(View view) {
        if (this.G) {
            return;
        }
        v.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        l0 l0VarD = b0.d(view);
        for (int i8 = size - 1; i8 >= 0; i8--) {
            d dVarM = aVarX.m(i8);
            if (dVarM.f5804a != null && l0VarD.equals(dVarM.f5807d)) {
                k1.a.b(aVarX.i(i8));
            }
        }
        ArrayList<f> arrayList = this.H;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.H.clone();
            int size2 = arrayList2.size();
            for (int i9 = 0; i9 < size2; i9++) {
                ((f) arrayList2.get(i9)).c(this);
            }
        }
        this.F = true;
    }

    public void O(ViewGroup viewGroup) {
        d dVar;
        this.f5800z = new ArrayList<>();
        this.A = new ArrayList<>();
        M(this.f5796v, this.f5797w);
        v.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        l0 l0VarD = b0.d(viewGroup);
        for (int i8 = size - 1; i8 >= 0; i8--) {
            Animator animatorI = aVarX.i(i8);
            if (animatorI != null && (dVar = aVarX.get(animatorI)) != null && dVar.f5804a != null && l0VarD.equals(dVar.f5807d)) {
                r rVar = dVar.f5806c;
                View view = dVar.f5804a;
                r rVarE = E(view, true);
                r rVarT = t(view, true);
                if (rVarE == null && rVarT == null) {
                    rVarT = this.f5797w.f5825a.get(view);
                }
                if (!(rVarE == null && rVarT == null) && dVar.f5808e.F(rVar, rVarT)) {
                    if (animatorI.isRunning() || animatorI.isStarted()) {
                        animatorI.cancel();
                    } else {
                        aVarX.remove(animatorI);
                    }
                }
            }
        }
        o(viewGroup, this.f5796v, this.f5797w, this.f5800z, this.A);
        T();
    }

    public l P(f fVar) {
        ArrayList<f> arrayList = this.H;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(fVar);
        if (this.H.size() == 0) {
            this.H = null;
        }
        return this;
    }

    public l Q(View view) {
        this.f5786l.remove(view);
        return this;
    }

    public void R(View view) {
        if (this.F) {
            if (!this.G) {
                v.a<Animator, d> aVarX = x();
                int size = aVarX.size();
                l0 l0VarD = b0.d(view);
                for (int i8 = size - 1; i8 >= 0; i8--) {
                    d dVarM = aVarX.m(i8);
                    if (dVarM.f5804a != null && l0VarD.equals(dVarM.f5807d)) {
                        k1.a.c(aVarX.i(i8));
                    }
                }
                ArrayList<f> arrayList = this.H;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.H.clone();
                    int size2 = arrayList2.size();
                    for (int i9 = 0; i9 < size2; i9++) {
                        ((f) arrayList2.get(i9)).d(this);
                    }
                }
            }
            this.F = false;
        }
    }

    public final void S(Animator animator, v.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            e(animator);
        }
    }

    public void T() {
        a0();
        v.a<Animator, d> aVarX = x();
        for (Animator animator : this.I) {
            if (aVarX.containsKey(animator)) {
                a0();
                S(animator, aVarX);
            }
        }
        this.I.clear();
        p();
    }

    public l U(long j8) {
        this.f5783i = j8;
        return this;
    }

    public void V(e eVar) {
        this.K = eVar;
    }

    public l W(TimeInterpolator timeInterpolator) {
        this.f5784j = timeInterpolator;
        return this;
    }

    public void X(g gVar) {
        if (gVar == null) {
            this.M = f5779e;
        } else {
            this.M = gVar;
        }
    }

    public void Y(o oVar) {
    }

    public l Z(long j8) {
        this.f5782h = j8;
        return this;
    }

    public l a(f fVar) {
        if (this.H == null) {
            this.H = new ArrayList<>();
        }
        this.H.add(fVar);
        return this;
    }

    public void a0() {
        if (this.E == 0) {
            ArrayList<f> arrayList = this.H;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.H.clone();
                int size = arrayList2.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((f) arrayList2.get(i8)).a(this);
                }
            }
            this.G = false;
        }
        this.E++;
    }

    public l b(View view) {
        this.f5786l.add(view);
        return this;
    }

    public String b0(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f5783i != -1) {
            str2 = str2 + "dur(" + this.f5783i + ") ";
        }
        if (this.f5782h != -1) {
            str2 = str2 + "dly(" + this.f5782h + ") ";
        }
        if (this.f5784j != null) {
            str2 = str2 + "interp(" + this.f5784j + ") ";
        }
        if (this.f5785k.size() <= 0 && this.f5786l.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f5785k.size() > 0) {
            for (int i8 = 0; i8 < this.f5785k.size(); i8++) {
                if (i8 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f5785k.get(i8);
            }
        }
        if (this.f5786l.size() > 0) {
            for (int i9 = 0; i9 < this.f5786l.size(); i9++) {
                if (i9 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f5786l.get(i9);
            }
        }
        return str3 + ")";
    }

    public final void c(v.a<View, r> aVar, v.a<View, r> aVar2) {
        for (int i8 = 0; i8 < aVar.size(); i8++) {
            r rVarM = aVar.m(i8);
            if (G(rVarM.f5823b)) {
                this.f5800z.add(rVarM);
                this.A.add(null);
            }
        }
        for (int i9 = 0; i9 < aVar2.size(); i9++) {
            r rVarM2 = aVar2.m(i9);
            if (G(rVarM2.f5823b)) {
                this.A.add(rVarM2);
                this.f5800z.add(null);
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
        for (int size = this.D.size() - 1; size >= 0; size--) {
            this.D.get(size).cancel();
        }
        ArrayList<f> arrayList = this.H;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.H.clone();
        int size2 = arrayList2.size();
        for (int i8 = 0; i8 < size2; i8++) {
            ((f) arrayList2.get(i8)).b(this);
        }
    }

    public abstract void g(r rVar);

    public final void h(View view, boolean z7) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.f5789o;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.f5790p;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.f5791q;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i8 = 0; i8 < size; i8++) {
                        if (this.f5791q.get(i8).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    r rVar = new r(view);
                    if (z7) {
                        j(rVar);
                    } else {
                        g(rVar);
                    }
                    rVar.f5824c.add(this);
                    i(rVar);
                    if (z7) {
                        d(this.f5796v, view, rVar);
                    } else {
                        d(this.f5797w, view, rVar);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.f5793s;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.f5794t;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.f5795u;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i9 = 0; i9 < size2; i9++) {
                                    if (this.f5795u.get(i9).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i10 = 0; i10 < viewGroup.getChildCount(); i10++) {
                                h(viewGroup.getChildAt(i10), z7);
                            }
                        }
                    }
                }
            }
        }
    }

    public void i(r rVar) {
        if (this.J != null && !rVar.f5822a.isEmpty()) {
            throw null;
        }
    }

    public abstract void j(r rVar);

    public void k(ViewGroup viewGroup, boolean z7) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        v.a<String, String> aVar;
        l(z7);
        if ((this.f5785k.size() > 0 || this.f5786l.size() > 0) && (((arrayList = this.f5787m) == null || arrayList.isEmpty()) && ((arrayList2 = this.f5788n) == null || arrayList2.isEmpty()))) {
            for (int i8 = 0; i8 < this.f5785k.size(); i8++) {
                View viewFindViewById = viewGroup.findViewById(this.f5785k.get(i8).intValue());
                if (viewFindViewById != null) {
                    r rVar = new r(viewFindViewById);
                    if (z7) {
                        j(rVar);
                    } else {
                        g(rVar);
                    }
                    rVar.f5824c.add(this);
                    i(rVar);
                    if (z7) {
                        d(this.f5796v, viewFindViewById, rVar);
                    } else {
                        d(this.f5797w, viewFindViewById, rVar);
                    }
                }
            }
            for (int i9 = 0; i9 < this.f5786l.size(); i9++) {
                View view = this.f5786l.get(i9);
                r rVar2 = new r(view);
                if (z7) {
                    j(rVar2);
                } else {
                    g(rVar2);
                }
                rVar2.f5824c.add(this);
                i(rVar2);
                if (z7) {
                    d(this.f5796v, view, rVar2);
                } else {
                    d(this.f5797w, view, rVar2);
                }
            }
        } else {
            h(viewGroup, z7);
        }
        if (z7 || (aVar = this.L) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i10 = 0; i10 < size; i10++) {
            arrayList3.add(this.f5796v.f5828d.remove(this.L.i(i10)));
        }
        for (int i11 = 0; i11 < size; i11++) {
            View view2 = (View) arrayList3.get(i11);
            if (view2 != null) {
                this.f5796v.f5828d.put(this.L.m(i11), view2);
            }
        }
    }

    public void l(boolean z7) {
        if (z7) {
            this.f5796v.f5825a.clear();
            this.f5796v.f5826b.clear();
            this.f5796v.f5827c.b();
        } else {
            this.f5797w.f5825a.clear();
            this.f5797w.f5826b.clear();
            this.f5797w.f5827c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public l clone() {
        try {
            l lVar = (l) super.clone();
            lVar.I = new ArrayList<>();
            lVar.f5796v = new s();
            lVar.f5797w = new s();
            lVar.f5800z = null;
            lVar.A = null;
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
    public void o(android.view.ViewGroup r20, k1.s r21, k1.s r22, java.util.ArrayList<k1.r> r23, java.util.ArrayList<k1.r> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k1.l.o(android.view.ViewGroup, k1.s, k1.s, java.util.ArrayList, java.util.ArrayList):void");
    }

    public void p() {
        int i8 = this.E - 1;
        this.E = i8;
        if (i8 == 0) {
            ArrayList<f> arrayList = this.H;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.H.clone();
                int size = arrayList2.size();
                for (int i9 = 0; i9 < size; i9++) {
                    ((f) arrayList2.get(i9)).e(this);
                }
            }
            for (int i10 = 0; i10 < this.f5796v.f5827c.n(); i10++) {
                View viewO = this.f5796v.f5827c.o(i10);
                if (viewO != null) {
                    o0.t.u0(viewO, false);
                }
            }
            for (int i11 = 0; i11 < this.f5797w.f5827c.n(); i11++) {
                View viewO2 = this.f5797w.f5827c.o(i11);
                if (viewO2 != null) {
                    o0.t.u0(viewO2, false);
                }
            }
            this.G = true;
        }
    }

    public long q() {
        return this.f5783i;
    }

    public e r() {
        return this.K;
    }

    public TimeInterpolator s() {
        return this.f5784j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r7 = r6.A;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r7 = r6.f5800z;
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
    public k1.r t(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            k1.p r0 = r6.f5798x
            if (r0 == 0) goto L9
            k1.r r7 = r0.t(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<k1.r> r0 = r6.f5800z
            goto L10
        Le:
            java.util.ArrayList<k1.r> r0 = r6.A
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
            k1.r r5 = (k1.r) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f5823b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<k1.r> r7 = r6.A
            goto L37
        L35:
            java.util.ArrayList<k1.r> r7 = r6.f5800z
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            k1.r r1 = (k1.r) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: k1.l.t(android.view.View, boolean):k1.r");
    }

    public String toString() {
        return b0("");
    }

    public String u() {
        return this.f5781g;
    }

    public g v() {
        return this.M;
    }

    public o w() {
        return this.J;
    }

    public long y() {
        return this.f5782h;
    }

    public List<Integer> z() {
        return this.f5785k;
    }
}
