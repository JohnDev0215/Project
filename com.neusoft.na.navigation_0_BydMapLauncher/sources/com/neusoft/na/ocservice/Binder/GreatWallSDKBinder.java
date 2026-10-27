package com.neusoft.na.ocservice.Binder;

import android.car.AoapService;
import android.car.user.CarUserManager;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import cerence.ark.assistant.library.JsonKey;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.neusoft.greatwallsdk.IAidlGreatWallInterface;
import com.neusoft.greatwallsdk.IAidlGreatWallListener;
import com.neusoft.greatwallsdk.IAidlRequestCallback;
import com.neusoft.greatwallsdk.IAidlSetSurfaceResultCallback;
import com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl;
import com.neusoft.na.ocservice.greatwallsdk.GreatWallApiTool;
import com.neusoft.na.ocservice.greatwallsdk.IGWRequestCallback;
import com.neusoft.na.ocservice.greatwallsdk.IGWSetSurfaceCallback;
import com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi;
import com.neusoft.na.ocservice.greatwallsdk.IGreateWallNotificationListener;
import com.neusoft.na.ocservice.greatwallsdk.bean.GeoPosition;
import com.neusoft.na.ocservice.greatwallsdk.bean.PeripheralData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PoiData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PreferencesData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SelectRouteData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: GreatWallSDKBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 r2\u00020\u0001:\u0001rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001c\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J:\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u0012\u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\"\u001a\u00020\u0015H\u0002J\b\u0010#\u001a\u00020\u0006H\u0002J\u0010\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006H\u0002J\b\u0010&\u001a\u00020\u0006H\u0002J\b\u0010'\u001a\u00020\u0006H\u0002J\b\u0010(\u001a\u00020\u0006H\u0002J\b\u0010)\u001a\u00020\u0006H\u0002J\b\u0010*\u001a\u00020\u0006H\u0002J\b\u0010+\u001a\u00020\u0006H\u0002J\u0010\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0006H\u0002J\b\u00100\u001a\u00020\u0006H\u0002J\u0010\u00101\u001a\u00020\u00062\u0006\u0010-\u001a\u00020.H\u0002J\b\u00102\u001a\u00020\u0006H\u0002J\b\u00103\u001a\u00020\u0006H\u0002J\u0010\u00104\u001a\u00020\u00062\u0006\u0010-\u001a\u00020.H\u0002J\b\u00105\u001a\u00020\u0006H\u0002J\b\u00106\u001a\u00020\u0006H\u0002J\u0010\u00107\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006H\u0002J\b\u00108\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020\u0006H\u0002J\b\u0010:\u001a\u00020\u0006H\u0002J\b\u0010;\u001a\u00020\u0006H\u0002J\b\u0010<\u001a\u00020\u0006H\u0002J\b\u0010=\u001a\u00020\u0006H\u0002J\b\u0010>\u001a\u00020\u0006H\u0002J\b\u0010?\u001a\u00020\u0006H\u0002J\b\u0010@\u001a\u00020\u0006H\u0002J\b\u0010A\u001a\u00020\u0006H\u0002J\b\u0010B\u001a\u00020\u0006H\u0002J\u0010\u0010C\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010D\u001a\u00020\u0006H\u0002J\b\u0010E\u001a\u00020\u0006H\u0002J\b\u0010F\u001a\u00020\u0006H\u0002J\b\u0010G\u001a\u00020\u0006H\u0002J\u000e\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020JJ\b\u0010K\u001a\u00020\u0006H\u0002J\b\u0010L\u001a\u00020\u0006H\u0002J*\u0010M\u001a\u00020\u00152\u0006\u0010N\u001a\u00020\u001b2\u0006\u0010O\u001a\u00020P2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010Q\u001a\u00020\u001bH\u0002J\"\u0010R\u001a\u00020\u00152\u0006\u0010N\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010T\u001a\u00020\u0006H\u0002J\u0010\u0010U\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\"\u0010V\u001a\u00020\u00152\u0006\u0010N\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u0010\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020YH\u0002J\u000e\u0010Z\u001a\u00020\u00152\u0006\u0010[\u001a\u00020\u0006J\u0010\u0010\\\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010]\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010^\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010_\u001a\u00020\u00152\u0006\u0010`\u001a\u00020\u001bH\u0002J\u0018\u0010a\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u0006H\u0002J\u001c\u0010b\u001a\u00020\u00152\b\u0010c\u001a\u0004\u0018\u00010d2\b\u0010e\u001a\u0004\u0018\u00010fH\u0016J\b\u0010g\u001a\u00020\u0006H\u0002J\b\u0010h\u001a\u00020\u0006H\u0002J\b\u0010i\u001a\u00020\u0006H\u0002J\u0010\u0010j\u001a\u00020\u00062\u0006\u0010k\u001a\u00020lH\u0002J\b\u0010m\u001a\u00020\u0006H\u0002J\b\u0010n\u001a\u00020\u0006H\u0002J\u0012\u0010o\u001a\u00020\u00062\b\u0010c\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010p\u001a\u00020\u0006H\u0002J\b\u0010q\u001a\u00020\u0006H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nj\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006s"}, d2 = {"Lcom/neusoft/na/ocservice/Binder/GreatWallSDKBinder;", "Lcom/neusoft/greatwallsdk/IAidlGreatWallInterface$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "cancelResultlist", "Ljava/util/ArrayList;", "Lcom/neusoft/greatwallsdk/IAidlGreatWallListener;", "Lkotlin/collections/ArrayList;", "navigationApi", "Lcom/neusoft/na/ocservice/greatwallsdk/IGreatWallNavigationApi;", "resultlist", "Ljava/util/concurrent/CopyOnWriteArrayList;", "addPointsToFavorites", "poiData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PoiData;", "async_request", "", CarUserManager.BUNDLE_PARAM_ACTION, "callback", "Lcom/neusoft/greatwallsdk/IAidlRequestCallback;", "calculateRoad", "atcionType", "", "endPoi", "startPoi", "wayPoints", "", "cancelListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "clearLog", "closeTraffic", "deleteNavigationPersonalizationData", "BeanId", "destinationInformation", "displayFavorites", "displayHistoricalDestination", "enterOverview", "exitOverview", "getCompanyAddress", "getCountryCode", "requestJson", "Lorg/json/JSONObject;", "getDayNightMode", "getFavoriteList", "getFrontTraffic", "getHomeAddress", "getLocation", "getLogOnOffStatus", "getMapStatus", "getNavigationLanguageSetting", "getNavigationPersonalizationData", "getNavigationStatus", "getOverviewStatus", "getScaleSize", "getTBTInfo", "getTrafficStatus", "getViewMode", "getWayPointList", "getdestinationInfo", "goCompany", "goHome", "historicalDestinations", "increaseRoutePoints", "mapToBackstage", "mapToFrontDesk", "mapToMaximum", "mapToMinimize", "openMapWithLocation", "position", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/GeoPosition;", "openSettingsMenu", "openTraffic", "peripheralSearch", "reqId", "peripheralData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PeripheralData;", "operaType", "poiSearch", "keyWord", "queryPreferences", "removeFavoritePoints", "searchAlong", "selectRoute", "jDSelectRouteData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/SelectRouteData;", "sendResult", AoapService.KEY_RESULT, "setCompanyAddress", "setHomeAddress", "setListener", "setLogOnOffStatus", "status", "setNavigationPersonalizationData", "setSurface", "var1", "Landroid/view/Surface;", "var2", "Lcom/neusoft/greatwallsdk/IAidlSetSurfaceResultCallback;", "setViewMode2DDueNorthUp", "setViewMode2DHeadUp", "setViewMode3DHeadUp", "settingPreferences", "preferencesData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PreferencesData;", "startNavigation", "stopNavigation", "sync_request", "zoomIn", "zoomOut", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class GreatWallSDKBinder extends IAidlGreatWallInterface.Stub {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static GreatWallSDKBinder instance;
    private final String TAG;
    private ArrayList<IAidlGreatWallListener> cancelResultlist;
    private final Context context;
    private IGreatWallNavigationApi navigationApi;
    private CopyOnWriteArrayList<IAidlGreatWallListener> resultlist;

    public GreatWallSDKBinder(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "GreatWallSDKBinder";
        this.resultlist = new CopyOnWriteArrayList<>();
        this.cancelResultlist = new ArrayList<>();
        Log.i("GreatWallSDKBinder", "EcarxSDKBinder init()");
        this.navigationApi = new GreatWallApiOctaImpl(context, new IGreateWallNotificationListener() { // from class: com.neusoft.na.ocservice.Binder.GreatWallSDKBinder$$ExternalSyntheticLambda1
            @Override // com.neusoft.na.ocservice.greatwallsdk.IGreateWallNotificationListener
            public final void onResult(String str) {
                GreatWallSDKBinder._init_$lambda$0(this.f$0, str);
            }
        });
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: GreatWallSDKBinder.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/neusoft/na/ocservice/Binder/GreatWallSDKBinder$Companion;", "", "()V", "instance", "Lcom/neusoft/na/ocservice/Binder/GreatWallSDKBinder;", "getInstance", "context", "Landroid/content/Context;", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GreatWallSDKBinder getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (GreatWallSDKBinder.instance == null) {
                GreatWallSDKBinder.instance = new GreatWallSDKBinder(context);
            }
            return GreatWallSDKBinder.instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(GreatWallSDKBinder this$0, String result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(this$0.TAG, "onResult: " + result);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        this$0.sendResult(result);
    }

    public final synchronized void sendResult(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        int size = this.resultlist.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            try {
                IAidlGreatWallListener iAidlGreatWallListener = this.resultlist.get(i2);
                if (iAidlGreatWallListener != null) {
                    iAidlGreatWallListener.onResult(result);
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.TAG, "There are invalid listeners");
                Log.e(this.TAG, "send " + i2 + " exception");
                this.cancelResultlist.add(this.resultlist.get(i2));
                Log.e(this.TAG, "cancelResultlist " + this.cancelResultlist.size());
            }
        }
        Log.i(this.TAG, "[sendResult] --> send " + i + " client");
        if (this.cancelResultlist.size() > 0) {
            Log.e(this.TAG, "Remove invalid listeners");
            int size2 = this.cancelResultlist.size();
            for (int i3 = 0; i3 < size2; i3++) {
                this.resultlist.remove(this.cancelResultlist.get(i3));
            }
            Log.i(this.TAG, "remove client size == " + this.cancelResultlist.size());
            this.cancelResultlist.clear();
        }
    }

    @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
    public String sync_request(String var1) throws JSONException {
        String navigationLanguageSetting;
        Log.i(this.TAG, "sync_request");
        JSONObject jSONObject = new JSONObject(var1);
        int i = jSONObject.getInt("moduleType");
        int i2 = jSONObject.getInt("actionType");
        int i3 = jSONObject.getInt("operaType");
        if (i == 1000 && i2 == 1 && i3 == 0) {
            navigationLanguageSetting = getTrafficStatus();
        } else if (i == 1000 && i2 == 1 && i3 == 1) {
            navigationLanguageSetting = openTraffic();
        } else if (i == 1000 && i2 == 1 && i3 == 2) {
            navigationLanguageSetting = closeTraffic();
        } else if (i == 1000 && i2 == 0 && i3 == 0) {
            navigationLanguageSetting = getScaleSize();
        } else if (i == 1000 && i2 == 0 && i3 == 1) {
            navigationLanguageSetting = zoomIn();
        } else if (i == 1000 && i2 == 0 && i3 == 2) {
            navigationLanguageSetting = zoomOut();
        } else if (i == 1000 && i2 == 0 && i3 == 3) {
            navigationLanguageSetting = mapToMaximum();
        } else if (i == 1000 && i2 == 0 && i3 == 4) {
            navigationLanguageSetting = mapToMinimize();
        } else if (i == 1000 && i2 == 2 && i3 == 0) {
            navigationLanguageSetting = getViewMode();
        } else if (i == 1000 && i2 == 2 && i3 == 1) {
            navigationLanguageSetting = setViewMode2DDueNorthUp();
        } else if (i == 1000 && i2 == 2 && i3 == 2) {
            navigationLanguageSetting = setViewMode2DHeadUp();
        } else if (i == 1000 && i2 == 2 && i3 == 3) {
            navigationLanguageSetting = setViewMode3DHeadUp();
        } else if (i == 1000 && i2 == 4 && i3 == 0) {
            navigationLanguageSetting = mapToFrontDesk();
        } else if (i == 1000 && i2 == 4 && i3 == 1) {
            navigationLanguageSetting = mapToBackstage();
        } else if (i == 1000 && i2 == 4 && i3 == 2) {
            navigationLanguageSetting = getMapStatus();
        } else if (i == 2000 && i2 == 3 && i3 == 0) {
            navigationLanguageSetting = getLocation();
        } else if (i == 3000 && i2 == 1 && i3 == 0) {
            SelectRouteData selectRouteDataJDSelectRouteData = GreatWallApiTool.jDSelectRouteData(jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString());
            Intrinsics.checkNotNullExpressionValue(selectRouteDataJDSelectRouteData, "jDSelectRouteData(object…bject(\"data\").toString())");
            navigationLanguageSetting = selectRoute(selectRouteDataJDSelectRouteData);
        } else if (i == 3000 && i2 == 2 && i3 == 0) {
            String string = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string, "objects.getJSONObject(\"data\").toString()");
            PreferencesData preferencesData = GreatWallApiTool.JDPreferencesData(string);
            Intrinsics.checkNotNullExpressionValue(preferencesData, "preferencesData");
            navigationLanguageSetting = settingPreferences(preferencesData);
        } else if (i == 3000 && i2 == 2 && i3 == 1) {
            navigationLanguageSetting = queryPreferences();
        } else if (i == 3000 && i2 == 3 && i3 == 0) {
            navigationLanguageSetting = getWayPointList();
        } else if (i == 3000 && i2 == 3 && i3 == 1) {
            String string2 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getJSONObject("wayPoints").toString();
            Intrinsics.checkNotNullExpressionValue(string2, "data.getJSONObject(\"wayPoints\").toString()");
            PoiData poiData = GreatWallApiTool.JDPoiData(string2);
            Intrinsics.checkNotNullExpressionValue(poiData, "poiData");
            navigationLanguageSetting = increaseRoutePoints(poiData);
        } else if (i == 3000 && i2 == 4 && i3 == 0) {
            navigationLanguageSetting = getdestinationInfo();
        } else if (i == 4000 && i2 == 0 && i3 == 0) {
            navigationLanguageSetting = getNavigationStatus();
        } else if (i == 4000 && i2 == 0 && i3 == 1) {
            navigationLanguageSetting = startNavigation();
        } else if (i == 4000 && i2 == 0 && i3 == 2) {
            navigationLanguageSetting = stopNavigation();
        } else if (i == 4000 && i2 == 1 && i3 == 0) {
            navigationLanguageSetting = getFrontTraffic(jSONObject);
        } else if (i == 4000 && i2 == 4 && i3 == 0) {
            navigationLanguageSetting = destinationInformation();
        } else if (i == 4000 && i2 == 3 && i3 == 0) {
            navigationLanguageSetting = getOverviewStatus();
        } else if (i == 4000 && i2 == 3 && i3 == 1) {
            navigationLanguageSetting = enterOverview();
        } else if (i == 4000 && i2 == 3 && i3 == 2) {
            navigationLanguageSetting = exitOverview();
        } else if (i == 5000 && i2 == 0 && i3 == 0) {
            navigationLanguageSetting = getFavoriteList();
        } else if (i == 5000 && i2 == 0 && i3 == 1) {
            String string3 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string3, "objects.getJSONObject(\"data\").toString()");
            PoiData poiData2 = GreatWallApiTool.JDPoiData(string3);
            Intrinsics.checkNotNullExpressionValue(poiData2, "poiData");
            navigationLanguageSetting = addPointsToFavorites(poiData2);
        } else if (i == 5000 && i2 == 0 && i3 == 2) {
            String string4 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string4, "objects.getJSONObject(\"data\").toString()");
            PoiData poiData3 = GreatWallApiTool.JDPoiData(string4);
            Intrinsics.checkNotNullExpressionValue(poiData3, "poiData");
            navigationLanguageSetting = removeFavoritePoints(poiData3);
        } else if (i == 5000 && i2 == 0 && i3 == 3) {
            navigationLanguageSetting = displayFavorites();
        } else if (i == 5000 && i2 == 1 && i3 == 0) {
            navigationLanguageSetting = getHomeAddress();
        } else if (i == 5000 && i2 == 1 && i3 == 1) {
            String string5 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string5, "objects.getJSONObject(\"data\").toString()");
            PoiData poiData4 = GreatWallApiTool.JDPoiData(string5);
            Intrinsics.checkNotNullExpressionValue(poiData4, "poiData");
            navigationLanguageSetting = setHomeAddress(poiData4);
        } else if (i == 5000 && i2 == 1 && i3 == 2) {
            navigationLanguageSetting = getCompanyAddress();
        } else if (i == 5000 && i2 == 1 && i3 == 3) {
            String string6 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string6, "objects.getJSONObject(\"data\").toString()");
            PoiData poiData5 = GreatWallApiTool.JDPoiData(string6);
            Intrinsics.checkNotNullExpressionValue(poiData5, "poiData");
            navigationLanguageSetting = setCompanyAddress(poiData5);
        } else if (i == 5000 && i2 == 1 && i3 == 4) {
            navigationLanguageSetting = goHome();
        } else if (i == 5000 && i2 == 1 && i3 == 5) {
            navigationLanguageSetting = goCompany();
        } else if (i == 8000 && i2 == 2 && i3 == 0) {
            navigationLanguageSetting = openSettingsMenu();
        } else if (i == 8000 && i2 == 3 && i3 == 0) {
            navigationLanguageSetting = historicalDestinations();
        } else if (i == 8000 && i2 == 3 && i3 == 1) {
            navigationLanguageSetting = displayHistoricalDestination();
        } else if (i == 10000 && i2 == 2 && i3 == 0) {
            String string7 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string7, "objects.getJSONObject(\"data\").toString()");
            GeoPosition geoPosition = GreatWallApiTool.JDGeoPosition(string7);
            Intrinsics.checkNotNullExpressionValue(geoPosition, "geoPosition");
            navigationLanguageSetting = openMapWithLocation(geoPosition);
        } else if (i == 10000 && i2 == 3 && i3 == 0) {
            navigationLanguageSetting = getDayNightMode();
        } else if (i == 20000 && i2 == 5) {
            navigationLanguageSetting = getCountryCode(jSONObject);
        } else if (i == 20000 && i2 == 2 && i3 == 1) {
            navigationLanguageSetting = getTBTInfo();
        } else if (i == 50000 && i2 == 1 && i3 == 0) {
            String BeanId = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getString("BeanId");
            String result = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getString(AoapService.KEY_RESULT);
            Intrinsics.checkNotNullExpressionValue(BeanId, "BeanId");
            Intrinsics.checkNotNullExpressionValue(result, "result");
            navigationLanguageSetting = setNavigationPersonalizationData(BeanId, result);
        } else if (i == 50000 && i2 == 1 && i3 == 1) {
            String BeanId2 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getString("BeanId");
            Intrinsics.checkNotNullExpressionValue(BeanId2, "BeanId");
            navigationLanguageSetting = getNavigationPersonalizationData(BeanId2);
        } else if (i == 50000 && i2 == 1 && i3 == 2) {
            String BeanId3 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getString("BeanId");
            Intrinsics.checkNotNullExpressionValue(BeanId3, "BeanId");
            navigationLanguageSetting = deleteNavigationPersonalizationData(BeanId3);
        } else if (i == 90000 && i2 == 2 && i3 == 1) {
            navigationLanguageSetting = getNavigationLanguageSetting();
        } else {
            if (i == 90000 && i2 == 3) {
                int i4 = jSONObject.getInt("operaType");
                if (i4 == 0) {
                    getLogOnOffStatus(jSONObject);
                } else if (i4 == 1) {
                    setLogOnOffStatus(jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getInt("status"));
                } else if (i4 == 2) {
                    clearLog();
                }
            }
            navigationLanguageSetting = "";
        }
        Log.i(this.TAG, "request : " + navigationLanguageSetting);
        return navigationLanguageSetting;
    }

    private final String getLogOnOffStatus(JSONObject requestJson) throws JSONException {
        Log.i(this.TAG, "getLogOnOffStatus requestJson == " + requestJson);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", this.navigationApi.getLogOnOffStatus());
        requestJson.put(JsonKey.RESULTS_DATA, jSONObject);
        String string = requestJson.toString();
        Intrinsics.checkNotNullExpressionValue(string, "requestJson.toString()");
        return string;
    }

    private final void setLogOnOffStatus(int status) {
        Log.i(this.TAG, "getLogOnOffStatus status == " + status);
        this.navigationApi.setLogOnOffStatus(status);
    }

    private final void clearLog() {
        Log.i(this.TAG, "clearLog");
        this.navigationApi.clearLog();
    }

    private final String getCountryCode(JSONObject requestJson) throws JSONException {
        Log.i(this.TAG, "[getCountryCode] requestJson == " + requestJson);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("countryCode", this.navigationApi.getCountryCode());
        requestJson.put(JsonKey.RESULTS_DATA, jSONObject);
        String string = requestJson.toString();
        Intrinsics.checkNotNullExpressionValue(string, "requestJson.toString()");
        return string;
    }

    private final String getFrontTraffic(JSONObject requestJson) throws JSONException {
        Log.i(this.TAG, "[getFrontTraffic] requestJson == " + requestJson);
        requestJson.put(JsonKey.RESULTS_DATA, this.navigationApi.getFrontTraffic());
        String string = requestJson.toString();
        Intrinsics.checkNotNullExpressionValue(string, "requestJson.toString()");
        return string;
    }

    private final String getdestinationInfo() {
        Log.i(this.TAG, "getdestinationInfo");
        return this.navigationApi.getdestinationInfo();
    }

    private final String deleteNavigationPersonalizationData(String BeanId) {
        Log.i(this.TAG, "deleteNavigationPersonalizationData:BeanId" + BeanId);
        return this.navigationApi.deleteNavigationPersonalizationData(BeanId);
    }

    private final String getNavigationLanguageSetting() {
        Log.i(this.TAG, "getNavigationLanguageSetting");
        return this.navigationApi.getNavigationLanguageSetting();
    }

    private final String getNavigationPersonalizationData(String BeanId) {
        Log.i(this.TAG, "getNavigationPersonalizationData:BeanId" + BeanId);
        return this.navigationApi.getNavigationPersonalizationData(BeanId);
    }

    private final String setNavigationPersonalizationData(String BeanId, String result) {
        Log.i(this.TAG, "setNavigationPersonalizationData:BeanId" + BeanId + "result:" + result);
        return this.navigationApi.setNavigationPersonalizationData(BeanId, result);
    }

    private final String getMapStatus() {
        Log.i(this.TAG, "getMapStatus");
        return this.navigationApi.getMapStatus();
    }

    private final String openSettingsMenu() {
        Log.i(this.TAG, "openSettingsMenu");
        return this.navigationApi.openSettingsMenu();
    }

    private final String mapToBackstage() {
        Log.i(this.TAG, "mapToBackstage");
        return this.navigationApi.mapToBackstage();
    }

    private final String mapToFrontDesk() {
        Log.i(this.TAG, "mapToFrontDesk");
        return this.navigationApi.mapToFrontDesk();
    }

    private final String setViewMode3DHeadUp() {
        Log.i(this.TAG, "setViewMode3DHeadUp");
        return this.navigationApi.setViewMode3DHeadUp();
    }

    private final String setViewMode2DHeadUp() {
        Log.i(this.TAG, "setViewMode2DHeadUp");
        return this.navigationApi.setViewMode2DHeadUp();
    }

    private final String setViewMode2DDueNorthUp() {
        Log.i(this.TAG, "setViewMode2DDueNorthUp");
        return this.navigationApi.setViewMode2DDueNorthUp();
    }

    private final String getViewMode() {
        Log.i(this.TAG, "getViewMode");
        return this.navigationApi.getViewMode();
    }

    private final String mapToMinimize() {
        Log.i(this.TAG, "mapToMinimize");
        return this.navigationApi.mapToMinimize();
    }

    private final String mapToMaximum() {
        Log.i(this.TAG, "mapToMaximum");
        return this.navigationApi.mapToMaximum();
    }

    private final String zoomOut() {
        Log.i(this.TAG, "zoomOut");
        return this.navigationApi.zoomOut();
    }

    private final String zoomIn() {
        Log.i(this.TAG, "zoomIn");
        return this.navigationApi.zoomIn();
    }

    private final String getScaleSize() {
        Log.i(this.TAG, "getScaleSize");
        return this.navigationApi.getScaleSize();
    }

    private final String closeTraffic() {
        Log.i(this.TAG, "closeTraffic");
        return this.navigationApi.closeTraffic();
    }

    private final String openTraffic() {
        Log.i(this.TAG, "openTraffic");
        return this.navigationApi.openTraffic();
    }

    private final String getTrafficStatus() {
        Log.i(this.TAG, "getTrafficStatus");
        return this.navigationApi.getTrafficStatus();
    }

    private final String getOverviewStatus() {
        Log.i(this.TAG, "getOverviewStatus");
        return this.navigationApi.getOverviewStatus();
    }

    private final String exitOverview() {
        Log.i(this.TAG, "exitOverview");
        return this.navigationApi.exitOverview();
    }

    private final String enterOverview() {
        Log.i(this.TAG, "enterOverview");
        return this.navigationApi.enterOverview();
    }

    private final String getLocation() {
        Log.i(this.TAG, "getLocation");
        return this.navigationApi.getLocation();
    }

    private final String removeFavoritePoints(PoiData poiData) {
        Log.i(this.TAG, "removeFavoritePoints");
        return this.navigationApi.removeFavoritePoints(poiData);
    }

    private final String displayFavorites() {
        Log.i(this.TAG, "displayFavorites");
        return this.navigationApi.displayFavorites();
    }

    private final String addPointsToFavorites(PoiData poiData) {
        Log.i(this.TAG, "addPointsToFavorites");
        return this.navigationApi.addPointsToFavorites(poiData);
    }

    private final String getFavoriteList() {
        Log.i(this.TAG, "getFavoriteList");
        return this.navigationApi.getFavoriteList();
    }

    private final String setCompanyAddress(PoiData poiData) {
        Log.i(this.TAG, "setCompanyAddress");
        return this.navigationApi.setCompanyAddress(poiData);
    }

    private final String setHomeAddress(PoiData poiData) {
        Log.i(this.TAG, "setHomeAddress");
        return this.navigationApi.setHomeAddress(poiData);
    }

    private final String goHome() {
        return this.navigationApi.goHome();
    }

    private final String goCompany() {
        return this.navigationApi.goCompany();
    }

    private final String displayHistoricalDestination() {
        Log.i(this.TAG, "displayHistoricalDestination");
        return this.navigationApi.displayHistoricalDestination();
    }

    private final String historicalDestinations() {
        Log.i(this.TAG, "historicalDestinations");
        return this.navigationApi.historicalDestinations();
    }

    private final String destinationInformation() {
        Log.i(this.TAG, "destinationInformation");
        return this.navigationApi.destinationInformation();
    }

    private final String selectRoute(SelectRouteData jDSelectRouteData) {
        Log.i(this.TAG, "selectRoute");
        return this.navigationApi.selectRoute(jDSelectRouteData);
    }

    private final String settingPreferences(PreferencesData preferencesData) {
        Log.i(this.TAG, "settingPreferences");
        return this.navigationApi.settingPreferences(preferencesData);
    }

    private final String queryPreferences() {
        Log.i(this.TAG, "queryPreferences");
        return this.navigationApi.queryPreferences();
    }

    private final String stopNavigation() {
        Log.i(this.TAG, "stopNavigation");
        return this.navigationApi.stopNavigation();
    }

    private final String getNavigationStatus() {
        Log.i(this.TAG, "getNavigationStatus");
        return this.navigationApi.getNavigationStatus();
    }

    private final String getTBTInfo() {
        Log.i(this.TAG, "[getTBTInfo]");
        return this.navigationApi.getTBTInfo();
    }

    private final String startNavigation() {
        Log.i(this.TAG, "startNavigation");
        return this.navigationApi.startNavigation();
    }

    private final String increaseRoutePoints(PoiData poiData) {
        Log.i(this.TAG, "increaseRoutePoints");
        return this.navigationApi.increaseRoutePoints(poiData);
    }

    private final String getWayPointList() {
        Log.i(this.TAG, "getWayPointList");
        return this.navigationApi.getWayPointList();
    }

    private final String getCompanyAddress() {
        Log.i(this.TAG, "getCompanyAddress");
        return this.navigationApi.getCompanyAddress();
    }

    private final String getHomeAddress() {
        Log.i(this.TAG, "getHomeAddress");
        return this.navigationApi.getHomeAddress();
    }

    private final String getDayNightMode() {
        Log.i(this.TAG, "getDayNightMode");
        return this.navigationApi.getDayNightMode();
    }

    public final String openMapWithLocation(GeoPosition position) {
        Intrinsics.checkNotNullParameter(position, "position");
        return this.navigationApi.openMapWithLocation(position);
    }

    @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
    public void setListener(IAidlGreatWallListener listener) {
        Log.i(this.TAG, "setListener: " + listener);
        this.resultlist.add(listener);
        Log.i(this.TAG, "resultlist.size" + this.resultlist.size());
    }

    @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
    public void async_request(String action, IAidlRequestCallback callback) throws JSONException {
        Log.i(this.TAG, "async_request:action:" + action);
        JSONObject jSONObject = new JSONObject(action);
        int i = jSONObject.getInt("moduleType");
        int i2 = jSONObject.getInt("actionType");
        if (i == 2000 && i2 == 0) {
            String keyWord = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getString("keyWord");
            int i3 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getInt("RequestID");
            Intrinsics.checkNotNullExpressionValue(keyWord, "keyWord");
            poiSearch(i3, keyWord, callback);
        } else if (i == 3000 && i2 == 0) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA);
            int i4 = jSONObject.getInt("operaType");
            PoiData endPoi = GreatWallApiTool.JDPoiData(jSONObject2.getJSONObject("endPoi").toString());
            PoiData startPoi = GreatWallApiTool.JDPoiData(jSONObject2.getJSONObject("startPoi").toString());
            List<PoiData> listJDListPoi = GreatWallApiTool.JDListPoi(jSONObject2.toString());
            Intrinsics.checkNotNullExpressionValue(endPoi, "endPoi");
            Intrinsics.checkNotNullExpressionValue(startPoi, "startPoi");
            calculateRoad(i4, endPoi, startPoi, listJDListPoi, callback);
        } else if (i == 2000 && i2 == 1) {
            String string = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).toString();
            Intrinsics.checkNotNullExpressionValue(string, "objects.getJSONObject(\"data\").toString()");
            int i5 = jSONObject.getInt("operaType");
            PeripheralData peripheralData = GreatWallApiTool.JDPaeripheralData(string);
            int requestID = peripheralData.getRequestID();
            Intrinsics.checkNotNullExpressionValue(peripheralData, "peripheralData");
            peripheralSearch(requestID, peripheralData, callback, i5);
        } else if (i == 2000 && i2 == 2) {
            String keyWord2 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getString("keyWord");
            int i6 = jSONObject.getJSONObject(JsonKey.RESULTS_DATA).getInt("RequestID");
            Intrinsics.checkNotNullExpressionValue(keyWord2, "keyWord");
            searchAlong(i6, keyWord2, callback);
        }
        Log.i(this.TAG, "moduleType:" + i + ":actionType" + i2);
    }

    @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
    public void setSurface(Surface var1, final IAidlSetSurfaceResultCallback var2) {
        Log.i(this.TAG, "setSurface");
        IGreatWallNavigationApi iGreatWallNavigationApi = this.navigationApi;
        if (iGreatWallNavigationApi == null || var1 == null) {
            return;
        }
        iGreatWallNavigationApi.setSurface(var1, new IGWSetSurfaceCallback() { // from class: com.neusoft.na.ocservice.Binder.GreatWallSDKBinder$$ExternalSyntheticLambda0
            @Override // com.neusoft.na.ocservice.greatwallsdk.IGWSetSurfaceCallback
            public final void onSetSurface(int i) throws RemoteException {
                GreatWallSDKBinder.setSurface$lambda$1(this.f$0, var2, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setSurface$lambda$1(GreatWallSDKBinder this$0, IAidlSetSurfaceResultCallback iAidlSetSurfaceResultCallback, int i) throws RemoteException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(this$0.TAG, "onSetSurface return.result=" + i);
        if (iAidlSetSurfaceResultCallback != null) {
            iAidlSetSurfaceResultCallback.onSetSurfaceCallBack(i);
        }
    }

    private final void searchAlong(int reqId, String keyWord, final IAidlRequestCallback callback) {
        Log.i(this.TAG, "searchAlong:keyWord" + keyWord);
        this.navigationApi.searchAlong(reqId, keyWord, new IGWRequestCallback() { // from class: com.neusoft.na.ocservice.Binder.GreatWallSDKBinder.searchAlong.1
            @Override // com.neusoft.na.ocservice.greatwallsdk.IGWRequestCallback
            public void onCallBack(String result) throws RemoteException {
                Intrinsics.checkNotNullParameter(result, "result");
                Log.i(GreatWallSDKBinder.this.getTAG(), "searchAlongresult:" + result);
                IAidlRequestCallback iAidlRequestCallback = callback;
                if (iAidlRequestCallback != null) {
                    iAidlRequestCallback.onCallBack(result);
                }
            }
        });
    }

    private final void peripheralSearch(int reqId, PeripheralData peripheralData, final IAidlRequestCallback callback, int operaType) {
        this.navigationApi.peripheralSearch(reqId, peripheralData, new IGWRequestCallback() { // from class: com.neusoft.na.ocservice.Binder.GreatWallSDKBinder.peripheralSearch.1
            @Override // com.neusoft.na.ocservice.greatwallsdk.IGWRequestCallback
            public void onCallBack(String result) throws RemoteException {
                Intrinsics.checkNotNullParameter(result, "result");
                Log.i(GreatWallSDKBinder.this.getTAG(), "peripheralSearchresult:" + result);
                IAidlRequestCallback iAidlRequestCallback = callback;
                if (iAidlRequestCallback != null) {
                    iAidlRequestCallback.onCallBack(result);
                }
            }
        }, operaType);
        Log.i(this.TAG, "peripheralSearch");
    }

    private final void calculateRoad(int atcionType, PoiData endPoi, PoiData startPoi, List<? extends PoiData> wayPoints, final IAidlRequestCallback callback) {
        Log.i(this.TAG, "calculateRoad");
        this.navigationApi.calculateRoad(atcionType, endPoi, startPoi, wayPoints, new IGWRequestCallback() { // from class: com.neusoft.na.ocservice.Binder.GreatWallSDKBinder.calculateRoad.1
            @Override // com.neusoft.na.ocservice.greatwallsdk.IGWRequestCallback
            public void onCallBack(String result) throws RemoteException {
                Intrinsics.checkNotNullParameter(result, "result");
                Log.i(GreatWallSDKBinder.this.getTAG(), "calculateRoadresult:" + result);
                IAidlRequestCallback iAidlRequestCallback = callback;
                if (iAidlRequestCallback != null) {
                    iAidlRequestCallback.onCallBack(result);
                }
            }
        });
    }

    private final void poiSearch(int reqId, String keyWord, final IAidlRequestCallback callback) {
        Log.i(this.TAG, "poiSearch:keyWord:" + keyWord);
        this.navigationApi.poiSearch(reqId, keyWord, new IGWRequestCallback() { // from class: com.neusoft.na.ocservice.Binder.GreatWallSDKBinder.poiSearch.1
            @Override // com.neusoft.na.ocservice.greatwallsdk.IGWRequestCallback
            public void onCallBack(String result) throws RemoteException {
                Intrinsics.checkNotNullParameter(result, "result");
                Log.i(GreatWallSDKBinder.this.getTAG(), "poiSearchresult:" + result);
                IAidlRequestCallback iAidlRequestCallback = callback;
                if (iAidlRequestCallback != null) {
                    iAidlRequestCallback.onCallBack(result);
                }
            }
        });
    }

    @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
    public void cancelListener(IAidlGreatWallListener listener) {
        Log.i(this.TAG, "cancelListener");
        if (this.resultlist.size() > 0) {
            int size = this.resultlist.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(this.resultlist.get(i), listener)) {
                    this.resultlist.remove(i);
                }
            }
        }
    }
}
