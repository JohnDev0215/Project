package com.android.car.ui.utils;

import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes7.dex */
public final class DirectManipulationHelper {
    public static final String DIRECT_MANIPULATION = "com.android.car.ui.utils.DIRECT_MANIPULATION";

    private DirectManipulationHelper() {
    }

    public static boolean enableDirectManipulationMode(View view, boolean enable) {
        int i;
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return false;
        }
        AccessibilityEvent event = AccessibilityEvent.obtain();
        event.setClassName(DIRECT_MANIPULATION);
        event.setSource(view);
        if (enable) {
            i = 32768;
        } else {
            i = 65536;
        }
        event.setEventType(i);
        accessibilityManager.sendAccessibilityEvent(event);
        return true;
    }

    public static boolean isDirectManipulation(AccessibilityEvent event) {
        return TextUtils.equals(DIRECT_MANIPULATION, event.getClassName());
    }

    public static boolean supportRotateDirectly(AccessibilityNodeInfo node) {
        return TextUtils.equals(DIRECT_MANIPULATION, node.getContentDescription());
    }

    public static void setSupportsRotateDirectly(View view, boolean enable) {
        view.setContentDescription(enable ? DIRECT_MANIPULATION : null);
    }

    @Deprecated
    public static boolean supportDirectManipulation(AccessibilityNodeInfo node) {
        return supportRotateDirectly(node);
    }

    @Deprecated
    public static void setSupportsDirectManipulation(View view, boolean enable) {
        setSupportsRotateDirectly(view, enable);
    }
}
