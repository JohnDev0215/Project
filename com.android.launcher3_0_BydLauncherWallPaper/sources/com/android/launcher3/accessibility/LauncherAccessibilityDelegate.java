package com.android.launcher3.accessibility;

import android.app.AlertDialog;
import android.appwidget.AppWidgetProviderInfo;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.launcher3.AppInfo;
import com.android.launcher3.AppWidgetResizeFrame;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.ButtonDropTarget;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DropTarget;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherState;
import com.android.launcher3.PendingAddItemInfo;
import com.android.launcher3.R;
import com.android.launcher3.Workspace;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.folder.Folder;
import com.android.launcher3.model.ModelWriter;
import com.android.launcher3.notification.NotificationListener;
import com.android.launcher3.popup.PopupContainerWithArrow;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAccessibilityDelegate extends View.AccessibilityDelegate implements DragController.DragListener {
    protected static final int ADD_TO_WORKSPACE = 2131296304;
    public static final int DEEP_SHORTCUTS = 2131296314;
    protected static final int MOVE = 2131296323;
    protected static final int MOVE_TO_WORKSPACE = 2131296326;
    public static final int RECONFIGURE = 2131296327;
    public static final int REMOVE = 2131296329;
    protected static final int RESIZE = 2131296330;
    public static final int SHORTCUTS_AND_NOTIFICATIONS = 2131296331;
    private static final String TAG = "LauncherAccessibilityDelegate";
    public static final int UNINSTALL = 2131296333;
    protected final SparseArray<AccessibilityNodeInfo.AccessibilityAction> mActions;
    private DragInfo mDragInfo;
    final Launcher mLauncher;

    public static class DragInfo {
        public DragType dragType;
        public ItemInfo info;
        public View item;
    }

    public enum DragType {
        ICON,
        FOLDER,
        WIDGET
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
    }

    public LauncherAccessibilityDelegate(Launcher launcher) {
        SparseArray<AccessibilityNodeInfo.AccessibilityAction> sparseArray = new SparseArray<>();
        this.mActions = sparseArray;
        this.mDragInfo = null;
        this.mLauncher = launcher;
        sparseArray.put(R.id.action_remove, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_remove, launcher.getText(R.string.remove_drop_target_label)));
        sparseArray.put(R.id.action_uninstall, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_uninstall, launcher.getText(R.string.uninstall_drop_target_label)));
        sparseArray.put(R.id.action_reconfigure, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_reconfigure, launcher.getText(R.string.gadget_setup_text)));
        sparseArray.put(R.id.action_add_to_workspace, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_add_to_workspace, launcher.getText(R.string.action_add_to_workspace)));
        sparseArray.put(R.id.action_move, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move, launcher.getText(R.string.action_move)));
        sparseArray.put(R.id.action_move_to_workspace, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_to_workspace, launcher.getText(R.string.action_move_to_workspace)));
        sparseArray.put(R.id.action_resize, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_resize, launcher.getText(R.string.action_resize)));
        sparseArray.put(R.id.action_deep_shortcuts, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_deep_shortcuts, launcher.getText(R.string.action_deep_shortcut)));
        sparseArray.put(R.id.action_shortcuts_and_notifications, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_deep_shortcuts, launcher.getText(R.string.shortcuts_menu_with_notifications_description)));
    }

    public void addAccessibilityAction(int action, int actionLabel) {
        this.mActions.put(action, new AccessibilityNodeInfo.AccessibilityAction(action, this.mLauncher.getText(actionLabel)));
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(host, info);
        addSupportedActions(host, info, false);
    }

    public void addSupportedActions(View host, AccessibilityNodeInfo info, boolean fromKeyboard) {
        if (host.getTag() instanceof ItemInfo) {
            ItemInfo itemInfo = (ItemInfo) host.getTag();
            if (!fromKeyboard && DeepShortcutManager.supportsShortcuts(itemInfo)) {
                info.addAction(this.mActions.get(NotificationListener.getInstanceIfConnected() != null ? R.id.action_shortcuts_and_notifications : R.id.action_deep_shortcuts));
            }
            for (ButtonDropTarget buttonDropTarget : this.mLauncher.getDropTargetBar().getDropTargets()) {
                if (buttonDropTarget.supportsAccessibilityDrop(itemInfo, host)) {
                    info.addAction(this.mActions.get(buttonDropTarget.getAccessibilityAction()));
                }
            }
            if (!fromKeyboard && itemSupportsAccessibleDrag(itemInfo)) {
                info.addAction(this.mActions.get(R.id.action_move));
                if (itemInfo.container >= 0) {
                    info.addAction(this.mActions.get(R.id.action_move_to_workspace));
                } else if ((itemInfo instanceof LauncherAppWidgetInfo) && !getSupportedResizeActions(host, (LauncherAppWidgetInfo) itemInfo).isEmpty()) {
                    info.addAction(this.mActions.get(R.id.action_resize));
                }
            }
            if ((itemInfo instanceof AppInfo) || (itemInfo instanceof PendingAddItemInfo)) {
                info.addAction(this.mActions.get(R.id.action_add_to_workspace));
            }
        }
    }

    private boolean itemSupportsAccessibleDrag(ItemInfo item) {
        return item instanceof WorkspaceItemInfo ? item.screenId >= 0 : (item instanceof LauncherAppWidgetInfo) || (item instanceof FolderInfo);
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        if ((host.getTag() instanceof ItemInfo) && performAction(host, (ItemInfo) host.getTag(), action)) {
            return true;
        }
        return super.performAccessibilityAction(host, action, args);
    }

    public boolean performAction(final View host, final ItemInfo item, int action) {
        if (action == R.id.action_move) {
            beginAccessibleDrag(host, item);
        } else {
            if (action == R.id.action_add_to_workspace) {
                final int[] iArr = new int[2];
                final int iFindSpaceOnWorkspace = findSpaceOnWorkspace(item, iArr);
                this.mLauncher.getStateManager().goToState(LauncherState.NORMAL, true, new Runnable() { // from class: com.android.launcher3.accessibility.LauncherAccessibilityDelegate.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ItemInfo itemInfo = item;
                        if (itemInfo instanceof AppInfo) {
                            WorkspaceItemInfo workspaceItemInfoMakeWorkspaceItem = ((AppInfo) itemInfo).makeWorkspaceItem();
                            ModelWriter modelWriter = LauncherAccessibilityDelegate.this.mLauncher.getModelWriter();
                            int i = iFindSpaceOnWorkspace;
                            int[] iArr2 = iArr;
                            modelWriter.addItemToDatabase(workspaceItemInfoMakeWorkspaceItem, -100, i, iArr2[0], iArr2[1]);
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(workspaceItemInfoMakeWorkspaceItem);
                            LauncherAccessibilityDelegate.this.mLauncher.bindItems(arrayList, true);
                        } else if (itemInfo instanceof PendingAddItemInfo) {
                            PendingAddItemInfo pendingAddItemInfo = (PendingAddItemInfo) itemInfo;
                            Workspace workspace = LauncherAccessibilityDelegate.this.mLauncher.getWorkspace();
                            workspace.snapToPage(workspace.getPageIndexForScreenId(iFindSpaceOnWorkspace));
                            LauncherAccessibilityDelegate.this.mLauncher.addPendingItem(pendingAddItemInfo, -100, iFindSpaceOnWorkspace, iArr, pendingAddItemInfo.spanX, pendingAddItemInfo.spanY);
                        }
                        LauncherAccessibilityDelegate.this.announceConfirmation(R.string.item_added_to_workspace);
                    }
                });
                return true;
            }
            if (action == R.id.action_move_to_workspace) {
                Folder open = Folder.getOpen(this.mLauncher);
                open.close(true);
                WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) item;
                open.getInfo().remove(workspaceItemInfo, false);
                int[] iArr2 = new int[2];
                this.mLauncher.getModelWriter().moveItemInDatabase(workspaceItemInfo, -100, findSpaceOnWorkspace(item, iArr2), iArr2[0], iArr2[1]);
                new Handler().post(new Runnable() { // from class: com.android.launcher3.accessibility.LauncherAccessibilityDelegate.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(item);
                        LauncherAccessibilityDelegate.this.mLauncher.bindItems(arrayList, true);
                        LauncherAccessibilityDelegate.this.announceConfirmation(R.string.item_moved);
                    }
                });
            } else {
                if (action == R.id.action_resize) {
                    final LauncherAppWidgetInfo launcherAppWidgetInfo = (LauncherAppWidgetInfo) item;
                    final IntArray supportedResizeActions = getSupportedResizeActions(host, launcherAppWidgetInfo);
                    CharSequence[] charSequenceArr = new CharSequence[supportedResizeActions.size()];
                    for (int i = 0; i < supportedResizeActions.size(); i++) {
                        charSequenceArr[i] = this.mLauncher.getText(supportedResizeActions.get(i));
                    }
                    new AlertDialog.Builder(this.mLauncher).setTitle(R.string.action_resize).setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.android.launcher3.accessibility.LauncherAccessibilityDelegate.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            LauncherAccessibilityDelegate.this.performResizeAction(supportedResizeActions.get(which), host, launcherAppWidgetInfo);
                            dialog.dismiss();
                        }
                    }).show();
                    return true;
                }
                if (action == R.id.action_deep_shortcuts) {
                    return PopupContainerWithArrow.showForIcon((BubbleTextView) host) != null;
                }
                for (ButtonDropTarget buttonDropTarget : this.mLauncher.getDropTargetBar().getDropTargets()) {
                    if (buttonDropTarget.supportsAccessibilityDrop(item, host) && action == buttonDropTarget.getAccessibilityAction()) {
                        buttonDropTarget.onAccessibilityDrop(host, item);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private IntArray getSupportedResizeActions(View host, LauncherAppWidgetInfo info) {
        IntArray intArray = new IntArray();
        AppWidgetProviderInfo appWidgetInfo = ((LauncherAppWidgetHostView) host).getAppWidgetInfo();
        if (appWidgetInfo == null) {
            return intArray;
        }
        CellLayout cellLayout = (CellLayout) host.getParent().getParent();
        if ((appWidgetInfo.resizeMode & 1) != 0) {
            if (cellLayout.isRegionVacant(info.cellX + info.spanX, info.cellY, 1, info.spanY) || cellLayout.isRegionVacant(info.cellX - 1, info.cellY, 1, info.spanY)) {
                intArray.add(R.string.action_increase_width);
            }
            if (info.spanX > info.minSpanX && info.spanX > 1) {
                intArray.add(R.string.action_decrease_width);
            }
        }
        if ((appWidgetInfo.resizeMode & 2) != 0) {
            if (cellLayout.isRegionVacant(info.cellX, info.cellY + info.spanY, info.spanX, 1) || cellLayout.isRegionVacant(info.cellX, info.cellY - 1, info.spanX, 1)) {
                intArray.add(R.string.action_increase_height);
            }
            if (info.spanY > info.minSpanY && info.spanY > 1) {
                intArray.add(R.string.action_decrease_height);
            }
        }
        return intArray;
    }

    void performResizeAction(int action, View host, LauncherAppWidgetInfo info) {
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) host.getLayoutParams();
        CellLayout cellLayout = (CellLayout) host.getParent().getParent();
        cellLayout.markCellsAsUnoccupiedForView(host);
        if (action == R.string.action_increase_width) {
            if ((host.getLayoutDirection() == 1 && cellLayout.isRegionVacant(info.cellX - 1, info.cellY, 1, info.spanY)) || !cellLayout.isRegionVacant(info.cellX + info.spanX, info.cellY, 1, info.spanY)) {
                layoutParams.cellX--;
                info.cellX--;
            }
            layoutParams.cellHSpan++;
            info.spanX++;
        } else if (action == R.string.action_decrease_width) {
            layoutParams.cellHSpan--;
            info.spanX--;
        } else if (action == R.string.action_increase_height) {
            if (!cellLayout.isRegionVacant(info.cellX, info.cellY + info.spanY, info.spanX, 1)) {
                layoutParams.cellY--;
                info.cellY--;
            }
            layoutParams.cellVSpan++;
            info.spanY++;
        } else if (action == R.string.action_decrease_height) {
            layoutParams.cellVSpan--;
            info.spanY--;
        }
        cellLayout.markCellsAsOccupiedForView(host);
        Rect rect = new Rect();
        AppWidgetResizeFrame.getWidgetSizeRanges(this.mLauncher, info.spanX, info.spanY, rect);
        ((LauncherAppWidgetHostView) host).updateAppWidgetSize(null, rect.left, rect.top, rect.right, rect.bottom);
        host.requestLayout();
        this.mLauncher.getModelWriter().updateItemInDatabase(info);
        announceConfirmation(this.mLauncher.getString(R.string.widget_resized, Integer.valueOf(info.spanX), Integer.valueOf(info.spanY)));
    }

    void announceConfirmation(int resId) {
        announceConfirmation(this.mLauncher.getResources().getString(resId));
    }

    void announceConfirmation(String confirmation) {
        this.mLauncher.getDragLayer().announceForAccessibility(confirmation);
    }

    public boolean isInAccessibleDrag() {
        return this.mDragInfo != null;
    }

    public DragInfo getDragInfo() {
        return this.mDragInfo;
    }

    public void handleAccessibleDrop(View clickedTarget, Rect dropLocation, String confirmation) {
        if (isInAccessibleDrag()) {
            int[] iArr = new int[2];
            if (dropLocation == null) {
                iArr[0] = clickedTarget.getWidth() / 2;
                iArr[1] = clickedTarget.getHeight() / 2;
            } else {
                iArr[0] = dropLocation.centerX();
                iArr[1] = dropLocation.centerY();
            }
            this.mLauncher.getDragLayer().getDescendantCoordRelativeToSelf(clickedTarget, iArr);
            this.mLauncher.getDragController().completeAccessibleDrag(iArr);
            if (TextUtils.isEmpty(confirmation)) {
                return;
            }
            announceConfirmation(confirmation);
        }
    }

    public void beginAccessibleDrag(View item, ItemInfo info) {
        DragInfo dragInfo = new DragInfo();
        this.mDragInfo = dragInfo;
        dragInfo.info = info;
        this.mDragInfo.item = item;
        this.mDragInfo.dragType = DragType.ICON;
        if (info instanceof FolderInfo) {
            this.mDragInfo.dragType = DragType.FOLDER;
        } else if (info instanceof LauncherAppWidgetInfo) {
            this.mDragInfo.dragType = DragType.WIDGET;
        }
        Rect rect = new Rect();
        this.mLauncher.getDragLayer().getDescendantRectRelativeToSelf(item, rect);
        this.mLauncher.getDragController().prepareAccessibleDrag(rect.centerX(), rect.centerY());
        this.mLauncher.getDragController().addDragListener(this);
        DragOptions dragOptions = new DragOptions();
        dragOptions.isAccessibleDrag = true;
        ItemLongClickListener.beginDrag(item, this.mLauncher, info, dragOptions);
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragEnd() {
        this.mLauncher.getDragController().removeDragListener(this);
        this.mDragInfo = null;
    }

    protected int findSpaceOnWorkspace(ItemInfo info, int[] outCoordinates) {
        Workspace workspace = this.mLauncher.getWorkspace();
        IntArray screenOrder = workspace.getScreenOrder();
        int currentPage = workspace.getCurrentPage();
        int i = screenOrder.get(currentPage);
        boolean zFindCellForSpan = ((CellLayout) workspace.getPageAt(currentPage)).findCellForSpan(outCoordinates, info.spanX, info.spanY);
        for (int i2 = 0; !zFindCellForSpan && i2 < screenOrder.size(); i2++) {
            i = screenOrder.get(i2);
            zFindCellForSpan = ((CellLayout) workspace.getPageAt(i2)).findCellForSpan(outCoordinates, info.spanX, info.spanY);
        }
        if (zFindCellForSpan) {
            return i;
        }
        workspace.addExtraEmptyScreen();
        int iCommitExtraEmptyScreen = workspace.commitExtraEmptyScreen();
        if (!workspace.getScreenWithId(iCommitExtraEmptyScreen).findCellForSpan(outCoordinates, info.spanX, info.spanY)) {
            Log.wtf(TAG, "Not enough space on an empty screen");
        }
        return iCommitExtraEmptyScreen;
    }
}
