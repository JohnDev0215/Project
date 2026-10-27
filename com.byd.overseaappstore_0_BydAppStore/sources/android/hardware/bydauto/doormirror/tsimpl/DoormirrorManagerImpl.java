package android.hardware.bydauto.doormirror.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.doormirror.AbsBYDAutoRearViewMirrorListener;
import android.hardware.bydauto.doormirror.BYDAutoRearViewMirrorDevice;
import android.util.Log;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

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
            public void onCabinBinderChanged(boolean z) {
                Log.d(DoormirrorManagerImpl.TAG, "cabin call back state :" + z);
            }

            public void onCabinChanged(int i, int i2, CabinPacket cabinPacket) {
                DoormirrorManagerImpl.this.doorMirrorCallBack(i, i2, cabinPacket);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorMirrorCallBack(int i, int i2, CabinPacket cabinPacket) {
        int i3;
        int autoExternalRearMirrorFoldActionStateR;
        Log.i(TAG, "doorMirrorCallBack propertyId:" + i);
        switch (i) {
            case 557858048:
                Iterator<AbsBYDAutoRearViewMirrorListener> it = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAutoExternalRearMirrorFoldActionStateChanged(cabinPacket.getAutoExternalRearMirrorFoldActionStateR());
                }
                i3 = 2169;
                autoExternalRearMirrorFoldActionStateR = cabinPacket.getAutoExternalRearMirrorFoldActionStateR();
                break;
            case 557858049:
                Iterator<AbsBYDAutoRearViewMirrorListener> it2 = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onAutoExternalRearMirrorFoldedState(cabinPacket.getAutoExternalRearMirrorFoldedStateR());
                }
                i3 = 2170;
                autoExternalRearMirrorFoldActionStateR = cabinPacket.getAutoExternalRearMirrorFoldedStateR();
                break;
            default:
                return;
        }
        caseFeatureIds(i3, Integer.valueOf(autoExternalRearMirrorFoldActionStateR));
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
    }

    public static synchronized DoormirrorManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DoormirrorManagerImpl(context);
        }
        return sInstance;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r12, java.lang.Class<?> r13) {
        /*
            r11 = this;
            java.lang.String r13 = "android.permission.BYDAUTO_REAR_VIEW_MIRROR_GET"
            r11.enforcePermission(r13)
            android.hardware.bydauto.BYDAutoEventValue r13 = new android.hardware.bydauto.BYDAutoEventValue
            r13.<init>()
            android.util.SparseArray r0 = new android.util.SparseArray
            r0.<init>()
            int r1 = r12.length
            int[] r1 = new int[r1]
            r13.intArrayValue = r1
            int r1 = r12.length
            r2 = -2147482648(0xffffffff800003e8, float:-1.401E-42)
            r3 = 1
            if (r1 >= r3) goto L1e
            r13.intValue = r2
            goto L74
        L1e:
            int r1 = r12.length
            r3 = 0
            r4 = r3
        L21:
            if (r4 >= r1) goto L74
            r5 = r12[r4]
            if (r5 <= 0) goto L6f
            java.lang.String r6 = "DoormirrorManagerImpl"
            r7 = 2169(0x879, float:3.04E-42)
            if (r5 == r7) goto L3c
            r7 = 2170(0x87a, float:3.041E-42)
            if (r5 == r7) goto L37
            java.lang.String r5 = "case fail ! please check out"
            android.util.Log.d(r6, r5)
            goto L4f
        L37:
            int r5 = r11.getAutoExternalRearMirrorFoldedState()
            goto L46
        L3c:
            android.hardware.bydauto.TsManagerImpl r5 = r11.mTsManagerImpl
            com.ts.lib.caradapter.cabin.CarCabinAdapterManager r5 = r5.getCarCabinAdapterManager()
            int r5 = r5.getAutoExternalRearMirrorFoldActionState()
        L46:
            r13.intValue = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.put(r7, r5)
        L4f:
            int r5 = r12.length
            r7 = r3
            r8 = r7
        L52:
            if (r7 >= r5) goto L71
            r9 = r12[r7]
            java.lang.Object r9 = r0.get(r9)     // Catch: java.lang.Exception -> L65
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch: java.lang.Exception -> L65
            int r9 = r9.intValue()     // Catch: java.lang.Exception -> L65
            int[] r10 = r13.intArrayValue     // Catch: java.lang.Exception -> L65
            r10[r8] = r9     // Catch: java.lang.Exception -> L65
            goto L6a
        L65:
            java.lang.String r9 = "Feature id doesn't exist"
            android.util.Log.e(r6, r9)
        L6a:
            int r8 = r8 + 1
            int r7 = r7 + 1
            goto L52
        L6f:
            r13.intValue = r2
        L71:
            int r4 = r4 + 1
            goto L21
        L74:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.doormirror.tsimpl.DoormirrorManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int getAutoExternalRearMirrorAntiglareState() {
        Log.d(TAG, "getAutoExternalRearMirrorAntiglareState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            e.v(e.k("getAutoExternalRearMirrorAntiglareState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorAntiglareState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        Log.d(TAG, "getAutoExternalRearMirrorFoldActionState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            e.v(e.k("getAutoExternalRearMirrorFoldActionState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldActionState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldedState() {
        Log.d(TAG, "getAutoExternalRearMirrorFoldedState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorFoldedState();
        }
        e.v(e.k("getAutoExternalRearMirrorFoldedState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getAutoExternalRearMirrorState() {
        Log.d(TAG, "getAutoExternalRearMirrorState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET);
        if (1 == this.mForTest) {
            e.v(e.k("getAutoExternalRearMirrorState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getAutoExternalRearMirrorState();
        }
        Log.e(TAG, "adapter is not connect");
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
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoRearViewMirrorListener> it = this.mAbsBYDAutoRearViewMirrorListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        Log.d(TAG, "postEvent is run");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON);
        return false;
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        registerListener(absBYDAutoRearViewMirrorListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe door mirror listener ");
        sb.append(absBYDAutoRearViewMirrorListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
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

    public int setAutoExternalRearMirrorFoldState(int i) {
        Log.d(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoRearViewMirrorDevice.PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_SET);
        if (1 == this.mForTest) {
            e.v(e.k("setAutoExternalRearMirrorFoldState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setAutoExternalRearMirrorFoldState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
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
                    Log.d(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i != 2015) {
            str = "case fail ! please check out";
            Log.d(TAG, str);
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
}
