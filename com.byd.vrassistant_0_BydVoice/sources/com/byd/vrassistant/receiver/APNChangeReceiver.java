package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class APNChangeReceiver extends BroadcastReceiver {
    private static final String APN_IFNAME_PROPKEY = "net.lte.apn3.ifname";
    private static final String TAG = "NEU_APN";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.i(TAG, "NetworkChangeReceiver onReceiver action=" + intent.getAction());
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        RLog.i(TAG, "networkInfo = " + networkInfo.getExtraInfo());
        if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
            RLog.i(TAG, "networkInfo.State =>CONNECTED");
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.equals(networkInfo)) {
                    if (activeNetworkInfo == null) {
                        RLog.i(TAG, "ActiveNetworkInfo is null");
                        return;
                    } else {
                        RLog.i(TAG, "ActiveNetworkInfo is not APN3");
                        return;
                    }
                }
                RLog.i(TAG, "ActiveNetworkInfo is APN3");
                LinkProperties linkProperties = connectivityManager.getLinkProperties(activeNetwork);
                if (Build.VERSION.SDK_INT >= 29) {
                    linkProperties.setDnsServers(getDns());
                    return;
                }
                return;
            }
            RLog.i(TAG, "getActiveNetwork is null");
        }
    }

    private static Collection<InetAddress> getDns() {
        LinkedList linkedList = new LinkedList();
        String str = SystemPropertiesUtils.get(APN_IFNAME_PROPKEY);
        if (!TextUtils.isEmpty(str)) {
            RLog.i(TAG, "ifname=" + str);
            String str2 = "net." + str + ".dns1";
            String str3 = "net." + str + ".dns2";
            try {
                InetAddress byName = InetAddress.getByName(str2);
                String hostAddress = byName.getHostAddress();
                linkedList.add(byName);
                RLog.i(TAG, "serverDNS1 = " + hostAddress);
                InetAddress byName2 = InetAddress.getByName(str3);
                String hostAddress2 = byName2.getHostAddress();
                linkedList.add(byName2);
                RLog.i(TAG, "serverDNS2 = " + hostAddress2);
            } catch (UnknownHostException e) {
                RLog.e(TAG, "getDns error", e);
            }
        } else {
            RLog.i(TAG, "ifname is empty");
        }
        return linkedList;
    }
}
