package com.ts.androidauto.aap;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.net.wifi.SoftApConfiguration;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Surface;
import com.google.android.projection.transport.TcpServer;
import com.ts.androidauto.aap.bluetooth.AapBluetoothManager;
import com.ts.androidauto.aap.bluetooth.RfCommConnectionListener;
import com.ts.androidauto.aap.bluetooth.RfCommController;
import com.ts.androidauto.aap.common.CommonUtil;
import com.ts.androidauto.aap.common.ConnectionInfo;
import com.ts.androidauto.aap.common.StatusConst;
import com.ts.androidauto.aap.common.SurfaceData;
import com.ts.androidauto.aap.common.UiConfig;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.common.WifiConnectInfo;
import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.aap.device.DeviceManager;
import com.ts.androidauto.aap.wifi.AapWifiListener;
import com.ts.androidauto.aap.wifi.AapWifiManager;
import com.ts.androidauto.aap.wifi.WifiAccessPoint;
import com.ts.androidauto.fsdk.AndroidAutoAdapterFactory;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener;
import com.ts.androidauto.fsdk.interfaces.vehicle.IVehicleControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.video.IVideoControllerAdapter;
import com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener;
import com.ts.androidauto.fsdk.interfaces.wifi.IWifiApControllerAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class AapPhoneManager {
    private static final String ACTION_ACC_ON = "andoirdauto.acc.on";
    private static final int DELAY_NOTIFY_STATUS_TIME = 500;
    private static final int DELAY_TIME_500 = 500;
    private static final String FINISH_SHOW_ACTIVITY_ACTION = "com.ts.androidauto.app.display.displayactivity.RECEIVER_FINISH_ACTION";
    public static final int MSCREENHEIGHT1280 = 1280;
    public static final int MSCREENWIDTH1920 = 1920;
    private static final int MSG_RECONNECT_HFP_TIMEOUT_RFCOMM_NOTCONNECT = 1;
    private static final int NAVIGATION_STATE_UNAVAILABLE = 0;
    public static final int RECONNECT_HFP_MAX_COUNTS = 8;
    public static final int RECONNECT_HFP_SLEEP_INTERVAL_TIME = 500;
    private static final String TAG = "AAP" + AapPhoneManager.class.getSimpleName();
    public static final int TIMEOUT_RECONNECT_HFP_WHEN_RFCOMM_NOT_CONNECT = 30000;
    private static final int VOICE_RECOGNITION_STATE_ENDED = 1;
    private int[] mAaWifiChannels5g;
    private AapController mAapController;
    private AapWifiManager mAapWifiManager;
    private IAudioControllerAdapter mAudioControllerAdapter;
    private IBluetoothControllerAdapter mBtControllerAdapter;
    private String mBtPhoneAddress;
    private Context mContext;
    private DeviceManager mDeviceManager;
    private volatile boolean mIsWaitDisconnect;
    private AndroidAutoDevice mNeedConnectDevice;
    private RfCommController mRfCommController;
    private AndroidAutoDevice mTargetDevice;
    private TcpServer mTcpServer;
    private IVehicleControllerAdapter mVehicleControllerAdapter;
    private IVideoControllerAdapter mVideoControllerAdapter;
    private IWifiApControllerAdapter mWifiApControllerAdapter;
    private List<AapPhoneManagerListener> mAapPhoneManagerListenerList = new Vector();
    public boolean mIsNeedShowPair = false;
    private AndroidAutoDevice mLinkDevice = null;
    private BluetoothConst.BtPhoneStatus mBtPhoneStatus = BluetoothConst.BtPhoneStatus.PHONE_STATUS_IDLE;
    public boolean mIsShowMirrorHmi = false;
    private boolean mIsNotifyWifiInfo = false;
    private WifiConnectInfo mWifiConnectInfo = null;
    public boolean mIsReqCreateWiFiAp = false;
    private int mScreenWidth = 1920;
    private int mScreenHeight = MSCREENHEIGHT1280;
    private boolean mIsNeedConnectWirelessDevice = false;
    private boolean mIsFrxConnectAap = true;
    private boolean mIsForceProjection = false;
    private boolean mIsNeedShowProjectionOnPhoneCall = false;
    boolean isHasClientCert = false;
    boolean isHasRootCert = false;
    boolean isHasPrivateKey = false;
    private boolean mIsVehiclePowerStart = true;
    private boolean isRequestStartWifiAp = false;
    private String mAoaSerialNumber = null;
    private boolean mIsFirstAutoConnectError = false;
    private int mDisconntReason = 0;
    private RfCommConnectionListener mRfCommConnectionListener = new RfCommConnectionListener() { // from class: com.ts.androidauto.aap.AapPhoneManager.2
        @Override // com.ts.androidauto.aap.bluetooth.RfCommConnectionListener
        public void onConnectedTo(BluetoothSocket bluetoothSocket, boolean isServer) {
            if (bluetoothSocket != null && bluetoothSocket.getRemoteDevice() != null) {
                LogUtil.debug(AapPhoneManager.TAG, "onConnectedTo --- mIsNeedConnectWirelessDevice: " + AapPhoneManager.this.mIsNeedConnectWirelessDevice);
                BluetoothDevice remoteDevice = bluetoothSocket.getRemoteDevice();
                String connectAddress = remoteDevice.getAddress();
                LogUtil.info(AapPhoneManager.TAG, "mNeedConnectDevice:" + AapPhoneManager.this.mNeedConnectDevice);
                LogUtil.debug(AapPhoneManager.TAG, "onConnectedTo connectAddress:" + LogEncrypt.MacAddressEncrypt(connectAddress));
                if (AapPhoneManager.this.mIsNeedConnectWirelessDevice && AapPhoneManager.this.mNeedConnectDevice != null && AapPhoneManager.this.mNeedConnectDevice.getBtAddr() != null && connectAddress != null && connectAddress.equals(AapPhoneManager.this.mNeedConnectDevice.getBtAddr()) && isServer) {
                    LogUtil.debug(AapPhoneManager.TAG, "onConnectedTo ---  connect in wireless type");
                    LogUtil.info(AapPhoneManager.TAG, "Remove reconnect hfp timeout in delay connect.");
                    AapPhoneManager.this.removeMessage(1);
                    AapPhoneManager aapPhoneManager = AapPhoneManager.this;
                    aapPhoneManager.updateBluetoothDevice(aapPhoneManager.mNeedConnectDevice, remoteDevice);
                    LogUtil.info(AapPhoneManager.TAG, "onConnectedTo isAutoConnect:" + AapPhoneManager.this.mNeedConnectDevice.isAutoConnect());
                    AapPhoneManager aapPhoneManager2 = AapPhoneManager.this;
                    aapPhoneManager2.updateDeviceStatus(aapPhoneManager2.mNeedConnectDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING, null);
                    AapPhoneManager aapPhoneManager3 = AapPhoneManager.this;
                    aapPhoneManager3.connectWirelessDelay(bluetoothSocket, aapPhoneManager3.mNeedConnectDevice);
                    LogUtil.debug(AapPhoneManager.TAG, "connectWirelessDelay --- mNeedConnectDevice:" + AapPhoneManager.this.mNeedConnectDevice);
                    AapPhoneManager.this.mNeedConnectDevice = null;
                    AapPhoneManager.this.mIsNeedConnectWirelessDevice = false;
                    return;
                }
                AndroidAutoDevice linkDevice = AapPhoneManager.this.toAndroidAutoDevice(remoteDevice);
                AapPhoneManager.this.saveDevice(linkDevice);
                AndroidAutoDevice targetDevice = AapPhoneManager.this.getTargetDevice(linkDevice);
                if (targetDevice != null) {
                    AapPhoneManager.this.updateBluetoothDevice(targetDevice, remoteDevice);
                    if (!isServer) {
                        AapPhoneManager.this.notifiSupportAapWirelessResult(linkDevice, true, null);
                    } else {
                        AapPhoneManager.this.addWirelessDevice(bluetoothSocket, targetDevice);
                    }
                }
            }
        }

        @Override // com.ts.androidauto.aap.bluetooth.RfCommConnectionListener
        public List<AndroidAutoDevice> getCurrConnectedDevice() {
            return AapPhoneManager.this.getCurrConnectedDevice();
        }
    };
    private AapControllerListener mAapControllerListener = new AapControllerListener() { // from class: com.ts.androidauto.aap.AapPhoneManager.3
        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onPhoneManagerStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
            AapPhoneManager.this.onPhoneManagerStatusChanged(linkDevice, linkStatus, errorCode, AndroidAutoConst.DEFAULT_SCREEN_NAME);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onPhoneManagerStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
            AapPhoneManager.this.onPhoneManagerStatusChanged(linkDevice, linkStatus, errorCode, errorMessage);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onPhoneManagerBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
            AapPhoneManager.this.onPhoneManagerBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onPhoneManagerSetupWifiAp(AndroidAutoDevice linkDevice, boolean isMdRequest) {
            AapPhoneManager.this.onPhoneManagerSetupWifiAp(linkDevice, isMdRequest);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onPhoneManagerPoorWirelessNetworkConnectivity() {
            AapPhoneManager.this.onPhoneManagerPoorWirelessNetworkConnectivity();
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onPhoneManagerNotSupportAa(AndroidAutoDevice linkDevice) {
            AapPhoneManager.this.onPhoneManagerNotSupportAa(linkDevice);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onVehicleReqRfcommDisconnect(AndroidAutoDevice linkDevice) {
            AapPhoneManager.this.onVehicleReqRfcommDisconnect(linkDevice);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifiSupportAapWirelessResult(AndroidAutoDevice linkDevice, boolean support, String seriNum) {
            AapPhoneManager.this.notifiSupportAapWirelessResult(linkDevice, support, seriNum);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public int[] getSupportedWiFiChannels() {
            return AapPhoneManager.this.getSupportedWiFiChannels();
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyNaviFocusChanged(boolean useNativeNavigation) {
            AapPhoneManager.this.notifyNaviFocusChanged(useNativeNavigation);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyVoiceSessionNotification(int voiceSessionStatus) {
            AapPhoneManager.this.notifyVoiceSessionNotification(voiceSessionStatus);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyMdConnectRequest(AndroidAutoDevice linkDevice) {
            AapPhoneManager.this.notifyMdConnectRequest(linkDevice);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyVideoFocusStatusChanged(int videoFocusStatus) {
            AapPhoneManager.this.notifyVideoFocusStatusChanged(videoFocusStatus);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyClusterVideoFocusStatusChanged(int videoFocusStatus) {
            AapPhoneManager.this.notifyClusterVideoFocusStatusChanged(videoFocusStatus);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyClusterVideoUpdateUiConfigReply(boolean replyOk) {
            AapPhoneManager.this.notifyClusterVideoUpdateUiConfigReply(replyOk);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyNavigationStatusChanged(int status) {
            AapPhoneManager.this.notifyNavigationStatusChanged(status);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyNavigationState(NavigationStateData navigationStateData) {
            AapPhoneManager.this.notifyNavigationState(navigationStateData);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyNavigationCurrentPosition(NavigationPositionData navigationPositionData) {
            AapPhoneManager.this.notifyNavigationCurrentPosition(navigationPositionData);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyMediaStatusChange(int mediaStatus) {
            AapPhoneManager.this.notifyMediaStatusChange(mediaStatus);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyMediaMetadataChange(MediaMetadata mediaMetadata) {
            AapPhoneManager.this.notifyMediaMetadataChange(mediaMetadata);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void notifyMediaProgressChange(int progress) {
            AapPhoneManager.this.notifyMediaProgressChange(progress);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onMdAuthFailed() {
            LogUtil.info(AapPhoneManager.TAG, "onMdAuthFailed");
            AapPhoneManager.this.quitAapDisplayActivity();
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public void onMergeDevice(String devName, String devMac) {
            AapPhoneManager.this.onMergeDevice(devName, devMac);
        }

        @Override // com.ts.androidauto.aap.AapControllerListener
        public List<AndroidAutoDevice> getCurrConnectedDevice() {
            return AapPhoneManager.this.getCurrConnectedDevice();
        }
    };
    private IVideoControllerListener videoListener = new IVideoControllerListener() { // from class: com.ts.androidauto.aap.AapPhoneManager.4
        @Override // com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener
        public void onRequestVideoFocus(int requestType) {
            LogUtil.debug(AapPhoneManager.TAG, "onRequestVideoFocus --- requestType: " + requestType);
            AapPhoneManager.this.notifyRequestVideoFocus(requestType);
        }

        @Override // com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener
        public void onRequestClusterVideoFocus(int requestType) {
            LogUtil.debug(AapPhoneManager.TAG, "onRequestClusterVideoFocus --- requestType: " + requestType);
            AapPhoneManager.this.notifyRequestClusterVideoFocus(requestType);
        }

        @Override // com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener
        public void onPhoneCallStateChanged(int state) {
            if (state == 0) {
                LogUtil.info(AapPhoneManager.TAG, "TelephonyManager onCallStateChanged CALL_STATE_IDLE");
                AapPhoneManager.this.mIsShowMirrorHmi = false;
                return;
            }
            if (state == 1 || state == 2) {
                LogUtil.info(AapPhoneManager.TAG, "TelephonyManager onCallStateChanged CALL_STATE_OFFHOOK or RINGING " + state);
                LogUtil.info(AapPhoneManager.TAG, "TelephonyManager onCallStateChanged mIsShowMirrorHmi:" + AapPhoneManager.this.mIsShowMirrorHmi + " isShow:" + AapPhoneManager.this.getShowingFlag());
                AndroidAutoDevice currDevice = AapPhoneManager.this.getActivitedDeviceExceptIpod();
                if (currDevice == null || currDevice.getCurrentMode() != AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber()) {
                    LogUtil.info(AapPhoneManager.TAG, "Current device is not Android Auto, so do nothing");
                } else if (!AapPhoneManager.this.mIsShowMirrorHmi && !AapPhoneManager.this.getShowingFlag()) {
                    AapPhoneManager.this.showMirrorHmi(AndroidAutoConst.HmiLastMode.LAST_MODE_PHONE);
                    AapPhoneManager.this.mIsShowMirrorHmi = true;
                }
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener
        public void appStateChangedTo(ResourceConst.AppType appType, ResourceConst.AppState appState) {
            if (AapPhoneManager.this.mAapController != null) {
                AapPhoneManager.this.mAapController.appStateChangedTo(appType, appState);
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener
        public void onPowerStatusChange(boolean powerOff) {
            AapBluetoothManager.getInstance().onPowerStatusChange(powerOff);
            AndroidAutoDevice connectedDevice = AapPhoneManager.this.getActivitedDeviceExceptIpod();
            if (powerOff) {
                if (connectedDevice != null) {
                    LogUtil.info(AapPhoneManager.TAG, "poweroff disconnect connectedDevice : " + connectedDevice);
                    if (connectedDevice.isWireLessLink()) {
                        AapPhoneManager.this.disconnectProjection(connectedDevice.getDeviceId(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), 0);
                        return;
                    }
                    return;
                }
                return;
            }
            if (connectedDevice == null) {
                LogUtil.info(AapPhoneManager.TAG, "poweron reconnect wirelessDevice");
                AapPhoneManager.this.sendAccOnBroadcastReceiver(AapPhoneManager.ACTION_ACC_ON);
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.video.IVideoControllerListener
        public void onPowerStrChanged(boolean strOff) {
            LogUtil.info(AapPhoneManager.TAG, "onPowerStrChanged status: " + strOff);
            AapBluetoothManager.getInstance().onPowerStatusChange(strOff);
            AndroidAutoDevice connectedDevice = AapPhoneManager.this.getActivitedDeviceExceptIpod();
            if (strOff && connectedDevice != null) {
                LogUtil.info(AapPhoneManager.TAG, "onPowerStrChanged disconnect Device : " + connectedDevice);
                if (connectedDevice.isWireLessLink()) {
                    AapPhoneManager.this.disconnectProjection(connectedDevice.getDeviceId(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), 0);
                }
            }
        }
    };
    private IBluetoothControllerListener mBluetoothListener = new IBluetoothControllerListener() { // from class: com.ts.androidauto.aap.AapPhoneManager.5
        @Override // com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener
        public boolean onPairRequest(String address, String pinCode) {
            LogUtil.debug(AapPhoneManager.TAG, "onPairRequest address:" + LogEncrypt.MacAddressEncrypt(address) + " pinCode:" + pinCode);
            if (AapPhoneManager.this.mAapController != null) {
                return AapPhoneManager.this.mAapController.onPairRequest(address, pinCode);
            }
            return false;
        }

        @Override // com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener
        public void onPhoneStatusChange(String address, BluetoothConst.BtPhoneStatus status) {
            LogUtil.debug(AapPhoneManager.TAG, "onPhoneStatusChange address:" + LogEncrypt.MacAddressEncrypt(address) + " status:" + status);
            AapPhoneManager.this.mBtPhoneAddress = address;
            AapPhoneManager.this.mBtPhoneStatus = status;
            if (AapPhoneManager.this.mAapController != null) {
                AapPhoneManager.this.mAapController.onPhoneStatusChange(address, status);
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener
        public void onProfileChange(String address, String profile, BluetoothConst.ProfileStatus status) {
            LogUtil.debug(AapPhoneManager.TAG, "onProfileChange address:" + LogEncrypt.MacAddressEncrypt(address) + " status:" + status);
            AapPhoneManager.this.notifyProfileChange(address, profile, status);
            if (AapPhoneManager.this.mAapController != null) {
                AapPhoneManager.this.mAapController.onProfileChange(address, profile, status);
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener
        public void onBtEnabled() {
            if (AapPhoneManager.this.mAapController != null) {
                AapPhoneManager.this.mAapController.onBtEnabled();
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener
        public void onBtDisabled() {
            if (AapPhoneManager.this.mAapController != null) {
                AapPhoneManager.this.mAapController.onBtDisabled();
            }
        }

        @Override // com.ts.androidauto.fsdk.interfaces.bluetooth.IBluetoothControllerListener
        public void onBluetoothBondStateChanged(int state, BluetoothDevice device) {
            LogUtil.debug(AapPhoneManager.TAG, "onBluetoothBondStateChanged state:" + state + " bt device:" + device);
            if (state == 12) {
                AndroidAutoDevice linkDevice = AapPhoneManager.this.toAndroidAutoDevice(device);
                boolean isRfcommConnected = false;
                if (AapPhoneManager.this.mRfCommController != null) {
                    isRfcommConnected = AapPhoneManager.this.mRfCommController.isRfcommConnected(device);
                }
                if (linkDevice != null) {
                    LogUtil.debug(AapPhoneManager.TAG, "onBluetoothBondStateChanged linkdevice:" + linkDevice + " isRfcommConnected: " + isRfcommConnected);
                    if (isRfcommConnected && linkDevice.getCurrentMode() == AndroidAutoConst.LinkMode.LINK_MODE_RFCOMM.toNumber()) {
                        if (linkDevice.getBluetoothDevice() == null || linkDevice.getBluetoothDevice().getAddress() == null) {
                            LogUtil.warning(AapPhoneManager.TAG, "current bluetooth device is null or mac address  is null!");
                        } else {
                            AapPhoneManager.this.notifyUpdateProjectionCapability(linkDevice.getBluetoothDevice().getAddress(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), true);
                            LogUtil.warning(AapPhoneManager.TAG, "current device rfcomm connectnotifyUpdateProjectionCapability");
                        }
                    }
                }
            }
            if (AapPhoneManager.this.mAapController != null) {
                AapPhoneManager.this.mAapController.onBluetoothBondStateChanged(state, device);
            }
        }
    };
    private AapWifiListener wifiApListener = new AapWifiListener() { // from class: com.ts.androidauto.aap.AapPhoneManager.6
        @Override // com.ts.androidauto.aap.wifi.AapWifiListener
        public void onWifiApInfoChanged(SoftApConfiguration config, int status) {
            LogUtil.info(AapPhoneManager.TAG, "onWifiApInfoChanged config:" + config + " status:" + status);
            if (status == 13) {
                AapPhoneManager.this.notifyWifiApInfo(config);
                if (config != null && AapPhoneManager.this.isRequestStartWifiAp) {
                    AapPhoneManager aapPhoneManager = AapPhoneManager.this;
                    aapPhoneManager.connectWireless(aapPhoneManager.mTargetDevice);
                    AapPhoneManager.this.isRequestStartWifiAp = false;
                    return;
                }
                return;
            }
            AapPhoneManager.this.notifyWifiApInfo(null);
            if (status == 14 && AapPhoneManager.this.isRequestStartWifiAp) {
                AapPhoneManager aapPhoneManager2 = AapPhoneManager.this;
                aapPhoneManager2.onCallbackStatusChanged(aapPhoneManager2.mTargetDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                AapPhoneManager.this.isRequestStartWifiAp = false;
            }
        }
    };
    private Handler mHandler = new Handler() { // from class: com.ts.androidauto.aap.AapPhoneManager.9
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                AapPhoneManager.this.mIsNeedConnectWirelessDevice = false;
                if (msg.obj != null) {
                    AndroidAutoDevice device = (AndroidAutoDevice) msg.obj;
                    AapPhoneManager.this.onCallbackStatusChanged(device, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    LogUtil.info(AapPhoneManager.TAG, "Rfcomm connect timeout Skip call disconnect.");
                }
            }
        }
    };

    public AapPhoneManager() {
        this.mAapController = null;
        this.mAapController = AapController.getInstance();
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [com.ts.androidauto.aap.AapPhoneManager$1] */
    public void init(Context context) {
        this.mContext = context;
        LogUtil.info(TAG, "init context:" + this.mContext);
        initBluetooth();
        initWifiManager();
        initAudioController();
        initVideoController();
        initVehicleController();
        this.mAapController.setContext(this.mContext);
        this.mAapController.init(this.mVehicleControllerAdapter, this.mBtControllerAdapter, this.mAudioControllerAdapter, this.mVideoControllerAdapter);
        this.mAapController.registerAapControllerListener(this.mAapControllerListener);
        DeviceManager deviceManager = DeviceManager.getInstance();
        this.mDeviceManager = deviceManager;
        deviceManager.init();
        this.mRfCommController = RfCommController.getInstance();
        boolean enableWirelessAa = true;
        IVehicleControllerAdapter iVehicleControllerAdapter = this.mVehicleControllerAdapter;
        if (iVehicleControllerAdapter != null) {
            enableWirelessAa = iVehicleControllerAdapter.getWhetherEnableWirelessAa();
        }
        if (enableWirelessAa) {
            new Thread() { // from class: com.ts.androidauto.aap.AapPhoneManager.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    LogUtil.info(AapPhoneManager.TAG, "init thread start");
                    AapPhoneManager.this.initRfCommm();
                }
            }.start();
        }
    }

    public void deInit() {
        this.mAapWifiManager.deInit();
        this.mVideoControllerAdapter.deinit();
        this.mAapController.deInit();
        this.mAapController.unregisterAapControllerListener();
        deinitRfCommm();
        this.mHandler.removeMessages(1);
        this.mAapPhoneManagerListenerList.clear();
    }

    private void initBluetooth() {
        Context context = this.mContext;
        if (context != null) {
            IBluetoothControllerAdapter bluetoothControllerAdapter = AndroidAutoAdapterFactory.getBluetoothControllerAdapter(context);
            this.mBtControllerAdapter = bluetoothControllerAdapter;
            if (bluetoothControllerAdapter != null) {
                LogUtil.info(TAG, "initBluetooth success, mBtControllerAdapter is not null");
                this.mBtControllerAdapter.registBluetoothListener(this.mBluetoothListener);
                this.mBtControllerAdapter.init(this.mContext);
                return;
            }
            LogUtil.info(TAG, "initBluetooth failed, mBtControllerAdapter is null");
        }
    }

    private void initWifiManager() {
        Context context = this.mContext;
        if (context != null) {
            this.mWifiApControllerAdapter = AndroidAutoAdapterFactory.getWifiApControllerAdapter(context);
            AapWifiManager aapWifiManager = AapWifiManager.getInstance(this.mContext);
            this.mAapWifiManager = aapWifiManager;
            if (this.mWifiApControllerAdapter != null && aapWifiManager != null) {
                LogUtil.info(TAG, "initWifiManager success, not null");
                this.mAapWifiManager.init();
                this.mAapWifiManager.registWifiApListener(this.wifiApListener);
                return;
            }
            LogUtil.info(TAG, "initWifiManager failed");
        }
    }

    private void initAudioController() {
        Context context = this.mContext;
        if (context != null) {
            IAudioControllerAdapter audioControllerAdapter = AndroidAutoAdapterFactory.getAudioControllerAdapter(context);
            this.mAudioControllerAdapter = audioControllerAdapter;
            if (audioControllerAdapter != null) {
                LogUtil.info(TAG, "initAudioController success, mAudioControllerAdapter is not null");
                this.mAudioControllerAdapter.init();
            } else {
                LogUtil.info(TAG, "initAudioController failed, mAudioControllerAdapter is null");
            }
        }
    }

    private void initVideoController() {
        Context context = this.mContext;
        if (context != null) {
            IVideoControllerAdapter videoControllerAdapter = AndroidAutoAdapterFactory.getVideoControllerAdapter(context);
            this.mVideoControllerAdapter = videoControllerAdapter;
            if (videoControllerAdapter != null) {
                LogUtil.info(TAG, "initVideoController success, mVideoControllerAdapter is not null");
                this.mVideoControllerAdapter.init();
                this.mVideoControllerAdapter.registVideoListener(this.videoListener);
                return;
            }
            LogUtil.info(TAG, "initVideoController failed, mVideoControllerAdapter is null");
        }
    }

    private void initVehicleController() {
        Context context = this.mContext;
        if (context != null) {
            IVehicleControllerAdapter vehicleControllerAdapter = AndroidAutoAdapterFactory.getVehicleControllerAdapter(context);
            this.mVehicleControllerAdapter = vehicleControllerAdapter;
            if (vehicleControllerAdapter != null) {
                LogUtil.info(TAG, "initVehicleController success, mVehicleControllerAdapter is not null");
                this.mVehicleControllerAdapter.init();
            } else {
                LogUtil.info(TAG, "initVehicleController failed, mVehicleControllerAdapter is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRfCommm() {
        LogUtil.info(TAG, "initRfCommm");
        this.mRfCommController.init(this.mContext);
        this.mRfCommController.registRfCommListener(this.mRfCommConnectionListener);
        this.mTcpServer = TcpServer.getInstance();
        toStartRfcommServer();
    }

    private void deinitRfCommm() {
        RfCommController rfCommController = this.mRfCommController;
        if (rfCommController != null) {
            rfCommController.unRegistRfCommListener();
            this.mRfCommController.stopRfcommServerThread();
        }
        TcpServer tcpServer = this.mTcpServer;
        if (tcpServer != null) {
            tcpServer.stopTcpServerThread();
        }
    }

    public void toStartRfcommServer() {
        int[] iArr;
        if (this.mRfCommController != null && this.mTcpServer != null) {
            LogUtil.info(TAG, "toStartRfcommServer isAapWirelessEnable : true");
            LogUtil.info(TAG, "toStartRfcommServer mAaWifiChannels5g : " + this.mAaWifiChannels5g);
            LogUtil.info(TAG, "toStartRfcommServer mIsVehiclePowerStart : " + this.mIsVehiclePowerStart);
            LogUtil.info(TAG, "toStartRfcommServer  isHasClientCert: " + this.isHasClientCert + " isHasRootCert:" + this.isHasRootCert + " isHasPrivateKey:" + this.isHasPrivateKey);
            getSupportedWiFiChannels();
            if (1 != 0 && (iArr = this.mAaWifiChannels5g) != null && iArr.length > 0 && this.mIsVehiclePowerStart && this.isHasPrivateKey && this.isHasRootCert && this.isHasClientCert) {
                this.mRfCommController.startRfcommServerThread();
                this.mTcpServer.startTcpServerThread();
            } else {
                this.mRfCommController.stopRfcommServerThread();
                this.mTcpServer.stopTcpServerThread();
            }
        }
    }

    public AndroidAutoDevice toAndroidAutoDevice(BluetoothDevice remoteDevice) {
        if (remoteDevice == null) {
            return null;
        }
        AndroidAutoDevice device = new AndroidAutoDevice();
        device.setStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_INIT.ordinal());
        device.setLinkType(AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal());
        device.setCurrentMode(AndroidAutoConst.LinkMode.LINK_MODE_RFCOMM.toNumber());
        device.setBluetoothDevice(remoteDevice);
        device.setBtAddr(remoteDevice.getAddress());
        return device;
    }

    public void addWirelessDevice(BluetoothSocket bluetoothSocket, AndroidAutoDevice linkDevice) {
        RfCommController rfCommController;
        LogUtil.verbose(TAG, "addWirelessDevice socket: " + bluetoothSocket + " linkDevice: " + linkDevice);
        boolean result = checkSupportAapWireless(bluetoothSocket, linkDevice);
        LogUtil.verbose(TAG, "addWirelessDevice checkSupportDevice result: " + result);
        if (!result && (rfCommController = this.mRfCommController) != null && bluetoothSocket != null) {
            rfCommController.removeRfcommMap(bluetoothSocket.getRemoteDevice());
        }
    }

    public int connect(AndroidAutoDevice device) {
        if (device == null) {
            LogUtil.error(TAG, "connect device is null !");
            return AndroidAutoConst.LinkStatus.LINK_STATUS_INIT.ordinal();
        }
        LogUtil.info(TAG, "connect device : " + device.toString());
        this.mLinkDevice = device;
        AndroidAutoConst.LinkStatus linkState = connecting(device);
        LogUtil.info(TAG, "connecting linkState : " + linkState);
        return linkState.ordinal();
    }

    private int disConnect(AndroidAutoDevice device, StatusConst.ConnectErrorCode connectErrorCode) {
        if (device == null) {
            LogUtil.error(TAG, "disconnect device is null ! ");
            return AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED.ordinal();
        }
        AndroidAutoConst.LinkStatus state = disconnecting(device, connectErrorCode);
        LogUtil.info(TAG, "disconnecting linkState : " + state);
        if (state == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED) {
            LogUtil.error(TAG, "disconnect error ! ");
            onPhoneManagerStatusChanged(device, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE, AndroidAutoConst.DEFAULT_SCREEN_NAME);
            this.mLinkDevice = null;
            return AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED.ordinal();
        }
        this.mLinkDevice = null;
        LogUtil.info(TAG, "disconnecting success !");
        return state.ordinal();
    }

    public void show(Surface surface, int width, int height) {
        LogUtil.info(TAG, "showVideo " + surface + " width:" + width + " height:" + height);
        SurfaceData data = new SurfaceData();
        data.setSurface(surface);
        data.setWidth(width);
        data.setHeight(height);
        AndroidAutoDevice currLinkDevice = getCurrentDevice();
        if (currLinkDevice == null) {
            LogUtil.info(TAG, "show currLinkDevice is null return");
            return;
        }
        LogUtil.info(TAG, "show currentMode:" + currLinkDevice.getCurrentMode());
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.show(currLinkDevice, AndroidAutoConst.ShowMode.SHOW_MODE_HOME, data);
        }
        LogUtil.info(TAG, "Notify projection application to show view!");
        setShowingFlag(true);
        setLastVideoMode();
    }

    public void hide() {
        AndroidAutoDevice currLinkDevice = getCurrentDevice();
        if (currLinkDevice == null) {
            LogUtil.info(TAG, "hide currLinkDevice is null return");
            return;
        }
        boolean isAaSendFinisBroadcast = false;
        if (currLinkDevice.isAapMode()) {
            isAaSendFinisBroadcast = getFinishBroadcastFlag();
        }
        LogUtil.info(TAG, "hide currentMode:" + currLinkDevice.getCurrentMode() + " isAaSendFinisBroadcast:" + isAaSendFinisBroadcast);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.hide(currLinkDevice);
        }
        if (currLinkDevice.getStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED.ordinal() && !isAaSendFinisBroadcast) {
            LogUtil.info(TAG, "hide Set projection screen flag false");
            setShowingFlag(false);
        }
        setLastVideoMode();
    }

    public void showCluster(Surface surface, int width, int height) {
        LogUtil.info(TAG, "showCluster " + surface + " width:" + width + " height:" + height);
        SurfaceData data = new SurfaceData();
        data.setSurface(surface);
        data.setWidth(width);
        data.setHeight(height);
        AndroidAutoDevice currLinkDevice = getCurrentDevice();
        if (currLinkDevice == null) {
            LogUtil.info(TAG, "showCluster currLinkDevice is null return");
            return;
        }
        LogUtil.info(TAG, "showCluster currentMode:" + currLinkDevice.getCurrentMode());
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.showCluster(currLinkDevice, AndroidAutoConst.ShowMode.SHOW_MODE_HOME, data);
        }
        LogUtil.info(TAG, "Notify showClusterProjection application to show view!");
    }

    public void hideCluster() {
        AndroidAutoDevice currLinkDevice = getCurrentDevice();
        if (currLinkDevice == null) {
            LogUtil.info(TAG, "hideCluster currLinkDevice is null return");
            return;
        }
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.hideCluster(currLinkDevice);
        }
    }

    public void screenTouchEvent(MotionEvent event) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.screenTouchEvent(event);
        }
    }

    public void notifiWifiChannelSetupFinish(WifiConnectInfo wifiConnectInfo, boolean isSuccess) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.notifiWifiChannelSetupFinish(wifiConnectInfo, isSuccess);
        }
    }

    public void updateWifiApInfo(WifiConnectInfo wifiConnectInfo) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.updateWifiApInfo(wifiConnectInfo);
        }
    }

    public void updateWirelessDevice(AndroidAutoDevice linkDevice) {
        LogUtil.verbose(TAG, "updateWirelessDevice linkDevice: " + linkDevice);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.updateWirelessDevice(linkDevice);
        }
    }

    public void connectWirelessDelay(BluetoothSocket bluetoothSocket, AndroidAutoDevice linkDevice) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.connectWirelessDelay(bluetoothSocket, linkDevice);
        }
    }

    public boolean checkSupportAapWireless(BluetoothSocket bluetoothSocket, AndroidAutoDevice linkDevice) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            return aapController.checkSupportAapWireless(bluetoothSocket, linkDevice);
        }
        return false;
    }

    public AndroidAutoConst.LinkStatus connecting(AndroidAutoDevice device) {
        LogUtil.info(TAG, "Start connect with device:" + device);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            return aapController.connecting(device);
        }
        return AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED;
    }

    public AndroidAutoConst.LinkStatus disconnecting(AndroidAutoDevice device, StatusConst.ConnectErrorCode connectErrorCode) {
        LogUtil.verbose(TAG, "disconnecting device: " + device);
        if (this.mAapController != null) {
            LogUtil.info(TAG, "disconnecting mDisconntReason" + this.mDisconntReason);
            return this.mAapController.disconnecting(device, this.mDisconntReason);
        }
        return AndroidAutoConst.LinkStatus.LINK_STATUS_AVAILABLE;
    }

    public ResourceConst.VideoFocusReqResult requestVideoFocus(ResourceConst.VideoFocusRequest requestType) {
        LogUtil.debug(TAG, "requestVideoFocus --- requestType: " + requestType);
        ResourceConst.VideoFocusReqResult state = ResourceConst.VideoFocusReqResult.VIDEOFOCUS_REQUEST_GRANTED;
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            ResourceConst.VideoFocusReqResult state2 = iVideoControllerAdapter.requestVideoFocus(requestType);
            return state2;
        }
        return state;
    }

    public AndroidAutoDevice getLinkDevice() {
        return this.mLinkDevice;
    }

    public void showMirrorHmi(AndroidAutoConst.HmiLastMode mode) {
        LogUtil.verbose(TAG, "showMirrorHmi mode is :" + mode);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.showMirrorHmi(mode);
        }
    }

    public void next() {
        LogUtil.info(TAG, "next");
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.next();
        }
    }

    public void previous() {
        LogUtil.info(TAG, "previous");
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.previous();
        }
    }

    public int getMusicStatus() {
        LogUtil.info(TAG, "getMusicStatus");
        AapController aapController = this.mAapController;
        if (aapController != null) {
            return aapController.getMusicStatus();
        }
        return AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
    }

    public void play() {
        LogUtil.info(TAG, "play");
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.play();
        }
    }

    public void pause() {
        LogUtil.info(TAG, "pause");
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.pause();
        }
    }

    public MediaMetadata getMediaMetadata() {
        LogUtil.info(TAG, "getMediaMetadata");
        AapController aapController = this.mAapController;
        if (aapController != null) {
            return aapController.getMediaMetadata();
        }
        return null;
    }

    public int getMediaProgress() {
        LogUtil.info(TAG, "getMediaProgress");
        AapController aapController = this.mAapController;
        if (aapController == null) {
            return 0;
        }
        int progress = aapController.getMediaProgress();
        return progress;
    }

    public boolean getFinishBroadcastFlag() {
        AapController aapController = this.mAapController;
        if (aapController == null) {
            return false;
        }
        boolean result = aapController.getFinishBroadcastFlag();
        return result;
    }

    public void sendKeyEvent(int keyCode, int keyAction) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.sendKeyEvent(keyCode, keyAction);
            LogUtil.info(TAG, "sendKeyEvent keyAction: " + keyCode + " keyCode: " + keyAction);
            return;
        }
        LogUtil.info(TAG, "sendKeyEvent has no mAapController ! ");
    }

    public void sendKeyLongPressEvent(int keyCode) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.sendKeyLongPressEvent(keyCode);
            LogUtil.info(TAG, "sendKeyLongPressEvent keyCode:" + keyCode);
            return;
        }
        LogUtil.info(TAG, "sendKeyLongPressEvent has no mAapController ! ");
    }

    public void sendVehicleInfo(VehicleInfo vehicleInfo) {
        if (this.mAapController != null) {
            LogUtil.info(TAG, "sendVehicleInfo");
            this.mAapController.sendVehicleInfo(vehicleInfo);
        } else {
            LogUtil.info(TAG, "sendVehicleInfo has no mAapController ! ");
        }
        this.mIsNeedShowProjectionOnPhoneCall = vehicleInfo.mIsNeedShowProjectionOnPhoneCall;
        this.mAoaSerialNumber = vehicleInfo.mAoaSerialNumber;
        if (!TextUtils.isEmpty(vehicleInfo.mRootCert)) {
            this.isHasRootCert = true;
        }
        if (!TextUtils.isEmpty(vehicleInfo.mClientCert)) {
            this.isHasClientCert = true;
        }
        if (!TextUtils.isEmpty(vehicleInfo.mPrivateKey)) {
            this.isHasPrivateKey = true;
        }
    }

    public void setClusterUiConfig(UiConfig uiConfig) {
        if (this.mAapController != null) {
            LogUtil.info(TAG, "setClusterUiConfig");
            this.mAapController.setClusterUiConfig(uiConfig);
        } else {
            LogUtil.info(TAG, "setClusterUiConfig has no mAapController ! ");
        }
    }

    public void addAapPhoneManagerListener(AapPhoneManagerListener aapPhoneManagerListener) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && aapPhoneManagerListener != null) {
            list.add(aapPhoneManagerListener);
        }
    }

    public void removeAapPhoneManagerListener(AapPhoneManagerListener aapPhoneManagerListener) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty() && aapPhoneManagerListener != null && this.mAapPhoneManagerListenerList.contains(aapPhoneManagerListener)) {
            this.mAapPhoneManagerListenerList.remove(aapPhoneManagerListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhoneManagerStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
        LogUtil.info(TAG, "onStatusChanged linkStatusl:" + linkStatus + " errorCode:" + errorCode + " linkDevice:" + linkDevice);
        setLinkDevice(linkDevice);
        onCallbackStatusChanged(linkDevice, linkStatus, errorCode, errorMessage);
    }

    private void notifyStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                if (aapPhoneManagerListener != null) {
                    aapPhoneManagerListener.notifyStatusChanged(linkDevice, linkStatus, errorCode, errorMessage);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhoneManagerSetupWifiAp(AndroidAutoDevice linkDevice, boolean isMdRequest) {
        LogUtil.debug(TAG, "onPhoneManagerSetupWifiAp isMdRequest:" + isMdRequest + " mIsNotifyWifiInfo:" + this.mIsNotifyWifiInfo);
        WifiConnectInfo wifiConnectInfo = this.mWifiConnectInfo;
        if (wifiConnectInfo != null && this.mIsNotifyWifiInfo) {
            notifiWifiChannelSetupFinish(wifiConnectInfo, true);
        } else {
            LogUtil.info(TAG, "onPhoneManagerSetupWifiAp wifi info is not notify");
            this.mIsReqCreateWiFiAp = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhoneManagerBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
        boolean isProjectionScreen = getShowingFlag();
        LogUtil.info(TAG, "isProjectionScreen :" + isProjectionScreen);
        if (batteryCritical && !isProjectionScreen) {
            AndroidAutoDevice currDevice = getCurrentDevice();
            if (currDevice != null) {
                currDevice.setBatteryVol(batteryLevel);
            }
            List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
            if (list != null && !list.isEmpty()) {
                for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                    if (aapPhoneManagerListener != null) {
                        aapPhoneManagerListener.onBatteryStatusNotification(batteryLevel, timeRemainingS, true);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhoneManagerPoorWirelessNetworkConnectivity() {
        LogUtil.info(TAG, "onPoorWirelessNetworkConnectivity");
        quitAapDisplayActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhoneManagerNotSupportAa(AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "onNotSupportAa linkDevice: " + linkDevice);
        if (linkDevice != null) {
            String deviceId = linkDevice.getDeviceId();
            LogUtil.info(TAG, "onNotSupportAa not support deviceId : " + LogEncrypt.MacAddressEncrypt(deviceId));
            if (linkDevice.getUsbSerialNumber() != null) {
                notifyUpdateProjectionCapability(linkDevice.getUsbSerialNumber(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal(), false);
            }
            if (linkDevice.getBtAddr() != null) {
                notifyWirelessAvailable(linkDevice.getBtAddr(), false);
                notifyUpdateProjectionCapability(linkDevice.getBtAddr(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifiSupportAapWirelessResult(AndroidAutoDevice linkDevice, boolean isSupport, String seriNum) {
        LogUtil.info(TAG, "notifySupportAapWireless linkDevice=" + linkDevice);
        LogUtil.info(TAG, "notifySupportAapWireless isSupport=" + isSupport + " seriNum=" + LogEncrypt.MacAddressEncrypt(seriNum));
        if (isSupport) {
            if (linkDevice != null) {
                if (linkDevice.getBluetoothDevice() != null) {
                    notifyUpdateProjectionCapability(linkDevice.getBluetoothDevice().getAddress(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), true);
                } else {
                    LogUtil.warning(TAG, "wirelessCapabilityUpdate mConnectionManager is null.");
                }
                if (seriNum != null && !seriNum.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    linkDevice.setUsbSerialNumber(seriNum);
                    if (linkDevice.getDeviceId() != null) {
                        LogUtil.info(TAG, "notifySupportAapWireless to mergeDevice linkDevice=" + linkDevice);
                        notifyMergeDevice(linkDevice.getDeviceId(), 1, seriNum);
                    } else {
                        notifyMergeDevice(linkDevice.getBtAddr(), 1, seriNum);
                    }
                }
                notifyWirelessAvailable(linkDevice.getBtAddr(), true);
                this.mIsFirstAutoConnectError = true;
                AndroidAutoDevice currDevice = getCurrentDevice();
                if (currDevice != null && currDevice.equals(linkDevice)) {
                    LogUtil.info(TAG, "notifySupportAapWireless is same device, return");
                    return;
                }
                AndroidAutoConst.LinkStatus curStatus = AndroidAutoConst.LinkStatus.values()[linkDevice.getStatus()];
                LogUtil.info(TAG, "notifySupportAapWireless linkDevice curStatus : " + curStatus);
                int i = AnonymousClass10.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[curStatus.ordinal()];
                if (i == 1 || i == 2 || i == 3 || i == 4) {
                    onCallbackStatusChanged(linkDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_AVAILABLE, null, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    return;
                }
                return;
            }
            return;
        }
        if (linkDevice != null && linkDevice.getBluetoothDevice() != null) {
            LogUtil.debug(TAG, "notifySupportAapWireless not suppurt bt_mac= " + LogEncrypt.MacAddressEncrypt(linkDevice.getBluetoothDevice().getAddress()));
            notifyWirelessAvailable(linkDevice.getBtAddr(), false);
            notifyUpdateProjectionCapability(linkDevice.getBluetoothDevice().getAddress(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), false);
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.AapPhoneManager$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus;

        static {
            int[] iArr = new int[AndroidAutoConst.LinkStatus.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus = iArr;
            try {
                iArr[AndroidAutoConst.LinkStatus.LINK_STATUS_INIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_AVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public void notifyUpdateProjectionCapability(String brAddr, int linkType, boolean flag) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                if (aapPhoneManagerListener != null) {
                    aapPhoneManagerListener.notifyUpdateProjectionCapability(brAddr, linkType, flag);
                }
            }
        }
    }

    public void notifyWirelessAvailable(String btMac, boolean available) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                if (aapPhoneManagerListener != null) {
                    aapPhoneManagerListener.notifyWirelessAvailable(btMac, available);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVehicleReqRfcommDisconnect(AndroidAutoDevice linkDevice) {
        LogUtil.debug(TAG, "onVehicleReqRfcommDisconnect linkDevice:" + linkDevice);
        RfCommController rfCommController = this.mRfCommController;
        if (rfCommController != null && linkDevice != null) {
            rfCommController.removeRfcommMap(linkDevice.getBluetoothDevice());
        }
        if (linkDevice != null) {
            updateBluetoothDevice(linkDevice, null);
            if (getTargetDevice(linkDevice) == null && linkDevice.getBtAddr() != null && linkDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
                notifyWirelessAvailable(linkDevice.getBtAddr(), false);
                notifyUpdateProjectionCapability(linkDevice.getBtAddr(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNaviFocusChanged(boolean useNativeNavigation) {
        LogUtil.info(TAG, "notifyNaviFocusChanged useNativeNavigation:" + useNativeNavigation);
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                if (aapPhoneManagerListener != null) {
                    aapPhoneManagerListener.notifyNaviFocusChanged(useNativeNavigation);
                }
            }
        }
    }

    public int[] getSupportedWiFiChannels() {
        if (this.mAaWifiChannels5g == null) {
            IWifiApControllerAdapter iWifiApControllerAdapter = this.mWifiApControllerAdapter;
            if (iWifiApControllerAdapter != null) {
                int[] supported5GWifiChannels = iWifiApControllerAdapter.getSupported5GWifiChannels();
                if (supported5GWifiChannels != null && supported5GWifiChannels.length > 0) {
                    int[] iArr = new int[supported5GWifiChannels.length];
                    this.mAaWifiChannels5g = iArr;
                    Arrays.fill(iArr, 0);
                    for (int i = 0; i < supported5GWifiChannels.length; i++) {
                        this.mAaWifiChannels5g[i] = supported5GWifiChannels[i];
                    }
                } else {
                    LogUtil.info(TAG, "getSupportedWiFiChannels is null not support");
                }
            } else {
                LogUtil.info(TAG, "getSupportedWiFiChannels mWifiApControllerAdapter is null");
            }
        }
        return this.mAaWifiChannels5g;
    }

    public void sendNativeNaviStatus(boolean useNativeNavigation) {
        LogUtil.info(TAG, "sendNativeNaviStatus useNativeNavigation: " + useNativeNavigation);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.sendNativeNaviStatus(useNativeNavigation);
        }
    }

    public void notifyVoiceSessionNotification(int voiceSessionStatus) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyVoiceSessionNotification(voiceSessionStatus);
            }
        }
    }

    public void notifyNavigationStatusChanged(int status) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyNavigationStatusChanged(status);
            }
        }
    }

    public void notifyMdConnectRequest(AndroidAutoDevice linkDevice) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyMdConnectRequest(linkDevice);
            }
        }
    }

    public void notifyProfileChange(String address, String profile, BluetoothConst.ProfileStatus status) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyProfileChange(address, profile, status);
            }
        }
    }

    public void notifyWifiApInfo(SoftApConfiguration wifiAp) {
        LogUtil.error(TAG, "notifyWifiApInfo mIsReqCreateWiFiAp is:" + this.mIsReqCreateWiFiAp);
        if (wifiAp == null) {
            LogUtil.error(TAG, "wifiAp is null ! wifi off.");
            this.mIsNotifyWifiInfo = false;
            if (this.mIsReqCreateWiFiAp) {
                this.mIsReqCreateWiFiAp = false;
                notifiWifiChannelSetupFinish(null, false);
                return;
            }
            return;
        }
        this.mIsNotifyWifiInfo = true;
        WifiAccessPoint wifiAccessPoint = new WifiAccessPoint(this.mContext);
        WifiConnectInfo wifiConnectInfoCreateWifiConnectInfo = wifiAccessPoint.createWifiConnectInfo(wifiAp);
        this.mWifiConnectInfo = wifiConnectInfoCreateWifiConnectInfo;
        if (this.mIsReqCreateWiFiAp) {
            this.mIsReqCreateWiFiAp = false;
            notifiWifiChannelSetupFinish(wifiConnectInfoCreateWifiConnectInfo, true);
        } else {
            updateWifiApInfo(wifiConnectInfoCreateWifiConnectInfo);
        }
    }

    public void notifyRequestVideoFocus(int requestType) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyRequestVideoFocus(requestType);
            }
        }
    }

    public void notifyRequestClusterVideoFocus(int requestType) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyRequestClusterVideoFocus(requestType);
            }
        }
    }

    public void notifyMergeDevice(String deviceId, int mergeType, String mergeInfo) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyMergeDevice(deviceId, mergeType, mergeInfo);
            }
        }
    }

    public void notifyProjectionBluetoothAddressChanged(String address) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyProjectionBluetoothAddressChanged(address);
            }
        }
    }

    public void notifyAutoConnectStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyAutoConnectStatusChanged(linkDevice, linkStatus, errorCode);
            }
        }
    }

    /* JADX INFO: renamed from: notifyUpdateConnectionStatus, reason: merged with bridge method [inline-methods] */
    public void lambda$onCallbackStatusChanged$0$AapPhoneManager(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyUpdateConnectionStatus(linkDevice, linkStatus, errorCode);
            }
        }
    }

    public void notifyRequestAoaMode(AndroidAutoDevice linkDevice) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyRequestAoaMode(linkDevice, this.mAoaSerialNumber);
            }
        }
    }

    public void notifyVideoFocusStatusChanged(int videoFocusStatus) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyVideoFocusStatusChanged(videoFocusStatus);
            }
        }
    }

    public void notifyClusterVideoFocusStatusChanged(int videoFocusStatus) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyClusterVideoFocusStatusChanged(videoFocusStatus);
            }
        }
    }

    public void notifyClusterVideoUpdateUiConfigReply(boolean replyOk) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyClusterVideoUpdateUiConfigReply(replyOk);
            }
        }
    }

    public void notifyNavigationState(NavigationStateData navigationStateData) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyNavigationState(navigationStateData);
            }
        }
    }

    public void notifyNavigationCurrentPosition(NavigationPositionData navigationPositionData) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyNavigationCurrentPosition(navigationPositionData);
            }
        }
    }

    public void notifyMediaStatusChange(int mediaStatus) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyMediaStatusChange(mediaStatus);
            }
        }
    }

    public void notifyMediaMetadataChange(MediaMetadata mediaMetadata) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyMediaMetadataChange(mediaMetadata);
            }
        }
    }

    public void notifyMediaProgressChange(int progress) {
        List<AapPhoneManagerListener> list = this.mAapPhoneManagerListenerList;
        if (list != null && !list.isEmpty()) {
            for (AapPhoneManagerListener aapPhoneManagerListener : this.mAapPhoneManagerListenerList) {
                aapPhoneManagerListener.notifyMediaProgressChange(progress);
            }
        }
    }

    public void setOtherProjectionActive(boolean active) {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.setOtherProjectionActive(active);
        }
    }

    public void onMergeDevice(String devName, String devMac) {
        LogUtil.debug(TAG, "onMergeDevice devName: " + devName + " devMac" + devMac);
        AndroidAutoDevice connectedDevice = getActivitedDeviceExceptIpod();
        if (connectedDevice != null && devMac != null) {
            notifyMergeDevice(connectedDevice.getDeviceId(), 2, devMac);
            if (TextUtils.isEmpty(connectedDevice.getBtAddr())) {
                connectedDevice.setBtAddr(devMac);
                if (connectedDevice.getStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED.ordinal()) {
                    notifyStatusChanged(connectedDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                }
                if (this.mIsNeedShowProjectionOnPhoneCall && isPhoneCallActive(connectedDevice) && !getShowingFlag()) {
                    ResourceConst.VideoFocusReqResult result = requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                    LogUtil.info(TAG, "need to showprojection when device phonecall is active:" + result);
                }
            }
            notifyProjectionBluetoothAddressChanged(devMac);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAccOnBroadcastReceiver(String action) {
        if (this.mContext != null) {
            Intent intent = new Intent(action);
            this.mContext.sendBroadcast(intent);
            LogUtil.info(TAG, "sendAccOnBroadcastReceiver()");
        }
    }

    public AndroidAutoDevice getCurrentDevice() {
        List<AndroidAutoDevice> currDeviceList = null;
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            currDeviceList = deviceManager.getCurrDeviceList();
        }
        if (currDeviceList == null || currDeviceList.isEmpty()) {
            return null;
        }
        for (AndroidAutoDevice device : currDeviceList) {
            if (device != null) {
                int currMode = device.getCurrentMode();
                if (currMode == AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber()) {
                    return device;
                }
            }
        }
        return null;
    }

    public List<AndroidAutoDevice> getCurrDeviceList() {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager == null) {
            return null;
        }
        List<AndroidAutoDevice> currDeviceList = deviceManager.getCurrDeviceList();
        return currDeviceList;
    }

    public boolean isLegalStatus(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus) {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            AndroidAutoConst.LinkStatus oldStatus = deviceManager.getDeviceMirrorStatus(linkDevice);
            return this.mDeviceManager.isLegalStatus(oldStatus, linkStatus);
        }
        return true;
    }

    public void updateBluetoothDevice(AndroidAutoDevice linkDevice, BluetoothDevice btDev) {
        this.mDeviceManager.updateBluetoothDevice(linkDevice, btDev);
        this.mDeviceManager.dumpDeviceList();
    }

    public AndroidAutoDevice getTargetDevice(AndroidAutoDevice linkDevice) {
        return this.mDeviceManager.getTargetDevice(linkDevice);
    }

    public List<AndroidAutoDevice> getCurrConnectedDevice() {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager == null) {
            return null;
        }
        List<AndroidAutoDevice> connectedDeviceList = deviceManager.getCurrConnectedLinkDevice();
        return connectedDeviceList;
    }

    public AndroidAutoDevice getActivitedDeviceExceptIpod() {
        List<AndroidAutoDevice> connectedDeviceList = null;
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            connectedDeviceList = deviceManager.getCurrActivitedLinkDevice();
        }
        if (connectedDeviceList == null || connectedDeviceList.isEmpty()) {
            return null;
        }
        for (AndroidAutoDevice device : connectedDeviceList) {
            if (device != null) {
                return device;
            }
        }
        return null;
    }

    public AndroidAutoDevice getFrxDevice() {
        List<AndroidAutoDevice> frxDeviceList = null;
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            frxDeviceList = deviceManager.getCurrFrxLinkDevice();
        }
        if (frxDeviceList == null || frxDeviceList.isEmpty()) {
            return null;
        }
        for (AndroidAutoDevice device : frxDeviceList) {
            if (device != null) {
                int currMode = device.getCurrentMode();
                if (currMode == AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber()) {
                    return device;
                }
            }
        }
        return null;
    }

    public AndroidAutoDevice getDeviceByDeviceId(String deviceId, int linkType) {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            return deviceManager.getDeviceByDeviceId(deviceId, linkType);
        }
        return null;
    }

    public AndroidAutoConst.LinkStatus getDeviceMirrorStatus(AndroidAutoDevice device) {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager == null) {
            return null;
        }
        AndroidAutoConst.LinkStatus status = deviceManager.getDeviceMirrorStatus(device);
        return status;
    }

    public boolean saveDevice(AndroidAutoDevice linkDevice) {
        boolean isAdded = false;
        if (this.mDeviceManager != null && linkDevice != null) {
            LogUtil.info(TAG, "addDevice currentMode:" + linkDevice.getCurrentMode());
            isAdded = this.mDeviceManager.addDevice(linkDevice);
        }
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            deviceManager.dumpDeviceList();
        }
        return isAdded;
    }

    public int getLinkStatus() {
        int linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED.ordinal();
        AndroidAutoDevice currDevice = getCurrentDevice();
        if (currDevice != null && this.mDeviceManager != null) {
            return currDevice.getStatus();
        }
        return linkStatus;
    }

    public AndroidAutoDevice getDeviceByBluetoothDevice(BluetoothDevice btDevice) {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager == null) {
            return null;
        }
        AndroidAutoDevice device = deviceManager.getDeviceByBluetoothDevice(btDevice);
        return device;
    }

    public boolean isPhoneCallActive(AndroidAutoDevice device) {
        if (device != null && !TextUtils.isEmpty(this.mBtPhoneAddress) && this.mBtPhoneAddress.equals(device.getBtAddr()) && this.mBtPhoneStatus == BluetoothConst.BtPhoneStatus.PHONE_STATUS_ACTIVE) {
            return true;
        }
        return false;
    }

    public void quitAapDisplayActivity() {
        LogUtil.info(TAG, "When Bt or wifi failed poppup showing, finish AapActivity");
        Intent finishShowActivityIntent = new Intent();
        finishShowActivityIntent.setAction(FINISH_SHOW_ACTIVITY_ACTION);
        this.mContext.sendBroadcast(finishShowActivityIntent);
    }

    public void setLinkDevice(AndroidAutoDevice linkDevice) {
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            iVideoControllerAdapter.setLinkDevice(linkDevice);
        }
    }

    public void setShowingFlag(boolean isShowing) {
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            iVideoControllerAdapter.setShowingFlag(isShowing);
        }
    }

    public boolean getShowingFlag() {
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            return iVideoControllerAdapter.getShowingFlag();
        }
        return false;
    }

    public void showProjectionScreen(boolean isProjectionApp, int width, int height) {
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            iVideoControllerAdapter.showProjectionScreen(isProjectionApp, width, height);
        }
    }

    public void setLastVideoMode() {
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            iVideoControllerAdapter.setLastVideoMode();
        }
    }

    public ResourceConst.LastVideoMode getLastVideoMode() {
        IVideoControllerAdapter iVideoControllerAdapter = this.mVideoControllerAdapter;
        if (iVideoControllerAdapter != null) {
            return iVideoControllerAdapter.getLastVideoMode();
        }
        return null;
    }

    public boolean isLastVideoMode(AndroidAutoDevice device) {
        if (device == null || !device.isAapMode()) {
            return false;
        }
        ResourceConst.LastVideoMode lastVideoMode = getLastVideoMode();
        LogUtil.info(TAG, "isLastVideoMode lastVideoMode:" + lastVideoMode);
        if (lastVideoMode != ResourceConst.LastVideoMode.PROJECTION_MODE) {
            return false;
        }
        return true;
    }

    public void connectProjection(ConnectionInfo connectionInfo) {
        AndroidAutoDevice targetDevice;
        int linkType = connectionInfo.getLinkType();
        AndroidAutoDevice androidAutoDevice = connectionInfo.getAndroidAutoDevice();
        saveDevice(androidAutoDevice);
        onPhoneManagerStatusChanged(androidAutoDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE, AndroidAutoConst.DEFAULT_SCREEN_NAME);
        if (linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
            this.mIsFrxConnectAap = connectionInfo.isFirstConnect();
            this.mIsForceProjection = connectionInfo.isForceProjection();
            if (connectionInfo.isHasAvailableCapability()) {
                notifyRequestAoaMode(androidAutoDevice);
                return;
            } else {
                LogUtil.warning(TAG, "onConnectProjection ANDROID_AUTO_USB is NOT_SUPPORTED.");
                onPhoneManagerStatusChanged(androidAutoDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.ERROR_NOT_SUPPORTED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                return;
            }
        }
        if (linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
            this.mIsFrxConnectAap = connectionInfo.isFirstConnect();
            this.mIsForceProjection = connectionInfo.isForceProjection();
            TcpServer tcpServer = this.mTcpServer;
            if (tcpServer != null) {
                tcpServer.clearSocketConnection();
            }
            if (this.mRfCommController != null && (targetDevice = getTargetDevice(androidAutoDevice)) != null) {
                if (connectionInfo.isHasAvailableCapability() || !this.mRfCommController.isRfcommConnected(targetDevice.getBluetoothDevice())) {
                    updateDeviceStatus(targetDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING, null);
                    LogUtil.info(TAG, "onConnectProjection updateWirelessDevice :" + targetDevice);
                    updateWirelessDevice(targetDevice);
                    if (targetDevice.getUsbSerialNumber() != null && targetDevice.getDeviceId() != null) {
                        notifyMergeDevice(targetDevice.getDeviceId(), 1, targetDevice.getUsbSerialNumber());
                    }
                    this.mTargetDevice = targetDevice;
                    int[] iArr = this.mAaWifiChannels5g;
                    if (iArr != null && iArr.length > 0 && iArr[0] != 0) {
                        this.isRequestStartWifiAp = true;
                        this.mAapWifiManager.startWifiAp(1, 2, iArr[0]);
                        return;
                    } else {
                        LogUtil.info(TAG, "No supported 5g wifi channels, connect failed!");
                        onCallbackStatusChanged(this.mTargetDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                        return;
                    }
                }
                LogUtil.warning(TAG, "onConnectProjection ANDROID_AUTO_WIRELESS is NOT_SUPPORTED.");
                onPhoneManagerStatusChanged(androidAutoDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.ERROR_NOT_SUPPORTED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                return;
            }
            return;
        }
        LogUtil.debug(TAG, "onConnectProjection not AA Capability.");
    }

    public void disconnectProjection(String devId, int linkType, int reason) {
        AndroidAutoDevice targetDevice = getDeviceByDeviceId(devId, linkType);
        this.mDisconntReason = reason;
        LogUtil.info(TAG, "disconnectProjection mDisconntReason: " + this.mDisconntReason);
        if (targetDevice != null) {
            startDisConnect(targetDevice, StatusConst.ConnectErrorCode.RELEASE_APP);
        }
        this.mTargetDevice = null;
    }

    public void disConnectDevice(List<AndroidAutoDevice> connectedDeviceList) {
        if (connectedDeviceList != null && !connectedDeviceList.isEmpty()) {
            LogUtil.info(TAG, "disConnectDevice size:" + connectedDeviceList.size());
            AndroidAutoDevice device = connectedDeviceList.remove(0);
            LogUtil.info(TAG, "disConnectDevice device:" + device);
            if (device != null) {
                int status = device.getStatus();
                if (status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING.ordinal() && status == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED.ordinal()) {
                    startDisConnect(device, StatusConst.ConnectErrorCode.INVALID_CODE);
                    waitForDeviceDisconnected(device, connectedDeviceList);
                    return;
                } else {
                    if (status == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING.ordinal()) {
                        waitForDeviceDisconnected(device, connectedDeviceList);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        LogUtil.info(TAG, "disConnectDevice finish");
    }

    private void waitForDeviceDisconnected(final AndroidAutoDevice device, final List<AndroidAutoDevice> connectedDeviceList) {
        this.mIsWaitDisconnect = true;
        Thread thread = new Thread(new Runnable() { // from class: com.ts.androidauto.aap.AapPhoneManager.7
            @Override // java.lang.Runnable
            public void run() {
                while (AapPhoneManager.this.mIsWaitDisconnect) {
                    AndroidAutoConst.LinkStatus status = AapPhoneManager.this.getDeviceMirrorStatus(device);
                    LogUtil.info(AapPhoneManager.TAG, "waitForDeviceDisconnected status:" + status);
                    if (status == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED || status == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || status == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR) {
                        AapPhoneManager.this.mIsWaitDisconnect = false;
                        AapPhoneManager.this.disConnectDevice(connectedDeviceList);
                        return;
                    }
                    CommonUtil.sleep(500L);
                }
            }
        });
        thread.start();
    }

    public int startDisConnect(AndroidAutoDevice linkDevice, StatusConst.ConnectErrorCode connectErrorCode) {
        AndroidAutoDevice targetDevice = getTargetDevice(linkDevice);
        LogUtil.info(TAG, "disconnect targetDevice:" + targetDevice);
        if (targetDevice != null && targetDevice.getStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING.ordinal()) {
            LogUtil.info(TAG, "disconnect durring AA disconnecting, does not call disconnect");
            return 0;
        }
        if (targetDevice != null && targetDevice.getStatus() != AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING.ordinal() && targetDevice.getStatus() != AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED.ordinal() && targetDevice.getStatus() != AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX.ordinal()) {
            onCallbackStatusChanged(targetDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE, AndroidAutoConst.DEFAULT_SCREEN_NAME);
            return 0;
        }
        if (targetDevice == null) {
            notifyStatusChanged(targetDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE, AndroidAutoConst.DEFAULT_SCREEN_NAME);
            return 0;
        }
        LogUtil.info(TAG, "disconnect connectErrorCode:" + connectErrorCode);
        this.mIsNeedConnectWirelessDevice = false;
        int state = disConnect(linkDevice, connectErrorCode);
        return state;
    }

    public void onAaEnableUsbAoaFinished(boolean result, String deviceId, UsbDevice usbDevice) {
        AndroidAutoDevice device = getDeviceByDeviceId(deviceId, AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal());
        if (result) {
            StatusConst.AoaStatus status = StatusConst.AoaStatus.AOA_STATUS_SUCCESS;
            LogUtil.info(TAG, "onAaEnableUsbAoaFinished success, deviceId:" + deviceId + " device:" + device + " usb:" + usbDevice);
            updateDeviceStatus(device, null, status);
            if (device != null) {
                device.setUsbDevice(usbDevice);
            }
            connect(device);
            return;
        }
        LogUtil.info(TAG, "onAaEnableUsbAoaFinished failed, deviceId:" + deviceId + " device:" + device + " usb:" + usbDevice);
        onPhoneManagerStatusChanged(device, AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED, AndroidAutoConst.LinkErrorCode.ERROR_REQUEST_AOA_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [com.ts.androidauto.aap.AapPhoneManager$8] */
    public int connectWireless(final AndroidAutoDevice device) {
        IBluetoothControllerAdapter iBluetoothControllerAdapter = this.mBtControllerAdapter;
        if (iBluetoothControllerAdapter != null) {
            boolean isBtEnable = iBluetoothControllerAdapter.isBluetoothEnable();
            LogUtil.debug(TAG, "connectWireless isBtEnable:" + isBtEnable);
            if (!isBtEnable) {
                this.mBtControllerAdapter.enableBluetooth();
            }
        } else {
            LogUtil.debug(TAG, "connectWireless mBtControllerAdapter is null!");
        }
        if (this.mRfCommController == null || device == null) {
            return -1;
        }
        LogUtil.debug(TAG, "connect in wireless linkDevice.getBluetoothDevice():" + device.getBluetoothDevice());
        if (!this.mRfCommController.isRfcommConnected(device.getBluetoothDevice())) {
            Message message = new Message();
            message.what = 1;
            message.obj = device;
            this.mHandler.sendMessageDelayed(message, 30000L);
            LogUtil.debug(TAG, "rfcomm is not connected, wait for connecting it");
            this.mIsNeedConnectWirelessDevice = true;
            this.mNeedConnectDevice = device;
            LogUtil.info(TAG, "listenForWirelessDevice connect mNeedConnectDevice :" + this.mNeedConnectDevice);
            updateDeviceStatus(device, AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING, null);
            new Thread() { // from class: com.ts.androidauto.aap.AapPhoneManager.8
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (AapPhoneManager.this.mBtControllerAdapter != null) {
                        boolean isHfpConnected = AapPhoneManager.this.mBtControllerAdapter.isHfpConnected(device.getBtAddr());
                        if (!isHfpConnected) {
                            LogUtil.info(AapPhoneManager.TAG, "device hfp is not connected,connectHfp");
                            AapPhoneManager.this.mBtControllerAdapter.connectHfp(device.getBtAddr());
                            return;
                        }
                        LogUtil.info(AapPhoneManager.TAG, "PhoneController trigger Hfp reConnectHfp");
                        AapPhoneManager.this.mBtControllerAdapter.disconnectHfp(device.getBtAddr());
                        for (int idx = 0; idx < 8; idx++) {
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException exp) {
                                exp.printStackTrace();
                            }
                            if (!AapPhoneManager.this.mBtControllerAdapter.isHfpConnected(device.getBtAddr())) {
                                break;
                            }
                        }
                        AapPhoneManager.this.mBtControllerAdapter.connectHfp(device.getBtAddr());
                    }
                }
            }.start();
            return -1;
        }
        LogUtil.debug(TAG, "listenForWirelessDevice connect in wireless type");
        AndroidAutoConst.LinkStatus status = AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING;
        device.setStatus(status.ordinal());
        updateDeviceStatus(device, status, null);
        int state = connect(device);
        LogUtil.info(TAG, "PhoneController toConnect for Wireless!");
        return state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCallbackStatusChanged(final AndroidAutoDevice linkDevice, final AndroidAutoConst.LinkStatus linkStatus, final AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
        AndroidAutoDevice currentDevice = getCurrentDevice();
        LogUtil.info(TAG, "onCallbackStatusChanged linkStatus: " + linkStatus);
        LogUtil.info(TAG, "onCallbackStatusChanged device: " + linkDevice);
        LogUtil.info(TAG, "onCallbackStatusChanged current: " + currentDevice);
        if (linkDevice != null && linkDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal() && (linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR)) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.androidauto.aap.-$$Lambda$AapPhoneManager$p3PV_uxEgCtxQ1-BLqwEOVnRQe4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCallbackStatusChanged$0$AapPhoneManager(linkDevice, linkStatus, errorCode);
                }
            }, 500L);
        } else {
            lambda$onCallbackStatusChanged$0$AapPhoneManager(linkDevice, linkStatus, errorCode);
        }
        if (!isLegalStatus(linkDevice, linkStatus)) {
            LogUtil.info(TAG, "onCallbackStatusChanged isLegalStatus false, return.");
            return;
        }
        LogUtil.info(TAG, "onCallbackStatusChanged isWireLessLink=" + linkDevice.isWireLessLink());
        if (linkDevice != null && linkDevice.isWireLessLink() && linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING) {
            if (this.mAapWifiManager != null) {
                LogUtil.info(TAG, "wlan_switch_disable");
                this.mAapWifiManager.disableWlan();
            } else {
                LogUtil.warning(TAG, "onCallbackStatusChanged mAapWifiManager is null.");
            }
        }
        if (errorCode == null) {
            errorCode = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
        }
        if (linkDevice != null && linkStatus != null) {
            linkDevice.setStatus(linkStatus.ordinal());
        }
        if ((linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR) && linkDevice != null && linkDevice.isWireLessLink()) {
            removeMessage(1);
        }
        if (linkStatus != null && linkDevice != null) {
            handleStatusChange(linkDevice, linkStatus, errorCode, errorMessage);
            handleNotifyCallback(linkDevice, linkStatus, errorCode, errorMessage);
        }
    }

    private void handleStatusChange(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
        LogUtil.debug(TAG, "Link status of HMI callback is " + linkStatus);
        int i = AnonymousClass10.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[linkStatus.ordinal()];
        if (i != 3 && i != 4) {
            if (i == 5) {
                updateDeviceStatus(linkDevice, linkStatus, null);
                ResourceConst.LastVideoMode lastVideoMode = getLastVideoMode();
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Show last video mode: ");
                sb.append(lastVideoMode);
                sb.append(" connect mode: ");
                sb.append(linkDevice != null ? Integer.valueOf(linkDevice.getConnMode()) : "null");
                LogUtil.debug(str, sb.toString());
                if (linkDevice != null) {
                    showLastVideo(linkDevice);
                }
                if (linkDevice != null) {
                    linkDevice.setConnMode(AndroidAutoConst.ConnMode.MODE_DEFAULT_CONN.ordinal());
                    linkDevice.setErrorCode(errorCode.ordinal());
                }
                if (linkDevice != null && linkDevice.isWireLessLink() && linkDevice.getWifiAddr() != null) {
                    notifyMergeDevice(linkDevice.getDeviceId(), 3, linkDevice.getWifiAddr());
                    return;
                }
                return;
            }
            if (i == 6) {
                updateDeviceStatus(linkDevice, linkStatus, null);
                if (linkDevice != null && linkDevice.isWireLessLink() && linkDevice.getWifiAddr() != null) {
                    notifyMergeDevice(linkDevice.getDeviceId(), 3, linkDevice.getWifiAddr());
                    return;
                }
                return;
            }
            if (i != 7) {
                updateDeviceStatus(linkDevice, linkStatus, null);
                return;
            }
        }
        LogUtil.info(TAG, "LINK_STATUS_DEACTIVATED");
        notifyVoiceSessionNotification(1);
        notifyNavigationStatusChanged(0);
        notifyProjectionBluetoothAddressChanged(null);
        if (linkDevice != null && linkDevice.isWireLessLink()) {
            LogUtil.info(TAG, "cancel AA rfcomm,  mIsNeedConnectWirelessDevice:" + this.mIsNeedConnectWirelessDevice);
            if (this.mIsNeedConnectWirelessDevice) {
                this.mIsNeedConnectWirelessDevice = false;
            }
        }
        updateDeviceStatus(linkDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED, null);
        if (linkDevice != null) {
            linkDevice.setErrorCode(errorCode.ordinal());
        }
        notifyAutoConnectStatusChanged(linkDevice, AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED, errorCode);
        this.mTargetDevice = null;
    }

    private void handleNotifyCallback(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
        if (linkDevice.isAutoConnect() && (linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR)) {
            if (this.mIsFirstAutoConnectError) {
                notifyStatusChanged(linkDevice, linkStatus, errorCode, errorMessage);
                this.mIsFirstAutoConnectError = false;
                return;
            } else {
                LogUtil.info(TAG, "Not show error popup in autoconnect mode");
                return;
            }
        }
        if (linkDevice.isWireLink() && ((linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR) && errorCode == AndroidAutoConst.LinkErrorCode.ERROR_PING_TIMEOUT)) {
            notifyStatusChanged(linkDevice, linkStatus, AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE, errorMessage);
        } else if (linkStatus != AndroidAutoConst.LinkStatus.LINK_STATUS_AVAILABLE) {
            notifyStatusChanged(linkDevice, linkStatus, errorCode, errorMessage);
        }
    }

    private void showLastVideo(AndroidAutoDevice device) {
        if (device != null) {
            boolean autoConnect = device.isAutoConnect();
            boolean isWirelessConnect = device.isWireLessLink();
            LogUtil.info(TAG, "showLastVideo auto connect: " + autoConnect + " curr wireless:" + isWirelessConnect);
            if (this.mIsFrxConnectAap) {
                ResourceConst.VideoFocusReqResult result = requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                LogUtil.info(TAG, "First connect Aap show Hmi： " + result);
                return;
            }
            if (isLastVideoMode(device)) {
                ResourceConst.VideoFocusReqResult result2 = requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                LogUtil.info(TAG, "Show Last Video mode： " + result2);
                return;
            }
            if (this.mIsNeedShowProjectionOnPhoneCall && isPhoneCallActive(device)) {
                ResourceConst.VideoFocusReqResult result3 = requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                LogUtil.info(TAG, "need to showprojection when device phonecall is active：" + result3);
                return;
            }
            if (this.mIsForceProjection) {
                ResourceConst.VideoFocusReqResult result4 = requestVideoFocus(ResourceConst.VideoFocusRequest.VIDEOFOCUS_REQUEST_PROJECTED);
                LogUtil.info(TAG, "Show projection when force start projection: " + result4);
            }
        }
    }

    public void updateDeviceStatus(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, StatusConst.AoaStatus aoaStatus) {
        if (linkDevice != null) {
            if (linkStatus == null) {
                linkStatus = this.mDeviceManager.getDeviceMirrorStatus(linkDevice);
            }
            LogUtil.info(TAG, "updateDeviceStatus linkStatus:" + linkStatus + " aoaStatus:" + aoaStatus);
            this.mDeviceManager.updateDeviceStatus(linkDevice, linkStatus, aoaStatus);
            this.mDeviceManager.dumpDeviceList();
            if (getTargetDevice(linkDevice) == null && linkDevice.getBtAddr() != null && linkDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
                notifyWirelessAvailable(linkDevice.getBtAddr(), false);
                notifyUpdateProjectionCapability(linkDevice.getBtAddr(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), false);
            }
        }
    }

    public void requestUi(int mode) throws RemoteException {
        AndroidAutoConst.HmiLastMode lastMode = AndroidAutoConst.HmiLastMode.values()[mode];
        LogUtil.info(TAG, "showMirrorHMI mode : " + lastMode);
        showMirrorHmi(lastMode);
        AndroidAutoDevice connectedDevice = getActivitedDeviceExceptIpod();
        if (connectedDevice != null) {
            LogUtil.info(TAG, "showMirrorHMI connectedDevice : " + connectedDevice);
            showProjectionScreen(true, this.mScreenWidth, this.mScreenHeight);
            return;
        }
        AndroidAutoDevice frxDevice = getFrxDevice();
        if (frxDevice != null) {
            LogUtil.info(TAG, "showMirrorHMI show Frx popup window: " + frxDevice);
        }
    }

    public int getCurrVideoFocusStatus() {
        AapController aapController = this.mAapController;
        if (aapController != null) {
            return aapController.getCurrVideoFocusStatus();
        }
        return ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal();
    }

    public void setNativePhoneCallStatus(int status) {
        LogUtil.info(TAG, "setNativePhoneCallStatus status:" + status);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            aapController.onNativePhoneCallStatusChange(status);
        }
    }

    public void removeMessage(int what) {
        LogUtil.info(TAG, "removeMessage what:" + what);
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(what)) {
            this.mHandler.removeMessages(what);
        }
    }
}
