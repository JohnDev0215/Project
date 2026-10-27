package android.hardware.bydauto.charging.tsimpl;

import a.a.e.c;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnChargingChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ChargingManagerImpl extends AbsTsMyCar {
    private static final int BASE_MILLIONS_SECOND = 1000;
    private static final int BASE_SECOND = 60;
    private static final int CHARGING_COMMAND_INVALID_VALUE = 0;
    private static final float CHARGING_COMMAND_INVALID_VALUE_FLOAT = 0.0f;
    private static final float CHARGING_COMMAND_INVALID_VALUE_float = 0.0f;
    private static final String S_KEY_FOR_TEST = "KEY_FOR_TEST_ChargingManagerImpl";
    private static final int S_VALUE_FOR_TEST = 1;
    private static final String TAG = "ChargingManagerImpl";
    private static volatile ChargingManagerImpl mInstance;
    private AbsBYDAutoChargingListener mAbsBydAutoChargingListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTestValue;
    private c mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private boolean mIgnoreBydPermission = false;
    private int evYear = 0;
    private int evMonth = 0;
    private int evDay = 0;
    private int evHour = 0;
    private int evMinute = 0;
    private int evYearEnd = 0;
    private int evMonthEnd = 0;
    private int evDayEnd = 0;
    private int evHourEnd = 0;
    private int evMinuteEnd = 0;
    private int dmYear = 0;
    private int dmMonth = 0;
    private int dmDay = 0;
    private int dmHour = 0;
    private int dmMinute = 0;
    private int dmYearEnd = 0;
    private int dmMonthEnd = 0;
    private int dmDayEnd = 0;
    private int dmHourEnd = 0;
    private int dmMinuteEnd = 0;
    private int carPlanYear = 0;
    private int carPlanMonth = 0;
    private int carPlanDay = 0;
    private int carPlanHour = 0;
    private int carPlanMinute = 0;
    private int syncTimeYear = 0;
    private int syncTimeMonth = 0;
    private int syncTimeDay = 0;
    private int syncTimeHour = 0;
    private int syncTimeMinute = 0;
    private int mHevChargePlanStartTimeYearR = 0;
    private int mHevChargePlanStartTimeMonthR = 0;
    private int mHevChargePlanStartTimeYearDayR = 0;
    private int mHevChargePlanStartTimeYearHourR = 0;
    private int mHevChargePlanStartTimeYearMinuteR = 0;
    private int mHevChargePlaneEndTimeYearR = 0;
    private int mHevChargePlanEndTimeMonthR = 0;
    private int mHevChargePlanEndTimeYearDayR = 0;
    private int mHevChargePlanEndTimeYearHourR = 0;
    private int mHevChargePlanEndTimeYearMinuteR = 0;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private final CopyOnWriteArrayList<AbsBYDAutoChargingListener> mAbsBydAutoChargingListeners = new CopyOnWriteArrayList<>();
    private int[] mDisChargeTime = new int[2];

    ChargingManagerImpl(Context context) {
        this.mForTestValue = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTestValue = i;
            if (1 == i) {
                Log.d(TAG, "ChargingManagerImpl mForTestValue is" + this.mForTestValue + " execute return");
                this.mTestCallback = new c();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(this.mContext);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnChargingChangedListener(new OnChargingChangedListener() { // from class: android.hardware.bydauto.charging.tsimpl.ChargingManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                Log.d(ChargingManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onChargingChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (ChargingManagerImpl.this.mAbsBydAutoChargingListeners.isEmpty()) {
                    Log.d(ChargingManagerImpl.TAG, "mAbsBydAutoChargingListener is null. ");
                } else {
                    ChargingManagerImpl.this.charginCallback(i2, i3, carBodyPacket);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void charginCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "charginCallback propertyId:" + i);
        switch (i) {
            case ChargingHalId.ID_CHARGER_FAULT_STATER /* 557855744 */:
                Iterator<AbsBYDAutoChargingListener> it = this.mAbsBydAutoChargingListeners.iterator();
                while (it.hasNext()) {
                    it.next().onChargerFaultStateChanged(carBodyPacket.getChargerFaultStateR());
                }
                break;
            case ChargingHalId.ID_CHARGER_WORK_STATER /* 557855745 */:
                Iterator<AbsBYDAutoChargingListener> it2 = this.mAbsBydAutoChargingListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onBatteryManagementDeviceStateChanged(carBodyPacket.getChargerWorkStateR());
                }
                break;
            default:
                switch (i) {
                    case ChargingHalId.ID_CHARING_PORT_LOCK_REBACK_STATER /* 557855748 */:
                        Iterator<AbsBYDAutoChargingListener> it3 = this.mAbsBydAutoChargingListeners.iterator();
                        while (it3.hasNext()) {
                            it3.next().onChargingPortLockRebackStateChanged(carBodyPacket.getCharingPortLockRebackStateR());
                        }
                        break;
                    case ChargingHalId.ID_DISCHARGE_REQUEST_STATER /* 557855749 */:
                        Iterator<AbsBYDAutoChargingListener> it4 = this.mAbsBydAutoChargingListeners.iterator();
                        while (it4.hasNext()) {
                            it4.next().onDischargeRequestStateChanged(carBodyPacket.getDischargeRequestStateR());
                        }
                        break;
                    case ChargingHalId.ID_CHARGER_STATER /* 557855750 */:
                        Iterator<AbsBYDAutoChargingListener> it5 = this.mAbsBydAutoChargingListeners.iterator();
                        while (it5.hasNext()) {
                            it5.next().onChargerStateChanged(carBodyPacket.getChargerStateR());
                        }
                        break;
                    case ChargingHalId.ID_CHARGING_GUN_STATER /* 557855751 */:
                        Iterator<AbsBYDAutoChargingListener> it6 = this.mAbsBydAutoChargingListeners.iterator();
                        while (it6.hasNext()) {
                            it6.next().onChargingGunStateChanged(carBodyPacket.getChargingGunStateR());
                        }
                        caseFeatureIds(2520, Integer.valueOf(carBodyPacket.getChargingGunStateR()));
                        break;
                    default:
                        switch (i) {
                            case ChargingHalId.ID_CHARGING_SCHEDULE_ENABLE_STATER /* 557855754 */:
                                Iterator<AbsBYDAutoChargingListener> it7 = this.mAbsBydAutoChargingListeners.iterator();
                                while (it7.hasNext()) {
                                    it7.next().onChargingScheduleEnableStateChanged(carBodyPacket.getChargingScheduleEnableStateR());
                                }
                                break;
                            case ChargingHalId.ID_CHARGING_SCHEDULE_STATER /* 557855755 */:
                                Iterator<AbsBYDAutoChargingListener> it8 = this.mAbsBydAutoChargingListeners.iterator();
                                while (it8.hasNext()) {
                                    it8.next().onChargingScheduleStateChanged(carBodyPacket.getChargingScheduleStateR());
                                }
                                break;
                            case ChargingHalId.ID_CHARGING_SCHEDULE_TIME_YEARR /* 557855756 */:
                            case ChargingHalId.ID_CHARGING_SCHEDULE_TIME_MONTHR /* 557855757 */:
                            case ChargingHalId.ID_CHARGING_SCHEDULE_TIME_DAYR /* 557855758 */:
                            case ChargingHalId.ID_CHARGING_SCHEDULE_TIME_HOURR /* 557855759 */:
                            case ChargingHalId.ID_CHARGING_SCHEDULE_TIME_MINUTER /* 557855760 */:
                                Iterator<AbsBYDAutoChargingListener> it9 = this.mAbsBydAutoChargingListeners.iterator();
                                while (it9.hasNext()) {
                                    it9.next().onChargingScheduleTimeCountDownChanged(carBodyPacket.getChargingScheduleTimeHourR(), carBodyPacket.getChargingScheduleTimeMinuteR());
                                }
                                break;
                            default:
                                switch (i) {
                                    case ChargingHalId.ID_CHARGE_TOGGLE_GUARDR /* 557855762 */:
                                        Iterator<AbsBYDAutoChargingListener> it10 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it10.hasNext()) {
                                            it10.next().onChargeToggleGuardChanged(carBodyPacket.getChargeToggleGuardR());
                                        }
                                        break;
                                    case ChargingHalId.ID_MOVE_POWER_STATION_TOGGLER /* 557855764 */:
                                        Iterator<AbsBYDAutoChargingListener> it11 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it11.hasNext()) {
                                            it11.next().onMovePowerStationToggleChanged(carBodyPacket.getMovePowerStationToggleR());
                                        }
                                        break;
                                    case ChargingHalId.ID_DISCHARGE_TOGGLER /* 557855768 */:
                                        Iterator<AbsBYDAutoChargingListener> it12 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it12.hasNext()) {
                                            it12.next().onDischargeToggleChanged(carBodyPacket.getDischargeToggleR());
                                        }
                                        break;
                                    case ChargingHalId.ID_PRODUCE_POWER_WHEN_LOW_POWERR /* 557855774 */:
                                        Iterator<AbsBYDAutoChargingListener> it13 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it13.hasNext()) {
                                            it13.next().onPorducePowerWhenLowPowerChanged(carBodyPacket.getProducePowerWhenLowPowerR());
                                        }
                                        break;
                                    case ChargingHalId.ID_VTOV_DISCHARGE_LOWEST_VALR /* 557855793 */:
                                        Iterator<AbsBYDAutoChargingListener> it14 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it14.hasNext()) {
                                            it14.next().onVtovDischargeLowestValChanged(carBodyPacket.getVtovDischargeLowestValR());
                                        }
                                        break;
                                    case ChargingHalId.ID_CHARGER_WORK_FIX_STATER /* 557855798 */:
                                        Iterator<AbsBYDAutoChargingListener> it15 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it15.hasNext()) {
                                            it15.next().onChargerWorkStateChanged(carBodyPacket.getChargerWorkStateFixR());
                                        }
                                        break;
                                    case ChargingHalId.ID_CHARGE_STOP_CAPACITY_STATER /* 557855804 */:
                                        Iterator<AbsBYDAutoChargingListener> it16 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it16.hasNext()) {
                                            it16.next().onChargeStopCapacityStateChanged(carBodyPacket.getChargeStopCapacityStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_CHARGE_TOAGGLE_GUARD_MIXR /* 557855806 */:
                                        Iterator<AbsBYDAutoChargingListener> it17 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it17.hasNext()) {
                                            it17.next().onChargeToggleGuardChanged(carBodyPacket.getChargeToaggleGuardMixR());
                                        }
                                        break;
                                    case ChargingHalId.ID_VTOV_DISCHARGE_LIMIT_VALR /* 557855807 */:
                                        Iterator<AbsBYDAutoChargingListener> it18 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it18.hasNext()) {
                                            it18.next().onVtovDischargeLimitValChanged(carBodyPacket.getVtovDischargeLimitValR());
                                        }
                                        caseFeatureIds(2748, Integer.valueOf(carBodyPacket.getVtovDischargeLimitValR()));
                                        break;
                                    case ChargingHalId.ID_CHARGING_RESTTIME_HOURR /* 557855808 */:
                                    case ChargingHalId.ID_CHARGING_RESTTIME_MINUTER /* 557855809 */:
                                        int chargingResetTimeHourR = carBodyPacket.getChargingResetTimeHourR();
                                        int chargingResetTimeMinuteR = carBodyPacket.getChargingResetTimeMinuteR();
                                        Iterator<AbsBYDAutoChargingListener> it19 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it19.hasNext()) {
                                            it19.next().onChargingRestTimeChanged(chargingResetTimeHourR, chargingResetTimeMinuteR);
                                        }
                                        break;
                                    case ChargingHalId.ID_VTOV_DISCHARGE_CONNECT_STATE /* 557855810 */:
                                        Iterator<AbsBYDAutoChargingListener> it20 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it20.hasNext()) {
                                            it20.next().onVtovDischargeConnectStateChanged(carBodyPacket.getVtovDischargeConnectState());
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE1_R /* 557855811 */:
                                        byte[] bArr = {(byte) carBodyPacket.getObcWakeNoSenseCharge1R()};
                                        Iterator<AbsBYDAutoChargingListener> it21 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it21.hasNext()) {
                                            it21.next().onOBCWakeNoSenseChargeChanged(bArr);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE2_R /* 557855812 */:
                                        byte[] bArr2 = {(byte) carBodyPacket.getObcWakeNoSenseCharge2R()};
                                        Iterator<AbsBYDAutoChargingListener> it22 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it22.hasNext()) {
                                            it22.next().onOBCWakeNoSenseChargeChanged(bArr2);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE3_R /* 557855813 */:
                                        byte[] bArr3 = {(byte) carBodyPacket.getObcWakeNoSenseCharge3R()};
                                        Iterator<AbsBYDAutoChargingListener> it23 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it23.hasNext()) {
                                            it23.next().onOBCWakeNoSenseChargeChanged(bArr3);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE4_R /* 557855814 */:
                                        byte[] bArr4 = {(byte) carBodyPacket.getObcWakeNoSenseCharge4R()};
                                        Iterator<AbsBYDAutoChargingListener> it24 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it24.hasNext()) {
                                            it24.next().onOBCWakeNoSenseChargeChanged(bArr4);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE5_R /* 557855815 */:
                                        byte[] bArr5 = {(byte) carBodyPacket.getObcWakeNoSenseCharge5R()};
                                        Iterator<AbsBYDAutoChargingListener> it25 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it25.hasNext()) {
                                            it25.next().onOBCWakeNoSenseChargeChanged(bArr5);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE6_R /* 557855816 */:
                                        byte[] bArr6 = {(byte) carBodyPacket.getObcWakeNoSenseCharge6R()};
                                        Iterator<AbsBYDAutoChargingListener> it26 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it26.hasNext()) {
                                            it26.next().onOBCWakeNoSenseChargeChanged(bArr6);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE7_R /* 557855817 */:
                                        byte[] bArr7 = {(byte) carBodyPacket.getObcWakeNoSenseCharge7R()};
                                        Iterator<AbsBYDAutoChargingListener> it27 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it27.hasNext()) {
                                            it27.next().onOBCWakeNoSenseChargeChanged(bArr7);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE8_R /* 557855818 */:
                                        byte[] bArr8 = {(byte) carBodyPacket.getObcWakeNoSenseCharge8R()};
                                        Iterator<AbsBYDAutoChargingListener> it28 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it28.hasNext()) {
                                            it28.next().onOBCWakeNoSenseChargeChanged(bArr8);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE9_R /* 557855819 */:
                                        byte[] bArr9 = {(byte) carBodyPacket.getObcWakeNoSenseCharge9R()};
                                        Iterator<AbsBYDAutoChargingListener> it29 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it29.hasNext()) {
                                            it29.next().onOBCWakeNoSenseChargeChanged(bArr9);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE10_R /* 557855820 */:
                                        byte[] bArr10 = {(byte) carBodyPacket.getObcWakeNoSenseCharge10R()};
                                        Iterator<AbsBYDAutoChargingListener> it30 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it30.hasNext()) {
                                            it30.next().onOBCWakeNoSenseChargeChanged(bArr10);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE11_R /* 557855821 */:
                                        byte[] bArr11 = {(byte) carBodyPacket.getObcWakeNoSenseCharge11R()};
                                        Iterator<AbsBYDAutoChargingListener> it31 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it31.hasNext()) {
                                            it31.next().onOBCWakeNoSenseChargeChanged(bArr11);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE12_R /* 557855822 */:
                                        byte[] bArr12 = {(byte) carBodyPacket.getObcWakeNoSenseCharge12R()};
                                        Iterator<AbsBYDAutoChargingListener> it32 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it32.hasNext()) {
                                            it32.next().onOBCWakeNoSenseChargeChanged(bArr12);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE13_R /* 557855823 */:
                                        byte[] bArr13 = {(byte) carBodyPacket.getObcWakeNoSenseCharge13R()};
                                        Iterator<AbsBYDAutoChargingListener> it33 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it33.hasNext()) {
                                            it33.next().onOBCWakeNoSenseChargeChanged(bArr13);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE14_R /* 557855824 */:
                                        byte[] bArr14 = {(byte) carBodyPacket.getObcWakeNoSenseCharge14R()};
                                        Iterator<AbsBYDAutoChargingListener> it34 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it34.hasNext()) {
                                            it34.next().onOBCWakeNoSenseChargeChanged(bArr14);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE15_R /* 557855825 */:
                                        byte[] bArr15 = {(byte) carBodyPacket.getObcWakeNoSenseCharge15R()};
                                        Iterator<AbsBYDAutoChargingListener> it35 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it35.hasNext()) {
                                            it35.next().onOBCWakeNoSenseChargeChanged(bArr15);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE16_R /* 557855826 */:
                                        byte[] bArr16 = {(byte) carBodyPacket.getObcWakeNoSenseCharge16R()};
                                        Iterator<AbsBYDAutoChargingListener> it36 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it36.hasNext()) {
                                            it36.next().onOBCWakeNoSenseChargeChanged(bArr16);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE17_R /* 557855827 */:
                                        byte[] bArr17 = {(byte) carBodyPacket.getObcWakeNoSenseCharge17R()};
                                        Iterator<AbsBYDAutoChargingListener> it37 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it37.hasNext()) {
                                            it37.next().onOBCWakeNoSenseChargeChanged(bArr17);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE18_R /* 557855828 */:
                                        byte[] bArr18 = {(byte) carBodyPacket.getObcWakeNoSenseCharge18R()};
                                        Iterator<AbsBYDAutoChargingListener> it38 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it38.hasNext()) {
                                            it38.next().onOBCWakeNoSenseChargeChanged(bArr18);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE19_R /* 557855829 */:
                                        byte[] bArr19 = {(byte) carBodyPacket.getObcWakeNoSenseCharge19R()};
                                        Iterator<AbsBYDAutoChargingListener> it39 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it39.hasNext()) {
                                            it39.next().onOBCWakeNoSenseChargeChanged(bArr19);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE20_R /* 557855830 */:
                                        byte[] bArr20 = {(byte) carBodyPacket.getObcWakeNoSenseCharge20R()};
                                        Iterator<AbsBYDAutoChargingListener> it40 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it40.hasNext()) {
                                            it40.next().onOBCWakeNoSenseChargeChanged(bArr20);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE21_R /* 557855831 */:
                                        byte[] bArr21 = {(byte) carBodyPacket.getObcWakeNoSenseCharge21R()};
                                        Iterator<AbsBYDAutoChargingListener> it41 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it41.hasNext()) {
                                            it41.next().onOBCWakeNoSenseChargeChanged(bArr21);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE22_R /* 557855832 */:
                                        byte[] bArr22 = {(byte) carBodyPacket.getObcWakeNoSenseCharge22R()};
                                        Iterator<AbsBYDAutoChargingListener> it42 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it42.hasNext()) {
                                            it42.next().onOBCWakeNoSenseChargeChanged(bArr22);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE23_R /* 557855833 */:
                                        byte[] bArr23 = {(byte) carBodyPacket.getObcWakeNoSenseCharge23R()};
                                        Iterator<AbsBYDAutoChargingListener> it43 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it43.hasNext()) {
                                            it43.next().onOBCWakeNoSenseChargeChanged(bArr23);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE24_R /* 557855834 */:
                                        byte[] bArr24 = {(byte) carBodyPacket.getObcWakeNoSenseCharge24R()};
                                        Iterator<AbsBYDAutoChargingListener> it44 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it44.hasNext()) {
                                            it44.next().onOBCWakeNoSenseChargeChanged(bArr24);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE25_R /* 557855835 */:
                                        byte[] bArr25 = {(byte) carBodyPacket.getObcWakeNoSenseCharge25R()};
                                        Iterator<AbsBYDAutoChargingListener> it45 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it45.hasNext()) {
                                            it45.next().onOBCWakeNoSenseChargeChanged(bArr25);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE26_R /* 557855836 */:
                                        byte[] bArr26 = {(byte) carBodyPacket.getObcWakeNoSenseCharge26R()};
                                        Iterator<AbsBYDAutoChargingListener> it46 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it46.hasNext()) {
                                            it46.next().onOBCWakeNoSenseChargeChanged(bArr26);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE27_R /* 557855837 */:
                                        byte[] bArr27 = {(byte) carBodyPacket.getObcWakeNoSenseCharge27R()};
                                        Iterator<AbsBYDAutoChargingListener> it47 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it47.hasNext()) {
                                            it47.next().onOBCWakeNoSenseChargeChanged(bArr27);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE28_R /* 557855838 */:
                                        byte[] bArr28 = {(byte) carBodyPacket.getObcWakeNoSenseCharge28R()};
                                        Iterator<AbsBYDAutoChargingListener> it48 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it48.hasNext()) {
                                            it48.next().onOBCWakeNoSenseChargeChanged(bArr28);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE29_R /* 557855839 */:
                                        byte[] bArr29 = {(byte) carBodyPacket.getObcWakeNoSenseCharge29R()};
                                        Iterator<AbsBYDAutoChargingListener> it49 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it49.hasNext()) {
                                            it49.next().onOBCWakeNoSenseChargeChanged(bArr29);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE30_R /* 557855840 */:
                                        byte[] bArr30 = {(byte) carBodyPacket.getObcWakeNoSenseCharge30R()};
                                        Iterator<AbsBYDAutoChargingListener> it50 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it50.hasNext()) {
                                            it50.next().onOBCWakeNoSenseChargeChanged(bArr30);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE31_R /* 557855841 */:
                                        byte[] bArr31 = {(byte) carBodyPacket.getObcWakeNoSenseCharge31R()};
                                        Iterator<AbsBYDAutoChargingListener> it51 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it51.hasNext()) {
                                            it51.next().onOBCWakeNoSenseChargeChanged(bArr31);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE32_R /* 557855842 */:
                                        byte[] bArr32 = {(byte) carBodyPacket.getObcWakeNoSenseCharge32R()};
                                        Iterator<AbsBYDAutoChargingListener> it52 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it52.hasNext()) {
                                            it52.next().onOBCWakeNoSenseChargeChanged(bArr32);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE33_R /* 557855843 */:
                                        byte[] bArr33 = {(byte) carBodyPacket.getObcWakeNoSenseCharge33R()};
                                        Iterator<AbsBYDAutoChargingListener> it53 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it53.hasNext()) {
                                            it53.next().onOBCWakeNoSenseChargeChanged(bArr33);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE34_R /* 557855844 */:
                                        byte[] bArr34 = {(byte) carBodyPacket.getObcWakeNoSenseCharge34R()};
                                        Iterator<AbsBYDAutoChargingListener> it54 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it54.hasNext()) {
                                            it54.next().onOBCWakeNoSenseChargeChanged(bArr34);
                                        }
                                        break;
                                    case ChargingHalId.ID_OBC_WAKE_NO_SENSE_CHARGE35_R /* 557855845 */:
                                        byte[] bArr35 = {(byte) carBodyPacket.getObcWakeNoSenseCharge35R()};
                                        Iterator<AbsBYDAutoChargingListener> it55 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it55.hasNext()) {
                                            it55.next().onOBCWakeNoSenseChargeChanged(bArr35);
                                        }
                                        break;
                                    case ChargingHalId.ID_SITU_POWER_GENERATION_STATE_R /* 557856266 */:
                                        Iterator<AbsBYDAutoChargingListener> it56 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it56.hasNext()) {
                                            it56.next().onSituPowerGenerationStateChanged(carBodyPacket.getSituPowerGenerationStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_NO_SENSE_CHARGE_STATE_R /* 557856276 */:
                                        Iterator<AbsBYDAutoChargingListener> it57 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it57.hasNext()) {
                                            it57.next().onNoSenseChargeStateChanged(carBodyPacket.getNoSenseChargeStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_WIRELESS_CHARGING_F_R_SWITCH_STATE_R /* 557856326 */:
                                        Iterator<AbsBYDAutoChargingListener> it58 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it58.hasNext()) {
                                            it58.next().onWirelessChargingFRSwitchStateChanged(carBodyPacket.getWirelessChargingFrSwitchStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_WIRELESS_CHARGING_F_R_STATE_R /* 557856327 */:
                                        Iterator<AbsBYDAutoChargingListener> it59 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it59.hasNext()) {
                                            it59.next().onWirelessChargingFRStateChanged(carBodyPacket.getWirelessChargingFrStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_WIRELESS_CHARGING_POWER_F_L_STATE_R /* 557856328 */:
                                        Iterator<AbsBYDAutoChargingListener> it60 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it60.hasNext()) {
                                            it60.next().onWirelessChargingPowerFLStateChanged(carBodyPacket.getWirelessChargingPowerFlStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_WIRELESS_CHARGING_POWER_F_R_STATE_R /* 557856329 */:
                                        Iterator<AbsBYDAutoChargingListener> it61 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it61.hasNext()) {
                                            it61.next().onWirelessChargingPowerFRStateChanged(carBodyPacket.getWirelessChargingPowerFrStateR());
                                        }
                                        break;
                                    case ChargingHalId.ID_PORT_CAP_STATE_RIGHT_R /* 557856330 */:
                                        Iterator<AbsBYDAutoChargingListener> it62 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it62.hasNext()) {
                                            it62.next().onPortCapStateChanged(2, carBodyPacket.getgetPortCapStateRightR());
                                        }
                                        caseFeatureIds(2628, Integer.valueOf(carBodyPacket.getgetPortCapStateRightR()));
                                        break;
                                    case ChargingHalId.ID_PORT_CAP_STATE_LEFT_R /* 557856331 */:
                                        Iterator<AbsBYDAutoChargingListener> it63 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it63.hasNext()) {
                                            it63.next().onPortCapStateChanged(1, carBodyPacket.getgetPortCapStateLeftR());
                                        }
                                        caseFeatureIds(2596, Integer.valueOf(carBodyPacket.getgetPortCapStateLeftR()));
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_START_TIME_YEAR_R /* 557856332 */:
                                        this.mHevChargePlanStartTimeYearR = carBodyPacket.getHevChargePlanStartTimeYearR();
                                        Iterator<AbsBYDAutoChargingListener> it64 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it64.hasNext()) {
                                            it64.next().onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_START_TIME_MONTH_R /* 557856333 */:
                                        this.mHevChargePlanStartTimeMonthR = carBodyPacket.getHevChargePlanStartTimeMonthR();
                                        Iterator<AbsBYDAutoChargingListener> it65 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it65.hasNext()) {
                                            it65.next().onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_START_TIME_DAY_R /* 557856334 */:
                                        this.mHevChargePlanStartTimeYearDayR = carBodyPacket.getHevChargePlanStartTimeYearDayR();
                                        Iterator<AbsBYDAutoChargingListener> it66 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it66.hasNext()) {
                                            it66.next().onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_START_TIME_HOUR_R /* 557856335 */:
                                        this.mHevChargePlanStartTimeYearHourR = carBodyPacket.getHevChargePlanStartTimeYearHourR();
                                        Iterator<AbsBYDAutoChargingListener> it67 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it67.hasNext()) {
                                            it67.next().onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_START_TIME_MINUTE_R /* 557856336 */:
                                        this.mHevChargePlanStartTimeYearMinuteR = carBodyPacket.getHevChargePlanStartTimeYearMinuteR();
                                        Iterator<AbsBYDAutoChargingListener> it68 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it68.hasNext()) {
                                            it68.next().onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R /* 557856337 */:
                                        this.mHevChargePlaneEndTimeYearR = carBodyPacket.getHevChargePlaneEndTimeYearR();
                                        Iterator<AbsBYDAutoChargingListener> it69 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it69.hasNext()) {
                                            it69.next().onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R /* 557856338 */:
                                        this.mHevChargePlanEndTimeMonthR = carBodyPacket.getHevChargePlanEndTimeMonthR();
                                        Iterator<AbsBYDAutoChargingListener> it70 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it70.hasNext()) {
                                            it70.next().onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_END_TIME_DAY_R /* 557856339 */:
                                        this.mHevChargePlanEndTimeYearDayR = carBodyPacket.getHevChargePlanEndTimeYearDayR();
                                        Iterator<AbsBYDAutoChargingListener> it71 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it71.hasNext()) {
                                            it71.next().onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R /* 557856340 */:
                                        this.mHevChargePlanEndTimeYearHourR = carBodyPacket.getHevChargePlanEndTimeYearHourR();
                                        Iterator<AbsBYDAutoChargingListener> it72 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it72.hasNext()) {
                                            it72.next().onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R /* 557856341 */:
                                        this.mHevChargePlanEndTimeYearMinuteR = carBodyPacket.getHevChargePlanEndTimeYearMinuteR();
                                        Iterator<AbsBYDAutoChargingListener> it73 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it73.hasNext()) {
                                            it73.next().onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case ChargingHalId.ID_CHARGING_POWERR /* 559952904 */:
                                        Iterator<AbsBYDAutoChargingListener> it74 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it74.hasNext()) {
                                            it74.next().onChargingPowerChanged(carBodyPacket.getChargingPowerR());
                                        }
                                        break;
                                    case ChargingHalId.ID_VTOV_DISCHARGE_QUANTITYR /* 559952946 */:
                                        Iterator<AbsBYDAutoChargingListener> it75 = this.mAbsBydAutoChargingListeners.iterator();
                                        while (it75.hasNext()) {
                                            it75.next().onVtovDischargeQuantityChanged(carBodyPacket.getVtovDischargeQuantityR());
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case ChargingHalId.ID_DISCHARGE_TIME_HOURR /* 557855771 */:
                                            case ChargingHalId.ID_DISCHARGE_TIME_MINUTER /* 557855772 */:
                                                int dischargeTimeHourR = carBodyPacket.getDischargeTimeHourR();
                                                int dischargeTimeMinuteR = carBodyPacket.getDischargeTimeMinuteR();
                                                int[] iArr = this.mDisChargeTime;
                                                iArr[0] = dischargeTimeHourR;
                                                iArr[1] = dischargeTimeMinuteR;
                                                Iterator<AbsBYDAutoChargingListener> it76 = this.mAbsBydAutoChargingListeners.iterator();
                                                while (it76.hasNext()) {
                                                    it76.next().onDischargeTimeChanged(hourMinArray2MillionsSec(this.mDisChargeTime));
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case ChargingHalId.ID_WIRELESS_CHARGING_SWITCH_STATER /* 557855776 */:
                                                        Iterator<AbsBYDAutoChargingListener> it77 = this.mAbsBydAutoChargingListeners.iterator();
                                                        while (it77.hasNext()) {
                                                            it77.next().onWirelessChargingSwitchStateChanged(carBodyPacket.getWirelessChargingSwitchStateR());
                                                        }
                                                        break;
                                                    case ChargingHalId.ID_WIRELESS_CHARGING_STATER /* 557855777 */:
                                                        Iterator<AbsBYDAutoChargingListener> it78 = this.mAbsBydAutoChargingListeners.iterator();
                                                        while (it78.hasNext()) {
                                                            it78.next().onWirelessChargingStateChanged(carBodyPacket.getWirelessChargingStateR());
                                                        }
                                                        break;
                                                    case ChargingHalId.ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR /* 557855778 */:
                                                        Iterator<AbsBYDAutoChargingListener> it79 = this.mAbsBydAutoChargingListeners.iterator();
                                                        while (it79.hasNext()) {
                                                            it79.next().onVtovChargeBatteryLevelNumChanged(carBodyPacket.getVtovChargeBatteryLevelNumR());
                                                        }
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case ChargingHalId.ID_CHARGING_CAP_STATE_LEFTR /* 557855800 */:
                                                                Iterator<AbsBYDAutoChargingListener> it80 = this.mAbsBydAutoChargingListeners.iterator();
                                                                while (it80.hasNext()) {
                                                                    it80.next().onChargingCapStateChanged(3, carBodyPacket.getChargingCapStateLeftR());
                                                                }
                                                                break;
                                                            case ChargingHalId.ID_CHARGING_CAP_STATE_RIGHTR /* 557855801 */:
                                                                Iterator<AbsBYDAutoChargingListener> it81 = this.mAbsBydAutoChargingListeners.iterator();
                                                                while (it81.hasNext()) {
                                                                    it81.next().onChargingCapStateChanged(4, carBodyPacket.getChargingCapStateRightR());
                                                                }
                                                                break;
                                                            case ChargingHalId.ID_CHARGE_TEMP_CTL_STATER /* 557855802 */:
                                                                Iterator<AbsBYDAutoChargingListener> it82 = this.mAbsBydAutoChargingListeners.iterator();
                                                                while (it82.hasNext()) {
                                                                    it82.next().onChargeTempCtlStateChanged(carBodyPacket.getChargeTempCtlStateR());
                                                                }
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case ChargingHalId.ID_SITU_POWER_GENERATION_SUPPORT_R /* 557856268 */:
                                                                        Iterator<AbsBYDAutoChargingListener> it83 = this.mAbsBydAutoChargingListeners.iterator();
                                                                        while (it83.hasNext()) {
                                                                            it83.next().onSituPowerGenerationSupportChanged(carBodyPacket.getSituPowerGenerationSupportR());
                                                                        }
                                                                        break;
                                                                    case ChargingHalId.ID_CHARGE_PREHEAT_STATE_R /* 557856269 */:
                                                                        Iterator<AbsBYDAutoChargingListener> it84 = this.mAbsBydAutoChargingListeners.iterator();
                                                                        while (it84.hasNext()) {
                                                                            it84.next().onChargePreheatStateChanged(carBodyPacket.getChargePreheatStateR());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case ChargingHalId.ID_CAR_PLAN_STATE_R /* 557856272 */:
                                                                                Iterator<AbsBYDAutoChargingListener> it85 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                while (it85.hasNext()) {
                                                                                    it85.next().onCarPlanStateChanged(carBodyPacket.getCarPlanStateR());
                                                                                }
                                                                                break;
                                                                            case ChargingHalId.ID_CAR_PLAN_ACC_ON_STATE_R /* 557856273 */:
                                                                                Iterator<AbsBYDAutoChargingListener> it86 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                while (it86.hasNext()) {
                                                                                    it86.next().onCarPlanAccOnStateChanged(carBodyPacket.getCarPlanAccOnStateR());
                                                                                }
                                                                                break;
                                                                            case ChargingHalId.ID_CAR_PLAN_BATTERY_HEAT_STATE_R /* 557856274 */:
                                                                                Iterator<AbsBYDAutoChargingListener> it87 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                while (it87.hasNext()) {
                                                                                    it87.next().onCarPlanBatteryHeatStateChanged(carBodyPacket.getCarPlanBatteryHeatStateR());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE1_R /* 557856288 */:
                                                                                        byte[] bArr36 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge1R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it88 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it88.hasNext()) {
                                                                                            it88.next().onBMSWakeNoSenseChargeChanged(bArr36);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE2_R /* 557856289 */:
                                                                                        byte[] bArr37 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge2R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it89 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it89.hasNext()) {
                                                                                            it89.next().onBMSWakeNoSenseChargeChanged(bArr37);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE3_R /* 557856290 */:
                                                                                        byte[] bArr38 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge3R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it90 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it90.hasNext()) {
                                                                                            it90.next().onBMSWakeNoSenseChargeChanged(bArr38);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE4_R /* 557856291 */:
                                                                                        byte[] bArr39 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge4R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it91 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it91.hasNext()) {
                                                                                            it91.next().onBMSWakeNoSenseChargeChanged(bArr39);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE5_R /* 557856292 */:
                                                                                        byte[] bArr40 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge5R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it92 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it92.hasNext()) {
                                                                                            it92.next().onBMSWakeNoSenseChargeChanged(bArr40);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE6_R /* 557856293 */:
                                                                                        byte[] bArr41 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge6R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it93 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it93.hasNext()) {
                                                                                            it93.next().onBMSWakeNoSenseChargeChanged(bArr41);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE7_R /* 557856294 */:
                                                                                        byte[] bArr42 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge7R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it94 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it94.hasNext()) {
                                                                                            it94.next().onBMSWakeNoSenseChargeChanged(bArr42);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE8_R /* 557856295 */:
                                                                                        byte[] bArr43 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge8R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it95 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it95.hasNext()) {
                                                                                            it95.next().onBMSWakeNoSenseChargeChanged(bArr43);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE9_R /* 557856296 */:
                                                                                        byte[] bArr44 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge9R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it96 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it96.hasNext()) {
                                                                                            it96.next().onBMSWakeNoSenseChargeChanged(bArr44);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE10_R /* 557856297 */:
                                                                                        byte[] bArr45 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge10R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it97 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it97.hasNext()) {
                                                                                            it97.next().onBMSWakeNoSenseChargeChanged(bArr45);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE11_R /* 557856298 */:
                                                                                        byte[] bArr46 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge11R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it98 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it98.hasNext()) {
                                                                                            it98.next().onBMSWakeNoSenseChargeChanged(bArr46);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE12_R /* 557856299 */:
                                                                                        byte[] bArr47 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge12R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it99 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it99.hasNext()) {
                                                                                            it99.next().onBMSWakeNoSenseChargeChanged(bArr47);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE13_R /* 557856300 */:
                                                                                        byte[] bArr48 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge13R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it100 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it100.hasNext()) {
                                                                                            it100.next().onBMSWakeNoSenseChargeChanged(bArr48);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE14_R /* 557856301 */:
                                                                                        byte[] bArr49 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge14R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it101 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it101.hasNext()) {
                                                                                            it101.next().onBMSWakeNoSenseChargeChanged(bArr49);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE15_R /* 557856302 */:
                                                                                        byte[] bArr50 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge15R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it102 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it102.hasNext()) {
                                                                                            it102.next().onBMSWakeNoSenseChargeChanged(bArr50);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE16_R /* 557856303 */:
                                                                                        byte[] bArr51 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge16R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it103 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it103.hasNext()) {
                                                                                            it103.next().onBMSWakeNoSenseChargeChanged(bArr51);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE17_R /* 557856304 */:
                                                                                        byte[] bArr52 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge17R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it104 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it104.hasNext()) {
                                                                                            it104.next().onBMSWakeNoSenseChargeChanged(bArr52);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE18_R /* 557856305 */:
                                                                                        byte[] bArr53 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge18R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it105 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it105.hasNext()) {
                                                                                            it105.next().onBMSWakeNoSenseChargeChanged(bArr53);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE19_R /* 557856306 */:
                                                                                        byte[] bArr54 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge19R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it106 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it106.hasNext()) {
                                                                                            it106.next().onBMSWakeNoSenseChargeChanged(bArr54);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE20_R /* 557856307 */:
                                                                                        byte[] bArr55 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge20R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it107 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it107.hasNext()) {
                                                                                            it107.next().onBMSWakeNoSenseChargeChanged(bArr55);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE21_R /* 557856308 */:
                                                                                        byte[] bArr56 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge21R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it108 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it108.hasNext()) {
                                                                                            it108.next().onBMSWakeNoSenseChargeChanged(bArr56);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE22_R /* 557856309 */:
                                                                                        byte[] bArr57 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge22R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it109 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it109.hasNext()) {
                                                                                            it109.next().onBMSWakeNoSenseChargeChanged(bArr57);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE23_R /* 557856310 */:
                                                                                        byte[] bArr58 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge23R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it110 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it110.hasNext()) {
                                                                                            it110.next().onBMSWakeNoSenseChargeChanged(bArr58);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE24_R /* 557856311 */:
                                                                                        byte[] bArr59 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge24R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it111 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it111.hasNext()) {
                                                                                            it111.next().onBMSWakeNoSenseChargeChanged(bArr59);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE25_R /* 557856312 */:
                                                                                        byte[] bArr60 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge25R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it112 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it112.hasNext()) {
                                                                                            it112.next().onBMSWakeNoSenseChargeChanged(bArr60);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE26_R /* 557856313 */:
                                                                                        byte[] bArr61 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge26R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it113 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it113.hasNext()) {
                                                                                            it113.next().onBMSWakeNoSenseChargeChanged(bArr61);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE27_R /* 557856314 */:
                                                                                        byte[] bArr62 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge27R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it114 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it114.hasNext()) {
                                                                                            it114.next().onBMSWakeNoSenseChargeChanged(bArr62);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE28_R /* 557856315 */:
                                                                                        byte[] bArr63 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge28R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it115 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it115.hasNext()) {
                                                                                            it115.next().onBMSWakeNoSenseChargeChanged(bArr63);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE29_R /* 557856316 */:
                                                                                        byte[] bArr64 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge29R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it116 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it116.hasNext()) {
                                                                                            it116.next().onBMSWakeNoSenseChargeChanged(bArr64);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE30_R /* 557856317 */:
                                                                                        byte[] bArr65 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge30R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it117 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it117.hasNext()) {
                                                                                            it117.next().onBMSWakeNoSenseChargeChanged(bArr65);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE31_R /* 557856318 */:
                                                                                        byte[] bArr66 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge31R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it118 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it118.hasNext()) {
                                                                                            it118.next().onBMSWakeNoSenseChargeChanged(bArr66);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE32_R /* 557856319 */:
                                                                                        byte[] bArr67 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge32R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it119 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it119.hasNext()) {
                                                                                            it119.next().onBMSWakeNoSenseChargeChanged(bArr67);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE33_R /* 557856320 */:
                                                                                        byte[] bArr68 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge33R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it120 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it120.hasNext()) {
                                                                                            it120.next().onBMSWakeNoSenseChargeChanged(bArr68);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE34_R /* 557856321 */:
                                                                                        byte[] bArr69 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge34R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it121 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it121.hasNext()) {
                                                                                            it121.next().onBMSWakeNoSenseChargeChanged(bArr69);
                                                                                        }
                                                                                        break;
                                                                                    case ChargingHalId.ID_BMS_WAKE_SENSE_CHARGE35_R /* 557856322 */:
                                                                                        byte[] bArr70 = {(byte) carBodyPacket.getBmsWakeNoSenseCharge35R()};
                                                                                        Iterator<AbsBYDAutoChargingListener> it122 = this.mAbsBydAutoChargingListeners.iterator();
                                                                                        while (it122.hasNext()) {
                                                                                            it122.next().onBMSWakeNoSenseChargeChanged(bArr70);
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

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static ChargingManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ChargingManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new ChargingManagerImpl(context);
                }
            }
        }
        return mInstance;
    }

    private long hourMinArray2MillionsSec(int[] iArr) {
        return ((long) (iArr[0] * 60 * 60 * 1000)) + ((long) (iArr[1] * 60 * 1000));
    }

    private int[] millionsSec2HourMinArray(long j) {
        int i = (int) ((j / 1000) / 60);
        return new int[]{i / 60, i % 60};
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
                        case 2019:
                            int carPlanBatteryHeatState = getCarPlanBatteryHeatState();
                            bYDAutoEventValue.intValue = carPlanBatteryHeatState;
                            sparseArray.put(2019, Integer.valueOf(carPlanBatteryHeatState));
                            break;
                        case 2021:
                            int carPlanAccOnState = getCarPlanAccOnState();
                            bYDAutoEventValue.intValue = carPlanAccOnState;
                            sparseArray.put(2021, Integer.valueOf(carPlanAccOnState));
                            break;
                        case 2022:
                            int carPlanState = getCarPlanState();
                            bYDAutoEventValue.intValue = carPlanState;
                            sparseArray.put(2022, Integer.valueOf(carPlanState));
                            break;
                        case 2516:
                            int batteryManagementDeviceState = getBatteryManagementDeviceState();
                            bYDAutoEventValue.intValue = batteryManagementDeviceState;
                            sparseArray.put(2516, Integer.valueOf(batteryManagementDeviceState));
                            break;
                        case 2519:
                            int chargingGunState = this.mTsManagerImpl.getChargingAdapterManager().getChargingGunState();
                            bYDAutoEventValue.intValue = chargingGunState;
                            sparseArray.put(2519, Integer.valueOf(chargingGunState));
                            break;
                        case 2535:
                            int chargerState = getChargerState();
                            bYDAutoEventValue.intValue = chargerState;
                            sparseArray.put(2535, Integer.valueOf(chargerState));
                            break;
                        case 2536:
                            int chargerFaultState = getChargerFaultState();
                            bYDAutoEventValue.intValue = chargerFaultState;
                            sparseArray.put(2536, Integer.valueOf(chargerFaultState));
                            break;
                        case 2547:
                            float chargingPower = getChargingPower();
                            bYDAutoEventValue.floatValue = chargingPower;
                            sparseArray.put(2547, Float.valueOf(chargingPower));
                            break;
                        case 2554:
                            int chargeTempCtlState = getChargeTempCtlState();
                            bYDAutoEventValue.intValue = chargeTempCtlState;
                            sparseArray.put(2554, Integer.valueOf(chargeTempCtlState));
                            break;
                        case 2565:
                            int wirelessChargingState = getWirelessChargingState();
                            bYDAutoEventValue.intValue = wirelessChargingState;
                            sparseArray.put(2565, Integer.valueOf(wirelessChargingState));
                            break;
                        case 2566:
                            int wirelessChargingSwitchState = getWirelessChargingSwitchState();
                            bYDAutoEventValue.intValue = wirelessChargingSwitchState;
                            sparseArray.put(2566, Integer.valueOf(wirelessChargingSwitchState));
                            break;
                        case 2571:
                            int dischargeRequestState = getDischargeRequestState();
                            bYDAutoEventValue.intValue = dischargeRequestState;
                            sparseArray.put(2571, Integer.valueOf(dischargeRequestState));
                            break;
                        case 2576:
                            int movePowerStationToggle = getMovePowerStationToggle();
                            bYDAutoEventValue.intValue = movePowerStationToggle;
                            sparseArray.put(2576, Integer.valueOf(movePowerStationToggle));
                            break;
                        case 2578:
                            int vtovDischargeLowPowerHint = getVtovDischargeLowPowerHint();
                            bYDAutoEventValue.intValue = vtovDischargeLowPowerHint;
                            sparseArray.put(2578, Integer.valueOf(vtovDischargeLowPowerHint));
                            break;
                        case 2584:
                            int[] chargingRestTime = getChargingRestTime();
                            if (chargingRestTime.length == 2) {
                                bYDAutoEventValue.intValue = chargingRestTime[0];
                            }
                            sparseArray.put(2584, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2585:
                            int[] chargingRestTime2 = getChargingRestTime();
                            if (chargingRestTime2.length == 2) {
                                bYDAutoEventValue.intValue = chargingRestTime2[1];
                            }
                            sparseArray.put(2585, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2588:
                            int chargingGunState2 = this.mTsManagerImpl.getChargingAdapterManager().getChargingGunState();
                            bYDAutoEventValue.intValue = chargingGunState2;
                            sparseArray.put(2588, Integer.valueOf(chargingGunState2));
                            break;
                        case 2592:
                            int situPowerGenerationSupport = getSituPowerGenerationSupport();
                            bYDAutoEventValue.intValue = situPowerGenerationSupport;
                            sparseArray.put(2592, Integer.valueOf(situPowerGenerationSupport));
                            break;
                        case 2594:
                            int situPowerGenerationState = getSituPowerGenerationState();
                            bYDAutoEventValue.intValue = situPowerGenerationState;
                            sparseArray.put(2594, Integer.valueOf(situPowerGenerationState));
                            break;
                        case 2596:
                            int chargingCapState = getChargingCapState(3);
                            bYDAutoEventValue.intValue = chargingCapState;
                            sparseArray.put(2596, Integer.valueOf(chargingCapState));
                            break;
                        case 2601:
                            int porducePowerWhenLowPower = getPorducePowerWhenLowPower();
                            bYDAutoEventValue.intValue = porducePowerWhenLowPower;
                            sparseArray.put(2601, Integer.valueOf(porducePowerWhenLowPower));
                            break;
                        case 2619:
                            int noSenseChargeState = getNoSenseChargeState();
                            bYDAutoEventValue.intValue = noSenseChargeState;
                            sparseArray.put(2619, Integer.valueOf(noSenseChargeState));
                            break;
                        case 2628:
                            int chargingCapState2 = getChargingCapState(4);
                            bYDAutoEventValue.intValue = chargingCapState2;
                            sparseArray.put(2628, Integer.valueOf(chargingCapState2));
                            break;
                        case 2634:
                            int chargingScheduleEnableState = getChargingScheduleEnableState();
                            bYDAutoEventValue.intValue = chargingScheduleEnableState;
                            sparseArray.put(2634, Integer.valueOf(chargingScheduleEnableState));
                            break;
                        case 2669:
                            int chargeStopCapacityState = getChargeStopCapacityState();
                            bYDAutoEventValue.intValue = chargeStopCapacityState;
                            sparseArray.put(2669, Integer.valueOf(chargeStopCapacityState));
                            break;
                        case 2733:
                            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                                int[] dischargeTime = this.mTsManagerImpl.getChargingAdapterManager().getDischargeTime();
                                if (dischargeTime.length == 2) {
                                    bYDAutoEventValue.intValue = dischargeTime[0];
                                }
                            }
                            sparseArray.put(2733, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2735:
                            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                                int[] dischargeTime2 = this.mTsManagerImpl.getChargingAdapterManager().getDischargeTime();
                                if (dischargeTime2.length == 2) {
                                    bYDAutoEventValue.intValue = dischargeTime2[1];
                                }
                            }
                            sparseArray.put(2735, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2738:
                            int dischargeToggle = getDischargeToggle();
                            bYDAutoEventValue.intValue = dischargeToggle;
                            sparseArray.put(2738, Integer.valueOf(dischargeToggle));
                            break;
                        case 2740:
                            int vtovDischargeConnectState = getVtovDischargeConnectState();
                            bYDAutoEventValue.intValue = vtovDischargeConnectState;
                            sparseArray.put(2740, Integer.valueOf(vtovDischargeConnectState));
                            break;
                        case 2742:
                            float vtovDischargeQuantity = getVtovDischargeQuantity();
                            bYDAutoEventValue.floatValue = vtovDischargeQuantity;
                            sparseArray.put(2742, Float.valueOf(vtovDischargeQuantity));
                            break;
                        case 2748:
                            int vtovDischargeLimitVal = getVtovDischargeLimitVal();
                            bYDAutoEventValue.intValue = vtovDischargeLimitVal;
                            sparseArray.put(2748, Integer.valueOf(vtovDischargeLimitVal));
                            break;
                        case 2749:
                            int vtovDischargeLowestVal = getVtovDischargeLowestVal();
                            bYDAutoEventValue.intValue = vtovDischargeLowestVal;
                            sparseArray.put(2749, Integer.valueOf(vtovDischargeLowestVal));
                            break;
                        default:
                            Log.d(TAG, "case fail ! please check out");
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getBatteryManagementDeviceState() {
        Log.d(TAG, "getBatteryManagementDeviceState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getBatteryManagementDeviceState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getBatteryManagementDeviceState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getBatteryType() {
        Log.d(TAG, "getBatteryType is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getBatteryType();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getBatteryType mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public byte[] getBmsWakeNoSenseCharge() {
        Log.d(TAG, "getBMSWakeNoSenseCharge is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getBmsWakeNoSenseCharge();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        Log.d(TAG, "getBMSWakeNoSenseCharge mForTestValue is " + this.mForTestValue + " execute return");
        return new byte[0];
    }

    public int getCarPlanAccOnState() {
        Log.d(TAG, "getCarPlanAccOnState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getCarPlanAccOnState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getCarPlanAccOnState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getCarPlanBatteryHeatState() {
        Log.d(TAG, "getCarPlanBatteryHeatState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getCarPlanBatteryHeatState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getCarPlanBatteryHeatState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getCarPlanState() {
        Log.d(TAG, "getCarPlanState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getCarPlanState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getCarPlanState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeBatteryLevelBegin() {
        Log.d(TAG, "getChargeBatteryLevelBegin is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeBatteryLevelBegin();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getChargeBatteryLevelBegin mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeBatteryLimitTemp() {
        Log.d(TAG, "getChargeBatteryLimitTemp is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeBatteryLimitTemp();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getChargeBatteryLimitTemp mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeBatteryTemp() {
        Log.d(TAG, "getChargeBatteryTemp is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeBatteryTemp();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getChargeBatteryTemp mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public float getChargeElectric() {
        Log.d(TAG, "getChargeElectric is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeElectric();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.d(TAG, "getChargeElectric mForTestValue is" + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public float getChargeLimit() {
        Log.d(TAG, "getChargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeLimit();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        Log.d(TAG, "getChargeLimit mForTestValue is " + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int getChargePreheatAllow() {
        Log.d(TAG, "getChargePreheatAllow is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargePreheatAllow();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargePreheatAllow mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargePreheatState() {
        Log.d(TAG, "getChargePreheatState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargePreheatState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargePreheatState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeStopCapacityState() {
        Log.d(TAG, "getChargeStopCapacityState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeStopCapacityState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getChargeStopCapacityState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeTempCtlState() {
        Log.d(TAG, "getChargeTempCtlState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeTempCtlState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getChargeTempCtlState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeToggleGuard() {
        Log.d(TAG, "getChargeToaggleGuard is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeToggleGuard();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargeToaggleGuard mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargeVoltage() {
        Log.d(TAG, "getChargeVoltage is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeVoltage();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getChargeVoltage mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public float getChargeVoltageMax() {
        Log.d(TAG, "getChargeVoltageMax is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargeVoltageMax();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.d(TAG, "getChargeVoltageMax mForTestValue is" + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int getChargerFaultState() {
        Log.d(TAG, "getChargerFaultState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargerFaultState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargerFaultState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargerState() {
        Log.d(TAG, "getChargerState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargerState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargerState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargerWorkState() {
        Log.d(TAG, "getChargerWorkState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargerWorkState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargerWorkState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargingCapState(int i) {
        Log.d(TAG, "getChargingCapState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.d(TAG, "getChargingCapState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 3) {
            return this.mTsManagerImpl.getChargingAdapterManager().getChargingCapStateLeft();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingCapStateRight();
    }

    public float getChargingCapacity() {
        Log.d(TAG, "getChargingCapacity is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingCapacity();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        Log.d(TAG, "getChargingCapacity mForTestValue is " + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int getChargingGunNotInsertedState() {
        Log.d(TAG, "getChargingGunNotInsertedState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingGunNotInsertedState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargingGunNotInsertedState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargingGunState() {
        Log.d(TAG, "getChargingGunState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingGunState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargingGunState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargingPortLockRebackState() {
        Log.d(TAG, "getChargingPortLockRebackState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingPortLockRebackState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargingPortLockRebackState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public float getChargingPower() {
        Log.d(TAG, "getChargingPower is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingPower();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        Log.d(TAG, "getChargingPower mForTestValue is " + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int[] getChargingRestTime() {
        Log.d(TAG, "getChargingRestTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingRestTime();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return new int[0];
        }
        Log.d(TAG, "getChargingRestTime mForTestValue is " + this.mForTestValue + " execute return");
        return new int[0];
    }

    public int getChargingScheduleEnableState() {
        Log.d(TAG, "getChargingScheduleEnableState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingScheduleEnableState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargingScheduleEnableState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getChargingScheduleState() {
        Log.d(TAG, "getChargingScheduleState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingScheduleState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargingScheduleState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int[] getChargingScheduleTime() {
        Log.d(TAG, "getChargingScheduleTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingScheduleTime();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return new int[0];
        }
        Log.d(TAG, "getChargingScheduleTime mForTestValue is " + this.mForTestValue + " execute return");
        return new int[0];
    }

    public int getChargingType() {
        Log.d(TAG, "getChargingType is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getChargingType();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getChargingType mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public float getDischargeElectric() {
        Log.d(TAG, "getDischargeElectric is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeElectric();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.d(TAG, "getDischargeElectric mForTestValue is" + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int getDischargeGunTimer() {
        Log.d(TAG, "getDischargeGunTimer is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeGunTimer();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDischargeGunTimer mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getDischargeLimit() {
        Log.d(TAG, "getDischargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeLimit();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getDischargeLimit mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getDischargeOverReason() {
        Log.d(TAG, "getDischargeOverReason is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeOverReason();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDischargeOverReason mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getDischargeRemainTime() {
        Log.d(TAG, "getDischargeRemainTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeRemainTime();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDischargeRemainTime mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getDischargeRequestState() {
        Log.d(TAG, "getDischargeRequestState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeRequestState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getDischargeRequestState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public long getDischargeTime() {
        Log.d(TAG, "getDischargeTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return hourMinArray2MillionsSec(this.mTsManagerImpl.getChargingAdapterManager().getDischargeTime());
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0L;
        }
        Log.d(TAG, "getDischargeTime mForTestValue is " + this.mForTestValue + " execute return");
        return 0L;
    }

    public int getDischargeToggle() {
        Log.d(TAG, "getDischargeToggle is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeToggle();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getDischargeToggle mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getDischargeTotalTime() {
        Log.d(TAG, "getDischargeTotalTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeTotalTime();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDischargeTotalTime mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getDischargeVoltage() {
        Log.d(TAG, "getDischargeVoltage is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getDischargeVoltage();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getDischargeVoltage mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getFullChgRemanTimH() {
        Log.d(TAG, "getFullChgRemanTimH is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getFullChgRemanTimH();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getFullChgRemanTimH mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getFullChgRemanTimM() {
        Log.d(TAG, "getFullChgRemanTimM is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getFullChgRemanTimM();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getFullChgRemanTimM mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int[] getHevChargePlanEndTime() {
        Log.i(TAG, "getHevChargePlanEndTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
            return new int[]{this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlaneEndTimeYearR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanEndTimeMonthR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanEndTimeYearDayR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanEndTimeYearHourR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanEndTimeYearMinuteR()};
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new int[0];
    }

    public int[] getHevChargePlanStartTime() {
        Log.i(TAG, "getHevChargePlanStartTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
            return new int[]{this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeMonthR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearDayR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearHourR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearMinuteR()};
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new int[0];
    }

    public int getHevChargingType() {
        Log.d(TAG, "getHEVChargingType is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getHevChargingType();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getHEVChargingType mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getIntelligentKeepTemMode() {
        Log.d(TAG, "getIntelligentKeepTemMode is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getIntelligentKeepTemMode();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getIntelligentKeepTemMode mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getMovePowerStationToggle() {
        Log.d(TAG, "getMovePowerStationToggle is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getMovePowerStationToggle();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getMovePowerStationToggle mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getNoSenseChargeState() {
        Log.d(TAG, "getNoSenseChargeState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getNoSenseChargeState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getNoSenseChargeState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public byte[] getObcWakeNoSenseCharge() {
        Log.d(TAG, "getOBCWakeNoSenseCharge is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getObcWakeNoSenseCharge();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        Log.d(TAG, "getOBCWakeNoSenseCharge mForTestValue is " + this.mForTestValue + " execute return");
        return new byte[0];
    }

    public int getPorducePowerWhenLowPower() {
        Log.d(TAG, "getPorducePowerWhenLowPower is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getPorducePowerWhenLowPower();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getPorducePowerWhenLowPower mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getPortCapState(int i) {
        Log.i(TAG, "getPortCapState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.d(TAG, "getPortCapState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getChargingAdapterManager().getgetPortCapStateLeftR();
        }
        if (i != 2) {
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getgetPortCapStateRightR();
    }

    public int getSituPowerGenerationState() {
        Log.d(TAG, "getSituPowerGenerationState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getSituPowerGenerationState();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getSituPowerGenerationState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getSituPowerGenerationSupport() {
        Log.d(TAG, "getSituPowerGenerationSupport is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getSituPowerGenerationSupport();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getSituPowerGenerationSupport mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleFri() {
        Log.d(TAG, "getTimerCycleFri is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleFri();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleFri mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleMon() {
        Log.d(TAG, "getTimerCycleMon is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleMon();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleMon mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleSat() {
        Log.d(TAG, "getTimerCycleSat is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleSat();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleSat mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleSun() {
        Log.d(TAG, "getTimerCycleSun is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleSun();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleSun mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleThu() {
        Log.d(TAG, "getTimerCycleThu is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleThu();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleThu mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleTue() {
        Log.d(TAG, "getTimerCycleTue is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleTue();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleTue mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerCycleWed() {
        Log.d(TAG, "getTimerCycleWed is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerCycleWed();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerCycleWed mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerSwitch() {
        Log.d(TAG, "getTimerSwitch is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.d(TAG, "getTimerSwitch mForTestValue is " + this.mForTestValue + " execute return");
        }
        return 0;
    }

    public int getTimerType() {
        Log.d(TAG, "getTimerType is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerType();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerType mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerUnitHour() {
        Log.d(TAG, "getTimerUnitHour is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerUnitHour();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerUnitHour mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getTimerUnitMinite() {
        Log.d(TAG, "getTimerUnitMinite is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().getTimerUnitMinite();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getTimerUnitMinite mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public float getVoltageMax() {
        Log.d(TAG, "getVoltageMax is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVoltageMax();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.d(TAG, "getVoltageMax mForTestValue is" + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int getVtovChargeBatteryLevelNum() {
        Log.d(TAG, "getVtovChargeBatteryLevelNum is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovChargeBatteryLevelNum();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getVtovChargeBatteryLevelNum mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getVtovChargeBatteryLevelNumLimit() {
        Log.d(TAG, "getVtovChargeBatteryLevelNumLimit is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovChargeBatteryLevelNumLimit();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getVtovChargeBatteryLevelNumLimit mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getVtovDischargeConnectState() {
        Log.d(TAG, "getVtovDischargeConnectState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeConnectState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getVtovDischargeConnectState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getVtovDischargeLimitVal() {
        Log.d(TAG, "getVtovDischargeLimitVal is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeLimitVal();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getVtovDischargeLimitVal mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getVtovDischargeLowPowerHint() {
        Log.d(TAG, "getVtovDischargeLowPowerHint is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeLowPowerHint();
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "getVtovDischargeLowPowerHint mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int getVtovDischargeLowestVal() {
        Log.d(TAG, "getVtovDischargeLowestVal is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeLowestVal();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getVtovDischargeLowestVal mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public float getVtovDischargeQuantity() {
        Log.d(TAG, "getVtovDischargeQuantity is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeQuantity();
            }
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        Log.d(TAG, "getVtovDischargeQuantity mForTestValue is" + this.mForTestValue + " execute return");
        return 0.0f;
    }

    public int getWirelessChargingFrState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingFrState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getWirelessChargingFrState mForTestValue is " + this.mForTestValue + " execute return");
        return -1;
    }

    public int getWirelessChargingFrSwitchState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingFrSwitchState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getWirelessChargingFrSwitchState mForTestValue is " + this.mForTestValue + " execute return");
        return -1;
    }

    public int getWirelessChargingPowerFlState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingPowerFlState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getWirelessChargingPowerFlState mForTestValue is " + this.mForTestValue + " execute return");
        return -1;
    }

    public int getWirelessChargingPowerFrState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingPowerFrState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getWirelessChargingPowerFrState mForTestValue is " + this.mForTestValue + " execute return");
        return -1;
    }

    public int getWirelessChargingState() {
        Log.d(TAG, "getWirelessChargingState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getWirelessChargingState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int getWirelessChargingSwitchState() {
        Log.d(TAG, "getWirelessChargingSwitchState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingSwitchState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getWirelessChargingSwitchState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoChargingListener> it = this.mAbsBydAutoChargingListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        registerListener(absBYDAutoChargingListener, new int[0]);
    }

    public int setCarPlan(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Log.d(TAG, "setCarPlan is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setCarPlan(i, i2, i3, i4, i5, i6, i7, i8);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setCarPlan mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setCarPlanState(int i) {
        Log.d(TAG, "setCarPlanState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setCarPlanState(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setCarPlanState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setChargeLimit(float f) {
        Log.d(TAG, "setChargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setChargeLimit(f);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setChargeLimit mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.c(6012, Float.valueOf(f));
        return 0;
    }

    public int setChargePlanState(int i) {
        Log.d(TAG, "setChargePlanState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setChargePlanState(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setChargePlanState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setChargePreheatState(int i) {
        Log.d(TAG, "setChargePreheatState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setChargePreheatState(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setChargePreheatState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setChargeStopCapacityState(int i) {
        Log.d(TAG, "setChargeStopCapacityState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setChargeStopCapacityState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setChargeStopCapacityState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int setChargeTempCtlState(int i) {
        Log.d(TAG, "setChargeTempCtlState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setChargeTempCtlState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setChargeTempCtlState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int setChargeToggleGuard(int i) {
        Log.d(TAG, "setChargeToaggleGuard is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setChargeToggleGuard(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setChargeToaggleGuard mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.b(6013, i);
        return 0;
    }

    public int setDischargeLimit(int i) {
        Log.d(TAG, "setDischargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setDischargeLimit(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setDischargeLimit mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.b(6015, i);
        return 0;
    }

    public int setDischargeTime(long j) {
        Log.d(TAG, "setDischargeTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
                Log.d(TAG, "caradatper still connecting, please wait");
                return 0;
            }
            return this.mTsManagerImpl.getChargingAdapterManager().setDischargeTime(millionsSec2HourMinArray(j));
        }
        Log.d(TAG, "setDischargeTime mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.c(6017, Long.valueOf(j));
        return 0;
    }

    public int setDischargeToggle(int i) {
        Log.d(TAG, "setDischargeToggle is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setDischargeToggle(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setDischargeToggle mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.b(6016, i);
        return 0;
    }

    public int setDriveDistanceShowStandard(int i) {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        Log.d(TAG, "setDriveDistanceShowStandard is execute");
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowStandard(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setDriveDistanceShowStandard mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int setElectricityPriceLevel(byte[] bArr) {
        Log.d(TAG, "setElectricityPriceLevel is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setElectricityPriceLevel(bArr);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setElectricityPriceLevel mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setEvChargePlanEndTime(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setEVChargePlanEndTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.d(TAG, "setEVChargePlanEndTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.evYearEnd = i;
        this.evMonthEnd = i2;
        this.evDayEnd = i3;
        this.evHourEnd = i4;
        this.evMinuteEnd = i5;
        return this.mTsManagerImpl.getChargingAdapterManager().setEvChargePlanEndTime(i, i2, i3, i4, i5);
    }

    public int setEvChargePlanStartTime(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setEVChargePlanStartTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.d(TAG, "setEVChargePlanStartTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.evYear = i;
        this.evMonth = i2;
        this.evDay = i3;
        this.evHour = i4;
        this.evMinute = i5;
        return this.mTsManagerImpl.getChargingAdapterManager().setEvChargePlanStartTime(i, i2, i3, i4, i5);
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
        if (i == 2736) {
            setDischargeTime(iIntValue);
            return 1;
        }
        if (i == 2737) {
            setDischargeToggle(iIntValue);
            return 1;
        }
        switch (i) {
            case 127:
                setSyncTime(this.syncTimeYear, this.syncTimeMonth, iIntValue, this.syncTimeHour, this.syncTimeMinute);
                return 1;
            case 135:
                setSyncTime(this.syncTimeYear, this.syncTimeMonth, this.syncTimeDay, iIntValue, this.syncTimeMinute);
                return 1;
            case 139:
                setSyncTime(this.syncTimeYear, this.syncTimeMonth, this.syncTimeDay, this.syncTimeHour, iIntValue);
                return 1;
            case 143:
                setSyncTime(this.syncTimeYear, iIntValue, this.syncTimeDay, this.syncTimeHour, this.syncTimeMinute);
                return 1;
            case 162:
                setSyncTime(iIntValue, this.syncTimeMonth, this.syncTimeDay, this.syncTimeHour, this.syncTimeMinute);
                return 1;
            case 2496:
                this.evDayEnd = iIntValue;
                setEvChargePlanEndTime(this.evYearEnd, this.evMonthEnd, iIntValue, this.evHourEnd, this.evMinuteEnd);
                Log.d(TAG, "setEvChargePlanEndTimeDay" + iIntValue);
                return 1;
            case 2498:
                this.evHourEnd = iIntValue;
                setEvChargePlanEndTime(this.evYearEnd, this.evMonthEnd, this.evDayEnd, iIntValue, this.evMinuteEnd);
                Log.d(TAG, "setEvChargePlanEndTimeHour" + iIntValue);
                return 1;
            case 2500:
                this.evMinuteEnd = iIntValue;
                setEvChargePlanEndTime(this.evYearEnd, this.evMonthEnd, this.evDayEnd, this.evHourEnd, iIntValue);
                Log.d(TAG, "setEvChargePlanEndTimeMinute" + iIntValue);
                return 1;
            case 2502:
                this.evMonthEnd = iIntValue;
                setEvChargePlanEndTime(this.evYearEnd, iIntValue, this.evDayEnd, this.evHourEnd, this.evMinuteEnd);
                Log.d(TAG, "setEvChargePlanEndTimeMonth" + iIntValue);
                return 1;
            case 2504:
                this.evYearEnd = iIntValue;
                setEvChargePlanEndTime(iIntValue, this.evMonthEnd, this.evDayEnd, this.evHourEnd, this.evMinuteEnd);
                Log.d(TAG, "setEvChargePlanEndTimeYear" + iIntValue);
                return 1;
            case 2507:
                setEvChargePlanStartTime(this.evYear, this.evMonth, iIntValue, this.evHour, this.evMinute);
                return 1;
            case 2509:
                setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, iIntValue, this.evMinute);
                return 1;
            case 2511:
                setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, this.evHour, iIntValue);
                return 1;
            case 2513:
                this.evMonth = iIntValue;
                setEvChargePlanStartTime(this.evYear, iIntValue, this.evDay, this.evHour, this.evMinute);
                Log.d(TAG, "setEVChargePlanStartTimeMonth" + iIntValue);
                return 1;
            case 2515:
                this.evYear = iIntValue;
                setEvChargePlanStartTime(iIntValue, this.evMonth, this.evDay, this.evHour, this.evMinute);
                Log.d(TAG, "setEVChargePlanStartTimeYear" + iIntValue);
                return 1;
            case 2543:
                setHevChargingType(iIntValue);
                return 1;
            case 2567:
                setWirelessChargingSwitchState(iIntValue);
                return 1;
            case 2577:
                setMovePowerStationToggle(iIntValue);
                return 1;
            case 2593:
                setSituPowerGenerationState(iIntValue);
                return 1;
            case 2602:
                setPorducePowerWhenLowPower(iIntValue);
                return 1;
            case 2620:
                setNoSenseChargeState(iIntValue);
                return 1;
            case 2670:
                setChargeStopCapacityState(iIntValue);
                return 1;
            case 2722:
                setChargePlanState(iIntValue);
                Log.d(TAG, "setChargePlanState" + iIntValue);
                return 1;
            case 2734:
                setDischargeTime(iIntValue);
                return 1;
            case 2745:
                setDischargeLimit(iIntValue);
                return 1;
            default:
                switch (i) {
                    case 2529:
                        setCarPlan(0, 0, 0, this.carPlanYear, this.carPlanMonth, iIntValue, this.carPlanHour, this.carPlanMinute);
                        return 1;
                    case 2530:
                        setCarPlan(0, 0, 0, this.carPlanYear, this.carPlanMonth, this.carPlanDay, iIntValue, this.carPlanMinute);
                        return 1;
                    case 2531:
                        setCarPlan(0, 0, 0, this.carPlanYear, this.carPlanMonth, this.carPlanDay, this.carPlanHour, iIntValue);
                        return 1;
                    case 2532:
                        setCarPlan(0, 0, 0, this.carPlanYear, iIntValue, this.carPlanDay, this.carPlanHour, this.carPlanMinute);
                        return 1;
                    case 2533:
                        setCarPlanState(iIntValue);
                        return 1;
                    case 2534:
                        setCarPlan(0, 0, 0, iIntValue, this.carPlanMonth, this.carPlanDay, this.carPlanHour, this.carPlanMinute);
                        return 1;
                    default:
                        switch (i) {
                            case 2635:
                                this.dmDayEnd = iIntValue;
                                setHevChargePlanEndTime(this.dmYearEnd, this.dmMonthEnd, iIntValue, this.dmHourEnd, this.dmMinuteEnd);
                                Log.d(TAG, "setHevChargePlanEndTimeDay" + iIntValue);
                                return 1;
                            case 2636:
                                this.dmHourEnd = iIntValue;
                                setHevChargePlanEndTime(this.dmYearEnd, this.dmMonthEnd, this.dmDayEnd, iIntValue, this.dmMinuteEnd);
                                Log.d(TAG, "setHevChargePlanEndTimeHour" + iIntValue);
                                return 1;
                            case 2637:
                                this.dmMinuteEnd = iIntValue;
                                setHevChargePlanEndTime(this.dmYearEnd, this.dmMonthEnd, this.dmDayEnd, this.dmHourEnd, iIntValue);
                                Log.d(TAG, "setHevChargePlanEndTimeMinute" + iIntValue);
                                return 1;
                            case 2638:
                                this.dmMonthEnd = iIntValue;
                                setHevChargePlanEndTime(this.dmYearEnd, iIntValue, this.dmDayEnd, this.dmHourEnd, this.dmMinuteEnd);
                                Log.d(TAG, "setHevChargePlanEndTimeMonth" + iIntValue);
                                return 1;
                            case 2639:
                                this.dmYearEnd = iIntValue;
                                setHevChargePlanEndTime(iIntValue, this.dmMonthEnd, this.dmDayEnd, this.dmHourEnd, this.dmMinuteEnd);
                                Log.d(TAG, "setHevChargePlanEndTimeYear" + iIntValue);
                                return 1;
                            case 2640:
                                this.evDay = iIntValue;
                                this.dmDay = iIntValue;
                                int i2 = iIntValue;
                                setEvChargePlanStartTime(this.evYear, this.evMonth, i2, this.evHour, this.evMinute);
                                setHevChargePlanStartTime(this.dmYear, this.dmMonth, i2, this.dmHour, this.dmMinute);
                                Log.d(TAG, "setEVChargePlanStartTimeDay" + iIntValue);
                                return 1;
                            case 2641:
                                this.evHour = iIntValue;
                                this.dmHour = iIntValue;
                                int i3 = iIntValue;
                                setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, i3, this.evMinute);
                                setHevChargePlanStartTime(this.dmYear, this.dmMonth, this.dmDay, i3, this.dmMinute);
                                Log.d(TAG, "setEVChargePlanStartTimeHour" + iIntValue);
                                return 1;
                            case 2642:
                                this.evMinute = iIntValue;
                                this.dmMinute = iIntValue;
                                int i4 = iIntValue;
                                setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, this.evHour, i4);
                                setHevChargePlanStartTime(this.dmYear, this.dmMonth, this.dmDay, this.dmHour, i4);
                                Log.d(TAG, "setEVChargePlanStartTimeMinute" + iIntValue);
                                return 1;
                            case 2643:
                                this.dmMonth = iIntValue;
                                setHevChargePlanStartTime(this.dmYear, iIntValue, this.dmDay, this.dmHour, this.dmMinute);
                                Log.d(TAG, "setHevChargePlanStartTimeMonth" + iIntValue);
                                return 1;
                            case 2644:
                                this.dmYear = iIntValue;
                                setHevChargePlanStartTime(iIntValue, this.dmMonth, this.dmDay, this.dmHour, this.dmMinute);
                                Log.d(TAG, "setHevChargePlanStartTimeYear" + iIntValue);
                                return 1;
                            default:
                                Log.d(TAG, "case fail ! please check out");
                                return -1;
                        }
                }
        }
    }

    public int setHevChargePlanEndTime(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setHEVChargePlanEndTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setHevChargePlanEndTime(i, i2, i3, i4, i5);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setHEVChargePlanEndTime mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setHevChargePlanStartTime(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setHEVChargePlanStartTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.d(TAG, "setHEVChargePlanStartTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.dmYear = i;
        this.dmMonth = i2;
        this.dmDay = i3;
        this.dmHour = i4;
        this.dmMinute = i5;
        return this.mTsManagerImpl.getChargingAdapterManager().setHevChargePlanStartTime(i, i2, i3, i4, i5);
    }

    public int setHevChargingType(int i) {
        Log.d(TAG, "setHEVChargingType is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setHevChargingType(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setHEVChargingType mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int setIntelligentKeepTemMode(int i) {
        Log.d(TAG, "setIntelligentKeepTemMode is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setIntelligentKeepTemMode(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setIntelligentKeepTemMode mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.b(6011, i);
        return 0;
    }

    public int setMovePowerStationToggle(int i) {
        Log.d(TAG, "isSupportDischarge is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setMovePowerStationToggle(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setMovePowerStationToggle mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.b(6014, i);
        return 0;
    }

    public int setNoSenseChargeState(int i) {
        Log.d(TAG, "setNoSenseChargeState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setNoSenseChargeState(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setNoSenseChargeState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setPorducePowerWhenLowPower(int i) {
        Log.d(TAG, "setPorducePowerWhenLowPower is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setPorducePowerWhenLowPower(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setPorducePowerWhenLowPower mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.b(6018, i);
        return 0;
    }

    public int setPreSaleModelState(int i) {
        Log.d(TAG, "setPreSaleModelState is execute");
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setPreSaleModelState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setPreSaleModelState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public int setSituPowerGenerationState(int i) {
        Log.d(TAG, "setSituPowerGenerationState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setSituPowerGenerationState(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setSituPowerGenerationState mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setSyncTime(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setSyncTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setSyncTime(i, i2, i3, i4, i5);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setSyncTime mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleFri(int i) {
        Log.d(TAG, "setTimerCycleFri is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleFri(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleFri mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleMon(int i) {
        Log.d(TAG, "setTimerCycleMon is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleMon(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleMon mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleSat(int i) {
        Log.d(TAG, "setTimerCycleSat is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleSat(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleSat mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleSun(int i) {
        Log.d(TAG, "setTimerCycleSun is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleSun(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleSun mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleThu(int i) {
        Log.d(TAG, "setTimerCycleThu is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleThu(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleThu mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleTue(int i) {
        Log.d(TAG, "setTimerCycleTue is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleTue(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleTue mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerCycleWed(int i) {
        Log.d(TAG, "setTimerCycleWed is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerCycleWed(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerCycleWed mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerSwitch(int i) {
        Log.d(TAG, "setTimerSwitch is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerSwitch(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerSwitch mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerType(int i) {
        Log.d(TAG, "setTimerType is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerType(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerType mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerUnitHour(int i) {
        Log.d(TAG, "setTimerUnitHour is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerUnitHour(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerUnitHour mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerUnitMinite(int i) {
        Log.d(TAG, "setTimerUnitMinite is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setTimerUnitMinite(i);
            }
            Log.d(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.d(TAG, "setTimerUnitMinite mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setWirelessChargingFrSwitchState(int i) {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setWirelessChargingFrSwitchState(i);
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " setWirelessChargingFrSwitchState mForTestValue is " + this.mForTestValue + " execute return");
        return -1;
    }

    public int setWirelessChargingSwitchState(int i) {
        Log.d(TAG, "setWirelessChargingSwitchState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 != this.mForTestValue) {
            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                return this.mTsManagerImpl.getChargingAdapterManager().setWirelessChargingSwitchState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setWirelessChargingSwitchState mForTestValue is" + this.mForTestValue + " execute return");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_COMMON);
        if (1 != this.mForTestValue) {
            if (absBYDAutoChargingListener == null) {
                return;
            }
            this.mAbsBydAutoChargingListeners.remove(absBYDAutoChargingListener);
            unregisterConnectListener(absBYDAutoChargingListener);
            return;
        }
        Log.d(TAG, "unregisterListener mForTestValue is " + this.mForTestValue + " execute return");
        this.mTestCallback.d(absBYDAutoChargingListener);
    }

    public void registerListener(AbsBYDAutoChargingListener absBYDAutoChargingListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe charging listener " + absBYDAutoChargingListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoChargingListener == null) {
            return;
        }
        if (1 == this.mForTestValue) {
            Log.d(TAG, "registerListener one para mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.e(absBYDAutoChargingListener);
            return;
        }
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_COMMON);
        if (!this.mAbsBydAutoChargingListeners.contains(absBYDAutoChargingListener)) {
            this.mAbsBydAutoChargingListeners.add(absBYDAutoChargingListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoChargingListener);
        TsManagerImpl.getInstance(this.mContext).registerChargingCallback();
    }
}
