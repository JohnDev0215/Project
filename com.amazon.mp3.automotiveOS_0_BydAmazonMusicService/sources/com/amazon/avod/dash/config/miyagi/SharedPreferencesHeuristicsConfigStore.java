package com.amazon.avod.dash.config.miyagi;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigRegistry;
import amazon.android.config.ConfigType;
import android.content.SharedPreferences;
import com.amazon.avod.content.config.ClassifierConfig;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.shortbuffer.G2S2NativeShortBufferHeuristicsConfig;
import com.amazon.avod.content.urlvending.QoeConfig;
import com.amazon.avod.dash.config.bola.BolaConfig;
import com.amazon.avod.dash.config.g2s2.G2S2CVBRSupportConfig;
import com.amazon.avod.dash.config.g2s2.G2S2DownloadConfig;
import com.amazon.avod.dash.config.g2s2.G2S2GraphConfigVOD;
import com.amazon.avod.dash.config.g2s2.G2S2ThroughputBitrateSelectorConfig;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SharedPreferencesHeuristicsConfigStore implements HeuristicsConfigStore {
    private static final ImmutableMap<String, String> NO_VALUE = ImmutableMap.builder().build();
    private final ConfigEditor mConfigEditor;
    private final ConfigEditor mOverridesConfigEditor;

    private static class SingletonHolder {
        private static final SharedPreferencesHeuristicsConfigStore INSTANCE = new SharedPreferencesHeuristicsConfigStore();

        private SingletonHolder() {
        }
    }

    public static SharedPreferencesHeuristicsConfigStore getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public SharedPreferencesHeuristicsConfigStore() {
        this(ConfigRegistry.getInstance().getConfigEditor(ConfigType.PLAYBACK_HEURISTICS), ConfigRegistry.getInstance().getConfigEditor(ConfigType.DEBUG_OVERRIDES));
    }

    SharedPreferencesHeuristicsConfigStore(@Nonnull ConfigEditor configEditor, @Nonnull ConfigEditor configEditor2) {
        this.mConfigEditor = (ConfigEditor) Preconditions.checkNotNull(configEditor, "configEditor");
        this.mOverridesConfigEditor = (ConfigEditor) Preconditions.checkNotNull(configEditor2, "overridesConfigEditor");
    }

    @Override // com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore
    @Nonnull
    public HeuristicsConfig getDefaultHeuristicsConfig() {
        Map<String, String> mapConvertMap = convertMap(this.mConfigEditor.getAll());
        QALog.newQALog(PlaybackQAEvent.PLAYBACK_HEURISTICS_SETTINGS_DETAILS).addMetric(PlaybackQAMetric.NOTE, new JSONObject((Map<?, ?>) mapConvertMap)).send();
        ImmutableMap<String, String> immutableMap = NO_VALUE;
        return new HeuristicsConfig(new FixedGraphConfig(immutableMap, immutableMap, mapConvertMap, G2S2GraphConfigVOD.getInstance()), new FixedDownloadConfig(immutableMap, immutableMap, mapConvertMap, G2S2DownloadConfig.getInstance()), new FixedCVBRSupportConfig(immutableMap, immutableMap, mapConvertMap, G2S2CVBRSupportConfig.getInstance()), new FixedThroughputBitrateSelectorConfig(immutableMap, immutableMap, mapConvertMap, G2S2ThroughputBitrateSelectorConfig.getInstance()), new OverridableHeuristicsPlaybackConfig(immutableMap, immutableMap, mapConvertMap, SmoothStreamingPlaybackConfig.INSTANCE, LiveStreamingPlaybackConfig.getInstance(), SmoothStreamingHeuristicConfig.getInstance(new DeviceConfiguration()), ClassifierConfig.INSTANCE), new FixedNativeShortBufferHeuristicsConfig(immutableMap, immutableMap, mapConvertMap, G2S2NativeShortBufferHeuristicsConfig.getInstance()), new FixedQoeConfig(immutableMap, immutableMap, mapConvertMap, QoeConfig.INSTANCE), new FixedBolaConfig(immutableMap, immutableMap, mapConvertMap, BolaConfig.INSTANCE), getHeuristicsSettingsId(mapConvertMap));
    }

    @Override // com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore
    @Nonnull
    public HeuristicsConfig createMergedHeuristicsConfig(@Nonnull Map<String, String> map) {
        Preconditions.checkNotNull(map, "partialConfig");
        Map<String, String> mapConvertMap = convertMap(this.mConfigEditor.getAll());
        Map<String, String> mapConvertMap2 = convertMap(this.mOverridesConfigEditor.getAll());
        HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(mapConvertMap.size() + map.size());
        mapNewHashMapWithExpectedSize.putAll(mapConvertMap);
        mapNewHashMapWithExpectedSize.putAll(map);
        mapNewHashMapWithExpectedSize.putAll(mapConvertMap2);
        QALog.newQALog(PlaybackQAEvent.PLAYBACK_HEURISTICS_SETTINGS_DETAILS).addMetric(PlaybackQAMetric.NOTE, new JSONObject((Map<?, ?>) mapNewHashMapWithExpectedSize)).send();
        return new HeuristicsConfig(new FixedGraphConfig(mapConvertMap2, map, mapConvertMap, G2S2GraphConfigVOD.getInstance()), new FixedDownloadConfig(mapConvertMap2, map, mapConvertMap, G2S2DownloadConfig.getInstance()), new FixedCVBRSupportConfig(mapConvertMap2, map, mapConvertMap, G2S2CVBRSupportConfig.getInstance()), new FixedThroughputBitrateSelectorConfig(mapConvertMap2, map, mapConvertMap, G2S2ThroughputBitrateSelectorConfig.getInstance()), new OverridableHeuristicsPlaybackConfig(mapConvertMap2, map, mapConvertMap, SmoothStreamingPlaybackConfig.INSTANCE, LiveStreamingPlaybackConfig.getInstance(), SmoothStreamingHeuristicConfig.getInstance(new DeviceConfiguration()), ClassifierConfig.INSTANCE), new FixedNativeShortBufferHeuristicsConfig(mapConvertMap2, map, mapConvertMap, G2S2NativeShortBufferHeuristicsConfig.getInstance()), new FixedQoeConfig(mapConvertMap2, map, mapConvertMap, QoeConfig.INSTANCE), new FixedBolaConfig(mapConvertMap2, map, mapConvertMap, BolaConfig.INSTANCE), getHeuristicsSettingsIdForMergedConfig(map, mapConvertMap));
    }

    @Override // com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore
    public void reload(Map<String, String> map) {
        SharedPreferences.Editor editorEdit = this.mConfigEditor.getSharedPreferences().edit();
        editorEdit.clear();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            editorEdit.putString(entry.getKey(), entry.getValue());
        }
        editorEdit.putLong(HeuristicsConfigStore.STORED_TIME_KEY, System.currentTimeMillis());
        editorEdit.commit();
        DLog.logf("Updated default heuristics configuration");
    }

    @Override // com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore
    public long getTimeToLiveMillis() {
        long longConfig = this.mConfigEditor.getLongConfig(HeuristicsConfigStore.TIME_TO_LIVE_MILLIS_KEY, -1L);
        long j = this.mConfigEditor.getSharedPreferences().getLong(HeuristicsConfigStore.STORED_TIME_KEY, -1L);
        if (longConfig == -1 || j == -1) {
            DLog.warnf("Bad time value. timeToLiveMillis when storing: %s ms. storedTimeMillis: %s ms.", Long.valueOf(longConfig), Long.valueOf(j));
            return -1L;
        }
        return (j - System.currentTimeMillis()) + longConfig;
    }

    @Nonnull
    String getHeuristicsSettingsIdForMergedConfig(@Nonnull Map<String, String> map, @Nonnull Map<String, String> map2) {
        String str = ((Map) Preconditions.checkNotNull(map, "partialConfigOverrides")).containsKey(HeuristicsConfigStore.SETTINGS_ID_KEY) ? map.get(HeuristicsConfigStore.SETTINGS_ID_KEY) : (String) ((Map) Preconditions.checkNotNull(map2, "appLoadValues")).get(HeuristicsConfigStore.SETTINGS_ID_KEY);
        return str == null ? "HeuristicsSettingsIdNotFound" : str;
    }

    @Nonnull
    String getHeuristicsSettingsId(@Nonnull Map<String, String> map) {
        String str = map.get(HeuristicsConfigStore.SETTINGS_ID_KEY);
        return str == null ? "HeuristicsSettingsIdNotFound" : str;
    }

    private static Map<String, String> convertMap(Map<String, Object> map) {
        HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            mapNewHashMapWithExpectedSize.put(entry.getKey(), entry.getValue().toString());
        }
        return mapNewHashMapWithExpectedSize;
    }
}
