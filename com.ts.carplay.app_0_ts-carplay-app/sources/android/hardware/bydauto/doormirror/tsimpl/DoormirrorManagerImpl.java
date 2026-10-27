package android.hardware.bydauto.doormirror.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.doormirror.AbsBYDAutoRearViewMirrorListener;
import android.hardware.bydauto.doormirror.BYDAutoRearViewMirrorDevice;
import android.util.Log;
import android.util.SparseArray;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DoormirrorManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_DoormirrorManagerImpl";
    private static final String TAG = "DoormirrorManagerImpl";
    private static DoormirrorManagerImpl sInstance;
    private AbsBYDAutoRearViewMirrorListener mAbsBydAutoRearViewMirrorListener;
    private Context mContext;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 0;
    private final CopyOnWriteArrayList<AbsBYDAutoRearViewMirrorListener> mAbsBYDAutoRearViewMirrorListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    DoormirrorManagerImpl(Context con) {
        this.mContext = con;
        this.mTsManagerImpl = TsManagerImpl.getInstance(con);
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        this.mTsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doormirror.tsimpl.DoormirrorManagerImpl.1
            public void onCabinChanged(int id, int areaId, CabinPacket packet) {
                DoormirrorManagerImpl.this.doorMirrorCallBack(id, areaId, packet);
            }

            public void onCabinBinderChanged(boolean binderState) {
                Log.d(DoormirrorManagerImpl.TAG, "cabin call back state :" + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorMirrorCallBack(int id, int areaId, CabinPacket packet) {
        Log.i(TAG, "doorMirrorCallBack propertyId:" + id);
        switch (id) {
            case 557858048:
                for (AbsBYDAutoRearViewMirrorListener listener : this.mAbsBYDAutoRearViewMirrorListeners) {
                    listener.onAutoExternalRearMirrorFoldActionStateChanged(packet.getAutoExternalRearMirrorFoldActionStateR());
                }
                caseFeatureIds(2169, Integer.valueOf(packet.getAutoExternalRearMirrorFoldActionStateR()));
                break;
            case 557858049:
                for (AbsBYDAutoRearViewMirrorListener listener2 : this.mAbsBYDAutoRearViewMirrorListeners) {
                    listener2.onAutoExternalRearMirrorFoldedState(packet.getAutoExternalRearMirrorFoldedStateR());
                }
                caseFeatureIds(2170, Integer.valueOf(packet.getAutoExternalRearMirrorFoldedStateR()));
                break;
        }
    }

    public static synchronized DoormirrorManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            sInstance = new DoormirrorManagerImpl(con);
        }
        return sInstance;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype is run. ");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return 0;
    }

    public String getSetPermission() {
        return "";
    }

    public String getGetPermission() {
        return "";
    }

    public int getAutoExternalRearMirrorState() {
        Log.d(TAG, "getAutoExternalRearMirrorState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoExternalRearMirrorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorState();
    }

    public int getAutoExternalRearMirrorAntiglareState() {
        Log.d(TAG, "getAutoExternalRearMirrorAntiglareState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoExternalRearMirrorAntiglareState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorAntiglareState();
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        Log.d(TAG, "getAutoExternalRearMirrorFoldActionState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoExternalRearMirrorFoldActionState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldActionState();
    }

    public int getAutoExternalRearMirrorFoldedState() {
        Log.d(TAG, "getAutoExternalRearMirrorFoldedState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoExternalRearMirrorFoldedState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldedState();
    }

    public int setAutoExternalRearMirrorFoldState(int state) {
        Log.d(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_SET);
        if (1 == this.mForTest) {
            Log.d(TAG, "setAutoExternalRearMirrorFoldState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setAutoExternalRearMirrorFoldState(state);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener listener, int[] featureIDs) {
        Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe door mirror listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        if (!this.mAbsBYDAutoRearViewMirrorListeners.contains(listener)) {
            this.mAbsBYDAutoRearViewMirrorListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        Log.d(TAG, "postEvent is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        return false;
    }

    public int getType() {
        Log.d(TAG, "getType is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return 0;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return new int[1];
    }

    public void unregisterListener(AbsBYDAutoRearViewMirrorListener listener) {
        Log.d(TAG, "unsubscribeMirrorCallback: " + listener);
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoRearViewMirrorListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 2169) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldActionState();
                        sparseArray.put(2169, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 2170) {
                        Log.d(TAG, "case fail ! please check out");
                    } else {
                        bydAutoEventValue.intValue = getAutoExternalRearMirrorFoldedState();
                        sparseArray.put(2170, Integer.valueOf(bydAutoEventValue.intValue));
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
        for (AbsBYDAutoRearViewMirrorListener listener : this.mAbsBYDAutoRearViewMirrorListeners) {
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
            Log.d(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 2015) {
            setAutoExternalRearMirrorFoldState(intValue);
            Log.d(TAG, "setAutoExternalRearMirrorFoldS" + intValue);
            return 1;
        }
        Log.d(TAG, "case fail ! please check out");
        return -1;
    }
}
