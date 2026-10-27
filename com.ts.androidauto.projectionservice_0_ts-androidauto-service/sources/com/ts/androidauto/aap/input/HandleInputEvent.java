package com.ts.androidauto.aap.input;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.GalReceiver;
import com.google.android.projection.protocol.InputSource;
import com.ts.androidauto.aap.audiofocus.StateManager;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.aap.video.AapVideoManager;
import com.ts.androidauto.fsdk.common.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public final class HandleInputEvent {
    private static final int NUMBER_75 = 75;
    private static final int SCREEN_HEIGHT = 480;
    private static final int SCREEN_WIDTH = 800;
    private static final String TAG = "AAP" + HandleInputEvent.class.getSimpleName();
    private static HandleInputEvent handleInputEvent;
    private Context mContext;
    private int[] mValidKeys;
    private final Object mLock = new Object();
    private boolean mTouchDebugSwitcher = false;
    private GalIntegration mGal = GalIntegration.getInstance();
    private AapVideoManager mAapVideoManager = AapVideoManager.getInstance();

    public static synchronized HandleInputEvent getInstance(Context context) {
        if (handleInputEvent == null) {
            handleInputEvent = new HandleInputEvent(context);
        }
        return handleInputEvent;
    }

    private HandleInputEvent(Context context) {
        this.mContext = context;
    }

    public void setTouchDebugSwitcher(boolean switcher) {
        this.mTouchDebugSwitcher = switcher;
    }

    public synchronized void initInputEvent() {
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info != null) {
            this.mValidKeys = info.getSupportKeyCodes();
            this.mGal.input.registerKeyCodes(this.mValidKeys);
            this.mGal.input.registerTouchScreen(info.mTouchScreenWidth, info.mTouchScreenHeight, InputSource.TOUCHSCREEN_TYPE_CAPACITIVE);
        } else {
            this.mValidKeys = new int[]{Protos.KeyCode.KEYCODE_SEARCH.getNumber()};
            this.mGal.input.registerKeyCodes(this.mValidKeys);
            this.mGal.input.registerTouchScreen(800, 480, InputSource.TOUCHSCREEN_TYPE_CAPACITIVE);
        }
        this.mGal.input.setDisplayId(0);
        this.mGal.inputCluster.setDisplayId(1);
    }

    public synchronized boolean handleKeyEvent(int key, int value) {
        boolean handled = false;
        LogUtil.info(TAG, "handleKeyEvent key:" + key + " value:" + value);
        if (this.mGal != null && this.mGal.input != null) {
            boolean z = true;
            if (value == 1 || value == 0) {
                Protos.KeyCode inputKey = getValidKey(key);
                int keyCode = translateKeyCode(inputKey);
                LogUtil.debug(TAG, "sendKeyEvent keyCode is:" + keyCode + " value:" + value);
                if (keyCode != Protos.KeyCode.KEYCODE_UNKNOWN.getNumber()) {
                    InputSource inputSource = this.mGal.input;
                    if (value != 0) {
                        z = false;
                    }
                    inputSource.sendKeyEvent(keyCode, z);
                    handled = true;
                }
                LogUtil.info(TAG, "handleKeyEvent:" + handled);
                return handled;
            }
        }
        LogUtil.info(TAG, "handleKeyEvent is return");
        return false;
    }

    private Protos.KeyCode getValidKey(int key) {
        Protos.KeyCode result = Protos.KeyCode.KEYCODE_UNKNOWN;
        if (key >= 0 && key < Protos.KeyCode.values().length) {
            Protos.KeyCode inputKey = Protos.KeyCode.values()[key];
            if (this.mValidKeys != null) {
                int i = 0;
                while (true) {
                    int[] iArr = this.mValidKeys;
                    if (i >= iArr.length) {
                        break;
                    }
                    if (iArr[i] != inputKey.getNumber()) {
                        i++;
                    } else {
                        result = inputKey;
                        break;
                    }
                }
            }
        }
        LogUtil.verbose(TAG, "getValidKeyCode, input=" + key + " result=" + result.getNumber());
        return result;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.aap.input.HandleInputEvent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$projection$proto$Protos$KeyCode;

        static {
            int[] iArr = new int[Protos.KeyCode.values().length];
            $SwitchMap$com$google$android$projection$proto$Protos$KeyCode = iArr;
            try {
                iArr[Protos.KeyCode.KEYCODE_MEDIA_PREVIOUS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$KeyCode[Protos.KeyCode.KEYCODE_MEDIA_NEXT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private int translateKeyCode(Protos.KeyCode key) {
        int keyCode = Protos.KeyCode.KEYCODE_UNKNOWN.getNumber();
        int i = AnonymousClass1.$SwitchMap$com$google$android$projection$proto$Protos$KeyCode[key.ordinal()];
        if (i == 1) {
            if (StateManager.getInstance().getVoiceSessionState() != GalReceiver.VOICE_RECOGNITION_STATE_STARTED && !StateManager.getInstance().getVrFocusState()) {
                return Protos.KeyCode.KEYCODE_MEDIA_PREVIOUS.getNumber();
            }
            return keyCode;
        }
        if (i == 2) {
            if (StateManager.getInstance().getVoiceSessionState() != GalReceiver.VOICE_RECOGNITION_STATE_STARTED && !StateManager.getInstance().getVrFocusState()) {
                return Protos.KeyCode.KEYCODE_MEDIA_NEXT.getNumber();
            }
            return keyCode;
        }
        int keyCode2 = key.getNumber();
        LogUtil.info(TAG, "Got keyCode: " + keyCode2);
        return keyCode2;
    }

    public synchronized boolean handleKeyLongPressEvent(int key) {
        boolean handled = false;
        LogUtil.info(TAG, "handleKeyLongPressEvent key:" + key);
        if (this.mGal != null && this.mGal.input != null && key == Protos.KeyCode.KEYCODE_SEARCH.ordinal()) {
            Protos.KeyCode inputKey = getValidKey(key);
            int keyCode = translateKeyCode(inputKey);
            LogUtil.debug(TAG, "handleKeyLongPressEvent keyCode is:" + keyCode);
            if (keyCode != Protos.KeyCode.KEYCODE_UNKNOWN.getNumber()) {
                this.mGal.input.sendKeyLongEvent(keyCode);
                handled = true;
            }
            LogUtil.info(TAG, "handleKeyLongPressEvent:" + handled);
            return handled;
        }
        LogUtil.info(TAG, "handleKeyLongPressEvent is return");
        return false;
    }

    public void onTouch(MotionEvent event) {
        if (this.mTouchDebugSwitcher) {
            int xx = (int) event.getX();
            int yy = (int) event.getY();
            LogUtil.info(TAG, "screenTouchEvent onTouch MD x:" + xx + " y:" + yy);
        }
        synchronized (this.mLock) {
            if (this.mGal != null && this.mGal.input != null && this.mAapVideoManager != null) {
                MotionEvent ev = this.mAapVideoManager.transformMotion(event);
                if (this.mTouchDebugSwitcher) {
                    int xx1 = (int) ev.getX();
                    int yy1 = (int) ev.getY();
                    LogUtil.info(TAG, "screenTouchEvent send to MD x:" + xx1 + " y:" + yy1);
                }
                this.mGal.input.sendTouchEvent(ev);
                ev.recycle();
            }
        }
    }

    public void sendNextKey(boolean isDown) {
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.input != null && StateManager.getInstance().getVoiceSessionState() != GalReceiver.VOICE_RECOGNITION_STATE_STARTED && !StateManager.getInstance().getVrFocusState()) {
            LogUtil.verbose(TAG, "sendNextKey isDown :" + isDown);
            this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_MEDIA_NEXT.getNumber(), isDown);
        }
    }

    public void sendPreviousKey(boolean isDown) {
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.input != null && StateManager.getInstance().getVoiceSessionState() != GalReceiver.VOICE_RECOGNITION_STATE_STARTED && !StateManager.getInstance().getVrFocusState()) {
            LogUtil.verbose(TAG, "sendPreviousKey isDown :" + isDown);
            this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_MEDIA_PREVIOUS.getNumber(), isDown);
        }
    }

    public void sendPlayKey(boolean isDown) {
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.input != null) {
            LogUtil.verbose(TAG, "sendPlayKey isDown :" + isDown);
            this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_MEDIA_PLAY.getNumber(), isDown);
        }
    }

    public void sendPauseKey(boolean isDown) {
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.input != null) {
            LogUtil.verbose(TAG, "sendPauseKey isDown :" + isDown);
            this.mGal.input.sendKeyEvent(Protos.KeyCode.KEYCODE_MEDIA_PAUSE.getNumber(), isDown);
        }
    }
}
