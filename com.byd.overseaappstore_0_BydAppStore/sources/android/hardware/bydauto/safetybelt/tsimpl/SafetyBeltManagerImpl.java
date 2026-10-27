package android.hardware.bydauto.safetybelt.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.safetybelt.AbsBYDAutoSafetyBeltListener;
import android.hardware.bydauto.safetybelt.BYDAutoSafetyBeltDevice;
import android.provider.Settings;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.general.CarGeneralAdapterManager;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class SafetyBeltManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_SafetyBeltManagerImpl";
    public static final String TAG = "SafetyBeltManagerImpl";
    private static volatile SafetyBeltManagerImpl mSafetyBeltManagerImpl;
    private CarAdapterManager mCarAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TsManagerImpl mTsManagerImpl;
    private CarGeneralAdapterManager mTsManger;
    private final CopyOnWriteArrayList<AbsBYDAutoSafetyBeltListener> mAbsBydAutoSafetyBeltListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private SafetyBeltManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.safetybelt.tsimpl.SafetyBeltManagerImpl.1
            public void onCarClusterBrightnessChanged(int i2) {
            }

            public void onCarGeneralBinderChanged(boolean z) {
            }

            public void onCarGeneralChanged(int i2, int i3, GeneralPacket generalPacket) {
                if (SafetyBeltManagerImpl.this.mAbsBydAutoSafetyBeltListeners.isEmpty()) {
                    return;
                }
                try {
                    SafetyBeltManagerImpl.this.safeBeltCallback(i2, i3, generalPacket);
                } catch (Exception unused) {
                    Log.e(SafetyBeltManagerImpl.TAG, "safeBeltCallback is error");
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
    }

    public static SafetyBeltManagerImpl getInstance(Context context) {
        if (mSafetyBeltManagerImpl == null) {
            synchronized (SafetyBeltManagerImpl.class) {
                if (mSafetyBeltManagerImpl == null) {
                    mSafetyBeltManagerImpl = new SafetyBeltManagerImpl(context);
                }
            }
        }
        return mSafetyBeltManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeBeltCallback(int i, int i2, GeneralPacket generalPacket) {
        if (generalPacket == null) {
            Log.e(TAG, "generaPacket is null");
        }
        e.t("generaPacket propertyId:", i, TAG);
        if (i == 557860875) {
            Iterator<AbsBYDAutoSafetyBeltListener> it = this.mAbsBydAutoSafetyBeltListeners.iterator();
            while (it.hasNext()) {
                it.next().onDriverBeltStatusChanged(generalPacket.getDriverBeltStatusR());
            }
            return;
        }
        switch (i) {
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_MAIN /* 557860864 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it2 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onSafetyBeltStatusChanged(1, generalPacket.getSafetyBeltStatusMain());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_DEPUTY /* 557860865 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it3 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onSafetyBeltStatusChanged(2, generalPacket.getSafetyBeltStatusDeputy());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_LEFT_TWO_REAR /* 557860866 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it4 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onSafetyBeltStatusChanged(3, generalPacket.getSafetyBeltStatusLeftTwoRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_MID_TWO_REAR /* 557860867 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it5 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onSafetyBeltStatusChanged(5, generalPacket.getSafetyBeltStatusMidTwoRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_RIGHT_TWO_REAR /* 557860868 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it6 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onSafetyBeltStatusChanged(4, generalPacket.getSafetyBeltStatusRightTwoRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_LEFT_THREE_REAR /* 557860869 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it7 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onSafetyBeltStatusChanged(6, generalPacket.getSafetyBeltStatusLeftThreeRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_MID_THREE_REAR /* 557860870 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it8 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onSafetyBeltStatusChanged(8, generalPacket.getSafetyBeltStatusMidThreeRear());
                }
                break;
            case SafetyBeltHalid.ID_SAFETY_BELT_STATUS_RIGHT_THREE_REAR /* 557860871 */:
                Iterator<AbsBYDAutoSafetyBeltListener> it9 = this.mAbsBydAutoSafetyBeltListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onSafetyBeltStatusChanged(7, generalPacket.getSafetyBeltStatusRightThreeRear());
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r12, java.lang.Class<?> r13) {
        /*
            r11 = this;
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
            if (r1 >= r3) goto L1a
            r13.intValue = r2
            goto L83
        L1a:
            int r1 = r12.length
            r3 = 0
            r4 = r3
        L1d:
            if (r4 >= r1) goto L83
            r5 = r12[r4]
            if (r5 <= 0) goto L7e
            java.lang.String r6 = "SafetyBeltManagerImpl"
            r7 = 3929(0xf59, float:5.506E-42)
            if (r5 == r7) goto L50
            switch(r5) {
                case 4048: goto L46;
                case 4049: goto L3c;
                case 4050: goto L32;
                default: goto L2c;
            }
        L2c:
            java.lang.String r5 = "case fail ! please check out"
            android.hardware.bydauto.Log.v(r6, r5)
            goto L5e
        L32:
            r5 = 4
            int r5 = r11.getSafetyBeltStatus(r5)
            r13.intValue = r5
            r7 = 4050(0xfd2, float:5.675E-42)
            goto L57
        L3c:
            r5 = 5
            int r5 = r11.getSafetyBeltStatus(r5)
            r13.intValue = r5
            r7 = 4049(0xfd1, float:5.674E-42)
            goto L57
        L46:
            r5 = 3
            int r5 = r11.getSafetyBeltStatus(r5)
            r13.intValue = r5
            r7 = 4048(0xfd0, float:5.672E-42)
            goto L57
        L50:
            r5 = 2
            int r5 = r11.getSafetyBeltStatus(r5)
            r13.intValue = r5
        L57:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.put(r7, r5)
        L5e:
            int r5 = r12.length
            r7 = r3
            r8 = r7
        L61:
            if (r7 >= r5) goto L80
            r9 = r12[r7]
            java.lang.Object r9 = r0.get(r9)     // Catch: java.lang.Exception -> L74
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch: java.lang.Exception -> L74
            int r9 = r9.intValue()     // Catch: java.lang.Exception -> L74
            int[] r10 = r13.intArrayValue     // Catch: java.lang.Exception -> L74
            r10[r8] = r9     // Catch: java.lang.Exception -> L74
            goto L79
        L74:
            java.lang.String r9 = "Feature id doesn't exist"
            android.hardware.bydauto.Log.e(r6, r9)
        L79:
            int r8 = r8 + 1
            int r7 = r7 + 1
            goto L61
        L7e:
            r13.intValue = r2
        L80:
            int r4 = r4 + 1
            goto L1d
        L83:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.safetybelt.tsimpl.SafetyBeltManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int getDriverBeltStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDriverBeltStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getDriverBeltStatus();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPassengerStatus(int i) {
        String str;
        Log.v(TAG, "getPassengerStatus is execute");
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_GET);
        if (1 == this.mForTest) {
            str = "getPassengerStatus mForTestValue is 0 execute return";
        } else {
            CarGeneralAdapterManager carGeneralAdapterManager = this.mTsManger;
            if (carGeneralAdapterManager != null) {
                return carGeneralAdapterManager.getPassengerStatus(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSafetyBeltStatus(int i) {
        Log.v(TAG, "getSafetyBeltStatus is execute");
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_GET);
        if (1 == this.mForTest) {
            e.s(e.k("getSafetyBeltStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMain();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusDeputy();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusLeftTwoRear();
        }
        if (i == 5) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMidTwoRear();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusRightTwoRear();
        }
        if (i == 6) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusLeftThreeRear();
        }
        if (i == 8) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusMidThreeRear();
        }
        if (i == 7) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getSafetyBeltStatusRightThreeRear();
        }
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener) {
        registerListener(absBYDAutoSafetyBeltListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe safebelt listener ");
        sb.append(absBYDAutoSafetyBeltListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
        if (absBYDAutoSafetyBeltListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            e.u(e.k("registerListener one para mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_COMMON);
        if (!this.mAbsBydAutoSafetyBeltListeners.contains(absBYDAutoSafetyBeltListener)) {
            this.mAbsBydAutoSafetyBeltListeners.add(absBYDAutoSafetyBeltListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoSafetyBeltListener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoSafetyBeltDevice.PERMISSION_BYDAUTO_SAFEBELT_COMMON);
        if (1 == this.mForTest) {
            e.u(e.k("unregisterListener mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            this.mAbsBydAutoSafetyBeltListeners.remove(absBYDAutoSafetyBeltListener);
            unregisterConnectListener(absBYDAutoSafetyBeltListener);
        }
    }
}
