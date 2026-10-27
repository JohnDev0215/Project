package com.amazon.music.arcus;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.arcus.ConfigRefreshGate;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class ConfigThresholdManager implements ConfigRefreshGate {
    private final String appConfigId;
    private final Context context;
    private long lastRefreshTime = -1;
    private long thresholdMs = DEFAULT_THRESHOLD_MS;
    private static final Logger LOG = LoggerFactory.getLogger(ConfigThresholdManager.class.getSimpleName());
    static final long DEFAULT_THRESHOLD_MS = TimeUnit.HOURS.toMillis(24);

    public ConfigThresholdManager(Context context, String str) {
        this.context = (Context) Validate.notNull(context);
        this.appConfigId = (String) Validate.notNull(str);
    }

    @Override // com.amazon.music.arcus.ConfigRefreshGate
    public ConfigRefreshGate.ConfigRefreshGateState getGateState() {
        return needsRefresh() ? ConfigRefreshGate.ConfigRefreshGateState.REFRESH_NEEDED : ConfigRefreshGate.ConfigRefreshGateState.REFRESH_ALLOWED;
    }

    public boolean needsRefresh() {
        long lastRefreshTime = getLastRefreshTime();
        return lastRefreshTime == -1 || System.currentTimeMillis() > lastRefreshTime + this.thresholdMs;
    }

    public void onConfigRefreshed(Configuration configuration, boolean z) {
        setLastRefreshTime();
        if (z) {
            try {
                this.thresholdMs = getThresholdFromConfiguration(configuration);
            } catch (JSONException unused) {
            }
        }
    }

    private long getLastRefreshTime() {
        long j = this.lastRefreshTime;
        return j != -1 ? j : getSharedPreferences().getLong(this.appConfigId, -1L);
    }

    private void setLastRefreshTime() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor editorEdit = getSharedPreferences().edit();
        editorEdit.putLong(this.appConfigId, jCurrentTimeMillis);
        editorEdit.apply();
        this.lastRefreshTime = jCurrentTimeMillis;
    }

    private SharedPreferences getSharedPreferences() {
        return this.context.getSharedPreferences("ArcusConfigManagerSharedPrefs", 0);
    }

    private long getThresholdFromConfiguration(Configuration configuration) throws JSONException {
        return configuration.getAsJsonObject().getLong("refreshThreshold");
    }
}
