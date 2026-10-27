package com.ts.carplay.app.service.proxy.telephony;

import android.content.Context;
import android.os.Looper;
import com.ts.carplay.app.service.common.IProxy;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.utils.LogUtil;
import ts.car.service.carplay.data.CallStateInfo;

/* JADX INFO: loaded from: classes.dex */
public class TelephonyProxy extends IProxy {
    private static final String TAG = "TelephonyProxy";
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private ICarPlayRemoteManagerListener mCarPlayRemoteManagerListener;
    private Context mContext;

    public TelephonyProxy(Looper looper) {
        super(looper);
        this.mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener();
    }

    @Override // com.ts.carplay.app.service.common.IProxy
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        CarPlayRemoteManager carPlayRemoteManager = CarPlayRemoteManager.getInstance();
        this.mCarPlayRemoteManager = carPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.addCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
        }
    }

    @Override // com.ts.carplay.app.service.common.IProxy
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.removeCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
            this.mCarPlayRemoteManager = null;
        }
        removeCallbacksAndMessages(null);
    }

    private class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        private CarPlayRemoteManagerListener() {
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCallStateUpdate(CallStateInfo info) {
        }
    }
}
