package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ae {
    public static yd a = new od();
    public static ThreadLocal<WeakReference<a4<ViewGroup, ArrayList<yd>>>> b = new ThreadLocal<>();
    public static ArrayList<ViewGroup> c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        public yd b;
        public ViewGroup c;

        /* JADX INFO: renamed from: ae$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0000a extends zd {
            public final /* synthetic */ a4 a;

            public C0000a(a4 a4Var) {
                this.a = a4Var;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // yd.f
            public void e(yd ydVar) {
                ((ArrayList) this.a.get(a.this.c)).remove(ydVar);
                ydVar.P(this);
            }
        }

        public a(yd ydVar, ViewGroup viewGroup) {
            this.b = ydVar;
            this.c = viewGroup;
        }

        public final void a() {
            this.c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!ae.c.remove(this.c)) {
                return true;
            }
            a4<ViewGroup, ArrayList<yd>> a4VarB = ae.b();
            ArrayList<yd> arrayList = a4VarB.get(this.c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                a4VarB.put(this.c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.b);
            this.b.a(new C0000a(a4VarB));
            this.b.k(this.c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((yd) it.next()).R(this.c);
                }
            }
            this.b.O(this.c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            ae.c.remove(this.c);
            ArrayList<yd> arrayList = ae.b().get(this.c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<yd> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.c);
                }
            }
            this.b.l(true);
        }
    }

    public static void a(ViewGroup viewGroup, yd ydVar) {
        if (c.contains(viewGroup) || !t9.P(viewGroup)) {
            return;
        }
        c.add(viewGroup);
        if (ydVar == null) {
            ydVar = a;
        }
        yd ydVarClone = ydVar.clone();
        d(viewGroup, ydVarClone);
        xd.c(viewGroup, null);
        c(viewGroup, ydVarClone);
    }

    public static a4<ViewGroup, ArrayList<yd>> b() {
        a4<ViewGroup, ArrayList<yd>> a4Var;
        WeakReference<a4<ViewGroup, ArrayList<yd>>> weakReference = b.get();
        if (weakReference != null && (a4Var = weakReference.get()) != null) {
            return a4Var;
        }
        a4<ViewGroup, ArrayList<yd>> a4Var2 = new a4<>();
        b.set(new WeakReference<>(a4Var2));
        return a4Var2;
    }

    public static void c(ViewGroup viewGroup, yd ydVar) {
        if (ydVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(ydVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    public static void d(ViewGroup viewGroup, yd ydVar) {
        ArrayList<yd> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<yd> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().N(viewGroup);
            }
        }
        if (ydVar != null) {
            ydVar.k(viewGroup, true);
        }
        xd xdVarB = xd.b(viewGroup);
        if (xdVarB != null) {
            xdVarB.a();
        }
    }
}
