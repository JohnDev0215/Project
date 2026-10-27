package androidx.leanback.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.leanback.R;
import androidx.leanback.transition.TransitionHelper;
import androidx.leanback.transition.TransitionListener;
import androidx.leanback.util.StateMachine;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.leanback.widget.DetailsParallax;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.ItemAlignmentFacet;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class DetailsFragment extends BaseFragment {
    static final boolean DEBUG = false;
    static final String TAG = "DetailsFragment";
    final StateMachine.State STATE_ENTER_TRANSITION_ADDLISTENER;
    final StateMachine.State STATE_ENTER_TRANSITION_CANCEL;
    final StateMachine.State STATE_ENTER_TRANSITION_PENDING;
    final StateMachine.State STATE_SWITCH_TO_VIDEO_IN_ON_CREATE;
    ObjectAdapter mAdapter;
    Drawable mBackgroundDrawable;
    View mBackgroundView;
    int mContainerListAlignTop;
    DetailsFragmentBackgroundController mDetailsBackgroundController;
    DetailsParallax mDetailsParallax;
    BaseOnItemViewSelectedListener mExternalOnItemViewSelectedListener;
    BaseOnItemViewClickedListener mOnItemViewClickedListener;
    BrowseFrameLayout mRootView;
    RowsFragment mRowsFragment;
    Object mSceneAfterEntranceTransition;
    Fragment mVideoFragment;
    WaitEnterTransitionTimeout mWaitEnterTransitionTimeout;
    final StateMachine.State STATE_SET_ENTRANCE_START_STATE = new StateMachine.State("STATE_SET_ENTRANCE_START_STATE") { // from class: androidx.leanback.app.DetailsFragment.1
        @Override // androidx.leanback.util.StateMachine.State
        public void run() {
            DetailsFragment.this.mRowsFragment.setEntranceTransitionState(false);
        }
    };
    final StateMachine.State STATE_ENTER_TRANSITION_INIT = new StateMachine.State("STATE_ENTER_TRANSIITON_INIT");
    final StateMachine.State STATE_ENTER_TRANSITION_COMPLETE = new StateMachine.State("STATE_ENTER_TRANSIITON_COMPLETE", true, false);
    final StateMachine.State STATE_ON_SAFE_START = new StateMachine.State("STATE_ON_SAFE_START") { // from class: androidx.leanback.app.DetailsFragment.6
        @Override // androidx.leanback.util.StateMachine.State
        public void run() {
            DetailsFragment.this.onSafeStart();
        }
    };
    final StateMachine.Event EVT_ONSTART = new StateMachine.Event("onStart");
    final StateMachine.Event EVT_NO_ENTER_TRANSITION = new StateMachine.Event("EVT_NO_ENTER_TRANSITION");
    final StateMachine.Event EVT_DETAILS_ROW_LOADED = new StateMachine.Event("onFirstRowLoaded");
    final StateMachine.Event EVT_ENTER_TRANSIITON_DONE = new StateMachine.Event("onEnterTransitionDone");
    final StateMachine.Event EVT_SWITCH_TO_VIDEO = new StateMachine.Event("switchToVideo");
    TransitionListener mEnterTransitionListener = new TransitionListener() { // from class: androidx.leanback.app.DetailsFragment.7
        @Override // androidx.leanback.transition.TransitionListener
        public void onTransitionStart(Object transition) {
            if (DetailsFragment.this.mWaitEnterTransitionTimeout != null) {
                DetailsFragment.this.mWaitEnterTransitionTimeout.mRef.clear();
            }
        }

        @Override // androidx.leanback.transition.TransitionListener
        public void onTransitionCancel(Object transition) {
            DetailsFragment.this.mStateMachine.fireEvent(DetailsFragment.this.EVT_ENTER_TRANSIITON_DONE);
        }

        @Override // androidx.leanback.transition.TransitionListener
        public void onTransitionEnd(Object transition) {
            DetailsFragment.this.mStateMachine.fireEvent(DetailsFragment.this.EVT_ENTER_TRANSIITON_DONE);
        }
    };
    TransitionListener mReturnTransitionListener = new TransitionListener() { // from class: androidx.leanback.app.DetailsFragment.8
        @Override // androidx.leanback.transition.TransitionListener
        public void onTransitionStart(Object transition) {
            DetailsFragment.this.onReturnTransitionStart();
        }
    };
    boolean mPendingFocusOnVideo = false;
    final SetSelectionRunnable mSetSelectionRunnable = new SetSelectionRunnable();
    final BaseOnItemViewSelectedListener<Object> mOnItemViewSelectedListener = new BaseOnItemViewSelectedListener<Object>() { // from class: androidx.leanback.app.DetailsFragment.9
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
            int position = DetailsFragment.this.mRowsFragment.getVerticalGridView().getSelectedPosition();
            int subposition = DetailsFragment.this.mRowsFragment.getVerticalGridView().getSelectedSubPosition();
            DetailsFragment.this.onRowSelected(position, subposition);
            if (DetailsFragment.this.mExternalOnItemViewSelectedListener != null) {
                DetailsFragment.this.mExternalOnItemViewSelectedListener.onItemSelected(itemViewHolder, item, rowViewHolder, row);
            }
        }
    };

    public DetailsFragment() {
        boolean z = false;
        this.STATE_SWITCH_TO_VIDEO_IN_ON_CREATE = new StateMachine.State("STATE_SWITCH_TO_VIDEO_IN_ON_CREATE", z, z) { // from class: androidx.leanback.app.DetailsFragment.2
            @Override // androidx.leanback.util.StateMachine.State
            public void run() {
                DetailsFragment.this.switchToVideoBeforeVideoFragmentCreated();
            }
        };
        this.STATE_ENTER_TRANSITION_CANCEL = new StateMachine.State("STATE_ENTER_TRANSITION_CANCEL", z, z) { // from class: androidx.leanback.app.DetailsFragment.3
            @Override // androidx.leanback.util.StateMachine.State
            public void run() {
                if (DetailsFragment.this.mWaitEnterTransitionTimeout != null) {
                    DetailsFragment.this.mWaitEnterTransitionTimeout.mRef.clear();
                }
                if (DetailsFragment.this.getActivity() != null) {
                    Window window = DetailsFragment.this.getActivity().getWindow();
                    Object returnTransition = TransitionHelper.getReturnTransition(window);
                    Object sharedReturnTransition = TransitionHelper.getSharedElementReturnTransition(window);
                    TransitionHelper.setEnterTransition(window, (Object) null);
                    TransitionHelper.setSharedElementEnterTransition(window, (Object) null);
                    TransitionHelper.setReturnTransition(window, returnTransition);
                    TransitionHelper.setSharedElementReturnTransition(window, sharedReturnTransition);
                }
            }
        };
        String str = "STATE_ENTER_TRANSITION_PENDING";
        this.STATE_ENTER_TRANSITION_ADDLISTENER = new StateMachine.State(str) { // from class: androidx.leanback.app.DetailsFragment.4
            @Override // androidx.leanback.util.StateMachine.State
            public void run() {
                Object transition = TransitionHelper.getEnterTransition(DetailsFragment.this.getActivity().getWindow());
                TransitionHelper.addTransitionListener(transition, DetailsFragment.this.mEnterTransitionListener);
            }
        };
        this.STATE_ENTER_TRANSITION_PENDING = new StateMachine.State(str) { // from class: androidx.leanback.app.DetailsFragment.5
            @Override // androidx.leanback.util.StateMachine.State
            public void run() {
                if (DetailsFragment.this.mWaitEnterTransitionTimeout == null) {
                    new WaitEnterTransitionTimeout(DetailsFragment.this);
                }
            }
        };
    }

    void switchToVideoBeforeVideoFragmentCreated() {
        this.mDetailsBackgroundController.switchToVideoBeforeCreate();
        showTitle(false);
        this.mPendingFocusOnVideo = true;
        slideOutGridView();
    }

    static class WaitEnterTransitionTimeout implements Runnable {
        static final long WAIT_ENTERTRANSITION_START = 200;
        final WeakReference<DetailsFragment> mRef;

        WaitEnterTransitionTimeout(DetailsFragment f) {
            this.mRef = new WeakReference<>(f);
            f.getView().postDelayed(this, WAIT_ENTERTRANSITION_START);
        }

        @Override // java.lang.Runnable
        public void run() {
            DetailsFragment f = this.mRef.get();
            if (f != null) {
                f.mStateMachine.fireEvent(f.EVT_ENTER_TRANSIITON_DONE);
            }
        }
    }

    @Override // androidx.leanback.app.BaseFragment
    void createStateMachineStates() {
        super.createStateMachineStates();
        this.mStateMachine.addState(this.STATE_SET_ENTRANCE_START_STATE);
        this.mStateMachine.addState(this.STATE_ON_SAFE_START);
        this.mStateMachine.addState(this.STATE_SWITCH_TO_VIDEO_IN_ON_CREATE);
        this.mStateMachine.addState(this.STATE_ENTER_TRANSITION_INIT);
        this.mStateMachine.addState(this.STATE_ENTER_TRANSITION_ADDLISTENER);
        this.mStateMachine.addState(this.STATE_ENTER_TRANSITION_CANCEL);
        this.mStateMachine.addState(this.STATE_ENTER_TRANSITION_PENDING);
        this.mStateMachine.addState(this.STATE_ENTER_TRANSITION_COMPLETE);
    }

    @Override // androidx.leanback.app.BaseFragment
    void createStateMachineTransitions() {
        super.createStateMachineTransitions();
        this.mStateMachine.addTransition(this.STATE_START, this.STATE_ENTER_TRANSITION_INIT, this.EVT_ON_CREATE);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_INIT, this.STATE_ENTER_TRANSITION_COMPLETE, this.COND_TRANSITION_NOT_SUPPORTED);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_INIT, this.STATE_ENTER_TRANSITION_COMPLETE, this.EVT_NO_ENTER_TRANSITION);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_INIT, this.STATE_ENTER_TRANSITION_CANCEL, this.EVT_SWITCH_TO_VIDEO);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_CANCEL, this.STATE_ENTER_TRANSITION_COMPLETE);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_INIT, this.STATE_ENTER_TRANSITION_ADDLISTENER, this.EVT_ON_CREATEVIEW);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_ADDLISTENER, this.STATE_ENTER_TRANSITION_COMPLETE, this.EVT_ENTER_TRANSIITON_DONE);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_ADDLISTENER, this.STATE_ENTER_TRANSITION_PENDING, this.EVT_DETAILS_ROW_LOADED);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_PENDING, this.STATE_ENTER_TRANSITION_COMPLETE, this.EVT_ENTER_TRANSIITON_DONE);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_COMPLETE, this.STATE_ENTRANCE_PERFORM);
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_INIT, this.STATE_SWITCH_TO_VIDEO_IN_ON_CREATE, this.EVT_SWITCH_TO_VIDEO);
        this.mStateMachine.addTransition(this.STATE_SWITCH_TO_VIDEO_IN_ON_CREATE, this.STATE_ENTRANCE_COMPLETE);
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_COMPLETE, this.STATE_SWITCH_TO_VIDEO_IN_ON_CREATE, this.EVT_SWITCH_TO_VIDEO);
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_ON_PREPARED, this.STATE_SET_ENTRANCE_START_STATE, this.EVT_ONSTART);
        this.mStateMachine.addTransition(this.STATE_START, this.STATE_ON_SAFE_START, this.EVT_ONSTART);
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_COMPLETE, this.STATE_ON_SAFE_START);
        this.mStateMachine.addTransition(this.STATE_ENTER_TRANSITION_COMPLETE, this.STATE_ON_SAFE_START);
    }

    private class SetSelectionRunnable implements Runnable {
        int mPosition;
        boolean mSmooth = true;

        SetSelectionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DetailsFragment.this.mRowsFragment == null) {
                return;
            }
            DetailsFragment.this.mRowsFragment.setSelectedPosition(this.mPosition, this.mSmooth);
        }
    }

    public void setAdapter(ObjectAdapter adapter) {
        this.mAdapter = adapter;
        Presenter[] presenters = adapter.getPresenterSelector().getPresenters();
        if (presenters != null) {
            for (Presenter presenter : presenters) {
                setupPresenter(presenter);
            }
        } else {
            Log.e(TAG, "PresenterSelector.getPresenters() not implemented");
        }
        RowsFragment rowsFragment = this.mRowsFragment;
        if (rowsFragment != null) {
            rowsFragment.setAdapter(adapter);
        }
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener listener) {
        this.mExternalOnItemViewSelectedListener = listener;
    }

    public void setOnItemViewClickedListener(BaseOnItemViewClickedListener listener) {
        if (this.mOnItemViewClickedListener != listener) {
            this.mOnItemViewClickedListener = listener;
            RowsFragment rowsFragment = this.mRowsFragment;
            if (rowsFragment != null) {
                rowsFragment.setOnItemViewClickedListener(listener);
            }
        }
    }

    public BaseOnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    @Override // androidx.leanback.app.BaseFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContainerListAlignTop = getResources().getDimensionPixelSize(R.dimen.lb_details_rows_align_top);
        Activity activity = getActivity();
        if (activity != null) {
            if (TransitionHelper.getEnterTransition(activity.getWindow()) == null) {
                this.mStateMachine.fireEvent(this.EVT_NO_ENTER_TRANSITION);
            }
            Object transition = TransitionHelper.getReturnTransition(activity.getWindow());
            if (transition != null) {
                TransitionHelper.addTransitionListener(transition, this.mReturnTransitionListener);
                return;
            }
            return;
        }
        this.mStateMachine.fireEvent(this.EVT_NO_ENTER_TRANSITION);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BrowseFrameLayout browseFrameLayout = (BrowseFrameLayout) inflater.inflate(R.layout.lb_details_fragment, container, false);
        this.mRootView = browseFrameLayout;
        View viewFindViewById = browseFrameLayout.findViewById(R.id.details_background_view);
        this.mBackgroundView = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setBackground(this.mBackgroundDrawable);
        }
        RowsFragment rowsFragment = (RowsFragment) getChildFragmentManager().findFragmentById(R.id.details_rows_dock);
        this.mRowsFragment = rowsFragment;
        if (rowsFragment == null) {
            this.mRowsFragment = new RowsFragment();
            getChildFragmentManager().beginTransaction().replace(R.id.details_rows_dock, this.mRowsFragment).commit();
        }
        installTitleView(inflater, this.mRootView, savedInstanceState);
        this.mRowsFragment.setAdapter(this.mAdapter);
        this.mRowsFragment.setOnItemViewSelectedListener(this.mOnItemViewSelectedListener);
        this.mRowsFragment.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        this.mSceneAfterEntranceTransition = TransitionHelper.createScene(this.mRootView, new Runnable() { // from class: androidx.leanback.app.DetailsFragment.10
            @Override // java.lang.Runnable
            public void run() {
                DetailsFragment.this.mRowsFragment.setEntranceTransitionState(true);
            }
        });
        setupDpadNavigation();
        if (Build.VERSION.SDK_INT >= 21) {
            this.mRowsFragment.setExternalAdapterListener(new ItemBridgeAdapter.AdapterListener() { // from class: androidx.leanback.app.DetailsFragment.11
                @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
                public void onCreate(ItemBridgeAdapter.ViewHolder vh) {
                    if (DetailsFragment.this.mDetailsParallax != null && (vh.getViewHolder() instanceof FullWidthDetailsOverviewRowPresenter.ViewHolder)) {
                        FullWidthDetailsOverviewRowPresenter.ViewHolder rowVh = (FullWidthDetailsOverviewRowPresenter.ViewHolder) vh.getViewHolder();
                        rowVh.getOverviewView().setTag(R.id.lb_parallax_source, DetailsFragment.this.mDetailsParallax);
                    }
                }
            });
        }
        return this.mRootView;
    }

    @Deprecated
    protected View inflateTitle(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return super.onInflateTitleView(inflater, parent, savedInstanceState);
    }

    @Override // androidx.leanback.app.BrandedFragment
    public View onInflateTitleView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflateTitle(inflater, parent, savedInstanceState);
    }

    void setVerticalGridViewLayout(VerticalGridView listview) {
        listview.setItemAlignmentOffset(-this.mContainerListAlignTop);
        listview.setItemAlignmentOffsetPercent(-1.0f);
        listview.setWindowAlignmentOffset(0);
        listview.setWindowAlignmentOffsetPercent(-1.0f);
        listview.setWindowAlignment(0);
    }

    protected void setupPresenter(Presenter rowPresenter) {
        if (rowPresenter instanceof FullWidthDetailsOverviewRowPresenter) {
            setupDetailsOverviewRowPresenter((FullWidthDetailsOverviewRowPresenter) rowPresenter);
        }
    }

    protected void setupDetailsOverviewRowPresenter(FullWidthDetailsOverviewRowPresenter presenter) {
        ItemAlignmentFacet facet = new ItemAlignmentFacet();
        ItemAlignmentFacet.ItemAlignmentDef alignDef1 = new ItemAlignmentFacet.ItemAlignmentDef();
        alignDef1.setItemAlignmentViewId(R.id.details_frame);
        alignDef1.setItemAlignmentOffset(-getResources().getDimensionPixelSize(R.dimen.lb_details_v2_align_pos_for_actions));
        alignDef1.setItemAlignmentOffsetPercent(0.0f);
        ItemAlignmentFacet.ItemAlignmentDef alignDef2 = new ItemAlignmentFacet.ItemAlignmentDef();
        alignDef2.setItemAlignmentViewId(R.id.details_frame);
        alignDef2.setItemAlignmentFocusViewId(R.id.details_overview_description);
        alignDef2.setItemAlignmentOffset(-getResources().getDimensionPixelSize(R.dimen.lb_details_v2_align_pos_for_description));
        alignDef2.setItemAlignmentOffsetPercent(0.0f);
        ItemAlignmentFacet.ItemAlignmentDef[] defs = {alignDef1, alignDef2};
        facet.setAlignmentDefs(defs);
        presenter.setFacet(ItemAlignmentFacet.class, facet);
    }

    VerticalGridView getVerticalGridView() {
        RowsFragment rowsFragment = this.mRowsFragment;
        if (rowsFragment == null) {
            return null;
        }
        return rowsFragment.getVerticalGridView();
    }

    public RowsFragment getRowsFragment() {
        return this.mRowsFragment;
    }

    private void setupChildFragmentLayout() {
        setVerticalGridViewLayout(this.mRowsFragment.getVerticalGridView());
    }

    public void setSelectedPosition(int position) {
        setSelectedPosition(position, true);
    }

    public void setSelectedPosition(int position, boolean smooth) {
        this.mSetSelectionRunnable.mPosition = position;
        this.mSetSelectionRunnable.mSmooth = smooth;
        if (getView() != null && getView().getHandler() != null) {
            getView().getHandler().post(this.mSetSelectionRunnable);
        }
    }

    void switchToVideo() {
        Fragment fragment = this.mVideoFragment;
        if (fragment != null && fragment.getView() != null) {
            this.mVideoFragment.getView().requestFocus();
        } else {
            this.mStateMachine.fireEvent(this.EVT_SWITCH_TO_VIDEO);
        }
    }

    void switchToRows() {
        this.mPendingFocusOnVideo = false;
        VerticalGridView verticalGridView = getVerticalGridView();
        if (verticalGridView != null && verticalGridView.getChildCount() > 0) {
            verticalGridView.requestFocus();
        }
    }

    final Fragment findOrCreateVideoFragment() {
        Fragment fragment = this.mVideoFragment;
        if (fragment != null) {
            return fragment;
        }
        Fragment fragment2 = getChildFragmentManager().findFragmentById(R.id.video_surface_container);
        if (fragment2 == null && this.mDetailsBackgroundController != null) {
            FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
            int i = R.id.video_surface_container;
            Fragment fragmentOnCreateVideoFragment = this.mDetailsBackgroundController.onCreateVideoFragment();
            fragment2 = fragmentOnCreateVideoFragment;
            ft2.add(i, fragmentOnCreateVideoFragment);
            ft2.commit();
            if (this.mPendingFocusOnVideo) {
                getView().post(new Runnable() { // from class: androidx.leanback.app.DetailsFragment.12
                    @Override // java.lang.Runnable
                    public void run() {
                        if (DetailsFragment.this.getView() != null) {
                            DetailsFragment.this.switchToVideo();
                        }
                        DetailsFragment.this.mPendingFocusOnVideo = false;
                    }
                });
            }
        }
        this.mVideoFragment = fragment2;
        return fragment2;
    }

    void onRowSelected(int selectedPosition, int selectedSubPosition) {
        ObjectAdapter adapter = getAdapter();
        RowsFragment rowsFragment = this.mRowsFragment;
        if (rowsFragment != null && rowsFragment.getView() != null && this.mRowsFragment.getView().hasFocus() && !this.mPendingFocusOnVideo && (adapter == null || adapter.size() == 0 || (getVerticalGridView().getSelectedPosition() == 0 && getVerticalGridView().getSelectedSubPosition() == 0))) {
            showTitle(true);
        } else {
            showTitle(false);
        }
        if (adapter != null && adapter.size() > selectedPosition) {
            VerticalGridView gridView = getVerticalGridView();
            int count = gridView.getChildCount();
            if (count > 0) {
                this.mStateMachine.fireEvent(this.EVT_DETAILS_ROW_LOADED);
            }
            for (int i = 0; i < count; i++) {
                ItemBridgeAdapter.ViewHolder bridgeViewHolder = (ItemBridgeAdapter.ViewHolder) gridView.getChildViewHolder(gridView.getChildAt(i));
                RowPresenter rowPresenter = (RowPresenter) bridgeViewHolder.getPresenter();
                onSetRowStatus(rowPresenter, rowPresenter.getRowViewHolder(bridgeViewHolder.getViewHolder()), bridgeViewHolder.getAbsoluteAdapterPosition(), selectedPosition, selectedSubPosition);
            }
        }
    }

    void onSafeStart() {
        DetailsFragmentBackgroundController detailsFragmentBackgroundController = this.mDetailsBackgroundController;
        if (detailsFragmentBackgroundController != null) {
            detailsFragmentBackgroundController.onStart();
        }
    }

    void onReturnTransitionStart() {
        DetailsFragmentBackgroundController detailsFragmentBackgroundController = this.mDetailsBackgroundController;
        if (detailsFragmentBackgroundController != null) {
            boolean isVideoVisible = detailsFragmentBackgroundController.disableVideoParallax();
            if (!isVideoVisible && this.mVideoFragment != null) {
                FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
                ft2.remove(this.mVideoFragment);
                ft2.commit();
                this.mVideoFragment = null;
            }
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        DetailsFragmentBackgroundController detailsFragmentBackgroundController = this.mDetailsBackgroundController;
        if (detailsFragmentBackgroundController != null) {
            detailsFragmentBackgroundController.onStop();
        }
        super.onStop();
    }

    protected void onSetRowStatus(RowPresenter presenter, RowPresenter.ViewHolder viewHolder, int adapterPosition, int selectedPosition, int selectedSubPosition) {
        if (presenter instanceof FullWidthDetailsOverviewRowPresenter) {
            onSetDetailsOverviewRowStatus((FullWidthDetailsOverviewRowPresenter) presenter, (FullWidthDetailsOverviewRowPresenter.ViewHolder) viewHolder, adapterPosition, selectedPosition, selectedSubPosition);
        }
    }

    protected void onSetDetailsOverviewRowStatus(FullWidthDetailsOverviewRowPresenter presenter, FullWidthDetailsOverviewRowPresenter.ViewHolder viewHolder, int adapterPosition, int selectedPosition, int selectedSubPosition) {
        if (selectedPosition > adapterPosition) {
            presenter.setState(viewHolder, 0);
            return;
        }
        if (selectedPosition == adapterPosition && selectedSubPosition == 1) {
            presenter.setState(viewHolder, 0);
        } else if (selectedPosition == adapterPosition && selectedSubPosition == 0) {
            presenter.setState(viewHolder, 1);
        } else {
            presenter.setState(viewHolder, 2);
        }
    }

    @Override // androidx.leanback.app.BrandedFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        setupChildFragmentLayout();
        this.mStateMachine.fireEvent(this.EVT_ONSTART);
        DetailsParallax detailsParallax = this.mDetailsParallax;
        if (detailsParallax != null) {
            detailsParallax.setRecyclerView(this.mRowsFragment.getVerticalGridView());
        }
        if (this.mPendingFocusOnVideo) {
            slideOutGridView();
        } else if (!getView().hasFocus()) {
            this.mRowsFragment.getVerticalGridView().requestFocus();
        }
    }

    @Override // androidx.leanback.app.BaseFragment
    protected Object createEntranceTransition() {
        return TransitionHelper.loadTransition(FragmentUtil.getContext(this), R.transition.lb_details_enter_transition);
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void runEntranceTransition(Object entranceTransition) {
        TransitionHelper.runTransition(this.mSceneAfterEntranceTransition, entranceTransition);
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void onEntranceTransitionEnd() {
        this.mRowsFragment.onTransitionEnd();
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void onEntranceTransitionPrepare() {
        this.mRowsFragment.onTransitionPrepare();
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void onEntranceTransitionStart() {
        this.mRowsFragment.onTransitionStart();
    }

    public DetailsParallax getParallax() {
        if (this.mDetailsParallax == null) {
            this.mDetailsParallax = new DetailsParallax();
            RowsFragment rowsFragment = this.mRowsFragment;
            if (rowsFragment != null && rowsFragment.getView() != null) {
                this.mDetailsParallax.setRecyclerView(this.mRowsFragment.getVerticalGridView());
            }
        }
        return this.mDetailsParallax;
    }

    void setBackgroundDrawable(Drawable drawable) {
        View view = this.mBackgroundView;
        if (view != null) {
            view.setBackground(drawable);
        }
        this.mBackgroundDrawable = drawable;
    }

    void setupDpadNavigation() {
        this.mRootView.setOnChildFocusListener(new BrowseFrameLayout.OnChildFocusListener() { // from class: androidx.leanback.app.DetailsFragment.13
            @Override // androidx.leanback.widget.BrowseFrameLayout.OnChildFocusListener
            public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
                return false;
            }

            @Override // androidx.leanback.widget.BrowseFrameLayout.OnChildFocusListener
            public void onRequestChildFocus(View child, View focused) {
                if (child != DetailsFragment.this.mRootView.getFocusedChild()) {
                    if (child.getId() == R.id.details_fragment_root) {
                        if (!DetailsFragment.this.mPendingFocusOnVideo) {
                            DetailsFragment.this.slideInGridView();
                            DetailsFragment.this.showTitle(true);
                            return;
                        }
                        return;
                    }
                    if (child.getId() == R.id.video_surface_container) {
                        DetailsFragment.this.slideOutGridView();
                        DetailsFragment.this.showTitle(false);
                    } else {
                        DetailsFragment.this.showTitle(true);
                    }
                }
            }
        });
        this.mRootView.setOnFocusSearchListener(new BrowseFrameLayout.OnFocusSearchListener() { // from class: androidx.leanback.app.DetailsFragment.14
            @Override // androidx.leanback.widget.BrowseFrameLayout.OnFocusSearchListener
            public View onFocusSearch(View focused, int direction) {
                if (DetailsFragment.this.mRowsFragment.getVerticalGridView() != null && DetailsFragment.this.mRowsFragment.getVerticalGridView().hasFocus()) {
                    if (direction == 33) {
                        if (DetailsFragment.this.mDetailsBackgroundController != null && DetailsFragment.this.mDetailsBackgroundController.canNavigateToVideoFragment() && DetailsFragment.this.mVideoFragment != null && DetailsFragment.this.mVideoFragment.getView() != null) {
                            return DetailsFragment.this.mVideoFragment.getView();
                        }
                        if (DetailsFragment.this.getTitleView() != null && DetailsFragment.this.getTitleView().hasFocusable()) {
                            return DetailsFragment.this.getTitleView();
                        }
                    }
                } else if (DetailsFragment.this.getTitleView() != null && DetailsFragment.this.getTitleView().hasFocus() && direction == 130 && DetailsFragment.this.mRowsFragment.getVerticalGridView() != null) {
                    return DetailsFragment.this.mRowsFragment.getVerticalGridView();
                }
                return focused;
            }
        });
        this.mRootView.setOnDispatchKeyListener(new View.OnKeyListener() { // from class: androidx.leanback.app.DetailsFragment.15
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (DetailsFragment.this.mVideoFragment != null && DetailsFragment.this.mVideoFragment.getView() != null && DetailsFragment.this.mVideoFragment.getView().hasFocus()) {
                    if ((keyCode == 4 || keyCode == 111) && DetailsFragment.this.getVerticalGridView().getChildCount() > 0) {
                        DetailsFragment.this.getVerticalGridView().requestFocus();
                        return true;
                    }
                    return false;
                }
                return false;
            }
        });
    }

    void slideOutGridView() {
        if (getVerticalGridView() != null) {
            getVerticalGridView().animateOut();
        }
    }

    void slideInGridView() {
        if (getVerticalGridView() != null) {
            getVerticalGridView().animateIn();
        }
    }
}
