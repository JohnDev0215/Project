package com.amazon.mp3.util;

import android.content.Context;
import android.os.Handler;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class AccessibilityUtil {
    private static AccessibilityListener accessibilityListener;
    private static Handler announcementHandler = new Handler();

    public static boolean isAccessibilityEnabled(final Context context) {
        if (accessibilityListener == null) {
            accessibilityListener = new AccessibilityListener(context);
        }
        return accessibilityListener.getIsAccessibilityEnabled(context);
    }

    public static void announceForAccessibility(@Nullable final Context context, @Nullable final String message) {
        AccessibilityManager accessibilityManager;
        if (!isAccessibilityEnabled(context) || context == null || (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(16384);
        accessibilityEventObtain.getText().add(message);
        if (accessibilityManager.isEnabled()) {
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }

    public static void announceDelayForAccessibility(final Context context, final String message) {
        announceDelayForAccessibility(context, message, 500L);
    }

    public static void announceDelayForAccessibility(final Context context, final String message, long delayMillis) {
        if (isAccessibilityEnabled(context)) {
            announcementHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.util.AccessibilityUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    AccessibilityUtil.announceForAccessibility(context, message);
                }
            }, delayMillis);
        }
    }

    private static final class AccessibilityListener {
        private Boolean isAccessibilityEnabled;
        private final AccessibilityManager.AccessibilityStateChangeListener listener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: com.amazon.mp3.util.AccessibilityUtil.AccessibilityListener.1
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public void onAccessibilityStateChanged(boolean enabled) {
                Log.debug("ACCESSIBILITY_CHANGED", "enabled: " + enabled);
                AccessibilityListener.this.setIsAccessibilityEnabled(Boolean.valueOf(enabled));
            }
        };

        public AccessibilityListener(final Context context) {
            init(context);
        }

        private void init(final Context context) {
            AccessibilityManager accessibilityManager;
            if (context == null || (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) == null) {
                return;
            }
            setIsAccessibilityEnabled(Boolean.valueOf(accessibilityManager.isEnabled()));
            accessibilityManager.removeAccessibilityStateChangeListener(this.listener);
            accessibilityManager.addAccessibilityStateChangeListener(this.listener);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized boolean getIsAccessibilityEnabled(final android.content.Context r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                java.lang.Boolean r0 = r1.isAccessibilityEnabled     // Catch: java.lang.Throwable -> L17
                if (r0 != 0) goto L8
                r1.init(r2)     // Catch: java.lang.Throwable -> L17
            L8:
                java.lang.Boolean r2 = r1.isAccessibilityEnabled     // Catch: java.lang.Throwable -> L17
                if (r2 == 0) goto L14
                boolean r2 = r2.booleanValue()     // Catch: java.lang.Throwable -> L17
                if (r2 == 0) goto L14
                r2 = 1
                goto L15
            L14:
                r2 = 0
            L15:
                monitor-exit(r1)
                return r2
            L17:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.AccessibilityUtil.AccessibilityListener.getIsAccessibilityEnabled(android.content.Context):boolean");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void setIsAccessibilityEnabled(final Boolean isAccessibilityEnabled) {
            this.isAccessibilityEnabled = isAccessibilityEnabled;
        }
    }
}
