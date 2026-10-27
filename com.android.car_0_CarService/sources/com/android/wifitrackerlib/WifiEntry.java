package com.android.wifitrackerlib;

import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkUtils;
import android.net.RouteInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WifiEntry implements Comparable<WifiEntry> {
    public static final int CONNECTED_STATE_CONNECTED = 2;
    public static final int CONNECTED_STATE_CONNECTING = 1;
    public static final int CONNECTED_STATE_DISCONNECTED = 0;
    public static final int FREQUENCY_2_4_GHZ = 2400;
    public static final int FREQUENCY_5_GHZ = 5000;
    public static final int FREQUENCY_6_GHZ = 6000;
    public static final int FREQUENCY_UNKNOWN = -1;
    public static final int MAX_FREQ_24GHZ = 2500;
    public static final int MAX_FREQ_5GHZ = 5900;
    public static final int MAX_FREQ_6GHZ = 7125;
    protected static final int MAX_VERBOSE_LOG_DISPLAY_SCANRESULT_COUNT = 4;
    public static final int METERED_CHOICE_AUTO = 0;
    public static final int METERED_CHOICE_METERED = 1;
    public static final int METERED_CHOICE_UNMETERED = 2;
    public static final int MIN_FREQ_24GHZ = 2400;
    public static final int MIN_FREQ_5GHZ = 4900;
    public static final int MIN_FREQ_6GHZ = 5925;
    public static final int NUM_SECURITY_TYPES = 7;
    public static final int PRIVACY_DEVICE_MAC = 0;
    public static final int PRIVACY_RANDOMIZED_MAC = 1;
    public static final int PRIVACY_UNKNOWN = 2;
    public static final int SECURITY_EAP = 3;
    public static final int SECURITY_EAP_SUITE_B = 6;
    public static final int SECURITY_NONE = 0;
    public static final int SECURITY_OWE = 4;
    public static final int SECURITY_PSK = 2;
    public static final int SECURITY_SAE = 5;
    public static final int SECURITY_WEP = 1;
    public static final int SPEED_FAST = 20;
    public static final int SPEED_MODERATE = 10;
    public static final int SPEED_NONE = 0;
    public static final int SPEED_SLOW = 5;
    public static final int SPEED_VERY_FAST = 30;
    public static final int WIFI_LEVEL_MAX = 4;
    public static final int WIFI_LEVEL_MIN = 0;
    public static final int WIFI_LEVEL_UNREACHABLE = -1;
    protected Handler mCallbackHandler;
    protected ConnectCallback mConnectCallback;
    protected ConnectedInfo mConnectedInfo;
    protected DisconnectCallback mDisconnectCallback;
    final boolean mForSavedNetworksPage;
    protected ForgetCallback mForgetCallback;
    private boolean mIsDefaultNetwork;
    protected boolean mIsLowQuality;
    private boolean mIsValidated;
    private WifiEntryCallback mListener;
    protected NetworkCapabilities mNetworkCapabilities;
    protected NetworkInfo mNetworkInfo;
    protected WifiNetworkScoreCache mScoreCache;
    protected WifiInfo mWifiInfo;
    protected final WifiManager mWifiManager;
    protected int mLevel = -1;
    protected int mSpeed = 0;
    protected boolean mCalledConnect = false;
    protected boolean mCalledDisconnect = false;

    public interface ConnectCallback {
        public static final int CONNECT_STATUS_FAILURE_NO_CONFIG = 1;
        public static final int CONNECT_STATUS_FAILURE_UNKNOWN = 2;
        public static final int CONNECT_STATUS_SUCCESS = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ConnectStatus {
        }

        void onConnectResult(int i);
    }

    public static class ConnectedInfo {
        public int frequencyMhz;
        public String gateway;
        public String ipAddress;
        public int linkSpeedMbps;
        public String subnetMask;
        public List<String> dnsServers = new ArrayList();
        public List<String> ipv6Addresses = new ArrayList();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectedState {
    }

    public interface DisconnectCallback {
        public static final int DISCONNECT_STATUS_FAILURE_UNKNOWN = 1;
        public static final int DISCONNECT_STATUS_SUCCESS = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DisconnectStatus {
        }

        void onDisconnectResult(int i);
    }

    public interface ForgetCallback {
        public static final int FORGET_STATUS_FAILURE_UNKNOWN = 1;
        public static final int FORGET_STATUS_SUCCESS = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ForgetStatus {
        }

        void onForgetResult(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Frequency {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MeteredChoice {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Privacy {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Security {
    }

    public interface SignInCallback {
        public static final int SIGNIN_STATUS_FAILURE_UNKNOWN = 1;
        public static final int SIGNIN_STATUS_SUCCESS = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface SignInStatus {
        }

        void onSignInResult(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Speed {
    }

    public interface WifiEntryCallback {
        void onUpdated();
    }

    public abstract boolean canConnect();

    public abstract boolean canDisconnect();

    public abstract boolean canEasyConnect();

    public abstract boolean canForget();

    public abstract boolean canSetAutoJoinEnabled();

    public abstract boolean canSetMeteredChoice();

    public abstract boolean canSetPrivacy();

    public abstract boolean canShare();

    public abstract boolean canSignIn();

    public abstract void connect(ConnectCallback connectCallback);

    protected abstract boolean connectionInfoMatches(WifiInfo wifiInfo, NetworkInfo networkInfo);

    public abstract void disconnect(DisconnectCallback disconnectCallback);

    public abstract void forget(ForgetCallback forgetCallback);

    public abstract String getKey();

    public abstract String getMacAddress();

    public abstract int getMeteredChoice();

    public abstract int getPrivacy();

    abstract String getScanResultDescription();

    public abstract int getSecurity();

    public abstract String getSecurityString(boolean z);

    public abstract String getSsid();

    public abstract String getSummary(boolean z);

    public abstract String getTitle();

    public abstract WifiConfiguration getWifiConfiguration();

    public abstract boolean isAutoJoinEnabled();

    public abstract boolean isExpired();

    public abstract boolean isMetered();

    public abstract boolean isSaved();

    public abstract boolean isSubscription();

    public abstract boolean isSuggestion();

    public abstract void setAutoJoinEnabled(boolean z);

    public abstract void setMeteredChoice(int i);

    public abstract void setPrivacy(int i);

    public abstract void signIn(SignInCallback signInCallback);

    WifiEntry(Handler callbackHandler, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) throws IllegalArgumentException {
        Preconditions.checkNotNull(callbackHandler, "Cannot construct with null handler!");
        Preconditions.checkNotNull(wifiManager, "Cannot construct with null WifiManager!");
        this.mCallbackHandler = callbackHandler;
        this.mForSavedNetworksPage = forSavedNetworksPage;
        this.mWifiManager = wifiManager;
        this.mScoreCache = scoreCache;
    }

    public int getConnectedState() {
        if (this.mNetworkInfo == null) {
            return 0;
        }
        switch (AnonymousClass1.$SwitchMap$android$net$NetworkInfo$DetailedState[this.mNetworkInfo.getDetailedState().ordinal()]) {
        }
        return 0;
    }

    /* JADX INFO: renamed from: com.android.wifitrackerlib.WifiEntry$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$DetailedState;

        static {
            int[] iArr = new int[NetworkInfo.DetailedState.values().length];
            $SwitchMap$android$net$NetworkInfo$DetailedState = iArr;
            try {
                iArr[NetworkInfo.DetailedState.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public String getSummary() {
        return getSummary(true);
    }

    public CharSequence getSecondSummary() {
        return "";
    }

    public int getLevel() {
        return this.mLevel;
    }

    public boolean shouldShowXLevelIcon() {
        return (getConnectedState() == 0 || (this.mIsValidated && this.mIsDefaultNetwork) || canSignIn()) ? false : true;
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public ConnectedInfo getConnectedInfo() {
        if (getConnectedState() != 2) {
            return null;
        }
        return this.mConnectedInfo;
    }

    public boolean canManageSubscription() {
        return false;
    }

    public String getHelpUriString() {
        return null;
    }

    public void manageSubscription() {
    }

    String getNetworkSelectionDescription() {
        return "";
    }

    String getNetworkCapabilityDescription() {
        StringBuilder sb = new StringBuilder();
        if (getConnectedState() == 2) {
            sb.append("isValidated:");
            sb.append(this.mIsValidated);
            sb.append(", isDefaultNetwork:");
            sb.append(this.mIsDefaultNetwork);
            sb.append(", isLowQuality:");
            sb.append(this.mIsLowQuality);
        }
        return sb.toString();
    }

    public boolean shouldEditBeforeConnect() {
        return false;
    }

    public void setListener(WifiEntryCallback listener) {
        this.mListener = listener;
    }

    protected void notifyOnUpdated() {
        if (this.mListener != null) {
            this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$Z7qIuj7K1pqbGQNunqibzqO18s0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$notifyOnUpdated$0$WifiEntry();
                }
            });
        }
    }

    public /* synthetic */ void lambda$notifyOnUpdated$0$WifiEntry() {
        this.mListener.onUpdated();
    }

    void updateConnectionInfo(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        if (wifiInfo != null && networkInfo != null && connectionInfoMatches(wifiInfo, networkInfo)) {
            this.mWifiInfo = wifiInfo;
            this.mNetworkInfo = networkInfo;
            int wifiInfoRssi = wifiInfo.getRssi();
            if (wifiInfoRssi != -127) {
                this.mLevel = this.mWifiManager.calculateSignalLevel(wifiInfoRssi);
                this.mSpeed = Utils.getSpeedFromWifiInfo(this.mScoreCache, wifiInfo);
            }
            if (getConnectedState() == 2) {
                if (this.mCalledConnect) {
                    this.mCalledConnect = false;
                    this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$mJZ4Rvce3-rMD9mRMpz-vWu5ItY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$updateConnectionInfo$1$WifiEntry();
                        }
                    });
                }
                if (this.mConnectedInfo == null) {
                    this.mConnectedInfo = new ConnectedInfo();
                }
                this.mConnectedInfo.frequencyMhz = wifiInfo.getFrequency();
                this.mConnectedInfo.linkSpeedMbps = wifiInfo.getLinkSpeed();
            }
        } else {
            this.mNetworkInfo = null;
            this.mNetworkCapabilities = null;
            this.mConnectedInfo = null;
            this.mIsValidated = false;
            this.mIsDefaultNetwork = false;
            this.mIsLowQuality = false;
            if (this.mCalledDisconnect) {
                this.mCalledDisconnect = false;
                this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$Wbjrmqbh-0TpH4DVTSRzGNL2Aks
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$updateConnectionInfo$2$WifiEntry();
                    }
                });
            }
        }
        notifyOnUpdated();
    }

    public /* synthetic */ void lambda$updateConnectionInfo$1$WifiEntry() {
        ConnectCallback connectCallback = this.mConnectCallback;
        if (connectCallback != null) {
            connectCallback.onConnectResult(0);
        }
    }

    public /* synthetic */ void lambda$updateConnectionInfo$2$WifiEntry() {
        DisconnectCallback disconnectCallback = this.mDisconnectCallback;
        if (disconnectCallback != null) {
            disconnectCallback.onDisconnectResult(0);
        }
    }

    void updateLinkProperties(LinkProperties linkProperties) {
        if (linkProperties == null || getConnectedState() != 2) {
            this.mConnectedInfo = null;
            notifyOnUpdated();
            return;
        }
        if (this.mConnectedInfo == null) {
            this.mConnectedInfo = new ConnectedInfo();
        }
        List<String> ipv6Addresses = new ArrayList<>();
        for (LinkAddress addr : linkProperties.getLinkAddresses()) {
            if (addr.getAddress() instanceof Inet4Address) {
                this.mConnectedInfo.ipAddress = addr.getAddress().getHostAddress();
                try {
                    InetAddress all = InetAddress.getByAddress(new byte[]{-1, -1, -1, -1});
                    this.mConnectedInfo.subnetMask = NetworkUtils.getNetworkPart(all, addr.getPrefixLength()).getHostAddress();
                } catch (UnknownHostException e) {
                }
            } else if (addr.getAddress() instanceof Inet6Address) {
                ipv6Addresses.add(addr.getAddress().getHostAddress());
            }
        }
        this.mConnectedInfo.ipv6Addresses = ipv6Addresses;
        Iterator<RouteInfo> it = linkProperties.getRoutes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RouteInfo routeInfo = it.next();
            if (routeInfo.isIPv4Default() && routeInfo.hasGateway()) {
                this.mConnectedInfo.gateway = routeInfo.getGateway().getHostAddress();
                break;
            }
        }
        this.mConnectedInfo.dnsServers = (List) linkProperties.getDnsServers().stream().map(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$XZAGhHrbkIDyusER4MAM6luKcT0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InetAddress) obj).getHostAddress();
            }
        }).collect(Collectors.toList());
        notifyOnUpdated();
    }

    void setIsDefaultNetwork(boolean isDefaultNetwork) {
        this.mIsDefaultNetwork = isDefaultNetwork;
        notifyOnUpdated();
    }

    void setIsLowQuality(boolean isLowQuality) {
        this.mIsLowQuality = isLowQuality;
    }

    void updateNetworkCapabilities(NetworkCapabilities capabilities) {
        this.mNetworkCapabilities = capabilities;
        if (this.mConnectedInfo == null) {
            return;
        }
        this.mIsValidated = capabilities != null && capabilities.hasCapability(16);
        notifyOnUpdated();
    }

    String getWifiInfoDescription() {
        StringJoiner sj = new StringJoiner(" ");
        if (getConnectedState() == 2 && this.mWifiInfo != null) {
            sj.add("f = " + this.mWifiInfo.getFrequency());
            String bssid = this.mWifiInfo.getBSSID();
            if (bssid != null) {
                sj.add(bssid);
            }
            sj.add("standard = " + this.mWifiInfo.getWifiStandard());
            sj.add("rssi = " + this.mWifiInfo.getRssi());
            sj.add("score = " + this.mWifiInfo.getScore());
            sj.add(String.format(" tx=%.1f,", Double.valueOf(this.mWifiInfo.getSuccessfulTxPacketsPerSecond())));
            sj.add(String.format("%.1f,", Double.valueOf(this.mWifiInfo.getRetriedTxPacketsPerSecond())));
            sj.add(String.format("%.1f ", Double.valueOf(this.mWifiInfo.getLostTxPacketsPerSecond())));
            sj.add(String.format("rx=%.1f", Double.valueOf(this.mWifiInfo.getSuccessfulRxPacketsPerSecond())));
        }
        return sj.toString();
    }

    protected class ConnectActionListener implements WifiManager.ActionListener {
        protected ConnectActionListener() {
        }

        public void onSuccess() {
            WifiEntry.this.mCalledConnect = true;
            WifiEntry.this.mCallbackHandler.postDelayed(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$ConnectActionListener$5ELC6BF3FPbf8_YJTi4LLio_uKE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$0$WifiEntry$ConnectActionListener();
                }
            }, 10000L);
        }

        public /* synthetic */ void lambda$onSuccess$0$WifiEntry$ConnectActionListener() {
            if (WifiEntry.this.mConnectCallback != null && WifiEntry.this.mCalledConnect && WifiEntry.this.getConnectedState() == 0) {
                WifiEntry.this.mConnectCallback.onConnectResult(2);
                WifiEntry.this.mCalledConnect = false;
            }
        }

        public void onFailure(int i) {
            WifiEntry.this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$ConnectActionListener$rNyjcSXqEa1w6d60US0s2fEnAoo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFailure$1$WifiEntry$ConnectActionListener();
                }
            });
        }

        public /* synthetic */ void lambda$onFailure$1$WifiEntry$ConnectActionListener() {
            if (WifiEntry.this.mConnectCallback != null) {
                ConnectCallback connectCallback = WifiEntry.this.mConnectCallback;
                ConnectCallback connectCallback2 = WifiEntry.this.mConnectCallback;
                connectCallback.onConnectResult(2);
            }
        }
    }

    protected class ForgetActionListener implements WifiManager.ActionListener {
        protected ForgetActionListener() {
        }

        public void onSuccess() {
            WifiEntry.this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$ForgetActionListener$SZ0IZPl_pQYHOFIlfk05jMAoIag
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$0$WifiEntry$ForgetActionListener();
                }
            });
        }

        public /* synthetic */ void lambda$onSuccess$0$WifiEntry$ForgetActionListener() {
            if (WifiEntry.this.mForgetCallback != null) {
                WifiEntry.this.mForgetCallback.onForgetResult(0);
            }
        }

        public void onFailure(int i) {
            WifiEntry.this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$ForgetActionListener$B2flgYn-ZZJGOI7c8yNLpXNeAXg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFailure$1$WifiEntry$ForgetActionListener();
                }
            });
        }

        public /* synthetic */ void lambda$onFailure$1$WifiEntry$ForgetActionListener() {
            if (WifiEntry.this.mForgetCallback != null) {
                WifiEntry.this.mForgetCallback.onForgetResult(1);
            }
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(WifiEntry other) {
        if (getLevel() != -1 && other.getLevel() == -1) {
            return -1;
        }
        if (getLevel() == -1 && other.getLevel() != -1) {
            return 1;
        }
        if (isSubscription() && !other.isSubscription()) {
            return -1;
        }
        if (!isSubscription() && other.isSubscription()) {
            return 1;
        }
        if (isSaved() && !other.isSaved()) {
            return -1;
        }
        if (!isSaved() && other.isSaved()) {
            return 1;
        }
        if (isSuggestion() && !other.isSuggestion()) {
            return -1;
        }
        if (!isSuggestion() && other.isSuggestion()) {
            return 1;
        }
        if (getLevel() > other.getLevel()) {
            return -1;
        }
        if (getLevel() < other.getLevel()) {
            return 1;
        }
        return getTitle().compareTo(other.getTitle());
    }

    public boolean equals(Object other) {
        if (other instanceof WifiEntry) {
            return getKey().equals(((WifiEntry) other).getKey());
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append(",title:");
        sb.append(getTitle());
        sb.append(",summary:");
        sb.append(getSummary());
        sb.append(",isSaved:");
        sb.append(isSaved());
        sb.append(",isSubscription:");
        sb.append(isSubscription());
        sb.append(",isSuggestion:");
        sb.append(isSuggestion());
        sb.append(",level:");
        sb.append(getLevel());
        sb.append(shouldShowXLevelIcon() ? "X" : "");
        sb.append(",security:");
        sb.append(getSecurity());
        sb.append(",connected:");
        sb.append(getConnectedState() == 2 ? "true" : "false");
        sb.append(",connectedInfo:");
        sb.append(getConnectedInfo());
        sb.append(",isValidated:");
        sb.append(this.mIsValidated);
        sb.append(",isDefaultNetwork:");
        sb.append(this.mIsDefaultNetwork);
        return sb.toString();
    }
}
