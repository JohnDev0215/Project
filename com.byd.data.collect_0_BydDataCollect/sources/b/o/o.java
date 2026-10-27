package b.o;

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
    private static m f2906a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ThreadLocal<WeakReference<b.d.a<ViewGroup, ArrayList<m>>>> f2907b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static ArrayList<ViewGroup> f2908c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        m f2909b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ViewGroup f2910c;

        /* JADX INFO: renamed from: b.o.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        class C0057a extends n {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ b.d.a f2911a;

            C0057a(b.d.a aVar) {
                this.f2911a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // b.o.m.f
            public void e(m mVar) {
                ((ArrayList) this.f2911a.get(a.this.f2910c)).remove(mVar);
                mVar.T(this);
            }
        }

        a(m mVar, ViewGroup viewGroup) {
            this.f2909b = mVar;
            this.f2910c = viewGroup;
        }

        private void a() {
            this.f2910c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f2910c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!o.f2908c.remove(this.f2910c)) {
                return true;
            }
            b.d.a<ViewGroup, ArrayList<m>> aVarB = o.b();
            ArrayList<m> arrayList = aVarB.get(this.f2910c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f2910c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f2909b);
            this.f2909b.b(new C0057a(aVarB));
            this.f2909b.m(this.f2910c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((m) it.next()).V(this.f2910c);
                }
            }
            this.f2909b.S(this.f2910c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            o.f2908c.remove(this.f2910c);
            ArrayList<m> arrayList = o.b().get(this.f2910c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().V(this.f2910c);
                }
            }
            this.f2909b.n(true);
        }
    }

    public static void a(ViewGroup viewGroup, m mVar) {
        if (f2908c.contains(viewGroup) || !b.g.k.t.P(viewGroup)) {
            return;
        }
        f2908c.add(viewGroup);
        if (mVar == null) {
            mVar = f2906a;
        }
        m mVarClone = mVar.clone();
        d(viewGroup, mVarClone);
        l.c(viewGroup, null);
        c(viewGroup, mVarClone);
    }

    static b.d.a<ViewGroup, ArrayList<m>> b() {
        b.d.a<ViewGroup, ArrayList<m>> aVar;
        WeakReference<b.d.a<ViewGroup, ArrayList<m>>> weakReference = f2907b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        b.d.a<ViewGroup, ArrayList<m>> aVar2 = new b.d.a<>();
        f2907b.set(new WeakReference<>(aVar2));
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
                it.next().R(viewGroup);
            }
        }
        if (mVar != null) {
            mVar.m(viewGroup, true);
        }
        l lVarB = l.b(viewGroup);
        if (lVarB != null) {
            lVarB.a();
        }
    }
}
