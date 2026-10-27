package com.ts.androidauto.app.model.hardkey;

import android.car.Car;
import android.car.media.CarEnhancedAudioManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFocusInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.KeyEvent;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener;
import com.ts.car.core.ServiceProvider;
import java.util.List;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.input.IHardKeyManager;
import ts.car.service.input.IKeyEventListener;
import ts.car.service.input.IKeyLongPressEventListener;

/* JADX INFO: loaded from: classes2.dex */
public class HardKeyModel extends IModel {
    private static final String ACTION_VOICE_REQUEST_RELEASE = "android.broadcast.voice.request.release";
    private static final String ACTION_VOICE_REQUEST_START = "android.broadcast.voice.request.start";
    private static final String KEY_USE_MIC = "usemic";
    private static final String KEY_VOICE_IDENTITY = "who";
    private static final int MAX_WAIT_TIME = 200000;
    private static final long MILLISECOND_DEALY = 500;
    public static final int PLAY_PAUSE_VALUE = 4;
    public static final int ROTATE_VALUE = 0;
    private static final String TAG = "AAP" + HardKeyModel.class.getSimpleName();
    public static final String USER_DEFIND_VALUE_7B = "byd_userdefind_value_7b";
    public static final String USER_DEFIND_VALUE_MC_B6 = "byd_userdefind_value_mc_b6";
    private static final String VALUE_FALSE = "false";
    private static final String VALUE_TRUE = "true";
    private static final String VOICE_IDENTITY_AA = "androidauto";
    private static final String VOICE_IDENTITY_BYD = "bydvoice";
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private IAndroidAutoRemoteUiManagerListener mAndroidAutoRemoteUiManagerListener;
    private Car mCar;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private Context mContext;
    private Handler mHandler;
    private IHardKeyManager mHardKeyManager;
    private boolean mIsLongPress;
    private boolean mIsMediaFocus;
    private boolean mIsNativeVrActive;
    private boolean mIsVrFocus;
    private boolean mIsVrKeyUp;
    private VehicleConst.AapHardkeyEvent mPressingHardKey;
    private VrBroadcastReceiver mVrBroadcastReceiver;
    public IKeyEventListener mkeyListener;
    public IKeyLongPressEventListener mkeyLongPressListener;

