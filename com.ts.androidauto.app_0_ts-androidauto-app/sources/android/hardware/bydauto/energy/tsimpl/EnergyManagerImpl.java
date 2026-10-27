package android.hardware.bydauto.energy.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto_test.TestEngergyManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class EnergyManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_EnergyManagerImpl";
    private static final String TAG = "EnergyManagerImpl";
    private static volatile EnergyManagerImpl mEnergyManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TestEngergyManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoEnergyListener> mAbsBydAutoEnergyListeners = new CopyOnWriteArrayList<>();
    private boolean mAssistInit = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    public static EnergyManagerImpl getInstance(Context context) {
        if (mEnergyManagerImpl == null) {
            synchronized (EnergyManagerImpl.class) {
                if (mEnergyManagerImpl == null) {
                    mEnergyManagerImpl = new EnergyManagerImpl(context);
                }
            }
        }
        return mEnergyManagerImpl;
    }

    private EnergyManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "EnergyManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new TestEngergyManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.energy.tsimpl.EnergyManagerImpl.1
            public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
                if (!EnergyManagerImpl.this.mAbsBydAutoEnergyListeners.isEmpty()) {
                    try {
                        EnergyManagerImpl.this.processCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(EnergyManagerImpl.TAG, "processCallback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(EnergyManagerImpl.TAG, "onBinderStateChanged" + binderState);
            }
        });
    }

    public int getEnergyMode() {
        Log.v(TAG, "getEnergyMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyMode();
    }

    public int getOperationMode() {
        Log.v(TAG, "getOperationMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOperationMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getOperationMode();
    }

    public int getPowerGenerationState() {
        Log.v(TAG, "getPowerGenerationState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPowerGenerationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getPowerGenerationState();
    }

    public int getPowerGenerationValue() {
        Log.v(TAG, "getPowerGenerationValue is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPowerGenerationValue mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getPowerGenerationValue();
    }

    public int getRoadSurfaceMode() {
        Log.v(TAG, "getRoadSurfaceMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getRoadSurfaceMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRoadSurfaceMode();
    }

    public int getEPedalState() {
        Log.v(TAG, "getEPedalState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPedalState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEPedalState();
    }

    public int setEPedalState(int value) {
        Log.v(TAG, "setEPedalState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(6000, value);
            Log.v(TAG, "setEPedalState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setEPedalState(value);
    }

    public int getElectricShowMode() {
        Log.v(TAG, "getElectricShowMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getElectricShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getElectricShowMode();
    }

    public int setElectricShowMode(int value) {
        Log.v(TAG, "setElectricShowMode is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_ELECTRIC_SHOW_MODE, value);
            Log.v(TAG, "setElectricShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setElectricShowMode(value);
    }

    public int getMandatoryElectricState() {
        Log.v(TAG, "getMandatoryElectricState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getMandatoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMandatoryElectricState();
    }

    public int setMandatoryElectricState(int value) {
        Log.v(TAG, "setMandatoryElectricState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MANDATORY_ELECTRIC_STATE, value);
            Log.v(TAG, "setMandatoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMandatoryElectricState(value);
    }

    public int getEnergyCurve() {
        Log.v(TAG, "getEnergyCurve is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyCurve mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyCurve();
    }

    public int getDriveDistanceShowMode() {
        Log.v(TAG, "getDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDistanceShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDriveDistanceShowMode();
    }

    public int setDriveDistanceShowMode(int value) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_DRIVE_DISTANCE_SHOW_MODE, value);
            Log.v(TAG, "setDriveDistanceShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(value);
    }

    public int getDriveDistanceShowStandard() {
        Log.v(TAG, "getDriveDistanceShowStandard is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDistanceShowStandard mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDriveDistanceShowStandard();
    }

    public int setDriveDistanceShowStandard(int value) {
        Log.v(TAG, "setDriveDistanceShowStandard is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_DRIVE_DISTANCE_SHOW_STANDARD, value);
            Log.v(TAG, "setDriveDistanceShowStandard mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowStandard(value);
    }

    public int getMemorySportState() {
        Log.v(TAG, "getMemorySportState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getMemorySportState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMemorySportState();
    }

    public int setMemorySportState(int value) {
        Log.v(TAG, "setMemorySportState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_SPORT_STATE, value);
            Log.v(TAG, "setMemorySportState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMemorySportState(value);
    }

    public int getMemoryMaxEvState() {
        Log.v(TAG, "getMemoryMaxEvState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getMemoryMaxEvState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMemoryMaxEvState();
    }

    public int setMemoryMaxEvState(int value) {
        Log.v(TAG, "setMemoryMaxEvState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_MAX_EV_STATE, value);
            Log.v(TAG, "setMemoryMaxEvState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMemoryMaxEvState(value);
    }

    @Deprecated
    public int getEnergyFeedback() {
        Log.v(TAG, "getEnergyFeedback is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyFeedback mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyFeedback();
    }

    @Deprecated
    public int setEnergyFeedback(int value) {
        Log.v(TAG, "setEnergyFeedback is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_ENERGY_FEEDBACK, value);
            Log.v(TAG, "setEnergyFeedback mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setEnergyFeedback(value);
    }

    public int getSocTarget() {
        Log.v(TAG, "getSocTarget is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSocTarget mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSocTarget();
    }

    public int getPreSaleModelState() {
        Log.v(TAG, "getPreSaleModelState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPreSaleModelState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getPreSaleModelState();
    }

    public int setPreSaleModelState(int state) {
        Log.v(TAG, "setPreSaleModelState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_PRE_SALE_MODEL_STATE, state);
            Log.v(TAG, "setPreSaleModelState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setPreSaleModelState(state);
    }

    public int setSocTarget(int value) {
        Log.v(TAG, "setSocTarget is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_S_O_C_TARGET, value);
            Log.v(TAG, "setSocTarget mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSocTarget(value);
    }

    public int getMemoryElectricState() {
        Log.v(TAG, "getMemoryElectricState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getMemoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMemoryElectricState();
    }

    public int setMemoryElectricState(int value) {
        Log.v(TAG, "setMemoryElectricState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_ELECTRIC_STATE, value);
            Log.v(TAG, "setMemoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMemoryElectricState(value);
    }

    public float[] getEnergyElectricCurveX(int interval, int maxValue) {
        Log.v(TAG, "getEnergyElectricCurveX is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyElectricCurveX mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyElectricCurveX(interval, maxValue);
    }

    public float[] getEnergyElectricCurveY(int interval, int maxValue) {
        Log.v(TAG, "getEnergyElectricCurveY is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyElectricCurveY mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyElectricCurveY(interval, maxValue);
    }

    public float[] getEnergyOilCurveX(int interval, int maxValue) {
        Log.v(TAG, "getEnergyOilCurveX is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyOilCurveX mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyOilCurveX(interval, maxValue);
    }

    public float[] getEnergyOilCurveY(int interval, int maxValue) {
        Log.v(TAG, "getEnergyOilCurveY is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyOilCurveY mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyOilCurveY(interval, maxValue);
    }

    public int resetCurveData(int type) {
        Log.v(TAG, "resetCurveData is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(6011, type);
            Log.v(TAG, "resetCurveData mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().resetCurveData(type);
    }

    public void registerListener(AbsBYDAutoEnergyListener absBYDAutoDoorLockListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe energy listener " + absBYDAutoDoorLockListener);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoEnergyListener(absBYDAutoDoorLockListener);
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        if (!this.mAbsBydAutoEnergyListeners.contains(absBYDAutoDoorLockListener)) {
            this.mAbsBydAutoEnergyListeners.add(absBYDAutoDoorLockListener);
        }
        registerConnectListener(this.mContext, absBYDAutoDoorLockListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoEnergyListener absBYDAutoDoorLockListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoDoorLockListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.mAbsBydAutoEnergyListeners.remove(absBYDAutoDoorLockListener);
        unregisterConnectListener(absBYDAutoDoorLockListener);
    }

    public int getOilPointFlag() {
        Log.v(TAG, "getOilPointFlag is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilPointFlag mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getOilPointFlag();
    }

    public int getElectricPointFlag() {
        Log.v(TAG, "getElectricPointFlag is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getElectricPointFlag mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getElectricPointFlag();
    }

    public float getLast200mOilCon() {
        Log.v(TAG, "getLast200MOilCon is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getLast200MOilCon mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLast200mOilCon();
    }

    public float getLast200mElectricCon() {
        Log.v(TAG, "getLast200MElectricCon is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getLast200MElectricCon mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLast200mElectricCon();
    }

    public float getLast50KmOilConAvg() {
        Log.v(TAG, "getLast50KMOilConAvg is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getLast50KMOilConAvg mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLast50KmOilConAvg();
    }

    public float getLast50KmElectricConAvg() {
        Log.v(TAG, "getLast50KMElectricConAvg is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getLast50KMElectricConAvg mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLast50KmElectricConAvg();
    }

    public int getEnergyState() {
        Log.v(TAG, "getEnergyState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyState();
    }

    public int getHevElectricPointFlag() {
        Log.v(TAG, "getHevElectricPointFlag is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getHevElectricPointFlag mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getHevElectricPointFlag();
    }

    public float getHevLast200MElectricCon() {
        Log.v(TAG, "getHevLast200MElectricCon is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getHevLast200MElectricCon mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getHevLast200MElectricCon();
    }

    public int getOperationModeNormal() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getOperationModeNormal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getOperationModeNormal();
    }

    public int setOperationMode(int value) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setOperationMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setOperationMode(value);
    }

    public int getRoadSurfaceModeNormal() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRoadSurfaceModeNormal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRoadSurfaceModeNormal();
    }

    public int setiTacpState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setiTacpState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setiTacpState(state);
    }

    public int getiTacpState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacpState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getiTacpState();
    }

    public int getiTacpAllowTag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacpAllowTag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getiTacpAllowTag();
    }

    public int getChangePHint() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getChangePHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getChangePHint();
    }

    public int getiTacExitTag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacExitTag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getiTacExitTag();
    }

    public int getiTacMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getiTacMode();
    }

    public int setiTacMode(int value) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setiTacMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setiTacMode(value);
    }

    public int getChargePortCoverTip() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getChargePortCoverTip mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getChargePortCoverTip();
    }

    public int setChargePortCoverHint(int value) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setChargePortCoverHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setChargePortCoverHint(value);
    }

    public int setConfirmChargePortCover(int value) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setConfirmChargePortCover mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setConfirmChargePortCover(value);
    }

    public int getTripDrivePercent() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripDrivePercent mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripDrivePercent();
    }

    public int getTripAcPercent() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripAcPercent mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripAcPercent();
    }

    public int getTripElecAppliancePercent() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripElecAppliancePercent mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripElecAppliancePercent();
    }

    public int getTripOtherPercent() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripOtherPercent mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripOtherPercent();
    }

    public int getTripPointFlag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripPointFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripPointFlag();
    }

    public int getTripDrivePercent50km() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripDrivePercent50km mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripDrivePercent50km();
    }

    public int getTripAcPercent50km() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripAcPercent50km mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripAcPercent50km();
    }

    public int getTripElecAppliancePercent50km() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripElecAppliancePercent50km mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripElecAppliancePercent50km();
    }

    public int getTripOtherPercent50km() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripOtherPercent50km mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripOtherPercent50km();
    }

    public float getTripOilConAvg() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripOilConAvg mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripOilConAvg();
    }

    public float getTripEnergyConAvg() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripEnergyConAvg mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripEnergyConAvg();
    }

    public float getYEnergyCon() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getYEnergyCon mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getYEnergyCon();
    }

    public float getTripElecConAvg() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripElecConAvg mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripElecConAvg();
    }

    public int getYEnergyConLimit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getYEnergyConLimit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getYEnergyConLimit();
    }

    public float getTripLast50kmElectricConAvg() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripLast50kmElectricConAvg mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripLast50kmElectricConAvg();
    }

    public float getTripLast50kmOilConAvg() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripLast50kmOilConAvg mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripLast50kmOilConAvg();
    }

    public float getTripLast50kmFuelConAvg() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTripLast50kmFuelConAvg mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTripLast50kmFuelConAvg();
    }

    public int getRollBenchModeStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRollBenchModeStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRollBenchModeStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCallback(int id, int areaId, CarBodyPacket packet) {
        Log.i(TAG, "carBodyPacket propertyId:" + id);
        switch (id) {
            case 557847552:
                for (AbsBYDAutoEnergyListener listener : this.mAbsBydAutoEnergyListeners) {
                    listener.onOperationModeChanged(packet.getmOperationMode());
                }
                caseFeatureIds(2448, Integer.valueOf(packet.getmOperationMode()));
                break;
            case 557847553:
                for (AbsBYDAutoEnergyListener listener2 : this.mAbsBydAutoEnergyListeners) {
                    listener2.onRoadSurfaceChanged(packet.getmRoadSurfaceMode());
                }
                break;
            case 557847554:
                for (AbsBYDAutoEnergyListener listener3 : this.mAbsBydAutoEnergyListeners) {
                    listener3.onPowerGenerationStateChanged(packet.getmPowerGenerationState());
                }
                break;
            case 557847555:
                for (AbsBYDAutoEnergyListener listener4 : this.mAbsBydAutoEnergyListeners) {
                    listener4.onPowerGenerationValueChanged(packet.getmPowerGenerationValue());
                }
                break;
            case 557847556:
                for (AbsBYDAutoEnergyListener listener5 : this.mAbsBydAutoEnergyListeners) {
                    listener5.onEnergyModeChanged(packet.getmEnergyMode());
                }
                break;
            default:
                switch (id) {
                    case 557847558:
                        for (AbsBYDAutoEnergyListener listener6 : this.mAbsBydAutoEnergyListeners) {
                            listener6.onDriveDistanceShowModeChanged(packet.getmDriveDistanceShowModeR());
                        }
                        break;
                    case 557847563:
                        for (AbsBYDAutoEnergyListener listener7 : this.mAbsBydAutoEnergyListeners) {
                            listener7.onEPedalStateChanged(packet.getmEPedalStateR());
                        }
                        break;
                    case 557847565:
                        for (AbsBYDAutoEnergyListener listener8 : this.mAbsBydAutoEnergyListeners) {
                            listener8.onElectricShowModeChanged(packet.getmElectricShowModeR());
                        }
                        break;
                    case 557847567:
                        for (AbsBYDAutoEnergyListener listener9 : this.mAbsBydAutoEnergyListeners) {
                            listener9.onMandatoryElectricStateChanged(packet.getmMandatoryElectricStateR());
                        }
                        break;
                    case 557847569:
                        for (AbsBYDAutoEnergyListener listener10 : this.mAbsBydAutoEnergyListeners) {
                            listener10.onDriveDistanceShowStandardChanged(packet.getmDriveDistanceShowStandardR());
                        }
                        break;
                    case 557847574:
                        for (AbsBYDAutoEnergyListener listener11 : this.mAbsBydAutoEnergyListeners) {
                            listener11.onMemoryMaxEvStateChanged(packet.getmMemoryMaxEvStateR());
                        }
                        break;
                    case 557847576:
                        for (AbsBYDAutoEnergyListener listener12 : this.mAbsBydAutoEnergyListeners) {
                            listener12.onMemoryElectricStateChanged(packet.getmMemoryElectricStateR());
                        }
                        break;
                    case 557847578:
                        for (AbsBYDAutoEnergyListener listener13 : this.mAbsBydAutoEnergyListeners) {
                            listener13.onPreSaleModelStateChanged(packet.getmPreSaleModelStateR());
                        }
                        break;
                    case 557847590:
                        for (AbsBYDAutoEnergyListener listener14 : this.mAbsBydAutoEnergyListeners) {
                            listener14.onOperationModeNormalChanged(packet.getOperationModeNormalR());
                        }
                        caseFeatureIds(2450, Integer.valueOf(packet.getOperationModeNormalR()));
                        break;
                    case 557847592:
                        for (AbsBYDAutoEnergyListener listener15 : this.mAbsBydAutoEnergyListeners) {
                            listener15.onRoadSurfaceModeNormalChanged(packet.getRoadSurfaceModeNormalR());
                        }
                        caseFeatureIds(2454, Integer.valueOf(packet.getRoadSurfaceModeNormalR()));
                        break;
                    case 557847594:
                        for (AbsBYDAutoEnergyListener listener16 : this.mAbsBydAutoEnergyListeners) {
                            listener16.oniTACStateChanged(packet.getiTacpStateR());
                        }
                        break;
                    case 557847601:
                        for (AbsBYDAutoEnergyListener listener17 : this.mAbsBydAutoEnergyListeners) {
                            listener17.onChargePortCoverHintChanged(packet.getChargePortCoverTipR());
                        }
                        break;
                    case 557847617:
                        for (AbsBYDAutoEnergyListener listener18 : this.mAbsBydAutoEnergyListeners) {
                            listener18.onYEnergyConLimitChanged(packet.getyEnergyConLimitR());
                        }
                        break;
                    case 557860387:
                        for (AbsBYDAutoEnergyListener listener19 : this.mAbsBydAutoEnergyListeners) {
                            listener19.onRollBenchModeStatusChanged(packet.getRollBenchModeStatusR());
                        }
                        break;
                    case 559944740:
                        for (AbsBYDAutoEnergyListener listener20 : this.mAbsBydAutoEnergyListeners) {
                            listener20.onHEVLast200MElectricConChanged(packet.getHevLast200MElectricConR());
                        }
                        break;
                    default:
                        switch (id) {
                            case 557847571:
                                for (AbsBYDAutoEnergyListener listener21 : this.mAbsBydAutoEnergyListeners) {
                                    listener21.onResetCurveDataChanged(0, packet.getmResetCurveData());
                                }
                                break;
                            case 557847572:
                                for (AbsBYDAutoEnergyListener listener22 : this.mAbsBydAutoEnergyListeners) {
                                    listener22.onMemorySportStateChanged(packet.getmMemorySportStateR());
                                }
                                break;
                            default:
                                switch (id) {
                                    case 557847580:
                                        for (AbsBYDAutoEnergyListener listener23 : this.mAbsBydAutoEnergyListeners) {
                                            listener23.onOilPointFlagChanged(packet.getOilPointFlag());
                                        }
                                        break;
                                    case 557847581:
                                        for (AbsBYDAutoEnergyListener listener24 : this.mAbsBydAutoEnergyListeners) {
                                            listener24.onElectricPointFlagChanged(packet.getElectricPointFlag());
                                        }
                                        break;
                                    default:
                                        switch (id) {
                                            case 557847586:
                                                for (AbsBYDAutoEnergyListener listener25 : this.mAbsBydAutoEnergyListeners) {
                                                    listener25.onEnergyStateChanged(packet.getEnergyStateR());
                                                }
                                                break;
                                            case 557847587:
                                                for (AbsBYDAutoEnergyListener listener26 : this.mAbsBydAutoEnergyListeners) {
                                                    listener26.onHEVElectricPointFlagChanged(packet.getHevElectricPointFlagR());
                                                }
                                                break;
                                            default:
                                                switch (id) {
                                                    case 557847596:
                                                        for (AbsBYDAutoEnergyListener listener27 : this.mAbsBydAutoEnergyListeners) {
                                                            listener27.oniTACAllowTagChanged(packet.getiTacpAllowTagR());
                                                        }
                                                        break;
                                                    case 557847597:
                                                        for (AbsBYDAutoEnergyListener listener28 : this.mAbsBydAutoEnergyListeners) {
                                                            listener28.onChangePHintChanged(packet.getChangePHintR());
                                                        }
                                                        break;
                                                    case 557847598:
                                                        for (AbsBYDAutoEnergyListener listener29 : this.mAbsBydAutoEnergyListeners) {
                                                            listener29.oniTACExitTagChanged(packet.getiTacExitTagR());
                                                        }
                                                        break;
                                                    case 557847599:
                                                        for (AbsBYDAutoEnergyListener listener30 : this.mAbsBydAutoEnergyListeners) {
                                                            listener30.oniTACModeChanged(packet.getiTacModeR());
                                                        }
                                                        break;
                                                    default:
                                                        switch (id) {
                                                            case 557847604:
                                                                for (AbsBYDAutoEnergyListener listener31 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener31.onTripDrivePercentChanged(packet.getTripDrivePercentR());
                                                                }
                                                                break;
                                                            case 557847605:
                                                                for (AbsBYDAutoEnergyListener listener32 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener32.onTripACPercentChanged(packet.getTripAcPercentR());
                                                                }
                                                                break;
                                                            case 557847606:
                                                                for (AbsBYDAutoEnergyListener listener33 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener33.onTripElecAppliancePercentChanged(packet.getTripElecAppliancePercentR());
                                                                }
                                                                break;
                                                            case 557847607:
                                                                for (AbsBYDAutoEnergyListener listener34 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener34.onTripOtherPercentChanged(packet.getTripOtherPercentR());
                                                                }
                                                                break;
                                                            case 557847608:
                                                                for (AbsBYDAutoEnergyListener listener35 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener35.onTripPointFlagChanged(packet.getTripPointFlagR());
                                                                }
                                                                break;
                                                            case 557847609:
                                                                for (AbsBYDAutoEnergyListener listener36 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener36.onTripDrivePercent50KMChanged(packet.getTripDrivePercent50kmr());
                                                                }
                                                                break;
                                                            case 557847610:
                                                                for (AbsBYDAutoEnergyListener listener37 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener37.onTripACPercent50KMChanged(packet.getTripAcPercent50kmr());
                                                                }
                                                                break;
                                                            case 557847611:
                                                                for (AbsBYDAutoEnergyListener listener38 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener38.onTripElecAppliancePercent50KMChanged(packet.getTripElecAppliancePercent50kmr());
                                                                }
                                                                break;
                                                            case 557847612:
                                                                for (AbsBYDAutoEnergyListener listener39 : this.mAbsBydAutoEnergyListeners) {
                                                                    listener39.onTripOtherPercent50KMChanged(packet.getTripOtherPercent50kmr());
                                                                }
                                                                break;
                                                            default:
                                                                switch (id) {
                                                                    case 559944734:
                                                                        for (AbsBYDAutoEnergyListener listener40 : this.mAbsBydAutoEnergyListeners) {
                                                                            listener40.onLast200MOilConChanged(packet.getLast200MOilCon());
                                                                        }
                                                                        break;
                                                                    case 559944735:
                                                                        for (AbsBYDAutoEnergyListener listener41 : this.mAbsBydAutoEnergyListeners) {
                                                                            listener41.onLast200MElectricConChanged(packet.getLast200MElectricCon());
                                                                        }
                                                                        break;
                                                                    case 559944736:
                                                                        for (AbsBYDAutoEnergyListener listener42 : this.mAbsBydAutoEnergyListeners) {
                                                                            listener42.onLast50KMOilConAvgChanged(packet.getLast50KmOilConAvg());
                                                                        }
                                                                        break;
                                                                    case 559944737:
                                                                        for (AbsBYDAutoEnergyListener listener43 : this.mAbsBydAutoEnergyListeners) {
                                                                            listener43.onLast50KMElectricConAvgChanged(packet.getLast50KmElectricConAvg());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (id) {
                                                                            case 559944765:
                                                                                for (AbsBYDAutoEnergyListener listener44 : this.mAbsBydAutoEnergyListeners) {
                                                                                    listener44.onTripOilConAvgChanged(packet.getTripOilConAvgR());
                                                                                }
                                                                                break;
                                                                            case 559944766:
                                                                                for (AbsBYDAutoEnergyListener listener45 : this.mAbsBydAutoEnergyListeners) {
                                                                                    listener45.onTripEnergyConAvgChanged(packet.getTripEnergyConAvgR());
                                                                                }
                                                                                break;
                                                                            case 559944767:
                                                                                for (AbsBYDAutoEnergyListener listener46 : this.mAbsBydAutoEnergyListeners) {
                                                                                    listener46.onYEnergyConChanged(packet.getyEnergyConR());
                                                                                }
                                                                                break;
                                                                            case 559944768:
                                                                                for (AbsBYDAutoEnergyListener listener47 : this.mAbsBydAutoEnergyListeners) {
                                                                                    listener47.onTripElecConAvgChanged(packet.getTripElecConAvgR());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (id) {
                                                                                    case 559944770:
                                                                                        for (AbsBYDAutoEnergyListener listener48 : this.mAbsBydAutoEnergyListeners) {
                                                                                            listener48.onTripLast50KMElectricConAvgChanged(packet.getTripLast50kmElectricConAvgR());
                                                                                        }
                                                                                        break;
                                                                                    case 559944771:
                                                                                        for (AbsBYDAutoEnergyListener listener49 : this.mAbsBydAutoEnergyListeners) {
                                                                                            listener49.onTripLast50KMOilConAvgChanged(packet.getTripLast50kmOilConAvgR());
                                                                                        }
                                                                                        break;
                                                                                    case 559944772:
                                                                                        for (AbsBYDAutoEnergyListener listener50 : this.mAbsBydAutoEnergyListeners) {
                                                                                            listener50.onTripLast50KMFuelConAvgChanged(packet.getTripLast50kmFuelConAvgR());
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

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 244:
                            bydAutoEventValue.floatValue = getLast200mElectricCon();
                            sparseArray.put(244, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 245:
                            bydAutoEventValue.intValue = getElectricPointFlag();
                            sparseArray.put(245, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 246:
                            bydAutoEventValue.intValue = getHevElectricPointFlag();
                            sparseArray.put(246, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 247:
                            bydAutoEventValue.floatValue = getHevLast200MElectricCon();
                            sparseArray.put(247, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 250:
                            bydAutoEventValue.intValue = getOilPointFlag();
                            sparseArray.put(250, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 251:
                            bydAutoEventValue.floatValue = getLast200mOilCon();
                            sparseArray.put(251, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 273:
                            bydAutoEventValue.intValue = getDriveDistanceShowMode();
                            sparseArray.put(273, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 287:
                            bydAutoEventValue.floatValue = getLast50KmElectricConAvg();
                            sparseArray.put(287, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 288:
                            bydAutoEventValue.floatValue = getLast50KmOilConAvg();
                            sparseArray.put(288, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 289:
                            bydAutoEventValue.floatValue = getLast50KmOilConAvg();
                            sparseArray.put(289, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 747:
                            bydAutoEventValue.intValue = getPreSaleModelState();
                            sparseArray.put(747, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1061:
                            bydAutoEventValue.intValue = getEPedalState();
                            sparseArray.put(1061, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1214:
                            bydAutoEventValue.intValue = getMemoryMaxEvState();
                            sparseArray.put(1214, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1225:
                            bydAutoEventValue.intValue = getDriveDistanceShowStandard();
                            sparseArray.put(1225, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1341:
                            bydAutoEventValue.intValue = getMemoryElectricState();
                            sparseArray.put(1341, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1360:
                            bydAutoEventValue.intValue = getMemorySportState();
                            sparseArray.put(1360, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2442:
                            bydAutoEventValue.intValue = getEnergyMode();
                            sparseArray.put(2442, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2443:
                            bydAutoEventValue.intValue = getEnergyMode();
                            sparseArray.put(2443, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2448:
                            bydAutoEventValue.intValue = getOperationMode();
                            sparseArray.put(2448, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2450:
                            bydAutoEventValue.intValue = getOperationModeNormal();
                            sparseArray.put(2450, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2451:
                            bydAutoEventValue.intValue = getPowerGenerationState();
                            sparseArray.put(2451, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2454:
                            bydAutoEventValue.intValue = getRoadSurfaceModeNormal();
                            sparseArray.put(2454, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2455:
                            bydAutoEventValue.intValue = getRoadSurfaceMode();
                            sparseArray.put(2455, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2457:
                            bydAutoEventValue.intValue = getEnergyState();
                            sparseArray.put(2457, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2665:
                            bydAutoEventValue.intValue = getMandatoryElectricState();
                            sparseArray.put(2665, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3808:
                            bydAutoEventValue.floatValue = getLast50KmElectricConAvg();
                            sparseArray.put(3808, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 4069:
                            bydAutoEventValue.intValue = getElectricShowMode();
                            sparseArray.put(4069, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
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
        if (eventType == 274) {
            setDriveDistanceShowMode(intValue);
            return 1;
        }
        if (eventType == 1062) {
            setEPedalState(intValue);
            Log.v(TAG, "Setting.SET_E_PEDAL_MODE_STATE_SET:");
            return 1;
        }
        if (eventType == 1215) {
            setMemoryMaxEvState(intValue);
            Log.v(TAG, "Setting.SET_MAX_EV_MEMORY_STATE_SET:");
            return 1;
        }
        if (eventType == 1342) {
            setMemoryElectricState(intValue);
            Log.v(TAG, "Setting.SET_SAVE_MEMORY_STATE_SET:");
            return 1;
        }
        if (eventType == 1361) {
            setMemorySportState(intValue);
            Log.v(TAG, "Setting.SET_SPORT_MEMORY_STATE_SET:");
            return 1;
        }
        if (eventType == 2449) {
            setOperationMode(intValue);
            return 1;
        }
        if (eventType == 2667) {
            setMandatoryElectricState(intValue);
            return 1;
        }
        if (eventType != 3914) {
            if (eventType == 4070) {
                setElectricShowMode(intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_ENERGY_DISPLAY_SET:");
                return 1;
            }
            Log.v(TAG, "case fail ! please check out");
            return -1;
        }
        resetCurveData(intValue);
        Log.v(TAG, "resetCurveData:" + intValue);
        return 1;
    }
}
