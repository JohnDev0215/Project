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
import java.util.Iterator;
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

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
            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (EnergyManagerImpl.this.mAbsBydAutoEnergyListeners.isEmpty()) {
                    return;
                }
                try {
                    EnergyManagerImpl.this.processCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(EnergyManagerImpl.TAG, "processCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(EnergyManagerImpl.TAG, "onBinderStateChanged" + z);
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

    public int setEPedalState(int i) {
        Log.v(TAG, "setEPedalState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(6000, i);
            Log.v(TAG, "setEPedalState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setEPedalState(i);
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

    public int setElectricShowMode(int i) {
        Log.v(TAG, "setElectricShowMode is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_ELECTRIC_SHOW_MODE, i);
            Log.v(TAG, "setElectricShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setElectricShowMode(i);
    }

    public int getMandatoryElectricState() {
        Log.v(TAG, "getMandatoryElectricState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getMandatoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMandatoryElectricState();
    }

    public int setMandatoryElectricState(int i) {
        Log.v(TAG, "setMandatoryElectricState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MANDATORY_ELECTRIC_STATE, i);
            Log.v(TAG, "setMandatoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMandatoryElectricState(i);
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

    public int setDriveDistanceShowMode(int i) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_DRIVE_DISTANCE_SHOW_MODE, i);
            Log.v(TAG, "setDriveDistanceShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(i);
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

    public int setDriveDistanceShowStandard(int i) {
        Log.v(TAG, "setDriveDistanceShowStandard is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_DRIVE_DISTANCE_SHOW_STANDARD, i);
            Log.v(TAG, "setDriveDistanceShowStandard mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowStandard(i);
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

    public int setMemorySportState(int i) {
        Log.v(TAG, "setMemorySportState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_SPORT_STATE, i);
            Log.v(TAG, "setMemorySportState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMemorySportState(i);
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

    public int setMemoryMaxEvState(int i) {
        Log.v(TAG, "setMemoryMaxEvState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_MAX_EV_STATE, i);
            Log.v(TAG, "setMemoryMaxEvState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMemoryMaxEvState(i);
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
    public int setEnergyFeedback(int i) {
        Log.v(TAG, "setEnergyFeedback is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_ENERGY_FEEDBACK, i);
            Log.v(TAG, "setEnergyFeedback mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setEnergyFeedback(i);
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

    public int setPreSaleModelState(int i) {
        Log.v(TAG, "setPreSaleModelState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_PRE_SALE_MODEL_STATE, i);
            Log.v(TAG, "setPreSaleModelState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setPreSaleModelState(i);
    }

    public int setSocTarget(int i) {
        Log.v(TAG, "setSocTarget is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_S_O_C_TARGET, i);
            Log.v(TAG, "setSocTarget mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSocTarget(i);
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

    public int setMemoryElectricState(int i) {
        Log.v(TAG, "setMemoryElectricState is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_ELECTRIC_STATE, i);
            Log.v(TAG, "setMemoryElectricState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMemoryElectricState(i);
    }

    public float[] getEnergyElectricCurveX(int i, int i2) {
        Log.v(TAG, "getEnergyElectricCurveX is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyElectricCurveX mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyElectricCurveX(i, i2);
    }

    public float[] getEnergyElectricCurveY(int i, int i2) {
        Log.v(TAG, "getEnergyElectricCurveY is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyElectricCurveY mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyElectricCurveY(i, i2);
    }

    public float[] getEnergyOilCurveX(int i, int i2) {
        Log.v(TAG, "getEnergyOilCurveX is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyOilCurveX mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyOilCurveX(i, i2);
    }

    public float[] getEnergyOilCurveY(int i, int i2) {
        Log.v(TAG, "getEnergyOilCurveY is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyOilCurveY mForTestValue is" + this.mForTest + " execute return");
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new float[0];
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyOilCurveY(i, i2);
    }

    public int resetCurveData(int i) {
        Log.v(TAG, "resetCurveData is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(6011, i);
            Log.v(TAG, "resetCurveData mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().resetCurveData(i);
    }

    public void registerListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe energy listener " + absBYDAutoEnergyListener);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoEnergyListener(absBYDAutoEnergyListener);
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (absBYDAutoEnergyListener == null) {
            return;
        }
        if (!this.mAbsBydAutoEnergyListeners.contains(absBYDAutoEnergyListener)) {
            this.mAbsBydAutoEnergyListeners.add(absBYDAutoEnergyListener);
        }
        registerConnectListener(this.mContext, absBYDAutoEnergyListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (1 != this.mForTest) {
            if (absBYDAutoEnergyListener == null) {
                return;
            }
            this.mAbsBydAutoEnergyListeners.remove(absBYDAutoEnergyListener);
            unregisterConnectListener(absBYDAutoEnergyListener);
            return;
        }
        this.mTestCallback.removeListener(absBYDAutoEnergyListener);
        Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
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

    public int setOperationMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setOperationMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setOperationMode(i);
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

    public int setiTacpState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setiTacpState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setiTacpState(i);
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

    public int setiTacMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setiTacMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setiTacMode(i);
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

    public int setChargePortCoverHint(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setChargePortCoverHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setChargePortCoverHint(i);
    }

    public int setConfirmChargePortCover(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setConfirmChargePortCover mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setConfirmChargePortCover(i);
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
    public void processCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "carBodyPacket propertyId:" + i);
        switch (i) {
            case EnergyHalId.ID_OPERATION_MODER /* 557847552 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.mAbsBydAutoEnergyListeners.iterator();
                while (it.hasNext()) {
                    it.next().onOperationModeChanged(carBodyPacket.getmOperationMode());
                }
                caseFeatureIds(2448, Integer.valueOf(carBodyPacket.getmOperationMode()));
                break;
            case 557847553:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onRoadSurfaceChanged(carBodyPacket.getmRoadSurfaceMode());
                }
                break;
            case EnergyHalId.ID_POWER_GENERATION_STATE /* 557847554 */:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onPowerGenerationStateChanged(carBodyPacket.getmPowerGenerationState());
                }
                break;
            case EnergyHalId.ID_POWER_GENERATION_VALUE /* 557847555 */:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onPowerGenerationValueChanged(carBodyPacket.getmPowerGenerationValue());
                }
                break;
            case EnergyHalId.ID_ENERGY_MODER /* 557847556 */:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onEnergyModeChanged(carBodyPacket.getmEnergyMode());
                }
                break;
            default:
                switch (i) {
                    case EnergyHalId.ID_DRIVE_DISTANCE_SHOW_MODER /* 557847558 */:
                        Iterator<AbsBYDAutoEnergyListener> it6 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it6.hasNext()) {
                            it6.next().onDriveDistanceShowModeChanged(carBodyPacket.getmDriveDistanceShowModeR());
                        }
                        break;
                    case EnergyHalId.ID_EPEDAL_STATER /* 557847563 */:
                        Iterator<AbsBYDAutoEnergyListener> it7 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it7.hasNext()) {
                            it7.next().onEPedalStateChanged(carBodyPacket.getmEPedalStateR());
                        }
                        break;
                    case EnergyHalId.ID_ELECTRIC_SHOW_MODER /* 557847565 */:
                        Iterator<AbsBYDAutoEnergyListener> it8 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it8.hasNext()) {
                            it8.next().onElectricShowModeChanged(carBodyPacket.getmElectricShowModeR());
                        }
                        break;
                    case EnergyHalId.ID_MANDATORY_ELECTRIC_STATER /* 557847567 */:
                        Iterator<AbsBYDAutoEnergyListener> it9 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it9.hasNext()) {
                            it9.next().onMandatoryElectricStateChanged(carBodyPacket.getmMandatoryElectricStateR());
                        }
                        break;
                    case EnergyHalId.ID_DRIVE_DISTANCE_SHOW_STANDARDR /* 557847569 */:
                        Iterator<AbsBYDAutoEnergyListener> it10 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it10.hasNext()) {
                            it10.next().onDriveDistanceShowStandardChanged(carBodyPacket.getmDriveDistanceShowStandardR());
                        }
                        break;
                    case EnergyHalId.ID_MEMORY_MAX_EV_STATER /* 557847574 */:
                        Iterator<AbsBYDAutoEnergyListener> it11 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it11.hasNext()) {
                            it11.next().onMemoryMaxEvStateChanged(carBodyPacket.getmMemoryMaxEvStateR());
                        }
                        break;
                    case EnergyHalId.ID_MEMORY_ELECTRIC_STATER /* 557847576 */:
                        Iterator<AbsBYDAutoEnergyListener> it12 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it12.hasNext()) {
                            it12.next().onMemoryElectricStateChanged(carBodyPacket.getmMemoryElectricStateR());
                        }
                        break;
                    case EnergyHalId.ID_PRE_SALE_MODEL_STATER /* 557847578 */:
                        Iterator<AbsBYDAutoEnergyListener> it13 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it13.hasNext()) {
                            it13.next().onPreSaleModelStateChanged(carBodyPacket.getmPreSaleModelStateR());
                        }
                        break;
                    case EnergyHalId.ID_OPERATION_MODE_NORMAL_R /* 557847590 */:
                        Iterator<AbsBYDAutoEnergyListener> it14 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it14.hasNext()) {
                            it14.next().onOperationModeNormalChanged(carBodyPacket.getOperationModeNormalR());
                        }
                        caseFeatureIds(2450, Integer.valueOf(carBodyPacket.getOperationModeNormalR()));
                        break;
                    case EnergyHalId.ID_ROAD_SURFACE_MODE_NORMAL_R /* 557847592 */:
                        Iterator<AbsBYDAutoEnergyListener> it15 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it15.hasNext()) {
                            it15.next().onRoadSurfaceModeNormalChanged(carBodyPacket.getRoadSurfaceModeNormalR());
                        }
                        caseFeatureIds(2454, Integer.valueOf(carBodyPacket.getRoadSurfaceModeNormalR()));
                        break;
                    case EnergyHalId.ID_I_T_A_C_P_STATE_R /* 557847594 */:
                        Iterator<AbsBYDAutoEnergyListener> it16 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it16.hasNext()) {
                            it16.next().oniTACStateChanged(carBodyPacket.getiTacpStateR());
                        }
                        break;
                    case EnergyHalId.ID_CHARGE_PORT_COVER_TIP_R /* 557847601 */:
                        Iterator<AbsBYDAutoEnergyListener> it17 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it17.hasNext()) {
                            it17.next().onChargePortCoverHintChanged(carBodyPacket.getChargePortCoverTipR());
                        }
                        break;
                    case EnergyHalId.ID_ROLL_BENCH_MODE_STATUS_R /* 557860387 */:
                        Iterator<AbsBYDAutoEnergyListener> it18 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it18.hasNext()) {
                            it18.next().onRollBenchModeStatusChanged(carBodyPacket.getRollBenchModeStatusR());
                        }
                        break;
                    case EnergyHalId.ID_HEV_LAST_200_M_ELECTRIC_CON_R /* 559944740 */:
                        Iterator<AbsBYDAutoEnergyListener> it19 = this.mAbsBydAutoEnergyListeners.iterator();
                        while (it19.hasNext()) {
                            it19.next().onHEVLast200MElectricConChanged(carBodyPacket.getHevLast200MElectricConR());
                        }
                        break;
                    default:
                        switch (i) {
                            case EnergyHalId.ID_RESET_CURVE_DATA /* 557847571 */:
                                Iterator<AbsBYDAutoEnergyListener> it20 = this.mAbsBydAutoEnergyListeners.iterator();
                                while (it20.hasNext()) {
                                    it20.next().onResetCurveDataChanged(0, carBodyPacket.getmResetCurveData());
                                }
                                break;
                            case EnergyHalId.ID_MEMORY_SPORT_STATER /* 557847572 */:
                                Iterator<AbsBYDAutoEnergyListener> it21 = this.mAbsBydAutoEnergyListeners.iterator();
                                while (it21.hasNext()) {
                                    it21.next().onMemorySportStateChanged(carBodyPacket.getmMemorySportStateR());
                                }
                                break;
                            default:
                                switch (i) {
                                    case EnergyHalId.ID_OIL_POINT_FLAG /* 557847580 */:
                                        Iterator<AbsBYDAutoEnergyListener> it22 = this.mAbsBydAutoEnergyListeners.iterator();
                                        while (it22.hasNext()) {
                                            it22.next().onOilPointFlagChanged(carBodyPacket.getOilPointFlag());
                                        }
                                        break;
                                    case EnergyHalId.ID_ELECTRIC_POINT_FLAG /* 557847581 */:
                                        Iterator<AbsBYDAutoEnergyListener> it23 = this.mAbsBydAutoEnergyListeners.iterator();
                                        while (it23.hasNext()) {
                                            it23.next().onElectricPointFlagChanged(carBodyPacket.getElectricPointFlag());
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case EnergyHalId.ID_ENERGY_STATER /* 557847586 */:
                                                Iterator<AbsBYDAutoEnergyListener> it24 = this.mAbsBydAutoEnergyListeners.iterator();
                                                while (it24.hasNext()) {
                                                    it24.next().onEnergyStateChanged(carBodyPacket.getEnergyStateR());
                                                }
                                                break;
                                            case EnergyHalId.ID_HEV_ELECTRIC_POINT_FLAG_R /* 557847587 */:
                                                Iterator<AbsBYDAutoEnergyListener> it25 = this.mAbsBydAutoEnergyListeners.iterator();
                                                while (it25.hasNext()) {
                                                    it25.next().onHEVElectricPointFlagChanged(carBodyPacket.getHevElectricPointFlagR());
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case EnergyHalId.ID_I_T_A_C_P_ALLOW_TAG_R /* 557847596 */:
                                                        Iterator<AbsBYDAutoEnergyListener> it26 = this.mAbsBydAutoEnergyListeners.iterator();
                                                        while (it26.hasNext()) {
                                                            it26.next().oniTACAllowTagChanged(carBodyPacket.getiTacpAllowTagR());
                                                        }
                                                        break;
                                                    case EnergyHalId.ID_CHANGE_P_HINT_R /* 557847597 */:
                                                        Iterator<AbsBYDAutoEnergyListener> it27 = this.mAbsBydAutoEnergyListeners.iterator();
                                                        while (it27.hasNext()) {
                                                            it27.next().onChangePHintChanged(carBodyPacket.getChangePHintR());
                                                        }
                                                        break;
                                                    case EnergyHalId.ID_I_T_A_C_EXIT_TAG_R /* 557847598 */:
                                                        Iterator<AbsBYDAutoEnergyListener> it28 = this.mAbsBydAutoEnergyListeners.iterator();
                                                        while (it28.hasNext()) {
                                                            it28.next().oniTACExitTagChanged(carBodyPacket.getiTacExitTagR());
                                                        }
                                                        break;
                                                    case EnergyHalId.ID_I_T_A_C_MODE_R /* 557847599 */:
                                                        Iterator<AbsBYDAutoEnergyListener> it29 = this.mAbsBydAutoEnergyListeners.iterator();
                                                        while (it29.hasNext()) {
                                                            it29.next().oniTACModeChanged(carBodyPacket.getiTacModeR());
                                                        }
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case EnergyHalId.ID_TRIP_DRIVE_PERCENT_R /* 557847604 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it30 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it30.hasNext()) {
                                                                    it30.next().onTripDrivePercentChanged(carBodyPacket.getTripDrivePercentR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_A_C_PERCENT_R /* 557847605 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it31 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it31.hasNext()) {
                                                                    it31.next().onTripACPercentChanged(carBodyPacket.getTripAcPercentR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_ELEC_APPLIANCE_PERCENT_R /* 557847606 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it32 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it32.hasNext()) {
                                                                    it32.next().onTripElecAppliancePercentChanged(carBodyPacket.getTripElecAppliancePercentR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_OTHER_PERCENT_R /* 557847607 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it33 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it33.hasNext()) {
                                                                    it33.next().onTripOtherPercentChanged(carBodyPacket.getTripOtherPercentR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_POINT_FLAG_R /* 557847608 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it34 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it34.hasNext()) {
                                                                    it34.next().onTripPointFlagChanged(carBodyPacket.getTripPointFlagR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_DRIVE_PERCENT50_K_M_R /* 557847609 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it35 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it35.hasNext()) {
                                                                    it35.next().onTripDrivePercent50KMChanged(carBodyPacket.getTripDrivePercent50kmr());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_A_C_PERCENT50_K_M_R /* 557847610 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it36 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it36.hasNext()) {
                                                                    it36.next().onTripACPercent50KMChanged(carBodyPacket.getTripAcPercent50kmr());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_ELEC_APPLIANCE_PERCENT50_K_M_R /* 557847611 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it37 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it37.hasNext()) {
                                                                    it37.next().onTripElecAppliancePercent50KMChanged(carBodyPacket.getTripElecAppliancePercent50kmr());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_OTHER_PERCENT50_K_M_R /* 557847612 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it38 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it38.hasNext()) {
                                                                    it38.next().onTripOtherPercent50KMChanged(carBodyPacket.getTripOtherPercent50kmr());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_OIL_CON_AVG_R /* 557847613 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it39 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it39.hasNext()) {
                                                                    it39.next().onTripOilConAvgChanged(carBodyPacket.getTripOilConAvgR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_ENERGY_CON_AVG_R /* 557847614 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it40 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it40.hasNext()) {
                                                                    it40.next().onTripEnergyConAvgChanged(carBodyPacket.getTripEnergyConAvgR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_Y_ENERGY_CON_R /* 557847615 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it41 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it41.hasNext()) {
                                                                    it41.next().onYEnergyConChanged(carBodyPacket.getyEnergyConR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_ELEC_CON_AVG_R /* 557847616 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it42 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it42.hasNext()) {
                                                                    it42.next().onTripElecConAvgChanged(carBodyPacket.getTripElecConAvgR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_Y_ENERGY_CON_LIMIT_R /* 557847617 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it43 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it43.hasNext()) {
                                                                    it43.next().onYEnergyConLimitChanged(carBodyPacket.getyEnergyConLimitR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_LAST50_K_M_ELECTRIC_CON_AVG_R /* 557847618 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it44 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it44.hasNext()) {
                                                                    it44.next().onTripLast50KMElectricConAvgChanged(carBodyPacket.getTripLast50kmElectricConAvgR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_LAST50_K_M_OIL_CON_AVG_R /* 557847619 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it45 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it45.hasNext()) {
                                                                    it45.next().onTripLast50KMOilConAvgChanged(carBodyPacket.getTripLast50kmOilConAvgR());
                                                                }
                                                                break;
                                                            case EnergyHalId.ID_TRIP_LAST50_K_M_FUEL_CON_AVG_R /* 557847620 */:
                                                                Iterator<AbsBYDAutoEnergyListener> it46 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                while (it46.hasNext()) {
                                                                    it46.next().onTripLast50KMFuelConAvgChanged(carBodyPacket.getTripLast50kmFuelConAvgR());
                                                                }
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case EnergyHalId.ID_LAST200_M_OIL_CON /* 559944734 */:
                                                                        Iterator<AbsBYDAutoEnergyListener> it47 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                        while (it47.hasNext()) {
                                                                            it47.next().onLast200MOilConChanged(carBodyPacket.getLast200MOilCon());
                                                                        }
                                                                        break;
                                                                    case EnergyHalId.ID_LAST200_M_ELECTRIC_CON /* 559944735 */:
                                                                        Iterator<AbsBYDAutoEnergyListener> it48 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                        while (it48.hasNext()) {
                                                                            it48.next().onLast200MElectricConChanged(carBodyPacket.getLast200MElectricCon());
                                                                        }
                                                                        break;
                                                                    case EnergyHalId.ID_LAST50_K_M_OIL_CON_AVG /* 559944736 */:
                                                                        Iterator<AbsBYDAutoEnergyListener> it49 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                        while (it49.hasNext()) {
                                                                            it49.next().onLast50KMOilConAvgChanged(carBodyPacket.getLast50KmOilConAvg());
                                                                        }
                                                                        break;
                                                                    case EnergyHalId.ID_LAST50_K_M_ELECTRIC_CON_AVG /* 559944737 */:
                                                                        Iterator<AbsBYDAutoEnergyListener> it50 = this.mAbsBydAutoEnergyListeners.iterator();
                                                                        while (it50.hasNext()) {
                                                                            it50.next().onLast50KMElectricConAvgChanged(carBodyPacket.getLast50KmElectricConAvg());
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    switch (i) {
                        case 244:
                            bYDAutoEventValue.floatValue = getLast200mElectricCon();
                            sparseArray.put(244, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 245:
                            bYDAutoEventValue.intValue = getElectricPointFlag();
                            sparseArray.put(245, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 246:
                            bYDAutoEventValue.intValue = getHevElectricPointFlag();
                            sparseArray.put(246, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 247:
                            bYDAutoEventValue.floatValue = getHevLast200MElectricCon();
                            sparseArray.put(247, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 250:
                            bYDAutoEventValue.intValue = getOilPointFlag();
                            sparseArray.put(250, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 251:
                            bYDAutoEventValue.floatValue = getLast200mOilCon();
                            sparseArray.put(251, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 273:
                            bYDAutoEventValue.intValue = getDriveDistanceShowMode();
                            sparseArray.put(273, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 287:
                            bYDAutoEventValue.floatValue = getLast50KmElectricConAvg();
                            sparseArray.put(287, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 288:
                            bYDAutoEventValue.floatValue = getLast50KmOilConAvg();
                            sparseArray.put(288, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 289:
                            bYDAutoEventValue.floatValue = getLast50KmOilConAvg();
                            sparseArray.put(289, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 747:
                            bYDAutoEventValue.intValue = getPreSaleModelState();
                            sparseArray.put(747, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1061:
                            bYDAutoEventValue.intValue = getEPedalState();
                            sparseArray.put(1061, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1214:
                            bYDAutoEventValue.intValue = getMemoryMaxEvState();
                            sparseArray.put(1214, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1225:
                            bYDAutoEventValue.intValue = getDriveDistanceShowStandard();
                            sparseArray.put(1225, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1341:
                            bYDAutoEventValue.intValue = getMemoryElectricState();
                            sparseArray.put(1341, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1360:
                            bYDAutoEventValue.intValue = getMemorySportState();
                            sparseArray.put(1360, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2442:
                            bYDAutoEventValue.intValue = getEnergyMode();
                            sparseArray.put(2442, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2443:
                            bYDAutoEventValue.intValue = getEnergyMode();
                            sparseArray.put(2443, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2448:
                            bYDAutoEventValue.intValue = getOperationMode();
                            sparseArray.put(2448, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2450:
                            bYDAutoEventValue.intValue = getOperationModeNormal();
                            sparseArray.put(2450, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2451:
                            bYDAutoEventValue.intValue = getPowerGenerationState();
                            sparseArray.put(2451, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2454:
                            bYDAutoEventValue.intValue = getRoadSurfaceModeNormal();
                            sparseArray.put(2454, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2455:
                            bYDAutoEventValue.intValue = getRoadSurfaceMode();
                            sparseArray.put(2455, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2457:
                            bYDAutoEventValue.intValue = getEnergyState();
                            sparseArray.put(2457, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2665:
                            bYDAutoEventValue.intValue = getMandatoryElectricState();
                            sparseArray.put(2665, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3808:
                            bYDAutoEventValue.floatValue = getLast50KmElectricConAvg();
                            sparseArray.put(3808, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 4069:
                            bYDAutoEventValue.intValue = getElectricShowMode();
                            sparseArray.put(4069, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
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
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i == 274) {
            setDriveDistanceShowMode(iIntValue);
            return 1;
        }
        if (i == 1062) {
            setEPedalState(iIntValue);
            Log.v(TAG, "Setting.SET_E_PEDAL_MODE_STATE_SET:");
            return 1;
        }
        if (i == 1215) {
            setMemoryMaxEvState(iIntValue);
            Log.v(TAG, "Setting.SET_MAX_EV_MEMORY_STATE_SET:");
            return 1;
        }
        if (i == 1342) {
            setMemoryElectricState(iIntValue);
            Log.v(TAG, "Setting.SET_SAVE_MEMORY_STATE_SET:");
            return 1;
        }
        if (i == 1361) {
            setMemorySportState(iIntValue);
            Log.v(TAG, "Setting.SET_SPORT_MEMORY_STATE_SET:");
            return 1;
        }
        if (i == 2449) {
            setOperationMode(iIntValue);
            return 1;
        }
        if (i == 2667) {
            setMandatoryElectricState(iIntValue);
            return 1;
        }
        if (i != 3914) {
            if (i == 4070) {
                setElectricShowMode(iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_ENERGY_DISPLAY_SET:");
                return 1;
            }
            Log.v(TAG, "case fail ! please check out");
            return -1;
        }
        resetCurveData(iIntValue);
        Log.v(TAG, "resetCurveData:" + iIntValue);
        return 1;
    }
}
