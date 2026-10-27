package com.ts.carplay.lib.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.SoftApInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ts.carplay.adapter.CarPlayAdapterFactory;
import com.ts.carplay.adapter.wifi.IWifiAdapter;
import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CarPlayInfo;
import com.ts.carplay.linkadapter.iap.data.AccessoryWiFiInfo;
import com.ts.carplay.linkadapter.iap.data.IapConstants;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayWifiManager {
    private static final String DEFULT_WIFI_MAC_ADDRESS = "02:00:00:00:00:02";
    private static final String EXTRA_REMOTE_DEVICE_ADDRESS = "macInfo";
    public static final int FLAG_1 = 1;
    public static final int FLAG_2 = 2;
    public static final int FLAG_3 = 3;
    private static final int[] FREQUENCY_TO_CHANNEL_MAP = {2412, 1, 1, 2417, 1, 2, 2422, 1, 3, 2427, 1, 4, 2432, 1, 5, 2437, 1, 6, 2442, 1, 7, 2447, 1, 8, 2452, 1, 9, 2457, 1, 10, 2462, 1, 11, 2467, 1, 12, 2472, 1, 13, 2484, 1, 14, 5170, 2, 34, 5180, 2, 36, 5190, 2, 38, 5200, 2, 40, 5210, 2, 42, 5220, 2, 44, 5230, 2, 46, 5240, 2, 48, 5260, 2, 52, 5280, 2, 56, 5300, 2, 60, 5320, 2, 64, 5500, 2, 100, 5520, 2, CarPlayInfo.CARPLAY_OEMICON_104_SIZE, 5540, 2, CarPlayConfig.DEFAULT_HEIGHT_PHYSICAL, 5560, 2, 112, 5580, 2, 116, 5600, 2, 120, 5620, 2, 124, 5640, 2, 128, 5660, 2, 132, 5680, 2, 136, 5700, 2, 140, 5720, 2, 144, 5745, 2, IapConstants.WifiChannelType.WIFI_CHANNEL_149, 5765, 2, 153, 5785, 2, IapConstants.WifiChannelType.WIFI_CHANNEL_157, 5805, 2, IapConstants.WifiChannelType.WIFI_CHANNEL_161, 5825, 2, 165, 5845, 2, 169, 5865, 2, 173, 5945, 4, 1, 5960, 4, 4, 6100, 4, 32};
    public static final int KEY_RANDOM_END_AFTER = 13;
    public static final int KEY_RANDOM_END_FRONT = 8;
    public static final int KEY_RANDOM_FIRST_AFTER = 9;
    public static final int KEY_RANDOM_FIRST_FRONT = 0;
    private static final int NEW_CONNECTION_IP_LENGHT = 16;
    private static final String TAG = "CarplayWifiManager";
    private static final int WIFI_AP_BAND = 2;
    private static final int WIFI_AP_CHANNEL = 153;
    private static final long WIFI_AP_OPERATION_TIME_OUT_SECOND = 3;
    private static final int WIFI_AP_SECURITY_TYPE = 2;
    private static final int WIFI_SECURITY_MAX_VALID = 14;
    private WifiManager.SoftApCallback mAaSoftApCllback;
    private HandlerThread mAaSoftApHandlerThread;
    private CarPlayManager mCarPlayManager;
    private final Condition mCondition;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;
    private final ReentrantLock mLock;
    private ConnectivityManager.OnStartTetheringCallback mTetheringCallback;
    private IWifiAdapter mWifiAdapter;
    private WifiConnectionReceiver mWifiConnectionReceiver;
    private WifiManager mWifiManager;
    private int mCurrWifiChannel = 153;
    private int mCurrWifiBand = 2;
    private String mWifiApName = "";
    private int mWifiApChannel = 153;

    public CarPlayWifiManager() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mLock = reentrantLock;
        this.mCondition = reentrantLock.newCondition();
        this.mAaSoftApCllback = new WifiManager.SoftApCallback() { // from class: com.ts.carplay.lib.wifi.CarPlayWifiManager.1
            public void onInfoChanged(SoftApInfo softApInfo) {
                LogUtil.info(CarPlayWifiManager.TAG, "onInfoChanged" + softApInfo);
                if (CarPlayWifiManager.this.is5GChennal(softApInfo.getFrequency())) {
                    LogUtil.info(CarPlayWifiManager.TAG, "onInfoChanged mCurrWifiChannel:" + CarPlayWifiManager.this.mCurrWifiChannel);
                }
            }

            public void onStateChanged(int state, int failureReason) {
                LogUtil.info(CarPlayWifiManager.TAG, "onStateChanged: state: " + state + " failure" + failureReason);
            }
        };
        this.mTetheringCallback = new ConnectivityManager.OnStartTetheringCallback() { // from class: com.ts.carplay.lib.wifi.CarPlayWifiManager.2
            public void onTetheringStarted() {
                LogUtil.info(CarPlayWifiManager.TAG, "onTetheringStarted");
            }

            public void onTetheringFailed() {
                LogUtil.info(CarPlayWifiManager.TAG, "onTetheringFailed");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean is5GChennal(int frequency) {
        boolean is5G = false;
        int i = 0;
        while (true) {
            int[] iArr = FREQUENCY_TO_CHANNEL_MAP;
            if (i < iArr.length) {
                if (frequency != iArr[i] || iArr[i + 1] != 2) {
                    i += 3;
                } else {
                    LogUtil.info(TAG, "is5GChennal FREQUENCY_TO_CHANNEL_MAP[" + i + "] " + FREQUENCY_TO_CHANNEL_MAP[i]);
                    LogUtil.info(TAG, "is5GChennal FREQUENCY_TO_CHANNEL_MAP[" + (i + 1) + "] " + FREQUENCY_TO_CHANNEL_MAP[i + 1]);
                    LogUtil.info(TAG, "is5GChennal FREQUENCY_TO_CHANNEL_MAP[" + (i + 2) + "] " + FREQUENCY_TO_CHANNEL_MAP[i + 2]);
                    int[] iArr2 = FREQUENCY_TO_CHANNEL_MAP;
                    this.mCurrWifiBand = iArr2[i + 1];
                    this.mCurrWifiChannel = iArr2[i + 2];
                    is5G = true;
                    break;
                }
            } else {
                break;
            }
        }
        LogUtil.info(TAG, "is5GChennal = " + is5G + " mCurrWifiBand = " + this.mCurrWifiBand + ", mCurrWifiChannel = " + this.mCurrWifiChannel);
        return is5G;
    }

    private void initCpSoftApHandler() {
        HandlerThread handlerThread = new HandlerThread("CpSoftApHandler", -19);
        this.mAaSoftApHandlerThread = handlerThread;
        handlerThread.start();
    }

    private class WifiConnectionReceiver extends BroadcastReceiver {
        private WifiConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                    int state = intent.getIntExtra("wifi_state", 14);
                    CarPlayWifiManager.this.mWifiApName = intent.getStringExtra("android.net.wifi.extra.WIFI_AP_INTERFACE_NAME");
                    LogUtil.info(CarPlayWifiManager.TAG, "onReceive, action:" + action + ", state:" + state);
                    if (state != 10 && state != 12) {
                        if (state == 13) {
                            CarPlayWifiManager.this.mWifiApName = intent.getStringExtra("android.net.wifi.extra.WIFI_AP_INTERFACE_NAME");
                            LogUtil.info(CarPlayWifiManager.TAG, "onReceive wifi ap is enabled mWifiApName:" + CarPlayWifiManager.this.mWifiApName);
                        }
                        if (CarPlayWifiManager.this.mWifiManager != null) {
                            SoftApConfiguration config = CarPlayWifiManager.this.mWifiManager.getSoftApConfiguration();
                            CarPlayWifiManager.this.onWifiApInfoChanged(config, state);
                        } else {
                            LogUtil.error(CarPlayWifiManager.TAG, "getSoftApConfiguration mWifiManager is null.");
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void init(Context context, CarPlayManager carPlayManager) {
        this.mContext = context;
        this.mCarPlayManager = carPlayManager;
        WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mWifiManager = wifiManager;
        if (wifiManager == null) {
            LogUtil.error(TAG, "getSystemService mWifiManager is null.");
        }
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        WifiConnectionReceiver wifiConnectionReceiver = new WifiConnectionReceiver();
        this.mWifiConnectionReceiver = wifiConnectionReceiver;
        this.mContext.registerReceiver(wifiConnectionReceiver, filter);
        IWifiAdapter wifiAdapter = CarPlayAdapterFactory.getWifiAdapter();
        this.mWifiAdapter = wifiAdapter;
        if (wifiAdapter != null) {
            wifiAdapter.init(context);
        }
        initCpSoftApHandler();
        this.mWifiManager.registerSoftApCallback(new HandlerExecutor(new Handler(this.mAaSoftApHandlerThread.getLooper())), this.mAaSoftApCllback);
    }

    public void uninit() {
        this.mContext.unregisterReceiver(this.mWifiConnectionReceiver);
        this.mWifiManager = null;
    }

    public boolean isWifiApEnabled() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            boolean isEnabled = wifiManager.isWifiApEnabled();
            return isEnabled;
        }
        LogUtil.warning(TAG, "isWifiApEnabled mWifiManager is null.");
        return false;
    }

    public boolean startSoftAp(SoftApConfiguration wifiConfig) {
        if (this.mWifiManager == null || this.mConnectivityManager == null) {
            LogUtil.warning(TAG, "startWifiAp, mWifiManager is null or mConnectivityManager is null.");
            return false;
        }
        if (isWifiEnabled()) {
            boolean ret = closeWifi();
            LogUtil.info(TAG, "startSoftAp closeWifi = " + ret);
        }
        IWifiAdapter iWifiAdapter = this.mWifiAdapter;
        if (iWifiAdapter != null) {
            int currentWifiChannel = iWifiAdapter.getSupportWifiChannel();
            LogUtil.debug(TAG, "get WifiChannel, current Wifi Channel is: " + currentWifiChannel);
            if (currentWifiChannel != 153) {
                this.mWifiApChannel = currentWifiChannel;
            }
        }
        SoftApConfiguration oldConfig = this.mWifiManager.getSoftApConfiguration();
        String preSharedKey = oldConfig.getPassphrase();
        if (TextUtils.isEmpty(preSharedKey)) {
            String uuid = UUID.randomUUID().toString();
            preSharedKey = uuid.substring(0, 8) + uuid.substring(9, 13);
        }
        if (!this.mWifiManager.isWifiApEnabled()) {
            boolean ret2 = this.mWifiManager.setSoftApConfiguration(new SoftApConfiguration.Builder(oldConfig).setChannel(this.mWifiApChannel, 2).setPassphrase(preSharedKey, 2).build());
            LogUtil.debug(TAG, "startWifiAp, enabling wifi... ret:" + ret2);
            try {
                try {
                    this.mLock.lock();
                    this.mConnectivityManager.startTethering(0, false, this.mTetheringCallback);
                    this.mCondition.awaitNanos(TimeUnit.SECONDS.toNanos(WIFI_AP_OPERATION_TIME_OUT_SECOND));
                } catch (InterruptedException expection) {
                    expection.printStackTrace();
                }
            } finally {
            }
        } else {
            SoftApConfiguration config = new SoftApConfiguration.Builder(oldConfig).setSsid(oldConfig.getSsid()).setChannel(this.mCurrWifiChannel, this.mCurrWifiBand).setPassphrase(preSharedKey, oldConfig.getSecurityType()).build();
            int currentBand = config.getBand();
            int currentChannel = config.getChannel();
            int currentAuthType = config.getSecurityType();
            if (currentAuthType != 2) {
                LogUtil.debug(TAG, "startWifiAp, currentBand:" + currentBand + " currentChannel:" + currentChannel + " currentAuthType:" + currentAuthType + " wifi ap running with un-expected parm, stop it.");
                try {
                    try {
                        this.mLock.lock();
                        this.mConnectivityManager.stopTethering(0);
                        this.mCondition.awaitNanos(TimeUnit.SECONDS.toNanos(WIFI_AP_OPERATION_TIME_OUT_SECOND));
                    } finally {
                    }
                } catch (InterruptedException expection2) {
                    expection2.printStackTrace();
                }
                this.mLock.unlock();
                startSoftAp(null);
            } else {
                LogUtil.debug(TAG, "startWifiAp, wifi ap already enabled withthe specified band and channel. currentAuthType:" + currentAuthType);
                onWifiApInfoChanged(config, 13);
            }
        }
        LogUtil.debug(TAG, "startWifiAp, enabling wifi... finish");
        return true;
    }

    public boolean stopSoftAp() {
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager != null) {
            connectivityManager.stopTethering(0);
            return true;
        }
        LogUtil.warning(TAG, "stopSoftAp mWifiManager is null.");
        return false;
    }

    public boolean isWifiEnabled() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            boolean isEnabled = wifiManager.isWifiEnabled();
            return isEnabled;
        }
        LogUtil.warning(TAG, "isWifiEnabled mWifiManager is null.");
        return false;
    }

    public boolean closeWifi() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            boolean ret = wifiManager.setWifiEnabled(false);
            return ret;
        }
        LogUtil.warning(TAG, "closeWifi mWifiManager is null.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWifiApInfoChanged(SoftApConfiguration config, int status) {
        LogUtil.debug(TAG, "onWifiApInfoChanged config:" + config + " status:" + status);
        if (this.mCarPlayManager != null) {
            AccessoryWiFiInfo wifiInfo = new AccessoryWiFiInfo();
            wifiInfo.setSsid(config.getSsid());
            wifiInfo.setPassphrase(config.getPassphrase());
            LogUtil.info(TAG, "onWifiApInfoChanged Security:" + config.getSecurityType());
            int securityType = config.getSecurityType();
            if (securityType == 0) {
                wifiInfo.setSecurity(0);
            } else if (securityType == 1) {
                wifiInfo.setSecurity(2);
            } else if (securityType == 2) {
                wifiInfo.setSecurity(3);
            } else {
                wifiInfo.setSecurity(-1);
            }
            wifiInfo.setChannel(config.getChannel());
            wifiInfo.setInterfaceName(this.mWifiApName);
            String macAddress = getWifiMacAddress();
            wifiInfo.setMacAddress(macAddress);
            LogUtil.debug(TAG, "onWifiApInfoChanged macAddress:" + LogEncrypt.MacAddressEncrypt(macAddress));
            LogUtil.info(TAG, "onWifiApInfoChanged convert AccessoryWiFiInfo:" + wifiInfo);
            this.mCarPlayManager.updateWifiApInfoChanged(wifiInfo, status);
        } else {
            LogUtil.warning(TAG, "updateWifiApInfoChanged mCarPlayManager is null.");
        }
        if (status == 11 || status == 13) {
            try {
                this.mLock.lock();
                LogUtil.info(TAG, "onWifiApInfoChanged operation signal ");
                this.mCondition.signalAll();
            } finally {
                this.mLock.unlock();
            }
        }
    }

    private String getWifiApIpAddress() {
        Enumeration<NetworkInterface> en;
        NetworkInterface intf;
        try {
            en = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException ex) {
            LogUtil.error(TAG, ex.toString());
        }
        while (en.hasMoreElements() && (intf = en.nextElement()) != null && intf.getName() != null && this.mWifiApName != null) {
            if (intf.getName().contains(this.mWifiApName)) {
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress.getAddress().length == 16) {
                        LogUtil.debug(TAG, "hostapd ipAddress: " + LogEncrypt.IpAddressEncrypt(inetAddress.getHostAddress()));
                        return inetAddress.getHostAddress();
                    }
                    return null;
                }
            }
        }
        return null;
    }

    private String getWifiMacAddress() {
        NetworkInterface networkInterface;
        byte[] addr;
        StringBuffer buf = new StringBuffer();
        try {
            if (this.mWifiApName != null && (networkInterface = NetworkInterface.getByName(this.mWifiApName)) != null && (addr = networkInterface.getHardwareAddress()) != null && addr.length != 0) {
                for (byte b : addr) {
                    buf.append(String.format("%02X:", Byte.valueOf(b)).toLowerCase(Locale.US));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                String macAddress = buf.toString();
                return macAddress;
            }
            return DEFULT_WIFI_MAC_ADDRESS;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return DEFULT_WIFI_MAC_ADDRESS;
        }
    }
}
