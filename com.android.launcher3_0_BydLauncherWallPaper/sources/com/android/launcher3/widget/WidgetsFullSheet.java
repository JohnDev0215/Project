package com.android.launcher3.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Insettable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetHost;
import com.android.launcher3.R;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.views.RecyclerViewFastScroller;
import com.android.launcher3.views.TopRoundedCornerView;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsFullSheet extends BaseWidgetSheet implements Insettable, LauncherAppWidgetHost.ProviderChangedListener {
    private static final long DEFAULT_OPEN_DURATION = 267;
    private static final long FADE_IN_DURATION = 150;
    private static final float VERTICAL_START_POSITION = 0.3f;
    private final WidgetsListAdapter mAdapter;
    private final Rect mInsets;
    private WidgetsRecyclerView mRecyclerView;

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 16) != 0;
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public /* bridge */ /* synthetic */ void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        super.fillInLogContainerData(v, info, target, targetParent);
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, com.android.launcher3.AbstractFloatingView
    public /* bridge */ /* synthetic */ int getLogContainerType() {
        return super.getLogContainerType();
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, com.android.launcher3.DragSource
    public /* bridge */ /* synthetic */ void onDropCompleted(View target, DropTarget.DragObject d, boolean success) {
        super.onDropCompleted(target, d, success);
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, android.view.View.OnLongClickListener
    public /* bridge */ /* synthetic */ boolean onLongClick(View v) {
        return super.onLongClick(v);
    }

    public WidgetsFullSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mInsets = new Rect();
        LauncherAppState launcherAppState = LauncherAppState.getInstance(context);
        this.mAdapter = new WidgetsListAdapter(context, LayoutInflater.from(context), launcherAppState.getWidgetCache(), launcherAppState.getIconCache(), this, this);
    }

    public WidgetsFullSheet(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContent = findViewById(R.id.container);
        WidgetsRecyclerView widgetsRecyclerView = (WidgetsRecyclerView) findViewById(R.id.widgets_list_view);
        this.mRecyclerView = widgetsRecyclerView;
        widgetsRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setApplyBitmapDeferred(true, this.mRecyclerView);
        TopRoundedCornerView topRoundedCornerView = (TopRoundedCornerView) this.mContent;
        topRoundedCornerView.addSpringView(R.id.widgets_list_view);
        this.mRecyclerView.setEdgeEffectFactory(topRoundedCornerView.createEdgeEffectFactory());
        onWidgetsBound();
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected Pair<View, String> getAccessibilityTarget() {
        return Pair.create(this.mRecyclerView, getContext().getString(this.mIsOpen ? R.string.widgets_list : R.string.widgets_list_closed));
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLauncher.getAppWidgetHost().addProviderChangeListener(this);
        notifyWidgetProvidersChanged();
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLauncher.getAppWidgetHost().removeProviderChangeListener(this);
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        this.mInsets.set(insets);
        WidgetsRecyclerView widgetsRecyclerView = this.mRecyclerView;
        widgetsRecyclerView.setPadding(widgetsRecyclerView.getPaddingLeft(), this.mRecyclerView.getPaddingTop(), this.mRecyclerView.getPaddingRight(), insets.bottom);
        if (insets.bottom > 0) {
            setupNavBarColor();
        } else {
            clearNavBarColor();
        }
        ((TopRoundedCornerView) this.mContent).setNavBarScrimHeight(this.mInsets.bottom);
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iMax;
        if (this.mInsets.bottom > 0) {
            iMax = 0;
        } else {
            Rect rect = this.mLauncher.getDeviceProfile().workspacePadding;
            iMax = Math.max(rect.left + rect.right, (this.mInsets.left + this.mInsets.right) * 2);
        }
        measureChildWithMargins(this.mContent, widthMeasureSpec, iMax, heightMeasureSpec, this.mInsets.top + this.mLauncher.getDeviceProfile().edgeMarginPx);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i = b - t;
        int measuredWidth = this.mContent.getMeasuredWidth();
        int i2 = ((r - l) - measuredWidth) / 2;
        this.mContent.layout(i2, i - this.mContent.getMeasuredHeight(), measuredWidth + i2, i);
        setTranslationShift(this.mTranslationShift);
    }

    @Override // com.android.launcher3.LauncherAppWidgetHost.ProviderChangedListener
    public void notifyWidgetProvidersChanged() {
        this.mLauncher.refreshAndBindWidgetsForPackageUser(null);
    }

    @Override // com.android.launcher3.popup.PopupDataProvider.PopupDataChangeListener
    public void onWidgetsBound() {
        this.mAdapter.setWidgets(this.mLauncher.getPopupDataProvider().getAllWidgets());
    }

    private void open(boolean animate) {
        if (animate) {
            if (getPopupContainer().getInsets().bottom > 0) {
                this.mContent.setAlpha(0.0f);
                setTranslationShift(VERTICAL_START_POSITION);
            }
            this.mOpenCloseAnimator.setValues(PropertyValuesHolder.ofFloat(TRANSLATION_SHIFT, 0.0f));
            this.mOpenCloseAnimator.setDuration(DEFAULT_OPEN_DURATION).setInterpolator(AnimationUtils.loadInterpolator(getContext(), 17563662));
            this.mOpenCloseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.widget.WidgetsFullSheet.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    WidgetsFullSheet.this.mRecyclerView.setLayoutFrozen(false);
                    WidgetsFullSheet.this.mAdapter.setApplyBitmapDeferred(false, WidgetsFullSheet.this.mRecyclerView);
                    WidgetsFullSheet.this.mOpenCloseAnimator.removeListener(this);
                }
            });
            post(new Runnable() { // from class: com.android.launcher3.widget.-$$Lambda$WidgetsFullSheet$JB8gNjKyv7lm8mPO9Cx7Q3Yz5_s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$open$0$WidgetsFullSheet();
                }
            });
            return;
        }
        setTranslationShift(0.0f);
        this.mAdapter.setApplyBitmapDeferred(false, this.mRecyclerView);
        post(new Runnable() { // from class: com.android.launcher3.widget.-$$Lambda$WidgetsFullSheet$Ea9DmYSB4Vrohepg4to2NcZuMlY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.announceAccessibilityChanges();
            }
        });
    }

    public /* synthetic */ void lambda$open$0$WidgetsFullSheet() {
        this.mRecyclerView.setLayoutFrozen(true);
        this.mOpenCloseAnimator.start();
        this.mContent.animate().alpha(1.0f).setDuration(FADE_IN_DURATION);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected void handleClose(boolean animate) {
        handleClose(animate, DEFAULT_OPEN_DURATION);
    }

    @Override // com.android.launcher3.views.AbstractSlideInView, com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mNoIntercept = false;
            RecyclerViewFastScroller scrollbar = this.mRecyclerView.getScrollbar();
            if (scrollbar.getThumbOffsetY() >= 0 && getPopupContainer().isEventOverView(scrollbar, ev)) {
                this.mNoIntercept = true;
            } else if (getPopupContainer().isEventOverView(this.mContent, ev)) {
                this.mNoIntercept = !this.mRecyclerView.shouldContainerScroll(ev, getPopupContainer());
            }
        }
        return super.onControllerInterceptTouchEvent(ev);
    }

    public static WidgetsFullSheet show(Launcher launcher, boolean animate) {
        WidgetsFullSheet widgetsFullSheet = (WidgetsFullSheet) launcher.getLayoutInflater().inflate(R.layout.widgets_full_sheet, (ViewGroup) launcher.getDragLayer(), false);
        widgetsFullSheet.attachToContainer();
        widgetsFullSheet.mIsOpen = true;
        widgetsFullSheet.open(animate);
        return widgetsFullSheet;
    }

    public static WidgetsRecyclerView getWidgetsView(Launcher launcher) {
        return (WidgetsRecyclerView) launcher.findViewById(R.id.widgets_list_view);
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet
    protected int getElementsRowCount() {
        return this.mAdapter.getItemCount();
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public Animator createHintCloseAnim(float distanceToMove) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.mRecyclerView, (Property<WidgetsRecyclerView, Float>) TRANSLATION_Y, -distanceToMove));
        animatorSet.play(ObjectAnimator.ofFloat(this.mRecyclerView, (Property<WidgetsRecyclerView, Float>) ALPHA, 0.5f));
        return animatorSet;
    }
}
