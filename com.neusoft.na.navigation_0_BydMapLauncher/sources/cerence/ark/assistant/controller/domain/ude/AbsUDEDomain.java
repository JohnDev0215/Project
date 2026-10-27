package cerence.ark.assistant.controller.domain.ude;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.ude.param.ControlTrafficInfoViewParam;
import cerence.ark.assistant.controller.domain.ude.param.GetAddressByTypeParam;
import cerence.ark.assistant.controller.domain.ude.param.GetDistanceParam;
import cerence.ark.assistant.controller.domain.ude.param.GetETAParam;
import cerence.ark.assistant.controller.domain.ude.param.NavigateToDestinationParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPlaceForRoutePlanningParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPoiByNameParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPoiCategoryByAddressParam;
import cerence.ark.assistant.controller.domain.ude.param.SetDefaultAddressByTypeParam;
import cerence.ark.assistant.controller.domain.ude.param.SetDefaultRouteOptionParam;
import cerence.ark.assistant.controller.domain.ude.param.SetMapDisplayModeParam;
import cerence.ark.assistant.controller.domain.ude.param.ZoomMapParam;
import cerence.ark.assistant.controller.domain.ude.result.GetAvoidPointsResult;
import cerence.ark.assistant.controller.domain.ude.result.GetDistanceResult;
import cerence.ark.assistant.controller.domain.ude.result.GetETAResult;
import cerence.ark.assistant.controller.domain.ude.result.GetMapDisplayModeResult;
import cerence.ark.assistant.controller.domain.ude.result.GetMaxWayPointsResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRecentListResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRouteOptionListResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRouteOptionResult;
import cerence.ark.assistant.controller.domain.ude.result.GetWayPointsResult;
import cerence.ark.assistant.controller.domain.ude.result.IsMapVisibleResult;
import cerence.ark.assistant.controller.domain.ude.result.IsNavigatingResult;
import cerence.ark.assistant.controller.domain.ude.result.PlaceInfoResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceForRoutePlanningResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPoiByNameResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPoiCategoryByAddressResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsUDEDomain extends Domain {
    private static final String TAG = "AbsUDEDomain";

    protected boolean abortCloseMap() {
        return true;
    }

    protected boolean abortControlTrafficInfoView() {
        return true;
    }

    protected boolean abortExitNavigation() {
        return true;
    }

    protected boolean abortGetAddressByType() {
        return true;
    }

    protected boolean abortGetAvoidPoints() {
        return true;
    }

    protected boolean abortGetDistance() {
        return true;
    }

    protected boolean abortGetETA() {
        return true;
    }

    protected boolean abortGetMapDisplayMode() {
        return true;
    }

    protected boolean abortGetMaxWayPoints() {
        return true;
    }

    protected boolean abortGetRecentList() {
        return true;
    }

    protected boolean abortGetRouteOption() {
        return true;
    }

    protected boolean abortGetRouteOptionList() {
        return true;
    }

    protected boolean abortGetWayPoints() {
        return true;
    }

    protected boolean abortIsMapVisible() {
        return true;
    }

    protected boolean abortIsNavigating() {
        return true;
    }

    protected boolean abortNavigateToDestination() {
        return true;
    }

    protected boolean abortOpenMap() {
        return true;
    }

    protected boolean abortSearchPlaceForRoutePlanning() {
        return true;
    }

    protected boolean abortSearchPoiByName() {
        return true;
    }

    protected boolean abortSearchPoiCategoryByAddress() {
        return true;
    }

    protected boolean abortSetDefaultAddressByType() {
        return true;
    }

    protected boolean abortSetDefaultRouteOption() {
        return true;
    }

    protected boolean abortSetMapDisplayMode() {
        return true;
    }

    protected boolean abortZoomMap() {
        return true;
    }

    protected abstract void closeMap(IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlTrafficInfoView(ControlTrafficInfoViewParam controlTrafficInfoViewParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void exitNavigation(IResponseSender<BaseResult> iResponseSender);

    protected abstract void getAddressByType(GetAddressByTypeParam getAddressByTypeParam, IResponseSender<PlaceInfoResult> iResponseSender);

    protected abstract void getAvoidPoints(IResponseSender<GetAvoidPointsResult> iResponseSender);

    protected abstract void getDistance(GetDistanceParam getDistanceParam, IResponseSender<GetDistanceResult> iResponseSender);

    protected abstract void getETA(GetETAParam getETAParam, IResponseSender<GetETAResult> iResponseSender);

    protected abstract void getMapDisplayMode(IResponseSender<GetMapDisplayModeResult> iResponseSender);

    protected abstract void getMaxWayPoints(IResponseSender<GetMaxWayPointsResult> iResponseSender);

    protected abstract void getRecentList(IResponseSender<GetRecentListResult> iResponseSender);

    protected abstract void getRouteOption(IResponseSender<GetRouteOptionResult> iResponseSender);

    protected abstract void getRouteOptionList(IResponseSender<GetRouteOptionListResult> iResponseSender);

    protected abstract void getWayPoints(IResponseSender<GetWayPointsResult> iResponseSender);

    protected abstract void isMapVisible(IResponseSender<IsMapVisibleResult> iResponseSender);

    protected abstract void isNavigating(IResponseSender<IsNavigatingResult> iResponseSender);

    protected abstract void navigateToDestination(NavigateToDestinationParam navigateToDestinationParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void openMap(IResponseSender<BaseResult> iResponseSender);

    protected abstract void searchPlaceForRoutePlanning(SearchPlaceForRoutePlanningParam searchPlaceForRoutePlanningParam, IResponseSender<SearchPlaceForRoutePlanningResult> iResponseSender);

    protected abstract void searchPoiByName(SearchPoiByNameParam searchPoiByNameParam, IResponseSender<SearchPoiByNameResult> iResponseSender);

    protected abstract void searchPoiCategoryByAddress(SearchPoiCategoryByAddressParam searchPoiCategoryByAddressParam, IResponseSender<SearchPoiCategoryByAddressResult> iResponseSender);

    protected abstract void setDefaultAddressByType(SetDefaultAddressByTypeParam setDefaultAddressByTypeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setDefaultRouteOption(SetDefaultRouteOptionParam setDefaultRouteOptionParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setMapDisplayMode(SetMapDisplayModeParam setMapDisplayModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void zoomMap(ZoomMapParam zoomMapParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsUDEDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(UDERequestsEnum.OPEN_MAP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.openMap(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortOpenMap();
            }
        });
        map.put(UDERequestsEnum.CLOSE_MAP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.closeMap(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortCloseMap();
            }
        });
        map.put(UDERequestsEnum.IS_NAVIGATING, new RequestHandler<IsNavigatingResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsNavigatingResult> iResponseSender) {
                AbsUDEDomain.this.isNavigating(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortIsNavigating();
            }
        });
        map.put(UDERequestsEnum.NAVIGATE_TO_DESTINATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.navigateToDestination(NavigateToDestinationParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> navigateToDestination]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortNavigateToDestination();
            }
        });
        map.put(UDERequestsEnum.EXIT_NAVIGATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.exitNavigation(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortExitNavigation();
            }
        });
        map.put(UDERequestsEnum.SET_DEFAULT_ROUTE_OPTION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.setDefaultRouteOption(SetDefaultRouteOptionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> setDefaultRouteOption]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSetDefaultRouteOption();
            }
        });
        map.put(UDERequestsEnum.GET_ROUTE_OPTION, new RequestHandler<GetRouteOptionResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRouteOptionResult> iResponseSender) {
                AbsUDEDomain.this.getRouteOption(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetRouteOption();
            }
        });
        map.put(UDERequestsEnum.GET_WAY_POINTS, new RequestHandler<GetWayPointsResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetWayPointsResult> iResponseSender) {
                AbsUDEDomain.this.getWayPoints(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetWayPoints();
            }
        });
        map.put(UDERequestsEnum.GET_MAX_WAY_POINTS, new RequestHandler<GetMaxWayPointsResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMaxWayPointsResult> iResponseSender) {
                AbsUDEDomain.this.getMaxWayPoints(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetMaxWayPoints();
            }
        });
        map.put(UDERequestsEnum.GET_AVOID_POINTS, new RequestHandler<GetAvoidPointsResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetAvoidPointsResult> iResponseSender) {
                AbsUDEDomain.this.getAvoidPoints(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetAvoidPoints();
            }
        });
        map.put(UDERequestsEnum.SEARCH_POI_CATEGORY_BY_ADDRESS, new RequestHandler<SearchPoiCategoryByAddressResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchPoiCategoryByAddressResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.searchPoiCategoryByAddress(SearchPoiCategoryByAddressParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> searchPoiCategoryByAddress]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSearchPoiCategoryByAddress();
            }
        });
        map.put(UDERequestsEnum.SEARCH_PLACE_FOR_ROUTE_PLANNING, new RequestHandler<SearchPlaceForRoutePlanningResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchPlaceForRoutePlanningResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.searchPlaceForRoutePlanning(SearchPlaceForRoutePlanningParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> searchPlaceForRoutePlanning]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSearchPlaceForRoutePlanning();
            }
        });
        map.put(UDERequestsEnum.GET_MAP_DISPLAY_MODE, new RequestHandler<GetMapDisplayModeResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMapDisplayModeResult> iResponseSender) {
                AbsUDEDomain.this.getMapDisplayMode(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetMapDisplayMode();
            }
        });
        map.put(UDERequestsEnum.SET_MAP_DISPLAY_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.setMapDisplayMode(SetMapDisplayModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> setMapDisplayMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSetMapDisplayMode();
            }
        });
        map.put(UDERequestsEnum.ZOOM_MAP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.zoomMap(ZoomMapParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> zoomMap]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortZoomMap();
            }
        });
        map.put(UDERequestsEnum.SET_DEFAULT_ADDRESS_BY_TYPE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.setDefaultAddressByType(SetDefaultAddressByTypeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> setDefaultAddressByType]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSetDefaultAddressByType();
            }
        });
        map.put(UDERequestsEnum.GET_ADDRESS_BY_TYPE, new RequestHandler<PlaceInfoResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.17
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PlaceInfoResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.getAddressByType(GetAddressByTypeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> getAddressByType]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetAddressByType();
            }
        });
        map.put(UDERequestsEnum.GET_ETA, new RequestHandler<GetETAResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.18
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetETAResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.getETA(GetETAParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> getETA]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetETA();
            }
        });
        map.put(UDERequestsEnum.GET_DISTANCE, new RequestHandler<GetDistanceResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.19
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetDistanceResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.getDistance(GetDistanceParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> getDistance]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetDistance();
            }
        });
        map.put(UDERequestsEnum.SEARCH_POI_BY_NAME, new RequestHandler<SearchPoiByNameResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.20
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchPoiByNameResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.searchPoiByName(SearchPoiByNameParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> searchPoiByName]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSearchPoiByName();
            }
        });
        map.put(UDERequestsEnum.GET_RECENT_LIST, new RequestHandler<GetRecentListResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.21
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRecentListResult> iResponseSender) {
                AbsUDEDomain.this.getRecentList(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetRecentList();
            }
        });
        map.put(UDERequestsEnum.CONTROL_TRAFFIC_INFO_VIEW, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.22
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.controlTrafficInfoView(ControlTrafficInfoViewParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> controlTrafficInfoView]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortControlTrafficInfoView();
            }
        });
        map.put(UDERequestsEnum.IS_MAP_VISIBLE, new RequestHandler<IsMapVisibleResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.23
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsMapVisibleResult> iResponseSender) {
                AbsUDEDomain.this.isMapVisible(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortIsMapVisible();
            }
        });
        map.put(UDERequestsEnum.GET_ROUTE_OPTION_LIST, new RequestHandler<GetRouteOptionListResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.24
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRouteOptionListResult> iResponseSender) {
                AbsUDEDomain.this.getRouteOptionList(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetRouteOptionList();
            }
        });
    }
}
