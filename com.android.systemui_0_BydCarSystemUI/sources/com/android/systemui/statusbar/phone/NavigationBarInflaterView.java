package com.android.systemui.statusbar.phone;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserManager;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.recents.RecentsActivity;
import com.android.systemui.recents.event.RecentEvent;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.android.systemui.stackdivider.view.preview.SplitPreviewWindowManager;
import com.android.systemui.stackdivider.view.restart.RestartSplitScreenViewManager;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.HvacController;
import com.android.systemui.statusbar.wm.AirPanelController;
import com.android.systemui.statusbar.wm.GestureControl;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.DialogUtils;
import com.android.systemui.util.FastClickUtil;
import com.android.systemui.util.GuideUtils;
import com.android.systemui.util.InterestingConfigChanges;
import com.android.systemui.util.SharedPreferencesUtil;
import com.android.systemui.util.ShellUtils;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.util.SysUiTempUtils;
import com.android.systemui.volume.VolumeComponent;
import com.android.systemui.widget.BydImageView;
import com.android.systemui.widget.CommonDialog;
import com.android.systemui.widget.KeyButtonView;
import com.android.systemui.widget.OperationModeImg;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.floatcard.TopActivityManager;
import com.byd.card.utils.AppUtils;
import com.byd.card.widget.RollBenchMode;
import com.byd.profile.ToastManager;
import com.byd.systemsettings.BydSettingsApi;
import com.byd.systemsettings.callback.OnServiceDeathCallback;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBarInflaterView extends FrameLayout implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    private static final int ANTI_SHAKE_TIME = 500;
    private static final int HVAC_POP_CONTROL = 50;
    private static final int SPRING_BACK_TIME = 2000;
    private static final String TAG = "NavigationBarInflaterView";
    private final BydSettingsApi.OnServiceConnectCallback callback;
    public boolean changeDayNightMode;
    private SeekBar eaac_temp_seek_bar;
    private ImageView img_blur_bg;
    private OperationModeImg img_operation_mode;
    private ImageView img_ota_update;
    private BydImageView img_sysui_air_defrost;
    private BydImageView img_sysui_air_people_arrow_rw;
    private ImageView img_sysui_air_switch;
    private BydImageView img_sysui_air_wind_face;
    private BydImageView img_sysui_air_wind_foot;
    private BydImageView img_sysui_air_wind_level;
    private KeyButtonView img_sysui_app;
    private KeyButtonView img_sysui_back;
    private KeyButtonView img_sysui_car;
    private ImageView img_sysui_custom;
    private KeyButtonView img_sysui_home;
    private KeyButtonView img_sysui_orientation;
    private KeyButtonView img_sysui_screen_off;
    private KeyButtonView img_sysui_split;
    private boolean isHasMove;
    private BydImageView iv_left_unit;
    private ImageView iv_navigation_bar_set_temp_left_dec;
    private ImageView iv_navigation_bar_set_temp_left_inc;
    private ImageView iv_navigation_bar_set_temp_right_dec;
    private ImageView iv_navigation_bar_set_temp_right_inc;
    private BydImageView iv_right_unit;
    private RelativeLayout ll_air_center;
    private LinearLayout ll_center;
    private LinearLayout ll_hvac_disable_left;
    private LinearLayout ll_hvac_disable_right;
    private LinearLayout ll_left;
    private LinearLayout ll_right;
    private LinearLayout ll_single_zone_eaac;
    private LinearLayout ll_temp_left;
    private LinearLayout ll_temp_right;
    private int mAcType;
    private AirPanelController mAirPanelController;
    private final AirPanelControllerListener mAirPanelControllerListener;
    private int mAutoType;
    private final HvacCallbacks mCallbacks;
    private CarController mCarController;
    private final Context mContext;
    private int mCycleMode;
    private CommonDialog mDialog;
    private Divider mDivider;
    private final DividerUtils mDividerUtils;
    private int mFaultNumShownState;
    private boolean mFrontDefrost;
    private GestureControl mGestureControl;
    private int mHasMessage404;
    private HvacController mHvacController;
    private boolean mHvacPower;
    private boolean mIgnoreSplitScreenHelper;
    protected LayoutInflater mLandscapeInflater;
    private float mLastX;
    protected LayoutInflater mLayoutInflater;
    private int mLeftTemp;
    private final InterestingConfigChanges mLocaleConfigChanges;
    private NavigationBarInflaterViewHandler mNavigationBarInflaterViewHandler;
    private final OperationModeCallback mOperationModeCallback;
    private final InterestingConfigChanges mOrientationConfigChanges;
    private int mRightTemp;
    protected RelativeLayout mRot0;
    private final SplitScreenHelpManager mSplitScreenHelpManager;
    private Toast mToast;
    private final InterestingConfigChanges mUiModeConfigChanges;
    private int mUnit;
    private int mVentilationState;
    private int mWindLevel;
    private int mWindMode;
    private final float offset;
    private RelativeLayout rlLeftTemp;
    private RelativeLayout rlRightTemp;
    private RelativeLayout rl_auto_hvac_temp_left;
    private RelativeLayout rl_double_zone_right;
    private StatusBar statusBar;
    private TextView tvTempLeft;
    private TextView tvTempRight;
    private TextView tvWindLevel;
    private final int[] windMipmaps;
    private final int[] windMipmapsDenza;

    public NavigationBarInflaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.windMipmaps = new int[]{R.mipmap.icon_dock_air_volume_gear_bg, R.mipmap.icon_dock_air_volume_gear_1, R.mipmap.icon_dock_air_volume_gear_2, R.mipmap.icon_dock_air_volume_gear_3, R.mipmap.icon_dock_air_volume_gear_4, R.mipmap.icon_dock_air_volume_gear_5, R.mipmap.icon_dock_air_volume_gear_6, R.mipmap.icon_dock_air_volume_gear_7};
        this.windMipmapsDenza = new int[]{R.mipmap.denza_icon_dock_air_volume_gear_bg, R.mipmap.denza_icon_dock_air_volume_gear_1, R.mipmap.denza_icon_dock_air_volume_gear_2, R.mipmap.denza_icon_dock_air_volume_gear_3, R.mipmap.denza_icon_dock_air_volume_gear_4, R.mipmap.denza_icon_dock_air_volume_gear_5, R.mipmap.denza_icon_dock_air_volume_gear_6, R.mipmap.denza_icon_dock_air_volume_gear_7};
        this.mLastX = 0.0f;
        this.offset = 55.0f;
        this.isHasMove = false;
        this.mAcType = 1;
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges(512);
        this.mUiModeConfigChanges = interestingConfigChanges;
        InterestingConfigChanges interestingConfigChanges2 = new InterestingConfigChanges(128);
        this.mOrientationConfigChanges = interestingConfigChanges2;
        InterestingConfigChanges interestingConfigChanges3 = new InterestingConfigChanges(4);
        this.mLocaleConfigChanges = interestingConfigChanges3;
        this.mCallbacks = new HvacCallbacks();
        this.mAirPanelControllerListener = new AirPanelControllerListener();
        this.mOperationModeCallback = new OperationModeCallback();
        this.callback = new BydSettingsApi.OnServiceConnectCallback() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$2t4O756pif9sCeTtavh6vaHCbGY
            @Override // com.byd.systemsettings.BydSettingsApi.OnServiceConnectCallback
            public final void onServiceConnectChanged(boolean z) {
                BydLogHelper.logD("registerSystemSettingsCallback onServiceConnectChanged : " + z);
            }
        };
        this.mContext = context;
        createInflaters();
        this.mNavigationBarInflaterViewHandler = new NavigationBarInflaterViewHandler(Looper.getMainLooper());
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(context, Divider.class);
        this.mSplitScreenHelpManager = SplitScreenHelpManager.getInstance(context);
        this.mDividerUtils = DividerUtils.getInstance();
        interestingConfigChanges.applyNewConfig(context.getResources());
        interestingConfigChanges2.applyNewConfig(context.getResources());
        interestingConfigChanges3.applyNewConfig(context.getResources());
        RollBenchMode.getInstance(context).init();
    }

    public void onBootCompleted() {
        Log.e(TAG, "onBootCompleted");
        initOperationMode();
        this.mGestureControl = (GestureControl) Dependency.get(GestureControl.class);
        HvacController hvacController = (HvacController) Dependency.get(HvacController.class);
        this.mHvacController = hvacController;
        hvacController.addCallback(this.mCallbacks);
        AirPanelController airPanelController = (AirPanelController) Dependency.get(AirPanelController.class);
        this.mAirPanelController = airPanelController;
        airPanelController.addAirPanelControllerListener(this.mAirPanelControllerListener);
        this.mNavigationBarInflaterViewHandler = new NavigationBarInflaterViewHandler(Looper.getMainLooper());
        Divider divider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
        this.mDivider = divider;
        updateNaviBarBg(divider.isSplitActive());
        DeviceManager.getInstance().refreshHvacView();
        updateOtaView();
        this.mNavigationBarInflaterViewHandler.removeMessages(22);
        this.mNavigationBarInflaterViewHandler.sendEmptyMessage(22);
    }

    private void updateOtaView() {
        CarController carController = this.mCarController;
        if (carController != null) {
            updateOta(carController.getUpdateFlag());
        }
    }

    private void createInflaters() {
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
        Configuration configuration = new Configuration();
        configuration.setTo(this.mContext.getResources().getConfiguration());
        configuration.orientation = 1;
        this.mLandscapeInflater = LayoutInflater.from(this.mContext.createConfigurationContext(configuration));
    }

    public void setBydAppThemeEvent() {
        KeyButtonView keyButtonView = this.img_sysui_back;
        if (keyButtonView != null) {
            keyButtonView.setBydThemeView();
        }
        KeyButtonView keyButtonView2 = this.img_sysui_home;
        if (keyButtonView2 != null) {
            keyButtonView2.setBydThemeView();
        }
        KeyButtonView keyButtonView3 = this.img_sysui_car;
        if (keyButtonView3 != null) {
            keyButtonView3.setBydThemeView();
        }
        KeyButtonView keyButtonView4 = this.img_sysui_app;
        if (keyButtonView4 != null) {
            keyButtonView4.setBydThemeView();
        }
        KeyButtonView keyButtonView5 = this.img_sysui_split;
        if (keyButtonView5 != null) {
            keyButtonView5.setBydThemeView();
        }
        KeyButtonView keyButtonView6 = this.img_sysui_orientation;
        if (keyButtonView6 != null) {
            keyButtonView6.setBydThemeView();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        inflateChildren();
        inflateButtons();
        BydLogHelper.logD("onFinishInflate", "refreshView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOta(boolean flag) {
        BydLogHelper.logD("updateOta", " flag:" + flag);
        ImageView imageView = this.img_ota_update;
        if (imageView == null) {
            return;
        }
        if (flag) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    private void initOperationMode() {
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        carController.addCallback(this.mOperationModeCallback);
    }

    public void initAirPanel() {
        this.mAirPanelController.startUp();
    }

    private void inflateChildren() {
        removeAllViews();
        if (AppUtils.isRightRudder()) {
            this.mRot0 = (RelativeLayout) this.mLayoutInflater.inflate(StyleUtil.getResLayout(R.layout.navigation_layout_right_rudder, "navigation_layout_right_rudder"), (ViewGroup) this, false);
        } else {
            this.mRot0 = (RelativeLayout) this.mLayoutInflater.inflate(StyleUtil.getResLayout(R.layout.navigation_layout, "navigation_layout"), (ViewGroup) this, false);
        }
        this.mRot0.setId(R.id.horizontal);
        addView(this.mRot0);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        BydLogHelper.logD("onConfigurationChanged newConfig:" + newConfig);
        CommonDialog commonDialog = this.mDialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
            this.mDialog = null;
        }
        DialogUtils.release();
        AirPanelController airPanelController = this.mAirPanelController;
        if (airPanelController != null) {
            airPanelController.release();
        }
        inflateChildren();
        inflateButtons();
        updateNaviBarBg(this.mDivider.isSplitActive());
        checkUpdateSplitHelpView();
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).getLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$v_BWlsQqyo71KFx85HtQBcpSigc
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return this.f$0.lambda$onConfigurationChanged$0$NavigationBarInflaterView(newConfig);
            }
        });
    }

    public /* synthetic */ boolean lambda$onConfigurationChanged$0$NavigationBarInflaterView(Configuration configuration) {
        updateDock(configuration);
        return false;
    }

    private void checkUpdateSplitHelpView() {
        boolean zIsShowHelpActivity = this.mSplitScreenHelpManager.isShowHelpActivity();
        BydLogHelper.logD("checkUpdateSplitHelpView mIgnoreSplitScreenHelper = " + this.mIgnoreSplitScreenHelper + " isShowingSplitHelp = " + zIsShowHelpActivity);
        if (this.mIgnoreSplitScreenHelper || !zIsShowHelpActivity) {
            return;
        }
        if (this.mOrientationConfigChanges.applyNewConfig(this.mContext.getResources()) || this.mUiModeConfigChanges.applyNewConfig(this.mContext.getResources())) {
            this.mSplitScreenHelpManager.onConfigurationChanged();
        }
    }

    private void updateDock(Configuration newConfig) {
        if (DeviceManager.getInstance().isConnected()) {
            BydLogHelper.logD("onConfigurationChanged", "mAcType = " + this.mAcType);
            updateHvacType(this.mAcType);
            int currCustomType = this.mAirPanelController.getCurrCustomType();
            BydLogHelper.logD("onConfigurationChanged", "currCustomType = " + currCustomType);
            updateCustom(currCustomType);
            BydLogHelper.logD("onConfigurationChanged", "mUnit = " + this.mUnit);
            updateUnit(this.mUnit);
            BydLogHelper.logD("onConfigurationChanged", "mWindMode = " + this.mWindMode);
            updateWindMode(this.mWindMode);
            BydLogHelper.logD("onConfigurationChanged", "mWindLevel : " + this.mWindLevel);
            updateWindLevel(this.mWindLevel);
            BydLogHelper.logD("onConfigurationChanged", "mFaultNumShownState = " + this.mFaultNumShownState);
            updateFaultNumShownState(this.mFaultNumShownState);
            BydLogHelper.logD("onConfigurationChanged", "mLeftTemp = " + this.mLeftTemp + " , mRightTemp = " + this.mRightTemp);
            updateLeftTemp(this.mLeftTemp);
            updateRightTemp(this.mRightTemp);
            BydLogHelper.logD("onConfigurationChanged", "mHvacPower = " + this.mHvacPower);
            updateHvacPower(this.mHvacPower);
            BydLogHelper.logD("onConfigurationChanged", "mHasMessage404 = " + this.mHasMessage404);
            updateHasMessage(this.mHasMessage404 == 1);
        }
        updateOtaView();
        if (this.mUiModeConfigChanges.applyNewConfig(this.mContext.getResources())) {
            BydLogHelper.logD("onConfigurationChanged new uiMode:" + this.mContext.getResources().getConfiguration().uiMode);
            RestartSplitScreenViewManager.getInstance().onUiModeConfigChange();
            reShowHvacPopupWindow();
            ComponentName currentTopActivity = TopActivityManager.getInstance(this.mContext).getCurrentTopActivity();
            if (currentTopActivity != null) {
                if (currentTopActivity.getPackageName().equals("com.ts.androidauto.app") || currentTopActivity.getPackageName().equals("com.ts.carplay.app")) {
                    this.mDivider.updateStatusBarCPAA(true);
                    return;
                }
                return;
            }
            return;
        }
        if (this.mOrientationConfigChanges.applyNewConfig(this.mContext.getResources())) {
            BydLogHelper.logD("onConfigurationChanged new orientation:" + this.mContext.getResources().getConfiguration().orientation);
            RestartSplitScreenViewManager.getInstance().onOrientationChanged(newConfig);
            reShowHvacPopupWindow();
        } else if (this.mLocaleConfigChanges.applyNewConfig(this.mContext.getResources())) {
            BydLogHelper.logD("onConfigurationChanged new language:" + this.mContext.getResources().getConfiguration().getLocales().get(0).getLanguage());
            reShowHvacPopupWindow();
        }
    }

    private void reShowHvacPopupWindow() {
        BydLogHelper.logD("reShowHvacPopupWindow mAirPanelController:" + this.mAirPanelController);
        AirPanelController airPanelController = this.mAirPanelController;
        if (airPanelController == null) {
            return;
        }
        boolean zIsShowPopupWindow = airPanelController.isShowPopupWindow();
        BydLogHelper.logD("reShowHvacPopupWindow isShowPopupWindow:" + zIsShowPopupWindow);
        this.mAirPanelController.destroyView();
        if (zIsShowPopupWindow) {
            this.mNavigationBarInflaterViewHandler.removeMessages(21);
            this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(21, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreHvacPopWindowState() {
        int lastPopupWindow = this.mAirPanelController.getLastPopupWindow();
        BydLogHelper.logD("restoreHvacPopWindowState currPopupWindow:" + lastPopupWindow);
        int i = this.mContext.getResources().getConfiguration().orientation;
        BydLogHelper.logD("restoreHvacPopWindowState orientation:" + i);
        if (lastPopupWindow == 1) {
            this.mAirPanelController.showPopTemp(this.ll_temp_left, true);
            return;
        }
        if (lastPopupWindow == 2) {
            this.mAirPanelController.showPopTemp(this.ll_temp_right, false);
            return;
        }
        if (lastPopupWindow == 3) {
            int i2 = this.mAcType;
            if (i2 == 2) {
                this.mAirPanelController.showWindLevel(this.ll_temp_right);
                return;
            } else {
                if (i2 == 1) {
                    this.mAirPanelController.showWindLevel(this.ll_air_center);
                    return;
                }
                return;
            }
        }
        if (lastPopupWindow == 4 && i == 2) {
            this.mAirPanelController.showPopCustomView(this.img_sysui_custom);
        } else if (lastPopupWindow == 5) {
            this.mAirPanelController.showEAACPopTemp(this.ll_temp_left);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BydLogHelper.logE("onAttachedToWindow");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydLogHelper.logE("onDetachedFromWindow");
        this.mHvacController.removeCallback(this.mCallbacks);
        this.mCarController.removeCallback(this.mOperationModeCallback);
        this.mAirPanelController.removeListener();
        this.mAirPanelController.destroyView();
        DialogUtils.release();
        AirPanelController airPanelController = this.mAirPanelController;
        if (airPanelController != null) {
            airPanelController.release();
        }
    }

    private void inflateButtons() {
        this.img_blur_bg = (ImageView) this.mRot0.findViewById(R.id.img_blur_bg);
        this.img_sysui_back = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_back);
        this.img_sysui_home = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_home);
        this.img_sysui_car = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_car);
        this.img_ota_update = (ImageView) this.mRot0.findViewById(R.id.img_ota_update);
        BydImageView bydImageView = (BydImageView) this.mRot0.findViewById(R.id.img_sysui_air_wind_level);
        this.img_sysui_air_wind_level = bydImageView;
        bydImageView.setVisibility(this.mAcType == 2 ? 8 : 0);
        this.img_sysui_air_wind_foot = (BydImageView) this.mRot0.findViewById(R.id.img_sysui_air_wind_foot);
        this.img_sysui_air_wind_face = (BydImageView) this.mRot0.findViewById(R.id.img_sysui_air_wind_face);
        this.img_sysui_air_defrost = (BydImageView) this.mRot0.findViewById(R.id.img_sysui_air_defrost);
        this.img_sysui_air_people_arrow_rw = (BydImageView) this.mRot0.findViewById(R.id.img_sysui_air_people_arrow_rw);
        this.iv_navigation_bar_set_temp_left_dec = (ImageView) this.mRot0.findViewById(R.id.iv_navigation_bar_set_temp_left_dec);
        this.iv_navigation_bar_set_temp_left_inc = (ImageView) this.mRot0.findViewById(R.id.iv_navigation_bar_set_temp_left_inc);
        this.iv_navigation_bar_set_temp_right_dec = (ImageView) this.mRot0.findViewById(R.id.iv_navigation_bar_set_temp_right_dec);
        this.iv_navigation_bar_set_temp_right_inc = (ImageView) this.mRot0.findViewById(R.id.iv_navigation_bar_set_temp_right_inc);
        this.img_blur_bg.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$2Bsi21--PAYV_Rrl-z6pFsGurM0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.onTouch(view, motionEvent);
            }
        });
        this.iv_navigation_bar_set_temp_left_dec.setOnClickListener(this);
        this.iv_navigation_bar_set_temp_left_inc.setOnClickListener(this);
        this.iv_navigation_bar_set_temp_right_dec.setOnClickListener(this);
        this.iv_navigation_bar_set_temp_right_inc.setOnClickListener(this);
        this.img_sysui_app = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_app);
        this.img_sysui_split = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_split);
        this.img_sysui_orientation = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_orientation);
        this.img_sysui_screen_off = (KeyButtonView) this.mRot0.findViewById(R.id.img_sysui_screen_off);
        this.ll_left = (LinearLayout) this.mRot0.findViewById(R.id.ll_left);
        this.ll_right = (LinearLayout) this.mRot0.findViewById(R.id.ll_right);
        this.ll_center = (LinearLayout) this.mRot0.findViewById(R.id.ll_center);
        this.img_operation_mode = (OperationModeImg) this.mRot0.findViewById(R.id.img_operation_mode);
        this.img_sysui_back.setOnClickListener(this);
        this.img_sysui_home.setOnClickListener(this);
        this.img_sysui_home.setOnLongClickListener(this);
        this.img_sysui_car.setOnClickListener(this);
        this.img_sysui_app.setOnClickListener(this);
        this.img_sysui_split.setOnClickListener(this);
        this.img_sysui_split.setOnLongClickListener(this);
        this.img_sysui_orientation.setOnClickListener(this);
        if (this.img_sysui_screen_off != null) {
            int i = SystemProperties.getInt(Constants.BOSCH_CONFIG_AREA, 49);
            BydLogHelper.logD("countryName = " + i);
            if (StyleUtil.isDenza() || ((i == 102 && AppUtil.isUKE(this.mContext)) || AppUtil.isMC(this.mContext))) {
                this.img_sysui_screen_off.setVisibility(0);
                this.img_sysui_orientation.setVisibility(8);
                this.img_sysui_screen_off.setOnClickListener(this);
            }
        }
        updateNaviBarIcon();
        this.tvTempLeft = (TextView) this.mRot0.findViewById(R.id.tv_temp_left);
        this.tvTempRight = (TextView) this.mRot0.findViewById(R.id.tv_temp_right);
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.mContext.getAssets(), "font/HFMoBestSans-R.otf");
        this.tvTempRight.setTypeface(typefaceCreateFromAsset);
        this.tvTempLeft.setTypeface(typefaceCreateFromAsset);
        TextView textView = (TextView) this.mRot0.findViewById(R.id.tv_wind_level);
        this.tvWindLevel = textView;
        textView.setTextColor(getResources().getColor(R.color.color_dock_wind_level_text, null));
        this.iv_left_unit = (BydImageView) this.mRot0.findViewById(R.id.iv_left_unit);
        this.iv_right_unit = (BydImageView) this.mRot0.findViewById(R.id.iv_right_unit);
        this.ll_temp_left = (LinearLayout) this.mRot0.findViewById(R.id.ll_temp_left);
        this.ll_temp_right = (LinearLayout) this.mRot0.findViewById(R.id.ll_temp_right);
        RelativeLayout relativeLayout = (RelativeLayout) this.mRot0.findViewById(R.id.ll_air_center);
        this.ll_air_center = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.ll_air_center.setOnTouchListener(this);
        this.rl_double_zone_right = (RelativeLayout) this.mRot0.findViewById(R.id.rl_double_zone_right);
        this.rl_auto_hvac_temp_left = (RelativeLayout) this.mRot0.findViewById(R.id.rl_auto_hvac_temp_left);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.mRot0.findViewById(R.id.rlLeftTemp);
        this.rlLeftTemp = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$2Bsi21--PAYV_Rrl-z6pFsGurM0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.onTouch(view, motionEvent);
            }
        });
        RelativeLayout relativeLayout3 = (RelativeLayout) this.mRot0.findViewById(R.id.rlRightTemp);
        this.rlRightTemp = relativeLayout3;
        relativeLayout3.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$2Bsi21--PAYV_Rrl-z6pFsGurM0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.onTouch(view, motionEvent);
            }
        });
        this.ll_single_zone_eaac = (LinearLayout) this.mRot0.findViewById(R.id.ll_single_zone_eaac);
        SeekBar seekBar = (SeekBar) this.mRot0.findViewById(R.id.eaac_temp_seek_bar);
        this.eaac_temp_seek_bar = seekBar;
        seekBar.setEnabled(false);
        this.ll_hvac_disable_left = (LinearLayout) this.mRot0.findViewById(R.id.ll_hvac_disable_left);
        this.ll_hvac_disable_right = (LinearLayout) this.mRot0.findViewById(R.id.ll_hvac_disable_right);
        this.img_sysui_air_switch = (ImageView) this.mRot0.findViewById(R.id.img_sysui_air_switch);
        this.img_sysui_custom = (ImageView) this.mRot0.findViewById(R.id.img_sysui_custom);
        this.img_sysui_air_switch.setOnClickListener(this);
        this.img_sysui_custom.setOnClickListener(this);
        this.img_sysui_custom.setOnLongClickListener(this);
        inflateDockAc();
        boolean zIsConnected = DeviceManager.getInstance().isConnected();
        BydLogHelper.logD("inflateButtons", "isConnected = " + zIsConnected);
        if (zIsConnected) {
            return;
        }
        updateHasMessage(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNaviBarIcon() {
        if (this.img_sysui_screen_off != null) {
            int i = SystemProperties.getInt(Constants.BOSCH_CONFIG_AREA, 49);
            BydLogHelper.logD("countryName = " + i);
            if (StyleUtil.isDenza() || ((i == 102 && AppUtil.isUKE(this.mContext)) || AppUtil.isMC(this.mContext))) {
                this.img_sysui_screen_off.setVisibility(0);
                this.img_sysui_orientation.setVisibility(8);
                this.img_sysui_screen_off.setOnClickListener(this);
            }
        }
    }

    public void inflateDockAc() {
        CarController carController;
        CarController carController2;
        BydLogHelper.logD("inflateButtons mAirPanelController = " + this.mAirPanelController);
        if (this.mAirPanelController != null && (carController2 = this.mCarController) != null) {
            this.mAutoType = carController2.getAutoType();
            updateCustom(this.mAirPanelController.getCurrCustomType(), false);
        } else {
            this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.navi_bar_bitmap_custom_defrost));
            this.img_sysui_custom.setSelected(false);
        }
        BydLogHelper.logD("inflateButtons mHvacController = " + this.mHvacController);
        if (this.mHvacController != null && (carController = this.mCarController) != null) {
            if (carController.hasMessage(59) == 1) {
                updateUnit(this.mHvacController.getUnit());
                updateHasMessage(this.mHasMessage404 == 1);
                return;
            } else {
                this.ll_hvac_disable_left.setVisibility(0);
                this.ll_hvac_disable_right.setVisibility(0);
                this.rl_auto_hvac_temp_left.setVisibility(8);
                this.rl_double_zone_right.setVisibility(8);
                return;
            }
        }
        this.iv_left_unit.setImageResource(R.mipmap.img_gesture_ac_temperature_unit);
        this.iv_right_unit.setImageResource(R.mipmap.img_gesture_ac_temperature_unit);
        this.img_sysui_air_switch.setSelected(false);
        if (StyleUtil.isDenza()) {
            this.img_sysui_air_wind_level.setImageResource(this.windMipmapsDenza[0]);
        } else {
            this.img_sysui_air_wind_level.setImageResource(this.windMipmaps[0]);
        }
    }

    public void initAcStatus() {
        int acType = this.mHvacController.getAcType();
        this.mAcType = acType;
        updateHvacType(acType);
        this.mFrontDefrost = this.mHvacController.isFrontDefrostEnabled();
        this.mVentilationState = this.mHvacController.getAcVentilationState();
        this.mCycleMode = this.mHvacController.getAcCycleMode();
        int currCustomType = this.mAirPanelController.getCurrCustomType();
        this.mAutoType = this.mCarController.getAutoType();
        updateCustom(currCustomType);
        BydLogHelper.logW(TAG, "initAcStatus", "mAcType = " + this.mAcType + " --- mFrontDefrost = " + this.mFrontDefrost + " --- mVentilationState = " + this.mVentilationState + " --- mCycleMode = " + this.mCycleMode + " --- currCustomType = " + currCustomType + " --- mAutoType = " + this.mAutoType);
        int unit = this.mHvacController.getUnit();
        this.mUnit = unit;
        updateUnit(unit);
        int acWindMode = this.mHvacController.getAcWindMode();
        this.mWindMode = acWindMode;
        updateWindMode(acWindMode);
        int acWindLevel = this.mHvacController.getAcWindLevel();
        this.mWindLevel = acWindLevel;
        updateWindLevel(acWindLevel);
        int acFaultNumShownState = this.mHvacController.getAcFaultNumShownState();
        this.mFaultNumShownState = acFaultNumShownState;
        updateFaultNumShownState(acFaultNumShownState);
        this.mLeftTemp = this.mHvacController.getFrontLeftTempValue();
        this.mRightTemp = this.mHvacController.getFrontRightTempValue();
        updateLeftTemp(this.mLeftTemp);
        updateRightTemp(this.mRightTemp);
        boolean zIsHvacPower = this.mHvacController.isHvacPower();
        this.mHvacPower = zIsHvacPower;
        updateHvacPower(zIsHvacPower);
        int iHasMessage = this.mCarController.hasMessage(59);
        this.mHasMessage404 = iHasMessage;
        updateHasMessage(iHasMessage == 1);
        BydLogHelper.logW(TAG, "initAcStatus", "mUnit = " + this.mUnit + " --- mWindMode = " + this.mWindMode + " --- mWindLevel = " + this.mWindLevel + " --- mFaultNumShownState = " + this.mFaultNumShownState + " --- mLeftTemp = " + this.mLeftTemp + " --- mRightTemp = " + this.mRightTemp + " --- mHvacPower = " + this.mHvacPower + " --- mHasMessage404 = " + this.mHasMessage404);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLeftTemp(int value) {
        String string;
        BydLogHelper.logD("updateLeftTemp mAcType : " + this.mAcType);
        int i = this.mAcType;
        if (i != 1) {
            if (i == 2) {
                updateEaacTemp(value);
                return;
            }
            return;
        }
        int acFaultNumShownState = this.mHvacController.getAcFaultNumShownState();
        BydLogHelper.logD("updateLeftTemp value = " + value + " , mFaultNumShownState : " + acFaultNumShownState);
        if (acFaultNumShownState == 2) {
            this.tvTempLeft.setText(Integer.toHexString(value).toUpperCase(Locale.ROOT));
            this.tvTempLeft.setTextColor(this.mContext.getResources().getColor(R.color.color_dock_hvac_temp_text, null));
            this.iv_left_unit.setVisibility(8);
            return;
        }
        if (acFaultNumShownState == 1) {
            BydLogHelper.logD("updateLeftTemp", "updateLeftTemp unit = " + this.mHvacController.getUnit());
            int[] tempsKey = SysUiTempUtils.getInstance().getTempsKey(this.mUnit);
            int i2 = tempsKey[0];
            int i3 = tempsKey[tempsKey.length - 1];
            resetTempViewTextSize(this.tvTempLeft);
            BydLogHelper.logD("updateLeftTemp", "updateLeftTemp max = " + i3 + " -- min = " + i2);
            if (value == i3) {
                string = getResources().getString(R.string.sys_ui_temp_hi);
                this.iv_left_unit.setVisibility(8);
                this.tvTempLeft.setTextColor(getResources().getColor(R.color.color_temp_hi, null));
            } else if (value == i2) {
                string = getResources().getString(R.string.sys_ui_temp_low);
                this.iv_left_unit.setVisibility(8);
                this.tvTempLeft.setTextColor(getResources().getColor(R.color.color_temp_lo, null));
            } else {
                string = SysUiTempUtils.getInstance().getTemps(this.mUnit)[this.mAirPanelController.getIndexByTemp(value)];
                BydLogHelper.logD("updateLeftTemp  tempValue = " + string);
                this.iv_left_unit.setVisibility(0);
                this.tvTempLeft.setTextColor(getResources().getColor(R.color.color_dock_hvac_temp_text, null));
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.tvTempLeft.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.iv_left_unit.getLayoutParams();
            if (string != null && string.contains(Constants.CHAR_DOT)) {
                SpannableString spannableString = new SpannableString(string);
                spannableString.setSpan(new RelativeSizeSpan(0.7f), string.indexOf(Constants.CHAR_DOT), string.indexOf(Constants.CHAR_DOT) + 2, 33);
                this.tvTempLeft.setText(spannableString);
                if (StyleUtil.isDenza()) {
                    marginLayoutParams.setMarginStart(0);
                    marginLayoutParams2.setMarginEnd(0);
                    this.tvTempLeft.setLayoutParams(marginLayoutParams);
                    this.tvTempLeft.setGravity(17);
                    this.iv_left_unit.setLayoutParams(marginLayoutParams2);
                    return;
                }
                return;
            }
            this.tvTempLeft.setText(string);
            if (StyleUtil.isDenza()) {
                marginLayoutParams.setMarginStart(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_4));
                marginLayoutParams2.setMarginEnd(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_2));
                this.tvTempLeft.setLayoutParams(marginLayoutParams);
                this.tvTempLeft.setGravity(17);
                this.iv_left_unit.setLayoutParams(marginLayoutParams2);
            }
        }
    }

    private void resetTempViewTextSize(TextView view) {
        CarController carController;
        if (view == null || (carController = this.mCarController) == null) {
            return;
        }
        if (carController.getTempRegulatingAccuracy() == 2) {
            view.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.sp_25));
        } else {
            view.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.sp_30));
        }
    }

    private void updateEaacTemp(int value) {
        BydLogHelper.logD("updateEaacTemp value : " + value);
        if (value >= 17 && value <= 33) {
            this.eaac_temp_seek_bar.setProgress(value);
            this.eaac_temp_seek_bar.setThumbTintList(ColorStateList.valueOf(getContext().getColor(Constants.TEMP_SCALE_COLOR[value - 17])));
        } else {
            BydLogHelper.logD("updateEaacTemp err");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRightTemp(int value) {
        String string;
        BydLogHelper.logD("updateRightTemp value = " + value);
        int acFaultNumShownState = this.mHvacController.getAcFaultNumShownState();
        BydLogHelper.logD("updateRightTemp value = " + value + " , mFaultNumShownState : " + acFaultNumShownState);
        if (acFaultNumShownState == 2) {
            this.tvTempRight.setText(Integer.toHexString(value).toUpperCase(Locale.ROOT));
            this.tvTempRight.setTextColor(this.mContext.getResources().getColor(R.color.color_dock_hvac_temp_text, null));
            this.iv_right_unit.setVisibility(8);
            return;
        }
        if (acFaultNumShownState == 1) {
            BydLogHelper.logD("updateRightTemp", "updateRightTemp unit = " + this.mHvacController.getUnit());
            int[] tempsKey = SysUiTempUtils.getInstance().getTempsKey(this.mUnit);
            int i = tempsKey[0];
            int i2 = tempsKey[tempsKey.length - 1];
            BydLogHelper.logD("updateRightTemp", "updateRightTemp max = " + i2 + " -- min = " + i);
            resetTempViewTextSize(this.tvTempRight);
            if (value == i2) {
                string = getResources().getString(R.string.sys_ui_temp_hi);
                this.iv_right_unit.setVisibility(8);
                this.tvTempRight.setTextColor(getResources().getColor(R.color.color_temp_hi, null));
            } else if (value == i) {
                string = getResources().getString(R.string.sys_ui_temp_low);
                this.iv_right_unit.setVisibility(8);
                this.tvTempRight.setTextColor(getResources().getColor(R.color.color_temp_lo, null));
            } else {
                string = SysUiTempUtils.getInstance().getTemps(this.mUnit)[this.mAirPanelController.getIndexByTemp(value)];
                BydLogHelper.logD("updateRightTemp  tempValue = " + string);
                this.iv_right_unit.setVisibility(0);
                this.tvTempRight.setTextColor(getResources().getColor(R.color.color_dock_hvac_temp_text, null));
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.tvTempRight.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.iv_right_unit.getLayoutParams();
            if (string != null && string.contains(Constants.CHAR_DOT)) {
                SpannableString spannableString = new SpannableString(string);
                spannableString.setSpan(new RelativeSizeSpan(0.7f), string.indexOf(Constants.CHAR_DOT), string.indexOf(Constants.CHAR_DOT) + 2, 33);
                this.tvTempRight.setText(spannableString);
                if (StyleUtil.isDenza()) {
                    marginLayoutParams.setMarginStart(0);
                    marginLayoutParams2.setMarginEnd(0);
                    this.tvTempRight.setLayoutParams(marginLayoutParams);
                    this.tvTempRight.setGravity(17);
                    this.iv_right_unit.setLayoutParams(marginLayoutParams2);
                    return;
                }
                return;
            }
            this.tvTempRight.setText(string);
            if (StyleUtil.isDenza()) {
                marginLayoutParams.setMarginStart(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_4));
                marginLayoutParams2.setMarginEnd(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_2));
                this.tvTempRight.setLayoutParams(marginLayoutParams);
                this.iv_right_unit.setLayoutParams(marginLayoutParams2);
                this.tvTempRight.setGravity(17);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWindLevel(int windLevel) {
        BydLogHelper.logD("windLevel = " + windLevel);
        if (this.mHasMessage404 == 1 && windLevel < this.windMipmaps.length) {
            BydLogHelper.logE("updateWindLevel isDenza = " + StyleUtil.isDenza());
            if (StyleUtil.isDenza()) {
                this.img_sysui_air_wind_level.setImageResource(this.windMipmapsDenza[windLevel]);
            } else {
                this.img_sysui_air_wind_level.setImageResource(this.windMipmaps[windLevel]);
            }
            this.tvWindLevel.setText(String.valueOf(windLevel));
            if (windLevel == 0) {
                this.img_sysui_air_wind_level.setVisibility(8);
            } else {
                this.img_sysui_air_wind_level.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHasMessage(boolean enable) {
        BydLogHelper.logD("updateHasMessage", "enable = " + enable);
        this.ll_temp_left.setEnabled(enable);
        this.ll_temp_right.setEnabled(enable);
        this.rlLeftTemp.setEnabled(enable);
        this.rlRightTemp.setEnabled(enable);
        this.ll_hvac_disable_left.setVisibility(enable ? 8 : 0);
        this.ll_hvac_disable_right.setVisibility(enable ? 8 : 0);
        BydLogHelper.logD("updateHasMessage", "mAcType = " + this.mAcType);
        int i = this.mAcType;
        if (i == 1) {
            this.rl_double_zone_right.setVisibility(enable ? 0 : 8);
            this.rl_auto_hvac_temp_left.setVisibility(enable ? 0 : 8);
        } else if (i == 2) {
            this.ll_single_zone_eaac.setVisibility(enable ? 0 : 8);
            this.eaac_temp_seek_bar.setVisibility(enable ? 0 : 8);
        }
        AirPanelController airPanelController = this.mAirPanelController;
        if (airPanelController != null) {
            updateCustom(airPanelController.getCurrCustomType(), false);
        }
        this.img_sysui_custom.setEnabled(enable);
        this.img_sysui_air_switch.setEnabled(enable);
        if (enable) {
            this.img_sysui_air_switch.setSelected(this.mHvacPower);
        } else {
            this.img_sysui_air_switch.setSelected(false);
        }
        this.img_sysui_air_wind_level.setEnabled(enable);
        if (!enable) {
            this.img_sysui_air_wind_level.setImageResource(R.mipmap.icon_dock_air_volume_gear_bg);
        }
        this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_close);
        this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_close);
        this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_close);
        this.img_sysui_air_people_arrow_rw.setAlpha(enable ? 1.0f : 0.5f);
        this.iv_navigation_bar_set_temp_left_dec.setEnabled(enable);
        this.iv_navigation_bar_set_temp_left_inc.setEnabled(enable);
        this.iv_navigation_bar_set_temp_right_dec.setEnabled(enable);
        this.iv_navigation_bar_set_temp_right_inc.setEnabled(enable);
        this.iv_navigation_bar_set_temp_left_dec.setAlpha(enable ? 1.0f : 0.8f);
        this.iv_navigation_bar_set_temp_left_inc.setAlpha(enable ? 1.0f : 0.8f);
        this.iv_navigation_bar_set_temp_right_dec.setAlpha(enable ? 1.0f : 0.8f);
        this.iv_navigation_bar_set_temp_right_inc.setAlpha(enable ? 1.0f : 0.8f);
        this.img_sysui_custom.setAlpha(enable ? 1.0f : 0.5f);
        this.img_sysui_air_switch.setAlpha(enable ? 1.0f : 0.5f);
        if (enable) {
            BydLogHelper.logD("updateHasMessage", "update other");
            updateCustom(this.mAirPanelController.getCurrCustomType());
            updateWindMode(this.mHvacController.getAcWindMode());
            updateWindLevel(this.mHvacController.getAcWindLevel());
            updateLeftTemp(this.mHvacController.getFrontLeftTempValue());
            updateRightTemp(this.mHvacController.getFrontRightTempValue());
            updateHvacPower(this.mHvacController.isHvacPower());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWindMode(int mode) {
        if (this.mHasMessage404 != 1) {
            return;
        }
        int acWindModeNum = this.mHvacController.getAcWindModeNum();
        BydLogHelper.logD("updateWindMode", "mHvacController.getAcWindModeNum() : " + this.mHvacController.getAcWindModeNum());
        if (acWindModeNum != 1) {
            acWindModeNum = 2;
        }
        BydLogHelper.logD("updateWindMode", "mode = " + mode + " , windModeNum : " + acWindModeNum);
        if (mode == 1) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_open);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_close);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_close);
            return;
        }
        if (mode == 2) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_open);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_open);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_close);
            return;
        }
        if (mode == 3) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_close);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_open);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_close);
            return;
        }
        if (mode == 4) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_close);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_open);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_open);
            return;
        }
        if (mode == 5) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_close);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_close);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_open);
        } else if (mode == 6 && acWindModeNum == 2) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_open);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_open);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_open);
        } else if (mode == 7 && acWindModeNum == 2) {
            this.img_sysui_air_wind_face.setImageResource(R.mipmap.icon_dock_ac_people_arrow_face_open);
            this.img_sysui_air_wind_foot.setImageResource(R.mipmap.icon_dock_ac_people_arrow_foot_close);
            this.img_sysui_air_defrost.setImageResource(R.mipmap.icon_dock_ac_people_arrow_defrost_open);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUnit(int value) {
        BydLogHelper.logD("updateUnit", "value = " + value);
        if (value == 1) {
            this.iv_left_unit.setImageResource(R.mipmap.img_gesture_ac_temperature_unit);
            this.iv_right_unit.setImageResource(R.mipmap.img_gesture_ac_temperature_unit);
            updateLeftTemp(this.mHvacController.getFrontLeftTempValue());
            updateRightTemp(this.mHvacController.getFrontRightTempValue());
            return;
        }
        if (value == 0) {
            this.iv_left_unit.setImageResource(R.mipmap.img_gesture_ac_temperature_unit_fahrenheit);
            this.iv_right_unit.setImageResource(R.mipmap.img_gesture_ac_temperature_unit_fahrenheit);
            updateLeftTemp(this.mHvacController.getFrontLeftTempValue());
            updateRightTemp(this.mHvacController.getFrontRightTempValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFaultNumShownState(int state) {
        BydLogHelper.logD("updateFaultNumShownState state = " + state);
        if (state != 2) {
            if (state == 1) {
                this.iv_left_unit.setVisibility(0);
                this.iv_right_unit.setVisibility(0);
                updateLeftTemp(this.mHvacController.getFrontLeftTempValue());
                updateRightTemp(this.mHvacController.getFrontRightTempValue());
                return;
            }
            return;
        }
        int frontLeftTempValue = this.mHvacController.getFrontLeftTempValue();
        int frontRightTempValue = this.mHvacController.getFrontRightTempValue();
        this.tvTempLeft.setText(Integer.toHexString(frontLeftTempValue).toUpperCase(Locale.ROOT));
        this.tvTempLeft.setTextColor(this.mContext.getResources().getColor(R.color.color_dock_hvac_temp_text, null));
        this.tvTempRight.setText(Integer.toHexString(frontRightTempValue).toUpperCase(Locale.ROOT));
        this.tvTempRight.setTextColor(this.mContext.getResources().getColor(R.color.color_dock_hvac_temp_text, null));
        this.iv_left_unit.setVisibility(8);
        this.iv_right_unit.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCustom(int type, boolean state) {
        BydLogHelper.logD("updateCustom type = " + type + " , state : " + state);
        if (type == 1) {
            this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.navi_bar_bitmap_custom_defrost));
            this.img_sysui_custom.setSelected(state);
            return;
        }
        if (type != 2) {
            if (type == 3) {
                this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.navi_bar_bitmap_custom_ventilate));
                this.img_sysui_custom.setSelected(state);
                return;
            }
            return;
        }
        BydLogHelper.logD("updateCustom", "mCycleMode : " + this.mCycleMode);
        this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, getAcCycleModeIconResId(this.mCycleMode)));
        int i = this.mCycleMode;
        if (i == 1 || i == 0) {
            this.img_sysui_custom.setSelected(state);
        } else {
            this.img_sysui_custom.setSelected(false);
        }
    }

    private int getAcCycleModeIconResId(int mode) {
        return mode != 0 ? this.mAutoType == 143 ? R.drawable.pa_navi_bar_bitmap_custom_inner_loop : R.drawable.navi_bar_bitmap_custom_inner_loop : this.mAutoType == 143 ? R.drawable.pa_navi_bar_bitmap_custom_outside_loop : R.drawable.navi_bar_bitmap_custom_outside_loop;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCustom(int type) {
        BydLogHelper.logD("updateCustom type = " + type);
        if (type == 1) {
            this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.navi_bar_bitmap_custom_defrost));
            this.img_sysui_custom.setSelected(this.mFrontDefrost);
            return;
        }
        if (type != 2) {
            if (type == 3) {
                this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.navi_bar_bitmap_custom_ventilate));
                this.img_sysui_custom.setSelected(this.mVentilationState == 1);
                return;
            }
            return;
        }
        BydLogHelper.logD("updateCustom mCycleMode = " + this.mCycleMode);
        int i = this.mCycleMode;
        if (i == 0 || i == 1) {
            this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, getAcCycleModeIconResId(i)));
            this.img_sysui_custom.setSelected(true);
        } else {
            this.img_sysui_custom.setSelected(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHvacPower(boolean enable) {
        BydLogHelper.logD("updateHvacPower enable = " + enable);
        this.img_sysui_air_switch.setSelected(enable);
    }

    private void updateHvacType(int type) {
        BydLogHelper.logD("updateHvacType type : " + type);
        if (type == 1) {
            this.rl_double_zone_right.setVisibility(0);
            this.rl_auto_hvac_temp_left.setVisibility(0);
            this.ll_single_zone_eaac.setVisibility(8);
            this.eaac_temp_seek_bar.setVisibility(8);
            return;
        }
        if (this.mAcType == 2) {
            this.rl_double_zone_right.setVisibility(8);
            this.rl_auto_hvac_temp_left.setVisibility(8);
            this.ll_single_zone_eaac.setVisibility(0);
            this.eaac_temp_seek_bar.setVisibility(0);
        }
    }

    public void showLauncherSelectDialog() {
        if (!AppUtils.isSupportMapDesktop()) {
            BydLogHelper.logD("isSupportMapDesktop false");
        } else {
            ((VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class)).showLauncherDialog();
        }
    }

    public void dismissLauncherSelectDialog() {
        ((VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class)).dismissLauncherDialog();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        BydLogHelper.logD("onLongClick navigationBar");
        this.statusBar.closePanelsIfNeed();
        this.statusBar.onNavigationBarClicked(true);
        boolean zIsForeground = AppUtil.isForeground(this.mContext, RecentsActivity.class.getName());
        if (zIsForeground) {
            EventBus.getDefault().post(new RecentEvent());
        }
        if (this.mGestureControl.mHasMoreThanOneFinger) {
            BydLogHelper.logE("onLongClick mGestureControl has multi finger moving!");
            return true;
        }
        switch (v.getId()) {
            case R.id.img_sysui_custom /* 2131296582 */:
                BydLogHelper.logE("long img_sysui_custom getCurrPopupWindow : " + this.mAirPanelController.getCurrPopupWindow());
                if (4 != this.mAirPanelController.getCurrPopupWindow()) {
                    this.mAirPanelController.showPopCustomView(this.img_sysui_custom);
                }
                break;
            case R.id.img_sysui_home /* 2131296583 */:
                if (!this.mDivider.isSplitActive()) {
                    showLauncherSelectDialog();
                } else {
                    BydLogHelper.logE("long click home isSplitActive return");
                    ToastManager.show(this.mContext, R.string.is_not_supported);
                }
                break;
            case R.id.img_sysui_split /* 2131296586 */:
                if (zIsForeground) {
                    ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$O6Id7dxOo_XNHOgDKiJWpOqy3F8
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onLongClick$1$NavigationBarInflaterView();
                        }
                    }, 100L);
                } else {
                    this.mSplitScreenHelpManager.showHelpView();
                }
                break;
        }
        return true;
    }

    public /* synthetic */ void lambda$onLongClick$1$NavigationBarInflaterView() {
        this.mSplitScreenHelpManager.showHelpView();
    }

    private void sendBroadcastClickBtn(String keyValue) {
        Intent intent = new Intent(Constants.SYS_NAVI_BTN_CLICK_ACTION);
        intent.putExtra("btn_value", keyValue);
        this.mContext.sendBroadcast(intent);
    }

    public void openCarSetting() {
        final Intent intent = new Intent();
        intent.setAction("byd.intent.aciton.carsettings");
        intent.addFlags(268435456);
        intent.putExtra("from", "navi_bar");
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.FINISH_APP_CENTER));
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$fHGQJ-65ghE_ItsHLs3GQUXuWIQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$openCarSetting$2$NavigationBarInflaterView(intent);
            }
        });
    }

    public /* synthetic */ void lambda$openCarSetting$2$NavigationBarInflaterView(Intent intent) {
        try {
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d("BydNavigationBar", "exception : " + e);
        }
    }

    public void openHvac() {
        BydLogHelper.logD("openHvac");
        Intent intent = new Intent();
        intent.setAction("byd.intent.aciton.hvac");
        intent.addFlags(268435456);
        intent.putExtra("from", "navi_bar");
        try {
            this.mContext.sendBroadcast(new Intent("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG"));
            this.mContext.startActivity(intent);
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.FINISH_APP_CENTER));
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_ADD_CARD_DIALOG));
        } catch (ActivityNotFoundException e) {
            Log.d("BydNavigationBar", "exception : " + e);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boolean zEquals;
        BydLogHelper.logD("onClick navigationBar");
        boolean z = false;
        this.statusBar.onNavigationBarClicked(false);
        if (AppUtil.isForeground(this.mContext, RecentsActivity.class.getName())) {
            EventBus.getDefault().post(new RecentEvent());
        }
        if (view.getId() == R.id.img_sysui_back) {
            if (FastClickUtil.isFastDoubleClick(500L)) {
            }
            if (this.statusBar.isShow()) {
                this.statusBar.closePanelsIfNeed();
                return;
            }
            this.statusBar.closePanelsIfNeed();
            if (AppUtils.isAppInstall("com.byd.vrassistant.xf")) {
                String str = SystemProperties.get(Constants.SYS_BYD_VR_STATUS, Constant.CarType.CAR_TYPE_FUEL);
                BydLogHelper.logD("vrStatus = " + str);
                zEquals = Constants.VR_RUNNING_STATUS.equals(str);
            } else {
                boolean zIsVrRunning = ShellUtils.isVrRunning();
                BydLogHelper.logD("isVrRunning = " + zIsVrRunning);
                z = zIsVrRunning;
                zEquals = false;
            }
            if (z) {
                Intent intent = new Intent(Constants.VR_ACTION_CANCEL);
                intent.setComponent(new ComponentName(Constants.VR_PACKAGE_NAME, Constants.VR_RECEIVER));
                this.mContext.sendBroadcast(intent);
            } else if (zEquals) {
                Intent intent2 = new Intent(Constants.VR_ACTION_CANCEL);
                intent2.setComponent(new ComponentName("com.byd.vrassistant.xf", Constants.VR_RECEIVER2));
                this.mContext.sendBroadcast(intent2);
            } else {
                long jUptimeMillis = SystemClock.uptimeMillis();
                sendEvent(4, 0, 0, jUptimeMillis, jUptimeMillis);
                sendEvent(4, 1, 0, jUptimeMillis, jUptimeMillis);
            }
            this.mAirPanelController.dismissDialogOnRecentVisible();
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.BACK_KEY_DOWN));
            return;
        }
        int id = view.getId();
        switch (id) {
            case R.id.horizontal /* 2131296536 */:
            case R.id.ll_air_center /* 2131296653 */:
                if (!FastClickUtil.isFastDoubleClick(500L)) {
                    if (this.statusBar.isShow()) {
                        SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
                    }
                    this.statusBar.closePanelsIfNeed();
                    dismissLauncherSelectDialog();
                    AppUtil.startCarSettings("finish_screen_saver");
                    openHvac();
                    break;
                }
                break;
            case R.id.img_sysui_air_switch /* 2131296575 */:
                if (!FastClickUtil.isFastDoubleClick(500L)) {
                    this.statusBar.closePanelsIfNeed();
                    this.mAirPanelController.dismissDialogOnRecentVisible();
                    this.img_sysui_air_switch.setSelected(!r15.isSelected());
                    boolean zIsHvacPower = this.mHvacController.isHvacPower();
                    BydLogHelper.logD("onClick", "hvacPower : " + zIsHvacPower);
                    this.mHvacController.setHvacPower(!zIsHvacPower);
                    this.mNavigationBarInflaterViewHandler.removeMessages(16);
                    this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(16, 2000L);
                    break;
                }
                break;
            case R.id.img_sysui_app /* 2131296579 */:
                if (!FastClickUtil.isFastDoubleClick(500L)) {
                    if (this.statusBar.isShow()) {
                        SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
                    }
                    this.statusBar.closePanelsIfNeed();
                    if (isForbiddenForParking()) {
                        showToast(getResources().getString(R.string.tip_exit_parking_mode));
                    } else {
                        dismissLauncherSelectDialog();
                        this.mAirPanelController.dismissDialogOnRecentVisible();
                        this.mSplitScreenHelpManager.hideSplitNavView();
                        if (this.mDivider.isSplitActive()) {
                            this.mDivider.doHomeClick();
                            this.mDividerUtils.setMoreAppExitSplitScreen(true);
                        } else {
                            sendBroadcastToCard("com.byd.action.card.more.app");
                        }
                    }
                    break;
                }
                break;
            default:
                switch (id) {
                    case R.id.img_sysui_car /* 2131296581 */:
                        if (!FastClickUtil.isFastDoubleClick(500L)) {
                            if (this.statusBar.isShow()) {
                                SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
                            }
                            this.statusBar.closePanelsIfNeed();
                            if (isForbiddenForParking()) {
                                showToast(getResources().getString(R.string.tip_exit_parking_mode));
                            } else {
                                dismissLauncherSelectDialog();
                                this.mAirPanelController.dismissDialogOnRecentVisible();
                                sendBroadcastClickBtn(Constants.SYS_NAVI_BTN_CLICK_VALUE_CAR);
                                openCarSetting();
                                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
                                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_ADD_CARD_DIALOG));
                            }
                            break;
                        }
                        break;
                    case R.id.img_sysui_custom /* 2131296582 */:
                        if (!FastClickUtil.isFastDoubleClick(500L)) {
                            this.statusBar.closePanelsIfNeed();
                            BydLogHelper.logD("onClick", "getCurrCustomType : " + this.mAirPanelController.getCurrCustomType());
                            if (this.mAirPanelController.getCurrCustomType() == 1) {
                                boolean z2 = this.mFrontDefrost;
                                BydLogHelper.logD("onClick", "isFrontDefrostEnabled : " + z2);
                                this.mHvacController.setFrontDefrostEnabled(!z2);
                                this.img_sysui_custom.setSelected(!r15.isSelected());
                            } else if (this.mAirPanelController.getCurrCustomType() == 2) {
                                BydLogHelper.logD("onClick", "mCycleMode : " + this.mCycleMode);
                                int i = this.mCycleMode;
                                if (i == 1) {
                                    this.mHvacController.setAcCycleMode(0);
                                    this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, this.mAutoType == 143 ? R.drawable.pa_navi_bar_bitmap_custom_outside_loop : R.drawable.navi_bar_bitmap_custom_outside_loop));
                                } else if (i == 0) {
                                    this.mHvacController.setAcCycleMode(1);
                                    this.img_sysui_custom.setBackground(ContextCompat.getDrawable(this.mContext, this.mAutoType == 143 ? R.drawable.pa_navi_bar_bitmap_custom_inner_loop : R.drawable.navi_bar_bitmap_custom_inner_loop));
                                }
                                this.img_sysui_custom.setSelected(true);
                            } else if (this.mAirPanelController.getCurrCustomType() == 3) {
                                int i2 = this.mVentilationState;
                                BydLogHelper.logD("onClick", "acVentilationState : " + i2);
                                this.mHvacController.setAcVentilationState(i2 != 1 ? 1 : 0);
                                this.img_sysui_custom.setSelected(!r15.isSelected());
                            }
                            this.mNavigationBarInflaterViewHandler.removeMessages(17);
                            this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(17, 2000L);
                            break;
                        }
                        break;
                    case R.id.img_sysui_home /* 2131296583 */:
                        if (!FastClickUtil.isFastDoubleClick(500L)) {
                            this.statusBar.closePanelsIfNeed();
                            dismissLauncherSelectDialog();
                            this.mAirPanelController.dismissDialogOnRecentVisible();
                            long jUptimeMillis2 = SystemClock.uptimeMillis();
                            sendEvent(3, 0, 0, jUptimeMillis2, jUptimeMillis2);
                            sendEvent(3, 1, 0, jUptimeMillis2, jUptimeMillis2);
                            break;
                        }
                        break;
                    case R.id.img_sysui_orientation /* 2131296584 */:
                        if (!FastClickUtil.isFastDoubleClick(500L)) {
                            this.statusBar.closePanelsIfNeed();
                            if (isForbiddenForParking()) {
                                showToast(getResources().getString(R.string.tip_exit_parking_mode));
                            } else if (this.mCarController.isAndroidAutoFocus()) {
                                BydLogHelper.logD("android auto is focus");
                                showToast(this.mContext.getString(R.string.toast_android_auto_focus));
                            } else if (this.mCarController.isCarPlayFocus()) {
                                BydLogHelper.logD("carplay is focus");
                                showToast(this.mContext.getString(R.string.toast_android_auto_focus));
                            } else if (this.mCarController.getScreenRotationOverHotState()) {
                                BydLogHelper.logD("RotationOverHotState is true");
                                showRotationTips(null, this.mContext.getString(R.string.rotate_over_hot_tips), 3);
                            } else if (isLand(this.mContext)) {
                                this.mCarController.setPadRotation(2);
                            } else {
                                this.mCarController.setPadRotation(1);
                            }
                            break;
                        }
                        break;
                    case R.id.img_sysui_screen_off /* 2131296585 */:
                        BydLogHelper.logD("img_sysui_screen_off");
                        if (FastClickUtil.isFastDoubleClick(R.id.img_sysui_screen_off, 1500L)) {
                            BydLogHelper.logD("screen off onClick is fast click");
                        } else {
                            if (this.statusBar.isShow()) {
                                SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
                            }
                            String str2 = SystemProperties.get("sys.byd.uereport.epb", Constant.CarType.CAR_TYPE_FUEL);
                            if (TextUtils.equals(str2, "1")) {
                                BydLogHelper.logD("epb model epbStatus: " + str2);
                                showToast(this.mContext.getString(R.string.qs_title_toast_epb));
                            } else if (this.mCarController.isCarPlayPhoneCall()) {
                                BydLogHelper.logD("carPlay is onCalling");
                                showToast(this.mContext.getString(R.string.operation_prohibited_during_call));
                            } else {
                                AppUtil.startCarSettings("screen_saver");
                            }
                        }
                        break;
                    case R.id.img_sysui_split /* 2131296586 */:
                        if (!FastClickUtil.isFastDoubleClick(500L)) {
                            this.statusBar.closePanelsIfNeed();
                            if (isForbiddenForParking()) {
                                showToast(getResources().getString(R.string.tip_exit_parking_mode));
                                break;
                            } else {
                                dismissLauncherSelectDialog();
                                boolean zIsAnimating = SplitPreviewWindowManager.getInstance(this.mContext).isAnimating();
                                BydLogHelper.logD("split btn isAnimating : " + zIsAnimating);
                                if (!zIsAnimating) {
                                    if (this.mDividerUtils.isFirstStartSplitScreen(this.mContext)) {
                                        boolean zIsShowHelpActivity = this.mSplitScreenHelpManager.isShowHelpActivity();
                                        BydLogHelper.logD("is first start split screen isShowHelpActivity : " + zIsShowHelpActivity);
                                        if (!zIsShowHelpActivity) {
                                            this.mSplitScreenHelpManager.showHelpView();
                                        }
                                    }
                                    if (this.mDividerUtils.isLauncherSelecting()) {
                                        BydLogHelper.logD("split isLauncherSelecting return");
                                    } else {
                                        boolean zIsLauncherReplacedDesktop = this.mDividerUtils.isLauncherReplacedDesktop();
                                        BydLogHelper.logD("split isLauncherReplacedDesktop : " + zIsLauncherReplacedDesktop);
                                        if (zIsLauncherReplacedDesktop) {
                                            BydLogHelper.logD("split isLauncherReplacedDesktop is true break;");
                                        } else {
                                            Settings.System.putInt(this.mContext.getContentResolver(), Constants.SYSTEM_UI_SPLIT_BTN_ANTI_SHAKE, 1);
                                            new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.1
                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    Settings.System.putInt(NavigationBarInflaterView.this.mContext.getContentResolver(), Constants.SYSTEM_UI_SPLIT_BTN_ANTI_SHAKE, 2);
                                                }
                                            }, 500L);
                                            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_ADD_CARD_DIALOG));
                                            if (this.mSplitScreenHelpManager.isShowHelpActivity()) {
                                                this.mSplitScreenHelpManager.hideSplitNavView();
                                            } else {
                                                this.statusBar.toggleSplitScreenMode();
                                                this.mAirPanelController.dismissDialogOnRecentVisible();
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                        switch (id) {
                            case R.id.iv_navigation_bar_set_temp_left_dec /* 2131296621 */:
                                BydLogHelper.logD("iv_navigation_bar_set_temp_left_dec showCarModelType : " + this.mCarController.getShowCarModelType());
                                if (!FastClickUtil.isFastDoubleClick(300L)) {
                                    this.statusBar.closePanelsIfNeed();
                                    showLeftHvacPopByDec(false);
                                    break;
                                }
                                break;
                            case R.id.iv_navigation_bar_set_temp_left_inc /* 2131296622 */:
                                BydLogHelper.logD("iv_navigation_bar_set_temp_left_inc showCarModelType : " + this.mCarController.getShowCarModelType());
                                if (!FastClickUtil.isFastDoubleClick(300L)) {
                                    this.statusBar.closePanelsIfNeed();
                                    showLeftHvacPopByInc(false);
                                    break;
                                }
                                break;
                            case R.id.iv_navigation_bar_set_temp_right_dec /* 2131296623 */:
                                BydLogHelper.logD("iv_navigation_bar_set_temp_right_dec showCarModelType : " + this.mCarController.getShowCarModelType());
                                if (!FastClickUtil.isFastDoubleClick(300L)) {
                                    this.statusBar.closePanelsIfNeed();
                                    showRightHvacPopByDec(false);
                                    break;
                                }
                                break;
                            case R.id.iv_navigation_bar_set_temp_right_inc /* 2131296624 */:
                                BydLogHelper.logD("iv_navigation_bar_set_temp_right_inc showCarModelType : " + this.mCarController.getShowCarModelType());
                                if (!FastClickUtil.isFastDoubleClick(300L)) {
                                    this.statusBar.closePanelsIfNeed();
                                    showRightHvacPopByInc(false);
                                    break;
                                }
                                break;
                            default:
                                switch (id) {
                                    case R.id.ll_temp_left /* 2131296678 */:
                                    case R.id.ll_temp_right /* 2131296679 */:
                                        if (!FastClickUtil.isFastDoubleClick(500L)) {
                                            this.statusBar.closePanelsIfNeed();
                                            break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    protected void showToast(String text) {
        int dimensionPixelSize;
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        this.mToast = new Toast(this.mContext);
        Resources resources = this.mContext.getResources();
        if (isLand(this.mContext)) {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.dp_72);
        } else {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.dp_190);
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_custom_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.mToast.setView(viewInflate);
        this.mToast.setGravity(48, 0, dimensionPixelSize);
        textView.setText(text);
        this.mToast.setDuration(0);
        this.mToast.show();
    }

    public boolean isLand(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        Log.d(TAG, "orientation " + i);
        return i != 1;
    }

    private void sendBroadcastToCard(String action) {
        BydLogHelper.logD("broadcast:" + action);
        Intent intent = new Intent();
        intent.setAction(action);
        this.mContext.sendBroadcast(intent);
    }

    private void showDialog(String title, String message, int bottomStyle) {
        if (this.mDialog == null) {
            this.mDialog = new CommonDialog(this.mContext);
        }
        this.mDialog.setCountTime(8);
        this.mDialog.setMessage(message).setTitle(title).setBtnLeftBgColor(true).setBottomStyle(bottomStyle).setBtnLeft(getResources().getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                BydLogHelper.logD("onPositiveClick");
                NavigationBarInflaterView.this.mDialog.dismiss();
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                NavigationBarInflaterView.this.mDialog.dismiss();
            }
        });
        if (this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    private void showRotationTips(String title, String message, int bottomStyle) {
        if (this.mDialog == null) {
            this.mDialog = new CommonDialog(this.mContext);
        }
        this.mDialog.setCountTime(5);
        this.mDialog.setMessage(message).setTitle(title).setBottomStyle(bottomStyle).setBtnLeft(getResources().getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.3
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                BydLogHelper.logD("onPositiveClick");
                NavigationBarInflaterView.this.mDialog.dismiss();
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                NavigationBarInflaterView.this.mDialog.dismiss();
            }
        });
        if (this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    private void showPopTemp(View view, boolean isLeft) {
        BydLogHelper.logD("showPopTemp mAcType : " + this.mAcType);
        int currPopupWindow = this.mAirPanelController.getCurrPopupWindow();
        int i = this.mAcType;
        if (i == 1) {
            int i2 = isLeft ? 1 : 2;
            BydLogHelper.logD("showPopTemp viewType : " + i2 + " , currPopupWindow : " + currPopupWindow);
            if (currPopupWindow != i2) {
                this.mAirPanelController.showPopTemp(view, isLeft);
                return;
            }
            return;
        }
        if (i == 2) {
            if (view.getId() == R.id.ll_temp_left) {
                BydLogHelper.logD("showPopTemp currPopupWindow : " + currPopupWindow);
                if (currPopupWindow != 5) {
                    this.mAirPanelController.showEAACPopTemp(view);
                    return;
                }
                return;
            }
            if (view.getId() == R.id.ll_temp_right) {
                BydLogHelper.logD("showPopTemp currPopupWindow : " + currPopupWindow);
                if (currPopupWindow != 3) {
                    this.mAirPanelController.showWindLevel(view);
                }
            }
        }
    }

    private void showWindLevel(View view) {
        int currPopupWindow = this.mAirPanelController.getCurrPopupWindow();
        BydLogHelper.logD("showWindLevel currPopupWindow : " + currPopupWindow);
        if (currPopupWindow != 3) {
            this.mAirPanelController.showWindLevel(view);
        }
    }

    public void sendEvent(int keyCode, int action, int flags, long down, long when) {
        KeyEvent keyEvent = new KeyEvent(down, when, action, keyCode, (flags & 128) != 0 ? 1 : 0, 0, -1, 0, flags | 8 | 64, 257);
        keyEvent.setDisplayId(0);
        InputManager.getInstance().injectInputEvent(keyEvent, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x015b  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarInflaterView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class HvacCallbacks implements HvacController.Callback {
        private HvacCallbacks() {
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onWindModeChanged(final int mode) {
            BydLogHelper.logD("onWindModeChanged mode = " + mode);
            NavigationBarInflaterView.this.mWindMode = mode;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$5yvYfN6cQxCWYb6v7KsDSzsC4b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onWindModeChanged$0$NavigationBarInflaterView$HvacCallbacks(mode);
                }
            });
        }

        public /* synthetic */ void lambda$onWindModeChanged$0$NavigationBarInflaterView$HvacCallbacks(int i) {
            NavigationBarInflaterView.this.updateWindMode(i);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onHvacPower(final boolean enabled) {
            BydLogHelper.logD("onHvacPower enabled = " + enabled);
            NavigationBarInflaterView.this.mHvacPower = enabled;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$sMIB7sky-8uA1Bq_R0nz7Vf3vSQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onHvacPower$1$NavigationBarInflaterView$HvacCallbacks(enabled);
                }
            });
            NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.removeMessages(16);
        }

        public /* synthetic */ void lambda$onHvacPower$1$NavigationBarInflaterView$HvacCallbacks(boolean z) {
            NavigationBarInflaterView.this.updateHvacPower(z);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onTemperatureUnitChanged(final int value) {
            BydLogHelper.logD("onTemperatureUnitChanged value = " + value);
            NavigationBarInflaterView.this.mUnit = value;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$qtdg-o6XvmAdMn-2pZmUoPNiMfc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTemperatureUnitChanged$2$NavigationBarInflaterView$HvacCallbacks(value);
                }
            });
        }

        public /* synthetic */ void lambda$onTemperatureUnitChanged$2$NavigationBarInflaterView$HvacCallbacks(int i) {
            NavigationBarInflaterView.this.updateUnit(i);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onFrontDefrostChanged(final boolean enabled) {
            BydLogHelper.logD("onFrontDefrostChanged enabled = " + enabled);
            NavigationBarInflaterView.this.mFrontDefrost = enabled;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$lP60KjZHR_p63mtEIKYgQfgPxdw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFrontDefrostChanged$3$NavigationBarInflaterView$HvacCallbacks(enabled);
                }
            });
        }

        public /* synthetic */ void lambda$onFrontDefrostChanged$3$NavigationBarInflaterView$HvacCallbacks(boolean z) {
            if (NavigationBarInflaterView.this.mAirPanelController.getCurrCustomType() == 1) {
                NavigationBarInflaterView.this.updateCustom(1, z);
            }
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onFrontLeftTempChanged(final int value) {
            BydLogHelper.logD("onFrontLeftTempChanged value = " + value);
            if (NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.hasMessages(18)) {
                return;
            }
            NavigationBarInflaterView.this.mLeftTemp = value;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$A34lgm93R1kxyhAUxegS3EAtBC0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFrontLeftTempChanged$4$NavigationBarInflaterView$HvacCallbacks(value);
                }
            });
        }

        public /* synthetic */ void lambda$onFrontLeftTempChanged$4$NavigationBarInflaterView$HvacCallbacks(int i) {
            NavigationBarInflaterView.this.updateLeftTemp(i);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onFrontRightTempChanged(final int value) {
            BydLogHelper.logD("onFrontRightTempChanged value = " + value);
            if (NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.hasMessages(19)) {
                return;
            }
            NavigationBarInflaterView.this.mRightTemp = value;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$0e4361AS6i9oUKeSL0JGFEr3oBc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFrontRightTempChanged$5$NavigationBarInflaterView$HvacCallbacks(value);
                }
            });
        }

        public /* synthetic */ void lambda$onFrontRightTempChanged$5$NavigationBarInflaterView$HvacCallbacks(int i) {
            NavigationBarInflaterView.this.updateRightTemp(i);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onWindLevelChanged(final int level) {
            BydLogHelper.logD("onWindLevelChanged level = " + level);
            if (NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.hasMessages(20)) {
                return;
            }
            NavigationBarInflaterView.this.mWindLevel = level;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$aW12XQN9oeDSNg5U_iguIkrWck8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onWindLevelChanged$6$NavigationBarInflaterView$HvacCallbacks(level);
                }
            });
        }

        public /* synthetic */ void lambda$onWindLevelChanged$6$NavigationBarInflaterView$HvacCallbacks(int i) {
            NavigationBarInflaterView.this.updateWindLevel(i);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onPowerLevelChanged(int level) {
            BydLogHelper.logD("onPowerLevelChanged level = " + level);
            if (level == 0) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                NavigationBarInflaterView.this.sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
                NavigationBarInflaterView.this.sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
            }
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onCycleModeChanged(int mode) {
            BydLogHelper.logD("onCycleModeChanged mode = " + mode);
            NavigationBarInflaterView.this.mCycleMode = mode;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$IOq1EnU4rRiYyc7Dr-vVOzOBfA8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCycleModeChanged$7$NavigationBarInflaterView$HvacCallbacks();
                }
            });
        }

        public /* synthetic */ void lambda$onCycleModeChanged$7$NavigationBarInflaterView$HvacCallbacks() {
            if (NavigationBarInflaterView.this.mAirPanelController.getCurrCustomType() == 2) {
                NavigationBarInflaterView.this.updateCustom(2);
            }
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onFaultNumShownStateChanged(final int state) {
            BydLogHelper.logD("onFaultNumShownStateChanged mode = " + state);
            NavigationBarInflaterView.this.mFaultNumShownState = state;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$_YIRdRcF5eNOfUNsR800I-KyPZM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFaultNumShownStateChanged$8$NavigationBarInflaterView$HvacCallbacks(state);
                }
            });
        }

        public /* synthetic */ void lambda$onFaultNumShownStateChanged$8$NavigationBarInflaterView$HvacCallbacks(int i) {
            NavigationBarInflaterView.this.updateFaultNumShownState(i);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onAcVentilationStateChanged(final int state) {
            BydLogHelper.logD("onAcVentilationStateChanged state = " + state + " mCurrCustomType : " + NavigationBarInflaterView.this.mAirPanelController.getCurrCustomType());
            NavigationBarInflaterView.this.mVentilationState = state;
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$mafCZE8VuPeJmxRNOkz2Ab-kwlE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAcVentilationStateChanged$9$NavigationBarInflaterView$HvacCallbacks(state);
                }
            });
        }

        public /* synthetic */ void lambda$onAcVentilationStateChanged$9$NavigationBarInflaterView$HvacCallbacks(int i) {
            if (NavigationBarInflaterView.this.mAirPanelController.getCurrCustomType() == 3) {
                NavigationBarInflaterView.this.updateCustom(3, i == 1);
            }
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onRefreshHvac() {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$Hul0nmy_gGisrmpQ_Le1hwBNT5s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRefreshHvac$10$NavigationBarInflaterView$HvacCallbacks();
                }
            });
        }

        public /* synthetic */ void lambda$onRefreshHvac$10$NavigationBarInflaterView$HvacCallbacks() {
            NavigationBarInflaterView.this.initAirPanel();
            NavigationBarInflaterView.this.initAcStatus();
            NavigationBarInflaterView.this.updateNaviBarIcon();
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onMessage5sOnlineStateChanged(final int id, final int state) {
            BydLogHelper.logD("onMessage5sOnlineStateChanged id = " + id + " , state = " + state);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$HvacCallbacks$xnD85_Dh8oPULsITLQgKZHEh0k8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onMessage5sOnlineStateChanged$11$NavigationBarInflaterView$HvacCallbacks(id, state);
                }
            });
        }

        public /* synthetic */ void lambda$onMessage5sOnlineStateChanged$11$NavigationBarInflaterView$HvacCallbacks(int i, int i2) {
            if (i == 59) {
                NavigationBarInflaterView.this.mHasMessage404 = i2;
                NavigationBarInflaterView.this.updateHasMessage(i2 == 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AirPanelControllerListener implements AirPanelController.AirPanelControllerListener {
        private AirPanelControllerListener() {
        }

        @Override // com.android.systemui.statusbar.wm.AirPanelController.AirPanelControllerListener
        public void onCustomTypeChanged(final int type) {
            BydLogHelper.logD("onCustomTypeChanged type = " + type);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$AirPanelControllerListener$Nz2sh9LrvUEMJ3zpr5n61lx2U70
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCustomTypeChanged$0$NavigationBarInflaterView$AirPanelControllerListener(type);
                }
            });
        }

        public /* synthetic */ void lambda$onCustomTypeChanged$0$NavigationBarInflaterView$AirPanelControllerListener(int i) {
            boolean z = true;
            if (i == 1) {
                z = NavigationBarInflaterView.this.mFrontDefrost;
            } else if (i != 2 && i == 3 && NavigationBarInflaterView.this.mVentilationState != 1) {
                z = false;
            }
            NavigationBarInflaterView.this.mCarController.syncHvacIcon2Qnx(i);
            BydLogHelper.logD("onCustomTypeChanged mHvacController.isHvacPower() = " + NavigationBarInflaterView.this.mHvacController.isHvacPower());
            NavigationBarInflaterView.this.updateCustom(i, NavigationBarInflaterView.this.mHvacController.isHvacPower() ? z : false);
        }

        @Override // com.android.systemui.statusbar.wm.AirPanelController.AirPanelControllerListener
        public void onTemperatureChanged(int area, int value) {
            if (area == 1) {
                NavigationBarInflaterView.this.updateLeftTemp(value);
                NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.removeMessages(18);
                NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(18, 2000L);
            } else if (area == 2) {
                NavigationBarInflaterView.this.updateRightTemp(value);
                NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.removeMessages(19);
                NavigationBarInflaterView.this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(19, 2000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerEspOffKeyStatus(int espOffKeyStatus) {
        if (espOffKeyStatus == 1) {
            int elecCarBodyStablySysState = this.mCarController.getElecCarBodyStablySysState();
            BydLogHelper.logD("getElecCarBodyStablySysState : " + elecCarBodyStablySysState);
            if (elecCarBodyStablySysState == 0) {
                int supportDoubleConfirm = this.mCarController.getSupportDoubleConfirm();
                BydLogHelper.logD("supportDoubleConfirm : " + supportDoubleConfirm);
                if (supportDoubleConfirm == 1) {
                    Context context = this.mContext;
                    DialogUtils.showEspOffDialog(context, context.getResources().getString(R.string.esp_system), this.mContext.getResources().getString(R.string.esp_system_close_tips));
                    return;
                } else {
                    BydLogHelper.logD("setElecCarBodyStablySysState SET_OFF（关） ");
                    this.mCarController.setElecCarBodyStablySysState(1);
                    return;
                }
            }
            BydLogHelper.logD("setElecCarBodyStablySysState SET_ON（开） ");
            this.mCarController.setElecCarBodyStablySysState(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class OperationModeCallback implements CarController.Callback {
        private OperationModeCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onEspOffKeyStatusChanged(final int espOffKeyStatus) {
            BydLogHelper.logD("espOffKeyStatus : " + espOffKeyStatus);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$OperationModeCallback$ixG_Wit7_devov4I5ehgMTg2wQ4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onEspOffKeyStatusChanged$0$NavigationBarInflaterView$OperationModeCallback(espOffKeyStatus);
                }
            });
        }

        public /* synthetic */ void lambda$onEspOffKeyStatusChanged$0$NavigationBarInflaterView$OperationModeCallback(int i) {
            NavigationBarInflaterView.this.handlerEspOffKeyStatus(i);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onOperationModeChanged(final int mode) {
            BydLogHelper.logD(NavigationBarInflaterView.TAG, "onOperationModeChanged mode:" + mode);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$OperationModeCallback$xkfX3bDhanrjxHQADODpHqk3ZsA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onOperationModeChanged$1$NavigationBarInflaterView$OperationModeCallback(mode);
                }
            });
        }

        public /* synthetic */ void lambda$onOperationModeChanged$1$NavigationBarInflaterView$OperationModeCallback(int i) {
            String carType = AppUtils.getCarType();
            BydLogHelper.logD(NavigationBarInflaterView.TAG, "carType:" + carType);
            if ("1".equals(carType)) {
                if (i == 1 || i == 2) {
                    NavigationBarInflaterView.this.setOperationMode(i);
                    return;
                }
                return;
            }
            if (i == 1 || i == 2) {
                NavigationBarInflaterView.this.setOperationMode(i);
            } else if (i == 3) {
                NavigationBarInflaterView.this.setOperationMode(0);
            }
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onOperationModeNormalChanged(final int mode) {
            BydLogHelper.logD(NavigationBarInflaterView.TAG, "onOperationModeNormalChanged mode:" + mode);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$OperationModeCallback$DfGCvqyW4mxGLq8DiKAdVFhybpc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onOperationModeNormalChanged$2$NavigationBarInflaterView$OperationModeCallback(mode);
                }
            });
        }

        public /* synthetic */ void lambda$onOperationModeNormalChanged$2$NavigationBarInflaterView$OperationModeCallback(int i) {
            if (i == 1) {
                NavigationBarInflaterView.this.setOperationMode(0);
            }
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onAvmDockerStatusChanged(final int value) {
            BydLogHelper.logD(NavigationBarInflaterView.TAG, "onAvmDockerStatusChanged value:" + value);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$OperationModeCallback$kstPNQ55U34JVtsGo5atypxO9-c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAvmDockerStatusChanged$3$NavigationBarInflaterView$OperationModeCallback(value);
                }
            });
        }

        public /* synthetic */ void lambda$onAvmDockerStatusChanged$3$NavigationBarInflaterView$OperationModeCallback(int i) {
            NavigationBarInflaterView.this.handlerAvm(i);
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onUpdateFlag(final boolean flag) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$OperationModeCallback$QvTaIYi1t9njsJ19Sj3Z4mDdwt4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onUpdateFlag$4$NavigationBarInflaterView$OperationModeCallback(flag);
                }
            });
        }

        public /* synthetic */ void lambda$onUpdateFlag$4$NavigationBarInflaterView$OperationModeCallback(boolean z) {
            NavigationBarInflaterView.this.updateOta(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerAvm(int value) {
        if (!GuideUtils.isProvisionFinished(this.mContext) || !GuideUtils.isSetupFinished(this.mContext)) {
            BydLogHelper.logD("handlerAvm guide isn't complete");
            return;
        }
        if (value == 1) {
            return;
        }
        if (value == 2) {
            dismissLauncherSelectDialog();
            return;
        }
        if (value == 3) {
            dismissLauncherSelectDialog();
        } else if (value == 4) {
            dismissLauncherSelectDialog();
        } else if (value == 5) {
            dismissLauncherSelectDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOperationMode(int mode) {
        int currentOperationMode = this.mCarController.getCurrentOperationMode();
        BydLogHelper.logD(TAG, "old Mode:" + currentOperationMode + " , new mode:" + mode);
        int i = (currentOperationMode == 0 && mode == 1) ? R.drawable.mode_normal_eco_anim : (currentOperationMode == 0 && mode == 2) ? R.drawable.mode_normal_sport_anim : (currentOperationMode == 1 && mode == 0) ? R.drawable.mode_eco_normal_anim : (currentOperationMode == 1 && mode == 2) ? R.drawable.mode_eco_sport_anim : (currentOperationMode == 2 && mode == 0) ? R.drawable.mode_sport_normal_anim : (currentOperationMode == 2 && mode == 1) ? R.drawable.mode_sport_eco_anim : 0;
        this.mCarController.setCurrentOperationMode(mode);
        if (i == 0 || this.img_operation_mode == null) {
            return;
        }
        BydLogHelper.logD(TAG, "-- show anima operation --");
        this.img_operation_mode.stopModeAnimation();
        this.img_operation_mode.setAnimRes(i);
        this.img_operation_mode.startModeAnimation();
    }

    public void updateNaviBarStatus(int value) {
        BydLogHelper.logD("updateNaviBarStatus value : " + value);
        Configuration configuration = this.mContext.getResources().getConfiguration();
        BydLogHelper.logD("updateNaviBarStatus uiMode: " + configuration.uiMode);
        if (value == 2) {
            this.mDivider.setIndexWindowManagerVisibility(false);
            if ((configuration.uiMode & 48) != 32) {
                BydLogHelper.logD("updateNaviBarStatus NAVIBAR_MODE_MODE_AC. Change uiMode to UI_MODE_NIGHT_YES.");
                this.changeDayNightMode = true;
                setNightModeOnScreensaverMode(this.mContext, true);
            }
            this.ll_left.setVisibility(4);
            this.ll_right.setVisibility(4);
            this.ll_center.setVisibility(0);
            return;
        }
        if (value != 1) {
            if (value == 0) {
                this.mDivider.setIndexWindowManagerVisibility(false);
                return;
            }
            return;
        }
        this.ll_left.setVisibility(0);
        this.ll_right.setVisibility(0);
        this.ll_center.setVisibility(0);
        if (this.mDivider.isSplitActive() && !this.mSplitScreenHelpManager.isShowHelpActivity()) {
            this.mDivider.setIndexWindowManagerVisibility(true);
        }
        BydLogHelper.logD("updateNaviBarStatus changeDayNightMode = " + this.changeDayNightMode);
        if (this.changeDayNightMode && (configuration.uiMode & 48) == 32) {
            BydLogHelper.logD("updateNaviBarStatus NAVIBAR_MODE_MODE_ALL. Reset uiMode to UI_MODE_NIGHT_NO.");
            this.changeDayNightMode = false;
            setNightModeOnScreensaverMode(this.mContext, false);
        }
    }

    private void setNightModeOnScreensaverMode(Context ctx, boolean nightMode) {
        BydLogHelper.logD("setNightModeOnScreensaverMode nightMode: " + nightMode);
        if (ctx == null) {
            return;
        }
        Resources resources = ctx.getApplicationContext().getResources();
        Configuration configuration = new Configuration();
        configuration.uiMode = nightMode ? 32 : 16;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        BydLogHelper.logD("setNightModeOnScreensaverMode1 call onConfigurationChanged. uiMode: " + resources.getConfiguration().uiMode);
        this.mIgnoreSplitScreenHelper = true;
        onConfigurationChanged(resources.getConfiguration());
        this.mIgnoreSplitScreenHelper = false;
        ((VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class)).onConfigurationChanged(resources.getConfiguration());
    }

    public void updateNaviBarBg(boolean splitScreenStatus) {
        BydLogHelper.logD("updateNaviBarBg splitScreenStatus : " + splitScreenStatus);
        CarController carController = this.mCarController;
        boolean z = carController != null && (carController.isCarPlayFocus() || this.mCarController.isAndroidAutoFocus());
        BydLogHelper.logD("isCPAAFocus = " + z);
        if (splitScreenStatus || z) {
            this.mRot0.setBackground(ResourcesCompat.getDrawable(getResources(), StyleUtil.getResMipmap(R.mipmap.img_navi_bar_bg_2, "img_navi_bar_bg_2"), null));
        } else {
            this.mRot0.setBackground(ResourcesCompat.getDrawable(getResources(), StyleUtil.getResMipmap(R.mipmap.img_navi_bar_bg, "img_navi_bar_bg"), null));
        }
    }

    public void bindBydCarSettingsService() {
        BydLogHelper.logD("bindBydCarSettingsService");
        BydSettingsApi.getApi(this.mContext, this.callback).listenToScreensaverDeath(new OnServiceDeathCallback() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarInflaterView$12_DSjIYriHq90_ncKgUx6mzHZo
            @Override // com.byd.systemsettings.callback.OnServiceDeathCallback
            public final void onServiceDied(String str) {
                this.f$0.lambda$bindBydCarSettingsService$3$NavigationBarInflaterView(str);
            }
        });
    }

    public /* synthetic */ void lambda$bindBydCarSettingsService$3$NavigationBarInflaterView(String str) {
        BydLogHelper.logD(TAG, "---onServiceDied---serviceName: " + str);
        Settings.Global.putInt(this.mContext.getContentResolver(), "navibar_mode_on_screensaver", 1);
    }

    private class NavigationBarInflaterViewHandler extends Handler {
        private static final int HVAC_CUSTOM = 17;
        private static final int HVAC_POWER = 16;
        private static final int LEFT_TEMP = 18;
        private static final int MESSAGE_LOAD_SPLIT_APP_LIST = 22;
        private static final int RESTORE_HVAC_STATE = 21;
        private static final int RIGHT_TEMP = 19;
        private static final int TIME_DELAY = 1000;
        private static final int WIND_LEVEL = 20;

        private NavigationBarInflaterViewHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BydLogHelper.logD("NavigationBarInflaterViewHandler handleMessage msg.what : " + msg.what);
            switch (msg.what) {
                case 16:
                    NavigationBarInflaterView navigationBarInflaterView = NavigationBarInflaterView.this;
                    navigationBarInflaterView.updateHvacPower(navigationBarInflaterView.mHvacController.isHvacPower());
                    break;
                case 17:
                    NavigationBarInflaterView navigationBarInflaterView2 = NavigationBarInflaterView.this;
                    navigationBarInflaterView2.updateCustom(navigationBarInflaterView2.mAirPanelController.getCurrCustomType());
                    if (!NavigationBarInflaterView.this.mHvacController.isHvacPower()) {
                        NavigationBarInflaterView.this.img_sysui_custom.setSelected(false);
                    }
                    break;
                case 18:
                    NavigationBarInflaterView navigationBarInflaterView3 = NavigationBarInflaterView.this;
                    navigationBarInflaterView3.mLeftTemp = navigationBarInflaterView3.mHvacController.getFrontLeftTempValue();
                    NavigationBarInflaterView navigationBarInflaterView4 = NavigationBarInflaterView.this;
                    navigationBarInflaterView4.updateLeftTemp(navigationBarInflaterView4.mLeftTemp);
                    break;
                case 19:
                    NavigationBarInflaterView navigationBarInflaterView5 = NavigationBarInflaterView.this;
                    navigationBarInflaterView5.mRightTemp = navigationBarInflaterView5.mHvacController.getFrontRightTempValue();
                    NavigationBarInflaterView navigationBarInflaterView6 = NavigationBarInflaterView.this;
                    navigationBarInflaterView6.updateRightTemp(navigationBarInflaterView6.mRightTemp);
                    break;
                case 20:
                    NavigationBarInflaterView navigationBarInflaterView7 = NavigationBarInflaterView.this;
                    navigationBarInflaterView7.mWindLevel = navigationBarInflaterView7.mHvacController.getAcWindLevel();
                    NavigationBarInflaterView navigationBarInflaterView8 = NavigationBarInflaterView.this;
                    navigationBarInflaterView8.updateWindLevel(navigationBarInflaterView8.mWindLevel);
                    break;
                case 21:
                    NavigationBarInflaterView.this.restoreHvacPopWindowState();
                    break;
                case 22:
                    UserManager userManager = (UserManager) NavigationBarInflaterView.this.mContext.getSystemService("user");
                    BydLogHelper.logD("handleMessage isUserUnlocked : " + userManager.isUserUnlocked());
                    if (userManager.isUserUnlocked()) {
                        removeMessages(22);
                        NavigationBarInflaterView.this.mDividerUtils.startLoadAppAsyncTask(NavigationBarInflaterView.this.mContext);
                    } else {
                        removeMessages(22);
                        sendEmptyMessageDelayed(22, 1000L);
                    }
                    break;
            }
        }
    }

    private void showLeftHvacPopByDec(final boolean playWheelSoundEffect) {
        showPopTemp(this.ll_temp_left, true);
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.4
            @Override // java.lang.Runnable
            public void run() {
                int currPopupWindow = NavigationBarInflaterView.this.mAirPanelController.getCurrPopupWindow();
                BydLogHelper.logD("showLeftHvacPopByDec currPopupWindow : " + currPopupWindow);
                if (currPopupWindow == 1 || currPopupWindow == 5) {
                    NavigationBarInflaterView.this.mAirPanelController.setNeedPlayWheelSoundEffect(playWheelSoundEffect);
                    NavigationBarInflaterView.this.mAirPanelController.reductionTemp();
                    NavigationBarInflaterView.this.mAirPanelController.setCenterIndex();
                }
            }
        }, 50L);
        this.mNavigationBarInflaterViewHandler.removeMessages(18);
        this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(18, 2000L);
    }

    private void showLeftHvacPopByInc(final boolean playWheelSoundEffect) {
        showPopTemp(this.ll_temp_left, true);
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.5
            @Override // java.lang.Runnable
            public void run() {
                int currPopupWindow = NavigationBarInflaterView.this.mAirPanelController.getCurrPopupWindow();
                BydLogHelper.logD("showLeftHvacPopByInc currPopupWindow : " + currPopupWindow);
                if (currPopupWindow == 1 || currPopupWindow == 5) {
                    NavigationBarInflaterView.this.mAirPanelController.setNeedPlayWheelSoundEffect(playWheelSoundEffect);
                    NavigationBarInflaterView.this.mAirPanelController.riseTemp();
                    NavigationBarInflaterView.this.mAirPanelController.setCenterIndex();
                }
            }
        }, 50L);
        this.mNavigationBarInflaterViewHandler.removeMessages(18);
        this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(18, 2000L);
    }

    private void showRightHvacPopByDec(final boolean playWheelSoundEffect) {
        showPopTemp(this.ll_temp_right, false);
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.6
            @Override // java.lang.Runnable
            public void run() {
                int currPopupWindow = NavigationBarInflaterView.this.mAirPanelController.getCurrPopupWindow();
                BydLogHelper.logD("showRightHvacPopByDec currPopupWindow : " + currPopupWindow);
                if (currPopupWindow == 2) {
                    NavigationBarInflaterView.this.mAirPanelController.setNeedPlayWheelSoundEffect(playWheelSoundEffect);
                    NavigationBarInflaterView.this.mAirPanelController.reductionTemp();
                    NavigationBarInflaterView.this.mAirPanelController.setCenterIndex();
                } else if (currPopupWindow == 3) {
                    NavigationBarInflaterView.this.mAirPanelController.decWindLevel();
                }
            }
        }, 50L);
        BydLogHelper.logD("showRightHvacPopByDec mAcType : " + this.mAcType);
        int i = this.mAcType;
        if (i == 1) {
            this.mNavigationBarInflaterViewHandler.removeMessages(19);
            this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(19, 2000L);
            return;
        }
        if (i == 2) {
            int i2 = this.mWindLevel - 1;
            this.mWindLevel = i2;
            if (i2 < 1) {
                this.mWindLevel = i2 + 1;
                return;
            }
            if (i2 <= 7) {
                updateWindLevel(i2);
            }
            this.mNavigationBarInflaterViewHandler.removeMessages(20);
            this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(20, 2000L);
        }
    }

    private void showRightHvacPopByInc(final boolean playWheelSoundEffect) {
        showPopTemp(this.ll_temp_right, false);
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.NavigationBarInflaterView.7
            @Override // java.lang.Runnable
            public void run() {
                int currPopupWindow = NavigationBarInflaterView.this.mAirPanelController.getCurrPopupWindow();
                BydLogHelper.logD("showRightHvacPopByInc currPopupWindow : " + currPopupWindow);
                if (currPopupWindow == 2) {
                    NavigationBarInflaterView.this.mAirPanelController.setNeedPlayWheelSoundEffect(playWheelSoundEffect);
                    NavigationBarInflaterView.this.mAirPanelController.riseTemp();
                    NavigationBarInflaterView.this.mAirPanelController.setCenterIndex();
                } else if (currPopupWindow == 3) {
                    NavigationBarInflaterView.this.mAirPanelController.incWindLevel();
                }
            }
        }, 50L);
        BydLogHelper.logD("showRightHvacPopByInc mAcType : " + this.mAcType);
        int i = this.mAcType;
        if (i == 1) {
            this.mNavigationBarInflaterViewHandler.removeMessages(19);
            this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(19, 2000L);
            return;
        }
        if (i == 2) {
            int i2 = this.mWindLevel + 1;
            this.mWindLevel = i2;
            if (i2 > 7) {
                this.mWindLevel = i2 - 1;
                return;
            }
            if (i2 >= 1) {
                updateWindLevel(i2);
            }
            this.mNavigationBarInflaterViewHandler.removeMessages(20);
            this.mNavigationBarInflaterViewHandler.sendEmptyMessageDelayed(20, 2000L);
        }
    }

    private boolean isForbiddenForParking() {
        BydLogHelper.logD("isForbiddenForParking");
        try {
            BYDAutoDiPilotDevice bYDAutoDiPilotDevice = BYDAutoDiPilotDevice.getInstance(this.mContext);
            int autoParkigAssistStatus = bYDAutoDiPilotDevice.getAutoParkigAssistStatus();
            boolean z = autoParkigAssistStatus == 2 || autoParkigAssistStatus == 3;
            BydLogHelper.logD("isForbiddenForParking apaStatus = " + autoParkigAssistStatus);
            if (z) {
                return true;
            }
            int homeZoneParkingAssistStatus = bYDAutoDiPilotDevice.getHomeZoneParkingAssistStatus();
            BydLogHelper.logD("isForbiddenForParking hpaStatus = " + homeZoneParkingAssistStatus);
            if (homeZoneParkingAssistStatus == 3 || homeZoneParkingAssistStatus == 4 || homeZoneParkingAssistStatus == 5 || homeZoneParkingAssistStatus == 6 || homeZoneParkingAssistStatus == 7 || homeZoneParkingAssistStatus == 8 || homeZoneParkingAssistStatus == 9 || homeZoneParkingAssistStatus == 10 || homeZoneParkingAssistStatus == 11 || homeZoneParkingAssistStatus == 12 || homeZoneParkingAssistStatus == 13) {
                return true;
            }
            int automatedValetParkingStatus = bYDAutoDiPilotDevice.getAutomatedValetParkingStatus();
            BydLogHelper.logD("isForbiddenForParking avpStatus = " + automatedValetParkingStatus);
            return automatedValetParkingStatus == 4 || automatedValetParkingStatus == 5 || automatedValetParkingStatus == 8 || automatedValetParkingStatus == 9 || automatedValetParkingStatus == 10 || automatedValetParkingStatus == 11 || automatedValetParkingStatus == 12 || automatedValetParkingStatus == 13 || automatedValetParkingStatus == 14;
        } catch (Throwable th) {
            BydLogHelper.logD("isForbiddenForParking error");
            th.printStackTrace();
            return false;
        }
    }
}
