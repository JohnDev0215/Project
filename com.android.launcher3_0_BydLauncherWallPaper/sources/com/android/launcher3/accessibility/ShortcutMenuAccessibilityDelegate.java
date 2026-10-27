package com.android.launcher3.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.model.ModelWriter;
import com.android.launcher3.notification.NotificationMainView;
import com.android.launcher3.shortcuts.DeepShortcutView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class ShortcutMenuAccessibilityDelegate extends LauncherAccessibilityDelegate {
    private static final int DISMISS_NOTIFICATION = 2131296315;

    public ShortcutMenuAccessibilityDelegate(Launcher launcher) {
        super(launcher);
        this.mActions.put(R.id.action_dismiss_notification, new AccessibilityNodeInfo.AccessibilityAction(R.id.action_dismiss_notification, launcher.getText(R.string.action_dismiss_notification)));
    }

    @Override // com.android.launcher3.accessibility.LauncherAccessibilityDelegate
    public void addSupportedActions(View host, AccessibilityNodeInfo info, boolean fromKeyboard) {
        if (host.getParent() instanceof DeepShortcutView) {
            info.addAction(this.mActions.get(R.id.action_add_to_workspace));
        } else if ((host instanceof NotificationMainView) && ((NotificationMainView) host).canChildBeDismissed()) {
            info.addAction(this.mActions.get(R.id.action_dismiss_notification));
        }
    }

    @Override // com.android.launcher3.accessibility.LauncherAccessibilityDelegate
    public boolean performAction(View host, ItemInfo item, int action) {
        if (action == R.id.action_add_to_workspace) {
            if (!(host.getParent() instanceof DeepShortcutView)) {
                return false;
            }
            final WorkspaceItemInfo finalInfo = ((DeepShortcutView) host.getParent()).getFinalInfo();
            final int[] iArr = new int[2];
            final int iFindSpaceOnWorkspace = findSpaceOnWorkspace(item, iArr);
            this.mLauncher.getStateManager().goToState(LauncherState.NORMAL, true, new Runnable() { // from class: com.android.launcher3.accessibility.ShortcutMenuAccessibilityDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    ModelWriter modelWriter = ShortcutMenuAccessibilityDelegate.this.mLauncher.getModelWriter();
                    WorkspaceItemInfo workspaceItemInfo = finalInfo;
                    int i = iFindSpaceOnWorkspace;
                    int[] iArr2 = iArr;
                    modelWriter.addItemToDatabase(workspaceItemInfo, -100, i, iArr2[0], iArr2[1]);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(finalInfo);
                    ShortcutMenuAccessibilityDelegate.this.mLauncher.bindItems(arrayList, true);
                    AbstractFloatingView.closeAllOpenViews(ShortcutMenuAccessibilityDelegate.this.mLauncher);
                    ShortcutMenuAccessibilityDelegate.this.announceConfirmation(R.string.item_added_to_workspace);
                }
            });
            return true;
        }
        if (action != R.id.action_dismiss_notification || !(host instanceof NotificationMainView)) {
            return false;
        }
        ((NotificationMainView) host).onChildDismissed();
        announceConfirmation(R.string.notification_dismissed);
        return true;
    }
}
