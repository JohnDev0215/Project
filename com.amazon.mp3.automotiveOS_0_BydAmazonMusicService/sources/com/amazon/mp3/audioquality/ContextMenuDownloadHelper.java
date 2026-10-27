package com.amazon.mp3.audioquality;

import android.content.Context;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.helper.UserPreferenceProvider;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.MediaItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMenuDownloadHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/audioquality/ContextMenuDownloadHelper;", "", "()V", "getDownloadQuality", "", "track", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContextMenuDownloadHelper {

    /* JADX INFO: compiled from: ContextMenuDownloadHelper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadQualityPreferences.values().length];
            iArr[DownloadQualityPreferences.AUTO.ordinal()] = 1;
            iArr[DownloadQualityPreferences.HD.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final String getDownloadQuality(Object track, Context context) {
        ContentEncoding maxContentEncoding;
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(context, "context");
        if (track instanceof MusicTrack) {
            maxContentEncoding = ContentEncodingDecoder.getMaxContentEncoding(((MusicTrack) track).getContentEncodings());
        } else if (track instanceof MediaItem) {
            maxContentEncoding = ContentEncodingDecoder.getMaxContentEncoding(((MediaItem) track).getContentEncodings());
        } else if (track instanceof PrimeTrack) {
            maxContentEncoding = ContentEncodingDecoder.getMaxContentEncoding(((PrimeTrack) track).getContentEncoding());
        } else {
            maxContentEncoding = ContentEncoding.UNKNOWN;
        }
        if (SettingsUtil.isSpatialModeSettingEnabled(context)) {
            if (maxContentEncoding == ContentEncoding.DOLBY_ATMOS) {
                String string = context.getResources().getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…tats_dolby_atmos_quality)");
                return string;
            }
            if (maxContentEncoding == ContentEncoding.SONY_360) {
                String string2 = context.getResources().getString(R.string.dmusic_nerd_stats_360_quality);
                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…c_nerd_stats_360_quality)");
                return string2;
            }
        }
        DownloadQualityPreferences currentDownloadQualityPreference = new UserPreferenceProvider(context).getCurrentDownloadQualityPreference();
        int i = currentDownloadQualityPreference == null ? -1 : WhenMappings.$EnumSwitchMapping$0[currentDownloadQualityPreference.ordinal()];
        if (i == 1) {
            String string3 = context.getResources().getString(R.string.dmusic_multi_bitrate_streaming_auto);
            Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…i_bitrate_streaming_auto)");
            return string3;
        }
        if (i == 2) {
            String string4 = context.getResources().getString(R.string.dmusic_device_audio_hd_quality);
            Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getStr…_device_audio_hd_quality)");
            return string4;
        }
        String string5 = context.getResources().getString(R.string.dmusic_multi_bitrate_streaming_high);
        Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getStr…i_bitrate_streaming_high)");
        return string5;
    }
}
