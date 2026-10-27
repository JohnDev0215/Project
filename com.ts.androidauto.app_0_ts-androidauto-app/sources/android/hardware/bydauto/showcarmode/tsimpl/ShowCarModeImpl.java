package android.hardware.bydauto.showcarmode.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.showcarmode.IShowCarModelChangeListener;
import android.util.SparseArray;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ShowCarModeImpl extends AbsTsMyCar {
    protected static final String TAG = "ShowCarModeImpl";
    private static volatile ShowCarModeImpl sInstance;
    private Context mContext;
    private final CopyOnWriteArrayList<IShowCarModelChangeListener> mShowCarModelChangeListeners = new CopyOnWriteArrayList<>();
    private TsManagerImpl mTsManagerImpl;

    public static ShowCarModeImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ShowCarModeImpl.class) {
                if (sInstance == null) {
                    sInstance = new ShowCarModeImpl(context);
                }
            }
        }
        return sInstance;
    }

    ShowCarModeImpl(Context context) {
        this.mContext = context;
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.showcarmode.tsimpl.ShowCarModeImpl.1
            public void onAssistChanged(int id, int areaId, CarAssistPacket packet) {
                if (!ShowCarModeImpl.this.mShowCarModelChangeListeners.isEmpty()) {
                    try {
                        ShowCarModeImpl.this.assistCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(ShowCarModeImpl.TAG, "assistCallback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(ShowCarModeImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int id, int areaId, CarAssistPacket packet) {
        Log.i(TAG, "AssistCallback propertyId:" + id);
        switch (id) {
            case 557871104:
                int showCarModelTypeR = packet.getShowCarModelTypeR();
                for (IShowCarModelChangeListener listener : this.mShowCarModelChangeListeners) {
                    listener.showCarModelChange(showCarModelTypeR);
                }
                break;
            case 557871106:
                int airCtrlChangeEventR = packet.getAirCtrlChangeEventR();
                for (IShowCarModelChangeListener listener2 : this.mShowCarModelChangeListeners) {
                    listener2.onAirCtrlChangeEvent(airCtrlChangeEventR);
                }
                break;
            case 557871107:
                int airCtrlPressEventR = packet.getAirCtrlPressEventR();
                for (IShowCarModelChangeListener listener3 : this.mShowCarModelChangeListeners) {
                    listener3.onAirCtrlPressEvent(airCtrlPressEventR);
                }
                break;
            case 557871108:
                int showCarGearChangeR = packet.getShowCarGearChangeR();
                for (IShowCarModelChangeListener listener4 : this.mShowCarModelChangeListeners) {
                    listener4.showCarGearChange(showCarGearChangeR);
                }
                break;
        }
    }

    public int getDeviceType() {
        return 0;
    }

    public String getSetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public String getGetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public int getShowCarModelType() {
        Log.v(TAG, "getShowCarModelType is execute");
        return this.mTsManagerImpl.getCarAssistAdapterManager().getShowCarModelType();
    }

    public int getCarEngineType() {
        Log.v(TAG, "getCarEngineType is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getCarEngineType();
    }

    @Deprecated
    public void setEngineEnable(boolean allowable) {
        Log.v(TAG, "setEngineEnable is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setEngineEnable(allowable);
        }
    }

    public void setShowCarModelType(int display) {
        Log.v(TAG, "setShowCarModelType is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setShowCarModelType(display);
        }
    }

    public void registerListener(IShowCarModelChangeListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(IShowCarModelChangeListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe carmode listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (!this.mShowCarModelChangeListeners.contains(listener)) {
            this.mShowCarModelChangeListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unRegisterListener(IShowCarModelChangeListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        if (listener == null) {
            return;
        }
        if (this.mShowCarModelChangeListeners.contains(listener)) {
            this.mShowCarModelChangeListeners.remove(listener);
        }
        unregisterConnectListener(listener);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 2444) {
                        bydAutoEventValue.intValue = getCarEngineType();
                        sparseArray.put(2444, Integer.valueOf(bydAutoEventValue.intValue));
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
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        return 0;
    }
}
