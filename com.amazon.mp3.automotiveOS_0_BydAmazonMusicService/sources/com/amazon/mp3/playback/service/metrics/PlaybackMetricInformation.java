package com.amazon.mp3.playback.service.metrics;

import android.os.Bundle;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackMetricInformation {
    private DirectedPlayStatus mDirectedPlayStatus;
    private PlaybackPageType mPageType;

    public PlaybackMetricInformation(DirectedPlayStatus isDirectedPlay, PlaybackPageType pageType) {
        DirectedPlayStatus directedPlayStatus = DirectedPlayStatus.DIRECTED;
        this.mDirectedPlayStatus = isDirectedPlay;
        this.mPageType = pageType;
    }

    public DirectedPlayStatus getIsDirectedPlay() {
        return this.mDirectedPlayStatus;
    }

    public void setPageType(PlaybackPageType pageType) {
        this.mPageType = pageType;
    }

    public PlaybackPageType getPageType() {
        return this.mPageType;
    }

    public void writeToJSONObject(JSONObject dest) throws JSONException {
        if (!DirectedPlayStatus.UNKNOWN.equals(this.mDirectedPlayStatus)) {
            dest.put("isDirectedPlay", DirectedPlayStatus.DIRECTED.equals(this.mDirectedPlayStatus));
        }
        dest.put("pageType", this.mPageType.name());
    }

    public static PlaybackMetricInformation createFromJSONObject(JSONObject source) throws JSONException {
        DirectedPlayStatus directedPlayStatus;
        PlaybackPageType playbackPageType;
        if (source.has("isDirectedPlay")) {
            directedPlayStatus = source.getBoolean("isDirectedPlay") ? DirectedPlayStatus.DIRECTED : DirectedPlayStatus.NOT_DIRECTED;
        } else {
            directedPlayStatus = DirectedPlayStatus.UNKNOWN;
        }
        if (source.has("pageType")) {
            playbackPageType = (PlaybackPageType) Enum.valueOf(PlaybackPageType.class, source.getString("pageType"));
        } else {
            playbackPageType = PlaybackPageType.UNKNOWN;
        }
        return new PlaybackMetricInformation(directedPlayStatus, playbackPageType);
    }

    public Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        if (!DirectedPlayStatus.UNKNOWN.equals(this.mDirectedPlayStatus)) {
            bundle.putBoolean("isDirectedPlay", DirectedPlayStatus.DIRECTED.equals(this.mDirectedPlayStatus));
        }
        bundle.putString("pageType", this.mPageType.name());
        return bundle;
    }
}
