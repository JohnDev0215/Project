package com.ts.carplay.lib.ts;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.android.carplay.AudioResource;
import com.ts.android.carplay.CarPlay;
import com.ts.android.carplay.CarPlayClient;
import com.ts.android.carplay.CarPlayDelegate;
import com.ts.android.carplay.CarPlayInputManager;
import com.ts.android.carplay.CarPlayKnob;
import com.ts.android.carplay.CarPlayPlayKey;
import com.ts.android.carplay.CarPlayResourceManager;
import com.ts.android.carplay.CarPlayTelephoneKey;
import com.ts.android.carplay.CarPlayTouchScreen;
import com.ts.android.carplay.CommandCompletionCallback;
import com.ts.android.carplay.InfoMsgId;
import com.ts.android.carplay.ScreenResource;
import com.ts.carplay.common.data.config.ButtonInfo;
import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.common.data.config.SafeArea;
import com.ts.carplay.common.data.config.ScreenInfo;
import com.ts.carplay.common.data.config.ViewArea;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CarPlayResourceStateChange;
import com.ts.carplay.lib.common.CarPlayStartInfo;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.SurfaceData;
import com.ts.carplay.lib.connect.CarPlayConnect;
import com.ts.carplay.lib.resourcemanager.CarPlayLastUserMode;
import com.ts.carplay.lib.transport.CarPlayTransport;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayStack {
    private static final int BIT_MASK_0XFF = 255;
    private static final byte BYTE_0X00 = 0;
    private static final byte BYTE_0X01 = 1;
    private static final byte BYTE_0X02 = 2;
    private static final byte BYTE_0X05 = 5;
    private static final byte BYTE_0X06 = 6;
    private static final byte BYTE_0X08 = 8;
    private static final byte BYTE_0X09 = 9;
    private static final byte BYTE_0X15 = 21;
    private static final byte BYTE_0X25 = 37;
    private static final byte BYTE_0X38 = 56;
    private static final byte BYTE_0X75 = 117;
    private static final byte BYTE_0X7F = 127;
    private static final byte BYTE_0X81 = -127;
    private static final byte BYTE_0X95 = -107;
    private static final byte BYTE_0XA1 = -95;
    private static final byte BYTE_0XC0 = -64;
    private static final int CARPLAY_TIMEOUT = 1000;
    public static final int CARPLAY_TRANSPORT_WIFI = 2;
    private static final int COUNTRY_CODE = 33;
    private static final String DEFAULT_BLUETOOTH_ID = "b6:53:2d:67:39:26";
    private static final int DEFAULT_CLUSTER_DISPLAY_HEIGHT = 372;
    private static final int DEFAULT_CLUSTER_DISPLAY_HEIGHT_OFFECT = 226;
    private static final int DEFAULT_CLUSTER_DISPLAY_WIDTH = 660;
    private static final int DEFAULT_CLUSTER_DISPLAY_WIDTH_OFFECT = 628;
    private static final int DEFAULT_HEIGHT_PHYSICAL = 159;
    private static final int DEFAULT_HEIGHT_PIXELS = 1080;
    private static final int DEFAULT_MAX_FPS = 30;
    private static final int DEFAULT_VALUE = 0;
    private static final int DEFAULT_WIDTH_PHYSICAL = 283;
    private static final int DEFAULT_WIDTH_PIXELS = 1920;
    private static final int FEATURE_INCLUDE_VALUE_FALSE = 0;
    private static final int FEATURE_INCLUDE_VALUE_TRUE = 1;
    private static final int FEATURE_NOT_INCLUDE = -1;
    private static final int HEXA_BASE = 16;
    private static final int INFO_MSG_SCREEN_TYPE_ALT1 = 111;
    private static final int INFO_MSG_SCREEN_TYPE_MAIN = 110;
    private static final int INPUT_DEVICE_TYPE = 1;
    private static final int LIMITED_UI_INFO = 31;
    private static final int MAC_ADDRESS_LEN = 6;
    private static final int MAX_XY_DEFINE_FOR_KNOB = 127;
    private static final int MIN_XY_DEFINE_FOR_KNOB = -127;
    private static final int MSG_NUMBER = 3;
    private static final int NUM_OF_RETRY = 10;
    private static final int OEM_ICON_104_SIZE = 104;
    private static final int OEM_ICON_120_SIZE = 120;
    private static final int OEM_ICON_156_SIZE = 156;
    private static final int OEM_ICON_180_SIZE = 180;
    private static final int OEM_ICON_256_SIZE = 256;
    private static final int OEM_ICON_SIZE = 104;
    private static final int PRODUCT_ID = 2;
    private static final int SCREEN_AUTOMATIC_SHOW = 1;
    private static final int SCREEN_NOT_AUTOMATIC_SHOW = 0;
    private static final int SCREEN_TYPE_ALT1 = 1;
    private static final int SCREEN_TYPE_ALT2 = 2;
    private static final int SCREEN_TYPE_MAIN = 0;
    private static final int SLEEP_TIME_1500 = 1500;
    private static final int SLEEP_TIME_200 = 200;
    private static final int SLEEP_TIME_300 = 300;
    private static final int SLEEP_TIME_800 = 800;
    private static final int SURFACE_STATE_HIDE = 3;
    private static final int SURFACE_STATE_NEED_RELAUNCH = 4;
    private static final int SURFACE_STATE_SHOW = 1;
    private static final int SURFACE_STATE_START_RELEASE = 2;
    private static final String TAG = "CarPlayStack";
    private static final int VENDOR_ID = 8;
    private static final int WAIT_TIMEOUT = 100;
    private String mAccessoryBtAddr;
    private CarPlayManager mManager;
    private CarPlayStackEventHandler mStackEventHandler;
    static CarPlayStack sCarPlayStack = null;
    private static final String DEFAULT_UUID_STR = "e5f7a68d-7b0f-4305-984b-974f677a150b";
    private static final String UTF_8 = "UTF-8";
    private static final byte[] DEFAULT_UUID = DEFAULT_UUID_STR.getBytes(Charset.forName(UTF_8));
    public static final int DEVICE_MASK = (CarPlay.BIT(1) | CarPlay.BIT(4)) | CarPlay.BIT(3);
    private static int sLogicalXMin = 0;
    private static int sLogicalXMax = 0;
    private static int sLogicalYMin = 0;
    private static int sLogicalYMax = 0;
    private static int sMaxAlternatives = 0;
    private boolean mIsSupportViewArea = false;
    private boolean mIsOemIconPreRender = true;
    private CarPlayConfig mCarPlayConfig = null;
    private CarPlay.InfoMsg mInfoMsg = new CarPlay.InfoMsg();
    private CarPlay.EnhancedSiriInfo mEnhancedSiriInfo = null;
    private Surface[] mSurfaces = new Surface[2];
    private boolean mIsSetUpRequested = false;
    private boolean mIsSetUpAltRequested = false;
    private boolean mIsSupportMap = false;
    private Object mSurfaceLock = new Object();
    private HashMap<Integer, CarPlayClient> mTsCarPlayClients = new HashMap<>();
    private CarPlayInputManager mInputManager = null;
    private CarPlayResourceManager mResourceManager = null;
    private CarPlayTouchScreen mTouchScreen = null;
    private CarPlayPlayKey mPlayKey = null;
    private CarPlayTelephoneKey mTelKey = null;
    private CarPlayKnob mCarPlayKnob = null;
    private CarPlay mTsCarPlay = null;
    private CarPlayClient mTsCarPlayClient = null;
    private boolean isStarted = false;
    private String mBtCarPlayAddr = null;
    private boolean screenInTaken = false;
    private volatile int mSupportFeatures = 0;
    private String mAccessoryWifiApAddr = null;
    private int mAuxInUpdateState = 0;
    private boolean mWireConnection = true;
    private int mIsScreenWillShow = 0;
    private boolean isCarPlayConnect = false;
    protected byte[] knobRight = {0, 1};
    protected byte[] knobLeft = {0, -1};
    protected byte[] knobSelectDown = {1, 0};
    protected byte[] knobSelectUp = {0, 0};
    private WorkerThread mHandlerThread = new WorkerThread("CarPlayHandlerThread");
    private Handler mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.carplay.lib.ts.CarPlayStack.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.info(CarPlayStack.TAG, "Handler processing Message ..");
        }
    };
    private CommandCompletionCallback mOnAppLaunched = new CommandCompletionCallback() { // from class: com.ts.carplay.lib.ts.CarPlayStack.2
        @Override // com.ts.android.carplay.CommandCompletionCallback
        public void onComplete(Object userContext, int status) {
            LogUtil.info(CarPlayStack.TAG, "AppLauched successfully");
        }
    };
    private CarPlayDelegate mCarPlayDelegate = new AnonymousClass3();
    protected int mStartStatus = 0;
    private CommandCompletionCallback mStartCompleteCallback = new CommandCompletionCallback() { // from class: com.ts.carplay.lib.ts.CarPlayStack.4
        @Override // com.ts.android.carplay.CommandCompletionCallback
        public void onComplete(Object appContext, int returnStatus) {
            LogUtil.info(CarPlayStack.TAG, "CarPlayStart complete = " + returnStatus);
            synchronized (this) {
                CarPlayStack.this.mStartStatus = returnStatus;
                notify();
            }
            if (returnStatus != 0) {
                CarPlayConnect.getInstance().onSessionEstablishFailed();
            }
        }
    };

    public CarPlayStack(CarPlayManager manager) {
        this.mAccessoryBtAddr = null;
        if (sCarPlayStack != null) {
            throw new IllegalStateException("sCarPlayStack was already created, use getInstance instead.");
        }
        this.mAccessoryBtAddr = DEFAULT_BLUETOOTH_ID;
        sCarPlayStack = this;
        this.mStackEventHandler = new CarPlayStackEventHandler(manager);
        this.mManager = manager;
    }

    public CarPlayClient getCarPlayClient() {
        return this.mTsCarPlayClient;
    }

    public void screenTouchEvent(MotionEvent event) {
        int xx = (int) event.getX();
        int yy = (int) event.getY();
        try {
            CarPlayTouchScreen touchScreen = this.mInputManager.getTouchScreen();
            boolean z = true;
            if (event.getAction() == 1) {
                z = false;
            }
            touchScreen.sendInput(xx, yy, z);
        } catch (Exception ex) {
            LogUtil.info(TAG, "screenTouchEvent setInput " + ex.toString());
        }
    }

    public static CarPlayStack getInstance() {
        CarPlayStack carPlayStack = sCarPlayStack;
        if (carPlayStack == null) {
            throw new IllegalStateException("Attempted to retrieve a CarPlayStack before constructor was called.");
        }
        return carPlayStack;
    }

    public void carPlayStart() {
    }

    public void carPlayStop() {
        LogUtil.info(TAG, "carPlayStop");
        stopCarplayClient();
        this.mBtCarPlayAddr = null;
    }

    public void onSessionDestoryed() {
        this.mIsSetUpRequested = false;
        this.mIsSetUpAltRequested = false;
        this.mSupportFeatures = 0;
    }

    public void changeAppState(int speechMode, boolean phoneCall, boolean turnByTurn) {
        CarPlayResourceManager carPlayResourceManager = this.mResourceManager;
        if (carPlayResourceManager != null) {
            try {
                carPlayResourceManager.changeAppState(speechMode, phoneCall, turnByTurn);
            } catch (Exception e) {
                LogUtil.info(TAG, "changeAppState Exception");
            }
        }
    }

    public boolean checkMacInMap(LinkDevice device) {
        this.mBtCarPlayAddr = device.getBtAddr();
        for (Object key : this.mTsCarPlayClients.keySet()) {
            CarPlayClient tmpClient = this.mTsCarPlayClients.get(key);
            if (tmpClient.mType == 2) {
                StringBuffer btMacStr = new StringBuffer("");
                if (tmpClient.mBluetoothMac != null) {
                    for (int i = 0; i < tmpClient.mBluetoothMac.length; i++) {
                        String strTemp = Integer.toHexString(tmpClient.mBluetoothMac[i] & 255);
                        if (strTemp.length() < 2) {
                            btMacStr.append(0);
                        }
                        btMacStr.append(strTemp);
                        if (i != tmpClient.mBluetoothMac.length - 1) {
                            btMacStr.append(":");
                        }
                    }
                }
                String str = this.mBtCarPlayAddr;
                if (str != null && str.toUpperCase(Locale.US).equals(btMacStr.toString().toUpperCase(Locale.US))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:83:0x039e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getDisplayInfo() {
        /*
            Method dump skipped, instruction units count: 992
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.carplay.lib.ts.CarPlayStack.getDisplayInfo():void");
    }

    private CarPlay.AppearanceMode convertAppearanceMode(ScreenInfo screenInfo) {
        if (screenInfo == null) {
            LogUtil.warning(TAG, "convertAppearanceMode screenInfo is null.");
            return null;
        }
        if (screenInfo.isIsSupportMap()) {
            return new CarPlay.AppearanceMode(screenInfo.getUiMode(), screenInfo.getUiSetting(), screenInfo.getMapMode(), screenInfo.getMapSetting());
        }
        return new CarPlay.AppearanceMode(screenInfo.getUiMode(), screenInfo.getUiSetting());
    }

    public String getDeviceId() {
        return this.mAccessoryBtAddr;
    }

    public void updateViewArea(String uuid, int index, int[] adjacentViewAreas, int animationDurationMillis) {
        LogUtil.warning(TAG, "updateViewArea index：" + index + ",uuid" + LogEncrypt.MacAddressEncrypt(uuid) + ",array:" + adjacentViewAreas.toString() + ",animationDurationMillis:" + animationDurationMillis);
        CarPlayClient carPlayClient = this.mTsCarPlayClient;
        if (carPlayClient != null) {
            carPlayClient.updateViewArea(uuid, animationDurationMillis, index, adjacentViewAreas);
        }
    }

    public void setEnhancedSiriInfo(List<String> voiceModelSupportedLanguages, String voiceModelCurrentLanguage, int supportedSiriTriggerZones) {
        LogUtil.debug(TAG, "setEnhancedSiriInfo voiceModelSupportedLanguages:" + voiceModelSupportedLanguages + ", voiceModelCurrentLanguage= " + voiceModelCurrentLanguage + ", supportedSiriTriggerZones= " + supportedSiriTriggerZones);
        String[] supportLanguages = (String[]) voiceModelSupportedLanguages.toArray(new String[voiceModelSupportedLanguages.size()]);
        this.mEnhancedSiriInfo = new CarPlay.EnhancedSiriInfo(false, supportedSiriTriggerZones, voiceModelCurrentLanguage, supportLanguages);
    }

    public int setUiAppearanceMode(String uuid, int uiMode, int uiSetting) {
        if (this.mTsCarPlayClient == null) {
            return -1;
        }
        ScreenInfo[] screenInfoList = this.mCarPlayConfig.getScreenInfos();
        int screenType = 0;
        while (screenType < screenInfoList.length) {
            String screenUuid = screenInfoList[screenType].getUuid();
            if (screenUuid.equals(uuid)) {
                break;
            }
            screenType++;
        }
        if (screenType >= screenInfoList.length) {
            return -1;
        }
        int ret = this.mTsCarPlayClient.setUiAppearanceMode(screenType, uiMode, uiSetting);
        return ret;
    }

    public int setMapAppearanceMode(String uuid, int mapMode, int mapSetting) {
        if (this.mTsCarPlayClient == null) {
            return -1;
        }
        ScreenInfo[] screenInfoList = this.mCarPlayConfig.getScreenInfos();
        int screenType = 0;
        while (screenType < screenInfoList.length) {
            String screenUuid = screenInfoList[screenType].getUuid();
            if (screenUuid.equals(uuid)) {
                break;
            }
            screenType++;
        }
        if (screenType >= screenInfoList.length) {
            return -1;
        }
        int ret = this.mTsCarPlayClient.setMapAppearanceMode(screenType, mapMode, mapSetting);
        return ret;
    }

    public synchronized void startCarPlayService() {
        LogUtil.info(TAG, "startCarPlayService");
        if (this.mTsCarPlay == null) {
            initializeSession();
        }
    }

    public void setCarPlayConfig(CarPlayConfig config) {
        LogUtil.info(TAG, "setCarPlayConfig :" + config);
        this.mCarPlayConfig = config;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientConnected(CarPlayClient client) {
        LogUtil.info(TAG, "onClientConnected client = " + client.toString() + "client.mClientId =" + client.mClientId);
        this.mTsCarPlayClients.put(new Integer(client.mClientId), client);
        CarPlayConnect.getInstance().onCarPlayClientConnected(client);
        this.mManager.setOobPairName(client.mName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientDisconnected(CarPlayClient client) {
        LogUtil.info(TAG, "onClientDisconnected client = " + client.toString() + "client.mClientId =" + client.mClientId);
        this.mTsCarPlayClients.remove(new Integer(client.mClientId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientSessionStarted(CarPlayClient client, CarPlayInputManager inputManager, CarPlayResourceManager resourceManager) {
        LogUtil.info(TAG, "onClientSessionStarted client.mClientId =" + client.mClientId);
        if (this.mTsCarPlayClient == null) {
            this.mTsCarPlayClient = client;
            CarPlayManager carPlayManager = this.mManager;
            if (carPlayManager != null && carPlayManager.getIsCarPlayConnection()) {
                startSession(this.mTsCarPlayClient);
            }
        }
        this.mInputManager = inputManager;
        this.mResourceManager = resourceManager;
        this.mTouchScreen = inputManager.getTouchScreen();
        this.mPlayKey = this.mInputManager.getPlayKey();
        this.mTelKey = this.mInputManager.getTelKey();
        this.mCarPlayKnob = this.mInputManager.getKnob();
        LogUtil.info(TAG, "mPlayKey = " + this.mPlayKey);
        LogUtil.info(TAG, "mTelKey = " + this.mTelKey);
        LogUtil.info(TAG, "mCarPlayKnob = " + this.mCarPlayKnob);
        this.mResourceManager = resourceManager;
        CarPlayManager carPlayManager2 = this.mManager;
        if (carPlayManager2 != null) {
            carPlayManager2.getCarPlayResourceManager().onSessionEstablished();
        }
        LogUtil.info(TAG, "onClientSessionStarted mIsScreenWillShow = " + this.mIsScreenWillShow);
        if (this.mIsScreenWillShow == 1) {
            CarPlayConnect.getInstance().onSessionEstablished(true);
        } else {
            CarPlayConnect.getInstance().onSessionEstablished(false);
        }
        this.isCarPlayConnect = true;
    }

    public synchronized void stopCarplayClient() {
        LogUtil.info(TAG, "stopCarplayClient.");
        if (this.mTsCarPlayClient != null) {
            LogUtil.info(TAG, "mTsCarPlayClient stop.");
            CarPlayConnect.getInstance().onSessionClosed();
            this.mTsCarPlayClient.setRenderingSurface(null, 0);
            this.mTsCarPlayClient.stop();
            this.mTsCarPlayClient = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientSessionStopped(CarPlayClient client) {
        LogUtil.info(TAG, "onClientSessionStopped +++client.mClientId =" + client.mClientId);
        this.isCarPlayConnect = false;
        this.mAuxInUpdateState = 0;
        LogUtil.info(TAG, "CarPlayConnect.getInstance().onSessionClosed() start");
        CarPlayConnect.getInstance().onSessionClosed();
        LogUtil.info(TAG, "CarPlayConnect.getInstance().onSessionClosed() end");
        this.mStackEventHandler.clientDisconnect();
        this.screenInTaken = false;
        this.mInputManager = null;
        this.mResourceManager = null;
        this.mTouchScreen = null;
        this.mPlayKey = null;
        this.mTelKey = null;
        this.mCarPlayKnob = null;
        stopCarplayClient();
    }

    private void onClientSessionConnectFail(CarPlayClient client) {
        LogUtil.info(TAG, "onClientSessionConnectFail");
        this.screenInTaken = false;
        this.mInputManager = null;
        this.mResourceManager = null;
        CarPlayConnect.getInstance().onSessionEstablishFailed();
        this.mTsCarPlayClient = null;
        this.mTouchScreen = null;
        this.mPlayKey = null;
        this.mTelKey = null;
        this.mCarPlayKnob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientSessionUpdate(CarPlayClient client) {
        LogUtil.info(TAG, "onClientSessionUpdate");
        CarPlayClient origClientRef = this.mTsCarPlayClients.get(new Integer(client.mClientId));
        if (origClientRef == null) {
            return;
        }
        if (client.isTypeChanged()) {
            origClientRef.mType = client.mType;
        }
        if (client.isNameChanged()) {
            origClientRef.mName = client.mName;
        }
        if (client.isBluetoothMacChanged()) {
            origClientRef.mBluetoothMac = client.mBluetoothMac;
        }
        if (client.isUsbMacChanged()) {
            origClientRef.mUsbMac = client.mUsbMac;
        }
        if (client.isWifiMacChanged()) {
            origClientRef.mWifiMac = client.mWifiMac;
        }
    }

    public void setNightMode(boolean mode) {
        CarPlayClient carPlayClient = this.mTsCarPlayClient;
        if (carPlayClient != null) {
            carPlayClient.setNightMode(mode);
        }
    }

    public void setLimitedUi(boolean mode) {
        if (this.mInputManager != null) {
            try {
                LogUtil.info(TAG, " setLimitedUi mode = " + mode + " LIMITED_UI_INFO = 31");
                this.mInputManager.setLimitedUi(mode);
            } catch (Exception e) {
                LogUtil.info(TAG, "carPlayShow Exception");
            }
        }
    }

    public void hardKeyEvent(int keyEvent, boolean ispressed) {
        LogUtil.info(TAG, "hardKeyEvent mPlayKey = " + this.mPlayKey + " keyEvent = " + keyEvent + " ispressed = " + ispressed);
        CarPlayPlayKey carPlayPlayKey = this.mPlayKey;
        if (carPlayPlayKey != null) {
            try {
                carPlayPlayKey.sendInput(keyEvent, ispressed);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void phoneKeyEvent(int keyEvent, boolean ispressed) {
        LogUtil.info(TAG, "phoneKeyEvent keyEvent = " + keyEvent + " ispressed = " + ispressed + " mTelKey = " + this.mTelKey);
        CarPlayTelephoneKey carPlayTelephoneKey = this.mTelKey;
        if (carPlayTelephoneKey != null) {
            try {
                carPlayTelephoneKey.sendInput(keyEvent, ispressed);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void carPlayKnobEvent(int knobKey, int positionX, int positionY, int wheelMovement) {
        LogUtil.info(TAG, "carPlayKnobEvent knobKey = " + knobKey + " x = " + positionX + " y = " + positionY + " wheelMovement = " + wheelMovement);
        StringBuilder sb = new StringBuilder();
        sb.append("mCarPlayKnob = ");
        sb.append(this.mCarPlayKnob);
        LogUtil.info(TAG, sb.toString());
        CarPlayKnob carPlayKnob = this.mCarPlayKnob;
        if (carPlayKnob != null) {
            try {
                carPlayKnob.sendInput(knobKey, positionX, positionY, wheelMovement);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void siriLaunch(int siriAction) {
        LogUtil.info(TAG, "siriAction = " + siriAction);
        CarPlayClient carPlayClient = this.mTsCarPlayClient;
        if (carPlayClient != null) {
            carPlayClient.siriLaunch(siriAction);
        }
    }

    public void launchSiri(int siriAction, long siriTriggerTimestamp) {
        LogUtil.debug(TAG, "launchSiri siriAction:" + siriAction + ", siriTriggerTimestamp= " + siriTriggerTimestamp);
        if (this.mTsCarPlayClient != null) {
            LogUtil.debug(TAG, "mTsCarPlayClient.siriLaunch");
            this.mTsCarPlayClient.siriLaunch(siriAction, siriTriggerTimestamp);
        }
    }

    public void carPlaySaveSurface(SurfaceData surfaceData) {
        if (this.mTsCarPlayClient == null) {
            LogUtil.info(TAG, "carPlaySaveSurface carplay not connected");
            return;
        }
        if (surfaceData != null && surfaceData.getScreenType() == 0) {
            synchronized (this.mSurfaceLock) {
                LogUtil.info(TAG, "carPlaySaveSurface surfaceData " + surfaceData);
                this.mSurfaces[0] = surfaceData.getSurface();
            }
        }
    }

    public void carPlayShow(SurfaceData surfaceData) {
        if (this.mTsCarPlayClient == null && surfaceData == null) {
            LogUtil.info(TAG, "carPlayShow carplay not connected " + surfaceData);
            return;
        }
        LogUtil.debug(TAG, "carPlayShow mIsScreenWillShow = " + this.mIsScreenWillShow);
        synchronized (this.mSurfaceLock) {
            this.mIsScreenWillShow = 0;
            int screenType = surfaceData.getScreenType();
            this.mSurfaces[screenType] = surfaceData.getSurface();
            if (this.mIsSetUpRequested || (this.mIsSetUpAltRequested && screenType != 0)) {
                this.mTsCarPlayClient.setRenderingSurface(this.mSurfaces[screenType], screenType);
                LogUtil.debug(TAG, "screen type = " + surfaceData.getScreenType());
                try {
                    this.mResourceManager.screenResource().available();
                } catch (Exception e) {
                    LogUtil.info(TAG, "carPlayShow Exception");
                }
                LogUtil.debug(TAG, "carPlayShow end mIsSetUpRequested = " + this.mIsSetUpRequested + " mIsSetUpAltRequested = " + this.mIsSetUpAltRequested + " screen type = " + screenType);
            } else {
                LogUtil.debug(TAG, "carPlayShow end mIsSetUpRequested = " + this.mIsSetUpRequested + " mIsSetUpAltRequested = " + this.mIsSetUpAltRequested + " screen type = " + screenType);
            }
        }
    }

    public void showUi(int screenType, int url) {
        if (this.mTsCarPlayClient == null) {
            LogUtil.info(TAG, "showUi carplay not connected ");
            return;
        }
        LogUtil.info(TAG, "showUi mIsSetUpRequested:" + this.mIsSetUpRequested + " mIsSetUpAltRequested:" + this.mIsSetUpAltRequested + " screenType:" + screenType);
        if ((this.mIsSetUpRequested && screenType == 0) || (this.mIsSetUpAltRequested && screenType != 0)) {
            if (this.mSurfaces[screenType] != null) {
                synchronized (this.mSurfaceLock) {
                    this.mTsCarPlayClient.showUi(screenType, url, this.mCarPlayConfig.getShowUiParameters());
                }
                return;
            }
            LogUtil.error(TAG, "showUi surface is null");
        }
    }

    public void stopUi(int screenType) {
        if (this.mTsCarPlayClient == null) {
            LogUtil.info(TAG, "stopUi carplay not connected ");
            return;
        }
        if ((this.mIsSetUpRequested && screenType == 0) || (this.mIsSetUpAltRequested && screenType != 0)) {
            if (this.mSurfaces[screenType] != null) {
                synchronized (this.mSurfaceLock) {
                    this.mTsCarPlayClient.stopUi(screenType);
                }
                return;
            }
            LogUtil.error(TAG, "stopUi surface is null");
        }
    }

    public void appLaunch(int app) {
        if (this.mTsCarPlayClient == null) {
            return;
        }
        LogUtil.info(TAG, "appLaunch app = " + app);
        this.mIsScreenWillShow = 0;
        if (app == 0) {
            int err = this.mTsCarPlayClient.appLaunch(CarPlay.APP_HOME, this.mOnAppLaunched);
            if (err != 0) {
                LogUtil.info(TAG, "launch home fail!");
                return;
            }
            return;
        }
        if (app == 2) {
            int err2 = this.mTsCarPlayClient.appLaunch(CarPlay.APP_MUSIC, this.mOnAppLaunched);
            if (err2 != 0) {
                LogUtil.info(TAG, "launch music fail!");
                return;
            }
            return;
        }
        if (app == 4) {
            int err3 = this.mTsCarPlayClient.appLaunch(CarPlay.APP_NOWPLAYING, this.mOnAppLaunched);
            if (err3 != 0) {
                LogUtil.info(TAG, "launch now playing fail!");
                return;
            }
            return;
        }
        if (app == 3) {
            int err4 = this.mTsCarPlayClient.appLaunch(CarPlay.APP_PHONE, this.mOnAppLaunched);
            if (err4 != 0) {
                LogUtil.info(TAG, "launch phone fail!");
                return;
            }
            return;
        }
        if (app == 1) {
            int err5 = this.mTsCarPlayClient.appLaunch(CarPlay.APP_MAPS, this.mOnAppLaunched);
            if (err5 != 0) {
                LogUtil.info(TAG, "launch maps fail!");
                return;
            }
            return;
        }
        if (app == 5) {
            int err6 = this.mTsCarPlayClient.appLaunch(CarPlay.APP_HOME, this.mOnAppLaunched);
            if (err6 != 0) {
                LogUtil.info(TAG, "launch default fail!");
            }
        }
    }

    public void launchHome() {
        LogUtil.info(TAG, "launch Home");
        try {
            int err = this.mResourceManager.screenResource().release(2);
            if (err != 0) {
                LogUtil.info(TAG, "launchApp fail!");
            }
        } catch (Exception e) {
            LogUtil.info(TAG, "take Exception");
        }
        try {
            Thread.currentThread();
            Thread.sleep(300L);
        } catch (Exception e2) {
            LogUtil.info(TAG, "sleep Exception");
        }
        CarPlayClient carPlayClient = this.mTsCarPlayClient;
        if (carPlayClient != null) {
            int err2 = carPlayClient.appLaunch(CarPlay.APP_HOME, this.mOnAppLaunched);
            if (err2 != 0) {
                LogUtil.info(TAG, "launchApp fail!");
            }
        }
    }

    public void carPlayHide() {
        carPlayHide(0);
    }

    public void carPlayHide(int screenType) {
        LogUtil.info(TAG, "carPlayHide");
        synchronized (this.mSurfaceLock) {
            if (screenType < this.mSurfaces.length) {
                this.mSurfaces[screenType] = null;
            }
            if (this.mTsCarPlayClient != null) {
                this.mTsCarPlayClient.setRenderingSurface(null, screenType);
                if (screenType != 0) {
                    this.mTsCarPlayClient.stopUi(screenType);
                }
            }
        }
    }

    public void take() {
        LogUtil.info(TAG, "take");
        if (this.mResourceManager != null) {
            CarPlayResourceManager.ResourceChange screenChange = new ScreenResource.Change(1, 2, 1, 1, "");
            this.screenInTaken = true;
            try {
                this.mResourceManager.screenResource().acquire(screenChange);
            } catch (Exception e) {
                LogUtil.info(TAG, "take Exception");
            }
        }
    }

    public CarPlayClient getCarPlayClientByUsbAddr(String addr) {
        HashMap<Integer, CarPlayClient> map;
        LogUtil.debug(TAG, "getCarPlayClientByUsbAddr, addr=" + LogEncrypt.MacAddressEncrypt(addr));
        if (addr != null && (map = this.mTsCarPlayClients) != null) {
            for (Object key : map.keySet()) {
                CarPlayClient tmpClient = this.mTsCarPlayClients.get(key);
                if (tmpClient != null && tmpClient.mType == 1) {
                    LogUtil.info(TAG, "getCarPlayClientByUsbAddr");
                    return tmpClient;
                }
            }
            return null;
        }
        return null;
    }

    public CarPlayClient getCarPlayClientByBtAddr(String addr) {
        LogUtil.debug(TAG, "getCarPlayClientByBtAddr , addr=" + LogEncrypt.MacAddressEncrypt(addr));
        if (addr != null) {
            for (Object key : this.mTsCarPlayClients.keySet()) {
                CarPlayClient tmpClient = this.mTsCarPlayClients.get(key);
                if (tmpClient.mType == 2) {
                    StringBuffer btMacStr = new StringBuffer("");
                    if (tmpClient.mBluetoothMac != null) {
                        for (int i = 0; i < tmpClient.mBluetoothMac.length; i++) {
                            String strTemp = Integer.toHexString(tmpClient.mBluetoothMac[i] & 255);
                            if (strTemp.length() < 2) {
                                btMacStr.append(0);
                            }
                            btMacStr.append(strTemp);
                            if (i != tmpClient.mBluetoothMac.length - 1) {
                                btMacStr.append(":");
                            }
                        }
                        LogUtil.debug(TAG, "getCarPlayClientByBtAddr , btMacStr=" + LogEncrypt.MacAddressEncrypt(btMacStr.toString()));
                        if (addr.toUpperCase(Locale.US).equals(btMacStr.toString().toUpperCase(Locale.US))) {
                            return tmpClient;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
        for (Object key2 : this.mTsCarPlayClients.keySet()) {
            CarPlayClient tmpClient2 = this.mTsCarPlayClients.get(key2);
            if (tmpClient2.mType == 1) {
                return tmpClient2;
            }
        }
        return null;
    }

    public int getListenPort() {
        CarPlay carPlay = this.mTsCarPlay;
        if (carPlay == null) {
            return -1;
        }
        try {
            int port = carPlay.getListenPort();
            return port;
        } catch (Exception ex) {
            LogUtil.info(TAG, ex.toString());
            return -1;
        }
    }

    public String getPairingIdentity() {
        CarPlay carPlay = this.mTsCarPlay;
        if (carPlay == null) {
            return null;
        }
        try {
            String publicKey = carPlay.getPairingIdentity();
            return publicKey;
        } catch (Exception ex) {
            LogUtil.info(TAG, ex.toString());
            return null;
        }
    }

    public String getSourceVersion() {
        CarPlay carPlay = this.mTsCarPlay;
        if (carPlay == null) {
            return null;
        }
        try {
            String sourceVersion = carPlay.getSourceVersion();
            return sourceVersion;
        } catch (Exception ex) {
            LogUtil.info(TAG, ex.toString());
            return null;
        }
    }

    public void startCarPlaySession(CarPlayClient client) {
        startSession(client);
    }

    private void updateBorrowId(int transfertype, CarPlayStartInfo startinfo, int updateType) {
        if (updateType == CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN.ordinal()) {
            if (transfertype == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal() && true == startinfo.screenTransferInfo.borrowId.equals("")) {
                startinfo.screenTransferInfo.borrowId = "standby";
                return;
            }
            return;
        }
        if (updateType == CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO.ordinal() && transfertype == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal() && true == startinfo.mainAudioTransferInfo.borrowId.equals("")) {
            startinfo.mainAudioTransferInfo.borrowId = "standby";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getModesInfo() {
        int screenTransferPriority;
        int audioPermanentEntity;
        int audioPermanentTakeConstraint;
        int audioPermanentBorrowConstraint;
        int screenPermanentTakeConstraint;
        int screenPermanentTakeConstraint2;
        int speechMode = -1;
        boolean turnByTurnActive = false;
        boolean phoneCallActive = false;
        CarPlayManager carPlayManager = this.mManager;
        if (carPlayManager != null) {
            turnByTurnActive = carPlayManager.getNativeNaviStatus();
            phoneCallActive = this.mManager.getNativePhoneStatus();
            speechMode = this.mManager.getCurNativeSpeechSts();
        }
        LogUtil.info(TAG, "startSession, phoneCallActive=" + phoneCallActive + ", tBT=" + turnByTurnActive);
        CarPlayStartInfo audioStartInfo = this.mStackEventHandler.getLastResourceState(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO);
        if (this.mManager.isSameBtMediaActive()) {
            LogUtil.info(TAG, "isSameBtMediaActive, change infoResponse.");
            audioStartInfo.mainAudioTransfer = CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal();
            audioStartInfo.mainAudioTransferInfo.transferPriorityValue = 1;
            audioStartInfo.mainAudioTransferInfo.takeConstraintValue = 1;
            audioStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue = 1;
        }
        int audioTransfer = audioStartInfo.mainAudioTransfer;
        updateBorrowId(audioTransfer, audioStartInfo, CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO.ordinal());
        int audioTransferPriority = audioStartInfo.mainAudioTransferInfo.transferPriorityValue;
        int audioTakeConstraint = audioStartInfo.mainAudioTransferInfo.takeConstraintValue;
        int audioBorrowOrUnborrowConstraint = audioStartInfo.mainAudioTransferInfo.borrowOrUnborrowConstraintValue;
        LogUtil.info(TAG, "startSession, audioStartInfo transfer = " + CpConst.CarPlayTransfer.toString(audioTransfer) + " priority = " + CpConst.CarPlayPriority.toString(audioTransferPriority) + " take_constraint = " + CpConst.CarPlayConstraint.toString(audioTakeConstraint) + " borrow_constraint = " + CpConst.CarPlayConstraint.toString(audioBorrowOrUnborrowConstraint) + " borrowID = " + audioStartInfo.mainAudioTransferInfo.borrowId);
        CarPlayStartInfo screenStartInfo = this.mStackEventHandler.getLastResourceState(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN);
        int screenTransfer = screenStartInfo.screenTransfer;
        int speechMode2 = speechMode;
        int speechMode3 = CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN.ordinal();
        updateBorrowId(screenTransfer, screenStartInfo, speechMode3);
        int screenTransferPriority2 = screenStartInfo.screenTransferInfo.transferPriorityValue;
        boolean turnByTurnActive2 = turnByTurnActive;
        int screenTakeConstraint = screenStartInfo.screenTransferInfo.takeConstraintValue;
        boolean phoneCallActive2 = phoneCallActive;
        int screenBorrowOrUnborrowConstraint = screenStartInfo.screenTransferInfo.borrowOrUnborrowConstraintValue;
        LogUtil.info(TAG, "startSession, screenStartInfo transfer = " + CpConst.CarPlayTransfer.toString(screenTransfer) + " priority = " + CpConst.CarPlayPriority.toString(screenTransferPriority2) + " take_constraint = " + CpConst.CarPlayConstraint.toString(screenTakeConstraint) + " borrow_constraint = " + CpConst.CarPlayConstraint.toString(screenBorrowOrUnborrowConstraint) + " borrowID = " + screenStartInfo.screenTransferInfo.borrowId);
        if (screenTransferPriority2 == CpConst.CarPlayPriority.CARPLAY_PRIORITY_NICETOHAVE.ordinal()) {
            this.mIsScreenWillShow = 1;
        } else {
            this.mIsScreenWillShow = 0;
        }
        CarPlayResourceManager.ResourceChange audioChange = new AudioResource.Change(audioTransfer, audioTransferPriority, audioTakeConstraint, audioBorrowOrUnborrowConstraint, audioStartInfo.mainAudioTransferInfo.borrowId);
        CarPlayResourceManager.ResourceChange screenChange = new ScreenResource.Change(screenTransfer, screenTransferPriority2, screenTakeConstraint, screenBorrowOrUnborrowConstraint, screenStartInfo.screenTransferInfo.borrowId);
        if (this.mManager == null) {
            LogUtil.error(TAG, "startSession: mManager is null,method getModesInfo return.");
            return;
        }
        CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
        CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
        CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
        CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_INVALID.ordinal();
        if (this.mManager.checkIsFirstConnCp()) {
            LogUtil.debug(TAG, "startSession, isfirstConnCp = true.");
            audioPermanentEntity = 2;
            screenTransferPriority = 2;
        } else {
            LogUtil.debug(TAG, "startSession, isfirstConnCp = false.");
            screenTransferPriority = CarPlayLastUserMode.getInstance().isCarPlay(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO) ? 1 : 2;
            audioPermanentEntity = CarPlayLastUserMode.getInstance().isCarPlay(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN) ? 1 : 2;
        }
        if (this.mStackEventHandler.isResourceInBorrowed(CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO)) {
            LogUtil.debug(TAG, "startSession, isAudioResourceInBorrowed = true.");
            int audioPermanentTakeConstraint2 = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
            audioPermanentTakeConstraint = audioPermanentTakeConstraint2;
            audioPermanentBorrowConstraint = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
        } else {
            LogUtil.debug(TAG, "startSession, isAudioResourceInBorrowed = false.");
            audioPermanentTakeConstraint = audioTakeConstraint;
            audioPermanentBorrowConstraint = audioBorrowOrUnborrowConstraint;
        }
        if (this.mStackEventHandler.isResourceInBorrowed(CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN)) {
            LogUtil.debug(TAG, "startSession, isScreenResourceInBorrowed = true.");
            int screenPermanentTakeConstraint3 = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_USER.ordinal();
            screenPermanentTakeConstraint = screenPermanentTakeConstraint3;
            screenPermanentTakeConstraint2 = CpConst.CarPlayConstraint.CARPLAY_CONSTRAINT_ANYTIME.ordinal();
        } else {
            LogUtil.debug(TAG, "startSession, isScreenResourceInBorrowed = false    .");
            screenPermanentTakeConstraint = screenTakeConstraint;
            screenPermanentTakeConstraint2 = screenBorrowOrUnborrowConstraint;
        }
        CarPlayResourceManager.PermanentResource permAudioResource = new CarPlayResourceManager.PermanentResource(screenTransferPriority, audioPermanentTakeConstraint, audioPermanentBorrowConstraint);
        CarPlayResourceManager.PermanentResource perScreenResource = new CarPlayResourceManager.PermanentResource(audioPermanentEntity, screenPermanentTakeConstraint, screenPermanentTakeConstraint2);
        CarPlay.ModesInfo modeInfo = new CarPlay.ModesInfo(phoneCallActive2, turnByTurnActive2, speechMode2, audioChange, screenChange, permAudioResource, perScreenResource);
        this.mInfoMsg.mModesInfo = modeInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getEnhancedSiriInfo() {
        CarPlay.EnhancedSiriInfo info = null;
        boolean isVoice = false;
        boolean isButton = false;
        int supportedFeatures = -1;
        boolean isAccessorySupport = false;
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig != null) {
            supportedFeatures = carPlayConfig.getSupportedFeatures();
            isAccessorySupport = CarPlayConfig.SupportedFeature.isFeatureSupported(supportedFeatures, 1);
            if (isAccessorySupport) {
                int supportedEnhancedSiriType = this.mCarPlayConfig.getSupportedEnhancedSiriType();
                isVoice = (supportedEnhancedSiriType & 1) > 0;
                isButton = (supportedEnhancedSiriType & 2) > 0;
                if (isVoice) {
                    CarPlay.EnhancedSiriInfo enhancedSiriInfo = this.mEnhancedSiriInfo;
                    int siriTriggerZones = enhancedSiriInfo == null ? CarPlay.SIRITRIGGERZONE_DRIVER : enhancedSiriInfo.siriTriggerZones;
                    CarPlay.EnhancedSiriInfo enhancedSiriInfo2 = this.mEnhancedSiriInfo;
                    String currentLanguage = (enhancedSiriInfo2 == null || enhancedSiriInfo2.voiceModelCurrentLanguage == null) ? "en-US" : this.mEnhancedSiriInfo.voiceModelCurrentLanguage;
                    CarPlay.EnhancedSiriInfo enhancedSiriInfo3 = this.mEnhancedSiriInfo;
                    String[] supportLanguages = (enhancedSiriInfo3 == null || enhancedSiriInfo3.voiceModelSupportedLanguages == null) ? new String[]{"en-US"} : this.mEnhancedSiriInfo.voiceModelSupportedLanguages;
                    info = new CarPlay.EnhancedSiriInfo(isButton, siriTriggerZones, currentLanguage, supportLanguages);
                } else {
                    info = new CarPlay.EnhancedSiriInfo(isButton);
                }
            } else {
                info = new CarPlay.EnhancedSiriInfo();
            }
        }
        LogUtil.debug(TAG, "getEnhancedSiriInfo supportedFeatures:" + supportedFeatures + ", isAccessorySupport: " + isAccessorySupport + "  isVoice:" + isVoice + ", isButton:" + isButton + ", supportedSiriTriggerZones:" + info.siriTriggerZones + ", voiceModelCurrentLanguage:" + info.voiceModelCurrentLanguage + ", supportLanguages:" + info.voiceModelSupportedLanguages);
        this.mInfoMsg.mEnhancedSiriInfo = info;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getManufacturer() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getManufacturer mCarPlayConfig is null.");
            this.mInfoMsg.mManufacturer = "OEM";
            return;
        }
        String temp = carPlayConfig.getManufacturer();
        if (TextUtils.isEmpty(temp)) {
            temp = "OEM";
            LogUtil.warning(TAG, "getManufacturer manufacturer is null.");
        }
        this.mInfoMsg.mManufacturer = temp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHardwareVersion() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getHardwareVersion mCarPlayConfig is null.");
            this.mInfoMsg.mHardwareRevision = "0.0.1";
            return;
        }
        String temp = carPlayConfig.getHardwareRevision();
        if (TextUtils.isEmpty(temp)) {
            temp = "0.0.1";
            LogUtil.warning(TAG, "getHardwareVersion hwRevision is null.");
        }
        this.mInfoMsg.mHardwareRevision = temp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFirmWareVersion() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getFirmWareVersion mCarPlayConfig is null.");
            this.mInfoMsg.mFiremwareRevision = "0.0.1";
            return;
        }
        String temp = carPlayConfig.getFirmwareRevision();
        if (TextUtils.isEmpty(temp)) {
            temp = "0.0.1";
            LogUtil.warning(TAG, "getFirmWareVersion FirmWareVersion is null.");
        }
        this.mInfoMsg.mFiremwareRevision = temp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHidLanguages() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getHidLanguages mCarPlayConfig is null.");
            this.mInfoMsg.setHidLaunguages(CarPlayConfig.DEFAULT_HIDLANGUAGES);
            return;
        }
        String[] hidLanguages = carPlayConfig.getHidLanguages();
        if (hidLanguages == null) {
            hidLanguages = CarPlayConfig.DEFAULT_HIDLANGUAGES;
            LogUtil.warning(TAG, "getHidLanguages hidLanguages is null");
        }
        this.mInfoMsg.setHidLaunguages(hidLanguages);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getModelName() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getModelName mCarPlayConfig is null.");
            this.mInfoMsg.mModelName = CarPlayConfig.DEFAULT_ACCESSORY_MODEL;
            return;
        }
        String accessoryModel = carPlayConfig.getAccessoryModel();
        if (TextUtils.isEmpty(accessoryModel)) {
            accessoryModel = CarPlayConfig.DEFAULT_ACCESSORY_MODEL;
            LogUtil.warning(TAG, "getModelName accessoryModel is null");
        }
        this.mInfoMsg.mModelName = accessoryModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOsInfo() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getOsInfo mCarPlayConfig is null.");
            this.mInfoMsg.mOsInfo = CarPlayConfig.DEFAULT_ACCESSORY_OS;
            return;
        }
        String accessoryOs = carPlayConfig.getAccessoryOsInfo();
        if (TextUtils.isEmpty(accessoryOs)) {
            accessoryOs = CarPlayConfig.DEFAULT_ACCESSORY_OS;
            LogUtil.warning(TAG, "accessoryOs is null");
        }
        this.mInfoMsg.mOsInfo = accessoryOs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOemIconLabel() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getOemIconLabel mCarPlayConfig is null.");
            this.mInfoMsg.mOemIconLabel = CarPlayConfig.DEFAULT_OEM_ICON_LABEL;
            return;
        }
        String oemIconLabel = carPlayConfig.getOemIconLabel();
        if (TextUtils.isEmpty(oemIconLabel)) {
            oemIconLabel = CarPlayConfig.DEFAULT_OEM_ICON_LABEL;
            LogUtil.warning(TAG, "getOemIconLabel oemIconLabel is null");
        }
        this.mInfoMsg.mOemIconLabel = oemIconLabel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOemIconPath() {
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig == null) {
            LogUtil.warning(TAG, "getOemIconPath mCarPlayConfig is null.");
            this.mInfoMsg.mOemIconPath = CarPlayConfig.DEFAULT_OEM_ICON_PATH[0];
            return;
        }
        String[] oemIconPaths = carPlayConfig.getOemIconPaths();
        if (oemIconPaths == null || (oemIconPaths != null && oemIconPaths.length < 4)) {
            oemIconPaths = CarPlayConfig.DEFAULT_OEM_ICON_PATH;
            LogUtil.warning(TAG, "getOemIconPath oemIconPaths is null");
        }
        this.mInfoMsg.mOemIconPath = oemIconPaths[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getButtonInfo() {
        ButtonInfo[] buttonInfo;
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig != null && (buttonInfo = carPlayConfig.getButtonInfo()) != null && buttonInfo.length != 0) {
            this.mInfoMsg.setButtonInfo(buttonInfo[0].getButtonLocation(), buttonInfo[0].getButtonPressDuration());
            LogUtil.info(TAG, "getButtonInfo Location:" + this.mInfoMsg.mButtonInfo.location + " PressDuration" + this.mInfoMsg.mButtonInfo.pressDuration);
        }
    }

    private int startSession(CarPlayClient client) {
        if (client == null) {
            LogUtil.info(TAG, "startSession client is not exist");
            return -1;
        }
        try {
            int ret = client.start(client.mClientId, this.mManager.getIsCarPlayConnection());
            LogUtil.info(TAG, "start ret" + ret);
            if (this.mTsCarPlayClient == null) {
                this.mTsCarPlayClient = client;
                return 0;
            }
            return 0;
        } catch (Exception ex) {
            LogUtil.info(TAG, ex.toString());
            return -1;
        }
    }

    public void setAccessoryWifiApAddr(String mac) {
        LogUtil.debug(TAG, "setAccessoryWifiApAddr mac " + LogEncrypt.MacAddressEncrypt(mac));
        this.mAccessoryWifiApAddr = mac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CarPlay.OemIcon[] parseOemIconList() {
        String[] iconPaths;
        CarPlay.OemIcon[] defaultOemIcons = {new CarPlay.OemIcon(OEM_ICON_120_SIZE, OEM_ICON_120_SIZE, this.mIsOemIconPreRender, CarPlayConfig.DEFAULT_OEM_ICON_PATH[1]), new CarPlay.OemIcon(180, 180, this.mIsOemIconPreRender, CarPlayConfig.DEFAULT_OEM_ICON_PATH[2]), new CarPlay.OemIcon(256, 256, this.mIsOemIconPreRender, CarPlayConfig.DEFAULT_OEM_ICON_PATH[3])};
        CarPlayConfig carPlayConfig = this.mCarPlayConfig;
        if (carPlayConfig != null && carPlayConfig.getOemIconPaths() != null && (iconPaths = (String[]) Arrays.copyOfRange(this.mCarPlayConfig.getOemIconPaths(), 1, this.mCarPlayConfig.getOemIconPaths().length)) != null && iconPaths.length == 3) {
            CarPlay.OemIcon[] newOemIcons = new CarPlay.OemIcon[3];
            int[] iconSize = {OEM_ICON_120_SIZE, 180, 256};
            for (int i = 0; i < 3; i++) {
                if (TextUtils.isEmpty(iconPaths[i])) {
                    LogUtil.warning(TAG, "parseOemIconList empty icon path, use default.");
                    return defaultOemIcons;
                }
                newOemIcons[i] = new CarPlay.OemIcon(iconSize[i], iconSize[i], this.mIsOemIconPreRender, iconPaths[i]);
            }
            LogUtil.info(TAG, "parseOemIconList use new icon paths");
            return newOemIcons;
        }
        LogUtil.warning(TAG, "parseOemIconList no config info, use default.");
        return defaultOemIcons;
    }

    public int initializeSession() {
        try {
            try {
                String address = BluetoothAdapter.getDefaultAdapter().getAddress();
                this.mAccessoryBtAddr = address;
                if (address == null || address.length() == 0) {
                    this.mAccessoryBtAddr = DEFAULT_BLUETOOTH_ID;
                }
            } catch (Exception ex) {
                LogUtil.info(TAG, ex.toString());
                this.mAccessoryBtAddr = DEFAULT_BLUETOOTH_ID;
            }
            LogUtil.debug(TAG, "initializeSession mAccessoryBtAddr " + this.mAccessoryBtAddr);
            CarPlay carPlay = new CarPlay();
            this.mTsCarPlay = carPlay;
            int status = carPlay.create(this.mCarPlayDelegate, null);
            if (status != 0) {
                LogUtil.info(TAG, "CarPlay Service create fail");
                this.mTsCarPlay = null;
                return status;
            }
            LogUtil.info(TAG, "CarPlay Service create success");
            return 0;
        } catch (Exception ex2) {
            LogUtil.info(TAG, "initializeSession tsCarPlay create " + ex2.toString());
            this.mTsCarPlay = null;
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHidConfig() {
        this.mInfoMsg.mHidDevicesInfo.keyMask = 0;
        int playKeyMask = this.mCarPlayConfig.getPlayKeyMask();
        if (!CarPlayConfig.MediaKey.isMediaKeyValid(playKeyMask)) {
            int playKeyMask2 = CarPlayConfig.DEFAULT_PLAYKEYMASK;
            LogUtil.warning(TAG, "playKeyMask is invalid");
        } else {
            this.mInfoMsg.mHidDevicesInfo.setPlayKeyInfo(33, 8, 2, playKeyMask);
            this.mInfoMsg.mHidDevicesInfo.keyMask |= CarPlayInputManager.BIT(3);
        }
        int telKeyMask = this.mCarPlayConfig.getTelKeyMask();
        if (!CarPlayConfig.PhoneKey.isPhoneKeyValid(telKeyMask)) {
            int telKeyMask2 = CarPlayConfig.DEFAULT_TELKEYMASK;
            LogUtil.warning(TAG, "telKeyMask is invalid");
        } else {
            this.mInfoMsg.mHidDevicesInfo.setTelephoneKeyInfo(33, 8, 2, telKeyMask);
            this.mInfoMsg.mHidDevicesInfo.keyMask |= CarPlayInputManager.BIT(4);
        }
        ScreenInfo[] screenInfoList = this.mCarPlayConfig.getScreenInfos();
        int heightPixels = screenInfoList[0].getHeightPixels();
        if (heightPixels == 0) {
            heightPixels = 720;
            LogUtil.warning(TAG, "heightPixels == 0");
        }
        int widthPixels = screenInfoList[0].getWidthPixels();
        if (widthPixels == 0) {
            widthPixels = 1920;
            LogUtil.warning(TAG, "widthPixels == 0");
        }
        sLogicalYMax = heightPixels;
        sLogicalXMax = widthPixels;
        this.mInfoMsg.mHidDevicesInfo.keyMask |= CarPlayInputManager.BIT(1);
        this.mInfoMsg.mHidDevicesInfo.setTouchScreenInfo(33, 8, 2, sLogicalXMin, sLogicalXMax, sLogicalYMin, sLogicalYMax);
    }

    private CarPlay.ViewArea[] convertViewAreas(ViewArea[] viewAreas) {
        CarPlay.ViewArea[] setViewAreas = new CarPlay.ViewArea[viewAreas.length];
        for (int i = 0; i < viewAreas.length; i++) {
            CarPlay.ViewArea setViewArea = new CarPlay.ViewArea();
            if (viewAreas[i] != null) {
                setViewArea.widthPixel = viewAreas[i].getWidthPixel();
                setViewArea.heightPixel = viewAreas[i].getHeightPixel();
                setViewArea.originX = viewAreas[i].getOriginX();
                setViewArea.originY = viewAreas[i].getOriginY();
                setViewArea.transitionControl = false;
                setViewArea.statusBarEdge = 2;
                setViewArea.supportsFocusTransfer = false;
                setViewAreas[i] = setViewArea;
            }
        }
        return setViewAreas;
    }

    private CarPlay.SafeArea[] convertSafeAreas(SafeArea[] safeAreas) {
        CarPlay.SafeArea[] setSafeAreas = new CarPlay.SafeArea[safeAreas.length];
        for (int i = 0; i < safeAreas.length; i++) {
            CarPlay.SafeArea setSafeArea = new CarPlay.SafeArea();
            if (safeAreas[i] != null) {
                setSafeArea.widthPixel = safeAreas[i].getWidthPixel();
                setSafeArea.heightPixel = safeAreas[i].getHeightPixel();
                setSafeArea.originX = safeAreas[i].getOriginX();
                setSafeArea.originY = safeAreas[i].getOriginY();
                setSafeAreas[i] = setSafeArea;
            }
        }
        return setSafeAreas;
    }

    /* JADX INFO: renamed from: com.ts.carplay.lib.ts.CarPlayStack$3, reason: invalid class name */
    class AnonymousClass3 implements CarPlayDelegate {
        AnonymousClass3() {
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onClientNotification(Object userContext, final int notification, final CarPlayClient client, final CarPlayInputManager inputManager, final CarPlayResourceManager resourceManager) {
            LogUtil.info(CarPlayStack.TAG, "onClientNotification notification=" + notification);
            if (CarPlayStack.this.mManager != null && notification == 5) {
                CarPlayStack.this.mManager.setIsConnectByUser(false);
            }
            Runnable clientNotificationHandler = new Runnable() { // from class: com.ts.carplay.lib.ts.CarPlayStack.3.1
                @Override // java.lang.Runnable
                public void run() {
                    int i = notification;
                    if (i == 1) {
                        CarPlayStack.this.onClientConnected(client);
                        return;
                    }
                    if (i == 2) {
                        CarPlayStack.this.onClientDisconnected(client);
                        return;
                    }
                    if (i == 3) {
                        CarPlayStack.this.onClientSessionUpdate(client);
                    } else if (i == 4) {
                        CarPlayStack.this.onClientSessionStarted(client, inputManager, resourceManager);
                    } else if (i == 5) {
                        CarPlayStack.this.onClientSessionStopped(client);
                    }
                }
            };
            CarPlayStack.this.mHandler.post(clientNotificationHandler);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public CarPlay.InfoMsg onInfoRequest(Object userContext, InfoMsgId msgType) {
            if (CarPlayStack.this.mCarPlayConfig == null) {
                LogUtil.warning(CarPlayStack.TAG, "onInfoRequest config is null!!!!!!!!!!!!!!!!!!!!!!!!");
                CarPlayStack.this.mCarPlayConfig = new CarPlayConfig();
            }
            switch (AnonymousClass5.$SwitchMap$com$ts$android$carplay$InfoMsgId[msgType.ordinal()]) {
                case 1:
                    int supportedFeatures = CarPlayStack.this.mCarPlayConfig.getSupportedFeatures();
                    boolean isAccessorySupportViewArea = CarPlayConfig.SupportedFeature.isFeatureSupported(supportedFeatures, 2);
                    boolean isPhoneSupportViewArea = CarPlayConfig.SupportedFeature.isFeatureSupported(CarPlayStack.this.mSupportFeatures, 2);
                    boolean isAccessorySupportAlt = CarPlayConfig.SupportedFeature.isFeatureSupported(supportedFeatures, 8);
                    boolean isPhoneSupportAlt = CarPlayConfig.SupportedFeature.isFeatureSupported(CarPlayStack.this.mSupportFeatures, 8);
                    CarPlayStack.this.mInfoMsg.mEnabledFeatures = supportedFeatures;
                    boolean enabledViewArea = false;
                    if (isAccessorySupportViewArea && isPhoneSupportViewArea) {
                        if (CarPlayStack.this.mCarPlayConfig.isUseMainViewArea()) {
                            enabledViewArea = true;
                        }
                        if (isAccessorySupportAlt && isPhoneSupportAlt && CarPlayStack.this.mCarPlayConfig.isUseAlt1ViewArea()) {
                            enabledViewArea = true;
                        }
                    }
                    if (!enabledViewArea) {
                        CarPlayStack.this.mInfoMsg.mEnabledFeatures &= -3;
                        LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " remove view area");
                    }
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mEnabledFeatures);
                    break;
                case 2:
                    if (CarPlayStack.this.mWireConnection) {
                        CarPlayStack.this.mInfoMsg.mDeviceId = CarPlayStack.this.mAccessoryBtAddr;
                    } else {
                        CarPlayStack.this.mInfoMsg.mDeviceId = CarPlayStack.this.mAccessoryWifiApAddr;
                        if (CarPlayStack.this.mAccessoryWifiApAddr == null) {
                            CarPlayStack.this.mInfoMsg.mDeviceId = CarPlayStack.this.mAccessoryBtAddr;
                        }
                    }
                    LogUtil.debug(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mDeviceId);
                    break;
                case 3:
                    CarPlayStack.this.getModesInfo();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                case 4:
                    CarPlayStack.this.getEnhancedSiriInfo();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                case 5:
                    CarPlayStack.this.getManufacturer();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mManufacturer);
                    break;
                case 6:
                    CarPlayStack.this.getHardwareVersion();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mHardwareRevision);
                    break;
                case 7:
                    CarPlayStack.this.getFirmWareVersion();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mFiremwareRevision);
                    break;
                case 8:
                    CarPlayStack.this.mInfoMsg.mbluetoothId = CarPlayStack.this.mAccessoryBtAddr;
                    LogUtil.debug(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mbluetoothId);
                    break;
                case 9:
                    CarPlayStack.this.mInfoMsg.mExtendedFeature = CarPlayStack.this.mCarPlayConfig.getExtendedFeatures();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mExtendedFeature);
                    break;
                case 10:
                    CarPlayStack.this.getHidConfig();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                case 11:
                    CarPlayStack.this.getHidLanguages();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                case 12:
                    CarPlayStack.this.getModelName();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mModelName);
                    break;
                case 13:
                    CarPlayStack.this.getOsInfo();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mOsInfo);
                    break;
                case 14:
                    CarPlayStack.this.mInfoMsg.mLimitedUiElements = CarPlayStack.this.mCarPlayConfig.getLimitedUiElements();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mLimitedUiElements);
                    break;
                case 15:
                    CarPlayStack.this.mInfoMsg.mSupportLimitedUi = CarPlayStack.this.mCarPlayConfig.isLimitedUi() ? 1 : -1;
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mSupportLimitedUi);
                    break;
                case 16:
                    CarPlayStack.this.mInfoMsg.mNightMode = CarPlayStack.this.mCarPlayConfig.isNightMode() ? 1 : 0;
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mNightMode);
                    break;
                case 17:
                    CarPlayStack.this.mInfoMsg.mRightHandDrive = CarPlayStack.this.mCarPlayConfig.getRightHandDrive();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mRightHandDrive);
                    break;
                case 18:
                    CarPlayStack.this.getOemIconLabel();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mOemIconLabel);
                    break;
                case 19:
                    CarPlayStack.this.mInfoMsg.mOemIcons = CarPlayStack.this.parseOemIconList();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                case 20:
                    CarPlayStack.this.getOemIconPath();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mOemIconPath);
                    break;
                case 21:
                    CarPlayStack.this.mInfoMsg.mOemIconVisible = CarPlayStack.this.mCarPlayConfig.isOemIconVisible();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mOemIconVisible);
                    break;
                case 22:
                case 23:
                    break;
                case 24:
                    boolean isAccessorySupportAlt2 = CarPlayConfig.SupportedFeature.isFeatureSupported(CarPlayStack.this.mCarPlayConfig.getSupportedFeatures(), 8);
                    boolean isPhoneSupportAlt2 = CarPlayConfig.SupportedFeature.isFeatureSupported(CarPlayStack.this.mSupportFeatures, 8);
                    if (!isAccessorySupportAlt2 || !isPhoneSupportAlt2) {
                        CarPlayStack.this.mInfoMsg.mAltScreenSuggestUiUrls = CarPlay.BIT(0);
                    } else {
                        CarPlayStack.this.mInfoMsg.mAltScreenSuggestUiUrls = CarPlayStack.this.mCarPlayConfig.getSuggestUrl();
                    }
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:" + CarPlayStack.this.mInfoMsg.mAltScreenSuggestUiUrls);
                    break;
                case 25:
                    CarPlayStack.this.getButtonInfo();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                case 26:
                    CarPlayStack.this.getDisplayInfo();
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
                default:
                    LogUtil.info(CarPlayStack.TAG, "onInfoRequest msgType:" + msgType + " value:");
                    break;
            }
            return CarPlayStack.this.mInfoMsg;
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onUiContextChanged(Object userContext, int a) {
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onSuggestUi(Object userContext, final int a) {
            LogUtil.info(CarPlayStack.TAG, "onSuggestUi url = " + a);
            CarPlayStack.this.mHandler.post(new Runnable() { // from class: com.ts.carplay.lib.ts.-$$Lambda$CarPlayStack$3$rmenNK1njgeNiDtquXyA2URaZvc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuggestUi$0$CarPlayStack$3(a);
                }
            });
        }

        public /* synthetic */ void lambda$onSuggestUi$0$CarPlayStack$3(int a) {
            if (CarPlayStack.this.mManager != null) {
                CarPlayStack.this.mManager.onSuggestUi(a);
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onSetInputMode(Object userContext, int inputMode, CarPlayInputManager.InputDevice inputDevice) {
            LogUtil.info(CarPlayStack.TAG, "onSetInputMode inputMode = " + inputMode);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onDisableBluetooth(Object userContext, final byte[] macAddress) {
            LogUtil.info(CarPlayStack.TAG, "onDisableBluetooth");
            CarPlayStack.this.mHandler.post(new Runnable() { // from class: com.ts.carplay.lib.ts.-$$Lambda$CarPlayStack$3$NxdMMOuMLb0jBHtqGUTbYwvn14Q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDisableBluetooth$1$CarPlayStack$3(macAddress);
                }
            });
        }

        public /* synthetic */ void lambda$onDisableBluetooth$1$CarPlayStack$3(byte[] macAddress) {
            if (CarPlayStack.this.mManager != null) {
                CarPlayStack.this.mManager.disableBluetooth(macAddress);
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public int onResourceRequest(Object userContext, int resource, int requestType, int sampleRate, int vocoderInfo) {
            LogUtil.info(CarPlayStack.TAG, "iPhone requested resource = " + resource + " requestType = " + requestType + " sampleRate = " + sampleRate + " vocoderInfo = " + vocoderInfo);
            if (resource != 1) {
                return CarPlayStack.this.mStackEventHandler.audioResourceRequest(requestType, sampleRate);
            }
            if (requestType == 1) {
                CarPlayStack.this.mIsSetUpAltRequested = true;
                LogUtil.debug(CarPlayStack.TAG, "alt request,return");
                if (CarPlayStack.this.mManager != null) {
                    CarPlayStack.this.mManager.onAltRequestSetup();
                }
                return 0;
            }
            synchronized (CarPlayStack.this.mSurfaceLock) {
                LogUtil.debug(CarPlayStack.TAG, "mIsSetUpRequested: " + CarPlayStack.this.mIsSetUpRequested + ", mSurface: " + CarPlayStack.this.mSurfaces);
                CarPlayStack.this.mIsSetUpRequested = true;
                Message msg = CarPlayStack.this.mStackEventHandler.getStackEventHandler().obtainMessage(3);
                if (CarPlayStack.this.mSurfaces[0] == null) {
                    CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
                    return 0;
                }
                CarPlayStack.this.mTsCarPlayClient.setRenderingSurface(CarPlayStack.this.mSurfaces[0], 0);
                CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
                return 1;
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onResourceChange(Object userContext, int screen, int audio, int perScreen, int perAudio) {
            if (CarPlayStack.this.mTsCarPlayClient == null) {
                LogUtil.info(CarPlayStack.TAG, "CarPlay Session already disconnected");
                return;
            }
            int[] data = {screen, audio, perScreen, perAudio};
            LogUtil.info(CarPlayStack.TAG, "iPhone resource Changed: screenEntity = " + data[0] + " audioEntity = " + data[1] + " perscreen = " + data[2] + " peraudio = " + data[3]);
            Message msg = CarPlayStack.this.mStackEventHandler.getStackEventHandler().obtainMessage(4, data);
            CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onAppStateChanged(Object userContext, int phoneEntity, int tbtEntity, int speechEntity, int speechMode) {
            if (CarPlayStack.this.mTsCarPlayClient == null) {
                LogUtil.info(CarPlayStack.TAG, "CarPlay Session already disconnected");
                return;
            }
            LogUtil.info(CarPlayStack.TAG, "[ATS] onAppStateChanged [ phoneEntity = " + phoneEntity + " tbtEntity = " + tbtEntity + " speechEntity = " + speechEntity + " speechMode = " + speechMode + " ]");
            CarPlayStack.this.mStackEventHandler.appStateChanged(phoneEntity, tbtEntity, speechEntity, speechMode);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onLauchNativeUI(Object userContext, String requestedUi) {
            LogUtil.info(CarPlayStack.TAG, "onLauchNativeUI = " + requestedUi);
            Message msg = CarPlayStack.this.mStackEventHandler.getStackEventHandler().obtainMessage(6);
            CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onDuckAudio(Object userContext, int durationMsec, int volumedB) {
            LogUtil.info(CarPlayStack.TAG, "onDuckAudio durationMsec = " + durationMsec + " volumedB = " + volumedB);
            Message msg = CarPlayStack.this.mStackEventHandler.getStackEventHandler().obtainMessage(7, durationMsec, volumedB);
            CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onUnDuckAudio(Object userContext, int durationMsec) {
            LogUtil.info(CarPlayStack.TAG, "onUnDuckAudio durationMsec = " + durationMsec);
            Message msg = CarPlayStack.this.mStackEventHandler.getStackEventHandler().obtainMessage(8, Integer.valueOf(durationMsec));
            CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onIAP2MessageReceived(Object userContext, byte[] iAP2Data) {
            CarPlayTransport.getInstance().sendMsgToNative(2, iAP2Data);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onNotification(Object userContext, int carplayNotification, int param1, int param2, int param3) {
            LogUtil.info(CarPlayStack.TAG, "onNotification Callback received Notification = " + carplayNotification + " param1 = " + param1 + " param2 = " + param2 + " param3 = " + param3);
            int[] data = {param1, param2, param3};
            if (carplayNotification == 2) {
                synchronized (CarPlayStack.this.mSurfaceLock) {
                    CarPlayStack.this.mIsSetUpRequested = false;
                }
            }
            Message msg = CarPlayStack.this.mStackEventHandler.getStackEventHandler().obtainMessage(9, carplayNotification, 0, data);
            CarPlayStack.this.mStackEventHandler.getStackEventHandler().sendMessage(msg);
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onClientMacAddrRecv(Object userContext, byte[] clientMac) {
            if (clientMac != null) {
                LogUtil.debug(CarPlayStack.TAG, "onClientMacAddrRecv clientMac");
                CarPlayStack.this.mStackEventHandler.onCpClientMacAddrRecv(clientMac);
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onSupportFeaturesUpdate(Object userContext, final int supportsFeatures) {
            LogUtil.info(CarPlayStack.TAG, "onSupportFeaturesUpdate supportsFeatures: " + supportsFeatures);
            CarPlayStack.this.mSupportFeatures = supportsFeatures;
            CarPlayStack.this.mHandler.post(new Runnable() { // from class: com.ts.carplay.lib.ts.-$$Lambda$CarPlayStack$3$9i9M8GHu4Bb9CqGkShxycBKmKcU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSupportFeaturesUpdate$2$CarPlayStack$3(supportsFeatures);
                }
            });
        }

        public /* synthetic */ void lambda$onSupportFeaturesUpdate$2$CarPlayStack$3(int supportsFeatures) {
            if (CarPlayStack.this.mManager != null) {
                CarPlayStack.this.mIsSupportViewArea = CarPlayConfig.SupportedFeature.isFeatureSupported(supportsFeatures, 2);
                CarPlayStack.this.mManager.onSupportFeaturesUpdate(supportsFeatures);
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onEnhancedSiriParamsUpdate(Object userContext, final int inMode, final String inLanguage) {
            LogUtil.info(CarPlayStack.TAG, "onEnhancedSiriParamsUpdate inMode:" + inMode + ", inLanguage= " + inLanguage);
            CarPlayStack.this.mHandler.post(new Runnable() { // from class: com.ts.carplay.lib.ts.-$$Lambda$CarPlayStack$3$EQuSQLHrx_nsI-prtYm6GybdkLc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onEnhancedSiriParamsUpdate$3$CarPlayStack$3(inMode, inLanguage);
                }
            });
        }

        public /* synthetic */ void lambda$onEnhancedSiriParamsUpdate$3$CarPlayStack$3(int inMode, String inLanguage) {
            int siriParameter;
            if (inMode != -1) {
                if (inMode != 1) {
                    if (inMode == 2) {
                        siriParameter = 1;
                    } else {
                        siriParameter = 2;
                    }
                } else {
                    siriParameter = 0;
                }
            } else {
                siriParameter = 2;
            }
            if (CarPlayStack.this.mManager != null) {
                CarPlayStack.this.mManager.onEnhancedSiriParamsUpdate(siriParameter, inLanguage);
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onAuxInUpdateState(Object userContext, int state) {
            LogUtil.info(CarPlayStack.TAG, "onAuxInUpdateState state:" + state);
            CarPlayStack.this.mAuxInUpdateState = state;
            if (CarPlayStack.this.mManager != null) {
                CarPlayStack.this.mManager.onAuxInUpdateState(state);
            }
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onScreenProcessErr(Object userContext) {
        }

        @Override // com.ts.android.carplay.CarPlayDelegate
        public void onRequestViewArea(Object userContext, int viewAreaIndex) {
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.lib.ts.CarPlayStack$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$android$carplay$InfoMsgId;

        static {
            int[] iArr = new int[InfoMsgId.values().length];
            $SwitchMap$com$ts$android$carplay$InfoMsgId = iArr;
            try {
                iArr[InfoMsgId.INFOMSG_ACCESSORYENABLEDFEATURES.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_DEVICEID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_MODES.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_ENHANCEDSIRIINFO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_MANUFACTURER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_HARDWAREREVISION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_FIRMWAREREVISION.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_BLUETOOTHIDS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_EXTENDEDFEATURES.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_HIDDEVICES.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_HIDLANGUAGES.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_MODELNAME.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_OSINFO.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_LIMITEDUIELEMENTS.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_LIMITEDUI.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_NIGHTMODE.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_RIGHTHANDDRIVE.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_OEMICONLABEL.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_OEMICONS.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_OEMICONPATH.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_OEMICONVISIBLE.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_ACCESSORYENABLEDUICONTEXTURLS.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_UICONTEXTNOWONDISPLAYURLS.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_ALTSCREENSUGGESTUIURLS.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_BUTTONINFO.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$ts$android$carplay$InfoMsgId[InfoMsgId.INFOMSG_DISPLAYS.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
        }
    }

    public void startAuxIn() {
        if (this.mTsCarPlayClient != null && this.isCarPlayConnect) {
            LogUtil.debug(TAG, "startAuxIn mAuxInUpdateState: " + this.mAuxInUpdateState);
            this.mTsCarPlayClient.startAuxIn();
            return;
        }
        LogUtil.debug(TAG, "startAuxIn mTsCarPlayClient is null isCarPlayConnect:" + this.isCarPlayConnect);
    }

    public void stopAuxIn() {
        if (this.mTsCarPlayClient != null) {
            LogUtil.debug(TAG, "stopAuxIn mAuxInUpdateState: " + this.mAuxInUpdateState);
            if (2 != this.mAuxInUpdateState) {
                this.mTsCarPlayClient.stopAuxIn();
                return;
            } else {
                LogUtil.debug(TAG, "stopAuxIn faild, current state is STREAMSTATE_STREAMING.");
                return;
            }
        }
        LogUtil.debug(TAG, "stopAuxIn mTsCarPlayClient is null");
    }

    public void rspAudioFocusReq(int audioType, boolean isSuccess) {
        if (this.mTsCarPlayClient != null) {
            LogUtil.debug(TAG, "rspAudioFocusReq");
            this.mTsCarPlayClient.rspAudioFocusReq(audioType, isSuccess);
        }
    }

    public int getSupportFeaturesUpdate() {
        LogUtil.info(TAG, "getSupportFeaturesUpdate mSupportFeatures = " + this.mSupportFeatures);
        return this.mSupportFeatures;
    }

    public void carPlayAvailableResource(CpConst.CarPlayResource inResource, int inRequestType) {
        if (this.mTsCarPlayClient == null) {
            LogUtil.info(TAG, "carplay not connected");
            return;
        }
        LogUtil.info(TAG, "carPlayAvailableResource resource = " + inResource + " requestType = " + inRequestType);
        if (inResource == CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN) {
            try {
                this.mResourceManager.screenResource().available();
            } catch (Exception e) {
                LogUtil.info(TAG, "carPlayShow Exception");
            }
        } else {
            try {
                this.mResourceManager.audioResource().available(inRequestType);
            } catch (Exception e2) {
                LogUtil.info(TAG, "carPlayShow Exception");
            }
        }
    }

    public int acquireResource(int resource, int transfer, CarPlayResourceStateChange stateChange) {
        String transferStr;
        String transferStr2;
        if (this.mTsCarPlayClient == null) {
            LogUtil.info(TAG, "carplay not connected");
            return -1;
        }
        if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal()) {
            transferStr = "TAKE";
        } else {
            if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_BORROW.ordinal()) {
                transferStr2 = "BORROW";
                if (true == stateChange.borrowId.equals("")) {
                    stateChange.borrowId = "standby";
                }
            } else if (transfer != CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNBORROW.ordinal()) {
                transferStr = null;
            } else {
                transferStr2 = "UNBORROW";
                if (true == stateChange.borrowId.equals("")) {
                    stateChange.borrowId = "standby";
                }
            }
            transferStr = transferStr2;
        }
        LogUtil.info(TAG, "acquireResource transfer=" + transferStr + " transferPriority=" + stateChange.transferPriority + " takeConstraint=" + stateChange.takeConstraint + " borrowOrUnborrowConstraint=" + stateChange.borrowOrUnborrowConstraint + " borrowId=" + stateChange.borrowId);
        if (resource == 2) {
            CarPlayResourceManager.ResourceChange audioChange = new AudioResource.Change(transfer, stateChange.transferPriority.ordinal(), stateChange.takeConstraint.ordinal(), stateChange.borrowOrUnborrowConstraint.ordinal(), stateChange.borrowId);
            try {
                int status = this.mResourceManager.audioResource().acquire(audioChange);
                if (status == 0) {
                    return 0;
                }
                LogUtil.info(TAG, "acquireResource audio fail status:" + status);
                return status;
            } catch (Exception ex) {
                LogUtil.info(TAG, "acquireResource audio Exception:" + ex);
                return -1;
            }
        }
        if (resource == 1) {
            CarPlayResourceManager.ResourceChange screenChange = new ScreenResource.Change(transfer, stateChange.transferPriority.ordinal(), stateChange.takeConstraint.ordinal(), stateChange.borrowOrUnborrowConstraint.ordinal(), stateChange.borrowId);
            if (transfer == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_TAKE.ordinal()) {
                this.screenInTaken = true;
            } else {
                this.mIsScreenWillShow = 0;
            }
            try {
                int status2 = this.mResourceManager.screenResource().acquire(screenChange);
                if (status2 == 0) {
                    return 0;
                }
                LogUtil.info(TAG, "acquireResource audio fail status:" + status2);
                return status2;
            } catch (Exception ex2) {
                LogUtil.info(TAG, "acquireResource audio Exception:" + ex2);
                return -1;
            }
        }
        return -1;
    }

    public int releaseResource(int resource, int requestType) {
        if (this.mTsCarPlayClient == null) {
            LogUtil.info(TAG, "carplay not connected");
            return -1;
        }
        LogUtil.info(TAG, "releaseResource = " + resource + " requestType = " + requestType + " currentTakeStatus = " + this.screenInTaken);
        if (!this.screenInTaken && requestType == CpConst.CarPlayTransfer.CARPLAY_TRANSFER_UNTAKE.ordinal()) {
            LogUtil.info(TAG, "ignore the unnecessory take");
            return -1;
        }
        try {
            if (resource == 1) {
                return this.mResourceManager.screenResource().release(requestType);
            }
            if (resource == 2) {
                return this.mResourceManager.audioResource().release(requestType);
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public void setNowCallingStatus(int status) {
        this.mManager.setCurrentCallSts(status);
    }

    public void setWireConnection(boolean isWireConnection) {
        LogUtil.debug(TAG, "setWireConnection : " + isWireConnection);
        this.mWireConnection = isWireConnection;
    }
}
