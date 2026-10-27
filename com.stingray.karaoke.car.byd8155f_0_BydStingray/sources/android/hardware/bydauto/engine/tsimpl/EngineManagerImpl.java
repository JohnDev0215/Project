package android.hardware.bydauto.engine.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.energy.tsimpl.EnergyManagerImpl;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import e.C1144g;
import java.util.ArrayList;
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
    private C1144g mTestCallback;
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
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                this.mTestCallback = new C1144g();
                a.q(new StringBuilder("EngineManagerImpl mForTestValue is"), this.mForTest, " execute return", TAG);
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.engine.tsimpl.EngineManagerImpl.1
            public void onBinderStateChanged(boolean z6) {
                Log.v(EngineManagerImpl.TAG, "onBinderStateChanged is " + z6);
            }

            public void onBodyChanged(int i10, int i11, CarBodyPacket carBodyPacket) {
                if (EngineManagerImpl.this.mAbsBydAutoEngineListeners.isEmpty()) {
                    return;
                }
                EngineManagerImpl.this.engineCallback(i10, i11, carBodyPacket);
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void engineCallback(int i7, int i10, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "engineCallback propertyId:" + i7);
        switch (i7) {
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
                try {
                    if (mEngineManagerImpl == null) {
                        mEngineManagerImpl = new EngineManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return mEngineManagerImpl;
    }

    public void caseFeatureIds(int i7, int i10) {
        int[] iArr = this.mFeatureIds;
        if (iArr == null || iArr.length <= 0 || this.mAbsBydAutoEngineListener == null) {
            return;
        }
        this.bydAutoEventValueListener.intValue = i10;
        a.p(new StringBuilder(":"), this.bydAutoEventValueListener.intValue, TAG);
        this.mAbsBydAutoEngineListener.onDataEventChanged(i7, this.bydAutoEventValueListener);
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    if (i7 == 3578) {
                        int oilLevel = getOilLevel();
                        bYDAutoEventValue.intValue = oilLevel;
                        sparseArray.put(3578, Integer.valueOf(oilLevel));
                    } else if (i7 == 3589) {
                        int engineSpeed = getEngineSpeed();
                        bYDAutoEventValue.intValue = engineSpeed;
                        sparseArray.put(3589, Integer.valueOf(engineSpeed));
                    } else if (i7 != 3592) {
                        switch (i7) {
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
                    int i10 = 0;
                    for (int i11 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i10] = ((Integer) sparseArray.get(i11)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i10++;
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
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEngineCode mForTestValue is"), this.mForTest, " execute return", TAG);
            return "";
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getEngineCode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return "";
    }

    public int getEngineCondition() {
        Log.v(TAG, "getEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEngineCondition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getEngineCondition();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEngineCoolantLevel() {
        Log.v(TAG, "getEngineCoolantLevel is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEngineCoolantLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getEngineCoolantLevel();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float getEngineDisplacement() {
        Log.v(TAG, "getEngineDisplacement is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEngineDisplacement mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getEngineDisplacement();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getEnginePower() {
        Log.v(TAG, "getEnginePower is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEnginePower mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getEnginePower();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEngineSpeed() {
        Log.v(TAG, "getEngineSpeed is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEngineSpeed mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getEngineSpeed();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getOilLevel() {
        Log.v(TAG, "getOilLevel is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getOilLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getOilLevel();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        registerListener(absBYDAutoEngineListener, new int[0]);
    }

    public int setEngineCondition(int i7) {
        Log.v(TAG, "setEngineCondition is execute");
        enforcePermission(BYDAutoEngineDevice.PERMISSION_BYDAUTO_ENGINE_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(i7);
            a.q(new StringBuilder("setEngineCondition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().setEngineCondition(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
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
        C1144g c1144g = this.mTestCallback;
        if (absBYDAutoEngineListener != null) {
            c1144g.f13661a.remove(absBYDAutoEngineListener);
        } else {
            c1144g.getClass();
        }
        a.t(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe engine listener ");
        sb.append(absBYDAutoEngineListener);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
        if (absBYDAutoEngineListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            ArrayList arrayList = this.mTestCallback.f13661a;
            if (!arrayList.contains(absBYDAutoEngineListener)) {
                arrayList.add(absBYDAutoEngineListener);
            }
            a.t(new StringBuilder("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
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
