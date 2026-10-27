package android.hardware.bydauto.reminder.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.reminder.AbsBYDAutoReminderListener;
import android.hardware.bydauto.reminder.BYDAutoReminderDevice;
import android.provider.Settings;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.Iterator;
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
    private boolean[] arr = new boolean[150];

    public ReminderManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_COMMON);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                a.q(new StringBuilder("ReminderManagerImpl mForTestValue is"), this.mForTest, " execute return", TAG);
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.reminder.tsimpl.ReminderManagerImpl.1
            public void onAssistChanged(int i10, int i11, CarAssistPacket carAssistPacket) {
                if (ReminderManagerImpl.this.mAbsBydAutoReminderListeners.isEmpty()) {
                    return;
                }
                ReminderManagerImpl.this.assistCallback(i10, i11, carAssistPacket);
            }

            public void onBinderStateChanged(boolean z6) {
                Log.v(ReminderManagerImpl.TAG, "onBinderStateChanged is " + z6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:342:0x07d9 A[LOOP:21: B:340:0x07d3->B:342:0x07d9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0a4e A[LOOP:28: B:444:0x0a48->B:446:0x0a4e, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void assistCallback(int r18, int r19, com.ts.lib.caradapter.assist.CarAssistPacket r20) {
        /*
            Method dump skipped, instruction units count: 3314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.reminder.tsimpl.ReminderManagerImpl.assistCallback(int, int, com.ts.lib.caradapter.assist.CarAssistPacket):void");
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static ReminderManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ReminderManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ReminderManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    public int getIndicatorValue() {
        Log.v(TAG, "getIndicatorValue is execute");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getIndicatorValue mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getIndicatorValue();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public boolean isRequired() {
        int i7;
        int i10 = this.onIndChangedPowerFailure;
        return (i10 < 3 || i10 > 6 || this.onIndChangedstart == 6 || (i7 = this.carResult) == 3 || i7 == 4 || i7 == 5) ? false : true;
    }

    public boolean isSatisfyReport(int i7) {
        Log.v(TAG, "isSatisfyReport is execute");
        enforcePermission(BYDAutoReminderDevice.PERMISSION_BYDAUTO_REMINDER_GET);
        return this.arr[i7];
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoReminderListener> it = this.mAbsBydAutoReminderListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoReminderListener absBYDAutoReminderListener) {
        registerListener(absBYDAutoReminderListener, new int[0]);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe reminder listener ");
        sb.append(absBYDAutoReminderListener);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
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
