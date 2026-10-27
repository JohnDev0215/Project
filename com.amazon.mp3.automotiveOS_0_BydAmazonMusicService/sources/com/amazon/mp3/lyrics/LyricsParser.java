package com.amazon.mp3.lyrics;

import com.amazon.mp3.lyrics.exception.LyricsParseExeption;
import com.amazon.mp3.lyrics.item.ExplicitStatus;
import com.amazon.mp3.lyrics.item.ExplicitWord;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.lyrics.item.LyricsSharingConfig;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsParser {
    public Lyrics parse(String json) throws LyricsParseExeption {
        try {
            return parse(new JSONObject(json));
        } catch (JSONException unused) {
            throw new LyricsParseExeption("Could not read lyrics");
        }
    }

    public Lyrics parse(JSONObject json) throws LyricsParseExeption {
        Lyrics lyrics = new Lyrics();
        parse(json, lyrics);
        return lyrics;
    }

    public void parse(JSONObject json, Lyrics lyrics) throws LyricsParseExeption {
        try {
            lyrics.setSource(json.optString("lyricsSource"));
            lyrics.setExternalId(json.optString("externalTrackId"));
            JSONObject jSONObject = json.getJSONObject("trackAsinAndMarketplace");
            lyrics.setAsin(jSONObject.getString("asin"));
            lyrics.setMarketplace(jSONObject.optString("marketplaceId"));
            if (json.has("lyricsSharingConfig")) {
                lyrics.setLyricsSharingConfig(new LyricsSharingConfig(Boolean.valueOf(json.getJSONObject("lyricsSharingConfig").getBoolean("isLyricsSharable")).booleanValue()));
            }
            JSONObject jSONObject2 = json.getJSONObject("lyrics");
            parseLines(lyrics, jSONObject2.getJSONArray("lines"));
            lyrics.setCopyrights(convertToStringArray(jSONObject2.optJSONArray("copyrightNotices")));
            lyrics.setWriter(convertToStringArray(jSONObject2.optJSONArray("writers")));
            lyrics.setExplicitStatus(ExplicitStatus.fromString(jSONObject2.optString("explicitLyricsStatus")));
        } catch (JSONException unused) {
            throw new LyricsParseExeption("Parse error");
        }
    }

    private String[] convertToStringArray(JSONArray array) throws JSONException {
        if (array == null || array.length() == 0) {
            return new String[0];
        }
        String[] strArr = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            strArr[i] = array.getString(i);
        }
        return strArr;
    }

    private void parseLines(Lyrics lyrics, JSONArray linesArray) throws JSONException {
        List<LyricsLine> lines = lyrics.getLines();
        for (int i = 0; i < linesArray.length(); i++) {
            lines.add(parseLine(linesArray.getJSONObject(i)));
        }
        lyrics.setLines(lines);
    }

    private LyricsLine parseLine(JSONObject json) throws JSONException {
        LyricsLine lyricsLine = new LyricsLine();
        lyricsLine.setLine(json.getString("text"));
        lyricsLine.setStartTime(json.optLong("startTime", -1L));
        lyricsLine.setEndTime(json.optLong("endTime", -1L));
        JSONArray jSONArrayOptJSONArray = json.optJSONArray("explicitWordMarkerList");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i);
                lyricsLine.addExplicitWord(new ExplicitWord(jSONObject.getInt("startIndex"), jSONObject.getInt("endIndex")));
            }
        }
        return lyricsLine;
    }
}
