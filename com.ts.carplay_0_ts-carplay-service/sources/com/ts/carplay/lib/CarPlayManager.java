package com.ts.carplay.lib;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.carplay.adapter.CarPlayAdapterFactory;
import com.ts.carplay.adapter.audio.IAudioAdapter;
import com.ts.carplay.adapter.bluetooth.IBluetoothAdapter;
import com.ts.carplay.adapter.location.ILocationAdapter;
import com.ts.carplay.adapter.location.ILocationAdapterListener;
import com.ts.carplay.adapter.power.IPowerAdapter;
import com.ts.carplay.adapter.powerSTR.IPowerStrAdapter;
import com.ts.carplay.adapter.wifi.IWifiAdapter;
import com.ts.carplay.adapter.wifi.IWifiAdapterListener;
import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.ConnectRequest;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.common.data.config.ScreenInfo;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.bluetooth.BluetoothManager;
import com.ts.carplay.lib.bluetooth.BluetoothUuidHandler;
import com.ts.carplay.lib.bluetooth.BluetoothUuidListener;
import com.ts.carplay.lib.common.CarPlayEventListener;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.SurfaceData;
import com.ts.carplay.lib.connect.CarPlayConnect;
import com.ts.carplay.lib.connect.CarPlayIapSessionManager;
import com.ts.carplay.lib.connect.CarPlayReconnect;
import com.ts.carplay.lib.iap2manager.CarPlayLocationDataManager;
import com.ts.carplay.lib.iap2manager.CarPlayMediaDataManager;
import com.ts.carplay.lib.iap2manager.CarPlayOobManager;
import com.ts.carplay.lib.interfaces.ICarPlayCapabilityListener;
import com.ts.carplay.lib.interfaces.ICarPlayManager;
import com.ts.carplay.lib.interfaces.ICarPlayManagerListener;
import com.ts.carplay.lib.interfaces.ReconnectionDependence;
import com.ts.carplay.lib.network.NetworkManagement;
import com.ts.carplay.lib.resourcemanager.AudioResourceManager;
import com.ts.carplay.lib.resourcemanager.CarPlayLastUserMode;
import com.ts.carplay.lib.resourcemanager.CarPlayResourceManager;
import com.ts.carplay.lib.resourcemanager.ScreenResourceManager;
import com.ts.carplay.lib.ts.CarPlayStack;
import com.ts.carplay.lib.ts.CarPlayStackCmdHandler;
import com.ts.carplay.lib.wifi.CarPlayWifiManager;
import com.ts.carplay.linkadapter.ProprietaryAdapterFactory;
import com.ts.carplay.linkadapter.device.DeviceManager;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import com.ts.carplay.linkadapter.iap.IIapAdapter;
import com.ts.carplay.linkadapter.iap.IIapAdapterListener;
import com.ts.carplay.linkadapter.iap.data.AccessoryWiFiInfo;
import com.ts.carplay.linkadapter.iap.data.CarplayStartSessionInfo;
import com.ts.carplay.linkadapter.iap.data.IapConfig;
import com.ts.carplay.linkadapter.iap.data.IapSessionStatus;
import com.ts.carplay.linkadapter.iap.data.WiredAttributes;
import com.ts.carplay.linkadapter.iap.data.WirelessAttributes;
import com.ts.carplay.linkadapter.link.ConnectionManagerModel;
import com.ts.carplay.linkadapter.link.ILinkAdapter;
import com.ts.carplay.linkadapter.link.ILinkAdapterListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayManager implements ICarPlayManager, ReconnectionDependence, BluetoothUuidListener {
    private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    private static final int AP_DEVICE_CONNECT = 1;
    private static final int AP_DEVICE_DISCONNECT = 0;
    private static final int AUDIOSOURCECHANGE_LOSS = 0;
    public static final int CALLSTATUS_ALERT = 1;
    public static final int CALLSTATUS_INITIAL = -1;
    public static final int CALLSTATUS_SPEAKING = 2;
    public static final int CAP_CARPLAY_USB = 64;
    public static final int CAP_CARPLAY_WIRELESS = 512;
    public static final int CARPLAY_CARPBILITY_BT = 2;
    public static final int CARPLAY_CARPBILITY_USB = 3;
    public static final int CARPLAY_CARPBILITY_WIFI = 1;
    private static final String CARPLAY_NAME = "CarPlay Bluetooth RFCOMM Device";
    private static final int CP_KEYEVENT_DOWN = 1;
    private static final int CP_KEYEVENT_UP = 2;
    private static final int FAULT_TOLERANT_DELAY = 6000;
    private static final String IAP_NAME = "IAP Bluetooth RFCOMM Device";
    public static final String INTERFACE_ACTION_DOWN = "down";
    public static final String INTERFACE_ACTION_UP = "up";
    private static final int IPHONE_ATTACHED_OVER_USB_TIME_OUT = 3000;
    private static final int JUDGE_PROJECTION_IS_VALID = 3;
    public static final int KEYCODE_CP_ACBACK_PRESS = 14;
    public static final int KEYCODE_CP_ACBACK_RELEASE = 15;
    public static final int KEYCODE_CP_DEPRESS = 2;
    public static final int KEYCODE_CP_PAUSE_PRESS = 12;
    public static final int KEYCODE_CP_PAUSE_RELEASE = 13;
    public static final int KEYCODE_CP_PLAY_PRESS = 10;
    public static final int KEYCODE_CP_PLAY_RELEASE = 11;
    public static final int KEYCODE_CP_PRESS = 1;
    public static final int KEYCODE_CP_RELEASE = 3;
    public static final int KEYCODE_CP_SEEKDOWN_PRESS = 8;
    public static final int KEYCODE_CP_SEEKDOWN_RELEASE = 9;
    public static final int KEYCODE_CP_SEEKUP_PRESS = 6;
    public static final int KEYCODE_CP_SEEKUP_RELEASE = 7;
    public static final int KEYCODE_CP_TELLCALL_PRESS = 4;
    public static final int KEYCODE_CP_TELLCALL_RELEASE = 5;
    private static final String KEY_INTERFACE_ACTION = "vendor.ts.carplay.interface.action";
    private static final String KEY_INTERFACE_NAME = "vendor.ts.carplay.interface.name";
    private static final int MAP_GCJ_02 = 4;
    private static final int MAP_WGS_84 = 1;
    private static final int MSG_INIT_CARPLAY_STACK = 0;
    private static final int MSG_OBTAIN_CARPLAY_CONFIG_DONE = 1;
    private static final int MSG_OBTAIN_CARPLAY_CONFIG_TIMEOUT = 2;
    public static final String NEW_CONNECTION_USB_NAME = "usb0";
    private static final int OBTAIN_CARPLAY_CONFIG_TIMEOUT = 20000;
    public static final int RESOURCEREQ_NOK = 0;
    public static final int RESOURCEREQ_OK = 1;
    private static final String TAG = "CarPlayManager";
    private static final String UUID_CARPLAY = "ec884348-cd41-40a2-9727-575d50bf1fd3";
    private static final String UUID_IAP_ACCESSARY = "00000000-deca-fade-deca-deafdecacaff";
    private static final int VOICEMODE_DISABLE = -1;
    public static final int WAIT_DELETE_ROUTE_COMPLETE = 350;
    public static final int WAIT_FOR_DISCONENCTED_TIMEOUT = 10000;
    public static final int WAIT_SEND_OOB_COMPLETE = 1000;
    private static CarPlayConfig mCarPlayConfig;
    private static volatile CarPlayManager mInstance;
    private IAudioAdapter mAudioAdapter;
    private AudioResourceManager mAudioResourceManager;
    private IBluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;
    private BluetoothUuidHandler mBluetoothUuidHandler;
    private CpBroadcastReceiver mBroadcastReceiver;
    private final CarPlayConnect mCarPlayConnect;
    private CarPlayLocationDataManager mCarPlayLocationDataManager;
    private final CarPlayReconnect mCarPlayReconnect;
    private CarPlayResourceManager mCarPlayResourceManager;
    private final CarPlayStack mCarPlayStack;
    private final CarPlayStackCmdHandler mCarPlayStackCmdHandler;
    private Context mContext;
    private CarPlayManagerHandler mHandler;
    private HandlerThread mHandlerThread;
    private IIapAdapter mIapAdapter;
    private CarPlayIapSessionManager mIapSessionManager;
    private CarPlayEventListener mInnerEventHandler;
    private ILinkAdapter mLinkAdapter;
    private LinkAdapterListenerImpl mLinkAdapterListenerImpl;
    private LinkDevice mLinkCapdevice;
    protected LinkDevice mLinkDevice;
    private LinkDevice mLinkMergeDevice;
    private ILocationAdapter mLocationAdapter;
    private CarPlayMediaDataManager mMediaDataManager;
    private CarPlayOobManager mOobManager;
    private IPowerAdapter mPowerAdapter;
    private IPowerStrAdapter mPowerStrAdapter;
    private ScreenResourceManager mScreenResourceManager;
    private CarplayStartSessionInfo mStartSessionInfo;
    private IWifiAdapter mWifiAdapter;
    private CarPlayWifiManager mWifiManager;
    private static String THREAD_NAME = "CpMgrHandlerThread";
    private static boolean mIsInitLinkAdapter = false;
    private ICarPlayManagerListener mCarPlayManagerListener = null;
    private Lock mLock = new ReentrantLock();
    private Object mWaitForDisconnectedLock = new Object();
    private Object mWaitStopOob = new Object();
    private Lock mProjectionLock = new ReentrantLock();
    private String mWifiInterface = "wlan0";
    private int mCurIapClientType = 153;
    private boolean mIsDeviceFirstConn = true;
    private boolean mIsConnectFromUser = false;
    private boolean mNativeNaviStatus = false;
    private boolean mNativePhoneStatus = false;
    private volatile boolean mIsCarPlayConnection = false;
    private volatile boolean mUserNewConnection = false;
    private volatile boolean mIsCarPlayOldConnection = false;
    private volatile boolean isiPhoneDetachedOverUsb = false;
    private int mCurDeviceStatus = 0;
    private int mCallStatus = -1;
    private boolean mIsSiriKeyDown = false;
    private boolean mIsTelKeyDown = false;
    private boolean mIsPreviousKeyDown = false;
    private boolean mIsNextKeyDown = false;
    private boolean mIsShowActivityStarted = false;
    private boolean mIsProjectionStarted = false;
    private String mWifiMacAddress = "";
    private boolean mIsBackCameraFlag = false;
    private boolean mNeedShowCpIcon = false;
    private int mNativeSpeechStatus = -1;
    private String mBtAddress = "";
    private boolean mIsFromUserConn = false;
    private boolean mIsBtA2dpConnected = false;
    private boolean mIsNeedRequestMedia = false;
    private boolean mHasAccOff = false;
    private int mCarPlayCapability = 3;
    private Map<String, ICarPlayCapabilityListener> deviceCarPlayCapabilityListeners = new ConcurrentHashMap();
    private LinkedList<Iap2CheckRequest> iap2CheckRequests = new LinkedList<>();
    private boolean mIsActivateEnhancedSiri = false;
    private NetworkManagement mNetworkManagement = NetworkManagement.getInstance();
    private String mDeviceId = "";
    private MyRunnable iPhoneAttachedRunnable = new MyRunnable();
    private boolean mIsConnectByUser = false;
    private ILocationAdapterListener mLocationAdapterListener = new ILocationAdapterListener() { // from class: com.ts.carplay.lib.CarPlayManager.1
        @Override // com.ts.carplay.adapter.location.ILocationAdapterListener
        public void onVehicleSpeedChanged(float vehicleSpeed) {
            if (CarPlayManager.this.mCarPlayLocationDataManager != null) {
                CarPlayManager.this.mCarPlayLocationDataManager.setSpeed(vehicleSpeed);
            }
        }

        @Override // com.ts.carplay.adapter.location.ILocationAdapterListener
        public void onVehicleGearModeChanged(int vehicleGearMode) {
            if (CarPlayManager.this.mCarPlayLocationDataManager != null) {
                CarPlayManager.this.mCarPlayLocationDataManager.setTransmissionState(vehicleGearMode);
            }
        }

        @Override // com.ts.carplay.adapter.location.ILocationAdapterListener
        public void onVehicleGpggaUpdate(String data) {
            if (CarPlayManager.this.mCarPlayLocationDataManager != null) {
                CarPlayManager.this.mCarPlayLocationDataManager.setGpgga(data);
            }
        }

        @Override // com.ts.carplay.adapter.location.ILocationAdapterListener
        public void onVehicleGprmcUpdate(String data) {
            if (CarPlayManager.this.mCarPlayLocationDataManager != null) {
                CarPlayManager.this.mCarPlayLocationDataManager.setGprmc(data);
            }
        }
    };

    class MyRunnable implements Runnable {
        private CarPlayDevice device;
        private boolean supported;

        MyRunnable() {
        }

        public void setConnectDevice(CarPlayDevice device, boolean supported) {
            this.device = device;
            this.supported = supported;
            LogUtil.debug("CarPlayManager", "setParameter: device = " + device + ", supported = " + supported);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CarPlayManager.this.isiPhoneDetachedOverUsb) {
                CarPlayManager.this.isiPhoneDetachedOverUsb = false;
                LogUtil.info("CarPlayManager", "iPhoneAttachedRunnable: iPhone is detached over Usb ");
                return;
            }
            if (CarPlayManager.this.mCarPlayConnect != null) {
                CarPlayManager.this.mCarPlayConnect.usbDeviceModeConnect(ConnectionManagerModel.toCarPlayDevice(this.device));
            }
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.oniPhoneAttachedOverUsb(this.device, this.supported);
            }
        }
    }

    public void setIsConnectByUser(boolean isConnectByUser) {
        LogUtil.info("CarPlayManager", "setIsConnectByUser:" + isConnectByUser);
        this.mIsConnectByUser = isConnectByUser;
    }

    public CarPlayManager() {
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new CarPlayManagerHandler(this.mHandlerThread.getLooper());
        this.mInnerEventHandler = new CarPlayEventListener(this);
        this.mCarPlayStack = new CarPlayStack(this);
        this.mCarPlayConnect = new CarPlayConnect(this);
        this.mCarPlayReconnect = new CarPlayReconnect(this);
        this.mCarPlayStackCmdHandler = new CarPlayStackCmdHandler(this.mCarPlayStack);
        this.mOobManager = new CarPlayOobManager(this, this.mHandler.getLooper());
        this.mBluetoothManager = new BluetoothManager();
        this.mMediaDataManager = new CarPlayMediaDataManager(this);
        this.mCarPlayLocationDataManager = new CarPlayLocationDataManager(this);
        this.mIapSessionManager = new CarPlayIapSessionManager(this, this.mCarPlayConnect);
        this.mStartSessionInfo = new CarplayStartSessionInfo();
        this.mWifiManager = new CarPlayWifiManager();
    }

    public static CarPlayManager getInstance() {
        if (mInstance == null) {
            synchronized (CarPlayManager.class) {
                if (mInstance == null) {
                    mInstance = new CarPlayManager();
                }
            }
        }
        return mInstance;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void init(Context context) {
        this.mContext = context;
        resetRoute();
        if (this.mContext != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_SHUTDOWN);
            CpBroadcastReceiver cpBroadcastReceiver = new CpBroadcastReceiver();
            this.mBroadcastReceiver = cpBroadcastReceiver;
            this.mContext.registerReceiver(cpBroadcastReceiver, filter, null, this.mHandler);
        }
        this.mBluetoothManager.registerListener(new BluetoothManagerListenerImpl());
        this.mBluetoothManager.init(context);
        this.mCarPlayReconnect.init(context);
        this.mCarPlayLocationDataManager.init(context);
        CarPlayLastUserMode.getInstance().initLastMode(context);
        this.mWifiManager.init(context, this);
        this.mCarPlayResourceManager = CarPlayResourceManager.getInstance();
        CarPlayIapSessionManager.getInstance().init(context);
        initRmManager();
        initAdapter(this.mContext);
        BluetoothUuidHandler bluetoothUuidHandler = new BluetoothUuidHandler(context, this.mBluetoothManager);
        this.mBluetoothUuidHandler = bluetoothUuidHandler;
        bluetoothUuidHandler.setListener(this);
        onStartRfCommServer(IAP_NAME, UUID_IAP_ACCESSARY);
        onStartRfCommServer(CARPLAY_NAME, UUID_CARPLAY);
    }

    private void resetRoute() {
        String interfaceName = SystemProperties.get(KEY_INTERFACE_NAME);
        String interfaceAction = SystemProperties.get(KEY_INTERFACE_ACTION);
        if (interfaceAction.equals(INTERFACE_ACTION_UP) && !TextUtils.isEmpty(interfaceName)) {
            setInterfaceRoute(INTERFACE_ACTION_DOWN);
            try {
                try {
                    Thread.sleep(350L);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            } finally {
                LogUtil.debug("CarPlayManager", "sleep complete after reset route.");
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void uninit() {
    }

    private class CarPlayManagerHandler extends Handler {
        public CarPlayManagerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                LogUtil.info("CarPlayManager", "MSG_OBTAIN_CARPLAY_CONFIG_DONE.");
                CarPlayManager.this.mHandler.removeMessages(2);
                CarPlayManager.this.initLinkAdapter();
            } else if (i == 2) {
                LogUtil.warning("CarPlayManager", "MSG_OBTAIN_CARPLAY_CONFIG_TIMEOUT.");
                CarPlayManager.this.initLinkAdapter();
            } else if (i == 3) {
                CarPlayManager.this.mProjectionLock.lock();
                if (CarPlayManager.this.mIsShowActivityStarted && !CarPlayManager.this.mIsProjectionStarted) {
                    LogUtil.error("CarPlayManager", "DisplayActivity starts, but projection is stop.");
                    CarPlayManager.this.requestVideoFocusChange(1);
                }
                CarPlayManager.this.mProjectionLock.unlock();
            }
        }
    }

    public void removeJudgeProjectionMsg() {
        CarPlayManagerHandler carPlayManagerHandler = this.mHandler;
        if (carPlayManagerHandler != null) {
            carPlayManagerHandler.removeMessages(3);
            LogUtil.info("CarPlayManager", "remove JUDGE_PROJECTION_IS_VALID message");
        }
    }

    public void projectionStateChange(boolean flg) {
        LogUtil.info("CarPlayManager", "projectionStateChange, flg: " + flg);
        this.mProjectionLock.lock();
        this.mIsProjectionStarted = flg;
        this.mProjectionLock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initLinkAdapter() {
        if (!mIsInitLinkAdapter) {
            CarPlayStack.getInstance().startCarPlayService();
            ILinkAdapter linkAdapter = ProprietaryAdapterFactory.getLinkAdapter();
            this.mLinkAdapter = linkAdapter;
            if (linkAdapter != null) {
                LogUtil.info("CarPlayManager", "initLinkAdapter CarPlay begin to register dcm.");
                ILinkAdapter iLinkAdapter = this.mLinkAdapter;
                LinkAdapterListenerImpl linkAdapterListenerImpl = new LinkAdapterListenerImpl();
                this.mLinkAdapterListenerImpl = linkAdapterListenerImpl;
                iLinkAdapter.registerListener(linkAdapterListenerImpl);
                this.mLinkAdapter.init(this.mContext);
            } else {
                LogUtil.warning("CarPlayManager", "initLinkAdapter mLinkAdapter is null.");
            }
            mIsInitLinkAdapter = true;
            return;
        }
        LogUtil.info("CarPlayManager", "initLinkAdapter no need to init.");
    }

    private void initAdapter(Context context) {
        IIapAdapter iapAdapter = ProprietaryAdapterFactory.getIapAdapter();
        this.mIapAdapter = iapAdapter;
        if (iapAdapter != null) {
            iapAdapter.registerListener(new IapAdapterListenerImpl());
            this.mIapAdapter.init(context);
        }
        IAudioAdapter audioAdapter = CarPlayAdapterFactory.getAudioAdapter();
        this.mAudioAdapter = audioAdapter;
        if (audioAdapter != null) {
            audioAdapter.registerListener(new AudioAdapterListenerImpl());
            this.mAudioAdapter.init(context);
        }
        IPowerAdapter powerAdapter = CarPlayAdapterFactory.getPowerAdapter();
        this.mPowerAdapter = powerAdapter;
        if (powerAdapter != null) {
            powerAdapter.registerListener(new PowerAdapterListenerImpl());
            this.mPowerAdapter.init(context);
        }
        IPowerStrAdapter powerStrAdapter = CarPlayAdapterFactory.getPowerStrAdapter();
        this.mPowerStrAdapter = powerStrAdapter;
        if (powerStrAdapter != null) {
            powerStrAdapter.registerListener(new PowerStrAdapterListenerImpl());
            this.mPowerStrAdapter.init(context);
        }
        IBluetoothAdapter bluetoothAdapter = CarPlayAdapterFactory.getBluetoothAdapter();
        this.mBluetoothAdapter = bluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.init(context);
        }
        ILocationAdapter locationAdapter = CarPlayAdapterFactory.getLocationAdapter();
        this.mLocationAdapter = locationAdapter;
        if (locationAdapter != null) {
            locationAdapter.init(context);
        }
        IWifiAdapter wifiAdapter = CarPlayAdapterFactory.getWifiAdapter();
        this.mWifiAdapter = wifiAdapter;
        if (wifiAdapter != null) {
            wifiAdapter.registerListener(new WifiAdapterListenerImpl());
            this.mWifiAdapter.init(context);
        }
    }

    public boolean doUsbRoleSwitch(LinkDevice device) {
        LogUtil.info("CarPlayManager", "doUsbRoleSwitch " + device);
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            return iLinkAdapter.doUsbRoleSwitch(device, 1);
        }
        return false;
    }

    public boolean doUsbRoleSwitchForIap(LinkDevice device) {
        LogUtil.info("CarPlayManager", "doUsbRoleSwitchForIap " + device);
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            return iLinkAdapter.doUsbRoleSwitch(device, 3);
        }
        return false;
    }

    public void notifyIap2Result(int transportMode, int result) {
        LogUtil.info("CarPlayManager", "notifyIap2Result");
        this.mCarPlayConnect.iap2AuthResult(transportMode, result);
    }

    public void setDeviceBtOrUsbAddr(String btMac, String usbAddr) {
        LogUtil.debug("CarPlayManager", "setDeviceBtOrUsbAddr, btMac=" + LogEncrypt.MacAddressEncrypt(btMac) + ", usbAddr=" + LogEncrypt.MacAddressEncrypt(usbAddr));
        LinkDevice linkDevice = this.mLinkMergeDevice;
        if (linkDevice != null) {
            linkDevice.setBtAddr(btMac);
            this.mLinkMergeDevice.setUsbSerialNumber(usbAddr);
        }
    }

    public void addCpDataListenerByCommon() {
        LogUtil.info("CarPlayManager", "addCpDataListenerByCommon");
    }

    public void removeCpDataListenerByCommon() {
        LogUtil.info("CarPlayManager", "removeCpDataListenerByCommon");
    }

    public void addCpDrLocListener() {
        LogUtil.info("CarPlayManager", "addCpDrLocListener!");
    }

    public void removeCpDrLocListener() {
        LogUtil.info("CarPlayManager", "removeCpDrLocListener!");
    }

    public void setCurrentCallSts(int status) {
        LogUtil.info("CarPlayManager", "setCurrentCallSts, status = " + status);
        this.mCallStatus = status;
    }

    public int getCurrentCallSts() {
        LogUtil.info("CarPlayManager", "getCurrentCallSts, mCallStatus= " + this.mCallStatus);
        return this.mCallStatus;
    }

    public int getCurNativeSpeechSts() {
        LogUtil.info("CarPlayManager", "getCurrentNativeVrSts, mNativeSpeechStatus= " + this.mNativeSpeechStatus);
        return this.mNativeSpeechStatus;
    }

    public boolean isDuringBtPhone(String mac) {
        if (mac != null && this.mBluetoothManager != null) {
            LogUtil.debug("CarPlayManager", "getBtPhoneActive, btmac = " + LogEncrypt.MacAddressEncrypt(mac));
            return this.mBluetoothManager.isDuringBtPhone(mac);
        }
        return false;
    }

    public void clearNeedSendUnborrow() {
        this.mAudioResourceManager.clearNeedSendUnborrow();
    }

    public void setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) {
        LogUtil.info("CarPlayManager", "setBtOobInfo");
        IBluetoothAdapter iBluetoothAdapter = this.mBluetoothAdapter;
        if (iBluetoothAdapter == null) {
            LogUtil.warning("CarPlayManager", "setBtOobInfo mBluetoothManagerListener is null");
        } else {
            iBluetoothAdapter.setBtOobInfo(name, linkKey, mac, linkKeyType);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public String getBluetoothMacAddress() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            if (bluetoothManager.isBluetoothEnabled()) {
                return this.mBluetoothManager.getBluetoothMacAddress();
            }
            return null;
        }
        LogUtil.warning("CarPlayManager", "getBluetoothMacAddress mBluetoothManagerListener is null");
        return null;
    }

    public int getBluetoothDeviceClass() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            return bluetoothManager.getBluetoothDeviceClass();
        }
        LogUtil.warning("CarPlayManager", "getBluetoothDeviceClass mBluetoothManagerListener is null");
        return -1;
    }

    public boolean isSameBtMediaActive() {
        IAudioAdapter iAudioAdapter = this.mAudioAdapter;
        boolean z = false;
        if (iAudioAdapter == null) {
            return false;
        }
        if (this.mIsBtA2dpConnected && iAudioAdapter.isBtMediaAudioFocus()) {
            z = true;
        }
        boolean result = z;
        return result;
    }

    public void disableBluetooth(byte[] btAddr) {
        BluetoothManager bluetoothManager;
        LogUtil.info("CarPlayManager", "disableBluetooth");
        String btaddr = new String(btAddr).toUpperCase(Locale.US);
        this.mBtAddress = btaddr;
        if (isDuringBtPhone(btaddr)) {
            requestAudioFocus(3);
            CarPlayLastUserMode carPlayLastUserMode = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode.setLastUserMode(carPlayResource, 1);
        }
        LinkDevice linkDevice = this.mLinkDevice;
        if (linkDevice != null && (bluetoothManager = this.mBluetoothManager) != null) {
            bluetoothManager.disableBluetooth(btaddr);
            this.mBluetoothManager.setBluetoothCoexistence(btaddr, true);
            this.mCarPlayConnect.disableBluetooth();
        }
    }

    public boolean getDrivingMediaVolMuteStatus() {
        return true;
    }

    public int show(SurfaceData surfaceData) {
        if (this.mCarPlayConnect.isConnected()) {
            if (this.mCarPlayStack == null) {
                LogUtil.error("CarPlayManager", "show: error,mCarPlayStack is null");
                return -1;
            }
            if (surfaceData.getScreenType() == 0 && !this.mCarPlayResourceManager.isScreenShowAllow()) {
                this.mCarPlayStack.carPlaySaveSurface(surfaceData);
                return -1;
            }
            this.mCarPlayStack.carPlayShow(surfaceData);
            return 0;
        }
        LogUtil.info("CarPlayManager", "show: carplay is not connected");
        return 0;
    }

    public void setCpShowPopup() {
        LogUtil.info("CarPlayManager", "setCpShowPopup");
    }

    public boolean getInBackcameraFlag() {
        LogUtil.info("CarPlayManager", "getInBackcameraFlag, mIsBackCameraFlag= " + this.mIsBackCameraFlag);
        return this.mIsBackCameraFlag;
    }

    public void setNeedShowCpIconSts(boolean status) {
        LogUtil.info("CarPlayManager", "setNeedSendCpIconSts, status= " + status);
        this.mNeedShowCpIcon = status;
    }

    public boolean getNeedShowCpIconSts() {
        LogUtil.info("CarPlayManager", "getNeedShowCpIconSts, mNeedShowCpIcon= " + this.mNeedShowCpIcon);
        return this.mNeedShowCpIcon;
    }

    public boolean getNativeNaviStatus() {
        return this.mNativeNaviStatus;
    }

    public boolean getNativePhoneStatus() {
        return this.mNativePhoneStatus;
    }

    private void checkCarPlayDeviceStatus(String mac) {
        LinkDevice currentCarPlayDevice = this.mLinkAdapter.getCarPlayDevice(mac);
        LinkDevice preCarPlayDevice = DeviceManager.getInstance().getDevice();
        if (currentCarPlayDevice == null) {
            LogUtil.info("CarPlayManager", "notifyCarPlayCapbility currentCarPlayDevice = null");
        }
        if (preCarPlayDevice == null) {
            LogUtil.info("CarPlayManager", "notifyCarPlayCapbility preCarPlayDevice = null");
        }
        if (currentCarPlayDevice != null && preCarPlayDevice != null) {
            LogUtil.debug("CarPlayManager", "currentCarPlayDevice: " + currentCarPlayDevice.toString() + ", preCarPlayDevice = " + preCarPlayDevice.toString());
            if (!TextUtils.isEmpty(preCarPlayDevice.getBtAddr()) && preCarPlayDevice.getBtAddr().equals(mac) && preCarPlayDevice.getStatus() == 1) {
                preCarPlayDevice.setStatus(4);
                DeviceManager.getInstance().saveDevice(preCarPlayDevice);
            }
            LogUtil.debug("CarPlayManager", "preCarPlayDevice = " + preCarPlayDevice.toString());
        }
    }

    public void notifyCarPlayCapbility(String mac, boolean flag, int reason) {
        LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility mac: " + LogEncrypt.MacAddressEncrypt(mac) + ", flag = " + flag + ",reason:" + reason);
        if (!flag) {
            checkCarPlayDeviceStatus(mac);
        }
        notifyCarPlayCapbility(mac, flag, reason, 0);
    }

    public void notifyCarPlayCapbility(String mac, boolean flag, int reason, int requestReason) {
        LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility mac: " + LogEncrypt.MacAddressEncrypt(mac) + ", flag = " + flag + ",reason:" + reason + ",requestReason:" + requestReason);
        if (this.mLinkAdapter != null) {
            if (!flag) {
                checkCarPlayDeviceStatus(mac);
            }
            this.mLinkAdapter.wirelessCapabilityUpdate(mac, flag, reason);
        }
        ICarPlayCapabilityListener listener = this.deviceCarPlayCapabilityListeners.remove(mac);
        if (listener != null) {
            LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility listener callback: " + LogEncrypt.MacAddressEncrypt(mac));
            listener.onWirelessCarPlayAvailabilityChanged(mac, flag);
        } else {
            LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility listener is null: " + LogEncrypt.MacAddressEncrypt(mac));
        }
        synchronized (this) {
            if (this.iap2CheckRequests.isEmpty()) {
                LogUtil.info("CarPlayManager", "notifyCarPlayCapbility iap2CheckRequests is empty: ");
                return;
            }
            if (!mac.equalsIgnoreCase(this.iap2CheckRequests.removeFirst().mac)) {
                LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility mac is exception: " + LogEncrypt.MacAddressEncrypt(mac));
            }
            Iap2CheckRequest request = this.iap2CheckRequests.peekFirst();
            if (Objects.isNull(request)) {
                LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility Iap2CheckRequest is null :" + LogEncrypt.MacAddressEncrypt(mac));
            } else if (this.mLinkAdapter != null) {
                requestIap2CapabilityCheck0(this.mLinkAdapter.getCarPlayDevice(request.mac), request.reason);
            } else {
                LogUtil.debug("CarPlayManager", "notifyCarPlayCapbility mLinkAdapter is null :" + LogEncrypt.MacAddressEncrypt(mac));
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void checkWirelessCarPlayAvailability(String macAddress, ICarPlayCapabilityListener listener) {
        LogUtil.debug("CarPlayManager", "checkWirelessCarPlayAvailability " + macAddress);
        if (this.mBluetoothManager == null) {
            LogUtil.debug("CarPlayManager", "checkWirelessCarPlayAvailability mBluetoothManager is null : " + LogEncrypt.MacAddressEncrypt(macAddress));
            return;
        }
        if (listener == null) {
            LogUtil.debug("CarPlayManager", "checkWirelessCarPlayAvailability ICarPlayCapabilityListener is null : " + LogEncrypt.MacAddressEncrypt(macAddress));
            return;
        }
        if (this.deviceCarPlayCapabilityListeners.containsKey(macAddress)) {
            this.deviceCarPlayCapabilityListeners.put(macAddress, listener);
            LogUtil.debug("CarPlayManager", "checkWirelessCarPlayAvailability request exists : " + LogEncrypt.MacAddressEncrypt(macAddress));
            return;
        }
        ParcelUuid[] uuids = this.mBluetoothManager.getBluetoothDeviceUuids(macAddress);
        this.deviceCarPlayCapabilityListeners.put(macAddress, listener);
        if (uuids == null) {
            LogUtil.debug("CarPlayManager", "checkWirelessCarPlayAvailability wait for broadcasting action: " + LogEncrypt.MacAddressEncrypt(macAddress));
            return;
        }
        checkCarPlayCapability(uuids, macAddress, listener);
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void connectCarPlay(ConnectRequest request) {
        LogUtil.info("CarPlayManager", "connectCarPlay " + request);
        if (this.mLinkAdapter != null && request != null) {
            int type = request.getConnectType();
            if (3 == type || 2 == this.mLinkAdapter.getDeviceConnectionType(request.getDeviceId())) {
                this.mWifiManager.startSoftAp(null);
                CarPlayStack carPlayStack = this.mCarPlayStack;
                if (carPlayStack != null) {
                    carPlayStack.setWireConnection(false);
                }
            } else {
                CarPlayStack carPlayStack2 = this.mCarPlayStack;
                if (carPlayStack2 != null) {
                    carPlayStack2.setWireConnection(true);
                }
            }
            if (this.mCarPlayResourceManager != null && request.isUserRequest()) {
                this.mCarPlayResourceManager.setUserRequest(true);
            }
            this.mLinkAdapter.connectCarPlay(type, request.getDeviceId(), request.isUserRequest());
            return;
        }
        LogUtil.warning("CarPlayManager", "connectCarPlay mLinkAdapter is null.");
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void disconnectCarPlay(String devId) {
        LogUtil.debug("CarPlayManager", "disconnectCarPlay devId:" + LogEncrypt.MacAddressEncrypt(devId));
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            iLinkAdapter.disconnectCarPlay(devId);
        } else {
            LogUtil.warning("CarPlayManager", "disconnectCarPlay mLinkAdapter is null.");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void deleteCarPlayDevice(String devId) {
        LinkDevice device;
        LogUtil.debug("CarPlayManager", "deleteCarPlayDevice devId:" + LogEncrypt.MacAddressEncrypt(devId));
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            iLinkAdapter.deleteCarPlayDevice(devId);
            if (this.mOobManager != null && (device = this.mLinkAdapter.getCarPlayDevice(devId)) != null && TextUtils.isEmpty(device.getBtAddr())) {
                String macAddr = device.getBtAddr();
                this.mOobManager.sendBluetoothPairingStatus(macAddr, false, "device:" + LogEncrypt.MacAddressEncrypt(macAddr) + " has deleted");
                return;
            }
            return;
        }
        LogUtil.warning("CarPlayManager", "deleteCarPlayDevice mLinkAdapter is null.");
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public LinkDevice getCarPlayDevice(String devId) {
        LinkDevice device;
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null || (device = iLinkAdapter.getCarPlayDevice(devId)) == null) {
            return null;
        }
        return device;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public CarPlayDevice getActivedWirelessDevice() {
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null) {
            return null;
        }
        CarPlayDevice device = iLinkAdapter.getActivedWirelessDevice();
        return device;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public List<CarPlayDevice> getUsbCarPlayDeviceList() {
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            return iLinkAdapter.getUsbCarPlayDeviceList();
        }
        return null;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public List<CarPlayDevice> getCarPlayDeviceList() {
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            return iLinkAdapter.getCarPlayDeviceList();
        }
        return null;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public boolean cancelWirelessCarPlay(String mac) {
        if (TextUtils.isEmpty(mac)) {
            LogUtil.error("CarPlayManager", "cancelWirelessCarPlay, mac is empty");
            return false;
        }
        this.mLock.lock();
        boolean result = this.mCarPlayConnect.closeRfCommConnectionByMac(mac);
        this.mLock.unlock();
        LogUtil.error("CarPlayManager", "cancelWirelessCarPlay, result is " + result);
        return result;
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean closeRfCommConnection(String mac) {
        LogUtil.debug("CarPlayManager", "closeRfCommConnection, mac=" + LogEncrypt.MacAddressEncrypt(mac));
        return cancelWirelessCarPlay(mac);
    }

    private boolean checkAppleUuid(ParcelUuid[] uuids, String macAddress) {
        for (ParcelUuid puuid : uuids) {
            LogUtil.debug("CarPlayManager", "checkAppleUuid " + LogEncrypt.MacAddressEncrypt(macAddress) + ":" + LogEncrypt.MacAddressEncrypt(puuid.toString()));
            if (isAppleDevice(puuid.toString())) {
                LogUtil.debug("CarPlayManager", "checkAppleUuid isAppleDevice" + LogEncrypt.MacAddressEncrypt(macAddress) + ":" + LogEncrypt.MacAddressEncrypt(puuid.toString()));
                return true;
            }
        }
        return false;
    }

    private void checkCarPlayCapability(ParcelUuid[] uuids, String macAddress, ICarPlayCapabilityListener listener) {
        LogUtil.debug("CarPlayManager", "checkCarPlayCapability " + LogEncrypt.MacAddressEncrypt(macAddress) + " " + uuids);
        if (uuids == null) {
            if (listener != null) {
                listener.onWirelessCarPlayAvailabilityChanged(macAddress, false);
                this.deviceCarPlayCapabilityListeners.remove(macAddress);
                return;
            }
            return;
        }
        checkCarPlayCapability0(uuids, macAddress, listener);
    }

    private void checkCarPlayCapability0(ParcelUuid[] uuids, String macAddress, ICarPlayCapabilityListener listener) {
        LogUtil.debug("CarPlayManager", "checkCarPlayCapability0 " + LogEncrypt.MacAddressEncrypt(macAddress));
        boolean isApple = checkAppleUuid(uuids, macAddress);
        if (isApple) {
            ILinkAdapter iLinkAdapter = this.mLinkAdapter;
            if (iLinkAdapter != null) {
                requestIap2CapabilityCheck(iLinkAdapter.getCarPlayDevice(macAddress), 1);
                return;
            }
            return;
        }
        if (listener != null) {
            listener.onWirelessCarPlayAvailabilityChanged(macAddress, false);
            this.deviceCarPlayCapabilityListeners.remove(macAddress);
        }
    }

    private boolean isSupportIap2(String uuid) {
        return "02030302-1d19-415f-86f2-22a2106a0a77".equalsIgnoreCase(uuid);
    }

    private boolean isSupportIap1(String uuid) {
        return "00000000-deca-fade-deca-deafdecacafe".equalsIgnoreCase(uuid);
    }

    public static boolean isAppleDevice(String uuid) {
        return "00000000-deca-fade-deca-deafdecacafe".equalsIgnoreCase(uuid) || "02030302-1d19-415f-86f2-22a2106a0a77".equalsIgnoreCase(uuid);
    }

    public void setOobPairName(String devName) {
        CarPlayOobManager carPlayOobManager = this.mOobManager;
        if (carPlayOobManager != null) {
            carPlayOobManager.setOobPairName(devName);
        }
    }

    public int getLastAudioFocusPriority() {
        return -1;
    }

    public int getLastAudioFocusId() {
        return -1;
    }

    public int requestVideoFocus() {
        LogUtil.debug("CarPlayManager", "requestVideoFocus mIsShowActivityStarted: " + this.mIsShowActivityStarted);
        requestVideoFocusChange(0);
        return 1;
    }

    public int releaseScreen() {
        LogUtil.debug("CarPlayManager", "releaseScreen");
        requestVideoFocusChange(1);
        return 0;
    }

    public int releaseScreenAndToHome() {
        LogUtil.error("CarPlayManager", "releaseScreenAndToHome");
        CarPlayResourceManager.getInstance().forceChangeToTake();
        requestVideoFocusChange(2);
        return 0;
    }

    public boolean requestAudioFocus(int requestType) {
        boolean ret = false;
        IAudioAdapter iAudioAdapter = this.mAudioAdapter;
        if (iAudioAdapter != null) {
            if (requestType == 0 && iAudioAdapter.isBtMediaAudioFocus() && this.mIsBtA2dpConnected) {
                LogUtil.warning("CarPlayManager", "requestAudioFocus cannot interrupt BtMedia when BT connected.");
                this.mIsNeedRequestMedia = true;
                return false;
            }
            ret = this.mAudioAdapter.requestAudioFocus(requestType);
        }
        LogUtil.error("CarPlayManager", "requestAudioFocus requestType: " + requestType + "  ret: " + ret);
        return ret;
    }

    public boolean abandonAudioFocus(int requestType) {
        boolean ret = false;
        IAudioAdapter iAudioAdapter = this.mAudioAdapter;
        if (iAudioAdapter != null) {
            ret = iAudioAdapter.abandonAudioFocus(requestType);
        }
        LogUtil.error("CarPlayManager", "abandonAudioFocus requestType: " + requestType + "  ret: " + ret);
        return ret;
    }

    public void onCarPlayMediaFocusChange(boolean isCarPlayMedia) {
        LogUtil.info("CarPlayManager", "onCarPlayMediaFocusChange isCarPlayMedia: " + isCarPlayMedia);
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onCarPlayMediaFocusState(isCarPlayMedia);
        }
    }

    public void onCarPlayAppStateChanged(int appState, boolean enable, int speechMode) {
        LogUtil.info("CarPlayManager", "onCarPlayAppStateChanged appState: " + appState + " enable: " + enable + " speechMode:" + speechMode);
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onCarPlayAppStateChanged(appState, enable, speechMode);
        }
    }

    public void onCarPlayAppStateChanged(int appState, int entity, int speechMode) {
        LogUtil.info("CarPlayManager", "onCarPlayAppStateChanged appState: " + appState + " entity: " + entity + " speechMode:" + speechMode + " isActivateEnhancedSiri:" + this.mIsActivateEnhancedSiri);
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onCarPlayAppStateChanged(appState, entity, speechMode);
        }
        if (this.mIsActivateEnhancedSiri) {
            if (appState == 0 || 2 == appState) {
                controlAuxIn();
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void updateViewArea(String uuid, int index, int[] adjacentViewAreas, int animationDurationMillis) {
        LogUtil.error("CarPlayManager", "updateViewArea index：" + index + ",uuid" + LogEncrypt.MacAddressEncrypt(uuid) + ",array:" + adjacentViewAreas.toString() + ",animationDurationMillis:" + animationDurationMillis);
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.updateViewArea(uuid, index, adjacentViewAreas, animationDurationMillis);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void onRequestViewArea(int index) {
        LogUtil.error("CarPlayManager", "onRequestViewArea index：" + index);
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onRequestViewArea(index);
        }
    }

    public void onCarPlayDisplayStatusChanged(boolean isShow) {
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onCarPlayDisplayStatusChanged(isShow);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public boolean getCarPlayDisPlayStatus() {
        boolean isShown = false;
        int screenEntity = this.mScreenResourceManager.getEntity();
        if (screenEntity == 1) {
            isShown = true;
        }
        LogUtil.info("CarPlayManager", "getCarPlayDisPlayStatus isShown: " + isShown);
        return isShown;
    }

    public void bluetoothProfileStateChanged(String address, int profile, int newState, int oldState) {
        if (this.mLinkMergeDevice != null) {
            LogUtil.debug("CarPlayManager", "bluetoothProfileStateChanged address:" + LogEncrypt.MacAddressEncrypt(address) + " profile:" + profile + " newState:" + newState + " oldState:" + oldState + ", btaddr:" + LogEncrypt.MacAddressEncrypt(this.mLinkMergeDevice.getBtAddr()));
            if (profile == 0 && newState == 0) {
                address.equals(this.mLinkMergeDevice.getBtAddr());
            }
        }
    }

    public boolean onStartRfCommServer(String name, String uuid) {
        LogUtil.info("CarPlayManager", "onStartRfCommServer" + name + "uuid" + LogEncrypt.MacAddressEncrypt(uuid));
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.startRfCommServer(name, uuid);
            return false;
        }
        return false;
    }

    public boolean onStopRfCommServer(String uuid) {
        LogUtil.info("CarPlayManager", "onStopRfCommServer" + LogEncrypt.MacAddressEncrypt(uuid));
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.stopRfCommServer(uuid);
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void btStateChangedTo(int state) {
        LogUtil.info("CarPlayManager", "btStateChangedTo state:" + state);
        if (state == 12) {
            onStartRfCommServer(IAP_NAME, UUID_IAP_ACCESSARY);
            onStartRfCommServer(CARPLAY_NAME, UUID_CARPLAY);
            CarPlayOobManager carPlayOobManager = this.mOobManager;
            if (carPlayOobManager != null) {
                carPlayOobManager.handleBluetoothEnabled(true);
            }
            if (this.mCarPlayConnect.isConnected() && this.mLinkDevice != null) {
                LogUtil.info("CarPlayManager", "btStateChangedTo, carplay is connected");
                return;
            }
            CarPlayReconnect carPlayReconnect = this.mCarPlayReconnect;
            if (carPlayReconnect != null) {
                carPlayReconnect.onBtStateChanged(state == 12);
                return;
            }
            return;
        }
        if (state == 10) {
            onStopRfCommServer(UUID_IAP_ACCESSARY);
            onStopRfCommServer(UUID_CARPLAY);
            this.mCarPlayConnect.closeRfCommConnection();
            CarPlayOobManager carPlayOobManager2 = this.mOobManager;
            if (carPlayOobManager2 != null) {
                carPlayOobManager2.handleBluetoothEnabled(false);
            }
        }
    }

    public void peerConnected(String uuid, int handle, String address) {
        String str;
        String str2;
        LogUtil.debug("CarPlayManager", "peerConnected uuid:" + LogEncrypt.MacAddressEncrypt(uuid) + " handle:" + handle + " address:" + LogEncrypt.MacAddressEncrypt(address));
        if (uuid == null || !UUID_IAP_ACCESSARY.equals(uuid)) {
            return;
        }
        CarPlayOobManager carPlayOobManager = this.mOobManager;
        if (carPlayOobManager != null) {
            carPlayOobManager.sendBluetoothPairingStatus(address, true, "device:" + LogEncrypt.MacAddressEncrypt(address) + " pair success");
        }
        synchronized (this.mWaitStopOob) {
            try {
                try {
                    this.mWaitStopOob.wait(1000L);
                    str = "CarPlayManager";
                    str2 = "wait complete after sendBluetoothPairingStatus.";
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    str = "CarPlayManager";
                    str2 = "wait complete after sendBluetoothPairingStatus.";
                }
                LogUtil.debug(str, str2);
            } catch (Throwable th) {
                LogUtil.debug("CarPlayManager", "wait complete after sendBluetoothPairingStatus.");
                throw th;
            }
        }
        if (-1 != handle) {
            closeConnection(handle);
        }
        Intent intent = new Intent("com.ts.carplay.action.OOB_BONDED_FOR_BT");
        intent.putExtra("from", "CarPlay");
        intent.putExtra("btAddr", address);
        Context context = this.mContext;
        if (context != null) {
            context.sendBroadcast(intent);
        }
    }

    public int connectToServer(String address, String uuid) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            return -1;
        }
        int ret = bluetoothManager.connectToServer(address, uuid);
        return ret;
    }

    public boolean closeConnection(int handle) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            return false;
        }
        boolean ret = bluetoothManager.closeConnection(handle);
        return ret;
    }

    public InputStream getInputStream(int handle) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            return null;
        }
        InputStream stream = bluetoothManager.getInputStream(handle);
        return stream;
    }

    public OutputStream getOutputStream(int handle) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            return null;
        }
        OutputStream stream = bluetoothManager.getOutputStream(handle);
        return stream;
    }

    public void onRfCommDisconnected(String rfcommMac) {
        LogUtil.debug("CarPlayManager", "onRfCommDisconnected rfcommMac: " + LogEncrypt.MacAddressEncrypt(rfcommMac));
        this.mCarPlayConnect.onRfCommDisconnected(rfcommMac);
    }

    public void onKnownWirelessAttributesChanged(String mac, int state) {
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            iLinkAdapter.onKnownWirelessAttributesChanged(mac, state);
        }
    }

    public void mergeDeviceForUsb(String btDeviceId, String btMac, String usbSerilNum) {
        LogUtil.info("CarPlayManager", "mergeDeviceForUsb, merge bt mac!");
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            iLinkAdapter.mergeDevices(btDeviceId, 2, btMac);
        }
    }

    public void mergeDeviceForWiFi(String btDeviceId, String btMac, String usbSerilNum) {
        LogUtil.info("CarPlayManager", "mergeDeviceForWiFi, merge USB serial number!");
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            iLinkAdapter.mergeDevices(btDeviceId, 1, usbSerilNum);
        }
    }

    public boolean onDeviceFriendlyNameUpdate(String devId, String name) {
        LogUtil.info("CarPlayManager", "onDeviceFriendlyNameUpdate, name = " + name);
        boolean ret = false;
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            ret = iLinkAdapter.onDeviceFriendlyNameUpdate(devId, name);
        } else {
            LogUtil.warning("CarPlayManager", "onDeviceFriendlyNameUpdate mLinkManagerListener is null.");
        }
        setOobPairName(name);
        return ret;
    }

    public void notifyCarPlayClientWifiAddr(String addr) {
        if (!this.mIsConnectByUser) {
            LogUtil.debug("CarPlayManager", "not connect by user,do not merge,hiJackingMac:" + LogEncrypt.MacAddressEncrypt(addr));
            return;
        }
        LinkDevice linkDevice = this.mLinkMergeDevice;
        if (linkDevice != null && linkDevice.getLinkType() == 2) {
            LogUtil.debug("CarPlayManager", "notifyCarPlayClientWifiAddr, addr = " + LogEncrypt.MacAddressEncrypt(addr) + ",mIsConnectByUser:" + this.mIsConnectByUser);
            this.mWifiMacAddress = addr;
        }
    }

    public void onSupportFeaturesUpdate(int supportsFeatures) {
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onSupportFeaturesUpdate(supportsFeatures);
        }
    }

    public void onAuxInUpdateState(int state) {
        if (1 == state && this.mIsActivateEnhancedSiri) {
            controlAuxIn();
        }
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onAuxInUpdateState(state);
        }
    }

    public void onEnhancedSiriParamsUpdate(int inMode, String inLanguage) {
        LogUtil.info("CarPlayManager", "onEnhancedSiriParamsUpdate inMode:" + inMode + ", inLanguage= " + inLanguage);
        this.mIsActivateEnhancedSiri = true;
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.stopAuxIn();
        }
        controlAuxIn();
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onEnhancedSiriParamsUpdate(inMode, inLanguage);
        }
    }

    public void onAltRequestSetup() {
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onAltRequestSetup();
        }
    }

    public void onSuggestUi(int url) {
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onSuggestUi(url);
        }
    }

    public boolean checkIsFirstConnCp() {
        if (this.mCarPlayConnect.getLinkDevice() != null) {
            LogUtil.info("CarPlayManager", "checkIsFirstConnCp, status = " + this.mIsDeviceFirstConn);
            return this.mIsDeviceFirstConn;
        }
        return false;
    }

    public boolean checkIsConnectFromUser() {
        if (this.mCarPlayConnect.getLinkDevice() != null) {
            LogUtil.info("CarPlayManager", "mIsConnectFromUser, status = " + this.mIsConnectFromUser);
            return this.mIsConnectFromUser;
        }
        return false;
    }

    public void saveConnCpValue() {
        this.mCarPlayConnect.getLinkDevice();
    }

    public int getCurTopScene() {
        return -1;
    }

    public int getCurTopScenePriority() {
        return -1;
    }

    public float getVehicleEngineSpeed() {
        LogUtil.info("CarPlayManager", "getVehicleEngineSpeed, engineSpeed=0.0");
        return 0.0f;
    }

    public Location getVehicleLastLocation() {
        LogUtil.info("CarPlayManager", "getVehicleLastLocation");
        return null;
    }

    public CarPlayConfig getCarPlayConfig() {
        LogUtil.info("CarPlayManager", "getCarPlayConfig :" + mCarPlayConfig);
        return mCarPlayConfig;
    }

    public GpsStatus getGpsStatus() {
        LogUtil.info("CarPlayManager", "getGpsStatus");
        return null;
    }

    public boolean getNightMode() {
        LogUtil.info("CarPlayManager", "getNightMode, isNightModefalse");
        return false;
    }

    public boolean getParkingBreak() {
        LogUtil.info("CarPlayManager", "getParkingBreak, isParkingBreak=true");
        return true;
    }

    public void duckAudio(int durationMs, int volume) {
        IAudioAdapter iAudioAdapter = this.mAudioAdapter;
        if (iAudioAdapter != null) {
            iAudioAdapter.duckAudio(durationMs, volume);
        }
    }

    public void unduckAudio(int durationMs) {
        IAudioAdapter iAudioAdapter = this.mAudioAdapter;
        if (iAudioAdapter != null) {
            iAudioAdapter.unduckAudio(durationMs);
        }
    }

    public void rspAudioFocusReq(int audioType, boolean isSuccess) {
        LogUtil.info("CarPlayManager", "rspAudioFocusReq audioType: " + audioType + ", isSuccess: " + isSuccess);
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.rspAudioFocusReq(audioType, isSuccess);
        }
    }

    public boolean requestChangeEntertainmentMute(boolean mute) {
        LogUtil.info("CarPlayManager", "requestChangeEntertainmentMute mute: " + mute);
        return false;
    }

    public void abandonCpMicFocus(int micId) {
    }

    public void onCarPlayDeviceStatusChanged(LinkDevice linkDevice, int linkStatus) {
        LogUtil.info("CarPlayManager", "onCarPlayDeviceStatusChanged linkDevice " + linkDevice);
        onStatusChanged(linkDevice, linkStatus);
    }

    public void onSessionEstablished(LinkDevice device) {
        LogUtil.info("CarPlayManager", "onSessionEstablished, device=" + device + ",isConnectByUser:" + this.mIsConnectByUser);
        if (this.mIsConnectByUser) {
            onStatusChanged(device, 2);
            addCpDrLocListener();
            this.mCarPlayLocationDataManager.onSessionEstablished();
            return;
        }
        this.mCarPlayConnect.disconnect(this.mLinkDevice);
    }

    public void onSessionEstablishFailed(LinkDevice device) {
        LogUtil.info("CarPlayManager", "onSessionEstablishFailed ");
        this.mCurIapClientType = 153;
        onStatusChanged(device, 5);
    }

    public void onSessionDestoryed(LinkDevice device) {
        LogUtil.info("CarPlayManager", "onSessionDestoryed device " + device);
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.setBluetoothCoexistence(this.mBtAddress, false);
        }
        ILocationAdapter iLocationAdapter = this.mLocationAdapter;
        if (iLocationAdapter != null) {
            iLocationAdapter.unregisterListener(this.mLocationAdapterListener);
        }
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.onSessionDestoryed();
        }
        this.mCurIapClientType = 153;
        this.mCarPlayLocationDataManager.onSessionDestoryed();
        removeCpDrLocListener();
        CarPlayMediaDataManager carPlayMediaDataManager = this.mMediaDataManager;
        if (carPlayMediaDataManager != null) {
            carPlayMediaDataManager.clearMusicInfo();
        }
        this.mCarPlayConnect.closeRfSocketConnected();
        onCarPlayDisplayStatusChanged(false);
        this.mIsActivateEnhancedSiri = false;
    }

    public void notifyCarPlaySessionDisconnected(LinkDevice device) {
        LogUtil.info("CarPlayManager", "notifyCarPlaySessionDisconnected device " + device);
        onStatusChanged(device, 4);
    }

    public CarPlayResourceManager getCarPlayResourceManager() {
        return this.mCarPlayResourceManager;
    }

    public CarPlayEventListener getInnerEventHandler() {
        return this.mInnerEventHandler;
    }

    public boolean getIsFromUserSts() {
        LogUtil.error("CarPlayManager", "getIsFromUserSts, isFromUserConn = " + this.mIsFromUserConn);
        return this.mIsFromUserConn;
    }

    public void handlePressHardKey(int keyCode) {
        LogUtil.info("CarPlayManager", "handlePressHardKey, keycode=" + keyCode);
        if (keyCode == 1) {
            this.mCarPlayStack.phoneKeyEvent(1, true);
            return;
        }
        if (keyCode == 2) {
            this.mCarPlayStack.phoneKeyEvent(2, true);
            return;
        }
        if (keyCode != 3) {
            switch (keyCode) {
                case 32:
                    this.mCarPlayStack.hardKeyEvent(0, true);
                    break;
                case 33:
                    this.mCarPlayStack.hardKeyEvent(1, true);
                    break;
                case 34:
                    this.mCarPlayStack.hardKeyEvent(4, true);
                    break;
                case 35:
                    this.mCarPlayStack.hardKeyEvent(2, true);
                    break;
                case 36:
                    this.mCarPlayStack.hardKeyEvent(3, true);
                    break;
                case 37:
                    this.mCarPlayStack.hardKeyEvent(6, true);
                    break;
            }
            return;
        }
        this.mCarPlayStack.phoneKeyEvent(3, true);
    }

    public void handleReleaseHardKey(int keyCode) {
        LogUtil.info("CarPlayManager", "releaseHardKey, keycode=" + keyCode);
        if (keyCode == 1) {
            this.mCarPlayStack.phoneKeyEvent(1, false);
        }
        if (keyCode == 2) {
            this.mCarPlayStack.phoneKeyEvent(2, false);
            return;
        }
        if (keyCode == 3) {
            this.mCarPlayStack.phoneKeyEvent(3, false);
            return;
        }
        switch (keyCode) {
            case 32:
                this.mCarPlayStack.hardKeyEvent(0, false);
                break;
            case 33:
                this.mCarPlayStack.hardKeyEvent(1, false);
                break;
            case 34:
                this.mCarPlayStack.hardKeyEvent(4, false);
                break;
            case 35:
                this.mCarPlayStack.hardKeyEvent(2, false);
                break;
            case 36:
                this.mCarPlayStack.hardKeyEvent(3, false);
                break;
            case 37:
                this.mCarPlayStack.hardKeyEvent(6, false);
                break;
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public int setUiAppearanceMode(String uuid, int uiMode, int uiSetting) {
        LogUtil.info("CarPlayManager", "setUiAppearanceMode : uuid = " + LogEncrypt.MacAddressEncrypt(uuid) + ", uiMode = " + uiMode + ", uiSetting = " + uiSetting);
        CarPlayConfig carPlayConfig = mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.info("CarPlayManager", "setUiAppearanceMode : mCarPlayConfig is null");
        } else {
            ScreenInfo[] screenInfoList = carPlayConfig.getScreenInfos();
            if (screenInfoList == null || screenInfoList.length <= 0) {
                LogUtil.warning("CarPlayManager", "setUiAppearanceMode : screenInfoList is null");
            } else {
                int screenType = 0;
                while (true) {
                    if (screenType >= screenInfoList.length) {
                        break;
                    }
                    String screenUuid = screenInfoList[screenType].getUuid();
                    if (!screenUuid.equals(uuid) || screenInfoList[screenType] == null) {
                        screenType++;
                    } else {
                        screenInfoList[screenType].setUiMode(uiMode);
                        screenInfoList[screenType].setUiSetting(uiSetting);
                        break;
                    }
                }
            }
            mCarPlayConfig.setScreenInfos(screenInfoList);
        }
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            return carPlayStack.setUiAppearanceMode(uuid, uiMode, uiSetting);
        }
        return -1;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public int setMapAppearanceMode(String uuid, int mapMode, int mapSetting) {
        LogUtil.debug("CarPlayManager", "setMapAppearanceMode: uuid = " + LogEncrypt.MacAddressEncrypt(uuid) + ", mapMode = " + mapMode + ", mapSetting = " + mapSetting);
        CarPlayConfig carPlayConfig = mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.info("CarPlayManager", "setMapAppearanceMode : mCarPlayConfig is null");
        } else {
            ScreenInfo[] screenInfoList = carPlayConfig.getScreenInfos();
            if (screenInfoList == null || screenInfoList.length <= 0) {
                LogUtil.warning("CarPlayManager", "setMapAppearanceMode : screenInfoList is null");
            } else {
                int screenType = 0;
                while (true) {
                    if (screenType >= screenInfoList.length) {
                        break;
                    }
                    if (screenInfoList[screenType] != null) {
                        String screenUuid = screenInfoList[screenType].getUuid();
                        if (screenUuid.equals(uuid)) {
                            screenInfoList[screenType].setMapMode(mapMode);
                            screenInfoList[screenType].setMapSetting(mapSetting);
                            break;
                        }
                    }
                    screenType++;
                }
            }
            mCarPlayConfig.setScreenInfos(screenInfoList);
        }
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            return carPlayStack.setMapAppearanceMode(uuid, mapMode, mapSetting);
        }
        return -1;
    }

    public void startNewWiredCarPlaySession() {
        LogUtil.info("CarPlayManager", "startNewWiredCarPlaySession.");
        if (this.mStartSessionInfo != null) {
            CarplayStartSessionInfo.WiredAttribute wiredAttribute = new CarplayStartSessionInfo.WiredAttribute();
            LogUtil.info("CarPlayManager", "startNewWiredCarPlaySession getUsbIpAddress start.");
            String address = this.mNetworkManagement.getUsbIpAddress();
            LogUtil.info("CarPlayManager", "startNewWiredCarPlaySession getUsbIpAddress end.");
            if (TextUtils.isEmpty(address)) {
                LogUtil.warning("CarPlayManager", "startNewWiredCarPlaySession IpAddress is null.");
                return;
            }
            LogUtil.debug("CarPlayManager", "getUsbIpAddress address:" + LogEncrypt.IpAddressEncrypt(address));
            wiredAttribute.setIpAddress(address);
            this.mStartSessionInfo.setWiredAttribute(wiredAttribute);
            this.mStartSessionInfo.setPort(CarPlayStack.getInstance().getListenPort());
            this.mStartSessionInfo.setPublicKey(CarPlayStack.getInstance().getPairingIdentity());
            this.mStartSessionInfo.setSourceVersion(CarPlayStack.getInstance().getSourceVersion());
            CarPlayStack carPlayStack = this.mCarPlayStack;
            if (carPlayStack != null) {
                this.mStartSessionInfo.setDeviceIdentifier(carPlayStack.getDeviceId());
            }
        }
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.sendCarplayStartSession(17, this.mStartSessionInfo);
        }
    }

    public boolean startNewWirelessCarPlaySession() {
        LogUtil.info("CarPlayManager", "startNewWirelessCarPlaySession.");
        setInterfaceName(this.mWifiInterface);
        setInterfaceRoute(INTERFACE_ACTION_UP);
        this.mUserNewConnection = true;
        CarplayStartSessionInfo.WirelessAttribute wirelessAttribute = new CarplayStartSessionInfo.WirelessAttribute();
        if (this.mStartSessionInfo != null) {
            CarPlayIapSessionManager carPlayIapSessionManager = this.mIapSessionManager;
            if (carPlayIapSessionManager != null) {
                String ipAddress = carPlayIapSessionManager.getIpAddress();
                if (ipAddress == null && (ipAddress = this.mNetworkManagement.getWifiApIpAddress(this.mIapSessionManager.getWiFiInfoBackUp())) == null) {
                    LogUtil.warning("CarPlayManager", "startNewWirelessCarPlaySession ipAddress is null.");
                    return false;
                }
                LogUtil.debug("CarPlayManager", "startNewWirelessCarPlaySession ipAddress is:" + LogEncrypt.IpAddressEncrypt(ipAddress));
                wirelessAttribute.setIpAddress(ipAddress);
                wirelessAttribute.setWifiSsid(this.mIapSessionManager.getSsid());
                wirelessAttribute.setPassphrase(this.mIapSessionManager.getPassphrase());
                wirelessAttribute.setChannel(this.mIapSessionManager.getChannel());
                int securityType = this.mIapSessionManager.getSecurity();
                if (securityType < 3) {
                    LogUtil.warning("CarPlayManager", "security type invaild.");
                }
                wirelessAttribute.setSecurityType(securityType);
                this.mStartSessionInfo.setDeviceIdentifier(this.mIapSessionManager.getMacAddress());
            }
            this.mStartSessionInfo.setWirelessAttribute(wirelessAttribute);
            this.mStartSessionInfo.setPort(CarPlayStack.getInstance().getListenPort());
            this.mStartSessionInfo.setPublicKey(CarPlayStack.getInstance().getPairingIdentity());
            this.mStartSessionInfo.setSourceVersion(CarPlayStack.getInstance().getSourceVersion());
        }
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter == null) {
            LogUtil.warning("CarPlayManager", "mIapAdapter is null.");
            return false;
        }
        iIapAdapter.sendCarplayStartSession(18, this.mStartSessionInfo);
        return true;
    }

    public boolean getIsCarPlayConnection() {
        LogUtil.info("CarPlayManager", "getIsCarPlayConnection, mIsCarPlayConnection=" + this.mIsCarPlayConnection);
        return this.mIsCarPlayConnection;
    }

    public boolean getIsCarplayOldConnection() {
        LogUtil.info("CarPlayManager", "getIsCarplayOldConnection, mIsCarplayOldConnection=" + this.mIsCarPlayOldConnection);
        return this.mIsCarPlayOldConnection;
    }

    public void setIsCarplayOldConnection(boolean isOldConnection) {
        LogUtil.info("CarPlayManager", "setIsCarplayOldConnection, mIsCarplayOldConnection=" + isOldConnection);
        this.mIsCarPlayOldConnection = isOldConnection;
    }

    private boolean isInCarPlayScreen() {
        return this.mCarPlayResourceManager.isCarPlayShown();
    }

    private boolean isPlayingMusic() {
        return false;
    }

    public void initRmManager() {
        this.mAudioResourceManager = this.mCarPlayResourceManager.getAudioResourceManager();
        this.mScreenResourceManager = this.mCarPlayResourceManager.getscreenResourceManager();
    }

    public void updateSerialNumberExternal(String serialNumber, LinkDevice device) {
    }

    public void updateSerialNumber(int devHandle, String serialNumber) {
        this.mCarPlayConnect.updateSerialNumber(devHandle, serialNumber);
    }

    public int getMusicStatus() {
        return -1;
    }

    public void setDeviceMirroringRunning(boolean on, boolean always) {
        LogUtil.info("CarPlayManager", "setDeviceMirroringRunning on " + on + ", always" + always);
        if (on && !always) {
        }
    }

    public void notifyBtOobPairComplete(String devId, int status, int srvType) {
        LogUtil.info("CarPlayManager", "notifyBtOobPairComplete status " + status);
    }

    public boolean onCreateIapSession(int clientType, IapConfig config) {
        LogUtil.info("CarPlayManager", "onCreateIapSession clientType " + clientType);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.createSession(clientType, config);
        }
        return false;
    }

    public boolean onDestroyIapSession(int clientType) {
        LogUtil.info("CarPlayManager", "onDestroyIapSession clientType " + clientType);
        this.mIsBtA2dpConnected = false;
        this.mIsNeedRequestMedia = false;
        if (clientType != 16) {
            this.mIsCarPlayConnection = false;
        }
        this.mCarPlayConnect.onSessionDestroy(clientType);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter == null) {
            return false;
        }
        return iIapAdapter.destroySession(clientType);
    }

    public int onConnectIapSession(int clientType) {
        LogUtil.info("CarPlayManager", "onConnectIapSession clientType " + clientType);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.connectSession(clientType);
        }
        return -1;
    }

    public int onDisconnectIapSession(int clientType) {
        LogUtil.info("CarPlayManager", "onDisconnectIapSession clientType " + clientType);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.disconnectSession(clientType);
        }
        return -1;
    }

    public int onSendLocationInformation(String nmeaSentence) {
        IIapAdapter iIapAdapter;
        int i = this.mCurIapClientType;
        if (i == 153 || (iIapAdapter = this.mIapAdapter) == null) {
            return -1;
        }
        return iIapAdapter.sendLocationInformation(i, nmeaSentence);
    }

    public int onSendOobBtPairingAccessoryInformation(int clientType, long deviceClass) {
        LogUtil.info("CarPlayManager", "onSendOobBtPairingAccessoryInformation clientType " + clientType + "deviceClass：" + deviceClass);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.sendOobBtPairingAccessoryInformation(clientType, deviceClass);
        }
        return -1;
    }

    public int onSendOobBtPairingCompletionInformation(int clientType, int resultCode) {
        LogUtil.info("CarPlayManager", "onSendOobBtPairingCompletionInformation clientType " + clientType + "resultCode:" + resultCode);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.sendOobBtPairingCompletionInformation(clientType, resultCode);
        }
        return -1;
    }

    public int onSendAccessoryWiFiConfigurationInformation(int clientType, AccessoryWiFiInfo info) {
        LogUtil.info("CarPlayManager", "onSendAccessoryWiFiConfigurationInformation clientType " + clientType + "info:" + info);
        AccessoryWiFiInfo wiFiInfo = new AccessoryWiFiInfo();
        wiFiInfo.setSsid(info.getSsid());
        wiFiInfo.setPassphrase(info.getPassphrase());
        int security = info.getSecurity();
        if (security > 2) {
            security = 2;
        }
        wiFiInfo.setSecurity(security);
        wiFiInfo.setChannel(info.getChannel());
        wiFiInfo.setIpAddress(info.getIpAddress());
        wiFiInfo.setMacAddress(info.getMacAddress());
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.sendAccessoryWiFiConfigurationInformation(clientType, wiFiInfo);
        }
        return -1;
    }

    public int onRequestAppLaunch(int clientType, String appBundleId, int appLaunchMethod) {
        LogUtil.info("CarPlayManager", "onRequestAppLaunch clientType " + clientType + appBundleId + appLaunchMethod);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.requestAppLaunch(clientType, appBundleId, appLaunchMethod);
        }
        return -1;
    }

    public CarPlayOobManager getOobManager() {
        return this.mOobManager;
    }

    public CarPlayMediaDataManager getCarPlayMediaDataManager() {
        return this.mMediaDataManager;
    }

    public CarPlayLocationDataManager getCarPlayLocationDataManager() {
        return this.mCarPlayLocationDataManager;
    }

    public void onPlaybackElapsedTimeChange(long playbackDuration, long playbackElapsedTime) {
    }

    public boolean onUsbDeviceNameUpdate(String devId, String name) {
        LogUtil.debug("CarPlayManager", "onUsbDeviceNameUpdate devId" + LogEncrypt.MacAddressEncrypt(devId) + "name" + name);
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null) {
            LogUtil.warning("CarPlayManager", "onUsbDeviceNameUpdate mLinkManagerListener is null.");
            return false;
        }
        boolean ret = iLinkAdapter.onUsbDeviceNameUpdate(devId, name);
        return ret;
    }

    public void onUpdateIapSessionState(String id, int state, int reason) {
        LogUtil.debug("CarPlayManager", "onUpdateIapSessionState devId:" + LogEncrypt.MacAddressEncrypt(id) + "state:" + state);
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null) {
            LogUtil.warning("CarPlayManager", "onUpdateIapSessionState mLinkManagerListener is null.");
        } else {
            iLinkAdapter.onUpdateIapSessionState(id, state, reason);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void registerListener(ICarPlayManagerListener listener) {
        this.mCarPlayManagerListener = listener;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void unregisterListener(ICarPlayManagerListener listener) {
        this.mCarPlayManagerListener = null;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setSurface(Surface surface) {
        setSurface(surface, 0);
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setSurface(Surface surface, int screenType) {
        if (surface == null) {
            LogUtil.error("CarPlayManager", "surface hide from ShowProjection");
            this.mProjectionLock.lock();
            this.mIsShowActivityStarted = false;
            this.mHandler.removeMessages(3);
            this.mProjectionLock.unlock();
            CarPlayStack carPlayStack = this.mCarPlayStack;
            if (carPlayStack != null) {
                carPlayStack.carPlayHide(screenType);
                return;
            }
            return;
        }
        this.mProjectionLock.lock();
        this.mIsShowActivityStarted = true;
        Message message = new Message();
        message.what = 3;
        this.mHandler.sendMessageDelayed(message, 6000L);
        this.mProjectionLock.unlock();
        SurfaceData surData = new SurfaceData();
        surData.setSurface(surface);
        surData.setScreenType(screenType);
        show(surData);
        LogUtil.info("CarPlayManager", "surface show from ShowProjection, ignore the surface");
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setLimitedUi(boolean mode) {
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.setLimitedUi(mode);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public int getSupportFeatures() {
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            int supportFeatures = carPlayStack.getSupportFeaturesUpdate();
            return supportFeatures;
        }
        LogUtil.warning("CarPlayManager", "getSupportFeatures mCarPlayStack is null");
        return 0;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setNightMode(boolean mode) {
        LogUtil.debug("CarPlayManager", "setNightMode: isNigthMode = " + mode);
        CarPlayConfig carPlayConfig = mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.info("CarPlayManager", "setNightMode: mCarPlayConfig is null");
        } else {
            carPlayConfig.setNightMode(mode);
        }
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.setNightMode(mode);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setLastUserMode(int resource, boolean mode) {
        if (resource == 0) {
            if (mode) {
                CarPlayLastUserMode carPlayLastUserMode = CarPlayLastUserMode.getInstance();
                CpConst.CarPlayResource carPlayResource = CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO;
                CarPlayLastUserMode.getInstance();
                carPlayLastUserMode.setLastUserMode(carPlayResource, 1);
                return;
            }
            CarPlayLastUserMode carPlayLastUserMode2 = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource2 = CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode2.setLastUserMode(carPlayResource2, 0);
            return;
        }
        if (resource == 1) {
            if (mode) {
                CarPlayLastUserMode carPlayLastUserMode3 = CarPlayLastUserMode.getInstance();
                CpConst.CarPlayResource carPlayResource3 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
                CarPlayLastUserMode.getInstance();
                carPlayLastUserMode3.setLastUserMode(carPlayResource3, 1);
                return;
            }
            CarPlayLastUserMode carPlayLastUserMode4 = CarPlayLastUserMode.getInstance();
            CpConst.CarPlayResource carPlayResource4 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN;
            CarPlayLastUserMode.getInstance();
            carPlayLastUserMode4.setLastUserMode(carPlayResource4, 0);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void changeVideoFocus(int priority, boolean backgroundSupport, String borrowId, int action) {
        CarPlayResourceManager carPlayResourceManager = this.mCarPlayResourceManager;
        if (carPlayResourceManager != null) {
            carPlayResourceManager.accessoryScreenChange(priority, backgroundSupport, borrowId, action);
        } else {
            LogUtil.warning("CarPlayManager", "changeVideoFocus mCarPlayResourceManager is null");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void screenTouchEvent(MotionEvent event) {
        if (this.mCarPlayConnect.isConnected()) {
            this.mCarPlayStack.screenTouchEvent(event);
        } else {
            LogUtil.info("CarPlayManager", "screenTouchEvent: carplay is not connected");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void requestUi(int launchMode) {
        LogUtil.debug("CarPlayManager", "requestUi, launchMode = " + launchMode);
        this.mCarPlayStack.appLaunch(launchMode);
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void showUi(int screenType, int url) {
        LogUtil.debug("CarPlayManager", "showUi, screenType = " + screenType + " url = " + url);
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.showUi(screenType, url);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void stopUi(int screenType) {
        LogUtil.debug("CarPlayManager", "stopUi, screenType = " + screenType);
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.stopUi(screenType);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public boolean getLastUserMode(int resource) {
        LogUtil.info("CarPlayManager", "getLastUserMode, resource = " + resource);
        CarPlayLastUserMode.getInstance();
        int lastmode = 0;
        if (resource == 0) {
            lastmode = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO);
        } else if (resource == 1) {
            lastmode = CarPlayLastUserMode.getInstance().getLastUserModeState(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN);
        }
        LogUtil.info("CarPlayManager", "getLastUserMode, lastmode = " + lastmode);
        CarPlayLastUserMode.getInstance();
        return lastmode == 1;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void initiateCall(int callType, String destId, int callService) {
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.initiateCall(this.mCurIapClientType, callType, destId, callService);
        } else {
            LogUtil.warning("CarPlayManager", "initiateCall mIapAdapter is null");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void acceptCall(int action, String callUuid) {
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.acceptCall(this.mCurIapClientType, action, callUuid);
        } else {
            LogUtil.warning("CarPlayManager", "acceptCall mIapAdapter is null");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void endCall(int action, String callUuid) {
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.endCall(this.mCurIapClientType, action, callUuid);
        } else {
            LogUtil.warning("CarPlayManager", "endCall mIapAdapter is null");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void swapCalls() {
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.swapCalls(this.mCurIapClientType);
        } else {
            LogUtil.warning("CarPlayManager", "swapCalls mIapAdapter is null");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void sendDtmf(int toneType, String callUuid) {
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.sendDtmf(this.mCurIapClientType, toneType, callUuid);
        } else {
            LogUtil.warning("CarPlayManager", "sendDtmf mIapAdapter is null");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void sendHidEventOverIap(int keyCode, int keyAction) {
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.sendHidEventOverIap(this.mCurIapClientType, keyCode, keyAction);
        } else {
            LogUtil.warning("CarPlayManager", "sendHidEventOverIap mIapAdapter is null.");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void sendHidEventOverCarPlay(int keyCode, int keyAction) {
        if (keyAction == 0) {
            handlePressHardKey(keyCode);
        } else if (keyAction == 1) {
            handleReleaseHardKey(keyCode);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setSongElapsedTime(long elapsedTime) {
        LogUtil.info("CarPlayManager", "setSongElapsedTime elapsedTime:" + elapsedTime);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.setSongElapsedTime(this.mCurIapClientType, elapsedTime);
        } else {
            LogUtil.warning("CarPlayManager", "setSongElapsedTime mIapAdapter is null.");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setNativeAppState(int appState, boolean enabled, int speechMode) {
        if (appState == 0) {
            this.mNativePhoneStatus = enabled;
            this.mNativeSpeechStatus = speechMode;
            if (this.mIsActivateEnhancedSiri) {
                controlAuxIn();
            }
        } else if (appState != 1) {
            if (appState == 2) {
                this.mNativeSpeechStatus = speechMode;
            } else {
                LogUtil.warning("CarPlayManager", "setNativeAppState IllegalArgument.");
                return;
            }
        } else {
            this.mNativeNaviStatus = enabled;
            if (!enabled && this.mCarPlayResourceManager.getNaviEntity() == 1) {
                LogUtil.info("CarPlayManager", "ios navi interrupt native navi.");
                return;
            }
        }
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.changeAppState(this.mNativeSpeechStatus, this.mNativePhoneStatus, this.mNativeNaviStatus);
        }
        if (!this.mCarPlayConnect.isConnected()) {
            LogUtil.info("CarPlayManager", "setNativeAppState: carplay is not connected");
            onCarPlayAppStateChanged(appState, false, speechMode);
            if (enabled) {
                onCarPlayAppStateChanged(appState, 2, speechMode);
            } else {
                onCarPlayAppStateChanged(appState, 0, speechMode);
            }
        }
    }

    private void controlAuxIn() {
        CarPlayResourceManager carPlayResourceManager = this.mCarPlayResourceManager;
        if (carPlayResourceManager != null && this.mCarPlayStack != null) {
            int phoneStatus = carPlayResourceManager.getPhoneEntity();
            int phoneSpeechMode = this.mCarPlayResourceManager.getPhoneSpeechMode();
            int vrStatus = this.mCarPlayResourceManager.getVrSpeechEntity();
            if (!this.mNativePhoneStatus) {
                if (2 == vrStatus || (1 == phoneStatus && 1 == phoneSpeechMode)) {
                    this.mCarPlayStack.stopAuxIn();
                } else if (1 == phoneStatus) {
                    LogUtil.info("CarPlayManager", "controlAuxIn,Cp phone is actived, do not startAuxIn");
                } else {
                    this.mCarPlayStack.startAuxIn();
                }
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setCarPlayConfig(CarPlayConfig carPlayConfig) {
        LogUtil.info("CarPlayManager", "setCarPlayConfig:" + carPlayConfig);
        if (mCarPlayConfig == null) {
            mCarPlayConfig = new CarPlayConfig();
        }
        if (carPlayConfig == null) {
            mCarPlayConfig = null;
            return;
        }
        mCarPlayConfig.copy(carPlayConfig);
        int disPlayOffExpectedAction = mCarPlayConfig.getDisPlayOffExpected();
        if (CarPlayConfig.DisPlayAction.isDisPlayActionValid(disPlayOffExpectedAction)) {
            this.mScreenResourceManager.setDisPlayOffExcepted(disPlayOffExpectedAction);
        }
        CarPlayIapSessionManager.getInstance().setIapFeature(mCarPlayConfig.getIapFeatures());
        this.mCarPlayCapability = mCarPlayConfig.getCarPlayCapability();
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.setCarPlayConfig(mCarPlayConfig);
        }
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void requestSiri(int siriAction) {
        LogUtil.debug("CarPlayManager", "requestSiri action:" + siriAction + " isActivateEnhancedSiri:" + this.mIsActivateEnhancedSiri);
        if (this.mNativePhoneStatus) {
            LogUtil.warning("CarPlayManager", "native phone call is active,not request siri.");
            return;
        }
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.siriLaunch(siriAction);
        }
        if (this.mIsActivateEnhancedSiri) {
            LogUtil.warning("CarPlayManager", "requestSiri illegal operation without timestamp.");
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void launchSiri(int siriAction, long siriTriggerTimestamp) {
        LogUtil.debug("CarPlayManager", "launchSiri action:" + siriAction + " timestamp:" + siriTriggerTimestamp + " isActivateEnhancedSiri:" + this.mIsActivateEnhancedSiri);
        if (this.mNativePhoneStatus) {
            LogUtil.warning("CarPlayManager", "native phone call is active,not launch siri.");
            return;
        }
        if (this.mIsActivateEnhancedSiri) {
            if (this.mCarPlayResourceManager.getVrSpeechMode() == 2 && siriAction == 4) {
                LogUtil.debug("CarPlayManager", "VOICE_ACTIVATION do nothing when siri RECOGNIZING");
                return;
            }
            CarPlayStack carPlayStack = this.mCarPlayStack;
            if (carPlayStack != null) {
                carPlayStack.launchSiri(siriAction, siriTriggerTimestamp);
                return;
            }
            return;
        }
        requestSiri(siriAction);
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setEnhancedSiriInfo(List<String> voiceModelSupportedLanguages, String voiceModelCurrentLanguage, int supportedSiriTriggerZones) {
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.setEnhancedSiriInfo(voiceModelSupportedLanguages, voiceModelCurrentLanguage, supportedSiriTriggerZones);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void setCallbackUpdate(int type, boolean isStart) {
        LogUtil.info("CarPlayManager", "setCallbackUpdate type = " + type + ",isStart = " + isStart);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.setCallbackUpdate(type, isStart);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public Object getCurrentInfo(int type) {
        LogUtil.info("CarPlayManager", "getInfo type = " + type);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter == null) {
            return null;
        }
        Object info = iIapAdapter.getCurrentInfo(type);
        return info;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public int getContactListCount(int type) {
        int count = -1;
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            count = iIapAdapter.getContactListCount(type);
        }
        LogUtil.info("CarPlayManager", "getContactListCount count: " + count);
        return count;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public List<RecentContactListInfo> getRecentListInfo() {
        List<RecentContactListInfo> list = null;
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            list = iIapAdapter.getRecentListInfo();
        }
        LogUtil.info("CarPlayManager", "getRecentListInfo.");
        return list;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public List<FavoriteContactListInfo> getFavoriteListInfo() {
        List<FavoriteContactListInfo> list = null;
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            list = iIapAdapter.getFavoriteListInfo();
        }
        LogUtil.info("CarPlayManager", "getFavoriteListInfo.");
        return list;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public void playSong(long pbIndex) {
        LogUtil.info("CarPlayManager", "playsong pbIndex:" + pbIndex);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            iIapAdapter.playSong(this.mCurIapClientType, pbIndex);
        } else {
            LogUtil.warning("CarPlayManager", "playsong mIapAdapter is null.");
        }
    }

    public boolean isWiredCapabilitySupported() {
        return CarPlayConfig.CarPlayCapability.isCarPlayCapabilitySupported(this.mCarPlayCapability, 1);
    }

    public boolean isWirelessCapabilitySupported() {
        return CarPlayConfig.CarPlayCapability.isCarPlayCapabilitySupported(this.mCarPlayCapability, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStatusChanged(LinkDevice device, int linkStatus) {
        if (device != null) {
            LogUtil.debug("CarPlayManager", "onStatusChanged, wifiAddr: " + LogEncrypt.MacAddressEncrypt(this.mWifiMacAddress) + ", linkStatus: " + linkStatus);
            if (linkStatus == 4 || linkStatus == 5) {
                boolean deleteRoute = false;
                if (device.getLinkType() == 2) {
                    if (this.mUserNewConnection) {
                        deleteRoute = true;
                        this.mUserNewConnection = false;
                        setInterfaceName(this.mWifiInterface);
                    }
                } else if (device.getLinkType() == 1) {
                    deleteRoute = true;
                    setInterfaceName("usb0");
                }
                this.mIsCarPlayConnection = false;
                if (deleteRoute) {
                    setInterfaceRoute(INTERFACE_ACTION_DOWN);
                    try {
                        try {
                            Thread.sleep(350L);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    } finally {
                        LogUtil.debug("CarPlayManager", "sleep complete after deleting route.");
                    }
                } else {
                    LogUtil.debug("CarPlayManager", "no need to delete route while old wireless conenction.");
                }
            }
        }
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter != null) {
            iLinkAdapter.onStatusChanged(device, linkStatus);
        }
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onCarPlaySessionStateChanged(linkStatus);
            this.mCarPlayManagerListener.onCarPlaySessionStateChanged(linkStatus, getIsCarplayOldConnection(), this.mWifiMacAddress);
            if (linkStatus != 1) {
                this.mCarPlayManagerListener.onCarPlaySessionStateChanged(linkStatus, getIsCarplayOldConnection(), this.mWifiMacAddress, this.mIsConnectFromUser);
            }
        }
        sendCpStatusBroadcast(linkStatus);
    }

    private void sendCpStatusBroadcast(int linkStatus) {
        boolean isActivated;
        Intent intent = new Intent(CpConst.BroadcastConst.ACTION_LINK_STATUS_CHANGED);
        if (linkStatus == 2) {
            isActivated = true;
        } else {
            isActivated = false;
        }
        intent.putExtra("isActivated", isActivated);
        this.mContext.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestVideoFocusChange(int requestType) {
        if (requestType == 0 || requestType == 1 || requestType == 2) {
            ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
            if (iCarPlayManagerListener != null) {
                iCarPlayManagerListener.onRequestVideoFocus(requestType);
                return;
            }
            return;
        }
        LogUtil.warning("CarPlayManager", "requestVideoFocusChange invalid requestType:" + requestType);
    }

    private class LinkAdapterListenerImpl implements ILinkAdapterListener {
        private LinkAdapterListenerImpl() {
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onRequestProjection(CarPlayDevice device, int type) {
            LogUtil.info("CarPlayManager", "onRequestProjection device:" + device + "type" + type);
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onRequestProjection(device, type);
            }
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public int connecting(LinkDevice device, boolean first, boolean fromUser) {
            CarPlayManager.this.mLock.lock();
            LogUtil.info("CarPlayManager", "connecting " + device + ", first=" + first + ",fromUser=" + fromUser);
            CarPlayManager.this.mIsFromUserConn = false;
            CarPlayManager.this.mLinkDevice = device;
            CarPlayManager.this.mLinkMergeDevice = device;
            CarPlayManager.this.mIsDeviceFirstConn = first;
            CarPlayManager.this.mIsConnectFromUser = fromUser;
            CarPlayManager.this.mLock.unlock();
            CarPlayManager.this.onStatusChanged(device, 1);
            if (device.getLinkType() == 2) {
                CarPlayManager.this.mCurIapClientType = 19;
            } else {
                CarPlayManager.this.mCurIapClientType = 17;
            }
            CarPlayManager.this.mCarPlayResourceManager.clearLocalStatus();
            int result1 = CarPlayManager.this.mCarPlayConnect.connect(device);
            return result1 != 0 ? 5 : 1;
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public int disconnecting(LinkDevice device, int reason) {
            LogUtil.info("CarPlayManager", "disconnecting, device=" + device);
            if (CarPlayManager.this.mCarPlayReconnect != null) {
                if (device == null) {
                    LogUtil.warning("CarPlayManager", "disconnecting, device is null.");
                    return 5;
                }
                if (reason == 6) {
                    CarPlayManager.this.mCarPlayReconnect.onDisconnect(device.getDeviceId(), 96);
                } else {
                    CarPlayManager.this.mCarPlayReconnect.onDisconnect(device.getDeviceId(), 16);
                }
            }
            int result = CarPlayManager.this.mCarPlayConnect.disconnect(device);
            return result != 0 ? 5 : 0;
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onUsbRoleSwitchFinished(LinkDevice device, boolean success) {
            LogUtil.info("CarPlayManager", "onUsbRoleSwitchFinished device: " + device + " success:" + success);
            CarPlayManager.this.mCarPlayConnect.onRoleSwitchFinished(device, success);
            LogUtil.info("CarPlayManager", "onUsbRoleSwitchFinished device finished");
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onSameDeviceConnect(LinkDevice device, int reason) {
            LogUtil.info("CarPlayManager", "onSameDeviceConnect device :" + device.toString() + " reason:" + reason);
            if (CarPlayManager.this.mCarPlayConnect != null) {
                CarPlayManager.this.mCarPlayConnect.sameDeviceConnect(device, reason);
            }
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onIap2BtDeviceConnected(LinkDevice device, int reason) {
            LogUtil.debug("CarPlayManager", "onIap2BtDeviceConnected device :" + device.toString() + ", reason:" + reason);
            if (CarPlayManager.this.requestIap2CapabilityCheck(device, reason)) {
                CarPlayManager.this.mLinkCapdevice = device;
                CarPlayManager.this.mLinkMergeDevice = device;
                return;
            }
            LogUtil.debug("CarPlayManager", "onIap2BtDeviceConnected cancel request ,device :" + device.toString() + ",reason:" + reason);
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void notifyBtOobPairComplete(String devId, int status, int srvType) {
            CarPlayManager.this.notifyBtOobPairComplete(devId, status, srvType);
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void checkUsbDeviceName(LinkDevice device) {
            LogUtil.debug("CarPlayManager", "checkUsbDeviceName devId" + device);
            CarPlayManager.this.mCarPlayConnect.checkUsbDeviceName(device);
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void disconnectIapSession(String devId) {
            LogUtil.debug("CarPlayManager", "disconnectIapSession devId" + LogEncrypt.MacAddressEncrypt(devId));
            CarPlayManager.this.mCarPlayConnect.disconnectIapSession(devId);
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onProjectionConnectionStateChanged(String devId, int projectionType, int status, int reason) {
            LogUtil.debug("CarPlayManager", "onProjectionConnectionStateChanged devId= " + LogEncrypt.MacAddressEncrypt(devId) + ", projectionType= " + projectionType + ", status= " + status + ", reason= " + reason);
            CarPlayManager.this.mCarPlayReconnect.onProjectionConnectionStateChanged(devId, projectionType, status, reason);
            if (status == 4 && projectionType != 2) {
                CarPlayManager.this.mWifiMacAddress = "";
                LogUtil.debug("CarPlayManager", "set mWifiMacAddress = \"\"");
                synchronized (CarPlayManager.this.mWaitForDisconnectedLock) {
                    CarPlayManager.this.mWaitForDisconnectedLock.notifyAll();
                }
            }
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onWirelessCarPlayUpdate(String devId, boolean available) {
            LogUtil.debug("CarPlayManager", "onWirelessCarPlayUpdate devId" + LogEncrypt.MacAddressEncrypt(devId) + ",available:" + available);
            CarPlayManager.this.mCarPlayReconnect.onWirelessCarPlayUpdate(devId, available);
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onServiceStateChanged(boolean connected) {
            LogUtil.info("CarPlayManager", "onServiceStateChanged DCM connected" + connected);
            if (true == connected) {
                if (CarPlayManager.this.mCarPlayReconnect != null) {
                    CarPlayManager.this.mCarPlayReconnect.onReconnectionReady();
                } else {
                    LogUtil.warning("CarPlayManager", "onServiceStateChanged mCarPlayReconnect is null");
                }
            }
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void onDeviceStatusChanged(CarPlayDevice device) {
            LogUtil.info("CarPlayManager", "onDeviceStatusChanged deviceStatus:" + device.getDeviceStatus() + ",isUserConnected:" + CarPlayManager.this.mLinkAdapter.isUserConnected() + ",isCarplayOldConnection:" + CarPlayManager.this.getIsCarplayOldConnection());
            if (CarPlayManager.this.mCurDeviceStatus != device.getDeviceStatus()) {
                CarPlayManager.this.mCurDeviceStatus = device.getDeviceStatus();
                if (device.getDeviceStatus() == 3) {
                    CarPlayManager.this.mCarPlayManagerListener.onCarPlaySessionStateChanged(1, CarPlayManager.this.getIsCarplayOldConnection(), null, CarPlayManager.this.mLinkAdapter.isUserConnected());
                }
            }
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onCarPlayDeviceStatusChanged(device);
            }
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void oniPhoneAttachedOverUsb(CarPlayDevice device, boolean supported) {
            LogUtil.debug("CarPlayManager", "oniPhoneAttachedOverUsb supported: " + supported + ", device:" + device);
            CarPlayManager.this.mDeviceId = device.getDeviceId();
            CarPlayManager.this.isiPhoneDetachedOverUsb = false;
            LogUtil.debug("CarPlayManager", "oniPhoneAttachedOverUsb mDeviceId:" + LogEncrypt.MacAddressEncrypt(CarPlayManager.this.mDeviceId) + ", isiPhoneDetachedOverUsb:" + CarPlayManager.this.isiPhoneDetachedOverUsb);
            if (CarPlayManager.this.isBluetoothEnabled()) {
                LogUtil.debug("CarPlayManager", "oniPhoneAttachedOverUsb bluetooth is enabled");
                if (CarPlayManager.this.mCarPlayConnect != null) {
                    CarPlayManager.this.mCarPlayConnect.usbDeviceModeConnect(ConnectionManagerModel.toCarPlayDevice(device));
                }
                if (CarPlayManager.this.mCarPlayManagerListener != null) {
                    CarPlayManager.this.mCarPlayManagerListener.oniPhoneAttachedOverUsb(device, supported);
                    return;
                }
                return;
            }
            LogUtil.debug("CarPlayManager", "oniPhoneAttachedOverUsb bluetooth is not enabled");
            if (CarPlayManager.this.enableBluetooth()) {
                LogUtil.debug("CarPlayManager", "oniPhoneAttachedOverUsb enable bluetooth success");
                CarPlayManager.this.iPhoneAttachedRunnable.setConnectDevice(device, supported);
                CarPlayManager.this.mHandler.postDelayed(CarPlayManager.this.iPhoneAttachedRunnable, 3000L);
            }
        }

        @Override // com.ts.carplay.linkadapter.link.ILinkAdapterListener
        public void oniPhoneDetachedOverUsb(CarPlayDevice device) {
            LogUtil.debug("CarPlayManager", "oniPhoneDetachedOverUsb device:" + device);
            CarPlayManager.this.isiPhoneDetachedOverUsb = true;
            CarPlayManager.this.mHandler.removeCallbacks(CarPlayManager.this.iPhoneAttachedRunnable);
            CarPlayManager.this.mCarPlayConnect.usbDeviceModeDisconnect(ConnectionManagerModel.toCarPlayDevice(device));
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.oniPhoneDetachedOverUsb(device);
            }
            if (!"".equals(CarPlayManager.this.mDeviceId) || CarPlayManager.this.mDeviceId != null) {
                CarPlayManager.this.mDeviceId = null;
            }
        }
    }

    private void requestIap2CapabilityCheck0(LinkDevice device, int reason) {
        LogUtil.info("CarPlayManager", "requestIap2CapabilityCheck0,  device:" + device + ", reason:" + reason);
        String mac = device.getBtAddr();
        this.mLock.lock();
        if (!isWirelessCapabilitySupported()) {
            notifyCarPlayCapbility(mac, false, 0);
        } else {
            int result = this.mCarPlayConnect.getCarplayCapbility(device, reason);
            LogUtil.info("CarPlayManager", "requestIap2CapabilityCheck0, getCarplayCapbility:" + result);
            if (result == -1) {
                notifyCarPlayCapbility(mac, false, 1);
            }
        }
        this.mLock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean requestIap2CapabilityCheck(LinkDevice device, int reason) {
        LogUtil.info("CarPlayManager", "requestIap2CapabilityCheck device: " + device + ", reason=" + reason);
        if (device == null) {
            return false;
        }
        String mac = device.getBtAddr();
        if (mac == null) {
            LogUtil.warning("CarPlayManager", "requestIap2CapabilityCheck device is invalid");
            return false;
        }
        if (reason == 3) {
            this.mLock.lock();
            this.mCarPlayConnect.closeRfSocketConnected();
            this.mLock.unlock();
            return false;
        }
        synchronized (this) {
            if (!this.iap2CheckRequests.isEmpty()) {
                LogUtil.info("CarPlayManager", "requestIap2CapabilityCheck, iap2CheckRequests is not empty!!!");
                return true;
            }
            Iap2CheckRequest request = new Iap2CheckRequest();
            request.mac = mac;
            request.reason = reason;
            this.iap2CheckRequests.add(request);
            requestIap2CapabilityCheck0(device, reason);
            return true;
        }
    }

    private boolean setCarPlaySession() {
        CarplayStartSessionInfo.WirelessAttribute wirelessAttribute = new CarplayStartSessionInfo.WirelessAttribute();
        if (this.mStartSessionInfo == null) {
            return false;
        }
        CarPlayIapSessionManager carPlayIapSessionManager = this.mIapSessionManager;
        if (carPlayIapSessionManager != null) {
            String ipAddress = carPlayIapSessionManager.getIpAddress();
            if (ipAddress == null) {
                LogUtil.info("CarPlayManager", "ipAddress is null.");
                return false;
            }
            wirelessAttribute.setIpAddress(ipAddress);
            wirelessAttribute.setWifiSsid(this.mIapSessionManager.getSsid());
            wirelessAttribute.setPassphrase(this.mIapSessionManager.getPassphrase());
            wirelessAttribute.setChannel(this.mIapSessionManager.getChannel());
            int securityType = this.mIapSessionManager.getSecurity();
            if (securityType < 3) {
                LogUtil.info("CarPlayManager", "security type invaild.");
            }
            wirelessAttribute.setSecurityType(securityType);
            this.mStartSessionInfo.setDeviceIdentifier(this.mIapSessionManager.getMacAddress());
        }
        LogUtil.info("CarPlayManager", "wireless carplay new connection.");
        this.mStartSessionInfo.setWirelessAttribute(wirelessAttribute);
        return true;
    }

    private class IapAdapterListenerImpl implements IIapAdapterListener {
        private IapAdapterListenerImpl() {
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onIapSessionStatusChanged(int clientType, IapSessionStatus status) {
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    listener.onIapSessionStatusChanged(status);
                    return;
                }
                LogUtil.warning("CarPlayManager", "onIapSessionStatusChanged listener null. type:" + clientType);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onDeviceInformationUpdate(int clientType, String deviceName) {
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    listener.onDeviceInformationUpdate(deviceName);
                    return;
                }
                LogUtil.warning("CarPlayManager", "onDeviceInformationUpdate listener null. type:" + clientType);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onDeviceTransportIdentifierNotification(int clientType, String btTransportIdentifier, String usbTransportIdentifier) {
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    listener.onDeviceTransportIdentifierNotification(btTransportIdentifier, usbTransportIdentifier);
                    return;
                }
                LogUtil.warning("CarPlayManager", "onDeviceTransportIdentifierNotification listener null. type:" + clientType);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onRequestAccessoryWiFiConfigurationInformation(int clientType) {
            CarPlayManager.this.mIsCarPlayOldConnection = true;
            LogUtil.debug("CarPlayManager", "onRequestAccessoryWiFiConfigurationInformation:mIsCarPlayOldConnection = " + CarPlayManager.this.mIsCarPlayOldConnection);
            CarPlayManager.this.mCarPlayConnect.onRequestAccessoryWiFiConfigurationInformation(clientType);
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onWirelessCarPlayUpdate(int clientType, boolean wirelessCarPlayAvailable) {
            LogUtil.debug("CarPlayManager", "onWirelessCarPlayUpdate, clientType:" + clientType);
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    listener.onWirelessCarPlayUpdate(wirelessCarPlayAvailable);
                } else {
                    LogUtil.warning("CarPlayManager", "onWirelessCarPlayUpdate listener null. type:" + clientType);
                }
                if (CarPlayManager.this.mCarPlayConnect.isWiFiActived() && !wirelessCarPlayAvailable && clientType == 19) {
                    LogUtil.debug("CarPlayManager", "onWirelessCarPlayUpdate, onDisconnect capability close");
                    CarPlayManager.this.mCarPlayReconnect.onDisconnect(CarPlayManager.this.mLinkDevice.getDeviceId(), 96);
                }
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onStartLocationInformation(int clientType, int sentenceType) {
            if (CarPlayManager.this.mLocationAdapter != null) {
                CarPlayManager.this.mLocationAdapter.registerListener(CarPlayManager.this.mLocationAdapterListener);
            }
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    listener.onStartLocationInformation(sentenceType);
                    return;
                }
                LogUtil.warning("CarPlayManager", "onStartLocationInformation listener null. type:" + clientType);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onStopLocationInformation(int clientType) {
            if (CarPlayManager.this.mLocationAdapter != null) {
                CarPlayManager.this.mLocationAdapter.unregisterListener(CarPlayManager.this.mLocationAdapterListener);
            }
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    listener.onStopLocationInformation();
                    return;
                }
                LogUtil.warning("CarPlayManager", "onStopLocationInformation listener null. type:" + clientType);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onRouteGuidanceUpdate(RouteGuidanceInfo info) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onRouteGuidanceUpdate(info);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onCallStateUpdate(CallStateInfo info) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onCallStateUpdate(info);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onRecentListCount(int listCount) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onRecentListCount(listCount);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onRecentListUpdate(List<RecentContactListInfo> list) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onRecentListUpdate(list);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onFavoriteListCount(int listCount) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onFavoriteListCount(listCount);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onFavoriteListUpdate(List<FavoriteContactListInfo> list) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onFavoriteListUpdate(list);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onNowPlayingUpdate(NowPlayingInfo info) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onNowPlayingUpdate(info);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onArtworkUpdate(NowPlayingInfo info) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onArtworkUpdate(info);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onGprmcDataStatus(int gprmcData) {
            if (CarPlayManager.this.mLocationAdapter != null) {
                CarPlayManager.this.mLocationAdapter.setMapCoordinateSystem(gprmcData);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onPlaybackListUpdate(int mediaItemCount, int mediaItemStartIndex, List<PlaybackMediaItemInfo> mediaItemList) {
            if (CarPlayManager.this.mCarPlayManagerListener != null) {
                CarPlayManager.this.mCarPlayManagerListener.onPlaybackListUpdate(mediaItemCount, mediaItemStartIndex, mediaItemList);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onBtA2dpConnect(boolean connected) {
            CarPlayManager.this.mIsBtA2dpConnected = connected;
            if (CarPlayManager.this.mIsNeedRequestMedia && !connected) {
                if (CarPlayManager.this.mAudioAdapter != null) {
                    LogUtil.info("CarPlayManager", "onBtA2dpConnect mIsNeedRequestMedia");
                    CarPlayManager.this.requestAudioFocus(0);
                }
                CarPlayManager.this.mIsNeedRequestMedia = false;
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public void onCarplayAvialability(int clientType, WiredAttributes wired, WirelessAttributes wireless) {
            LogUtil.debug("CarPlayManager", "onCarplayAvialability clientType:" + clientType + " " + wired + " " + wireless);
            CarPlayManager.this.mIsCarPlayConnection = true;
            if (wireless != null && wired != null) {
                onDeviceTransportIdentifierNotification(clientType, wireless.getBluetoothTransportIdentifier(), wired.getUsbTransportIdentifier());
            }
            if (17 == clientType) {
                CarPlayManager.this.mCarPlayConnect.onWiredAttributesChanged(wired);
                return;
            }
            if (18 == clientType) {
                if (wireless == null) {
                    LogUtil.warning("CarPlayManager", "onCarplayAvialability clientType:" + clientType + " wireless is null");
                    return;
                }
                CarPlayManager.this.mCarPlayReconnect.onCarplayAvialability(CarPlayManager.this.mLinkAdapter.getConnectionDeviceId(wireless.getBluetoothTransportIdentifier()), wireless.isWirelessAvailable());
                onWirelessCarPlayUpdate(clientType, wireless.isWirelessAvailable());
                CarPlayManager.this.mCarPlayConnect.onWirelessAttributesChanged(wireless);
            }
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public int onStartBluetoothPairing(int clientType, short btTransComId, byte[] macAddress) {
            CarPlayManager carPlayManager;
            CarPlayDevice device;
            LogUtil.debug("CarPlayManager", "onStartBluetoothPairing clientType: " + clientType + ", btTransComId: " + ((int) btTransComId) + ", macAddress: " + LogEncrypt.MacAddressEncrypt(String.valueOf(macAddress)));
            if (!"".equals(CarPlayManager.this.mDeviceId) && CarPlayManager.this.mDeviceId != null && macAddress != null && (device = (carPlayManager = CarPlayManager.this).getCarPlayAidlDevice(carPlayManager.mDeviceId)) != null) {
                int sessionStatus = device.getDeviceStatus();
                LogUtil.debug("CarPlayManager", "onStartBluetoothPairing sessionStatus: " + sessionStatus + " macAddress length = " + macAddress.length);
                if (sessionStatus != 4) {
                    CarPlayManager.this.onCarPlayOobStartEvent(macAddress);
                }
            } else {
                CarPlayManager.this.onCarPlayOobStartEvent(macAddress);
            }
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    return listener.onStartBluetoothPairing(btTransComId, macAddress);
                }
                LogUtil.warning("CarPlayManager", "onStartBluetoothPairing listener null. type:" + clientType);
                return -1;
            }
            return -1;
        }

        @Override // com.ts.carplay.linkadapter.iap.IIapAdapterListener
        public int onStopBluetoothPairing(int clientType, short btTransComId, int state) {
            LogUtil.debug("CarPlayManager", "onStopBluetoothPairing clientType: " + clientType + ", btTransComId: " + ((int) btTransComId) + ", state: " + state);
            synchronized (CarPlayManager.this.mWaitStopOob) {
                CarPlayManager.this.mWaitStopOob.notifyAll();
            }
            if (CarPlayManager.this.mIapSessionManager != null) {
                CarPlayIapListener listener = CarPlayManager.this.mIapSessionManager.getCarPlayIapListener(clientType);
                if (listener != null) {
                    return listener.onStopBluetoothPairing(btTransComId, state);
                }
                LogUtil.warning("CarPlayManager", "onStopBluetoothPairing listener null. type:" + clientType);
                return -1;
            }
            return -1;
        }
    }

    private class AudioAdapterListenerImpl implements IAudioAdapter.IAudioAdapterListener {
        private AudioAdapterListenerImpl() {
        }

        @Override // com.ts.carplay.adapter.audio.IAudioAdapter.IAudioAdapterListener
        public void onAudioFocusChanged(int priority) {
            LogUtil.info("CarPlayManager", "AudioManagerImpl onAudioFocusChanged priority: " + priority);
            if (priority == 1) {
                CarPlayManager carPlayManager = CarPlayManager.this;
                carPlayManager.onCarPlayMediaFocusChange(carPlayManager.mAudioResourceManager.getCurrentCpFocus() != 7);
            } else if (priority != 4 && priority != 7) {
                CarPlayManager.this.onCarPlayMediaFocusChange(false);
            }
            CarPlayManager.this.mCarPlayResourceManager.accessoryAudioChange(priority, "");
        }

        @Override // com.ts.carplay.adapter.audio.IAudioAdapter.IAudioAdapterListener
        public void onAudioFocusChanged(int priority, String borrowId) {
            LogUtil.info("CarPlayManager", "AudioManagerImpl onAudioFocusChanged priority: " + priority + "borrowId" + borrowId);
            CarPlayManager.this.onCarPlayMediaFocusChange(false);
            CarPlayManager.this.mCarPlayResourceManager.accessoryAudioChange(priority, borrowId);
        }
    }

    private class PowerAdapterListenerImpl implements IPowerAdapter.IPowerAdapterListener {
        private PowerAdapterListenerImpl() {
        }

        @Override // com.ts.carplay.adapter.power.IPowerAdapter.IPowerAdapterListener
        public void onAccStatusChanged(int status) {
            LogUtil.info("CarPlayManager", "onAccStatusChanged priostatusrity: " + status);
            if (status == 4) {
                LogUtil.info("CarPlayManager", "onAccStatusChanged ACCOFF disconnect carplay and turn off AP");
                if (CarPlayManager.this.mCarPlayReconnect != null) {
                    if (CarPlayManager.this.mCarPlayConnect != null && CarPlayManager.this.mCarPlayConnect.isWiFiActived()) {
                        LogUtil.info("CarPlayManager", "onAccStatusChanged CarPlay is actived while acc off, disconnect it");
                        CarPlayManager.this.mHasAccOff = true;
                        if (CarPlayManager.this.mLinkDevice != null) {
                            CarPlayManager.this.mCarPlayReconnect.onDisconnect(CarPlayManager.this.mLinkDevice.getDeviceId(), 80);
                        }
                        synchronized (CarPlayManager.this.mWaitForDisconnectedLock) {
                            CarPlayManager.this.mCarPlayConnect.disconnect(CarPlayManager.this.mLinkDevice);
                            try {
                                CarPlayManager.this.mWaitForDisconnectedLock.wait(10000L);
                            } catch (InterruptedException exception) {
                                exception.printStackTrace();
                            }
                        }
                        if (CarPlayManager.this.mWifiManager != null) {
                            CarPlayManager.this.mWifiManager.stopSoftAp();
                        } else {
                            LogUtil.warning("CarPlayManager", "stopSoftAp while onAccStatusChanged, mWifiManager is null");
                        }
                    }
                    CarPlayManager.this.mCarPlayReconnect.onAccessoryTurnOff();
                    return;
                }
                LogUtil.info("CarPlayManager", "onAccStatusChanged ACCOFF carplay is not active");
                return;
            }
            if (status == 10 && CarPlayManager.this.mHasAccOff) {
                CarPlayManager.this.mHasAccOff = false;
            }
        }
    }

    private class PowerStrAdapterListenerImpl implements IPowerStrAdapter.IPowerStrAdapterListener {
        private PowerStrAdapterListenerImpl() {
        }

        @Override // com.ts.carplay.adapter.powerSTR.IPowerStrAdapter.IPowerStrAdapterListener
        public void onPowerStrChanged(int status) {
            LogUtil.info("CarPlayManager", "onPowerStrChanged status: " + status);
            if (status == 19) {
                LogUtil.info("CarPlayManager", "onPowerStrChanged STR disconnect carplay and turn off AP");
                if (CarPlayManager.this.mCarPlayReconnect != null) {
                    if (CarPlayManager.this.mCarPlayConnect != null && CarPlayManager.this.mCarPlayConnect.isWiFiActived()) {
                        LogUtil.info("CarPlayManager", "onPowerStrChanged CarPlay is actived while STR, disconnect it");
                        if (CarPlayManager.this.mLinkDevice != null) {
                            CarPlayManager.this.mCarPlayReconnect.onDisconnect(CarPlayManager.this.mLinkDevice.getDeviceId(), 80);
                        }
                        synchronized (CarPlayManager.this.mWaitForDisconnectedLock) {
                            CarPlayManager.this.mCarPlayConnect.disconnect(CarPlayManager.this.mLinkDevice);
                            try {
                                CarPlayManager.this.mWaitForDisconnectedLock.wait(10000L);
                            } catch (InterruptedException exception) {
                                exception.printStackTrace();
                            }
                        }
                        if (CarPlayManager.this.mWifiManager != null) {
                            CarPlayManager.this.mWifiManager.stopSoftAp();
                        } else {
                            LogUtil.warning("CarPlayManager", "stopSoftAp while onPowerStrChanged, mWifiManager is null");
                        }
                    }
                    CarPlayManager.this.mCarPlayReconnect.onAccessoryTurnOff();
                    return;
                }
                LogUtil.info("CarPlayManager", "onPowerStrChanged STR carplay is not active");
                return;
            }
            if (status == 16) {
                LogUtil.info("CarPlayManager", "onPowerStrChanged POWER_STR_SUSPEND_EXIT");
            }
        }
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothUuidListener
    public void onReceiveUuid(BluetoothDevice device) {
        ICarPlayCapabilityListener listener = this.deviceCarPlayCapabilityListeners.get(device.getAddress());
        LogUtil.info("CarPlayManager", "onReceiveUuid listener: " + listener);
        checkCarPlayCapability(device.getUuids(), device.getAddress(), listener);
    }

    private class BluetoothManagerListenerImpl implements BluetoothManager.IBluetoothManagerListener {
        private BluetoothManagerListenerImpl() {
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onLocalBluetoothStateChanged(int state) {
            CarPlayManager.this.btStateChangedTo(state);
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onLocalSppPeerConnected(String uuid, int handle, String address) {
            CarPlayManager.this.peerConnected(uuid, handle, address);
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onLocalBluetoothProfileStateChanged(String address, int profile, int newState, int oldState) {
            if (CarPlayManager.this.mCarPlayReconnect != null) {
                CarPlayManager.this.mCarPlayReconnect.onBtProfileStateChanged(address, profile, newState);
            }
            CarPlayManager.this.bluetoothProfileStateChanged(address, profile, newState, oldState);
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onBtPairingChanged(String address, boolean isPaired) {
            if (CarPlayManager.this.mCarPlayReconnect != null && isPaired && !CarPlayManager.this.mCarPlayConnect.isWiFiActived()) {
                CarPlayManager.this.mCarPlayReconnect.onBtPairStateChanged(address, isPaired);
            }
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onSdpRecorder(String address) {
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onUuid(String address) {
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onSdpServerUuidChanged(String uuid) {
            if (CarPlayManager.this.mOobManager != null) {
                CarPlayManager.this.mOobManager.onSdpServerUuidChanged(uuid);
            }
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
        public void onReceiveOobData(boolean success) {
            if (CarPlayManager.this.mOobManager != null) {
                CarPlayManager.this.mOobManager.onReceiveOobData(success);
            }
        }
    }

    public void updateWifiApInfoChanged(AccessoryWiFiInfo config, int status) {
        LogUtil.info("CarPlayManager", "onWifiApInfo, config = " + config);
        boolean open = false;
        if (status == 13) {
            if (config != null && this.mCarPlayStack != null) {
                this.mWifiInterface = config.getInterfaceName();
                this.mCarPlayStack.setAccessoryWifiApAddr(config.getMacAddress());
                config.setIpAddress(this.mNetworkManagement.getWifiApIpAddress(config));
            }
            CarPlayIapSessionManager carPlayIapSessionManager = this.mIapSessionManager;
            if (carPlayIapSessionManager != null) {
                carPlayIapSessionManager.setWifiConfiguration(config);
            }
            open = true;
            this.mCarPlayConnect.checkCarPlayStartSession();
        } else if (status == 11) {
            open = false;
            CarPlayIapSessionManager carPlayIapSessionManager2 = this.mIapSessionManager;
            if (carPlayIapSessionManager2 != null) {
                carPlayIapSessionManager2.clearWifiConfiguration();
            }
        }
        CarPlayReconnect carPlayReconnect = this.mCarPlayReconnect;
        if (carPlayReconnect != null) {
            carPlayReconnect.onWifiApStateChanged(open);
        }
    }

    private class WifiAdapterListenerImpl implements IWifiAdapterListener {
        private WifiAdapterListenerImpl() {
        }

        @Override // com.ts.carplay.adapter.wifi.IWifiAdapterListener
        public void onWifiConnectStatus(String mac, int status) {
            LogUtil.debug("CarPlayManager", "onWifiConnectStatus, mac = " + LogEncrypt.MacAddressEncrypt(mac) + ", status = " + status);
            if (!TextUtils.isEmpty(mac)) {
                if (1 == status || 2 == status) {
                    if (!TextUtils.isEmpty(CarPlayManager.this.mWifiMacAddress)) {
                        String wifiMac = mac.toUpperCase(Locale.US);
                        String wifiMacCarplay = CarPlayManager.this.mWifiMacAddress.toUpperCase(Locale.US);
                        if (CarPlayManager.this.mCarPlayConnect != null && wifiMacCarplay.equals(wifiMac) && CarPlayManager.this.mCarPlayConnect.isHasWirelessConn()) {
                            LogUtil.info("CarPlayManager", "Received the wifi disconnect, goto disconnected");
                            if (CarPlayManager.this.mCarPlayReconnect != null && CarPlayManager.this.mLinkDevice != null) {
                                CarPlayManager.this.mCarPlayReconnect.onDisconnect(CarPlayManager.this.mLinkDevice.getDeviceId(), 32);
                            }
                            CarPlayManager.this.mCarPlayConnect.disconnect(CarPlayManager.this.mLinkDevice);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (status == 0 && CarPlayManager.this.mCarPlayReconnect != null) {
                    CarPlayManager.this.mCarPlayReconnect.onWifiDeviceAttach(mac);
                }
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean connectRfcommSocket(String btMac) {
        LogUtil.debug("CarPlayManager", "connectRfcommSocket btMac: " + LogEncrypt.MacAddressEncrypt(btMac));
        if (btMac == null) {
            return false;
        }
        this.mLock.lock();
        boolean ret = this.mCarPlayConnect.onSppRetryConnected(btMac);
        this.mLock.unlock();
        return ret;
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean setBluetoothAutoConnectEnabled(boolean enabled, String address, boolean reconnState) {
        BluetoothManager bluetoothManager;
        LogUtil.debug("CarPlayManager", "setBluetoothAutoConnectEnabled enabled: " + enabled + " address: " + LogEncrypt.MacAddressEncrypt(address) + " reconnState: " + reconnState);
        if (reconnState && (bluetoothManager = this.mBluetoothManager) != null) {
            bluetoothManager.setBluetoothCoexistence(address, true);
        }
        IBluetoothAdapter iBluetoothAdapter = this.mBluetoothAdapter;
        if (iBluetoothAdapter == null) {
            LogUtil.warning("CarPlayManager", "setBluetoothAutoConnectEnabled mBluetoothManager is null.");
            return false;
        }
        return iBluetoothAdapter.setBluetoothAutoConnectEnabled(enabled, address, reconnState);
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean reconnectWirelessCarPlay(String id) {
        CarPlayStack carPlayStack = this.mCarPlayStack;
        if (carPlayStack != null) {
            carPlayStack.setWireConnection(false);
        }
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null) {
            return false;
        }
        boolean ret = iLinkAdapter.reconnectWirelessCarPlay(id);
        return ret;
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean confirmWirelessCarPlayAvailable(String id) {
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null) {
            return false;
        }
        boolean ret = iLinkAdapter.confirmWirelessCarPlayAvailable(id);
        return ret;
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean isWifiApEnabled() {
        CarPlayWifiManager carPlayWifiManager = this.mWifiManager;
        if (carPlayWifiManager == null) {
            LogUtil.warning("CarPlayManager", "isWifiApEnabled mWifiAdapter is null.");
            return false;
        }
        return carPlayWifiManager.isWifiApEnabled();
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean isBluetoothEnabled() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "isBluetoothEnabled mBluetoothAdapter is null.");
            return false;
        }
        return bluetoothManager.isBluetoothEnabled();
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean enableBluetooth() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "enableBluetooth mBluetoothManager is null.");
            return false;
        }
        return bluetoothManager.enableBluetooth();
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public Set<String> getBondedDevices() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "getBondedDevices mBluetoothAdapter is null.");
            return null;
        }
        return bluetoothManager.getBondedDevices();
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean connectBtClassicProfile(String mac) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "connectBtClassicProfile mBluetoothAdapter is null.");
            return false;
        }
        return bluetoothManager.connectBtClassicProfile(mac);
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean isWifiActived() {
        CarPlayConnect carPlayConnect = this.mCarPlayConnect;
        if (carPlayConnect == null) {
            LogUtil.warning("CarPlayManager", "isWifiActived mCarPlayConnect is null.");
            return false;
        }
        return carPlayConnect.isWiFiActived();
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean startSoftAp() {
        CarPlayWifiManager carPlayWifiManager = this.mWifiManager;
        if (carPlayWifiManager != null) {
            boolean ret = carPlayWifiManager.startSoftAp(null);
            return ret;
        }
        LogUtil.warning("CarPlayManager", "startSoftAp, mWifiManager is null");
        return false;
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionDependence
    public boolean isConnected(String mac) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "isConnected mBluetoothAdapter is null.");
            return false;
        }
        return bluetoothManager.isConnected(mac);
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManager
    public CarPlayDevice getCarPlayAidlDevice(String devId) {
        ILinkAdapter iLinkAdapter = this.mLinkAdapter;
        if (iLinkAdapter == null) {
            return null;
        }
        return iLinkAdapter.getCarPlayAidlDevice(devId);
    }

    public boolean getLocalOobData() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            boolean ret = bluetoothManager.getLocalOobData();
            LogUtil.debug("CarPlayManager", "getLocalOobData:" + ret);
            return ret;
        }
        LogUtil.warning("CarPlayManager", "getLocalOobData mBluetoothManager is null");
        return false;
    }

    public byte[][] getPairingDataP192() {
        LogUtil.debug("CarPlayManager", "getPairingDataP192.");
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "getPairingDataP192 mBluetoothManagerListener is null");
            return null;
        }
        return bluetoothManager.getPairingDataP192();
    }

    public byte[][] getPairingDataP256() {
        LogUtil.debug("CarPlayManager", "getPairingDataP256.");
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.warning("CarPlayManager", "getPairingDataP256 mBluetoothManagerListener is null");
            return null;
        }
        return bluetoothManager.getPairingDataP256();
    }

    public int onSendBluetoothPairingAccessoryInformation(int clientType, short btTransComId, byte[][] pairingDataP192, byte[][] pairingDataP256) {
        LogUtil.info("CarPlayManager", "sendBluetoothPairingAccessoryInformation clientType " + clientType);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.sendBluetoothPairingAccessoryInformation(clientType, btTransComId, pairingDataP192, pairingDataP256);
        }
        return -1;
    }

    public int onSendBluetoothPairingStatus(int clientType, short btTransComId, boolean success, String reason) {
        LogUtil.debug("CarPlayManager", "onSendBluetoothPairingStatus clientType " + clientType + ", btTransComId: " + ((int) btTransComId) + ", success: " + success + ", reason: " + reason);
        IIapAdapter iIapAdapter = this.mIapAdapter;
        if (iIapAdapter != null) {
            return iIapAdapter.sendBluetoothPairingStatus(clientType, btTransComId, success, reason);
        }
        return -1;
    }

    public void onCarPlayOobStartEvent(byte[] macAddress) {
        LogUtil.debug("CarPlayManager", "onCarPlayOobStartEvent mac: " + LogEncrypt.MacAddressEncrypt(String.valueOf(macAddress)));
        ICarPlayManagerListener iCarPlayManagerListener = this.mCarPlayManagerListener;
        if (iCarPlayManagerListener != null) {
            iCarPlayManagerListener.onCarPlayOobStartEvent(macAddress);
        }
    }

    private class CpBroadcastReceiver extends BroadcastReceiver {
        private CpBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(CarPlayManager.ACTION_SHUTDOWN)) {
                LogUtil.info("CarPlayManager", "ACTION_SHUTDOWN, turn off start");
                if (CarPlayManager.this.mCarPlayReconnect != null) {
                    if (CarPlayManager.this.mCarPlayConnect != null && CarPlayManager.this.mCarPlayConnect.isWiFiActived()) {
                        LogUtil.info("CarPlayManager", "Wireless CarPlay is actived while power off, disconnect it");
                        if (CarPlayManager.this.mLinkDevice != null) {
                            CarPlayManager.this.mCarPlayReconnect.onDisconnect(CarPlayManager.this.mLinkDevice.getDeviceId(), 80);
                        }
                        synchronized (CarPlayManager.this.mWaitForDisconnectedLock) {
                            CarPlayManager.this.mCarPlayConnect.disconnect(CarPlayManager.this.mLinkDevice);
                            try {
                                CarPlayManager.this.mWaitForDisconnectedLock.wait(10000L);
                            } catch (InterruptedException exception) {
                                exception.printStackTrace();
                            }
                        }
                        if (CarPlayManager.this.mWifiManager != null) {
                            CarPlayManager.this.mWifiManager.stopSoftAp();
                        } else {
                            LogUtil.warning("CarPlayManager", "stopSoftAp while ACTION_SHUTDOWN, mLinkAdapter is null");
                        }
                    }
                    CarPlayManager.this.mCarPlayReconnect.onAccessoryTurnOff();
                }
                LogUtil.info("CarPlayManager", "ACTION_SHUTDOWN, turn off end");
            }
        }
    }

    public void setInterfaceName(String interfaceName) {
        try {
            Method set = SystemProperties.class.getDeclaredMethod("set", String.class, String.class);
            set.invoke(SystemProperties.class, KEY_INTERFACE_NAME, interfaceName);
            LogUtil.debug("CarPlayManager", "setInterfaceName succeeded");
        } catch (Exception ex) {
            LogUtil.warning("CarPlayManager", "setInterfaceName failed");
            ex.printStackTrace();
        }
    }

    public void setInterfaceRoute(String action) {
        try {
            Method set = SystemProperties.class.getDeclaredMethod("set", String.class, String.class);
            set.invoke(SystemProperties.class, KEY_INTERFACE_ACTION, action);
            LogUtil.debug("CarPlayManager", "setInterfaceRoute succeeded");
        } catch (Exception ex) {
            LogUtil.warning("CarPlayManager", "setInterfaceRoute failed");
            ex.printStackTrace();
        }
    }
}
