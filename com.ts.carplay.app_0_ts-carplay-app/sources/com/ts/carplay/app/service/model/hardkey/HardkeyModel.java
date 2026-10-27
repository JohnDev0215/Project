package com.ts.carplay.app.service.model.hardkey;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.app.service.common.IModel;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.app.service.model.hardkey.HardkeyModel;
import com.ts.carplay.utils.LogUtil;
import java.util.List;
import ts.car.service.input.IHardKeyManager;
import ts.car.service.input.IKeyEventListener;
import ts.car.service.input.IKeyLongPressEventListener;

/* JADX INFO: loaded from: classes.dex */
public class HardkeyModel extends IModel {
    private static final long MILLISECOND_DEALY = 500;
    public static final int PLAY_PAUSE_VALUE = 4;
    public static final int ROTATE_VALUE = 0;
    private static final String TAG = "HardkeyModel";
    public static final String USER_DEFIND_VALUE_7B = "byd_userdefind_value_7b";
    public static final String USER_DEFIND_VALUE_MC_B6 = "byd_userdefind_value_mc_b6";
    private BtPhoneCallStateListener mBtPhoneCallStateListener;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private ICarPlayRemoteManagerListener mCarPlayRemoteManagerListener;
    private Context mContext;
    private Handler mHandler;
    private IHardKeyManager mHardKeyManager;
    private boolean mIsBtPhoneCallActive;
    private boolean mIsCarPlayMedia;
    private boolean mIsCpPhoneActive;
    private boolean mIsLongPress;
    private boolean mIsNativeVrActive;
    private boolean mIsSiriActive;
    private boolean mIsVrKeyUp;
    private boolean mIscarPlayDisplayStatus;
    private final Object mLock;
    private TelephonyManager mTelephonyManager;
    private IKeyEventListener mkeyListener;
    public IKeyLongPressEventListener mkeyLongPressListener;

