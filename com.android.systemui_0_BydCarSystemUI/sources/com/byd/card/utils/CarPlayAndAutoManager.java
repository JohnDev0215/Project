package com.byd.card.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.ts.car.core.ServiceProvider;
import org.greenrobot.eventbus.EventBus;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.ICarPlayStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayAndAutoManager {
    private static final String OPEN_AUTO = "com.byd.card.open.auto";
    private static final String OPEN_CARPLAY = "com.byd.card.open.carplay";
    private static final String TAG = "CarPlayAndAutoManager";
    private static final String UE_PACKAGE = "com.byd.uereport";
    private static volatile CarPlayAndAutoManager sInstance;
    private OnAutoConnectStatusListener autoConnectStatusListener;
    private OnCarPlayConnectStatusListener carPlayConnectStatusListener;
    private ICarPlayAppManager mCarPlayAppManager;
    private boolean mDeviceServiceConnected;
    private IAndroidAutoAppManager mAndroidAutoAppManager = null;
    private IAndroidAutoDeviceListManager mAndroidAutoDeviceManager = null;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private boolean mBindCarPlayService = false;
    private boolean mBindAutoService = false;
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.card.utils.CarPlayAndAutoManager.1
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService service) {
            CarPlayAndAutoManager.this.mBindCarPlayService = true;
            if (CarPlayAndAutoManager.this.carPlayConnectStatusListener != null) {
                CarPlayAndAutoManager.this.carPlayConnectStatusListener.onBindCarPlayService(true);
            }
            if (CarPlayAndAutoManager.this.mCarPlayAppManager != null) {
                try {
                    CarPlayAndAutoManager.this.mCarPlayAppManager.registerCarPlayStateListener(CarPlayAndAutoManager.this.mCarPlayStateListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.w(CarPlayAndAutoManager.TAG, "registerCarPlayStateListener RemoteException = " + e.getMessage());
                }
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            CarPlayAndAutoManager.this.mBindCarPlayService = false;
            if (CarPlayAndAutoManager.this.carPlayConnectStatusListener != null) {
                CarPlayAndAutoManager.this.carPlayConnectStatusListener.onBindCarPlayService(false);
            }
        }
    };
    private ICarPlayStateListener mCarPlayStateListener = new ICarPlayStateListener() { // from class: com.byd.card.utils.CarPlayAndAutoManager.2
        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(int status) {
            if (CarPlayAndAutoManager.this.carPlayConnectStatusListener != null) {
                CarPlayAndAutoManager.this.carPlayConnectStatusListener.onCarPlayConnectStatusChanged(status);
            }
        }
    };
    private IServiceConnectionListener mAutoConnectionListener = new IServiceConnectionListener() { // from class: com.byd.card.utils.CarPlayAndAutoManager.3
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService service) {
            CarPlayAndAutoManager.this.mBindAutoService = true;
            if (CarPlayAndAutoManager.this.autoConnectStatusListener != null) {
                CarPlayAndAutoManager.this.autoConnectStatusListener.onBindAutoService(true);
            }
            try {
                CarPlayAndAutoManager.this.mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener(CarPlayAndAutoManager.this.mAndroidAutoSessionStatusListener);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.w(CarPlayAndAutoManager.TAG, "auto onServiceConnected RemoteException e = " + e.getMessage());
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService service) {
            CarPlayAndAutoManager.this.mBindAutoService = false;
            if (CarPlayAndAutoManager.this.autoConnectStatusListener != null) {
                CarPlayAndAutoManager.this.autoConnectStatusListener.onBindAutoService(false);
            }
        }
    };
    private IAndroidAutoSessionStatusListener mAndroidAutoSessionStatusListener = new IAndroidAutoSessionStatusListener() { // from class: com.byd.card.utils.CarPlayAndAutoManager.4
        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onSessionStatusChanged(int linkStatus, int errorCode, String message) {
            if (CarPlayAndAutoManager.this.autoConnectStatusListener != null) {
                CarPlayAndAutoManager.this.autoConnectStatusListener.onAutoConnectStatusChanged(linkStatus);
            }
        }
    };

    public interface OnAutoConnectStatusListener {
        void onAutoConnectStatusChanged(int status);

        void onBindAutoService(boolean bind);
    }

    public interface OnCarPlayConnectStatusListener {
        void onBindCarPlayService(boolean bind);

        void onCarPlayConnectStatusChanged(int status);
    }

    public void setOnCarPlayConnectStatusListener(OnCarPlayConnectStatusListener listener) {
        this.carPlayConnectStatusListener = listener;
    }

    public void setOnAutoConnectStatusListener(OnAutoConnectStatusListener listener) {
        this.autoConnectStatusListener = listener;
    }

    private CarPlayAndAutoManager() {
    }

    public static CarPlayAndAutoManager getInstance() {
        if (sInstance == null) {
            synchronized (CarPlayAndAutoManager.class) {
                if (sInstance == null) {
                    sInstance = new CarPlayAndAutoManager();
                }
            }
        }
        return sInstance;
    }

    public void initCarPlay(Context context, OnCarPlayConnectStatusListener listener) {
        this.carPlayConnectStatusListener = listener;
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, context);
        this.mCarPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        initCarPlayDeviceManager(context);
    }

    public void unregisterCarPlay() {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            try {
                iCarPlayAppManager.unregisterCarPlayStateListener(this.mCarPlayStateListener);
                this.mCarPlayAppManager.unregisterConnectionListener(this.mServiceConnectionListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterAuto() {
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                iAndroidAutoAppManager.unregisterConnectionListener(this.mAutoConnectionListener);
                this.mAndroidAutoAppManager.unregisterAndroidAutoSessionStatusListener(this.mAndroidAutoSessionStatusListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void initAuto(Context context, OnAutoConnectStatusListener listener) {
        this.autoConnectStatusListener = listener;
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, context);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mAutoConnectionListener);
        }
        if (this.mAndroidAutoDeviceManager == null) {
            this.mAndroidAutoDeviceManager = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, context);
        }
    }

    private void initCarPlayDeviceManager(Context context) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, context);
        this.mCarPlayDeviceManager = iCarPlayDeviceListManager;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.registerConnectionListener(this.mServiceConnectionListener);
        }
    }

    public void jumpApplication(Context context, AppInfo appInfo, Activity activity, ViewGroup viewGroup, View view, Bitmap bitmap, int width, int height, int x, int y, boolean card) {
        if (appInfo == null) {
            return;
        }
        if ("com.ts.carplay.app".equals(appInfo.packageName)) {
            Log.i(TAG, "bluetooth state = " + HfpManage.getInstance().getCallState());
            if (HfpManage.getInstance().getCallState() >= 1) {
                return;
            }
            Intent intent = new Intent();
            intent.setAction(OPEN_CARPLAY);
            intent.setPackage(UE_PACKAGE);
            intent.setFlags(16777248);
            context.sendBroadcast(intent);
            if (card) {
                return;
            }
            AppUtils.insertRecentApp(appInfo.packageName);
            return;
        }
        if ("com.ts.androidauto.app".equals(appInfo.packageName)) {
            Intent intent2 = new Intent();
            intent2.setAction(OPEN_AUTO);
            intent2.setPackage(UE_PACKAGE);
            intent2.setFlags(16777248);
            context.sendBroadcast(intent2);
            if (card) {
                return;
            }
            AppUtils.insertRecentApp(appInfo.packageName);
            return;
        }
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
        AppUtils.openApplication(context, appInfo, activity, viewGroup, view, bitmap, width, height, x, y, card);
    }

    public void cardJumpCPAA(Context context, AppInfo appInfo) {
        if (appInfo == null) {
            return;
        }
        if ("com.ts.carplay.app".equals(appInfo.packageName)) {
            Log.w(TAG, "bluetooth state = " + HfpManage.getInstance().getCallState());
            if (HfpManage.getInstance().getCallState() >= 1) {
                return;
            }
            Intent intent = new Intent();
            intent.setAction(OPEN_CARPLAY);
            intent.setPackage(UE_PACKAGE);
            intent.setFlags(16777248);
            context.sendBroadcast(intent);
            return;
        }
        if ("com.ts.androidauto.app".equals(appInfo.packageName)) {
            Intent intent2 = new Intent();
            intent2.setAction(OPEN_AUTO);
            intent2.setPackage(UE_PACKAGE);
            intent2.setFlags(16777248);
            context.sendBroadcast(intent2);
        }
    }
}
