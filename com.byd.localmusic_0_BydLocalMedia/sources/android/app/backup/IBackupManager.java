package android.app.backup;

import android.app.backup.IBackupManagerMonitor;
import android.app.backup.IBackupObserver;
import android.app.backup.IFullBackupRestoreObserver;
import android.app.backup.IRestoreSession;
import android.app.backup.ISelectBackupTransportCallback;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBackupManager extends IInterface {

    public static class Default implements IBackupManager {
        @Override // android.app.backup.IBackupManager
        public void acknowledgeFullBackupOrRestore(int i, boolean z, String str, String str2, IFullBackupRestoreObserver iFullBackupRestoreObserver) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void acknowledgeFullBackupOrRestoreForUser(int i, int i2, boolean z, String str, String str2, IFullBackupRestoreObserver iFullBackupRestoreObserver) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void adbBackup(int i, ParcelFileDescriptor parcelFileDescriptor, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String[] strArr) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void adbRestore(int i, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void agentConnected(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void agentConnectedForUser(int i, String str, IBinder iBinder) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void agentDisconnected(String str) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void agentDisconnectedForUser(int i, String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public void backupNow() throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void backupNowForUser(int i) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public IRestoreSession beginRestoreSessionForUser(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public void cancelBackups() throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void cancelBackupsForUser(int i) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void clearBackupData(String str, String str2) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void clearBackupDataForUser(int i, String str, String str2) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void dataChanged(String str) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void dataChangedForUser(int i, String str) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void excludeKeysFromRestore(String str, List<String> list) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public String[] filterAppsEligibleForBackupForUser(int i, String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public void fullTransportBackupForUser(int i, String[] strArr) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public long getAvailableRestoreTokenForUser(int i, String str) throws RemoteException {
            return 0L;
        }

        @Override // android.app.backup.IBackupManager
        public Intent getConfigurationIntent(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public Intent getConfigurationIntentForUser(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String getCurrentTransport() throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public ComponentName getCurrentTransportComponentForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String getCurrentTransportForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public Intent getDataManagementIntent(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public Intent getDataManagementIntentForUser(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public CharSequence getDataManagementLabelForUser(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String getDestinationString(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String getDestinationStringForUser(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String[] getTransportWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public UserHandle getUserForAncestralSerialNumber(long j) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public boolean hasBackupPassword() throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public void initializeTransportsForUser(int i, String[] strArr, IBackupObserver iBackupObserver) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public boolean isAppEligibleForBackupForUser(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public boolean isBackupEnabled() throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public boolean isBackupEnabledForUser(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public boolean isBackupServiceActive(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public boolean isUserReadyForBackup(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public ComponentName[] listAllTransportComponentsForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String[] listAllTransports() throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public String[] listAllTransportsForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public void opComplete(int i, long j) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void opCompleteForUser(int i, int i2, long j) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public int requestBackup(String[] strArr, IBackupObserver iBackupObserver, IBackupManagerMonitor iBackupManagerMonitor, int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.backup.IBackupManager
        public int requestBackupForUser(int i, String[] strArr, IBackupObserver iBackupObserver, IBackupManagerMonitor iBackupManagerMonitor, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.app.backup.IBackupManager
        public void restoreAtInstall(String str, int i) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void restoreAtInstallForUser(int i, String str, int i2) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public String selectBackupTransport(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public void selectBackupTransportAsyncForUser(int i, ComponentName componentName, ISelectBackupTransportCallback iSelectBackupTransportCallback) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public String selectBackupTransportForUser(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.backup.IBackupManager
        public void setAncestralSerialNumber(long j) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void setAutoRestore(boolean z) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void setAutoRestoreForUser(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void setBackupEnabled(boolean z) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void setBackupEnabledForUser(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public boolean setBackupPassword(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.app.backup.IBackupManager
        public void setBackupServiceActive(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.backup.IBackupManager
        public void updateTransportAttributesForUser(int i, ComponentName componentName, String str, Intent intent, String str2, Intent intent2, CharSequence charSequence) throws RemoteException {
        }
    }

    void acknowledgeFullBackupOrRestore(int i, boolean z, String str, String str2, IFullBackupRestoreObserver iFullBackupRestoreObserver) throws RemoteException;

    void acknowledgeFullBackupOrRestoreForUser(int i, int i2, boolean z, String str, String str2, IFullBackupRestoreObserver iFullBackupRestoreObserver) throws RemoteException;

    void adbBackup(int i, ParcelFileDescriptor parcelFileDescriptor, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String[] strArr) throws RemoteException;

    void adbRestore(int i, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void agentConnected(String str, IBinder iBinder) throws RemoteException;

    void agentConnectedForUser(int i, String str, IBinder iBinder) throws RemoteException;

    void agentDisconnected(String str) throws RemoteException;

    void agentDisconnectedForUser(int i, String str) throws RemoteException;

    void backupNow() throws RemoteException;

    void backupNowForUser(int i) throws RemoteException;

    IRestoreSession beginRestoreSessionForUser(int i, String str, String str2) throws RemoteException;

    void cancelBackups() throws RemoteException;

    void cancelBackupsForUser(int i) throws RemoteException;

    void clearBackupData(String str, String str2) throws RemoteException;

    void clearBackupDataForUser(int i, String str, String str2) throws RemoteException;

    void dataChanged(String str) throws RemoteException;

    void dataChangedForUser(int i, String str) throws RemoteException;

    void excludeKeysFromRestore(String str, List<String> list) throws RemoteException;

    String[] filterAppsEligibleForBackupForUser(int i, String[] strArr) throws RemoteException;

    void fullTransportBackupForUser(int i, String[] strArr) throws RemoteException;

    long getAvailableRestoreTokenForUser(int i, String str) throws RemoteException;

    Intent getConfigurationIntent(String str) throws RemoteException;

    Intent getConfigurationIntentForUser(int i, String str) throws RemoteException;

    String getCurrentTransport() throws RemoteException;

    ComponentName getCurrentTransportComponentForUser(int i) throws RemoteException;

    String getCurrentTransportForUser(int i) throws RemoteException;

    Intent getDataManagementIntent(String str) throws RemoteException;

    Intent getDataManagementIntentForUser(int i, String str) throws RemoteException;

    CharSequence getDataManagementLabelForUser(int i, String str) throws RemoteException;

    String getDestinationString(String str) throws RemoteException;

    String getDestinationStringForUser(int i, String str) throws RemoteException;

    String[] getTransportWhitelist() throws RemoteException;

    UserHandle getUserForAncestralSerialNumber(long j) throws RemoteException;

    boolean hasBackupPassword() throws RemoteException;

    void initializeTransportsForUser(int i, String[] strArr, IBackupObserver iBackupObserver) throws RemoteException;

    boolean isAppEligibleForBackupForUser(int i, String str) throws RemoteException;

    boolean isBackupEnabled() throws RemoteException;

    boolean isBackupEnabledForUser(int i) throws RemoteException;

    boolean isBackupServiceActive(int i) throws RemoteException;

    boolean isUserReadyForBackup(int i) throws RemoteException;

    ComponentName[] listAllTransportComponentsForUser(int i) throws RemoteException;

    String[] listAllTransports() throws RemoteException;

    String[] listAllTransportsForUser(int i) throws RemoteException;

    void opComplete(int i, long j) throws RemoteException;

    void opCompleteForUser(int i, int i2, long j) throws RemoteException;

    int requestBackup(String[] strArr, IBackupObserver iBackupObserver, IBackupManagerMonitor iBackupManagerMonitor, int i) throws RemoteException;

    int requestBackupForUser(int i, String[] strArr, IBackupObserver iBackupObserver, IBackupManagerMonitor iBackupManagerMonitor, int i2) throws RemoteException;

    void restoreAtInstall(String str, int i) throws RemoteException;

    void restoreAtInstallForUser(int i, String str, int i2) throws RemoteException;

    String selectBackupTransport(String str) throws RemoteException;

    void selectBackupTransportAsyncForUser(int i, ComponentName componentName, ISelectBackupTransportCallback iSelectBackupTransportCallback) throws RemoteException;

    String selectBackupTransportForUser(int i, String str) throws RemoteException;

    void setAncestralSerialNumber(long j) throws RemoteException;

    void setAutoRestore(boolean z) throws RemoteException;

    void setAutoRestoreForUser(int i, boolean z) throws RemoteException;

    void setBackupEnabled(boolean z) throws RemoteException;

    void setBackupEnabledForUser(int i, boolean z) throws RemoteException;

    boolean setBackupPassword(String str, String str2) throws RemoteException;

    void setBackupServiceActive(int i, boolean z) throws RemoteException;

    void updateTransportAttributesForUser(int i, ComponentName componentName, String str, Intent intent, String str2, Intent intent2, CharSequence charSequence) throws RemoteException;

    public static abstract class Stub extends Binder implements IBackupManager {
        private static final String DESCRIPTOR = "android.app.backup.IBackupManager";
        static final int TRANSACTION_acknowledgeFullBackupOrRestore = 26;
        static final int TRANSACTION_acknowledgeFullBackupOrRestoreForUser = 25;
        static final int TRANSACTION_adbBackup = 22;
        static final int TRANSACTION_adbRestore = 24;
        static final int TRANSACTION_agentConnected = 7;
        static final int TRANSACTION_agentConnectedForUser = 6;
        static final int TRANSACTION_agentDisconnected = 9;
        static final int TRANSACTION_agentDisconnectedForUser = 8;
        static final int TRANSACTION_backupNow = 21;
        static final int TRANSACTION_backupNowForUser = 20;
        static final int TRANSACTION_beginRestoreSessionForUser = 45;
        static final int TRANSACTION_cancelBackups = 57;
        static final int TRANSACTION_cancelBackupsForUser = 56;
        static final int TRANSACTION_clearBackupData = 4;
        static final int TRANSACTION_clearBackupDataForUser = 3;
        static final int TRANSACTION_dataChanged = 2;
        static final int TRANSACTION_dataChangedForUser = 1;
        static final int TRANSACTION_excludeKeysFromRestore = 60;
        static final int TRANSACTION_filterAppsEligibleForBackupForUser = 53;
        static final int TRANSACTION_fullTransportBackupForUser = 23;
        static final int TRANSACTION_getAvailableRestoreTokenForUser = 51;
        static final int TRANSACTION_getConfigurationIntent = 39;
        static final int TRANSACTION_getConfigurationIntentForUser = 38;
        static final int TRANSACTION_getCurrentTransport = 29;
        static final int TRANSACTION_getCurrentTransportComponentForUser = 30;
        static final int TRANSACTION_getCurrentTransportForUser = 28;
        static final int TRANSACTION_getDataManagementIntent = 43;
        static final int TRANSACTION_getDataManagementIntentForUser = 42;
        static final int TRANSACTION_getDataManagementLabelForUser = 44;
        static final int TRANSACTION_getDestinationString = 41;
        static final int TRANSACTION_getDestinationStringForUser = 40;
        static final int TRANSACTION_getTransportWhitelist = 34;
        static final int TRANSACTION_getUserForAncestralSerialNumber = 58;
        static final int TRANSACTION_hasBackupPassword = 19;
        static final int TRANSACTION_initializeTransportsForUser = 5;
        static final int TRANSACTION_isAppEligibleForBackupForUser = 52;
        static final int TRANSACTION_isBackupEnabled = 17;
        static final int TRANSACTION_isBackupEnabledForUser = 16;
        static final int TRANSACTION_isBackupServiceActive = 49;
        static final int TRANSACTION_isUserReadyForBackup = 50;
        static final int TRANSACTION_listAllTransportComponentsForUser = 33;
        static final int TRANSACTION_listAllTransports = 32;
        static final int TRANSACTION_listAllTransportsForUser = 31;
        static final int TRANSACTION_opComplete = 47;
        static final int TRANSACTION_opCompleteForUser = 46;
        static final int TRANSACTION_requestBackup = 55;
        static final int TRANSACTION_requestBackupForUser = 54;
        static final int TRANSACTION_restoreAtInstall = 11;
        static final int TRANSACTION_restoreAtInstallForUser = 10;
        static final int TRANSACTION_selectBackupTransport = 36;
        static final int TRANSACTION_selectBackupTransportAsyncForUser = 37;
        static final int TRANSACTION_selectBackupTransportForUser = 35;
        static final int TRANSACTION_setAncestralSerialNumber = 59;
        static final int TRANSACTION_setAutoRestore = 15;
        static final int TRANSACTION_setAutoRestoreForUser = 14;
        static final int TRANSACTION_setBackupEnabled = 13;
        static final int TRANSACTION_setBackupEnabledForUser = 12;
        static final int TRANSACTION_setBackupPassword = 18;
        static final int TRANSACTION_setBackupServiceActive = 48;
        static final int TRANSACTION_updateTransportAttributesForUser = 27;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "dataChangedForUser";
                case 2:
                    return "dataChanged";
                case 3:
                    return "clearBackupDataForUser";
                case 4:
                    return "clearBackupData";
                case 5:
                    return "initializeTransportsForUser";
                case 6:
                    return "agentConnectedForUser";
                case 7:
                    return "agentConnected";
                case 8:
                    return "agentDisconnectedForUser";
                case 9:
                    return "agentDisconnected";
                case 10:
                    return "restoreAtInstallForUser";
                case 11:
                    return "restoreAtInstall";
                case 12:
                    return "setBackupEnabledForUser";
                case 13:
                    return "setBackupEnabled";
                case 14:
                    return "setAutoRestoreForUser";
                case 15:
                    return "setAutoRestore";
                case 16:
                    return "isBackupEnabledForUser";
                case 17:
                    return "isBackupEnabled";
                case 18:
                    return "setBackupPassword";
                case 19:
                    return "hasBackupPassword";
                case 20:
                    return "backupNowForUser";
                case 21:
                    return "backupNow";
                case 22:
                    return "adbBackup";
                case 23:
                    return "fullTransportBackupForUser";
                case 24:
                    return "adbRestore";
                case 25:
                    return "acknowledgeFullBackupOrRestoreForUser";
                case 26:
                    return "acknowledgeFullBackupOrRestore";
                case 27:
                    return "updateTransportAttributesForUser";
                case 28:
                    return "getCurrentTransportForUser";
                case 29:
                    return "getCurrentTransport";
                case 30:
                    return "getCurrentTransportComponentForUser";
                case 31:
                    return "listAllTransportsForUser";
                case 32:
                    return "listAllTransports";
                case 33:
                    return "listAllTransportComponentsForUser";
                case 34:
                    return "getTransportWhitelist";
                case 35:
                    return "selectBackupTransportForUser";
                case 36:
                    return "selectBackupTransport";
                case 37:
                    return "selectBackupTransportAsyncForUser";
                case 38:
                    return "getConfigurationIntentForUser";
                case 39:
                    return "getConfigurationIntent";
                case 40:
                    return "getDestinationStringForUser";
                case 41:
                    return "getDestinationString";
                case 42:
                    return "getDataManagementIntentForUser";
                case 43:
                    return "getDataManagementIntent";
                case 44:
                    return "getDataManagementLabelForUser";
                case 45:
                    return "beginRestoreSessionForUser";
                case 46:
                    return "opCompleteForUser";
                case 47:
                    return "opComplete";
                case 48:
                    return "setBackupServiceActive";
                case 49:
                    return "isBackupServiceActive";
                case 50:
                    return "isUserReadyForBackup";
                case 51:
                    return "getAvailableRestoreTokenForUser";
                case 52:
                    return "isAppEligibleForBackupForUser";
                case 53:
                    return "filterAppsEligibleForBackupForUser";
                case 54:
                    return "requestBackupForUser";
                case 55:
                    return "requestBackup";
                case 56:
                    return "cancelBackupsForUser";
                case 57:
                    return "cancelBackups";
                case 58:
                    return "getUserForAncestralSerialNumber";
                case 59:
                    return "setAncestralSerialNumber";
                case 60:
                    return "excludeKeysFromRestore";
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

        public static IBackupManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBackupManager)) {
                return (IBackupManager) iInterfaceQueryLocalInterface;
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
                    dataChangedForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    dataChanged(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearBackupDataForUser(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearBackupData(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    initializeTransportsForUser(parcel.readInt(), parcel.createStringArray(), IBackupObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    agentConnectedForUser(parcel.readInt(), parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    agentConnected(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    agentDisconnectedForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    agentDisconnected(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreAtInstallForUser(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreAtInstall(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBackupEnabledForUser(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBackupEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAutoRestoreForUser(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAutoRestore(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBackupEnabledForUser = isBackupEnabledForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBackupEnabledForUser ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBackupEnabled = isBackupEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBackupEnabled ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean backupPassword = setBackupPassword(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(backupPassword ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasBackupPassword = hasBackupPassword();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasBackupPassword ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    backupNowForUser(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    backupNow();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    adbBackup(parcel.readInt(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    fullTransportBackupForUser(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    adbRestore(parcel.readInt(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    acknowledgeFullBackupOrRestoreForUser(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readString(), parcel.readString(), IFullBackupRestoreObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    acknowledgeFullBackupOrRestore(parcel.readInt(), parcel.readInt() != 0, parcel.readString(), parcel.readString(), IFullBackupRestoreObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateTransportAttributesForUser(parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentTransportForUser = getCurrentTransportForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(currentTransportForUser);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentTransport = getCurrentTransport();
                    parcel2.writeNoException();
                    parcel2.writeString(currentTransport);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName currentTransportComponentForUser = getCurrentTransportComponentForUser(parcel.readInt());
                    parcel2.writeNoException();
                    if (currentTransportComponentForUser != null) {
                        parcel2.writeInt(1);
                        currentTransportComponentForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrListAllTransportsForUser = listAllTransportsForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrListAllTransportsForUser);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrListAllTransports = listAllTransports();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrListAllTransports);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName[] componentNameArrListAllTransportComponentsForUser = listAllTransportComponentsForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(componentNameArrListAllTransportComponentsForUser, 1);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] transportWhitelist = getTransportWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(transportWhitelist);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strSelectBackupTransportForUser = selectBackupTransportForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strSelectBackupTransportForUser);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strSelectBackupTransport = selectBackupTransport(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strSelectBackupTransport);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    selectBackupTransportAsyncForUser(parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, ISelectBackupTransportCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    Intent configurationIntentForUser = getConfigurationIntentForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (configurationIntentForUser != null) {
                        parcel2.writeInt(1);
                        configurationIntentForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    Intent configurationIntent = getConfigurationIntent(parcel.readString());
                    parcel2.writeNoException();
                    if (configurationIntent != null) {
                        parcel2.writeInt(1);
                        configurationIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    String destinationStringForUser = getDestinationStringForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(destinationStringForUser);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    String destinationString = getDestinationString(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(destinationString);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    Intent dataManagementIntentForUser = getDataManagementIntentForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (dataManagementIntentForUser != null) {
                        parcel2.writeInt(1);
                        dataManagementIntentForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    Intent dataManagementIntent = getDataManagementIntent(parcel.readString());
                    parcel2.writeNoException();
                    if (dataManagementIntent != null) {
                        parcel2.writeInt(1);
                        dataManagementIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    CharSequence dataManagementLabelForUser = getDataManagementLabelForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (dataManagementLabelForUser != null) {
                        parcel2.writeInt(1);
                        TextUtils.writeToParcel(dataManagementLabelForUser, parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    IRestoreSession iRestoreSessionBeginRestoreSessionForUser = beginRestoreSessionForUser(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iRestoreSessionBeginRestoreSessionForUser != null ? iRestoreSessionBeginRestoreSessionForUser.asBinder() : null);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    opCompleteForUser(parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    opComplete(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBackupServiceActive(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBackupServiceActive = isBackupServiceActive(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBackupServiceActive ? 1 : 0);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserReadyForBackup = isUserReadyForBackup(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserReadyForBackup ? 1 : 0);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    long availableRestoreTokenForUser = getAvailableRestoreTokenForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(availableRestoreTokenForUser);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAppEligibleForBackupForUser = isAppEligibleForBackupForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAppEligibleForBackupForUser ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrFilterAppsEligibleForBackupForUser = filterAppsEligibleForBackupForUser(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrFilterAppsEligibleForBackupForUser);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRequestBackupForUser = requestBackupForUser(parcel.readInt(), parcel.createStringArray(), IBackupObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRequestBackupForUser);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRequestBackup = requestBackup(parcel.createStringArray(), IBackupObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRequestBackup);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelBackupsForUser(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelBackups();
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserHandle userForAncestralSerialNumber = getUserForAncestralSerialNumber(parcel.readLong());
                    parcel2.writeNoException();
                    if (userForAncestralSerialNumber != null) {
                        parcel2.writeInt(1);
                        userForAncestralSerialNumber.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAncestralSerialNumber(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    excludeKeysFromRestore(parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBackupManager {
            public static IBackupManager sDefaultImpl;
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

            @Override // android.app.backup.IBackupManager
            public void dataChangedForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dataChangedForUser(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void dataChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dataChanged(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void clearBackupDataForUser(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearBackupDataForUser(i, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void clearBackupData(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearBackupData(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void initializeTransportsForUser(int i, String[] strArr, IBackupObserver iBackupObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeStrongBinder(iBackupObserver != null ? iBackupObserver.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initializeTransportsForUser(i, strArr, iBackupObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void agentConnectedForUser(int i, String str, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().agentConnectedForUser(i, str, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void agentConnected(String str, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().agentConnected(str, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void agentDisconnectedForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().agentDisconnectedForUser(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void agentDisconnected(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().agentDisconnected(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void restoreAtInstallForUser(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreAtInstallForUser(i, str, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void restoreAtInstall(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreAtInstall(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void setBackupEnabledForUser(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBackupEnabledForUser(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void setBackupEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBackupEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void setAutoRestoreForUser(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoRestoreForUser(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void setAutoRestore(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoRestore(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean isBackupEnabledForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBackupEnabledForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean isBackupEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBackupEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean setBackupPassword(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBackupPassword(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean hasBackupPassword() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasBackupPassword();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void backupNowForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().backupNowForUser(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void backupNow() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().backupNow();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void adbBackup(int i, ParcelFileDescriptor parcelFileDescriptor, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String[] strArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    int i2 = 1;
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(z3 ? 1 : 0);
                    parcelObtain.writeInt(z4 ? 1 : 0);
                    parcelObtain.writeInt(z5 ? 1 : 0);
                    parcelObtain.writeInt(z6 ? 1 : 0);
                    parcelObtain.writeInt(z7 ? 1 : 0);
                    if (!z8) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringArray(strArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adbBackup(i, parcelFileDescriptor, z, z2, z3, z4, z5, z6, z7, z8, strArr);
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

            @Override // android.app.backup.IBackupManager
            public void fullTransportBackupForUser(int i, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().fullTransportBackupForUser(i, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void adbRestore(int i, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adbRestore(i, parcelFileDescriptor);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void acknowledgeFullBackupOrRestoreForUser(int i, int i2, boolean z, String str, String str2, IFullBackupRestoreObserver iFullBackupRestoreObserver) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iFullBackupRestoreObserver != null ? iFullBackupRestoreObserver.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acknowledgeFullBackupOrRestoreForUser(i, i2, z, str, str2, iFullBackupRestoreObserver);
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

            @Override // android.app.backup.IBackupManager
            public void acknowledgeFullBackupOrRestore(int i, boolean z, String str, String str2, IFullBackupRestoreObserver iFullBackupRestoreObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iFullBackupRestoreObserver != null ? iFullBackupRestoreObserver.asBinder() : null);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acknowledgeFullBackupOrRestore(i, z, str, str2, iFullBackupRestoreObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void updateTransportAttributesForUser(int i, ComponentName componentName, String str, Intent intent, String str2, Intent intent2, CharSequence charSequence) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    if (intent2 != null) {
                        parcelObtain.writeInt(1);
                        intent2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTransportAttributesForUser(i, componentName, str, intent, str2, intent2, charSequence);
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

            @Override // android.app.backup.IBackupManager
            public String getCurrentTransportForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTransportForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String getCurrentTransport() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTransport();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public ComponentName getCurrentTransportComponentForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTransportComponentForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String[] listAllTransportsForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listAllTransportsForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String[] listAllTransports() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listAllTransports();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public ComponentName[] listAllTransportComponentsForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listAllTransportComponentsForUser(i);
                    }
                    parcelObtain2.readException();
                    return (ComponentName[]) parcelObtain2.createTypedArray(ComponentName.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String[] getTransportWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTransportWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String selectBackupTransportForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectBackupTransportForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String selectBackupTransport(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectBackupTransport(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void selectBackupTransportAsyncForUser(int i, ComponentName componentName, ISelectBackupTransportCallback iSelectBackupTransportCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iSelectBackupTransportCallback != null ? iSelectBackupTransportCallback.asBinder() : null);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectBackupTransportAsyncForUser(i, componentName, iSelectBackupTransportCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public Intent getConfigurationIntentForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigurationIntentForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public Intent getConfigurationIntent(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigurationIntent(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String getDestinationStringForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDestinationStringForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String getDestinationString(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDestinationString(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public Intent getDataManagementIntentForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataManagementIntentForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public Intent getDataManagementIntent(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataManagementIntent(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public CharSequence getDataManagementLabelForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataManagementLabelForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public IRestoreSession beginRestoreSessionForUser(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().beginRestoreSessionForUser(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return IRestoreSession.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void opCompleteForUser(int i, int i2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().opCompleteForUser(i, i2, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void opComplete(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().opComplete(i, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void setBackupServiceActive(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBackupServiceActive(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean isBackupServiceActive(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBackupServiceActive(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean isUserReadyForBackup(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserReadyForBackup(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public long getAvailableRestoreTokenForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableRestoreTokenForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public boolean isAppEligibleForBackupForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAppEligibleForBackupForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public String[] filterAppsEligibleForBackupForUser(int i, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().filterAppsEligibleForBackupForUser(i, strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public int requestBackupForUser(int i, String[] strArr, IBackupObserver iBackupObserver, IBackupManagerMonitor iBackupManagerMonitor, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeStrongBinder(iBackupObserver != null ? iBackupObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBackupManagerMonitor != null ? iBackupManagerMonitor.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBackupForUser(i, strArr, iBackupObserver, iBackupManagerMonitor, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public int requestBackup(String[] strArr, IBackupObserver iBackupObserver, IBackupManagerMonitor iBackupManagerMonitor, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeStrongBinder(iBackupObserver != null ? iBackupObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBackupManagerMonitor != null ? iBackupManagerMonitor.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBackup(strArr, iBackupObserver, iBackupManagerMonitor, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void cancelBackupsForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelBackupsForUser(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void cancelBackups() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelBackups();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public UserHandle getUserForAncestralSerialNumber(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserForAncestralSerialNumber(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void setAncestralSerialNumber(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAncestralSerialNumber(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IBackupManager
            public void excludeKeysFromRestore(String str, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().excludeKeysFromRestore(str, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBackupManager iBackupManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBackupManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBackupManager;
            return true;
        }

        public static IBackupManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
