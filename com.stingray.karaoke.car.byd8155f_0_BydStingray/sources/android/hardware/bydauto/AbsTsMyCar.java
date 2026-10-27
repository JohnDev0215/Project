package android.hardware.bydauto;

import android.content.Context;
import b.d;
import h6.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsTsMyCar {
    protected static final int S_SET_FAILED = -2147482648;
    private static final String TAG = "AbsTsMyCar";
    private final BYDAutoEventValue mBydAutoEventValue = new BYDAutoEventValue();
    private List<Integer> mFeatureIds = new ArrayList();
    private final List<d> mBydAutoListeners = new ArrayList();

    public AbsTsMyCar() {
        n.j(TAG, "constructor execute!");
        TsManagerImpl.registerConnectListener(new TsConnectListener() { // from class: android.hardware.bydauto.AbsTsMyCar.1
            @Override // android.hardware.bydauto.TsConnectListener
            public void isConnected(boolean z6) {
                android.util.Log.v(AbsTsMyCar.TAG, "registerConnectListener result:" + z6);
                Iterator it = AbsTsMyCar.this.mBydAutoListeners.iterator();
                while (it.hasNext()) {
                    ((d) it.next()).isConnected(z6);
                }
            }
        });
    }

    public void caseFeatureIds(int i7, Object obj) {
        android.util.Log.v(TAG, "caseFeatureIds:" + getClass().getName() + ",featureId:" + i7);
        synchronized (this.mFeatureIds) {
            try {
                if (this.mFeatureIds.isEmpty() || this.mFeatureIds.contains(Integer.valueOf(i7))) {
                    if (obj instanceof Integer) {
                        this.mBydAutoEventValue.intValue = ((Integer) obj).intValue();
                        android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.intValue);
                    } else if (obj instanceof Float) {
                        this.mBydAutoEventValue.floatValue = ((Float) obj).floatValue();
                        android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.floatValue);
                    } else if (obj instanceof Double) {
                        this.mBydAutoEventValue.doubleValue = ((Double) obj).doubleValue();
                        android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.doubleValue);
                    } else if (obj instanceof int[]) {
                        this.mBydAutoEventValue.intArrayValue = (int[]) obj;
                        android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.intArrayValue);
                    } else if (obj instanceof float[]) {
                        this.mBydAutoEventValue.floatArrayValue = (float[]) obj;
                        android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.floatArrayValue);
                    } else if (obj instanceof String) {
                        String str = (String) obj;
                        this.mBydAutoEventValue.bufferDataValue = str.getBytes();
                        android.util.Log.v(TAG, ":".concat(str));
                    } else {
                        android.util.Log.v(TAG, "the type of value is not a suitable type");
                    }
                    onDataEventChanged(i7, this.mBydAutoEventValue);
                } else {
                    android.util.Log.v(TAG, getClass().getName() + "feature id " + i7 + " isn't contained");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public List<Integer> getFeatureIds() {
        return this.mFeatureIds;
    }

    public abstract void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue);

    public void registerConnectListener(Context context, d dVar) {
        android.util.Log.v(TAG, "registerConnectListener result:" + dVar);
        synchronized (this.mBydAutoListeners) {
            try {
                if (!this.mBydAutoListeners.contains(dVar)) {
                    this.mBydAutoListeners.add(dVar);
                    android.util.Log.v(TAG, "registerConnectListener :" + TsManagerImpl.isConnectSuccess());
                    if (TsManagerImpl.isConnectSuccess()) {
                        dVar.isConnected(true);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr == null || iArr.length < 1) {
            android.util.Log.d(TAG, "please check if the array is null or empty");
            return -2147482648;
        }
        if (iArr.length == 1) {
            int i7 = bYDAutoEventValue.intValue;
            if (i7 != -999999999) {
                setForOneFeatureId(iArr[0], Integer.valueOf(i7));
            } else {
                double d = bYDAutoEventValue.doubleValue;
                if (d != -9.99999999E8d) {
                    setForOneFeatureId(iArr[0], Double.valueOf(d));
                } else {
                    byte[] bArr = bYDAutoEventValue.bufferDataValue;
                    if (bArr == null) {
                        android.util.Log.d(TAG, "the type of value is not a suitable type");
                        return -2147482648;
                    }
                    setForOneFeatureId(iArr[0], bArr);
                }
            }
            return 0;
        }
        int[] iArr2 = bYDAutoEventValue.intArrayValue;
        if (iArr2 != null && iArr.length == iArr2.length) {
            int i10 = 0;
            while (true) {
                int[] iArr3 = bYDAutoEventValue.intArrayValue;
                if (i10 >= iArr3.length) {
                    break;
                }
                setForOneFeatureId(iArr[i10], Integer.valueOf(iArr3[i10]));
                i10++;
            }
        } else {
            float[] fArr = bYDAutoEventValue.floatArrayValue;
            if (fArr != null && iArr.length == fArr.length) {
                int i11 = 0;
                while (true) {
                    float[] fArr2 = bYDAutoEventValue.floatArrayValue;
                    if (i11 >= fArr2.length) {
                        break;
                    }
                    setForOneFeatureId(iArr[i11], Float.valueOf(fArr2[i11]));
                    i11++;
                }
            } else {
                android.util.Log.d(TAG, "the type of value is not a suitable type");
                return -2147482648;
            }
        }
        return 0;
    }

    public void setFeatureIds(int[] iArr) {
        synchronized (this.mFeatureIds) {
            try {
                this.mFeatureIds.clear();
                for (int i7 : iArr) {
                    this.mFeatureIds.add(Integer.valueOf(i7));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract int setForOneFeatureId(int i7, Object obj);

    public void unregisterConnectListener(d dVar) {
        android.util.Log.v(TAG, "unregisterConnectListener result:" + dVar);
        synchronized (this.mBydAutoListeners) {
            if (dVar != null) {
                try {
                    if (this.mBydAutoListeners.contains(dVar)) {
                        this.mBydAutoListeners.remove(dVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
