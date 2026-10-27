package com.android.car.ui.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import spi.p9;
import spi.pc;

/* JADX INFO: loaded from: classes2.dex */
public final class RecyclerViewAdapterAdapterV1 implements spi.e<ViewHolderAdapterV1> {

    @NonNull
    private final RecyclerView.Adapter mAdapter;

    @NonNull
    private final Context mAppContext;

    @NonNull
    private List<spi.d> mAdapterDataObservers = new ArrayList();
    private RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.android.car.ui.recyclerview.RecyclerViewAdapterAdapterV1.1
        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            Iterator it = RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers.iterator();
            while (it.hasNext()) {
                ((spi.d) it.next()).c();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            Iterator it = RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers.iterator();
            while (it.hasNext()) {
                ((spi.d) it.next()).g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            Iterator it = RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers.iterator();
            while (it.hasNext()) {
                ((spi.d) it.next()).f();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            for (spi.d dVar : RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dVar.b();
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            Iterator it = RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers.iterator();
            while (it.hasNext()) {
                ((spi.d) it.next()).a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            Iterator it = RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers.iterator();
            while (it.hasNext()) {
                ((spi.d) it.next()).e();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            Iterator it = RecyclerViewAdapterAdapterV1.this.mAdapterDataObservers.iterator();
            while (it.hasNext()) {
                ((spi.d) it.next()).d();
            }
        }
    };

    /* JADX INFO: renamed from: com.android.car.ui.recyclerview.RecyclerViewAdapterAdapterV1$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy;

        static {
            int[] iArr = new int[RecyclerView.Adapter.StateRestorationPolicy.values().length];
            $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy = iArr;
            try {
                iArr[RecyclerView.Adapter.StateRestorationPolicy.PREVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy[RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy[RecyclerView.Adapter.StateRestorationPolicy.ALLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class ViewHolderAdapterV1 implements pc {
        private RecyclerView.ViewHolder mViewHolder;

        public ViewHolderAdapterV1(@NonNull RecyclerView.ViewHolder viewHolder) {
            this.mViewHolder = viewHolder;
        }

        @Override // spi.pc
        public View getItemView() {
            return this.mViewHolder.itemView;
        }

        @NonNull
        public RecyclerView.ViewHolder getViewHolder() {
            return this.mViewHolder;
        }

        @Override // spi.pc
        public boolean isRecyclable() {
            return this.mViewHolder.isRecyclable();
        }
    }

    public RecyclerViewAdapterAdapterV1(@NonNull Context context, @NonNull RecyclerView.Adapter<?> adapter) {
        this.mAppContext = context;
        this.mAdapter = adapter;
    }

    @NonNull
    public RecyclerView.Adapter getAdapter() {
        return this.mAdapter;
    }

    @Override // spi.e
    public int getItemCount() {
        return this.mAdapter.getItemCount();
    }

    @Override // spi.e
    public long getItemId(int i) {
        return this.mAdapter.getItemId(i);
    }

    @Override // spi.e
    public int getItemViewType(int i) {
        return this.mAdapter.getItemViewType(i);
    }

    public int getStateRestorationPolicyInt() {
        int i = AnonymousClass2.$SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy[this.mAdapter.getStateRestorationPolicy().ordinal()];
        if (i != 1) {
            return i != 2 ? 0 : 1;
        }
        return 2;
    }

    @Override // spi.e
    public boolean hasStableIds() {
        return this.mAdapter.hasStableIds();
    }

    @Override // spi.e
    public void onAttachedToRecyclerView(p9 p9Var) {
        this.mAdapter.onAttachedToRecyclerView(null);
        this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
    }

    @Override // spi.e
    public void onDetachedFromRecyclerView(p9 p9Var) {
        this.mAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mAdapter.onDetachedFromRecyclerView(null);
    }

    public void registerAdapterDataObserver(spi.d dVar) {
        if (dVar == null) {
            return;
        }
        this.mAdapterDataObservers.add(dVar);
    }

    public void setMaxItems(int i) {
        Object obj = this.mAdapter;
        if (obj instanceof CarUiRecyclerView.a) {
            ((CarUiRecyclerView.a) obj).a(i);
        }
    }

    public void unregisterAdapterDataObserver(spi.d dVar) {
        if (dVar == null) {
            return;
        }
        this.mAdapterDataObservers.remove(dVar);
    }

    @Override // spi.e
    public void bindViewHolder(ViewHolderAdapterV1 viewHolderAdapterV1, int i) {
        this.mAdapter.bindViewHolder(viewHolderAdapterV1.getViewHolder(), i);
    }

    @Override // spi.e
    public ViewHolderAdapterV1 createViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolderAdapterV1(this.mAdapter.createViewHolder(new FrameLayout(this.mAppContext), i));
    }

    @Override // spi.e
    public boolean onFailedToRecycleView(ViewHolderAdapterV1 viewHolderAdapterV1) {
        return this.mAdapter.onFailedToRecycleView(viewHolderAdapterV1.getViewHolder());
    }

    @Override // spi.e
    public void onViewAttachedToWindow(ViewHolderAdapterV1 viewHolderAdapterV1) {
        this.mAdapter.onViewAttachedToWindow(viewHolderAdapterV1.getViewHolder());
    }

    @Override // spi.e
    public void onViewDetachedFromWindow(ViewHolderAdapterV1 viewHolderAdapterV1) {
        this.mAdapter.onViewDetachedFromWindow(viewHolderAdapterV1.getViewHolder());
    }

    @Override // spi.e
    public void onViewRecycled(ViewHolderAdapterV1 viewHolderAdapterV1) {
        this.mAdapter.onViewRecycled(viewHolderAdapterV1.getViewHolder());
    }
}
