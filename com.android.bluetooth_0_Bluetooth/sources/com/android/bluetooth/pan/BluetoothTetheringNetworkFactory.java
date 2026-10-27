package com.android.bluetooth.pan;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.INetd;
import android.net.LinkProperties;
import android.net.NetworkAgent;
import android.net.NetworkAgentConfig;
import android.net.NetworkCapabilities;
import android.net.NetworkFactory;
import android.net.ip.IIpClient;
import android.net.ip.IpClientUtil;
import android.net.shared.ProvisioningConfiguration;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothTetheringNetworkFactory extends NetworkFactory {
    private static final String BLUETOOTH_SHARED_DATA_DISABLE = "persist.wifi.BLUETOOTH_SHARED_DATA_DISABLE";
    private static final int NETWORK_SCORE = 69;
    private static final String NETWORK_TYPE = "Bluetooth Tethering";
    private static final String TAG = "BluetoothTetheringNetworkFactory";
    private final Context mContext;
    private String mInterfaceName;
    private IIpClient mIpClient;
    private int mIpClientStartIndex;
    private NetworkAgent mNetworkAgent;
    private final NetworkCapabilities mNetworkCapabilities;
    private final PanService mPanService;

    public BluetoothTetheringNetworkFactory(Context context, Looper looper, PanService panService) {
        super(looper, context, NETWORK_TYPE, new NetworkCapabilities());
        this.mIpClientStartIndex = 0;
        this.mContext = context;
        this.mPanService = panService;
        this.mNetworkCapabilities = new NetworkCapabilities();
        initNetworkCapabilities();
        setCapabilityFilter(this.mNetworkCapabilities);
    }

    private class BtIpClientCallback extends IpClientUtil.WaitForProvisioningCallbacks {
        private final int mCurrentStartIndex;

        private BtIpClientCallback(int currentStartIndex) {
            this.mCurrentStartIndex = currentStartIndex;
        }

        @Override // android.net.ip.IpClientCallbacks
        public void onIpClientCreated(IIpClient ipClient) {
            synchronized (BluetoothTetheringNetworkFactory.this) {
                if (this.mCurrentStartIndex != BluetoothTetheringNetworkFactory.this.mIpClientStartIndex) {
                    return;
                }
                BluetoothTetheringNetworkFactory.this.mIpClient = ipClient;
                try {
                    BluetoothTetheringNetworkFactory.this.mIpClient.startProvisioning(new ProvisioningConfiguration.Builder().withoutMultinetworkPolicyTracker().withoutIpReachabilityMonitor().build().toStableParcelable());
                } catch (RemoteException e) {
                    Log.e(BluetoothTetheringNetworkFactory.TAG, "Error starting IpClient provisioning", e);
                }
            }
        }

        @Override // android.net.ip.IpClientCallbacks
        public void onLinkPropertiesChange(LinkProperties newLp) {
            synchronized (BluetoothTetheringNetworkFactory.this) {
                if (BluetoothTetheringNetworkFactory.this.mNetworkAgent != null) {
                    BluetoothTetheringNetworkFactory.this.mNetworkAgent.sendLinkProperties(newLp);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopIpClientLocked() {
        this.mIpClientStartIndex++;
        IIpClient iIpClient = this.mIpClient;
        if (iIpClient != null) {
            try {
                iIpClient.shutdown();
            } catch (RemoteException e) {
                Log.e(TAG, "Error shutting down IpClient", e);
            }
            this.mIpClient = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BtIpClientCallback startIpClientLocked() {
        int i = this.mIpClientStartIndex + 1;
        this.mIpClientStartIndex = i;
        BtIpClientCallback callback = new BtIpClientCallback(i);
        IpClientUtil.makeIpClient(this.mContext, this.mInterfaceName, callback);
        return callback;
    }

    @Override // android.net.NetworkFactory
    protected void startNetwork() {
        Thread ipProvisioningThread = new Thread(new Runnable() { // from class: com.android.bluetooth.pan.BluetoothTetheringNetworkFactory.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                synchronized (BluetoothTetheringNetworkFactory.this) {
                    if (TextUtils.isEmpty(BluetoothTetheringNetworkFactory.this.mInterfaceName)) {
                        Log.e(BluetoothTetheringNetworkFactory.TAG, "attempted to reverse tether without interface name");
                        return;
                    }
                    BluetoothTetheringNetworkFactory.this.log("ipProvisioningThread(+" + BluetoothTetheringNetworkFactory.this.mInterfaceName + ") start IP provisioning");
                    IpClientUtil.WaitForProvisioningCallbacks ipcCallback = BluetoothTetheringNetworkFactory.this.startIpClientLocked();
                    int ipClientStartIndex = BluetoothTetheringNetworkFactory.this.mIpClientStartIndex;
                    LinkProperties linkProperties = ipcCallback.waitForProvisioning();
                    if (linkProperties == null) {
                        Log.e(BluetoothTetheringNetworkFactory.TAG, "IP provisioning error.");
                        synchronized (BluetoothTetheringNetworkFactory.this) {
                            BluetoothTetheringNetworkFactory.this.stopIpClientLocked();
                            BluetoothTetheringNetworkFactory.this.setScoreFilter(-1);
                        }
                        return;
                    }
                    if (SystemProperties.getBoolean(BluetoothTetheringNetworkFactory.BLUETOOTH_SHARED_DATA_DISABLE, true)) {
                        return;
                    }
                    NetworkAgentConfig config = new NetworkAgentConfig.Builder().setLegacyType(7).setLegacyTypeName(BluetoothTetheringNetworkFactory.NETWORK_TYPE).build();
                    synchronized (BluetoothTetheringNetworkFactory.this) {
                        try {
                            try {
                                if (ipClientStartIndex != BluetoothTetheringNetworkFactory.this.mIpClientStartIndex) {
                                    return;
                                }
                                BluetoothTetheringNetworkFactory.this.mNetworkAgent = new NetworkAgent(BluetoothTetheringNetworkFactory.this.mContext, BluetoothTetheringNetworkFactory.this.getLooper(), BluetoothTetheringNetworkFactory.NETWORK_TYPE, BluetoothTetheringNetworkFactory.this.mNetworkCapabilities, linkProperties, 69, config, BluetoothTetheringNetworkFactory.this.getProvider()) { // from class: com.android.bluetooth.pan.BluetoothTetheringNetworkFactory.1.1
                                    public void unwanted() {
                                        BluetoothTetheringNetworkFactory.this.onCancelRequest();
                                    }
                                };
                                BluetoothTetheringNetworkFactory.this.mNetworkAgent.register();
                                BluetoothTetheringNetworkFactory.this.mNetworkAgent.markConnected();
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                }
            }
        });
        ipProvisioningThread.start();
    }

    @Override // android.net.NetworkFactory
    protected void stopNetwork() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onCancelRequest() {
        stopIpClientLocked();
        this.mInterfaceName = INetd.NEXTHOP_NONE;
        if (this.mNetworkAgent != null) {
            this.mNetworkAgent.unregister();
            this.mNetworkAgent = null;
        }
        for (BluetoothDevice device : this.mPanService.getConnectedDevices()) {
            this.mPanService.disconnect(device);
        }
    }

    public void startReverseTether(String iface) {
        if (iface == null || TextUtils.isEmpty(iface)) {
            Log.e(TAG, "attempted to reverse tether with empty interface");
            return;
        }
        synchronized (this) {
            if (!TextUtils.isEmpty(this.mInterfaceName)) {
                Log.e(TAG, "attempted to reverse tether while already in process");
                return;
            }
            this.mInterfaceName = iface;
            register();
            setScoreFilter(69);
        }
    }

    public synchronized void stopReverseTether() {
        if (TextUtils.isEmpty(this.mInterfaceName)) {
            Log.e(TAG, "attempted to stop reverse tether with nothing tethered");
            return;
        }
        onCancelRequest();
        setScoreFilter(-1);
        terminate();
    }

    private void initNetworkCapabilities() {
        this.mNetworkCapabilities.addTransportType(2);
        this.mNetworkCapabilities.addCapability(12);
        this.mNetworkCapabilities.addCapability(13);
        this.mNetworkCapabilities.addCapability(18);
        this.mNetworkCapabilities.addCapability(21);
        this.mNetworkCapabilities.setLinkUpstreamBandwidthKbps(24000);
        this.mNetworkCapabilities.setLinkDownstreamBandwidthKbps(24000);
    }
}
