package y0;

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
    public static l f5967a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ThreadLocal<WeakReference<s.a<ViewGroup, ArrayList<l>>>> f5968b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ArrayList<ViewGroup> f5969c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public l f5970b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ViewGroup f5971c;

        /* JADX INFO: renamed from: y0.n$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0077a extends m {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ s.a f5972a;

            public C0077a(s.a aVar) {
                this.f5972a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // y0.l.f
            public void e(l lVar) {
                ((ArrayList) this.f5972a.get(a.this.f5971c)).remove(lVar);
                lVar.P(this);
            }
        }

        public a(l lVar, ViewGroup viewGroup) {
            this.f5970b = lVar;
            this.f5971c = viewGroup;
        }

        public final void a() {
            this.f5971c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f5971c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!n.f5969c.remove(this.f5971c)) {
                return true;
            }
            s.a<ViewGroup, ArrayList<l>> aVarB = n.b();
            ArrayList<l> arrayList = aVarB.get(this.f5971c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f5971c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f5970b);
            this.f5970b.a(new C0077a(aVarB));
            this.f5970b.k(this.f5971c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((l) it.next()).R(this.f5971c);
                }
            }
            this.f5970b.O(this.f5971c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            n.f5969c.remove(this.f5971c);
            ArrayList<l> arrayList = n.b().get(this.f5971c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<l> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f5971c);
                }
            }
            this.f5970b.l(true);
        }
    }

    public static void a(ViewGroup viewGroup, l lVar) {
        if (f5969c.contains(viewGroup) || !l0.t.P(viewGroup)) {
            return;
        }
        f5969c.add(viewGroup);
        if (lVar == null) {
            lVar = f5967a;
        }
        l lVarClone = lVar.clone();
        d(viewGroup, lVarClone);
        k.c(viewGroup, null);
        c(viewGroup, lVarClone);
    }

    public static s.a<ViewGroup, ArrayList<l>> b() {
        s.a<ViewGroup, ArrayList<l>> aVar;
        WeakReference<s.a<ViewGroup, ArrayList<l>>> weakReference = f5968b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        s.a<ViewGroup, ArrayList<l>> aVar2 = new s.a<>();
        f5968b.set(new WeakReference<>(aVar2));
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
