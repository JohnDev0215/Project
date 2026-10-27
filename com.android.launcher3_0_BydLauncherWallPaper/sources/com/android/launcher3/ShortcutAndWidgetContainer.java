package com.android.launcher3;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.CellLayout;
import com.android.launcher3.views.ActivityContext;
import com.android.launcher3.widget.LauncherAppWidgetHostView;

/* JADX INFO: loaded from: classes4.dex */
public class ShortcutAndWidgetContainer extends ViewGroup {
    static final String TAG = "ShortcutAndWidgetContainer";
    private ActivityContext mActivity;
    private int mCellHeight;
    private int mCellWidth;
    private final int mContainerType;
    private int mCountX;
    private boolean mInvertIfRtl;
    private final int[] mTmpCellXY;
    private final WallpaperManager mWallpaperManager;

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ShortcutAndWidgetContainer(Context context, int containerType) {
        super(context);
        this.mTmpCellXY = new int[2];
        this.mInvertIfRtl = false;
        this.mActivity = ActivityContext.lookupContext(context);
        this.mWallpaperManager = WallpaperManager.getInstance(context);
        this.mContainerType = containerType;
    }

    public void setCellDimensions(int cellWidth, int cellHeight, int countX, int countY) {
        this.mCellWidth = cellWidth;
        this.mCellHeight = cellHeight;
        this.mCountX = countX;
    }

    public View getChildAt(int x, int y) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) childAt.getLayoutParams();
            if (layoutParams.cellX <= x && x < layoutParams.cellX + layoutParams.cellHSpan && layoutParams.cellY <= y && y < layoutParams.cellY + layoutParams.cellVSpan) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt);
            }
        }
    }

    public void setupLp(View child) {
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) child.getLayoutParams();
        if (child instanceof LauncherAppWidgetHostView) {
            DeviceProfile wallpaperDeviceProfile = this.mActivity.getWallpaperDeviceProfile();
            layoutParams.setup(this.mCellWidth, this.mCellHeight, invertLayoutHorizontally(), this.mCountX, wallpaperDeviceProfile.appWidgetScale.x, wallpaperDeviceProfile.appWidgetScale.y);
        } else {
            layoutParams.setup(this.mCellWidth, this.mCellHeight, invertLayoutHorizontally(), this.mCountX);
        }
    }

    public void setInvertIfRtl(boolean invert) {
        this.mInvertIfRtl = invert;
    }

    public int getCellContentHeight() {
        return Math.min(getMeasuredHeight(), this.mActivity.getWallpaperDeviceProfile().getCellHeight(this.mContainerType));
    }

    public void measureChild(View child) {
        int i;
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) child.getLayoutParams();
        DeviceProfile wallpaperDeviceProfile = this.mActivity.getWallpaperDeviceProfile();
        if (child instanceof LauncherAppWidgetHostView) {
            layoutParams.setup(this.mCellWidth, this.mCellHeight, invertLayoutHorizontally(), this.mCountX, wallpaperDeviceProfile.appWidgetScale.x, wallpaperDeviceProfile.appWidgetScale.y);
        } else {
            layoutParams.setup(this.mCellWidth, this.mCellHeight, invertLayoutHorizontally(), this.mCountX);
            int iMax = (int) Math.max(0.0f, (layoutParams.height - getCellContentHeight()) / 2.0f);
            if (this.mContainerType == 0) {
                i = wallpaperDeviceProfile.workspaceCellPaddingXPx;
            } else {
                i = (int) (wallpaperDeviceProfile.edgeMarginPx / 2.0f);
            }
            child.setPadding(i, iMax, i, 0);
        }
        child.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
    }

    public boolean invertLayoutHorizontally() {
        return this.mInvertIfRtl && Utilities.isRtl(getResources());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) childAt.getLayoutParams();
                if (childAt instanceof LauncherAppWidgetHostView) {
                    LauncherAppWidgetHostView launcherAppWidgetHostView = (LauncherAppWidgetHostView) childAt;
                    DeviceProfile deviceProfile = this.mActivity.getDeviceProfile();
                    float f = deviceProfile.appWidgetScale.x;
                    float f2 = deviceProfile.appWidgetScale.y;
                    launcherAppWidgetHostView.setScaleToFit(Math.min(f, f2));
                    launcherAppWidgetHostView.setTranslationForCentering((-(layoutParams.width - (layoutParams.width * f))) / 2.0f, (-(layoutParams.height - (layoutParams.height * f2))) / 2.0f);
                }
                int i2 = layoutParams.x;
                int i3 = layoutParams.y;
                childAt.layout(i2, i3, layoutParams.width + i2, layoutParams.height + i3);
                if (layoutParams.dropped) {
                    layoutParams.dropped = false;
                    int[] iArr = this.mTmpCellXY;
                    getLocationOnScreen(iArr);
                    this.mWallpaperManager.sendWallpaperCommand(getWindowToken(), WallpaperManager.COMMAND_DROP, iArr[0] + i2 + (layoutParams.width / 2), iArr[1] + i3 + (layoutParams.height / 2), 0, null);
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0 && getAlpha() == 0.0f) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        if (child != null) {
            Rect rect = new Rect();
            child.getDrawingRect(rect);
            requestRectangleOnScreen(rect);
        }
    }

    @Override // android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).cancelLongPress();
        }
    }
}
