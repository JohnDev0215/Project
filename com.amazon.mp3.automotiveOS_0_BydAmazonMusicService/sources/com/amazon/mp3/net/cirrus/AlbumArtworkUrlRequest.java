package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlCache;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumArtworkUrlRequest {
    private static final String TAG = "AlbumArtworkUrlRequest";
    private final CirrusV3Request mGetTracksByIdRequest = CirrusV3Request.GetTracksById;

    public Response requestAlbumArtworkUrls(String trackId, String albumId, AlbumArtworkUrlCache cache, AlbumArtworkUrlCache.AlbumArtworkUrlCacheType cacheType) throws ServiceException, JSONException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        HashMap map = new HashMap();
        map.put(trackId, albumId);
        return requestAlbumArtworkUrls(map, cache, cacheType);
    }

    public Response requestAlbumArtworkUrls(Map<String, String> trackIds, AlbumArtworkUrlCache cache, AlbumArtworkUrlCache.AlbumArtworkUrlCacheType cacheType) throws ServiceException, JSONException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return parseResponseJson(this.mGetTracksByIdRequest.execute(buildJsonRequestParam(trackIds)), trackIds, cache, cacheType);
    }

    private JSONObject buildJsonRequestParam(Map<String, String> trackIds) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = trackIds.keySet().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        try {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put("albumCoverImageLarge");
            jSONArray2.put("albumCoverImageMedium");
            jSONArray2.put("albumCoverImageXL");
            jSONObject.put("trackIdList", jSONArray);
            jSONObject.put("attributeList", jSONArray2);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    private Response parseResponseJson(JSONObject responseJson, Map<String, String> trackIds, AlbumArtworkUrlCache cache, AlbumArtworkUrlCache.AlbumArtworkUrlCacheType cacheType) throws JSONException {
        String str;
        AlbumMetadata albumMetadata;
        String string;
        String str2 = "";
        Response response = new Response();
        try {
            JSONArray jSONArray = responseJson.getJSONArray("trackList");
            ArrayList arrayList = new ArrayList(jSONArray.length());
            response.mAlbumMetadataList = arrayList;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
                    albumMetadata = new AlbumMetadata();
                    string = jSONObject.getString("trackId");
                    try {
                        albumMetadata.mTrackId = string;
                        albumMetadata.mAlbumUrlLarge = jSONObject2.optString("albumCoverImageLarge");
                        albumMetadata.mAlbumUrlMedium = jSONObject2.optString("albumCoverImageMedium");
                        albumMetadata.mAlbumUrlXL = jSONObject2.optString("albumCoverImageXL");
                        arrayList.add(albumMetadata);
                        str = trackIds.get(string);
                    } catch (JSONException e) {
                        e = e;
                        str = "";
                    }
                } catch (JSONException e2) {
                    e = e2;
                    str = "";
                }
                try {
                    cache.put(cacheType, str, albumMetadata);
                } catch (JSONException e3) {
                    e = e3;
                    str2 = string;
                    Log.error(TAG, "JSONException while parsing JSONObject for track id: " + str2 + " album id:  " + str + " json: " + response.toString());
                    throw e;
                }
            }
            return response;
        } catch (JSONException e4) {
            Log.error(TAG, "JSONException while parsing track list response: " + response.toString());
            throw e4;
        }
    }

    public static class Response {
        private List<AlbumMetadata> mAlbumMetadataList;

        public List<AlbumMetadata> getAlbumMetadataList() {
            return this.mAlbumMetadataList;
        }
    }

    public static class AlbumMetadata {
        private String mAlbumUrlLarge;
        private String mAlbumUrlMedium;
        private String mAlbumUrlXL;
        private String mTrackId;

        public String getAlbumUrlLarge() {
            return this.mAlbumUrlLarge;
        }

        public String getAlbumUrlMedium() {
            return this.mAlbumUrlMedium;
        }

        public String getAlbumUrlXL() {
            return this.mAlbumUrlXL;
        }

        public JSONObject toJSON() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("albumCoverImageLarge", this.mAlbumUrlLarge);
            jSONObject.put("albumCoverImageMedium", this.mAlbumUrlMedium);
            jSONObject.put("albumCoverImageXL", this.mAlbumUrlXL);
            jSONObject.put("trackId", this.mTrackId);
            return jSONObject;
        }

        public static AlbumMetadata fromJSON(JSONObject object) throws JSONException {
            AlbumMetadata albumMetadata = new AlbumMetadata();
            albumMetadata.mAlbumUrlLarge = object.optString("albumCoverImageLarge");
            albumMetadata.mAlbumUrlMedium = object.optString("albumCoverImageLarge");
            albumMetadata.mAlbumUrlXL = object.optString("albumCoverImageXL");
            albumMetadata.mTrackId = object.optString("trackId");
            return albumMetadata;
        }
    }
}
