package com.amazon.avod.config.internal;

import amazon.android.config.ConfigRegistry;
import amazon.android.config.ConfigType;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.AppStartConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerAppStartupConfigPersistence {
    private final ConfigRegistry mConfigRegistry = ConfigRegistry.getInstance();
    private final ConfigPairCommiter mConfigPairCommiter = new ConfigPairCommiter();

    @Nonnull
    private SharedPreferences getSharedPreferences() {
        return this.mConfigRegistry.getConfigEditor(ConfigType.SERVER).getSharedPreferences();
    }

    @SuppressLint({"ApplySharedPref"})
    public void saveConfig(@Nonnull PlayerAppStartupConfigResponse playerAppStartupConfigResponse) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "PlayerAppStartupConfigPersistence:saveConfig");
        SharedPreferences.Editor editorEdit = getSharedPreferences().edit();
        editorEdit.clear();
        this.mConfigPairCommiter.save("customerConfig_", playerAppStartupConfigResponse.customerConfig, editorEdit);
        this.mConfigPairCommiter.save("", playerAppStartupConfigResponse.deviceConfig, editorEdit);
        editorEdit.putString("madeSuccessfulServerConfigFetch", "true");
        editorEdit.commit();
        AppStartConfig.getInstance().onSuccessfulAppStartConfigCall();
        Profiler.endTrace(traceKeyBeginTrace);
    }

    static class ConfigPairCommiter {
        ConfigPairCommiter() {
        }

        public void save(@Nonnull String str, @Nonnull ImmutableMap<String, String> immutableMap, @Nonnull SharedPreferences.Editor editor) {
            Preconditions.checkNotNull(str, "editorKeyPrefix");
            Preconditions.checkNotNull(immutableMap, "configPairs");
            Preconditions.checkNotNull(editor, "editor");
            UnmodifiableIterator<Map.Entry<String, String>> it = immutableMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                String key = next.getKey();
                String value = next.getValue();
                DLog.devf("Updating service config value (key=%s,value=%s)", key, value);
                editor.putString(str + key, value);
            }
        }
    }
}
