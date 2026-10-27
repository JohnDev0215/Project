package android.hardware.bydauto.ac.tsimpl;

import a.a.e.a;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.util.SparseArray;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ACManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_ACManagerImpl";
    private static final String TAG = "ACManagerImpl";
    private static final int TEST_VALUE = 100;
    private static final int TWO = 2;
    private static volatile ACManagerImpl sInstance;
    private AbsBYDAutoAcListener mAbsBydAutoAcListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private a mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 100;
    private final CopyOnWriteArrayList<AbsBYDAutoAcListener> mAbsBydAutoAcListeners = new CopyOnWriteArrayList<>();
    private int mOnline = 0;
    private int mAirOutletOnline = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    ACManagerImpl(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.ac.tsimpl.ACManagerImpl.1
            public void onHvacChanged(int i, int i2, HvacPacket hvacPacket) {
                if (ACManagerImpl.this.mAbsBydAutoAcListeners.isEmpty()) {
                    return;
                }
                ACManagerImpl.this.hvacCallback(i, i2, hvacPacket);
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static ACManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ACManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new ACManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int i, int i2, HvacPacket hvacPacket) {
        int i3;
        if (hvacPacket == null) {
            Log.e(TAG, "hvacCallback is null");
        }
        Log.d(TAG, "hvacCallback propertyId:" + i + "hvacPacket" + hvacPacket.toString());
        switch (i) {
            case 557846528:
                int temperatureUnit = hvacPacket.getTemperatureUnit();
                Log.d(TAG, "getTemperatureUnit value is: " + temperatureUnit);
                i3 = temperatureUnit == 0 ? 1 : 0;
                Iterator<AbsBYDAutoAcListener> it = this.mAbsBydAutoAcListeners.iterator();
                while (it.hasNext()) {
                    it.next().onTemperatureUnitChanged(i3);
                }
                break;
            case HvacHalId.ID_AC_REAR_WIND_LEVEL /* 557846532 */:
                Iterator<AbsBYDAutoAcListener> it2 = this.mAbsBydAutoAcListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onRearAcMaxWindLevelChanged(hvacPacket.getAcRearAcWindLevel());
                }
                break;
            case HvacHalId.ID_AC_REAR_CTRL_MODE /* 557846547 */:
                int acCtrlMode = hvacPacket.getAcCtrlMode();
                Log.d(TAG, "ID_AC_REAR_CTRL_MODE value is: " + acCtrlMode);
                if (1 == acCtrlMode) {
                    i = 1;
                } else if (2 == acCtrlMode) {
                    i = 0;
                } else if (acCtrlMode != 0) {
                    i = acCtrlMode;
                }
                Iterator<AbsBYDAutoAcListener> it3 = this.mAbsBydAutoAcListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onAcCtrlModeChanged(i);
                }
                break;
            case HvacHalId.ID_AC_DEFROST /* 557846549 */:
                int acDefrost = hvacPacket.getAcDefrost();
                Log.d(TAG, "getAcDefrostState value is: " + acDefrost);
                if (acDefrost == 1) {
                    i = 0;
                } else if (acDefrost == 2) {
                    i = 1;
                } else if (acDefrost != 0) {
                    i = acDefrost;
                }
                Iterator<AbsBYDAutoAcListener> it4 = this.mAbsBydAutoAcListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onAcDefrostStateChanged(1, i);
                }
                break;
            case HvacHalId.ID_AC_COMPRESSOR_MODE /* 557846561 */:
                int acRequestRight = hvacPacket.getAcRequestRight();
                Log.d(TAG, "ID_AC_COMPRESSOR_MODE value is:" + acRequestRight);
                i3 = acRequestRight != 0 ? acRequestRight == 1 ? 1 : acRequestRight : 0;
                Iterator<AbsBYDAutoAcListener> it5 = this.mAbsBydAutoAcListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onAcCompressorModeChanged(i3);
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_DRIVER_TEMP_SET /* 557846563 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_MAIN is: " + hvacPacket.getAcControllerDriverTemp());
                    absBYDAutoAcListener.onTemperatureChanged(1, hvacPacket.getAcControllerDriverTemp());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_WIND_LEVEL /* 557846567 */:
                Iterator<AbsBYDAutoAcListener> it6 = this.mAbsBydAutoAcListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onAcWindLevelChanged(hvacPacket.getAcFrontWindLevelR());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_WIND_MODE /* 557846569 */:
                Iterator<AbsBYDAutoAcListener> it7 = this.mAbsBydAutoAcListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onAcWindModeChanged(hvacPacket.getAcWindMode());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_MAX_COLD_STATE /* 557846571 */:
                int acMaxCold = hvacPacket.getAcMaxCold();
                Log.d(TAG, "ID_AC_CONTROLLER_MAX_COLD_STATE value is: " + acMaxCold);
                i = acMaxCold != 0 ? acMaxCold == 2 ? 0 : acMaxCold : -1;
                Iterator<AbsBYDAutoAcListener> it8 = this.mAbsBydAutoAcListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onAcMaxCoolingStateChanged(i);
                }
                caseFeatureIds(3388, Integer.valueOf(i));
                break;
            case HvacHalId.ID_AC_REAR_PANEL_LOCK /* 557846576 */:
                Iterator<AbsBYDAutoAcListener> it9 = this.mAbsBydAutoAcListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onAcRearPanelLockStateChanged(hvacPacket.getAcRearPanelLock());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_AC_CTRL_MODE /* 557846578 */:
                int acRearAcCtrlMode = hvacPacket.getAcRearAcCtrlMode();
                Log.d(TAG, "ID_AC_CONTROLLER_REAR_AC_CTRL_MODE value is: " + acRearAcCtrlMode);
                if (2 == acRearAcCtrlMode) {
                    i = 0;
                } else if (acRearAcCtrlMode != 0) {
                    i = acRearAcCtrlMode;
                }
                Iterator<AbsBYDAutoAcListener> it10 = this.mAbsBydAutoAcListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onRearAcCtrlModeChanged(i);
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_STATE /* 557846580 */:
                int acRearAcState = hvacPacket.getAcRearAcState();
                for (AbsBYDAutoAcListener absBYDAutoAcListener2 : this.mAbsBydAutoAcListeners) {
                    if (acRearAcState == 1) {
                        absBYDAutoAcListener2.onAcRearStarted();
                    } else if (acRearAcState == 2) {
                        absBYDAutoAcListener2.onAcRearStoped();
                    }
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_WIND_MODE /* 557846582 */:
                Iterator<AbsBYDAutoAcListener> it11 = this.mAbsBydAutoAcListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onRearAcWindModeChanged(hvacPacket.getAcRearWindMode());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_TEMP /* 557846584 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener3 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_REAR is: " + hvacPacket.getAcRearTemp());
                    absBYDAutoAcListener3.onTemperatureChanged(3, hvacPacket.getAcRearTemp());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_WIND_LEVEL /* 557846586 */:
                Iterator<AbsBYDAutoAcListener> it12 = this.mAbsBydAutoAcListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onRearAcWindLevelChanged(hvacPacket.getAcRearWindLevel());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_LOCK /* 557846588 */:
                Iterator<AbsBYDAutoAcListener> it13 = this.mAbsBydAutoAcListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onRearAcKeyLockStateChanged(hvacPacket.getAcRearLock());
                }
                caseFeatureIds(3477, Integer.valueOf(hvacPacket.getAcRearLock()));
                break;
            case HvacHalId.ID_AC_PTC_PREHEAT_SIGNALR /* 557846601 */:
                Iterator<AbsBYDAutoAcListener> it14 = this.mAbsBydAutoAcListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onAcPtcPreheatSignalChanged(hvacPacket.getAcPtcPreheatSignal());
                }
                break;
            case HvacHalId.ID_AC_TEMP_DEPUTY /* 557846603 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener4 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_DEPUTY is: " + hvacPacket.getAcTempratureDeputy());
                    absBYDAutoAcListener4.onTemperatureChanged(2, hvacPacket.getAcTempratureDeputy());
                }
                break;
            case 557856063:
                Iterator<AbsBYDAutoAcListener> it15 = this.mAbsBydAutoAcListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onAcRemoteCtrlTimeChanged(hvacPacket.getAcRemoteCtrlTimeR());
                }
                break;
            default:
                switch (i) {
                    case HvacHalId.ID_AC_FAULT_NUM_SHOWN_STATE /* 557846542 */:
                        Iterator<AbsBYDAutoAcListener> it16 = this.mAbsBydAutoAcListeners.iterator();
                        while (it16.hasNext()) {
                            it16.next().onAcFaultNumShownStateChanged(hvacPacket.getAcFaultNumShownState());
                        }
                        break;
                    case HvacHalId.ID_AC_TEMPCTRL_SEPARATE_STATE /* 557846543 */:
                        int acTempCtrlSeparateState = hvacPacket.getAcTempCtrlSeparateState();
                        Log.d(TAG, "ID_AC_TEMPCTRL_SEPARATE_STATE value is: " + acTempCtrlSeparateState);
                        i = acTempCtrlSeparateState != 0 ? acTempCtrlSeparateState : -1;
                        i3 = i != 1 ? i : 0;
                        int i4 = i3 != 2 ? i3 : 1;
                        Iterator<AbsBYDAutoAcListener> it17 = this.mAbsBydAutoAcListeners.iterator();
                        while (it17.hasNext()) {
                            it17.next().onAcTemperatureControlModeChanged(i4);
                        }
                        break;
                    default:
                        switch (i) {
                            case HvacHalId.ID_AC_OPEN_STATE /* 557846552 */:
                                int acOpenState = hvacPacket.getAcOpenState();
                                for (AbsBYDAutoAcListener absBYDAutoAcListener5 : this.mAbsBydAutoAcListeners) {
                                    if (acOpenState == 1) {
                                        absBYDAutoAcListener5.onAcStarted();
                                    } else if (acOpenState == 2) {
                                        absBYDAutoAcListener5.onAcStoped();
                                    }
                                }
                                break;
                            case HvacHalId.ID_AC_CIRCLE_MODE /* 557846553 */:
                                int acCircleMode = hvacPacket.getAcCircleMode();
                                Log.d(TAG, "getAcCycleMode value is: " + acCircleMode);
                                if (1 == acCircleMode) {
                                    i = 0;
                                } else if (2 == acCircleMode) {
                                    i = 1;
                                } else if (acCircleMode != 0) {
                                    i = acCircleMode;
                                }
                                Iterator<AbsBYDAutoAcListener> it18 = this.mAbsBydAutoAcListeners.iterator();
                                while (it18.hasNext()) {
                                    it18.next().onAcCycleModeChanged(i);
                                }
                                break;
                            default:
                                switch (i) {
                                    case HvacHalId.ID_AC_HEAT_MODE_FLAG /* 557846557 */:
                                        Iterator<AbsBYDAutoAcListener> it19 = this.mAbsBydAutoAcListeners.iterator();
                                        while (it19.hasNext()) {
                                            it19.next().onAcWarmTypeOnlineStateChanged(hvacPacket.getAcHeatModeFlag());
                                        }
                                        break;
                                    case HvacHalId.ID_AC_HEAT_PUMP_COMPRESSOR_MODE /* 557846558 */:
                                        int acHeatPumpCompressorMode = hvacPacket.getAcHeatPumpCompressorMode();
                                        Log.d(TAG, "ID_AC_HEAT_PUMP_COMPRESSOR_MODE value is:" + acHeatPumpCompressorMode);
                                        Iterator<AbsBYDAutoAcListener> it20 = this.mAbsBydAutoAcListeners.iterator();
                                        while (it20.hasNext()) {
                                            it20.next().onHeatPumpCompressorWorkModeChanged(acHeatPumpCompressorMode);
                                        }
                                        break;
                                    case HvacHalId.ID_AC_NATURAL_WIND_STATE /* 557846559 */:
                                        int acNaturalWind = hvacPacket.getAcNaturalWind();
                                        Log.d(TAG, "ID_AC_NATURAL_WIND_STATE value is " + acNaturalWind);
                                        if (acNaturalWind == 1) {
                                            i = 1;
                                        } else if (acNaturalWind == 2) {
                                            i = 0;
                                        } else if (acNaturalWind != 0) {
                                            i = acNaturalWind;
                                        }
                                        Iterator<AbsBYDAutoAcListener> it21 = this.mAbsBydAutoAcListeners.iterator();
                                        while (it21.hasNext()) {
                                            it21.next().onAcVentilationStateChanged(i);
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case HvacHalId.ID_AC_COMPRESSOR_MANUAL_SIGN /* 557846590 */:
                                                int acCompressorManualSign = hvacPacket.getAcCompressorManualSign();
                                                Log.d(TAG, "ID_AC_COMPRESSOR_MANUAL_SIGN value is: " + acCompressorManualSign);
                                                if (acCompressorManualSign == 0) {
                                                    i = 0;
                                                } else if (acCompressorManualSign == 1) {
                                                    i = 1;
                                                }
                                                Iterator<AbsBYDAutoAcListener> it22 = this.mAbsBydAutoAcListeners.iterator();
                                                while (it22.hasNext()) {
                                                    it22.next().onAcCompressorManualSignChanged(i);
                                                }
                                                break;
                                            case HvacHalId.ID_AC_WIND_LEVEL_MANUAL_SIGN /* 557846591 */:
                                                int acWindLevelManualSign = hvacPacket.getAcWindLevelManualSign();
                                                Log.d(TAG, "ID_AC_WIND_LEVEL_MANUAL_SIGN value is: " + acWindLevelManualSign);
                                                i3 = acWindLevelManualSign != 0 ? acWindLevelManualSign == 1 ? 1 : acWindLevelManualSign : 0;
                                                Iterator<AbsBYDAutoAcListener> it23 = this.mAbsBydAutoAcListeners.iterator();
                                                while (it23.hasNext()) {
                                                    it23.next().onAcWindLevelManualSignChanged(i3);
                                                }
                                                break;
                                            case HvacHalId.ID_AC_WIND_MODE_MANUAL_SIGN /* 557846592 */:
                                                Iterator<AbsBYDAutoAcListener> it24 = this.mAbsBydAutoAcListeners.iterator();
                                                while (it24.hasNext()) {
                                                    it24.next().onAcWindModeManualSignChanged(hvacPacket.getAcWindModeManualSign());
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case HvacHalId.ID_HIGH_TEMP_ANTIVIRUS_COUNT_DOWN /* 557846596 */:
                                                        Iterator<AbsBYDAutoAcListener> it25 = this.mAbsBydAutoAcListeners.iterator();
                                                        while (it25.hasNext()) {
                                                            it25.next().onHighTempAntivirusCountDownChanged(hvacPacket.getHighTempAntivirusCountDown());
                                                        }
                                                        break;
                                                    case HvacHalId.ID_HIGHTEMPANTIVIRUSSTATE /* 557846597 */:
                                                        Iterator<AbsBYDAutoAcListener> it26 = this.mAbsBydAutoAcListeners.iterator();
                                                        while (it26.hasNext()) {
                                                            it26.next().onHighTempAntivirusStateChanged(hvacPacket.getHighTempAntivirusState());
                                                        }
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case HvacHalId.ID_AC_QUICK_CLEAN_AIR_STATER /* 557846606 */:
                                                                Iterator<AbsBYDAutoAcListener> it27 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it27.hasNext()) {
                                                                    it27.next().onQuickCleanAirStateChanged(hvacPacket.getQuickCleanAirStateR());
                                                                }
                                                                break;
                                                            case HvacHalId.ID_QUICK_CLEAN_TIPR /* 557846607 */:
                                                                Iterator<AbsBYDAutoAcListener> it28 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it28.hasNext()) {
                                                                    it28.next().onQuickCleanTipChanged(hvacPacket.getQuickCleanTipR());
                                                                }
                                                                break;
                                                            case 557846608:
                                                                Iterator<AbsBYDAutoAcListener> it29 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it29.hasNext()) {
                                                                    it29.next().onAutoCleanAirStateChanged(hvacPacket.getAutoCleanAirStateR());
                                                                }
                                                                caseFeatureIds(3316, Integer.valueOf(hvacPacket.getAutoCleanAirStateR()));
                                                                break;
                                                            case HvacHalId.ID_AC_ASSIST_MODE_TIPS /* 557846609 */:
                                                                Iterator<AbsBYDAutoAcListener> it30 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it30.hasNext()) {
                                                                    it30.next().onACAssistModeTips(hvacPacket.getAcAssistModeTips());
                                                                }
                                                                break;
                                                            case HvacHalId.ID_AC_KEEP_ENGINE_RUN_TIPS /* 557846610 */:
                                                                for (AbsBYDAutoAcListener absBYDAutoAcListener6 : this.mAbsBydAutoAcListeners) {
                                                                    absBYDAutoAcListener6.onACKeepEngineRunTips(hvacPacket.getAcKeepEngineRunTIp());
                                                                    absBYDAutoAcListener6.onACMaxEVModeTips(hvacPacket.getAcKeepEngineRunTIp());
                                                                }
                                                                break;
                                                            case HvacHalId.ID_ACBT_WIND_TIPS /* 557846611 */:
                                                                Iterator<AbsBYDAutoAcListener> it31 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it31.hasNext()) {
                                                                    it31.next().onACBTWindTips(hvacPacket.getAcbtWindTips());
                                                                }
                                                                break;
                                                            case 557846612:
                                                                Iterator<AbsBYDAutoAcListener> it32 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it32.hasNext()) {
                                                                    it32.next().onACDefrostRearPanelNotAvailableTips(hvacPacket.getAcDefrosRearPanelNotAvailableTips());
                                                                }
                                                                break;
                                                            case HvacHalId.ID_AC_DEFROST_OPEN_STATE_LEFT /* 557846613 */:
                                                                Iterator<AbsBYDAutoAcListener> it33 = this.mAbsBydAutoAcListeners.iterator();
                                                                while (it33.hasNext()) {
                                                                    it33.next().onAcDefrostStateChanged(2, hvacPacket.getAcDefrostOpenStateLeft());
                                                                }
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case HvacHalId.ID_FREE_WIND_BTN_STATE_DRIVER_R /* 557846622 */:
                                                                        Iterator<AbsBYDAutoAcListener> it34 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it34.hasNext()) {
                                                                            it34.next().onFreeWindBTNStateChanged(0, hvacPacket.getFreeWindBtnStateDriverR());
                                                                        }
                                                                        caseFeatureIds(3359, Integer.valueOf(hvacPacket.getFreeWindBtnStateDriverR()));
                                                                        break;
                                                                    case HvacHalId.ID_FREE_WIND_BTN_STATE_COPILOT_R /* 557846623 */:
                                                                        Iterator<AbsBYDAutoAcListener> it35 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it35.hasNext()) {
                                                                            it35.next().onFreeWindBTNStateChanged(1, hvacPacket.getFreeWindBtnStateCopilotR());
                                                                        }
                                                                        caseFeatureIds(3413, Integer.valueOf(hvacPacket.getFreeWindBtnStateCopilotR()));
                                                                        break;
                                                                    case HvacHalId.ID_BLOW_PEOPLE_STATE_DRIVER_R /* 557846624 */:
                                                                        Iterator<AbsBYDAutoAcListener> it36 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it36.hasNext()) {
                                                                            it36.next().onBlowPeopleState(0, hvacPacket.getBlowPeopleStateDriverR());
                                                                        }
                                                                        caseFeatureIds(3357, Integer.valueOf(hvacPacket.getBlowPeopleStateDriverR()));
                                                                        break;
                                                                    case HvacHalId.ID_BLOW_PEOPLE_STATE_COPILOT_R /* 557846625 */:
                                                                        Iterator<AbsBYDAutoAcListener> it37 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it37.hasNext()) {
                                                                            it37.next().onBlowPeopleState(1, hvacPacket.getBlowPeopleStateCopilotR());
                                                                        }
                                                                        caseFeatureIds(3411, Integer.valueOf(hvacPacket.getBlowPeopleStateCopilotR()));
                                                                        break;
                                                                    case HvacHalId.ID_AVOID_BLOW_PEOPLE_STATE_DRIVER_R /* 557846626 */:
                                                                        Iterator<AbsBYDAutoAcListener> it38 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it38.hasNext()) {
                                                                            it38.next().onAvoidBlowPeopleStateChanged(0, hvacPacket.getAvoidBlowPeopleStateDriverR());
                                                                        }
                                                                        caseFeatureIds(3382, Integer.valueOf(hvacPacket.getAvoidBlowPeopleStateDriverR()));
                                                                        break;
                                                                    case HvacHalId.ID_AVOID_BLOW_PEOPLE_STATE_COPILOT_R /* 557846627 */:
                                                                        Iterator<AbsBYDAutoAcListener> it39 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it39.hasNext()) {
                                                                            it39.next().onAvoidBlowPeopleStateChanged(1, hvacPacket.getAvoidBlowPeopleStateCopilotR());
                                                                        }
                                                                        caseFeatureIds(3436, Integer.valueOf(hvacPacket.getAvoidBlowPeopleStateCopilotR()));
                                                                        break;
                                                                    case HvacHalId.ID_SWEEP_BLOW_STATE_DRIVER_R /* 557846628 */:
                                                                        Iterator<AbsBYDAutoAcListener> it40 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it40.hasNext()) {
                                                                            it40.next().onSweepBlowStateChanged(0, hvacPacket.getSweepBlowStateDriverR());
                                                                        }
                                                                        caseFeatureIds(3361, Integer.valueOf(hvacPacket.getSweepBlowStateDriverR()));
                                                                        break;
                                                                    case HvacHalId.ID_SWEEP_BLOW_STATE_COPILOT_R /* 557846629 */:
                                                                        Iterator<AbsBYDAutoAcListener> it41 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it41.hasNext()) {
                                                                            it41.next().onSweepBlowStateChanged(1, hvacPacket.getSweepBlowStateCopilotR());
                                                                        }
                                                                        caseFeatureIds(3415, Integer.valueOf(hvacPacket.getSweepBlowStateCopilotR()));
                                                                        break;
                                                                    case HvacHalId.ID_AIR_OUTLET_STATE_DRIVER_R /* 557846630 */:
                                                                        Iterator<AbsBYDAutoAcListener> it42 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it42.hasNext()) {
                                                                            it42.next().onAirOutletStateChanged(0, hvacPacket.getAirOutletStateDriverR());
                                                                        }
                                                                        caseFeatureIds(3384, Integer.valueOf(hvacPacket.getAirOutletStateDriverR()));
                                                                        break;
                                                                    case HvacHalId.ID_AIR_OUTLET_STATE_COPILOT_R /* 557846631 */:
                                                                        Iterator<AbsBYDAutoAcListener> it43 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it43.hasNext()) {
                                                                            it43.next().onAirOutletStateChanged(1, hvacPacket.getAirOutletStateCopilotR());
                                                                        }
                                                                        caseFeatureIds(3438, Integer.valueOf(hvacPacket.getAirOutletStateCopilotR()));
                                                                        break;
                                                                    case HvacHalId.ID_INTELLIGENT_CONTROL_STATE_R /* 557846632 */:
                                                                        Iterator<AbsBYDAutoAcListener> it44 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it44.hasNext()) {
                                                                            it44.next().onIntelligentControlStateChanged(hvacPacket.getIntelligentControlStateR());
                                                                        }
                                                                        caseFeatureIds(3352, Integer.valueOf(hvacPacket.getIntelligentControlStateR()));
                                                                        break;
                                                                    case HvacHalId.ID_FRONT_ROW_AIR_OUTLET_STATE_R /* 557846633 */:
                                                                        Iterator<AbsBYDAutoAcListener> it45 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it45.hasNext()) {
                                                                            it45.next().onFrontRowAirOutletStateChanged(hvacPacket.getFrontRowAirOutletStateR());
                                                                        }
                                                                        caseFeatureIds(3342, Integer.valueOf(hvacPacket.getFrontRowAirOutletStateR()));
                                                                        break;
                                                                    case HvacHalId.ID_AC_WORK_MODE_DRIVER_R /* 557846634 */:
                                                                        Iterator<AbsBYDAutoAcListener> it46 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it46.hasNext()) {
                                                                            it46.next().onACWorkModeChanged(0, hvacPacket.getAcWorkModeDriverR());
                                                                        }
                                                                        caseFeatureIds(3355, Integer.valueOf(hvacPacket.getAcWorkModeDriverR()));
                                                                        break;
                                                                    case HvacHalId.ID_AC_WORK_MODE_COPILOT_R /* 557846635 */:
                                                                        Iterator<AbsBYDAutoAcListener> it47 = this.mAbsBydAutoAcListeners.iterator();
                                                                        while (it47.hasNext()) {
                                                                            it47.next().onACWorkModeChanged(1, hvacPacket.getAcWorkModeCopilotR());
                                                                        }
                                                                        caseFeatureIds(3509, Integer.valueOf(hvacPacket.getAcWorkModeCopilotR()));
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_LEFT_VERTICAL_R /* 559943766 */:
                                                                                for (AbsBYDAutoAcListener absBYDAutoAcListener7 : this.mAbsBydAutoAcListeners) {
                                                                                    if (i2 == 2) {
                                                                                        this.mAirOutletOnline = 0;
                                                                                    } else {
                                                                                        this.mAirOutletOnline = 1;
                                                                                        absBYDAutoAcListener7.onAirOutletValueChanged(0, hvacPacket.getAirOutletValueDriverLeftVerticalR());
                                                                                    }
                                                                                }
                                                                                caseFeatureIds(3365, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverLeftHorizontalR()));
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_LEFT_HORIZONTAL_R /* 559943767 */:
                                                                                Iterator<AbsBYDAutoAcListener> it48 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it48.hasNext()) {
                                                                                    it48.next().onAirOutletValueChanged(1, hvacPacket.getAirOutletValueDriverLeftHorizontalR());
                                                                                }
                                                                                caseFeatureIds(3363, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverLeftHorizontalR()));
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_VERTICAL_R /* 559943768 */:
                                                                                Iterator<AbsBYDAutoAcListener> it49 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it49.hasNext()) {
                                                                                    it49.next().onAirOutletValueChanged(2, hvacPacket.getAirOutletValueDriverRightVerticalR());
                                                                                }
                                                                                caseFeatureIds(3369, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverRightVerticalR()));
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_HORIZONTAL_R /* 559943769 */:
                                                                                Iterator<AbsBYDAutoAcListener> it50 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it50.hasNext()) {
                                                                                    it50.next().onAirOutletValueChanged(3, hvacPacket.getAirOutletValueDriverRightHorizontalR());
                                                                                }
                                                                                caseFeatureIds(3368, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverRightHorizontalR()));
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_LEFT_VERTICAL_R /* 559943770 */:
                                                                                Iterator<AbsBYDAutoAcListener> it51 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it51.hasNext()) {
                                                                                    it51.next().onAirOutletValueChanged(4, hvacPacket.getAirOutletValueCopilotLeftVerticalR());
                                                                                }
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_LEFT_HORIZONTAL_R /* 559943771 */:
                                                                                Iterator<AbsBYDAutoAcListener> it52 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it52.hasNext()) {
                                                                                    it52.next().onAirOutletValueChanged(5, hvacPacket.getAirOutletValueCopilotLeftHorizontalR());
                                                                                }
                                                                                caseFeatureIds(3417, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotLeftHorizontalR()));
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_VERTICAL_R /* 559943772 */:
                                                                                Iterator<AbsBYDAutoAcListener> it53 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it53.hasNext()) {
                                                                                    it53.next().onAirOutletValueChanged(6, hvacPacket.getAirOutletValueCopilotRightVerticalR());
                                                                                }
                                                                                caseFeatureIds(3423, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotRightVerticalR()));
                                                                                break;
                                                                            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_HORIZONTAL_R /* 559943773 */:
                                                                                Iterator<AbsBYDAutoAcListener> it54 = this.mAbsBydAutoAcListeners.iterator();
                                                                                while (it54.hasNext()) {
                                                                                    it54.next().onAirOutletValueChanged(7, hvacPacket.getAirOutletValueCopilotRightHorizontalR());
                                                                                }
                                                                                caseFeatureIds(3421, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotRightHorizontalR()));
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
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public int confirmCleanAirState(int i) {
        Log.d(TAG, "confirmCleanAirState is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().confirmCleanAirState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "confirmCleanAirState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int enablePurificationFunctionPrompt(int i) {
        Log.d(TAG, "enablePurificationFunctionPrompt is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().enablePurificationFunctionPrompt(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "enablePurificationFunctionPrompt mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int feelColdHot(int i, int i2) {
        Log.d(TAG, "feelColdHot is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().feelColdHot(i, i2);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "feelColdHot mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    switch (i) {
                        case 3313:
                            int heatPumpCompressorWorkMode = getHeatPumpCompressorWorkMode();
                            bYDAutoEventValue.intValue = heatPumpCompressorWorkMode;
                            sparseArray.put(3313, Integer.valueOf(heatPumpCompressorWorkMode));
                            break;
                        case 3316:
                            int autoCleanAirState = getAutoCleanAirState();
                            bYDAutoEventValue.intValue = autoCleanAirState;
                            sparseArray.put(3316, Integer.valueOf(autoCleanAirState));
                            break;
                        case 3320:
                            int acKeepEngineRunTipsState = getAcKeepEngineRunTipsState();
                            bYDAutoEventValue.intValue = acKeepEngineRunTipsState;
                            sparseArray.put(3320, Integer.valueOf(acKeepEngineRunTipsState));
                            break;
                        case 3321:
                            int acCompressorManualSign = getAcCompressorManualSign();
                            bYDAutoEventValue.intValue = acCompressorManualSign;
                            sparseArray.put(3321, Integer.valueOf(acCompressorManualSign));
                            break;
                        case 3322:
                            int acCompressorMode = getAcCompressorMode();
                            bYDAutoEventValue.intValue = acCompressorMode;
                            sparseArray.put(3322, Integer.valueOf(acCompressorMode));
                            break;
                        case 3325:
                            int acControlMode = getAcControlMode();
                            bYDAutoEventValue.intValue = acControlMode;
                            sparseArray.put(3325, Integer.valueOf(acControlMode));
                            break;
                        case 3331:
                            int acCycleMode = getAcCycleMode();
                            bYDAutoEventValue.intValue = acCycleMode;
                            sparseArray.put(3331, Integer.valueOf(acCycleMode));
                            break;
                        case 3333:
                            int acDefrostState = getAcDefrostState(1);
                            bYDAutoEventValue.intValue = acDefrostState;
                            sparseArray.put(3333, Integer.valueOf(acDefrostState));
                            break;
                        case 3335:
                            int defrostRearConfig = getDefrostRearConfig();
                            bYDAutoEventValue.intValue = defrostRearConfig;
                            sparseArray.put(3335, Integer.valueOf(defrostRearConfig));
                            break;
                        case 3336:
                            int electricDefrostState = getElectricDefrostState();
                            bYDAutoEventValue.intValue = electricDefrostState;
                            sparseArray.put(3336, Integer.valueOf(electricDefrostState));
                            break;
                        case 3340:
                            int acFaultNumShownState = getAcFaultNumShownState();
                            bYDAutoEventValue.intValue = acFaultNumShownState;
                            sparseArray.put(3340, Integer.valueOf(acFaultNumShownState));
                            break;
                        case 3342:
                            int frontRowAirOutletState = getFrontRowAirOutletState();
                            bYDAutoEventValue.intValue = frontRowAirOutletState;
                            sparseArray.put(3342, Integer.valueOf(frontRowAirOutletState));
                            break;
                        case 3349:
                            int highTempAntivirusCountDown = getHighTempAntivirusCountDown();
                            bYDAutoEventValue.intValue = highTempAntivirusCountDown;
                            sparseArray.put(3349, Integer.valueOf(highTempAntivirusCountDown));
                            break;
                        case 3350:
                            int highTempAntivirusState = getHighTempAntivirusState();
                            bYDAutoEventValue.intValue = highTempAntivirusState;
                            sparseArray.put(3350, Integer.valueOf(highTempAntivirusState));
                            break;
                        case 3352:
                            int intelligentControlState = getIntelligentControlState();
                            bYDAutoEventValue.intValue = intelligentControlState;
                            sparseArray.put(3352, Integer.valueOf(intelligentControlState));
                            break;
                        case 3355:
                            int acWorkMode = getAcWorkMode(0);
                            bYDAutoEventValue.intValue = acWorkMode;
                            sparseArray.put(3355, Integer.valueOf(acWorkMode));
                            break;
                        case 3357:
                            int blowPeopleState = getBlowPeopleState(0);
                            bYDAutoEventValue.intValue = blowPeopleState;
                            sparseArray.put(3357, Integer.valueOf(blowPeopleState));
                            break;
                        case 3359:
                            int freeWindBtnState = getFreeWindBtnState(0);
                            bYDAutoEventValue.intValue = freeWindBtnState;
                            sparseArray.put(3359, Integer.valueOf(freeWindBtnState));
                            break;
                        case 3361:
                            int sweepBlowState = getSweepBlowState(0);
                            bYDAutoEventValue.intValue = sweepBlowState;
                            sparseArray.put(3361, Integer.valueOf(sweepBlowState));
                            break;
                        case 3382:
                            int avoidBlowPeopleState = getAvoidBlowPeopleState(0);
                            bYDAutoEventValue.intValue = avoidBlowPeopleState;
                            sparseArray.put(3382, Integer.valueOf(avoidBlowPeopleState));
                            break;
                        case 3384:
                            int airOutletState = getAirOutletState(0);
                            bYDAutoEventValue.intValue = airOutletState;
                            sparseArray.put(3384, Integer.valueOf(airOutletState));
                            break;
                        case 3388:
                            int acMaxCoolingState = getAcMaxCoolingState();
                            bYDAutoEventValue.intValue = acMaxCoolingState;
                            sparseArray.put(3388, Integer.valueOf(acMaxCoolingState));
                            break;
                        case 3411:
                            int blowPeopleState2 = getBlowPeopleState(1);
                            bYDAutoEventValue.intValue = blowPeopleState2;
                            sparseArray.put(3411, Integer.valueOf(blowPeopleState2));
                            break;
                        case 3413:
                            int freeWindBtnState2 = getFreeWindBtnState(1);
                            bYDAutoEventValue.intValue = freeWindBtnState2;
                            sparseArray.put(3413, Integer.valueOf(freeWindBtnState2));
                            break;
                        case 3415:
                            int sweepBlowState2 = getSweepBlowState(1);
                            bYDAutoEventValue.intValue = sweepBlowState2;
                            sparseArray.put(3415, Integer.valueOf(sweepBlowState2));
                            break;
                        case 3436:
                            int avoidBlowPeopleState2 = getAvoidBlowPeopleState(1);
                            bYDAutoEventValue.intValue = avoidBlowPeopleState2;
                            sparseArray.put(3436, Integer.valueOf(avoidBlowPeopleState2));
                            break;
                        case 3438:
                            int airOutletState2 = getAirOutletState(1);
                            bYDAutoEventValue.intValue = airOutletState2;
                            sparseArray.put(3438, Integer.valueOf(airOutletState2));
                            break;
                        case 3447:
                            int acStartState = getAcStartState();
                            bYDAutoEventValue.intValue = acStartState;
                            sparseArray.put(3447, Integer.valueOf(acStartState));
                            break;
                        case 3450:
                            int acPtcPreheatSignal = getAcPtcPreheatSignal();
                            bYDAutoEventValue.intValue = acPtcPreheatSignal;
                            sparseArray.put(3450, Integer.valueOf(acPtcPreheatSignal));
                            break;
                        case 3453:
                            int quickCleanAirState = getQuickCleanAirState();
                            bYDAutoEventValue.intValue = quickCleanAirState;
                            sparseArray.put(3453, Integer.valueOf(quickCleanAirState));
                            break;
                        case 3457:
                            int quickCleanTip = getQuickCleanTip();
                            bYDAutoEventValue.intValue = quickCleanTip;
                            sparseArray.put(3457, Integer.valueOf(quickCleanTip));
                            break;
                        case 3462:
                            int rearAcControlMode = getRearAcControlMode();
                            bYDAutoEventValue.intValue = rearAcControlMode;
                            sparseArray.put(3462, Integer.valueOf(rearAcControlMode));
                            break;
                        case 3477:
                            int rearACkeyLockState = getRearACkeyLockState();
                            bYDAutoEventValue.intValue = rearACkeyLockState;
                            sparseArray.put(3477, Integer.valueOf(rearACkeyLockState));
                            break;
                        case 3479:
                            int rearAcMaxWindLevel = getRearAcMaxWindLevel();
                            bYDAutoEventValue.intValue = rearAcMaxWindLevel;
                            sparseArray.put(3479, Integer.valueOf(rearAcMaxWindLevel));
                            break;
                        case 3480:
                            int acDefrostRearPanelNotAvailableTipsState = getAcDefrostRearPanelNotAvailableTipsState();
                            bYDAutoEventValue.intValue = acDefrostRearPanelNotAvailableTipsState;
                            sparseArray.put(3480, Integer.valueOf(acDefrostRearPanelNotAvailableTipsState));
                            break;
                        case 3483:
                            int acRearPanelLockState = getAcRearPanelLockState();
                            bYDAutoEventValue.intValue = acRearPanelLockState;
                            sparseArray.put(3483, Integer.valueOf(acRearPanelLockState));
                            break;
                        case 3486:
                            int rearAcStartState = getRearAcStartState();
                            bYDAutoEventValue.intValue = rearAcStartState;
                            sparseArray.put(3486, Integer.valueOf(rearAcStartState));
                            break;
                        case 3500:
                            int rearAcWindLevel = getRearAcWindLevel();
                            bYDAutoEventValue.intValue = rearAcWindLevel;
                            sparseArray.put(3500, Integer.valueOf(rearAcWindLevel));
                            break;
                        case 3503:
                            int rearAcWindMode = getRearAcWindMode();
                            bYDAutoEventValue.intValue = rearAcWindMode;
                            sparseArray.put(3503, Integer.valueOf(rearAcWindMode));
                            break;
                        case 3506:
                            int acRemoteCtrlTime = getAcRemoteCtrlTime();
                            bYDAutoEventValue.intValue = acRemoteCtrlTime;
                            sparseArray.put(3506, Integer.valueOf(acRemoteCtrlTime));
                            break;
                        case 3509:
                            int acWorkMode2 = getAcWorkMode(1);
                            bYDAutoEventValue.intValue = acWorkMode2;
                            sparseArray.put(3509, Integer.valueOf(acWorkMode2));
                            break;
                        case 3524:
                            int acTemperatureControlMode = getAcTemperatureControlMode();
                            bYDAutoEventValue.intValue = acTemperatureControlMode;
                            sparseArray.put(3524, Integer.valueOf(acTemperatureControlMode));
                            break;
                        case 3526:
                            int temperatureUnit = getTemperatureUnit();
                            bYDAutoEventValue.intValue = temperatureUnit;
                            sparseArray.put(3526, Integer.valueOf(temperatureUnit));
                            break;
                        case 3529:
                            int temprature = getTemprature(2);
                            bYDAutoEventValue.intValue = temprature;
                            sparseArray.put(3529, Integer.valueOf(temprature));
                            break;
                        case 3532:
                            int temprature2 = getTemprature(1);
                            bYDAutoEventValue.intValue = temprature2;
                            sparseArray.put(3532, Integer.valueOf(temprature2));
                            break;
                        case 3535:
                            int temprature3 = getTemprature(3);
                            bYDAutoEventValue.intValue = temprature3;
                            sparseArray.put(3535, Integer.valueOf(temprature3));
                            break;
                        case 3553:
                            int acType = getAcType();
                            bYDAutoEventValue.intValue = acType;
                            sparseArray.put(3553, Integer.valueOf(acType));
                            break;
                        case 3554:
                            int acVentilationState = getAcVentilationState();
                            bYDAutoEventValue.intValue = acVentilationState;
                            sparseArray.put(3554, Integer.valueOf(acVentilationState));
                            break;
                        case 3559:
                            int acWarmTypeOnlineState = getAcWarmTypeOnlineState();
                            bYDAutoEventValue.intValue = acWarmTypeOnlineState;
                            sparseArray.put(3559, Integer.valueOf(acWarmTypeOnlineState));
                            break;
                        case 3561:
                            int acWindLevelManualSign = getAcWindLevelManualSign();
                            bYDAutoEventValue.intValue = acWindLevelManualSign;
                            sparseArray.put(3561, Integer.valueOf(acWindLevelManualSign));
                            break;
                        case 3562:
                            int acWindModeManualSign = getAcWindModeManualSign();
                            bYDAutoEventValue.intValue = acWindModeManualSign;
                            sparseArray.put(3562, Integer.valueOf(acWindModeManualSign));
                            break;
                        case 3564:
                            int acWindLevel = getAcWindLevel();
                            bYDAutoEventValue.intValue = acWindLevel;
                            sparseArray.put(3564, Integer.valueOf(acWindLevel));
                            break;
                        case 3566:
                            int acWindMode = getAcWindMode();
                            bYDAutoEventValue.intValue = acWindMode;
                            sparseArray.put(3566, Integer.valueOf(acWindMode));
                            break;
                        case 3567:
                            int acWindModeNum = getAcWindModeNum();
                            bYDAutoEventValue.intValue = acWindModeNum;
                            sparseArray.put(3567, Integer.valueOf(acWindModeNum));
                            break;
                        case 4045:
                            int temprature4 = getTemprature(4);
                            bYDAutoEventValue.intValue = temprature4;
                            sparseArray.put(4045, Integer.valueOf(temprature4));
                            break;
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int get3daOnlineState() {
        Log.d(TAG, "get3daOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().get3daOnlineState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "get3daOnlineState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getACPreheatState() {
        Log.d(TAG, "getACPreheatState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getACPreheatState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getACPreheatState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getACSystemState() {
        Log.d(TAG, "getACSystemState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getACSystemState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getACSystemState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcAssistModeTipsState() {
        Log.d(TAG, "getAcAssistModeTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcAssistModeTipsState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcAssistModeTipsState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcCompressorManualSign() {
        Log.d(TAG, "getAcCompressorManualSign is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcCompressorManualSign mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acCompressorManualSign = this.mTsManagerImpl.getHvacAdapterManager().getAcCompressorManualSign();
        Log.d(TAG, "getAcCompressorManualSign value is: " + acCompressorManualSign);
        if (acCompressorManualSign == 0) {
            return 0;
        }
        return acCompressorManualSign == 1 ? 1 : -1;
    }

    public int getAcCompressorMode() {
        Log.d(TAG, "getAcCompressorMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcCompressorMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acCompressorMode = this.mTsManagerImpl.getHvacAdapterManager().getAcCompressorMode();
        Log.d(TAG, "getAcCompressorMode value is:" + acCompressorMode);
        if (acCompressorMode == 0) {
            return 0;
        }
        return acCompressorMode == 1 ? 1 : -1;
    }

    public int getAcControlMode() {
        Log.d(TAG, "getAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acControlMode = this.mTsManagerImpl.getHvacAdapterManager().getAcControlMode();
        Log.d(TAG, "getAcControlMode value is: " + acControlMode);
        if (1 == acControlMode) {
            return 1;
        }
        if (2 == acControlMode) {
            return 0;
        }
        if (acControlMode == 0) {
            return -1;
        }
        return acControlMode;
    }

    public int getAcCycleMode() {
        Log.d(TAG, "getAcCycleMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcCycleMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acCycleMode = this.mTsManagerImpl.getHvacAdapterManager().getAcCycleMode();
        Log.d(TAG, "getAcCycleMode value is: " + acCycleMode);
        if (1 == acCycleMode) {
            return 0;
        }
        if (2 == acCycleMode) {
            return 1;
        }
        if (acCycleMode == 0) {
            return -1;
        }
        return acCycleMode;
    }

    public int getAcDefrostOnlineState() {
        Log.d(TAG, "getAcDefrostOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcDefrostOnlineState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcDefrostOnlineState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcDefrostRearPanelNotAvailableTipsState() {
        Log.d(TAG, "getAcDefrostRearPanelNotAvailableTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcDefrostRearPanelNotAvailableTipsState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcDefrostRearPanelNotAvailableTipsState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcDefrostState(int i) {
        Log.d(TAG, "getAcDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcDefrostState area  is: " + i);
        if (i != 1) {
            if (i != 2) {
                return 0;
            }
            int electricDefrostState = getElectricDefrostState();
            Log.d(TAG, "getAcDefrostState value is: " + electricDefrostState);
            return electricDefrostState;
        }
        int acDefrostStateFront = this.mTsManagerImpl.getHvacAdapterManager().getAcDefrostStateFront();
        Log.d(TAG, "getAcDefrostState value is: " + acDefrostStateFront);
        if (acDefrostStateFront == 1) {
            return 0;
        }
        if (acDefrostStateFront == 2) {
            return 1;
        }
        if (acDefrostStateFront == 0) {
            return -1;
        }
        return acDefrostStateFront;
    }

    public int getAcFaultNumShownState() {
        Log.d(TAG, "getAcFaultNumShownState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcFaultNumShownState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcFaultNumShownState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcKeepEngineRunTipsState() {
        Log.d(TAG, "getAcKeepEngineRunTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcKeepEngineRunTipsState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcKeepEngineRunTipsState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcKeyActionState() {
        Log.d(TAG, "getAcKeyActionState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcKeyActionState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcKeyActionState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcMaxCoolingState() {
        Log.d(TAG, "getAcMaxCoolingState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcMaxCoolingState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acMaxCoolingState = this.mTsManagerImpl.getHvacAdapterManager().getAcMaxCoolingState();
        if (acMaxCoolingState == 2) {
            return 0;
        }
        if (acMaxCoolingState == 0) {
            return -1;
        }
        return acMaxCoolingState;
    }

    public int getAcOnlineState() {
        Log.d(TAG, "getAcOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mOnline;
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcOnlineState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcPanelLockPromptState() {
        Log.d(TAG, "getAcPanelLockPromptState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcPanelLockPromptState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcPanelLockPromptState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcPromptBoxShownState() {
        Log.d(TAG, "getAcPromptBoxShownState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcPromptBoxShownState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcPromptBoxShownState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcPtcPreheatSignal() {
        Log.d(TAG, "getAcPtcPreheatSignal is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcPtcPreheatSignal();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcPtcPreheatSignal mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcRearPaneNotAvailableMenuState() {
        Log.d(TAG, "getAcRearPaneNotAvailableMenuState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcRearPaneNotAvailableMenuState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcRearPaneNotAvailableMenuState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcRearPanelLockState() {
        Log.d(TAG, "getAcRearPanelLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcRearPanelLockState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcRearPanelLockState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcRemoteCtrlTime() {
        Log.d(TAG, "getAcRemoteCtrlTime is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcRemoteCtrlTime();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcRemoteCtrlTime mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcStartState() {
        Log.d(TAG, "getAcStartState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acStartState = this.mTsManagerImpl.getHvacAdapterManager().getAcStartState();
        Log.d(TAG, "getAcStartState value is: " + acStartState);
        if (1 == acStartState) {
            return 1;
        }
        if (2 == acStartState) {
            return 0;
        }
        if (acStartState == 0) {
            return -1;
        }
        return acStartState;
    }

    public int getAcSubBatteryTemperature() {
        Log.d(TAG, "getAcSubBatteryTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcSubBatteryTemperature();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcSubBatteryTemperature mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcTemperatureControlMode() {
        Log.d(TAG, "getAcTemperatureControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcTemperatureControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acTemperatureControlMode = this.mTsManagerImpl.getHvacAdapterManager().getAcTemperatureControlMode();
        Log.d(TAG, "getAcTemperatureControlMode value is: " + acTemperatureControlMode);
        if (acTemperatureControlMode == 1) {
            return 0;
        }
        if (acTemperatureControlMode == 2) {
            return 1;
        }
        if (acTemperatureControlMode == 0) {
            return -1;
        }
        return acTemperatureControlMode;
    }

    public int getAcType() {
        Log.d(TAG, "getAcType is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcType();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcVentilationState() {
        Log.d(TAG, "getAcVentilationState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acVentilationState = this.mTsManagerImpl.getHvacAdapterManager().getAcVentilationState();
        if (acVentilationState == 1) {
            return 1;
        }
        return (acVentilationState != 2 && acVentilationState == 0) ? -1 : 0;
    }

    public int getAcWarmState() {
        Log.d(TAG, "getAcWarmState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWarmState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcWarmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWarmTypeOnlineState() {
        Log.d(TAG, "getAcWarmTypeOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWarmTypeOnlineState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcWarmTypeOnlineState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWindLevel() {
        Log.d(TAG, "getAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWindLevel();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcWindLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWindLevelManualSign() {
        Log.d(TAG, "getAcWindLevelManualSign is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcWindLevelManualSign mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acWindLevelManualSign = this.mTsManagerImpl.getHvacAdapterManager().getAcWindLevelManualSign();
        Log.d(TAG, "getAcWindLevelManualSign value is: " + acWindLevelManualSign);
        if (acWindLevelManualSign == 0) {
            return 0;
        }
        return acWindLevelManualSign == 1 ? 1 : -1;
    }

    public int getAcWindMode() {
        Log.d(TAG, "getAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWindMode();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcWindMode mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWindModeManualSign() {
        Log.d(TAG, "getAcWindModeManualSign is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWindModeManualSign();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcWindModeManualSign mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWindModeNum() {
        Log.d(TAG, "getAcWindModeNum is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWindModeNum();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcWindModeNum mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWindModeShownState() {
        Log.d(TAG, "getAcWindModeShownState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcWindModeShownState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAcWindModeShownState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAcWorkMode(int i) {
        Log.d(TAG, "getAcWorkMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAcWorkMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeCopilot();
    }

    public int getAcbtWindTipsState() {
        Log.d(TAG, "getAcbtWindTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAcbtWindTipsState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getAcbtWindTipsState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAirOutleOnlineState() {
        Log.d(TAG, "getAirOutleOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mAirOutletOnline;
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAirOutleOnlineState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAirOutletState(int i) {
        Log.d(TAG, "getAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAirOutletState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAirOutletStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAirOutletStateCopilot();
    }

    public float getAirOutletValue(int i) {
        Log.d(TAG, "getAirOutletValue is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return 0.0f;
            }
            switch (i) {
            }
            return 0.0f;
        }
        Log.d(TAG, "getAirOutletValue mForTestValue is" + this.mForTest + " execute return");
        return 0.0f;
    }

    public int getAirQualityCtrlMenuState() {
        Log.d(TAG, "getAirQualityCtrlMenuState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAirQualityCtrlMenuState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAirQualityCtrlMenuState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void getAllStatus() {
        Log.d(TAG, "getAllStatus is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getHvacAdapterManager().getAllStatus();
                return;
            }
        }
        Log.d(TAG, "getAllStatus mForTestValue is" + this.mForTest + " execute return");
    }

    public int getAutoCleanAirState() {
        Log.d(TAG, "getAutoCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getAutoCleanAirState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAutoCleanAirState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAvoidBlowPeopleState(int i) {
        Log.d(TAG, "getAvoidBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getAvoidBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAvoidBlowPeopleStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAvoidBlowPeopleStateCopilot();
    }

    public int getBlowPeopleState(int i) {
        Log.d(TAG, "getBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getBlowPeopleStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getBlowPeopleStateCopilot();
    }

    public int getDefrostRearConfig() {
        Log.d(TAG, "getDefrostRearConfig is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getDefrostRearConfig();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDefrostRearConfig mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getDevicetype();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getElectricDefrostState() {
        Log.d(TAG, "getElectricDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getElectricDefrostState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getElectricDefrostState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getFeatrueList() {
        Log.d(TAG, "getFeatrueList is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getFeatrueList();
            }
            Log.e(TAG, "adapter is not connect");
            return new int[0];
        }
        Log.d(TAG, "getFeatrueList mForTestValue is" + this.mForTest + " execute return");
        return new int[0];
    }

    public int getFreeWindBtnState(int i) {
        Log.d(TAG, "getFreeWindBtnState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getFreeWindBtnState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFreeWindBtnStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFreeWindBtnStateCopilot();
    }

    public int getFrontRowAirOutletState() {
        Log.d(TAG, "getFrontRowAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getFrontRowAirOutletState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getFrontRowAirOutletState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public int getHeatPumpCompressorWorkMode() {
        Log.d(TAG, "getHeatPumpCompressorWorkMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getHeatPumpCompressorWorkMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int heatPumpCompressorWorkMode = this.mTsManagerImpl.getHvacAdapterManager().getHeatPumpCompressorWorkMode();
        Log.d(TAG, "getHeatPumpCompressorWorkMode value is:" + heatPumpCompressorWorkMode);
        if (heatPumpCompressorWorkMode == 0) {
            return 0;
        }
        if (heatPumpCompressorWorkMode == 1) {
            return 1;
        }
        if (heatPumpCompressorWorkMode == 2) {
            return 2;
        }
        return heatPumpCompressorWorkMode == 3 ? 3 : -1;
    }

    public int getHighTempAntivirusCountDown() {
        Log.d(TAG, "getHighTempAntivirusCountDown is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getHighTempAntivirusCountDown();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getHighTempAntivirusCountDown mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getHighTempAntivirusState() {
        Log.d(TAG, "getHighTempAntivirusState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getHighTempAntivirusState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getHighTempAntivirusState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getIntelligentControlState() {
        Log.d(TAG, "getIntelligentControlState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getIntelligentControlState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getIntelligentControlState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getMaxAcState() {
        Log.d(TAG, "getMaxAcState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getMaxAcState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getMaxAcState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getQuickCleanAirState() {
        Log.d(TAG, "getQuickCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getQuickCleanAirState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getQuickCleanAirState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getQuickCleanTip() {
        Log.d(TAG, "getQuickCleanTip is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getQuickCleanTip();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getQuickCleanTip mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getRearACkeyLockState() {
        Log.d(TAG, "getRearACkeyLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getRearACkeyLockState();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getRearACkeyLockState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getRearAcControlMode() {
        Log.d(TAG, "getRearAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getRearAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int rearAcControlMode = this.mTsManagerImpl.getHvacAdapterManager().getRearAcControlMode();
        Log.d(TAG, "getAcRearAcCtrlMode value is: " + rearAcControlMode);
        if (2 == rearAcControlMode) {
            return 0;
        }
        if (rearAcControlMode == 0) {
            return -1;
        }
        return rearAcControlMode;
    }

    public int getRearAcLockState() {
        Log.d(TAG, "getRearAcLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getRearAcLockState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getRearAcLockState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getRearAcMaxWindLevel() {
        Log.d(TAG, "getRearAcMaxWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getRearAcMaxWindLevel();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getRearAcMaxWindLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getRearAcStartState() {
        Log.d(TAG, "getRearAcStartState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getRearAcStartState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int rearAcStartState = this.mTsManagerImpl.getHvacAdapterManager().getRearAcStartState();
        if (rearAcStartState == 1) {
            return 1;
        }
        if (rearAcStartState == 2) {
            return 0;
        }
        if (rearAcStartState == 0) {
            return -1;
        }
        return rearAcStartState;
    }

    public int getRearAcWindLevel() {
        Log.d(TAG, "getRearAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getRearAcWindLevel();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getRearAcWindLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getRearAcWindMode() {
        Log.d(TAG, "getRearAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getRearAcWindMode();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getRearAcWindMode mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public int getSweepBlowState(int i) {
        Log.d(TAG, "getSweepBlowState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSweepBlowState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getSweepBlowStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getSweepBlowStateCopilot();
    }

    public int getTemperatureUnit() {
        Log.d(TAG, "getTemperatureUnit is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getTemperatureUnit mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int temperatureUnit = this.mTsManagerImpl.getHvacAdapterManager().getTemperatureUnit();
        Log.d(TAG, "getTemperatureUnit value is: " + temperatureUnit);
        return temperatureUnit == 0 ? 1 : 0;
    }

    public int getTemprature(int i) {
        Log.d(TAG, "getTemprature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.d(TAG, "getTemprature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureMain();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureDeputy();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureRear();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureOut();
        }
        return 0;
    }

    public int getType() {
        Log.d(TAG, "getType is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getType();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getVoiceCmdResult() {
        Log.d(TAG, "getVoiceCmdResult is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getVoiceCmdResult();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getVoiceCmdResult mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int hasFeature(String str) {
        Log.d(TAG, "hasFeature is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().hasFeature(str);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "hasFeature mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoAcListener> it = this.mAbsBydAutoAcListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        Log.d(TAG, "postEvent is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        return false;
    }

    public int qurAcAllStatus() {
        Log.d(TAG, "qurAcAllStatus is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().qurAcAllStatus();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "qurAcAllStatus mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void registerListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        registerListener(absBYDAutoAcListener, new int[0]);
    }

    public int reset(int i, int i2) {
        Log.d(TAG, "reset is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().reset(i, i2);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "reset mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setAcAntibacterialControlState(int i) {
        Log.d(TAG, "setACAntibacterialControlState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setAcAntibacterialControlState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setACAntibacterialControlState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setAcCompressorMode(int i, int i2) {
        Log.d(TAG, "setAcCompressorMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 1;
        } else if (i2 == 1) {
            i2 = 2;
        }
        Log.d(TAG, "setAcCompressorMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCompressorMode(i, i2);
    }

    public int setAcCompressorState(int i) {
        Log.d(TAG, "setAcCompressorState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcCompressorState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAcControlMode(int i, int i2) {
        Log.d(TAG, "setAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        }
        Log.d(TAG, "setAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcControlMode(i, i2);
    }

    public int setAcCycleMode(int i, int i2) {
        Log.d(TAG, "setAcCycleMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        }
        Log.d(TAG, "setAcCycleMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCycleMode(i, i2);
    }

    public int setAcDefrostState(int i, int i2, int i3) {
        Log.d(TAG, "setAcDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i3 == 1) {
            i3 = 2;
        } else if (i3 == 0) {
            i3 = 1;
        }
        Log.d(TAG, "setAcDefrostState param0 is:" + i + " param1 is:" + i2 + " param2 is:" + i3);
        if (i2 == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcDefrostStateFront(i3);
        }
        if (i2 == 2) {
            return setElectricDefrostState(i3);
        }
        return 0;
    }

    public int setAcMaxCoolingState(int i) {
        Log.d(TAG, "setAcMaxCoolingState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 0) {
            i = 2;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcMaxCoolingState(i);
    }

    public int setAcRearPanelLockState(int i) {
        Log.d(TAG, "setAcRearPanelLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcRearPanelLockState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setAcRemoteCtrlTime(int i) {
        Log.d(TAG, "setAcRemoteCtrlTime is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcRemoteCtrlTime(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setAcTemperature(int i, int i2, int i3, int i4) {
        Log.d(TAG, "setAcTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureMain(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureDeputy(i2);
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureMainDeputy(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureRear(i2);
        }
        return 0;
    }

    public int setAcTemperatureControlMode(int i, int i2) {
        Log.d(TAG, "setAcTemperatureControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 1;
        } else if (i2 == 1) {
            i2 = 2;
        }
        Log.d(TAG, "setAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureControlMode(i, i2);
    }

    public int setAcVentilationState(int i, int i2) {
        Log.d(TAG, "setAcVentilationState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        } else if (i2 == 1) {
            i2 = 1;
        }
        Log.d(TAG, "setAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcVentilationState(i, i2);
    }

    public int setAcWarmState(int i) {
        Log.d(TAG, "setAcWarmState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setAcWarmState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setAcWarmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setAcWindLevel(int i, int i2) {
        Log.d(TAG, "setAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcWindLevel(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setAcWindMode(int i, int i2) {
        Log.d(TAG, "setAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setAcWindMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWindMode(i, i2);
    }

    public int setAirOutletState(int i, int i2) {
        Log.d(TAG, "setAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.d(TAG, "setAirOutletState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setAirOutletStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setAirOutletStateCopilot(i2);
        }
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int setAirOutletValue(int i, int i2) {
        Log.d(TAG, "setAirOutletValue is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.d(TAG, "setAirOutletValue mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        switch (i) {
            case 0:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverLeftVertical(i2);
                return 0;
            case 1:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverLeftHorizontal(i2);
                return 0;
            case 2:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverRightVertical(i2);
                return 0;
            case 3:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverRightHorizontal(i2);
                return 0;
            case 4:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotLeftVertical(i2);
                return 0;
            case 5:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotLeftHorizontal(i2);
                return 0;
            case 6:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotRightVertical(i2);
                return 0;
            case 7:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotRightHorizontal(i2);
                return 0;
            default:
                return 0;
        }
    }

    public void setAllStatus() {
        Log.d(TAG, "setAllStatus is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getHvacAdapterManager().setAllStatus();
                return;
            }
        }
        Log.d(TAG, "setAllStatus mForTestValue is" + this.mForTest + " execute return");
    }

    public int setAutoCleanAirState(int i) {
        Log.d(TAG, "setAutoCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAutoCleanAirState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setAvoidBlowPeopleState(int i, int i2) {
        Log.d(TAG, "setAvoidBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.d(TAG, "setAvoidBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setAvoidBlowPeopleStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setAvoidBlowPeopleStateCopilot(i2);
        }
        return 0;
    }

    public int setBlowPeopleState(int i, int i2) {
        Log.d(TAG, "setBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.d(TAG, "setBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setBlowPeopleStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setBlowPeopleStateCopilot(i2);
        }
        return 0;
    }

    public int setElectricDefrostState(int i) {
        Log.d(TAG, "setElectricDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setElectricDefrostState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    Log.d(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        switch (i) {
            case 369:
                setAcAntibacterialControlState(iIntValue);
                return 1;
            case 3317:
                setAutoCleanAirState(iIntValue);
                return 1;
            case 3323:
                setAcCompressorMode(0, iIntValue);
                return 1;
            case 3332:
                setAcCycleMode(0, iIntValue);
                return 1;
            case 3334:
                setAcDefrostState(0, 1, iIntValue);
                return 1;
            case 3337:
                setElectricDefrostState(iIntValue);
                return 1;
            case 3364:
                setAirOutletValue(1, iIntValue);
                return 1;
            case 3366:
                setAirOutletValue(0, iIntValue);
                return 1;
            case 3367:
                setAirOutletValue(3, iIntValue);
                return 1;
            case 3370:
                setAirOutletValue(0, iIntValue);
                return 1;
            case 3389:
                setAcMaxCoolingState(iIntValue);
                return 1;
            case 3418:
                setAirOutletValue(5, iIntValue);
                return 1;
            case 3420:
                setAirOutletValue(4, iIntValue);
                return 1;
            case 3422:
                setAirOutletValue(7, iIntValue);
                return 1;
            case 3424:
                setAirOutletValue(6, iIntValue);
                return 1;
            case 3448:
                if (iIntValue == 1) {
                    start(0);
                } else if (iIntValue == 2) {
                    stop(0);
                }
                return 1;
            case 3452:
                confirmCleanAirState(iIntValue);
                return 1;
            case 3456:
                setQuickCleanAirState(iIntValue);
                return 1;
            case 3463:
                setRearAcControlMode(0, iIntValue);
                return 1;
            case 3478:
                setRearAcKeyLockState(iIntValue);
                return 1;
            case 3485:
                setAcRearPanelLockState(iIntValue);
                return 1;
            case 3487:
                startRearAc(iIntValue);
                return 1;
            case 3501:
                setRearAcWindLevel(0, iIntValue);
                return 1;
            case 3504:
                setRearAcWindMode(0, iIntValue);
                return 1;
            case 3507:
                setAcRemoteCtrlTime(iIntValue);
                return 1;
            case 3525:
                setAcTemperatureControlMode(0, iIntValue);
                return 1;
            case 3530:
                setAcTemperature(2, iIntValue, 0, 0);
                return 1;
            case 3533:
                setAcTemperature(1, iIntValue, 0, 0);
                return 1;
            case 3536:
                setAcTemperature(3, iIntValue, 0, 0);
                return 1;
            case 3555:
                setAcVentilationState(0, iIntValue);
                return 1;
            case 3565:
                setAcWindLevel(0, iIntValue);
                return 1;
            default:
                Log.d(TAG, "case fail ! please check out");
                return -1;
        }
    }

    public int setFreeWindBtnState(int i, int i2) {
        Log.d(TAG, "setFreeWindBtnState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.d(TAG, "setFreeWindBtnState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setFreeWindBtnStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setFreeWindBtnStateCopilot(i2);
        }
        return 0;
    }

    public int setFrontRowAirOutletState(int i) {
        Log.d(TAG, "setFrontRowAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setFrontRowAirOutletState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setFrontRowAirOutletState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setIntelligentControlState(int i) {
        Log.d(TAG, "setIntelligentControlState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest != 0) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setIntelligentControlState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setIntelligentControlState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setMaxAcState(int i) {
        Log.d(TAG, "setMaxAcState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setMaxAcState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setQuickCleanAirState(int i) {
        Log.d(TAG, "setQuickCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setQuickCleanAirState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setRearAcControlMode(int i, int i2) {
        Log.d(TAG, "setRearAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        }
        Log.d(TAG, "setRearAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcControlMode(i, i2);
    }

    public int setRearAcKeyLockState(int i) {
        Log.d(TAG, "setRearAcKeyLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setRearAcKeyLockState(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRearAcLockState(int i) {
        Log.d(TAG, "setRearAcLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setRearAcLockState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setRearAcWindLevel(int i, int i2) {
        Log.d(TAG, "setRearAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setRearAcWindLevel(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRearAcWindMode(int i, int i2) {
        Log.d(TAG, "setRearAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setRearAcWindMode(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setSweepBlowState(int i, int i2) {
        Log.d(TAG, "setSweepBlowState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.d(TAG, "setSweepBlowState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setSweepBlowStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setSweepBlowStateCopilot(i2);
        }
        return 0;
    }

    public int start(int i) {
        Log.d(TAG, "start is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().start(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int startRearAc(int i) {
        Log.d(TAG, "startRearAc is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().startRearAc(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int stop(int i) {
        Log.d(TAG, "stop is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().stop(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int stopRearAc(int i) {
        Log.d(TAG, "stopRearAc is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().stopRearAc(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            throw null;
        }
        Log.d(TAG, "unsubscribeHvacCallback: " + absBYDAutoAcListener);
        if (absBYDAutoAcListener == null) {
            return;
        }
        this.mAbsBydAutoAcListeners.remove(absBYDAutoAcListener);
        unregisterConnectListener(absBYDAutoAcListener);
    }

    public void registerListener(AbsBYDAutoAcListener absBYDAutoAcListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe ac listener " + absBYDAutoAcListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoAcListener == null) {
            return;
        }
        if (this.mForTest == 0) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            throw null;
        }
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (!this.mAbsBydAutoAcListeners.contains(absBYDAutoAcListener)) {
            this.mAbsBydAutoAcListeners.add(absBYDAutoAcListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoAcListener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }
}
