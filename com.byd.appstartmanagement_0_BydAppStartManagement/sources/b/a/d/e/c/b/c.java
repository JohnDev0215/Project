package b.a.d.e.c.b;

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
    public static final String f349a = "c";
    public View f;
    public RecyclerView.Adapter g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f350b = 1000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f351c = 2000;
    public RecyclerView.AdapterDataObserver h = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<View> f352d = new SparseArray<>();
    public SparseArray<View> e = new SparseArray<>();

    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class b extends RecyclerView.ViewHolder {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: renamed from: b.a.d.e.c.b.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: WrapRecyclerAdapter.java */
    public class C0024c extends GridLayoutManager.SpanSizeLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GridLayoutManager f355a;

        public C0024c(GridLayoutManager gridLayoutManager) {
            this.f355a = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            if (c.this.o(i) || c.this.n(i)) {
                return this.f355a.getSpanCount();
            }
            return 1;
        }
    }

    public c(RecyclerView.Adapter adapter) {
        this.g = adapter;
        adapter.registerAdapterDataObserver(this.h);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.g.getItemCount() + this.f352d.size() + this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (o(i)) {
            return this.f352d.keyAt(i);
        }
        if (n(i)) {
            return this.e.keyAt((i - this.f352d.size()) - this.g.getItemCount());
        }
        return this.g.getItemViewType(i - this.f352d.size());
    }

    public void i(View view) {
        r(this.f);
        this.f = view;
        p();
    }

    public void j(View view) {
        if (this.e.indexOfValue(view) == -1) {
            SparseArray<View> sparseArray = this.e;
            int i = this.f351c;
            this.f351c = i + 1;
            sparseArray.put(i, view);
        }
        notifyDataSetChanged();
    }

    public void k(View view) {
        if (this.f352d.indexOfValue(view) == -1) {
            SparseArray<View> sparseArray = this.f352d;
            int i = this.f350b;
            this.f350b = i + 1;
            sparseArray.put(i, view);
        }
        notifyDataSetChanged();
    }

    public void l(RecyclerView recyclerView, boolean z) {
        if (z && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            gridLayoutManager.setSpanSizeLookup(new C0024c(gridLayoutManager));
        }
    }

    public final RecyclerView.ViewHolder m(View view) {
        return new b(view);
    }

    public final boolean n(int i) {
        return i >= this.f352d.size() + this.g.getItemCount();
    }

    public final boolean o(int i) {
        return i < this.f352d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (o(viewHolder.getAdapterPosition()) || n(viewHolder.getAdapterPosition())) {
            return;
        }
        this.g.onBindViewHolder(viewHolder, viewHolder.getAdapterPosition() - this.f352d.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return this.f352d.indexOfKey(i) >= 0 ? m(this.f352d.get(i)) : this.e.indexOfKey(i) >= 0 ? m(this.e.get(i)) : this.g.onCreateViewHolder(viewGroup, i);
    }

    public final void p() {
        if (this.f == null) {
            return;
        }
        if (this.g.getItemCount() == 0) {
            k(this.f);
        } else {
            r(this.f);
        }
    }

    public void q(View view) {
        int iIndexOfValue = this.e.indexOfValue(view);
        if (iIndexOfValue == -1) {
            return;
        }
        this.e.removeAt(iIndexOfValue);
        notifyDataSetChanged();
    }

    public void r(View view) {
        int iIndexOfValue = this.f352d.indexOfValue(view);
        if (iIndexOfValue == -1) {
            return;
        }
        this.f352d.removeAt(iIndexOfValue);
        notifyDataSetChanged();
    }

    public void s() {
        try {
            unregisterAdapterDataObserver(this.h);
        } catch (Exception e) {
            Log.e(f349a, "unregisterAdapterDataObserver failed.", e);
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
                cVar.notifyItemChanged(i + cVar.f352d.size());
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
                cVar.notifyItemInserted(i + cVar.f352d.size());
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
                cVar.notifyItemMoved(i + cVar.f352d.size(), i2 + c.this.f352d.size());
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
                cVar.notifyItemRemoved(i + cVar.f352d.size());
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
                cVar.notifyItemChanged(i + cVar.f352d.size(), obj);
            }
            c.this.p();
        }
    }
}
