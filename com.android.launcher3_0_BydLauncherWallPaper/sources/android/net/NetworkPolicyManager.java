package android.net;

import android.content.Context;
import android.net.INetworkPolicyListener;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.keystore.KeyProperties;
import android.telephony.SubscriptionPlan;
import android.util.DebugUtils;
import android.util.Pair;
import android.util.Range;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkPolicyManager {
    private static final boolean ALLOW_PLATFORM_APP_POLICY = true;
    public static final String EXTRA_NETWORK_TEMPLATE = "android.net.NETWORK_TEMPLATE";
    public static final String FIREWALL_CHAIN_NAME_DOZABLE = "dozable";
    public static final String FIREWALL_CHAIN_NAME_NONE = "none";
    public static final String FIREWALL_CHAIN_NAME_POWERSAVE = "powersave";
    public static final String FIREWALL_CHAIN_NAME_STANDBY = "standby";
    public static final int FIREWALL_RULE_DEFAULT = 0;
    public static final int FOREGROUND_THRESHOLD_STATE = 5;
    public static final int MASK_ALL_NETWORKS = 240;
    public static final int MASK_METERED_NETWORKS = 15;
    public static final int POLICY_ALLOW_METERED_BACKGROUND = 4;
    public static final int POLICY_DIABLE_METERED = 9;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
    public static final int RULE_ALLOW_ALL = 32;
    public static final int RULE_ALLOW_METERED = 1;
    public static final int RULE_NONE = 0;
    public static final int RULE_REJECT_ALL = 64;
    public static final int RULE_REJECT_METERED = 4;
    public static final int RULE_TEMPORARY_ALLOW_METERED = 2;
    public static final int SUBSCRIPTION_OVERRIDE_CONGESTED = 2;
    public static final int SUBSCRIPTION_OVERRIDE_UNMETERED = 1;
    private final Map<SubscriptionCallback, SubscriptionCallbackProxy> mCallbackMap = new ConcurrentHashMap();
    private final Context mContext;
    private INetworkPolicyManager mService;

    public static class Listener extends INetworkPolicyListener.Stub {
        @Override // android.net.INetworkPolicyListener
        public void onMeteredIfacesChanged(String[] strArr) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onRestrictBackgroundChanged(boolean z) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onSubscriptionOverride(int i, int i2, int i3) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onUidPoliciesChanged(int i, int i2) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onUidRulesChanged(int i, int i2) {
        }
    }

    public static class SubscriptionCallback {
        public void onSubscriptionOverride(int i, int i2, int i3) {
        }

        public void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SubscriptionOverrideMask {
    }

    public static boolean isProcStateAllowedWhileIdleOrPowerSaveMode(int i) {
        return i <= 5;
    }

    public static boolean isProcStateAllowedWhileOnRestrictBackground(int i) {
        return i <= 5;
    }

    public NetworkPolicyManager(Context context, INetworkPolicyManager iNetworkPolicyManager) {
        if (iNetworkPolicyManager == null) {
            throw new IllegalArgumentException("missing INetworkPolicyManager");
        }
        this.mContext = context;
        this.mService = iNetworkPolicyManager;
    }

    public static NetworkPolicyManager from(Context context) {
        return (NetworkPolicyManager) context.getSystemService(Context.NETWORK_POLICY_SERVICE);
    }

    public void setUidPolicy(int i, int i2) {
        try {
            this.mService.setUidPolicy(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addUidPolicy(int i, int i2) {
        try {
            this.mService.addUidPolicy(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeUidPolicy(int i, int i2) {
        try {
            this.mService.removeUidPolicy(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUidPolicy(int i) {
        try {
            return this.mService.getUidPolicy(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int[] getUidsWithPolicy(int i) {
        try {
            return this.mService.getUidsWithPolicy(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerListener(INetworkPolicyListener iNetworkPolicyListener) {
        try {
            this.mService.registerListener(iNetworkPolicyListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterListener(INetworkPolicyListener iNetworkPolicyListener) {
        try {
            this.mService.unregisterListener(iNetworkPolicyListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerSubscriptionCallback(SubscriptionCallback subscriptionCallback) {
        Objects.requireNonNull(subscriptionCallback, "Callback cannot be null.");
        SubscriptionCallbackProxy subscriptionCallbackProxy = new SubscriptionCallbackProxy(subscriptionCallback);
        if (this.mCallbackMap.putIfAbsent(subscriptionCallback, subscriptionCallbackProxy) != null) {
            throw new IllegalArgumentException("Callback is already registered.");
        }
        registerListener(subscriptionCallbackProxy);
    }

    public void unregisterSubscriptionCallback(SubscriptionCallback subscriptionCallback) {
        Objects.requireNonNull(subscriptionCallback, "Callback cannot be null.");
        SubscriptionCallbackProxy subscriptionCallbackProxyRemove = this.mCallbackMap.remove(subscriptionCallback);
        if (subscriptionCallbackProxyRemove == null) {
            return;
        }
        unregisterListener(subscriptionCallbackProxyRemove);
    }

    public void setNetworkPolicies(NetworkPolicy[] networkPolicyArr) {
        try {
            this.mService.setNetworkPolicies(networkPolicyArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NetworkPolicy[] getNetworkPolicies() {
        try {
            return this.mService.getNetworkPolicies(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setRestrictBackground(boolean z) {
        try {
            this.mService.setRestrictBackground(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean getRestrictBackground() {
        try {
            return this.mService.getRestrictBackground();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSubscriptionOverride(int i, int i2, int i3, long j, String str) {
        try {
            this.mService.setSubscriptionOverride(i, i2, i3, j, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSubscriptionPlans(int i, SubscriptionPlan[] subscriptionPlanArr, String str) {
        try {
            this.mService.setSubscriptionPlans(i, subscriptionPlanArr, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public SubscriptionPlan[] getSubscriptionPlans(int i, String str) {
        try {
            return this.mService.getSubscriptionPlans(i, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void factoryReset(String str) {
        try {
            this.mService.factoryReset(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public static Iterator<Pair<ZonedDateTime, ZonedDateTime>> cycleIterator(NetworkPolicy networkPolicy) {
        final Iterator<Range<ZonedDateTime>> itCycleIterator = networkPolicy.cycleIterator();
        return new Iterator<Pair<ZonedDateTime, ZonedDateTime>>() { // from class: android.net.NetworkPolicyManager.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return itCycleIterator.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Pair<ZonedDateTime, ZonedDateTime> next() {
                if (hasNext()) {
                    Range range = (Range) itCycleIterator.next();
                    return Pair.create((ZonedDateTime) range.getLower(), (ZonedDateTime) range.getUpper());
                }
                return Pair.create(null, null);
            }
        };
    }

    @Deprecated
    public static boolean isUidValidForPolicy(Context context, int i) {
        return UserHandle.isApp(i);
    }

    public static String uidRulesToString(int i) {
        StringBuilder sbAppend = new StringBuilder().append(i).append(" (");
        if (i == 0) {
            sbAppend.append(KeyProperties.DIGEST_NONE);
        } else {
            sbAppend.append(DebugUtils.flagsToString(NetworkPolicyManager.class, "RULE_", i));
        }
        sbAppend.append(")");
        return sbAppend.toString();
    }

    public static String uidPoliciesToString(int i) {
        StringBuilder sbAppend = new StringBuilder().append(i).append(" (");
        if (i == 0) {
            sbAppend.append(KeyProperties.DIGEST_NONE);
        } else {
            sbAppend.append(DebugUtils.flagsToString(NetworkPolicyManager.class, "POLICY_", i));
        }
        sbAppend.append(")");
        return sbAppend.toString();
    }

    public static String resolveNetworkId(WifiConfiguration wifiConfiguration) {
        return WifiInfo.sanitizeSsid(wifiConfiguration.isPasspoint() ? wifiConfiguration.providerFriendlyName : wifiConfiguration.SSID);
    }

    public static String resolveNetworkId(String str) {
        return WifiInfo.sanitizeSsid(str);
    }

    public class SubscriptionCallbackProxy extends Listener {
        private final SubscriptionCallback mCallback;

        SubscriptionCallbackProxy(SubscriptionCallback subscriptionCallback) {
            this.mCallback = subscriptionCallback;
        }

        @Override // android.net.NetworkPolicyManager.Listener, android.net.INetworkPolicyListener
        public void onSubscriptionOverride(int i, int i2, int i3) {
            this.mCallback.onSubscriptionOverride(i, i2, i3);
        }

        @Override // android.net.NetworkPolicyManager.Listener, android.net.INetworkPolicyListener
        public void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) {
            this.mCallback.onSubscriptionPlansChanged(i, subscriptionPlanArr);
        }
    }
}
