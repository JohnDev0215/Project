package com.amazon.music.curate.skyfire;

import android.content.Context;
import com.amazon.music.curate.R;
import com.amazon.music.subscription.UserSubscription;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class AudioContentEncodingUtil {
    private static final String DOLBY_ATMOS = "atmosAvailable";
    private static final String HD = "hdAvailable";
    private static final String IMMERSIVE = "immersive";
    private static final String SONY_360 = "ra360Available";
    private static final String UHD = "uhdAvailable";

    public static String getAudioQualityText(final Context context, final List<String> contentEncoding) {
        if (contentEncoding != null && !contentEncoding.isEmpty()) {
            if (contentEncoding.contains(UHD)) {
                return context.getResources().getString(R.string.katana_uhd_audio_badge);
            }
            if (contentEncoding.contains(HD)) {
                return context.getResources().getString(R.string.katana_hd_audio_badge);
            }
        }
        return null;
    }

    public static String getImmersiveBadgeText(final Context context, final List<String> contentEncoding, final UserSubscription userSubscription) {
        if (contentEncoding != null && !contentEncoding.isEmpty() && userSubscription != null && userSubscription.isIvy()) {
            if (contentEncoding.contains(SONY_360)) {
                return context.getResources().getString(R.string.ivy_ra360_badge);
            }
            if (contentEncoding.contains(DOLBY_ATMOS)) {
                return context.getResources().getString(R.string.ivy_atmos_badge);
            }
            if (contentEncoding.contains(IMMERSIVE)) {
                return context.getResources().getString(R.string.immersive_audio_badge);
            }
        }
        return null;
    }

    public static boolean containsBothAtmosAnd360(final List<String> contentEncoding, final UserSubscription userSubscription) {
        return contentEncoding != null && !contentEncoding.isEmpty() && userSubscription != null && userSubscription.isIvy() && contentEncoding.contains(SONY_360) && contentEncoding.contains(DOLBY_ATMOS);
    }
}
