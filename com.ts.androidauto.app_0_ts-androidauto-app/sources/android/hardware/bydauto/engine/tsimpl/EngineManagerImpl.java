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
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Arrays;
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
    private TestEngineManagerImplCallback mTestCallback;
    private final CopyOnWriteArrayList<AbsBYDAutoEngineListener> mAbsBydAutoEngineListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

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
            public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
                if (!EngineManagerImpl.this.mAbsBydAutoEngineListeners.isEmpty()) {
                    try {
                        EngineManagerImpl.this.engineCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(EngineManagerImpl.TAG, "engineCallback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(EngineManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
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
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
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

    public int setEngineCondition(int con) {
        Log.v(TAG, "setEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(8000, con);
            Log.v(TAG, "setEngineCondition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().setEngineCondition(con);
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

    public void registerListener(AbsBYDAutoEngineListener listener, int[] featureIds) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe engine listener " + listener + " featureIDs = " + Arrays.toString(featureIds));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoEngineListener(listener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        if (!this.mAbsBydAutoEngineListeners.contains(listener)) {
            this.mAbsBydAutoEngineListeners.add(listener);
        }
        setFeatureIds(featureIds);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoEngineListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (absBYDAutoEngineListener == null) {
            return;
        }
        this.mAbsBydAutoEngineListeners.remove(absBYDAutoEngineListener);
        unregisterConnectListener(absBYDAutoEngineListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void engineCallback(int id, int areaId, CarBodyPacket packet) {
        Log.i(TAG, "engineCallback propertyId:" + id);
        switch (id) {
            case 557851651:
                for (AbsBYDAutoEngineListener listener : this.mAbsBydAutoEngineListeners) {
                    listener.onEngineSpeedChanged(packet.getmEngineSpeedR());
                }
                break;
            case 557851652:
                for (AbsBYDAutoEngineListener listener2 : this.mAbsBydAutoEngineListeners) {
                    listener2.onEngineCoolantLevelChanged(packet.getmEngineCoolantLevelR());
                }
                break;
            case 557851654:
                for (AbsBYDAutoEngineListener listener3 : this.mAbsBydAutoEngineListeners) {
                    listener3.onEngineConditionChanged(packet.getmEngineConditionR());
                }
                break;
            case 557851655:
                for (AbsBYDAutoEngineListener listener4 : this.mAbsBydAutoEngineListeners) {
                    listener4.onOilLevelChanged(packet.getmOilLevelR());
                }
                break;
        }
    }

    public void caseFeatureIds(int featureIds, int getNums) {
        int[] iArr = this.mFeatureIds;
        if (iArr != null && iArr.length > 0 && this.mAbsBydAutoEngineListener != null) {
            this.bydAutoEventValueListener.intValue = getNums;
            Log.v(TAG, ":" + this.bydAutoEventValueListener.intValue);
            this.mAbsBydAutoEngineListener.onDataEventChanged(featureIds, this.bydAutoEventValueListener);
        }
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
                    if (eventType == 3578) {
                        bydAutoEventValue.intValue = getOilLevel();
                        sparseArray.put(3578, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3589) {
                        bydAutoEventValue.intValue = getEngineSpeed();
                        sparseArray.put(3589, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 3592) {
                        switch (eventType) {
                            case 3569:
                                bydAutoEventValue.bufferDataValue = getEngineCode().getBytes();
                                sparseArray.put(3569, bydAutoEventValue.bufferDataValue);
                                break;
                            case 3570:
                                bydAutoEventValue.intValue = getEngineCoolantLevel();
                                sparseArray.put(3570, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3571:
                                bydAutoEventValue.floatValue = getEngineDisplacement();
                                sparseArray.put(3571, Float.valueOf(bydAutoEventValue.floatValue));
                                break;
                        }
                    } else {
                        bydAutoEventValue.intValue = getEngineCondition();
                        sparseArray.put(3592, Integer.valueOf(bydAutoEventValue.intValue));
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
