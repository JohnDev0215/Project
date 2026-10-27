package b.q;

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
    public static l f1499a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ThreadLocal<WeakReference<b.e.a<ViewGroup, ArrayList<l>>>> f1500b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ArrayList<ViewGroup> f1501c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public l f1502b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ViewGroup f1503c;

        /* JADX INFO: renamed from: b.q.n$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0040a extends m {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b.e.a f1504a;

            public C0040a(b.e.a aVar) {
                this.f1504a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // b.q.l.f
            public void e(l lVar) {
                ((ArrayList) this.f1504a.get(a.this.f1503c)).remove(lVar);
                lVar.P(this);
            }
        }

        public a(l lVar, ViewGroup viewGroup) {
            this.f1502b = lVar;
            this.f1503c = viewGroup;
        }

        public final void a() {
            this.f1503c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f1503c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!n.f1501c.remove(this.f1503c)) {
                return true;
            }
            b.e.a<ViewGroup, ArrayList<l>> aVarB = n.b();
            ArrayList<l> arrayList = aVarB.get(this.f1503c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f1503c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f1502b);
            this.f1502b.a(new C0040a(aVarB));
            this.f1502b.k(this.f1503c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((l) it.next()).R(this.f1503c);
                }
            }
            this.f1502b.O(this.f1503c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            n.f1501c.remove(this.f1503c);
            ArrayList<l> arrayList = n.b().get(this.f1503c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<l> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f1503c);
                }
            }
            this.f1502b.l(true);
        }
    }

    public static void a(ViewGroup viewGroup, l lVar) {
        if (f1501c.contains(viewGroup) || !b.g.j.t.P(viewGroup)) {
            return;
        }
        f1501c.add(viewGroup);
        if (lVar == null) {
            lVar = f1499a;
        }
        l lVarClone = lVar.clone();
        d(viewGroup, lVarClone);
        k.c(viewGroup, null);
        c(viewGroup, lVarClone);
    }

    public static b.e.a<ViewGroup, ArrayList<l>> b() {
        b.e.a<ViewGroup, ArrayList<l>> aVar;
        WeakReference<b.e.a<ViewGroup, ArrayList<l>>> weakReference = f1500b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        b.e.a<ViewGroup, ArrayList<l>> aVar2 = new b.e.a<>();
        f1500b.set(new WeakReference<>(aVar2));
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
