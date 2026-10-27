package androidx.leanback.app;

import android.animation.TimeAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.leanback.R;
import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridView;
import androidx.leanback.widget.ViewHolderTask;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class RowsFragment extends BaseRowFragment implements BrowseFragment.MainFragmentRowsAdapterProvider, BrowseFragment.MainFragmentAdapterProvider {
    static final int ALIGN_TOP_NOT_SET = Integer.MIN_VALUE;
    static final boolean DEBUG = false;
    static final String TAG = "RowsFragment";
    ItemBridgeAdapter.AdapterListener mExternalAdapterListener;
    boolean mFreezeRows;
    private MainFragmentAdapter mMainFragmentAdapter;
    private MainFragmentRowsAdapter mMainFragmentRowsAdapter;
    BaseOnItemViewClickedListener mOnItemViewClickedListener;
    BaseOnItemViewSelectedListener mOnItemViewSelectedListener;
    private ArrayList<Presenter> mPresenterMapper;
    private RecyclerView.RecycledViewPool mRecycledViewPool;
    ItemBridgeAdapter.ViewHolder mSelectedViewHolder;
    private int mSubPosition;
    boolean mViewsCreated;
    boolean mExpand = true;
    private int mAlignedTop = Integer.MIN_VALUE;
    boolean mAfterEntranceTransition = true;
    private final ItemBridgeAdapter.AdapterListener mBridgeAdapterListener = new ItemBridgeAdapter.AdapterListener() { // from class: androidx.leanback.app.RowsFragment.1
        @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onAddPresenter(Presenter presenter, int type) {
            if (RowsFragment.this.mExternalAdapterListener != null) {
                RowsFragment.this.mExternalAdapterListener.onAddPresenter(presenter, type);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onCreate(ItemBridgeAdapter.ViewHolder vh) {
            VerticalGridView listView = RowsFragment.this.getVerticalGridView();
            if (listView != null) {
                listView.setClipChildren(RowsFragment.DEBUG);
            }
            RowsFragment.this.setupSharedViewPool(vh);
            RowsFragment.this.mViewsCreated = true;
            vh.setExtraObject(new RowViewHolderExtra(vh));
            RowsFragment.setRowViewSelected(vh, RowsFragment.DEBUG, true);
            if (RowsFragment.this.mExternalAdapterListener != null) {
                RowsFragment.this.mExternalAdapterListener.onCreate(vh);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onAttachedToWindow(ItemBridgeAdapter.ViewHolder vh) {
            RowsFragment.setRowViewExpanded(vh, RowsFragment.this.mExpand);
            RowPresenter rowPresenter = (RowPresenter) vh.getPresenter();
            RowPresenter.ViewHolder rowVh = rowPresenter.getRowViewHolder(vh.getViewHolder());
            rowPresenter.setEntranceTransitionState(rowVh, RowsFragment.this.mAfterEntranceTransition);
            rowVh.setOnItemViewSelectedListener(RowsFragment.this.mOnItemViewSelectedListener);
            rowVh.setOnItemViewClickedListener(RowsFragment.this.mOnItemViewClickedListener);
            rowPresenter.freeze(rowVh, RowsFragment.this.mFreezeRows);
            if (RowsFragment.this.mExternalAdapterListener != null) {
                RowsFragment.this.mExternalAdapterListener.onAttachedToWindow(vh);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onDetachedFromWindow(ItemBridgeAdapter.ViewHolder vh) {
            if (RowsFragment.this.mSelectedViewHolder == vh) {
                RowsFragment.setRowViewSelected(RowsFragment.this.mSelectedViewHolder, RowsFragment.DEBUG, true);
                RowsFragment.this.mSelectedViewHolder = null;
            }
            RowPresenter rowPresenter = (RowPresenter) vh.getPresenter();
            RowPresenter.ViewHolder rowVh = rowPresenter.getRowViewHolder(vh.getViewHolder());
            rowVh.setOnItemViewSelectedListener(null);
            rowVh.setOnItemViewClickedListener(null);
            if (RowsFragment.this.mExternalAdapterListener != null) {
                RowsFragment.this.mExternalAdapterListener.onDetachedFromWindow(vh);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onBind(ItemBridgeAdapter.ViewHolder vh) {
            if (RowsFragment.this.mExternalAdapterListener != null) {
                RowsFragment.this.mExternalAdapterListener.onBind(vh);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onUnbind(ItemBridgeAdapter.ViewHolder vh) {
            RowsFragment.setRowViewSelected(vh, RowsFragment.DEBUG, true);
            if (RowsFragment.this.mExternalAdapterListener != null) {
                RowsFragment.this.mExternalAdapterListener.onUnbind(vh);
            }
        }
    };

    @Override // androidx.leanback.app.BaseRowFragment
    public /* bridge */ /* synthetic */ int getSelectedPosition() {
        return super.getSelectedPosition();
    }

    @Override // androidx.leanback.app.BaseRowFragment, android.app.Fragment
    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.leanback.app.BaseRowFragment, android.app.Fragment
    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.leanback.app.BaseRowFragment
    public /* bridge */ /* synthetic */ void onTransitionStart() {
        super.onTransitionStart();
    }

    @Override // androidx.leanback.app.BaseRowFragment
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i) {
        super.setSelectedPosition(i);
    }

    @Override // androidx.leanback.app.BaseRowFragment
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i, boolean z) {
        super.setSelectedPosition(i, z);
    }

    @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapterProvider
    public BrowseFragment.MainFragmentAdapter getMainFragmentAdapter() {
        if (this.mMainFragmentAdapter == null) {
            this.mMainFragmentAdapter = new MainFragmentAdapter(this);
        }
        return this.mMainFragmentAdapter;
    }

    @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapterProvider
    public BrowseFragment.MainFragmentRowsAdapter getMainFragmentRowsAdapter() {
        if (this.mMainFragmentRowsAdapter == null) {
            this.mMainFragmentRowsAdapter = new MainFragmentRowsAdapter(this);
        }
        return this.mMainFragmentRowsAdapter;
    }

    static final class RowViewHolderExtra implements TimeAnimator.TimeListener {
        static final Interpolator sSelectAnimatorInterpolator = new DecelerateInterpolator(2.0f);
        final RowPresenter mRowPresenter;
        final Presenter.ViewHolder mRowViewHolder;
        final TimeAnimator mSelectAnimator = new TimeAnimator();
        final int mSelectAnimatorDurationInUse;
        final Interpolator mSelectAnimatorInterpolatorInUse;
        float mSelectLevelAnimDelta;
        float mSelectLevelAnimStart;

        RowViewHolderExtra(ItemBridgeAdapter.ViewHolder ibvh) {
            this.mRowPresenter = (RowPresenter) ibvh.getPresenter();
            this.mRowViewHolder = ibvh.getViewHolder();
            this.mSelectAnimator.setTimeListener(this);
            this.mSelectAnimatorDurationInUse = ibvh.itemView.getResources().getInteger(R.integer.lb_browse_rows_anim_duration);
            this.mSelectAnimatorInterpolatorInUse = sSelectAnimatorInterpolator;
        }

        @Override // android.animation.TimeAnimator.TimeListener
        public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
            if (this.mSelectAnimator.isRunning()) {
                updateSelect(totalTime, deltaTime);
            }
        }

        void updateSelect(long totalTime, long deltaTime) {
            float fraction;
            int i = this.mSelectAnimatorDurationInUse;
            if (totalTime >= i) {
                fraction = 1.0f;
                this.mSelectAnimator.end();
            } else {
                fraction = (float) (totalTime / ((double) i));
            }
            Interpolator interpolator = this.mSelectAnimatorInterpolatorInUse;
            if (interpolator != null) {
                fraction = interpolator.getInterpolation(fraction);
            }
            float level = this.mSelectLevelAnimStart + (this.mSelectLevelAnimDelta * fraction);
            this.mRowPresenter.setSelectLevel(this.mRowViewHolder, level);
        }

        void animateSelect(boolean select, boolean immediate) {
            this.mSelectAnimator.end();
            float end = select ? 1.0f : 0.0f;
            if (immediate) {
                this.mRowPresenter.setSelectLevel(this.mRowViewHolder, end);
            } else if (this.mRowPresenter.getSelectLevel(this.mRowViewHolder) != end) {
                float selectLevel = this.mRowPresenter.getSelectLevel(this.mRowViewHolder);
                this.mSelectLevelAnimStart = selectLevel;
                this.mSelectLevelAnimDelta = end - selectLevel;
                this.mSelectAnimator.start();
            }
        }
    }

    @Override // androidx.leanback.app.BaseRowFragment
    protected VerticalGridView findGridViewFromRoot(View view) {
        return (VerticalGridView) view.findViewById(R.id.container_list);
    }

    public void setOnItemViewClickedListener(BaseOnItemViewClickedListener listener) {
        this.mOnItemViewClickedListener = listener;
        if (this.mViewsCreated) {
            throw new IllegalStateException("Item clicked listener must be set before views are created");
        }
    }

    public BaseOnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    @Deprecated
    public void enableRowScaling(boolean enable) {
    }

    public void setExpand(boolean expand) {
        this.mExpand = expand;
        VerticalGridView listView = getVerticalGridView();
        if (listView != null) {
            int count = listView.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = listView.getChildAt(i);
                ItemBridgeAdapter.ViewHolder vh = (ItemBridgeAdapter.ViewHolder) listView.getChildViewHolder(view);
                setRowViewExpanded(vh, this.mExpand);
            }
        }
    }

    public void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener listener) {
        this.mOnItemViewSelectedListener = listener;
        VerticalGridView listView = getVerticalGridView();
        if (listView != null) {
            int count = listView.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = listView.getChildAt(i);
                ItemBridgeAdapter.ViewHolder ibvh = (ItemBridgeAdapter.ViewHolder) listView.getChildViewHolder(view);
                getRowViewHolder(ibvh).setOnItemViewSelectedListener(this.mOnItemViewSelectedListener);
            }
        }
    }

    public BaseOnItemViewSelectedListener getOnItemViewSelectedListener() {
        return this.mOnItemViewSelectedListener;
    }

    @Override // androidx.leanback.app.BaseRowFragment
    void onRowSelected(RecyclerView parent, RecyclerView.ViewHolder viewHolder, int position, int subposition) {
        if (this.mSelectedViewHolder != viewHolder || this.mSubPosition != subposition) {
            this.mSubPosition = subposition;
            ItemBridgeAdapter.ViewHolder viewHolder2 = this.mSelectedViewHolder;
            if (viewHolder2 != null) {
                setRowViewSelected(viewHolder2, DEBUG, DEBUG);
            }
            ItemBridgeAdapter.ViewHolder viewHolder3 = (ItemBridgeAdapter.ViewHolder) viewHolder;
            this.mSelectedViewHolder = viewHolder3;
            if (viewHolder3 != null) {
                setRowViewSelected(viewHolder3, true, DEBUG);
            }
        }
        MainFragmentAdapter mainFragmentAdapter = this.mMainFragmentAdapter;
        if (mainFragmentAdapter != null) {
            mainFragmentAdapter.getFragmentHost().showTitleView(position <= 0);
        }
    }

    public RowPresenter.ViewHolder getRowViewHolder(int position) {
        VerticalGridView verticalView = getVerticalGridView();
        if (verticalView == null) {
            return null;
        }
        return getRowViewHolder((ItemBridgeAdapter.ViewHolder) verticalView.findViewHolderForAdapterPosition(position));
    }

    @Override // androidx.leanback.app.BaseRowFragment
    int getLayoutResourceId() {
        return R.layout.lb_rows_fragment;
    }

    @Override // androidx.leanback.app.BaseRowFragment, android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVerticalGridView().setItemAlignmentViewId(R.id.row_content);
        getVerticalGridView().setSaveChildrenPolicy(2);
        setAlignment(this.mAlignedTop);
        this.mRecycledViewPool = null;
        this.mPresenterMapper = null;
        MainFragmentAdapter mainFragmentAdapter = this.mMainFragmentAdapter;
        if (mainFragmentAdapter != null) {
            mainFragmentAdapter.getFragmentHost().notifyViewCreated(this.mMainFragmentAdapter);
        }
    }

    @Override // androidx.leanback.app.BaseRowFragment, android.app.Fragment
    public void onDestroyView() {
        this.mViewsCreated = DEBUG;
        super.onDestroyView();
    }

    void setExternalAdapterListener(ItemBridgeAdapter.AdapterListener listener) {
        this.mExternalAdapterListener = listener;
    }

    static void setRowViewExpanded(ItemBridgeAdapter.ViewHolder vh, boolean expanded) {
        ((RowPresenter) vh.getPresenter()).setRowViewExpanded(vh.getViewHolder(), expanded);
    }

    static void setRowViewSelected(ItemBridgeAdapter.ViewHolder vh, boolean selected, boolean immediate) {
        RowViewHolderExtra extra = (RowViewHolderExtra) vh.getExtraObject();
        extra.animateSelect(selected, immediate);
        ((RowPresenter) vh.getPresenter()).setRowViewSelected(vh.getViewHolder(), selected);
    }

    void setupSharedViewPool(ItemBridgeAdapter.ViewHolder bridgeVh) {
        RowPresenter rowPresenter = (RowPresenter) bridgeVh.getPresenter();
        RowPresenter.ViewHolder rowVh = rowPresenter.getRowViewHolder(bridgeVh.getViewHolder());
        if (rowVh instanceof ListRowPresenter.ViewHolder) {
            HorizontalGridView view = ((ListRowPresenter.ViewHolder) rowVh).getGridView();
            RecyclerView.RecycledViewPool recycledViewPool = this.mRecycledViewPool;
            if (recycledViewPool == null) {
                this.mRecycledViewPool = view.getRecycledViewPool();
            } else {
                view.setRecycledViewPool(recycledViewPool);
            }
            ItemBridgeAdapter bridgeAdapter = ((ListRowPresenter.ViewHolder) rowVh).getBridgeAdapter();
            ArrayList<Presenter> arrayList = this.mPresenterMapper;
            if (arrayList == null) {
                this.mPresenterMapper = bridgeAdapter.getPresenterMapper();
            } else {
                bridgeAdapter.setPresenterMapper(arrayList);
            }
        }
    }

    @Override // androidx.leanback.app.BaseRowFragment
    void updateAdapter() {
        super.updateAdapter();
        this.mSelectedViewHolder = null;
        this.mViewsCreated = DEBUG;
        ItemBridgeAdapter adapter = getBridgeAdapter();
        if (adapter != null) {
            adapter.setAdapterListener(this.mBridgeAdapterListener);
        }
    }

    @Override // androidx.leanback.app.BaseRowFragment
    public boolean onTransitionPrepare() {
        boolean prepared = super.onTransitionPrepare();
        if (prepared) {
            freezeRows(true);
        }
        return prepared;
    }

    @Override // androidx.leanback.app.BaseRowFragment
    public void onTransitionEnd() {
        super.onTransitionEnd();
        freezeRows(DEBUG);
    }

    private void freezeRows(boolean freeze) {
        this.mFreezeRows = freeze;
        VerticalGridView verticalView = getVerticalGridView();
        if (verticalView != null) {
            int count = verticalView.getChildCount();
            for (int i = 0; i < count; i++) {
                ItemBridgeAdapter.ViewHolder ibvh = (ItemBridgeAdapter.ViewHolder) verticalView.getChildViewHolder(verticalView.getChildAt(i));
                RowPresenter rowPresenter = (RowPresenter) ibvh.getPresenter();
                RowPresenter.ViewHolder vh = rowPresenter.getRowViewHolder(ibvh.getViewHolder());
                rowPresenter.freeze(vh, freeze);
            }
        }
    }

    public void setEntranceTransitionState(boolean afterTransition) {
        this.mAfterEntranceTransition = afterTransition;
        VerticalGridView verticalView = getVerticalGridView();
        if (verticalView != null) {
            int count = verticalView.getChildCount();
            for (int i = 0; i < count; i++) {
                ItemBridgeAdapter.ViewHolder ibvh = (ItemBridgeAdapter.ViewHolder) verticalView.getChildViewHolder(verticalView.getChildAt(i));
                RowPresenter rowPresenter = (RowPresenter) ibvh.getPresenter();
                RowPresenter.ViewHolder vh = rowPresenter.getRowViewHolder(ibvh.getViewHolder());
                rowPresenter.setEntranceTransitionState(vh, this.mAfterEntranceTransition);
            }
        }
    }

    public void setSelectedPosition(int rowPosition, boolean smooth, final Presenter.ViewHolderTask rowHolderTask) {
        VerticalGridView verticalView = getVerticalGridView();
        if (verticalView == null) {
            return;
        }
        ViewHolderTask task = null;
        if (rowHolderTask != null) {
            task = new ViewHolderTask() { // from class: androidx.leanback.app.RowsFragment.2
                @Override // androidx.leanback.widget.ViewHolderTask
                public void run(final RecyclerView.ViewHolder rvh) {
                    rvh.itemView.post(new Runnable() { // from class: androidx.leanback.app.RowsFragment.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            rowHolderTask.run(RowsFragment.getRowViewHolder((ItemBridgeAdapter.ViewHolder) rvh));
                        }
                    });
                }
            };
        }
        if (smooth) {
            verticalView.setSelectedPositionSmooth(rowPosition, task);
        } else {
            verticalView.setSelectedPosition(rowPosition, task);
        }
    }

    static RowPresenter.ViewHolder getRowViewHolder(ItemBridgeAdapter.ViewHolder ibvh) {
        if (ibvh == null) {
            return null;
        }
        RowPresenter rowPresenter = (RowPresenter) ibvh.getPresenter();
        return rowPresenter.getRowViewHolder(ibvh.getViewHolder());
    }

    public boolean isScrolling() {
        if (getVerticalGridView() == null || getVerticalGridView().getScrollState() == 0) {
            return DEBUG;
        }
        return true;
    }

    @Override // androidx.leanback.app.BaseRowFragment
    public void setAlignment(int windowAlignOffsetFromTop) {
        if (windowAlignOffsetFromTop == Integer.MIN_VALUE) {
            return;
        }
        this.mAlignedTop = windowAlignOffsetFromTop;
        VerticalGridView gridView = getVerticalGridView();
        if (gridView != null) {
            gridView.setItemAlignmentOffset(0);
            gridView.setItemAlignmentOffsetPercent(-1.0f);
            gridView.setItemAlignmentOffsetWithPadding(true);
            gridView.setWindowAlignmentOffset(this.mAlignedTop);
            gridView.setWindowAlignmentOffsetPercent(-1.0f);
            gridView.setWindowAlignment(0);
        }
    }

    public RowPresenter.ViewHolder findRowViewHolderByPosition(int position) {
        if (this.mVerticalGridView == null) {
            return null;
        }
        return getRowViewHolder((ItemBridgeAdapter.ViewHolder) this.mVerticalGridView.findViewHolderForAdapterPosition(position));
    }

    public static class MainFragmentAdapter extends BrowseFragment.MainFragmentAdapter<RowsFragment> {
        public MainFragmentAdapter(RowsFragment fragment) {
            super(fragment);
            setScalingEnabled(true);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public boolean isScrolling() {
            return getFragment().isScrolling();
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public void setExpand(boolean expand) {
            getFragment().setExpand(expand);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public void setEntranceTransitionState(boolean state) {
            getFragment().setEntranceTransitionState(state);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public void setAlignment(int windowAlignOffsetFromTop) {
            getFragment().setAlignment(windowAlignOffsetFromTop);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public boolean onTransitionPrepare() {
            return getFragment().onTransitionPrepare();
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public void onTransitionStart() {
            getFragment().onTransitionStart();
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentAdapter
        public void onTransitionEnd() {
            getFragment().onTransitionEnd();
        }
    }

    @Deprecated
    public static class MainFragmentRowsAdapter extends BrowseFragment.MainFragmentRowsAdapter<RowsFragment> {
        public MainFragmentRowsAdapter(RowsFragment fragment) {
            super(fragment);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public void setAdapter(ObjectAdapter adapter) {
            getFragment().setAdapter(adapter);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public void setOnItemViewClickedListener(OnItemViewClickedListener listener) {
            getFragment().setOnItemViewClickedListener(listener);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public void setOnItemViewSelectedListener(OnItemViewSelectedListener listener) {
            getFragment().setOnItemViewSelectedListener(listener);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public void setSelectedPosition(int rowPosition, boolean smooth, Presenter.ViewHolderTask rowHolderTask) {
            getFragment().setSelectedPosition(rowPosition, smooth, rowHolderTask);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public void setSelectedPosition(int rowPosition, boolean smooth) {
            getFragment().setSelectedPosition(rowPosition, smooth);
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public int getSelectedPosition() {
            return getFragment().getSelectedPosition();
        }

        @Override // androidx.leanback.app.BrowseFragment.MainFragmentRowsAdapter
        public RowPresenter.ViewHolder findRowViewHolderByPosition(int position) {
            return getFragment().findRowViewHolderByPosition(position);
        }
    }
}
