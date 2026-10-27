package com.ts.appservice.power.carplay;

import android.content.Context;
import android.os.RemoteException;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.power.util.LogUtil;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayRemoteManager {
    private static final String APPLICATION_CLASS_NAME = "com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity";
    private static final String APPLICATION_PACKAGE_NAME = "com.ts.carplay.app";
    private static final String TAG = "power_CarPlayRemoteManager";
    private static CarPlayRemoteManager mCarPlayRemoteManager = null;
    private Context mContext;
    private boolean isCarPlayLink = false;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private CarPlayStateListener mCarPlayStateListener = new CarPlayStateListener();
    private final IServiceConnectionListener mAppConnectionListener = new IServiceConnectionListener() { // from class: com.ts.appservice.power.carplay.CarPlayRemoteManager.1
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            if (CarPlayRemoteManager.this.mCarPlayAppManager != null) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "registerCarPlayStateListener.");
                try {
                    CarPlayRemoteManager.this.mCarPlayAppManager.registerCarPlayStateListener(CarPlayRemoteManager.this.mCarPlayStateListener);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            if (CarPlayRemoteManager.this.mCarPlayAppManager != null) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "unregisterCarPlayStateListener.");
                try {
                    CarPlayRemoteManager.this.mCarPlayAppManager.unregisterCarPlayStateListener(CarPlayRemoteManager.this.mCarPlayStateListener);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        }
    };

    public boolean isCarPlayLink() {
        return this.isCarPlayLink;
    }

    private CarPlayRemoteManager(Context context) {
        LogUtil.info(TAG, "CarPlayRemoteManager start");
        init(context);
    }

    public static synchronized CarPlayRemoteManager getInstance(Context context) {
        if (mCarPlayRemoteManager == null) {
            mCarPlayRemoteManager = new CarPlayRemoteManager(context);
        }
        return mCarPlayRemoteManager;
    }

    private void init(Context context) {
        LogUtil.debug(TAG, "init start.");
        this.mContext = context;
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, context);
        this.mCarPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            LogUtil.debug(TAG, "registerConnectionListener: mAppConnectionListener Connected:" + this.mCarPlayAppManager.isServiceConnected());
            this.mCarPlayAppManager.registerConnectionListener(this.mAppConnectionListener);
        }
    }

    public void quitSafely() {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.unregisterConnectionListener(this.mAppConnectionListener);
            try {
                this.mCarPlayAppManager.unregisterCarPlayStateListener(this.mCarPlayStateListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            this.mCarPlayAppManager = null;
        }
    }

    private class CarPlayStateListener implements ICarPlayStateListener {
        private CarPlayStateListener() {
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onAppStateChanged(int appState, boolean enabled, int speechMode) {
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(int status) {
            if (status == 1 || status == 2) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "isCarPlayLink true");
                CarPlayRemoteManager.this.isCarPlayLink = true;
            } else {
                LogUtil.debug(CarPlayRemoteManager.TAG, "isCarPlayLink false");
                CarPlayRemoteManager.this.isCarPlayLink = false;
            }
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) {
        }
    }
}
