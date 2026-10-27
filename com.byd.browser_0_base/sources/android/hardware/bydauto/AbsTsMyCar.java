package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsTsMyCar {
    protected static final int S_SET_FAILED = -2147482648;
    private static final String TAG = "AbsTsMyCar";
    private final BYDAutoEventValue mBydAutoEventValue = new BYDAutoEventValue();
    private List<Integer> mFeatureIds = new ArrayList();
    private final List<IBYDAutoListener> mBydAutoListeners = new ArrayList();

    public AbsTsMyCar() {
        android.util.Log.e(TAG, "constructor execute!");
        TsManagerImpl.registerConnectListener(new TsConnectListener() { // from class: android.hardware.bydauto.AbsTsMyCar.1
            @Override // android.hardware.bydauto.TsConnectListener
            public void isConnected(boolean z) {
                Iterator it = AbsTsMyCar.this.mBydAutoListeners.iterator();
                while (it.hasNext()) {
                    ((IBYDAutoListener) it.next()).isConnected(z);
                }
            }

            @Override // android.hardware.bydauto.TsConnectListener
            public void isOptimizationApi(boolean z) {
                Iterator it = AbsTsMyCar.this.mBydAutoListeners.iterator();
                while (it.hasNext()) {
                    ((IBYDAutoListener) it.next()).isOptimizationApi(z);
                }
            }
        });
    }

    public void caseFeatureIds(int i, Object obj) {
        synchronized (this.mFeatureIds) {
            if (this.mFeatureIds.isEmpty() || this.mFeatureIds.contains(Integer.valueOf(i))) {
                if (obj instanceof Integer) {
                    this.mBydAutoEventValue.intValue = ((Integer) obj).intValue();
                    int i2 = this.mBydAutoEventValue.intValue;
                } else if (obj instanceof Float) {
                    this.mBydAutoEventValue.floatValue = ((Float) obj).floatValue();
                    float f = this.mBydAutoEventValue.floatValue;
                } else if (obj instanceof Double) {
                    this.mBydAutoEventValue.doubleValue = ((Double) obj).doubleValue();
                    double d = this.mBydAutoEventValue.doubleValue;
                } else if (obj instanceof int[]) {
                    this.mBydAutoEventValue.intArrayValue = (int[]) obj;
                } else if (obj instanceof float[]) {
                    this.mBydAutoEventValue.floatArrayValue = (float[]) obj;
                } else if (obj instanceof String) {
                    this.mBydAutoEventValue.bufferDataValue = ((String) obj).getBytes();
                }
                onDataEventChanged(i, this.mBydAutoEventValue);
            }
        }
    }

    public List<Integer> getFeatureIds() {
        return this.mFeatureIds;
    }

    public abstract void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    public void registerConnectListener(Context context, IBYDAutoListener iBYDAutoListener) {
        Objects.toString(iBYDAutoListener);
        synchronized (this.mBydAutoListeners) {
            if (!this.mBydAutoListeners.contains(iBYDAutoListener)) {
                this.mBydAutoListeners.add(iBYDAutoListener);
                android.util.Log.e(TAG, "isReturnCallback state is :" + TsManagerImpl.isReturnCallback);
                if (TsManagerImpl.isReturnCallback) {
                    iBYDAutoListener.isOptimizationApi(true);
                }
                TsManagerImpl.isConnectSuccess();
                if (TsManagerImpl.isConnectSuccess()) {
                    iBYDAutoListener.isConnected(true);
                }
            }
        }
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr != null && iArr.length >= 1) {
            if (iArr.length == 1) {
                int i = bYDAutoEventValue.intValue;
                if (i != -999999999) {
                    setForOneFeatureId(iArr[0], Integer.valueOf(i));
                } else {
                    double d = bYDAutoEventValue.doubleValue;
                    if (d != -9.99999999E8d) {
                        setForOneFeatureId(iArr[0], Double.valueOf(d));
                    } else {
                        byte[] bArr = bYDAutoEventValue.bufferDataValue;
                        if (bArr == null) {
                            return -2147482648;
                        }
                        setForOneFeatureId(iArr[0], bArr);
                    }
                }
                return 0;
            }
            int[] iArr2 = bYDAutoEventValue.intArrayValue;
            if (iArr2 != null && iArr.length == iArr2.length) {
                int i2 = 0;
                while (true) {
                    int[] iArr3 = bYDAutoEventValue.intArrayValue;
                    if (i2 >= iArr3.length) {
                        break;
                    }
                    setForOneFeatureId(iArr[i2], Integer.valueOf(iArr3[i2]));
                    i2++;
                }
            } else {
                float[] fArr = bYDAutoEventValue.floatArrayValue;
                if (fArr != null && iArr.length == fArr.length) {
                    int i3 = 0;
                    while (true) {
                        float[] fArr2 = bYDAutoEventValue.floatArrayValue;
                        if (i3 >= fArr2.length) {
                            break;
                        }
                        setForOneFeatureId(iArr[i3], Float.valueOf(fArr2[i3]));
                        i3++;
                    }
                }
            }
            return 0;
        }
        return -2147482648;
    }

    public void setFeatureIds(int[] iArr) {
        synchronized (this.mFeatureIds) {
            for (int i : iArr) {
                if (!this.mFeatureIds.contains(Integer.valueOf(i))) {
                    this.mFeatureIds.add(Integer.valueOf(i));
                }
            }
        }
    }

    public abstract int setForOneFeatureId(int i, Object obj);

    public void unregisterConnectListener(IBYDAutoListener iBYDAutoListener) {
        Objects.toString(iBYDAutoListener);
        synchronized (this.mBydAutoListeners) {
            if (iBYDAutoListener != null) {
                if (this.mBydAutoListeners.contains(iBYDAutoListener)) {
                    this.mBydAutoListeners.remove(iBYDAutoListener);
                }
            }
        }
    }
}
