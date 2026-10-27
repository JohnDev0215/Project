package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import defpackage.am;
import defpackage.bq0;
import defpackage.cq0;
import defpackage.y70;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class d extends bq0 {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class b extends Transition.c {
        public final /* synthetic */ Rect a;

        public b(Rect rect) {
            this.a = rect;
        }

        @Override // androidx.transition.Transition.c
        public final Rect a() {
            Rect rect = this.a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return rect;
        }
    }

    public static boolean s(Transition transition) {
        return (bq0.h(transition.i) && bq0.h(null) && bq0.h(null)) ? false : true;
    }

    @Override // defpackage.bq0
    public final void a(View view, Object obj) {
        ((Transition) obj).e(view);
    }

    @Override // defpackage.bq0
    public final void b(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition == null) {
            return;
        }
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int size = transitionSet.C.size();
            while (i < size) {
                b((i < 0 || i >= transitionSet.C.size()) ? null : transitionSet.C.get(i), arrayList);
                i++;
            }
            return;
        }
        if (s(transition) || !bq0.h(transition.j)) {
            return;
        }
        int size2 = arrayList.size();
        while (i < size2) {
            transition.e(arrayList.get(i));
            i++;
        }
    }

    @Override // defpackage.bq0
    public final void c(ViewGroup viewGroup, Object obj) {
        h.a(viewGroup, (Transition) obj);
    }

    @Override // defpackage.bq0
    public final boolean e(Object obj) {
        return obj instanceof Transition;
    }

    @Override // defpackage.bq0
    public final Object f(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    @Override // defpackage.bq0
    public final Object i(Object obj, Object obj2, Object obj3) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition != null && transition2 != null) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.Q(transition);
            transitionSet.Q(transition2);
            transitionSet.U(1);
            transition = transitionSet;
        } else if (transition == null) {
            transition = transition2 != null ? transition2 : null;
        }
        if (transition3 == null) {
            return transition;
        }
        TransitionSet transitionSet2 = new TransitionSet();
        if (transition != null) {
            transitionSet2.Q(transition);
        }
        transitionSet2.Q(transition3);
        return transitionSet2;
    }

    @Override // defpackage.bq0
    public final Object j(Object obj, Object obj2) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.Q((Transition) obj);
        }
        transitionSet.Q((Transition) obj2);
        return transitionSet;
    }

    @Override // defpackage.bq0
    public final void k(Object obj, View view, ArrayList<View> arrayList) {
        ((Transition) obj).b(new a(view, arrayList));
    }

    @Override // defpackage.bq0
    public final void l(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2) {
        ((Transition) obj).b(new e(this, obj2, arrayList, obj3, arrayList2));
    }

    @Override // defpackage.bq0
    public final void m(View view, Object obj) {
        if (view != null) {
            Rect rect = new Rect();
            bq0.g(view, rect);
            ((Transition) obj).I(new c(rect));
        }
    }

    @Override // defpackage.bq0
    public final void n(Object obj, Rect rect) {
        ((Transition) obj).I(new b(rect));
    }

    @Override // defpackage.bq0
    public final void o(Object obj, am amVar, y70 y70Var) {
        Transition transition = (Transition) obj;
        amVar.setOnCancelListener(new cq0(transition));
        transition.b(new f(y70Var));
    }

    @Override // defpackage.bq0
    public final void p(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        ArrayList<View> arrayList2 = transitionSet.j;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bq0.d(arrayList2, arrayList.get(i));
        }
        arrayList2.add(view);
        arrayList.add(view);
        b(transitionSet, arrayList);
    }

    @Override // defpackage.bq0
    public final void q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            ArrayList<View> arrayList3 = transitionSet.j;
            arrayList3.clear();
            arrayList3.addAll(arrayList2);
            t(transitionSet, arrayList, arrayList2);
        }
    }

    @Override // defpackage.bq0
    public final Object r(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.Q((Transition) obj);
        return transitionSet;
    }

    public final void t(Object obj, @SuppressLint({"UnknownNullness"}) ArrayList<View> arrayList, @SuppressLint({"UnknownNullness"}) ArrayList<View> arrayList2) {
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int size = transitionSet.C.size();
            while (i < size) {
                t((i < 0 || i >= transitionSet.C.size()) ? null : transitionSet.C.get(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (s(transition)) {
            return;
        }
        ArrayList<View> arrayList3 = transition.j;
        if (arrayList3.size() != arrayList.size() || !arrayList3.containsAll(arrayList)) {
            return;
        }
        int size2 = arrayList2 == null ? 0 : arrayList2.size();
        while (i < size2) {
            transition.e(arrayList2.get(i));
            i++;
        }
        int size3 = arrayList.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                return;
            } else {
                transition.D(arrayList.get(size3));
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class a implements Transition.d {
        public final /* synthetic */ View e;
        public final /* synthetic */ ArrayList f;

        public a(View view, ArrayList arrayList) {
            this.e = view;
            this.f = arrayList;
        }

        @Override // androidx.transition.Transition.d
        public final void b(Transition transition) {
            transition.C(this);
            transition.b(this);
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            transition.C(this);
            this.e.setVisibility(8);
            ArrayList arrayList = this.f;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((View) arrayList.get(i)).setVisibility(0);
            }
        }

        @Override // androidx.transition.Transition.d
        public final void a() {
        }

        @Override // androidx.transition.Transition.d
        public final void c() {
        }

        @Override // androidx.transition.Transition.d
        public final void e() {
        }
    }
}
