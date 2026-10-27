package a.n;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static m f577a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ThreadLocal<WeakReference<a.d.a<ViewGroup, ArrayList<m>>>> f578b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static ArrayList<ViewGroup> f579c = new ArrayList<>();

    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        m f580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ViewGroup f581c;

        /* JADX INFO: renamed from: a.n.o$a$a, reason: collision with other inner class name */
        class C0025a extends n {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ a.d.a f582a;

            C0025a(a.d.a aVar) {
                this.f582a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // a.n.m.f
            public void e(m mVar) {
                ((ArrayList) this.f582a.get(a.this.f581c)).remove(mVar);
                mVar.P(this);
            }
        }

        a(m mVar, ViewGroup viewGroup) {
            this.f580b = mVar;
            this.f581c = viewGroup;
        }

        private void a() {
            this.f581c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f581c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!o.f579c.remove(this.f581c)) {
                return true;
            }
            a.d.a<ViewGroup, ArrayList<m>> aVarC = o.c();
            ArrayList<m> arrayList = aVarC.get(this.f581c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                aVarC.put(this.f581c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f580b);
            this.f580b.a(new C0025a(aVarC));
            this.f580b.k(this.f581c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((m) it.next()).R(this.f581c);
                }
            }
            this.f580b.O(this.f581c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            o.f579c.remove(this.f581c);
            ArrayList<m> arrayList = o.c().get(this.f581c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().R(this.f581c);
                }
            }
            this.f580b.l(true);
        }
    }

    public static void a(ViewGroup viewGroup) {
        b(viewGroup, null);
    }

    public static void b(ViewGroup viewGroup, m mVar) {
        if (f579c.contains(viewGroup) || !a.g.k.t.N(viewGroup)) {
            return;
        }
        f579c.add(viewGroup);
        if (mVar == null) {
            mVar = f577a;
        }
        m mVarClone = mVar.clone();
        e(viewGroup, mVarClone);
        l.c(viewGroup, null);
        d(viewGroup, mVarClone);
    }

    static a.d.a<ViewGroup, ArrayList<m>> c() {
        a.d.a<ViewGroup, ArrayList<m>> aVar;
        WeakReference<a.d.a<ViewGroup, ArrayList<m>>> weakReference = f578b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        a.d.a<ViewGroup, ArrayList<m>> aVar2 = new a.d.a<>();
        f578b.set(new WeakReference<>(aVar2));
        return aVar2;
    }

    private static void d(ViewGroup viewGroup, m mVar) {
        if (mVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(mVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void e(ViewGroup viewGroup, m mVar) {
        ArrayList<m> arrayList = c().get(viewGroup);
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
