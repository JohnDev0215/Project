package com.ts.car.ncm.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetd;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkAgent;
import android.net.NetworkCapabilities;
import android.net.NetworkFactory;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ExtNetworkFactory extends NetworkFactory {
    public static final boolean DBG = true;
    private static final String TAG = ExtNetworkFactory.class.getSimpleName();
    private LinkStateListener listener;
    private Context mContext;
    private Handler mHandler;
    private LinkProperties mLinkProperties;
    private boolean mLinkUp;
    private IfaceMonitor mMonitor;
    private NetworkAgent mNetworkAgent;
    private ConnectivityManager.NetworkCallback mNetworkCallbacks;
    private NetworkCapabilities mNetworkCapabilities;
    private NetworkInfo mNetworkInfo;
    private int mNetworkType;
    private String mNetworkTypeName;
    private int mScore;
    private boolean mStarted;

    public interface LinkStateListener {
        void onLinkStateChanged(String str, boolean z);

        void onMoibleNetworkStateChanged(String str, boolean z);
    }

    public ExtNetworkFactory(Handler handler, Context context, NetworkCapabilities netcap, String typeName, int type) {
        super(handler.getLooper(), context, typeName, netcap);
        this.mLinkUp = false;
        this.mNetworkCallbacks = new ConnectivityManager.NetworkCallback() { // from class: com.ts.car.ncm.network.ExtNetworkFactory.1
            boolean isAvailable = false;

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                Log.d(ExtNetworkFactory.TAG, this + "onAvailable:" + network);
                if (ExtNetworkFactory.this.mMonitor != null && !this.isAvailable) {
                    this.isAvailable = true;
                    ExtNetworkFactory.this.mMonitor.networkAgentConnected();
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                Log.d(ExtNetworkFactory.TAG, this + "onLost:" + network);
                if (ExtNetworkFactory.this.mMonitor != null && this.isAvailable) {
                    this.isAvailable = false;
                    ExtNetworkFactory.this.mMonitor.networkAgentDisconnected();
                }
            }
        };
        this.mContext = context;
        this.mHandler = handler;
        this.mNetworkCapabilities = netcap;
        this.mNetworkTypeName = typeName;
        this.mNetworkType = type;
        this.mNetworkInfo = new NetworkInfo(this.mNetworkType, 0, this.mNetworkTypeName, INetd.NEXTHOP_NONE);
        this.mLinkProperties = new LinkProperties();
        this.mMonitor = null;
        ConnectivityManager cm = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkRequest request = new NetworkRequest.Builder().setCapabilities(this.mNetworkCapabilities).build();
        cm.registerNetworkCallback(request, this.mNetworkCallbacks);
        Log.i(TAG, "registerNetworkCallback:" + this.mNetworkCallbacks);
    }

    public void setIfaceMonitor(IfaceMonitor monitor) {
        this.mMonitor = monitor;
    }

    public void registerNetworkLinkListener(LinkStateListener listener) {
        this.listener = listener;
    }

    public void removeNetworkLinkListener() {
        this.listener = null;
    }

    public LinkProperties getLinkProperties() {
        return this.mLinkProperties;
    }

    public synchronized void updateNetworkInfo(LinkProperties linkProperties, NetworkCapabilities ncs) {
        boolean changed = false;
        Log.i(TAG, "updateNetworkInfo for linkProperties:" + IpMask.getLinkPropertiesString(linkProperties) + " ncs:" + ncs);
        if (ncs != null && !this.mNetworkCapabilities.equals(ncs)) {
            this.mNetworkCapabilities = ncs;
            changed = true;
        }
        if (linkProperties != null && !this.mLinkProperties.equals(linkProperties)) {
            this.mLinkProperties = linkProperties;
            changed = true;
        }
        if (changed) {
            updateNetworkAgent();
        }
    }

    private synchronized void updateNetworkAgent() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Update mNetworkAgent with: mNetworkCapabilities:");
        sb.append(this.mNetworkCapabilities);
        sb.append(", mNetworkInfo:");
        sb.append(this.mNetworkInfo);
        sb.append(", mLinkProperties:");
        sb.append(IpMask.getLinkPropertiesString(this.mLinkProperties));
        sb.append(", mLinkUp:");
        sb.append(this.mLinkUp);
        sb.append(", mScore:");
        sb.append(this.mLinkUp ? this.mScore : 0);
        Log.i(str, sb.toString());
        this.mNetworkAgent.sendNetworkCapabilities(this.mNetworkCapabilities);
        this.mNetworkAgent.sendNetworkInfo(this.mNetworkInfo);
        this.mNetworkAgent.sendLinkProperties(this.mLinkProperties);
        this.mNetworkAgent.sendNetworkScore(this.mLinkUp ? this.mScore : 0);
    }

    @Override // android.net.NetworkFactory
    public void startNetwork() {
        Log.i(TAG, "startNetwork mStarted:" + this.mStarted);
        if (!this.mStarted) {
            mayStartIpManager();
            this.mStarted = true;
        }
    }

    @Override // android.net.NetworkFactory
    public void stopNetwork() {
        Log.i(TAG, "stopNetwork mStarted:" + this.mStarted);
        if (this.mStarted) {
            stopIpManager();
            this.mStarted = false;
        }
    }

    private synchronized void mayStartIpManager() {
        if (!this.mStarted && this.mLinkUp) {
            Log.d(TAG, "start IpManager");
            startIpManager();
        }
    }

    public synchronized boolean isIfaceUp() {
        return this.mLinkUp;
    }

    public void destory() {
        ConnectivityManager cm = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        cm.unregisterNetworkCallback(this.mNetworkCallbacks);
        Log.i(TAG, "unregisterNetworkCallback:" + this.mNetworkCallbacks);
        terminate();
    }

    private synchronized void startIpManager() {
        Log.d(TAG, "startIpManager:" + this);
        if (this.mNetworkAgent != null) {
            Log.e(TAG, "the NetworkAgent " + this.mNetworkAgent + " have been created in " + this);
            return;
        }
        this.mLinkUp = true;
        this.mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.CONNECTING, null, null);
        this.mNetworkInfo.setIsAvailable(false);
        this.mNetworkAgent = new NetworkAgent(this.mHandler.getLooper(), this.mContext, this.mNetworkTypeName, this.mNetworkInfo, this.mNetworkCapabilities, this.mLinkProperties, this.mScore) { // from class: com.ts.car.ncm.network.ExtNetworkFactory.2
            protected void unwanted() {
                synchronized (ExtNetworkFactory.this) {
                    if (this == ExtNetworkFactory.this.mNetworkAgent) {
                        Log.d(ExtNetworkFactory.TAG, "network agent is unwanted");
                        ExtNetworkFactory.this.stopIpManager();
                    } else if (ExtNetworkFactory.this.mNetworkAgent != null) {
                        Log.i(ExtNetworkFactory.TAG, "ignore this request");
                    }
                }
            }
        };
        this.mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.CONNECTED, null, null);
        this.mNetworkInfo.setIsAvailable(true);
        this.mNetworkAgent.sendNetworkInfo(this.mNetworkInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void stopIpManager() {
        Log.d(TAG, "stopIpManager");
        this.mLinkUp = false;
        this.mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.DISCONNECTED, null, null);
        if (this.mNetworkAgent != null) {
            updateNetworkAgent();
            this.mNetworkAgent = null;
        }
        this.mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.IDLE, null, null);
        this.mNetworkInfo.setIsAvailable(false);
    }

    public void updateLinkProperties(LinkProperties linkProperties) {
        if (this.mLinkProperties != linkProperties) {
            this.mLinkProperties = linkProperties;
        }
    }

    public void updateScore(int score) {
        if (this.mScore != score) {
            this.mScore = score;
            setScoreFilter(score);
        }
    }

    public void updateNetworkCapability(NetworkCapabilities ncs) {
        if (this.mNetworkCapabilities != ncs) {
            this.mNetworkCapabilities = ncs;
            setCapabilityFilter(ncs);
        }
    }

    public boolean updateInterfaceState(boolean linkUp) {
        Log.d(TAG, "updateInterfaceState function:" + linkUp);
        synchronized (this) {
            if (this.mLinkUp == linkUp) {
                Log.e(TAG, "mLinkUp:" + this.mLinkUp + " linkUp:" + linkUp + " don't need to update");
                return false;
            }
            if (linkUp) {
                startIpManager();
            } else {
                stopIpManager();
            }
            if (this.listener != null) {
                if ("Tbox".equals(this.mNetworkTypeName)) {
                    this.listener.onMoibleNetworkStateChanged(this.mLinkProperties.getInterfaceName(), linkUp);
                }
                this.listener.onLinkStateChanged(this.mLinkProperties.getInterfaceName(), linkUp);
            }
            return true;
        }
    }
}
