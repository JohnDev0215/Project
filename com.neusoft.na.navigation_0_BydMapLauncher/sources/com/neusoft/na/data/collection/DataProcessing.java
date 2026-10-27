package com.neusoft.na.data.collection;

import android.util.Base64;
import com.neusoft.na.ocservice.MotsBl;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import na.pb.DataCollectionOuterClass;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DataProcessing.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 92\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0018\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u0011H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u000fH\u0002J\u0018\u0010 \u001a\u00020!2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0004H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u0018\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004H\u0002J\u0012\u00102\u001a\u0004\u0018\u0001032\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u00104\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J&\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006:"}, d2 = {"Lcom/neusoft/na/data/collection/DataProcessing;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "fromBase64", "byteArray", "", "getCalculateRouteEnd", "Lna/pb/DataCollectionOuterClass$CalculateRouteEnd;", "dataBlob", "Lorg/json/JSONObject;", "getCalculateRouteEndDataBlob", "Lna/pb/DataCollectionOuterClass$DataBlob;", "json", "getDataBlobInBlob", "base64", "getDataCollection2base64", "header", "Lna/pb/DataCollectionOuterClass$Header;", "getGuidanceEnd", "Lna/pb/DataCollectionOuterClass$GuidanceEnd;", "getGuidanceEndDataBlob", "getGuidanceStart", "Lna/pb/DataCollectionOuterClass$GuidanceStart;", "getGuidanceStartDataBlob", "getHeader", "headerObject", "getIntForJson", "", "key", "getMapUpdateEnd", "Lna/pb/DataCollectionOuterClass$MapUpdateEnd;", "getMapUpdateEndDataBlob", "getMapUpdateStart", "Lna/pb/DataCollectionOuterClass$MapUpdateStart;", "getMapUpdateStartDataBlob", "getNameInfo", "Lna/pb/DataCollectionOuterClass$NameInfo;", "getNameInfoDataBlob", "getNavigationAppStart", "Lna/pb/DataCollectionOuterClass$NavigationAppStart;", "getNavigationAppStartDataBlob", "getPostJson", "base", "oem", "getRegionInfo", "Lna/pb/DataCollectionOuterClass$RegionInfo;", "getRegionInfoDataBlob", "getResult", "type", "jheader", "content", "Companion", "data_collection_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DataProcessing {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static DataProcessing instance;
    private String TAG = "DataProcessing";

    /* JADX INFO: compiled from: DataProcessing.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/neusoft/na/data/collection/DataProcessing$Companion;", "", "()V", "instance", "Lcom/neusoft/na/data/collection/DataProcessing;", "getInstance", "data_collection_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DataProcessing getInstance() {
            if (DataProcessing.instance == null) {
                DataProcessing.instance = new DataProcessing();
            }
            return DataProcessing.instance;
        }
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final JSONObject getResult(String type, String oem, JSONObject jheader, JSONObject content) throws JSONException {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(oem, "oem");
        Intrinsics.checkNotNullParameter(jheader, "jheader");
        Intrinsics.checkNotNullParameter(content, "content");
        new JSONObject();
        Intrinsics.checkNotNullExpressionValue(DataCollectionOuterClass.Header.getDefaultInstance(), "getDefaultInstance()");
        DataCollectionOuterClass.DataBlob defaultInstance = DataCollectionOuterClass.DataBlob.getDefaultInstance();
        Intrinsics.checkNotNullExpressionValue(defaultInstance, "getDefaultInstance()");
        DataCollectionOuterClass.Header header = getHeader(jheader);
        switch (type.hashCode()) {
            case -1636086410:
                if (type.equals("MapUpdateEnd")) {
                    defaultInstance = getMapUpdateEndDataBlob(content);
                }
                break;
            case -1241567432:
                if (type.equals("CalculateRouteEnd")) {
                    defaultInstance = getCalculateRouteEndDataBlob(content);
                }
                break;
            case -307900867:
                if (type.equals("MapUpdateStart")) {
                    defaultInstance = getMapUpdateStartDataBlob(content);
                }
                break;
            case 2638306:
                if (type.equals("RegionInfo")) {
                    defaultInstance = getRegionInfoDataBlob(content);
                }
                break;
            case 21323029:
                if (type.equals("NavigationAppStart")) {
                    defaultInstance = getNavigationAppStartDataBlob(content);
                }
                break;
            case 534671306:
                if (type.equals("GuidanceStart")) {
                    defaultInstance = getGuidanceStartDataBlob(content);
                }
                break;
            case 563670595:
                if (type.equals("GuidanceEnd")) {
                    defaultInstance = getGuidanceEndDataBlob(content);
                }
                break;
            case 1904900601:
                if (type.equals("NameInfo")) {
                    defaultInstance = getNameInfoDataBlob(content);
                }
                break;
        }
        return getPostJson(getDataCollection2base64(header, defaultInstance), oem);
    }

    private final JSONObject getPostJson(String base, String oem) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("oem", oem);
        jSONObject.put("content", base);
        return jSONObject;
    }

    private final String getDataCollection2base64(DataCollectionOuterClass.Header header, DataCollectionOuterClass.DataBlob dataBlob) {
        DataCollectionOuterClass.DataCollection.Builder builderNewBuilder = DataCollectionOuterClass.DataCollection.newBuilder();
        builderNewBuilder.setHeader(header);
        builderNewBuilder.addDataBlob(dataBlob);
        return fromBase64(builderNewBuilder.build().toByteArray());
    }

    private final DataCollectionOuterClass.Header getHeader(JSONObject headerObject) throws JSONException {
        int intForJson = getIntForJson(headerObject, "oem");
        String string = headerObject.getString(MotsBl.JS_PROPERTY_LOCALE_COUNTRY);
        String string2 = headerObject.getString("deviceId");
        String string3 = headerObject.getString("carMode");
        String string4 = headerObject.getString("appVersion");
        String string5 = headerObject.getString(MotsBl.JS_PROPERTY_LOCALE_LANGUAGE);
        String string6 = headerObject.getString("userId");
        DataCollectionOuterClass.Header header = DataCollectionOuterClass.Header.newBuilder().setOem(DataCollectionOuterClass.OEM.forNumber(intForJson)).setCountry(string).setDeviceId(string2).setCarMode(string3).setCollectTime(String.valueOf(System.currentTimeMillis())).setAppVersion(string4).setLanguage(string5).setUserId(string6).setDbVersion(headerObject.getString("dbVersion")).build();
        Intrinsics.checkNotNullExpressionValue(header, "header");
        return header;
    }

    private final DataCollectionOuterClass.DataBlob getDataBlobInBlob(String base64, JSONObject json) {
        DataCollectionOuterClass.DataBlob dataBlob = DataCollectionOuterClass.DataBlob.newBuilder().setEventId(DataCollectionOuterClass.EventID.forNumber(getIntForJson(json, "eventId"))).setEventComponent(DataCollectionOuterClass.EventComponent.forNumber(getIntForJson(json, "eventComponent"))).setEventType(DataCollectionOuterClass.EventType.forNumber(getIntForJson(json, "eventType"))).setDataBlob(base64).build();
        Intrinsics.checkNotNullExpressionValue(dataBlob, "dataBlob");
        return dataBlob;
    }

    private final DataCollectionOuterClass.DataBlob getGuidanceStartDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.GuidanceStart guidanceStart = getGuidanceStart(jSONObject);
        return getDataBlobInBlob(fromBase64(guidanceStart != null ? guidanceStart.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.GuidanceStart getGuidanceStart(JSONObject dataBlob) {
        return DataCollectionOuterClass.GuidanceStart.newBuilder().setEta(dataBlob.getString("eta")).setTimeStamp(dataBlob.getString("timeStamp")).build();
    }

    private final DataCollectionOuterClass.DataBlob getGuidanceEndDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.GuidanceEnd guidanceEnd = getGuidanceEnd(jSONObject);
        return getDataBlobInBlob(fromBase64(guidanceEnd != null ? guidanceEnd.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.GuidanceEnd getGuidanceEnd(JSONObject dataBlob) {
        return DataCollectionOuterClass.GuidanceEnd.newBuilder().setEta(dataBlob.getString("eta")).setTimeStamp(dataBlob.getString("timeStamp")).build();
    }

    private final DataCollectionOuterClass.DataBlob getNavigationAppStartDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.NavigationAppStart navigationAppStart = getNavigationAppStart(jSONObject);
        return getDataBlobInBlob(fromBase64(navigationAppStart != null ? navigationAppStart.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.NavigationAppStart getNavigationAppStart(JSONObject dataBlob) {
        return DataCollectionOuterClass.NavigationAppStart.newBuilder().setTimeStamp(dataBlob.getString("timeStamp")).build();
    }

    private final DataCollectionOuterClass.DataBlob getNameInfoDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.NameInfo nameInfo = getNameInfo(jSONObject);
        return getDataBlobInBlob(fromBase64(nameInfo != null ? nameInfo.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.NameInfo getNameInfo(JSONObject dataBlob) {
        return DataCollectionOuterClass.NameInfo.newBuilder().setLanguageCode(dataBlob.getString("languageCode")).setName(dataBlob.getString("name")).build();
    }

    private final DataCollectionOuterClass.DataBlob getCalculateRouteEndDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.CalculateRouteEnd calculateRouteEnd = getCalculateRouteEnd(jSONObject);
        return getDataBlobInBlob(fromBase64(calculateRouteEnd != null ? calculateRouteEnd.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.CalculateRouteEnd getCalculateRouteEnd(JSONObject dataBlob) throws JSONException {
        DataCollectionOuterClass.CalculateRouteEnd.Builder builderNewBuilder = DataCollectionOuterClass.CalculateRouteEnd.newBuilder();
        builderNewBuilder.setTimeStamp(dataBlob.getString("timeStamp"));
        builderNewBuilder.setRouteSource(DataCollectionOuterClass.RouteSrc.forNumber(getIntForJson(dataBlob, "routeSource")));
        builderNewBuilder.setLat(dataBlob.getString("lat"));
        builderNewBuilder.setLon(dataBlob.getString("lon"));
        builderNewBuilder.setDestType(DataCollectionOuterClass.DestType.forNumber(getIntForJson(dataBlob, "destType")));
        builderNewBuilder.setAddress(dataBlob.getString("address"));
        builderNewBuilder.setPoiSrc(DataCollectionOuterClass.PoiSrc.forNumber(getIntForJson(dataBlob, "poiSrc")));
        builderNewBuilder.setPoiCategory(getIntForJson(dataBlob, "poiCategory"));
        JSONArray jSONArray = dataBlob.getJSONArray("nameInfos");
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                builderNewBuilder.addNameInfos(DataCollectionOuterClass.NameInfo.newBuilder().setName(jSONObject.getString("name")).setLanguageCode(jSONObject.getString("languageCode")).build());
            }
        }
        return builderNewBuilder.build();
    }

    private final DataCollectionOuterClass.DataBlob getRegionInfoDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.RegionInfo regionInfo = getRegionInfo(jSONObject);
        return getDataBlobInBlob(fromBase64(regionInfo != null ? regionInfo.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.RegionInfo getRegionInfo(JSONObject dataBlob) {
        return DataCollectionOuterClass.RegionInfo.newBuilder().setProductName(dataBlob.getString("productName")).setRegionName(dataBlob.getString("regionName")).setRegionId(getIntForJson(dataBlob, "regionId")).setVersionId(dataBlob.getString("versionId")).build();
    }

    private final DataCollectionOuterClass.DataBlob getMapUpdateStartDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.MapUpdateStart mapUpdateStart = getMapUpdateStart(jSONObject);
        return getDataBlobInBlob(fromBase64(mapUpdateStart != null ? mapUpdateStart.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.MapUpdateStart getMapUpdateStart(JSONObject dataBlob) throws JSONException {
        DataCollectionOuterClass.MapUpdateStart.Builder builderNewBuilder = DataCollectionOuterClass.MapUpdateStart.newBuilder();
        builderNewBuilder.setTimeStamp(dataBlob.getString("timeStamp"));
        builderNewBuilder.setCurrentMapVersion(dataBlob.getString("currentMapVersion"));
        builderNewBuilder.setUpdateType(DataCollectionOuterClass.UpdateType.forNumber(getIntForJson(dataBlob, "updateType")));
        builderNewBuilder.setUpdateSource(DataCollectionOuterClass.UpdateSource.forNumber(getIntForJson(dataBlob, "updateSource")));
        builderNewBuilder.setMapVersion(dataBlob.getString("mapVersion"));
        builderNewBuilder.setRegionCount(getIntForJson(dataBlob, "regionCount"));
        JSONArray jSONArray = dataBlob.getJSONArray("regionInfo");
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsb = jSONArray.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jsb, "jsb");
                builderNewBuilder.addRegionInfo(getRegionInfo(jsb));
            }
        }
        return builderNewBuilder.build();
    }

    private final DataCollectionOuterClass.DataBlob getMapUpdateEndDataBlob(JSONObject json) throws JSONException {
        JSONObject jSONObject = json.getJSONObject("dataBlob");
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(\"dataBlob\")");
        DataCollectionOuterClass.MapUpdateEnd mapUpdateEnd = getMapUpdateEnd(jSONObject);
        return getDataBlobInBlob(fromBase64(mapUpdateEnd != null ? mapUpdateEnd.toByteArray() : null), json);
    }

    private final DataCollectionOuterClass.MapUpdateEnd getMapUpdateEnd(JSONObject dataBlob) {
        DataCollectionOuterClass.MapUpdateEnd.Builder builderNewBuilder = DataCollectionOuterClass.MapUpdateEnd.newBuilder();
        builderNewBuilder.setTimeStamp(dataBlob.getString("timeStamp"));
        builderNewBuilder.setUpdateType(DataCollectionOuterClass.UpdateType.forNumber(getIntForJson(dataBlob, "updateType")));
        builderNewBuilder.setUpdateSource(DataCollectionOuterClass.UpdateSource.forNumber(getIntForJson(dataBlob, "updateSource")));
        builderNewBuilder.setUpdateResult(DataCollectionOuterClass.UpdateResult.forNumber(getIntForJson(dataBlob, "updateResult")));
        builderNewBuilder.setUpdateStatus(DataCollectionOuterClass.UpdateStatus.forNumber(getIntForJson(dataBlob, "updateStatus")));
        return builderNewBuilder.build();
    }

    private final String fromBase64(byte[] byteArray) {
        String strEncodeToString;
        if (byteArray != null) {
            strEncodeToString = Base64.encodeToString(byteArray, byteArray.length);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(byteArray, byteArray.size)");
        } else {
            strEncodeToString = "";
        }
        return StringsKt.replace$default(StringsKt.replace$default(strEncodeToString, StringUtils.CR, "", false, 4, (Object) null), "\n", "", false, 4, (Object) null);
    }

    private final int getIntForJson(JSONObject json, String key) throws JSONException {
        String string = json.getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "json.getString(key)");
        return Integer.parseInt(string);
    }
}
