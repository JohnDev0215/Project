package k1;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionManager.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static l f5809a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ThreadLocal<WeakReference<v.a<ViewGroup, ArrayList<l>>>> f5810b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ArrayList<ViewGroup> f5811c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public l f5812d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f5813e;

        /* JADX INFO: renamed from: k1.n$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0071a extends m {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ v.a f5814d;

            public C0071a(v.a aVar) {
                this.f5814d = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // k1.l.f
            public void e(l lVar) {
                ((ArrayList) this.f5814d.get(a.this.f5813e)).remove(lVar);
                lVar.P(this);
            }
        }

        public a(l lVar, ViewGroup viewGroup) {
            this.f5812d = lVar;
            this.f5813e = viewGroup;
        }

        public final void a() {
            this.f5813e.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f5813e.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!n.f5811c.remove(this.f5813e)) {
                return true;
            }
            v.a<ViewGroup, ArrayList<l>> aVarB = n.b();
            ArrayList<l> arrayList = aVarB.get(this.f5813e);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f5813e, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f5812d);
            this.f5812d.a(new C0071a(aVarB));
            this.f5812d.k(this.f5813e, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((l) it.next()).R(this.f5813e);
                }
            }
            this.f5812d.O(this.f5813e);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            n.f5811c.remove(this.f5813e);
            ArrayList<l> arrayList = n.b().get(this.f5813e);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<l> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f5813e);
                }
            }
            this.f5812d.l(true);
        }
    }

    public static void a(ViewGroup viewGroup, l lVar) {
        if (f5811c.contains(viewGroup) || !o0.t.Q(viewGroup)) {
            return;
        }
        f5811c.add(viewGroup);
        if (lVar == null) {
            lVar = f5809a;
        }
        l lVarClone = lVar.clone();
        d(viewGroup, lVarClone);
        k.c(viewGroup, null);
        c(viewGroup, lVarClone);
    }

    public static v.a<ViewGroup, ArrayList<l>> b() {
        v.a<ViewGroup, ArrayList<l>> aVar;
        WeakReference<v.a<ViewGroup, ArrayList<l>>> weakReference = f5810b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        v.a<ViewGroup, ArrayList<l>> aVar2 = new v.a<>();
        f5810b.set(new WeakReference<>(aVar2));
        return aVar2;
    }

    public static void c(ViewGroup viewGroup, l lVar) {
        if (lVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(lVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    public static void d(ViewGroup viewGroup, l lVar) {
        ArrayList<l> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<l> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().N(viewGroup);
            }
        }
        if (lVar != null) {
            lVar.k(viewGroup, true);
        }
        k kVarB = k.b(viewGroup);
        if (kVarB != null) {
            kVarB.a();
        }
    }
}
