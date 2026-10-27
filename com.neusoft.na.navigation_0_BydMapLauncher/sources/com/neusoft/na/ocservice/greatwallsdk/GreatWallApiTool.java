package com.neusoft.na.ocservice.greatwallsdk;

import android.car.AoapService;
import cerence.ark.assistant.library.JsonKey;
import com.google.gson.Gson;
import com.neusoft.na.ocservice.greatwallsdk.bean.DestinationInformationData;
import com.neusoft.na.ocservice.greatwallsdk.bean.GeoPosition;
import com.neusoft.na.ocservice.greatwallsdk.bean.GreatWallBaseBean;
import com.neusoft.na.ocservice.greatwallsdk.bean.GuidanceInfoStatusData;
import com.neusoft.na.ocservice.greatwallsdk.bean.LaneInfoStatusData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ListPoiData;
import com.neusoft.na.ocservice.greatwallsdk.bean.MapFrontStateData;
import com.neusoft.na.ocservice.greatwallsdk.bean.MapLevelData;
import com.neusoft.na.ocservice.greatwallsdk.bean.MapModeData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ModeData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PeripheralData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PoiData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PreferencesData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ResultData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ResultPoiListData;
import com.neusoft.na.ocservice.greatwallsdk.bean.RoutePreferenceData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SelectRouteData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SpeedLimitData;
import com.neusoft.na.ocservice.greatwallsdk.bean.StateData;
import com.neusoft.na.ocservice.greatwallsdk.bean.StatusData;
import com.neusoft.na.ocservice.greatwallsdk.bean.TrafficeStateData;
import com.neusoft.na.ocservice.greatwallsdk.bean.UserProfileRecordData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ViewIdData;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GreatWallApiTool {
    public static Gson gson = new Gson();

    public static String jsonEncoding(int i, String str, String str2, int i2, int i3, int i4) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("actionType", i);
        jSONObject.put("auth", str);
        jSONObject.put(JsonKey.RESULTS_DATA, new JSONObject(str2));
        jSONObject.put("moduleType", i2);
        jSONObject.put("operaType", i3);
        jSONObject.put("statusCode", i4);
        return jSONObject.toString();
    }

    public static String jsonEncoding(int i, String str, int i2, int i3, int i4) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("actionType", i);
        jSONObject.put("auth", str);
        jSONObject.put("moduleType", i2);
        jSONObject.put("operaType", i3);
        jSONObject.put("statusCode", i4);
        return jSONObject.toString();
    }

    public static GreatWallBaseBean jsonDecoding(String str) {
        return (GreatWallBaseBean) gson.fromJson(str, GreatWallBaseBean.class);
    }

    public static SelectRouteData jDSelectRouteData(String str) {
        return (SelectRouteData) gson.fromJson(str, SelectRouteData.class);
    }

    public static PoiData JDPoiData(String str) {
        return (PoiData) gson.fromJson(str, PoiData.class);
    }

    public static GeoPosition JDGeoPosition(String str) {
        return (GeoPosition) gson.fromJson(str, GeoPosition.class);
    }

    public static PeripheralData JDPaeripheralData(String str) {
        return (PeripheralData) gson.fromJson(str, PeripheralData.class);
    }

    public static PreferencesData JDPreferencesData(String str) {
        return (PreferencesData) gson.fromJson(str, PreferencesData.class);
    }

    public static String jsonEncoding(StatusData statusData) throws JSONException {
        return gson.toJson(statusData);
    }

    public static String jsonEncoding(StateData stateData) throws JSONException {
        return gson.toJson(stateData);
    }

    public static String jsonEncoding(SpeedLimitData speedLimitData) throws JSONException {
        return gson.toJson(speedLimitData);
    }

    public static String jsonEncoding(LaneInfoStatusData laneInfoStatusData) throws JSONException {
        return gson.toJson(laneInfoStatusData);
    }

    public static String jsonEncoding(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("firstTBT", str);
        jSONObject.put("secondTBT", str2);
        jSONObject.put("ETA", str3);
        return jSONObject.toString();
    }

    public static String jsonEncoding(PoiData poiData) throws JSONException {
        return gson.toJson(poiData);
    }

    public static String jsonEncoding(ResultPoiListData resultPoiListData) throws JSONException {
        return gson.toJson(resultPoiListData);
    }

    public static String jsonEncoding(ResultData resultData) throws JSONException {
        return gson.toJson(resultData);
    }

    public static String jsonEncoding(DestinationInformationData destinationInformationData) throws JSONException {
        return gson.toJson(destinationInformationData);
    }

    public static List<PoiData> JDListPoi(String str) {
        return ((ListPoiData) gson.fromJson(str, ListPoiData.class)).getWayPoints();
    }

    public static String jsonEncoding(GuidanceInfoStatusData guidanceInfoStatusData) throws JSONException {
        return gson.toJson(guidanceInfoStatusData);
    }

    public static String jsonEncoding(ListPoiData listPoiData) throws JSONException {
        return gson.toJson(listPoiData);
    }

    public static String jsonEncoding(MapFrontStateData mapFrontStateData) throws JSONException {
        return gson.toJson(mapFrontStateData);
    }

    public static String jsonEncoding(MapModeData mapModeData) throws JSONException {
        return gson.toJson(mapModeData);
    }

    public static String jsonEncoding(MapLevelData mapLevelData) throws JSONException {
        return gson.toJson(mapLevelData);
    }

    public static String jsonEncoding(RoutePreferenceData routePreferenceData) throws JSONException {
        return gson.toJson(routePreferenceData);
    }

    public static String jsonEncoding(TrafficeStateData trafficeStateData) throws JSONException {
        return gson.toJson(trafficeStateData);
    }

    public static String jsonEncoding(UserProfileRecordData userProfileRecordData) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("BeanId", userProfileRecordData.getBeanId());
        if (userProfileRecordData.getResult().equals("")) {
            jSONObject.put(AoapService.KEY_RESULT, userProfileRecordData.getResult());
        } else {
            jSONObject.put(AoapService.KEY_RESULT, new JSONObject(userProfileRecordData.getResult()));
        }
        return jSONObject.toString();
    }

    public static String jsonEncoding(ViewIdData viewIdData) throws JSONException {
        return gson.toJson(viewIdData);
    }

    public static String jsonEncoding(ModeData modeData) throws JSONException {
        return gson.toJson(modeData);
    }
}
