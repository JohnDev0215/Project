package com.android.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.media.AudioAttributes;
import android.media.AudioFocusInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.qs.QSPanelContract;
import com.android.systemui.qs.tileimpl.QSTile;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.statusbar.datas.Icons;
import com.android.systemui.statusbar.phone.BlurViewController;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.BrightnessController;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.VolumeController;
import com.android.systemui.util.InterestingConfigChanges;
import com.android.systemui.util.SharedPreferencesUtil;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.util.ViewSoundEffectUtil;
import com.android.systemui.widget.BydSwitch;
import com.android.systemui.widget.SeekBarContainer;
import com.android.systemui.widget.SocSeekbar;
import com.android.systemui.widget.TransportModeDialog;
import com.byd.card.utils.AppUtils;
import com.byd.profile.ToastManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QuickQSPanel extends LinearLayout implements View.OnClickListener, View.OnLongClickListener, QSPanelContract.View {
    private static final int MSG_VIEW_TOAST = 7;
    private static final int SEND_DASHBOARD_BRIGHTNESS = 3;
    private static final int SPRING_BACK_TIME = 2000;
    private static final String TAG = "QuickQSPanel";
    private static final int UPDATE_DASHBOARD_BRIGHTNESS = 4;
    private static final int UPDATE_SOC_PROCESS_MSG = 1;
    private static final int UPDATE_SOC_TARGET_LOW_LIMIT = 6;
    private static final int UPDATE_SOC_TYPE = 2;
    private static final int UPDATE_TRANSPORT_MODE_STATE = 5;
    public static final int category_label_type_car = 1;
    public static final String category_label_type_key = "category_label_type";
    public static final int category_label_type_qs = 0;
    public static final int category_label_type_scene = 2;
    private RecyclerView carRV;
    private String category_label_car;
    private String category_label_qs;
    private String category_label_scene;
    private ImageView imgCarEdit;
    private ImageView imgQsEdit;
    private ImageView imgSceneModeEdit;
    private ImageView img_drawer_mask;
    private ImageView img_drawer_overlay;
    private boolean isMoving;
    private final AbsBYDAutoInstrumentListener mAbsBYDAutoInstrumentListener;
    private final AbsBYDAutoGearboxListener mAutoGearboxListener;
    private BYDAutoInstrumentDevice mBYDAutoInstrumentDevice;
    private BYDAutoStudy mBYDAutoStudy;
    private BlurViewController mBlurViewController;
    protected final BrightnessCallback mBrightnessCallback;
    private final BrightnessController mBrightnessController;
    private QSPanelAdapter mCarAdapter;
    protected final CarControlCallback mCarControlCallback;
    private final CarController mCarController;
    List<QSTile> mCarUnselectedList;
    private final DeviceManager mDeviceManager;
    private int mDmType;
    private final H mHandler;
    private boolean mIsCarEV;
    private boolean mIsFirstUpdateStreamValue;
    private int mLastMediaStreamValue;
    private int mLastNavigationStreamValue;
    private int mLastVoiceStreamValue;
    private View mLayoutContent;
    protected LayoutInflater mLayoutInflater;
    private final InterestingConfigChanges mLocaleConfigChanges;
    private int mLowLimit;
    private int mMandatoryElectricState;
    private int mOrientation;
    private QSPanelAdapter mQsAdapter;
    private int[] mRange;
    private RelativeLayout mRlSocSeekbar;
    private int mSOCTarget;
    private QSPanelAdapter mSceneAdapter;
    private SeekBarContainer mSeekBarContainerCentralControl;
    private SeekBarContainer mSeekBarContainerDashboard;
    private SeekBarContainer mSeekBarContainerMedia;
    private SeekBarContainer mSeekBarContainerNavigation;
    private SeekBarContainer mSeekBarContainerVoice;
    private int mSocRange;
    private SocSeekbar mSocSeekbar;
    private BydSwitch mSocSwitch;
    private final StatusBar mStatusBarComponent;
    private RelativeLayout mSysUiQsPanelContent;
    private boolean mTracking;
    private TextView mTvSocEnd;
    private TextView mTvSocStart;
    private TextView mTvSocValue;
    protected final VolumeControlCallback mVolumeControlCallback;
    private VolumeController mVolumeController;
    private QSPanelPresenter presenter;
    private RecyclerView qsRV;
    private RelativeLayout rlSoc;
    private RelativeLayout rl_car;
    private RelativeLayout rl_qs;
    private RelativeLayout rl_scene_mode;
    private RecyclerView sceneModeRV;
    private int socProcessMax;
    private int socProcessMin;
    private TextView tvCarName;
    private TextView tvQsName;
    private TextView tvSceneMode;

    @Override // com.android.systemui.qs.QSPanelContract.View
    public String getCategoryLabelCar() {
        return Constants.TileId.CATEGORY_ID_CAR_SELECT;
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public String getCategoryLabelQs() {
        return Constants.TileId.CATEGORY_ID_LINK_SELECT;
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public String getCategoryLabelScene() {
        return Constants.TileId.CATEGORY_ID_SCENE_SELECT;
    }

    public ImageView getImgDrawerMask() {
        return this.img_drawer_mask;
    }

    public ImageView getImgDrawerOverlay() {
        return this.img_drawer_overlay;
    }

    public QuickQSPanel(Context context) {
        this(context, null);
    }

    public QuickQSPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickQSPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCarUnselectedList = new ArrayList();
        this.mHandler = new H();
        this.mRange = new int[2];
        this.mIsFirstUpdateStreamValue = true;
        this.mCarControlCallback = new CarControlCallback();
        this.mBrightnessCallback = new BrightnessCallback();
        this.mVolumeControlCallback = new VolumeControlCallback();
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges(4);
        this.mLocaleConfigChanges = interestingConfigChanges;
        AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener = new AbsBYDAutoInstrumentListener() { // from class: com.android.systemui.qs.QuickQSPanel.10
            @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
            public void onMasterVolumeChanged(int area, int value) {
                super.onMasterVolumeChanged(area, value);
                int muteState = QuickQSPanel.this.mBYDAutoInstrumentDevice.getMuteState(area);
                BydLogHelper.logD("armrest volume area = " + area + " ,value = " + value + " ,muteValue = " + muteState);
                if (value != 63) {
                    if (muteState == 7 && value == 0) {
                        BydLogHelper.logD("ignore default value");
                        return;
                    }
                    boolean z = muteState == 1;
                    BydLogHelper.logD("armrest volume isMute = " + z);
                    if (value < 0 || value > 39) {
                        return;
                    }
                    if (value > 0) {
                        QuickQSPanel.this.mVolumeController.setAllMute(false);
                    }
                    if (value == 0 && z) {
                        return;
                    }
                    QuickQSPanel.this.mVolumeController.setGroupVolume(QuickQSPanel.this.mVolumeController.getVolumeGroupIdForUsage(1), value, 1);
                }
            }

            @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
            public void onMuteStateChanged(int area, int state) {
                super.onMuteStateChanged(area, state);
                BydLogHelper.logD("muteState area = " + area + " ,state = " + state);
                if (state == 7) {
                    int groupVolume = QuickQSPanel.this.mVolumeController.getGroupVolume(QuickQSPanel.this.mVolumeController.getVolumeGroupIdForUsage(1));
                    BydLogHelper.logD("setMasterVolume mediaVolume = " + groupVolume);
                    QuickQSPanel.this.mBYDAutoInstrumentDevice.setMasterVolume(groupVolume);
                    boolean allMute = QuickQSPanel.this.mVolumeController.getAllMute();
                    BydLogHelper.logD("setMuteState isAllMute = " + allMute);
                    QuickQSPanel.this.mBYDAutoInstrumentDevice.setMuteState(allMute ? 1 : 2);
                    return;
                }
                boolean z = state == 1;
                BydLogHelper.logD("muteState isMute = " + z);
                if (z) {
                    QuickQSPanel.this.mVolumeController.setVolumeGroupMute(0, QuickQSPanel.this.mVolumeController.getVolumeGroupIdForUsage(1), true, 1);
                }
            }
        };
        this.mAbsBYDAutoInstrumentListener = absBYDAutoInstrumentListener;
        this.mAutoGearboxListener = new AbsBYDAutoGearboxListener() { // from class: com.android.systemui.qs.QuickQSPanel.11
            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearChanged(int value) {
                boolean zIsCarEV = AppUtils.isCarEV();
                Log.d("AbsBYDAutoGearboxListener", "onGearChanged value = " + value + ", isCarEv = " + zIsCarEV + ", " + (zIsCarEV ? "handle!" : "skip!"));
                if (zIsCarEV) {
                    QuickQSPanel.this.closeIfRGear(value);
                }
            }

            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearboxAutoModeLevelChanged(int level) {
                boolean zIsCarEV = AppUtils.isCarEV();
                Log.d("AbsBYDAutoGearboxListener", "onGearboxAutoModeLevelChanged level = " + level + ", isCarEv = " + zIsCarEV + ", " + (zIsCarEV ? "skip!" : "handle!"));
                if (zIsCarEV) {
                    return;
                }
                QuickQSPanel.this.closeIfRGear(level);
            }

            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearboxAutoModeTypeChanged(int level) {
                super.onGearboxAutoModeTypeChanged(level);
            }
        };
        interestingConfigChanges.applyNewConfig(this.mContext.getResources());
        createInflaters();
        this.mStatusBarComponent = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        this.mCarController = (CarController) Dependency.get(CarController.class);
        this.mBrightnessController = (BrightnessController) Dependency.get(BrightnessController.class);
        this.mVolumeController = (VolumeController) Dependency.get(VolumeController.class);
        this.mDeviceManager = DeviceManager.getInstance();
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(this.mContext);
        this.mBYDAutoInstrumentDevice = bYDAutoInstrumentDevice;
        bYDAutoInstrumentDevice.registerListener(absBYDAutoInstrumentListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.mStatusBarComponent.cancelAutoHide();
        } else if (action == 1 || action == 3 || action == 4) {
            this.mStatusBarComponent.scheduleAutoHide();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        QSPanelPresenter qSPanelPresenter = new QSPanelPresenter();
        this.presenter = qSPanelPresenter;
        qSPanelPresenter.attachView((QSPanelContract.View) this);
        this.category_label_qs = getContext().getString(R.string.qs_link_name) + getContext().getString(R.string.SPLIT) + getContext().getString(R.string.label_selected);
        this.category_label_car = getContext().getString(R.string.car_control_name) + getContext().getString(R.string.SPLIT) + getContext().getString(R.string.label_selected);
        this.category_label_scene = getContext().getString(R.string.scene_mode_name) + getContext().getString(R.string.SPLIT) + getContext().getString(R.string.label_selected);
        this.mBYDAutoStudy = BYDAutoStudy.getInstance(this.mStatusBarComponent.mContext);
        inflateChildren();
        initView();
        initData();
        this.mBlurViewController = (BlurViewController) Dependency.get(BlurViewController.class);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BydLogHelper.logD("onAttachedToWindow");
        this.mCarController.addCallback(this.mCarControlCallback);
        this.mBrightnessController.addCallback(this.mBrightnessCallback);
        this.mVolumeController.addCallback(this.mVolumeControlCallback, this.mHandler);
        this.mVolumeController.getState();
        BYDAutoGearboxDevice.getInstance(getContext()).registerListener(this.mAutoGearboxListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.presenter.detachView();
        BydLogHelper.logD("onDetachedFromWindow");
        BYDAutoGearboxDevice.getInstance(getContext()).unregisterListener(this.mAutoGearboxListener);
        this.mCarController.removeCallback(this.mCarControlCallback);
        this.mBrightnessController.removeCallback(this.mBrightnessCallback);
        this.mVolumeController.removeCallback(this.mVolumeControlCallback);
        this.mQsAdapter.releaseList();
        this.mSceneAdapter.releaseList();
        this.mCarAdapter.releaseList();
        this.presenter = null;
        removeAllViews();
    }

    private void createInflaters() {
        this.mLayoutInflater = LayoutInflater.from(getContext());
    }

    private void inflateChildren() {
        removeAllViews();
        if (AppUtils.isRightRudder()) {
            this.mLayoutContent = this.mLayoutInflater.inflate(StyleUtil.getResLayout(R.layout.sysui_qs_panel_content_right_rudder, "sysui_qs_panel_content_right_rudder"), (ViewGroup) this, false);
        } else {
            this.mLayoutContent = this.mLayoutInflater.inflate(StyleUtil.getResLayout(R.layout.sysui_qs_panel_content, "sysui_qs_panel_content"), (ViewGroup) this, false);
        }
        this.mLayoutContent.setId(R.id.sysui_qs_panel_content);
        addView(this.mLayoutContent);
    }

    private void initQs() {
        RelativeLayout relativeLayout = (RelativeLayout) this.mLayoutContent.findViewById(R.id.rl_qs);
        this.rl_qs = relativeLayout;
        this.tvQsName = (TextView) relativeLayout.findViewById(R.id.tv_name);
        ImageView imageView = (ImageView) this.rl_qs.findViewById(R.id.img_edit);
        this.imgQsEdit = imageView;
        imageView.setId(R.id.sysui_qs_img);
        RecyclerView recyclerView = (RecyclerView) this.rl_qs.findViewById(R.id.rv_qs);
        this.qsRV = recyclerView;
        recyclerView.setId(R.id.sysui_qs_rv);
        this.tvQsName.setText(getResources().getString(R.string.qs_link_name));
        this.imgQsEdit.setOnClickListener(this);
        this.mQsAdapter = new QSPanelAdapter(this.category_label_qs, getCategoryLabelQs());
        this.qsRV.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.panel_count_qs)));
        this.qsRV.setAdapter(this.mQsAdapter);
    }

    private void initCar() {
        RelativeLayout relativeLayout = (RelativeLayout) this.mLayoutContent.findViewById(R.id.rl_car);
        this.rl_car = relativeLayout;
        this.tvCarName = (TextView) relativeLayout.findViewById(R.id.tv_name);
        ImageView imageView = (ImageView) this.rl_car.findViewById(R.id.img_edit);
        this.imgCarEdit = imageView;
        imageView.setId(R.id.sysui_car_img);
        RecyclerView recyclerView = (RecyclerView) this.rl_car.findViewById(R.id.rv_qs);
        this.carRV = recyclerView;
        recyclerView.setId(R.id.sysui_car_rv);
        this.tvCarName.setText(getResources().getString(R.string.car_control_name));
        this.imgCarEdit.setOnClickListener(this);
        this.mCarAdapter = new QSPanelAdapter(this.category_label_car, getCategoryLabelCar());
        this.carRV.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.panel_count_qs)));
        this.carRV.setAdapter(this.mCarAdapter);
    }

    private void initSceneMode() {
        this.rl_scene_mode = (RelativeLayout) this.mLayoutContent.findViewById(R.id.rl_scene_mode);
        this.mSceneAdapter = new QSPanelAdapter(this.category_label_scene, getCategoryLabelScene());
        this.rl_scene_mode.setVisibility(8);
    }

    public void initView() {
        initQs();
        initCar();
        initSceneMode();
        initVolumeAndBrightness();
        initSoc();
        this.mSysUiQsPanelContent = (RelativeLayout) this.mLayoutContent.findViewById(R.id.sysui_qs_panel_content);
        this.img_drawer_mask = (ImageView) this.mLayoutContent.findViewById(R.id.img_drawer_mask);
        this.img_drawer_overlay = (ImageView) this.mLayoutContent.findViewById(R.id.img_drawer_overlay);
        this.mSysUiQsPanelContent.setOnClickListener(this);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        SeekBarContainer seekBarContainer;
        super.onVisibilityChanged(changedView, visibility);
        BydLogHelper.logD("onVisibilityChanged visibility = " + visibility);
        this.mQsAdapter.update(visibility == 0);
        if (visibility != 4 || (seekBarContainer = this.mSeekBarContainerVoice) == null) {
            return;
        }
        seekBarContainer.setProgressViewVisible(8);
        this.mSeekBarContainerVoice.removeMessageUpdateProgress();
        this.mSeekBarContainerMedia.setProgressViewVisible(8);
        this.mSeekBarContainerMedia.removeMessageUpdateProgress();
        this.mSeekBarContainerNavigation.setProgressViewVisible(8);
        this.mSeekBarContainerNavigation.removeMessageUpdateProgress();
        this.mSeekBarContainerCentralControl.setProgressViewVisible(8);
        this.mSeekBarContainerCentralControl.removeMessageUpdateProgress();
        this.mSeekBarContainerDashboard.setProgressViewVisible(8);
        this.mSeekBarContainerDashboard.removeMessageUpdateProgress();
    }

    private void initVolumeAndBrightness() {
        AudioAttributes attributes;
        this.mSeekBarContainerVoice = (SeekBarContainer) this.mLayoutContent.findViewById(R.id.sysui_qs_seekbar_voice);
        this.mSeekBarContainerMedia = (SeekBarContainer) this.mLayoutContent.findViewById(R.id.sysui_qs_seekbar_media);
        this.mSeekBarContainerNavigation = (SeekBarContainer) this.mLayoutContent.findViewById(R.id.sysui_qs_seekbar_navigation);
        this.mSeekBarContainerCentralControl = (SeekBarContainer) this.mLayoutContent.findViewById(R.id.sysui_qs_seekbar_central_control);
        this.mSeekBarContainerDashboard = (SeekBarContainer) this.mLayoutContent.findViewById(R.id.sysui_qs_seekbar_dashboard);
        this.mSeekBarContainerVoice.initSeekBar(5, 24, R.string.voice, Icons.VOICE_VOL_QS_ICONS);
        this.mSeekBarContainerMedia.initSeekBar(0, 39, R.string.media, Icons.MUSIC_VOL_QS_ICONS);
        this.mSeekBarContainerNavigation.initSeekBar(0, 10, R.string.navigation, Icons.NAVI_VOL_QS_ICONS);
        this.mSeekBarContainerCentralControl.initSeekBar(this.mBrightnessController.getMinimumBackLight(), 255, R.string.center_control, Icons.CENTRAL_BRIGHTNESS_QS_ICONS_NIGHT);
        VolumeController volumeController = this.mVolumeController;
        if (volumeController != null) {
            if (volumeController.getAllMute()) {
                this.mSeekBarContainerVoice.setSeekBarProgress(5);
                this.mSeekBarContainerMedia.setSeekBarProgress(0);
                this.mSeekBarContainerNavigation.setSeekBarProgress(0);
            } else {
                this.mSeekBarContainerVoice.setSeekBarProgress(this.mVolumeController.getVoiceVolume());
                this.mSeekBarContainerMedia.setSeekBarProgress(this.mVolumeController.getMediaVolume());
                this.mSeekBarContainerNavigation.setSeekBarProgress(this.mVolumeController.getNaviVolume());
            }
        }
        List<AudioFocusInfo> curAudioFocusInfos = this.mVolumeController.getCurAudioFocusInfos();
        if (curAudioFocusInfos != null && curAudioFocusInfos.size() > 0) {
            for (AudioFocusInfo audioFocusInfo : curAudioFocusInfos) {
                if (audioFocusInfo != null && (attributes = audioFocusInfo.getAttributes()) != null) {
                    int usage = attributes.getUsage();
                    BydLogHelper.logD("getCurAudioFocusInfo  curUsage：" + usage);
                    if (usage == 16) {
                        showVoiceBar(true);
                        return;
                    }
                }
            }
        }
        showVoiceBar(false);
        this.mSeekBarContainerVoice.setProgressCallback(new SeekBarContainer.ProgressChangedCallback() { // from class: com.android.systemui.qs.QuickQSPanel.1
            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logW(QuickQSPanel.TAG, "mSeekBarContainerVoice onProgressChanged: " + progress + " , fromUser : " + fromUser);
                QuickQSPanel.this.mVolumeController.setGroupVolume(QuickQSPanel.this.mVolumeController.getVolumeGroupIdForUsage(16), progress, 0);
                if (QuickQSPanel.this.mVolumeController.getAllMute()) {
                    QuickQSPanel.this.mVolumeController.setAllMute(false);
                }
            }
        });
        this.mSeekBarContainerMedia.setProgressCallback(new SeekBarContainer.ProgressChangedCallback() { // from class: com.android.systemui.qs.QuickQSPanel.2
            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logW(QuickQSPanel.TAG, "mSeekBarContainerMedia onProgressChanged: " + progress + " , fromUser : " + fromUser);
                if (fromUser) {
                    QuickQSPanel.this.mVolumeController.setStreamVolume(3, progress);
                    if (QuickQSPanel.this.mVolumeController.getAllMute()) {
                        QuickQSPanel.this.mVolumeController.setAllMute(false);
                    }
                    if (QuickQSPanel.this.mVolumeController.getStreamMute(3)) {
                        QuickQSPanel.this.mVolumeController.setStreamMute(3, false);
                    }
                }
            }
        });
        this.mSeekBarContainerNavigation.setProgressCallback(new SeekBarContainer.ProgressChangedCallback() { // from class: com.android.systemui.qs.QuickQSPanel.3
            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logW(QuickQSPanel.TAG, "mSeekBarContainerNavigation onProgressChanged: " + progress + " , fromUser : " + fromUser);
                if (fromUser) {
                    QuickQSPanel.this.mVolumeController.setStreamVolume(14, progress);
                    if (QuickQSPanel.this.mVolumeController.getAllMute()) {
                        QuickQSPanel.this.mVolumeController.setAllMute(false);
                    }
                    if (QuickQSPanel.this.mVolumeController.getStreamMute(14)) {
                        QuickQSPanel.this.mVolumeController.setStreamMute(14, false);
                    }
                }
            }
        });
        this.mSeekBarContainerCentralControl.setProgressCallback(new SeekBarContainer.ProgressChangedCallback() { // from class: com.android.systemui.qs.QuickQSPanel.4
            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logW(QuickQSPanel.TAG, "mSeekBarContainerCentralControl onProgressChanged: " + progress + " , fromUser : " + fromUser);
                if (fromUser) {
                    QuickQSPanel.this.mBrightnessController.setValue(progress, false, true);
                }
            }
        });
        this.mSeekBarContainerDashboard.setProgressCallback(new SeekBarContainer.ProgressChangedCallback() { // from class: com.android.systemui.qs.QuickQSPanel.5
            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logW(QuickQSPanel.TAG, "mSeekBarContainerDashboard onProgressChanged progress:" + progress + " , fromUser : " + fromUser);
                if (fromUser) {
                    Message message = new Message();
                    message.what = 3;
                    message.arg1 = progress;
                    QuickQSPanel.this.mHandler.removeMessages(3);
                    QuickQSPanel.this.mHandler.sendMessageDelayed(message, 300L);
                }
            }

            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onStopTrackingTouch(SeekBar seekBar) {
                BydLogHelper.logW(QuickQSPanel.TAG, "setInstrumentBrightness progress = " + seekBar.getProgress());
                QuickQSPanel.this.mCarController.setInstrumentBrightness(seekBar.getProgress());
                QuickQSPanel.this.mHandler.removeMessages(3);
                QuickQSPanel.this.mHandler.removeMessages(4);
                QuickQSPanel.this.mHandler.sendEmptyMessageDelayed(4, 2000L);
            }

            @Override // com.android.systemui.widget.SeekBarContainer.ProgressChangedCallback
            public void onStartTrackingTouch(SeekBar seekBar) {
                BydLogHelper.logW(QuickQSPanel.TAG, "mSeekBarContainerDashboard onStartTrackingTouch progress : " + seekBar.getProgress());
            }
        });
    }

    private void initSoc() {
        this.rlSoc = (RelativeLayout) this.mLayoutContent.findViewById(R.id.rlSoc);
        this.mSocSwitch = (BydSwitch) this.mLayoutContent.findViewById(R.id.switch_soc);
        this.mTvSocValue = (TextView) this.mLayoutContent.findViewById(R.id.tv_soc_value);
        this.mRlSocSeekbar = (RelativeLayout) this.mLayoutContent.findViewById(R.id.rl_soc_seekbar);
        this.mTvSocStart = (TextView) this.mLayoutContent.findViewById(R.id.tv_soc_start);
        this.mTvSocEnd = (TextView) this.mLayoutContent.findViewById(R.id.tv_soc_end);
        this.mSocSeekbar = (SocSeekbar) this.mLayoutContent.findViewById(R.id.seekbar_soc);
        this.mSocSwitch.setOnInterceptTouchListener(new BydSwitch.OnInterceptTouchListener() { // from class: com.android.systemui.qs.QuickQSPanel.6
            @Override // com.android.systemui.widget.BydSwitch.OnInterceptTouchListener
            public void OnBydSwitchTouch(View v, MotionEvent event, boolean isChecked) {
                if (v.isEnabled()) {
                    BydLogHelper.logD("onCheckedChanged isChecked " + isChecked);
                    ViewSoundEffectUtil.playViewSound(v, 0);
                    QuickQSPanel.this.mHandler.removeMessages(2);
                    QuickQSPanel.this.presenter.setMandatoryElectricState(isChecked ? 2 : 1);
                    QuickQSPanel.this.updateMandatoryElectric(isChecked ? 2 : 1);
                    QuickQSPanel.this.mHandler.sendEmptyMessageDelayed(2, 2000L);
                }
            }
        });
        this.mSocSeekbar.setOnSeekBarChangeListener(new SocSeekbar.OnSeekBarChangeListener() { // from class: com.android.systemui.qs.QuickQSPanel.7
            @Override // com.android.systemui.widget.SocSeekbar.OnSeekBarChangeListener
            public void onStartTrackingTouch(float progress) {
                QuickQSPanel.this.isMoving = true;
                BydLogHelper.logD("onStartTrackingTouch progress = " + progress);
                QuickQSPanel.this.mTvSocValue.setText(Math.round(((QuickQSPanel.this.mRange[1] - QuickQSPanel.this.mRange[0]) * progress) + QuickQSPanel.this.mRange[0]) + "%");
                if (QuickQSPanel.this.mSocSwitch.isChecked()) {
                    return;
                }
                QuickQSPanel.this.mHandler.removeMessages(2);
                QuickQSPanel.this.presenter.setMandatoryElectricState(2);
                QuickQSPanel.this.updateMandatoryElectric(2);
                QuickQSPanel.this.mHandler.sendEmptyMessageDelayed(2, 2000L);
            }

            @Override // com.android.systemui.widget.SocSeekbar.OnSeekBarChangeListener
            public void onStopTrackingTouch(float progress) {
                QuickQSPanel.this.isMoving = false;
                int iRound = Math.round(((QuickQSPanel.this.mRange[1] - QuickQSPanel.this.mRange[0]) * progress) + QuickQSPanel.this.mRange[0]);
                BydLogHelper.logD("onStopTrackingTouch progress = " + progress + "  --  textProgress = " + iRound);
                QuickQSPanel.this.presenter.setSOCTarget(iRound);
                QuickQSPanel.this.mHandler.removeMessages(1);
                QuickQSPanel.this.mHandler.sendEmptyMessageDelayed(1, 2000L);
            }

            @Override // com.android.systemui.widget.SocSeekbar.OnSeekBarChangeListener
            public void onProgressChanged(float progress) {
                BydLogHelper.logD("onProgressChanged progress = " + progress);
                QuickQSPanel.this.mTvSocValue.setText(Math.round(((QuickQSPanel.this.mRange[1] - QuickQSPanel.this.mRange[0]) * progress) + QuickQSPanel.this.mRange[0]) + "%");
            }

            @Override // com.android.systemui.widget.SocSeekbar.OnSeekBarChangeListener
            public void onProgressLimit() {
                BydLogHelper.logD("onProgressLimit  = ");
                if (QuickQSPanel.this.mHandler.hasMessages(7)) {
                    return;
                }
                ToastManager.show(QuickQSPanel.this.getContext(), R.string.tip_ensure_vehicle_performance);
                QuickQSPanel.this.mHandler.sendEmptyMessageDelayed(7, 3000L);
            }
        });
    }

    public void initData() {
        this.presenter.initData();
    }

    public void goneMask() {
        this.img_drawer_mask.setVisibility(8);
        this.img_drawer_overlay.setVisibility(8);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        view.getId();
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sysui_car_img /* 2131296915 */:
                BydLogHelper.logD("onClick", "show car edit activity");
                toEditActivity(1);
                break;
            case R.id.sysui_qs_img /* 2131296949 */:
                toEditActivity(0);
                break;
            case R.id.sysui_qs_panel_content /* 2131296950 */:
                this.mStatusBarComponent.closePanelsIfNeed();
                break;
            case R.id.sysui_scene_mode_img /* 2131296960 */:
                toEditActivity(2);
                break;
        }
    }

    private void toEditActivity(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(category_label_type_key, type);
        this.mStatusBarComponent.showQSEdit(bundle);
    }

    private void executeAnimation(View view, float toX, float toY) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, toX, 1.0f, toY, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(100L);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(2);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(scaleAnimation);
    }

    private static int getImpliedLevel(SeekBar seekBar, int progress) {
        int max = seekBar.getMax();
        int i = max / 100;
        int i2 = i - 1;
        if (progress == 0) {
            return 0;
        }
        return progress == max ? i : ((int) ((progress / max) * i2)) + 1;
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void refreshQSList(List<QSTile> selected, boolean fromEdit) {
        if (fromEdit) {
            QSPanelAdapter qSPanelAdapter = new QSPanelAdapter(this.category_label_qs, getCategoryLabelQs());
            this.mQsAdapter = qSPanelAdapter;
            this.qsRV.setAdapter(qSPanelAdapter);
        }
        this.mQsAdapter.refreshList(selected);
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void refreshCarSelectList(List<QSTile> selected, boolean fromEdit) {
        if (fromEdit) {
            QSPanelAdapter qSPanelAdapter = new QSPanelAdapter(this.category_label_car, getCategoryLabelCar());
            this.mCarAdapter = qSPanelAdapter;
            this.carRV.setAdapter(qSPanelAdapter);
        }
        this.mCarAdapter.refreshList(selected);
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void refreshCarUnselectedList(List<QSTile> selected, boolean fromEdit) {
        this.mCarUnselectedList = selected;
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void refreshSceneModeList(List<QSTile> selected) {
        this.mSceneAdapter.refreshList(selected);
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void updateSocTargetChanged(final int value) {
        BydLogHelper.logD("updateSocTargetChanged", "value = " + value);
        if (this.mHandler.hasMessages(1) || this.isMoving) {
            return;
        }
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.-$$Lambda$QuickQSPanel$LM2LfBcyQTwzJCttMF-3GdF443c
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateSocTargetChanged$0$QuickQSPanel(value);
            }
        });
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void updateSOCTargetLowLimitChanged(final int value) {
        BydLogHelper.logD("updateSOCTargetLowLimitChanged", "value = " + value);
        if (this.mHandler.hasMessages(6)) {
            return;
        }
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.-$$Lambda$QuickQSPanel$Wf2PTp-f96U6czSP8nRz9lt36eA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateSOCTargetLowLimitChanged$1$QuickQSPanel(value);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSOCTargetLowLimit, reason: merged with bridge method [inline-methods] */
    public void lambda$updateSOCTargetLowLimitChanged$1$QuickQSPanel(int value) {
        BydLogHelper.logD("updateSOCTargetLowLimit", "value = " + value);
        initProgress(this.mSocSwitch.isChecked(), this.mRange[0], value, this.presenter.getSOCTarget(), this.mRange[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateProgress, reason: merged with bridge method [inline-methods] */
    public void lambda$updateSocTargetChanged$0$QuickQSPanel(int value) {
        BydLogHelper.logD("updateProgress", "greater socTarget = " + value);
        if (this.isMoving) {
            return;
        }
        this.mTvSocValue.setText(value + "%");
        initProgress(this.mSocSwitch.isChecked(), this.mRange[0], this.presenter.getSocTargetLowLimit(), this.presenter.getSOCTarget(), this.mRange[1]);
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void updateMandatoryElectricStateChanged(final int value) {
        BydLogHelper.logD("updateMandatoryElectricStateChanged", "value = " + value);
        if (this.mHandler.hasMessages(2)) {
            return;
        }
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.QuickQSPanel.8
            @Override // java.lang.Runnable
            public void run() {
                QuickQSPanel.this.updateMandatoryElectric(value);
            }
        });
    }

    public boolean isMandatoryElectricConfig() {
        boolean z = this.presenter.hasMessage(20) == 1;
        boolean z2 = !DeviceManager.getInstance().isCarEV();
        int dMType = this.mBYDAutoStudy.getDMType();
        boolean z3 = z && (dMType == 3 || dMType == 2);
        BydLogHelper.logD("isMandatoryElectricConfig", "isOnline34F = " + z + " ---- isOnline464 = " + z2 + " ---- dmType = " + dMType + " ---- is34F = " + z3);
        return z3 && z2;
    }

    private int[] getSocRange() {
        int i;
        int[] iArr = {25, 70};
        if (!(!this.mIsCarEV) || (i = this.mSocRange) == 0 || i == 15) {
            int i2 = this.mDmType;
            if (i2 == 3) {
                iArr[0] = 15;
            } else if (i2 == 2) {
                iArr[0] = 25;
            }
        } else if (i == 1) {
            iArr[0] = -1;
        } else if (i == 2) {
            iArr[0] = 25;
        } else if (i == 3) {
            iArr[0] = 15;
        } else if (i == 4) {
            iArr[0] = 20;
        } else if (i == 5) {
            iArr[0] = 18;
        } else if (i == 6) {
            iArr[0] = 22;
        } else if (i == 7) {
            iArr[0] = 10;
        } else {
            iArr[0] = 25;
        }
        return iArr;
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void refreshSoc() {
        if (isMandatoryElectricConfig()) {
            this.rlSoc.setVisibility(0);
            this.mDmType = this.mBYDAutoStudy.getDMType();
            this.mIsCarEV = DeviceManager.getInstance().isCarEV();
            this.mSocRange = this.presenter.getSocTargetRange();
            this.mLowLimit = this.presenter.getSocTargetLowLimit();
            this.mSOCTarget = this.presenter.getSOCTarget();
            this.mMandatoryElectricState = this.presenter.getMandatoryElectricState();
            BydLogHelper.logD("refreshSoc", "dmType = " + this.mDmType + " , isCarEV : " + this.mIsCarEV + " , mSocRange : " + this.mSocRange + " , mLowLimit : " + this.mLowLimit + " , mSOCTarget : " + this.mSOCTarget);
            this.mRange = getSocRange();
            this.mTvSocStart.setText(this.mRange[0] + "%");
            this.mTvSocEnd.setText(this.mRange[1] + "%");
            BydLogHelper.logD("refreshSoc", "mandatoryElectricState = " + this.mMandatoryElectricState);
            updateMandatoryElectric(this.mMandatoryElectricState);
            lambda$updateSocTargetChanged$0$QuickQSPanel(this.mSOCTarget);
            return;
        }
        this.rlSoc.setVisibility(8);
    }

    private void initProgress(boolean isCheck, int min, int lowLimitTemp, int targetTemp, int max) {
        int i;
        int i2 = lowLimitTemp;
        int iMax = targetTemp;
        BydLogHelper.logI(TAG, "[initProgress]----original data: min = " + min + ",lowLimit = " + i2 + ",target = " + iMax + ",max = " + max);
        if (iMax > max) {
            iMax = max;
        } else if (iMax < min) {
            iMax = min;
        }
        if (i2 < min) {
            i2 = min;
        } else if (i2 > max) {
            i2 = max;
        }
        if (iMax < min) {
            iMax = min;
        }
        if (iMax > max) {
            iMax = max;
        }
        if (isCheck) {
            i = 0;
            iMax = Math.max(i2, iMax);
        } else {
            if (iMax < i2) {
                i2 = iMax;
            }
            i = 2;
        }
        int i3 = i;
        BydLogHelper.logI(TAG, "[initProgress]----handle data: min = " + min + ",lowLimit = " + i2 + ",target = " + iMax + ",max = " + max);
        int i4 = max - min;
        float f = i4;
        float f2 = ((i2 - min) * 1.0f) / f;
        float f3 = ((iMax - i2) * 1.0f) / f;
        float f4 = (1.0f - f2) - f3;
        BydLogHelper.logI(TAG, "[initProgress]----progressOne = " + f2 + ",progressTwo = " + f3 + ",progressThree = " + f4);
        this.mTvSocValue.setText(iMax + "%");
        this.mSocSeekbar.setProgress(i3, f2, f3, f4, i4);
        this.mSocSeekbar.postInvalidate();
    }

    @Override // com.android.systemui.qs.QSPanelContract.View
    public void refreshVolumeAndBrightness() {
        int instrumentHeightSupport = this.mBYDAutoStudy.getInstrumentHeightSupport();
        BydLogHelper.logD("refreshVolumeAndBrightness instrumentHeightSupport:" + instrumentHeightSupport);
        int backlightLevels = this.mCarController.getBacklightLevels();
        BydLogHelper.logD("refreshVolumeAndBrightness backlightLevels:" + backlightLevels);
        if (backlightLevels == 1) {
            this.mSeekBarContainerCentralControl.initSeekBar(this.mBrightnessController.getMinimumBackLight(), 255, R.string.center_control, Icons.CENTRAL_BRIGHTNESS_QS_ICONS_NIGHT);
            this.mSeekBarContainerDashboard.initSeekBar(1, 11, R.string.dashboard, Icons.METER_BRIGHTNESS_QS_ICONS_NIGHT);
        } else {
            this.mSeekBarContainerCentralControl.initSeekBar(this.mBrightnessController.getMinimumBackLight(), 255, R.string.center_control, Icons.CENTRAL_BRIGHTNESS_QS_ICONS);
            this.mSeekBarContainerDashboard.initSeekBar(1, 11, R.string.dashboard, Icons.METER_BRIGHTNESS_QS_ICONS);
        }
        if (instrumentHeightSupport == 1) {
            this.mSeekBarContainerDashboard.setVisibility(0);
            this.mLayoutContent.findViewById(R.id.space3).setVisibility(0);
            int instrumentBrightness = this.mCarController.getInstrumentBrightness();
            BydLogHelper.logD("refreshVolumeAndBrightness instrumentBrightness:" + instrumentBrightness);
            this.mSeekBarContainerDashboard.setSeekBarProgress(instrumentBrightness);
            return;
        }
        this.mSeekBarContainerDashboard.setVisibility(8);
        this.mLayoutContent.findViewById(R.id.space3).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMandatoryElectric(int value) {
        BydLogHelper.logD("updateMandatoryElectric value : " + value);
        this.mTvSocStart.setAlpha(value == 2 ? 1.0f : 0.45f);
        this.mTvSocEnd.setAlpha(value == 2 ? 1.0f : 0.45f);
        this.mRlSocSeekbar.setAlpha(value != 2 ? 0.45f : 1.0f);
        this.mSocSwitch.setChecked(value == 2, false);
        BydLogHelper.logD("updateMandatoryElectric isMoving : " + this.isMoving);
        if (!this.isMoving && !this.mHandler.hasMessages(1)) {
            initProgress(this.mSocSwitch.isChecked(), this.mRange[0], this.presenter.getSocTargetLowLimit(), this.presenter.getSOCTarget(), this.mRange[1]);
        } else {
            this.mSocSeekbar.setViewType(this.mSocSwitch.isChecked() ? 0 : 2);
        }
    }

    protected final class CarControlCallback implements CarController.Callback {
        protected CarControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onInstrumentBrightnessChanged(final int brightnessValue) {
            if (!QuickQSPanel.this.mHandler.hasMessages(3) && !QuickQSPanel.this.mHandler.hasMessages(4)) {
                BydLogHelper.logD("onInstrumentBrightnessChanged value : " + brightnessValue + " , cntProgress : " + QuickQSPanel.this.mSeekBarContainerDashboard.getSeekBarProgress());
                if (brightnessValue > 11) {
                    brightnessValue -= 11;
                }
                if (brightnessValue < 1 || brightnessValue > 11) {
                    return;
                }
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.QuickQSPanel.CarControlCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onInstrumentBrightnessChanged brightnessValue : " + brightnessValue);
                        QuickQSPanel.this.mSeekBarContainerDashboard.setSeekBarProgress(brightnessValue);
                    }
                });
                return;
            }
            BydLogHelper.logD("onInstrumentBrightnessChanged hasMessages : " + QuickQSPanel.this.mHandler.hasMessages(3) + " ----- " + QuickQSPanel.this.mHandler.hasMessages(4));
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onBacklightLevelsChanged(final int level) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.QuickQSPanel.CarControlCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    if (level == 1) {
                        QuickQSPanel.this.mSeekBarContainerCentralControl.setImgResAll(Icons.CENTRAL_BRIGHTNESS_QS_ICONS_NIGHT);
                        QuickQSPanel.this.mSeekBarContainerDashboard.setImgResAll(Icons.METER_BRIGHTNESS_QS_ICONS_NIGHT);
                    } else {
                        QuickQSPanel.this.mSeekBarContainerCentralControl.setImgResAll(Icons.CENTRAL_BRIGHTNESS_QS_ICONS);
                        QuickQSPanel.this.mSeekBarContainerDashboard.setImgResAll(Icons.METER_BRIGHTNESS_QS_ICONS);
                    }
                    int intForUser = Settings.System.getIntForUser(QuickQSPanel.this.getContext().getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, ((PowerManager) QuickQSPanel.this.getContext().getSystemService(PowerManager.class)).getDefaultScreenBrightnessSetting(), -2);
                    BydLogHelper.logD("onBacklightLevelsChanged screenBrightness:" + intForUser);
                    QuickQSPanel.this.mSeekBarContainerCentralControl.setSeekBarProgress(intForUser);
                    int instrumentBrightness = QuickQSPanel.this.mCarController.getInstrumentBrightness();
                    BydLogHelper.logD("onBacklightLevelsChanged instrumentBrightness:" + instrumentBrightness);
                    QuickQSPanel.this.mSeekBarContainerDashboard.setSeekBarProgress(instrumentBrightness);
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onTransportModeChanged(int state) {
            BydLogHelper.logD("onTransportModeChanged state : " + state);
            QuickQSPanel.this.mHandler.removeMessages(5);
            Message message = new Message();
            message.what = 5;
            message.arg1 = state;
            QuickQSPanel.this.mHandler.sendMessageDelayed(message, 100L);
        }
    }

    protected final class BrightnessCallback implements BrightnessController.Callback {
        protected BrightnessCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.BrightnessController.Callback
        public void onBrightnessValueChanged(final int value, int max, int min) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.-$$Lambda$QuickQSPanel$BrightnessCallback$NINTkQRZI19xf4vpcZMQA_S4Bvs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onBrightnessValueChanged$0$QuickQSPanel$BrightnessCallback(value);
                }
            });
        }

        public /* synthetic */ void lambda$onBrightnessValueChanged$0$QuickQSPanel$BrightnessCallback(int i) {
            int i2 = (int) (((i <= 0 || i >= 3) ? i : 3) / 2.55f);
            BydLogHelper.logD(QuickQSPanel.TAG, "onBrightnessValueChanged: " + i + " , brightnessValue : " + i2);
            QuickQSPanel.this.mSeekBarContainerCentralControl.setSeekBarProgress(i);
            QuickQSPanel.this.mSeekBarContainerCentralControl.setSeekBarText(i2);
        }
    }

    protected final class VolumeControlCallback implements VolumeController.Callbacks {
        protected VolumeControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void onStateChanged(boolean showUI, VolumeController.State state, boolean isFromMute, int flags) {
            for (int i = 0; i < state.states.size(); i++) {
                int iKeyAt = state.states.keyAt(i);
                VolumeController.StreamState streamState = state.states.get(iKeyAt);
                BydLogHelper.logD(QuickQSPanel.TAG, "onStateChanged state = " + iKeyAt + " , muted = " + streamState.muted + " , level = " + streamState.level + " , levelMin = " + streamState.levelMin + " , levelMax = " + streamState.levelMax);
                if (iKeyAt == 3) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        QuickQSPanel.this.mSeekBarContainerMedia.setSeekBarProgressMute(streamState.levelMin);
                    } else {
                        QuickQSPanel.this.mSeekBarContainerMedia.setSeekBarProgress(streamState.level);
                    }
                    QuickQSPanel.this.mBYDAutoInstrumentDevice.setMasterVolume(streamState.level);
                    if (!QuickQSPanel.this.mIsFirstUpdateStreamValue && streamState.level != QuickQSPanel.this.mLastMediaStreamValue) {
                        QuickQSPanel.this.mSeekBarContainerMedia.setSeekBarProgressValueVisible();
                    }
                    QuickQSPanel.this.mLastMediaStreamValue = streamState.level;
                } else if (iKeyAt == 14) {
                    if (streamState.muted) {
                        QuickQSPanel.this.mSeekBarContainerNavigation.setSeekBarProgressMute(streamState.levelMin);
                    } else {
                        QuickQSPanel.this.mSeekBarContainerNavigation.setSeekBarProgress(streamState.level);
                    }
                    if (!QuickQSPanel.this.mIsFirstUpdateStreamValue && streamState.level != QuickQSPanel.this.mLastNavigationStreamValue) {
                        QuickQSPanel.this.mSeekBarContainerNavigation.setSeekBarProgressValueVisible();
                    }
                    QuickQSPanel.this.mLastNavigationStreamValue = streamState.level;
                } else if (iKeyAt == 16) {
                    if (streamState.muted) {
                        QuickQSPanel.this.mSeekBarContainerVoice.setSeekBarProgressMute(streamState.levelMin);
                    } else {
                        QuickQSPanel.this.mSeekBarContainerVoice.setSeekBarProgress(streamState.level);
                    }
                    if (!QuickQSPanel.this.mIsFirstUpdateStreamValue && streamState.level != QuickQSPanel.this.mLastVoiceStreamValue) {
                        QuickQSPanel.this.mSeekBarContainerVoice.setSeekBarProgressValueVisible();
                    }
                    QuickQSPanel.this.mLastVoiceStreamValue = streamState.level;
                }
            }
            boolean allMute = QuickQSPanel.this.mVolumeController.getAllMute();
            BydLogHelper.logD(QuickQSPanel.TAG, "isAllMute = " + allMute);
            QuickQSPanel.this.mBYDAutoInstrumentDevice.setMuteState(allMute ? 1 : 2);
            QuickQSPanel.this.mIsFirstUpdateStreamValue = false;
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void voiceBarVisibleChange(boolean show) {
            QuickQSPanel.this.showVoiceBar(show);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVoiceBar(boolean show) {
        if (show) {
            SeekBarContainer seekBarContainer = this.mSeekBarContainerVoice;
            if (seekBarContainer != null && seekBarContainer.getVisibility() != 0) {
                this.mSeekBarContainerVoice.setVisibility(0);
            }
            SeekBarContainer seekBarContainer2 = this.mSeekBarContainerMedia;
            if (seekBarContainer2 == null || seekBarContainer2.getVisibility() == 8) {
                return;
            }
            this.mSeekBarContainerMedia.setVisibility(8);
            return;
        }
        SeekBarContainer seekBarContainer3 = this.mSeekBarContainerMedia;
        if (seekBarContainer3 != null && seekBarContainer3.getVisibility() != 0) {
            this.mSeekBarContainerMedia.setVisibility(0);
        }
        SeekBarContainer seekBarContainer4 = this.mSeekBarContainerVoice;
        if (seekBarContainer4 == null || seekBarContainer4.getVisibility() == 8) {
            return;
        }
        this.mSeekBarContainerVoice.setVisibility(8);
    }

    private final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                int sOCTarget = QuickQSPanel.this.presenter.getSOCTarget();
                BydLogHelper.logD("handleMessage", "socTarget = " + sOCTarget);
                QuickQSPanel.this.lambda$updateSocTargetChanged$0$QuickQSPanel(sOCTarget);
                return;
            }
            if (msg.what == 2) {
                int mandatoryElectricState = QuickQSPanel.this.presenter.getMandatoryElectricState();
                BydLogHelper.logD("handleMessage", "mandatoryElectricState = " + mandatoryElectricState);
                QuickQSPanel.this.updateMandatoryElectric(mandatoryElectricState);
                return;
            }
            if (msg.what == 6) {
                int socTargetLowLimit = QuickQSPanel.this.presenter.getSocTargetLowLimit();
                BydLogHelper.logD("handleMessage", "socTargetLowLimit = " + socTargetLowLimit);
                QuickQSPanel.this.lambda$updateSOCTargetLowLimitChanged$1$QuickQSPanel(socTargetLowLimit);
            } else if (msg.what == 3) {
                BydLogHelper.logD("handleMessage", "setInstrumentBrightness progress = " + msg.arg1);
                QuickQSPanel.this.mCarController.setInstrumentBrightness(msg.arg1);
            } else if (msg.what == 4) {
                int instrumentBrightness = QuickQSPanel.this.mCarController.getInstrumentBrightness();
                BydLogHelper.logD("handleMessage", "UPDATE_DASHBOARD_BRIGHTNESS instrumentBrightness = " + instrumentBrightness);
                QuickQSPanel.this.mSeekBarContainerDashboard.setSeekBarProgress(instrumentBrightness);
            } else if (msg.what == 5) {
                int i = msg.arg1;
                BydLogHelper.logD("handleMessage state : " + i);
                QuickQSPanel.this.updateTransportModeState(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTransportModeState(final int state) {
        BydLogHelper.logD("updateTransportModeState state : " + state);
        if (state == 3 || state == 2 || state == 1) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.QuickQSPanel.9
                @Override // java.lang.Runnable
                public void run() {
                    boolean z;
                    String string = QuickQSPanel.this.mStatusBarComponent.mContext.getString(R.string.transport_mode);
                    List<QSTile> arrayList = new ArrayList<>();
                    if (QuickQSPanel.this.mCarAdapter != null) {
                        arrayList = QuickQSPanel.this.mCarAdapter.getData();
                    }
                    int i = 0;
                    while (true) {
                        if (i >= arrayList.size()) {
                            z = false;
                            break;
                        } else {
                            if (arrayList.get(i).getTileLabel().equals(string)) {
                                z = true;
                                break;
                            }
                            i++;
                        }
                    }
                    if (!z) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= QuickQSPanel.this.mCarUnselectedList.size()) {
                                break;
                            }
                            if (QuickQSPanel.this.mCarUnselectedList.get(i2).getTileLabel().equals(string)) {
                                z = true;
                                break;
                            }
                            i2++;
                        }
                    }
                    BydLogHelper.logD("updateTransportModeState", "isContainTransportMode = " + z);
                    if (state == 3 && z && DeviceManager.getInstance().isFreshCarControlView()) {
                        DeviceManager.getInstance().removeCarControlList(string);
                        QuickQSPanel.this.presenter.refreshCarList();
                    } else {
                        int i3 = state;
                        if (i3 == 1 || i3 == 2) {
                            if (!z && DeviceManager.getInstance().isFreshCarControlView()) {
                                DeviceManager.getInstance().addCarControlList(string);
                                QuickQSPanel.this.presenter.refreshCarList();
                            }
                            if (state == 1) {
                                boolean z2 = SharedPreferencesUtil.getBoolean(QuickQSPanel.this.getContext(), Constants.SET_TRANSPORT_MODE_BY_USER, false);
                                BydLogHelper.logD("updateTransportModeState", "showTransportDialog = " + z2);
                                if (z2) {
                                    new TransportModeDialog(QuickQSPanel.this.mContext).show();
                                }
                            }
                        }
                    }
                    SharedPreferencesUtil.putBoolean(QuickQSPanel.this.getContext(), Constants.SET_TRANSPORT_MODE_BY_USER, false);
                }
            });
        }
    }

    private void updateSeekBarScales(int process) {
        int i = this.socProcessMin;
        if (i != 15 && i == 25) {
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mLocaleConfigChanges.applyNewConfig(this.mContext.getResources())) {
            BydLogHelper.logD("onConfigurationChanged new language:" + this.mContext.getResources().getConfiguration().getLocales().get(0).getLanguage());
            DeviceManager.getInstance().saveCarControlList();
            QSIconConstants.initQSIcon();
            DividerUtils.getInstance().startLoadAppAsyncTask(this.mContext);
        }
        this.mQsAdapter.releaseList();
        this.mSceneAdapter.releaseList();
        this.mCarAdapter.releaseList();
        removeAllViews();
        inflateChildren();
        initView();
        this.presenter.initData();
        this.mSeekBarContainerCentralControl.setSeekBarProgress(Settings.System.getIntForUser(getContext().getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, ((PowerManager) getContext().getSystemService(PowerManager.class)).getDefaultScreenBrightnessSetting(), -2));
        if (this.mBlurViewController.getBlurBitmap() != null) {
            getImgDrawerMask().setImageBitmap(this.mBlurViewController.getBlurBitmap());
            getImgDrawerMask().setVisibility(0);
            getImgDrawerOverlay().setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeIfRGear(final int gear) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.-$$Lambda$QuickQSPanel$tzwX0WEBKACH5UMmKCmwzmrwDOY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$closeIfRGear$2$QuickQSPanel(gear);
            }
        });
    }

    public /* synthetic */ void lambda$closeIfRGear$2$QuickQSPanel(int i) {
        if (i == 2) {
            this.mStatusBarComponent.closePanelsIfNeed();
        }
    }
}
