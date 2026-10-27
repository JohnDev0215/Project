package com.ts.androidauto.aap.wifi;

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
import android.os.Message;
import android.text.TextUtils;
import com.google.android.projection.proto.Protos;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class AapWifiManager {
    private static final int EVENT_WIFI_AP_STATE_CHANGE = 1;
    public static final int FLAG_1 = 1;
    public static final int FLAG_2 = 2;
    public static final int FLAG_3 = 3;
    public static final int KEY_RANDOM_END_AFTER = 18;
    public static final int KEY_RANDOM_END_CENTER = 13;
    public static final int KEY_RANDOM_END_FRONT = 8;
    public static final int KEY_RANDOM_FIRST_AFTER = 14;
    public static final int KEY_RANDOM_FIRST_CENTER = 9;
    public static final int KEY_RANDOM_FIRST_FRONT = 0;
    public static final int PSD_MIN_LENGTH = 12;
    private static final int WIFI_AP_BAND = 2;
    private static final int WIFI_AP_CHANNEL = 153;
    private static final int WIFI_AP_STOP_TIME_OUT = 5000;
    private static AapWifiManager mAapWifiManager;
    private HandlerThread mAaSoftApHandlerThread;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;
    private int mDesiredBand;
    private int mDesiredChannel;
    private int mDesiredSecurityType;
    private AapWifiListener mWifiApListener;
    private WifiManager mWifiManager;
    private static final String TAG = "AAP" + AapWifiManager.class.getSimpleName();
    private static final int[] FREQUENCY_TO_CHANNEL_MAP = {2412, 1, 1, 2417, 1, 2, 2422, 1, 3, 2427, 1, 4, 2432, 1, 5, 2437, 1, 6, 2442, 1, 7, 2447, 1, 8, 2452, 1, 9, 2457, 1, 10, 2462, 1, 11, 2467, 1, 12, 2472, 1, 13, 2484, 1, 14, 5170, 2, 34, 5180, 2, 36, 5190, 2, 38, 5200, 2, 40, 5210, 2, 42, 5220, 2, 44, 5230, 2, 46, 5240, 2, 48, 5260, 2, 52, 5280, 2, 56, 5300, 2, 60, 5320, 2, 64, 5500, 2, 100, 5520, 2, Protos.KeyCode.KEYCODE_BUTTON_L2_VALUE, 5540, 2, Protos.KeyCode.KEYCODE_BUTTON_START_VALUE, 5560, 2, Protos.KeyCode.KEYCODE_FORWARD_DEL_VALUE, 5580, 2, Protos.KeyCode.KEYCODE_SCROLL_LOCK_VALUE, 5600, 2, 120, 5620, 2, Protos.KeyCode.KEYCODE_INSERT_VALUE, 5640, 2, 128, 5660, 2, 132, 5680, 2, 136, 5700, 2, 140, 5720, 2, 144, 5745, 2, 149, 5765, 2, 153, 5785, 2, 157, 5805, 2, Protos.KeyCode.KEYCODE_NUMPAD_EQUALS_VALUE, 5825, 2, Protos.KeyCode.KEYCODE_INFO_VALUE, 5845, 2, Protos.KeyCode.KEYCODE_ZOOM_OUT_VALUE, 5865, 2, Protos.KeyCode.KEYCODE_DVR_VALUE, 5945, 4, 1, 5960, 4, 4, 6100, 4, 32};
    private int mCurrWifiChannel = 153;
    private int mCurrWifiBand = 2;
    private WifiManager.SoftApCallback mAaSoftApCllback = new WifiManager.SoftApCallback() { // from class: com.ts.androidauto.aap.wifi.AapWifiManager.1
        public void onInfoChanged(SoftApInfo softApInfo) {
            LogUtil.info(AapWifiManager.TAG, "onInfoChanged" + softApInfo);
            if (AapWifiManager.this.is5GChennal(softApInfo.getFrequency())) {
                LogUtil.info(AapWifiManager.TAG, "onInfoChanged mCurrWifiChannel:" + AapWifiManager.this.mCurrWifiChannel);
            }
        }

        public void onStateChanged(int state, int failureReason) {
            LogUtil.info(AapWifiManager.TAG, "onStateChanged: state: " + state + " failure" + failureReason);
        }
    };
    private Runnable enableWifiApRunnable = new Runnable() { // from class: com.ts.androidauto.aap.wifi.AapWifiManager.2
        @Override // java.lang.Runnable
        public void run() {
            AapWifiManager aapWifiManager = AapWifiManager.this;
            aapWifiManager.startWifiAp(aapWifiManager.mDesiredSecurityType, AapWifiManager.this.mDesiredBand, AapWifiManager.this.mDesiredChannel);
        }
    };
    private ConnectivityManager.OnStartTetheringCallback mTetheringCallback = new ConnectivityManager.OnStartTetheringCallback() { // from class: com.ts.androidauto.aap.wifi.AapWifiManager.3
        public void onTetheringStarted() {
            LogUtil.error(AapWifiManager.TAG, "onTetheringStarted");
        }

        public void onTetheringFailed() {
            LogUtil.error(AapWifiManager.TAG, "onTetheringFailed");
        }
    };
    private BroadcastReceiver mWifiConnectionReceiver = new BroadcastReceiver() { // from class: com.ts.androidauto.aap.wifi.AapWifiManager.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                int state = intent.getIntExtra("wifi_state", 14);
                LogUtil.debug(AapWifiManager.TAG, "onReceive, action:" + action + ", state:" + state);
                Message msg = AapWifiManager.this.mHandler.obtainMessage(1);
                msg.arg1 = state;
                AapWifiManager.this.mHandler.sendMessage(msg);
            }
        }
    };
    Handler mHandler = new Handler(new Handler.Callback() { // from class: com.ts.androidauto.aap.wifi.AapWifiManager.5
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            LogUtil.debug(AapWifiManager.TAG, "handleMessage msg.what:" + msg.what);
            if (msg.what == 1 && AapWifiManager.this.mWifiApListener != null) {
                int state = msg.arg1;
                SoftApConfiguration config = AapWifiManager.this.mWifiManager.getSoftApConfiguration();
                if (state == 11) {
                    AapWifiManager.this.mWifiApListener.onWifiApInfoChanged(config, 11);
                    return false;
                }
                if (state == 10) {
                    AapWifiManager.this.mWifiApListener.onWifiApInfoChanged(config, 10);
                    return false;
                }
                if (state == 12) {
                    AapWifiManager.this.mWifiApListener.onWifiApInfoChanged(config, 12);
                    return false;
                }
                if (state == 13) {
                    AapWifiManager.this.mWifiApListener.onWifiApInfoChanged(config, 13);
                    return false;
                }
                if (state == 14) {
                    AapWifiManager.this.mWifiApListener.onWifiApInfoChanged(config, 14);
                    return false;
                }
                return false;
            }
            return false;
        }
    });

    public AapWifiManager(Context context) {
        this.mContext = context;
    }

    public static synchronized AapWifiManager getInstance(Context context) {
        if (mAapWifiManager == null) {
            mAapWifiManager = new AapWifiManager(context);
        }
        return mAapWifiManager;
    }

    public void init() {
        this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        initWifiApReceiver();
        initCpSoftApHandler();
        this.mWifiManager.registerSoftApCallback(new HandlerExecutor(new Handler(this.mAaSoftApHandlerThread.getLooper())), this.mAaSoftApCllback);
    }

    private void initWifiApReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        this.mContext.registerReceiver(this.mWifiConnectionReceiver, filter);
    }

    public void registWifiApListener(AapWifiListener wifiApListener) {
        this.mWifiApListener = wifiApListener;
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

    public void startWifiAp(int securityType, int apBand, int apChannel) {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null || this.mConnectivityManager == null) {
            LogUtil.error(TAG, "startWifiAp, mWifiManager is null or mConnectivityManager is null.");
            return;
        }
        this.mDesiredSecurityType = securityType;
        this.mDesiredBand = apBand;
        this.mDesiredChannel = apChannel;
        SoftApConfiguration currentConfig = wifiManager.getSoftApConfiguration();
        if (currentConfig == null) {
            LogUtil.debug(TAG, "startWifiAp, SoftApConfiguration is null.");
            AapWifiListener aapWifiListener = this.mWifiApListener;
            if (aapWifiListener != null) {
                aapWifiListener.onWifiApInfoChanged(null, 14);
                return;
            }
            return;
        }
        if (!this.mWifiManager.isWifiApEnabled()) {
            LogUtil.debug(TAG, "startWifiAp, enabling wifi...");
            String preSharedKey = currentConfig.getPassphrase();
            while (!checkPassword(preSharedKey)) {
                LogUtil.debug(TAG, "checkPassword false, createPassword");
                preSharedKey = createPassword();
            }
            boolean ret = this.mWifiManager.setSoftApConfiguration(new SoftApConfiguration.Builder(currentConfig).setChannel(this.mDesiredChannel, this.mDesiredBand).setPassphrase(preSharedKey, this.mDesiredSecurityType).build());
            LogUtil.debug(TAG, "startWifiAp, enabling wifi... ret:" + ret);
            this.mConnectivityManager.startTethering(0, false, this.mTetheringCallback);
            return;
        }
        SoftApConfiguration config = new SoftApConfiguration.Builder(currentConfig).setSsid(currentConfig.getSsid()).setChannel(this.mCurrWifiChannel, this.mCurrWifiBand).setPassphrase(currentConfig.getPassphrase(), currentConfig.getSecurityType()).build();
        int currentBand = config.getBand();
        int currentChannel = config.getChannel();
        boolean isDesiredSecurityType = false;
        if (config.getSecurityType() == 1 || config.getSecurityType() == 2) {
            isDesiredSecurityType = true;
        }
        boolean isDesiredPassword = checkPassword(config.getPassphrase());
        if (!isDesiredSecurityType || !isDesiredPassword) {
            LogUtil.debug(TAG, "currentBand=" + currentBand + ",currentChannel=" + currentChannel + ",isDesiredSecurityType=" + isDesiredSecurityType + ",isDesiredPassword=" + isDesiredPassword);
            LogUtil.debug(TAG, "startWifiAp, wifi ap running with un-expected band or channel or password, stop it.");
            this.mConnectivityManager.stopTethering(0);
            this.mHandler.postDelayed(this.enableWifiApRunnable, 5000L);
            return;
        }
        LogUtil.debug(TAG, "processEnableWifiAp, wifi ap already enabled with the specified band, channel and password.");
        AapWifiListener aapWifiListener2 = this.mWifiApListener;
        if (aapWifiListener2 != null) {
            aapWifiListener2.onWifiApInfoChanged(config, 13);
        }
    }

    public void disableWlan() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null) {
            LogUtil.error(TAG, "disableWlan error, mWifiManager is null!");
            return;
        }
        if (wifiManager.isWifiEnabled()) {
            boolean ret = this.mWifiManager.setWifiEnabled(false);
            LogUtil.debug(TAG, "disableWlan close wlan, ret=" + ret);
            return;
        }
        LogUtil.debug(TAG, "disableWlan wlan is already closed.");
    }

    private String createPassword() {
        String uuid = UUID.randomUUID().toString();
        String psd = uuid.substring(0, 8).toUpperCase(Locale.US) + uuid.substring(9, 13).toLowerCase(Locale.US) + uuid.substring(14, 18).toLowerCase(Locale.US);
        return psd;
    }

    private boolean checkPassword(String psd) {
        if (!TextUtils.isEmpty(psd) && psd.length() >= 12) {
            Pattern pattern = Pattern.compile("(.*[A-Z].*)(.*[0-9].*)(.*[a-z].*)");
            return pattern.matcher(psd).matches();
        }
        return false;
    }

    public void deInit() {
        this.mHandler.removeCallbacks(this.enableWifiApRunnable);
        this.mContext.unregisterReceiver(this.mWifiConnectionReceiver);
    }
}
