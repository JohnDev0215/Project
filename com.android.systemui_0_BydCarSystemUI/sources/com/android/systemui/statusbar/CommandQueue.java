package com.android.systemui.statusbar;

import android.app.ITransientNotificationCallback;
import android.content.ComponentName;
import android.content.res.Configuration;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Pair;
import com.android.internal.os.SomeArgs;
import com.android.internal.statusbar.IStatusBar;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUI;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.stackdivider.Divider;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class CommandQueue extends IStatusBar.Stub {
    public static final int FLAG_EXCLUDE_COMPAT_MODE_PANEL = 16;
    public static final int FLAG_EXCLUDE_INPUT_METHODS_PANEL = 8;
    public static final int FLAG_EXCLUDE_NONE = 0;
    public static final int FLAG_EXCLUDE_NOTIFICATION_PANEL = 4;
    public static final int FLAG_EXCLUDE_RECENTS_PANEL = 2;
    public static final int FLAG_EXCLUDE_SEARCH_PANEL = 1;
    private static final int INDEX_MASK = 65535;
    private static final int MSG_ABORT_TRANSIENT = 3211264;
    private static final int MSG_ADD_BAR = 3080192;
    private static final int MSG_ADD_QS_TILE = 1769472;
    private static final int MSG_APP_TRANSITION_CANCELLED = 1310720;
    private static final int MSG_APP_TRANSITION_FINISHED = 2031616;
    private static final int MSG_APP_TRANSITION_PENDING = 1245184;
    private static final int MSG_APP_TRANSITION_STARTING = 1376256;
    private static final int MSG_ASSIST_DISCLOSURE = 1441792;
    private static final int MSG_BYD_APP_THEME_CHANGE = 6488064;
    private static final int MSG_CAMERA_LAUNCH_GESTURE = 1572864;
    private static final int MSG_CANCEL_PRELOAD_RECENT_APPS = 720896;
    private static final int MSG_CLICK_QS_TILE = 1900544;
    private static final int MSG_COLLAPSE_PANELS = 262144;
    private static final int MSG_DISABLE = 131072;
    private static final int MSG_DISMISS_KEYBOARD_SHORTCUTS = 2097152;
    private static final int MSG_EXPAND_NOTIFICATIONS = 196608;
    private static final int MSG_EXPAND_SETTINGS = 327680;
    private static final int MSG_FINGERPRINT_AUTHENTICATED = 2621440;
    private static final int MSG_FINGERPRINT_ERROR = 2752512;
    private static final int MSG_FINGERPRINT_HELP = 2686976;
    private static final int MSG_FINGERPRINT_HIDE = 2818048;
    private static final int MSG_FINGERPRINT_SHOW = 2555904;
    private static final int MSG_HANDLE_SYSTEM_KEY = 2162688;
    private static final int MSG_HIDE_RECENT_APPS = 917504;
    private static final int MSG_HIDE_TOAST = 3538944;
    private static final int MSG_ICON = 65536;
    private static final int MSG_MASK = -65536;
    private static final int MSG_PRELOAD_RECENT_APPS = 655360;
    private static final int MSG_REMOVE_QS_TILE = 1835008;
    private static final int MSG_ROTATION_PROPOSAL = 2490368;
    private static final int MSG_SET_TOP_APP_HIDES_STATUS_BAR = 2424832;
    private static final int MSG_SET_WINDOW_STATE = 786432;
    private static final int MSG_SHIFT = 16;
    private static final int MSG_SHOW_CHARGING_ANIMATION = 2883584;
    private static final int MSG_SHOW_GLOBAL_ACTIONS = 2228224;
    private static final int MSG_SHOW_IME_BUTTON = 524288;
    private static final int MSG_SHOW_PICTURE_IN_PICTURE_MENU = 1703936;
    private static final int MSG_SHOW_PINNING_TOAST_ENTER_EXIT = 2949120;
    private static final int MSG_SHOW_PINNING_TOAST_ESCAPE = 3014656;
    private static final int MSG_SHOW_RECENT_APPS = 851968;
    private static final int MSG_SHOW_SCREEN_PIN_REQUEST = 1179648;
    private static final int MSG_SHOW_SHUTDOWN_UI = 2359296;
    private static final int MSG_SHOW_TOAST = 3473408;
    private static final int MSG_SHOW_TRANSIENT = 3145728;
    private static final int MSG_START_ASSIST = 1507328;
    private static final int MSG_SYSTEM_BAR_APPEARANCE_CHANGED = 393216;
    private static final int MSG_TOGGLE_APP_SPLIT_SCREEN = 1966080;
    private static final int MSG_TOGGLE_KEYBOARD_SHORTCUTS = 1638400;
    private static final int MSG_TOGGLE_PANEL = 2293760;
    private static final int MSG_TOGGLE_RECENT_APPS = 589824;
    private static final int MSG_TOP_APP_WINDOW_CHANGED = 458752;
    private static final int OP_REMOVE_ICON = 2;
    private static final int OP_SET_ICON = 1;
    private static final String SHOW_IME_SWITCHER_KEY = "showImeSwitcherKey";
    private int mDisable1;
    private int mDisable2;
    private final Object mLock = new Object();
    private ArrayList<Callbacks> mCallbacks = new ArrayList<>();
    private Handler mHandler = new H(Looper.getMainLooper());

    public interface Callbacks {
        default void abortTransient(int displayId, int[] types) {
        }

        default void addBar() {
        }

        default void addQsTile(ComponentName tile) {
        }

        default void animateCollapsePanels(int flags) {
        }

        default void animateExpandNotificationsPanel() {
        }

        default void animateExpandSettingsPanel(String obj) {
        }

        default void appTransitionCancelled() {
        }

        default void appTransitionFinished(int displayId) {
        }

        default void appTransitionPending(boolean forced) {
        }

        default void appTransitionStarting(long startTime, long duration, boolean forced) {
        }

        default void cancelPreloadRecentApps() {
        }

        default void clickTile(ComponentName tile) {
        }

        default void disable(int state1, int state2, boolean animate) {
        }

        default void dismissKeyboardShortcutsMenu() {
        }

        default void handleShowGlobalActionsMenu() {
        }

        default void handleShowShutdownUi(boolean isReboot, String reason) {
        }

        default void handleSystemKey(int arg1) {
        }

        default void hideFingerprintDialog() {
        }

        default void hideRecentApps(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) {
        }

        default void hideToast(String packageName, IBinder token) {
        }

        default void onBydAppThemeChanged() {
        }

        default void onCameraLaunchGestureDetected(int source) {
        }

        default void onFingerprintAuthenticated() {
        }

        default void onFingerprintError(String error) {
        }

        default void onFingerprintHelp(String message) {
        }

        default void onRotationProposal(int rotation, boolean isValid) {
        }

        default void onSystemBarAppearanceChanged(int displayId, int appearance, AppearanceRegion[] appearanceRegions, boolean navbarColorManagedByIme) {
        }

        default void preloadRecentApps() {
        }

        default void remQsTile(ComponentName tile) {
        }

        default void removeIcon(String slot) {
        }

        default void setIcon(String slot, StatusBarIcon icon) {
        }

        default void setImeWindowStatus(IBinder token, int vis, int backDisposition, boolean showImeSwitcher) {
        }

        default void setTopAppHidesStatusBar(boolean topAppHidesStatusBar) {
        }

        default void setWindowState(int window, int state) {
        }

        default void showAssistDisclosure() {
        }

        default void showPictureInPictureMenu() {
        }

        default void showPinningEnterExitToast(boolean entering) {
        }

        default void showPinningEscapeToast() {
        }

        default void showRecentApps(boolean triggeredFromAltTab) {
        }

        default void showScreenPinningRequest(int taskId) {
        }

        default void showToast(int uid, String packageName, IBinder token, CharSequence text, IBinder windowToken, int duration, ITransientNotificationCallback callback) {
        }

        default void showTransient(int displayId, int[] types) {
        }

        default void showWirelessChargingAnimation(int batteryLevel) {
        }

        default void startAssist(Bundle args) {
        }

        default void toggleKeyboardShortcutsMenu(int deviceId) {
        }

        default void togglePanel() {
        }

        default void toggleRecentApps() {
        }

        default void toggleSplitScreen() {
        }

        default void topAppWindowChanged(boolean visible) {
        }
    }

    public void dismissInattentiveSleepWarning(boolean animated) throws RemoteException {
    }

    public void hideAuthenticationDialog() {
    }

    public void onBiometricAuthenticated() {
    }

    public void onBiometricError(int modality, int error, int vendorCode) {
    }

    public void onBiometricHelp(String message) {
    }

    public void onDisplayReady(int displayId) {
    }

    public void onProposedRotationChanged(int i, boolean b) throws RemoteException {
    }

    public void onRecentsAnimationStateChanged(boolean running) {
    }

    public void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal receiver, int biometricModality, boolean requireConfirmation, int userId, String opPackageName, long operationId, int sysUiSessionId) {
    }

    public void showInattentiveSleepWarning() throws RemoteException {
    }

    public void showPinningEnterExitToast(boolean b) throws RemoteException {
    }

    public void showPinningEscapeToast() throws RemoteException {
    }

    public void showWirelessChargingAnimation(int i) throws RemoteException {
    }

    public void startTracing() throws RemoteException {
    }

    public void stopTracing() throws RemoteException {
    }

    public void suppressAmbientDisplay(boolean suppress) throws RemoteException {
    }

    protected CommandQueue() {
    }

    public void addCallback(Callbacks callbacks) {
        this.mCallbacks.add(callbacks);
        callbacks.disable(this.mDisable1, this.mDisable2, false);
    }

    public void removeCallbacks(Callbacks callbacks) {
        this.mCallbacks.remove(callbacks);
    }

    public void setIcon(String slot, StatusBarIcon icon) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(65536, 1, 0, new Pair(slot, icon)).sendToTarget();
        }
    }

    public void removeIcon(String slot) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(65536, 2, 0, slot).sendToTarget();
        }
    }

    public void disable(int state1, int state2, boolean animate) {
        synchronized (this.mLock) {
            this.mDisable1 = state1;
            this.mDisable2 = state2;
            this.mHandler.removeMessages(131072);
            Message messageObtainMessage = this.mHandler.obtainMessage(131072, state1, state2, Boolean.valueOf(animate));
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                this.mHandler.handleMessage(messageObtainMessage);
                messageObtainMessage.recycle();
            } else {
                messageObtainMessage.sendToTarget();
            }
        }
    }

    public void disable(int displayId, int state1, int state2) {
        disable(state1, state2, true);
    }

    public void recomputeDisableFlags(boolean animate) {
        disable(this.mDisable1, this.mDisable2, animate);
    }

    public void animateExpandNotificationsPanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_EXPAND_NOTIFICATIONS);
            this.mHandler.sendEmptyMessage(MSG_EXPAND_NOTIFICATIONS);
        }
    }

    public void animateCollapsePanels() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(262144);
            this.mHandler.obtainMessage(262144, 0, 0).sendToTarget();
        }
    }

    public void animateCollapsePanels(int flags) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(262144);
            this.mHandler.obtainMessage(262144, flags, 0).sendToTarget();
        }
    }

    public void togglePanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_TOGGLE_PANEL);
            this.mHandler.obtainMessage(MSG_TOGGLE_PANEL, 0, 0).sendToTarget();
        }
    }

    public void animateExpandSettingsPanel(String subPanel) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_EXPAND_SETTINGS);
            this.mHandler.obtainMessage(MSG_EXPAND_SETTINGS, subPanel).sendToTarget();
        }
    }

    public void topAppWindowChanged(int displayId, boolean isFullscreen, boolean isImmersive) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_TOP_APP_WINDOW_CHANGED);
            this.mHandler.obtainMessage(MSG_TOP_APP_WINDOW_CHANGED, isFullscreen ? 1 : 0, 0, null).sendToTarget();
        }
    }

    public void setImeWindowStatus(int displayId, IBinder token, int vis, int backDisposition, boolean showImeSwitcher, boolean isMultiClientImeEnabled) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(524288);
            Message messageObtainMessage = this.mHandler.obtainMessage(524288, vis, backDisposition, token);
            messageObtainMessage.getData().putBoolean(SHOW_IME_SWITCHER_KEY, showImeSwitcher);
            messageObtainMessage.sendToTarget();
        }
    }

    public void showRecentApps(boolean triggeredFromAltTab) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_SHOW_RECENT_APPS);
            this.mHandler.obtainMessage(MSG_SHOW_RECENT_APPS, triggeredFromAltTab ? 1 : 0, 0, null).sendToTarget();
        }
    }

    public void hideRecentApps(boolean z, boolean z2) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_HIDE_RECENT_APPS);
            this.mHandler.obtainMessage(MSG_HIDE_RECENT_APPS, z ? 1 : 0, z2 ? 1 : 0, null).sendToTarget();
        }
    }

    public void toggleSplitScreen() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_TOGGLE_APP_SPLIT_SCREEN);
            this.mHandler.obtainMessage(MSG_TOGGLE_APP_SPLIT_SCREEN, 0, 0, null).sendToTarget();
        }
    }

    public void toggleRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_TOGGLE_RECENT_APPS);
            Message messageObtainMessage = this.mHandler.obtainMessage(MSG_TOGGLE_RECENT_APPS, 0, 0, null);
            messageObtainMessage.setAsynchronous(true);
            messageObtainMessage.sendToTarget();
        }
    }

    public void preloadRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_PRELOAD_RECENT_APPS);
            this.mHandler.obtainMessage(MSG_PRELOAD_RECENT_APPS, 0, 0, null).sendToTarget();
        }
    }

    public void cancelPreloadRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_CANCEL_PRELOAD_RECENT_APPS);
            this.mHandler.obtainMessage(MSG_CANCEL_PRELOAD_RECENT_APPS, 0, 0, null).sendToTarget();
        }
    }

    public void dismissKeyboardShortcutsMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2097152);
            this.mHandler.obtainMessage(2097152).sendToTarget();
        }
    }

    public void toggleKeyboardShortcutsMenu(int deviceId) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_TOGGLE_KEYBOARD_SHORTCUTS);
            this.mHandler.obtainMessage(MSG_TOGGLE_KEYBOARD_SHORTCUTS, deviceId, 0).sendToTarget();
        }
    }

    public void showPictureInPictureMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_SHOW_PICTURE_IN_PICTURE_MENU);
            this.mHandler.obtainMessage(MSG_SHOW_PICTURE_IN_PICTURE_MENU).sendToTarget();
        }
    }

    public void setWindowState(int display, int window, int state) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_SET_WINDOW_STATE, window, state, null).sendToTarget();
        }
    }

    public void showScreenPinningRequest(int taskId) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_SHOW_SCREEN_PIN_REQUEST, taskId, 0, null).sendToTarget();
        }
    }

    public void appTransitionPending(int displayId) {
        appTransitionPending(false);
    }

    public void appTransitionPending(boolean forced) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_APP_TRANSITION_PENDING, forced ? 1 : 0, 0).sendToTarget();
        }
    }

    public void appTransitionCancelled(int displayId) {
        synchronized (this.mLock) {
            this.mHandler.sendEmptyMessage(MSG_APP_TRANSITION_CANCELLED);
        }
    }

    public void appTransitionStarting(int displayId, long startTime, long duration) {
        appTransitionStarting(startTime, duration, false);
    }

    public void appTransitionStarting(long startTime, long duration, boolean forced) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_APP_TRANSITION_STARTING, forced ? 1 : 0, 0, Pair.create(Long.valueOf(startTime), Long.valueOf(duration))).sendToTarget();
        }
    }

    public void appTransitionFinished(int displayId) {
        synchronized (this.mLock) {
            this.mHandler.sendEmptyMessage(MSG_APP_TRANSITION_FINISHED);
        }
    }

    public void showAssistDisclosure() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_ASSIST_DISCLOSURE);
            this.mHandler.obtainMessage(MSG_ASSIST_DISCLOSURE).sendToTarget();
        }
    }

    public void startAssist(Bundle args) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_START_ASSIST);
            this.mHandler.obtainMessage(MSG_START_ASSIST, args).sendToTarget();
        }
    }

    public void onCameraLaunchGestureDetected(int source) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_CAMERA_LAUNCH_GESTURE);
            this.mHandler.obtainMessage(MSG_CAMERA_LAUNCH_GESTURE, source, 0).sendToTarget();
        }
    }

    public void addQsTile(ComponentName tile) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_ADD_QS_TILE, tile).sendToTarget();
        }
    }

    public void remQsTile(ComponentName tile) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_REMOVE_QS_TILE, tile).sendToTarget();
        }
    }

    public void clickQsTile(ComponentName tile) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_CLICK_QS_TILE, tile).sendToTarget();
        }
    }

    public void handleSystemKey(int key) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_HANDLE_SYSTEM_KEY, key, 0).sendToTarget();
        }
    }

    public void showGlobalActionsMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_SHOW_GLOBAL_ACTIONS);
            this.mHandler.obtainMessage(MSG_SHOW_GLOBAL_ACTIONS).sendToTarget();
        }
    }

    public void setTopAppHidesStatusBar(boolean z) {
        this.mHandler.removeMessages(MSG_SET_TOP_APP_HIDES_STATUS_BAR);
        this.mHandler.obtainMessage(MSG_SET_TOP_APP_HIDES_STATUS_BAR, z ? 1 : 0, 0).sendToTarget();
    }

    public void showShutdownUi(boolean isReboot, String reason) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_SHOW_SHUTDOWN_UI);
            this.mHandler.obtainMessage(MSG_SHOW_SHUTDOWN_UI, isReboot ? 1 : 0, 0, reason).sendToTarget();
        }
    }

    public void onSystemBarAppearanceChanged(int displayId, int appearance, AppearanceRegion[] appearanceRegions, boolean navbarColorManagedByIme) {
        synchronized (this.mLock) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.argi1 = displayId;
            someArgsObtain.argi2 = appearance;
            someArgsObtain.argi3 = navbarColorManagedByIme ? 1 : 0;
            someArgsObtain.arg1 = appearanceRegions;
            this.mHandler.obtainMessage(MSG_SYSTEM_BAR_APPEARANCE_CHANGED, someArgsObtain).sendToTarget();
        }
    }

    public void showTransient(int displayId, int[] types) throws RemoteException {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_SHOW_TRANSIENT, displayId, 0, types).sendToTarget();
        }
    }

    public void abortTransient(int displayId, int[] types) throws RemoteException {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(MSG_ABORT_TRANSIENT, displayId, 0, types).sendToTarget();
        }
    }

    public void showToast(int uid, String packageName, IBinder token, CharSequence text, IBinder windowToken, int duration, ITransientNotificationCallback callback) {
        synchronized (this.mLock) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = packageName;
            someArgsObtain.arg2 = token;
            someArgsObtain.arg3 = text;
            someArgsObtain.arg4 = windowToken;
            someArgsObtain.arg5 = callback;
            someArgsObtain.argi1 = uid;
            someArgsObtain.argi2 = duration;
            this.mHandler.obtainMessage(MSG_SHOW_TOAST, someArgsObtain).sendToTarget();
        }
    }

    public void hideToast(String packageName, IBinder token) {
        synchronized (this.mLock) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = packageName;
            someArgsObtain.arg2 = token;
            this.mHandler.obtainMessage(MSG_HIDE_TOAST, someArgsObtain).sendToTarget();
        }
    }

    public void handleHomeButton() throws RemoteException {
        doHandleHomeButton();
    }

    private void doHandleHomeButton() {
        BydLogHelper.logD("doHandleHomeButton");
        ((Divider) SysUiServiceProvider.getComponent(SystemUIApplication.getInstance(), Divider.class)).doHomeClick();
    }

    private final class H extends Handler {
        private H(Looper l) {
            super(l);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = 0;
            switch (msg.what & (-65536)) {
                case 65536:
                    int i2 = msg.arg1;
                    if (i2 == 1) {
                        Pair pair = (Pair) msg.obj;
                        while (i < CommandQueue.this.mCallbacks.size()) {
                            ((Callbacks) CommandQueue.this.mCallbacks.get(i)).setIcon((String) pair.first, (StatusBarIcon) pair.second);
                            i++;
                        }
                        break;
                    } else if (i2 == 2) {
                        while (i < CommandQueue.this.mCallbacks.size()) {
                            ((Callbacks) CommandQueue.this.mCallbacks.get(i)).removeIcon((String) msg.obj);
                            i++;
                        }
                        break;
                    }
                    break;
                case 131072:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).disable(msg.arg1, msg.arg2, ((Boolean) msg.obj).booleanValue());
                        i++;
                    }
                    break;
                case CommandQueue.MSG_EXPAND_NOTIFICATIONS /* 196608 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).animateExpandNotificationsPanel();
                        i++;
                    }
                    break;
                case 262144:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).animateCollapsePanels(msg.arg1);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_EXPAND_SETTINGS /* 327680 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).animateExpandSettingsPanel((String) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SYSTEM_BAR_APPEARANCE_CHANGED /* 393216 */:
                    SomeArgs someArgs = (SomeArgs) msg.obj;
                    for (int i3 = 0; i3 < CommandQueue.this.mCallbacks.size(); i3++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i3)).onSystemBarAppearanceChanged(someArgs.argi1, someArgs.argi2, (AppearanceRegion[]) someArgs.arg1, someArgs.argi3 == 1);
                    }
                    someArgs.recycle();
                    break;
                case CommandQueue.MSG_TOP_APP_WINDOW_CHANGED /* 458752 */:
                    for (int i4 = 0; i4 < CommandQueue.this.mCallbacks.size(); i4++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i4)).topAppWindowChanged(msg.arg1 != 0);
                    }
                    break;
                case 524288:
                    for (int i5 = 0; i5 < CommandQueue.this.mCallbacks.size(); i5++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i5)).setImeWindowStatus((IBinder) msg.obj, msg.arg1, msg.arg2, msg.getData().getBoolean(CommandQueue.SHOW_IME_SWITCHER_KEY, false));
                    }
                    break;
                case CommandQueue.MSG_TOGGLE_RECENT_APPS /* 589824 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).toggleRecentApps();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_PRELOAD_RECENT_APPS /* 655360 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).preloadRecentApps();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_CANCEL_PRELOAD_RECENT_APPS /* 720896 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).cancelPreloadRecentApps();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SET_WINDOW_STATE /* 786432 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).setWindowState(msg.arg1, msg.arg2);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_RECENT_APPS /* 851968 */:
                    for (int i6 = 0; i6 < CommandQueue.this.mCallbacks.size(); i6++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i6)).showRecentApps(msg.arg1 != 0);
                    }
                    break;
                case CommandQueue.MSG_HIDE_RECENT_APPS /* 917504 */:
                    for (int i7 = 0; i7 < CommandQueue.this.mCallbacks.size(); i7++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i7)).hideRecentApps(msg.arg1 != 0, msg.arg2 != 0);
                    }
                    break;
                case CommandQueue.MSG_SHOW_SCREEN_PIN_REQUEST /* 1179648 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showScreenPinningRequest(msg.arg1);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_APP_TRANSITION_PENDING /* 1245184 */:
                    for (int i8 = 0; i8 < CommandQueue.this.mCallbacks.size(); i8++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i8)).appTransitionPending(msg.arg1 != 0);
                    }
                    break;
                case CommandQueue.MSG_APP_TRANSITION_CANCELLED /* 1310720 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).appTransitionCancelled();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_APP_TRANSITION_STARTING /* 1376256 */:
                    for (int i9 = 0; i9 < CommandQueue.this.mCallbacks.size(); i9++) {
                        Pair pair2 = (Pair) msg.obj;
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i9)).appTransitionStarting(((Long) pair2.first).longValue(), ((Long) pair2.second).longValue(), msg.arg1 != 0);
                    }
                    break;
                case CommandQueue.MSG_ASSIST_DISCLOSURE /* 1441792 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showAssistDisclosure();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_START_ASSIST /* 1507328 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).startAssist((Bundle) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_CAMERA_LAUNCH_GESTURE /* 1572864 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).onCameraLaunchGestureDetected(msg.arg1);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_TOGGLE_KEYBOARD_SHORTCUTS /* 1638400 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).toggleKeyboardShortcutsMenu(msg.arg1);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_PICTURE_IN_PICTURE_MENU /* 1703936 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showPictureInPictureMenu();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_ADD_QS_TILE /* 1769472 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).addQsTile((ComponentName) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_REMOVE_QS_TILE /* 1835008 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).remQsTile((ComponentName) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_CLICK_QS_TILE /* 1900544 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).clickTile((ComponentName) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_TOGGLE_APP_SPLIT_SCREEN /* 1966080 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).toggleSplitScreen();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_APP_TRANSITION_FINISHED /* 2031616 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).appTransitionFinished(msg.arg1);
                        i++;
                    }
                    break;
                case 2097152:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).dismissKeyboardShortcutsMenu();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_HANDLE_SYSTEM_KEY /* 2162688 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).handleSystemKey(msg.arg1);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_GLOBAL_ACTIONS /* 2228224 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).handleShowGlobalActionsMenu();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_TOGGLE_PANEL /* 2293760 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).togglePanel();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_SHUTDOWN_UI /* 2359296 */:
                    for (int i10 = 0; i10 < CommandQueue.this.mCallbacks.size(); i10++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i10)).handleShowShutdownUi(msg.arg1 != 0, (String) msg.obj);
                    }
                    break;
                case CommandQueue.MSG_SET_TOP_APP_HIDES_STATUS_BAR /* 2424832 */:
                    for (int i11 = 0; i11 < CommandQueue.this.mCallbacks.size(); i11++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i11)).setTopAppHidesStatusBar(msg.arg1 != 0);
                    }
                    break;
                case CommandQueue.MSG_ROTATION_PROPOSAL /* 2490368 */:
                    for (int i12 = 0; i12 < CommandQueue.this.mCallbacks.size(); i12++) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i12)).onRotationProposal(msg.arg1, msg.arg2 != 0);
                    }
                    break;
                case CommandQueue.MSG_FINGERPRINT_SHOW /* 2555904 */:
                    CommandQueue.this.mHandler.removeMessages(CommandQueue.MSG_FINGERPRINT_ERROR);
                    CommandQueue.this.mHandler.removeMessages(CommandQueue.MSG_FINGERPRINT_HELP);
                    CommandQueue.this.mHandler.removeMessages(CommandQueue.MSG_FINGERPRINT_AUTHENTICATED);
                    break;
                case CommandQueue.MSG_FINGERPRINT_AUTHENTICATED /* 2621440 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).onFingerprintAuthenticated();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_FINGERPRINT_HELP /* 2686976 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).onFingerprintHelp((String) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_FINGERPRINT_ERROR /* 2752512 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).onFingerprintError((String) msg.obj);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_FINGERPRINT_HIDE /* 2818048 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).hideFingerprintDialog();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_CHARGING_ANIMATION /* 2883584 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showWirelessChargingAnimation(msg.arg1);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_PINNING_TOAST_ENTER_EXIT /* 2949120 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showPinningEnterExitToast(((Boolean) msg.obj).booleanValue());
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_PINNING_TOAST_ESCAPE /* 3014656 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showPinningEscapeToast();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_ADD_BAR /* 3080192 */:
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).addBar();
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_TRANSIENT /* 3145728 */:
                    int i13 = msg.arg1;
                    int[] iArr = (int[]) msg.obj;
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).showTransient(i13, iArr);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_ABORT_TRANSIENT /* 3211264 */:
                    int i14 = msg.arg1;
                    int[] iArr2 = (int[]) msg.obj;
                    while (i < CommandQueue.this.mCallbacks.size()) {
                        ((Callbacks) CommandQueue.this.mCallbacks.get(i)).abortTransient(i14, iArr2);
                        i++;
                    }
                    break;
                case CommandQueue.MSG_SHOW_TOAST /* 3473408 */:
                    SomeArgs someArgs2 = (SomeArgs) msg.obj;
                    String str = (String) someArgs2.arg1;
                    IBinder iBinder = (IBinder) someArgs2.arg2;
                    CharSequence charSequence = (CharSequence) someArgs2.arg3;
                    IBinder iBinder2 = (IBinder) someArgs2.arg4;
                    ITransientNotificationCallback iTransientNotificationCallback = (ITransientNotificationCallback) someArgs2.arg5;
                    int i15 = someArgs2.argi1;
                    int i16 = someArgs2.argi2;
                    Iterator it = CommandQueue.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((Callbacks) it.next()).showToast(i15, str, iBinder, charSequence, iBinder2, i16, iTransientNotificationCallback);
                    }
                    break;
                case CommandQueue.MSG_HIDE_TOAST /* 3538944 */:
                    SomeArgs someArgs3 = (SomeArgs) msg.obj;
                    String str2 = (String) someArgs3.arg1;
                    IBinder iBinder3 = (IBinder) someArgs3.arg2;
                    Iterator it2 = CommandQueue.this.mCallbacks.iterator();
                    while (it2.hasNext()) {
                        ((Callbacks) it2.next()).hideToast(str2, iBinder3);
                    }
                    break;
                case CommandQueue.MSG_BYD_APP_THEME_CHANGE /* 6488064 */:
                    Iterator it3 = CommandQueue.this.mCallbacks.iterator();
                    while (it3.hasNext()) {
                        ((Callbacks) it3.next()).onBydAppThemeChanged();
                    }
                    break;
            }
        }
    }

    public void notifyBydAppThemeChanged() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(MSG_BYD_APP_THEME_CHANGE);
            this.mHandler.obtainMessage(MSG_BYD_APP_THEME_CHANGE).sendToTarget();
        }
    }

    public static class CommandQueueStart extends SystemUI {
        @Override // com.android.systemui.SystemUI
        public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        }

        @Override // com.android.systemui.SystemUI
        protected void onBootCompleted() {
        }

        @Override // com.android.systemui.SystemUI
        protected void onConfigurationChanged(Configuration newConfig) {
        }

        @Override // com.android.systemui.SystemUI
        public void start() {
            putComponent(CommandQueue.class, new CommandQueue());
        }
    }
}
