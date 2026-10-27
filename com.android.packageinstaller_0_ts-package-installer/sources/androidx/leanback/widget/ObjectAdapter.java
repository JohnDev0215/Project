package androidx.leanback.widget;

import android.database.Observable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectAdapter {
    public static final int NO_ID = -1;
    private boolean mHasStableIds;
    private final DataObservable mObservable = new DataObservable();
    private PresenterSelector mPresenterSelector;

    public abstract Object get(int i);

    public abstract int size();

    public static abstract class DataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            onChanged();
        }

        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            onChanged();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            onChanged();
        }

        public void onItemMoved(int fromPosition, int toPosition) {
            onChanged();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onChanged();
        }
    }

    private static final class DataObservable extends Observable<DataObserver> {
        DataObservable() {
        }

        public void notifyChanged() {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((DataObserver) this.mObservers.get(i)).onChanged();
            }
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((DataObserver) this.mObservers.get(i)).onItemRangeChanged(positionStart, itemCount);
            }
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((DataObserver) this.mObservers.get(i)).onItemRangeChanged(positionStart, itemCount, payload);
            }
        }

        public void notifyItemRangeInserted(int positionStart, int itemCount) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((DataObserver) this.mObservers.get(i)).onItemRangeInserted(positionStart, itemCount);
            }
        }

        public void notifyItemRangeRemoved(int positionStart, int itemCount) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((DataObserver) this.mObservers.get(i)).onItemRangeRemoved(positionStart, itemCount);
            }
        }

        public void notifyItemMoved(int positionStart, int toPosition) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((DataObserver) this.mObservers.get(i)).onItemMoved(positionStart, toPosition);
            }
        }

        boolean hasObserver() {
            return this.mObservers.size() > 0;
        }
    }

    public ObjectAdapter(PresenterSelector presenterSelector) {
        setPresenterSelector(presenterSelector);
    }

    public ObjectAdapter(Presenter presenter) {
        setPresenterSelector(new SinglePresenterSelector(presenter));
    }

    public ObjectAdapter() {
    }

    public final void setPresenterSelector(PresenterSelector presenterSelector) {
        if (presenterSelector == null) {
            throw new IllegalArgumentException("Presenter selector must not be null");
        }
        boolean update = this.mPresenterSelector != null;
        boolean selectorChanged = update && this.mPresenterSelector != presenterSelector;
        this.mPresenterSelector = presenterSelector;
        if (selectorChanged) {
            onPresenterSelectorChanged();
        }
        if (update) {
            notifyChanged();
        }
    }

    protected void onPresenterSelectorChanged() {
    }

    public final PresenterSelector getPresenterSelector() {
        return this.mPresenterSelector;
    }

    public final void registerObserver(DataObserver observer) {
        this.mObservable.registerObserver(observer);
    }

    public final void unregisterObserver(DataObserver observer) {
        this.mObservable.unregisterObserver(observer);
    }

    public final boolean hasObserver() {
        return this.mObservable.hasObserver();
    }

    public final void unregisterAllObservers() {
        this.mObservable.unregisterAll();
    }

    public final void notifyItemRangeChanged(int positionStart, int itemCount) {
        this.mObservable.notifyItemRangeChanged(positionStart, itemCount);
    }

    public final void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
        this.mObservable.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    protected final void notifyItemRangeInserted(int positionStart, int itemCount) {
        this.mObservable.notifyItemRangeInserted(positionStart, itemCount);
    }

    protected final void notifyItemRangeRemoved(int positionStart, int itemCount) {
        this.mObservable.notifyItemRangeRemoved(positionStart, itemCount);
    }

    protected final void notifyItemMoved(int fromPosition, int toPosition) {
        this.mObservable.notifyItemMoved(fromPosition, toPosition);
    }

    protected final void notifyChanged() {
        this.mObservable.notifyChanged();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void setHasStableIds(boolean hasStableIds) {
        boolean changed = this.mHasStableIds != hasStableIds;
        this.mHasStableIds = hasStableIds;
        if (changed) {
            onHasStableIdsChanged();
        }
    }

    protected void onHasStableIdsChanged() {
    }

    public final Presenter getPresenter(Object item) {
        PresenterSelector presenterSelector = this.mPresenterSelector;
        if (presenterSelector == null) {
            throw new IllegalStateException("Presenter selector must not be null");
        }
        return presenterSelector.getPresenter(item);
    }

    public long getId(int position) {
        return -1L;
    }

    public boolean isImmediateNotifySupported() {
        return false;
    }
}
