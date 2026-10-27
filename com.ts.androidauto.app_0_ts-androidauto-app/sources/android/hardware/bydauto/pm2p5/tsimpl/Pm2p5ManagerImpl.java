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
import java.util.Arrays;
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
    private TestPm2p5ManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private HvacAdapterManager mTsManger;
    private final CopyOnWriteArrayList<AbsBYDAutoPM2p5Listener> mAbsBydAutoPM2p5Listeners = new CopyOnWriteArrayList<>();
    private int mOnline = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private Pm2p5ManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContentResolver = con.getContentResolver();
        this.mContext = con;
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "Pm2p5ManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new TestPm2p5ManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.pm2p5.tsimpl.Pm2p5ManagerImpl.1
            public void onHvacChanged(int id, int areaId, HvacPacket packet) {
                if (!Pm2p5ManagerImpl.this.mAbsBydAutoPM2p5Listeners.isEmpty()) {
                    try {
                        Pm2p5ManagerImpl.this.hvacCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(Pm2p5ManagerImpl.TAG, "hvacCallback is error");
                    }
                }
            }
        });
    }

    public static Pm2p5ManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (Pm2p5ManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new Pm2p5ManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int id, int statusId, HvacPacket packet) {
        Log.i(TAG, "hvacCallback propertyId:" + id);
        switch (id) {
            case 557854720:
                for (AbsBYDAutoPM2p5Listener listener : this.mAbsBydAutoPM2p5Listeners) {
                    if (statusId == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        listener.onPM2p5DualChannelDetectChanged(packet.getPm2p5DualChannelDetectStateR());
                    }
                }
                caseFeatureIds(2290, Integer.valueOf(packet.getPm2p5DualChannelDetectStateR()));
                break;
            case 557854722:
            case 557854723:
                for (AbsBYDAutoPM2p5Listener listener2 : this.mAbsBydAutoPM2p5Listeners) {
                    if (statusId == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        listener2.onPM2p5CheckStateChanged(packet.getPm2p5CheckStateIn(), packet.getPm2p5CheckStateOut());
                    }
                }
                caseFeatureIds(2293, Integer.valueOf(packet.getPm2p5CheckStateIn()));
                caseFeatureIds(2294, Integer.valueOf(packet.getPm2p5CheckStateOut()));
                break;
            case 557854725:
            case 557854726:
                for (AbsBYDAutoPM2p5Listener listener3 : this.mAbsBydAutoPM2p5Listeners) {
                    if (statusId == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        listener3.onPM2p5ValueChanged(packet.getPm2p5ValueIn(), packet.getPm2p5ValueOut());
                    }
                }
                caseFeatureIds(2295, Integer.valueOf(packet.getPm2p5ValueIn()));
                caseFeatureIds(2296, Integer.valueOf(packet.getPm2p5ValueOut()));
                break;
            case 557854727:
                for (AbsBYDAutoPM2p5Listener listener4 : this.mAbsBydAutoPM2p5Listeners) {
                    listener4.onPM2p5AnionDetectChanged(packet.getPm2p5AnionState());
                }
                break;
            case 557854728:
            case 557854729:
                for (AbsBYDAutoPM2p5Listener listener5 : this.mAbsBydAutoPM2p5Listeners) {
                    if (statusId == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        listener5.onPM2p5LevelChanged(packet.getPm2p5LevelIn(), packet.getPm2p5LevelOut());
                    }
                }
                caseFeatureIds(2291, Integer.valueOf(packet.getPm2p5LevelIn()));
                caseFeatureIds(2292, Integer.valueOf(packet.getPm2p5LevelOut()));
                break;
            case 557854730:
                for (AbsBYDAutoPM2p5Listener listener6 : this.mAbsBydAutoPM2p5Listeners) {
                    listener6.onPM2p5AnionDetectChanged(packet.getPm2p5AnionDetectState());
                }
                break;
            case 557854731:
                for (AbsBYDAutoPM2p5Listener listener7 : this.mAbsBydAutoPM2p5Listeners) {
                    if (statusId == 2) {
                        this.mOnline = 0;
                    } else {
                        this.mOnline = 1;
                        listener7.onPM2p5WarningInfoChanged(packet.getPm2p5WarningInfo());
                    }
                }
                caseFeatureIds(2297, Integer.valueOf(packet.getPm2p5WarningInfo()));
                break;
        }
    }

    public int getPM2p5OnlineState() {
        Log.v(TAG, "getPM2p5OnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5OnlineState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mOnline;
    }

    public int[] getPM2p5CheckState() {
        Log.v(TAG, "getPM2p5CheckState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5CheckState mForTestValue is 0 execute return");
            return new int[0];
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5CheckState();
    }

    public int[] getPM2p5Level() {
        Log.v(TAG, "getPM2p5Level is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5Level mForTestValue is 0 execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5Level();
    }

    public int[] getPM2p5Value() {
        Log.v(TAG, "getPM2p5Value is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5Value mForTestValue is 0 execute return");
            return new int[0];
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5Value();
    }

    public int getNegativeIonState() {
        Log.v(TAG, "getNegativeIonState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getNegativeIonState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getNegativeIonState();
    }

    public int setNegativeIonState(int state) {
        Log.v(TAG, "setNegativeIonState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(5001, state);
            Log.v(TAG, "setNegativeIonState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setNegativeIonState(state);
    }

    public void registerListener(AbsBYDAutoPM2p5Listener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoPM2p5Listener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe pm2p5 listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBydAutoPm2p5Listener(listener);
            return;
        }
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        if (!this.mAbsBydAutoPM2p5Listeners.contains(listener)) {
            this.mAbsBydAutoPM2p5Listeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }

    public void unregisterListener(AbsBYDAutoPM2p5Listener listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoPM2p5Device.PERMISSION_BYDAUTO_PM2P5_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(listener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribePm25Callback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoPM2p5Listeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getPM2p5DualChannelDetectState() {
        Log.v(TAG, "getPM2p5DualChannelDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        Log.v(TAG, "getPM2p5DualChannelDetectState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5DualChannelDetectState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5DualChannelDetectState();
    }

    public int getPM2p5WarningInfo() {
        Log.v(TAG, "getPM2p5WarningInfo is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5WarningInfo mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5WarningInfo();
    }

    public int setPM2p5DualChannelDetectState(int state) {
        Log.v(TAG, "setPM2p5DualChannelDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(5003, state);
            Log.v(TAG, "setPM2p5DualChannelDetectState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5DualChannelDetectState(state);
    }

    public int setPM2p5CheckState(int area, int state) {
        Log.v(TAG, "setPM2p5CheckState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(5002, state, state);
            Log.v(TAG, "setPM2p5CheckState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5CheckState(area, state);
    }

    public int getPM2p5AnionState() {
        Log.v(TAG, "getPM2p5AnionState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM2p5AnionState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPM2p5AnionState();
    }

    public int setPM2p5AnionDetectState(int state) {
        Log.v(TAG, "setPM2p5AnionDetectState is execute");
        enforcePermission("android.permission.BYDAUTO_PM2P5_SET");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(5004, state);
            Log.v(TAG, "setPM2p5AnionDetectState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setPM2p5AnionDetectState(state);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 2289:
                            bydAutoEventValue.intValue = getPM2p5AnionState();
                            sparseArray.put(2289, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2290:
                            bydAutoEventValue.intValue = getPM2p5DualChannelDetectState();
                            sparseArray.put(2290, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2291:
                            bydAutoEventValue.intArrayValue = getPM2p5Level();
                            sparseArray.put(2291, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2292:
                            bydAutoEventValue.intArrayValue = getPM2p5Level();
                            sparseArray.put(2292, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2293:
                            bydAutoEventValue.intArrayValue = getPM2p5CheckState();
                            sparseArray.put(2293, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2294:
                            bydAutoEventValue.intArrayValue = getPM2p5CheckState();
                            sparseArray.put(2294, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2295:
                            bydAutoEventValue.intArrayValue = getPM2p5Value();
                            sparseArray.put(2295, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2296:
                            bydAutoEventValue.intArrayValue = getPM2p5Value();
                            sparseArray.put(2296, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2297:
                            bydAutoEventValue.intValue = getPM2p5WarningInfo();
                            sparseArray.put(2297, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
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
        for (AbsBYDAutoPM2p5Listener listener : this.mAbsBydAutoPM2p5Listeners) {
            listener.onDataEventChanged(id, value);
        }
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
        if (eventType == 2301) {
            setPM2p5AnionDetectState(intValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
