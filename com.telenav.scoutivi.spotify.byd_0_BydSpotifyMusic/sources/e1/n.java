package e1;

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
    private static l f6147a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ThreadLocal<WeakReference<n.a<ViewGroup, ArrayList<l>>>> f6148b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static ArrayList<ViewGroup> f6149c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        l f6150e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        ViewGroup f6151g;

        /* JADX INFO: renamed from: e1.n$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        class C0070a extends m {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ n.a f6152a;

            C0070a(n.a aVar) {
                this.f6152a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // e1.l.f
            public void e(l lVar) {
                ((ArrayList) this.f6152a.get(a.this.f6151g)).remove(lVar);
                lVar.P(this);
            }
        }

        a(l lVar, ViewGroup viewGroup) {
            this.f6150e = lVar;
            this.f6151g = viewGroup;
        }

        private void a() {
            this.f6151g.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f6151g.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!n.f6149c.remove(this.f6151g)) {
                return true;
            }
            n.a<ViewGroup, ArrayList<l>> aVarB = n.b();
            ArrayList<l> arrayList = aVarB.get(this.f6151g);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f6151g, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f6150e);
            this.f6150e.a(new C0070a(aVarB));
            this.f6150e.j(this.f6151g, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((l) it.next()).R(this.f6151g);
                }
            }
            this.f6150e.O(this.f6151g);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            n.f6149c.remove(this.f6151g);
            ArrayList<l> arrayList = n.b().get(this.f6151g);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<l> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f6151g);
                }
            }
            this.f6150e.k(true);
        }
    }

    public static void a(ViewGroup viewGroup, l lVar) {
        if (f6149c.contains(viewGroup) || !i0.u.P(viewGroup)) {
            return;
        }
        f6149c.add(viewGroup);
        if (lVar == null) {
            lVar = f6147a;
        }
        l lVarClone = lVar.clone();
        d(viewGroup, lVarClone);
        k.c(viewGroup, null);
        c(viewGroup, lVarClone);
    }

    static n.a<ViewGroup, ArrayList<l>> b() {
        n.a<ViewGroup, ArrayList<l>> aVar;
        WeakReference<n.a<ViewGroup, ArrayList<l>>> weakReference = f6148b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        n.a<ViewGroup, ArrayList<l>> aVar2 = new n.a<>();
        f6148b.set(new WeakReference<>(aVar2));
        return aVar2;
    }

    private static void c(ViewGroup viewGroup, l lVar) {
        if (lVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(lVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void d(ViewGroup viewGroup, l lVar) {
        ArrayList<l> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<l> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().N(viewGroup);
            }
        }
        if (lVar != null) {
            lVar.j(viewGroup, true);
        }
        k kVarB = k.b(viewGroup);
        if (kVarB != null) {
            kVarB.a();
        }
    }
}
