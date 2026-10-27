package b.x;

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
    public static final int[] f3286b = {2, 1, 3, 4};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f3287c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ThreadLocal<b.e.a<Animator, d>> f3288d = new ThreadLocal<>();
    public p H;
    public e I;
    public b.e.a<String, String> J;
    public ArrayList<s> x;
    public ArrayList<s> y;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3289e = getClass().getName();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f3290f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f3291g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TimeInterpolator f3292h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<Integer> f3293i = new ArrayList<>();
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
    public t t = new t();
    public t u = new t();
    public q v = null;
    public int[] w = f3286b;
    public ViewGroup z = null;
    public boolean A = false;
    public ArrayList<Animator> B = new ArrayList<>();
    public int C = 0;
    public boolean D = false;
    public boolean E = false;
    public ArrayList<f> F = null;
    public ArrayList<Animator> G = new ArrayList<>();
    public g K = f3287c;

    /* JADX INFO: compiled from: Transition.java */
    public static class a extends g {
        @Override // b.x.g
        public Path a(float f2, float f3, float f4, float f5) {
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f4, f5);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.e.a f3294a;

        public b(b.e.a aVar) {
            this.f3294a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3294a.remove(animator);
            m.this.B.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            m.this.B.add(animator);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            m.this.o();
            animator.removeListener(this);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f3297a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3298b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public s f3299c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public m0 f3300d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public m f3301e;

        public d(View view, String str, m mVar, m0 m0Var, s sVar) {
            this.f3297a = view;
            this.f3298b = str;
            this.f3299c = sVar;
            this.f3300d = m0Var;
            this.f3301e = mVar;
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

    public static boolean I(s sVar, s sVar2, String str) {
        Object obj = sVar.f3315a.get(str);
        Object obj2 = sVar2.f3315a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public static void d(t tVar, View view, s sVar) {
        tVar.f3318a.put(view, sVar);
        int id = view.getId();
        if (id >= 0) {
            if (tVar.f3319b.indexOfKey(id) >= 0) {
                tVar.f3319b.put(id, null);
            } else {
                tVar.f3319b.put(id, view);
            }
        }
        String strH = b.h.m.t.H(view);
        if (strH != null) {
            if (tVar.f3321d.containsKey(strH)) {
                tVar.f3321d.put(strH, null);
            } else {
                tVar.f3321d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (tVar.f3320c.g(itemIdAtPosition) < 0) {
                    b.h.m.t.t0(view, true);
                    tVar.f3320c.i(itemIdAtPosition, view);
                    return;
                }
                View viewE = tVar.f3320c.e(itemIdAtPosition);
                if (viewE != null) {
                    b.h.m.t.t0(viewE, false);
                    tVar.f3320c.i(itemIdAtPosition, null);
                }
            }
        }
    }

    public static b.e.a<Animator, d> x() {
        b.e.a<Animator, d> aVar = f3288d.get();
        if (aVar != null) {
            return aVar;
        }
        b.e.a<Animator, d> aVar2 = new b.e.a<>();
        f3288d.set(aVar2);
        return aVar2;
    }

    public List<Integer> A() {
        return this.f3293i;
    }

    public List<String> B() {
        return this.k;
    }

    public List<Class<?>> C() {
        return this.l;
    }

    public List<View> D() {
        return this.j;
    }

    public String[] E() {
        return null;
    }

    public s F(View view, boolean z) {
        q qVar = this.v;
        if (qVar != null) {
            return qVar.F(view, z);
        }
        return (z ? this.t : this.u).f3318a.get(view);
    }

    public boolean G(s sVar, s sVar2) {
        if (sVar == null || sVar2 == null) {
            return false;
        }
        String[] strArrE = E();
        if (strArrE == null) {
            Iterator<String> it = sVar.f3315a.keySet().iterator();
            while (it.hasNext()) {
                if (I(sVar, sVar2, it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrE) {
            if (!I(sVar, sVar2, str)) {
            }
        }
        return false;
        return true;
    }

    public boolean H(View view) {
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
        if (this.p != null && b.h.m.t.H(view) != null && this.p.contains(b.h.m.t.H(view))) {
            return false;
        }
        if ((this.f3293i.size() == 0 && this.j.size() == 0 && (((arrayList = this.l) == null || arrayList.isEmpty()) && ((arrayList2 = this.k) == null || arrayList2.isEmpty()))) || this.f3293i.contains(Integer.valueOf(id)) || this.j.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.k;
        if (arrayList6 != null && arrayList6.contains(b.h.m.t.H(view))) {
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

    public final void J(b.e.a<View, s> aVar, b.e.a<View, s> aVar2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View viewValueAt = sparseArray.valueAt(i2);
            if (viewValueAt != null && H(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i2))) != null && H(view)) {
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

    public final void K(b.e.a<View, s> aVar, b.e.a<View, s> aVar2) {
        s sVarRemove;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View viewI = aVar.i(size);
            if (viewI != null && H(viewI) && (sVarRemove = aVar2.remove(viewI)) != null && H(sVarRemove.f3316b)) {
                this.x.add(aVar.k(size));
                this.y.add(sVarRemove);
            }
        }
    }

    public final void L(b.e.a<View, s> aVar, b.e.a<View, s> aVar2, b.e.d<View> dVar, b.e.d<View> dVar2) {
        View viewE;
        int iL = dVar.l();
        for (int i2 = 0; i2 < iL; i2++) {
            View viewM = dVar.m(i2);
            if (viewM != null && H(viewM) && (viewE = dVar2.e(dVar.h(i2))) != null && H(viewE)) {
                s sVar = aVar.get(viewM);
                s sVar2 = aVar2.get(viewE);
                if (sVar != null && sVar2 != null) {
                    this.x.add(sVar);
                    this.y.add(sVar2);
                    aVar.remove(viewM);
                    aVar2.remove(viewE);
                }
            }
        }
    }

    public final void M(b.e.a<View, s> aVar, b.e.a<View, s> aVar2, b.e.a<String, View> aVar3, b.e.a<String, View> aVar4) {
        View view;
        int size = aVar3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View viewM = aVar3.m(i2);
            if (viewM != null && H(viewM) && (view = aVar4.get(aVar3.i(i2))) != null && H(view)) {
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

    public final void N(t tVar, t tVar2) {
        b.e.a<View, s> aVar = new b.e.a<>(tVar.f3318a);
        b.e.a<View, s> aVar2 = new b.e.a<>(tVar2.f3318a);
        int i2 = 0;
        while (true) {
            int[] iArr = this.w;
            if (i2 >= iArr.length) {
                c(aVar, aVar2);
                return;
            }
            int i3 = iArr[i2];
            if (i3 == 1) {
                K(aVar, aVar2);
            } else if (i3 == 2) {
                M(aVar, aVar2, tVar.f3321d, tVar2.f3321d);
            } else if (i3 == 3) {
                J(aVar, aVar2, tVar.f3319b, tVar2.f3319b);
            } else if (i3 == 4) {
                L(aVar, aVar2, tVar.f3320c, tVar2.f3320c);
            }
            i2++;
        }
    }

    public void O(View view) {
        if (this.E) {
            return;
        }
        b.e.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        m0 m0VarD = c0.d(view);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            d dVarM = aVarX.m(i2);
            if (dVarM.f3297a != null && m0VarD.equals(dVarM.f3300d)) {
                b.x.a.b(aVarX.i(i2));
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

    public void P(ViewGroup viewGroup) {
        d dVar;
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        N(this.t, this.u);
        b.e.a<Animator, d> aVarX = x();
        int size = aVarX.size();
        m0 m0VarD = c0.d(viewGroup);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animatorI = aVarX.i(i2);
            if (animatorI != null && (dVar = aVarX.get(animatorI)) != null && dVar.f3297a != null && m0VarD.equals(dVar.f3300d)) {
                s sVar = dVar.f3299c;
                View view = dVar.f3297a;
                s sVarF = F(view, true);
                s sVarS = s(view, true);
                if (sVarF == null && sVarS == null) {
                    sVarS = this.u.f3318a.get(view);
                }
                if (!(sVarF == null && sVarS == null) && dVar.f3301e.G(sVar, sVarS)) {
                    if (animatorI.isRunning() || animatorI.isStarted()) {
                        animatorI.cancel();
                    } else {
                        aVarX.remove(animatorI);
                    }
                }
            }
        }
        n(viewGroup, this.t, this.u, this.x, this.y);
        U();
    }

    public m Q(f fVar) {
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

    public m R(View view) {
        this.j.remove(view);
        return this;
    }

    public void S(View view) {
        if (this.D) {
            if (!this.E) {
                b.e.a<Animator, d> aVarX = x();
                int size = aVarX.size();
                m0 m0VarD = c0.d(view);
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    d dVarM = aVarX.m(i2);
                    if (dVarM.f3297a != null && m0VarD.equals(dVarM.f3300d)) {
                        b.x.a.c(aVarX.i(i2));
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

    public final void T(Animator animator, b.e.a<Animator, d> aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            e(animator);
        }
    }

    public void U() {
        b0();
        b.e.a<Animator, d> aVarX = x();
        for (Animator animator : this.G) {
            if (aVarX.containsKey(animator)) {
                b0();
                T(animator, aVarX);
            }
        }
        this.G.clear();
        o();
    }

    public m V(long j) {
        this.f3291g = j;
        return this;
    }

    public void W(e eVar) {
        this.I = eVar;
    }

    public m X(TimeInterpolator timeInterpolator) {
        this.f3292h = timeInterpolator;
        return this;
    }

    public void Y(g gVar) {
        if (gVar == null) {
            this.K = f3287c;
        } else {
            this.K = gVar;
        }
    }

    public void Z(p pVar) {
    }

    public m a(f fVar) {
        if (this.F == null) {
            this.F = new ArrayList<>();
        }
        this.F.add(fVar);
        return this;
    }

    public m a0(long j) {
        this.f3290f = j;
        return this;
    }

    public m b(View view) {
        this.j.add(view);
        return this;
    }

    public void b0() {
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

    public final void c(b.e.a<View, s> aVar, b.e.a<View, s> aVar2) {
        for (int i2 = 0; i2 < aVar.size(); i2++) {
            s sVarM = aVar.m(i2);
            if (H(sVarM.f3316b)) {
                this.x.add(sVarM);
                this.y.add(null);
            }
        }
        for (int i3 = 0; i3 < aVar2.size(); i3++) {
            s sVarM2 = aVar2.m(i3);
            if (H(sVarM2.f3316b)) {
                this.y.add(sVarM2);
                this.x.add(null);
            }
        }
    }

    public String c0(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f3291g != -1) {
            str2 = str2 + "dur(" + this.f3291g + ") ";
        }
        if (this.f3290f != -1) {
            str2 = str2 + "dly(" + this.f3290f + ") ";
        }
        if (this.f3292h != null) {
            str2 = str2 + "interp(" + this.f3292h + ") ";
        }
        if (this.f3293i.size() <= 0 && this.j.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f3293i.size() > 0) {
            for (int i2 = 0; i2 < this.f3293i.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f3293i.get(i2);
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

    public void cancel() {
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

    public void e(Animator animator) {
        if (animator == null) {
            o();
            return;
        }
        if (p() >= 0) {
            animator.setDuration(p());
        }
        if (y() >= 0) {
            animator.setStartDelay(y() + animator.getStartDelay());
        }
        if (r() != null) {
            animator.setInterpolator(r());
        }
        animator.addListener(new c());
        animator.start();
    }

    public abstract void f(s sVar);

    public final void g(View view, boolean z) {
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
                        i(sVar);
                    } else {
                        f(sVar);
                    }
                    sVar.f3317c.add(this);
                    h(sVar);
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
                                for (int i3 = 0; i3 < size2; i3++) {
                                    if (this.s.get(i3).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                                g(viewGroup.getChildAt(i4), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public void h(s sVar) {
        if (this.H != null && !sVar.f3315a.isEmpty()) {
            throw null;
        }
    }

    public abstract void i(s sVar);

    public void j(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        b.e.a<String, String> aVar;
        k(z);
        if ((this.f3293i.size() > 0 || this.j.size() > 0) && (((arrayList = this.k) == null || arrayList.isEmpty()) && ((arrayList2 = this.l) == null || arrayList2.isEmpty()))) {
            for (int i2 = 0; i2 < this.f3293i.size(); i2++) {
                View viewFindViewById = viewGroup.findViewById(this.f3293i.get(i2).intValue());
                if (viewFindViewById != null) {
                    s sVar = new s(viewFindViewById);
                    if (z) {
                        i(sVar);
                    } else {
                        f(sVar);
                    }
                    sVar.f3317c.add(this);
                    h(sVar);
                    if (z) {
                        d(this.t, viewFindViewById, sVar);
                    } else {
                        d(this.u, viewFindViewById, sVar);
                    }
                }
            }
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                View view = this.j.get(i3);
                s sVar2 = new s(view);
                if (z) {
                    i(sVar2);
                } else {
                    f(sVar2);
                }
                sVar2.f3317c.add(this);
                h(sVar2);
                if (z) {
                    d(this.t, view, sVar2);
                } else {
                    d(this.u, view, sVar2);
                }
            }
        } else {
            g(viewGroup, z);
        }
        if (z || (aVar = this.J) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i4 = 0; i4 < size; i4++) {
            arrayList3.add(this.t.f3321d.remove(this.J.i(i4)));
        }
        for (int i5 = 0; i5 < size; i5++) {
            View view2 = (View) arrayList3.get(i5);
            if (view2 != null) {
                this.t.f3321d.put(this.J.m(i5), view2);
            }
        }
    }

    public void k(boolean z) {
        if (z) {
            this.t.f3318a.clear();
            this.t.f3319b.clear();
            this.t.f3320c.b();
        } else {
            this.u.f3318a.clear();
            this.u.f3319b.clear();
            this.u.f3320c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
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

    public Animator m(ViewGroup viewGroup, s sVar, s sVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(android.view.ViewGroup r20, b.x.t r21, b.x.t r22, java.util.ArrayList<b.x.s> r23, java.util.ArrayList<b.x.s> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.x.m.n(android.view.ViewGroup, b.x.t, b.x.t, java.util.ArrayList, java.util.ArrayList):void");
    }

    public void o() {
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
            for (int i4 = 0; i4 < this.t.f3320c.l(); i4++) {
                View viewM = this.t.f3320c.m(i4);
                if (viewM != null) {
                    b.h.m.t.t0(viewM, false);
                }
            }
            for (int i5 = 0; i5 < this.u.f3320c.l(); i5++) {
                View viewM2 = this.u.f3320c.m(i5);
                if (viewM2 != null) {
                    b.h.m.t.t0(viewM2, false);
                }
            }
            this.E = true;
        }
    }

    public long p() {
        return this.f3291g;
    }

    public e q() {
        return this.I;
    }

    public TimeInterpolator r() {
        return this.f3292h;
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
    public b.x.s s(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            b.x.q r0 = r6.v
            if (r0 == 0) goto L9
            b.x.s r7 = r0.s(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<b.x.s> r0 = r6.x
            goto L10
        Le:
            java.util.ArrayList<b.x.s> r0 = r6.y
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
            b.x.s r5 = (b.x.s) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f3316b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<b.x.s> r7 = r6.y
            goto L37
        L35:
            java.util.ArrayList<b.x.s> r7 = r6.x
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            b.x.s r1 = (b.x.s) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.x.m.s(android.view.View, boolean):b.x.s");
    }

    public String t() {
        return this.f3289e;
    }

    public String toString() {
        return c0("");
    }

    public g u() {
        return this.K;
    }

    public p v() {
        return this.H;
    }

    public long y() {
        return this.f3290f;
    }
}
