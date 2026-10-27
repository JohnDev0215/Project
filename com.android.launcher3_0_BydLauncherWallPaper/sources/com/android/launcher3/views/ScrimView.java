package com.android.launcher3.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Insettable;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.util.MultiValueAlpha;
import com.android.launcher3.util.Themes;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ScrimView extends View implements Insettable, WallpaperColorInfo.OnChangeListener, AccessibilityManager.AccessibilityStateChangeListener, LauncherStateManager.StateListener {
    private static final int ALPHA_CHANNEL_COUNT = 1;
    public static final Property<ScrimView, Integer> DRAG_HANDLE_ALPHA = new Property<ScrimView, Integer>(Integer.TYPE, "dragHandleAlpha") { // from class: com.android.launcher3.views.ScrimView.1
        @Override // android.util.Property
        public Integer get(ScrimView scrimView) {
            return Integer.valueOf(scrimView.mDragHandleAlpha);
        }

        @Override // android.util.Property
        public void set(ScrimView scrimView, Integer value) {
            scrimView.setDragHandleAlpha(value.intValue());
        }
    };
    private static final int SETTINGS = 2131755150;
    private static final int WALLPAPERS = 2131755169;
    private static final int WIDGETS = 2131755172;
    private final AccessibilityManager mAM;
    private final AccessibilityHelper mAccessibilityHelper;
    protected int mCurrentFlatColor;
    protected Drawable mDragHandle;
    private int mDragHandleAlpha;
    private final Rect mDragHandleBounds;
    protected float mDragHandleOffset;
    protected final int mDragHandleSize;
    protected int mEndFlatColor;
    protected int mEndFlatColorAlpha;
    protected final int mEndScrim;
    private final RectF mHitRect;
    protected final Launcher mLauncher;
    protected float mMaxScrimAlpha;
    private final MultiValueAlpha mMultiValueAlpha;
    protected float mProgress;
    protected int mScrimColor;
    private final int[] mTempPos;
    private final Rect mTempRect;
    private final WallpaperColorInfo mWallpaperColorInfo;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // com.android.launcher3.LauncherStateManager.StateListener
    public void onStateTransitionStart(LauncherState toState) {
    }

    public void reInitUi() {
    }

    public ScrimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTempRect = new Rect();
        this.mTempPos = new int[2];
        this.mProgress = 1.0f;
        this.mHitRect = new RectF();
        this.mDragHandleAlpha = 255;
        this.mLauncher = Launcher.getLauncher(context);
        this.mWallpaperColorInfo = WallpaperColorInfo.getInstance(context);
        this.mEndScrim = Themes.getAttrColor(context, R.attr.allAppsScrimColor);
        this.mMaxScrimAlpha = 0.7f;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.vertical_drag_handle_size);
        this.mDragHandleSize = dimensionPixelSize;
        this.mDragHandleBounds = new Rect(0, 0, dimensionPixelSize, dimensionPixelSize);
        AccessibilityHelper accessibilityHelperCreateAccessibilityHelper = createAccessibilityHelper();
        this.mAccessibilityHelper = accessibilityHelperCreateAccessibilityHelper;
        ViewCompat.setAccessibilityDelegate(this, accessibilityHelperCreateAccessibilityHelper);
        this.mAM = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        setFocusable(false);
        this.mMultiValueAlpha = new MultiValueAlpha(this, 1);
    }

    public MultiValueAlpha.AlphaProperty getAlphaProperty(int index) {
        return this.mMultiValueAlpha.getProperty(index);
    }

    protected AccessibilityHelper createAccessibilityHelper() {
        return new AccessibilityHelper();
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        updateDragHandleBounds();
        updateDragHandleVisibility(null);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        updateDragHandleBounds();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mWallpaperColorInfo.addOnChangeListener(this);
        onExtractedColorsChanged(this.mWallpaperColorInfo);
        this.mAM.addAccessibilityStateChangeListener(this);
        onAccessibilityStateChanged(this.mAM.isEnabled());
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mWallpaperColorInfo.removeOnChangeListener(this);
        this.mAM.removeAccessibilityStateChangeListener(this);
    }

    @Override // com.android.launcher3.uioverrides.WallpaperColorInfo.OnChangeListener
    public void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo) {
        int mainColor = wallpaperColorInfo.getMainColor();
        this.mScrimColor = mainColor;
        int iCompositeColors = ColorUtils.compositeColors(this.mEndScrim, GraphicsUtils.setColorAlphaBound(mainColor, Math.round(this.mMaxScrimAlpha * 255.0f)));
        this.mEndFlatColor = iCompositeColors;
        this.mEndFlatColorAlpha = Color.alpha(iCompositeColors);
        updateColors();
        invalidate();
    }

    public void setProgress(float progress) {
        if (this.mProgress != progress) {
            this.mProgress = progress;
            updateColors();
            updateDragHandleAlpha();
            invalidate();
        }
    }

    protected void updateColors() {
        float f = this.mProgress;
        this.mCurrentFlatColor = f >= 1.0f ? 0 : GraphicsUtils.setColorAlphaBound(this.mEndFlatColor, Math.round((1.0f - f) * this.mEndFlatColorAlpha));
    }

    protected void updateDragHandleAlpha() {
        Drawable drawable = this.mDragHandle;
        if (drawable != null) {
            drawable.setAlpha(this.mDragHandleAlpha);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDragHandleAlpha(int alpha) {
        if (alpha != this.mDragHandleAlpha) {
            this.mDragHandleAlpha = alpha;
            Drawable drawable = this.mDragHandle;
            if (drawable != null) {
                drawable.setAlpha(alpha);
                invalidate();
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i = this.mCurrentFlatColor;
        if (i != 0) {
            canvas.drawColor(i);
        }
        drawDragHandle(canvas);
    }

    protected void drawDragHandle(Canvas canvas) {
        if (this.mDragHandle != null) {
            canvas.translate(0.0f, -this.mDragHandleOffset);
            this.mDragHandle.draw(canvas);
            canvas.translate(0.0f, this.mDragHandleOffset);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        boolean zOnTouchEvent = super.onTouchEvent(event);
        if (!zOnTouchEvent && this.mDragHandle != null && event.getAction() == 0 && this.mDragHandle.getAlpha() == 255 && this.mHitRect.contains(event.getX(), event.getY())) {
            final Drawable drawable = this.mDragHandle;
            this.mDragHandle = null;
            Rect rect = new Rect(this.mDragHandleBounds);
            rect.offset(0, -((int) this.mDragHandleOffset));
            drawable.setBounds(rect);
            Rect rect2 = new Rect(rect);
            rect2.offset(0, (-rect.height()) / 2);
            final Rect rect3 = new Rect(rect);
            rect3.top = rect2.top;
            Keyframe keyframeOfObject = Keyframe.ofObject(0.6f, rect2);
            keyframeOfObject.setInterpolator(Interpolators.DEACCEL);
            Keyframe keyframeOfObject2 = Keyframe.ofObject(1.0f, rect);
            keyframeOfObject2.setInterpolator(Interpolators.ACCEL);
            PropertyValuesHolder propertyValuesHolderOfKeyframe = PropertyValuesHolder.ofKeyframe("bounds", Keyframe.ofObject(0.0f, rect), keyframeOfObject, keyframeOfObject2);
            propertyValuesHolderOfKeyframe.setEvaluator(new RectEvaluator());
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(drawable, propertyValuesHolderOfKeyframe);
            objectAnimatorOfPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.ScrimView.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    ScrimView.this.getOverlay().remove(drawable);
                    ScrimView.this.updateDragHandleVisibility(drawable);
                }
            });
            objectAnimatorOfPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.views.-$$Lambda$ScrimView$FOqGLZSgtbvaTzIrB0rpZlNTRRE
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$onTouchEvent$0$ScrimView(rect3, valueAnimator);
                }
            });
            getOverlay().add(drawable);
            objectAnimatorOfPropertyValuesHolder.start();
        }
        return zOnTouchEvent;
    }

    public /* synthetic */ void lambda$onTouchEvent$0$ScrimView(Rect rect, ValueAnimator valueAnimator) {
        invalidate(rect);
    }

    protected void updateDragHandleBounds() {
        int i;
        int i2;
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = (getMeasuredHeight() - this.mDragHandleSize) - deviceProfile.getInsets().bottom;
        if (deviceProfile.isVerticalBarLayout()) {
            i2 = deviceProfile.workspacePadding.bottom;
            if (deviceProfile.isSeascape()) {
                i = (measuredWidth - deviceProfile.getInsets().right) - this.mDragHandleSize;
            } else {
                i = this.mDragHandleSize + deviceProfile.getInsets().left;
            }
        } else {
            i = (measuredWidth - this.mDragHandleSize) / 2;
            i2 = deviceProfile.hotseatBarSizePx;
        }
        this.mDragHandleBounds.offsetTo(i, measuredHeight - i2);
        this.mHitRect.set(this.mDragHandleBounds);
        float f = (-this.mDragHandleSize) / 2;
        this.mHitRect.inset(f, f);
        Drawable drawable = this.mDragHandle;
        if (drawable != null) {
            drawable.setBounds(this.mDragHandleBounds);
        }
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
        LauncherStateManager stateManager = this.mLauncher.getStateManager();
        stateManager.removeStateListener(this);
        if (enabled) {
            stateManager.addStateListener(this);
            handleStateChangedComplete(this.mLauncher.getStateManager().getState());
        } else {
            setImportantForAccessibility(4);
        }
        updateDragHandleVisibility(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDragHandleVisibility(Drawable recycle) {
        boolean z = this.mLauncher.getDeviceProfile().isVerticalBarLayout() || this.mAM.isEnabled();
        if (z != (this.mDragHandle != null)) {
            if (z) {
                if (recycle == null) {
                    recycle = this.mLauncher.getDrawable(R.drawable.drag_handle_indicator);
                }
                this.mDragHandle = recycle;
                recycle.setBounds(this.mDragHandleBounds);
                updateDragHandleAlpha();
            } else {
                this.mDragHandle = null;
            }
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        return this.mAccessibilityHelper.dispatchHoverEvent(event) || super.dispatchHoverEvent(event);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mAccessibilityHelper.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        this.mAccessibilityHelper.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override // com.android.launcher3.LauncherStateManager.StateListener
    public void onStateTransitionComplete(LauncherState finalState) {
        handleStateChangedComplete(finalState);
    }

    private void handleStateChangedComplete(LauncherState finalState) {
        setImportantForAccessibility(finalState == LauncherState.ALL_APPS ? 4 : 0);
    }

    protected class AccessibilityHelper extends ExploreByTouchHelper {
        private static final int DRAG_HANDLE_ID = 1;

        public AccessibilityHelper() {
            super(ScrimView.this);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected int getVirtualViewAt(float x, float y) {
            return ScrimView.this.mDragHandleBounds.contains((int) x, (int) y) ? 1 : Integer.MIN_VALUE;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void getVisibleVirtualViews(List<Integer> virtualViewIds) {
            virtualViewIds.add(1);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateNodeForVirtualView(int virtualViewId, AccessibilityNodeInfoCompat node) {
            node.setContentDescription(ScrimView.this.getContext().getString(R.string.all_apps_button_label));
            node.setBoundsInParent(ScrimView.this.mDragHandleBounds);
            ScrimView scrimView = ScrimView.this;
            scrimView.getLocationOnScreen(scrimView.mTempPos);
            ScrimView.this.mTempRect.set(ScrimView.this.mDragHandleBounds);
            ScrimView.this.mTempRect.offset(ScrimView.this.mTempPos[0], ScrimView.this.mTempPos[1]);
            node.setBoundsInScreen(ScrimView.this.mTempRect);
            node.addAction(16);
            node.setClickable(true);
            node.setFocusable(true);
            if (ScrimView.this.mLauncher.isInState(LauncherState.NORMAL)) {
                Context context = ScrimView.this.getContext();
                if (Utilities.isWallpaperAllowed(context)) {
                    node.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.string.wallpaper_button_text, context.getText(R.string.wallpaper_button_text)));
                }
                node.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.string.widget_button_text, context.getText(R.string.widget_button_text)));
                node.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.string.settings_button_text, context.getText(R.string.settings_button_text)));
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
            if (action == 16) {
                ScrimView.this.mLauncher.getUserEventDispatcher().logActionOnControl(0, 1, ScrimView.this.mLauncher.getStateManager().getState().containerType);
                ScrimView.this.mLauncher.getStateManager().goToState(LauncherState.ALL_APPS);
                return true;
            }
            if (action == R.string.wallpaper_button_text) {
                return OptionsPopupView.startWallpaperPicker(ScrimView.this);
            }
            if (action == R.string.widget_button_text) {
                return OptionsPopupView.onWidgetsClicked(ScrimView.this);
            }
            if (action == R.string.settings_button_text) {
                return OptionsPopupView.startSettings(ScrimView.this);
            }
            return false;
        }
    }

    public int getDragHandleSize() {
        return this.mDragHandleSize;
    }
}
