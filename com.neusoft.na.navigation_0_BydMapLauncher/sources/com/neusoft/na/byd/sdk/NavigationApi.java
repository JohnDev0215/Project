package com.neusoft.na.byd.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import api.notifications.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseNotifications;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviPresentationInfo;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviStatusChanged;
import api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqAddViaPosition;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqAppendVia;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqGuideStart;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqModifyDestination;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqNaviAlongTheWaySearch;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqNaviRouteFullView;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqNaviSetRouteOption;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqOpenPoiDetail;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqPOISearchByCategories;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqPOISearchByKeyword;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqRoutePlanning;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSetAddress;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSmartNaviStart;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSmartRoutePlanning;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSwitchFavorite;
import api.requests.com.neusoft.byd.bydnavapi.ReqRequestShowHomeOrCompanySetPage;
import api.requests.com.neusoft.byd.bydnavapi.ReqShowPoiIconsFromVR;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviMapViewMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviSwitchFullView;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviZoomIn;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviZoomOut;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqSwitchDayNightMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchMapSetting;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchTrafficInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResCheckDestinationHistoryEmpty;
import api.responses.com.neusoft.byd.bydnavapi.ResCheckFavoriteEmpty;
import api.responses.com.neusoft.byd.bydnavapi.ResCheckFavoriteFull;
import api.responses.com.neusoft.byd.bydnavapi.ResGetCompany;
import api.responses.com.neusoft.byd.bydnavapi.ResGetCurrentPlace;
import api.responses.com.neusoft.byd.bydnavapi.ResGetFavoriteStatus;
import api.responses.com.neusoft.byd.bydnavapi.ResGetHome;
import api.responses.com.neusoft.byd.bydnavapi.ResGetNaviMaxViaCount;
import api.responses.com.neusoft.byd.bydnavapi.ResGetPoiDetailStatus;
import api.responses.com.neusoft.byd.bydnavapi.ResGetSupportedRouteOptions;
import api.responses.com.neusoft.byd.bydnavapi.ResGetUnfinishedRoute;
import api.responses.com.neusoft.byd.bydnavapi.ResHidePoiIconsFromVR;
import api.responses.com.neusoft.byd.bydnavapi.ResReqAddViaPosition;
import api.responses.com.neusoft.byd.bydnavapi.ResReqAppendVia;
import api.responses.com.neusoft.byd.bydnavapi.ResReqGuideStart;
import api.responses.com.neusoft.byd.bydnavapi.ResReqModifyDestination;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviAddFavorite;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviAlongTheWaySearch;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviGetRouteOption;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviRouteFullView;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviSetRouteOption;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviViaPosition;
import api.responses.com.neusoft.byd.bydnavapi.ResReqOpenHistoryDestination;
import api.responses.com.neusoft.byd.bydnavapi.ResReqPOISearchByCategories;
import api.responses.com.neusoft.byd.bydnavapi.ResReqPOISearchByKeyword;
import api.responses.com.neusoft.byd.bydnavapi.ResReqRoutePlanning;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSetAddress;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSmartNaviStart;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSmartRoutePlanning;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSwitchFavorite;
import api.responses.com.neusoft.byd.bydnavapi.ResRequestShowHomeOrCompanySetPage;
import api.responses.com.neusoft.byd.bydnavapi.ResResumeNavigation;
import api.responses.com.neusoft.byd.bydnavapi.ResShowPoiIconsFromVR;
import api.responses.com.neusoft.common.commonsysapiinverse.ResCheckNaviGuide;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetDayNightMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapAppStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapSettingStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviSwitchFullView;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetTrafficInfoState;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviGuideStop;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviRemainTimeDistance;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviSwitchFullView;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviZoomIn;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviZoomOut;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqSwitchDayNightMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSwitchTrafficInfo;
import api.structs.com.neusoft.byd.AddressType;
import api.structs.com.neusoft.byd.FavoriteStatus;
import api.structs.com.neusoft.byd.FavoriteType;
import api.structs.com.neusoft.byd.GeoPosition;
import api.structs.com.neusoft.byd.LocationInfo;
import api.structs.com.neusoft.byd.LocationType;
import api.structs.com.neusoft.byd.NaviCode;
import api.structs.com.neusoft.byd.PlaceInfo;
import api.structs.com.neusoft.byd.StrategyType;
import api.structs.com.neusoft.common.MapStyleType;
import api.structs.com.neusoft.common.NavAPPActivityStatus;
import api.structs.com.neusoft.common.RemainTimeDist;
import api.structs.com.neusoft.common.SwitchStatus;
import api.structs.com.neusoft.common.TrafficMode;
import api.structs.com.neusoft.common.ViewModeType;
import com.byd.naviserviceapi.INaviSearchAlongCallback;
import com.byd.naviserviceapi.INaviSearchPOICallback;
import com.byd.naviserviceapi.INaviServiceCallback;
import com.byd.naviserviceapi.INaviStateCallback;
import com.byd.naviserviceapi.Place;
import com.byd.naviserviceapi.RouteInfo;
import com.bydux.api.common.tools.util.ListUtils;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.na.common.ClientEndpointManger;
import com.neusoft.na.common.ClientEndpointMangerCallback;
import com.neusoft.na.common.util.MyAppCrashHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java9.util.function.Consumer;
import java9.util.function.Function;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes.dex */
public class NavigationApi implements ClientEndpointMangerCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CLOSE_MAINACTIVITY = "com.neusoft.na.mots.closeMainActivity";
    private static final String TAG = "NavigationApi";
    private static final int TIME_OUT = 2000;
    private BydNavApiRequests bydNavApiRequests;
    private CommonSysApiInverseNotifications commonNotifications;
    private Context mContext;
    private boolean mTtsPlayState;
    private OneCoreWSContextUtil motsWsContext;
    private CommonSysApiInverseRequests requestsCommon;
    private List<IAttribute> remoteAttributes = new ArrayList();
    private List<Subscription> remoteSubscriptions = new ArrayList();
    private boolean isServerConnect = false;
    private RemoteCallbackList<INaviServiceCallback> mNaviServiceCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<INaviStateCallback> mNaviStateCallbackList = new RemoteCallbackList<>();
    private final String ACTION_NEUSOFT_NAVIGATION_PLAY_STATE = "com.neusoft.navigation.tts.play.state";
    private final String ACTION_KEY_TTS_PLAY_STATE = "ttsPlayState";
    private boolean isHasPermission = false;

    public int highlightPlaceMark(List<Place> list) {
        return -2;
    }

    public int reqNaviSupportedOption() throws RemoteException {
        return 1;
    }

    public NavigationApi(Context context) {
        Log.d(TAG, TAG);
        this.mContext = context;
        context.registerReceiver(new NavigationBroadcast(), new IntentFilter("com.neusoft.navigation.tts.play.state"));
        ClientEndpointManger companion = ClientEndpointManger.INSTANCE.getInstance(context);
        companion.setClientEndpointMangerCallback(this);
        if (companion.getConnectionState() == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
            onServerConnect(companion.getWsContext());
        }
    }

    private class NavigationBroadcast extends BroadcastReceiver {
        private NavigationBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(NavigationApi.TAG, "onReceive: " + intent.getAction());
            if ("com.neusoft.navigation.tts.play.state".equals(intent.getAction())) {
                NavigationApi.this.mTtsPlayState = intent.getBooleanExtra("ttsPlayState", false);
                Log.i(NavigationApi.TAG, "onReceive: " + NavigationApi.this.mTtsPlayState);
            }
        }
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerConnect(OneCoreWSContextUtil oneCoreWSContextUtil) {
        Log.d(TAG, "onServerConnect: isServerConnect:" + this.isServerConnect);
        if (this.isServerConnect) {
            return;
        }
        this.isServerConnect = true;
        this.motsWsContext = oneCoreWSContextUtil;
        this.requestsCommon = new CommonSysApiInverseRequests(this.motsWsContext);
        this.bydNavApiRequests = new BydNavApiRequests(this.motsWsContext);
        CommonSysApiInverseNotifications commonSysApiInverseNotifications = new CommonSysApiInverseNotifications(this.motsWsContext);
        this.commonNotifications = commonSysApiInverseNotifications;
        IAttribute<NotifyNaviStatusChanged> iAttributeNaviStatusChangedAttribute = commonSysApiInverseNotifications.naviStatusChangedAttribute();
        iAttributeNaviStatusChangedAttribute.signIn();
        this.remoteAttributes.add(iAttributeNaviStatusChangedAttribute);
        this.remoteSubscriptions.add(iAttributeNaviStatusChangedAttribute.getObservable().subscribe(new Action1() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda99
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.m114lambda$onServerConnect$0$comneusoftnabydsdkNavigationApi((NotifyNaviStatusChanged) obj);
            }
        }));
        IAttribute<NotifyNaviPresentationInfo> iAttributeNaviPresentationInfoAttribute = this.commonNotifications.naviPresentationInfoAttribute();
        iAttributeNaviPresentationInfoAttribute.signIn();
        this.remoteAttributes.add(iAttributeNaviPresentationInfoAttribute);
        this.remoteSubscriptions.add(iAttributeNaviPresentationInfoAttribute.getObservable().subscribe(new Action1() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda100
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.m115lambda$onServerConnect$1$comneusoftnabydsdkNavigationApi((NotifyNaviPresentationInfo) obj);
            }
        }));
    }

    /* JADX INFO: renamed from: lambda$onServerConnect$0$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m114lambda$onServerConnect$0$comneusoftnabydsdkNavigationApi(NotifyNaviStatusChanged notifyNaviStatusChanged) {
        notifyStateCallback(notifyNaviStatusChanged.getNaviStatusChanged().getValue().intValue());
    }

    /* JADX INFO: renamed from: lambda$onServerConnect$1$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m115lambda$onServerConnect$1$comneusoftnabydsdkNavigationApi(NotifyNaviPresentationInfo notifyNaviPresentationInfo) {
        boolean zBooleanValue = notifyNaviPresentationInfo.getNaviPresentationInfo().getActivationStatus().booleanValue();
        boolean zBooleanValue2 = notifyNaviPresentationInfo.getNaviPresentationInfo().getPrivacyPolicyStatus().booleanValue();
        Log.i(TAG, "NotifyNaviPresentationInfo: " + zBooleanValue + ListUtils.DEFAULT_JOIN_SEPARATOR + zBooleanValue2);
        this.isHasPermission = zBooleanValue && zBooleanValue2;
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerDisconnect() {
        Log.d(TAG, "onServerDisconnect: ");
        this.isServerConnect = false;
        this.isHasPermission = false;
        if (this.motsWsContext != null) {
            this.motsWsContext = null;
            Iterator<IAttribute> it = this.remoteAttributes.iterator();
            while (it.hasNext()) {
                it.next().signOut();
            }
            this.remoteAttributes.clear();
            Iterator<Subscription> it2 = this.remoteSubscriptions.iterator();
            while (it2.hasNext()) {
                it2.next().unsubscribe();
            }
            this.remoteSubscriptions.clear();
        }
    }

    private synchronized void notifyStateCallback(int i) {
        try {
            this.mNaviStateCallbackList.beginBroadcast();
            for (int i2 = 0; i2 < this.mNaviStateCallbackList.getRegisteredCallbackCount(); i2++) {
                INaviStateCallback iNaviStateCallback = (INaviStateCallback) this.mNaviStateCallbackList.getBroadcastItem(i2);
                if (i == 1 || i == 2) {
                    iNaviStateCallback.onMapOverViewChange(i);
                } else if (i == 30 || i == 32) {
                    iNaviStateCallback.onDayNightModeChange(i);
                } else if (i == 49) {
                    iNaviStateCallback.onOpenHistoryDestination(i);
                } else if (i != 50) {
                    switch (i) {
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            iNaviStateCallback.onMapStatusUpdate(i);
                            break;
                        default:
                            switch (i) {
                                case 16:
                                case 17:
                                    iNaviStateCallback.onNaviStatusUpdate(i);
                                    continue;
                                case 18:
                                case 19:
                                    break;
                                default:
                                    switch (i) {
                                        case 21:
                                        case 22:
                                            break;
                                        case 23:
                                        case 24:
                                            iNaviStateCallback.onTrafficStatusUpdate(i);
                                            break;
                                        case 25:
                                        case 26:
                                        case 27:
                                            iNaviStateCallback.onNaviOrientationChange(i);
                                            break;
                                        default:
                                            switch (i) {
                                                case 44:
                                                case 45:
                                                    iNaviStateCallback.onMapSettingUpdate(i);
                                                    break;
                                                case 46:
                                                case 47:
                                                    iNaviStateCallback.onFavoriteStatusUpdate(i);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 55:
                                                        case 56:
                                                        case 57:
                                                        case 58:
                                                            iNaviStateCallback.onPOIDetailStatusUpdate(i);
                                                            break;
                                                        default:
                                                            continue;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            iNaviStateCallback.onZoomStatusUpdate(i);
                            break;
                    }
                } else {
                    iNaviStateCallback.onOpenRouteOption(i);
                }
            }
            this.mNaviStateCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean registerCallback(INaviServiceCallback iNaviServiceCallback) {
        Log.d(TAG, "registerCallback: " + iNaviServiceCallback);
        if (iNaviServiceCallback != null) {
            return this.mNaviServiceCallbackList.register(iNaviServiceCallback);
        }
        return false;
    }

    public boolean unregisterCallback(INaviServiceCallback iNaviServiceCallback) {
        Log.d(TAG, "unregisterCallback: " + iNaviServiceCallback);
        if (iNaviServiceCallback != null) {
            return this.mNaviServiceCallbackList.unregister(iNaviServiceCallback);
        }
        return false;
    }

    public boolean registerStateCallback(INaviStateCallback iNaviStateCallback) {
        Log.d(TAG, "registerStateCallback: " + iNaviStateCallback);
        RemoteCallbackList<INaviStateCallback> remoteCallbackList = this.mNaviStateCallbackList;
        if (remoteCallbackList != null) {
            return remoteCallbackList.register(iNaviStateCallback);
        }
        return false;
    }

    public boolean unregisterStateCallback(INaviStateCallback iNaviStateCallback) {
        Log.d(TAG, "unregisterStateCallback: " + iNaviStateCallback);
        RemoteCallbackList<INaviStateCallback> remoteCallbackList = this.mNaviStateCallbackList;
        if (remoteCallbackList != null) {
            return remoteCallbackList.unregister(iNaviStateCallback);
        }
        return false;
    }

    public int reqNaviLaunchMap() {
        Log.d(TAG, "launchMap");
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent();
            launchIntentForPackage.setClassName(this.mContext, "com.neusoft.na.mots.MainActivity");
            launchIntentForPackage.setFlags(268435456);
        }
        this.mContext.startActivity(launchIntentForPackage);
        return 1;
    }

    public int reqNaviBackToMap() {
        Log.d(TAG, "backToMap: ");
        return reqNaviLaunchMap();
    }

    public boolean exitMap() {
        Log.d(TAG, "exitMap: ");
        Intent intent = new Intent();
        intent.setAction("com.neusoft.na.mots.closeMainActivity");
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public int reqOpenHistoryDestination() {
        Log.d(TAG, "reqOpenHistoryDestination");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        reqNaviLaunchMap();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestReqOpenHistoryDestination().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda2
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m133xc134b1fb(atomicInteger, countDownLatch, (ResReqOpenHistoryDestination) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda3
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqOpenHistoryDestination$3((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqOpenHistoryDestination");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqOpenHistoryDestination$2$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m133xc134b1fb(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResReqOpenHistoryDestination resReqOpenHistoryDestination) {
        atomicInteger.set(getReturnCode(resReqOpenHistoryDestination.getArguments().getNaviCode().getValue().intValue()));
        Log.d(TAG, "reqOpenHistoryDestination: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$reqOpenHistoryDestination$3(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviZoomIn(boolean z) {
        Log.d(TAG, "reqNaviZoomIn: " + z);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestReqNaviZoomIn(new ReqReqNaviZoomIn.Arguments(Boolean.valueOf(z))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda39
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m131lambda$reqNaviZoomIn$4$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResReqNaviZoomIn.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda40
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviZoomIn$5((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqNaviZoomIn");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqNaviZoomIn$4$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m131lambda$reqNaviZoomIn$4$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResReqNaviZoomIn.Arguments arguments) {
        Log.d(TAG, "reqNaviZoomIn: thenAccept");
        atomicInteger.set(getReturnCode(arguments.getNaviCode().getValue().intValue()));
        Log.d(TAG, "reqNaviZoomIn: " + atomicInteger.get());
        countDownLatch.countDown();
        Log.d(TAG, "reqNaviZoomIn: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviZoomIn$5(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviZoomOut(boolean z) {
        Log.d(TAG, "reqNaviZoomOut: " + z);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestReqNaviZoomOut(new ReqReqNaviZoomOut.Arguments(Boolean.valueOf(z))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda64
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m132lambda$reqNaviZoomOut$6$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResReqNaviZoomOut.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda65
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviZoomOut$7((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqNaviZoomOut");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqNaviZoomOut$6$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m132lambda$reqNaviZoomOut$6$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResReqNaviZoomOut.Arguments arguments) {
        Log.d(TAG, "reqNaviZoomOut: thenAccept");
        atomicInteger.set(getReturnCode(arguments.getNaviCode().getValue().intValue()));
        Log.d(TAG, "reqNaviZoomOut: " + atomicInteger.get());
        countDownLatch.countDown();
        Log.d(TAG, "reqNaviZoomOut: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviZoomOut$7(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSwitchTraffic(int i) {
        Log.d(TAG, "reqSwitchTraffic:trafficType:: " + i);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.requestsCommon.requestSwitchTrafficInfo(new ReqSwitchTrafficInfo.Arguments(i == 1 ? TrafficMode.TRAFFIC_ON : TrafficMode.TRAFFIC_OFF)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda6
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m143lambda$reqSwitchTraffic$8$comneusoftnabydsdkNavigationApi((ResSwitchTrafficInfo.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda7
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqSwitchTraffic$9((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqSwitchTraffic$8$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m143lambda$reqSwitchTraffic$8$comneusoftnabydsdkNavigationApi(ResSwitchTrafficInfo.Arguments arguments) {
        Log.d(TAG, "reqSwitchTriffic: thenAccept");
        try {
            int iIntValue = arguments.getResultCode().intValue();
            this.mNaviStateCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviStateCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviStateCallback) this.mNaviStateCallbackList.getBroadcastItem(i)).onTrafficStatusUpdate(iIntValue);
            }
            this.mNaviStateCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqSwitchTriffic: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqSwitchTraffic$9(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviMapMode(int i) {
        Log.d(TAG, "setMapViewMode: viewMode:" + i);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestReqNaviMapViewMode(new ReqReqNaviMapViewMode.Arguments(ViewModeType.getByValue(Integer.valueOf(i)))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda79
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m125lambda$reqNaviMapMode$10$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResReqNaviMapViewMode.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda81
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviMapMode$11((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqNaviZoomOut");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqNaviMapMode$10$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m125lambda$reqNaviMapMode$10$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResReqNaviMapViewMode.Arguments arguments) {
        Log.d(TAG, "reqNaviMapMode: thenAccept");
        atomicInteger.set(getReturnCode(arguments.getNaviCode().getValue().intValue()));
        Log.d(TAG, "reqNaviMapMode: " + atomicInteger.get());
        countDownLatch.countDown();
        Log.d(TAG, "reqNaviMapMode: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviMapMode$11(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviSwitchFullView(boolean z) {
        Log.d(TAG, "reqNaviSwitchFullView: " + z);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.requestsCommon.requestReqNaviSwitchFullView(new ReqReqNaviSwitchFullView.Arguments(Boolean.valueOf(z))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda82
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m129xe4042938((ResReqNaviSwitchFullView.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda83
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviSwitchFullView$13((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviSwitchFullView$12$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m129xe4042938(ResReqNaviSwitchFullView.Arguments arguments) {
        Log.d(TAG, "reqNaviSwitchFullView: thenAccept");
        try {
            int returnCode = getReturnCode(arguments.getNaviCode().getValue().intValue());
            this.mNaviStateCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviStateCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviStateCallback) this.mNaviStateCallbackList.getBroadcastItem(i)).onMapOverViewChange(returnCode);
            }
            this.mNaviStateCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqNaviSwitchFullView: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviSwitchFullView$13(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getMapAppStatus() {
        Log.d(TAG, "getMapAppStatus");
        if (!this.isHasPermission) {
            return -4;
        }
        if (this.requestsCommon == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestGetMapAppStatus().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda69
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getMapAppStatus$14(atomicInteger, countDownLatch, (ResGetMapAppStatus) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda80
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getMapAppStatus$15((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getMapAppStatus");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getMapAppStatus$14(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetMapAppStatus resGetMapAppStatus) {
        atomicInteger.set(resGetMapAppStatus.getArguments().getMapAppStatus() == NavAPPActivityStatus.NAV_APP_FOREGROUND ? 1 : 2);
        Log.d(TAG, "getMapAppStatus: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getMapAppStatus$15(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getNaviMapMode() {
        Log.d(TAG, "getNaviMapMode");
        if (this.requestsCommon == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestGetNaviMapViewMode().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda84
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getNaviMapMode$16(atomicInteger, countDownLatch, (ResGetNaviMapViewMode) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda85
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getNaviMapMode$17((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getNaviMapMode");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getNaviMapMode$16(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetNaviMapViewMode resGetNaviMapViewMode) {
        atomicInteger.set(resGetNaviMapViewMode.getArguments().getViewMode().getValue().intValue());
        Log.d(TAG, "getNaviMapMode: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getNaviMapMode$17(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public boolean getNaviSwitchFullView() {
        Log.d(TAG, "getNaviSwitchFullView");
        if (this.requestsCommon == null) {
            return false;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestGetNaviSwitchFullView().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda91
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getNaviSwitchFullView$18(atomicBoolean, countDownLatch, (ResGetNaviSwitchFullView) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda102
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getNaviSwitchFullView$19((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getNaviSwitchFullView");
        return atomicBoolean.get();
    }

    static /* synthetic */ void lambda$getNaviSwitchFullView$18(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, ResGetNaviSwitchFullView resGetNaviSwitchFullView) {
        atomicBoolean.set(resGetNaviSwitchFullView.getArguments().getStatus().booleanValue());
        Log.d(TAG, "getNaviSwitchFullView: " + atomicBoolean.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getNaviSwitchFullView$19(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSwitchMapSetting(int i) {
        SwitchStatus switchStatus;
        Log.d(TAG, "reqSwitchMapSetting: " + i);
        if (this.requestsCommon == null) {
            return 0;
        }
        if (i == 1) {
            reqNaviLaunchMap();
        }
        if (i == 0) {
            switchStatus = SwitchStatus.SWITCH_OFF;
        } else if (i == 1) {
            switchStatus = SwitchStatus.SWITCH_ON;
        } else {
            switchStatus = SwitchStatus.UNKNOWN;
        }
        this.requestsCommon.fireSwitchMapSetting(new ReqSwitchMapSetting(switchStatus));
        return 1;
    }

    public int getMapSettingStatus() {
        Log.d(TAG, "getMapSettingStatus");
        if (this.requestsCommon == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestGetMapSettingStatus().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda71
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getMapSettingStatus$20(atomicInteger, countDownLatch, (ResGetMapSettingStatus) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda72
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getMapSettingStatus$21((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getMapSettingStatus");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getMapSettingStatus$20(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetMapSettingStatus resGetMapSettingStatus) {
        atomicInteger.set(resGetMapSettingStatus.getArguments().getMapSettingStatus().getValue().intValue());
        Log.d(TAG, "getMapSettingStatus: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getMapSettingStatus$21(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSwitchDayNightMode(int i) {
        Log.d(TAG, "reqSwitchDayNightMode: " + i);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.requestsCommon.requestReqSwitchDayNightMode(new ReqReqSwitchDayNightMode.Arguments(MapStyleType.getByValue(Integer.valueOf(i)))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda19
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m141xeb3bfffe((ResReqSwitchDayNightMode.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda20
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqSwitchDayNightMode$23((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqSwitchDayNightMode$22$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m141xeb3bfffe(ResReqSwitchDayNightMode.Arguments arguments) {
        Log.d(TAG, "reqSwitchDayNightMode: thenAccept");
        try {
            int returnCode = getReturnCode(arguments.getNaviCode().getValue().intValue());
            this.mNaviStateCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviStateCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviStateCallback) this.mNaviStateCallbackList.getBroadcastItem(i)).onDayNightModeChange(returnCode);
            }
            this.mNaviStateCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqSwitchDayNightMode: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqSwitchDayNightMode$23(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getDayNightMode() {
        Log.d(TAG, "getDayNightMode");
        if (this.requestsCommon == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestGetDayNightMode().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda93
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getDayNightMode$24(atomicInteger, countDownLatch, (ResGetDayNightMode) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda94
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getDayNightMode$25((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getDayNightMode");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getDayNightMode$24(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetDayNightMode resGetDayNightMode) {
        atomicInteger.set(resGetDayNightMode.getArguments().getMapStyleType().getValue().intValue());
        Log.d(TAG, "getDayNightMode: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getDayNightMode$25(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getTrafficStatus() {
        Log.d(TAG, "getTrifficStatus");
        if (this.requestsCommon == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestGetTrafficInfoState().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda54
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getTrafficStatus$26(atomicInteger, countDownLatch, (ResGetTrafficInfoState) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda55
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getTrafficStatus$27((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getTrifficStatus");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getTrafficStatus$26(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetTrafficInfoState resGetTrafficInfoState) {
        atomicInteger.set(resGetTrafficInfoState.getArguments().getTrafficMode() == TrafficMode.TRAFFIC_ON ? 1 : 0);
        Log.d(TAG, "getTrifficStatus: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getTrafficStatus$27(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviGuideStart(String str) {
        Log.d(TAG, "reqNaviGuideStart: " + str);
        if (this.requestsCommon == null) {
            return 0;
        }
        reqNaviLaunchMap();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestReqGuideStart(new ReqReqGuideStart(str)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda95
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m122lambda$reqNaviGuideStart$28$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResReqGuideStart) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda96
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviGuideStart$29((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqNaviGuideStart");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqNaviGuideStart$28$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m122lambda$reqNaviGuideStart$28$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResReqGuideStart resReqGuideStart) {
        atomicInteger.set(getReturnCode(resReqGuideStart.getArguments().getNaviCode().getValue().intValue()));
        Log.d(TAG, "reqNaviGuideStart: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$reqNaviGuideStart$29(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviGuideStop() {
        Log.d(TAG, "reqNaviGuideStop");
        if (this.requestsCommon == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestReqNaviGuideStop().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda73
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m123lambda$reqNaviGuideStop$30$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResReqNaviGuideStop) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda74
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviGuideStop$31((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqNaviGuideStop");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqNaviGuideStop$30$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m123lambda$reqNaviGuideStop$30$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResReqNaviGuideStop resReqNaviGuideStop) {
        atomicInteger.set(getReturnCode(resReqNaviGuideStop.getArguments().getNaviCode().getValue().intValue()));
        Log.d(TAG, "reqNaviGuideStop: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$reqNaviGuideStop$31(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public boolean checkNaviGuide() {
        Log.d(TAG, "checkNaviGuide");
        if (this.requestsCommon == null) {
            return false;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.requestsCommon.requestCheckNaviGuide().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda52
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$checkNaviGuide$32(atomicBoolean, countDownLatch, (ResCheckNaviGuide) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda53
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$checkNaviGuide$33((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "checkNaviGuide");
        return atomicBoolean.get();
    }

    static /* synthetic */ void lambda$checkNaviGuide$32(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, ResCheckNaviGuide resCheckNaviGuide) {
        atomicBoolean.set(resCheckNaviGuide.getArguments().getStatus().booleanValue());
        Log.d(TAG, "checkNaviGuide: " + atomicBoolean.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$checkNaviGuide$33(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public Place getHome() {
        Log.d(TAG, "getHome");
        if (this.bydNavApiRequests == null) {
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference(new Place());
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetHome().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda56
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m109lambda$getHome$34$comneusoftnabydsdkNavigationApi(atomicReference, countDownLatch, (ResGetHome) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda57
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getHome$35((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getHome");
        return (Place) atomicReference.get();
    }

    /* JADX INFO: renamed from: lambda$getHome$34$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m109lambda$getHome$34$comneusoftnabydsdkNavigationApi(AtomicReference atomicReference, CountDownLatch countDownLatch, ResGetHome resGetHome) {
        atomicReference.set(getPlace(resGetHome.getArguments().getPlaceInfo()));
        Log.d(TAG, "getHome: done");
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getHome$35(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public Place getCompany() {
        Log.d(TAG, "getCompany");
        if (this.bydNavApiRequests == null) {
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference(new Place());
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetCompany().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda101
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m108lambda$getCompany$36$comneusoftnabydsdkNavigationApi(atomicReference, countDownLatch, (ResGetCompany) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda1
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getCompany$37((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getCompany");
        return (Place) atomicReference.get();
    }

    /* JADX INFO: renamed from: lambda$getCompany$36$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m108lambda$getCompany$36$comneusoftnabydsdkNavigationApi(AtomicReference atomicReference, CountDownLatch countDownLatch, ResGetCompany resGetCompany) {
        atomicReference.set(getPlace(resGetCompany.getArguments().getPlaceInfo()));
        Log.d(TAG, "getCompany done");
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getCompany$37(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    private synchronized Place getPlace(PlaceInfo placeInfo) {
        Place place;
        place = new Place();
        if (placeInfo != null) {
            place.setType(placeInfo.getType().intValue());
            place.setId(placeInfo.getUid());
            place.setAddress(placeInfo.getAddress());
            place.setName(placeInfo.getName());
            place.setLatitude(placeInfo.getLatitude().doubleValue());
            place.setLongitude(placeInfo.getLongitude().doubleValue());
            place.setDistance(placeInfo.getDistance().intValue());
            place.setCountry("");
            place.setState("");
            place.setCity("");
            place.setStreet(new String[0]);
            place.setHouse_number("");
            List<String> categoryId = placeInfo.getCategoryId();
            if (categoryId != null) {
                place.setCategoryId((String[]) categoryId.toArray(new String[0]));
            }
        }
        return place;
    }

    private synchronized PlaceInfo getPlaceInfo(Place place) {
        PlaceInfo placeInfo;
        placeInfo = new PlaceInfo();
        if (place != null) {
            placeInfo.setType(Integer.valueOf(place.getType()));
            placeInfo.setUid(place.getId());
            placeInfo.setAddress(place.getAddress());
            placeInfo.setName(place.getName());
            placeInfo.setLatitude(Double.valueOf(place.getLatitude()));
            placeInfo.setLongitude(Double.valueOf(place.getLongitude()));
            placeInfo.setDistance(Integer.valueOf((int) place.getDistance()));
            String[] categoryId = place.getCategoryId();
            if (categoryId != null && categoryId.length > 0) {
                ArrayList arrayList = new ArrayList();
                Collections.addAll(arrayList, categoryId);
                placeInfo.setCategoryId(arrayList);
            }
        } else {
            Log.w(TAG, "getPlaceInfo: place==null");
        }
        return placeInfo;
    }

    private synchronized RouteInfo getRouteInfo(api.structs.com.neusoft.byd.RouteInfo routeInfo) {
        RouteInfo routeInfo2;
        routeInfo2 = new RouteInfo();
        if (routeInfo != null) {
            routeInfo2.setId(routeInfo.getId());
            routeInfo2.setLabel(routeInfo.getLabel());
            routeInfo2.setRouteOption(routeInfo.getRouteOption().getValue().intValue());
            routeInfo2.setTime(routeInfo.getTime().intValue());
            routeInfo2.setDistance(routeInfo.getDistance().doubleValue());
        }
        return routeInfo2;
    }

    public Place getCurrentPlace() {
        Log.d(TAG, "getCurrentPlace");
        if (this.bydNavApiRequests == null) {
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference(new Place());
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetCurrentPlace().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda8
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getCurrentPlace$38(atomicReference, countDownLatch, (ResGetCurrentPlace) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda9
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getCurrentPlace$39((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getCurrentPlace");
        return (Place) atomicReference.get();
    }

    static /* synthetic */ void lambda$getCurrentPlace$38(AtomicReference atomicReference, CountDownLatch countDownLatch, ResGetCurrentPlace resGetCurrentPlace) {
        LocationInfo locationInfo = resGetCurrentPlace.getArguments().getLocationInfo();
        Place place = new Place();
        if (locationInfo != null) {
            place.setType(locationInfo.getType().getValue().intValue());
            place.setId(locationInfo.getId());
            place.setAddress(locationInfo.getAddress());
            place.setName(locationInfo.getName());
            GeoPosition geoPosition = locationInfo.getGeoPosition();
            if (geoPosition != null) {
                place.setLatitude(geoPosition.getLatitude().doubleValue());
                place.setLongitude(geoPosition.getLongitude().doubleValue());
            }
            place.setDistance(locationInfo.getDistance().doubleValue());
            place.setCountry(locationInfo.getCountry());
            place.setState("");
            place.setCity(locationInfo.getCity());
            place.setStreet(new String[0]);
            place.setHouse_number(locationInfo.getHouse_number());
            List<String> categoryId = locationInfo.getCategoryId();
            if (categoryId != null) {
                place.setCategoryId((String[]) categoryId.toArray(new String[0]));
            }
        }
        atomicReference.set(place);
        Log.d(TAG, "getCurrentPlace done");
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getCurrentPlace$39(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSetAddress(final int i, final Place place) {
        AddressType addressType;
        Log.d(TAG, "reqSetAddress: " + i);
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        PlaceInfo placeInfo = getPlaceInfo(place);
        if (i == 1) {
            addressType = AddressType.HOME;
        } else if (i == 2) {
            addressType = AddressType.COMPANY;
        } else if (i == 3) {
            addressType = AddressType.ADDRESS;
        } else {
            addressType = AddressType.UNKNOWN;
        }
        this.bydNavApiRequests.requestReqSetAddress(new ReqReqSetAddress.Arguments(placeInfo, addressType)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda28
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m138lambda$reqSetAddress$40$comneusoftnabydsdkNavigationApi(i, place, (ResReqSetAddress.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda29
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqSetAddress$41((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqSetAddress$40$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m138lambda$reqSetAddress$40$comneusoftnabydsdkNavigationApi(int i, Place place, ResReqSetAddress.Arguments arguments) {
        Log.d(TAG, "reqSetAddress: thenAccept");
        try {
            int returnCode = getReturnCode(arguments.getNaviCode().getValue().intValue());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i2 = 0; i2 < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i2++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i2)).onSetAddress(returnCode, i, place);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqSetAddress: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqSetAddress$41(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getNaviMaxViaCount() {
        Log.d(TAG, "getNaviMaxViaCount");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetNaviMaxViaCount().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda0
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getNaviMaxViaCount$42(atomicInteger, countDownLatch, (ResGetNaviMaxViaCount) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda14
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getNaviMaxViaCount$43((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getNaviMaxViaCount");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getNaviMaxViaCount$42(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetNaviMaxViaCount resGetNaviMaxViaCount) {
        atomicInteger.set(resGetNaviMaxViaCount.getArguments().getMaxViaCount().intValue());
        Log.d(TAG, "getNaviMaxViaCount: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getNaviMaxViaCount$43(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public List<Place> reqNaviViaPosition() {
        Log.d(TAG, "reqNaviViaPosition");
        if (this.bydNavApiRequests == null) {
            return null;
        }
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestReqNaviViaPosition().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda12
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m130xb4166ffa(arrayList, countDownLatch, (ResReqNaviViaPosition) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda13
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviViaPosition$45((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqNaviViaPosition");
        return arrayList;
    }

    /* JADX INFO: renamed from: lambda$reqNaviViaPosition$44$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m130xb4166ffa(List list, CountDownLatch countDownLatch, ResReqNaviViaPosition resReqNaviViaPosition) {
        list.addAll(getPlaceList(resReqNaviViaPosition.getArguments().getPlaceInfoList()));
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$reqNaviViaPosition$45(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSwitchFavorite(int i) {
        FavoriteStatus favoriteStatus;
        Log.d(TAG, "reqSwitchFavorite: " + i);
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        if (i == 1) {
            reqNaviLaunchMap();
        }
        if (i == 0) {
            favoriteStatus = FavoriteStatus.CLOSE;
        } else if (i == 1) {
            favoriteStatus = FavoriteStatus.OPEN;
        } else {
            favoriteStatus = FavoriteStatus.UNKNOWN;
        }
        this.bydNavApiRequests.requestReqSwitchFavorite(new ReqReqSwitchFavorite.Arguments(favoriteStatus)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda90
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m142lambda$reqSwitchFavorite$46$comneusoftnabydsdkNavigationApi((ResReqSwitchFavorite.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda92
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqSwitchFavorite$47((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqSwitchFavorite$46$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m142lambda$reqSwitchFavorite$46$comneusoftnabydsdkNavigationApi(ResReqSwitchFavorite.Arguments arguments) {
        Log.d(TAG, "reqSwitchFavorite: thenAccept");
        try {
            int returnCode = getReturnCode(arguments.getNaviCode().getValue().intValue());
            this.mNaviStateCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviStateCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviStateCallback) this.mNaviStateCallbackList.getBroadcastItem(i)).onFavoriteStatusUpdate(returnCode);
            }
            this.mNaviStateCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqSwitchFavorite: done");
    }

    static /* synthetic */ Void lambda$reqSwitchFavorite$47(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getFavoriteStatus() {
        Log.d(TAG, "getFavoriteStatus");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetFavoriteStatus().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda68
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getFavoriteStatus$48(atomicInteger, countDownLatch, (ResGetFavoriteStatus) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda70
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getFavoriteStatus$49((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getFavoriteStatus");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getFavoriteStatus$48(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetFavoriteStatus resGetFavoriteStatus) {
        atomicInteger.set(resGetFavoriteStatus.getArguments().getFavoriteStatus().getValue().intValue());
        Log.d(TAG, "getFavoriteStatus: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getFavoriteStatus$49(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviAddFavorite() {
        Log.d(TAG, "reqNaviAddFavorite");
        BydNavApiRequests bydNavApiRequests = this.bydNavApiRequests;
        if (bydNavApiRequests == null) {
            return 0;
        }
        bydNavApiRequests.requestReqNaviAddFavorite().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda32
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m118x7ec294c5((ResReqNaviAddFavorite) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda33
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviAddFavorite$51((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviAddFavorite$50$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m118x7ec294c5(ResReqNaviAddFavorite resReqNaviAddFavorite) {
        Log.d(TAG, "reqNaviAddFavorite: thenAccept");
        try {
            int returnCode = getReturnCode(resReqNaviAddFavorite.getArguments().getNaviCode().getValue().intValue());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onReqNaviAddFavorite(returnCode, null);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqNaviAddFavorite: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviAddFavorite$51(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public boolean checkFavoriteEmpty() {
        Log.d(TAG, "checkFavoriteEmpty");
        if (this.bydNavApiRequests == null) {
            return true;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestCheckFavoriteEmpty().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda21
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$checkFavoriteEmpty$52(atomicBoolean, countDownLatch, (ResCheckFavoriteEmpty) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda22
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$checkFavoriteEmpty$53((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getFavoriteStatus");
        return atomicBoolean.get();
    }

    static /* synthetic */ void lambda$checkFavoriteEmpty$52(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, ResCheckFavoriteEmpty resCheckFavoriteEmpty) {
        atomicBoolean.set(resCheckFavoriteEmpty.getArguments().getIsFavoriteEmpty().booleanValue());
        Log.d(TAG, "checkFavoriteEmpty: " + atomicBoolean.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$checkFavoriteEmpty$53(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public boolean checkFavoriteFull() {
        Log.d(TAG, "checkFavoriteFull");
        if (this.bydNavApiRequests == null) {
            return true;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestCheckFavoriteFull().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda97
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$checkFavoriteFull$54(atomicBoolean, countDownLatch, (ResCheckFavoriteFull) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda98
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$checkFavoriteFull$55((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "checkFavoriteFull");
        return atomicBoolean.get();
    }

    static /* synthetic */ void lambda$checkFavoriteFull$54(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, ResCheckFavoriteFull resCheckFavoriteFull) {
        atomicBoolean.set(resCheckFavoriteFull.getArguments().getIsFavoriteFull().booleanValue());
        Log.d(TAG, "checkFavoriteFull: " + atomicBoolean.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$checkFavoriteFull$55(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviRemainTimeDistance() {
        Log.d(TAG, "reqNaviRemainTimeDistance");
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommon;
        if (commonSysApiInverseRequests == null) {
            return 0;
        }
        commonSysApiInverseRequests.requestReqNaviRemainTimeDistance().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda48
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m126xc8e4107e((ResReqNaviRemainTimeDistance) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda49
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviRemainTimeDistance$57((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviRemainTimeDistance$56$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m126xc8e4107e(ResReqNaviRemainTimeDistance resReqNaviRemainTimeDistance) {
        try {
            RemainTimeDist remainTimeDist = resReqNaviRemainTimeDistance.getArguments().getRemainTimeDist();
            int returnCode = getReturnCode(remainTimeDist.getCode().getValue().intValue());
            int iIntValue = remainTimeDist.getRemainTime().intValue();
            int iIntValue2 = remainTimeDist.getRemainDistance().intValue();
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onNaviRemainTimeDistance(returnCode, iIntValue, iIntValue2);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ Void lambda$reqNaviRemainTimeDistance$57(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public List<String> getSupportedRouteOptions() {
        Log.d(TAG, "getSupportedRouteOptions");
        final ArrayList arrayList = new ArrayList();
        if (this.bydNavApiRequests == null) {
            return arrayList;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetSupportedRouteOptions().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda15
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m110xf4394d1(arrayList, countDownLatch, (ResGetSupportedRouteOptions) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda16
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getSupportedRouteOptions$59((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getSupportedRouteOptions");
        return arrayList;
    }

    /* JADX INFO: renamed from: lambda$getSupportedRouteOptions$58$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m110xf4394d1(List list, CountDownLatch countDownLatch, ResGetSupportedRouteOptions resGetSupportedRouteOptions) {
        Log.d(TAG, "getSupportedRouteOptions: thenAccept");
        try {
            List<StrategyType> strategyTypeList = resGetSupportedRouteOptions.getArguments().getStrategyTypeList();
            Iterator<StrategyType> it = strategyTypeList.iterator();
            while (it.hasNext()) {
                list.add(String.valueOf(getRouteOption(it.next())));
            }
            Log.i(TAG, "getSupportedRouteOptions: " + strategyTypeList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$getSupportedRouteOptions$59(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviSetRouteOption(int i) {
        Log.d(TAG, "reqNaviSetRouteOption: " + i);
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.bydNavApiRequests.requestReqNaviSetRouteOption(new ReqReqNaviSetRouteOption(getStrategyType(i))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda26
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m128x10ce2d73((ResReqNaviSetRouteOption) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda27
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviSetRouteOption$61((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviSetRouteOption$60$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m128x10ce2d73(ResReqNaviSetRouteOption resReqNaviSetRouteOption) {
        Log.d(TAG, "reqNaviSetRouteOption: thenAccept");
        try {
            int returnCode = getReturnCode(resReqNaviSetRouteOption.getArguments().getNaviCode().getValue().intValue());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onReqNaviSetRouteOption(returnCode);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ Void lambda$reqNaviSetRouteOption$61(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviInfo() {
        Log.d(TAG, "reqNaviInfo");
        BydNavApiRequests bydNavApiRequests = this.bydNavApiRequests;
        if (bydNavApiRequests == null) {
            return 0;
        }
        bydNavApiRequests.requestReqNaviInfo().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda61
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m124lambda$reqNaviInfo$62$comneusoftnabydsdkNavigationApi((ResReqNaviInfo) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda62
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviInfo$63((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviInfo$62$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m124lambda$reqNaviInfo$62$comneusoftnabydsdkNavigationApi(ResReqNaviInfo resReqNaviInfo) {
        Log.d(TAG, "reqNaviInfo: thenAccept");
        try {
            int returnCode = getReturnCode(resReqNaviInfo.getArguments().getNaviCode().getValue().intValue());
            int routeOption = getRouteOption(resReqNaviInfo.getArguments().getStrategyType());
            PlaceInfo startPosition = resReqNaviInfo.getArguments().getStartPosition();
            PlaceInfo destinationPosition = resReqNaviInfo.getArguments().getDestinationPosition();
            List<PlaceInfo> viaPositions = resReqNaviInfo.getArguments().getViaPositions();
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onReqNaviInfo(returnCode, routeOption, getPlace(startPosition), getPlace(destinationPosition), getPlaceList(viaPositions));
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ Void lambda$reqNaviInfo$63(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int addViaPosition(List<Place> list) throws RemoteException {
        Log.d(TAG, "addViaPosition: " + list.size());
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        if (list == null || list.size() < 1) {
            return -1;
        }
        this.bydNavApiRequests.requestReqAddViaPosition(new ReqReqAddViaPosition(getPlaceInfo(list.get(0)))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda4
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m107lambda$addViaPosition$64$comneusoftnabydsdkNavigationApi((ResReqAddViaPosition) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda5
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$addViaPosition$65((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$addViaPosition$64$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m107lambda$addViaPosition$64$comneusoftnabydsdkNavigationApi(ResReqAddViaPosition resReqAddViaPosition) {
        try {
            int returnCode = getReturnCode(resReqAddViaPosition.getArguments().getNaviCode().getValue().intValue());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onNaviAddViaPosition(returnCode);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ Void lambda$addViaPosition$65(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    private StrategyType getStrategyType(int i) {
        if (i == 1) {
            return StrategyType.TIME_PRIORITY;
        }
        if (i == 3) {
            return StrategyType.AVOID_HIGHWAY;
        }
        if (i == 10) {
            return StrategyType.AVOID_CONGESTION;
        }
        switch (i) {
            case 12:
                return StrategyType.RECOMMENDATION;
            case 13:
                return StrategyType.HIGHWAY_PRIORITY;
            case 14:
                return StrategyType.DISTANCE_PRIORITY;
            default:
                return StrategyType.INVALID;
        }
    }

    private int getRouteOption(StrategyType strategyType) {
        if (strategyType == StrategyType.TIME_PRIORITY) {
            return 1;
        }
        if (strategyType == StrategyType.AVOID_HIGHWAY) {
            return 3;
        }
        if (strategyType == StrategyType.AVOID_CONGESTION) {
            return 10;
        }
        if (strategyType == StrategyType.RECOMMENDATION) {
            return 12;
        }
        if (strategyType == StrategyType.HIGHWAY_PRIORITY) {
            return 13;
        }
        return strategyType == StrategyType.DISTANCE_PRIORITY ? 14 : 0;
    }

    public int reqRoutePlanning(Place place, List<Place> list, int i) {
        Log.d(TAG, "reqRoutePlanning: " + i + ListUtils.DEFAULT_JOIN_SEPARATOR + list.size());
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.bydNavApiRequests.requestReqRoutePlanning(new ReqReqRoutePlanning(getPlaceInfo(place), getPlaceInfoList(list), getStrategyType(i))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda30
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m137lambda$reqRoutePlanning$66$comneusoftnabydsdkNavigationApi((ResReqRoutePlanning) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda31
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqRoutePlanning$67((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqRoutePlanning$66$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m137lambda$reqRoutePlanning$66$comneusoftnabydsdkNavigationApi(ResReqRoutePlanning resReqRoutePlanning) {
        Log.d(TAG, "reqRoutePlanning: thenAccept");
        try {
            int returnCode = getReturnCode(resReqRoutePlanning.getArguments().getNaviCode().getValue().intValue());
            List<api.structs.com.neusoft.byd.RouteInfo> routeInfoList = resReqRoutePlanning.getArguments().getRouteInfoList();
            ArrayList arrayList = new ArrayList();
            for (api.structs.com.neusoft.byd.RouteInfo routeInfo : routeInfoList) {
                RouteInfo routeInfo2 = new RouteInfo();
                routeInfo2.setId(routeInfo.getId());
                routeInfo2.setTime(routeInfo.getTime().intValue());
                routeInfo2.setLabel(routeInfo.getLabel());
                routeInfo2.setRouteOption(routeInfo.getRouteOption().getValue().intValue());
                routeInfo2.setDistance(routeInfo.getDistance().doubleValue());
                arrayList.add(routeInfo2);
            }
            Log.i(TAG, "reqRoutePlanning: " + arrayList.size());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onRoutePlanning(returnCode, arrayList);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ Void lambda$reqRoutePlanning$67(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviRouteFullView(List<RouteInfo> list, int i) {
        Log.d(TAG, "reqNaviRouteFullView: " + i + ListUtils.DEFAULT_JOIN_SEPARATOR + list.size());
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        reqNaviLaunchMap();
        ArrayList arrayList = new ArrayList();
        for (RouteInfo routeInfo : list) {
            api.structs.com.neusoft.byd.RouteInfo routeInfo2 = new api.structs.com.neusoft.byd.RouteInfo();
            routeInfo2.setId(routeInfo.getId());
            routeInfo2.setTime(Integer.valueOf((int) routeInfo.getTime()));
            routeInfo2.setLabel(routeInfo.getLabel());
            routeInfo2.setRouteOption(getStrategyType(routeInfo.getRouteOption()));
            routeInfo2.setDistance(Double.valueOf(routeInfo.getDistance()));
            arrayList.add(routeInfo2);
        }
        this.bydNavApiRequests.requestReqNaviRouteFullView(new ReqReqNaviRouteFullView(arrayList, Integer.valueOf(i))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda10
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m127x82ec709e((ResReqNaviRouteFullView) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda11
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviRouteFullView$69((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviRouteFullView$68$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m127x82ec709e(ResReqNaviRouteFullView resReqNaviRouteFullView) {
        Log.d(TAG, "reqNaviRouteFullView: accept");
        try {
            int returnCode = getReturnCode(resReqNaviRouteFullView.getArguments().getNaviCode().getValue().intValue());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onRouteFullView(returnCode);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ Void lambda$reqNaviRouteFullView$69(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqPOISearchByKeyword(String str, final INaviSearchPOICallback iNaviSearchPOICallback) {
        Log.d(TAG, "reqPOISearchByKeyword: " + str);
        BydNavApiRequests bydNavApiRequests = this.bydNavApiRequests;
        if (bydNavApiRequests == null) {
            return 0;
        }
        bydNavApiRequests.requestReqPOISearchByKeyword(new ReqReqPOISearchByKeyword.Arguments(str)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda77
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m136x9bad8832(iNaviSearchPOICallback, (ResReqPOISearchByKeyword.Arguments) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda78
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqPOISearchByKeyword$71((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqPOISearchByKeyword$70$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m136x9bad8832(INaviSearchPOICallback iNaviSearchPOICallback, ResReqPOISearchByKeyword.Arguments arguments) {
        Log.d(TAG, "reqPOISearchByKeyword: thenAccept");
        try {
            int returnCode = getReturnCode(arguments.getNaviCode().getValue().intValue());
            List<Place> placeList = getPlaceList(arguments.getPlaceInfoList());
            if (iNaviSearchPOICallback != null) {
                iNaviSearchPOICallback.onResult(returnCode, placeList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqPOISearchByKeyword: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqPOISearchByKeyword$71(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    private List<Place> getPlaceList(List<PlaceInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<PlaceInfo> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(getPlace(it.next()));
            }
        }
        Log.i(TAG, "getPlaceList size:" + arrayList.size());
        return arrayList;
    }

    private List<PlaceInfo> getPlaceInfoList(List<Place> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<Place> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(getPlaceInfo(it.next()));
            }
        }
        Log.i(TAG, "getPlaceInfoList: " + arrayList.size());
        return arrayList;
    }

    public int reqPOISearchByCategories(int i, int i2, String str, final INaviSearchPOICallback iNaviSearchPOICallback) {
        LocationType locationType;
        Log.d(TAG, "reqPOISearchByCategories: " + i);
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        if (i2 == 2) {
            locationType = LocationType.Destination;
        } else if (i2 == 3) {
            locationType = LocationType.SpecifyLocation;
        } else {
            locationType = LocationType.CurrentLocation;
        }
        this.bydNavApiRequests.requestReqPOISearchByCategories(new ReqReqPOISearchByCategories(Integer.valueOf(i), locationType, str)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda59
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m135x5ab9f4f3(iNaviSearchPOICallback, (ResReqPOISearchByCategories) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda60
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqPOISearchByCategories$73((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqPOISearchByCategories$72$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m135x5ab9f4f3(INaviSearchPOICallback iNaviSearchPOICallback, ResReqPOISearchByCategories resReqPOISearchByCategories) {
        Log.d(TAG, "reqPOISearchByCategories: thenAccept");
        try {
            int returnCode = getReturnCode(resReqPOISearchByCategories.getArguments().getNaviCode().getValue().intValue());
            List<Place> placeList = getPlaceList(resReqPOISearchByCategories.getArguments().getPlaceInfoList());
            if (iNaviSearchPOICallback != null) {
                iNaviSearchPOICallback.onResult(returnCode, placeList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqPOISearchByCategories: done");
    }

    static /* synthetic */ Void lambda$reqPOISearchByCategories$73(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviAlongTheWaySearch(int i, final INaviSearchAlongCallback iNaviSearchAlongCallback) {
        Log.d(TAG, "reqNaviAlongTheWaySearch: " + i);
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.requestReqNaviAlongTheWaySearch(new ReqReqNaviAlongTheWaySearch(Integer.valueOf(i))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda45
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m120x357e1ea9(iNaviSearchAlongCallback, (ResReqNaviAlongTheWaySearch) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda46
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviAlongTheWaySearch$75((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviAlongTheWaySearch$74$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m120x357e1ea9(INaviSearchAlongCallback iNaviSearchAlongCallback, ResReqNaviAlongTheWaySearch resReqNaviAlongTheWaySearch) {
        Log.d(TAG, "reqNaviAlongTheWaySearch: thenAccept");
        try {
            int returnCode = getReturnCode(resReqNaviAlongTheWaySearch.getArguments().getNaviCode().getValue().intValue());
            List<Place> placeList = getPlaceList(resReqNaviAlongTheWaySearch.getArguments().getPlaceInfoList());
            if (iNaviSearchAlongCallback != null) {
                iNaviSearchAlongCallback.onResult(returnCode, placeList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqNaviAlongTheWaySearch: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviAlongTheWaySearch$75(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public boolean checkHistoryDestinationEmpty() {
        Log.d(TAG, "checkHistoryDestinationEmpty");
        if (this.bydNavApiRequests == null) {
            return true;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestCheckDestinationHistoryEmpty().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda75
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$checkHistoryDestinationEmpty$76(atomicBoolean, countDownLatch, (ResCheckDestinationHistoryEmpty) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda76
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$checkHistoryDestinationEmpty$77((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "checkHistoryDestinationEmpty");
        return atomicBoolean.get();
    }

    static /* synthetic */ void lambda$checkHistoryDestinationEmpty$76(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, ResCheckDestinationHistoryEmpty resCheckDestinationHistoryEmpty) {
        Log.d(TAG, "checkHistoryDestinationEmpty thenAccept");
        atomicBoolean.set(resCheckDestinationHistoryEmpty.getArguments().getIsDestinationHistoryEmpty().booleanValue());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$checkHistoryDestinationEmpty$77(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqOpenRouteOption() {
        Log.d(TAG, "reqOpenRouteOption");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.bydNavApiRequests.fireReqOpenRouteOption();
        return 1;
    }

    public int reqNaviGetRouteOption() {
        Log.d(TAG, "reqNaviGetRouteOption");
        BydNavApiRequests bydNavApiRequests = this.bydNavApiRequests;
        if (bydNavApiRequests == null) {
            return 0;
        }
        bydNavApiRequests.requestReqNaviGetRouteOption().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda23
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m121xab1a1a38((ResReqNaviGetRouteOption) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda24
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviGetRouteOption$79((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviGetRouteOption$78$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m121xab1a1a38(ResReqNaviGetRouteOption resReqNaviGetRouteOption) {
        Log.d(TAG, "reqNaviGetRouteOption: thenAccept");
        try {
            int returnCode = getReturnCode(resReqNaviGetRouteOption.getArguments().getNaviCode().getValue().intValue());
            StrategyType strategyType = resReqNaviGetRouteOption.getArguments().getStrategyType();
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onReqNaviGetRouteOption(returnCode, getRouteOption(strategyType));
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqNaviGetRouteOption: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviGetRouteOption$79(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqOpenSetAddress(int i) {
        Log.i(TAG, "reqOpenSetAddress");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestRequestShowHomeOrCompanySetPage(new ReqRequestShowHomeOrCompanySetPage(FavoriteType.getByValue(Integer.valueOf(i)))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda88
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m134lambda$reqOpenSetAddress$80$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResRequestShowHomeOrCompanySetPage) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda89
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqOpenSetAddress$81((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "reqOpenSetAddress");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$reqOpenSetAddress$80$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m134lambda$reqOpenSetAddress$80$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResRequestShowHomeOrCompanySetPage resRequestShowHomeOrCompanySetPage) {
        atomicInteger.set(getReturnCode(resRequestShowHomeOrCompanySetPage.getArguments().getResultCode().intValue()));
        Log.d(TAG, "reqOpenSetAddress: " + atomicInteger.get());
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$reqOpenSetAddress$81(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviAddFavoritePlace(Place place) {
        Log.i(TAG, "reqNaviAddFavoritePlace");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.requestReqSetAddress(new ReqReqSetAddress(getPlaceInfo(place), AddressType.ADDRESS)).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda86
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m119x7ff270c1((ResReqSetAddress) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda87
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqNaviAddFavoritePlace$83((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqNaviAddFavoritePlace$82$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m119x7ff270c1(ResReqSetAddress resReqSetAddress) {
        try {
            int returnCode = getReturnCode(resReqSetAddress.getArguments().getNaviCode().getValue().intValue());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onReqNaviAddFavorite(returnCode, null);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqNaviAddFavoritePlace: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqNaviAddFavoritePlace$83(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int getUnfinishedRoute() {
        Log.i(TAG, "getUnfinishedRoute");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        reqNaviLaunchMap();
        this.bydNavApiRequests.requestGetUnfinishedRoute().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda66
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m112x5232be12((ResGetUnfinishedRoute) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda67
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getUnfinishedRoute$86((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$getUnfinishedRoute$85$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m112x5232be12(final ResGetUnfinishedRoute resGetUnfinishedRoute) {
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda63
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m111xf0e02173(resGetUnfinishedRoute);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getUnfinishedRoute$84$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m111xf0e02173(ResGetUnfinishedRoute resGetUnfinishedRoute) {
        try {
            ResGetUnfinishedRoute.Arguments arguments = resGetUnfinishedRoute.getArguments();
            int returnCode = getReturnCode(arguments.getNaviCode().getValue().intValue());
            int iIntValue = arguments.getStrategyType().getValue().intValue();
            Place place = getPlace(arguments.getPlaceInfo());
            List<Place> placeList = getPlaceList(arguments.getPlaceInfoList());
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onReqUnfinishedRoute(returnCode, iIntValue, place, placeList);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "getUnfinishedRoute: thenAccept done");
    }

    static /* synthetic */ Void lambda$getUnfinishedRoute$86(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int resumeNavigation() {
        Log.i(TAG, "resumeNavigation");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestResumeNavigation().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda43
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m144lambda$resumeNavigation$87$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResResumeNavigation) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda44
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$resumeNavigation$88((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "resumeNavigation");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$resumeNavigation$87$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m144lambda$resumeNavigation$87$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResResumeNavigation resResumeNavigation) {
        NaviCode naviCode = resResumeNavigation.getArguments().getNaviCode();
        Log.d(TAG, "resumeNavigation: " + naviCode);
        atomicInteger.set(getReturnCode(naviCode.getValue().intValue()));
        countDownLatch.countDown();
    }

    static /* synthetic */ Void lambda$resumeNavigation$88(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public boolean checkTTSPlaying() {
        Log.i(TAG, "checkTTSPlaying:" + this.mTtsPlayState);
        return this.mTtsPlayState;
    }

    public int showPlaceMark(List<Place> list) {
        Log.i(TAG, "showPlaceMark: " + list.size());
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestShowPoiIconsFromVR(new ReqShowPoiIconsFromVR(getPlaceInfoList(list))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda37
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m145lambda$showPlaceMark$89$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResShowPoiIconsFromVR) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda38
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$showPlaceMark$90((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "showPlaceMark");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$showPlaceMark$89$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m145lambda$showPlaceMark$89$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResShowPoiIconsFromVR resShowPoiIconsFromVR) {
        atomicInteger.set(getReturnCode(resShowPoiIconsFromVR.getArguments().getNaviCode().getValue().intValue()));
        countDownLatch.countDown();
        Log.d(TAG, "showPlaceMark: thenAccept " + atomicInteger.get());
    }

    static /* synthetic */ Void lambda$showPlaceMark$90(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int hidePlaceMark() {
        Log.i(TAG, "hidePlaceMark");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestHidePoiIconsFromVR().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda41
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m113lambda$hidePlaceMark$91$comneusoftnabydsdkNavigationApi(atomicInteger, countDownLatch, (ResHidePoiIconsFromVR) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda42
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$hidePlaceMark$92((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "hidePlaceMark");
        return atomicInteger.get();
    }

    /* JADX INFO: renamed from: lambda$hidePlaceMark$91$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m113lambda$hidePlaceMark$91$comneusoftnabydsdkNavigationApi(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResHidePoiIconsFromVR resHidePoiIconsFromVR) {
        atomicInteger.set(getReturnCode(resHidePoiIconsFromVR.getArguments().getNaviCode().getValue().intValue()));
        countDownLatch.countDown();
        Log.d(TAG, "hidePlaceMark: thenAccept " + atomicInteger.get());
    }

    static /* synthetic */ Void lambda$hidePlaceMark$92(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqNaviNextViaTimeDistance() throws RemoteException {
        Log.i(TAG, "reqNaviNextViaTimeDistance");
        return -2;
    }

    public int reqOpenPOIDetail(Place place) throws RemoteException {
        Log.i(TAG, "reqOpenPOIDetail");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.fireReqOpenPoiDetail(new ReqReqOpenPoiDetail(getPlaceInfo(place)));
        return 1;
    }

    public int reqClosePOIDetail() throws RemoteException {
        Log.i(TAG, "reqClosePOIDetail");
        BydNavApiRequests bydNavApiRequests = this.bydNavApiRequests;
        if (bydNavApiRequests == null) {
            return 0;
        }
        bydNavApiRequests.fireReqClosePoiDetail();
        return 1;
    }

    public int getPOIDetailStatus() throws RemoteException {
        Log.i(TAG, "getPOIDetailStatus");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.bydNavApiRequests.requestGetPoiDetailStatus().thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda47
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                NavigationApi.lambda$getPOIDetailStatus$93(atomicInteger, countDownLatch, (ResGetPoiDetailStatus) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda58
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$getPOIDetailStatus$94((Throwable) obj);
            }
        });
        setTimeOut(countDownLatch, "getPOIDetailStatus");
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$getPOIDetailStatus$93(AtomicInteger atomicInteger, CountDownLatch countDownLatch, ResGetPoiDetailStatus resGetPoiDetailStatus) {
        atomicInteger.set(resGetPoiDetailStatus.getArguments().getDetailPageStatus().getValue().intValue());
        countDownLatch.countDown();
        Log.d(TAG, "getPOIDetailStatus: thenAccept " + atomicInteger.get());
    }

    static /* synthetic */ Void lambda$getPOIDetailStatus$94(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSmartNaviStart(Place place) throws RemoteException {
        Log.i(TAG, "reqSmartNaviStart");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.requestReqSmartNaviStart(new ReqReqSmartNaviStart(getPlaceInfo(place))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda25
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m139lambda$reqSmartNaviStart$95$comneusoftnabydsdkNavigationApi((ResReqSmartNaviStart) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda36
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqSmartNaviStart$96((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqSmartNaviStart$95$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m139lambda$reqSmartNaviStart$95$comneusoftnabydsdkNavigationApi(ResReqSmartNaviStart resReqSmartNaviStart) {
        onRoutePlanningUpdate(resReqSmartNaviStart.getArguments().getNaviCode().getValue().intValue());
        Log.d(TAG, "reqSmartNaviStart: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqSmartNaviStart$96(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqSmartRoutePlanning(Place place, List<Place> list, int i) throws RemoteException {
        Log.i(TAG, "reqSmartRoutePlanning:" + list.size() + ListUtils.DEFAULT_JOIN_SEPARATOR + i);
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.requestReqSmartRoutePlanning(new ReqReqSmartRoutePlanning(getPlaceInfo(place), getPlaceInfoList(list), getStrategyType(i))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda50
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m140x65861e6a((ResReqSmartRoutePlanning) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda51
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqSmartRoutePlanning$98((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqSmartRoutePlanning$97$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m140x65861e6a(ResReqSmartRoutePlanning resReqSmartRoutePlanning) {
        try {
            int returnCode = getReturnCode(resReqSmartRoutePlanning.getArguments().getNaviCode().getValue().intValue());
            List<api.structs.com.neusoft.byd.RouteInfo> routeInfoList = resReqSmartRoutePlanning.getArguments().getRouteInfoList();
            RouteInfo routeInfo = new RouteInfo();
            if (routeInfoList != null && routeInfoList.size() > 0) {
                Log.i(TAG, "routeInfoList size > 0");
                routeInfo = getRouteInfo(routeInfoList.get(0));
            }
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i = 0; i < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i)).onSmartRoutePlanning(returnCode, routeInfo);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reqSmartRoutePlanning: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqSmartRoutePlanning$98(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqModifyDestination(Place place) throws RemoteException {
        Log.i(TAG, "reqModifyDestination");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.requestReqModifyDestination(new ReqReqModifyDestination(getPlaceInfo(place))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda34
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m117xd7764bbd((ResReqModifyDestination) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda35
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqModifyDestination$100((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqModifyDestination$99$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m117xd7764bbd(ResReqModifyDestination resReqModifyDestination) {
        onRoutePlanningUpdate(resReqModifyDestination.getArguments().getNaviCode().getValue().intValue());
        Log.d(TAG, "reqModifyDestination: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqModifyDestination$100(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    public int reqAppendVia(Place place) throws RemoteException {
        Log.i(TAG, "reqAppendVia");
        if (this.bydNavApiRequests == null) {
            return 0;
        }
        this.bydNavApiRequests.requestReqAppendVia(new ReqReqAppendVia(getPlaceInfo(place))).thenAccept(new Consumer() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda17
            @Override // java9.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m116lambda$reqAppendVia$101$comneusoftnabydsdkNavigationApi((ResReqAppendVia) obj);
            }
        }).exceptionally(new Function() { // from class: com.neusoft.na.byd.sdk.NavigationApi$$ExternalSyntheticLambda18
            @Override // java9.util.function.Function
            public final Object apply(Object obj) {
                return NavigationApi.lambda$reqAppendVia$102((Throwable) obj);
            }
        });
        return 1;
    }

    /* JADX INFO: renamed from: lambda$reqAppendVia$101$com-neusoft-na-byd-sdk-NavigationApi, reason: not valid java name */
    /* synthetic */ void m116lambda$reqAppendVia$101$comneusoftnabydsdkNavigationApi(ResReqAppendVia resReqAppendVia) {
        onRoutePlanningUpdate(resReqAppendVia.getArguments().getNaviCode().getValue().intValue());
        Log.d(TAG, "reqAppendVia: thenAccept done");
    }

    static /* synthetic */ Void lambda$reqAppendVia$102(Throwable th) {
        Log.e(TAG, "exceptionally: " + th.getMessage());
        return null;
    }

    private void onRoutePlanningUpdate(int i) {
        try {
            int returnCode = getReturnCode(i);
            this.mNaviServiceCallbackList.beginBroadcast();
            for (int i2 = 0; i2 < this.mNaviServiceCallbackList.getRegisteredCallbackCount(); i2++) {
                ((INaviServiceCallback) this.mNaviServiceCallbackList.getBroadcastItem(i2)).onRoutePlanningUpdate(returnCode);
            }
            this.mNaviServiceCallbackList.finishBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRemoteVersionCode() throws RemoteException {
        Log.i(TAG, "getRemoteVersionCode");
        return 20;
    }

    private void setTimeOut(CountDownLatch countDownLatch, String str) {
        try {
            if (countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            Log.i(TAG, str + " time out");
        } catch (InterruptedException e) {
            Log.e(TAG, str + " error");
            e.printStackTrace();
        }
    }

    private int getReturnCode(int i) {
        Log.i(TAG, "getReturnCode: " + i);
        if (i == 0) {
            return 1;
        }
        switch (i) {
            case 2:
                return -2;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return -3;
            case 10:
                return 8;
            case 11:
                return 9;
            case 12:
                return 10;
            default:
                return -1;
        }
    }

    public void shutdown() {
        Log.i(TAG, "shutdown");
        MyAppCrashHandler.setNaviAppExit(this.requestsCommon, 2);
    }
}
