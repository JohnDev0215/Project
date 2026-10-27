package org.chromium.chrome.browser.autofill;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class AutofillAccessibilityUtils {
    @CalledByNative
    public static void announce(String str) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) xz.a.getSystemService("accessibility");
        if (accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
            accessibilityEventObtain.setEventType(16384);
            accessibilityEventObtain.getText().add(str);
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }
}
