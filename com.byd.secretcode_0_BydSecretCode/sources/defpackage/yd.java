package defpackage;

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
public abstract class yd implements Cloneable {
    public static final int[] b = {2, 1, 3, 4};
    public static final td c = new a();
    public static ThreadLocal<a4<Animator, d>> d = new ThreadLocal<>();
    public be H;
    public e I;
    public a4<String, String> J;
    public ArrayList<ee> x;
    public ArrayList<ee> y;
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
    public fe t = new fe();
    public fe u = new fe();
    public ce v = null;
    public int[] w = b;
    public ViewGroup z = null;
    public boolean A = false;
    public ArrayList<Animator> B = new ArrayList<>();
    public int C = 0;
    public boolean D = false;
    public boolean E = false;
    public ArrayList<f> F = null;
    public ArrayList<Animator> G = new ArrayList<>();
    public td K = c;

    /* JADX INFO: compiled from: Transition.java */
    public static class a extends td {
        @Override // defpackage.td
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ a4 a;

        public b(a4 a4Var) {
            this.a = a4Var;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.remove(animator);
            yd.this.B.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            yd.this.B.add(animator);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            yd.this.p();
            animator.removeListener(this);
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public static class d {
        public View a;
        public String b;
        public ee c;
        public ye d;
        public yd e;

        public d(View view, String str, yd ydVar, ye yeVar, ee eeVar) {
            this.a = view;
            this.b = str;
            this.c = eeVar;
            this.d = yeVar;
            this.e = ydVar;
        }
    }

    /* JADX INFO: compiled from: Transition.java */
    public static abstract class e {
    }

    /* JADX INFO: compiled from: Transition.java */
    public interface f {
        void a(yd ydVar);

        void b(yd ydVar);

        void c(yd ydVar);

        void d(yd ydVar);

        void e(yd ydVar);
    }

    public static boolean H(ee eeVar, ee eeVar2, String str) {
        Object obj = eeVar.a.get(str);
        Object obj2 = eeVar2.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public static void d(fe feVar, View view, ee eeVar) {
        feVar.a.put(view, eeVar);
        int id = view.getId();
        if (id >= 0) {
            if (feVar.b.indexOfKey(id) >= 0) {
                feVar.b.put(id, null);
            } else {
                feVar.b.put(id, view);
            }
        }
        String strH = t9.H(view);
        if (strH != null) {
            if (feVar.d.containsKey(strH)) {
                feVar.d.put(strH, null);
            } else {
                feVar.d.put(strH, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (feVar.c.g(itemIdAtPosition) < 0) {
                    t9.s0(view, true);
                    feVar.c.i(itemIdAtPosition, view);
                    return;
                }
                View viewE = feVar.c.e(itemIdAtPosition);
                if (viewE != null) {
                    t9.s0(viewE, false);
                    feVar.c.i(itemIdAtPosition, null);
                }
            }
        }
    }

    public static a4<Animator, d> x() {
        a4<Animator, d> a4Var = d.get();
        if (a4Var != null) {
            return a4Var;
        }
        a4<Animator, d> a4Var2 = new a4<>();
        d.set(a4Var2);
        return a4Var2;
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

    public ee E(View view, boolean z) {
        ce ceVar = this.v;
        if (ceVar != null) {
            return ceVar.E(view, z);
        }
        return (z ? this.t : this.u).a.get(view);
    }

    public boolean F(ee eeVar, ee eeVar2) {
        if (eeVar == null || eeVar2 == null) {
            return false;
        }
        String[] strArrD = D();
        if (strArrD == null) {
            Iterator<String> it = eeVar.a.keySet().iterator();
            while (it.hasNext()) {
                if (H(eeVar, eeVar2, it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrD) {
            if (!H(eeVar, eeVar2, str)) {
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
        if (this.p != null && t9.H(view) != null && this.p.contains(t9.H(view))) {
            return false;
        }
        if ((this.i.size() == 0 && this.j.size() == 0 && (((arrayList = this.l) == null || arrayList.isEmpty()) && ((arrayList2 = this.k) == null || arrayList2.isEmpty()))) || this.i.contains(Integer.valueOf(id)) || this.j.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.k;
        if (arrayList6 != null && arrayList6.contains(t9.H(view))) {
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

    public final void I(a4<View, ee> a4Var, a4<View, ee> a4Var2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View viewValueAt = sparseArray.valueAt(i);
            if (viewValueAt != null && G(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i))) != null && G(view)) {
                ee eeVar = a4Var.get(viewValueAt);
                ee eeVar2 = a4Var2.get(view);
                if (eeVar != null && eeVar2 != null) {
                    this.x.add(eeVar);
                    this.y.add(eeVar2);
                    a4Var.remove(viewValueAt);
                    a4Var2.remove(view);
                }
            }
        }
    }

    public final void J(a4<View, ee> a4Var, a4<View, ee> a4Var2) {
        ee eeVarRemove;
        for (int size = a4Var.size() - 1; size >= 0; size--) {
            View viewI = a4Var.i(size);
            if (viewI != null && G(viewI) && (eeVarRemove = a4Var2.remove(viewI)) != null && G(eeVarRemove.b)) {
                this.x.add(a4Var.k(size));
                this.y.add(eeVarRemove);
            }
        }
    }

    public final void K(a4<View, ee> a4Var, a4<View, ee> a4Var2, d4<View> d4Var, d4<View> d4Var2) {
        View viewE;
        int iL = d4Var.l();
        for (int i = 0; i < iL; i++) {
            View viewM = d4Var.m(i);
            if (viewM != null && G(viewM) && (viewE = d4Var2.e(d4Var.h(i))) != null && G(viewE)) {
                ee eeVar = a4Var.get(viewM);
                ee eeVar2 = a4Var2.get(viewE);
                if (eeVar != null && eeVar2 != null) {
                    this.x.add(eeVar);
                    this.y.add(eeVar2);
                    a4Var.remove(viewM);
                    a4Var2.remove(viewE);
                }
            }
        }
    }

    public final void L(a4<View, ee> a4Var, a4<View, ee> a4Var2, a4<String, View> a4Var3, a4<String, View> a4Var4) {
        View view;
        int size = a4Var3.size();
        for (int i = 0; i < size; i++) {
            View viewM = a4Var3.m(i);
            if (viewM != null && G(viewM) && (view = a4Var4.get(a4Var3.i(i))) != null && G(view)) {
                ee eeVar = a4Var.get(viewM);
                ee eeVar2 = a4Var2.get(view);
                if (eeVar != null && eeVar2 != null) {
                    this.x.add(eeVar);
                    this.y.add(eeVar2);
                    a4Var.remove(viewM);
                    a4Var2.remove(view);
                }
            }
        }
    }

    public final void M(fe feVar, fe feVar2) {
        a4<View, ee> a4Var = new a4<>(feVar.a);
        a4<View, ee> a4Var2 = new a4<>(feVar2.a);
        int i = 0;
        while (true) {
            int[] iArr = this.w;
            if (i >= iArr.length) {
                c(a4Var, a4Var2);
                return;
            }
            int i2 = iArr[i];
            if (i2 == 1) {
                J(a4Var, a4Var2);
            } else if (i2 == 2) {
                L(a4Var, a4Var2, feVar.d, feVar2.d);
            } else if (i2 == 3) {
                I(a4Var, a4Var2, feVar.b, feVar2.b);
            } else if (i2 == 4) {
                K(a4Var, a4Var2, feVar.c, feVar2.c);
            }
            i++;
        }
    }

    public void N(View view) {
        if (this.E) {
            return;
        }
        a4<Animator, d> a4VarX = x();
        int size = a4VarX.size();
        ye yeVarD = oe.d(view);
        for (int i = size - 1; i >= 0; i--) {
            d dVarM = a4VarX.m(i);
            if (dVarM.a != null && yeVarD.equals(dVarM.d)) {
                nd.b(a4VarX.i(i));
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
        a4<Animator, d> a4VarX = x();
        int size = a4VarX.size();
        ye yeVarD = oe.d(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animatorI = a4VarX.i(i);
            if (animatorI != null && (dVar = a4VarX.get(animatorI)) != null && dVar.a != null && yeVarD.equals(dVar.d)) {
                ee eeVar = dVar.c;
                View view = dVar.a;
                ee eeVarE = E(view, true);
                ee eeVarT = t(view, true);
                if (eeVarE == null && eeVarT == null) {
                    eeVarT = this.u.a.get(view);
                }
                if (!(eeVarE == null && eeVarT == null) && dVar.e.F(eeVar, eeVarT)) {
                    if (animatorI.isRunning() || animatorI.isStarted()) {
                        animatorI.cancel();
                    } else {
                        a4VarX.remove(animatorI);
                    }
                }
            }
        }
        o(viewGroup, this.t, this.u, this.x, this.y);
        T();
    }

    public yd P(f fVar) {
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

    public yd Q(View view) {
        this.j.remove(view);
        return this;
    }

    public void R(View view) {
        if (this.D) {
            if (!this.E) {
                a4<Animator, d> a4VarX = x();
                int size = a4VarX.size();
                ye yeVarD = oe.d(view);
                for (int i = size - 1; i >= 0; i--) {
                    d dVarM = a4VarX.m(i);
                    if (dVarM.a != null && yeVarD.equals(dVarM.d)) {
                        nd.c(a4VarX.i(i));
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

    public final void S(Animator animator, a4<Animator, d> a4Var) {
        if (animator != null) {
            animator.addListener(new b(a4Var));
            e(animator);
        }
    }

    public void T() {
        a0();
        a4<Animator, d> a4VarX = x();
        for (Animator animator : this.G) {
            if (a4VarX.containsKey(animator)) {
                a0();
                S(animator, a4VarX);
            }
        }
        this.G.clear();
        p();
    }

    public yd U(long j) {
        this.g = j;
        return this;
    }

    public void V(e eVar) {
        this.I = eVar;
    }

    public yd W(TimeInterpolator timeInterpolator) {
        this.h = timeInterpolator;
        return this;
    }

    public void X(td tdVar) {
        if (tdVar == null) {
            this.K = c;
        } else {
            this.K = tdVar;
        }
    }

    public void Y(be beVar) {
    }

    public yd Z(long j) {
        this.f = j;
        return this;
    }

    public yd a(f fVar) {
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

    public yd b(View view) {
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

    public final void c(a4<View, ee> a4Var, a4<View, ee> a4Var2) {
        for (int i = 0; i < a4Var.size(); i++) {
            ee eeVarM = a4Var.m(i);
            if (G(eeVarM.b)) {
                this.x.add(eeVarM);
                this.y.add(null);
            }
        }
        for (int i2 = 0; i2 < a4Var2.size(); i2++) {
            ee eeVarM2 = a4Var2.m(i2);
            if (G(eeVarM2.b)) {
                this.y.add(eeVarM2);
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

    public abstract void g(ee eeVar);

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
                    ee eeVar = new ee(view);
                    if (z) {
                        j(eeVar);
                    } else {
                        g(eeVar);
                    }
                    eeVar.c.add(this);
                    i(eeVar);
                    if (z) {
                        d(this.t, view, eeVar);
                    } else {
                        d(this.u, view, eeVar);
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

    public void i(ee eeVar) {
        if (this.H != null && !eeVar.a.isEmpty()) {
            throw null;
        }
    }

    public abstract void j(ee eeVar);

    public void k(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        a4<String, String> a4Var;
        l(z);
        if ((this.i.size() > 0 || this.j.size() > 0) && (((arrayList = this.k) == null || arrayList.isEmpty()) && ((arrayList2 = this.l) == null || arrayList2.isEmpty()))) {
            for (int i = 0; i < this.i.size(); i++) {
                View viewFindViewById = viewGroup.findViewById(this.i.get(i).intValue());
                if (viewFindViewById != null) {
                    ee eeVar = new ee(viewFindViewById);
                    if (z) {
                        j(eeVar);
                    } else {
                        g(eeVar);
                    }
                    eeVar.c.add(this);
                    i(eeVar);
                    if (z) {
                        d(this.t, viewFindViewById, eeVar);
                    } else {
                        d(this.u, viewFindViewById, eeVar);
                    }
                }
            }
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                View view = this.j.get(i2);
                ee eeVar2 = new ee(view);
                if (z) {
                    j(eeVar2);
                } else {
                    g(eeVar2);
                }
                eeVar2.c.add(this);
                i(eeVar2);
                if (z) {
                    d(this.t, view, eeVar2);
                } else {
                    d(this.u, view, eeVar2);
                }
            }
        } else {
            h(viewGroup, z);
        }
        if (z || (a4Var = this.J) == null) {
            return;
        }
        int size = a4Var.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList3.add(this.t.d.remove(this.J.i(i3)));
        }
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) arrayList3.get(i4);
            if (view2 != null) {
                this.t.d.put(this.J.m(i4), view2);
            }
        }
    }

    public void l(boolean z) {
        if (z) {
            this.t.a.clear();
            this.t.b.clear();
            this.t.c.b();
        } else {
            this.u.a.clear();
            this.u.b.clear();
            this.u.c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public yd clone() {
        try {
            yd ydVar = (yd) super.clone();
            ydVar.G = new ArrayList<>();
            ydVar.t = new fe();
            ydVar.u = new fe();
            ydVar.x = null;
            ydVar.y = null;
            return ydVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator n(ViewGroup viewGroup, ee eeVar, ee eeVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void o(android.view.ViewGroup r20, defpackage.fe r21, defpackage.fe r22, java.util.ArrayList<defpackage.ee> r23, java.util.ArrayList<defpackage.ee> r24) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yd.o(android.view.ViewGroup, fe, fe, java.util.ArrayList, java.util.ArrayList):void");
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
            for (int i3 = 0; i3 < this.t.c.l(); i3++) {
                View viewM = this.t.c.m(i3);
                if (viewM != null) {
                    t9.s0(viewM, false);
                }
            }
            for (int i4 = 0; i4 < this.u.c.l(); i4++) {
                View viewM2 = this.u.c.m(i4);
                if (viewM2 != null) {
                    t9.s0(viewM2, false);
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
    public defpackage.ee t(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            ce r0 = r6.v
            if (r0 == 0) goto L9
            ee r7 = r0.t(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<ee> r0 = r6.x
            goto L10
        Le:
            java.util.ArrayList<ee> r0 = r6.y
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
            ee r5 = (defpackage.ee) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<ee> r7 = r6.y
            goto L37
        L35:
            java.util.ArrayList<ee> r7 = r6.x
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            ee r1 = (defpackage.ee) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yd.t(android.view.View, boolean):ee");
    }

    public String toString() {
        return b0("");
    }

    public String u() {
        return this.e;
    }

    public td v() {
        return this.K;
    }

    public be w() {
        return this.H;
    }

    public long y() {
        return this.f;
    }

    public List<Integer> z() {
        return this.i;
    }
}
