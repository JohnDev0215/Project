package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsTsMyCar {
    protected static final int S_SET_FAILED = -2147482648;
    private static final String TAG = "AbsTsMyCar";
    private final BYDAutoEventValue mBydAutoEventValue = new BYDAutoEventValue();
    private List<Integer> mFeatureIds = new ArrayList();
    private final List<IBYDAutoListener> mBydAutoListeners = new ArrayList();

    public abstract void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    protected abstract int setForOneFeatureId(int i, Object obj);

    public List<Integer> getFeatureIds() {
        return this.mFeatureIds;
    }

    public AbsTsMyCar() {
        android.util.Log.e(TAG, "constructor execute!");
        TsManagerImpl.registerConnectListener(new TsConnectListener() { // from class: android.hardware.bydauto.AbsTsMyCar.1
            @Override // android.hardware.bydauto.TsConnectListener
            public void isConnected(boolean z) {
                android.util.Log.v(AbsTsMyCar.TAG, "registerConnectListener result:" + z);
                Iterator it = AbsTsMyCar.this.mBydAutoListeners.iterator();
                while (it.hasNext()) {
                    ((IBYDAutoListener) it.next()).isConnected(z);
                }
            }
        });
    }

    public void registerConnectListener(Context context, IBYDAutoListener iBYDAutoListener) {
        android.util.Log.v(TAG, "registerConnectListener result:" + iBYDAutoListener);
        synchronized (this.mBydAutoListeners) {
            if (!this.mBydAutoListeners.contains(iBYDAutoListener)) {
                this.mBydAutoListeners.add(iBYDAutoListener);
                android.util.Log.v(TAG, "registerConnectListener :" + TsManagerImpl.isConnectSuccess());
                if (TsManagerImpl.isConnectSuccess()) {
                    iBYDAutoListener.isConnected(true);
                }
            }
        }
    }

    public void unregisterConnectListener(IBYDAutoListener iBYDAutoListener) {
        android.util.Log.v(TAG, "unregisterConnectListener result:" + iBYDAutoListener);
        synchronized (this.mBydAutoListeners) {
            if (iBYDAutoListener != null) {
                if (this.mBydAutoListeners.contains(iBYDAutoListener)) {
                    this.mBydAutoListeners.remove(iBYDAutoListener);
                }
            }
        }
    }

    public void setFeatureIds(int[] iArr) {
        synchronized (this.mFeatureIds) {
            this.mFeatureIds.clear();
            for (int i : iArr) {
                this.mFeatureIds.add(Integer.valueOf(i));
            }
        }
    }

    public void caseFeatureIds(int i, Object obj) {
        android.util.Log.v(TAG, "caseFeatureIds:" + getClass().getName() + ",featureId:" + i);
        synchronized (this.mFeatureIds) {
            if (this.mFeatureIds.isEmpty() || this.mFeatureIds.contains(Integer.valueOf(i))) {
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
                    android.util.Log.v(TAG, ":" + str);
                } else {
                    android.util.Log.v(TAG, "the type of value is not a suitable type");
                }
                onDataEventChanged(i, this.mBydAutoEventValue);
            } else {
                android.util.Log.v(TAG, getClass().getName() + "feature id " + i + " isn't contained");
            }
        }
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr != null && iArr.length >= 1) {
            if (iArr.length == 1) {
                if (bYDAutoEventValue.intValue != -999999999) {
                    setForOneFeatureId(iArr[0], Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (bYDAutoEventValue.doubleValue != -9.99999999E8d) {
                    setForOneFeatureId(iArr[0], Double.valueOf(bYDAutoEventValue.doubleValue));
                } else if (bYDAutoEventValue.bufferDataValue != null) {
                    setForOneFeatureId(iArr[0], bYDAutoEventValue.bufferDataValue);
                } else {
                    android.util.Log.d(TAG, "the type of value is not a suitable type");
                    return -2147482648;
                }
                return 0;
            }
            if (bYDAutoEventValue.intArrayValue != null && iArr.length == bYDAutoEventValue.intArrayValue.length) {
                for (int i = 0; i < bYDAutoEventValue.intArrayValue.length; i++) {
                    setForOneFeatureId(iArr[i], Integer.valueOf(bYDAutoEventValue.intArrayValue[i]));
                }
            } else if (bYDAutoEventValue.floatArrayValue != null && iArr.length == bYDAutoEventValue.floatArrayValue.length) {
                for (int i2 = 0; i2 < bYDAutoEventValue.floatArrayValue.length; i2++) {
                    setForOneFeatureId(iArr[i2], Float.valueOf(bYDAutoEventValue.floatArrayValue[i2]));
                }
            } else {
                android.util.Log.d(TAG, "the type of value is not a suitable type");
                return -2147482648;
            }
            return 0;
        }
        android.util.Log.d(TAG, "please check if the array is null or empty");
        return -2147482648;
    }
}
