package android.net;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.content.Context;
import android.net.INetworkPolicyManager;
import android.net.ISocketKeepaliveCallback;
import android.net.IpSecManager;
import android.net.NetworkRequest;
import android.net.SocketKeepalive;
import android.net.TetheringManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.INetworkActivityListener;
import android.os.INetworkManagementService;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import libcore.net.event.NetworkEventDispatcher;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectivityManager {

    @Deprecated
    public static final String ACTION_BACKGROUND_DATA_SETTING_CHANGED = "android.net.conn.BACKGROUND_DATA_SETTING_CHANGED";
    public static final String ACTION_CAPTIVE_PORTAL_SIGN_IN = "android.net.conn.CAPTIVE_PORTAL";
    public static final String ACTION_CAPTIVE_PORTAL_TEST_COMPLETED = "android.net.conn.CAPTIVE_PORTAL_TEST_COMPLETED";
    public static final String ACTION_DATA_ACTIVITY_CHANGE = "android.net.conn.DATA_ACTIVITY_CHANGE";
    public static final String ACTION_PROMPT_LOST_VALIDATION = "android.net.conn.PROMPT_LOST_VALIDATION";
    public static final String ACTION_PROMPT_PARTIAL_CONNECTIVITY = "android.net.conn.PROMPT_PARTIAL_CONNECTIVITY";
    public static final String ACTION_PROMPT_UNVALIDATED = "android.net.conn.PROMPT_UNVALIDATED";
    public static final String ACTION_RESTRICT_BACKGROUND_CHANGED = "android.net.conn.RESTRICT_BACKGROUND_CHANGED";
    public static final String ACTION_TETHER_STATE_CHANGED = "android.net.conn.TETHER_STATE_CHANGED";
    private static final int BASE = 524288;
    public static final int CALLBACK_AVAILABLE = 524290;
    public static final int CALLBACK_BLK_CHANGED = 524299;
    public static final int CALLBACK_CAP_CHANGED = 524294;
    public static final int CALLBACK_IP_CHANGED = 524295;
    public static final int CALLBACK_LOSING = 524291;
    public static final int CALLBACK_LOST = 524292;
    public static final int CALLBACK_PRECHECK = 524289;
    public static final int CALLBACK_RESUMED = 524298;
    public static final int CALLBACK_SUSPENDED = 524297;
    public static final int CALLBACK_UNAVAIL = 524293;

    @Deprecated
    public static final String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Deprecated
    public static final int DEFAULT_NETWORK_PREFERENCE = 1;
    private static final int DEPRECATED_PHONE_CONSTANT_APN_ALREADY_ACTIVE = 0;
    private static final int DEPRECATED_PHONE_CONSTANT_APN_REQUEST_FAILED = 3;
    private static final int DEPRECATED_PHONE_CONSTANT_APN_REQUEST_STARTED = 1;
    private static final int EXPIRE_LEGACY_REQUEST = 524296;
    public static final String EXTRA_ACTIVE_LOCAL_ONLY = "android.net.extra.ACTIVE_LOCAL_ONLY";
    public static final String EXTRA_ACTIVE_TETHER = "tetherArray";
    public static final String EXTRA_ADD_TETHER_TYPE = "extraAddTetherType";
    public static final String EXTRA_AVAILABLE_TETHER = "availableArray";
    public static final String EXTRA_CAPTIVE_PORTAL = "android.net.extra.CAPTIVE_PORTAL";

    @SystemApi
    public static final String EXTRA_CAPTIVE_PORTAL_PROBE_SPEC = "android.net.extra.CAPTIVE_PORTAL_PROBE_SPEC";
    public static final String EXTRA_CAPTIVE_PORTAL_URL = "android.net.extra.CAPTIVE_PORTAL_URL";

    @SystemApi
    public static final String EXTRA_CAPTIVE_PORTAL_USER_AGENT = "android.net.extra.CAPTIVE_PORTAL_USER_AGENT";
    public static final String EXTRA_DEVICE_TYPE = "deviceType";
    public static final String EXTRA_ERRORED_TETHER = "erroredArray";

    @Deprecated
    public static final String EXTRA_EXTRA_INFO = "extraInfo";
    public static final String EXTRA_INET_CONDITION = "inetCondition";
    public static final String EXTRA_IS_ACTIVE = "isActive";
    public static final String EXTRA_IS_CAPTIVE_PORTAL = "captivePortal";

    @Deprecated
    public static final String EXTRA_IS_FAILOVER = "isFailover";
    public static final String EXTRA_NETWORK = "android.net.extra.NETWORK";

    @Deprecated
    public static final String EXTRA_NETWORK_INFO = "networkInfo";
    public static final String EXTRA_NETWORK_REQUEST = "android.net.extra.NETWORK_REQUEST";

    @Deprecated
    public static final String EXTRA_NETWORK_TYPE = "networkType";
    public static final String EXTRA_NO_CONNECTIVITY = "noConnectivity";

    @Deprecated
    public static final String EXTRA_OTHER_NETWORK_INFO = "otherNetwork";
    public static final String EXTRA_PROVISION_CALLBACK = "extraProvisionCallback";
    public static final String EXTRA_REALTIME_NS = "tsNanos";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_REM_TETHER_TYPE = "extraRemTetherType";
    public static final String EXTRA_RUN_PROVISION = "extraRunProvision";
    public static final String EXTRA_SET_ALARM = "extraSetAlarm";
    public static final String INET_CONDITION_ACTION = "android.net.conn.INET_CONDITION_ACTION";
    private static final int LISTEN = 1;
    public static final int MAX_NETWORK_TYPE = 18;
    public static final int MAX_RADIO_TYPE = 18;
    private static final int MIN_NETWORK_TYPE = 0;
    public static final int MULTIPATH_PREFERENCE_HANDOVER = 1;
    public static final int MULTIPATH_PREFERENCE_PERFORMANCE = 4;
    public static final int MULTIPATH_PREFERENCE_RELIABILITY = 2;
    public static final int MULTIPATH_PREFERENCE_UNMETERED = 7;
    public static final int NETID_UNSET = 0;
    public static final String PRIVATE_DNS_DEFAULT_MODE_FALLBACK = "opportunistic";
    public static final String PRIVATE_DNS_MODE_OFF = "off";
    public static final String PRIVATE_DNS_MODE_OPPORTUNISTIC = "opportunistic";
    public static final String PRIVATE_DNS_MODE_PROVIDER_HOSTNAME = "hostname";
    private static final int REQUEST = 2;
    public static final int REQUEST_ID_UNSET = 0;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    @SystemApi
    public static final int TETHERING_BLUETOOTH = 2;
    public static final int TETHERING_INVALID = -1;

    @SystemApi
    public static final int TETHERING_USB = 1;

    @SystemApi
    public static final int TETHERING_WIFI = 0;
    public static final int TETHERING_WIFI_P2P = 3;

    @Deprecated
    public static final int TETHER_ERROR_DHCPSERVER_ERROR = 12;

    @Deprecated
    public static final int TETHER_ERROR_DISABLE_NAT_ERROR = 9;

    @Deprecated
    public static final int TETHER_ERROR_ENABLE_NAT_ERROR = 8;

    @SystemApi
    @Deprecated
    public static final int TETHER_ERROR_ENTITLEMENT_UNKONWN = 13;

    @Deprecated
    public static final int TETHER_ERROR_IFACE_CFG_ERROR = 10;

    @Deprecated
    public static final int TETHER_ERROR_MASTER_ERROR = 5;

    @SystemApi
    @Deprecated
    public static final int TETHER_ERROR_NO_ERROR = 0;

    @SystemApi
    @Deprecated
    public static final int TETHER_ERROR_PROVISION_FAILED = 11;

    @Deprecated
    public static final int TETHER_ERROR_SERVICE_UNAVAIL = 2;

    @Deprecated
    public static final int TETHER_ERROR_TETHER_IFACE_ERROR = 6;

    @Deprecated
    public static final int TETHER_ERROR_UNAVAIL_IFACE = 4;

    @Deprecated
    public static final int TETHER_ERROR_UNKNOWN_IFACE = 1;

    @Deprecated
    public static final int TETHER_ERROR_UNSUPPORTED = 3;

    @Deprecated
    public static final int TETHER_ERROR_UNTETHER_IFACE_ERROR = 7;

    @Deprecated
    public static final int TYPE_BLUETOOTH = 7;

    @Deprecated
    public static final int TYPE_DUMMY = 8;

    @Deprecated
    public static final int TYPE_ETHERNET = 9;

    @Deprecated
    public static final int TYPE_MOBILE = 0;

    @Deprecated
    public static final int TYPE_MOBILE_CBS = 12;

    @Deprecated
    public static final int TYPE_MOBILE_DUN = 4;

    @Deprecated
    public static final int TYPE_MOBILE_EMERGENCY = 15;

    @Deprecated
    public static final int TYPE_MOBILE_FOTA = 10;

    @Deprecated
    public static final int TYPE_MOBILE_HIPRI = 5;

    @Deprecated
    public static final int TYPE_MOBILE_IA = 14;

    @Deprecated
    public static final int TYPE_MOBILE_IMS = 11;

    @Deprecated
    public static final int TYPE_MOBILE_MMS = 2;

    @Deprecated
    public static final int TYPE_MOBILE_SUPL = 3;

    @SystemApi
    public static final int TYPE_NONE = -1;

    @Deprecated
    public static final int TYPE_PROXY = 16;

    @Deprecated
    public static final int TYPE_TEST = 18;

    @Deprecated
    public static final int TYPE_VPN = 17;

    @Deprecated
    public static final int TYPE_WIFI = 1;

    @SystemApi
    @Deprecated
    public static final int TYPE_WIFI_P2P = 13;

    @Deprecated
    public static final int TYPE_WIMAX = 6;
    private static CallbackHandler sCallbackHandler;
    private static final HashMap<NetworkRequest, NetworkCallback> sCallbacks;
    private static ConnectivityManager sInstance;
    private static final SparseIntArray sLegacyTypeToCapability;
    private static final SparseIntArray sLegacyTypeToTransport;
    private final Context mContext;
    private INetworkManagementService mNMService;
    private INetworkPolicyManager mNPManager;
    private final IConnectivityManager mService;
    private final TetheringManager mTetheringManager;
    private static final String TAG = "ConnectivityManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final NetworkRequest ALREADY_UNREGISTERED = new NetworkRequest.Builder().clearCapabilities().build();
    private static final HashMap<NetworkCapabilities, LegacyRequest> sLegacyRequests = new HashMap<>();
    private final ArrayMap<OnNetworkActiveListener, INetworkActivityListener> mNetworkActivityListeners = new ArrayMap<>();
    private final ArrayMap<OnTetheringEventCallback, TetheringManager.TetheringEventCallback> mTetheringEventCallbacks = new ArrayMap<>();

    @Retention(RetentionPolicy.SOURCE)
    public @interface EntitlementResultCode {
    }

    public interface Errors {
        public static final int TOO_MANY_REQUESTS = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @Deprecated
    public @interface LegacyNetworkType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MultipathPreference {
    }

    public interface OnNetworkActiveListener {
        void onNetworkActive();
    }

    @SystemApi
    @Deprecated
    public static abstract class OnStartTetheringCallback {
        public void onTetheringFailed() {
        }

        public void onTetheringStarted() {
        }
    }

    @SystemApi
    @Deprecated
    public interface OnTetheringEntitlementResultListener {
        void onTetheringEntitlementResult(int i);
    }

    @SystemApi
    @Deprecated
    public static abstract class OnTetheringEventCallback {
        public void onUpstreamChanged(Network network) {
        }
    }

    public static class PacketKeepaliveCallback {
        public void onError(int i) {
        }

        public void onStarted() {
        }

        public void onStopped() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RestrictBackgroundStatus {
    }

    public static class TooManyRequestsException extends RuntimeException {
    }

    @Deprecated
    public static boolean isNetworkTypeMobile(int i) {
        if (i == 0 || i == 2 || i == 3 || i == 4 || i == 5 || i == 14 || i == 15) {
            return true;
        }
        switch (i) {
            case 10:
            case 11:
            case 12:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean isNetworkTypeValid(int i) {
        return i >= 0 && i <= 18;
    }

    @Deprecated
    public static boolean isNetworkTypeWifi(int i) {
        return i == 1 || i == 13;
    }

    @Deprecated
    public boolean getBackgroundDataSetting() {
        return true;
    }

    @Deprecated
    public int getNetworkPreference() {
        return -1;
    }

    @Deprecated
    public void setBackgroundDataSetting(boolean z) {
    }

    @Deprecated
    public void setNetworkPreference(int i) {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sLegacyTypeToTransport = sparseIntArray;
        sparseIntArray.put(0, 0);
        sLegacyTypeToTransport.put(12, 0);
        sLegacyTypeToTransport.put(4, 0);
        sLegacyTypeToTransport.put(10, 0);
        sLegacyTypeToTransport.put(5, 0);
        sLegacyTypeToTransport.put(11, 0);
        sLegacyTypeToTransport.put(2, 0);
        sLegacyTypeToTransport.put(3, 0);
        sLegacyTypeToTransport.put(1, 1);
        sLegacyTypeToTransport.put(13, 1);
        sLegacyTypeToTransport.put(7, 2);
        sLegacyTypeToTransport.put(9, 3);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sLegacyTypeToCapability = sparseIntArray2;
        sparseIntArray2.put(12, 5);
        sLegacyTypeToCapability.put(4, 2);
        sLegacyTypeToCapability.put(10, 3);
        sLegacyTypeToCapability.put(11, 4);
        sLegacyTypeToCapability.put(2, 0);
        sLegacyTypeToCapability.put(3, 1);
        sLegacyTypeToCapability.put(13, 6);
        sCallbacks = new HashMap<>();
    }

    @Deprecated
    public static String getNetworkTypeName(int i) {
        switch (i) {
            case -1:
                return KeyProperties.DIGEST_NONE;
            case 0:
                return "MOBILE";
            case 1:
                return "WIFI";
            case 2:
                return "MOBILE_MMS";
            case 3:
                return "MOBILE_SUPL";
            case 4:
                return "MOBILE_DUN";
            case 5:
                return "MOBILE_HIPRI";
            case 6:
                return "WIMAX";
            case 7:
                return "BLUETOOTH";
            case 8:
                return "DUMMY";
            case 9:
                return "ETHERNET";
            case 10:
                return "MOBILE_FOTA";
            case 11:
                return "MOBILE_IMS";
            case 12:
                return "MOBILE_CBS";
            case 13:
                return "WIFI_P2P";
            case 14:
                return "MOBILE_IA";
            case 15:
                return "MOBILE_EMERGENCY";
            case 16:
                return "PROXY";
            case 17:
                return "VPN";
            default:
                return Integer.toString(i);
        }
    }

    @Deprecated
    public NetworkInfo getActiveNetworkInfo() {
        try {
            return this.mService.getActiveNetworkInfo();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Network getActiveNetwork() {
        try {
            return this.mService.getActiveNetwork();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Network getActiveNetworkForUid(int i) {
        return getActiveNetworkForUid(i, false);
    }

    public Network getActiveNetworkForUid(int i, boolean z) {
        try {
            return this.mService.getActiveNetworkForUid(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAlwaysOnVpnPackageSupportedForUser(int i, String str) {
        try {
            return this.mService.isAlwaysOnVpnPackageSupported(i, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean setAlwaysOnVpnPackageForUser(int i, String str, boolean z, List<String> list) {
        try {
            return this.mService.setAlwaysOnVpnPackage(i, str, z, list);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getAlwaysOnVpnPackageForUser(int i) {
        try {
            return this.mService.getAlwaysOnVpnPackage(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isVpnLockdownEnabled(int i) {
        try {
            return this.mService.isVpnLockdownEnabled(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<String> getVpnLockdownWhitelist(int i) {
        try {
            return this.mService.getVpnLockdownWhitelist(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NetworkInfo getActiveNetworkInfoForUid(int i) {
        return getActiveNetworkInfoForUid(i, false);
    }

    public NetworkInfo getActiveNetworkInfoForUid(int i, boolean z) {
        try {
            return this.mService.getActiveNetworkInfoForUid(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public NetworkInfo getNetworkInfo(int i) {
        try {
            return this.mService.getNetworkInfo(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public NetworkInfo getNetworkInfo(Network network) {
        return getNetworkInfoForUid(network, Process.myUid(), false);
    }

    public NetworkInfo getNetworkInfoForUid(Network network, int i, boolean z) {
        try {
            return this.mService.getNetworkInfoForUid(network, i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public NetworkInfo[] getAllNetworkInfo() {
        try {
            return this.mService.getAllNetworkInfo();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public Network getNetworkForType(int i) {
        try {
            return this.mService.getNetworkForType(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Network[] getAllNetworks() {
        try {
            return this.mService.getAllNetworks();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i) {
        try {
            return this.mService.getDefaultNetworkCapabilitiesForUser(i, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public LinkProperties getActiveLinkProperties() {
        try {
            return this.mService.getActiveLinkProperties();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public LinkProperties getLinkProperties(int i) {
        try {
            return this.mService.getLinkPropertiesForType(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public LinkProperties getLinkProperties(Network network) {
        try {
            return this.mService.getLinkProperties(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NetworkCapabilities getNetworkCapabilities(Network network) {
        try {
            return this.mService.getNetworkCapabilities(network, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public String getCaptivePortalServerUrl() {
        try {
            return this.mService.getCaptivePortalServerUrl();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int startUsingNetworkFeature(int i, String str) {
        checkLegacyRoutingApiAccess();
        NetworkCapabilities networkCapabilitiesNetworkCapabilitiesForFeature = networkCapabilitiesForFeature(i, str);
        if (networkCapabilitiesNetworkCapabilitiesForFeature == null) {
            Log.d(TAG, "Can't satisfy startUsingNetworkFeature for " + i + ", " + str);
            return 3;
        }
        synchronized (sLegacyRequests) {
            LegacyRequest legacyRequest = sLegacyRequests.get(networkCapabilitiesNetworkCapabilitiesForFeature);
            if (legacyRequest != null) {
                Log.d(TAG, "renewing startUsingNetworkFeature request " + legacyRequest.networkRequest);
                renewRequestLocked(legacyRequest);
                return legacyRequest.currentNetwork != null ? 0 : 1;
            }
            NetworkRequest networkRequestRequestNetworkForFeatureLocked = requestNetworkForFeatureLocked(networkCapabilitiesNetworkCapabilitiesForFeature);
            if (networkRequestRequestNetworkForFeatureLocked != null) {
                Log.d(TAG, "starting startUsingNetworkFeature for request " + networkRequestRequestNetworkForFeatureLocked);
                return 1;
            }
            Log.d(TAG, " request Failed");
            return 3;
        }
    }

    @Deprecated
    public int stopUsingNetworkFeature(int i, String str) {
        checkLegacyRoutingApiAccess();
        NetworkCapabilities networkCapabilitiesNetworkCapabilitiesForFeature = networkCapabilitiesForFeature(i, str);
        if (networkCapabilitiesNetworkCapabilitiesForFeature == null) {
            Log.d(TAG, "Can't satisfy stopUsingNetworkFeature for " + i + ", " + str);
            return -1;
        }
        if (!removeRequestForFeature(networkCapabilitiesNetworkCapabilitiesForFeature)) {
            return 1;
        }
        Log.d(TAG, "stopUsingNetworkFeature for " + i + ", " + str);
        return 1;
    }

    private NetworkCapabilities networkCapabilitiesForFeature(int i, String str) {
        if (i == 0) {
            switch (str) {
                case "enableCBS":
                    return networkCapabilitiesForType(12);
                case "enableDUN":
                case "enableDUNAlways":
                    return networkCapabilitiesForType(4);
                case "enableFOTA":
                    return networkCapabilitiesForType(10);
                case "enableHIPRI":
                    return networkCapabilitiesForType(5);
                case "enableIMS":
                    return networkCapabilitiesForType(11);
                case "enableMMS":
                    return networkCapabilitiesForType(2);
                case "enableSUPL":
                    return networkCapabilitiesForType(3);
                default:
                    return null;
            }
        }
        if (i == 1 && "p2p".equals(str)) {
            return networkCapabilitiesForType(13);
        }
        return null;
    }

    private int legacyTypeForNetworkCapabilities(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return -1;
        }
        if (networkCapabilities.hasCapability(5)) {
            return 12;
        }
        if (networkCapabilities.hasCapability(4)) {
            return 11;
        }
        if (networkCapabilities.hasCapability(3)) {
            return 10;
        }
        if (networkCapabilities.hasCapability(2)) {
            return 4;
        }
        if (networkCapabilities.hasCapability(1)) {
            return 3;
        }
        if (networkCapabilities.hasCapability(0)) {
            return 2;
        }
        if (networkCapabilities.hasCapability(12)) {
            return 5;
        }
        return networkCapabilities.hasCapability(6) ? 13 : -1;
    }

    private static class LegacyRequest {
        Network currentNetwork;
        int delay;
        int expireSequenceNumber;
        NetworkCallback networkCallback;
        NetworkCapabilities networkCapabilities;
        NetworkRequest networkRequest;

        private LegacyRequest() {
            this.delay = -1;
            this.networkCallback = new NetworkCallback() { // from class: android.net.ConnectivityManager.LegacyRequest.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    LegacyRequest.this.currentNetwork = network;
                    Log.d(ConnectivityManager.TAG, "startUsingNetworkFeature got Network:" + network);
                    ConnectivityManager.setProcessDefaultNetworkForHostResolution(network);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    if (network.equals(LegacyRequest.this.currentNetwork)) {
                        LegacyRequest.this.clearDnsBinding();
                    }
                    Log.d(ConnectivityManager.TAG, "startUsingNetworkFeature lost Network:" + network);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDnsBinding() {
            if (this.currentNetwork != null) {
                this.currentNetwork = null;
                ConnectivityManager.setProcessDefaultNetworkForHostResolution(null);
            }
        }
    }

    private NetworkRequest findRequestForFeature(NetworkCapabilities networkCapabilities) {
        synchronized (sLegacyRequests) {
            LegacyRequest legacyRequest = sLegacyRequests.get(networkCapabilities);
            if (legacyRequest == null) {
                return null;
            }
            return legacyRequest.networkRequest;
        }
    }

    private void renewRequestLocked(LegacyRequest legacyRequest) {
        legacyRequest.expireSequenceNumber++;
        Log.d(TAG, "renewing request to seqNum " + legacyRequest.expireSequenceNumber);
        sendExpireMsgForFeature(legacyRequest.networkCapabilities, legacyRequest.expireSequenceNumber, legacyRequest.delay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void expireRequest(NetworkCapabilities networkCapabilities, int i) {
        synchronized (sLegacyRequests) {
            LegacyRequest legacyRequest = sLegacyRequests.get(networkCapabilities);
            if (legacyRequest == null) {
                return;
            }
            int i2 = legacyRequest.expireSequenceNumber;
            if (legacyRequest.expireSequenceNumber == i) {
                removeRequestForFeature(networkCapabilities);
            }
            Log.d(TAG, "expireRequest with " + i2 + ", " + i);
        }
    }

    private NetworkRequest requestNetworkForFeatureLocked(NetworkCapabilities networkCapabilities) {
        int iLegacyTypeForNetworkCapabilities = legacyTypeForNetworkCapabilities(networkCapabilities);
        try {
            int restoreDefaultNetworkDelay = this.mService.getRestoreDefaultNetworkDelay(iLegacyTypeForNetworkCapabilities);
            LegacyRequest legacyRequest = new LegacyRequest();
            legacyRequest.networkCapabilities = networkCapabilities;
            legacyRequest.delay = restoreDefaultNetworkDelay;
            legacyRequest.expireSequenceNumber = 0;
            legacyRequest.networkRequest = sendRequestForNetwork(networkCapabilities, legacyRequest.networkCallback, 0, 2, iLegacyTypeForNetworkCapabilities, getDefaultHandler());
            if (legacyRequest.networkRequest == null) {
                return null;
            }
            sLegacyRequests.put(networkCapabilities, legacyRequest);
            sendExpireMsgForFeature(networkCapabilities, legacyRequest.expireSequenceNumber, restoreDefaultNetworkDelay);
            return legacyRequest.networkRequest;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void sendExpireMsgForFeature(NetworkCapabilities networkCapabilities, int i, int i2) {
        if (i2 >= 0) {
            Log.d(TAG, "sending expire msg with seqNum " + i + " and delay " + i2);
            CallbackHandler defaultHandler = getDefaultHandler();
            defaultHandler.sendMessageDelayed(defaultHandler.obtainMessage(EXPIRE_LEGACY_REQUEST, i, 0, networkCapabilities), (long) i2);
        }
    }

    private boolean removeRequestForFeature(NetworkCapabilities networkCapabilities) {
        LegacyRequest legacyRequestRemove;
        synchronized (sLegacyRequests) {
            legacyRequestRemove = sLegacyRequests.remove(networkCapabilities);
        }
        if (legacyRequestRemove == null) {
            return false;
        }
        unregisterNetworkCallback(legacyRequestRemove.networkCallback);
        legacyRequestRemove.clearDnsBinding();
        return true;
    }

    public static NetworkCapabilities networkCapabilitiesForType(int i) {
        NetworkCapabilities networkCapabilities = new NetworkCapabilities();
        int i2 = sLegacyTypeToTransport.get(i, -1);
        Preconditions.checkArgument(i2 != -1, "unknown legacy type: " + i);
        networkCapabilities.addTransportType(i2);
        networkCapabilities.addCapability(sLegacyTypeToCapability.get(i, 12));
        networkCapabilities.maybeMarkCapabilitiesRestricted();
        return networkCapabilities;
    }

    public class PacketKeepalive {
        public static final int BINDER_DIED = -10;
        public static final int ERROR_HARDWARE_ERROR = -31;
        public static final int ERROR_HARDWARE_UNSUPPORTED = -30;
        public static final int ERROR_INVALID_INTERVAL = -24;
        public static final int ERROR_INVALID_IP_ADDRESS = -21;
        public static final int ERROR_INVALID_LENGTH = -23;
        public static final int ERROR_INVALID_NETWORK = -20;
        public static final int ERROR_INVALID_PORT = -22;
        public static final int MIN_INTERVAL = 10;
        public static final int NATT_PORT = 4500;
        public static final int NO_KEEPALIVE = -1;
        public static final int SUCCESS = 0;
        private static final String TAG = "PacketKeepalive";
        private final ISocketKeepaliveCallback mCallback;
        private final ExecutorService mExecutor;
        private final Network mNetwork;
        private volatile Integer mSlot;

        public void stop() {
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$--8nwufwzyblnuYRFEYIKx7L4Vg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$stop$0$ConnectivityManager$PacketKeepalive();
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }

        public /* synthetic */ void lambda$stop$0$ConnectivityManager$PacketKeepalive() {
            try {
                if (this.mSlot != null) {
                    ConnectivityManager.this.mService.stopKeepalive(this.mNetwork, this.mSlot.intValue());
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Error stopping packet keepalive: ", e);
                throw e.rethrowFromSystemServer();
            }
        }

        private PacketKeepalive(Network network, PacketKeepaliveCallback packetKeepaliveCallback) {
            Preconditions.checkNotNull(network, "network cannot be null");
            Preconditions.checkNotNull(packetKeepaliveCallback, "callback cannot be null");
            this.mNetwork = network;
            this.mExecutor = Executors.newSingleThreadExecutor();
            this.mCallback = new AnonymousClass1(ConnectivityManager.this, packetKeepaliveCallback);
        }

        /* JADX INFO: renamed from: android.net.ConnectivityManager$PacketKeepalive$1, reason: invalid class name */
        class AnonymousClass1 extends ISocketKeepaliveCallback.Stub {
            final /* synthetic */ PacketKeepaliveCallback val$callback;
            final /* synthetic */ ConnectivityManager val$this$0;

            @Override // android.net.ISocketKeepaliveCallback
            public void onDataReceived() {
            }

            AnonymousClass1(ConnectivityManager connectivityManager, PacketKeepaliveCallback packetKeepaliveCallback) {
                this.val$this$0 = connectivityManager;
                this.val$callback = packetKeepaliveCallback;
            }

            @Override // android.net.ISocketKeepaliveCallback
            public void onStarted(final int i) {
                final PacketKeepaliveCallback packetKeepaliveCallback = this.val$callback;
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$1$iOtsqOYp69ztB6u3PYNu-iI_PGo
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onStarted$1$ConnectivityManager$PacketKeepalive$1(i, packetKeepaliveCallback);
                    }
                });
            }

            public /* synthetic */ void lambda$onStarted$1$ConnectivityManager$PacketKeepalive$1(final int i, final PacketKeepaliveCallback packetKeepaliveCallback) throws Exception {
                PacketKeepalive.this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$1$NfMgP6Nh6Ep6LcaiJ10o_zBccII
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onStarted$0$ConnectivityManager$PacketKeepalive$1(i, packetKeepaliveCallback);
                    }
                });
            }

            public /* synthetic */ void lambda$onStarted$0$ConnectivityManager$PacketKeepalive$1(int i, PacketKeepaliveCallback packetKeepaliveCallback) {
                PacketKeepalive.this.mSlot = Integer.valueOf(i);
                packetKeepaliveCallback.onStarted();
            }

            @Override // android.net.ISocketKeepaliveCallback
            public void onStopped() {
                final PacketKeepaliveCallback packetKeepaliveCallback = this.val$callback;
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$1$-H5tzn67t3ydWL8tXpl9UyOmDcc
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onStopped$3$ConnectivityManager$PacketKeepalive$1(packetKeepaliveCallback);
                    }
                });
                PacketKeepalive.this.mExecutor.shutdown();
            }

            public /* synthetic */ void lambda$onStopped$3$ConnectivityManager$PacketKeepalive$1(final PacketKeepaliveCallback packetKeepaliveCallback) throws Exception {
                PacketKeepalive.this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$1$WmmtbYWlzqL-V8wWUDKe3CWjvy0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onStopped$2$ConnectivityManager$PacketKeepalive$1(packetKeepaliveCallback);
                    }
                });
            }

            public /* synthetic */ void lambda$onStopped$2$ConnectivityManager$PacketKeepalive$1(PacketKeepaliveCallback packetKeepaliveCallback) {
                PacketKeepalive.this.mSlot = null;
                packetKeepaliveCallback.onStopped();
            }

            @Override // android.net.ISocketKeepaliveCallback
            public void onError(final int i) {
                final PacketKeepaliveCallback packetKeepaliveCallback = this.val$callback;
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$1$nt5Pgsn85fhX6h9EJ0eAK_PXAjU
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$onError$5$ConnectivityManager$PacketKeepalive$1(packetKeepaliveCallback, i);
                    }
                });
                PacketKeepalive.this.mExecutor.shutdown();
            }

            public /* synthetic */ void lambda$onError$5$ConnectivityManager$PacketKeepalive$1(final PacketKeepaliveCallback packetKeepaliveCallback, final int i) throws Exception {
                PacketKeepalive.this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityManager$PacketKeepalive$1$JWcQQZv8Qrs81cZ-BMAOZZ8MUeU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onError$4$ConnectivityManager$PacketKeepalive$1(packetKeepaliveCallback, i);
                    }
                });
            }

            public /* synthetic */ void lambda$onError$4$ConnectivityManager$PacketKeepalive$1(PacketKeepaliveCallback packetKeepaliveCallback, int i) {
                PacketKeepalive.this.mSlot = null;
                packetKeepaliveCallback.onError(i);
            }
        }
    }

    public PacketKeepalive startNattKeepalive(Network network, int i, PacketKeepaliveCallback packetKeepaliveCallback, InetAddress inetAddress, int i2, InetAddress inetAddress2) {
        PacketKeepalive packetKeepalive = new PacketKeepalive(network, packetKeepaliveCallback);
        try {
            this.mService.startNattKeepalive(network, i, packetKeepalive.mCallback, inetAddress.getHostAddress(), i2, inetAddress2.getHostAddress());
            return packetKeepalive;
        } catch (RemoteException e) {
            Log.e(TAG, "Error starting packet keepalive: ", e);
            throw e.rethrowFromSystemServer();
        }
    }

    public SocketKeepalive createSocketKeepalive(Network network, IpSecManager.UdpEncapsulationSocket udpEncapsulationSocket, InetAddress inetAddress, InetAddress inetAddress2, Executor executor, SocketKeepalive.Callback callback) {
        ParcelFileDescriptor parcelFileDescriptor;
        try {
            parcelFileDescriptor = ParcelFileDescriptor.dup(udpEncapsulationSocket.getFileDescriptor());
        } catch (IOException unused) {
            parcelFileDescriptor = new ParcelFileDescriptor(new FileDescriptor());
        }
        return new NattSocketKeepalive(this.mService, network, parcelFileDescriptor, udpEncapsulationSocket.getResourceId(), inetAddress, inetAddress2, executor, callback);
    }

    @SystemApi
    public SocketKeepalive createNattKeepalive(Network network, ParcelFileDescriptor parcelFileDescriptor, InetAddress inetAddress, InetAddress inetAddress2, Executor executor, SocketKeepalive.Callback callback) {
        ParcelFileDescriptor parcelFileDescriptor2;
        try {
            parcelFileDescriptor2 = parcelFileDescriptor.dup();
        } catch (IOException unused) {
            parcelFileDescriptor2 = new ParcelFileDescriptor(new FileDescriptor());
        }
        return new NattSocketKeepalive(this.mService, network, parcelFileDescriptor2, -1, inetAddress, inetAddress2, executor, callback);
    }

    @SystemApi
    public SocketKeepalive createSocketKeepalive(Network network, Socket socket, Executor executor, SocketKeepalive.Callback callback) {
        ParcelFileDescriptor parcelFileDescriptor;
        try {
            parcelFileDescriptor = ParcelFileDescriptor.fromSocket(socket);
        } catch (UncheckedIOException unused) {
            parcelFileDescriptor = new ParcelFileDescriptor(new FileDescriptor());
        }
        return new TcpSocketKeepalive(this.mService, network, parcelFileDescriptor, executor, callback);
    }

    @Deprecated
    public boolean requestRouteToHost(int i, int i2) {
        return requestRouteToHostAddress(i, NetworkUtils.intToInetAddress(i2));
    }

    @Deprecated
    public boolean requestRouteToHostAddress(int i, InetAddress inetAddress) {
        checkLegacyRoutingApiAccess();
        try {
            return this.mService.requestRouteToHostAddress(i, inetAddress.getAddress());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public NetworkQuotaInfo getActiveNetworkQuotaInfo() {
        try {
            return this.mService.getActiveNetworkQuotaInfo();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean getMobileDataEnabled() {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        if (telephonyManager != null) {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            Log.d(TAG, "getMobileDataEnabled()+ subId=" + defaultDataSubscriptionId);
            boolean zIsDataEnabled = telephonyManager.createForSubscriptionId(defaultDataSubscriptionId).isDataEnabled();
            Log.d(TAG, "getMobileDataEnabled()- subId=" + defaultDataSubscriptionId + " retVal=" + zIsDataEnabled);
            return zIsDataEnabled;
        }
        Log.d(TAG, "getMobileDataEnabled()- remote exception retVal=false");
        return false;
    }

    private INetworkManagementService getNetworkManagementService() {
        synchronized (this) {
            if (this.mNMService != null) {
                return this.mNMService;
            }
            INetworkManagementService iNetworkManagementServiceAsInterface = INetworkManagementService.Stub.asInterface(ServiceManager.getService(Context.NETWORKMANAGEMENT_SERVICE));
            this.mNMService = iNetworkManagementServiceAsInterface;
            return iNetworkManagementServiceAsInterface;
        }
    }

    public void addDefaultNetworkActiveListener(final OnNetworkActiveListener onNetworkActiveListener) {
        INetworkActivityListener.Stub stub = new INetworkActivityListener.Stub() { // from class: android.net.ConnectivityManager.1
            @Override // android.os.INetworkActivityListener
            public void onNetworkActive() throws RemoteException {
                onNetworkActiveListener.onNetworkActive();
            }
        };
        try {
            getNetworkManagementService().registerNetworkActivityListener(stub);
            this.mNetworkActivityListeners.put(onNetworkActiveListener, stub);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeDefaultNetworkActiveListener(OnNetworkActiveListener onNetworkActiveListener) {
        INetworkActivityListener iNetworkActivityListener = this.mNetworkActivityListeners.get(onNetworkActiveListener);
        Preconditions.checkArgument(iNetworkActivityListener != null, "Listener was not registered.");
        try {
            getNetworkManagementService().unregisterNetworkActivityListener(iNetworkActivityListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isDefaultNetworkActive() {
        try {
            return getNetworkManagementService().isNetworkActive();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ConnectivityManager(Context context, IConnectivityManager iConnectivityManager) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "missing context");
        this.mService = (IConnectivityManager) Preconditions.checkNotNull(iConnectivityManager, "missing IConnectivityManager");
        this.mTetheringManager = (TetheringManager) this.mContext.getSystemService(Context.TETHERING_SERVICE);
        sInstance = this;
    }

    public static ConnectivityManager from(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    public NetworkRequest getDefaultRequest() {
        try {
            return this.mService.getDefaultRequest();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static final void enforceChangePermission(Context context) {
        int callingUid = Binder.getCallingUid();
        Settings.checkAndNoteChangeNetworkStateOperation(context, callingUid, Settings.getPackageNameForUid(context, callingUid), true);
    }

    @Deprecated
    static ConnectivityManager getInstanceOrNull() {
        return sInstance;
    }

    @Deprecated
    private static ConnectivityManager getInstance() {
        if (getInstanceOrNull() == null) {
            throw new IllegalStateException("No ConnectivityManager yet constructed");
        }
        return getInstanceOrNull();
    }

    @Deprecated
    public String[] getTetherableIfaces() {
        return this.mTetheringManager.getTetherableIfaces();
    }

    @Deprecated
    public String[] getTetheredIfaces() {
        return this.mTetheringManager.getTetheredIfaces();
    }

    @Deprecated
    public String[] getTetheringErroredIfaces() {
        return this.mTetheringManager.getTetheringErroredIfaces();
    }

    @Deprecated
    public String[] getTetheredDhcpRanges() {
        throw new UnsupportedOperationException("getTetheredDhcpRanges is not supported");
    }

    @Deprecated
    public int tether(String str) {
        return this.mTetheringManager.tether(str);
    }

    @Deprecated
    public int untether(String str) {
        return this.mTetheringManager.untether(str);
    }

    @SystemApi
    public boolean isTetheringSupported() {
        return this.mTetheringManager.isTetheringSupported();
    }

    @SystemApi
    @Deprecated
    public void startTethering(int i, boolean z, OnStartTetheringCallback onStartTetheringCallback) {
        startTethering(i, z, onStartTetheringCallback, null);
    }

    @SystemApi
    @Deprecated
    public void startTethering(int i, boolean z, final OnStartTetheringCallback onStartTetheringCallback, final Handler handler) {
        Preconditions.checkNotNull(onStartTetheringCallback, "OnStartTetheringCallback cannot be null.");
        Executor executor = new Executor() { // from class: android.net.ConnectivityManager.2
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                Handler handler2 = handler;
                if (handler2 == null) {
                    runnable.run();
                } else {
                    handler2.post(runnable);
                }
            }
        };
        TetheringManager.StartTetheringCallback startTetheringCallback = new TetheringManager.StartTetheringCallback() { // from class: android.net.ConnectivityManager.3
            public void onTetheringStarted() {
                onStartTetheringCallback.onTetheringStarted();
            }

            public void onTetheringFailed(int i2) {
                onStartTetheringCallback.onTetheringFailed();
            }
        };
        this.mTetheringManager.startTethering(new TetheringManager.TetheringRequest.Builder(i).setShouldShowEntitlementUi(z).build(), executor, startTetheringCallback);
    }

    @SystemApi
    @Deprecated
    public void stopTethering(int i) {
        this.mTetheringManager.stopTethering(i);
    }

    @SystemApi
    @Deprecated
    public void registerTetheringEventCallback(Executor executor, final OnTetheringEventCallback onTetheringEventCallback) {
        Preconditions.checkNotNull(onTetheringEventCallback, "OnTetheringEventCallback cannot be null.");
        TetheringManager.TetheringEventCallback tetheringEventCallback = new TetheringManager.TetheringEventCallback() { // from class: android.net.ConnectivityManager.4
            public void onUpstreamChanged(Network network) {
                onTetheringEventCallback.onUpstreamChanged(network);
            }
        };
        synchronized (this.mTetheringEventCallbacks) {
            this.mTetheringEventCallbacks.put(onTetheringEventCallback, tetheringEventCallback);
            this.mTetheringManager.registerTetheringEventCallback(executor, tetheringEventCallback);
        }
    }

    @SystemApi
    @Deprecated
    public void unregisterTetheringEventCallback(OnTetheringEventCallback onTetheringEventCallback) {
        Objects.requireNonNull(onTetheringEventCallback, "The callback must be non-null");
        synchronized (this.mTetheringEventCallbacks) {
            this.mTetheringManager.unregisterTetheringEventCallback(this.mTetheringEventCallbacks.remove(onTetheringEventCallback));
        }
    }

    @Deprecated
    public String[] getTetherableUsbRegexs() {
        return this.mTetheringManager.getTetherableUsbRegexs();
    }

    @Deprecated
    public String[] getTetherableWifiRegexs() {
        return this.mTetheringManager.getTetherableWifiRegexs();
    }

    @Deprecated
    public String[] getTetherableBluetoothRegexs() {
        return this.mTetheringManager.getTetherableBluetoothRegexs();
    }

    @Deprecated
    public int setUsbTethering(boolean z) {
        return this.mTetheringManager.setUsbTethering(z);
    }

    @Deprecated
    public int getLastTetherError(String str) {
        int lastTetherError = this.mTetheringManager.getLastTetherError(str);
        if (lastTetherError == 16) {
            return 1;
        }
        return lastTetherError;
    }

    /* JADX INFO: renamed from: android.net.ConnectivityManager$5, reason: invalid class name */
    class AnonymousClass5 extends ResultReceiver {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ OnTetheringEntitlementResultListener val$listener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Handler handler, Executor executor, OnTetheringEntitlementResultListener onTetheringEntitlementResultListener) {
            super(handler);
            this.val$executor = executor;
            this.val$listener = onTetheringEntitlementResultListener;
        }

        @Override // android.os.ResultReceiver
        protected void onReceiveResult(final int i, Bundle bundle) {
            final Executor executor = this.val$executor;
            final OnTetheringEntitlementResultListener onTetheringEntitlementResultListener = this.val$listener;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityManager$5$KgnVAIsLrj_uzlbjjmtk8vq4TCc
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    executor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityManager$5$OMSqRWp-UPz6QSkiXvLAuEDgkhc
                        @Override // java.lang.Runnable
                        public final void run() {
                            onTetheringEntitlementResultListener.onTetheringEntitlementResult(i);
                        }
                    });
                }
            });
        }
    }

    @SystemApi
    @Deprecated
    public void getLatestTetheringEntitlementResult(int i, boolean z, Executor executor, OnTetheringEntitlementResultListener onTetheringEntitlementResultListener) {
        Preconditions.checkNotNull(onTetheringEntitlementResultListener, "TetheringEntitlementResultListener cannot be null.");
        this.mTetheringManager.requestLatestTetheringEntitlementResult(i, new AnonymousClass5(null, executor, onTetheringEntitlementResultListener), z);
    }

    public void reportInetCondition(int i, int i2) {
        printStackTrace();
        try {
            this.mService.reportInetCondition(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void reportBadNetwork(Network network) {
        printStackTrace();
        try {
            this.mService.reportNetworkConnectivity(network, true);
            this.mService.reportNetworkConnectivity(network, false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void reportNetworkConnectivity(Network network, boolean z) {
        printStackTrace();
        try {
            this.mService.reportNetworkConnectivity(network, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setGlobalProxy(ProxyInfo proxyInfo) {
        try {
            this.mService.setGlobalProxy(proxyInfo);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ProxyInfo getGlobalProxy() {
        try {
            return this.mService.getGlobalProxy();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ProxyInfo getProxyForNetwork(Network network) {
        try {
            return this.mService.getProxyForNetwork(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ProxyInfo getDefaultProxy() {
        return getProxyForNetwork(getBoundNetworkForProcess());
    }

    @Deprecated
    public boolean isNetworkSupported(int i) {
        try {
            return this.mService.isNetworkSupported(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isActiveNetworkMetered() {
        try {
            return this.mService.isActiveNetworkMetered();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean updateLockdownVpn() {
        try {
            return this.mService.updateLockdownVpn();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int checkMobileProvisioning(int i) {
        try {
            return this.mService.checkMobileProvisioning(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getMobileProvisioningUrl() {
        try {
            return this.mService.getMobileProvisioningUrl();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void setProvisioningNotificationVisible(boolean z, int i, String str) {
        try {
            this.mService.setProvisioningNotificationVisible(z, i, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setAirplaneMode(boolean z) {
        try {
            this.mService.setAirplaneMode(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int registerNetworkFactory(Messenger messenger, String str) {
        try {
            return this.mService.registerNetworkFactory(messenger, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterNetworkFactory(Messenger messenger) {
        try {
            this.mService.unregisterNetworkFactory(messenger);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int registerNetworkProvider(NetworkProvider networkProvider) {
        if (networkProvider.getProviderId() != -1) {
            throw new IllegalStateException("NetworkProviders can only be registered once");
        }
        try {
            networkProvider.setProviderId(this.mService.registerNetworkProvider(networkProvider.getMessenger(), networkProvider.getName()));
            return networkProvider.getProviderId();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void unregisterNetworkProvider(NetworkProvider networkProvider) {
        try {
            this.mService.unregisterNetworkProvider(networkProvider.getMessenger());
            networkProvider.setProviderId(-1);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void declareNetworkRequestUnfulfillable(NetworkRequest networkRequest) {
        try {
            this.mService.declareNetworkRequestUnfulfillable(networkRequest);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Network registerNetworkAgent(Messenger messenger, NetworkInfo networkInfo, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i, NetworkAgentConfig networkAgentConfig) {
        return registerNetworkAgent(messenger, networkInfo, linkProperties, networkCapabilities, i, networkAgentConfig, -1);
    }

    public Network registerNetworkAgent(Messenger messenger, NetworkInfo networkInfo, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i, NetworkAgentConfig networkAgentConfig, int i2) {
        try {
            return this.mService.registerNetworkAgent(messenger, networkInfo, linkProperties, networkCapabilities, i, networkAgentConfig, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static class NetworkCallback {
        private NetworkRequest networkRequest;

        public void onAvailable(Network network) {
        }

        public void onBlockedStatusChanged(Network network, boolean z) {
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        }

        public void onLosing(Network network, int i) {
        }

        public void onLost(Network network) {
        }

        public void onNetworkResumed(Network network) {
        }

        public void onNetworkSuspended(Network network) {
        }

        public void onPreCheck(Network network) {
        }

        public void onUnavailable() {
        }

        public void onAvailable(Network network, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, boolean z) {
            onAvailable(network);
            if (!networkCapabilities.hasCapability(21)) {
                onNetworkSuspended(network);
            }
            onCapabilitiesChanged(network, networkCapabilities);
            onLinkPropertiesChanged(network, linkProperties);
            onBlockedStatusChanged(network, z);
        }
    }

    private static RuntimeException convertServiceException(ServiceSpecificException serviceSpecificException) {
        if (serviceSpecificException.errorCode == 1) {
            return new TooManyRequestsException();
        }
        Log.w(TAG, "Unknown service error code " + serviceSpecificException.errorCode);
        return new RuntimeException(serviceSpecificException);
    }

    public static String getCallbackName(int i) {
        switch (i) {
            case CALLBACK_PRECHECK /* 524289 */:
                return "CALLBACK_PRECHECK";
            case 524290:
                return "CALLBACK_AVAILABLE";
            case 524291:
                return "CALLBACK_LOSING";
            case 524292:
                return "CALLBACK_LOST";
            case CALLBACK_UNAVAIL /* 524293 */:
                return "CALLBACK_UNAVAIL";
            case CALLBACK_CAP_CHANGED /* 524294 */:
                return "CALLBACK_CAP_CHANGED";
            case CALLBACK_IP_CHANGED /* 524295 */:
                return "CALLBACK_IP_CHANGED";
            case EXPIRE_LEGACY_REQUEST /* 524296 */:
                return "EXPIRE_LEGACY_REQUEST";
            case CALLBACK_SUSPENDED /* 524297 */:
                return "CALLBACK_SUSPENDED";
            case CALLBACK_RESUMED /* 524298 */:
                return "CALLBACK_RESUMED";
            case CALLBACK_BLK_CHANGED /* 524299 */:
                return "CALLBACK_BLK_CHANGED";
            default:
                return Integer.toString(i);
        }
    }

    private class CallbackHandler extends Handler {
        private static final boolean DBG = false;
        private static final String TAG = "ConnectivityManager.CallbackHandler";

        CallbackHandler(Looper looper) {
            super(looper);
        }

        CallbackHandler(ConnectivityManager connectivityManager, Handler handler) {
            this(((Handler) Preconditions.checkNotNull(handler, "Handler cannot be null.")).getLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == ConnectivityManager.EXPIRE_LEGACY_REQUEST) {
                ConnectivityManager.this.expireRequest((NetworkCapabilities) message.obj, message.arg1);
                return;
            }
            NetworkRequest networkRequest = (NetworkRequest) getObject(message, NetworkRequest.class);
            Network network = (Network) getObject(message, Network.class);
            synchronized (ConnectivityManager.sCallbacks) {
                NetworkCallback networkCallback = (NetworkCallback) ConnectivityManager.sCallbacks.get(networkRequest);
                if (networkCallback == null) {
                    Log.w(TAG, "callback not found for " + ConnectivityManager.getCallbackName(message.what) + " message");
                    return;
                }
                if (message.what == 524293) {
                    ConnectivityManager.sCallbacks.remove(networkRequest);
                    networkCallback.networkRequest = ConnectivityManager.ALREADY_UNREGISTERED;
                }
                switch (message.what) {
                    case ConnectivityManager.CALLBACK_PRECHECK /* 524289 */:
                        networkCallback.onPreCheck(network);
                        return;
                    case 524290:
                        networkCallback.onAvailable(network, (NetworkCapabilities) getObject(message, NetworkCapabilities.class), (LinkProperties) getObject(message, LinkProperties.class), message.arg1 != 0);
                        return;
                    case 524291:
                        networkCallback.onLosing(network, message.arg1);
                        return;
                    case 524292:
                        networkCallback.onLost(network);
                        return;
                    case ConnectivityManager.CALLBACK_UNAVAIL /* 524293 */:
                        networkCallback.onUnavailable();
                        return;
                    case ConnectivityManager.CALLBACK_CAP_CHANGED /* 524294 */:
                        networkCallback.onCapabilitiesChanged(network, (NetworkCapabilities) getObject(message, NetworkCapabilities.class));
                        return;
                    case ConnectivityManager.CALLBACK_IP_CHANGED /* 524295 */:
                        networkCallback.onLinkPropertiesChanged(network, (LinkProperties) getObject(message, LinkProperties.class));
                        return;
                    case ConnectivityManager.EXPIRE_LEGACY_REQUEST /* 524296 */:
                    default:
                        return;
                    case ConnectivityManager.CALLBACK_SUSPENDED /* 524297 */:
                        networkCallback.onNetworkSuspended(network);
                        return;
                    case ConnectivityManager.CALLBACK_RESUMED /* 524298 */:
                        networkCallback.onNetworkResumed(network);
                        return;
                    case ConnectivityManager.CALLBACK_BLK_CHANGED /* 524299 */:
                        networkCallback.onBlockedStatusChanged(network, message.arg1 != 0);
                        return;
                }
            }
        }

        private <T> T getObject(Message message, Class<T> cls) {
            return (T) message.getData().getParcelable(cls.getSimpleName());
        }
    }

    private CallbackHandler getDefaultHandler() {
        CallbackHandler callbackHandler;
        synchronized (sCallbacks) {
            if (sCallbackHandler == null) {
                sCallbackHandler = new CallbackHandler(ConnectivityThread.getInstanceLooper());
            }
            callbackHandler = sCallbackHandler;
        }
        return callbackHandler;
    }

    private NetworkRequest sendRequestForNetwork(NetworkCapabilities networkCapabilities, NetworkCallback networkCallback, int i, int i2, int i3, CallbackHandler callbackHandler) {
        NetworkRequest networkRequestRequestNetwork;
        printStackTrace();
        checkCallbackNotNull(networkCallback);
        Preconditions.checkArgument(i2 == 2 || networkCapabilities != null, "null NetworkCapabilities");
        String opPackageName = this.mContext.getOpPackageName();
        try {
            synchronized (sCallbacks) {
                if (networkCallback.networkRequest != null && networkCallback.networkRequest != ALREADY_UNREGISTERED) {
                    Log.e(TAG, "NetworkCallback was already registered");
                }
                Messenger messenger = new Messenger(callbackHandler);
                Binder binder = new Binder();
                if (i2 == 1) {
                    networkRequestRequestNetwork = this.mService.listenForNetwork(networkCapabilities, messenger, binder, opPackageName);
                } else {
                    networkRequestRequestNetwork = this.mService.requestNetwork(networkCapabilities, messenger, i, binder, i3, opPackageName);
                }
                if (networkRequestRequestNetwork != null) {
                    sCallbacks.put(networkRequestRequestNetwork, networkCallback);
                }
                networkCallback.networkRequest = networkRequestRequestNetwork;
            }
            return networkRequestRequestNetwork;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw convertServiceException(e2);
        }
    }

    @SystemApi
    public void requestNetwork(NetworkRequest networkRequest, int i, int i2, Handler handler, NetworkCallback networkCallback) {
        if (i2 == -1) {
            throw new IllegalArgumentException("TYPE_NONE is meaningless legacy type");
        }
        sendRequestForNetwork(networkRequest.networkCapabilities, networkCallback, i, 2, i2, new CallbackHandler(this, handler));
    }

    public void requestNetwork(NetworkRequest networkRequest, NetworkCallback networkCallback) {
        requestNetwork(networkRequest, networkCallback, getDefaultHandler());
    }

    public void requestNetwork(NetworkRequest networkRequest, NetworkCallback networkCallback, Handler handler) {
        sendRequestForNetwork(networkRequest.networkCapabilities, networkCallback, 0, 2, -1, new CallbackHandler(this, handler));
    }

    public void requestNetwork(NetworkRequest networkRequest, NetworkCallback networkCallback, int i) {
        checkTimeout(i);
        sendRequestForNetwork(networkRequest.networkCapabilities, networkCallback, i, 2, -1, getDefaultHandler());
    }

    public void requestNetwork(NetworkRequest networkRequest, NetworkCallback networkCallback, Handler handler, int i) {
        checkTimeout(i);
        sendRequestForNetwork(networkRequest.networkCapabilities, networkCallback, i, 2, -1, new CallbackHandler(this, handler));
    }

    public void requestNetwork(NetworkRequest networkRequest, PendingIntent pendingIntent) {
        printStackTrace();
        checkPendingIntentNotNull(pendingIntent);
        try {
            this.mService.pendingRequestForNetwork(networkRequest.networkCapabilities, pendingIntent, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw convertServiceException(e2);
        }
    }

    public void releaseNetworkRequest(PendingIntent pendingIntent) {
        printStackTrace();
        checkPendingIntentNotNull(pendingIntent);
        try {
            this.mService.releasePendingNetworkRequest(pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static void checkPendingIntentNotNull(PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent cannot be null.");
    }

    private static void checkCallbackNotNull(NetworkCallback networkCallback) {
        Preconditions.checkNotNull(networkCallback, "null NetworkCallback");
    }

    private static void checkTimeout(int i) {
        Preconditions.checkArgumentPositive(i, "timeoutMs must be strictly positive.");
    }

    public void registerNetworkCallback(NetworkRequest networkRequest, NetworkCallback networkCallback) {
        registerNetworkCallback(networkRequest, networkCallback, getDefaultHandler());
    }

    public void registerNetworkCallback(NetworkRequest networkRequest, NetworkCallback networkCallback, Handler handler) {
        sendRequestForNetwork(networkRequest.networkCapabilities, networkCallback, 0, 1, -1, new CallbackHandler(this, handler));
    }

    public void registerNetworkCallback(NetworkRequest networkRequest, PendingIntent pendingIntent) {
        printStackTrace();
        checkPendingIntentNotNull(pendingIntent);
        try {
            this.mService.pendingListenForNetwork(networkRequest.networkCapabilities, pendingIntent, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw convertServiceException(e2);
        }
    }

    public void registerDefaultNetworkCallback(NetworkCallback networkCallback) {
        registerDefaultNetworkCallback(networkCallback, getDefaultHandler());
    }

    public void registerDefaultNetworkCallback(NetworkCallback networkCallback, Handler handler) {
        sendRequestForNetwork(null, networkCallback, 0, 2, -1, new CallbackHandler(this, handler));
    }

    public boolean requestBandwidthUpdate(Network network) {
        try {
            return this.mService.requestBandwidthUpdate(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterNetworkCallback(NetworkCallback networkCallback) {
        printStackTrace();
        checkCallbackNotNull(networkCallback);
        ArrayList<NetworkRequest> arrayList = new ArrayList();
        synchronized (sCallbacks) {
            Preconditions.checkArgument(networkCallback.networkRequest != null, "NetworkCallback was not registered");
            if (networkCallback.networkRequest == ALREADY_UNREGISTERED) {
                Log.d(TAG, "NetworkCallback was already unregistered");
                return;
            }
            for (Map.Entry<NetworkRequest, NetworkCallback> entry : sCallbacks.entrySet()) {
                if (entry.getValue() == networkCallback) {
                    arrayList.add(entry.getKey());
                }
            }
            for (NetworkRequest networkRequest : arrayList) {
                try {
                    this.mService.releaseNetworkRequest(networkRequest);
                    sCallbacks.remove(networkRequest);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            networkCallback.networkRequest = ALREADY_UNREGISTERED;
        }
    }

    public void unregisterNetworkCallback(PendingIntent pendingIntent) {
        releaseNetworkRequest(pendingIntent);
    }

    public void setAcceptUnvalidated(Network network, boolean z, boolean z2) {
        try {
            this.mService.setAcceptUnvalidated(network, z, z2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setAcceptPartialConnectivity(Network network, boolean z, boolean z2) {
        try {
            this.mService.setAcceptPartialConnectivity(network, z, z2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setAvoidUnvalidated(Network network) {
        try {
            this.mService.setAvoidUnvalidated(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void startCaptivePortalApp(Network network) {
        try {
            this.mService.startCaptivePortalApp(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void startCaptivePortalApp(Network network, Bundle bundle) {
        try {
            this.mService.startCaptivePortalAppInternal(network, bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean shouldAvoidBadWifi() {
        try {
            return this.mService.shouldAvoidBadWifi();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getMultipathPreference(Network network) {
        try {
            return this.mService.getMultipathPreference(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void factoryReset() {
        try {
            this.mService.factoryReset();
            this.mTetheringManager.stopAllTethering();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean bindProcessToNetwork(Network network) {
        return setProcessDefaultNetwork(network);
    }

    @Deprecated
    public static boolean setProcessDefaultNetwork(Network network) {
        int netIdForResolv = network == null ? 0 : network.netId;
        boolean z = netIdForResolv == NetworkUtils.getBoundNetworkForProcess();
        if (netIdForResolv != 0) {
            netIdForResolv = network.getNetIdForResolv();
        }
        if (!NetworkUtils.bindProcessToNetwork(netIdForResolv)) {
            return false;
        }
        if (!z) {
            try {
                Proxy.setHttpProxySystemProperty(getInstance().getDefaultProxy());
            } catch (SecurityException e) {
                Log.e(TAG, "Can't set proxy properties", e);
            }
            InetAddress.clearDnsCache();
            NetworkEventDispatcher.getInstance().onNetworkConfigurationChanged();
        }
        return true;
    }

    public Network getBoundNetworkForProcess() {
        return getProcessDefaultNetwork();
    }

    @Deprecated
    public static Network getProcessDefaultNetwork() {
        int boundNetworkForProcess = NetworkUtils.getBoundNetworkForProcess();
        if (boundNetworkForProcess == 0) {
            return null;
        }
        return new Network(boundNetworkForProcess);
    }

    private void unsupportedStartingFrom(int i) {
        if (Process.myUid() != 1000 && this.mContext.getApplicationInfo().targetSdkVersion >= i) {
            throw new UnsupportedOperationException("This method is not supported in target SDK version " + i + " and above");
        }
    }

    private void checkLegacyRoutingApiAccess() {
        unsupportedStartingFrom(23);
    }

    @Deprecated
    public static boolean setProcessDefaultNetworkForHostResolution(Network network) {
        return NetworkUtils.bindProcessToNetworkForHostResolution(network == null ? 0 : network.getNetIdForResolv());
    }

    private INetworkPolicyManager getNetworkPolicyManager() {
        synchronized (this) {
            if (this.mNPManager != null) {
                return this.mNPManager;
            }
            INetworkPolicyManager iNetworkPolicyManagerAsInterface = INetworkPolicyManager.Stub.asInterface(ServiceManager.getService(Context.NETWORK_POLICY_SERVICE));
            this.mNPManager = iNetworkPolicyManagerAsInterface;
            return iNetworkPolicyManagerAsInterface;
        }
    }

    public int getRestrictBackgroundStatus() {
        try {
            return getNetworkPolicyManager().getRestrictBackgroundByCaller();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public byte[] getNetworkWatchlistConfigHash() {
        try {
            return this.mService.getNetworkWatchlistConfigHash();
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to get watchlist config hash");
            throw e.rethrowFromSystemServer();
        }
    }

    public int getConnectionOwnerUid(int i, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        try {
            return this.mService.getConnectionOwnerUid(new ConnectionInfo(i, inetSocketAddress, inetSocketAddress2));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void printStackTrace() {
        String string;
        if (DEBUG) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 3; i < stackTrace.length && (string = stackTrace[i].toString()) != null && !string.contains("android.os"); i++) {
                stringBuffer.append(" [");
                stringBuffer.append(string);
                stringBuffer.append("]");
            }
            Log.d(TAG, "StackLog:" + stringBuffer.toString());
        }
    }

    public void simulateDataStall(int i, long j, Network network, PersistableBundle persistableBundle) {
        try {
            this.mService.simulateDataStall(i, j, network, persistableBundle);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }
}
