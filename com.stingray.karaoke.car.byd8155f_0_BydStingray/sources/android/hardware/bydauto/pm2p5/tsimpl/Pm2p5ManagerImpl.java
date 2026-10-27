package android.hardware.bydauto.pm2p5.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.hvac.HvacAdapterManager;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import e.C1149l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Pm2p5ManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_Pm2p5ManagerImpl";
    private static final String TAG = "Pm2p5ManagerImpl";
    private static volatile Pm2p5ManagerImpl sInstance;
    private AbsBYDAutoPM2p5Listener mAbsBydAutoPM2p5Listener;
    private CarAdapterManager mCarAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private C1149l mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private HvacAdapterManager mTsManger;
    private final CopyOnWriteArrayList<AbsBYDAutoPM2p5Listener> mAbsBydAutoPM2p5Listeners = new CopyOnWriteArrayList<>();
    private int mOnline = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private Pm2p5ManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                Log.v(TAG, "Pm2p5ManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new C1149l();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.pm2p5.tsimpl.Pm2p5ManagerImpl.1
            public void onHvacChanged(int i10, int i11, HvacPacket hvacPacket) {
                if (Pm2p5ManagerImpl.this.mAbsBydAutoPM2p5Listeners.isEmpty()) {
                    return;
                }
                Pm2p5ManagerImpl.this.hvacCallback(i10, i11, hvacPacket);
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static Pm2p5ManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (Pm2p5ManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new Pm2p5ManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int i7, int i10, HvacPacket hvacPacket) {
        Log.i(TAG, "hvacCallback propertyId:" + i7);
        switch (i7) {
            case Pm2p5Halid.ID_PM2P5_DUAL_CHANNEL_DETECT_STATER /* 557854720 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener : this.mAbsBydAutoPM2p5Listeners) {
                    if (i10 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener.onPM2p5DualChannelDetectChanged(hvacPacket.getPm2p5DualChannelDetectStateR());
                    }
                }
                caseFeatureIds(2290, Integer.valueOf(hvacPacket.getPm2p5DualChannelDetectStateR()));
                break;
            case Pm2p5Halid.ID_PM2P5_CHECK_STATE_IN /* 557854722 */:
            case Pm2p5Halid.ID_PM2P5_CHECK_STATE_OUT /* 557854723 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener2 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i10 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener2.onPM2p5CheckStateChanged(hvacPacket.getPm2p5CheckStateIn(), hvacPacket.getPm2p5CheckStateOut());
                    }
                }
                caseFeatureIds(2293, Integer.valueOf(hvacPacket.getPm2p5CheckStateIn()));
                caseFeatureIds(2294, Integer.valueOf(hvacPacket.getPm2p5CheckStateOut()));
                break;
            case Pm2p5Halid.ID_PM2P5_VALUE_IN /* 557854725 */:
            case Pm2p5Halid.ID_PM2P5_VALUE_OUT /* 557854726 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener3 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i10 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener3.onPM2p5ValueChanged(hvacPacket.getPm2p5ValueIn(), hvacPacket.getPm2p5ValueOut());
                    }
                }
                caseFeatureIds(2295, Integer.valueOf(hvacPacket.getPm2p5ValueIn()));
                caseFeatureIds(2296, Integer.valueOf(hvacPacket.getPm2p5ValueOut()));
                break;
            case Pm2p5Halid.ID_PM2P5_ANION_STATE /* 557854727 */:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it.hasNext()) {
                    it.next().onPM2p5AnionDetectChanged(hvacPacket.getPm2p5AnionState());
                }
                break;
            case Pm2p5Halid.ID_PM2P5_LEVEL_IN /* 557854728 */:
            case Pm2p5Halid.ID_PM2P5_LEVEL_OUT /* 557854729 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener4 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i10 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener4.onPM2p5LevelChanged(hvacPacket.getPm2p5LevelIn(), hvacPacket.getPm2p5LevelOut());
                    }
                }
                caseFeatureIds(2291, Integer.valueOf(hvacPacket.getPm2p5LevelIn()));
                caseFeatureIds(2292, Integer.valueOf(hvacPacket.getPm2p5LevelOut()));
                break;
            case Pm2p5Halid.ID_PM2P5_ANION_DETECT_STATE /* 557854730 */:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onPM2p5AnionDetectChanged(hvacPacket.getPm2p5AnionDetectState());
                }
                break;
            case Pm2p5Halid.ID_PM2P5_WARNING_INFO /* 557854731 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener5 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i10 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener5.onPM2p5WarningInfoChanged(hvacPacket.getPm2p5WarningInfo());
                    }
                }
                caseFeatureIds(2297, Integer.valueOf(hvacPacket.getPm2p5WarningInfo()));
                break;
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    switch (i7) {
                        case 2289:
                            int pM2p5AnionState = getPM2p5AnionState();
                            bYDAutoEventValue.intValue = pM2p5AnionState;
                            sparseArray.put(2289, Integer.valueOf(pM2p5AnionState));
                            break;
                        case 2290:
                            int pM2p5DualChannelDetectState = getPM2p5DualChannelDetectState();
                            bYDAutoEventValue.intValue = pM2p5DualChannelDetectState;
                            sparseArray.put(2290, Integer.valueOf(pM2p5DualChannelDetectState));
                            break;
                        case 2291:
                            bYDAutoEventValue.intArrayValue = getPM2p5Level();
                            sparseArray.put(2291, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2292:
                            bYDAutoEventValue.intArrayValue = getPM2p5Level();
                            sparseArray.put(2292, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2293:
                            bYDAutoEventValue.intArrayValue = getPM2p5CheckState();
                            sparseArray.put(2293, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2294:
                            bYDAutoEventValue.intArrayValue = getPM2p5CheckState();
                            sparseArray.put(2294, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2295:
                            bYDAutoEventValue.intArrayValue = getPM2p5Value();
                            sparseArray.put(2295, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2296:
                            bYDAutoEventValue.intArrayValue = getPM2p5Value();
                            sparseArray.put(2296, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2297:
                            int pM2p5WarningInfo = getPM2p5WarningInfo();
                            bYDAutoEventValue.intValue = pM2p5WarningInfo;
                            sparseArray.put(2297, Integer.valueOf(pM2p5WarningInfo));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getNegativeIonState() {
        Log.v(TAG, "getNegativeIonState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getNegativeIonState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getNegativeIonState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getPM2p5AnionState() {
        Log.v(TAG, "getPM2p5AnionState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getPM2p5AnionState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5AnionState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getPM2p5CheckState() {
        Log.v(TAG, "getPM2p5CheckState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5CheckState mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5CheckState();
        }
        Log.e(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getPM2p5DualChannelDetectState() {
        Log.v(TAG, "getPM2p5DualChannelDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        Log.v(TAG, "getPM2p5DualChannelDetectState is execute");
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getPM2p5DualChannelDetectState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5DualChannelDetectState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getPM2p5Level() {
        Log.v(TAG, "getPM2p5Level is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5Level mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5Level();
        }
        Log.e(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getPM2p5OnlineState() {
        Log.v(TAG, "getPM2p5OnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5OnlineState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mOnline;
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getPM2p5Value() {
        Log.v(TAG, "getPM2p5Value is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5Value mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5Value();
        }
        Log.e(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getPM2p5WarningInfo() {
        Log.v(TAG, "getPM2p5WarningInfo is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getPM2p5WarningInfo mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5WarningInfo();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoPM2p5Listener> it = this.mAbsBydAutoPM2p5Listeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        registerListener(absBYDAutoPM2p5Listener, new int[0]);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).getClass();
            } else if (obj instanceof Double) {
                ((Double) obj).getClass();
            } else if (!(obj instanceof byte[])) {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i7 != 2301) {
            Log.v(TAG, "case fail ! please check out");
            return -1;
        }
        setPM2p5AnionDetectState(iIntValue);
        return 1;
    }

    public int setNegativeIonState(int i7) {
        Log.v(TAG, "setNegativeIonState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.a(5001, i7);
            Log.v(TAG, "setNegativeIonState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setNegativeIonState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setPM2p5AnionDetectState(int i7) {
        Log.v(TAG, "setPM2p5AnionDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.a(5004, i7);
            a.q(new StringBuilder("setPM2p5AnionDetectState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5AnionDetectState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setPM2p5CheckState(int i7, int i10) {
        Log.v(TAG, "setPM2p5CheckState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.b(i10, i10);
            a.q(new StringBuilder("setPM2p5CheckState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5CheckState(i7, i10);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setPM2p5DualChannelDetectState(int i7) {
        Log.v(TAG, "setPM2p5DualChannelDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.a(5003, i7);
            a.q(new StringBuilder("setPM2p5DualChannelDetectState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5DualChannelDetectState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        if (1 == this.mForTest) {
            C1149l c1149l = this.mTestCallback;
            if (absBYDAutoPM2p5Listener != null) {
                c1149l.f13670a.remove(absBYDAutoPM2p5Listener);
            } else {
                c1149l.getClass();
            }
            a.t(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "unsubscribePm25Callback: " + absBYDAutoPM2p5Listener);
        if (absBYDAutoPM2p5Listener == null) {
            return;
        }
        this.mAbsBydAutoPM2p5Listeners.remove(absBYDAutoPM2p5Listener);
        unregisterConnectListener(absBYDAutoPM2p5Listener);
    }

    public void registerListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe pm2p5 listener ");
        sb.append(absBYDAutoPM2p5Listener);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
        if (absBYDAutoPM2p5Listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            a.t(new StringBuilder("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
            ArrayList arrayList = this.mTestCallback.f13670a;
            if (arrayList.contains(absBYDAutoPM2p5Listener)) {
                return;
            }
            arrayList.add(absBYDAutoPM2p5Listener);
            return;
        }
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        if (!this.mAbsBydAutoPM2p5Listeners.contains(absBYDAutoPM2p5Listener)) {
            this.mAbsBydAutoPM2p5Listeners.add(absBYDAutoPM2p5Listener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoPM2p5Listener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }
}
