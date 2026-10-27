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
import android.hardware.bydauto_test.TestEngineManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.java_websocket.extensions.ExtensionRequestData;

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
    private TestEngineManagerImplCallback mTestCallback;
    private final CopyOnWriteArrayList<AbsBYDAutoEngineListener> mAbsBydAutoEngineListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
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

    private EngineManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestEngineManagerImplCallback();
                Log.v(TAG, "EngineManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.engine.tsimpl.EngineManagerImpl.1
            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (EngineManagerImpl.this.mAbsBydAutoEngineListeners.isEmpty()) {
                    return;
                }
                try {
                    EngineManagerImpl.this.engineCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(EngineManagerImpl.TAG, "engineCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(EngineManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    public float getEngineDisplacement() {
        Log.v(TAG, "getEngineDisplacement is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineDisplacement mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return mTsManagerImpl.getCarBodyManager().getEngineDisplacement();
    }

    public String getEngineCode() {
        Log.v(TAG, "getEngineCode is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineCode mForTestValue is" + this.mForTest + " execute return");
            return ExtensionRequestData.EMPTY_VALUE;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return ExtensionRequestData.EMPTY_VALUE;
        }
        return mTsManagerImpl.getCarBodyManager().getEngineCode();
    }

    public int getEnginePower() {
        Log.v(TAG, "getEnginePower is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnginePower mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getEnginePower();
    }

    public int getEngineSpeed() {
        Log.v(TAG, "getEngineSpeed is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineSpeed mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getEngineSpeed();
    }

    public int getEngineCoolantLevel() {
        Log.v(TAG, "getEngineCoolantLevel is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineCoolantLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getEngineCoolantLevel();
    }

    public int getOilLevel() {
        Log.v(TAG, "getOilLevel is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getOilLevel();
    }

    public int getEngineCondition() {
        Log.v(TAG, "getEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineCondition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getEngineCondition();
    }

    public int setEngineCondition(int i) {
        Log.v(TAG, "setEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(8000, i);
            Log.v(TAG, "setEngineCondition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().setEngineCondition(i);
    }

    public int getSlideRailMotorElecPercent() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSlideRailMotorElecPercent mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getSlideRailMotorElecPercent();
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        registerListener(absBYDAutoEngineListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe engine listener " + absBYDAutoEngineListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoEngineListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoEngineListener(absBYDAutoEngineListener);
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

    public void unregisterListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoEngineListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
        } else {
            if (absBYDAutoEngineListener == null) {
                return;
            }
            this.mAbsBydAutoEngineListeners.remove(absBYDAutoEngineListener);
            unregisterConnectListener(absBYDAutoEngineListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void engineCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "engineCallback propertyId:" + i);
        switch (i) {
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

    public void caseFeatureIds(int i, int i2) {
        int[] iArr = this.mFeatureIds;
        if (iArr == null || iArr.length <= 0 || this.mAbsBydAutoEngineListener == null) {
            return;
        }
        this.bydAutoEventValueListener.intValue = i2;
        Log.v(TAG, ":" + this.bydAutoEventValueListener.intValue);
        this.mAbsBydAutoEngineListener.onDataEventChanged(i, this.bydAutoEventValueListener);
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
                    if (i == 3578) {
                        bYDAutoEventValue.intValue = getOilLevel();
                        sparseArray.put(3578, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3589) {
                        bYDAutoEventValue.intValue = getEngineSpeed();
                        sparseArray.put(3589, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i != 3592) {
                        switch (i) {
                            case 3569:
                                bYDAutoEventValue.bufferDataValue = getEngineCode().getBytes();
                                sparseArray.put(3569, bYDAutoEventValue.bufferDataValue);
                                break;
                            case 3570:
                                bYDAutoEventValue.intValue = getEngineCoolantLevel();
                                sparseArray.put(3570, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3571:
                                bYDAutoEventValue.floatValue = getEngineDisplacement();
                                sparseArray.put(3571, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                        }
                    } else {
                        bYDAutoEventValue.intValue = getEngineCondition();
                        sparseArray.put(3592, Integer.valueOf(bYDAutoEventValue.intValue));
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
