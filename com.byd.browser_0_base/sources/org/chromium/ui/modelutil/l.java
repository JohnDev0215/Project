package org.chromium.ui.modelutil;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.a0;
import java.util.Iterator;
import java.util.List;
import org.chromium.ui.modelutil.d;

/* JADX INFO: compiled from: RecyclerViewAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public class l<VH extends RecyclerView.a0, P> extends RecyclerView.e<VH> implements d.a<P> {
    public final a<VH, P> h;
    public final b<VH> i;

    /* JADX INFO: compiled from: RecyclerViewAdapter.java */
    public interface b<VH> {
        Object j(int i, RecyclerView recyclerView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public l(a<VH, P> aVar, b<VH> bVar) {
        this.h = aVar;
        this.i = bVar;
        ((e) aVar).g(this);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void b(int i, int i2) {
        q(i, i2);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void f(int i, int i2) {
        p(i, i2);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void i(int i, int i2, Object obj) {
        this.e.d(i, i2, obj);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void j(int i, int i2) {
        r(i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h.d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i) {
        return this.h.getItemViewType(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(VH vh, int i) {
        this.h.a(vh, i, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void u(VH vh, int i, List<Object> list) {
        if (list.isEmpty()) {
            t(vh, i);
            return;
        }
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            this.h.a(vh, i, it.next());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        return (RecyclerView.a0) this.i.j(i, recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void z(VH vh) {
        this.h.c(vh);
    }

    /* JADX INFO: compiled from: RecyclerViewAdapter.java */
    public interface a<VH, P> extends d<P> {
        void a(VH vh, int i, P p);

        int d();

        int getItemViewType(int i);

        default void c(VH vh) {
        }
    }
}
