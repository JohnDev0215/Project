package com.ts.androidauto.aap.wifi;

import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import com.google.android.projection.proto.Protos;
import com.ts.androidauto.aap.common.WifiConnectInfo;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes2.dex */
public class WifiAccessPoint {
    private static final int BSSID_FORMAT_0 = 0;
    private static final int BSSID_FORMAT_1 = 1;
    private static final int BSSID_FORMAT_2 = 2;
    private static final int BSSID_FORMAT_3 = 3;
    private static final int BSSID_FORMAT_4 = 4;
    private static final int BSSID_FORMAT_5 = 5;
    private static final String FAILED_TO_FIND_HOST_IP = "Failed to find host IP";
    private static final int GET_RETRY_COUNT = 3;
    private static final int GET_RETRY_SLEEP_50 = 100;
    private static final String TAG = "AAPWifiAccessPoint";
    private Context mContext;
    private volatile String mHostBssid;
    private volatile String mHostIp;
    private WifiManager mWifiManager;

    public WifiAccessPoint(Context context) {
        this.mContext = null;
        this.mWifiManager = null;
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    private String formatHostBssid(byte[] bssid) {
        if (bssid == null || bssid.length <= 0) {
            return null;
        }
        String hostBssid = String.format("%02x:%02x:%02x:%02x:%02x:%02x", Byte.valueOf(bssid[0]), Byte.valueOf(bssid[1]), Byte.valueOf(bssid[2]), Byte.valueOf(bssid[3]), Byte.valueOf(bssid[4]), Byte.valueOf(bssid[5]));
        return hostBssid;
    }

    private String getWifiStaAddress() {
        WifiInfo info;
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null || (info = wifiManager.getConnectionInfo()) == null) {
            return "00:00:00:00:00:00";
        }
        String addr = info.getMacAddress();
        return addr;
    }

    private NetworkInterface getHostApInterface() {
        NetworkInterface hostInterface = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                LogUtil.info(TAG, "interfaces is " + iface.toString());
                String interfaceName = iface.getDisplayName();
                if (interfaceName == null) {
                    LogUtil.info(TAG, "interfaceName is null, skip.");
                } else if (interfaceName.indexOf("wlan") == -1 && interfaceName.indexOf("softap") == -1) {
                    LogUtil.info(TAG, "interfaceName not wifi ap, skip.");
                } else if (interfaceName.equals(getWifiStaAddress())) {
                    LogUtil.info(TAG, "interfaceName equal sta wifi addr, skip.");
                } else {
                    Inet4Address hostInet4Address = getIpv4Address(iface);
                    if (hostInet4Address == null) {
                        LogUtil.info(TAG, "interfaceName not assign ipv4, skip.");
                    } else {
                        hostInterface = iface;
                        LogUtil.info(TAG, "hostInterface is " + hostInterface);
                        return hostInterface;
                    }
                }
            }
            return null;
        } catch (SocketException exception) {
            LogUtil.error(TAG, "getHostApInterface SocketException : " + exception);
            return hostInterface;
        }
    }

    private Inet4Address getIpv4Address(NetworkInterface apif) {
        if (apif == null) {
            return null;
        }
        Enumeration<InetAddress> addresses = apif.getInetAddresses();
        while (addresses.hasMoreElements()) {
            InetAddress address = addresses.nextElement();
            LogUtil.debug(TAG, "getV4 is " + address);
            if (!address.isLoopbackAddress() && (address instanceof Inet4Address)) {
                Inet4Address hostInet4Address = (Inet4Address) address;
                return hostInet4Address;
            }
        }
        return null;
    }

    private String getHostBssid() {
        String hostBssid = null;
        NetworkInterface hostInterface = getHostApInterface();
        if (hostInterface == null) {
            return null;
        }
        try {
            hostBssid = formatHostBssid(hostInterface.getHardwareAddress());
            LogUtil.debug(TAG, "getHostBssid hostBssid : " + LogEncrypt.MacAddressEncrypt(hostBssid));
            return hostBssid;
        } catch (SocketException exception) {
            LogUtil.error(TAG, "getHostBssid SocketException : " + exception);
            return hostBssid;
        }
    }

    public String getHostIp() {
        NetworkInterface hostInterface = getHostApInterface();
        Inet4Address hostInet4Address = getIpv4Address(hostInterface);
        if (hostInet4Address != null) {
            LogUtil.debug(TAG, "getHostIp hostInet4Address : " + LogEncrypt.MacAddressEncrypt(hostInet4Address.getHostAddress()));
            return hostInet4Address.getHostAddress();
        }
        return null;
    }

    public WifiConnectInfo createWifiConnectInfo(SoftApConfiguration wifiAp) {
        LogUtil.debug(TAG, "createWifiConnectInfo mHostBssid: " + LogEncrypt.MacAddressEncrypt(this.mHostBssid) + " mHostIp:" + LogEncrypt.IpAddressEncrypt(this.mHostIp));
        if (wifiAp != null) {
            String hostBssid = getHostBssid();
            if (hostBssid != null) {
                this.mHostBssid = hostBssid;
            }
            String hostIp = getHostIp();
            if (hostIp != null) {
                this.mHostIp = hostIp;
            }
            if (this.mHostBssid == null || this.mHostIp == null) {
                LogUtil.info(TAG, "createWifiConnectInfo start retry ");
                int count = 0;
                while (true) {
                    if (count >= 3) {
                        break;
                    }
                    LogUtil.info(TAG, "createWifiConnectInfo retry count:" + count);
                    SystemClock.sleep(100L);
                    this.mHostIp = getHostIp();
                    this.mHostBssid = getHostBssid();
                    if (this.mHostBssid == null || this.mHostIp == null) {
                        count++;
                    } else {
                        LogUtil.debug(TAG, "createWifiConnectInfo retry mHostBssid: " + LogEncrypt.MacAddressEncrypt(this.mHostBssid) + " mHostIp:" + LogEncrypt.IpAddressEncrypt(this.mHostIp));
                        break;
                    }
                }
            }
        }
        return newConnectInfo(wifiAp, this.mHostIp, this.mHostBssid);
    }

    public WifiConnectInfo newConnectInfo(SoftApConfiguration wifiAp, String hostIp, String hostBssid) {
        LogUtil.debug(TAG, "newConnectInfo hostBssid: " + LogEncrypt.MacAddressEncrypt(hostBssid) + " hostIp:" + LogEncrypt.IpAddressEncrypt(hostIp));
        if (wifiAp != null) {
            WifiConnectInfo wifiConnectInfo = new WifiConnectInfo();
            wifiConnectInfo.setWifiSsid(wifiAp.getSsid());
            wifiConnectInfo.setWifiPassword(wifiAp.getPassphrase());
            if (wifiAp.getSecurityType() == 1) {
                wifiConnectInfo.setWifiSecurityType(Protos.WifiSecurityMode.WPA2_PERSONAL);
            } else if (wifiAp.getSecurityType() == 2) {
                wifiConnectInfo.setWifiSecurityType(Protos.WifiSecurityMode.WPA2_WPA3_PERSONAL);
            } else {
                wifiConnectInfo.setWifiSecurityType(Protos.WifiSecurityMode.OPEN);
            }
            if (hostBssid != null) {
                wifiConnectInfo.setHostBssid(hostBssid);
            } else {
                wifiConnectInfo.setHostBssid("00:11:22:33:44:55");
            }
            if (hostIp != null) {
                wifiConnectInfo.setHostIp(hostIp);
            } else {
                wifiConnectInfo.setHostIp("192.168.43.1");
            }
            return wifiConnectInfo;
        }
        return null;
    }
}
