package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import defpackage.he2;
import defpackage.in3;
import defpackage.m8;
import defpackage.om3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: TransitionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static final AutoTransition a = new AutoTransition();
    public static final ThreadLocal<WeakReference<m8<ViewGroup, ArrayList<Transition>>>> b = new ThreadLocal<>();
    public static final ArrayList<ViewGroup> c = new ArrayList<>();

    public static void a(ViewGroup viewGroup, Transition transition) {
        ArrayList<ViewGroup> arrayList = c;
        if (arrayList.contains(viewGroup)) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.c(viewGroup)) {
            arrayList.add(viewGroup);
            if (transition == null) {
                transition = a;
            }
            Transition transitionClone = transition.clone();
            ArrayList<Transition> arrayList2 = b().get(viewGroup);
            if (arrayList2 != null && arrayList2.size() > 0) {
                Iterator<Transition> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().B(viewGroup);
                }
            }
            if (transitionClone != null) {
                transitionClone.m(viewGroup, true);
            }
            int i = R$id.transition_current_scene;
            if (((he2) viewGroup.getTag(i)) != null) {
                throw null;
            }
            viewGroup.setTag(i, null);
            if (transitionClone != null) {
                a aVar = new a(viewGroup, transitionClone);
                viewGroup.addOnAttachStateChangeListener(aVar);
                viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
            }
        }
    }

    public static m8<ViewGroup, ArrayList<Transition>> b() {
        m8<ViewGroup, ArrayList<Transition>> m8Var;
        ThreadLocal<WeakReference<m8<ViewGroup, ArrayList<Transition>>>> threadLocal = b;
        WeakReference<m8<ViewGroup, ArrayList<Transition>>> weakReference = threadLocal.get();
        if (weakReference != null && (m8Var = weakReference.get()) != null) {
            return m8Var;
        }
        m8<ViewGroup, ArrayList<Transition>> m8Var2 = new m8<>();
        threadLocal.set(new WeakReference<>(m8Var2));
        return m8Var2;
    }

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        public final Transition e;
        public final ViewGroup f;

        /* JADX INFO: renamed from: androidx.transition.h$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0030a extends g {
            public final /* synthetic */ m8 e;

            public C0030a(m8 m8Var) {
                this.e = m8Var;
            }

            @Override // androidx.transition.Transition.d
            public final void d(Transition transition) {
                ((ArrayList) this.e.get(a.this.f)).remove(transition);
                transition.C(this);
            }
        }

        public a(ViewGroup viewGroup, Transition transition) {
            this.e = transition;
            this.f = viewGroup;
        }

        /* JADX WARN: Removed duplicated region for block: B:106:0x022b  */
        /* JADX WARN: Removed duplicated region for block: B:141:0x01d6 A[EDGE_INSN: B:141:0x01d6->B:89:0x01d6 BREAK  A[LOOP:1: B:19:0x0086->B:88:0x01cf], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x008b  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x01dc  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01fd  */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean onPreDraw() {
            /*
                Method dump skipped, instruction units count: 682
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.transition.h.a.onPreDraw():boolean");
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            ViewGroup viewGroup = this.f;
            viewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
            viewGroup.removeOnAttachStateChangeListener(this);
            h.c.remove(viewGroup);
            ArrayList<Transition> arrayList = h.b().get(viewGroup);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Transition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().E(viewGroup);
                }
            }
            this.e.n(true);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }
    }
}
