package com.ts.carplay.lib.network;

import android.net.LinkAddress;
import android.os.IBinder;
import android.os.INetworkManagementService;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.server.net.BaseNetworkObserver;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.linkadapter.iap.data.AccessoryWiFiInfo;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class NetworkManagement {
    private static final int NEW_CONNECTION_IP_LENGHT = 16;
    private static final String NEW_CONNECTION_USB_NAME = "usb0";
    public static final int WAIT_FOR_IP_TIMEOUT = 3000;
    private String isWaitingForIpInterfaceName;
    private volatile String mUsbIpAddress;
    private volatile String mWifiApIpAddress;
    private static NetworkManagement INSTANCE = new NetworkManagement();
    private static String TAG = "NetworkManagement";
    private final Object mWaitForUsbIpLock = new Object();
    private final Object mWaitForWifiIpLock = new Object();
    private final HashMap<String, String> mWifiIpAddresses = new HashMap<>();

    public static NetworkManagement getInstance() {
        return INSTANCE;
    }

    private NetworkManagement() {
        try {
            IBinder binder = ServiceManager.getService("network_management");
            INetworkManagementService mNetworkManagerService = INetworkManagementService.Stub.asInterface(binder);
            mNetworkManagerService.registerObserver(new InterfaceObserver());
        } catch (RemoteException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    private class InterfaceObserver extends BaseNetworkObserver {
        private InterfaceObserver() {
        }

        public void addressUpdated(String iface, LinkAddress address) {
            LogUtil.debug(NetworkManagement.TAG, "addressUpdated:" + iface + ",address" + LogEncrypt.IpAddressEncrypt(address.getAddress().getHostAddress().toString()));
            if ("usb0".equals(iface)) {
                NetworkManagement.this.setUsbIpAddress(address);
            } else {
                NetworkManagement.this.addWifiAddress(iface, address);
            }
        }

        public void addressRemoved(String iface, LinkAddress address) {
            LogUtil.debug(NetworkManagement.TAG, "addressRemoved:" + iface + ",address" + LogEncrypt.IpAddressEncrypt(address.getAddress().getHostAddress().toString()));
            if ("usb0".equals(iface)) {
                NetworkManagement.this.setUsbIpAddress(null);
            } else {
                NetworkManagement.this.removeWifiAddress(iface, address);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUsbIpAddress(LinkAddress address) {
        synchronized (this.mWaitForUsbIpLock) {
            if (address == null) {
                this.mUsbIpAddress = null;
            } else {
                this.mUsbIpAddress = address.getAddress().getHostAddress().toString().split("%")[0];
                if (this.isWaitingForIpInterfaceName.equals("usb0")) {
                    LogUtil.info(TAG, "addWifiAddress, wait for ip success");
                    this.isWaitingForIpInterfaceName = null;
                    this.mWaitForUsbIpLock.notifyAll();
                }
            }
        }
    }

    public String getUsbIpAddress() {
        synchronized (this.mWaitForUsbIpLock) {
            LogUtil.debug(TAG, "getUsbIpAddress, mUsbIpAddress: " + LogEncrypt.IpAddressEncrypt(this.mUsbIpAddress));
            if (this.mUsbIpAddress == null) {
                LogUtil.info(TAG, "getUsbIpAddress, wait for callback");
                this.isWaitingForIpInterfaceName = "usb0";
                try {
                    this.mWaitForUsbIpLock.wait(3000L);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
        if (this.mUsbIpAddress == null) {
            this.mUsbIpAddress = getUsbIpAddressFromNetworkInterface();
        }
        return this.mUsbIpAddress;
    }

    private String getUsbIpAddressFromNetworkInterface() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().contains("usb0")) {
                    Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                    while (enumIpAddr.hasMoreElements()) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet6Address)) {
                            return inetAddress.getHostAddress().toString().split("%")[0];
                        }
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            LogUtil.error(TAG, ex.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWifiAddress(String iface, LinkAddress address) {
        synchronized (this.mWaitForWifiIpLock) {
            String ip = checkWifiApIpAddress(address.getAddress());
            if (ip != null) {
                if (this.isWaitingForIpInterfaceName != null && this.isWaitingForIpInterfaceName.equals(iface)) {
                    LogUtil.info(TAG, "addWifiAddress, wait for ip success");
                    this.mWifiApIpAddress = ip;
                    this.isWaitingForIpInterfaceName = null;
                    this.mWaitForWifiIpLock.notifyAll();
                }
                this.mWifiIpAddresses.put(iface, ip);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeWifiAddress(String iface, LinkAddress address) {
        synchronized (this.mWaitForWifiIpLock) {
            String ip = checkWifiApIpAddress(address.getAddress());
            if (ip != null) {
                this.mWifiIpAddresses.remove(iface);
            }
        }
    }

    public String getWifiApIpAddress(AccessoryWiFiInfo config) {
        String ipAddress;
        synchronized (this.mWaitForWifiIpLock) {
            if (config == null) {
                LogUtil.info(TAG, "getWifiApIpAddress, config is null");
                return null;
            }
            LogUtil.info(TAG, "getWifiApIpAddress, config: " + config);
            this.mWifiApIpAddress = this.mWifiIpAddresses.get(config.getInterfaceName());
            if (this.mWifiApIpAddress == null) {
                LogUtil.info(TAG, "not found ipAddress, wait for callback");
                this.isWaitingForIpInterfaceName = config.getInterfaceName();
                try {
                    this.mWaitForWifiIpLock.wait(3000L);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            if (this.mWifiApIpAddress == null && (ipAddress = getWifiApIpAddressFromNetworkInterface(config.getInterfaceName())) != null) {
                this.mWifiIpAddresses.put(config.getInterfaceName(), ipAddress);
                this.mWifiApIpAddress = ipAddress;
            }
            LogUtil.debug(TAG, "getWifiApIpAddress, mWifiApIpAddress:" + LogEncrypt.IpAddressEncrypt(this.mWifiApIpAddress));
            return this.mWifiApIpAddress;
        }
    }

    private String checkWifiApIpAddress(InetAddress inetAddress) {
        if (!inetAddress.isLoopbackAddress() && inetAddress.getAddress().length == 16) {
            LogUtil.debug(TAG, "checkWifiApIpAddress ipAddress: " + LogEncrypt.IpAddressEncrypt(inetAddress.getHostAddress().toString()));
            return inetAddress.getHostAddress().toString().split("%")[0];
        }
        return null;
    }

    private String getWifiApIpAddressFromNetworkInterface(String wifiApName) {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().contains(wifiApName)) {
                    Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                    while (enumIpAddr.hasMoreElements()) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        String ip = checkWifiApIpAddress(inetAddress);
                        if (ip != null) {
                            LogUtil.debug(TAG, "getWifiApIpAddressFromNetworkInterface, ip: " + LogEncrypt.IpAddressEncrypt(ip));
                            return ip;
                        }
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            LogUtil.error(TAG, ex.toString());
            return null;
        }
    }
}
