package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.source.SettingsStore;
import com.amazon.dp.logger.DPLogger;
import com.amazon.music.destination.parser.ParserUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DropBoxArtifactSource implements ArtifactSource {
    private static final long DEFAULT_LAST_GLOBAL_DROPBOX_TIME_INDEX = 0;
    private static final String DEFAULT_TIMESTAMP_JSON_STRING = "{}";
    private int mCurrentTagIdx = 0;
    protected final DropBoxManager mDropBoxManager;
    private final long mLastGlobalDedupeTimestamp;
    private final long mLastGlobalUploadTimestamp;
    private JSONObject mLatestDedupeTimestampsJson;
    private JSONObject mLatestUploadTimestampsJson;
    protected final SettingsStore mSettingsStore;
    private final List<String> mTags;
    private static final DPLogger log = new DPLogger("CrashManager.DropBoxArtifactSource");
    private static final SettingsStore.SettingsKey KEY_LAST_GLOBAL_UPLOAD_TIMESTAMP = SettingsStore.SettingsKey.DROPBOX_TIME_INDEX_KEY;
    private static final SettingsStore.SettingsKey KEY_LAST_GLOBAL_DEDUPE_TIMESTAMP = SettingsStore.SettingsKey.MAP_BUILD_INDEX_KEY;
    private static final SettingsStore.SettingsKey KEY_LATEST_UPLOAD_TIMESTAMPS_JSON = SettingsStore.SettingsKey.UPLOAD_TIMESTAMPS_JSON;
    private static final SettingsStore.SettingsKey KEY_LATEST_DEDUPE_TIMESTAMPS_JSON = SettingsStore.SettingsKey.DEDUPE_TIMESTAMPS_JSON;

    public DropBoxArtifactSource(DropBoxManager dropBoxManager, SettingsStore settingsStore, Set<String> set) {
        if (dropBoxManager == null) {
            throw new IllegalArgumentException("DropBoxManager cannot be null");
        }
        if (settingsStore == null) {
            throw new IllegalArgumentException("SettingsStore cannot be null");
        }
        if (set == null) {
            throw new IllegalArgumentException("Tags cannot be null");
        }
        this.mDropBoxManager = dropBoxManager;
        this.mSettingsStore = settingsStore;
        ArrayList arrayList = new ArrayList(set);
        this.mTags = arrayList;
        Collections.sort(arrayList);
        long j = settingsStore.getLong(KEY_LAST_GLOBAL_UPLOAD_TIMESTAMP, 0L);
        this.mLastGlobalUploadTimestamp = j;
        this.mLastGlobalDedupeTimestamp = settingsStore.getLong(KEY_LAST_GLOBAL_DEDUPE_TIMESTAMP, j);
        JSONObject savedTimestampJson = getSavedTimestampJson(KEY_LATEST_UPLOAD_TIMESTAMPS_JSON, DEFAULT_TIMESTAMP_JSON_STRING);
        this.mLatestUploadTimestampsJson = savedTimestampJson;
        this.mLatestDedupeTimestampsJson = getSavedTimestampJson(KEY_LATEST_DEDUPE_TIMESTAMPS_JSON, savedTimestampJson.toString());
    }

    private JSONObject getSavedTimestampJson(SettingsStore.SettingsKey settingsKey, String str) {
        String string = this.mSettingsStore.getString(settingsKey, str);
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            log.warn("getSavedTimestampJson", "Illegal JSON stored in key " + settingsKey + ": " + string + ". Ignoring.", e);
            return new JSONObject();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00d5, code lost:
    
        r16.mCurrentTagIdx++;
        r4 = r4;
     */
    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.device.crashmanager.Artifact getNextArtifact(com.amazon.client.metrics.common.MetricEvent r17, java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.crashmanager.source.DropBoxArtifactSource.getNextArtifact(com.amazon.client.metrics.common.MetricEvent, java.lang.String):com.amazon.device.crashmanager.Artifact");
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public void saveCurrentIndex() {
        this.mSettingsStore.saveString(KEY_LATEST_UPLOAD_TIMESTAMPS_JSON, this.mLatestUploadTimestampsJson.toString());
    }

    public void saveBuildMapIndex() {
        this.mSettingsStore.saveString(KEY_LATEST_DEDUPE_TIMESTAMPS_JSON, this.mLatestDedupeTimestampsJson.toString());
    }

    private long getLastTimestamp(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("The tag may not be null");
        }
        if (CrashManagerActions.BUILD_MAP.equals(str2)) {
            return this.mLatestDedupeTimestampsJson.optLong(str, this.mLastGlobalDedupeTimestamp);
        }
        return this.mLatestUploadTimestampsJson.optLong(str, this.mLastGlobalUploadTimestamp);
    }

    private void updateLastTimestamp(String str, String str2, long j) {
        if (str == null) {
            throw new NullPointerException("The tag may not be null");
        }
        try {
            if (CrashManagerActions.BUILD_MAP.equals(str2)) {
                this.mLatestDedupeTimestampsJson.put(str, j);
            } else {
                this.mLatestUploadTimestampsJson.put(str, j);
            }
        } catch (JSONException e) {
            log.fatal("updateLastTimestamp", "Unable to update timestamp!", ParserUtil.TAG_QUERY_PARAM_NAME, str, "reason", str2, e);
        }
    }
}
