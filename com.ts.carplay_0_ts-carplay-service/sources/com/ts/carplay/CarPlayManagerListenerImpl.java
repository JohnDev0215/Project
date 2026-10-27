package com.ts.carplay;

import android.os.Handler;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.ts.carplay.common.aidl.ICallStateCallback;
import com.ts.carplay.common.aidl.ICarPlayAppCallback;
import com.ts.carplay.common.aidl.ICarPlayDeviceListCallback;
import com.ts.carplay.common.aidl.ICarPlayServiceCallback;
import com.ts.carplay.common.aidl.ICarPlayStateCallback;
import com.ts.carplay.common.aidl.IContactListCallback;
import com.ts.carplay.common.aidl.INowPlayingCallback;
import com.ts.carplay.common.aidl.IRouteGuidanceCallback;
import com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback;
import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.interfaces.ICarPlayManagerListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class CarPlayManagerListenerImpl implements ICarPlayManagerListener {
    private static final String TAG = CarPlayManagerListenerImpl.class.getName();
    private RemoteCallbackList<ICallStateCallback> mCallStateUpdateCallbackList;
    private RemoteCallbackList<ICarPlayAppCallback> mCarPlayAppCallbackList;
    private RemoteCallbackList<ICarPlayDeviceListCallback> mCarPlayDeviceListCallbackList;
    private RemoteCallbackList<ICarPlayServiceCallback> mCarPlayServiceCallbackList;
    private RemoteCallbackList<ICarPlayStateCallback> mCarPlayStateCallbackList;
    private RemoteCallbackList<IContactListCallback> mContactListUpdateCallbackList;
    private Handler mHandler;
    private RemoteCallbackList<INowPlayingCallback> mNowPlayingUpdateCallbackList;
    private RemoteCallbackList<IRouteGuidanceCallback> mRouteGuidanceUpdateCallbackList;
    private RemoteCallbackList<IWirelessCarPlayAvailabilityCallback> mWirelessCarPlayAvailabilityCallback;

    public CarPlayManagerListenerImpl(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlayMediaFocusState(final boolean isCarPlayMedia) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$cbO3K7F51YMi16D2MR3hqVF1GnU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlayMediaFocusState$0$CarPlayManagerListenerImpl(isCarPlayMedia);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlayMediaFocusState$0$CarPlayManagerListenerImpl(boolean isCarPlayMedia) {
        int size = this.mCarPlayStateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayStateCallback) this.mCarPlayStateCallbackList.getBroadcastItem(i)).onCarPlayMediaFocusStateChanged(isCarPlayMedia);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlayMediaFocusState RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayStateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onSupportFeaturesUpdate(final int supportsFeatures) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$5fTAKPR61nB-00J4Znpwl3UFOAk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSupportFeaturesUpdate$1$CarPlayManagerListenerImpl(supportsFeatures);
            }
        });
    }

    public /* synthetic */ void lambda$onSupportFeaturesUpdate$1$CarPlayManagerListenerImpl(int supportsFeatures) {
        int size = this.mCarPlayServiceCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayServiceCallback) this.mCarPlayServiceCallbackList.getBroadcastItem(i)).onSupportFeaturesChanged(supportsFeatures);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onSupportFeaturesUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayServiceCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlaySessionStateChanged(final int status) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$1c-nwVfWAgCiwhj7YZyWDl8JclQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlaySessionStateChanged$2$CarPlayManagerListenerImpl(status);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlaySessionStateChanged$2$CarPlayManagerListenerImpl(int status) {
        int size = this.mCarPlayStateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayStateCallback) this.mCarPlayStateCallbackList.getBroadcastItem(i)).onSessionStateChanged(status);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlaySessionStateChanged RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayStateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlaySessionStateChanged(final int status, final boolean isOldConnection, final String clientAddr) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$N2tVgdsnYLewm5KzLohfmAvJR4s
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlaySessionStateChanged$3$CarPlayManagerListenerImpl(status, isOldConnection, clientAddr);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlaySessionStateChanged$3$CarPlayManagerListenerImpl(int status, boolean isOldConnection, String clientAddr) {
        int size = this.mCarPlayStateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayStateCallback) this.mCarPlayStateCallbackList.getBroadcastItem(i)).onSessionConnectStateChanged(status, isOldConnection, clientAddr);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlaySessionStateChanged RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayStateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlaySessionStateChanged(final int status, final boolean isOldConnection, final String clientAddr, final boolean isUserConnect) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$hYv4UkQZfqwZtxnkGIohJ72iqFM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlaySessionStateChanged$4$CarPlayManagerListenerImpl(status, isOldConnection, clientAddr, isUserConnect);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlaySessionStateChanged$4$CarPlayManagerListenerImpl(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) {
        int size = this.mCarPlayStateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayStateCallback) this.mCarPlayStateCallbackList.getBroadcastItem(i)).onCarPlaySessionConnectStateChanged(status, isOldConnection, clientAddr, isUserConnect);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlaySessionStateChanged RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayStateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlayDisplayStatusChanged(final boolean isShow) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$6Okr0KI95sCIbxek8Am6uZ97EPc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlayDisplayStatusChanged$5$CarPlayManagerListenerImpl(isShow);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlayDisplayStatusChanged$5$CarPlayManagerListenerImpl(boolean isShow) {
        int size = this.mCarPlayAppCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayAppCallback) this.mCarPlayAppCallbackList.getBroadcastItem(i)).onCarPlayDisplayStatusChanged(isShow);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlayDisplayStatusChanged RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayAppCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onEnhancedSiriParamsUpdate(final int inMode, final String inLanguage) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$yWqlvhbL_lYdkUUD7WkDWIRAhGk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEnhancedSiriParamsUpdate$6$CarPlayManagerListenerImpl(inMode, inLanguage);
            }
        });
    }

    public /* synthetic */ void lambda$onEnhancedSiriParamsUpdate$6$CarPlayManagerListenerImpl(int inMode, String inLanguage) {
        int size = this.mCarPlayAppCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayAppCallback) this.mCarPlayAppCallbackList.getBroadcastItem(i)).onEnhancedSiriParametersChanged(inMode, inLanguage);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onEnhancedSiriParamsUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayAppCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onAuxInUpdateState(final int state) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$hbu-EQweFXXqqlREPCYY8S9fDqY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAuxInUpdateState$7$CarPlayManagerListenerImpl(state);
            }
        });
    }

    public /* synthetic */ void lambda$onAuxInUpdateState$7$CarPlayManagerListenerImpl(int state) {
        int size = this.mCarPlayAppCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayAppCallback) this.mCarPlayAppCallbackList.getBroadcastItem(i)).onAuxInUpdateState(state);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onAuxInUpdateState RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayAppCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onAltRequestSetup() {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$sgkyagyhYZu_US_B0kseuO1Sxwg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAltRequestSetup$8$CarPlayManagerListenerImpl();
            }
        });
    }

    public /* synthetic */ void lambda$onAltRequestSetup$8$CarPlayManagerListenerImpl() {
        int size = this.mCarPlayAppCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayAppCallback) this.mCarPlayAppCallbackList.getBroadcastItem(i)).onAltRequestSetup();
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onAltRequestSetup RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayAppCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onSuggestUi(final int url) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$LxSg1WvYubwSocRAvSDaqYna5Lc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSuggestUi$9$CarPlayManagerListenerImpl(url);
            }
        });
    }

    public /* synthetic */ void lambda$onSuggestUi$9$CarPlayManagerListenerImpl(int url) {
        int size = this.mCarPlayAppCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayAppCallback) this.mCarPlayAppCallbackList.getBroadcastItem(i)).onSuggestUi(url);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onSuggestUi RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayAppCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onRequestVideoFocus(final int requestType) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$r_KQOVKaP951e7SUejgD4Ja79rA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRequestVideoFocus$10$CarPlayManagerListenerImpl(requestType);
            }
        });
    }

    public /* synthetic */ void lambda$onRequestVideoFocus$10$CarPlayManagerListenerImpl(int requestType) {
        int size = this.mCarPlayServiceCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayServiceCallback) this.mCarPlayServiceCallbackList.getBroadcastItem(i)).onRequestVideoFocusChanged(requestType);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRequestVideoFocus RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayServiceCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlayAppStateChanged(final int appState, final boolean enable, final int speechMode) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$i19ld3gKYDd8NarNBYd9K53yzM0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlayAppStateChanged$11$CarPlayManagerListenerImpl(appState, enable, speechMode);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlayAppStateChanged$11$CarPlayManagerListenerImpl(int appState, boolean enable, int speechMode) {
        int size = this.mCarPlayStateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayStateCallback) this.mCarPlayStateCallbackList.getBroadcastItem(i)).onAppStateChanged(appState, enable, speechMode);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onAppStateChanged RemoteException appState: " + appState + " enable: " + enable + " speechMode:" + speechMode);
                ex.printStackTrace();
            }
        }
        this.mCarPlayStateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlayAppStateChanged(final int appState, final int entity, final int speechMode) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$MPZz98FQc071oxnig433PYX4UJs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlayAppStateChanged$12$CarPlayManagerListenerImpl(appState, entity, speechMode);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlayAppStateChanged$12$CarPlayManagerListenerImpl(int appState, int entity, int speechMode) {
        int size = this.mCarPlayServiceCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayServiceCallback) this.mCarPlayServiceCallbackList.getBroadcastItem(i)).onAppStateChanged(appState, entity, speechMode);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onAppStateChanged RemoteException appState: " + appState + " entity: " + entity + " speechMode:" + speechMode);
                ex.printStackTrace();
            }
        }
        this.mCarPlayServiceCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onNowPlayingUpdate(final NowPlayingInfo info) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$ZxTaxrJnWZOwxOsgWFSIc0E5XwU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onNowPlayingUpdate$13$CarPlayManagerListenerImpl(info);
            }
        });
    }

    public /* synthetic */ void lambda$onNowPlayingUpdate$13$CarPlayManagerListenerImpl(NowPlayingInfo info) {
        int size = this.mNowPlayingUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((INowPlayingCallback) this.mNowPlayingUpdateCallbackList.getBroadcastItem(i)).onNowPlayingInfoChanged(info);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onNowPlayingUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mNowPlayingUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onRequestViewArea(final int index) {
        LogUtil.debug(TAG, "onRequestViewArea index:" + index);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$qF5IPx8y9LpHf9JKToXfQrY1Rgk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRequestViewArea$14$CarPlayManagerListenerImpl(index);
            }
        });
    }

    public /* synthetic */ void lambda$onRequestViewArea$14$CarPlayManagerListenerImpl(int index) {
        int size = this.mCarPlayAppCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayAppCallback) this.mCarPlayAppCallbackList.getBroadcastItem(i)).onRequestViewArea(index);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRequestViewArea RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayAppCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onArtworkUpdate(final NowPlayingInfo info) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$8Em0I6VlCZjVFRxDlyWbkFPVJGw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onArtworkUpdate$15$CarPlayManagerListenerImpl(info);
            }
        });
    }

    public /* synthetic */ void lambda$onArtworkUpdate$15$CarPlayManagerListenerImpl(NowPlayingInfo info) {
        int size = this.mNowPlayingUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((INowPlayingCallback) this.mNowPlayingUpdateCallbackList.getBroadcastItem(i)).onArtworkUpdate(info);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onArtworkUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mNowPlayingUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCallStateUpdate(final CallStateInfo info) {
        LogUtil.debug(TAG, "onCallStateUpdate info: " + info);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$uzkXxxDc4-QTzTRGf3JXPU_D1kk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCallStateUpdate$16$CarPlayManagerListenerImpl(info);
            }
        });
    }

    public /* synthetic */ void lambda$onCallStateUpdate$16$CarPlayManagerListenerImpl(CallStateInfo info) {
        int size = this.mCallStateUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICallStateCallback) this.mCallStateUpdateCallbackList.getBroadcastItem(i)).onCallStateInfoChanged(info);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCallStateUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCallStateUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onRecentListCount(final int listCount) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$zFOArGXUxbyBS7qxcgyYocLvCsM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRecentListCount$17$CarPlayManagerListenerImpl(listCount);
            }
        });
    }

    public /* synthetic */ void lambda$onRecentListCount$17$CarPlayManagerListenerImpl(int listCount) {
        int size = this.mContactListUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IContactListCallback) this.mContactListUpdateCallbackList.getBroadcastItem(i)).onRecentListCountChanged(listCount);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRecentListCount RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mContactListUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onRecentListUpdate(final List<RecentContactListInfo> list) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$54EVQzPlxp5EoUmqdoifKbWbSr8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRecentListUpdate$18$CarPlayManagerListenerImpl(list);
            }
        });
    }

    public /* synthetic */ void lambda$onRecentListUpdate$18$CarPlayManagerListenerImpl(List list) {
        int size = this.mContactListUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IContactListCallback) this.mContactListUpdateCallbackList.getBroadcastItem(i)).onRecentListChanged(list);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRecentListUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mContactListUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onFavoriteListCount(final int listCount) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$5-NJm6ic8M8vLv25B1Esjpy49hw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFavoriteListCount$19$CarPlayManagerListenerImpl(listCount);
            }
        });
    }

    public /* synthetic */ void lambda$onFavoriteListCount$19$CarPlayManagerListenerImpl(int listCount) {
        int size = this.mContactListUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IContactListCallback) this.mContactListUpdateCallbackList.getBroadcastItem(i)).onFavoriteListCountChanged(listCount);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onFavoriteListCount RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mContactListUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onFavoriteListUpdate(final List<FavoriteContactListInfo> list) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$y8OGZp5r4taHnaREqKxA27WipK8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFavoriteListUpdate$20$CarPlayManagerListenerImpl(list);
            }
        });
    }

    public /* synthetic */ void lambda$onFavoriteListUpdate$20$CarPlayManagerListenerImpl(List list) {
        int size = this.mContactListUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IContactListCallback) this.mContactListUpdateCallbackList.getBroadcastItem(i)).onFavoriteListChanged(list);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onFavoriteListUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mContactListUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onRouteGuidanceUpdate(final RouteGuidanceInfo info) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$--I09TNdGmL-K-19dUP0XFr6iHo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRouteGuidanceUpdate$21$CarPlayManagerListenerImpl(info);
            }
        });
    }

    public /* synthetic */ void lambda$onRouteGuidanceUpdate$21$CarPlayManagerListenerImpl(RouteGuidanceInfo info) {
        int size = this.mRouteGuidanceUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IRouteGuidanceCallback) this.mRouteGuidanceUpdateCallbackList.getBroadcastItem(i)).onRouteGuidanceInfoChanged(info);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRouteGuidanceUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mRouteGuidanceUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onPlaybackListUpdate(final int mediaItemCount, final int mediaItemStartIndex, List<PlaybackMediaItemInfo> mediaItemList) {
        final List<PlaybackMediaItemInfo> list = new ArrayList<>(mediaItemList);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$DVYDsNX3DQnB7_MBsrBKQfSoY2U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onPlaybackListUpdate$22$CarPlayManagerListenerImpl(mediaItemCount, mediaItemStartIndex, list);
            }
        });
    }

    public /* synthetic */ void lambda$onPlaybackListUpdate$22$CarPlayManagerListenerImpl(int mediaItemCount, int mediaItemStartIndex, List list) {
        int size = this.mNowPlayingUpdateCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((INowPlayingCallback) this.mNowPlayingUpdateCallbackList.getBroadcastItem(i)).onPlaybackListUpdate(mediaItemCount, mediaItemStartIndex, list);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onPlaybackListUpdate RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mNowPlayingUpdateCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onRequestProjection(final CarPlayDevice device, final int type) {
        LogUtil.debug(TAG, "onRequestProjection device:" + device + "type" + type);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$1SyAQjV6pQb5Sgvu6m1T0rTLVso
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRequestProjection$23$CarPlayManagerListenerImpl(device, type);
            }
        });
    }

    public /* synthetic */ void lambda$onRequestProjection$23$CarPlayManagerListenerImpl(CarPlayDevice device, int type) {
        int size = this.mCarPlayDeviceListCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayDeviceListCallback) this.mCarPlayDeviceListCallbackList.getBroadcastItem(i)).onRequestProjection(device, type);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onRequestProjection RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayDeviceListCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onDeviceMerged(final CarPlayDevice device) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$471z2g7gWuJdmJ9OZZuK7oD19oU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceMerged$24$CarPlayManagerListenerImpl(device);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceMerged$24$CarPlayManagerListenerImpl(CarPlayDevice device) {
        int size = this.mCarPlayDeviceListCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayDeviceListCallback) this.mCarPlayDeviceListCallbackList.getBroadcastItem(i)).onDeviceMerged(device);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onDeviceMerged RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayDeviceListCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlayDeviceStatusChanged(final CarPlayDevice device) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$CbB-CSSwdp-ee0wqPF20sd49Bpo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlayDeviceStatusChanged$25$CarPlayManagerListenerImpl(device);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlayDeviceStatusChanged$25$CarPlayManagerListenerImpl(CarPlayDevice device) {
        int size = this.mCarPlayDeviceListCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayDeviceListCallback) this.mCarPlayDeviceListCallbackList.getBroadcastItem(i)).onCarPlayDeviceStatusChanged(device);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlayDeviceStatusChanged RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayDeviceListCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onWirelessCarPlayAvailabilityChanged(final String btAddr, final boolean enabled) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$XxO7PDpUOlCSViNRF6lzcMiu4Eg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onWirelessCarPlayAvailabilityChanged$26$CarPlayManagerListenerImpl(btAddr, enabled);
            }
        });
    }

    public /* synthetic */ void lambda$onWirelessCarPlayAvailabilityChanged$26$CarPlayManagerListenerImpl(String btAddr, boolean enabled) {
        int size = this.mWirelessCarPlayAvailabilityCallback.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IWirelessCarPlayAvailabilityCallback) this.mWirelessCarPlayAvailabilityCallback.getBroadcastItem(i)).onWirelessCarPlayAvailabilityChanged(btAddr, enabled);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onWirelessCarPlayAvailabilityChanged RemoteException.");
                ex.printStackTrace();
            }
            RemoteCallbackList<IWirelessCarPlayAvailabilityCallback> remoteCallbackList = this.mWirelessCarPlayAvailabilityCallback;
            remoteCallbackList.unregister((IWirelessCarPlayAvailabilityCallback) remoteCallbackList.getBroadcastItem(i));
        }
        this.mWirelessCarPlayAvailabilityCallback.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void onCarPlayOobStartEvent(final byte[] macAddress) {
        LogUtil.debug(TAG, "onCarPlayOobStartEvent");
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$bwd9L3VRuwz6ABDVOAmKhaypTyk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPlayOobStartEvent$27$CarPlayManagerListenerImpl(macAddress);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPlayOobStartEvent$27$CarPlayManagerListenerImpl(byte[] macAddress) {
        int size = this.mCarPlayDeviceListCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayDeviceListCallback) this.mCarPlayDeviceListCallbackList.getBroadcastItem(i)).onCarPlayOobStartEvent(macAddress);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "onCarPlayOobStartEvent RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayDeviceListCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void oniPhoneAttachedOverUsb(final CarPlayDevice device, final boolean supported) {
        LogUtil.debug(TAG, "oniPhoneAttachedOverUsb supported: " + supported + ", device:" + device);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$0m1SR906vgNJ1aoVslG9u-9E47c
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$oniPhoneAttachedOverUsb$28$CarPlayManagerListenerImpl(device, supported);
            }
        });
    }

    public /* synthetic */ void lambda$oniPhoneAttachedOverUsb$28$CarPlayManagerListenerImpl(CarPlayDevice device, boolean supported) {
        int size = this.mCarPlayDeviceListCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayDeviceListCallback) this.mCarPlayDeviceListCallbackList.getBroadcastItem(i)).oniPhoneAttachedOverUsb(device, supported);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "oniPhoneAttachedOverUsb RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayDeviceListCallbackList.finishBroadcast();
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayManagerListener
    public void oniPhoneDetachedOverUsb(final CarPlayDevice device) {
        LogUtil.debug(TAG, "oniPhoneDetachedOverUsb device:" + device);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.-$$Lambda$CarPlayManagerListenerImpl$jHcZW_YqiL5U2LpP8QfjHSzQOeY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$oniPhoneDetachedOverUsb$29$CarPlayManagerListenerImpl(device);
            }
        });
    }

    public /* synthetic */ void lambda$oniPhoneDetachedOverUsb$29$CarPlayManagerListenerImpl(CarPlayDevice device) {
        int size = this.mCarPlayDeviceListCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((ICarPlayDeviceListCallback) this.mCarPlayDeviceListCallbackList.getBroadcastItem(i)).oniPhoneDetachedOverUsb(device);
            } catch (RemoteException ex) {
                LogUtil.warning(TAG, "oniPhoneDetachedOverUsb RemoteException.");
                ex.printStackTrace();
            }
        }
        this.mCarPlayDeviceListCallbackList.finishBroadcast();
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
}
