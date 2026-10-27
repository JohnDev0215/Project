package c.n;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionManager.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static m f2480a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ThreadLocal<WeakReference<c.d.a<ViewGroup, ArrayList<m>>>> f2481b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static ArrayList<ViewGroup> f2482c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        m f2483b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ViewGroup f2484c;

        /* JADX INFO: renamed from: c.n.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        class C0077a extends n {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.d.a f2485a;

            C0077a(c.d.a aVar) {
                this.f2485a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // c.n.m.f
            public void e(m mVar) {
                ((ArrayList) this.f2485a.get(a.this.f2484c)).remove(mVar);
                mVar.P(this);
            }
        }

        a(m mVar, ViewGroup viewGroup) {
            this.f2483b = mVar;
            this.f2484c = viewGroup;
        }

        private void a() {
            this.f2484c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f2484c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!o.f2482c.remove(this.f2484c)) {
                return true;
            }
            c.d.a<ViewGroup, ArrayList<m>> aVarB = o.b();
            ArrayList<m> arrayList = aVarB.get(this.f2484c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f2484c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f2483b);
            this.f2483b.a(new C0077a(aVarB));
            this.f2483b.k(this.f2484c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((m) it.next()).R(this.f2484c);
                }
            }
            this.f2483b.O(this.f2484c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            o.f2482c.remove(this.f2484c);
            ArrayList<m> arrayList = o.b().get(this.f2484c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f2484c);
                }
            }
            this.f2483b.l(true);
        }
    }

    public static void a(ViewGroup viewGroup, m mVar) {
        if (f2482c.contains(viewGroup) || !c.g.k.t.P(viewGroup)) {
            return;
        }
        f2482c.add(viewGroup);
        if (mVar == null) {
            mVar = f2480a;
        }
        m mVarClone = mVar.clone();
        d(viewGroup, mVarClone);
        l.c(viewGroup, null);
        c(viewGroup, mVarClone);
    }

    static c.d.a<ViewGroup, ArrayList<m>> b() {
        c.d.a<ViewGroup, ArrayList<m>> aVar;
        WeakReference<c.d.a<ViewGroup, ArrayList<m>>> weakReference = f2481b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        c.d.a<ViewGroup, ArrayList<m>> aVar2 = new c.d.a<>();
        f2481b.set(new WeakReference<>(aVar2));
        return aVar2;
    }

    private static void c(ViewGroup viewGroup, m mVar) {
        if (mVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(mVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void d(ViewGroup viewGroup, m mVar) {
        ArrayList<m> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<m> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().N(viewGroup);
            }
        }
        if (mVar != null) {
            mVar.k(viewGroup, true);
        }
        l lVarB = l.b(viewGroup);
        if (lVarB != null) {
            lVarB.a();
        }
    }
}
