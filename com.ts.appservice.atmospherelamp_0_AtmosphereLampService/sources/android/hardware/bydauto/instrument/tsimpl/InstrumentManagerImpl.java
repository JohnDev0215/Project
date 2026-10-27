package android.hardware.bydauto.instrument.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Arrays;
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
    private TestInstrumentManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoInstrumentListener> mAbsBydAutoInstrumentListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mIgnoreBydPermission = false;

    InstrumentManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestInstrumentManagerImplCallback();
                Log.v(TAG, "InstrumentManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.instrument.tsimpl.InstrumentManagerImpl.1
            public void onCabinChanged(int id, int areaId, CabinPacket packet) {
                if (!InstrumentManagerImpl.this.mAbsBydAutoInstrumentListeners.isEmpty()) {
                    try {
                        InstrumentManagerImpl.this.instrumentCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(InstrumentManagerImpl.TAG, "instrumentCallback is error");
                    }
                }
            }

            public void onCabinBinderChanged(boolean binderState) {
                Log.v(InstrumentManagerImpl.TAG, "onCabinBinderChanged is ");
            }
        });
    }

    public static InstrumentManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (InstrumentManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new InstrumentManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void instrumentCallback(int id, int areaId, CabinPacket cabinPacket) {
        if (cabinPacket == null) {
            Log.e(TAG, "cabinPacket is null");
        }
        Log.i(TAG, "cabinPacket propertyId:" + id);
        switch (id) {
            case 557857026:
                for (AbsBYDAutoInstrumentListener listener : this.mAbsBydAutoInstrumentListeners) {
                    listener.onBacklightBrightnessChanged(cabinPacket.getInstrumentBrightnessLeft());
                }
                break;
            case 557857027:
                for (AbsBYDAutoInstrumentListener listener2 : this.mAbsBydAutoInstrumentListeners) {
                    listener2.onBacklightBrightnessChanged(cabinPacket.getInstrumentBrightnessR());
                }
                break;
            default:
                switch (id) {
                    case 557857029:
                        for (AbsBYDAutoInstrumentListener listener3 : this.mAbsBydAutoInstrumentListeners) {
                            listener3.onInstrumentAutoDimmStateChange(cabinPacket.getInstrumentAutoDimmStateR());
                        }
                        break;
                    case 557857032:
                        for (AbsBYDAutoInstrumentListener listener4 : this.mAbsBydAutoInstrumentListeners) {
                            listener4.onMilesTypeChanged(cabinPacket.getMilesTypeR());
                        }
                        break;
                    case 557857039:
                        for (AbsBYDAutoInstrumentListener listener5 : this.mAbsBydAutoInstrumentListeners) {
                            listener5.onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoOneR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case 557857047:
                        for (AbsBYDAutoInstrumentListener listener6 : this.mAbsBydAutoInstrumentListeners) {
                            listener6.onUnitChanged(1, cabinPacket.getUnitPressureLeftR());
                        }
                        caseFeatureIds(4208, Integer.valueOf(cabinPacket.getUnitPressureLeftR()));
                        break;
                    case 557857049:
                        for (AbsBYDAutoInstrumentListener listener7 : this.mAbsBydAutoInstrumentListeners) {
                            listener7.onUnitChanged(2, cabinPacket.getUnitFuelConsumptionAndDistanceLeftR());
                        }
                        break;
                    case 557857054:
                        for (AbsBYDAutoInstrumentListener listener8 : this.mAbsBydAutoInstrumentListeners) {
                            listener8.onMalfunctionInfoChanged(1, cabinPacket.getMalFunctionInfosPleaseLeaveVehicleImmdRed());
                        }
                        break;
                    case 557857056:
                        for (AbsBYDAutoInstrumentListener listener9 : this.mAbsBydAutoInstrumentListeners) {
                            listener9.onMalfunctionInfoChanged(2, cabinPacket.getMalFunctionInfosPleaseCheckTheBrakeSysRed());
                        }
                        break;
                    case 557857057:
                        for (AbsBYDAutoInstrumentListener listener10 : this.mAbsBydAutoInstrumentListeners) {
                            listener10.onMalfunctionInfoChanged(3, cabinPacket.getMalFunctionInfosBrakeFluidLevelIsLowRed());
                        }
                        break;
                    case 557857058:
                        for (AbsBYDAutoInstrumentListener listener11 : this.mAbsBydAutoInstrumentListeners) {
                            listener11.onMalfunctionInfoChanged(4, cabinPacket.getMalFunctionInfosPleaseCheckTheSteerSysRed());
                        }
                        break;
                    case 557857059:
                        for (AbsBYDAutoInstrumentListener listener12 : this.mAbsBydAutoInstrumentListeners) {
                            listener12.onMalfunctionInfoChanged(5, cabinPacket.getMalFunctionInfosEngineCoolTempTooHigh1Red());
                        }
                        break;
                    case 557857060:
                        for (AbsBYDAutoInstrumentListener listener13 : this.mAbsBydAutoInstrumentListeners) {
                            listener13.onMalfunctionInfoChanged(6, cabinPacket.getMalFunctionInfosEngineCoolTempTooHigh2Red());
                        }
                        break;
                    case 557857061:
                        for (AbsBYDAutoInstrumentListener listener14 : this.mAbsBydAutoInstrumentListeners) {
                            listener14.onMalfunctionInfoChanged(7, cabinPacket.getMalFunctionInfosInsufficientOilPressureRed());
                        }
                        break;
                    case 557857062:
                        for (AbsBYDAutoInstrumentListener listener15 : this.mAbsBydAutoInstrumentListeners) {
                            listener15.onMalfunctionInfoChanged(8, cabinPacket.getMalFunctionInfosPleaseCheckThePowerSysRed());
                        }
                        break;
                    case 557857063:
                        for (AbsBYDAutoInstrumentListener listener16 : this.mAbsBydAutoInstrumentListeners) {
                            listener16.onMalfunctionInfoChanged(9, cabinPacket.getMalFunctionInfosLvPowerSupplySysFaultyRed());
                        }
                        break;
                    case 557857064:
                        for (AbsBYDAutoInstrumentListener listener17 : this.mAbsBydAutoInstrumentListeners) {
                            listener17.onMalfunctionInfoChanged(10, cabinPacket.getMalFunctionInfosStartTypeIronBatFaultyRed());
                        }
                        break;
                    case 557857065:
                        for (AbsBYDAutoInstrumentListener listener18 : this.mAbsBydAutoInstrumentListeners) {
                            listener18.onMalfunctionInfoChanged(11, cabinPacket.getMalFunctionInfosPleaseCheckVehicleNetRed());
                        }
                        break;
                    case 557857066:
                        for (AbsBYDAutoInstrumentListener listener19 : this.mAbsBydAutoInstrumentListeners) {
                            listener19.onMalfunctionInfoChanged(12, cabinPacket.getMalFunctionInfosPleaseCheckTransmiSys1Red());
                        }
                        break;
                    case 557857067:
                        for (AbsBYDAutoInstrumentListener listener20 : this.mAbsBydAutoInstrumentListeners) {
                            listener20.onMalfunctionInfoChanged(13, cabinPacket.getMalFunctionInfosPleaseCheckTransmisSys2Red());
                        }
                        break;
                    case 557857068:
                        for (AbsBYDAutoInstrumentListener listener21 : this.mAbsBydAutoInstrumentListeners) {
                            listener21.onMalfunctionInfoChanged(14, cabinPacket.getMalFunctionInfosLowBatteryLimitedPowerRed());
                        }
                        break;
                    case 557857069:
                        for (AbsBYDAutoInstrumentListener listener22 : this.mAbsBydAutoInstrumentListeners) {
                            listener22.onMalfunctionInfoChanged(15, cabinPacket.getMalFunctionInfosEvFunctionIsLimitedRed());
                        }
                        break;
                    case 557857070:
                        for (AbsBYDAutoInstrumentListener listener23 : this.mAbsBydAutoInstrumentListeners) {
                            listener23.onMalfunctionInfoChanged(16, cabinPacket.getMalFunctionInfosExitSmartDrivingModeRed());
                        }
                        break;
                    case 557857071:
                        for (AbsBYDAutoInstrumentListener listener24 : this.mAbsBydAutoInstrumentListeners) {
                            listener24.onMalfunctionInfoChanged(17, cabinPacket.getMalFunctionInfosIntelligentDriveFailureRed());
                        }
                        break;
                    case 557857072:
                        for (AbsBYDAutoInstrumentListener listener25 : this.mAbsBydAutoInstrumentListeners) {
                            listener25.onMalfunctionInfoChanged(18, cabinPacket.getMalFunctionInfosPleaseCheckEpbRed());
                        }
                        break;
                    case 557857073:
                        for (AbsBYDAutoInstrumentListener listener26 : this.mAbsBydAutoInstrumentListeners) {
                            listener26.onMalfunctionInfoChanged(19, cabinPacket.getMalFunctionInfosLackOfParkCapacityYellow());
                        }
                        break;
                    case 557857074:
                        for (AbsBYDAutoInstrumentListener listener27 : this.mAbsBydAutoInstrumentListeners) {
                            listener27.onMalfunctionInfoChanged(20, cabinPacket.getMalFunctionInfosPleaseCheckLbmsRed());
                        }
                        break;
                    case 557857075:
                        for (AbsBYDAutoInstrumentListener listener28 : this.mAbsBydAutoInstrumentListeners) {
                            listener28.onMalfunctionInfoChanged(21, cabinPacket.getMalFunctionInfosPleaseCheckLvSupSysRed());
                        }
                        break;
                    case 557857076:
                        for (AbsBYDAutoInstrumentListener listener29 : this.mAbsBydAutoInstrumentListeners) {
                            listener29.onMalfunctionInfoChanged(22, cabinPacket.getMalFunctionInfosPleaseCheckEngineSystemRed());
                        }
                        break;
                    case 557857077:
                        for (AbsBYDAutoInstrumentListener listener30 : this.mAbsBydAutoInstrumentListeners) {
                            listener30.onMalfunctionInfoChanged(23, cabinPacket.getMalFunctionInfosEngAccessoriesLimitedRed());
                        }
                        break;
                    case 557857078:
                        for (AbsBYDAutoInstrumentListener listener31 : this.mAbsBydAutoInstrumentListeners) {
                            listener31.onMalfunctionInfoChanged(24, cabinPacket.getMalFunctionInfosTransmisLimitedinFuncRed());
                        }
                        break;
                    case 557857079:
                        for (AbsBYDAutoInstrumentListener listener32 : this.mAbsBydAutoInstrumentListeners) {
                            listener32.onMalfunctionInfoChanged(25, cabinPacket.getMalFunctionInfosHighClutchTemperatureRed());
                        }
                        break;
                    case 557857080:
                        for (AbsBYDAutoInstrumentListener listener33 : this.mAbsBydAutoInstrumentListeners) {
                            listener33.onMalfunctionInfoChanged(26, cabinPacket.getMalFunctionInfosGearboxOilTempTooHighRed());
                        }
                        break;
                    case 557857081:
                        for (AbsBYDAutoInstrumentListener listener34 : this.mAbsBydAutoInstrumentListeners) {
                            listener34.onMalfunctionInfoChanged(27, cabinPacket.getMalFunctionInfosPleaseCheckSrsSystemRed());
                        }
                        break;
                    case 557857082:
                        for (AbsBYDAutoInstrumentListener listener35 : this.mAbsBydAutoInstrumentListeners) {
                            listener35.onMalfunctionInfoChanged(28, cabinPacket.getMalFunctionInfosPleaseCheckAdbSystemRed());
                        }
                        break;
                    case 557857140:
                        for (AbsBYDAutoInstrumentListener listener36 : this.mAbsBydAutoInstrumentListeners) {
                            listener36.onSportModeStateChanged(cabinPacket.getSportModeStateR());
                        }
                        caseFeatureIds(4570, Integer.valueOf(cabinPacket.getSportModeStateR()));
                        break;
                    case 557857144:
                        for (AbsBYDAutoInstrumentListener listener37 : this.mAbsBydAutoInstrumentListeners) {
                            listener37.onDayNightModeChanged(cabinPacket.getDayNightModeR());
                        }
                        break;
                    case 557857146:
                        for (AbsBYDAutoInstrumentListener listener38 : this.mAbsBydAutoInstrumentListeners) {
                            listener38.onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoTwoR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case 557857152:
                        for (AbsBYDAutoInstrumentListener listener39 : this.mAbsBydAutoInstrumentListeners) {
                            listener39.onTotalMileageChanged(cabinPacket.getTotalMileageR());
                        }
                        break;
                    case 557857156:
                        for (AbsBYDAutoInstrumentListener listener40 : this.mAbsBydAutoInstrumentListeners) {
                            listener40.onMaintenanceInfoChanged(cabinPacket.getMaintenanceInfoFourR(), cabinPacket.getMaintenanceInfoThreeR());
                        }
                        break;
                    case 557857158:
                        for (AbsBYDAutoInstrumentListener listener41 : this.mAbsBydAutoInstrumentListeners) {
                            listener41.onSpeedUpFormInsChange(cabinPacket.getSpeedUpFormIns());
                        }
                        break;
                    case 557857169:
                        for (AbsBYDAutoInstrumentListener listener42 : this.mAbsBydAutoInstrumentListeners) {
                            listener42.onAllWeatherLightsStateChanged(cabinPacket.getAllWeatherLightsStateR());
                        }
                        caseFeatureIds(BYDAutoFeatureIds.INSTRUMENT_ALL_WEATHER_LIGHT_SWITCH_FEEDBACK, Integer.valueOf(cabinPacket.getAllWeatherLightsStateR()));
                        break;
                    case 557857207:
                        for (AbsBYDAutoInstrumentListener listener43 : this.mAbsBydAutoInstrumentListeners) {
                            listener43.onMasterVolumeChanged(1, cabinPacket.getMasterVolumeFirstR());
                        }
                        break;
                    case 557857208:
                        for (AbsBYDAutoInstrumentListener listener44 : this.mAbsBydAutoInstrumentListeners) {
                            listener44.onMasterVolumeChanged(2, cabinPacket.getMasterVolumeSecondR());
                        }
                        break;
                    case 557857209:
                        for (AbsBYDAutoInstrumentListener listener45 : this.mAbsBydAutoInstrumentListeners) {
                            listener45.onMuteStateChanged(1, cabinPacket.getMuteStateFirstR());
                        }
                        break;
                    case 557857210:
                        for (AbsBYDAutoInstrumentListener listener46 : this.mAbsBydAutoInstrumentListeners) {
                            listener46.onMuteStateChanged(2, cabinPacket.getMuteStateSecondR());
                        }
                        break;
                    case 557867009:
                        for (AbsBYDAutoInstrumentListener listener47 : this.mAbsBydAutoInstrumentListeners) {
                            listener47.onSpeedUpCountStateChanged(cabinPacket.getSpeedUpCountStateR());
                        }
                        break;
                    case 557867010:
                        for (AbsBYDAutoInstrumentListener listener48 : this.mAbsBydAutoInstrumentListeners) {
                            listener48.onSpeedUnitChanged(cabinPacket.getSpeedUnit());
                        }
                        break;
                    case 557868033:
                        for (AbsBYDAutoInstrumentListener listener49 : this.mAbsBydAutoInstrumentListeners) {
                            listener49.onMileageUnitChanged(cabinPacket.getMileageUnitR());
                        }
                        break;
                    case 557868037:
                        for (AbsBYDAutoInstrumentListener listener50 : this.mAbsBydAutoInstrumentListeners) {
                            listener50.onAreaInfoChanged(cabinPacket.getAreaInfoR());
                        }
                        break;
                    case 557868043:
                        for (AbsBYDAutoInstrumentListener listener51 : this.mAbsBydAutoInstrumentListeners) {
                            listener51.onDistanceMileageUnitChanged(cabinPacket.getDistanceMileageUnitR());
                        }
                        break;
                    case 557869062:
                        for (AbsBYDAutoInstrumentListener listener52 : this.mAbsBydAutoInstrumentListeners) {
                            listener52.onLowPowerBatteryWarnLightColorChanged(cabinPacket.getLowPowerBatteryWarnLightColorR());
                        }
                        break;
                    case 557869067:
                        for (AbsBYDAutoInstrumentListener listener53 : this.mAbsBydAutoInstrumentListeners) {
                            listener53.onLowFuelWarnLightColorChanged(cabinPacket.getLowFuelWarnLightColorR());
                        }
                        break;
                    case 557875262:
                        for (AbsBYDAutoInstrumentListener listener54 : this.mAbsBydAutoInstrumentListeners) {
                            listener54.onINSCenterDisplay(cabinPacket.getINSCenterDisplayR());
                        }
                        break;
                    case 559954186:
                        for (AbsBYDAutoInstrumentListener listener55 : this.mAbsBydAutoInstrumentListeners) {
                            listener55.onExternalChargingPowerChanged(cabinPacket.getExternalChargingPower());
                        }
                        caseFeatureIds(3932, Integer.valueOf((int) cabinPacket.getExternalChargingPower()));
                        caseFeatureIds(4085, Integer.valueOf((int) cabinPacket.getExternalChargingPower()));
                        break;
                    default:
                        switch (id) {
                            case 557857051:
                                for (AbsBYDAutoInstrumentListener listener56 : this.mAbsBydAutoInstrumentListeners) {
                                    listener56.onUnitChanged(3, cabinPacket.getUnitPowerLeftR());
                                }
                                break;
                            case 557857052:
                                for (AbsBYDAutoInstrumentListener listener57 : this.mAbsBydAutoInstrumentListeners) {
                                    listener57.onAlarmBuzzleStateChange(cabinPacket.getAlarmBuzzleState());
                                }
                                break;
                            default:
                                switch (id) {
                                    case 557857084:
                                        for (AbsBYDAutoInstrumentListener listener58 : this.mAbsBydAutoInstrumentListeners) {
                                            listener58.onMalfunctionInfoChanged(29, cabinPacket.getMalFunctionInfosCheckHeadlightSysYellow());
                                        }
                                        break;
                                    case 557857085:
                                        for (AbsBYDAutoInstrumentListener listener59 : this.mAbsBydAutoInstrumentListeners) {
                                            listener59.onMalfunctionInfoChanged(30, cabinPacket.getMalFunctionInfosEngineStartFailedRed());
                                        }
                                        break;
                                    case 557857086:
                                        for (AbsBYDAutoInstrumentListener listener60 : this.mAbsBydAutoInstrumentListeners) {
                                            listener60.onMalfunctionInfoChanged(31, cabinPacket.getMalFunctionInfosCoolantLiquiLevelLowYellow());
                                        }
                                        break;
                                    case 557857087:
                                        for (AbsBYDAutoInstrumentListener listener61 : this.mAbsBydAutoInstrumentListeners) {
                                            listener61.onMalfunctionInfoChanged(32, cabinPacket.getMalFunctionInfosAcHotManaFailYellow());
                                        }
                                        break;
                                    default:
                                        switch (id) {
                                            case 557857090:
                                                for (AbsBYDAutoInstrumentListener listener62 : this.mAbsBydAutoInstrumentListeners) {
                                                    listener62.onMalfunctionInfoChanged(33, cabinPacket.getMalFunctionInfosCheckTheOilMonitSysYellow());
                                                }
                                                break;
                                            case 557857091:
                                                for (AbsBYDAutoInstrumentListener listener63 : this.mAbsBydAutoInstrumentListeners) {
                                                    listener63.onMalfunctionInfoChanged(34, cabinPacket.getMalFunctionInfosCheckTirePresMonSysYellow());
                                                }
                                                break;
                                            default:
                                                switch (id) {
                                                    case 557857093:
                                                        for (AbsBYDAutoInstrumentListener listener64 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener64.onMalfunctionInfoChanged(35, cabinPacket.getMalFunctionInfosCheckTheBrakSysYellow());
                                                        }
                                                        break;
                                                    case 557857094:
                                                        for (AbsBYDAutoInstrumentListener listener65 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener65.onMalfunctionInfoChanged(36, cabinPacket.getMalFunctionInfosPleaseCheckAbsSysYellow());
                                                        }
                                                        break;
                                                    case 557857095:
                                                        for (AbsBYDAutoInstrumentListener listener66 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener66.onMalfunctionInfoChanged(37, cabinPacket.getMalFunctionInfosCheckGenerationSysYellow());
                                                        }
                                                        break;
                                                    case 557857096:
                                                        for (AbsBYDAutoInstrumentListener listener67 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener67.onMalfunctionInfoChanged(38, cabinPacket.getMalFunctionInfosPleaseCheckGearSysYellow());
                                                        }
                                                        break;
                                                    case 557857097:
                                                        for (AbsBYDAutoInstrumentListener listener68 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener68.onMalfunctionInfoChanged(39, cabinPacket.getMalFunctionInfosPleaseCheckEspSysYellow());
                                                        }
                                                        break;
                                                    case 557857098:
                                                        for (AbsBYDAutoInstrumentListener listener69 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener69.onMalfunctionInfoChanged(40, cabinPacket.getMalFunctionInfosPleaseCheckHdcSysYellow());
                                                        }
                                                        break;
                                                    case 557857099:
                                                        for (AbsBYDAutoInstrumentListener listener70 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener70.onMalfunctionInfoChanged(41, cabinPacket.getMalFunctionInfosCheckBrakePlateYellow());
                                                        }
                                                        break;
                                                    case 557857100:
                                                        for (AbsBYDAutoInstrumentListener listener71 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener71.onMalfunctionInfoChanged(42, cabinPacket.getMalFunctionInfosCheckVideoControllerYellow());
                                                        }
                                                        break;
                                                    case 557857101:
                                                        for (AbsBYDAutoInstrumentListener listener72 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener72.onMalfunctionInfoChanged(43, cabinPacket.getMalFunctionInfosCheckFmrrYellow());
                                                        }
                                                        break;
                                                    case 557857102:
                                                        for (AbsBYDAutoInstrumentListener listener73 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener73.onMalfunctionInfoChanged(44, cabinPacket.getMalFunctionInfosAebFunctLimitYellow());
                                                        }
                                                        break;
                                                    case 557857103:
                                                        for (AbsBYDAutoInstrumentListener listener74 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener74.onMalfunctionInfoChanged(45, cabinPacket.getMalFunctionInfosCheckPredCollAlaSysYellow());
                                                        }
                                                        break;
                                                    case 557857104:
                                                        for (AbsBYDAutoInstrumentListener listener75 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener75.onMalfunctionInfoChanged(46, cabinPacket.getMalFunctionInfosCheckPedestriDeteSysYellow());
                                                        }
                                                        break;
                                                    case 557857105:
                                                        for (AbsBYDAutoInstrumentListener listener76 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener76.onMalfunctionInfoChanged(47, cabinPacket.getMalFunctionInfosLimitedLaneKeepingYellow());
                                                        }
                                                        break;
                                                    case 557857106:
                                                        for (AbsBYDAutoInstrumentListener listener77 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener77.onMalfunctionInfoChanged(48, cabinPacket.getMalFunctionInfosLimitedAdaptiveCruiseYellow());
                                                        }
                                                        break;
                                                    case 557857107:
                                                        for (AbsBYDAutoInstrumentListener listener78 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener78.onMalfunctionInfoChanged(49, cabinPacket.getMalFunctionInfosLimitLaneDepartuFuncYellow());
                                                        }
                                                        break;
                                                    case 557857108:
                                                        for (AbsBYDAutoInstrumentListener listener79 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener79.onMalfunctionInfoChanged(50, cabinPacket.getMalFunctionInfosLimitedBlindSpotMoniYellow());
                                                        }
                                                        break;
                                                    case 557857109:
                                                        for (AbsBYDAutoInstrumentListener listener80 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener80.onMalfunctionInfoChanged(51, cabinPacket.getMalFunctionInfosCheckDischargeEquipYellow());
                                                        }
                                                        break;
                                                    case 557857110:
                                                        for (AbsBYDAutoInstrumentListener listener81 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener81.onMalfunctionInfoChanged(52, cabinPacket.getMalFunctionInfosCheckChargOutEquipYellow());
                                                        }
                                                        break;
                                                    case 557857111:
                                                        for (AbsBYDAutoInstrumentListener listener82 : this.mAbsBydAutoInstrumentListeners) {
                                                            listener82.onMalfunctionInfoChanged(53, cabinPacket.getMalFunctionInfosCheckOnBoardcharSysYellow());
                                                        }
                                                        break;
                                                    default:
                                                        switch (id) {
                                                            case 557857129:
                                                                for (AbsBYDAutoInstrumentListener listener83 : this.mAbsBydAutoInstrumentListeners) {
                                                                    listener83.onMalfunctionInfoChanged(54, cabinPacket.getMalFunctionInfosApaFunctionLimitWhite());
                                                                }
                                                                break;
                                                            case 557857130:
                                                                for (AbsBYDAutoInstrumentListener listener84 : this.mAbsBydAutoInstrumentListeners) {
                                                                    listener84.onMalfunctionInfoChanged(55, cabinPacket.getMalFunctionInfosCheckMotPretBeltSysYellow());
                                                                }
                                                                break;
                                                            case 557857131:
                                                                for (AbsBYDAutoInstrumentListener listener85 : this.mAbsBydAutoInstrumentListeners) {
                                                                    listener85.onMalfunctionInfoChanged(56, cabinPacket.getMalFunctionInfosPleaseCheckMemorySysYellow());
                                                                }
                                                                break;
                                                            case 557857132:
                                                                for (AbsBYDAutoInstrumentListener listener86 : this.mAbsBydAutoInstrumentListeners) {
                                                                    listener86.onMalfunctionInfoChanged(57, cabinPacket.getMalFunctionInfosPleaseCheckSunrooSysYellow());
                                                                }
                                                                break;
                                                            case 557857133:
                                                                for (AbsBYDAutoInstrumentListener listener87 : this.mAbsBydAutoInstrumentListeners) {
                                                                    listener87.onINSNavDisplayMode(cabinPacket.getINSNavDisplayModeR());
                                                                }
                                                                break;
                                                            default:
                                                                switch (id) {
                                                                    case 557857135:
                                                                        for (AbsBYDAutoInstrumentListener listener88 : this.mAbsBydAutoInstrumentListeners) {
                                                                            listener88.onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplaySend4C1());
                                                                        }
                                                                        break;
                                                                    case 557857136:
                                                                        for (AbsBYDAutoInstrumentListener listener89 : this.mAbsBydAutoInstrumentListeners) {
                                                                            listener89.onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplayRespond40C());
                                                                        }
                                                                        break;
                                                                    case 557857137:
                                                                        for (AbsBYDAutoInstrumentListener listener90 : this.mAbsBydAutoInstrumentListeners) {
                                                                            listener90.onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplaySend26F());
                                                                        }
                                                                        break;
                                                                    case 557857138:
                                                                        for (AbsBYDAutoInstrumentListener listener91 : this.mAbsBydAutoInstrumentListeners) {
                                                                            listener91.onINSAdasCenterDisplayChanged(cabinPacket.getINSAdasCenterDisplay());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (id) {
                                                                            case 557857171:
                                                                                for (AbsBYDAutoInstrumentListener listener92 : this.mAbsBydAutoInstrumentListeners) {
                                                                                    listener92.onHeadlightControlModeChanged(cabinPacket.getHeadlightControlModeR());
                                                                                }
                                                                                break;
                                                                            case 557857172:
                                                                                for (AbsBYDAutoInstrumentListener listener93 : this.mAbsBydAutoInstrumentListeners) {
                                                                                    listener93.onRearFogLightStateChanged(cabinPacket.getRearFogLightStateR());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (id) {
                                                                                    case 557857182:
                                                                                        for (AbsBYDAutoInstrumentListener listener94 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener94.onMusicProgressChanged(1, cabinPacket.getMusicProgressFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857183:
                                                                                        for (AbsBYDAutoInstrumentListener listener95 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener95.onMusicProgressChanged(2, cabinPacket.getMusicProgressSecondR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857184:
                                                                                        for (AbsBYDAutoInstrumentListener listener96 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener96.onMusicControlStatusChanged(1, cabinPacket.getMusicControlStatusFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857185:
                                                                                        for (AbsBYDAutoInstrumentListener listener97 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener97.onMusicControlStatusChanged(2, cabinPacket.getMusicControlStatusSecondR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857186:
                                                                                        for (AbsBYDAutoInstrumentListener listener98 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener98.onMusicChannelStatusChanged(1, cabinPacket.getMusicChannelStatusFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857187:
                                                                                        for (AbsBYDAutoInstrumentListener listener99 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener99.onMusicChannelStatusChanged(2, cabinPacket.getMusicChannelStatusSecondR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857188:
                                                                                        for (AbsBYDAutoInstrumentListener listener100 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener100.onMusicResourceConfigChanged(1, cabinPacket.getMusicResourceConfigFirstR());
                                                                                        }
                                                                                        break;
                                                                                    case 557857189:
                                                                                        for (AbsBYDAutoInstrumentListener listener101 : this.mAbsBydAutoInstrumentListeners) {
                                                                                            listener101.onMusicResourceConfigChanged(2, cabinPacket.getMusicResourceConfigSecondR());
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

    public int getMalfunctionInfo(int typeName) {
        Log.v(TAG, "getMalfunctionInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMalfunctionInfo mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMalfunctionInfo(typeName);
    }

    public int getAlarmBuzzleState() {
        Log.v(TAG, "getMalfunctionInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMalfunctionInfo mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAlarmBuzzleState();
    }

    public int getMaintenanceInfo(int typeName) {
        Log.v(TAG, "getMaintenanceInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMaintenanceInfo mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return 0;
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setUnit(int unitName, int unitValue) {
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
        if (unitName == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitTemperaure(unitValue);
        }
        if (unitName == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitPressure(unitValue);
        }
        if (unitName == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitFuel(unitValue);
        }
        if (unitName == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setUnitUnitPower(unitValue);
        }
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
        float power = this.mTsManagerImpl.getCarCabinAdapterManager().getExternalChargingPower();
        if (power > 99999.9d || power < 0.0d) {
            Log.v(TAG, "The value is illegal");
            return -1.0f;
        }
        return power;
    }

    public void registerListener(AbsBYDAutoInstrumentListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoInstrumentListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe instrument listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoInstrumentListener(listener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (!this.mAbsBydAutoInstrumentListeners.contains(listener)) {
            this.mAbsBydAutoInstrumentListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(listener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeInstrumentCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoInstrumentListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    @Deprecated
    public int getInstrumentTheme() {
        Log.v(TAG, "getInstrumentTheme is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentTheme mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentTheme();
    }

    @Deprecated
    public int setInstrumentTheme(int value) {
        Log.v(TAG, "setInstrumentTheme is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestInstrumentManagerImplCallback.SET_INSTRUMENT_THEME, value);
            Log.v(TAG, "setInstrumentTheme mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentTheme(value);
    }

    @Deprecated
    public int getInstrumentBrightness() {
        Log.v(TAG, "getInstrumentBrightness is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentBrightness mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int brightness = this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentBrightnessLeft();
        if (brightness < 1 || brightness > 22) {
            Log.v(TAG, "The value is illegal！");
            return -1;
        }
        return brightness;
    }

    @Deprecated
    public int setInstrumentBrightness(int value) {
        Log.v(TAG, "setInstrumentBrightness is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestInstrumentManagerImplCallback.SET_INSTRUMENT_BRIGHTNESS, value);
            Log.v(TAG, "setInstrumentBrightness mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentBrightness(value);
    }

    public int getInstrumentAutoDimmState() {
        Log.v(TAG, "getInstrumentAutoDimmState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentAutoDimmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getInstrumentAutoDimmState();
    }

    public int setInstrumentAutoDimmState(int value) {
        Log.v(TAG, "setInstrumentAutoDimmState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestInstrumentManagerImplCallback.SET_INSTRUMENT_AUTO_DIMM_STATE, value);
            Log.v(TAG, "setInstrumentAutoDimmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentAutoDimmState(value);
    }

    public int setMilesType(int type) {
        Log.v(TAG, "setMilesType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestInstrumentManagerImplCallback.SET_MILES_TYPE, type);
            Log.v(TAG, "setMilesType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMilesType(type);
    }

    public int getMilesType() {
        Log.v(TAG, "getMilesType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMilesType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMilesType();
    }

    public long getMilesByType(int type) {
        Log.v(TAG, "getMilesByType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMilesByType mForTestValue is" + this.mForTest + " execute return");
            return 0L;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0L;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMilesByType(type);
    }

    public int getUnit(int unitName) {
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
        if (unitName == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitTemperatureLeft();
        }
        if (unitName == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitPressureLeft();
        }
        if (unitName == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitFuelConsumptionAndDistanceLeft();
        }
        if (unitName == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getUnitPowerLeft();
        }
        return 0;
    }

    @Deprecated
    public int getMaintenanceInfoTimeState() {
        Log.v(TAG, "getMaintenanceInfoTimeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMaintenanceInfoTimeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMaintenanceInfoTimeState();
    }

    @Deprecated
    public int setMaintenanceInfoTimeState(int value) {
        Log.v(TAG, "setMaintenanceInfoTimeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setMaintenanceInfoTimeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoTimeState(value);
    }

    public int setMaintenanceInfo(int typeName, int infoValue) {
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
        if (typeName == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoMileState(infoValue);
        }
        if (typeName == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoTimeState(infoValue);
        }
        return 0;
    }

    @Deprecated
    public int getMaintenanceInfoMileState() {
        Log.v(TAG, "getMaintenanceInfoMileState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMaintenanceInfoMileState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMaintenanceInfoMileState();
    }

    @Deprecated
    public int setMaintenanceInfoMileState(int value) {
        Log.v(TAG, "setMaintenanceInfoMileState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestInstrumentManagerImplCallback.SET_MILES_TYPE, value);
            Log.v(TAG, "setMaintenanceInfoMileState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMaintenanceInfoMileState(value);
    }

    public int[] getMalfunctionInfos() {
        Log.v(TAG, "getMalfunctionInfos is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMalfunctionInfos mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMalfunctionInfos();
    }

    public int getInsNavDisplayMode() {
        Log.v(TAG, "getINSNavDisplayMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getINSNavDisplayMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getINSNavDisplayMode();
    }

    public int setINSNavDisplayMode(int value) {
        Log.v(TAG, "setINSNavDisplayMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setINSNavDisplayMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setINSNavDisplayMode(value);
    }

    public int setINSAdasCenterDisplay(int value) {
        Log.v(TAG, "setINSAdasCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setINSAdasCenterDisplay mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setINSAdasCenterDisplay(value);
    }

    public int getINSAdasCenterDisplay() {
        Log.v(TAG, "getINSAdasCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getINSAdasCenterDisplay mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getINSAdasCenterDisplay();
    }

    public int getINSCenterDisplay() {
        Log.v(TAG, "getINSCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getINSCenterDisplay mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getINSCenterDisplay();
    }

    public int setINSCenterDisplay(int value) {
        Log.v(TAG, "setINSCenterDisplay is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setINSCenterDisplay mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setINSCenterDisplay(value);
    }

    public int getSportModeState() {
        Log.v(TAG, "getSportModeState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSportModeState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getSportModeState();
    }

    public int getSpeedUpCountState() {
        Log.v(TAG, "getSpeedUpCountState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSpeedUpCountState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUpCountState();
    }

    public int setSpeedUpCountState(int state) {
        Log.v(TAG, "setSpeedUpCountState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setSpeedUpCountState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setSpeedUpCountState(state);
    }

    public int setDayNightMode(int mode) {
        Log.v(TAG, "setDayNightMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setDayNightMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setDayNightMode(mode);
    }

    public int getDayNightMode() {
        Log.v(TAG, "getDayNightMode is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDayNightMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getDayNightMode();
    }

    public int getSpeedUnit() {
        Log.v(TAG, "getSpeedUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSpeedUnit mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUnit();
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
        if (totalMileage < 0 || totalMileage > 99999999) {
            Log.v(TAG, "The vale is illegal");
            return -1;
        }
        return totalMileage;
    }

    public int getMileageUnit() {
        Log.v(TAG, "getMileageUnit is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMileageUnit mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getMileageUnit();
    }

    public int getInstrumentScreenType() {
        Log.v(TAG, "getInstrumentScreenType is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return 2;
    }

    public int getInstrumentConfigure() {
        Log.v(TAG, "getInstrumentConfigure is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return 1;
    }

    public int getSpeedUpFormIns() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getSpeedUpFormIns mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getSpeedUpFormIns();
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 686:
                            bydAutoEventValue.intValue = getInstrumentBrightness();
                            sparseArray.put(686, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3820:
                            bydAutoEventValue.intValue = getMilesType();
                            sparseArray.put(3820, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3844:
                            bydAutoEventValue.intValue = getInstrumentBrightness();
                            sparseArray.put(3844, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3932:
                            bydAutoEventValue.floatValue = getExternalChargingPower();
                            sparseArray.put(3932, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 4038:
                            bydAutoEventValue.intValue = getMileageUnit();
                            sparseArray.put(4038, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4056:
                            bydAutoEventValue.intValue = getSpeedUnit();
                            sparseArray.put(4056, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4167:
                            bydAutoEventValue.intValue = getInstrumentConfigure();
                            sparseArray.put(4167, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4200:
                            bydAutoEventValue.intValue = getUnit(2);
                            sparseArray.put(4200, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4205:
                            bydAutoEventValue.intValue = getUnit(3);
                            sparseArray.put(4205, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4208:
                            bydAutoEventValue.intValue = getUnit(1);
                            sparseArray.put(4208, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4570:
                            bydAutoEventValue.intValue = getSportModeState();
                            sparseArray.put(4570, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoInstrumentListener listener : this.mAbsBydAutoInstrumentListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        switch (eventType) {
            case 607:
                setINSAdasCenterDisplay(intValue);
                Log.v(TAG, "Setting.SETTING_ADAS_DRIVER_ASSISTENCE_INFO_DISPLAY_SET:");
                return 1;
            case 687:
                setINSCenterDisplay(intValue);
                Log.v(TAG, "Setting.SETTING_INSTRUMENT_DRIVING_INFORMATION_SET:");
                return 1;
            case 736:
                setMilesType(intValue);
                Log.v(TAG, "Setting.SETTING_MILLAGE_INFO_DISPLAY_SET:");
                return 1;
            case 1222:
                setDayNightMode(intValue);
                Log.v(TAG, "Setting.SET_METER_DEPTH_MODE_SET:");
                return 1;
            case 3843:
                setInstrumentAutoDimmState(intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_BACKLIGHT_AUTO_MODE_STATE_SET:");
                return 1;
            case 3845:
                setInstrumentBrightness(intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_BACKLIGHT_BRIGHTNESS_SET:");
                return 1;
            case 4114:
                setMaintenanceInfo(2, intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_MAINTENANCE_MILEAGE_SET:");
                return 1;
            case 4116:
                setMaintenanceInfo(1, intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_MAINTENANCE_TIME_SET:");
                return 1;
            case 4202:
                setUnit(2, intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_FUELCON_DISTANCE_SET:");
                return 1;
            case 4207:
                setUnit(3, intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_POWER_SET:");
                return 1;
            case 4210:
                setUnit(1, intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_PRESSURE_SET:");
                return 1;
            case 4215:
                setUnit(0, intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_UNIT_TEMPERATURE_SET:");
                return 1;
            case 4217:
                setSpeedUpCountState(intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_ACCELERATION_TIMER_DISPLAY_SET:");
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }

    public int setCallState(int state) {
        Log.v(TAG, "setCallState is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        if (state != 0 && state != 1 && state != 2 && state != 3 && state != 4 && state != 5) {
            return -1;
        }
        Log.v(TAG, "setCallState value = " + state);
        return this.mTsManagerImpl.getGeneralAdapterManager().setCallState(state);
    }

    public int setNaviState(int state) {
        Log.v(TAG, "setNaviState value = " + state);
        if (1 == this.mForTest) {
            Log.v(TAG, " setNaviState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (1 == state) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setNaviState(2);
        }
        if (2 == state) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setNaviState(4);
        }
        return 0;
    }

    public int getAreaInfoR() {
        Log.i(TAG, "getAreaInfoR is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "AreaInfoR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAreaInfoR();
    }

    public int setNotifyMapStateS(int arg0) {
        Log.i(TAG, "setNotifyMapStateS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyMapStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setNotifyMapStateS(arg0);
    }

    public int setInstrumentDisplayLanguage(int language) {
        Log.i(TAG, "setInstrumentDisplayLanguageS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyMapStateS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setInstrumentDisplayLanguageS(language);
    }

    public int setNotifyScreenStatusS(int arg0) {
        Log.i(TAG, "setNotifyScreenStatusS is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setNotifyScreenStatusS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setNotifyScreenStatusS(arg0);
    }

    public int setAreaInfo(int arg0) {
        Log.i(TAG, "setAreaInfo is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAreaInfo mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setAreaInfo(arg0);
    }

    public int getLowPowerBatteryWarnLightColor() {
        Log.i(TAG, "getLowPowerBatteryWarnLightColor is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "AreaInfoR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getLowPowerBatteryWarnLightColor();
    }

    public int getLowFuelWarnLightColor() {
        Log.i(TAG, "getLowFuelWarnLightColor is execute");
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "AreaInfoR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getLowFuelWarnLightColor();
    }

    public int getAllWeatherLightsState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAllWeatherLightsState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAllWeatherLightsState();
    }

    public int setHeadlightControlMode(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setHeadlightControlMode(mode);
    }

    public int getHeadlightControlMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHeadlightControlMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getHeadlightControlMode();
    }

    public int getRearFogLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRearFogLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getRearFogLightState();
    }

    public int setRearFogLightState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRearFogLightState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setRearFogLightState(state);
    }

    public int setPadPrsntTime(int hour, int minute, int second) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPadPrsntTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setPadPrsntTime(hour, minute, second);
    }

    public int setPadTotalTime(int hour, int minute, int second) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setPadTotalTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setPadTotalTime(hour, minute, second);
    }

    public int setMasterVolume(int value) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMasterVolume mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMasterVolume(value);
    }

    public int setMuteState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMuteState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMuteState(state);
    }

    public int getMusicProgress(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicProgress mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicProgressFirst(type);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicProgressSecond(type);
        }
        return 0;
    }

    public int getMusicControlStatus(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicControlStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicControlStatusFirst(type);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicControlStatusSecond(type);
        }
        return 0;
    }

    public int getMusicChannelStatus(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicChannelStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicChannelStatusFirst(type);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicChannelStatusSecond(type);
        }
        return 0;
    }

    public int getMusicResourceConfig(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMusicResourceConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicResourceConfigFirst(type);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMusicResourceConfigSecond(type);
        }
        return 0;
    }

    public int setMusicInfo(int playState, int radioState, int playProgress, int musicSource) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMusicInfo mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMusicInfo(playState, radioState, playProgress, musicSource);
    }

    public int setMusicName(String name) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMusicName mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMusicName(name);
    }

    public int setRadioChannel(String channel) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setRadioChannel mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setRadioChannel(channel);
    }

    public int setMusicSingerName(String name) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setMusicSingerName mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setMusicSingerName(name);
    }

    public int getMasterVolume(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMasterVolume mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMasterVolumeFirst(type);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMasterVolumeSecond(type);
        }
        return 0;
    }

    public int getMuteState(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMuteState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMuteStateFirst(type);
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getMuteStateSecond(type);
        }
        return 0;
    }

    public int setScreenDisplayState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setScreenDisplayState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return 0;
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int clearDistanceMilesType(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " clearDistanceMilesType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().clearDistanceMilesType(type);
    }

    public int getDistanceMileageUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDistanceMileageUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getDistanceMileageUnit();
    }
}
