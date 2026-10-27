package com.android.internal.accessibility.dialog;

import android.accessibilityservice.AccessibilityShortcutInfo;
import android.content.Context;
import com.android.internal.accessibility.util.ShortcutUtils;

/* JADX INFO: loaded from: classes4.dex */
class AccessibilityActivityTarget extends AccessibilityTarget {
    AccessibilityActivityTarget(Context context, int i, AccessibilityShortcutInfo accessibilityShortcutInfo) {
        super(context, i, 3, ShortcutUtils.isShortcutContained(context, i, accessibilityShortcutInfo.getComponentName().flattenToString()), accessibilityShortcutInfo.getComponentName().flattenToString(), accessibilityShortcutInfo.getActivityInfo().loadLabel(context.getPackageManager()), accessibilityShortcutInfo.getActivityInfo().loadIcon(context.getPackageManager()), ShortcutUtils.convertToKey(ShortcutUtils.convertToUserType(i)));
    }
}
