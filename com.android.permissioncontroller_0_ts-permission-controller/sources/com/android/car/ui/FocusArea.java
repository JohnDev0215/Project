package com.android.car.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import com.android.car.ui.utils.CarUiUtils;
import com.android.car.ui.utils.RotaryConstants;
import com.android.car.ui.utils.ViewUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public class FocusArea extends LinearLayout {
    private static final int INVALID_DIMEN = -1;
    private static final int INVALID_DIRECTION = -1;
    private static final List<Integer> NUDGE_DIRECTIONS = Arrays.asList(17, 66, 33, 130);
    private static final String TAG = "FocusArea";
    private Drawable mBackgroundHighlight;
    private int mBottomOffset;
    private boolean mClearFocusAreaHistoryWhenRotating;
    private int mDefaultFocusId;
    private boolean mDefaultFocusOverridesHistory;
    private View mDefaultFocusView;
    private boolean mEnableBackgroundHighlight;
    private boolean mEnableForegroundHighlight;
    private final ViewTreeObserver.OnGlobalFocusChangeListener mFocusChangeListener;
    private View mFocusedView;
    private Drawable mForegroundHighlight;
    private boolean mHasFocus;
    private int mLeftOffset;
    private int mNudgeShortcutDirection;
    private int mNudgeShortcutId;
    private View mNudgeShortcutView;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private FocusArea mPreviousFocusArea;
    private int mRightOffset;
    private RotaryCache mRotaryCache;
    private boolean mRtl;
    private Map<Integer, FocusArea> mSpecifiedNudgeFocusAreaMap;
    private Map<Integer, Integer> mSpecifiedNudgeIdMap;
    private int mTopOffset;
    private boolean mWrapAround;

    public /* synthetic */ void lambda$new$0$FocusArea(View oldFocus, View newFocus) {
        boolean hasFocus = hasFocus();
        saveFocusHistory(hasFocus);
        maybeUpdatePreviousFocusArea(hasFocus, oldFocus);
        maybeClearFocusAreaHistory(hasFocus, oldFocus);
        maybeUpdateFocusAreaHighlight(hasFocus);
        this.mHasFocus = hasFocus;
    }

    public FocusArea(Context context) {
        super(context);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusArea$IFJY9LbteUXfr5H_NaP-OZfWDq8
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusArea(view, view2);
            }
        };
        init(context, null);
    }

    public FocusArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusArea$IFJY9LbteUXfr5H_NaP-OZfWDq8
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusArea(view, view2);
            }
        };
        init(context, attrs);
    }

    public FocusArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusArea$IFJY9LbteUXfr5H_NaP-OZfWDq8
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusArea(view, view2);
            }
        };
        init(context, attrs);
    }

    public FocusArea(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.ui.-$$Lambda$FocusArea$IFJY9LbteUXfr5H_NaP-OZfWDq8
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$FocusArea(view, view2);
            }
        };
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Resources resources = getContext().getResources();
        this.mEnableForegroundHighlight = resources.getBoolean(R.bool.car_ui_enable_focus_area_foreground_highlight);
        this.mEnableBackgroundHighlight = resources.getBoolean(R.bool.car_ui_enable_focus_area_background_highlight);
        this.mForegroundHighlight = resources.getDrawable(R.drawable.car_ui_focus_area_foreground_highlight, getContext().getTheme());
        this.mBackgroundHighlight = resources.getDrawable(R.drawable.car_ui_focus_area_background_highlight, getContext().getTheme());
        this.mClearFocusAreaHistoryWhenRotating = resources.getBoolean(R.bool.car_ui_clear_focus_area_history_when_rotating);
        int focusHistoryCacheType = resources.getInteger(R.integer.car_ui_focus_history_cache_type);
        int focusHistoryExpirationPeriodMs = resources.getInteger(R.integer.car_ui_focus_history_expiration_period_ms);
        int focusAreaHistoryCacheType = resources.getInteger(R.integer.car_ui_focus_area_history_cache_type);
        int focusAreaHistoryExpirationPeriodMs = resources.getInteger(R.integer.car_ui_focus_area_history_expiration_period_ms);
        this.mRotaryCache = new RotaryCache(focusHistoryCacheType, focusHistoryExpirationPeriodMs, focusAreaHistoryCacheType, focusAreaHistoryExpirationPeriodMs);
        setImportantForAccessibility(1);
        setWillNotDraw(false);
        initAttrs(context, attrs);
    }

    private void saveFocusHistory(boolean hasFocus) {
        if (!hasFocus) {
            if (this.mHasFocus) {
                this.mRotaryCache.saveFocusedView(this.mFocusedView, SystemClock.uptimeMillis());
                this.mFocusedView = null;
                return;
            }
            return;
        }
        View v = getFocusedChild();
        while (v != null && !v.isFocused()) {
            v = v instanceof ViewGroup ? ((ViewGroup) v).getFocusedChild() : null;
        }
        this.mFocusedView = v;
    }

    private void maybeUpdatePreviousFocusArea(boolean hasFocus, View oldFocus) {
        if (this.mHasFocus || !hasFocus || oldFocus == null || (oldFocus instanceof FocusParkingView)) {
            this.mPreviousFocusArea = null;
            return;
        }
        FocusArea ancestorFocusArea = ViewUtils.getAncestorFocusArea(oldFocus);
        this.mPreviousFocusArea = ancestorFocusArea;
        if (ancestorFocusArea == null) {
            Log.w(TAG, "No parent FocusArea for " + oldFocus);
        }
    }

    private void maybeClearFocusAreaHistory(boolean hasFocus, View oldFocus) {
        if (!this.mClearFocusAreaHistoryWhenRotating || !hasFocus || oldFocus == null) {
            return;
        }
        FocusArea oldFocusArea = ViewUtils.getAncestorFocusArea(oldFocus);
        if (oldFocusArea != this) {
            return;
        }
        this.mRotaryCache.clearFocusAreaHistory();
    }

    private void maybeUpdateFocusAreaHighlight(boolean hasFocus) {
        if ((this.mEnableBackgroundHighlight || this.mEnableForegroundHighlight) && this.mHasFocus != hasFocus) {
            invalidate();
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FocusArea);
        try {
            this.mDefaultFocusId = a.getResourceId(R.styleable.FocusArea_defaultFocus, -1);
            int paddingStart = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingStart, -1);
            if (paddingStart == -1) {
                paddingStart = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingHorizontal, 0);
            }
            int paddingEnd = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingEnd, -1);
            if (paddingEnd == -1) {
                paddingEnd = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingHorizontal, 0);
            }
            boolean z = true;
            boolean z2 = getLayoutDirection() == 1;
            this.mRtl = z2;
            this.mPaddingLeft = z2 ? paddingEnd : paddingStart;
            this.mPaddingRight = this.mRtl ? paddingStart : paddingEnd;
            int dimensionPixelSize = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingTop, -1);
            this.mPaddingTop = dimensionPixelSize;
            if (dimensionPixelSize == -1) {
                this.mPaddingTop = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingVertical, 0);
            }
            int dimensionPixelSize2 = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingBottom, -1);
            this.mPaddingBottom = dimensionPixelSize2;
            if (dimensionPixelSize2 == -1) {
                this.mPaddingBottom = a.getDimensionPixelSize(R.styleable.FocusArea_highlightPaddingVertical, 0);
            }
            int startOffset = a.getDimensionPixelSize(R.styleable.FocusArea_startBoundOffset, -1);
            if (startOffset == -1) {
                startOffset = a.getDimensionPixelSize(R.styleable.FocusArea_horizontalBoundOffset, paddingStart);
            }
            int endOffset = a.getDimensionPixelSize(R.styleable.FocusArea_endBoundOffset, -1);
            if (endOffset == -1) {
                endOffset = a.getDimensionPixelSize(R.styleable.FocusArea_horizontalBoundOffset, paddingEnd);
            }
            this.mLeftOffset = this.mRtl ? endOffset : startOffset;
            this.mRightOffset = this.mRtl ? startOffset : endOffset;
            int dimensionPixelSize3 = a.getDimensionPixelSize(R.styleable.FocusArea_topBoundOffset, -1);
            this.mTopOffset = dimensionPixelSize3;
            if (dimensionPixelSize3 == -1) {
                this.mTopOffset = a.getDimensionPixelSize(R.styleable.FocusArea_verticalBoundOffset, this.mPaddingTop);
            }
            int dimensionPixelSize4 = a.getDimensionPixelSize(R.styleable.FocusArea_bottomBoundOffset, -1);
            this.mBottomOffset = dimensionPixelSize4;
            if (dimensionPixelSize4 == -1) {
                this.mBottomOffset = a.getDimensionPixelSize(R.styleable.FocusArea_verticalBoundOffset, this.mPaddingBottom);
            }
            this.mNudgeShortcutId = a.getResourceId(R.styleable.FocusArea_nudgeShortcut, -1);
            this.mNudgeShortcutDirection = a.getInt(R.styleable.FocusArea_nudgeShortcutDirection, -1);
            boolean z3 = this.mNudgeShortcutId == -1;
            if (this.mNudgeShortcutDirection != -1) {
                z = false;
            }
            if (z ^ z3) {
                throw new IllegalStateException("nudgeShortcut and nudgeShortcutDirection must be specified together");
            }
            HashMap map = new HashMap();
            this.mSpecifiedNudgeIdMap = map;
            map.put(17, Integer.valueOf(a.getResourceId(R.styleable.FocusArea_nudgeLeft, -1)));
            this.mSpecifiedNudgeIdMap.put(66, Integer.valueOf(a.getResourceId(R.styleable.FocusArea_nudgeRight, -1)));
            this.mSpecifiedNudgeIdMap.put(33, Integer.valueOf(a.getResourceId(R.styleable.FocusArea_nudgeUp, -1)));
            this.mSpecifiedNudgeIdMap.put(130, Integer.valueOf(a.getResourceId(R.styleable.FocusArea_nudgeDown, -1)));
            this.mDefaultFocusOverridesHistory = a.getBoolean(R.styleable.FocusArea_defaultFocusOverridesHistory, false);
            this.mWrapAround = a.getBoolean(R.styleable.FocusArea_wrapAround, false);
        } finally {
            a.recycle();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.mDefaultFocusId;
        if (i != -1) {
            this.mDefaultFocusView = CarUiUtils.requireViewByRefId(this, i);
        }
        int i2 = this.mNudgeShortcutId;
        if (i2 != -1) {
            this.mNudgeShortcutView = CarUiUtils.requireViewByRefId(this, i2);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        boolean rtl = getLayoutDirection() == 1;
        if (this.mRtl != rtl) {
            this.mRtl = rtl;
            int temp = this.mPaddingLeft;
            this.mPaddingLeft = this.mPaddingRight;
            this.mPaddingRight = temp;
            int temp2 = this.mLeftOffset;
            this.mLeftOffset = this.mRightOffset;
            this.mRightOffset = temp2;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.mFocusChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.mFocusChangeListener);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus && !isInTouchMode()) {
            maybeInitFocus();
        }
        super.onWindowFocusChanged(hasWindowFocus);
    }

    private boolean maybeInitFocus() {
        View root = getRootView();
        View focus = root.findFocus();
        return ViewUtils.initFocus(root, focus);
    }

    private boolean maybeAdjustFocus() {
        View root = getRootView();
        View focus = root.findFocus();
        return ViewUtils.adjustFocus(root, focus);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        int direction;
        if (action != 1) {
            if (action == 16777216) {
                return nudgeToShortcutView(arguments);
            }
            if (action == 33554432) {
                return nudgeToAnotherFocusArea(arguments);
            }
            return super.performAccessibilityAction(action, arguments);
        }
        boolean success = focusOnDescendant();
        if (success && this.mPreviousFocusArea != null && (direction = getNudgeDirection(arguments)) != -1) {
            saveFocusAreaHistory(direction, this.mPreviousFocusArea, this, SystemClock.uptimeMillis());
        }
        return success;
    }

    private boolean focusOnDescendant() {
        View lastFocusedView = this.mRotaryCache.getFocusedView(SystemClock.uptimeMillis());
        return ViewUtils.adjustFocus(this, lastFocusedView, this.mDefaultFocusOverridesHistory);
    }

    public View getDefaultFocusView() {
        return this.mDefaultFocusView;
    }

    private boolean nudgeToShortcutView(Bundle arguments) {
        if (this.mNudgeShortcutDirection == -1 || arguments == null || arguments.getInt(RotaryConstants.NUDGE_DIRECTION, -1) != this.mNudgeShortcutDirection || this.mNudgeShortcutView.isFocused()) {
            return false;
        }
        return ViewUtils.requestFocus(this.mNudgeShortcutView);
    }

    private boolean nudgeToAnotherFocusArea(Bundle arguments) {
        int direction = getNudgeDirection(arguments);
        long elapsedRealtime = SystemClock.uptimeMillis();
        FocusArea targetFocusArea = getSpecifiedFocusArea(direction);
        boolean success = targetFocusArea != null && targetFocusArea.focusOnDescendant();
        if (!success) {
            FocusArea targetFocusArea2 = this.mRotaryCache.getCachedFocusArea(direction, elapsedRealtime);
            return targetFocusArea2 != null && targetFocusArea2.focusOnDescendant();
        }
        return success;
    }

    private static int getNudgeDirection(Bundle arguments) {
        if (arguments == null) {
            return -1;
        }
        return arguments.getInt(RotaryConstants.NUDGE_DIRECTION, -1);
    }

    private void saveFocusAreaHistory(int direction, FocusArea sourceFocusArea, FocusArea targetFocusArea, long elapsedRealtime) {
        if (sourceFocusArea.mRotaryCache.getCachedFocusArea(direction, elapsedRealtime) == null) {
            int oppositeDirection = getOppositeDirection(direction);
            targetFocusArea.mRotaryCache.saveFocusArea(oppositeDirection, sourceFocusArea, elapsedRealtime);
        }
    }

    private static int getOppositeDirection(int direction) {
        if (direction == 17) {
            return 66;
        }
        if (direction == 33) {
            return 130;
        }
        if (direction == 66) {
            return 17;
        }
        if (direction == 130) {
            return 33;
        }
        throw new IllegalArgumentException("direction must be FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, or FOCUS_RIGHT.");
    }

    private FocusArea getSpecifiedFocusArea(int direction) {
        maybeInitializeSpecifiedFocusAreas();
        return this.mSpecifiedNudgeFocusAreaMap.get(Integer.valueOf(direction));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mEnableBackgroundHighlight && this.mHasFocus && !isInTouchMode()) {
            this.mBackgroundHighlight.setBounds(this.mPaddingLeft + getScrollX(), this.mPaddingTop + getScrollY(), (getScrollX() + getWidth()) - this.mPaddingRight, (getScrollY() + getHeight()) - this.mPaddingBottom);
            this.mBackgroundHighlight.draw(canvas);
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mEnableForegroundHighlight && this.mHasFocus && !isInTouchMode()) {
            this.mForegroundHighlight.setBounds(this.mPaddingLeft + getScrollX(), this.mPaddingTop + getScrollY(), (getScrollX() + getWidth()) - this.mPaddingRight, (getScrollY() + getHeight()) - this.mPaddingBottom);
            this.mForegroundHighlight.draw(canvas);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return FocusArea.class.getName();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        Bundle bundle = info.getExtras();
        bundle.putInt(RotaryConstants.FOCUS_AREA_LEFT_BOUND_OFFSET, this.mLeftOffset);
        bundle.putInt(RotaryConstants.FOCUS_AREA_RIGHT_BOUND_OFFSET, this.mRightOffset);
        bundle.putInt(RotaryConstants.FOCUS_AREA_TOP_BOUND_OFFSET, this.mTopOffset);
        bundle.putInt(RotaryConstants.FOCUS_AREA_BOTTOM_BOUND_OFFSET, this.mBottomOffset);
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        if (isInTouchMode()) {
            return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
        }
        return maybeAdjustFocus();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean restoreDefaultFocus() {
        return maybeAdjustFocus();
    }

    private void maybeInitializeSpecifiedFocusAreas() {
        if (this.mSpecifiedNudgeFocusAreaMap != null) {
            return;
        }
        View root = getRootView();
        this.mSpecifiedNudgeFocusAreaMap = new HashMap();
        for (Integer direction : NUDGE_DIRECTIONS) {
            int id = this.mSpecifiedNudgeIdMap.get(direction).intValue();
            this.mSpecifiedNudgeFocusAreaMap.put(direction, (FocusArea) root.findViewById(id));
        }
    }

    public void setHighlightPadding(int left, int top, int right, int bottom) {
        if (this.mPaddingLeft == left && this.mPaddingTop == top && this.mPaddingRight == right && this.mPaddingBottom == bottom) {
            return;
        }
        this.mPaddingLeft = left;
        this.mPaddingTop = top;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
        invalidate();
    }

    public void setBoundsOffset(int left, int top, int right, int bottom) {
        this.mLeftOffset = left;
        this.mTopOffset = top;
        this.mRightOffset = right;
        this.mBottomOffset = bottom;
    }

    public void setWrapAround(boolean wrapAround) {
        this.mWrapAround = wrapAround;
    }

    public void setDefaultFocus(View defaultFocus) {
        this.mDefaultFocusView = defaultFocus;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        if (this.mWrapAround) {
            return FocusFinder.getInstance().findNextFocus(this, focused, direction);
        }
        return super.focusSearch(focused, direction);
    }

    void enableForegroundHighlight() {
        this.mEnableForegroundHighlight = true;
    }

    void setDefaultFocusOverridesHistory(boolean override) {
        this.mDefaultFocusOverridesHistory = override;
    }

    void setRotaryCache(RotaryCache rotaryCache) {
        this.mRotaryCache = rotaryCache;
    }

    void setClearFocusAreaHistoryWhenRotating(boolean clear) {
        this.mClearFocusAreaHistoryWhenRotating = clear;
    }
}
