package com.ts.carplay.manager;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import com.ts.carplay.common.CarPlayConstants;
import com.ts.carplay.common.Converter;
import com.ts.carplay.common.aidl.ICallStateCallback;
import com.ts.carplay.common.aidl.ICarPlayAppCallback;
import com.ts.carplay.common.aidl.ICarPlayService;
import com.ts.carplay.common.aidl.ICarPlayStateCallback;
import com.ts.carplay.common.aidl.IContactListCallback;
import com.ts.carplay.common.aidl.INowPlayingCallback;
import com.ts.carplay.common.aidl.IRouteGuidanceCallback;
import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.common.util.ThrowingConsumer;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.carplay.ICallStateListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.IContactListListener;
import ts.car.service.carplay.INowPlayingListener;
import ts.car.service.carplay.IRouteGuidanceListener;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformCarPlayAppManager extends SimpleServiceBase implements ICarPlayAppManager {
    private static final String TAG = PlatformCarPlayAppManager.class.getSimpleName();
    private List<ICallStateCallbackImpl> mCallStateListenerList;
    private List<ICarPlayAppCallbackImpl> mCarPlayAppListenerList;
    private ICarPlayService mCarPlayService;
    private List<ICarPlayStateCallbackImpl> mCarPlayStateListenerList;
    private List<IContactListCallbackImpl> mContactListListenerList;
    private List<INowPlayingCallbackImpl> mNowPlayingListenerList;
    private List<IRouteGuidanceCallbackImpl> mRouteGuidanceListenerList;

    @PlatformConstruction
    public PlatformCarPlayAppManager(Context context, IBinder binder) {
        super(context, CarPlayConstants.SERVICE_COMPONENT, CarPlayConstants.REMOTE_SERVICE_NAME, binder);
        this.mCarPlayStateListenerList = new ArrayList();
        this.mCarPlayAppListenerList = new ArrayList();
        this.mRouteGuidanceListenerList = new ArrayList();
        this.mCallStateListenerList = new ArrayList();
        this.mContactListListenerList = new ArrayList();
        this.mNowPlayingListenerList = new ArrayList();
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

    private class ICarPlayAppCallbackImpl extends ICarPlayAppCallback.Stub {
        ICarPlayAppListener mListener;

        public ICarPlayAppCallbackImpl(ICarPlayAppListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onCarPlayDisplayStatusChanged(boolean isShow) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayAppCallback --- onCarPlayDisplayStatusChanged isShow:" + isShow);
            this.mListener.onCarPlayDisplayStatusChanged(isShow);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onEnhancedSiriParametersChanged(int voiceActivationMode, String voiceModelLanguage) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayAppCallback --- onEnhancedSiriParametersChanged voiceActivationMode:" + voiceActivationMode + ", voiceModelLanguage: " + voiceModelLanguage);
            this.mListener.onEnhancedSiriParametersChanged(voiceActivationMode, voiceModelLanguage);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onSuggestUi(int url) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayAppCallback --- onSuggestUi url:" + url);
            this.mListener.onSuggestUi(url);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onAuxInUpdateState(int state) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayAppCallback --- onAuxInUpdateState state:" + state);
            ICarPlayAppListener iCarPlayAppListener = this.mListener;
            if (iCarPlayAppListener != null) {
                iCarPlayAppListener.onAuxInUpdateState(state);
            }
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onAltRequestSetup() throws RemoteException {
            ICarPlayAppListener iCarPlayAppListener = this.mListener;
            if (iCarPlayAppListener != null) {
                iCarPlayAppListener.onAltRequestSetup();
            }
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayAppCallback
        public void onRequestViewArea(int index) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayAppCallback --- onRequestViewArea index:" + index);
            ICarPlayAppListener iCarPlayAppListener = this.mListener;
            if (iCarPlayAppListener != null) {
                iCarPlayAppListener.onRequestViewArea(index);
            }
        }
    }

    private class ICarPlayStateCallbackImpl extends ICarPlayStateCallback.Stub {
        ICarPlayStateListener mListener;

        public ICarPlayStateCallbackImpl(ICarPlayStateListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onSessionStateChanged(int status) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayStateCallback --- onCarPlaySessionStateChanged status:" + status);
            this.mListener.onSessionStateChanged(status);
            if (status == 4) {
                PlatformCarPlayAppManager.this.stopListener();
            }
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayStateCallback --- onSessionConnectStateChanged status:" + status + " isOldConnection:" + isOldConnection + " clientAddr:" + LogEncrypt.MacAddressEncrypt(clientAddr));
            this.mListener.onSessionConnectStateChanged(status, isOldConnection, clientAddr);
            if (status == 4) {
                PlatformCarPlayAppManager.this.stopListener();
            }
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayStateCallback --- onCarPlaySessionConnectStateChanged status:" + status + " isOldConnection:" + isOldConnection + " clientAddr:" + LogEncrypt.MacAddressEncrypt(clientAddr) + " isUserConnect:" + isUserConnect);
            this.mListener.onCarPlaySessionConnectStateChanged(status, isOldConnection, clientAddr, isUserConnect);
            if (status == 4) {
                PlatformCarPlayAppManager.this.stopListener();
            }
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onAppStateChanged(int appState, boolean enable, int speechMode) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayStateCallback --- onAppStateChanged appState:" + appState + " enable:" + enable + " speechMode:" + speechMode);
            this.mListener.onAppStateChanged(appState, enable, speechMode);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayStateCallback
        public void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) throws RemoteException {
            LogUtil.debug(PlatformCarPlayAppManager.TAG, "ICarPlayServiceCallback --- onCarPlayMediaFocusStateChanged isCarPlayMedia:" + isCarPlayMedia);
            this.mListener.onCarPlayMediaFocusStateChanged(isCarPlayMedia);
        }
    }

    private class IRouteGuidanceCallbackImpl extends IRouteGuidanceCallback.Stub {
        IRouteGuidanceListener mListener;

        public IRouteGuidanceCallbackImpl(IRouteGuidanceListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.IRouteGuidanceCallback
        public void onRouteGuidanceInfoChanged(RouteGuidanceInfo info) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "IRouteGuidanceCallback --- onRouteGuidanceInfoChanged info:" + info);
            this.mListener.onRouteGuidanceInfoChanged(Converter.aidlToRouteGuidanceInfo(info));
        }
    }

    private class ICallStateCallbackImpl extends ICallStateCallback.Stub {
        ICallStateListener mListener;

        public ICallStateCallbackImpl(ICallStateListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.ICallStateCallback
        public void onCallStateInfoChanged(CallStateInfo info) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "ICallStateCallbackImpl --- onCallStateInfoChanged info:" + info);
            this.mListener.onCallStateInfoChanged(Converter.aidlToCallStateInfo(info));
        }
    }

    private class IContactListCallbackImpl extends IContactListCallback.Stub {
        IContactListListener mListener;

        public IContactListCallbackImpl(IContactListListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onRecentListCountChanged(int listCount) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "IContactListCallback --- onRecentListCountChanged listCount:" + listCount);
            this.mListener.onRecentListCountChanged(listCount);
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onRecentListChanged(List<RecentContactListInfo> list) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "IContactListCallback --- onRecentListChanged list:" + list.toString());
            this.mListener.onRecentListChanged(Converter.aidlToRecentContactList(list));
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onFavoriteListCountChanged(int listCount) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "IContactListCallback --- onFavoriteListCountChanged listCount:" + listCount);
            this.mListener.onFavoriteListCountChanged(listCount);
        }

        @Override // com.ts.carplay.common.aidl.IContactListCallback
        public void onFavoriteListChanged(List<FavoriteContactListInfo> list) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "IContactListCallback --- onFavoriteListChanged list:" + list.toString());
            this.mListener.onFavoriteListChanged(Converter.aidlToFavoriteContactList(list));
        }
    }

    private class INowPlayingCallbackImpl extends INowPlayingCallback.Stub {
        INowPlayingListener mListener;

        public INowPlayingCallbackImpl(INowPlayingListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.INowPlayingCallback
        public void onNowPlayingInfoChanged(NowPlayingInfo info) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "INowPlayingCallback --- onNowPlayingInfoChanged info:" + info);
            this.mListener.onNowPlayingInfoChanged(Converter.aidlToNowPlayingInfo(info));
        }

        @Override // com.ts.carplay.common.aidl.INowPlayingCallback
        public void onArtworkUpdate(NowPlayingInfo info) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "onArtworkUpdate --- onArtworkUpdate info:" + info);
            this.mListener.onArtworkUpdate(Converter.aidlToNowPlayingInfo(info));
        }

        @Override // com.ts.carplay.common.aidl.INowPlayingCallback
        public void onPlaybackListUpdate(int mediaItemCount, int mediaItemStartIndex, List<PlaybackMediaItemInfo> mediaItemList) throws RemoteException {
            LogUtil.verbose(PlatformCarPlayAppManager.TAG, "INowPlayingUpdateCallback --- onPlaybackListUpdate mediaItemCount:" + mediaItemCount + " mediaItemStartIndex:" + mediaItemStartIndex);
            if (mediaItemList != null) {
                LogUtil.verbose(PlatformCarPlayAppManager.TAG, "onPlaybackListUpdate mediaItemList:" + mediaItemList.toString());
            }
            this.mListener.onPlaybackListUpdated(mediaItemCount, mediaItemStartIndex, Converter.aidlToPlaybackMediaItemInfo(mediaItemList));
        }
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
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

    @Override // ts.car.service.carplay.ICarPlayAppManager
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

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean registerCarPlayAppListener(ICarPlayAppListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "registerCarPlayAppListener ICarPlayService is not Ready!.");
            return false;
        }
        if (listener != null) {
            ICarPlayAppCallbackImpl carPlayAppCallback = new ICarPlayAppCallbackImpl(listener);
            synchronized (this) {
                this.mCarPlayAppListenerList.add(carPlayAppCallback);
            }
            this.mCarPlayService.registerCarPlayAppListener(carPlayAppCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerCarPlayAppListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean unregisterCarPlayAppListener(ICarPlayAppListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterCarPlayAppListener ICarPlayService is not Ready!.");
            return false;
        }
        if (listener != null) {
            List<ICarPlayAppCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mCarPlayAppListenerList.size()) {
                    ICarPlayAppCallbackImpl lstn = this.mCarPlayAppListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mCarPlayAppListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (ICarPlayAppCallbackImpl carPlayAppCallback : list) {
                this.mCarPlayService.unregisterCarPlayAppListener(carPlayAppCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterCarPlayAppListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean registerCallStateListener(ICallStateListener listener) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.warning(TAG, "registerCallStateListener ICarPlayService is not Ready!.");
            return false;
        }
        if (iCarPlayService.getCarPlaySessionStatus() != 2) {
            throw new RemoteException("CarPlay is not connection");
        }
        LogUtil.debug(TAG, "registerCallStateListener listener: " + listener);
        if (listener != null) {
            ICallStateCallbackImpl callStateCallback = new ICallStateCallbackImpl(listener);
            synchronized (this) {
                this.mCallStateListenerList.add(callStateCallback);
            }
            this.mCarPlayService.registerCallStateListener(callStateCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerCallStateListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean unregisterCallStateListener(ICallStateListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterCallStateListener ICarPlayService is not Ready!.");
            return false;
        }
        LogUtil.debug(TAG, "unregisterCallStateListener listener: " + listener);
        if (listener != null) {
            List<ICallStateCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mCallStateListenerList.size()) {
                    ICallStateCallbackImpl lstn = this.mCallStateListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mCallStateListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (ICallStateCallbackImpl callStateCallback : list) {
                this.mCarPlayService.unregisterCallStateListener(callStateCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterCallStateListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean registerContactListListener(IContactListListener listener) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.warning(TAG, "registerContactListListener ICarPlayService is not Ready!.");
            return false;
        }
        if (iCarPlayService.getCarPlaySessionStatus() != 2) {
            throw new RemoteException("CarPlay is not connection");
        }
        LogUtil.debug(TAG, "registerContactListListener listener: " + listener);
        if (listener != null) {
            IContactListCallbackImpl contactListCallback = new IContactListCallbackImpl(listener);
            synchronized (this) {
                this.mContactListListenerList.add(contactListCallback);
            }
            this.mCarPlayService.registerContactListListener(contactListCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerContactListListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean unregisterContactListListener(IContactListListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterContactListListener ICarPlayService is not Ready!.");
            return false;
        }
        LogUtil.debug(TAG, "unregisterContactListListener listener: " + listener);
        if (listener != null) {
            List<IContactListCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mContactListListenerList.size()) {
                    IContactListCallbackImpl lstn = this.mContactListListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mContactListListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (IContactListCallbackImpl contactListCallback : list) {
                this.mCarPlayService.unregisterContactListListener(contactListCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterContactListListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean registerRouteGuidanceListener(IRouteGuidanceListener listener) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.warning(TAG, "registerRouteGuidanceListener ICarPlayService is not Ready!.");
            return false;
        }
        if (iCarPlayService.getCarPlaySessionStatus() != 2) {
            throw new RemoteException("CarPlay is not connection");
        }
        LogUtil.debug(TAG, "registerRouteGuidanceListener listener: " + listener);
        if (listener != null) {
            IRouteGuidanceCallbackImpl routeGuidanceCallback = new IRouteGuidanceCallbackImpl(listener);
            synchronized (this) {
                this.mRouteGuidanceListenerList.add(routeGuidanceCallback);
            }
            this.mCarPlayService.registerRouteGuidanceListener(routeGuidanceCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerRouteGuidanceListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean unregisterRouteGuidanceListener(IRouteGuidanceListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterRouteGuidanceListener ICarPlayService is not Ready!.");
            return false;
        }
        LogUtil.debug(TAG, "unregisterRouteGuidanceListener listener: " + listener);
        if (listener != null) {
            List<IRouteGuidanceCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mRouteGuidanceListenerList.size()) {
                    IRouteGuidanceCallbackImpl lstn = this.mRouteGuidanceListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mRouteGuidanceListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (IRouteGuidanceCallbackImpl routeGuidanceCallback : list) {
                this.mCarPlayService.unregisterRouteGuidanceListener(routeGuidanceCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterRouteGuidanceListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean registerNowPlayingListener(INowPlayingListener listener) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.warning(TAG, "registerNowPlayingListener ICarPlayService is not Ready!.");
            return false;
        }
        if (iCarPlayService.getCarPlaySessionStatus() != 2) {
            throw new RemoteException("CarPlay is not connection");
        }
        LogUtil.debug(TAG, "registerNowPlayingListener listener: " + listener);
        if (listener != null) {
            INowPlayingCallbackImpl nowPlayingCallback = new INowPlayingCallbackImpl(listener);
            synchronized (this) {
                this.mNowPlayingListenerList.add(nowPlayingCallback);
            }
            this.mCarPlayService.registerNowPlayingListener(nowPlayingCallback);
            return true;
        }
        LogUtil.debug(TAG, "registerNowPlayingListener listener is null.");
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean unregisterNowPlayingListener(INowPlayingListener listener) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "unregisterNowPlayingListener ICarPlayService is not Ready!.");
            return false;
        }
        LogUtil.debug(TAG, "unregisterNowPlayingListener listener: " + listener);
        if (listener != null) {
            List<INowPlayingCallbackImpl> list = new ArrayList<>();
            synchronized (this) {
                int i = 0;
                while (i < this.mNowPlayingListenerList.size()) {
                    INowPlayingCallbackImpl lstn = this.mNowPlayingListenerList.get(i);
                    if (lstn.mListener == listener) {
                        list.add(lstn);
                        lstn.mListener = null;
                        this.mNowPlayingListenerList.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            for (INowPlayingCallbackImpl nowPlayingCallback : list) {
                this.mCarPlayService.unregisterNowPlayingListener(nowPlayingCallback);
            }
            return true;
        }
        LogUtil.debug(TAG, "unregisterNowPlayingListener listener is null.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListener() {
        List<IRouteGuidanceCallbackImpl> routeGuidanceCallbackList;
        List<ICallStateCallbackImpl> callStateCallbackList;
        List<IContactListCallbackImpl> contactListCallbackList;
        List<INowPlayingCallbackImpl> nowPlayingCallbackList;
        if (this.mCarPlayService == null) {
            return;
        }
        LogUtil.debug(TAG, "stopListener.");
        try {
            synchronized (this) {
                routeGuidanceCallbackList = this.mRouteGuidanceListenerList;
                callStateCallbackList = this.mCallStateListenerList;
                contactListCallbackList = this.mContactListListenerList;
                nowPlayingCallbackList = this.mNowPlayingListenerList;
                this.mCallStateListenerList.clear();
                this.mRouteGuidanceListenerList.clear();
                this.mContactListListenerList.clear();
                this.mNowPlayingListenerList.clear();
            }
            for (ICallStateCallbackImpl impl : callStateCallbackList) {
                this.mCarPlayService.unregisterCallStateListener(impl);
            }
            for (IRouteGuidanceCallbackImpl impl2 : routeGuidanceCallbackList) {
                this.mCarPlayService.unregisterRouteGuidanceListener(impl2);
            }
            for (IContactListCallbackImpl impl3 : contactListCallbackList) {
                this.mCarPlayService.unregisterContactListListener(impl3);
            }
            for (INowPlayingCallbackImpl impl4 : nowPlayingCallbackList) {
                this.mCarPlayService.unregisterNowPlayingListener(impl4);
            }
        } catch (RemoteException ex) {
            LogUtil.warning(TAG, "ex: " + ex.toString());
        }
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void requestUi(final int launchMode) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "requestUi ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "requestUi launchMode:" + launchMode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$AZjtyr4FcTxIWlV05QCvkhJLj9w
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).requestUi(launchMode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void initiateCall(final int callType, final String destId, final int callService) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "initiateCall ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "initiateCall callType:" + callType + ",destId:" + destId + ",callService" + callService);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$p2cix0c1YOPy03nFXVt8uOOB-XA
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).initiateCall(callType, destId, callService);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void acceptCall(final int action, final String callUuid) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "acceptCall ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "acceptCall action:" + action + " callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$xz6VWP8Qio8iOn6WesfJe_f_wGU
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).acceptCall(action, callUuid);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void endCall(final int action, final String callUuid) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "endCall ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "endCall action:" + action + " callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$c4DT5mm7umSN9BO6WPFfk_c0ARE
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).endCall(action, callUuid);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void swapCalls() throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "swapCalls ICarPlayService is not Ready!.");
        } else {
            LogUtil.debug(TAG, "swapCalls");
            invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$Bx9ZkWHEgiEIdP19-UQGVlpyDVo
                @Override // com.ts.carplay.common.util.ThrowingConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((ICarPlayService) obj).swapCalls();
                }
            });
        }
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void sendDtmf(final int toneType, final String callUuid) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "sendDtmf ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "sendDtmf toneType:" + toneType + " callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$NzEgOGQoaFVe6CYOaF9LdwWkwqM
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).sendDtmf(toneType, callUuid);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void sendRemoteMediaPlayback(final int remoteMediaPlaybackKeyCode, final int keyAction) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "sendRemoteMediaPlayback ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "sendRemoteMediaPlayback remoteMediaPlaybackKeyCode:" + remoteMediaPlaybackKeyCode + " keyAction:" + keyAction);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$3gFozLRPnX9ltE8Mtzjl6lEeme0
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).sendHidEventOverIap(remoteMediaPlaybackKeyCode, keyAction);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void setSongElapsedTime(final long elapsedTime) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setSongElapsedTime ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.debug(TAG, "setSongElapsedTime elapsedTime:" + elapsedTime);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$OTgVGKqtbgFAI5GzyX0jZudpkAI
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setSongElapsedTime(elapsedTime);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
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

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public boolean getCarPlayDisplayStatus() throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.warning(TAG, "getCarPlayDisplayStatus ICarPlayService is not Ready!.");
            return false;
        }
        boolean isShown = iCarPlayService.getCarPlayDisPlayStatus();
        return isShown;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void playSong(ts.car.service.carplay.data.PlaybackMediaItemInfo info) throws RemoteException {
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void requestSiri(final int siriAction, final long siriTriggerTimestamp) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "requestSiri ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.info(TAG, "requestSiri siriAction:" + siriAction + ", siriTriggerTimestamp= " + siriTriggerTimestamp);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$sJTw5aroZwN46D58-4n-0jEi4m8
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).launchSiri(siriAction, siriTriggerTimestamp);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void setEnhancedSiriInfo(final List<String> voiceModelSupportedLanguages, final String voiceModelCurrentLanguage, final int supportedSiriTriggerZones) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setEnhancedSiriInfo ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.info(TAG, "setEnhancedSiriInfo voiceModelSupportedLanguages:" + voiceModelSupportedLanguages + ", voiceModelCurrentLanguage= " + voiceModelCurrentLanguage + ", supportedSiriTriggerZones= " + supportedSiriTriggerZones);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$Rlqx3179Emv8jDServOmlHWNcQA
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setEnhancedSiriInfo(voiceModelSupportedLanguages, voiceModelCurrentLanguage, supportedSiriTriggerZones);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public void setNativeAppState(final int appState, final boolean enabled, final int speechMode) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setNativeAppState ICarPlayService is not Ready!.");
            return;
        }
        LogUtil.info(TAG, "setNativeAppState appState:" + appState + " enabled:" + enabled + " speechMode:" + speechMode);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$gMlDgp6g9zFmX3Aog7l56H34d1w
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setNativeAppState(appState, enabled, speechMode);
            }
        });
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public int setUiAppearanceMode(final String uuid, final int uiMode, final int uiSetting) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setUiAppearanceMode ICarPlayService is not Ready!.");
            return -1;
        }
        LogUtil.info(TAG, "setUiAppearanceMode uuid:" + LogEncrypt.MacAddressEncrypt(uuid) + " uiMode:" + uiMode + " uiSetting:" + uiSetting);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$9_xgj5dGl8SNii6QvER-KDCaqa8
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setUiAppearanceMode(uuid, uiMode, uiSetting);
            }
        });
        return 0;
    }

    @Override // ts.car.service.carplay.ICarPlayAppManager
    public int setMapAppearanceMode(final String uuid, final int mapMode, final int mapSetting) throws Exception {
        if (this.mCarPlayService == null) {
            LogUtil.warning(TAG, "setMapAppearanceMode ICarPlayService is not Ready!.");
            return -1;
        }
        LogUtil.info(TAG, "setUiAppearanceMode uuid:" + LogEncrypt.MacAddressEncrypt(uuid) + " mapMode:" + mapMode + " mapSetting:" + mapSetting);
        invokeCarPlayService(new ThrowingConsumer() { // from class: com.ts.carplay.manager.-$$Lambda$PlatformCarPlayAppManager$FOyTLYq-3ej9dvpHe4EjjZlLvI8
            @Override // com.ts.carplay.common.util.ThrowingConsumer
            public final void accept(Object obj) throws RemoteException {
                ((ICarPlayService) obj).setMapAppearanceMode(uuid, mapMode, mapSetting);
            }
        });
        return 0;
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
