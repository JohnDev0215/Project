package com.android.car.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.RotaryCache;
import com.android.car.ui.utils.ViewUtils;

/* JADX INFO: loaded from: classes7.dex */
public class FocusParkingView extends View {
    private boolean mDefaultFocusOverridesHistory;
    private RotaryCache.FocusCache mFocusCache;
    private final ViewTreeObserver.OnGlobalFocusChangeListener mFocusChangeListener;
    private View mFocusedView;
    ViewGroup mScrollableContainer;
    private boolean mShouldRestoreFocus;

    public /* synthetic */ void lambda$new$0$FocusParkingView(View oldFocus, View newFocus) {
        View focusedView = newFocus instanceof FocusParkingView ? null : newFocus;
        updateFocusedView(focusedView);
    }

    public FocusParkingView(Context context) {
        super(context);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusParkingView$VLQ3JJxPvI70IB0YIP3vrT2NmAU
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusParkingView(view, view2);
            }
        };
        init(context, null);
    }

    public FocusParkingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusParkingView$VLQ3JJxPvI70IB0YIP3vrT2NmAU
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusParkingView(view, view2);
            }
        };
        init(context, attrs);
    }

    public FocusParkingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusParkingView$VLQ3JJxPvI70IB0YIP3vrT2NmAU
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusParkingView(view, view2);
            }
        };
        init(context, attrs);
    }

    public FocusParkingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusParkingView$VLQ3JJxPvI70IB0YIP3vrT2NmAU
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusParkingView(view, view2);
            }
        };
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FocusParkingView);
            this.mShouldRestoreFocus = a.getBoolean(R.styleable.FocusParkingView_shouldRestoreFocus, true);
        }
        setFocusable(1);
        setVisibility(0);
        setEnabled(true);
        setClickable(false);
        setAlpha(0.0f);
        setDefaultFocusHighlightEnabled(false);
        Resources resources = getResources();
        int focusHistoryCacheType = resources.getInteger(R.integer.car_ui_focus_history_cache_type);
        int focusHistoryExpirationPeriodMs = resources.getInteger(R.integer.car_ui_focus_history_expiration_period_ms);
        this.mFocusCache = new RotaryCache.FocusCache(focusHistoryCacheType, focusHistoryExpirationPeriodMs);
        this.mDefaultFocusOverridesHistory = resources.getBoolean(R.bool.car_ui_focus_area_default_focus_overrides_history);
    }

    private void updateFocusedView(View focusedView) {
        View view = this.mFocusedView;
        if (view != null) {
            this.mFocusCache.setFocusedView(view, SystemClock.uptimeMillis());
        }
        this.mFocusedView = focusedView;
        this.mScrollableContainer = ViewUtils.getAncestorScrollableContainer(focusedView);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(1, 1);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.mFocusChangeListener);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.mFocusChangeListener);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) {
            super.requestFocus(130, null);
            updateFocusedView(null);
        } else if (isFocused()) {
            restoreFocusInRoot(true);
        }
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return FocusParkingView.class.getName();
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (action == 1) {
            if (hasFocus()) {
                return false;
            }
            return super.requestFocus(130, null);
        }
        if (action == 67108864) {
            return restoreFocusInRoot(false);
        }
        if (action == 134217728) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
            return inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
        return super.performAccessibilityAction(action, arguments);
    }

    @Override // android.view.View
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (!this.mShouldRestoreFocus) {
            return super.requestFocus(direction, previouslyFocusedRect);
        }
        return restoreFocusInRoot(true);
    }

    @Override // android.view.View
    public boolean restoreDefaultFocus() {
        if (!this.mShouldRestoreFocus) {
            return super.restoreDefaultFocus();
        }
        return restoreFocusInRoot(true);
    }

    public void setShouldRestoreFocus(boolean shouldRestoreFocus) {
        this.mShouldRestoreFocus = shouldRestoreFocus;
    }

    private boolean restoreFocusInRoot(boolean checkForTouchMode) {
        if (checkForTouchMode && isInTouchMode()) {
            return false;
        }
        if (maybeFocusOnScrollableContainer()) {
            return true;
        }
        View cachedFocusedView = this.mFocusCache.getFocusedView(SystemClock.uptimeMillis());
        if (ViewUtils.adjustFocus(getRootView(), cachedFocusedView, this.mDefaultFocusOverridesHistory)) {
            return true;
        }
        return super.requestFocus(130, null);
    }

    private boolean maybeFocusOnScrollableContainer() {
        ViewGroup viewGroup;
        final RecyclerView recyclerView;
        View view = this.mFocusedView;
        if (view != null && !view.isAttachedToWindow() && this.mFocusedView.getParent() != null && (viewGroup = this.mScrollableContainer) != null && viewGroup.isAttachedToWindow() && this.mScrollableContainer.isShown()) {
            ViewGroup viewGroup2 = this.mScrollableContainer;
            if (viewGroup2 instanceof RecyclerView) {
                recyclerView = (RecyclerView) viewGroup2;
            } else {
                recyclerView = null;
            }
            if (this.mScrollableContainer.requestFocus()) {
                if (recyclerView != null && recyclerView.isComputingLayout()) {
                    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.car.ui.FocusParkingView.1
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            recyclerView.sendAccessibilityEvent(8);
                            FocusParkingView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }
}
