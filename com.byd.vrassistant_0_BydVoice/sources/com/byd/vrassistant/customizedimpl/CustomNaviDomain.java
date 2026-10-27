package com.byd.vrassistant.customizedimpl;

import android.provider.Settings;
import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.ude.AbsUDEDomain;
import cerence.ark.assistant.controller.domain.ude.param.AddToFavoritesParam;
import cerence.ark.assistant.controller.domain.ude.param.ChangeRouteOptionParam;
import cerence.ark.assistant.controller.domain.ude.param.ChangeWaypointsParam;
import cerence.ark.assistant.controller.domain.ude.param.CheckDuplicateRoutePlaceParam;
import cerence.ark.assistant.controller.domain.ude.param.CheckNaviFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.ude.param.GetAddressByTypeParam;
import cerence.ark.assistant.controller.domain.ude.param.GetDistanceParam;
import cerence.ark.assistant.controller.domain.ude.param.GetETAParam;
import cerence.ark.assistant.controller.domain.ude.param.ReplaceDestinationParam;
import cerence.ark.assistant.controller.domain.ude.param.RouteOverviewParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPlaceByCategoryParam;
import cerence.ark.assistant.controller.domain.ude.param.SearchPlaceByNameParam;
import cerence.ark.assistant.controller.domain.ude.param.SetAddressByTypeParam;
import cerence.ark.assistant.controller.domain.ude.param.SetDefaultRouteOptionParam;
import cerence.ark.assistant.controller.domain.ude.param.StartGuidanceParam;
import cerence.ark.assistant.controller.domain.ude.param.ZoomMapParam;
import cerence.ark.assistant.controller.domain.ude.result.CheckDuplicateRoutePlaceResult;
import cerence.ark.assistant.controller.domain.ude.result.CheckNaviFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.ude.result.GetAddressByTypeResult;
import cerence.ark.assistant.controller.domain.ude.result.GetDistanceResult;
import cerence.ark.assistant.controller.domain.ude.result.GetETAResult;
import cerence.ark.assistant.controller.domain.ude.result.GetMaxWaypointsResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRecentListResult;
import cerence.ark.assistant.controller.domain.ude.result.GetRouteOptionResult;
import cerence.ark.assistant.controller.domain.ude.result.GetSupportedRouteOptionsResult;
import cerence.ark.assistant.controller.domain.ude.result.GetUnfinishedRouteResult;
import cerence.ark.assistant.controller.domain.ude.result.GetWaypointsResult;
import cerence.ark.assistant.controller.domain.ude.result.IsDetailPageSupportedResult;
import cerence.ark.assistant.controller.domain.ude.result.IsNavigatingResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceByCategoryResult;
import cerence.ark.assistant.controller.domain.ude.result.SearchPlaceByNameResult;
import cerence.ark.assistant.controller.enumeration.AddressTypeEnum;
import cerence.ark.assistant.controller.enumeration.FunctionAvailabilityEnum;
import cerence.ark.assistant.controller.enumeration.MileageUnitEnum;
import cerence.ark.assistant.controller.enumeration.NaviFunctionEnum;
import cerence.ark.assistant.controller.enumeration.PlaceTypeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.RouteOptionEnum;
import cerence.ark.assistant.controller.enumeration.ZoomOptionsEnum;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.controller.struct.RouteInfo;
import cerence.ark.assistant.controller.struct.RouteOptionInfo;
import com.byd.naviserviceapi.Place;
import com.byd.naviserviceapi.RouteInfo2;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.NaviHelper;
import com.byd.vrassistant.conversation.VehicleHelper;
import com.byd.vrassistant.conversation.VehicleSettingsHelper;
import com.byd.vrassistant.eventbus.NaviUpdateEvent;
import com.byd.vrassistant.utils.NavUtil;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class CustomNaviDomain extends AbsUDEDomain {
    private static final int DEFAULT_ROUTE_OPTION = 0;
    private static final String TAG = "NEU_CustomNaviDomain";
    private boolean isDetailPageSupported;
    private double mDesLat;
    private double mDesLon;
    private int maxViaCount;

    protected CustomNaviDomain(Dhi dhi) {
        super(dhi);
        this.maxViaCount = 0;
        this.mDesLon = 0.0d;
        this.mDesLat = 0.0d;
        this.isDetailPageSupported = false;
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void openMap(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】");
        RLog.i(TAG, "enter openMap");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
            int mapAppStatus = NaviHelper.getInstance().getMapAppStatus();
            RLog.i(TAG, "openMap: getMapAppStatus==" + mapAppStatus);
            if (mapAppStatus == 0) {
                int iReqNaviLaunchMap = NaviHelper.getInstance().reqNaviLaunchMap();
                RLog.i(TAG, "reqNaviLaunchMap: mapStatus==" + iReqNaviLaunchMap);
                if (iReqNaviLaunchMap == 1) {
                    RLog.i(TAG, "openMap: OK");
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【OK】");
                } else if (-2 == iReqNaviLaunchMap) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【UNSUPPORTED】");
                } else {
                    RLog.d(TAG, "openMap: reqNaviLaunchMap Fail");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【ERROR】");
                }
            } else if (2 == mapAppStatus) {
                int iReqNaviBackToMap = NaviHelper.getInstance().reqNaviBackToMap();
                if (iReqNaviBackToMap == 1) {
                    RLog.i(TAG, "openMap: OK");
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【OK】");
                } else if (-2 == iReqNaviBackToMap) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【UNSUPPORTED】");
                } else {
                    RLog.d(TAG, "openMap: reqNaviBackToMap Fail");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【ERROR】");
                }
            } else if (1 == mapAppStatus) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【OK】");
            } else if (-4 == mapAppStatus) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【NAVI_AUTH_INAVLID】");
            } else if (-2 == mapAppStatus) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【UNSUPPORTED】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.d(TAG, "openMap: ERROR3");
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【ERROR】");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.e(TAG, "openMap: ERROR4 e==>" + e.toString(), e);
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openMap】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void isNavigating(IResponseSender<IsNavigatingResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isNavigating】");
        RLog.i(TAG, "enter isNavigating");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isNavigating】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            int iCheckNaviGuide = NaviHelper.getInstance().checkNaviGuide();
            RLog.i(TAG, "isNavigating: checkNaviGuide==" + iCheckNaviGuide);
            IsNavigatingResult isNavigatingResult = new IsNavigatingResult();
            if (iCheckNaviGuide == 1) {
                isNavigatingResult.setIsNavigating(true);
                iResponseSender.send(new Response("OK", isNavigatingResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isNavigating】, result【OK】");
                RLog.i(TAG, "isNavigating: OK+result==" + isNavigatingResult);
                return;
            }
            if (iCheckNaviGuide == 0) {
                isNavigatingResult.setIsNavigating(false);
                iResponseSender.send(new Response("OK", isNavigatingResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isNavigating】, result【OK】");
                RLog.i(TAG, "isNavigating: OK_result==" + isNavigatingResult);
                return;
            }
            RLog.e(TAG, "isNavigating: ERROR1");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isNavigating】, result【ERROR】");
        } catch (Exception e) {
            RLog.e(TAG, "isNavigating: ERROR2 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isNavigating】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void stopGuidance(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter stopGuidance");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【stopGuidance】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【stopGuidance】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            int iReqNaviGuideStop = NaviHelper.getInstance().reqNaviGuideStop();
            RLog.i(TAG, "stopGuidance: reqNaviGuideStop==" + iReqNaviGuideStop);
            if (iReqNaviGuideStop == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【stopGuidance】, result【OK】");
                RLog.i(TAG, "stopGuidance: OK");
            } else if (-2 == iReqNaviGuideStop) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【stopGuidance】, result【UNSUPPORTED】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【stopGuidance】, result【ERROR】");
                RLog.d(TAG, "stopGuidance: ERROR1");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【stopGuidance】, result【ERROR】");
            RLog.e(TAG, "stopGuidance: ERROR2 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void setDefaultRouteOption(SetDefaultRouteOptionParam setDefaultRouteOptionParam, IResponseSender<BaseResult> iResponseSender) {
        int iReqNaviSetRouteOption;
        RLog.i(TAG, "enter setDefaultRouteOption");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            boolean zCheckRouteOptionListSupported = NaviHelper.getInstance().checkRouteOptionListSupported();
            ArrayList arrayList = new ArrayList();
            arrayList.add(setDefaultRouteOptionParam.getRouteOption());
            String str = "";
            if (arrayList.size() > 0) {
                RLog.i(TAG, "setDefaultRouteOption: lstRouteOption.size() ==" + arrayList.size());
                str = (String) arrayList.get(0);
            } else {
                RLog.i(TAG, "setDefaultRouteOption: lstRouteOption.size() == 0");
                if (arrayList.size() == 0) {
                    arrayList.add("UNSPECIFIED");
                }
            }
            int i = 1;
            if (zCheckRouteOptionListSupported) {
                int[] iArr = new int[arrayList.size()];
                int i2 = 0;
                while (i2 < arrayList.size()) {
                    String str2 = (String) arrayList.get(i2);
                    if (!TextUtils.isEmpty(str2)) {
                        if (RouteOptionEnum.FASTEST.equals(str2)) {
                            iArr[i2] = i;
                        } else if (RouteOptionEnum.AVOID_TOLLS.equals(str2)) {
                            if (NaviHelper.getInstance().getNaviProvider() == i) {
                                iArr[i2] = 2;
                            } else {
                                iArr[i2] = 4;
                            }
                        } else if (RouteOptionEnum.AVOID_HIGHWAY.equals(str2)) {
                            iArr[i2] = 3;
                        } else if (RouteOptionEnum.SHORTEST.equals(str2)) {
                            iArr[i2] = 14;
                        } else if (RouteOptionEnum.HIGHWAY.equals(str2)) {
                            iArr[i2] = 13;
                        } else if (RouteOptionEnum.RECOMMENDED.equals(str2)) {
                            iArr[i2] = 12;
                        } else if (RouteOptionEnum.AVOID_UNPAVED.equals(str2)) {
                            iArr[i2] = 5;
                        } else if (RouteOptionEnum.AVOID_TUNNEL.equals(str2)) {
                            iArr[i2] = 6;
                        } else if (RouteOptionEnum.AVOID_CARPOOL.equals(str2)) {
                            iArr[i2] = 7;
                        } else if (RouteOptionEnum.AVOID_COUNTRY_BORDER.equals(str2)) {
                            iArr[i2] = 8;
                        } else if (RouteOptionEnum.AVOID_FERRIES.equals(str2)) {
                            iArr[i2] = 9;
                        } else if (RouteOptionEnum.AVOID_CONGESTION.equals(str2)) {
                            iArr[i2] = 10;
                        } else {
                            iArr[i2] = 0;
                        }
                    } else {
                        iArr[i2] = 0;
                    }
                    RLog.i(TAG, "setDefaultRouteOption: RouteOption==" + iArr[i2]);
                    i2++;
                    i = 1;
                }
                iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOptions(iArr, false);
            } else {
                RLog.i(TAG, "setDefaultRouteOption: setDefaultRouteOptionParam.getRouteOption()==" + str);
                if (!TextUtils.isEmpty(str)) {
                    if (RouteOptionEnum.FASTEST.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(1, false);
                    } else if (RouteOptionEnum.AVOID_TOLLS.equals(str)) {
                        if (NaviHelper.getInstance().getNaviProvider() == 1) {
                            iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(2, false);
                        } else {
                            iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(4, false);
                        }
                    } else if (RouteOptionEnum.AVOID_HIGHWAY.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(3, false);
                    } else if (RouteOptionEnum.SHORTEST.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(14, false);
                    } else if (RouteOptionEnum.HIGHWAY.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(13, false);
                    } else if (RouteOptionEnum.RECOMMENDED.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(12, false);
                    } else if (RouteOptionEnum.AVOID_UNPAVED.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(5, false);
                    } else if (RouteOptionEnum.AVOID_TUNNEL.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(6, false);
                    } else if (RouteOptionEnum.AVOID_CARPOOL.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(7, false);
                    } else if (RouteOptionEnum.AVOID_COUNTRY_BORDER.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(8, false);
                    } else if (RouteOptionEnum.AVOID_FERRIES.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(9, false);
                    } else if (RouteOptionEnum.AVOID_CONGESTION.equals(str)) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(10, false);
                    } else {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(0, false);
                    }
                } else {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(0, false);
                }
            }
            RLog.i(TAG, "setDefaultRouteOption: routeOptionStatus==" + iReqNaviSetRouteOption);
            if (iReqNaviSetRouteOption != 1) {
                if (iReqNaviSetRouteOption == 2) {
                    RLog.i(TAG, "getRouteOption: ALREADY_EXPECTED");
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【OK】");
                    if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                        NaviHelper.getInstance().reqOpenRouteOption();
                        return;
                    }
                    return;
                }
                if (-2 == iReqNaviSetRouteOption) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【UNSUPPORTED】");
                } else {
                    RLog.i(TAG, "getRouteOption: routeOptionStatus ERROR2");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【ERROR】");
                    if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                        NaviHelper.getInstance().reqOpenRouteOption();
                        return;
                    }
                    return;
                }
            }
            NaviHelper.getInstance().startWait();
            RLog.i(TAG, "setDefaultRouteOption: getOnReqNaviSetRouteOption==" + NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode());
            if (NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode() == 1) {
                if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                    NaviHelper.getInstance().reqOpenRouteOption();
                }
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【OK】");
                RLog.i(TAG, "setDefaultRouteOption: DefaultRouteOption_OK");
                return;
            }
            if (-2 == NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode()) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【UNSUPPORTED】");
                return;
            }
            if (NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode() == 2) {
                if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                    NaviHelper.getInstance().reqOpenRouteOption();
                }
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【OK】");
                RLog.i(TAG, "setDefaultRouteOption: DefaultRouteOption_ALREADY_EXPECTED");
                return;
            }
            if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                NaviHelper.getInstance().reqOpenRouteOption();
            }
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【ERROR】");
            RLog.i(TAG, "setDefaultRouteOption: ERROR2");
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setDefaultRouteOption】, result【ERROR】");
            RLog.e(TAG, "setDefaultRouteOption: ERROR e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getRouteOption(IResponseSender<GetRouteOptionResult> iResponseSender) {
        RLog.i(TAG, "enter getRouteOption");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            GetRouteOptionResult getRouteOptionResult = new GetRouteOptionResult();
            int iReqNaviGetRouteOption = NaviHelper.getInstance().reqNaviGetRouteOption();
            RLog.i(TAG, "getRouteOption: reqNaviGetRouteOption==" + iReqNaviGetRouteOption);
            if (iReqNaviGetRouteOption != 1) {
                if (-2 == iReqNaviGetRouteOption) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【UNSUPPORTED】");
                    return;
                } else {
                    RLog.i(TAG, "getRouteOption: ERROR1");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【ERROR】");
                    return;
                }
            }
            NaviHelper.getInstance().startWait();
            int onReqNaviGetRouteOptionResultCode = NaviHelper.getInstance().getOnReqNaviGetRouteOptionResultCode();
            RLog.i(TAG, "getRouteOption: getOnReqNaviGetRouteOptionResultCode==" + onReqNaviGetRouteOptionResultCode);
            if (1 != onReqNaviGetRouteOptionResultCode) {
                if (-2 == onReqNaviGetRouteOptionResultCode) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【UNSUPPORTED】");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【ERROR】");
                    RLog.i(TAG, "getRouteOption: ERROR2");
                    return;
                }
            }
            if (NaviHelper.getInstance().checkRouteOptionListSupported()) {
                List<String> routeOptions = NaviHelper.getInstance().getRouteOptions();
                if (routeOptions == null) {
                    routeOptions = new ArrayList<>();
                }
                if (routeOptions.size() == 0) {
                    routeOptions.add("UNSPECIFIED");
                }
                getRouteOptionResult.setRouteOption(routeOptions.get(0));
                iResponseSender.send(new Response("OK", getRouteOptionResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【OK】");
                RLog.i(TAG, "getRouteOption_OK_result3==" + getRouteOptionResult);
                return;
            }
            String routeOption = NaviHelper.getInstance().getRouteOption();
            ArrayList arrayList = new ArrayList();
            RLog.i(TAG, "getRouteOption: getRouteOption==" + routeOption);
            if (!TextUtils.isEmpty(routeOption)) {
                arrayList.add(routeOption);
                RLog.i(TAG, "getRouteOption_OK_result1==");
            } else {
                arrayList.add("UNSPECIFIED");
                RLog.i(TAG, "getRouteOption_OK_result2==");
            }
            getRouteOptionResult.setRouteOption((String) arrayList.get(0));
            iResponseSender.send(new Response("OK", getRouteOptionResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【OK】");
        } catch (Exception e) {
            RLog.e(TAG, "getRouteOption: ERROR4 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRouteOption】, result【ERROR】");
        }
    }

    /* JADX WARN: Not initialized variable reg: 26, insn: 0x031a: MOVE (r2 I:??[OBJECT, ARRAY]) = (r26 I:??[OBJECT, ARRAY]), block:B:84:0x0318 */
    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getWaypoints(IResponseSender<GetWaypointsResult> iResponseSender) {
        String str;
        String str2;
        String str3;
        Iterator<Place> it;
        String str4;
        double distance;
        double distance2;
        String str5 = ResultCodeEnum.ERROR;
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getWaypoints】");
        RLog.i(TAG, "enter getWaypoints");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
        } catch (Exception e) {
            e = e;
            str = "request domain【navi】, api【getWaypoints】, result【ERROR】";
            str2 = ResultCodeEnum.ERROR;
        }
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getWaypoints】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        List<Place> listReqNaviViaPosition = NaviHelper.getInstance().reqNaviViaPosition();
        GetWaypointsResult getWaypointsResult = new GetWaypointsResult();
        ArrayList arrayList = new ArrayList();
        try {
            if (listReqNaviViaPosition != null) {
                try {
                    Iterator<Place> it2 = listReqNaviViaPosition.iterator();
                    while (it2.hasNext()) {
                        Place next = it2.next();
                        if (next != null) {
                            PlaceInfo placeInfo = new PlaceInfo();
                            placeInfo.setExtraInfo(next.getId());
                            placeInfo.setAddress(next.getAddress());
                            placeInfo.setCity(next.getCity());
                            placeInfo.setCountry(next.getCountry());
                            it = it2;
                            if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                                distance = next.getDistance() / 1000.0d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                            } else {
                                distance = next.getDistance() * 6.2137E-4d;
                                placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                            }
                            if (distance != 0.0d) {
                                str4 = str5;
                                distance = Math.round(distance * 1000.0d) / 1000.0d;
                            } else {
                                str4 = str5;
                            }
                            placeInfo.setDistance(distance);
                            placeInfo.setHouseNumber(next.getHouse_number());
                            placeInfo.setLatitude(next.getLatitude());
                            placeInfo.setLongitude(next.getLongitude());
                            placeInfo.setName(next.getName());
                            placeInfo.setState(next.getState());
                            if (next.getStreet() != null && next.getStreet().length > 0) {
                                placeInfo.setStreet(Arrays.asList(next.getStreet()));
                            }
                            if (next.getType() == 1) {
                                placeInfo.setType(PlaceTypeEnum.POI);
                            } else if (next.getType() == 2) {
                                placeInfo.setType(PlaceTypeEnum.ADDRESS);
                            }
                            RLog.s_log(TAG, "getWaypoints: waypoint=>" + placeInfo.toJsonString());
                            arrayList.add(placeInfo);
                        } else {
                            it = it2;
                            str4 = str5;
                        }
                        it2 = it;
                        str5 = str4;
                    }
                    getWaypointsResult.setWayPointsList(arrayList);
                    iResponseSender.send(new Response("OK", getWaypointsResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getWaypoints】, result【OK】");
                    RLog.i(TAG, "Waypoints size==" + arrayList.size());
                    RLog.i(TAG, "getWaypoints_OK_result1==" + getWaypointsResult);
                    return;
                } catch (Exception e2) {
                    e = e2;
                    str2 = str5;
                }
            } else {
                NaviHelper.getInstance().startWait();
                RLog.i(TAG, "getWaypoints: getOnNaviViaPositionResultCode==" + NaviHelper.getInstance().getOnNaviViaPositionResultCode());
                if (NaviHelper.getInstance().getOnNaviViaPositionResultCode() == 1) {
                    List<Place> list = NaviHelper.getInstance().getmPlaceList();
                    RLog.i(TAG, "getWaypoints: places==" + list.toString());
                    for (Place place : list) {
                        if (place != null) {
                            PlaceInfo placeInfo2 = new PlaceInfo();
                            placeInfo2.setExtraInfo(place.getId());
                            placeInfo2.setAddress(place.getAddress());
                            placeInfo2.setCity(place.getCity());
                            placeInfo2.setCountry(place.getCountry());
                            if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                                distance2 = place.getDistance() / 1000.0d;
                                placeInfo2.setUnitOfDistance(MileageUnitEnum.KM);
                            } else {
                                distance2 = place.getDistance() * 6.2137E-4d;
                                placeInfo2.setUnitOfDistance(MileageUnitEnum.MI);
                            }
                            if (distance2 != 0.0d) {
                                distance2 = Math.round(distance2 * 1000.0d) / 1000.0d;
                            }
                            placeInfo2.setDistance(distance2);
                            placeInfo2.setHouseNumber(place.getHouse_number());
                            placeInfo2.setLatitude(place.getLatitude());
                            placeInfo2.setLongitude(place.getLongitude());
                            placeInfo2.setName(place.getName());
                            placeInfo2.setState(place.getState());
                            if (place.getStreet() != null && place.getStreet().length > 0) {
                                placeInfo2.setStreet(Arrays.asList(place.getStreet()));
                            }
                            if (place.getType() == 1) {
                                placeInfo2.setType(PlaceTypeEnum.POI);
                            } else if (place.getType() == 2) {
                                placeInfo2.setType(PlaceTypeEnum.ADDRESS);
                            }
                            arrayList.add(placeInfo2);
                        }
                    }
                    RLog.s_log(TAG, "getWaypoints: listWayPoints==" + arrayList.toString());
                    getWaypointsResult.setWayPointsList(arrayList);
                    iResponseSender.send(new Response("OK", getWaypointsResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getWaypoints】, result【OK】");
                    RLog.i(TAG, "getWaypoints_OK_result2==" + getWaypointsResult.toJsonString());
                    return;
                }
                if (-2 == NaviHelper.getInstance().getOnNaviViaPositionResultCode()) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getWaypoints】, result【UNSUPPORTED】");
                    return;
                }
                RLog.i(TAG, "getWaypoints: ERROR1");
                str2 = ResultCodeEnum.ERROR;
                try {
                    iResponseSender.send(new Response(str2));
                    str = "request domain【navi】, api【getWaypoints】, result【ERROR】";
                    try {
                        RLog.i(Constant.TAG_FORTEST, str);
                        return;
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Exception e4) {
                    e = e4;
                    str = "request domain【navi】, api【getWaypoints】, result【ERROR】";
                }
            }
            str = "request domain【navi】, api【getWaypoints】, result【ERROR】";
        } catch (Exception e5) {
            e = e5;
            str = "request domain【navi】, api【getWaypoints】, result【ERROR】";
            str2 = str3;
        }
        RLog.e(TAG, "getWaypoints: ERROR2 e==>" + e.toString(), e);
        iResponseSender.send(new Response(str2));
        RLog.i(Constant.TAG_FORTEST, str);
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getMaxWaypoints(IResponseSender<GetMaxWaypointsResult> iResponseSender) {
        RLog.i(TAG, "enter getMaxWaypoints");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        GetMaxWaypointsResult getMaxWaypointsResult = new GetMaxWaypointsResult();
        int i = this.maxViaCount;
        if (i > 0) {
            getMaxWaypointsResult.setNumber(i);
            iResponseSender.send(new Response("OK", getMaxWaypointsResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】, result【OK】");
            RLog.i(TAG, "getMaxWaypoints response OK");
            return;
        }
        try {
            int naviMaxViaCount = NaviHelper.getInstance().getNaviMaxViaCount();
            RLog.i(TAG, "getMaxWaypoints: getNaviMaxViaCount==" + naviMaxViaCount);
            if (naviMaxViaCount != -100) {
                if (-2 == NaviHelper.getInstance().getOnNaviViaPositionResultCode()) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】, result【UNSUPPORTED】");
                    return;
                } else {
                    getMaxWaypointsResult.setNumber(naviMaxViaCount);
                    iResponseSender.send(new Response("OK", getMaxWaypointsResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】, result【OK】");
                    RLog.i(TAG, "getMaxWaypoints_OK");
                    return;
                }
            }
            RLog.i(TAG, "getMaxWaypoints: ERROR1 ");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】, result【ERROR】");
        } catch (Exception e) {
            RLog.e(TAG, "getMaxWaypoints: ERROR2 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getMaxWaypoints】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void searchPlaceByCategory(SearchPlaceByCategoryParam searchPlaceByCategoryParam, IResponseSender<SearchPlaceByCategoryResult> iResponseSender) {
        int i;
        RLog.i(TAG, "enter searchPlaceByCategory");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByCategory】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        int iMappingRule = NavUtil.mappingRule(searchPlaceByCategoryParam.getCategory());
        String locationReference = searchPlaceByCategoryParam.getLocationReference();
        String placeName = searchPlaceByCategoryParam.getPlaceName();
        RLog.i(TAG, "searchPlaceByCategory: categoryId==" + iMappingRule + " , locationReference==" + locationReference + " , placeName==" + placeName);
        locationReference.hashCode();
        i = 3;
        switch (locationReference) {
            case "OFFICE":
            case "HOME":
            case "DESTINATION":
                i = 2;
                break;
            case "CURRENT_LOCATION":
                i = 1;
                break;
            case "PLACE":
                break;
            case "ROUTE":
                i = 4;
                break;
            default:
                i = -100;
                break;
        }
        RLog.i(TAG, "searchPlaceByCategory: locationType==" + i);
        if (iMappingRule == 0) {
            RLog.i(TAG, "searchPlaceByCategory: reqPOISearchByKeywordInCategories");
            if (TextUtils.isEmpty(searchPlaceByCategoryParam.getCategoryLiteral())) {
                NaviHelper.getInstance().reqPOISearchByKeywordInCategories(searchPlaceByCategoryParam.getCategory(), iResponseSender);
                return;
            } else {
                NaviHelper.getInstance().reqPOISearchByKeywordInCategories(searchPlaceByCategoryParam.getCategoryLiteral(), iResponseSender);
                return;
            }
        }
        if (4 == i) {
            RLog.i(TAG, "searchPlaceByCategory: reqNaviAlongTheWaySearch");
            NaviHelper.getInstance().reqNaviAlongTheWaySearch(iMappingRule, iResponseSender);
        } else {
            RLog.i(TAG, "searchPlaceByCategory: reqPOISearchByCategories");
            NaviHelper.getInstance().reqPOISearchByCategories(iMappingRule, i, placeName, iResponseSender);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void searchPlaceByName(SearchPlaceByNameParam searchPlaceByNameParam, IResponseSender<SearchPlaceByNameResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【searchPlaceByName】, result【NAVI_AUTH_INAVLID】");
        } else {
            RLog.i(TAG, "searchPlaceByName  getPlaceName==" + searchPlaceByNameParam.getPlaceName());
            NaviHelper.getInstance().reqPOISearchByKeyword(searchPlaceByNameParam.getPlaceName(), iResponseSender);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void zoomMap(ZoomMapParam zoomMapParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter zoomMap");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            String zoomOption = zoomMapParam.getZoomOption();
            RLog.i(TAG, "zoomMap: getZoomOption==" + zoomOption);
            if (ZoomOptionsEnum.ZOOM_IN.equals(zoomOption)) {
                int iReqNaviZoomIn = NaviHelper.getInstance().reqNaviZoomIn(false);
                RLog.i(TAG, "zoomMap: reqNaviZoomIn==" + iReqNaviZoomIn);
                if (iReqNaviZoomIn == 1) {
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【OK】");
                    RLog.i(TAG, "zoomMap: ZOOM_IN_OK");
                } else if (iReqNaviZoomIn == 3) {
                    iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MAX));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ALREADY_MAX】");
                    RLog.i(TAG, "zoomMap: ZOOM_IN_ALREADY_MAX_OK");
                } else if (-2 == iReqNaviZoomIn) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【UNSUPPORTED】");
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ERROR】");
                    RLog.i(TAG, "zoomMap: ZOOM_IN_ERROR");
                }
            } else if (ZoomOptionsEnum.ZOOM_OUT.equals(zoomOption)) {
                int iReqNaviZoomOut = NaviHelper.getInstance().reqNaviZoomOut(false);
                RLog.i(TAG, "zoomMap: reqNaviZoomOut==" + iReqNaviZoomOut);
                if (iReqNaviZoomOut == 1) {
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【OK】");
                    RLog.i(TAG, "zoomMap: ZOOM_OUT_OK");
                } else if (iReqNaviZoomOut == 4) {
                    iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MIN));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ALREADY_MIN】");
                    RLog.i(TAG, "zoomMap: ZOOM_OUT_ALREADY_MIN_OK");
                } else if (-2 == iReqNaviZoomOut) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【UNSUPPORTED】");
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ERROR】");
                    RLog.i(TAG, "zoomMap: ZOOM_OUT_ERROR");
                }
            } else if (ZoomOptionsEnum.ZOOM_IN_TO_MAX.equals(zoomOption)) {
                int iReqNaviZoomIn2 = NaviHelper.getInstance().reqNaviZoomIn(true);
                RLog.i(TAG, "zoomMap: reqNaviZoomIn==" + iReqNaviZoomIn2);
                if (iReqNaviZoomIn2 == 1) {
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【OK】");
                    RLog.i(TAG, "zoomMap: ZOOM_IN_TO_MAX_OK");
                } else if (iReqNaviZoomIn2 == 3) {
                    iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MAX));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ALREADY_MAX】");
                    RLog.i(TAG, "zoomMap: ZOOM_IN_ALREADY_MAX_OK");
                } else if (-2 == iReqNaviZoomIn2) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【UNSUPPORTED】");
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ERROR】");
                    RLog.i(TAG, "zoomMap: ZOOM_IN_TO_MAX_ERROR");
                }
            } else if (ZoomOptionsEnum.ZOOM_OUT_TO_MIN.equals(zoomOption)) {
                int iReqNaviZoomOut2 = NaviHelper.getInstance().reqNaviZoomOut(true);
                RLog.i(TAG, "zoomMap: reqNaviZoomOut==" + iReqNaviZoomOut2);
                if (iReqNaviZoomOut2 == 1) {
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【OK】");
                    RLog.i(TAG, "zoomMap: ZOOM_OUT_TO_MIN_OK");
                } else if (iReqNaviZoomOut2 == 4) {
                    iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MIN));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ALREADY_MIN】");
                    RLog.i(TAG, "zoomMap: ZOOM_OUT_ALREADY_MIN_OK");
                } else if (-2 == iReqNaviZoomOut2) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【UNSUPPORTED】");
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ERROR】");
                    RLog.i(TAG, "zoomMap: ZOOM_OUT_TO_MIN_ERROR");
                }
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ERROR】");
                RLog.i(TAG, "zoomMap: ERROR1");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【zoomMap】, result【ERROR】");
            RLog.e(TAG, "zoomMap: ERROR2 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void setAddressByType(SetAddressByTypeParam setAddressByTypeParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter setAddressByType");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        int i = -100;
        try {
            if ("HOME".equals(setAddressByTypeParam.getAddressType())) {
                i = 1;
            } else if ("OFFICE".equals(setAddressByTypeParam.getAddressType())) {
                i = 2;
            } else if ("DESTINATION".equals(setAddressByTypeParam.getAddressType())) {
                i = 3;
            }
            RLog.i(TAG, "setAddressByType: addressType==" + setAddressByTypeParam.getAddressType());
            if (setAddressByTypeParam.getAddress() != null) {
                RLog.i(TAG, "setAddressByType: setAddressByTypeParam.getAddress()==xxx");
                RLog.s_log(TAG, "setAddressByType: setAddressByTypeParam.getAddress()==" + setAddressByTypeParam.getAddress().toJsonString());
            } else {
                RLog.i(TAG, "setAddressByType: setAddressByTypeParam.getAddress()==null");
            }
            Place place = new Place();
            place.setId(setAddressByTypeParam.getAddress().getExtraInfo());
            place.setAddress(setAddressByTypeParam.getAddress().getAddress());
            place.setCity(setAddressByTypeParam.getAddress().getCity());
            place.setCountry(setAddressByTypeParam.getAddress().getCountry());
            place.setDistance(setAddressByTypeParam.getAddress().getDistance());
            place.setHouse_number(setAddressByTypeParam.getAddress().getHouseNumber());
            place.setLatitude(setAddressByTypeParam.getAddress().getLatitude());
            place.setLongitude(setAddressByTypeParam.getAddress().getLongitude());
            place.setName(setAddressByTypeParam.getAddress().getName());
            place.setState(setAddressByTypeParam.getAddress().getState());
            place.setCategoryId(new String[]{"0"});
            if (setAddressByTypeParam.getAddress().getStreet() != null && setAddressByTypeParam.getAddress().getStreet().size() > 0) {
                place.setStreet((String[]) setAddressByTypeParam.getAddress().getStreet().toArray(new String[setAddressByTypeParam.getAddress().getStreet().size()]));
            }
            if (PlaceTypeEnum.POI.equals(setAddressByTypeParam.getAddress().getType())) {
                place.setType(1);
            } else if (PlaceTypeEnum.ADDRESS.equals(setAddressByTypeParam.getAddress().getType())) {
                place.setType(2);
            }
            RLog.s_log(TAG, "setAddressByType: place==" + place.toString());
            if (place.getLongitude() != 0.0d && place.getLatitude() != 0.0d) {
                RLog.i(TAG, "setAddressByType: PLACE_IS_EFFICIENT");
                int iReqSetAddress = NaviHelper.getInstance().reqSetAddress(i, place);
                RLog.i(TAG, "setAddressByType: reqSetAddress==" + iReqSetAddress);
                if (iReqSetAddress != 1) {
                    if (-2 == iReqSetAddress) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【UNSUPPORTED】");
                        return;
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        NaviHelper.getInstance().reqOpenSetAddress(i);
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【ERROR】");
                        RLog.i(TAG, "setAddressByType: ERROR2");
                        return;
                    }
                }
                NaviHelper.getInstance().startWait();
                RLog.i(TAG, "setAddressByType: getOnSetAddressResultCode==" + NaviHelper.getInstance().getOnSetAddressResultCode());
                if (NaviHelper.getInstance().getOnSetAddressResultCode() == 1) {
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【OK】");
                    RLog.i(TAG, "setAddressByType: OK");
                    return;
                } else if (-2 == NaviHelper.getInstance().getOnSetAddressResultCode()) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【UNSUPPORTED】");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    NaviHelper.getInstance().reqOpenSetAddress(i);
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【ERROR】");
                    RLog.i(TAG, "setAddressByType: ERROR1");
                    return;
                }
            }
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            NaviHelper.getInstance().reqOpenSetAddress(i);
            RLog.i(TAG, "setAddressByType: ERROR_PLACE_IS_INVALID");
        } catch (Exception e) {
            RLog.e(TAG, "setAddressByType: ERROR3 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            NaviHelper.getInstance().reqOpenSetAddress(-100);
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【setAddressByType】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getAddressByType(GetAddressByTypeParam getAddressByTypeParam, IResponseSender<GetAddressByTypeResult> iResponseSender) {
        Place currentPlace;
        double distance;
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】");
        RLog.i(TAG, "enter getAddressByType");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        RLog.i(TAG, "getAddressByType: getAddressType()==" + getAddressByTypeParam.getAddressType());
        try {
            if ("HOME".equals(getAddressByTypeParam.getAddressType())) {
                currentPlace = NaviHelper.getInstance().getHome();
                RLog.i(TAG, "getAddressByType: HOME");
            } else if ("OFFICE".equals(getAddressByTypeParam.getAddressType())) {
                currentPlace = NaviHelper.getInstance().getCompany();
                RLog.i(TAG, "getAddressByType: OFFICE");
            } else if ("DESTINATION".equals(getAddressByTypeParam.getAddressType())) {
                int iReqNaviInfo = NaviHelper.getInstance().reqNaviInfo();
                RLog.i(TAG, "getAddressByType: reqNaviInfo==" + iReqNaviInfo);
                if (iReqNaviInfo != 1) {
                    if (-2 == iReqNaviInfo) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【UNSUPPORTED】");
                        return;
                    } else {
                        RLog.i(TAG, "getAddressByType: ERROR1");
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【ERROR】");
                        return;
                    }
                }
                NaviHelper.getInstance().startWait();
                RLog.i(TAG, "getAddressByType: getOnReqNaviInfoResultCode==" + NaviHelper.getInstance().getOnReqNaviInfoResultCode());
                if (NaviHelper.getInstance().getOnReqNaviInfoResultCode() == 1) {
                    currentPlace = NaviHelper.getInstance().getDestinationPosition();
                    RLog.i(TAG, "getAddressByType: DESTINATION");
                } else {
                    currentPlace = null;
                }
            } else if (AddressTypeEnum.CURRENT.equals(getAddressByTypeParam.getAddressType())) {
                currentPlace = NaviHelper.getInstance().getCurrentPlace();
                RLog.i(TAG, "getAddressByType: CURRENT");
            } else {
                RLog.i(TAG, "getAddressByType: ERROR1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【ERROR】");
                return;
            }
            RLog.i(TAG, "getAddressByType: -------------------------------------1");
            if (currentPlace != null) {
                RLog.i(TAG, "getAddressByType: place !=null");
                GetAddressByTypeResult getAddressByTypeResult = new GetAddressByTypeResult();
                PlaceInfo placeInfo = new PlaceInfo();
                placeInfo.setExtraInfo(currentPlace.getId());
                placeInfo.setAddress(currentPlace.getAddress());
                placeInfo.setCity(currentPlace.getCity());
                placeInfo.setCountry(currentPlace.getCountry());
                placeInfo.setHouseNumber(currentPlace.getHouse_number());
                placeInfo.setLatitude(currentPlace.getLatitude());
                placeInfo.setLongitude(currentPlace.getLongitude());
                placeInfo.setName(currentPlace.getName());
                placeInfo.setState(currentPlace.getState());
                if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                    distance = currentPlace.getDistance() / 1000.0d;
                    placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                } else {
                    distance = currentPlace.getDistance() * 6.2137E-4d;
                    placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                }
                if (distance != 0.0d) {
                    distance = Math.round(distance * 1000.0d) / 1000.0d;
                }
                placeInfo.setDistance(distance);
                if (currentPlace.getStreet() != null && currentPlace.getStreet().length > 0) {
                    RLog.i(TAG, "getAddressByType: place.getStreet()==" + currentPlace.getStreet().toString());
                    placeInfo.setStreet(Arrays.asList(currentPlace.getStreet()));
                    RLog.i(TAG, "getAddressByType: info.getStreet()==" + placeInfo.getStreet().toString());
                }
                if (currentPlace.getType() == 1) {
                    placeInfo.setType(PlaceTypeEnum.POI);
                } else if (currentPlace.getType() == 2) {
                    placeInfo.setType(PlaceTypeEnum.ADDRESS);
                }
                getAddressByTypeResult.setPlaceInfo(placeInfo);
                this.mDesLat = currentPlace.getLatitude();
                this.mDesLon = currentPlace.getLongitude();
                RLog.s_log(TAG, "getAddressByType: OK_result_not_null==" + getAddressByTypeResult.toJsonString());
                iResponseSender.send(new Response("OK", getAddressByTypeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【OK】");
                return;
            }
            RLog.i(TAG, "getAddressByType: place==null");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【ERROR】");
        } catch (Exception e) {
            RLog.e(TAG, "getAddressByType: ERROR3 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getAddressByType】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getETA(GetETAParam getETAParam, IResponseSender<GetETAResult> iResponseSender) {
        RLog.i(TAG, "enter getETA");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        if (this.mDesLat == getETAParam.getLatitude()) {
            int i = (this.mDesLon > getETAParam.getLongitude() ? 1 : (this.mDesLon == getETAParam.getLongitude() ? 0 : -1));
        }
        RLog.s_log(TAG, "getETA: getETAParam.getLongitude()==" + getETAParam.getLongitude());
        RLog.s_log(TAG, "getETA: getETAParam.getLatitude()==" + getETAParam.getLatitude());
        try {
            int iReqNaviRemainTimeDistance = NaviHelper.getInstance().reqNaviRemainTimeDistance(true);
            RLog.i(TAG, "getETA: reqNaviRemainTimeDistance==" + iReqNaviRemainTimeDistance);
            if (iReqNaviRemainTimeDistance != 1) {
                if (-2 == iReqNaviRemainTimeDistance) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【UNSUPPORTED】");
                    return;
                } else {
                    RLog.i(TAG, "getETA: ERROR1");
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【ERROR】");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                }
            }
            NaviHelper.getInstance().startWait();
            RLog.i(TAG, "getETA: getOnNaviRemainTimeDistanceCode==" + NaviHelper.getInstance().getOnNaviRemainTimeDistanceCode());
            if (NaviHelper.getInstance().getOnNaviRemainTimeDistanceCode() == 1) {
                long remainTime = NaviHelper.getInstance().getRemainTime();
                RLog.i(TAG, "getETA: getRemainTime==" + remainTime);
                GetETAResult getETAResult = new GetETAResult();
                getETAResult.setDuration(remainTime);
                iResponseSender.send(new Response("OK", getETAResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【OK】");
                RLog.i(TAG, "getETA:  OK_result==" + getETAResult.toJsonString());
                return;
            }
            if (-2 == NaviHelper.getInstance().getOnNaviRemainTimeDistanceCode()) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【UNSUPPORTED】");
            } else {
                RLog.i(TAG, "getETA: ERROR2");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【ERROR】");
            }
        } catch (Exception e) {
            RLog.d(TAG, "getETA: ERROR4 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getETA】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getDistance(GetDistanceParam getDistanceParam, IResponseSender<GetDistanceResult> iResponseSender) {
        double dRound;
        RLog.i(TAG, "enter getDistance");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        if (NaviHelper.getInstance().checkNaviGuide() == 0) {
            iResponseSender.send(new Response(ResultCodeEnum.NOT_SUPPORTED_BY_VOICE));
            RLog.i(TAG, "request domain【navi】, api【getDistance】, result【NOT_SUPPORTED_BY_VOICE】");
            return;
        }
        if (this.mDesLat == getDistanceParam.getLatitude()) {
            int i = (this.mDesLon > getDistanceParam.getLongitude() ? 1 : (this.mDesLon == getDistanceParam.getLongitude() ? 0 : -1));
        }
        RLog.s_log(TAG, "getDistance: getDistanceParam.getLongitude()==" + getDistanceParam.getLongitude());
        RLog.s_log(TAG, "getDistance: getDistanceParam.getLatitude()==" + getDistanceParam.getLatitude());
        try {
            int iReqNaviRemainTimeDistance = NaviHelper.getInstance().reqNaviRemainTimeDistance(true);
            RLog.i(TAG, "getDistance: reqNaviRemainTimeDistance==" + iReqNaviRemainTimeDistance);
            if (iReqNaviRemainTimeDistance != 1) {
                if (-2 == iReqNaviRemainTimeDistance) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【UNSUPPORTED】");
                    return;
                } else {
                    RLog.i(TAG, "getDistance: ERROR1");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【ERROR】");
                }
            }
            NaviHelper.getInstance().startWait();
            RLog.i(TAG, "getDistance: getOnNaviRemainTimeDistanceCode==" + NaviHelper.getInstance().getOnNaviRemainTimeDistanceCode());
            if (NaviHelper.getInstance().getOnNaviRemainTimeDistanceCode() == 1) {
                double remainDistance = NaviHelper.getInstance().getRemainDistance();
                RLog.i(TAG, "getDistance: getRemainDistance==" + remainDistance);
                GetDistanceResult getDistanceResult = new GetDistanceResult();
                if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                    dRound = remainDistance / 1000.0d;
                    getDistanceResult.setUnitOfDistance(MileageUnitEnum.KM);
                } else {
                    dRound = remainDistance * 6.2137E-4d;
                    getDistanceResult.setUnitOfDistance(MileageUnitEnum.MI);
                }
                if (dRound != 0.0d) {
                    dRound = Math.round(dRound * 1000.0d) / 1000.0d;
                }
                getDistanceResult.setDistance(dRound);
                iResponseSender.send(new Response("OK", getDistanceResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【OK】");
                RLog.i(TAG, "getDistance: OK_result==" + getDistanceResult.toJsonString());
                return;
            }
            if (-2 == NaviHelper.getInstance().getOnNaviRemainTimeDistanceCode()) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【UNSUPPORTED】");
            } else {
                RLog.i(TAG, "getDistance: ERROR2");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【ERROR】");
            }
        } catch (Exception e) {
            RLog.d(TAG, "getDistance: ERROR4 e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getDistance】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getRecentList(IResponseSender<GetRecentListResult> iResponseSender) {
        RLog.i(TAG, "enter getRecentList");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            GetRecentListResult getRecentListResult = new GetRecentListResult();
            ArrayList arrayList = new ArrayList();
            int iCheckHistoryDestinationEmpty = NaviHelper.getInstance().checkHistoryDestinationEmpty();
            if (iCheckHistoryDestinationEmpty == 1) {
                RLog.i(TAG, "getRecentList: checkHistoryDestinationEmpty==" + iCheckHistoryDestinationEmpty);
                getRecentListResult.setRecentList(arrayList);
                iResponseSender.send(new Response("OK", getRecentListResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【OK】");
                return;
            }
            if (iCheckHistoryDestinationEmpty == -100) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【ERROR】");
                RLog.i(TAG, "getRecentList: ERROR1");
                return;
            }
            int iReqOpenHistoryDestination = NaviHelper.getInstance().reqOpenHistoryDestination();
            RLog.i(TAG, "getRecentList: reqOpenHistoryDestination==" + iReqOpenHistoryDestination);
            if (iReqOpenHistoryDestination != 1 && iReqOpenHistoryDestination != 2) {
                if (-2 == iReqOpenHistoryDestination) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【UNSUPPORTED】");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【ERROR】");
                    RLog.i(TAG, "getRecentList: ERROR2");
                    return;
                }
            }
            PlaceInfo placeInfo = new PlaceInfo();
            placeInfo.setExtraInfo("1");
            placeInfo.setAddress("1");
            placeInfo.setCity("1");
            placeInfo.setCountry("1");
            placeInfo.setDistance(1.0d);
            if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
            } else {
                placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
            }
            placeInfo.setHouseNumber("1");
            placeInfo.setLatitude(120.2d);
            placeInfo.setLongitude(123.2d);
            placeInfo.setName("1");
            placeInfo.setState("1");
            placeInfo.setType(PlaceTypeEnum.ADDRESS);
            arrayList.add(placeInfo);
            getRecentListResult.setRecentList(arrayList);
            iResponseSender.send(new Response("OK", getRecentListResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【OK】");
            RLog.i(TAG, "getRecentList: OK");
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getRecentList】, result【ERROR】");
            RLog.d(TAG, "getRecentList: ERROR3 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getSupportedRouteOptions(IResponseSender<GetSupportedRouteOptionsResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getSupportedRouteOptions】");
        RLog.i(TAG, "enter getSupportedRouteOptions");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getSupportedRouteOptions】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            List<String> supportedRouteOptions = NaviHelper.getInstance().getSupportedRouteOptions();
            if (supportedRouteOptions != null && supportedRouteOptions.size() > 0) {
                RLog.i(TAG, "getSupportedRouteOptions: options==" + supportedRouteOptions.toString());
                GetSupportedRouteOptionsResult getSupportedRouteOptionsResult = new GetSupportedRouteOptionsResult();
                ArrayList arrayList = new ArrayList();
                StringBuffer stringBuffer = new StringBuffer();
                for (String str : supportedRouteOptions) {
                    if (String.valueOf(1).equals(str)) {
                        RouteOptionInfo routeOptionInfo = new RouteOptionInfo();
                        routeOptionInfo.setRouteOption(RouteOptionEnum.FASTEST);
                        arrayList.add(routeOptionInfo);
                        stringBuffer.append("option:1(TASTEST)");
                    } else if (String.valueOf(4).equals(str)) {
                        RouteOptionInfo routeOptionInfo2 = new RouteOptionInfo();
                        routeOptionInfo2.setRouteOption(RouteOptionEnum.AVOID_TOLLS);
                        arrayList.add(routeOptionInfo2);
                        stringBuffer.append(",option:2(AVOID_TOLLS)");
                    } else if (String.valueOf(3).equals(str)) {
                        RouteOptionInfo routeOptionInfo3 = new RouteOptionInfo();
                        routeOptionInfo3.setRouteOption(RouteOptionEnum.AVOID_HIGHWAY);
                        arrayList.add(routeOptionInfo3);
                        stringBuffer.append(",option:3(AVOID_HIGHWAY)");
                    } else if (String.valueOf(14).equals(str)) {
                        RouteOptionInfo routeOptionInfo4 = new RouteOptionInfo();
                        routeOptionInfo4.setRouteOption(RouteOptionEnum.SHORTEST);
                        arrayList.add(routeOptionInfo4);
                        stringBuffer.append(",option:4(SHORTEST)");
                    } else if (String.valueOf(12).equals(str)) {
                        RouteOptionInfo routeOptionInfo5 = new RouteOptionInfo();
                        routeOptionInfo5.setRouteOption(RouteOptionEnum.RECOMMENDED);
                        arrayList.add(routeOptionInfo5);
                        stringBuffer.append(",option:5(RECOMMENDED)");
                    } else if (String.valueOf(13).equals(str)) {
                        RouteOptionInfo routeOptionInfo6 = new RouteOptionInfo();
                        routeOptionInfo6.setRouteOption(RouteOptionEnum.HIGHWAY);
                        arrayList.add(routeOptionInfo6);
                        stringBuffer.append(",option:6(HIGHWAY)");
                    } else if (String.valueOf(5).equals(str)) {
                        RouteOptionInfo routeOptionInfo7 = new RouteOptionInfo();
                        routeOptionInfo7.setRouteOption(RouteOptionEnum.AVOID_UNPAVED);
                        arrayList.add(routeOptionInfo7);
                        stringBuffer.append(",option:7(AVOID_UNPAVED)");
                    } else if (String.valueOf(6).equals(str)) {
                        RouteOptionInfo routeOptionInfo8 = new RouteOptionInfo();
                        routeOptionInfo8.setRouteOption(RouteOptionEnum.AVOID_TUNNEL);
                        arrayList.add(routeOptionInfo8);
                        stringBuffer.append(",option:8(AVOID_TUNNEL)");
                    } else if (String.valueOf(7).equals(str)) {
                        RouteOptionInfo routeOptionInfo9 = new RouteOptionInfo();
                        routeOptionInfo9.setRouteOption(RouteOptionEnum.AVOID_CARPOOL);
                        arrayList.add(routeOptionInfo9);
                        stringBuffer.append(",option:9(AVOID_CARPOOL)");
                    } else if (String.valueOf(8).equals(str)) {
                        RouteOptionInfo routeOptionInfo10 = new RouteOptionInfo();
                        routeOptionInfo10.setRouteOption(RouteOptionEnum.AVOID_COUNTRY_BORDER);
                        arrayList.add(routeOptionInfo10);
                        stringBuffer.append(",option:10(AVOID_COUNTRY_BORDER)");
                    } else if (String.valueOf(9).equals(str)) {
                        RouteOptionInfo routeOptionInfo11 = new RouteOptionInfo();
                        routeOptionInfo11.setRouteOption(RouteOptionEnum.AVOID_FERRIES);
                        arrayList.add(routeOptionInfo11);
                        stringBuffer.append(",option:11(AVOID_FERRIES)");
                    } else if (String.valueOf(10).equals(str)) {
                        RouteOptionInfo routeOptionInfo12 = new RouteOptionInfo();
                        routeOptionInfo12.setRouteOption(RouteOptionEnum.AVOID_CONGESTION);
                        arrayList.add(routeOptionInfo12);
                        stringBuffer.append(",option:12(AVOID_CONGESTION)");
                    }
                }
                getSupportedRouteOptionsResult.setRouteOptionList(arrayList);
                iResponseSender.send(new Response("OK", getSupportedRouteOptionsResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getSupportedRouteOptions】, result【OK】");
                RLog.i(TAG, "getSupportedRouteOptions: OK option size==>" + arrayList.size() + "," + stringBuffer.toString());
                return;
            }
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getSupportedRouteOptions】, result【ERROR】");
            RLog.i(TAG, "getSupportedRouteOptions: ERROR1");
        } catch (Exception e) {
            RLog.d(TAG, "getSupportedRouteOptions: ERROR2 e==>" + e.toString(), e);
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getSupportedRouteOptions】, result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:6|(2:10|(2:12|13)(2:14|(2:16|17)))|567|18|19|583|(1:21)(21:25|(1:27)|28|552|(5:563|30|(7:33|(4:565|35|(1:37)(2:39|(2:41|(1:43)(1:44))(2:45|(1:47)(2:48|(1:50)(2:51|(1:53)(2:54|(1:56)(2:57|(1:59)(2:60|(1:62)(2:63|(1:65)(2:66|(1:68)(2:69|(1:71)(2:72|(1:74)(1:75))))))))))))|38)(2:79|80)|81|82|83|84|31)|610|85)(5:91|554|92|(1:94)(3:95|(3:97|98|(1:100)(1:101))(2:104|(1:106)(2:108|(1:110)(2:111|(1:113)(2:114|(1:116)(2:117|(1:119)(2:120|(1:122)(2:123|(1:125)(2:126|(1:128)(2:129|(1:131)(2:132|(1:134)(1:135)))))))))))|107)|136)|137|(1:143)(1:141)|144|(2:146|147)(2:149|(2:151|152))|153|(7:156|(1:161)(1:160)|162|(1:164)(2:165|(1:167))|168|169|154)|586|170|171|(2:174|172)|587|176|558|(5:181|182|(4:(1:185)(1:186)|187|577|(6:189|546|(7:191|192|574|193|194|195|196)(7:199|200|578|201|202|203|204)|88|89|90)(2:209|(7:211|576|(2:213|(2:255|256)(4:217|(10:220|(1:222)(1:223)|224|(1:226)|227|(4:229|(3:231|SW:232|247)|591|248)(1:249)|250|(2:252|589)(1:590)|253|218)|588|254))(2:257|(4:318|319|320|321)(4:261|(13:264|265|544|266|(1:268)(1:269)|270|(1:272)|273|(1:275)(2:277|(1:279)(2:280|(1:282)(2:283|(1:285)(2:286|(1:288)(2:289|(1:291)(2:292|(1:294)(2:295|(1:297)(2:298|(1:300)(2:301|(1:303)(3:304|(2:306|608)(2:307|(2:309|609)(2:310|606))|311))))))))))|276|607|311|262)|605|312))|313|314|315|611)(2:324|(2:326|612)(2:327|615))))(5:331|(2:549|333)(2:556|337)|338|339|(2:341|(2:343|344)(2:345|346))(2:347|(10:349|(3:351|573|(12:353|354|355|(1:357)(1:358)|359|(1:361)|362|(1:367)(1:366)|368|(1:370)(2:372|(1:408)(2:377|(1:379)(2:380|(1:382)(2:383|(1:385)(2:386|(1:388)(2:389|(1:391)(2:392|(1:394)(2:395|(1:397)(2:398|(1:400)(2:401|(1:403)(2:404|(1:406)(1:407))))))))))))|371|409)(5:410|411|561|412|413))(4:418|547|419|(10:569|421|422|(2:585|424)(1:428)|429|(3:431|432|433)|434|435|(1:437)(3:439|440|(1:442)(3:444|445|(1:447)(3:449|450|(1:452)(3:454|455|(1:457)(3:459|460|(1:462)(3:464|465|(1:467)(3:469|470|(1:472)(3:474|475|(1:477)(3:479|480|(1:482)(3:484|485|(2:487|488)(3:490|491|(2:493|494)(2:496|497))))))))))))|498)(9:513|580|514|515|559|516|517|518|519))|499|500|571|501|502|550|503|616)(2:526|(2:528|613)(2:529|614))))|542|543)|532|617)|582|28|552|(0)(0)|137|(16:139|143|144|(0)(0)|153|(1:154)|586|170|171|(1:172)|587|176|558|(4:178|181|182|(0)(0))|532|617)(0)|542|543) */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0276, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03b0 A[Catch: Exception -> 0x0e73, TRY_ENTER, TryCatch #6 {Exception -> 0x0e73, blocks: (B:137:0x02ea, B:144:0x03b9, B:153:0x03e2, B:154:0x03f4, B:171:0x04be, B:172:0x04fb, B:176:0x0522, B:178:0x0542, B:181:0x054c, B:149:0x03ce, B:143:0x03b0, B:92:0x0256, B:136:0x02d5, B:95:0x0261, B:104:0x0278, B:108:0x0282, B:111:0x028b, B:114:0x0294, B:117:0x029d, B:120:0x02a5, B:123:0x02af, B:126:0x02b7, B:129:0x02c0, B:132:0x02c9), top: B:554:0x0256 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03ce A[Catch: Exception -> 0x0e73, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x0e73, blocks: (B:137:0x02ea, B:144:0x03b9, B:153:0x03e2, B:154:0x03f4, B:171:0x04be, B:172:0x04fb, B:176:0x0522, B:178:0x0542, B:181:0x054c, B:149:0x03ce, B:143:0x03b0, B:92:0x0256, B:136:0x02d5, B:95:0x0261, B:104:0x0278, B:108:0x0282, B:111:0x028b, B:114:0x0294, B:117:0x029d, B:120:0x02a5, B:123:0x02af, B:126:0x02b7, B:129:0x02c0, B:132:0x02c9), top: B:554:0x0256 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03fa A[Catch: Exception -> 0x0276, TRY_ENTER, TryCatch #5 {Exception -> 0x0276, blocks: (B:83:0x022c, B:139:0x0382, B:141:0x0390, B:147:0x03ca, B:156:0x03fa, B:158:0x0461, B:160:0x046b, B:162:0x048c, B:164:0x0498, B:168:0x04ad, B:165:0x049d, B:167:0x04a9, B:161:0x0483, B:174:0x0501, B:185:0x0561, B:187:0x0572, B:191:0x0593, B:186:0x056a, B:152:0x03df, B:97:0x0267), top: B:552:0x0142 }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0501 A[Catch: Exception -> 0x0276, LOOP:1: B:172:0x04fb->B:174:0x0501, LOOP_END, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x0276, blocks: (B:83:0x022c, B:139:0x0382, B:141:0x0390, B:147:0x03ca, B:156:0x03fa, B:158:0x0461, B:160:0x046b, B:162:0x048c, B:164:0x0498, B:168:0x04ad, B:165:0x049d, B:167:0x04a9, B:161:0x0483, B:174:0x0501, B:185:0x0561, B:187:0x0572, B:191:0x0593, B:186:0x056a, B:152:0x03df, B:97:0x0267), top: B:552:0x0142 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x09ab  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x024e  */
    /* JADX WARN: Type inference failed for: r12v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r12v22, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v111 */
    /* JADX WARN: Type inference failed for: r1v116 */
    /* JADX WARN: Type inference failed for: r1v118 */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.byd.naviserviceapi.Place] */
    /* JADX WARN: Type inference failed for: r1v129 */
    /* JADX WARN: Type inference failed for: r1v130 */
    /* JADX WARN: Type inference failed for: r1v131 */
    /* JADX WARN: Type inference failed for: r1v132 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v50 */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r1v63 */
    /* JADX WARN: Type inference failed for: r1v65 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r26v0 */
    /* JADX WARN: Type inference failed for: r26v1 */
    /* JADX WARN: Type inference failed for: r26v4 */
    /* JADX WARN: Type inference failed for: r28v0 */
    /* JADX WARN: Type inference failed for: r28v1 */
    /* JADX WARN: Type inference failed for: r28v11 */
    /* JADX WARN: Type inference failed for: r28v12 */
    /* JADX WARN: Type inference failed for: r28v13 */
    /* JADX WARN: Type inference failed for: r28v14 */
    /* JADX WARN: Type inference failed for: r28v15 */
    /* JADX WARN: Type inference failed for: r28v16 */
    /* JADX WARN: Type inference failed for: r28v17 */
    /* JADX WARN: Type inference failed for: r28v18 */
    /* JADX WARN: Type inference failed for: r28v19 */
    /* JADX WARN: Type inference failed for: r28v2 */
    /* JADX WARN: Type inference failed for: r28v20 */
    /* JADX WARN: Type inference failed for: r28v21 */
    /* JADX WARN: Type inference failed for: r28v22 */
    /* JADX WARN: Type inference failed for: r28v23 */
    /* JADX WARN: Type inference failed for: r28v24 */
    /* JADX WARN: Type inference failed for: r28v3 */
    /* JADX WARN: Type inference failed for: r28v4 */
    /* JADX WARN: Type inference failed for: r28v5 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v25, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r2v3, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v60 */
    /* JADX WARN: Type inference failed for: r2v68 */
    /* JADX WARN: Type inference failed for: r2v69 */
    /* JADX WARN: Type inference failed for: r2v70 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v101 */
    /* JADX WARN: Type inference failed for: r3v102 */
    /* JADX WARN: Type inference failed for: r3v103 */
    /* JADX WARN: Type inference failed for: r3v104, types: [com.byd.vrassistant.conversation.NaviHelper] */
    /* JADX WARN: Type inference failed for: r3v110 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v161, types: [com.byd.vrassistant.conversation.NaviHelper] */
    /* JADX WARN: Type inference failed for: r3v186 */
    /* JADX WARN: Type inference failed for: r3v188 */
    /* JADX WARN: Type inference failed for: r3v189 */
    /* JADX WARN: Type inference failed for: r3v190 */
    /* JADX WARN: Type inference failed for: r3v191 */
    /* JADX WARN: Type inference failed for: r3v192 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35, types: [int] */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v96, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v112 */
    /* JADX WARN: Type inference failed for: r4v113 */
    /* JADX WARN: Type inference failed for: r4v114 */
    /* JADX WARN: Type inference failed for: r4v115 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v50, types: [java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r4v52 */
    /* JADX WARN: Type inference failed for: r4v56 */
    /* JADX WARN: Type inference failed for: r4v58 */
    /* JADX WARN: Type inference failed for: r4v59, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v62 */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r4v66 */
    /* JADX WARN: Type inference failed for: r4v85 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v41, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v42 */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r5v44 */
    /* JADX WARN: Type inference failed for: r5v45 */
    /* JADX WARN: Type inference failed for: r5v46 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v50 */
    /* JADX WARN: Type inference failed for: r5v51 */
    /* JADX WARN: Type inference failed for: r5v52 */
    /* JADX WARN: Type inference failed for: r5v53 */
    /* JADX WARN: Type inference failed for: r5v54 */
    /* JADX WARN: Type inference failed for: r5v55 */
    /* JADX WARN: Type inference failed for: r5v56 */
    /* JADX WARN: Type inference failed for: r5v57 */
    /* JADX WARN: Type inference failed for: r5v58 */
    /* JADX WARN: Type inference failed for: r5v59 */
    /* JADX WARN: Type inference failed for: r5v60 */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v15, types: [com.byd.vrassistant.conversation.NaviHelper] */
    /* JADX WARN: Type inference failed for: r9v4, types: [com.byd.vrassistant.conversation.NaviHelper] */
    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void getRoutes(cerence.ark.assistant.controller.domain.ude.param.GetRoutesParam r38, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.ude.result.GetRoutesResult> r39) {
        /*
            Method dump skipped, instruction units count: 3782
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomNaviDomain.getRoutes(cerence.ark.assistant.controller.domain.ude.param.GetRoutesParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void startGuidance(StartGuidanceParam startGuidanceParam, IResponseSender<BaseResult> iResponseSender) {
        int iReqNaviGuideStart;
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【startGuidance】");
        RLog.i(TAG, "enter startGuidance");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【startGuidance】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            String routeId = startGuidanceParam.getRouteId();
            RLog.i(TAG, "startGuidance: startGuidanceParam.getRouteId()==" + routeId);
            if (TextUtils.isEmpty(routeId)) {
                iReqNaviGuideStart = NaviHelper.getInstance().reqSmartNaviStart(NaviHelper.getInstance().getDetailPOI());
            } else {
                iReqNaviGuideStart = NaviHelper.getInstance().reqNaviGuideStart(startGuidanceParam.getRouteId());
            }
            RLog.i(TAG, "startGuidance: reqNaviGuideStart==" + iReqNaviGuideStart);
            if (iReqNaviGuideStart == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【startGuidance】, result【OK】");
                RLog.i(TAG, "startGuidance: OK");
            } else if (-2 == iReqNaviGuideStart) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【startGuidance】, result【UNSUPPORTED】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【startGuidance】, result【ERROR】");
                RLog.i(TAG, "startGuidance: ERROR1");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【startGuidance】, result【ERROR】");
            RLog.d(TAG, "startGuidance: ERROR2 e==>" + e.toString(), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00c0  */
    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void setMapOption(cerence.ark.assistant.controller.domain.ude.param.SetMapOptionParam r21, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r22) {
        /*
            Method dump skipped, instruction units count: 1520
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomNaviDomain.setMapOption(cerence.ark.assistant.controller.domain.ude.param.SetMapOptionParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void addToFavorites(AddToFavoritesParam addToFavoritesParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】");
        RLog.i(TAG, "enter addToFavorites");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            RLog.s_log(TAG, "enter addToFavorites param = " + addToFavoritesParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            int iCheckFavoriteFull = NaviHelper.getInstance().checkFavoriteFull();
            RLog.i(TAG, "addToFavorites: checkFavoriteFull==" + iCheckFavoriteFull);
            if (iCheckFavoriteFull == 1) {
                iResponseSender.send(new Response(ResultCodeEnum.FAVORITE_LIST_FULL));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【FAVORITE_LIST_FULL】");
                RLog.i(TAG, "addToFavorites: FAVORITE_LIST_FULL");
                return;
            }
            int iReqNaviAddFavorite = NaviHelper.getInstance().reqNaviAddFavorite();
            RLog.i(TAG, "addToFavorites: reqNaviAddFavorite==" + iReqNaviAddFavorite);
            if (iReqNaviAddFavorite != 1) {
                if (-2 == iReqNaviAddFavorite) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【UNSUPPORTED】");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【ERROR】");
                    RLog.i(TAG, "addToFavorites: ERROR2");
                    return;
                }
            }
            NaviHelper.getInstance().startWait();
            RLog.i(TAG, "addToFavorites: getOnReqNaviAddFavoriteResultCode==" + NaviHelper.getInstance().getOnReqNaviAddFavoriteResultCode());
            if (NaviHelper.getInstance().getOnReqNaviAddFavoriteResultCode() == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【OK】");
                RLog.i(TAG, "addToFavorites: OK");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【ERROR】");
                RLog.i(TAG, "addToFavorites: ERROR1");
            }
        } catch (Exception e2) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【addToFavorites】, result【ERROR】");
            RLog.d(TAG, "addToFavorites: ERROR3 e==>" + e2.toString(), e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r1v1, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v56 */
    /* JADX WARN: Type inference failed for: r1v57 */
    /* JADX WARN: Type inference failed for: r1v58 */
    /* JADX WARN: Type inference failed for: r1v59 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v64 */
    /* JADX WARN: Type inference failed for: r1v67 */
    /* JADX WARN: Type inference failed for: r1v68, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r1v71 */
    /* JADX WARN: Type inference failed for: r1v72 */
    /* JADX WARN: Type inference failed for: r1v73 */
    /* JADX WARN: Type inference failed for: r1v74 */
    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void routeOverview(RouteOverviewParam routeOverviewParam, IResponseSender<BaseResult> iResponseSender) {
        String str;
        String str2;
        String str3;
        int iReqNaviRouteFullViewEx;
        String str4;
        ArrayList arrayList;
        String str5;
        ?? r1 = iResponseSender;
        String str6 = ResultCodeEnum.ERROR;
        RLog.i(TAG, "enter routeOverview");
        String str7 = Constant.TAG_FORTEST;
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【routeOverview】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            r1.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【routeOverview】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        if (this.isDetailPageSupported) {
            return;
        }
        try {
            boolean zCheckRouteOptionListSupported = NaviHelper.getInstance().checkRouteOptionListSupported();
            int highlightIndex = routeOverviewParam.getHighlightIndex();
            RLog.i(TAG, "routeOverview: routeOverviewParam.getHighlightIndex()==" + highlightIndex);
            String str8 = "UNSPECIFIED";
            String str9 = "routeOverview: routes.size()==";
            try {
                if (zCheckRouteOptionListSupported) {
                    try {
                        ArrayList arrayList2 = new ArrayList();
                        for (RouteInfo routeInfo : routeOverviewParam.getRouteList()) {
                            RouteInfo2 routeInfo2 = new RouteInfo2();
                            String str10 = str9;
                            routeInfo2.setDistance(routeInfo.getDistance());
                            routeInfo2.setId(routeInfo.getId());
                            routeInfo2.setLabel(routeInfo.getLabel());
                            routeInfo2.setTime(routeInfo.getDuration());
                            ArrayList arrayList3 = new ArrayList();
                            arrayList3.add(routeInfo.getRouteOption());
                            if (arrayList3.size() > 0) {
                                str4 = str7;
                                RLog.i(TAG, "routeOverview: lstRouteOption.size() ==" + arrayList3.size());
                            } else {
                                str4 = str7;
                                RLog.i(TAG, "routeOverview: lstRouteOption.size() == 0");
                                if (arrayList3.size() == 0) {
                                    arrayList3.add(str8);
                                }
                            }
                            int[] iArr = new int[arrayList3.size()];
                            String str11 = str8;
                            int i = 0;
                            while (i < arrayList3.size()) {
                                String str12 = (String) arrayList3.get(i);
                                if (!TextUtils.isEmpty(str12)) {
                                    if (RouteOptionEnum.FASTEST.equals(str12)) {
                                        iArr[i] = 1;
                                        arrayList = arrayList3;
                                    } else if (RouteOptionEnum.AVOID_TOLLS.equals(str12)) {
                                        arrayList = arrayList3;
                                        if (NaviHelper.getInstance().getNaviProvider() == 1) {
                                            iArr[i] = 2;
                                        } else {
                                            iArr[i] = 4;
                                        }
                                    } else {
                                        arrayList = arrayList3;
                                        if (RouteOptionEnum.AVOID_HIGHWAY.equals(str12)) {
                                            iArr[i] = 3;
                                        } else if (RouteOptionEnum.SHORTEST.equals(str12)) {
                                            iArr[i] = 14;
                                        } else if (RouteOptionEnum.HIGHWAY.equals(str12)) {
                                            iArr[i] = 13;
                                        } else if (RouteOptionEnum.RECOMMENDED.equals(str12)) {
                                            iArr[i] = 12;
                                        } else if (RouteOptionEnum.AVOID_UNPAVED.equals(str12)) {
                                            iArr[i] = 5;
                                        } else if (RouteOptionEnum.AVOID_TUNNEL.equals(str12)) {
                                            iArr[i] = 6;
                                        } else if (RouteOptionEnum.AVOID_CARPOOL.equals(str12)) {
                                            iArr[i] = 7;
                                        } else if (RouteOptionEnum.AVOID_COUNTRY_BORDER.equals(str12)) {
                                            iArr[i] = 8;
                                        } else if (RouteOptionEnum.AVOID_FERRIES.equals(str12)) {
                                            iArr[i] = 9;
                                        } else if (RouteOptionEnum.AVOID_CONGESTION.equals(str12)) {
                                            iArr[i] = 10;
                                        } else {
                                            iArr[i] = 0;
                                        }
                                    }
                                } else {
                                    arrayList = arrayList3;
                                    iArr[i] = 0;
                                }
                                RLog.i(TAG, "routeOverview: RouteOption==" + iArr[i]);
                                i++;
                                arrayList3 = arrayList;
                            }
                            routeInfo2.setRouteOption(iArr);
                            arrayList2.add(routeInfo2);
                            str7 = str4;
                            str9 = str10;
                            str8 = str11;
                        }
                        str3 = str7;
                        RLog.i(TAG, str9 + arrayList2.size());
                        iReqNaviRouteFullViewEx = NaviHelper.getInstance().reqNaviRouteFullViewEx(arrayList2, highlightIndex);
                    } catch (Exception e) {
                        e = e;
                        r1 = iResponseSender;
                        str2 = str7;
                        str = "request domain【navi】, api【routeOverview】, result【ERROR】";
                        str6 = ResultCodeEnum.ERROR;
                        r1.send(new Response(str6));
                        RLog.i(str2, str);
                        RLog.d(TAG, "routeOverview: ERROR2 e==>" + e.toString(), e);
                    }
                } else {
                    str3 = Constant.TAG_FORTEST;
                    ArrayList arrayList4 = new ArrayList();
                    for (RouteInfo routeInfo3 : routeOverviewParam.getRouteList()) {
                        com.byd.naviserviceapi.RouteInfo routeInfo4 = new com.byd.naviserviceapi.RouteInfo();
                        int i2 = highlightIndex;
                        routeInfo4.setDistance(routeInfo3.getDistance());
                        routeInfo4.setId(routeInfo3.getId());
                        routeInfo4.setLabel(routeInfo3.getLabel());
                        routeInfo4.setTime(routeInfo3.getDuration());
                        ArrayList arrayList5 = new ArrayList();
                        arrayList5.add(routeInfo3.getRouteOption());
                        if (arrayList5.size() > 0) {
                            RLog.i(TAG, "routeOverview: lstRouteOption.size() ==" + arrayList5.size());
                            str5 = (String) arrayList5.get(0);
                        } else {
                            str5 = "UNSPECIFIED";
                        }
                        if (RouteOptionEnum.FASTEST.equals(str5)) {
                            routeInfo4.setRouteOption(1);
                        } else {
                            if (RouteOptionEnum.AVOID_TOLLS.equals(str5)) {
                                if (NaviHelper.getInstance().getNaviProvider() == 1) {
                                    routeInfo4.setRouteOption(2);
                                } else {
                                    routeInfo4.setRouteOption(4);
                                }
                            } else if (RouteOptionEnum.AVOID_HIGHWAY.equals(str5)) {
                                routeInfo4.setRouteOption(3);
                            } else if (RouteOptionEnum.SHORTEST.equals(str5)) {
                                routeInfo4.setRouteOption(14);
                            } else if (RouteOptionEnum.HIGHWAY.equals(str5)) {
                                routeInfo4.setRouteOption(13);
                            } else if (RouteOptionEnum.RECOMMENDED.equals(str5)) {
                                routeInfo4.setRouteOption(12);
                            } else if (RouteOptionEnum.AVOID_UNPAVED.equals(str5)) {
                                routeInfo4.setRouteOption(5);
                            } else if (RouteOptionEnum.AVOID_TUNNEL.equals(str5)) {
                                routeInfo4.setRouteOption(6);
                            } else if (RouteOptionEnum.AVOID_CARPOOL.equals(str5)) {
                                routeInfo4.setRouteOption(7);
                            } else if (RouteOptionEnum.AVOID_COUNTRY_BORDER.equals(str5)) {
                                routeInfo4.setRouteOption(8);
                            } else if (RouteOptionEnum.AVOID_FERRIES.equals(str5)) {
                                routeInfo4.setRouteOption(9);
                            } else if (RouteOptionEnum.AVOID_CONGESTION.equals(str5)) {
                                routeInfo4.setRouteOption(10);
                            }
                            arrayList4.add(routeInfo4);
                            highlightIndex = i2;
                        }
                        arrayList4.add(routeInfo4);
                        highlightIndex = i2;
                    }
                    RLog.i(TAG, "routeOverview: routes.size()==" + arrayList4.size());
                    iReqNaviRouteFullViewEx = NaviHelper.getInstance().reqNaviRouteFullView(arrayList4, highlightIndex);
                }
                str2 = "routeOverview: reqNaviRouteFullView==";
                RLog.i(TAG, "routeOverview: reqNaviRouteFullView==" + iReqNaviRouteFullViewEx);
                r1 = 1;
                try {
                    if (iReqNaviRouteFullViewEx == 1) {
                        try {
                            NaviHelper.getInstance().startWait();
                            RLog.i(TAG, "routeOverview: getOnRouteFullViewCode()==" + NaviHelper.getInstance().getOnRouteFullViewCode());
                            if (NaviHelper.getInstance().getOnRouteFullViewCode() == 1) {
                                IResponseSender<BaseResult> iResponseSender2 = iResponseSender;
                                try {
                                    iResponseSender2.send(new Response("OK"));
                                    str2 = str3;
                                    RLog.i(str2, "request domain【navi】, api【routeOverview】, result【OK】");
                                    RLog.i(TAG, "routeOverview: OK");
                                    r1 = iResponseSender2;
                                } catch (Exception e2) {
                                    e = e2;
                                    r1 = iResponseSender2;
                                    str2 = str3;
                                    str = "request domain【navi】, api【routeOverview】, result【ERROR】";
                                    str6 = ResultCodeEnum.ERROR;
                                    r1.send(new Response(str6));
                                    RLog.i(str2, str);
                                    RLog.d(TAG, "routeOverview: ERROR2 e==>" + e.toString(), e);
                                }
                            } else {
                                r1 = iResponseSender;
                                str2 = str3;
                                RLog.i(TAG, "routeOverview: ERROR1");
                            }
                        } catch (Exception e3) {
                            e = e3;
                            r1 = iResponseSender;
                        }
                    } else {
                        r1 = iResponseSender;
                        str2 = str3;
                        if (-2 == iReqNaviRouteFullViewEx) {
                            r1.send(new Response("UNSUPPORTED"));
                            RLog.i(str2, "request domain【navi】, api【routeOverview】, result【UNSUPPORTED】");
                            r1 = r1;
                        } else {
                            str6 = ResultCodeEnum.ERROR;
                            try {
                                r1.send(new Response(str6));
                                str = "request domain【navi】, api【routeOverview】, result【ERROR】";
                            } catch (Exception e4) {
                                e = e4;
                                str = "request domain【navi】, api【routeOverview】, result【ERROR】";
                            }
                            try {
                                RLog.i(str2, str);
                                RLog.i(TAG, "routeOverview: ERROR2");
                                r1 = r1;
                            } catch (Exception e5) {
                                e = e5;
                                r1.send(new Response(str6));
                                RLog.i(str2, str);
                                RLog.d(TAG, "routeOverview: ERROR2 e==>" + e.toString(), e);
                            }
                        }
                    }
                } catch (Exception e6) {
                    e = e6;
                }
            } catch (Exception e7) {
                e = e7;
                r1 = iResponseSender;
                str = "request domain【navi】, api【routeOverview】, result【ERROR】";
                str6 = ResultCodeEnum.ERROR;
                str2 = str3;
            }
        } catch (Exception e8) {
            e = e8;
            str = "request domain【navi】, api【routeOverview】, result【ERROR】";
            str2 = Constant.TAG_FORTEST;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0087 A[Catch: Exception -> 0x00d5, TryCatch #0 {Exception -> 0x00d5, blocks: (B:6:0x0040, B:8:0x0061, B:13:0x006d, B:15:0x0087, B:19:0x0091, B:21:0x00a3, B:22:0x00c4, B:17:0x008d, B:10:0x0067), top: B:27:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a3 A[Catch: Exception -> 0x00d5, TryCatch #0 {Exception -> 0x00d5, blocks: (B:6:0x0040, B:8:0x0061, B:13:0x006d, B:15:0x0087, B:19:0x0091, B:21:0x00a3, B:22:0x00c4, B:17:0x008d, B:10:0x0067), top: B:27:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c4 A[Catch: Exception -> 0x00d5, TRY_LEAVE, TryCatch #0 {Exception -> 0x00d5, blocks: (B:6:0x0040, B:8:0x0061, B:13:0x006d, B:15:0x0087, B:19:0x0091, B:21:0x00a3, B:22:0x00c4, B:17:0x008d, B:10:0x0067), top: B:27:0x0040 }] */
    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void getFavoritesListStatus(cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.ude.result.GetFavoritesListStatusResult> r14) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomNaviDomain.getFavoritesListStatus(cerence.ark.assistant.api.IResponseSender):void");
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void openFavoritesList(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openFavoritesList】");
        RLog.i(TAG, "enter openFavoritesList");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openFavoritesList】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            int iReqSwitchFavorite = NaviHelper.getInstance().reqSwitchFavorite(1);
            RLog.i(TAG, "openFavoritesList: reqSwitchFavorite==" + iReqSwitchFavorite);
            if (iReqSwitchFavorite == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openFavoritesList】, result【OK】");
                RLog.i(TAG, "openFavoritesList: OK");
            } else if (-2 == iReqSwitchFavorite) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openFavoritesList】, result【UNSUPPORTED】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openFavoritesList】, result【ERROR】");
                RLog.i(TAG, "openFavoritesList: ERROR1");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【openFavoritesList】, result【ERROR】");
            RLog.d(TAG, "openFavoritesList: ERROR2 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void closeFavoritesList(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【closeFavoritesList】");
        RLog.i(TAG, "enter closeFavoritesList");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【closeFavoritesList】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            int iReqSwitchFavorite = NaviHelper.getInstance().reqSwitchFavorite(0);
            RLog.i(TAG, "closeFavoritesList: reqSwitchFavorite==" + iReqSwitchFavorite);
            if (iReqSwitchFavorite == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【closeFavoritesList】, result【OK】");
                RLog.i(TAG, "closeFavoritesList: OK");
            } else if (-2 == iReqSwitchFavorite) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【closeFavoritesList】, result【UNSUPPORTED】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【closeFavoritesList】, result【ERROR】");
                RLog.i(TAG, "closeFavoritesList: ERROR1");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【closeFavoritesList】, result【ERROR】");
            RLog.d(TAG, "closeFavoritesList: ERROR2 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void changeWaypoints(ChangeWaypointsParam changeWaypointsParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            RLog.s_log(TAG, "enter changeWaypoints param = " + changeWaypointsParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<PlaceInfo> waypoints = changeWaypointsParam.getWaypoints();
        ArrayList arrayList = new ArrayList();
        for (PlaceInfo placeInfo : waypoints) {
            Place place = new Place();
            place.setId(placeInfo.getExtraInfo());
            place.setName(placeInfo.getName());
            place.setAddress(placeInfo.getAddress());
            place.setLongitude(placeInfo.getLongitude());
            place.setLatitude(placeInfo.getLatitude());
            place.setDistance(placeInfo.getDistance());
            place.setCountry(placeInfo.getCountry());
            place.setState(placeInfo.getState());
            place.setCity(placeInfo.getCity());
            if (placeInfo.getStreet() == null) {
                place.setStreet(new String[1]);
            } else {
                place.setStreet(new String[placeInfo.getStreet().size()]);
            }
            place.setHouse_number(placeInfo.getHouseNumber());
            if (PlaceTypeEnum.POI == placeInfo.getType()) {
                place.setType(1);
            } else if (PlaceTypeEnum.ADDRESS == placeInfo.getType()) {
                place.setType(2);
            }
            place.setCategoryId(new String[]{"0"});
            arrayList.add(place);
        }
        if (arrayList.size() > 0) {
            if (!this.isDetailPageSupported) {
                int iAddViaPosition = NaviHelper.getInstance().addViaPosition(arrayList);
                if (iAddViaPosition != 1) {
                    if (-2 == iAddViaPosition) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【UNSUPPORTED】");
                        return;
                    } else {
                        RLog.i(TAG, "add via result error ,result=" + iAddViaPosition);
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
                        return;
                    }
                }
                NaviHelper.getInstance().startWait();
                if (NaviHelper.getInstance().getAddViaPositionCode() == 1) {
                    iResponseSender.send(new Response("OK"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【OK】");
                    RLog.i(TAG, "addViaPosition OK");
                    return;
                } else if (NaviHelper.getInstance().getAddViaPositionCode() == 2) {
                    iResponseSender.send(new Response(ResultCodeEnum.AUTO_ROUTE_SUPPORTED));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【AUTO_ROUTE_SUPPORTED】");
                    RLog.i(TAG, "addViaPosition AUTO_ROUTE_SUPPORTED");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
                    RLog.i(TAG, "addViaPosition responseCode ERROR result=" + NaviHelper.getInstance().getAddViaPositionCode());
                    return;
                }
            }
            if (NaviHelper.getInstance().getPOIDetailStatus() == 2) {
                int iReqDetailPOIAsVia = NaviHelper.getInstance().reqDetailPOIAsVia((Place) arrayList.get(0));
                if (iReqDetailPOIAsVia != 1) {
                    if (-2 == iReqDetailPOIAsVia) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【UNSUPPORTED】");
                        return;
                    } else {
                        RLog.i(TAG, "add via result error ,result=" + iReqDetailPOIAsVia);
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
                        return;
                    }
                }
                NaviHelper.getInstance().startWaitRoutePlanning();
                if (NaviHelper.getInstance().getRoutePlanningUpdateCode() == 1) {
                    iResponseSender.send(new Response(ResultCodeEnum.AUTO_ROUTE_SUPPORTED));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【OK】");
                    RLog.i(TAG, "addViaPosition OK");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
                    RLog.i(TAG, "addViaPosition responseCode ERROR result=" + NaviHelper.getInstance().getRoutePlanningUpdateCode());
                    return;
                }
            }
            if (NaviHelper.getInstance().getPOIDetailStatus() == 1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            int iAddViaPosition2 = NaviHelper.getInstance().addViaPosition(arrayList);
            if (iAddViaPosition2 != 1) {
                if (-2 == iAddViaPosition2) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【UNSUPPORTED】");
                    return;
                } else {
                    RLog.i(TAG, "add via result error ,result=" + iAddViaPosition2);
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
                    return;
                }
            }
            NaviHelper.getInstance().startWait();
            if (NaviHelper.getInstance().getAddViaPositionCode() == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【OK】");
                RLog.i(TAG, "addViaPosition OK");
                return;
            } else if (NaviHelper.getInstance().getAddViaPositionCode() == 2 || NaviHelper.getInstance().getAddViaPositionCode() == 11) {
                iResponseSender.send(new Response(ResultCodeEnum.AUTO_ROUTE_SUPPORTED));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【AUTO_ROUTE_SUPPORTED】");
                RLog.i(TAG, "addViaPosition AUTO_ROUTE_SUPPORTED");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
                RLog.i(TAG, "addViaPosition responseCode ERROR result=" + NaviHelper.getInstance().getAddViaPositionCode());
                return;
            }
        }
        RLog.i(TAG, "target via zero，add via fail.");
        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeWaypoints】, result【ERROR】");
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void changeRouteOption(ChangeRouteOptionParam changeRouteOptionParam, IResponseSender<BaseResult> iResponseSender) {
        int iReqNaviSetRouteOption;
        ArrayList arrayList;
        RLog.i(TAG, "enter changeRouteOption");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】");
        VehicleSettingsHelper.getInstance();
        VehicleSettingsHelper.stopAppByForce("com.byd.avm");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            boolean zCheckRouteOptionListSupported = NaviHelper.getInstance().checkRouteOptionListSupported();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(changeRouteOptionParam.getRouteOption());
            String str = "";
            if (arrayList2.size() > 0) {
                RLog.i(TAG, "changeRouteOption: lstRouteOption.size() ==" + arrayList2.size());
                str = (String) arrayList2.get(0);
            } else {
                RLog.i(TAG, "changeRouteOption: lstRouteOption.size() == 0");
                if (arrayList2.size() == 0) {
                    arrayList2.add("UNSPECIFIED");
                }
            }
            if (zCheckRouteOptionListSupported) {
                int[] iArr = new int[arrayList2.size()];
                int i = 0;
                while (i < arrayList2.size()) {
                    String str2 = (String) arrayList2.get(i);
                    if (!TextUtils.isEmpty(str2)) {
                        if (RouteOptionEnum.FASTEST.equals(str2)) {
                            iArr[i] = 1;
                            arrayList = arrayList2;
                        } else if (RouteOptionEnum.AVOID_TOLLS.equals(str2)) {
                            arrayList = arrayList2;
                            if (NaviHelper.getInstance().getNaviProvider() == 1) {
                                iArr[i] = 2;
                            } else {
                                iArr[i] = 4;
                            }
                        } else {
                            arrayList = arrayList2;
                            if (RouteOptionEnum.AVOID_HIGHWAY.equals(str2)) {
                                iArr[i] = 3;
                            } else if (RouteOptionEnum.SHORTEST.equals(str2)) {
                                iArr[i] = 14;
                            } else if (RouteOptionEnum.HIGHWAY.equals(str2)) {
                                iArr[i] = 13;
                            } else if (RouteOptionEnum.RECOMMENDED.equals(str2)) {
                                iArr[i] = 12;
                            } else if (RouteOptionEnum.AVOID_UNPAVED.equals(str2)) {
                                iArr[i] = 5;
                            } else if (RouteOptionEnum.AVOID_TUNNEL.equals(str2)) {
                                iArr[i] = 6;
                            } else if (RouteOptionEnum.AVOID_CARPOOL.equals(str2)) {
                                iArr[i] = 7;
                            } else if (RouteOptionEnum.AVOID_COUNTRY_BORDER.equals(str2)) {
                                iArr[i] = 8;
                            } else if (RouteOptionEnum.AVOID_FERRIES.equals(str2)) {
                                iArr[i] = 9;
                            } else if (RouteOptionEnum.AVOID_CONGESTION.equals(str2)) {
                                iArr[i] = 10;
                            } else {
                                iArr[i] = 0;
                            }
                        }
                    } else {
                        arrayList = arrayList2;
                        iArr[i] = 0;
                    }
                    RLog.i(TAG, "changeRouteOption: RouteOption==" + iArr[i]);
                    i++;
                    arrayList2 = arrayList;
                }
                iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOptions(iArr, false);
            } else if (!TextUtils.isEmpty(str)) {
                if (RouteOptionEnum.FASTEST.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(1, false);
                } else if (RouteOptionEnum.AVOID_TOLLS.equals(str)) {
                    if (NaviHelper.getInstance().getNaviProvider() == 1) {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(2, false);
                    } else {
                        iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(4, false);
                    }
                } else if (RouteOptionEnum.AVOID_HIGHWAY.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(3, false);
                } else if (RouteOptionEnum.SHORTEST.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(14, false);
                } else if (RouteOptionEnum.HIGHWAY.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(13, false);
                } else if (RouteOptionEnum.RECOMMENDED.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(12, false);
                } else if (RouteOptionEnum.AVOID_UNPAVED.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(5, false);
                } else if (RouteOptionEnum.AVOID_TUNNEL.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(6, false);
                } else if (RouteOptionEnum.AVOID_CARPOOL.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(7, false);
                } else if (RouteOptionEnum.AVOID_COUNTRY_BORDER.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(8, false);
                } else if (RouteOptionEnum.AVOID_FERRIES.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(9, false);
                } else if (RouteOptionEnum.AVOID_CONGESTION.equals(str)) {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(10, false);
                } else {
                    iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(0, false);
                }
            } else {
                iReqNaviSetRouteOption = NaviHelper.getInstance().reqNaviSetRouteOption(0, false);
            }
            RLog.i(TAG, "changeRouteOption: routeOptionStatus==" + iReqNaviSetRouteOption);
            if (iReqNaviSetRouteOption != 1) {
                if (iReqNaviSetRouteOption == 2) {
                    if (NaviHelper.getInstance().getNaviProvider() == 1) {
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【AUTO_ROUTE_SUPPORTED】");
                        RLog.i(TAG, "changeRouteOption getRouteOption: ALREADY_EXPECTED taiwei");
                    } else {
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【OK】");
                        RLog.i(TAG, "changeRouteOption getRouteOption: ALREADY_EXPECTED");
                    }
                    if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                        NaviHelper.getInstance().reqOpenRouteOption();
                        return;
                    }
                    return;
                }
                if (-2 == iReqNaviSetRouteOption) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【UNSUPPORTED】");
                    return;
                }
                RLog.i(TAG, "getRouteOption: routeOptionStatus ERROR2");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【ERROR】");
                if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                    NaviHelper.getInstance().reqOpenRouteOption();
                    return;
                }
                return;
            }
            NaviHelper.getInstance().startWait();
            RLog.i(TAG, "changeRouteOption: getOnReqNaviSetRouteOption==" + NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode());
            if (NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode() == 1) {
                if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                    NaviHelper.getInstance().reqOpenRouteOption();
                }
                iResponseSender.send(new Response(ResultCodeEnum.AUTO_ROUTE_SUPPORTED));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【AUTO_ROUTE_SUPPORTED】");
                RLog.i(TAG, "changeRouteOption: DefaultRouteOption_AUTO_ROUTE_SUPPORTED");
                return;
            }
            if (NaviHelper.getInstance().getOnReqNaviSetRouteOptionCode() == 2) {
                if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                    NaviHelper.getInstance().reqOpenRouteOption();
                }
                if (NaviHelper.getInstance().getNaviProvider() == 1) {
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【AUTO_ROUTE_SUPPORTED】");
                    RLog.i(TAG, "changeRouteOption: DefaultRouteOption_ALREADY_EXPECTED taiwei");
                    return;
                } else {
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【OK】");
                    RLog.i(TAG, "changeRouteOption: DefaultRouteOption_ALREADY_EXPECTED");
                    return;
                }
            }
            if (NaviHelper.getInstance().checkNaviGuide() != 1) {
                NaviHelper.getInstance().reqOpenRouteOption();
            }
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【ERROR】");
            RLog.i(TAG, "changeRouteOption: ERROR2");
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【changeRouteOption】, result【ERROR】");
            RLog.e(TAG, "changeRouteOption: ERROR e==>" + e.toString(), e);
        }
    }

    /* JADX WARN: Not initialized variable reg: 22, insn: 0x04e1: MOVE (r3 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY]), block:B:143:0x04e1 */
    /* JADX WARN: Not initialized variable reg: 23, insn: 0x04e3: MOVE (r2 I:??[OBJECT, ARRAY]) = (r23 I:??[OBJECT, ARRAY]), block:B:143:0x04e1 */
    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void getUnfinishedRoute(IResponseSender<GetUnfinishedRouteResult> iResponseSender) {
        String str;
        String str2;
        String str3;
        String str4;
        double distance;
        double distance2;
        double distance3;
        double distance4;
        String str5 = "request domain【navi】, api【getUnfinishedRoute】, result【ERROR】";
        String str6 = ResultCodeEnum.ERROR;
        RLog.i(TAG, "enter getUnfinishedRoute");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            int unfinishedRoute = NaviHelper.getInstance().getUnfinishedRoute();
            RLog.i(TAG, "getUnfinishedRoute: getUnfinishedRoute==" + unfinishedRoute);
            if (unfinishedRoute != 1) {
                if (-2 == unfinishedRoute) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】, result【UNSUPPORTED】");
                    return;
                } else {
                    RLog.i(TAG, "getUnfinishedRoute: ERROR1");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】, result【ERROR】");
                    return;
                }
            }
            NaviHelper.getInstance().startWait();
            RLog.i(TAG, "getUnfinishedRoute: getOnReqUnfinishedRouteCode==" + NaviHelper.getInstance().getOnReqUnfinishedRouteCode());
            try {
                if (NaviHelper.getInstance().getOnReqUnfinishedRouteCode() == 9) {
                    boolean zCheckRouteOptionListSupported = NaviHelper.getInstance().checkRouteOptionListSupported();
                    Place destinationPositionUnfinished = NaviHelper.getInstance().getDestinationPositionUnfinished();
                    GetUnfinishedRouteResult getUnfinishedRouteResult = new GetUnfinishedRouteResult();
                    List<String> arrayList = new ArrayList<>();
                    if (zCheckRouteOptionListSupported) {
                        List<String> routeOptionUnfinishedEx = NaviHelper.getInstance().getRouteOptionUnfinishedEx();
                        if (routeOptionUnfinishedEx == null) {
                            routeOptionUnfinishedEx = new ArrayList<>();
                        }
                        arrayList = routeOptionUnfinishedEx;
                        if (arrayList.size() == 0) {
                            arrayList.add("UNSPECIFIED");
                        }
                    } else {
                        String routeOptionUnfinished = NaviHelper.getInstance().getRouteOptionUnfinished();
                        if (TextUtils.isEmpty(routeOptionUnfinished)) {
                            arrayList.add("UNSPECIFIED");
                        } else {
                            arrayList.add(routeOptionUnfinished);
                        }
                    }
                    getUnfinishedRouteResult.setRouteOption(arrayList.get(0));
                    PlaceInfo placeInfo = new PlaceInfo();
                    placeInfo.setExtraInfo(destinationPositionUnfinished.getId());
                    placeInfo.setAddress(destinationPositionUnfinished.getAddress());
                    placeInfo.setCity(destinationPositionUnfinished.getCity());
                    placeInfo.setCountry(destinationPositionUnfinished.getCountry());
                    placeInfo.setHouseNumber(destinationPositionUnfinished.getHouse_number());
                    placeInfo.setLatitude(destinationPositionUnfinished.getLatitude());
                    placeInfo.setLongitude(destinationPositionUnfinished.getLongitude());
                    placeInfo.setName(destinationPositionUnfinished.getName());
                    placeInfo.setState(destinationPositionUnfinished.getState());
                    if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                        distance3 = destinationPositionUnfinished.getDistance() / 1000.0d;
                        placeInfo.setUnitOfDistance(MileageUnitEnum.KM);
                    } else {
                        distance3 = destinationPositionUnfinished.getDistance() * 6.2137E-4d;
                        placeInfo.setUnitOfDistance(MileageUnitEnum.MI);
                    }
                    if (distance3 != 0.0d) {
                        distance3 = Math.round(distance3 * 1000.0d) / 1000.0d;
                    }
                    placeInfo.setDistance(distance3);
                    if (destinationPositionUnfinished.getStreet() != null && destinationPositionUnfinished.getStreet().length > 0) {
                        placeInfo.setStreet(Arrays.asList(destinationPositionUnfinished.getStreet()));
                    }
                    if (destinationPositionUnfinished.getType() == 1) {
                        placeInfo.setType(PlaceTypeEnum.POI);
                    } else if (destinationPositionUnfinished.getType() == 2) {
                        placeInfo.setType(PlaceTypeEnum.ADDRESS);
                    }
                    getUnfinishedRouteResult.setDestination(placeInfo);
                    ArrayList arrayList2 = new ArrayList();
                    for (Place place : NaviHelper.getInstance().getmViaPositionsUnfinished()) {
                        PlaceInfo placeInfo2 = new PlaceInfo();
                        placeInfo2.setExtraInfo(place.getId());
                        placeInfo2.setAddress(place.getAddress());
                        placeInfo2.setCity(place.getCity());
                        placeInfo2.setCountry(place.getCountry());
                        placeInfo2.setHouseNumber(place.getHouse_number());
                        String str7 = str5;
                        String str8 = str6;
                        placeInfo2.setLatitude(place.getLatitude());
                        placeInfo2.setLongitude(place.getLongitude());
                        placeInfo2.setName(place.getName());
                        placeInfo2.setState(place.getState());
                        if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                            distance4 = place.getDistance() / 1000.0d;
                            placeInfo2.setUnitOfDistance(MileageUnitEnum.KM);
                        } else {
                            distance4 = place.getDistance() * 6.2137E-4d;
                            placeInfo2.setUnitOfDistance(MileageUnitEnum.MI);
                        }
                        if (distance4 != 0.0d) {
                            distance4 = Math.round(distance4 * 1000.0d) / 1000.0d;
                        }
                        placeInfo2.setDistance(distance4);
                        if (place.getStreet() != null && place.getStreet().length > 0) {
                            placeInfo2.setStreet(Arrays.asList(place.getStreet()));
                        }
                        if (place.getType() == 1) {
                            placeInfo2.setType(PlaceTypeEnum.POI);
                        } else if (place.getType() == 2) {
                            placeInfo2.setType(PlaceTypeEnum.ADDRESS);
                        }
                        arrayList2.add(placeInfo2);
                        str5 = str7;
                        str6 = str8;
                    }
                    getUnfinishedRouteResult.setWaypoints(arrayList2);
                    iResponseSender.send(new Response("UNSUPPORTED", getUnfinishedRouteResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】, result【RESUME_NEEDROUTE】");
                    RLog.s_log(TAG, "getUnfinishedRoute: OK_result==" + getUnfinishedRouteResult.toJsonString());
                    return;
                }
                if (NaviHelper.getInstance().getOnReqUnfinishedRouteCode() == 10) {
                    boolean zCheckRouteOptionListSupported2 = NaviHelper.getInstance().checkRouteOptionListSupported();
                    Place destinationPositionUnfinished2 = NaviHelper.getInstance().getDestinationPositionUnfinished();
                    GetUnfinishedRouteResult getUnfinishedRouteResult2 = new GetUnfinishedRouteResult();
                    List<String> arrayList3 = new ArrayList<>();
                    if (zCheckRouteOptionListSupported2) {
                        List<String> routeOptionUnfinishedEx2 = NaviHelper.getInstance().getRouteOptionUnfinishedEx();
                        if (routeOptionUnfinishedEx2 == null) {
                            routeOptionUnfinishedEx2 = new ArrayList<>();
                        }
                        arrayList3 = routeOptionUnfinishedEx2;
                        if (arrayList3.size() == 0) {
                            arrayList3.add("UNSPECIFIED");
                        }
                    } else {
                        String routeOptionUnfinished2 = NaviHelper.getInstance().getRouteOptionUnfinished();
                        if (TextUtils.isEmpty(routeOptionUnfinished2)) {
                            arrayList3.add("UNSPECIFIED");
                        } else {
                            arrayList3.add(routeOptionUnfinished2);
                        }
                    }
                    getUnfinishedRouteResult2.setRouteOption(arrayList3.get(0));
                    PlaceInfo placeInfo3 = new PlaceInfo();
                    placeInfo3.setExtraInfo(destinationPositionUnfinished2.getId());
                    placeInfo3.setAddress(destinationPositionUnfinished2.getAddress());
                    placeInfo3.setCity(destinationPositionUnfinished2.getCity());
                    placeInfo3.setCountry(destinationPositionUnfinished2.getCountry());
                    placeInfo3.setHouseNumber(destinationPositionUnfinished2.getHouse_number());
                    placeInfo3.setLatitude(destinationPositionUnfinished2.getLatitude());
                    placeInfo3.setLongitude(destinationPositionUnfinished2.getLongitude());
                    placeInfo3.setName(destinationPositionUnfinished2.getName());
                    placeInfo3.setState(destinationPositionUnfinished2.getState());
                    if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                        distance = destinationPositionUnfinished2.getDistance() / 1000.0d;
                        placeInfo3.setUnitOfDistance(MileageUnitEnum.KM);
                    } else {
                        distance = destinationPositionUnfinished2.getDistance() * 6.2137E-4d;
                        placeInfo3.setUnitOfDistance(MileageUnitEnum.MI);
                    }
                    if (distance != 0.0d) {
                        distance = Math.round(distance * 1000.0d) / 1000.0d;
                    }
                    placeInfo3.setDistance(distance);
                    if (destinationPositionUnfinished2.getStreet() != null && destinationPositionUnfinished2.getStreet().length > 0) {
                        placeInfo3.setStreet(Arrays.asList(destinationPositionUnfinished2.getStreet()));
                    }
                    if (destinationPositionUnfinished2.getType() == 1) {
                        placeInfo3.setType(PlaceTypeEnum.POI);
                    } else if (destinationPositionUnfinished2.getType() == 2) {
                        placeInfo3.setType(PlaceTypeEnum.ADDRESS);
                    }
                    placeInfo3.setExtraInfo(ResultCodeEnum.AUTO_ROUTE_SUPPORTED);
                    getUnfinishedRouteResult2.setDestination(placeInfo3);
                    ArrayList arrayList4 = new ArrayList();
                    for (Place place2 : NaviHelper.getInstance().getmViaPositionsUnfinished()) {
                        PlaceInfo placeInfo4 = new PlaceInfo();
                        placeInfo4.setExtraInfo(place2.getId());
                        placeInfo4.setAddress(place2.getAddress());
                        placeInfo4.setCity(place2.getCity());
                        placeInfo4.setCountry(place2.getCountry());
                        placeInfo4.setHouseNumber(place2.getHouse_number());
                        placeInfo4.setLatitude(place2.getLatitude());
                        placeInfo4.setLongitude(place2.getLongitude());
                        placeInfo4.setName(place2.getName());
                        placeInfo4.setState(place2.getState());
                        if (VehicleHelper.getInstance().getLengthUnit() == 1) {
                            distance2 = place2.getDistance() / 1000.0d;
                            placeInfo4.setUnitOfDistance(MileageUnitEnum.KM);
                        } else {
                            distance2 = place2.getDistance() * 6.2137E-4d;
                            placeInfo4.setUnitOfDistance(MileageUnitEnum.MI);
                        }
                        if (distance2 != 0.0d) {
                            distance2 = Math.round(distance2 * 1000.0d) / 1000.0d;
                        }
                        placeInfo4.setDistance(distance2);
                        if (place2.getStreet() != null && place2.getStreet().length > 0) {
                            placeInfo4.setStreet(Arrays.asList(place2.getStreet()));
                        }
                        if (place2.getType() == 1) {
                            placeInfo4.setType(PlaceTypeEnum.POI);
                        } else if (place2.getType() == 2) {
                            placeInfo4.setType(PlaceTypeEnum.ADDRESS);
                        }
                        arrayList4.add(placeInfo4);
                    }
                    getUnfinishedRouteResult2.setWaypoints(arrayList4);
                    iResponseSender.send(new Response("OK", getUnfinishedRouteResult2));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】, result【RESUME_NOTROUTE】");
                    RLog.s_log(TAG, "getUnfinishedRoute: RESUME_NOTROUTE_result==" + getUnfinishedRouteResult2.toJsonString());
                    return;
                }
                if (NaviHelper.getInstance().getOnReqUnfinishedRouteCode() == 8) {
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【getUnfinishedRoute】, result【NO_UNFINISHEDROUTE】");
                    iResponseSender.send(new Response("OK", (BaseResult) null));
                    return;
                }
                RLog.i(TAG, "getUnfinishedRoute: ERROR2");
                str2 = ResultCodeEnum.ERROR;
                try {
                    iResponseSender.send(new Response(str2));
                    str = "request domain【navi】, api【getUnfinishedRoute】, result【ERROR】";
                    try {
                        RLog.i(Constant.TAG_FORTEST, str);
                    } catch (Exception e) {
                        e = e;
                        RLog.d(TAG, "getUnfinishedRoute: ERROR4 e==>" + e.toString(), e);
                        iResponseSender.send(new Response(str2));
                        RLog.i(Constant.TAG_FORTEST, str);
                    }
                } catch (Exception e2) {
                    e = e2;
                    str = "request domain【navi】, api【getUnfinishedRoute】, result【ERROR】";
                }
            } catch (Exception e3) {
                e = e3;
                str = str3;
                str2 = str4;
            }
        } catch (Exception e4) {
            e = e4;
            str = "request domain【navi】, api【getUnfinishedRoute】, result【ERROR】";
            str2 = ResultCodeEnum.ERROR;
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void resumeNavigation(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter resumeNavigation");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【resumeNavigation】");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【resumeNavigation】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            int iResumeNavigation = NaviHelper.getInstance().resumeNavigation();
            RLog.i(TAG, "resumeNavigation: resumeNavigation==" + iResumeNavigation);
            if (iResumeNavigation == 1) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【resumeNavigation】, result【OK】");
                RLog.i(TAG, "resumeNavigation: OK");
            } else if (-2 == iResumeNavigation) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【resumeNavigation】, result【UNSUPPORTED】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【resumeNavigation】, result【ERROR】");
                RLog.d(TAG, "resumeNavigation: ERROR1");
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【resumeNavigation】, result【ERROR】");
            RLog.e(TAG, "resumeNavigation: ERROR2 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void replaceDestination(ReplaceDestinationParam replaceDestinationParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter replaceDestination");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【replaceDestination】");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【replaceDestination】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            if (!this.isDetailPageSupported) {
                iResponseSender.send(new Response("OK"));
                return;
            }
            if (NaviHelper.getInstance().getPOIDetailStatus() == 2) {
                PlaceInfo newDestination = replaceDestinationParam.getNewDestination();
                Place place = new Place();
                place.setId(newDestination.getExtraInfo());
                place.setName(newDestination.getName());
                place.setAddress(newDestination.getAddress());
                place.setLongitude(newDestination.getLongitude());
                place.setLatitude(newDestination.getLatitude());
                place.setDistance(newDestination.getDistance());
                place.setCountry(newDestination.getCountry());
                place.setState(newDestination.getState());
                place.setCity(newDestination.getCity());
                if (newDestination.getStreet() == null) {
                    place.setStreet(new String[1]);
                } else {
                    place.setStreet(new String[newDestination.getStreet().size()]);
                }
                place.setHouse_number(newDestination.getHouseNumber());
                if (PlaceTypeEnum.POI == newDestination.getType()) {
                    place.setType(1);
                } else if (PlaceTypeEnum.ADDRESS == newDestination.getType()) {
                    place.setType(2);
                }
                place.setCategoryId(new String[]{"0"});
                int iReqDetailPOIAsDestination = NaviHelper.getInstance().reqDetailPOIAsDestination(place);
                RLog.i(TAG, "reqDetailPOIAsDestination==" + iReqDetailPOIAsDestination);
                if (iReqDetailPOIAsDestination != 1) {
                    if (-2 == iReqDetailPOIAsDestination) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【reqDetailPOIAsDestination】, result【UNSUPPORTED】");
                        return;
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【reqDetailPOIAsDestination】, result【ERROR】");
                        RLog.i(TAG, "getRoutes: ERROR1");
                        return;
                    }
                }
                NaviHelper.getInstance().startWaitRoutePlanning();
                int routePlanningUpdateCode = NaviHelper.getInstance().getRoutePlanningUpdateCode();
                RLog.i(TAG, "replaceDestination: replaceDestination==" + routePlanningUpdateCode);
                if (routePlanningUpdateCode == 1) {
                    iResponseSender.send(new Response(ResultCodeEnum.AUTO_ROUTE_SUPPORTED));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【replaceDestination】, result【OK】");
                    RLog.i(TAG, "replaceDestination: OK");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【replaceDestination】, result【ERROR】");
                    RLog.d(TAG, "replaceDestination: ERROR1");
                    return;
                }
            }
            if (NaviHelper.getInstance().getPOIDetailStatus() == 1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception e) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【replaceDestination】, result【ERROR】");
            RLog.e(TAG, "replaceDestination: ERROR2 e==>" + e.toString(), e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void checkDuplicateRoutePlace(CheckDuplicateRoutePlaceParam checkDuplicateRoutePlaceParam, IResponseSender<CheckDuplicateRoutePlaceResult> iResponseSender) {
        boolean z;
        RLog.i(TAG, "enter checkDuplicateRoutePlace");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【checkDuplicateRoutePlace】");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【checkDuplicateRoutePlace】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            PlaceInfo place = checkDuplicateRoutePlaceParam.getPlace();
            Iterator<PlaceInfo> it = checkDuplicateRoutePlaceParam.getRoutePlaceList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                PlaceInfo next = it.next();
                if (next.getName().equals(place.getName()) && next.getLatitude() == place.getLatitude() && next.getLongitude() == place.getLongitude()) {
                    z = true;
                    break;
                }
            }
            CheckDuplicateRoutePlaceResult checkDuplicateRoutePlaceResult = new CheckDuplicateRoutePlaceResult();
            checkDuplicateRoutePlaceResult.setIsDuplicate(z);
            iResponseSender.send(new Response("OK", checkDuplicateRoutePlaceResult));
        } catch (Exception e) {
            RLog.d(TAG, "checkDuplicateRoutePlace: ERROR e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【checkDuplicateRoutePlace】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void isDetailPageSupported(IResponseSender<IsDetailPageSupportedResult> iResponseSender) {
        RLog.i(TAG, "enter isDetailPageSupported");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isDetailPageSupported】");
        if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
            iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isDetailPageSupported】, result【NAVI_AUTH_INAVLID】");
            return;
        }
        try {
            IsDetailPageSupportedResult isDetailPageSupportedResult = new IsDetailPageSupportedResult();
            boolean zCheckDetailPageSupported = NaviHelper.getInstance().checkDetailPageSupported();
            this.isDetailPageSupported = zCheckDetailPageSupported;
            isDetailPageSupportedResult.setIsSupported(zCheckDetailPageSupported);
            iResponseSender.send(new Response("OK", isDetailPageSupportedResult));
        } catch (Exception e) {
            RLog.d(TAG, "isDetailPageSupported: ERROR e==>" + e.toString(), e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【isDetailPageSupported】, result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.ude.AbsUDEDomain
    protected void checkNaviFunctionAvailability(CheckNaviFunctionAvailabilityParam checkNaviFunctionAvailabilityParam, IResponseSender<CheckNaviFunctionAvailabilityResult> iResponseSender) {
        CheckNaviFunctionAvailabilityResult naviFunctionAvailabilityResultExpTmap;
        CheckNaviFunctionAvailabilityResult checkNaviFunctionAvailabilityResult = new CheckNaviFunctionAvailabilityResult();
        try {
            if (NaviHelper.getInstance().checkNaviInstalled()) {
                String naviFunction = checkNaviFunctionAvailabilityParam.getNaviFunction();
                RLog.i(TAG, "checkNaviFunction strType: " + naviFunction);
                int naviProvider = NaviHelper.getInstance().getNaviProvider();
                if (NaviFunctionEnum.OPEN_MAP_APP_FUNC.equals(naviFunction) || NaviFunctionEnum.CLOSE_MAP_APP_FUNC.equals(naviFunction) || NaviFunctionEnum.STOP_NAVI_GUIDANCE_FUNC.equals(naviFunction) || NaviFunctionEnum.START_NAVI_GUIDANCE_FUNC.equals(naviFunction) || NaviFunctionEnum.NAVIGATE_TO_POI_FUNC.equals(naviFunction) || NaviFunctionEnum.NAIVGATE_TO_CAT_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResult(checkNaviFunctionAvailabilityResult, naviProvider);
                } else if (NaviFunctionEnum.ADD_WAYPOINT_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResultExpTmap(checkNaviFunctionAvailabilityResult, naviProvider);
                } else if (NaviFunctionEnum.GO_HOME_FUNC.equals(naviFunction) || NaviFunctionEnum.GO_WORK_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResult(checkNaviFunctionAvailabilityResult, naviProvider);
                } else if (NaviFunctionEnum.OPEN_ROUTE_OVERVIEW_FUNC.equals(naviFunction) || NaviFunctionEnum.CLOSE_ROUTE_OVERVIEW_FUNC.equals(naviFunction) || NaviFunctionEnum.ZOOM_IN_MAP_FUNC.equals(naviFunction) || NaviFunctionEnum.ZOOM_OUT_MAP_FUNC.equals(naviFunction) || NaviFunctionEnum.QUERY_ROUTE_TIME_INFO_FUNC.equals(naviFunction) || NaviFunctionEnum.QUERY_ROUTE_DISTANCE_INFO_FUNC.equals(naviFunction) || NaviFunctionEnum.RESUME_NAVI_GUIDANCE_FUNC.equals(naviFunction) || NaviFunctionEnum.DEL_WAYPOINT_FUNC.equals(naviFunction) || NaviFunctionEnum.SET_HOME_ADDRESS_FUNC.equals(naviFunction) || NaviFunctionEnum.SET_WORK_ADDRESS_FUNC.equals(naviFunction) || NaviFunctionEnum.OPEN_MAP_SETTING_FUNC.equals(naviFunction) || NaviFunctionEnum.CLOSE_MAP_SETTING_FUNC.equals(naviFunction) || NaviFunctionEnum.OPEN_CLOSE_MAP_FAVOURITE_LIST_FUNC.equals(naviFunction) || NaviFunctionEnum.SAVE_CURRENT_LOCATION_FUNC.equals(naviFunction) || NaviFunctionEnum.TAKING_ROUTE_OPTION_FUNC.equals(naviFunction) || NaviFunctionEnum.AVOIDING_ROUTE_OPTION_FUNC.equals(naviFunction) || NaviFunctionEnum.MAP_2D_MODE_FUNC.equals(naviFunction) || NaviFunctionEnum.MAP_3D_MODE_FUNC.equals(naviFunction) || NaviFunctionEnum.MAP_2D_HEADING_UP_MODE_FUNC.equals(naviFunction) || NaviFunctionEnum.MAP_2D_NORTH_UP_MODE_FUNC.equals(naviFunction) || NaviFunctionEnum.MAP_3D_HEADING_UP_MODE_FUNC.equals(naviFunction) || NaviFunctionEnum.OPEN_TRAFFIC_OVERVIEW_FUNC.equals(naviFunction) || NaviFunctionEnum.CLOSE_TRAFFIC_OVERVIEW_FUNC.equals(naviFunction) || NaviFunctionEnum.VIEW_HISTORY_DESTINATION_FUNC.equals(naviFunction) || NaviFunctionEnum.QUERY_DESTINATION_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResultExpTmap(checkNaviFunctionAvailabilityResult, naviProvider);
                } else if (NaviFunctionEnum.OPEN_ROUTE_OPTION_SETTING_FUNC.equals(naviFunction) || NaviFunctionEnum.CLOSE_ROUTE_OPTION_SETTING_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResultNEU(checkNaviFunctionAvailabilityResult, naviProvider);
                } else if (NaviFunctionEnum.OPEN_ADDRESS_SETTING_FUNC.equals(naviFunction) || NaviFunctionEnum.CLOSE_ADDRESS_SETTING_FUNC.equals(naviFunction) || NaviFunctionEnum.QUERY_SPEED_OF_LIMITED_FUNC.equals(naviFunction) || NaviFunctionEnum.SWITCH_MAP_DISPLAY_MODE_FUNC.equals(naviFunction) || NaviFunctionEnum.CONSIDERING_ROUTE_OPTION_FUNC.equals(naviFunction)) {
                    checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.UNAVAILABLE);
                } else if (NaviFunctionEnum.QUERY_ROUTE_WAYPOINT_INFO_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResultNEU(checkNaviFunctionAvailabilityResult, naviProvider);
                } else if (NaviFunctionEnum.QUERY_CURRENT_LOCATION_FUNC.equals(naviFunction)) {
                    naviFunctionAvailabilityResultExpTmap = getNaviFunctionAvailabilityResultExpTmap(checkNaviFunctionAvailabilityResult, naviProvider);
                }
                checkNaviFunctionAvailabilityResult = naviFunctionAvailabilityResultExpTmap;
            } else {
                checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.UNAVAILABLE);
            }
            RLog.i(TAG, "checkNaviFunctionAvailability: " + checkNaviFunctionAvailabilityResult.getCheckResult());
            iResponseSender.send(new Response("OK", checkNaviFunctionAvailabilityResult));
        } catch (Exception unused) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, checkNaviFunctionAvailabilityResult));
        }
    }

    private CheckNaviFunctionAvailabilityResult getNaviFunctionAvailabilityResult(CheckNaviFunctionAvailabilityResult checkNaviFunctionAvailabilityResult, int i) {
        if (i != 0) {
            checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.AVAILABLE);
        } else {
            checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.UNAVAILABLE);
        }
        return checkNaviFunctionAvailabilityResult;
    }

    private CheckNaviFunctionAvailabilityResult getNaviFunctionAvailabilityResultNEU(CheckNaviFunctionAvailabilityResult checkNaviFunctionAvailabilityResult, int i) {
        if (i == 2) {
            checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.AVAILABLE);
        } else {
            checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.UNAVAILABLE);
        }
        return checkNaviFunctionAvailabilityResult;
    }

    private CheckNaviFunctionAvailabilityResult getNaviFunctionAvailabilityResultExpTmap(CheckNaviFunctionAvailabilityResult checkNaviFunctionAvailabilityResult, int i) {
        if (i != 0 && i != 5) {
            checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.AVAILABLE);
        } else {
            checkNaviFunctionAvailabilityResult.setCheckResult(FunctionAvailabilityEnum.UNAVAILABLE);
        }
        return checkNaviFunctionAvailabilityResult;
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(TAG, "activate CustomNaviDomain ");
        if (NaviHelper.getInstance().checkNaviInstalled()) {
            Settings.System.putInt(BydVoiceApplication.getContext().getContentResolver(), "KEY_NAVI_AVAILABLE", 1);
        } else {
            Settings.System.putInt(BydVoiceApplication.getContext().getContentResolver(), "KEY_NAVI_AVAILABLE", 0);
        }
        this.isDetailPageSupported = NaviHelper.getInstance().checkDetailPageSupported();
        EventBus.getDefault().post(new NaviUpdateEvent());
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "deactivate CustomNaviDomain");
    }
}
