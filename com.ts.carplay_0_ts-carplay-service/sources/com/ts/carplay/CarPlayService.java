package com.ts.carplay;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import com.ts.carplay.common.aidl.ICallStateCallback;
import com.ts.carplay.common.aidl.ICarPlayAppCallback;
import com.ts.carplay.common.aidl.ICarPlayDeviceListCallback;
import com.ts.carplay.common.aidl.ICarPlayServiceCallback;
import com.ts.carplay.common.aidl.ICarPlayStateCallback;
import com.ts.carplay.common.aidl.IContactListCallback;
import com.ts.carplay.common.aidl.INowPlayingCallback;
import com.ts.carplay.common.aidl.IRouteGuidanceCallback;
import com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.interfaces.ICarPlayManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayService extends Service {
    private static final String ACTION_CARPLAY_START_FINISH = "com.ts.carplay.start.finish";
    private static final int AUDIOMANAGER_REGISTER_SLEEP = 10000;
    public static final int CARPLAY_APP_OR_SERVICE_PERMISSION = 2;
    public static final int CARPLAY_APP_PERMISSION = 0;
    public static final int CARPLAY_DEVICE_LIST_PERMISSION = 3;
    public static final int CARPLAY_SERVICE_PERMISSION = 1;
    private static final int INIT_ADAPTER_NUM = 8;
    private static final int INIT_ADAPTER_TIMEOUT = 10;
    public static final int PLATFORM_DEVICE_LIST_PERMISSION = 4;
    private static final String TAG = "CarPlayService";
    private ICarPlayManager mCarPlayManager;
    private CarPlayManagerListenerImpl mCarPlayManagerListener;
    private CarPlayServiceBinder mCarPlayServiceBinder;
    private CarPlayServiceHandler mHandler;
    private HandlerThread mHandlerThread;
    private Context mContext = null;
    private RemoteCallbackList<ICarPlayAppCallback> mCarPlayAppCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<ICarPlayStateCallback> mCarPlayStateCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<ICarPlayServiceCallback> mCarPlayServiceCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<ICarPlayDeviceListCallback> mCarPlayDeviceListCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<ICallStateCallback> mCallStateUpdateCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IContactListCallback> mContactListUpdateCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IRouteGuidanceCallback> mRouteGuidanceUpdateCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<INowPlayingCallback> mNowPlayingUpdateCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IWirelessCarPlayAvailabilityCallback> mWirelessCarPlayAvailabilityCallback = new RemoteCallbackList<>();

    public CarPlayService() {
        this.mCarPlayServiceBinder = null;
        HandlerThread handlerThread = new HandlerThread("CarPlayServiceHandlerThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new CarPlayServiceHandler(this.mHandlerThread.getLooper());
        CarPlayServiceBinder carPlayServiceBinder = new CarPlayServiceBinder(this.mHandler);
        this.mCarPlayServiceBinder = carPlayServiceBinder;
        carPlayServiceBinder.setCarPlayService(this);
        this.mCarPlayServiceBinder.setCarPlayAppCallbackList(this.mCarPlayAppCallbackList);
        this.mCarPlayServiceBinder.setCarPlayStateCallbackList(this.mCarPlayStateCallbackList);
        this.mCarPlayServiceBinder.setCarPlayServiceCallbackList(this.mCarPlayServiceCallbackList);
        this.mCarPlayServiceBinder.setCarPlayDeviceListCallbackList(this.mCarPlayDeviceListCallbackList);
        this.mCarPlayServiceBinder.setCallStateUpdateCallbackList(this.mCallStateUpdateCallbackList);
        this.mCarPlayServiceBinder.setContactListUpdateCallbackList(this.mContactListUpdateCallbackList);
        this.mCarPlayServiceBinder.setRouteGuidanceUpdateCallbackList(this.mRouteGuidanceUpdateCallbackList);
        this.mCarPlayServiceBinder.setNowPlayingUpdateCallbackList(this.mNowPlayingUpdateCallbackList);
        this.mCarPlayServiceBinder.setWirelessCarPlayAvailabilityCallback(this.mWirelessCarPlayAvailabilityCallback);
        CarPlayManagerListenerImpl carPlayManagerListenerImpl = new CarPlayManagerListenerImpl(this.mHandler);
        this.mCarPlayManagerListener = carPlayManagerListenerImpl;
        carPlayManagerListenerImpl.setCarPlayAppCallbackList(this.mCarPlayAppCallbackList);
        this.mCarPlayManagerListener.setCarPlayStateCallbackList(this.mCarPlayStateCallbackList);
        this.mCarPlayManagerListener.setCarPlayServiceCallbackList(this.mCarPlayServiceCallbackList);
        this.mCarPlayManagerListener.setCarPlayDeviceListCallbackList(this.mCarPlayDeviceListCallbackList);
        this.mCarPlayManagerListener.setCallStateUpdateCallbackList(this.mCallStateUpdateCallbackList);
        this.mCarPlayManagerListener.setContactListUpdateCallbackList(this.mContactListUpdateCallbackList);
        this.mCarPlayManagerListener.setRouteGuidanceUpdateCallbackList(this.mRouteGuidanceUpdateCallbackList);
        this.mCarPlayManagerListener.setNowPlayingUpdateCallbackList(this.mNowPlayingUpdateCallbackList);
        this.mCarPlayManagerListener.setWirelessCarPlayAvailabilityCallback(this.mWirelessCarPlayAvailabilityCallback);
    }

    private class CarPlayServiceHandler extends Handler {
        public CarPlayServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
        }
    }

    void checkPermission(int permissionType) {
        if (Binder.getCallingUid() == 1000) {
            return;
        }
        if (permissionType == 0) {
            if (getBaseContext().checkCallingOrSelfPermission("car.permission.CARPLAY_APP") != 0) {
                LogUtil.warning(TAG, "Caller needs permission 'car.permission.CARPLAY_APP' to call ");
                throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission car.permission.CARPLAY_APP");
            }
            return;
        }
        if (permissionType == 1) {
            if (getBaseContext().checkCallingOrSelfPermission("car.permission.CARPLAY") != 0) {
                LogUtil.warning(TAG, "Caller needs permission 'car.permission.CARPLAY' to call ");
                throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission car.permission.CARPLAY");
            }
            return;
        }
        if (permissionType == 2) {
            if (getBaseContext().checkCallingOrSelfPermission("car.permission.CARPLAY") != 0 && getBaseContext().checkCallingOrSelfPermission("car.permission.CARPLAY_APP") != 0) {
                LogUtil.warning(TAG, "Caller needs permission 'car.permission.CARPLAY' to call ");
                throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission car.permission.CARPLAY_APP or car.permission.CARPLAY");
            }
            return;
        }
        if (permissionType == 3) {
            if (getBaseContext().checkCallingOrSelfPermission("car.permission.CARPLAY_DEVICE_LIST") != 0) {
                LogUtil.warning(TAG, "Caller needs permission 'car.permission.CARPLAY_DEVICE_LIST' to call ");
                throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission car.permission.CARPLAY_DEVICE_LIST");
            }
            return;
        }
        if (permissionType == 4 && getBaseContext().checkCallingOrSelfPermission("car.permission.CARPLAY_DEVICE_LIST") != 0) {
            LogUtil.warning(TAG, "Caller needs permission 'car.permission.CARPLAY_DEVICE_LIST' to call ");
            throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission car.permission.CARPLAY_DEVICE_LIST");
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        init(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.info(TAG, "onStartCommand start" + intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.info(TAG, "onBind start.");
        return this.mCarPlayServiceBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        uninit();
        super.onDestroy();
    }

    private void init(Context context) {
        LogUtil.info(TAG, "init start context:" + context);
        ICarPlayManager carPlayManager = CarPlayManagerFactory.getCarPlayManager();
        this.mCarPlayManager = carPlayManager;
        this.mCarPlayServiceBinder.setCarPlayManager(carPlayManager);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.init(context);
            this.mCarPlayManager.registerListener(this.mCarPlayManagerListener);
        }
        sendBroadcast(new Intent(ACTION_CARPLAY_START_FINISH));
        LogUtil.info(TAG, "init end.");
    }

    private void uninit() {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.unregisterListener(this.mCarPlayManagerListener);
            this.mCarPlayManager.uninit();
        }
        CarPlayServiceHandler carPlayServiceHandler = this.mHandler;
        if (carPlayServiceHandler != null) {
            carPlayServiceHandler.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }
}
