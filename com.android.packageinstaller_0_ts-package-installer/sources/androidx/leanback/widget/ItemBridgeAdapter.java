package androidx.leanback.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ItemBridgeAdapter extends RecyclerView.Adapter implements FacetProviderAdapter {
    static final boolean DEBUG = false;
    static final String TAG = "ItemBridgeAdapter";
    private ObjectAdapter mAdapter;
    private AdapterListener mAdapterListener;
    private ObjectAdapter.DataObserver mDataObserver;
    FocusHighlightHandler mFocusHighlight;
    private PresenterSelector mPresenterSelector;
    private ArrayList<Presenter> mPresenters;
    Wrapper mWrapper;

    public static abstract class Wrapper {
        public abstract View createWrapper(View view);

        public abstract void wrap(View view, View view2);
    }

    public static class AdapterListener {
        public void onAddPresenter(Presenter presenter, int type) {
        }

        public void onCreate(ViewHolder viewHolder) {
        }

        public void onBind(ViewHolder viewHolder) {
        }

        public void onBind(ViewHolder viewHolder, List payloads) {
            onBind(viewHolder);
        }

        public void onUnbind(ViewHolder viewHolder) {
        }

        public void onAttachedToWindow(ViewHolder viewHolder) {
        }

        public void onDetachedFromWindow(ViewHolder viewHolder) {
        }
    }

    static final class ChainingFocusChangeListener implements View.OnFocusChangeListener {
        final View.OnFocusChangeListener mChainedListener;
        FocusHighlightHandler mFocusHighlight;
        boolean mHasWrapper;

        ChainingFocusChangeListener(View.OnFocusChangeListener chainedListener, boolean hasWrapper, FocusHighlightHandler focusHighlight) {
            this.mChainedListener = chainedListener;
            this.mHasWrapper = hasWrapper;
            this.mFocusHighlight = focusHighlight;
        }

        void update(boolean hasWrapper, FocusHighlightHandler focusHighlight) {
            this.mHasWrapper = hasWrapper;
            this.mFocusHighlight = focusHighlight;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean hasFocus) {
            if (this.mHasWrapper) {
                view = (View) view.getParent();
            }
            this.mFocusHighlight.onItemFocused(view, hasFocus);
            View.OnFocusChangeListener onFocusChangeListener = this.mChainedListener;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(view, hasFocus);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements FacetProvider {
        Object mExtraObject;
        final Presenter.ViewHolder mHolder;
        Object mItem;
        final Presenter mPresenter;

        public final Presenter getPresenter() {
            return this.mPresenter;
        }

        public final Presenter.ViewHolder getViewHolder() {
            return this.mHolder;
        }

        public final Object getItem() {
            return this.mItem;
        }

        public final Object getExtraObject() {
            return this.mExtraObject;
        }

        public void setExtraObject(Object object) {
            this.mExtraObject = object;
        }

        @Override // androidx.leanback.widget.FacetProvider
        public Object getFacet(Class<?> facetClass) {
            return this.mHolder.getFacet(facetClass);
        }

        ViewHolder(Presenter presenter, View view, Presenter.ViewHolder holder) {
            super(view);
            this.mPresenter = presenter;
            this.mHolder = holder;
        }
    }

    public ItemBridgeAdapter(ObjectAdapter adapter, PresenterSelector presenterSelector) {
        this.mPresenters = new ArrayList<>();
        this.mDataObserver = new ObjectAdapter.DataObserver() { // from class: androidx.leanback.widget.ItemBridgeAdapter.1
            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onChanged() {
                ItemBridgeAdapter.this.notifyDataSetChanged();
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeChanged(int positionStart, int itemCount) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeInserted(int positionStart, int itemCount) {
                ItemBridgeAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                ItemBridgeAdapter.this.notifyItemRangeRemoved(positionStart, itemCount);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemMoved(int fromPosition, int toPosition) {
                ItemBridgeAdapter.this.notifyItemMoved(fromPosition, toPosition);
            }
        };
        setAdapter(adapter);
        this.mPresenterSelector = presenterSelector;
    }

    public ItemBridgeAdapter(ObjectAdapter adapter) {
        this(adapter, null);
    }

    public ItemBridgeAdapter() {
        this.mPresenters = new ArrayList<>();
        this.mDataObserver = new ObjectAdapter.DataObserver() { // from class: androidx.leanback.widget.ItemBridgeAdapter.1
            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onChanged() {
                ItemBridgeAdapter.this.notifyDataSetChanged();
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeChanged(int positionStart, int itemCount) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeInserted(int positionStart, int itemCount) {
                ItemBridgeAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                ItemBridgeAdapter.this.notifyItemRangeRemoved(positionStart, itemCount);
            }

            @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
            public void onItemMoved(int fromPosition, int toPosition) {
                ItemBridgeAdapter.this.notifyItemMoved(fromPosition, toPosition);
            }
        };
    }

    public void setAdapter(ObjectAdapter adapter) {
        ObjectAdapter objectAdapter = this.mAdapter;
        if (adapter == objectAdapter) {
            return;
        }
        if (objectAdapter != null) {
            objectAdapter.unregisterObserver(this.mDataObserver);
        }
        this.mAdapter = adapter;
        if (adapter == null) {
            notifyDataSetChanged();
            return;
        }
        adapter.registerObserver(this.mDataObserver);
        if (hasStableIds() != this.mAdapter.hasStableIds()) {
            setHasStableIds(this.mAdapter.hasStableIds());
        }
        notifyDataSetChanged();
    }

    public void setPresenter(PresenterSelector presenterSelector) {
        this.mPresenterSelector = presenterSelector;
        notifyDataSetChanged();
    }

    public void setWrapper(Wrapper wrapper) {
        this.mWrapper = wrapper;
    }

    public Wrapper getWrapper() {
        return this.mWrapper;
    }

    void setFocusHighlight(FocusHighlightHandler listener) {
        this.mFocusHighlight = listener;
    }

    public void clear() {
        setAdapter(null);
    }

    public void setPresenterMapper(ArrayList<Presenter> presenters) {
        this.mPresenters = presenters;
    }

    public ArrayList<Presenter> getPresenterMapper() {
        return this.mPresenters;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ObjectAdapter objectAdapter = this.mAdapter;
        if (objectAdapter != null) {
            return objectAdapter.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        PresenterSelector presenterSelector = this.mPresenterSelector;
        if (presenterSelector == null) {
            presenterSelector = this.mAdapter.getPresenterSelector();
        }
        Object item = this.mAdapter.get(position);
        Presenter presenter = presenterSelector.getPresenter(item);
        int type = this.mPresenters.indexOf(presenter);
        if (type < 0) {
            this.mPresenters.add(presenter);
            type = this.mPresenters.indexOf(presenter);
            onAddPresenter(presenter, type);
            AdapterListener adapterListener = this.mAdapterListener;
            if (adapterListener != null) {
                adapterListener.onAddPresenter(presenter, type);
            }
        }
        return type;
    }

    protected void onAddPresenter(Presenter presenter, int type) {
    }

    protected void onCreate(ViewHolder viewHolder) {
    }

    protected void onBind(ViewHolder viewHolder) {
    }

    protected void onUnbind(ViewHolder viewHolder) {
    }

    protected void onAttachedToWindow(ViewHolder viewHolder) {
    }

    protected void onDetachedFromWindow(ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Presenter.ViewHolder presenterVh;
        View view;
        Presenter presenter = this.mPresenters.get(viewType);
        Wrapper wrapper = this.mWrapper;
        if (wrapper != null) {
            view = wrapper.createWrapper(parent);
            presenterVh = presenter.onCreateViewHolder(parent);
            this.mWrapper.wrap(view, presenterVh.view);
        } else {
            presenterVh = presenter.onCreateViewHolder(parent);
            view = presenterVh.view;
        }
        ViewHolder viewHolder = new ViewHolder(presenter, view, presenterVh);
        onCreate(viewHolder);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onCreate(viewHolder);
        }
        View presenterView = viewHolder.mHolder.view;
        View.OnFocusChangeListener currentListener = presenterView.getOnFocusChangeListener();
        if (this.mFocusHighlight != null) {
            if (currentListener instanceof ChainingFocusChangeListener) {
                ((ChainingFocusChangeListener) currentListener).update(this.mWrapper != null, this.mFocusHighlight);
            } else {
                presenterView.setOnFocusChangeListener(new ChainingFocusChangeListener(currentListener, this.mWrapper != null, this.mFocusHighlight));
            }
            this.mFocusHighlight.onInitializeView(view);
        } else if (currentListener instanceof ChainingFocusChangeListener) {
            presenterView.setOnFocusChangeListener(((ChainingFocusChangeListener) currentListener).mChainedListener);
        }
        return viewHolder;
    }

    public void setAdapterListener(AdapterListener listener) {
        this.mAdapterListener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mItem = this.mAdapter.get(position);
        viewHolder.mPresenter.onBindViewHolder(viewHolder.mHolder, viewHolder.mItem);
        onBind(viewHolder);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onBind(viewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mItem = this.mAdapter.get(position);
        viewHolder.mPresenter.onBindViewHolder(viewHolder.mHolder, viewHolder.mItem, payloads);
        onBind(viewHolder);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onBind(viewHolder, payloads);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mPresenter.onUnbindViewHolder(viewHolder.mHolder);
        onUnbind(viewHolder);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onUnbind(viewHolder);
        }
        viewHolder.mItem = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        onViewRecycled(holder);
        return DEBUG;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        onAttachedToWindow(viewHolder);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onAttachedToWindow(viewHolder);
        }
        viewHolder.mPresenter.onViewAttachedToWindow(viewHolder.mHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mPresenter.onViewDetachedFromWindow(viewHolder.mHolder);
        onDetachedFromWindow(viewHolder);
        AdapterListener adapterListener = this.mAdapterListener;
        if (adapterListener != null) {
            adapterListener.onDetachedFromWindow(viewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return this.mAdapter.getId(position);
    }

    @Override // androidx.leanback.widget.FacetProviderAdapter
    public FacetProvider getFacetProvider(int type) {
        return this.mPresenters.get(type);
    }
}
