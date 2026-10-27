package com.ts.appservice.settings.impl.vradapter;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.KeyEvent;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.vradapter.ILocalVrSettingsService;
import java.util.List;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.input.IHardKeyManager;
import ts.car.service.input.IKeyEventListener;

/* JADX INFO: loaded from: classes.dex */
public class VrAdapter {
    private static final String AA_CP_ENABLE = "persist.sys.cpaa.status";
    private static final int ACTION_LONG_PRESS = -1;
    private static final int CPAA_INSTALL_STATE_AA = 2;
    private static final int CPAA_INSTALL_STATE_CP = 1;
    private static final int CPAA_INSTALL_STATE_CPAA = 3;
    private static final int CPAA_INSTALL_STATE_NONE = 0;
    private static final long MILLISECOND_DEALY = 500;
    private static final int NAVIBAR_MODE_MODE_ALL = 1;
    private static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    private static final int NAVIBAR_MODE_POWER_OFF = 0;
    private static final String QUIT_SCHEME = "byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String SETTING_BLUETOOTH = "byd_pad://byd_car_settings:2933/launcher?action=Bluetooth";
    public static final String TAG = VrAdapter.class.getSimpleName();
    private int mAacpEnable;
    AndroidAutoVrAdapter mAndroidAutoAdapter;
    CarPlayVrAdapter mCarPlayAdapter;
    Context mContext;
    private Handler mHardKeyHandler;
    private int mKeyAction;
    LocalVrAdapter mLocalVrAdapter;
    IHardKeyManager mHardKeyManager = null;
    private boolean mIsVrKeyUp = false;
    private boolean mIsLongPress = false;
    private boolean mAaVrLongPressStatus = false;
    public IKeyEventListener mkeyListener = new IKeyEventListener() { // from class: com.ts.appservice.settings.impl.vradapter.VrAdapter.1
        public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
            if (sceneType != 515) {
                LogUtil.error(VrAdapter.TAG, "onKeyEvent, scene type is not vr adapter related.");
                return;
            }
            if (event == null) {
                LogUtil.error(VrAdapter.TAG, "onKeyEvent, KeyEvent is null.");
                return;
            }
            int keyCode = event.getKeyCode();
            int keyAction = event.getAction();
            LogUtil.debug(VrAdapter.TAG, "onKeyEvent keycode: " + keyCode + " keyAction: " + keyAction + " sceneType: " + sceneType);
            if (keyCode == 11) {
                LogUtil.debug(VrAdapter.TAG, "onKeyEvent: KEYCODE_VR keyAction:" + keyAction);
                VrAdapter.this.mKeyAction = keyAction;
                if (keyAction == 0) {
                    VrAdapter.this.mIsVrKeyUp = false;
                    VrAdapter.this.mIsLongPress = false;
                    VrAdapter vrAdapter = VrAdapter.this;
                    vrAdapter.handlerKeyPress(vrAdapter.mKeyAction);
                    VrAdapter vrAdapter2 = VrAdapter.this;
                    vrAdapter2.screenOn(vrAdapter2.mContext);
                    VrAdapter.this.judgeWhetherLongPressOfVrKey();
                    return;
                }
                VrAdapter.this.mIsVrKeyUp = true;
                VrAdapter vrAdapter3 = VrAdapter.this;
                vrAdapter3.screenOn(vrAdapter3.mContext);
                if (!VrAdapter.this.mIsLongPress && VrAdapter.this.mHardKeyHandler != null) {
                    VrAdapter.this.mHardKeyHandler.removeCallbacksAndMessages(null);
                }
                VrAdapter vrAdapter4 = VrAdapter.this;
                vrAdapter4.handlerKeyPress(vrAdapter4.mKeyAction);
            }
        }
    };

    public VrAdapter(Context context) {
        this.mContext = context;
    }

    public void init() {
        LogUtil.info(TAG, "init");
        this.mCarPlayAdapter = CarPlayVrAdapter.getInstance(this.mContext);
        this.mAndroidAutoAdapter = AndroidAutoVrAdapter.getInstance(this.mContext);
        this.mHardKeyManager = (IHardKeyManager) ServiceProvider.get(IHardKeyManager.class, this.mContext);
        this.mHardKeyHandler = new Handler();
        addKeyEventListener(515);
    }

    public void initLocalVr(ILocalVrSettingsService localVrSettingsService) {
        LogUtil.info(TAG, "initLocalVr");
        this.mLocalVrAdapter = new LocalVrAdapter(this.mContext, localVrSettingsService);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerKeyPress(int event) {
        LogUtil.debug(TAG, "handlerKeyPress mIsLongPress" + this.mIsLongPress + "event:" + event);
        if (event == 0) {
            if (isAacpEnable() == 0) {
                LogUtil.debug(TAG, "handlerKeyPress key event action down and cpaa enable is false");
                return;
            }
            if (cpEnableStatus()) {
                if (!isCarPlayConnectPhone()) {
                    LogUtil.debug(TAG, "carplay not connect");
                } else if (!isCarPlaySiriActive()) {
                    requestSiri(1);
                } else {
                    requestSiri(2);
                }
            }
            if (aaEnableStatus()) {
                if (!isAndroidAutoConnectPhone()) {
                    LogUtil.debug(TAG, "android auto not connect");
                    return;
                } else if (isAAVrActive()) {
                    sendAAVrsKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH, 0);
                    return;
                } else {
                    LogUtil.debug(TAG, "longPress aaVR not Active");
                    return;
                }
            }
            return;
        }
        if (event == 1) {
            if (!this.mIsLongPress) {
                if (isAacpEnable() == 0) {
                    LogUtil.debug(TAG, "handlerKeyPress key event action up no long pressand cpaa enable is false to do local vr");
                    if (!isLocalVrActive()) {
                        startLocalVr();
                        return;
                    } else {
                        stopLocalVr();
                        return;
                    }
                }
                if (isAAVrActive() && aaEnableStatus()) {
                    sendAAVrsKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH, 1);
                    return;
                }
                if (isCarPlayConnectPhone() && cpEnableStatus()) {
                    requestSiri(3);
                }
                LogUtil.debug(TAG, "LocalVr state:" + getLocalVrState());
                if (!isLocalVrActive()) {
                    if (isCarPlaySiriActive()) {
                        LogUtil.debug(TAG, "siri active");
                        return;
                    } else {
                        startLocalVr();
                        return;
                    }
                }
                stopLocalVr();
                return;
            }
            if (isAacpEnable() == 0) {
                LogUtil.debug(TAG, "handlerKeyPress key event action up long pressand cpaa enable is false to return");
                return;
            }
            if (isCarPlayConnectPhone() && cpEnableStatus()) {
                requestSiri(3);
                if (isLocalVrActive()) {
                    stopLocalVr();
                }
            }
            if (isAndroidAutoConnectPhone() && aaEnableStatus() && isAAVrActive()) {
                LogUtil.debug(TAG, "aa active long up:");
                if (!this.mAaVrLongPressStatus) {
                    sendAAVrsKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH, 1);
                }
                if (isLocalVrActive()) {
                    stopLocalVr();
                }
            }
            this.mAaVrLongPressStatus = false;
            return;
        }
        if (this.mIsLongPress) {
            LogUtil.debug(TAG, "longPress no action");
            if (isAacpEnable() == 0) {
                LogUtil.debug(TAG, "handlerKeyPress key event defaultand cpaa enable is false");
                return;
            }
            if ((aaEnableStatus() || cpEnableStatus()) && !isAndroidAutoConnectPhone() && !isCarPlayConnectPhone()) {
                showDevicesListDialog();
                return;
            }
            if (cpEnableStatus()) {
                if (isCarPlayConnectPhone() && !isCarPlaySiriActive()) {
                    requestSiri(2);
                } else {
                    LogUtil.debug(TAG, "longPress carPlayVR isActive");
                }
            }
            if (aaEnableStatus()) {
                if (isAndroidAutoConnectPhone() && !isAAVrActive()) {
                    sendAAVrLongPressKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH);
                    this.mAaVrLongPressStatus = true;
                } else {
                    LogUtil.debug(TAG, "longPress aaVR isActive or is not connect");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeWhetherLongPressOfVrKey() {
        if (this.mHardKeyHandler != null) {
            LogUtil.debug(TAG, "start  judge WhetherLongPressOfVrKey");
            this.mHardKeyHandler.postDelayed(new Runnable() { // from class: com.ts.appservice.settings.impl.vradapter.VrAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!VrAdapter.this.mIsVrKeyUp) {
                        VrAdapter.this.mIsLongPress = true;
                        VrAdapter.this.handlerKeyPress(-1);
                    }
                }
            }, MILLISECOND_DEALY);
        }
    }

    private void addKeyEventListener(int scene) {
        if (this.mkeyListener == null) {
            LogUtil.warning(TAG, "addKeyEventListener mkeyListener is null.");
            return;
        }
        try {
            LogUtil.error(TAG, "addKeyEventListener scene: " + scene);
            this.mHardKeyManager.addKeyEventListener(this.mkeyListener, scene);
            LogUtil.info(TAG, "addKeyEventListener success.");
        } catch (Exception ex) {
            LogUtil.error(TAG, "addKeyEventListener failed" + ex);
        }
    }

    private void removeKeyEventListener(int scene) {
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

    public void showDevicesListDialog(Context context) {
        LogUtil.info(TAG, "show devices list dialog.");
        PackageManager pm = context.getPackageManager();
        Intent in = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=car_play"));
        in.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(in, 0);
        if (!resolveInfos.isEmpty()) {
            context.startService(in);
        }
    }

    private void showDevicesListDialog() {
        LogUtil.info(TAG, "show bluetooth list dialog.");
        PackageManager pm = this.mContext.getPackageManager();
        Intent in = new Intent("android.intent.action.VIEW", Uri.parse(SETTING_BLUETOOTH));
        in.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(in, 0);
        LogUtil.info(TAG, "----startIntentByPath----resolveInfos = " + resolveInfos);
        if (!resolveInfos.isEmpty()) {
            this.mContext.startService(in);
        }
    }

    private boolean isLocalVrActive() {
        return getLocalVrState() == 0;
    }

    private int getLocalVrState() {
        LocalVrAdapter localVrAdapter = this.mLocalVrAdapter;
        if (localVrAdapter == null) {
            LogUtil.error(TAG, "mLocalVrAdapter is null.");
            return -1;
        }
        int vrState = localVrAdapter.getVrState();
        LogUtil.info(TAG, "getLocalVrState:" + vrState);
        return vrState;
    }

    private void startLocalVr() {
        LogUtil.info(TAG, "startLocalVr");
        LocalVrAdapter localVrAdapter = this.mLocalVrAdapter;
        if (localVrAdapter == null) {
            LogUtil.error(TAG, "mLocalVrAdapter is null.");
        } else {
            localVrAdapter.doStartVr();
        }
    }

    private void stopLocalVr() {
        LogUtil.info(TAG, "stopLocalVr");
        LocalVrAdapter localVrAdapter = this.mLocalVrAdapter;
        if (localVrAdapter == null) {
            LogUtil.error(TAG, "mLocalVrAdapter is null.");
        } else {
            localVrAdapter.doStopVr();
        }
    }

    private void requestSiri(int value) {
        if (this.mCarPlayAdapter == null) {
            LogUtil.error(TAG, "requestSiri mCarPlayAdapter is null.");
        } else {
            if (isLocalVrActive()) {
                return;
            }
            this.mCarPlayAdapter.requestSiri(value);
        }
    }

    private boolean isCarPlaySiriActive() {
        if (this.mCarPlayAdapter == null) {
            LogUtil.error(TAG, "isCarPlaySiriActive CarPlayService is null.");
            return false;
        }
        LogUtil.info(TAG, "isCarPlaySiriActive acitive:" + this.mCarPlayAdapter.isSiriActive());
        return this.mCarPlayAdapter.isSiriActive();
    }

    private boolean isAAVrActive() {
        if (this.mAndroidAutoAdapter == null) {
            LogUtil.error(TAG, "isAAVrActive AndroidAutoService is null.");
            return false;
        }
        LogUtil.info(TAG, "isAAVrActive acitive:" + this.mAndroidAutoAdapter.isAaVrActive());
        return this.mAndroidAutoAdapter.isAaVrActive();
    }

    private boolean isAndroidAutoConnectPhone() {
        AndroidAutoVrAdapter androidAutoVrAdapter = this.mAndroidAutoAdapter;
        if (androidAutoVrAdapter == null) {
            LogUtil.error(TAG, "isAndroidAutoConnectPhone AndroidAutoService is null.");
            return false;
        }
        int sessionStatus = androidAutoVrAdapter.getAndroidAutoSessionStatus();
        LogUtil.error(TAG, "isAndroidAutoConnectPhone sessionStatus：" + sessionStatus);
        return sessionStatus == 4;
    }

    private boolean isCarPlayConnectPhone() {
        CarPlayVrAdapter carPlayVrAdapter = this.mCarPlayAdapter;
        if (carPlayVrAdapter == null) {
            LogUtil.error(TAG, "isCarPlayConnectPhone CarPlayService is null.");
            return false;
        }
        int sessionStatus = carPlayVrAdapter.getCarPlaySessionStatus();
        LogUtil.info(TAG, "isCarPlayConnectPhone sessionStatus：" + sessionStatus);
        return sessionStatus == 2;
    }

    private void sendAAVrLongPressKeyEvent(VehicleConst.AapHardkeyEvent event) {
        AndroidAutoVrAdapter androidAutoVrAdapter = this.mAndroidAutoAdapter;
        if (androidAutoVrAdapter == null) {
            LogUtil.error(TAG, "sendAAVrLongPressKeyEvent AndroidAutoService is null.");
        } else {
            androidAutoVrAdapter.sendKeyLongPressEvent(event);
        }
    }

    private void sendAAVrsKeyEvent(VehicleConst.AapHardkeyEvent event, int action) {
        AndroidAutoVrAdapter androidAutoVrAdapter = this.mAndroidAutoAdapter;
        if (androidAutoVrAdapter == null) {
            LogUtil.error(TAG, "sendAAVrsKeyEvent AndroidAutoService is null.");
        } else {
            androidAutoVrAdapter.sendKeyEvent(event, action);
        }
    }

    public void destroy() {
        removeKeyEventListener(515);
        if (this.mHardKeyManager != null) {
            this.mHardKeyManager = null;
        }
        Handler handler = this.mHardKeyHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHardKeyHandler = null;
        }
        if (this.mLocalVrAdapter != null) {
            this.mLocalVrAdapter = null;
        }
        CarPlayVrAdapter carPlayVrAdapter = this.mCarPlayAdapter;
        if (carPlayVrAdapter != null) {
            carPlayVrAdapter.destroy();
            this.mCarPlayAdapter = null;
        }
        if (this.mAndroidAutoAdapter != null) {
            this.mAndroidAutoAdapter = null;
        }
    }

    public void destroyLocalVr() {
        this.mLocalVrAdapter.destroyService();
        this.mLocalVrAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void screenOn(Context context) {
        if (getNavibarModeScreensaver(context) == 1) {
            LogUtil.info(TAG, "navibar_mode_on_screensaver is NAVIBAR_MODE_MODE_ALL, do not execute screen on action");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(QUIT_SCHEME));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentServices(intent, 0);
        if (resolveInfoList == null || resolveInfoList.isEmpty()) {
            LogUtil.error(TAG, "error, resolve service:com.byd.dialogservice.DialogService error!");
        } else {
            LogUtil.info(TAG, "screen on by vr key.");
            context.startService(intent);
        }
    }

    public int getNavibarModeScreensaver(Context ctx) {
        ContentResolver cr = ctx.getContentResolver();
        return Settings.Global.getInt(cr, NAVIBAR_MODE_ON_SCREENSAVER, 0);
    }

    private int isAacpEnable() {
        this.mAacpEnable = SystemProperties.getInt(AA_CP_ENABLE, 0);
        LogUtil.error(TAG, "mAACPEnable:" + this.mAacpEnable);
        return this.mAacpEnable;
    }

    private boolean cpEnableStatus() {
        boolean z = true;
        if (isAacpEnable() != 1 && isAacpEnable() != 3) {
            z = false;
        }
        boolean cpEnableStatus = z;
        LogUtil.debug(TAG, "Carplay enable status : " + cpEnableStatus);
        return cpEnableStatus;
    }

    private boolean aaEnableStatus() {
        boolean aaEnableStatus = isAacpEnable() == 2 || isAacpEnable() == 3;
        LogUtil.debug(TAG, "AndroidAuto enable status : " + aaEnableStatus);
        return aaEnableStatus;
    }
}
