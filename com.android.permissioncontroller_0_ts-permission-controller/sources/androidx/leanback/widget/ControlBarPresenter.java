package androidx.leanback.widget;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.R;
import androidx.leanback.widget.ControlBar;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;

/* JADX INFO: loaded from: classes3.dex */
class ControlBarPresenter extends Presenter {
    static final int MAX_CONTROLS = 7;
    private static int sChildMarginDefault;
    private static int sControlIconWidth;
    boolean mDefaultFocusToMiddle = true;
    private int mLayoutResourceId;
    OnControlClickedListener mOnControlClickedListener;
    OnControlSelectedListener mOnControlSelectedListener;

    interface OnControlClickedListener {
        void onControlClicked(Presenter.ViewHolder viewHolder, Object obj, BoundData boundData);
    }

    interface OnControlSelectedListener {
        void onControlSelected(Presenter.ViewHolder viewHolder, Object obj, BoundData boundData);
    }

    static class BoundData {
        ObjectAdapter adapter;
        Presenter presenter;

        BoundData() {
        }
    }

    class ViewHolder extends Presenter.ViewHolder {
        ObjectAdapter mAdapter;
        ControlBar mControlBar;
        View mControlsContainer;
        BoundData mData;
        ObjectAdapter.DataObserver mDataObserver;
        Presenter mPresenter;
        SparseArray<Presenter.ViewHolder> mViewHolders;

        ViewHolder(View rootView) {
            super(rootView);
            this.mViewHolders = new SparseArray<>();
            this.mControlsContainer = rootView.findViewById(R.id.controls_container);
            ControlBar controlBar = (ControlBar) rootView.findViewById(R.id.control_bar);
            this.mControlBar = controlBar;
            if (controlBar == null) {
                throw new IllegalStateException("Couldn't find control_bar");
            }
            controlBar.setDefaultFocusToMiddle(ControlBarPresenter.this.mDefaultFocusToMiddle);
            this.mControlBar.setOnChildFocusedListener(new ControlBar.OnChildFocusedListener() { // from class: androidx.leanback.widget.ControlBarPresenter.ViewHolder.1
                @Override // androidx.leanback.widget.ControlBar.OnChildFocusedListener
                public void onChildFocusedListener(View child, View focused) {
                    if (ControlBarPresenter.this.mOnControlSelectedListener == null) {
                        return;
                    }
                    for (int position = 0; position < ViewHolder.this.mViewHolders.size(); position++) {
                        if (ViewHolder.this.mViewHolders.get(position).view == child) {
                            ControlBarPresenter.this.mOnControlSelectedListener.onControlSelected(ViewHolder.this.mViewHolders.get(position), ViewHolder.this.getDisplayedAdapter().get(position), ViewHolder.this.mData);
                            return;
                        }
                    }
                }
            });
            this.mDataObserver = new ObjectAdapter.DataObserver() { // from class: androidx.leanback.widget.ControlBarPresenter.ViewHolder.2
                @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
                public void onChanged() {
                    if (ViewHolder.this.mAdapter == ViewHolder.this.getDisplayedAdapter()) {
                        ViewHolder viewHolder = ViewHolder.this;
                        viewHolder.showControls(viewHolder.mPresenter);
                    }
                }

                @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    if (ViewHolder.this.mAdapter == ViewHolder.this.getDisplayedAdapter()) {
                        for (int i = 0; i < itemCount; i++) {
                            ViewHolder viewHolder = ViewHolder.this;
                            viewHolder.bindControlToAction(positionStart + i, viewHolder.mPresenter);
                        }
                    }
                }
            };
        }

        int getChildMarginFromCenter(Context context, int numControls) {
            return ControlBarPresenter.this.getChildMarginDefault(context) + ControlBarPresenter.this.getControlIconWidth(context);
        }

