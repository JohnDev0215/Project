package com.android.systemui.wm;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.RotationUtils;
import android.util.Size;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.DisplayInfo;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class DisplayLayout {
    public static final int NAV_BAR_BOTTOM = 4;
    public static final int NAV_BAR_LEFT = 1;
    public static final int NAV_BAR_RIGHT = 2;
    private DisplayCutout mCutout;
    private int mDensityDpi;
    private int mHeight;
    private int mRotation;
    private int mUiMode;
    private int mWidth;
    private final Rect mNonDecorInsets = new Rect();
    private final Rect mStableInsets = new Rect();
    private boolean mHasNavigationBar = false;
    private boolean mHasStatusBar = false;
    private int mNavBarFrameHeight = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface NavBarPosition {
    }

    private static int getBoundIndexFromRotation(int index, int rotation) {
        int i = index - rotation;
        return i < 0 ? i + 4 : i;
    }

    static boolean hasStatusBar(int displayId) {
        return displayId == 0;
    }

    public DisplayLayout() {
    }

    public DisplayLayout(DisplayInfo info, Resources res, boolean hasNavigationBar, boolean hasStatusBar) {
        init(info, res, hasNavigationBar, hasStatusBar);
    }

    public DisplayLayout(Context context, Display rawDisplay) {
        int displayId = rawDisplay.getDisplayId();
        DisplayInfo displayInfo = new DisplayInfo();
        rawDisplay.getDisplayInfo(displayInfo);
        init(displayInfo, context.getResources(), hasNavigationBar(displayInfo, context, displayId), hasStatusBar(displayId));
    }

    public DisplayLayout(DisplayLayout dl) {
        set(dl);
    }

    public final void set(DisplayLayout dl) {
        this.mUiMode = dl.mUiMode;
        this.mWidth = dl.mWidth;
        this.mHeight = dl.mHeight;
        this.mCutout = dl.mCutout;
        this.mRotation = dl.mRotation;
        this.mDensityDpi = dl.mDensityDpi;
        this.mHasNavigationBar = dl.mHasNavigationBar;
        this.mHasStatusBar = dl.mHasStatusBar;
        this.mNonDecorInsets.set(dl.mNonDecorInsets);
        this.mStableInsets.set(dl.mStableInsets);
    }

    private void init(DisplayInfo info, Resources res, boolean hasNavigationBar, boolean hasStatusBar) {
        this.mUiMode = res.getConfiguration().uiMode;
        this.mWidth = info.logicalWidth;
        this.mHeight = info.logicalHeight;
        this.mRotation = info.rotation;
        this.mCutout = info.displayCutout;
        this.mDensityDpi = info.logicalDensityDpi;
        this.mHasNavigationBar = hasNavigationBar;
        this.mHasStatusBar = hasStatusBar;
        recalcInsets(res);
    }

    private void recalcInsets(Resources res) {
        computeNonDecorInsets(res, this.mRotation, this.mWidth, this.mHeight, this.mCutout, this.mUiMode, this.mNonDecorInsets, this.mHasNavigationBar);
        this.mStableInsets.set(this.mNonDecorInsets);
        boolean z = this.mHasStatusBar;
        if (z) {
            convertNonDecorInsetsToStableInsets(res, this.mStableInsets, this.mWidth, this.mHeight, z);
        }
        this.mNavBarFrameHeight = getNavigationBarFrameHeight(res, this.mWidth > this.mHeight);
    }

    public void rotateTo(Resources res, int targetRotation) {
        int i = ((targetRotation - this.mRotation) + 4) % 4;
        boolean z = i % 2 != 0;
        int i2 = this.mWidth;
        int i3 = this.mHeight;
        this.mRotation = targetRotation;
        if (z) {
            this.mWidth = i3;
            this.mHeight = i2;
        }
        DisplayCutout displayCutout = this.mCutout;
        if (displayCutout != null && !displayCutout.isEmpty()) {
            this.mCutout = calculateDisplayCutoutForRotation(this.mCutout, i, i2, i3);
        }
        recalcInsets(res);
    }

    public Rect nonDecorInsets() {
        return this.mNonDecorInsets;
    }

    public Rect stableInsets() {
        return this.mStableInsets;
    }

    public int width() {
        return this.mWidth;
    }

    public int height() {
        return this.mHeight;
    }

    public int rotation() {
        return this.mRotation;
    }

    public int densityDpi() {
        return this.mDensityDpi;
    }

    public float density() {
        return this.mDensityDpi * 0.00625f;
    }

    public boolean isLandscape() {
        return this.mWidth > this.mHeight;
    }

    public int navBarFrameHeight() {
        return this.mNavBarFrameHeight;
    }

    public int getOrientation() {
        return this.mWidth > this.mHeight ? 2 : 1;
    }

    public void getStableBounds(Rect outBounds) {
        outBounds.set(0, 0, this.mWidth, this.mHeight);
        outBounds.inset(this.mStableInsets);
    }

    public int getNavigationBarPosition(Resources res) {
        BydLogHelper.logD("getNavigationBarPosition mWidth : " + this.mWidth + " , mHeight : " + this.mHeight + " , mRotation : " + this.mRotation);
        return navigationBarPosition(res, this.mWidth, this.mHeight, this.mRotation);
    }

    public static void rotateBounds(Rect inOutBounds, Rect parentBounds, int delta) {
        int i = ((delta % 4) + 4) % 4;
        int i2 = inOutBounds.left;
        if (i == 1) {
            inOutBounds.left = inOutBounds.top;
            inOutBounds.top = parentBounds.right - inOutBounds.right;
            inOutBounds.right = inOutBounds.bottom;
            inOutBounds.bottom = parentBounds.right - i2;
            return;
        }
        if (i == 2) {
            inOutBounds.left = parentBounds.right - inOutBounds.right;
            inOutBounds.right = parentBounds.right - i2;
        } else {
            if (i != 3) {
                return;
            }
            inOutBounds.left = parentBounds.bottom - inOutBounds.bottom;
            inOutBounds.bottom = inOutBounds.right;
            inOutBounds.right = parentBounds.bottom - inOutBounds.top;
            inOutBounds.top = i2;
        }
    }

    private static void convertNonDecorInsetsToStableInsets(Resources res, Rect inOutInsets, int displayWidth, int displayHeight, boolean hasStatusBar) {
        if (hasStatusBar) {
            inOutInsets.top = Math.max(inOutInsets.top, getStatusBarHeight(displayWidth > displayHeight, res));
        }
    }

    static void computeNonDecorInsets(Resources res, int displayRotation, int displayWidth, int displayHeight, DisplayCutout displayCutout, int uiMode, Rect outInsets, boolean hasNavigationBar) {
        outInsets.setEmpty();
        if (hasNavigationBar) {
            int iNavigationBarPosition = navigationBarPosition(res, displayWidth, displayHeight, displayRotation);
            int navigationBarSize = getNavigationBarSize(res, iNavigationBarPosition, displayWidth > displayHeight, uiMode);
            if (iNavigationBarPosition == 4) {
                outInsets.bottom = navigationBarSize;
            } else if (iNavigationBarPosition == 2) {
                outInsets.right = navigationBarSize;
            } else if (iNavigationBarPosition == 1) {
                outInsets.left = navigationBarSize;
            }
        }
        if (displayCutout != null) {
            outInsets.left += displayCutout.getSafeInsetLeft();
            outInsets.top += displayCutout.getSafeInsetTop();
            outInsets.right += displayCutout.getSafeInsetRight();
            outInsets.bottom += displayCutout.getSafeInsetBottom();
        }
    }

    static void computeStableInsets(Resources res, int displayRotation, int displayWidth, int displayHeight, DisplayCutout displayCutout, int uiMode, Rect outInsets, boolean hasNavigationBar, boolean hasStatusBar) {
        outInsets.setEmpty();
        computeNonDecorInsets(res, displayRotation, displayWidth, displayHeight, displayCutout, uiMode, outInsets, hasNavigationBar);
        convertNonDecorInsetsToStableInsets(res, outInsets, displayWidth, displayHeight, hasStatusBar);
    }

    static int getStatusBarHeight(boolean landscape, Resources res) {
        if (landscape) {
            return res.getDimensionPixelSize(R.dimen.media_notification_expanded_image_max_size);
        }
        return res.getDimensionPixelSize(R.dimen.media_notification_header_height);
    }

    public static DisplayCutout calculateDisplayCutoutForRotation(DisplayCutout cutout, int rotation, int displayWidth, int displayHeight) {
        if (cutout == null || cutout == DisplayCutout.NO_CUTOUT) {
            return null;
        }
        Insets insetsRotateInsets = RotationUtils.rotateInsets(cutout.getWaterfallInsets(), rotation);
        if (rotation == 0) {
            return computeSafeInsets(cutout, displayWidth, displayHeight);
        }
        boolean z = true;
        if (rotation != 1 && rotation != 3) {
            z = false;
        }
        Rect[] boundingRectsAll = cutout.getBoundingRectsAll();
        Rect[] rectArr = new Rect[boundingRectsAll.length];
        Rect rect = new Rect(0, 0, displayWidth, displayHeight);
        for (int i = 0; i < boundingRectsAll.length; i++) {
            Rect rect2 = new Rect(boundingRectsAll[i]);
            if (!rect2.isEmpty()) {
                rotateBounds(rect2, rect, rotation);
            }
            rectArr[getBoundIndexFromRotation(i, rotation)] = rect2;
        }
        DisplayCutout displayCutoutFromBoundsAndWaterfall = DisplayCutout.fromBoundsAndWaterfall(rectArr, insetsRotateInsets);
        int i2 = z ? displayHeight : displayWidth;
        if (!z) {
            displayWidth = displayHeight;
        }
        return computeSafeInsets(displayCutoutFromBoundsAndWaterfall, i2, displayWidth);
    }

    public static DisplayCutout computeSafeInsets(DisplayCutout inner, int displayWidth, int displayHeight) {
        if (inner == DisplayCutout.NO_CUTOUT) {
            return null;
        }
        return inner.replaceSafeInsets(computeSafeInsets(new Size(displayWidth, displayHeight), inner));
    }

    private static Rect computeSafeInsets(Size displaySize, DisplayCutout cutout) {
        if (displaySize.getWidth() == displaySize.getHeight()) {
            throw new UnsupportedOperationException("not implemented: display=" + displaySize + " cutout=" + cutout);
        }
        return new Rect(Math.max(cutout.getWaterfallInsets().left, findCutoutInsetForSide(displaySize, cutout.getBoundingRectLeft(), 3)), Math.max(cutout.getWaterfallInsets().top, findCutoutInsetForSide(displaySize, cutout.getBoundingRectTop(), 48)), Math.max(cutout.getWaterfallInsets().right, findCutoutInsetForSide(displaySize, cutout.getBoundingRectRight(), 5)), Math.max(cutout.getWaterfallInsets().bottom, findCutoutInsetForSide(displaySize, cutout.getBoundingRectBottom(), 80)));
    }

    private static int findCutoutInsetForSide(Size display, Rect boundingRect, int gravity) {
        if (boundingRect.isEmpty()) {
            return 0;
        }
        if (gravity == 3) {
            return Math.max(0, boundingRect.right);
        }
        if (gravity == 5) {
            return Math.max(0, display.getWidth() - boundingRect.left);
        }
        if (gravity == 48) {
            return Math.max(0, boundingRect.bottom);
        }
        if (gravity == 80) {
            return Math.max(0, display.getHeight() - boundingRect.top);
        }
        throw new IllegalArgumentException("unknown gravity: " + gravity);
    }

    static boolean hasNavigationBar(DisplayInfo info, Context context, int displayId) {
        if (displayId == 0) {
            String str = SystemProperties.get("qemu.hw.mainkeys");
            if ("1".equals(str)) {
                return false;
            }
            if (Constant.CarType.CAR_TYPE_FUEL.equals(str)) {
                return true;
            }
            return context.getResources().getBoolean(R.bool.config_cecQuerySadMpeg2_userConfigurable);
        }
        boolean z = info.type == 5 && info.ownerUid != 1000;
        boolean z2 = Settings.Global.getInt(context.getContentResolver(), "force_desktop_mode_on_external_displays", 0) != 0;
        if ((info.flags & 64) == 0) {
            return z2 && !z;
        }
        return true;
    }

    public static int navigationBarPosition(Resources res, int displayWidth, int displayHeight, int rotation) {
        boolean z = displayWidth != displayHeight && res.getBoolean(R.bool.config_cecQuerySadDtsDisabled_allowed);
        BydLogHelper.logD("navigationBarPosition navBarCanMove : " + z + " , displayWidth : " + displayWidth + " , displayHeight : " + displayHeight + " , rotation : " + rotation);
        if (!z || displayWidth <= displayHeight) {
            return 4;
        }
        return rotation == 1 ? 2 : 1;
    }

    public static int getNavigationBarSize(Resources res, int navBarSide, boolean landscape, int uiMode) {
        if ((uiMode & 15) == 3) {
            if (navBarSide == 4) {
                return res.getDimensionPixelSize(landscape ? R.dimen.importance_ring_stroke_width : R.dimen.importance_ring_anim_max_stroke_width);
            }
            return res.getDimensionPixelSize(R.dimen.indeterminate_progress_alpha_03);
        }
        if (navBarSide == 4) {
            return res.getDimensionPixelSize(landscape ? R.dimen.importance_ring_size : R.dimen.immersive_mode_cling_width);
        }
        return res.getDimensionPixelSize(R.dimen.indeterminate_progress_alpha_02);
    }

    public static int getNavigationBarFrameHeight(Resources res, boolean landscape) {
        return res.getDimensionPixelSize(landscape ? R.dimen.image_margin_start : R.dimen.highlight_alpha_material_light);
    }
}
