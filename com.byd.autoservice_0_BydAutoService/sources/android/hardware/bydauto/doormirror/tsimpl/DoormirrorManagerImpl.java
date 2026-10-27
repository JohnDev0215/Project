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

    public DoormirrorManagerImpl(Context context) {
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        this.mTsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doormirror.tsimpl.DoormirrorManagerImpl.1
            public void onCabinBinderChanged(boolean z3) {
                Log.d(DoormirrorManagerImpl.TAG, "cabin call back state :" + z3);
            }

            public void onCabinChanged(int i3, int i4, CabinPacket cabinPacket) {
                DoormirrorManagerImpl.this.doorMirrorCallBack(i3, i4, cabinPacket);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorMirrorCallBack(int i3, int i4, CabinPacket cabinPacket) {
        Log.i(TAG, "doorMirrorCallBack propertyId:" + i3);
        switch (i3) {
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

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static synchronized DoormirrorManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DoormirrorManagerImpl(context);
        }
        return sInstance;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i3 : iArr) {
                if (i3 > 0) {
                    if (i3 == 2169) {
                        int autoExternalRearMirrorFoldActionState = this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldActionState();
                        bYDAutoEventValue.intValue = autoExternalRearMirrorFoldActionState;
                        sparseArray.put(2169, Integer.valueOf(autoExternalRearMirrorFoldActionState));
                    } else if (i3 != 2170) {
                        Log.d(TAG, "case fail ! please check out");
                    } else {
                        int autoExternalRearMirrorFoldedState = getAutoExternalRearMirrorFoldedState();
                        bYDAutoEventValue.intValue = autoExternalRearMirrorFoldedState;
                        sparseArray.put(2170, Integer.valueOf(autoExternalRearMirrorFoldedState));
                    }
                    int i4 = 0;
                    for (int i5 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i4] = ((Integer) sparseArray.get(i5)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i4++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAutoExternalRearMirrorAntiglareState() {
        Log.d(TAG, "getAutoExternalRearMirrorAntiglareState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorAntiglareState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAutoExternalRearMirrorAntiglareState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        Log.d(TAG, "getAutoExternalRearMirrorFoldActionState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldActionState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAutoExternalRearMirrorFoldActionState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldedState() {
        Log.d(TAG, "getAutoExternalRearMirrorFoldedState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldedState();
        }
        Log.d(TAG, "getAutoExternalRearMirrorFoldedState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getAutoExternalRearMirrorState() {
        Log.d(TAG, "getAutoExternalRearMirrorState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getAutoExternalRearMirrorState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype is run. ");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return 0;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return new int[1];
    }

    public String getGetPermission() {
        return "";
    }

    public String getSetPermission() {
        return "";
    }

    public int getType() {
        Log.d(TAG, "getType is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoRearViewMirrorListener> it = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i3, bYDAutoEventValue);
        }
    }

    public boolean postEvent(int i3, int i4, int i5, Object obj) {
        Log.d(TAG, "postEvent is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        return false;
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        registerListener(absBYDAutoRearViewMirrorListener, new int[0]);
    }

    public int setAutoExternalRearMirrorFoldState(int i3) {
        Log.d(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setAutoExternalRearMirrorFoldState(i3);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "setAutoExternalRearMirrorFoldState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i3, Object obj) {
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
                    Log.d(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i3 != 2015) {
            Log.d(TAG, "case fail ! please check out");
            return -1;
        }
        setAutoExternalRearMirrorFoldState(iIntValue);
        Log.d(TAG, "setAutoExternalRearMirrorFoldS" + iIntValue);
        return 1;
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
}
