package org.chromium.components.browser_ui.widget.dragreorder;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import defpackage.es3;
import defpackage.gv;
import defpackage.in3;
import defpackage.om3;
import defpackage.pd0;
import defpackage.yp;
import defpackage.z93;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$integer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DragReorderableListAdapter<T> extends RecyclerView.e<RecyclerView.a0> {
    public final Context h;
    public l i;
    public ArrayList j;
    public RecyclerView k;
    public final int l;
    public final float m;
    public pd0 n;
    public int o;
    public final org.chromium.base.b<a> p = new org.chromium.base.b<>();

    public interface a {
        void b(boolean z);
    }

    public DragReorderableListAdapter(Context context) {
        this.h = context;
        Resources resources = context.getResources();
        this.l = gv.g(yp.c(context, R$dimen.default_elevation_1), resources.getInteger(R$integer.list_item_dragged_alpha));
        this.m = resources.getDimension(R$dimen.list_item_dragged_elevation);
    }

    public final void D() {
        l lVar = this.i;
        if (lVar != null) {
            lVar.j(null);
        }
    }

    public abstract boolean E(RecyclerView.a0 a0Var);

    public abstract boolean F(RecyclerView.a0 a0Var);

    public abstract void G(ArrayList arrayList);

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.j.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void s(RecyclerView recyclerView) {
        this.k = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void w(RecyclerView recyclerView) {
        this.k = null;
    }

    public class b extends l.d {
        public RecyclerView.a0 c;

        public b() {
        }

        @Override // androidx.recyclerview.widget.l.d
        public final boolean a(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2) {
            DragReorderableListAdapter dragReorderableListAdapter = DragReorderableListAdapter.this;
            return dragReorderableListAdapter.F(a0Var) && dragReorderableListAdapter.F(a0Var2);
        }

        @Override // androidx.recyclerview.widget.l.d
        public final void b(RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            super.b(recyclerView, a0Var);
            int iF = a0Var.f();
            DragReorderableListAdapter dragReorderableListAdapter = DragReorderableListAdapter.this;
            if (iF != dragReorderableListAdapter.o && recyclerView.isAttachedToWindow()) {
                recyclerView.post(new es3(5, this));
            }
            this.c = null;
            Iterator<a> it = dragReorderableListAdapter.p.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    k(a0Var, false);
                    return;
                }
                ((a) aVar.next()).b(false);
            }
        }

        @Override // androidx.recyclerview.widget.l.d
        public final int c(RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            RecyclerView.a0 a0Var2 = this.c;
            return ((a0Var2 == a0Var || a0Var2 == null) && DragReorderableListAdapter.this.E(a0Var)) ? 196611 : 0;
        }

        @Override // androidx.recyclerview.widget.l.d
        public final boolean f() {
            return DragReorderableListAdapter.this.n.a();
        }

        @Override // androidx.recyclerview.widget.l.d
        public final boolean h(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2) {
            int iF = a0Var.f();
            int iF2 = a0Var2.f();
            if (iF == iF2) {
                return false;
            }
            DragReorderableListAdapter dragReorderableListAdapter = DragReorderableListAdapter.this;
            Collections.swap(dragReorderableListAdapter.j, iF, iF2);
            dragReorderableListAdapter.p(iF, iF2);
            return true;
        }

        @Override // androidx.recyclerview.widget.l.d
        public final void i(RecyclerView.a0 a0Var, int i) {
            if (i != 2 || this.c == a0Var) {
                return;
            }
            this.c = a0Var;
            int iF = a0Var.f();
            DragReorderableListAdapter dragReorderableListAdapter = DragReorderableListAdapter.this;
            dragReorderableListAdapter.o = iF;
            Iterator<a> it = dragReorderableListAdapter.p.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    k(a0Var, true);
                    return;
                }
                ((a) aVar.next()).b(true);
            }
        }

        public final void k(RecyclerView.a0 a0Var, boolean z) {
            in3 in3VarB = om3.b(a0Var.e);
            float f = z ? DragReorderableListAdapter.this.m : 0.0f;
            WeakReference<View> weakReference = in3VarB.a;
            View view = weakReference.get();
            if (view != null) {
                view.animate().translationZ(f);
            }
            z93 z93Var = new z93(this, 2, a0Var, z);
            View view2 = weakReference.get();
            if (view2 != null) {
                view2.animate().withEndAction(z93Var);
            }
            in3VarB.c(100L);
            View view3 = weakReference.get();
            if (view3 != null) {
                view3.animate().start();
            }
        }

        @Override // androidx.recyclerview.widget.l.d
        public final void j(RecyclerView.a0 a0Var) {
        }
    }
}
