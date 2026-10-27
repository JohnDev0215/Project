package com.ts.carplay.manager;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import com.ts.carplay.common.CarPlayConstants;
import com.ts.carplay.common.Converter;
import com.ts.carplay.common.aidl.ICarPlayService;
import com.ts.carplay.common.aidl.ICarPlayServiceCallback;
import com.ts.carplay.common.aidl.ICarPlayStateCallback;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.common.util.ThrowingConsumer;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.carplay.ICarPlayServiceListener;
import ts.car.service.carplay.ICarPlayServiceManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformCarPlayServiceManager extends SimpleServiceBase implements ICarPlayServiceManager {
    private static final String TAG = PlatformCarPlayServiceManager.class.getSimpleName();
    private ICarPlayService mCarPlayService;
    private List<ICarPlayServiceCallbackImpl> mCarPlayServiceListenerList;
    private List<ICarPlayStateCallbackImpl> mCarPlayStateListenerList;

    @PlatformConstruction
    public PlatformCarPlayServiceManager(Context context, IBinder binder) {
        super(context, CarPlayConstants.SERVICE_COMPONENT, CarPlayConstants.REMOTE_SERVICE_NAME, binder);
        this.mCarPlayServiceListenerList = new ArrayList();
        this.mCarPlayStateListenerList = new ArrayList();
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-CarPlayService_4.5.2.2";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        LogUtil.debug(TAG, "onServiceConnected.");
        this.mCarPlayService = ICarPlayService.Stub.asInterface(iBinder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        LogUtil.debug(TAG, "onServiceDied.");
        this.mCarPlayService = null;
    }

    private class ICarPlayServiceCallbackImpl extends ICarPlayServiceCallback.Stub {
        ICarPlayServiceListener mListener;

        public ICarPlayServiceCallbackImpl(ICarPlayServiceListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
        public void onRequestVideoFocusChanged(int requestType) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayServiceCallback --- onRequestVideoFocusChanged requestType:" + requestType);
            this.mListener.onRequestVideoFocusChanged(requestType);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
        public void onSupportFeaturesChanged(int supportsFeatures) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayServiceCallback --- onSupportFeaturesChanged supportsFeatures:" + supportsFeatures);
            this.mListener.onSupportFeaturesChanged(supportsFeatures);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayServiceCallback
        public void onAppStateChanged(int appState, int entity, int speechMode) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayStateCallback --- onAppStateChanged appState:" + appState + " entity:" + entity + " speechMode:" + speechMode);
            this.mListener.onAppStateChanged(appState, entity, speechMode);
        }
    }

    private class ICarPlayStateCallbackImpl extends ICarPlayStateCallback.Stub {
        ICarPlayStateListener mListener;

        public ICarPlayStateCallbackImpl(ICarPlayStateListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onSessionStateChanged(int status) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayStateCallback --- onSessionStateChanged status:" + status);
            this.mListener.onSessionStateChanged(status);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayStateCallback --- onSessionConnectStateChanged status:" + status + " isOldConnection:" + isOldConnection + " clientAddr:" + LogEncrypt.MacAddressEncrypt(clientAddr));
            this.mListener.onSessionConnectStateChanged(status, isOldConnection, clientAddr);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayStateCallback --- onCarPlaySessionConnectStateChanged status:" + status + " isOldConnection:" + isOldConnection + " clientAddr:" + LogEncrypt.MacAddressEncrypt(clientAddr) + " isUserConnect:" + isUserConnect);
            this.mListener.onCarPlaySessionConnectStateChanged(status, isOldConnection, clientAddr, isUserConnect);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onAppStateChanged(int appState, boolean enable, int speechMode) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayStateCallback --- onAppStateChanged appState:" + appState + " enable:" + enable + " speechMode:" + speechMode);
            this.mListener.onAppStateChanged(appState, enable, speechMode);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) throws RemoteException {
            LogUtil.debug(PlatformCarPlayServiceManager.TAG, "ICarPlayServiceCallback --- onCarPlayMediaFocusStateChanged isCarPlayMedia:" + isCarPlayMedia);
            this.mListener.onCarPlayMediaFocusStateChanged(isCarPlayMedia);
        }
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public boolean registerCarPlayStateListener(ICarPlayStateListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "registerCarPlayStateListener ICarPlayService is not Ready!.");
            return false;
        }
        if (listener != null) {
            ICarPlayStateCallbackImpl carPlayStateCallback = new ICarPlayStateCallbackImpl(listener);
            synchronized (this) {
                this.mCarPlayStateListenerList.add(carPlayStateCallback);
            }
            this.mCarPlayService.registerCarPlayStateListener(carPlayStateCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerCarPlayStateListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public boolean unregisterCarPlayStateListener(ICarPlayStateListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterCarPlayStateListener ICarPlayService is not Ready!.");
            return false;
        }
        if (listener != null) {
            List<ICarPlayStateCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mCarPlayStateListenerList.size()) {
                    ICarPlayStateCallbackImpl lstn = this.mCarPlayStateListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mCarPlayStateListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (ICarPlayStateCallbackImpl carPlayStateCallback : list) {
                this.mCarPlayService.unregisterCarPlayStateListener(carPlayStateCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterCarPlayStateListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public boolean registerCarPlayServiceListener(ICarPlayServiceListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "registerCarPlayServiceListener ICarPlayService is not Ready!.");
            return false;
        }
        if (listener != null) {
            ICarPlayServiceCallbackImpl carPlayServiceCallback = new ICarPlayServiceCallbackImpl(listener);
            synchronized (this) {
                this.mCarPlayServiceListenerList.add(carPlayServiceCallback);
            }
            this.mCarPlayService.registerCarPlayServiceListener(carPlayServiceCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerCarPlayServiceListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public boolean unregisterCarPlayServiceListener(ICarPlayServiceListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterCarPlayServiceListener ICarPlayService is not Ready!.");
            return false;
        }
        if (listener != null) {
            List<ICarPlayServiceCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mCarPlayServiceListenerList.size()) {
                    ICarPlayServiceCallbackImpl lstn = this.mCarPlayServiceListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mCarPlayServiceListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (ICarPlayServiceCallbackImpl carPlayServiceCallback : list) {
                this.mCarPlayService.unregisterCarPlayServiceListener(carPlayServiceCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterCarPlayServiceListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setNightMode(final boolean mode) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setNightMode ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "setNightMode mode:" + mode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$55JpMJZVvFzDZamTOuAGVd418v4
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setNightMode(mode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setLimitedUi(final boolean mode) throws Exception {
        if (this.mCarPlayService == null) {
            throw new IllegalStateException("ICarPlayService is not Ready!");
        }
        LogUtil.debug(TAG, "setLimitedUi mode:" + mode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$JRo-1IL8UuaHDscYuPVfowOvPSw
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setLimitedUi(mode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setLastUserMode(final int resource, final boolean mode) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setLastUserMode ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "setLastUserMode resource: " + resource + " mode: " + mode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$iByAp9tBHOEEureq-nfGxB-2KS0
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setLastUserMode(resource, mode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public boolean getLastUserMode(int resource) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.warning(TAG, "getLastUserMode ICarPlayService is not Ready!.");
            return false;
        }
        boolean ret = iCarPlayService.getLastUserMode(resource);
        LogUtil.debug(TAG, "getLastUserMode ret: " + ret);
        return ret;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void changeVideoFocus(final int priority, final boolean backgroundSupport, final String borrowId, final int action) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "changeVideoFocus ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "changeVideoFocus priority: " + priority + ",backgroundSupport: " + backgroundSupport + ",borrowId: " + borrowId + ",action: " + action);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$-v_70bQ_LcC7f3Nlcsp90DsFcQI
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).changeVideoFocus(priority, backgroundSupport, borrowId, action);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setSurface(final Surface surface) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setSurface ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "setSurface surface:" + surface);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$MAbw_TIr4etSbfdm49OilfcTbLo
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setSurface(surface);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setSurface(final Surface surface, final int screenType) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setSurface ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "setSurface surface:" + surface + ",screenType:" + screenType);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$mzaH1i6h1_pXvYGglr1eFylcCNI
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setSurfaceWithType(surface, screenType);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public String getBluetoothMacAddress() throws Exception {
        if (this.mCarPlayService == null) {
            throw new IllegalStateException("ICarPlayService is not Ready!");
        }
        LogUtil.info(TAG, "getBluetoothMacAddress");
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$C-DD6OGofLlWnvzCLmywu3REw1w
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).getBluetoothMacAddress();
            }
        });
        return null;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public int getSupportFeatures() throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "getSupportFeatures ICarPlayService is not Ready!.");
            return -1;
        }
        LogUtil.debug(TAG, "getSupportFeatures start");
        int ret = this.mCarPlayService.getSupportFeatures();
        LogUtil.debug(TAG, "getSupportFeatures end ret:" + ret);
        return ret;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void screenTouchEvent(final MotionEvent event) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "screenTouchEvent ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.verbose(TAG, "screenTouchEvent event:" + event);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$dZu7Mo1BVkwSWuDpLeJNqA3qGAA
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).screenTouchEvent(event);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void sendCarPlayKey(final int carPlayKeyCode, final int keyAction) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "sendCarPlayKey ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "sendCarPlayKey carPlayKeyCode:" + carPlayKeyCode + " keyAction:" + keyAction);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$7y5YnAFGQniscJRq66JXIGOyGbs
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).sendHidEventOverCarPlay(carPlayKeyCode, keyAction);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public int getCarPlaySessionStatus() throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "getCarPlaySessionStatus ICarPlayService is not Ready!.");
            return -1;
        }
        LogUtil.debug(TAG, "getCarPlaySessionStatus start.");
        int ret = this.mCarPlayService.getCarPlaySessionStatus();
        LogUtil.debug(TAG, "getCarPlaySessionStatus end ret:" + ret);
        return ret;
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setNativeAppState(final int appState, final boolean enabled, final int speechMode) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setNativeAppState ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.info(TAG, "setNativeAppState appState:" + appState + " enabled:" + enabled + " speechMode:" + speechMode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$Wlz1ZIw7TeyQnAB_eTdzloMLaZ4
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setNativeAppState(appState, enabled, speechMode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void setCarPlayConfig(final CarPlayConfig carPlayConfig) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setCarPlayConfig ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "setCarPlayConfig carPlayConfig:" + carPlayConfig);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$mCR5kdh_Cv04aJfoD_Xt9gogK3Y
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setCarPlayConfig(Converter.aidlToCarPlayConfig(carPlayConfig));
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void requestSiri(final int siriAction) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "requestSiri ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.info(TAG, "requestSiri siriAction:" + siriAction);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$Eu99e2JKe2PsceOILA6LpVb1HiE
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).requestSiri(siriAction);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void requestSiri(final int siriAction, final long siriTriggerTimestamp) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "requestSiri ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.info(TAG, "requestSiri siriAction:" + siriAction + ", siriTriggerTimestamp= " + siriTriggerTimestamp);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$NmDtB9GsAeQ6mtpOLzGfhVHOwik
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).launchSiri(siriAction, siriTriggerTimestamp);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void disconnectCarPlaySession() throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "disconnectCarPlaySession ICarPlayService is not Ready!.");
        } else {
            LogUtil.info(TAG, "disconnectCarPlaySession.");
        }
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void requestUi(final int launchMode) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "requestUi ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "requestUi launchMode:" + launchMode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$5MTdQgw3QGrYLfh6r7hqiN6HFAU
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).requestUi(launchMode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void showUi(final int screenType, final int url) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "showUi ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "showUi screenType:" + screenType + " url:" + url);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$TYCoXhW3SEkobyo9ytNR0YFeeGg
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).showUi(screenType, url);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void stopUi(final int screenType) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "stopUi ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "stopUi screenType:" + screenType);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$zkQwTxgWS7XA1bTFYjYQBajzQOU
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).stopUi(screenType);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayServiceManager
    public void updateViewArea(final String uuid, final int index, final int[] adjacentViewAreas, final int animationDurationMillis) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "updateViewArea ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.error(TAG, "updateViewArea index：" + index + ",uuid" + LogEncrypt.MacAddressEncrypt(uuid) + ",array:" + adjacentViewAreas.toString() + ",animationDurationMillis:" + animationDurationMillis);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayServiceManager$DN_56NyKkZUYie379OSu9-e46g8
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).updateViewArea(uuid, index, adjacentViewAreas, animationDurationMillis);
            }
        });
    }

    private void invokeCarPlayService(ThrowingConsumer<ICarPlayService, RemoteException> consumer) throws Exception {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService != null) {
            try {
                consumer.accept(iCarPlayService);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "mCarPlayService is null.");
    }
}
