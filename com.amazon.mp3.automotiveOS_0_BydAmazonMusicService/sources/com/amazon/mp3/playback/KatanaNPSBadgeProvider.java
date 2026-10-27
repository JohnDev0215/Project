package com.amazon.mp3.playback;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.casting.CastingMediaItem;
import com.amazon.mp3.playback.view.BadgeShowcaseView;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlaybackController;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;

/* JADX INFO: loaded from: classes3.dex */
public class KatanaNPSBadgeProvider {
    private static final PlaybackController PLAYBACK_CONTROLLER = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
    private static final String TAG = KatanaNPSBadgeProvider.class.getSimpleName();
    private static boolean hasShownKatanaShowcase = false;

    private static String getSharedPrefName() {
        return "com.amazon.mp3_MaxAvailableAudioQuality";
    }

    public static Observable<AudioQualityBadge> getAudioQualityBadge(final Context context) {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.playback.KatanaNPSBadgeProvider$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return KatanaNPSBadgeProvider.lambda$getAudioQualityBadge$0(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ AudioQualityBadge lambda$getAudioQualityBadge$0(Context context) throws Exception {
        StreamingBitrate streamingBitrateFromEntryValue;
        PlaybackController playbackController = PLAYBACK_CONTROLLER;
        if (playbackController == null) {
            return null;
        }
        MediaItem currentMediaItem = playbackController.getCurrentMediaItem();
        Boolean boolIsCurrentMediaItemDownloaded = isCurrentMediaItemDownloaded(currentMediaItem);
        if (ConnectivityUtil.isWifiConnected(context)) {
            streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(context));
        } else {
            streamingBitrateFromEntryValue = StreamingBitrate.fromEntryValue(SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(context));
        }
        StreamingBitrate streamingBitrate = streamingBitrateFromEntryValue;
        if (isCurrentAndBestAvailableAttrsUnknown()) {
            throw new Exception("PlaybackAudioQuality is UNKNOWN for current and bestAvailable");
        }
        PlaybackAudioQuality maxPlaybackQuality = getMaxPlaybackQuality(context, currentMediaItem);
        if (!hasAsin(currentMediaItem) || NowPlayingUtil.isVideoPlaying()) {
            return null;
        }
        if (maxPlaybackQuality == PlaybackAudioQuality.UNKNOWN) {
            throw new Exception("PlaybackAudioQuality is UNKNOWN");
        }
        AudioQualityBadge audioQualityBadge = getAudioQualityBadge(context, boolIsCurrentMediaItemDownloaded, streamingBitrate, maxPlaybackQuality, currentMediaItem, getCurrentPlaybackAudioQuality());
        persistMaxAudioQuality(context, currentMediaItem, maxPlaybackQuality);
        return audioQualityBadge;
    }

    public static void displayShowcaseIfNeeded(final FragmentActivity activity, final View view) {
        if (hasShownShowcaseAudioQualityBadge(activity) || hasShownKatanaShowcase) {
            return;
        }
        hasShownKatanaShowcase = true;
        BadgeShowcaseView.show(activity, view);
    }

    private static boolean hasAsin(final MediaItem currentMediaItem) {
        MediaItemId mediaItemId;
        return (currentMediaItem == null || (mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN)) == null || StringUtil.isNullOrWhiteSpaces(mediaItemId.getId())) ? false : true;
    }

