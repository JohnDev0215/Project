package com.android.launcher3.touch;

import android.view.View;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DropTarget;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.folder.Folder;

/* JADX INFO: loaded from: classes4.dex */
public class ItemLongClickListener {
    public static View.OnLongClickListener INSTANCE_WORKSPACE = new View.OnLongClickListener() { // from class: com.android.launcher3.touch.-$$Lambda$ItemLongClickListener$nz9MSaglTImbNX-jBQmvpOY7s8M
        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            return ItemLongClickListener.onWorkspaceItemLongClick(view);
        }
    };
    public static View.OnLongClickListener INSTANCE_ALL_APPS = new View.OnLongClickListener() { // from class: com.android.launcher3.touch.-$$Lambda$ItemLongClickListener$w0E77iw3NhDMXITrEZo4RYOwnrg
        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            return ItemLongClickListener.onAllAppsItemLongClick(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean onWorkspaceItemLongClick(View v) {
        Launcher launcher = Launcher.getLauncher(v.getContext());
        if (!canStartDrag(launcher)) {
            return false;
        }
        if ((!launcher.isInState(LauncherState.NORMAL) && !launcher.isInState(LauncherState.OVERVIEW)) || !(v.getTag() instanceof ItemInfo)) {
            return false;
        }
        launcher.setWaitingForResult(null);
        beginDrag(v, launcher, (ItemInfo) v.getTag(), new DragOptions());
        return true;
    }

    public static void beginDrag(View v, Launcher launcher, ItemInfo info, DragOptions dragOptions) {
        Folder open;
        if (info.container >= 0 && (open = Folder.getOpen(launcher)) != null) {
            if (!open.getItemsInReadingOrder().contains(v)) {
                open.close(true);
            } else {
                open.startDrag(v, dragOptions);
                return;
            }
        }
        launcher.getWorkspace().startDrag(new CellLayout.CellInfo(v, info), dragOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean onAllAppsItemLongClick(final View v) {
        Launcher launcher = Launcher.getLauncher(v.getContext());
        if (!canStartDrag(launcher)) {
            return false;
        }
        if ((!launcher.isInState(LauncherState.ALL_APPS) && !launcher.isInState(LauncherState.OVERVIEW)) || launcher.getWorkspace().isSwitchingState()) {
            return false;
        }
        final DragController dragController = launcher.getDragController();
        dragController.addDragListener(new DragController.DragListener() { // from class: com.android.launcher3.touch.ItemLongClickListener.1
            @Override // com.android.launcher3.dragndrop.DragController.DragListener
            public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
                v.setVisibility(4);
            }

            @Override // com.android.launcher3.dragndrop.DragController.DragListener
            public void onDragEnd() {
                v.setVisibility(0);
                dragController.removeDragListener(this);
            }
        });
        DeviceProfile deviceProfile = launcher.getDeviceProfile();
        DragOptions dragOptions = new DragOptions();
        dragOptions.intrinsicIconScaleFactor = deviceProfile.allAppsIconSizePx / deviceProfile.iconSizePx;
        launcher.getWorkspace().beginDragShared(v, launcher.getAppsView(), dragOptions);
        return false;
    }

    public static boolean canStartDrag(Launcher launcher) {
        return (launcher == null || launcher.isWorkspaceLocked() || launcher.getDragController().isDragging()) ? false : true;
    }
}
