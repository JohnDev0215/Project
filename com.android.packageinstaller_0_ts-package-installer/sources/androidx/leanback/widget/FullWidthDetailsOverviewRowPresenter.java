package androidx.leanback.widget;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.leanback.R;
import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.DetailsOverviewLogoPresenter;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class FullWidthDetailsOverviewRowPresenter extends RowPresenter {
    public static final int ALIGN_MODE_MIDDLE = 1;
    public static final int ALIGN_MODE_START = 0;
    static final boolean DEBUG = false;
    public static final int STATE_FULL = 1;
    public static final int STATE_HALF = 0;
    public static final int STATE_SMALL = 2;
    static final String TAG = "FullWidthDetailsRP";
    static final Handler sHandler = new Handler();
    OnActionClickedListener mActionClickedListener;
    private int mActionsBackgroundColor;
    private boolean mActionsBackgroundColorSet;
    private int mAlignmentMode;
    private int mBackgroundColor;
    private boolean mBackgroundColorSet;
    final DetailsOverviewLogoPresenter mDetailsOverviewLogoPresenter;
    final Presenter mDetailsPresenter;
    protected int mInitialState;
    private Listener mListener;
    private boolean mParticipatingEntranceTransition;

    public static abstract class Listener {
        public void onBindLogo(ViewHolder vh) {
        }
    }

    class ActionsItemBridgeAdapter extends ItemBridgeAdapter {
        ViewHolder mViewHolder;

        ActionsItemBridgeAdapter(ViewHolder viewHolder) {
            this.mViewHolder = viewHolder;
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onBind(final ItemBridgeAdapter.ViewHolder ibvh) {
            if (this.mViewHolder.getOnItemViewClickedListener() != null || FullWidthDetailsOverviewRowPresenter.this.mActionClickedListener != null) {
                ibvh.getPresenter().setOnClickListener(ibvh.getViewHolder(), new View.OnClickListener() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.ActionsItemBridgeAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        if (ActionsItemBridgeAdapter.this.mViewHolder.getOnItemViewClickedListener() != null) {
                            ActionsItemBridgeAdapter.this.mViewHolder.getOnItemViewClickedListener().onItemClicked(ibvh.getViewHolder(), ibvh.getItem(), ActionsItemBridgeAdapter.this.mViewHolder, ActionsItemBridgeAdapter.this.mViewHolder.getRow());
                        }
                        if (FullWidthDetailsOverviewRowPresenter.this.mActionClickedListener != null) {
                            FullWidthDetailsOverviewRowPresenter.this.mActionClickedListener.onActionClicked((Action) ibvh.getItem());
                        }
                    }
                });
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onUnbind(ItemBridgeAdapter.ViewHolder ibvh) {
            if (this.mViewHolder.getOnItemViewClickedListener() != null || FullWidthDetailsOverviewRowPresenter.this.mActionClickedListener != null) {
                ibvh.getPresenter().setOnClickListener(ibvh.getViewHolder(), null);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onAttachedToWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            viewHolder.itemView.removeOnLayoutChangeListener(this.mViewHolder.mLayoutChangeListener);
            viewHolder.itemView.addOnLayoutChangeListener(this.mViewHolder.mLayoutChangeListener);
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onDetachedFromWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            viewHolder.itemView.removeOnLayoutChangeListener(this.mViewHolder.mLayoutChangeListener);
            this.mViewHolder.checkFirstAndLastPosition(FullWidthDetailsOverviewRowPresenter.DEBUG);
        }
    }

    public class ViewHolder extends RowPresenter.ViewHolder {
        ItemBridgeAdapter mActionBridgeAdapter;
        final HorizontalGridView mActionsRow;
        final OnChildSelectedListener mChildSelectedListener;
        final ViewGroup mDetailsDescriptionFrame;
        final Presenter.ViewHolder mDetailsDescriptionViewHolder;
        final DetailsOverviewLogoPresenter.ViewHolder mDetailsLogoViewHolder;
        final View.OnLayoutChangeListener mLayoutChangeListener;
        int mNumItems;
        final FrameLayout mOverviewFrame;
        final ViewGroup mOverviewRoot;
        protected final DetailsOverviewRow.Listener mRowListener;
        final RecyclerView.OnScrollListener mScrollListener;
        int mState;
        final Runnable mUpdateDrawableCallback;

        protected DetailsOverviewRow.Listener createRowListener() {
            return new DetailsOverviewRowListener();
        }

        public class DetailsOverviewRowListener extends DetailsOverviewRow.Listener {
            public DetailsOverviewRowListener() {
            }

            @Override // androidx.leanback.widget.DetailsOverviewRow.Listener
            public void onImageDrawableChanged(DetailsOverviewRow row) {
                FullWidthDetailsOverviewRowPresenter.sHandler.removeCallbacks(ViewHolder.this.mUpdateDrawableCallback);
                FullWidthDetailsOverviewRowPresenter.sHandler.post(ViewHolder.this.mUpdateDrawableCallback);
            }

            @Override // androidx.leanback.widget.DetailsOverviewRow.Listener
            public void onItemChanged(DetailsOverviewRow row) {
                if (ViewHolder.this.mDetailsDescriptionViewHolder != null) {
                    FullWidthDetailsOverviewRowPresenter.this.mDetailsPresenter.onUnbindViewHolder(ViewHolder.this.mDetailsDescriptionViewHolder);
                }
                FullWidthDetailsOverviewRowPresenter.this.mDetailsPresenter.onBindViewHolder(ViewHolder.this.mDetailsDescriptionViewHolder, row.getItem());
            }

            @Override // androidx.leanback.widget.DetailsOverviewRow.Listener
            public void onActionsAdapterChanged(DetailsOverviewRow row) {
                ViewHolder.this.bindActions(row.getActionsAdapter());
            }
        }

        void bindActions(ObjectAdapter adapter) {
            this.mActionBridgeAdapter.setAdapter(adapter);
            this.mActionsRow.setAdapter(this.mActionBridgeAdapter);
            this.mNumItems = this.mActionBridgeAdapter.getItemCount();
        }

        void onBind() {
            DetailsOverviewRow row = (DetailsOverviewRow) getRow();
            bindActions(row.getActionsAdapter());
            row.addListener(this.mRowListener);
        }

        void onUnbind() {
            DetailsOverviewRow row = (DetailsOverviewRow) getRow();
            row.removeListener(this.mRowListener);
            FullWidthDetailsOverviewRowPresenter.sHandler.removeCallbacks(this.mUpdateDrawableCallback);
        }

        void dispatchItemSelection(View view) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForPosition;
            if (!isSelected()) {
                return;
            }
            if (view != null) {
                viewHolderFindViewHolderForPosition = this.mActionsRow.getChildViewHolder(view);
            } else {
                HorizontalGridView horizontalGridView = this.mActionsRow;
                viewHolderFindViewHolderForPosition = horizontalGridView.findViewHolderForPosition(horizontalGridView.getSelectedPosition());
            }
            ItemBridgeAdapter.ViewHolder ibvh = (ItemBridgeAdapter.ViewHolder) viewHolderFindViewHolderForPosition;
            if (ibvh == null) {
                if (getOnItemViewSelectedListener() != null) {
                    getOnItemViewSelectedListener().onItemSelected(null, null, this, getRow());
                }
            } else if (getOnItemViewSelectedListener() != null) {
                getOnItemViewSelectedListener().onItemSelected(ibvh.getViewHolder(), ibvh.getItem(), this, getRow());
            }
        }

        void checkFirstAndLastPosition(boolean fromScroll) {
            boolean z = true;
            RecyclerView.ViewHolder viewHolder = this.mActionsRow.findViewHolderForPosition(this.mNumItems - 1);
            if (viewHolder == null || viewHolder.itemView.getRight() > this.mActionsRow.getWidth()) {
            }
            RecyclerView.ViewHolder viewHolder2 = this.mActionsRow.findViewHolderForPosition(0);
            if (viewHolder2 != null && viewHolder2.itemView.getLeft() >= 0) {
                z = false;
            }
        }

        public ViewHolder(View rootView, Presenter detailsPresenter, DetailsOverviewLogoPresenter logoPresenter) {
            super(rootView);
            this.mRowListener = createRowListener();
            this.mState = 0;
            this.mUpdateDrawableCallback = new Runnable() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.ViewHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    Row row = ViewHolder.this.getRow();
                    if (row == null) {
                        return;
                    }
                    FullWidthDetailsOverviewRowPresenter.this.mDetailsOverviewLogoPresenter.onBindViewHolder(ViewHolder.this.mDetailsLogoViewHolder, row);
                }
            };
            this.mLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.ViewHolder.2
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    ViewHolder.this.checkFirstAndLastPosition(FullWidthDetailsOverviewRowPresenter.DEBUG);
                }
            };
            this.mChildSelectedListener = new OnChildSelectedListener() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.ViewHolder.3
                @Override // androidx.leanback.widget.OnChildSelectedListener
                public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                    ViewHolder.this.dispatchItemSelection(view);
                }
            };
            this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.ViewHolder.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    ViewHolder.this.checkFirstAndLastPosition(true);
                }
            };
            this.mOverviewRoot = (ViewGroup) rootView.findViewById(R.id.details_root);
            this.mOverviewFrame = (FrameLayout) rootView.findViewById(R.id.details_frame);
            this.mDetailsDescriptionFrame = (ViewGroup) rootView.findViewById(R.id.details_overview_description);
            HorizontalGridView horizontalGridView = (HorizontalGridView) this.mOverviewFrame.findViewById(R.id.details_overview_actions);
            this.mActionsRow = horizontalGridView;
            horizontalGridView.setHasOverlappingRendering(FullWidthDetailsOverviewRowPresenter.DEBUG);
            this.mActionsRow.setOnScrollListener(this.mScrollListener);
            this.mActionsRow.setAdapter(this.mActionBridgeAdapter);
            this.mActionsRow.setOnChildSelectedListener(this.mChildSelectedListener);
            int fadeLength = rootView.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_actions_fade_size);
            this.mActionsRow.setFadingRightEdgeLength(fadeLength);
            this.mActionsRow.setFadingLeftEdgeLength(fadeLength);
            Presenter.ViewHolder viewHolderOnCreateViewHolder = detailsPresenter.onCreateViewHolder(this.mDetailsDescriptionFrame);
            this.mDetailsDescriptionViewHolder = viewHolderOnCreateViewHolder;
            this.mDetailsDescriptionFrame.addView(viewHolderOnCreateViewHolder.view);
            DetailsOverviewLogoPresenter.ViewHolder viewHolder = (DetailsOverviewLogoPresenter.ViewHolder) logoPresenter.onCreateViewHolder(this.mOverviewRoot);
            this.mDetailsLogoViewHolder = viewHolder;
            this.mOverviewRoot.addView(viewHolder.view);
        }

        public final ViewGroup getOverviewView() {
            return this.mOverviewFrame;
        }

        public final DetailsOverviewLogoPresenter.ViewHolder getLogoViewHolder() {
            return this.mDetailsLogoViewHolder;
        }

        public final Presenter.ViewHolder getDetailsDescriptionViewHolder() {
            return this.mDetailsDescriptionViewHolder;
        }

        public final ViewGroup getDetailsDescriptionFrame() {
            return this.mDetailsDescriptionFrame;
        }

        public final ViewGroup getActionsRow() {
            return this.mActionsRow;
        }

        public final int getState() {
            return this.mState;
        }
    }

    public FullWidthDetailsOverviewRowPresenter(Presenter detailsPresenter) {
        this(detailsPresenter, new DetailsOverviewLogoPresenter());
    }

    public FullWidthDetailsOverviewRowPresenter(Presenter detailsPresenter, DetailsOverviewLogoPresenter logoPresenter) {
        this.mInitialState = 0;
        this.mBackgroundColor = 0;
        this.mActionsBackgroundColor = 0;
        setHeaderPresenter(null);
        setSelectEffectEnabled(DEBUG);
        this.mDetailsPresenter = detailsPresenter;
        this.mDetailsOverviewLogoPresenter = logoPresenter;
    }

    public void setOnActionClickedListener(OnActionClickedListener listener) {
        this.mActionClickedListener = listener;
    }

    public OnActionClickedListener getOnActionClickedListener() {
        return this.mActionClickedListener;
    }

    public final void setBackgroundColor(int color) {
        this.mBackgroundColor = color;
        this.mBackgroundColorSet = true;
    }

    public final int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public final void setActionsBackgroundColor(int color) {
        this.mActionsBackgroundColor = color;
        this.mActionsBackgroundColorSet = true;
    }

    public final int getActionsBackgroundColor() {
        return this.mActionsBackgroundColor;
    }

    public final boolean isParticipatingEntranceTransition() {
        return this.mParticipatingEntranceTransition;
    }

    public final void setParticipatingEntranceTransition(boolean participating) {
        this.mParticipatingEntranceTransition = participating;
    }

    public final void setInitialState(int state) {
        this.mInitialState = state;
    }

    public final int getInitialState() {
        return this.mInitialState;
    }

    public final void setAlignmentMode(int alignmentMode) {
        this.mAlignmentMode = alignmentMode;
    }

    public final int getAlignmentMode() {
        return this.mAlignmentMode;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected boolean isClippingChildren() {
        return true;
    }

    public final void setListener(Listener listener) {
        this.mListener = listener;
    }

    protected int getLayoutResourceId() {
        return R.layout.lb_fullwidth_details_overview;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayoutResourceId(), parent, DEBUG);
        final ViewHolder vh = new ViewHolder(v, this.mDetailsPresenter, this.mDetailsOverviewLogoPresenter);
        this.mDetailsOverviewLogoPresenter.setContext(vh.mDetailsLogoViewHolder, vh, this);
        setState(vh, this.mInitialState);
        vh.mActionBridgeAdapter = new ActionsItemBridgeAdapter(vh);
        View overview = vh.mOverviewFrame;
        if (this.mBackgroundColorSet) {
            overview.setBackgroundColor(this.mBackgroundColor);
        }
        if (this.mActionsBackgroundColorSet) {
            overview.findViewById(R.id.details_overview_actions_background).setBackgroundColor(this.mActionsBackgroundColor);
        }
        RoundedRectHelper.setClipToRoundedOutline(overview, true);
        if (!getSelectEffectEnabled()) {
            vh.mOverviewFrame.setForeground(null);
        }
        vh.mActionsRow.setOnUnhandledKeyListener(new BaseGridView.OnUnhandledKeyListener() { // from class: androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter.1
            @Override // androidx.leanback.widget.BaseGridView.OnUnhandledKeyListener
            public boolean onUnhandledKey(KeyEvent event) {
                if (vh.getOnKeyListener() != null) {
                    return vh.getOnKeyListener().onKey(vh.view, event.getKeyCode(), event);
                }
                return FullWidthDetailsOverviewRowPresenter.DEBUG;
            }
        });
        return vh;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        super.onBindRowViewHolder(holder, item);
        DetailsOverviewRow row = (DetailsOverviewRow) item;
        ViewHolder vh = (ViewHolder) holder;
        this.mDetailsOverviewLogoPresenter.onBindViewHolder(vh.mDetailsLogoViewHolder, row);
        this.mDetailsPresenter.onBindViewHolder(vh.mDetailsDescriptionViewHolder, row.getItem());
        vh.onBind();
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onUnbindRowViewHolder(RowPresenter.ViewHolder holder) {
        ViewHolder vh = (ViewHolder) holder;
        vh.onUnbind();
        this.mDetailsPresenter.onUnbindViewHolder(vh.mDetailsDescriptionViewHolder);
        this.mDetailsOverviewLogoPresenter.onUnbindViewHolder(vh.mDetailsLogoViewHolder);
        super.onUnbindRowViewHolder(holder);
    }

    @Override // androidx.leanback.widget.RowPresenter
    public final boolean isUsingDefaultSelectEffect() {
        return DEBUG;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onSelectLevelChanged(RowPresenter.ViewHolder holder) {
        super.onSelectLevelChanged(holder);
        if (getSelectEffectEnabled()) {
            ViewHolder vh = (ViewHolder) holder;
            int dimmedColor = vh.mColorDimmer.getPaint().getColor();
            ((ColorDrawable) vh.mOverviewFrame.getForeground().mutate()).setColor(dimmedColor);
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewAttachedToWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewAttachedToWindow(vh);
        ViewHolder viewHolder = (ViewHolder) vh;
        this.mDetailsPresenter.onViewAttachedToWindow(viewHolder.mDetailsDescriptionViewHolder);
        this.mDetailsOverviewLogoPresenter.onViewAttachedToWindow(viewHolder.mDetailsLogoViewHolder);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewDetachedFromWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewDetachedFromWindow(vh);
        ViewHolder viewHolder = (ViewHolder) vh;
        this.mDetailsPresenter.onViewDetachedFromWindow(viewHolder.mDetailsDescriptionViewHolder);
        this.mDetailsOverviewLogoPresenter.onViewDetachedFromWindow(viewHolder.mDetailsLogoViewHolder);
    }

    public final void notifyOnBindLogo(ViewHolder viewHolder) {
        onLayoutOverviewFrame(viewHolder, viewHolder.getState(), true);
        onLayoutLogo(viewHolder, viewHolder.getState(), true);
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onBindLogo(viewHolder);
        }
    }

    protected void onLayoutLogo(ViewHolder viewHolder, int oldState, boolean logoChanged) {
        View v = viewHolder.getLogoViewHolder().view;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        if (this.mAlignmentMode != 1) {
            lp.setMarginStart(v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_logo_margin_start));
        } else {
            lp.setMarginStart(v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_left) - lp.width);
        }
        int state = viewHolder.getState();
        if (state == 0) {
            lp.topMargin = v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_blank_height) + v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_actions_height) + v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_description_margin_top);
        } else if (state != 2) {
            lp.topMargin = v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_blank_height) - (lp.height / 2);
        } else {
            lp.topMargin = 0;
        }
        v.setLayoutParams(lp);
    }

    protected void onLayoutOverviewFrame(ViewHolder viewHolder, int oldState, boolean logoChanged) {
        int frameMarginStart;
        int descriptionMarginStart;
        boolean wasBanner = oldState == 2;
        boolean isBanner = viewHolder.getState() == 2;
        if (wasBanner != isBanner || logoChanged) {
            Resources res = viewHolder.view.getResources();
            int logoWidth = 0;
            if (this.mDetailsOverviewLogoPresenter.isBoundToImage(viewHolder.getLogoViewHolder(), (DetailsOverviewRow) viewHolder.getRow())) {
                logoWidth = viewHolder.getLogoViewHolder().view.getLayoutParams().width;
            }
            if (this.mAlignmentMode != 1) {
                if (isBanner) {
                    frameMarginStart = res.getDimensionPixelSize(R.dimen.lb_details_v2_logo_margin_start);
                    descriptionMarginStart = logoWidth;
                } else {
                    frameMarginStart = 0;
                    descriptionMarginStart = logoWidth + res.getDimensionPixelSize(R.dimen.lb_details_v2_logo_margin_start);
                }
            } else if (isBanner) {
                frameMarginStart = res.getDimensionPixelSize(R.dimen.lb_details_v2_left) - logoWidth;
                descriptionMarginStart = logoWidth;
            } else {
                frameMarginStart = 0;
                descriptionMarginStart = res.getDimensionPixelSize(R.dimen.lb_details_v2_left);
            }
            ViewGroup.MarginLayoutParams lpFrame = (ViewGroup.MarginLayoutParams) viewHolder.getOverviewView().getLayoutParams();
            lpFrame.topMargin = isBanner ? 0 : res.getDimensionPixelSize(R.dimen.lb_details_v2_blank_height);
            lpFrame.rightMargin = frameMarginStart;
            lpFrame.leftMargin = frameMarginStart;
            viewHolder.getOverviewView().setLayoutParams(lpFrame);
            View description = viewHolder.getDetailsDescriptionFrame();
            ViewGroup.MarginLayoutParams lpDesc = (ViewGroup.MarginLayoutParams) description.getLayoutParams();
            lpDesc.setMarginStart(descriptionMarginStart);
            description.setLayoutParams(lpDesc);
            View action = viewHolder.getActionsRow();
            ViewGroup.MarginLayoutParams lpActions = (ViewGroup.MarginLayoutParams) action.getLayoutParams();
            lpActions.setMarginStart(descriptionMarginStart);
            lpActions.height = isBanner ? 0 : res.getDimensionPixelSize(R.dimen.lb_details_v2_actions_height);
            action.setLayoutParams(lpActions);
        }
    }

    public final void setState(ViewHolder viewHolder, int state) {
        if (viewHolder.getState() != state) {
            int oldState = viewHolder.getState();
            viewHolder.mState = state;
            onStateChanged(viewHolder, oldState);
        }
    }

    protected void onStateChanged(ViewHolder viewHolder, int oldState) {
        onLayoutOverviewFrame(viewHolder, oldState, DEBUG);
        onLayoutLogo(viewHolder, oldState, DEBUG);
    }

    @Override // androidx.leanback.widget.RowPresenter
    public void setEntranceTransitionState(RowPresenter.ViewHolder holder, boolean afterEntrance) {
        super.setEntranceTransitionState(holder, afterEntrance);
        if (this.mParticipatingEntranceTransition) {
            holder.view.setVisibility(afterEntrance ? 0 : 4);
        }
    }
}
