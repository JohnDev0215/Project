package androidx.leanback.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PresenterSwitcher {
    private Presenter mCurrentPresenter;
    private Presenter.ViewHolder mCurrentViewHolder;
    private ViewGroup mParent;
    private PresenterSelector mPresenterSelector;

    protected abstract void insertView(View view);

    public void init(ViewGroup parent, PresenterSelector presenterSelector) {
        clear();
        this.mParent = parent;
        this.mPresenterSelector = presenterSelector;
    }

    public void select(Object object) {
        switchView(object);
        showView(true);
    }

    public void unselect() {
        showView(false);
    }

    public final ViewGroup getParentViewGroup() {
        return this.mParent;
    }

    private void showView(boolean show) {
        Presenter.ViewHolder viewHolder = this.mCurrentViewHolder;
        if (viewHolder != null) {
            showView(viewHolder.view, show);
        }
    }

    private void switchView(Object object) {
        Presenter presenter = this.mPresenterSelector.getPresenter(object);
        Presenter presenter2 = this.mCurrentPresenter;
        if (presenter != presenter2) {
            showView(false);
            clear();
            this.mCurrentPresenter = presenter;
            if (presenter == null) {
                return;
            }
            Presenter.ViewHolder viewHolderOnCreateViewHolder = presenter.onCreateViewHolder(this.mParent);
            this.mCurrentViewHolder = viewHolderOnCreateViewHolder;
            insertView(viewHolderOnCreateViewHolder.view);
        } else if (presenter2 == null) {
            return;
        } else {
            presenter2.onUnbindViewHolder(this.mCurrentViewHolder);
        }
        this.mCurrentPresenter.onBindViewHolder(this.mCurrentViewHolder, object);
        onViewSelected(this.mCurrentViewHolder.view);
    }

    protected void onViewSelected(View view) {
    }

    protected void showView(View view, boolean visible) {
        view.setVisibility(visible ? 0 : 8);
    }

    public void clear() {
        Presenter presenter = this.mCurrentPresenter;
        if (presenter != null) {
            presenter.onUnbindViewHolder(this.mCurrentViewHolder);
            this.mParent.removeView(this.mCurrentViewHolder.view);
            this.mCurrentViewHolder = null;
            this.mCurrentPresenter = null;
        }
    }
}
