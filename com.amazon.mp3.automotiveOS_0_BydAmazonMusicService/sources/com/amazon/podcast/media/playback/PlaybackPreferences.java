package com.amazon.podcast.media.playback;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.podcast.mappers.Mappers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class PlaybackPreferences {
    private static final Logger logger = LoggerFactory.getLogger("PlaybackPreferences");
    private SharedPreferences.Editor editor;
    private final Object lock = new Object();
    private SharedPreferences sharedPreferences;

    public PlaybackPreferences(final Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PodcastPlayback", 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void writePodcastLastPlayed(final boolean isPodcastLastPlayed) {
        this.editor.putBoolean("LastPlayedPodcast", isPodcastLastPlayed);
        if (!isPodcastLastPlayed) {
            this.editor.putString("RestorePodcastMethod", null);
            this.editor.putString("PodcastDownloadedId", null);
        }
        this.editor.apply();
    }

    public boolean isPodcastLastPlayed() {
        return this.sharedPreferences.getBoolean("LastPlayedPodcast", false);
    }

    public void writeRestoreMiniPlayerMethods(final List<Method> methods) {
        if (methods == null) {
            return;
        }
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(methods);
            if (strWriteValueAsString == null) {
                return;
            }
            this.editor.putString("RestorePodcastMethod", strWriteValueAsString);
            this.editor.apply();
        } catch (IOException e) {
            Logger logger2 = logger;
            logger2.error("Error serializing onRestore method");
            logger2.error("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void writeRestoreAndStartMiniPlayerMethods(final List<Method> methods) {
        if (methods == null) {
            return;
        }
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(methods);
            if (strWriteValueAsString == null) {
                return;
            }
            this.editor.putString("RestoreAndStartPodcastMethod", strWriteValueAsString);
            this.editor.apply();
        } catch (IOException e) {
            Logger logger2 = logger;
            logger2.error("Error serializing onRestoreAndStart method");
            logger2.error("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Method> readRestoreMiniPlayerMethods() {
        List<String> listJsonToArray = null;
        String string = this.sharedPreferences.getString("RestorePodcastMethod", null);
        if (string == null) {
            return null;
        }
        try {
            listJsonToArray = jsonToArray(string);
        } catch (JSONException e) {
            logger.error("Error converting onRestore json String to json List");
            logger.error("Exception: " + e.getMessage());
        }
        return buildMethodList(listJsonToArray);
    }

    public String downloadedEpisodeId() {
        return this.sharedPreferences.getString("PodcastDownloadedId", null);
    }

    public void writeEpisodeDownloadedId(final String downloadId) {
        this.editor.putString("PodcastDownloadedId", downloadId);
        this.editor.apply();
    }

    public PlaybackSpeed readPlaybackSpeed() {
        return matchFloatToPlaybackSpeed(this.sharedPreferences.getFloat("PlaybackSpeed", -1.0f));
    }

    public void writePlaybackSpeed(final PlaybackSpeed speed) {
        this.editor.putFloat("PlaybackSpeed", speed.getValue());
        this.editor.apply();
    }

    public long readHeartbeatWriteFrequency() {
        long j;
        synchronized (this.lock) {
            j = this.sharedPreferences.getLong("HeartbeatWriteFrequency", 5L);
        }
        return j;
    }

    public void writeHeartbeatWriteFrequency(final long writeFrequencySeconds) {
        try {
            synchronized (this.lock) {
                this.editor.putLong("HeartbeatWriteFrequency", writeFrequencySeconds);
                this.editor.apply();
            }
        } catch (Exception e) {
            logger.error("Unable to persist heartbeat write frequency of {}.", Long.valueOf(writeFrequencySeconds), e);
        }
    }

    public long readHeartbeatWriteAndReportFrequency() {
        long j;
        synchronized (this.lock) {
            j = this.sharedPreferences.getLong("HeartbeatWriteAndReportFrequency", 300L);
        }
        return j;
    }

    public void writeHeartbeatWriteAndReportFrequency(final long writeAndReportFrequencySeconds) {
        try {
            synchronized (this.lock) {
                this.editor.putLong("HeartbeatWriteAndReportFrequency", writeAndReportFrequencySeconds);
                this.editor.apply();
            }
        } catch (Exception e) {
            logger.error("Unable to persist heartbeat write and report frequency of {}.", Long.valueOf(writeAndReportFrequencySeconds), e);
        }
    }

    public void clear() {
        synchronized (this.lock) {
            this.editor.remove("LastPlayedPodcast");
            this.editor.remove("RestorePodcastMethod");
            this.editor.remove("PodcastDownloadedId");
            this.editor.remove("PlaybackSpeed");
            this.editor.remove("HeartbeatWriteFrequency");
            this.editor.remove("HeartbeatWriteAndReportFrequency");
            this.editor.apply();
        }
    }

    private PlaybackSpeed matchFloatToPlaybackSpeed(final float speed) {
        PlaybackSpeed playbackSpeedLookupByValue = PlaybackSpeed.lookupByValue(speed);
        return playbackSpeedLookupByValue == null ? PlaybackSpeed.ONE : playbackSpeedLookupByValue;
    }

    private List<String> jsonToArray(final String json) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray(json);
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    private List<Method> buildMethodList(final List<String> jsonMethodList) {
        if (jsonMethodList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<String> it = jsonMethodList.iterator();
            while (it.hasNext()) {
                arrayList.add((Method) Mappers.mapper().readValue(it.next(), Method.class));
            }
        } catch (IOException e) {
            Logger logger2 = logger;
            logger2.error("Error converting stored onRestore methods back to Java object");
            logger2.error("Exception: " + e.getMessage());
        }
        return arrayList;
    }
}
