package com.android.launcher3;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.FrameLayout;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.logging.StatsLogUtils;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.views.Transposable;

/* JADX INFO: loaded from: classes4.dex */
public class Hotseat extends CellLayout implements StatsLogUtils.LogContainerProvider, Insettable, Transposable {

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mHasVerticalHotseat;

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public Hotseat(Context context) {
        this(context, null);
    }

    public Hotseat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Hotseat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int getCellXFromOrder(int rank) {
        if (this.mHasVerticalHotseat) {
            return 0;
        }
        return rank;
    }

    int getCellYFromOrder(int rank) {
        if (this.mHasVerticalHotseat) {
            return getCountY() - (rank + 1);
        }
        return 0;
    }

    public void resetLayout(boolean hasVerticalHotseat) {
        removeAllViewsInLayout();
        this.mHasVerticalHotseat = hasVerticalHotseat;
        InvariantDeviceProfile invariantDeviceProfile = this.mActivity.getDeviceProfile().inv;
        if (hasVerticalHotseat) {
            setGridSize(1, invariantDeviceProfile.numHotseatIcons);
        } else {
            setGridSize(invariantDeviceProfile.numHotseatIcons, 1);
        }
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        target.gridX = info.cellX;
        target.gridY = info.cellY;
        targetParent.containerType = 2;
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        DeviceProfile wallpaperDeviceProfile = this.mActivity.getWallpaperDeviceProfile();
        Rect insets2 = wallpaperDeviceProfile.getInsets();
        if (wallpaperDeviceProfile.isVerticalBarLayout()) {
            layoutParams.height = -1;
            if (wallpaperDeviceProfile.isSeascape()) {
                layoutParams.gravity = 3;
                layoutParams.width = wallpaperDeviceProfile.hotseatBarSizePx + insets2.left;
            } else {
                layoutParams.gravity = 5;
                layoutParams.width = wallpaperDeviceProfile.hotseatBarSizePx + insets2.right;
            }
        } else {
            layoutParams.gravity = 80;
            layoutParams.width = -1;
            layoutParams.height = wallpaperDeviceProfile.hotseatBarSizePx + insets2.bottom;
        }
        Rect hotseatLayoutPadding = wallpaperDeviceProfile.getHotseatLayoutPadding();
        setPadding(hotseatLayoutPadding.left, hotseatLayoutPadding.top, hotseatLayoutPadding.right, hotseatLayoutPadding.bottom);
        layoutParams.height = 0;
        setLayoutParams(layoutParams);
        InsettableFrameLayout.dispatchInsets(this, insets2);
    }

    @Override // com.android.launcher3.CellLayout, com.android.launcher3.views.Transposable
    public RotationMode getRotationMode() {
        return Launcher.getLauncher(getContext()).getRotationMode();
    }
}
