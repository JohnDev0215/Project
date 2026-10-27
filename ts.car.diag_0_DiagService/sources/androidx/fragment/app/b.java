package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.b;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.SpecialEffectsController;
import androidx.fragment.app.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class b extends SpecialEffectsController {
    private final HashMap<SpecialEffectsController.Operation, HashSet<androidx.core.os.b>> f;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f656c;
        final /* synthetic */ SpecialEffectsController.Operation d;

        a(List list, SpecialEffectsController.Operation operation) {
            this.f656c = list;
            this.d = operation;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f656c.contains(this.d)) {
                this.f656c.remove(this.d);
                b.this.q(this.d);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0021b implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ SpecialEffectsController.Operation f657a;

        C0021b(SpecialEffectsController.Operation operation) {
            this.f657a = operation;
        }

        @Override // androidx.core.os.b.a
        public void a() {
            b.this.r(this.f657a);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f659a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f660b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ SpecialEffectsController.Operation f661c;
        final /* synthetic */ androidx.core.os.b d;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                cVar.f659a.endViewTransition(cVar.f660b);
                c cVar2 = c.this;
                b.this.u(cVar2.f661c, cVar2.d);
            }
        }

        c(ViewGroup viewGroup, View view, SpecialEffectsController.Operation operation, androidx.core.os.b bVar) {
            this.f659a = viewGroup;
            this.f660b = view;
            this.f661c = operation;
            this.d = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f659a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f663a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f664b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ SpecialEffectsController.Operation f665c;
        final /* synthetic */ androidx.core.os.b d;

        d(ViewGroup viewGroup, View view, SpecialEffectsController.Operation operation, androidx.core.os.b bVar) {
            this.f663a = viewGroup;
            this.f664b = view;
            this.f665c = operation;
            this.d = bVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f663a.endViewTransition(this.f664b);
            b.this.u(this.f665c, this.d);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f666a;

        e(b bVar, View view) {
            this.f666a = view;
        }

        @Override // androidx.core.os.b.a
        public void a() {
            this.f666a.clearAnimation();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ u f667c;
        final /* synthetic */ View d;
        final /* synthetic */ Rect e;

        f(b bVar, u uVar, View view, Rect rect) {
            this.f667c = uVar;
            this.d = view;
            this.e = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f667c.k(this.d, this.e);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ j f668c;

        g(j jVar) {
            this.f668c = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.u(this.f668c.c(), this.f668c.e());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static /* synthetic */ class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f669a;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.Type.values().length];
            f669a = iArr;
            try {
                iArr[SpecialEffectsController.Operation.Type.HIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f669a[SpecialEffectsController.Operation.Type.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f669a[SpecialEffectsController.Operation.Type.SHOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f669a[SpecialEffectsController.Operation.Type.ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @NonNull
        private final SpecialEffectsController.Operation f670a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @NonNull
        private final androidx.core.os.b f671b;

        i(@NonNull SpecialEffectsController.Operation operation, @NonNull androidx.core.os.b bVar) {
            this.f670a = operation;
            this.f671b = bVar;
        }

        @NonNull
        SpecialEffectsController.Operation a() {
            return this.f670a;
        }

        @NonNull
        androidx.core.os.b b() {
            return this.f671b;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @NonNull
        private final SpecialEffectsController.Operation f672a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @NonNull
        private final androidx.core.os.b f673b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        private final Object f674c;
        private final boolean d;

        @Nullable
        private final Object e;

        j(@NonNull SpecialEffectsController.Operation operation, @NonNull androidx.core.os.b bVar, boolean z, boolean z2) {
            this.f672a = operation;
            this.f673b = bVar;
            if (operation.e() == SpecialEffectsController.Operation.Type.ADD || operation.e() == SpecialEffectsController.Operation.Type.SHOW) {
                this.f674c = z ? operation.d().C() : operation.d().s();
                this.d = z ? operation.d().l() : operation.d().m();
            } else {
                this.f674c = z ? operation.d().E() : operation.d().u();
                this.d = true;
            }
            if (!z2) {
                this.e = null;
            } else if (z) {
                this.e = operation.d().G();
            } else {
                this.e = operation.d().F();
            }
        }

        @Nullable
        private u b(Object obj) {
            if (obj == null) {
                return null;
            }
            u uVar = s.f718b;
            if (uVar != null && uVar.e(obj)) {
                return s.f718b;
            }
            u uVar2 = s.f719c;
            if (uVar2 != null && uVar2.e(obj)) {
                return s.f719c;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.f672a.d() + " is not a valid framework Transition or AndroidX Transition");
        }

        @Nullable
        u a() {
            u uVarB = b(this.f674c);
            u uVarB2 = b(this.e);
            if (uVarB == null || uVarB2 == null || uVarB == uVarB2) {
                return uVarB != null ? uVarB : uVarB2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.f672a.d() + " returned Transition " + this.f674c + " which uses a different Transition  type than its shared element transition " + this.e);
        }

        @NonNull
        SpecialEffectsController.Operation c() {
            return this.f672a;
        }

        @Nullable
        public Object d() {
            return this.e;
        }

        @NonNull
        androidx.core.os.b e() {
            return this.f673b;
        }

        @Nullable
        Object f() {
            return this.f674c;
        }

        public boolean g() {
            return this.e != null;
        }

        boolean h() {
            return this.d;
        }
    }

    b(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
        this.f = new HashMap<>();
    }

    private void p(@NonNull SpecialEffectsController.Operation operation, @NonNull androidx.core.os.b bVar) {
        if (this.f.get(operation) == null) {
            this.f.put(operation, new HashSet<>());
        }
        this.f.get(operation).add(bVar);
    }

    private void v(@NonNull SpecialEffectsController.Operation operation, @NonNull androidx.core.os.b bVar) {
        ViewGroup viewGroupK = k();
        Context context = viewGroupK.getContext();
        Fragment fragmentD = operation.d();
        View view = fragmentD.J;
        d.C0023d c0023dB = androidx.fragment.app.d.b(context, fragmentD, operation.e() == SpecialEffectsController.Operation.Type.ADD || operation.e() == SpecialEffectsController.Operation.Type.SHOW);
        if (c0023dB == null) {
            u(operation, bVar);
            return;
        }
        viewGroupK.startViewTransition(view);
        if (c0023dB.f689a != null) {
            Animation fVar = (operation.e() == SpecialEffectsController.Operation.Type.ADD || operation.e() == SpecialEffectsController.Operation.Type.SHOW) ? new d.f(c0023dB.f689a) : new d.e(c0023dB.f689a, viewGroupK, view);
            fVar.setAnimationListener(new c(viewGroupK, view, operation, bVar));
            view.startAnimation(fVar);
        } else {
            c0023dB.f690b.addListener(new d(viewGroupK, view, operation, bVar));
            c0023dB.f690b.setTarget(view);
            c0023dB.f690b.start();
        }
        bVar.c(new e(this, view));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void w(@NonNull List<j> list, boolean z, @Nullable SpecialEffectsController.Operation operation, @Nullable SpecialEffectsController.Operation operation2) {
        Object obj;
        View view;
        Iterator<j> it;
        ArrayList<String> arrayListI;
        ArrayList<String> arrayListH;
        View view2;
        View view3;
        SpecialEffectsController.Operation operation3 = operation2;
        u uVar = null;
        for (j jVar : list) {
            u uVarA = jVar.a();
            if (uVar == null) {
                uVar = uVarA;
            } else if (uVarA != null && uVar != uVarA) {
                throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + jVar.c().d() + " returned Transition " + jVar.f() + " which uses a different Transition  type than other Fragments.");
            }
        }
        if (uVar == null) {
            for (j jVar2 : list) {
                u(jVar2.c(), jVar2.e());
            }
            return;
        }
        View view4 = new View(k().getContext());
        Rect rect = new Rect();
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        b.a.a aVar = new b.a.a();
        Iterator<j> it2 = list.iterator();
        View view5 = null;
        boolean z2 = false;
        Object objB = null;
        while (it2.hasNext()) {
            j next = it2.next();
            if (!next.g() || operation == null || operation3 == null) {
                it = it2;
                view5 = view5;
            } else {
                objB = uVar.B(uVar.g(next.d()));
                Fragment fragmentD = next.c().d();
                if (z) {
                    arrayListI = fragmentD.I();
                    arrayListH = fragmentD.H();
                } else {
                    arrayListI = fragmentD.H();
                    arrayListH = fragmentD.I();
                }
                int size = arrayListI.size();
                int i2 = 0;
                while (i2 < size) {
                    int i3 = size;
                    ArrayList<String> arrayList3 = arrayListI;
                    aVar.put(arrayList3.get(i2), arrayListH.get(i2));
                    i2++;
                    arrayListI = arrayList3;
                    size = i3;
                    it2 = it2;
                    view5 = view5;
                }
                it = it2;
                View view6 = view5;
                ArrayList<String> arrayList4 = arrayListI;
                b.a.a aVar2 = new b.a.a();
                t(aVar2, operation.d().J);
                aVar2.p(arrayList4);
                aVar.p(aVar2.keySet());
                b.a.a aVar3 = new b.a.a();
                t(aVar3, operation2.d().J);
                aVar3.p(arrayListH);
                s.y(aVar, aVar3);
                aVar2.p(aVar.keySet());
                aVar3.p(aVar.values());
                if (aVar.isEmpty()) {
                    arrayList.clear();
                    arrayList2.clear();
                    view5 = view6;
                    objB = null;
                } else {
                    for (Iterator it3 = aVar2.values().iterator(); it3.hasNext(); it3 = it3) {
                        s(arrayList, (View) it3.next());
                    }
                    if (arrayList4.isEmpty()) {
                        view2 = view6;
                    } else {
                        view2 = (View) aVar2.get(arrayList4.get(0));
                        uVar.v(objB, view2);
                    }
                    Iterator it4 = aVar3.values().iterator();
                    while (it4.hasNext()) {
                        s(arrayList2, (View) it4.next());
                    }
                    if (!arrayListH.isEmpty() && (view3 = (View) aVar3.get(arrayListH.get(0))) != null) {
                        androidx.core.view.q.a(k(), new f(this, uVar, view3, rect));
                        z2 = true;
                    }
                    uVar.b(objB, arrayList);
                    view5 = view2;
                }
            }
            it2 = it;
        }
        View view7 = view5;
        ArrayList arrayList5 = new ArrayList();
        Iterator<j> it5 = list.iterator();
        Object objN = null;
        Object objN2 = null;
        while (it5.hasNext()) {
            j next2 = it5.next();
            Iterator<j> it6 = it5;
            Object objG = uVar.g(next2.f());
            b.a.a aVar4 = aVar;
            SpecialEffectsController.Operation operationC = next2.c();
            boolean z3 = objB != null && (operationC == operation || operationC == operation3);
            if (objG == null) {
                if (!z3) {
                    u(next2.c(), next2.e());
                }
                obj = objB;
                view = view7;
            } else {
                ArrayList<View> arrayList6 = new ArrayList<>();
                obj = objB;
                s(arrayList6, next2.c().d().J);
                if (z3) {
                    if (operationC == operation) {
                        arrayList6.removeAll(arrayList);
                    } else {
                        arrayList6.removeAll(arrayList2);
                    }
                }
                if (arrayList6.isEmpty()) {
                    uVar.a(objG, view4);
                } else {
                    uVar.b(objG, arrayList6);
                }
                if (next2.c().e().equals(SpecialEffectsController.Operation.Type.ADD)) {
                    arrayList5.addAll(arrayList6);
                    if (z2) {
                        uVar.u(objG, rect);
                    }
                    view = view7;
                } else {
                    view = view7;
                    uVar.v(objG, view);
                }
                if (next2.h()) {
                    objN = uVar.n(objN, objG, null);
                } else {
                    objN2 = uVar.n(objN2, objG, null);
                }
            }
            it5 = it6;
            view7 = view;
            aVar = aVar4;
            objB = obj;
            operation3 = operation2;
        }
        b.a.a aVar5 = aVar;
        Object objM = uVar.m(objN, objN2, objB);
        for (j jVar3 : list) {
            if (jVar3.f() != null) {
                uVar.w(jVar3.c().d(), objM, jVar3.e(), new g(jVar3));
            }
        }
        s.B(arrayList5, 4);
        ArrayList<String> arrayListO = uVar.o(arrayList2);
        uVar.c(k(), objM);
        uVar.y(k(), arrayList, arrayList2, arrayListO, aVar5);
        s.B(arrayList5, 0);
        uVar.A(objB, arrayList, arrayList2);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0072  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void g(@androidx.annotation.NonNull java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r11, boolean r12) {
        /*
            r10 = this;
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r2 = r1
        L6:
            boolean r3 = r0.hasNext()
            r4 = 1
            if (r3 == 0) goto L31
            java.lang.Object r3 = r0.next()
            androidx.fragment.app.SpecialEffectsController$Operation r3 = (androidx.fragment.app.SpecialEffectsController.Operation) r3
            int[] r5 = androidx.fragment.app.b.h.f669a
            androidx.fragment.app.SpecialEffectsController$Operation$Type r6 = r3.e()
            int r6 = r6.ordinal()
            r5 = r5[r6]
            if (r5 == r4) goto L2d
            r4 = 2
            if (r5 == r4) goto L2d
            r4 = 3
            if (r5 == r4) goto L2b
            r4 = 4
            if (r5 == r4) goto L2b
            goto L6
        L2b:
            r2 = r3
            goto L6
        L2d:
            if (r1 != 0) goto L6
            r1 = r3
            goto L6
        L31:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r11)
            java.util.Iterator r11 = r11.iterator()
        L44:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto L8e
            java.lang.Object r6 = r11.next()
            androidx.fragment.app.SpecialEffectsController$Operation r6 = (androidx.fragment.app.SpecialEffectsController.Operation) r6
            androidx.core.os.b r7 = new androidx.core.os.b
            r7.<init>()
            r10.p(r6, r7)
            androidx.fragment.app.b$i r8 = new androidx.fragment.app.b$i
            r8.<init>(r6, r7)
            r0.add(r8)
            androidx.core.os.b r7 = new androidx.core.os.b
            r7.<init>()
            r10.p(r6, r7)
            androidx.fragment.app.b$j r8 = new androidx.fragment.app.b$j
            r9 = 0
            if (r12 == 0) goto L70
            if (r6 != r1) goto L73
            goto L72
        L70:
            if (r6 != r2) goto L73
        L72:
            r9 = r4
        L73:
            r8.<init>(r6, r7, r12, r9)
            r3.add(r8)
            androidx.fragment.app.b$a r7 = new androidx.fragment.app.b$a
            r7.<init>(r5, r6)
            r6.a(r7)
            androidx.core.os.b r7 = r6.c()
            androidx.fragment.app.b$b r8 = new androidx.fragment.app.b$b
            r8.<init>(r6)
            r7.c(r8)
            goto L44
        L8e:
            r10.w(r3, r12, r1, r2)
            java.util.Iterator r11 = r0.iterator()
        L95:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Lad
            java.lang.Object r12 = r11.next()
            androidx.fragment.app.b$i r12 = (androidx.fragment.app.b.i) r12
            androidx.fragment.app.SpecialEffectsController$Operation r0 = r12.a()
            androidx.core.os.b r12 = r12.b()
            r10.v(r0, r12)
            goto L95
        Lad:
            java.util.Iterator r11 = r5.iterator()
        Lb1:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Lc1
            java.lang.Object r12 = r11.next()
            androidx.fragment.app.SpecialEffectsController$Operation r12 = (androidx.fragment.app.SpecialEffectsController.Operation) r12
            r10.q(r12)
            goto Lb1
        Lc1:
            r5.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b.g(java.util.List, boolean):void");
    }

    void q(@NonNull SpecialEffectsController.Operation operation) {
        View view = operation.d().J;
        int i2 = h.f669a[operation.e().ordinal()];
        if (i2 == 1) {
            view.setVisibility(8);
            return;
        }
        if (i2 == 2) {
            k().removeView(view);
        } else if (i2 == 3 || i2 == 4) {
            view.setVisibility(0);
        }
    }

    void r(@NonNull SpecialEffectsController.Operation operation) {
        HashSet<androidx.core.os.b> hashSetRemove = this.f.remove(operation);
        if (hashSetRemove != null) {
            Iterator<androidx.core.os.b> it = hashSetRemove.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    void s(ArrayList<View> arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (androidx.core.view.t.a(viewGroup)) {
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                s(arrayList, childAt);
            }
        }
    }

    void t(Map<String, View> map, @NonNull View view) {
        String strV = ViewCompat.v(view);
        if (strV != null) {
            map.put(strV, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    t(map, childAt);
                }
            }
        }
    }

    void u(@NonNull SpecialEffectsController.Operation operation, @NonNull androidx.core.os.b bVar) {
        HashSet<androidx.core.os.b> hashSet = this.f.get(operation);
        if (hashSet != null && hashSet.remove(bVar) && hashSet.isEmpty()) {
            this.f.remove(operation);
            operation.b();
        }
    }
}
