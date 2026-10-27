package com.amazon.mp3.lyrics;

import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.lyrics.exception.LyricsParseExeption;
import com.amazon.mp3.lyrics.exception.NetworkException;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.lyrics.item.LyricsStatusChanges;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.lyrics.item.UnparsedLyrics;
import com.amazon.mp3.lyrics.net.LyricsRequest;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
class LibraryLyricsFetcher implements LyricsFetcher {
    private static final String TAG = "LibraryLyricsFetcher";
    private final Context mContext;
    private final String mMusicTerritory;

    public LibraryLyricsFetcher(final Context context, String musicTerritory) {
        this.mContext = context;
        this.mMusicTerritory = musicTerritory;
    }

    @Override // com.amazon.mp3.lyrics.LyricsFetcher
    public Map<LyricsTrackInfo, UnparsedLyrics> downloadLyrics(final Collection<LyricsTrackInfo> infoList) throws NetworkException {
        HashMap map = new HashMap(infoList.size());
        ArrayList arrayList = new ArrayList(25);
        Iterator<LyricsTrackInfo> it = infoList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            arrayList.add(it.next());
            if (i % 25 == 0 || i == infoList.size()) {
                try {
                    map.putAll(executeLyricsDownload(arrayList));
                } catch (JSONException e) {
                    Log.warning(TAG, "Could not fetch lyrics ", e);
                } catch (Exception unused) {
                    throw new NetworkException("Network connection issue");
                }
                arrayList.clear();
            }
        }
        return map;
    }

    @Override // com.amazon.mp3.lyrics.LyricsFetcher
    public LyricsStatusChanges getRefreshList(long startDate, String pageToken) throws NetworkException {
        try {
            return parseStatusChangeResponse(LyricsRequest.GetLyricsStatusChangesByDate.execute(createStatusChangeRequest(startDate, LyricsStatusChanges.StatusChangeType.UPDATE, pageToken)));
        } catch (Exception e) {
            throw new NetworkException("Could not fetch lyrics status", e);
        }
    }

    @Override // com.amazon.mp3.lyrics.LyricsFetcher
    public LyricsStatusChanges getTakedownList(long startDate, String pageToken) throws NetworkException {
        try {
            return parseStatusChangeResponse(LyricsRequest.GetLyricsStatusChangesByDate.execute(createStatusChangeRequest(startDate, LyricsStatusChanges.StatusChangeType.TAKEDOWN, pageToken)));
        } catch (Exception e) {
            throw new NetworkException("Could not fetch lyrics status", e);
        }
    }

    private Map<LyricsTrackInfo, UnparsedLyrics> executeLyricsDownload(List<LyricsTrackInfo> infoList) throws JSONException, NetworkException {
        try {
            return parseLyricsDownloadResponse(LyricsRequest.GetLyricsByTrackAsins.execute(createLyricsDownloadRequest(infoList)));
        } catch (JSONException e) {
            throw new JSONException(e.getMessage());
        } catch (Exception e2) {
            throw new NetworkException("Could not download lyrics", e2);
        }
    }

    private JSONObject createLyricsDownloadRequest(List<LyricsTrackInfo> infoList) throws JSONException {
        if (infoList == null || infoList.size() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (LyricsTrackInfo lyricsTrackInfo : infoList) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("asin", lyricsTrackInfo.getAsin());
            jSONObject2.put(Splash.PARAMS_MUSIC_TERRITORY, this.mMusicTerritory);
            jSONArray.put(jSONObject2);
        }
        jSONObject.put("trackAsinsAndMarketplaceList", jSONArray);
        return jSONObject;
    }

    private Map<LyricsTrackInfo, UnparsedLyrics> parseLyricsDownloadResponse(JSONObject response) throws JSONException {
        HashMap map = new HashMap();
        JSONArray jSONArray = response.getJSONArray("lyricsResponseList");
        LyricsParser lyricsParser = new LyricsParser();
        Lyrics lyrics = new Lyrics();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getInt("lyricsResponseCode") == 1002) {
                try {
                    lyricsParser.parse(jSONObject, lyrics);
                    map.put(new FetcherTrackInfo(lyrics.getAsin(), lyrics.getMarketplace()), new UnparsedLyrics(LyricsStatus.AVAILABLE, jSONObject.toString()));
                    lyrics.clear();
                } catch (LyricsParseExeption e) {
                    Log.warning(TAG, e.getMessage());
                }
            } else {
                map.put(new ParsedTrackInfo(jSONObject.getJSONObject("trackAsinAndMarketplace")), new UnparsedLyrics(LyricsStatus.NO_LYRICS, null));
            }
        }
        return map;
    }

    private JSONObject createStatusChangeRequest(long startDate, LyricsStatusChanges.StatusChangeType changeType, String pageToken) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("startDate", startDate);
        jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, this.mMusicTerritory);
        jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, changeType.toString());
        if (pageToken != null) {
            jSONObject.put("pagingToken", pageToken);
        }
        return jSONObject;
    }

    private LyricsStatusChanges parseStatusChangeResponse(JSONObject jsonResponse) throws JSONException {
        LyricsStatusChanges lyricsStatusChanges = new LyricsStatusChanges();
        lyricsStatusChanges.setPagingToken(jsonResponse.optString("pagingToken"));
        lyricsStatusChanges.setLastFetchTimestamp(jsonResponse.getLong("takeDownsCompletedToDate"));
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jsonResponse.getJSONArray("statusChanges");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            lyricsStatusChanges.setStatusChangeType(jSONObject.getString("statusChangeType"));
            arrayList.add(jSONObject.getString("asin"));
        }
        lyricsStatusChanges.setAsinList(arrayList);
        return lyricsStatusChanges;
    }

    private static class FetcherTrackInfo extends LyricsTrackInfo {
        private final String mAsin;
        private final String mMarketplaceId;

        public FetcherTrackInfo(String asin, String marketplaceId) {
            this.mAsin = asin;
            this.mMarketplaceId = marketplaceId;
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getAsin() {
            return this.mAsin;
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getMarketplaceId() {
            return this.mMarketplaceId;
        }
    }

    private static class ParsedTrackInfo extends LyricsTrackInfo {
        private final String mAsin;
        private final String mMarketplaceId;

        public ParsedTrackInfo(JSONObject jsonObject) throws JSONException {
            this.mAsin = jsonObject.getString("asin");
            this.mMarketplaceId = jsonObject.getString("marketplaceId");
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getAsin() {
            return this.mAsin;
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getMarketplaceId() {
            return this.mMarketplaceId;
        }
    }
}
