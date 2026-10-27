package android.hardware.bydauto.showcarmode.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.showcarmode.IShowCarModelChangeListener;
import android.util.SparseArray;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ShowCarModeImpl extends AbsTsMyCar {
    public static final String TAG = "ShowCarModeImpl";
    private static volatile ShowCarModeImpl sInstance;
    private Context mContext;
    private final CopyOnWriteArrayList<IShowCarModelChangeListener> mShowCarModelChangeListeners = new CopyOnWriteArrayList<>();
    private TsManagerImpl mTsManagerImpl;

    public ShowCarModeImpl(Context context) {
        this.mContext = context;
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.showcarmode.tsimpl.ShowCarModeImpl.1
            public void onAssistChanged(int i8, int i9, CarAssistPacket carAssistPacket) {
                if (ShowCarModeImpl.this.mShowCarModelChangeListeners.isEmpty()) {
                    return;
                }
                try {
                    ShowCarModeImpl.this.assistCallback(i8, i9, carAssistPacket);
                } catch (Exception unused) {
                    Log.e(ShowCarModeImpl.TAG, "assistCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z7) {
                Log.v(ShowCarModeImpl.TAG, "onBinderStateChanged is " + z7);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int i8, int i9, CarAssistPacket carAssistPacket) {
        Log.i(TAG, "AssistCallback propertyId:" + i8);
        switch (i8) {
            case CarModeHalId.ID_SHOW_CAR_MODEL_TYPER /* 557871104 */:
                int showCarModelTypeR = carAssistPacket.getShowCarModelTypeR();
                Iterator<IShowCarModelChangeListener> it = this.mShowCarModelChangeListeners.iterator();
                while (it.hasNext()) {
                    it.next().showCarModelChange(showCarModelTypeR);
                }
                break;
            case CarModeHalId.ID_AIR_CTRL_CHANGE_EVENT_R /* 557871106 */:
                int airCtrlChangeEventR = carAssistPacket.getAirCtrlChangeEventR();
                Iterator<IShowCarModelChangeListener> it2 = this.mShowCarModelChangeListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onAirCtrlChangeEvent(airCtrlChangeEventR);
                }
                break;
            case CarModeHalId.ID_AIR_CTRL_PRESS_EVENT_R /* 557871107 */:
                int airCtrlPressEventR = carAssistPacket.getAirCtrlPressEventR();
                Iterator<IShowCarModelChangeListener> it3 = this.mShowCarModelChangeListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onAirCtrlPressEvent(airCtrlPressEventR);
                }
                break;
            case CarModeHalId.ID_SHOW_CAR_GEAR_CHANGER /* 557871108 */:
                int showCarGearChangeR = carAssistPacket.getShowCarGearChangeR();
                Iterator<IShowCarModelChangeListener> it4 = this.mShowCarModelChangeListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().showCarGearChange(showCarGearChangeR);
                }
                break;
        }
    }

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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i8 : iArr) {
                if (i8 > 0) {
                    if (i8 == 2444) {
                        int carEngineType = getCarEngineType();
                        bYDAutoEventValue.intValue = carEngineType;
                        sparseArray.put(2444, Integer.valueOf(carEngineType));
                    }
                    int i9 = 0;
                    for (int i10 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i9] = ((Integer) sparseArray.get(i10)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i9++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getCarEngineType() {
        Log.v(TAG, "getCarEngineType is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getCarEngineType();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getDeviceType() {
        return 0;
    }

    public String getGetPermission() {
        return "";
    }

    public String getSetPermission() {
        return "";
    }

    public int getShowCarModelType() {
        Log.v(TAG, "getShowCarModelType is execute");
        return this.mTsManagerImpl.getCarAssistAdapterManager().getShowCarModelType();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerListener(IShowCarModelChangeListener iShowCarModelChangeListener) {
        registerListener(iShowCarModelChangeListener, new int[0]);
    }

    @Deprecated
    public void setEngineEnable(boolean z7) {
        Log.v(TAG, "setEngineEnable is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setEngineEnable(z7);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i8, Object obj) {
        return 0;
    }

    public void setShowCarModelType(int i8) {
        Log.v(TAG, "setShowCarModelType is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setShowCarModelType(i8);
        }
    }

    public void unRegisterListener(IShowCarModelChangeListener iShowCarModelChangeListener) {
        Log.d(TAG, "unregisterListener is run. ");
        if (iShowCarModelChangeListener == null) {
            return;
        }
        if (this.mShowCarModelChangeListeners.contains(iShowCarModelChangeListener)) {
            this.mShowCarModelChangeListeners.remove(iShowCarModelChangeListener);
        }
        unregisterConnectListener(iShowCarModelChangeListener);
    }

    public void registerListener(IShowCarModelChangeListener iShowCarModelChangeListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe carmode listener " + iShowCarModelChangeListener + " featureIDs = " + Arrays.toString(iArr));
        if (iShowCarModelChangeListener == null) {
            return;
        }
        if (!this.mShowCarModelChangeListeners.contains(iShowCarModelChangeListener)) {
            this.mShowCarModelChangeListeners.add(iShowCarModelChangeListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, iShowCarModelChangeListener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }
}
