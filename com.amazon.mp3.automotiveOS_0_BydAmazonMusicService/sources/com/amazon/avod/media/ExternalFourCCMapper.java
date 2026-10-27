package com.amazon.avod.media;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ExternalFourCCMapper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/amazon/avod/media/ExternalFourCCMapper;", "", "", "codecName", "Lcom/amazon/avod/media/VideoStreamType;", "mapVideoCodecToStreamType", "Lcom/amazon/avod/media/AudioStreamType;", "mapAudioCodecToStreamType", "Lcom/amazon/avod/media/ExternalFourCCMapper$FourCCMapConfig;", "mFourCCMapConfig", "Lcom/amazon/avod/media/ExternalFourCCMapper$FourCCMapConfig;", "<init>", "()V", "FourCCMapConfig", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0})
public final class ExternalFourCCMapper {
    public static final ExternalFourCCMapper INSTANCE = new ExternalFourCCMapper();
    private static final FourCCMapConfig mFourCCMapConfig = FourCCMapConfig.INSTANCE;

    private ExternalFourCCMapper() {
    }

    public final VideoStreamType mapVideoCodecToStreamType(String codecName) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        Map<String, String> value = mFourCCMapConfig.getMVideoFourCCMap().getValue();
        String str = (String) StringsKt__StringsKt.split$default((CharSequence) codecName, new String[]{"."}, false, 0, 6, (Object) null).get(0);
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = str.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        String str2 = value.get(lowerCase);
        if (str2 == null) {
            return null;
        }
        return VideoStreamType.valueOf(str2);
    }

    public final AudioStreamType mapAudioCodecToStreamType(String codecName) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        Map<String, String> value = mFourCCMapConfig.getMAudioFourCCMap().getValue();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = codecName.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        String str = value.get(lowerCase);
        if (str == null) {
            return null;
        }
        return AudioStreamType.valueOf(str);
    }

    /* JADX INFO: compiled from: ExternalFourCCMapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fR)\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR)\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00030\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/avod/media/ExternalFourCCMapper$FourCCMapConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "Lamazon/android/config/ConfigurationValue;", "", "", "mVideoFourCCMap", "Lamazon/android/config/ConfigurationValue;", "getMVideoFourCCMap", "()Lamazon/android/config/ConfigurationValue;", "mAudioFourCCMap", "getMAudioFourCCMap", "<init>", "()V", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0})
    public static final class FourCCMapConfig extends MediaConfigBase {
        public static final FourCCMapConfig INSTANCE;
        private static final ConfigurationValue<Map<String, String>> mAudioFourCCMap;
        private static final ConfigurationValue<Map<String, String>> mVideoFourCCMap;

        private FourCCMapConfig() {
        }

        public final ConfigurationValue<Map<String, String>> getMVideoFourCCMap() {
            return mVideoFourCCMap;
        }

        static {
            FourCCMapConfig fourCCMapConfig = new FourCCMapConfig();
            INSTANCE = fourCCMapConfig;
            ConfigType configType = ConfigType.SERVER;
            ConfigurationValue<Map<String, String>> configurationValueNewStringMapConfigValue = fourCCMapConfig.newStringMapConfigValue("playback_videoFourCCMap", "avc1:H264,h264:H264,hev1:H265,hvc1:H265,dvhe:DVHE,av01:AV01", ",", ":", configType);
            Intrinsics.checkNotNullExpressionValue(configurationValueNewStringMapConfigValue, "newStringMapConfigValue(…       ConfigType.SERVER)");
            mVideoFourCCMap = configurationValueNewStringMapConfigValue;
            ConfigurationValue<Map<String, String>> configurationValueNewStringMapConfigValue2 = fourCCMapConfig.newStringMapConfigValue("playback_audioFourCCMap", "mp4a.40.2:AACL,mp4a.40.5:AACH,mp4a.40.29:AACHV2,ec-3:DDP", ",", ":", configType);
            Intrinsics.checkNotNullExpressionValue(configurationValueNewStringMapConfigValue2, "newStringMapConfigValue(…itter, ConfigType.SERVER)");
            mAudioFourCCMap = configurationValueNewStringMapConfigValue2;
        }

        public final ConfigurationValue<Map<String, String>> getMAudioFourCCMap() {
            return mAudioFourCCMap;
        }
    }
}
