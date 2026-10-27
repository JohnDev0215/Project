package com.ts.car.ncm.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ExtNetworkBroadCast {
    public static final String DEBUG_ACTION = "com.ts.car.ncm.debug";
    private static final String DNS_SERVERS = "dns_servers";
    private static final String GATEWAY = "gateway";
    private static final String IFACE = "iface";
    private static final String IPADDR = "ipaddr";
    private static final String NETCAP = "netcap";
    private static final String TAG = "ExtNetworkBroadCast";
    private BroadcastInterface mBroadCastIface;
    private Context mContext;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.ts.car.ncm.network.ExtNetworkBroadCast.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.d(ExtNetworkBroadCast.TAG, "action is null");
                return;
            }
            Log.i(ExtNetworkBroadCast.TAG, "UID:" + Binder.getCallingUid() + " PID:" + Binder.getCallingPid());
            Log.i(ExtNetworkBroadCast.TAG, "on Receiver:" + intent + " action:" + action);
            String iface = intent.getStringExtra(ExtNetworkBroadCast.IFACE);
            String gateway = intent.getStringExtra(ExtNetworkBroadCast.GATEWAY);
            ArrayList<String> dnsServers = intent.getStringArrayListExtra(ExtNetworkBroadCast.DNS_SERVERS);
            String ipAddr = intent.getStringExtra(ExtNetworkBroadCast.IPADDR);
            int[] netcaps = intent.getIntArrayExtra(ExtNetworkBroadCast.NETCAP);
            if (TextUtils.isEmpty(iface)) {
                Log.e(ExtNetworkBroadCast.TAG, "ExtNetworkBroadCast recve broadcast intent iface is null");
                return;
            }
            DebugConfig config = new DebugConfig();
            config.setIface(iface);
            config.setIpAddr(ipAddr);
            config.setNetcaps(netcaps);
            config.setGateway(gateway);
            config.setDnsServers(dnsServers);
            synchronized (ExtNetworkBroadCast.class) {
                Log.i(ExtNetworkBroadCast.TAG, "mBroadCastIface:" + ExtNetworkBroadCast.this.mBroadCastIface + " config:" + config);
                if (ExtNetworkBroadCast.this.mBroadCastIface != null) {
                    ExtNetworkBroadCast.this.mBroadCastIface.onNetworkConfigChanged(config);
                }
            }
        }
    };

    public static class DebugConfig {
        private ArrayList<String> dnsServers;
        private String gateway;
        private String iface;
        private String ipAddr;
        private int[] netcaps;

        public String getIface() {
            return this.iface;
        }

        public void setIface(String iface) {
            this.iface = iface;
        }

        public String getGateway() {
            return this.gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public String getIpAddr() {
            return this.ipAddr;
        }

        public void setIpAddr(String ipAddr) {
            this.ipAddr = ipAddr;
        }

        public ArrayList<String> getDnsServers() {
            return this.dnsServers;
        }

        public void setDnsServers(ArrayList<String> dnsServers) {
            this.dnsServers = dnsServers;
        }

        public int[] getNetcaps() {
            return this.netcaps;
        }

        public void setNetcaps(int[] netcaps) {
            this.netcaps = netcaps;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DebugConfig{");
            sb.append("iface=");
            sb.append(this.iface);
            sb.append(", gateway=");
            sb.append(IpMask.maskIpMiddleString(this.gateway));
            sb.append(", ipAddr=");
            sb.append(IpMask.maskIpMiddleString(this.ipAddr));
            sb.append(", dnsServers=");
            ArrayList<String> arrayList = this.dnsServers;
            sb.append(Arrays.toString(arrayList == null ? null : arrayList.toArray()));
            sb.append(", netcaps=");
            sb.append(Arrays.toString(this.netcaps));
            sb.append('}');
            return sb.toString();
        }
    }

    public interface BroadcastInterface {
        default void onNetworkConfigChanged(DebugConfig config) {
            Log.i(ExtNetworkBroadCast.TAG, "onNetworkConfigChanged config:" + config);
        }
    }

    public synchronized void registerBroadcastIface(BroadcastInterface bcInterface) {
        this.mBroadCastIface = bcInterface;
    }

    public synchronized void revmoeBroadcastIface() {
        this.mBroadCastIface = null;
    }

    public ExtNetworkBroadCast(Context context) {
        this.mContext = context;
        if (Build.IS_USERDEBUG) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DEBUG_ACTION);
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
            Log.i(TAG, "register action:com.ts.car.ncm.debug");
            return;
        }
        Log.i(TAG, "current system is:" + Build.TYPE);
    }
}
