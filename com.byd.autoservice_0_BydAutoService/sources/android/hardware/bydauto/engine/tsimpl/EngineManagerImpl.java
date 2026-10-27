package android.hardware.bydauto.engine.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.energy.tsimpl.EnergyManagerImpl;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import e.g;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class EngineManagerImpl extends AbsTsMyCar {
    private static final int ENGINE_COMMAND_INVALID_VALUE = 0;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_EngineManagerImpl";
    private static final String TAG = "EngineManagerImpl";
    private static volatile EngineManagerImpl mEngineManagerImpl;
    private static volatile TsManagerImpl mTsManagerImpl;
    private AbsBYDAutoEngineListener mAbsBydAutoEngineListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private g mTestCallback;
    private final CopyOnWriteArrayList<AbsBYDAutoEngineListener> mAbsBydAutoEngineListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private EngineManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i3 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i3;
            if (1 == i3) {
                this.mTestCallback = new g();
                Log.v(TAG, "EngineManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.engine.tsimpl.EngineManagerImpl.1
            public void onBinderStateChanged(boolean z3) {
                Log.v(EngineManagerImpl.TAG, "onBinderStateChanged is " + z3);
            }

            public void onBodyChanged(int i4, int i5, CarBodyPacket carBodyPacket) {
                if (EngineManagerImpl.this.mAbsBydAutoEngineListeners.isEmpty()) {
                    return;
                }
                try {
                    EngineManagerImpl.this.engineCallback(i4, i5, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(EngineManagerImpl.TAG, "engineCallback is error");
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
    public void engineCallback(int i3, int i4, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "engineCallback propertyId:" + i3);
        switch (i3) {
            case EngineHalId.ID_ENGINE_SPEEDR /* 557851651 */:
                Iterator<AbsBYDAutoEngineListener> it = this.mAbsBydAutoEngineListeners.iterator();
                while (it.hasNext()) {
                    it.next().onEngineSpeedChanged(carBodyPacket.getmEngineSpeedR());
                }
                break;
            case EngineHalId.ID_ENGINE_COOLANT_LEVELR /* 557851652 */:
                Iterator<AbsBYDAutoEngineListener> it2 = this.mAbsBydAutoEngineListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onEngineCoolantLevelChanged(carBodyPacket.getmEngineCoolantLevelR());
                }
                break;
            case EngineHalId.ID_ENGINE_CONDITIONR /* 557851654 */:
                Iterator<AbsBYDAutoEngineListener> it3 = this.mAbsBydAutoEngineListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onEngineConditionChanged(carBodyPacket.getmEngineConditionR());
                }
                break;
            case EngineHalId.ID_OIL_LEVELR /* 557851655 */:
                Iterator<AbsBYDAutoEngineListener> it4 = this.mAbsBydAutoEngineListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onOilLevelChanged(carBodyPacket.getmOilLevelR());
                }
                break;
        }
    }

    public static EngineManagerImpl getInstance(Context context) {
        if (mEngineManagerImpl == null) {
            synchronized (EnergyManagerImpl.class) {
                if (mEngineManagerImpl == null) {
                    mEngineManagerImpl = new EngineManagerImpl(context);
                }
            }
        }
        return mEngineManagerImpl;
    }

    public void caseFeatureIds(int i3, int i4) {
        int[] iArr = this.mFeatureIds;
        if (iArr == null || iArr.length <= 0 || this.mAbsBydAutoEngineListener == null) {
            return;
        }
        this.bydAutoEventValueListener.intValue = i4;
        Log.v(TAG, ":" + this.bydAutoEventValueListener.intValue);
        this.mAbsBydAutoEngineListener.onDataEventChanged(i3, this.bydAutoEventValueListener);
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i3 : iArr) {
                if (i3 > 0) {
                    if (i3 == 3578) {
                        int oilLevel = getOilLevel();
                        bYDAutoEventValue.intValue = oilLevel;
                        sparseArray.put(3578, Integer.valueOf(oilLevel));
                    } else if (i3 == 3589) {
                        int engineSpeed = getEngineSpeed();
                        bYDAutoEventValue.intValue = engineSpeed;
                        sparseArray.put(3589, Integer.valueOf(engineSpeed));
                    } else if (i3 != 3592) {
                        switch (i3) {
                            case 3569:
                                byte[] bytes = getEngineCode().getBytes();
                                bYDAutoEventValue.bufferDataValue = bytes;
                                sparseArray.put(3569, bytes);
                                break;
                            case 3570:
                                int engineCoolantLevel = getEngineCoolantLevel();
                                bYDAutoEventValue.intValue = engineCoolantLevel;
                                sparseArray.put(3570, Integer.valueOf(engineCoolantLevel));
                                break;
                            case 3571:
                                float engineDisplacement = getEngineDisplacement();
                                bYDAutoEventValue.floatValue = engineDisplacement;
                                sparseArray.put(3571, Float.valueOf(engineDisplacement));
                                break;
                        }
                    } else {
                        int engineCondition = getEngineCondition();
                        bYDAutoEventValue.intValue = engineCondition;
                        sparseArray.put(3592, Integer.valueOf(engineCondition));
                    }
                    int i4 = 0;
                    for (int i5 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i4] = ((Integer) sparseArray.get(i5)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i4++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public String getEngineCode() {
        Log.v(TAG, "getEngineCode is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getEngineCode();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return "";
        }
        Log.v(TAG, "getEngineCode mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public int getEngineCondition() {
        Log.v(TAG, "getEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getEngineCondition();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "getEngineCondition mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getEngineCoolantLevel() {
        Log.v(TAG, "getEngineCoolantLevel is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getEngineCoolantLevel();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "getEngineCoolantLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public float getEngineDisplacement() {
        Log.v(TAG, "getEngineDisplacement is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getEngineDisplacement();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        Log.v(TAG, "getEngineDisplacement mForTestValue is" + this.mForTest + " execute return");
        return 0.0f;
    }

    public int getEnginePower() {
        Log.v(TAG, "getEnginePower is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getEnginePower();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "getEnginePower mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getEngineSpeed() {
        Log.v(TAG, "getEngineSpeed is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getEngineSpeed();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "getEngineSpeed mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getOilLevel() {
        Log.v(TAG, "getOilLevel is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getOilLevel();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "getOilLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSlideRailMotorElecPercent() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSlideRailMotorElecPercent mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getSlideRailMotorElecPercent();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        registerListener(absBYDAutoEngineListener, new int[0]);
    }

    public int setEngineCondition(int i3) {
        Log.v(TAG, "setEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_SET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().setEngineCondition(i3);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.b(BYDAutoEngineDevice.ENGINE_SPEED_MAX, i3);
        Log.v(TAG, "setEngineCondition mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i3, Object obj) {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        if (1 != this.mForTest) {
            if (absBYDAutoEngineListener == null) {
                return;
            }
            this.mAbsBydAutoEngineListeners.remove(absBYDAutoEngineListener);
            unregisterConnectListener(absBYDAutoEngineListener);
            return;
        }
        this.mTestCallback.c(absBYDAutoEngineListener);
        Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe engine listener " + absBYDAutoEngineListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoEngineListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.d(absBYDAutoEngineListener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        if (!this.mAbsBydAutoEngineListeners.contains(absBYDAutoEngineListener)) {
            this.mAbsBydAutoEngineListeners.add(absBYDAutoEngineListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoEngineListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }
}
