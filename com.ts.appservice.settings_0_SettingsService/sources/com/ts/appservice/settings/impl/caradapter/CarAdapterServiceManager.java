package com.ts.appservice.settings.impl.caradapter;

import android.content.Context;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.interfaces.OnCarAdapterConnectListener;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarAdapterServiceManager {
    private static final String TAG = CarAdapterServiceManager.class.getSimpleName();
    private static volatile CarAdapterServiceManager sInstance = null;
    private CarAdapterManager mCarAdapterManager;
    private Context mContext;
    private boolean mBinderState = false;
    private OnCarAdapterConnectListener mOnCarAdapterConnectListener = new OnCarAdapterConnectListener() { // from class: com.ts.appservice.settings.impl.caradapter.CarAdapterServiceManager.1
        public void onBinderStateChanged(boolean binderState) {
            LogUtil.error(CarAdapterServiceManager.TAG, "binderState is " + binderState);
            CarAdapterServiceManager.this.mBinderState = binderState;
        }
    };

    public CarAdapterServiceManager(Context context) {
        this.mContext = context;
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(context);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(this.mOnCarAdapterConnectListener);
        this.mCarAdapterManager.connect();
    }

    public static synchronized CarAdapterServiceManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CarAdapterServiceManager(context);
        }
        return sInstance;
    }

    public boolean getCarAdapterBinderState() {
        LogUtil.error(TAG, "getCarAdapterBinderState is " + this.mBinderState);
        return this.mBinderState;
    }
}
