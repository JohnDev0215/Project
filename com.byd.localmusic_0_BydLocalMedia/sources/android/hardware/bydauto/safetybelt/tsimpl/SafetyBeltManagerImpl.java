package android.hardware.bydauto.safetybelt.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.safetybelt.AbsBYDAutoSafetyBeltListener;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.general.CarGeneralAdapterManager;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SafetyBeltManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_SafetyBeltManagerImpl";
    protected static final String TAG = "SafetyBeltManagerImpl";
    private static volatile SafetyBeltManagerImpl mSafetyBeltManagerImpl;
    private CarAdapterManager mCarAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TsManagerImpl mTsManagerImpl;
    private CarGeneralAdapterManager mTsManger;
    private final CopyOnWriteArrayList<AbsBYDAutoSafetyBeltListener> mAbsBydAutoSafetyBeltListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    private SafetyBeltManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission("android.permission.BYDAUTO_SAFETY_BELT_COMMON");
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.safetybelt.tsimpl.SafetyBeltManagerImpl.1
            public void onCarClusterBrightnessChanged(int i2) {
            }

            public void onCarGeneralBinderChanged(boolean z) {
            }

            public void onCarGeneralChanged(int i2, int i3, GeneralPacket generalPacket) {
                if (SafetyBeltManagerImpl.this.mAbsBydAutoSafetyBeltListeners.isEmpty()) {
                    return;
                }
                try {
                    SafetyBeltManagerImpl.this.safeBeltCallback(i2, i3, generalPacket);
                } catch (Exception unused) {
                    Log.e(SafetyBeltManagerImpl.TAG, "safeBeltCallback is error");
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

    /* JADX INFO: Access modifiers changed from: private */
    public void safeBeltCallback(int i, int i2, GeneralPacket generalPacket) {
        if (generalPacket == null) {
            Log.e(TAG, "generaPacket is null");
        }
        Log.i(TAG, "generaPacket propertyId:" + i);
        switch (i) {
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_MAIN /* 557860864 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it.hasNext()) {
                    it.next().onSafetyBeltStatusChanged(1, generalPacket.getSafetyBeltStatusMain());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_DEPUTY /* 557860865 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it2 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onSafetyBeltStatusChanged(2, generalPacket.getSafetyBeltStatusDeputy());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_LEFT_TWO_REAR /* 557860866 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it3 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onSafetyBeltStatusChanged(3, generalPacket.getSafetyBeltStatusLeftTwoRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_MID_TWO_REAR /* 557860867 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it4 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onSafetyBeltStatusChanged(5, generalPacket.getSafetyBeltStatusMidTwoRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_RIGHT_TWO_REAR /* 557860868 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it5 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onSafetyBeltStatusChanged(4, generalPacket.getSafetyBeltStatusRightTwoRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_LEFT_THREE_REAR /* 557860869 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it6 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onSafetyBeltStatusChanged(6, generalPacket.getSafetyBeltStatusLeftThreeRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_MID_THREE_REAR /* 557860870 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it7 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onSafetyBeltStatusChanged(8, generalPacket.getSafetyBeltStatusMidThreeRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_RIGHT_THREE_REAR /* 557860871 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it8 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onSafetyBeltStatusChanged(7, generalPacket.getSafetyBeltStatusRightThreeRear());
                }
                break;
            case SafetyBeltHalid.ID_DRIVER_BELT_STATUS_R /* 557860875 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it9 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onDriverBeltStatusChanged(generalPacket.getDriverBeltStatusR());
                }
                caseFeatureIds(4037, Integer.valueOf(generalPacket.getDriverBeltStatusR()));
                break;
            case 557860876:
                Iterator<AbsBYDAutoSafetyBeltListener> it10 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onMainSeatStateChanged(generalPacket.getMainSeatStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_LEFT, Integer.valueOf(generalPacket.getMainSeatStateR()));
                break;
        }
    }

    public static SafetyBeltManagerImpl getInstance(Context context) {
        if (mSafetyBeltManagerImpl == null) {
            synchronized (SafetyBeltManagerImpl.class) {
                if (mSafetyBeltManagerImpl == null) {
                    mSafetyBeltManagerImpl = new SafetyBeltManagerImpl(context);
                }
            }
        }
        return mSafetyBeltManagerImpl;
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener) {
        registerListener(absBYDAutoSafetyBeltListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe safebelt listener " + absBYDAutoSafetyBeltListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoSafetyBeltListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener one para mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission("android.permission.BYDAUTO_SAFETY_BELT_COMMON");
        if (!this.mAbsBydAutoSafetyBeltListeners.contains(absBYDAutoSafetyBeltListener)) {
            this.mAbsBydAutoSafetyBeltListeners.add(absBYDAutoSafetyBeltListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoSafetyBeltListener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }

    public void unregisterListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission("android.permission.BYDAUTO_SAFETY_BELT_COMMON");
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        this.mAbsBydAutoSafetyBeltListeners.remove(absBYDAutoSafetyBeltListener);
        unregisterConnectListener(absBYDAutoSafetyBeltListener);
    }

    public int getSafetyBeltStatus(int i) {
        Log.v(TAG, "getSafetyBeltStatus is execute");
        enforcePermission("android.permission.BYDAUTO_SAFETY_BELT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSafetyBeltStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMain();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusDeputy();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusLeftTwoRear();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMidTwoRear();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusRightTwoRear();
        }
        if (i == 6) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusLeftThreeRear();
        }
        if (i == 8) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMidThreeRear();
        }
        if (i == 7) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusRightThreeRear();
        }
        return 0;
    }

    public int getDriverBeltStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDriverBeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getDriverBeltStatus();
    }

    public int getMainSeatState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMainSeatState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getMainSeatState();
    }

    public int getPassengerStatus(int i) {
        Log.v(TAG, "getPassengerStatus is execute");
        enforcePermission("android.permission.BYDAUTO_SAFETY_BELT_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPassengerStatus mForTestValue is 0 execute return");
            return 0;
        }
        CarGeneralAdapterManager carGeneralAdapterManager = this.mTsManger;
        if (carGeneralAdapterManager == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return carGeneralAdapterManager.getPassengerStatus(i);
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
                    if (i == 3929) {
                        bYDAutoEventValue.intValue = getSafetyBeltStatus(2);
                        sparseArray.put(3929, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else {
                        switch (i) {
                            case 4048:
                                bYDAutoEventValue.intValue = getSafetyBeltStatus(3);
                                sparseArray.put(4048, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4049:
                                bYDAutoEventValue.intValue = getSafetyBeltStatus(5);
                                sparseArray.put(4049, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4050:
                                bYDAutoEventValue.intValue = getSafetyBeltStatus(4);
                                sparseArray.put(4050, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            default:
                                Log.v(TAG, "case fail ! please check out");
                                break;
                        }
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
}