    public HardKeyModel(Looper looper) {
        super(looper);
        this.mIsMediaFocus = false;
        this.mIsVrFocus = false;
        this.mPressingHardKey = null;
        this.mHardKeyManager = null;
        this.mAndroidAutoRemoteUiManagerListener = new AndroidAutoRemoteUiManagerListener();
        this.mVrBroadcastReceiver = null;
        this.mIsNativeVrActive = false;
        this.mIsVrKeyUp = false;
        this.mIsLongPress = false;
        this.mkeyLongPressListener = new IKeyLongPressEventListener() { // from class: com.ts.androidauto.app.model.hardkey.HardKeyModel.1
            public void onKeyLongPressed(KeyEvent event, int sceneType, int targetDisplay) {
                if (event == null) {
                    LogUtil.warning(HardKeyModel.TAG, "onKeyLongPressed, KeyEvent is null.");
                    return;
                }
                int keyCode = event.getKeyCode();
                int keyAction = event.getAction();
                LogUtil.debug(HardKeyModel.TAG, "onKeyEvent keycode: " + keyCode + " keyAction: " + keyAction + " sceneType: " + sceneType);
            }
        };
        this.mkeyListener = new IKeyEventListener() { // from class: com.ts.androidauto.app.model.hardkey.HardKeyModel.3
            public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
                if (sceneType != 505) {
                    LogUtil.info(HardKeyModel.TAG, "onKeyEvent, scene type is not AndroidAuto related.");
                    return;
                }
                if (event == null) {
                    LogUtil.warning(HardKeyModel.TAG, "onKeyEvent, KeyEvent is null.");
                    return;
                }
                int keyCode = event.getKeyCode();
                int keyAction = event.getAction();
                LogUtil.debug(HardKeyModel.TAG, "onKeyEvent keycode: " + keyCode + " keyAction: " + keyAction + " sceneType: " + sceneType);
                if (keyCode == 2) {
                    int functionKeyCode = Settings.Global.getInt(HardKeyModel.this.mContext.getContentResolver(), HardKeyModel.USER_DEFIND_VALUE_7B, 0);
                    LogUtil.info(HardKeyModel.TAG, "functionKeyCode 7b =" + functionKeyCode);
                    if (functionKeyCode == 4) {
                        HardKeyModel.this.handleMediaKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE, 0);
                        HardKeyModel.this.handleMediaKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE, 1);
                        return;
                    }
                    return;
                }
                if (keyCode == 10) {
                    HardKeyModel.this.handleMediaKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_NEXT, keyAction);
                    return;
                }
                if (keyCode != 7) {
                    if (keyCode == 8) {
                        HardKeyModel.this.handleMediaKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PREVIOUS, keyAction);
                        return;
                    }
                    return;
                }
                int functionKeyCode2 = Settings.Global.getInt(HardKeyModel.this.mContext.getContentResolver(), HardKeyModel.USER_DEFIND_VALUE_MC_B6, 0);
                LogUtil.info(HardKeyModel.TAG, "functionKeyCode b6 =" + functionKeyCode2);
                if (functionKeyCode2 == 4) {
                    HardKeyModel.this.handleMediaKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE, 0);
                    HardKeyModel.this.handleMediaKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY_PAUSE, 1);
                }
            }
        };
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        this.mHandler = new Handler();
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        this.mAndroidAutoRemoteUiManager = androidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.addAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
        }
        new Thread(new Runnable() { // from class: com.ts.androidauto.app.model.hardkey.-$$Lambda$HardKeyModel$NP4mKySE7QaNBLqAH-7hmuEHnq4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$init$0$HardKeyModel();
            }
        }).start();
        this.mHardKeyManager = (IHardKeyManager) ServiceProvider.get(IHardKeyManager.class, this.mContext);
        registerVrBroadcastReceiver();
    }

    public /* synthetic */ void lambda$init$0$HardKeyModel() {
        LogUtil.info(TAG, "init thread start");
        initCarAudio();
    }

    public void initCarAudio() {
        Car.createCar(this.mContext, null, 200000L, new Car.CarServiceLifecycleListener() { // from class: com.ts.androidauto.app.model.hardkey.-$$Lambda$HardKeyModel$wELVL2uzDzb0e6wKzq8HTnQ0BzU
            @Override // android.car.Car.CarServiceLifecycleListener
            public final void onLifecycleChanged(Car car, boolean z) {
                this.f$0.lambda$initCarAudio$1$HardKeyModel(car, z);
            }
        });
    }

    public /* synthetic */ void lambda$initCarAudio$1$HardKeyModel(Car car, boolean ready) {
        if (ready) {
            this.mCar = car;
            if (car != null) {
                this.mCarEnhancedAudioManager = (CarEnhancedAudioManager) car.getCarManager("enhanced_audio");
                LogUtil.debug(TAG, "init() mCarEnhancedAudioManager:" + this.mCarEnhancedAudioManager);
                return;
            }
            return;
        }
        this.mCarEnhancedAudioManager = null;
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void uninit() {
        VrBroadcastReceiver vrBroadcastReceiver;
        LogUtil.info(TAG, "uninit");
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.removeAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
            this.mAndroidAutoRemoteUiManager = null;
        }
        if (this.mHardKeyManager != null) {
            this.mHardKeyManager = null;
        }
        if (this.mCar != null) {
            this.mCar = null;
        }
        Context context = this.mContext;
        if (context != null && (vrBroadcastReceiver = this.mVrBroadcastReceiver) != null) {
            context.unregisterReceiver(vrBroadcastReceiver);
            this.mVrBroadcastReceiver = null;
        }
        removeCallbacksAndMessages(null);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AndroidAutoRemoteUiManagerListener implements IAndroidAutoRemoteUiManagerListener {
        private AndroidAutoRemoteUiManagerListener() {
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onStatusChanged(final int linkStatus) {
            HardKeyModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.model.hardkey.-$$Lambda$HardKeyModel$AndroidAutoRemoteUiManagerListener$ggarD_oaaRPlQRTv2jFcT4wTqm4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStatusChanged$0$HardKeyModel$AndroidAutoRemoteUiManagerListener(linkStatus);
                }
            });
        }

        public /* synthetic */ void lambda$onStatusChanged$0$HardKeyModel$AndroidAutoRemoteUiManagerListener(int linkStatus) {
            if (linkStatus == 4) {
                HardKeyModel.this.addKeyEventListener(505);
                HardKeyModel.this.addLongPressEventListener(505);
            } else if (linkStatus == 5 || linkStatus == 1) {
                HardKeyModel.this.mPressingHardKey = null;
                HardKeyModel.this.mIsVrFocus = false;
                HardKeyModel.this.removeKeyEventListener(505);
                HardKeyModel.this.removeLongPressEventListener(505);
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onVoiceSessionStatusChanged(final boolean isVrFocus) {
            HardKeyModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.model.hardkey.-$$Lambda$HardKeyModel$AndroidAutoRemoteUiManagerListener$wlMzER385_WFANl82WEePBWIIfI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onVoiceSessionStatusChanged$1$HardKeyModel$AndroidAutoRemoteUiManagerListener(isVrFocus);
                }
            });
        }

        public /* synthetic */ void lambda$onVoiceSessionStatusChanged$1$HardKeyModel$AndroidAutoRemoteUiManagerListener(boolean isVrFocus) {
            HardKeyModel.this.mIsVrFocus = isVrFocus;
            if (HardKeyModel.this.mIsVrFocus) {
                HardKeyModel.this.sendVrBroadcastReceiver(HardKeyModel.ACTION_VOICE_REQUEST_START);
            } else {
                HardKeyModel.this.sendVrBroadcastReceiver(HardKeyModel.ACTION_VOICE_REQUEST_RELEASE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addKeyEventListener(int scene) {
        if (this.mkeyListener == null) {
            LogUtil.warning(TAG, "addKeyEventListener mkeyListener is null.");
            return;
        }
        try {
            LogUtil.info(TAG, "addKeyEventListener scene: " + scene);
            this.mHardKeyManager.addKeyEventListener(this.mkeyListener, scene);
            LogUtil.info(TAG, "addKeyEventListener success.");
        } catch (Exception ex) {
            LogUtil.error(TAG, "addKeyEventListener failed" + ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeKeyEventListener(int scene) {
        if (this.mkeyListener == null) {
            LogUtil.warning(TAG, "removeKeyEventListener mkeyListener is null.");
            return;
        }
        try {
            LogUtil.info(TAG, "removeKeyEventListener scene: " + scene);
            this.mHardKeyManager.removeKeyEventListener(this.mkeyListener, scene);
            LogUtil.info(TAG, "removeKeyEventListener success.");
        } catch (Exception ex) {
            LogUtil.error(TAG, "removeKeyEventListener failed" + ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLongPressEventListener(int scene) {
        if (this.mkeyLongPressListener == null) {
            LogUtil.warning(TAG, "addLongPressEventListener mkeyLongPressListener is null.");
            return;
        }
        try {
            LogUtil.info(TAG, "addLongPressEventListener scene: " + scene);
            this.mHardKeyManager.addLongPressEventListener(this.mkeyLongPressListener, scene);
            LogUtil.info(TAG, "addLongPressEventListener success.");
        } catch (Exception ex) {
            LogUtil.error(TAG, "addLongPressEventListener failed" + ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLongPressEventListener(int scene) {
        if (this.mkeyLongPressListener == null) {
            LogUtil.warning(TAG, "removeLongPressEventListener mkeyLongPressListener is null.");
            return;
        }
        try {
            LogUtil.info(TAG, "removeLongPressEventListener scene: " + scene);
            this.mHardKeyManager.removeLongPressEventListener(this.mkeyLongPressListener, scene);
            LogUtil.info(TAG, "removeLongPressEventListener success.");
        } catch (Exception ex) {
            LogUtil.error(TAG, "removeLongPressEventListener failed" + ex);
        }
    }

    private void judgeWhetherLongPressOfVrKey() {
        if (this.mHandler != null) {
            LogUtil.debug(TAG, "start  judge WhetherLongPressOfVrKey");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.androidauto.app.model.hardkey.HardKeyModel.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!HardKeyModel.this.mIsVrKeyUp) {
                        HardKeyModel.this.mIsLongPress = true;
                        if (!HardKeyModel.this.mIsVrFocus) {
                            HardKeyModel.this.sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH);
                        }
                    }
                }
            }, MILLISECOND_DEALY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMediaKeyEvent(VehicleConst.AapHardkeyEvent keyCode, int keyAction) {
        List<AudioFocusInfo> focusHolders;
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null && (focusHolders = carEnhancedAudioManager.getAudioFocusHoldersForZoneId(0)) != null) {
            judgeIsMediaFocus(focusHolders);
        }
        LogUtil.debug(TAG, "handleMediaVrEvent: keyCode=" + keyCode + " keyAction" + keyAction + " mIsMediaFocus=" + this.mIsMediaFocus);
        if (keyAction == 0) {
            if (this.mIsMediaFocus) {
                forceReleasePressingKey();
                sendKeyEvent(keyCode, 0);
                sendKeyEvent(keyCode, 1);
                this.mPressingHardKey = keyCode;
                return;
            }
            return;
        }
        if (this.mPressingHardKey == keyCode) {
            this.mPressingHardKey = null;
        }
    }

    private void judgeIsMediaFocus(List<AudioFocusInfo> focusHolders) {
        boolean isMediaFocus = false;
        for (AudioFocusInfo info : focusHolders) {
            if (info != null) {
                LogUtil.debug(TAG, "getAudioFocusHoldersForZoneId getUsage=" + info.getAttributes().getUsage());
                if (info.getAttributes().getUsage() == 33) {
                    isMediaFocus = true;
                }
            }
        }
        this.mIsMediaFocus = isMediaFocus;
        LogUtil.debug(TAG, "judgeIsMediaFocus mIsMediaFocus = " + this.mIsMediaFocus);
    }

    private void forceReleasePressingKey() {
        if (this.mPressingHardKey != null) {
            LogUtil.debug(TAG, "forceReleasePressingKey keyCode: " + this.mPressingHardKey + " ACTION_UP.");
            sendKeyEvent(this.mPressingHardKey, 1);
            this.mPressingHardKey = null;
        }
    }

    private void sendKeyEvent(VehicleConst.AapHardkeyEvent keyCode, int keyAction) {
        LogUtil.info(TAG, "sendKeyEvent keyCode: " + keyCode.ordinal() + " keyAction: " + keyAction);
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager == null) {
            LogUtil.warning(TAG, "mAndroidAutoRemoteUiManager is null.");
        } else {
            androidAutoRemoteUiManager.sendKeyEvent(keyCode, keyAction);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent keyCode) {
        LogUtil.info(TAG, "sendKeyLongPressEvent keyCode: " + keyCode.ordinal());
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager == null) {
            LogUtil.warning(TAG, "mAndroidAutoRemoteUiManager is null.");
        } else {
            androidAutoRemoteUiManager.sendKeyLongPressEvent(keyCode);
        }
    }

    private void registerVrBroadcastReceiver() {
        if (this.mContext != null) {
            this.mVrBroadcastReceiver = new VrBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_VOICE_REQUEST_START);
            filter.addAction(ACTION_VOICE_REQUEST_RELEASE);
            this.mContext.registerReceiver(this.mVrBroadcastReceiver, filter);
            LogUtil.debug(TAG, "registerVrBroadcastReceiver()==VrBroadcastReceiver");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendVrBroadcastReceiver(String action) {
        if (this.mContext != null) {
            Intent intent = new Intent(action);
            intent.putExtra(KEY_VOICE_IDENTITY, VOICE_IDENTITY_AA);
            if (ACTION_VOICE_REQUEST_START.equals(action)) {
                intent.putExtra(KEY_USE_MIC, VALUE_TRUE);
            } else {
                intent.putExtra(KEY_USE_MIC, VALUE_FALSE);
            }
            this.mContext.sendBroadcast(intent);
            LogUtil.debug(TAG, "sendVrBroadcastReceiver()+action=" + action + "mIsVrFocus=" + this.mIsVrFocus);
        }
    }

    private class VrBroadcastReceiver extends BroadcastReceiver {
        private VrBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context ctx, Intent intent) {
            String action = intent.getAction();
            String who = intent.getStringExtra(HardKeyModel.KEY_VOICE_IDENTITY);
            LogUtil.debug(HardKeyModel.TAG, "onReceive(): action=" + action + "who=" + who);
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    LogUtil.debug(HardKeyModel.TAG, "key:" + key + ",value:" + bundle.get(key));
                }
            }
            byte b = -1;
            int iHashCode = action.hashCode();
            if (iHashCode != -1988150117) {
                if (iHashCode == -658999808 && action.equals(HardKeyModel.ACTION_VOICE_REQUEST_RELEASE)) {
                    b = 1;
                }
            } else if (action.equals(HardKeyModel.ACTION_VOICE_REQUEST_START)) {
                b = 0;
            }
            if (b == 0) {
                if (HardKeyModel.VOICE_IDENTITY_BYD.equals(who)) {
                    boolean unused = HardKeyModel.this.mIsVrFocus;
                }
                if (HardKeyModel.VOICE_IDENTITY_BYD.equals(who)) {
                    HardKeyModel.this.mIsNativeVrActive = true;
                    return;
                }
                return;
            }
            if (b == 1 && HardKeyModel.VOICE_IDENTITY_BYD.equals(who)) {
                HardKeyModel.this.mIsNativeVrActive = false;
            }
        }
    }
}
