package com.amplifyframework.logging;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class AndroidLoggingPlugin extends LoggingPlugin<Void> {
    private static final String AMPLIFY_NAMESPACE = "amplify";
    private final LogLevel defaultLoggerThreshold;

    @Override // com.amplifyframework.core.plugin.Plugin
    public void configure(JSONObject jSONObject, @NonNull Context context) {
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @Nullable
    public Void getEscapeHatch() {
        return null;
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getPluginKey() {
        return "AndroidLoggingPlugin";
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getVersion() {
        return "1.37.11";
    }

    public AndroidLoggingPlugin() {
        this(LogLevel.INFO);
    }

    public AndroidLoggingPlugin(@NonNull LogLevel logLevel) {
        this.defaultLoggerThreshold = logLevel;
    }

    @Override // com.amplifyframework.logging.LoggingCategoryBehavior
    @NonNull
    public Logger forNamespace(@Nullable String str) {
        if (str == null) {
            str = AMPLIFY_NAMESPACE;
        }
        return new AndroidLogger(str, this.defaultLoggerThreshold);
    }
}
