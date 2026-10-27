package b.x;

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
    public static m f3302a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ThreadLocal<WeakReference<b.e.a<ViewGroup, ArrayList<m>>>> f3303b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ArrayList<ViewGroup> f3304c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public m f3305b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ViewGroup f3306c;

        /* JADX INFO: renamed from: b.x.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0066a extends n {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b.e.a f3307a;

            public C0066a(b.e.a aVar) {
                this.f3307a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // b.x.m.f
            public void e(m mVar) {
                ((ArrayList) this.f3307a.get(a.this.f3306c)).remove(mVar);
                mVar.Q(this);
            }
        }

        public a(m mVar, ViewGroup viewGroup) {
            this.f3305b = mVar;
            this.f3306c = viewGroup;
        }

        public final void a() {
            this.f3306c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f3306c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!o.f3304c.remove(this.f3306c)) {
                return true;
            }
            b.e.a<ViewGroup, ArrayList<m>> aVarB = o.b();
            ArrayList<m> arrayList = aVarB.get(this.f3306c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarB.put(this.f3306c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f3305b);
            this.f3305b.a(new C0066a(aVarB));
            this.f3305b.j(this.f3306c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((m) it.next()).S(this.f3306c);
                }
            }
            this.f3305b.P(this.f3306c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            o.f3304c.remove(this.f3306c);
            ArrayList<m> arrayList = o.b().get(this.f3306c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().S(this.f3306c);
                }
            }
            this.f3305b.k(true);
        }
    }

    public static void a(ViewGroup viewGroup, m mVar) {
        if (f3304c.contains(viewGroup) || !b.h.m.t.P(viewGroup)) {
            return;
        }
        f3304c.add(viewGroup);
        if (mVar == null) {
            mVar = f3302a;
        }
        m mVarClone = mVar.clone();
        d(viewGroup, mVarClone);
        l.c(viewGroup, null);
        c(viewGroup, mVarClone);
    }

    public static b.e.a<ViewGroup, ArrayList<m>> b() {
        b.e.a<ViewGroup, ArrayList<m>> aVar;
        WeakReference<b.e.a<ViewGroup, ArrayList<m>>> weakReference = f3303b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        b.e.a<ViewGroup, ArrayList<m>> aVar2 = new b.e.a<>();
        f3303b.set(new WeakReference<>(aVar2));
        return aVar2;
    }

    public static void c(ViewGroup viewGroup, m mVar) {
        if (mVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(mVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    public static void d(ViewGroup viewGroup, m mVar) {
        ArrayList<m> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<m> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().O(viewGroup);
            }
        }
        if (mVar != null) {
            mVar.j(viewGroup, true);
        }
        l lVarB = l.b(viewGroup);
        if (lVarB != null) {
            lVarB.a();
        }
    }
}