    public static AudioQualityBadge getAudioQualityBadge(final Context context, @Nullable final Boolean isCurrentMediaItemDownloaded, final StreamingBitrate userPreferredBitrate, final PlaybackAudioQuality maxPlaybackQuality, final MediaItem currentMediaItem, final PlaybackAudioQuality currentPlaybackQuality) {
        if (CastingUtil.isCasting()) {
            return getAudioQualityBadgeForCasting(maxPlaybackQuality, currentMediaItem, context);
        }
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        AudioQualityBadge audioQualityBadge = AudioQualityBadge.STANDARD;
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = currentMediaItem != null ? currentMediaItem.getName() : "";
        objArr[1] = isCurrentMediaItemDownloaded;
        Log.debug(str, "getAudioQualityBadge item: %s, isCurrentMediaItemDownloaded: %b", objArr);
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            if (StreamingBitrate.KATANA == userPreferredBitrate || StreamingBitrate.AUTO == userPreferredBitrate || StreamingBitrate.HIGH == userPreferredBitrate) {
                if (playbackAudioQualityToAudioQuality.isHD(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isHD(maxPlaybackQuality)) {
                    return AudioQualityBadge.HD;
                }
                if (playbackAudioQualityToAudioQuality.isUHD(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isUHD(maxPlaybackQuality)) {
                    return AudioQualityBadge.UHD;
                }
                if (playbackAudioQualityToAudioQuality.isAtmos(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isAtmos(maxPlaybackQuality)) {
                    return AudioQualityBadge.DOLBY_ATMOS;
                }
                if (playbackAudioQualityToAudioQuality.isRa360(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isRa360(maxPlaybackQuality)) {
                    return AudioQualityBadge.SONY_360;
                }
            } else {
                if (playbackAudioQualityToAudioQuality.isHD(maxPlaybackQuality)) {
                    return AudioQualityBadge.HD_ALERT;
                }
                if (playbackAudioQualityToAudioQuality.isUHD(maxPlaybackQuality)) {
                    return AudioQualityBadge.UHD_ALERT;
                }
                if (playbackAudioQualityToAudioQuality.isAtmos(maxPlaybackQuality)) {
                    return AudioQualityBadge.DOLBY_ATMOS_ALERT;
                }
                if (playbackAudioQualityToAudioQuality.isRa360(maxPlaybackQuality)) {
                    return AudioQualityBadge.SONY_360_ALERT;
                }
            }
            if (currentMediaItem.getContentEncodings() != null) {
                if (currentMediaItem.getContentEncodings().contains(ContentEncoding.DOLBY_ATMOS)) {
                    return AudioQualityBadge.DOLBY_ATMOS;
                }
                if (currentMediaItem.getContentEncodings().contains(ContentEncoding.SONY_360)) {
                    return AudioQualityBadge.SONY_360;
                }
                if (currentMediaItem.getContentEncodings().contains(ContentEncoding.UHD)) {
                    return AudioQualityBadge.UHD;
                }
                if (currentMediaItem.getContentEncodings().contains(ContentEncoding.HD)) {
                    return AudioQualityBadge.HD;
                }
            }
        } else if (playbackAudioQualityToAudioQuality.isHD(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isUHD(currentPlaybackQuality)) {
            if (playbackAudioQualityToAudioQuality.isHD(currentPlaybackQuality)) {
                return AudioQualityBadge.HD;
            }
            if (playbackAudioQualityToAudioQuality.isUHD(currentPlaybackQuality)) {
                return AudioQualityBadge.UHD;
            }
        } else {
            if (playbackAudioQualityToAudioQuality.isAtmos(currentPlaybackQuality)) {
                return AudioQualityBadge.DOLBY_ATMOS;
            }
            if (playbackAudioQualityToAudioQuality.isRa360(currentPlaybackQuality)) {
                return AudioQualityBadge.SONY_360;
            }
            if (playbackAudioQualityToAudioQuality.isHigh(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isLow(currentPlaybackQuality)) {
                return audioQualityBadge;
            }
            if (playbackAudioQualityToAudioQuality.isHD(maxPlaybackQuality)) {
                return AudioQualityBadge.HD_ALERT;
            }
            if (playbackAudioQualityToAudioQuality.isUHD(maxPlaybackQuality)) {
                return AudioQualityBadge.UHD_ALERT;
            }
            if (playbackAudioQualityToAudioQuality.isAtmos(maxPlaybackQuality)) {
                return AudioQualityBadge.DOLBY_ATMOS_ALERT;
            }
            if (playbackAudioQualityToAudioQuality.isRa360(maxPlaybackQuality)) {
                return AudioQualityBadge.SONY_360_ALERT;
            }
        }
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(context)) {
            if (playbackAudioQualityToAudioQuality.isAtmos(maxPlaybackQuality)) {
                return AudioQualityBadge.DOLBY_ATMOS;
            }
            if (playbackAudioQualityToAudioQuality.isRa360(maxPlaybackQuality)) {
                return AudioQualityBadge.SONY_360;
            }
        } else if (playbackAudioQualityToAudioQuality.isImmersive(maxPlaybackQuality)) {
            return AudioQualityBadge.THREE_DIMENSIONAL;
        }
        return audioQualityBadge;
    }

    private static AudioQualityBadge getAudioQualityBadgeForCasting(final PlaybackAudioQuality maxPlaybackQuality, final MediaItem currentMediaItem, final Context context) {
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        AudioQualityBadge audioQualityBadge = AudioQualityBadge.STANDARD;
        if (maxPlaybackQuality == PlaybackAudioQuality.UNKNOWN) {
            return null;
        }
        if (playbackAudioQualityToAudioQuality.isHD(maxPlaybackQuality)) {
            return AudioQualityBadge.HD;
        }
        if (playbackAudioQualityToAudioQuality.isUHD(maxPlaybackQuality)) {
            return AudioQualityBadge.UHD;
        }
        if (playbackAudioQualityToAudioQuality.isAtmos(maxPlaybackQuality)) {
            return AudioQualityBadge.DOLBY_ATMOS;
        }
        if (playbackAudioQualityToAudioQuality.isRa360(maxPlaybackQuality)) {
            return AudioQualityBadge.SONY_360;
        }
        if (!playbackAudioQualityToAudioQuality.isImmersive(maxPlaybackQuality)) {
            return audioQualityBadge;
        }
        if ((currentMediaItem instanceof CastingMediaItem) && AmazonApplication.getCapabilities().isSpatialAudioEnabled(context)) {
            CastingMediaItem.CastingPlaybackFormat playbackFormat = ((CastingMediaItem) currentMediaItem).getPlaybackFormat();
            if (playbackFormat == CastingMediaItem.CastingPlaybackFormat.ATMOS) {
                return AudioQualityBadge.DOLBY_ATMOS;
            }
            if (playbackFormat == CastingMediaItem.CastingPlaybackFormat.RA360) {
                return AudioQualityBadge.SONY_360;
            }
        }
        return AudioQualityBadge.THREE_DIMENSIONAL;
    }

    public static PlaybackAudioQuality getMaxPlaybackQuality(final Context context, final MediaItem currentMediaItem) {
        List<ContentEncoding> contentEncodings;
        PlaybackAudioQuality playbackAudioQuality = PLAYBACK_CONTROLLER.getBestAvailablePlaybackAttributes().getPlaybackAudioQuality();
        PlaybackAudioQuality playbackAudioQuality2 = PlaybackAudioQuality.UNKNOWN;
        if (playbackAudioQuality == playbackAudioQuality2 && currentMediaItem != null && (contentEncodings = currentMediaItem.getContentEncodings()) != null && contentEncodings.size() > 0) {
            playbackAudioQuality = SettingsUtil.isSpatialModeSettingEnabled(context) ? ContentEncodingDecoder.getMaxPlaybackAudioQuality(contentEncodings) : ContentEncodingDecoder.getMaxStereoPlaybackAudioQuality(contentEncodings);
        }
        if (playbackAudioQuality == playbackAudioQuality2) {
            playbackAudioQuality = getPersistedMaxPlaybackQuality(context, currentMediaItem);
        }
        if (currentMediaItem != null && currentMediaItem.isOwned() && currentMediaItem.getOverriddenPrimaryId() == null) {
            Log.warning(TAG, "track is owned and currently playing downloaded file, %s = %s", "MaxAvailableAudioQuality", playbackAudioQuality.name());
        } else {
            playbackAudioQuality2 = playbackAudioQuality;
        }
        Log.debug(TAG, "MaxAvailableAudioQuality = " + playbackAudioQuality2.name());
        return playbackAudioQuality2;
    }

    @Nullable
    public static Boolean isCurrentMediaItemDownloaded(final MediaItem currentMediaItem) {
        PlaybackController playbackController = PLAYBACK_CONTROLLER;
        if (playbackController.getCurrentPlaybackAttributes() == null) {
            return null;
        }
        if (!playbackController.isStreaming()) {
            return Boolean.TRUE;
        }
        return Boolean.valueOf((currentMediaItem == null || currentMediaItem.getMediaAccessInfo() == null || !currentMediaItem.getMediaAccessInfo().isAvailableOffline()) ? false : true);
    }

    public static PlaybackAudioQuality getCurrentPlaybackAudioQuality() {
        PlaybackController playbackController = PLAYBACK_CONTROLLER;
        if (playbackController.getCurrentPlaybackAttributes() != null) {
            return playbackController.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
        }
        return PlaybackAudioQuality.UNKNOWN;
    }

    private static boolean isCurrentAndBestAvailableAttrsUnknown() {
        PlaybackController playbackController = PLAYBACK_CONTROLLER;
        PlaybackAttributes currentPlaybackAttributes = playbackController.getCurrentPlaybackAttributes();
        PlaybackAttributes playbackAttributes = PlaybackAttributes.UNKNOWN;
        return currentPlaybackAttributes == playbackAttributes && playbackController.getBestAvailablePlaybackAttributes() == playbackAttributes;
    }

    private static PlaybackAudioQuality getPersistedMaxPlaybackQuality(final Context context, final MediaItem currentMediaItem) {
        if (context == null || currentMediaItem == null) {
            return PlaybackAudioQuality.UNKNOWN;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(getSharedPrefName(), 0);
        String strName = PlaybackAudioQuality.UNKNOWN.name();
        Iterator<MediaItemId> it = currentMediaItem.getMediaItemIds().iterator();
        while (true) {
            if (it.hasNext()) {
                String id = it.next().getId();
                if (!StringUtil.isNullOrWhiteSpaces(id)) {
                    PlaybackAudioQuality playbackAudioQuality = PlaybackAudioQuality.UNKNOWN;
                    String string = sharedPreferences.getString(id, playbackAudioQuality.name());
                    if (!string.equals(playbackAudioQuality.name())) {
                        strName = string;
                        break;
                    }
                    strName = string;
                }
            }
        }
        try {
            return PlaybackAudioQuality.valueOf(strName);
        } catch (IllegalArgumentException unused) {
            return PlaybackAudioQuality.UNKNOWN;
        }
    }

    public static void setShowcaseAudioQualityBadgeStatus(Context context, final boolean value) {
        SettingsUtil.setBooleanPref(context.getApplicationContext(), R.string.setting_key_showcase_audio_quality_badge, value);
    }

    public static boolean hasShownShowcaseAudioQualityBadge(Context context) {
        return SettingsUtil.getBooleanPref(context.getApplicationContext(), R.string.setting_key_showcase_audio_quality_badge);
    }

    private static void persistMaxAudioQuality(final Context context, final MediaItem currentMediaItem, final PlaybackAudioQuality maxPlaybackQuality) {
        if (context == null || currentMediaItem == null || maxPlaybackQuality == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(getSharedPrefName(), 0).edit();
        for (MediaItemId mediaItemId : currentMediaItem.getMediaItemIds()) {
            if (!StringUtil.isNullOrWhiteSpaces(mediaItemId.getId())) {
                editorEdit.putString(mediaItemId.getId(), maxPlaybackQuality.name());
            }
        }
        editorEdit.apply();
    }

    public enum AudioQualityBadge {
        STANDARD(R.string.dmusic_nps_audio_quality_badge_standard_text, R.string.dmusic_audio_stats_sd_quality_message, 16, 48.0d, R.string.dmusic_nps_audio_quality_badge_standard_text, R.string.dmusic_audio_stats_sd_quality_message),
        HD(R.string.dmusic_device_audio_hd_quality, R.string.dmusic_audio_stats_hd_quality_message, 16, 44.1d, R.string.dmusic_device_audio_hd_quality, R.string.dmusic_audio_stats_hd_quality_message),
        UHD(R.string.dmusic_nps_audio_quality_badge_uhd_text, R.string.dmusic_audio_stats_uhd_quality_message, 24, 192.0d, R.string.dmusic_nps_audio_quality_badge_uhd_text, R.string.dmusic_audio_stats_uhd_quality_message),
        THREE_DIMENSIONAL(R.string.dmusic_nps_audio_quality_badge_immersive_text, R.string.dmusic_audio_stats_uhd_quality_message, 24, 192.0d, R.string.dmusic_nps_audio_quality_badge_immersive_text, R.string.dmusic_audio_stats_uhd_quality_message),
        DOLBY_ATMOS(R.string.dmusic_badge_nps_atmos_text, R.string.dmusic_badge_atmos_description, 24, 192.0d, R.string.dmusic_badge_atmos_text, R.string.dmusic_badge_atmos_description),
        SONY_360(R.string.dmusic_nerd_stats_360_quality, R.string.dmusic_badge_ra360_description, 24, 192.0d, R.string.dmusic_badge_ra360_text, R.string.dmusic_badge_ra360_description),
        STANDARD_ALERT(R.string.dmusic_nps_audio_quality_badge_standard_text, -1, 16, 48.0d, R.string.dmusic_nps_audio_quality_badge_standard_alert_text, -1),
        HD_ALERT(R.string.dmusic_device_audio_hd_quality, R.string.dmusic_audio_stats_hd_alert_quality_message, 16, 44.1d, R.string.dmusic_nps_audio_quality_badge_hd_alert_text, R.string.dmusic_audio_stats_hd_download_alert_quality_message),
        UHD_ALERT(R.string.dmusic_nps_audio_quality_badge_uhd_text, R.string.dmusic_audio_stats_uhd_alert_quality_message, 24, 192.0d, R.string.dmusic_nps_audio_quality_badge_uhd_alert_text, R.string.dmusic_audio_stats_uhd_download_alert_quality_message),
        DOLBY_ATMOS_ALERT(R.string.dmusic_badge_nps_atmos_text, R.string.dmusic_nps_badge_atmos_streaming_alert_quality_message, 24, 192.0d, R.string.dmusic_nps_badge_atmos_alert_quality_text, R.string.dmusic_nps_badge_atmos_download_alert_quality_message),
        SONY_360_ALERT(R.string.dmusic_nerd_stats_360_quality, R.string.dmusic_nps_badge_ra360_streaming_alert_quality_message, 24, 192.0d, R.string.dmusic_nps_badge_ra360_alert_quality_text, R.string.dmusic_nps_badge_ra360_download_alert_quality_message);

        private int badgeTextDownloadedResId;
        private int badgeTextResId;
        private int badgeTitleResId;
        private int contentDescription;
        private short maxBitDepth;
        private double maxFrequency;

        AudioQualityBadge(final int badgeTitleResId, final int badgeTextResId, final short maxBitDepth, final double maxFrequency, final int contentDescription, final int badgeTextDownloadedResId) {
            this.badgeTitleResId = badgeTitleResId;
            this.badgeTextResId = badgeTextResId;
            this.maxBitDepth = maxBitDepth;
            this.maxFrequency = maxFrequency;
            this.contentDescription = contentDescription;
            this.badgeTextDownloadedResId = badgeTextDownloadedResId;
        }

        public int getBadgeTitleResId() {
            return this.badgeTitleResId;
        }

        public int getBadgeTextResId() {
            return this.badgeTextResId;
        }

        public short getMaxBitDepth() {
            return this.maxBitDepth;
        }

        public double getMaxFrequency() {
            return this.maxFrequency;
        }

        public int getContentDescription() {
            return this.contentDescription;
        }

        public int getBadgeTextDownloadedResId() {
            return this.badgeTextDownloadedResId;
        }

        public boolean isAlert() {
            return this == STANDARD_ALERT || this == HD_ALERT || this == UHD_ALERT || this == DOLBY_ATMOS_ALERT || this == SONY_360_ALERT;
        }
    }
}
