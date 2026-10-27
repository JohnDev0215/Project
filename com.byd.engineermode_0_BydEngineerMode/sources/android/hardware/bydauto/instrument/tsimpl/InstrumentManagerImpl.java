package android.hardware.bydauto.instrument.tsimpl;

import a.a.e.j;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Arrays;
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
    private j mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoInstrumentListener> mAbsBydAutoInstrumentListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mIgnoreBydPermission = false;

    InstrumentManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new j();
                Log.v(TAG, "InstrumentManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.instrument.tsimpl.InstrumentManagerImpl.1
            public void onCabinBinderChanged(boolean z) {
                Log.v(InstrumentManagerImpl.TAG, "onCabinBinderChanged is ");
            }

            public void onCabinChanged(int i2, int i3, CabinPacket cabinPacket) {
                if (InstrumentManagerImpl.this.mAbsBydAutoInstrumentListeners.isEmpty()) {
                    return;
                }
                try {
                    InstrumentManagerImpl.this.instrumentCallback(i2, i3, cabinPacket);
                } catch (Exception unused) {
                    Log.e(InstrumentManagerImpl.TAG, "instrumentCallback is error");
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static InstrumentManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (InstrumentManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new InstrumentManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void instrumentCallback(int i, int i2, CabinPacket cabinPacket) {
        if (cabinPacket == null) {
            Log.e(TAG, "cabinPacket is null");
        }
        Log.i(TAG, "cabinPacket propertyId:" + i);
        switch (i) {
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
                switch (i) {
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
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_BRAKE_SYS_RED /* 557857056 */:
                        Iterator<AbsBYDAutoInstrumentListener> it9 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it9.hasNext()) {
                            it9.next().onMalfunctionInfoChanged(2, cabinPacket.getMalFunctionInfosPleaseCheckTheBrakeSysRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_BRAKE_FLUID_LEVEL_IS_LOW_RED /* 557857057 */:
                        Iterator<AbsBYDAutoInstrumentListener> it10 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it10.hasNext()) {
                            it10.next().onMalfunctionInfoChanged(3, cabinPacket.getMalFunctionInfosBrakeFluidLevelIsLowRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_STEER_SYS_RED /* 557857058 */:
                        Iterator<AbsBYDAutoInstrumentListener> it11 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it11.hasNext()) {
                            it11.next().onMalfunctionInfoChanged(4, cabinPacket.getMalFunctionInfosPleaseCheckTheSteerSysRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH1_RED /* 557857059 */:
                        Iterator<AbsBYDAutoInstrumentListener> it12 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it12.hasNext()) {
                            it12.next().onMalfunctionInfoChanged(5, cabinPacket.getMalFunctionInfosEngineCoolTempTooHigh1Red());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH2_RED /* 557857060 */:
                        Iterator<AbsBYDAutoInstrumentListener> it13 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it13.hasNext()) {
                            it13.next().onMalfunctionInfoChanged(6, cabinPacket.getMalFunctionInfosEngineCoolTempTooHigh2Red());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_INSUFFICIENT_OIL_PRESSURE_RED /* 557857061 */:
                        Iterator<AbsBYDAutoInstrumentListener> it14 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it14.hasNext()) {
                            it14.next().onMalfunctionInfoChanged(7, cabinPacket.getMalFunctionInfosInsufficientOilPressureRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_POWER_SYS_RED /* 557857062 */:
                        Iterator<AbsBYDAutoInstrumentListener> it15 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it15.hasNext()) {
                            it15.next().onMalfunctionInfoChanged(8, cabinPacket.getMalFunctionInfosPleaseCheckThePowerSysRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LV_POWER_SUPPLY_SYS_FAULTY_RED /* 557857063 */:
                        Iterator<AbsBYDAutoInstrumentListener> it16 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it16.hasNext()) {
                            it16.next().onMalfunctionInfoChanged(9, cabinPacket.getMalFunctionInfosLvPowerSupplySysFaultyRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_START_TYPE_IRON_BAT_FAULTY_RED /* 557857064 */:
                        Iterator<AbsBYDAutoInstrumentListener> it17 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it17.hasNext()) {
                            it17.next().onMalfunctionInfoChanged(10, cabinPacket.getMalFunctionInfosStartTypeIronBatFaultyRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_VEHICLE_NET_RED /* 557857065 */:
                        Iterator<AbsBYDAutoInstrumentListener> it18 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it18.hasNext()) {
                            it18.next().onMalfunctionInfoChanged(11, cabinPacket.getMalFunctionInfosPleaseCheckVehicleNetRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMI_SYS1_RED /* 557857066 */:
                        Iterator<AbsBYDAutoInstrumentListener> it19 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it19.hasNext()) {
                            it19.next().onMalfunctionInfoChanged(12, cabinPacket.getMalFunctionInfosPleaseCheckTransmiSys1Red());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMIS_SYS2_RED /* 557857067 */:
                        Iterator<AbsBYDAutoInstrumentListener> it20 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it20.hasNext()) {
                            it20.next().onMalfunctionInfoChanged(13, cabinPacket.getMalFunctionInfosPleaseCheckTransmisSys2Red());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LOW_BATTERY_LIMITED_POWER_RED /* 557857068 */:
                        Iterator<AbsBYDAutoInstrumentListener> it21 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it21.hasNext()) {
                            it21.next().onMalfunctionInfoChanged(14, cabinPacket.getMalFunctionInfosLowBatteryLimitedPowerRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_EV_FUNCTION_IS_LIMITED_RED /* 557857069 */:
                        Iterator<AbsBYDAutoInstrumentListener> it22 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it22.hasNext()) {
                            it22.next().onMalfunctionInfoChanged(15, cabinPacket.getMalFunctionInfosEvFunctionIsLimitedRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_EXIT_SMART_DRIVING_MODE_RED /* 557857070 */:
                        Iterator<AbsBYDAutoInstrumentListener> it23 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it23.hasNext()) {
                            it23.next().onMalfunctionInfoChanged(16, cabinPacket.getMalFunctionInfosExitSmartDrivingModeRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_INTELLIGENT_DRIVE_FAILURE_RED /* 557857071 */:
                        Iterator<AbsBYDAutoInstrumentListener> it24 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it24.hasNext()) {
                            it24.next().onMalfunctionInfoChanged(17, cabinPacket.getMalFunctionInfosIntelligentDriveFailureRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_EPB_RED /* 557857072 */:
                        Iterator<AbsBYDAutoInstrumentListener> it25 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it25.hasNext()) {
                            it25.next().onMalfunctionInfoChanged(18, cabinPacket.getMalFunctionInfosPleaseCheckEpbRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LACK_OF_PARK_CAPACITY_YELLOW /* 557857073 */:
                        Iterator<AbsBYDAutoInstrumentListener> it26 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it26.hasNext()) {
                            it26.next().onMalfunctionInfoChanged(19, cabinPacket.getMalFunctionInfosLackOfParkCapacityYellow());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LBMS_RED /* 557857074 */:
                        Iterator<AbsBYDAutoInstrumentListener> it27 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it27.hasNext()) {
                            it27.next().onMalfunctionInfoChanged(20, cabinPacket.getMalFunctionInfosPleaseCheckLbmsRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LV_SUP_SYS_RED /* 557857075 */:
                        Iterator<AbsBYDAutoInstrumentListener> it28 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it28.hasNext()) {
                            it28.next().onMalfunctionInfoChanged(21, cabinPacket.getMalFunctionInfosPleaseCheckLvSupSysRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ENGINE_SYSTEM_RED /* 557857076 */:
                        Iterator<AbsBYDAutoInstrumentListener> it29 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it29.hasNext()) {
                            it29.next().onMalfunctionInfoChanged(22, cabinPacket.getMalFunctionInfosPleaseCheckEngineSystemRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENG_ACCESSORIES_LIMITED_RED /* 557857077 */:
                        Iterator<AbsBYDAutoInstrumentListener> it30 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it30.hasNext()) {
                            it30.next().onMalfunctionInfoChanged(23, cabinPacket.getMalFunctionInfosEngAccessoriesLimitedRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_TRANSMIS_LIMITEDIN_FUNC_RED /* 557857078 */:
                        Iterator<AbsBYDAutoInstrumentListener> it31 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it31.hasNext()) {
                            it31.next().onMalfunctionInfoChanged(24, cabinPacket.getMalFunctionInfosTransmisLimitedinFuncRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_HIGH_CLUTCH_TEMPERATURE_RED /* 557857079 */:
                        Iterator<AbsBYDAutoInstrumentListener> it32 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it32.hasNext()) {
                            it32.next().onMalfunctionInfoChanged(25, cabinPacket.getMalFunctionInfosHighClutchTemperatureRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_GEARBOX_OIL_TEMP_TOO_HIGH_RED /* 557857080 */:
                        Iterator<AbsBYDAutoInstrumentListener> it33 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it33.hasNext()) {
                            it33.next().onMalfunctionInfoChanged(26, cabinPacket.getMalFunctionInfosGearboxOilTempTooHighRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SRS_SYSTEM_RED /* 557857081 */:
                        Iterator<AbsBYDAutoInstrumentListener> it34 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it34.hasNext()) {
                            it34.next().onMalfunctionInfoChanged(27, cabinPacket.getMalFunctionInfosPleaseCheckSrsSystemRed());
                        }
                        break;
                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ADB_SYSTEM_RED /* 557857082 */:
                        Iterator<AbsBYDAutoInstrumentListener> it35 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it35.hasNext()) {
                            it35.next().onMalfunctionInfoChanged(28, cabinPacket.getMalFunctionInfosPleaseCheckAdbSystemRed());
                        }
                        break;
                    case InstrumentId.ID_SPORT_MODE_STATE /* 557857140 */:
                        Iterator<AbsBYDAutoInstrumentListener> it36 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it36.hasNext()) {
                            it36.next().onSportModeStateChanged(cabinPacket.getSportModeStateR());
                        }
                        caseFeatureIds(4570, Integer.valueOf(cabinPacket.getSportModeStateR()));
                        break;
                    case InstrumentId.ID_DAY_NIGHT_MODER /* 557857144 */:
                        Iterator<AbsBYDAutoInstrumentListener> it37 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it37.hasNext()) {
                            it37.next().onDayNightModeChanged(cabinPacket.getDayNightModeR());
                        }
                        break;
                    case InstrumentId.ID_MAINTENANCE_INFO_TWOR /* 557857146 */:
                        Iterator<AbsBYDAutoInstrumentListener> it38 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it38.hasNext()) {
                            it38.next().onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoTwoR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case InstrumentId.ID_TOTAL_MILEAGER /* 557857152 */:
                        Iterator<AbsBYDAutoInstrumentListener> it39 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it39.hasNext()) {
                            it39.next().onTotalMileageChanged(cabinPacket.getTotalMileageR());
                        }
                        break;
                    case InstrumentId.ID_MAINTENANCE_INFO_FOURR /* 557857156 */:
                        Iterator<AbsBYDAutoInstrumentListener> it40 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it40.hasNext()) {
                            it40.next().onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoFourR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case InstrumentId.ID_SPEED_UP_FORM_INS_R /* 557857158 */:
                        Iterator<AbsBYDAutoInstrumentListener> it41 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it41.hasNext()) {
                            it41.next().onSpeedUpFormInsChange(cabinPacket.getSpeedUpFormIns());
                        }
                        break;
                    case 557857169:
                        Iterator<AbsBYDAutoInstrumentListener> it42 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it42.hasNext()) {
                            it42.next().onAllWeatherLightsStateChanged(cabinPacket.getAllWeatherLightsStateR());
                        }
                        caseFeatureIds(BYDAutoFeatureIds.INSTRUMENT_ALL_WEATHER_LIGHT_SWITCH_FEEDBACK, Integer.valueOf(cabinPacket.getAllWeatherLightsStateR()));
                        break;
                    case InstrumentId.ID_MASTER_VOLUME_FIRST_R /* 557857207 */:
                        Iterator<AbsBYDAutoInstrumentListener> it43 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it43.hasNext()) {
                            it43.next().onMasterVolumeChanged(1, cabinPacket.getMasterVolumeFirstR());
                        }
                        break;
                    case InstrumentId.ID_MASTER_VOLUME_SECOND_R /* 557857208 */:
                        Iterator<AbsBYDAutoInstrumentListener> it44 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it44.hasNext()) {
                            it44.next().onMasterVolumeChanged(2, cabinPacket.getMasterVolumeSecondR());
                        }
                        break;
                    case InstrumentId.ID_MUTE_STATE_FIRST_R /* 557857209 */:
                        Iterator<AbsBYDAutoInstrumentListener> it45 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it45.hasNext()) {
                            it45.next().onMuteStateChanged(1, cabinPacket.getMuteStateFirstR());
                        }
                        break;
                    case InstrumentId.ID_MUTE_STATE_SECONDE_R /* 557857210 */:
                        Iterator<AbsBYDAutoInstrumentListener> it46 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it46.hasNext()) {
                            it46.next().onMuteStateChanged(2, cabinPacket.getMuteStateSecondR());
                        }
                        break;
                    case InstrumentId.ID_SPEED_UP_COUNT_STATER /* 557867009 */:
                        Iterator<AbsBYDAutoInstrumentListener> it47 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it47.hasNext()) {
                            it47.next().onSpeedUpCountStateChanged(cabinPacket.getSpeedUpCountStateR());
                        }
                        break;
                    case InstrumentId.ID_SPEED_UNITR /* 557867010 */:
                        Iterator<AbsBYDAutoInstrumentListener> it48 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it48.hasNext()) {
                            it48.next().onSpeedUnitChanged(cabinPacket.getSpeedUnit());
                        }
                        break;
                    case InstrumentId.ID_MILEAGE_UNITR /* 557868033 */:
                        Iterator<AbsBYDAutoInstrumentListener> it49 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it49.hasNext()) {
                            it49.next().onMileageUnitChanged(cabinPacket.getMileageUnitR());
                        }
                        break;
                    case InstrumentId.ID_AREA_INFO_R /* 557868037 */:
                        Iterator<AbsBYDAutoInstrumentListener> it50 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it50.hasNext()) {
                            it50.next().onAreaInfoChanged(cabinPacket.getAreaInfoR());
                        }
                        break;
                    case InstrumentId.ID_DISTANCE_MILEAGE_UNIT_R /* 557868043 */:
                        Iterator<AbsBYDAutoInstrumentListener> it51 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it51.hasNext()) {
                            it51.next().onDistanceMileageUnitChanged(cabinPacket.getDistanceMileageUnitR());
                        }
                        break;
                    case InstrumentId.ID_LOW_POWER_BATTERY_WARN_LIGHT_COLOR_R /* 557869062 */:
                        Iterator<AbsBYDAutoInstrumentListener> it52 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it52.hasNext()) {
                            it52.next().onLowPowerBatteryWarnLightColorChanged(cabinPacket.getLowPowerBatteryWarnLightColorR());
                        }
                        break;
                    case InstrumentId.ID_LOW_FUEL_WARN_LIGHT_COLOR_R /* 557869067 */:
                        Iterator<AbsBYDAutoInstrumentListener> it53 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it53.hasNext()) {
                            it53.next().onLowFuelWarnLightColorChanged(cabinPacket.getLowFuelWarnLightColorR());
                        }
                        break;
                    case InstrumentId.ID_INS_CENTER_DISPLAYR /* 557875262 */:
                        Iterator<AbsBYDAutoInstrumentListener> it54 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it54.hasNext()) {
                            it54.next().onINSCenterDisplay(cabinPacket.getINSCenterDisplayR());
                        }
                        break;
                    case InstrumentId.ID_EXTERNAL_CHARGING_POWER /* 559954186 */:
                        Iterator<AbsBYDAutoInstrumentListener> it55 = this.mAbsBydAutoInstrumentListeners.iterator();
                        while (it55.hasNext()) {
                            it55.next().onExternalChargingPowerChanged(cabinPacket.getExternalChargingPower());
                        }
                        caseFeatureIds(3932, Integer.valueOf((int) cabinPacket.getExternalChargingPower()));
                        caseFeatureIds(4085, Integer.valueOf((int) cabinPacket.getExternalChargingPower()));
                        break;
                    default:
                        switch (i) {
                            case InstrumentId.ID_UNIT_POWER_LEFT_R /* 557857051 */:
                                Iterator<AbsBYDAutoInstrumentListener> it56 = this.mAbsBydAutoInstrumentListeners.iterator();
                                while (it56.hasNext()) {
                                    it56.next().onUnitChanged(3, cabinPacket.getUnitPowerLeftR());
                                }
                                break;
                            case InstrumentId.ID_ALARM_BUZZLE_STATE /* 557857052 */:
                                Iterator<AbsBYDAutoInstrumentListener> it57 = this.mAbsBydAutoInstrumentListeners.iterator();
                                while (it57.hasNext()) {
                                    it57.next().onAlarmBuzzleStateChange(cabinPacket.getAlarmBuzzleState());
                                }
                                break;
                            default:
                                switch (i) {
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_HEADLIGHT_SYS_YELLOW /* 557857084 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it58 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it58.hasNext()) {
                                            it58.next().onMalfunctionInfoChanged(29, cabinPacket.getMalFunctionInfosCheckHeadlightSysYellow());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_ENGINE_START_FAILED_RED /* 557857085 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it59 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it59.hasNext()) {
                                            it59.next().onMalfunctionInfoChanged(30, cabinPacket.getMalFunctionInfosEngineStartFailedRed());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_COOLANT_LIQUI_LEVEL_LOW_YELLOW /* 557857086 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it60 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it60.hasNext()) {
                                            it60.next().onMalfunctionInfoChanged(31, cabinPacket.getMalFunctionInfosCoolantLiquiLevelLowYellow());
                                        }
                                        break;
                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_AC_HOT_MANA_FAIL_YELLOW /* 557857087 */:
                                        Iterator<AbsBYDAutoInstrumentListener> it61 = this.mAbsBydAutoInstrumentListeners.iterator();
                                        while (it61.hasNext()) {
                                            it61.next().onMalfunctionInfoChanged(32, cabinPacket.getMalFunctionInfosAcHotManaFailYellow());
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_THE_OIL_MONIT_SYS_YELLOW /* 557857090 */:
                                                Iterator<AbsBYDAutoInstrumentListener> it62 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                while (it62.hasNext()) {
                                                    it62.next().onMalfunctionInfoChanged(33, cabinPacket.getMalFunctionInfosCheckTheOilMonitSysYellow());
                                                }
                                                break;
                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_TIRE_PRES_MON_SYS_YELLOW /* 557857091 */:
                                                Iterator<AbsBYDAutoInstrumentListener> it63 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                while (it63.hasNext()) {
                                                    it63.next().onMalfunctionInfoChanged(34, cabinPacket.getMalFunctionInfosCheckTirePresMonSysYellow());
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_THE_BRAK_SYS_YELLOW /* 557857093 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it64 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it64.hasNext()) {
                                                            it64.next().onMalfunctionInfoChanged(35, cabinPacket.getMalFunctionInfosCheckTheBrakSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ABS_SYS_YELLOW /* 557857094 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it65 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it65.hasNext()) {
                                                            it65.next().onMalfunctionInfoChanged(36, cabinPacket.getMalFunctionInfosPleaseCheckAbsSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_GENERATION_SYS_YELLOW /* 557857095 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it66 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it66.hasNext()) {
                                                            it66.next().onMalfunctionInfoChanged(37, cabinPacket.getMalFunctionInfosCheckGenerationSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_GEAR_SYS_YELLOW /* 557857096 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it67 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it67.hasNext()) {
                                                            it67.next().onMalfunctionInfoChanged(38, cabinPacket.getMalFunctionInfosPleaseCheckGearSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ESP_SYS_YELLOW /* 557857097 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it68 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it68.hasNext()) {
                                                            it68.next().onMalfunctionInfoChanged(39, cabinPacket.getMalFunctionInfosPleaseCheckEspSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_HDC_SYS_YELLOW /* 557857098 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it69 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it69.hasNext()) {
                                                            it69.next().onMalfunctionInfoChanged(40, cabinPacket.getMalFunctionInfosPleaseCheckHdcSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_BRAKE_PLATE_YELLOW /* 557857099 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it70 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it70.hasNext()) {
                                                            it70.next().onMalfunctionInfoChanged(41, cabinPacket.getMalFunctionInfosCheckBrakePlateYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_VIDEO_CONTROLLER_YELLOW /* 557857100 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it71 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it71.hasNext()) {
                                                            it71.next().onMalfunctionInfoChanged(42, cabinPacket.getMalFunctionInfosCheckVideoControllerYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_FMRR_YELLOW /* 557857101 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it72 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it72.hasNext()) {
                                                            it72.next().onMalfunctionInfoChanged(43, cabinPacket.getMalFunctionInfosCheckFmrrYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_AEB_FUNCT_LIMIT_YELLOW /* 557857102 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it73 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it73.hasNext()) {
                                                            it73.next().onMalfunctionInfoChanged(44, cabinPacket.getMalFunctionInfosAebFunctLimitYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_PRED_COLL_ALA_SYS_YELLOW /* 557857103 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it74 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it74.hasNext()) {
                                                            it74.next().onMalfunctionInfoChanged(45, cabinPacket.getMalFunctionInfosCheckPredCollAlaSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_PEDESTRI_DETE_SYS_YELLOW /* 557857104 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it75 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it75.hasNext()) {
                                                            it75.next().onMalfunctionInfoChanged(46, cabinPacket.getMalFunctionInfosCheckPedestriDeteSysYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMITED_LANE_KEEPING_YELLOW /* 557857105 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it76 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it76.hasNext()) {
                                                            it76.next().onMalfunctionInfoChanged(47, cabinPacket.getMalFunctionInfosLimitedLaneKeepingYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMITED_ADAPTIVE_CRUISE_YELLOW /* 557857106 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it77 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it77.hasNext()) {
                                                            it77.next().onMalfunctionInfoChanged(48, cabinPacket.getMalFunctionInfosLimitedAdaptiveCruiseYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMIT_LANE_DEPARTU_FUNC_YELLOW /* 557857107 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it78 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it78.hasNext()) {
                                                            it78.next().onMalfunctionInfoChanged(49, cabinPacket.getMalFunctionInfosLimitLaneDepartuFuncYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_LIMITED_BLIND_SPOT_MONI_YELLOW /* 557857108 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it79 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it79.hasNext()) {
                                                            it79.next().onMalfunctionInfoChanged(50, cabinPacket.getMalFunctionInfosLimitedBlindSpotMoniYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_DISCHARGE_EQUIP_YELLOW /* 557857109 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it80 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it80.hasNext()) {
                                                            it80.next().onMalfunctionInfoChanged(51, cabinPacket.getMalFunctionInfosCheckDischargeEquipYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_CHARG_OUT_EQUIP_YELLOW /* 557857110 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it81 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it81.hasNext()) {
                                                            it81.next().onMalfunctionInfoChanged(52, cabinPacket.getMalFunctionInfosCheckChargOutEquipYellow());
                                                        }
                                                        break;
                                                    case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_ON_BOARDCHAR_SYS_YELLOW /* 557857111 */:
                                                        Iterator<AbsBYDAutoInstrumentListener> it82 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                        while (it82.hasNext()) {
                                                            it82.next().onMalfunctionInfoChanged(53, cabinPacket.getMalFunctionInfosCheckOnBoardcharSysYellow());
                                                        }
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_APA_FUNCTION_LIMIT_WHITE /* 557857129 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it83 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it83.hasNext()) {
                                                                    it83.next().onMalfunctionInfoChanged(54, cabinPacket.getMalFunctionInfosApaFunctionLimitWhite());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_CHECK_MOT_PRET_BELT_SYS_YELLOW /* 557857130 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it84 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it84.hasNext()) {
                                                                    it84.next().onMalfunctionInfoChanged(55, cabinPacket.getMalFunctionInfosCheckMotPretBeltSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_MEMORY_SYS_YELLOW /* 557857131 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it85 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it85.hasNext()) {
                                                                    it85.next().onMalfunctionInfoChanged(56, cabinPacket.getMalFunctionInfosPleaseCheckMemorySysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SUNROO_SYS_YELLOW /* 557857132 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it86 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it86.hasNext()) {
                                                                    it86.next().onMalfunctionInfoChanged(57, cabinPacket.getMalFunctionInfosPleaseCheckSunrooSysYellow());
                                                                }
                                                                break;
                                                            case InstrumentId.ID_INS_NAV_DISPLAY_MODE_R /* 557857133 */:
                                                                Iterator<AbsBYDAutoInstrumentListener> it87 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                while (it87.hasNext()) {
                                                                    it87.next().onINSNavDisplayMode(cabinPacket.getINSNavDisplayModeR());
                                                                }
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY_SEND_4C1 /* 557857135 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it88 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it88.hasNext()) {
                                                                            it88.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplaySend4C1());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY_RESPOND_40C /* 557857136 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it89 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it89.hasNext()) {
                                                                            it89.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplayRespond40C());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY_SEND_26F /* 557857137 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it90 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it90.hasNext()) {
                                                                            it90.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplaySend26F());
                                                                        }
                                                                        break;
                                                                    case InstrumentId.ID_INS_ADAS_CENTER_DISPLAY /* 557857138 */:
                                                                        Iterator<AbsBYDAutoInstrumentListener> it91 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                        while (it91.hasNext()) {
                                                                            it91.next().onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplay());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case 557857171:
                                                                                Iterator<AbsBYDAutoInstrumentListener> it92 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                while (it92.hasNext()) {
                                                                                    it92.next().onHeadlightControlModeChanged(cabinPacket.getHeadlightControlModeR());
                                                                                }
                                                                                break;
                                                                            case 557857172:
                                                                                Iterator<AbsBYDAutoInstrumentListener> it93 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                while (it93.hasNext()) {
                                                                                    it93.next().onRearFogLightStateChanged(cabinPacket.getRearFogLightStateR());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case InstrumentId.ID_MUSIC_PROGRESS_2F8_R /* 557857182 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it94 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it94.hasNext()) {
                                                                                            it94.next().onMusicProgressChanged(1, cabinPacket.getMusicProgressFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_PROGRESS_2FC_R /* 557857183 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it95 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it95.hasNext()) {
                                                                                            it95.next().onMusicProgressChanged(2, cabinPacket.getMusicProgressSecondR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_CONTROL_STATUS_FIRST_R /* 557857184 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it96 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it96.hasNext()) {
                                                                                            it96.next().onMusicControlStatusChanged(1, cabinPacket.getMusicControlStatusFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_CONTROL_STATUS_SECOND_R /* 557857185 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it97 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it97.hasNext()) {
                                                                                            it97.next().onMusicControlStatusChanged(2, cabinPacket.getMusicControlStatusSecondR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_CHANNEL_STATUS_FIRST_R /* 557857186 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it98 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it98.hasNext()) {
                                                                                            it98.next().onMusicChannelStatusChanged(1, cabinPacket.getMusicChannelStatusFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_CHANNEL_STATUS_SECOND_R /* 557857187 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it99 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it99.hasNext()) {
                                                                                            it99.next().onMusicChannelStatusChanged(2, cabinPacket.getMusicChannelStatusSecondR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_RESOURCE_CONFIG_FIRST_R /* 557857188 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it100 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it100.hasNext()) {
                                                                                            it100.next().onMusicResourceConfigChanged(1, cabinPacket.getMusicResourceConfigFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case InstrumentId.ID_MUSIC_RESOURCE_CONFIG_SECOND_R /* 557857189 */:
                                                                                        Iterator<AbsBYDAutoInstrumentListener> it101 = this.mAbsBydAutoInstrumentListeners.iterator();
                                                                                        while (it101.hasNext()) {
                                                                                            it101.next().onMusicResourceConfigChanged(2, cabinPacket.getMusicResourceConfigSecondR());
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

    public int clearDistanceMilesType(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " clearDistanceMilesType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().clearDistanceMilesType(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    switch (i) {
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
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getAlarmBuzzleState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMalfunctionInfo mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAllWeatherLightsState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAllWeatherLightsState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAllWeatherLightsState();
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
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDayNightMode();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getDayNightMode mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getDistanceMileageUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDistanceMileageUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getDistanceMileageUnit();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public float getExternalChargingPower() {
        Log.v(TAG, "getExternalChargingPower is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getExternalChargingPower mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0.0f;
        }
        float externalChargingPower = this.mTsManagerImpl.getCarCabinAdapterManager().getExternalChargingPower();
        double d2 = externalChargingPower;
        if (d2 <= 99999.9d && d2 >= 0.0d) {
            return externalChargingPower;
        }
        Log.v(TAG, "The value is illegal");
        return -1.0f;
    }

    public int getHeadlightControlMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getHeadlightControlMode();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getINSAdasCenterDisplay() {
        Log.v(TAG, "getINSAdasCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getINSAdasCenterDisplay();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getINSAdasCenterDisplay mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getINSCenterDisplay() {
        Log.v(TAG, "getINSCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getINSCenterDisplay();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getINSCenterDisplay mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getInsNavDisplayMode() {
        Log.v(TAG, "getINSNavDisplayMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getINSNavDisplayMode();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getINSNavDisplayMode mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getInstrumentAutoDimmState() {
        Log.v(TAG, "getInstrumentAutoDimmState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentAutoDimmState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getInstrumentAutoDimmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Deprecated
    public int getInstrumentBrightness() {
        Log.v(TAG, "getInstrumentBrightness is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentBrightness mForTestValue is" + this.mForTest + " execute return");
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
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentTheme();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getInstrumentTheme mForTestValue is" + this.mForTest + " execute return");
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

    public int getMaintenanceInfo(int i) {
        Log.v(TAG, "getMaintenanceInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
                Log.v(TAG, "adapter is not connect");
            }
            return 0;
        }
        Log.v(TAG, "getMaintenanceInfo mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Deprecated
    public int getMaintenanceInfoMileState() {
        Log.v(TAG, "getMaintenanceInfoMileState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMaintenanceInfoMileState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMaintenanceInfoMileState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Deprecated
    public int getMaintenanceInfoTimeState() {
        Log.v(TAG, "getMaintenanceInfoTimeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMaintenanceInfoTimeState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMaintenanceInfoTimeState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getMalfunctionInfo(int i) {
        Log.v(TAG, "getMalfunctionInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMalfunctionInfo(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMalfunctionInfo mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getMalfunctionInfos() {
        Log.v(TAG, "getMalfunctionInfos is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMalfunctionInfos();
            }
            Log.e(TAG, "adapter is not connect");
            return new int[0];
        }
        Log.v(TAG, "getMalfunctionInfos mForTestValue is" + this.mForTest + " execute return");
        return new int[0];
    }

    public int getMasterVolume(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMasterVolume mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMasterVolumeFirst(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMasterVolumeSecond(i);
        }
        return 0;
    }

    public int getMileageUnit() {
        Log.v(TAG, "getMileageUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMileageUnit();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMileageUnit mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public long getMilesByType(int i) {
        Log.v(TAG, "getMilesByType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMilesByType(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0L;
        }
        Log.v(TAG, "getMilesByType mForTestValue is" + this.mForTest + " execute return");
        return 0L;
    }

    public int getMilesType() {
        Log.v(TAG, "getMilesType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getMilesType();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getMilesType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getMusicChannelStatus(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicChannelStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicChannelStatusFirst(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicChannelStatusSecond(i);
        }
        return 0;
    }

    public int getMusicControlStatus(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicControlStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicControlStatusFirst(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicControlStatusSecond(i);
        }
        return 0;
    }

    public int getMusicProgress(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicProgress mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicProgressFirst(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicProgressSecond(i);
        }
        return 0;
    }

    public int getMusicResourceConfig(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicResourceConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicResourceConfigFirst(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicResourceConfigSecond(i);
        }
        return 0;
    }

    public int getMuteState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMuteState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMuteStateFirst(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMuteStateSecond(i);
        }
        return 0;
    }

    public int getRearFogLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRearFogLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getRearFogLightState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSpeedUnit() {
        Log.v(TAG, "getSpeedUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUnit();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getSpeedUnit mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSpeedUpCountState() {
        Log.v(TAG, "getSpeedUpCountState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUpCountState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getSpeedUpCountState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSpeedUpFormIns() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUpFormIns();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSpeedUpFormIns mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSportModeState() {
        Log.v(TAG, "getSportModeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getSportModeState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getSportModeState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getTotalMileage() {
        Log.v(TAG, "getTotalMileage is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalMileage mForTestValue is" + this.mForTest + " execute return");
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

    public int getUnit(int i) {
        Log.v(TAG, "getUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getUnit mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitTemperatureLeft();
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitPressureLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitFuelConsumptionAndDistanceLeft();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitPowerLeft();
        }
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoInstrumentListener> it = this.mAbsBydAutoInstrumentListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        registerListener(absBYDAutoInstrumentListener, new int[0]);
    }

    public int setAreaInfo(int i) {
        Log.i(TAG, "setAreaInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAreaInfo mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setAreaInfo(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallState(int i) {
        Log.v(TAG, "setCallState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        if (i != 0 && i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
            return -1;
        }
        Log.v(TAG, "setCallState value = " + i);
        return this.mTsManagerImpl.getGeneralAdapterManager().setCallState(i);
    }

    public int setDayNightMode(int i) {
        Log.v(TAG, "setDayNightMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setDayNightMode(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setDayNightMode mForTestValue is" + this.mForTest + " execute return");
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
                    Log.v(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        switch (i) {
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

    public int setHeadlightControlMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setHeadlightControlMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setINSAdasCenterDisplay(int i) {
        Log.v(TAG, "setINSAdasCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setINSAdasCenterDisplay(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setINSAdasCenterDisplay mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setINSCenterDisplay(int i) {
        Log.v(TAG, "setINSCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setINSCenterDisplay(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setINSCenterDisplay mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setINSNavDisplayMode(int i) {
        Log.v(TAG, "setINSNavDisplayMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setINSNavDisplayMode(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setINSNavDisplayMode mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setInstrumentAutoDimmState(int i) {
        Log.v(TAG, "setInstrumentAutoDimmState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentAutoDimmState(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.b(7003, i);
        Log.v(TAG, "setInstrumentAutoDimmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Deprecated
    public int setInstrumentBrightness(int i) {
        Log.v(TAG, "setInstrumentBrightness is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentBrightness(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.b(7004, i);
        Log.v(TAG, "setInstrumentBrightness mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setInstrumentDisplayLanguage(int i) {
        Log.i(TAG, "setInstrumentDisplayLanguageS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyMapStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentDisplayLanguageS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int setInstrumentTheme(int i) {
        Log.v(TAG, "setInstrumentTheme is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentTheme(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.b(7000, i);
        Log.v(TAG, "setInstrumentTheme mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setMaintenanceInfo(int i, int i2) {
        Log.v(TAG, "setMaintenanceInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setMaintenanceInfo mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoMileState(i2);
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoTimeState(i2);
        }
        return 0;
    }

    @Deprecated
    public int setMaintenanceInfoMileState(int i) {
        Log.v(TAG, "setMaintenanceInfoMileState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoMileState(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.b(7001, i);
        Log.v(TAG, "setMaintenanceInfoMileState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Deprecated
    public int setMaintenanceInfoTimeState(int i) {
        Log.v(TAG, "setMaintenanceInfoTimeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoTimeState(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setMaintenanceInfoTimeState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setMasterVolume(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMasterVolume mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMasterVolume(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMilesType(int i) {
        Log.v(TAG, "setMilesType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setMilesType(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.b(7001, i);
        Log.v(TAG, "setMilesType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setMusicInfo(int i, int i2, int i3, int i4) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMusicInfo mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMusicInfo(i, i2, i3, i4);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMusicName(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMusicName mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMusicName(str);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMusicSingerName(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMusicSingerName mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMusicSingerName(str);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setMuteState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMuteState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMuteState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setNaviState(int i) {
        Log.v(TAG, "setNaviState value = " + i);
        if (1 == this.mForTest) {
            Log.v(TAG, " setNaviState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (1 == i) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setNaviState(2);
        }
        if (2 == i) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setNaviState(4);
        }
        return 0;
    }

    public int setNotifyMapStateS(int i) {
        Log.i(TAG, "setNotifyMapStateS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyMapStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setNotifyMapStateS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNotifyScreenStatusS(int i) {
        Log.i(TAG, "setNotifyScreenStatusS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyScreenStatusS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setNotifyScreenStatusS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setPadPrsntTime(int i, int i2, int i3) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPadPrsntTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setPadPrsntTime(i, i2, i3);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setPadTotalTime(int i, int i2, int i3) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPadTotalTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setPadTotalTime(i, i2, i3);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRadioChannel(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRadioChannel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setRadioChannel(str);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setRearFogLightState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRearFogLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setRearFogLightState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setScreenDisplayState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setScreenDisplayState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
        }
        return 0;
    }

    public int setSpeedUpCountState(int i) {
        Log.v(TAG, "setSpeedUpCountState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setSpeedUpCountState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setSpeedUpCountState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setUnit(int i, int i2) {
        Log.v(TAG, "setUnitUnitTemperaure is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setUnitUnitTemperaure mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitTemperaure(i2);
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitPressure(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitFuel(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitPower(i2);
        }
        return 0;
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.c(absBYDAutoInstrumentListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
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
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe instrument listener " + absBYDAutoInstrumentListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoInstrumentListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.d(absBYDAutoInstrumentListener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
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
