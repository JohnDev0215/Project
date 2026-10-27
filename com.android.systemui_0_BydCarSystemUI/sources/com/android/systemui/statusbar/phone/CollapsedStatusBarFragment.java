package com.android.systemui.statusbar.phone;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.StatusListDialog;
import com.android.systemui.statusbar.StatusListManager;
import com.android.systemui.statusbar.datas.Icons;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.ClockController;
import com.android.systemui.statusbar.policy.ClockControllerImpl;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.statusbar.policy.TBoxSignalController;
import com.android.systemui.statusbar.policy.TelephonyIcons;
import com.android.systemui.statusbar.policy.UsbController;
import com.android.systemui.statusbar.policy.VolumeController;
import com.android.systemui.statusbar.wm.Util;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.GuideUtils;
import com.android.systemui.util.ScreenShotUtil;
import com.android.systemui.util.SharedPreferencesUtil;
import com.android.systemui.util.StringUtil;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.volume.VolumeComponent;
import com.android.systemui.widget.CommonDialog;
import com.android.systemui.widget.MediaPlayingImg;
import com.android.systemui.widget.VoiceImg;
import com.byd.card.floatcard.TopActivityManager;
import com.byd.card.utils.AppUtils;
import com.byd.profile.DialogChangeEvent;
import com.byd.profile.StatusBarChangeEvent;
import com.byd.profile.ToastManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class CollapsedStatusBarFragment extends Fragment implements CommandQueue.Callbacks, View.OnClickListener, StatusBarIconController.TopActivityChangeListener {
    private static final String ACTION_REQUEST_FOR_MEDIA_INFO = "com.byd.ACTION_REQUEST_FOR_MEDIA_INFO";
    private static final int EXECUTE_STATUS_BAR_TASK = 1;
    private static final String EXTRA_PANEL_STATE = "panel_state";
    public static final int MAX_TIME = 3;
    private static final int SLW_OFF = 1;
    private static final int SLW_ON = 2;
    public static final String TAG = "CollapsedStatusBarFragment";
    private static final int UPDATE_PM_LAYOUT = 4;
    private static final int UPDATE_STATUS_BAR_COLOR = 3;
    private static final int UPDATE_STATUS_ICONS = 5;
    private boolean changeDayNightMode;
    private boolean ignoreChange;
    private ImageView img_android_auto_btn;
    private ImageView img_bt_btn;
    private ImageView img_carplay_btn;
    private ImageView img_signal_btn;
    private ImageView img_wifi_btn;
    private boolean isAmazonMusicShow;
    private RelativeLayout ll_android_auto;
    private RelativeLayout ll_carplay;
    private FrameLayout ll_swl;
    private LinearLayout ll_voice;
    private AnimationDrawable mAnimationDrawable;
    private boolean mBTIsConnected;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoStudy mBYDAutoStudy;
    private BluetoothController mBluetoothController;
    private BYDAutoPowerDevice mBydAutoPowerDevice;
    private boolean mCameraShown;
    private ImageView mCameraView;
    private CarController mCarController;
    private int mCarModeValue;
    private ClockController mClockController;
    private Context mContext;
    private int mDvrState;
    private ImageView mDvrView;
    private boolean mIsRtl;
    private boolean mIsScreenBright;
    private ImageView mIvTsr;
    private ImageView mIvTsrBtn;
    private String mLastBtAddress;
    private LinearLayout mMediaLayout;
    private TextView mMediaPlayCategoryTv;
    private ImageView mMediaPlayDefImg;
    private MediaPlayingImg mMediaPlayStatusImg;
    private boolean mMicShown;
    private ImageView mMicView;
    private ImageView mMoreView;
    private boolean mMuteShow;
    private ImageView mMuteView;
    private NetworkController mNetworkController;
    private ImageView mOTAView;
    private boolean mOtaFlag;
    private int mPM2p5DualChannelDetectState;
    private int mPM2p5InState;
    private int mPM2p5OutState;
    private LinearLayout mPmLayout;
    private boolean mReverseShowIcon;
    private boolean mSDMounted;
    public CommonDialog mSLWDialog;
    public ScheduledExecutorService mScheduledExecutorService;
    private ImageView mSdcardView;
    private ImageView mShowModeView;
    private PhoneStatusBarView mStatusBar;
    private StatusBarIconController mStatusBarIconController;
    private int mStatusIconsWidth;
    String mStatusLabel;
    private StatusListDialog mStatusListDialog;
    private StatusListManager mStatusListManager;
    private ImageView mSysUiIconBt;
    private ImageView mSysUiIconPhoneSignal;
    private ImageView mSysUiIconPhoneSignalOrientation;
    private ImageView mSysUiIconPhoneSignalType;
    private ImageView mSysUiIconWiFi;
    private ImageView mSysUiIconWiFiData;
    private TextView mSysuiStatusBarInPM;
    private TextView mSysuiStatusBarOutPM;
    private TextView mSysuiStatusBarTime;
    TBoxSignalController.TBoxSignalState mTBoxState;
    private int mTSRState;
    public int mTimes;
    private boolean mUSBMounted;
    private int mUiMode;
    private UsbController mUsbController;
    private ImageView mUsbView;
    private ValueAnimator mValueAnimator;
    private VolumeController mVolumeController;
    SignalController.State mWifiState;
    private AnimationDrawable mWireLessChargeLeftAnimationDrawable;
    private AnimationDrawable mWireLessChargeRightAnimationDrawable;
    private ImageView mWirelessChargingLeftView;
    private ImageView mWirelessChargingRightView;
    private ImageView mWirelessChargingView;
    private int mWirelessFLSwitch;
    private int mWirelessFRSwitch;
    private int mWirelessStateFL;
    private int mWirelessStateFR;
    private boolean pm25SwitchFlag;
    private LinearLayout statusIcons;
    private ImageView sysui_icon_user;
    private VoiceImg sysui_status_bar_img_voice;
    private ImageView sysui_status_bar_img_voice_static;
    private TextView sysui_status_bar_time_left;
    private TextView sysui_status_bar_time_right;
    private TextView tv_pm;
    private TextView tv_pm_in_option;
    private TextView tv_pm_out_option;
    private H mHandler = new H();
    private boolean mIsInitControllers = false;
    private final BYDAutoStudyListener mAutoStudyListener = new AnonymousClass1();
    private String musicType = "music_type";
    public boolean mChargeAnimateLeft = false;
    public boolean mIsOnChargingLeft = false;
    public boolean mChargeAnimateRight = false;
    public boolean mIsOnChargingRight = false;
    public boolean mChargeAnimate = false;
    public boolean mIsOnCharging = false;
    private String mPMValueIn = "";
    private String mPMValueOut = "";
    private final ClockCallbacks mClockCallbacks = new ClockCallbacks(this, null);
    protected final CarControlCallback mCarControlCallback = new CarControlCallback();
    private int mCurrentMusicState = -1;
    private int mMusicSource = 0;
    private int mCurrentMusicStateOnSaved = -1;
    private int mMusicSourceOnSaved = 0;
    protected final VolumeControlCallback mVolumeControlCallback = new VolumeControlCallback();
    protected final BluetoothCallback mBluetoothCallback = new BluetoothCallback();
    protected final UsbCallback mUsbCallback = new UsbCallback();
    protected final WifiSignalCallback mSignalCallback = new WifiSignalCallback();
    private boolean isAppSetDayNight = false;
    private final AbsBYDAutoPowerListener mAbsBYDAutoPowerListener = new AbsBYDAutoPowerListener() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.4
        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerLevelChanged(int level) {
            super.onPowerLevelChanged(level);
            BydLogHelper.logW("AbsBYDAutoPowerListener", "CollapsedStatusBarFragment level = " + level);
            CollapsedStatusBarFragment.this.mHandler.removeMessages(4);
            if (level == 2) {
                CollapsedStatusBarFragment.this.getScreenBrightness(3);
                CollapsedStatusBarFragment.this.mHandler.sendEmptyMessageDelayed(4, 10000L);
            }
        }
    };
    private final BroadcastReceiver mDialogReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BydLogHelper.logD("action = " + action);
            if (Constants.SYS_BT_DIALOG_CHANGE_ACTION.equals(action) || Constants.SYS_WIFI_DIALOG_CHANGE_ACTION.equals(action) || Constants.SYS_DEVICE_DIALOG_CHANGE_ACTION.equals(action)) {
                BydLogHelper.logD("onReceive isShow = " + intent.getBooleanExtra("show", true));
                ComponentName currentTopActivity = TopActivityManager.getInstance(CollapsedStatusBarFragment.this.getContext()).getCurrentTopActivity();
                if (currentTopActivity == null || !currentTopActivity.getPackageName().equals("com.android.launcher3")) {
                    return;
                }
                CollapsedStatusBarFragment.this.getScreenBrightness();
            }
        }
    };
    private final BroadcastReceiver mDayNightReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BydLogHelper.logD("action = " + action);
            if (Constants.SYS_REQUEST_STATUSBAR_DAYNIGHT_ACTION.equals(action)) {
                if (((Divider) SysUiServiceProvider.getComponent(context, Divider.class)).isSplitActive()) {
                    BydLogHelper.logD("onReceive SYS_REQUEST_STATUSBAR_DAYNIGHT_ACTION, split screen is active, return!");
                    return;
                }
                int intExtra = intent.getIntExtra(Constants.SYS_REQUEST_STATUSBAR_DAYNIGHT_KEY, -1);
                BydLogHelper.logD("daynightmode value = " + intExtra);
                if (intExtra == 0) {
                    CollapsedStatusBarFragment.this.setStatusBarDayNight(false);
                } else if (intExtra == 1) {
                    CollapsedStatusBarFragment.this.setStatusBarDayNight(true);
                }
            }
        }
    };
    private final BroadcastReceiver mDvrReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BydLogHelper.logD("action = " + action);
            if (Constants.SYS_STATUSBAR_DVR_STATE_ACTION.equals(action)) {
                String stringExtra = intent.getStringExtra(Constants.SYS_STATUSBAR_DVR_STATE_KEY);
                BydLogHelper.logD("dvr value = " + stringExtra);
                if (Constants.STATE_DVR_NO_VIDEO.equals(stringExtra)) {
                    CollapsedStatusBarFragment.this.mDvrState = 1;
                } else if (Constants.STATE_DVR_NO_PAUSE.equals(stringExtra)) {
                    CollapsedStatusBarFragment.this.mDvrState = 2;
                } else {
                    CollapsedStatusBarFragment.this.mDvrState = 0;
                }
                CollapsedStatusBarFragment.this.updateDvr();
            }
        }
    };

    public static class StatusBarEvent {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPM25Value(int value) {
        if (value > 999) {
            return 999;
        }
        if (value < 5) {
            return 5;
        }
        return value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStatusIcons() {
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void disable(int state1, int state2, boolean animate) {
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setImeWindowStatus(IBinder token, int vis, int backDisposition, boolean showImeSwitcher) {
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setTopAppHidesStatusBar(boolean topAppHidesStatusBar) {
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void topAppWindowChanged(boolean visible) {
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (AppUtils.isRightRudder()) {
            return inflater.inflate(R.layout.status_bar_right_rudder, container, false);
        }
        return inflater.inflate(R.layout.status_bar, container, false);
    }

    public void updateNaviBarStatus(int value) {
        BydLogHelper.logD("updateNaviBarStatus value = " + value);
        if (value == 1) {
            updateMusicIcon(this.mCurrentMusicState, this.mMusicSource);
            updateStatusBarColor();
        }
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        this.mStatusBar = (PhoneStatusBarView) view;
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXTRA_PANEL_STATE)) {
                this.mStatusBar.restoreHierarchyState(savedInstanceState.getSparseParcelableArray(EXTRA_PANEL_STATE));
            }
            if (savedInstanceState.containsKey("mMusicSourceOnSaved")) {
                this.mMusicSourceOnSaved = savedInstanceState.getInt("mMusicSourceOnSaved");
            }
            if (savedInstanceState.containsKey("mCurrentMusicStateOnSaved")) {
                this.mCurrentMusicStateOnSaved = savedInstanceState.getInt("mCurrentMusicStateOnSaved");
            }
            Log.i(TAG, "mCurrentMusicStateOnSaved:" + this.mCurrentMusicStateOnSaved + ",mMusicSourceOnSaved：" + this.mMusicSourceOnSaved);
        }
        makeStatusBarView();
        IntentFilter intentFilter = new IntentFilter(Constants.SYS_BT_DIALOG_CHANGE_ACTION);
        intentFilter.addAction(Constants.SYS_WIFI_DIALOG_CHANGE_ACTION);
        intentFilter.addAction(Constants.SYS_DEVICE_DIALOG_CHANGE_ACTION);
        getContext().registerReceiver(this.mDialogReceiver, intentFilter);
        getContext().registerReceiver(this.mDayNightReceiver, new IntentFilter(Constants.SYS_REQUEST_STATUSBAR_DAYNIGHT_ACTION));
        getContext().registerReceiver(this.mDvrReceiver, new IntentFilter(Constants.SYS_STATUSBAR_DVR_STATE_ACTION));
    }

    private void playVoiceAnim() {
        VoiceImg voiceImg = this.sysui_status_bar_img_voice;
        if (voiceImg != null) {
            voiceImg.setAnimRes(R.drawable.voice_status_bar_anim);
            this.sysui_status_bar_img_voice.startModeAnimation();
        }
    }

    public void onBootCompleted() {
        addControllerCallback();
        initData();
        updateStatusBarView(((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive());
        getScreenBrightness();
        Log.i(TAG, "sendBroadcast ACTION_REQUEST_FOR_MEDIA_INFO");
        this.mContext.sendBroadcast(new Intent("com.byd.ACTION_REQUEST_FOR_MEDIA_INFO"));
    }

    public void onNavigationBarClicked(boolean isLongClick) {
        BydLogHelper.logD("isLongClick = " + isLongClick);
        dismissStatusListDialog();
    }

    /* JADX INFO: renamed from: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment$1, reason: invalid class name */
    class AnonymousClass1 extends BYDAutoStudyListener {
        AnonymousClass1() {
        }

        @Override // android.hardware.IBYDAutoListener
        public void isConnected(boolean isBinding) {
            super.isConnected(isBinding);
            int powerLevel = CollapsedStatusBarFragment.this.mBYDAutoBodyworkDevice.getPowerLevel();
            BydLogHelper.logD("isConnected powerLevel: " + powerLevel);
            if (powerLevel == 2 || powerLevel == 3) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$1$tKzaS3oBbWmMla_WrfZCI66kdWw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$isConnected$0$CollapsedStatusBarFragment$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$isConnected$0$CollapsedStatusBarFragment$1() {
            CollapsedStatusBarFragment.this.updatePmLayout();
        }
    }

    public void updatePmLayout() {
        int iHasMessage;
        CarController carController = this.mCarController;
        if (carController != null) {
            try {
                iHasMessage = carController.hasMessage(2);
            } catch (Throwable th) {
                Log.w(TAG, "hasMessage error ", th);
                iHasMessage = 0;
            }
            BydLogHelper.logD("pm2_5State = " + iHasMessage);
            LinearLayout linearLayout = this.mPmLayout;
            if (linearLayout != null) {
                if (iHasMessage == 1) {
                    linearLayout.setVisibility(0);
                } else {
                    linearLayout.setVisibility(8);
                }
            }
        }
    }

    public void updateStatusBarView(boolean isSplit) {
        BydLogHelper.logD("updateStatusBarView isSplit : " + isSplit);
        if (isSplit) {
            this.mStatusBar.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.sysui_status_bar_bg, null));
        } else {
            this.mStatusBar.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.color.transparent_00, null));
        }
    }

    public void updateStatusBarColorBySplit() {
        getScreenBrightness();
    }

    public void updateStatusColor(boolean isShow) {
        this.mStatusBar.setBackgroundColor(getResources().getColor(isShow ? R.color.bg_navi_cp_aa : R.color.transparent_00));
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        this.mStatusBar.saveHierarchyState(sparseArray);
        outState.putSparseParcelableArray(EXTRA_PANEL_STATE, sparseArray);
        outState.putInt("mCurrentMusicStateOnSaved", this.mCurrentMusicState);
        outState.putInt("mMusicSourceOnSaved", this.mMusicSource);
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        EventBus.getDefault().register(this);
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StatusBarEvent event) {
        Log.d(TAG, "onMessageEvent EXECUTE_STATUS_BAR_TASK");
        getScreenBrightness();
    }

    private void makeStatusBarView() {
        this.mMediaLayout = (LinearLayout) this.mStatusBar.findViewById(R.id.ll_media);
        this.mMediaPlayStatusImg = (MediaPlayingImg) this.mStatusBar.findViewById(R.id.sysui_status_bar_img_media_playing);
        this.mMediaPlayDefImg = (ImageView) this.mStatusBar.findViewById(R.id.sysui_status_bar_img_media_def);
        this.mMediaPlayCategoryTv = (TextView) this.mStatusBar.findViewById(R.id.sysui_status_bar_media_name);
        this.sysui_status_bar_img_voice = (VoiceImg) this.mStatusBar.findViewById(R.id.sysui_status_bar_img_voice);
        this.sysui_status_bar_img_voice_static = (ImageView) this.mStatusBar.findViewById(R.id.sysui_status_bar_img_voice_static);
        this.ll_voice = (LinearLayout) this.mStatusBar.findViewById(R.id.ll_voice);
        this.img_signal_btn = (ImageView) this.mStatusBar.findViewById(R.id.img_signal_btn);
        this.img_wifi_btn = (ImageView) this.mStatusBar.findViewById(R.id.img_wifi_btn);
        this.img_bt_btn = (ImageView) this.mStatusBar.findViewById(R.id.img_bt_btn);
        this.img_carplay_btn = (ImageView) this.mStatusBar.findViewById(R.id.img_carplay_btn);
        this.img_android_auto_btn = (ImageView) this.mStatusBar.findViewById(R.id.img_android_auto_btn);
        this.ll_android_auto = (RelativeLayout) this.mStatusBar.findViewById(R.id.ll_android_auto);
        this.ll_carplay = (RelativeLayout) this.mStatusBar.findViewById(R.id.ll_carplay);
        this.ll_swl = (FrameLayout) this.mStatusBar.findViewById(R.id.ll_slw);
        this.mIvTsr = (ImageView) this.mStatusBar.findViewById(R.id.img_slw);
        this.mIvTsrBtn = (ImageView) this.mStatusBar.findViewById(R.id.img_slw_btn);
        this.mPmLayout = (LinearLayout) this.mStatusBar.findViewById(R.id.ll_pm);
        this.mSysUiIconWiFi = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_wifi_signal);
        this.mSysUiIconWiFiData = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_wifi_data);
        this.mSysUiIconPhoneSignal = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_phone_signal_level);
        this.mSysUiIconPhoneSignalType = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_phone_signal_type);
        this.mSysUiIconPhoneSignalOrientation = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_phone_signal_orientation);
        this.mSysUiIconBt = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_bt);
        this.sysui_icon_user = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_user);
        this.statusIcons = (LinearLayout) this.mStatusBar.findViewById(R.id.statusIcons);
        this.mStatusListManager = new StatusListManager();
        this.mWirelessChargingView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_wireless_charging);
        this.mWirelessChargingLeftView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_wireless_charging_left);
        this.mWirelessChargingRightView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_wireless_charging_right);
        this.mMuteView = (ImageView) this.mStatusBar.findViewById(R.id.sysui_icon_mute);
        this.mUsbView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_usb);
        this.mSdcardView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_sdcard);
        this.mOTAView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_ota);
        this.mDvrView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_dvr);
        this.mShowModeView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_show_mode);
        this.mMicView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_mic);
        this.mCameraView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_camera);
        this.mMoreView = (ImageView) this.statusIcons.findViewById(R.id.sysui_icon_more);
        this.mWirelessChargingView.setOnClickListener(this);
        this.mWirelessChargingLeftView.setOnClickListener(this);
        this.mWirelessChargingRightView.setOnClickListener(this);
        this.mMuteView.setOnClickListener(this);
        this.mUsbView.setOnClickListener(this);
        this.mOTAView.setOnClickListener(this);
        this.mShowModeView.setOnClickListener(this);
        this.mMicView.setOnClickListener(this);
        this.mCameraView.setOnClickListener(this);
        this.mMoreView.setOnClickListener(this);
        this.mDvrView.setOnClickListener(this);
        this.mSdcardView.setOnClickListener(this);
        this.sysui_status_bar_time_left = (TextView) this.mStatusBar.findViewById(R.id.sysui_status_bar_time_left);
        this.sysui_status_bar_time_right = (TextView) this.mStatusBar.findViewById(R.id.sysui_status_bar_time_right);
        this.mSysuiStatusBarTime = (TextView) this.mStatusBar.findViewById(R.id.sysui_status_bar_time);
        this.tv_pm = (TextView) this.mStatusBar.findViewById(R.id.tv_pm);
        this.tv_pm_in_option = (TextView) this.mStatusBar.findViewById(R.id.tv_pm_in_option);
        this.tv_pm_out_option = (TextView) this.mStatusBar.findViewById(R.id.tv_pm_out_option);
        this.mSysuiStatusBarInPM = (TextView) this.mStatusBar.findViewById(R.id.sysui_status_bar_in_pm);
        this.mSysuiStatusBarOutPM = (TextView) this.mStatusBar.findViewById(R.id.sysui_status_bar_out_pm);
        this.ll_voice.setOnClickListener(this);
        this.mMediaLayout.setOnClickListener(this);
        this.img_signal_btn.setOnClickListener(this);
        this.img_wifi_btn.setOnClickListener(this);
        this.img_bt_btn.setOnClickListener(this);
        this.img_carplay_btn.setOnClickListener(this);
        this.img_android_auto_btn.setOnClickListener(this);
        this.mPmLayout.setOnClickListener(this);
        this.sysui_icon_user.setOnClickListener(this);
        this.mIvTsrBtn.setOnClickListener(this);
        if (StyleUtil.isDenza()) {
            this.sysui_status_bar_img_voice.setVisibility(8);
            this.sysui_status_bar_img_voice_static.setVisibility(0);
        } else {
            playVoiceAnim();
        }
    }

    public void updateStatusIcons() {
        BydLogHelper.logD("updateStatusIcons");
        updateStatusListDialog(false);
    }

    private void setViewVisibility(View view, boolean isVisibility) {
        view.setVisibility(isVisibility ? 0 : 8);
    }

    private void updateMoreView() {
        BydLogHelper.logD("updateMoreView");
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.mMoreView.setImageResource(R.mipmap.icon_status_bar_more_light);
                return;
            } else {
                this.mMoreView.setImageResource(R.mipmap.icon_status_bar_more_dark);
                return;
            }
        }
        this.mMoreView.setImageResource(R.mipmap.icon_status_bar_more);
    }

    private void addControllerCallback() {
        if (this.mIsInitControllers) {
            return;
        }
        StatusBarIconController statusBarIconController = (StatusBarIconController) Dependency.get(StatusBarIconController.class);
        this.mStatusBarIconController = statusBarIconController;
        statusBarIconController.setListener(this);
        ClockController clockController = (ClockController) Dependency.get(ClockController.class);
        this.mClockController = clockController;
        clockController.addCallback(this.mClockCallbacks);
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        carController.addCallback(this.mCarControlCallback);
        VolumeController volumeController = (VolumeController) Dependency.get(VolumeController.class);
        this.mVolumeController = volumeController;
        volumeController.addCallback(this.mVolumeControlCallback, this.mHandler);
        UsbController usbController = (UsbController) Dependency.get(UsbController.class);
        this.mUsbController = usbController;
        usbController.addCallback(this.mUsbCallback);
        BluetoothController bluetoothController = (BluetoothController) Dependency.get(BluetoothController.class);
        this.mBluetoothController = bluetoothController;
        bluetoothController.addCallback(this.mBluetoothCallback);
        NetworkController networkController = (NetworkController) Dependency.get(NetworkController.class);
        this.mNetworkController = networkController;
        networkController.addCallback((NetworkController.SignalCallback) this.mSignalCallback);
        this.mTBoxState = (TBoxSignalController.TBoxSignalState) this.mNetworkController.getTBoxState();
        this.mIsInitControllers = true;
    }

    private void initData() {
        Log.w(TAG, "isBootCompleted:" + GuideUtils.isBootCompleted());
        Log.w(TAG, "initData mReverseShowIcon:" + this.mReverseShowIcon + " , mIsScreenBright:" + this.mIsScreenBright);
        this.mUiMode = getResources().getConfiguration().uiMode;
        this.mIsRtl = AppUtils.isRtl(getContext());
        BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(this.mContext);
        this.mBydAutoPowerDevice = bYDAutoPowerDevice;
        bYDAutoPowerDevice.registerListener(this.mAbsBYDAutoPowerListener);
        this.mBYDAutoStudy = BYDAutoStudy.getInstance(this.mContext);
        this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        this.mBYDAutoStudy.registerConnectListener(this.mAutoStudyListener);
        boolean zIsVrInstalled = AppUtil.isVrInstalled(this.mContext);
        BydLogHelper.logD("isVrInstalled: " + zIsVrInstalled);
        this.ll_voice.setVisibility(zIsVrInstalled ? 0 : 8);
        updateTBoxSignal();
        this.mNetworkController.getState();
        Log.i(TAG, "mCurrentMusicStateOnSaved:" + this.mCurrentMusicStateOnSaved + " , mMusicSourceOnSaved" + this.mMusicSourceOnSaved);
        int i = this.mCurrentMusicStateOnSaved;
        if (i != -1) {
            updateMusicIcon(i, this.mMusicSourceOnSaved);
        }
        if (this.mCarController.isChargingListenerConnected()) {
            parseWirelessChargingRight(this.mCarController.getWirelessChargingFRState(), false);
            parseWirelessChargingLeft(this.mCarController.getWirelessChargingStatus(), false);
        } else {
            BydLogHelper.logD("mChargeListenerConnected is false");
        }
        updateTime(this.mClockController.getClockTime());
        this.mBTIsConnected = this.mBluetoothController.isConnected();
        updateBtConnected();
        this.mUsbController.getState();
        updatePM25TextColor();
        initPmState();
        if (!TextUtils.isEmpty(this.mStatusBarIconController.getStatusBean().getPMInOnSaved()) && !TextUtils.isEmpty(this.mStatusBarIconController.getStatusBean().getPMOutOnSaved())) {
            this.mPMValueIn = this.mStatusBarIconController.getStatusBean().getPMInOnSaved();
            this.mPMValueOut = this.mStatusBarIconController.getStatusBean().getPMOutOnSaved();
            updatePMView();
        } else {
            this.mPMValueIn = this.mContext.getString(R.string.sys_ui_txt_whippletree);
            this.mPMValueOut = this.mContext.getString(R.string.sys_ui_txt_whippletree);
            updatePM2p5();
        }
        if (this.mStatusBarIconController.getStatusBean().getCarModeValueOnSaved() != -1) {
            this.mCarModeValue = this.mStatusBarIconController.getStatusBean().getCarModeValueOnSaved();
        } else {
            this.mCarModeValue = this.mCarController.getShowCarModelType();
        }
        updateShowCarMode();
        updateMoreView();
        this.mOtaFlag = this.mCarController.getUpdateFlag();
        updateOta();
        updateDvr();
        updateMuteIcon();
        updateCarPlayIcon(this.mCarController.getCarPlayConnectionStatus());
        updateAndroidAutoIcon(this.mCarController.getAndroidAutoConnectionStatus());
        this.mMediaPlayCategoryTv.setMaxWidth(this.mContext.getResources().getDimensionPixelOffset(AppUtil.isLandscape(this.mContext) ? R.dimen.dp_100 : R.dimen.dp_55));
        if (this.mStatusBarIconController.getStatusBean().getMicOnSaved() != 0) {
            this.mMicShown = this.mStatusBarIconController.getStatusBean().getMicOnSaved() == 1;
            updateMicIcon();
        } else {
            parseMicShowState(this.mCarController.getMicPackageName(), true);
        }
        if (this.mStatusBarIconController.getStatusBean().getCameraOnSaved() != 0) {
            this.mCameraShown = this.mStatusBarIconController.getStatusBean().getCameraOnSaved() == 1;
            updateCameraIcon();
        } else {
            parseCameraState(this.mCarController.getCameraPackageName(), true);
        }
        if (this.mStatusBarIconController.getStatusBean().getMsgShownOnSaved() != 0) {
            updateMsgIcon(this.mStatusBarIconController.getStatusBean().getMsgShownOnSaved() == 1);
        } else {
            executeQueryMsg();
        }
        this.mTSRState = this.mCarController.getISLIWarningLevelSwitchStatus();
        updateTSR();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTSR() {
        BydLogHelper.logD("updateTSR mTSRState = " + this.mTSRState);
        if (isShowTSR()) {
            this.ll_swl.setVisibility(0);
            if (this.mTSRState == 2) {
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_open_light);
                        return;
                    } else {
                        this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_open_dark);
                        return;
                    }
                }
                this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_open);
                return;
            }
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_close_light);
                    return;
                } else {
                    this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_close_dark);
                    return;
                }
            }
            this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_close);
            return;
        }
        this.ll_swl.setVisibility(8);
    }

    private boolean isShowTSR() {
        int iHasMessage = BYDAutoBodyworkDevice.getInstance(this.mContext).hasMessage(10);
        BydLogHelper.logD("isShowTSR state = " + iHasMessage);
        int supportSLW = BYDAutoStudy.getInstance(this.mContext).getSupportSLW();
        BydLogHelper.logD("isShowTSR support = " + supportSLW);
        return supportSLW == 2 && iHasMessage == 1;
    }

    private void operateTSR() {
        BydLogHelper.logD("operateTSR", "state : " + this.mTSRState);
        if (this.mTSRState == 2) {
            if (this.mCarController.getSupportTSRFlow() == 2) {
                Context context = this.mContext;
                showSLWDialog(context, context.getResources().getString(R.string.tips_close_notice), this.mContext.getResources().getString(R.string.tips_slw_close_content));
                return;
            } else {
                revertSlwWhenClick();
                this.mCarController.setISLIWarningLevelSwitchStatus(1);
                this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$7jxi2Os2qVExreYC_XUYD6a25Bg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$operateTSR$0$CollapsedStatusBarFragment();
                    }
                }, 2000L);
                return;
            }
        }
        int trafficSignRecognize2B4State = this.mCarController.getTrafficSignRecognize2B4State();
        int intelligentSpeedLimitAlert2B4State = this.mCarController.getIntelligentSpeedLimitAlert2B4State();
        if (intelligentSpeedLimitAlert2B4State == 0 && trafficSignRecognize2B4State == 0) {
            ToastManager.show(this.mContext, R.string.toast_open_tsr_and_isli);
            return;
        }
        if (intelligentSpeedLimitAlert2B4State == 0 && (trafficSignRecognize2B4State == 1 || trafficSignRecognize2B4State == 2 || trafficSignRecognize2B4State == 3)) {
            ToastManager.show(this.mContext, R.string.toast_open_isli);
            return;
        }
        BydLogHelper.logD("handlerClick  updateItem");
        revertSlwWhenClick();
        this.mCarController.setISLIWarningLevelSwitchStatus(2);
        this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$DsOOEqjhoCpt38Q9g-azDysAUzI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$operateTSR$1$CollapsedStatusBarFragment();
            }
        }, 2000L);
    }

    public /* synthetic */ void lambda$operateTSR$0$CollapsedStatusBarFragment() {
        this.mTSRState = this.mCarController.getISLIWarningLevelSwitchStatus();
        updateTSR();
    }

    public /* synthetic */ void lambda$operateTSR$1$CollapsedStatusBarFragment() {
        this.mTSRState = this.mCarController.getISLIWarningLevelSwitchStatus();
        updateTSR();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void revertSlwWhenClick() {
        if (this.mTSRState == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_close_light);
                    return;
                } else {
                    this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_close_dark);
                    return;
                }
            }
            this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_close);
            return;
        }
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_open_light);
                return;
            } else {
                this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_open_dark);
                return;
            }
        }
        this.mIvTsr.setImageResource(R.mipmap.icon_status_bar_tsr_slw_open);
    }

    public void showSLWDialog(Context context, String title, String message) {
        if (this.mSLWDialog == null) {
            this.mSLWDialog = new CommonDialog(context);
        }
        BydLogHelper.logD("isShowing  : " + this.mSLWDialog.isShowing());
        if (this.mSLWDialog.isShowing()) {
            return;
        }
        this.mSLWDialog.setMessage(message).setTitle(title).setBottomStyle(2).setBtnLeft(context.getResources().getString(R.string.cancel)).setBtnRight(context.getResources().getString(R.string.confirm)).setBtnRightBgColor(true).setBtnRightTextColor(context.getColor(R.color.white)).setDoubleButtonStyle(1).setOnClickBottomListener(new AnonymousClass2()).show();
    }

    /* JADX INFO: renamed from: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment$2, reason: invalid class name */
    class AnonymousClass2 implements CommonDialog.OnClickBottomListener {
        AnonymousClass2() {
        }

        @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
        public void onPositiveClick() {
            CollapsedStatusBarFragment.this.mSLWDialog.dismiss();
            CollapsedStatusBarFragment.this.revertSlwWhenClick();
            CollapsedStatusBarFragment.this.mCarController.setISLIWarningLevelSwitchStatus(1);
            CollapsedStatusBarFragment.this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$2$6dN8B5WyWCx1T02CrPMyNTSlwbs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPositiveClick$0$CollapsedStatusBarFragment$2();
                }
            }, 2000L);
        }

        public /* synthetic */ void lambda$onPositiveClick$0$CollapsedStatusBarFragment$2() {
            CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
            collapsedStatusBarFragment.mTSRState = collapsedStatusBarFragment.mCarController.getISLIWarningLevelSwitchStatus();
            CollapsedStatusBarFragment.this.updateTSR();
        }

        @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
        public void onNegativeClick() {
            CollapsedStatusBarFragment.this.mSLWDialog.dismiss();
            CollapsedStatusBarFragment.this.updateTSR();
        }
    }

    public void updateStatusBarColor() {
        Log.w(TAG, "updateStatusBarColor mReverseShowIcon:" + this.mReverseShowIcon + " , mIsScreenBright:" + this.mIsScreenBright);
        updateTimeTextColor();
        updateMediaTextColor();
        updatePM25TextColor();
        updateTBoxSignal();
        updateWifiSignal();
        updateBtConnected();
        updateShowCarMode();
        updateOta();
        updateDvr();
        updateUSBConnected();
        updateSdCardMounted();
        updateMuteIcon();
        updateWirelessChargingRightIcon(false);
        updateWirelessChargingLeftAndRightIcon(false);
        updateCameraIcon();
        updateMicIcon();
        updateTSR();
        updateMoreView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWifiSignal() {
        SignalController.State state = this.mWifiState;
        if (state == null) {
            BydLogHelper.logD("mWifiState is null");
            return;
        }
        if (this.mSysUiIconWiFi != null) {
            if (!state.enabled || !this.mWifiState.connected) {
                this.mSysUiIconWiFiData.setVisibility(8);
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        if (this.mWifiState.enabled) {
                            this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_LIGHT[Icons.WIFI_SIGNAL_STRENGTH_LIGHT.length - 1]);
                            return;
                        } else {
                            this.mSysUiIconWiFi.setImageResource(R.mipmap.icon_status_bar_wifi_light_0);
                            return;
                        }
                    }
                    if (this.mWifiState.enabled) {
                        this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_DARK[Icons.WIFI_SIGNAL_STRENGTH_DARK.length - 1]);
                        return;
                    } else {
                        this.mSysUiIconWiFi.setImageResource(R.mipmap.icon_status_bar_wifi_dark_0);
                        return;
                    }
                }
                if (this.mWifiState.enabled) {
                    this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_DEFAULT[Icons.WIFI_SIGNAL_STRENGTH_DEFAULT.length - 1]);
                    return;
                } else {
                    this.mSysUiIconWiFi.setImageResource(R.mipmap.icon_status_bar_wifi_0);
                    return;
                }
            }
            if (TextUtils.isEmpty(this.mStatusLabel) || !TextUtils.equals(this.mStatusLabel, this.mContext.getString(R.string.wifi_status_no_internet))) {
                this.mSysUiIconWiFiData.setVisibility(0);
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_LIGHT[this.mWifiState.level]);
                        this.mSysUiIconWiFiData.setImageResource(R.mipmap.icon_status_bar_wifi_signal_data_light);
                        return;
                    } else {
                        this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_DARK[this.mWifiState.level]);
                        this.mSysUiIconWiFiData.setImageResource(R.mipmap.icon_status_bar_wifi_signal_data_dark);
                        return;
                    }
                }
                this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_DEFAULT[this.mWifiState.level]);
                this.mSysUiIconWiFiData.setImageResource(R.mipmap.icon_status_bar_wifi_signal_data);
                return;
            }
            this.mSysUiIconWiFiData.setVisibility(8);
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_ERROR_LIGHT[this.mWifiState.level]);
                    return;
                } else {
                    this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_ERROR_DARK[this.mWifiState.level]);
                    return;
                }
            }
            this.mSysUiIconWiFi.setImageResource(Icons.WIFI_SIGNAL_STRENGTH_ERROR_DEFAULT[this.mWifiState.level]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTBoxSignal() {
        int i;
        int i2;
        int i3;
        if (this.mTBoxState == null) {
            BydLogHelper.logD("mTBoxState is null");
            return;
        }
        BydLogHelper.logD("TBoxState state:" + this.mTBoxState.state);
        if (this.mTBoxState.state == 0 || this.mTBoxState.state == 1) {
            ImageView imageView = this.mSysUiIconPhoneSignal;
            if (imageView != null) {
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        imageView.setImageResource(R.mipmap.icon_status_bar_signal_light_none);
                    } else {
                        imageView.setImageResource(R.mipmap.icon_status_bar_signal_dark_none);
                    }
                } else {
                    imageView.setImageResource(R.mipmap.icon_status_bar_signal_none);
                }
                this.mSysUiIconPhoneSignal.setVisibility(0);
            }
            ImageView imageView2 = this.mSysUiIconPhoneSignalType;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            ImageView imageView3 = this.mSysUiIconPhoneSignalOrientation;
            if (imageView3 != null) {
                imageView3.setVisibility(8);
                return;
            }
            return;
        }
        if (this.mTBoxState.state == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = TelephonyIcons.TELEPHONY_DATA_TYPE_LIGHT[0];
                } else {
                    i = TelephonyIcons.TELEPHONY_DATA_TYPE_DARK[0];
                }
            } else {
                i = TelephonyIcons.TELEPHONY_DATA_TYPE[0];
            }
        } else if (this.mTBoxState.state == 3) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = TelephonyIcons.TELEPHONY_DATA_TYPE_LIGHT[1];
                } else {
                    i = TelephonyIcons.TELEPHONY_DATA_TYPE_DARK[1];
                }
            } else {
                i = TelephonyIcons.TELEPHONY_DATA_TYPE[1];
            }
        } else if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                i = TelephonyIcons.TELEPHONY_DATA_TYPE_LIGHT[2];
            } else {
                i = TelephonyIcons.TELEPHONY_DATA_TYPE_DARK[2];
            }
        } else {
            i = TelephonyIcons.TELEPHONY_DATA_TYPE[2];
        }
        BydLogHelper.logD("TBoxState up down status:" + this.mTBoxState.updown_status);
        if (this.mTBoxState.updown_status == 0) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_LIGHT[0];
                } else {
                    i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_DARK[0];
                }
            } else {
                i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION[0];
            }
        } else if (this.mTBoxState.updown_status == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_LIGHT[1];
                } else {
                    i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_DARK[1];
                }
            } else {
                i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION[1];
            }
        } else if (this.mTBoxState.updown_status == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_LIGHT[2];
                } else {
                    i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_DARK[2];
                }
            } else {
                i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION[2];
            }
        } else if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_LIGHT[3];
            } else {
                i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION_DARK[3];
            }
        } else {
            i2 = TelephonyIcons.TELEPHONY_DATA_ORIENTATION[3];
        }
        BydLogHelper.logD("TBoxState level:" + this.mTBoxState.level);
        if (this.mTBoxState.level < 0) {
            BydLogHelper.logD("invalid level:" + this.mTBoxState.level);
            this.mTBoxState.level = 0;
        }
        if (this.mTBoxState.level > 5) {
            BydLogHelper.logD("invalid level:" + this.mTBoxState.level);
            this.mTBoxState.level = 5;
        }
        if (this.mTBoxState.level == 0) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_LIGHT[0];
                } else {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_DARK[0];
                }
            } else {
                i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH[0];
            }
        } else if (this.mTBoxState.level == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_LIGHT[1];
                } else {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_DARK[1];
                }
            } else {
                i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH[1];
            }
        } else if (this.mTBoxState.level == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_LIGHT[2];
                } else {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_DARK[2];
                }
            } else {
                i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH[2];
            }
        } else if (this.mTBoxState.level == 3) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_LIGHT[3];
                } else {
                    i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_DARK[3];
                }
            } else {
                i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH[3];
            }
        } else if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_LIGHT[4];
            } else {
                i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH_DARK[4];
            }
        } else {
            i3 = TelephonyIcons.CELLULAR_SIGNAL_STRENGTH[4];
        }
        ImageView imageView4 = this.mSysUiIconPhoneSignalType;
        if (imageView4 != null) {
            imageView4.setImageResource(i);
            this.mSysUiIconPhoneSignalType.setVisibility(0);
        }
        ImageView imageView5 = this.mSysUiIconPhoneSignal;
        if (imageView5 != null) {
            imageView5.setImageResource(i3);
            this.mSysUiIconPhoneSignal.setVisibility(0);
        }
        ImageView imageView6 = this.mSysUiIconPhoneSignalOrientation;
        if (imageView6 != null) {
            imageView6.setImageResource(i2);
            if (this.mNetworkController.isDataEnabled()) {
                this.mSysUiIconPhoneSignalOrientation.setVisibility(0);
            } else {
                this.mSysUiIconPhoneSignalOrientation.setVisibility(8);
            }
        }
    }

    private void updateTimeTextColor() {
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.sysui_status_bar_time_left.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                this.sysui_status_bar_time_right.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                this.mSysuiStatusBarTime.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                return;
            } else {
                this.sysui_status_bar_time_left.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
                this.sysui_status_bar_time_right.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
                this.mSysuiStatusBarTime.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
                return;
            }
        }
        this.sysui_status_bar_time_left.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        this.sysui_status_bar_time_right.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        this.mSysuiStatusBarTime.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePM25TextColor() {
        updatePmLayout();
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.tv_pm.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                this.tv_pm_in_option.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                this.tv_pm_out_option.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                this.mSysuiStatusBarInPM.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                this.mSysuiStatusBarOutPM.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
                return;
            }
            this.tv_pm.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            this.tv_pm_in_option.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            this.tv_pm_out_option.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            this.mSysuiStatusBarInPM.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            this.mSysuiStatusBarOutPM.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            return;
        }
        this.tv_pm.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        this.tv_pm_in_option.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        this.tv_pm_out_option.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        this.mSysuiStatusBarInPM.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        this.mSysuiStatusBarOutPM.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
    }

    public void changeIcon() {
        getScreenBrightness();
    }

    public void removeMsg() {
        this.ignoreChange = true;
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(3);
    }

    public void getScreenBrightness() {
        getScreenBrightness(1);
    }

    public void getScreenBrightness(int times) {
        Log.i(TAG, "getScreenBrightness");
        this.ignoreChange = false;
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(3);
        startGetBright(times);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeStatusBarTask() {
        new StatusBarAreaAnalyseTask().execute(new Void[0]);
    }

    public void getScreenBrightnessDelay() {
        Log.d(TAG, "getScreenBrightnessDelay");
        Bitmap bitmapTakeScreenShot = ScreenShotUtil.takeScreenShot(this.mContext);
        if (bitmapTakeScreenShot == null) {
            Log.d(TAG, "getScreenBrightnessDelay screenshot null");
            return;
        }
        Bitmap bitmapCopy = bitmapTakeScreenShot.copy(Bitmap.Config.ARGB_8888, true);
        recycleBitmap(bitmapTakeScreenShot);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCopy, 0, 0, bitmapCopy.getWidth(), Util.getStatusBarHeight(this.mContext));
        recycleBitmap(bitmapCopy);
        if (getBright(bitmapCreateBitmap) >= 128) {
            Log.d(TAG, "getScreenBrightnessDelay bright");
            this.mIsScreenBright = true;
        } else {
            Log.d(TAG, "getScreenBrightnessDelay drak");
            this.mIsScreenBright = false;
        }
        updateStatusBarColor();
        recycleBitmap(bitmapCreateBitmap);
    }

    public void recycleBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public static int getBright(Bitmap bitmap) {
        if (bitmap == null) {
            return -1;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = 0;
        for (int i3 = 0; i3 < width; i3++) {
            for (int i4 = 0; i4 < height; i4++) {
                int i5 = iArr[(i4 * width) + i3];
                i2 = (int) (((double) i2) + (((double) ((i5 >> 16) & 255)) * 0.299d) + (((double) ((i5 >> 8) & 255)) * 0.587d) + (((double) (i5 & 255)) * 0.114d));
            }
        }
        return i2 / i;
    }

    private boolean isZh() {
        return Locale.getDefault().getLanguage().endsWith("zh");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMsgIcon(boolean hasMsg) {
        BydLogHelper.logD(TAG, "hasMsg:" + hasMsg);
        if (this.sysui_icon_user != null) {
            if (hasMsg) {
                this.mStatusBarIconController.getStatusBean().setMsgShownOnSaved(1);
                this.sysui_icon_user.setImageResource(R.mipmap.img_personal);
            } else {
                this.mStatusBarIconController.getStatusBean().setMsgShownOnSaved(2);
                this.sysui_icon_user.setImageResource(R.mipmap.img_personal_normal);
            }
        }
    }

    public void executeQueryMsg() {
        new QueryMsgTask().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int queryMsg() {
        int i = 0;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.mContext.getContentResolver().query(Uri.parse(Constants.MSG_PROVIDER_URI), Constants.COLUMNS_MSG, null, null, null);
                if (cursorQuery != null) {
                    Log.i(TAG, "cursor count:" + cursorQuery.getCount());
                    if (cursorQuery.moveToFirst()) {
                        do {
                            i = cursorQuery.getInt(cursorQuery.getColumnIndex("hasUnreadMsg"));
                            Log.i(TAG, "hasUnreadMsg :" + i);
                        } while (cursorQuery.moveToNext());
                    }
                    cursorQuery.close();
                } else {
                    Log.i(TAG, "cursor is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (0 != 0) {
                }
            }
            return i;
        } finally {
            if (0 != 0) {
                cursorQuery.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCarPlayIcon(boolean connected) {
        if (this.ll_carplay != null) {
            BydLogHelper.logD("updateCarPlayIcon", "connected:" + connected);
            this.ll_carplay.setVisibility(connected ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAndroidAutoIcon(boolean connected) {
        if (this.ll_android_auto != null) {
            BydLogHelper.logD("updateAndroidAutoIcon", "connected:" + connected);
            this.ll_android_auto.setVisibility(connected ? 0 : 8);
        }
    }

    private void upMusicIcon() {
        int i = SharedPreferencesUtil.getInt(this.mContext, this.musicType, -1);
        Log.i(TAG, "upMusicIcon type:" + i);
        if (i == -1) {
            this.mMediaLayout.setVisibility(8);
        } else {
            updateMusicIcon(2, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return;
        }
        if (time.contains(ClockControllerImpl.SPLIT)) {
            if (isZh()) {
                this.sysui_status_bar_time_left.setVisibility(0);
                this.sysui_status_bar_time_right.setVisibility(8);
            } else {
                this.sysui_status_bar_time_left.setVisibility(8);
                this.sysui_status_bar_time_right.setVisibility(0);
            }
            String[] strArrSplit = time.split(ClockControllerImpl.SPLIT);
            this.sysui_status_bar_time_left.setText(strArrSplit[0]);
            this.sysui_status_bar_time_right.setText(strArrSplit[0]);
            this.mSysuiStatusBarTime.setText(strArrSplit[1]);
            BydLogHelper.logD("updateTime", "time : " + strArrSplit[0] + " : " + strArrSplit[1]);
        } else {
            this.sysui_status_bar_time_left.setVisibility(8);
            this.sysui_status_bar_time_right.setVisibility(8);
            this.mSysuiStatusBarTime.setText(time);
        }
        updateTimeTextColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOta() {
        BydLogHelper.logD("mOtaFlag:" + this.mOtaFlag);
        if (this.mOtaFlag) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mOTAView.setImageResource(R.mipmap.icon_status_bar_ota_light);
                } else {
                    this.mOTAView.setImageResource(R.mipmap.icon_status_bar_ota_dark);
                }
            } else {
                this.mOTAView.setImageResource(R.mipmap.icon_status_bar_ota);
            }
            this.mOTAView.setVisibility(0);
        } else {
            this.mOTAView.setVisibility(8);
        }
        this.mStatusListManager.setVisibility(2, this.mOtaFlag);
        updateStatusIcons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDvr() {
        BydLogHelper.logD("mDvrState :" + this.mDvrState);
        int i = this.mDvrState;
        if (i == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mDvrView.setImageResource(R.mipmap.icon_status_bar_dvr_error_light);
                } else {
                    this.mDvrView.setImageResource(R.mipmap.icon_status_bar_dvr_error_dark);
                }
            } else {
                this.mDvrView.setImageResource(R.mipmap.icon_status_bar_dvr_error);
            }
            this.mDvrView.setVisibility(0);
        } else if (i == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mDvrView.setImageResource(R.mipmap.icon_status_bar_dvr_pause_light);
                } else {
                    this.mDvrView.setImageResource(R.mipmap.icon_status_bar_dvr_pause_dark);
                }
            } else {
                this.mDvrView.setImageResource(R.mipmap.icon_status_bar_dvr_pause);
            }
            this.mDvrView.setVisibility(0);
        } else {
            this.mDvrView.setVisibility(8);
        }
        this.mStatusListManager.setStatus(1, this.mDvrState);
        StatusListManager statusListManager = this.mStatusListManager;
        int i2 = this.mDvrState;
        statusListManager.setVisibility(1, i2 == 1 || i2 == 2);
        updateStatusIcons();
    }

    private void updateMultiMediaCategory(String category) {
        BydLogHelper.logD(" category:" + category);
        if (TextUtils.isEmpty(category)) {
            return;
        }
        this.mMediaPlayCategoryTv.setText(category);
        this.mMediaLayout.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShowCarMode() {
        int i;
        BydLogHelper.logD(" mCarModeValue:" + this.mCarModeValue);
        int i2 = this.mCarModeValue;
        boolean z = true;
        if (i2 == 1) {
            if (this.mReverseShowIcon) {
                i = this.mIsScreenBright ? R.mipmap.icon_status_bar_indoor_mode_light : R.mipmap.icon_status_bar_indoor_mode_dark;
            } else {
                i = R.mipmap.icon_status_bar_indoor_mode;
            }
        } else if (i2 == 2) {
            if (this.mReverseShowIcon) {
                i = this.mIsScreenBright ? R.mipmap.icon_status_bar_outdoor_mode_light : R.mipmap.icon_status_bar_outdoor_mode_dark;
            } else {
                i = R.mipmap.icon_status_bar_outdoor_mode;
            }
        } else {
            this.mShowModeView.setVisibility(8);
            i = 0;
            z = false;
        }
        this.mStatusBarIconController.getStatusBean().setCarModeValueOnSaved(this.mCarModeValue);
        if (z) {
            this.mShowModeView.setImageResource(i);
            this.mShowModeView.setVisibility(0);
        }
        this.mStatusListManager.setStatus(0, this.mCarModeValue);
        this.mStatusListManager.setVisibility(0, z);
        updateStatusIcons();
    }

    public void setChargeAnimateLeftEnable() {
        this.mChargeAnimateLeft = true;
    }

    public void setChargeAnimateLeftDisable() {
        this.mChargeAnimateLeft = false;
    }

    public void setOnChargingAnimationLeftDisable() {
        this.mIsOnChargingLeft = false;
    }

    public void setOnChargingAnimationLeftEnable() {
        this.mIsOnChargingLeft = true;
    }

    public void setChargeAnimateRightEnable() {
        this.mChargeAnimateRight = true;
    }

    public void setChargeAnimateRightDisable() {
        this.mChargeAnimateRight = false;
    }

    public void setOnChargingAnimationRightDisable() {
        this.mIsOnChargingRight = false;
    }

    public void setOnChargingAnimationRightEnable() {
        this.mIsOnChargingRight = true;
    }

    public void setChargeAnimateEnable() {
        this.mChargeAnimate = true;
    }

    public void setChargeAnimateDisable() {
        this.mChargeAnimate = false;
    }

    public void setOnChargingAnimationDisable() {
        this.mIsOnCharging = false;
    }

    public void setOnChargingAnimationEnable() {
        this.mIsOnCharging = true;
    }

    public void updateChargeAnimate(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        AnimationDrawable animationDrawable = this.mAnimationDrawable;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.mAnimationDrawable.stop();
            this.mAnimationDrawable = null;
        }
        imageView.clearAnimation();
        if (this.mChargeAnimate) {
            if (this.mIsOnCharging) {
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                this.mAnimationDrawable = animationDrawable2;
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_LIGHT[0], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_LIGHT[1], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_LIGHT[2], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_LIGHT[3], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_LIGHT[4], null), 1000);
                    } else {
                        animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_DARK[0], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_DARK[1], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_DARK[2], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_DARK[3], null), 1000);
                        this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS_DARK[4], null), 1000);
                    }
                } else {
                    animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS[0], null), 1000);
                    this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS[1], null), 1000);
                    this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS[2], null), 1000);
                    this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS[3], null), 1000);
                    this.mAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_ICONS[4], null), 1000);
                }
                this.mAnimationDrawable.setOneShot(false);
                imageView.setImageDrawable(this.mAnimationDrawable);
                this.mAnimationDrawable.start();
                return;
            }
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            alphaAnimation.setDuration(1000L);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            imageView.startAnimation(alphaAnimation);
        }
    }

    public void updateChargeAnimateLeft(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        AnimationDrawable animationDrawable = this.mWireLessChargeLeftAnimationDrawable;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.mWireLessChargeLeftAnimationDrawable.stop();
            this.mWireLessChargeLeftAnimationDrawable = null;
        }
        imageView.clearAnimation();
        if (this.mChargeAnimateLeft) {
            if (this.mIsOnChargingLeft) {
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                this.mWireLessChargeLeftAnimationDrawable = animationDrawable2;
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[0], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[1], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[2], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[3], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[4], null), 1000);
                    } else {
                        animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[0], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[1], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[2], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[3], null), 1000);
                        this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[4], null), 1000);
                    }
                } else {
                    animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS[0], null), 1000);
                    this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS[1], null), 1000);
                    this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS[2], null), 1000);
                    this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS[3], null), 1000);
                    this.mWireLessChargeLeftAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_LEFT_ICONS[4], null), 1000);
                }
                this.mWireLessChargeLeftAnimationDrawable.setOneShot(false);
                imageView.setImageDrawable(this.mWireLessChargeLeftAnimationDrawable);
                this.mWireLessChargeLeftAnimationDrawable.start();
                return;
            }
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            alphaAnimation.setDuration(1000L);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            imageView.startAnimation(alphaAnimation);
        }
    }

    public void updateChargeAnimateRight(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        AnimationDrawable animationDrawable = this.mWireLessChargeRightAnimationDrawable;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.mWireLessChargeRightAnimationDrawable.stop();
            this.mWireLessChargeRightAnimationDrawable = null;
        }
        imageView.clearAnimation();
        if (this.mChargeAnimateRight) {
            if (this.mIsOnChargingRight) {
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                this.mWireLessChargeRightAnimationDrawable = animationDrawable2;
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[0], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[1], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[2], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[3], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[4], null), 1000);
                    } else {
                        animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[0], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[1], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[2], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[3], null), 1000);
                        this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[4], null), 1000);
                    }
                } else {
                    animationDrawable2.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS[0], null), 1000);
                    this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS[1], null), 1000);
                    this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS[2], null), 1000);
                    this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS[3], null), 1000);
                    this.mWireLessChargeRightAnimationDrawable.addFrame(this.mContext.getResources().getDrawable(Icons.WIRELESS_CHARGING_RIGHT_ICONS[4], null), 1000);
                }
                this.mWireLessChargeRightAnimationDrawable.setOneShot(false);
                imageView.setImageDrawable(this.mWireLessChargeRightAnimationDrawable);
                this.mWireLessChargeRightAnimationDrawable.start();
                return;
            }
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            alphaAnimation.setDuration(1000L);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            imageView.startAnimation(alphaAnimation);
        }
    }

    private void updateWirelessChargingRightIcon(boolean needShowDialog) {
        int i = this.mWirelessFRSwitch;
        if (i == 1 || i == 2) {
            updateWirelessChargingRightStatus(needShowDialog);
            return;
        }
        ImageView imageView = this.mWirelessChargingRightView;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    private void updateWirelessChargingLeftAndRightIcon(boolean needShowDialog) {
        int i;
        int i2;
        int i3 = this.mWirelessFLSwitch;
        if ((i3 == 1 || i3 == 2) && ((i = this.mWirelessFRSwitch) == 1 || i == 2)) {
            updateWirelessChargingLeftStatus(this.mWirelessStateFL, needShowDialog);
            ImageView imageView = this.mWirelessChargingView;
            if (imageView != null) {
                imageView.clearAnimation();
                this.mWirelessChargingView.setVisibility(8);
            }
            this.mStatusListManager.setVisibility(6, false);
        } else if ((i3 == 1 || i3 == 2) && (i2 = this.mWirelessFRSwitch) != 1 && i2 != 2) {
            updateWirelessChargingStatus(this.mWirelessStateFL, needShowDialog);
            ImageView imageView2 = this.mWirelessChargingLeftView;
            if (imageView2 != null) {
                imageView2.clearAnimation();
                this.mWirelessChargingLeftView.setVisibility(8);
            }
            ImageView imageView3 = this.mWirelessChargingRightView;
            if (imageView3 != null) {
                imageView3.clearAnimation();
                this.mWirelessChargingRightView.setVisibility(8);
            }
            this.mStatusListManager.setVisibility(5, false);
            this.mStatusListManager.setVisibility(7, false);
        } else {
            ImageView imageView4 = this.mWirelessChargingView;
            if (imageView4 != null) {
                imageView4.clearAnimation();
                this.mWirelessChargingView.setVisibility(8);
            }
            ImageView imageView5 = this.mWirelessChargingLeftView;
            if (imageView5 != null) {
                imageView5.clearAnimation();
                this.mWirelessChargingLeftView.setVisibility(8);
            }
            ImageView imageView6 = this.mWirelessChargingRightView;
            if (imageView6 != null) {
                imageView6.clearAnimation();
                this.mWirelessChargingRightView.setVisibility(8);
            }
        }
        updateStatusIcons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseWirelessChargingRight(int wirelessStateFR, boolean needShowDialog) {
        this.mWirelessStateFR = wirelessStateFR;
        this.mWirelessFRSwitch = this.mCarController.getWirelessChargingFRSwitchState();
        updateWirelessChargingRightIcon(needShowDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseWirelessChargingLeft(int wirelessStateFL, boolean needShowDialog) {
        this.mWirelessStateFL = wirelessStateFL;
        this.mWirelessFLSwitch = this.mCarController.getWirelessChargingSwitchState();
        this.mWirelessFRSwitch = this.mCarController.getWirelessChargingFRSwitchState();
        updateWirelessChargingLeftAndRightIcon(needShowDialog);
    }

    private void showWirelessAnimDialog(int wirelessPower) {
        BydLogHelper.logD("showWirelessAnimDialog");
    }

    private void updateWirelessChargingLeftStatus(int mWirelessStateFL, boolean needShowDialog) {
        int i;
        ImageView imageView;
        BydLogHelper.logD("updateWirelessChargingLeftStatus needShowDialog:" + needShowDialog);
        boolean z = true;
        if (mWirelessStateFL == 0) {
            if (needShowDialog && isCanShowWirelessAnimaDialog()) {
                showWirelessAnimDialog(this.mCarController.getWirelessChargingPowerFLState());
            }
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[0];
                } else {
                    i = Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[0];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_LEFT_ICONS[0];
            }
            setChargeAnimateLeftEnable();
            setOnChargingAnimationLeftEnable();
        } else if (mWirelessStateFL == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[4];
                } else {
                    i = Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[4];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_LEFT_ICONS[4];
            }
            setChargeAnimateLeftDisable();
            setOnChargingAnimationLeftDisable();
        } else if (mWirelessStateFL == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_LEFT_ICONS_LIGHT[5];
                } else {
                    i = Icons.WIRELESS_CHARGING_LEFT_ICONS_DARK[5];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_LEFT_ICONS[5];
            }
            setChargeAnimateLeftEnable();
            setOnChargingAnimationLeftDisable();
        } else {
            setChargeAnimateLeftDisable();
            setOnChargingAnimationLeftDisable();
            ImageView imageView2 = this.mWirelessChargingLeftView;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            i = 0;
            z = false;
        }
        if (z && (imageView = this.mWirelessChargingLeftView) != null) {
            imageView.setImageResource(i);
            this.mWirelessChargingLeftView.setVisibility(0);
        }
        updateChargeAnimateLeft(this.mWirelessChargingLeftView);
        this.mStatusListManager.setVisibility(5, z);
        this.mStatusListManager.setStatus(5, mWirelessStateFL);
    }

    private void updateWirelessChargingRightStatus(boolean needShowDialog) {
        int i;
        ImageView imageView;
        BydLogHelper.logD("updateWirelessChargingRightStatus needShowDialog:" + needShowDialog + ",mWirelessStateFR:" + this.mWirelessStateFR);
        int i2 = this.mWirelessStateFR;
        boolean z = true;
        if (i2 == 0) {
            if (needShowDialog && isCanShowWirelessAnimaDialog() && isCanShowWirelessAnimaDialog()) {
                showWirelessAnimDialog(this.mCarController.getWirelessChargingPowerFRState());
            }
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[0];
                } else {
                    i = Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[0];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_RIGHT_ICONS[0];
            }
            setChargeAnimateRightEnable();
            setOnChargingAnimationRightEnable();
        } else if (i2 == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[4];
                } else {
                    i = Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[4];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_RIGHT_ICONS[4];
            }
            setChargeAnimateRightDisable();
            setOnChargingAnimationRightDisable();
        } else if (i2 == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_RIGHT_ICONS_LIGHT[5];
                } else {
                    i = Icons.WIRELESS_CHARGING_RIGHT_ICONS_DARK[5];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_RIGHT_ICONS[5];
            }
            setChargeAnimateRightEnable();
            setOnChargingAnimationRightDisable();
        } else {
            setChargeAnimateRightDisable();
            setOnChargingAnimationRightDisable();
            ImageView imageView2 = this.mWirelessChargingRightView;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            i = 0;
            z = false;
        }
        if (z && (imageView = this.mWirelessChargingRightView) != null) {
            imageView.setImageResource(i);
            this.mWirelessChargingRightView.setVisibility(0);
        }
        updateChargeAnimateRight(this.mWirelessChargingRightView);
        this.mStatusListManager.setVisibility(7, z);
        this.mStatusListManager.setStatus(7, this.mWirelessStateFR);
        updateStatusIcons();
    }

    private boolean isCanShowWirelessAnimaDialog() {
        boolean zIsSplitActive = ((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive();
        BydLogHelper.logD("isSplitActive:" + zIsSplitActive);
        return (!GuideUtils.isProvisionFinished(this.mContext) || !GuideUtils.isSetupFinished(this.mContext) || zIsSplitActive || this.mBluetoothController.onCalling() || this.mCarController.isAvmForeground() || this.mCarController.isRMode()) ? false : true;
    }

    private void updateWirelessChargingStatus(int status, boolean needShowDialog) {
        int i;
        ImageView imageView;
        BydLogHelper.logD("updateWirelessChargingStatus status:" + status + ",needShowDialog:" + needShowDialog);
        boolean z = true;
        if (status == 0) {
            if (needShowDialog && isCanShowWirelessAnimaDialog()) {
                showWirelessAnimDialog(this.mCarController.getWirelessChargingPowerFLState());
            }
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_ICONS_LIGHT[0];
                } else {
                    i = Icons.WIRELESS_CHARGING_ICONS_DARK[0];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_ICONS[0];
            }
            setOnChargingAnimationEnable();
            setChargeAnimateEnable();
        } else if (status == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_ICONS_LIGHT[4];
                } else {
                    i = Icons.WIRELESS_CHARGING_ICONS_DARK[4];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_ICONS[4];
            }
            setChargeAnimateDisable();
            setOnChargingAnimationDisable();
        } else if (status == 2) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    i = Icons.WIRELESS_CHARGING_ICONS_LIGHT[5];
                } else {
                    i = Icons.WIRELESS_CHARGING_ICONS_DARK[5];
                }
            } else {
                i = Icons.WIRELESS_CHARGING_ICONS[5];
            }
            setChargeAnimateEnable();
            setOnChargingAnimationDisable();
        } else {
            setChargeAnimateDisable();
            setOnChargingAnimationDisable();
            ImageView imageView2 = this.mWirelessChargingView;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            i = 0;
            z = false;
        }
        if (z && (imageView = this.mWirelessChargingView) != null) {
            imageView.setImageResource(i);
            this.mWirelessChargingView.setVisibility(0);
        }
        updateChargeAnimate(this.mWirelessChargingView);
        this.mStatusListManager.setVisibility(6, z);
        this.mStatusListManager.setStatus(6, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPmState() {
        int pM2p5DualChannelDetectState = this.mCarController.getPM2p5DualChannelDetectState();
        this.mPM2p5DualChannelDetectState = pM2p5DualChannelDetectState;
        if (pM2p5DualChannelDetectState == 1) {
            this.mPM2p5InState = 1;
            this.mPM2p5OutState = 1;
            return;
        }
        if (pM2p5DualChannelDetectState == 2) {
            this.mPM2p5InState = 2;
            this.mPM2p5OutState = 2;
            return;
        }
        int[] pM2p5CheckState = this.mCarController.getPM2p5CheckState();
        if (pM2p5CheckState != null && pM2p5CheckState.length > 1) {
            this.mPM2p5InState = pM2p5CheckState[0];
            this.mPM2p5OutState = pM2p5CheckState[1];
        } else {
            this.mPM2p5InState = 2;
            this.mPM2p5OutState = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePM2p5() {
        int[] pM2p5Value;
        int[] pM2p5Value2;
        BydLogHelper.logD("updatePM2p5");
        this.tv_pm.setText(this.mContext.getString(R.string.pm_2p5));
        if (this.mPM2p5InState == 1 && (pM2p5Value2 = this.mCarController.getPM2p5Value()) != null && pM2p5Value2.length > 0) {
            BydLogHelper.logD("pmIn:" + pM2p5Value2[0]);
            this.mPMValueIn = String.valueOf(getPM25Value(pM2p5Value2[0]));
        } else {
            this.mPMValueIn = this.mContext.getString(R.string.sys_ui_txt_whippletree);
        }
        if (this.mPM2p5OutState == 1 && (pM2p5Value = this.mCarController.getPM2p5Value()) != null && pM2p5Value.length > 1) {
            BydLogHelper.logD("pmOut:" + pM2p5Value[1]);
            this.mPMValueOut = String.valueOf(getPM25Value(pM2p5Value[1]));
        } else {
            this.mPMValueOut = this.mContext.getString(R.string.sys_ui_txt_whippletree);
        }
        updatePMView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePM2p5FromDetectChanged() {
        BydLogHelper.logD("updatePM2p5FromDetectChanged:" + this.mPM2p5DualChannelDetectState);
        int i = this.mPM2p5DualChannelDetectState;
        if (i == 1) {
            this.pm25SwitchFlag = true;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    BydLogHelper.logD("PM2p5DualChannelDetectState set pm25 value");
                    CollapsedStatusBarFragment.this.pm25SwitchFlag = false;
                    if (CollapsedStatusBarFragment.this.mPM2p5DualChannelDetectState != 1) {
                        BydLogHelper.logD("mPM2p5DualChannelDetectState is not open! mPM2p5DualChannelDetectState = " + CollapsedStatusBarFragment.this.mPM2p5DualChannelDetectState);
                        return;
                    }
                    int[] pM2p5Value = CollapsedStatusBarFragment.this.mCarController.getPM2p5Value();
                    if (pM2p5Value != null && pM2p5Value.length > 1) {
                        BydLogHelper.logD(" pmIn:" + pM2p5Value[0] + " , pmOut:" + pM2p5Value[1]);
                        CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
                        collapsedStatusBarFragment.mPMValueIn = String.valueOf(collapsedStatusBarFragment.getPM25Value(pM2p5Value[0]));
                        CollapsedStatusBarFragment collapsedStatusBarFragment2 = CollapsedStatusBarFragment.this;
                        collapsedStatusBarFragment2.mPMValueOut = String.valueOf(collapsedStatusBarFragment2.getPM25Value(pM2p5Value[1]));
                    } else {
                        CollapsedStatusBarFragment collapsedStatusBarFragment3 = CollapsedStatusBarFragment.this;
                        collapsedStatusBarFragment3.mPMValueIn = collapsedStatusBarFragment3.mContext.getString(R.string.sys_ui_txt_whippletree);
                        CollapsedStatusBarFragment collapsedStatusBarFragment4 = CollapsedStatusBarFragment.this;
                        collapsedStatusBarFragment4.mPMValueOut = collapsedStatusBarFragment4.mContext.getString(R.string.sys_ui_txt_whippletree);
                    }
                    CollapsedStatusBarFragment.this.updatePMView();
                }
            }, 3000L);
            return;
        }
        if (i == 2) {
            this.pm25SwitchFlag = false;
            this.mPMValueIn = this.mContext.getString(R.string.sys_ui_txt_whippletree);
            this.mPMValueOut = this.mContext.getString(R.string.sys_ui_txt_whippletree);
            updatePMView();
            return;
        }
        if (i == 0) {
            this.pm25SwitchFlag = false;
        } else if (i == 3) {
            this.pm25SwitchFlag = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePMView() {
        TextView textView = this.mSysuiStatusBarInPM;
        if (textView == null || this.mSysuiStatusBarOutPM == null) {
            return;
        }
        textView.setText(this.mPMValueIn);
        this.mSysuiStatusBarOutPM.setText(this.mPMValueOut);
        this.mStatusBarIconController.getStatusBean().setPMInOnSaved(this.mPMValueIn);
        this.mStatusBarIconController.getStatusBean().setPMOutOnSaved(this.mPMValueOut);
        updatePM25TextColor();
    }

    public void updateBtConnected() {
        Log.d(TAG, "updateBtConnected btConnected = " + this.mBTIsConnected);
        if (this.mBTIsConnected) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_LIGHT[2]);
                } else {
                    this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_DARK[2]);
                }
            } else {
                this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS[2]);
            }
        } else if (this.mBluetoothController.isEnable()) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_LIGHT[1]);
                } else {
                    this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_DARK[1]);
                }
            } else {
                this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS[1]);
            }
        } else if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_LIGHT[0]);
            } else {
                this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_DARK[0]);
            }
        } else {
            this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS[0]);
        }
        this.mSysUiIconBt.setVisibility(0);
    }

    public void updateBtEnable(boolean enabled) {
        Log.d(TAG, "updateBtEnable enabled = " + enabled);
        if (enabled) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_LIGHT[1]);
                    return;
                } else {
                    this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_DARK[1]);
                    return;
                }
            }
            this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS[1]);
            return;
        }
        this.mBTIsConnected = false;
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_LIGHT[0]);
                return;
            } else {
                this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS_DARK[0]);
                return;
            }
        }
        this.mSysUiIconBt.setImageResource(Icons.BLUETOOTH_ICONS[0]);
    }

    private void startVoice() {
        Intent intent = new Intent(Constants.PackageInfo.ACTION_START_VOICE_FROM_LAUNCHER);
        intent.setFlags(16777248);
        this.mContext.sendBroadcast(intent);
    }

    private void startCarPlay(boolean flag) {
        Log.i(TAG, "send broad to cp");
        Intent intent = new Intent("com.byd.card.open.carplay");
        intent.putExtra("from", "status_bar");
        if (flag) {
            intent.putExtra("playStatus", this.mCurrentMusicState);
        }
        intent.setPackage("com.byd.uereport");
        this.mContext.sendBroadcast(intent);
    }

    private void startAndroidAuto(boolean flag) {
        Log.i(TAG, "send broad to aa");
        Intent intent = new Intent("com.byd.card.open.auto");
        intent.putExtra("from", "status_bar");
        if (flag) {
            intent.putExtra("playStatus", this.mCurrentMusicState);
        }
        intent.setPackage("com.byd.uereport");
        this.mContext.sendBroadcast(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x013e  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r5) {
        /*
            Method dump skipped, instruction units count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.onClick(android.view.View):void");
    }

    public void dismissLauncherSelectDialog() {
        ((VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class)).dismissLauncherDialog();
    }

    public static void sendEvent(int keyCode, int action, int flags, long down, long when) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(down, when, action, keyCode, (flags & 128) != 0 ? 1 : 0, 0, -1, 0, flags | 8 | 64, 257), 0);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        BydLogHelper.logD("onResume");
        ((CommandQueue) SysUiServiceProvider.getComponent(getContext(), CommandQueue.class)).addCallback(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        ((CommandQueue) SysUiServiceProvider.getComponent(getContext(), CommandQueue.class)).removeCallbacks(this);
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
        this.mValueAnimator = null;
        getContext().unregisterReceiver(this.mDialogReceiver);
        getContext().unregisterReceiver(this.mDayNightReceiver);
        this.mBydAutoPowerDevice.unregisterListener(this.mAbsBYDAutoPowerListener);
        this.mClockController.removeCallback(this.mClockCallbacks);
        this.mCarController.removeCallback(this.mCarControlCallback);
        this.mVolumeController.removeCallback(this.mVolumeControlCallback);
        this.mUsbController.removeCallback(this.mUsbCallback);
        this.mNetworkController.removeCallback((NetworkController.SignalCallback) this.mSignalCallback);
        this.mBluetoothController.removeCallback(this.mBluetoothCallback);
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(3);
        stopGetBright();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setWindowState(int window, int state) {
        Log.d(TAG, "setWindowState window = " + window + " , state = " + state);
    }

    @Override // com.android.systemui.statusbar.phone.StatusBarIconController.TopActivityChangeListener
    public void onTopActivityChanged(ComponentName componentName, int displayId) {
        if (displayId != 0) {
            BydLogHelper.logD("just handle displayId = 0, displayId = " + displayId);
            return;
        }
        boolean z = false;
        if (componentName != null) {
            BydLogHelper.logD(TAG, "onTopActivityChanged pkg:" + componentName.getPackageName() + ",clazz:" + componentName.getClassName());
            if (TextUtils.equals(Constants.SYS_CARD_ACTIVITY_MORE, componentName.getClassName()) || TextUtils.equals(Constants.SYS_CARD_ACTIVITY_ADD, componentName.getClassName()) || TextUtils.equals(Constants.SYS_CARD_ACTIVITY_ADD_POP, componentName.getClassName())) {
                BydLogHelper.logD(TAG, "card not handle");
                return;
            }
            if (TextUtils.equals(Constants.SYS_DIALOG_PERMISSION, componentName.getPackageName())) {
                BydLogHelper.logD(TAG, "system dialog not handle");
                return;
            }
            if ("com.android.car.media".equals(componentName.getPackageName()) || "com.amazon.mp3.automotiveOS".equals(componentName.getPackageName())) {
                this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$dzuNX-xoj_3WTmGmiocU9fhDXTE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTopActivityChanged$2$CollapsedStatusBarFragment();
                    }
                });
                return;
            }
            if (this.isAmazonMusicShow) {
                this.isAmazonMusicShow = false;
                this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$T3GvrJkbGUwaekis92A94My9PvU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTopActivityChanged$3$CollapsedStatusBarFragment();
                    }
                });
            }
            if ("com.android.launcher3".equals(componentName.getPackageName())) {
                int powerLevel = this.mCarController.getPowerLevel();
                BydLogHelper.logD("getPowerLevel level:" + powerLevel);
                if (powerLevel == 0) {
                    BydLogHelper.logW(TAG, "---acc off not handle---");
                    return;
                }
                z = true;
            }
        } else {
            BydLogHelper.logD(TAG, "onTopActivityChanged componentName is null");
        }
        BydLogHelper.logD("reverseShowIcon = " + z);
        if (z) {
            getScreenBrightness();
        } else {
            showDefaultIcon();
        }
    }

    public /* synthetic */ void lambda$onTopActivityChanged$2$CollapsedStatusBarFragment() {
        BydLogHelper.logD("onTopActivityChanged AMAZON");
        this.isAmazonMusicShow = true;
        this.mIsScreenBright = false;
        this.mReverseShowIcon = true;
        this.mStatusBar.setBackgroundColor(getResources().getColor(R.color.black));
        updateStatusBarColor();
    }

    public /* synthetic */ void lambda$onTopActivityChanged$3$CollapsedStatusBarFragment() {
        this.mStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent_00));
    }

    public void showDefaultStatusBarView() {
        BydLogHelper.logD("showDefaultStatusBarView");
        showDefaultIcon();
    }

    private void showDefaultIcon() {
        Log.d(TAG, "showDefaultIcon");
        this.mReverseShowIcon = false;
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessage(3);
        stopGetBright();
    }

    private class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.d(CollapsedStatusBarFragment.TAG, "handleMessage msg = " + msg.what);
            int i = msg.what;
            if (i == 1) {
                CollapsedStatusBarFragment.this.executeStatusBarTask();
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    CollapsedStatusBarFragment.this.updatePM25TextColor();
                    return;
                } else {
                    if (i != 5) {
                        return;
                    }
                    CollapsedStatusBarFragment.this.initStatusIcons();
                    return;
                }
            }
            CollapsedStatusBarFragment.this.updateStatusBarColor();
            if (CollapsedStatusBarFragment.this.isAppSetDayNight) {
                CollapsedStatusBarFragment.this.isAppSetDayNight = false;
                CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
                collapsedStatusBarFragment.updateStatusBarView(((Divider) SysUiServiceProvider.getComponent(collapsedStatusBarFragment.mContext, Divider.class)).isSplitActive());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class ClockCallbacks implements ClockController.Callback {
        private ClockCallbacks() {
        }

        /* synthetic */ ClockCallbacks(CollapsedStatusBarFragment collapsedStatusBarFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.android.systemui.statusbar.policy.ClockController.Callback
        public void onClockTime(final String time) {
            Log.d(CollapsedStatusBarFragment.TAG, "onClockTime = " + time);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$ClockCallbacks$mOnw64D2JrE2tPgpWWFB4oZ99l4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onClockTime$0$CollapsedStatusBarFragment$ClockCallbacks(time);
                }
            });
        }

        public /* synthetic */ void lambda$onClockTime$0$CollapsedStatusBarFragment$ClockCallbacks(String str) {
            CollapsedStatusBarFragment.this.updateTime(str);
        }

        @Override // com.android.systemui.statusbar.policy.ClockController.Callback
        public void onClockDate(String date) {
            Log.d(CollapsedStatusBarFragment.TAG, "onClockDate = " + date);
        }
    }

    protected final class CarControlCallback implements CarController.Callback {
        protected CarControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onISLIWarningLevelSwitchStatusChanged(final int status) {
            BydLogHelper.logD("onISLIWarningLevelSwitchStatusChanged status : " + status);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$o298jcg4J5Bf3Rb0bs32AJQlAr4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onISLIWarningLevelSwitchStatusChanged$0$CollapsedStatusBarFragment$CarControlCallback(status);
                }
            });
        }

        public /* synthetic */ void lambda$onISLIWarningLevelSwitchStatusChanged$0$CollapsedStatusBarFragment$CarControlCallback(int i) {
            CollapsedStatusBarFragment.this.mTSRState = i;
            CollapsedStatusBarFragment.this.updateTSR();
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void showCarModelChange(final int value) {
            BydLogHelper.logD("value:" + value);
            if (value == 0) {
                return;
            }
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$G4EWjHvRUlMhw6VIG_Dr4LavU4M
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$showCarModelChange$1$CollapsedStatusBarFragment$CarControlCallback(value);
                }
            });
        }

        public /* synthetic */ void lambda$showCarModelChange$1$CollapsedStatusBarFragment$CarControlCallback(int i) {
            CollapsedStatusBarFragment.this.mCarModeValue = i;
            CollapsedStatusBarFragment.this.updateShowCarMode();
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onChargingListenerConnected(final boolean isBinding) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$CO53hBsL-zPkmEPNscm7UhDWP3s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onChargingListenerConnected$2$CollapsedStatusBarFragment$CarControlCallback(isBinding);
                }
            });
        }

        public /* synthetic */ void lambda$onChargingListenerConnected$2$CollapsedStatusBarFragment$CarControlCallback(boolean z) {
            if (z) {
                CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
                collapsedStatusBarFragment.parseWirelessChargingRight(collapsedStatusBarFragment.mCarController.getWirelessChargingFRState(), false);
                CollapsedStatusBarFragment collapsedStatusBarFragment2 = CollapsedStatusBarFragment.this;
                collapsedStatusBarFragment2.parseWirelessChargingLeft(collapsedStatusBarFragment2.mCarController.getWirelessChargingStatus(), false);
            }
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onWirelessChargingStateChanged(final int status) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$WzJniXmP1RHHVHk-AH8qetjXnLY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onWirelessChargingStateChanged$3$CollapsedStatusBarFragment$CarControlCallback(status);
                }
            });
        }

        public /* synthetic */ void lambda$onWirelessChargingStateChanged$3$CollapsedStatusBarFragment$CarControlCallback(int i) {
            CollapsedStatusBarFragment.this.parseWirelessChargingLeft(i, true);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onWirelessChargingFRStateChanged(final int value) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$_T28WvAOcCt-3EMtc_67XQAOvug
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onWirelessChargingFRStateChanged$4$CollapsedStatusBarFragment$CarControlCallback(value);
                }
            });
        }

        public /* synthetic */ void lambda$onWirelessChargingFRStateChanged$4$CollapsedStatusBarFragment$CarControlCallback(int i) {
            CollapsedStatusBarFragment.this.parseWirelessChargingRight(i, true);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onPM2p5ValueChanged(int valueIn, int valueOut) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$iaV4NU8bQs0mQALaAvHitm_VQ40
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPM2p5ValueChanged$5$CollapsedStatusBarFragment$CarControlCallback();
                }
            });
        }

        public /* synthetic */ void lambda$onPM2p5ValueChanged$5$CollapsedStatusBarFragment$CarControlCallback() {
            CollapsedStatusBarFragment.this.initPmState();
            CollapsedStatusBarFragment.this.updatePM2p5();
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onPM2p5DualChannelDetectChanged(final int state) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$I28rXLUFMLe0WBRWwziWHL8tVNA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPM2p5DualChannelDetectChanged$6$CollapsedStatusBarFragment$CarControlCallback(state);
                }
            });
        }

        public /* synthetic */ void lambda$onPM2p5DualChannelDetectChanged$6$CollapsedStatusBarFragment$CarControlCallback(int i) {
            CollapsedStatusBarFragment.this.mPM2p5DualChannelDetectState = i;
            CollapsedStatusBarFragment.this.mPM2p5InState = i;
            CollapsedStatusBarFragment.this.mPM2p5OutState = i;
            CollapsedStatusBarFragment.this.updatePM2p5FromDetectChanged();
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onPM2p5CheckStateChanged(final int inState, final int outState) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$3bGSLV5u_T9E0VOnfOeG31l4ACg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPM2p5CheckStateChanged$7$CollapsedStatusBarFragment$CarControlCallback(inState, outState);
                }
            });
        }

        public /* synthetic */ void lambda$onPM2p5CheckStateChanged$7$CollapsedStatusBarFragment$CarControlCallback(int i, int i2) {
            CollapsedStatusBarFragment.this.mPM2p5DualChannelDetectState = 0;
            CollapsedStatusBarFragment.this.mPM2p5InState = i;
            CollapsedStatusBarFragment.this.mPM2p5OutState = i2;
            CollapsedStatusBarFragment.this.updatePM2p5();
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onUpdateFlag(final boolean flag) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$pzVrQEYZl2fU6d-8kSVGVOXjvN0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onUpdateFlag$8$CollapsedStatusBarFragment$CarControlCallback(flag);
                }
            });
        }

        public /* synthetic */ void lambda$onUpdateFlag$8$CollapsedStatusBarFragment$CarControlCallback(boolean z) {
            CollapsedStatusBarFragment.this.mOtaFlag = z;
            CollapsedStatusBarFragment.this.updateOta();
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onMusicInfoUrlChange(String musicName, String artistName, final int playState, final int musicSource, String imgUrl) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$Yredb9oT2yWeCUQOM4GNTW-6JkU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onMusicInfoUrlChange$9$CollapsedStatusBarFragment$CarControlCallback(playState, musicSource);
                }
            });
        }

        public /* synthetic */ void lambda$onMusicInfoUrlChange$9$CollapsedStatusBarFragment$CarControlCallback(int i, int i2) {
            BydLogHelper.logD("playState:" + i + ",musicSource:" + i2);
            CollapsedStatusBarFragment.this.updateMusicIcon(i, i2);
            SharedPreferencesUtil.putInt(CollapsedStatusBarFragment.this.mContext, CollapsedStatusBarFragment.this.musicType, i2);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onSessionStatusChangedOnAndroidAuto(final boolean connected) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$NPvTIToNs7wUrfzA6eEnKe6Q5ew
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSessionStatusChangedOnAndroidAuto$10$CollapsedStatusBarFragment$CarControlCallback(connected);
                }
            });
        }

        public /* synthetic */ void lambda$onSessionStatusChangedOnAndroidAuto$10$CollapsedStatusBarFragment$CarControlCallback(boolean z) {
            BydLogHelper.logD("connected:" + z);
            CollapsedStatusBarFragment.this.updateAndroidAutoIcon(z);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onSessionStatusChangedOnCarPlay(final boolean connected) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$7inw3RqwEcPSMG9iueOJlyxsrwE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSessionStatusChangedOnCarPlay$11$CollapsedStatusBarFragment$CarControlCallback(connected);
                }
            });
        }

        public /* synthetic */ void lambda$onSessionStatusChangedOnCarPlay$11$CollapsedStatusBarFragment$CarControlCallback(boolean z) {
            BydLogHelper.logD("connected:" + z);
            CollapsedStatusBarFragment.this.updateCarPlayIcon(z);
            int carPlaySessionStatusStatus = ((CarController) Dependency.get(CarController.class)).getCarPlaySessionStatusStatus();
            BydLogHelper.logD("status = " + carPlaySessionStatusStatus);
            if (carPlaySessionStatusStatus == 5) {
                ((Divider) SysUiServiceProvider.getComponent(CollapsedStatusBarFragment.this.mContext, Divider.class)).updateStatusBarCPAA(false);
            }
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onCameraUseChange(final String packageName, final boolean state) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$RO4fClGUExkpJJ0sWe4-QdQdJco
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCameraUseChange$12$CollapsedStatusBarFragment$CarControlCallback(packageName, state);
                }
            });
        }

        public /* synthetic */ void lambda$onCameraUseChange$12$CollapsedStatusBarFragment$CarControlCallback(String str, boolean z) {
            BydLogHelper.logD("packageName:" + str + ",state:" + z);
            CollapsedStatusBarFragment.this.parseCameraState(str, z);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onMicUseChange(final String packageName, final boolean state) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$CollapsedStatusBarFragment$CarControlCallback$N9VmYnDCT1XJl9XzewL8T1bMdBY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onMicUseChange$13$CollapsedStatusBarFragment$CarControlCallback(packageName, state);
                }
            });
        }

        public /* synthetic */ void lambda$onMicUseChange$13$CollapsedStatusBarFragment$CarControlCallback(String str, boolean z) {
            BydLogHelper.logD("packageName:" + str + ",state:" + z);
            CollapsedStatusBarFragment.this.parseMicShowState(str, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseCameraState(String packageName, boolean state) {
        if (TextUtils.isEmpty(packageName) || !state || packageName.contains("com.android.")) {
            this.mCameraShown = false;
            this.mStatusBarIconController.getStatusBean().setCameraOnSaved(2);
        } else {
            this.mCameraShown = true;
            this.mStatusBarIconController.getStatusBean().setCameraOnSaved(1);
        }
        updateCameraIcon();
    }

    private void updateCameraIcon() {
        ImageView imageView = this.mCameraView;
        if (imageView != null) {
            if (this.mCameraShown) {
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        imageView.setImageResource(R.mipmap.icon_status_bar_camera_light);
                    } else {
                        imageView.setImageResource(R.mipmap.icon_status_bar_camera_dark);
                    }
                } else {
                    imageView.setImageResource(R.mipmap.icon_status_bar_camera);
                }
                this.mCameraView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        this.mStatusListManager.setVisibility(9, this.mCameraShown);
        updateStatusIcons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseMicShowState(String packageName, boolean state) {
        if (TextUtils.isEmpty(packageName) || !state || TextUtils.equals(packageName, "com.byd.vrsettings") || packageName.contains("com.android.")) {
            this.mMicShown = false;
            this.mStatusBarIconController.getStatusBean().setMicOnSaved(2);
        } else {
            this.mMicShown = true;
            this.mStatusBarIconController.getStatusBean().setMicOnSaved(1);
        }
        updateMicIcon();
    }

    private void updateMicIcon() {
        ImageView imageView = this.mMicView;
        if (imageView != null) {
            if (this.mMicShown) {
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        imageView.setImageResource(R.mipmap.icon_status_bar_mic_light);
                    } else {
                        imageView.setImageResource(R.mipmap.icon_status_bar_mic_dark);
                    }
                } else {
                    imageView.setImageResource(R.mipmap.icon_status_bar_mic);
                }
                this.mMicView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        this.mStatusListManager.setVisibility(8, this.mMicShown);
        updateStatusIcons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMusicIcon(int playState, int musicSource) {
        BydLogHelper.logD("playState:" + playState + ",musicSource:" + musicSource + ",mCurrentMusicState:" + this.mCurrentMusicState);
        BydLogHelper.logD("mReverseShowIcon:" + this.mReverseShowIcon + ",mIsScreenBright:" + this.mIsScreenBright);
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.mMediaPlayCategoryTv.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
            } else {
                this.mMediaPlayCategoryTv.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            }
        } else {
            this.mMediaPlayCategoryTv.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        }
        if (playState == 0) {
            this.mCurrentMusicState = playState;
            this.mMediaPlayStatusImg.stopAnimation();
            this.mMediaPlayStatusImg.setVisibility(8);
            this.mMediaPlayDefImg.setVisibility(0);
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_music_light);
                } else {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_music_dark);
                }
            } else {
                this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_music);
            }
        } else if (playState == 1) {
            this.mCurrentMusicState = playState;
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mMediaPlayStatusImg.resetDrawable(R.drawable.media_playing_list_anim_light);
                } else {
                    this.mMediaPlayStatusImg.resetDrawable(R.drawable.media_playing_list_anim_dark);
                }
            } else {
                this.mMediaPlayStatusImg.resetDrawable(R.drawable.media_playing_list_anim);
            }
            this.mMediaPlayStatusImg.setVisibility(0);
            this.mMediaPlayStatusImg.startAnimation();
            this.mMediaPlayDefImg.setVisibility(8);
        } else if (playState == 2) {
            this.mCurrentMusicState = playState;
            this.mMediaPlayStatusImg.stopAnimation();
            this.mMediaPlayStatusImg.setVisibility(8);
            this.mMediaPlayDefImg.setVisibility(0);
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_play_light);
                } else {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_play_dark);
                }
            } else {
                this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_play);
            }
        }
        DividerUtils.getInstance().setMusicPlayState(playState);
        this.mMusicSource = musicSource;
        if (musicSource == 0) {
            this.mMediaLayout.setVisibility(8);
            return;
        }
        if (musicSource == 1) {
            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_music));
            return;
        }
        if (musicSource == 2 || musicSource == 3) {
            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_usb));
            return;
        }
        if (musicSource == 4) {
            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_sd_card));
            return;
        }
        if (musicSource == 6) {
            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_blue));
            return;
        }
        if (musicSource == 7) {
            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_fm));
            return;
        }
        if (musicSource != 49) {
            switch (musicSource) {
                case 16:
                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_spotify));
                    break;
                case 17:
                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_dab));
                    break;
                case 18:
                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_am));
                    break;
                default:
                    switch (musicSource) {
                        case 30:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_carplay));
                            break;
                        case 31:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_auto));
                            break;
                        case 32:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_amazon));
                            break;
                        case 33:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_flo));
                            break;
                        case 34:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_deezer));
                            break;
                        case 35:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_tidal));
                            break;
                        case 36:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_melon));
                            break;
                        case 37:
                            updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_tuneln_radio));
                            break;
                        default:
                            switch (musicSource) {
                                case 39:
                                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_pocket_casts));
                                    break;
                                case 40:
                                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_iheart_radio));
                                    break;
                                case 41:
                                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_audials_play));
                                    break;
                                case 42:
                                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_radioline));
                                    break;
                                case 43:
                                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_libby));
                                    break;
                                case 44:
                                    updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_audible));
                                    break;
                            }
                            break;
                    }
                    break;
            }
            return;
        }
        updateMultiMediaCategory(this.mContext.getString(R.string.media_pop_bloomberg));
    }

    private void updateMediaTextColor() {
        if (this.mReverseShowIcon) {
            if (this.mIsScreenBright) {
                this.mMediaPlayCategoryTv.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_light, null));
            } else {
                this.mMediaPlayCategoryTv.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time_dark, null));
            }
        } else {
            this.mMediaPlayCategoryTv.setTextColor(this.mContext.getResources().getColor(R.color.color_status_time, null));
        }
        int i = this.mCurrentMusicState;
        if (i == 0) {
            this.mMediaPlayStatusImg.stopAnimation();
            this.mMediaPlayStatusImg.setVisibility(8);
            this.mMediaPlayDefImg.setVisibility(0);
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_music_light);
                    return;
                } else {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_music_dark);
                    return;
                }
            }
            this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_music);
            return;
        }
        if (i == 1) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mMediaPlayStatusImg.resetDrawable(R.drawable.media_playing_list_anim_light);
                } else {
                    this.mMediaPlayStatusImg.resetDrawable(R.drawable.media_playing_list_anim_dark);
                }
            } else {
                this.mMediaPlayStatusImg.resetDrawable(R.drawable.media_playing_list_anim);
            }
            this.mMediaPlayStatusImg.setVisibility(0);
            this.mMediaPlayStatusImg.startAnimation();
            this.mMediaPlayDefImg.setVisibility(8);
            return;
        }
        if (i == 2) {
            this.mMediaPlayStatusImg.stopAnimation();
            this.mMediaPlayStatusImg.setVisibility(8);
            this.mMediaPlayDefImg.setVisibility(0);
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_play_light);
                    return;
                } else {
                    this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_play_dark);
                    return;
                }
            }
            this.mMediaPlayDefImg.setImageResource(R.mipmap.icon_media_play);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMuteIcon() {
        ImageView imageView = this.mMuteView;
        if (imageView != null) {
            if (this.mMuteShow) {
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        imageView.setImageResource(R.mipmap.icon_status_bar_mute_light);
                    } else {
                        imageView.setImageResource(R.mipmap.icon_status_bar_mute_dark);
                    }
                } else {
                    imageView.setImageResource(R.mipmap.icon_status_bar_mute);
                }
                this.mMuteView.setVisibility(0);
                return;
            }
            imageView.setVisibility(8);
        }
    }

    protected final class VolumeControlCallback implements VolumeController.Callbacks {
        protected VolumeControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void onStateChanged(boolean showUI, VolumeController.State state, boolean isFromMute, int flags) {
            boolean zEquals = Constants.SYS_BYD_BOOT_COMPLETED_TRUE.equals(SystemProperties.get(Constants.SYS_BYD_BOOT_COMPLETED, ""));
            Log.d(CollapsedStatusBarFragment.TAG, "isFirstBoot = " + zEquals);
            int i = 0;
            while (true) {
                if (i >= state.states.size()) {
                    break;
                }
                int iKeyAt = state.states.keyAt(i);
                VolumeController.StreamState streamState = state.states.get(iKeyAt);
                Log.d(CollapsedStatusBarFragment.TAG, "onStateChanged state = " + iKeyAt + " , muted = " + streamState.muted + " , level = " + streamState.level + " , levelMin = " + streamState.levelMin + " , levelMax = " + streamState.levelMax);
                if (iKeyAt == 3) {
                    if ((streamState.muted || streamState.level == streamState.levelMin) && zEquals) {
                        CollapsedStatusBarFragment.this.mMuteShow = true;
                    } else {
                        CollapsedStatusBarFragment.this.mMuteShow = false;
                    }
                    CollapsedStatusBarFragment.this.updateMuteIcon();
                } else {
                    i++;
                }
            }
            if (zEquals) {
                return;
            }
            Log.d(CollapsedStatusBarFragment.TAG, "set SYS_BYD_BOOT_COMPLETED");
            SystemProperties.set(Constants.SYS_BYD_BOOT_COMPLETED, Constants.SYS_BYD_BOOT_COMPLETED_TRUE);
        }
    }

    protected final class BluetoothCallback implements BluetoothController.Callback {
        @Override // com.android.systemui.statusbar.policy.BluetoothController.Callback
        public void onServiceConnectionChanged(boolean isConnected) {
        }

        protected BluetoothCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.BluetoothController.Callback
        public void onConnectedChanged(boolean isConnected, String address) {
            BydLogHelper.logD("device: " + StringUtil.desensitizeString(address, 0));
            if (isConnected) {
                CollapsedStatusBarFragment.this.mBTIsConnected = true;
            } else {
                CollapsedStatusBarFragment.this.mBTIsConnected = CollapsedStatusBarFragment.this.mBluetoothController.getConnectedDevice() != null;
            }
            CollapsedStatusBarFragment.this.updateBtConnected();
        }

        @Override // com.android.systemui.statusbar.policy.BluetoothController.Callback
        public void onEnableChanged(boolean enabled) {
            CollapsedStatusBarFragment.this.updateBtEnable(enabled);
        }

        @Override // com.android.systemui.statusbar.policy.BluetoothController.Callback
        public void onCallStateChanged(int state) {
            BydLogHelper.logD("state: " + state);
            if (state == 7) {
                CollapsedStatusBarFragment.this.getScreenBrightness();
            }
        }
    }

    private BluetoothDevice hasAnotherConnectedDevice() {
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            return null;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (bluetoothDevice.isConnected()) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    protected final class UsbCallback implements UsbController.Callback {
        protected UsbCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.UsbController.Callback
        public void onSdCardStateChanged(boolean isMounted) {
            CollapsedStatusBarFragment.this.mSDMounted = isMounted;
            CollapsedStatusBarFragment.this.updateSdCardMounted();
        }

        @Override // com.android.systemui.statusbar.policy.UsbController.Callback
        public void onUsbStateChanged(boolean isConnected) {
            CollapsedStatusBarFragment.this.mUSBMounted = isConnected;
            CollapsedStatusBarFragment.this.updateUSBConnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUSBConnected() {
        BydLogHelper.logD("mUSBMounted:" + this.mUSBMounted);
        if (this.mUSBMounted) {
            if (this.mReverseShowIcon) {
                if (this.mIsScreenBright) {
                    this.mUsbView.setImageResource(R.mipmap.icon_status_bar_usb_light);
                } else {
                    this.mUsbView.setImageResource(R.mipmap.icon_status_bar_usb_dark);
                }
            } else {
                this.mUsbView.setImageResource(R.mipmap.icon_status_bar_usb);
            }
            this.mUsbView.setVisibility(0);
        } else {
            this.mUsbView.setVisibility(8);
        }
        this.mStatusListManager.setVisibility(4, this.mUSBMounted);
        updateStatusIcons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSdCardMounted() {
        Log.d(TAG, "mSDMounted:" + this.mSDMounted);
        if (AppUtil.isDvrInstalled(this.mContext)) {
            if (!this.mSDMounted) {
                if (this.mReverseShowIcon) {
                    if (this.mIsScreenBright) {
                        this.mSdcardView.setImageResource(R.mipmap.icon_status_bar_sdcard_warm_light);
                    } else {
                        this.mSdcardView.setImageResource(R.mipmap.icon_status_bar_sdcard_warm_dark);
                    }
                } else {
                    this.mSdcardView.setImageResource(R.mipmap.icon_status_bar_sdcard_warm);
                }
                this.mSdcardView.setVisibility(0);
            } else {
                this.mSdcardView.setVisibility(8);
            }
            this.mStatusListManager.setVisibility(3, !this.mSDMounted);
            updateStatusIcons();
            return;
        }
        this.mSdcardView.setVisibility(8);
    }

    protected final class WifiSignalCallback implements NetworkController.SignalCallback {
        protected WifiSignalCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void setWifiIndicators(SignalController.State state, NetworkController.IconState statusIcon, String description, boolean isTransient, String statusLabel) {
            Log.d(CollapsedStatusBarFragment.TAG, "setWifiIndicators enabled=" + state.enabled + " , connected=" + state.connected + " , level=" + state.level + " , inetCondition=" + state.inetCondition + " , " + StringUtil.desensitizeString(description, 0) + " , " + isTransient + " , " + statusLabel);
            CollapsedStatusBarFragment.this.mWifiState = state;
            CollapsedStatusBarFragment.this.mStatusLabel = statusLabel;
            CollapsedStatusBarFragment.this.updateWifiSignal();
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void setTBoxIndicators(TBoxSignalController.TBoxSignalState state) {
            CollapsedStatusBarFragment.this.mTBoxState = state;
            CollapsedStatusBarFragment.this.updateTBoxSignal();
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void setMobileDataEnabled(boolean enabled) {
            if (enabled) {
                if (CollapsedStatusBarFragment.this.mNetworkController.isOnline()) {
                    if (CollapsedStatusBarFragment.this.mSysUiIconPhoneSignalOrientation != null) {
                        CollapsedStatusBarFragment.this.mSysUiIconPhoneSignalOrientation.setVisibility(0);
                        return;
                    }
                    return;
                } else {
                    if (CollapsedStatusBarFragment.this.mSysUiIconPhoneSignalOrientation != null) {
                        CollapsedStatusBarFragment.this.mSysUiIconPhoneSignalOrientation.setVisibility(8);
                        return;
                    }
                    return;
                }
            }
            if (CollapsedStatusBarFragment.this.mSysUiIconPhoneSignalOrientation != null) {
                CollapsedStatusBarFragment.this.mSysUiIconPhoneSignalOrientation.setVisibility(8);
            }
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void setTBoxNoneSignal(TBoxSignalController.TBoxSignalState state) {
            CollapsedStatusBarFragment.this.mTBoxState = state;
            CollapsedStatusBarFragment.this.updateTBoxSignal();
        }
    }

    public class QueryMsgTask extends AsyncTask<Void, Integer, Boolean> {
        public QueryMsgTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voids) {
            return Boolean.valueOf(CollapsedStatusBarFragment.this.queryMsg() == 1);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            CollapsedStatusBarFragment.this.updateMsgIcon(aBoolean.booleanValue());
        }
    }

    public class StatusBarAreaAnalyseTask extends AsyncTask<Void, Integer, Boolean> {
        public StatusBarAreaAnalyseTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voids) {
            Log.d(CollapsedStatusBarFragment.TAG, "StatusBarAreaAnalyseTask");
            Bitmap bitmapTakeScreenShot = ScreenShotUtil.takeScreenShot(CollapsedStatusBarFragment.this.mContext);
            if (bitmapTakeScreenShot == null) {
                Log.d(CollapsedStatusBarFragment.TAG, "StatusBarAreaAnalyseTask screenshot null");
                return null;
            }
            boolean z = true;
            Bitmap bitmapCopy = bitmapTakeScreenShot.copy(Bitmap.Config.ARGB_8888, true);
            CollapsedStatusBarFragment.this.recycleBitmap(bitmapTakeScreenShot);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCopy, 0, 0, bitmapCopy.getWidth(), Util.getStatusBarHeight(CollapsedStatusBarFragment.this.mContext));
            CollapsedStatusBarFragment.this.recycleBitmap(bitmapCopy);
            int bright = CollapsedStatusBarFragment.getBright(bitmapCreateBitmap);
            Log.d(CollapsedStatusBarFragment.TAG, "bright = " + bright);
            if (bright >= 128) {
                Log.d(CollapsedStatusBarFragment.TAG, "StatusBarAreaAnalyseTask bright");
            } else {
                Log.d(CollapsedStatusBarFragment.TAG, "StatusBarAreaAnalyseTask drak");
                z = false;
            }
            CollapsedStatusBarFragment.this.recycleBitmap(bitmapCreateBitmap);
            return Boolean.valueOf(z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean isBright) {
            super.onPostExecute(isBright);
            Log.d(CollapsedStatusBarFragment.TAG, "ignoreChange:" + CollapsedStatusBarFragment.this.ignoreChange);
            if (CollapsedStatusBarFragment.this.mStatusBarIconController == null || !CollapsedStatusBarFragment.this.mStatusBarIconController.needReverse() || CollapsedStatusBarFragment.this.ignoreChange || isBright == null) {
                return;
            }
            Log.d(CollapsedStatusBarFragment.TAG, "onPostExecute handle");
            CollapsedStatusBarFragment.this.mReverseShowIcon = true;
            CollapsedStatusBarFragment.this.mIsScreenBright = isBright.booleanValue();
            CollapsedStatusBarFragment.this.updateStatusBarColor();
            CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
            collapsedStatusBarFragment.updateStatusBarView(((Divider) SysUiServiceProvider.getComponent(collapsedStatusBarFragment.mContext, Divider.class)).isSplitActive());
        }
    }

    public void setStatusBarDayNight(boolean isDay) {
        BydLogHelper.logD("setStatusBarDayNight isDay = " + isDay);
        this.isAppSetDayNight = true;
        this.mIsScreenBright = isDay;
        this.mReverseShowIcon = true;
        updateStatusBarColor();
        this.mStatusBar.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), android.R.color.transparent, null));
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        addControllerCallback();
        int i = newConfig.uiMode;
        BydLogHelper.logD("onConfigurationChanged mUiMode = " + this.mUiMode + " newUiMode = " + i);
        if (this.mUiMode != i) {
            this.mUiMode = i;
            String topActivity = AppUtils.getTopActivity(this.mContext);
            if (!TextUtils.isEmpty(topActivity) && topActivity.startsWith("com.android.launcher3")) {
                getScreenBrightness(3);
            }
            StatusListDialog statusListDialog = this.mStatusListDialog;
            if (statusListDialog != null && statusListDialog.isShowing()) {
                this.mStatusListDialog.dismiss();
                showStatusListDialog();
            }
        } else {
            updateStatusListDialog(true);
        }
        boolean zIsRtl = AppUtils.isRtl(getContext());
        if (this.mIsRtl != zIsRtl) {
            this.mIsRtl = zIsRtl;
            updateLayout();
        }
        initData();
        BydLogHelper.logD("isAmazonMusicShow: " + this.isAmazonMusicShow);
        if (this.isAmazonMusicShow) {
            this.mStatusBar.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            updateStatusBarView(((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive());
        }
        this.tv_pm_in_option.setText(R.string.sys_ui_inside);
        this.tv_pm_out_option.setText(R.string.sys_ui_outside);
        CommonDialog commonDialog = this.mSLWDialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
            this.mSLWDialog = null;
        }
    }

    private void updateLayout() {
        BydLogHelper.logD("updateLayout");
        this.mStatusBar.removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.status_bar, (ViewGroup) this.mStatusBar, true);
        this.mStatusBar.invalidate();
        makeStatusBarView();
    }

    private void updateStatusListDialog(boolean isConfigurationChanged) {
        if (this.mStatusListDialog != null) {
            BydLogHelper.logD("updateStatusListDialog");
            this.mStatusListDialog.update(this.mStatusListManager.getList(), isConfigurationChanged);
        }
    }

    private void showStatusListDialog() {
        this.mContext.sendBroadcast(new Intent("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG"));
        this.mStatusListDialog = new StatusListDialog(this.mContext, this.mStatusListManager.getList());
        if (AppUtils.isRightRudder()) {
            this.mStatusListDialog.show(this.statusIcons.getLeft());
        } else {
            this.mStatusListDialog.show(this.statusIcons.getRight());
        }
    }

    public void dismissStatusListDialog() {
        StatusListDialog statusListDialog = this.mStatusListDialog;
        if (statusListDialog == null || !statusListDialog.isShowing()) {
            return;
        }
        this.mStatusListDialog.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatusBarChange(StatusBarChangeEvent event) {
        boolean z = event.isDrawerOpened;
        BydLogHelper.logD("onStatusBarChange isDrawerOpened = " + z);
        if (z) {
            return;
        }
        startGetBright(3);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDialogChange(DialogChangeEvent event) {
        BydLogHelper.logD("onStatusBarChange");
        if ("Bluetooth".equals(event.name) || "WiFi".equals(event.name) || Constants.CLOSE_SETTINGS_DIALOG.equals(event.name)) {
            dismissStatusListDialog();
        }
    }

    public void startGetBright(final int times) {
        BydLogHelper.logD("startGetBright");
        ScheduledExecutorService scheduledExecutorService = this.mScheduledExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        this.mTimes = 0;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        this.mScheduledExecutorService = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.8
            @Override // java.lang.Runnable
            public void run() {
                CollapsedStatusBarFragment.this.mTimes++;
                if (CollapsedStatusBarFragment.this.mTimes >= times) {
                    CollapsedStatusBarFragment.this.mScheduledExecutorService.shutdown();
                }
                Bitmap bitmapTakeScreenShot = ScreenShotUtil.takeScreenShot(CollapsedStatusBarFragment.this.mContext);
                if (bitmapTakeScreenShot != null) {
                    Bitmap bitmapCopy = bitmapTakeScreenShot.copy(Bitmap.Config.ARGB_8888, true);
                    CollapsedStatusBarFragment.this.recycleBitmap(bitmapTakeScreenShot);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCopy, 0, 0, bitmapCopy.getWidth(), Util.getStatusBarHeight(CollapsedStatusBarFragment.this.mContext));
                    CollapsedStatusBarFragment.this.recycleBitmap(bitmapCopy);
                    final int bright = CollapsedStatusBarFragment.getBright(bitmapCreateBitmap);
                    CollapsedStatusBarFragment.this.recycleBitmap(bitmapCreateBitmap);
                    BydLogHelper.logD("startGetBright bright = " + bright);
                    CollapsedStatusBarFragment.this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.CollapsedStatusBarFragment.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            boolean z = bright >= 128;
                            boolean zIsSplitActive = ((Divider) SysUiServiceProvider.getComponent(CollapsedStatusBarFragment.this.mContext, Divider.class)).isSplitActive();
                            if ((CollapsedStatusBarFragment.this.mStatusBarIconController == null || !CollapsedStatusBarFragment.this.mStatusBarIconController.needReverse() || CollapsedStatusBarFragment.this.ignoreChange) && !zIsSplitActive) {
                                return;
                            }
                            CollapsedStatusBarFragment.this.mReverseShowIcon = true;
                            CollapsedStatusBarFragment.this.mIsScreenBright = z;
                            CollapsedStatusBarFragment.this.updateStatusBarColor();
                            CollapsedStatusBarFragment.this.updateStatusBarView(zIsSplitActive);
                        }
                    });
                }
            }
        }, 500L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void stopGetBright() {
        BydLogHelper.logD("stopGetBright");
        ScheduledExecutorService scheduledExecutorService = this.mScheduledExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }
}
