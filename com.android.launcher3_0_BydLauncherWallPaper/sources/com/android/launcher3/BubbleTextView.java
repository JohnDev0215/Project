package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.TextView;
import com.android.launcher3.Launcher;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;
import com.android.launcher3.dot.DotInfo;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.graphics.DrawableFactory;
import com.android.launcher3.graphics.IconPalette;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.graphics.PreloadIconDrawable;
import com.android.launcher3.icons.DotRenderer;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.views.ActivityContext;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes4.dex */
public class BubbleTextView extends TextView implements IconCache.ItemInfoUpdateReceiver, Launcher.OnResumeCallback {
    private static final int DISPLAY_ALL_APPS = 1;
    private static final int DISPLAY_FOLDER = 2;
    private static final int DISPLAY_WORKSPACE = 0;
    private final ActivityContext mActivity;
    private final boolean mCenterVertically;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mDisableRelayout;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private DotInfo mDotInfo;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE, deepExport = true)
    private DotRenderer.DrawParams mDotParams;
    private DotRenderer mDotRenderer;
    private Animator mDotScaleAnim;
    private boolean mForceHideDot;
    private Drawable mIcon;
    private IconCache.IconLoadRequest mIconLoadRequest;
    private final int mIconSize;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mIgnorePressedStateChange;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mIsIconVisible;
    private final boolean mLayoutHorizontal;
    private final CheckLongPressHelper mLongPressHelper;
    private final float mSlop;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mStayPressed;
    private final StylusEventHelper mStylusEventHelper;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private float mTextAlpha;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private int mTextColor;
    private static final int[] STATE_PRESSED = {16842919};
    private static final Property<BubbleTextView, Float> DOT_SCALE_PROPERTY = new Property<BubbleTextView, Float>(Float.TYPE, "dotScale") { // from class: com.android.launcher3.BubbleTextView.1
        @Override // android.util.Property
        public Float get(BubbleTextView bubbleTextView) {
            return Float.valueOf(bubbleTextView.mDotParams.scale);
        }

        @Override // android.util.Property
        public void set(BubbleTextView bubbleTextView, Float value) {
            bubbleTextView.mDotParams.scale = value.floatValue();
            bubbleTextView.invalidate();
        }
    };
    public static final Property<BubbleTextView, Float> TEXT_ALPHA_PROPERTY = new Property<BubbleTextView, Float>(Float.class, "textAlpha") { // from class: com.android.launcher3.BubbleTextView.2
        @Override // android.util.Property
        public Float get(BubbleTextView bubbleTextView) {
            return Float.valueOf(bubbleTextView.mTextAlpha);
        }

        @Override // android.util.Property
        public void set(BubbleTextView bubbleTextView, Float alpha) {
            bubbleTextView.setTextAlpha(alpha.floatValue());
        }
    };

    public BubbleTextView(Context context) {
        this(context, null, 0);
    }

    public BubbleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleTextView(Context context, AttributeSet attrs, int defStyle) {
        int i;
        super(context, attrs, defStyle);
        this.mIsIconVisible = true;
        this.mTextAlpha = 1.0f;
        this.mDisableRelayout = false;
        ActivityContext activityContextLookupContext = ActivityContext.lookupContext(context);
        this.mActivity = activityContextLookupContext;
        this.mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.BubbleTextView, defStyle, 0);
        this.mLayoutHorizontal = typedArrayObtainStyledAttributes.getBoolean(3, false);
        int integer = typedArrayObtainStyledAttributes.getInteger(1, 0);
        if (integer == 0) {
            DeviceProfile wallpaperDeviceProfile = activityContextLookupContext.getWallpaperDeviceProfile();
            setTextSize(0, wallpaperDeviceProfile.iconTextSizePx);
            setCompoundDrawablePadding(wallpaperDeviceProfile.iconDrawablePaddingPx);
            i = wallpaperDeviceProfile.iconSizePx;
        } else if (integer == 1) {
            DeviceProfile deviceProfile = activityContextLookupContext.getDeviceProfile();
            setTextSize(0, deviceProfile.allAppsIconTextSizePx);
            setCompoundDrawablePadding(deviceProfile.allAppsIconDrawablePaddingPx);
            i = deviceProfile.allAppsIconSizePx;
        } else if (integer == 2) {
            DeviceProfile deviceProfile2 = activityContextLookupContext.getDeviceProfile();
            setTextSize(0, deviceProfile2.folderChildTextSizePx);
            setCompoundDrawablePadding(deviceProfile2.folderChildDrawablePaddingPx);
            i = deviceProfile2.folderChildIconSizePx;
        } else {
            i = activityContextLookupContext.getDeviceProfile().iconSizePx;
        }
        this.mCenterVertically = typedArrayObtainStyledAttributes.getBoolean(0, false);
        this.mIconSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, i);
        typedArrayObtainStyledAttributes.recycle();
        this.mLongPressHelper = new CheckLongPressHelper(this);
        this.mStylusEventHelper = new StylusEventHelper(new SimpleOnStylusPressListener(this), this);
        this.mDotParams = new DotRenderer.DrawParams();
        setEllipsize(TextUtils.TruncateAt.END);
        setAccessibilityDelegate(activityContextLookupContext.getAccessibilityDelegate());
        setTextAlpha(1.0f);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        setEllipsize(focused ? TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.END);
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    public void reset() {
        this.mDotInfo = null;
        this.mDotParams.color = 0;
        cancelDotScaleAnim();
        this.mDotParams.scale = 0.0f;
        this.mForceHideDot = false;
    }

    private void cancelDotScaleAnim() {
        Animator animator = this.mDotScaleAnim;
        if (animator != null) {
            animator.cancel();
        }
    }

    private void animateDotScale(float... dotScales) {
        cancelDotScaleAnim();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, DOT_SCALE_PROPERTY, dotScales);
        this.mDotScaleAnim = objectAnimatorOfFloat;
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.BubbleTextView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                BubbleTextView.this.mDotScaleAnim = null;
            }
        });
        this.mDotScaleAnim.start();
    }

    public void applyFromWorkspaceItem(WorkspaceItemInfo info) {
        applyFromWorkspaceItem(info, false);
    }

    @Override // android.view.View
    public void setAccessibilityDelegate(View.AccessibilityDelegate delegate) {
        if (delegate instanceof LauncherAccessibilityDelegate) {
            super.setAccessibilityDelegate(delegate);
        }
    }

    public void applyFromWorkspaceItem(WorkspaceItemInfo info, boolean promiseStateChanged) {
        applyIconAndLabel(info);
        setTag(info);
        if (promiseStateChanged || info.hasPromiseIconUi()) {
            applyPromiseState(promiseStateChanged);
        }
        applyDotState(info, false);
    }

    public void applyFromApplicationInfo(AppInfo info) {
        applyIconAndLabel(info);
        super.setTag(info);
        verifyHighRes();
        if (info instanceof PromiseAppInfo) {
            applyProgressLevel(((PromiseAppInfo) info).level);
        }
        applyDotState(info, false);
    }

    public void applyFromPackageItemInfo(PackageItemInfo info) {
        applyIconAndLabel(info);
        super.setTag(info);
        verifyHighRes();
    }

    private void applyIconAndLabel(ItemInfoWithIcon info) {
        CharSequence string;
        FastBitmapDrawable fastBitmapDrawableNewIcon = DrawableFactory.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext()).newIcon(getContext(), info);
        this.mDotParams.color = IconPalette.getMutedColor(info.iconColor, 0.54f);
        setIcon(fastBitmapDrawableNewIcon);
        setText(info.title);
        if (info.contentDescription != null) {
            if (info.isDisabled()) {
                string = getContext().getString(R.string.disabled_app_label, info.contentDescription);
            } else {
                string = info.contentDescription;
            }
            setContentDescription(string);
        }
    }

    public void setLongPressTimeoutFactor(float longPressTimeoutFactor) {
        this.mLongPressHelper.setLongPressTimeoutFactor(longPressTimeoutFactor);
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        if (this.mIgnorePressedStateChange) {
            return;
        }
        super.refreshDrawableState();
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(extraSpace + 1);
        if (this.mStayPressed) {
            mergeDrawableStates(iArrOnCreateDrawableState, STATE_PRESSED);
        }
        return iArrOnCreateDrawableState;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            boolean r0 = com.android.launcher3.testing.TestProtocol.sDebugTracing
            if (r0 == 0) goto L1c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "BubbleTextView.onTouchEvent "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "b/132900132"
            android.util.Log.d(r1, r0)
        L1c:
            boolean r0 = super.onTouchEvent(r4)
            com.android.launcher3.StylusEventHelper r1 = r3.mStylusEventHelper
            boolean r1 = r1.onMotionEvent(r4)
            r2 = 1
            if (r1 == 0) goto L2f
            com.android.launcher3.CheckLongPressHelper r0 = r3.mLongPressHelper
            r0.cancelLongPress()
            r0 = r2
        L2f:
            int r1 = r4.getAction()
            if (r1 == 0) goto L5a
            if (r1 == r2) goto L54
            r2 = 2
            if (r1 == r2) goto L3e
            r4 = 3
            if (r1 == r4) goto L54
            goto L67
        L3e:
            float r1 = r4.getX()
            float r4 = r4.getY()
            float r2 = r3.mSlop
            boolean r4 = com.android.launcher3.Utilities.pointInView(r3, r1, r4, r2)
            if (r4 != 0) goto L67
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
            goto L67
        L54:
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
            goto L67
        L5a:
            com.android.launcher3.StylusEventHelper r4 = r3.mStylusEventHelper
            boolean r4 = r4.inStylusButtonPressed()
            if (r4 != 0) goto L67
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.postCheckForLongPress()
        L67:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.BubbleTextView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    void setStayPressed(boolean stayPressed) {
        this.mStayPressed = stayPressed;
        refreshDrawableState();
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean isVisible) {
        super.onVisibilityAggregated(isVisible);
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            drawable.setVisible(isVisible, false);
        }
    }

    @Override // com.android.launcher3.Launcher.OnResumeCallback
    public void onLauncherResume() {
        setStayPressed(false);
    }

    void clearPressedBackground() {
        setPressed(false);
        setStayPressed(false);
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        this.mIgnorePressedStateChange = true;
        boolean zOnKeyUp = super.onKeyUp(keyCode, event);
        this.mIgnorePressedStateChange = false;
        refreshDrawableState();
        return zOnKeyUp;
    }

    protected void drawWithoutDot(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDotIfNecessary(canvas);
    }

    protected void drawDotIfNecessary(Canvas canvas) {
        if (this.mForceHideDot) {
            return;
        }
        if (hasDot() || this.mDotParams.scale > 0.0f) {
            getIconBounds(this.mDotParams.iconBounds);
            Utilities.scaleRectAboutCenter(this.mDotParams.iconBounds, IconShape.getNormalizationScale());
            canvas.translate(getScrollX(), getScrollY());
            this.mDotRenderer.draw(canvas, this.mDotParams);
            canvas.translate(-r0, -r1);
        }
    }

    public void forceHideDot(boolean forceHideDot) {
        if (this.mForceHideDot == forceHideDot) {
            return;
        }
        this.mForceHideDot = forceHideDot;
        if (forceHideDot) {
            invalidate();
        } else if (hasDot()) {
            animateDotScale(0.0f, 1.0f);
        }
    }

    private boolean hasDot() {
        return this.mDotInfo != null;
    }

    public void getIconBounds(Rect outBounds) {
        getIconBounds(this, outBounds, this.mIconSize);
    }

    public static void getIconBounds(View iconView, Rect outBounds, int iconSize) {
        int paddingTop = iconView.getPaddingTop();
        int width = (iconView.getWidth() - iconSize) / 2;
        outBounds.set(width, paddingTop, width + iconSize, iconSize + paddingTop);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mCenterVertically) {
            Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
            setPadding(getPaddingLeft(), (View.MeasureSpec.getSize(heightMeasureSpec) - ((this.mIconSize + getCompoundDrawablePadding()) + ((int) Math.ceil(fontMetrics.bottom - fontMetrics.top)))) / 2, getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.widget.TextView
    public void setTextColor(int color) {
        this.mTextColor = color;
        super.setTextColor(getModifiedColor());
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colors) {
        this.mTextColor = colors.getDefaultColor();
        if (Float.compare(this.mTextAlpha, 1.0f) == 0) {
            super.setTextColor(colors);
        } else {
            super.setTextColor(getModifiedColor());
        }
    }

    public boolean shouldTextBeVisible() {
        Object tag = getParent() instanceof FolderIcon ? ((View) getParent()).getTag() : getTag();
        ItemInfo itemInfo = tag instanceof ItemInfo ? (ItemInfo) tag : null;
        return itemInfo == null || itemInfo.container != -101;
    }

    public void setTextVisibility(boolean visible) {
        setTextAlpha(visible ? 1.0f : 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextAlpha(float alpha) {
        this.mTextAlpha = alpha;
        super.setTextColor(getModifiedColor());
    }

    private int getModifiedColor() {
        if (this.mTextAlpha == 0.0f) {
            return 0;
        }
        return GraphicsUtils.setColorAlphaBound(this.mTextColor, Math.round(Color.alpha(r0) * this.mTextAlpha));
    }

    public ObjectAnimator createTextAlphaAnimator(boolean fadeIn) {
        return ObjectAnimator.ofFloat(this, TEXT_ALPHA_PROPERTY, (shouldTextBeVisible() && fadeIn) ? 1.0f : 0.0f);
    }

    @Override // android.widget.TextView, android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        this.mLongPressHelper.cancelLongPress();
    }

    public void applyPromiseState(boolean promiseStateChanged) {
        int installProgress;
        if (getTag() instanceof WorkspaceItemInfo) {
            WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) getTag();
            if (workspaceItemInfo.hasPromiseIconUi()) {
                installProgress = workspaceItemInfo.hasStatusFlag(4) ? workspaceItemInfo.getInstallProgress() : 0;
            } else {
                installProgress = 100;
            }
            PreloadIconDrawable preloadIconDrawableApplyProgressLevel = applyProgressLevel(installProgress);
            if (preloadIconDrawableApplyProgressLevel == null || !promiseStateChanged) {
                return;
            }
            preloadIconDrawableApplyProgressLevel.maybePerformFinishedAnimation();
        }
    }

    public PreloadIconDrawable applyProgressLevel(int progressLevel) {
        if (!(getTag() instanceof ItemInfoWithIcon)) {
            return null;
        }
        ItemInfoWithIcon itemInfoWithIcon = (ItemInfoWithIcon) getTag();
        if (progressLevel >= 100) {
            setContentDescription(itemInfoWithIcon.contentDescription != null ? itemInfoWithIcon.contentDescription : "");
        } else if (progressLevel > 0) {
            setContentDescription(getContext().getString(R.string.app_downloading_title, itemInfoWithIcon.title, NumberFormat.getPercentInstance().format(((double) progressLevel) * 0.01d)));
        } else {
            setContentDescription(getContext().getString(R.string.app_waiting_download_title, itemInfoWithIcon.title));
        }
        Drawable drawable = this.mIcon;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof PreloadIconDrawable) {
            PreloadIconDrawable preloadIconDrawable = (PreloadIconDrawable) drawable;
            preloadIconDrawable.setLevel(progressLevel);
            return preloadIconDrawable;
        }
        PreloadIconDrawable preloadIconDrawableNewPendingIcon = DrawableFactory.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext()).newPendingIcon(getContext(), itemInfoWithIcon);
        preloadIconDrawableNewPendingIcon.setLevel(progressLevel);
        setIcon(preloadIconDrawableNewPendingIcon);
        return preloadIconDrawableNewPendingIcon;
    }

    public void applyDotState(ItemInfo itemInfo, boolean animate) {
        if (this.mIcon instanceof FastBitmapDrawable) {
            boolean z = this.mDotInfo != null;
            DotInfo dotInfoForItem = this.mActivity.getDotInfoForItem(itemInfo);
            this.mDotInfo = dotInfoForItem;
            boolean z2 = dotInfoForItem != null;
            float f = z2 ? 1.0f : 0.0f;
            this.mDotRenderer = this.mActivity.getDeviceProfile().mDotRenderer;
            if (z || z2) {
                if (animate && (z ^ z2) && isShown()) {
                    animateDotScale(f);
                } else {
                    cancelDotScaleAnim();
                    this.mDotParams.scale = f;
                    invalidate();
                }
            }
            if (itemInfo.contentDescription != null) {
                if (itemInfo.isDisabled()) {
                    setContentDescription(getContext().getString(R.string.disabled_app_label, itemInfo.contentDescription));
                } else if (hasDot()) {
                    int notificationCount = this.mDotInfo.getNotificationCount();
                    setContentDescription(getContext().getResources().getQuantityString(R.plurals.dotted_app_label, notificationCount, itemInfo.contentDescription, Integer.valueOf(notificationCount)));
                } else {
                    setContentDescription(itemInfo.contentDescription);
                }
            }
        }
    }

    private void setIcon(Drawable icon) {
        if (this.mIsIconVisible) {
            applyCompoundDrawables(icon);
        }
        this.mIcon = icon;
        if (icon != null) {
            icon.setVisible(getWindowVisibility() == 0 && isShown(), false);
        }
    }

    public void setIconVisible(boolean visible) {
        this.mIsIconVisible = visible;
        applyCompoundDrawables(visible ? this.mIcon : new ColorDrawable(0));
    }

    protected void applyCompoundDrawables(Drawable icon) {
        this.mDisableRelayout = this.mIcon != null;
        int i = this.mIconSize;
        icon.setBounds(0, 0, i, i);
        if (this.mLayoutHorizontal) {
            setCompoundDrawablesRelative(icon, null, null, null);
        } else {
            setCompoundDrawables(null, icon, null, null);
        }
        this.mDisableRelayout = false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mDisableRelayout) {
            return;
        }
        super.requestLayout();
    }

    @Override // com.android.launcher3.icons.IconCache.ItemInfoUpdateReceiver
    public void reapplyItemInfo(ItemInfoWithIcon info) {
        if (getTag() == info) {
            this.mIconLoadRequest = null;
            this.mDisableRelayout = true;
            info.iconBitmap.prepareToDraw();
            if (info instanceof AppInfo) {
                applyFromApplicationInfo((AppInfo) info);
            } else if (info instanceof WorkspaceItemInfo) {
                applyFromWorkspaceItem((WorkspaceItemInfo) info);
                this.mActivity.invalidateParent(info);
            } else if (info instanceof PackageItemInfo) {
                applyFromPackageItemInfo((PackageItemInfo) info);
            }
            this.mDisableRelayout = false;
        }
    }

    public void verifyHighRes() {
        IconCache.IconLoadRequest iconLoadRequest = this.mIconLoadRequest;
        if (iconLoadRequest != null) {
            iconLoadRequest.cancel();
            this.mIconLoadRequest = null;
        }
        if (getTag() instanceof ItemInfoWithIcon) {
            ItemInfoWithIcon itemInfoWithIcon = (ItemInfoWithIcon) getTag();
            if (itemInfoWithIcon.usingLowResIcon()) {
                this.mIconLoadRequest = LauncherAppState.getInstance(getContext()).getIconCache().updateIconInBackground(this, itemInfoWithIcon);
            }
        }
    }

    public int getIconSize() {
        return this.mIconSize;
    }
}
