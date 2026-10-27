package com.android.settingslib.core.instrumentation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MetricsFeatureProvider {
    public static final String EXTRA_SOURCE_METRICS_CATEGORY = ":settings:source_metrics";
    protected List<LogWriter> mLoggerWriters = new ArrayList();

    public MetricsFeatureProvider() {
        installLogWriters();
    }

    protected void installLogWriters() {
        this.mLoggerWriters.add(new EventLogWriter());
    }

    public int getAttribution(Activity activity) {
        Intent intent;
        if (activity != null && (intent = activity.getIntent()) != null) {
            return intent.getIntExtra(EXTRA_SOURCE_METRICS_CATEGORY, 0);
        }
        return 0;
    }

    public void visible(Context context, int source, int category, int latency) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.visible(context, source, category, latency);
        }
    }

    public void hidden(Context context, int category, int visibleTime) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.hidden(context, category, visibleTime);
        }
    }

    public void action(Context context, int category, Pair<Integer, Object>... taggedData) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.action(context, category, taggedData);
        }
    }

    public void action(Context context, int category, String pkg) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.action(context, category, pkg);
        }
    }

    public void action(int attribution, int action, int pageId, String key, int value) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.action(attribution, action, pageId, key, value);
        }
    }

    public void action(Context context, int category, int value) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.action(context, category, value);
        }
    }

    public void action(Context context, int category, boolean value) {
        for (LogWriter writer : this.mLoggerWriters) {
            writer.action(context, category, value);
        }
    }

    public int getMetricsCategory(Object object) {
        if (object == null || !(object instanceof Instrumentable)) {
            return 0;
        }
        return ((Instrumentable) object).getMetricsCategory();
    }

    public boolean logClickedPreference(Preference preference, int sourceMetricsCategory) {
        if (preference == null) {
            return false;
        }
        return logSettingsTileClick(preference.getKey(), sourceMetricsCategory) || logStartedIntent(preference.getIntent(), sourceMetricsCategory) || logSettingsTileClick(preference.getFragment(), sourceMetricsCategory);
    }

    public boolean logStartedIntent(Intent intent, int sourceMetricsCategory) {
        if (intent == null) {
            return false;
        }
        ComponentName cn = intent.getComponent();
        return logSettingsTileClick(cn != null ? cn.flattenToString() : intent.getAction(), sourceMetricsCategory);
    }

    public boolean logStartedIntentWithProfile(Intent intent, int sourceMetricsCategory, boolean isWorkProfile) {
        if (intent == null) {
            return false;
        }
        ComponentName cn = intent.getComponent();
        String key = cn != null ? cn.flattenToString() : intent.getAction();
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append(isWorkProfile ? "/work" : "/personal");
        return logSettingsTileClick(sb.toString(), sourceMetricsCategory);
    }

    public boolean logSettingsTileClick(String logKey, int sourceMetricsCategory) {
        if (TextUtils.isEmpty(logKey)) {
            return false;
        }
        action(sourceMetricsCategory, 830, 0, logKey, 0);
        return true;
    }
}
