package com.amazon.avod.media.playback.support;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaCodecInternalConfig extends ConfigBase {
    private static final String ELEMENT_SPLITTER = ",";
    private static final String KEY_VALUE_SPLITTER = ":";
    private ConfigurationValue<Map<String, String>> mDeviceTunneledPlaybackSupport;

    enum CodecSupport {
        UNINITIALIZED(true, false),
        ANALYZED_FALSE(false, false),
        ANALYZED_TRUE(false, true);

        private final boolean mIsTunneledPlaybackSupported;
        private final boolean mShouldAnalyzeDeviceSupport;

        CodecSupport(boolean z, boolean z2) {
            this.mShouldAnalyzeDeviceSupport = z;
            this.mIsTunneledPlaybackSupported = z2;
        }

        public boolean shouldAnalyzeDeviceSupport() {
            return this.mShouldAnalyzeDeviceSupport;
        }

        public boolean isTunneledPlaybackSupported() {
            return this.mIsTunneledPlaybackSupported;
        }
    }

    private static final class SingletonHolder {
        private static final MediaCodecInternalConfig INSTANCE = new MediaCodecInternalConfig();

        private SingletonHolder() {
        }
    }

    public static MediaCodecInternalConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MediaCodecInternalConfig() {
        super("com.amazon.avod.media.playback.support");
        this.mDeviceTunneledPlaybackSupport = newStringMapConfigValue("playback_deviceTunneledPlaybackSupport", "video/avc:UNINITIALIZED,video/hevc:UNINITIALIZED", ELEMENT_SPLITTER, KEY_VALUE_SPLITTER, ConfigType.INTERNAL);
    }

    @Nullable
    private Map<String, CodecSupport> getCodecTunneledPlaybackMapping() {
        ImmutableMap immutableMapCopyOf = ImmutableMap.copyOf((Map) this.mDeviceTunneledPlaybackSupport.getValue());
        try {
            HashMap mapNewHashMap = Maps.newHashMap();
            UnmodifiableIterator it = immutableMapCopyOf.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                mapNewHashMap.put(entry.getKey(), CodecSupport.valueOf((String) entry.getValue()));
            }
            return mapNewHashMap;
        } catch (IllegalArgumentException e) {
            DLog.exceptionf(e, "invalid value for config key: playback_deviceTunneledPlaybackSupport", new Object[0]);
            return null;
        }
    }

    boolean shouldAnalyzeDeviceTunnelPlaybackSupport(@Nonnull String str) {
        Preconditions.checkNotNull(str, "mimeType");
        Map<String, CodecSupport> codecTunneledPlaybackMapping = getCodecTunneledPlaybackMapping();
        if (codecTunneledPlaybackMapping == null || !codecTunneledPlaybackMapping.containsKey(str)) {
            return true;
        }
        return codecTunneledPlaybackMapping.get(str).shouldAnalyzeDeviceSupport();
    }

    boolean isTunneledPlaybackSupported(@Nonnull String str) {
        Preconditions.checkNotNull(str, "mimeType");
        Map<String, CodecSupport> codecTunneledPlaybackMapping = getCodecTunneledPlaybackMapping();
        if (codecTunneledPlaybackMapping == null || !codecTunneledPlaybackMapping.containsKey(str)) {
            return false;
        }
        return codecTunneledPlaybackMapping.get(str).isTunneledPlaybackSupported();
    }

    void updateCodecTunnelPlaybackSupport(@Nonnull String str, @Nonnull CodecSupport codecSupport) {
        Preconditions.checkNotNull(str, "mimeType");
        Preconditions.checkNotNull(codecSupport, "codecSupport");
        Map<String, CodecSupport> codecTunneledPlaybackMapping = getCodecTunneledPlaybackMapping();
        if (codecTunneledPlaybackMapping != null) {
            codecTunneledPlaybackMapping.put(str, codecSupport);
        }
        updateCodecTunneledPlaybackMapping(codecTunneledPlaybackMapping);
    }

    private void updateCodecTunneledPlaybackMapping(@Nullable Map<String, CodecSupport> map) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        if (map != null) {
            for (Map.Entry<String, CodecSupport> entry : map.entrySet()) {
                builder.put(entry.getKey(), entry.getValue().name());
            }
        }
        this.mDeviceTunneledPlaybackSupport.updateValue(builder.build());
    }
}
