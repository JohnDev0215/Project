package android.accessibilityservice;

import android.accessibilityservice.GestureDescription;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.graphics.ColorSpace;
import android.graphics.ParcelableColorSpace;
import android.graphics.Region;
import android.hardware.HardwareBuffer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class AccessibilityService extends Service {
    public static final int ACCESSIBILITY_TAKE_SCREENSHOT_REQUEST_INTERVAL_TIMES_MS = 1000;
    public static final int ERROR_TAKE_SCREENSHOT_INTERNAL_ERROR = 1;
    public static final int ERROR_TAKE_SCREENSHOT_INTERVAL_TIME_SHORT = 3;
    public static final int ERROR_TAKE_SCREENSHOT_INVALID_DISPLAY = 4;
    public static final int ERROR_TAKE_SCREENSHOT_NO_ACCESSIBILITY_ACCESS = 2;
    public static final int GESTURE_2_FINGER_DOUBLE_TAP = 20;
    public static final int GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD = 40;
    public static final int GESTURE_2_FINGER_SINGLE_TAP = 19;
    public static final int GESTURE_2_FINGER_SWIPE_DOWN = 26;
    public static final int GESTURE_2_FINGER_SWIPE_LEFT = 27;
    public static final int GESTURE_2_FINGER_SWIPE_RIGHT = 28;
    public static final int GESTURE_2_FINGER_SWIPE_UP = 25;
    public static final int GESTURE_2_FINGER_TRIPLE_TAP = 21;
    public static final int GESTURE_3_FINGER_DOUBLE_TAP = 23;
    public static final int GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD = 41;
    public static final int GESTURE_3_FINGER_SINGLE_TAP = 22;
    public static final int GESTURE_3_FINGER_SWIPE_DOWN = 30;
    public static final int GESTURE_3_FINGER_SWIPE_LEFT = 31;
    public static final int GESTURE_3_FINGER_SWIPE_RIGHT = 32;
    public static final int GESTURE_3_FINGER_SWIPE_UP = 29;
    public static final int GESTURE_3_FINGER_TRIPLE_TAP = 24;
    public static final int GESTURE_4_FINGER_DOUBLE_TAP = 38;
    public static final int GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD = 42;
    public static final int GESTURE_4_FINGER_SINGLE_TAP = 37;
    public static final int GESTURE_4_FINGER_SWIPE_DOWN = 34;
    public static final int GESTURE_4_FINGER_SWIPE_LEFT = 35;
    public static final int GESTURE_4_FINGER_SWIPE_RIGHT = 36;
    public static final int GESTURE_4_FINGER_SWIPE_UP = 33;
    public static final int GESTURE_4_FINGER_TRIPLE_TAP = 39;
    public static final int GESTURE_DOUBLE_TAP = 17;
    public static final int GESTURE_DOUBLE_TAP_AND_HOLD = 18;
    public static final int GESTURE_SWIPE_DOWN = 2;
    public static final int GESTURE_SWIPE_DOWN_AND_LEFT = 15;
    public static final int GESTURE_SWIPE_DOWN_AND_RIGHT = 16;
    public static final int GESTURE_SWIPE_DOWN_AND_UP = 8;
    public static final int GESTURE_SWIPE_LEFT = 3;
    public static final int GESTURE_SWIPE_LEFT_AND_DOWN = 10;
    public static final int GESTURE_SWIPE_LEFT_AND_RIGHT = 5;
    public static final int GESTURE_SWIPE_LEFT_AND_UP = 9;
    public static final int GESTURE_SWIPE_RIGHT = 4;
    public static final int GESTURE_SWIPE_RIGHT_AND_DOWN = 12;
    public static final int GESTURE_SWIPE_RIGHT_AND_LEFT = 6;
    public static final int GESTURE_SWIPE_RIGHT_AND_UP = 11;
    public static final int GESTURE_SWIPE_UP = 1;
    public static final int GESTURE_SWIPE_UP_AND_DOWN = 7;
    public static final int GESTURE_SWIPE_UP_AND_LEFT = 13;
    public static final int GESTURE_SWIPE_UP_AND_RIGHT = 14;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_ALL_APPS = 14;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON = 11;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON_CHOOSER = 12;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_SHORTCUT = 13;
    public static final int GLOBAL_ACTION_BACK = 1;
    public static final int GLOBAL_ACTION_HOME = 2;
    public static final int GLOBAL_ACTION_KEYCODE_HEADSETHOOK = 10;
    public static final int GLOBAL_ACTION_LOCK_SCREEN = 8;
    public static final int GLOBAL_ACTION_NOTIFICATIONS = 4;
    public static final int GLOBAL_ACTION_POWER_DIALOG = 6;
    public static final int GLOBAL_ACTION_QUICK_SETTINGS = 5;
    public static final int GLOBAL_ACTION_RECENTS = 3;
    public static final int GLOBAL_ACTION_TAKE_SCREENSHOT = 9;
    public static final int GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN = 7;
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_COLORSPACE = "screenshot_colorSpace";
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_HARDWAREBUFFER = "screenshot_hardwareBuffer";
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_STATUS = "screenshot_status";
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_TIMESTAMP = "screenshot_timestamp";
    private static final String LOG_TAG = "AccessibilityService";
    public static final String SERVICE_INTERFACE = "android.accessibilityservice.AccessibilityService";
    public static final String SERVICE_META_DATA = "android.accessibilityservice";
    public static final int SHOW_MODE_AUTO = 0;
    public static final int SHOW_MODE_HARD_KEYBOARD_ORIGINAL_VALUE = 536870912;
    public static final int SHOW_MODE_HARD_KEYBOARD_OVERRIDDEN = 1073741824;
    public static final int SHOW_MODE_HIDDEN = 1;
    public static final int SHOW_MODE_IGNORE_HARD_KEYBOARD = 2;
    public static final int SHOW_MODE_MASK = 3;
    public static final int TAKE_SCREENSHOT_SUCCESS = 0;
    private FingerprintGestureController mFingerprintGestureController;
    private SparseArray<GestureResultCallbackInfo> mGestureStatusCallbackInfos;
    private int mGestureStatusCallbackSequence;
    private AccessibilityServiceInfo mInfo;
    private SoftKeyboardController mSoftKeyboardController;
    private WindowManager mWindowManager;
    private IBinder mWindowToken;
    private int mConnectionId = -1;
    private final SparseArray<MagnificationController> mMagnificationControllers = new SparseArray<>(0);
    private final SparseArray<AccessibilityButtonController> mAccessibilityButtonControllers = new SparseArray<>(0);
    private final Object mLock = new Object();

    public interface Callbacks {
        void init(int i, IBinder iBinder);

        void onAccessibilityButtonAvailabilityChanged(boolean z);

        void onAccessibilityButtonClicked(int i);

        void onAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        void onFingerprintCapturingGesturesChanged(boolean z);

        void onFingerprintGesture(int i);

        boolean onGesture(AccessibilityGestureEvent accessibilityGestureEvent);

        void onInterrupt();

        boolean onKeyEvent(KeyEvent keyEvent);

        void onMagnificationChanged(int i, Region region, float f, float f2, float f3);

        void onPerformGestureResult(int i, boolean z);

        void onServiceConnected();

        void onSoftKeyboardShowModeChanged(int i);

        void onSystemActionsChanged();
    }

    public static abstract class GestureResultCallback {
        public void onCancelled(GestureDescription gestureDescription) {
        }

        public void onCompleted(GestureDescription gestureDescription) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScreenshotErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SoftKeyboardShowMode {
    }

    public interface TakeScreenshotCallback {
        void onFailure(int i);

        void onSuccess(ScreenshotResult screenshotResult);
    }

    public abstract void onAccessibilityEvent(AccessibilityEvent accessibilityEvent);

    @Deprecated
    protected boolean onGesture(int i) {
        return false;
    }

    public abstract void onInterrupt();

    protected boolean onKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    protected void onServiceConnected() {
    }

    public void onSystemActionsChanged() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchServiceConnected() {
        synchronized (this.mLock) {
            for (int i = 0; i < this.mMagnificationControllers.size(); i++) {
                this.mMagnificationControllers.valueAt(i).onServiceConnectedLocked();
            }
        }
        SoftKeyboardController softKeyboardController = this.mSoftKeyboardController;
        if (softKeyboardController != null) {
            softKeyboardController.onServiceConnected();
        }
        onServiceConnected();
    }

    public boolean onGesture(AccessibilityGestureEvent accessibilityGestureEvent) {
        if (accessibilityGestureEvent.getDisplayId() != 0) {
            return false;
        }
        onGesture(accessibilityGestureEvent.getGestureId());
        return false;
    }

    public List<AccessibilityWindowInfo> getWindows() {
        return AccessibilityInteractionClient.getInstance().getWindows(this.mConnectionId);
    }

    public final SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
        return AccessibilityInteractionClient.getInstance().getWindowsOnAllDisplays(this.mConnectionId);
    }

    public AccessibilityNodeInfo getRootInActiveWindow() {
        return AccessibilityInteractionClient.getInstance().getRootInActiveWindow(this.mConnectionId);
    }

    public final void disableSelf() {
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.disableSelf();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createDisplayContext(Display display) {
        Context contextCreateDisplayContext = super.createDisplayContext(display);
        setDefaultTokenInternal(contextCreateDisplayContext, display.getDisplayId());
        return contextCreateDisplayContext;
    }

    private void setDefaultTokenInternal(Context context, int i) {
        IBinder overlayWindowToken;
        WindowManagerImpl windowManagerImpl = (WindowManagerImpl) context.getSystemService(Context.WINDOW_SERVICE);
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            synchronized (this.mLock) {
                try {
                    overlayWindowToken = connection.getOverlayWindowToken(i);
                } catch (RemoteException e) {
                    Log.w(LOG_TAG, "Failed to get window token", e);
                    e.rethrowFromSystemServer();
                    overlayWindowToken = null;
                }
            }
            windowManagerImpl.setDefaultToken(overlayWindowToken);
        }
    }

    public final MagnificationController getMagnificationController() {
        return getMagnificationController(0);
    }

    public final MagnificationController getMagnificationController(int i) {
        MagnificationController magnificationController;
        synchronized (this.mLock) {
            magnificationController = this.mMagnificationControllers.get(i);
            if (magnificationController == null) {
                magnificationController = new MagnificationController(this, this.mLock, i);
                this.mMagnificationControllers.put(i, magnificationController);
            }
        }
        return magnificationController;
    }

    public final FingerprintGestureController getFingerprintGestureController() {
        if (this.mFingerprintGestureController == null) {
            AccessibilityInteractionClient.getInstance();
            this.mFingerprintGestureController = new FingerprintGestureController(AccessibilityInteractionClient.getConnection(this.mConnectionId));
        }
        return this.mFingerprintGestureController;
    }

    public final boolean dispatchGesture(GestureDescription gestureDescription, GestureResultCallback gestureResultCallback, Handler handler) {
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            return false;
        }
        List<GestureDescription.GestureStep> gestureStepsFromGestureDescription = GestureDescription.MotionEventGenerator.getGestureStepsFromGestureDescription(gestureDescription, 16);
        try {
            synchronized (this.mLock) {
                this.mGestureStatusCallbackSequence++;
                if (gestureResultCallback != null) {
                    if (this.mGestureStatusCallbackInfos == null) {
                        this.mGestureStatusCallbackInfos = new SparseArray<>();
                    }
                    this.mGestureStatusCallbackInfos.put(this.mGestureStatusCallbackSequence, new GestureResultCallbackInfo(gestureDescription, gestureResultCallback, handler));
                }
                connection.dispatchGesture(this.mGestureStatusCallbackSequence, new ParceledListSlice(gestureStepsFromGestureDescription), gestureDescription.getDisplayId());
            }
            return true;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    void onPerformGestureResult(int i, final boolean z) {
        final GestureResultCallbackInfo gestureResultCallbackInfo;
        if (this.mGestureStatusCallbackInfos == null) {
            return;
        }
        synchronized (this.mLock) {
            gestureResultCallbackInfo = this.mGestureStatusCallbackInfos.get(i);
            this.mGestureStatusCallbackInfos.remove(i);
        }
        if (gestureResultCallbackInfo == null || gestureResultCallbackInfo.gestureDescription == null || gestureResultCallbackInfo.callback == null) {
            return;
        }
        if (gestureResultCallbackInfo.handler != null) {
            gestureResultCallbackInfo.handler.post(new Runnable() { // from class: android.accessibilityservice.AccessibilityService.1
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        gestureResultCallbackInfo.callback.onCompleted(gestureResultCallbackInfo.gestureDescription);
                    } else {
                        gestureResultCallbackInfo.callback.onCancelled(gestureResultCallbackInfo.gestureDescription);
                    }
                }
            });
        } else if (z) {
            gestureResultCallbackInfo.callback.onCompleted(gestureResultCallbackInfo.gestureDescription);
        } else {
            gestureResultCallbackInfo.callback.onCancelled(gestureResultCallbackInfo.gestureDescription);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMagnificationChanged(int i, Region region, float f, float f2, float f3) {
        MagnificationController magnificationController;
        synchronized (this.mLock) {
            magnificationController = this.mMagnificationControllers.get(i);
        }
        if (magnificationController != null) {
            magnificationController.dispatchMagnificationChanged(region, f, f2, f3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFingerprintCapturingGesturesChanged(boolean z) {
        getFingerprintGestureController().onGestureDetectionActiveChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFingerprintGesture(int i) {
        getFingerprintGestureController().onGesture(i);
    }

    public static final class MagnificationController {
        private final int mDisplayId;
        private ArrayMap<OnMagnificationChangedListener, Handler> mListeners;
        private final Object mLock;
        private final AccessibilityService mService;

        public interface OnMagnificationChangedListener {
            void onMagnificationChanged(MagnificationController magnificationController, Region region, float f, float f2, float f3);
        }

        MagnificationController(AccessibilityService accessibilityService, Object obj, int i) {
            this.mService = accessibilityService;
            this.mLock = obj;
            this.mDisplayId = i;
        }

        void onServiceConnectedLocked() {
            ArrayMap<OnMagnificationChangedListener, Handler> arrayMap = this.mListeners;
            if (arrayMap == null || arrayMap.isEmpty()) {
                return;
            }
            setMagnificationCallbackEnabled(true);
        }

        public void addListener(OnMagnificationChangedListener onMagnificationChangedListener) {
            addListener(onMagnificationChangedListener, null);
        }

        public void addListener(OnMagnificationChangedListener onMagnificationChangedListener, Handler handler) {
            synchronized (this.mLock) {
                if (this.mListeners == null) {
                    this.mListeners = new ArrayMap<>();
                }
                boolean zIsEmpty = this.mListeners.isEmpty();
                this.mListeners.put(onMagnificationChangedListener, handler);
                if (zIsEmpty) {
                    setMagnificationCallbackEnabled(true);
                }
            }
        }

        public boolean removeListener(OnMagnificationChangedListener onMagnificationChangedListener) {
            boolean z;
            if (this.mListeners == null) {
                return false;
            }
            synchronized (this.mLock) {
                int iIndexOfKey = this.mListeners.indexOfKey(onMagnificationChangedListener);
                z = iIndexOfKey >= 0;
                if (z) {
                    this.mListeners.removeAt(iIndexOfKey);
                }
                if (z && this.mListeners.isEmpty()) {
                    setMagnificationCallbackEnabled(false);
                }
            }
            return z;
        }

        private void setMagnificationCallbackEnabled(boolean z) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection != null) {
                try {
                    connection.setMagnificationCallbackEnabled(this.mDisplayId, z);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        void dispatchMagnificationChanged(final Region region, final float f, final float f2, final float f3) {
            synchronized (this.mLock) {
                if (this.mListeners != null && !this.mListeners.isEmpty()) {
                    ArrayMap arrayMap = new ArrayMap(this.mListeners);
                    int size = arrayMap.size();
                    for (int i = 0; i < size; i++) {
                        final OnMagnificationChangedListener onMagnificationChangedListener = (OnMagnificationChangedListener) arrayMap.keyAt(i);
                        Handler handler = (Handler) arrayMap.valueAt(i);
                        if (handler != null) {
                            handler.post(new Runnable() { // from class: android.accessibilityservice.AccessibilityService.MagnificationController.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    onMagnificationChangedListener.onMagnificationChanged(MagnificationController.this, region, f, f2, f3);
                                }
                            });
                        } else {
                            onMagnificationChangedListener.onMagnificationChanged(this, region, f, f2, f3);
                        }
                    }
                    return;
                }
                Slog.d(AccessibilityService.LOG_TAG, "Received magnification changed callback with no listeners registered!");
                setMagnificationCallbackEnabled(false);
            }
        }

        public float getScale() {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 1.0f;
            }
            try {
                return connection.getMagnificationScale(this.mDisplayId);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to obtain scale", e);
                e.rethrowFromSystemServer();
                return 1.0f;
            }
        }

        public float getCenterX() {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 0.0f;
            }
            try {
                return connection.getMagnificationCenterX(this.mDisplayId);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to obtain center X", e);
                e.rethrowFromSystemServer();
                return 0.0f;
            }
        }

        public float getCenterY() {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 0.0f;
            }
            try {
                return connection.getMagnificationCenterY(this.mDisplayId);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to obtain center Y", e);
                e.rethrowFromSystemServer();
                return 0.0f;
            }
        }

        public Region getMagnificationRegion() {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection != null) {
                try {
                    return connection.getMagnificationRegion(this.mDisplayId);
                } catch (RemoteException e) {
                    Log.w(AccessibilityService.LOG_TAG, "Failed to obtain magnified region", e);
                    e.rethrowFromSystemServer();
                }
            }
            return Region.obtain();
        }

        public boolean reset(boolean z) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.resetMagnification(this.mDisplayId, z);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to reset", e);
                e.rethrowFromSystemServer();
                return false;
            }
        }

        public boolean setScale(float f, boolean z) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.setMagnificationScaleAndCenter(this.mDisplayId, f, Float.NaN, Float.NaN, z);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set scale", e);
                e.rethrowFromSystemServer();
                return false;
            }
        }

        public boolean setCenter(float f, float f2, boolean z) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.setMagnificationScaleAndCenter(this.mDisplayId, Float.NaN, f, f2, z);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set center", e);
                e.rethrowFromSystemServer();
                return false;
            }
        }
    }

    public final SoftKeyboardController getSoftKeyboardController() {
        SoftKeyboardController softKeyboardController;
        synchronized (this.mLock) {
            if (this.mSoftKeyboardController == null) {
                this.mSoftKeyboardController = new SoftKeyboardController(this, this.mLock);
            }
            softKeyboardController = this.mSoftKeyboardController;
        }
        return softKeyboardController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSoftKeyboardShowModeChanged(int i) {
        SoftKeyboardController softKeyboardController = this.mSoftKeyboardController;
        if (softKeyboardController != null) {
            softKeyboardController.dispatchSoftKeyboardShowModeChanged(i);
        }
    }

    public static final class SoftKeyboardController {
        private ArrayMap<OnShowModeChangedListener, Handler> mListeners;
        private final Object mLock;
        private final AccessibilityService mService;

        public interface OnShowModeChangedListener {
            void onShowModeChanged(SoftKeyboardController softKeyboardController, int i);
        }

        SoftKeyboardController(AccessibilityService accessibilityService, Object obj) {
            this.mService = accessibilityService;
            this.mLock = obj;
        }

        void onServiceConnected() {
            synchronized (this.mLock) {
                if (this.mListeners != null && !this.mListeners.isEmpty()) {
                    setSoftKeyboardCallbackEnabled(true);
                }
            }
        }

        public void addOnShowModeChangedListener(OnShowModeChangedListener onShowModeChangedListener) {
            addOnShowModeChangedListener(onShowModeChangedListener, null);
        }

        public void addOnShowModeChangedListener(OnShowModeChangedListener onShowModeChangedListener, Handler handler) {
            synchronized (this.mLock) {
                if (this.mListeners == null) {
                    this.mListeners = new ArrayMap<>();
                }
                boolean zIsEmpty = this.mListeners.isEmpty();
                this.mListeners.put(onShowModeChangedListener, handler);
                if (zIsEmpty) {
                    setSoftKeyboardCallbackEnabled(true);
                }
            }
        }

        public boolean removeOnShowModeChangedListener(OnShowModeChangedListener onShowModeChangedListener) {
            boolean z;
            if (this.mListeners == null) {
                return false;
            }
            synchronized (this.mLock) {
                int iIndexOfKey = this.mListeners.indexOfKey(onShowModeChangedListener);
                z = iIndexOfKey >= 0;
                if (z) {
                    this.mListeners.removeAt(iIndexOfKey);
                }
                if (z && this.mListeners.isEmpty()) {
                    setSoftKeyboardCallbackEnabled(false);
                }
            }
            return z;
        }

        private void setSoftKeyboardCallbackEnabled(boolean z) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection != null) {
                try {
                    connection.setSoftKeyboardCallbackEnabled(z);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        void dispatchSoftKeyboardShowModeChanged(final int i) {
            synchronized (this.mLock) {
                if (this.mListeners != null && !this.mListeners.isEmpty()) {
                    ArrayMap arrayMap = new ArrayMap(this.mListeners);
                    int size = arrayMap.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        final OnShowModeChangedListener onShowModeChangedListener = (OnShowModeChangedListener) arrayMap.keyAt(i2);
                        Handler handler = (Handler) arrayMap.valueAt(i2);
                        if (handler != null) {
                            handler.post(new Runnable() { // from class: android.accessibilityservice.AccessibilityService.SoftKeyboardController.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    onShowModeChangedListener.onShowModeChanged(SoftKeyboardController.this, i);
                                }
                            });
                        } else {
                            onShowModeChangedListener.onShowModeChanged(this, i);
                        }
                    }
                    return;
                }
                Slog.w(AccessibilityService.LOG_TAG, "Received soft keyboard show mode changed callback with no listeners registered!");
                setSoftKeyboardCallbackEnabled(false);
            }
        }

        public int getShowMode() {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 0;
            }
            try {
                return connection.getSoftKeyboardShowMode();
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set soft keyboard behavior", e);
                e.rethrowFromSystemServer();
                return 0;
            }
        }

        public boolean setShowMode(int i) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.setSoftKeyboardShowMode(i);
            } catch (RemoteException e) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set soft keyboard behavior", e);
                e.rethrowFromSystemServer();
                return false;
            }
        }

        public boolean switchToInputMethod(String str) {
            AccessibilityInteractionClient.getInstance();
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.switchToInputMethod(str);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final AccessibilityButtonController getAccessibilityButtonController() {
        return getAccessibilityButtonController(0);
    }

    public final AccessibilityButtonController getAccessibilityButtonController(int i) {
        AccessibilityButtonController accessibilityButtonController;
        synchronized (this.mLock) {
            accessibilityButtonController = this.mAccessibilityButtonControllers.get(i);
            if (accessibilityButtonController == null) {
                AccessibilityInteractionClient.getInstance();
                accessibilityButtonController = new AccessibilityButtonController(AccessibilityInteractionClient.getConnection(this.mConnectionId));
                this.mAccessibilityButtonControllers.put(i, accessibilityButtonController);
            }
        }
        return accessibilityButtonController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccessibilityButtonClicked(int i) {
        getAccessibilityButtonController(i).dispatchAccessibilityButtonClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccessibilityButtonAvailabilityChanged(boolean z) {
        getAccessibilityButtonController().dispatchAccessibilityButtonAvailabilityChanged(z);
    }

    public final List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() {
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                return connection.getSystemActions();
            } catch (RemoteException e) {
                Log.w(LOG_TAG, "Error while calling getSystemActions", e);
                e.rethrowFromSystemServer();
            }
        }
        return Collections.emptyList();
    }

    public final boolean performGlobalAction(int i) {
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            return false;
        }
        try {
            return connection.performGlobalAction(i);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, "Error while calling performGlobalAction", e);
            e.rethrowFromSystemServer();
            return false;
        }
    }

    public AccessibilityNodeInfo findFocus(int i) {
        return AccessibilityInteractionClient.getInstance().findFocus(this.mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, i);
    }

    public final AccessibilityServiceInfo getServiceInfo() {
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            return null;
        }
        try {
            return connection.getServiceInfo();
        } catch (RemoteException e) {
            Log.w(LOG_TAG, "Error while getting AccessibilityServiceInfo", e);
            e.rethrowFromSystemServer();
            return null;
        }
    }

    public final void setServiceInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
        this.mInfo = accessibilityServiceInfo;
        sendServiceInfo();
    }

    private void sendServiceInfo() {
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        AccessibilityServiceInfo accessibilityServiceInfo = this.mInfo;
        if (accessibilityServiceInfo == null || connection == null) {
            return;
        }
        try {
            connection.setServiceInfo(accessibilityServiceInfo);
            this.mInfo = null;
            AccessibilityInteractionClient.getInstance().clearCache();
        } catch (RemoteException e) {
            Log.w(LOG_TAG, "Error while setting AccessibilityServiceInfo", e);
            e.rethrowFromSystemServer();
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (getBaseContext() == null) {
            throw new IllegalStateException("System services not available to Activities before onCreate()");
        }
        if (Context.WINDOW_SERVICE.equals(str)) {
            if (this.mWindowManager == null) {
                this.mWindowManager = (WindowManager) getBaseContext().getSystemService(str);
            }
            return this.mWindowManager;
        }
        return super.getSystemService(str);
    }

    public void takeScreenshot(int i, Executor executor, TakeScreenshotCallback takeScreenshotCallback) {
        Preconditions.checkNotNull(executor, "executor cannot be null");
        Preconditions.checkNotNull(takeScreenshotCallback, "callback cannot be null");
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            sendScreenshotFailure(1, executor, takeScreenshotCallback);
            return;
        }
        try {
            connection.takeScreenshot(i, new RemoteCallback(new $$Lambda$AccessibilityService$q1syDPtLKDyMLkI7cIJLVQqmK_Y(this, executor, takeScreenshotCallback)));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public /* synthetic */ void lambda$takeScreenshot$0$AccessibilityService(Executor executor, TakeScreenshotCallback takeScreenshotCallback, Bundle bundle) {
        int i = bundle.getInt(KEY_ACCESSIBILITY_SCREENSHOT_STATUS);
        if (i != 0) {
            sendScreenshotFailure(i, executor, takeScreenshotCallback);
        } else {
            sendScreenshotSuccess(new ScreenshotResult((HardwareBuffer) bundle.getParcelable(KEY_ACCESSIBILITY_SCREENSHOT_HARDWAREBUFFER), ((ParcelableColorSpace) bundle.getParcelable(KEY_ACCESSIBILITY_SCREENSHOT_COLORSPACE)).getColorSpace(), bundle.getLong(KEY_ACCESSIBILITY_SCREENSHOT_TIMESTAMP)), executor, takeScreenshotCallback);
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return new IAccessibilityServiceClientWrapper(this, getMainLooper(), new Callbacks() { // from class: android.accessibilityservice.AccessibilityService.2
            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onServiceConnected() {
                AccessibilityService.this.dispatchServiceConnected();
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onInterrupt() {
                AccessibilityService.this.onInterrupt();
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
                AccessibilityService.this.onAccessibilityEvent(accessibilityEvent);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void init(int i, IBinder iBinder) {
                AccessibilityService.this.mConnectionId = i;
                AccessibilityService.this.mWindowToken = iBinder;
                ((WindowManagerImpl) AccessibilityService.this.getSystemService(Context.WINDOW_SERVICE)).setDefaultToken(iBinder);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public boolean onGesture(AccessibilityGestureEvent accessibilityGestureEvent) {
                return AccessibilityService.this.onGesture(accessibilityGestureEvent);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public boolean onKeyEvent(KeyEvent keyEvent) {
                return AccessibilityService.this.onKeyEvent(keyEvent);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onMagnificationChanged(int i, Region region, float f, float f2, float f3) {
                AccessibilityService.this.onMagnificationChanged(i, region, f, f2, f3);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onSoftKeyboardShowModeChanged(int i) {
                AccessibilityService.this.onSoftKeyboardShowModeChanged(i);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onPerformGestureResult(int i, boolean z) {
                AccessibilityService.this.onPerformGestureResult(i, z);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onFingerprintCapturingGesturesChanged(boolean z) {
                AccessibilityService.this.onFingerprintCapturingGesturesChanged(z);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onFingerprintGesture(int i) {
                AccessibilityService.this.onFingerprintGesture(i);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onAccessibilityButtonClicked(int i) {
                AccessibilityService.this.onAccessibilityButtonClicked(i);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onAccessibilityButtonAvailabilityChanged(boolean z) {
                AccessibilityService.this.onAccessibilityButtonAvailabilityChanged(z);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onSystemActionsChanged() {
                AccessibilityService.this.onSystemActionsChanged();
            }
        });
    }

    public static class IAccessibilityServiceClientWrapper extends IAccessibilityServiceClient.Stub implements HandlerCaller.Callback {
        private static final int DO_ACCESSIBILITY_BUTTON_AVAILABILITY_CHANGED = 13;
        private static final int DO_ACCESSIBILITY_BUTTON_CLICKED = 12;
        private static final int DO_CLEAR_ACCESSIBILITY_CACHE = 5;
        private static final int DO_GESTURE_COMPLETE = 9;
        private static final int DO_INIT = 1;
        private static final int DO_ON_ACCESSIBILITY_EVENT = 3;
        private static final int DO_ON_FINGERPRINT_ACTIVE_CHANGED = 10;
        private static final int DO_ON_FINGERPRINT_GESTURE = 11;
        private static final int DO_ON_GESTURE = 4;
        private static final int DO_ON_INTERRUPT = 2;
        private static final int DO_ON_KEY_EVENT = 6;
        private static final int DO_ON_MAGNIFICATION_CHANGED = 7;
        private static final int DO_ON_SOFT_KEYBOARD_SHOW_MODE_CHANGED = 8;
        private static final int DO_ON_SYSTEM_ACTIONS_CHANGED = 14;
        private final Callbacks mCallback;
        private final HandlerCaller mCaller;
        private int mConnectionId = -1;

        public IAccessibilityServiceClientWrapper(Context context, Looper looper, Callbacks callbacks) {
            this.mCallback = callbacks;
            this.mCaller = new HandlerCaller(context, looper, this, true);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void init(IAccessibilityServiceConnection iAccessibilityServiceConnection, int i, IBinder iBinder) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageIOO(1, i, iAccessibilityServiceConnection, iBinder));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onInterrupt() {
            this.mCaller.sendMessage(this.mCaller.obtainMessage(2));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, boolean z) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageBO(3, z, accessibilityEvent));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onGesture(AccessibilityGestureEvent accessibilityGestureEvent) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageO(4, accessibilityGestureEvent));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void clearAccessibilityCache() {
            this.mCaller.sendMessage(this.mCaller.obtainMessage(5));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onKeyEvent(KeyEvent keyEvent, int i) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageIO(6, i, keyEvent));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onMagnificationChanged(int i, Region region, float f, float f2, float f3) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = region;
            someArgsObtain.arg2 = Float.valueOf(f);
            someArgsObtain.arg3 = Float.valueOf(f2);
            someArgsObtain.arg4 = Float.valueOf(f3);
            someArgsObtain.argi1 = i;
            this.mCaller.sendMessage(this.mCaller.obtainMessageO(7, someArgsObtain));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onSoftKeyboardShowModeChanged(int i) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageI(8, i));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onPerformGestureResult(int i, boolean z) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageII(9, i, z ? 1 : 0));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onFingerprintCapturingGesturesChanged(boolean z) {
            HandlerCaller handlerCaller = this.mCaller;
            handlerCaller.sendMessage(handlerCaller.obtainMessageI(10, z ? 1 : 0));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onFingerprintGesture(int i) {
            HandlerCaller handlerCaller = this.mCaller;
            handlerCaller.sendMessage(handlerCaller.obtainMessageI(11, i));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onAccessibilityButtonClicked(int i) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageI(12, i));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onAccessibilityButtonAvailabilityChanged(boolean z) {
            this.mCaller.sendMessage(this.mCaller.obtainMessageI(13, z ? 1 : 0));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onSystemActionsChanged() {
            HandlerCaller handlerCaller = this.mCaller;
            handlerCaller.sendMessage(handlerCaller.obtainMessage(14));
        }

        @Override // com.android.internal.os.HandlerCaller.Callback
        public void executeMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.mConnectionId = message.arg1;
                    SomeArgs someArgs = (SomeArgs) message.obj;
                    IAccessibilityServiceConnection iAccessibilityServiceConnection = (IAccessibilityServiceConnection) someArgs.arg1;
                    IBinder iBinder = (IBinder) someArgs.arg2;
                    someArgs.recycle();
                    if (iAccessibilityServiceConnection != null) {
                        AccessibilityInteractionClient.getInstance();
                        AccessibilityInteractionClient.addConnection(this.mConnectionId, iAccessibilityServiceConnection);
                        this.mCallback.init(this.mConnectionId, iBinder);
                        this.mCallback.onServiceConnected();
                        return;
                    }
                    AccessibilityInteractionClient.getInstance();
                    AccessibilityInteractionClient.removeConnection(this.mConnectionId);
                    this.mConnectionId = -1;
                    AccessibilityInteractionClient.getInstance().clearCache();
                    this.mCallback.init(-1, null);
                    return;
                case 2:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onInterrupt();
                        return;
                    }
                    return;
                case 3:
                    AccessibilityEvent accessibilityEvent = (AccessibilityEvent) message.obj;
                    boolean z = message.arg1 != 0;
                    if (accessibilityEvent != null) {
                        AccessibilityInteractionClient.getInstance().onAccessibilityEvent(accessibilityEvent);
                        if (z && this.mConnectionId != -1) {
                            this.mCallback.onAccessibilityEvent(accessibilityEvent);
                        }
                        try {
                            accessibilityEvent.recycle();
                            return;
                        } catch (IllegalStateException unused) {
                            return;
                        }
                    }
                    return;
                case 4:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onGesture((AccessibilityGestureEvent) message.obj);
                        return;
                    }
                    return;
                case 5:
                    AccessibilityInteractionClient.getInstance().clearCache();
                    return;
                case 6:
                    KeyEvent keyEvent = (KeyEvent) message.obj;
                    try {
                        AccessibilityInteractionClient.getInstance();
                        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
                        if (connection != null) {
                            try {
                                connection.setOnKeyEventResult(this.mCallback.onKeyEvent(keyEvent), message.arg1);
                                break;
                            } catch (RemoteException unused2) {
                            }
                        }
                        try {
                            return;
                        } catch (IllegalStateException unused3) {
                            return;
                        }
                    } finally {
                        try {
                            keyEvent.recycle();
                            break;
                        } catch (IllegalStateException unused4) {
                        }
                    }
                case 7:
                    if (this.mConnectionId != -1) {
                        SomeArgs someArgs2 = (SomeArgs) message.obj;
                        Region region = (Region) someArgs2.arg1;
                        float fFloatValue = ((Float) someArgs2.arg2).floatValue();
                        float fFloatValue2 = ((Float) someArgs2.arg3).floatValue();
                        float fFloatValue3 = ((Float) someArgs2.arg4).floatValue();
                        int i = someArgs2.argi1;
                        someArgs2.recycle();
                        this.mCallback.onMagnificationChanged(i, region, fFloatValue, fFloatValue2, fFloatValue3);
                        return;
                    }
                    return;
                case 8:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onSoftKeyboardShowModeChanged(message.arg1);
                        return;
                    }
                    return;
                case 9:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onPerformGestureResult(message.arg1, message.arg2 == 1);
                        return;
                    }
                    return;
                case 10:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onFingerprintCapturingGesturesChanged(message.arg1 == 1);
                        return;
                    }
                    return;
                case 11:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onFingerprintGesture(message.arg1);
                        return;
                    }
                    return;
                case 12:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onAccessibilityButtonClicked(message.arg1);
                        return;
                    }
                    return;
                case 13:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onAccessibilityButtonAvailabilityChanged(message.arg1 != 0);
                        return;
                    }
                    return;
                case 14:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onSystemActionsChanged();
                        return;
                    }
                    return;
                default:
                    Log.w(AccessibilityService.LOG_TAG, "Unknown message type " + message.what);
                    return;
            }
        }
    }

    private static class GestureResultCallbackInfo {
        GestureResultCallback callback;
        GestureDescription gestureDescription;
        Handler handler;

        GestureResultCallbackInfo(GestureDescription gestureDescription, GestureResultCallback gestureResultCallback, Handler handler) {
            this.gestureDescription = gestureDescription;
            this.callback = gestureResultCallback;
            this.handler = handler;
        }
    }

    private void sendScreenshotSuccess(final ScreenshotResult screenshotResult, Executor executor, final TakeScreenshotCallback takeScreenshotCallback) {
        executor.execute(new Runnable() { // from class: android.accessibilityservice.-$$Lambda$AccessibilityService$Tu7BpkB1mA_cnSrnZqwCCw4inNQ
            @Override // java.lang.Runnable
            public final void run() {
                takeScreenshotCallback.onSuccess(screenshotResult);
            }
        });
    }

    private void sendScreenshotFailure(final int i, Executor executor, final TakeScreenshotCallback takeScreenshotCallback) {
        executor.execute(new Runnable() { // from class: android.accessibilityservice.-$$Lambda$AccessibilityService$QPuf76fmbA3YdpMRuW3aotBDLhc
            @Override // java.lang.Runnable
            public final void run() {
                takeScreenshotCallback.onFailure(i);
            }
        });
    }

    public static final class ScreenshotResult {
        private final ColorSpace mColorSpace;
        private final HardwareBuffer mHardwareBuffer;
        private final long mTimestamp;

        private ScreenshotResult(HardwareBuffer hardwareBuffer, ColorSpace colorSpace, long j) {
            Preconditions.checkNotNull(hardwareBuffer, "hardwareBuffer cannot be null");
            Preconditions.checkNotNull(colorSpace, "colorSpace cannot be null");
            this.mHardwareBuffer = hardwareBuffer;
            this.mColorSpace = colorSpace;
            this.mTimestamp = j;
        }

        public ColorSpace getColorSpace() {
            return this.mColorSpace;
        }

        public HardwareBuffer getHardwareBuffer() {
            return this.mHardwareBuffer;
        }

        public long getTimestamp() {
            return this.mTimestamp;
        }
    }

    public void setGestureDetectionPassthroughRegion(int i, Region region) {
        Preconditions.checkNotNull(region, "region cannot be null");
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.setGestureDetectionPassthroughRegion(i, region);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setTouchExplorationPassthroughRegion(int i, Region region) {
        Preconditions.checkNotNull(region, "region cannot be null");
        AccessibilityInteractionClient.getInstance();
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.setTouchExplorationPassthroughRegion(i, region);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
