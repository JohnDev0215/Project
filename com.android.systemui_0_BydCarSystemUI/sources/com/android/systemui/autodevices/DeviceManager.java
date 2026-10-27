package com.android.systemui.autodevices;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.byd.card.utils.AppUtils;
import com.byd.card.widget.RollBenchMode;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager {
    private static final int MSG_ENTER_ENERGY_SUCCESS = 4;
    private static final int MSG_EXIT_ENERGY_TEST = 5;
    private static final int MSG_HIDE_VIEW = 6;
    private static final int MSG_ROLL_BENCH_ENTERED = 8;
    private static final int MSG_ROLL_BENCH_EXITED = 7;
    private static final int START_ENERGY_TEST = 3;
    private static final String TAG = "DeviceManager";
    private static final int WIRE_LESS_CHARGING = 1;
    private static final int WIRE_LESS_CHARGING_LR = 2;
    private static final int WITHOUT_WIRE_LESS_CHARGING = 0;
    private static DeviceManager instance;
    private BYDAutoAcDevice mBYDAutoAcDevice;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoChargingDevice mBYDAutoChargingDevice;
    private BYDAutoDiPilotDevice mBYDAutoDiPilotDevice;
    private BYDAutoEnergyDevice mBYDAutoEnergyDevice;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;
    private BYDAutoStatisticDevice mBYDAutoStatisticDevice;
    private BYDAutoStudy mBYDAutoStudy;
    private BYDAutoTestDevice mBYDAutoTestDevice;
    private BYDAutoVehicleHealthDevice mBYDAutoVehicleHealthDevice;
    private Context mContext;
    private boolean mIsFreshCarControlView;
    private volatile boolean mIsOptimizationApi;
    private int mReconnectCount;
    private int mCurrentTestChangeStatus = -1;
    private int mCurrentRollBenchStatus = -1;
    private final String IS_START_ENERGY = "persist.sys.start.energy.test";
    private final BYDAutoStudyListener autoStudyListener = new BYDAutoStudyListener() { // from class: com.android.systemui.autodevices.DeviceManager.1
        @Override // android.hardware.IBYDAutoListener
        public void isConnected(boolean isBinding) {
            super.isConnected(isBinding);
            BydLogHelper.logW(DeviceManager.TAG, "DeviceManager AbsBYDAutoBodyworkListener isConnected isBinding: " + isBinding);
        }

        @Override // android.hardware.IBYDAutoListener
        public void isOptimizationApi(boolean isOptimizationApi) {
            BydLogHelper.logW(DeviceManager.TAG, "DeviceManager AbsBYDAutoBodyworkListener isOptimizationApi: " + isOptimizationApi);
            DeviceManager.this.mIsOptimizationApi = isOptimizationApi;
            if (isOptimizationApi) {
                int powerLevel = DeviceManager.this.mBYDAutoBodyworkDevice.getPowerLevel();
                BydLogHelper.logW(DeviceManager.TAG, "DeviceManager AbsBYDAutoBodyworkListener isConnected powerLevel: " + powerLevel);
                if (powerLevel != 2 && powerLevel != 3) {
                    DeviceManager.this.isConnected = false;
                    return;
                }
                BydLogHelper.logW(DeviceManager.TAG, "AbsBYDAutoBodyworkListener isConnected refresh start: ");
                if (DeviceManager.this.mHandler.hasMessages(1)) {
                    DeviceManager.this.mHandler.removeMessages(1);
                }
                DeviceManager.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
                BydLogHelper.logW(DeviceManager.TAG, "AbsBYDAutoBodyworkListener isConnected refresh end : ");
                String str = SystemProperties.get("persist.sys.start.energy.test");
                BydLogHelper.logD("AbsBYDAutoBodyworkListener isConnected isStartEnergy : " + str);
                if ("true".equals(str)) {
                    DeviceManager.this.mHandler.removeMessages(3);
                    DeviceManager.this.mHandler.sendEmptyMessage(3);
                    return;
                }
                return;
            }
            DeviceManager.this.isConnected = false;
        }
    };
    private int lastPowerLevel = -1;
    private final int UPDATE_POWER_LEVEL = 1;
    private final int UPDATE_SERVICE_CONNECT_STATE = 2;
    private final H mHandler = new H();
    private final AbsBYDAutoBodyworkListener mAutoBodyworkListener = new AbsBYDAutoBodyworkListener() { // from class: com.android.systemui.autodevices.DeviceManager.2
        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int level) {
            super.onPowerLevelChanged(level);
            BydLogHelper.logW("AbsBYDAutoBodyworkListener", "DeviceManager AbsBYDAutoBodyworkListener onPowerLevelChanged level: " + level + " --- lastPowerLevel: " + DeviceManager.this.lastPowerLevel);
            if (DeviceManager.this.lastPowerLevel != level) {
                if (level == 2 || level == 3) {
                    if (DeviceManager.this.mHandler.hasMessages(1)) {
                        DeviceManager.this.mHandler.removeMessages(1);
                    }
                    DeviceManager.this.mHandler.sendEmptyMessageDelayed(1, 5000L);
                } else {
                    DeviceManager.this.isConnected = false;
                    DeviceManager.this.mHandler.removeMessages(1);
                }
                DeviceManager.this.lastPowerLevel = level;
                if (level == 0) {
                    String str = SystemProperties.get("persist.sys.start.energy.test");
                    BydLogHelper.logD("AbsBYDAutoBodyworkListener isStartEnergy : " + str);
                    if ("true".equals(str)) {
                        BYDAutoTestDevice.getInstance(DeviceManager.this.mContext).setEnergyTest(1);
                        return;
                    } else {
                        DeviceManager.this.mHandler.removeMessages(6);
                        DeviceManager.this.mHandler.sendEmptyMessage(6);
                        return;
                    }
                }
                if (level == 2 || level == 3) {
                    String str2 = SystemProperties.get("persist.sys.start.energy.test");
                    BydLogHelper.logD("AbsBYDAutoBodyworkListener isStartEnergy : " + str2);
                    if ("true".equals(str2)) {
                        DeviceManager.this.mHandler.removeMessages(3);
                        DeviceManager.this.mHandler.sendEmptyMessageDelayed(3, 3000L);
                    }
                }
            }
        }
    };
    private final AbsBYDAutoTestListener absBYDAutoTestListener = new AbsBYDAutoTestListener() { // from class: com.android.systemui.autodevices.DeviceManager.3
        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onEnergyTestChange(int status) {
            super.onEnergyTestChange(status);
            BydLogHelper.logW("", "onEnergyTestChange status = " + status + " , mCurrentTestChangeStatus : " + DeviceManager.this.mCurrentTestChangeStatus);
            if (status != DeviceManager.this.mCurrentTestChangeStatus) {
                DeviceManager.this.mCurrentTestChangeStatus = status;
                if (status == 0) {
                    DeviceManager.this.mHandler.removeMessages(4);
                    DeviceManager.this.mHandler.sendEmptyMessage(4);
                } else if (status == 1) {
                    DeviceManager.this.mHandler.removeMessages(5);
                    DeviceManager.this.mHandler.sendEmptyMessage(5);
                }
            }
        }
    };
    private final AbsBYDAutoEnergyListener absBYDAutoEnergyListener = new AbsBYDAutoEnergyListener() { // from class: com.android.systemui.autodevices.DeviceManager.4
        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onRollBenchModeStatusChanged(int status) {
            super.onRollBenchModeStatusChanged(status);
            BydLogHelper.logW("", "onRollBenchModeStatusChanged status: " + status + " --- mCurrentRollBenchStatus: " + DeviceManager.this.mCurrentRollBenchStatus);
            if (DeviceManager.this.mCurrentRollBenchStatus != status) {
                DeviceManager.this.mCurrentRollBenchStatus = status;
                if (status == 1) {
                    DeviceManager.this.mHandler.removeMessages(8);
                    DeviceManager.this.mHandler.sendEmptyMessage(8);
                } else if (status == 0) {
                    DeviceManager.this.mHandler.removeMessages(7);
                    DeviceManager.this.mHandler.sendEmptyMessage(7);
                }
            }
        }
    };
    private boolean isConnected = false;
    private final ArrayList<DeviceManagerListener> mCallbacks = new ArrayList<>();
    private List<String> mCarControlList = new ArrayList();
    private int mPowerOffNode = 30;

    public interface DeviceManagerListener {
        default void onRefreshCarControlView() {
        }

        default void onRefreshHvacView() {
        }

        default void onRefreshSocView() {
        }

        default void onRefreshVolumeAndBrightnessView() {
        }
    }

    static /* synthetic */ int access$408(DeviceManager deviceManager) {
        int i = deviceManager.mReconnectCount;
        deviceManager.mReconnectCount = i + 1;
        return i;
    }

    private DeviceManager() {
    }

    public static synchronized DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    public void init(Context context) {
        if (instance == null) {
            BydLogHelper.logW(TAG, "DeviceManager init instance == null return ");
            return;
        }
        BydLogHelper.logW(TAG, "DeviceManager init");
        this.mContext = context;
        this.mBYDAutoAcDevice = BYDAutoAcDevice.getInstance(context);
        this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
        this.mBYDAutoChargingDevice = BYDAutoChargingDevice.getInstance(this.mContext);
        this.mBYDAutoDiPilotDevice = BYDAutoDiPilotDevice.getInstance(this.mContext);
        this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        this.mBYDAutoStudy = BYDAutoStudy.getInstance(this.mContext);
        this.mBYDAutoVehicleHealthDevice = BYDAutoVehicleHealthDevice.getInstance(this.mContext);
        this.mBYDAutoStatisticDevice = BYDAutoStatisticDevice.getInstance(this.mContext);
        this.mBYDAutoTestDevice = BYDAutoTestDevice.getInstance(this.mContext);
        this.mBYDAutoBodyworkDevice.registerListener(this.mAutoBodyworkListener);
        this.mBYDAutoStudy.registerConnectListener(this.autoStudyListener);
        if (this.mHandler.hasMessages(2)) {
            BydLogHelper.logW(TAG, "DeviceManager  init removeMessages(UPDATE_SERVICE_CONNECT_STATE) ");
            this.mHandler.removeMessages(2);
        }
        BydLogHelper.logW(TAG, "DeviceManager  init sendEmptyMessageDelayed(UPDATE_SERVICE_CONNECT_STATE)  = " + this.mIsOptimizationApi + "  mReconnectCount = " + this.mReconnectCount);
        if (!this.mIsOptimizationApi && this.mReconnectCount < 2) {
            this.mHandler.sendEmptyMessageDelayed(2, 10000L);
        }
        this.mBYDAutoTestDevice.registerListener(this.absBYDAutoTestListener);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = BYDAutoEnergyDevice.getInstance(this.mContext);
        this.mBYDAutoEnergyDevice = bYDAutoEnergyDevice;
        bYDAutoEnergyDevice.registerListener(this.absBYDAutoEnergyListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                BydLogHelper.logW(DeviceManager.TAG, "handleMessage UPDATE_POWER_LEVEL : ");
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.autodevices.-$$Lambda$DeviceManager$H$Yrqw5RHI65eB1AXNhMJkeiq-25E
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleMessage$0$DeviceManager$H();
                    }
                });
                return;
            }
            if (msg.what == 2) {
                BydLogHelper.logW(DeviceManager.TAG, "handleMessage UPDATE_SERVICE_CONNECT_STATE mIsOptimizationApi: " + DeviceManager.this.mIsOptimizationApi + "  mReconnectCount = " + DeviceManager.this.mReconnectCount);
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.autodevices.-$$Lambda$DeviceManager$H$7KX5WlztUDd2HD6f3vIU6gLipv8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleMessage$1$DeviceManager$H();
                    }
                });
                return;
            }
            if (msg.what == 3) {
                BydLogHelper.logD("handleMessage START_ENERGY_TEST");
                BYDAutoTestDevice.getInstance(DeviceManager.this.mContext).setEnergyTest(0);
                return;
            }
            if (msg.what == 4) {
                BydLogHelper.logD("handleMessage MSG_ENTER_ENERGY_SUCCESS");
                RollBenchMode.getInstance(DeviceManager.this.mContext).updateStatus(true);
                RollBenchMode.getInstance(DeviceManager.this.mContext).showView();
            } else {
                if (msg.what == 5) {
                    BydLogHelper.logD("handleMessage MSG_EXIT_ENERGY_TEST");
                    return;
                }
                if (msg.what == 6) {
                    BydLogHelper.logD("handleMessage MSG_HIDE_VIEW");
                    RollBenchMode.getInstance(DeviceManager.this.mContext).hideView();
                    RollBenchMode.getInstance(DeviceManager.this.mContext).updateStatus(false);
                } else if (msg.what == 8) {
                    BydLogHelper.logD("handleMessage MSG_ROLL_BENCH_ENTERED setHeadlightControlMode 1");
                    BYDAutoLightDevice.getInstance(DeviceManager.this.mContext).setHeadlightControlMode(1);
                    BydLogHelper.logD("handleMessage MSG_ROLL_BENCH_ENTERED setDayTimeLightState 1");
                    BYDAutoLightDevice.getInstance(DeviceManager.this.mContext).setDayTimeLightState(1);
                }
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$DeviceManager$H() {
            DeviceManager.this.isConnected = true;
            BydLogHelper.logW(DeviceManager.TAG, "handleMessage UPDATE_POWER_LEVEL refresh start: ");
            DeviceManager.this.refreshAll();
            BydLogHelper.logW(DeviceManager.TAG, "handleMessage UPDATE_POWER_LEVEL refresh end : ");
        }

        public /* synthetic */ void lambda$handleMessage$1$DeviceManager$H() {
            if (DeviceManager.this.mIsOptimizationApi) {
                return;
            }
            DeviceManager.access$408(DeviceManager.this);
            DeviceManager deviceManager = DeviceManager.this;
            deviceManager.init(deviceManager.mContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAll() {
        BydLogHelper.logW(TAG, "refreshAll isConnected : " + this.isConnected);
        initSocDefaultValue();
        saveCarControlList();
        refreshCarControlView();
        this.mIsFreshCarControlView = true;
        refreshHvacView();
        refreshSocView();
        refreshVolumeAndBrightnessView();
    }

    private void initSocDefaultValue() {
        boolean zIsCarEV = AppUtils.isCarEV();
        if (zIsCarEV) {
            this.mPowerOffNode = 30;
        } else {
            this.mPowerOffNode = 35;
        }
        BydLogHelper.logW(TAG, "refreshAll initSocDefaultValue isCarEV : " + zIsCarEV + " , mPowerOffNode : " + this.mPowerOffNode);
        this.mBYDAutoVehicleHealthDevice.setPowerOffChargedState(this.mPowerOffNode);
    }

    public boolean isFreshCarControlView() {
        BydLogHelper.logD("isFreshCarControlView mIsFreshCarControlView : " + this.mIsFreshCarControlView);
        return this.mIsFreshCarControlView;
    }

    public boolean isConnected() {
        BydLogHelper.logD("isConnected isConnected : " + this.isConnected);
        return this.isConnected;
    }

    public void refreshHvacView() {
        BydLogHelper.logW(TAG, "refreshHvacView isConnected : " + this.isConnected);
        if (this.isConnected) {
            synchronized (this.mCallbacks) {
                Iterator<DeviceManagerListener> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onRefreshHvacView();
                }
            }
        }
    }

    public void refreshCarControlView() {
        BydLogHelper.logW(TAG, "refreshCarControlView isConnected : " + this.isConnected);
        if (this.isConnected) {
            synchronized (this.mCallbacks) {
                Iterator<DeviceManagerListener> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onRefreshCarControlView();
                }
            }
        }
    }

    public void refreshSocView() {
        BydLogHelper.logW(TAG, "refreshSocView isConnected : " + this.isConnected);
        if (this.isConnected) {
            synchronized (this.mCallbacks) {
                Iterator<DeviceManagerListener> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onRefreshSocView();
                }
            }
        }
    }

    public void refreshVolumeAndBrightnessView() {
        BydLogHelper.logW(TAG, "refreshVolumeAndBrightnessView isConnected : " + this.isConnected);
        if (this.isConnected) {
            synchronized (this.mCallbacks) {
                Iterator<DeviceManagerListener> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onRefreshVolumeAndBrightnessView();
                }
            }
        }
    }

    public List<String> getCarControlList() {
        return this.mCarControlList;
    }

    public void setCarControlList(List<String> mCarControlList) {
        this.mCarControlList = mCarControlList;
    }

    public int getPowerOffNode() {
        return this.mPowerOffNode;
    }

    public void setPowerOffNode(int mPowerOffNode) {
        this.mPowerOffNode = mPowerOffNode;
    }

    public void addCallback(DeviceManagerListener listener) {
        synchronized (this.mCallbacks) {
            if (listener != null) {
                if (!this.mCallbacks.contains(listener)) {
                    this.mCallbacks.add(listener);
                    return;
                }
            }
            BydLogHelper.logW(TAG, "addCallback listener : " + listener + " return.");
        }
    }

    public void removeCallback(DeviceManagerListener listener) {
        synchronized (this.mCallbacks) {
            if (listener == null) {
                return;
            }
            this.mCallbacks.remove(listener);
        }
    }

    public void saveCarControlList() {
        BydLogHelper.logW(TAG, "saveCarControlList isConnected : " + this.isConnected);
        if (this.isConnected) {
            if (this.mCarControlList == null) {
                this.mCarControlList = new ArrayList();
            }
            this.mCarControlList.clear();
            for (String str : this.mContext.getResources().getStringArray(R.array.car_control_items)) {
                if (configAutoStudy(str)) {
                    this.mCarControlList.add(str);
                    BydLogHelper.logD("BYDAutoStudyListener Support Auto Study item : " + str);
                }
            }
        }
    }

    public void addCarControlList(String s) {
        BydLogHelper.logD("addCarControlList isConnected : " + this.isConnected);
        if (this.isConnected) {
            if (this.mCarControlList == null) {
                this.mCarControlList = new ArrayList();
            }
            boolean zConfigAutoStudy = configAutoStudy(s);
            BydLogHelper.logD("addCarControlList s : " + s + " , configAutoStudy : " + zConfigAutoStudy + " , contains : " + this.mCarControlList.contains(s));
            if (!zConfigAutoStudy || this.mCarControlList.contains(s)) {
                return;
            }
            this.mCarControlList.add(s);
        }
    }

    public void removeCarControlList(String s) {
        BydLogHelper.logD("removeCarControlList isConnected : " + this.isConnected);
        if (this.isConnected) {
            if (this.mCarControlList == null) {
                this.mCarControlList = new ArrayList();
            }
            BydLogHelper.logD("removeCarControlList s : " + s + " , contains : " + this.mCarControlList.contains(s));
            this.mCarControlList.remove(s);
        }
    }

    public boolean configAutoStudy(String tileLabel) {
        BydLogHelper.logD("tileLabel : " + tileLabel);
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_hud))) {
            return isSupportHud();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_slw))) {
            int iHasMessage = this.mBYDAutoBodyworkDevice.hasMessage(10);
            int supportSLW = this.mBYDAutoStudy.getSupportSLW();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , hasMessage 432 state : " + iHasMessage + " , support : " + supportSLW);
            return supportSLW == 2 && iHasMessage == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_active_suspension_mode))) {
            return isSupportDynamicSuspensionMode();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_massage))) {
            return isSupportMassage();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_ventilation_heating))) {
            return isSupportVentilationHeating();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_auto_hold))) {
            int supportAVH = this.mBYDAutoStudy.getSupportAVH();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getSupportAVH : " + supportAVH);
            return supportAVH == 2;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_electric_defrosting)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_rear_defrosting))) {
            int defrostRearConfig = this.mBYDAutoAcDevice.getDefrostRearConfig();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getDefrostRearConfig : " + defrostRearConfig);
            return defrostRearConfig == 2 || defrostRearConfig == 1 || defrostRearConfig == 3;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_trunk)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_trunk_open)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_trunk_door_open))) {
            return this.mBYDAutoBodyworkDevice.getAutoType() != 143;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_skylight)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_skylight_and_sunshade)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_sunshade))) {
            int moonRoofPercentControlFlag = this.mBYDAutoBodyworkDevice.getMoonRoofPercentControlFlag();
            int moonRoofConfig = this.mBYDAutoBodyworkDevice.getMoonRoofConfig();
            BydLogHelper.logW("configAutoStudy", "moonRoofPercentControlFlag : " + moonRoofPercentControlFlag + " , getMoonRoofConfig : " + moonRoofConfig);
            boolean z = moonRoofConfig == 1 || moonRoofConfig == 2 || moonRoofConfig == 3 || moonRoofConfig == 4 || moonRoofConfig == 6 || moonRoofConfig == 7 || moonRoofConfig == 8 || moonRoofConfig == 9 || moonRoofConfig == 10 || moonRoofConfig == 11;
            if (moonRoofPercentControlFlag == 1) {
                return z;
            }
            int autoType = this.mBYDAutoBodyworkDevice.getAutoType();
            BydLogHelper.logW(TAG, "configAutoStudy", "carType : " + autoType);
            if (autoType == 111 || autoType == 112 || autoType == 71 || autoType == 121 || autoType == 115 || autoType == 129 || autoType == 130 || autoType == 128 || autoType == 135 || autoType == 127 || autoType == 126 || autoType == 125 || autoType == 60 || autoType == 57 || autoType == 152 || autoType == 136 || autoType == 137 || autoType == 74 || autoType == 90 || autoType == 147 || autoType == 196 || autoType == 182 || autoType == 208 || autoType == 143 || autoType == 175) {
                return z;
            }
            return false;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_window_lock))) {
            int electroniChildLockConfig = this.mBYDAutoSettingDevice.getElectroniChildLockConfig();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getElectroniChildLockConfig electroniChildLockConfig : " + electroniChildLockConfig);
            return electroniChildLockConfig == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_left_child_lock))) {
            int electroniChildLockConfig2 = this.mBYDAutoSettingDevice.getElectroniChildLockConfig();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getElectroniChildLockConfig leftChildLockConfig : " + electroniChildLockConfig2);
            return electroniChildLockConfig2 == 2;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_right_child_lock))) {
            int electroniChildLockConfig3 = this.mBYDAutoSettingDevice.getElectroniChildLockConfig();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getElectroniChildLockConfig rightChildLockConfig : " + electroniChildLockConfig3);
            return electroniChildLockConfig3 == 2;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_electronic_parking_brake))) {
            int iHasMessage2 = this.mBYDAutoBodyworkDevice.hasMessage(43);
            BydLogHelper.logW(TAG, "configAutoStudy", "has218 : " + iHasMessage2);
            if (iHasMessage2 != 1) {
                return false;
            }
            int ePBConfig = this.mBYDAutoVehicleHealthDevice.getEPBConfig();
            int ePBType = this.mBYDAutoVehicleHealthDevice.getEPBType();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getEPBConfig epbConfig : " + ePBConfig + " , epbType : " + ePBType);
            return ePBConfig == 1 && ePBType == 0;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_energy_feedback))) {
            return true;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_steering_wheel_heating))) {
            return isSupportSteeringWheelHeating();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_wireless_charging))) {
            int wireLessChargingType = getWireLessChargingType();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , wireLessChargingType : " + wireLessChargingType);
            return wireLessChargingType == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_wireless_charging_l)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_wireless_charging_r))) {
            int wireLessChargingType2 = getWireLessChargingType();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , wireLessChargingType : " + wireLessChargingType2);
            return wireLessChargingType2 == 2;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_door_light_auto))) {
            int doorLightSupport = this.mBYDAutoStudy.getDoorLightSupport();
            boolean zIsFirstBoot = isFirstBoot();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getDoorLightSupport isFirstBoot : " + zIsFirstBoot + " , doorLightSupport : " + doorLightSupport);
            return !zIsFirstBoot && doorLightSupport == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_hma))) {
            return isSupportAiDistanceLight();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_interior_light_on))) {
            int indoorLightSupport = this.mBYDAutoStudy.getIndoorLightSupport();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getIndoorLightSupport indoorLightOpen : " + indoorLightSupport);
            return indoorLightSupport == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_interior_light_off))) {
            int indoorLightSupport2 = this.mBYDAutoStudy.getIndoorLightSupport();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getIndoorLightSupport indoorLightClose : " + indoorLightSupport2);
            return indoorLightSupport2 == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_esp_off))) {
            return isSupportESP();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_reversing_radar))) {
            return isSupportReverseRadar();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_hill_descent_control))) {
            return isSupportSteepDescentSlowDown();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_auto_parking))) {
            int supportAutoParking = this.mBYDAutoStudy.getSupportAutoParking();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getSupportAutoParking : " + supportAutoParking);
            return supportAutoParking == 2;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_bsd))) {
            return isSupportBlindSpotAssist();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_tsr))) {
            return isSupportTrafficSignsRecognition();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_icc))) {
            return isSupportIntelligentNavigation();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_new_energy)) || TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_sound))) {
            return true;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.transport_mode))) {
            float totalMileageValue = this.mBYDAutoStatisticDevice.getTotalMileageValue();
            int transportMode = this.mBYDAutoVehicleHealthDevice.getTransportMode();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , transportMode : " + transportMode + " , totalMileageValue : " + totalMileageValue);
            return (transportMode == 1 || transportMode == 2) && totalMileageValue < 60.0f;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_rear_control_lock))) {
            int rearPanelLockTimeSupport = this.mBYDAutoStudy.getRearPanelLockTimeSupport();
            BydLogHelper.logW(TAG, "configAutoStudy", "tileLabel : " + tileLabel + " , getRearPanelLockTimeSupport : " + rearPanelLockTimeSupport);
            return rearPanelLockTimeSupport == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_driver_monitor))) {
            return isSupportDms();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_cpd))) {
            int cpdSupport = this.mBYDAutoStudy.getCpdSupport();
            BydLogHelper.logW(TAG, "configAutoStudy cpdSupport : " + cpdSupport);
            return cpdSupport == 1;
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_engine_sound_simulator))) {
            return isSupportAvas();
        }
        if (TextUtils.equals(tileLabel, this.mContext.getString(R.string.car_title_la))) {
            int intelligentSpeedLimitMapVersionDisplay = this.mBYDAutoStudy.getIntelligentSpeedLimitMapVersionDisplay();
            int supportLaneDepartureWarning = this.mBYDAutoStudy.getSupportLaneDepartureWarning();
            int supportLaneDepartureStop = this.mBYDAutoStudy.getSupportLaneDepartureStop();
            int supportEmergencyLaneKeepAssist = this.mBYDAutoStudy.getSupportEmergencyLaneKeepAssist();
            BydLogHelper.logE("configAutoStudy LA display: " + intelligentSpeedLimitMapVersionDisplay + "  -- " + supportEmergencyLaneKeepAssist + "  -- " + supportLaneDepartureWarning + "  -- " + supportLaneDepartureStop);
            if (intelligentSpeedLimitMapVersionDisplay == 1) {
                return (supportLaneDepartureWarning == 1 && supportLaneDepartureStop == 0) || supportEmergencyLaneKeepAssist == 0;
            }
            return false;
        }
        BydLogHelper.logE("configAutoStudy", "no this " + tileLabel);
        return false;
    }

    public boolean isSupportHud() {
        int iHasMessage = this.mBYDAutoBodyworkDevice.hasMessage(156);
        boolean z = Settings.System.getInt(this.mContext.getContentResolver(), "car_set_first_in", 0) == 0;
        if (z) {
            Settings.System.putInt(this.mContext.getContentResolver(), "car_set_first_in", 1);
        }
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportHud state : " + iHasMessage + " , isFirstIn : " + z);
        return !z && iHasMessage == 1;
    }

    public boolean isSupportDynamicSuspensionMode() {
        int dynamicSuspensionModeByCan = this.mBYDAutoStudy.getDynamicSuspensionModeByCan();
        int dynamicSuspensionModeByCanFD = this.mBYDAutoStudy.getDynamicSuspensionModeByCanFD();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportDynamicSuspensionMode getDynamicSuspensionModeByCan : " + dynamicSuspensionModeByCan + " , getDynamicSuspensionModeByCanFD : " + dynamicSuspensionModeByCanFD);
        return (dynamicSuspensionModeByCan == 1) || (dynamicSuspensionModeByCanFD == 1);
    }

    public boolean isSupportAiDistanceLight() {
        int supportAiDistanceLight = this.mBYDAutoStudy.getSupportAiDistanceLight();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportAiDistanceLight getSupportAiDistanceLight : " + supportAiDistanceLight);
        return supportAiDistanceLight == 1;
    }

    public boolean isSupportESP() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice;
        if (this.mBYDAutoStudy != null && (bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice) != null) {
            boolean z = bYDAutoBodyworkDevice.hasMessage(1) == 1;
            boolean z2 = this.mBYDAutoBodyworkDevice.hasMessage(19) == 1;
            BydLogHelper.logW(TAG, "configAutoStudy", "isSupportESP has40DOnline : " + z);
            if (z && z2) {
                int autoType = this.mBYDAutoBodyworkDevice.getAutoType();
                BydLogHelper.logW(TAG, "configAutoStudy", "isSupportESP carType : " + autoType);
                if (53 == autoType || 54 == autoType || 55 == autoType) {
                    return true;
                }
                boolean z3 = this.mBYDAutoBodyworkDevice.hasMessage(18) == 1;
                BydLogHelper.logW(TAG, "configAutoStudy", "isSupportESP has123Online : " + z3);
                if (69 == autoType && z3) {
                    return true;
                }
                int supportESP = this.mBYDAutoStudy.getSupportESP();
                BydLogHelper.logW(TAG, "configAutoStudy", "isSupportESP supportESP : " + supportESP);
                return supportESP == 2;
            }
        }
        return false;
    }

    public boolean isSupportReverseRadar() {
        int autoType = this.mBYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportReverseRadar carType : " + autoType);
        if (autoType == 47 || autoType == 54 || autoType == 58) {
            return false;
        }
        return (autoType == 73 && BYDAutoGearboxDevice.getInstance(this.mContext).getGearboxType() == 1) ? false : true;
    }

    public boolean isSupportSteepDescentSlowDown() {
        int autoType = this.mBYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportSteepDescentSlowDown carType : " + autoType);
        if (autoType != 68 && autoType != 73 && autoType != 88 && autoType != 89 && autoType != 90 && autoType != 110 && autoType != 134) {
            int brakePedalModeSupport = this.mBYDAutoStudy.getBrakePedalModeSupport();
            BydLogHelper.logW(TAG, "configAutoStudy", "isSupportSteepDescentSlowDown brakePedalModeSupport : " + brakePedalModeSupport);
            if (brakePedalModeSupport != 1) {
                return false;
            }
            int steepDescentSlowDownState = this.mBYDAutoDiPilotDevice.getSteepDescentSlowDownState();
            BydLogHelper.logW(TAG, "configAutoStudy", "isSupportSteepDescentSlowDown isSupportSteepDescentSlowDownState : " + steepDescentSlowDownState);
            if (steepDescentSlowDownState == 3) {
                return false;
            }
        }
        return true;
    }

    public boolean isSupportBlindSpotAssist() {
        int supportBlindSpotAssist = this.mBYDAutoStudy.getSupportBlindSpotAssist();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportBlindSpotAssist getSupportBlindSpotAssist : " + supportBlindSpotAssist);
        if (supportBlindSpotAssist == 1) {
            return true;
        }
        int iHasMessage = this.mBYDAutoBodyworkDevice.hasMessage(11);
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportBlindSpotAssist hasMessage418 : " + iHasMessage);
        return iHasMessage == 1;
    }

    public boolean isSupportTrafficSignsRecognition() {
        int supportTrafficSignsRecognition = this.mBYDAutoStudy.getSupportTrafficSignsRecognition();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportTrafficSignsRecognition getSupportTrafficSignsRecognition : " + supportTrafficSignsRecognition);
        return supportTrafficSignsRecognition == 1;
    }

    public boolean isSupportIntelligentNavigation() {
        int supportIntelligentNavigation = this.mBYDAutoStudy.getSupportIntelligentNavigation();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportIntelligentNavigation getSupportIntelligentNavigation : " + supportIntelligentNavigation);
        return supportIntelligentNavigation == 1;
    }

    public boolean isSupportDms() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.isp", "-1");
        String str2 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.name", "-1");
        String str3 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.lens", "-1");
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportDms cameraIsp = " + str + ", cameraName = " + str2 + ", cameraLens = " + str3);
        int autoType = this.mBYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportDms carType : " + autoType);
        return ((autoType != 196 && autoType != 182 && autoType != 208 && autoType != 175 && autoType != 143 && autoType != 60 && autoType != 123) || "-1".equals(str) || "-1".equals(str2) || "-1".equals(str3)) ? false : true;
    }

    public boolean isSupportAvas() {
        String countryCode = AppUtils.getCountryCode();
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.avas_country_code);
        int length = stringArray.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str = stringArray[i];
            BydLogHelper.logW(TAG, "configAutoStudy", "isSupportAvas avasCountryCode = " + str);
            if (countryCode.equals(str)) {
                z = true;
                break;
            }
            i++;
        }
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportAvas config = " + z + " --- codeCode = " + countryCode);
        return z;
    }

    public boolean isSupportMassage() {
        int supportSeatMassage = this.mBYDAutoSettingDevice.getSupportSeatMassage(1);
        int supportSeatMassage2 = this.mBYDAutoSettingDevice.getSupportSeatMassage(2);
        int supportSeatMassage3 = this.mBYDAutoSettingDevice.getSupportSeatMassage(3);
        int supportSeatMassage4 = this.mBYDAutoSettingDevice.getSupportSeatMassage(4);
        int leftRearMassageChairConfigurationForECU = this.mBYDAutoSettingDevice.getLeftRearMassageChairConfigurationForECU();
        int rightRearMassageChairConfigurationForECU = this.mBYDAutoSettingDevice.getRightRearMassageChairConfigurationForECU();
        int rearRightMassageSwConfiguration = this.mBYDAutoSettingDevice.getRearRightMassageSwConfiguration();
        int rearLeftMassageSwConfiguration = this.mBYDAutoSettingDevice.getRearLeftMassageSwConfiguration();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportMassage mainSupport : " + supportSeatMassage + ", deputySupport : " + supportSeatMassage2 + ", lrSupport : " + supportSeatMassage3 + ", rrSupport : " + supportSeatMassage4 + ", leftRearMassage : " + leftRearMassageChairConfigurationForECU + ", rightRearMassage : " + rightRearMassageChairConfigurationForECU + ", rearRightMassage : " + rearRightMassageSwConfiguration + ", rearLeftMassage : " + rearLeftMassageSwConfiguration);
        return supportSeatMassage == 1 || supportSeatMassage2 == 1 || supportSeatMassage3 == 1 || supportSeatMassage4 == 1 || leftRearMassageChairConfigurationForECU == 1 || rightRearMassageChairConfigurationForECU == 1 || rearRightMassageSwConfiguration == 1 || rearLeftMassageSwConfiguration == 1;
    }

    public boolean isSupportVentilationHeating() {
        int i = this.mBYDAutoSettingDevice.get5LevelSeatHeatingState(1);
        int i2 = this.mBYDAutoSettingDevice.get5LevelSeatHeatingState(2);
        int i3 = this.mBYDAutoSettingDevice.get5LevelSeatHeatingState(3);
        int i4 = this.mBYDAutoSettingDevice.get5LevelSeatHeatingState(4);
        int i5 = this.mBYDAutoSettingDevice.get5LevelSeatVentilatingState(1);
        int i6 = this.mBYDAutoSettingDevice.get5LevelSeatVentilatingState(2);
        int i7 = this.mBYDAutoSettingDevice.get5LevelSeatVentilatingState(3);
        int i8 = this.mBYDAutoSettingDevice.get5LevelSeatVentilatingState(4);
        int seatVentilatingState = this.mBYDAutoSettingDevice.getSeatVentilatingState(1);
        int seatVentilatingState2 = this.mBYDAutoSettingDevice.getSeatVentilatingState(2);
        int seatVentilatingState3 = this.mBYDAutoSettingDevice.getSeatVentilatingState(3);
        int seatVentilatingState4 = this.mBYDAutoSettingDevice.getSeatVentilatingState(4);
        int seatHeatingState = this.mBYDAutoSettingDevice.getSeatHeatingState(1);
        int seatHeatingState2 = this.mBYDAutoSettingDevice.getSeatHeatingState(2);
        int seatHeatingState3 = this.mBYDAutoSettingDevice.getSeatHeatingState(3);
        int seatHeatingState4 = this.mBYDAutoSettingDevice.getSeatHeatingState(4);
        int seatVentilatingState22 = this.mBYDAutoSettingDevice.getSeatVentilatingState2(3);
        int seatVentilatingState23 = this.mBYDAutoSettingDevice.getSeatVentilatingState2(4);
        int seatVentilatingNotL1PState = this.mBYDAutoSettingDevice.getSeatVentilatingNotL1PState();
        int leftPlatformConfigs = this.mBYDAutoStudy.getLeftPlatformConfigs();
        int seatHeatingState22 = this.mBYDAutoSettingDevice.getSeatHeatingState2(3);
        int seatHeatingState23 = this.mBYDAutoSettingDevice.getSeatHeatingState2(4);
        int seatHeatingNotL1PState = this.mBYDAutoSettingDevice.getSeatHeatingNotL1PState();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportVentilationHeating 五档 m5LevelDriverSeatHeatingState : " + i + ", m5LevelPassengerSeatHeatingState : " + i2 + ", m5LevelRearLeftSeatHeatingState : " + i3 + ", m5LevelRearRightSeatHeatingState : " + i4 + ", m5LevelDriverSeatVentilatingState : " + i5 + ", m5LevelPassengerSeatVentilatingState : " + i6 + ", m5LevelRearLeftSeatVentilatingState : " + i7 + ", m5LevelRearRightSeatVentilatingState : " + i8 + "; 两档 driverSeatVentilating : " + seatVentilatingState + ", passengerSeatVentilating : " + seatVentilatingState2 + ", rearLeftSeatVentilating : " + seatVentilatingState3 + ", rearRightSeatVentilating : " + seatVentilatingState4 + ", driverSeatHeating : " + seatHeatingState + ", passengerSeatHeating : " + seatHeatingState2 + ", rearLeftSeatHeating : " + seatHeatingState3 + ", rearRightSeatHeating : " + seatHeatingState4 + ", seatVentilationLeftRearState2 : " + seatVentilatingState22 + ", seatVentilationRightRearState2 : " + seatVentilatingState23 + ", copilotVentilationNotL1PState : " + seatVentilatingNotL1PState + ", leftPlatformConfigs : " + leftPlatformConfigs + ", seatWarmLeftRearState2 : " + seatHeatingState22 + ", seatWarmRightRearState2 : " + seatHeatingState23 + ", copilotWarmNotL1PState : " + seatHeatingNotL1PState);
        int i9 = seatVentilatingState + seatVentilatingState3 + seatVentilatingState4 + seatHeatingState + seatHeatingState3 + seatHeatingState4 + i + i2 + i3 + i4 + i5 + i6 + i7 + i8 + seatVentilatingState22 + seatVentilatingState23 + seatHeatingState22 + seatHeatingState23;
        return (leftPlatformConfigs == 1 ? (i9 + seatVentilatingState2) + seatHeatingState2 : (i9 + seatVentilatingNotL1PState) + seatHeatingNotL1PState) > 0;
    }

    public boolean isSupportSteeringWheelHeating() {
        int steeringWheelHeatingAutoState = this.mBYDAutoSettingDevice.getSteeringWheelHeatingAutoState();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportSteeringWheelHeating getSteeringWheelHeatingAutoState steeringWheelHeatingAutoState : " + steeringWheelHeatingAutoState);
        int steeringWheelHeatingState = this.mBYDAutoSettingDevice.getSteeringWheelHeatingState();
        BydLogHelper.logW(TAG, "configAutoStudy", "isSupportSteeringWheelHeating getSteeringWheelHeatingState steeringWheelHeatingState : " + steeringWheelHeatingState);
        return steeringWheelHeatingState == 1 || steeringWheelHeatingState == 2 || steeringWheelHeatingAutoState == 1 || steeringWheelHeatingAutoState == 2;
    }

    public boolean isCarEV() {
        int autoType = this.mBYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(TAG, "configAutoStudy", "isCarEV : " + autoType);
        switch (autoType) {
            case 0:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
            case 224:
                return true;
            default:
                return false;
        }
    }

    private boolean isFirstBoot() {
        boolean z = Settings.System.getInt(this.mContext.getContentResolver(), "is_first_boot", 0) == 0;
        if (z) {
            Settings.System.putInt(this.mContext.getContentResolver(), "is_first_boot", 1);
        }
        return z;
    }

    private int getWireLessChargingType() {
        int wirelessChargingSwitchState = this.mBYDAutoChargingDevice.getWirelessChargingSwitchState();
        int wirelessChargingFRSwitchState = this.mBYDAutoChargingDevice.getWirelessChargingFRSwitchState();
        BydLogHelper.logW(TAG, "getWireLessChargingType", "wirelessChargingSwitchState : " + wirelessChargingSwitchState + " , wirelessChargingFRSwitchState : " + wirelessChargingFRSwitchState);
        int i = ((wirelessChargingSwitchState != 1 && wirelessChargingSwitchState != 2) || wirelessChargingFRSwitchState == 1 || wirelessChargingFRSwitchState == 2) ? 0 : 1;
        if ((wirelessChargingSwitchState == 1 || wirelessChargingSwitchState == 2) && (wirelessChargingFRSwitchState == 1 || wirelessChargingFRSwitchState == 2)) {
            return 2;
        }
        return i;
    }
}
