package b.l.d;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.SpecialEffectsController;
import b.h.i.a;
import b.l.d.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends SpecialEffectsController {

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2750a;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            f2750a = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2750a[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2750a[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2750a[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: b.l.d.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class RunnableC0051b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f2751b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ SpecialEffectsController.Operation f2752c;

        public RunnableC0051b(List list, SpecialEffectsController.Operation operation) {
            this.f2751b = list;
            this.f2752c = operation;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2751b.contains(this.f2752c)) {
                this.f2751b.remove(this.f2752c);
                b.this.s(this.f2752c);
            }
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f2754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2755b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f2756c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ SpecialEffectsController.Operation f2757d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ k f2758e;

        public c(ViewGroup viewGroup, View view, boolean z, SpecialEffectsController.Operation operation, k kVar) {
            this.f2754a = viewGroup;
            this.f2755b = view;
            this.f2756c = z;
            this.f2757d = operation;
            this.f2758e = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2754a.endViewTransition(this.f2755b);
            if (this.f2756c) {
                this.f2757d.e().applyState(this.f2755b);
            }
            this.f2758e.a();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class d implements a.InterfaceC0036a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Animator f2760a;

        public d(Animator animator) {
            this.f2760a = animator;
        }

        @Override // b.h.i.a.InterfaceC0036a
        public void a() {
            this.f2760a.end();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class e implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f2762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2763b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ k f2764c;

        /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.f2762a.endViewTransition(eVar.f2763b);
                e.this.f2764c.a();
            }
        }

        public e(ViewGroup viewGroup, View view, k kVar) {
            this.f2762a = viewGroup;
            this.f2763b = view;
            this.f2764c = kVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f2762a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class f implements a.InterfaceC0036a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2767a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f2768b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ k f2769c;

        public f(View view, ViewGroup viewGroup, k kVar) {
            this.f2767a = view;
            this.f2768b = viewGroup;
            this.f2769c = kVar;
        }

        @Override // b.h.i.a.InterfaceC0036a
        public void a() {
            this.f2767a.clearAnimation();
            this.f2768b.endViewTransition(this.f2767a);
            this.f2769c.a();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class g implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ SpecialEffectsController.Operation f2771b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ SpecialEffectsController.Operation f2772c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f2773d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ b.e.a f2774e;

        public g(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, b.e.a aVar) {
            this.f2771b = operation;
            this.f2772c = operation2;
            this.f2773d = z;
            this.f2774e = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            q.f(this.f2771b.f(), this.f2772c.f(), this.f2773d, this.f2774e, false);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class h implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ s f2776b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2777c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Rect f2778d;

        public h(s sVar, View view, Rect rect) {
            this.f2776b = sVar;
            this.f2777c = view;
            this.f2778d = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2776b.k(this.f2777c, this.f2778d);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class i implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ArrayList f2780b;

        public i(ArrayList arrayList) {
            this.f2780b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            q.A(this.f2780b, 4);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public class j implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ m f2782b;

        public j(m mVar) {
            this.f2782b = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2782b.a();
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public static class k extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2784c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2785d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c.d f2786e;

        public k(SpecialEffectsController.Operation operation, b.h.i.a aVar, boolean z) {
            super(operation, aVar);
            this.f2785d = false;
            this.f2784c = z;
        }

        public c.d e(Context context) {
            if (this.f2785d) {
                return this.f2786e;
            }
            c.d dVarC = b.l.d.c.c(context, b().f(), b().e() == SpecialEffectsController.Operation.State.VISIBLE, this.f2784c);
            this.f2786e = dVarC;
            this.f2785d = true;
            return dVarC;
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public static class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SpecialEffectsController.Operation f2787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.h.i.a f2788b;

        public l(SpecialEffectsController.Operation operation, b.h.i.a aVar) {
            this.f2787a = operation;
            this.f2788b = aVar;
        }

        public void a() {
            this.f2787a.d(this.f2788b);
        }

        public SpecialEffectsController.Operation b() {
            return this.f2787a;
        }

        public b.h.i.a c() {
            return this.f2788b;
        }

        public boolean d() {
            SpecialEffectsController.Operation.State state;
            SpecialEffectsController.Operation.State stateFrom = SpecialEffectsController.Operation.State.from(this.f2787a.f().mView);
            SpecialEffectsController.Operation.State stateE = this.f2787a.e();
            return stateFrom == stateE || !(stateFrom == (state = SpecialEffectsController.Operation.State.VISIBLE) || stateE == state);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.java */
    public static class m extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Object f2789c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f2790d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Object f2791e;

        public m(SpecialEffectsController.Operation operation, b.h.i.a aVar, boolean z, boolean z2) {
            super(operation, aVar);
            if (operation.e() == SpecialEffectsController.Operation.State.VISIBLE) {
                this.f2789c = z ? operation.f().getReenterTransition() : operation.f().getEnterTransition();
                this.f2790d = z ? operation.f().getAllowReturnTransitionOverlap() : operation.f().getAllowEnterTransitionOverlap();
            } else {
                this.f2789c = z ? operation.f().getReturnTransition() : operation.f().getExitTransition();
                this.f2790d = true;
            }
            if (!z2) {
                this.f2791e = null;
            } else if (z) {
                this.f2791e = operation.f().getSharedElementReturnTransition();
            } else {
                this.f2791e = operation.f().getSharedElementEnterTransition();
            }
        }

        public s e() {
            s sVarF = f(this.f2789c);
            s sVarF2 = f(this.f2791e);
            if (sVarF == null || sVarF2 == null || sVarF == sVarF2) {
                return sVarF != null ? sVarF : sVarF2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + b().f() + " returned Transition " + this.f2789c + " which uses a different Transition  type than its shared element transition " + this.f2791e);
        }

        public final s f(Object obj) {
            if (obj == null) {
                return null;
            }
            s sVar = q.f2860b;
            if (sVar != null && sVar.e(obj)) {
                return sVar;
            }
            s sVar2 = q.f2861c;
            if (sVar2 != null && sVar2.e(obj)) {
                return sVar2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + b().f() + " is not a valid framework Transition or AndroidX Transition");
        }

        public Object g() {
            return this.f2791e;
        }

        public Object h() {
            return this.f2789c;
        }

        public boolean i() {
            return this.f2791e != null;
        }

        public boolean j() {
            return this.f2790d;
        }
    }

    public b(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0085  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r11, boolean r12) {
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
            androidx.fragment.app.SpecialEffectsController$Operation r1 = (androidx.fragment.app.SpecialEffectsController.Operation) r1
            androidx.fragment.app.Fragment r3 = r1.f()
            android.view.View r3 = r3.mView
            androidx.fragment.app.SpecialEffectsController$Operation$State r3 = androidx.fragment.app.SpecialEffectsController.Operation.State.from(r3)
            int[] r4 = b.l.d.b.a.f2750a
            androidx.fragment.app.SpecialEffectsController$Operation$State r5 = r1.e()
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
            androidx.fragment.app.SpecialEffectsController$Operation$State r2 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r3 == r2) goto L7
            r7 = r1
            goto L7
        L3c:
            androidx.fragment.app.SpecialEffectsController$Operation$State r2 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
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
            androidx.fragment.app.SpecialEffectsController$Operation r4 = (androidx.fragment.app.SpecialEffectsController.Operation) r4
            b.h.i.a r5 = new b.h.i.a
            r5.<init>()
            r4.j(r5)
            b.l.d.b$k r8 = new b.l.d.b$k
            r8.<init>(r4, r5, r12)
            r0.add(r8)
            b.h.i.a r5 = new b.h.i.a
            r5.<init>()
            r4.j(r5)
            b.l.d.b$m r8 = new b.l.d.b$m
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
            b.l.d.b$b r5 = new b.l.d.b$b
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
            androidx.fragment.app.SpecialEffectsController$Operation r12 = (androidx.fragment.app.SpecialEffectsController.Operation) r12
            r10.s(r12)
            goto La9
        Lb9:
            r1.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.l.d.b.f(java.util.List, boolean):void");
    }

    public void s(SpecialEffectsController.Operation operation) {
        operation.e().applyState(operation.f().mView);
    }

    public void t(ArrayList<View> arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        if (!arrayList.contains(view) && b.h.m.t.H(view) != null) {
            arrayList.add(view);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                t(arrayList, childAt);
            }
        }
    }

    public void u(Map<String, View> map, View view) {
        String strH = b.h.m.t.H(view);
        if (strH != null) {
            map.put(strH, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    u(map, childAt);
                }
            }
        }
    }

    public void v(b.e.a<String, View> aVar, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = aVar.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(b.h.m.t.H(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    public final void w(List<k> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        ViewGroup viewGroupM = m();
        Context context = viewGroupM.getContext();
        ArrayList<k> arrayList = new ArrayList();
        boolean z2 = false;
        for (k kVar : list) {
            if (kVar.d()) {
                kVar.a();
            } else {
                c.d dVarE = kVar.e(context);
                if (dVarE == null) {
                    kVar.a();
                } else {
                    Animator animator = dVarE.f2804b;
                    if (animator == null) {
                        arrayList.add(kVar);
                    } else {
                        SpecialEffectsController.Operation operationB = kVar.b();
                        Fragment fragmentF = operationB.f();
                        if (Boolean.TRUE.equals(map.get(operationB))) {
                            if (FragmentManager.G0(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragmentF + " as this Fragment was involved in a Transition.");
                            }
                            kVar.a();
                        } else {
                            boolean z3 = operationB.e() == SpecialEffectsController.Operation.State.GONE;
                            if (z3) {
                                list2.remove(operationB);
                            }
                            View view = fragmentF.mView;
                            viewGroupM.startViewTransition(view);
                            animator.addListener(new c(viewGroupM, view, z3, operationB, kVar));
                            animator.setTarget(view);
                            animator.start();
                            kVar.c().c(new d(animator));
                            z2 = true;
                        }
                    }
                }
            }
        }
        for (k kVar2 : arrayList) {
            SpecialEffectsController.Operation operationB2 = kVar2.b();
            Fragment fragmentF2 = operationB2.f();
            if (z) {
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragmentF2 + " as Animations cannot run alongside Transitions.");
                }
                kVar2.a();
            } else if (z2) {
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragmentF2 + " as Animations cannot run alongside Animators.");
                }
                kVar2.a();
            } else {
                View view2 = fragmentF2.mView;
                Animation animation = (Animation) b.h.l.g.c(((c.d) b.h.l.g.c(kVar2.e(context))).f2803a);
                if (operationB2.e() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation);
                    kVar2.a();
                } else {
                    viewGroupM.startViewTransition(view2);
                    c.e eVar = new c.e(animation, viewGroupM, view2);
                    eVar.setAnimationListener(new e(viewGroupM, view2, kVar2));
                    view2.startAnimation(eVar);
                }
                kVar2.c().c(new f(view2, viewGroupM, kVar2));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<SpecialEffectsController.Operation, Boolean> x(List<m> list, List<SpecialEffectsController.Operation> list2, boolean z, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2) {
        Iterator<m> it;
        View view;
        Object obj;
        ArrayList<View> arrayList;
        Object obj2;
        ArrayList<View> arrayList2;
        SpecialEffectsController.Operation operation3;
        SpecialEffectsController.Operation operation4;
        View view2;
        Object objN;
        b.e.a aVar;
        ArrayList<View> arrayList3;
        SpecialEffectsController.Operation operation5;
        ArrayList<View> arrayList4;
        Rect rect;
        s sVar;
        SpecialEffectsController.Operation operation6;
        View view3;
        b.h.d.k enterTransitionCallback;
        b.h.d.k exitTransitionCallback;
        View view4;
        View view5;
        boolean z2 = z;
        SpecialEffectsController.Operation operation7 = operation;
        SpecialEffectsController.Operation operation8 = operation2;
        HashMap map = new HashMap();
        s sVar2 = null;
        for (m mVar : list) {
            if (!mVar.d()) {
                s sVarE = mVar.e();
                if (sVar2 == null) {
                    sVar2 = sVarE;
                } else if (sVarE != null && sVar2 != sVarE) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + mVar.b().f() + " returned Transition " + mVar.h() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (sVar2 == null) {
            for (m mVar2 : list) {
                map.put(mVar2.b(), Boolean.FALSE);
                mVar2.a();
            }
            return map;
        }
        View view6 = new View(m().getContext());
        Rect rect2 = new Rect();
        ArrayList<View> arrayList5 = new ArrayList<>();
        ArrayList<View> arrayList6 = new ArrayList<>();
        b.e.a aVar2 = new b.e.a();
        Object obj3 = null;
        View view7 = null;
        boolean z3 = false;
        for (m mVar3 : list) {
            if (!mVar3.i() || operation7 == null || operation8 == null) {
                aVar = aVar2;
                arrayList3 = arrayList6;
                operation5 = operation7;
                arrayList4 = arrayList5;
                rect = rect2;
                sVar = sVar2;
                operation6 = operation8;
                view3 = view6;
                view7 = view7;
            } else {
                Object objB = sVar2.B(sVar2.g(mVar3.g()));
                ArrayList<String> sharedElementSourceNames = operation2.f().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.f().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation.f().getSharedElementTargetNames();
                View view8 = view7;
                int i2 = 0;
                while (i2 < sharedElementTargetNames.size()) {
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i2));
                    ArrayList<String> arrayList7 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i2));
                    }
                    i2++;
                    sharedElementTargetNames = arrayList7;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.f().getSharedElementTargetNames();
                if (z2) {
                    enterTransitionCallback = operation.f().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.f().getExitTransitionCallback();
                } else {
                    enterTransitionCallback = operation.f().getExitTransitionCallback();
                    exitTransitionCallback = operation2.f().getEnterTransitionCallback();
                }
                int i3 = 0;
                for (int size = sharedElementSourceNames.size(); i3 < size; size = size) {
                    aVar2.put(sharedElementSourceNames.get(i3), sharedElementTargetNames2.get(i3));
                    i3++;
                }
                b.e.a<String, View> aVar3 = new b.e.a<>();
                u(aVar3, operation.f().mView);
                aVar3.o(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    throw null;
                }
                aVar2.o(aVar3.keySet());
                b.e.a<String, View> aVar4 = new b.e.a<>();
                u(aVar4, operation2.f().mView);
                aVar4.o(sharedElementTargetNames2);
                aVar4.o(aVar2.values());
                if (exitTransitionCallback != null) {
                    throw null;
                }
                q.x(aVar2, aVar4);
                v(aVar3, aVar2.keySet());
                v(aVar4, aVar2.values());
                if (aVar2.isEmpty()) {
                    arrayList5.clear();
                    arrayList6.clear();
                    operation5 = operation;
                    aVar = aVar2;
                    arrayList3 = arrayList6;
                    arrayList4 = arrayList5;
                    rect = rect2;
                    view3 = view6;
                    sVar = sVar2;
                    view7 = view8;
                    obj3 = null;
                    operation6 = operation2;
                } else {
                    q.f(operation2.f(), operation.f(), z2, aVar3, true);
                    HashMap map2 = map;
                    View view9 = view6;
                    Rect rect3 = rect2;
                    aVar = aVar2;
                    ArrayList<View> arrayList8 = arrayList6;
                    b.h.m.q.a(m(), new g(operation2, operation, z, aVar4));
                    Iterator<View> it2 = aVar3.values().iterator();
                    while (it2.hasNext()) {
                        t(arrayList5, it2.next());
                    }
                    if (sharedElementSourceNames.isEmpty()) {
                        view7 = view8;
                    } else {
                        View view10 = (View) aVar3.get(sharedElementSourceNames.get(0));
                        sVar2.v(objB, view10);
                        view7 = view10;
                    }
                    Iterator<View> it3 = aVar4.values().iterator();
                    while (it3.hasNext()) {
                        t(arrayList8, it3.next());
                    }
                    arrayList3 = arrayList8;
                    if (sharedElementTargetNames2.isEmpty() || (view5 = (View) aVar4.get(sharedElementTargetNames2.get(0))) == null) {
                        rect = rect3;
                        view4 = view9;
                    } else {
                        rect = rect3;
                        b.h.m.q.a(m(), new h(sVar2, view5, rect));
                        view4 = view9;
                        z3 = true;
                    }
                    sVar2.z(objB, view4, arrayList5);
                    arrayList4 = arrayList5;
                    view3 = view4;
                    sVar = sVar2;
                    sVar2.t(objB, null, null, null, null, objB, arrayList3);
                    Boolean bool = Boolean.TRUE;
                    operation5 = operation;
                    map = map2;
                    map.put(operation5, bool);
                    operation6 = operation2;
                    map.put(operation6, bool);
                    obj3 = objB;
                }
            }
            z2 = z;
            operation7 = operation5;
            arrayList5 = arrayList4;
            rect2 = rect;
            view6 = view3;
            operation8 = operation6;
            aVar2 = aVar;
            arrayList6 = arrayList3;
            sVar2 = sVar;
        }
        View view11 = view7;
        b.e.a aVar5 = aVar2;
        ArrayList<View> arrayList9 = arrayList6;
        SpecialEffectsController.Operation operation9 = operation7;
        ArrayList<View> arrayList10 = arrayList5;
        Rect rect4 = rect2;
        s sVar3 = sVar2;
        SpecialEffectsController.Operation operation10 = operation8;
        View view12 = view6;
        ArrayList arrayList11 = new ArrayList();
        Iterator<m> it4 = list.iterator();
        Object obj4 = null;
        Object objN2 = null;
        while (it4.hasNext()) {
            m next = it4.next();
            if (next.d()) {
                map.put(next.b(), Boolean.FALSE);
                next.a();
            } else {
                Object objG = sVar3.g(next.h());
                SpecialEffectsController.Operation operationB = next.b();
                boolean z4 = obj3 != null && (operationB == operation9 || operationB == operation10);
                if (objG == null) {
                    if (!z4) {
                        map.put(operationB, Boolean.FALSE);
                        next.a();
                    }
                    arrayList2 = arrayList9;
                    arrayList = arrayList10;
                    it = it4;
                    view = view12;
                    objN = obj4;
                    operation3 = operation10;
                    view2 = view11;
                } else {
                    it = it4;
                    ArrayList<View> arrayList12 = new ArrayList<>();
                    Object obj5 = obj4;
                    t(arrayList12, operationB.f().mView);
                    if (z4) {
                        if (operationB == operation9) {
                            arrayList12.removeAll(arrayList10);
                        } else {
                            arrayList12.removeAll(arrayList9);
                        }
                    }
                    if (arrayList12.isEmpty()) {
                        sVar3.a(objG, view12);
                        arrayList2 = arrayList9;
                        arrayList = arrayList10;
                        view = view12;
                        operation4 = operationB;
                        obj2 = objN2;
                        operation3 = operation10;
                        obj = obj5;
                    } else {
                        sVar3.b(objG, arrayList12);
                        view = view12;
                        obj = obj5;
                        arrayList = arrayList10;
                        obj2 = objN2;
                        arrayList2 = arrayList9;
                        operation3 = operation10;
                        sVar3.t(objG, objG, arrayList12, null, null, null, null);
                        if (operationB.e() == SpecialEffectsController.Operation.State.GONE) {
                            operation4 = operationB;
                            list2.remove(operation4);
                            sVar3.r(objG, operation4.f().mView, arrayList12);
                            b.h.m.q.a(m(), new i(arrayList12));
                        } else {
                            operation4 = operationB;
                        }
                    }
                    if (operation4.e() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList11.addAll(arrayList12);
                        if (z3) {
                            sVar3.u(objG, rect4);
                        }
                        view2 = view11;
                    } else {
                        view2 = view11;
                        sVar3.v(objG, view2);
                    }
                    map.put(operation4, Boolean.TRUE);
                    if (next.j()) {
                        objN2 = sVar3.n(obj2, objG, null);
                        objN = obj;
                    } else {
                        objN = sVar3.n(obj, objG, null);
                        objN2 = obj2;
                    }
                }
                operation10 = operation3;
                view11 = view2;
                obj4 = objN;
                view12 = view;
                arrayList10 = arrayList;
                arrayList9 = arrayList2;
                it4 = it;
            }
        }
        ArrayList<View> arrayList13 = arrayList9;
        ArrayList<View> arrayList14 = arrayList10;
        SpecialEffectsController.Operation operation11 = operation10;
        Object objM = sVar3.m(objN2, obj4, obj3);
        for (m mVar4 : list) {
            if (!mVar4.d()) {
                Object objH = mVar4.h();
                SpecialEffectsController.Operation operationB2 = mVar4.b();
                boolean z5 = obj3 != null && (operationB2 == operation9 || operationB2 == operation11);
                if (objH != null || z5) {
                    if (b.h.m.t.P(m())) {
                        sVar3.w(mVar4.b().f(), objM, mVar4.c(), new j(mVar4));
                    } else {
                        if (FragmentManager.G0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + m() + " has not been laid out. Completing operation " + operationB2);
                        }
                        mVar4.a();
                    }
                }
            }
        }
        if (!b.h.m.t.P(m())) {
            return map;
        }
        q.A(arrayList11, 4);
        ArrayList<String> arrayListO = sVar3.o(arrayList13);
        sVar3.c(m(), objM);
        sVar3.y(m(), arrayList14, arrayList13, arrayListO, aVar5);
        q.A(arrayList11, 0);
        sVar3.A(obj3, arrayList14, arrayList13);
        return map;
    }
}
