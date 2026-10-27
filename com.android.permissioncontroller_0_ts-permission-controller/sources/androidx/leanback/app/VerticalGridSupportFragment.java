package androidx.leanback.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.R;
import androidx.leanback.transition.TransitionHelper;
import androidx.leanback.util.StateMachine;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnChildLaidOutListener;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;

/* JADX INFO: loaded from: classes3.dex */
public class VerticalGridSupportFragment extends BaseSupportFragment {
    static final boolean DEBUG = false;
    static final String TAG = "VerticalGF";
    private ObjectAdapter mAdapter;
    private VerticalGridPresenter mGridPresenter;
    VerticalGridPresenter.ViewHolder mGridViewHolder;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    OnItemViewSelectedListener mOnItemViewSelectedListener;
    private Object mSceneAfterEntranceTransition;
    private int mSelectedPosition = -1;
    final StateMachine.State STATE_SET_ENTRANCE_START_STATE = new StateMachine.State("SET_ENTRANCE_START_STATE") { // from class: androidx.leanback.app.VerticalGridSupportFragment.1
        @Override // androidx.leanback.util.StateMachine.State
        public void run() {
            VerticalGridSupportFragment.this.setEntranceTransitionState(false);
        }
    };
    private final OnItemViewSelectedListener mViewSelectedListener = new OnItemViewSelectedListener() { // from class: androidx.leanback.app.VerticalGridSupportFragment.2
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            int position = VerticalGridSupportFragment.this.mGridViewHolder.getGridView().getSelectedPosition();
            VerticalGridSupportFragment.this.gridOnItemSelected(position);
            if (VerticalGridSupportFragment.this.mOnItemViewSelectedListener != null) {
                VerticalGridSupportFragment.this.mOnItemViewSelectedListener.onItemSelected(itemViewHolder, item, rowViewHolder, row);
            }
        }
    };
    private final OnChildLaidOutListener mChildLaidOutListener = new OnChildLaidOutListener() { // from class: androidx.leanback.app.VerticalGridSupportFragment.3
        @Override // androidx.leanback.widget.OnChildLaidOutListener
        public void onChildLaidOut(ViewGroup parent, View view, int position, long id) {
            if (position == 0) {
                VerticalGridSupportFragment.this.showOrHideTitle();
            }
        }
    };

    @Override // androidx.leanback.app.BaseSupportFragment
    void createStateMachineStates() {
        super.createStateMachineStates();
        this.mStateMachine.addState(this.STATE_SET_ENTRANCE_START_STATE);
    }

    @Override // androidx.leanback.app.BaseSupportFragment
    void createStateMachineTransitions() {
        super.createStateMachineTransitions();
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_ON_PREPARED, this.STATE_SET_ENTRANCE_START_STATE, this.EVT_ON_CREATEVIEW);
    }

    public void setGridPresenter(VerticalGridPresenter gridPresenter) {
        if (gridPresenter == null) {
            throw new IllegalArgumentException("Grid presenter may not be null");
        }
        this.mGridPresenter = gridPresenter;
        gridPresenter.setOnItemViewSelectedListener(this.mViewSelectedListener);
        OnItemViewClickedListener onItemViewClickedListener = this.mOnItemViewClickedListener;
        if (onItemViewClickedListener != null) {
            this.mGridPresenter.setOnItemViewClickedListener(onItemViewClickedListener);
        }
    }

    public VerticalGridPresenter getGridPresenter() {
        return this.mGridPresenter;
    }

    public void setAdapter(ObjectAdapter adapter) {
        this.mAdapter = adapter;
        updateAdapter();
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener listener) {
        this.mOnItemViewSelectedListener = listener;
    }

    void gridOnItemSelected(int position) {
        if (position != this.mSelectedPosition) {
            this.mSelectedPosition = position;
            showOrHideTitle();
        }
    }

    void showOrHideTitle() {
        if (this.mGridViewHolder.getGridView().findViewHolderForAdapterPosition(this.mSelectedPosition) == null) {
            return;
        }
        if (!this.mGridViewHolder.getGridView().hasPreviousViewInSameRow(this.mSelectedPosition)) {
            showTitle(true);
        } else {
            showTitle(false);
        }
    }

    public void setOnItemViewClickedListener(OnItemViewClickedListener listener) {
        this.mOnItemViewClickedListener = listener;
        VerticalGridPresenter verticalGridPresenter = this.mGridPresenter;
        if (verticalGridPresenter != null) {
            verticalGridPresenter.setOnItemViewClickedListener(listener);
        }
    }

    public OnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lb_vertical_grid_fragment, container, false);
        ViewGroup gridFrame = (ViewGroup) root.findViewById(R.id.grid_frame);
        installTitleView(inflater, gridFrame, savedInstanceState);
        getProgressBarManager().setRootView(root);
        ViewGroup gridDock = (ViewGroup) root.findViewById(R.id.browse_grid_dock);
        VerticalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = this.mGridPresenter.onCreateViewHolder(gridDock);
        this.mGridViewHolder = viewHolderOnCreateViewHolder;
        gridDock.addView(viewHolderOnCreateViewHolder.view);
        this.mGridViewHolder.getGridView().setOnChildLaidOutListener(this.mChildLaidOutListener);
        this.mSceneAfterEntranceTransition = TransitionHelper.createScene(gridDock, new Runnable() { // from class: androidx.leanback.app.VerticalGridSupportFragment.4
            @Override // java.lang.Runnable
            public void run() {
                VerticalGridSupportFragment.this.setEntranceTransitionState(true);
            }
        });
        updateAdapter();
        return root;
    }

    private void setupFocusSearchListener() {
        BrowseFrameLayout browseFrameLayout = (BrowseFrameLayout) getView().findViewById(R.id.grid_frame);
        browseFrameLayout.setOnFocusSearchListener(getTitleHelper().getOnFocusSearchListener());
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setupFocusSearchListener();
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mGridViewHolder = null;
    }

    public void setSelectedPosition(int position) {
        this.mSelectedPosition = position;
        VerticalGridPresenter.ViewHolder viewHolder = this.mGridViewHolder;
        if (viewHolder != null && viewHolder.getGridView().getAdapter() != null) {
            this.mGridViewHolder.getGridView().setSelectedPositionSmooth(position);
        }
    }

    private void updateAdapter() {
        VerticalGridPresenter.ViewHolder viewHolder = this.mGridViewHolder;
        if (viewHolder != null) {
            this.mGridPresenter.onBindViewHolder(viewHolder, this.mAdapter);
            if (this.mSelectedPosition != -1) {
                this.mGridViewHolder.getGridView().setSelectedPosition(this.mSelectedPosition);
            }
        }
    }

    @Override // androidx.leanback.app.BaseSupportFragment
    protected Object createEntranceTransition() {
        return TransitionHelper.loadTransition(getContext(), R.transition.lb_vertical_grid_entrance_transition);
    }

    @Override // androidx.leanback.app.BaseSupportFragment
    protected void runEntranceTransition(Object entranceTransition) {
        TransitionHelper.runTransition(this.mSceneAfterEntranceTransition, entranceTransition);
    }

    void setEntranceTransitionState(boolean afterTransition) {
        this.mGridPresenter.setEntranceTransitionState(this.mGridViewHolder, afterTransition);
    }
}
