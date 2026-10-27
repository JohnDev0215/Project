package com.android.launcher3;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.icons.DotRenderer;
import com.android.launcher3.icons.IconNormalizer;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceProfile {
    private static final float MAX_HORIZONTAL_PADDING_PERCENT = 0.14f;
    private static final int PORTRAIT_TABLET_LEFT_RIGHT_PADDING_MULTIPLIER = 4;
    private static final float TALL_DEVICE_ASPECT_RATIO_THRESHOLD = 2.0f;
    public int allAppsCellHeightPx;
    public int allAppsIconDrawablePaddingPx;
    public int allAppsIconSizePx;
    public float allAppsIconTextSizePx;
    public final float aspectRatio;
    public final int availableHeightPx;
    public final int availableWidthPx;
    public int cellHeightPx;
    public final int cellLayoutBottomPaddingPx;
    public final int cellLayoutPaddingLeftRightPx;
    public int cellWidthPx;
    public final int desiredWorkspaceLeftRightMarginPx;
    public int dropTargetBarSizePx;
    public final int edgeMarginPx;
    public int folderCellHeightPx;
    public int folderCellWidthPx;
    public int folderChildDrawablePaddingPx;
    public int folderChildIconSizePx;
    public int folderChildTextSizePx;
    public int folderIconOffsetYPx;
    public int folderIconSizePx;
    public final int heightPx;
    public int hotseatBarBottomPaddingPx;
    public final int hotseatBarSidePaddingEndPx;
    public final int hotseatBarSidePaddingStartPx;
    public int hotseatBarSizePx;
    public final int hotseatBarTopPaddingPx;
    public int hotseatCellHeightPx;
    public int iconDrawablePaddingOriginalPx;
    public int iconDrawablePaddingPx;
    public int iconSizePx;
    public int iconTextSizePx;
    public final InvariantDeviceProfile inv;
    public final boolean isLandscape;
    public final boolean isLargeTablet;
    public final boolean isMultiWindowMode;
    public final boolean isPhone;
    public final boolean isTablet;
    public DotRenderer mDotRenderer;
    private boolean mIsSeascape;
    public final boolean transposeLayoutWithOrientation;
    private final int verticalDragHandleOverlapWorkspace;
    public final int verticalDragHandleSizePx;
    public final int widthPx;
    public int workspaceCellPaddingXPx;
    public float workspaceSpringLoadShrinkFactor;
    public final int workspaceSpringLoadedBottomSpace;
    public final PointF appWidgetScale = new PointF(1.0f, 1.0f);
    private final Rect mInsets = new Rect();
    public final Rect workspacePadding = new Rect();
    private final Rect mHotseatPadding = new Rect();

    public interface OnDeviceProfileChangeListener {
        void onDeviceProfileChanged(DeviceProfile dp);
    }

    public DeviceProfile(Context context, InvariantDeviceProfile inv, Point minSize, Point maxSize, int width, int height, boolean isLandscape, boolean isMultiWindowMode) {
        this.inv = inv;
        this.isLandscape = isLandscape;
        this.isMultiWindowMode = isMultiWindowMode;
        this.widthPx = width;
        this.heightPx = height;
        if (isLandscape) {
            this.availableWidthPx = maxSize.x;
            this.availableHeightPx = minSize.y;
        } else {
            this.availableWidthPx = minSize.x;
            this.availableHeightPx = maxSize.y;
        }
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        boolean z = resources.getBoolean(R.bool.is_tablet);
        this.isTablet = z;
        boolean z2 = resources.getBoolean(R.bool.is_large_tablet);
        this.isLargeTablet = z2;
        int i = 1;
        boolean z3 = (z || z2) ? false : true;
        this.isPhone = z3;
        float fMax = Math.max(width, height) / Math.min(width, height);
        this.aspectRatio = fMax;
        boolean z4 = Float.compare(fMax, TALL_DEVICE_ASPECT_RATIO_THRESHOLD) >= 0;
        this.transposeLayoutWithOrientation = resources.getBoolean(R.bool.hotseat_transpose_layout_with_orientation);
        Resources resources2 = getContext(context, isVerticalBarLayout() ? 2 : 1).getResources();
        int dimensionPixelSize = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_edge_margin);
        this.edgeMarginPx = dimensionPixelSize;
        this.desiredWorkspaceLeftRightMarginPx = isVerticalBarLayout() ? 0 : dimensionPixelSize;
        if (!isVerticalBarLayout() && z) {
            i = 4;
        }
        int dimensionPixelSize2 = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_cell_layout_padding);
        if (isLandscape) {
            this.cellLayoutPaddingLeftRightPx = 0;
            this.cellLayoutBottomPaddingPx = dimensionPixelSize2;
        } else {
            this.cellLayoutPaddingLeftRightPx = i * dimensionPixelSize2;
            this.cellLayoutBottomPaddingPx = 0;
        }
        int dimensionPixelSize3 = resources2.getDimensionPixelSize(R.dimen.vertical_drag_handle_size);
        this.verticalDragHandleSizePx = dimensionPixelSize3;
        this.verticalDragHandleOverlapWorkspace = resources2.getDimensionPixelSize(R.dimen.vertical_drag_handle_overlap_workspace);
        this.iconDrawablePaddingOriginalPx = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_icon_drawable_padding);
        this.dropTargetBarSizePx = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_drop_target_size);
        this.workspaceSpringLoadedBottomSpace = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_min_spring_loaded_space);
        this.workspaceCellPaddingXPx = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_cell_padding_x);
        this.hotseatBarTopPaddingPx = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_hotseat_top_padding);
        this.hotseatBarBottomPaddingPx = (z4 ? 0 : resources2.getDimensionPixelSize(R.dimen.dynamic_grid_hotseat_bottom_non_tall_padding)) + resources2.getDimensionPixelSize(R.dimen.dynamic_grid_hotseat_bottom_padding);
        this.hotseatBarSidePaddingEndPx = resources2.getDimensionPixelSize(R.dimen.dynamic_grid_hotseat_side_padding);
        this.hotseatBarSidePaddingStartPx = 0;
        this.hotseatBarSizePx = 0;
        updateAvailableDimensions(displayMetrics, resources2);
        if (!isVerticalBarLayout() && z3 && z4) {
            int i2 = ((getCellSize().y - this.iconSizePx) - (this.iconDrawablePaddingPx * 2)) - dimensionPixelSize3;
            this.hotseatBarSizePx += i2;
            this.hotseatBarBottomPaddingPx += i2;
            updateAvailableDimensions(displayMetrics, resources2);
        }
        updateWorkspacePadding();
        this.mDotRenderer = new DotRenderer(this.iconSizePx, IconShape.getShapePath(), 100);
    }

    public DeviceProfile copy(Context context) {
        Point point = new Point(this.availableWidthPx, this.availableHeightPx);
        return new DeviceProfile(context, this.inv, point, point, this.widthPx, this.heightPx, this.isLandscape, this.isMultiWindowMode);
    }

    public DeviceProfile getMultiWindowProfile(Context context, Point mwSize) {
        mwSize.set(Math.min(this.availableWidthPx, mwSize.x), Math.min(this.availableHeightPx, mwSize.y));
        DeviceProfile deviceProfile = new DeviceProfile(context, this.inv, mwSize, mwSize, mwSize.x, mwSize.y, this.isLandscape, true);
        if (((deviceProfile.getCellSize().y - deviceProfile.iconSizePx) - this.iconDrawablePaddingPx) - deviceProfile.iconTextSizePx < deviceProfile.iconDrawablePaddingPx * 2) {
            deviceProfile.adjustToHideWorkspaceLabels();
        }
        deviceProfile.appWidgetScale.set(deviceProfile.getCellSize().x / getCellSize().x, deviceProfile.getCellSize().y / getCellSize().y);
        deviceProfile.updateWorkspacePadding();
        return deviceProfile;
    }

    public DeviceProfile getFullScreenProfile() {
        boolean z = this.isLandscape;
        InvariantDeviceProfile invariantDeviceProfile = this.inv;
        return z ? invariantDeviceProfile.landscapeProfile : invariantDeviceProfile.portraitProfile;
    }

    private void adjustToHideWorkspaceLabels() {
        this.iconTextSizePx = 0;
        this.iconDrawablePaddingPx = 0;
        this.cellHeightPx = this.iconSizePx;
        this.allAppsCellHeightPx = this.allAppsIconSizePx + this.allAppsIconDrawablePaddingPx + Utilities.calculateTextHeight(this.allAppsIconTextSizePx) + (this.allAppsIconDrawablePaddingPx * (isVerticalBarLayout() ? 2 : 1) * 2);
    }

    private void updateAvailableDimensions(DisplayMetrics dm, Resources res) {
        updateIconSize(1.0f, res, dm);
        float f = this.cellHeightPx * this.inv.numRows;
        float f2 = this.availableHeightPx - getTotalWorkspacePadding().y;
        if (f > f2) {
            updateIconSize(f2 / f, res, dm);
        }
        updateAvailableFolderCellDimensions(dm, res);
    }

    private void updateIconSize(float scale, Resources res, DisplayMetrics dm) {
        boolean zIsVerticalBarLayout = isVerticalBarLayout();
        InvariantDeviceProfile invariantDeviceProfile = this.inv;
        this.iconSizePx = Math.max(1, (int) (ResourceUtils.pxFromDp(zIsVerticalBarLayout ? invariantDeviceProfile.landscapeIconSize : invariantDeviceProfile.iconSize, dm) * scale));
        int iPxFromSp = (int) (Utilities.pxFromSp(this.inv.iconTextSize, dm) * scale);
        this.iconTextSizePx = iPxFromSp;
        int i = (int) (this.iconDrawablePaddingOriginalPx * scale);
        this.iconDrawablePaddingPx = i;
        this.cellHeightPx = this.iconSizePx + i + Utilities.calculateTextHeight(iPxFromSp);
        int i2 = getCellSize().y;
        int i3 = this.cellHeightPx;
        int i4 = (i2 - i3) / 2;
        int i5 = this.iconDrawablePaddingPx;
        if (i5 > i4 && !zIsVerticalBarLayout && !this.isMultiWindowMode) {
            this.cellHeightPx = i3 - (i5 - i4);
            this.iconDrawablePaddingPx = i4;
        }
        int i6 = this.iconSizePx;
        int i7 = this.iconDrawablePaddingPx;
        this.cellWidthPx = i6 + i7;
        this.allAppsIconTextSizePx = this.iconTextSizePx;
        this.allAppsIconSizePx = i6;
        this.allAppsIconDrawablePaddingPx = i7;
        this.allAppsCellHeightPx = getCellSize().y;
        if (zIsVerticalBarLayout) {
            adjustToHideWorkspaceLabels();
        }
        if (zIsVerticalBarLayout) {
            this.hotseatBarSizePx = this.iconSizePx + this.hotseatBarSidePaddingStartPx + this.hotseatBarSidePaddingEndPx;
        }
        this.hotseatCellHeightPx = this.iconSizePx;
        if (!zIsVerticalBarLayout) {
            this.workspaceSpringLoadShrinkFactor = Math.min(res.getInteger(R.integer.config_workspaceSpringLoadShrinkPercentage) / 100.0f, 1.0f - ((this.dropTargetBarSizePx + this.workspaceSpringLoadedBottomSpace) / (((this.availableHeightPx - this.hotseatBarSizePx) - this.verticalDragHandleSizePx) - this.edgeMarginPx)));
        } else {
            this.workspaceSpringLoadShrinkFactor = res.getInteger(R.integer.config_workspaceSpringLoadShrinkPercentage) / 100.0f;
        }
        int normalizedCircleSize = IconNormalizer.getNormalizedCircleSize(this.iconSizePx);
        this.folderIconSizePx = normalizedCircleSize;
        this.folderIconOffsetYPx = (this.iconSizePx - normalizedCircleSize) / 2;
    }

    private void updateAvailableFolderCellDimensions(DisplayMetrics dm, Resources res) {
        int dimensionPixelSize = res.getDimensionPixelSize(R.dimen.folder_label_padding_top) + res.getDimensionPixelSize(R.dimen.folder_label_padding_bottom) + Utilities.calculateTextHeight(res.getDimension(R.dimen.folder_label_text_size));
        updateFolderCellSize(1.0f, dm, res);
        int i = this.edgeMarginPx * 2;
        Point totalWorkspacePadding = getTotalWorkspacePadding();
        float fMin = Math.min(((this.availableWidthPx - totalWorkspacePadding.x) - i) / (this.folderCellWidthPx * this.inv.numFolderColumns), ((this.availableHeightPx - totalWorkspacePadding.y) - i) / ((this.folderCellHeightPx * this.inv.numFolderRows) + dimensionPixelSize));
        if (fMin < 1.0f) {
            updateFolderCellSize(fMin, dm, res);
        }
    }

    private void updateFolderCellSize(float scale, DisplayMetrics dm, Resources res) {
        this.folderChildIconSizePx = (int) (ResourceUtils.pxFromDp(this.inv.iconSize, dm) * scale);
        int dimensionPixelSize = (int) (res.getDimensionPixelSize(R.dimen.folder_child_text_size) * scale);
        this.folderChildTextSizePx = dimensionPixelSize;
        int iCalculateTextHeight = Utilities.calculateTextHeight(dimensionPixelSize);
        int dimensionPixelSize2 = (int) (res.getDimensionPixelSize(R.dimen.folder_cell_x_padding) * scale);
        int dimensionPixelSize3 = (int) (res.getDimensionPixelSize(R.dimen.folder_cell_y_padding) * scale);
        int i = this.folderChildIconSizePx;
        this.folderCellWidthPx = (dimensionPixelSize2 * 2) + i;
        int i2 = (dimensionPixelSize3 * 2) + i + iCalculateTextHeight;
        this.folderCellHeightPx = i2;
        this.folderChildDrawablePaddingPx = Math.max(0, ((i2 - i) - iCalculateTextHeight) / 3);
    }

    public void updateInsets(Rect insets) {
        this.mInsets.set(insets);
        updateWorkspacePadding();
    }

    public Rect getInsets() {
        return this.mInsets;
    }

    public Point getCellSize() {
        Point point = new Point();
        Point totalWorkspacePadding = getTotalWorkspacePadding();
        point.x = calculateCellWidth((this.availableWidthPx - totalWorkspacePadding.x) - (this.cellLayoutPaddingLeftRightPx * 2), this.inv.numColumns);
        point.y = calculateCellHeight((this.availableHeightPx - totalWorkspacePadding.y) - this.cellLayoutBottomPaddingPx, this.inv.numRows);
        return point;
    }

    public Point getTotalWorkspacePadding() {
        updateWorkspacePadding();
        return new Point(this.workspacePadding.left + this.workspacePadding.right, this.workspacePadding.top + this.workspacePadding.bottom);
    }

    private void updateWorkspacePadding() {
        Rect rect = this.workspacePadding;
        if (isVerticalBarLayout()) {
            rect.top = 0;
            rect.bottom = this.edgeMarginPx;
            if (isSeascape()) {
                rect.left = this.hotseatBarSizePx;
                rect.right = this.verticalDragHandleSizePx;
                return;
            } else {
                rect.left = this.verticalDragHandleSizePx;
                rect.right = this.hotseatBarSizePx;
                return;
            }
        }
        int i = (this.hotseatBarSizePx + this.verticalDragHandleSizePx) - this.verticalDragHandleOverlapWorkspace;
        if (this.isTablet) {
            int iMin = ((int) Math.min(Math.max(0, this.widthPx - ((this.inv.numColumns * this.cellWidthPx) + ((this.inv.numColumns - 1) * this.cellWidthPx))), this.widthPx * MAX_HORIZONTAL_PADDING_PERCENT)) / 2;
            int iMax = Math.max(0, ((((this.heightPx - this.edgeMarginPx) - i) - ((this.inv.numRows * 2) * this.cellHeightPx)) - this.hotseatBarTopPaddingPx) - this.hotseatBarBottomPaddingPx) / 2;
            rect.set(iMin, this.edgeMarginPx + iMax, iMin, i + iMax);
            return;
        }
        int i2 = this.desiredWorkspaceLeftRightMarginPx;
        rect.set(i2, this.edgeMarginPx, i2, i);
    }

    public Rect getHotseatLayoutPadding() {
        if (isVerticalBarLayout()) {
            if (isSeascape()) {
                this.mHotseatPadding.set(this.mInsets.left + this.hotseatBarSidePaddingStartPx, this.mInsets.top, this.hotseatBarSidePaddingEndPx, this.mInsets.bottom);
            } else {
                this.mHotseatPadding.set(this.hotseatBarSidePaddingEndPx, this.mInsets.top, this.mInsets.right + this.hotseatBarSidePaddingStartPx, this.mInsets.bottom);
            }
        } else {
            int iRound = Math.round(((this.widthPx / this.inv.numColumns) - (this.widthPx / this.inv.numHotseatIcons)) / TALL_DEVICE_ASPECT_RATIO_THRESHOLD);
            this.mHotseatPadding.set(this.workspacePadding.left + iRound + this.cellLayoutPaddingLeftRightPx, this.hotseatBarTopPaddingPx, iRound + this.workspacePadding.right + this.cellLayoutPaddingLeftRightPx, this.hotseatBarBottomPaddingPx + this.mInsets.bottom + this.cellLayoutBottomPaddingPx);
        }
        return this.mHotseatPadding;
    }

    public Rect getAbsoluteOpenFolderBounds() {
        if (isVerticalBarLayout()) {
            return new Rect(this.mInsets.left + this.dropTargetBarSizePx + this.edgeMarginPx, this.mInsets.top, ((this.mInsets.left + this.availableWidthPx) - this.hotseatBarSizePx) - this.edgeMarginPx, this.mInsets.top + this.availableHeightPx);
        }
        return new Rect(this.mInsets.left + this.edgeMarginPx, this.mInsets.top + this.dropTargetBarSizePx + this.edgeMarginPx, (this.mInsets.left + this.availableWidthPx) - this.edgeMarginPx, (((this.mInsets.top + this.availableHeightPx) - this.hotseatBarSizePx) - this.verticalDragHandleSizePx) - this.edgeMarginPx);
    }

    public static int calculateCellWidth(int width, int countX) {
        return width / countX;
    }

    public static int calculateCellHeight(int height, int countY) {
        return height / countY;
    }

    public boolean isVerticalBarLayout() {
        return this.isLandscape && this.transposeLayoutWithOrientation;
    }

    public boolean updateIsSeascape(WindowManager wm) {
        if (isVerticalBarLayout()) {
            boolean z = wm.getDefaultDisplay().getRotation() == 3;
            if (this.mIsSeascape != z) {
                this.mIsSeascape = z;
                return true;
            }
        }
        return false;
    }

    public boolean isSeascape() {
        return isVerticalBarLayout() && this.mIsSeascape;
    }

    public boolean shouldFadeAdjacentWorkspaceScreens() {
        return isVerticalBarLayout() || this.isLargeTablet;
    }

    public int getCellHeight(int containerType) {
        if (containerType == 0) {
            return this.cellHeightPx;
        }
        if (containerType == 1) {
            return this.hotseatCellHeightPx;
        }
        if (containerType != 2) {
            return 0;
        }
        return this.folderCellHeightPx;
    }

    private static Context getContext(Context c, int orientation) {
        Configuration configuration = new Configuration(c.getResources().getConfiguration());
        configuration.orientation = orientation;
        return c.createConfigurationContext(configuration);
    }
}
