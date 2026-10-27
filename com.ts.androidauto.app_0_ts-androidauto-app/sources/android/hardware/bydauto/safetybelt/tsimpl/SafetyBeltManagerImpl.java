package android.hardware.bydauto.safetybelt.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.safetybelt.AbsBYDAutoSafetyBeltListener;
import android.hardware.bydauto.safetybelt.BYDAutoSafetyBeltDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.general.CarGeneralAdapterManager;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import java.util.Arrays;
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

    private SafetyBeltManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.safetybelt.tsimpl.SafetyBeltManagerImpl.1
            public void onCarGeneralChanged(int id, int areaId, GeneralPacket packet) {
                if (!SafetyBeltManagerImpl.this.mAbsBydAutoSafetyBeltListeners.isEmpty()) {
                    try {
                        SafetyBeltManagerImpl.this.safeBeltCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(SafetyBeltManagerImpl.TAG, "safeBeltCallback is error");
                    }
                }
            }

            public void onCarGeneralBinderChanged(boolean binderState) {
            }

            public void onCarClusterBrightnessChanged(int brightness) {
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeBeltCallback(int id, int areaId, GeneralPacket generaPacket) {
        if (generaPacket == null) {
            Log.e(TAG, "generaPacket is null");
        }
        Log.i(TAG, "generaPacket propertyId:" + id);
        switch (id) {
            case 557860864:
                for (AbsBYDAutoSafetyBeltListener listener : this.mAbsBydAutoSafetyBeltListeners) {
                    listener.onSafetyBeltStatusChanged(1, generaPacket.getSafetyBeltStatusMain());
                }
                break;
            case 557860865:
                for (AbsBYDAutoSafetyBeltListener listener2 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener2.onSafetyBeltStatusChanged(2, generaPacket.getSafetyBeltStatusDeputy());
                }
                break;
            case 557860866:
                for (AbsBYDAutoSafetyBeltListener listener3 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener3.onSafetyBeltStatusChanged(3, generaPacket.getSafetyBeltStatusLeftTwoRear());
                }
                break;
            case 557860867:
                for (AbsBYDAutoSafetyBeltListener listener4 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener4.onSafetyBeltStatusChanged(5, generaPacket.getSafetyBeltStatusMidTwoRear());
                }
                break;
            case 557860868:
                for (AbsBYDAutoSafetyBeltListener listener5 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener5.onSafetyBeltStatusChanged(4, generaPacket.getSafetyBeltStatusRightTwoRear());
                }
                break;
            case 557860869:
                for (AbsBYDAutoSafetyBeltListener listener6 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener6.onSafetyBeltStatusChanged(6, generaPacket.getSafetyBeltStatusLeftThreeRear());
                }
                break;
            case 557860870:
                for (AbsBYDAutoSafetyBeltListener listener7 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener7.onSafetyBeltStatusChanged(8, generaPacket.getSafetyBeltStatusMidThreeRear());
                }
                break;
            case 557860871:
                for (AbsBYDAutoSafetyBeltListener listener8 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener8.onSafetyBeltStatusChanged(7, generaPacket.getSafetyBeltStatusRightThreeRear());
                }
                break;
            case 557860875:
                for (AbsBYDAutoSafetyBeltListener listener9 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener9.onDriverBeltStatusChanged(generaPacket.getDriverBeltStatusR());
                }
                caseFeatureIds(4037, Integer.valueOf(generaPacket.getDriverBeltStatusR()));
                break;
            case 557860876:
                for (AbsBYDAutoSafetyBeltListener listener10 : this.mAbsBydAutoSafetyBeltListeners) {
                    listener10.onMainSeatStateChanged(generaPacket.getMainSeatStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_LEFT, Integer.valueOf(generaPacket.getMainSeatStateR()));
                break;
        }
    }

    public static SafetyBeltManagerImpl getInstance(Context con) {
        if (mSafetyBeltManagerImpl == null) {
            synchronized (SafetyBeltManagerImpl.class) {
                if (mSafetyBeltManagerImpl == null) {
                    mSafetyBeltManagerImpl = new SafetyBeltManagerImpl(con);
                }
            }
        }
        return mSafetyBeltManagerImpl;
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe safebelt listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener one para mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_COMMON);
        if (!this.mAbsBydAutoSafetyBeltListeners.contains(listener)) {
            this.mAbsBydAutoSafetyBeltListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }

    public void unregisterListener(AbsBYDAutoSafetyBeltListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_COMMON);
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        this.mAbsBydAutoSafetyBeltListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getSafetyBeltStatus(int area) {
        Log.v(TAG, "getSafetyBeltStatus is execute");
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSafetyBeltStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (area == 1) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMain();
        }
        if (area == 2) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusDeputy();
        }
        if (area == 3) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusLeftTwoRear();
        }
        if (area == 5) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMidTwoRear();
        }
        if (area == 4) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusRightTwoRear();
        }
        if (area == 6) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusLeftThreeRear();
        }
        if (area == 8) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMidThreeRear();
        }
        if (area == 7) {
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

    public int getPassengerStatus(int area) {
        Log.v(TAG, "getPassengerStatus is execute");
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getPassengerStatus mForTestValue is 0 execute return");
            return 0;
        }
        CarGeneralAdapterManager carGeneralAdapterManager = this.mTsManger;
        if (carGeneralAdapterManager == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return carGeneralAdapterManager.getPassengerStatus(area);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 3929) {
                        bydAutoEventValue.intValue = getSafetyBeltStatus(2);
                        sparseArray.put(3929, Integer.valueOf(bydAutoEventValue.intValue));
                    } else {
                        switch (eventType) {
                            case 4048:
                                bydAutoEventValue.intValue = getSafetyBeltStatus(3);
                                sparseArray.put(4048, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 4049:
                                bydAutoEventValue.intValue = getSafetyBeltStatus(5);
                                sparseArray.put(4049, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 4050:
                                bydAutoEventValue.intValue = getSafetyBeltStatus(4);
                                sparseArray.put(4050, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            default:
                                Log.v(TAG, "case fail ! please check out");
                                break;
                        }
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
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
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        return 0;
    }
}
