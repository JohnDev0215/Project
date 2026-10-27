package com.amazon.mp3.catalog.fragment.provider;

import android.net.Uri;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.amazon.mp3.catalog.fragment.model.FollowedArtistModel;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.connect.firefly.FireFlyService;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: FollowedArtistRetriever.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J8\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/provider/FollowedArtistRetriever;", "", "fireFlyService", "Lcom/amazon/music/connect/firefly/FireFlyService;", "(Lcom/amazon/music/connect/firefly/FireFlyService;)V", "emitFireflyCallMetrics", "", "eventName", "", "startTime", "", "endTime", "errorResponse", "exception", "Ljava/lang/Exception;", "getFollowedArtist", "Lcom/amazon/mp3/catalog/fragment/model/FollowedArtistModel;", "nextPageToken", "pageSize", "", "parseFireFlyResponse", "response", "Lorg/json/JSONObject;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FollowedArtistRetriever {
    private static final String TAG = FollowedArtistRetriever.class.getSimpleName();
    private final FireFlyService fireFlyService;

    public FollowedArtistRetriever(FireFlyService fireFlyService) {
        this.fireFlyService = fireFlyService;
    }

    public final FollowedArtistModel getFollowedArtist(String nextPageToken, int pageSize) {
        Exception exc;
        JSONObject jSONObjectQuery;
        Intrinsics.checkNotNullParameter(nextPageToken, "nextPageToken");
        String str = "query { user { followedArtists(limit: " + pageSize + ", cursor: \"" + nextPageToken + "\") { pageInfo { hasNextPage token } edgeCount edges { node { id url name images { width height url } } } } } }";
        MetricsLogger.sendEvent(FlexEvent.builder("followedArtistsFireflyRequest").build());
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            FireFlyService fireFlyService = this.fireFlyService;
            jSONObjectQuery = fireFlyService == null ? null : fireFlyService.query(str);
            try {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                FollowedArtistModel fireFlyResponse = parseFireFlyResponse(jSONObjectQuery);
                emitFireflyCallMetrics("followedArtistsFireflyResponseSuccess", jCurrentTimeMillis, jCurrentTimeMillis2, null, null);
                Log.debug(TAG, "FireFly call for fetching followed artist successful, retrieved " + fireFlyResponse.getFollowedArtists().size() + " artists");
                return fireFlyResponse;
            } catch (Exception e) {
                exc = e;
                String str2 = TAG;
                Log.error(str2, "Error while fetching firefly response for followed artists", exc);
                Log.debug(str2, Intrinsics.stringPlus("Firefly request: ", str));
                Log.debug(str2, Intrinsics.stringPlus("Firefly response: ", jSONObjectQuery));
                emitFireflyCallMetrics("followedArtistsFireflyResponseFailure", jCurrentTimeMillis, System.currentTimeMillis(), jSONObjectQuery == null ? null : jSONObjectQuery.toString(), exc);
                return new FollowedArtistModel(null, new ArrayList(), 1, null);
            }
        } catch (Exception e2) {
            exc = e2;
            jSONObjectQuery = null;
        }
    }

    private final void emitFireflyCallMetrics(String eventName, long startTime, long endTime, String errorResponse, Exception exception) {
        FlexEvent.Builder builder = FlexEvent.builder(eventName);
        if (exception != null) {
            builder.withFlexStr1(exception.getMessage());
        }
        if (errorResponse != null) {
            builder.withFlexStr2(errorResponse);
        }
        MetricsLogger.sendEvent(builder.build());
        MetricsLogger.sendEvent(FlexEvent.builder("followedArtistsFireflyLatency").withFlexNum1(Float.valueOf(endTime - startTime)).build());
    }

    private final FollowedArtistModel parseFireFlyResponse(JSONObject response) throws Exception {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        String string;
        JSONObject jSONObject4;
        JSONObject jSONObject5;
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = response == null ? null : response.optJSONArray(MAPWebViewEventHelper.KEY_ERRORS);
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            Log.debug(TAG, Intrinsics.stringPlus("Error while fetching followed Artist, firefly response:", response));
            throw new Exception(jSONArrayOptJSONArray.toString());
        }
        JSONObject jSONObject6 = (response == null || (jSONObject = response.getJSONObject("data")) == null || (jSONObject2 = jSONObject.getJSONObject("user")) == null) ? null : jSONObject2.getJSONObject("followedArtists");
        String string2 = (!Intrinsics.areEqual((jSONObject6 != null && (jSONObject3 = jSONObject6.getJSONObject("pageInfo")) != null) ? Boolean.valueOf(jSONObject3.getBoolean("hasNextPage")) : null, Boolean.TRUE) || jSONObject6 == null || (jSONObject5 = jSONObject6.getJSONObject("pageInfo")) == null) ? null : jSONObject5.getString("token");
        JSONArray jSONArray = jSONObject6 == null ? null : jSONObject6.getJSONArray("edges");
        if (jSONArray != null) {
            int length = jSONArray.length();
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                JSONObject jSONObject7 = jSONArray.getJSONObject(i);
                JSONObject jSONObject8 = jSONObject7 == null ? null : jSONObject7.getJSONObject("node");
                if (jSONObject8 != null) {
                    if (jSONObject8.isNull("images")) {
                        string = null;
                    } else {
                        JSONArray jSONArray2 = jSONObject8.getJSONArray("images");
                        string = (jSONArray2 == null || (jSONObject4 = jSONArray2.getJSONObject(0)) == null) ? null : jSONObject4.getString(ImagesContract.URL);
                    }
                    String string3 = jSONObject8.getString("id");
                    String string4 = jSONObject8.getString("name");
                    String string5 = jSONObject8.getString("name");
                    Uri uri = Uri.parse(jSONObject8.getString(ImagesContract.URL));
                    Intrinsics.checkNotNullExpressionValue(uri, "parse(node.getString(URL_PROPERTY_KEY))");
                    arrayList.add(new ArtistMetadata(null, uri, null, null, string3, string5, string4, string, null, null, null, true, null, null, false, null, null, false, false, null, 1046285, null));
                }
                i = i2;
            }
        }
        return new FollowedArtistModel(string2, arrayList);
    }
}
