package android.hardware.bydauto.instrument.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import e.C1147j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class InstrumentManagerImpl extends AbsTsMyCar {
    private static final int INSTRUMENT_LCD_SCREEN = 2;
    private static final int INSTRUMENT_PEN_DUAN_SCREEN = 1;
    private static final int INSTRUMENT_SCREEN_BROKE_CODE = 2;
    private static final int INSTRUMENT_SCREEN_LARGE_SIZE = 5;
    private static final int INSTRUMENT_SCREEN_SMALL_SIZE = 3;
    private static final int INSTRUMENT_SCREEN_SPLICING = 4;
    private static final int INSTRUMENT_SCREEN_TWO_IN_ONE = 1;
    private static final int S_CALL_STATE_CALLING = 3;
    private static final int S_CALL_STATE_DEFAULT = 0;
    private static final int S_CALL_STATE_HANGUP_CALL = 5;
    private static final int S_CALL_STATE_IGNORE_CALL = 4;
    private static final int S_CALL_STATE_INCOMING_CALL = 2;
    private static final int S_CALL_STATE_MAKE_CALL = 1;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = -1;
    private static final String S_KEY_FOR_TEST = "key_for_test_InstrumentManagerImpl";
    private static final int S_NAVIGATION_STATE_END = 2;
    private static final int S_NAVIGATION_STATE_START = 1;
    private static final int S_WHUD_NAVIGATION_STATE_END = 4;
    private static final int S_WHUD_NAVIGATION_STATE_START = 2;
    private static final String TAG = "InstrumentManagerImpl";
    private static final int V_1 = 1;
    private static final int V_10 = 10;
    private static final int V_11 = 11;
    private static final int V_12 = 12;
    private static final int V_13 = 13;
    private static final int V_14 = 14;
    private static final int V_15 = 15;
    private static final int V_16 = 16;
    private static final int V_17 = 17;
    private static final int V_18 = 18;
    private static final int V_19 = 19;
    private static final int V_2 = 2;
    private static final int V_20 = 20;
    private static final int V_21 = 21;
    private static final int V_22 = 22;
    private static final int V_23 = 23;
    private static final int V_24 = 24;
    private static final int V_25 = 25;
    private static final int V_26 = 26;
    private static final int V_27 = 27;
    private static final int V_28 = 28;
    private static final int V_29 = 29;
    private static final int V_3 = 3;
    private static final int V_30 = 30;
    private static final int V_31 = 31;
    private static final int V_32 = 32;
    private static final int V_33 = 33;
    private static final int V_34 = 34;
    private static final int V_35 = 35;
    private static final int V_36 = 36;
    private static final int V_37 = 37;
    private static final int V_38 = 38;
    private static final int V_39 = 39;
    private static final int V_4 = 4;
    private static final int V_40 = 40;
    private static final int V_41 = 41;
    private static final int V_42 = 42;
    private static final int V_43 = 43;
    private static final int V_44 = 44;
    private static final int V_45 = 45;
    private static final int V_46 = 46;
    private static final int V_47 = 47;
    private static final int V_48 = 48;
    private static final int V_49 = 49;
    private static final int V_5 = 5;
    private static final int V_50 = 50;
    private static final int V_51 = 51;
    private static final int V_52 = 52;
    private static final int V_53 = 53;
    private static final int V_54 = 54;
    private static final int V_55 = 55;
    private static final int V_56 = 56;
    private static final int V_57 = 57;
    private static final int V_6 = 6;
    private static final int V_7 = 7;
    private static final int V_8 = 8;
    private static final int V_9 = 9;
    private static volatile InstrumentManagerImpl sInstance;
    private AbsBYDAutoInstrumentListener mAbsBydAutoInstrumentListener;
    private CabinPacket mCabinPacket;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private C1147j mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoInstrumentListener> mAbsBydAutoInstrumentListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mIgnoreBydPermission = false;

    public InstrumentManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                this.mTestCallback = new C1147j();
                a.q(new StringBuilder("InstrumentManagerImpl mForTestValue is"), this.mForTest, " execute return", TAG);
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.instrument.tsimpl.InstrumentManagerImpl.1
            public void onCabinBinderChanged(boolean z6) {
                Log.v(InstrumentManagerImpl.TAG, "onCabinBinderChanged is ");
            }

            public void onCabinChanged(int i10, int i11, CabinPacket cabinPacket) {
                if (InstrumentManagerImpl.this.mAbsBydAutoInstrumentListeners.isEmpty()) {
                    return;
                }
                InstrumentManagerImpl.this.instrumentCallback(i10, i11, cabinPacket);
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static InstrumentManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (InstrumentManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new InstrumentManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void instrumentCallback(int i7, int i10, CabinPacket cabinPacket) {
        if (cabinPacket == null) {
            Log.e(TAG, "cabinPacket is null");
        }
        Log.i(TAG, "cabinPacket propertyId:" + i7);
        switch (i7) {
            case InstrumentId.ID_INSTRUMENT_BRIGHTNESS_LEFT_R /* 557857026 */:
                Iterator<AbsBYDAutoInstrumentListener> it = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it.hasNext()) {
                    it.next().onBacklightBrightnessChanged(cabinPacket.getInstrumentBrightnessLeft());
                }
                break;
            case InstrumentId.ID_INSTRUMENT_BRIGHTNESS_R /* 557857027 */:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onBacklightBrightnessChanged(cabinPacket.getInstrumentBrightnessR());
                }
                break;
            default:
                switch (i7) {
                    case InstrumentId.ID_INSTRUMENT_AUTO_DIMM_STATE_R /* 557857029 */:
                        Iterator<AbsBYDAutoInstrumentListener> it3 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it3.hasNext()) {
                            it3.next().onInstrumentAutoDimmStateChange(cabinPacket.getInstrumentAutoDimmStateR());
                        }
                        break;
                    case InstrumentId.ID_MILES_TYPE_R /* 557857032 */:
                        Iterator<AbsBYDAutoInstrumentListener> it4 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it4.hasNext()) {
                            it4.next().onMilesTypeChanged(cabinPacket.getMilesTypeR());
                        }
                        break;
                    case InstrumentId.ID_MAINTENANCE_INFO_ONER /* 557857039 */:
                        Iterator<AbsBYDAutoInstrumentListener> it5 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it5.hasNext()) {
                            it5.next().onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoOneR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case InstrumentId.ID_UNIT_PRESSURE_LEFT_R /* 557857047 */:
                        Iterator<AbsBYDAutoInstrumentListener> it6 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it6.hasNext()) {
                            it6.next().onUnitChanged(1, cabinPacket.getUnitPressureLeftR());
                        }
                        caseFeatureIds(4208, Integer.valueOf(cabinPacket.getUnitPressureLeftR()));
                        break;
                    case InstrumentId.ID_UNIT_FUEL_CONSUMPTION_AND_DISTANCE_LEFT_R /* 557857049 */:
                        Iterator<AbsBYDAutoInstrumentListener> it7 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it7.hasNext()) {
                            it7.next().onUnitChanged(2, cabinPacket.getUnitFuelConsumptionAndDistanceLeftR());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_LEAVE_VEHICLE_IMMD_RED /* 557857054 */:
                        Iterator<AbsBYDAutoInstrumentListener> it8 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it8.hasNext()) {
                            it8.next().onMalfunctionInfoChanged(1, cabinPacket.getMalFunctionInfosPleaseLeaveVehicleImmdRed());
                        }
                        break;
                    case InstrumentId.ID_DAY_NIGHT_MODER /* 557857144 */:
                        Iterator<AbsBYDAutoInstrumentListener> it9 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it9.hasNext()) {
                            it9.next().onDayNightModeChanged(cabinPacket.getDayNightModeR());
                        }
                        break;
                    case InstrumentId.ID_MAINTENANCE_INFO_TWOR /* 557857146 */:
                        Iterator<AbsBYDAutoInstrumentListener> it10 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it10.hasNext()) {
                            it10.next().onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoTwoR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case InstrumentId.ID_TOTAL_MILEAGER /* 557857152 */:
                        Iterator<AbsBYDAutoInstrumentListener> it11 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it11.hasNext()) {
                            it11.next().onTotalMileageChanged(cabinPacket.getTotalMileageR());
                        }
                        break;
                    case InstrumentId.ID_MAINTENANCE_INFO_FOURR /* 557857156 */:
                        Iterator<AbsBYDAutoInstrumentListener> it12 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it12.hasNext()) {
                            it12.next().onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoFourR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case InstrumentId.ID_SPEED_UP_FORM_INS_R /* 557857158 */:
                        Iterator<AbsBYDAutoInstrumentListener> it13 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it13.hasNext()) {
                            it13.next().onSpeedUpFormInsChange(cabinPacket.getSpeedUpFormIns());
                        }
                        break;
                    case InstrumentId.ID_MILEAGE_UNITR /* 557868033 */:
                        Iterator<AbsBYDAutoInstrumentListener> it14 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it14.hasNext()) {
                            it14.next().onMileageUnitChanged(cabinPacket.getMileageUnitR());
                        }
                        break;
                    case InstrumentId.ID_AREA_INFO_R /* 557868037 */:
                        Iterator<AbsBYDAutoInstrumentListener> it15 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it15.hasNext()) {
                            it15.next().onAreaInfoChanged(cabinPacket.getAreaInfoR());
                        }
                        break;
                    case InstrumentId.ID_LOW_POWER_BATTERY_WARN_LIGHT_COLOR_R /* 557869062 */:
                        Iterator<AbsBYDAutoInstrumentListener> it16 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it16.hasNext()) {
                            it16.next().onLowPowerBatteryWarnLightColorChanged(cabinPacket.getLowPowerBatteryWarnLightColorR());
                        }
                        break;
                    case InstrumentId.ID_LOW_FUEL_WARN_LIGHT_COLOR_R /* 557869067 */:
                        Iterator<AbsBYDAutoInstrumentListener> it17 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it17.hasNext()) {
                            it17.next().onLowFuelWarnLightColorChanged(cabinPacket.getLowFuelWarnLightColorR());
                        }
                        break;
                    case InstrumentId.ID_INS_CENTER_DISPLAYR /* 557875262 */:
                        Iterator<AbsBYDAutoInstrumentListener> it18 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it18.hasNext()) {
                            it18.next().onINSCenterDisplay(cabinPacket.getINSCenterDisplayR());
                        }
                        break;
                    case InstrumentId.ID_EXTERNAL_CHARGING_POWER /* 559954186 */:
                        Iterator<AbsBYDAutoInstrumentListener> it19 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it19.hasNext()) {
                            it19.next().onExternalChargingPowerChanged(cabinPacket.getExternalChargingPower());
                        }
                        caseFeatureIds(3932, Integer.valueOf((int) cabinPacket.getExternalChargingPower()));
                        caseFeatureIds(4085, Integer.valueOf((int) cabinPacket.getExternalChargingPower()));
                        break;
                    default:
                        switch (i7) {
                            case InstrumentId.ID_UNIT_POWER_LEFT_R /* 557857051 */:
                                Iterator<AbsBYDAutoInstrumentListener> it20 = this.mAbsBydAutoInstrumentListeners.iterator();
                                while (it20.hasNext()) {
                                    it20.next().onUnitChanged(3, cabinPacket.getUnitPowerLeftR());
                                }
                                break;
                            case InstrumentId.ID_ALARM_BUZZLE_STATE /* 557857052 */:
                                Iterator<AbsBYDAutoInstrumentListener> it21 = this.mAbsBydAutoInstrumentListeners.iterator();
                                while (it21.hasNext()) {
                                    it21.next().onAlarmBuzzleStateChange(cabinPacket.getAlarmBuzzleState());
                                }
                                break;
                            default:
                                switch (i7) {
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_BRAKE_SYS_RED /* 557857056 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it22 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it22.hasNext()) {
                                            it22.next().onMalfunctionInfoChanged(2, cabinPacket.getMalFunctionInfosPleaseCheckTheBrakeSysRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_BRAKE_FLUID_LEVEL_IS_LOW_RED /* 557857057 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it23 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it23.hasNext()) {
                                            it23.next().onMalfunctionInfoChanged(3, cabinPacket.getMalFunctionInfosBrakeFluidLevelIsLowRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_STEER_SYS_RED /* 557857058 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it24 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it24.hasNext()) {
                                            it24.next().onMalfunctionInfoChanged(4, cabinPacket.getMalFunctionInfosPleaseCheckTheSteerSysRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH1_RED /* 557857059 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it25 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it25.hasNext()) {
                                            it25.next().onMalfunctionInfoChanged(5, cabinPacket.getMalFunctionInfosEngineCoolTempTooHigh1Red());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH2_RED /* 557857060 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it26 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it26.hasNext()) {
                                            it26.next().onMalfunctionInfoChanged(6, cabinPacket.getMalFunctionInfosEngineCoolTempTooHigh2Red());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_INSUFFICIENT_OIL_PRESSURE_RED /* 557857061 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it27 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it27.hasNext()) {
                                            it27.next().onMalfunctionInfoChanged(7, cabinPacket.getMalFunctionInfosInsufficientOilPressureRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_POWER_SYS_RED /* 557857062 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it28 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it28.hasNext()) {
                                            it28.next().onMalfunctionInfoChanged(8, cabinPacket.getMalFunctionInfosPleaseCheckThePowerSysRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LV_POWER_SUPPLY_SYS_FAULTY_RED /* 557857063 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it29 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it29.hasNext()) {
                                            it29.next().onMalfunctionInfoChanged(9, cabinPacket.getMalFunctionInfosLvPowerSupplySysFaultyRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_START_TYPE_IRON_BAT_FAULTY_RED /* 557857064 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it30 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it30.hasNext()) {
                                            it30.next().onMalfunctionInfoChanged(10, cabinPacket.getMalFunctionInfosStartTypeIronBatFaultyRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_VEHICLE_NET_RED /* 557857065 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it31 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it31.hasNext()) {
                                            it31.next().onMalfunctionInfoChanged(11, cabinPacket.getMalFunctionInfosPleaseCheckVehicleNetRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMI_SYS1_RED /* 557857066 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it32 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it32.hasNext()) {
                                            it32.next().onMalfunctionInfoChanged(12, cabinPacket.getMalFunctionInfosPleaseCheckTransmiSys1Red());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMIS_SYS2_RED /* 557857067 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it33 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it33.hasNext()) {
                                            it33.next().onMalfunctionInfoChanged(13, cabinPacket.getMalFunctionInfosPleaseCheckTransmisSys2Red());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LOW_BATTERY_LIMITED_POWER_RED /* 557857068 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it34 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it34.hasNext()) {
                                            it34.next().onMalfunctionInfoChanged(14, cabinPacket.getMalFunctionInfosLowBatteryLimitedPowerRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_EV_FUNCTION_IS_LIMITED_RED /* 557857069 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it35 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it35.hasNext()) {
                                            it35.next().onMalfunctionInfoChanged(15, cabinPacket.getMalFunctionInfosEvFunctionIsLimitedRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_EXIT_SMART_DRIVING_MODE_RED /* 557857070 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it36 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it36.hasNext()) {
                                            it36.next().onMalfunctionInfoChanged(16, cabinPacket.getMalFunctionInfosExitSmartDrivingModeRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_INTELLIGENT_DRIVE_FAILURE_RED /* 557857071 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it37 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it37.hasNext()) {
                                            it37.next().onMalfunctionInfoChanged(17, cabinPacket.getMalFunctionInfosIntelligentDriveFailureRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_EPB_RED /* 557857072 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it38 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it38.hasNext()) {
                                            it38.next().onMalfunctionInfoChanged(18, cabinPacket.getMalFunctionInfosPleaseCheckEpbRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LACK_OF_PARK_CAPACITY_YELLOW /* 557857073 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it39 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it39.hasNext()) {
                                            it39.next().onMalfunctionInfoChanged(19, cabinPacket.getMalFunctionInfosLackOfParkCapacityYellow());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LBMS_RED /* 557857074 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it40 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it40.hasNext()) {
                                            it40.next().onMalfunctionInfoChanged(20, cabinPacket.getMalFunctionInfosPleaseCheckLbmsRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LV_SUP_SYS_RED /* 557857075 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it41 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it41.hasNext()) {
                                            it41.next().onMalfunctionInfoChanged(21, cabinPacket.getMalFunctionInfosPleaseCheckLvSupSysRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ENGINE_SYSTEM_RED /* 557857076 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it42 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it42.hasNext()) {
                                            it42.next().onMalfunctionInfoChanged(22, cabinPacket.getMalFunctionInfosPleaseCheckEngineSystemRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENG_ACCESSORIES_LIMITED_RED /* 557857077 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it43 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it43.hasNext()) {
                                            it43.next().onMalfunctionInfoChanged(23, cabinPacket.getMalFunctionInfosEngAccessoriesLimitedRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_TRANSMIS_LIMITEDIN_FUNC_RED /* 557857078 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it44 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it44.hasNext()) {
                                            it44.next().onMalfunctionInfoChanged(24, cabinPacket.getMalFunctionInfosTransmisLimitedinFuncRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_HIGH_CLUTCH_TEMPERATURE_RED /* 557857079 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it45 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it45.hasNext()) {
                                            it45.next().onMalfunctionInfoChanged(25, cabinPacket.getMalFunctionInfosHighClutchTemperatureRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_GEARBOX_OIL_TEMP_TOO_HIGH_RED /* 557857080 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it46 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it46.hasNext()) {
                                            it46.next().onMalfunctionInfoChanged(26, cabinPacket.getMalFunctionInfosGearboxOilTempTooHighRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SRS_SYSTEM_RED /* 557857081 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it47 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it47.hasNext()) {
                                            it47.next().onMalfunctionInfoChanged(27, cabinPacket.getMalFunctionInfosPleaseCheckSrsSystemRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ADB_SYSTEM_RED /* 557857082 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it48 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it48.hasNext()) {
                                            it48.next().onMalfunctionInfoChanged(28, cabinPacket.getMalFunctionInfosPleaseCheckAdbSystemRed());
                                        }
                                        break;
                                    default:
                                        switch (i7) {
                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_HEADLIGHT_SYS_YELLOW /* 557857084 */:
                                                Iterator<AbsBYDAutoInstrumentListener> it49 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                while (it49.hasNext()) {
                                                    it49.next().onMalfunctionInfoChanged(29, cabinPacket.getMalFunctionInfosCheckHeadlightSysYellow());
                                                }
                                                break;
                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_ENGINE_START_FAILED_RED /* 557857085 */:
                                                Iterator<AbsBYDAutoInstrumentListener> it50 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                while (it50.hasNext()) {
                                                    it50.next().onMalfunctionInfoChanged(30, cabinPacket.getMalFunctionInfosEngineStartFailedRed());
                                                }
                                                break;
                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_COOLANT_LIQUI_LEVEL_LOW_YELLOW /* 557857086 */:
                                                Iterator<AbsBYDAutoInstrumentListener> it51 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                while (it51.hasNext()) {
                                                    it51.next().onMalfunctionInfoChanged(31, cabinPacket.getMalFunctionInfosCoolantLiquiLevelLowYellow());
                                                }
                                                break;
                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_AC_HOT_MANA_FAIL_YELLOW /* 557857087 */:
                                                Iterator<AbsBYDAutoInstrumentListener> it52 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                while (it52.hasNext()) {
                                                    it52.next().onMalfunctionInfoChanged(32, cabinPacket.getMalFunctionInfosAcHotManaFailYellow());
                                                }
                                                break;
                                            default:
                                                switch (i7) {
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_THE_OIL_MONIT_SYS_YELLOW /* 557857090 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it53 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it53.hasNext()) {
                                                            it53.next().onMalfunctionInfoChanged(33, cabinPacket.getMalFunctionInfosCheckTheOilMonitSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_TIRE_PRES_MON_SYS_YELLOW /* 557857091 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it54 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it54.hasNext()) {
                                                            it54.next().onMalfunctionInfoChanged(34, cabinPacket.getMalFunctionInfosCheckTirePresMonSysYellow());
                                                        }
                                                        break;
                                                    default:
                                                        switch (i7) {
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_THE_BRAK_SYS_YELLOW /* 557857093 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it55 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it55.hasNext()) {
                                                                    it55.next().onMalfunctionInfoChanged(35, cabinPacket.getMalFunctionInfosCheckTheBrakSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ABS_SYS_YELLOW /* 557857094 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it56 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it56.hasNext()) {
                                                                    it56.next().onMalfunctionInfoChanged(36, cabinPacket.getMalFunctionInfosPleaseCheckAbsSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_GENERATION_SYS_YELLOW /* 557857095 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it57 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it57.hasNext()) {
                                                                    it57.next().onMalfunctionInfoChanged(37, cabinPacket.getMalFunctionInfosCheckGenerationSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_GEAR_SYS_YELLOW /* 557857096 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it58 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it58.hasNext()) {
                                                                    it58.next().onMalfunctionInfoChanged(38, cabinPacket.getMalFunctionInfosPleaseCheckGearSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ESP_SYS_YELLOW /* 557857097 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it59 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it59.hasNext()) {
                                                                    it59.next().onMalfunctionInfoChanged(39, cabinPacket.getMalFunctionInfosPleaseCheckEspSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_HDC_SYS_YELLOW /* 557857098 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it60 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it60.hasNext()) {
                                                                    it60.next().onMalfunctionInfoChanged(40, cabinPacket.getMalFunctionInfosPleaseCheckHdcSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_BRAKE_PLATE_YELLOW /* 557857099 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it61 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it61.hasNext()) {
                                                                    it61.next().onMalfunctionInfoChanged(41, cabinPacket.getMalFunctionInfosCheckBrakePlateYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_VIDEO_CONTROLLER_YELLOW /* 557857100 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it62 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it62.hasNext()) {
                                                                    it62.next().onMalfunctionInfoChanged(42, cabinPacket.getMalFunctionInfosCheckVideoControllerYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_FMRR_YELLOW /* 557857101 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it63 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it63.hasNext()) {
                                                                    it63.next().onMalfunctionInfoChanged(43, cabinPacket.getMalFunctionInfosCheckFmrrYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_AEB_FUNCT_LIMIT_YELLOW /* 557857102 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it64 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it64.hasNext()) {
                                                                    it64.next().onMalfunctionInfoChanged(44, cabinPacket.getMalFunctionInfosAebFunctLimitYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_PRED_COLL_ALA_SYS_YELLOW /* 557857103 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it65 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it65.hasNext()) {
                                                                    it65.next().onMalfunctionInfoChanged(45, cabinPacket.getMalFunctionInfosCheckPredCollAlaSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_PEDESTRI_DETE_SYS_YELLOW /* 557857104 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it66 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it66.hasNext()) {
                                                                    it66.next().onMalfunctionInfoChanged(46, cabinPacket.getMalFunctionInfosCheckPedestriDeteSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMITED_LANE_KEEPING_YELLOW /* 557857105 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it67 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it67.hasNext()) {
                                                                    it67.next().onMalfunctionInfoChanged(47, cabinPacket.getMalFunctionInfosLimitedLaneKeepingYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMITED_ADAPTIVE_CRUISE_YELLOW /* 557857106 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it68 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it68.hasNext()) {
                                                                    it68.next().onMalfunctionInfoChanged(48, cabinPacket.getMalFunctionInfosLimitedAdaptiveCruiseYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMIT_LANE_DEPARTU_FUNC_YELLOW /* 557857107 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it69 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it69.hasNext()) {
                                                                    it69.next().onMalfunctionInfoChanged(49, cabinPacket.getMalFunctionInfosLimitLaneDepartuFuncYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMITED_BLIND_SPOT_MONI_YELLOW /* 557857108 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it70 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it70.hasNext()) {
                                                                    it70.next().onMalfunctionInfoChanged(50, cabinPacket.getMalFunctionInfosLimitedBlindSpotMoniYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_DISCHARGE_EQUIP_YELLOW /* 557857109 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it71 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it71.hasNext()) {
                                                                    it71.next().onMalfunctionInfoChanged(51, cabinPacket.getMalFunctionInfosCheckDischargeEquipYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_CHARG_OUT_EQUIP_YELLOW /* 557857110 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it72 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it72.hasNext()) {
                                                                    it72.next().onMalfunctionInfoChanged(52, cabinPacket.getMalFunctionInfosCheckChargOutEquipYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_ON_BOARDCHAR_SYS_YELLOW /* 557857111 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it73 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it73.hasNext()) {
                                                                    it73.next().onMalfunctionInfoChanged(53, cabinPacket.getMalFunctionInfosCheckOnBoardcharSysYellow());
                                                                }
                                                                break;
                                                            default:
                                                                switch (i7) {
                                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_APA_FUNCTION_LIMIT_WHITE /* 557857129 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it74 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it74.hasNext()) {
                                                                            it74.next().onMalfunctionInfoChanged(54, cabinPacket.getMalFunctionInfosApaFunctionLimitWhite());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_MOT_PRET_BELT_SYS_YELLOW /* 557857130 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it75 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it75.hasNext()) {
                                                                            it75.next().onMalfunctionInfoChanged(55, cabinPacket.getMalFunctionInfosCheckMotPretBeltSysYellow());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_MEMORY_SYS_YELLOW /* 557857131 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it76 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it76.hasNext()) {
                                                                            it76.next().onMalfunctionInfoChanged(56, cabinPacket.getMalFunctionInfosPleaseCheckMemorySysYellow());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SUNROO_SYS_YELLOW /* 557857132 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it77 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it77.hasNext()) {
                                                                            it77.next().onMalfunctionInfoChanged(57, cabinPacket.getMalFunctionInfosPleaseCheckSunrooSysYellow());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_INS_NAV_DISPLAY_MODE_R /* 557857133 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it78 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it78.hasNext()) {
                                                                            it78.next().onINSNavDisplayMode(cabinPacket.getINSNavDisplayModeR());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (i7) {
                                                                            case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY_SEND_4C1 /* 557857135 */:
                                                                                Iterator<AbsBYDAutoInstrumentListener> it79 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                while (it79.hasNext()) {
                                                                                    it79.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplaySend4C1());
                                                                                }
                                                                                break;
                                                                            case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY_RESPOND_40C /* 557857136 */:
                                                                                Iterator<AbsBYDAutoInstrumentListener> it80 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                while (it80.hasNext()) {
                                                                                    it80.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplayRespond40C());
                                                                                }
                                                                                break;
                                                                            case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY_SEND_26F /* 557857137 */:
                                                                                Iterator<AbsBYDAutoInstrumentListener> it81 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                while (it81.hasNext()) {
                                                                                    it81.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplaySend26F());
                                                                                }
                                                                                break;
                                                                            case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY /* 557857138 */:
                                                                                Iterator<AbsBYDAutoInstrumentListener> it82 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                while (it82.hasNext()) {
                                                                                    it82.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplay());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (i7) {
                                                                                    case InstrumentId.ID_SPEED_UP_COUNT_STATER /* 557867009 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it83 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it83.hasNext()) {
                                                                                            it83.next().onSpeedUpCountStateChanged(cabinPacket.getSpeedUpCountStateR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_SPEED_UNITR /* 557867010 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it84 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it84.hasNext()) {
                                                                                            it84.next().onSpeedUnitChanged(cabinPacket.getSpeedUnit());
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
                        break;
                }
                break;
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    switch (i7) {
                        case 686:
                            int instrumentBrightness = getInstrumentBrightness();
                            bYDAutoEventValue.intValue = instrumentBrightness;
                            sparseArray.put(686, Integer.valueOf(instrumentBrightness));
                            break;
                        case 3820:
                            int milesType = getMilesType();
                            bYDAutoEventValue.intValue = milesType;
                            sparseArray.put(3820, Integer.valueOf(milesType));
                            break;
                        case 3844:
                            int instrumentBrightness2 = getInstrumentBrightness();
                            bYDAutoEventValue.intValue = instrumentBrightness2;
                            sparseArray.put(3844, Integer.valueOf(instrumentBrightness2));
                            break;
                        case 3932:
                            float externalChargingPower = getExternalChargingPower();
                            bYDAutoEventValue.floatValue = externalChargingPower;
                            sparseArray.put(3932, Float.valueOf(externalChargingPower));
                            break;
                        case 4038:
                            int mileageUnit = getMileageUnit();
                            bYDAutoEventValue.intValue = mileageUnit;
                            sparseArray.put(4038, Integer.valueOf(mileageUnit));
                            break;
                        case 4056:
                            int speedUnit = getSpeedUnit();
                            bYDAutoEventValue.intValue = speedUnit;
                            sparseArray.put(4056, Integer.valueOf(speedUnit));
                            break;
                        case 4167:
                            int instrumentConfigure = getInstrumentConfigure();
                            bYDAutoEventValue.intValue = instrumentConfigure;
                            sparseArray.put(4167, Integer.valueOf(instrumentConfigure));
                            break;
                        case 4200:
                            int unit = getUnit(2);
                            bYDAutoEventValue.intValue = unit;
                            sparseArray.put(4200, Integer.valueOf(unit));
                            break;
                        case 4205:
                            int unit2 = getUnit(3);
                            bYDAutoEventValue.intValue = unit2;
                            sparseArray.put(4205, Integer.valueOf(unit2));
                            break;
                        case 4208:
                            int unit3 = getUnit(1);
                            bYDAutoEventValue.intValue = unit3;
                            sparseArray.put(4208, Integer.valueOf(unit3));
                            break;
                        case 4570:
                            int sportModeState = getSportModeState();
                            bYDAutoEventValue.intValue = sportModeState;
                            sparseArray.put(4570, Integer.valueOf(sportModeState));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAlarmBuzzleState() {
        Log.v(TAG, "getMalfunctionInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMalfunctionInfo mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAlarmBuzzleState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAreaInfoR() {
        Log.i(TAG, "getAreaInfoR is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "AreaInfoR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAreaInfoR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDayNightMode() {
        Log.v(TAG, "getDayNightMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getDayNightMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getDayNightMode();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public float getExternalChargingPower() {
        Log.v(TAG, "getExternalChargingPower is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getExternalChargingPower mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0.0f;
        }
        float externalChargingPower = this.mTsManagerImpl.getCarCabinAdapterManager().getExternalChargingPower();
        double d = externalChargingPower;
        if (d <= 99999.9d && d >= 0.0d) {
            return externalChargingPower;
        }
        Log.v(TAG, "The value is illegal");
        return -1.0f;
    }

    public int getINSAdasCenterDisplay() {
        Log.v(TAG, "getINSAdasCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getINSAdasCenterDisplay mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getINSAdasCenterDisplay();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getINSCenterDisplay() {
        Log.v(TAG, "getINSCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getINSCenterDisplay mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getINSCenterDisplay();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getInsNavDisplayMode() {
        Log.v(TAG, "getINSNavDisplayMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getINSNavDisplayMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getINSNavDisplayMode();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstrumentAutoDimmState() {
        Log.v(TAG, "getInstrumentAutoDimmState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getInstrumentAutoDimmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentAutoDimmState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getInstrumentBrightness() {
        Log.v(TAG, "getInstrumentBrightness is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getInstrumentBrightness mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        int instrumentBrightnessLeft = this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentBrightnessLeft();
        if (instrumentBrightnessLeft >= 1 && instrumentBrightnessLeft <= 22) {
            return instrumentBrightnessLeft;
        }
        Log.v(TAG, "The value is illegal！");
        return -1;
    }

    public int getInstrumentConfigure() {
        Log.v(TAG, "getInstrumentConfigure is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return 1;
    }

    public int getInstrumentScreenType() {
        Log.v(TAG, "getInstrumentScreenType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return 2;
    }

    @Deprecated
    public int getInstrumentTheme() {
        Log.v(TAG, "getInstrumentTheme is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getInstrumentTheme mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentTheme();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLowFuelWarnLightColor() {
        Log.i(TAG, "getLowFuelWarnLightColor is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "AreaInfoR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getLowFuelWarnLightColor();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLowPowerBatteryWarnLightColor() {
        Log.i(TAG, "getLowPowerBatteryWarnLightColor is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "AreaInfoR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getLowPowerBatteryWarnLightColor();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMaintenanceInfo(int i7) {
        Log.v(TAG, "getMaintenanceInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMaintenanceInfo mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
        }
        return 0;
    }

    @Deprecated
    public int getMaintenanceInfoMileState() {
        Log.v(TAG, "getMaintenanceInfoMileState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMaintenanceInfoMileState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMaintenanceInfoMileState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getMaintenanceInfoTimeState() {
        Log.v(TAG, "getMaintenanceInfoTimeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMaintenanceInfoTimeState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMaintenanceInfoTimeState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getMalfunctionInfo(int i7) {
        Log.v(TAG, "getMalfunctionInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMalfunctionInfo mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMalfunctionInfo(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getMalfunctionInfos() {
        Log.v(TAG, "getMalfunctionInfos is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMalfunctionInfos mForTestValue is"), this.mForTest, " execute return", TAG);
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMalfunctionInfos();
        }
        Log.e(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getMileageUnit() {
        Log.v(TAG, "getMileageUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMileageUnit mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMileageUnit();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public long getMilesByType(int i7) {
        Log.v(TAG, "getMilesByType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMilesByType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0L;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMilesByType(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0L;
    }

    public int getMilesType() {
        Log.v(TAG, "getMilesType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMilesType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMilesType();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSpeedUnit() {
        Log.v(TAG, "getSpeedUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSpeedUnit mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUnit();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSpeedUpCountState() {
        Log.v(TAG, "getSpeedUpCountState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSpeedUpCountState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUpCountState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSpeedUpFormIns() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" getSpeedUpFormIns mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUpFormIns();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSportModeState() {
        Log.v(TAG, "getSportModeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSportModeState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getSportModeState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getTotalMileage() {
        Log.v(TAG, "getTotalMileage is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getTotalMileage mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        int totalMileage = this.mTsManagerImpl.getCarCabinAdapterManager().getTotalMileage();
        if (totalMileage >= 0 && totalMileage <= 99999999) {
            return totalMileage;
        }
        Log.v(TAG, "The vale is illegal");
        return -1;
    }

    public int getUnit(int i7) {
        Log.v(TAG, "getUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getUnit mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitTemperatureLeft();
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitPressureLeft();
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitFuelConsumptionAndDistanceLeft();
        }
        if (i7 == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitPowerLeft();
        }
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoInstrumentListener> it = this.mAbsBydAutoInstrumentListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        registerListener(absBYDAutoInstrumentListener, new int[0]);
    }

    public int setAreaInfo(int i7) {
        Log.i(TAG, "setAreaInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAreaInfo mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setAreaInfo(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallState(int i7) {
        Log.v(TAG, "setCallState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        if (i7 != 0 && i7 != 1 && i7 != 2 && i7 != 3 && i7 != 4 && i7 != 5) {
            return -1;
        }
        b.s("setCallState value = ", i7, TAG);
        return this.mTsManagerImpl.getGeneralAdapterManager().setCallState(i7);
    }

    public int setDayNightMode(int i7) {
        Log.v(TAG, "setDayNightMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setDayNightMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setDayNightMode(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).getClass();
            } else if (obj instanceof Double) {
                ((Double) obj).getClass();
            } else if (!(obj instanceof byte[])) {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        switch (i7) {
            case 607:
                setINSAdasCenterDisplay(iIntValue);
                Log.v(TAG, "Setting.SETTING_ADAS_DRIVER_ASSISTENCE_INFO_DISPLAY_SET:");
                return 1;
            case 687:
                setINSCenterDisplay(iIntValue);
                Log.v(TAG, "Setting.SETTING_INSTRUMENT_DRIVING_INFORMATION_SET:");
                return 1;
            case 736:
                setMilesType(iIntValue);
                Log.v(TAG, "Setting.SETTING_MILLAGE_INFO_DISPLAY_SET:");
                return 1;
            case 1222:
                setDayNightMode(iIntValue);
                Log.v(TAG, "Setting.SET_METER_DEPTH_MODE_SET:");
                return 1;
            case 3843:
                setInstrumentAutoDimmState(iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_BACKLIGHT_AUTO_MODE_STATE_SET:");
                return 1;
            case 3845:
                setInstrumentBrightness(iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_BACKLIGHT_BRIGHTNESS_SET:");
                return 1;
            case 4114:
                setMaintenanceInfo(2, iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_MAINTENANCE_MILEAGE_SET:");
                return 1;
            case 4116:
                setMaintenanceInfo(1, iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_MAINTENANCE_TIME_SET:");
                return 1;
            case 4202:
                setUnit(2, iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_FUELCON_DISTANCE_SET:");
                return 1;
            case 4207:
                setUnit(3, iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_POWER_SET:");
                return 1;
            case 4210:
                setUnit(1, iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_PRESSURE_SET:");
                return 1;
            case 4215:
                setUnit(0, iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_TEMPERATURE_SET:");
                return 1;
            case 4217:
                setSpeedUpCountState(iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_ACCELERATION_TIMER_DISPLAY_SET:");
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }

    public int setINSAdasCenterDisplay(int i7) {
        Log.v(TAG, "setINSAdasCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setINSAdasCenterDisplay mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setINSAdasCenterDisplay(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setINSCenterDisplay(int i7) {
        Log.v(TAG, "setINSCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setINSCenterDisplay mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setINSCenterDisplay(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setINSNavDisplayMode(int i7) {
        Log.v(TAG, "setINSNavDisplayMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setINSNavDisplayMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setINSNavDisplayMode(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setInstrumentAutoDimmState(int i7) {
        Log.v(TAG, "setInstrumentAutoDimmState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(7003, i7);
            a.q(new StringBuilder("setInstrumentAutoDimmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentAutoDimmState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int setInstrumentBrightness(int i7) {
        Log.v(TAG, "setInstrumentBrightness is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(7004, i7);
            a.q(new StringBuilder("setInstrumentBrightness mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentBrightness(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setInstrumentDisplayLanguage(int i7) {
        Log.i(TAG, "setInstrumentDisplayLanguageS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyMapStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentDisplayLanguageS(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int setInstrumentTheme(int i7) {
        Log.v(TAG, "setInstrumentTheme is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(7000, i7);
            a.q(new StringBuilder("setInstrumentTheme mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentTheme(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMaintenanceInfo(int i7, int i10) {
        Log.v(TAG, "setMaintenanceInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setMaintenanceInfo mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoMileState(i10);
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoTimeState(i10);
        }
        return 0;
    }

    @Deprecated
    public int setMaintenanceInfoMileState(int i7) {
        Log.v(TAG, "setMaintenanceInfoMileState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(7001, i7);
            a.q(new StringBuilder("setMaintenanceInfoMileState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoMileState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int setMaintenanceInfoTimeState(int i7) {
        Log.v(TAG, "setMaintenanceInfoTimeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setMaintenanceInfoTimeState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoTimeState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMilesType(int i7) {
        Log.v(TAG, "setMilesType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(7001, i7);
            a.q(new StringBuilder("setMilesType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMilesType(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setNaviState(int i7) {
        b.s("setNaviState value = ", i7, TAG);
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" setNaviState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (1 == i7) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setNaviState(2);
        }
        if (2 == i7) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setNaviState(4);
        }
        return 0;
    }

    public int setNotifyMapStateS(int i7) {
        Log.i(TAG, "setNotifyMapStateS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyMapStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setNotifyMapStateS(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNotifyScreenStatusS(int i7) {
        Log.i(TAG, "setNotifyScreenStatusS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyScreenStatusS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setNotifyScreenStatusS(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setSpeedUpCountState(int i7) {
        Log.v(TAG, "setSpeedUpCountState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setSpeedUpCountState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setSpeedUpCountState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setUnit(int i7, int i10) {
        Log.v(TAG, "setUnitUnitTemperaure is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setUnitUnitTemperaure mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitTemperaure(i10);
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitPressure(i10);
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitFuel(i10);
        }
        if (i7 == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitPower(i10);
        }
        return 0;
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (1 == this.mForTest) {
            C1147j c1147j = this.mTestCallback;
            if (absBYDAutoInstrumentListener != null) {
                c1147j.f13666a.remove(absBYDAutoInstrumentListener);
            } else {
                c1147j.getClass();
            }
            a.t(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "unsubscribeInstrumentCallback: " + absBYDAutoInstrumentListener);
        if (absBYDAutoInstrumentListener == null) {
            return;
        }
        this.mAbsBydAutoInstrumentListeners.remove(absBYDAutoInstrumentListener);
        unregisterConnectListener(absBYDAutoInstrumentListener);
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe instrument listener ");
        sb.append(absBYDAutoInstrumentListener);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
        if (absBYDAutoInstrumentListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            ArrayList arrayList = this.mTestCallback.f13666a;
            if (!arrayList.contains(absBYDAutoInstrumentListener)) {
                arrayList.add(absBYDAutoInstrumentListener);
            }
            a.t(new StringBuilder("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (!this.mAbsBydAutoInstrumentListeners.contains(absBYDAutoInstrumentListener)) {
            this.mAbsBydAutoInstrumentListeners.add(absBYDAutoInstrumentListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoInstrumentListener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }
}
