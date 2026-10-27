package cerence.ark.assistant.controller.domain.ude;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.ude.param.AddToFavoritesParam;
import cerence.ark.assistant.controller.domain.ude.param.ChangeRouteOptionParam;
import cerence.ark.assistant.controller.domain.ude.param.ChangeWaypointsParam;
import cerence.ark.assistant.controller.domain.ude.param.CheckDuplicateRoutePlaceParam;
import cerence.ark.assistant.controller.domain.ude.param.CheckNaviFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.ude.param.GetAddressByTypeParam;
import cerence.ark.assistant.controller.domain.ude.param.GetDistanceParam;
import cerence.ark.assistant.controller.domain.ude.param.GetETAParam;
import cerence.ark.assistant.controller.domain.ude.param.GetRoutesParam;
import cerence.ark.assistant.controller.domain.ude.param.ReplaceDestinationParam;
import cerence.ark.assistant.controller.domain.ude.param.RouteOverviewParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPlaceByCategoryParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPlaceByNameParam;
import cerence.ark.assistant.controller.domain.ude.param.SetAddressByTypeParam;
import cerence.ark.assistant.controller.domain.ude.param.SetDefaultRouteOptionParam;
import cerence.ark.assistant.controller.domain.ude.param.SetMapOptionParam;
import cerence.ark.assistant.controller.domain.ude.param.StartGuidanceParam;
import cerence.ark.assistant.controller.domain.ude.param.ZoomMapParam;
import cerence.ark.assistant.controller.domain.ude.result.CheckDuplicateRoutePlaceResult;
import cerence.ark.assistant.controller.domain.ude.result.CheckNaviFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.ude.result.GetAddressByTypeResult;
import cerence.ark.assistant.controller.domain.ude.result.GetDistanceResult;
import cerence.ark.assistant.controller.domain.ude.result.GetETAResult;
import cerence.ark.assistant.controller.domain.ude.result.GetFavoritesListStatusResult;
import cerence.ark.assistant.controller.domain.ude.result.GetMaxWaypointsResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRecentListResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRouteOptionResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRoutesResult;
import cerence.ark.assistant.controller.domain.ude.result.GetSupportedRouteOptionsResult;
import cerence.ark.assistant.controller.domain.ude.result.GetUnfinishedRouteResult;
import cerence.ark.assistant.controller.domain.ude.result.GetWaypointsResult;
import cerence.ark.assistant.controller.domain.ude.result.IsDetailPageSupportedResult;
import cerence.ark.assistant.controller.domain.ude.result.IsNavigatingResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceByCategoryResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceByNameResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsUDEDomain extends Domain {
    private static final String TAG = "AbsUDEDomain";

    protected boolean abortAddToFavorites() {
        return true;
    }

    protected boolean abortChangeRouteOption() {
        return true;
    }

    protected boolean abortChangeWaypoints() {
        return true;
    }

    protected boolean abortCheckDuplicateRoutePlace() {
        return true;
    }

    protected boolean abortCheckNaviFunctionAvailability() {
        return true;
    }

    protected boolean abortCloseFavoritesList() {
        return true;
    }

    protected boolean abortGetAddressByType() {
        return true;
    }

    protected boolean abortGetDistance() {
        return true;
    }

    protected boolean abortGetETA() {
        return true;
    }

    protected boolean abortGetFavoritesListStatus() {
        return true;
    }

    protected boolean abortGetMaxWaypoints() {
        return true;
    }

    protected boolean abortGetRecentList() {
        return true;
    }

    protected boolean abortGetRouteOption() {
        return true;
    }

    protected boolean abortGetRoutes() {
        return true;
    }

    protected boolean abortGetSupportedRouteOptions() {
        return true;
    }

    protected boolean abortGetUnfinishedRoute() {
        return true;
    }

    protected boolean abortGetWaypoints() {
        return true;
    }

    protected boolean abortIsDetailPageSupported() {
        return true;
    }

    protected boolean abortIsNavigating() {
        return true;
    }

    protected boolean abortOpenFavoritesList() {
        return true;
    }

    protected boolean abortOpenMap() {
        return true;
    }

    protected boolean abortReplaceDestination() {
        return true;
    }

    protected boolean abortResumeNavigation() {
        return true;
    }

    protected boolean abortRouteOverview() {
        return true;
    }

    protected boolean abortSearchPlaceByCategory() {
        return true;
    }

    protected boolean abortSearchPlaceByName() {
        return true;
    }

    protected boolean abortSetAddressByType() {
        return true;
    }

    protected boolean abortSetDefaultRouteOption() {
        return true;
    }

    protected boolean abortSetMapOption() {
        return true;
    }

    protected boolean abortStartGuidance() {
        return true;
    }

    protected boolean abortStopGuidance() {
        return true;
    }

    protected boolean abortZoomMap() {
        return true;
    }

    protected abstract void addToFavorites(AddToFavoritesParam addToFavoritesParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void changeRouteOption(ChangeRouteOptionParam changeRouteOptionParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void changeWaypoints(ChangeWaypointsParam changeWaypointsParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void checkDuplicateRoutePlace(CheckDuplicateRoutePlaceParam checkDuplicateRoutePlaceParam, IResponseSender<CheckDuplicateRoutePlaceResult> iResponseSender);

    protected abstract void checkNaviFunctionAvailability(CheckNaviFunctionAvailabilityParam checkNaviFunctionAvailabilityParam, IResponseSender<CheckNaviFunctionAvailabilityResult> iResponseSender);

    protected abstract void closeFavoritesList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void getAddressByType(GetAddressByTypeParam getAddressByTypeParam, IResponseSender<GetAddressByTypeResult> iResponseSender);

    protected abstract void getDistance(GetDistanceParam getDistanceParam, IResponseSender<GetDistanceResult> iResponseSender);

    protected abstract void getETA(GetETAParam getETAParam, IResponseSender<GetETAResult> iResponseSender);

    protected abstract void getFavoritesListStatus(IResponseSender<GetFavoritesListStatusResult> iResponseSender);

    protected abstract void getMaxWaypoints(IResponseSender<GetMaxWaypointsResult> iResponseSender);

    protected abstract void getRecentList(IResponseSender<GetRecentListResult> iResponseSender);

    protected abstract void getRouteOption(IResponseSender<GetRouteOptionResult> iResponseSender);

    protected abstract void getRoutes(GetRoutesParam getRoutesParam, IResponseSender<GetRoutesResult> iResponseSender);

    protected abstract void getSupportedRouteOptions(IResponseSender<GetSupportedRouteOptionsResult> iResponseSender);

    protected abstract void getUnfinishedRoute(IResponseSender<GetUnfinishedRouteResult> iResponseSender);

    protected abstract void getWaypoints(IResponseSender<GetWaypointsResult> iResponseSender);

    protected abstract void isDetailPageSupported(IResponseSender<IsDetailPageSupportedResult> iResponseSender);

    protected abstract void isNavigating(IResponseSender<IsNavigatingResult> iResponseSender);

    protected abstract void openFavoritesList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void openMap(IResponseSender<BaseResult> iResponseSender);

    protected abstract void replaceDestination(ReplaceDestinationParam replaceDestinationParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void resumeNavigation(IResponseSender<BaseResult> iResponseSender);

    protected abstract void routeOverview(RouteOverviewParam routeOverviewParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void searchPlaceByCategory(SearchPlaceByCategoryParam searchPlaceByCategoryParam, IResponseSender<SearchPlaceByCategoryResult> iResponseSender);

    protected abstract void searchPlaceByName(SearchPlaceByNameParam searchPlaceByNameParam, IResponseSender<SearchPlaceByNameResult> iResponseSender);

    protected abstract void setAddressByType(SetAddressByTypeParam setAddressByTypeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setDefaultRouteOption(SetDefaultRouteOptionParam setDefaultRouteOptionParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setMapOption(SetMapOptionParam setMapOptionParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void startGuidance(StartGuidanceParam startGuidanceParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void stopGuidance(IResponseSender<BaseResult> iResponseSender);

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
        map.put(UDERequestsEnum.IS_NAVIGATING, new RequestHandler<IsNavigatingResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsNavigatingResult> iResponseSender) {
                AbsUDEDomain.this.isNavigating(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortIsNavigating();
            }
        });
        map.put(UDERequestsEnum.STOP_GUIDANCE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.stopGuidance(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortStopGuidance();
            }
        });
        map.put(UDERequestsEnum.SET_DEFAULT_ROUTE_OPTION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.4
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
        map.put(UDERequestsEnum.GET_ROUTE_OPTION, new RequestHandler<GetRouteOptionResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRouteOptionResult> iResponseSender) {
                AbsUDEDomain.this.getRouteOption(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetRouteOption();
            }
        });
        map.put(UDERequestsEnum.GET_WAYPOINTS, new RequestHandler<GetWaypointsResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetWaypointsResult> iResponseSender) {
                AbsUDEDomain.this.getWaypoints(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetWaypoints();
            }
        });
        map.put(UDERequestsEnum.GET_MAX_WAYPOINTS, new RequestHandler<GetMaxWaypointsResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMaxWaypointsResult> iResponseSender) {
                AbsUDEDomain.this.getMaxWaypoints(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetMaxWaypoints();
            }
        });
        map.put(UDERequestsEnum.SEARCH_PLACE_BY_CATEGORY, new RequestHandler<SearchPlaceByCategoryResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchPlaceByCategoryResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.searchPlaceByCategory(SearchPlaceByCategoryParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> searchPlaceByCategory]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSearchPlaceByCategory();
            }
        });
        map.put(UDERequestsEnum.SEARCH_PLACE_BY_NAME, new RequestHandler<SearchPlaceByNameResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchPlaceByNameResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.searchPlaceByName(SearchPlaceByNameParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> searchPlaceByName]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSearchPlaceByName();
            }
        });
        map.put(UDERequestsEnum.ZOOM_MAP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.10
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
        map.put(UDERequestsEnum.SET_ADDRESS_BY_TYPE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.setAddressByType(SetAddressByTypeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> setAddressByType]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSetAddressByType();
            }
        });
        map.put(UDERequestsEnum.GET_ADDRESS_BY_TYPE, new RequestHandler<GetAddressByTypeResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetAddressByTypeResult> iResponseSender) {
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
        map.put(UDERequestsEnum.GET_ETA, new RequestHandler<GetETAResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.13
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
        map.put(UDERequestsEnum.GET_DISTANCE, new RequestHandler<GetDistanceResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.14
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
        map.put(UDERequestsEnum.GET_RECENT_LIST, new RequestHandler<GetRecentListResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRecentListResult> iResponseSender) {
                AbsUDEDomain.this.getRecentList(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetRecentList();
            }
        });
        map.put(UDERequestsEnum.GET_SUPPORTED_ROUTE_OPTIONS, new RequestHandler<GetSupportedRouteOptionsResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSupportedRouteOptionsResult> iResponseSender) {
                AbsUDEDomain.this.getSupportedRouteOptions(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetSupportedRouteOptions();
            }
        });
        map.put(UDERequestsEnum.GET_ROUTES, new RequestHandler<GetRoutesResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.17
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRoutesResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.getRoutes(GetRoutesParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> getRoutes]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetRoutes();
            }
        });
        map.put(UDERequestsEnum.START_GUIDANCE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.18
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.startGuidance(StartGuidanceParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> startGuidance]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortStartGuidance();
            }
        });
        map.put(UDERequestsEnum.SET_MAP_OPTION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.19
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.setMapOption(SetMapOptionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> setMapOption]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortSetMapOption();
            }
        });
        map.put(UDERequestsEnum.ADD_TO_FAVORITES, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.20
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.addToFavorites(AddToFavoritesParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> addToFavorites]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortAddToFavorites();
            }
        });
        map.put(UDERequestsEnum.ROUTE_OVERVIEW, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.21
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.routeOverview(RouteOverviewParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> routeOverview]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortRouteOverview();
            }
        });
        map.put(UDERequestsEnum.GET_FAVORITES_LIST_STATUS, new RequestHandler<GetFavoritesListStatusResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.22
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetFavoritesListStatusResult> iResponseSender) {
                AbsUDEDomain.this.getFavoritesListStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetFavoritesListStatus();
            }
        });
        map.put(UDERequestsEnum.OPEN_FAVORITES_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.23
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.openFavoritesList(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortOpenFavoritesList();
            }
        });
        map.put(UDERequestsEnum.CLOSE_FAVORITES_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.24
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.closeFavoritesList(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortCloseFavoritesList();
            }
        });
        map.put(UDERequestsEnum.CHANGE_WAYPOINTS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.25
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.changeWaypoints(ChangeWaypointsParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> changeWaypoints]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortChangeWaypoints();
            }
        });
        map.put(UDERequestsEnum.CHANGE_ROUTE_OPTION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.26
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.changeRouteOption(ChangeRouteOptionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> changeRouteOption]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortChangeRouteOption();
            }
        });
        map.put(UDERequestsEnum.GET_UNFINISHED_ROUTE, new RequestHandler<GetUnfinishedRouteResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.27
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetUnfinishedRouteResult> iResponseSender) {
                AbsUDEDomain.this.getUnfinishedRoute(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortGetUnfinishedRoute();
            }
        });
        map.put(UDERequestsEnum.RESUME_NAVIGATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.28
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsUDEDomain.this.resumeNavigation(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortResumeNavigation();
            }
        });
        map.put(UDERequestsEnum.CHECK_DUPLICATE_ROUTE_PLACE, new RequestHandler<CheckDuplicateRoutePlaceResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.29
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckDuplicateRoutePlaceResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.checkDuplicateRoutePlace(CheckDuplicateRoutePlaceParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> checkDuplicateRoutePlace]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortCheckDuplicateRoutePlace();
            }
        });
        map.put(UDERequestsEnum.REPLACE_DESTINATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.30
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.replaceDestination(ReplaceDestinationParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> replaceDestination]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortReplaceDestination();
            }
        });
        map.put(UDERequestsEnum.IS_DETAIL_PAGE_SUPPORTED, new RequestHandler<IsDetailPageSupportedResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.31
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsDetailPageSupportedResult> iResponseSender) {
                AbsUDEDomain.this.isDetailPageSupported(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortIsDetailPageSupported();
            }
        });
        map.put(UDERequestsEnum.CHECK_NAVI_FUNCTION_AVAILABILITY, new RequestHandler<CheckNaviFunctionAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.ude.AbsUDEDomain.32
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckNaviFunctionAvailabilityResult> iResponseSender) {
                try {
                    AbsUDEDomain.this.checkNaviFunctionAvailability(CheckNaviFunctionAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsUDEDomain.TAG, "[UDE -> checkNaviFunctionAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsUDEDomain.this.abortCheckNaviFunctionAvailability();
            }
        });
    }
}
