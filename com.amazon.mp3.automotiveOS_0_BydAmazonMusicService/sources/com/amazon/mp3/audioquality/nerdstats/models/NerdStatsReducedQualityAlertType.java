package com.amazon.mp3.audioquality.nerdstats.models;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NerdStatsReducedQualityAlertType.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u0000 \u00072\u00020\u0001:\u0004\u0007\b\t\nB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "Companion", "DownloadSetting", "None", "StreamingSetting", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class NerdStatsReducedQualityAlertType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String message;

    public abstract String getMessage();

    public NerdStatsReducedQualityAlertType(String str) {
        this.message = str;
    }

    /* JADX INFO: compiled from: NerdStatsReducedQualityAlertType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType$StreamingSetting;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "", "toString", "", "hashCode", "", "other", "", "equals", "message", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class StreamingSetting extends NerdStatsReducedQualityAlertType {
        private final String message;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StreamingSetting) && Intrinsics.areEqual(getMessage(), ((StreamingSetting) other).getMessage());
        }

        public int hashCode() {
            return getMessage().hashCode();
        }

        public String toString() {
            return "StreamingSetting(message=" + getMessage() + ')';
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.NerdStatsReducedQualityAlertType
        public String getMessage() {
            return this.message;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StreamingSetting(String message) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }
    }

    /* JADX INFO: compiled from: NerdStatsReducedQualityAlertType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType$DownloadSetting;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "", "toString", "", "hashCode", "", "other", "", "equals", "message", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DownloadSetting extends NerdStatsReducedQualityAlertType {
        private final String message;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DownloadSetting) && Intrinsics.areEqual(getMessage(), ((DownloadSetting) other).getMessage());
        }

        public int hashCode() {
            return getMessage().hashCode();
        }

        public String toString() {
            return "DownloadSetting(message=" + getMessage() + ')';
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.NerdStatsReducedQualityAlertType
        public String getMessage() {
            return this.message;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadSetting(String message) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }
    }

    /* JADX INFO: compiled from: NerdStatsReducedQualityAlertType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType$None;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "", "toString", "", "hashCode", "", "other", "", "equals", "message", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class None extends NerdStatsReducedQualityAlertType {
        private final String message;

        public None() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof None) && Intrinsics.areEqual(getMessage(), ((None) other).getMessage());
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        public String toString() {
            return "None(message=" + ((Object) getMessage()) + ')';
        }

        public /* synthetic */ None(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.NerdStatsReducedQualityAlertType
        public String getMessage() {
            return this.message;
        }

        public None(String str) {
            super(str);
            this.message = str;
        }
    }

    /* JADX INFO: compiled from: NerdStatsReducedQualityAlertType.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType$Companion;", "", "()V", "getDownloadAlert", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "injectedAccess", "Lcom/amazon/mp3/audioquality/nerdstats/models/InjectedAccess;", "qualityMap", "Lcom/amazon/mp3/playback/PlaybackAudioQualityToAudioQuality;", "getDownloadSettingMessage", "", "getQualityAlert", "getStreamingSettingAlert", "getStreamingSettingMessage", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: NerdStatsReducedQualityAlertType.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[StreamingBitrate.values().length];
                iArr[StreamingBitrate.KATANA.ordinal()] = 1;
                iArr[StreamingBitrate.AUTO.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NerdStatsReducedQualityAlertType getQualityAlert(InjectedAccess injectedAccess, PlaybackAudioQualityToAudioQuality qualityMap) {
            Intrinsics.checkNotNullParameter(injectedAccess, "injectedAccess");
            Intrinsics.checkNotNullParameter(qualityMap, "qualityMap");
            PlaybackAudioQuality maxPlaybackQuality = injectedAccess.getMaxPlaybackQuality();
            boolean zIsCurrentMediaItemDownloaded = injectedAccess.isCurrentMediaItemDownloaded();
            int i = 1;
            boolean z = !injectedAccess.hasAnyInternetConnection();
            boolean zIsPrioritizeDownloadsEnabled = injectedAccess.isPrioritizeDownloadsEnabled();
            if (qualityMap.isStandard(maxPlaybackQuality)) {
                return new None(null, i, 0 == true ? 1 : 0);
            }
            if (zIsCurrentMediaItemDownloaded && (z || zIsPrioritizeDownloadsEnabled)) {
                return getDownloadAlert(injectedAccess, qualityMap);
            }
            return getStreamingSettingAlert(injectedAccess, qualityMap);
        }

        private final NerdStatsReducedQualityAlertType getDownloadAlert(InjectedAccess injectedAccess, PlaybackAudioQualityToAudioQuality qualityMap) {
            PlaybackAudioQuality maxPlaybackQuality = injectedAccess.getMaxPlaybackQuality();
            DownloadQualityPreferences currentDownloadQualityPreference = injectedAccess.getCurrentDownloadQualityPreference();
            PlaybackAudioQuality playbackAudioQuality = injectedAccess.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
            Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "injectedAccess.getCurren…es().playbackAudioQuality");
            if (qualityMap.isHD(maxPlaybackQuality)) {
                if ((qualityMap.isHigh(playbackAudioQuality) || qualityMap.isLow(playbackAudioQuality)) && (currentDownloadQualityPreference != DownloadQualityPreferences.AUTO || currentDownloadQualityPreference != DownloadQualityPreferences.HD)) {
                    return new DownloadSetting(getDownloadSettingMessage(injectedAccess, qualityMap));
                }
            } else if (qualityMap.isUHD(maxPlaybackQuality)) {
                if (!qualityMap.isUHD(playbackAudioQuality) && !qualityMap.isAtmosOr360(playbackAudioQuality) && currentDownloadQualityPreference != DownloadQualityPreferences.AUTO) {
                    return new DownloadSetting(getDownloadSettingMessage(injectedAccess, qualityMap));
                }
            } else if (qualityMap.isAtmosOr360(maxPlaybackQuality) && !qualityMap.isAtmosOr360(playbackAudioQuality)) {
                return new DownloadSetting(getDownloadSettingMessage(injectedAccess, qualityMap));
            }
            return new None(null, 1, 0 == true ? 1 : 0);
        }

        private final String getDownloadSettingMessage(InjectedAccess injectedAccess, PlaybackAudioQualityToAudioQuality qualityMap) {
            String string;
            PlaybackAudioQuality playbackAudioQuality = injectedAccess.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
            Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "injectedAccess.getCurren…es().playbackAudioQuality");
            if (qualityMap.isHigh(playbackAudioQuality)) {
                string = injectedAccess.getString(R.string.dmusic_multi_bitrate_streaming_high);
            } else if (qualityMap.isHD(playbackAudioQuality)) {
                string = injectedAccess.getString(R.string.dmusic_device_audio_hd_quality);
            } else if (qualityMap.isUHD(playbackAudioQuality)) {
                string = injectedAccess.getString(R.string.dmusic_nerd_stats_ultra_hd_quality);
            } else if (qualityMap.isAtmos(playbackAudioQuality)) {
                string = injectedAccess.getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
            } else if (qualityMap.isRa360(playbackAudioQuality)) {
                string = injectedAccess.getString(R.string.dmusic_nerd_stats_360_quality);
            } else {
                string = injectedAccess.getString(R.string.dmusic_download_preferences_low);
            }
            return injectedAccess.getString(R.string.dmusic_nerd_stats_downloaded_track, string);
        }

        private final NerdStatsReducedQualityAlertType getStreamingSettingAlert(InjectedAccess injectedAccess, PlaybackAudioQualityToAudioQuality qualityMap) {
            PlaybackAudioQuality maxPlaybackQuality = injectedAccess.getMaxPlaybackQuality();
            int i = 1;
            boolean z = injectedAccess.isWifiConnected() || injectedAccess.isCastingToChromecast();
            StreamingBitrate streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(injectedAccess.getMultiBitrateStreamingPreferenceForOTA());
            StreamingBitrate streamingBitrateFromEntryValue2 = StreamingBitrate.fromEntryValue(injectedAccess.getMultiBitrateStreamingPreferenceForWifi());
            if (z) {
                if ((qualityMap.isHD(maxPlaybackQuality) || qualityMap.isUHD(maxPlaybackQuality)) && (streamingBitrateFromEntryValue2 == StreamingBitrate.LOW || streamingBitrateFromEntryValue2 == StreamingBitrate.HIGH)) {
                    return new StreamingSetting(getStreamingSettingMessage(injectedAccess));
                }
            } else if ((qualityMap.isHD(maxPlaybackQuality) || qualityMap.isUHD(maxPlaybackQuality)) && (streamingBitrateFromEntryValue == StreamingBitrate.LOW || streamingBitrateFromEntryValue == StreamingBitrate.HIGH)) {
                return new StreamingSetting(getStreamingSettingMessage(injectedAccess));
            }
            return new None(null, i, 0 == true ? 1 : 0);
        }

        private final String getStreamingSettingMessage(InjectedAccess injectedAccess) {
            StreamingBitrate streamingBitrateFromEntryValue;
            String string;
            if (injectedAccess.isWifiConnected()) {
                streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(injectedAccess.getMultiBitrateStreamingPreferenceForWifi());
            } else {
                streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(injectedAccess.getMultiBitrateStreamingPreferenceForOTA());
            }
            int i = streamingBitrateFromEntryValue == null ? -1 : WhenMappings.$EnumSwitchMapping$0[streamingBitrateFromEntryValue.ordinal()];
            if (i == 1) {
                string = injectedAccess.getString(R.string.dmusic_multi_bitrate_streaming_HD);
            } else if (i == 2) {
                string = injectedAccess.getString(R.string.dmusic_multi_bitrate_streaming_auto);
            } else {
                string = injectedAccess.getString(R.string.dmusic_multi_bitrate_streaming_high);
            }
            return injectedAccess.getString(R.string.dmusic_nerd_stats_streaming_settings, string);
        }
    }
}
