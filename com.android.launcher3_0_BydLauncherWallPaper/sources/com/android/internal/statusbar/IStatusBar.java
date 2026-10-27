package com.android.internal.statusbar;

import android.app.ITransientNotificationCallback;
import android.content.ComponentName;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.internal.view.AppearanceRegion;

/* JADX INFO: loaded from: classes4.dex */
public interface IStatusBar extends IInterface {

    public static class Default implements IStatusBar {
        @Override // com.android.internal.statusbar.IStatusBar
        public void abortTransient(int i, int[] iArr) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void addQsTile(ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void animateCollapsePanels() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void animateExpandNotificationsPanel() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void animateExpandSettingsPanel(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionCancelled(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionFinished(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionPending(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionStarting(int i, long j, long j2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void cancelPreloadRecentApps() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void clickQsTile(ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void disable(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void dismissInattentiveSleepWarning(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void dismissKeyboardShortcutsMenu() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void handleSystemKey(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void hideAuthenticationDialog() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void hideRecentApps(boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void hideToast(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onBiometricAuthenticated() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onBiometricError(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onBiometricHelp(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onCameraLaunchGestureDetected(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onDisplayReady(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onProposedRotationChanged(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onRecentsAnimationStateChanged(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onSystemBarAppearanceChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void preloadRecentApps() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void remQsTile(ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void removeIcon(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setIcon(String str, StatusBarIcon statusBarIcon) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setTopAppHidesStatusBar(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setWindowState(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showAssistDisclosure() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2, String str, long j, int i3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showGlobalActionsMenu() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showInattentiveSleepWarning() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showPictureInPictureMenu() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showPinningEnterExitToast(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showPinningEscapeToast() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showRecentApps(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showScreenPinningRequest(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showShutdownUi(boolean z, String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showTransient(int i, int[] iArr) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showWirelessChargingAnimation(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void startAssist(Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void startTracing() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void stopTracing() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void suppressAmbientDisplay(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void toggleKeyboardShortcutsMenu(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void togglePanel() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void toggleRecentApps() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void toggleSplitScreen() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void topAppWindowChanged(int i, boolean z, boolean z2) throws RemoteException {
        }
    }

    void abortTransient(int i, int[] iArr) throws RemoteException;

    void addQsTile(ComponentName componentName) throws RemoteException;

    void animateCollapsePanels() throws RemoteException;

    void animateExpandNotificationsPanel() throws RemoteException;

    void animateExpandSettingsPanel(String str) throws RemoteException;

    void appTransitionCancelled(int i) throws RemoteException;

    void appTransitionFinished(int i) throws RemoteException;

    void appTransitionPending(int i) throws RemoteException;

    void appTransitionStarting(int i, long j, long j2) throws RemoteException;

    void cancelPreloadRecentApps() throws RemoteException;

    void clickQsTile(ComponentName componentName) throws RemoteException;

    void disable(int i, int i2, int i3) throws RemoteException;

    void dismissInattentiveSleepWarning(boolean z) throws RemoteException;

    void dismissKeyboardShortcutsMenu() throws RemoteException;

    void handleSystemKey(int i) throws RemoteException;

    void hideAuthenticationDialog() throws RemoteException;

    void hideRecentApps(boolean z, boolean z2) throws RemoteException;

    void hideToast(String str, IBinder iBinder) throws RemoteException;

    void onBiometricAuthenticated() throws RemoteException;

    void onBiometricError(int i, int i2, int i3) throws RemoteException;

    void onBiometricHelp(String str) throws RemoteException;

    void onCameraLaunchGestureDetected(int i) throws RemoteException;

    void onDisplayReady(int i) throws RemoteException;

    void onProposedRotationChanged(int i, boolean z) throws RemoteException;

    void onRecentsAnimationStateChanged(boolean z) throws RemoteException;

    void onSystemBarAppearanceChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z) throws RemoteException;

    void preloadRecentApps() throws RemoteException;

    void remQsTile(ComponentName componentName) throws RemoteException;

    void removeIcon(String str) throws RemoteException;

    void setIcon(String str, StatusBarIcon statusBarIcon) throws RemoteException;

    void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws RemoteException;

    void setTopAppHidesStatusBar(boolean z) throws RemoteException;

    void setWindowState(int i, int i2, int i3) throws RemoteException;

    void showAssistDisclosure() throws RemoteException;

    void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2, String str, long j, int i3) throws RemoteException;

    void showGlobalActionsMenu() throws RemoteException;

    void showInattentiveSleepWarning() throws RemoteException;

    void showPictureInPictureMenu() throws RemoteException;

    void showPinningEnterExitToast(boolean z) throws RemoteException;

    void showPinningEscapeToast() throws RemoteException;

    void showRecentApps(boolean z) throws RemoteException;

    void showScreenPinningRequest(int i) throws RemoteException;

    void showShutdownUi(boolean z, String str) throws RemoteException;

    void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException;

    void showTransient(int i, int[] iArr) throws RemoteException;

    void showWirelessChargingAnimation(int i) throws RemoteException;

    void startAssist(Bundle bundle) throws RemoteException;

    void startTracing() throws RemoteException;

    void stopTracing() throws RemoteException;

    void suppressAmbientDisplay(boolean z) throws RemoteException;

    void toggleKeyboardShortcutsMenu(int i) throws RemoteException;

    void togglePanel() throws RemoteException;

    void toggleRecentApps() throws RemoteException;

    void toggleSplitScreen() throws RemoteException;

    void topAppWindowChanged(int i, boolean z, boolean z2) throws RemoteException;

    public static abstract class Stub extends Binder implements IStatusBar {
        private static final String DESCRIPTOR = "com.android.internal.statusbar.IStatusBar";
        static final int TRANSACTION_abortTransient = 48;
        static final int TRANSACTION_addQsTile = 32;
        static final int TRANSACTION_animateCollapsePanels = 6;
        static final int TRANSACTION_animateExpandNotificationsPanel = 4;
        static final int TRANSACTION_animateExpandSettingsPanel = 5;
        static final int TRANSACTION_appTransitionCancelled = 22;
        static final int TRANSACTION_appTransitionFinished = 24;
        static final int TRANSACTION_appTransitionPending = 21;
        static final int TRANSACTION_appTransitionStarting = 23;
        static final int TRANSACTION_cancelPreloadRecentApps = 17;
        static final int TRANSACTION_clickQsTile = 34;
        static final int TRANSACTION_disable = 3;
        static final int TRANSACTION_dismissInattentiveSleepWarning = 50;
        static final int TRANSACTION_dismissKeyboardShortcutsMenu = 19;
        static final int TRANSACTION_handleSystemKey = 35;
        static final int TRANSACTION_hideAuthenticationDialog = 43;
        static final int TRANSACTION_hideRecentApps = 13;
        static final int TRANSACTION_hideToast = 52;
        static final int TRANSACTION_onBiometricAuthenticated = 40;
        static final int TRANSACTION_onBiometricError = 42;
        static final int TRANSACTION_onBiometricHelp = 41;
        static final int TRANSACTION_onCameraLaunchGestureDetected = 27;
        static final int TRANSACTION_onDisplayReady = 44;
        static final int TRANSACTION_onProposedRotationChanged = 30;
        static final int TRANSACTION_onRecentsAnimationStateChanged = 45;
        static final int TRANSACTION_onSystemBarAppearanceChanged = 46;
        static final int TRANSACTION_preloadRecentApps = 16;
        static final int TRANSACTION_remQsTile = 33;
        static final int TRANSACTION_removeIcon = 2;
        static final int TRANSACTION_setIcon = 1;
        static final int TRANSACTION_setImeWindowStatus = 10;
        static final int TRANSACTION_setTopAppHidesStatusBar = 31;
        static final int TRANSACTION_setWindowState = 11;
        static final int TRANSACTION_showAssistDisclosure = 25;
        static final int TRANSACTION_showAuthenticationDialog = 39;
        static final int TRANSACTION_showGlobalActionsMenu = 29;
        static final int TRANSACTION_showInattentiveSleepWarning = 49;
        static final int TRANSACTION_showPictureInPictureMenu = 28;
        static final int TRANSACTION_showPinningEnterExitToast = 36;
        static final int TRANSACTION_showPinningEscapeToast = 37;
        static final int TRANSACTION_showRecentApps = 12;
        static final int TRANSACTION_showScreenPinningRequest = 18;
        static final int TRANSACTION_showShutdownUi = 38;
        static final int TRANSACTION_showToast = 51;
        static final int TRANSACTION_showTransient = 47;
        static final int TRANSACTION_showWirelessChargingAnimation = 8;
        static final int TRANSACTION_startAssist = 26;
        static final int TRANSACTION_startTracing = 53;
        static final int TRANSACTION_stopTracing = 54;
        static final int TRANSACTION_suppressAmbientDisplay = 55;
        static final int TRANSACTION_toggleKeyboardShortcutsMenu = 20;
        static final int TRANSACTION_togglePanel = 7;
        static final int TRANSACTION_toggleRecentApps = 14;
        static final int TRANSACTION_toggleSplitScreen = 15;
        static final int TRANSACTION_topAppWindowChanged = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setIcon";
                case 2:
                    return "removeIcon";
                case 3:
                    return "disable";
                case 4:
                    return "animateExpandNotificationsPanel";
                case 5:
                    return "animateExpandSettingsPanel";
                case 6:
                    return "animateCollapsePanels";
                case 7:
                    return "togglePanel";
                case 8:
                    return "showWirelessChargingAnimation";
                case 9:
                    return "topAppWindowChanged";
                case 10:
                    return "setImeWindowStatus";
                case 11:
                    return "setWindowState";
                case 12:
                    return "showRecentApps";
                case 13:
                    return "hideRecentApps";
                case 14:
                    return "toggleRecentApps";
                case 15:
                    return "toggleSplitScreen";
                case 16:
                    return "preloadRecentApps";
                case 17:
                    return "cancelPreloadRecentApps";
                case 18:
                    return "showScreenPinningRequest";
                case 19:
                    return "dismissKeyboardShortcutsMenu";
                case 20:
                    return "toggleKeyboardShortcutsMenu";
                case 21:
                    return "appTransitionPending";
                case 22:
                    return "appTransitionCancelled";
                case 23:
                    return "appTransitionStarting";
                case 24:
                    return "appTransitionFinished";
                case 25:
                    return "showAssistDisclosure";
                case 26:
                    return "startAssist";
                case 27:
                    return "onCameraLaunchGestureDetected";
                case 28:
                    return "showPictureInPictureMenu";
                case 29:
                    return "showGlobalActionsMenu";
                case 30:
                    return "onProposedRotationChanged";
                case 31:
                    return "setTopAppHidesStatusBar";
                case 32:
                    return "addQsTile";
                case 33:
                    return "remQsTile";
                case 34:
                    return "clickQsTile";
                case 35:
                    return "handleSystemKey";
                case 36:
                    return "showPinningEnterExitToast";
                case 37:
                    return "showPinningEscapeToast";
                case 38:
                    return "showShutdownUi";
                case 39:
                    return "showAuthenticationDialog";
                case 40:
                    return "onBiometricAuthenticated";
                case 41:
                    return "onBiometricHelp";
                case 42:
                    return "onBiometricError";
                case 43:
                    return "hideAuthenticationDialog";
                case 44:
                    return "onDisplayReady";
                case 45:
                    return "onRecentsAnimationStateChanged";
                case 46:
                    return "onSystemBarAppearanceChanged";
                case 47:
                    return "showTransient";
                case 48:
                    return "abortTransient";
                case 49:
                    return "showInattentiveSleepWarning";
                case 50:
                    return "dismissInattentiveSleepWarning";
                case 51:
                    return "showToast";
                case 52:
                    return "hideToast";
                case 53:
                    return "startTracing";
                case 54:
                    return "stopTracing";
                case 55:
                    return "suppressAmbientDisplay";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStatusBar asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStatusBar)) {
                return (IStatusBar) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIcon(parcel.readString(), parcel.readInt() != 0 ? StatusBarIcon.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeIcon(parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    disable(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    animateExpandNotificationsPanel();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    animateExpandSettingsPanel(parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    animateCollapsePanels();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    togglePanel();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    showWirelessChargingAnimation(parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    topAppWindowChanged(parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setImeWindowStatus(parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWindowState(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    showRecentApps(parcel.readInt() != 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideRecentApps(parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    toggleRecentApps();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    toggleSplitScreen();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    preloadRecentApps();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelPreloadRecentApps();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    showScreenPinningRequest(parcel.readInt());
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissKeyboardShortcutsMenu();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    toggleKeyboardShortcutsMenu(parcel.readInt());
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    appTransitionPending(parcel.readInt());
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    appTransitionCancelled(parcel.readInt());
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    appTransitionStarting(parcel.readInt(), parcel.readLong(), parcel.readLong());
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    appTransitionFinished(parcel.readInt());
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    showAssistDisclosure();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    startAssist(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCameraLaunchGestureDetected(parcel.readInt());
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    showPictureInPictureMenu();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    showGlobalActionsMenu();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    onProposedRotationChanged(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTopAppHidesStatusBar(parcel.readInt() != 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    addQsTile(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    remQsTile(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    clickQsTile(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    handleSystemKey(parcel.readInt());
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    showPinningEnterExitToast(parcel.readInt() != 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    showPinningEscapeToast();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    showShutdownUi(parcel.readInt() != 0, parcel.readString());
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    showAuthenticationDialog(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, IBiometricServiceReceiverInternal.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readInt());
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBiometricAuthenticated();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBiometricHelp(parcel.readString());
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBiometricError(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideAuthenticationDialog();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDisplayReady(parcel.readInt());
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRecentsAnimationStateChanged(parcel.readInt() != 0);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSystemBarAppearanceChanged(parcel.readInt(), parcel.readInt(), (AppearanceRegion[]) parcel.createTypedArray(AppearanceRegion.CREATOR), parcel.readInt() != 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    showTransient(parcel.readInt(), parcel.createIntArray());
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    abortTransient(parcel.readInt(), parcel.createIntArray());
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    showInattentiveSleepWarning();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissInattentiveSleepWarning(parcel.readInt() != 0);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    showToast(parcel.readInt(), parcel.readString(), parcel.readStrongBinder(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readInt(), ITransientNotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideToast(parcel.readString(), parcel.readStrongBinder());
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    startTracing();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopTracing();
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    suppressAmbientDisplay(parcel.readInt() != 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IStatusBar {
            public static IStatusBar sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setIcon(String str, StatusBarIcon statusBarIcon) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (statusBarIcon != null) {
                        parcelObtain.writeInt(1);
                        statusBarIcon.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setIcon(str, statusBarIcon);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void removeIcon(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeIcon(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void disable(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disable(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void animateExpandNotificationsPanel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().animateExpandNotificationsPanel();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void animateExpandSettingsPanel(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().animateExpandSettingsPanel(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void animateCollapsePanels() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().animateCollapsePanels();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void togglePanel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().togglePanel();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showWirelessChargingAnimation(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showWirelessChargingAnimation(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void topAppWindowChanged(int i, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().topAppWindowChanged(i, z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setImeWindowStatus(i, iBinder, i2, i3, z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setWindowState(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setWindowState(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showRecentApps(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showRecentApps(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void hideRecentApps(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideRecentApps(z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void toggleRecentApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().toggleRecentApps();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void toggleSplitScreen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().toggleSplitScreen();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void preloadRecentApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().preloadRecentApps();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void cancelPreloadRecentApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelPreloadRecentApps();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showScreenPinningRequest(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showScreenPinningRequest(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void dismissKeyboardShortcutsMenu() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dismissKeyboardShortcutsMenu();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void toggleKeyboardShortcutsMenu(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().toggleKeyboardShortcutsMenu(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionPending(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().appTransitionPending(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionCancelled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().appTransitionCancelled(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionStarting(int i, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (this.mRemote.transact(23, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().appTransitionStarting(i, j, j2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionFinished(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(24, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().appTransitionFinished(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showAssistDisclosure() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(25, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showAssistDisclosure();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void startAssist(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(26, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startAssist(bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onCameraLaunchGestureDetected(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(27, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCameraLaunchGestureDetected(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showPictureInPictureMenu() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(28, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showPictureInPictureMenu();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showGlobalActionsMenu() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(29, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showGlobalActionsMenu();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onProposedRotationChanged(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(30, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProposedRotationChanged(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setTopAppHidesStatusBar(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(31, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setTopAppHidesStatusBar(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void addQsTile(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(32, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addQsTile(componentName);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void remQsTile(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(33, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().remQsTile(componentName);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void clickQsTile(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(34, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().clickQsTile(componentName);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void handleSystemKey(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(35, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handleSystemKey(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showPinningEnterExitToast(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(36, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showPinningEnterExitToast(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showPinningEscapeToast() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(37, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showPinningEscapeToast();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showShutdownUi(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(38, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showShutdownUi(z, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2, String str, long j, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBiometricServiceReceiverInternal != null ? iBiometricServiceReceiverInternal.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(39, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showAuthenticationDialog(bundle, iBiometricServiceReceiverInternal, i, z, i2, str, j, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onBiometricAuthenticated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(40, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBiometricAuthenticated();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onBiometricHelp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(41, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBiometricHelp(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onBiometricError(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(42, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBiometricError(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void hideAuthenticationDialog() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(43, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideAuthenticationDialog();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onDisplayReady(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(44, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayReady(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onRecentsAnimationStateChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(45, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecentsAnimationStateChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onSystemBarAppearanceChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeTypedArray(appearanceRegionArr, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(46, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSystemBarAppearanceChanged(i, i2, appearanceRegionArr, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showTransient(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (this.mRemote.transact(47, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showTransient(i, iArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void abortTransient(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (this.mRemote.transact(48, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().abortTransient(i, iArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showInattentiveSleepWarning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(49, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showInattentiveSleepWarning();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void dismissInattentiveSleepWarning(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(50, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dismissInattentiveSleepWarning(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder2);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iTransientNotificationCallback != null ? iTransientNotificationCallback.asBinder() : null);
                    if (this.mRemote.transact(51, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showToast(i, str, iBinder, charSequence, iBinder2, i2, iTransientNotificationCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void hideToast(String str, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(52, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideToast(str, iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void startTracing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(53, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startTracing();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void stopTracing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(54, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopTracing();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void suppressAmbientDisplay(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(55, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().suppressAmbientDisplay(z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStatusBar iStatusBar) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iStatusBar == null) {
                return false;
            }
            Proxy.sDefaultImpl = iStatusBar;
            return true;
        }

        public static IStatusBar getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
