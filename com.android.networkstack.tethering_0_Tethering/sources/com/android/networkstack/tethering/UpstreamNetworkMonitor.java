package com.android.networkstack.tethering;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.IpPrefix;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.util.PrefixUtils;
import android.net.util.SharedLog;
import android.os.Handler;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.networkstack.tethering.util.StateMachine;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class UpstreamNetworkMonitor {
    private static final String TAG = "UpstreamNetworkMonitor";
    public static final int TYPE_NONE = -1;
    private static final SparseIntArray sLegacyTypeToTransport;
    private ConnectivityManager mCM;
    private final Context mContext;
    private Network mDefaultInternetNetwork;
    private ConnectivityManager.NetworkCallback mDefaultNetworkCallback;
    private boolean mDunRequired;
    private EntitlementManager mEntitlementMgr;
    private final Handler mHandler;
    private boolean mIsDefaultCellularUpstream;
    private ConnectivityManager.NetworkCallback mListenAllCallback;
    private HashSet<IpPrefix> mLocalPrefixes;
    private final SharedLog mLog;
    private ConnectivityManager.NetworkCallback mMobileNetworkCallback;
    private final HashMap<Network, UpstreamNetworkState> mNetworkMap;
    private final StateMachine mTarget;
    private final int mWhat;

    public void setCurrentUpstream(Network network) {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sLegacyTypeToTransport = sparseIntArray;
        sparseIntArray.put(0, 0);
        sLegacyTypeToTransport.put(4, 0);
        sLegacyTypeToTransport.put(5, 0);
        sLegacyTypeToTransport.put(1, 1);
        sLegacyTypeToTransport.put(7, 2);
        sLegacyTypeToTransport.put(9, 3);
    }

    public UpstreamNetworkMonitor(Context context, StateMachine stateMachine, SharedLog sharedLog, int i) {
        this.mNetworkMap = new HashMap<>();
        this.mContext = context;
        this.mTarget = stateMachine;
        this.mHandler = stateMachine.getHandler();
        this.mLog = sharedLog.forSubComponent(TAG);
        this.mWhat = i;
        this.mLocalPrefixes = new HashSet<>();
        this.mIsDefaultCellularUpstream = false;
    }

    public UpstreamNetworkMonitor(ConnectivityManager connectivityManager, StateMachine stateMachine, SharedLog sharedLog, int i) {
        this((Context) null, stateMachine, sharedLog, i);
        this.mCM = connectivityManager;
    }

    public void startTrackDefaultNetwork(NetworkRequest networkRequest, EntitlementManager entitlementManager) {
        if (this.mDefaultNetworkCallback == null) {
            this.mDefaultNetworkCallback = new UpstreamNetworkCallback(2);
            cm().requestNetwork(networkRequest, this.mDefaultNetworkCallback, this.mHandler);
        }
        if (this.mEntitlementMgr == null) {
            this.mEntitlementMgr = entitlementManager;
        }
    }

    public void startObserveAllNetworks() {
        stop();
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().clearCapabilities().build();
        this.mListenAllCallback = new UpstreamNetworkCallback(1);
        cm().registerNetworkCallback(networkRequestBuild, this.mListenAllCallback, this.mHandler);
    }

    public void stop() {
        releaseMobileNetworkRequest();
        releaseCallback(this.mListenAllCallback);
        this.mListenAllCallback = null;
        this.mNetworkMap.clear();
    }

    public void updateMobileRequiresDun(boolean z) {
        boolean z2 = this.mDunRequired != z;
        this.mDunRequired = z;
        if (z2 && mobileNetworkRequested()) {
            releaseMobileNetworkRequest();
            registerMobileNetworkRequest();
        }
    }

    public boolean mobileNetworkRequested() {
        return this.mMobileNetworkCallback != null;
    }

    public void registerMobileNetworkRequest() {
        NetworkRequest networkRequestBuild;
        if (!isCellularUpstreamPermitted()) {
            this.mLog.i("registerMobileNetworkRequest() is not permitted");
            releaseMobileNetworkRequest();
            return;
        }
        if (this.mMobileNetworkCallback != null) {
            this.mLog.e("registerMobileNetworkRequest() already registered");
            return;
        }
        if (this.mDunRequired) {
            networkRequestBuild = new NetworkRequest.Builder().addCapability(2).removeCapability(13).addTransportType(0).build();
        } else {
            networkRequestBuild = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
        }
        NetworkRequest networkRequest = networkRequestBuild;
        this.mMobileNetworkCallback = new UpstreamNetworkCallback(3);
        int i = this.mDunRequired ? 4 : 5;
        this.mLog.i("requesting mobile upstream network: " + networkRequest);
        cm().requestNetwork(networkRequest, 0, i, this.mHandler, this.mMobileNetworkCallback);
    }

    public void releaseMobileNetworkRequest() {
        if (this.mMobileNetworkCallback == null) {
            return;
        }
        cm().unregisterNetworkCallback(this.mMobileNetworkCallback);
        this.mMobileNetworkCallback = null;
    }

    public UpstreamNetworkState selectPreferredUpstreamType(Iterable<Integer> iterable) {
        TypeStatePair typeStatePairFindFirstAvailableUpstreamByType = findFirstAvailableUpstreamByType(this.mNetworkMap.values(), iterable, isCellularUpstreamPermitted());
        this.mLog.log("preferred upstream type: " + typeStatePairFindFirstAvailableUpstreamByType.type);
        int i = typeStatePairFindFirstAvailableUpstreamByType.type;
        if (i != -1) {
            if (i == 4 || i == 5) {
                if (!this.mIsDefaultCellularUpstream) {
                    this.mEntitlementMgr.maybeRunProvisioning();
                }
                registerMobileNetworkRequest();
            } else {
                releaseMobileNetworkRequest();
            }
        } else if (!isCellularUpstreamPermitted()) {
            releaseMobileNetworkRequest();
        }
        return typeStatePairFindFirstAvailableUpstreamByType.ns;
    }

    public UpstreamNetworkState getCurrentPreferredUpstream() {
        Network network = this.mDefaultInternetNetwork;
        UpstreamNetworkState upstreamNetworkState = network != null ? this.mNetworkMap.get(network) : null;
        if (isNetworkUsableAndNotCellular(upstreamNetworkState)) {
            return upstreamNetworkState;
        }
        if (isCellularUpstreamPermitted()) {
            return !this.mDunRequired ? upstreamNetworkState : findFirstDunNetwork(this.mNetworkMap.values());
        }
        return null;
    }

    public Set<IpPrefix> getLocalPrefixes() {
        return (Set) this.mLocalPrefixes.clone();
    }

    private boolean isCellularUpstreamPermitted() {
        EntitlementManager entitlementManager = this.mEntitlementMgr;
        if (entitlementManager != null) {
            return entitlementManager.isCellularUpstreamPermitted();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAvailable(Network network) {
        if (this.mNetworkMap.containsKey(network)) {
            return;
        }
        this.mNetworkMap.put(network, new UpstreamNetworkState(null, null, network));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNetCap(Network network, NetworkCapabilities networkCapabilities) {
        UpstreamNetworkState upstreamNetworkState = this.mNetworkMap.get(network);
        if (upstreamNetworkState == null || networkCapabilities.equals(upstreamNetworkState.networkCapabilities)) {
            return;
        }
        this.mNetworkMap.put(network, new UpstreamNetworkState(upstreamNetworkState.linkProperties, networkCapabilities, network));
        notifyTarget(1, network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLinkProp(Network network, LinkProperties linkProperties) {
        UpstreamNetworkState upstreamNetworkState = this.mNetworkMap.get(network);
        if (upstreamNetworkState == null || linkProperties.equals(upstreamNetworkState.linkProperties)) {
            return;
        }
        this.mNetworkMap.put(network, new UpstreamNetworkState(linkProperties, upstreamNetworkState.networkCapabilities, network));
        notifyTarget(2, network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLost(Network network) {
        if (this.mNetworkMap.containsKey(network)) {
            notifyTarget(3, this.mNetworkMap.remove(network));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recomputeLocalPrefixes() {
        HashSet<IpPrefix> hashSetAllLocalPrefixes = allLocalPrefixes(this.mNetworkMap.values());
        if (this.mLocalPrefixes.equals(hashSetAllLocalPrefixes)) {
            return;
        }
        this.mLocalPrefixes = hashSetAllLocalPrefixes;
        notifyTarget(10, hashSetAllLocalPrefixes.clone());
    }

    private ConnectivityManager cm() {
        if (this.mCM == null) {
            this.mCM = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        }
        return this.mCM;
    }

    private class UpstreamNetworkCallback extends ConnectivityManager.NetworkCallback {
        private final int mCallbackType;

        UpstreamNetworkCallback(int i) {
            this.mCallbackType = i;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            UpstreamNetworkMonitor.this.handleAvailable(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (this.mCallbackType == 2) {
                UpstreamNetworkMonitor.this.mDefaultInternetNetwork = network;
                boolean zIsCellular = UpstreamNetworkMonitor.isCellular(networkCapabilities);
                if (UpstreamNetworkMonitor.this.mIsDefaultCellularUpstream != zIsCellular) {
                    UpstreamNetworkMonitor.this.mIsDefaultCellularUpstream = zIsCellular;
                    UpstreamNetworkMonitor.this.mEntitlementMgr.notifyUpstream(zIsCellular);
                    return;
                }
                return;
            }
            UpstreamNetworkMonitor.this.handleNetCap(network, networkCapabilities);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            if (this.mCallbackType == 2) {
                return;
            }
            UpstreamNetworkMonitor.this.handleLinkProp(network, linkProperties);
            if (this.mCallbackType == 1) {
                UpstreamNetworkMonitor.this.recomputeLocalPrefixes();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            if (this.mCallbackType == 2) {
                UpstreamNetworkMonitor.this.mDefaultInternetNetwork = null;
                UpstreamNetworkMonitor.this.mIsDefaultCellularUpstream = false;
                UpstreamNetworkMonitor.this.mEntitlementMgr.notifyUpstream(false);
            } else {
                UpstreamNetworkMonitor.this.handleLost(network);
                if (this.mCallbackType == 1) {
                    UpstreamNetworkMonitor.this.recomputeLocalPrefixes();
                }
            }
        }
    }

    private void releaseCallback(ConnectivityManager.NetworkCallback networkCallback) {
        if (networkCallback != null) {
            cm().unregisterNetworkCallback(networkCallback);
        }
    }

    private void notifyTarget(int i, Network network) {
        notifyTarget(i, this.mNetworkMap.get(network));
    }

    private void notifyTarget(int i, Object obj) {
        this.mTarget.sendMessage(this.mWhat, i, 0, obj);
    }

    private static class TypeStatePair {
        public UpstreamNetworkState ns;
        public int type;

        private TypeStatePair() {
            this.type = -1;
            this.ns = null;
        }
    }

    private static TypeStatePair findFirstAvailableUpstreamByType(Iterable<UpstreamNetworkState> iterable, Iterable<Integer> iterable2, boolean z) {
        NetworkCapabilities networkCapabilitiesNetworkCapabilitiesForType;
        TypeStatePair typeStatePair = new TypeStatePair();
        Iterator<Integer> it = iterable2.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            try {
                networkCapabilitiesNetworkCapabilitiesForType = networkCapabilitiesForType(iIntValue);
            } catch (IllegalArgumentException unused) {
                Log.e(TAG, "No NetworkCapabilities mapping for legacy type: " + iIntValue);
            }
            if (z || !isCellular(networkCapabilitiesNetworkCapabilitiesForType)) {
                for (UpstreamNetworkState upstreamNetworkState : iterable) {
                    if (networkCapabilitiesNetworkCapabilitiesForType.satisfiedByNetworkCapabilities(upstreamNetworkState.networkCapabilities)) {
                        typeStatePair.type = iIntValue;
                        typeStatePair.ns = upstreamNetworkState;
                        return typeStatePair;
                    }
                }
            }
        }
        return typeStatePair;
    }

    private static HashSet<IpPrefix> allLocalPrefixes(Iterable<UpstreamNetworkState> iterable) {
        HashSet<IpPrefix> hashSet = new HashSet<>();
        Iterator<UpstreamNetworkState> it = iterable.iterator();
        while (it.hasNext()) {
            LinkProperties linkProperties = it.next().linkProperties;
            if (linkProperties != null) {
                hashSet.addAll(PrefixUtils.localPrefixesFrom(linkProperties));
            }
        }
        return hashSet;
    }

    private static boolean isCellular(UpstreamNetworkState upstreamNetworkState) {
        return upstreamNetworkState != null && isCellular(upstreamNetworkState.networkCapabilities);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isCellular(NetworkCapabilities networkCapabilities) {
        return networkCapabilities != null && networkCapabilities.hasTransport(0) && networkCapabilities.hasCapability(15);
    }

    private static boolean hasCapability(UpstreamNetworkState upstreamNetworkState, int i) {
        NetworkCapabilities networkCapabilities;
        return (upstreamNetworkState == null || (networkCapabilities = upstreamNetworkState.networkCapabilities) == null || !networkCapabilities.hasCapability(i)) ? false : true;
    }

    private static boolean isNetworkUsableAndNotCellular(UpstreamNetworkState upstreamNetworkState) {
        NetworkCapabilities networkCapabilities;
        return (upstreamNetworkState == null || (networkCapabilities = upstreamNetworkState.networkCapabilities) == null || upstreamNetworkState.linkProperties == null || isCellular(networkCapabilities)) ? false : true;
    }

    private static UpstreamNetworkState findFirstDunNetwork(Iterable<UpstreamNetworkState> iterable) {
        for (UpstreamNetworkState upstreamNetworkState : iterable) {
            if (isCellular(upstreamNetworkState) && hasCapability(upstreamNetworkState, 2)) {
                return upstreamNetworkState;
            }
        }
        return null;
    }

    public static NetworkCapabilities networkCapabilitiesForType(int i) {
        NetworkCapabilities.Builder builder = new NetworkCapabilities.Builder();
        int i2 = sLegacyTypeToTransport.get(i, -1);
        if (i2 == -1) {
            throw new IllegalArgumentException("unknown legacy type: " + i);
        }
        builder.addTransportType(i2);
        if (i == 4) {
            builder.addCapability(2);
            builder.removeCapability(13);
        } else {
            builder.addCapability(12);
        }
        return builder.build();
    }
}
