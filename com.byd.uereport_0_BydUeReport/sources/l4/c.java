package l4;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: WrapRecyclerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends RecyclerView.g<RecyclerView.c0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6277a = "c";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f6282f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RecyclerView.g f6283g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6278b = 1000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6279c = 2000;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RecyclerView.i f6284h = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<View> f6280d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<View> f6281e = new SparseArray<>();

    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class b extends RecyclerView.c0 {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: renamed from: l4.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class C0084c extends GridLayoutManager.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ GridLayoutManager f6287e;

        public C0084c(GridLayoutManager gridLayoutManager) {
            this.f6287e = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i8) {
            if (c.this.l(i8) || c.this.k(i8)) {
                return this.f6287e.k();
            }
            return 1;
        }
    }

    public c(RecyclerView.g gVar) {
        this.f6283g = gVar;
        gVar.registerAdapterDataObserver(this.f6284h);
    }

    public void f(View view) {
        o(this.f6282f);
        this.f6282f = view;
        m();
    }

    public void g(View view) {
        if (this.f6281e.indexOfValue(view) == -1) {
            SparseArray<View> sparseArray = this.f6281e;
            int i8 = this.f6279c;
            this.f6279c = i8 + 1;
            sparseArray.put(i8, view);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.f6283g.getItemCount() + this.f6280d.size() + this.f6281e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i8) {
        if (l(i8)) {
            return this.f6280d.keyAt(i8);
        }
        if (k(i8)) {
            return this.f6281e.keyAt((i8 - this.f6280d.size()) - this.f6283g.getItemCount());
        }
        return this.f6283g.getItemViewType(i8 - this.f6280d.size());
    }

    public void h(View view) {
        if (this.f6280d.indexOfValue(view) == -1) {
            SparseArray<View> sparseArray = this.f6280d;
            int i8 = this.f6278b;
            this.f6278b = i8 + 1;
            sparseArray.put(i8, view);
        }
        notifyDataSetChanged();
    }

    public void i(RecyclerView recyclerView, boolean z7) {
        if (z7 && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            gridLayoutManager.s(new C0084c(gridLayoutManager));
        }
    }

    public final RecyclerView.c0 j(View view) {
        return new b(view);
    }

    public final boolean k(int i8) {
        return i8 >= this.f6280d.size() + this.f6283g.getItemCount();
    }

    public final boolean l(int i8) {
        return i8 < this.f6280d.size();
    }

    public final void m() {
        if (this.f6282f == null) {
            return;
        }
        if (this.f6283g.getItemCount() == 0) {
            h(this.f6282f);
        } else {
            o(this.f6282f);
        }
    }

    public void n(View view) {
        int iIndexOfValue = this.f6281e.indexOfValue(view);
        if (iIndexOfValue == -1) {
            return;
        }
        this.f6281e.removeAt(iIndexOfValue);
        notifyDataSetChanged();
    }

    public void o(View view) {
        int iIndexOfValue = this.f6280d.indexOfValue(view);
        if (iIndexOfValue == -1) {
            return;
        }
        this.f6280d.removeAt(iIndexOfValue);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, int i8) {
        if (l(c0Var.getAdapterPosition()) || k(c0Var.getAdapterPosition())) {
            return;
        }
        this.f6283g.onBindViewHolder(c0Var, c0Var.getAdapterPosition() - this.f6280d.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.c0 onCreateViewHolder(ViewGroup viewGroup, int i8) {
        return this.f6280d.indexOfKey(i8) >= 0 ? j(this.f6280d.get(i8)) : this.f6281e.indexOfKey(i8) >= 0 ? j(this.f6281e.get(i8)) : this.f6283g.onCreateViewHolder(viewGroup, i8);
    }

    public void p() {
        try {
            unregisterAdapterDataObserver(this.f6284h);
        } catch (Exception e8) {
            Log.e(f6277a, "unregisterAdapterDataObserver failed.", e8);
        }
    }

    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class a extends RecyclerView.i {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void onChanged() {
            if (c.this.f6283g == null) {
                return;
            }
            if (!(c.this.f6283g instanceof c)) {
                c.this.notifyDataSetChanged();
            }
            c.this.m();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void onItemRangeChanged(int i8, int i9) {
            if (c.this.f6283g == null) {
                return;
            }
            if (!(c.this.f6283g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemChanged(i8 + cVar.f6280d.size());
            }
            c.this.m();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void onItemRangeInserted(int i8, int i9) {
            if (c.this.f6283g == null) {
                return;
            }
            if (!(c.this.f6283g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemInserted(i8 + cVar.f6280d.size());
            }
            c.this.m();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void onItemRangeMoved(int i8, int i9, int i10) {
            if (c.this.f6283g == null) {
                return;
            }
            if (!(c.this.f6283g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemMoved(i8 + cVar.f6280d.size(), i9 + c.this.f6280d.size());
            }
            c.this.m();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void onItemRangeRemoved(int i8, int i9) {
            if (c.this.f6283g == null) {
                return;
            }
            if (!(c.this.f6283g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemRemoved(i8 + cVar.f6280d.size());
            }
            c.this.m();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void onItemRangeChanged(int i8, int i9, Object obj) {
            if (c.this.f6283g == null) {
                return;
            }
            if (!(c.this.f6283g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemChanged(i8 + cVar.f6280d.size(), obj);
            }
            c.this.m();
        }
    }
}
