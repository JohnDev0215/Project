package com.ts.androidauto.service.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice;
import com.ts.androidauto.sdk.aidl.data.ConnectRequest;
import com.ts.androidauto.service.common.DeviceConnectionManagerModel;
import ts.car.service.dcm.data.ConnectionDevice;

/* JADX INFO: loaded from: classes2.dex */
public class AutoConnectManager {
    private static final String ACTION_ACC_ON = "andoirdauto.acc.on";
    private static final int AUTO_CONNECT_DELAY_MS = 1000;
    private static final int AUTO_CONNECT_FAILED_RETRY_MS = 20000;
    private static final int BLUETOOTH_RECONNECT_CHECK_DELAY_MS = 1000;
    private static final int CHECK_RECONNECT_CONDITION_INTERVAL_MS = 1000;
    private static final int CHECK_RECONNECT_CONDITION_TIMES = 45;
    private static final int MSG_AUTO_CONNECT_PROJECTION = 1;
    private static final int MSG_CHECK_RECONNECT_CONDITION = 2;
    private static final int MSG_RECONNECT_WIRED_PROJECTION = 3;
    private static final String PREFERENCE_LAST_DEVICE = "last_device";
    private static final String PROJECTION_BTMAC_KEY = "projection_btmac_key";
    private static final String PROJECTION_DEVID_KEY = "projection_devid_key";
    private static final String TAG = "AAPAutoConnectManager";
    private static AutoConnectManager mAutoConnectManagerInstance;
    private Context mContext;
    private DeviceConnectionManagerModel.Listener mDcmListener;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private String mLastDeviceId;
    private String mLastDeviceMac;
    private boolean mWirelessAvailable = false;
    private boolean mIsAapConnected = false;
    private int mCheckReconnectConditionCount = 0;
    private boolean mIsLastDeviceInited = false;
    private BroadcastReceiver mAccOnReceiver = new BroadcastReceiver() { // from class: com.ts.androidauto.service.device.AutoConnectManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (AutoConnectManager.ACTION_ACC_ON.equals(action)) {
                LogUtil.info(AutoConnectManager.TAG, "ACTION_ACC_ON receiver");
                AutoConnectManager.this.getLastDeviceInfo();
                if (AutoConnectManager.this.mLastDeviceId != null && !AutoConnectManager.this.mIsAapConnected) {
                    LogUtil.info(AutoConnectManager.TAG, "ACTION_ACC_ON reconnect mLastDeviceId:" + LogEncrypt.MacAddressEncrypt(AutoConnectManager.this.mLastDeviceId) + "mWirelessAvailable:" + AutoConnectManager.this.mWirelessAvailable);
                    ConnectionDevice device = DeviceConnectionManagerModel.getInstance().getDevice(AutoConnectManager.this.mLastDeviceId);
                    if (device != null && device.isAvailableCapability(1024)) {
                        AutoConnectManager.this.mWirelessAvailable = true;
                    }
                    LogUtil.info(AutoConnectManager.TAG, "ACC_ON reconnect mWirelessAvailable:" + AutoConnectManager.this.mWirelessAvailable);
                    AutoConnectManager.this.startCheckReconnectCondition(1000);
                }
            }
        }
    };

    public static synchronized AutoConnectManager getInstance() {
        if (mAutoConnectManagerInstance == null) {
            mAutoConnectManagerInstance = new AutoConnectManager();
        }
        return mAutoConnectManagerInstance;
    }

    private AutoConnectManager() {
    }

    public void init(Context context) {
        this.mContext = context;
        getLastDeviceInfo();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.androidauto.service.device.AutoConnectManager.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 1) {
                    AutoConnectManager.this.handleAutoConnectProjection();
                    return;
                }
                if (i == 2) {
                    AutoConnectManager.this.handleCheckReconnectCondition();
                } else if (i == 3) {
                    String deviceId = msg.getData().getString("deviceId");
                    AutoConnectManager.this.handleReConnectWiredProjection(deviceId);
                }
            }
        };
        this.mDcmListener = new DeviceConnectionManagerModel.Listener() { // from class: com.ts.androidauto.service.device.AutoConnectManager.2
            @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
            public void onDeviceStatusChanged(ConnectionDevice device) {
                LogUtil.info(AutoConnectManager.TAG, "[onDeviceStatusChanged] device : " + device);
                if (device != null) {
                    if (device.isActivatedCapability(128) && device.getActiveCapabilityState(128) == 4) {
                        if (device.isAvailableCapability(1024) && !TextUtils.isEmpty(device.getBtAddr())) {
                            AutoConnectManager.this.saveLastDevice(device.getDeviceId(), device.getBtAddr());
                            AutoConnectManager.this.mWirelessAvailable = true;
                            AutoConnectManager.this.mIsAapConnected = true;
                            return;
                        }
                        return;
                    }
                    if (device.isActivatedCapability(1024) && device.getActiveCapabilityState(1024) == 4) {
                        AutoConnectManager.this.saveLastDevice(device.getDeviceId(), device.getBtAddr());
                        AutoConnectManager.this.mWirelessAvailable = true;
                        AutoConnectManager.this.mIsAapConnected = true;
                    } else if (device.getDeviceId() != null && device.getDeviceId().equals(AutoConnectManager.this.mLastDeviceId)) {
                        device.isPaired();
                    }
                }
            }
        };
        DeviceConnectionManagerModel.getInstance().registerListener(this.mDcmListener);
        initPowerReceiver();
    }

    private void initPowerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ACC_ON);
        this.mContext.registerReceiver(this.mAccOnReceiver, filter);
    }

    public void deleteAndroidAutoDevice(AndroidAutoDevice androidAutoDevice) {
        getLastDeviceInfo();
        String devBtMacId = androidAutoDevice.getBtAddr();
        String devId = androidAutoDevice.getUsbSerialNumber();
        LogUtil.debug(TAG, "[deleteAndroidAutoDevice]  mLastDeviceId=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceId) + " mLastDeviceMac=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceMac) + " AndroidAutoDevice.getBtAddr" + LogEncrypt.MacAddressEncrypt(devBtMacId) + " AndroidAutoDevice.getUsbSerialNumber" + LogEncrypt.MacAddressEncrypt(devId));
        if ((!TextUtils.isEmpty(devBtMacId) && devBtMacId.equals(this.mLastDeviceMac)) || ((!TextUtils.isEmpty(devId) && devId.equals(this.mLastDeviceId)) || (!TextUtils.isEmpty(devBtMacId) && devBtMacId.equals(this.mLastDeviceId)))) {
            saveLastDevice(null, null);
            removeLastDevicePreference();
        }
    }

    public void onCallbackStatusChanged(com.ts.androidauto.fsdk.data.AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
        LogUtil.info(TAG, "[onCallbackStatusChanged] device:" + linkDevice + " status:" + linkStatus + " errcode:" + errorCode);
        if (linkDevice != null && linkStatus != null) {
            LogUtil.debug(TAG, "[onCallbackStatusChanged] device isAutoConnect=" + linkDevice.isAutoConnect());
            if (linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED && linkDevice.getDeviceId() != null) {
                if (linkDevice.isWireLink() && errorCode == AndroidAutoConst.LinkErrorCode.ERROR_PING_TIMEOUT) {
                    Message message = this.mHandler.obtainMessage(3);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", linkDevice.getDeviceId());
                    message.setData(bundle);
                    this.mHandler.sendMessageDelayed(message, 1000L);
                    return;
                }
                if (linkDevice.getDeviceId().equals(this.mLastDeviceId)) {
                    this.mIsAapConnected = false;
                    if (errorCode == AndroidAutoConst.LinkErrorCode.ERROR_AUTHENTICATION_FAILURE_CERT_NOT_YET_VALID || errorCode == AndroidAutoConst.LinkErrorCode.ERROR_NOT_SUPPORTED || errorCode == AndroidAutoConst.LinkErrorCode.ERROR_USER_SELECT_DISCONNECT || errorCode == AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE) {
                        saveLastDevice(null, null);
                        return;
                    }
                    if (linkDevice.getConnMode() != AndroidAutoConst.ConnMode.MODE_USER_REQUEST.ordinal()) {
                        if (linkDevice.isAutoConnect()) {
                            if (linkDevice.isWireLessLink()) {
                                startCheckReconnectCondition(AUTO_CONNECT_FAILED_RETRY_MS);
                            }
                        } else if (linkDevice.isWireLessLink()) {
                            startCheckReconnectCondition(1000);
                        }
                    }
                }
            }
        }
    }

    public void updateBtProfileStatusChanged(final String address, final String profile, final BluetoothConst.ProfileStatus status) {
        LogUtil.debug(TAG, "[updateBtProfileStatusChanged] address:" + LogEncrypt.MacAddressEncrypt(address) + " mLastDeviceMac=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceMac) + " profile:" + profile + " status:" + status + " mIsAapConnected=" + this.mIsAapConnected);
        if (this.mIsLastDeviceInited) {
            if (address != null && address.equals(this.mLastDeviceMac) && !this.mIsAapConnected && status == BluetoothConst.ProfileStatus.PROFILE_STATUS_CONNECTED) {
                startCheckReconnectCondition(1000);
                return;
            }
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.ts.androidauto.service.device.-$$Lambda$AutoConnectManager$nYh6K2fHCIw_DMZ1IkXj795y1bU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateBtProfileStatusChanged$0$AutoConnectManager(address, profile, status);
                }
            }, 1000L);
        }
    }

    public /* synthetic */ void lambda$updateBtProfileStatusChanged$0$AutoConnectManager(String address, String profile, BluetoothConst.ProfileStatus status) {
        LogUtil.debug(TAG, "updateBtProfileStatusChanged retry.");
        updateBtProfileStatusChanged(address, profile, status);
    }

    public void onWirelessAvailable(String btMac, boolean available) {
        LogUtil.debug(TAG, "[onWirelessAvailable] btMac:" + LogEncrypt.MacAddressEncrypt(btMac) + " available:" + available);
        getLastDeviceInfo();
        if (btMac != null && btMac.equals(this.mLastDeviceMac)) {
            this.mWirelessAvailable = available;
            if (available && !this.mIsAapConnected) {
                LogUtil.debug(TAG, "[onWirelessAvailable] reconnect android auto btMac:" + LogEncrypt.MacAddressEncrypt(btMac) + " mIsAapConnected=" + this.mIsAapConnected);
                handleAutoConnectProjection();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCheckReconnectCondition(int delayMs) {
        LogUtil.info(TAG, "startCheckReconnectCondition. delayMs=" + delayMs + " mHandler=" + this.mHandler);
        Handler handler = this.mHandler;
        if (handler != null) {
            this.mCheckReconnectConditionCount = 45;
            handler.removeMessages(2);
            this.mHandler.sendEmptyMessageDelayed(2, delayMs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckReconnectCondition() {
        LogUtil.info(TAG, "handleCheckReconnectCondition. mWirelessAvailable=" + this.mWirelessAvailable + " mCheckReconnectConditionCount=" + this.mCheckReconnectConditionCount);
        int i = this.mCheckReconnectConditionCount;
        if (i > 0) {
            if (this.mWirelessAvailable) {
                this.mCheckReconnectConditionCount = 0;
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.sendEmptyMessage(1);
                    return;
                }
                return;
            }
            this.mCheckReconnectConditionCount = i - 1;
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.removeMessages(2);
                this.mHandler.sendEmptyMessageDelayed(2, 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleAutoConnectProjection() {
        LogUtil.debug(TAG, "[handleAutoConnectProjection] deviceId=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceId));
        if (this.mLastDeviceId != null) {
            DeviceConnectionManagerModel.getInstance().autoConnectProjection(this.mLastDeviceId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleReConnectWiredProjection(String deviceId) {
        LogUtil.debug(TAG, "[handleReConnectWiredProjection] deviceId=" + LogEncrypt.MacAddressEncrypt(deviceId));
        if (!TextUtils.isEmpty(deviceId)) {
            ConnectRequest request = new ConnectRequest();
            request.setConnectType(1);
            request.setUserRequest(true);
            request.setDeviceId(deviceId);
            DeviceConnectionManagerModel.getInstance().connectAndroidAuto(request);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void saveLastDevice(String devId, String btMac) {
        LogUtil.debug(TAG, "[saveLastDevice] deviceId=" + LogEncrypt.MacAddressEncrypt(devId) + " btmac=" + LogEncrypt.MacAddressEncrypt(btMac) + " mLastDeviceId=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceId));
        if (this.mContext != null) {
            if (!TextUtils.isEmpty(devId) && !TextUtils.isEmpty(btMac)) {
                if (!devId.equals(this.mLastDeviceId)) {
                    this.mCheckReconnectConditionCount = 0;
                }
                SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(PREFERENCE_LAST_DEVICE, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PROJECTION_DEVID_KEY, devId);
                this.mLastDeviceId = devId;
                editor.putString(PROJECTION_BTMAC_KEY, btMac);
                this.mLastDeviceMac = btMac;
                editor.apply();
            } else {
                this.mLastDeviceId = null;
                this.mLastDeviceMac = null;
                this.mWirelessAvailable = false;
                this.mCheckReconnectConditionCount = 0;
            }
        }
    }

    private void removeLastDevicePreference() {
        LogUtil.debug(TAG, "[removeLastDevicePreference] deviceId=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceId) + " btmac=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceMac));
        Context context = this.mContext;
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_LAST_DEVICE, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PROJECTION_DEVID_KEY, null);
            editor.putString(PROJECTION_BTMAC_KEY, null);
            editor.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void getLastDeviceInfo() {
        if (this.mContext != null) {
            SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(PREFERENCE_LAST_DEVICE, 0);
            this.mLastDeviceId = sharedPreferences.getString(PROJECTION_DEVID_KEY, null);
            this.mLastDeviceMac = sharedPreferences.getString(PROJECTION_BTMAC_KEY, null);
            LogUtil.debug(TAG, "[getLastDeviceInfo] deviceId=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceId) + " btmac=" + LogEncrypt.MacAddressEncrypt(this.mLastDeviceMac));
        }
        this.mIsLastDeviceInited = true;
    }
}
