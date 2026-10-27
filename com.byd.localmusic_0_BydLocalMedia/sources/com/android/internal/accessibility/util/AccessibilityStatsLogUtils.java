package com.android.internal.accessibility.util;

import android.content.ComponentName;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.util.FrameworkStatsLog;

/* JADX INFO: loaded from: classes4.dex */
public final class AccessibilityStatsLogUtils {
    private static final int UNKNOWN_STATUS = 0;

    private static int convertToLoggingServiceStatus(boolean z) {
        return z ? 1 : 2;
    }

    private static int convertToLoggingShortcutType(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 2;
        }
        return 1;
    }

    private AccessibilityStatsLogUtils() {
    }

    public static void logAccessibilityShortcutActivated(ComponentName componentName, int i) {
        logAccessibilityShortcutActivated(componentName, i, 0);
    }

    public static void logAccessibilityShortcutActivated(ComponentName componentName, int i, boolean z) {
        logAccessibilityShortcutActivated(componentName, i, convertToLoggingServiceStatus(z));
    }

    private static void logAccessibilityShortcutActivated(ComponentName componentName, int i, int i2) {
        FrameworkStatsLog.write(266, componentName.flattenToString(), convertToLoggingShortcutType(i), i2);
    }

    public static void logMagnificationTripleTap(boolean z) {
        FrameworkStatsLog.write(266, AccessibilityShortcutController.MAGNIFICATION_COMPONENT_NAME.flattenToString(), 3, convertToLoggingServiceStatus(z));
    }

    public static void logAccessibilityButtonLongPressStatus(ComponentName componentName) {
        FrameworkStatsLog.write(266, componentName.flattenToString(), 4, 0);
    }
}
