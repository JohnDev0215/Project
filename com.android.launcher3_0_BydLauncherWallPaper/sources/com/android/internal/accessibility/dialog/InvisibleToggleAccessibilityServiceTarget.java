package com.android.internal.accessibility.dialog;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import com.android.internal.accessibility.util.AccessibilityUtils;
import com.android.internal.accessibility.util.ShortcutUtils;

/* JADX INFO: loaded from: classes4.dex */
class InvisibleToggleAccessibilityServiceTarget extends AccessibilityServiceTarget {
    InvisibleToggleAccessibilityServiceTarget(Context context, int i, AccessibilityServiceInfo accessibilityServiceInfo) {
        super(context, i, 1, accessibilityServiceInfo);
    }

    @Override // com.android.internal.accessibility.dialog.AccessibilityTarget, com.android.internal.accessibility.dialog.OnTargetCheckedChangeListener
    public void onCheckedChanged(boolean z) {
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(getId());
        if (!isComponentIdExistingInOtherShortcut()) {
            AccessibilityUtils.setAccessibilityServiceState(getContext(), componentNameUnflattenFromString, z);
        }
        super.onCheckedChanged(z);
    }

    private boolean isComponentIdExistingInOtherShortcut() {
        int shortcutType = getShortcutType();
        if (shortcutType == 0) {
            return ShortcutUtils.isComponentIdExistingInSettings(getContext(), 2, getId());
        }
        if (shortcutType == 1) {
            return ShortcutUtils.isComponentIdExistingInSettings(getContext(), 1, getId());
        }
        throw new IllegalStateException("Unexpected shortcut type");
    }
}
