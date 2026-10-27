package com.android.launcher3.accessibility;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.launcher3.AppInfo;
import com.android.launcher3.CellLayout;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;
import com.android.launcher3.dragndrop.DragLayer;

/* JADX INFO: loaded from: classes4.dex */
public class WorkspaceAccessibilityHelper extends DragAndDropAccessibilityDelegate {
    private final int[] mTempCords;
    private final Rect mTempRect;

    public WorkspaceAccessibilityHelper(CellLayout layout) {
        super(layout);
        this.mTempRect = new Rect();
        this.mTempCords = new int[2];
    }

    @Override // com.android.launcher3.accessibility.DragAndDropAccessibilityDelegate
    protected int intersectsValidDropTarget(int id) {
        int countX = this.mView.getCountX();
        int countY = this.mView.getCountY();
        int i = id % countX;
        int i2 = id / countX;
        LauncherAccessibilityDelegate.DragInfo dragInfo = this.mDelegate.getDragInfo();
        if (dragInfo.dragType == LauncherAccessibilityDelegate.DragType.WIDGET && !this.mView.acceptsWidget()) {
            return -1;
        }
        if (dragInfo.dragType == LauncherAccessibilityDelegate.DragType.WIDGET) {
            int i3 = dragInfo.info.spanX;
            int i4 = dragInfo.info.spanY;
            for (int i5 = 0; i5 < i3; i5++) {
                for (int i6 = 0; i6 < i4; i6++) {
                    int i7 = i - i5;
                    int i8 = i2 - i6;
                    if (i7 >= 0 && i8 >= 0) {
                        boolean z = true;
                        for (int i9 = i7; i9 < i7 + i3 && z; i9++) {
                            for (int i10 = i8; i10 < i8 + i4; i10++) {
                                if (i9 >= countX || i10 >= countY || this.mView.isOccupied(i9, i10)) {
                                    z = false;
                                    break;
                                }
                            }
                        }
                        if (z) {
                            return i7 + (countX * i8);
                        }
                    }
                }
            }
            return -1;
        }
        View childAt = this.mView.getChildAt(i, i2);
        if (childAt == null || childAt == dragInfo.item) {
            return id;
        }
        if (dragInfo.dragType != LauncherAccessibilityDelegate.DragType.FOLDER) {
            ItemInfo itemInfo = (ItemInfo) childAt.getTag();
            if ((itemInfo instanceof AppInfo) || (itemInfo instanceof FolderInfo) || (itemInfo instanceof WorkspaceItemInfo)) {
                return id;
            }
        }
        return -1;
    }

    @Override // com.android.launcher3.accessibility.DragAndDropAccessibilityDelegate
    protected String getConfirmationForIconDrop(int id) {
        int countX = id % this.mView.getCountX();
        int countX2 = id / this.mView.getCountX();
        LauncherAccessibilityDelegate.DragInfo dragInfo = this.mDelegate.getDragInfo();
        View childAt = this.mView.getChildAt(countX, countX2);
        if (childAt == null || childAt == dragInfo.item) {
            return this.mContext.getString(R.string.item_moved);
        }
        ItemInfo itemInfo = (ItemInfo) childAt.getTag();
        if ((itemInfo instanceof AppInfo) || (itemInfo instanceof WorkspaceItemInfo)) {
            return this.mContext.getString(R.string.folder_created);
        }
        return itemInfo instanceof FolderInfo ? this.mContext.getString(R.string.added_to_folder) : "";
    }

    @Override // com.android.launcher3.accessibility.DragAndDropAccessibilityDelegate, androidx.customview.widget.ExploreByTouchHelper
    protected void onPopulateNodeForVirtualView(int id, AccessibilityNodeInfoCompat node) {
        super.onPopulateNodeForVirtualView(id, node);
        DragLayer dragLayer = Launcher.getLauncher(this.mView.getContext()).getDragLayer();
        int[] iArr = this.mTempCords;
        iArr[1] = 0;
        iArr[0] = 0;
        float descendantCoordRelativeToSelf = dragLayer.getDescendantCoordRelativeToSelf((View) this.mView, this.mTempCords);
        node.getBoundsInParent(this.mTempRect);
        this.mTempRect.left = this.mTempCords[0] + ((int) (r0.left * descendantCoordRelativeToSelf));
        this.mTempRect.right = this.mTempCords[0] + ((int) (r0.right * descendantCoordRelativeToSelf));
        this.mTempRect.top = this.mTempCords[1] + ((int) (r0.top * descendantCoordRelativeToSelf));
        this.mTempRect.bottom = this.mTempCords[1] + ((int) (r0.bottom * descendantCoordRelativeToSelf));
        node.setBoundsInScreen(this.mTempRect);
    }

    @Override // com.android.launcher3.accessibility.DragAndDropAccessibilityDelegate
    protected String getLocationDescriptionForIconDrop(int id) {
        int countX = id % this.mView.getCountX();
        int countX2 = id / this.mView.getCountX();
        LauncherAccessibilityDelegate.DragInfo dragInfo = this.mDelegate.getDragInfo();
        View childAt = this.mView.getChildAt(countX, countX2);
        if (childAt == null || childAt == dragInfo.item) {
            return this.mView.getItemMoveDescription(countX, countX2);
        }
        return getDescriptionForDropOver(childAt, this.mContext);
    }

    public static String getDescriptionForDropOver(View overChild, Context context) {
        ItemInfo itemInfo = (ItemInfo) overChild.getTag();
        if (itemInfo instanceof WorkspaceItemInfo) {
            return context.getString(R.string.create_folder_with, itemInfo.title);
        }
        if (!(itemInfo instanceof FolderInfo)) {
            return "";
        }
        if (TextUtils.isEmpty(itemInfo.title)) {
            WorkspaceItemInfo workspaceItemInfo = null;
            for (WorkspaceItemInfo workspaceItemInfo2 : ((FolderInfo) itemInfo).contents) {
                if (workspaceItemInfo == null || workspaceItemInfo.rank > workspaceItemInfo2.rank) {
                    workspaceItemInfo = workspaceItemInfo2;
                }
            }
            if (workspaceItemInfo != null) {
                return context.getString(R.string.add_to_folder_with_app, workspaceItemInfo.title);
            }
        }
        return context.getString(R.string.add_to_folder, itemInfo.title);
    }
}
