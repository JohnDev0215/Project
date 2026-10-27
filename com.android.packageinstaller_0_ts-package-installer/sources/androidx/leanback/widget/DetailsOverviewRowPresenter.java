package androidx.leanback.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.leanback.R;
import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class DetailsOverviewRowPresenter extends RowPresenter {
    static final boolean DEBUG = false;
    private static final long DEFAULT_TIMEOUT = 5000;
    static final String TAG = "DetailsOverviewRowP";
    OnActionClickedListener mActionClickedListener;
    private boolean mBackgroundColorSet;
    final Presenter mDetailsPresenter;
    private DetailsOverviewSharedElementHelper mSharedElementHelper;
    private int mBackgroundColor = 0;
    private boolean mIsStyleLarge = true;

    class ActionsItemBridgeAdapter extends ItemBridgeAdapter {
        ViewHolder mViewHolder;

        ActionsItemBridgeAdapter(ViewHolder viewHolder) {
            this.mViewHolder = viewHolder;
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onBind(final ItemBridgeAdapter.ViewHolder ibvh) {
            if (this.mViewHolder.getOnItemViewClickedListener() != null || DetailsOverviewRowPresenter.this.mActionClickedListener != null) {
                ibvh.getPresenter().setOnClickListener(ibvh.getViewHolder(), new View.OnClickListener() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.ActionsItemBridgeAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        if (ActionsItemBridgeAdapter.this.mViewHolder.getOnItemViewClickedListener() != null) {
                            ActionsItemBridgeAdapter.this.mViewHolder.getOnItemViewClickedListener().onItemClicked(ibvh.getViewHolder(), ibvh.getItem(), ActionsItemBridgeAdapter.this.mViewHolder, ActionsItemBridgeAdapter.this.mViewHolder.getRow());
                        }
                        if (DetailsOverviewRowPresenter.this.mActionClickedListener != null) {
                            DetailsOverviewRowPresenter.this.mActionClickedListener.onActionClicked((Action) ibvh.getItem());
                        }
                    }
                });
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onUnbind(ItemBridgeAdapter.ViewHolder ibvh) {
            if (this.mViewHolder.getOnItemViewClickedListener() != null || DetailsOverviewRowPresenter.this.mActionClickedListener != null) {
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
            this.mViewHolder.checkFirstAndLastPosition(DetailsOverviewRowPresenter.DEBUG);
        }
    }

    public final class ViewHolder extends RowPresenter.ViewHolder {
        ItemBridgeAdapter mActionBridgeAdapter;
        final HorizontalGridView mActionsRow;
        final OnChildSelectedListener mChildSelectedListener;
        final FrameLayout mDetailsDescriptionFrame;
        public final Presenter.ViewHolder mDetailsDescriptionViewHolder;
        final Handler mHandler;
        final ImageView mImageView;
        final View.OnLayoutChangeListener mLayoutChangeListener;
        final DetailsOverviewRow.Listener mListener;
        int mNumItems;
        final FrameLayout mOverviewFrame;
        final ViewGroup mOverviewView;
        final ViewGroup mRightPanel;
        final RecyclerView.OnScrollListener mScrollListener;
        boolean mShowMoreLeft;
        boolean mShowMoreRight;
        final Runnable mUpdateDrawableCallback;

        void bindActions(ObjectAdapter adapter) {
            this.mActionBridgeAdapter.setAdapter(adapter);
            this.mActionsRow.setAdapter(this.mActionBridgeAdapter);
            this.mNumItems = this.mActionBridgeAdapter.getItemCount();
            this.mShowMoreRight = DetailsOverviewRowPresenter.DEBUG;
            this.mShowMoreLeft = true;
            showMoreLeft(DetailsOverviewRowPresenter.DEBUG);
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
            boolean showRight = viewHolder == null || viewHolder.itemView.getRight() > this.mActionsRow.getWidth();
            RecyclerView.ViewHolder viewHolder2 = this.mActionsRow.findViewHolderForPosition(0);
            if (viewHolder2 != null && viewHolder2.itemView.getLeft() >= 0) {
                z = false;
            }
            boolean showLeft = z;
            showMoreRight(showRight);
            showMoreLeft(showLeft);
        }

        private void showMoreLeft(boolean show) {
            if (show != this.mShowMoreLeft) {
                this.mActionsRow.setFadingLeftEdge(show);
                this.mShowMoreLeft = show;
            }
        }

        private void showMoreRight(boolean show) {
            if (show != this.mShowMoreRight) {
                this.mActionsRow.setFadingRightEdge(show);
                this.mShowMoreRight = show;
            }
        }

        public ViewHolder(View rootView, Presenter detailsPresenter) {
            super(rootView);
            this.mHandler = new Handler();
            this.mUpdateDrawableCallback = new Runnable() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    DetailsOverviewRowPresenter.this.bindImageDrawable(ViewHolder.this);
                }
            };
            this.mListener = new DetailsOverviewRow.Listener() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.2
                @Override // androidx.leanback.widget.DetailsOverviewRow.Listener
                public void onImageDrawableChanged(DetailsOverviewRow row) {
                    ViewHolder.this.mHandler.removeCallbacks(ViewHolder.this.mUpdateDrawableCallback);
                    ViewHolder.this.mHandler.post(ViewHolder.this.mUpdateDrawableCallback);
                }

                @Override // androidx.leanback.widget.DetailsOverviewRow.Listener
                public void onItemChanged(DetailsOverviewRow row) {
                    if (ViewHolder.this.mDetailsDescriptionViewHolder != null) {
                        DetailsOverviewRowPresenter.this.mDetailsPresenter.onUnbindViewHolder(ViewHolder.this.mDetailsDescriptionViewHolder);
                    }
                    DetailsOverviewRowPresenter.this.mDetailsPresenter.onBindViewHolder(ViewHolder.this.mDetailsDescriptionViewHolder, row.getItem());
                }

                @Override // androidx.leanback.widget.DetailsOverviewRow.Listener
                public void onActionsAdapterChanged(DetailsOverviewRow row) {
                    ViewHolder.this.bindActions(row.getActionsAdapter());
                }
            };
            this.mLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.3
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    ViewHolder.this.checkFirstAndLastPosition(DetailsOverviewRowPresenter.DEBUG);
                }
            };
            this.mChildSelectedListener = new OnChildSelectedListener() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.4
                @Override // androidx.leanback.widget.OnChildSelectedListener
                public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                    ViewHolder.this.dispatchItemSelection(view);
                }
            };
            this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.5
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    ViewHolder.this.checkFirstAndLastPosition(true);
                }
            };
            this.mOverviewFrame = (FrameLayout) rootView.findViewById(R.id.details_frame);
            this.mOverviewView = (ViewGroup) rootView.findViewById(R.id.details_overview);
            this.mImageView = (ImageView) rootView.findViewById(R.id.details_overview_image);
            ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.details_overview_right_panel);
            this.mRightPanel = viewGroup;
            this.mDetailsDescriptionFrame = (FrameLayout) viewGroup.findViewById(R.id.details_overview_description);
            HorizontalGridView horizontalGridView = (HorizontalGridView) this.mRightPanel.findViewById(R.id.details_overview_actions);
            this.mActionsRow = horizontalGridView;
            horizontalGridView.setHasOverlappingRendering(DetailsOverviewRowPresenter.DEBUG);
            this.mActionsRow.setOnScrollListener(this.mScrollListener);
            this.mActionsRow.setAdapter(this.mActionBridgeAdapter);
            this.mActionsRow.setOnChildSelectedListener(this.mChildSelectedListener);
            int fadeLength = rootView.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_actions_fade_size);
            this.mActionsRow.setFadingRightEdgeLength(fadeLength);
            this.mActionsRow.setFadingLeftEdgeLength(fadeLength);
            Presenter.ViewHolder viewHolderOnCreateViewHolder = detailsPresenter.onCreateViewHolder(this.mDetailsDescriptionFrame);
            this.mDetailsDescriptionViewHolder = viewHolderOnCreateViewHolder;
            this.mDetailsDescriptionFrame.addView(viewHolderOnCreateViewHolder.view);
        }
    }

    public DetailsOverviewRowPresenter(Presenter detailsPresenter) {
        setHeaderPresenter(null);
        setSelectEffectEnabled(DEBUG);
        this.mDetailsPresenter = detailsPresenter;
    }

    public void setOnActionClickedListener(OnActionClickedListener listener) {
        this.mActionClickedListener = listener;
    }

    public OnActionClickedListener getOnActionClickedListener() {
        return this.mActionClickedListener;
    }

    public void setBackgroundColor(int color) {
        this.mBackgroundColor = color;
        this.mBackgroundColorSet = true;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setStyleLarge(boolean large) {
        this.mIsStyleLarge = large;
    }

    public boolean isStyleLarge() {
        return this.mIsStyleLarge;
    }

    public final void setSharedElementEnterTransition(Activity activity, String sharedElementName, long timeoutMs) {
        if (this.mSharedElementHelper == null) {
            this.mSharedElementHelper = new DetailsOverviewSharedElementHelper();
        }
        this.mSharedElementHelper.setSharedElementEnterTransition(activity, sharedElementName, timeoutMs);
    }

    public final void setSharedElementEnterTransition(Activity activity, String sharedElementName) {
        setSharedElementEnterTransition(activity, sharedElementName, DEFAULT_TIMEOUT);
    }

    private int getDefaultBackgroundColor(Context context) {
        TypedValue outValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.defaultBrandColor, outValue, true)) {
            return context.getResources().getColor(outValue.resourceId);
        }
        return context.getResources().getColor(R.color.lb_default_brand_color);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewSelected(RowPresenter.ViewHolder vh, boolean selected) {
        super.onRowViewSelected(vh, selected);
        if (selected) {
            ((ViewHolder) vh).dispatchItemSelection(null);
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_details_overview, parent, DEBUG);
        ViewHolder vh = new ViewHolder(v, this.mDetailsPresenter);
        initDetailsOverview(vh);
        return vh;
    }

    private int getCardHeight(Context context) {
        int resId = this.mIsStyleLarge ? R.dimen.lb_details_overview_height_large : R.dimen.lb_details_overview_height_small;
        return context.getResources().getDimensionPixelSize(resId);
    }

    private void initDetailsOverview(final ViewHolder vh) {
        vh.mActionBridgeAdapter = new ActionsItemBridgeAdapter(vh);
        View overview = vh.mOverviewFrame;
        ViewGroup.LayoutParams lp = overview.getLayoutParams();
        lp.height = getCardHeight(overview.getContext());
        overview.setLayoutParams(lp);
        if (!getSelectEffectEnabled()) {
            vh.mOverviewFrame.setForeground(null);
        }
        vh.mActionsRow.setOnUnhandledKeyListener(new BaseGridView.OnUnhandledKeyListener() { // from class: androidx.leanback.widget.DetailsOverviewRowPresenter.1
            @Override // androidx.leanback.widget.BaseGridView.OnUnhandledKeyListener
            public boolean onUnhandledKey(KeyEvent event) {
                if (vh.getOnKeyListener() != null && vh.getOnKeyListener().onKey(vh.view, event.getKeyCode(), event)) {
                    return true;
                }
                return DetailsOverviewRowPresenter.DEBUG;
            }
        });
    }

    private static int getNonNegativeWidth(Drawable drawable) {
        int width = drawable == null ? 0 : drawable.getIntrinsicWidth();
        if (width > 0) {
            return width;
        }
        return 0;
    }

    private static int getNonNegativeHeight(Drawable drawable) {
        int height = drawable == null ? 0 : drawable.getIntrinsicHeight();
        if (height > 0) {
            return height;
        }
        return 0;
    }

    void bindImageDrawable(ViewHolder vh) {
        DetailsOverviewSharedElementHelper detailsOverviewSharedElementHelper;
        DetailsOverviewRow row = (DetailsOverviewRow) vh.getRow();
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) vh.mImageView.getLayoutParams();
        int cardHeight = getCardHeight(vh.mImageView.getContext());
        int verticalMargin = vh.mImageView.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_image_margin_vertical);
        int horizontalMargin = vh.mImageView.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_image_margin_horizontal);
        int drawableWidth = getNonNegativeWidth(row.getImageDrawable());
        int drawableHeight = getNonNegativeHeight(row.getImageDrawable());
        boolean scaleImage = row.isImageScaleUpAllowed();
        boolean useMargin = DEBUG;
        if (row.getImageDrawable() != null) {
            boolean landscape = DEBUG;
            if (drawableWidth > drawableHeight) {
                landscape = true;
                if (this.mIsStyleLarge) {
                    useMargin = true;
                }
            }
            if ((landscape && drawableWidth > cardHeight) || (!landscape && drawableHeight > cardHeight)) {
                scaleImage = true;
            }
            if (!scaleImage) {
                useMargin = true;
            }
            if (useMargin && !scaleImage) {
                if (landscape && drawableWidth > cardHeight - horizontalMargin) {
                    scaleImage = true;
                } else if (!landscape && drawableHeight > cardHeight - (verticalMargin * 2)) {
                    scaleImage = true;
                }
            }
        }
        boolean landscape2 = this.mBackgroundColorSet;
        int bgColor = landscape2 ? this.mBackgroundColor : getDefaultBackgroundColor(vh.mOverviewView.getContext());
        if (useMargin) {
            layoutParams.setMarginStart(horizontalMargin);
            layoutParams.bottomMargin = verticalMargin;
            layoutParams.topMargin = verticalMargin;
            vh.mOverviewFrame.setBackgroundColor(bgColor);
            vh.mRightPanel.setBackground(null);
            vh.mImageView.setBackground(null);
        } else {
            layoutParams.bottomMargin = 0;
            layoutParams.topMargin = 0;
            layoutParams.leftMargin = 0;
            vh.mRightPanel.setBackgroundColor(bgColor);
            vh.mImageView.setBackgroundColor(bgColor);
            vh.mOverviewFrame.setBackground(null);
        }
        RoundedRectHelper.setClipToRoundedOutline(vh.mOverviewFrame, true);
        if (scaleImage) {
            vh.mImageView.setScaleType(ImageView.ScaleType.FIT_START);
            vh.mImageView.setAdjustViewBounds(true);
            vh.mImageView.setMaxWidth(cardHeight);
            layoutParams.height = -1;
            layoutParams.width = -2;
        } else {
            vh.mImageView.setScaleType(ImageView.ScaleType.CENTER);
            vh.mImageView.setAdjustViewBounds(DEBUG);
            layoutParams.height = -2;
            layoutParams.width = Math.min(cardHeight, drawableWidth);
        }
        vh.mImageView.setLayoutParams(layoutParams);
        vh.mImageView.setImageDrawable(row.getImageDrawable());
        if (row.getImageDrawable() != null && (detailsOverviewSharedElementHelper = this.mSharedElementHelper) != null) {
            detailsOverviewSharedElementHelper.onBindToDrawable(vh);
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        super.onBindRowViewHolder(holder, item);
        DetailsOverviewRow row = (DetailsOverviewRow) item;
        ViewHolder vh = (ViewHolder) holder;
        bindImageDrawable(vh);
        this.mDetailsPresenter.onBindViewHolder(vh.mDetailsDescriptionViewHolder, row.getItem());
        vh.bindActions(row.getActionsAdapter());
        row.addListener(vh.mListener);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onUnbindRowViewHolder(RowPresenter.ViewHolder holder) {
        ViewHolder vh = (ViewHolder) holder;
        DetailsOverviewRow dor = (DetailsOverviewRow) vh.getRow();
        dor.removeListener(vh.mListener);
        if (vh.mDetailsDescriptionViewHolder != null) {
            this.mDetailsPresenter.onUnbindViewHolder(vh.mDetailsDescriptionViewHolder);
        }
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
        Presenter presenter = this.mDetailsPresenter;
        if (presenter != null) {
            presenter.onViewAttachedToWindow(((ViewHolder) vh).mDetailsDescriptionViewHolder);
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewDetachedFromWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewDetachedFromWindow(vh);
        Presenter presenter = this.mDetailsPresenter;
        if (presenter != null) {
            presenter.onViewDetachedFromWindow(((ViewHolder) vh).mDetailsDescriptionViewHolder);
        }
    }
}
