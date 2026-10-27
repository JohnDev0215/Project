package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.e;
import androidx.fragment.app.x;
import d0.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
/* JADX INFO: loaded from: classes.dex */
class b extends x {

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2243a;

        static {
            int[] iArr = new int[x.e.c.values().length];
            f2243a = iArr;
            try {
                iArr[x.e.c.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2243a[x.e.c.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2243a[x.e.c.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2243a[x.e.c.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class RunnableC0020b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ List f2244e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ x.e f2245g;

        RunnableC0020b(List list, x.e eVar) {
            this.f2244e = list;
            this.f2245g = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2244e.contains(this.f2245g)) {
                this.f2244e.remove(this.f2245g);
                b.this.s(this.f2245g);
            }
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2247a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2248b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2249c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ x.e f2250d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ k f2251e;

        c(ViewGroup viewGroup, View view, boolean z6, x.e eVar, k kVar) {
            this.f2247a = viewGroup;
            this.f2248b = view;
            this.f2249c = z6;
            this.f2250d = eVar;
            this.f2251e = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2247a.endViewTransition(this.f2248b);
            if (this.f2249c) {
                this.f2250d.e().a(this.f2248b);
            }
            this.f2251e.a();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class d implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Animator f2253a;

        d(Animator animator) {
            this.f2253a = animator;
        }

        @Override // d0.b.a
        public void a() {
            this.f2253a.end();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class e implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2255a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2256b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ k f2257c;

        /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.f2255a.endViewTransition(eVar.f2256b);
                e.this.f2257c.a();
            }
        }

        e(ViewGroup viewGroup, View view, k kVar) {
            this.f2255a = viewGroup;
            this.f2256b = view;
            this.f2257c = kVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f2255a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class f implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f2260a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2261b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ k f2262c;

        f(View view, ViewGroup viewGroup, k kVar) {
            this.f2260a = view;
            this.f2261b = viewGroup;
            this.f2262c = kVar;
        }

        @Override // d0.b.a
        public void a() {
            this.f2260a.clearAnimation();
            this.f2261b.endViewTransition(this.f2260a);
            this.f2262c.a();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class g implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ x.e f2264e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ x.e f2265g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ boolean f2266h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ n.a f2267i;

        g(x.e eVar, x.e eVar2, boolean z6, n.a aVar) {
            this.f2264e = eVar;
            this.f2265g = eVar2;
            this.f2266h = z6;
            this.f2267i = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.f(this.f2264e.f(), this.f2265g.f(), this.f2266h, this.f2267i, false);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class h implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ u f2269e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ View f2270g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ Rect f2271h;

        h(u uVar, View view, Rect rect) {
            this.f2269e = uVar;
            this.f2270g = view;
            this.f2271h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2269e.k(this.f2270g, this.f2271h);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class i implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f2273e;

        i(ArrayList arrayList) {
            this.f2273e = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.A(this.f2273e, 4);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    class j implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ m f2275e;

        j(m mVar) {
            this.f2275e = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2275e.a();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    private static class k extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2277c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2278d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private e.d f2279e;

        k(x.e eVar, d0.b bVar, boolean z6) {
            super(eVar, bVar);
            this.f2278d = false;
            this.f2277c = z6;
        }

        e.d e(Context context) {
            if (this.f2278d) {
                return this.f2279e;
            }
            e.d dVarC = androidx.fragment.app.e.c(context, b().f(), b().e() == x.e.c.VISIBLE, this.f2277c);
            this.f2279e = dVarC;
            this.f2278d = true;
            return dVarC;
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    private static class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final x.e f2280a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d0.b f2281b;

        l(x.e eVar, d0.b bVar) {
            this.f2280a = eVar;
            this.f2281b = bVar;
        }

        void a() {
            this.f2280a.d(this.f2281b);
        }

        x.e b() {
            return this.f2280a;
        }

        d0.b c() {
            return this.f2281b;
        }

        boolean d() {
            x.e.c cVar;
            x.e.c cVarC = x.e.c.c(this.f2280a.f().M);
            x.e.c cVarE = this.f2280a.e();
            return cVarC == cVarE || !(cVarC == (cVar = x.e.c.VISIBLE) || cVarE == cVar);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    private static class m extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Object f2282c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f2283d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Object f2284e;

        m(x.e eVar, d0.b bVar, boolean z6, boolean z7) {
            super(eVar, bVar);
            if (eVar.e() == x.e.c.VISIBLE) {
                this.f2282c = z6 ? eVar.f().M() : eVar.f().u();
                this.f2283d = z6 ? eVar.f().n() : eVar.f().l();
            } else {
                this.f2282c = z6 ? eVar.f().O() : eVar.f().x();
                this.f2283d = true;
            }
            if (!z7) {
                this.f2284e = null;
            } else if (z6) {
                this.f2284e = eVar.f().Q();
            } else {
                this.f2284e = eVar.f().P();
            }
        }

        private u f(Object obj) {
            if (obj == null) {
                return null;
            }
            u uVar = s.f2394b;
            if (uVar != null && uVar.e(obj)) {
                return uVar;
            }
            u uVar2 = s.f2395c;
            if (uVar2 != null && uVar2.e(obj)) {
                return uVar2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + b().f() + " is not a valid framework Transition or AndroidX Transition");
        }

        u e() {
            u uVarF = f(this.f2282c);
            u uVarF2 = f(this.f2284e);
            if (uVarF == null || uVarF2 == null || uVarF == uVarF2) {
                return uVarF != null ? uVarF : uVarF2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + b().f() + " returned Transition " + this.f2282c + " which uses a different Transition  type than its shared element transition " + this.f2284e);
        }

        public Object g() {
            return this.f2284e;
        }

        Object h() {
            return this.f2282c;
        }

        public boolean i() {
            return this.f2284e != null;
        }

        boolean j() {
            return this.f2283d;
        }
    }

    b(ViewGroup viewGroup) {
        super(viewGroup);
    }

    private void w(List<k> list, List<x.e> list2, boolean z6, Map<x.e, Boolean> map) {
        ViewGroup viewGroupM = m();
        Context context = viewGroupM.getContext();
        ArrayList<k> arrayList = new ArrayList();
        boolean z7 = false;
        for (k kVar : list) {
            if (kVar.d()) {
                kVar.a();
            } else {
                e.d dVarE = kVar.e(context);
                if (dVarE == null) {
                    kVar.a();
                } else {
                    Animator animator = dVarE.f2327b;
                    if (animator == null) {
                        arrayList.add(kVar);
                    } else {
                        x.e eVarB = kVar.b();
                        Fragment fragmentF = eVarB.f();
                        if (Boolean.TRUE.equals(map.get(eVarB))) {
                            if (FragmentManager.F0(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragmentF + " as this Fragment was involved in a Transition.");
                            }
                            kVar.a();
                        } else {
                            boolean z8 = eVarB.e() == x.e.c.GONE;
                            if (z8) {
                                list2.remove(eVarB);
                            }
                            View view = fragmentF.M;
                            viewGroupM.startViewTransition(view);
                            animator.addListener(new c(viewGroupM, view, z8, eVarB, kVar));
                            animator.setTarget(view);
                            animator.start();
                            kVar.c().c(new d(animator));
                            z7 = true;
                        }
                    }
                }
            }
        }
        for (k kVar2 : arrayList) {
            x.e eVarB2 = kVar2.b();
            Fragment fragmentF2 = eVarB2.f();
            if (z6) {
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragmentF2 + " as Animations cannot run alongside Transitions.");
                }
                kVar2.a();
            } else if (z7) {
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragmentF2 + " as Animations cannot run alongside Animators.");
                }
                kVar2.a();
            } else {
                View view2 = fragmentF2.M;
                Animation animation = (Animation) h0.h.f(((e.d) h0.h.f(kVar2.e(context))).f2326a);
                if (eVarB2.e() != x.e.c.REMOVED) {
                    view2.startAnimation(animation);
                    kVar2.a();
                } else {
                    viewGroupM.startViewTransition(view2);
                    e.RunnableC0022e runnableC0022e = new e.RunnableC0022e(animation, viewGroupM, view2);
                    runnableC0022e.setAnimationListener(new e(viewGroupM, view2, kVar2));
                    view2.startAnimation(runnableC0022e);
                }
                kVar2.c().c(new f(view2, viewGroupM, kVar2));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Map<x.e, Boolean> x(List<m> list, List<x.e> list2, boolean z6, x.e eVar, x.e eVar2) {
        View view;
        Object obj;
        ArrayList<View> arrayList;
        Object obj2;
        ArrayList<View> arrayList2;
        x.e eVar3;
        x.e eVar4;
        View view2;
        Object objN;
        n.a aVar;
        ArrayList<View> arrayList3;
        x.e eVar5;
        ArrayList<View> arrayList4;
        Rect rect;
        View view3;
        u uVar;
        x.e eVar6;
        View view4;
        boolean z7 = z6;
        x.e eVar7 = eVar;
        x.e eVar8 = eVar2;
        HashMap map = new HashMap();
        u uVar2 = null;
        for (m mVar : list) {
            if (!mVar.d()) {
                u uVarE = mVar.e();
                if (uVar2 == null) {
                    uVar2 = uVarE;
                } else if (uVarE != null && uVar2 != uVarE) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + mVar.b().f() + " returned Transition " + mVar.h() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (uVar2 == null) {
            for (m mVar2 : list) {
                map.put(mVar2.b(), Boolean.FALSE);
                mVar2.a();
            }
            return map;
        }
        View view5 = new View(m().getContext());
        Rect rect2 = new Rect();
        ArrayList<View> arrayList5 = new ArrayList<>();
        ArrayList<View> arrayList6 = new ArrayList<>();
        n.a aVar2 = new n.a();
        Object obj3 = null;
        View view6 = null;
        boolean z8 = false;
        for (m mVar3 : list) {
            if (!mVar3.i() || eVar7 == null || eVar8 == null) {
                aVar = aVar2;
                arrayList3 = arrayList6;
                eVar5 = eVar7;
                arrayList4 = arrayList5;
                rect = rect2;
                view3 = view5;
                uVar = uVar2;
                eVar6 = eVar8;
                view6 = view6;
            } else {
                Object objB = uVar2.B(uVar2.g(mVar3.g()));
                ArrayList<String> arrayListR = eVar2.f().R();
                ArrayList<String> arrayListR2 = eVar.f().R();
                ArrayList<String> arrayListS = eVar.f().S();
                View view7 = view6;
                int i7 = 0;
                while (i7 < arrayListS.size()) {
                    int iIndexOf = arrayListR.indexOf(arrayListS.get(i7));
                    ArrayList<String> arrayList7 = arrayListS;
                    if (iIndexOf != -1) {
                        arrayListR.set(iIndexOf, arrayListR2.get(i7));
                    }
                    i7++;
                    arrayListS = arrayList7;
                }
                ArrayList<String> arrayListS2 = eVar2.f().S();
                if (z7) {
                    eVar.f().v();
                    eVar2.f().y();
                } else {
                    eVar.f().y();
                    eVar2.f().v();
                }
                int i8 = 0;
                for (int size = arrayListR.size(); i8 < size; size = size) {
                    aVar2.put(arrayListR.get(i8), arrayListS2.get(i8));
                    i8++;
                }
                n.a<String, View> aVar3 = new n.a<>();
                u(aVar3, eVar.f().M);
                aVar3.q(arrayListR);
                aVar2.q(aVar3.keySet());
                n.a<String, View> aVar4 = new n.a<>();
                u(aVar4, eVar2.f().M);
                aVar4.q(arrayListS2);
                aVar4.q(aVar2.values());
                s.x(aVar2, aVar4);
                v(aVar3, aVar2.keySet());
                v(aVar4, aVar2.values());
                if (aVar2.isEmpty()) {
                    arrayList5.clear();
                    arrayList6.clear();
                    aVar = aVar2;
                    arrayList3 = arrayList6;
                    eVar5 = eVar7;
                    arrayList4 = arrayList5;
                    rect = rect2;
                    view3 = view5;
                    uVar = uVar2;
                    view6 = view7;
                    obj3 = null;
                    eVar6 = eVar8;
                } else {
                    s.f(eVar2.f(), eVar.f(), z7, aVar3, true);
                    aVar = aVar2;
                    ArrayList<View> arrayList8 = arrayList6;
                    i0.s.a(m(), new g(eVar2, eVar, z6, aVar4));
                    Iterator<View> it = aVar3.values().iterator();
                    while (it.hasNext()) {
                        t(arrayList5, it.next());
                    }
                    if (arrayListR.isEmpty()) {
                        view6 = view7;
                    } else {
                        View view8 = (View) aVar3.get(arrayListR.get(0));
                        uVar2.v(objB, view8);
                        view6 = view8;
                    }
                    Iterator<View> it2 = aVar4.values().iterator();
                    while (it2.hasNext()) {
                        t(arrayList8, it2.next());
                    }
                    arrayList3 = arrayList8;
                    if (!arrayListS2.isEmpty() && (view4 = (View) aVar4.get(arrayListS2.get(0))) != null) {
                        i0.s.a(m(), new h(uVar2, view4, rect2));
                        z8 = true;
                    }
                    uVar2.z(objB, view5, arrayList5);
                    arrayList4 = arrayList5;
                    rect = rect2;
                    view3 = view5;
                    uVar = uVar2;
                    uVar2.t(objB, null, null, null, null, objB, arrayList3);
                    Boolean bool = Boolean.TRUE;
                    eVar5 = eVar;
                    map.put(eVar5, bool);
                    eVar6 = eVar2;
                    map.put(eVar6, bool);
                    obj3 = objB;
                }
            }
            eVar7 = eVar5;
            arrayList5 = arrayList4;
            rect2 = rect;
            view5 = view3;
            eVar8 = eVar6;
            aVar2 = aVar;
            z7 = z6;
            arrayList6 = arrayList3;
            uVar2 = uVar;
        }
        View view9 = view6;
        n.a aVar5 = aVar2;
        ArrayList<View> arrayList9 = arrayList6;
        x.e eVar9 = eVar7;
        ArrayList<View> arrayList10 = arrayList5;
        Rect rect3 = rect2;
        View view10 = view5;
        u uVar3 = uVar2;
        boolean z9 = false;
        x.e eVar10 = eVar8;
        ArrayList arrayList11 = new ArrayList();
        Object obj4 = null;
        Object objN2 = null;
        for (m mVar4 : list) {
            if (mVar4.d()) {
                map.put(mVar4.b(), Boolean.FALSE);
                mVar4.a();
            } else {
                Object objG = uVar3.g(mVar4.h());
                x.e eVarB = mVar4.b();
                boolean z10 = (obj3 == null || !(eVarB == eVar9 || eVarB == eVar10)) ? z9 : true;
                if (objG == null) {
                    if (!z10) {
                        map.put(eVarB, Boolean.FALSE);
                        mVar4.a();
                    }
                    arrayList2 = arrayList9;
                    arrayList = arrayList10;
                    view = view10;
                    objN = obj4;
                    eVar3 = eVar10;
                    view2 = view9;
                } else {
                    ArrayList<View> arrayList12 = new ArrayList<>();
                    Object obj5 = obj4;
                    t(arrayList12, eVarB.f().M);
                    if (z10) {
                        if (eVarB == eVar9) {
                            arrayList12.removeAll(arrayList10);
                        } else {
                            arrayList12.removeAll(arrayList9);
                        }
                    }
                    if (arrayList12.isEmpty()) {
                        uVar3.a(objG, view10);
                        arrayList2 = arrayList9;
                        arrayList = arrayList10;
                        view = view10;
                        eVar4 = eVarB;
                        obj2 = objN2;
                        eVar3 = eVar10;
                        obj = obj5;
                    } else {
                        uVar3.b(objG, arrayList12);
                        view = view10;
                        obj = obj5;
                        arrayList = arrayList10;
                        obj2 = objN2;
                        arrayList2 = arrayList9;
                        eVar3 = eVar10;
                        uVar3.t(objG, objG, arrayList12, null, null, null, null);
                        if (eVarB.e() == x.e.c.GONE) {
                            eVar4 = eVarB;
                            list2.remove(eVar4);
                            uVar3.r(objG, eVar4.f().M, arrayList12);
                            i0.s.a(m(), new i(arrayList12));
                        } else {
                            eVar4 = eVarB;
                        }
                    }
                    if (eVar4.e() == x.e.c.VISIBLE) {
                        arrayList11.addAll(arrayList12);
                        if (z8) {
                            uVar3.u(objG, rect3);
                        }
                        view2 = view9;
                    } else {
                        view2 = view9;
                        uVar3.v(objG, view2);
                    }
                    map.put(eVar4, Boolean.TRUE);
                    if (mVar4.j()) {
                        objN2 = uVar3.n(obj2, objG, null);
                        objN = obj;
                    } else {
                        objN = uVar3.n(obj, objG, null);
                        objN2 = obj2;
                    }
                }
                obj4 = objN;
                eVar10 = eVar3;
                view9 = view2;
                view10 = view;
                arrayList10 = arrayList;
                arrayList9 = arrayList2;
                z9 = false;
            }
        }
        ArrayList<View> arrayList13 = arrayList9;
        ArrayList<View> arrayList14 = arrayList10;
        x.e eVar11 = eVar10;
        Object objM = uVar3.m(objN2, obj4, obj3);
        for (m mVar5 : list) {
            if (!mVar5.d()) {
                Object objH = mVar5.h();
                x.e eVarB2 = mVar5.b();
                boolean z11 = obj3 != null && (eVarB2 == eVar9 || eVarB2 == eVar11);
                if (objH != null || z11) {
                    if (i0.u.P(m())) {
                        uVar3.w(mVar5.b().f(), objM, mVar5.c(), new j(mVar5));
                    } else {
                        if (FragmentManager.F0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + m() + " has not been laid out. Completing operation " + eVarB2);
                        }
                        mVar5.a();
                    }
                }
            }
        }
        if (!i0.u.P(m())) {
            return map;
        }
        s.A(arrayList11, 4);
        ArrayList<String> arrayListO = uVar3.o(arrayList13);
        uVar3.c(m(), objM);
        uVar3.y(m(), arrayList14, arrayList13, arrayListO, aVar5);
        s.A(arrayList11, 0);
        uVar3.A(obj3, arrayList14, arrayList13);
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0085  */
    @Override // androidx.fragment.app.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void f(java.util.List<androidx.fragment.app.x.e> r11, boolean r12) {
        /*
            r10 = this;
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r6 = r1
            r7 = r6
        L7:
            boolean r1 = r0.hasNext()
            r2 = 1
            if (r1 == 0) goto L44
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.x$e r1 = (androidx.fragment.app.x.e) r1
            androidx.fragment.app.Fragment r3 = r1.f()
            android.view.View r3 = r3.M
            androidx.fragment.app.x$e$c r3 = androidx.fragment.app.x.e.c.c(r3)
            int[] r4 = androidx.fragment.app.b.a.f2243a
            androidx.fragment.app.x$e$c r5 = r1.e()
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 == r2) goto L3c
            r2 = 2
            if (r4 == r2) goto L3c
            r2 = 3
            if (r4 == r2) goto L3c
            r2 = 4
            if (r4 == r2) goto L36
            goto L7
        L36:
            androidx.fragment.app.x$e$c r2 = androidx.fragment.app.x.e.c.VISIBLE
            if (r3 == r2) goto L7
            r7 = r1
            goto L7
        L3c:
            androidx.fragment.app.x$e$c r2 = androidx.fragment.app.x.e.c.VISIBLE
            if (r3 != r2) goto L7
            if (r6 != 0) goto L7
            r6 = r1
            goto L7
        L44:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r11)
            java.util.Iterator r11 = r11.iterator()
        L57:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto L95
            java.lang.Object r4 = r11.next()
            androidx.fragment.app.x$e r4 = (androidx.fragment.app.x.e) r4
            d0.b r5 = new d0.b
            r5.<init>()
            r4.j(r5)
            androidx.fragment.app.b$k r8 = new androidx.fragment.app.b$k
            r8.<init>(r4, r5, r12)
            r0.add(r8)
            d0.b r5 = new d0.b
            r5.<init>()
            r4.j(r5)
            androidx.fragment.app.b$m r8 = new androidx.fragment.app.b$m
            r9 = 0
            if (r12 == 0) goto L83
            if (r4 != r6) goto L86
            goto L85
        L83:
            if (r4 != r7) goto L86
        L85:
            r9 = r2
        L86:
            r8.<init>(r4, r5, r12, r9)
            r3.add(r8)
            androidx.fragment.app.b$b r5 = new androidx.fragment.app.b$b
            r5.<init>(r1, r4)
            r4.a(r5)
            goto L57
        L95:
            r2 = r10
            r4 = r1
            r5 = r12
            java.util.Map r11 = r2.x(r3, r4, r5, r6, r7)
            java.lang.Boolean r12 = java.lang.Boolean.TRUE
            boolean r12 = r11.containsValue(r12)
            r10.w(r0, r1, r12, r11)
            java.util.Iterator r11 = r1.iterator()
        La9:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Lb9
            java.lang.Object r12 = r11.next()
            androidx.fragment.app.x$e r12 = (androidx.fragment.app.x.e) r12
            r10.s(r12)
            goto La9
        Lb9:
            r1.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b.f(java.util.List, boolean):void");
    }

    void s(x.e eVar) {
        eVar.e().a(eVar.f().M);
    }

    void t(ArrayList<View> arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        if (!arrayList.contains(view) && i0.u.H(view) != null) {
            arrayList.add(view);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if (childAt.getVisibility() == 0) {
                t(arrayList, childAt);
            }
        }
    }

    void u(Map<String, View> map, View view) {
        String strH = i0.u.H(view);
        if (strH != null) {
            map.put(strH, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = viewGroup.getChildAt(i7);
                if (childAt.getVisibility() == 0) {
                    u(map, childAt);
                }
            }
        }
    }

    void v(n.a<String, View> aVar, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = aVar.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(i0.u.H(it.next().getValue()))) {
                it.remove();
            }
        }
    }
}
