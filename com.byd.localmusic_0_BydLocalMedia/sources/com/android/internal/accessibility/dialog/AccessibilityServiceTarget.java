package com.android.internal.accessibility.dialog;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import com.android.internal.accessibility.util.ShortcutUtils;

/* JADX INFO: loaded from: classes4.dex */
class AccessibilityServiceTarget extends AccessibilityTarget {
    AccessibilityServiceTarget(Context context, int i, int i2, AccessibilityServiceInfo accessibilityServiceInfo) {
        super(context, i, i2, ShortcutUtils.isShortcutContained(context, i, accessibilityServiceInfo.getComponentName().flattenToString()), accessibilityServiceInfo.getComponentName().flattenToString(), accessibilityServiceInfo.getResolveInfo().loadLabel(context.getPackageManager()), accessibilityServiceInfo.getResolveInfo().loadIcon(context.getPackageManager()), ShortcutUtils.convertToKey(ShortcutUtils.convertToUserType(i)));
    }
}
