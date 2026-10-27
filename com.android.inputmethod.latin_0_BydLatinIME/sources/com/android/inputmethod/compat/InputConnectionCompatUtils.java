package com.android.inputmethod.compat;

import android.view.inputmethod.InputConnection;
import com.android.inputmethod.compat.CompatUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class InputConnectionCompatUtils {
    private static int CURSOR_UPDATE_IMMEDIATE;
    private static int CURSOR_UPDATE_MONITOR;
    private static final CompatUtils.ClassWrapper sInputConnectionType;
    private static final CompatUtils.ToBooleanMethodWrapper sRequestCursorUpdatesMethod;

    static {
        CompatUtils.ClassWrapper classWrapper = new CompatUtils.ClassWrapper(InputConnection.class);
        sInputConnectionType = classWrapper;
        sRequestCursorUpdatesMethod = classWrapper.getPrimitiveMethod("requestCursorUpdates", false, Integer.TYPE);
        CURSOR_UPDATE_IMMEDIATE = 1;
        CURSOR_UPDATE_MONITOR = 2;
    }

    public static boolean isRequestCursorUpdatesAvailable() {
        return sRequestCursorUpdatesMethod != null;
    }

    private static boolean requestCursorUpdatesImpl(final InputConnection inputConnection, final int cursorUpdateMode) {
        if (isRequestCursorUpdatesAvailable()) {
            return sRequestCursorUpdatesMethod.invoke(inputConnection, Integer.valueOf(cursorUpdateMode));
        }
        return false;
    }

    public static boolean requestCursorUpdates(final InputConnection inputConnection, final boolean enableMonitor, final boolean requestImmediateCallback) {
        return requestCursorUpdatesImpl(inputConnection, (enableMonitor ? CURSOR_UPDATE_MONITOR : 0) | (requestImmediateCallback ? CURSOR_UPDATE_IMMEDIATE : 0));
    }
}
