package com.android.internal.policy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.WindowConfiguration;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.Property;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.InputQueue;
import android.view.InsetsState;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.PendingInsetsController;
import android.view.SurfaceHolder;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewRootImpl;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowCallbacks;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.android.internal.R;
import com.android.internal.policy.PhoneWindow;
import com.android.internal.view.FloatingActionMode;
import com.android.internal.view.RootViewSurfaceTaker;
import com.android.internal.view.StandaloneActionMode;
import com.android.internal.view.menu.ContextMenuBuilder;
import com.android.internal.view.menu.MenuBuilder;
import com.android.internal.view.menu.MenuHelper;
import com.android.internal.widget.ActionBarContextView;
import com.android.internal.widget.BackgroundFallback;
import com.android.internal.widget.DecorCaptionView;
import com.android.internal.widget.FloatingToolbar;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DecorView extends FrameLayout implements RootViewSurfaceTaker, WindowCallbacks {
    private static final boolean DEBUG_MEASURE = false;
    public static final int DECOR_SHADOW_FOCUSED_HEIGHT_IN_DIP = 20;
    public static final int DECOR_SHADOW_UNFOCUSED_HEIGHT_IN_DIP = 5;
    private static final int SCRIM_LIGHT = -419430401;
    private static final boolean SWEEP_OPEN_MENU = false;
    private static final String TAG = "DecorView";
    private boolean mAllowUpdateElevation;
    private boolean mApplyFloatingHorizontalInsets;
    private boolean mApplyFloatingVerticalInsets;
    private float mAvailableWidth;
    private BackdropFrameRenderer mBackdropFrameRenderer;
    private final BackgroundFallback mBackgroundFallback;
    private Insets mBackgroundInsets;
    private final Rect mBackgroundPadding;
    private final int mBarEnterExitDuration;
    private Drawable mCaptionBackgroundDrawable;
    private boolean mChanging;
    ViewGroup mContentRoot;
    private DecorCaptionView mDecorCaptionView;
    int mDefaultOpacity;
    private int mDownY;
    private boolean mDrawLegacyNavigationBarBackground;
    private final Rect mDrawingBounds;
    private boolean mElevationAdjustedForStack;
    private ObjectAnimator mFadeAnim;
    private final int mFeatureId;
    private ActionMode mFloatingActionMode;
    private View mFloatingActionModeOriginatingView;
    private final Rect mFloatingInsets;
    private FloatingToolbar mFloatingToolbar;
    private ViewTreeObserver.OnPreDrawListener mFloatingToolbarPreDrawListener;
    final boolean mForceWindowDrawsBarBackgrounds;
    private final Rect mFrameOffsets;
    private final Rect mFramePadding;
    private boolean mHasCaption;
    private final Interpolator mHideInterpolator;
    private final Paint mHorizontalResizeShadowPaint;
    private boolean mIsInPictureInPictureMode;
    private Drawable.Callback mLastBackgroundDrawableCb;
    private Insets mLastBackgroundInsets;
    private int mLastBottomInset;
    private boolean mLastHasBottomStableInset;
    private boolean mLastHasLeftStableInset;
    private boolean mLastHasRightStableInset;
    private boolean mLastHasTopStableInset;
    private int mLastLeftInset;
    private Drawable mLastOriginalBackgroundDrawable;
    private ViewOutlineProvider mLastOutlineProvider;
    private int mLastRightInset;
    private boolean mLastShouldAlwaysConsumeSystemBars;
    private int mLastTopInset;
    private int mLastWindowFlags;
    private final Paint mLegacyNavigationBarBackgroundPaint;
    String mLogTag;
    private Drawable mMenuBackground;
    private final ColorViewState mNavigationColorViewState;
    private Drawable mOriginalBackgroundDrawable;
    private PendingInsetsController mPendingInsetsController;
    private Drawable mPendingWindowBackground;
    ActionMode mPrimaryActionMode;
    private PopupWindow mPrimaryActionModePopup;
    private ActionBarContextView mPrimaryActionModeView;
    private int mResizeMode;
    private final int mResizeShadowSize;
    private Drawable mResizingBackgroundDrawable;
    private int mRootScrollY;
    private final int mSemiTransparentBarColor;
    private final Interpolator mShowInterpolator;
    private Runnable mShowPrimaryActionModePopup;
    private final ColorViewState mStatusColorViewState;
    private View mStatusGuard;
    private Rect mTempRect;
    private Drawable mUserCaptionBackgroundDrawable;
    private final Paint mVerticalResizeShadowPaint;
    private boolean mWatchingForMenu;
    private PhoneWindow mWindow;
    private boolean mWindowResizeCallbacksAdded;
    public static final ColorViewAttributes STATUS_BAR_COLOR_VIEW_ATTRIBUTES = new ColorViewAttributes(4, 67108864, 48, 3, 5, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME, 16908335, 1024, 0);
    public static final ColorViewAttributes NAVIGATION_BAR_COLOR_VIEW_ATTRIBUTES = new ColorViewAttributes(2, 134217728, 80, 5, 3, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME, 16908336, 0, 1);
    private static final ViewOutlineProvider PIP_OUTLINE_PROVIDER = new ViewOutlineProvider() { // from class: com.android.internal.policy.DecorView.1
        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
            outline.setAlpha(1.0f);
        }
    };

    public static boolean isNavBarToLeftEdge(int i, int i2) {
        return i == 0 && i2 > 0;
    }

    public static boolean isNavBarToRightEdge(int i, int i2) {
        return i == 0 && i2 > 0;
    }

    @Override // android.view.View
    public int getAccessibilityViewId() {
        return 2147483646;
    }

    @Override // android.view.ViewGroup
    public boolean isTransitionGroup() {
        return false;
    }

    DecorView(Context context, int i, PhoneWindow phoneWindow, WindowManager.LayoutParams layoutParams) {
        super(context);
        boolean z = false;
        this.mAllowUpdateElevation = false;
        this.mElevationAdjustedForStack = false;
        this.mDefaultOpacity = -1;
        this.mDrawingBounds = new Rect();
        this.mBackgroundPadding = new Rect();
        this.mFramePadding = new Rect();
        this.mFrameOffsets = new Rect();
        this.mHasCaption = false;
        this.mStatusColorViewState = new ColorViewState(STATUS_BAR_COLOR_VIEW_ATTRIBUTES);
        this.mNavigationColorViewState = new ColorViewState(NAVIGATION_BAR_COLOR_VIEW_ATTRIBUTES);
        this.mBackgroundFallback = new BackgroundFallback();
        this.mLastTopInset = 0;
        this.mLastBottomInset = 0;
        this.mLastRightInset = 0;
        this.mLastLeftInset = 0;
        this.mLastHasTopStableInset = false;
        this.mLastHasBottomStableInset = false;
        this.mLastHasRightStableInset = false;
        this.mLastHasLeftStableInset = false;
        this.mLastWindowFlags = 0;
        this.mLastShouldAlwaysConsumeSystemBars = false;
        this.mRootScrollY = 0;
        this.mWindowResizeCallbacksAdded = false;
        this.mLastBackgroundDrawableCb = null;
        this.mBackdropFrameRenderer = null;
        this.mLogTag = TAG;
        this.mFloatingInsets = new Rect();
        this.mApplyFloatingVerticalInsets = false;
        this.mApplyFloatingHorizontalInsets = false;
        this.mResizeMode = -1;
        this.mVerticalResizeShadowPaint = new Paint();
        this.mHorizontalResizeShadowPaint = new Paint();
        Paint paint = new Paint();
        this.mLegacyNavigationBarBackgroundPaint = paint;
        this.mBackgroundInsets = Insets.NONE;
        this.mLastBackgroundInsets = Insets.NONE;
        this.mPendingInsetsController = new PendingInsetsController();
        this.mFeatureId = i;
        this.mShowInterpolator = AnimationUtils.loadInterpolator(context, 17563662);
        this.mHideInterpolator = AnimationUtils.loadInterpolator(context, 17563663);
        this.mBarEnterExitDuration = context.getResources().getInteger(R.integer.dock_enter_exit_duration);
        if (context.getResources().getBoolean(R.bool.config_forceWindowDrawsStatusBarBackground) && context.getApplicationInfo().targetSdkVersion >= 24) {
            z = true;
        }
        this.mForceWindowDrawsBarBackgrounds = z;
        this.mSemiTransparentBarColor = context.getResources().getColor(R.color.resize_shadow_end_color, null);
        updateAvailableWidth();
        setWindow(phoneWindow);
        updateLogTag(layoutParams);
        this.mResizeShadowSize = context.getResources().getDimensionPixelSize(R.dimen.resize_shadow_size);
        initResizingPaints();
        paint.setColor(-16777216);
    }

    void setBackgroundFallback(Drawable drawable) {
        this.mBackgroundFallback.setDrawable(drawable);
        setWillNotDraw(getBackground() == null && !this.mBackgroundFallback.hasFallback());
    }

    public Drawable getBackgroundFallback() {
        return this.mBackgroundFallback.getDrawable();
    }

    View getStatusBarBackgroundView() {
        return this.mStatusColorViewState.view;
    }

    View getNavigationBarBackgroundView() {
        return this.mNavigationColorViewState.view;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean gatherTransparentRegion(Region region) {
        return gatherTransparentRegion(this.mStatusColorViewState, region) || gatherTransparentRegion(this.mNavigationColorViewState, region) || super.gatherTransparentRegion(region);
    }

    boolean gatherTransparentRegion(ColorViewState colorViewState, Region region) {
        if (colorViewState.view != null && colorViewState.visible && isResizing()) {
            return colorViewState.view.gatherTransparentRegion(region);
        }
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mBackgroundFallback.draw(this, this.mContentRoot, canvas, this.mWindow.mContentParent, this.mStatusColorViewState.view, this.mNavigationColorViewState.view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getAction() == 0;
        if (z && keyEvent.getRepeatCount() == 0) {
            if (this.mWindow.mPanelChordingKey > 0 && this.mWindow.mPanelChordingKey != keyCode && dispatchKeyShortcutEvent(keyEvent)) {
                return true;
            }
            if (this.mWindow.mPreparedPanel != null && this.mWindow.mPreparedPanel.isOpen) {
                PhoneWindow phoneWindow = this.mWindow;
                if (phoneWindow.performPanelShortcut(phoneWindow.mPreparedPanel, keyCode, keyEvent, 0)) {
                    return true;
                }
            }
        }
        if (!this.mWindow.isDestroyed()) {
            Window.Callback callback = this.mWindow.getCallback();
            if ((callback == null || this.mFeatureId >= 0) ? super.dispatchKeyEvent(keyEvent) : callback.dispatchKeyEvent(keyEvent)) {
                return true;
            }
        }
        if (z) {
            return this.mWindow.onKeyDown(this.mFeatureId, keyEvent.getKeyCode(), keyEvent);
        }
        return this.mWindow.onKeyUp(this.mFeatureId, keyEvent.getKeyCode(), keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (this.mWindow.mPreparedPanel != null) {
            PhoneWindow phoneWindow = this.mWindow;
            if (phoneWindow.performPanelShortcut(phoneWindow.mPreparedPanel, keyEvent.getKeyCode(), keyEvent, 1)) {
                if (this.mWindow.mPreparedPanel != null) {
                    this.mWindow.mPreparedPanel.isHandled = true;
                }
                return true;
            }
        }
        Window.Callback callback = this.mWindow.getCallback();
        if ((callback == null || this.mWindow.isDestroyed() || this.mFeatureId >= 0) ? super.dispatchKeyShortcutEvent(keyEvent) : callback.dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        PhoneWindow.PanelFeatureState panelState = this.mWindow.getPanelState(0, false);
        if (panelState != null && this.mWindow.mPreparedPanel == null) {
            this.mWindow.preparePanel(panelState, keyEvent);
            boolean zPerformPanelShortcut = this.mWindow.performPanelShortcut(panelState, keyEvent.getKeyCode(), keyEvent, 1);
            panelState.isPrepared = false;
            if (zPerformPanelShortcut) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.mWindow.getCallback();
        return (callback == null || this.mWindow.isDestroyed() || this.mFeatureId >= 0) ? super.dispatchTouchEvent(motionEvent) : callback.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.mWindow.getCallback();
        return (callback == null || this.mWindow.isDestroyed() || this.mFeatureId >= 0) ? super.dispatchTrackballEvent(motionEvent) : callback.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.mWindow.getCallback();
        return (callback == null || this.mWindow.isDestroyed() || this.mFeatureId >= 0) ? super.dispatchGenericMotionEvent(motionEvent) : callback.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            int action = keyEvent.getAction();
            ActionMode actionMode = this.mPrimaryActionMode;
            if (actionMode != null) {
                if (action == 1) {
                    actionMode.finish();
                }
                return true;
            }
        }
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return getViewRootImpl() != null && getViewRootImpl().dispatchUnhandledKeyEvent(keyEvent);
    }

    public boolean superDispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean superDispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean superDispatchTrackballEvent(MotionEvent motionEvent) {
        return super.dispatchTrackballEvent(motionEvent);
    }

    public boolean superDispatchGenericMotionEvent(MotionEvent motionEvent) {
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return onInterceptTouchEvent(motionEvent);
    }

    private boolean isOutOfInnerBounds(int i, int i2) {
        return i < 0 || i2 < 0 || i > getWidth() || i2 > getHeight();
    }

    private boolean isOutOfBounds(int i, int i2) {
        return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.mHasCaption && isShowingCaption() && action == 0 && isOutOfInnerBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return true;
        }
        if (this.mFeatureId < 0 || action != 0 || !isOutOfBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        this.mWindow.closePanel(this.mFeatureId);
        return true;
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int i) {
        if (AccessibilityManager.getInstance(this.mContext).isEnabled()) {
            int i2 = this.mFeatureId;
            if ((i2 == 0 || i2 == 6 || i2 == 2 || i2 == 5) && getChildCount() == 1) {
                getChildAt(0).sendAccessibilityEvent(i);
            } else {
                super.sendAccessibilityEvent(i);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityEvent) {
        Window.Callback callback = this.mWindow.getCallback();
        if (callback == null || this.mWindow.isDestroyed() || !callback.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
            return super.dispatchPopulateAccessibilityEventInternal(accessibilityEvent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (frame) {
            Rect rect = this.mDrawingBounds;
            getDrawingRect(rect);
            Drawable foreground = getForeground();
            if (foreground != null) {
                Rect rect2 = this.mFrameOffsets;
                rect.left += rect2.left;
                rect.top += rect2.top;
                rect.right -= rect2.right;
                rect.bottom -= rect2.bottom;
                foreground.setBounds(rect);
                Rect rect3 = this.mFramePadding;
                rect.left += rect3.left - rect2.left;
                rect.top += rect3.top - rect2.top;
                rect.right -= rect3.right - rect2.right;
                rect.bottom -= rect3.bottom - rect2.bottom;
            }
            Drawable background = super.getBackground();
            if (background != null) {
                background.setBounds(rect);
            }
        }
        return frame;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0078 A[PHI: r14
  0x0078: PHI (r14v1 int) = (r14v0 int), (r14v0 int), (r14v0 int), (r14v7 int) binds: [B:7:0x002d, B:12:0x0038, B:14:0x003c, B:26:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x010e  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.policy.DecorView.onMeasure(int, int):void");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mApplyFloatingVerticalInsets) {
            offsetTopAndBottom(this.mFloatingInsets.top);
        }
        if (this.mApplyFloatingHorizontalInsets) {
            offsetLeftAndRight(this.mFloatingInsets.left);
        }
        updateElevation();
        this.mAllowUpdateElevation = true;
        if (z) {
            if (this.mResizeMode == 1 || this.mDrawLegacyNavigationBarBackground) {
                getViewRootImpl().requestInvalidateRootRenderNode();
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.mMenuBackground;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view) {
        return showContextMenuForChildInternal(view, Float.NaN, Float.NaN);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view, float f, float f2) {
        return showContextMenuForChildInternal(view, f, f2);
    }

    private boolean showContextMenuForChildInternal(View view, float f, float f2) {
        MenuHelper menuHelperShowDialog;
        if (this.mWindow.mContextMenuHelper != null) {
            this.mWindow.mContextMenuHelper.dismiss();
            this.mWindow.mContextMenuHelper = null;
        }
        PhoneWindow.PhoneWindowMenuCallback phoneWindowMenuCallback = this.mWindow.mContextMenuCallback;
        if (this.mWindow.mContextMenu == null) {
            this.mWindow.mContextMenu = new ContextMenuBuilder(getContext());
            this.mWindow.mContextMenu.setCallback(phoneWindowMenuCallback);
        } else {
            this.mWindow.mContextMenu.clearAll();
        }
        boolean z = (Float.isNaN(f) || Float.isNaN(f2)) ? false : true;
        if (z) {
            menuHelperShowDialog = this.mWindow.mContextMenu.showPopup(getContext(), view, f, f2);
        } else {
            menuHelperShowDialog = this.mWindow.mContextMenu.showDialog(view, view.getWindowToken());
        }
        if (menuHelperShowDialog != null) {
            phoneWindowMenuCallback.setShowDialogForSubmenu(!z);
            menuHelperShowDialog.setPresenterCallback(phoneWindowMenuCallback);
        }
        this.mWindow.mContextMenuHelper = menuHelperShowDialog;
        return menuHelperShowDialog != null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return startActionModeForChild(view, callback, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        return startActionMode(view, callback, i);
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return startActionMode(callback, 0);
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback callback, int i) {
        return startActionMode(this, callback, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.ActionMode startActionMode(android.view.View r3, android.view.ActionMode.Callback r4, int r5) {
        /*
            r2 = this;
            com.android.internal.policy.DecorView$ActionModeCallback2Wrapper r0 = new com.android.internal.policy.DecorView$ActionModeCallback2Wrapper
            r0.<init>(r4)
            com.android.internal.policy.PhoneWindow r4 = r2.mWindow
            android.view.Window$Callback r4 = r4.getCallback()
            r1 = 0
            if (r4 == 0) goto L2e
            com.android.internal.policy.PhoneWindow r4 = r2.mWindow
            boolean r4 = r4.isDestroyed()
            if (r4 != 0) goto L2e
            com.android.internal.policy.PhoneWindow r4 = r2.mWindow     // Catch: java.lang.AbstractMethodError -> L21
            android.view.Window$Callback r4 = r4.getCallback()     // Catch: java.lang.AbstractMethodError -> L21
            android.view.ActionMode r4 = r4.onWindowStartingActionMode(r0, r5)     // Catch: java.lang.AbstractMethodError -> L21
            goto L2f
        L21:
            if (r5 != 0) goto L2e
            com.android.internal.policy.PhoneWindow r4 = r2.mWindow     // Catch: java.lang.AbstractMethodError -> L2e
            android.view.Window$Callback r4 = r4.getCallback()     // Catch: java.lang.AbstractMethodError -> L2e
            android.view.ActionMode r4 = r4.onWindowStartingActionMode(r0)     // Catch: java.lang.AbstractMethodError -> L2e
            goto L2f
        L2e:
            r4 = r1
        L2f:
            if (r4 == 0) goto L4f
            int r3 = r4.getType()
            if (r3 != 0) goto L3d
            r2.cleanupPrimaryActionMode()
            r2.mPrimaryActionMode = r4
            goto L4d
        L3d:
            int r3 = r4.getType()
            r5 = 1
            if (r3 != r5) goto L4d
            android.view.ActionMode r3 = r2.mFloatingActionMode
            if (r3 == 0) goto L4b
            r3.finish()
        L4b:
            r2.mFloatingActionMode = r4
        L4d:
            r1 = r4
            goto L63
        L4f:
            android.view.ActionMode r3 = r2.createActionMode(r5, r0, r3)
            if (r3 == 0) goto L63
            android.view.Menu r4 = r3.getMenu()
            boolean r4 = r0.onCreateActionMode(r3, r4)
            if (r4 == 0) goto L63
            r2.setHandledActionMode(r3)
            r1 = r3
        L63:
            if (r1 == 0) goto L7e
            com.android.internal.policy.PhoneWindow r3 = r2.mWindow
            android.view.Window$Callback r3 = r3.getCallback()
            if (r3 == 0) goto L7e
            com.android.internal.policy.PhoneWindow r3 = r2.mWindow
            boolean r3 = r3.isDestroyed()
            if (r3 != 0) goto L7e
            com.android.internal.policy.PhoneWindow r2 = r2.mWindow     // Catch: java.lang.AbstractMethodError -> L7e
            android.view.Window$Callback r2 = r2.getCallback()     // Catch: java.lang.AbstractMethodError -> L7e
            r2.onActionModeStarted(r1)     // Catch: java.lang.AbstractMethodError -> L7e
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.policy.DecorView.startActionMode(android.view.View, android.view.ActionMode$Callback, int):android.view.ActionMode");
    }

    private void cleanupPrimaryActionMode() {
        ActionMode actionMode = this.mPrimaryActionMode;
        if (actionMode != null) {
            actionMode.finish();
            this.mPrimaryActionMode = null;
        }
        ActionBarContextView actionBarContextView = this.mPrimaryActionModeView;
        if (actionBarContextView != null) {
            actionBarContextView.killMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupFloatingActionModeViews() {
        FloatingToolbar floatingToolbar = this.mFloatingToolbar;
        if (floatingToolbar != null) {
            floatingToolbar.dismiss();
            this.mFloatingToolbar = null;
        }
        View view = this.mFloatingActionModeOriginatingView;
        if (view != null) {
            if (this.mFloatingToolbarPreDrawListener != null) {
                view.getViewTreeObserver().removeOnPreDrawListener(this.mFloatingToolbarPreDrawListener);
                this.mFloatingToolbarPreDrawListener = null;
            }
            this.mFloatingActionModeOriginatingView = null;
        }
    }

    void startChanging() {
        this.mChanging = true;
    }

    void finishChanging() {
        this.mChanging = false;
        drawableChanged();
    }

    public void setWindowBackground(Drawable drawable) {
        if (this.mWindow == null) {
            this.mPendingWindowBackground = drawable;
            return;
        }
        if (this.mOriginalBackgroundDrawable != drawable) {
            this.mOriginalBackgroundDrawable = drawable;
            updateBackgroundDrawable();
            if (drawable != null) {
                this.mResizingBackgroundDrawable = enforceNonTranslucentBackground(drawable, this.mWindow.isTranslucent() || this.mWindow.isShowingWallpaper());
            } else {
                this.mResizingBackgroundDrawable = getResizingBackgroundDrawable(this.mWindow.mBackgroundDrawable, this.mWindow.mBackgroundFallbackDrawable, this.mWindow.isTranslucent() || this.mWindow.isShowingWallpaper());
            }
            Drawable drawable2 = this.mResizingBackgroundDrawable;
            if (drawable2 != null) {
                drawable2.getPadding(this.mBackgroundPadding);
            } else {
                this.mBackgroundPadding.setEmpty();
            }
            if (View.sBrokenWindowBackground) {
                return;
            }
            drawableChanged();
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        setWindowBackground(drawable);
    }

    public void setWindowFrame(Drawable drawable) {
        if (getForeground() != drawable) {
            setForeground(drawable);
            if (drawable != null) {
                drawable.getPadding(this.mFramePadding);
            } else {
                this.mFramePadding.setEmpty();
            }
            drawableChanged();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        updateColorViews(null, true);
        updateDecorCaptionStatus(getResources().getConfiguration());
        View view = this.mStatusGuard;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        updateStatusGuardColor();
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        this.mFloatingInsets.setEmpty();
        if ((attributes.flags & 256) == 0) {
            if (attributes.height == -2) {
                this.mFloatingInsets.top = windowInsets.getSystemWindowInsetTop();
                this.mFloatingInsets.bottom = windowInsets.getSystemWindowInsetBottom();
                windowInsets = windowInsets.inset(0, windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
            }
            if (this.mWindow.getAttributes().width == -2) {
                this.mFloatingInsets.left = windowInsets.getSystemWindowInsetTop();
                this.mFloatingInsets.right = windowInsets.getSystemWindowInsetBottom();
                windowInsets = windowInsets.inset(windowInsets.getSystemWindowInsetLeft(), 0, windowInsets.getSystemWindowInsetRight(), 0);
            }
        }
        this.mFrameOffsets.set(windowInsets.getSystemWindowInsetsAsRect());
        WindowInsets windowInsetsUpdateStatusGuard = updateStatusGuard(updateColorViews(windowInsets, true));
        if (getForeground() != null) {
            drawableChanged();
        }
        return windowInsetsUpdateStatusGuard;
    }

    public static int getNavBarSize(int i, int i2, int i3) {
        return isNavBarToRightEdge(i, i2) ? i2 : isNavBarToLeftEdge(i, i3) ? i3 : i;
    }

    public static void getNavigationBarRect(int i, int i2, Rect rect, Rect rect2, float f) {
        int i3 = (int) (rect.bottom * f);
        int i4 = (int) (rect.left * f);
        int i5 = (int) (rect.right * f);
        int navBarSize = getNavBarSize(i3, i5, i4);
        if (isNavBarToRightEdge(i3, i5)) {
            rect2.set(i - navBarSize, 0, i, i2);
        } else if (isNavBarToLeftEdge(i3, i4)) {
            rect2.set(0, 0, navBarSize, i2);
        } else {
            rect2.set(0, i2 - navBarSize, i, i2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.WindowInsets updateColorViews(android.view.WindowInsets r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 623
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.policy.DecorView.updateColorViews(android.view.WindowInsets, boolean):android.view.WindowInsets");
    }

    private void updateBackgroundDrawable() {
        if (this.mBackgroundInsets == null) {
            this.mBackgroundInsets = Insets.NONE;
        }
        if (this.mBackgroundInsets.equals(this.mLastBackgroundInsets) && this.mLastOriginalBackgroundDrawable == this.mOriginalBackgroundDrawable) {
            return;
        }
        if (this.mOriginalBackgroundDrawable == null || this.mBackgroundInsets.equals(Insets.NONE)) {
            super.setBackgroundDrawable(this.mOriginalBackgroundDrawable);
        } else {
            super.setBackgroundDrawable(new InsetDrawable(this.mOriginalBackgroundDrawable, this.mBackgroundInsets.left, this.mBackgroundInsets.top, this.mBackgroundInsets.right, this.mBackgroundInsets.bottom) { // from class: com.android.internal.policy.DecorView.2
                @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
                public boolean getPadding(Rect rect) {
                    return getDrawable().getPadding(rect);
                }
            });
        }
        this.mLastBackgroundInsets = this.mBackgroundInsets;
        this.mLastOriginalBackgroundDrawable = this.mOriginalBackgroundDrawable;
    }

    @Override // android.view.View
    public Drawable getBackground() {
        return this.mOriginalBackgroundDrawable;
    }

    private int calculateStatusBarColor() {
        return calculateBarColor(this.mWindow.getAttributes().flags, 67108864, this.mSemiTransparentBarColor, this.mWindow.mStatusBarColor, getWindowSystemUiVisibility(), 8192, this.mWindow.mEnsureStatusBarContrastWhenTransparent);
    }

    private int calculateNavigationBarColor() {
        return calculateBarColor(this.mWindow.getAttributes().flags, 134217728, this.mSemiTransparentBarColor, this.mWindow.mNavigationBarColor, getWindowSystemUiVisibility(), 16, this.mWindow.mEnsureNavigationBarContrastWhenTransparent && getContext().getResources().getBoolean(R.bool.config_navBarNeedsScrim));
    }

    public static int calculateBarColor(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if ((i2 & i) != 0) {
            return i3;
        }
        if ((i & Integer.MIN_VALUE) == 0) {
            return -16777216;
        }
        if (z && Color.alpha(i4) == 0) {
            return (i5 & i6) != 0 ? SCRIM_LIGHT : i3;
        }
        return i4;
    }

    private int getCurrentColor(ColorViewState colorViewState) {
        if (colorViewState.visible) {
            return colorViewState.color;
        }
        return 0;
    }

    private void updateColorViewInt(final ColorViewState colorViewState, int i, int i2, int i3, int i4, boolean z, boolean z2, int i5, boolean z3, boolean z4, WindowInsetsController windowInsetsController) {
        boolean zIsPresent;
        int i6;
        boolean z5;
        int i7 = i5;
        if (ViewRootImpl.sNewInsetsMode != 2) {
            zIsPresent = colorViewState.attributes.isPresent(i, this.mWindow.getAttributes().flags, z4);
        } else {
            zIsPresent = colorViewState.attributes.isPresent(windowInsetsController.isRequestedVisible(colorViewState.attributes.insetsType), this.mWindow.getAttributes().flags, z4);
        }
        colorViewState.present = zIsPresent;
        boolean zIsVisible = colorViewState.attributes.isVisible(colorViewState.present, i2, this.mWindow.getAttributes().flags, z4);
        boolean z6 = zIsVisible && !isResizing() && !this.mHasCaption && i4 > 0;
        View view = colorViewState.view;
        int i8 = z ? -1 : i4;
        int i9 = z ? i4 : -1;
        if (z) {
            ColorViewAttributes colorViewAttributes = colorViewState.attributes;
            i6 = z2 ? colorViewAttributes.seascapeGravity : colorViewAttributes.horizontalGravity;
        } else {
            i6 = colorViewState.attributes.verticalGravity;
        }
        if (view != null) {
            int i10 = z6 ? 0 : 4;
            boolean z7 = colorViewState.targetVisibility != i10;
            colorViewState.targetVisibility = i10;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            int i11 = z2 ? 0 : i7;
            boolean z8 = z7;
            if (!z2) {
                i7 = 0;
            }
            if (layoutParams.height != i8 || layoutParams.width != i9 || layoutParams.gravity != i6 || layoutParams.rightMargin != i11 || layoutParams.leftMargin != i7) {
                layoutParams.height = i8;
                layoutParams.width = i9;
                layoutParams.gravity = i6;
                layoutParams.rightMargin = i11;
                layoutParams.leftMargin = i7;
                view.setLayoutParams(layoutParams);
            }
            if (z6) {
                setColor(view, i2, i3, z, z2);
            }
            z5 = z8;
        } else if (z6) {
            view = new View(this.mContext);
            colorViewState.view = view;
            setColor(view, i2, i3, z, z2);
            view.setTransitionName(colorViewState.attributes.transitionName);
            view.setId(colorViewState.attributes.id);
            view.setVisibility(4);
            colorViewState.targetVisibility = 0;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i9, i8, i6);
            if (z2) {
                layoutParams2.leftMargin = i7;
            } else {
                layoutParams2.rightMargin = i7;
            }
            addView(view, layoutParams2);
            updateColorViewTranslations();
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5) {
            view.animate().cancel();
            if (!z3 || isResizing()) {
                view.setAlpha(1.0f);
                view.setVisibility(z6 ? 0 : 4);
            } else if (z6) {
                if (view.getVisibility() != 0) {
                    view.setVisibility(0);
                    view.setAlpha(0.0f);
                }
                view.animate().alpha(1.0f).setInterpolator(this.mShowInterpolator).setDuration(this.mBarEnterExitDuration);
            } else {
                view.animate().alpha(0.0f).setInterpolator(this.mHideInterpolator).setDuration(this.mBarEnterExitDuration).withEndAction(new Runnable() { // from class: com.android.internal.policy.DecorView.3
                    @Override // java.lang.Runnable
                    public void run() {
                        colorViewState.view.setAlpha(1.0f);
                        colorViewState.view.setVisibility(4);
                    }
                });
            }
        }
        colorViewState.visible = zIsVisible;
        colorViewState.color = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void setColor(View view, int i, int i2, boolean z, boolean z2) {
        if (i2 != 0) {
            Pair pair = (Pair) view.getTag();
            if (pair == null || ((Boolean) pair.first).booleanValue() != z || ((Boolean) pair.second).booleanValue() != z2) {
                int iRound = Math.round(TypedValue.applyDimension(1, 1.0f, view.getContext().getResources().getDisplayMetrics()));
                view.setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(i2), new InsetDrawable((Drawable) new ColorDrawable(i), (!z || z2) ? 0 : iRound, !z ? iRound : 0, (z && z2) ? iRound : 0, 0)}));
                view.setTag(new Pair(Boolean.valueOf(z), Boolean.valueOf(z2)));
                return;
            } else {
                LayerDrawable layerDrawable = (LayerDrawable) view.getBackground();
                ((ColorDrawable) ((InsetDrawable) layerDrawable.getDrawable(1)).getDrawable()).setColor(i);
                ((ColorDrawable) layerDrawable.getDrawable(0)).setColor(i2);
                return;
            }
        }
        view.setTag(null);
        view.setBackgroundColor(i);
    }

    private void updateColorViewTranslations() {
        int i = this.mRootScrollY;
        if (this.mStatusColorViewState.view != null) {
            this.mStatusColorViewState.view.setTranslationY(i > 0 ? i : 0.0f);
        }
        if (this.mNavigationColorViewState.view != null) {
            this.mNavigationColorViewState.view.setTranslationY(i < 0 ? i : 0.0f);
        }
    }

    private WindowInsets updateStatusGuard(WindowInsets windowInsets) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.mPrimaryActionModeView;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mPrimaryActionModeView.getLayoutParams();
            if (this.mPrimaryActionModeView.isShown()) {
                if (this.mTempRect == null) {
                    this.mTempRect = new Rect();
                }
                WindowInsets windowInsetsComputeSystemWindowInsets = this.mWindow.mContentParent.computeSystemWindowInsets(windowInsets, this.mTempRect);
                int systemWindowInsetTop = windowInsetsComputeSystemWindowInsets.getSystemWindowInsetTop();
                int systemWindowInsetLeft = windowInsetsComputeSystemWindowInsets.getSystemWindowInsetLeft();
                int systemWindowInsetRight = windowInsetsComputeSystemWindowInsets.getSystemWindowInsetRight();
                WindowInsets rootWindowInsets = getRootWindowInsets();
                int systemWindowInsetLeft2 = rootWindowInsets.getSystemWindowInsetLeft();
                int systemWindowInsetRight2 = rootWindowInsets.getSystemWindowInsetRight();
                if (marginLayoutParams.topMargin == systemWindowInsetTop && marginLayoutParams.leftMargin == systemWindowInsetLeft && marginLayoutParams.rightMargin == systemWindowInsetRight) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = systemWindowInsetTop;
                    marginLayoutParams.leftMargin = systemWindowInsetLeft;
                    marginLayoutParams.rightMargin = systemWindowInsetRight;
                    z2 = true;
                }
                if (systemWindowInsetTop > 0 && this.mStatusGuard == null) {
                    View view = new View(this.mContext);
                    this.mStatusGuard = view;
                    view.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = systemWindowInsetLeft2;
                    layoutParams.rightMargin = systemWindowInsetRight2;
                    addView(this.mStatusGuard, indexOfChild(this.mStatusColorViewState.view), layoutParams);
                } else {
                    View view2 = this.mStatusGuard;
                    if (view2 != null) {
                        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) view2.getLayoutParams();
                        if (layoutParams2.height != marginLayoutParams.topMargin || layoutParams2.leftMargin != systemWindowInsetLeft2 || layoutParams2.rightMargin != systemWindowInsetRight2) {
                            layoutParams2.height = marginLayoutParams.topMargin;
                            layoutParams2.leftMargin = systemWindowInsetLeft2;
                            layoutParams2.rightMargin = systemWindowInsetRight2;
                            this.mStatusGuard.setLayoutParams(layoutParams2);
                        }
                    }
                }
                View view3 = this.mStatusGuard;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    updateStatusGuardColor();
                }
                if (((this.mWindow.getLocalFeaturesPrivate() & 1024) == 0) && z) {
                    windowInsets = windowInsets.inset(0, windowInsets.getSystemWindowInsetTop(), 0, 0);
                }
                z = z2;
            } else if (marginLayoutParams.topMargin == 0 && marginLayoutParams.leftMargin == 0 && marginLayoutParams.rightMargin == 0) {
                z = false;
                z = false;
            } else {
                marginLayoutParams.topMargin = 0;
                z = false;
            }
            if (z) {
                this.mPrimaryActionModeView.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.mStatusGuard;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return windowInsets;
    }

    private void updateStatusGuardColor() {
        int color;
        boolean z = (getWindowSystemUiVisibility() & 8192) != 0;
        View view = this.mStatusGuard;
        if (z) {
            color = this.mContext.getColor(R.color.decor_view_status_guard_light);
        } else {
            color = this.mContext.getColor(R.color.decor_view_status_guard);
        }
        view.setBackgroundColor(color);
    }

    public void updatePictureInPictureOutlineProvider(boolean z) {
        if (this.mIsInPictureInPictureMode == z) {
            return;
        }
        if (z) {
            Window.WindowControllerCallback windowControllerCallback = this.mWindow.getWindowControllerCallback();
            if (windowControllerCallback != null && windowControllerCallback.isTaskRoot()) {
                super.setOutlineProvider(PIP_OUTLINE_PROVIDER);
            }
        } else {
            ViewOutlineProvider outlineProvider = getOutlineProvider();
            ViewOutlineProvider viewOutlineProvider = this.mLastOutlineProvider;
            if (outlineProvider != viewOutlineProvider) {
                setOutlineProvider(viewOutlineProvider);
            }
        }
        this.mIsInPictureInPictureMode = z;
    }

    @Override // android.view.View
    public void setOutlineProvider(ViewOutlineProvider viewOutlineProvider) {
        super.setOutlineProvider(viewOutlineProvider);
        this.mLastOutlineProvider = viewOutlineProvider;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawableChanged() {
        /*
            r6 = this;
            boolean r0 = r6.mChanging
            if (r0 == 0) goto L5
            return
        L5:
            android.graphics.Rect r0 = r6.mFramePadding
            if (r0 == 0) goto La
            goto Lf
        La:
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
        Lf:
            android.graphics.Rect r1 = r6.mBackgroundPadding
            if (r1 == 0) goto L14
            goto L19
        L14:
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
        L19:
            int r2 = r0.left
            int r3 = r1.left
            int r2 = r2 + r3
            int r3 = r0.top
            int r4 = r1.top
            int r3 = r3 + r4
            int r4 = r0.right
            int r5 = r1.right
            int r4 = r4 + r5
            int r5 = r0.bottom
            int r1 = r1.bottom
            int r5 = r5 + r1
            r6.setPadding(r2, r3, r4, r5)
            r6.requestLayout()
            r6.invalidate()
            android.content.res.Resources r1 = r6.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            android.app.WindowConfiguration r1 = r1.windowConfiguration
            com.android.internal.policy.PhoneWindow r2 = r6.mWindow
            boolean r2 = r2.mRenderShadowsInCompositor
            boolean r1 = r1.hasWindowShadow()
            r3 = -3
            r4 = -1
            if (r1 == 0) goto L4f
            if (r2 != 0) goto L4f
            goto L8b
        L4f:
            android.graphics.drawable.Drawable r1 = r6.getBackground()
            android.graphics.drawable.Drawable r2 = r6.getForeground()
            if (r1 == 0) goto L8a
            if (r2 != 0) goto L60
            int r3 = r1.getOpacity()
            goto L8b
        L60:
            int r5 = r0.left
            if (r5 > 0) goto L8b
            int r5 = r0.top
            if (r5 > 0) goto L8b
            int r5 = r0.right
            if (r5 > 0) goto L8b
            int r0 = r0.bottom
            if (r0 > 0) goto L8b
            int r0 = r2.getOpacity()
            int r1 = r1.getOpacity()
            if (r0 == r4) goto L8a
            if (r1 != r4) goto L7d
            goto L8a
        L7d:
            if (r0 != 0) goto L81
            r3 = r1
            goto L8b
        L81:
            if (r1 != 0) goto L84
            goto L88
        L84:
            int r0 = android.graphics.drawable.Drawable.resolveOpacity(r0, r1)
        L88:
            r3 = r0
            goto L8b
        L8a:
            r3 = r4
        L8b:
            r6.mDefaultOpacity = r3
            int r0 = r6.mFeatureId
            if (r0 >= 0) goto L96
            com.android.internal.policy.PhoneWindow r6 = r6.mWindow
            r6.setDefaultWindowFormat(r3)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.policy.DecorView.drawableChanged():void");
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mWindow.hasFeature(0) && !z && this.mWindow.mPanelChordingKey != 0) {
            this.mWindow.closePanel(0);
        }
        Window.Callback callback = this.mWindow.getCallback();
        if (callback != null && !this.mWindow.isDestroyed() && this.mFeatureId < 0) {
            callback.onWindowFocusChanged(z);
        }
        ActionMode actionMode = this.mPrimaryActionMode;
        if (actionMode != null) {
            actionMode.onWindowFocusChanged(z);
        }
        ActionMode actionMode2 = this.mFloatingActionMode;
        if (actionMode2 != null) {
            actionMode2.onWindowFocusChanged(z);
        }
        updateElevation();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window.Callback callback = this.mWindow.getCallback();
        if (callback != null && !this.mWindow.isDestroyed() && this.mFeatureId < 0) {
            callback.onAttachedToWindow();
        }
        if (this.mFeatureId == -1) {
            this.mWindow.openPanelsAfterRestore();
        }
        if (!this.mWindowResizeCallbacksAdded) {
            getViewRootImpl().addWindowCallbacks(this);
            this.mWindowResizeCallbacksAdded = true;
        } else {
            BackdropFrameRenderer backdropFrameRenderer = this.mBackdropFrameRenderer;
            if (backdropFrameRenderer != null) {
                backdropFrameRenderer.onConfigurationChange();
            }
        }
        this.mWindow.onViewRootImplSet(getViewRootImpl());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Window.Callback callback = this.mWindow.getCallback();
        if (callback != null && this.mFeatureId < 0) {
            callback.onDetachedFromWindow();
        }
        if (this.mWindow.mDecorContentParent != null) {
            this.mWindow.mDecorContentParent.dismissPopups();
        }
        if (this.mPrimaryActionModePopup != null) {
            removeCallbacks(this.mShowPrimaryActionModePopup);
            if (this.mPrimaryActionModePopup.isShowing()) {
                this.mPrimaryActionModePopup.dismiss();
            }
            this.mPrimaryActionModePopup = null;
        }
        FloatingToolbar floatingToolbar = this.mFloatingToolbar;
        if (floatingToolbar != null) {
            floatingToolbar.dismiss();
            this.mFloatingToolbar = null;
        }
        PhoneWindow.PanelFeatureState panelState = this.mWindow.getPanelState(0, false);
        if (panelState != null && panelState.menu != null && this.mFeatureId < 0) {
            panelState.menu.close();
        }
        releaseThreadedRenderer();
        if (this.mWindowResizeCallbacksAdded) {
            getViewRootImpl().removeWindowCallbacks(this);
            this.mWindowResizeCallbacksAdded = false;
        }
        this.mPendingInsetsController.detach();
    }

    @Override // android.view.View
    public void onCloseSystemDialogs(String str) {
        if (this.mFeatureId >= 0) {
            this.mWindow.closeAllPanels();
        }
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public SurfaceHolder.Callback2 willYouTakeTheSurface() {
        if (this.mFeatureId < 0) {
            return this.mWindow.mTakeSurfaceCallback;
        }
        return null;
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public InputQueue.Callback willYouTakeTheInputQueue() {
        if (this.mFeatureId < 0) {
            return this.mWindow.mTakeInputQueueCallback;
        }
        return null;
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public void setSurfaceType(int i) {
        this.mWindow.setType(i);
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public void setSurfaceFormat(int i) {
        this.mWindow.setFormat(i);
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public void setSurfaceKeepScreenOn(boolean z) {
        if (z) {
            this.mWindow.addFlags(128);
        } else {
            this.mWindow.clearFlags(128);
        }
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public void onRootViewScrollYChanged(int i) {
        this.mRootScrollY = i;
        DecorCaptionView decorCaptionView = this.mDecorCaptionView;
        if (decorCaptionView != null) {
            decorCaptionView.onRootViewScrollYChanged(i);
        }
        updateColorViewTranslations();
    }

    @Override // com.android.internal.view.RootViewSurfaceTaker
    public PendingInsetsController providePendingInsetsController() {
        return this.mPendingInsetsController;
    }

    private ActionMode createActionMode(int i, ActionMode.Callback2 callback2, View view) {
        if (i != 1) {
            return createStandaloneActionMode(callback2);
        }
        return createFloatingActionMode(view, callback2);
    }

    private void setHandledActionMode(ActionMode actionMode) {
        if (actionMode.getType() == 0) {
            setHandledPrimaryActionMode(actionMode);
        } else if (actionMode.getType() == 1) {
            setHandledFloatingActionMode(actionMode);
        }
    }

    private ActionMode createStandaloneActionMode(ActionMode.Callback callback) {
        Context contextThemeWrapper;
        endOnGoingFadeAnimation();
        cleanupPrimaryActionMode();
        ActionBarContextView actionBarContextView = this.mPrimaryActionModeView;
        if (actionBarContextView == null || !actionBarContextView.isAttachedToWindow()) {
            if (this.mWindow.isFloating()) {
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = this.mContext.getTheme();
                theme.resolveAttribute(16843825, typedValue, true);
                if (typedValue.resourceId != 0) {
                    Resources.Theme themeNewTheme = this.mContext.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                    themeNewTheme.applyStyle(typedValue.resourceId, true);
                    contextThemeWrapper = new ContextThemeWrapper(this.mContext, 0);
                    contextThemeWrapper.getTheme().setTo(themeNewTheme);
                } else {
                    contextThemeWrapper = this.mContext;
                }
                this.mPrimaryActionModeView = new ActionBarContextView(contextThemeWrapper);
                PopupWindow popupWindow = new PopupWindow(contextThemeWrapper, (AttributeSet) null, R.attr.actionModePopupWindowStyle);
                this.mPrimaryActionModePopup = popupWindow;
                popupWindow.setWindowLayoutType(2);
                this.mPrimaryActionModePopup.setContentView(this.mPrimaryActionModeView);
                this.mPrimaryActionModePopup.setWidth(-1);
                contextThemeWrapper.getTheme().resolveAttribute(16843499, typedValue, true);
                this.mPrimaryActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, contextThemeWrapper.getResources().getDisplayMetrics()));
                this.mPrimaryActionModePopup.setHeight(-2);
                this.mShowPrimaryActionModePopup = new Runnable() { // from class: com.android.internal.policy.DecorView.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DecorView.this.mPrimaryActionModePopup.showAtLocation(DecorView.this.mPrimaryActionModeView.getApplicationWindowToken(), 55, 0, 0);
                        DecorView.this.endOnGoingFadeAnimation();
                        if (!DecorView.this.shouldAnimatePrimaryActionModeView()) {
                            DecorView.this.mPrimaryActionModeView.setAlpha(1.0f);
                            DecorView.this.mPrimaryActionModeView.setVisibility(0);
                        } else {
                            DecorView decorView = DecorView.this;
                            decorView.mFadeAnim = ObjectAnimator.ofFloat(decorView.mPrimaryActionModeView, (Property<ActionBarContextView, Float>) View.ALPHA, 0.0f, 1.0f);
                            DecorView.this.mFadeAnim.addListener(new AnimatorListenerAdapter() { // from class: com.android.internal.policy.DecorView.4.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationStart(Animator animator) {
                                    DecorView.this.mPrimaryActionModeView.setVisibility(0);
                                }

                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    DecorView.this.mPrimaryActionModeView.setAlpha(1.0f);
                                    DecorView.this.mFadeAnim = null;
                                }
                            });
                            DecorView.this.mFadeAnim.start();
                        }
                    }
                };
            } else {
                ViewStub viewStub = (ViewStub) findViewById(R.id.action_mode_bar_stub);
                if (viewStub != null) {
                    this.mPrimaryActionModeView = (ActionBarContextView) viewStub.inflate();
                    this.mPrimaryActionModePopup = null;
                }
            }
        }
        ActionBarContextView actionBarContextView2 = this.mPrimaryActionModeView;
        if (actionBarContextView2 == null) {
            return null;
        }
        actionBarContextView2.killMode();
        return new StandaloneActionMode(this.mPrimaryActionModeView.getContext(), this.mPrimaryActionModeView, callback, this.mPrimaryActionModePopup == null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endOnGoingFadeAnimation() {
        ObjectAnimator objectAnimator = this.mFadeAnim;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
    }

    private void setHandledPrimaryActionMode(ActionMode actionMode) {
        endOnGoingFadeAnimation();
        this.mPrimaryActionMode = actionMode;
        actionMode.invalidate();
        this.mPrimaryActionModeView.initForMode(this.mPrimaryActionMode);
        if (this.mPrimaryActionModePopup != null) {
            post(this.mShowPrimaryActionModePopup);
        } else if (shouldAnimatePrimaryActionModeView()) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mPrimaryActionModeView, (Property<ActionBarContextView, Float>) View.ALPHA, 0.0f, 1.0f);
            this.mFadeAnim = objectAnimatorOfFloat;
            objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.internal.policy.DecorView.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    DecorView.this.mPrimaryActionModeView.setVisibility(0);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    DecorView.this.mPrimaryActionModeView.setAlpha(1.0f);
                    DecorView.this.mFadeAnim = null;
                }
            });
            this.mFadeAnim.start();
        } else {
            this.mPrimaryActionModeView.setAlpha(1.0f);
            this.mPrimaryActionModeView.setVisibility(0);
        }
        this.mPrimaryActionModeView.sendAccessibilityEvent(32);
    }

    boolean shouldAnimatePrimaryActionModeView() {
        return isLaidOut();
    }

    private ActionMode createFloatingActionMode(View view, ActionMode.Callback2 callback2) {
        ActionMode actionMode = this.mFloatingActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        cleanupFloatingActionModeViews();
        this.mFloatingToolbar = new FloatingToolbar(this.mWindow);
        final FloatingActionMode floatingActionMode = new FloatingActionMode(this.mContext, callback2, view, this.mFloatingToolbar);
        this.mFloatingActionModeOriginatingView = view;
        this.mFloatingToolbarPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.internal.policy.DecorView.6
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                floatingActionMode.updateViewLocationInWindow();
                return true;
            }
        };
        return floatingActionMode;
    }

    private void setHandledFloatingActionMode(ActionMode actionMode) {
        this.mFloatingActionMode = actionMode;
        actionMode.invalidate();
        this.mFloatingActionModeOriginatingView.getViewTreeObserver().addOnPreDrawListener(this.mFloatingToolbarPreDrawListener);
    }

    void enableCaption(boolean z) {
        if (this.mHasCaption != z) {
            this.mHasCaption = z;
            if (getForeground() != null) {
                drawableChanged();
            }
        }
    }

    public void notifyCaptionHeightChanged() {
        getWindowInsetsController().setCaptionInsetsHeight(getCaptionInsetsHeight());
    }

    void setWindow(PhoneWindow phoneWindow) {
        this.mWindow = phoneWindow;
        Context context = getContext();
        if (context instanceof DecorContext) {
            ((DecorContext) context).setPhoneWindow(this.mWindow);
        }
        Drawable drawable = this.mPendingWindowBackground;
        if (drawable != null) {
            this.mPendingWindowBackground = null;
            setWindowBackground(drawable);
        }
    }

    @Override // android.view.View
    public Resources getResources() {
        return getContext().getResources();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateDecorCaptionStatus(configuration);
        updateAvailableWidth();
        initializeElevation();
    }

    @Override // android.view.View
    public void onMovedToDisplay(int i, Configuration configuration) {
        super.onMovedToDisplay(i, configuration);
        getContext().updateDisplay(i);
    }

    private boolean isFillingScreen(Configuration configuration) {
        if (configuration.windowConfiguration.getWindowingMode() == 1) {
            return ((getSystemUiVisibility() | getWindowSystemUiVisibility()) & 4) != 0;
        }
        return false;
    }

    private void updateDecorCaptionStatus(Configuration configuration) {
        boolean z = configuration.windowConfiguration.hasWindowDecorCaption() && !isFillingScreen(configuration);
        DecorCaptionView decorCaptionView = this.mDecorCaptionView;
        if (decorCaptionView != null || !z) {
            if (decorCaptionView != null) {
                decorCaptionView.onConfigurationChanged(z);
                enableCaption(z);
                return;
            }
            return;
        }
        DecorCaptionView decorCaptionViewCreateDecorCaptionView = createDecorCaptionView(this.mWindow.getLayoutInflater());
        this.mDecorCaptionView = decorCaptionViewCreateDecorCaptionView;
        if (decorCaptionViewCreateDecorCaptionView != null) {
            if (decorCaptionViewCreateDecorCaptionView.getParent() == null) {
                addView(this.mDecorCaptionView, 0, new ViewGroup.LayoutParams(-1, -1));
            }
            removeView(this.mContentRoot);
            this.mDecorCaptionView.addView(this.mContentRoot, new ViewGroup.MarginLayoutParams(-1, -1));
        }
    }

    void onResourcesLoaded(LayoutInflater layoutInflater, int i) {
        if (this.mBackdropFrameRenderer != null) {
            loadBackgroundDrawablesIfNeeded();
            this.mBackdropFrameRenderer.onResourcesLoaded(this, this.mResizingBackgroundDrawable, this.mCaptionBackgroundDrawable, this.mUserCaptionBackgroundDrawable, getCurrentColor(this.mStatusColorViewState), getCurrentColor(this.mNavigationColorViewState));
        }
        this.mDecorCaptionView = createDecorCaptionView(layoutInflater);
        View viewInflate = layoutInflater.inflate(i, (ViewGroup) null);
        DecorCaptionView decorCaptionView = this.mDecorCaptionView;
        if (decorCaptionView != null) {
            if (decorCaptionView.getParent() == null) {
                addView(this.mDecorCaptionView, new ViewGroup.LayoutParams(-1, -1));
            }
            this.mDecorCaptionView.addView(viewInflate, new ViewGroup.MarginLayoutParams(-1, -1));
        } else {
            addView(viewInflate, 0, new ViewGroup.LayoutParams(-1, -1));
        }
        this.mContentRoot = (ViewGroup) viewInflate;
        initializeElevation();
    }

    private void loadBackgroundDrawablesIfNeeded() {
        if (this.mResizingBackgroundDrawable == null) {
            Drawable resizingBackgroundDrawable = getResizingBackgroundDrawable(this.mWindow.mBackgroundDrawable, this.mWindow.mBackgroundFallbackDrawable, this.mWindow.isTranslucent() || this.mWindow.isShowingWallpaper());
            this.mResizingBackgroundDrawable = resizingBackgroundDrawable;
            if (resizingBackgroundDrawable == null) {
                Log.w(this.mLogTag, "Failed to find background drawable for PhoneWindow=" + this.mWindow);
            }
        }
        if (this.mCaptionBackgroundDrawable == null) {
            this.mCaptionBackgroundDrawable = getContext().getDrawable(R.drawable.decor_caption_title_focused);
        }
        Drawable drawable = this.mResizingBackgroundDrawable;
        if (drawable != null) {
            this.mLastBackgroundDrawableCb = drawable.getCallback();
            this.mResizingBackgroundDrawable.setCallback(null);
        }
    }

    private DecorCaptionView createDecorCaptionView(LayoutInflater layoutInflater) {
        DecorCaptionView decorCaptionViewInflateDecorCaptionView = null;
        DecorCaptionView decorCaptionView = null;
        for (int childCount = getChildCount() - 1; childCount >= 0 && decorCaptionView == null; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt instanceof DecorCaptionView) {
                decorCaptionView = (DecorCaptionView) childAt;
                removeViewAt(childCount);
            }
        }
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        boolean z = attributes.type == 1 || attributes.type == 2 || attributes.type == 4;
        WindowConfiguration windowConfiguration = getResources().getConfiguration().windowConfiguration;
        if (!this.mWindow.isFloating() && z && windowConfiguration.hasWindowDecorCaption()) {
            decorCaptionViewInflateDecorCaptionView = decorCaptionView == null ? inflateDecorCaptionView(layoutInflater) : decorCaptionView;
            decorCaptionViewInflateDecorCaptionView.setPhoneWindow(this.mWindow, true);
        }
        enableCaption(decorCaptionViewInflateDecorCaptionView != null);
        return decorCaptionViewInflateDecorCaptionView;
    }

    private DecorCaptionView inflateDecorCaptionView(LayoutInflater layoutInflater) {
        DecorCaptionView decorCaptionView = (DecorCaptionView) LayoutInflater.from(getContext()).inflate(R.layout.decor_caption, (ViewGroup) null);
        setDecorCaptionShade(decorCaptionView);
        return decorCaptionView;
    }

    private void setDecorCaptionShade(DecorCaptionView decorCaptionView) {
        int decorCaptionShade = this.mWindow.getDecorCaptionShade();
        if (decorCaptionShade == 1) {
            setLightDecorCaptionShade(decorCaptionView);
            return;
        }
        if (decorCaptionShade == 2) {
            setDarkDecorCaptionShade(decorCaptionView);
        } else if ((getWindowSystemUiVisibility() & 8192) != 0) {
            setDarkDecorCaptionShade(decorCaptionView);
        } else {
            setLightDecorCaptionShade(decorCaptionView);
        }
    }

    void updateDecorCaptionShade() {
        DecorCaptionView decorCaptionView = this.mDecorCaptionView;
        if (decorCaptionView != null) {
            setDecorCaptionShade(decorCaptionView);
        }
    }

    private void setLightDecorCaptionShade(DecorCaptionView decorCaptionView) {
        decorCaptionView.findViewById(R.id.maximize_window).setBackgroundResource(R.drawable.decor_maximize_button_light);
        decorCaptionView.findViewById(R.id.close_window).setBackgroundResource(R.drawable.decor_close_button_light);
    }

    private void setDarkDecorCaptionShade(DecorCaptionView decorCaptionView) {
        decorCaptionView.findViewById(R.id.maximize_window).setBackgroundResource(R.drawable.decor_maximize_button_dark);
        decorCaptionView.findViewById(R.id.close_window).setBackgroundResource(R.drawable.decor_close_button_dark);
    }

    public static Drawable getResizingBackgroundDrawable(Drawable drawable, Drawable drawable2, boolean z) {
        if (drawable != null) {
            return enforceNonTranslucentBackground(drawable, z);
        }
        if (drawable2 != null) {
            return enforceNonTranslucentBackground(drawable2, z);
        }
        return new ColorDrawable(-16777216);
    }

    private static Drawable enforceNonTranslucentBackground(Drawable drawable, boolean z) {
        if (z || !(drawable instanceof ColorDrawable)) {
            return drawable;
        }
        ColorDrawable colorDrawable = (ColorDrawable) drawable;
        int color = colorDrawable.getColor();
        if (Color.alpha(color) == 255) {
            return drawable;
        }
        ColorDrawable colorDrawable2 = (ColorDrawable) colorDrawable.getConstantState().newDrawable().mutate();
        colorDrawable2.setColor(Color.argb(255, Color.red(color), Color.green(color), Color.blue(color)));
        return colorDrawable2;
    }

    void clearContentView() {
        DecorCaptionView decorCaptionView = this.mDecorCaptionView;
        if (decorCaptionView != null) {
            decorCaptionView.removeContentView();
            return;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != this.mStatusColorViewState.view && childAt != this.mNavigationColorViewState.view && childAt != this.mStatusGuard) {
                removeViewAt(childCount);
            }
        }
    }

    @Override // android.view.WindowCallbacks
    public void onWindowSizeIsChanging(Rect rect, boolean z, Rect rect2, Rect rect3) {
        BackdropFrameRenderer backdropFrameRenderer = this.mBackdropFrameRenderer;
        if (backdropFrameRenderer != null) {
            backdropFrameRenderer.setTargetRect(rect, z, rect2);
        }
    }

    @Override // android.view.WindowCallbacks
    public void onWindowDragResizeStart(Rect rect, boolean z, Rect rect2, Rect rect3, int i) {
        if (this.mWindow.isDestroyed()) {
            releaseThreadedRenderer();
            return;
        }
        if (this.mBackdropFrameRenderer != null) {
            return;
        }
        ThreadedRenderer threadedRenderer = getThreadedRenderer();
        if (threadedRenderer != null) {
            loadBackgroundDrawablesIfNeeded();
            this.mBackdropFrameRenderer = new BackdropFrameRenderer(this, threadedRenderer, rect, this.mResizingBackgroundDrawable, this.mCaptionBackgroundDrawable, this.mUserCaptionBackgroundDrawable, getCurrentColor(this.mStatusColorViewState), getCurrentColor(this.mNavigationColorViewState), z, getRootWindowInsets().getInsets(WindowInsets.Type.systemBars()));
            updateElevation();
            updateColorViews(null, false);
        }
        this.mResizeMode = i;
        getViewRootImpl().requestInvalidateRootRenderNode();
    }

    @Override // android.view.WindowCallbacks
    public void onWindowDragResizeEnd() {
        releaseThreadedRenderer();
        updateColorViews(null, false);
        this.mResizeMode = -1;
        getViewRootImpl().requestInvalidateRootRenderNode();
    }

    @Override // android.view.WindowCallbacks
    public boolean onContentDrawn(int i, int i2, int i3, int i4) {
        BackdropFrameRenderer backdropFrameRenderer = this.mBackdropFrameRenderer;
        if (backdropFrameRenderer == null) {
            return false;
        }
        return backdropFrameRenderer.onContentDrawn(i, i2, i3, i4);
    }

    @Override // android.view.WindowCallbacks
    public void onRequestDraw(boolean z) {
        BackdropFrameRenderer backdropFrameRenderer = this.mBackdropFrameRenderer;
        if (backdropFrameRenderer != null) {
            backdropFrameRenderer.onRequestDraw(z);
        } else if (z && isAttachedToWindow()) {
            getViewRootImpl().reportDrawFinish();
        }
    }

    @Override // android.view.WindowCallbacks
    public void onPostDraw(RecordingCanvas recordingCanvas) {
        drawResizingShadowIfNeeded(recordingCanvas);
        drawLegacyNavigationBarBackground(recordingCanvas);
    }

    private void initResizingPaints() {
        int color = this.mContext.getResources().getColor(R.color.resize_shadow_start_color, null);
        int color2 = this.mContext.getResources().getColor(R.color.resize_shadow_end_color, null);
        int i = (color + color2) / 2;
        this.mHorizontalResizeShadowPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.mResizeShadowSize, new int[]{color, i, color2}, new float[]{0.0f, 0.3f, 1.0f}, Shader.TileMode.CLAMP));
        this.mVerticalResizeShadowPaint.setShader(new LinearGradient(0.0f, 0.0f, this.mResizeShadowSize, 0.0f, new int[]{color, i, color2}, new float[]{0.0f, 0.3f, 1.0f}, Shader.TileMode.CLAMP));
    }

    private void drawResizingShadowIfNeeded(RecordingCanvas recordingCanvas) {
        if (this.mResizeMode != 1 || this.mWindow.mIsFloating || this.mWindow.isTranslucent() || this.mWindow.isShowingWallpaper()) {
            return;
        }
        recordingCanvas.save();
        recordingCanvas.translate(0.0f, getHeight() - this.mFrameOffsets.bottom);
        recordingCanvas.drawRect(0.0f, 0.0f, getWidth(), this.mResizeShadowSize, this.mHorizontalResizeShadowPaint);
        recordingCanvas.restore();
        recordingCanvas.save();
        recordingCanvas.translate(getWidth() - this.mFrameOffsets.right, 0.0f);
        recordingCanvas.drawRect(0.0f, 0.0f, this.mResizeShadowSize, getHeight(), this.mVerticalResizeShadowPaint);
        recordingCanvas.restore();
    }

    private void drawLegacyNavigationBarBackground(RecordingCanvas recordingCanvas) {
        View view;
        if (this.mDrawLegacyNavigationBarBackground && (view = this.mNavigationColorViewState.view) != null) {
            recordingCanvas.drawRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), this.mLegacyNavigationBarBackgroundPaint);
        }
    }

    private void releaseThreadedRenderer() {
        Drawable.Callback callback;
        Drawable drawable = this.mResizingBackgroundDrawable;
        if (drawable != null && (callback = this.mLastBackgroundDrawableCb) != null) {
            drawable.setCallback(callback);
            this.mLastBackgroundDrawableCb = null;
        }
        BackdropFrameRenderer backdropFrameRenderer = this.mBackdropFrameRenderer;
        if (backdropFrameRenderer != null) {
            backdropFrameRenderer.releaseRenderer();
            this.mBackdropFrameRenderer = null;
            updateElevation();
        }
    }

    private boolean isResizing() {
        return this.mBackdropFrameRenderer != null;
    }

    private void initializeElevation() {
        this.mAllowUpdateElevation = false;
        updateElevation();
    }

    private void updateElevation() {
        int windowingMode = getResources().getConfiguration().windowConfiguration.getWindowingMode();
        if (this.mWindow.mRenderShadowsInCompositor) {
            return;
        }
        float fDipToPx = 0.0f;
        boolean z = this.mElevationAdjustedForStack;
        if (windowingMode == 5 && !isResizing()) {
            fDipToPx = dipToPx(this.mAllowUpdateElevation ? hasWindowFocus() ? 20.0f : 5.0f : 20.0f);
            this.mElevationAdjustedForStack = true;
        } else if (windowingMode == 2) {
            fDipToPx = dipToPx(5.0f);
            this.mElevationAdjustedForStack = true;
        } else {
            this.mElevationAdjustedForStack = false;
        }
        if ((z || this.mElevationAdjustedForStack) && getElevation() != fDipToPx) {
            if (!isResizing()) {
                this.mWindow.setElevation(fDipToPx);
            } else {
                setElevation(fDipToPx);
            }
        }
    }

    boolean isShowingCaption() {
        DecorCaptionView decorCaptionView = this.mDecorCaptionView;
        return decorCaptionView != null && decorCaptionView.isCaptionShowing();
    }

    int getCaptionHeight() {
        if (isShowingCaption()) {
            return this.mDecorCaptionView.getCaptionHeight();
        }
        return 0;
    }

    public int getCaptionInsetsHeight() {
        if (this.mWindow.isOverlayWithDecorCaptionEnabled()) {
            return getCaptionHeight();
        }
        return 0;
    }

    private float dipToPx(float f) {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    void setUserCaptionBackgroundDrawable(Drawable drawable) {
        this.mUserCaptionBackgroundDrawable = drawable;
        BackdropFrameRenderer backdropFrameRenderer = this.mBackdropFrameRenderer;
        if (backdropFrameRenderer != null) {
            backdropFrameRenderer.setUserCaptionBackgroundDrawable(drawable);
        }
    }

    private static String getTitleSuffix(WindowManager.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return "";
        }
        String[] strArrSplit = layoutParams.getTitle().toString().split("\\.");
        return strArrSplit.length > 0 ? strArrSplit[strArrSplit.length - 1] : "";
    }

    void updateLogTag(WindowManager.LayoutParams layoutParams) {
        this.mLogTag = "DecorView[" + getTitleSuffix(layoutParams) + "]";
    }

    private void updateAvailableWidth() {
        this.mAvailableWidth = TypedValue.applyDimension(1, r0.getConfiguration().screenWidthDp, getResources().getDisplayMetrics());
    }

    @Override // android.view.View
    public void requestKeyboardShortcuts(List<KeyboardShortcutGroup> list, int i) {
        PhoneWindow.PanelFeatureState panelState = this.mWindow.getPanelState(0, false);
        MenuBuilder menuBuilder = panelState != null ? panelState.menu : null;
        if (this.mWindow.isDestroyed() || this.mWindow.getCallback() == null) {
            return;
        }
        this.mWindow.getCallback().onProvideKeyboardShortcuts(list, menuBuilder, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchPointerCaptureChanged(boolean z) {
        super.dispatchPointerCaptureChanged(z);
        if (this.mWindow.isDestroyed() || this.mWindow.getCallback() == null) {
            return;
        }
        this.mWindow.getCallback().onPointerCaptureChanged(z);
    }

    @Override // android.view.View
    public WindowInsetsController getWindowInsetsController() {
        if (isAttachedToWindow()) {
            return super.getWindowInsetsController();
        }
        return this.mPendingInsetsController;
    }

    @Override // android.view.View
    public String toString() {
        return "DecorView@" + Integer.toHexString(hashCode()) + "[" + getTitleSuffix(this.mWindow.getAttributes()) + "]";
    }

    private static class ColorViewState {
        final ColorViewAttributes attributes;
        int color;
        boolean visible;
        View view = null;
        int targetVisibility = 4;
        boolean present = false;

        ColorViewState(ColorViewAttributes colorViewAttributes) {
            this.attributes = colorViewAttributes;
        }
    }

    public static class ColorViewAttributes {
        final int hideWindowFlag;
        final int horizontalGravity;
        final int id;
        final int insetsType;
        final int seascapeGravity;
        final int systemUiHideFlag;
        final String transitionName;
        final int translucentFlag;
        final int verticalGravity;

        public boolean isPresent(boolean z, int i, boolean z2) {
            return z && ((Integer.MIN_VALUE & i) != 0 || z2);
        }

        private ColorViewAttributes(int i, int i2, int i3, int i4, int i5, String str, int i6, int i7, int i8) {
            this.id = i6;
            this.systemUiHideFlag = i;
            this.translucentFlag = i2;
            this.verticalGravity = i3;
            this.horizontalGravity = i4;
            this.seascapeGravity = i5;
            this.transitionName = str;
            this.hideWindowFlag = i7;
            this.insetsType = i8;
        }

        public boolean isPresent(int i, int i2, boolean z) {
            return (i & this.systemUiHideFlag) == 0 && (this.hideWindowFlag & i2) == 0 && ((Integer.MIN_VALUE & i2) != 0 || z);
        }

        public boolean isVisible(boolean z, int i, int i2, boolean z2) {
            return z && ((-16777216) & i) != 0 && ((this.translucentFlag & i2) == 0 || z2);
        }

        public boolean isVisible(int i, int i2, int i3, boolean z) {
            return isVisible(isPresent(i, i3, z), i2, i3, z);
        }

        public boolean isVisible(InsetsState insetsState, int i, int i2, boolean z) {
            return isVisible(isPresent(insetsState.getSource(this.insetsType).isVisible(), i2, z), i, i2, z);
        }
    }

    private class ActionModeCallback2Wrapper extends ActionMode.Callback2 {
        private final ActionMode.Callback mWrapped;

        public ActionModeCallback2Wrapper(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            DecorView.this.requestFitSystemWindows();
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            boolean z;
            this.mWrapped.onDestroyActionMode(actionMode);
            if (DecorView.this.mContext.getApplicationInfo().targetSdkVersion >= 23) {
                z = actionMode == DecorView.this.mPrimaryActionMode;
                z = actionMode == DecorView.this.mFloatingActionMode;
                if (!z && actionMode.getType() == 0) {
                    Log.e(DecorView.this.mLogTag, "Destroying unexpected ActionMode instance of TYPE_PRIMARY; " + actionMode + " was not the current primary action mode! Expected " + DecorView.this.mPrimaryActionMode);
                }
                if (!z && actionMode.getType() == 1) {
                    Log.e(DecorView.this.mLogTag, "Destroying unexpected ActionMode instance of TYPE_FLOATING; " + actionMode + " was not the current floating action mode! Expected " + DecorView.this.mFloatingActionMode);
                }
            } else {
                z = actionMode.getType() == 0;
                if (actionMode.getType() == 1) {
                    z = true;
                }
            }
            if (z) {
                if (DecorView.this.mPrimaryActionModePopup != null) {
                    DecorView decorView = DecorView.this;
                    decorView.removeCallbacks(decorView.mShowPrimaryActionModePopup);
                }
                if (DecorView.this.mPrimaryActionModeView != null) {
                    DecorView.this.endOnGoingFadeAnimation();
                    final ActionBarContextView actionBarContextView = DecorView.this.mPrimaryActionModeView;
                    DecorView decorView2 = DecorView.this;
                    decorView2.mFadeAnim = ObjectAnimator.ofFloat(decorView2.mPrimaryActionModeView, (Property<ActionBarContextView, Float>) View.ALPHA, 1.0f, 0.0f);
                    DecorView.this.mFadeAnim.addListener(new Animator.AnimatorListener() { // from class: com.android.internal.policy.DecorView.ActionModeCallback2Wrapper.1
                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationRepeat(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            if (actionBarContextView == DecorView.this.mPrimaryActionModeView) {
                                actionBarContextView.setVisibility(8);
                                if (DecorView.this.mPrimaryActionModePopup != null) {
                                    DecorView.this.mPrimaryActionModePopup.dismiss();
                                }
                                actionBarContextView.killMode();
                                DecorView.this.mFadeAnim = null;
                                DecorView.this.requestApplyInsets();
                            }
                        }
                    });
                    DecorView.this.mFadeAnim.start();
                }
                DecorView.this.mPrimaryActionMode = null;
            } else if (z) {
                DecorView.this.cleanupFloatingActionModeViews();
                DecorView.this.mFloatingActionMode = null;
            }
            if (DecorView.this.mWindow.getCallback() != null && !DecorView.this.mWindow.isDestroyed()) {
                try {
                    DecorView.this.mWindow.getCallback().onActionModeFinished(actionMode);
                } catch (AbstractMethodError unused) {
                }
            }
            DecorView.this.requestFitSystemWindows();
        }

        @Override // android.view.ActionMode.Callback2
        public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            ActionMode.Callback callback = this.mWrapped;
            if (callback instanceof ActionMode.Callback2) {
                ((ActionMode.Callback2) callback).onGetContentRect(actionMode, view, rect);
            } else {
                super.onGetContentRect(actionMode, view, rect);
            }
        }
    }
}
