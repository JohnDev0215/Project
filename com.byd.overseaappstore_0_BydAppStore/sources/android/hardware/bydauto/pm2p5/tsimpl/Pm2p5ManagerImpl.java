package android.hardware.bydauto.pm2p5.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto_test.TestPm2p5ManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.hvac.HvacAdapterManager;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

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
    private TestPm2p5ManagerImplCallback mTestCallback;
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
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                StringBuilder sbK = e.k("Pm2p5ManagerImpl mForTestValue is");
                sbK.append(this.mForTest);
                sbK.append(" execute return");
                Log.v(TAG, sbK.toString());
                this.mTestCallback = new TestPm2p5ManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.pm2p5.tsimpl.Pm2p5ManagerImpl.1
            public void onHvacChanged(int i2, int i3, HvacPacket hvacPacket) {
                if (Pm2p5ManagerImpl.this.mAbsBydAutoPM2p5Listeners.isEmpty()) {
                    return;
                }
                try {
                    Pm2p5ManagerImpl.this.hvacCallback(i2, i3, hvacPacket);
                } catch (Exception unused) {
                    Log.e(Pm2p5ManagerImpl.TAG, "hvacCallback is error");
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
    }

    public static Pm2p5ManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (Pm2p5ManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new Pm2p5ManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int i, int i2, HvacPacket hvacPacket) {
        int i3;
        int pm2p5DualChannelDetectStateR;
        e.t("hvacCallback propertyId:", i, TAG);
        switch (i) {
            case Pm2p5Halid.ID_PM2P5_DUAL_CHANNEL_DETECT_STATER /* 557854720 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener : this.mAbsBydAutoPM2p5Listeners) {
                    if (i2 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener.onPM2p5DualChannelDetectChanged(hvacPacket.getPm2p5DualChannelDetectStateR());
                    }
                }
                i3 = 2290;
                pm2p5DualChannelDetectStateR = hvacPacket.getPm2p5DualChannelDetectStateR();
                break;
            case Pm2p5Halid.ID_PM2P5_DUAL_CHANNEL_DETECT_STATES /* 557854721 */:
            case Pm2p5Halid.ID_PM2P5_CHECK_STATE /* 557854724 */:
            default:
                return;
            case Pm2p5Halid.ID_PM2P5_CHECK_STATE_IN /* 557854722 */:
            case Pm2p5Halid.ID_PM2P5_CHECK_STATE_OUT /* 557854723 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener2 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i2 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener2.onPM2p5CheckStateChanged(hvacPacket.getPm2p5CheckStateIn(), hvacPacket.getPm2p5CheckStateOut());
                    }
                }
                caseFeatureIds(2293, Integer.valueOf(hvacPacket.getPm2p5CheckStateIn()));
                i3 = 2294;
                pm2p5DualChannelDetectStateR = hvacPacket.getPm2p5CheckStateOut();
                break;
            case Pm2p5Halid.ID_PM2P5_VALUE_IN /* 557854725 */:
            case Pm2p5Halid.ID_PM2P5_VALUE_OUT /* 557854726 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener3 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i2 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener3.onPM2p5ValueChanged(hvacPacket.getPm2p5ValueIn(), hvacPacket.getPm2p5ValueOut());
                    }
                }
                caseFeatureIds(2295, Integer.valueOf(hvacPacket.getPm2p5ValueIn()));
                i3 = 2296;
                pm2p5DualChannelDetectStateR = hvacPacket.getPm2p5ValueOut();
                break;
            case Pm2p5Halid.ID_PM2P5_ANION_STATE /* 557854727 */:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it.hasNext()) {
                    it.next().onPM2p5AnionDetectChanged(hvacPacket.getPm2p5AnionState());
                }
                return;
            case Pm2p5Halid.ID_PM2P5_LEVEL_IN /* 557854728 */:
            case Pm2p5Halid.ID_PM2P5_LEVEL_OUT /* 557854729 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener4 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i2 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener4.onPM2p5LevelChanged(hvacPacket.getPm2p5LevelIn(), hvacPacket.getPm2p5LevelOut());
                    }
                }
                caseFeatureIds(2291, Integer.valueOf(hvacPacket.getPm2p5LevelIn()));
                i3 = 2292;
                pm2p5DualChannelDetectStateR = hvacPacket.getPm2p5LevelOut();
                break;
            case Pm2p5Halid.ID_PM2P5_ANION_DETECT_STATE /* 557854730 */:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onPM2p5AnionDetectChanged(hvacPacket.getPm2p5AnionDetectState());
                }
                return;
            case Pm2p5Halid.ID_PM2P5_WARNING_INFO /* 557854731 */:
                for (AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener5 : this.mAbsBydAutoPM2p5Listeners) {
                    if (i2 == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoPM2p5Listener5.onPM2p5WarningInfoChanged(hvacPacket.getPm2p5WarningInfo());
                    }
                }
                i3 = 2297;
                pm2p5DualChannelDetectStateR = hvacPacket.getPm2p5WarningInfo();
                break;
        }
        caseFeatureIds(i3, Integer.valueOf(pm2p5DualChannelDetectStateR));
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        int pM2p5AnionState;
        int i;
        int i2;
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i3 : iArr) {
                if (i3 > 0) {
                    switch (i3) {
                        case 2289:
                            pM2p5AnionState = getPM2p5AnionState();
                            bYDAutoEventValue.intValue = pM2p5AnionState;
                            i = 2289;
                            sparseArray.put(i, Integer.valueOf(pM2p5AnionState));
                            break;
                        case 2290:
                            pM2p5AnionState = getPM2p5DualChannelDetectState();
                            bYDAutoEventValue.intValue = pM2p5AnionState;
                            i = 2290;
                            sparseArray.put(i, Integer.valueOf(pM2p5AnionState));
                            break;
                        case 2291:
                            bYDAutoEventValue.intArrayValue = getPM2p5Level();
                            i2 = 2291;
                            sparseArray.put(i2, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2292:
                            bYDAutoEventValue.intArrayValue = getPM2p5Level();
                            i2 = 2292;
                            sparseArray.put(i2, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2293:
                            bYDAutoEventValue.intArrayValue = getPM2p5CheckState();
                            i2 = 2293;
                            sparseArray.put(i2, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2294:
                            bYDAutoEventValue.intArrayValue = getPM2p5CheckState();
                            i2 = 2294;
                            sparseArray.put(i2, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2295:
                            bYDAutoEventValue.intArrayValue = getPM2p5Value();
                            i2 = 2295;
                            sparseArray.put(i2, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2296:
                            bYDAutoEventValue.intArrayValue = getPM2p5Value();
                            i2 = 2296;
                            sparseArray.put(i2, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2297:
                            pM2p5AnionState = getPM2p5WarningInfo();
                            bYDAutoEventValue.intValue = pM2p5AnionState;
                            i = 2297;
                            sparseArray.put(i, Integer.valueOf(pM2p5AnionState));
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
            e.s(e.k("getPM2p5AnionState mForTestValue is"), this.mForTest, " execute return", TAG);
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
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5CheckState();
        }
        Log.v(TAG, "getPM2p5CheckState mForTestValue is 0 execute return");
        return new int[0];
    }

    public int getPM2p5DualChannelDetectState() {
        Log.v(TAG, "getPM2p5DualChannelDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        Log.v(TAG, "getPM2p5DualChannelDetectState is execute");
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5DualChannelDetectState();
        }
        e.s(e.k("getPM2p5DualChannelDetectState mForTestValue is"), this.mForTest, " execute return", TAG);
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
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5Value();
        }
        Log.v(TAG, "getPM2p5Value mForTestValue is 0 execute return");
        return new int[0];
    }

    public int getPM2p5WarningInfo() {
        Log.v(TAG, "getPM2p5WarningInfo is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            e.s(e.k("getPM2p5WarningInfo mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5WarningInfo();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoPM2p5Listener> it = this.mAbsBydAutoPM2p5Listeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        registerListener(absBYDAutoPM2p5Listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe pm2p5 listener ");
        sb.append(absBYDAutoPM2p5Listener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
        if (absBYDAutoPM2p5Listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            e.u(e.k("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
            this.mTestCallback.setAbsBydAutoPm2p5Listener(absBYDAutoPM2p5Listener);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 2301) {
            setPM2p5AnionDetectState(iIntValue);
            return 1;
        }
        str = "case fail ! please check out";
        Log.v(TAG, str);
        return -1;
    }

    public int setNegativeIonState(int i) {
        Log.v(TAG, "setNegativeIonState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(5001, i);
            Log.v(TAG, "setNegativeIonState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().setNegativeIonState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setPM2p5AnionDetectState(int i) {
        Log.v(TAG, "setPM2p5AnionDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5AnionDetectState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.enqueueMessage(5004, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setPM2p5AnionDetectState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setPM2p5CheckState(int i, int i2) {
        Log.v(TAG, "setPM2p5CheckState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5CheckState(i, i2);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.enqueueMessage(5002, i2, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("setPM2p5CheckState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setPM2p5DualChannelDetectState(int i) {
        Log.v(TAG, "setPM2p5DualChannelDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5DualChannelDetectState(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.enqueueMessage(5003, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setPM2p5DualChannelDetectState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public void unregisterListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoPM2p5Listener);
            StringBuilder sb = new StringBuilder();
            sb.append("unregisterListener mForTestValue is");
            e.u(sb, this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "unsubscribePm25Callback: " + absBYDAutoPM2p5Listener);
        if (absBYDAutoPM2p5Listener == null) {
            return;
        }
        this.mAbsBydAutoPM2p5Listeners.remove(absBYDAutoPM2p5Listener);
        unregisterConnectListener(absBYDAutoPM2p5Listener);
    }
}
