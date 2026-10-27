package com.android.internal.statusbar;

import android.app.Notification;
import android.content.ComponentName;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.internal.statusbar.IStatusBar;

/* JADX INFO: loaded from: classes4.dex */
public interface IStatusBarService extends IInterface {

    public static class Default implements IStatusBarService {
        @Override // com.android.internal.statusbar.IStatusBarService
        public void addTile(ComponentName componentName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void clearInlineReplyUriPermissions(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void clearNotificationEffects() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void clickTile(ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void collapsePanels() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void disable(int i, IBinder iBinder, String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void disable2(int i, IBinder iBinder, String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void disable2ForUser(int i, IBinder iBinder, String str, int i2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void disableForUser(int i, IBinder iBinder, String str, int i2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void dismissInattentiveSleepWarning(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void expandNotificationsPanel() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void expandSettingsPanel(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public int[] getDisableFlags(IBinder iBinder, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void grantInlineReplyUriPermission(String str, Uri uri, UserHandle userHandle, String str2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void handleSystemKey(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void hideAuthenticationDialog() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void hideCurrentInputMethodForBubbles() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public boolean isTracing() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onBiometricAuthenticated() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onBiometricError(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onBiometricHelp(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onBubbleNotificationSuppressionChanged(String str, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onClearAllNotifications(int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onGlobalActionsHidden() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onGlobalActionsShown() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationActionClick(String str, int i, Notification.Action action, NotificationVisibility notificationVisibility, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationBubbleChanged(String str, boolean z, int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationClear(String str, String str2, int i, int i2, String str3, int i3, int i4, NotificationVisibility notificationVisibility) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationClick(String str, NotificationVisibility notificationVisibility) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationDirectReplied(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationError(String str, String str2, int i, int i2, int i3, String str3, int i4) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationExpansionChanged(String str, boolean z, boolean z2, int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationSettingsViewed(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationSmartReplySent(String str, int i, CharSequence charSequence, int i2, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationSmartSuggestionsAdded(String str, int i, int i2, boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onNotificationVisibilityChanged(NotificationVisibility[] notificationVisibilityArr, NotificationVisibility[] notificationVisibilityArr2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onPanelHidden() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void onPanelRevealed(boolean z, int i) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void reboot(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public RegisterStatusBarResult registerStatusBar(IStatusBar iStatusBar) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void remTile(ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void removeIcon(String str) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void setIcon(String str, String str2, int i, int i2, String str3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void setIconVisibility(String str, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2, String str, long j, int i3) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void showInattentiveSleepWarning() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void showPinningEnterExitToast(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void showPinningEscapeToast() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void shutdown() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void startTracing() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void stopTracing() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void suppressAmbientDisplay(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBarService
        public void togglePanel() throws RemoteException {
        }
    }

    void addTile(ComponentName componentName) throws RemoteException;

    void clearInlineReplyUriPermissions(String str) throws RemoteException;

    void clearNotificationEffects() throws RemoteException;

    void clickTile(ComponentName componentName) throws RemoteException;

    void collapsePanels() throws RemoteException;

    void disable(int i, IBinder iBinder, String str) throws RemoteException;

    void disable2(int i, IBinder iBinder, String str) throws RemoteException;

    void disable2ForUser(int i, IBinder iBinder, String str, int i2) throws RemoteException;

    void disableForUser(int i, IBinder iBinder, String str, int i2) throws RemoteException;

    void dismissInattentiveSleepWarning(boolean z) throws RemoteException;

    void expandNotificationsPanel() throws RemoteException;

    void expandSettingsPanel(String str) throws RemoteException;

    int[] getDisableFlags(IBinder iBinder, int i) throws RemoteException;

    void grantInlineReplyUriPermission(String str, Uri uri, UserHandle userHandle, String str2) throws RemoteException;

    void handleSystemKey(int i) throws RemoteException;

    void hideAuthenticationDialog() throws RemoteException;

    void hideCurrentInputMethodForBubbles() throws RemoteException;

    boolean isTracing() throws RemoteException;

    void onBiometricAuthenticated() throws RemoteException;

    void onBiometricError(int i, int i2, int i3) throws RemoteException;

    void onBiometricHelp(String str) throws RemoteException;

    void onBubbleNotificationSuppressionChanged(String str, boolean z) throws RemoteException;

    void onClearAllNotifications(int i) throws RemoteException;

    void onGlobalActionsHidden() throws RemoteException;

    void onGlobalActionsShown() throws RemoteException;

    void onNotificationActionClick(String str, int i, Notification.Action action, NotificationVisibility notificationVisibility, boolean z) throws RemoteException;

    void onNotificationBubbleChanged(String str, boolean z, int i) throws RemoteException;

    void onNotificationClear(String str, String str2, int i, int i2, String str3, int i3, int i4, NotificationVisibility notificationVisibility) throws RemoteException;

    void onNotificationClick(String str, NotificationVisibility notificationVisibility) throws RemoteException;

    void onNotificationDirectReplied(String str) throws RemoteException;

    void onNotificationError(String str, String str2, int i, int i2, int i3, String str3, int i4) throws RemoteException;

    void onNotificationExpansionChanged(String str, boolean z, boolean z2, int i) throws RemoteException;

    void onNotificationSettingsViewed(String str) throws RemoteException;

    void onNotificationSmartReplySent(String str, int i, CharSequence charSequence, int i2, boolean z) throws RemoteException;

    void onNotificationSmartSuggestionsAdded(String str, int i, int i2, boolean z, boolean z2) throws RemoteException;

    void onNotificationVisibilityChanged(NotificationVisibility[] notificationVisibilityArr, NotificationVisibility[] notificationVisibilityArr2) throws RemoteException;

    void onPanelHidden() throws RemoteException;

    void onPanelRevealed(boolean z, int i) throws RemoteException;

    void reboot(boolean z) throws RemoteException;

    RegisterStatusBarResult registerStatusBar(IStatusBar iStatusBar) throws RemoteException;

    void remTile(ComponentName componentName) throws RemoteException;

    void removeIcon(String str) throws RemoteException;

    void setIcon(String str, String str2, int i, int i2, String str3) throws RemoteException;

    void setIconVisibility(String str, boolean z) throws RemoteException;

    void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws RemoteException;

    void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2, String str, long j, int i3) throws RemoteException;

    void showInattentiveSleepWarning() throws RemoteException;

    void showPinningEnterExitToast(boolean z) throws RemoteException;

    void showPinningEscapeToast() throws RemoteException;

    void shutdown() throws RemoteException;

    void startTracing() throws RemoteException;

    void stopTracing() throws RemoteException;

    void suppressAmbientDisplay(boolean z) throws RemoteException;

    void togglePanel() throws RemoteException;

    public static abstract class Stub extends Binder implements IStatusBarService {
        private static final String DESCRIPTOR = "com.android.internal.statusbar.IStatusBarService";
        static final int TRANSACTION_addTile = 38;
        static final int TRANSACTION_clearInlineReplyUriPermissions = 33;
        static final int TRANSACTION_clearNotificationEffects = 17;
        static final int TRANSACTION_clickTile = 40;
        static final int TRANSACTION_collapsePanels = 2;
        static final int TRANSACTION_disable = 4;
        static final int TRANSACTION_disable2 = 6;
        static final int TRANSACTION_disable2ForUser = 7;
        static final int TRANSACTION_disableForUser = 5;
        static final int TRANSACTION_dismissInattentiveSleepWarning = 50;
        static final int TRANSACTION_expandNotificationsPanel = 1;
        static final int TRANSACTION_expandSettingsPanel = 13;
        static final int TRANSACTION_getDisableFlags = 8;
        static final int TRANSACTION_grantInlineReplyUriPermission = 32;
        static final int TRANSACTION_handleSystemKey = 41;
        static final int TRANSACTION_hideAuthenticationDialog = 48;
        static final int TRANSACTION_hideCurrentInputMethodForBubbles = 31;
        static final int TRANSACTION_isTracing = 53;
        static final int TRANSACTION_onBiometricAuthenticated = 45;
        static final int TRANSACTION_onBiometricError = 47;
        static final int TRANSACTION_onBiometricHelp = 46;
        static final int TRANSACTION_onBubbleNotificationSuppressionChanged = 30;
        static final int TRANSACTION_onClearAllNotifications = 21;
        static final int TRANSACTION_onGlobalActionsHidden = 35;
        static final int TRANSACTION_onGlobalActionsShown = 34;
        static final int TRANSACTION_onNotificationActionClick = 19;
        static final int TRANSACTION_onNotificationBubbleChanged = 29;
        static final int TRANSACTION_onNotificationClear = 22;
        static final int TRANSACTION_onNotificationClick = 18;
        static final int TRANSACTION_onNotificationDirectReplied = 25;
        static final int TRANSACTION_onNotificationError = 20;
        static final int TRANSACTION_onNotificationExpansionChanged = 24;
        static final int TRANSACTION_onNotificationSettingsViewed = 28;
        static final int TRANSACTION_onNotificationSmartReplySent = 27;
        static final int TRANSACTION_onNotificationSmartSuggestionsAdded = 26;
        static final int TRANSACTION_onNotificationVisibilityChanged = 23;
        static final int TRANSACTION_onPanelHidden = 16;
        static final int TRANSACTION_onPanelRevealed = 15;
        static final int TRANSACTION_reboot = 37;
        static final int TRANSACTION_registerStatusBar = 14;
        static final int TRANSACTION_remTile = 39;
        static final int TRANSACTION_removeIcon = 11;
        static final int TRANSACTION_setIcon = 9;
        static final int TRANSACTION_setIconVisibility = 10;
        static final int TRANSACTION_setImeWindowStatus = 12;
        static final int TRANSACTION_showAuthenticationDialog = 44;
        static final int TRANSACTION_showInattentiveSleepWarning = 49;
        static final int TRANSACTION_showPinningEnterExitToast = 42;
        static final int TRANSACTION_showPinningEscapeToast = 43;
        static final int TRANSACTION_shutdown = 36;
        static final int TRANSACTION_startTracing = 51;
        static final int TRANSACTION_stopTracing = 52;
        static final int TRANSACTION_suppressAmbientDisplay = 54;
        static final int TRANSACTION_togglePanel = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "expandNotificationsPanel";
                case 2:
                    return "collapsePanels";
                case 3:
                    return "togglePanel";
                case 4:
                    return "disable";
                case 5:
                    return "disableForUser";
                case 6:
                    return "disable2";
                case 7:
                    return "disable2ForUser";
                case 8:
                    return "getDisableFlags";
                case 9:
                    return "setIcon";
                case 10:
                    return "setIconVisibility";
                case 11:
                    return "removeIcon";
                case 12:
                    return "setImeWindowStatus";
                case 13:
                    return "expandSettingsPanel";
                case 14:
                    return "registerStatusBar";
                case 15:
                    return "onPanelRevealed";
                case 16:
                    return "onPanelHidden";
                case 17:
                    return "clearNotificationEffects";
                case 18:
                    return "onNotificationClick";
                case 19:
                    return "onNotificationActionClick";
                case 20:
                    return "onNotificationError";
                case 21:
                    return "onClearAllNotifications";
                case 22:
                    return "onNotificationClear";
                case 23:
                    return "onNotificationVisibilityChanged";
                case 24:
                    return "onNotificationExpansionChanged";
                case 25:
                    return "onNotificationDirectReplied";
                case 26:
                    return "onNotificationSmartSuggestionsAdded";
                case 27:
                    return "onNotificationSmartReplySent";
                case 28:
                    return "onNotificationSettingsViewed";
                case 29:
                    return "onNotificationBubbleChanged";
                case 30:
                    return "onBubbleNotificationSuppressionChanged";
                case 31:
                    return "hideCurrentInputMethodForBubbles";
                case 32:
                    return "grantInlineReplyUriPermission";
                case 33:
                    return "clearInlineReplyUriPermissions";
                case 34:
                    return "onGlobalActionsShown";
                case 35:
                    return "onGlobalActionsHidden";
                case 36:
                    return "shutdown";
                case 37:
                    return "reboot";
                case 38:
                    return "addTile";
                case 39:
                    return "remTile";
                case 40:
                    return "clickTile";
                case 41:
                    return "handleSystemKey";
                case 42:
                    return "showPinningEnterExitToast";
                case 43:
                    return "showPinningEscapeToast";
                case 44:
                    return "showAuthenticationDialog";
                case 45:
                    return "onBiometricAuthenticated";
                case 46:
                    return "onBiometricHelp";
                case 47:
                    return "onBiometricError";
                case 48:
                    return "hideAuthenticationDialog";
                case 49:
                    return "showInattentiveSleepWarning";
                case 50:
                    return "dismissInattentiveSleepWarning";
                case 51:
                    return "startTracing";
                case 52:
                    return "stopTracing";
                case 53:
                    return "isTracing";
                case 54:
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

        public static IStatusBarService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStatusBarService)) {
                return (IStatusBarService) iInterfaceQueryLocalInterface;
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
                    expandNotificationsPanel();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    collapsePanels();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    togglePanel();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    disable(parcel.readInt(), parcel.readStrongBinder(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableForUser(parcel.readInt(), parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    disable2(parcel.readInt(), parcel.readStrongBinder(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    disable2ForUser(parcel.readInt(), parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] disableFlags = getDisableFlags(parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(disableFlags);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIcon(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIconVisibility(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeIcon(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setImeWindowStatus(parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    expandSettingsPanel(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    RegisterStatusBarResult registerStatusBarResultRegisterStatusBar = registerStatusBar(IStatusBar.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (registerStatusBarResultRegisterStatusBar != null) {
                        parcel2.writeInt(1);
                        registerStatusBarResultRegisterStatusBar.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPanelRevealed(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPanelHidden();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearNotificationEffects();
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationClick(parcel.readString(), parcel.readInt() != 0 ? NotificationVisibility.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationActionClick(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? Notification.Action.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NotificationVisibility.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationError(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    onClearAllNotifications(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationClear(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? NotificationVisibility.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationVisibilityChanged((NotificationVisibility[]) parcel.createTypedArray(NotificationVisibility.CREATOR), (NotificationVisibility[]) parcel.createTypedArray(NotificationVisibility.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationExpansionChanged(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationDirectReplied(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationSmartSuggestionsAdded(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationSmartReplySent(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationSettingsViewed(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationBubbleChanged(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBubbleNotificationSuppressionChanged(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideCurrentInputMethodForBubbles();
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantInlineReplyUriPermission(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearInlineReplyUriPermissions(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    onGlobalActionsShown();
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    onGlobalActionsHidden();
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    shutdown();
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    reboot(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    addTile(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    remTile(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    clickTile(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    handleSystemKey(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    showPinningEnterExitToast(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    showPinningEscapeToast();
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    showAuthenticationDialog(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, IBiometricServiceReceiverInternal.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBiometricAuthenticated();
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBiometricHelp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBiometricError(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideAuthenticationDialog();
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    showInattentiveSleepWarning();
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissInattentiveSleepWarning(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    startTracing();
                    parcel2.writeNoException();
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopTracing();
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTracing = isTracing();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTracing ? 1 : 0);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    suppressAmbientDisplay(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IStatusBarService {
            public static IStatusBarService sDefaultImpl;
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

            @Override // com.android.internal.statusbar.IStatusBarService
            public void expandNotificationsPanel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().expandNotificationsPanel();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void collapsePanels() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().collapsePanels();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void togglePanel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().togglePanel();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void disable(int i, IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disable(i, iBinder, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void disableForUser(int i, IBinder iBinder, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableForUser(i, iBinder, str, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void disable2(int i, IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disable2(i, iBinder, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void disable2ForUser(int i, IBinder iBinder, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disable2ForUser(i, iBinder, str, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public int[] getDisableFlags(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisableFlags(iBinder, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void setIcon(String str, String str2, int i, int i2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIcon(str, str2, i, i2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void setIconVisibility(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIconVisibility(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void removeIcon(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeIcon(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    int i4 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i4 = 0;
                    }
                    parcelObtain.writeInt(i4);
                    try {
                        if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().setImeWindowStatus(i, iBinder, i2, i3, z, z2);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void expandSettingsPanel(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().expandSettingsPanel(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public RegisterStatusBarResult registerStatusBar(IStatusBar iStatusBar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStatusBar != null ? iStatusBar.asBinder() : null);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerStatusBar(iStatusBar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RegisterStatusBarResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onPanelRevealed(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanelRevealed(z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onPanelHidden() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPanelHidden();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void clearNotificationEffects() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearNotificationEffects();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationClick(String str, NotificationVisibility notificationVisibility) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (notificationVisibility != null) {
                        parcelObtain.writeInt(1);
                        notificationVisibility.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationClick(str, notificationVisibility);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationActionClick(String str, int i, Notification.Action action, NotificationVisibility notificationVisibility, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    int i2 = 1;
                    if (action != null) {
                        parcelObtain.writeInt(1);
                        action.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (notificationVisibility != null) {
                        parcelObtain.writeInt(1);
                        notificationVisibility.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationActionClick(str, i, action, notificationVisibility, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationError(String str, String str2, int i, int i2, int i3, String str3, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationError(str, str2, i, i2, i3, str3, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onClearAllNotifications(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClearAllNotifications(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationClear(String str, String str2, int i, int i2, String str3, int i3, int i4, NotificationVisibility notificationVisibility) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (notificationVisibility != null) {
                        parcelObtain.writeInt(1);
                        notificationVisibility.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationClear(str, str2, i, i2, str3, i3, i4, notificationVisibility);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationVisibilityChanged(NotificationVisibility[] notificationVisibilityArr, NotificationVisibility[] notificationVisibilityArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(notificationVisibilityArr, 0);
                    parcelObtain.writeTypedArray(notificationVisibilityArr2, 0);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationVisibilityChanged(notificationVisibilityArr, notificationVisibilityArr2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationExpansionChanged(String str, boolean z, boolean z2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    int i2 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationExpansionChanged(str, z, z2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationDirectReplied(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationDirectReplied(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationSmartSuggestionsAdded(String str, int i, int i2, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    int i3 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i3 = 0;
                    }
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationSmartSuggestionsAdded(str, i, i2, z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationSmartReplySent(String str, int i, CharSequence charSequence, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    int i3 = 1;
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!z) {
                        i3 = 0;
                    }
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationSmartReplySent(str, i, charSequence, i2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationSettingsViewed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationSettingsViewed(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onNotificationBubbleChanged(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationBubbleChanged(str, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onBubbleNotificationSuppressionChanged(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBubbleNotificationSuppressionChanged(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void hideCurrentInputMethodForBubbles() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideCurrentInputMethodForBubbles();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void grantInlineReplyUriPermission(String str, Uri uri, UserHandle userHandle, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantInlineReplyUriPermission(str, uri, userHandle, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void clearInlineReplyUriPermissions(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearInlineReplyUriPermissions(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onGlobalActionsShown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGlobalActionsShown();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onGlobalActionsHidden() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGlobalActionsHidden();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void shutdown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().shutdown();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void reboot(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reboot(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void addTile(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addTile(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void remTile(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().remTile(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void clickTile(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clickTile(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void handleSystemKey(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleSystemKey(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void showPinningEnterExitToast(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showPinningEnterExitToast(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void showPinningEscapeToast() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showPinningEscapeToast();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void showAuthenticationDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2, String str, long j, int i3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i4 = 1;
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBiometricServiceReceiverInternal != null ? iBiometricServiceReceiverInternal.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!z) {
                        i4 = 0;
                    }
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i3);
                    try {
                        if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().showAuthenticationDialog(bundle, iBiometricServiceReceiverInternal, i, z, i2, str, j, i3);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onBiometricAuthenticated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBiometricAuthenticated();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onBiometricHelp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBiometricHelp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void onBiometricError(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBiometricError(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void hideAuthenticationDialog() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideAuthenticationDialog();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void showInattentiveSleepWarning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInattentiveSleepWarning();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void dismissInattentiveSleepWarning(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissInattentiveSleepWarning(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void startTracing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startTracing();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void stopTracing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopTracing();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public boolean isTracing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTracing();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBarService
            public void suppressAmbientDisplay(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suppressAmbientDisplay(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStatusBarService iStatusBarService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iStatusBarService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iStatusBarService;
            return true;
        }

        public static IStatusBarService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
