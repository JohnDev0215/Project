package com.android.launcher3;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.CellLayout;
import com.android.launcher3.folder.Folder;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.touch.ItemLongClickListener;

/* JADX INFO: loaded from: classes4.dex */
public interface WorkspaceLayoutManager {
    public static final int EXTRA_EMPTY_SCREEN_ID = -201;
    public static final int FIRST_SCREEN_ID = 0;
    public static final String TAG = "Launcher.Workspace";

    Hotseat getHotseat();

    CellLayout getScreenWithId(int screenId);

    default void onAddDropTarget(DropTarget target) {
    }

    default void addInScreenFromBind(View child, ItemInfo info) {
        int i;
        int cellYFromOrder;
        int i2 = info.cellX;
        int i3 = info.cellY;
        if (info.container == -101) {
            int i4 = info.screenId;
            int cellXFromOrder = getHotseat().getCellXFromOrder(i4);
            cellYFromOrder = getHotseat().getCellYFromOrder(i4);
            i = cellXFromOrder;
        } else {
            i = i2;
            cellYFromOrder = i3;
        }
        addInScreen(child, info.container, info.screenId, i, cellYFromOrder, info.spanX, info.spanY);
    }

    default void addInScreen(View child, ItemInfo info) {
        addInScreen(child, info.container, info.screenId, info.cellX, info.cellY, info.spanX, info.spanY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void addInScreen(View child, int container, int screenId, int x, int y, int spanX, int spanY) {
        CellLayout screenWithId;
        CellLayout.LayoutParams layoutParams;
        if (container == -100 && getScreenWithId(screenId) == null) {
            Log.e(TAG, "Skipping child, screenId " + screenId + " not found");
            new Throwable().printStackTrace();
            return;
        }
        if (screenId == -201) {
            throw new RuntimeException("Screen id should not be EXTRA_EMPTY_SCREEN_ID");
        }
        if (container == -101) {
            screenWithId = getHotseat();
            if (child instanceof FolderIcon) {
                ((FolderIcon) child).setTextVisible(false);
            }
        } else {
            if (child instanceof FolderIcon) {
                ((FolderIcon) child).setTextVisible(true);
            }
            screenWithId = getScreenWithId(screenId);
        }
        ViewGroup.LayoutParams layoutParams2 = child.getLayoutParams();
        if (layoutParams2 == null || !(layoutParams2 instanceof CellLayout.LayoutParams)) {
            layoutParams = new CellLayout.LayoutParams(x, y, spanX, spanY);
        } else {
            layoutParams = (CellLayout.LayoutParams) layoutParams2;
            layoutParams.cellX = x;
            layoutParams.cellY = y;
            layoutParams.cellHSpan = spanX;
            layoutParams.cellVSpan = spanY;
        }
        if (spanX < 0 && spanY < 0) {
            layoutParams.isLockedToGrid = false;
        }
        if (!screenWithId.addViewToCellLayout(child, -1, ((ItemInfo) child.getTag()).getViewId(), layoutParams, !(child instanceof Folder))) {
            Log.e(TAG, "Failed to add to item at (" + layoutParams.cellX + SmsManager.REGEX_PREFIX_DELIMITER + layoutParams.cellY + ") to CellLayout");
        }
        child.setHapticFeedbackEnabled(false);
        child.setOnLongClickListener(ItemLongClickListener.INSTANCE_WORKSPACE);
        if (child instanceof DropTarget) {
            onAddDropTarget((DropTarget) child);
        }
    }
}
