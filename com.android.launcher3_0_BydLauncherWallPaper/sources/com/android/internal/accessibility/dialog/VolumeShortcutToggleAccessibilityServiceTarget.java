package com.android.internal.accessibility.dialog;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.widget.Toast;
import com.android.internal.R;
import com.android.internal.accessibility.util.AccessibilityUtils;
import com.android.internal.accessibility.util.ShortcutUtils;

/* JADX INFO: loaded from: classes4.dex */
class VolumeShortcutToggleAccessibilityServiceTarget extends AccessibilityServiceTarget {
    VolumeShortcutToggleAccessibilityServiceTarget(Context context, int i, AccessibilityServiceInfo accessibilityServiceInfo) {
        super(context, i, 0, accessibilityServiceInfo);
    }

    @Override // com.android.internal.accessibility.dialog.AccessibilityTarget, com.android.internal.accessibility.dialog.OnTargetCheckedChangeListener
    public void onCheckedChanged(boolean z) {
        int shortcutType = getShortcutType();
        if (shortcutType == 0) {
            onCheckedFromAccessibilityButton(z);
        } else {
            if (shortcutType == 1) {
                super.onCheckedChanged(z);
                return;
            }
            throw new IllegalStateException("Unexpected shortcut type");
        }
    }

    private void onCheckedFromAccessibilityButton(boolean z) {
        setShortcutEnabled(z);
        AccessibilityUtils.setAccessibilityServiceState(getContext(), ComponentName.unflattenFromString(getId()), z);
        if (z) {
            return;
        }
        ShortcutUtils.optOutValueFromSettings(getContext(), 2, getId());
        Toast.makeText(getContext(), getContext().getString(R.string.accessibility_uncheck_legacy_item_warning, getLabel()), 0).show();
    }
}
