package android.os;

import android.net.INetworkManagementEventObserver;
import android.net.ITetheringStatsProvider;
import android.net.InterfaceConfiguration;
import android.net.Network;
import android.net.NetworkStats;
import android.net.RouteInfo;
import android.net.UidRange;
import android.os.INetworkActivityListener;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkManagementService extends IInterface {

    public static class Default implements INetworkManagementService {
        @Override // android.os.INetworkManagementService
        public void addIdleTimer(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void addInterfaceToLocalNetwork(String str, List<RouteInfo> list) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void addInterfaceToNetwork(String str, int i) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void addLegacyRouteForNetId(int i, RouteInfo routeInfo, int i2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void addRoute(int i, RouteInfo routeInfo) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void addVpnUidRanges(int i, UidRange[] uidRangeArr) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void allowProtect(int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.INetworkManagementService
        public void clearDefaultNetId() throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void clearInterfaceAddresses(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void denyProtect(int i) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void disableIpv6(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void disableNat(String str, String str2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void enableIpv6(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void enableNat(String str, String str2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public String[] getDnsForwarders() throws RemoteException {
            return null;
        }

        @Override // android.os.INetworkManagementService
        public InterfaceConfiguration getInterfaceConfig(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.INetworkManagementService
        public boolean getIpForwardingEnabled() throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public NetworkStats getNetworkStatsTethering(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.INetworkManagementService
        public boolean isBandwidthControlEnabled() throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public boolean isFirewallEnabled() throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public boolean isNetworkActive() throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public boolean isNetworkRestricted(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public boolean isTetheringStarted() throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public String[] listInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.os.INetworkManagementService
        public String[] listTetheredInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.os.INetworkManagementService
        public void registerNetworkActivityListener(INetworkActivityListener iNetworkActivityListener) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void registerObserver(INetworkManagementEventObserver iNetworkManagementEventObserver) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void registerTetheringStatsProvider(ITetheringStatsProvider iTetheringStatsProvider, String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void removeIdleTimer(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void removeInterfaceAlert(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void removeInterfaceFromLocalNetwork(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void removeInterfaceFromNetwork(String str, int i) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void removeInterfaceQuota(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void removeRoute(int i, RouteInfo routeInfo) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public int removeRoutesFromLocalNetwork(List<RouteInfo> list) throws RemoteException {
            return 0;
        }

        @Override // android.os.INetworkManagementService
        public void removeVpnUidRanges(int i, UidRange[] uidRangeArr) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setAllowOnlyVpnForUids(boolean z, UidRange[] uidRangeArr) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public boolean setDataSaverModeEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override // android.os.INetworkManagementService
        public void setDefaultNetId(int i) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setDnsForwarders(Network network, String[] strArr) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setFirewallChainEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setFirewallEnabled(boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setFirewallInterfaceRule(String str, boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setFirewallUidRule(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setFirewallUidRules(int i, int[] iArr, int[] iArr2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setGlobalAlert(long j) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setIPv6AddrGenMode(String str, int i) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setInterfaceAlert(String str, long j) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setInterfaceConfig(String str, InterfaceConfiguration interfaceConfiguration) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setInterfaceDown(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setInterfaceIpv6PrivacyExtensions(String str, boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setInterfaceQuota(String str, long j) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setInterfaceUp(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setIpForwardingEnabled(boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setMtu(String str, int i) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setNetworkPermission(int i, int i2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setUidCleartextNetworkPolicy(int i, int i2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setUidMeteredNetworkBlacklist(int i, boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void setUidMeteredNetworkWhitelist(int i, boolean z) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void shutdown() throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void startInterfaceForwarding(String str, String str2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void startTethering(String[] strArr) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void startTetheringWithConfiguration(boolean z, String[] strArr) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void stopInterfaceForwarding(String str, String str2) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void stopTethering() throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void tetherInterface(String str) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void tetherLimitReached(ITetheringStatsProvider iTetheringStatsProvider) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void unregisterNetworkActivityListener(INetworkActivityListener iNetworkActivityListener) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void unregisterObserver(INetworkManagementEventObserver iNetworkManagementEventObserver) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void unregisterTetheringStatsProvider(ITetheringStatsProvider iTetheringStatsProvider) throws RemoteException {
        }

        @Override // android.os.INetworkManagementService
        public void untetherInterface(String str) throws RemoteException {
        }
    }

    void addIdleTimer(String str, int i, int i2) throws RemoteException;

    void addInterfaceToLocalNetwork(String str, List<RouteInfo> list) throws RemoteException;

    void addInterfaceToNetwork(String str, int i) throws RemoteException;

    void addLegacyRouteForNetId(int i, RouteInfo routeInfo, int i2) throws RemoteException;

    void addRoute(int i, RouteInfo routeInfo) throws RemoteException;

    void addVpnUidRanges(int i, UidRange[] uidRangeArr) throws RemoteException;

    void allowProtect(int i) throws RemoteException;

    void clearDefaultNetId() throws RemoteException;

    void clearInterfaceAddresses(String str) throws RemoteException;

    void denyProtect(int i) throws RemoteException;

    void disableIpv6(String str) throws RemoteException;

    void disableNat(String str, String str2) throws RemoteException;

    void enableIpv6(String str) throws RemoteException;

    void enableNat(String str, String str2) throws RemoteException;

    String[] getDnsForwarders() throws RemoteException;

    InterfaceConfiguration getInterfaceConfig(String str) throws RemoteException;

    boolean getIpForwardingEnabled() throws RemoteException;

    NetworkStats getNetworkStatsTethering(int i) throws RemoteException;

    boolean isBandwidthControlEnabled() throws RemoteException;

    boolean isFirewallEnabled() throws RemoteException;

    boolean isNetworkActive() throws RemoteException;

    boolean isNetworkRestricted(int i) throws RemoteException;

    boolean isTetheringStarted() throws RemoteException;

    String[] listInterfaces() throws RemoteException;

    String[] listTetheredInterfaces() throws RemoteException;

    void registerNetworkActivityListener(INetworkActivityListener iNetworkActivityListener) throws RemoteException;

    void registerObserver(INetworkManagementEventObserver iNetworkManagementEventObserver) throws RemoteException;

    void registerTetheringStatsProvider(ITetheringStatsProvider iTetheringStatsProvider, String str) throws RemoteException;

    void removeIdleTimer(String str) throws RemoteException;

    void removeInterfaceAlert(String str) throws RemoteException;

    void removeInterfaceFromLocalNetwork(String str) throws RemoteException;

    void removeInterfaceFromNetwork(String str, int i) throws RemoteException;

    void removeInterfaceQuota(String str) throws RemoteException;

    void removeRoute(int i, RouteInfo routeInfo) throws RemoteException;

    int removeRoutesFromLocalNetwork(List<RouteInfo> list) throws RemoteException;

    void removeVpnUidRanges(int i, UidRange[] uidRangeArr) throws RemoteException;

    void setAllowOnlyVpnForUids(boolean z, UidRange[] uidRangeArr) throws RemoteException;

    boolean setDataSaverModeEnabled(boolean z) throws RemoteException;

    void setDefaultNetId(int i) throws RemoteException;

    void setDnsForwarders(Network network, String[] strArr) throws RemoteException;

    void setFirewallChainEnabled(int i, boolean z) throws RemoteException;

    void setFirewallEnabled(boolean z) throws RemoteException;

    void setFirewallInterfaceRule(String str, boolean z) throws RemoteException;

    void setFirewallUidRule(int i, int i2, int i3) throws RemoteException;

    void setFirewallUidRules(int i, int[] iArr, int[] iArr2) throws RemoteException;

    void setGlobalAlert(long j) throws RemoteException;

    void setIPv6AddrGenMode(String str, int i) throws RemoteException;

    void setInterfaceAlert(String str, long j) throws RemoteException;

    void setInterfaceConfig(String str, InterfaceConfiguration interfaceConfiguration) throws RemoteException;

    void setInterfaceDown(String str) throws RemoteException;

    void setInterfaceIpv6PrivacyExtensions(String str, boolean z) throws RemoteException;

    void setInterfaceQuota(String str, long j) throws RemoteException;

    void setInterfaceUp(String str) throws RemoteException;

    void setIpForwardingEnabled(boolean z) throws RemoteException;

    void setMtu(String str, int i) throws RemoteException;

    void setNetworkPermission(int i, int i2) throws RemoteException;

    void setUidCleartextNetworkPolicy(int i, int i2) throws RemoteException;

    void setUidMeteredNetworkBlacklist(int i, boolean z) throws RemoteException;

    void setUidMeteredNetworkWhitelist(int i, boolean z) throws RemoteException;

    void shutdown() throws RemoteException;

    void startInterfaceForwarding(String str, String str2) throws RemoteException;

    void startTethering(String[] strArr) throws RemoteException;

    void startTetheringWithConfiguration(boolean z, String[] strArr) throws RemoteException;

    void stopInterfaceForwarding(String str, String str2) throws RemoteException;

    void stopTethering() throws RemoteException;

    void tetherInterface(String str) throws RemoteException;

    void tetherLimitReached(ITetheringStatsProvider iTetheringStatsProvider) throws RemoteException;

    void unregisterNetworkActivityListener(INetworkActivityListener iNetworkActivityListener) throws RemoteException;

    void unregisterObserver(INetworkManagementEventObserver iNetworkManagementEventObserver) throws RemoteException;

    void unregisterTetheringStatsProvider(ITetheringStatsProvider iTetheringStatsProvider) throws RemoteException;

    void untetherInterface(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkManagementService {
        private static final String DESCRIPTOR = "android.os.INetworkManagementService";
        static final int TRANSACTION_addIdleTimer = 46;
        static final int TRANSACTION_addInterfaceToLocalNetwork = 67;
        static final int TRANSACTION_addInterfaceToNetwork = 59;
        static final int TRANSACTION_addLegacyRouteForNetId = 61;
        static final int TRANSACTION_addRoute = 13;
        static final int TRANSACTION_addVpnUidRanges = 54;
        static final int TRANSACTION_allowProtect = 65;
        static final int TRANSACTION_clearDefaultNetId = 63;
        static final int TRANSACTION_clearInterfaceAddresses = 6;
        static final int TRANSACTION_denyProtect = 66;
        static final int TRANSACTION_disableIpv6 = 10;
        static final int TRANSACTION_disableNat = 31;
        static final int TRANSACTION_enableIpv6 = 11;
        static final int TRANSACTION_enableNat = 30;
        static final int TRANSACTION_getDnsForwarders = 27;
        static final int TRANSACTION_getInterfaceConfig = 4;
        static final int TRANSACTION_getIpForwardingEnabled = 17;
        static final int TRANSACTION_getNetworkStatsTethering = 35;
        static final int TRANSACTION_isBandwidthControlEnabled = 45;
        static final int TRANSACTION_isFirewallEnabled = 49;
        static final int TRANSACTION_isNetworkActive = 58;
        static final int TRANSACTION_isNetworkRestricted = 71;
        static final int TRANSACTION_isTetheringStarted = 22;
        static final int TRANSACTION_listInterfaces = 3;
        static final int TRANSACTION_listTetheredInterfaces = 25;
        static final int TRANSACTION_registerNetworkActivityListener = 56;
        static final int TRANSACTION_registerObserver = 1;
        static final int TRANSACTION_registerTetheringStatsProvider = 32;
        static final int TRANSACTION_removeIdleTimer = 47;
        static final int TRANSACTION_removeInterfaceAlert = 39;
        static final int TRANSACTION_removeInterfaceFromLocalNetwork = 68;
        static final int TRANSACTION_removeInterfaceFromNetwork = 60;
        static final int TRANSACTION_removeInterfaceQuota = 37;
        static final int TRANSACTION_removeRoute = 14;
        static final int TRANSACTION_removeRoutesFromLocalNetwork = 69;
        static final int TRANSACTION_removeVpnUidRanges = 55;
        static final int TRANSACTION_setAllowOnlyVpnForUids = 70;
        static final int TRANSACTION_setDataSaverModeEnabled = 43;
        static final int TRANSACTION_setDefaultNetId = 62;
        static final int TRANSACTION_setDnsForwarders = 26;
        static final int TRANSACTION_setFirewallChainEnabled = 53;
        static final int TRANSACTION_setFirewallEnabled = 48;
        static final int TRANSACTION_setFirewallInterfaceRule = 50;
        static final int TRANSACTION_setFirewallUidRule = 51;
        static final int TRANSACTION_setFirewallUidRules = 52;
        static final int TRANSACTION_setGlobalAlert = 40;
        static final int TRANSACTION_setIPv6AddrGenMode = 12;
        static final int TRANSACTION_setInterfaceAlert = 38;
        static final int TRANSACTION_setInterfaceConfig = 5;
        static final int TRANSACTION_setInterfaceDown = 7;
        static final int TRANSACTION_setInterfaceIpv6PrivacyExtensions = 9;
        static final int TRANSACTION_setInterfaceQuota = 36;
        static final int TRANSACTION_setInterfaceUp = 8;
        static final int TRANSACTION_setIpForwardingEnabled = 18;
        static final int TRANSACTION_setMtu = 15;
        static final int TRANSACTION_setNetworkPermission = 64;
        static final int TRANSACTION_setUidCleartextNetworkPolicy = 44;
        static final int TRANSACTION_setUidMeteredNetworkBlacklist = 41;
        static final int TRANSACTION_setUidMeteredNetworkWhitelist = 42;
        static final int TRANSACTION_shutdown = 16;
        static final int TRANSACTION_startInterfaceForwarding = 28;
        static final int TRANSACTION_startTethering = 19;
        static final int TRANSACTION_startTetheringWithConfiguration = 20;
        static final int TRANSACTION_stopInterfaceForwarding = 29;
        static final int TRANSACTION_stopTethering = 21;
        static final int TRANSACTION_tetherInterface = 23;
        static final int TRANSACTION_tetherLimitReached = 34;
        static final int TRANSACTION_unregisterNetworkActivityListener = 57;
        static final int TRANSACTION_unregisterObserver = 2;
        static final int TRANSACTION_unregisterTetheringStatsProvider = 33;
        static final int TRANSACTION_untetherInterface = 24;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerObserver";
                case 2:
                    return "unregisterObserver";
                case 3:
                    return "listInterfaces";
                case 4:
                    return "getInterfaceConfig";
                case 5:
                    return "setInterfaceConfig";
                case 6:
                    return "clearInterfaceAddresses";
                case 7:
                    return "setInterfaceDown";
                case 8:
                    return "setInterfaceUp";
                case 9:
                    return "setInterfaceIpv6PrivacyExtensions";
                case 10:
                    return "disableIpv6";
                case 11:
                    return "enableIpv6";
                case 12:
                    return "setIPv6AddrGenMode";
                case 13:
                    return "addRoute";
                case 14:
                    return "removeRoute";
                case 15:
                    return "setMtu";
                case 16:
                    return "shutdown";
                case 17:
                    return "getIpForwardingEnabled";
                case 18:
                    return "setIpForwardingEnabled";
                case 19:
                    return "startTethering";
                case 20:
                    return "startTetheringWithConfiguration";
                case 21:
                    return "stopTethering";
                case 22:
                    return "isTetheringStarted";
                case 23:
                    return "tetherInterface";
                case 24:
                    return "untetherInterface";
                case 25:
                    return "listTetheredInterfaces";
                case 26:
                    return "setDnsForwarders";
                case 27:
                    return "getDnsForwarders";
                case 28:
                    return "startInterfaceForwarding";
                case 29:
                    return "stopInterfaceForwarding";
                case 30:
                    return "enableNat";
                case 31:
                    return "disableNat";
                case 32:
                    return "registerTetheringStatsProvider";
                case 33:
                    return "unregisterTetheringStatsProvider";
                case 34:
                    return "tetherLimitReached";
                case 35:
                    return "getNetworkStatsTethering";
                case 36:
                    return "setInterfaceQuota";
                case 37:
                    return "removeInterfaceQuota";
                case 38:
                    return "setInterfaceAlert";
                case 39:
                    return "removeInterfaceAlert";
                case 40:
                    return "setGlobalAlert";
                case 41:
                    return "setUidMeteredNetworkBlacklist";
                case 42:
                    return "setUidMeteredNetworkWhitelist";
                case 43:
                    return "setDataSaverModeEnabled";
                case 44:
                    return "setUidCleartextNetworkPolicy";
                case 45:
                    return "isBandwidthControlEnabled";
                case 46:
                    return "addIdleTimer";
                case 47:
                    return "removeIdleTimer";
                case 48:
                    return "setFirewallEnabled";
                case 49:
                    return "isFirewallEnabled";
                case 50:
                    return "setFirewallInterfaceRule";
                case 51:
                    return "setFirewallUidRule";
                case 52:
                    return "setFirewallUidRules";
                case 53:
                    return "setFirewallChainEnabled";
                case 54:
                    return "addVpnUidRanges";
                case 55:
                    return "removeVpnUidRanges";
                case 56:
                    return "registerNetworkActivityListener";
                case 57:
                    return "unregisterNetworkActivityListener";
                case 58:
                    return "isNetworkActive";
                case 59:
                    return "addInterfaceToNetwork";
                case 60:
                    return "removeInterfaceFromNetwork";
                case 61:
                    return "addLegacyRouteForNetId";
                case 62:
                    return "setDefaultNetId";
                case 63:
                    return "clearDefaultNetId";
                case 64:
                    return "setNetworkPermission";
                case 65:
                    return "allowProtect";
                case 66:
                    return "denyProtect";
                case 67:
                    return "addInterfaceToLocalNetwork";
                case 68:
                    return "removeInterfaceFromLocalNetwork";
                case 69:
                    return "removeRoutesFromLocalNetwork";
                case 70:
                    return "setAllowOnlyVpnForUids";
                case 71:
                    return "isNetworkRestricted";
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

        public static INetworkManagementService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkManagementService)) {
                return (INetworkManagementService) iInterfaceQueryLocalInterface;
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
                    registerObserver(INetworkManagementEventObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterObserver(INetworkManagementEventObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrListInterfaces = listInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrListInterfaces);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    InterfaceConfiguration interfaceConfig = getInterfaceConfig(parcel.readString());
                    parcel2.writeNoException();
                    if (interfaceConfig != null) {
                        parcel2.writeInt(1);
                        interfaceConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterfaceConfig(parcel.readString(), parcel.readInt() != 0 ? InterfaceConfiguration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearInterfaceAddresses(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterfaceDown(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterfaceUp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterfaceIpv6PrivacyExtensions(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableIpv6(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableIpv6(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIPv6AddrGenMode(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    addRoute(parcel.readInt(), parcel.readInt() != 0 ? RouteInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeRoute(parcel.readInt(), parcel.readInt() != 0 ? RouteInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMtu(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    shutdown();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ipForwardingEnabled = getIpForwardingEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(ipForwardingEnabled ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIpForwardingEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    startTethering(parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    startTetheringWithConfiguration(parcel.readInt() != 0, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopTethering();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTetheringStarted = isTetheringStarted();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTetheringStarted ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    tetherInterface(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    untetherInterface(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrListTetheredInterfaces = listTetheredInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrListTetheredInterfaces);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDnsForwarders(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] dnsForwarders = getDnsForwarders();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(dnsForwarders);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    startInterfaceForwarding(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopInterfaceForwarding(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableNat(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableNat(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerTetheringStatsProvider(ITetheringStatsProvider.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterTetheringStatsProvider(ITetheringStatsProvider.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    tetherLimitReached(ITetheringStatsProvider.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkStats networkStatsTethering = getNetworkStatsTethering(parcel.readInt());
                    parcel2.writeNoException();
                    if (networkStatsTethering != null) {
                        parcel2.writeInt(1);
                        networkStatsTethering.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterfaceQuota(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeInterfaceQuota(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInterfaceAlert(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeInterfaceAlert(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    setGlobalAlert(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUidMeteredNetworkBlacklist(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUidMeteredNetworkWhitelist(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean dataSaverModeEnabled = setDataSaverModeEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(dataSaverModeEnabled ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUidCleartextNetworkPolicy(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBandwidthControlEnabled = isBandwidthControlEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBandwidthControlEnabled ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    addIdleTimer(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeIdleTimer(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFirewallEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFirewallEnabled = isFirewallEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFirewallEnabled ? 1 : 0);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFirewallInterfaceRule(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFirewallUidRule(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFirewallUidRules(parcel.readInt(), parcel.createIntArray(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFirewallChainEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    addVpnUidRanges(parcel.readInt(), (UidRange[]) parcel.createTypedArray(UidRange.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeVpnUidRanges(parcel.readInt(), (UidRange[]) parcel.createTypedArray(UidRange.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerNetworkActivityListener(INetworkActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterNetworkActivityListener(INetworkActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNetworkActive = isNetworkActive();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNetworkActive ? 1 : 0);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    addInterfaceToNetwork(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeInterfaceFromNetwork(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    addLegacyRouteForNetId(parcel.readInt(), parcel.readInt() != 0 ? RouteInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultNetId(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearDefaultNetId();
                    parcel2.writeNoException();
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNetworkPermission(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowProtect(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    denyProtect(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    addInterfaceToLocalNetwork(parcel.readString(), parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeInterfaceFromLocalNetwork(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRemoveRoutesFromLocalNetwork = removeRoutesFromLocalNetwork(parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRemoveRoutesFromLocalNetwork);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAllowOnlyVpnForUids(parcel.readInt() != 0, (UidRange[]) parcel.createTypedArray(UidRange.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNetworkRestricted = isNetworkRestricted(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNetworkRestricted ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INetworkManagementService {
            public static INetworkManagementService sDefaultImpl;
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

            @Override // android.os.INetworkManagementService
            public void registerObserver(INetworkManagementEventObserver iNetworkManagementEventObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNetworkManagementEventObserver != null ? iNetworkManagementEventObserver.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerObserver(iNetworkManagementEventObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void unregisterObserver(INetworkManagementEventObserver iNetworkManagementEventObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNetworkManagementEventObserver != null ? iNetworkManagementEventObserver.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterObserver(iNetworkManagementEventObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public String[] listInterfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listInterfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public InterfaceConfiguration getInterfaceConfig(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterfaceConfig(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InterfaceConfiguration.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setInterfaceConfig(String str, InterfaceConfiguration interfaceConfiguration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (interfaceConfiguration != null) {
                        parcelObtain.writeInt(1);
                        interfaceConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceConfig(str, interfaceConfiguration);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void clearInterfaceAddresses(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearInterfaceAddresses(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setInterfaceDown(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceDown(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setInterfaceUp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceUp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setInterfaceIpv6PrivacyExtensions(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceIpv6PrivacyExtensions(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void disableIpv6(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableIpv6(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void enableIpv6(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableIpv6(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setIPv6AddrGenMode(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIPv6AddrGenMode(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void addRoute(int i, RouteInfo routeInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (routeInfo != null) {
                        parcelObtain.writeInt(1);
                        routeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addRoute(i, routeInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeRoute(int i, RouteInfo routeInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (routeInfo != null) {
                        parcelObtain.writeInt(1);
                        routeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeRoute(i, routeInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setMtu(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMtu(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void shutdown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().shutdown();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean getIpForwardingEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIpForwardingEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setIpForwardingEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIpForwardingEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void startTethering(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startTethering(strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void startTetheringWithConfiguration(boolean z, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startTetheringWithConfiguration(z, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void stopTethering() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopTethering();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean isTetheringStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTetheringStarted();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void tetherInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherInterface(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void untetherInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().untetherInterface(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public String[] listTetheredInterfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listTetheredInterfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setDnsForwarders(Network network, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDnsForwarders(network, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public String[] getDnsForwarders() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDnsForwarders();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void startInterfaceForwarding(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startInterfaceForwarding(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void stopInterfaceForwarding(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopInterfaceForwarding(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void enableNat(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableNat(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void disableNat(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableNat(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void registerTetheringStatsProvider(ITetheringStatsProvider iTetheringStatsProvider, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTetheringStatsProvider != null ? iTetheringStatsProvider.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTetheringStatsProvider(iTetheringStatsProvider, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void unregisterTetheringStatsProvider(ITetheringStatsProvider iTetheringStatsProvider) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTetheringStatsProvider != null ? iTetheringStatsProvider.asBinder() : null);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTetheringStatsProvider(iTetheringStatsProvider);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void tetherLimitReached(ITetheringStatsProvider iTetheringStatsProvider) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTetheringStatsProvider != null ? iTetheringStatsProvider.asBinder() : null);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherLimitReached(iTetheringStatsProvider);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public NetworkStats getNetworkStatsTethering(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkStatsTethering(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setInterfaceQuota(String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceQuota(str, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeInterfaceQuota(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeInterfaceQuota(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setInterfaceAlert(String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceAlert(str, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeInterfaceAlert(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeInterfaceAlert(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setGlobalAlert(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setGlobalAlert(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setUidMeteredNetworkBlacklist(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUidMeteredNetworkBlacklist(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setUidMeteredNetworkWhitelist(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUidMeteredNetworkWhitelist(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean setDataSaverModeEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDataSaverModeEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setUidCleartextNetworkPolicy(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUidCleartextNetworkPolicy(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean isBandwidthControlEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBandwidthControlEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void addIdleTimer(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addIdleTimer(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeIdleTimer(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeIdleTimer(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setFirewallEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFirewallEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean isFirewallEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFirewallEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setFirewallInterfaceRule(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFirewallInterfaceRule(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setFirewallUidRule(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFirewallUidRule(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setFirewallUidRules(int i, int[] iArr, int[] iArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeIntArray(iArr2);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFirewallUidRules(i, iArr, iArr2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setFirewallChainEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFirewallChainEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void addVpnUidRanges(int i, UidRange[] uidRangeArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(uidRangeArr, 0);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVpnUidRanges(i, uidRangeArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeVpnUidRanges(int i, UidRange[] uidRangeArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(uidRangeArr, 0);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVpnUidRanges(i, uidRangeArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void registerNetworkActivityListener(INetworkActivityListener iNetworkActivityListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNetworkActivityListener != null ? iNetworkActivityListener.asBinder() : null);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerNetworkActivityListener(iNetworkActivityListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void unregisterNetworkActivityListener(INetworkActivityListener iNetworkActivityListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNetworkActivityListener != null ? iNetworkActivityListener.asBinder() : null);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterNetworkActivityListener(iNetworkActivityListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean isNetworkActive() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNetworkActive();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void addInterfaceToNetwork(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addInterfaceToNetwork(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeInterfaceFromNetwork(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeInterfaceFromNetwork(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void addLegacyRouteForNetId(int i, RouteInfo routeInfo, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (routeInfo != null) {
                        parcelObtain.writeInt(1);
                        routeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addLegacyRouteForNetId(i, routeInfo, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setDefaultNetId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultNetId(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void clearDefaultNetId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearDefaultNetId();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setNetworkPermission(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNetworkPermission(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void allowProtect(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowProtect(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void denyProtect(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().denyProtect(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void addInterfaceToLocalNetwork(String str, List<RouteInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addInterfaceToLocalNetwork(str, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void removeInterfaceFromLocalNetwork(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeInterfaceFromLocalNetwork(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public int removeRoutesFromLocalNetwork(List<RouteInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeRoutesFromLocalNetwork(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public void setAllowOnlyVpnForUids(boolean z, UidRange[] uidRangeArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeTypedArray(uidRangeArr, 0);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAllowOnlyVpnForUids(z, uidRangeArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.INetworkManagementService
            public boolean isNetworkRestricted(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNetworkRestricted(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkManagementService iNetworkManagementService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkManagementService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkManagementService;
            return true;
        }

        public static INetworkManagementService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
