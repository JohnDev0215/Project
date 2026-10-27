package e.a.a;

import a.k.g;
import a.k.n;
import a.k.o;
import a.n.g;
import a.n.k;
import a.n.l;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import e.a.a.a;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c<T> extends RecyclerView.e<RecyclerView.b0> implements e.a.a.b<T> {
    public static final Object k = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e.a.a.e<? super T> f2519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e<T> f2520d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<T> f2521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LayoutInflater f2522f;
    public InterfaceC0059c<? super T> g;
    public d h;
    public RecyclerView i;
    public k j;

    public class a extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.b0 f2523a;

        public a(RecyclerView.b0 b0Var) {
            this.f2523a = b0Var;
        }
    }

    public static class b extends RecyclerView.b0 {
        public b(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.f1545f);
        }
    }

    /* JADX INFO: renamed from: e.a.a.c$c, reason: collision with other inner class name */
    public interface InterfaceC0059c<T> {
        long a(int i, T t);
    }

    public interface d {
        RecyclerView.b0 a(ViewDataBinding viewDataBinding);
    }

    public static class e<T> extends n.a<n<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<c<T>> f2525a;

        public e(c<T> cVar, n<T> nVar) {
            a.c cVar2 = e.a.a.a.f2516b;
            if (cVar2 == null || !cVar2.isAlive()) {
                a.c cVar3 = new a.c(null);
                e.a.a.a.f2516b = cVar3;
                cVar3.start();
            }
            this.f2525a = new a.b(cVar, nVar, this);
        }

        @Override // a.k.n.a
        public void d(n nVar) {
            c<T> cVar = this.f2525a.get();
            if (cVar == null) {
                return;
            }
            c.b.j.a.b();
            cVar.f1636a.b();
        }

        @Override // a.k.n.a
        public void e(n nVar, int i, int i2) {
            c<T> cVar = this.f2525a.get();
            if (cVar == null) {
                return;
            }
            c.b.j.a.b();
            cVar.f1636a.d(i, i2, null);
        }

        @Override // a.k.n.a
        public void f(n nVar, int i, int i2) {
            c<T> cVar = this.f2525a.get();
            if (cVar == null) {
                return;
            }
            c.b.j.a.b();
            cVar.f1636a.e(i, i2);
        }

        @Override // a.k.n.a
        public void g(n nVar, int i, int i2, int i3) {
            c<T> cVar = this.f2525a.get();
            if (cVar == null) {
                return;
            }
            c.b.j.a.b();
            for (int i4 = 0; i4 < i3; i4++) {
                cVar.f1636a.c(i + i4, i2 + i4);
            }
        }

        @Override // a.k.n.a
        public void h(n nVar, int i, int i2) {
            c<T> cVar = this.f2525a.get();
            if (cVar == null) {
                return;
            }
            c.b.j.a.b();
            cVar.f1636a.f(i, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public int a() {
        List<T> list = this.f2521e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public long b(int i) {
        InterfaceC0059c<? super T> interfaceC0059c = this.g;
        return interfaceC0059c == null ? i : interfaceC0059c.a(i, this.f2521e.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public int c(int i) {
        e.a.a.e<? super T> eVar = this.f2519c;
        T t = this.f2521e.get(i);
        f<? super T> fVar = eVar.f2528a;
        if (fVar != null) {
            eVar.f2529b = -1;
            eVar.f2530c = 0;
            fVar.a(eVar, i, t);
            if (eVar.f2529b == -1) {
                throw new IllegalStateException("variableId not set in onItemBind()");
            }
            if (eVar.f2530c == 0) {
                throw new IllegalStateException("layoutRes not set in onItemBind()");
            }
        }
        return this.f2519c.f2530c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public void e(RecyclerView recyclerView) {
        if (this.i == null) {
            List<T> list = this.f2521e;
            if (list instanceof n) {
                e<T> eVar = new e<>(this, (n) list);
                this.f2520d = eVar;
                ((n) this.f2521e).b(eVar);
            }
        }
        this.i = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void f(RecyclerView.b0 b0Var, int i) {
        g(b0Var, i, Collections.emptyList());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public void g(RecyclerView.b0 b0Var, int i, List<Object> list) {
        boolean z;
        k kVar;
        ViewDataBinding viewDataBindingD = g.d(b0Var.f1629a);
        boolean z2 = true;
        if (list == null || list.size() == 0) {
            z = false;
            break;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != k) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            viewDataBindingD.l();
            return;
        }
        T t = this.f2521e.get(i);
        int i3 = this.f2519c.f2529b;
        k kVar2 = this.j;
        if (kVar2 == null || ((l) kVar2.a()).f1094b == g.b.DESTROYED) {
            RecyclerView recyclerView = this.i;
            ViewDataBinding viewDataBindingC = a.k.g.c(recyclerView);
            k kVar3 = viewDataBindingC != null ? viewDataBindingC.l : null;
            Object context = recyclerView.getContext();
            if (kVar3 == null && (context instanceof k)) {
                kVar3 = (k) context;
            }
            this.j = kVar3;
        }
        e.a.a.e<? super T> eVar = this.f2519c;
        int i4 = eVar.f2529b;
        if (i4 == 0) {
            z2 = false;
        } else {
            if (!viewDataBindingD.v(i4, t)) {
                int i5 = eVar.f2529b;
                throw new IllegalStateException("Could not bind variable '" + a.k.g.f917a.b(i5) + "' in layout '" + viewDataBindingD.f1545f.getContext().getResources().getResourceName(eVar.f2530c) + "'");
            }
            SparseArray<Object> sparseArray = eVar.f2531d;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i6 = 0; i6 < size; i6++) {
                    int iKeyAt = eVar.f2531d.keyAt(i6);
                    Object objValueAt = eVar.f2531d.valueAt(i6);
                    if (iKeyAt != 0) {
                        viewDataBindingD.v(iKeyAt, objValueAt);
                    }
                }
            }
        }
        if (z2) {
            viewDataBindingD.l();
            k kVar4 = this.j;
            if (kVar4 == null || (kVar = viewDataBindingD.l) == kVar4) {
                return;
            }
            if (kVar != null) {
                ((l) kVar.a()).f1093a.g(viewDataBindingD.m);
            }
            viewDataBindingD.l = kVar4;
            if (viewDataBindingD.m == null) {
                viewDataBindingD.m = new ViewDataBinding.OnStartListener(viewDataBindingD, null);
            }
            kVar4.a().a(viewDataBindingD.m);
            for (ViewDataBinding.i iVar : viewDataBindingD.f1544e) {
                if (iVar != null) {
                    iVar.f1549a.b(kVar4);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.b0 h(ViewGroup viewGroup, int i) {
        if (this.f2522f == null) {
            this.f2522f = LayoutInflater.from(viewGroup.getContext());
        }
        ViewDataBinding viewDataBindingA = a.k.g.a(a.k.g.f918b, this.f2522f.inflate(i, viewGroup, false), i);
        d dVar = this.h;
        RecyclerView.b0 b0VarA = dVar != null ? dVar.a(viewDataBindingA) : new b(viewDataBindingA);
        a aVar = new a(b0VarA);
        if (viewDataBindingA.g == null) {
            viewDataBindingA.g = new a.k.c<>(ViewDataBinding.r);
        }
        viewDataBindingA.g.a(aVar);
        return b0VarA;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public void i(RecyclerView recyclerView) {
        if (this.i != null) {
            List<T> list = this.f2521e;
            if (list instanceof n) {
                ((n) list).a(this.f2520d);
                this.f2520d = null;
            }
        }
        this.i = null;
    }

    public void j(List<T> list) {
        List<T> list2 = this.f2521e;
        if (list2 == list) {
            return;
        }
        if (this.i != null) {
            if (list2 instanceof n) {
                ((n) list2).a(this.f2520d);
                this.f2520d = null;
            }
            if (list instanceof n) {
                n nVar = (n) list;
                e<T> eVar = new e<>(this, nVar);
                this.f2520d = eVar;
                nVar.b(eVar);
            }
        }
        this.f2521e = list;
        this.f1636a.b();
    }
}
