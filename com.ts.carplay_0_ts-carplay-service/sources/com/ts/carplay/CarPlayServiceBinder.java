package com.ts.carplay;

import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.carplay.common.aidl.ICallStateCallback;
import com.ts.carplay.common.aidl.ICarPlayAppCallback;
import com.ts.carplay.common.aidl.ICarPlayDeviceListCallback;
import com.ts.carplay.common.aidl.ICarPlayService;
import com.ts.carplay.common.aidl.ICarPlayServiceCallback;
import com.ts.carplay.common.aidl.ICarPlayStateCallback;
import com.ts.carplay.common.aidl.IContactListCallback;
import com.ts.carplay.common.aidl.INowPlayingCallback;
import com.ts.carplay.common.aidl.IRouteGuidanceCallback;
import com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback;
import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.ConnectRequest;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.interfaces.ICarPlayManager;
import com.ts.carplay.linkadapter.device.DeviceManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class CarPlayServiceBinder extends ICarPlayService.Stub {
    private static final String TAG = CarPlayServiceBinder.class.getName();
    private CarPlayService carPlayService;
    private RemoteCallbackList<ICallStateCallback> mCallStateUpdateCallbackList;
    private RemoteCallbackList<ICarPlayAppCallback> mCarPlayAppCallbackList;
    private RemoteCallbackList<ICarPlayDeviceListCallback> mCarPlayDeviceListCallbackList;
    private ICarPlayManager mCarPlayManager;
    private RemoteCallbackList<ICarPlayServiceCallback> mCarPlayServiceCallbackList;
    private RemoteCallbackList<ICarPlayStateCallback> mCarPlayStateCallbackList;
    private RemoteCallbackList<IContactListCallback> mContactListUpdateCallbackList;
    private Handler mHandler;
    private RemoteCallbackList<INowPlayingCallback> mNowPlayingUpdateCallbackList;
    private RemoteCallbackList<IRouteGuidanceCallback> mRouteGuidanceUpdateCallbackList;
    private RemoteCallbackList<IWirelessCarPlayAvailabilityCallback> mWirelessCarPlayAvailabilityCallback;

    public CarPlayServiceBinder(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public void setCarPlayService(CarPlayService carPlayService) {
        this.carPlayService = carPlayService;
    }

    public void setCarPlayManager(ICarPlayManager carPlayManager) {
        this.mCarPlayManager = carPlayManager;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerCarPlayAppListener(final ICarPlayAppCallback callback) {
        LogUtil.debug(TAG, "registerCarPlayAppListener. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$DqXnx92RStwqEF_zM5bA_sG3rkU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerCarPlayAppListener$0$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerCarPlayAppListener$0$CarPlayServiceBinder(ICarPlayAppCallback callback) {
        this.mCarPlayAppCallbackList.register(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterCarPlayAppListener(final ICarPlayAppCallback callback) {
        LogUtil.debug(TAG, "removeCarPlayServiceCallback. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$re7-60yjLUtqC__t1JBOv0wKNRI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterCarPlayAppListener$1$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterCarPlayAppListener$1$CarPlayServiceBinder(ICarPlayAppCallback callback) {
        this.mCarPlayAppCallbackList.unregister(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerCarPlayStateListener(final ICarPlayStateCallback callback) {
        LogUtil.debug(TAG, "registerCarPlayStateListener. callback:" + callback);
        checkPermission(2);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$wWYZuUAyHfLXl3_MR0dMyxM89LQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerCarPlayStateListener$2$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerCarPlayStateListener$2$CarPlayServiceBinder(ICarPlayStateCallback callback) {
        this.mCarPlayStateCallbackList.register(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterCarPlayStateListener(final ICarPlayStateCallback callback) {
        LogUtil.debug(TAG, "removeCarPlayServiceCallback. callback:" + callback);
        checkPermission(2);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$uTEIBU-BVkbh6jC484pNVdim5cE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterCarPlayStateListener$3$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterCarPlayStateListener$3$CarPlayServiceBinder(ICarPlayStateCallback callback) {
        this.mCarPlayStateCallbackList.unregister(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerCarPlayServiceListener(final ICarPlayServiceCallback callback) {
        LogUtil.debug(TAG, "registerCarPlayServiceListener. callback:" + callback);
        checkPermission(1);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$fcPu_Kuw5NCsnpG6A8EimXxSLpw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerCarPlayServiceListener$4$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerCarPlayServiceListener$4$CarPlayServiceBinder(ICarPlayServiceCallback callback) {
        this.mCarPlayServiceCallbackList.register(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterCarPlayServiceListener(final ICarPlayServiceCallback callback) {
        LogUtil.debug(TAG, "removeCarPlayServiceCallback. callback:" + callback);
        checkPermission(1);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$O3bKnKJRfizC45naCygIc8MatcM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterCarPlayServiceListener$5$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterCarPlayServiceListener$5$CarPlayServiceBinder(ICarPlayServiceCallback callback) {
        this.mCarPlayServiceCallbackList.unregister(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerCarPlayDeviceListListener(final ICarPlayDeviceListCallback callback) {
        LogUtil.debug(TAG, "registerCarPlayDeviceListListener. callback:" + callback);
        checkPermission(3);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$x8OMNOFWbLdjH8LkJo4ri3DoVWc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerCarPlayDeviceListListener$6$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerCarPlayDeviceListListener$6$CarPlayServiceBinder(ICarPlayDeviceListCallback callback) {
        this.mCarPlayDeviceListCallbackList.register(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterCarPlayDeviceListListener(final ICarPlayDeviceListCallback callback) {
        LogUtil.debug(TAG, "removeCarPlayDeviceListCallback. callback:" + callback);
        checkPermission(3);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$vWCf8h2Vf_0bIEnqhurLcEq0L7E
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterCarPlayDeviceListListener$7$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterCarPlayDeviceListListener$7$CarPlayServiceBinder(ICarPlayDeviceListCallback callback) {
        this.mCarPlayDeviceListCallbackList.unregister(callback);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerCallStateListener(final ICallStateCallback callback) {
        LogUtil.debug(TAG, "startCallStateUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$MPQMR7-aEqvmNB0NjckfEUtCDIs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerCallStateListener$8$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerCallStateListener$8$CarPlayServiceBinder(ICallStateCallback callback) {
        int previousSize = this.mCallStateUpdateCallbackList.getRegisteredCallbackCount();
        this.mCallStateUpdateCallbackList.register(callback);
        int currentSize = this.mCallStateUpdateCallbackList.getRegisteredCallbackCount();
        if (currentSize == previousSize + 1) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(0, true);
            } else {
                LogUtil.warning(TAG, "startCallStateUpdates mCarPlayManager null");
            }
            notifyCallStateChanged(callback);
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterCallStateListener(final ICallStateCallback callback) {
        LogUtil.debug(TAG, "stopCallStateUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$9EjGRkhbSa8Kn5Gee_LCzk2R0n8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterCallStateListener$9$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterCallStateListener$9$CarPlayServiceBinder(ICallStateCallback callback) {
        this.mCallStateUpdateCallbackList.unregister(callback);
        int size = this.mCallStateUpdateCallbackList.beginBroadcast();
        LogUtil.debug(TAG, "stopCallStateUpdates. size:" + size);
        if (size == 0) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(0, false);
            } else {
                LogUtil.warning(TAG, "stopCallStateUpdates mCarPlayManager null");
            }
        }
        this.mCallStateUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerContactListListener(final IContactListCallback callback) {
        LogUtil.debug(TAG, "startContactListUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$T3JFZxP7OOmYMoCox4iUd1ZjdGE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerContactListListener$10$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerContactListListener$10$CarPlayServiceBinder(IContactListCallback callback) {
        int previousSize = this.mContactListUpdateCallbackList.getRegisteredCallbackCount();
        this.mContactListUpdateCallbackList.register(callback);
        int currentSize = this.mContactListUpdateCallbackList.getRegisteredCallbackCount();
        if (currentSize == previousSize + 1) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(3, true);
            } else {
                LogUtil.warning(TAG, "startContactListUpdates mCarPlayManager null");
            }
            notifyContactListChanged(callback);
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterContactListListener(final IContactListCallback callback) {
        LogUtil.debug(TAG, "stopContactListUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$_K9Gyw_P_zdf8wz5oaw01gv0qns
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterContactListListener$11$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterContactListListener$11$CarPlayServiceBinder(IContactListCallback callback) {
        this.mContactListUpdateCallbackList.unregister(callback);
        int size = this.mContactListUpdateCallbackList.beginBroadcast();
        LogUtil.debug(TAG, "stopContactListUpdates. size:" + size);
        if (size == 0) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(3, false);
            } else {
                LogUtil.warning(TAG, "stopContactListUpdates mCarPlayManager null");
            }
        }
        this.mContactListUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerRouteGuidanceListener(final IRouteGuidanceCallback callback) {
        LogUtil.debug(TAG, "startRouteGuidanceUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$duXC1NkycVj7xlQcc5BpGQnGsbE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerRouteGuidanceListener$12$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerRouteGuidanceListener$12$CarPlayServiceBinder(IRouteGuidanceCallback callback) {
        int previousSize = this.mRouteGuidanceUpdateCallbackList.getRegisteredCallbackCount();
        this.mRouteGuidanceUpdateCallbackList.register(callback);
        int currentSize = this.mRouteGuidanceUpdateCallbackList.getRegisteredCallbackCount();
        if (currentSize == previousSize + 1) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(1, true);
            } else {
                LogUtil.warning(TAG, "startRouteGuidanceUpdates mCarPlayManager null");
            }
            notifyRouteGuidanceChanged(callback);
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterRouteGuidanceListener(final IRouteGuidanceCallback callback) {
        LogUtil.debug(TAG, "stopRouteGuidanceUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$4IBaW54Y31qsF2ELetWOWCz_5-g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterRouteGuidanceListener$13$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterRouteGuidanceListener$13$CarPlayServiceBinder(IRouteGuidanceCallback callback) {
        this.mRouteGuidanceUpdateCallbackList.unregister(callback);
        int size = this.mRouteGuidanceUpdateCallbackList.beginBroadcast();
        LogUtil.debug(TAG, "stopRouteGuidanceUpdates. size:" + size);
        if (size == 0) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(1, false);
            } else {
                LogUtil.warning(TAG, "stopRouteGuidanceUpdates mCarPlayManager null");
            }
        }
        this.mRouteGuidanceUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void registerNowPlayingListener(final INowPlayingCallback callback) {
        LogUtil.debug(TAG, "startNowPlayingUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$jdM9EP1JJqKaQB5aiDaILqPb5b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerNowPlayingListener$14$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$registerNowPlayingListener$14$CarPlayServiceBinder(INowPlayingCallback callback) {
        int previousSize = this.mNowPlayingUpdateCallbackList.getRegisteredCallbackCount();
        this.mNowPlayingUpdateCallbackList.register(callback);
        int currentSize = this.mNowPlayingUpdateCallbackList.getRegisteredCallbackCount();
        if (currentSize == previousSize + 1) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(2, true);
            } else {
                LogUtil.warning(TAG, "startNowPlayingUpdates mCarPlayManager null");
            }
            notifyNowPlayingChanged(callback);
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void unregisterNowPlayingListener(final INowPlayingCallback callback) {
        LogUtil.debug(TAG, "stopNowPlayingUpdates. callback:" + callback);
        checkPermission(0);
        if (callback != null) {
            this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayServiceBinder$075-O8jCo49cWaR04RAJdNbJ-Fc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterNowPlayingListener$15$CarPlayServiceBinder(callback);
                }
            });
        }
    }

    public /* synthetic */ void lambda$unregisterNowPlayingListener$15$CarPlayServiceBinder(INowPlayingCallback callback) {
        this.mNowPlayingUpdateCallbackList.unregister(callback);
        int size = this.mNowPlayingUpdateCallbackList.beginBroadcast();
        LogUtil.debug(TAG, "stopNowPlayingUpdates. size:" + size);
        if (size == 0) {
            ICarPlayManager iCarPlayManager = this.mCarPlayManager;
            if (iCarPlayManager != null) {
                iCarPlayManager.setCallbackUpdate(2, false);
            } else {
                LogUtil.warning(TAG, "stopNowPlayingUpdates mCarPlayManager null");
            }
        }
        this.mNowPlayingUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void checkWirelessCarPlayAvailability(String btAddr, IWirelessCarPlayAvailabilityCallback callback) {
        LogUtil.debug(TAG, "checkWirelessCarPlayAvailability btAddr:" + btAddr);
        checkPermission(4);
        if (callback == null) {
            LogUtil.warning(TAG, "checkWirelessCarPlayAvailability callback is null.");
            return;
        }
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.checkWirelessCarPlayAvailability(btAddr, new CarPlayCapabilityListenerImpl(callback));
        } else {
            LogUtil.warning(TAG, "checkWirelessCarPlayAvailability mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void connectCarPlay(ConnectRequest request) {
        LogUtil.debug(TAG, "connectCarPlay " + request);
        checkPermission(4);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.connectCarPlay(request);
        } else {
            LogUtil.warning(TAG, "connectCarPlay mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void disconnectCarPlay(String devId) {
        LogUtil.debug(TAG, "disconnectCarPlay devId:" + devId);
        checkPermission(4);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.disconnectCarPlay(devId);
        } else {
            LogUtil.warning(TAG, "disconnectCarPlay mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void deleteCarPlayDevice(String devId) {
        LogUtil.debug(TAG, "deleteCarPlayDevice devId:" + devId);
        checkPermission(4);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.deleteCarPlayDevice(devId);
        } else {
            LogUtil.warning(TAG, "deleteCarPlayDevice mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setSongElapsedTime(long elapsedTime) {
        LogUtil.debug(TAG, "setSongElapsedTime elapsedTime:" + elapsedTime);
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setSongElapsedTime(elapsedTime);
        } else {
            LogUtil.warning(TAG, "setSongElapsedTime mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void playSong(long pbIndex) {
        LogUtil.debug(TAG, "playsong pbIndex:" + pbIndex);
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.playSong(pbIndex);
        } else {
            LogUtil.warning(TAG, "playsong mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setLastUserMode(int resource, boolean mode) {
        LogUtil.debug(TAG, "setLastUserMode resource: " + resource + " mode: " + mode);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setLastUserMode(resource, mode);
        } else {
            LogUtil.warning(TAG, "setLastUserMode mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public CarPlayDevice getActivedWirelessDevice() {
        checkPermission(3);
        CarPlayDevice device = null;
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            device = iCarPlayManager.getActivedWirelessDevice();
        } else {
            LogUtil.warning(TAG, "getActivedWirelessDevice mCarPlayManager is null.");
        }
        LogUtil.debug(TAG, "getActivedWirelessDevice device: " + device);
        return device;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public boolean getLastUserMode(int resource) {
        checkPermission(1);
        boolean ret = false;
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            ret = iCarPlayManager.getLastUserMode(resource);
        } else {
            LogUtil.warning(TAG, "getLastUserMode mCarPlayManager is null.");
        }
        LogUtil.debug(TAG, "getLastUserMode ret: " + ret);
        return ret;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void changeVideoFocus(int priority, boolean backgroundSupport, String borrowId, int action) {
        LogUtil.debug(TAG, "changeVideoFocus priority: " + priority + ",backgroundSupport： " + backgroundSupport + ",borrowId: " + borrowId + ",action: " + action);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.changeVideoFocus(priority, backgroundSupport, borrowId, action);
        } else {
            LogUtil.warning(TAG, "changeVideoFocus mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public boolean getCarPlayDisPlayStatus() {
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.getCarPlayDisPlayStatus();
        }
        LogUtil.warning(TAG, "getCarPlayDisPlayStatus mCarPlayManager is null.");
        return false;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setNightMode(boolean mode) {
        LogUtil.debug(TAG, "setNightMode mode:" + mode);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setNightMode(mode);
        } else {
            LogUtil.warning(TAG, "setNightMode mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setSurface(Surface surface) {
        LogUtil.debug(TAG, "setSurface surface:" + surface);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setSurface(surface);
        } else {
            LogUtil.warning(TAG, "setSurface mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setSurfaceWithType(Surface surface, int screenType) {
        LogUtil.debug(TAG, "setSurface surface:" + surface + ",screenType:" + screenType);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setSurface(surface, screenType);
        } else {
            LogUtil.warning(TAG, "setSurface mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setLimitedUi(boolean mode) {
        LogUtil.debug(TAG, "setLimitedUi mode:" + mode);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setLimitedUi(mode);
        } else {
            LogUtil.warning(TAG, "setLimitedUi mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public String getBluetoothMacAddress() {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.getBluetoothMacAddress();
        }
        return null;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public int getSupportFeatures() {
        checkPermission(1);
        int supportFeatures = 0;
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            supportFeatures = iCarPlayManager.getSupportFeatures();
        } else {
            LogUtil.warning(TAG, "getSupportFeatures mCarPlayManager is null.");
        }
        LogUtil.debug(TAG, "getSupportFeatures supportFeatures:" + supportFeatures);
        return supportFeatures;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void screenTouchEvent(MotionEvent event) {
        LogUtil.verbose(TAG, "screenTouchEvent event:" + event);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.screenTouchEvent(event);
        } else {
            LogUtil.warning(TAG, "screenTouchEvent mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void requestUi(int launchMode) {
        LogUtil.debug(TAG, "requestUi launchMode:" + launchMode);
        checkPermission(2);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.requestUi(launchMode);
        } else {
            LogUtil.warning(TAG, "requestUi mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void showUi(int screenType, int url) {
        LogUtil.debug(TAG, "showUi screenType:" + screenType + " url:" + url);
        checkPermission(2);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.showUi(screenType, url);
        } else {
            LogUtil.warning(TAG, "showUi mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void stopUi(int screenType) {
        LogUtil.debug(TAG, "stopUi screenType:" + screenType);
        checkPermission(2);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.stopUi(screenType);
        } else {
            LogUtil.warning(TAG, "stopUi mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void requestSiri(int siriAction) {
        LogUtil.debug(TAG, "requestSiri siriAction:" + siriAction);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.requestSiri(siriAction);
        } else {
            LogUtil.warning(TAG, "requestSiri mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void launchSiri(int siriAction, long siriTriggerTimestamp) {
        LogUtil.debug(TAG, "launchSiri siriAction:" + siriAction + ", siriTriggerTimestamp= " + siriTriggerTimestamp);
        checkPermission(2);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.launchSiri(siriAction, siriTriggerTimestamp);
        } else {
            LogUtil.warning(TAG, "launchSiri mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setEnhancedSiriInfo(List<String> voiceModelSupportedLanguages, String voiceModelCurrentLanguage, int supportedSiriTriggerZones) {
        LogUtil.debug(TAG, "setEnhancedSiriInfo voiceModelSupportedLanguages:" + voiceModelSupportedLanguages + ", voiceModelCurrentLanguage= " + voiceModelCurrentLanguage + ", supportedSiriTriggerZones= " + supportedSiriTriggerZones);
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setEnhancedSiriInfo(voiceModelSupportedLanguages, voiceModelCurrentLanguage, supportedSiriTriggerZones);
        } else {
            LogUtil.warning(TAG, "setEnhancedSiriInfo mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void initiateCall(int callType, String destId, int callService) {
        LogUtil.debug(TAG, "initiateCall callType:" + callType + ",destId:" + destId + ",callService" + callService);
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.initiateCall(callType, destId, callService);
        } else {
            LogUtil.warning(TAG, "initiateCall mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void acceptCall(int action, String callUuid) {
        LogUtil.debug(TAG, "acceptCall action:" + action + ",callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.acceptCall(action, callUuid);
        } else {
            LogUtil.warning(TAG, "acceptCall mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void endCall(int action, String callUuid) {
        LogUtil.debug(TAG, "endCall action:" + action + ",callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.endCall(action, callUuid);
        } else {
            LogUtil.warning(TAG, "endCall mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void swapCalls() {
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.swapCalls();
        } else {
            LogUtil.warning(TAG, "swapCalls mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void sendDtmf(int toneType, String callUuid) {
        LogUtil.debug(TAG, "sendDtmf toneType:" + toneType + ",callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.sendDtmf(toneType, callUuid);
        } else {
            LogUtil.warning(TAG, "sendDtmf mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void sendHidEventOverIap(int keyCode, int keyAction) {
        LogUtil.debug(TAG, "sendHidEventOverIap keyCode:" + keyCode + " keyAction:" + keyAction);
        checkPermission(0);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.sendHidEventOverIap(keyCode, keyAction);
        } else {
            LogUtil.warning(TAG, "sendHidEventOverIap mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void sendHidEventOverCarPlay(int keyCode, int keyAction) {
        LogUtil.debug(TAG, "sendHidEventOverCarPlay keyCode:" + keyCode + " keyAction:" + keyAction);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.sendHidEventOverCarPlay(keyCode, keyAction);
        } else {
            LogUtil.warning(TAG, "sendHidEventOverCarPlay mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public int getCarPlaySessionStatus() {
        checkPermission(2);
        int linkStatus = 0;
        DeviceManager manager = DeviceManager.getInstance();
        if (manager != null) {
            linkStatus = manager.getConnectStatus(manager.getDevice());
        } else {
            LogUtil.warning(TAG, "getLinkStatus deviceManager is null.");
        }
        LogUtil.debug(TAG, "getLinkStatus linkStatus:" + linkStatus);
        return linkStatus;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setNativeAppState(int appState, boolean enabled, int speechMode) {
        LogUtil.info(TAG, "setNativeAppState appState:" + appState + " enabled:" + enabled + " speechMode:" + speechMode);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setNativeAppState(appState, enabled, speechMode);
        } else {
            LogUtil.warning(TAG, "setNativeAppState mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void setCarPlayConfig(CarPlayConfig carPlayConfig) {
        checkPermission(1);
        LogUtil.info(TAG, "setCarPlayConfig carPlayConfig:" + carPlayConfig);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.setCarPlayConfig(carPlayConfig);
        } else {
            LogUtil.warning(TAG, "setCarPlayConfig mCarPlayManager is null.");
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void onDeviceMerged(CarPlayDevice device) {
        LogUtil.info(TAG, "onDeviceMerged " + device);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public CarPlayDevice getCarPlayDevice(String devId) {
        checkPermission(3);
        return this.mCarPlayManager.getCarPlayAidlDevice(devId);
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public int setUiAppearanceMode(String uuid, int uiMode, int uiSetting) {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.setUiAppearanceMode(uuid, uiMode, uiSetting);
        }
        return -1;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public int setMapAppearanceMode(String uuid, int mapMode, int mapSetting) {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.setMapAppearanceMode(uuid, mapMode, mapSetting);
        }
        return -1;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public List<CarPlayDevice> getUsbCarPlayDeviceList() {
        checkPermission(3);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.getUsbCarPlayDeviceList();
        }
        return null;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public List<CarPlayDevice> getCarPlayDeviceList() {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.getCarPlayDeviceList();
        }
        return null;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public boolean cancelWirelessCarPlay(String mac) {
        checkPermission(3);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            return iCarPlayManager.cancelWirelessCarPlay(mac);
        }
        return false;
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService
    public void updateViewArea(String uuid, int index, int[] adjacentViewAreas, int animationDurationMillis) {
        LogUtil.error(TAG, "updateViewArea index：" + index + ",uuid" + LogEncrypt.MacAddressEncrypt(uuid) + ",array:" + adjacentViewAreas.toString() + ",animationDurationMillis:" + animationDurationMillis);
        checkPermission(1);
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            iCarPlayManager.updateViewArea(uuid, index, adjacentViewAreas, animationDurationMillis);
        }
    }

    @Override // com.ts.carplay.common.aidl.ICarPlayService.Stub, android.os.Binder
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        try {
            super.onTransact(code, data, reply, flags);
            return true;
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    private void notifyCallStateChanged(ICallStateCallback callback) {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            try {
                Object obj = iCarPlayManager.getCurrentInfo(0);
                if (obj instanceof CallStateInfo) {
                    CallStateInfo info = (CallStateInfo) obj;
                    callback.onCallStateInfoChanged(info);
                }
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCallStateUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
    }

    private void notifyContactListChanged(IContactListCallback callback) {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            try {
                int recentListCount = iCarPlayManager.getContactListCount(0);
                List<RecentContactListInfo> recentContactListInfo = this.mCarPlayManager.getRecentListInfo();
                if (recentListCount != -1) {
                    callback.onRecentListCountChanged(recentListCount);
                    callback.onRecentListChanged(recentContactListInfo);
                }
                int favoriteListCount = this.mCarPlayManager.getContactListCount(1);
                List<FavoriteContactListInfo> favoriteContactListInfo = this.mCarPlayManager.getFavoriteListInfo();
                if (favoriteListCount != -1) {
                    callback.onFavoriteListCountChanged(favoriteListCount);
                    callback.onFavoriteListChanged(favoriteContactListInfo);
                }
                LogUtil.debug(TAG, "notifyContactListChanged recentListCount: " + recentListCount + " favoriteListCount: " + favoriteListCount);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCallStateUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
    }

    private void notifyRouteGuidanceChanged(IRouteGuidanceCallback callback) {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            try {
                Object obj = iCarPlayManager.getCurrentInfo(1);
                if (obj instanceof RouteGuidanceInfo) {
                    RouteGuidanceInfo info = (RouteGuidanceInfo) obj;
                    callback.onRouteGuidanceInfoChanged(info);
                }
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRouteGuidanceUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
    }

    private void notifyNowPlayingChanged(INowPlayingCallback callback) {
        ICarPlayManager iCarPlayManager = this.mCarPlayManager;
        if (iCarPlayManager != null) {
            try {
                Object obj = iCarPlayManager.getCurrentInfo(2);
                if (obj instanceof NowPlayingInfo) {
                    NowPlayingInfo info = (NowPlayingInfo) obj;
                    callback.onNowPlayingInfoChanged(info);
                }
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onNowPlayingUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
    }

    public void setCarPlayAppCallbackList(RemoteCallbackList<ICarPlayAppCallback> mCarPlayAppCallbackList) {
        this.mCarPlayAppCallbackList = mCarPlayAppCallbackList;
    }

    public void setCarPlayStateCallbackList(RemoteCallbackList<ICarPlayStateCallback> mCarPlayStateCallbackList) {
        this.mCarPlayStateCallbackList = mCarPlayStateCallbackList;
    }

    public void setCarPlayServiceCallbackList(RemoteCallbackList<ICarPlayServiceCallback> mCarPlayServiceCallbackList) {
        this.mCarPlayServiceCallbackList = mCarPlayServiceCallbackList;
    }

    public void setCarPlayDeviceListCallbackList(RemoteCallbackList<ICarPlayDeviceListCallback> mCarPlayDeviceListCallbackList) {
        this.mCarPlayDeviceListCallbackList = mCarPlayDeviceListCallbackList;
    }

    public void setCallStateUpdateCallbackList(RemoteCallbackList<ICallStateCallback> mCallStateUpdateCallbackList) {
        this.mCallStateUpdateCallbackList = mCallStateUpdateCallbackList;
    }

    public void setContactListUpdateCallbackList(RemoteCallbackList<IContactListCallback> mContactListUpdateCallbackList) {
        this.mContactListUpdateCallbackList = mContactListUpdateCallbackList;
    }

    public void setRouteGuidanceUpdateCallbackList(RemoteCallbackList<IRouteGuidanceCallback> mRouteGuidanceUpdateCallbackList) {
        this.mRouteGuidanceUpdateCallbackList = mRouteGuidanceUpdateCallbackList;
    }

    public void setNowPlayingUpdateCallbackList(RemoteCallbackList<INowPlayingCallback> mNowPlayingUpdateCallbackList) {
        this.mNowPlayingUpdateCallbackList = mNowPlayingUpdateCallbackList;
    }

    public void setWirelessCarPlayAvailabilityCallback(RemoteCallbackList<IWirelessCarPlayAvailabilityCallback> mWirelessCarPlayAvailabilityCallback) {
        this.mWirelessCarPlayAvailabilityCallback = mWirelessCarPlayAvailabilityCallback;
    }

    void checkPermission(int permissionType) {
        this.carPlayService.checkPermission(permissionType);
    }
}
