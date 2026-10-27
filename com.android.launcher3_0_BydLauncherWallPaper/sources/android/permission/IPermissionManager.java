package android.permission;

import android.content.pm.ParceledListSlice;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.permission.IOnPermissionsChangeListener;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IPermissionManager extends IInterface {

    public static class Default implements IPermissionManager {
        @Override // android.permission.IPermissionManager
        public void addOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean addPermission(PermissionInfo permissionInfo, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean addWhitelistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public int checkDeviceIdentifierAccess(String str, String str2, String str3, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.permission.IPermissionManager
        public int checkPermission(String str, String str2, int i) throws RemoteException {
            return 0;
        }

        @Override // android.permission.IPermissionManager
        public int checkUidPermission(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.permission.IPermissionManager
        public ParceledListSlice getAllPermissionGroups(int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public String[] getAppOpPermissionPackages(String str) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public List<String> getAutoRevokeExemptionGrantedPackages(int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public List<String> getAutoRevokeExemptionRequestedPackages(int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public String getDefaultBrowser(int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public int getPermissionFlags(String str, String str2, int i) throws RemoteException {
            return 0;
        }

        @Override // android.permission.IPermissionManager
        public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public PermissionInfo getPermissionInfo(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public List<SplitPermissionInfoParcelable> getSplitPermissions() throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public List<String> getWhitelistedRestrictedPermissions(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public void grantDefaultPermissionsToActiveLuiApp(String str, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void grantDefaultPermissionsToEnabledCarrierApps(String[] strArr, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void grantDefaultPermissionsToEnabledImsServices(String[] strArr, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] strArr, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void grantRuntimePermission(String str, String str2, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean isAutoRevokeWhitelisted(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean isPermissionEnforced(String str) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean isPermissionRevokedByPolicy(String str, String str2, int i) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public ParceledListSlice queryPermissionsByGroup(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.permission.IPermissionManager
        public void removeOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void removePermission(String str) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean removeWhitelistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public void resetRuntimePermissions() throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] strArr, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void revokeDefaultPermissionsFromLuiApps(String[] strArr, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void revokeRuntimePermission(String str, String str2, int i, String str3) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean setAutoRevokeWhitelisted(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public boolean setDefaultBrowser(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public void setPermissionEnforced(String str, boolean z) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public boolean shouldShowRequestPermissionRationale(String str, String str2, int i) throws RemoteException {
            return false;
        }

        @Override // android.permission.IPermissionManager
        public void startOneTimePermissionSession(String str, int i, long j, int i2, int i3) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void stopOneTimePermissionSession(String str, int i) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void updatePermissionFlags(String str, String str2, int i, int i2, boolean z, int i3) throws RemoteException {
        }

        @Override // android.permission.IPermissionManager
        public void updatePermissionFlagsForAllApps(int i, int i2, int i3) throws RemoteException {
        }
    }

    void addOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException;

    boolean addPermission(PermissionInfo permissionInfo, boolean z) throws RemoteException;

    boolean addWhitelistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException;

    int checkDeviceIdentifierAccess(String str, String str2, String str3, int i, int i2) throws RemoteException;

    int checkPermission(String str, String str2, int i) throws RemoteException;

    int checkUidPermission(String str, int i) throws RemoteException;

    ParceledListSlice getAllPermissionGroups(int i) throws RemoteException;

    String[] getAppOpPermissionPackages(String str) throws RemoteException;

    List<String> getAutoRevokeExemptionGrantedPackages(int i) throws RemoteException;

    List<String> getAutoRevokeExemptionRequestedPackages(int i) throws RemoteException;

    String getDefaultBrowser(int i) throws RemoteException;

    int getPermissionFlags(String str, String str2, int i) throws RemoteException;

    PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException;

    PermissionInfo getPermissionInfo(String str, String str2, int i) throws RemoteException;

    List<SplitPermissionInfoParcelable> getSplitPermissions() throws RemoteException;

    List<String> getWhitelistedRestrictedPermissions(String str, int i, int i2) throws RemoteException;

    void grantDefaultPermissionsToActiveLuiApp(String str, int i) throws RemoteException;

    void grantDefaultPermissionsToEnabledCarrierApps(String[] strArr, int i) throws RemoteException;

    void grantDefaultPermissionsToEnabledImsServices(String[] strArr, int i) throws RemoteException;

    void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] strArr, int i) throws RemoteException;

    void grantRuntimePermission(String str, String str2, int i) throws RemoteException;

    boolean isAutoRevokeWhitelisted(String str, int i) throws RemoteException;

    boolean isPermissionEnforced(String str) throws RemoteException;

    boolean isPermissionRevokedByPolicy(String str, String str2, int i) throws RemoteException;

    ParceledListSlice queryPermissionsByGroup(String str, int i) throws RemoteException;

    void removeOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException;

    void removePermission(String str) throws RemoteException;

    boolean removeWhitelistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException;

    void resetRuntimePermissions() throws RemoteException;

    void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] strArr, int i) throws RemoteException;

    void revokeDefaultPermissionsFromLuiApps(String[] strArr, int i) throws RemoteException;

    void revokeRuntimePermission(String str, String str2, int i, String str3) throws RemoteException;

    boolean setAutoRevokeWhitelisted(String str, boolean z, int i) throws RemoteException;

    boolean setDefaultBrowser(String str, int i) throws RemoteException;

    void setPermissionEnforced(String str, boolean z) throws RemoteException;

    boolean shouldShowRequestPermissionRationale(String str, String str2, int i) throws RemoteException;

    void startOneTimePermissionSession(String str, int i, long j, int i2, int i3) throws RemoteException;

    void stopOneTimePermissionSession(String str, int i) throws RemoteException;

    void updatePermissionFlags(String str, String str2, int i, int i2, boolean z, int i3) throws RemoteException;

    void updatePermissionFlagsForAllApps(int i, int i2, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IPermissionManager {
        private static final String DESCRIPTOR = "android.permission.IPermissionManager";
        static final int TRANSACTION_addOnPermissionsChangeListener = 14;
        static final int TRANSACTION_addPermission = 6;
        static final int TRANSACTION_addWhitelistedRestrictedPermission = 17;
        static final int TRANSACTION_checkDeviceIdentifierAccess = 13;
        static final int TRANSACTION_checkPermission = 11;
        static final int TRANSACTION_checkUidPermission = 12;
        static final int TRANSACTION_getAllPermissionGroups = 2;
        static final int TRANSACTION_getAppOpPermissionPackages = 1;
        static final int TRANSACTION_getAutoRevokeExemptionGrantedPackages = 38;
        static final int TRANSACTION_getAutoRevokeExemptionRequestedPackages = 37;
        static final int TRANSACTION_getDefaultBrowser = 23;
        static final int TRANSACTION_getPermissionFlags = 8;
        static final int TRANSACTION_getPermissionGroupInfo = 3;
        static final int TRANSACTION_getPermissionInfo = 4;
        static final int TRANSACTION_getSplitPermissions = 34;
        static final int TRANSACTION_getWhitelistedRestrictedPermissions = 16;
        static final int TRANSACTION_grantDefaultPermissionsToActiveLuiApp = 28;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledCarrierApps = 24;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledImsServices = 25;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledTelephonyDataServices = 26;
        static final int TRANSACTION_grantRuntimePermission = 19;
        static final int TRANSACTION_isAutoRevokeWhitelisted = 40;
        static final int TRANSACTION_isPermissionEnforced = 31;
        static final int TRANSACTION_isPermissionRevokedByPolicy = 33;
        static final int TRANSACTION_queryPermissionsByGroup = 5;
        static final int TRANSACTION_removeOnPermissionsChangeListener = 15;
        static final int TRANSACTION_removePermission = 7;
        static final int TRANSACTION_removeWhitelistedRestrictedPermission = 18;
        static final int TRANSACTION_resetRuntimePermissions = 21;
        static final int TRANSACTION_revokeDefaultPermissionsFromDisabledTelephonyDataServices = 27;
        static final int TRANSACTION_revokeDefaultPermissionsFromLuiApps = 29;
        static final int TRANSACTION_revokeRuntimePermission = 20;
        static final int TRANSACTION_setAutoRevokeWhitelisted = 39;
        static final int TRANSACTION_setDefaultBrowser = 22;
        static final int TRANSACTION_setPermissionEnforced = 30;
        static final int TRANSACTION_shouldShowRequestPermissionRationale = 32;
        static final int TRANSACTION_startOneTimePermissionSession = 35;
        static final int TRANSACTION_stopOneTimePermissionSession = 36;
        static final int TRANSACTION_updatePermissionFlags = 9;
        static final int TRANSACTION_updatePermissionFlagsForAllApps = 10;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getAppOpPermissionPackages";
                case 2:
                    return "getAllPermissionGroups";
                case 3:
                    return "getPermissionGroupInfo";
                case 4:
                    return "getPermissionInfo";
                case 5:
                    return "queryPermissionsByGroup";
                case 6:
                    return "addPermission";
                case 7:
                    return "removePermission";
                case 8:
                    return "getPermissionFlags";
                case 9:
                    return "updatePermissionFlags";
                case 10:
                    return "updatePermissionFlagsForAllApps";
                case 11:
                    return "checkPermission";
                case 12:
                    return "checkUidPermission";
                case 13:
                    return "checkDeviceIdentifierAccess";
                case 14:
                    return "addOnPermissionsChangeListener";
                case 15:
                    return "removeOnPermissionsChangeListener";
                case 16:
                    return "getWhitelistedRestrictedPermissions";
                case 17:
                    return "addWhitelistedRestrictedPermission";
                case 18:
                    return "removeWhitelistedRestrictedPermission";
                case 19:
                    return "grantRuntimePermission";
                case 20:
                    return "revokeRuntimePermission";
                case 21:
                    return "resetRuntimePermissions";
                case 22:
                    return "setDefaultBrowser";
                case 23:
                    return "getDefaultBrowser";
                case 24:
                    return "grantDefaultPermissionsToEnabledCarrierApps";
                case 25:
                    return "grantDefaultPermissionsToEnabledImsServices";
                case 26:
                    return "grantDefaultPermissionsToEnabledTelephonyDataServices";
                case 27:
                    return "revokeDefaultPermissionsFromDisabledTelephonyDataServices";
                case 28:
                    return "grantDefaultPermissionsToActiveLuiApp";
                case 29:
                    return "revokeDefaultPermissionsFromLuiApps";
                case 30:
                    return "setPermissionEnforced";
                case 31:
                    return "isPermissionEnforced";
                case 32:
                    return "shouldShowRequestPermissionRationale";
                case 33:
                    return "isPermissionRevokedByPolicy";
                case 34:
                    return "getSplitPermissions";
                case 35:
                    return "startOneTimePermissionSession";
                case 36:
                    return "stopOneTimePermissionSession";
                case 37:
                    return "getAutoRevokeExemptionRequestedPackages";
                case 38:
                    return "getAutoRevokeExemptionGrantedPackages";
                case 39:
                    return "setAutoRevokeWhitelisted";
                case 40:
                    return "isAutoRevokeWhitelisted";
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

        public static IPermissionManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPermissionManager)) {
                return (IPermissionManager) iInterfaceQueryLocalInterface;
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
                    String[] appOpPermissionPackages = getAppOpPermissionPackages(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(appOpPermissionPackages);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice allPermissionGroups = getAllPermissionGroups(parcel.readInt());
                    parcel2.writeNoException();
                    if (allPermissionGroups != null) {
                        parcel2.writeInt(1);
                        allPermissionGroups.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    PermissionGroupInfo permissionGroupInfo = getPermissionGroupInfo(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (permissionGroupInfo != null) {
                        parcel2.writeInt(1);
                        permissionGroupInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    PermissionInfo permissionInfo = getPermissionInfo(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (permissionInfo != null) {
                        parcel2.writeInt(1);
                        permissionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryPermissionsByGroup = queryPermissionsByGroup(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryPermissionsByGroup != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryPermissionsByGroup.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddPermission = addPermission(parcel.readInt() != 0 ? PermissionInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddPermission ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    removePermission(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int permissionFlags = getPermissionFlags(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(permissionFlags);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePermissionFlags(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePermissionFlagsForAllApps(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckPermission = checkPermission(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckPermission);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckUidPermission = checkUidPermission(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckUidPermission);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckDeviceIdentifierAccess = checkDeviceIdentifierAccess(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckDeviceIdentifierAccess);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    addOnPermissionsChangeListener(IOnPermissionsChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeOnPermissionsChangeListener(IOnPermissionsChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> whitelistedRestrictedPermissions = getWhitelistedRestrictedPermissions(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(whitelistedRestrictedPermissions);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddWhitelistedRestrictedPermission = addWhitelistedRestrictedPermission(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddWhitelistedRestrictedPermission ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveWhitelistedRestrictedPermission = removeWhitelistedRestrictedPermission(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveWhitelistedRestrictedPermission ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetRuntimePermissions();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean defaultBrowser = setDefaultBrowser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultBrowser ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    String defaultBrowser2 = getDefaultBrowser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(defaultBrowser2);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantDefaultPermissionsToEnabledCarrierApps(parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantDefaultPermissionsToEnabledImsServices(parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantDefaultPermissionsToEnabledTelephonyDataServices(parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeDefaultPermissionsFromDisabledTelephonyDataServices(parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantDefaultPermissionsToActiveLuiApp(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeDefaultPermissionsFromLuiApps(parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPermissionEnforced(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPermissionEnforced = isPermissionEnforced(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPermissionEnforced ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldShowRequestPermissionRationale ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPermissionRevokedByPolicy = isPermissionRevokedByPolicy(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPermissionRevokedByPolicy ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SplitPermissionInfoParcelable> splitPermissions = getSplitPermissions();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(splitPermissions);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    startOneTimePermissionSession(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopOneTimePermissionSession(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> autoRevokeExemptionRequestedPackages = getAutoRevokeExemptionRequestedPackages(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(autoRevokeExemptionRequestedPackages);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> autoRevokeExemptionGrantedPackages = getAutoRevokeExemptionGrantedPackages(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(autoRevokeExemptionGrantedPackages);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean autoRevokeWhitelisted = setAutoRevokeWhitelisted(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(autoRevokeWhitelisted ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAutoRevokeWhitelisted = isAutoRevokeWhitelisted(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAutoRevokeWhitelisted ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPermissionManager {
            public static IPermissionManager sDefaultImpl;
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

            @Override // android.permission.IPermissionManager
            public String[] getAppOpPermissionPackages(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppOpPermissionPackages(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public ParceledListSlice getAllPermissionGroups(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPermissionGroups(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionGroupInfo(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PermissionGroupInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public PermissionInfo getPermissionInfo(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionInfo(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PermissionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public ParceledListSlice queryPermissionsByGroup(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryPermissionsByGroup(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean addPermission(PermissionInfo permissionInfo, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (permissionInfo != null) {
                        parcelObtain.writeInt(1);
                        permissionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPermission(permissionInfo, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void removePermission(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removePermission(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public int getPermissionFlags(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionFlags(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void updatePermissionFlags(String str, String str2, int i, int i2, boolean z, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePermissionFlags(str, str2, i, i2, z, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void updatePermissionFlagsForAllApps(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePermissionFlagsForAllApps(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public int checkPermission(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkPermission(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public int checkUidPermission(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkUidPermission(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public int checkDeviceIdentifierAccess(String str, String str2, String str3, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkDeviceIdentifierAccess(str, str2, str3, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void addOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOnPermissionsChangeListener != null ? iOnPermissionsChangeListener.asBinder() : null);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addOnPermissionsChangeListener(iOnPermissionsChangeListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void removeOnPermissionsChangeListener(IOnPermissionsChangeListener iOnPermissionsChangeListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOnPermissionsChangeListener != null ? iOnPermissionsChangeListener.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeOnPermissionsChangeListener(iOnPermissionsChangeListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<String> getWhitelistedRestrictedPermissions(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWhitelistedRestrictedPermissions(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean addWhitelistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addWhitelistedRestrictedPermission(str, str2, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean removeWhitelistedRestrictedPermission(String str, String str2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeWhitelistedRestrictedPermission(str, str2, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void grantRuntimePermission(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantRuntimePermission(str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void revokeRuntimePermission(String str, String str2, int i, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeRuntimePermission(str, str2, i, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void resetRuntimePermissions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetRuntimePermissions();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean setDefaultBrowser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultBrowser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public String getDefaultBrowser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultBrowser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void grantDefaultPermissionsToEnabledCarrierApps(String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantDefaultPermissionsToEnabledCarrierApps(strArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void grantDefaultPermissionsToEnabledImsServices(String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantDefaultPermissionsToEnabledImsServices(strArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantDefaultPermissionsToEnabledTelephonyDataServices(strArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeDefaultPermissionsFromDisabledTelephonyDataServices(strArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void grantDefaultPermissionsToActiveLuiApp(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantDefaultPermissionsToActiveLuiApp(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void revokeDefaultPermissionsFromLuiApps(String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeDefaultPermissionsFromLuiApps(strArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void setPermissionEnforced(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPermissionEnforced(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean isPermissionEnforced(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPermissionEnforced(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean shouldShowRequestPermissionRationale(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowRequestPermissionRationale(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean isPermissionRevokedByPolicy(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPermissionRevokedByPolicy(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<SplitPermissionInfoParcelable> getSplitPermissions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSplitPermissions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SplitPermissionInfoParcelable.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void startOneTimePermissionSession(String str, int i, long j, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startOneTimePermissionSession(str, i, j, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public void stopOneTimePermissionSession(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopOneTimePermissionSession(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<String> getAutoRevokeExemptionRequestedPackages(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoRevokeExemptionRequestedPackages(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public List<String> getAutoRevokeExemptionGrantedPackages(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoRevokeExemptionGrantedPackages(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean setAutoRevokeWhitelisted(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAutoRevokeWhitelisted(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionManager
            public boolean isAutoRevokeWhitelisted(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutoRevokeWhitelisted(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPermissionManager iPermissionManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPermissionManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPermissionManager;
            return true;
        }

        public static IPermissionManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
