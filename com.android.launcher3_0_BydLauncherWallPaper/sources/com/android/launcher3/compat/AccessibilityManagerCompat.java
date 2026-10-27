package com.android.launcher3.compat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.android.launcher3.Utilities;
import com.android.launcher3.testing.TestProtocol;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class AccessibilityManagerCompat {
    public static boolean isAccessibilityEnabled(Context context) {
        return getManager(context).isEnabled();
    }

    public static boolean isObservedEventType(Context context, int eventType) {
        return isAccessibilityEnabled(context);
    }

    public static void sendCustomAccessibilityEvent(View target, int type, String text) {
        if (isObservedEventType(target.getContext(), type)) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(type);
            target.onInitializeAccessibilityEvent(accessibilityEventObtain);
            accessibilityEventObtain.getText().add(text);
            getManager(target.getContext()).sendAccessibilityEvent(accessibilityEventObtain);
        }
    }

    private static AccessibilityManager getManager(Context context) {
        return (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
    }

    public static void sendStateEventToTest(Context context, int stateOrdinal) {
        if (TestProtocol.sDebugTracing) {
            Log.e(TestProtocol.NO_ALLAPPS_EVENT_TAG, "sendStateEventToTest");
        }
        AccessibilityManager accessibilityManagerForTest = getAccessibilityManagerForTest(context);
        if (accessibilityManagerForTest == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("state", stateOrdinal);
        sendEventToTest(accessibilityManagerForTest, TestProtocol.SWITCHED_TO_STATE_MESSAGE, bundle);
    }

    public static void sendScrollFinishedEventToTest(Context context) {
        AccessibilityManager accessibilityManagerForTest = getAccessibilityManagerForTest(context);
        if (accessibilityManagerForTest == null) {
            return;
        }
        sendEventToTest(accessibilityManagerForTest, TestProtocol.SCROLL_FINISHED_MESSAGE, null);
    }

    public static void sendPauseDetectedEventToTest(Context context) {
        AccessibilityManager accessibilityManagerForTest = getAccessibilityManagerForTest(context);
        if (accessibilityManagerForTest == null) {
            return;
        }
        sendEventToTest(accessibilityManagerForTest, TestProtocol.PAUSE_DETECTED_MESSAGE, null);
    }

    private static void sendEventToTest(AccessibilityManager accessibilityManager, String eventTag, Bundle data) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(16384);
        accessibilityEventObtain.setClassName(eventTag);
        accessibilityEventObtain.setParcelableData(data);
        accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
    }

    private static AccessibilityManager getAccessibilityManagerForTest(Context context) {
        if (!Utilities.IS_RUNNING_IN_TEST_HARNESS) {
            return null;
        }
        AccessibilityManager manager = getManager(context);
        if (manager.isEnabled()) {
            return manager;
        }
        return null;
    }

    public static boolean processTestRequest(Context context, String eventTag, int action, Bundle request, Consumer<Bundle> responseFiller) {
        AccessibilityManager accessibilityManagerForTest = getAccessibilityManagerForTest(context);
        if (accessibilityManagerForTest == null || action != 2097152 || !eventTag.equals(request.getCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE"))) {
            return false;
        }
        Bundle bundle = new Bundle();
        responseFiller.accept(bundle);
        sendEventToTest(accessibilityManagerForTest, eventTag + TestProtocol.RESPONSE_MESSAGE_POSTFIX, bundle);
        return true;
    }

    public static int getRecommendedTimeoutMillis(Context context, int originalTimeout, int flags) {
        return Utilities.ATLEAST_Q ? getManager(context).getRecommendedTimeoutMillis(originalTimeout, flags) : originalTimeout;
    }
}
