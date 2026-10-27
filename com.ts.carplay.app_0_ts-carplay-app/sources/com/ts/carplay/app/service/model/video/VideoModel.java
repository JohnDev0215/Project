package com.ts.carplay.app.service.model.video;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.carplay.app.service.common.IModel;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.app.service.model.ModelConstant;
import com.ts.carplay.utils.LogUtil;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ota.CarVendorOtaManager;
import com.ts.lib.car.hardware.ota.OtaEventChangeValue;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class VideoModel extends IModel {
    private static final String ACTION_ECALL_STATE_CHANGE = "com.byd.system.ecall.ACTION_EVENT_CHANGE";
    private static final String ACTION_REQUEST_ECALL_STATE_UPDATE = "com.ts.carplay.ecall.ACTION_REQUEST_UPDATE";
    private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    private static final String ACTION_TAB_CLICK = "com.byd.action.navi.btn.click";
    private static final String ACTION_VR_END = "android.broadcast.voice.request.release";
    private static final String ACTION_VR_START = "android.broadcast.voice.request.start";
    private static final String BROADCAST_FINISH_CARPLAY_ACTIVITY = "com.ts.carplay.action.FINISH_ACTIVITY";
    private static final String BT_DIALOG_STATE_TAG = "show";
    private static final String BT_PHONE_ACTIVITY = "com.android.car.dialer.ui.activecall.InCallActivity";
    private static final String CARPLAY_DISPLAY_ACTIVITYNAME = "com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity";
    public static final String CLOSE_SAVER_SCHEME = "byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver";
    private static final String ECALL_WIDGET_EXTRA_LAYOUT = "isFullScreen";
    private static final int ECALL_WIDGET_EXTRA_LAYOUT_FULLSCREEN = 1;
    private static final int ECALL_WIDGET_EXTRA_LAYOUT_SMALLWINDOW = 0;
    private static final String ECALL_WIDGET_EXTRA_STATE = "isShow";
    private static final int ECALL_WIDGET_EXTRA_STATE_HIDEN = 0;
    private static final int ECALL_WIDGET_EXTRA_STATE_SHOWN = 1;
    private static final String FOCUSMODE_BACK_CAMERA = "backcamera";
    private static final String FOCUSMODE_BACK_CAMERA_FRAME = "backcamera_frame";
    private static final String FOCUSMODE_BT_DIALOG = "com.byd.carsettings.bt.dialog.state.change";
    private static final String FOCUSMODE_CARD = "com.byd.card";
    private static final String FOCUSMODE_CARPLAY = "com.ts.carplay.app";
    private static final String FOCUSMODE_CAR_SETTING = "com.byd.carsettings";
    private static final String FOCUSMODE_DISPLAYOFF = "displayoff";
    private static final String FOCUSMODE_ECALL = "com.byd.uereport";
    private static final String FOCUSMODE_HAVC = "com.byd.hvac";
    private static final String FOCUSMODE_HOTSPOT_DIALOG = "com.byd.carsettings.hotspot.dialog.state.change";
    private static final String FOCUSMODE_LAUNCHER = "com.android.launcher3";
    private static final String FOCUSMODE_LOCAL_MUSIC = "com.byd.localmusic";
    private static final String FOCUSMODE_MODE_STANDBY = "standby";
    private static final String FOCUSMODE_MY_CAR = "com.byd.mycar";
    private static final String FOCUSMODE_PANEL = "com.byd.action.panel.state.change";
    private static final String FOCUSMODE_PHONE_CALL_ACTIVITY = "com.byd.bluetoothcall";
    private static final String FOCUSMODE_SOPTITY = "com.telenav.scoutivi.spotify.byd";
    private static final String FOCUSMODE_SYSTEM_DIALOG = "system.dialog";
    private static final String FOCUSMODE_SYSTEM_UI = "com.android.systemui";
    private static final String FOCUSMODE_VR = "com.byd.bydvoice";
    private static final String FOCUSMODE_VR_SETTINGS = "com.byd.vrsettings";
    private static final String FOCUSMODE_WIFI_DIALOG = "com.byd.carsettings.wifi.dialog.state.change";
    private static final int GEAR_DRIVE = 4;
    private static final int GEAR_INVALID = 0;
    private static final int GEAR_NEUTRAL = 3;
    private static final int GEAR_PARK = 1;
    private static final int GEAR_REVERSE = 2;
    private static final int IVI_PM_DEGRADED = 3;
    private static final int IVI_PM_DISPLAY_ON = 10;
    private static final int IVI_PM_OFF = 0;
    private static final int IVI_PM_PRE_STARTUP = 1;
    private static final int IVI_PM_REFLASH = 6;
    private static final int IVI_PM_REMOTE_FOTA = 7;
    private static final int IVI_PM_SLEEP = 8;
    private static final int IVI_PM_STANDBY = 4;
    private static final int IVI_PM_STARTUP = 2;
    private static final int IVI_PM_STR = 5;
    private static final int IVI_PM_STR_SUSPENDING = 9;
    private static final int IVI_PM_TOD = 12;
    private static final int LANDSCAPE_MODE = 1;
    private static final int MAX_TASK_NUM = 10;
    public static final int NAVIBAR_MODE_MODE_AC = 2;
    public static final int NAVIBAR_MODE_MODE_ALL = 1;
    public static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    public static final int NAVIBAR_MODE_POWER_OFF = 0;
    private static final String PACKAGE_NAME_AVM = "com.byd.avm";
    private static final int PAIRING_REQUEST_PRIORITY = 1000;
    private static final String PANEL_START_ACTIVITY_TAG = "isStartActivity";
    private static final String PANEL_STATE_TAG = "expanded";
    public static final int SCREEN_OFF_TYPE_ON = 1;
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String SYSTEMUI_RECENT = "com.android.systemui.recents.RecentsActivity";
    public static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    public static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    public static String SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION = "com.byd.carsettings.connect_device.dialog.state.change";
    private static final String TAB_CLICK_KEY = "btn_value";
    private static final String TAB_CLICK_VALUE = "carsettings";
    private static final String TAG = "VideoModel";
    private static final String VR_ISSHOW_KEY = "show";
    private static final String VR_USER_KEY = "who";
    private static final String VR_USER_NAME_BYD = "bydvoice";
    private static final int XCALL_STATUS_CALL_CONNECTED = 6;
    private static final int XCALL_STATUS_CALL_CONNECTING = 5;
    private static final int XCALL_STATUS_CALL_DATA_SENDING = 7;
    private static final int XCALL_STATUS_CALL_END = 8;
    private static final int XCALL_STATUS_CALL_FAILED = 4;
    private static final int XCALL_STATUS_CALL_FAILED_RECONNECTION = 3;
    private static final int XCALL_STATUS_CALL_START = 1;
    private static final int XCALL_STATUS_INCOMING_CALL = 2;
    private static final int XCALL_STATUS_INVALID = 0;
    private final Object avmLock;
    private CarVendorOtaManager carManager;
    private ContentResolver contentResolver;
    private ActivityManager mAm;
    private IAvmServiceConnectionListener mAvmConnection;
    private AvmManager.AvmStatusListener mAvmStatusListener;
    private BtPhoneCallStateListener mBtPhoneCallStateListener;
    private Car mCarClient;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private CarPlayRemoteManagerListener mCarPlayRemoteManagerListener;
    private CarPowerEventListener mCarPowerEventListener;
    private CarPowerManager mCarPowerManager;
    private Context mContext;
    private boolean mCurrentCallStateActive;
    private int mCurrentGearPosition;
    BroadcastReceiver mEcallWidgetStateReceiver;
    private Stack<FocusModeInfo> mFocusModeList;
    private List<FocusModeInfo> mHigherFocusInfoList;
    private IActivityManager mIam;
    private boolean mIsAvm;
    private boolean mIsBtDialogShowing;
    private boolean mIsBtPhoneCallActive;
    private boolean mIsCarPlayConnected;
    private boolean mIsClickHome;
    private boolean mIsClickSettings;
    private boolean mIsCpCallActive;
    private boolean mIsDevConDialogShowing;
    private boolean mIsDeviceProvisioned;
    private boolean mIsEcallActive;
    private boolean mIsFirstFocusChange;
    private boolean mIsHotspotDialogShowing;
    private boolean mIsNativeCallActive;
    private boolean mIsNativeVrActive;
    private boolean mIsOobDialogShown;
    private boolean mIsPreEcallActive;
    private boolean mIsPreNativeCallActive;
    private boolean mIsServiceConnection;
    private boolean mIsStandbyMode;
    private FocusModeInfo mLastFocusModeInfo;
    BroadcastReceiver mPanelReceiver;
    private int mPreNativeCallState;
    private ProvisioningObserver mProvisioningObserver;
    public int mScreenOffType;
    BroadcastReceiver mSettingDialogReceiver;
    BroadcastReceiver mSystemDialogStateReceiver;
    private TaskStackListener mTaskStackListener;
    private TelephonyManager mTelephonyManager;
    private PackageManager packageManager;
    private SettingsObserver settingsObserver;
    BroadcastReceiver tabCLickReceiver;
    BroadcastReceiver vrReceiver;

    public VideoModel(Looper looper) {
        super(looper);
        this.mIsOobDialogShown = false;
        this.mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener();
        this.mProvisioningObserver = null;
        this.mHigherFocusInfoList = new ArrayList();
        this.mFocusModeList = new Stack<>();
        this.mLastFocusModeInfo = null;
        this.mIsStandbyMode = false;
        this.mIsFirstFocusChange = false;
        this.mIsCarPlayConnected = false;
        this.mIsServiceConnection = false;
        this.mIsDeviceProvisioned = false;
        this.mCurrentCallStateActive = false;
        this.mIsNativeVrActive = false;
        this.mCurrentGearPosition = 0;
        this.mTelephonyManager = null;
        this.mIsBtPhoneCallActive = false;
        this.mIsCpCallActive = false;
        this.mIsNativeCallActive = false;
        this.mIsPreNativeCallActive = false;
        this.mPreNativeCallState = 0;
        this.mIsPreEcallActive = false;
        this.mIsEcallActive = false;
        this.mIsClickHome = false;
        this.mIsClickSettings = false;
        this.mIsBtDialogShowing = false;
        this.mIsDevConDialogShowing = false;
        this.mIsHotspotDialogShowing = false;
        this.mIsAvm = false;
        this.avmLock = new Object();
        this.mCarPowerEventListener = new CarPowerEventListener() { // from class: com.ts.carplay.app.service.model.video.VideoModel.1
            public void onCarPowerWorkModeChangeEvent(CarPowerWorkModeStatus carPowerWorkModeStatus) {
                if (carPowerWorkModeStatus == null) {
                    LogUtil.debug(VideoModel.TAG, "carPowerWorkModeStatus is null");
                    return;
                }
                int carPowerWorkModeStatusEnum = carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal();
                LogUtil.debug(VideoModel.TAG, "carPowerWorkModeStatus: " + carPowerWorkModeStatusEnum + ", mIsStandbyMode: " + VideoModel.this.mIsStandbyMode);
                if (carPowerWorkModeStatusEnum != 4) {
                    if (carPowerWorkModeStatusEnum == 10 && VideoModel.this.mIsStandbyMode) {
                        VideoModel.this.mIsStandbyMode = false;
                        VideoModel.this.exitStack(VideoModel.FOCUSMODE_MODE_STANDBY);
                        return;
                    }
                    return;
                }
                if (!VideoModel.this.mIsStandbyMode) {
                    VideoModel.this.mIsStandbyMode = true;
                    if (VideoModel.this.mCarPlayRemoteManager != null && VideoModel.this.mIsCarPlayConnected) {
                        VideoModel.this.mCarPlayRemoteManager.setLastUserMode(1, VideoModel.this.isCarPlayInStack());
                    }
                    VideoModel videoModel = VideoModel.this;
                    videoModel.enterStack(videoModel.new FocusModeInfo(VideoModel.FOCUSMODE_MODE_STANDBY, videoModel.findPty(VideoModel.FOCUSMODE_MODE_STANDBY), VideoModel.this.findBid(VideoModel.FOCUSMODE_MODE_STANDBY)));
                }
            }

            public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
            }

            public void onCarPowerServiceConnected() {
            }

            public void onCarPowerServiceDisconnected() {
            }

            public void onCarPowerEvent(int i, int i1, CarPowerData carPowerData) {
            }

            public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
                LogUtil.error(VideoModel.TAG, " onCarPowerDisplayEvent type : " + type + " displayPanelId : " + displayPanelId + " value : " + value);
            }
        };
        this.mSettingDialogReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.video.VideoModel.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    LogUtil.debug(VideoModel.TAG, "mSettingDialogReceiver.onReceive() action=" + action);
                    if (action != null) {
                        if (action.equals(VideoModel.FOCUSMODE_BT_DIALOG) || action.equals(VideoModel.FOCUSMODE_WIFI_DIALOG) || action.equals(VideoModel.SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION) || action.equals(VideoModel.FOCUSMODE_HOTSPOT_DIALOG)) {
                            boolean isShow = intent.getBooleanExtra("show", true);
                            LogUtil.info(VideoModel.TAG, "SYS_DIALOG_CHANGE_ACTION isShow:" + isShow + " mIsCarPlayConnected:" + VideoModel.this.mIsCarPlayConnected + " mIsClickHome:" + VideoModel.this.mIsClickHome);
                            if (action.equals(VideoModel.FOCUSMODE_BT_DIALOG)) {
                                VideoModel.this.mIsBtDialogShowing = isShow;
                            }
                            if (action.equals(VideoModel.SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION)) {
                                VideoModel.this.mIsDevConDialogShowing = isShow;
                            }
                            if (action.equals(VideoModel.FOCUSMODE_HOTSPOT_DIALOG)) {
                                VideoModel.this.mIsHotspotDialogShowing = isShow;
                            }
                            if (VideoModel.this.mIsCarPlayConnected && !VideoModel.this.mIsClickHome) {
                                if (!isShow) {
                                    VideoModel.this.exitStack(action);
                                    return;
                                }
                                FocusModeInfo info = VideoModel.this.new FocusModeInfo();
                                info.setFocus(action);
                                info.setBid(VideoModel.this.findBid(action));
                                info.setPty(VideoModel.this.findPty(action));
                                VideoModel.this.enterStack(info);
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.mPanelReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.video.VideoModel.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    LogUtil.debug(VideoModel.TAG, "mPanelReceiver.onReceive() action=" + action);
                    if (action != null && action.equals(VideoModel.FOCUSMODE_PANEL)) {
                        boolean isShow = intent.getBooleanExtra(VideoModel.PANEL_STATE_TAG, true);
                        boolean isStartActivity = intent.getBooleanExtra(VideoModel.PANEL_START_ACTIVITY_TAG, false);
                        LogUtil.info(VideoModel.TAG, "SYS_PANEL_STATE_CHANGE_ACTION isShow:" + isShow + " mIsCarPlayConnected:" + VideoModel.this.mIsCarPlayConnected + " isStartActivity:" + isStartActivity + " mIsClickHome:" + VideoModel.this.mIsClickHome);
                        if (VideoModel.this.mIsCarPlayConnected && !VideoModel.this.mIsClickHome && !VideoModel.this.mIsClickSettings) {
                            if (isShow) {
                                FocusModeInfo info = VideoModel.this.new FocusModeInfo();
                                info.setFocus(VideoModel.FOCUSMODE_PANEL);
                                info.setBid(VideoModel.this.findBid(VideoModel.FOCUSMODE_PANEL));
                                info.setPty(VideoModel.this.findPty(VideoModel.FOCUSMODE_PANEL));
                                VideoModel.this.enterStack(info);
                            } else if (!isStartActivity) {
                                VideoModel.this.exitStack(VideoModel.FOCUSMODE_PANEL);
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.vrReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.video.VideoModel.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    String user = intent.getStringExtra(VideoModel.VR_USER_KEY);
                    boolean isShowAftExit = intent.getBooleanExtra("show", true);
                    LogUtil.debug(VideoModel.TAG, "vrReceiver.onReceive() user:" + user + " mIsNativeVrActive:" + VideoModel.this.mIsNativeVrActive + " action:" + action + " mIsCarPlayConnected:" + VideoModel.this.mIsCarPlayConnected + " isShowAftExit: " + isShowAftExit);
                    if (VideoModel.ACTION_VR_START.equals(action)) {
                        if (VideoModel.VR_USER_NAME_BYD.equals(user) && !VideoModel.this.mIsNativeVrActive) {
                            VideoModel.this.mIsNativeVrActive = true;
                            FocusModeInfo info = VideoModel.this.new FocusModeInfo();
                            info.setFocus(VideoModel.FOCUSMODE_VR);
                            info.setBid(VideoModel.this.findBid(VideoModel.FOCUSMODE_VR));
                            info.setPty(VideoModel.this.findPty(VideoModel.FOCUSMODE_VR));
                            VideoModel.this.enterStack(info);
                            if (VideoModel.this.isCarPlayInStack()) {
                                VideoModel.this.finishCarPlayActivity();
                            }
                            return;
                        }
                        return;
                    }
                    if (VideoModel.ACTION_VR_END.equals(action) && VideoModel.VR_USER_NAME_BYD.equals(user) && VideoModel.this.mIsNativeVrActive) {
                        VideoModel.this.mIsNativeVrActive = false;
                        if (isShowAftExit || !VideoModel.this.mIsCarPlayConnected) {
                            VideoModel.this.exitStack(VideoModel.FOCUSMODE_VR);
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.tabCLickReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.video.VideoModel.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    LogUtil.info(VideoModel.TAG, "homeCLickReceiver.onReceive() action:" + action + " mIsCarPlayConnected:" + VideoModel.this.mIsCarPlayConnected);
                    if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                        String reason = intent.getStringExtra(VideoModel.SYSTEM_DIALOG_REASON_KEY);
                        if (reason != null) {
                            LogUtil.info(VideoModel.TAG, "action:" + action + ",reason:" + reason);
                            if (reason.equals(VideoModel.SYSTEM_DIALOG_REASON_HOME_KEY)) {
                                VideoModel.this.mIsClickHome = true;
                            }
                        }
                    } else if (VideoModel.ACTION_TAB_CLICK.equals(action) && TextUtils.equals("carsettings", intent.getStringExtra(VideoModel.TAB_CLICK_KEY))) {
                        VideoModel.this.mIsClickSettings = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.mSystemDialogStateReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.video.VideoModel.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    String dialogName = intent.getStringExtra(SystemDialogConstants.SYSTEM_DIALOG_NAME_KEY);
                    boolean isStartActivity = intent.getBooleanExtra(VideoModel.PANEL_START_ACTIVITY_TAG, false);
                    if (dialogName == null || action == null) {
                        LogUtil.debug(VideoModel.TAG, "mSystemDialogStateReceiver.onReceive: parm is null");
                        return;
                    }
                    LogUtil.debug(VideoModel.TAG, "mSystemDialogStateReceiver.onReceive(), dialogName=" + dialogName + ", action=" + action + ", mIsOobDialogShown=" + VideoModel.this.mIsOobDialogShown + ", isStartActivity=" + isStartActivity);
                    if (dialogName.equals(SystemDialogConstants.SYSTEM_DIALOG_NAME_VALUE_OOB)) {
                        if (SystemDialogConstants.ACTION_SYSTEM_DIALOG_SHOW.equals(action) && !VideoModel.this.mIsOobDialogShown) {
                            VideoModel.this.mIsOobDialogShown = true;
                            FocusModeInfo info = VideoModel.this.new FocusModeInfo();
                            info.setFocus(VideoModel.FOCUSMODE_SYSTEM_DIALOG);
                            info.setBid(VideoModel.this.findBid(VideoModel.FOCUSMODE_SYSTEM_DIALOG));
                            info.setPty(VideoModel.this.findPty(VideoModel.FOCUSMODE_SYSTEM_DIALOG));
                            VideoModel.this.enterStack(info);
                            if (VideoModel.this.isCarPlayInStack()) {
                                VideoModel.this.finishCarPlayActivity();
                            }
                        } else if (SystemDialogConstants.ACTION_SYSTEM_DIALOG_DISMISS.equals(action) && VideoModel.this.mIsOobDialogShown) {
                            VideoModel.this.mIsOobDialogShown = false;
                            if (!isStartActivity || !VideoModel.this.mIsCarPlayConnected) {
                                VideoModel.this.exitStack(VideoModel.FOCUSMODE_SYSTEM_DIALOG);
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.mEcallWidgetStateReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.video.VideoModel.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    String action = intent.getAction();
                    int state = intent.getIntExtra(VideoModel.ECALL_WIDGET_EXTRA_STATE, 0);
                    int layout = intent.getIntExtra(VideoModel.ECALL_WIDGET_EXTRA_LAYOUT, 0);
                    if (action == null) {
                        LogUtil.debug(VideoModel.TAG, "mEcallWidgetStateReceiver.onReceive: action is null");
                        return;
                    }
                    LogUtil.info(VideoModel.TAG, "mEcallWidgetStateReceiver.onReceive(), action=" + action + ", state=" + state + ", layout=" + layout);
                    if (VideoModel.ACTION_ECALL_STATE_CHANGE.equals(action)) {
                        if (1 != state || 1 != layout) {
                            VideoModel.this.mIsEcallActive = false;
                        } else {
                            VideoModel.this.mIsEcallActive = true;
                        }
                        VideoModel.this.sendEcallState();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        this.mTaskStackListener = new TaskStackListener() { // from class: com.ts.carplay.app.service.model.video.VideoModel.9
            public void onTaskStackChanged() throws RemoteException {
                LogUtil.debug(VideoModel.TAG, "TaskStackListener.onTaskStackChanged()");
                VideoModel.this.foregroundViewChanged();
            }
        };
        this.mAvmConnection = new IAvmServiceConnectionListener() { // from class: com.ts.carplay.app.service.model.video.VideoModel.10
            @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
            public void onServiceConnected() {
                LogUtil.info(VideoModel.TAG, "mAvmConnection.onServiceConnected()");
                int ret = AvmManager.getInstance().registerAvmStatusListener(VideoModel.this.mAvmStatusListener);
                int avmWorkState = AvmManager.getInstance().getAvmWorkState();
                LogUtil.info(VideoModel.TAG, "mAvmConnection ret=" + ret + ", avmWorkState=" + avmWorkState);
                synchronized (VideoModel.this.avmLock) {
                    if (!VideoModel.this.judgeGear(VideoModel.this.mCurrentGearPosition) || 2 != avmWorkState) {
                        VideoModel.this.mIsAvm = false;
                    } else {
                        VideoModel.this.enterAvm();
                    }
                }
            }

            @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
            public void onServiceDisConnected() {
                LogUtil.info(VideoModel.TAG, "mAvmConnection.onServiceDisConnected()");
                AvmManager.getInstance().unregisterAvmStatusListener(VideoModel.this.mAvmStatusListener);
            }

            @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
            public void onError(int i, String s) {
                LogUtil.info(VideoModel.TAG, "mAvmConnection.onError()");
            }
        };
        this.mAvmStatusListener = new AvmManager.AvmStatusListener() { // from class: com.ts.carplay.app.service.model.video.VideoModel.11
            @Override // com.ts.avm.sdk.AvmManager.AvmStatusListener
            public void onStatusChanged(int code, String message) {
                LogUtil.info(VideoModel.TAG, "mAvmStatusListener.onStatusChanged() code=" + code + ", message=" + message + " // code != 2 means background");
                synchronized (VideoModel.this.avmLock) {
                    if (2 != code) {
                        ComponentName topActivity = VideoModel.this.getTopActivity();
                        if (topActivity == null) {
                            VideoModel.this.exitStack(VideoModel.FOCUSMODE_BACK_CAMERA_FRAME);
                        } else {
                            String runningPackageName = topActivity.getPackageName();
                            if (VideoModel.this.findPty(runningPackageName) != 0 || VideoModel.this.mFocusModeList.isEmpty() || VideoModel.this.isEquals(((FocusModeInfo) VideoModel.this.mFocusModeList.peek()).getFocus(), runningPackageName) || VideoModel.this.mIsNativeVrActive) {
                                VideoModel.this.exitStack(VideoModel.FOCUSMODE_BACK_CAMERA_FRAME);
                            } else {
                                LogUtil.info(VideoModel.TAG, "Check the foregroundView");
                                VideoModel.this.foregroundViewChanged();
                            }
                        }
                    } else {
                        FocusModeInfo info = VideoModel.this.new FocusModeInfo();
                        info.setFocus(VideoModel.FOCUSMODE_BACK_CAMERA_FRAME);
                        info.setBid(VideoModel.this.findBid(VideoModel.FOCUSMODE_BACK_CAMERA_FRAME));
                        info.setPty(VideoModel.this.findPty(VideoModel.FOCUSMODE_BACK_CAMERA_FRAME));
                        VideoModel.this.enterStack(info);
                    }
                }
            }
        };
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        CarPlayRemoteManager carPlayRemoteManager = CarPlayRemoteManager.getInstance();
        this.mCarPlayRemoteManager = carPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.addCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
        }
        initHigherFocusInfo();
        this.mProvisioningObserver = new ProvisioningObserver();
        this.mAm = (ActivityManager) context.getSystemService("activity");
        IActivityManager service = ActivityManager.getService();
        this.mIam = service;
        if (service != null) {
            try {
                service.registerTaskStackListener(this.mTaskStackListener);
            } catch (Exception exception) {
                LogUtil.warning(TAG, "Failed to call registerTaskStackListener", exception);
            }
        }
        this.mBtPhoneCallStateListener = new BtPhoneCallStateListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        if (telephonyManager != null) {
            telephonyManager.listen(this.mBtPhoneCallStateListener, 32);
            int callState = this.mTelephonyManager.getCallState();
            LogUtil.info(TAG, "callState :" + callState);
            notifyBtCallState(callState);
        }
        this.packageManager = context.getPackageManager();
        registerVrStartAndEndReceiver(this.mContext);
        registerSettingDialogReceiver(this.mContext);
        registerOobDialogStateReceiver(this.mContext);
        registerTabClickReceiver(this.mContext);
        registerEcallStateReceiver(this.mContext);
        initCar();
        this.contentResolver = context.getContentResolver();
        this.settingsObserver = new SettingsObserver(null);
        this.contentResolver.registerContentObserver(Settings.Global.getUriFor(NAVIBAR_MODE_ON_SCREENSAVER), false, this.settingsObserver);
        initAvm();
        Intent intent = new Intent(ACTION_REQUEST_ECALL_STATE_UPDATE);
        this.mContext.sendBroadcast(intent);
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
        this.mContext.unregisterReceiver(this.vrReceiver);
        this.mContext.unregisterReceiver(this.mSettingDialogReceiver);
        this.mContext.unregisterReceiver(this.tabCLickReceiver);
        this.mContext.unregisterReceiver(this.mEcallWidgetStateReceiver);
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver != null) {
            SettingsObserver settingsObserver = this.settingsObserver;
            if (settingsObserver != null) {
                contentResolver.unregisterContentObserver(settingsObserver);
                this.settingsObserver = null;
            }
            this.contentResolver = null;
        }
        if (this.packageManager != null) {
            this.packageManager = null;
        }
        Car car = this.mCarClient;
        if (car != null) {
            car.disconnect();
            this.mCarClient = null;
        }
        uninitAvm();
    }

    private void initAvm() {
        LogUtil.info(TAG, "initAvm()");
        AvmManager.getInstance().init(this.mContext, this.mAvmConnection);
    }

    private void uninitAvm() {
        LogUtil.info(TAG, "uninitAvm()");
        AvmManager.getInstance().deinit(this.mAvmConnection);
    }

    private void registerVrStartAndEndReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction(ACTION_VR_START);
        filter.addAction(ACTION_VR_END);
        context.registerReceiver(this.vrReceiver, filter);
    }

    private void registerPanelReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction(FOCUSMODE_PANEL);
        context.registerReceiver(this.mPanelReceiver, filter);
    }

    private void registerSettingDialogReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction(FOCUSMODE_WIFI_DIALOG);
        filter.addAction(FOCUSMODE_HOTSPOT_DIALOG);
        context.registerReceiver(this.mSettingDialogReceiver, filter);
    }

    private void registerTabClickReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        filter.addAction(ACTION_TAB_CLICK);
        context.registerReceiver(this.tabCLickReceiver, filter);
    }

    private void registerOobDialogStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction(SystemDialogConstants.ACTION_SYSTEM_DIALOG_SHOW);
        filter.addAction(SystemDialogConstants.ACTION_SYSTEM_DIALOG_DISMISS);
        context.registerReceiver(this.mSystemDialogStateReceiver, filter);
    }

    private void registerEcallStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ECALL_STATE_CHANGE);
        context.registerReceiver(this.mEcallWidgetStateReceiver, filter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        private CarPlayRemoteManagerListener() {
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlaySessionStateChanged(final int status) {
            LogUtil.debug(VideoModel.TAG, "onCarPlaySessionStateChanged() status=" + status);
            VideoModel.this.post(new Runnable() { // from class: com.ts.carplay.app.service.model.video.-$$Lambda$VideoModel$CarPlayRemoteManagerListener$HWW3GFLlPH6v4So-Y0hgkEo6kcM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCarPlaySessionStateChanged$0$VideoModel$CarPlayRemoteManagerListener(status);
                }
            });
        }

        public /* synthetic */ void lambda$onCarPlaySessionStateChanged$0$VideoModel$CarPlayRemoteManagerListener(int status) {
            if (status == 2) {
                VideoModel.this.mIsCarPlayConnected = true;
                return;
            }
            if (status == 4) {
                VideoModel.this.mIsCarPlayConnected = false;
                if (VideoModel.this.mIsBtDialogShowing) {
                    VideoModel.this.exitStack(VideoModel.FOCUSMODE_BT_DIALOG);
                }
                if (VideoModel.this.mIsDevConDialogShowing) {
                    VideoModel.this.exitStack(VideoModel.SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION);
                }
                if (VideoModel.this.mIsHotspotDialogShowing) {
                    VideoModel.this.exitStack(VideoModel.FOCUSMODE_HOTSPOT_DIALOG);
                }
                VideoModel.this.mLastFocusModeInfo = null;
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onServiceStateChanged(boolean connected) {
            LogUtil.debug(VideoModel.TAG, "onServiceStateChanged() connected=" + connected);
            if (connected) {
                VideoModel.this.mIsServiceConnection = true;
                if (VideoModel.this.mFocusModeList.isEmpty()) {
                    VideoModel.this.foregroundViewChanged();
                    return;
                } else {
                    VideoModel videoModel = VideoModel.this;
                    videoModel.enterStack((FocusModeInfo) videoModel.mFocusModeList.peek());
                    return;
                }
            }
            VideoModel.this.mIsServiceConnection = false;
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onAppStatesChanged(int appState, int entity, int speechMode) {
            if (appState == 0) {
                if (entity == 1) {
                    VideoModel.this.mIsCpCallActive = true;
                    VideoModel.this.closeSaver();
                } else {
                    VideoModel.this.mIsCpCallActive = false;
                }
                LogUtil.info(VideoModel.TAG, "mIsCpCallActive " + VideoModel.this.mIsCpCallActive);
                return;
            }
            if (appState == 2 && entity == 1) {
                VideoModel.this.closeSaver();
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlayDisplayStatusChanged(boolean isShow) {
            LogUtil.warning(VideoModel.TAG, "onCarPlayDisplayStatusChanged isShow:" + isShow);
            if (isShow) {
                StringBuilder sb = new StringBuilder();
                sb.append("onCarPlayDisplayStatusChanged mCarPlayRemoteManager ");
                sb.append(VideoModel.this.mCarPlayRemoteManager == null);
                LogUtil.debug(VideoModel.TAG, sb.toString());
                if (VideoModel.this.mCarPlayRemoteManager != null) {
                    LogUtil.debug(VideoModel.TAG, "onCarPlayDisplayStatusChanged force set screen to landscape");
                    VideoModel.this.mCarPlayRemoteManager.setScreenRotationS(1);
                }
            }
        }
    }

    private void initHigherFocusInfo() {
        List<FocusModeInfo> list = this.mHigherFocusInfoList;
        if (list != null) {
            list.add(new FocusModeInfo(FOCUSMODE_MODE_STANDBY, 7, FOCUSMODE_MODE_STANDBY));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_CARPLAY, 1, ""));
            this.mHigherFocusInfoList.add(new FocusModeInfo(BT_PHONE_ACTIVITY, 7, "phoneCall"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_ECALL, 7, "phoneCall"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_BACK_CAMERA, 7, "backupCamera"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_BACK_CAMERA_FRAME, 6, "uiNotification"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_VR, 6, "nativeVR"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_DISPLAYOFF, 6, "displayOff"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_CARD, 6, "uiNotification"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_WIFI_DIALOG, 6, "uiNotification"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_HOTSPOT_DIALOG, 6, "uiNotification"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_SYSTEM_DIALOG, 6, "uiNotification"));
            this.mHigherFocusInfoList.add(new FocusModeInfo(FOCUSMODE_PANEL, 6, "uiNotification"));
        }
    }

    private void initCar() {
        Car carCreateCar = Car.createCar(this.mContext, new CarConnectionCallback() { // from class: com.ts.carplay.app.service.model.video.VideoModel.2
            @Override // com.ts.lib.car.CarConnectionCallback
            public void onConnected(Car car) {
                try {
                    if (car != null) {
                        VideoModel.this.carManager = (CarVendorOtaManager) car.getCarManager(Car.VENDOR_OTA_SERVICE);
                        VideoModel.this.mCurrentGearPosition = VideoModel.this.carManager.getGear();
                        VideoModel.this.carManager.addCarOtaEventListener(new CarVendorOtaManager.OnCarOtaEventListener() { // from class: com.ts.carplay.app.service.model.video.VideoModel.2.1
                            @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager.OnCarOtaEventListener
                            public void onOtaEventChange(OtaEventChangeValue value) {
                                int gearPosition = value.getGear();
                                int currentGear = VideoModel.this.convertVehicleGearMode(gearPosition);
                                LogUtil.info(VideoModel.TAG, "onOtaEventChange() gear:" + gearPosition + " currentGear:" + currentGear + " mCurrentGearPosition:" + VideoModel.this.mCurrentGearPosition);
                                VideoModel.this.mCurrentGearPosition = currentGear;
                                if (VideoModel.this.judgeGear(currentGear)) {
                                    VideoModel.this.enterAvm();
                                } else {
                                    VideoModel.this.mIsAvm = false;
                                    if (!VideoModel.this.mIsEcallActive) {
                                        if (!VideoModel.this.mIsNativeCallActive) {
                                            VideoModel.this.exitStack(VideoModel.FOCUSMODE_BACK_CAMERA);
                                        } else {
                                            FocusModeInfo info = VideoModel.this.new FocusModeInfo();
                                            info.setFocus(VideoModel.BT_PHONE_ACTIVITY);
                                            info.setBid(VideoModel.this.findBid(VideoModel.BT_PHONE_ACTIVITY));
                                            info.setPty(VideoModel.this.findPty(VideoModel.BT_PHONE_ACTIVITY));
                                            VideoModel.this.enterStack(info);
                                        }
                                    } else {
                                        FocusModeInfo info2 = VideoModel.this.new FocusModeInfo();
                                        info2.setFocus(VideoModel.FOCUSMODE_ECALL);
                                        info2.setBid(VideoModel.this.findBid(VideoModel.FOCUSMODE_ECALL));
                                        info2.setPty(VideoModel.this.findPty(VideoModel.FOCUSMODE_ECALL));
                                        VideoModel.this.enterStack(info2);
                                    }
                                }
                                Intent intent = new Intent(ModelConstant.ACTION_AVM_STATUS);
                                intent.putExtra(ModelConstant.KEY_AVM_STATUS, VideoModel.this.mIsAvm);
                                VideoModel.this.mContext.sendBroadcast(intent);
                            }

                            @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager.OnCarOtaEventListener
                            public void onOtaErrorEvent(int propertyId, int zone) {
                            }
                        });
                    } else {
                        LogUtil.info(VideoModel.TAG, "initCar car is null!");
                    }
                } catch (CarNotConnectedException exception) {
                    exception.printStackTrace();
                }
            }

            @Override // com.ts.lib.car.CarConnectionCallback
            public void onDisconnected(Car car) {
                if (VideoModel.this.mCarClient != null) {
                    VideoModel.this.mCarClient = null;
                }
            }
        });
        this.mCarClient = carCreateCar;
        if (carCreateCar != null) {
            carCreateCar.connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean judgeGear(int currentGear) {
        if (currentGear == 3) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int convertVehicleGearMode(int vehicleGearMode) {
        if (vehicleGearMode == 0) {
            LogUtil.warning(TAG, "convertVehicleGearMode vehicleGearMode is invalid. vehicleGearMode=" + vehicleGearMode);
            return -1;
        }
        if (vehicleGearMode == 1) {
            return 2;
        }
        if (vehicleGearMode == 2) {
            return 3;
        }
        if (vehicleGearMode == 3) {
            return 0;
        }
        if (vehicleGearMode == 4) {
            return 1;
        }
        LogUtil.warning(TAG, "convertVehicleGearMode vehicleGearMode is unknown. vehicleGearMode=" + vehicleGearMode);
        return -1;
    }

    private class ShutdownBroadcastReceiver extends BroadcastReceiver {
        private ShutdownBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.debug(VideoModel.TAG, "ShutdownBroadcastReceiver.onReceive() action=" + action);
            if (action != null && action.equals(VideoModel.ACTION_SHUTDOWN)) {
                LogUtil.debug(VideoModel.TAG, "ACTION_SHUTDOWN start");
                if (VideoModel.this.mCarPlayRemoteManager != null && VideoModel.this.mIsCarPlayConnected) {
                    VideoModel.this.mCarPlayRemoteManager.setLastUserMode(1, VideoModel.this.isCarPlayInStack());
                }
                LogUtil.debug(VideoModel.TAG, "ACTION_SHUTDOWN end");
            }
        }
    }

    private class ProvisioningObserver extends ContentObserver {
        ProvisioningObserver() {
            super(new Handler());
            VideoModel.this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("device_provisioned"), false, this);
            onChange(false);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            VideoModel.this.mIsDeviceProvisioned = isDeviceProvisioned();
            LogUtil.debug(VideoModel.TAG, "ProvisioningObserver.onChange() selfChange:" + selfChange + " mIsDeviceProvisioned:" + VideoModel.this.mIsDeviceProvisioned);
            if (VideoModel.this.mCarPlayRemoteManager != null) {
                VideoModel.this.mCarPlayRemoteManager.setDeviceProvisioned(VideoModel.this.mIsDeviceProvisioned);
                if (VideoModel.this.mIsDeviceProvisioned) {
                    VideoModel.this.foregroundViewChanged();
                }
            }
        }

        private boolean isDeviceProvisioned() {
            return Settings.Global.getInt(VideoModel.this.mContext.getContentResolver(), "device_provisioned", 0) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ComponentName getTopActivity() {
        ComponentName topActivity = (ComponentName) Optional.ofNullable(this.mAm).map(new Function() { // from class: com.ts.carplay.app.service.model.video.-$$Lambda$VideoModel$F6aj30lLniXuDqkCBiOydir3c3A
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ActivityManager) obj).getRunningTasks(10);
            }
        }).map(new Function() { // from class: com.ts.carplay.app.service.model.video.-$$Lambda$VideoModel$P6ALSdWrb2ffACiy5D7hzwJegmA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return VideoModel.lambda$getTopActivity$1((List) obj);
            }
        }).map(new Function() { // from class: com.ts.carplay.app.service.model.video.-$$Lambda$VideoModel$dNOJ4x02iHK0DXS9VIXU54eiv68
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ActivityManager.RunningTaskInfo) obj).topActivity;
            }
        }).orElse(null);
        return topActivity;
    }

    static /* synthetic */ ActivityManager.RunningTaskInfo lambda$getTopActivity$1(List tasks) {
        if (tasks.isEmpty()) {
            return null;
        }
        return (ActivityManager.RunningTaskInfo) tasks.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void foregroundViewChanged() {
        FocusModeInfo focusModeInfo;
        ComponentName topActivity = getTopActivity();
        if (topActivity == null) {
            return;
        }
        String runningPackageName = topActivity.getPackageName();
        String topActivityName = topActivity.getClassName();
        LogUtil.debug(TAG, "foregroundViewChanged() uniqueName=" + runningPackageName + ", topPackageName=" + runningPackageName + ", topActivityName=" + topActivityName + ", mIsNativeVrActive=" + this.mIsNativeVrActive + ", mIsOobDialogShown=" + this.mIsOobDialogShown);
        if (isEquals(runningPackageName, FOCUSMODE_SYSTEM_UI)) {
            if (isEquals(SYSTEMUI_RECENT, topActivityName)) {
                LogUtil.debug(TAG, "recentui enterStack to take");
            } else {
                LogUtil.debug(TAG, "systemui do not enterStack");
                return;
            }
        }
        if (isEquals(runningPackageName, FOCUSMODE_LAUNCHER)) {
            LogUtil.debug(TAG, "go to launcher,reset click flag");
            this.mIsClickHome = false;
        }
        if (isEquals(runningPackageName, "com.byd.carsettings")) {
            LogUtil.debug(TAG, "go to car settings,reset click flag");
            this.mIsClickSettings = false;
        }
        if (isEquals(topActivityName, BT_PHONE_ACTIVITY) || this.mIsNativeCallActive || this.mIsEcallActive) {
            LogUtil.debug(TAG, "Enter into " + topActivityName + "no need to deal.");
            return;
        }
        if (runningPackageName != null && (focusModeInfo = this.mLastFocusModeInfo) != null && runningPackageName.equals(focusModeInfo.getFocus())) {
            if (this.mIsNativeVrActive || this.mIsOobDialogShown) {
                LogUtil.debug(TAG, "foregroundViewChanged ignore this TaskChanged.");
                return;
            }
            LogUtil.debug(TAG, "foregroundViewChanged insert CarPlay");
            FocusModeInfo info = new FocusModeInfo();
            info.setFocus(FOCUSMODE_CARPLAY);
            info.setBid(findBid(FOCUSMODE_CARPLAY));
            info.setPty(findPty(FOCUSMODE_CARPLAY));
            enterStack(info);
            return;
        }
        if (isCarPlayInStack()) {
            this.mIsFirstFocusChange = true;
        } else {
            this.mIsFirstFocusChange = false;
        }
        if (isEquals(runningPackageName, PACKAGE_NAME_AVM)) {
            LogUtil.debug(TAG, "top package is AVM_FRAME.");
            FocusModeInfo info2 = new FocusModeInfo();
            info2.setFocus(FOCUSMODE_BACK_CAMERA_FRAME);
            info2.setBid(findBid(FOCUSMODE_BACK_CAMERA_FRAME));
            info2.setPty(findPty(FOCUSMODE_BACK_CAMERA_FRAME));
            enterStack(info2);
            return;
        }
        FocusModeInfo info3 = new FocusModeInfo();
        info3.setFocus(runningPackageName);
        info3.setBid(findBid(runningPackageName));
        info3.setPty(findPty(runningPackageName));
        if (info3.getPty() == 0 && isCarPlayInStack()) {
            finishCarPlayActivity();
        }
        enterStack(info3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isCarPlayInStack() {
        for (FocusModeInfo info : this.mFocusModeList) {
            if (info.getPty() == 1) {
                LogUtil.info(TAG, " isCarPlayInStack true");
                return true;
            }
        }
        LogUtil.info(TAG, " isCarPlayInStack false");
        return false;
    }

    private synchronized boolean isSceneInStack(String focus) {
        boolean res = false;
        if (focus == null) {
            return false;
        }
        if (this.mFocusModeList != null) {
            Iterator<FocusModeInfo> it = this.mFocusModeList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                FocusModeInfo info = it.next();
                if (info != null && focus.equals(info.getFocus())) {
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String findBid(String focus) {
        LogUtil.info(TAG, "findBid keyFocus:" + focus);
        if (this.mHigherFocusInfoList == null) {
            return "";
        }
        for (int i = 0; i < this.mHigherFocusInfoList.size(); i++) {
            if (this.mHigherFocusInfoList.get(i) != null && isEquals(this.mHigherFocusInfoList.get(i).getFocus(), focus)) {
                String res = this.mHigherFocusInfoList.get(i).getBid();
                LogUtil.info(TAG, "findBidRet=" + res);
                return res;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int findPty(String focus) {
        LogUtil.info(TAG, "findPty keyFocus:" + focus);
        if (this.mHigherFocusInfoList == null) {
            return 0;
        }
        for (int i = 0; i < this.mHigherFocusInfoList.size(); i++) {
            if (this.mHigherFocusInfoList.get(i) != null && isEquals(this.mHigherFocusInfoList.get(i).getFocus(), focus)) {
                int res = this.mHigherFocusInfoList.get(i).getPty();
                LogUtil.info(TAG, "findPtyRet=" + res);
                return res;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEquals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        if (str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void enterStack(FocusModeInfo inF) {
        if (inF == null) {
            LogUtil.error(TAG, "enterStack: inf is null.");
            return;
        }
        LogUtil.info(TAG, inF.getFocus() + " enterStack start,list size:" + this.mFocusModeList.size() + " mIsFirstFocusChange:" + this.mIsFirstFocusChange);
        if (inF != null && ((inF.getPty() == 1 || inF.getPty() == 0) && this.mLastFocusModeInfo != null)) {
            LogUtil.info(TAG, "clear mLastFocusModeInfo while enter normal or carplay.");
            this.mLastFocusModeInfo = null;
        }
        if (this.mFocusModeList.isEmpty()) {
            LogUtil.debug(TAG, "PUSH: " + inF.getFocus());
            this.mFocusModeList.push(inF);
            priorityChanged(this.mFocusModeList.peek(), 1);
            LogUtil.info(TAG, inF.getFocus() + " enterStack end.");
            return;
        }
        if (inF.getPty() > this.mFocusModeList.peek().getPty()) {
            LogUtil.debug(TAG, "PUSH2: " + inF.getFocus());
            this.mFocusModeList.push(inF);
            priorityChanged(this.mFocusModeList.peek(), 1);
        } else {
            if (inF.getPty() == 0 && !this.mIsFirstFocusChange) {
                checkLastUserMode(inF);
            }
            if (isEquals(inF.getFocus(), this.mFocusModeList.peek().getFocus())) {
                priorityChanged(this.mFocusModeList.peek(), 1);
                LogUtil.info(TAG, inF.getFocus() + " enterStack end.");
                return;
            }
            if (this.mFocusModeList.peek().getPty() == 7 && inF.getPty() == 6) {
                FocusModeInfo popInf = this.mFocusModeList.pop();
                this.mFocusModeList.push(inF);
                this.mFocusModeList.push(popInf);
            } else {
                priorityChanged(this.mFocusModeList.peek(), 0);
                FocusModeInfo popInf2 = this.mFocusModeList.pop();
                LogUtil.info(TAG, "POP: " + popInf2.getFocus() + " mPriority:" + popInf2.mPriority + " enterStack loopback,list size:" + this.mFocusModeList.size());
                enterStack(inF);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void exitStack(String focus) {
        LogUtil.debug(TAG, "exitStack() focus=" + focus);
        if (this.mFocusModeList != null && !this.mFocusModeList.isEmpty()) {
            if (isEquals(this.mFocusModeList.peek().getFocus(), focus)) {
                LogUtil.debug(TAG, "exitStack1");
                priorityChanged(this.mFocusModeList.peek(), 0);
                FocusModeInfo popInf = this.mFocusModeList.pop();
                LogUtil.debug(TAG, "POP: " + popInf.getFocus());
                if (!this.mFocusModeList.isEmpty()) {
                    priorityChanged(this.mFocusModeList.peek(), 1);
                } else {
                    LogUtil.warning(TAG, "after mFocusModeList.pop(), mFocusModeList.isEmpty()");
                }
            } else {
                LogUtil.debug(TAG, "exitStack2");
                ListIterator<FocusModeInfo> it = this.mFocusModeList.listIterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FocusModeInfo info = it.next();
                    LogUtil.debug(TAG, "info.getFocus():" + info.getFocus());
                    if (isEquals(info.getFocus(), focus)) {
                        priorityChanged(info, 0);
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    private synchronized void checkLastUserMode(FocusModeInfo inF) {
        LogUtil.warning(TAG, "checkLastUserMode() inF.focus=" + inF.getFocus() + ", priority=" + inF.getPty());
        LogUtil.warning(TAG, "mFocusModeList.peek() peek.focus=" + this.mFocusModeList.peek().getFocus() + ", priority=" + this.mFocusModeList.peek().getPty());
        if (this.mFocusModeList.peek().getPty() == 0 && !isEquals(this.mFocusModeList.peek().getFocus(), inF.getFocus()) && this.mCarPlayRemoteManager != null) {
            LogUtil.warning(TAG, "setLastUserMode ");
            this.mCarPlayRemoteManager.setLastUserMode(1, false);
        }
    }

    private void priorityChanged(final FocusModeInfo inF, final int action) {
        post(new Runnable() { // from class: com.ts.carplay.app.service.model.video.-$$Lambda$VideoModel$Frs7KabUNbiSW7CAJkwngC98-XU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$priorityChanged$3$VideoModel(inF, action);
            }
        });
    }

    public /* synthetic */ void lambda$priorityChanged$3$VideoModel(FocusModeInfo inF, int action) {
        if (this.mCarPlayRemoteManager != null && inF != null && this.mIsServiceConnection) {
            if (inF.getPty() != 0 || action != 0) {
                LogUtil.info(TAG, "priorityChanged() inf:" + inF.toString() + ", action:" + action);
                this.mCarPlayRemoteManager.changeVideoFocus(inF.getPty(), true, inF.getBid(), action);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class FocusModeInfo {
        private String mBorrowId;
        private String mFocus;
        private int mPriority;

        public FocusModeInfo() {
            this.mFocus = "";
            this.mBorrowId = "";
            this.mPriority = -1;
        }

        public FocusModeInfo(String focus, int priority, String borrowId) {
            this.mFocus = "";
            this.mBorrowId = "";
            this.mPriority = -1;
            this.mFocus = focus;
            this.mPriority = priority;
            this.mBorrowId = borrowId;
        }

        public void setFocus(String focus) {
            this.mFocus = focus;
        }

        public String getFocus() {
            return this.mFocus;
        }

        public void setBid(String id) {
            this.mBorrowId = id;
        }

        public String getBid() {
            return this.mBorrowId;
        }

        public void setPty(int priority) {
            this.mPriority = priority;
        }

        public int getPty() {
            return this.mPriority;
        }

        public String toString() {
            return "FocusModeInfo{mFocus='" + this.mFocus + "', mBorrowId='" + this.mBorrowId + "', mPriority=" + this.mPriority + '}';
        }
    }

    private synchronized FocusModeInfo getFocusMode(int priorityId) {
        FocusModeInfo ret;
        ret = null;
        if (this.mFocusModeList != null) {
            Iterator<FocusModeInfo> it = this.mFocusModeList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                FocusModeInfo info = it.next();
                if (info != null && info.getFocus() != null && priorityId == info.getPty()) {
                    ret = info;
                    break;
                }
            }
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishCarPlayActivity() {
        FocusModeInfo focus = getFocusMode(0);
        if (focus != null) {
            this.mLastFocusModeInfo = focus;
        }
        LogUtil.debug(TAG, "finishCarPlayActivity getFocusMode PRIORITY_LEVEL_NORMAL :" + focus);
        this.mContext.sendBroadcast(new Intent(BROADCAST_FINISH_CARPLAY_ACTIVITY));
    }

    private void sendCallState() {
        this.mIsNativeCallActive = this.mIsBtPhoneCallActive;
        LogUtil.info(TAG, "currentNativeCallStates mIsNativeCallActive: " + this.mIsNativeCallActive + ", mIsPreNativeCallActive: " + this.mIsPreNativeCallActive + ", mIsBtPhoneCallActive: " + this.mIsBtPhoneCallActive + ", mIsCPCallActive: " + this.mIsCpCallActive);
        if (this.mIsPreNativeCallActive != this.mIsNativeCallActive) {
            post(new Runnable() { // from class: com.ts.carplay.app.service.model.video.-$$Lambda$VideoModel$Jzujp6ANQoHkppfjxkHP7ENMRNU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sendCallState$4$VideoModel();
                }
            });
        }
        this.mIsPreNativeCallActive = this.mIsNativeCallActive;
    }

    public /* synthetic */ void lambda$sendCallState$4$VideoModel() {
        if (this.mIsNativeCallActive) {
            FocusModeInfo info = new FocusModeInfo();
            info.setFocus(BT_PHONE_ACTIVITY);
            info.setBid(findBid(BT_PHONE_ACTIVITY));
            info.setPty(findPty(BT_PHONE_ACTIVITY));
            enterStack(info);
            if (isCarPlayInStack()) {
                finishCarPlayActivity();
                return;
            }
            return;
        }
        if (this.mIsAvm) {
            FocusModeInfo info2 = new FocusModeInfo();
            info2.setFocus(FOCUSMODE_BACK_CAMERA);
            info2.setBid(findBid(FOCUSMODE_BACK_CAMERA));
            info2.setPty(findPty(FOCUSMODE_BACK_CAMERA));
            enterStack(info2);
            return;
        }
        exitStack(BT_PHONE_ACTIVITY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEcallState() {
        LogUtil.debug(TAG, "mIsEcallActive: " + this.mIsEcallActive + ", mIsPreEcallActive: " + this.mIsPreEcallActive);
        boolean z = this.mIsPreEcallActive;
        boolean z2 = this.mIsEcallActive;
        if (z != z2) {
            if (z2) {
                FocusModeInfo info = new FocusModeInfo();
                info.setFocus(FOCUSMODE_ECALL);
                info.setBid(findBid(FOCUSMODE_ECALL));
                info.setPty(findPty(FOCUSMODE_ECALL));
                enterStack(info);
                if (isCarPlayInStack()) {
                    finishCarPlayActivity();
                }
            } else if (this.mIsAvm) {
                FocusModeInfo info2 = new FocusModeInfo();
                info2.setFocus(FOCUSMODE_BACK_CAMERA);
                info2.setBid(findBid(FOCUSMODE_BACK_CAMERA));
                info2.setPty(findPty(FOCUSMODE_BACK_CAMERA));
                enterStack(info2);
            } else if (this.mIsNativeCallActive) {
                FocusModeInfo info3 = new FocusModeInfo();
                info3.setFocus(BT_PHONE_ACTIVITY);
                info3.setBid(findBid(BT_PHONE_ACTIVITY));
                info3.setPty(findPty(BT_PHONE_ACTIVITY));
                enterStack(info3);
            } else {
                exitStack(FOCUSMODE_ECALL);
            }
        }
        this.mIsPreEcallActive = this.mIsEcallActive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBtCallState(int state) {
        LogUtil.debug(TAG, "notifyBtCallState state: " + state + "mPreNativeCallState: " + this.mPreNativeCallState + " mIsCpCallActive: " + this.mIsCpCallActive);
        if (state == 0) {
            this.mIsBtPhoneCallActive = false;
            sendCallState();
        } else if (state == 1) {
            this.mIsBtPhoneCallActive = true;
            sendCallState();
        } else if (state == 2) {
            if (this.mPreNativeCallState == 0 && this.mIsCpCallActive) {
                LogUtil.debug(TAG, "cp call now,do not show native.");
                return;
            } else {
                this.mIsBtPhoneCallActive = true;
                sendCallState();
            }
        }
        this.mPreNativeCallState = state;
    }

    private class BtPhoneCallStateListener extends PhoneStateListener {
        public BtPhoneCallStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
            LogUtil.debug(VideoModel.TAG, "BtPhoneCallStateListener onServiceStateChanged: " + serviceState);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int state, String inComingNumber) {
            LogUtil.debug(VideoModel.TAG, "BtPhoneCallStateListener state: " + state + " inComingNumber: " + inComingNumber);
            VideoModel.this.notifyBtCallState(state);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            super.onCellInfoChanged(cellInfo);
        }
    }

    private class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            String option = uri.getLastPathSegment();
            LogUtil.info(VideoModel.TAG, "onChange selfChange : " + selfChange + " , option : " + option);
            if (option.equals(VideoModel.NAVIBAR_MODE_ON_SCREENSAVER)) {
                int screenOffType = Settings.Global.getInt(VideoModel.this.mContext.getContentResolver(), VideoModel.NAVIBAR_MODE_ON_SCREENSAVER, 0);
                VideoModel.this.mScreenOffType = screenOffType;
                LogUtil.info(VideoModel.TAG, "onChange screenOffType : " + screenOffType);
            }
        }
    }

    public void closeSaver() {
        Context context;
        if (this.mScreenOffType != 1) {
            LogUtil.info(TAG, "----on saver close");
            if (this.packageManager != null) {
                Intent in = new Intent("android.intent.action.VIEW", Uri.parse(CLOSE_SAVER_SCHEME));
                in.setComponent(new ComponentName("com.byd.carsettings", SERVICE_CLASS));
                List<ResolveInfo> resolveInfos = this.packageManager.queryIntentServices(in, 0);
                LogUtil.info(TAG, "----startIntentByPath----resolveInfos = " + resolveInfos);
                if (!resolveInfos.isEmpty() && (context = this.mContext) != null) {
                    context.startService(in);
                    return;
                }
                return;
            }
            LogUtil.info(TAG, "----packageManager = null");
            return;
        }
        LogUtil.info(TAG, "----not on saver,do nothing");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterAvm() {
        if (!this.mIsAvm) {
            this.mIsAvm = true;
            FocusModeInfo info = new FocusModeInfo();
            info.setFocus(FOCUSMODE_BACK_CAMERA);
            info.setBid(findBid(FOCUSMODE_BACK_CAMERA));
            info.setPty(findPty(FOCUSMODE_BACK_CAMERA));
            enterStack(info);
            return;
        }
        LogUtil.info(TAG, "avm is on foreground,not enter again");
    }

    public class SystemDialogConstants {
        public static final String ACTION_SYSTEM_DIALOG_DISMISS = "com.byd.system.dialog.ACTION_DISMISS";
        public static final String ACTION_SYSTEM_DIALOG_SHOW = "com.byd.system.dialog.ACTION_SHOW";
        public static final String SYSTEM_DIALOG_NAME_KEY = "dialog_name";
        public static final String SYSTEM_DIALOG_NAME_VALUE_OOB = "CarPlayWirelessDialog";

        public SystemDialogConstants() {
        }
    }
}