        void showControls(Presenter presenter) {
            ObjectAdapter adapter = getDisplayedAdapter();
            int adapterSize = adapter == null ? 0 : adapter.size();
            View focusedView = this.mControlBar.getFocusedChild();
            if (focusedView != null && adapterSize > 0 && this.mControlBar.indexOfChild(focusedView) >= adapterSize) {
                this.mControlBar.getChildAt(adapter.size() - 1).requestFocus();
            }
            for (int i = this.mControlBar.getChildCount() - 1; i >= adapterSize; i--) {
                this.mControlBar.removeViewAt(i);
            }
            for (int position = 0; position < adapterSize && position < 7; position++) {
                bindControlToAction(position, adapter, presenter);
            }
            ControlBar controlBar = this.mControlBar;
            controlBar.setChildMarginFromCenter(getChildMarginFromCenter(controlBar.getContext(), adapterSize));
        }

        void bindControlToAction(int position, Presenter presenter) {
            bindControlToAction(position, getDisplayedAdapter(), presenter);
        }

        private void bindControlToAction(final int position, ObjectAdapter adapter, Presenter presenter) {
            final Presenter.ViewHolder vh = this.mViewHolders.get(position);
            Object item = adapter.get(position);
            if (vh == null) {
                vh = presenter.onCreateViewHolder(this.mControlBar);
                this.mViewHolders.put(position, vh);
                presenter.setOnClickListener(vh, new View.OnClickListener() { // from class: androidx.leanback.widget.ControlBarPresenter.ViewHolder.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        Object item2 = ViewHolder.this.getDisplayedAdapter().get(position);
                        if (ControlBarPresenter.this.mOnControlClickedListener != null) {
                            ControlBarPresenter.this.mOnControlClickedListener.onControlClicked(vh, item2, ViewHolder.this.mData);
                        }
                    }
                });
            }
            if (vh.view.getParent() == null) {
                this.mControlBar.addView(vh.view);
            }
            presenter.onBindViewHolder(vh, item);
        }

        ObjectAdapter getDisplayedAdapter() {
            return this.mAdapter;
        }
    }

    public ControlBarPresenter(int layoutResourceId) {
        this.mLayoutResourceId = layoutResourceId;
    }

    public int getLayoutResourceId() {
        return this.mLayoutResourceId;
    }

    public void setOnControlClickedListener(OnControlClickedListener listener) {
        this.mOnControlClickedListener = listener;
    }

    public OnControlClickedListener getOnItemViewClickedListener() {
        return this.mOnControlClickedListener;
    }

    public void setOnControlSelectedListener(OnControlSelectedListener listener) {
        this.mOnControlSelectedListener = listener;
    }

    public OnControlSelectedListener getOnItemControlListener() {
        return this.mOnControlSelectedListener;
    }

    public void setBackgroundColor(ViewHolder vh, int color) {
        vh.mControlsContainer.setBackgroundColor(color);
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayoutResourceId(), parent, false);
        return new ViewHolder(v);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder holder, Object item) {
        ViewHolder vh = (ViewHolder) holder;
        BoundData data = (BoundData) item;
        if (vh.mAdapter != data.adapter) {
            vh.mAdapter = data.adapter;
            if (vh.mAdapter != null) {
                vh.mAdapter.registerObserver(vh.mDataObserver);
            }
        }
        vh.mPresenter = data.presenter;
        vh.mData = data;
        vh.showControls(vh.mPresenter);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder holder) {
        ViewHolder vh = (ViewHolder) holder;
        if (vh.mAdapter != null) {
            vh.mAdapter.unregisterObserver(vh.mDataObserver);
            vh.mAdapter = null;
        }
        vh.mData = null;
    }

    int getChildMarginDefault(Context context) {
        if (sChildMarginDefault == 0) {
            sChildMarginDefault = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_controls_child_margin_default);
        }
        return sChildMarginDefault;
    }

    int getControlIconWidth(Context context) {
        if (sControlIconWidth == 0) {
            sControlIconWidth = context.getResources().getDimensionPixelSize(R.dimen.lb_control_icon_width);
        }
        return sControlIconWidth;
    }

    void setDefaultFocusToMiddle(boolean defaultFocusToMiddle) {
        this.mDefaultFocusToMiddle = defaultFocusToMiddle;
    }
}
