package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoListener;
import java.util.ArrayList;
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
            public void isConnected(boolean isConnected) {
                android.util.Log.v(AbsTsMyCar.TAG, "registerConnectListener result:" + isConnected);
                for (IBYDAutoListener listener : AbsTsMyCar.this.mBydAutoListeners) {
                    listener.isConnected(isConnected);
                }
            }

            @Override // android.hardware.bydauto.TsConnectListener
            public void isOptimizationApi(boolean isOptimizationApi) {
                android.util.Log.v(AbsTsMyCar.TAG, "isOptimizationApi result:" + isOptimizationApi);
                for (IBYDAutoListener listener : AbsTsMyCar.this.mBydAutoListeners) {
                    listener.isOptimizationApi(isOptimizationApi);
                }
            }
        });
    }

    public void registerConnectListener(Context context, IBYDAutoListener listener) {
        android.util.Log.v(TAG, "registerConnectListener result:" + listener);
        synchronized (this.mBydAutoListeners) {
            if (!this.mBydAutoListeners.contains(listener)) {
                this.mBydAutoListeners.add(listener);
                android.util.Log.e(TAG, "isReturnCallback state is :" + TsManagerImpl.isReturnCallback);
                if (TsManagerImpl.isReturnCallback) {
                    listener.isOptimizationApi(TsManagerImpl.isConnect);
                }
                android.util.Log.v(TAG, "registerConnectListener :" + TsManagerImpl.isConnectSuccess());
                if (TsManagerImpl.isConnectSuccess()) {
                    listener.isConnected(true);
                }
            }
        }
    }

    public void unregisterConnectListener(IBYDAutoListener listener) {
        android.util.Log.v(TAG, "unregisterConnectListener result:" + listener);
        synchronized (this.mBydAutoListeners) {
            if (listener != null) {
                if (this.mBydAutoListeners.contains(listener)) {
                    this.mBydAutoListeners.remove(listener);
                }
            }
        }
    }

    public void setFeatureIds(int[] featureIds) {
        synchronized (this.mFeatureIds) {
            for (int id : featureIds) {
                if (!this.mFeatureIds.contains(Integer.valueOf(id))) {
                    this.mFeatureIds.add(Integer.valueOf(id));
                }
            }
        }
    }

    public void caseFeatureIds(int featureId, Object getNums) {
        android.util.Log.v(TAG, "caseFeatureIds:" + getClass().getName() + ",featureId:" + featureId);
        synchronized (this.mFeatureIds) {
            if (this.mFeatureIds.isEmpty() || this.mFeatureIds.contains(Integer.valueOf(featureId))) {
                if (getNums instanceof Integer) {
                    this.mBydAutoEventValue.intValue = ((Integer) getNums).intValue();
                    android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.intValue);
                } else if (getNums instanceof Long) {
                    this.mBydAutoEventValue.longValue = ((Long) getNums).longValue();
                    android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.longValue);
                } else if (getNums instanceof Float) {
                    this.mBydAutoEventValue.floatValue = ((Float) getNums).floatValue();
                    android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.floatValue);
                } else if (getNums instanceof Double) {
                    this.mBydAutoEventValue.doubleValue = ((Double) getNums).doubleValue();
                    android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.doubleValue);
                } else if (getNums instanceof int[]) {
                    this.mBydAutoEventValue.intArrayValue = (int[]) getNums;
                    android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.intArrayValue);
                } else if (getNums instanceof float[]) {
                    this.mBydAutoEventValue.floatArrayValue = (float[]) getNums;
                    android.util.Log.v(TAG, ":" + this.mBydAutoEventValue.floatArrayValue);
                } else if (getNums instanceof String) {
                    String str = (String) getNums;
                    this.mBydAutoEventValue.bufferDataValue = str.getBytes();
                    android.util.Log.v(TAG, ":" + str);
                } else {
                    android.util.Log.v(TAG, "the type of value is not a suitable type");
                }
                onDataEventChanged(featureId, this.mBydAutoEventValue);
            } else {
                android.util.Log.v(TAG, getClass().getName() + "feature id " + featureId + " isn't contained");
            }
        }
    }

    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        if (eventTypes != null && eventTypes.length >= 1) {
            if (eventTypes.length == 1) {
                if (eventValue.intValue != -999999999) {
                    setForOneFeatureId(eventTypes[0], Integer.valueOf(eventValue.intValue));
                } else if (eventValue.doubleValue != -9.99999999E8d) {
                    setForOneFeatureId(eventTypes[0], Double.valueOf(eventValue.doubleValue));
                } else if (eventValue.bufferDataValue != null) {
                    setForOneFeatureId(eventTypes[0], eventValue.bufferDataValue);
                } else {
                    android.util.Log.d(TAG, "the type of value is not a suitable type");
                    return -2147482648;
                }
                return 0;
            }
            if (eventValue.intArrayValue != null && eventTypes.length == eventValue.intArrayValue.length) {
                for (int i = 0; i < eventValue.intArrayValue.length; i++) {
                    setForOneFeatureId(eventTypes[i], Integer.valueOf(eventValue.intArrayValue[i]));
                }
            } else if (eventValue.floatArrayValue != null && eventTypes.length == eventValue.floatArrayValue.length) {
                for (int i2 = 0; i2 < eventValue.floatArrayValue.length; i2++) {
                    setForOneFeatureId(eventTypes[i2], Float.valueOf(eventValue.floatArrayValue[i2]));
                }
            } else if (eventValue.longArrayValue != null && eventTypes.length == eventValue.longArrayValue.length) {
                for (int i3 = 0; i3 < eventValue.longArrayValue.length; i3++) {
                    setForOneFeatureId(eventTypes[i3], Long.valueOf(eventValue.longArrayValue[i3]));
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
