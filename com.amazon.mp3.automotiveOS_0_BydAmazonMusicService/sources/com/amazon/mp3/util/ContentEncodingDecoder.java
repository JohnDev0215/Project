package com.amazon.mp3.util;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.view.model.BadgeModel;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes4.dex */
public class ContentEncodingDecoder {
    public static List<ContentEncoding> loadFrom(final List<String> encodingValues) {
        ArrayList arrayList = new ArrayList();
        if (encodingValues == null) {
            return arrayList;
        }
        if (encodingValues.contains("hdAvailable")) {
            arrayList.add(ContentEncoding.HD);
        }
        if (encodingValues.contains("uhdAvailable")) {
            arrayList.add(ContentEncoding.UHD);
        }
        if (encodingValues.contains("immersive")) {
            arrayList.add(ContentEncoding.IMMERSIVE);
        }
        if (encodingValues.contains("atmosAvailable")) {
            arrayList.add(ContentEncoding.DOLBY_ATMOS);
        }
        if (encodingValues.contains("ra360Available")) {
            arrayList.add(ContentEncoding.SONY_360);
        }
        return arrayList;
    }

    public static List<ContentEncoding> loadFrom(final JSONArray encodingValues) {
        if (encodingValues != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < encodingValues.length(); i++) {
                try {
                    arrayList.add(encodingValues.getString(i));
                } catch (Exception unused) {
                    return new ArrayList();
                }
            }
            return loadFrom(arrayList);
        }
        return new ArrayList();
    }

    public static List<String> parseFrom(final List<ContentEncoding> contentEncodings) {
        ArrayList arrayList = new ArrayList();
        if (contentEncodings != null && contentEncodings.size() != 0) {
            if (contentEncodings.contains(ContentEncoding.HD)) {
                arrayList.add("hdAvailable");
            }
            if (contentEncodings.contains(ContentEncoding.UHD)) {
                arrayList.add("uhdAvailable");
            }
            if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(AmazonApplication.getContext())) {
                if (contentEncodings.contains(ContentEncoding.DOLBY_ATMOS)) {
                    arrayList.add("atmosAvailable");
                }
                if (contentEncodings.contains(ContentEncoding.SONY_360)) {
                    arrayList.add("ra360Available");
                }
            } else if (contentEncodings.contains(ContentEncoding.IMMERSIVE)) {
                arrayList.add("immersive");
            }
        }
        return arrayList;
    }

    public static BadgeModel getEncodingBadgeModel(final List<ContentEncoding> encodings, final Context context) {
        return getEncodingBadgeModel(encodings, context, 8);
    }

    public static BadgeModel getEncodingBadgeModel(final List<ContentEncoding> encodings, final Context context, final int badgeVisibility) {
        return getEncodingBadgeModel(encodings, context, badgeVisibility, AmazonApplication.getCapabilities().isKatanaSupported());
    }

    public static BadgeModel getEncodingBadgeModel(final List<ContentEncoding> encodings, final Context context, final int badgeVisibility, final boolean isKatanaSupported) {
        if (context == null || encodings == null) {
            return new BadgeModel();
        }
        if (isKatanaSupported) {
            if (encodings.contains(ContentEncoding.UHD)) {
                return new BadgeModel(context.getResources().getString(R.string.katana_uhd_audio_badge), context.getResources().getString(R.string.contentEncoding_badge_description_uhd), 0);
            }
            if (encodings.contains(ContentEncoding.HD)) {
                return new BadgeModel(context.getResources().getString(R.string.katana_hd_audio_badge), context.getResources().getString(R.string.contentEncoding_badge_description_hd), 0);
            }
        }
        return new BadgeModel("", "", badgeVisibility);
    }

    public static BadgeModel getImmersiveContentBadgeModel(final List<ContentEncoding> encodings, final Context context, final int badgeVisibility) {
        return getImmersiveContentBadgeModel(encodings, context, badgeVisibility, null);
    }

    public static BadgeModel getImmersiveContentBadgeModel(final List<ContentEncoding> encodings, final Context context, final int badgeVisibility, @Nullable final Boolean isIvy) {
        boolean zBooleanValue;
        if (isIvy == null) {
            zBooleanValue = UserSubscriptionUtil.getUserSubscription().isIvy();
        } else {
            zBooleanValue = isIvy.booleanValue();
        }
        if (context == null) {
            return new BadgeModel();
        }
        if (encodings == null || encodings.size() == 0) {
            return new BadgeModel("", "", badgeVisibility);
        }
        if (!zBooleanValue) {
            return new BadgeModel("", "", badgeVisibility);
        }
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(context)) {
            if (encodings.contains(ContentEncoding.SONY_360)) {
                return new BadgeModel(context.getResources().getString(R.string.dmusic_badge_ra360_text), context.getResources().getString(R.string.dmusic_badge_ra360_description), 0);
            }
            if (encodings.contains(ContentEncoding.DOLBY_ATMOS)) {
                return new BadgeModel(context.getResources().getString(R.string.dmusic_badge_atmos_text), context.getResources().getString(R.string.dmusic_badge_atmos_description), 0, Integer.valueOf(R.drawable.ic_dd_logo));
            }
            return new BadgeModel("", "", badgeVisibility);
        }
        if (encodings.contains(ContentEncoding.IMMERSIVE)) {
            return new BadgeModel(context.getResources().getString(R.string.ivy_immersive_audio_badge), context.getResources().getString(R.string.immersive_badge_description), 0);
        }
        return new BadgeModel("", "", badgeVisibility);
    }

    public static BadgeModel getExtraAlbumAtmosContentBadgeModel(final List<ContentEncoding> encodings, final Context context, final int badgeVisibility, final boolean showText) {
        if (context == null) {
            return new BadgeModel();
        }
        if (encodings == null || encodings.size() == 0 || !UserSubscriptionUtil.getUserSubscription().isIvy()) {
            return new BadgeModel("", "", badgeVisibility);
        }
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(context) && encodings.contains(ContentEncoding.DOLBY_ATMOS)) {
            return new BadgeModel(showText ? context.getResources().getString(R.string.dmusic_badge_atmos_text) : "", context.getResources().getString(R.string.dmusic_badge_atmos_description), 0, Integer.valueOf(R.drawable.ic_dd_logo));
        }
        return new BadgeModel("", "", badgeVisibility);
    }

    public static ContentEncoding getMaxContentEncoding(final List<ContentEncoding> encodings) {
        if (encodings == null || encodings.size() == 0) {
            return ContentEncoding.UNKNOWN;
        }
        if (!UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return ContentEncoding.UNKNOWN;
        }
        ContentEncoding contentEncoding = ContentEncoding.DOLBY_ATMOS;
        if (encodings.contains(contentEncoding)) {
            return contentEncoding;
        }
        ContentEncoding contentEncoding2 = ContentEncoding.SONY_360;
        if (encodings.contains(contentEncoding2)) {
            return contentEncoding2;
        }
        ContentEncoding contentEncoding3 = ContentEncoding.UHD;
        if (encodings.contains(contentEncoding3)) {
            return contentEncoding3;
        }
        ContentEncoding contentEncoding4 = ContentEncoding.HD;
        return encodings.contains(contentEncoding4) ? contentEncoding4 : ContentEncoding.UNKNOWN;
    }

    private static ContentEncoding getMaxStereoContentEncoding(final List<ContentEncoding> encodings) {
        if (encodings == null || encodings.size() == 0) {
            return ContentEncoding.UNKNOWN;
        }
        if (!UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return ContentEncoding.UNKNOWN;
        }
        ContentEncoding contentEncoding = ContentEncoding.UHD;
        if (encodings.contains(contentEncoding)) {
            return contentEncoding;
        }
        ContentEncoding contentEncoding2 = ContentEncoding.HD;
        return encodings.contains(contentEncoding2) ? contentEncoding2 : ContentEncoding.UNKNOWN;
    }

    public static String contentEncodingToString(List<ContentEncoding> contentEncoding) {
        return TextUtils.join(",", parseFrom(contentEncoding));
    }

    public static List<ContentEncoding> stringtoContentEncoding(String encodings) {
        if (encodings == null) {
            return loadFrom((List<String>) Arrays.asList(""));
        }
        if (encodings.contains(" ")) {
            return loadFrom((List<String>) Arrays.asList(encodings.split(", ")));
        }
        return loadFrom((List<String>) Arrays.asList(encodings.split(",")));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.util.ContentEncodingDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$contentEncoding$ContentEncoding;

        static {
            int[] iArr = new int[ContentEncoding.values().length];
            $SwitchMap$com$amazon$music$contentEncoding$ContentEncoding = iArr;
            try {
                iArr[ContentEncoding.UHD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$contentEncoding$ContentEncoding[ContentEncoding.HD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$contentEncoding$ContentEncoding[ContentEncoding.DOLBY_ATMOS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$contentEncoding$ContentEncoding[ContentEncoding.SONY_360.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static PlaybackAudioQuality getMaxPlaybackAudioQuality(final List<ContentEncoding> contentEncodings) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$contentEncoding$ContentEncoding[getMaxContentEncoding(contentEncodings).ordinal()];
        if (i == 1) {
            return PlaybackAudioQuality.UHD192;
        }
        if (i == 2) {
            return PlaybackAudioQuality.HD192;
        }
        if (i == 3) {
            return PlaybackAudioQuality.THREED_DATMOS_HIGH;
        }
        if (i == 4) {
            return PlaybackAudioQuality.THREED_S360RA_L3;
        }
        return PlaybackAudioQuality.UNKNOWN;
    }

    public static PlaybackAudioQuality getMaxStereoPlaybackAudioQuality(final List<ContentEncoding> contentEncodings) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$contentEncoding$ContentEncoding[getMaxStereoContentEncoding(contentEncodings).ordinal()];
        if (i == 1) {
            return PlaybackAudioQuality.UHD192;
        }
        if (i == 2) {
            return PlaybackAudioQuality.HD192;
        }
        return PlaybackAudioQuality.UNKNOWN;
    }

    public static List<ContentEncoding> getAggregatedContentEncoding(final String tracksContentEncodings) {
        if (TextUtils.isEmpty(tracksContentEncodings)) {
            return null;
        }
        return loadFrom((List<String>) Arrays.asList(tracksContentEncodings.split(",")));
    }
}
