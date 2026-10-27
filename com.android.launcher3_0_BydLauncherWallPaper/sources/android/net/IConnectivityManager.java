package android.net;

import android.app.PendingIntent;
import android.net.IConnectivityDiagnosticsCallback;
import android.net.ISocketKeepaliveCallback;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteException;
import com.android.internal.net.LegacyVpnInfo;
import com.android.internal.net.VpnConfig;
import com.android.internal.net.VpnProfile;
import java.io.FileDescriptor;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IConnectivityManager extends IInterface {

    public static class Default implements IConnectivityManager {
        @Override // android.net.IConnectivityManager
        public boolean addVpnAddress(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public int checkMobileProvisioning(int i) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public void declareNetworkRequestUnfulfillable(NetworkRequest networkRequest) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void deleteVpnProfile(String str) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public ParcelFileDescriptor establishVpn(VpnConfig vpnConfig) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public void factoryReset() throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public LinkProperties getActiveLinkProperties() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public Network getActiveNetwork() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public Network getActiveNetworkForUid(int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkInfo getActiveNetworkInfo() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkInfo getActiveNetworkInfoForUid(int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkQuotaInfo getActiveNetworkQuotaInfo() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkInfo[] getAllNetworkInfo() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkState[] getAllNetworkState() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public Network[] getAllNetworks() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String getAlwaysOnVpnPackage(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String getCaptivePortalServerUrl() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public int getConnectionOwnerUid(ConnectionInfo connectionInfo) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkRequest getDefaultRequest() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public ProxyInfo getGlobalProxy() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public int getLastTetherError(String str) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public LegacyVpnInfo getLegacyVpnInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public LinkProperties getLinkProperties(Network network) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public LinkProperties getLinkPropertiesForType(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String getMobileProvisioningUrl() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public int getMultipathPreference(Network network) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public NetworkCapabilities getNetworkCapabilities(Network network, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public Network getNetworkForType(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkInfo getNetworkInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public NetworkInfo getNetworkInfoForUid(Network network, int i, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public byte[] getNetworkWatchlistConfigHash() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public ProxyInfo getProxyForNetwork(Network network) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public int getRestoreDefaultNetworkDelay(int i) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public String[] getTetherableIfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String[] getTetherableUsbRegexs() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String[] getTetherableWifiRegexs() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String[] getTetheredIfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public String[] getTetheringErroredIfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public VpnConfig getVpnConfig(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public List<String> getVpnLockdownWhitelist(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public boolean isActiveNetworkMetered() throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public boolean isAlwaysOnVpnPackageSupported(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public boolean isCallerCurrentAlwaysOnVpnApp() throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public boolean isCallerCurrentAlwaysOnVpnLockdownApp() throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public boolean isNetworkSupported(int i) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public boolean isVpnLockdownEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public NetworkRequest listenForNetwork(NetworkCapabilities networkCapabilities, Messenger messenger, IBinder iBinder, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public void pendingListenForNetwork(NetworkCapabilities networkCapabilities, PendingIntent pendingIntent, String str) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public NetworkRequest pendingRequestForNetwork(NetworkCapabilities networkCapabilities, PendingIntent pendingIntent, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public boolean prepareVpn(String str, String str2, int i) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public boolean provisionVpnProfile(VpnProfile vpnProfile, String str) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public void registerConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback, NetworkRequest networkRequest, String str) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public Network registerNetworkAgent(Messenger messenger, NetworkInfo networkInfo, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i, NetworkAgentConfig networkAgentConfig, int i2) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public int registerNetworkFactory(Messenger messenger, String str) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public int registerNetworkProvider(Messenger messenger, String str) throws RemoteException {
            return 0;
        }

        @Override // android.net.IConnectivityManager
        public void releaseNetworkRequest(NetworkRequest networkRequest) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void releasePendingNetworkRequest(PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public boolean removeVpnAddress(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public void reportInetCondition(int i, int i2) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void reportNetworkConnectivity(Network network, boolean z) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public boolean requestBandwidthUpdate(Network network) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public NetworkRequest requestNetwork(NetworkCapabilities networkCapabilities, Messenger messenger, int i, IBinder iBinder, int i2, String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public boolean requestRouteToHostAddress(int i, byte[] bArr) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public void setAcceptPartialConnectivity(Network network, boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void setAcceptUnvalidated(Network network, boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void setAirplaneMode(boolean z) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public boolean setAlwaysOnVpnPackage(int i, String str, boolean z, List<String> list) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public void setAvoidUnvalidated(Network network) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void setGlobalProxy(ProxyInfo proxyInfo) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void setProvisioningNotificationVisible(boolean z, int i, String str) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public boolean setUnderlyingNetworksForVpn(Network[] networkArr) throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public void setVpnPackageAuthorization(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public boolean shouldAvoidBadWifi() throws RemoteException {
            return false;
        }

        @Override // android.net.IConnectivityManager
        public void simulateDataStall(int i, long j, Network network, PersistableBundle persistableBundle) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void startCaptivePortalApp(Network network) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void startCaptivePortalAppInternal(Network network, Bundle bundle) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void startLegacyVpn(VpnProfile vpnProfile) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void startNattKeepalive(Network network, int i, ISocketKeepaliveCallback iSocketKeepaliveCallback, String str, int i2, String str2) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void startNattKeepaliveWithFd(Network network, FileDescriptor fileDescriptor, int i, int i2, ISocketKeepaliveCallback iSocketKeepaliveCallback, String str, String str2) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public IBinder startOrGetTestNetworkService() throws RemoteException {
            return null;
        }

        @Override // android.net.IConnectivityManager
        public void startTcpKeepalive(Network network, FileDescriptor fileDescriptor, int i, ISocketKeepaliveCallback iSocketKeepaliveCallback) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void startVpnProfile(String str) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void stopKeepalive(Network network, int i) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void stopVpnProfile(String str) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void unregisterConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void unregisterNetworkFactory(Messenger messenger) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public void unregisterNetworkProvider(Messenger messenger) throws RemoteException {
        }

        @Override // android.net.IConnectivityManager
        public boolean updateLockdownVpn() throws RemoteException {
            return false;
        }
    }

    boolean addVpnAddress(String str, int i) throws RemoteException;

    int checkMobileProvisioning(int i) throws RemoteException;

    void declareNetworkRequestUnfulfillable(NetworkRequest networkRequest) throws RemoteException;

    void deleteVpnProfile(String str) throws RemoteException;

    ParcelFileDescriptor establishVpn(VpnConfig vpnConfig) throws RemoteException;

    void factoryReset() throws RemoteException;

    LinkProperties getActiveLinkProperties() throws RemoteException;

    Network getActiveNetwork() throws RemoteException;

    Network getActiveNetworkForUid(int i, boolean z) throws RemoteException;

    NetworkInfo getActiveNetworkInfo() throws RemoteException;

    NetworkInfo getActiveNetworkInfoForUid(int i, boolean z) throws RemoteException;

    NetworkQuotaInfo getActiveNetworkQuotaInfo() throws RemoteException;

    NetworkInfo[] getAllNetworkInfo() throws RemoteException;

    NetworkState[] getAllNetworkState() throws RemoteException;

    Network[] getAllNetworks() throws RemoteException;

    String getAlwaysOnVpnPackage(int i) throws RemoteException;

    String getCaptivePortalServerUrl() throws RemoteException;

    int getConnectionOwnerUid(ConnectionInfo connectionInfo) throws RemoteException;

    NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i, String str) throws RemoteException;

    NetworkRequest getDefaultRequest() throws RemoteException;

    ProxyInfo getGlobalProxy() throws RemoteException;

    int getLastTetherError(String str) throws RemoteException;

    LegacyVpnInfo getLegacyVpnInfo(int i) throws RemoteException;

    LinkProperties getLinkProperties(Network network) throws RemoteException;

    LinkProperties getLinkPropertiesForType(int i) throws RemoteException;

    String getMobileProvisioningUrl() throws RemoteException;

    int getMultipathPreference(Network network) throws RemoteException;

    NetworkCapabilities getNetworkCapabilities(Network network, String str) throws RemoteException;

    Network getNetworkForType(int i) throws RemoteException;

    NetworkInfo getNetworkInfo(int i) throws RemoteException;

    NetworkInfo getNetworkInfoForUid(Network network, int i, boolean z) throws RemoteException;

    byte[] getNetworkWatchlistConfigHash() throws RemoteException;

    ProxyInfo getProxyForNetwork(Network network) throws RemoteException;

    int getRestoreDefaultNetworkDelay(int i) throws RemoteException;

    String[] getTetherableIfaces() throws RemoteException;

    String[] getTetherableUsbRegexs() throws RemoteException;

    String[] getTetherableWifiRegexs() throws RemoteException;

    String[] getTetheredIfaces() throws RemoteException;

    String[] getTetheringErroredIfaces() throws RemoteException;

    VpnConfig getVpnConfig(int i) throws RemoteException;

    List<String> getVpnLockdownWhitelist(int i) throws RemoteException;

    boolean isActiveNetworkMetered() throws RemoteException;

    boolean isAlwaysOnVpnPackageSupported(int i, String str) throws RemoteException;

    boolean isCallerCurrentAlwaysOnVpnApp() throws RemoteException;

    boolean isCallerCurrentAlwaysOnVpnLockdownApp() throws RemoteException;

    boolean isNetworkSupported(int i) throws RemoteException;

    boolean isVpnLockdownEnabled(int i) throws RemoteException;

    NetworkRequest listenForNetwork(NetworkCapabilities networkCapabilities, Messenger messenger, IBinder iBinder, String str) throws RemoteException;

    void pendingListenForNetwork(NetworkCapabilities networkCapabilities, PendingIntent pendingIntent, String str) throws RemoteException;

    NetworkRequest pendingRequestForNetwork(NetworkCapabilities networkCapabilities, PendingIntent pendingIntent, String str) throws RemoteException;

    boolean prepareVpn(String str, String str2, int i) throws RemoteException;

    boolean provisionVpnProfile(VpnProfile vpnProfile, String str) throws RemoteException;

    void registerConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback, NetworkRequest networkRequest, String str) throws RemoteException;

    Network registerNetworkAgent(Messenger messenger, NetworkInfo networkInfo, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i, NetworkAgentConfig networkAgentConfig, int i2) throws RemoteException;

    int registerNetworkFactory(Messenger messenger, String str) throws RemoteException;

    int registerNetworkProvider(Messenger messenger, String str) throws RemoteException;

    void releaseNetworkRequest(NetworkRequest networkRequest) throws RemoteException;

    void releasePendingNetworkRequest(PendingIntent pendingIntent) throws RemoteException;

    boolean removeVpnAddress(String str, int i) throws RemoteException;

    void reportInetCondition(int i, int i2) throws RemoteException;

    void reportNetworkConnectivity(Network network, boolean z) throws RemoteException;

    boolean requestBandwidthUpdate(Network network) throws RemoteException;

    NetworkRequest requestNetwork(NetworkCapabilities networkCapabilities, Messenger messenger, int i, IBinder iBinder, int i2, String str) throws RemoteException;

    boolean requestRouteToHostAddress(int i, byte[] bArr) throws RemoteException;

    void setAcceptPartialConnectivity(Network network, boolean z, boolean z2) throws RemoteException;

    void setAcceptUnvalidated(Network network, boolean z, boolean z2) throws RemoteException;

    void setAirplaneMode(boolean z) throws RemoteException;

    boolean setAlwaysOnVpnPackage(int i, String str, boolean z, List<String> list) throws RemoteException;

    void setAvoidUnvalidated(Network network) throws RemoteException;

    void setGlobalProxy(ProxyInfo proxyInfo) throws RemoteException;

    void setProvisioningNotificationVisible(boolean z, int i, String str) throws RemoteException;

    boolean setUnderlyingNetworksForVpn(Network[] networkArr) throws RemoteException;

    void setVpnPackageAuthorization(String str, int i, int i2) throws RemoteException;

    boolean shouldAvoidBadWifi() throws RemoteException;

    void simulateDataStall(int i, long j, Network network, PersistableBundle persistableBundle) throws RemoteException;

    void startCaptivePortalApp(Network network) throws RemoteException;

    void startCaptivePortalAppInternal(Network network, Bundle bundle) throws RemoteException;

    void startLegacyVpn(VpnProfile vpnProfile) throws RemoteException;

    void startNattKeepalive(Network network, int i, ISocketKeepaliveCallback iSocketKeepaliveCallback, String str, int i2, String str2) throws RemoteException;

    void startNattKeepaliveWithFd(Network network, FileDescriptor fileDescriptor, int i, int i2, ISocketKeepaliveCallback iSocketKeepaliveCallback, String str, String str2) throws RemoteException;

    IBinder startOrGetTestNetworkService() throws RemoteException;

    void startTcpKeepalive(Network network, FileDescriptor fileDescriptor, int i, ISocketKeepaliveCallback iSocketKeepaliveCallback) throws RemoteException;

    void startVpnProfile(String str) throws RemoteException;

    void stopKeepalive(Network network, int i) throws RemoteException;

    void stopVpnProfile(String str) throws RemoteException;

    void unregisterConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback) throws RemoteException;

    void unregisterNetworkFactory(Messenger messenger) throws RemoteException;

    void unregisterNetworkProvider(Messenger messenger) throws RemoteException;

    boolean updateLockdownVpn() throws RemoteException;

    public static abstract class Stub extends Binder implements IConnectivityManager {
        private static final String DESCRIPTOR = "android.net.IConnectivityManager";
        static final int TRANSACTION_addVpnAddress = 73;
        static final int TRANSACTION_checkMobileProvisioning = 47;
        static final int TRANSACTION_declareNetworkRequestUnfulfillable = 56;
        static final int TRANSACTION_deleteVpnProfile = 35;
        static final int TRANSACTION_establishVpn = 33;
        static final int TRANSACTION_factoryReset = 76;
        static final int TRANSACTION_getActiveLinkProperties = 12;
        static final int TRANSACTION_getActiveNetwork = 1;
        static final int TRANSACTION_getActiveNetworkForUid = 2;
        static final int TRANSACTION_getActiveNetworkInfo = 3;
        static final int TRANSACTION_getActiveNetworkInfoForUid = 4;
        static final int TRANSACTION_getActiveNetworkQuotaInfo = 17;
        static final int TRANSACTION_getAllNetworkInfo = 7;
        static final int TRANSACTION_getAllNetworkState = 16;
        static final int TRANSACTION_getAllNetworks = 9;
        static final int TRANSACTION_getAlwaysOnVpnPackage = 44;
        static final int TRANSACTION_getCaptivePortalServerUrl = 81;
        static final int TRANSACTION_getConnectionOwnerUid = 83;
        static final int TRANSACTION_getDefaultNetworkCapabilitiesForUser = 10;
        static final int TRANSACTION_getDefaultRequest = 71;
        static final int TRANSACTION_getGlobalProxy = 28;
        static final int TRANSACTION_getLastTetherError = 20;
        static final int TRANSACTION_getLegacyVpnInfo = 40;
        static final int TRANSACTION_getLinkProperties = 14;
        static final int TRANSACTION_getLinkPropertiesForType = 13;
        static final int TRANSACTION_getMobileProvisioningUrl = 48;
        static final int TRANSACTION_getMultipathPreference = 70;
        static final int TRANSACTION_getNetworkCapabilities = 15;
        static final int TRANSACTION_getNetworkForType = 8;
        static final int TRANSACTION_getNetworkInfo = 5;
        static final int TRANSACTION_getNetworkInfoForUid = 6;
        static final int TRANSACTION_getNetworkWatchlistConfigHash = 82;
        static final int TRANSACTION_getProxyForNetwork = 30;
        static final int TRANSACTION_getRestoreDefaultNetworkDelay = 72;
        static final int TRANSACTION_getTetherableIfaces = 21;
        static final int TRANSACTION_getTetherableUsbRegexs = 24;
        static final int TRANSACTION_getTetherableWifiRegexs = 25;
        static final int TRANSACTION_getTetheredIfaces = 22;
        static final int TRANSACTION_getTetheringErroredIfaces = 23;
        static final int TRANSACTION_getVpnConfig = 38;
        static final int TRANSACTION_getVpnLockdownWhitelist = 46;
        static final int TRANSACTION_isActiveNetworkMetered = 18;
        static final int TRANSACTION_isAlwaysOnVpnPackageSupported = 42;
        static final int TRANSACTION_isCallerCurrentAlwaysOnVpnApp = 84;
        static final int TRANSACTION_isCallerCurrentAlwaysOnVpnLockdownApp = 85;
        static final int TRANSACTION_isNetworkSupported = 11;
        static final int TRANSACTION_isVpnLockdownEnabled = 45;
        static final int TRANSACTION_listenForNetwork = 61;
        static final int TRANSACTION_pendingListenForNetwork = 62;
        static final int TRANSACTION_pendingRequestForNetwork = 59;
        static final int TRANSACTION_prepareVpn = 31;
        static final int TRANSACTION_provisionVpnProfile = 34;
        static final int TRANSACTION_registerConnectivityDiagnosticsCallback = 86;
        static final int TRANSACTION_registerNetworkAgent = 57;
        static final int TRANSACTION_registerNetworkFactory = 52;
        static final int TRANSACTION_registerNetworkProvider = 54;
        static final int TRANSACTION_releaseNetworkRequest = 63;
        static final int TRANSACTION_releasePendingNetworkRequest = 60;
        static final int TRANSACTION_removeVpnAddress = 74;
        static final int TRANSACTION_reportInetCondition = 26;
        static final int TRANSACTION_reportNetworkConnectivity = 27;
        static final int TRANSACTION_requestBandwidthUpdate = 51;
        static final int TRANSACTION_requestNetwork = 58;
        static final int TRANSACTION_requestRouteToHostAddress = 19;
        static final int TRANSACTION_setAcceptPartialConnectivity = 65;
        static final int TRANSACTION_setAcceptUnvalidated = 64;
        static final int TRANSACTION_setAirplaneMode = 50;
        static final int TRANSACTION_setAlwaysOnVpnPackage = 43;
        static final int TRANSACTION_setAvoidUnvalidated = 66;
        static final int TRANSACTION_setGlobalProxy = 29;
        static final int TRANSACTION_setProvisioningNotificationVisible = 49;
        static final int TRANSACTION_setUnderlyingNetworksForVpn = 75;
        static final int TRANSACTION_setVpnPackageAuthorization = 32;
        static final int TRANSACTION_shouldAvoidBadWifi = 69;
        static final int TRANSACTION_simulateDataStall = 89;
        static final int TRANSACTION_startCaptivePortalApp = 67;
        static final int TRANSACTION_startCaptivePortalAppInternal = 68;
        static final int TRANSACTION_startLegacyVpn = 39;
        static final int TRANSACTION_startNattKeepalive = 77;
        static final int TRANSACTION_startNattKeepaliveWithFd = 78;
        static final int TRANSACTION_startOrGetTestNetworkService = 88;
        static final int TRANSACTION_startTcpKeepalive = 79;
        static final int TRANSACTION_startVpnProfile = 36;
        static final int TRANSACTION_stopKeepalive = 80;
        static final int TRANSACTION_stopVpnProfile = 37;
        static final int TRANSACTION_unregisterConnectivityDiagnosticsCallback = 87;
        static final int TRANSACTION_unregisterNetworkFactory = 53;
        static final int TRANSACTION_unregisterNetworkProvider = 55;
        static final int TRANSACTION_updateLockdownVpn = 41;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getActiveNetwork";
                case 2:
                    return "getActiveNetworkForUid";
                case 3:
                    return "getActiveNetworkInfo";
                case 4:
                    return "getActiveNetworkInfoForUid";
                case 5:
                    return "getNetworkInfo";
                case 6:
                    return "getNetworkInfoForUid";
                case 7:
                    return "getAllNetworkInfo";
                case 8:
                    return "getNetworkForType";
                case 9:
                    return "getAllNetworks";
                case 10:
                    return "getDefaultNetworkCapabilitiesForUser";
                case 11:
                    return "isNetworkSupported";
                case 12:
                    return "getActiveLinkProperties";
                case 13:
                    return "getLinkPropertiesForType";
                case 14:
                    return "getLinkProperties";
                case 15:
                    return "getNetworkCapabilities";
                case 16:
                    return "getAllNetworkState";
                case 17:
                    return "getActiveNetworkQuotaInfo";
                case 18:
                    return "isActiveNetworkMetered";
                case 19:
                    return "requestRouteToHostAddress";
                case 20:
                    return "getLastTetherError";
                case 21:
                    return "getTetherableIfaces";
                case 22:
                    return "getTetheredIfaces";
                case 23:
                    return "getTetheringErroredIfaces";
                case 24:
                    return "getTetherableUsbRegexs";
                case 25:
                    return "getTetherableWifiRegexs";
                case 26:
                    return "reportInetCondition";
                case 27:
                    return "reportNetworkConnectivity";
                case 28:
                    return "getGlobalProxy";
                case 29:
                    return "setGlobalProxy";
                case 30:
                    return "getProxyForNetwork";
                case 31:
                    return "prepareVpn";
                case 32:
                    return "setVpnPackageAuthorization";
                case 33:
                    return "establishVpn";
                case 34:
                    return "provisionVpnProfile";
                case 35:
                    return "deleteVpnProfile";
                case 36:
                    return "startVpnProfile";
                case 37:
                    return "stopVpnProfile";
                case 38:
                    return "getVpnConfig";
                case 39:
                    return "startLegacyVpn";
                case 40:
                    return "getLegacyVpnInfo";
                case 41:
                    return "updateLockdownVpn";
                case 42:
                    return "isAlwaysOnVpnPackageSupported";
                case 43:
                    return "setAlwaysOnVpnPackage";
                case 44:
                    return "getAlwaysOnVpnPackage";
                case 45:
                    return "isVpnLockdownEnabled";
                case 46:
                    return "getVpnLockdownWhitelist";
                case 47:
                    return "checkMobileProvisioning";
                case 48:
                    return "getMobileProvisioningUrl";
                case 49:
                    return "setProvisioningNotificationVisible";
                case 50:
                    return "setAirplaneMode";
                case 51:
                    return "requestBandwidthUpdate";
                case 52:
                    return "registerNetworkFactory";
                case 53:
                    return "unregisterNetworkFactory";
                case 54:
                    return "registerNetworkProvider";
                case 55:
                    return "unregisterNetworkProvider";
                case 56:
                    return "declareNetworkRequestUnfulfillable";
                case 57:
                    return "registerNetworkAgent";
                case 58:
                    return "requestNetwork";
                case 59:
                    return "pendingRequestForNetwork";
                case 60:
                    return "releasePendingNetworkRequest";
                case 61:
                    return "listenForNetwork";
                case 62:
                    return "pendingListenForNetwork";
                case 63:
                    return "releaseNetworkRequest";
                case 64:
                    return "setAcceptUnvalidated";
                case 65:
                    return "setAcceptPartialConnectivity";
                case 66:
                    return "setAvoidUnvalidated";
                case 67:
                    return "startCaptivePortalApp";
                case 68:
                    return "startCaptivePortalAppInternal";
                case 69:
                    return "shouldAvoidBadWifi";
                case 70:
                    return "getMultipathPreference";
                case 71:
                    return "getDefaultRequest";
                case 72:
                    return "getRestoreDefaultNetworkDelay";
                case 73:
                    return "addVpnAddress";
                case 74:
                    return "removeVpnAddress";
                case 75:
                    return "setUnderlyingNetworksForVpn";
                case 76:
                    return "factoryReset";
                case 77:
                    return "startNattKeepalive";
                case 78:
                    return "startNattKeepaliveWithFd";
                case 79:
                    return "startTcpKeepalive";
                case 80:
                    return "stopKeepalive";
                case 81:
                    return "getCaptivePortalServerUrl";
                case 82:
                    return "getNetworkWatchlistConfigHash";
                case 83:
                    return "getConnectionOwnerUid";
                case 84:
                    return "isCallerCurrentAlwaysOnVpnApp";
                case 85:
                    return "isCallerCurrentAlwaysOnVpnLockdownApp";
                case 86:
                    return "registerConnectivityDiagnosticsCallback";
                case 87:
                    return "unregisterConnectivityDiagnosticsCallback";
                case 88:
                    return "startOrGetTestNetworkService";
                case 89:
                    return "simulateDataStall";
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

        public static IConnectivityManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IConnectivityManager)) {
                return (IConnectivityManager) iInterfaceQueryLocalInterface;
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
                    Network activeNetwork = getActiveNetwork();
                    parcel2.writeNoException();
                    if (activeNetwork != null) {
                        parcel2.writeInt(1);
                        activeNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    Network activeNetworkForUid = getActiveNetworkForUid(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (activeNetworkForUid != null) {
                        parcel2.writeInt(1);
                        activeNetworkForUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
                    parcel2.writeNoException();
                    if (activeNetworkInfo != null) {
                        parcel2.writeInt(1);
                        activeNetworkInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkInfo activeNetworkInfoForUid = getActiveNetworkInfoForUid(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (activeNetworkInfoForUid != null) {
                        parcel2.writeInt(1);
                        activeNetworkInfoForUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkInfo networkInfo = getNetworkInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (networkInfo != null) {
                        parcel2.writeInt(1);
                        networkInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkInfo networkInfoForUid = getNetworkInfoForUid(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (networkInfoForUid != null) {
                        parcel2.writeInt(1);
                        networkInfoForUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkInfo[] allNetworkInfo = getAllNetworkInfo();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(allNetworkInfo, 1);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    Network networkForType = getNetworkForType(parcel.readInt());
                    parcel2.writeNoException();
                    if (networkForType != null) {
                        parcel2.writeInt(1);
                        networkForType.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    Network[] allNetworks = getAllNetworks();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(allNetworks, 1);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkCapabilities[] defaultNetworkCapabilitiesForUser = getDefaultNetworkCapabilitiesForUser(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(defaultNetworkCapabilitiesForUser, 1);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNetworkSupported = isNetworkSupported(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNetworkSupported ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    LinkProperties activeLinkProperties = getActiveLinkProperties();
                    parcel2.writeNoException();
                    if (activeLinkProperties != null) {
                        parcel2.writeInt(1);
                        activeLinkProperties.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    LinkProperties linkPropertiesForType = getLinkPropertiesForType(parcel.readInt());
                    parcel2.writeNoException();
                    if (linkPropertiesForType != null) {
                        parcel2.writeInt(1);
                        linkPropertiesForType.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    LinkProperties linkProperties = getLinkProperties(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (linkProperties != null) {
                        parcel2.writeInt(1);
                        linkProperties.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkCapabilities networkCapabilities = getNetworkCapabilities(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (networkCapabilities != null) {
                        parcel2.writeInt(1);
                        networkCapabilities.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkState[] allNetworkState = getAllNetworkState();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(allNetworkState, 1);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkQuotaInfo activeNetworkQuotaInfo = getActiveNetworkQuotaInfo();
                    parcel2.writeNoException();
                    if (activeNetworkQuotaInfo != null) {
                        parcel2.writeInt(1);
                        activeNetworkQuotaInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsActiveNetworkMetered = isActiveNetworkMetered();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsActiveNetworkMetered ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestRouteToHostAddress = requestRouteToHostAddress(parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestRouteToHostAddress ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lastTetherError = getLastTetherError(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(lastTetherError);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] tetherableIfaces = getTetherableIfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(tetherableIfaces);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] tetheredIfaces = getTetheredIfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(tetheredIfaces);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] tetheringErroredIfaces = getTetheringErroredIfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(tetheringErroredIfaces);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] tetherableUsbRegexs = getTetherableUsbRegexs();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(tetherableUsbRegexs);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] tetherableWifiRegexs = getTetherableWifiRegexs();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(tetherableWifiRegexs);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportInetCondition(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportNetworkConnectivity(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    ProxyInfo globalProxy = getGlobalProxy();
                    parcel2.writeNoException();
                    if (globalProxy != null) {
                        parcel2.writeInt(1);
                        globalProxy.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    setGlobalProxy(parcel.readInt() != 0 ? ProxyInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    ProxyInfo proxyForNetwork = getProxyForNetwork(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (proxyForNetwork != null) {
                        parcel2.writeInt(1);
                        proxyForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPrepareVpn = prepareVpn(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPrepareVpn ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVpnPackageAuthorization(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor parcelFileDescriptorEstablishVpn = establishVpn(parcel.readInt() != 0 ? VpnConfig.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (parcelFileDescriptorEstablishVpn != null) {
                        parcel2.writeInt(1);
                        parcelFileDescriptorEstablishVpn.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zProvisionVpnProfile = provisionVpnProfile(parcel.readInt() != 0 ? VpnProfile.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zProvisionVpnProfile ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteVpnProfile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    startVpnProfile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopVpnProfile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    VpnConfig vpnConfig = getVpnConfig(parcel.readInt());
                    parcel2.writeNoException();
                    if (vpnConfig != null) {
                        parcel2.writeInt(1);
                        vpnConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    startLegacyVpn(parcel.readInt() != 0 ? VpnProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    LegacyVpnInfo legacyVpnInfo = getLegacyVpnInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (legacyVpnInfo != null) {
                        parcel2.writeInt(1);
                        legacyVpnInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateLockdownVpn = updateLockdownVpn();
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateLockdownVpn ? 1 : 0);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAlwaysOnVpnPackageSupported = isAlwaysOnVpnPackageSupported(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAlwaysOnVpnPackageSupported ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean alwaysOnVpnPackage = setAlwaysOnVpnPackage(parcel.readInt(), parcel.readString(), parcel.readInt() != 0, parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(alwaysOnVpnPackage ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    String alwaysOnVpnPackage2 = getAlwaysOnVpnPackage(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(alwaysOnVpnPackage2);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVpnLockdownEnabled = isVpnLockdownEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVpnLockdownEnabled ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> vpnLockdownWhitelist = getVpnLockdownWhitelist(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(vpnLockdownWhitelist);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckMobileProvisioning = checkMobileProvisioning(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckMobileProvisioning);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    String mobileProvisioningUrl = getMobileProvisioningUrl();
                    parcel2.writeNoException();
                    parcel2.writeString(mobileProvisioningUrl);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    setProvisioningNotificationVisible(parcel.readInt() != 0, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAirplaneMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestBandwidthUpdate = requestBandwidthUpdate(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestBandwidthUpdate ? 1 : 0);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRegisterNetworkFactory = registerNetworkFactory(parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterNetworkFactory);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterNetworkFactory(parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRegisterNetworkProvider = registerNetworkProvider(parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterNetworkProvider);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterNetworkProvider(parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    declareNetworkRequestUnfulfillable(parcel.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    Network networkRegisterNetworkAgent = registerNetworkAgent(parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NetworkInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? LinkProperties.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NetworkCapabilities.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? NetworkAgentConfig.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (networkRegisterNetworkAgent != null) {
                        parcel2.writeInt(1);
                        networkRegisterNetworkAgent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkRequest networkRequestRequestNetwork = requestNetwork(parcel.readInt() != 0 ? NetworkCapabilities.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (networkRequestRequestNetwork != null) {
                        parcel2.writeInt(1);
                        networkRequestRequestNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkRequest networkRequestPendingRequestForNetwork = pendingRequestForNetwork(parcel.readInt() != 0 ? NetworkCapabilities.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (networkRequestPendingRequestForNetwork != null) {
                        parcel2.writeInt(1);
                        networkRequestPendingRequestForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    releasePendingNetworkRequest(parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkRequest networkRequestListenForNetwork = listenForNetwork(parcel.readInt() != 0 ? NetworkCapabilities.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readString());
                    parcel2.writeNoException();
                    if (networkRequestListenForNetwork != null) {
                        parcel2.writeInt(1);
                        networkRequestListenForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    pendingListenForNetwork(parcel.readInt() != 0 ? NetworkCapabilities.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseNetworkRequest(parcel.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAcceptUnvalidated(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAcceptPartialConnectivity(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAvoidUnvalidated(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    startCaptivePortalApp(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    startCaptivePortalAppInternal(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldAvoidBadWifi = shouldAvoidBadWifi();
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldAvoidBadWifi ? 1 : 0);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    int multipathPreference = getMultipathPreference(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(multipathPreference);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    NetworkRequest defaultRequest = getDefaultRequest();
                    parcel2.writeNoException();
                    if (defaultRequest != null) {
                        parcel2.writeInt(1);
                        defaultRequest.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    int restoreDefaultNetworkDelay = getRestoreDefaultNetworkDelay(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(restoreDefaultNetworkDelay);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddVpnAddress = addVpnAddress(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddVpnAddress ? 1 : 0);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveVpnAddress = removeVpnAddress(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveVpnAddress ? 1 : 0);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean underlyingNetworksForVpn = setUnderlyingNetworksForVpn((Network[]) parcel.createTypedArray(Network.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(underlyingNetworksForVpn ? 1 : 0);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    factoryReset();
                    parcel2.writeNoException();
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    startNattKeepalive(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), ISocketKeepaliveCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    startNattKeepaliveWithFd(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readRawFileDescriptor(), parcel.readInt(), parcel.readInt(), ISocketKeepaliveCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    startTcpKeepalive(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readRawFileDescriptor(), parcel.readInt(), ISocketKeepaliveCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopKeepalive(parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    String captivePortalServerUrl = getCaptivePortalServerUrl();
                    parcel2.writeNoException();
                    parcel2.writeString(captivePortalServerUrl);
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] networkWatchlistConfigHash = getNetworkWatchlistConfigHash();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(networkWatchlistConfigHash);
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionOwnerUid = getConnectionOwnerUid(parcel.readInt() != 0 ? ConnectionInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionOwnerUid);
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCallerCurrentAlwaysOnVpnApp = isCallerCurrentAlwaysOnVpnApp();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCallerCurrentAlwaysOnVpnApp ? 1 : 0);
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCallerCurrentAlwaysOnVpnLockdownApp = isCallerCurrentAlwaysOnVpnLockdownApp();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCallerCurrentAlwaysOnVpnLockdownApp ? 1 : 0);
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder iBinderStartOrGetTestNetworkService = startOrGetTestNetworkService();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBinderStartOrGetTestNetworkService);
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    simulateDataStall(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0 ? Network.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IConnectivityManager {
            public static IConnectivityManager sDefaultImpl;
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

            @Override // android.net.IConnectivityManager
            public Network getActiveNetwork() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNetwork();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Network.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public Network getActiveNetworkForUid(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNetworkForUid(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Network.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkInfo getActiveNetworkInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNetworkInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkInfo getActiveNetworkInfoForUid(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNetworkInfoForUid(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkInfo getNetworkInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkInfo getNetworkInfoForUid(Network network, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkInfoForUid(network, i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkInfo[] getAllNetworkInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllNetworkInfo();
                    }
                    parcelObtain2.readException();
                    return (NetworkInfo[]) parcelObtain2.createTypedArray(NetworkInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public Network getNetworkForType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkForType(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Network.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public Network[] getAllNetworks() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllNetworks();
                    }
                    parcelObtain2.readException();
                    return (Network[]) parcelObtain2.createTypedArray(Network.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultNetworkCapabilitiesForUser(i, str);
                    }
                    parcelObtain2.readException();
                    return (NetworkCapabilities[]) parcelObtain2.createTypedArray(NetworkCapabilities.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean isNetworkSupported(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNetworkSupported(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public LinkProperties getActiveLinkProperties() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveLinkProperties();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LinkProperties.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public LinkProperties getLinkPropertiesForType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLinkPropertiesForType(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LinkProperties.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public LinkProperties getLinkProperties(Network network) throws RemoteException {
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
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLinkProperties(network);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LinkProperties.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkCapabilities getNetworkCapabilities(Network network, String str) throws RemoteException {
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
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkCapabilities(network, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkCapabilities.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkState[] getAllNetworkState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllNetworkState();
                    }
                    parcelObtain2.readException();
                    return (NetworkState[]) parcelObtain2.createTypedArray(NetworkState.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkQuotaInfo getActiveNetworkQuotaInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNetworkQuotaInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkQuotaInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean isActiveNetworkMetered() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isActiveNetworkMetered();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean requestRouteToHostAddress(int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestRouteToHostAddress(i, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int getLastTetherError(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastTetherError(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String[] getTetherableIfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTetherableIfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String[] getTetheredIfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTetheredIfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String[] getTetheringErroredIfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTetheringErroredIfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String[] getTetherableUsbRegexs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTetherableUsbRegexs();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String[] getTetherableWifiRegexs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTetherableWifiRegexs();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void reportInetCondition(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportInetCondition(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void reportNetworkConnectivity(Network network, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportNetworkConnectivity(network, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public ProxyInfo getGlobalProxy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGlobalProxy();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ProxyInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setGlobalProxy(ProxyInfo proxyInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (proxyInfo != null) {
                        parcelObtain.writeInt(1);
                        proxyInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setGlobalProxy(proxyInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public ProxyInfo getProxyForNetwork(Network network) throws RemoteException {
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
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProxyForNetwork(network);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ProxyInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean prepareVpn(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().prepareVpn(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setVpnPackageAuthorization(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVpnPackageAuthorization(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public ParcelFileDescriptor establishVpn(VpnConfig vpnConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vpnConfig != null) {
                        parcelObtain.writeInt(1);
                        vpnConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().establishVpn(vpnConfig);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean provisionVpnProfile(VpnProfile vpnProfile, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vpnProfile != null) {
                        parcelObtain.writeInt(1);
                        vpnProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().provisionVpnProfile(vpnProfile, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void deleteVpnProfile(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteVpnProfile(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startVpnProfile(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startVpnProfile(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void stopVpnProfile(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopVpnProfile(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public VpnConfig getVpnConfig(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVpnConfig(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VpnConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startLegacyVpn(VpnProfile vpnProfile) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vpnProfile != null) {
                        parcelObtain.writeInt(1);
                        vpnProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startLegacyVpn(vpnProfile);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public LegacyVpnInfo getLegacyVpnInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLegacyVpnInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LegacyVpnInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean updateLockdownVpn() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateLockdownVpn();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean isAlwaysOnVpnPackageSupported(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAlwaysOnVpnPackageSupported(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean setAlwaysOnVpnPackage(int i, String str, boolean z, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAlwaysOnVpnPackage(i, str, z, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String getAlwaysOnVpnPackage(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlwaysOnVpnPackage(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean isVpnLockdownEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVpnLockdownEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public List<String> getVpnLockdownWhitelist(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVpnLockdownWhitelist(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int checkMobileProvisioning(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkMobileProvisioning(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String getMobileProvisioningUrl() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMobileProvisioningUrl();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setProvisioningNotificationVisible(boolean z, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setProvisioningNotificationVisible(z, i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setAirplaneMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAirplaneMode(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean requestBandwidthUpdate(Network network) throws RemoteException {
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
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBandwidthUpdate(network);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int registerNetworkFactory(Messenger messenger, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerNetworkFactory(messenger, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void unregisterNetworkFactory(Messenger messenger) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterNetworkFactory(messenger);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int registerNetworkProvider(Messenger messenger, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerNetworkProvider(messenger, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void unregisterNetworkProvider(Messenger messenger) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterNetworkProvider(messenger);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void declareNetworkRequestUnfulfillable(NetworkRequest networkRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkRequest != null) {
                        parcelObtain.writeInt(1);
                        networkRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().declareNetworkRequestUnfulfillable(networkRequest);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public Network registerNetworkAgent(Messenger messenger, NetworkInfo networkInfo, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i, NetworkAgentConfig networkAgentConfig, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (networkInfo != null) {
                        parcelObtain.writeInt(1);
                        networkInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (linkProperties != null) {
                        parcelObtain.writeInt(1);
                        linkProperties.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (networkCapabilities != null) {
                        parcelObtain.writeInt(1);
                        networkCapabilities.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (networkAgentConfig != null) {
                        parcelObtain.writeInt(1);
                        networkAgentConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerNetworkAgent(messenger, networkInfo, linkProperties, networkCapabilities, i, networkAgentConfig, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Network.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkRequest requestNetwork(NetworkCapabilities networkCapabilities, Messenger messenger, int i, IBinder iBinder, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkCapabilities != null) {
                        parcelObtain.writeInt(1);
                        networkCapabilities.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestNetwork(networkCapabilities, messenger, i, iBinder, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkRequest pendingRequestForNetwork(NetworkCapabilities networkCapabilities, PendingIntent pendingIntent, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkCapabilities != null) {
                        parcelObtain.writeInt(1);
                        networkCapabilities.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pendingRequestForNetwork(networkCapabilities, pendingIntent, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void releasePendingNetworkRequest(PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releasePendingNetworkRequest(pendingIntent);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkRequest listenForNetwork(NetworkCapabilities networkCapabilities, Messenger messenger, IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkCapabilities != null) {
                        parcelObtain.writeInt(1);
                        networkCapabilities.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listenForNetwork(networkCapabilities, messenger, iBinder, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void pendingListenForNetwork(NetworkCapabilities networkCapabilities, PendingIntent pendingIntent, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkCapabilities != null) {
                        parcelObtain.writeInt(1);
                        networkCapabilities.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pendingListenForNetwork(networkCapabilities, pendingIntent, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void releaseNetworkRequest(NetworkRequest networkRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkRequest != null) {
                        parcelObtain.writeInt(1);
                        networkRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseNetworkRequest(networkRequest);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setAcceptUnvalidated(Network network, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAcceptUnvalidated(network, z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setAcceptPartialConnectivity(Network network, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAcceptPartialConnectivity(network, z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void setAvoidUnvalidated(Network network) throws RemoteException {
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
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAvoidUnvalidated(network);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startCaptivePortalApp(Network network) throws RemoteException {
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
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startCaptivePortalApp(network);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startCaptivePortalAppInternal(Network network, Bundle bundle) throws RemoteException {
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
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startCaptivePortalAppInternal(network, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean shouldAvoidBadWifi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldAvoidBadWifi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int getMultipathPreference(Network network) throws RemoteException {
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
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMultipathPreference(network);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public NetworkRequest getDefaultRequest() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultRequest();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkRequest.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int getRestoreDefaultNetworkDelay(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRestoreDefaultNetworkDelay(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean addVpnAddress(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addVpnAddress(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean removeVpnAddress(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeVpnAddress(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean setUnderlyingNetworksForVpn(Network[] networkArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(networkArr, 0);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUnderlyingNetworksForVpn(networkArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void factoryReset() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().factoryReset();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startNattKeepalive(Network network, int i, ISocketKeepaliveCallback iSocketKeepaliveCallback, String str, int i2, String str2) throws RemoteException {
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
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSocketKeepaliveCallback != null ? iSocketKeepaliveCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startNattKeepalive(network, i, iSocketKeepaliveCallback, str, i2, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startNattKeepaliveWithFd(Network network, FileDescriptor fileDescriptor, int i, int i2, ISocketKeepaliveCallback iSocketKeepaliveCallback, String str, String str2) throws RemoteException {
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
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iSocketKeepaliveCallback != null ? iSocketKeepaliveCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startNattKeepaliveWithFd(network, fileDescriptor, i, i2, iSocketKeepaliveCallback, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void startTcpKeepalive(Network network, FileDescriptor fileDescriptor, int i, ISocketKeepaliveCallback iSocketKeepaliveCallback) throws RemoteException {
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
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iSocketKeepaliveCallback != null ? iSocketKeepaliveCallback.asBinder() : null);
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startTcpKeepalive(network, fileDescriptor, i, iSocketKeepaliveCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void stopKeepalive(Network network, int i) throws RemoteException {
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
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopKeepalive(network, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public String getCaptivePortalServerUrl() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaptivePortalServerUrl();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public byte[] getNetworkWatchlistConfigHash() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkWatchlistConfigHash();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public int getConnectionOwnerUid(ConnectionInfo connectionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectionInfo != null) {
                        parcelObtain.writeInt(1);
                        connectionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(83, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionOwnerUid(connectionInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean isCallerCurrentAlwaysOnVpnApp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(84, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCallerCurrentAlwaysOnVpnApp();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public boolean isCallerCurrentAlwaysOnVpnLockdownApp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(85, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCallerCurrentAlwaysOnVpnLockdownApp();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void registerConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback, NetworkRequest networkRequest, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConnectivityDiagnosticsCallback != null ? iConnectivityDiagnosticsCallback.asBinder() : null);
                    if (networkRequest != null) {
                        parcelObtain.writeInt(1);
                        networkRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(86, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerConnectivityDiagnosticsCallback(iConnectivityDiagnosticsCallback, networkRequest, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void unregisterConnectivityDiagnosticsCallback(IConnectivityDiagnosticsCallback iConnectivityDiagnosticsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConnectivityDiagnosticsCallback != null ? iConnectivityDiagnosticsCallback.asBinder() : null);
                    if (!this.mRemote.transact(87, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterConnectivityDiagnosticsCallback(iConnectivityDiagnosticsCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public IBinder startOrGetTestNetworkService() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(88, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startOrGetTestNetworkService();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IConnectivityManager
            public void simulateDataStall(int i, long j, Network network, PersistableBundle persistableBundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (network != null) {
                        parcelObtain.writeInt(1);
                        network.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (persistableBundle != null) {
                        parcelObtain.writeInt(1);
                        persistableBundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(89, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().simulateDataStall(i, j, network, persistableBundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectivityManager iConnectivityManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iConnectivityManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iConnectivityManager;
            return true;
        }

        public static IConnectivityManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
