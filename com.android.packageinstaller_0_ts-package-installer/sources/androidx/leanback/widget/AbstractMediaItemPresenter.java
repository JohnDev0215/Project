package androidx.leanback.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.core.view.ViewCompat;
import androidx.leanback.R;
import androidx.leanback.widget.MultiActionsProvider;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractMediaItemPresenter extends RowPresenter {
    public static final int PLAY_STATE_INITIAL = 0;
    public static final int PLAY_STATE_PAUSED = 1;
    public static final int PLAY_STATE_PLAYING = 2;
    static final Rect sTempRect = new Rect();
    private int mBackgroundColor;
    private boolean mBackgroundColorSet;
    private Presenter mMediaItemActionPresenter;
    private boolean mMediaRowSeparator;
    private int mThemeId;

    protected abstract void onBindMediaDetails(ViewHolder viewHolder, Object obj);

    public AbstractMediaItemPresenter() {
        this(0);
    }

    public AbstractMediaItemPresenter(int themeId) {
        this.mBackgroundColor = 0;
        this.mMediaItemActionPresenter = new MediaItemActionPresenter();
        this.mThemeId = themeId;
        setHeaderPresenter(null);
    }

    public void setThemeId(int themeId) {
        this.mThemeId = themeId;
    }

    public int getThemeId() {
        return this.mThemeId;
    }

    public void setActionPresenter(Presenter actionPresenter) {
        this.mMediaItemActionPresenter = actionPresenter;
    }

    public Presenter getActionPresenter() {
        return this.mMediaItemActionPresenter;
    }

    public static class ViewHolder extends RowPresenter.ViewHolder {
        private final List<Presenter.ViewHolder> mActionViewHolders;
        ValueAnimator mFocusViewAnimator;
        private final ViewGroup mMediaItemActionsContainer;
        private final View mMediaItemDetailsView;
        private final TextView mMediaItemDurationView;
        private final TextView mMediaItemNameView;
        final TextView mMediaItemNumberView;
        final ViewFlipper mMediaItemNumberViewFlipper;
        final View mMediaItemPausedView;
        final View mMediaItemPlayingView;
        MultiActionsProvider.MultiAction[] mMediaItemRowActions;
        private final View mMediaItemRowSeparator;
        final View mMediaRowView;
        AbstractMediaItemPresenter mRowPresenter;
        final View mSelectorView;

        public ViewHolder(View view) {
            int i;
            super(view);
            this.mSelectorView = view.findViewById(R.id.mediaRowSelector);
            this.mMediaRowView = view.findViewById(R.id.mediaItemRow);
            this.mMediaItemDetailsView = view.findViewById(R.id.mediaItemDetails);
            this.mMediaItemNameView = (TextView) view.findViewById(R.id.mediaItemName);
            this.mMediaItemDurationView = (TextView) view.findViewById(R.id.mediaItemDuration);
            this.mMediaItemRowSeparator = view.findViewById(R.id.mediaRowSeparator);
            this.mMediaItemActionsContainer = (ViewGroup) view.findViewById(R.id.mediaItemActionsContainer);
            this.mActionViewHolders = new ArrayList();
            getMediaItemDetailsView().setOnClickListener(new View.OnClickListener() { // from class: androidx.leanback.widget.AbstractMediaItemPresenter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (ViewHolder.this.getOnItemViewClickedListener() != null) {
                        BaseOnItemViewClickedListener onItemViewClickedListener = ViewHolder.this.getOnItemViewClickedListener();
                        ViewHolder viewHolder = ViewHolder.this;
                        onItemViewClickedListener.onItemClicked(null, null, viewHolder, viewHolder.getRowObject());
                    }
                }
            });
            getMediaItemDetailsView().setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: androidx.leanback.widget.AbstractMediaItemPresenter.ViewHolder.2
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view2, boolean hasFocus) {
                    ViewHolder viewHolder = ViewHolder.this;
                    viewHolder.mFocusViewAnimator = AbstractMediaItemPresenter.updateSelector(viewHolder.mSelectorView, view2, ViewHolder.this.mFocusViewAnimator, true);
                }
            });
            this.mMediaItemNumberViewFlipper = (ViewFlipper) view.findViewById(R.id.mediaItemNumberViewFlipper);
            TypedValue typedValue = new TypedValue();
            boolean found = view.getContext().getTheme().resolveAttribute(R.attr.playbackMediaItemNumberViewFlipperLayout, typedValue, true);
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(view.getContext());
            if (found) {
                i = typedValue.resourceId;
            } else {
                i = R.layout.lb_media_item_number_view_flipper;
            }
            View mergeView = layoutInflaterFrom.inflate(i, (ViewGroup) this.mMediaItemNumberViewFlipper, true);
            this.mMediaItemNumberView = (TextView) mergeView.findViewById(R.id.initial);
            this.mMediaItemPausedView = mergeView.findViewById(R.id.paused);
            this.mMediaItemPlayingView = mergeView.findViewById(R.id.playing);
        }

        public void onBindRowActions() {
            int i = getMediaItemActionsContainer().getChildCount();
            while (true) {
                i--;
                if (i < this.mActionViewHolders.size()) {
                    break;
                }
                getMediaItemActionsContainer().removeViewAt(i);
                this.mActionViewHolders.remove(i);
            }
            this.mMediaItemRowActions = null;
            Object rowObject = getRowObject();
            if (rowObject instanceof MultiActionsProvider) {
                MultiActionsProvider.MultiAction[] actionList = ((MultiActionsProvider) rowObject).getActions();
                Presenter actionPresenter = this.mRowPresenter.getActionPresenter();
                if (actionPresenter == null) {
                    return;
                }
                this.mMediaItemRowActions = actionList;
                for (int i2 = this.mActionViewHolders.size(); i2 < actionList.length; i2++) {
                    final int actionIndex = i2;
                    final Presenter.ViewHolder actionViewHolder = actionPresenter.onCreateViewHolder(getMediaItemActionsContainer());
                    getMediaItemActionsContainer().addView(actionViewHolder.view);
                    this.mActionViewHolders.add(actionViewHolder);
                    actionViewHolder.view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: androidx.leanback.widget.AbstractMediaItemPresenter.ViewHolder.3
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean hasFocus) {
                            ViewHolder viewHolder = ViewHolder.this;
                            viewHolder.mFocusViewAnimator = AbstractMediaItemPresenter.updateSelector(viewHolder.mSelectorView, view, ViewHolder.this.mFocusViewAnimator, false);
                        }
                    });
                    actionViewHolder.view.setOnClickListener(new View.OnClickListener() { // from class: androidx.leanback.widget.AbstractMediaItemPresenter.ViewHolder.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (ViewHolder.this.getOnItemViewClickedListener() != null) {
                                BaseOnItemViewClickedListener onItemViewClickedListener = ViewHolder.this.getOnItemViewClickedListener();
                                Presenter.ViewHolder viewHolder = actionViewHolder;
                                MultiActionsProvider.MultiAction multiAction = ViewHolder.this.mMediaItemRowActions[actionIndex];
                                ViewHolder viewHolder2 = ViewHolder.this;
                                onItemViewClickedListener.onItemClicked(viewHolder, multiAction, viewHolder2, viewHolder2.getRowObject());
                            }
                        }
                    });
                }
                if (this.mMediaItemActionsContainer != null) {
                    for (int i3 = 0; i3 < actionList.length; i3++) {
                        Presenter.ViewHolder avh = this.mActionViewHolders.get(i3);
                        actionPresenter.onUnbindViewHolder(avh);
                        actionPresenter.onBindViewHolder(avh, this.mMediaItemRowActions[i3]);
                    }
                }
            }
        }

        int findActionIndex(MultiActionsProvider.MultiAction action) {
            if (this.mMediaItemRowActions != null) {
                int i = 0;
                while (true) {
                    MultiActionsProvider.MultiAction[] multiActionArr = this.mMediaItemRowActions;
                    if (i < multiActionArr.length) {
                        if (multiActionArr[i] != action) {
                            i++;
                        } else {
                            return i;
                        }
                    } else {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        }

        public void notifyActionChanged(MultiActionsProvider.MultiAction action) {
            int actionIndex;
            Presenter actionPresenter = this.mRowPresenter.getActionPresenter();
            if (actionPresenter != null && (actionIndex = findActionIndex(action)) >= 0) {
                Presenter.ViewHolder actionViewHolder = this.mActionViewHolders.get(actionIndex);
                actionPresenter.onUnbindViewHolder(actionViewHolder);
                actionPresenter.onBindViewHolder(actionViewHolder, action);
            }
        }

        public void notifyDetailsChanged() {
            this.mRowPresenter.onUnbindMediaDetails(this);
            this.mRowPresenter.onBindMediaDetails(this, getRowObject());
        }

        public void notifyPlayStateChanged() {
            this.mRowPresenter.onBindMediaPlayState(this);
        }

        public View getSelectorView() {
            return this.mSelectorView;
        }

        public ViewFlipper getMediaItemNumberViewFlipper() {
            return this.mMediaItemNumberViewFlipper;
        }

        public TextView getMediaItemNumberView() {
            return this.mMediaItemNumberView;
        }

        public View getMediaItemPausedView() {
            return this.mMediaItemPausedView;
        }

        public View getMediaItemPlayingView() {
            return this.mMediaItemPlayingView;
        }

        public void setSelectedMediaItemNumberView(int position) {
            if (position >= 0 && position < this.mMediaItemNumberViewFlipper.getChildCount()) {
                this.mMediaItemNumberViewFlipper.setDisplayedChild(position);
            }
        }

        public TextView getMediaItemNameView() {
            return this.mMediaItemNameView;
        }

        public TextView getMediaItemDurationView() {
            return this.mMediaItemDurationView;
        }

        public View getMediaItemDetailsView() {
            return this.mMediaItemDetailsView;
        }

        public View getMediaItemRowSeparator() {
            return this.mMediaItemRowSeparator;
        }

        public ViewGroup getMediaItemActionsContainer() {
            return this.mMediaItemActionsContainer;
        }

        public MultiActionsProvider.MultiAction[] getMediaItemRowActions() {
            return this.mMediaItemRowActions;
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        Context context = parent.getContext();
        if (this.mThemeId != 0) {
            context = new ContextThemeWrapper(context, this.mThemeId);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.lb_row_media_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        vh.mRowPresenter = this;
        if (this.mBackgroundColorSet) {
            vh.mMediaRowView.setBackgroundColor(this.mBackgroundColor);
        }
        return vh;
    }

    @Override // androidx.leanback.widget.RowPresenter
    public boolean isUsingDefaultSelectEffect() {
        return false;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected boolean isClippingChildren() {
        return true;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder vh, Object item) {
        super.onBindRowViewHolder(vh, item);
        ViewHolder mvh = (ViewHolder) vh;
        onBindRowActions(mvh);
        mvh.getMediaItemRowSeparator().setVisibility(hasMediaRowSeparator() ? 0 : 8);
        onBindMediaPlayState(mvh);
        onBindMediaDetails((ViewHolder) vh, item);
    }

    protected void onBindRowActions(ViewHolder vh) {
        vh.onBindRowActions();
    }

    public void setBackgroundColor(int color) {
        this.mBackgroundColorSet = true;
        this.mBackgroundColor = color;
    }

    public void setHasMediaRowSeparator(boolean hasSeparator) {
        this.mMediaRowSeparator = hasSeparator;
    }

    public boolean hasMediaRowSeparator() {
        return this.mMediaRowSeparator;
    }

    protected void onUnbindMediaDetails(ViewHolder vh) {
    }

    public void onBindMediaPlayState(ViewHolder vh) {
        int childIndex = calculateMediaItemNumberFlipperIndex(vh);
        if (childIndex != -1 && vh.mMediaItemNumberViewFlipper.getDisplayedChild() != childIndex) {
            vh.mMediaItemNumberViewFlipper.setDisplayedChild(childIndex);
        }
    }

    static int calculateMediaItemNumberFlipperIndex(ViewHolder vh) {
        int newPlayState = vh.mRowPresenter.getMediaPlayState(vh.getRowObject());
        int iIndexOfChild = -1;
        if (newPlayState == 0) {
            if (vh.mMediaItemNumberView != null) {
                iIndexOfChild = vh.mMediaItemNumberViewFlipper.indexOfChild(vh.mMediaItemNumberView);
            }
            int childIndex = iIndexOfChild;
            return childIndex;
        }
        if (newPlayState == 1) {
            if (vh.mMediaItemPausedView != null) {
                iIndexOfChild = vh.mMediaItemNumberViewFlipper.indexOfChild(vh.mMediaItemPausedView);
            }
            int childIndex2 = iIndexOfChild;
            return childIndex2;
        }
        if (newPlayState != 2) {
            return -1;
        }
        if (vh.mMediaItemPlayingView != null) {
            iIndexOfChild = vh.mMediaItemNumberViewFlipper.indexOfChild(vh.mMediaItemPlayingView);
        }
        int childIndex3 = iIndexOfChild;
        return childIndex3;
    }

    public void onUnbindMediaPlayState(ViewHolder vh) {
    }

    protected int getMediaPlayState(Object item) {
        return 0;
    }

    static ValueAnimator updateSelector(final View selectorView, View focusChangedView, ValueAnimator layoutAnimator, boolean isDetails) {
        ValueAnimator layoutAnimator2;
        int animationDuration = focusChangedView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
        DecelerateInterpolator interpolator = new DecelerateInterpolator();
        int layoutDirection = ViewCompat.getLayoutDirection(selectorView);
        if (!focusChangedView.hasFocus()) {
            selectorView.animate().cancel();
            selectorView.animate().alpha(0.0f).setDuration(animationDuration).setInterpolator(interpolator).start();
            return layoutAnimator;
        }
        if (layoutAnimator == null) {
            layoutAnimator2 = layoutAnimator;
        } else {
            layoutAnimator.cancel();
            layoutAnimator2 = null;
        }
        float currentAlpha = selectorView.getAlpha();
        selectorView.animate().alpha(1.0f).setDuration(animationDuration).setInterpolator(interpolator).start();
        final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) selectorView.getLayoutParams();
        ViewGroup rootView = (ViewGroup) selectorView.getParent();
        sTempRect.set(0, 0, focusChangedView.getWidth(), focusChangedView.getHeight());
        rootView.offsetDescendantRectToMyCoords(focusChangedView, sTempRect);
        if (isDetails) {
            if (layoutDirection == 1) {
                sTempRect.right += rootView.getHeight();
                sTempRect.left -= rootView.getHeight() / 2;
            } else {
                sTempRect.left -= rootView.getHeight();
                sTempRect.right += rootView.getHeight() / 2;
            }
        }
        final int targetLeft = sTempRect.left;
        final int targetWidth = sTempRect.width();
        final float deltaWidth = lp.width - targetWidth;
        final float deltaLeft = lp.leftMargin - targetLeft;
        if (deltaLeft != 0.0f || deltaWidth != 0.0f) {
            if (currentAlpha == 0.0f) {
                lp.width = targetWidth;
                lp.leftMargin = targetLeft;
                selectorView.requestLayout();
            } else {
                ValueAnimator layoutAnimator3 = ValueAnimator.ofFloat(0.0f, 1.0f);
                layoutAnimator3.setDuration(animationDuration);
                layoutAnimator3.setInterpolator(interpolator);
                layoutAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.leanback.widget.AbstractMediaItemPresenter.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fractionToEnd = 1.0f - valueAnimator.getAnimatedFraction();
                        lp.leftMargin = Math.round(targetLeft + (deltaLeft * fractionToEnd));
                        lp.width = Math.round(targetWidth + (deltaWidth * fractionToEnd));
                        selectorView.requestLayout();
                    }
                });
                layoutAnimator3.start();
                return layoutAnimator3;
            }
        }
        return layoutAnimator2;
    }
}
