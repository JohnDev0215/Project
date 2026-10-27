package com.ts.android.carplay;

import com.ts.android.carplay.CarPlay;
import com.ts.android.carplay.CarPlayInputManager;
import com.ts.android.carplay.CarPlayResourceManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayNative {
    private static final String TAG = "CarPlayNative";
    private static CarPlayDelegate mDelegate;
    private static Object mUserContext;
    private static HashMap<Integer, CarPlayClient> mClients = new HashMap<>();
    private static CarPlayNative mInstance = null;

    public native int native_acquireResource(int i, CarPlayResourceManager.ResourceChange resourceChange);

    public native int native_appLaunch(String str);

    public native int native_appstate(int i, boolean z, boolean z2);

    public native int native_availableResource(int i);

    public native int native_changeMapZoomLevel(int i, int i2);

    public native int native_changeUiContext(int i);

    public native int native_create();

    public native int native_destroy();

    public native int native_getListenPort();

    public native String native_getPairingIdentity();

    public native String native_getPeerAddress();

    public native String native_getSourceVersion();

    public native int native_init(Object obj);

    public native int native_inputGeneric(byte[] bArr, int i);

    public native int native_inputKeys(int i, boolean z);

    public native int native_inputKnob(int i, int i2, int i3, int i4);

    public native int native_inputTelkeys(int i, boolean z);

    public native int native_inputTouch(int i, int i2, boolean z);

    public native int native_rspAudioFocusReq(int i, boolean z);

    public native int native_sendIapMsg(byte[] bArr, int i);

    public native int native_setAAudioDeviceId(CarPlayAudioDeviceInfo carPlayAudioDeviceInfo);

    public native int native_setLimitedUi(boolean z);

    public native int native_setMapAppearanceMode(int i, int i2, int i3);

    public native int native_setNightMode(boolean z);

    public native int native_setSurface(Object obj, int i);

    public native int native_setUiAppearanceMode(int i, int i2, int i3);

    public native int native_showUi(int i, int i2, int i3);

    public native int native_siriLaunch(int i, int i2);

    public native int native_siriLaunch(int i, long j);

    public native int native_start(int i, boolean z);

    public native int native_startAuxIn();

    public native int native_stop();

    public native int native_stopAuxIn();

    public native int native_stopUi(int i);

    public native int native_updateViewArea(String str, int i, int i2, int[] iArr);

    private CarPlayNative() {
        System.loadLibrary("carplay_jni");
    }

    public static CarPlayNative getInstance() {
        if (mInstance == null) {
            mInstance = new CarPlayNative();
        }
        return mInstance;
    }

    public static void onAppStateChanged(int phoneEntity, int tbtEntity, int speechEntity, int speechMode) {
        mDelegate.onAppStateChanged(mUserContext, phoneEntity, tbtEntity, speechEntity, speechMode);
    }

    public static void onDisableBluetooth(byte[] macAddress) {
        mDelegate.onDisableBluetooth(mUserContext, macAddress);
    }

    public static void onDuckAudio(int durationMsec, int volumedB) {
        mDelegate.onDuckAudio(mUserContext, durationMsec, volumedB);
    }

    public static void onPerformHapticFeedback(long inHapticFeedbackType, int inDeviceUID) {
    }

    public static void onIAP2MessageReceived(byte[] iAP2Data) {
        mDelegate.onIAP2MessageReceived(mUserContext, iAP2Data);
    }

    public static void onLauchNativeUI(String requestedUI) {
        mDelegate.onLauchNativeUI(mUserContext, requestedUI);
    }

    public static void onNotification(int carplayNotification, int param1, int param2, int param3) {
        mDelegate.onNotification(mUserContext, carplayNotification, param1, param2, param3);
    }

    public static void onResourceChange(int screen, int audio, int permScreen, int permAudio) {
        mDelegate.onResourceChange(mUserContext, screen, audio, permScreen, permAudio);
    }

    public static int onResourceRequest(int carplayResource, int requestType, int sampleRate, int vocoderInfo) {
        int ret = mDelegate.onResourceRequest(mUserContext, carplayResource, requestType, sampleRate, vocoderInfo);
        return ret;
    }

    public static void onSetInputMode(int inputMode, CarPlayInputManager.InputDevice inputDevice) {
        mDelegate.onSetInputMode(mUserContext, inputMode, inputDevice);
    }

    public static void onUnDuckAudio(int durationMsec) {
        mDelegate.onUnDuckAudio(mUserContext, durationMsec);
    }

    public static void onClientMacAddrRecv(byte[] clientMac) {
        mDelegate.onClientMacAddrRecv(mUserContext, clientMac);
    }

    public static void onSupportFeaturesUpdate(int supportsFeatures) {
        mDelegate.onSupportFeaturesUpdate(mUserContext, supportsFeatures);
    }

    public static void onSuggestUi(int inUrl) {
        mDelegate.onSuggestUi(mUserContext, inUrl);
    }

    public static void onEnhancedSiriParamsUpdate(int inMode, String inLanguage) {
        mDelegate.onEnhancedSiriParamsUpdate(mUserContext, inMode, inLanguage);
    }

    public static void onUiContextChanged(int contextUrl) {
        mDelegate.onUiContextChanged(mUserContext, contextUrl);
    }

    public static void onAuxInUpdateState(int state) {
        mDelegate.onAuxInUpdateState(mUserContext, state);
    }

    public static void onScreenProcessErr() {
        mDelegate.onScreenProcessErr(mUserContext);
    }

    public static void onRequestViewArea(int viewAreaIndex) {
        mDelegate.onRequestViewArea(mUserContext, viewAreaIndex);
    }

    public static void onClientNotification(int notification, boolean iswifi, int clientId, byte[] btMac, String name) {
        LogUtil.info(TAG, "notification :" + notification);
        CarPlayClient myClient = mClients.get(new Integer(clientId));
        if (myClient == null) {
            myClient = new CarPlayClient();
            mClients.put(new Integer(myClient.mClientId), myClient);
        }
        myClient.mType = iswifi ? 2 : 1;
        myClient.setBluetoothMac(btMac);
        myClient.mName = name;
        myClient.mClientId = clientId;
        if (notification == 1) {
            LogUtil.info(TAG, "myClient :" + myClient + "clientID=:" + clientId);
            mDelegate.onClientNotification(mUserContext, notification, myClient, CarPlayInputManager.getInstance(), new CarPlayResourceManager());
            return;
        }
        if (notification == 4) {
            mDelegate.onClientNotification(mUserContext, notification, myClient, CarPlayInputManager.getInstance(), new CarPlayResourceManager());
            return;
        }
        if (notification == 2) {
            mDelegate.onClientNotification(mUserContext, notification, myClient, CarPlayInputManager.getInstance(), new CarPlayResourceManager());
            return;
        }
        if (notification == 5) {
            mDelegate.onClientNotification(mUserContext, notification, myClient, CarPlayInputManager.getInstance(), new CarPlayResourceManager());
        } else if (notification == 3) {
            mDelegate.onClientNotification(mUserContext, notification, myClient, CarPlayInputManager.getInstance(), new CarPlayResourceManager());
        } else if (notification == 6) {
            mDelegate.onClientNotification(mUserContext, notification, myClient, CarPlayInputManager.getInstance(), new CarPlayResourceManager());
        }
    }

    public static CarPlay.InfoMsg onInfoRequest(int msgType) {
        return mDelegate.onInfoRequest(mUserContext, InfoMsgId.values()[msgType]);
    }

    public int create(CarPlayDelegate delegate, Object userContext) {
        mDelegate = delegate;
        mUserContext = userContext;
        return native_create();
    }

    public int init() {
        return native_init(new WeakReference(this));
    }

    public int start(int clientId, boolean isConnectedByIap) {
        return native_start(clientId, isConnectedByIap);
    }

    public int stop() {
        return native_stop();
    }

    public int destroy() {
        return native_destroy();
    }

    public int getListenPort() {
        return native_getListenPort();
    }

    public String getPeerAddress() {
        return native_getPeerAddress();
    }

    public String getSourceVersion() {
        return native_getSourceVersion();
    }

    public String getPairingIdentity() {
        return native_getPairingIdentity();
    }

    public int appstate(int app, int speechMode, boolean enable) {
        return 1;
    }

    public int changeAppState(int speechMode, boolean phoneCall, boolean turnByTurn) {
        return native_appstate(speechMode, phoneCall, turnByTurn);
    }

    public int appLaunch(String carplayApp, CommandCompletionCallback callback) {
        return native_appLaunch(carplayApp);
    }

    public int siriLaunch(int siriAction, int latencyMs) {
        return native_siriLaunch(siriAction, latencyMs);
    }

    public int siriLaunch(int siriAction, long timestamp) {
        return native_siriLaunch(siriAction, timestamp);
    }

    public int setNightMode(boolean nightMode) {
        return native_setNightMode(nightMode);
    }

    public int setUiAppearanceMode(int screenType, int inMode, int inSetting) {
        return native_setUiAppearanceMode(screenType, inMode, inSetting);
    }

    public int setMapAppearanceMode(int screenType, int inMode, int inSetting) {
        return native_setMapAppearanceMode(screenType, inMode, inSetting);
    }

    public int changeMapZoomLevel(int screenType, int zoomDirection) {
        return native_changeMapZoomLevel(screenType, zoomDirection);
    }

    public int acquireResource(int resource, CarPlayResourceManager.ResourceChange resourceChange) {
        return native_acquireResource(resource, resourceChange);
    }

    public int availableResource(int resource) {
        return native_availableResource(resource);
    }

    public int inputGeneric(byte[] report) {
        return native_inputGeneric(report, report.length);
    }

    public int inputKnob(int knobKey, int x, int y, int wheelMovement) {
        return native_inputKnob(knobKey, x, y, wheelMovement);
    }

    public int inputKeys(int key, boolean pressed) {
        return native_inputKeys(key, pressed);
    }

    public int inputTelKeys(int key, boolean pressed) {
        return native_inputTelkeys(key, pressed);
    }

    public int rspAudioFocusReq(int audioType, boolean isSuccess) {
        return native_rspAudioFocusReq(audioType, isSuccess);
    }

    public int inputTouch(int x, int y, boolean pressed) {
        return native_inputTouch(x, y, pressed);
    }

    public int setSurface(Object surface, int screenType) {
        return native_setSurface(surface, screenType);
    }

    public int showUi(int screenType, int inUrl, int exElement) {
        return native_showUi(screenType, inUrl, exElement);
    }

    public int stopUi(int screenType) {
        return native_stopUi(screenType);
    }

    public int startAuxIn() {
        return native_startAuxIn();
    }

    public int stopAuxIn() {
        return native_stopAuxIn();
    }

    public int changeUiContext(int contextUrl) {
        return native_changeUiContext(contextUrl);
    }

    public int updateViewArea(String uuid, int animationDurationMilli, int viewAreaIndex, int[] adjacentAreas) {
        return native_updateViewArea(uuid, animationDurationMilli, viewAreaIndex, adjacentAreas);
    }

    public int setAAudioDeviceId(CarPlayAudioDeviceInfo audioInfo) {
        return native_setAAudioDeviceId(audioInfo);
    }

    public int setLimitedUi(boolean enable) {
        return native_setLimitedUi(enable);
    }

    public int sendIapMsg(byte[] iapMessage, int length) {
        return native_sendIapMsg(iapMessage, length);
    }
}
