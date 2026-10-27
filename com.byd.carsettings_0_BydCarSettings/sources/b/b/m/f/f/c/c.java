package b.b.m.f.f.c;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: WrapRecyclerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2159a = "c";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f2164f;
    public RecyclerView.Adapter g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2160b = 1000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2161c = 2000;
    public RecyclerView.AdapterDataObserver h = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<View> f2162d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<View> f2163e = new SparseArray<>();

    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class b extends RecyclerView.ViewHolder {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: renamed from: b.b.m.f.f.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class C0088c extends GridLayoutManager.SpanSizeLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GridLayoutManager f2167a;

        public C0088c(GridLayoutManager gridLayoutManager) {
            this.f2167a = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            if (c.this.o(i) || c.this.n(i)) {
                return this.f2167a.getSpanCount();
            }
            return 1;
        }
    }

    public c(RecyclerView.Adapter adapter) {
        this.g = adapter;
        adapter.registerAdapterDataObserver(this.h);
    }

    public void g(View view) {
        r(this.f2164f);
        this.f2164f = view;
        p();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.g.getItemCount() + this.f2162d.size() + this.f2163e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (o(i)) {
            return this.f2162d.keyAt(i);
        }
        if (n(i)) {
            return this.f2163e.keyAt((i - this.f2162d.size()) - this.g.getItemCount());
        }
        return this.g.getItemViewType(i - this.f2162d.size());
    }

    public void i(View view) {
        if (this.f2163e.indexOfValue(view) == -1) {
            SparseArray<View> sparseArray = this.f2163e;
            int i = this.f2161c;
            this.f2161c = i + 1;
            sparseArray.put(i, view);
        }
        notifyDataSetChanged();
    }

    public void j(View view) {
        if (this.f2162d.indexOfValue(view) == -1) {
            SparseArray<View> sparseArray = this.f2162d;
            int i = this.f2160b;
            this.f2160b = i + 1;
            sparseArray.put(i, view);
        }
        notifyDataSetChanged();
    }

    public void k(RecyclerView recyclerView, boolean z) {
        if (z && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            gridLayoutManager.setSpanSizeLookup(new C0088c(gridLayoutManager));
        }
    }

    public final RecyclerView.ViewHolder l(View view) {
        return new b(view);
    }

    public final boolean n(int i) {
        return i >= this.f2162d.size() + this.g.getItemCount();
    }

    public final boolean o(int i) {
        return i < this.f2162d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (o(viewHolder.getAdapterPosition()) || n(viewHolder.getAdapterPosition())) {
            return;
        }
        this.g.onBindViewHolder(viewHolder, viewHolder.getAdapterPosition() - this.f2162d.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return this.f2162d.indexOfKey(i) >= 0 ? l(this.f2162d.get(i)) : this.f2163e.indexOfKey(i) >= 0 ? l(this.f2163e.get(i)) : this.g.onCreateViewHolder(viewGroup, i);
    }

    public final void p() {
        if (this.f2164f == null) {
            return;
        }
        if (this.g.getItemCount() == 0) {
            j(this.f2164f);
        } else {
            r(this.f2164f);
        }
    }

    public void q(View view) {
        int iIndexOfValue = this.f2163e.indexOfValue(view);
        if (iIndexOfValue == -1) {
            return;
        }
        this.f2163e.removeAt(iIndexOfValue);
        notifyDataSetChanged();
    }

    public void r(View view) {
        int iIndexOfValue = this.f2162d.indexOfValue(view);
        if (iIndexOfValue == -1) {
            return;
        }
        this.f2162d.removeAt(iIndexOfValue);
        notifyDataSetChanged();
    }

    public void s() {
        try {
            unregisterAdapterDataObserver(this.h);
        } catch (Exception e2) {
            Log.e(f2159a, "unregisterAdapterDataObserver failed.", e2);
        }
    }

    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class a extends RecyclerView.AdapterDataObserver {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            if (c.this.g == null) {
                return;
            }
            if (!(c.this.g instanceof c)) {
                c.this.notifyDataSetChanged();
            }
            c.this.p();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            if (c.this.g == null) {
                return;
            }
            if (!(c.this.g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemChanged(i + cVar.f2162d.size());
            }
            c.this.p();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            if (c.this.g == null) {
                return;
            }
            if (!(c.this.g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemInserted(i + cVar.f2162d.size());
            }
            c.this.p();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            if (c.this.g == null) {
                return;
            }
            if (!(c.this.g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemMoved(i + cVar.f2162d.size(), i2 + c.this.f2162d.size());
            }
            c.this.p();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            if (c.this.g == null) {
                return;
            }
            if (!(c.this.g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemRemoved(i + cVar.f2162d.size());
            }
            c.this.p();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            if (c.this.g == null) {
                return;
            }
            if (!(c.this.g instanceof c)) {
                c cVar = c.this;
                cVar.notifyItemChanged(i + cVar.f2162d.size(), obj);
            }
            c.this.p();
        }
    }
}
