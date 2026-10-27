package android.hardware.bydauto.reminder.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.reminder.AbsBYDAutoReminderListener;
import android.hardware.bydauto.reminder.BYDAutoReminderDevice;
import android.provider.Settings;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ReminderManagerImpl extends AbsTsMyCar {
    private static final int DEFAULT = -1;
    public static final int FIFTY = 15;
    public static final int FIVE = 5;
    public static final int FOUR = 4;
    public static final int ONE = 1;
    private static final int REMINDER_TYPE_COUNT = 150;
    public static final int SEVEN = 7;
    public static final int SEVENTEEN = 17;
    public static final int SIX = 6;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_ReminderManagerImpl";
    protected static final String TAG = "ReminderManagerImpl";
    public static final int THREE = 3;
    public static final int TWENTYTHREE = 23;
    public static final int TWO = 2;
    private static volatile ReminderManagerImpl sInstance;
    private int airConditioningUnlock;
    private int doorStateHood;
    private int doorStateHoodStatus;
    private int frontFrost;
    private int lastInsufficientFuel;
    private int lastLowkeyPower;
    private AbsBYDAutoReminderListener mAbsBydAutoReminderListener;
    private int mAccTextInfo;
    private int mAdasTwoValue;
    private ContentResolver mContentResolver;
    private Context mContext;
    private float mCurrentSpeed;
    private int[] mFeatureIds;
    private int mForTest;
    private float mLastCurrentSpeed;
    private TsManagerImpl mTsManagerImpl;
    private int monIndChangedBeCarefulCarsBackLeft;
    private int onIndChangedAdas0x26CFirst;
    private int onIndChangedAdas0x29CSecond;
    private int onIndChangedAdas0x316;
    private int onIndChangedAdas0x32D;
    private int onIndChangedBeCarefulCarsBackRight;
    private int onIndChangedExternalDischargeTermination;
    private int onIndChangedInsufficientFuel;
    private int onIndChangedKeyNotDetected;
    private int onIndChangedLeaveTheVehicleAtNonPGearIns;
    private int onIndChangedLowkeyPower;
    private int onIndChangedNightRunningLights;
    private int onIndChangedNotWearingSeatBelt;
    private int onIndChangedParkingBrakeNotReleasedIns;
    private int onIndChangedPowerFailure;
    private int onIndChangedPowerSystemFault;
    private int onIndChangedRemoteDrivingSuspension;
    private int onIndChangedstart;
    private int onmDoorStateLeftFront;
    private int onmDoorStateLeftRear;
    private int onmDoorStateRightFront;
    private int onmDoorStateRightRear;
    private int rearLock;
    private int rmdTakePhoneR;
    private int runningState;
    private int stateLuggage;
    private int stateLuggageStatus;
    private final CopyOnWriteArrayList<AbsBYDAutoReminderListener> mAbsBydAutoReminderListeners = new CopyOnWriteArrayList<>();
    private int mAdasValue = 0;
    private int mLastX26CValue = 0;
    private int m245TempValue = 0;
    private int m410LeftTempValue = 0;
    private int m410RightTempValue = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private int lastdoorStatus = 0;
    private boolean flag = true;
    private boolean doorStatusAll = true;
    private boolean hasReprotDoor = false;
    private boolean isReport = false;
    private boolean gearFlag = true;
    private int laneFlag = 0;
    private int sysFlag = 0;
    private int frontCarAdaptationResult = 0;
    private boolean lightFlag = true;
    private boolean remoteControlFlag = true;
    private boolean safeBeltFlag = true;
    private boolean keyFlag = true;
    private int gearResult = 0;
    private int carResult = 0;
    private int mLastStop = 0;
    private int dynamical = 0;
    private int lastNumber = 0;
    private int rmdRapidAccelerationLimit = -1;
    private int rmdRapidAccelerationLimitLast = -1;
    private boolean[] arr = new boolean[150];

    public static ReminderManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ReminderManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new ReminderManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    ReminderManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_COMMON);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "ReminderManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.reminder.tsimpl.ReminderManagerImpl.1
            public void onAssistChanged(int id, int areaId, CarAssistPacket packet) {
                if (!ReminderManagerImpl.this.mAbsBydAutoReminderListeners.isEmpty()) {
                    try {
                        ReminderManagerImpl.this.assistCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(ReminderManagerImpl.TAG, "assistCallback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(ReminderManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int id, int areaId, CarAssistPacket packet) {
        int i;
        int i2;
        int i3;
        Log.i(TAG, "AssistCallback propertyId:" + id);
        this.onIndChangedstart = packet.getMonIndChangedstart();
        this.onIndChangedPowerFailure = packet.getMonIndChangedPowerFailure();
        this.onIndChangedParkingBrakeNotReleasedIns = packet.getMonIndChangedParkingBrakeNotReleasedIns();
        int i4 = packet.getonIndChangedRmdRapidAccelerationLimit();
        this.rmdRapidAccelerationLimit = i4;
        switch (id) {
            case 557845248:
                Log.i(TAG, id + "   state:" + this.onIndChangedstart);
                if (this.gearResult != 6 && this.onIndChangedstart == 6 && (i = this.onIndChangedPowerFailure) != 3 && i != 4 && i != 5) {
                    this.arr[101] = true;
                    for (AbsBYDAutoReminderListener listener : this.mAbsBydAutoReminderListeners) {
                        listener.onIndChanged(101);
                        Log.e(TAG, "onIndChanged ID_IND_CHANGED_START is 101");
                    }
                    caseFeatureIds(4152, 101);
                } else {
                    this.arr[101] = false;
                }
                this.gearResult = this.onIndChangedstart;
                break;
            case 557845249:
                Log.i(TAG, id + "   state:" + this.onIndChangedPowerFailure);
                if (this.onIndChangedPowerFailure == 3 && isRequired()) {
                    boolean[] zArr = this.arr;
                    zArr[101] = true;
                    zArr[102] = false;
                    zArr[42] = false;
                    for (AbsBYDAutoReminderListener listener2 : this.mAbsBydAutoReminderListeners) {
                        listener2.onIndChanged(101);
                        Log.e(TAG, "onIndChanged returnValue4 is 101");
                    }
                    caseFeatureIds(4150, 101);
                } else {
                    int i5 = this.onIndChangedPowerFailure;
                    if ((i5 != 4 && i5 != 5) || !isRequired()) {
                        if (this.onIndChangedPowerFailure == 6 && this.carResult != 6) {
                            boolean[] zArr2 = this.arr;
                            zArr2[101] = false;
                            zArr2[102] = false;
                            zArr2[42] = true;
                            for (AbsBYDAutoReminderListener listener3 : this.mAbsBydAutoReminderListeners) {
                                listener3.onIndChanged(42);
                                Log.e(TAG, "onIndChanged returnValue4 is 42");
                            }
                            caseFeatureIds(4150, 42);
                        } else {
                            boolean[] zArr3 = this.arr;
                            zArr3[101] = false;
                            zArr3[102] = false;
                            zArr3[42] = false;
                        }
                    } else {
                        boolean[] zArr4 = this.arr;
                        zArr4[101] = false;
                        zArr4[102] = true;
                        zArr4[42] = false;
                        for (AbsBYDAutoReminderListener listener4 : this.mAbsBydAutoReminderListeners) {
                            listener4.onIndChanged(102);
                            Log.e(TAG, "onIndChanged returnValue4 is 102");
                        }
                        caseFeatureIds(4150, 102);
                    }
                }
                this.carResult = this.onIndChangedPowerFailure;
                break;
            default:
                switch (id) {
                    case 557845259:
                        this.onIndChangedLeaveTheVehicleAtNonPGearIns = packet.getMonIndChangedLeaveTheVehicleAtNonpGearIns();
                        Log.i(TAG, id + "   state:" + this.onIndChangedLeaveTheVehicleAtNonPGearIns + ",lastNumber:" + this.lastNumber);
                        if (this.onIndChangedLeaveTheVehicleAtNonPGearIns == 1 && this.lastNumber != 1) {
                            this.arr[103] = true;
                            for (AbsBYDAutoReminderListener listener5 : this.mAbsBydAutoReminderListeners) {
                                listener5.onIndChanged(103);
                            }
                            caseFeatureIds(1336, 103);
                        } else {
                            this.arr[103] = false;
                        }
                        this.lastNumber = this.onIndChangedLeaveTheVehicleAtNonPGearIns;
                        break;
                    case 557845261:
                        this.onIndChangedRemoteDrivingSuspension = packet.getMonIndChangedRemoteDrivingSuspension();
                        Log.i(TAG, id + "   state:" + this.onIndChangedRemoteDrivingSuspension);
                        if (this.remoteControlFlag && this.onIndChangedRemoteDrivingSuspension == 2) {
                            this.arr[41] = true;
                            for (AbsBYDAutoReminderListener listener6 : this.mAbsBydAutoReminderListeners) {
                                listener6.onIndChanged(41);
                                this.remoteControlFlag = false;
                            }
                        } else {
                            this.arr[41] = false;
                        }
                        if (this.onIndChangedRemoteDrivingSuspension != 2) {
                            this.remoteControlFlag = true;
                        }
                        break;
                    case 557846612:
                        if (packet.getonIndChangedRmdRearAirConditioningOperateInvalid() == 2) {
                            this.arr[131] = true;
                            for (AbsBYDAutoReminderListener listener7 : this.mAbsBydAutoReminderListeners) {
                                listener7.onIndChanged(131);
                                Log.e(TAG, "onIndChanged131");
                            }
                        } else {
                            this.arr[131] = false;
                        }
                        break;
                    case 557856774:
                        this.mAdasTwoValue = packet.getMonIndChangedAdasFirst();
                        Log.i(TAG, id + "   state:" + this.mAdasTwoValue);
                        int returnValueFirst = -1;
                        int i6 = this.mAdasTwoValue;
                        if (i6 >= 2 && i6 <= 4) {
                            int i7 = this.onIndChangedAdas0x29CSecond;
                            if (i7 == 4) {
                                returnValueFirst = 126;
                                boolean[] zArr5 = this.arr;
                                zArr5[126] = true;
                                zArr5[127] = false;
                                zArr5[125] = false;
                            } else if (i7 == 5) {
                                returnValueFirst = 127;
                                boolean[] zArr6 = this.arr;
                                zArr6[126] = false;
                                zArr6[127] = true;
                                zArr6[125] = false;
                            } else if (i7 == 7) {
                                returnValueFirst = 125;
                                boolean[] zArr7 = this.arr;
                                zArr7[126] = false;
                                zArr7[127] = false;
                                zArr7[125] = true;
                            } else {
                                boolean[] zArr8 = this.arr;
                                zArr8[126] = false;
                                zArr8[127] = false;
                                zArr8[125] = false;
                            }
                        } else {
                            boolean[] zArr9 = this.arr;
                            zArr9[126] = false;
                            zArr9[127] = false;
                            zArr9[125] = false;
                        }
                        int i8 = this.sysFlag;
                        int i9 = this.mAdasTwoValue;
                        if (i8 != i9 && ((i9 == 2 || i9 == 3 || i9 == 4) && returnValueFirst != -1)) {
                            for (AbsBYDAutoReminderListener listener8 : this.mAbsBydAutoReminderListeners) {
                                listener8.onIndChanged(returnValueFirst);
                            }
                        }
                        this.sysFlag = this.mAdasTwoValue;
                        break;
                    case 557856776:
                        this.onIndChangedAdas0x316 = packet.getMonIndChangedAdasThree();
                        Log.i(TAG, id + "   state:" + this.onIndChangedAdas0x316 + " mAdasValue:" + this.mAdasValue);
                        int returnValue2 = -1;
                        int i10 = this.onIndChangedAdas0x316;
                        if (i10 == 3) {
                            returnValue2 = 123;
                            this.arr[123] = true;
                        } else if (i10 == 1 || i10 == 5) {
                            int i11 = this.mAdasValue;
                            if (i11 == 2 || i11 == 3) {
                                returnValue2 = 124;
                                this.arr[123] = true;
                            } else {
                                this.arr[123] = false;
                            }
                        } else {
                            this.arr[123] = false;
                        }
                        if ((this.onIndChangedAdas0x316 == 3 && this.mAdasValue != 3) || (((i2 = this.onIndChangedAdas0x316) == 1 || i2 == 5) && (((i3 = this.mAdasValue) == 2 || i3 == 3) && returnValue2 != -1))) {
                            for (AbsBYDAutoReminderListener listener9 : this.mAbsBydAutoReminderListeners) {
                                listener9.onIndChanged(returnValue2);
                            }
                        }
                        this.mAdasValue = this.onIndChangedAdas0x316;
                        break;
                    case 557857284:
                        Log.i(TAG, id + "   state:" + this.onIndChangedParkingBrakeNotReleasedIns + "   currentSpeed:" + this.mCurrentSpeed);
                        if (this.mLastStop != 1 && this.onIndChangedParkingBrakeNotReleasedIns == 1 && this.mCurrentSpeed > 5.0f) {
                            this.arr[8] = true;
                            for (AbsBYDAutoReminderListener listener10 : this.mAbsBydAutoReminderListeners) {
                                listener10.onIndChanged(8);
                                Log.e(TAG, "0x055 8");
                            }
                        } else {
                            this.arr[8] = false;
                        }
                        this.mLastStop = this.onIndChangedParkingBrakeNotReleasedIns;
                        break;
                    case 557859850:
                        this.onIndChangedLowkeyPower = packet.getMonIndChangedLowkeyPower();
                        Log.i(TAG, id + "   state:" + this.onIndChangedLowkeyPower + ", lastLowkeyPower = " + this.lastLowkeyPower);
                        if (this.onIndChangedLowkeyPower == 1 && this.lastLowkeyPower != 1) {
                            this.arr[10] = true;
                            for (AbsBYDAutoReminderListener listener11 : this.mAbsBydAutoReminderListeners) {
                                listener11.onIndChanged(10);
                            }
                        } else {
                            this.arr[10] = false;
                        }
                        this.lastLowkeyPower = this.onIndChangedLowkeyPower;
                        break;
                    case 557860386:
                        if (packet.getMonIndChangedExternalDischargeTermination() == 2) {
                            this.arr[104] = true;
                            for (AbsBYDAutoReminderListener listener12 : this.mAbsBydAutoReminderListeners) {
                                listener12.onIndChanged(104);
                            }
                        } else {
                            this.arr[104] = false;
                        }
                        break;
                    case 557862927:
                        int otaPowerLevel = packet.getOtaPowerLevel();
                        Log.i(TAG, id + "   otaPowerLevel:" + otaPowerLevel);
                        if (otaPowerLevel == 1) {
                            this.m410RightTempValue = 0;
                            this.mAdasValue = 0;
                            this.mLastX26CValue = 0;
                            this.m245TempValue = 0;
                            this.m410LeftTempValue = 0;
                            this.flag = true;
                            this.laneFlag = 0;
                            this.sysFlag = 0;
                            this.frontCarAdaptationResult = 0;
                            this.lightFlag = true;
                            this.remoteControlFlag = true;
                            this.safeBeltFlag = true;
                            this.keyFlag = true;
                            this.gearResult = 0;
                            this.carResult = 0;
                            this.mLastStop = 0;
                            this.dynamical = 0;
                            this.lastNumber = 0;
                            this.hasReprotDoor = false;
                            this.mCurrentSpeed = 0.0f;
                            this.doorStatusAll = true;
                            this.onIndChangedLowkeyPower = 0;
                            this.lastLowkeyPower = 0;
                            this.onIndChangedInsufficientFuel = 0;
                            this.lastInsufficientFuel = 0;
                        }
                        break;
                    case 557862953:
                        int defaultValue = packet.getLeftStrutMorotRunningState();
                        this.runningState = defaultValue;
                        Log.i(TAG, id + "   runningState:" + this.runningState);
                        if (this.stateLuggage == 2 && this.mCurrentSpeed > 5.0f) {
                            int i12 = this.runningState;
                            if (i12 == 1 || i12 == 0) {
                                for (AbsBYDAutoReminderListener listener13 : this.mAbsBydAutoReminderListeners) {
                                    listener13.onIndChanged(136);
                                }
                            }
                            break;
                        }
                        break;
                    case 557870400:
                        this.mAccTextInfo = packet.getAccTextInfo();
                        Log.i(TAG, id + "   mAccTextInfo:" + this.mAccTextInfo);
                        if (this.mAccTextInfo == 23) {
                            for (AbsBYDAutoReminderListener listener14 : this.mAbsBydAutoReminderListeners) {
                                listener14.onIndChanged(129);
                            }
                        }
                        break;
                    case 557870438:
                        this.onIndChangedAdas0x29CSecond = packet.getMonIndChangedAdasSecond();
                        Log.i(TAG, id + "   state:" + this.onIndChangedAdas0x29CSecond + " mAdasTwoValue:" + this.mAdasTwoValue);
                        int returnValue = -1;
                        int i13 = this.mAdasTwoValue;
                        if (i13 >= 2 && i13 <= 4) {
                            int i14 = this.onIndChangedAdas0x29CSecond;
                            if (i14 == 4) {
                                returnValue = 126;
                                boolean[] zArr10 = this.arr;
                                zArr10[126] = true;
                                zArr10[127] = false;
                                zArr10[125] = false;
                            } else if (i14 == 5) {
                                returnValue = 127;
                                boolean[] zArr11 = this.arr;
                                zArr11[126] = false;
                                zArr11[127] = true;
                                zArr11[125] = false;
                            } else if (i14 == 7) {
                                returnValue = 125;
                                boolean[] zArr12 = this.arr;
                                zArr12[126] = false;
                                zArr12[127] = false;
                                zArr12[125] = true;
                            } else {
                                boolean[] zArr13 = this.arr;
                                zArr13[126] = false;
                                zArr13[127] = false;
                                zArr13[125] = false;
                            }
                        } else {
                            boolean[] zArr14 = this.arr;
                            zArr14[126] = false;
                            zArr14[127] = false;
                            zArr14[125] = false;
                        }
                        int i15 = this.laneFlag;
                        int i16 = this.onIndChangedAdas0x29CSecond;
                        if (i15 != i16 && ((i16 == 4 || i16 == 5 || i16 == 7) && returnValue != -1)) {
                            for (AbsBYDAutoReminderListener listener15 : this.mAbsBydAutoReminderListeners) {
                                listener15.onIndChanged(returnValue);
                            }
                        }
                        this.laneFlag = this.onIndChangedAdas0x29CSecond;
                        break;
                    case 557870596:
                        this.onIndChangedNotWearingSeatBelt = packet.getMonIndChangedNotWearingSeatBelt();
                        Log.i(TAG, id + "   state:" + this.onIndChangedNotWearingSeatBelt);
                        if (this.safeBeltFlag && this.onIndChangedNotWearingSeatBelt == 1) {
                            this.arr[36] = true;
                            for (AbsBYDAutoReminderListener listener16 : this.mAbsBydAutoReminderListeners) {
                                listener16.onIndChanged(36);
                            }
                            caseFeatureIds(1429, 36);
                            this.safeBeltFlag = false;
                        } else {
                            this.arr[36] = false;
                        }
                        if (this.onIndChangedNotWearingSeatBelt != 1) {
                            this.safeBeltFlag = true;
                        }
                        break;
                    case 559957505:
                        float monIndChangedParkingBrakeNotReleasedEsc = packet.getMonIndChangedParkingBrakeNotReleasedEsc();
                        this.mCurrentSpeed = monIndChangedParkingBrakeNotReleasedEsc;
                        if (this.flag && monIndChangedParkingBrakeNotReleasedEsc > 5.0f && this.mLastCurrentSpeed <= 5.0f) {
                            if ((this.onmDoorStateLeftFront == 2 || this.onmDoorStateRightFront == 2 || this.onmDoorStateLeftRear == 2 || this.onmDoorStateRightRear == 2) && !this.hasReprotDoor && this.doorStatusAll) {
                                Log.i(TAG, id + " ,doorStatusAll: " + this.doorStatusAll + " ,hasReprotDoor: " + this.hasReprotDoor);
                                this.arr[9] = true;
                                for (AbsBYDAutoReminderListener listener17 : this.mAbsBydAutoReminderListeners) {
                                    listener17.onIndChanged(9);
                                    Log.e(TAG, "onIndChanged is 9");
                                }
                                this.doorStatusAll = false;
                                this.hasReprotDoor = true;
                            } else if (this.onIndChangedParkingBrakeNotReleasedIns == 1) {
                                this.arr[8] = true;
                                for (AbsBYDAutoReminderListener listener18 : this.mAbsBydAutoReminderListeners) {
                                    listener18.onIndChanged(8);
                                }
                            } else if (this.doorStateHoodStatus != 2 && this.doorStateHood == 2) {
                                for (AbsBYDAutoReminderListener listener19 : this.mAbsBydAutoReminderListeners) {
                                    listener19.onIndChanged(135);
                                }
                                this.doorStateHoodStatus = this.doorStateHood;
                            } else if (this.runningState != 2 && this.stateLuggageStatus != 2 && this.stateLuggage == 2) {
                                for (AbsBYDAutoReminderListener listener20 : this.mAbsBydAutoReminderListeners) {
                                    listener20.onIndChanged(136);
                                }
                                this.stateLuggageStatus = this.stateLuggage;
                            } else {
                                this.arr[9] = false;
                            }
                            this.flag = false;
                        } else {
                            this.arr[9] = false;
                            this.doorStatusAll = true;
                            this.hasReprotDoor = false;
                        }
                        if (this.mCurrentSpeed < 5.0f) {
                            this.flag = true;
                        }
                        this.mLastCurrentSpeed = this.mCurrentSpeed;
                        break;
                    default:
                        switch (id) {
                            case 557845263:
                                this.onIndChangedKeyNotDetected = packet.getMonIndChangedKeyNotDetected();
                                Log.i(TAG, id + "   state:" + this.onIndChangedKeyNotDetected);
                                if (this.keyFlag && this.onIndChangedKeyNotDetected == 2) {
                                    this.arr[7] = true;
                                    for (AbsBYDAutoReminderListener listener21 : this.mAbsBydAutoReminderListeners) {
                                        listener21.onIndChanged(7);
                                    }
                                    caseFeatureIds(337, 7);
                                    this.keyFlag = false;
                                } else {
                                    this.arr[7] = false;
                                }
                                if (this.onIndChangedKeyNotDetected != 2) {
                                    this.keyFlag = true;
                                }
                                break;
                            case 557845264:
                                this.onIndChangedNightRunningLights = packet.getMonIndChangedNightRunningLights();
                                Log.i(TAG, id + "   state:" + this.onIndChangedNightRunningLights);
                                if (this.lightFlag && this.onIndChangedNightRunningLights == 1) {
                                    this.arr[38] = true;
                                    for (AbsBYDAutoReminderListener listener22 : this.mAbsBydAutoReminderListeners) {
                                        listener22.onIndChanged(38);
                                        this.lightFlag = false;
                                    }
                                    caseFeatureIds(3311, 38);
                                } else {
                                    this.arr[38] = false;
                                }
                                if (this.onIndChangedNightRunningLights != 1) {
                                    this.lightFlag = true;
                                }
                                break;
                            case 557845265:
                                this.onIndChangedInsufficientFuel = packet.getMonIndChangedInsufficientFuel();
                                Log.i(TAG, id + "   state:" + this.onIndChangedInsufficientFuel);
                                if (this.onIndChangedInsufficientFuel == 1 && this.lastInsufficientFuel != 1) {
                                    this.arr[37] = true;
                                    for (AbsBYDAutoReminderListener listener23 : this.mAbsBydAutoReminderListeners) {
                                        listener23.onIndChanged(37);
                                    }
                                    caseFeatureIds(1736, 37);
                                } else {
                                    this.arr[37] = false;
                                }
                                this.lastInsufficientFuel = this.onIndChangedInsufficientFuel;
                                break;
                            case 557845266:
                                this.onIndChangedExternalDischargeTermination = packet.getMonIndChangedExternalDischargeTermination();
                                int i17 = this.m245TempValue;
                                if ((i17 == 0 || i17 == 1 || i17 == 3) && this.onIndChangedExternalDischargeTermination == 2) {
                                    this.arr[104] = true;
                                    for (AbsBYDAutoReminderListener listener24 : this.mAbsBydAutoReminderListeners) {
                                        listener24.onIndChanged(104);
                                    }
                                } else {
                                    this.arr[104] = false;
                                }
                                this.m245TempValue = this.onIndChangedExternalDischargeTermination;
                                break;
                            case 557845267:
                                this.onIndChangedPowerSystemFault = packet.getMonIndChangedPowerSystemFault();
                                Log.i(TAG, id + "   state:" + this.onIndChangedPowerSystemFault);
                                int i18 = this.dynamical;
                                int i19 = this.onIndChangedPowerSystemFault;
                                if (i18 != i19 && i19 >= 1 && i19 <= 4) {
                                    int returnValue5 = 0;
                                    if (i19 == 1) {
                                        returnValue5 = 106;
                                        boolean[] zArr15 = this.arr;
                                        zArr15[106] = true;
                                        zArr15[107] = false;
                                        zArr15[108] = false;
                                        zArr15[109] = false;
                                    } else if (i19 == 2) {
                                        returnValue5 = 107;
                                        boolean[] zArr16 = this.arr;
                                        zArr16[106] = false;
                                        zArr16[107] = true;
                                        zArr16[108] = false;
                                        zArr16[109] = false;
                                    } else if (i19 == 3) {
                                        returnValue5 = 108;
                                        boolean[] zArr17 = this.arr;
                                        zArr17[106] = false;
                                        zArr17[107] = false;
                                        zArr17[108] = true;
                                        zArr17[109] = false;
                                    } else if (i19 == 4) {
                                        returnValue5 = 109;
                                        boolean[] zArr18 = this.arr;
                                        zArr18[106] = false;
                                        zArr18[107] = false;
                                        zArr18[108] = false;
                                        zArr18[109] = true;
                                    } else {
                                        boolean[] zArr19 = this.arr;
                                        zArr19[106] = false;
                                        zArr19[107] = false;
                                        zArr19[108] = false;
                                        zArr19[109] = false;
                                    }
                                    if (returnValue5 != 0) {
                                        for (AbsBYDAutoReminderListener listener25 : this.mAbsBydAutoReminderListeners) {
                                            listener25.onIndChanged(returnValue5);
                                        }
                                        caseFeatureIds(1677, Integer.valueOf(returnValue5));
                                    }
                                } else {
                                    boolean[] zArr20 = this.arr;
                                    zArr20[106] = false;
                                    zArr20[107] = false;
                                    zArr20[108] = false;
                                    zArr20[109] = false;
                                }
                                this.dynamical = this.onIndChangedPowerSystemFault;
                                break;
                            default:
                                switch (id) {
                                    case 557845269:
                                        this.monIndChangedBeCarefulCarsBackLeft = packet.getMonIndChangedBeCarefulCarsBackLeft();
                                        Log.i(TAG, "   monIndChangedBeCarefulCarsBackLeft:" + this.monIndChangedBeCarefulCarsBackLeft);
                                        int i20 = this.m410LeftTempValue;
                                        if ((i20 == 0 || i20 == 1 || i20 == 3) && this.monIndChangedBeCarefulCarsBackLeft == 2) {
                                            this.arr[111] = true;
                                            for (AbsBYDAutoReminderListener listener26 : this.mAbsBydAutoReminderListeners) {
                                                listener26.onIndChanged(111);
                                            }
                                        } else {
                                            this.arr[111] = false;
                                        }
                                        this.m410LeftTempValue = this.monIndChangedBeCarefulCarsBackLeft;
                                        break;
                                    case 557845270:
                                        this.onIndChangedBeCarefulCarsBackRight = packet.getMonIndChangedBeCarefulCarsBackRight();
                                        int i21 = this.m410RightTempValue;
                                        if ((i21 == 0 || i21 == 1 || i21 == 3) && this.onIndChangedBeCarefulCarsBackRight == 2) {
                                            this.arr[111] = true;
                                            for (AbsBYDAutoReminderListener listener27 : this.mAbsBydAutoReminderListeners) {
                                                listener27.onIndChanged(111);
                                            }
                                        } else {
                                            this.arr[111] = false;
                                        }
                                        this.m410RightTempValue = this.onIndChangedBeCarefulCarsBackRight;
                                        break;
                                    case 557845271:
                                        this.onIndChangedAdas0x32D = packet.getMonIndChangedAdasFour();
                                        Log.i(TAG, id + "   state:" + this.onIndChangedAdas0x32D);
                                        int returnValue3 = -1;
                                        int i22 = this.onIndChangedAdas0x32D;
                                        if (i22 == 3) {
                                            returnValue3 = 121;
                                            boolean[] zArr21 = this.arr;
                                            zArr21[121] = true;
                                            zArr21[122] = false;
                                            zArr21[128] = false;
                                            zArr21[120] = false;
                                            zArr21[129] = false;
                                        } else if (i22 == 2) {
                                            returnValue3 = 122;
                                            boolean[] zArr22 = this.arr;
                                            zArr22[121] = false;
                                            zArr22[122] = true;
                                            zArr22[128] = false;
                                            zArr22[120] = false;
                                            zArr22[129] = false;
                                        } else if (i22 == 6) {
                                            returnValue3 = 128;
                                            boolean[] zArr23 = this.arr;
                                            zArr23[121] = false;
                                            zArr23[122] = false;
                                            zArr23[128] = true;
                                            zArr23[120] = false;
                                            zArr23[129] = false;
                                        } else if (i22 == 15) {
                                            returnValue3 = 120;
                                            boolean[] zArr24 = this.arr;
                                            zArr24[121] = false;
                                            zArr24[122] = false;
                                            zArr24[128] = false;
                                            zArr24[120] = true;
                                            zArr24[129] = false;
                                        } else if (i22 == 23) {
                                            returnValue3 = 129;
                                            boolean[] zArr25 = this.arr;
                                            zArr25[121] = false;
                                            zArr25[122] = false;
                                            zArr25[128] = false;
                                            zArr25[120] = false;
                                            zArr25[129] = true;
                                        } else {
                                            boolean[] zArr26 = this.arr;
                                            zArr26[121] = false;
                                            zArr26[122] = false;
                                            zArr26[128] = false;
                                            zArr26[120] = false;
                                            zArr26[129] = false;
                                        }
                                        int i23 = this.frontCarAdaptationResult;
                                        int i24 = this.onIndChangedAdas0x32D;
                                        if (i23 != i24 && ((i24 == 2 || i24 == 3 || i24 == 6 || i24 == 15 || i24 == 23) && returnValue3 != -1)) {
                                            for (AbsBYDAutoReminderListener listener28 : this.mAbsBydAutoReminderListeners) {
                                                listener28.onIndChanged(returnValue3);
                                            }
                                        }
                                        this.frontCarAdaptationResult = this.onIndChangedAdas0x32D;
                                        break;
                                    case 557845272:
                                        this.onIndChangedAdas0x26CFirst = packet.getMonIndChangedAdas0x26CFirst();
                                        Log.i(TAG, id + "   state:" + this.onIndChangedAdas0x26CFirst);
                                        if (this.mLastX26CValue != 2 && this.onIndChangedAdas0x26CFirst == 2) {
                                            this.arr[110] = true;
                                            for (AbsBYDAutoReminderListener listener29 : this.mAbsBydAutoReminderListeners) {
                                                listener29.onIndChanged(110);
                                            }
                                            caseFeatureIds(3624, 110);
                                        } else {
                                            this.arr[110] = false;
                                        }
                                        this.mLastX26CValue = this.onIndChangedAdas0x26CFirst;
                                        break;
                                    case 557845273:
                                        if (this.rmdRapidAccelerationLimitLast != -1 && i4 == 0) {
                                            this.arr[130] = true;
                                            for (AbsBYDAutoReminderListener listener30 : this.mAbsBydAutoReminderListeners) {
                                                listener30.onIndChanged(130);
                                            }
                                        } else {
                                            this.arr[130] = false;
                                        }
                                        this.rmdRapidAccelerationLimitLast = this.rmdRapidAccelerationLimit;
                                        break;
                                    default:
                                        switch (id) {
                                            case 557845275:
                                                int rmdTakePhoneValue = packet.getonIndChangedRmdRearAirConditioningUnlock();
                                                this.airConditioningUnlock = rmdTakePhoneValue;
                                                if (rmdTakePhoneValue == 2) {
                                                    this.arr[132] = true;
                                                    for (AbsBYDAutoReminderListener listener31 : this.mAbsBydAutoReminderListeners) {
                                                        listener31.onIndChanged(132);
                                                        Log.e(TAG, "onIndChanged132");
                                                    }
                                                } else {
                                                    this.arr[132] = false;
                                                }
                                                break;
                                            case 557845276:
                                                this.rmdTakePhoneR = packet.getRmdTakePhone();
                                                Log.i(TAG, id + "   rmdTakePhoneR:" + this.rmdTakePhoneR);
                                                if (this.rmdTakePhoneR == 1) {
                                                    for (AbsBYDAutoReminderListener listener32 : this.mAbsBydAutoReminderListeners) {
                                                        listener32.onIndChanged(137);
                                                    }
                                                }
                                                break;
                                            default:
                                                switch (id) {
                                                    case 557862943:
                                                        this.onmDoorStateLeftFront = packet.getmDoorStateLeftFront();
                                                        Log.i(TAG, id + "   state:" + this.onmDoorStateLeftFront + "   currentSpeed: " + this.mCurrentSpeed + " ,doorStatusAll: " + this.doorStatusAll + " ,hasReprotDoor: " + this.hasReprotDoor);
                                                        if (this.onmDoorStateLeftFront == 2 && this.mCurrentSpeed > 5.0f && !this.hasReprotDoor && this.doorStatusAll) {
                                                            for (AbsBYDAutoReminderListener listener33 : this.mAbsBydAutoReminderListeners) {
                                                                listener33.onIndChanged(9);
                                                            }
                                                            this.doorStatusAll = false;
                                                            this.hasReprotDoor = true;
                                                            break;
                                                        } else if (this.onmDoorStateLeftFront != 2 && this.onmDoorStateRightFront != 2 && this.onmDoorStateLeftRear != 2 && this.onmDoorStateRightRear != 2) {
                                                            this.doorStatusAll = true;
                                                            this.hasReprotDoor = false;
                                                            break;
                                                        }
                                                        break;
                                                    case 557862944:
                                                        this.onmDoorStateRightFront = packet.getmDoorStateRightFront();
                                                        Log.i(TAG, id + "   state:" + this.onmDoorStateRightFront + "   currentSpeed: " + this.mCurrentSpeed + " ,doorStatusAll: " + this.doorStatusAll + " ,hasReprotDoor: " + this.hasReprotDoor);
                                                        if (this.onmDoorStateRightFront == 2 && this.mCurrentSpeed > 5.0f && !this.hasReprotDoor && this.doorStatusAll) {
                                                            for (AbsBYDAutoReminderListener listener34 : this.mAbsBydAutoReminderListeners) {
                                                                listener34.onIndChanged(9);
                                                            }
                                                            this.doorStatusAll = false;
                                                            this.hasReprotDoor = true;
                                                            break;
                                                        } else if (this.onmDoorStateLeftFront != 2 && this.onmDoorStateRightFront != 2 && this.onmDoorStateLeftRear != 2 && this.onmDoorStateRightRear != 2) {
                                                            this.doorStatusAll = true;
                                                            this.hasReprotDoor = false;
                                                            break;
                                                        }
                                                        break;
                                                    case 557862945:
                                                        this.onmDoorStateLeftRear = packet.getmDoorStateLeftRear();
                                                        Log.i(TAG, id + "   state:" + this.onmDoorStateLeftRear + "   currentSpeed: " + this.mCurrentSpeed + " ,doorStatusAll: " + this.doorStatusAll + " ,hasReprotDoor: " + this.hasReprotDoor);
                                                        if (this.onmDoorStateLeftRear == 2 && this.mCurrentSpeed > 5.0f && !this.hasReprotDoor && this.doorStatusAll) {
                                                            for (AbsBYDAutoReminderListener listener35 : this.mAbsBydAutoReminderListeners) {
                                                                listener35.onIndChanged(9);
                                                            }
                                                            this.doorStatusAll = false;
                                                            this.hasReprotDoor = true;
                                                            break;
                                                        } else if (this.onmDoorStateLeftFront != 2 && this.onmDoorStateRightFront != 2 && this.onmDoorStateLeftRear != 2 && this.onmDoorStateRightRear != 2) {
                                                            this.doorStatusAll = true;
                                                            this.hasReprotDoor = false;
                                                            break;
                                                        }
                                                        break;
                                                    case 557862946:
                                                        int luggageValue = packet.getmDoorStateRightRear();
                                                        this.onmDoorStateRightRear = luggageValue;
                                                        Log.i(TAG, id + "   state:" + this.onmDoorStateRightRear + "   currentSpeed: " + this.mCurrentSpeed + " ,doorStatusAll: " + this.doorStatusAll + " ,hasReprotDoor: " + this.hasReprotDoor);
                                                        if (this.onmDoorStateRightRear == 2 && this.mCurrentSpeed > 5.0f && !this.hasReprotDoor && this.doorStatusAll) {
                                                            for (AbsBYDAutoReminderListener listener36 : this.mAbsBydAutoReminderListeners) {
                                                                listener36.onIndChanged(9);
                                                            }
                                                            this.doorStatusAll = false;
                                                            this.hasReprotDoor = true;
                                                            break;
                                                        } else if (this.onmDoorStateLeftFront != 2 && this.onmDoorStateRightFront != 2 && this.onmDoorStateLeftRear != 2 && this.onmDoorStateRightRear != 2) {
                                                            this.doorStatusAll = true;
                                                            this.hasReprotDoor = false;
                                                            break;
                                                        }
                                                        break;
                                                    case 557862947:
                                                        int hoodValue = packet.getmDoorStateLuggage();
                                                        this.stateLuggage = hoodValue;
                                                        Log.i(TAG, id + "   stateLuggage:" + this.stateLuggage);
                                                        if (this.runningState != 2 && this.stateLuggage == 2 && this.mCurrentSpeed > 5.0f) {
                                                            for (AbsBYDAutoReminderListener listener37 : this.mAbsBydAutoReminderListeners) {
                                                                listener37.onIndChanged(136);
                                                            }
                                                            this.stateLuggageStatus = this.stateLuggage;
                                                            break;
                                                        }
                                                        break;
                                                    case 557862948:
                                                        this.doorStateHood = packet.getmDoorStateHood();
                                                        Log.i(TAG, id + "   doorStateHood:" + this.doorStateHood);
                                                        if (this.doorStateHood == 2 && this.mCurrentSpeed > 5.0f) {
                                                            for (AbsBYDAutoReminderListener listener38 : this.mAbsBydAutoReminderListeners) {
                                                                listener38.onIndChanged(135);
                                                            }
                                                            this.doorStateHoodStatus = this.doorStateHood;
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

    public int getIndicatorValue() {
        Log.v(TAG, "getIndicatorValue is execute");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getIndicatorValue mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIndicatorValue();
    }

    public boolean isSatisfyReport(int value) {
        Log.v(TAG, "isSatisfyReport is execute");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_GET);
        return this.arr[value];
    }

    public void registerListener(AbsBYDAutoReminderListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoReminderListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe reminder listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_COMMON);
        if (!this.mAbsBydAutoReminderListeners.contains(listener)) {
            this.mAbsBydAutoReminderListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unregisterListener(AbsBYDAutoReminderListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_COMMON);
        if (listener == null) {
            return;
        }
        if (this.mAbsBydAutoReminderListeners.contains(listener)) {
            this.mAbsBydAutoReminderListeners.remove(listener);
        }
        unregisterConnectListener(listener);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoReminderListener listener : this.mAbsBydAutoReminderListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        throw new UnsupportedOperationException("Unimplemented method 'setForOneFeatureId'");
    }

    public boolean isRequired() {
        int i;
        int i2 = this.onIndChangedPowerFailure;
        if (i2 >= 3 && i2 <= 6 && this.onIndChangedstart != 6 && (i = this.carResult) != 3 && i != 4 && i != 5) {
            return true;
        }
        return false;
    }
}