    public HardkeyModel(Looper looper) {
        super(looper);
        this.mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener(this, null);
        this.mIsNativeVrActive = false;
        this.mIsSiriActive = false;
        this.mIsCpPhoneActive = false;
        this.mIsCarPlayMedia = false;
        this.mHardKeyManager = null;
        this.mLock = new Object();
        this.mIsVrKeyUp = false;
        this.mIsLongPress = false;
        this.mIscarPlayDisplayStatus = false;
        this.mTelephonyManager = null;
        this.mIsBtPhoneCallActive = false;
        this.mkeyLongPressListener = new AnonymousClass1();
        this.mkeyListener = new AnonymousClass2();
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        this.mHandler = new Handler();
        CarPlayRemoteManager carPlayRemoteManager = CarPlayRemoteManager.getInstance();
        this.mCarPlayRemoteManager = carPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.addCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
        }
        this.mHardKeyManager = (IHardKeyManager) ServiceProvider.get(IHardKeyManager.class, this.mContext);
        this.mBtPhoneCallStateListener = new BtPhoneCallStateListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        if (telephonyManager != null) {
            telephonyManager.listen(this.mBtPhoneCallStateListener, 32);
        }
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.removeCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
            this.mCarPlayRemoteManager = null;
        }
        removeCallbacksAndMessages(null);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        private CarPlayRemoteManagerListener() {
        }

        /* synthetic */ CarPlayRemoteManagerListener(HardkeyModel x0, AnonymousClass1 x1) {
            this();
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlaySessionStateChanged(final int status) {
            HardkeyModel.this.post(new Runnable() { // from class: com.ts.carplay.app.service.model.hardkey.-$$Lambda$HardkeyModel$CarPlayRemoteManagerListener$4tuyFJs01DztTcGi0_uTXiCEDtw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCarPlaySessionStateChanged$0$HardkeyModel$CarPlayRemoteManagerListener(status);
                }
            });
        }

        public /* synthetic */ void lambda$onCarPlaySessionStateChanged$0$HardkeyModel$CarPlayRemoteManagerListener(int status) {
            if (status == 2) {
                HardkeyModel.this.addKeyEventListener(509);
                HardkeyModel.this.addLongPressEventListener(509);
                HardkeyModel.this.addKeyEventListener(510);
                HardkeyModel.this.addKeyEventListener(511);
                return;
            }
            if (status == 3) {
                HardkeyModel.this.removeKeyEventListener(509);
                HardkeyModel.this.removeLongPressEventListener(509);
                HardkeyModel.this.removeKeyEventListener(510);
                HardkeyModel.this.removeKeyEventListener(511);
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onAppStatesChanged(int appState, int entity, int speechMode) {
            LogUtil.info(HardkeyModel.TAG, "onAppStatesChanged appState: " + appState + ", entity: " + entity + ", speechMode: " + speechMode);
            if (appState == 0) {
                HardkeyModel.this.mIsCpPhoneActive = entity == 1;
            } else if (appState == 2) {
                HardkeyModel.this.mIsNativeVrActive = entity == 2;
                HardkeyModel.this.mIsSiriActive = entity == 1;
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlayMediaFocusState(boolean isCarPlayMedia) {
            LogUtil.info(HardkeyModel.TAG, "onCarPlayMediaFocusState :" + isCarPlayMedia);
            HardkeyModel.this.mIsCarPlayMedia = isCarPlayMedia;
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlayDisplayStatusChanged(boolean isShow) {
            LogUtil.debug(HardkeyModel.TAG, "onCarPlayDisplayStatusChanged isShow: " + isShow);
            HardkeyModel.this.mIscarPlayDisplayStatus = isShow;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBtCallState(int state) {
        LogUtil.debug(TAG, "notifyBtCallState state: " + state);
        this.mIsBtPhoneCallActive = false;
        if (state == 1 || state == 2) {
            this.mIsBtPhoneCallActive = true;
        }
    }

    private class BtPhoneCallStateListener extends PhoneStateListener {
        public BtPhoneCallStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
            LogUtil.debug(HardkeyModel.TAG, "BtPhoneCallStateListener onServiceStateChanged: " + serviceState);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int state, String inComingNumber) {
            LogUtil.debug(HardkeyModel.TAG, "BtPhoneCallStateListener state: " + state + " inComingNumber: " + inComingNumber);
            HardkeyModel.this.notifyBtCallState(state);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            super.onCellInfoChanged(cellInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addKeyEventListener(int scene) {
        if (this.mkeyListener == null) {
            LogUtil.warning(TAG, "addKeyEventListener mIKeyEventListener is null.");
            return;
        }
        synchronized (this.mLock) {
            try {
                LogUtil.debug(TAG, "addKeyEventListener scene: " + scene);
                this.mHardKeyManager.addKeyEventListener(this.mkeyListener, scene);
                LogUtil.debug(TAG, "addKeyEventListener success.");
            } catch (Exception ex) {
                LogUtil.warning(TAG, "addKeyEventListener failed" + ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeKeyEventListener(int scene) {
        if (this.mkeyListener == null) {
            LogUtil.warning(TAG, "unregisterHardKeyListener mIKeyEventListener is null.");
            return;
        }
        synchronized (this.mLock) {
            try {
                LogUtil.debug(TAG, "removeKeyEventListener scene: " + scene);
                this.mHardKeyManager.removeKeyEventListener(this.mkeyListener, scene);
                LogUtil.debug(TAG, "removeKeyEventListener success.");
            } catch (Exception ex) {
                LogUtil.warning(TAG, "removeKeyEventListener failed" + ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLongPressEventListener(int scene) {
        if (this.mkeyLongPressListener == null) {
            LogUtil.warning(TAG, "addLongPressEventListener mIKeyEventListener is null.");
            return;
        }
        synchronized (this.mLock) {
            try {
                LogUtil.debug(TAG, "addLongPressEventListener scene: " + scene);
                this.mHardKeyManager.addLongPressEventListener(this.mkeyLongPressListener, scene);
                LogUtil.debug(TAG, "addLongPressEventListener success.");
            } catch (Exception ex) {
                LogUtil.warning(TAG, "addLongPressEventListener failed" + ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLongPressEventListener(int scene) {
        if (this.mkeyLongPressListener == null) {
            LogUtil.warning(TAG, "removeLongPressEventListener mIKeyEventListener is null.");
            return;
        }
        synchronized (this.mLock) {
            try {
                LogUtil.debug(TAG, "removeLongPressEventListener scene: " + scene);
                this.mHardKeyManager.removeLongPressEventListener(this.mkeyLongPressListener, scene);
                LogUtil.debug(TAG, "removeLongPressEventListener success.");
            } catch (Exception ex) {
                LogUtil.warning(TAG, "removeLongPressEventListener failed" + ex);
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.service.model.hardkey.HardkeyModel$1, reason: invalid class name */
    class AnonymousClass1 implements IKeyLongPressEventListener {
        AnonymousClass1() {
        }

        public void onKeyLongPressed(KeyEvent event, int scene, int targetDisplay) {
            LogUtil.debug(HardkeyModel.TAG, "onKeyLongPress KeyEvent: " + event + " sceneType: " + scene + " targetDisplay: " + targetDisplay);
            HardkeyModel.this.post(new Runnable() { // from class: com.ts.carplay.app.service.model.hardkey.-$$Lambda$HardkeyModel$1$TJdZaB85ofVfGAiVaVNOxBo7ndU
                @Override // java.lang.Runnable
                public final void run() {
                    HardkeyModel.AnonymousClass1.lambda$onKeyLongPressed$0();
                }
            });
        }

        static /* synthetic */ void lambda$onKeyLongPressed$0() {
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.service.model.hardkey.HardkeyModel$2, reason: invalid class name */
    class AnonymousClass2 implements IKeyEventListener {
        AnonymousClass2() {
        }

        public void onKeyEvent(final KeyEvent event, final int scene, int targetDisplay) {
            HardkeyModel.this.post(new Runnable() { // from class: com.ts.carplay.app.service.model.hardkey.-$$Lambda$HardkeyModel$2$tChx9D4WQ4JMLVMWs8B1SVGh0tE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onKeyEvent$0$HardkeyModel$2(scene, event);
                }
            });
        }

        public /* synthetic */ void lambda$onKeyEvent$0$HardkeyModel$2(int scene, KeyEvent event) {
            if (scene != 509 && scene != 510 && scene != 511 && event.getKeyCode() != 2) {
                LogUtil.info(HardkeyModel.TAG, "onKeyEvent,scene type is not carplay related.");
                return;
            }
            if (event == null) {
                LogUtil.warning(HardkeyModel.TAG, "onKeyEvent, KeyEvent is null.");
                return;
            }
            int keycode = event.getKeyCode();
            int keyAction = event.getAction();
            LogUtil.debug(HardkeyModel.TAG, "mIKeyEventListener onKeyEvent scene: " + scene + " keycode: " + keycode + " keyAction:" + keyAction);
            if (keycode == 2) {
                int functionKeyCode = Settings.Global.getInt(HardkeyModel.this.mContext.getContentResolver(), HardkeyModel.USER_DEFIND_VALUE_7B, 0);
                LogUtil.info(HardkeyModel.TAG, "functionKeyCode 7b =" + functionKeyCode + "mIsCarPlayMedia = " + HardkeyModel.this.mIsCarPlayMedia);
                if (functionKeyCode == 4 && HardkeyModel.this.mIsCarPlayMedia) {
                    HardkeyModel.this.sendKeyEvent(0, 34);
                    HardkeyModel.this.sendKeyEvent(1, 34);
                    return;
                }
                return;
            }
            if (keycode == 10) {
                if (HardkeyModel.this.mIsCarPlayMedia) {
                    HardkeyModel.this.sendKeyEvent(keyAction, 35);
                    return;
                }
                return;
            }
            if (keycode == 7) {
                int functionKeyCode2 = Settings.Global.getInt(HardkeyModel.this.mContext.getContentResolver(), HardkeyModel.USER_DEFIND_VALUE_MC_B6, 0);
                LogUtil.info(HardkeyModel.TAG, "functionKeyCode b6 =" + functionKeyCode2 + "mIsCarPlayMedia = " + HardkeyModel.this.mIsCarPlayMedia);
                if (functionKeyCode2 == 4 && HardkeyModel.this.mIsCarPlayMedia) {
                    HardkeyModel.this.sendKeyEvent(0, 34);
                    HardkeyModel.this.sendKeyEvent(1, 34);
                    return;
                }
                return;
            }
            if (keycode == 8) {
                if (HardkeyModel.this.mIsCarPlayMedia) {
                    HardkeyModel.this.sendKeyEvent(keyAction, 36);
                }
            } else if (keycode == 12) {
                if (scene == 510) {
                    HardkeyModel.this.sendKeyEvent(keyAction, 1);
                }
            } else if (keycode == 13 && scene == 511) {
                if (HardkeyModel.this.mIscarPlayDisplayStatus || !HardkeyModel.this.mIsBtPhoneCallActive) {
                    HardkeyModel.this.sendKeyEvent(keyAction, 3);
                } else {
                    LogUtil.debug(HardkeyModel.TAG, "no need to send hardkey drop");
                }
            }
        }
    }

    private void judgeWhetherLongPressOfVrKey() {
        if (this.mHandler != null) {
            LogUtil.debug(TAG, "start  judge WhetherLongPressOfVrKey");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.carplay.app.service.model.hardkey.HardkeyModel.3
                @Override // java.lang.Runnable
                public void run() {
                    if (!HardkeyModel.this.mIsVrKeyUp) {
                        HardkeyModel.this.mIsLongPress = true;
                        if (!HardkeyModel.this.mIsSiriActive) {
                            HardkeyModel.this.mCarPlayRemoteManager.requestSiri(2, SystemClock.uptimeMillis());
                        }
                    }
                }
            }, MILLISECOND_DEALY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendKeyEvent(int keyAction, int keyCode) {
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager == null) {
            LogUtil.warning(TAG, "mCarPlayRemoteManager is null.");
            return;
        }
        if (keyAction == 0) {
            if (keyCode == 21) {
                if (this.mIsSiriActive) {
                    carPlayRemoteManager.requestSiri(2, SystemClock.uptimeMillis());
                    return;
                } else {
                    carPlayRemoteManager.requestSiri(1, SystemClock.uptimeMillis());
                    return;
                }
            }
            carPlayRemoteManager.sendCarPlayKey(keyCode, 0);
            return;
        }
        if (keyAction == 1) {
            if (keyCode == 21) {
                carPlayRemoteManager.requestSiri(3, SystemClock.uptimeMillis());
            } else {
                carPlayRemoteManager.sendCarPlayKey(keyCode, 1);
            }
        }
    }
}
