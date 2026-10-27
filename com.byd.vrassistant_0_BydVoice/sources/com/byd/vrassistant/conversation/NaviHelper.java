package com.byd.vrassistant.conversation;

import android.os.RemoteException;
import android.text.TextUtils;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceByCategoryResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceByNameResult;
import cerence.ark.assistant.controller.enumeration.MileageUnitEnum;
import cerence.ark.assistant.controller.enumeration.PlaceTypeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.RouteOptionEnum;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import com.blankj.utilcode.util.SPUtils;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.naviserviceapi.INaviSearchAlongCallback;
import com.byd.naviserviceapi.INaviSearchPOICallback;
import com.byd.naviserviceapi.INaviService;
import com.byd.naviserviceapi.INaviServiceCallback;
import com.byd.naviserviceapi.INaviStateCallback;
import com.byd.naviserviceapi.Place;
import com.byd.naviserviceapi.RouteInfo;
import com.byd.naviserviceapi.RouteInfo2;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.NavUtil;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class NaviHelper {
    public static final int NAVIPROVIDER_KOR = 5;
    public static final int NAVIPROVIDER_NEUNAV = 2;
    public static final int NAVIPROVIDER_TELENAV = 1;
    public static final int NAVIPROVIDER_ZINLIN = 3;
    private static final String TAG = "NEU_NaviHelper";
    private static final int TimeOut = 4;
    private Place destinationPosition;
    private Place destinationPositionUnfinished;
    private Place mDetailPOI;
    private List<RouteInfo> mListRouteInfo;
    private List<RouteInfo2> mListRouteInfo2;
    private List<Place> mPlaceList;
    private List<String> mRouteOptions;
    private List<Place> mViaPositionsUnfinished;
    private double remainDistance;
    private double remainDistanceNext;
    private long remainTime;
    private long remainTimeNext;
    private RouteInfo routeInfo;
    private RouteInfo2 routeInfo2;
    private String routeOption;
    private String routeOptionUnfinished;
    private List<String> routeOptionUnfinishedEx;
    private INaviService mINaviService = null;
    private CountDownLatch latch = null;
    private CountDownLatch latchRoute = null;
    private boolean blSynchronous = false;
    private int onAddViaPointCode = -100;
    private int routePlanningCode = -100;
    private int routePlanningUpdateCode = -100;
    private int onReqOpenPOIDetailCode = -100;
    private int onReqNaviAddFavoriteResultCode = -100;
    private int onReqNaviInfoResultCode = -100;
    private int onNaviViaPositionResultCode = -100;
    private int onSetAddressResultCode = -100;
    private int onReqNaviSetRouteOptionCode = -100;
    private int onNaviRemainTimeDistanceCode = -100;
    private int onReqNaviGetRouteOptionResultCode = -100;
    private int onNaviNextViaTimeDistanceCode = -100;
    private int onReqUnfinishedRouteCode = -100;
    private int onRouteFullViewCode = -100;
    private INaviServiceCallback mNaviServiceCallback = new INaviServiceCallback.Stub() { // from class: com.byd.vrassistant.conversation.NaviHelper.1
        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviSupportedOptionResult(int i, List<String> list) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviSupportedOptionResult");
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSetAddress(int i, int i2, Place place) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onSetAddress");
            NaviHelper.this.onSetAddressResultCode = i;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviViaPositionResult(int i, List<Place> list) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviViaPositionResult code = " + i + ",list size=" + list.size());
            NaviHelper.this.onNaviViaPositionResultCode = i;
            NaviHelper.this.mPlaceList = list;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviAddFavorite(int i, Place place) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onReqNaviAddFavorite");
            NaviHelper.this.onReqNaviAddFavoriteResultCode = i;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviRemainTimeDistance(int i, long j, double d) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviRemainTimeDistance");
            NaviHelper.this.onNaviRemainTimeDistanceCode = i;
            NaviHelper.this.remainTime = j;
            NaviHelper.this.remainDistance = d;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviSetRouteOption(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onReqNaviSetRouteOption");
            NaviHelper.this.onReqNaviSetRouteOptionCode = i;
            if (NaviHelper.this.blSynchronous || NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviInfo(int i, int i2, Place place, Place place2, List<Place> list) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onReqNaviInfo");
            RLog.i(NaviHelper.TAG, "onReqNaviInfo: resultCode==" + i);
            RLog.i(NaviHelper.TAG, "onReqNaviInfo: resultRouteOption==" + i2);
            NaviHelper.this.onReqNaviInfoResultCode = i;
            RLog.i(NaviHelper.TAG, "onReqNaviInfo: onReqNaviInfoResultCode==" + NaviHelper.this.onReqNaviInfoResultCode);
            switch (i2) {
                case 1:
                    NaviHelper.this.routeOption = RouteOptionEnum.FASTEST;
                    break;
                case 2:
                case 11:
                default:
                    NaviHelper.this.routeOption = "UNSPECIFIED";
                    break;
                case 3:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_HIGHWAY;
                    break;
                case 4:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_TOLLS;
                    break;
                case 5:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_UNPAVED;
                    break;
                case 6:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_TUNNEL;
                    break;
                case 7:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_CARPOOL;
                    break;
                case 8:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_COUNTRY_BORDER;
                    break;
                case 9:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_FERRIES;
                    break;
                case 10:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_CONGESTION;
                    break;
                case 12:
                    NaviHelper.this.routeOption = RouteOptionEnum.RECOMMENDED;
                    break;
                case 13:
                    NaviHelper.this.routeOption = RouteOptionEnum.HIGHWAY;
                    break;
                case 14:
                    NaviHelper.this.routeOption = RouteOptionEnum.SHORTEST;
                    break;
            }
            NaviHelper.this.destinationPosition = place2;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviAddViaPosition(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviAddViaPosition");
            NaviHelper.this.onAddViaPointCode = i;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanning(int i, List<RouteInfo> list) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onRoutePlanning   " + i);
            NaviHelper.this.routePlanningCode = i;
            NaviHelper.this.mListRouteInfo = list;
            if (NaviHelper.this.latchRoute == null || NaviHelper.this.latchRoute.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latchRoute.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRouteFullView(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onRouteFullView---" + i);
            NaviHelper.this.onRouteFullViewCode = i;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviGetRouteOption(int i, int i2) throws RemoteException {
            RLog.i(NaviHelper.TAG, "onReqNaviGetRouteOption: code==" + i);
            RLog.i(NaviHelper.TAG, "onReqNaviGetRouteOption: routeOption==" + i2);
            NaviHelper.this.onReqNaviGetRouteOptionResultCode = i;
            switch (i2) {
                case 1:
                    NaviHelper.this.routeOption = RouteOptionEnum.FASTEST;
                    break;
                case 2:
                case 11:
                default:
                    NaviHelper.this.routeOption = "UNSPECIFIED";
                    break;
                case 3:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_HIGHWAY;
                    break;
                case 4:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_TOLLS;
                    break;
                case 5:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_UNPAVED;
                    break;
                case 6:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_TUNNEL;
                    break;
                case 7:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_CARPOOL;
                    break;
                case 8:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_COUNTRY_BORDER;
                    break;
                case 9:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_FERRIES;
                    break;
                case 10:
                    NaviHelper.this.routeOption = RouteOptionEnum.AVOID_CONGESTION;
                    break;
                case 12:
                    NaviHelper.this.routeOption = RouteOptionEnum.RECOMMENDED;
                    break;
                case 13:
                    NaviHelper.this.routeOption = RouteOptionEnum.HIGHWAY;
                    break;
                case 14:
                    NaviHelper.this.routeOption = RouteOptionEnum.SHORTEST;
                    break;
            }
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqUnfinishedRoute(int i, int i2, Place place, List<Place> list) throws RemoteException {
            RLog.i(NaviHelper.TAG, "onReqUnfinishedRoute: code==" + i);
            NaviHelper.this.onReqUnfinishedRouteCode = i;
            switch (i2) {
                case 1:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.FASTEST;
                    break;
                case 2:
                case 11:
                default:
                    NaviHelper.this.routeOptionUnfinished = "UNSPECIFIED";
                    break;
                case 3:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_HIGHWAY;
                    break;
                case 4:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_TOLLS;
                    break;
                case 5:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_UNPAVED;
                    break;
                case 6:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_TUNNEL;
                    break;
                case 7:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_CARPOOL;
                    break;
                case 8:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_COUNTRY_BORDER;
                    break;
                case 9:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_FERRIES;
                    break;
                case 10:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.AVOID_CONGESTION;
                    break;
                case 12:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.RECOMMENDED;
                    break;
                case 13:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.HIGHWAY;
                    break;
                case 14:
                    NaviHelper.this.routeOptionUnfinished = RouteOptionEnum.SHORTEST;
                    break;
            }
            NaviHelper.this.destinationPositionUnfinished = place;
            NaviHelper.this.mViaPositionsUnfinished = list;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviNextViaTimeDistance(int i, long j, double d) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviNextViaTimeDistance");
            NaviHelper.this.onNaviNextViaTimeDistanceCode = i;
            NaviHelper.this.remainTimeNext = j;
            NaviHelper.this.remainDistanceNext = d;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSmartRoutePlanning(int i, RouteInfo routeInfo) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onSmartRoutePlanning   " + i);
            NaviHelper.this.routePlanningCode = i;
            NaviHelper.this.routeInfo = routeInfo;
            if (NaviHelper.this.latchRoute == null || NaviHelper.this.latchRoute.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latchRoute.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanningUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onRoutePlanningUpdate   " + i);
            NaviHelper.this.routePlanningUpdateCode = i;
            if (NaviHelper.this.latchRoute == null || NaviHelper.this.latchRoute.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latchRoute.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviGetRouteOptions(int i, int[] iArr) throws RemoteException {
            String str;
            RLog.i(NaviHelper.TAG, "onReqNaviGetRouteOptions: code==" + i);
            NaviHelper.this.onReqNaviGetRouteOptionResultCode = i;
            NaviHelper.this.mRouteOptions = new ArrayList();
            if (iArr != null) {
                RLog.i(NaviHelper.TAG, "onReqNaviGetRouteOptions: routeOption size==" + iArr.length);
                for (int i2 : iArr) {
                    switch (i2) {
                        case 1:
                            str = RouteOptionEnum.FASTEST;
                            break;
                        case 2:
                        case 4:
                            str = RouteOptionEnum.AVOID_TOLLS;
                            break;
                        case 3:
                            str = RouteOptionEnum.AVOID_HIGHWAY;
                            break;
                        case 5:
                            str = RouteOptionEnum.AVOID_UNPAVED;
                            break;
                        case 6:
                            str = RouteOptionEnum.AVOID_TUNNEL;
                            break;
                        case 7:
                            str = RouteOptionEnum.AVOID_CARPOOL;
                            break;
                        case 8:
                            str = RouteOptionEnum.AVOID_COUNTRY_BORDER;
                            break;
                        case 9:
                            str = RouteOptionEnum.AVOID_FERRIES;
                            break;
                        case 10:
                            str = RouteOptionEnum.AVOID_CONGESTION;
                            break;
                        case 11:
                        default:
                            str = "UNSPECIFIED";
                            break;
                        case 12:
                            str = RouteOptionEnum.RECOMMENDED;
                            break;
                        case 13:
                            str = RouteOptionEnum.HIGHWAY;
                            break;
                        case 14:
                            str = RouteOptionEnum.SHORTEST;
                            break;
                    }
                    NaviHelper.this.mRouteOptions.add(str);
                }
            }
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqUnfinishedRoute2(int i, int[] iArr, Place place, List<Place> list) throws RemoteException {
            String str;
            RLog.i(NaviHelper.TAG, "onReqUnfinishedRoute2: code==" + i);
            NaviHelper.this.onReqUnfinishedRouteCode = i;
            NaviHelper.this.routeOptionUnfinishedEx = new ArrayList();
            if (iArr != null) {
                RLog.i(NaviHelper.TAG, "onReqUnfinishedRoute2: routeOption size==" + iArr.length);
                for (int i2 : iArr) {
                    switch (i2) {
                        case 1:
                            str = RouteOptionEnum.FASTEST;
                            break;
                        case 2:
                        case 4:
                            str = RouteOptionEnum.AVOID_TOLLS;
                            break;
                        case 3:
                            str = RouteOptionEnum.AVOID_HIGHWAY;
                            break;
                        case 5:
                            str = RouteOptionEnum.AVOID_UNPAVED;
                            break;
                        case 6:
                            str = RouteOptionEnum.AVOID_TUNNEL;
                            break;
                        case 7:
                            str = RouteOptionEnum.AVOID_CARPOOL;
                            break;
                        case 8:
                            str = RouteOptionEnum.AVOID_COUNTRY_BORDER;
                            break;
                        case 9:
                            str = RouteOptionEnum.AVOID_FERRIES;
                            break;
                        case 10:
                            str = RouteOptionEnum.AVOID_CONGESTION;
                            break;
                        case 11:
                        default:
                            str = "UNSPECIFIED";
                            break;
                        case 12:
                            str = RouteOptionEnum.RECOMMENDED;
                            break;
                        case 13:
                            str = RouteOptionEnum.HIGHWAY;
                            break;
                        case 14:
                            str = RouteOptionEnum.SHORTEST;
                            break;
                    }
                    NaviHelper.this.routeOptionUnfinishedEx.add(str);
                }
            }
            NaviHelper.this.destinationPositionUnfinished = place;
            NaviHelper.this.mViaPositionsUnfinished = list;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviInfo2(int i, int[] iArr, Place place, Place place2, List<Place> list) throws RemoteException {
            String str;
            RLog.i(NaviHelper.TAG, "enter onReqNaviInfo2");
            RLog.i(NaviHelper.TAG, "onReqNaviInfo2: resultCode==" + i);
            RLog.i(NaviHelper.TAG, "onReqNaviInfo2: resultRouteOptions==" + iArr);
            NaviHelper.this.onReqNaviInfoResultCode = i;
            RLog.i(NaviHelper.TAG, "onReqNaviInfo2: onReqNaviInfoResultCode==" + NaviHelper.this.onReqNaviInfoResultCode);
            NaviHelper.this.mRouteOptions = new ArrayList();
            if (iArr != null) {
                RLog.i(NaviHelper.TAG, "onReqNaviInfo2: routeOption size==" + iArr.length);
                for (int i2 : iArr) {
                    switch (i2) {
                        case 1:
                            str = RouteOptionEnum.FASTEST;
                            break;
                        case 2:
                        case 4:
                            str = RouteOptionEnum.AVOID_TOLLS;
                            break;
                        case 3:
                            str = RouteOptionEnum.AVOID_HIGHWAY;
                            break;
                        case 5:
                            str = RouteOptionEnum.AVOID_UNPAVED;
                            break;
                        case 6:
                            str = RouteOptionEnum.AVOID_TUNNEL;
                            break;
                        case 7:
                            str = RouteOptionEnum.AVOID_CARPOOL;
                            break;
                        case 8:
                            str = RouteOptionEnum.AVOID_COUNTRY_BORDER;
                            break;
                        case 9:
                            str = RouteOptionEnum.AVOID_FERRIES;
                            break;
                        case 10:
                            str = RouteOptionEnum.AVOID_CONGESTION;
                            break;
                        case 11:
                        default:
                            str = "UNSPECIFIED";
                            break;
                        case 12:
                            str = RouteOptionEnum.RECOMMENDED;
                            break;
                        case 13:
                            str = RouteOptionEnum.HIGHWAY;
                            break;
                        case 14:
                            str = RouteOptionEnum.SHORTEST;
                            break;
                    }
                    NaviHelper.this.mRouteOptions.add(str);
                }
            }
            NaviHelper.this.destinationPosition = place2;
            if (NaviHelper.this.latch == null || NaviHelper.this.latch.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latch.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanning2(int i, List<RouteInfo2> list) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onRoutePlanning2   " + i);
            NaviHelper.this.routePlanningCode = i;
            NaviHelper.this.mListRouteInfo2 = list;
            if (NaviHelper.this.latchRoute == null || NaviHelper.this.latchRoute.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latchRoute.countDown();
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSmartRoutePlanning2(int i, RouteInfo2 routeInfo2) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onSmartRoutePlanning2   " + i);
            NaviHelper.this.routePlanningCode = i;
            NaviHelper.this.routeInfo2 = routeInfo2;
            if (NaviHelper.this.latchRoute == null || NaviHelper.this.latchRoute.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latchRoute.countDown();
        }
    };
    private INaviStateCallback mNaviStateCallback = new INaviStateCallback.Stub() { // from class: com.byd.vrassistant.conversation.NaviHelper.2
        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onOpenRouteOption(int i) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onNaviStatusUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviStatusUpdate");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onMapStatusUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onMapStatusUpdate");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onTrafficStatusUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onTrafficStatusUpdate");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onZoomStatusUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onZoomStatusUpdate");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onDayNightModeChange(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onDayNightModeChange");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onNaviOrientationChange(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onNaviOrientationChange");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onFavoriteStatusUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onFavoriteStatusUpdate");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onMapSettingUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onMapSettingUpdate");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onMapOverViewChange(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onMapOverViewChange");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onOpenHistoryDestination(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onOpenHistoryDestination");
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onPOIDetailStatusUpdate(int i) throws RemoteException {
            RLog.i(NaviHelper.TAG, "enter onPOIDetailStatusUpdate");
            NaviHelper.this.onReqOpenPOIDetailCode = i;
            if (NaviHelper.this.latchRoute == null || NaviHelper.this.latchRoute.getCount() <= 0) {
                return;
            }
            NaviHelper.this.latchRoute.countDown();
        }
    };

    public int getOnNaviNextViaTimeDistanceCode() {
        return this.onNaviNextViaTimeDistanceCode;
    }

    public long getRemainTimeNext() {
        return this.remainTimeNext;
    }

    public double getRemainDistanceNext() {
        return this.remainDistanceNext;
    }

    public long getRemainTime() {
        return this.remainTime;
    }

    public double getRemainDistance() {
        return this.remainDistance;
    }

    public int getOnNaviRemainTimeDistanceCode() {
        return this.onNaviRemainTimeDistanceCode;
    }

    public int getOnReqNaviSetRouteOptionCode() {
        return this.onReqNaviSetRouteOptionCode;
    }

    public int getOnRouteFullViewCode() {
        return this.onRouteFullViewCode;
    }

    public int getOnReqNaviAddFavoriteResultCode() {
        return this.onReqNaviAddFavoriteResultCode;
    }

    public int getOnSetAddressResultCode() {
        return this.onSetAddressResultCode;
    }

    public int getOnReqOpenPOIDetailCode() {
        return this.onReqOpenPOIDetailCode;
    }

    public int getOnNaviViaPositionResultCode() {
        return this.onNaviViaPositionResultCode;
    }

    public List<Place> getmPlaceList() {
        return this.mPlaceList;
    }

    public List<RouteInfo2> getmListRouteInfo2() {
        return this.mListRouteInfo2;
    }

    public Place getDetailPOI() {
        return this.mDetailPOI;
    }

    public RouteInfo2 getRouteInfo2() {
        return this.routeInfo2;
    }

    public RouteInfo getRouteInfo() {
        return this.routeInfo;
    }

    public List<RouteInfo> getListRouteInfo() {
        return this.mListRouteInfo;
    }

    public List<String> getRouteOptions() {
        return this.mRouteOptions;
    }

    public List<String> getRouteOptionUnfinishedEx() {
        return this.routeOptionUnfinishedEx;
    }

    public int getAddViaPositionCode() {
        return this.onAddViaPointCode;
    }

    public Place getDestinationPosition() {
        return this.destinationPosition;
    }

    public int getRoutePlanningCode() {
        return this.routePlanningCode;
    }

    public int getRoutePlanningUpdateCode() {
        return this.routePlanningUpdateCode;
    }

    public int getOnReqNaviInfoResultCode() {
        return this.onReqNaviInfoResultCode;
    }

    public int getOnReqNaviGetRouteOptionResultCode() {
        return this.onReqNaviGetRouteOptionResultCode;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public Place getDestinationPositionUnfinished() {
        return this.destinationPositionUnfinished;
    }

    public String getRouteOptionUnfinished() {
        return this.routeOptionUnfinished;
    }

    public List<Place> getmViaPositionsUnfinished() {
        return this.mViaPositionsUnfinished;
    }

    public int getOnReqUnfinishedRouteCode() {
        return this.onReqUnfinishedRouteCode;
    }

    private static final class NaviHelperHandler {
        private static final NaviHelper instance = new NaviHelper();

        private NaviHelperHandler() {
        }
    }

    public static NaviHelper getInstance() {
        return NaviHelperHandler.instance;
    }

    public void setNaviService(INaviService iNaviService) {
        this.mINaviService = iNaviService;
        registerCallback(this.mNaviServiceCallback);
        registerStateCallback(this.mNaviStateCallback);
        if (this.mINaviService != null) {
            RLog.i(TAG, "setNaviService");
        }
    }

    public void destroyNaviService() {
        unregisterCallback(this.mNaviServiceCallback);
        unregisterStateCallback(this.mNaviStateCallback);
        this.mINaviService = null;
    }

    public int reqNaviSupportedOption() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviSupportedOption();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public boolean registerCallback(INaviServiceCallback iNaviServiceCallback) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.registerCallback(iNaviServiceCallback);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterCallback(INaviServiceCallback iNaviServiceCallback) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null || !iNaviService.asBinder().isBinderAlive()) {
                return false;
            }
            return this.mINaviService.unregisterCallback(iNaviServiceCallback);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerStateCallback(INaviStateCallback iNaviStateCallback) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.registerStateCallback(iNaviStateCallback);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterStateCallback(INaviStateCallback iNaviStateCallback) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null || !iNaviService.asBinder().isBinderAlive()) {
                return false;
            }
            return this.mINaviService.unregisterStateCallback(iNaviStateCallback);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int reqNaviLaunchMap() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviLaunchMap();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviBackToMap() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviBackToMap();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getMapAppStatus() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getMapAppStatus();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviZoomOut(boolean z) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviZoomOut(z);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviZoomIn(boolean z) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviZoomIn(z);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviMapMode(int i) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviMapMode(i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getNaviMapMode() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getNaviMapMode();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviSwitchFullView(boolean z) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviSwitchFullView(z);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getNaviSwitchFullView() {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "getNaviSwitchFullView: " + this.mINaviService.getNaviSwitchFullView());
            return this.mINaviService.getNaviSwitchFullView() ? 1 : 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqSwitchMapSetting(int i) {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "reqSwitchMapSetting:  " + i);
            return this.mINaviService.reqSwitchMapSetting(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getMapSettingStatus() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getMapSettingStatus();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqSwitchDayNightMode(int i) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqSwitchDayNightMode(i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getDayNightMode() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getDayNightMode();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqSwitchTraffic(int i) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqSwitchTraffic(i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getTrafficStatus() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getTrafficStatus();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviGetRouteOption() {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "reqNaviGetRouteOption: ");
            return this.mINaviService.reqNaviGetRouteOption();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqOpenRouteOption() {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "reqOpenRouteOption: ");
            return this.mINaviService.reqOpenRouteOption();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int checkHistoryDestinationEmpty() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null) {
                return -100;
            }
            int i = iNaviService.checkHistoryDestinationEmpty() ? 1 : 0;
            RLog.i(TAG, "checkHistoryDestinationEmpty: " + i);
            return i;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqOpenHistoryDestination() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqOpenHistoryDestination();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviGuideStart(String str) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviGuideStart(str);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviGuideStop() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviGuideStop();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int checkNaviGuide() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.checkNaviGuide() ? 1 : 0;
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public Place getHome() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getHome();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Place getCompany() {
        RLog.i(TAG, "enter getCompany");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getCompany();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Place getCurrentPlace() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getCurrentPlace();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int reqSetAddress(int i, Place place) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqSetAddress(i, place);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int resumeNavigation() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.resumeNavigation();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getUnfinishedRoute() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getUnfinishedRoute();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getNaviMaxViaCount() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getNaviMaxViaCount();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int addViaPosition(List<Place> list) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.addViaPosition(list);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public List<Place> reqNaviViaPosition() {
        try {
            if (this.mINaviService == null) {
                return null;
            }
            List<Place> list = this.mPlaceList;
            if (list != null) {
                list.clear();
            }
            return this.mINaviService.reqNaviViaPosition();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int reqSwitchFavorite(int i) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqSwitchFavorite(i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getFavoriteStatus() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getFavoriteStatus();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviAddFavorite() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviAddFavorite();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int checkFavoriteEmpty() {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "checkFavoriteEmpty: " + this.mINaviService.checkFavoriteEmpty());
            return this.mINaviService.checkFavoriteEmpty() ? 1 : 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int checkFavoriteFull() {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "checkFavoriteFull: " + this.mINaviService.checkFavoriteFull());
            return this.mINaviService.checkFavoriteFull() ? 1 : 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviRemainTimeDistance(boolean z) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null) {
                return -100;
            }
            if (z) {
                return iNaviService.reqNaviRemainTimeDistance();
            }
            return iNaviService.reqNaviNextViaTimeDistance();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public List<String> getSupportedRouteOptions() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.getSupportedRouteOptions();
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int reqNaviSetRouteOption(int i, boolean z) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null) {
                return -100;
            }
            this.blSynchronous = z;
            return iNaviService.reqNaviSetRouteOption(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviSetRouteOptions(int[] iArr, boolean z) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null) {
                return -100;
            }
            this.blSynchronous = z;
            return iNaviService.reqNaviSetRouteOptions(iArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviInfo() {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviInfo();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqRoutePlanning(Place place, List<Place> list, int i) {
        RLog.i(TAG, "enter reqRoutePlanning: ");
        this.routePlanningCode = -100;
        try {
            if (this.mINaviService != null) {
                List<RouteInfo> list2 = this.mListRouteInfo;
                if (list2 != null) {
                    list2.clear();
                }
                return this.mINaviService.reqRoutePlanning(place, list, i);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int reqRoutePlanningEx(Place place, List<Place> list, int[] iArr) {
        RLog.i(TAG, "enter reqRoutePlanningEx: ");
        this.routePlanningCode = -100;
        try {
            if (this.mINaviService != null) {
                List<RouteInfo> list2 = this.mListRouteInfo;
                if (list2 != null) {
                    list2.clear();
                }
                return this.mINaviService.reqRoutePlanning2(place, list, iArr);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int reqNaviRouteFullView(List<RouteInfo> list, int i) {
        RLog.i(TAG, "enter reqNaviRouteFullView: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviRouteFullView(list, i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviRouteFullViewEx(List<RouteInfo2> list, int i) {
        RLog.i(TAG, "enter reqNaviRouteFullViewEx: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviRouteFullView2(list, i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public void reqPOISearchByKeyword(String str, final IResponseSender<SearchPlaceByNameResult> iResponseSender) {
        try {
            if (this.mINaviService != null) {
                final int lengthUnit = VehicleHelper.getInstance().getLengthUnit();
                int iReqPOISearchByKeyword = this.mINaviService.reqPOISearchByKeyword(str, new INaviSearchPOICallback.Stub() { // from class: com.byd.vrassistant.conversation.NaviHelper.3
                    @Override // com.byd.naviserviceapi.INaviSearchPOICallback
                    public void onResult(int i, List<Place> list) throws RemoteException {
                        double distance;
                        RLog.i(NaviHelper.TAG, "onResult: resultCode==" + i);
                        if (1 != i) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqPOISearchByKeyword: ERROR");
                            return;
                        }
                        if (list == null) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqPOISearchByKeyword ERROR_list==null");
                            return;
                        }
                        RLog.i(NaviHelper.TAG, "onResult: reqPOISearchByKeyword list.size()==" + list.size());
                        SearchPlaceByNameResult searchPlaceByNameResult = new SearchPlaceByNameResult();
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            PlaceInfo placeInfo = new PlaceInfo();
                            placeInfo.setExtraInfo(list.get(i2).getId());
                            placeInfo.setName(list.get(i2).getName());
                            placeInfo.setAddress(list.get(i2).getAddress());
                            placeInfo.setLongitude(list.get(i2).getLongitude());
                            placeInfo.setLatitude(list.get(i2).getLatitude());
                            if (lengthUnit == 1) {
                                distance = list.get(i2).getDistance() / 1000.0d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                            } else {
                                distance = list.get(i2).getDistance() * 6.2137E-4d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                            }
                            if (distance != 0.0d) {
                                distance = Math.round(distance * 1000.0d) / 1000.0d;
                            }
                            placeInfo.setDistance(distance);
                            placeInfo.setCountry(list.get(i2).getCountry());
                            placeInfo.setState(list.get(i2).getState());
                            placeInfo.setCity(list.get(i2).getCity());
                            if (list.get(i2).getStreet() != null && list.get(i2).getStreet().length > 0) {
                                placeInfo.setStreet(Arrays.asList(list.get(i2).getStreet()));
                            }
                            placeInfo.setHouseNumber(list.get(i2).getHouse_number());
                            if (1 == list.get(i2).getType()) {
                                placeInfo.setType(PlaceTypeEnum.POI);
                            } else if (2 == list.get(i2).getType()) {
                                placeInfo.setType(PlaceTypeEnum.ADDRESS);
                            } else {
                                placeInfo.setType(null);
                            }
                            arrayList.add(placeInfo);
                        }
                        RLog.i(NaviHelper.TAG, "onResult: reqPOISearchByKeyword placeInfoList.size()==" + arrayList.size());
                        RLog.s_log(NaviHelper.TAG, "onResult: reqPOISearchByKeyword placeInfoList==" + arrayList.toString());
                        RLog.i(NaviHelper.TAG, "reqPOISearchByKeyword: type==address");
                        searchPlaceByNameResult.setPlaceList(arrayList);
                        iResponseSender.send(new Response("OK", searchPlaceByNameResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【OK】");
                        RLog.s_log(NaviHelper.TAG, "reqPOISearchByKeyword: OK_result==" + searchPlaceByNameResult.toString());
                    }
                });
                if (iReqPOISearchByKeyword != 1) {
                    if (iReqPOISearchByKeyword == -2) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【UNSUPPORTED】");
                        RLog.i(TAG, "reqPOISearchByKeyword: ERROR ---2");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【ERROR】");
                        RLog.i(TAG, "reqPOISearchByKeyword: ERROR ---1");
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【ERROR】");
            RLog.i(TAG, "reqPOISearchByKeyword: ERROR==", e);
        }
    }

    public void reqPOISearchByKeywordInCategories(String str, final IResponseSender<SearchPlaceByCategoryResult> iResponseSender) {
        try {
            if (this.mINaviService != null) {
                final int lengthUnit = VehicleHelper.getInstance().getLengthUnit();
                int iReqPOISearchByKeyword = this.mINaviService.reqPOISearchByKeyword(str, new INaviSearchPOICallback.Stub() { // from class: com.byd.vrassistant.conversation.NaviHelper.4
                    @Override // com.byd.naviserviceapi.INaviSearchPOICallback
                    public void onResult(int i, List<Place> list) throws RemoteException {
                        double distance;
                        RLog.i(NaviHelper.TAG, "onResult: resultCode==" + i);
                        if (1 != i) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqPOISearchByKeywordInCategories: ERROR");
                            return;
                        }
                        if (list == null) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqPOISearchByKeywordInCategories: ERROR_list==null");
                            return;
                        }
                        RLog.i(NaviHelper.TAG, "onResult: reqPOISearchByKeywordInCategories: list.size()==" + list.size());
                        SearchPlaceByCategoryResult searchPlaceByCategoryResult = new SearchPlaceByCategoryResult();
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            PlaceInfo placeInfo = new PlaceInfo();
                            placeInfo.setExtraInfo(list.get(i2).getId());
                            placeInfo.setName(list.get(i2).getName());
                            placeInfo.setAddress(list.get(i2).getAddress());
                            placeInfo.setLongitude(list.get(i2).getLongitude());
                            placeInfo.setLatitude(list.get(i2).getLatitude());
                            if (lengthUnit == 1) {
                                distance = list.get(i2).getDistance() / 1000.0d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                            } else {
                                distance = list.get(i2).getDistance() * 6.2137E-4d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                            }
                            if (distance != 0.0d) {
                                distance = Math.round(distance * 1000.0d) / 1000.0d;
                            }
                            placeInfo.setDistance(distance);
                            placeInfo.setCountry(list.get(i2).getCountry());
                            placeInfo.setState(list.get(i2).getState());
                            placeInfo.setCity(list.get(i2).getCity());
                            if (list.get(i2).getStreet() != null && list.get(i2).getStreet().length > 0) {
                                placeInfo.setStreet(Arrays.asList(list.get(i2).getStreet()));
                            }
                            placeInfo.setHouseNumber(list.get(i2).getHouse_number());
                            if (1 == list.get(i2).getType()) {
                                placeInfo.setType(PlaceTypeEnum.POI);
                            } else if (2 == list.get(i2).getType()) {
                                placeInfo.setType(PlaceTypeEnum.ADDRESS);
                            } else {
                                placeInfo.setType(null);
                            }
                            arrayList.add(placeInfo);
                            RLog.i(NaviHelper.TAG, "reqPOISearchByKeywordInCategories: placeInfo Name==" + list.get(i2).getName());
                        }
                        RLog.i(NaviHelper.TAG, "reqPOISearchByKeywordInCategories placeInfoList.size()==" + arrayList.size());
                        RLog.s_log(NaviHelper.TAG, "reqPOISearchByKeywordInCategories placeInfoList==" + arrayList.toString());
                        RLog.i(NaviHelper.TAG, "reqPOISearchByKeywordInCategories: type==poi");
                        searchPlaceByCategoryResult.setPlaceList(arrayList);
                        iResponseSender.send(new Response("OK", searchPlaceByCategoryResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【OK】");
                        RLog.s_log(NaviHelper.TAG, "reqPOISearchByKeywordInCategories: OK_result==" + searchPlaceByCategoryResult.toString());
                    }
                });
                if (iReqPOISearchByKeyword != 1) {
                    if (iReqPOISearchByKeyword == -2) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【UNSUPPORTED】");
                        RLog.i(TAG, "reqPOISearchByKeywordInCategories: ERROR---2");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                        RLog.i(TAG, "reqPOISearchByKeywordInCategories: ERROR---1");
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
            RLog.i(TAG, "reqPOISearchByKeywordInCategories: ERROR==", e);
        }
    }

    public void reqPOISearchByCategories(int i, int i2, String str, final IResponseSender<SearchPlaceByCategoryResult> iResponseSender) {
        try {
            if (this.mINaviService != null) {
                final int lengthUnit = VehicleHelper.getInstance().getLengthUnit();
                int iReqPOISearchByCategories = this.mINaviService.reqPOISearchByCategories(i, i2, str, new INaviSearchPOICallback.Stub() { // from class: com.byd.vrassistant.conversation.NaviHelper.5
                    @Override // com.byd.naviserviceapi.INaviSearchPOICallback
                    public void onResult(int i3, List<Place> list) throws RemoteException {
                        double distance;
                        RLog.i(NaviHelper.TAG, "onResult: resultCode==" + i3);
                        if (1 != i3) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqPOISearchByCategories: ERROR");
                            return;
                        }
                        if (list == null) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqPOISearchByCategories: ERROR_list==null");
                            return;
                        }
                        RLog.i(NaviHelper.TAG, "onResult: reqPOISearchByCategories: list.size()==" + list.size());
                        SearchPlaceByCategoryResult searchPlaceByCategoryResult = new SearchPlaceByCategoryResult();
                        ArrayList arrayList = new ArrayList();
                        for (int i4 = 0; i4 < list.size(); i4++) {
                            PlaceInfo placeInfo = new PlaceInfo();
                            placeInfo.setExtraInfo(list.get(i4).getId());
                            placeInfo.setName(list.get(i4).getName());
                            placeInfo.setAddress(list.get(i4).getAddress());
                            placeInfo.setLongitude(list.get(i4).getLongitude());
                            placeInfo.setLatitude(list.get(i4).getLatitude());
                            if (lengthUnit == 1) {
                                distance = list.get(i4).getDistance() / 1000.0d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                            } else {
                                distance = list.get(i4).getDistance() * 6.2137E-4d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                            }
                            if (distance != 0.0d) {
                                distance = Math.round(distance * 1000.0d) / 1000.0d;
                            }
                            placeInfo.setDistance(distance);
                            placeInfo.setCountry(list.get(i4).getCountry());
                            placeInfo.setState(list.get(i4).getState());
                            placeInfo.setCity(list.get(i4).getCity());
                            RLog.i(NaviHelper.TAG, "reqPOISearchByCategories setCity: ");
                            if (list.get(i4).getStreet() != null && list.get(i4).getStreet().length > 0) {
                                placeInfo.setStreet(Arrays.asList(list.get(i4).getStreet()));
                            }
                            placeInfo.setHouseNumber(list.get(i4).getHouse_number());
                            if (1 == list.get(i4).getType()) {
                                placeInfo.setType(PlaceTypeEnum.POI);
                            } else if (2 == list.get(i4).getType()) {
                                placeInfo.setType(PlaceTypeEnum.ADDRESS);
                            } else {
                                placeInfo.setType(null);
                            }
                            arrayList.add(placeInfo);
                        }
                        RLog.i(NaviHelper.TAG, "reqPOISearchByCategories placeInfoList.size()==" + arrayList.size());
                        RLog.s_log(NaviHelper.TAG, "reqPOISearchByCategories placeInfoList==" + arrayList.toString());
                        RLog.i(NaviHelper.TAG, "reqPOISearchByCategories: type==poi");
                        searchPlaceByCategoryResult.setPlaceList(arrayList);
                        iResponseSender.send(new Response("OK", searchPlaceByCategoryResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【OK】");
                        RLog.s_log(NaviHelper.TAG, "reqPOISearchByCategories: OK_result==" + searchPlaceByCategoryResult.toString());
                    }
                });
                if (iReqPOISearchByCategories != 1) {
                    if (iReqPOISearchByCategories == -2) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【UNSUPPORTED】");
                        RLog.i(TAG, "reqPOISearchByCategories: ERROR---2");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                        RLog.i(TAG, "reqPOISearchByCategories: ERROR---1");
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            RLog.i(TAG, "reqPOISearchByCategories: ERROR==", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
        }
    }

    public void reqNaviAlongTheWaySearch(int i, final IResponseSender<SearchPlaceByCategoryResult> iResponseSender) {
        try {
            if (this.mINaviService != null) {
                final int lengthUnit = VehicleHelper.getInstance().getLengthUnit();
                int iReqNaviAlongTheWaySearch = this.mINaviService.reqNaviAlongTheWaySearch(i, new INaviSearchAlongCallback.Stub() { // from class: com.byd.vrassistant.conversation.NaviHelper.6
                    @Override // com.byd.naviserviceapi.INaviSearchAlongCallback
                    public void onResult(int i2, List<Place> list) throws RemoteException {
                        double distance;
                        RLog.i(NaviHelper.TAG, "onResult: resultCode==" + i2);
                        if (1 != i2) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "onResult: reqNaviAlongTheWaySearch: ERROR");
                            return;
                        }
                        if (list == null) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                            RLog.i(NaviHelper.TAG, "reqNaviAlongTheWaySearch: ERROR_listPlace==null");
                            return;
                        }
                        RLog.i(NaviHelper.TAG, "onResult: reqNaviAlongTheWaySearch: listPlace.size()==" + list.size());
                        SearchPlaceByCategoryResult searchPlaceByCategoryResult = new SearchPlaceByCategoryResult();
                        ArrayList arrayList = new ArrayList();
                        for (int i3 = 0; i3 < list.size(); i3++) {
                            PlaceInfo placeInfo = new PlaceInfo();
                            placeInfo.setExtraInfo(list.get(i3).getId());
                            placeInfo.setName(list.get(i3).getName());
                            placeInfo.setAddress(list.get(i3).getAddress());
                            placeInfo.setLongitude(list.get(i3).getLongitude());
                            placeInfo.setLatitude(list.get(i3).getLatitude());
                            if (lengthUnit == 1) {
                                distance = list.get(i3).getDistance() / 1000.0d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                            } else {
                                distance = list.get(i3).getDistance() * 6.2137E-4d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                            }
                            if (distance != 0.0d) {
                                distance = Math.round(distance * 1000.0d) / 1000.0d;
                            }
                            placeInfo.setDistance(distance);
                            placeInfo.setCountry(list.get(i3).getCountry());
                            placeInfo.setState(list.get(i3).getState());
                            placeInfo.setCity(list.get(i3).getCity());
                            if (list.get(i3).getStreet() != null && list.get(i3).getStreet().length > 0) {
                                placeInfo.setStreet(Arrays.asList(list.get(i3).getStreet()));
                            }
                            placeInfo.setHouseNumber(list.get(i3).getHouse_number());
                            if (1 == list.get(i3).getType()) {
                                placeInfo.setType(PlaceTypeEnum.POI);
                            } else if (2 == list.get(i3).getType()) {
                                placeInfo.setType(PlaceTypeEnum.ADDRESS);
                            } else {
                                placeInfo.setType(null);
                            }
                            arrayList.add(placeInfo);
                        }
                        RLog.i(NaviHelper.TAG, "reqNaviAlongTheWaySearch: placeInfoList.size()==" + arrayList.size());
                        RLog.s_log(NaviHelper.TAG, "reqNaviAlongTheWaySearch: placeInfoList==" + arrayList.toString());
                        RLog.i(NaviHelper.TAG, "reqNaviAlongTheWaySearch: type==poi");
                        searchPlaceByCategoryResult.setPlaceList(arrayList);
                        iResponseSender.send(new Response("OK", searchPlaceByCategoryResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【OK】");
                        RLog.s_log(NaviHelper.TAG, "reqNaviAlongTheWaySearch: OK_result==" + searchPlaceByCategoryResult.toString());
                    }
                });
                if (iReqNaviAlongTheWaySearch != 1) {
                    if (iReqNaviAlongTheWaySearch == -2) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【UNSUPPORTED】");
                        RLog.i(TAG, "onResult: reqNaviAlongTheWaySearch: ERROR---2");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
                        RLog.i(TAG, "onResult: reqNaviAlongTheWaySearch: ERROR---1");
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【ERROR】");
            RLog.i(TAG, "reqNaviAlongTheWaySearch: ERROR==", e);
        }
    }

    public int reqOpenSetAddress(int i) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqOpenSetAddress(i);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqNaviAddFavoritePlace(Place place) {
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqNaviAddFavoritePlace(place);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int checkTTSPlaying() {
        try {
            if (this.mINaviService == null) {
                return -100;
            }
            RLog.i(TAG, "checkTTSPlaying: " + this.mINaviService.checkTTSPlaying());
            return this.mINaviService.checkTTSPlaying() ? 1 : 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public void startWait() {
        RLog.i(TAG, "enter startWait: ");
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.latch = countDownLatch;
            countDownLatch.await(4L, TimeUnit.SECONDS);
            if (this.latch.getCount() > 0) {
                this.latch.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startWaitRoutePlanning() {
        RLog.i(TAG, "enter startWaitRoutePlanning: ");
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.latchRoute = countDownLatch;
            countDownLatch.await(15L, TimeUnit.SECONDS);
            if (this.latchRoute.getCount() > 0) {
                this.latchRoute.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int reqSmartNaviStart(Place place) {
        RLog.i(TAG, "enter reqSmartNaviStart: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqSmartNaviStart(place);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqOpenPOIDetail(Place place) {
        RLog.i(TAG, "enter reqOpenPOIDetail: ");
        this.onReqOpenPOIDetailCode = -100;
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                this.mDetailPOI = place;
                return iNaviService.reqOpenPOIDetail(place);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int reqClosePOIDetail() {
        RLog.i(TAG, "enter reqClosePOIDetail: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqClosePOIDetail();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int getPOIDetailStatus() {
        RLog.i(TAG, "enter getPOIDetailStatus: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService == null || !iNaviService.asBinder().isBinderAlive()) {
                return -100;
            }
            return this.mINaviService.getPOIDetailStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqDetailPOIAsDestination(Place place) {
        RLog.i(TAG, "enter reqDetailPOIAsDestination: ");
        this.routePlanningUpdateCode = -100;
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqModifyDestination(place);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int reqDetailPOIAsVia(Place place) {
        RLog.i(TAG, "enter reqDetailPOIAsVia: ");
        this.routePlanningUpdateCode = -100;
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.reqAppendVia(place);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int reqSmartRoutePlanning(Place place, List<Place> list, int i) {
        RLog.i(TAG, "enter reqSmartRoutePlanning: ");
        this.routePlanningCode = -100;
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                if (this.routeInfo != null) {
                    this.routeInfo = null;
                }
                return iNaviService.reqSmartRoutePlanning(place, list, i);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int reqSmartRoutePlanningEx(Place place, List<Place> list, int[] iArr) {
        RLog.i(TAG, "enter reqSmartRoutePlanningEx: ");
        this.routePlanningCode = -100;
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                if (this.routeInfo != null) {
                    this.routeInfo = null;
                }
                return iNaviService.reqSmartRoutePlanning2(place, list, iArr);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -100;
    }

    public int getNaviProvider() {
        String strCodeToPackage = NavUtil.codeToPackage(CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext()));
        if (Constant.TELENAV_NAVIPACKAGE.equals(strCodeToPackage)) {
            return 1;
        }
        if (Constant.NEU_NAVIPACKAGE.equals(strCodeToPackage)) {
            return 2;
        }
        if (Constant.ZAMP_NAVIPACKAGE.equals(strCodeToPackage)) {
            return 3;
        }
        return Constant.KOR_NAVIPACKAGE.equals(strCodeToPackage) ? 5 : 0;
    }

    public int reqShowPlaceMark(List<Place> list) {
        RLog.i(TAG, "enter reqShowPlaceMark");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.showPlaceMark(list);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqHidePlaceMark() {
        RLog.i(TAG, "enter reqHidePlaceMark");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.hidePlaceMark();
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public int reqHighlightPlaceMark(List<Place> list) {
        RLog.i(TAG, "enter reqHighlightPlaceMark");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                return iNaviService.highlightPlaceMark(list);
            }
            return -100;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -100;
        }
    }

    public boolean checkNaviInstalled() {
        String strCodeToPackage = NavUtil.codeToPackage(CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext()));
        if (TextUtils.isEmpty(strCodeToPackage)) {
            return false;
        }
        try {
            BydVoiceApplication.getContext().getPackageManager().getPackageInfo(strCodeToPackage, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean checkDetailPageSupported() {
        RLog.i(TAG, "enter checkDetailPageSupported: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                int remoteVersionCode = iNaviService.getRemoteVersionCode();
                RLog.i(TAG, "versionCode: " + remoteVersionCode);
                return remoteVersionCode >= 19;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkRouteOptionListSupported() {
        RLog.i(TAG, "enter checkRouteOptionListSupported: ");
        try {
            INaviService iNaviService = this.mINaviService;
            if (iNaviService != null) {
                int remoteVersionCode = iNaviService.getRemoteVersionCode();
                RLog.i(TAG, "versionCode: " + remoteVersionCode);
                return remoteVersionCode >= 21;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkNaviSupported() {
        String string = SPUtils.getInstance().getString(Constant.SP_KEY_CUR_LANGUAGE);
        RLog.i(TAG, "checkNaviSupported: " + string);
        if (!TextUtils.isEmpty(string)) {
            if (getInstance().getNaviProvider() == 2) {
                return string.contains("data_eng") || string.contains("data_nor") || string.contains("data_fra") || string.contains("data_deu") || string.contains("data_spa") || string.contains("data_tha") || string.contains("data_ita") || string.contains("data_por") || string.contains("data_nld");
            }
            if (getInstance().getNaviProvider() == 3 && string.contains("data_jpn")) {
                return true;
            }
        }
        return false;
    }
}
