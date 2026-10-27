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
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
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
    public static final String TAG = "ReminderManagerImpl";
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

    public ReminderManagerImpl(Context context) {
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
            public void onAssistChanged(int i2, int i3, CarAssistPacket carAssistPacket) {
                if (ReminderManagerImpl.this.mAbsBydAutoReminderListeners.isEmpty()) {
                    return;
                }
                try {
                    ReminderManagerImpl.this.assistCallback(i2, i3, carAssistPacket);
                } catch (Exception unused) {
                    Log.e(ReminderManagerImpl.TAG, "assistCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(ReminderManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:365:0x090a A[LOOP:23: B:363:0x0904->B:365:0x090a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0bd2 A[LOOP:30: B:467:0x0bcc->B:469:0x0bd2, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void assistCallback(int r18, int r19, com.ts.lib.caradapter.assist.CarAssistPacket r20) {
        /*
            Method dump skipped, instruction units count: 3756
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.reminder.tsimpl.ReminderManagerImpl.assistCallback(int, int, com.ts.lib.caradapter.assist.CarAssistPacket):void");
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

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

    public int getIndicatorValue() {
        Log.v(TAG, "getIndicatorValue is execute");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
                return this.mTsManagerImpl.getCarAssistAdapterManager().getIndicatorValue();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getIndicatorValue mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public boolean isRequired() {
        int i;
        int i2 = this.onIndChangedPowerFailure;
        return (i2 < 3 || i2 > 6 || this.onIndChangedstart == 6 || (i = this.carResult) == 3 || i == 4 || i == 5) ? false : true;
    }

    public boolean isSatisfyReport(int i) {
        Log.v(TAG, "isSatisfyReport is execute");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_GET);
        return this.arr[i];
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoReminderListener> it = this.mAbsBydAutoReminderListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoReminderListener absBYDAutoReminderListener) {
        registerListener(absBYDAutoReminderListener, new int[0]);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'setForOneFeatureId'");
    }

    public void unregisterListener(AbsBYDAutoReminderListener absBYDAutoReminderListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_COMMON);
        if (absBYDAutoReminderListener == null) {
            return;
        }
        if (this.mAbsBydAutoReminderListeners.contains(absBYDAutoReminderListener)) {
            this.mAbsBydAutoReminderListeners.remove(absBYDAutoReminderListener);
        }
        unregisterConnectListener(absBYDAutoReminderListener);
    }

    public void registerListener(AbsBYDAutoReminderListener absBYDAutoReminderListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe reminder listener " + absBYDAutoReminderListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoReminderListener == null) {
            return;
        }
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_COMMON);
        if (!this.mAbsBydAutoReminderListeners.contains(absBYDAutoReminderListener)) {
            this.mAbsBydAutoReminderListeners.add(absBYDAutoReminderListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoReminderListener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }
}
