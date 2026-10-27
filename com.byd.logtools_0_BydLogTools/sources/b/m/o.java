package b.m;

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
    private static m f2756a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ThreadLocal<WeakReference<b.d.a<ViewGroup, ArrayList<m>>>> f2757b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static ArrayList<ViewGroup> f2758c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        m f2759b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ViewGroup f2760c;

        /* JADX INFO: renamed from: b.m.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        class C0050a extends n {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ b.d.a f2761a;

            C0050a(b.d.a aVar) {
                this.f2761a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // b.m.m.f
            public void e(m mVar) {
                ((ArrayList) this.f2761a.get(a.this.f2760c)).remove(mVar);
                mVar.P(this);
            }
        }

        a(m mVar, ViewGroup viewGroup) {
            this.f2759b = mVar;
            this.f2760c = viewGroup;
        }

        private void a() {
            this.f2760c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f2760c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!o.f2758c.remove(this.f2760c)) {
                return true;
            }
            b.d.a<ViewGroup, ArrayList<m>> aVarB = o.b();
            ArrayList<m> arrayList = aVarB.get(this.f2760c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f2760c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f2759b);
            this.f2759b.a(new C0050a(aVarB));
            this.f2759b.k(this.f2760c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((m) it.next()).R(this.f2760c);
                }
            }
            this.f2759b.O(this.f2760c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            o.f2758c.remove(this.f2760c);
            ArrayList<m> arrayList = o.b().get(this.f2760c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f2760c);
                }
            }
            this.f2759b.l(true);
        }
    }

    public static void a(ViewGroup viewGroup, m mVar) {
        if (f2758c.contains(viewGroup) || !b.g.k.t.P(viewGroup)) {
            return;
        }
        f2758c.add(viewGroup);
        if (mVar == null) {
            mVar = f2756a;
        }
        m mVarClone = mVar.clone();
        d(viewGroup, mVarClone);
        l.c(viewGroup, null);
        c(viewGroup, mVarClone);
    }

    static b.d.a<ViewGroup, ArrayList<m>> b() {
        b.d.a<ViewGroup, ArrayList<m>> aVar;
        WeakReference<b.d.a<ViewGroup, ArrayList<m>>> weakReference = f2757b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        b.d.a<ViewGroup, ArrayList<m>> aVar2 = new b.d.a<>();
        f2757b.set(new WeakReference<>(aVar2));
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
