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
import java.util.Iterator;
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

    public String getGetPermission() {
        return "";
    }

    public String getSetPermission() {
        return "";
    }

    DoormirrorManagerImpl(Context context) {
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        this.mTsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doormirror.tsimpl.DoormirrorManagerImpl.1
            public void onCabinChanged(int i, int i2, CabinPacket cabinPacket) {
                DoormirrorManagerImpl.this.doorMirrorCallBack(i, i2, cabinPacket);
            }

            public void onCabinBinderChanged(boolean z) {
                Log.d(DoormirrorManagerImpl.TAG, "cabin call back state :" + z);
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorMirrorCallBack(int i, int i2, CabinPacket cabinPacket) {
        Log.i(TAG, "doorMirrorCallBack propertyId:" + i);
        switch (i) {
            case 557858048:
                Iterator<AbsBYDAutoRearViewMirrorListener> it = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAutoExternalRearMirrorFoldActionStateChanged(cabinPacket.getAutoExternalRearMirrorFoldActionStateR());
                }
                caseFeatureIds(2169, Integer.valueOf(cabinPacket.getAutoExternalRearMirrorFoldActionStateR()));
                break;
            case 557858049:
                Iterator<AbsBYDAutoRearViewMirrorListener> it2 = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onAutoExternalRearMirrorFoldedState(cabinPacket.getAutoExternalRearMirrorFoldedStateR());
                }
                caseFeatureIds(2170, Integer.valueOf(cabinPacket.getAutoExternalRearMirrorFoldedStateR()));
                break;
        }
    }

    public static synchronized DoormirrorManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DoormirrorManagerImpl(context);
        }
        return sInstance;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype is run. ");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return 0;
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

    public int setAutoExternalRearMirrorFoldState(int i) {
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
        return this.mTsManagerImpl.getCarCabinAdapterManager().setAutoExternalRearMirrorFoldState(i);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        registerListener(absBYDAutoRearViewMirrorListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener, int[] iArr) {
        Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe door mirror listener " + absBYDAutoRearViewMirrorListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoRearViewMirrorListener == null) {
            return;
        }
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        if (!this.mAbsBYDAutoRearViewMirrorListeners.contains(absBYDAutoRearViewMirrorListener)) {
            this.mAbsBYDAutoRearViewMirrorListeners.add(absBYDAutoRearViewMirrorListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoRearViewMirrorListener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
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

    public void unregisterListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        Log.d(TAG, "unsubscribeMirrorCallback: " + absBYDAutoRearViewMirrorListener);
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        if (absBYDAutoRearViewMirrorListener == null) {
            return;
        }
        this.mAbsBYDAutoRearViewMirrorListeners.remove(absBYDAutoRearViewMirrorListener);
        unregisterConnectListener(absBYDAutoRearViewMirrorListener);
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 2169) {
                        bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldActionState();
                        sparseArray.put(2169, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 2170) {
                        bYDAutoEventValue.intValue = getAutoExternalRearMirrorFoldedState();
                        sparseArray.put(2170, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else {
                        Log.d(TAG, "case fail ! please check out");
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
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoRearViewMirrorListener> it = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
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
                Log.d(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i == 2015) {
            setAutoExternalRearMirrorFoldState(iIntValue);
            Log.d(TAG, "setAutoExternalRearMirrorFoldS" + iIntValue);
            return 1;
        }
        Log.d(TAG, "case fail ! please check out");
        return -1;
    }
}
