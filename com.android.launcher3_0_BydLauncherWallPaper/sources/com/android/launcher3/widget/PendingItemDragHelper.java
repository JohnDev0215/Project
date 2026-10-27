package com.android.launcher3.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RemoteViews;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DragSource;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.PendingAddItemInfo;
import com.android.launcher3.R;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.dragndrop.LivePreviewWidgetCell;
import com.android.launcher3.graphics.DragPreviewProvider;
import com.android.launcher3.icons.LauncherIcons;

/* JADX INFO: loaded from: classes4.dex */
public class PendingItemDragHelper extends DragPreviewProvider {
    private static final float MAX_WIDGET_SCALE = 1.25f;
    private final PendingAddItemInfo mAddInfo;
    private int[] mEstimatedCellSize;
    private RemoteViews mPreview;

    public PendingItemDragHelper(View view) {
        super(view);
        this.mAddInfo = (PendingAddItemInfo) view.getTag();
    }

    public void setPreview(RemoteViews preview) {
        this.mPreview = preview;
    }

    public void startDrag(Rect previewBounds, int previewBitmapWidth, int previewViewWidth, Point screenPos, DragSource source, DragOptions options) {
        float width;
        Bitmap bitmap;
        Rect rect;
        Point point;
        Launcher launcher = Launcher.getLauncher(this.mView.getContext());
        LauncherAppState launcherAppState = LauncherAppState.getInstance(launcher);
        int[] iArrEstimateItemSize = launcher.getWorkspace().estimateItemSize(this.mAddInfo);
        this.mEstimatedCellSize = iArrEstimateItemSize;
        PendingAddItemInfo pendingAddItemInfo = this.mAddInfo;
        if (pendingAddItemInfo instanceof PendingAddWidgetInfo) {
            PendingAddWidgetInfo pendingAddWidgetInfo = (PendingAddWidgetInfo) pendingAddItemInfo;
            int iMin = Math.min((int) (previewBitmapWidth * MAX_WIDGET_SCALE), iArrEstimateItemSize[0]);
            int[] iArr = new int[1];
            RemoteViews remoteViews = this.mPreview;
            Bitmap bitmapGenerateFromRemoteViews = remoteViews != null ? LivePreviewWidgetCell.generateFromRemoteViews(launcher, remoteViews, pendingAddWidgetInfo.info, iMin, iArr) : null;
            if (bitmapGenerateFromRemoteViews == null) {
                bitmapGenerateFromRemoteViews = launcherAppState.getWidgetCache().generateWidgetPreview(launcher, pendingAddWidgetInfo.info, iMin, null, iArr);
            }
            if (iArr[0] < previewBitmapWidth) {
                int i = (previewBitmapWidth - iArr[0]) / 2;
                if (previewBitmapWidth > previewViewWidth) {
                    i = (i * previewViewWidth) / previewBitmapWidth;
                }
                previewBounds.left += i;
                previewBounds.right -= i;
            }
            launcher.getDragController().addDragListener(new WidgetHostViewLoader(launcher, this.mView));
            width = previewBounds.width() / bitmapGenerateFromRemoteViews.getWidth();
            bitmap = bitmapGenerateFromRemoteViews;
            point = null;
            rect = null;
        } else {
            Drawable fullResIcon = ((PendingAddShortcutInfo) pendingAddItemInfo).activityInfo.getFullResIcon(launcherAppState.getIconCache());
            LauncherIcons launcherIconsObtain = LauncherIcons.obtain(launcher);
            Bitmap bitmapCreateScaledBitmapWithoutShadow = launcherIconsObtain.createScaledBitmapWithoutShadow(fullResIcon, 0);
            launcherIconsObtain.recycle();
            Point point2 = new Point(this.previewPadding / 2, this.previewPadding / 2);
            DeviceProfile deviceProfile = launcher.getDeviceProfile();
            int i2 = deviceProfile.iconSizePx;
            int dimensionPixelSize = launcher.getResources().getDimensionPixelSize(R.dimen.widget_preview_shortcut_padding);
            previewBounds.left += dimensionPixelSize;
            previewBounds.top += dimensionPixelSize;
            Rect rect2 = new Rect();
            rect2.left = (this.mEstimatedCellSize[0] - i2) / 2;
            rect2.right = rect2.left + i2;
            rect2.top = (((this.mEstimatedCellSize[1] - i2) - deviceProfile.iconTextSizePx) - deviceProfile.iconDrawablePaddingPx) / 2;
            rect2.bottom = rect2.top + i2;
            width = launcher.getDeviceProfile().iconSizePx / bitmapCreateScaledBitmapWithoutShadow.getWidth();
            bitmap = bitmapCreateScaledBitmapWithoutShadow;
            rect = rect2;
            point = point2;
        }
        launcher.getWorkspace().prepareDragWithProvider(this);
        launcher.getDragController().startDrag(bitmap, screenPos.x + previewBounds.left + ((int) (((bitmap.getWidth() * width) - bitmap.getWidth()) / 2.0f)), screenPos.y + previewBounds.top + ((int) (((bitmap.getHeight() * width) - bitmap.getHeight()) / 2.0f)), source, this.mAddInfo, point, rect, width, width, options);
    }

    @Override // com.android.launcher3.graphics.DragPreviewProvider
    protected Bitmap convertPreviewToAlphaBitmap(Bitmap preview) {
        int[] iArr;
        if ((this.mAddInfo instanceof PendingAddShortcutInfo) || (iArr = this.mEstimatedCellSize) == null) {
            return super.convertPreviewToAlphaBitmap(preview);
        }
        int i = iArr[0];
        int i2 = iArr[1];
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ALPHA_8);
        Rect rect = new Rect(0, 0, preview.getWidth(), preview.getHeight());
        float fMin = Math.min((i - this.blurSizeOutline) / preview.getWidth(), (i2 - this.blurSizeOutline) / preview.getHeight());
        int width = (int) (preview.getWidth() * fMin);
        int height = (int) (fMin * preview.getHeight());
        Rect rect2 = new Rect(0, 0, width, height);
        rect2.offset((i - width) / 2, (i2 - height) / 2);
        new Canvas(bitmapCreateBitmap).drawBitmap(preview, rect, rect2, new Paint(2));
        return bitmapCreateBitmap;
    }
}
