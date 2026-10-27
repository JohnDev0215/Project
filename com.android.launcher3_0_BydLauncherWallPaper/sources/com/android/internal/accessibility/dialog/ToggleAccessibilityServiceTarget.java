package com.android.internal.accessibility.dialog;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import com.android.internal.R;
import com.android.internal.accessibility.dialog.TargetAdapter;
import com.android.internal.accessibility.util.AccessibilityUtils;

/* JADX INFO: loaded from: classes4.dex */
class ToggleAccessibilityServiceTarget extends AccessibilityServiceTarget {
    ToggleAccessibilityServiceTarget(Context context, int i, AccessibilityServiceInfo accessibilityServiceInfo) {
        super(context, i, 2, accessibilityServiceInfo);
    }

    @Override // com.android.internal.accessibility.dialog.AccessibilityTarget, com.android.internal.accessibility.dialog.TargetOperations
    public void updateActionItem(TargetAdapter.ViewHolder viewHolder, int i) {
        super.updateActionItem(viewHolder, i);
        viewHolder.mStatusView.setVisibility(i == 1 ? 8 : 0);
        viewHolder.mStatusView.setText(getContext().getString(AccessibilityUtils.isAccessibilityServiceEnabled(getContext(), getId()) ? R.string.accessibility_shortcut_menu_item_status_on : R.string.accessibility_shortcut_menu_item_status_off));
    }
}
